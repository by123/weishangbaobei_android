package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.g;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.i;
import com.umeng.analytics.pro.j;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.pro.l;
import com.umeng.analytics.pro.o;
import com.umeng.analytics.pro.p;
import com.umeng.analytics.pro.q;
import com.umeng.analytics.pro.r;
import com.umeng.analytics.pro.u;
import com.umeng.analytics.pro.x;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InternalAgent */
public class b implements p {
    private static final String A = "umsp_3";
    private static final String B = "umsp_4";
    private static final String C = "umsp_5";
    private static final String h = "sp_uapp";
    private static final String i = "prepp_uapp";
    private static final int o = 128;
    private static final int p = 256;
    private static String q = "";
    private static String r = "";
    private static final String s = "ekv_bl";
    private static final String t = "ekv_bl_ver";
    private static final String v = "ekv_wl";
    private static final String w = "ekv_wl_ver";
    private static final String y = "umsp_1";
    private static final String z = "umsp_2";
    private Context a;
    private x b;
    private l c;
    private r d;
    private i e;
    private q f;
    private j g;
    private boolean j;
    private volatile JSONObject k;
    private volatile JSONObject l;
    private volatile JSONObject m;
    private boolean n;
    private com.umeng.analytics.filter.a u;
    private com.umeng.analytics.filter.b x;

    private b() {
        this.a = null;
        this.c = new l();
        this.d = new r();
        this.e = new i();
        this.f = q.a();
        this.g = null;
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.u = null;
        this.x = null;
        this.c.a((p) this);
    }

