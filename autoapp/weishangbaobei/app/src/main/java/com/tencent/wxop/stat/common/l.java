package com.tencent.wxop.stat.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class l {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static Random e = null;
    private static DisplayMetrics f = null;
    private static String g = null;
    private static String h = "";
    private static String i = "";
    private static int j = -1;
    /* access modifiers changed from: private */
    public static StatLogger k = null;
    private static String l = null;
    private static String m = null;
    private static volatile int n = -1;
    private static String o = null;
    private static String p = null;
    private static long q = -1;
    private static String r = "";
    private static o s = null;
    private static String t = "__MTA_FIRST_ACTIVATE__";
    private static int u = -1;
    private static long v = -1;
    private static int w = 0;
    private static String x = "";

    public static String A(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity.activityInfo != null && !resolveActivity.activityInfo.packageName.equals("android")) {
            return resolveActivity.activityInfo.packageName;
        }
        return null;
    }

    private static long B(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static int a() {
        return g().nextInt(Integer.MAX_VALUE);
    }

    public static int a(Context context, boolean z) {
        if (z) {
            w = y(context);
        }
        return w;
    }

    public static Long a(String str, String str2, int i2, int i3, Long l2) {
        if (!(str == null || str2 == null)) {
            if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
                str2 = "\\" + str2;
            }
            String[] split = str.split(str2);
            if (split.length == i3) {
                try {
                    Long l3 = 0L;
                    for (String valueOf : split) {
                        l3 = Long.valueOf(((long) i2) * (l3.longValue() + Long.valueOf(valueOf).longValue()));
                    }
                    return l3;
                } catch (NumberFormatException unused) {
                }
            }
        }
        return l2;
    }

    public static String a(int i2) {
        Calendar instance = Calendar.getInstance();
        instance.roll(6, i2);
        return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
    }

    public static String a(long j2) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j2));
    }

    public static String a(Context context, String str) {
        if (StatConfig.isEnableConcurrentProcess()) {
            if (m == null) {
                m = o(context);
            }
            if (m != null) {
                return str + "_" + m;
            }
        }
        return str;
    }

    public static String a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                byte b3 = b2 & 255;
                if (b3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(b3));
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return "0";
        }
    }

    public static HttpHost a(Context context) {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0 || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
                return null;
            }
            if ((activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) || (extraInfo = activeNetworkInfo.getExtraInfo()) == null) {
                return null;
            }
            if (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap")) {
                if (!extraInfo.equals("uniwap")) {
                    if (extraInfo.equals("ctwap")) {
                        return new HttpHost("10.0.0.200", 80);
                    }
                    String defaultHost = Proxy.getDefaultHost();
                    if (defaultHost != null && defaultHost.trim().length() > 0) {
                        return new HttpHost(defaultHost, Proxy.getDefaultPort());
                    }
                    return null;
                }
            }
            return new HttpHost("10.0.0.172", 80);
        } catch (Throwable th) {
            k.e(th);
        }
    }

    public static void a(Context context, int i2) {
        w = i2;
        q.b(context, "mta.qq.com.difftime", i2);
    }

    public static boolean a(StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (statSpecifyReportedInfo == null) {
            return false;
        }
        return c(statSpecifyReportedInfo.getAppKey());
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static long b(String str) {
        return a(str, ".", 100, 3, 0L).longValue();
    }

    public static synchronized StatLogger b() {
        StatLogger statLogger;
        synchronized (l.class) {
            if (k == null) {
                StatLogger statLogger2 = new StatLogger(StatConstants.LOG_TAG);
                k = statLogger2;
                statLogger2.setDebugEnable(false);
            }
            statLogger = k;
        }
        return statLogger;
    }

    public static synchronized String b(Context context) {
        synchronized (l.class) {
            if (a == null || a.trim().length() == 0) {
                String a2 = r.a(context);
                a = a2;
                if (a2 == null || a.trim().length() == 0) {
                    a = Integer.toString(g().nextInt(Integer.MAX_VALUE));
                }
                String str = a;
                return str;
            }
            String str2 = a;
            return str2;
        }
    }

    public static long c() {
        long currentTimeMillis;
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            currentTimeMillis = instance.getTimeInMillis();
        } catch (Throwable th) {
            k.e(th);
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis + 86400000;
    }

    public static synchronized String c(Context context) {
        String str;
        synchronized (l.class) {
            if (c == null || c.trim().length() == 0) {
                c = r.b(context);
            }
            str = c;
        }
        return str;
    }

    public static boolean c(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    public static DisplayMetrics d(Context context) {
        if (f == null) {
            f = new DisplayMetrics();
            ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay().getMetrics(f);
        }
        return f;
    }

    public static String d() {
        if (c(p)) {
            return p;
        }
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        String str = String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(e() / 1000000);
        p = str;
        return str;
    }

    public static long e() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static boolean e(Context context) {
        NetworkInfo[] allNetworkInfo;
        try {
            if (r.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("connectivity");
                if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
                    for (int i2 = 0; i2 < allNetworkInfo.length; i2++) {
                        if (allNetworkInfo[i2].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i2].isConnected()) {
                            return true;
                        }
                    }
                }
                return false;
            }
            k.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        } catch (Throwable th) {
            k.e(th);
        }
    }

    public static String f(Context context) {
        if (context == null) {
            return null;
        }
        return context.getClass().getName();
    }

    public static String g(Context context) {
        TelephonyManager telephonyManager;
        if (g != null) {
            return g;
        }
        try {
            if (!r.a(context, "android.permission.READ_PHONE_STATE")) {
                k.e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
            } else if (i(context) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                g = telephonyManager.getSimOperator();
            }
        } catch (Throwable th) {
            k.e(th);
        }
        return g;
    }

    private static synchronized Random g() {
        Random random;
        synchronized (l.class) {
            if (e == null) {
                e = new Random();
            }
            random = e;
        }
        return random;
    }

    private static long h() {
        if (q > 0) {
            return q;
        }
        long j2 = 1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j2 = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            bufferedReader.close();
        } catch (Exception unused) {
        }
        q = j2;
        return j2;
    }

    public static String h(Context context) {
        if (c(h)) {
            return h;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            h = str;
            if (str == null) {
                return "";
            }
        } catch (Throwable th) {
            k.e(th);
        }
        return h;
    }

    public static boolean i(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    public static String j(Context context) {
        String str = "";
        try {
            if (!r.a(context, "android.permission.INTERNET") || !r.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                k.e((Object) "can not get the permission of android.permission.ACCESS_WIFI_STATE");
                return str;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                String typeName = activeNetworkInfo.getTypeName();
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (typeName != null) {
                    if (typeName.equalsIgnoreCase("WIFI")) {
                        return "WIFI";
                    }
                    if (typeName.equalsIgnoreCase("MOBILE")) {
                        return extraInfo != null ? extraInfo : "MOBILE";
                    }
                    if (extraInfo != null) {
                        return extraInfo;
                    }
                    str = typeName;
                }
            }
            return str;
        } catch (Throwable th) {
            k.e(th);
        }
    }

    public static Integer k(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String l(Context context) {
        if (c(i)) {
            return i;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            i = str;
            if (str == null || i.length() == 0) {
                return "unknown";
            }
        } catch (Throwable th) {
            k.e(th);
        }
        return i;
    }

    public static int m(Context context) {
        if (j != -1) {
            return j;
        }
        try {
            if (p.a()) {
                j = 1;
            }
        } catch (Throwable th) {
            k.e(th);
        }
        j = 0;
        return 0;
    }

    public static String n(Context context) {
        String path;
        if (c(l)) {
            return l;
        }
        try {
            if (r.a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (!(externalStorageState == null || !externalStorageState.equals("mounted") || (path = Environment.getExternalStorageDirectory().getPath()) == null)) {
                    StatFs statFs = new StatFs(path);
                    String str = String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf((((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000);
                    l = str;
                    return str;
                }
                return null;
            }
            k.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Throwable th) {
            k.e(th);
        }
    }

    static String o(Context context) {
        try {
            if (m != null) {
                return m;
            }
            int myPid = Process.myPid();
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    m = next.processName;
                    break;
                }
            }
            return m;
        } catch (Throwable unused) {
        }
    }

    public static String p(Context context) {
        return a(context, StatConstants.DATABASE_NAME);
    }

    public static synchronized Integer q(Context context) {
        Integer valueOf;
        synchronized (l.class) {
            if (n <= 0) {
                n = q.a(context, "MTA_EVENT_INDEX", 0);
                q.b(context, "MTA_EVENT_INDEX", n + SocializeConstants.CANCLE_RESULTCODE);
            } else if (n % SocializeConstants.CANCLE_RESULTCODE == 0) {
                try {
                    int i2 = n + SocializeConstants.CANCLE_RESULTCODE;
                    if (n >= 2147383647) {
                        i2 = 0;
                    }
                    q.b(context, "MTA_EVENT_INDEX", i2);
                } catch (Throwable th) {
                    k.w(th);
                }
            }
            int i3 = n + 1;
            n = i3;
            valueOf = Integer.valueOf(i3);
        }
        return valueOf;
    }

    public static String r(Context context) {
        try {
            return String.valueOf(B(context) / 1000000) + "/" + String.valueOf(h() / 1000000);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static JSONObject s(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", m.a());
            String d2 = m.d();
            if (d2 != null && d2.length() > 0) {
                jSONObject.put("na", d2);
            }
            int b2 = m.b();
            if (b2 > 0) {
                jSONObject.put("fx", b2 / 1000000);
            }
            int c2 = m.c();
            if (c2 > 0) {
                jSONObject.put("fn", c2 / 1000000);
            }
        } catch (Throwable th) {
            Log.w(StatConstants.LOG_TAG, "get cpu error", th);
        }
        return jSONObject;
    }

    public static String t(Context context) {
        List<Sensor> sensorList;
        if (c(r)) {
            return r;
        }
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService(e.aa);
            if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null)) {
                StringBuilder sb = new StringBuilder(sensorList.size() * 10);
                for (int i2 = 0; i2 < sensorList.size(); i2++) {
                    sb.append(sensorList.get(i2).getType());
                    if (i2 != sensorList.size() - 1) {
                        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    }
                }
                r = sb.toString();
            }
        } catch (Throwable th) {
            k.e(th);
        }
        return r;
    }

    public static synchronized int u(Context context) {
        synchronized (l.class) {
            if (u != -1) {
                int i2 = u;
                return i2;
            }
            v(context);
            int i3 = u;
            return i3;
        }
    }

    public static void v(Context context) {
        int a2 = q.a(context, t, 1);
        u = a2;
        if (a2 == 1) {
            q.b(context, t, 0);
        }
    }

    public static boolean w(Context context) {
        if (v < 0) {
            v = q.a(context, "mta.qq.com.checktime", 0);
        }
        return Math.abs(System.currentTimeMillis() - v) > 86400000;
    }

    public static void x(Context context) {
        v = System.currentTimeMillis();
        q.b(context, "mta.qq.com.checktime", v);
    }

    public static int y(Context context) {
        return q.a(context, "mta.qq.com.difftime", 0);
    }

    public static boolean z(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.processName.startsWith(packageName)) {
                if (next.importance == 400) {
                    return true;
                }
            }
        }
        return false;
    }
}
