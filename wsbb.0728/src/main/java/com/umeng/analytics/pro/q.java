package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.b;
import com.umeng.analytics.pro.c;
import com.umeng.analytics.pro.g;
import com.umeng.analytics.pro.u;
import com.umeng.analytics.process.UMProcessDBDatasSender;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;
import org.json.JSONObject;

public class q implements u.a {
    public static final String a = "session_start_time";
    public static final String b = "session_end_time";
    public static final String c = "session_id";
    public static final String d = "pre_session_id";
    public static final String e = "a_start_time";
    public static final String f = "a_end_time";
    private static String g;
    private static Context h;
    private static boolean i;

    private static class a {
        /* access modifiers changed from: private */
        public static final q a = new q();

        private a() {
        }
    }

    private q() {
        u.a().a((u.a) this);
    }

    public static q a() {
        return a.a;
    }

    private void a(Context context, String str, long j, long j2) {
        if (TextUtils.isEmpty(g)) {
            g = u.a().a(h);
        }
        if (!TextUtils.isEmpty(str) && !str.equals(g)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(c.d.a.g, j2);
                JSONObject b2 = b.a().b();
                if (b2 != null && b2.length() > 0) {
                    jSONObject.put("__sp", b2);
                }
                JSONObject c2 = b.a().c();
                if (c2 != null && c2.length() > 0) {
                    jSONObject.put("__pp", c2);
                }
                g.a(context).a(g, jSONObject, g.a.END);
            } catch (Exception e2) {
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("__e", j);
                g.a(context).a(str, jSONObject2, g.a.BEGIN);
            } catch (Exception e3) {
            }
            g = str;
        }
    }

    private void a(String str, long j) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(h);
        if (sharedPreferences != null) {
            long j2 = sharedPreferences.getLong(b, 0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__ii", str);
                jSONObject.put("__e", j);
                jSONObject.put(c.d.a.g, j2);
                double[] location = AnalyticsConfig.getLocation();
                if (location != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("lat", location[0]);
                    jSONObject2.put("lng", location[1]);
                    jSONObject2.put("ts", System.currentTimeMillis());
                    jSONObject.put(c.d.a.e, jSONObject2);
                }
                Class<?> cls = Class.forName("android.net.TrafficStats");
                Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
                Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
                int i2 = h.getApplicationInfo().uid;
                if (i2 != -1) {
                    long longValue = ((Long) method.invoke((Object) null, new Object[]{Integer.valueOf(i2)})).longValue();
                    long longValue2 = ((Long) method2.invoke((Object) null, new Object[]{Integer.valueOf(i2)})).longValue();
                    if (longValue > 0 && longValue2 > 0) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(b.G, longValue);
                        jSONObject3.put(b.F, longValue2);
                        jSONObject.put(c.d.a.d, jSONObject3);
                    }
                    g.a(h).a(str, jSONObject, g.a.NEWSESSION);
                    r.a(h);
                    j.a(h);
                }
            } catch (Throwable th) {
            }
        }
    }

    private String b(Context context) {
        if (h == null && context != null) {
            h = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        String d2 = u.a().d(h);
        try {
            c(context);
            k.a(h).d((Object) null);
        } catch (Throwable th) {
        }
        return d2;
    }

    private void c(Context context) {
        k.a(context).b(context);
        k.a(context).d();
    }

    public String a(Context context) {
        try {
            if (g == null) {
                return PreferenceWrapper.getDefault(context).getString(c, (String) null);
            }
        } catch (Throwable th) {
        }
        return g;
    }

    public String a(Context context, long j, boolean z) {
        String b2 = u.a().b(context);
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onInstantSessionInternal: current session id = " + b2);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j);
            JSONObject b3 = b.a().b();
            if (b3 != null && b3.length() > 0) {
                jSONObject.put("__sp", b3);
            }
            JSONObject c2 = b.a().c();
            if (c2 != null && c2.length() > 0) {
                jSONObject.put("__pp", c2);
            }
            g.a(context).a(b2, jSONObject, g.a.INSTANTSESSIONBEGIN);
            k.a(context).a((Object) jSONObject, z);
        } catch (Throwable th) {
        }
        return b2;
    }

    public void a(Context context, long j) {
        SharedPreferences.Editor edit;
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(h);
        if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.putLong(a, j);
            edit.commit();
        }
    }

    public void a(Context context, Object obj) {
        SharedPreferences.Editor edit;
        try {
            if (h == null && context != null) {
                h = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(h);
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                String string = sharedPreferences.getString(b.aw, "");
                String appVersionName = UMUtils.getAppVersionName(h);
                if (TextUtils.isEmpty(string)) {
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(b.aw, appVersionName);
                    edit.commit();
                } else if (!string.equals(appVersionName)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onStartSessionInternal: upgrade version: " + string + "-> " + appVersionName);
                    int i2 = sharedPreferences.getInt("versioncode", 0);
                    String string2 = sharedPreferences.getString("pre_date", "");
                    String string3 = sharedPreferences.getString("pre_version", "");
                    String string4 = sharedPreferences.getString(b.aw, "");
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(b.aw, appVersionName);
                    edit.putString("vers_date", string2);
                    edit.putString("vers_pre_version", string3);
                    edit.putString("cur_version", string4);
                    edit.putInt("vers_code", i2);
                    edit.putString("vers_name", string);
                    if (longValue - sharedPreferences.getLong(f, 0) < u.a().b()) {
                        edit.putLong(f, 0);
                    }
                    edit.commit();
                    if (i) {
                        i = false;
                        b(h, longValue);
                        c(h, longValue);
                        return;
                    }
                    return;
                }
                if (i) {
                    i = false;
                    g = b(context);
                    MLog.i("Start new session: " + g);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "mSessionChanged flag has been set, Start new session: " + g);
                    return;
                }
                g = sharedPreferences.getString(c, (String) null);
                edit.putLong(e, longValue);
                edit.putLong(f, 0);
                edit.commit();
                MLog.i("Extend current session: " + g);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Extend current session: " + g);
                c(context);
                k.a(h).a(false);
            }
        } catch (Throwable th) {
        }
    }

    public void a(String str, long j, long j2) {
        if (!TextUtils.isEmpty(str)) {
            a(str, j);
        }
    }

    public void a(String str, String str2, long j, long j2) {
        a(h, str2, j, j2);
        UMRTLog.i(UMRTLog.RTLOG_TAG, "saveSessionToDB: complete");
        if (AnalyticsConstants.SUB_PROCESS_EVENT) {
            UMWorkDispatch.sendEvent(h, UMProcessDBDatasSender.UM_PROCESS_EVENT_KEY, UMProcessDBDatasSender.getInstance(h), Long.valueOf(System.currentTimeMillis()));
        }
    }

    public String b() {
        return g;
    }

    public void b(Context context, Object obj) {
        try {
            if (h == null) {
                h = UMGlobalContext.getAppContext(context);
            }
            long currentTimeMillis = obj == null ? System.currentTimeMillis() : ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(h);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString(b.aw, "");
                String appVersionName = UMUtils.getAppVersionName(h);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (edit == null) {
                    return;
                }
                if (!TextUtils.isEmpty(string) && !string.equals(appVersionName)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> requestNewInstantSessionIf: version upgrade");
                    edit.putLong(a, currentTimeMillis);
                    edit.commit();
                    k.a(h).a((Object) null, true);
                    String c2 = u.a().c(h);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> force generate new session: session id = " + c2);
                    i = true;
                    a(h, currentTimeMillis, true);
                } else if (u.a().e(h)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> More then 30 sec from last session.");
                    i = true;
                    edit.putLong(a, currentTimeMillis);
                    edit.commit();
                    a(h, currentTimeMillis, false);
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> less then 30 sec from last session, do nothing.");
                    i = false;
                }
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean b(Context context, long j) {
        String a2;
        boolean z = true;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null || (a2 = u.a().a(h)) == null) {
                return false;
            }
            long j2 = sharedPreferences.getLong(e, 0);
            long j3 = sharedPreferences.getLong(f, 0);
            if (j2 <= 0 || j3 != 0) {
                z = false;
            } else {
                c(h, (Object) Long.valueOf(j));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(c.d.a.g, j);
                JSONObject b2 = b.a().b();
                if (b2 != null && b2.length() > 0) {
                    jSONObject.put("__sp", b2);
                }
                JSONObject c2 = b.a().c();
                if (c2 != null && c2.length() > 0) {
                    jSONObject.put("__pp", c2);
                }
                g.a(context).a(a2, jSONObject, g.a.END);
                k.a(h).e();
            }
            return z;
        } catch (Throwable th) {
            return false;
        }
    }

    public String c() {
        return a(h);
    }

    public void c(Context context, long j) {
        if (PreferenceWrapper.getDefault(context) != null) {
            try {
                k.a(h).c((Object) null);
            } catch (Throwable th) {
            }
        }
    }

    public void c(Context context, Object obj) {
        try {
            if (h == null && context != null) {
                h = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                if (sharedPreferences.getLong(e, 0) == 0) {
                    MLog.e("onPause called before onResume");
                    return;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onEndSessionInternal: write activity end time = " + longValue);
                edit.putLong(f, longValue);
                edit.putLong(b, longValue);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }
}
