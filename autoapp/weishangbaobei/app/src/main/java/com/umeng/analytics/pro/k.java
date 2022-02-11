package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.process.UMProcessDBHelper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CoreProtocolImpl */
public class k {
    /* access modifiers changed from: private */
    public static Context a = null;
    private static final String l = "first_activate_time";
    private static final String m = "ana_is_f";
    private static final String n = "thtstart";
    private static final String o = "dstk_last_time";
    private static final String p = "dstk_cnt";
    private static final String q = "gkvc";
    private static final String r = "ekvc";
    private static final String t = "-1";
    private c b;
    private SharedPreferences c;
    private String d;
    private String e;
    private int f;
    private JSONArray g;
    private final int h;
    private int i;
    private int j;
    private long k;
    private final long s;
    private boolean u;
    private boolean v;
    private Object w;

    /* compiled from: CoreProtocolImpl */
    public static class a {
        public static final int a = 4097;
        public static final int b = 4098;
        public static final int c = 4099;
        public static final int d = 4100;
        public static final int e = 4101;
        public static final int f = 4102;
        public static final int g = 4103;
        public static final int h = 4104;
        public static final int i = 4105;
        public static final int j = 4106;
        public static final int k = 4352;
        public static final int l = 4353;
        public static final int m = 4354;
        public static final int n = 8193;
        public static final int o = 8194;
        public static final int p = 8195;
        public static final int q = 8196;
        public static final int r = 8197;
        public static final int s = 8198;
        public static final int t = 8199;
        public static final int u = 8200;
        public static final int v = 8201;
        public static final int w = 8202;
    }

    public void b() {
    }

