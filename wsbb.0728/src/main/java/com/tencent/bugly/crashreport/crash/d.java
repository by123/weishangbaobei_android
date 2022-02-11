package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.LinkedHashMap;
import java.util.Map;

public final class d {
    /* access modifiers changed from: private */
    public static d a;
    private a b;
    private com.tencent.bugly.crashreport.common.info.a c;
    private b d;
    private Context e;

    private d(Context context) {
        c a2 = c.a();
        if (a2 != null) {
            this.b = a.a();
            this.c = com.tencent.bugly.crashreport.common.info.a.a(context);
            this.d = a2.p;
            this.e = context;
            w.a().a(new Runnable() {
                public final void run() {
                    d.a(d.this);
                }
            });
        }
    }

    public static d a(Context context) {
        if (a == null) {
            a = new d(context);
        }
        return a;
    }

    static /* synthetic */ void a(d dVar) {
        x.c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            String str = "com.tencent.bugly";
            dVar.c.getClass();
            if (!"".equals("")) {
                str = "com.tencent.bugly" + "." + "";
            }
            z.a(cls, "sdkPackageName", (Object) str, (Object) null);
            x.c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable th) {
            x.a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x023e A[Catch:{ Throwable -> 0x0237, all -> 0x024a }] */
    static /* synthetic */ void a(d dVar, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        String str6;
        if (thread == null) {
            thread = Thread.currentThread();
        }
        switch (i) {
            case 4:
                str4 = "Unity";
                break;
            case 5:
            case 6:
                str4 = "Cocos";
                break;
            case 8:
                str4 = "H5";
                break;
            default:
                x.d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
                return;
        }
        x.e("[ExtraCrashManager] %s Crash Happen", str4);
        if (!dVar.b.b()) {
            x.d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
        }
        StrategyBean c2 = dVar.b.c();
        if (c2.g || !dVar.b.b()) {
            switch (i) {
                case 5:
                case 6:
                    if (!c2.l) {
                        x.e("[ExtraCrashManager] %s report is disabled.", str4);
                        x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                        return;
                    }
                case 8:
                    if (!c2.m) {
                        x.e("[ExtraCrashManager] %s report is disabled.", str4);
                        x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                        return;
                    }
                default:
                    if (i == 8) {
                        i = 5;
                    }
                    try {
                        CrashDetailBean crashDetailBean = new CrashDetailBean();
                        crashDetailBean.C = b.k();
                        crashDetailBean.D = b.i();
                        crashDetailBean.E = b.m();
                        crashDetailBean.F = dVar.c.p();
                        crashDetailBean.G = dVar.c.o();
                        crashDetailBean.H = dVar.c.q();
                        crashDetailBean.w = z.a(dVar.e, c.e, (String) null);
                        crashDetailBean.b = i;
                        crashDetailBean.e = dVar.c.h();
                        crashDetailBean.f = dVar.c.k;
                        crashDetailBean.g = dVar.c.w();
                        crashDetailBean.m = dVar.c.g();
                        crashDetailBean.n = str;
                        crashDetailBean.o = str2;
                        String str7 = "";
                        if (str3 != null) {
                            String[] split = str3.split("\n");
                            if (split.length > 0) {
                                str7 = split[0];
                            }
                            str6 = str3;
                        } else {
                            str6 = "";
                        }
                        crashDetailBean.p = str7;
                        crashDetailBean.q = str6;
                        crashDetailBean.r = System.currentTimeMillis();
                        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
                        crashDetailBean.z = z.a(c.f, false);
                        crashDetailBean.A = dVar.c.d;
                        crashDetailBean.B = thread.getName() + "(" + thread.getId() + ")";
                        crashDetailBean.I = dVar.c.y();
                        crashDetailBean.h = dVar.c.v();
                        crashDetailBean.M = dVar.c.a;
                        crashDetailBean.N = dVar.c.a();
                        if (!c.a().n()) {
                            dVar.d.d(crashDetailBean);
                        }
                        crashDetailBean.Q = dVar.c.H();
                        crashDetailBean.R = dVar.c.I();
                        crashDetailBean.S = dVar.c.B();
                        crashDetailBean.T = dVar.c.G();
                        crashDetailBean.y = y.a();
                        if (crashDetailBean.O == null) {
                            crashDetailBean.O = new LinkedHashMap();
                        }
                        if (map != null) {
                            crashDetailBean.O.putAll(map);
                        }
                        String a2 = z.a();
                        String str8 = dVar.c.d;
                        String name = thread.getName();
                        b.a(str4, a2, str8, name, str + "\n" + str2 + "\n" + str3, crashDetailBean);
                        if (!dVar.d.a(crashDetailBean)) {
                            dVar.d.a(crashDetailBean, 3000, false);
                        }
                        return;
                    } catch (Throwable th) {
                        if (!x.a(th)) {
                        }
                        return;
                    } finally {
                        str5 = "[ExtraCrashManager] Successfully handled.";
                        x.e(str5, new Object[0]);
                    }
            }
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return;
        }
        x.e("[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
        String a3 = z.a();
        String str9 = dVar.c.d;
        String name2 = thread.getName();
        b.a(str4, a3, str9, name2, str + "\n" + str2 + "\n" + str3, (CrashDetailBean) null);
        x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
    }

    public static void a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        w.a().a(new Runnable() {
            public final void run() {
                try {
                    if (d.a == null) {
                        x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        d.a(d.a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!x.b(th)) {
                        th.printStackTrace();
                    }
                    x.e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
