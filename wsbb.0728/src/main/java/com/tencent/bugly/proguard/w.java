package com.tencent.bugly.proguard;

import com.tencent.bugly.b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class w {
    /* access modifiers changed from: private */
    public static final AtomicInteger a = new AtomicInteger(1);
    private static w b;
    private ScheduledExecutorService c;

    protected w() {
        this.c = null;
        this.c = Executors.newScheduledThreadPool(3, new ThreadFactory(this) {
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + w.a.getAndIncrement());
                return thread;
            }
        });
        if (this.c == null || this.c.isShutdown()) {
            x.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    public static w a() {
        w wVar;
        synchronized (w.class) {
            try {
                if (b == null) {
                    b = new w();
                }
                wVar = b;
            } catch (Throwable th) {
                Class<w> cls = w.class;
                throw th;
            }
        }
        return wVar;
    }

    public final boolean a(Runnable runnable) {
        synchronized (this) {
            if (!c()) {
                x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
                return false;
            } else if (runnable == null) {
                x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
                return false;
            } else {
                x.c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
                try {
                    this.c.execute(runnable);
                    return true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                    return false;
                }
            }
        }
    }

    public final boolean a(Runnable runnable, long j) {
        synchronized (this) {
            if (!c()) {
                x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
                return false;
            } else if (runnable == null) {
                x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
                return false;
            } else {
                if (j <= 0) {
                    j = 0;
                }
                x.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.c.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    return true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                    return false;
                }
            }
        }
    }

    public final void b() {
        synchronized (this) {
            if (this.c != null && !this.c.isShutdown()) {
                x.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
                this.c.shutdownNow();
            }
        }
    }

    public final boolean c() {
        boolean z;
        synchronized (this) {
            z = this.c != null && !this.c.isShutdown();
        }
        return z;
    }
}
