package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.a;
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
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

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
            public void onImprintChanged(ImprintHandler.a aVar) {
                c.this.k.onImprintChanged(aVar);
                c.this.a = UMEnvelopeBuild.imprintProperty(c.this.r, "track_list", (String) null);
                try {
                    String a2 = a.a(c.this.r, com.umeng.commonsdk.proguard.e.e, (String) null);
                    if (!TextUtils.isEmpty(a2)) {
                        try {
                            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                            if (cls != null) {
                                cls.getMethod("updateUMTT", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{c.this.r, a2});
                            }
                        } catch (ClassNotFoundException e) {
                        }
                    }
                } catch (Throwable th) {
                }
            }
        });
        this.h = e.a(this.r);
        this.f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f.a(StatTracer.getInstance(this.r));
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

    public boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            com.umeng.commonsdk.statistics.internal.a.a(this.r).c(file.getName());
            byte[] a2 = this.f.a(byteArray, com.umeng.commonsdk.statistics.internal.a.a(this.r).a(file.getName()));
            int a3 = a2 == null ? 1 : a(a2);
            switch (a3) {
                case 2:
                    this.h.d();
                    StatTracer.getInstance(this.r).saveSate();
                    break;
                case 3:
                    StatTracer.getInstance(this.r).saveSate();
                    break;
            }
            return a3 == 2;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
            return false;
        }
    }
}
