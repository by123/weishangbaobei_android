package com.umeng.socialize.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DeviceConfig {
    protected static final String LOG_TAG = "DeviceConfig";
    private static final String MOBILE_NETWORK = "2G/3G";
    protected static final String UNKNOW = "Unknown";
    private static final String WIFI = "Wi-Fi";
    public static Context context;
    private static Object object = new Object();

    public static boolean checkPermission(Context context2, String str) {
        return (context2 == null || context2 == null || context2.getPackageManager().checkPermission(str, context2.getPackageName()) != 0) ? false : true;
    }

    public static String getAndroidID(Context context2) {
        return context2 == null ? "" : Settings.Secure.getString(context2.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
    }

    public static String getAppVersion(String str, Context context2) {
        if (context2 == null) {
            return "";
        }
        try {
            return context2.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDeviceId(Context context2) {
        String str;
        if (context2 == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context2.getSystemService("phone");
        if (telephonyManager == null) {
            Log.w(LOG_TAG, "No IMEI.");
        }
        try {
            str = checkPermission(context2, "android.permission.READ_PHONE_STATE") ? telephonyManager.getDeviceId() : "";
        } catch (Exception e) {
            Log.w(LOG_TAG, "No IMEI.", e);
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        Log.w(LOG_TAG, "No IMEI.");
        String mac = getMac(context2);
        if (!TextUtils.isEmpty(mac)) {
            return mac;
        }
        Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        String string = Settings.Secure.getString(context2.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
        Log.w(LOG_TAG, "getDeviceId: Secure.ANDROID_ID: " + string);
        return TextUtils.isEmpty(string) ? getDeviceSN() : string;
    }

    public static String getDeviceSN() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMac(Context context2) {
        if (context2 == null) {
            return "";
        }
        String str = "";
        try {
            str = getMacShell();
            try {
                if (TextUtils.isEmpty(str)) {
                    Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
                }
                if (TextUtils.isEmpty(str)) {
                    str = getMacByJava();
                    if (TextUtils.isEmpty(str)) {
                        Log.w(LOG_TAG, "Could not get mac address by java");
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    return SocializeSpUtils.getMac(context2);
                }
                SocializeSpUtils.putMac(context2, str);
                return str;
            } catch (Exception e) {
                e = e;
                Log.w(LOG_TAG, "Could not get mac address." + e.toString());
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static String getMacByJava() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.getName().equals("wlan0")) {
                    byte[] hardwareAddress = nextElement.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    if (hardwareAddress.length == 0) {
                        return null;
                    }
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
        } catch (Exception e) {
            return null;
        }
    }

    private static String getMacShell() {
        int i = 0;
        String[] strArr = {"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
        while (i < strArr.length) {
            try {
                String reaMac = reaMac(strArr[i]);
                if (reaMac != null) {
                    return reaMac;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return null;
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

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getPackageName(Context context2) {
        return context2 == null ? "" : context2.getPackageName();
    }

    public static boolean isAppInstalled(String str, Context context2) {
        boolean z = false;
        if (context2 != null) {
            synchronized (object) {
                try {
                    context2.getPackageManager().getPackageInfo(str, 1);
                    z = true;
                } catch (PackageManager.NameNotFoundException | RuntimeException e) {
                }
            }
        }
        return z;
    }

    public static boolean isNetworkAvailable(Context context2) {
        return context2 != null && checkPermission(context2, "android.permission.ACCESS_NETWORK_STATE") && isOnline(context2);
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
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x001f A[SYNTHETIC, Splitter:B:15:0x001f] */
    private static String reaMac(String str) throws FileNotFoundException {
        BufferedReader bufferedReader;
        Throwable th;
        ? r0 = 0;
        FileReader fileReader = new FileReader(str);
        try {
            bufferedReader = new BufferedReader(fileReader, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            try {
                String readLine = bufferedReader.readLine();
                try {
                    fileReader.close();
                } catch (IOException e) {
                }
                try {
                    bufferedReader.close();
                    r0 = readLine;
                } catch (IOException e2) {
                    r0 = readLine;
                }
            } catch (IOException e3) {
            } catch (Throwable th2) {
                th = th2;
                r0 = bufferedReader;
                try {
                    fileReader.close();
                } catch (IOException e4) {
                }
                if (r0 != 0) {
                    try {
                        r0.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            bufferedReader = null;
            try {
                fileReader.close();
            } catch (IOException e7) {
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e8) {
                }
            }
            return r0;
        } catch (Throwable th3) {
            th = th3;
            fileReader.close();
            if (r0 != 0) {
            }
            throw th;
        }
        return r0;
    }
}
