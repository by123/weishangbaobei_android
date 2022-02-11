package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.RealCall;
import okhttp3.internal.Util;

public final class Dispatcher {
    @Nullable
    private ExecutorService executorService;
    @Nullable
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public Dispatcher() {
    }

    public Dispatcher(ExecutorService executorService2) {
        this.executorService = executorService2;
    }

    private <T> void finished(Deque<T> deque, T t, boolean z) {
        int runningCallsCount;
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    promoteCalls();
                }
                runningCallsCount = runningCallsCount();
                runnable = this.idleCallback;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (runningCallsCount == 0 && runnable != null) {
            runnable.run();
        }
    }

    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequests && !this.readyAsyncCalls.isEmpty()) {
            Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
            while (it.hasNext()) {
                RealCall.AsyncCall next = it.next();
                if (runningCallsForHost(next) < this.maxRequestsPerHost) {
                    it.remove();
                    this.runningAsyncCalls.add(next);
                    executorService().execute(next);
                }
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(RealCall.AsyncCall asyncCall) {
        int i = 0;
        for (RealCall.AsyncCall next : this.runningAsyncCalls) {
            if (!next.get().forWebSocket && next.host().equals(asyncCall.host())) {
                i++;
            }
        }
        return i;
    }

    public void cancelAll() {
        synchronized (this) {
            for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
                asyncCall.get().cancel();
            }
            for (RealCall.AsyncCall asyncCall2 : this.runningAsyncCalls) {
                asyncCall2.get().cancel();
            }
            for (RealCall cancel : this.runningSyncCalls) {
                cancel.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enqueue(RealCall.AsyncCall asyncCall) {
        synchronized (this) {
            if (this.runningAsyncCalls.size() >= this.maxRequests || runningCallsForHost(asyncCall) >= this.maxRequestsPerHost) {
                this.readyAsyncCalls.add(asyncCall);
            } else {
                this.runningAsyncCalls.add(asyncCall);
                executorService().execute(asyncCall);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void executed(RealCall realCall) {
        synchronized (this) {
            this.runningSyncCalls.add(realCall);
        }
    }

    public ExecutorService executorService() {
        ExecutorService executorService2;
        synchronized (this) {
            if (this.executorService == null) {
                this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
            }
            executorService2 = this.executorService;
        }
        return executorService2;
    }

    /* access modifiers changed from: package-private */
    public void finished(RealCall.AsyncCall asyncCall) {
        finished(this.runningAsyncCalls, asyncCall, true);
    }

    /* access modifiers changed from: package-private */
    public void finished(RealCall realCall) {
        finished(this.runningSyncCalls, realCall, false);
    }

    public int getMaxRequests() {
        int i;
        synchronized (this) {
            i = this.maxRequests;
        }
        return i;
    }

    public int getMaxRequestsPerHost() {
        int i;
        synchronized (this) {
            i = this.maxRequestsPerHost;
        }
        return i;
    }

    public List<Call> queuedCalls() {
        List<Call> unmodifiableList;
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
                arrayList.add(asyncCall.get());
            }
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    public int queuedCallsCount() {
        int size;
        synchronized (this) {
            size = this.readyAsyncCalls.size();
        }
        return size;
    }

    public List<Call> runningCalls() {
        List<Call> unmodifiableList;
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.runningSyncCalls);
            for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
                arrayList.add(asyncCall.get());
            }
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    public int runningCallsCount() {
        int size;
        int size2;
        synchronized (this) {
            size = this.runningAsyncCalls.size();
            size2 = this.runningSyncCalls.size();
        }
        return size + size2;
    }

    public void setIdleCallback(@Nullable Runnable runnable) {
        synchronized (this) {
            this.idleCallback = runnable;
        }
    }

    public void setMaxRequests(int i) {
        synchronized (this) {
            if (i >= 1) {
                this.maxRequests = i;
                promoteCalls();
            } else {
                throw new IllegalArgumentException("max < 1: " + i);
            }
        }
    }

    public void setMaxRequestsPerHost(int i) {
        synchronized (this) {
            if (i >= 1) {
                this.maxRequestsPerHost = i;
                promoteCalls();
            } else {
                throw new IllegalArgumentException("max < 1: " + i);
            }
        }
    }
}
