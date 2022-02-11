package com.umeng.socialize.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DeviceConfig {
    protected static final String LOG_TAG = "DeviceConfig";
    private static final String MOBILE_NETWORK = "2G/3G";
    protected static final String UNKNOW = "Unknown";
    private static final String WIFI = "Wi-Fi";
    public static Context context;
    private static Object object = new Object();

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isAppInstalled(java.lang.String r3, android.content.Context r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.Object r1 = object
            monitor-enter(r1)
            r2 = 1
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ NameNotFoundException | RuntimeException -> 0x0013 }
            r4.getPackageInfo(r3, r2)     // Catch:{ NameNotFoundException | RuntimeException -> 0x0013 }
            r0 = 1
            goto L_0x0013
        L_0x0011:
            r3 = move-exception
            goto L_0x0015
        L_0x0013:
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0015:
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.utils.DeviceConfig.isAppInstalled(java.lang.String, android.content.Context):boolean");
    }

    public static String getAppVersion(String str, Context context2) {
        if (context2 == null) {
            return "";
        }
        try {
            return context2.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean checkPermission(Context context2, String str) {
        if (context2 == null || context2 == null || context2.getPackageManager().checkPermission(str, context2.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static String getDeviceId(Context context2) {
        if (context2 == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context2.getSystemService("phone");
        if (telephonyManager == null) {
            Log.w(LOG_TAG, "No IMEI.");
        }
        String str = "";
        try {
            if (checkPermission(context2, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, "No IMEI.", e);
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(LOG_TAG, "No IMEI.");
            str = getMac(context2);
            if (TextUtils.isEmpty(str)) {
                Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                str = Settings.Secure.getString(context2.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
                Log.w(LOG_TAG, "getDeviceId: Secure.ANDROID_ID: " + str);
                if (TextUtils.isEmpty(str)) {
                    return getDeviceSN();
                }
            }
        }
        return str;
    }

    public static String getDeviceSN() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] getNetworkAccessMode(Context context2) {
        if (context2 == null) {
            return new String[]{"", ""};
        }
        String[] strArr = {UNKNOW, UNKNOW};
        if (context2.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context2.getPackageName()) != 0) {
            strArr[0] = UNKNOW;
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = UNKNOW;
            return strArr;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 == null || networkInfo2.getState() != NetworkInfo.State.CONNECTED) {
                return strArr;
            }
            strArr[0] = "2G/3G";
            strArr[1] = networkInfo2.getSubtypeName();
            return strArr;
        }
        strArr[0] = "Wi-Fi";
        return strArr;
    }

    public static boolean isOnline(Context context2) {
        if (context2 == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean isNetworkAvailable(Context context2) {
        if (context2 != null && checkPermission(context2, "android.permission.ACCESS_NETWORK_STATE") && isOnline(context2)) {
            return true;
        }
        return false;
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getAndroidID(Context context2) {
        return context2 == null ? "" : Settings.Secure.getString(context2.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getMac(Context context2) {
        if (context2 == null) {
            return "";
        }
        String str = "";
        try {
            String macShell = getMacShell();
            try {
                if (TextUtils.isEmpty(macShell)) {
                    Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
                }
                if (TextUtils.isEmpty(macShell)) {
                    str = getMacByJava();
                    if (TextUtils.isEmpty(str)) {
                        Log.w(LOG_TAG, "Could not get mac address by java");
                    }
                } else {
                    str = macShell;
                }
                if (TextUtils.isEmpty(str)) {
                    return SocializeSpUtils.getMac(context2);
                }
                SocializeSpUtils.putMac(context2, str);
                return str;
            } catch (Exception e) {
                e = e;
                str = macShell;
                Log.w(LOG_TAG, "Could not get mac address." + e.toString());
                return str;
            }
        } catch (Exception e2) {
            e = e2;
            Log.w(LOG_TAG, "Could not get mac address." + e.toString());
            return str;
        }
    }

    private static String getMacByJava() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.getName().equals("wlan0")) {
                    byte[] hardwareAddress = nextElement.getHardwareAddress();
                    if (hardwareAddress != null) {
                        if (hardwareAddress.length != 0) {
                            StringBuilder sb = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            return sb.toString();
                        }
                    }
                    return null;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String getPackageName(Context context2) {
        return context2 == null ? "" : context2.getPackageName();
    }

    private static String getMacShell() {
        String[] strArr = {"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
        int i = 0;
        while (i < strArr.length) {
            try {
                String reaMac = reaMac(strArr[i]);
                if (reaMac != null) {
                    return reaMac;
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|33) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0014 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0024 A[SYNTHETIC, Splitter:B:18:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String reaMac(java.lang.String r4) throws java.io.FileNotFoundException {
        /*
            java.io.FileReader r0 = new java.io.FileReader
            r0.<init>(r4)
            r4 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0028, all -> 0x001e }
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x0028, all -> 0x001e }
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x0029, all -> 0x0019 }
            r0.close()     // Catch:{ IOException -> 0x0014 }
        L_0x0014:
            r1.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0017:
            r4 = r2
            goto L_0x0031
        L_0x0019:
            r4 = move-exception
            r3 = r1
            r1 = r4
            r4 = r3
            goto L_0x001f
        L_0x001e:
            r1 = move-exception
        L_0x001f:
            r0.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            if (r4 == 0) goto L_0x0027
            r4.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r1
        L_0x0028:
            r1 = r4
        L_0x0029:
            r0.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.utils.DeviceConfig.reaMac(java.lang.String):java.lang.String");
    }
}
