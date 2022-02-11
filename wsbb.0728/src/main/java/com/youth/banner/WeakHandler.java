package com.youth.banner;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WeakHandler {
    private final Handler.Callback mCallback;
    private final ExecHandler mExec;
    private Lock mLock;
    @VisibleForTesting
    final ChainedRef mRunnables;

    static class ChainedRef {
        @NonNull
        Lock lock;
        @Nullable
        ChainedRef next;
        @Nullable
        ChainedRef prev;
        @NonNull
        final Runnable runnable;
        @NonNull
        final WeakRunnable wrapper;

        public ChainedRef(@NonNull Lock lock2, @NonNull Runnable runnable2) {
            this.runnable = runnable2;
            this.lock = lock2;
            this.wrapper = new WeakRunnable(new WeakReference(runnable2), new WeakReference(this));
        }

        public void insertAfter(@NonNull ChainedRef chainedRef) {
            this.lock.lock();
            try {
                if (this.next != null) {
                    this.next.prev = chainedRef;
                }
                chainedRef.next = this.next;
                this.next = chainedRef;
                chainedRef.prev = this;
            } finally {
                this.lock.unlock();
            }
        }

        /* JADX INFO: finally extract failed */
        public WeakRunnable remove() {
            this.lock.lock();
            try {
                if (this.prev != null) {
                    this.prev.next = this.next;
                }
                if (this.next != null) {
                    this.next.prev = this.prev;
                }
                this.prev = null;
                this.next = null;
                this.lock.unlock();
                return this.wrapper;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }

        @Nullable
        public WeakRunnable remove(Runnable runnable2) {
            this.lock.lock();
            try {
                for (ChainedRef chainedRef = this.next; chainedRef != null; chainedRef = chainedRef.next) {
                    if (chainedRef.runnable == runnable2) {
                        return chainedRef.remove();
                    }
                }
                this.lock.unlock();
                return null;
            } finally {
                this.lock.unlock();
            }
        }
    }

    private static class ExecHandler extends Handler {
        private final WeakReference<Handler.Callback> mCallback;

        ExecHandler() {
            this.mCallback = null;
        }

        ExecHandler(Looper looper) {
            super(looper);
            this.mCallback = null;
        }

        ExecHandler(Looper looper, WeakReference<Handler.Callback> weakReference) {
            super(looper);
            this.mCallback = weakReference;
        }

        ExecHandler(WeakReference<Handler.Callback> weakReference) {
            this.mCallback = weakReference;
        }

        public void handleMessage(@NonNull Message message) {
            Handler.Callback callback;
            if (this.mCallback != null && (callback = (Handler.Callback) this.mCallback.get()) != null) {
                callback.handleMessage(message);
            }
        }
    }

    static class WeakRunnable implements Runnable {
        private final WeakReference<Runnable> mDelegate;
        private final WeakReference<ChainedRef> mReference;

        WeakRunnable(WeakReference<Runnable> weakReference, WeakReference<ChainedRef> weakReference2) {
            this.mDelegate = weakReference;
            this.mReference = weakReference2;
        }

        public void run() {
            Runnable runnable = (Runnable) this.mDelegate.get();
            ChainedRef chainedRef = (ChainedRef) this.mReference.get();
            if (chainedRef != null) {
                chainedRef.remove();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public WeakHandler() {
        this.mLock = new ReentrantLock();
        this.mRunnables = new ChainedRef(this.mLock, (Runnable) null);
        this.mCallback = null;
        this.mExec = new ExecHandler();
    }

    public WeakHandler(@Nullable Handler.Callback callback) {
        this.mLock = new ReentrantLock();
        this.mRunnables = new ChainedRef(this.mLock, (Runnable) null);
        this.mCallback = callback;
        this.mExec = new ExecHandler((WeakReference<Handler.Callback>) new WeakReference(callback));
    }

    public WeakHandler(@NonNull Looper looper) {
        this.mLock = new ReentrantLock();
        this.mRunnables = new ChainedRef(this.mLock, (Runnable) null);
        this.mCallback = null;
        this.mExec = new ExecHandler(looper);
    }

    public WeakHandler(@NonNull Looper looper, @NonNull Handler.Callback callback) {
        this.mLock = new ReentrantLock();
        this.mRunnables = new ChainedRef(this.mLock, (Runnable) null);
        this.mCallback = callback;
        this.mExec = new ExecHandler(looper, new WeakReference(callback));
    }

    private WeakRunnable wrapRunnable(@NonNull Runnable runnable) {
        if (runnable != null) {
            ChainedRef chainedRef = new ChainedRef(this.mLock, runnable);
            this.mRunnables.insertAfter(chainedRef);
            return chainedRef.wrapper;
        }
        throw new NullPointerException("Runnable can't be null");
    }

    public final Looper getLooper() {
        return this.mExec.getLooper();
    }

    public final boolean hasMessages(int i) {
        return this.mExec.hasMessages(i);
    }

    public final boolean hasMessages(int i, Object obj) {
        return this.mExec.hasMessages(i, obj);
    }

    public final boolean post(@NonNull Runnable runnable) {
        return this.mExec.post(wrapRunnable(runnable));
    }

    public final boolean postAtFrontOfQueue(Runnable runnable) {
        return this.mExec.postAtFrontOfQueue(wrapRunnable(runnable));
    }

    public final boolean postAtTime(@NonNull Runnable runnable, long j) {
        return this.mExec.postAtTime(wrapRunnable(runnable), j);
    }

    public final boolean postAtTime(Runnable runnable, Object obj, long j) {
        return this.mExec.postAtTime(wrapRunnable(runnable), obj, j);
    }

    public final boolean postDelayed(Runnable runnable, long j) {
        return this.mExec.postDelayed(wrapRunnable(runnable), j);
    }

    public final void removeCallbacks(Runnable runnable) {
        WeakRunnable remove = this.mRunnables.remove(runnable);
        if (remove != null) {
            this.mExec.removeCallbacks(remove);
        }
    }

    public final void removeCallbacks(Runnable runnable, Object obj) {
        WeakRunnable remove = this.mRunnables.remove(runnable);
        if (remove != null) {
            this.mExec.removeCallbacks(remove, obj);
        }
    }

    public final void removeCallbacksAndMessages(Object obj) {
        this.mExec.removeCallbacksAndMessages(obj);
    }

    public final void removeMessages(int i) {
        this.mExec.removeMessages(i);
    }

    public final void removeMessages(int i, Object obj) {
        this.mExec.removeMessages(i, obj);
    }

    public final boolean sendEmptyMessage(int i) {
        return this.mExec.sendEmptyMessage(i);
    }

    public final boolean sendEmptyMessageAtTime(int i, long j) {
        return this.mExec.sendEmptyMessageAtTime(i, j);
    }

    public final boolean sendEmptyMessageDelayed(int i, long j) {
        return this.mExec.sendEmptyMessageDelayed(i, j);
    }

    public final boolean sendMessage(Message message) {
        return this.mExec.sendMessage(message);
    }

    public final boolean sendMessageAtFrontOfQueue(Message message) {
        return this.mExec.sendMessageAtFrontOfQueue(message);
    }

    public boolean sendMessageAtTime(Message message, long j) {
        return this.mExec.sendMessageAtTime(message, j);
    }

    public final boolean sendMessageDelayed(Message message, long j) {
        return this.mExec.sendMessageDelayed(message, j);
    }
}
