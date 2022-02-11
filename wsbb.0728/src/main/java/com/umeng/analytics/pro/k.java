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
import com.umeng.commonsdk.utils.JSONArraySortUtil;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

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

    private static class b {
        /* access modifiers changed from: private */
        public static final k a = new k();

        private b() {
        }
    }

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

        private ReportPolicy.ReportStrategy b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.a instanceof ReportPolicy.ReportRealtime ? this.a : new ReportPolicy.ReportRealtime();
                case 1:
                    return this.a instanceof ReportPolicy.ReportAtLaunch ? this.a : new ReportPolicy.ReportAtLaunch();
                case 4:
                    return this.a instanceof ReportPolicy.ReportDaily ? this.a : new ReportPolicy.ReportDaily(StatTracer.getInstance(k.a));
                case 5:
                    return this.a instanceof ReportPolicy.ReportWifiOnly ? this.a : new ReportPolicy.ReportWifiOnly(k.a);
                case 6:
                    if (!(this.a instanceof ReportPolicy.ReportByInterval)) {
                        return new ReportPolicy.ReportByInterval(StatTracer.getInstance(k.a), (long) i2);
                    }
                    ReportPolicy.ReportStrategy reportStrategy = this.a;
                    ((ReportPolicy.ReportByInterval) reportStrategy).setReportInterval((long) i2);
                    return reportStrategy;
                case 8:
                    return this.a instanceof ReportPolicy.SmartPolicy ? this.a : new ReportPolicy.SmartPolicy(StatTracer.getInstance(k.a));
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
                    return this.a instanceof ReportPolicy.ReportAtLaunch ? this.a : new ReportPolicy.ReportAtLaunch();
            }
        }

        public int a(int i) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue();
            return (intValue == -1 || intValue < 90 || intValue > 86400) ? i : intValue * SocializeConstants.CANCLE_RESULTCODE;
        }

        public void a() {
            try {
                int[] a2 = a(-1, -1);
                this.b = a2[0];
                this.c = a2[1];
            } catch (Throwable th) {
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
                if (intValue2 == -1 || intValue2 < 15) {
                    intValue2 = 15;
                } else if (intValue2 > 3600) {
                    intValue2 = 15;
                }
                return new int[]{intValue, intValue2 * SocializeConstants.CANCLE_RESULTCODE};
            } else {
                return new int[]{i, i2};
            }
        }

        /* access modifiers changed from: protected */
        public void b() {
            boolean z = true;
            int i = 0;
            Defcon service = Defcon.getService(k.a);
            if (service.isOpen()) {
                if (!(this.a instanceof ReportPolicy.DefconPolicy) || !this.a.isValid()) {
                    z = false;
                }
                this.a = z ? this.a : new ReportPolicy.DefconPolicy(StatTracer.getInstance(k.a), service);
            } else {
                boolean z2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "integrated_test", k.t)).intValue() == 1;
                if (UMConfigure.isDebugLog() && z2 && !MLog.DEBUG) {
                    UMLog.mutlInfo(h.J, 3, "\\|", (String[]) null, (String[]) null);
                }
                if (MLog.DEBUG && z2) {
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
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.a, "test_report_interval", k.t)).intValue() == -1) {
                            z = false;
                        }
                        i = z ? a(90000) : this.c > 0 ? this.c : this.e;
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
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(h.L, 3, "", new String[]{"@"}, new String[]{String.valueOf(((ReportPolicy.ReportQuasiRealtime) this.a).getReportInterval() / 1000)});
                    } else {
                        boolean z3 = this.a instanceof ReportPolicy.DefconPolicy;
                    }
                } catch (Throwable th) {
                }
            }
        }

        public ReportPolicy.ReportStrategy c() {
            b();
            return this.a;
        }
    }

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
        } catch (Throwable th) {
        }
    }

    public static k a(Context context) {
        if (a == null && context != null) {
            a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return b.a;
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
        } catch (Throwable th) {
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
            } catch (Throwable th) {
            }
        }
    }

    private boolean a(long j2, int i2) {
        if (j2 == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j2 <= 28800000) {
            return i2 < 5000;
        }
        p();
        return true;
    }

    private JSONObject b(JSONObject jSONObject, long j2) {
        try {
            if (m.a(jSONObject) <= j2) {
                return jSONObject;
            }
            try {
                g.a(a).a(true, false);
                g.a(a).b();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Instant session packet overload !!! ");
                return null;
            } catch (Throwable th) {
                return null;
            }
        } catch (Throwable th2) {
            return jSONObject;
        }
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
                } catch (Throwable th) {
                }
            }
        } else {
            jSONObject.put("app_version", UMUtils.getAppVersionName(a));
            jSONObject.put("version_code", UMUtils.getAppVersionCode(a));
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
        if (!shouldSendMessage) {
            return shouldSendMessage;
        }
        if (((c2 instanceof ReportPolicy.ReportByInterval) || (c2 instanceof ReportPolicy.DebugPolicy) || (c2 instanceof ReportPolicy.ReportQuasiRealtime)) && q()) {
            d();
        }
        if (!(c2 instanceof ReportPolicy.DefconPolicy) || !q()) {
            return shouldSendMessage;
        }
        d();
        return shouldSendMessage;
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
        } catch (Exception e2) {
        }
    }

    private boolean d(boolean z) {
        if (this.b == null) {
            this.b = new c();
        }
        ReportPolicy.ReportStrategy c2 = this.b.c();
        if (c2 instanceof ReportPolicy.DefconPolicy) {
            return z ? ((ReportPolicy.DefconPolicy) c2).shouldSendMessageByInstant() : c2.shouldSendMessage(false);
        }
        return true;
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
        } catch (Exception e2) {
        }
    }

    private void f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
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
            if (jSONObject != null && jSONObject.length() > 0) {
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
        } catch (Throwable th) {
        }
    }

    private void h() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("loadNativeData", new Class[]{Context.class}).invoke((Object) null, new Object[]{a});
        } catch (Exception e2) {
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
        } catch (Throwable th) {
        }
    }

    private void i() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("processCommond", new Class[]{Context.class, String.class}).invoke((Object) null, new Object[]{a, AnalyticsConfig.getAppkey(a)});
        } catch (Exception e2) {
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
                    } catch (Throwable th) {
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
                } catch (Throwable th) {
                }
                c(buildEnvelopeWithExtHeader);
                a((Object) buildEnvelopeWithExtHeader);
            }
        }
    }

    private JSONObject l() {
        JSONObject m2 = m();
        if (m2 != null) {
            try {
                m2.put("st", "1");
            } catch (Throwable th) {
            }
        }
        return m2;
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
                    String string = this.c.getString("pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(a);
                    if (TextUtils.isEmpty(string)) {
                        this.c.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                        str = "0";
                    } else {
                        str = this.c.getString("cur_version", "");
                        if (!appVersionName.equals(str)) {
                            this.c.edit().putString("pre_version", str).putString("cur_version", appVersionName).commit();
                        } else {
                            str = string;
                        }
                    }
                    this.d = str;
                    return str;
                }
            } catch (Throwable th) {
            }
            str = imprintProperty;
        } catch (Throwable th2) {
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
                    String string = this.c.getString("pre_date", "");
                    if (TextUtils.isEmpty(string)) {
                        str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.c.edit().putString("pre_date", str).commit();
                    } else {
                        str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (!string.equals(str)) {
                            this.c.edit().putString("pre_date", str).commit();
                        } else {
                            str = string;
                        }
                    }
                    this.e = str;
                    return str;
                }
            } catch (Throwable th) {
            }
            str = imprintProperty;
        } catch (Throwable th2) {
        }
        this.e = str;
        return str;
    }

    private void p() {
        try {
            this.i = 0;
            this.j = 0;
            this.k = System.currentTimeMillis();
            PreferenceWrapper.getDefault(a).edit().putLong(o, System.currentTimeMillis()).putInt(p, 0).commit();
        } catch (Throwable th) {
        }
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
        } catch (Throwable th) {
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
                } catch (Throwable th) {
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
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(a);
     */
    private boolean t() {
        SharedPreferences sharedPreferences;
        try {
            return (a == null || sharedPreferences == null || sharedPreferences.getLong(m, -1) == 0) ? false : true;
        } catch (Throwable th) {
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
            } else if (b2 != null) {
                if (b2.length() > 0) {
                    jSONObject2.put("analytics", b2);
                }
            }
            if (m2 != null && m2.length() > 0) {
                jSONObject.put("header", m2);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("content", jSONObject2);
            }
            return a(jSONObject, j2);
        } catch (Throwable th) {
            return jSONObject;
        }
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
            } catch (Throwable th) {
            }
        }
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
                    } catch (Throwable th) {
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

    public void a(Object obj, boolean z) {
        if (z) {
            if (d(true)) {
                j();
            }
        } else if (UMEnvelopeBuild.isOnline(a) && d(true)) {
            j();
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
            } catch (Throwable th) {
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
            } catch (Throwable th2) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        com.umeng.commonsdk.statistics.common.MLog.e("merge pages error");
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0137, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x000d, B:26:0x00a0] */
    public JSONObject b(boolean z) {
        JSONObject jSONObject;
        try {
            JSONObject a2 = g.a(a).a(z);
            if (a2 == null) {
                jSONObject = new JSONObject();
            } else if (a2.has(b.n)) {
                JSONArray jSONArray = a2.getJSONArray(b.n);
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    JSONArray optJSONArray = jSONObject2.optJSONArray(b.s);
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray(b.t);
                    if (optJSONArray == null && optJSONArray2 != null) {
                        jSONObject2.put(b.s, optJSONArray2);
                        jSONObject2.remove(b.t);
                    }
                    if (!(optJSONArray == null || optJSONArray2 == null)) {
                        ArrayList<JSONObject> arrayList = new ArrayList<>();
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            arrayList.add((JSONObject) optJSONArray.get(i3));
                        }
                        for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                            arrayList.add((JSONObject) optJSONArray2.get(i4));
                        }
                        JSONArraySortUtil jSONArraySortUtil = new JSONArraySortUtil();
                        jSONArraySortUtil.setCompareKey(b.w);
                        Collections.sort(arrayList, jSONArraySortUtil);
                        JSONArray jSONArray3 = new JSONArray();
                        for (JSONObject put : arrayList) {
                            jSONArray3.put(put);
                        }
                        jSONObject2.put(b.s, jSONArray3);
                        jSONObject2.remove(b.t);
                    }
                    if (jSONObject2.has(b.s)) {
                        JSONArray optJSONArray3 = jSONObject2.optJSONArray(b.s);
                        for (int i5 = 0; i5 < optJSONArray3.length(); i5++) {
                            JSONObject jSONObject3 = optJSONArray3.getJSONObject(i5);
                            if (jSONObject3.has(b.w)) {
                                jSONObject3.put("ts", jSONObject3.getLong(b.w));
                                jSONObject3.remove(b.w);
                            }
                        }
                        jSONObject2.put(b.s, optJSONArray3);
                        jSONObject2.put(b.y, optJSONArray3.length());
                    } else {
                        jSONObject2.put(b.y, 0);
                    }
                    jSONArray2.put(jSONObject2);
                }
                a2.put(b.n, jSONArray2);
                jSONObject = a2;
            } else {
                jSONObject = a2;
            }
            try {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (sharedPreferences != null) {
                    String string = sharedPreferences.getString("userlevel", "");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put("userlevel", string);
                    }
                }
                String[] a3 = com.umeng.analytics.c.a(a);
                if (a3 != null && !TextUtils.isEmpty(a3[0]) && !TextUtils.isEmpty(a3[1])) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put(b.L, a3[0]);
                    jSONObject4.put(b.M, a3[1]);
                    if (jSONObject4.length() > 0) {
                        jSONObject.put(b.K, jSONObject4);
                    }
                }
                if (ABTest.getService(a).isInTest()) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put(ABTest.getService(a).getTestName(), ABTest.getService(a).getGroupInfo());
                    jSONObject.put(b.J, jSONObject5);
                }
                n.a().a(jSONObject, a);
                return jSONObject;
            } catch (Throwable th) {
                return jSONObject;
            }
        } catch (Throwable th2) {
            return null;
        }
    }

    public void b() {
    }

    public void b(Context context) {
        try {
            g.a(context).d();
            r();
        } catch (Throwable th) {
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
            } catch (Throwable th) {
            }
        }
    }

    public void c() {
        b(a);
        d();
        a(true);
    }

    public void c(Object obj) {
        b(a);
        d();
        if (d(false)) {
            k();
        }
    }

    public void d() {
        try {
            if (this.g.length() > 0) {
                g.a(a).a(this.g);
                this.g = new JSONArray();
            }
            PreferenceWrapper.getDefault(a).edit().putLong(n, this.k).putInt(q, this.i).putInt(r, this.j).commit();
        } catch (Throwable th) {
        }
    }

    public void d(Object obj) {
        s();
        n();
        o();
        a(true);
    }

    public void e() {
        if (d(false)) {
            k();
        }
    }

    public long f() {
        SharedPreferences sharedPreferences;
        long j2;
        try {
            if (a == null || (sharedPreferences = PreferenceWrapper.getDefault(a)) == null) {
                return 0;
            }
            long j3 = sharedPreferences.getLong(l, 0);
            if (j3 != 0) {
                return j3;
            }
            try {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    sharedPreferences.edit().putLong(l, currentTimeMillis).commit();
                    return currentTimeMillis;
                } catch (Throwable th) {
                    return currentTimeMillis;
                }
            } catch (Throwable th2) {
                return j2;
            }
        } catch (Throwable th3) {
            return 0;
        }
    }
}
