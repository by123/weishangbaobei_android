package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final int MODIFIERS_IGNORE = 5192;
    private static final String ON_EVENT_METHOD_NAME = "onEvent";
    private static final int SYNTHETIC = 4096;
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

    SubscriberMethodFinder(List<Class<?>> list) {
        if (list != null) {
            for (Class next : list) {
                this.skipMethodVerificationForClasses.put(next, next);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls) {
        List<SubscriberMethod> list;
        ThreadMode threadMode;
        String name = cls.getName();
        synchronized (methodCache) {
            list = methodCache.get(name);
        }
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name2 = cls2.getName();
            if (name2.startsWith("java.") || name2.startsWith("javax.") || name2.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getDeclaredMethods()) {
                String name3 = method.getName();
                if (name3.startsWith(ON_EVENT_METHOD_NAME)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                        Class[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            String substring = name3.substring(ON_EVENT_METHOD_NAME.length());
                            if (substring.length() == 0) {
                                threadMode = ThreadMode.PostThread;
                            } else if (substring.equals("MainThread")) {
                                threadMode = ThreadMode.MainThread;
                            } else if (substring.equals("BackgroundThread")) {
                                threadMode = ThreadMode.BackgroundThread;
                            } else if (substring.equals("Async")) {
                                threadMode = ThreadMode.Async;
                            } else if (!this.skipMethodVerificationForClasses.containsKey(cls2)) {
                                throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                            }
                            Class cls3 = parameterTypes[0];
                            sb.setLength(0);
                            sb.append(name3);
                            sb.append('>');
                            sb.append(cls3.getName());
                            if (hashSet.add(sb.toString())) {
                                arrayList.add(new SubscriberMethod(method, threadMode, cls3));
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.skipMethodVerificationForClasses.containsKey(cls2)) {
                        Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + cls2 + "." + name3);
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            synchronized (methodCache) {
                methodCache.put(name, arrayList);
            }
            return arrayList;
        }
        throw new EventBusException("Subscriber " + cls + " has no public methods called " + ON_EVENT_METHOD_NAME);
    }

    static void clearCaches() {
        synchronized (methodCache) {
            methodCache.clear();
        }
    }
}
