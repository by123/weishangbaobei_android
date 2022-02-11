package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;

final class ae implements Runnable {
    final /* synthetic */ Context a;

    ae(Context context) {
        this.a = context;
    }

    public final void run() {
        try {
            new Thread(new ap(this.a, (Map<String, Integer>) null, (StatSpecifyReportedInfo) null), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.a, th);
        }
    }
}
