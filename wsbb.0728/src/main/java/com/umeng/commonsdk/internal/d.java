package com.umeng.commonsdk.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.stub.StubApp;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.internal.utils.d;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.c;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("f", a.c());
            jSONObject.put(e.ar, a.d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static void a(Context context) {
        try {
            ULog.i("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(e.aw, a.d);
            JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, e(context));
            if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                a.f(context);
                j.d(context);
                c.c(context);
            }
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
    }

    private static void a(Context context, JSONObject jSONObject) {
        PackageManager packageManager;
        if (context != null && (packageManager = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager()) != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
            a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
            a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
            a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
            a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
        }
    }

    private static void a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null && !TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    jSONObject.put(str, 1);
                } catch (Exception e) {
                }
            } else {
                jSONObject.put(str, 0);
            }
        }
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray = new JSONArray();
                for (String put : Build.SUPPORTED_32_BIT_ABIS) {
                    jSONArray.put(put);
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                for (String put2 : Build.SUPPORTED_64_BIT_ABIS) {
                    jSONArray2.put(put2);
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                for (String put3 : Build.SUPPORTED_ABIS) {
                    jSONArray3.put(put3);
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", "unknown");
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static void b(Context context) {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, a.e, b.a(context).a(), (Object) null);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
        if (context != null) {
            String a = l.a(context);
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    if (jSONObject2.has(l.d)) {
                        jSONObject.put(l.d, jSONObject2.opt(l.d));
                    }
                    if (jSONObject2.has(l.c)) {
                        jSONObject.put(l.c, jSONObject2.opt(l.c));
                    }
                    if (jSONObject2.has(l.b)) {
                        jSONObject.put(l.b, jSONObject2.opt(l.b));
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tot_s", a.h());
            jSONObject.put("ava_s", a.i());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static void c(Context context) {
        if (context != null) {
            try {
                ULog.i("walle", "[internal] begin by not stateful--->>>");
                context = StubApp.getOrigApplicationContext(context.getApplicationContext());
                try {
                    f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(a.a.getBytes(), 0), 10);
                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                    JSONObject buildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                    if (buildSLBaseHeader != null && buildSLBaseHeader.has("header")) {
                        try {
                            JSONObject jSONObject = (JSONObject) buildSLBaseHeader.opt("header");
                            if (jSONObject != null) {
                                jSONObject.put(e.aw, a.d);
                            }
                        } catch (Exception e) {
                        }
                    }
                    ULog.i("walle", "[internal] header is " + buildSLBaseHeader.toString());
                    JSONObject d = d(context);
                    ULog.i("walle", "[internal] body is " + d.toString());
                    ULog.i("walle", uMSLEnvelopeBuild.buildSLEnvelope(context, buildSLBaseHeader, d, a.a).toString());
                    return;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            return;
        }
        UMCrashManager.reportCrash(context, th);
    }

    private static JSONObject d() {
        try {
            d.a a = com.umeng.commonsdk.internal.utils.d.a();
            if (a == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pro", a.a);
                jSONObject.put("pla", a.b);
                jSONObject.put("cpus", a.c);
                jSONObject.put("fea", a.d);
                jSONObject.put("imp", a.e);
                jSONObject.put("arc", a.f);
                jSONObject.put("var", a.g);
                jSONObject.put("par", a.h);
                jSONObject.put("rev", a.i);
                jSONObject.put("har", a.j);
                jSONObject.put("rev", a.k);
                jSONObject.put("ser", a.l);
                jSONObject.put("cur_cpu", com.umeng.commonsdk.internal.utils.d.d());
                jSONObject.put("max_cpu", com.umeng.commonsdk.internal.utils.d.b());
                jSONObject.put("min_cpu", com.umeng.commonsdk.internal.utils.d.c());
                jSONObject.put("ts", System.currentTimeMillis());
                return jSONObject;
            } catch (Exception e) {
                return jSONObject;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r3, r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    public static JSONObject d(Context context) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            try {
                JSONArray p = p(origApplicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("run_server", p);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(origApplicationContext, e);
            }
            try {
                String k = a.k(origApplicationContext);
                if (!TextUtils.isEmpty(k)) {
                    jSONObject2.put("imsi", k);
                }
                try {
                    String l = a.l(origApplicationContext);
                    if (!TextUtils.isEmpty(l)) {
                        jSONObject2.put("meid", l);
                    }
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(origApplicationContext, e2);
                }
                try {
                    jSONObject.put("internal", jSONObject2);
                } catch (JSONException e3) {
                    UMCrashManager.reportCrash(origApplicationContext, e3);
                }
            } catch (Exception e4) {
            }
        }
        return jSONObject;
    }

    public static JSONObject e(Context context) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            try {
                JSONArray p = p(origApplicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("rs", p);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(origApplicationContext, e);
            }
            try {
                JSONArray q = q(origApplicationContext);
                if (q != null && q.length() > 0) {
                    jSONObject2.put("bstn", q);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(origApplicationContext, e2);
            }
            try {
                JSONArray r = r(origApplicationContext);
                if (r != null && r.length() > 0) {
                    jSONObject2.put("by", r);
                }
            } catch (Exception e3) {
                UMCrashManager.reportCrash(origApplicationContext, e3);
            }
            try {
                a(origApplicationContext, jSONObject2);
            } catch (Exception e4) {
                UMCrashManager.reportCrash(origApplicationContext, e4);
            }
            try {
                b(origApplicationContext, jSONObject2);
            } catch (Exception e5) {
                UMCrashManager.reportCrash(origApplicationContext, e5);
            }
            try {
                JSONObject a = a();
                if (a != null && a.length() > 0) {
                    jSONObject2.put("sd", a);
                }
            } catch (Exception e6) {
                UMCrashManager.reportCrash(origApplicationContext, e6);
            }
            try {
                JSONObject b = b();
                if (b != null && b.length() > 0) {
                    jSONObject2.put("build", b);
                }
            } catch (Exception e7) {
                UMCrashManager.reportCrash(origApplicationContext, e7);
            }
            try {
                JSONObject jSONObject3 = new JSONObject();
                JSONArray g = g(origApplicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        jSONObject3.put("a_sr", g);
                    } catch (JSONException e8) {
                    }
                }
                JSONArray c = j.c(origApplicationContext);
                if (c != null && c.length() > 0) {
                    try {
                        jSONObject3.put("stat", c);
                    } catch (JSONException e9) {
                    }
                }
                jSONObject2.put("sr", jSONObject3);
            } catch (Exception e10) {
                UMCrashManager.reportCrash(origApplicationContext, e10);
            }
            try {
                JSONObject h = h(origApplicationContext);
                if (h != null && h.length() > 0) {
                    jSONObject2.put("scr", h);
                }
            } catch (Exception e11) {
                UMCrashManager.reportCrash(origApplicationContext, e11);
            }
            try {
                JSONObject i = i(origApplicationContext);
                if (i != null && i.length() > 0) {
                    jSONObject2.put("sinfo", i);
                }
            } catch (Exception e12) {
                UMCrashManager.reportCrash(origApplicationContext, e12);
            }
            try {
                JSONObject jSONObject4 = new JSONObject();
                JSONArray e13 = a.e(origApplicationContext);
                if (e13 != null && e13.length() > 0) {
                    try {
                        jSONObject4.put("wl", e13);
                    } catch (JSONException e14) {
                    }
                }
                JSONArray j = j(origApplicationContext);
                if (j != null && j.length() > 0) {
                    try {
                        jSONObject4.put("a_wls", j);
                    } catch (JSONException e15) {
                    }
                }
                jSONObject2.put("winfo", jSONObject4);
            } catch (Exception e16) {
                UMCrashManager.reportCrash(origApplicationContext, e16);
            }
            try {
                JSONArray k = k(origApplicationContext);
                if (k != null && k.length() > 0) {
                    jSONObject2.put("input", k);
                }
            } catch (Exception e17) {
                UMCrashManager.reportCrash(origApplicationContext, e17);
            }
            try {
                JSONObject o = a.o(origApplicationContext);
                if (o != null && o.length() > 0) {
                    jSONObject2.put("bt", o);
                }
            } catch (Exception e18) {
                UMCrashManager.reportCrash(origApplicationContext, e18);
            }
            try {
                JSONArray l = l(origApplicationContext);
                if (l != null && l.length() > 0) {
                    jSONObject2.put("cam", l);
                }
            } catch (Exception e19) {
                UMCrashManager.reportCrash(origApplicationContext, e19);
            }
            try {
                JSONArray m = m(origApplicationContext);
                if (m != null && m.length() > 0) {
                    jSONObject2.put("appls", m);
                }
            } catch (Exception e20) {
                UMCrashManager.reportCrash(origApplicationContext, e20);
            }
            try {
                JSONObject n = n(origApplicationContext);
                if (n != null && n.length() > 0) {
                    jSONObject2.put("mem", n);
                }
            } catch (Exception e21) {
                UMCrashManager.reportCrash(origApplicationContext, e21);
            }
            try {
                JSONArray o2 = o(origApplicationContext);
                if (o2 != null && o2.length() > 0) {
                    jSONObject2.put("lbs", o2);
                }
            } catch (Exception e22) {
                UMCrashManager.reportCrash(origApplicationContext, e22);
            }
            try {
                JSONObject d = d();
                if (d != null && d.length() > 0) {
                    jSONObject2.put(e.v, d);
                }
            } catch (Exception e23) {
            }
            try {
                JSONObject c2 = c();
                if (c2 != null && c2.length() > 0) {
                    jSONObject2.put("rom", c2);
                }
            } catch (Exception e24) {
            }
            try {
                jSONObject.put(e.ak, jSONObject2);
            } catch (JSONException e25) {
                UMCrashManager.reportCrash(origApplicationContext, e25);
            }
        }
        return jSONObject;
    }

    public static String f(Context context) {
        try {
            com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context);
            if (a != null) {
                a.a();
                String encodeToString = Base64.encodeToString(new s().a(a.b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    return encodeToString;
                }
            }
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
        return null;
    }

    public static JSONArray g(Context context) {
        if (context != null) {
            return k.g(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    public static JSONObject h(Context context) {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", a.h(context));
                jSONObject.put("a_nav_h", a.i(context));
                if (!(context.getResources() == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null)) {
                    jSONObject.put("a_den", (double) displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
            }
        }
        return jSONObject;
    }

    public static JSONObject i(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            String packageName = origApplicationContext.getPackageName();
            try {
                jSONObject.put("a_fit", a.a(origApplicationContext, packageName));
                jSONObject.put("a_alut", a.b(origApplicationContext, packageName));
                jSONObject.put("a_c", a.c(origApplicationContext, packageName));
                jSONObject.put("a_uid", a.d(origApplicationContext, packageName));
                if (a.a()) {
                    jSONObject.put("a_root", 1);
                } else {
                    jSONObject.put("a_root", 0);
                }
                jSONObject.put("tf", a.b());
                jSONObject.put("s_fs", (double) a.a(origApplicationContext));
                jSONObject.put("a_meid", a.l(origApplicationContext));
                jSONObject.put("a_imsi", a.k(origApplicationContext));
                jSONObject.put("st", a.f());
                String b = k.b(origApplicationContext);
                if (!TextUtils.isEmpty(b)) {
                    try {
                        jSONObject.put("a_iccid", b);
                    } catch (Exception e) {
                    }
                }
                String c = k.c(origApplicationContext);
                if (!TextUtils.isEmpty(c)) {
                    try {
                        jSONObject.put("a_simei", c);
                    } catch (Exception e2) {
                    }
                }
                jSONObject.put("hn", a.g());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e3) {
                UMCrashManager.reportCrash(origApplicationContext, e3);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = com.stub.StubApp.getOrigApplicationContext(r8.getApplicationContext());
     */
    public static JSONArray j(Context context) {
        Context origApplicationContext;
        List<ScanResult> b;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (b = a.b(origApplicationContext)) == null || b.size() <= 0)) {
            for (ScanResult next : b) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_bssid", next.BSSID);
                    jSONObject.put("a_ssid", next.SSID);
                    jSONObject.put("a_cap", next.capabilities);
                    jSONObject.put("a_fcy", next.frequency);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (Build.VERSION.SDK_INT >= 23) {
                        jSONObject.put("a_c0", next.centerFreq0);
                        jSONObject.put("a_c1", next.centerFreq1);
                        jSONObject.put("a_cw", next.channelWidth);
                        if (next.is80211mcResponder()) {
                            jSONObject.put("a_is80211", 1);
                        } else {
                            jSONObject.put("a_is80211", 0);
                        }
                        if (next.isPasspointNetwork()) {
                            jSONObject.put("a_isppn", 1);
                        } else {
                            jSONObject.put("a_isppn", 0);
                        }
                        jSONObject.put("a_ofn", next.operatorFriendlyName);
                        jSONObject.put("a_vn", next.venueName);
                    }
                    jSONObject.put("a_dc", next.describeContents());
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    UMCrashManager.reportCrash(origApplicationContext, e);
                }
            }
        }
        return jSONArray;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = com.stub.StubApp.getOrigApplicationContext(r8.getApplicationContext());
     */
    public static JSONArray k(Context context) {
        Context origApplicationContext;
        List<InputMethodInfo> m;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (m = a.m(origApplicationContext)) == null)) {
            for (InputMethodInfo next : m) {
                try {
                    CharSequence loadLabel = next.loadLabel(origApplicationContext.getPackageManager());
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_la", loadLabel);
                    jSONObject.put("a_pn", next.getPackageName());
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    UMCrashManager.reportCrash(origApplicationContext, e);
                }
            }
        }
        return jSONArray;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = com.stub.StubApp.getOrigApplicationContext(r8.getApplicationContext());
     */
    public static JSONArray l(Context context) {
        Context origApplicationContext;
        List<j.a> e;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (e = j.e(origApplicationContext)) == null || e.isEmpty())) {
            for (j.a next : e) {
                if (next != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_w", next.a);
                        jSONObject.put("a_h", next.b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e2) {
                        UMCrashManager.reportCrash(origApplicationContext, e2);
                    }
                }
            }
        }
        return jSONArray;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = com.stub.StubApp.getOrigApplicationContext(r8.getApplicationContext());
     */
    public static JSONArray m(Context context) {
        Context origApplicationContext;
        List<a.C0007a> p;
        JSONArray jSONArray = new JSONArray();
        if (!(context == null || (p = a.p(origApplicationContext)) == null || p.isEmpty())) {
            for (a.C0007a next : p) {
                if (next != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_pn", next.a);
                        jSONObject.put("a_la", next.b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e) {
                        UMCrashManager.reportCrash(origApplicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = com.stub.StubApp.getOrigApplicationContext(r6.getApplicationContext());
     */
    public static JSONObject n(Context context) {
        Context origApplicationContext;
        ActivityManager.MemoryInfo q;
        JSONObject jSONObject = new JSONObject();
        if (!(context == null || (q = a.q(origApplicationContext)) == null)) {
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    jSONObject.put(e.ar, q.totalMem);
                }
                jSONObject.put("f", q.availMem);
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                UMCrashManager.reportCrash(origApplicationContext, e);
            }
        }
        return jSONObject;
    }

    private static JSONArray o(Context context) {
        if (context != null) {
            return c.b(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    private static JSONArray p(Context context) {
        Throwable th;
        JSONArray jSONArray;
        List<ActivityManager.RunningServiceInfo> runningServices;
        JSONArray jSONArray2 = null;
        if (context != null) {
            try {
                ActivityManager activityManager = (ActivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("activity");
                if (!(activityManager == null || (runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)) == null || runningServices.isEmpty())) {
                    int i = 0;
                    while (i < runningServices.size()) {
                        try {
                            if (!(runningServices.get(i) == null || runningServices.get(i).service == null || runningServices.get(i).service.getClassName() == null || runningServices.get(i).service.getPackageName() == null)) {
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("sn", runningServices.get(i).service.getClassName().toString());
                                    jSONObject.put(b.ad, runningServices.get(i).service.getPackageName().toString());
                                    if (jSONArray2 == null) {
                                        jSONArray2 = new JSONArray();
                                    }
                                    jSONArray2.put(jSONObject);
                                } catch (JSONException e) {
                                }
                            }
                            i++;
                            jSONArray2 = jSONArray2;
                        } catch (Throwable th2) {
                            th = th2;
                            jSONArray = jSONArray2;
                            th = th;
                            UMCrashManager.reportCrash(context, th);
                            jSONArray2 = jSONArray;
                            return jSONArray2;
                        }
                    }
                    if (jSONArray2 != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("ts", System.currentTimeMillis());
                            jSONObject2.put("ls", jSONArray2);
                        } catch (JSONException e2) {
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            jSONObject3.put("sers", jSONObject2);
                        } catch (JSONException e3) {
                        }
                        jSONArray = new JSONArray();
                        try {
                            jSONArray.put(jSONObject3);
                            return jSONArray;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                jSONArray = null;
                th = th;
                UMCrashManager.reportCrash(context, th);
                jSONArray2 = jSONArray;
                return jSONArray2;
            }
        }
        return jSONArray2;
    }

    private static JSONArray q(Context context) {
        JSONArray jSONArray = new JSONArray();
        JSONObject d = k.d(context);
        if (d != null) {
            try {
                String e = k.e(context);
                if (!TextUtils.isEmpty(e)) {
                    d.put("sig", e);
                }
                jSONArray.put(d);
            } catch (Exception e2) {
            }
        }
        return jSONArray;
    }

    private static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        String f = k.f(context);
        if (!TextUtils.isEmpty(f)) {
            try {
                jSONArray.put(new JSONObject(f));
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    private static JSONArray s(Context context) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            String a = k.a(origApplicationContext);
            if (!TextUtils.isEmpty(a)) {
                try {
                    jSONObject = new JSONObject();
                    try {
                        jSONObject.put(e.X, a);
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    jSONObject = null;
                }
            } else {
                jSONObject = null;
            }
            String b = k.b(origApplicationContext);
            if (!TextUtils.isEmpty(b)) {
                if (jSONObject == null) {
                    try {
                        jSONObject2 = new JSONObject();
                    } catch (Exception e3) {
                        jSONObject2 = jSONObject;
                    }
                } else {
                    jSONObject2 = jSONObject;
                }
                try {
                    jSONObject2.put(e.Y, b);
                } catch (Exception e4) {
                }
            } else {
                jSONObject2 = jSONObject;
            }
            String c = k.c(origApplicationContext);
            if (!TextUtils.isEmpty(c)) {
                if (jSONObject2 == null) {
                    try {
                        jSONObject2 = new JSONObject();
                    } catch (Exception e5) {
                    }
                }
                jSONObject2.put(e.Z, c);
            }
            JSONObject d = k.d(origApplicationContext);
            if (d != null) {
                try {
                    String e6 = k.e(origApplicationContext);
                    if (!TextUtils.isEmpty(e6)) {
                        d.put("signalscale", e6);
                    }
                    if (jSONObject2 == null) {
                        jSONObject2 = new JSONObject();
                    }
                    jSONObject2.put(e.ab, d);
                    jSONObject3 = jSONObject2;
                } catch (Exception e7) {
                    jSONObject3 = jSONObject2;
                }
            } else {
                jSONObject3 = jSONObject2;
            }
            String f = k.f(origApplicationContext);
            if (!TextUtils.isEmpty(f)) {
                if (jSONObject3 == null) {
                    try {
                        jSONObject4 = new JSONObject();
                    } catch (Exception e8) {
                        jSONObject4 = jSONObject3;
                    }
                } else {
                    jSONObject4 = jSONObject3;
                }
                try {
                    jSONObject4.put(e.W, new JSONObject(f));
                } catch (Exception e9) {
                }
            } else {
                jSONObject4 = jSONObject3;
            }
            if (jSONObject4 != null) {
                jSONArray.put(jSONObject4);
            }
        }
        return jSONArray;
    }
}
