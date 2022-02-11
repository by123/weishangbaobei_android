package io.reactivex.processors;

import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport("none")
public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
    static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
    final int bufferSize;
    int consumed;
    volatile boolean done;
    volatile Throwable error;
    int fusionMode;
    final int limit;
    final AtomicBoolean once;
    volatile SimpleQueue<T> queue;
    final boolean refcount;
    final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicInteger wip = new AtomicInteger();

    MulticastProcessor(int i, boolean z) {
        ObjectHelper.verifyPositive(i, "bufferSize");
        this.bufferSize = i;
        this.limit = i - (i >> 2);
        this.refcount = z;
        this.once = new AtomicBoolean();
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create() {
        return new MulticastProcessor<>(bufferSize(), false);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(int i) {
        return new MulticastProcessor<>(i, false);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(int i, boolean z) {
        return new MulticastProcessor<>(i, z);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(boolean z) {
        return new MulticastProcessor<>(bufferSize(), z);
    }

    /* access modifiers changed from: package-private */
    public boolean add(MulticastSubscription<T> multicastSubscription) {
        MulticastSubscription[] multicastSubscriptionArr;
        MulticastSubscription[] multicastSubscriptionArr2;
        do {
            multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
            if (multicastSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = multicastSubscriptionArr.length;
            multicastSubscriptionArr2 = new MulticastSubscription[(length + 1)];
            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
            multicastSubscriptionArr2[length] = multicastSubscription;
        } while (!this.subscribers.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: CFG modification limit reached, blocks count: 190 */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0087, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e7, code lost:
        if (r8 != 0) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e9, code lost:
        r3 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r10.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f1, code lost:
        if (r3 != TERMINATED) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f3, code lost:
        r13.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0104, code lost:
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0107, code lost:
        if (r2 != r3) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010d, code lost:
        if (r18.done == false) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0113, code lost:
        if (r13.isEmpty() == false) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0115, code lost:
        r4 = r18.error;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0119, code lost:
        if (r4 == null) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011b, code lost:
        r2 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r10.getAndSet(TERMINATED);
        r5 = r2.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0125, code lost:
        if (r3 >= r5) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0127, code lost:
        r2[r3].onError(r4);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x012f, code lost:
        r2 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r10.getAndSet(TERMINATED);
        r4 = r2.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0139, code lost:
        if (r3 >= r4) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013b, code lost:
        r2[r3].onComplete();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0143, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0152, code lost:
        r8 = r6;
     */
    public void drain() {
        Object obj;
        if (this.wip.getAndIncrement() == 0) {
            AtomicReference<MulticastSubscription<T>[]> atomicReference = this.subscribers;
            int i = this.consumed;
            int i2 = this.limit;
            int i3 = this.fusionMode;
            int i4 = 1;
            loop0:
            while (true) {
                SimpleQueue<T> simpleQueue = this.queue;
                if (simpleQueue != null) {
                    MulticastSubscription[] multicastSubscriptionArr = (MulticastSubscription[]) atomicReference.get();
                    if (multicastSubscriptionArr.length != 0) {
                        long j = -1;
                        for (MulticastSubscription multicastSubscription : multicastSubscriptionArr) {
                            long j2 = multicastSubscription.get();
                            if (j2 >= 0) {
                                j = j == -1 ? j2 - multicastSubscription.emitted : Math.min(j, j2 - multicastSubscription.emitted);
                            }
                        }
                        long j3 = j;
                        int i5 = i;
                        while (true) {
                            if (j3 <= 0) {
                                break;
                            }
                            MulticastSubscription[] multicastSubscriptionArr2 = (MulticastSubscription[]) atomicReference.get();
                            if (multicastSubscriptionArr2 == TERMINATED) {
                                simpleQueue.clear();
                                break loop0;
                            } else if (multicastSubscriptionArr != multicastSubscriptionArr2) {
                                break;
                            } else {
                                boolean z = this.done;
                                try {
                                    obj = simpleQueue.poll();
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    SubscriptionHelper.cancel(this.upstream);
                                    this.error = th;
                                    this.done = true;
                                    z = true;
                                    obj = null;
                                }
                                boolean z2 = obj == null;
                                if (!z || !z2) {
                                    if (!z2) {
                                        for (MulticastSubscription onNext : multicastSubscriptionArr) {
                                            onNext.onNext(obj);
                                        }
                                        long j4 = j3 - 1;
                                        if (i3 == 1) {
                                            break;
                                        }
                                        i5++;
                                        if (i5 != i2) {
                                            break;
                                        }
                                        this.upstream.get().request((long) i2);
                                        i5 = 0;
                                        j3 = j4;
                                    } else {
                                        break;
                                    }
                                } else {
                                    Throwable th2 = this.error;
                                    if (th2 != null) {
                                        for (MulticastSubscription onError : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onError.onError(th2);
                                        }
                                    } else {
                                        for (MulticastSubscription onComplete : (MulticastSubscription[]) atomicReference.getAndSet(TERMINATED)) {
                                            onComplete.onComplete();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                int addAndGet = this.wip.addAndGet(-i4);
                if (addAndGet == 0) {
                    break;
                }
                i4 = addAndGet;
            }
        }
    }

    public Throwable getThrowable() {
        if (this.once.get()) {
            return this.error;
        }
        return null;
    }

    public boolean hasComplete() {
        return this.once.get() && this.error == null;
    }

    public boolean hasSubscribers() {
        return ((MulticastSubscription[]) this.subscribers.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.once.get() && this.error != null;
    }

    public boolean offer(T t) {
        if (this.once.get()) {
            return false;
        }
        ObjectHelper.requireNonNull(t, "offer called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.fusionMode != 0 || !this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
    }

    public void onComplete() {
        if (this.once.compareAndSet(false, true)) {
            this.done = true;
            drain();
        }
    }

    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onNext(T t) {
        if (!this.once.get()) {
            if (this.fusionMode == 0) {
                ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                if (!this.queue.offer(t)) {
                    SubscriptionHelper.cancel(this.upstream);
                    onError(new MissingBackpressureException());
                    return;
                }
            }
            drain();
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            if (subscription instanceof QueueSubscription) {
                QueueSubscription queueSubscription = (QueueSubscription) subscription;
                int requestFusion = queueSubscription.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = queueSubscription;
                    this.done = true;
                    drain();
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = queueSubscription;
                    subscription.request((long) this.bufferSize);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.bufferSize);
            subscription.request((long) this.bufferSize);
        }
    }

    /* access modifiers changed from: package-private */
    public void remove(MulticastSubscription<T> multicastSubscription) {
        while (true) {
            MulticastSubscription<T>[] multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
            int length = multicastSubscriptionArr.length;
            if (length != 0) {
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    } else if (multicastSubscriptionArr[i] == multicastSubscription) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length != 1) {
                    MulticastSubscription[] multicastSubscriptionArr2 = new MulticastSubscription[(length - 1)];
                    System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, i);
                    System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr2, i, (length - i) - 1);
                    if (this.subscribers.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2)) {
                        return;
                    }
                } else if (this.refcount) {
                    if (this.subscribers.compareAndSet(multicastSubscriptionArr, TERMINATED)) {
                        SubscriptionHelper.cancel(this.upstream);
                        this.once.set(true);
                        return;
                    }
                } else if (this.subscribers.compareAndSet(multicastSubscriptionArr, EMPTY)) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void start() {
        if (SubscriptionHelper.setOnce(this.upstream, EmptySubscription.INSTANCE)) {
            this.queue = new SpscArrayQueue(this.bufferSize);
        }
    }

    public void startUnbounded() {
        if (SubscriptionHelper.setOnce(this.upstream, EmptySubscription.INSTANCE)) {
            this.queue = new SpscLinkedArrayQueue(this.bufferSize);
        }
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        Throwable th;
        MulticastSubscription multicastSubscription = new MulticastSubscription(subscriber, this);
        subscriber.onSubscribe(multicastSubscription);
        if (add(multicastSubscription)) {
            if (multicastSubscription.get() == Long.MIN_VALUE) {
                remove(multicastSubscription);
            } else {
                drain();
            }
        } else if ((this.once.get() || !this.refcount) && (th = this.error) != null) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
    }
}
