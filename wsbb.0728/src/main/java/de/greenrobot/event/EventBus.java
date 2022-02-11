package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class EventBus {
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    public static String TAG = "Event";
    static volatile EventBus defaultInstance;
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal<PostingThreadState> currentPostingThreadState;
    private final boolean eventInheritance;
    private final ExecutorService executorService;
    private final boolean logNoSubscriberMessages;
    private final boolean logSubscriberExceptions;
    private final HandlerPoster mainThreadPoster;
    private final boolean sendNoSubscriberEvent;
    private final boolean sendSubscriberExceptionEvent;
    private final Map<Class<?>, Object> stickyEvents;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final boolean throwSubscriberException;
    private final Map<Object, List<Class<?>>> typesBySubscriber;

    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    static final class PostingThreadState {
        boolean canceled;
        Object event;
        final List<Object> eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }

    public EventBus() {
        this(DEFAULT_BUILDER);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.currentPostingThreadState = new ThreadLocal<PostingThreadState>() {
            /* access modifiers changed from: protected */
            public PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        this.subscriptionsByEventType = new HashMap();
        this.typesBySubscriber = new HashMap();
        this.stickyEvents = new ConcurrentHashMap();
        this.mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
        this.backgroundPoster = new BackgroundPoster(this);
        this.asyncPoster = new AsyncPoster(this);
        this.subscriberMethodFinder = new SubscriberMethodFinder(eventBusBuilder.skipMethodVerificationForClasses);
        this.logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        this.logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        this.sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        this.sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        this.throwSubscriberException = eventBusBuilder.throwSubscriberException;
        this.eventInheritance = eventBusBuilder.eventInheritance;
        this.executorService = eventBusBuilder.executorService;
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                try {
                    if (defaultInstance == null) {
                        defaultInstance = new EventBus();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<EventBus> cls = EventBus.class;
                        throw th;
                    }
                }
            }
        }
        return defaultInstance;
    }

    private void handleSubscriberException(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.logSubscriberExceptions) {
                String str = TAG;
                Log.e(str, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                String str2 = TAG;
                Log.e(str2, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
            }
        } else if (!this.throwSubscriberException) {
            if (this.logSubscriberExceptions) {
                String str3 = TAG;
                Log.e(str3, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), th);
            }
            if (this.sendSubscriberExceptionEvent) {
                post(new SubscriberExceptionEvent(this, th, obj, subscription.subscriber));
            }
        } else {
            throw new EventBusException("Invoking subscriber failed", th);
        }
    }

    private List<Class<?>> lookupAllEventTypes(Class<?> cls) {
        List<Class<?>> list;
        synchronized (eventTypesCache) {
            list = eventTypesCache.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    addInterfaces(list, cls2.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    private void postSingleEvent(Object obj, PostingThreadState postingThreadState) throws Error {
        boolean z;
        Class<?> cls = obj.getClass();
        if (this.eventInheritance) {
            List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
            int size = lookupAllEventTypes.size();
            boolean z2 = false;
            for (int i = 0; i < size; i++) {
                z2 |= postSingleEventForEventType(obj, postingThreadState, lookupAllEventTypes.get(i));
            }
            z = z2;
        } else {
            z = postSingleEventForEventType(obj, postingThreadState, cls);
        }
        if (!z) {
            if (this.logNoSubscriberMessages) {
                Log.d(TAG, "No subscribers registered for event " + cls);
            }
            if (this.sendNoSubscriberEvent && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                post(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean postSingleEventForEventType(Object obj, PostingThreadState postingThreadState, Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            postingThreadState.event = obj;
            postingThreadState.subscription = subscription;
            try {
                postToSubscription(subscription, obj, postingThreadState.isMainThread);
                if (postingThreadState.canceled) {
                    break;
                }
            } finally {
                postingThreadState.event = null;
                postingThreadState.subscription = null;
                postingThreadState.canceled = false;
            }
        }
        return true;
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        switch (subscription.subscriberMethod.threadMode) {
            case PostThread:
                invokeSubscriber(subscription, obj);
                return;
            case MainThread:
                if (z) {
                    invokeSubscriber(subscription, obj);
                    return;
                } else {
                    this.mainThreadPoster.enqueue(subscription, obj);
                    return;
                }
            case BackgroundThread:
                if (z) {
                    this.backgroundPoster.enqueue(subscription, obj);
                    return;
                } else {
                    invokeSubscriber(subscription, obj);
                    return;
                }
            case Async:
                this.asyncPoster.enqueue(subscription, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
        }
    }

    private void register(Object obj, boolean z, int i) {
        synchronized (this) {
            for (SubscriberMethod subscribe : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass())) {
                subscribe(obj, subscribe, z, i);
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod, boolean z, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Object obj2;
        Class<?> cls = subscriberMethod.eventType;
        CopyOnWriteArrayList copyOnWriteArrayList2 = this.subscriptionsByEventType.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod, i);
        if (copyOnWriteArrayList2 == null) {
            CopyOnWriteArrayList copyOnWriteArrayList3 = new CopyOnWriteArrayList();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList3);
            copyOnWriteArrayList = copyOnWriteArrayList3;
        } else if (!copyOnWriteArrayList2.contains(subscription)) {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            } else if (i2 == size || subscription.priority > ((Subscription) copyOnWriteArrayList.get(i2)).priority) {
                copyOnWriteArrayList.add(i2, subscription);
            } else {
                i2++;
            }
        }
        copyOnWriteArrayList.add(i2, subscription);
        List list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (z) {
            synchronized (this.stickyEvents) {
                obj2 = this.stickyEvents.get(cls);
            }
            if (obj2 != null) {
                postToSubscription(subscription, obj2, Looper.getMainLooper() == Looper.myLooper());
            }
        }
    }

    private void unubscribeByEventType(Object obj, Class<?> cls) {
        int i;
        List list = this.subscriptionsByEventType.get(cls);
        if (list != null) {
            int size = list.size();
            int i2 = 0;
            while (i2 < size) {
                Subscription subscription = (Subscription) list.get(i2);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    list.remove(i2);
                    i = i2 - 1;
                    size--;
                } else {
                    i = i2;
                }
                i2 = i + 1;
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        if (!postingThreadState.isPosting) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new EventBusException("Event may not be null");
        } else if (postingThreadState.event != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        } else if (postingThreadState.subscription.subscriberMethod.threadMode == ThreadMode.PostThread) {
            postingThreadState.canceled = true;
        } else {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
    }

    /* access modifiers changed from: package-private */
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public <T> T getStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.get(cls));
        }
        return cast;
    }

    public boolean hasSubscriberForEvent(Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
        if (lookupAllEventTypes != null) {
            int size = lookupAllEventTypes.size();
            for (int i = 0; i < size; i++) {
                Class cls2 = lookupAllEventTypes.get(i);
                synchronized (this) {
                    copyOnWriteArrayList = this.subscriptionsByEventType.get(cls2);
                }
                if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, new Object[]{obj});
        } catch (InvocationTargetException e) {
            handleSubscriberException(subscription, obj, e.getCause());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    public boolean isRegistered(Object obj) {
        boolean containsKey;
        synchronized (this) {
            containsKey = this.typesBySubscriber.containsKey(obj);
        }
        return containsKey;
    }

    public void post(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        List<Object> list = postingThreadState.eventQueue;
        list.add(obj);
        if (!postingThreadState.isPosting) {
            postingThreadState.isMainThread = Looper.getMainLooper() == Looper.myLooper();
            postingThreadState.isPosting = true;
            if (!postingThreadState.canceled) {
                while (!list.isEmpty()) {
                    try {
                        postSingleEvent(list.remove(0), postingThreadState);
                    } finally {
                        postingThreadState.isPosting = false;
                        postingThreadState.isMainThread = false;
                    }
                }
                return;
            }
            throw new EventBusException("Internal error. Abort state was not reset");
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public void register(Object obj) {
        register(obj, false, 0);
    }

    public void register(Object obj, int i) {
        register(obj, false, i);
    }

    public void registerSticky(Object obj) {
        register(obj, true, 0);
    }

    public void registerSticky(Object obj, int i) {
        register(obj, true, i);
    }

    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.remove(cls));
        }
        return cast;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.stickyEvents.get(cls))) {
                return false;
            }
            this.stickyEvents.remove(cls);
            return true;
        }
    }

    public void unregister(Object obj) {
        synchronized (this) {
            List<Class> list = this.typesBySubscriber.get(obj);
            if (list != null) {
                for (Class unubscribeByEventType : list) {
                    unubscribeByEventType(obj, unubscribeByEventType);
                }
                this.typesBySubscriber.remove(obj);
            } else {
                String str = TAG;
                Log.w(str, "Subscriber to unregister was not registered before: " + obj.getClass());
            }
        }
    }
}
