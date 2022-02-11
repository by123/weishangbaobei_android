package com.tencent.wxop.stat;

import android.content.Context;
import com.stub.StubApp;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.l;
import com.umeng.socialize.common.SocializeConstants;
import java.util.Timer;
import java.util.TimerTask;

public class d {
    private static volatile d b;
    private Timer a = null;
    /* access modifiers changed from: private */
    public Context c = null;

    private d(Context context) {
        this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.a = new Timer(false);
    }

    public static d a(Context context) {
        if (b == null) {
            synchronized (d.class) {
                try {
                    if (b == null) {
                        b = new d(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<d> cls = d.class;
                        throw th;
                    }
                }
            }
        }
        return b;
    }

    public void a() {
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.PERIOD) {
            long sendPeriodMinutes = (long) (StatConfig.getSendPeriodMinutes() * 60 * SocializeConstants.CANCLE_RESULTCODE);
            if (StatConfig.isDebugEnable()) {
                StatLogger b2 = l.b();
                b2.i("setupPeriodTimer delay:" + sendPeriodMinutes);
            }
            a(new e(this), sendPeriodMinutes);
        }
    }

    public void a(TimerTask timerTask, long j) {
        if (this.a != null) {
            if (StatConfig.isDebugEnable()) {
                StatLogger b2 = l.b();
                b2.i("setupPeriodTimer schedule delay:" + j);
            }
            this.a.schedule(timerTask, j);
        } else if (StatConfig.isDebugEnable()) {
            l.b().w("setupPeriodTimer schedule timer == null");
        }
    }
}
