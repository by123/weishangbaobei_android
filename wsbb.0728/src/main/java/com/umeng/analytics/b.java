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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b();

        private a() {
        }
    }

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

    public static b a() {
        return a.a;
    }

    private void a(String str, Object obj) {
        int i2 = 0;
        try {
            if (this.k == null) {
                this.k = new JSONObject();
            }
            new JSONObject();
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    if (strArr.length <= 10) {
                        JSONArray jSONArray = new JSONArray();
                        while (i2 < strArr.length) {
                            if (strArr[i2] != null) {
                                if (!HelperUtils.checkStrLen(strArr[i2], 256)) {
                                    jSONArray.put(strArr[i2]);
                                }
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
        } catch (Throwable th) {
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

    private boolean b(String str, Object obj) {
        int i2;
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.e("key is " + str + ", please check key, illegal");
                return false;
            }
            i2 = str.getBytes("UTF-8").length;
            if (i2 > 128) {
                MLog.e("key length is " + i2 + ", please check key, illegal");
                return false;
            } else if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= 256) {
                    return true;
                }
                MLog.e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
                return false;
            } else if (obj instanceof Integer) {
                return true;
            } else {
                if (obj instanceof Long) {
                    return true;
                }
                if (obj instanceof Double) {
                    return true;
                }
                if (obj instanceof Float) {
                    return true;
                }
                MLog.e("value is " + obj + ", please check value, type illegal");
                return false;
            }
        } catch (UnsupportedEncodingException e2) {
            i2 = 0;
        } catch (Throwable th) {
            return false;
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

    private void i(Context context) {
        if (context == null) {
            try {
                MLog.e("unexpected null context in getNativeSuperProperties");
            } catch (Throwable th) {
            }
        } else {
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (this.k == null) {
                this.k = new JSONObject();
            }
            if (this.l == null) {
                this.l = new JSONObject();
            }
            String string = sharedPreferences.getString(i, (String) null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.m = new JSONObject(string);
                } catch (JSONException e2) {
                }
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
        }
    }

    private void j(Context context) {
        try {
            Class.forName("com.umeng.visual.UMVisualAgent");
        } catch (ClassNotFoundException e2) {
            if (Build.VERSION.SDK_INT > 13) {
                UMWorkDispatch.sendEvent(context, k.a.s, CoreProtocol.getInstance(context), Long.valueOf(System.currentTimeMillis()));
            }
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
    public void a(long j2) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setSessionContinueMillis can not be called in child process");
            return;
        }
        AnalyticsConfig.kContinueSessionMillis = j2;
        u.a().a(AnalyticsConfig.kContinueSessionMillis);
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
            } catch (Throwable th) {
            }
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

    public void a(Context context, String str, Object obj) {
        int i2 = 0;
        synchronized (this) {
            if (context == null) {
                UMLog.aq(h.ae, 0, "\\|");
                return;
            }
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("registerSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (TextUtils.isEmpty(str) || obj == null) {
                UMLog.aq(h.af, 0, "\\|");
                return;
            } else if (str.equals(y) || str.equals(z) || str.equals(A) || str.equals(B) || str.equals(C)) {
                try {
                    if (!(obj instanceof String) || HelperUtils.checkStrLen(obj.toString(), 256)) {
                        try {
                            if (this.k == null) {
                                this.k = new JSONObject();
                            }
                            new JSONObject();
                            if (obj.getClass().isArray()) {
                                if (obj instanceof String[]) {
                                    String[] strArr = (String[]) obj;
                                    if (strArr.length > 10) {
                                        MLog.e("please check value, size is " + strArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray = new JSONArray();
                                    while (i2 < strArr.length) {
                                        if (strArr[i2] != null) {
                                            if (HelperUtils.checkStrLen(strArr[i2], 256)) {
                                                jSONArray.put(strArr[i2]);
                                                i2++;
                                            }
                                        }
                                        MLog.e("please check value, length is " + strArr[i2].length() + ", overlength 256!");
                                        return;
                                    }
                                    this.k.put(str, jSONArray);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else if (obj instanceof long[]) {
                                    long[] jArr = (long[]) obj;
                                    if (jArr.length > 10) {
                                        MLog.e("please check value, size is " + jArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i2 < jArr.length) {
                                        jSONArray2.put(jArr[i2]);
                                        i2++;
                                    }
                                    this.k.put(str, jSONArray2);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else if (obj instanceof int[]) {
                                    int[] iArr = (int[]) obj;
                                    if (iArr.length > 10) {
                                        MLog.e("please check value, size is " + iArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i2 < iArr.length) {
                                        jSONArray3.put(iArr[i2]);
                                        i2++;
                                    }
                                    this.k.put(str, jSONArray3);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else if (obj instanceof float[]) {
                                    float[] fArr = (float[]) obj;
                                    if (fArr.length > 10) {
                                        MLog.e("please check value, size is " + fArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i2 < fArr.length) {
                                        jSONArray4.put((double) fArr[i2]);
                                        i2++;
                                    }
                                    this.k.put(str, jSONArray4);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else if (obj instanceof double[]) {
                                    double[] dArr = (double[]) obj;
                                    if (dArr.length > 10) {
                                        MLog.e("please check value, size is " + dArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i2 < dArr.length) {
                                        jSONArray5.put(dArr[i2]);
                                        i2++;
                                    }
                                    this.k.put(str, jSONArray5);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else if (obj instanceof short[]) {
                                    short[] sArr = (short[]) obj;
                                    if (sArr.length > 10) {
                                        MLog.e("please check value, size is " + sArr.length + ", overstep 10!");
                                        return;
                                    }
                                    JSONArray jSONArray6 = new JSONArray();
                                    while (i2 < sArr.length) {
                                        jSONArray6.put(sArr[i2]);
                                        i2++;
                                    }
                                    this.k.put(str, jSONArray6);
                                    UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                                } else {
                                    MLog.e("please check value, illegal type!");
                                    return;
                                }
                            } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                this.k.put(str, obj);
                                UMWorkDispatch.sendEvent(this.a, k.a.p, CoreProtocol.getInstance(this.a), this.k.toString());
                            } else {
                                MLog.e("please check value, illegal type!");
                                return;
                            }
                        } catch (Throwable th) {
                        }
                    } else {
                        MLog.e("property value is " + obj + ", please check value, lawless!");
                        return;
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            } else {
                MLog.e("property name is " + str + ", please check key, must be correct!");
                return;
            }
        }
        return;
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

    public void a(Context context, List<String> list) {
        synchronized (this) {
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
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return;
     */
    public void a(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2;
        synchronized (this) {
            if (context == null) {
                UMLog.aq(h.ak, 0, "\\|");
                return;
            }
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("registerPreProperties can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
            if (jSONObject == null || jSONObject.length() <= 0) {
                UMLog.aq(h.al, 0, "\\|");
                return;
            }
            try {
                jSONObject2 = new JSONObject(this.m.toString());
            } catch (Exception e2) {
                jSONObject2 = null;
            }
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            Iterator<String> keys = jSONObject.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    try {
                        String obj = keys.next().toString();
                        Object obj2 = jSONObject.get(obj);
                        if (b(obj, obj2)) {
                            jSONObject2.put(obj, obj2);
                            if (jSONObject2.length() > 10) {
                                MLog.e("please check propertics, size overlength!");
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Exception e3) {
                    }
                }
            }
            this.m = jSONObject2;
            if (this.m.length() > 0) {
                UMWorkDispatch.sendEvent(this.a, k.a.t, CoreProtocol.getInstance(this.a), this.m.toString());
            }
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

    public void a(x xVar) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
            MLog.e("setSysListener can not be called in child process");
        } else {
            this.b = xVar;
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public void a(Object obj) {
        synchronized (this) {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("registerSuperPropertyByCoreProtocol can not be called in child process");
            } else if (obj != null) {
                if (this.a != null) {
                    String str = (String) obj;
                    SharedPreferences.Editor edit = PreferenceWrapper.getDefault(this.a).edit();
                    if (edit != null && !TextUtils.isEmpty(str)) {
                        edit.putString(h, this.k.toString()).commit();
                    }
                }
            }
        }
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
        } catch (Throwable th) {
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
    public void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
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

    public JSONObject b() {
        return this.k;
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

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public void b(Object obj) {
        synchronized (this) {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("updateNativePrePropertiesByCoreProtocol can not be called in child process");
                return;
            }
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(this.a).edit();
            if (obj != null) {
                String str = (String) obj;
                if (edit != null && !TextUtils.isEmpty(str)) {
                    edit.putString(i, str).commit();
                }
            } else if (edit != null) {
                edit.remove(i).commit();
            }
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
        } catch (Throwable th) {
        }
    }

    public JSONObject c() {
        return this.m;
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

    public JSONObject d() {
        return this.l;
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
            } catch (Throwable th) {
            }
        }
    }

    public void d(Context context, String str) {
        synchronized (this) {
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
        }
    }

    public Object e(Context context, String str) {
        Object obj = null;
        synchronized (this) {
            if (context == null) {
                try {
                    UMLog.aq(h.ah, 0, "\\|");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("getSuperProperty can not be called in child process");
                } else if (TextUtils.isEmpty(str)) {
                    UMLog.aq(h.af, 0, "\\|");
                } else if (!str.equals(y) && !str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C)) {
                    MLog.e("please check key or value, must be correct!");
                } else if (this.k == null) {
                    this.k = new JSONObject();
                } else if (this.k.has(str)) {
                    obj = this.k.opt(str);
                }
            }
        }
        return obj;
    }

    public String e(Context context) {
        String str = null;
        synchronized (this) {
            if (context == null) {
                try {
                    UMLog.aq(h.ah, 0, "\\|");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("getSuperProperties can not be called in child process");
                } else if (this.k != null) {
                    str = this.k.toString();
                } else {
                    this.k = new JSONObject();
                }
            }
        }
        return str;
    }

    public void e() {
        this.l = null;
    }

    public String f() {
        if (UMGlobalContext.getInstance().isMainProcess(this.a)) {
            return q;
        }
        MLog.e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    public void f(Context context) {
        synchronized (this) {
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
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return;
     */
    public void f(Context context, String str) {
        synchronized (this) {
            if (context == null) {
                UMLog.aq(h.am, 0, "\\|");
                return;
            }
            if (this.a == null) {
                this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("unregisterPreProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.a);
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
            if (str == null || str.length() <= 0) {
                MLog.e("please check propertics, property is null!");
            } else if (this.m.has(str)) {
                this.m.remove(str);
                UMWorkDispatch.sendEvent(this.a, k.a.u, CoreProtocol.getInstance(this.a), this.m.toString());
            } else if (UMConfigure.isDebugLog()) {
                UMLog.aq(h.an, 0, "\\|");
            }
        }
    }

    public String g() {
        if (UMGlobalContext.getInstance().isMainProcess(this.a)) {
            return r;
        }
        MLog.e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    public void g(Context context) {
        synchronized (this) {
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
    }

    public JSONObject h(Context context) {
        JSONObject jSONObject = null;
        synchronized (this) {
            if (context == null) {
                UMLog.aq(h.ap, 0, "\\|");
            } else {
                if (this.a == null) {
                    this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("getPreProperties can not be called in child process");
                } else {
                    if (!this.j || !this.n) {
                        a(this.a);
                    }
                    if (this.m == null) {
                        this.m = new JSONObject();
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    if (this.m.length() > 0) {
                        try {
                            jSONObject = new JSONObject(this.m.toString());
                        } catch (JSONException e2) {
                            jSONObject = jSONObject2;
                        }
                    } else {
                        jSONObject = jSONObject2;
                    }
                }
            }
        }
        return jSONObject;
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
        } catch (Throwable th) {
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
        } catch (Throwable th) {
        }
        if (this.b != null) {
            this.b.b();
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

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public void k() {
        synchronized (this) {
            if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                MLog.e("unregisterSuperPropertyByCoreProtocol can not be called in child process");
            } else if (this.k == null || this.a == null) {
                this.k = new JSONObject();
            } else {
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(this.a).edit();
                edit.putString(h, this.k.toString());
                edit.commit();
            }
        }
    }

    public JSONObject l() {
        synchronized (this) {
            try {
                if (!UMGlobalContext.getInstance().isMainProcess(this.a)) {
                    MLog.e("getSuperPropertiesJSONObject can not be called in child process");
                    return null;
                }
                if (this.k == null) {
                    this.k = new JSONObject();
                }
                JSONObject jSONObject = this.k;
                return jSONObject;
            } catch (Throwable th) {
            }
        }
    }

    public void m() {
        synchronized (this) {
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
        }
    }
}
