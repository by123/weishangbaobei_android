package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ab;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.m;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.a;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

/* compiled from: NetWorkManager */
public class c {
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final String o = "thtstart";
    private static final String p = "gkvc";
    private static final String q = "ekvc";
    String a = null;
    private final int e = 1;
    private com.umeng.commonsdk.statistics.internal.c f;
    private ImprintHandler g;
    private e h;
    private ImprintHandler.a i = null;
    private ABTest j = null;
    /* access modifiers changed from: private */
    public Defcon k = null;
    private long l = 0;
    private int m = 0;
    private int n = 0;
    /* access modifiers changed from: private */
    public Context r;
    private ReportPolicy.ReportStrategy s = null;

    public c(Context context) {
        this.r = context;
        this.i = ImprintHandler.getImprintService(this.r).c();
        this.k = Defcon.getService(this.r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.r);
        this.l = sharedPreferences.getLong(o, 0);
        this.m = sharedPreferences.getInt(p, 0);
        this.n = sharedPreferences.getInt(q, 0);
        this.a = UMEnvelopeBuild.imprintProperty(this.r, "track_list", (String) null);
        this.g = ImprintHandler.getImprintService(this.r);
        this.g.a((d) new d() {
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onImprintChanged(com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a r8) {
                /*
                    r7 = this;
                    com.umeng.commonsdk.statistics.c r0 = com.umeng.commonsdk.statistics.c.this
                    com.umeng.commonsdk.statistics.noise.Defcon r0 = r0.k
                    r0.onImprintChanged(r8)
                    com.umeng.commonsdk.statistics.c r8 = com.umeng.commonsdk.statistics.c.this
                    com.umeng.commonsdk.statistics.c r0 = com.umeng.commonsdk.statistics.c.this
                    android.content.Context r0 = r0.r
                    java.lang.String r1 = "track_list"
                    r2 = 0
                    java.lang.String r0 = com.umeng.commonsdk.framework.UMEnvelopeBuild.imprintProperty(r0, r1, r2)
                    r8.a = r0
                    com.umeng.commonsdk.statistics.c r8 = com.umeng.commonsdk.statistics.c.this     // Catch:{  }
                    android.content.Context r8 = r8.r     // Catch:{  }
                    java.lang.String r0 = "umtt"
                    java.lang.String r8 = com.umeng.commonsdk.framework.a.a((android.content.Context) r8, (java.lang.String) r0, (java.lang.String) r2)     // Catch:{  }
                    boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{  }
                    if (r0 != 0) goto L_0x0056
                    java.lang.String r0 = "com.umeng.commonsdk.internal.utils.SDStorageAgent"
                    java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Throwable -> 0x0056 }
                    if (r0 == 0) goto L_0x0056
                    java.lang.String r1 = "updateUMTT"
                    r2 = 2
                    java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x0056 }
                    java.lang.Class<android.content.Context> r4 = android.content.Context.class
                    r5 = 0
                    r3[r5] = r4     // Catch:{ Throwable -> 0x0056 }
                    java.lang.Class<java.lang.String> r4 = java.lang.String.class
                    r6 = 1
                    r3[r6] = r4     // Catch:{ Throwable -> 0x0056 }
                    java.lang.reflect.Method r1 = r0.getMethod(r1, r3)     // Catch:{ Throwable -> 0x0056 }
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0056 }
                    com.umeng.commonsdk.statistics.c r3 = com.umeng.commonsdk.statistics.c.this     // Catch:{ Throwable -> 0x0056 }
                    android.content.Context r3 = r3.r     // Catch:{ Throwable -> 0x0056 }
                    r2[r5] = r3     // Catch:{ Throwable -> 0x0056 }
                    r2[r6] = r8     // Catch:{ Throwable -> 0x0056 }
                    r1.invoke(r0, r2)     // Catch:{ Throwable -> 0x0056 }
                L_0x0056:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.c.AnonymousClass1.onImprintChanged(com.umeng.commonsdk.statistics.idtracking.ImprintHandler$a):void");
            }
        });
        this.h = e.a(this.r);
        this.f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f.a(StatTracer.getInstance(this.r));
    }

    public boolean a(File file) {
        int i2;
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            a.a(this.r).c(file.getName());
            byte[] a2 = this.f.a(byteArray, a.a(this.r).a(file.getName()));
            if (a2 == null) {
                i2 = 1;
            } else {
                i2 = a(a2);
            }
            switch (i2) {
                case 1:
                    break;
                case 2:
                    this.h.d();
                    StatTracer.getInstance(this.r).saveSate();
                    break;
                case 3:
                    StatTracer.getInstance(this.r).saveSate();
                    break;
            }
            if (i2 == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
            return false;
        }
    }

    private int a(byte[] bArr) {
        Response response = new Response();
        try {
            new m(new ab.a()).a((j) response, bArr);
            if (response.resp_code == 1) {
                this.g.b(response.getImprint());
                this.g.d();
            }
            MLog.i("send log:" + response.getMsg());
            UMRTLog.i(UMRTLog.RTLOG_TAG, "send log: " + response.getMsg());
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
        }
        return response.resp_code == 1 ? 2 : 3;
    }
}