    /* compiled from: InternalAgent */
    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.a;
    }

    public void a(Context context) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (this.u == null) {
                    this.u = new com.umeng.analytics.filter.a("ekv_bl", "ekv_bl_ver");
                    this.u.register(this.a);
                }
                if (this.x == null) {
                    this.x = new com.umeng.analytics.filter.b("ekv_wl", "ekv_wl_ver");
                    this.x.register(this.a);
                }
                if (UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    if (!this.j) {
                        this.j = true;
                        i(this.a);
                    }
                    if (Build.VERSION.SDK_INT > 13) {
                        synchronized (this) {
                            if (!this.n) {
                                this.g = new j(context);
                                if (this.g.a()) {
                                    this.n = true;
                                }
                            }
                        }
                    } else {
                        this.n = true;
                    }
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(h.B, 3, "", (String[]) null, (String[]) null);
                    }
                    if (Build.VERSION.SDK_INT > 13) {
                        UMWorkDispatch.sendEvent(this.a, k.a.w, CoreProtocol.getInstance(this.a), Long.valueOf(System.currentTimeMillis()));
                    }
                    UMWorkDispatch.registerConnStateObserver(CoreProtocol.getInstance(this.a));
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:5|(1:7)|8|(1:10)|11|(1:13)|14|(2:16|17)|18|19|(2:21|23)(1:25)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0044 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048 A[Catch:{ Throwable -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0008
            java.lang.String r3 = "unexpected null context in getNativeSuperProperties"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r3)     // Catch:{ Throwable -> 0x004f }
            return
        L_0x0008:
            android.content.Context r0 = r2.a     // Catch:{ Throwable -> 0x004f }
            if (r0 != 0) goto L_0x0016
            android.content.Context r0 = r3.getApplicationContext()     // Catch:{ Throwable -> 0x004f }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x004f }
            r2.a = r0     // Catch:{ Throwable -> 0x004f }
        L_0x0016:
            android.content.SharedPreferences r3 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r3)     // Catch:{ Throwable -> 0x004f }
            org.json.JSONObject r0 = r2.k     // Catch:{ Throwable -> 0x004f }
            if (r0 != 0) goto L_0x0025
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004f }
            r0.<init>()     // Catch:{ Throwable -> 0x004f }
            r2.k = r0     // Catch:{ Throwable -> 0x004f }
        L_0x0025:
            org.json.JSONObject r0 = r2.l     // Catch:{ Throwable -> 0x004f }
            if (r0 != 0) goto L_0x0030
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004f }
            r0.<init>()     // Catch:{ Throwable -> 0x004f }
            r2.l = r0     // Catch:{ Throwable -> 0x004f }
        L_0x0030:
            java.lang.String r0 = "prepp_uapp"
            r1 = 0
            java.lang.String r3 = r3.getString(r0, r1)     // Catch:{ Throwable -> 0x004f }
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x004f }
            if (r0 != 0) goto L_0x0044
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0044 }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x0044 }
            r2.m = r0     // Catch:{ JSONException -> 0x0044 }
        L_0x0044:
            org.json.JSONObject r3 = r2.m     // Catch:{ Throwable -> 0x004f }
            if (r3 != 0) goto L_0x004f
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004f }
            r3.<init>()     // Catch:{ Throwable -> 0x004f }
            r2.m = r3     // Catch:{ Throwable -> 0x004f }
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.i(android.content.Context):void");
    }

    public JSONObject b() {
        return this.k;
    }

    public JSONObject c() {
        return this.m;
    }

    public JSONObject d() {
        return this.l;
    }

    public void e() {
        this.l = null;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("onPageStart can not be called in child process");
            return;
        }
        try {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.a(str);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("onPageEnd can not be called in child process");
            return;
        }
        try {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.b(str);
            }
        } catch (Throwable unused) {
        }
    }

    public void a(x xVar) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setSysListener can not be called in child process");
        } else {
            this.b = xVar;
        }
    }

    public void a(Context context, int i2) {
        if (context == null) {
            MLog.e("unexpected null context in setVerticalType");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setVerticalType can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
        AnalyticsConfig.a(this.a, i2);
    }

    public String f() {
        if (UMGlobalContext.getInstance().isMainProcess(this.a)) {
            return q;
        }
        MLog.e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    public String g() {
        if (UMGlobalContext.getInstance().isMainProcess(this.a)) {
            return r;
        }
        MLog.e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    private void j(Context context) {
        try {
            Class.forName("com.umeng.visual.UMVisualAgent");
        } catch (ClassNotFoundException unused) {
            if (Build.VERSION.SDK_INT > 13) {
                UMWorkDispatch.sendEvent(context, k.a.s, CoreProtocol.getInstance(context), Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
        } else if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onResume can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.aq(h.o, 2, "\\|");
            }
            try {
                if (!this.j || !this.n) {
                    a(context);
                }
                if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.e.a(context.getClass().getName());
                }
                h();
                j(this.a);
                if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                    q = context.getClass().getName();
                }
            } catch (Throwable th) {
                MLog.e("Exception occurred in Mobclick.onResume(). ", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Context context) {
        if (context == null) {
            UMLog.aq(h.p, 0, "\\|");
        } else if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onPause can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.aq(h.q, 2, "\\|");
            }
            try {
                if (!this.j || !this.n) {
                    a(context);
                }
                if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.e.b(context.getClass().getName());
                }
                i();
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e("Exception occurred in Mobclick.onRause(). ", th);
                }
            }
            if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                r = context.getClass().getName();
            }
        }
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("onGKVEvent can not be called in child process");
                    return;
                }
                if (!this.j || !this.n) {
                    a(this.a);
                }
                String str2 = "";
                if (this.k == null) {
                    this.k = new JSONObject();
                } else {
                    str2 = this.k.toString();
                }
                o.a(this.a).a(str, hashMap, str2);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str) {
        if (context == null) {
            UMLog.aq(h.w, 0, "\\|");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("reportError can not be called in child process");
        } else if (!TextUtils.isEmpty(str)) {
            try {
                if (!this.j || !this.n) {
                    a(this.a);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(com.umeng.analytics.pro.b.P, 2);
                jSONObject.put(com.umeng.analytics.pro.b.Q, str);
                jSONObject.put("__ii", this.f.c());
                UMWorkDispatch.sendEvent(this.a, k.a.j, CoreProtocol.getInstance(this.a), jSONObject);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else if (UMConfigure.isDebugLog()) {
            UMLog.aq(h.x, 0, "\\|");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Throwable th) {
        if (context == null || th == null) {
            UMLog.aq(h.y, 0, "\\|");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("reportError can not be called in child process");
            return;
        }
        try {
            if (!this.j || !this.n) {
                a(this.a);
            }
            a(this.a, DataHelper.convertExceptionToString(th));
        } catch (Exception e2) {
            if (MLog.DEBUG) {
                MLog.e((Throwable) e2);
            }
        }
    }

    public void h() {
        try {
            if (this.a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("onStartSessionInternal can not be called in child process");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                UMWorkDispatch.sendEvent(this.a, k.a.k, CoreProtocol.getInstance(this.a), Long.valueOf(currentTimeMillis));
                UMWorkDispatch.sendEvent(this.a, k.a.g, CoreProtocol.getInstance(this.a), Long.valueOf(currentTimeMillis));
            }
            if (this.b != null) {
                this.b.a();
            }
        } catch (Throwable unused) {
        }
    }

    public void i() {
        try {
            if (this.a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("onEndSessionInternal can not be called in child process");
                    return;
                }
                UMWorkDispatch.sendEvent(this.a, k.a.h, CoreProtocol.getInstance(this.a), Long.valueOf(System.currentTimeMillis()));
                UMWorkDispatch.sendEvent(this.a, k.a.d, CoreProtocol.getInstance(this.a), (Object) null);
                UMWorkDispatch.sendEvent(this.a, k.a.c, CoreProtocol.getInstance(this.a), (Object) null);
                UMWorkDispatch.sendEvent(this.a, k.a.i, CoreProtocol.getInstance(this.a), (Object) null);
            }
        } catch (Throwable unused) {
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public void b(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(h.M, 0, "\\|");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onDeepLinkReceived can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (!TextUtils.isEmpty(str)) {
                HashMap hashMap = new HashMap();
                hashMap.put(com.umeng.analytics.pro.b.aB, str);
                b(this.a, com.umeng.analytics.pro.b.aA, hashMap, -1);
                return;
            }
            UMLog.aq(h.N, 0, "\\|");
        }
    }

    private boolean c(String str) {
        if (this.u.enabled() && this.u.matchHit(str)) {
            return true;
        }
        if (!this.x.enabled()) {
            return false;
        }
        if (!this.x.matchHit(str)) {
            return true;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> white list match! id = " + str);
        return false;
    }

    public void a(Context context, String str, String str2, long j2, int i2) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!this.j || !this.n) {
                    a(this.a);
                }
                if (c(str)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                    return;
                }
                String str3 = "";
                if (this.k == null) {
                    this.k = new JSONObject();
                } else {
                    str3 = this.k.toString();
                }
                o.a(this.a).a(str, str2, j2, i2, str3);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, Map<String, Object> map, long j2) {
        try {
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(h.c, 0, "\\|");
            } else if (Arrays.asList(com.umeng.analytics.pro.b.aC).contains(str)) {
                UMLog.aq(h.b, 0, "\\|");
            } else if (map.isEmpty()) {
                UMLog.aq(h.d, 0, "\\|");
            } else {
                for (Map.Entry<String, Object> key : map.entrySet()) {
                    if (Arrays.asList(com.umeng.analytics.pro.b.aC).contains(key.getKey())) {
                        UMLog.aq(h.e, 0, "\\|");
                        return;
                    }
                }
                b(context, str, map, j2);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    private void b(Context context, String str, Map<String, Object> map, long j2) {
        if (context == null) {
            try {
                MLog.e("context is null in onEventNoCheck, please check!");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (c(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String str2 = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                str2 = this.k.toString();
            }
            o.a(this.a).a(str, map, j2, str2);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(Context context) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("onKillProcess can not be called in child process");
                    return;
                }
                if (this.g != null) {
                    this.g.c();
                }
                if (this.e != null) {
                    this.e.b();
                }
                if (this.d != null) {
                    this.d.b();
                }
                if (this.a != null) {
                    if (this.f != null) {
                        this.f.c(this.a, (Object) Long.valueOf(System.currentTimeMillis()));
                    }
                    k.a(this.a).d();
                    r.a(this.a);
                    j.a(this.a);
                    PreferenceWrapper.getDefault(this.a).edit().commit();
                }
                UMWorkDispatch.Quit();
            } catch (Throwable unused) {
            }
        }
    }

    public void a(Throwable th) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onAppCrash can not be called in child process");
                UMWorkDispatch.Quit();
                return;
            }
            if (this.d != null) {
                this.d.b();
            }
            if (this.e != null) {
                this.e.b();
            }
            if (this.g != null) {
                this.g.c();
            }
            if (this.a != null) {
                if (this.f != null) {
                    this.f.c(this.a, (Object) Long.valueOf(System.currentTimeMillis()));
                }
                if (th != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(com.umeng.analytics.pro.b.P, 1);
                    jSONObject.put(com.umeng.analytics.pro.b.Q, DataHelper.convertExceptionToString(th));
                    g.a(this.a).a(this.f.c(), jSONObject.toString(), 1);
                }
                k.a(this.a).d();
                r.a(this.a);
                j.a(this.a);
                PreferenceWrapper.getDefault(this.a).edit().commit();
            }
            UMWorkDispatch.Quit();
        } catch (Exception e2) {
            if (MLog.DEBUG) {
                MLog.e("Exception in onAppCrash", (Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onProfileSignIn can not be called in child process");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.umeng.analytics.pro.b.L, str);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, str2);
            jSONObject.put("ts", currentTimeMillis);
            k.a(this.a).a((Object) null, true);
            UMWorkDispatch.sendEvent(this.a, k.a.e, CoreProtocol.getInstance(this.a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("onProfileSignOff can not be called in child process");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", currentTimeMillis);
            k.a(this.a).a((Object) null, true);
            UMWorkDispatch.sendEvent(this.a, k.a.f, CoreProtocol.getInstance(this.a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setCatchUncaughtExceptions can not be called in child process");
        } else if (!AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW) {
            AnalyticsConfig.CATCH_EXCEPTION = z2;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    /* access modifiers changed from: package-private */
    public void a(MobclickAgent.PageMode pageMode) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setPageCollectionMode can not be called in child process");
        } else {
            AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION = pageMode;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(double d2, double d3) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setLocation can not be called in child process");
            return;
        }
        if (AnalyticsConfig.a == null) {
            AnalyticsConfig.a = new double[2];
        }
        AnalyticsConfig.a[0] = d2;
        AnalyticsConfig.a[1] = d3;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context == null) {
            MLog.e("unexpected null context in setScenarioType");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setScenarioType can not be called in child process");
            return;
        }
        if (eScenarioType != null) {
            a(this.a, eScenarioType.toValue());
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Context context, String str) {
        if (context == null) {
            UMLog.aq(h.z, 0, "\\|");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setSecret can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
        AnalyticsConfig.a(this.a, str);
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setSessionContinueMillis can not be called in child process");
            return;
        }
        AnalyticsConfig.kContinueSessionMillis = j2;
        u.a().a(AnalyticsConfig.kContinueSessionMillis);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02c3, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:164:0x02a2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            if (r4 != 0) goto L_0x0013
            java.lang.String r4 = com.umeng.analytics.pro.h.ae     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r5 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r4, (int) r0, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0010 }
            monitor-exit(r3)
            return
        L_0x000d:
            r4 = move-exception
            goto L_0x02c4
        L_0x0010:
            r4 = move-exception
            goto L_0x02bf
        L_0x0013:
            android.content.Context r1 = r3.a     // Catch:{ Throwable -> 0x0010 }
            if (r1 != 0) goto L_0x0021
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ Throwable -> 0x0010 }
            android.content.Context r4 = com.stub.StubApp.getOrigApplicationContext(r4)     // Catch:{ Throwable -> 0x0010 }
            r3.a = r4     // Catch:{ Throwable -> 0x0010 }
        L_0x0021:
            com.umeng.commonsdk.service.UMGlobalContext r4 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x0010 }
            android.content.Context r1 = r3.a     // Catch:{ Throwable -> 0x0010 }
            boolean r4 = r4.isMainProcess(r1)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x0034
            java.lang.String r4 = "registerSuperProperty can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x0010 }
            monitor-exit(r3)
            return
        L_0x0034:
            boolean r4 = r3.j     // Catch:{ Throwable -> 0x0010 }
            if (r4 == 0) goto L_0x003c
            boolean r4 = r3.n     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x0041
        L_0x003c:
            android.content.Context r4 = r3.a     // Catch:{ Throwable -> 0x0010 }
            r3.a((android.content.Context) r4)     // Catch:{ Throwable -> 0x0010 }
        L_0x0041:
            boolean r4 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x02b6
            if (r6 != 0) goto L_0x004b
            goto L_0x02b6
        L_0x004b:
            java.lang.String r4 = "umsp_1"
            boolean r4 = r5.equals(r4)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x008e
            java.lang.String r4 = "umsp_2"
            boolean r4 = r5.equals(r4)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x008e
            java.lang.String r4 = "umsp_3"
            boolean r4 = r5.equals(r4)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x008e
            java.lang.String r4 = "umsp_4"
            boolean r4 = r5.equals(r4)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x008e
            java.lang.String r4 = "umsp_5"
            boolean r4 = r5.equals(r4)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x008e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0010 }
            r4.<init>()     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r6 = "property name is "
            r4.append(r6)     // Catch:{ Throwable -> 0x0010 }
            r4.append(r5)     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r5 = ", please check key, must be correct!"
            r4.append(r5)     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0010 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x0010 }
            monitor-exit(r3)
            return
        L_0x008e:
            boolean r4 = r6 instanceof java.lang.String     // Catch:{ Throwable -> 0x0010 }
            r1 = 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x00b9
            java.lang.String r4 = r6.toString()     // Catch:{ Throwable -> 0x0010 }
            boolean r4 = com.umeng.commonsdk.statistics.common.HelperUtils.checkStrLen(r4, r1)     // Catch:{ Throwable -> 0x0010 }
            if (r4 != 0) goto L_0x00b9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0010 }
            r4.<init>()     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r5 = "property value is "
            r4.append(r5)     // Catch:{ Throwable -> 0x0010 }
            r4.append(r6)     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r5 = ", please check value, lawless!"
            r4.append(r5)     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0010 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x0010 }
            monitor-exit(r3)
            return
        L_0x00b9:
            org.json.JSONObject r4 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x00c4
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            r3.k = r4     // Catch:{ Throwable -> 0x02a2 }
        L_0x00c4:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.Class r4 = r6.getClass()     // Catch:{ Throwable -> 0x02a2 }
            boolean r4 = r4.isArray()     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x027d
            boolean r4 = r6 instanceof java.lang.String[]     // Catch:{ Throwable -> 0x02a2 }
            r2 = 10
            if (r4 == 0) goto L_0x0141
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x00fc
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x00fc:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x0101:
            int r2 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r2) goto L_0x013a
            r2 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            if (r2 == 0) goto L_0x0119
            r2 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            boolean r2 = com.umeng.commonsdk.statistics.common.HelperUtils.checkStrLen(r2, r1)     // Catch:{ Throwable -> 0x02a2 }
            if (r2 != 0) goto L_0x0111
            goto L_0x0119
        L_0x0111:
            r2 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r2)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x0101
        L_0x0119:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, length is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            r5 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r5.length()     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overlength 256!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x013a:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x0141:
            boolean r4 = r6 instanceof long[]     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x017f
            long[] r6 = (long[]) r6     // Catch:{ Throwable -> 0x02a2 }
            long[] r6 = (long[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x0168
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x0168:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x016d:
            int r1 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r1) goto L_0x0178
            r1 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r1)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x016d
        L_0x0178:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x017f:
            boolean r4 = r6 instanceof int[]     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x01bd
            int[] r6 = (int[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int[] r6 = (int[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x01a6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x01a6:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x01ab:
            int r1 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r1) goto L_0x01b6
            r1 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r1)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x01ab
        L_0x01b6:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x01bd:
            boolean r4 = r6 instanceof float[]     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x01fc
            float[] r6 = (float[]) r6     // Catch:{ Throwable -> 0x02a2 }
            float[] r6 = (float[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x01e4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x01e4:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x01e9:
            int r1 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r1) goto L_0x01f5
            r1 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            double r1 = (double) r1     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r1)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x01e9
        L_0x01f5:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x01fc:
            boolean r4 = r6 instanceof double[]     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x0239
            double[] r6 = (double[]) r6     // Catch:{ Throwable -> 0x02a2 }
            double[] r6 = (double[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x0223
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x0223:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x0228:
            int r1 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r1) goto L_0x0233
            r1 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r1)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x0228
        L_0x0233:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x0239:
            boolean r4 = r6 instanceof short[]     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x0276
            short[] r6 = (short[]) r6     // Catch:{ Throwable -> 0x02a2 }
            short[] r6 = (short[]) r6     // Catch:{ Throwable -> 0x02a2 }
            int r4 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r4 <= r2) goto L_0x0260
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = "please check value, size is "
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            int r5 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r5 = ", overstep 10!"
            r4.append(r5)     // Catch:{ Throwable -> 0x02a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02a2 }
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x0260:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Throwable -> 0x02a2 }
            r4.<init>()     // Catch:{ Throwable -> 0x02a2 }
        L_0x0265:
            int r1 = r6.length     // Catch:{ Throwable -> 0x02a2 }
            if (r0 >= r1) goto L_0x0270
            short r1 = r6[r0]     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r1)     // Catch:{ Throwable -> 0x02a2 }
            int r0 = r0 + 1
            goto L_0x0265
        L_0x0270:
            org.json.JSONObject r6 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r6.put(r5, r4)     // Catch:{ Throwable -> 0x02a2 }
            goto L_0x02a2
        L_0x0276:
            java.lang.String r4 = "please check value, illegal type!"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x027d:
            boolean r4 = r6 instanceof java.lang.String     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x029d
            boolean r4 = r6 instanceof java.lang.Long     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x029d
            boolean r4 = r6 instanceof java.lang.Integer     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x029d
            boolean r4 = r6 instanceof java.lang.Float     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x029d
            boolean r4 = r6 instanceof java.lang.Double     // Catch:{ Throwable -> 0x02a2 }
            if (r4 != 0) goto L_0x029d
            boolean r4 = r6 instanceof java.lang.Short     // Catch:{ Throwable -> 0x02a2 }
            if (r4 == 0) goto L_0x0296
            goto L_0x029d
        L_0x0296:
            java.lang.String r4 = "please check value, illegal type!"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r4)     // Catch:{ Throwable -> 0x02a2 }
            monitor-exit(r3)
            return
        L_0x029d:
            org.json.JSONObject r4 = r3.k     // Catch:{ Throwable -> 0x02a2 }
            r4.put(r5, r6)     // Catch:{ Throwable -> 0x02a2 }
        L_0x02a2:
            android.content.Context r4 = r3.a     // Catch:{ Throwable -> 0x0010 }
            r5 = 8195(0x2003, float:1.1484E-41)
            android.content.Context r6 = r3.a     // Catch:{ Throwable -> 0x0010 }
            com.umeng.analytics.CoreProtocol r6 = com.umeng.analytics.CoreProtocol.getInstance(r6)     // Catch:{ Throwable -> 0x0010 }
            org.json.JSONObject r0 = r3.k     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0010 }
            com.umeng.commonsdk.framework.UMWorkDispatch.sendEvent(r4, r5, r6, r0)     // Catch:{ Throwable -> 0x0010 }
            goto L_0x02c2
        L_0x02b6:
            java.lang.String r4 = com.umeng.analytics.pro.h.af     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r5 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r4, (int) r0, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0010 }
            monitor-exit(r3)
            return
        L_0x02bf:
            r4.printStackTrace()     // Catch:{ all -> 0x000d }
        L_0x02c2:
            monitor-exit(r3)
            return
        L_0x02c4:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.a(android.content.Context, java.lang.String, java.lang.Object):void");
    }

    private void a(String str, Object obj) {
        try {
            if (this.k == null) {
                this.k = new JSONObject();
            }
            new JSONObject();
            int i2 = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    if (strArr.length <= 10) {
                        JSONArray jSONArray = new JSONArray();
                        while (i2 < strArr.length) {
                            if (strArr[i2] != null && !HelperUtils.checkStrLen(strArr[i2], 256)) {
                                jSONArray.put(strArr[i2]);
                            }
                            i2++;
                        }
                        this.k.put(str, jSONArray);
                    }
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    JSONArray jSONArray2 = new JSONArray();
                    while (i2 < jArr.length) {
                        jSONArray2.put(jArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray2);
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    JSONArray jSONArray3 = new JSONArray();
                    while (i2 < iArr.length) {
                        jSONArray3.put(iArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray3);
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    JSONArray jSONArray4 = new JSONArray();
                    while (i2 < fArr.length) {
                        jSONArray4.put((double) fArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray4);
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray5 = new JSONArray();
                    while (i2 < dArr.length) {
                        jSONArray5.put(dArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray5);
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i2 < sArr.length) {
                        jSONArray6.put(sArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray6);
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray7 = new JSONArray();
                while (i2 < list.size()) {
                    Object obj2 = list.get(i2);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray7.put(list.get(i2));
                    }
                    i2++;
                }
                this.k.put(str, jSONArray7);
            } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.k.put(str, obj);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.Object r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.umeng.commonsdk.service.UMGlobalContext r0 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            android.content.Context r1 = r2.a     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            boolean r0 = r0.isMainProcess(r1)     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            if (r0 != 0) goto L_0x0014
            java.lang.String r3 = "registerSuperPropertyByCoreProtocol can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r3)     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            monitor-exit(r2)
            return
        L_0x0014:
            if (r3 == 0) goto L_0x0041
            android.content.Context r0 = r2.a     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            if (r0 == 0) goto L_0x0041
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            android.content.Context r0 = r2.a     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            android.content.SharedPreferences r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r0)     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            if (r0 == 0) goto L_0x0041
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            if (r3 != 0) goto L_0x0041
            java.lang.String r3 = "sp_uapp"
            org.json.JSONObject r1 = r2.k     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            android.content.SharedPreferences$Editor r3 = r0.putString(r3, r1)     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            r3.commit()     // Catch:{ Throwable -> 0x0041, all -> 0x003e }
            goto L_0x0041
        L_0x003e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0041:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.a(java.lang.Object):void");
    }

    public synchronized void d(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(h.ag, 0, "\\|");
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("unregisterSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(h.af, 0, "\\|");
                return;
            } else if (str.equals(y) || str.equals(z) || str.equals(A) || str.equals(B) || str.equals(C)) {
                if (this.k == null) {
                    this.k = new JSONObject();
                }
                if (this.k.has(str)) {
                    this.k.remove(str);
                    UMWorkDispatch.sendEvent(this.a, k.a.r, CoreProtocol.getInstance(this.a), str);
                }
            } else {
                MLog.e("please check key or value, must be correct!");
                return;
            }
        }
        return;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void k() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.umeng.commonsdk.service.UMGlobalContext r0 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            android.content.Context r1 = r3.a     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            boolean r0 = r0.isMainProcess(r1)     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "unregisterSuperPropertyByCoreProtocol can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r0)     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            monitor-exit(r3)
            return
        L_0x0014:
            org.json.JSONObject r0 = r3.k     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            if (r0 == 0) goto L_0x0035
            android.content.Context r0 = r3.a     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            if (r0 == 0) goto L_0x0035
            android.content.Context r0 = r3.a     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            android.content.SharedPreferences r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r0)     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            java.lang.String r1 = "sp_uapp"
            org.json.JSONObject r2 = r3.k     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            r0.putString(r1, r2)     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            r0.commit()     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            goto L_0x0040
        L_0x0035:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            r0.<init>()     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            r3.k = r0     // Catch:{ Throwable -> 0x0040, all -> 0x003d }
            goto L_0x0040
        L_0x003d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0040:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.k():void");
    }

    public synchronized Object e(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(h.ah, 0, "\\|");
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("getSuperProperty can not be called in child process");
                return null;
            } else if (TextUtils.isEmpty(str)) {
                UMLog.aq(h.af, 0, "\\|");
                return null;
            } else if (!str.equals(y) && !str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C)) {
                MLog.e("please check key or value, must be correct!");
                return null;
            } else if (this.k == null) {
                this.k = new JSONObject();
            } else if (this.k.has(str)) {
                return this.k.opt(str);
            }
        }
        return null;
    }

    public synchronized String e(Context context) {
        if (context == null) {
            try {
                UMLog.aq(h.ah, 0, "\\|");
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("getSuperProperties can not be called in child process");
                return null;
            } else if (this.k != null) {
                return this.k.toString();
            } else {
                this.k = new JSONObject();
            }
        }
        return null;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.json.JSONObject l() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.umeng.commonsdk.service.UMGlobalContext r0 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x0023 }
            android.content.Context r1 = r2.a     // Catch:{ Throwable -> 0x0023 }
            boolean r0 = r0.isMainProcess(r1)     // Catch:{ Throwable -> 0x0023 }
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = "getSuperPropertiesJSONObject can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r0)     // Catch:{ Throwable -> 0x0023 }
            r0 = 0
            monitor-exit(r2)
            return r0
        L_0x0015:
            org.json.JSONObject r0 = r2.k     // Catch:{ Throwable -> 0x0023 }
            if (r0 != 0) goto L_0x0023
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0023 }
            r0.<init>()     // Catch:{ Throwable -> 0x0023 }
            r2.k = r0     // Catch:{ Throwable -> 0x0023 }
            goto L_0x0023
        L_0x0021:
            r0 = move-exception
            goto L_0x0027
        L_0x0023:
            org.json.JSONObject r0 = r2.k     // Catch:{ all -> 0x0021 }
            monitor-exit(r2)
            return r0
        L_0x0027:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.l():org.json.JSONObject");
    }

    public synchronized void f(Context context) {
        if (context == null) {
            UMLog.aq(h.ag, 0, "\\|");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("clearSuperProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
        this.k = new JSONObject();
        UMWorkDispatch.sendEvent(this.a, k.a.q, CoreProtocol.getInstance(this.a), (Object) null);
    }

    public synchronized void m() {
        try {
            if (this.a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("clearSuperPropertiesByCoreProtocol can not be called in child process");
                    return;
                }
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(this.a).edit();
                edit.remove(h);
                edit.commit();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public synchronized void a(Context context, List<String> list) {
        if (context == null) {
            try {
                UMLog.aq(h.ai, 0, "\\|");
                return;
            } catch (Throwable th) {
                MLog.e(th);
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("setFirstLaunchEvent can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            o.a(this.a).a(list);
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b6, code lost:
        return;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:59:0x00b7=Splitter:B:59:0x00b7, B:53:0x0098=Splitter:B:53:0x0098} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r5, org.json.JSONObject r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r5 != 0) goto L_0x0010
            java.lang.String r5 = com.umeng.analytics.pro.h.ak     // Catch:{ all -> 0x000d }
            java.lang.String r6 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r5, (int) r0, (java.lang.String) r6)     // Catch:{ all -> 0x000d }
            monitor-exit(r4)
            return
        L_0x000d:
            r5 = move-exception
            goto L_0x00c0
        L_0x0010:
            android.content.Context r1 = r4.a     // Catch:{ all -> 0x000d }
            if (r1 != 0) goto L_0x001e
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ all -> 0x000d }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ all -> 0x000d }
            r4.a = r5     // Catch:{ all -> 0x000d }
        L_0x001e:
            com.umeng.commonsdk.service.UMGlobalContext r5 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ all -> 0x000d }
            android.content.Context r1 = r4.a     // Catch:{ all -> 0x000d }
            boolean r5 = r5.isMainProcess(r1)     // Catch:{ all -> 0x000d }
            if (r5 != 0) goto L_0x0031
            java.lang.String r5 = "registerPreProperties can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r5)     // Catch:{ all -> 0x000d }
            monitor-exit(r4)
            return
        L_0x0031:
            boolean r5 = r4.j     // Catch:{ all -> 0x000d }
            if (r5 == 0) goto L_0x0039
            boolean r5 = r4.n     // Catch:{ all -> 0x000d }
            if (r5 != 0) goto L_0x003e
        L_0x0039:
            android.content.Context r5 = r4.a     // Catch:{ all -> 0x000d }
            r4.a((android.content.Context) r5)     // Catch:{ all -> 0x000d }
        L_0x003e:
            org.json.JSONObject r5 = r4.m     // Catch:{ all -> 0x000d }
            if (r5 != 0) goto L_0x0049
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x000d }
            r5.<init>()     // Catch:{ all -> 0x000d }
            r4.m = r5     // Catch:{ all -> 0x000d }
        L_0x0049:
            if (r6 == 0) goto L_0x00b7
            int r5 = r6.length()     // Catch:{ all -> 0x000d }
            if (r5 > 0) goto L_0x0052
            goto L_0x00b7
        L_0x0052:
            r5 = 0
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x005f }
            org.json.JSONObject r1 = r4.m     // Catch:{ Exception -> 0x005f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x005f }
            r0.<init>(r1)     // Catch:{ Exception -> 0x005f }
            r5 = r0
        L_0x005f:
            if (r5 != 0) goto L_0x0066
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x000d }
            r5.<init>()     // Catch:{ all -> 0x000d }
        L_0x0066:
            java.util.Iterator r0 = r6.keys()     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x0098
        L_0x006c:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x000d }
            if (r1 == 0) goto L_0x0098
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x006c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x006c }
            java.lang.Object r2 = r6.get(r1)     // Catch:{ Exception -> 0x006c }
            boolean r3 = r4.b((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x006c }
            if (r3 != 0) goto L_0x0086
            monitor-exit(r4)
            return
        L_0x0086:
            r5.put(r1, r2)     // Catch:{ Exception -> 0x006c }
            int r1 = r5.length()     // Catch:{ Exception -> 0x006c }
            r2 = 10
            if (r1 <= r2) goto L_0x006c
            java.lang.String r1 = "please check propertics, size overlength!"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r1)     // Catch:{ Exception -> 0x006c }
            monitor-exit(r4)
            return
        L_0x0098:
            r4.m = r5     // Catch:{ all -> 0x000d }
            org.json.JSONObject r5 = r4.m     // Catch:{ all -> 0x000d }
            int r5 = r5.length()     // Catch:{ all -> 0x000d }
            if (r5 <= 0) goto L_0x00b5
            android.content.Context r5 = r4.a     // Catch:{ all -> 0x000d }
            r6 = 8199(0x2007, float:1.1489E-41)
            android.content.Context r0 = r4.a     // Catch:{ all -> 0x000d }
            com.umeng.analytics.CoreProtocol r0 = com.umeng.analytics.CoreProtocol.getInstance(r0)     // Catch:{ all -> 0x000d }
            org.json.JSONObject r1 = r4.m     // Catch:{ all -> 0x000d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x000d }
            com.umeng.commonsdk.framework.UMWorkDispatch.sendEvent(r5, r6, r0, r1)     // Catch:{ all -> 0x000d }
        L_0x00b5:
            monitor-exit(r4)
            return
        L_0x00b7:
            java.lang.String r5 = com.umeng.analytics.pro.h.al     // Catch:{ all -> 0x000d }
            java.lang.String r6 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r5, (int) r0, (java.lang.String) r6)     // Catch:{ all -> 0x000d }
            monitor-exit(r4)
            return
        L_0x00c0:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.a(android.content.Context, org.json.JSONObject):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0081, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            if (r3 != 0) goto L_0x0010
            java.lang.String r3 = com.umeng.analytics.pro.h.am     // Catch:{ all -> 0x000d }
            java.lang.String r4 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r3, (int) r0, (java.lang.String) r4)     // Catch:{ all -> 0x000d }
            monitor-exit(r2)
            return
        L_0x000d:
            r3 = move-exception
            goto L_0x0089
        L_0x0010:
            android.content.Context r1 = r2.a     // Catch:{ all -> 0x000d }
            if (r1 != 0) goto L_0x001e
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x000d }
            android.content.Context r3 = com.stub.StubApp.getOrigApplicationContext(r3)     // Catch:{ all -> 0x000d }
            r2.a = r3     // Catch:{ all -> 0x000d }
        L_0x001e:
            com.umeng.commonsdk.service.UMGlobalContext r3 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ all -> 0x000d }
            android.content.Context r1 = r2.a     // Catch:{ all -> 0x000d }
            boolean r3 = r3.isMainProcess(r1)     // Catch:{ all -> 0x000d }
            if (r3 != 0) goto L_0x0031
            java.lang.String r3 = "unregisterPreProperty can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r3)     // Catch:{ all -> 0x000d }
            monitor-exit(r2)
            return
        L_0x0031:
            boolean r3 = r2.j     // Catch:{ all -> 0x000d }
            if (r3 == 0) goto L_0x0039
            boolean r3 = r2.n     // Catch:{ all -> 0x000d }
            if (r3 != 0) goto L_0x003e
        L_0x0039:
            android.content.Context r3 = r2.a     // Catch:{ all -> 0x000d }
            r2.a((android.content.Context) r3)     // Catch:{ all -> 0x000d }
        L_0x003e:
            org.json.JSONObject r3 = r2.m     // Catch:{ all -> 0x000d }
            if (r3 != 0) goto L_0x0049
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x000d }
            r3.<init>()     // Catch:{ all -> 0x000d }
            r2.m = r3     // Catch:{ all -> 0x000d }
        L_0x0049:
            if (r4 == 0) goto L_0x0082
            int r3 = r4.length()     // Catch:{ all -> 0x000d }
            if (r3 > 0) goto L_0x0052
            goto L_0x0082
        L_0x0052:
            org.json.JSONObject r3 = r2.m     // Catch:{ all -> 0x000d }
            boolean r3 = r3.has(r4)     // Catch:{ all -> 0x000d }
            if (r3 == 0) goto L_0x0073
            org.json.JSONObject r3 = r2.m     // Catch:{ all -> 0x000d }
            r3.remove(r4)     // Catch:{ all -> 0x000d }
            android.content.Context r3 = r2.a     // Catch:{ all -> 0x000d }
            r4 = 8200(0x2008, float:1.149E-41)
            android.content.Context r0 = r2.a     // Catch:{ all -> 0x000d }
            com.umeng.analytics.CoreProtocol r0 = com.umeng.analytics.CoreProtocol.getInstance(r0)     // Catch:{ all -> 0x000d }
            org.json.JSONObject r1 = r2.m     // Catch:{ all -> 0x000d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x000d }
            com.umeng.commonsdk.framework.UMWorkDispatch.sendEvent(r3, r4, r0, r1)     // Catch:{ all -> 0x000d }
            goto L_0x0080
        L_0x0073:
            boolean r3 = com.umeng.commonsdk.UMConfigure.isDebugLog()     // Catch:{ all -> 0x000d }
            if (r3 == 0) goto L_0x0080
            java.lang.String r3 = com.umeng.analytics.pro.h.an     // Catch:{ all -> 0x000d }
            java.lang.String r4 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r3, (int) r0, (java.lang.String) r4)     // Catch:{ all -> 0x000d }
        L_0x0080:
            monitor-exit(r2)
            return
        L_0x0082:
            java.lang.String r3 = "please check propertics, property is null!"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r3)     // Catch:{ all -> 0x000d }
            monitor-exit(r2)
            return
        L_0x0089:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.f(android.content.Context, java.lang.String):void");
    }

    public synchronized void g(Context context) {
        if (context == null) {
            UMLog.aq(h.ao, 0, "\\|");
            return;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("clearPreProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
        if (this.m.length() > 0) {
            UMWorkDispatch.sendEvent(this.a, k.a.v, CoreProtocol.getInstance(this.a), (Object) null);
        }
        this.m = new JSONObject();
    }

    public synchronized JSONObject h(Context context) {
        JSONObject jSONObject;
        if (context == null) {
            UMLog.aq(h.ap, 0, "\\|");
            return null;
        }
        if (this.a == null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("getPreProperties can not be called in child process");
            return null;
        }
        if (!this.j || !this.n) {
            a(this.a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        jSONObject = new JSONObject();
        if (this.m.length() > 0) {
            try {
                jSONObject = new JSONObject(this.m.toString());
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(java.lang.Object r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.umeng.commonsdk.service.UMGlobalContext r0 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            android.content.Context r1 = r2.a     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            boolean r0 = r0.isMainProcess(r1)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            if (r0 != 0) goto L_0x0014
            java.lang.String r3 = "updateNativePrePropertiesByCoreProtocol can not be called in child process"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r3)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            monitor-exit(r2)
            return
        L_0x0014:
            android.content.Context r0 = r2.a     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            android.content.SharedPreferences r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            if (r3 == 0) goto L_0x0034
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            if (r0 == 0) goto L_0x0043
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            if (r1 != 0) goto L_0x0043
            java.lang.String r1 = "prepp_uapp"
            android.content.SharedPreferences$Editor r3 = r0.putString(r1, r3)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            r3.commit()     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            goto L_0x0043
        L_0x0034:
            if (r0 == 0) goto L_0x0043
            java.lang.String r3 = "prepp_uapp"
            android.content.SharedPreferences$Editor r3 = r0.remove(r3)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            r3.commit()     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            goto L_0x0043
        L_0x0040:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0043:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.b(java.lang.Object):void");
    }

    private boolean b(String str, Object obj) {
        int i2;
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.e("key is " + str + ", please check key, illegal");
                return false;
            }
            try {
                i2 = str.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException unused) {
                i2 = 0;
            }
            if (i2 > 128) {
                MLog.e("key length is " + i2 + ", please check key, illegal");
                return false;
            } else if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= 256) {
                    return true;
                }
                MLog.e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
                return false;
            } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float)) {
                return true;
            } else {
                MLog.e("value is " + obj + ", please check value, type illegal");
                return false;
            }
        } catch (Throwable unused2) {
        }
    }
}
