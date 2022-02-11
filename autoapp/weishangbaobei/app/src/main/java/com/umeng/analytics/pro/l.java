package com.umeng.analytics.pro;

import com.umeng.analytics.AnalyticsConfig;
import java.lang.Thread;

/* compiled from: CrashHandler */
public class l implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler a;
    private p b;

    public l() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void a(p pVar) {
        this.b = pVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        if (this.a != null && this.a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.a.uncaughtException(thread, th);
        }
    }

    private void a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.b.a(th);
        } else {
            this.b.a((Throwable) null);
        }
    }
}