    private k() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 10;
        this.g = new JSONArray();
        this.h = 5000;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.s = 28800000;
        this.u = false;
        this.v = false;
        this.w = new Object();
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            this.k = sharedPreferences.getLong(n, 0);
            this.i = sharedPreferences.getInt(q, 0);
            this.j = sharedPreferences.getInt(r, 0);
            this.b = new c();
        } catch (Throwable unused) {
        }
    }

    /* compiled from: CoreProtocolImpl */
    private static class b {
        /* access modifiers changed from: private */
        public static final k a = new k();

        private b() {
        }
    }

    public static k a(Context context) {
        if (a == null && context != null) {
            a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return b.a;
    }

    public void a() {
        if (a != null) {
            synchronized (this.w) {
                if (this.u) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> network is now available, rebuild instant session data packet.");
                    UMWorkDispatch.sendEvent(a, a.l, CoreProtocol.getInstance(a), (Object) null);
                }
            }
            synchronized (this.w) {
                if (this.v) {
                    UMWorkDispatch.sendEvent(a, a.m, CoreProtocol.getInstance(a), (Object) null);
                }
            }
        }
    }

    public void c() {
        b(a);
        d();
        a(true);
    }

    public void a(Object obj, int i2) {
        switch (i2) {
            case a.a /*4097*/:
                if (UMGlobalContext.getInstance().isMainProcess(a)) {
                    if (obj != null) {
                        e(obj);
                    }
                    if (!t.equals(((JSONObject) obj).optString("__i"))) {
                        a(false);
                        return;
                    }
                    return;
                }
                UMProcessDBHelper.getInstance(a).insertEventsInSubProcess(UMFrUtils.getSubProcessName(a), new JSONArray().put(obj));
                return;
            case a.b /*4098*/:
                if (obj != null) {
                    try {
                        e(obj);
                    } catch (Throwable unused) {
                        return;
                    }
                }
                if (!t.equals(((JSONObject) obj).optString("__i"))) {
                    a(false);
                    return;
                }
                return;
            case a.c /*4099*/:
                r.a(a);
                return;
            case a.d /*4100*/:
                j.a(a);
                return;
            case a.e /*4101*/:
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNIN");
                g(obj);
                return;
            case a.f /*4102*/:
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNOFF");
                f(obj);
                return;
            case a.g /*4103*/:
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> START_SESSION");
                q.a().a(a, obj);
                synchronized (this.w) {
                    this.v = true;
                }
                return;
            case a.h /*4104*/:
                q.a().c(a, obj);
                return;
            case a.i /*4105*/:
                d();
                return;
            case a.j /*4106*/:
                h(obj);
                return;
            default:
                switch (i2) {
                    case a.k /*4352*/:
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> INSTANT_SESSION_START");
                        q.a().b(a, obj);
                        synchronized (this.w) {
                            this.u = true;
                        }
                        return;
                    case a.l /*4353*/:
                        a(obj, true);
                        return;
                    case a.m /*4354*/:
                        c();
                        return;
                    default:
                        switch (i2) {
                            case a.p /*8195*/:
                                com.umeng.analytics.b.a().a(obj);
                                return;
                            case a.q /*8196*/:
                                com.umeng.analytics.b.a().m();
                                return;
                            case a.r /*8197*/:
                                com.umeng.analytics.b.a().k();
                                return;
                            case a.s /*8198*/:
                                if (!TextUtils.isEmpty(q.a().b())) {
                                    i();
                                    return;
                                }
                                return;
                            case a.t /*8199*/:
                            case a.u /*8200*/:
                                com.umeng.analytics.b.a().b(obj);
                                return;
                            case a.v /*8201*/:
                                com.umeng.analytics.b.a().b((Object) null);
                                return;
                            case a.w /*8202*/:
                                h();
                                return;
                            default:
                                return;
                        }
                }
        }
    }

    private void h() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("loadNativeData", new Class[]{Context.class}).invoke((Object) null, new Object[]{a});
        } catch (Exception unused) {
        }
    }

    private void i() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("processCommond", new Class[]{Context.class, String.class}).invoke((Object) null, new Object[]{a, AnalyticsConfig.getAppkey(a)});
        } catch (Exception unused) {
        }
    }

    public void a(boolean z) {
        if (!c(z)) {
            return;
        }
        if (this.b.c() instanceof ReportPolicy.ReportQuasiRealtime) {
            if (z) {
                if (UMEnvelopeBuild.isOnline(a)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send session start in policy ReportQuasiRealtime.");
                    k();
                }
            } else if (UMEnvelopeBuild.isReadyBuild(a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send normal data in policy ReportQuasiRealtime.");
                k();
            }
        } else if (UMEnvelopeBuild.isReadyBuild(a, UMLogDataProtocol.UMBusinessType.U_APP)) {
            k();
        }
    }

    private void j() {
        JSONObject b2 = b(UMEnvelopeBuild.maxDataSpace(a));
        if (b2 != null && b2.length() >= 1) {
            JSONObject jSONObject = (JSONObject) b2.opt("header");
            JSONObject jSONObject2 = (JSONObject) b2.opt("content");
            if (a != null && jSONObject != null && jSONObject2 != null) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> constructInstantMessage: request build envelope.");
                JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject, jSONObject2);
                if (buildEnvelopeWithExtHeader != null) {
                    try {
                        if (buildEnvelopeWithExtHeader.has("exception")) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                        }
                    } catch (Throwable unused) {
                    }
                    b((Object) buildEnvelopeWithExtHeader);
                }
            }
        }
    }

    private void k() {
        JSONObject buildEnvelopeWithExtHeader;
        JSONObject a2 = a(UMEnvelopeBuild.maxDataSpace(a));
        if (a2 != null && a2.length() >= 1) {
            JSONObject jSONObject = (JSONObject) a2.opt("header");
            JSONObject jSONObject2 = (JSONObject) a2.opt("content");
            if (a != null && jSONObject != null && jSONObject2 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject, jSONObject2)) != null) {
                try {
                    if (buildEnvelopeWithExtHeader.has("exception")) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                    }
                } catch (Throwable unused) {
                }
                c(buildEnvelopeWithExtHeader);
                a((Object) buildEnvelopeWithExtHeader);
            }
        }
    }

    public JSONObject a(long j2) {
        if (TextUtils.isEmpty(u.a().d(a))) {
            return null;
        }
        JSONObject b2 = b(false);
        int a2 = n.a().a(a);
        if (b2.length() <= 0) {
            if (a2 != 3) {
                return null;
            }
        } else if (b2.length() == 1) {
            if (b2.optJSONObject(b.K) != null && a2 != 3) {
                return null;
            }
            if (!TextUtils.isEmpty(b2.optString("userlevel")) && a2 != 3) {
                return null;
            }
        } else if (b2.length() == 2 && b2.optJSONObject(b.K) != null && !TextUtils.isEmpty(b2.optString("userlevel")) && a2 != 3) {
            return null;
        }
        JSONObject m2 = m();
        if (m2 != null) {
            b(m2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (a2 == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (b2 != null && b2.length() > 0) {
                jSONObject2.put("analytics", b2);
            }
            if (m2 != null && m2.length() > 0) {
                jSONObject.put("header", m2);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("content", jSONObject2);
            }
            return a(jSONObject, j2);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject f2;
        if (!g.a(UMGlobalContext.getAppContext(a)).c() && (f2 = g.a(UMGlobalContext.getAppContext(a)).f()) != null) {
            String optString = f2.optString("__av");
            String optString2 = f2.optString("__vc");
            try {
                if (TextUtils.isEmpty(optString)) {
                    jSONObject.put("app_version", UMUtils.getAppVersionName(a));
                } else {
                    jSONObject.put("app_version", optString);
                }
                if (TextUtils.isEmpty(optString2)) {
                    jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
                } else {
                    jSONObject.put("version_code", optString2);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public JSONObject b(long j2) {
        if (TextUtils.isEmpty(u.a().d(UMGlobalContext.getAppContext(a)))) {
            return null;
        }
        JSONObject b2 = g.a(UMGlobalContext.getAppContext(a)).b(false);
        String[] a2 = com.umeng.analytics.c.a(a);
        if (a2 != null && !TextUtils.isEmpty(a2[0]) && !TextUtils.isEmpty(a2[1])) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(b.L, a2[0]);
                jSONObject.put(b.M, a2[1]);
                if (jSONObject.length() > 0) {
                    b2.put(b.K, jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
        int a3 = n.a().a(a);
        if (b2.length() == 1 && b2.optJSONObject(b.K) != null && a3 != 3) {
            return null;
        }
        n.a().b(b2, a);
        if (b2.length() <= 0 && a3 != 3) {
            return null;
        }
        JSONObject l2 = l();
        if (l2 != null) {
            a(l2);
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        if (a3 == 3) {
            try {
                jSONObject3.put("analytics", new JSONObject());
            } catch (Throwable unused2) {
                return jSONObject2;
            }
        } else if (b2 != null && b2.length() > 0) {
            jSONObject3.put("analytics", b2);
        }
        if (l2 != null && l2.length() > 0) {
            jSONObject2.put("header", l2);
        }
        if (jSONObject3.length() > 0) {
            jSONObject2.put("content", jSONObject3);
        }
        return b(jSONObject2, j2);
    }

    private JSONObject a(JSONObject jSONObject, long j2) {
        try {
            if (m.a(jSONObject) <= j2) {
                return jSONObject;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("header");
            jSONObject2.put(b.ay, m.a(jSONObject));
            jSONObject.put("header", jSONObject2);
            return m.a(a, j2, jSONObject);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private JSONObject b(JSONObject jSONObject, long j2) {
        try {
            if (m.a(jSONObject) <= j2) {
                return jSONObject;
            }
            g.a(a).a(true, false);
            g.a(a).b();
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Instant session packet overload !!! ");
            return null;
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private JSONObject l() {
        JSONObject m2 = m();
        if (m2 != null) {
            try {
                m2.put("st", "1");
            } catch (Throwable unused) {
            }
        }
        return m2;
    }

    private void b(JSONObject jSONObject) {
        if (!g.a(a).e()) {
            JSONObject g2 = g.a(a).g();
            if (g2 != null) {
                String optString = g2.optString("__av");
                String optString2 = g2.optString("__vc");
                try {
                    if (TextUtils.isEmpty(optString)) {
                        jSONObject.put("app_version", UMUtils.getAppVersionName(a));
                    } else {
                        jSONObject.put("app_version", optString);
                    }
                    if (TextUtils.isEmpty(optString2)) {
                        jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
                    } else {
                        jSONObject.put("version_code", optString2);
                    }
                } catch (Throwable unused) {
                }
            }
        } else {
            jSONObject.put("app_version", UMUtils.getAppVersionName(a));
            jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
        }
    }

    private JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                jSONObject.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            jSONObject.put(b.i, AnalyticsConfig.getVerticalType(a));
            jSONObject.put("sdk_version", v.a);
            String MD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(a));
            if (!TextUtils.isEmpty(MD5)) {
                jSONObject.put("secret", MD5);
            }
            String imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", (String) null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            String imprintProperty2 = UMEnvelopeBuild.imprintProperty(a, b.ak, "");
            if (!TextUtils.isEmpty(imprintProperty2)) {
                if (AnalyticsConfig.CLEAR_EKV_BL) {
                    jSONObject.put(b.am, "");
                } else {
                    jSONObject.put(b.am, imprintProperty2);
                }
            }
            String imprintProperty3 = UMEnvelopeBuild.imprintProperty(a, b.al, "");
            if (!TextUtils.isEmpty(imprintProperty3)) {
                if (AnalyticsConfig.CLEAR_EKV_WL) {
                    jSONObject.put(b.an, "");
                } else {
                    jSONObject.put(b.an, imprintProperty3);
                }
            }
            jSONObject.put(b.ae, "1.0.0");
            if (t()) {
                jSONObject.put(b.ag, "1");
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putLong(m, 0).commit();
                }
            }
            jSONObject.put(b.l, n());
            jSONObject.put(b.m, o());
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("vers_name", "");
                if (!TextUtils.isEmpty(string)) {
                    String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (TextUtils.isEmpty(imprintProperty)) {
                        jSONObject.put(b.l, sharedPreferences.getString("vers_pre_version", "0"));
                        jSONObject.put(b.m, sharedPreferences.getString("vers_date", format));
                    }
                    sharedPreferences.edit().putString("pre_version", string).putString("cur_version", DeviceConfig.getAppVersionName(a)).putString("pre_date", format).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0104, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        com.umeng.commonsdk.statistics.common.MLog.e("merge pages error");
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return r12;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000e, B:9:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject b(boolean r12) {
        /*
            r11 = this;
            r0 = 0
            android.content.Context r1 = a     // Catch:{ Throwable -> 0x0196 }
            com.umeng.analytics.pro.g r1 = com.umeng.analytics.pro.g.a((android.content.Context) r1)     // Catch:{ Throwable -> 0x0196 }
            org.json.JSONObject r12 = r1.a((boolean) r12)     // Catch:{ Throwable -> 0x0196 }
            r0 = 0
            if (r12 != 0) goto L_0x0016
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0197 }
            r1.<init>()     // Catch:{ Throwable -> 0x0197 }
            r12 = r1
            goto L_0x010d
        L_0x0016:
            java.lang.String r1 = "sessions"
            boolean r1 = r12.has(r1)     // Catch:{ Exception -> 0x0104 }
            if (r1 == 0) goto L_0x010d
            java.lang.String r1 = "sessions"
            org.json.JSONArray r1 = r12.getJSONArray(r1)     // Catch:{ Exception -> 0x0104 }
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Exception -> 0x0104 }
            r2.<init>()     // Catch:{ Exception -> 0x0104 }
            r3 = 0
        L_0x002a:
            int r4 = r1.length()     // Catch:{ Exception -> 0x0104 }
            if (r3 >= r4) goto L_0x00fe
            java.lang.Object r4 = r1.get(r3)     // Catch:{ Exception -> 0x0104 }
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ Exception -> 0x0104 }
            java.lang.String r5 = "pages"
            org.json.JSONArray r5 = r4.optJSONArray(r5)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r6 = "autopages"
            org.json.JSONArray r6 = r4.optJSONArray(r6)     // Catch:{ Exception -> 0x0104 }
            if (r5 != 0) goto L_0x0050
            if (r6 == 0) goto L_0x0050
            java.lang.String r7 = "pages"
            r4.put(r7, r6)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r7 = "autopages"
            r4.remove(r7)     // Catch:{ Exception -> 0x0104 }
        L_0x0050:
            if (r5 == 0) goto L_0x00af
            if (r6 == 0) goto L_0x00af
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x0104 }
            r7.<init>()     // Catch:{ Exception -> 0x0104 }
            r8 = 0
        L_0x005a:
            int r9 = r5.length()     // Catch:{ Exception -> 0x0104 }
            if (r8 >= r9) goto L_0x006c
            java.lang.Object r9 = r5.get(r8)     // Catch:{ Exception -> 0x0104 }
            org.json.JSONObject r9 = (org.json.JSONObject) r9     // Catch:{ Exception -> 0x0104 }
            r7.add(r9)     // Catch:{ Exception -> 0x0104 }
            int r8 = r8 + 1
            goto L_0x005a
        L_0x006c:
            r5 = 0
        L_0x006d:
            int r8 = r6.length()     // Catch:{ Exception -> 0x0104 }
            if (r5 >= r8) goto L_0x007f
            java.lang.Object r8 = r6.get(r5)     // Catch:{ Exception -> 0x0104 }
            org.json.JSONObject r8 = (org.json.JSONObject) r8     // Catch:{ Exception -> 0x0104 }
            r7.add(r8)     // Catch:{ Exception -> 0x0104 }
            int r5 = r5 + 1
            goto L_0x006d
        L_0x007f:
            com.umeng.commonsdk.utils.JSONArraySortUtil r5 = new com.umeng.commonsdk.utils.JSONArraySortUtil     // Catch:{ Exception -> 0x0104 }
            r5.<init>()     // Catch:{ Exception -> 0x0104 }
            java.lang.String r6 = "page_start"
            r5.setCompareKey(r6)     // Catch:{ Exception -> 0x0104 }
            java.util.Collections.sort(r7, r5)     // Catch:{ Exception -> 0x0104 }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Exception -> 0x0104 }
            r5.<init>()     // Catch:{ Exception -> 0x0104 }
            java.util.Iterator r6 = r7.iterator()     // Catch:{ Exception -> 0x0104 }
        L_0x0095:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0104 }
            if (r7 == 0) goto L_0x00a5
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0104 }
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ Exception -> 0x0104 }
            r5.put(r7)     // Catch:{ Exception -> 0x0104 }
            goto L_0x0095
        L_0x00a5:
            java.lang.String r6 = "pages"
            r4.put(r6, r5)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r5 = "autopages"
            r4.remove(r5)     // Catch:{ Exception -> 0x0104 }
        L_0x00af:
            java.lang.String r5 = "pages"
            boolean r5 = r4.has(r5)     // Catch:{ Exception -> 0x0104 }
            if (r5 == 0) goto L_0x00f2
            java.lang.String r5 = "pages"
            org.json.JSONArray r5 = r4.optJSONArray(r5)     // Catch:{ Exception -> 0x0104 }
            r6 = 0
        L_0x00be:
            int r7 = r5.length()     // Catch:{ Exception -> 0x0104 }
            if (r6 >= r7) goto L_0x00e3
            org.json.JSONObject r7 = r5.getJSONObject(r6)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r8 = "page_start"
            boolean r8 = r7.has(r8)     // Catch:{ Exception -> 0x0104 }
            if (r8 == 0) goto L_0x00e0
            java.lang.String r8 = "ts"
            java.lang.String r9 = "page_start"
            long r9 = r7.getLong(r9)     // Catch:{ Exception -> 0x0104 }
            r7.put(r8, r9)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r8 = "page_start"
            r7.remove(r8)     // Catch:{ Exception -> 0x0104 }
        L_0x00e0:
            int r6 = r6 + 1
            goto L_0x00be
        L_0x00e3:
            java.lang.String r6 = "pages"
            r4.put(r6, r5)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r6 = "$page_num"
            int r5 = r5.length()     // Catch:{ Exception -> 0x0104 }
            r4.put(r6, r5)     // Catch:{ Exception -> 0x0104 }
            goto L_0x00f7
        L_0x00f2:
            java.lang.String r5 = "$page_num"
            r4.put(r5, r0)     // Catch:{ Exception -> 0x0104 }
        L_0x00f7:
            r2.put(r4)     // Catch:{ Exception -> 0x0104 }
            int r3 = r3 + 1
            goto L_0x002a
        L_0x00fe:
            java.lang.String r1 = "sessions"
            r12.put(r1, r2)     // Catch:{ Exception -> 0x0104 }
            goto L_0x010d
        L_0x0104:
            r1 = move-exception
            java.lang.String r2 = "merge pages error"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r2)     // Catch:{ Throwable -> 0x0197 }
            r1.printStackTrace()     // Catch:{ Throwable -> 0x0197 }
        L_0x010d:
            android.content.Context r1 = a     // Catch:{ Throwable -> 0x0197 }
            android.content.SharedPreferences r1 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r1)     // Catch:{ Throwable -> 0x0197 }
            if (r1 == 0) goto L_0x0128
            java.lang.String r2 = "userlevel"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ Throwable -> 0x0197 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0197 }
            if (r2 != 0) goto L_0x0128
            java.lang.String r2 = "userlevel"
            r12.put(r2, r1)     // Catch:{ Throwable -> 0x0197 }
        L_0x0128:
            android.content.Context r1 = a     // Catch:{ Throwable -> 0x0197 }
            java.lang.String[] r1 = com.umeng.analytics.c.a(r1)     // Catch:{ Throwable -> 0x0197 }
            if (r1 == 0) goto L_0x015f
            r2 = r1[r0]     // Catch:{ Throwable -> 0x0197 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0197 }
            if (r2 != 0) goto L_0x015f
            r2 = 1
            r3 = r1[r2]     // Catch:{ Throwable -> 0x0197 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0197 }
            if (r3 != 0) goto L_0x015f
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0197 }
            r3.<init>()     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r4 = "provider"
            r0 = r1[r0]     // Catch:{ Throwable -> 0x0197 }
            r3.put(r4, r0)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r0 = "puid"
            r1 = r1[r2]     // Catch:{ Throwable -> 0x0197 }
            r3.put(r0, r1)     // Catch:{ Throwable -> 0x0197 }
            int r0 = r3.length()     // Catch:{ Throwable -> 0x0197 }
            if (r0 <= 0) goto L_0x015f
            java.lang.String r0 = "active_user"
            r12.put(r0, r3)     // Catch:{ Throwable -> 0x0197 }
        L_0x015f:
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x0197 }
            com.umeng.commonsdk.statistics.noise.ABTest r0 = com.umeng.commonsdk.statistics.noise.ABTest.getService(r0)     // Catch:{ Throwable -> 0x0197 }
            boolean r0 = r0.isInTest()     // Catch:{ Throwable -> 0x0197 }
            if (r0 == 0) goto L_0x018c
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0197 }
            r0.<init>()     // Catch:{ Throwable -> 0x0197 }
            android.content.Context r1 = a     // Catch:{ Throwable -> 0x0197 }
            com.umeng.commonsdk.statistics.noise.ABTest r1 = com.umeng.commonsdk.statistics.noise.ABTest.getService(r1)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r1 = r1.getTestName()     // Catch:{ Throwable -> 0x0197 }
            android.content.Context r2 = a     // Catch:{ Throwable -> 0x0197 }
            com.umeng.commonsdk.statistics.noise.ABTest r2 = com.umeng.commonsdk.statistics.noise.ABTest.getService(r2)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r2 = r2.getGroupInfo()     // Catch:{ Throwable -> 0x0197 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r1 = "group_info"
            r12.put(r1, r0)     // Catch:{ Throwable -> 0x0197 }
        L_0x018c:
            com.umeng.analytics.pro.n r0 = com.umeng.analytics.pro.n.a()     // Catch:{ Throwable -> 0x0197 }
            android.content.Context r1 = a     // Catch:{ Throwable -> 0x0197 }
            r0.a((org.json.JSONObject) r12, (android.content.Context) r1)     // Catch:{ Throwable -> 0x0197 }
            goto L_0x0197
        L_0x0196:
            r12 = r0
        L_0x0197:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.b(boolean):org.json.JSONObject");
    }

    private String n() {
        String str = null;
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", (String) null);
            try {
                if (TextUtils.isEmpty(imprintProperty)) {
                    if (!TextUtils.isEmpty(this.d)) {
                        return this.d;
                    }
                    if (this.c == null) {
                        this.c = PreferenceWrapper.getDefault(a);
                    }
                    str = this.c.getString("pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(a);
                    if (TextUtils.isEmpty(str)) {
                        this.c.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                        str = "0";
                    } else {
                        String string = this.c.getString("cur_version", "");
                        if (!appVersionName.equals(string)) {
                            this.c.edit().putString("pre_version", string).putString("cur_version", appVersionName).commit();
                            str = string;
                        }
                    }
                    this.d = str;
                    return str;
                }
            } catch (Throwable unused) {
            }
            str = imprintProperty;
        } catch (Throwable unused2) {
        }
        this.d = str;
        return str;
    }

    private String o() {
        String str = null;
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(a, "ud_da", (String) null);
            try {
                if (TextUtils.isEmpty(imprintProperty)) {
                    if (!TextUtils.isEmpty(this.e)) {
                        return this.e;
                    }
                    if (this.c == null) {
                        this.c = PreferenceWrapper.getDefault(a);
                    }
                    str = this.c.getString("pre_date", "");
                    if (TextUtils.isEmpty(str)) {
                        str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.c.edit().putString("pre_date", str).commit();
                    } else {
                        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (!str.equals(format)) {
                            this.c.edit().putString("pre_date", format).commit();
                            str = format;
                        }
                    }
                    this.e = str;
                    return str;
                }
            } catch (Throwable unused) {
            }
            str = imprintProperty;
        } catch (Throwable unused2) {
        }
        this.e = str;
        return str;
    }

    public void d() {
        try {
            if (this.g.length() > 0) {
                g.a(a).a(this.g);
                this.g = new JSONArray();
            }
            PreferenceWrapper.getDefault(a).edit().putLong(n, this.k).putInt(q, this.i).putInt(r, this.j).commit();
        } catch (Throwable unused) {
        }
    }

    /* compiled from: CoreProtocolImpl */
    public static class d {
        private Map<String, Object> a = null;
        private String b = null;
        private String c = null;
        private long d = 0;

        private d() {
        }

        public d(String str, Map<String, Object> map, String str2, long j) {
            this.a = map;
            this.b = str;
            this.d = j;
            this.c = str2;
        }

        public Map<String, Object> a() {
            return this.a;
        }

        public String b() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        public long d() {
            return this.d;
        }
    }

    private void e(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (2050 == jSONObject.getInt("__t")) {
                if (a(this.k, this.i)) {
                    this.i++;
                } else {
                    return;
                }
            } else if (2049 == jSONObject.getInt("__t")) {
                if (a(this.k, this.j)) {
                    this.j++;
                } else {
                    return;
                }
            }
            if (this.g.length() >= this.f) {
                g.a(a).a(this.g);
                this.g = new JSONArray();
            }
            if (this.k == 0) {
                this.k = System.currentTimeMillis();
            }
            this.g.put(jSONObject);
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    private boolean a(long j2, int i2) {
        if (j2 == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j2 > 28800000) {
            p();
            return true;
        } else if (i2 < 5000) {
            return true;
        } else {
            return false;
        }
    }

    private void p() {
        try {
            this.i = 0;
            this.j = 0;
            this.k = System.currentTimeMillis();
            PreferenceWrapper.getDefault(a).edit().putLong(o, System.currentTimeMillis()).putInt(p, 0).commit();
        } catch (Throwable unused) {
        }
    }

    private boolean c(boolean z) {
        if (t()) {
            return true;
        }
        if (this.b == null) {
            this.b = new c();
        }
        this.b.a();
        ReportPolicy.ReportStrategy c2 = this.b.c();
        MLog.d("Report policy : " + c2.getClass().getSimpleName());
        boolean shouldSendMessage = c2.shouldSendMessage(z);
        if (shouldSendMessage) {
            if (((c2 instanceof ReportPolicy.ReportByInterval) || (c2 instanceof ReportPolicy.DebugPolicy) || (c2 instanceof ReportPolicy.ReportQuasiRealtime)) && q()) {
                d();
            }
            if ((c2 instanceof ReportPolicy.DefconPolicy) && q()) {
                d();
            }
        }
        return shouldSendMessage;
    }

    private boolean q() {
        try {
            if (!TextUtils.isEmpty(q.a().b())) {
                b(a);
            }
            if (this.g.length() <= 0) {
                return false;
            }
            for (int i2 = 0; i2 < this.g.length(); i2++) {
                JSONObject optJSONObject = this.g.optJSONObject(i2);
                if (optJSONObject != null && optJSONObject.length() > 0) {
                    String optString = optJSONObject.optString("__i");
                    if (TextUtils.isEmpty(optString) || t.equals(optString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* compiled from: CoreProtocolImpl */
    public static class c {
        private ReportPolicy.ReportStrategy a;
        private int b;
        private int c;
        private int d;
        private int e;
        private ABTest f;

        public c() {
            this.a = null;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            this.f = null;
            this.f = ABTest.getService(k.a);
        }

        public void a() {
            try {
                int[] a2 = a(-1, -1);
                this.b = a2[0];
                this.c = a2[1];
            } catch (Throwable unused) {
            }
        }

        public int[] a(int i, int i2) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "report_policy", k.t)).intValue();
            int intValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "report_interval", k.t)).intValue();
            if (intValue == -1 || !ReportPolicy.isValid(intValue)) {
                return new int[]{i, i2};
            } else if (6 == intValue) {
                int i3 = 90;
                if (intValue2 != -1 && intValue2 >= 90 && intValue2 <= 86400) {
                    i3 = intValue2;
                }
                return new int[]{intValue, i3 * SocializeConstants.CANCLE_RESULTCODE};
            } else if (11 == intValue) {
                int i4 = 15;
                if (intValue2 != -1 && intValue2 >= 15 && intValue2 <= 3600) {
                    i4 = intValue2;
                }
                return new int[]{intValue, i4 * SocializeConstants.CANCLE_RESULTCODE};
            } else {
                return new int[]{i, i2};
            }
        }

        public int a(int i) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue();
            return (intValue == -1 || intValue < 90 || intValue > 86400) ? i : intValue * SocializeConstants.CANCLE_RESULTCODE;
        }

        /* access modifiers changed from: protected */
        public void b() {
            int i;
            Defcon service = Defcon.getService(k.a);
            if (service.isOpen()) {
                this.a = (this.a instanceof ReportPolicy.DefconPolicy) && this.a.isValid() ? this.a : new ReportPolicy.DefconPolicy(StatTracer.getInstance(k.a), service);
            } else {
                boolean z = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "integrated_test", k.t)).intValue() == 1;
                if (UMConfigure.isDebugLog() && z && !MLog.DEBUG) {
                    UMLog.mutlInfo(h.J, 3, "\\|", (String[]) null, (String[]) null);
                }
                if (MLog.DEBUG && z) {
                    this.a = new ReportPolicy.DebugPolicy(StatTracer.getInstance(k.a));
                } else if (!this.f.isInTest() || !"RPT".equals(this.f.getTestName())) {
                    int i2 = this.d;
                    int i3 = this.e;
                    if (this.b != -1) {
                        i2 = this.b;
                        i3 = this.c;
                    }
                    this.a = b(i2, i3);
                } else {
                    if (this.f.getTestPolicy() == 6) {
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue() != -1) {
                            i = a(90000);
                        } else {
                            i = this.c > 0 ? this.c : this.e;
                        }
                    } else {
                        i = 0;
                    }
                    this.a = b(this.f.getTestPolicy(), i);
                }
            }
            if (UMConfigure.isDebugLog()) {
                try {
                    if (this.a instanceof ReportPolicy.ReportAtLaunch) {
                        UMLog.mutlInfo(h.H, 3, "", (String[]) null, (String[]) null);
                    } else if (this.a instanceof ReportPolicy.ReportByInterval) {
                        UMLog.mutlInfo(h.I, 3, "", new String[]{"@"}, new String[]{String.valueOf(((ReportPolicy.ReportByInterval) this.a).getReportInterval() / 1000)});
                    } else if (this.a instanceof ReportPolicy.DebugPolicy) {
                        UMLog.mutlInfo(h.K, 3, "", (String[]) null, (String[]) null);
                    } else if (this.a instanceof ReportPolicy.ReportQuasiRealtime) {
                        String[] strArr = {String.valueOf(((ReportPolicy.ReportQuasiRealtime) this.a).getReportInterval() / 1000)};
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(h.L, 3, "", new String[]{"@"}, strArr);
                    } else {
                        boolean z2 = this.a instanceof ReportPolicy.DefconPolicy;
                    }
                } catch (Throwable unused) {
                }
            }
        }

        public ReportPolicy.ReportStrategy c() {
            b();
            return this.a;
        }

        private ReportPolicy.ReportStrategy b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.a instanceof ReportPolicy.ReportRealtime ? this.a : new ReportPolicy.ReportRealtime();
                case 1:
                    return this.a instanceof ReportPolicy.ReportAtLaunch ? this.a : new ReportPolicy.ReportAtLaunch();
                case 4:
                    if (this.a instanceof ReportPolicy.ReportDaily) {
                        return this.a;
                    }
                    return new ReportPolicy.ReportDaily(StatTracer.getInstance(k.a));
                case 5:
                    if (this.a instanceof ReportPolicy.ReportWifiOnly) {
                        return this.a;
                    }
                    return new ReportPolicy.ReportWifiOnly(k.a);
                case 6:
                    if (!(this.a instanceof ReportPolicy.ReportByInterval)) {
                        return new ReportPolicy.ReportByInterval(StatTracer.getInstance(k.a), (long) i2);
                    }
                    ReportPolicy.ReportStrategy reportStrategy = this.a;
                    ((ReportPolicy.ReportByInterval) reportStrategy).setReportInterval((long) i2);
                    return reportStrategy;
                case 8:
                    if (this.a instanceof ReportPolicy.SmartPolicy) {
                        return this.a;
                    }
                    return new ReportPolicy.SmartPolicy(StatTracer.getInstance(k.a));
                case 11:
                    if (this.a instanceof ReportPolicy.ReportQuasiRealtime) {
                        ReportPolicy.ReportStrategy reportStrategy2 = this.a;
                        ((ReportPolicy.ReportQuasiRealtime) reportStrategy2).setReportInterval((long) i2);
                        return reportStrategy2;
                    }
                    ReportPolicy.ReportQuasiRealtime reportQuasiRealtime = new ReportPolicy.ReportQuasiRealtime();
                    reportQuasiRealtime.setReportInterval((long) i2);
                    return reportQuasiRealtime;
                default:
                    if (this.a instanceof ReportPolicy.ReportAtLaunch) {
                        return this.a;
                    }
                    return new ReportPolicy.ReportAtLaunch();
            }
        }
    }

    private void c(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject.has("analytics")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("analytics");
                        if (jSONObject4.has(b.R)) {
                            jSONObject3.put(b.R, jSONObject4.getJSONArray(b.R));
                        }
                        if (jSONObject4.has(b.S)) {
                            jSONObject3.put(b.S, jSONObject4.getJSONArray(b.S));
                        }
                        if (jSONObject4.has("error")) {
                            jSONObject3.put("error", jSONObject4.getJSONArray("error"));
                        }
                        if (jSONObject4.has(b.n)) {
                            JSONArray jSONArray = jSONObject4.getJSONArray(b.n);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i2);
                                if (jSONObject5 != null && jSONObject5.length() > 0) {
                                    if (jSONObject5.has(b.t)) {
                                        jSONObject5.remove(b.t);
                                    }
                                    jSONArray2.put(jSONObject5);
                                }
                            }
                            jSONObject3.put(b.n, jSONArray2);
                        }
                        if (jSONObject4.has(b.H)) {
                            jSONObject3.put(b.H, jSONObject4.getJSONObject(b.H));
                        }
                        if (jSONObject4.has(b.K)) {
                            jSONObject3.put(b.K, jSONObject4.getJSONObject(b.K));
                        }
                    }
                    if (jSONObject.has("dplus")) {
                        jSONObject3.put("dplus", jSONObject.getJSONObject("dplus"));
                    }
                    if (jSONObject.has("header") && jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null && jSONObject2.length() > 0) {
                        if (jSONObject2.has("sdk_version")) {
                            jSONObject3.put("sdk_version", jSONObject2.getString("sdk_version"));
                        }
                        if (jSONObject2.has("device_id")) {
                            jSONObject3.put("device_id", jSONObject2.getString("device_id"));
                        }
                        if (jSONObject2.has("device_model")) {
                            jSONObject3.put("device_model", jSONObject2.getString("device_model"));
                        }
                        if (jSONObject2.has("version_code")) {
                            jSONObject3.put("version", jSONObject2.getInt("version_code"));
                        }
                        if (jSONObject2.has("appkey")) {
                            jSONObject3.put("appkey", jSONObject2.getString("appkey"));
                        }
                        if (jSONObject2.has("channel")) {
                            jSONObject3.put("channel", jSONObject2.getString("channel"));
                        }
                    }
                    if (jSONObject3.length() > 0) {
                        MLog.d("constructMessage:" + jSONObject3.toString());
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "constructMessage: " + jSONObject3.toString());
                    }
                }
            } catch (Throwable th) {
                MLog.e(th);
            }
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() <= 0) {
                    return;
                }
                if (!jSONObject.has("exception")) {
                    e(jSONObject);
                } else if (101 != jSONObject.getInt("exception")) {
                    e(jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() <= 0) {
                    return;
                }
                if (!jSONObject.has("exception")) {
                    d(jSONObject);
                } else if (101 != jSONObject.getInt("exception")) {
                    d(jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void d(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        try {
            if (jSONObject.getJSONObject("header").has(b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(b.n) && (optJSONObject2 = jSONObject2.getJSONArray(b.n).optJSONObject(0)) != null) {
                        String optString = optJSONObject2.optString("id");
                        if (!TextUtils.isEmpty(optString)) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: really delete instant session data");
                            g.a(a).b(optString);
                        }
                    }
                }
                g.a(a).b();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: send INSTANT_SESSION_START_CONTINUE event because OVERSIZE.");
                UMWorkDispatch.sendEvent(a, a.l, CoreProtocol.getInstance(a), (Object) null);
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0 && optJSONObject.has(b.n)) {
                g.a(a).a(true, false);
            }
            g.a(a).b();
        } catch (Exception unused) {
        }
    }

    private void e(JSONObject jSONObject) {
        JSONObject optJSONObject;
        try {
            if (jSONObject.getJSONObject("header").has(b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (!jSONObject.has("analytics")) {
                    return;
                }
                if (jSONObject.getJSONObject("analytics").has(b.n)) {
                    g.a(a).i();
                    g.a(a).h();
                    g.a(a).b(true, false);
                    g.a(a).a();
                    return;
                }
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Error, Should not go to this branch.");
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (optJSONObject = jSONObject.optJSONObject("analytics")) != null && optJSONObject.length() > 0) {
                if (optJSONObject.has(b.n)) {
                    g.a(a).b(true, false);
                }
                if (optJSONObject.has(b.R) || optJSONObject.has(b.S)) {
                    g.a(a).h();
                }
                if (optJSONObject.has("error")) {
                    g.a(a).i();
                }
            }
            g.a(a).a();
        } catch (Exception unused) {
        }
    }

    public void c(Object obj) {
        b(a);
        d();
        if (d(false)) {
            k();
        }
    }

    public void b(Context context) {
        try {
            g.a(context).d();
            r();
        } catch (Throwable unused) {
        }
    }

    public void e() {
        if (d(false)) {
            k();
        }
    }

    public void d(Object obj) {
        s();
        n();
        o();
        a(true);
    }

    private boolean d(boolean z) {
        if (this.b == null) {
            this.b = new c();
        }
        ReportPolicy.ReportStrategy c2 = this.b.c();
        if (!(c2 instanceof ReportPolicy.DefconPolicy)) {
            return true;
        }
        if (z) {
            return ((ReportPolicy.DefconPolicy) c2).shouldSendMessageByInstant();
        }
        return c2.shouldSendMessage(false);
    }

    public void a(Object obj, boolean z) {
        if (z) {
            if (d(true)) {
                j();
            }
        } else if (UMEnvelopeBuild.isOnline(a) && d(true)) {
            j();
        }
    }

    private void r() {
        if (this.g.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            while (i2 < this.g.length()) {
                try {
                    JSONObject jSONObject = this.g.getJSONObject(i2);
                    if (jSONObject == null || jSONObject.length() <= 0) {
                        jSONArray.put(jSONObject);
                        i2++;
                    } else {
                        String optString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(optString) || t.equals(optString)) {
                            String b2 = q.a().b();
                            if (TextUtils.isEmpty(b2)) {
                                b2 = t;
                            }
                            jSONObject.put("__i", b2);
                        }
                        jSONArray.put(jSONObject);
                        i2++;
                    }
                } catch (Throwable unused) {
                }
            }
            this.g = jSONArray;
        }
    }

    private void s() {
        SharedPreferences sharedPreferences;
        try {
            if (t() && a != null && (sharedPreferences = PreferenceWrapper.getDefault(a)) != null && sharedPreferences.getLong(l, 0) == 0) {
                sharedPreferences.edit().putLong(l, System.currentTimeMillis()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    public long f() {
        SharedPreferences sharedPreferences;
        long j2 = 0;
        try {
            if (a == null || (sharedPreferences = PreferenceWrapper.getDefault(a)) == null) {
                return 0;
            }
            long j3 = sharedPreferences.getLong(l, 0);
            if (j3 == 0) {
                try {
                    j2 = System.currentTimeMillis();
                    sharedPreferences.edit().putLong(l, j2).commit();
                    return j2;
                } catch (Throwable unused) {
                }
            }
            return j3;
        } catch (Throwable unused2) {
            return j2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(a);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean t() {
        /*
            r5 = this;
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x001c }
            if (r0 == 0) goto L_0x001c
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x001c }
            android.content.SharedPreferences r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r0)     // Catch:{ Throwable -> 0x001c }
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = "ana_is_f"
            r2 = -1
            long r0 = r0.getLong(r1, r2)     // Catch:{ Throwable -> 0x001c }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x001c
            r0 = 1
            return r0
        L_0x001c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.k.t():boolean");
    }

    private void f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                long j2 = jSONObject.getLong("ts");
                b(a);
                d();
                String[] a2 = com.umeng.analytics.c.a(a);
                if (a2 != null && !TextUtils.isEmpty(a2[0]) && !TextUtils.isEmpty(a2[1])) {
                    q.a().a(a, j2);
                    String c2 = u.a().c(a);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c2);
                    boolean b2 = q.a().b(a, j2);
                    com.umeng.analytics.c.b(a);
                    q.a().a(a, j2, true);
                    if (b2) {
                        q.a().c(a, j2);
                    }
                }
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    private void g(Object obj) {
        try {
            b(a);
            d();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                String string = jSONObject.getString(b.L);
                String string2 = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID);
                long j2 = jSONObject.getLong("ts");
                String[] a2 = com.umeng.analytics.c.a(a);
                if (a2 == null || !string.equals(a2[0]) || !string2.equals(a2[1])) {
                    q.a().a(a, j2);
                    String c2 = u.a().c(a);
                    boolean b2 = q.a().b(a, j2);
                    com.umeng.analytics.c.a(a, string, string2);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + c2);
                    q.a().a(a, j2, true);
                    if (b2) {
                        q.a().c(a, j2);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void h(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0 && jSONObject.has("__ii")) {
                String optString = jSONObject.optString("__ii");
                jSONObject.remove("__ii");
                if (!TextUtils.isEmpty(optString)) {
                    g.a(a).a(optString, obj.toString(), 2);
                }
            }
        } catch (Throwable unused) {
        }
    }
}
