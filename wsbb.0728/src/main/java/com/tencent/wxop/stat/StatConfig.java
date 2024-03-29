package com.tencent.wxop.stat;

import android.content.Context;
import android.os.Build;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.tencent.a.a.a.a.g;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.l;
import com.tencent.wxop.stat.common.q;
import com.umeng.socialize.common.SocializeConstants;
import java.net.URI;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig {
    private static String A = null;
    private static String B;
    private static String C;
    private static String D = "mta_channel";
    private static int E = SubsamplingScaleImageView.ORIENTATION_180;
    private static int F = WXMediaMessage.DESCRIPTION_LENGTH_LIMIT;
    private static long G = 0;
    private static long H = 300000;
    private static volatile String I = StatConstants.MTA_REPORT_FULL_URL;
    private static int J = 0;
    private static volatile int K = 0;
    private static int L = 20;
    private static int M = 0;
    private static boolean N = false;
    private static int O = 4096;
    private static boolean P = false;
    private static String Q = null;
    private static boolean R = false;
    private static g S = null;
    static f a = new f(2);
    static f b = new f(1);
    static String c = "__HIBERNATE__";
    static String d = "__HIBERNATE__TIME";
    static String e = "__MTA_KILL__";
    static String f = "";
    static boolean g = false;
    static int h = 100;
    static long i = 10000;
    public static boolean isAutoExceptionCaught = true;
    static boolean j = true;
    static volatile String k = StatConstants.MTA_SERVER;
    static boolean l = true;
    static int m = 0;
    static long n = 10000;
    static int o = WXMediaMessage.TITLE_LENGTH_LIMIT;
    private static StatLogger p = l.b();
    private static StatReportStrategy q = StatReportStrategy.APP_LAUNCH;
    private static boolean r = false;
    private static boolean s = true;
    private static int t = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
    private static int u = 100000;
    private static int v = 30;
    private static int w = 10;
    private static int x = 100;
    private static int y = 30;
    private static int z = 1;

    static int a() {
        return v;
    }

    static String a(String str, String str2) {
        try {
            String string = b.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            StatLogger statLogger = p;
            statLogger.w("can't find custom key:" + str);
            return str2;
        }
    }

    static void a(int i2) {
        synchronized (StatConfig.class) {
            try {
                K = i2;
            } catch (Throwable th) {
                Class<StatConfig> cls = StatConfig.class;
                throw th;
            }
        }
    }

    static void a(long j2) {
        q.b(i.a(), c, j2);
        setEnableStatService(false);
        p.warn("MTA is disable for current SDK version");
    }

    static void a(Context context, f fVar) {
        if (fVar.a == b.a) {
            b = fVar;
            a(fVar.b);
            if (!b.b.isNull("iplist")) {
                a.a(context).a(b.b.getString("iplist"));
            }
        } else if (fVar.a == a.a) {
            a = fVar;
        }
    }

    static void a(Context context, f fVar, JSONObject jSONObject) {
        boolean z2 = false;
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equalsIgnoreCase("v")) {
                    int i2 = jSONObject.getInt(next);
                    boolean z3 = fVar.d != i2 ? true : z2;
                    fVar.d = i2;
                    z2 = z3;
                } else if (next.equalsIgnoreCase("c")) {
                    String string = jSONObject.getString("c");
                    if (string.length() > 0) {
                        fVar.b = new JSONObject(string);
                    }
                } else if (next.equalsIgnoreCase("m")) {
                    fVar.c = jSONObject.getString("m");
                }
            }
            if (z2) {
                au a2 = au.a(i.a());
                if (a2 != null) {
                    a2.a(fVar);
                }
                if (fVar.a == b.a) {
                    a(fVar.b);
                    b(fVar.b);
                }
            }
            a(context, fVar);
        } catch (JSONException e2) {
            e = e2;
            p.e(e);
        } catch (Throwable th) {
            e = th;
            p.e(e);
        }
    }

    static void a(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2;
        f fVar;
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equalsIgnoreCase(Integer.toString(b.a))) {
                    jSONObject2 = jSONObject.getJSONObject(next);
                    fVar = b;
                } else if (next.equalsIgnoreCase(Integer.toString(a.a))) {
                    jSONObject2 = jSONObject.getJSONObject(next);
                    fVar = a;
                } else if (next.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(next));
                    if (statReportStrategy != null) {
                        q = statReportStrategy;
                        if (isDebugEnable()) {
                            StatLogger statLogger = p;
                            statLogger.d("Change to ReportStrategy:" + statReportStrategy.name());
                        }
                    }
                } else {
                    return;
                }
                a(context, fVar, jSONObject2);
            }
        } catch (JSONException e2) {
            p.e((Throwable) e2);
        }
    }

    static void a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                setStatSendStrategy(statReportStrategy);
            }
        } catch (JSONException e2) {
            if (isDebugEnable()) {
                p.i("rs not found.");
            }
        }
    }

    static boolean a(int i2, int i3, int i4) {
        return i2 >= i3 && i2 <= i4;
    }

    static boolean a(JSONObject jSONObject, String str, String str2) {
        if (!jSONObject.isNull(str)) {
            String optString = jSONObject.optString(str);
            return l.c(str2) && l.c(optString) && str2.equalsIgnoreCase(optString);
        }
    }

    static void b() {
        M++;
    }

    static void b(int i2) {
        if (i2 >= 0) {
            M = i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034 A[Catch:{ Exception -> 0x01ab }] */
    static void b(Context context, JSONObject jSONObject) {
        boolean z2;
        int i2;
        Integer valueOf;
        try {
            String optString = jSONObject.optString(e);
            if (l.c(optString)) {
                JSONObject jSONObject2 = new JSONObject(optString);
                if (jSONObject2.length() != 0) {
                    if (!jSONObject2.isNull("sm")) {
                        Object obj = jSONObject2.get("sm");
                        if (obj instanceof Integer) {
                            valueOf = (Integer) obj;
                        } else if (obj instanceof String) {
                            valueOf = Integer.valueOf((String) obj);
                        } else {
                            i2 = 0;
                            if (i2 > 0) {
                                if (isDebugEnable()) {
                                    p.i("match sleepTime:" + i2 + " minutes");
                                }
                                q.b(context, d, System.currentTimeMillis() + ((long) (i2 * 60 * SocializeConstants.CANCLE_RESULTCODE)));
                                setEnableStatService(false);
                                p.warn("MTA is disable for current SDK version");
                            }
                        }
                        i2 = valueOf.intValue();
                        if (i2 > 0) {
                        }
                    }
                    if (a(jSONObject2, "sv", StatConstants.VERSION)) {
                        p.i("match sdk version:2.0.4");
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (a(jSONObject2, "md", Build.MODEL)) {
                        p.i("match MODEL:" + Build.MODEL);
                        z2 = true;
                    }
                    if (a(jSONObject2, "av", l.h(context))) {
                        p.i("match app version:" + l.h(context));
                        z2 = true;
                    }
                    if (a(jSONObject2, "mf", Build.MANUFACTURER)) {
                        p.i("match MANUFACTURER:" + Build.MANUFACTURER);
                        z2 = true;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(Build.VERSION.SDK_INT);
                    if (a(jSONObject2, "osv", sb.toString())) {
                        p.i("match android SDK version:" + Build.VERSION.SDK_INT);
                        z2 = true;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(Build.VERSION.SDK_INT);
                    if (a(jSONObject2, "ov", sb2.toString())) {
                        p.i("match android SDK version:" + Build.VERSION.SDK_INT);
                        z2 = true;
                    }
                    if (a(jSONObject2, "ui", au.a(context).b(context).b())) {
                        p.i("match imei:" + au.a(context).b(context).b());
                        z2 = true;
                    }
                    if (a(jSONObject2, "mid", getLocalMidOnly(context))) {
                        p.i("match mid:" + getLocalMidOnly(context));
                        z2 = true;
                    }
                    if (z2) {
                        a(l.b(StatConstants.VERSION));
                    }
                }
            }
        } catch (Exception e2) {
            p.e((Throwable) e2);
        }
    }

    static void b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                b(i.a(), jSONObject);
                String string = jSONObject.getString(c);
                if (isDebugEnable()) {
                    StatLogger statLogger = p;
                    statLogger.d("hibernateVer:" + string + ", current version:2.0.4");
                }
                long b2 = l.b(string);
                if (l.b(StatConstants.VERSION) <= b2) {
                    a(b2);
                }
            } catch (JSONException e2) {
                p.d("__HIBERNATE__ not found.");
            }
        }
    }

    static int c() {
        return M;
    }

    public static String getAppKey(Context context) {
        String str;
        synchronized (StatConfig.class) {
            try {
                str = B;
            } catch (Throwable th) {
                Class<StatConfig> cls = StatConfig.class;
                throw th;
            }
        }
        return str;
    }

    public static int getCurSessionStatReportCount() {
        return K;
    }

    public static g getCustomLogger() {
        return S;
    }

    public static String getCustomProperty(String str) {
        try {
            return a.b.getString(str);
        } catch (Throwable th) {
            p.e(th);
            return null;
        }
    }

    public static String getCustomProperty(String str, String str2) {
        try {
            String string = a.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            p.e(th);
            return str2;
        }
    }

    public static String getCustomUserId(Context context) {
        if (context == null) {
            p.error((Object) "Context for getCustomUid is null.");
            return null;
        }
        if (Q == null) {
            Q = q.a(context, "MTA_CUSTOM_UID", "");
        }
        return Q;
    }

    public static long getFlushDBSpaceMS() {
        return n;
    }

    public static String getInstallChannel(Context context) {
        String str;
        synchronized (StatConfig.class) {
            try {
                str = C;
            } catch (Throwable th) {
                Class<StatConfig> cls = StatConfig.class;
                throw th;
            }
        }
        return str;
    }

    public static String getLocalMidOnly(Context context) {
        return context != null ? g.C(context).p().a() : "0";
    }

    public static int getMaxBatchReportCount() {
        return y;
    }

    public static int getMaxDaySessionNumbers() {
        return L;
    }

    public static int getMaxImportantDataSendRetryCount() {
        return x;
    }

    public static int getMaxParallelTimmingEvents() {
        return F;
    }

    public static int getMaxReportEventLength() {
        return O;
    }

    public static int getMaxSendRetryCount() {
        return w;
    }

    public static int getMaxSessionStatReportCount() {
        return J;
    }

    public static int getMaxStoreEventCount() {
        return u;
    }

    public static String getMid(Context context) {
        return getLocalMidOnly(context);
    }

    public static long getMsPeriodForMethodsCalledLimitClear() {
        return i;
    }

    public static int getNumEventsCachedInMemory() {
        return m;
    }

    public static int getNumEventsCommitPerSec() {
        return z;
    }

    public static int getNumOfMethodsCalledLimit() {
        return h;
    }

    public static String getQQ(Context context) {
        return q.a(context, "mta.acc.qq", f);
    }

    public static int getReportCompressedSize() {
        return o;
    }

    public static int getSendPeriodMinutes() {
        return E;
    }

    public static int getSessionTimoutMillis() {
        return t;
    }

    public static String getStatReportHost() {
        return k;
    }

    public static String getStatReportUrl() {
        return I;
    }

    public static StatReportStrategy getStatSendStrategy() {
        return q;
    }

    public static boolean isAutoExceptionCaught() {
        return isAutoExceptionCaught;
    }

    public static boolean isDebugEnable() {
        return r;
    }

    public static boolean isEnableConcurrentProcess() {
        return P;
    }

    public static boolean isEnableSmartReporting() {
        return j;
    }

    public static boolean isEnableStatService() {
        return s;
    }

    public static boolean isReportEventsByOrder() {
        return l;
    }

    public static boolean isXGProMode() {
        return R;
    }

    public static void setAppKey(Context context, String str) {
        StatLogger statLogger;
        String str2;
        if (context == null) {
            statLogger = p;
            str2 = "ctx in StatConfig.setAppKey() is null";
        } else if (str == null || str.length() > 256) {
            statLogger = p;
            str2 = "appkey in StatConfig.setAppKey() is null or exceed 256 bytes";
        } else {
            B = str;
            return;
        }
        statLogger.error((Object) str2);
    }

    public static void setAppKey(String str) {
        StatLogger statLogger;
        String str2;
        if (str == null) {
            statLogger = p;
            str2 = "appkey in StatConfig.setAppKey() is null";
        } else if (str.length() > 256) {
            statLogger = p;
            str2 = "The length of appkey cann't exceed 256 bytes.";
        } else {
            B = str;
            return;
        }
        statLogger.error((Object) str2);
    }

    public static void setAutoExceptionCaught(boolean z2) {
        isAutoExceptionCaught = z2;
    }

    public static void setCustomLogger(g gVar) {
        S = gVar;
    }

    public static void setCustomUserId(Context context, String str) {
        if (context == null) {
            p.error((Object) "Context for setCustomUid is null.");
            return;
        }
        q.b(context, "MTA_CUSTOM_UID", str);
        Q = str;
    }

    public static void setDebugEnable(boolean z2) {
        r = z2;
        l.b().setDebugEnable(z2);
    }

    public static void setEnableConcurrentProcess(boolean z2) {
        P = z2;
    }

    public static void setEnableSmartReporting(boolean z2) {
        j = z2;
    }

    public static void setEnableStatService(boolean z2) {
        s = z2;
        if (!z2) {
            p.warn("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static void setFlushDBSpaceMS(long j2) {
        if (j2 > 0) {
            n = j2;
        }
    }

    public static void setInstallChannel(Context context, String str) {
        if (str.length() > 128) {
            p.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            C = str;
        }
    }

    public static void setInstallChannel(String str) {
        C = str;
    }

    public static void setMaxBatchReportCount(int i2) {
        if (!a(i2, 2, (int) SocializeConstants.CANCLE_RESULTCODE)) {
            p.error((Object) "setMaxBatchReportCount can not exceed the range of [2,1000].");
        } else {
            y = i2;
        }
    }

    public static void setMaxDaySessionNumbers(int i2) {
        if (i2 <= 0) {
            p.e((Object) "maxDaySessionNumbers must be greater than 0.");
        } else {
            L = i2;
        }
    }

    public static void setMaxImportantDataSendRetryCount(int i2) {
        if (i2 > 100) {
            x = i2;
        }
    }

    public static void setMaxParallelTimmingEvents(int i2) {
        if (!a(i2, 1, 4096)) {
            p.error((Object) "setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
        } else {
            F = i2;
        }
    }

    public static void setMaxReportEventLength(int i2) {
        if (i2 <= 0) {
            p.error((Object) "maxReportEventLength on setMaxReportEventLength() must greater than 0.");
        } else {
            O = i2;
        }
    }

    public static void setMaxSendRetryCount(int i2) {
        if (!a(i2, 1, (int) SocializeConstants.CANCLE_RESULTCODE)) {
            p.error((Object) "setMaxSendRetryCount can not exceed the range of [1,1000].");
        } else {
            w = i2;
        }
    }

    public static void setMaxSessionStatReportCount(int i2) {
        if (i2 < 0) {
            p.error((Object) "maxSessionStatReportCount cannot be less than 0.");
        } else {
            J = i2;
        }
    }

    public static void setMaxStoreEventCount(int i2) {
        if (!a(i2, 0, 500000)) {
            p.error((Object) "setMaxStoreEventCount can not exceed the range of [0, 500000].");
        } else {
            u = i2;
        }
    }

    public static void setNumEventsCachedInMemory(int i2) {
        if (i2 >= 0) {
            m = i2;
        }
    }

    public static void setNumEventsCommitPerSec(int i2) {
        if (i2 > 0) {
            z = i2;
        }
    }

    public static void setNumOfMethodsCalledLimit(int i2, long j2) {
        h = i2;
        if (j2 >= 1000) {
            i = j2;
        }
    }

    public static void setQQ(Context context, String str) {
        q.b(context, "mta.acc.qq", str);
        f = str;
    }

    public static void setReportCompressedSize(int i2) {
        if (i2 > 0) {
            o = i2;
        }
    }

    public static void setReportEventsByOrder(boolean z2) {
        l = z2;
    }

    public static void setSendPeriodMinutes(int i2) {
        if (!a(i2, 1, 10080)) {
            p.error((Object) "setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        } else {
            E = i2;
        }
    }

    public static void setSessionTimoutMillis(int i2) {
        if (!a(i2, (int) SocializeConstants.CANCLE_RESULTCODE, 86400000)) {
            p.error((Object) "setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        } else {
            t = i2;
        }
    }

    public static void setStatReportUrl(String str) {
        if (str == null || str.length() == 0) {
            p.error((Object) "statReportUrl cannot be null or empty.");
            return;
        }
        I = str;
        try {
            k = new URI(I).getHost();
        } catch (Exception e2) {
            p.w(e2);
        }
        if (isDebugEnable()) {
            StatLogger statLogger = p;
            statLogger.i("url:" + I + ", domain:" + k);
        }
    }

    public static void setStatSendStrategy(StatReportStrategy statReportStrategy) {
        q = statReportStrategy;
        if (statReportStrategy != StatReportStrategy.PERIOD) {
            StatServiceImpl.c = 0;
        }
        if (isDebugEnable()) {
            StatLogger statLogger = p;
            statLogger.d("Change to statSendStrategy: " + statReportStrategy);
        }
    }

    public static void setXGProMode(boolean z2) {
        R = z2;
    }
}
