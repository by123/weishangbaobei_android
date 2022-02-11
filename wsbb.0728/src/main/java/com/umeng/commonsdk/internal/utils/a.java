package com.umeng.commonsdk.internal.utils;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.ShareContent;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {

    /* renamed from: com.umeng.commonsdk.internal.utils.a$a  reason: collision with other inner class name */
    public static class C0007a {
        public String a;
        public String b;
    }

    public static class b {
        public String a = null;
        public int b = -1;
        public String c = null;
    }

    public static class c {
        public int a;
        public String b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public String h;
        public int i;
        public int j;
        public int k;
        public long l;
    }

    public static float a(Context context) {
        if (context == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            return configuration.fontScale;
        } catch (Exception e) {
            ULog.e("getFontSize:" + e.getMessage());
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public static long a(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppFirstInstallTime" + e.getMessage());
            return 0;
        }
    }

    private static String a(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter == null) {
            return null;
        }
        Class<?> cls = bluetoothAdapter.getClass();
        try {
            Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
            Field declaredField = cls.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Method method = cls2.getMethod("getAddress", new Class[0]);
            method.setAccessible(true);
            return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
        } catch (Exception e) {
            return bluetoothAdapter.getAddress();
        }
    }

    public static boolean a() {
        return h.a();
    }

    public static long b(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppLastUpdateTime:" + e.getMessage());
            return 0;
        }
    }

    public static String b() {
        return new SimpleDateFormat().format(new Date());
    }

    public static List<ScanResult> b(Context context) {
        WifiManager wifiManager;
        List<ScanResult> list;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        if (!DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (!DeviceConfig.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") && !DeviceConfig.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION"))) {
            list = null;
        } else {
            list = wifiManager.getScanResults();
            if (list == null || list.size() == 0) {
                return list;
            }
        }
        return list;
    }

    public static long c() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0;
        }
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r0 = (android.net.wifi.WifiManager) r2.getSystemService("wifi");
     */
    public static WifiInfo c(Context context) {
        WifiManager wifiManager;
        if (context == null) {
            return null;
        }
        WifiInfo connectionInfo = (!DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || wifiManager == null) ? null : wifiManager.getConnectionInfo();
        return connectionInfo;
    }

    public static String c(Context context, String str) {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppInstaller:" + e.getMessage());
            return null;
        }
    }

    public static int d(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppUid:" + e.getMessage());
            return 0;
        }
    }

    public static long d() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0;
        }
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }

    public static void d(Context context) {
        WifiInfo c2;
        boolean z = false;
        if (context != null && (c2 = c(context)) != null) {
            c cVar = new c();
            cVar.a = c2.describeContents();
            cVar.b = c2.getBSSID();
            cVar.c = c2.getSSID();
            if (Build.VERSION.SDK_INT >= 21) {
                cVar.d = c2.getFrequency();
            } else {
                cVar.d = -1;
            }
            if (c2.getHiddenSSID()) {
                cVar.e = 1;
            } else {
                cVar.e = 0;
            }
            cVar.f = c2.getIpAddress();
            cVar.g = c2.getLinkSpeed();
            cVar.h = DeviceConfig.getMac(context);
            cVar.i = c2.getNetworkId();
            cVar.j = c2.getRssi();
            cVar.k = g(context);
            cVar.l = System.currentTimeMillis();
            if (c2 != null) {
                try {
                    JSONArray b2 = f.b(context);
                    if (b2 != null && b2.length() > 0) {
                        int i = 0;
                        while (true) {
                            if (i < b2.length()) {
                                String optString = b2.optJSONObject(i).optString("ssid", (String) null);
                                if (optString != null && optString.equals(cVar.c)) {
                                    z = true;
                                    break;
                                }
                                i++;
                            } else {
                                break;
                            }
                        }
                    }
                    if (!z) {
                        f.a(context, cVar);
                    }
                } catch (Exception e) {
                    ULog.e("wifiChange:" + e.getMessage());
                }
            }
        }
    }

    public static String e() {
        return g.a("df");
    }

    private static String e(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, ShareContent.MINAPP_STYLE);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
        } catch (Exception e) {
            ULog.e("getLabel:" + e.getMessage());
        }
        return null;
    }

    public static JSONArray e(Context context) {
        if (context == null) {
            return null;
        }
        return f.b(context);
    }

    public static long f() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static void f(Context context) {
        if (context != null) {
            f.c(context);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r0 = (android.net.wifi.WifiManager) r2.getSystemService("wifi");
     */
    public static int g(Context context) {
        WifiManager wifiManager;
        if (context == null) {
            return -1;
        }
        int wifiState = (!DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || wifiManager == null) ? -1 : wifiManager.getWifiState();
        return wifiState;
    }

    public static String g() {
        String str = null;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            str = declaredMethod.invoke((Object) null, new Object[]{"net.hostname"}).toString();
            if (str == null) {
                return str;
            }
            try {
                return !str.equalsIgnoreCase("") ? HelperUtils.getUmengMD5(str) : str;
            } catch (Exception e) {
                e = e;
                ULog.e("getHostName:" + e.getMessage());
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static int h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            return 0;
        }
    }

    public static int i(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            return 0;
        }
    }

    public static DisplayMetrics j(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    private static boolean j() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r0 = (android.telephony.TelephonyManager) r2.getSystemService("phone");
     */
    public static String k(Context context) {
        TelephonyManager telephonyManager;
        if (context == null) {
            return null;
        }
        String subscriberId = (!DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) ? null : telephonyManager.getSubscriberId();
        return subscriberId;
    }

    public static String l(Context context) {
        TelephonyManager telephonyManager;
        String str;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        if (!DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
            str = null;
        } else if (Build.VERSION.SDK_INT < 26) {
            return telephonyManager.getDeviceId();
        } else {
            try {
                String t = t(context);
                try {
                    if (TextUtils.isEmpty(t)) {
                        return telephonyManager.getDeviceId();
                    }
                    str = t;
                } catch (Throwable th) {
                    str = t;
                }
            } catch (Throwable th2) {
                return null;
            }
        }
        return str;
    }

    public static List<InputMethodInfo> m(Context context) {
        if (context == null) {
            return null;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return null;
        }
        return inputMethodManager.getInputMethodList();
    }

    public static void n(Context context) {
        if (context != null) {
            try {
                if (DeviceConfig.checkPermission(context, "android.permission.BLUETOOTH")) {
                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                    b bVar = new b();
                    if (defaultAdapter.isEnabled()) {
                        bVar.b = defaultAdapter.getState();
                        if (Build.VERSION.SDK_INT < 23) {
                            bVar.a = defaultAdapter.getAddress();
                        } else {
                            bVar.a = a(defaultAdapter);
                        }
                        bVar.c = defaultAdapter.getName();
                        UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.i, com.umeng.commonsdk.internal.b.a(context).a(), bVar);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static JSONObject o(Context context) {
        if (context == null) {
            return null;
        }
        return f.a(context);
    }

    public static List<C0007a> p(Context context) {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (!file.isDirectory() || (list = file.list()) == null || list.length <= 0) {
                return arrayList;
            }
            for (String str : list) {
                if (str != null) {
                    if (!str.startsWith(".")) {
                        C0007a aVar = new C0007a();
                        aVar.a = str;
                        aVar.b = e(context, str);
                        arrayList.add(aVar);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            ULog.e("getAppList:" + e.getMessage());
            return arrayList;
        }
    }

    public static ActivityManager.MemoryInfo q(Context context) {
        if (context == null) {
            return null;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    public static String r(Context context) {
        if (context == null) {
        }
        return null;
    }

    public static String s(Context context) {
        return null;
    }

    private static String t(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Object invoke = Class.forName("android.telephony.TelephonyManager").getMethod("getMeid", new Class[0]).invoke((Object) null, new Object[0]);
            if (invoke != null && (invoke instanceof String)) {
                return (String) invoke;
            }
        } catch (Exception e) {
            ULog.e("meid:" + e.getMessage());
        }
        return null;
    }
}
