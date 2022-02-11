package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import ezy.assist.compat.RomUtil;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

public class DeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    protected static final String LOG_TAG = "com.umeng.commonsdk.statistics.common.DeviceConfig";
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase(Locale.getDefault()));
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        if (r7.getPackageManager().checkPermission(r8, r7.getPackageName()) != 0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
        if (((java.lang.Integer) java.lang.Class.forName("android.content.Context").getMethod("checkSelfPermission", new java.lang.Class[]{java.lang.String.class}).invoke(r7, new java.lang.Object[]{r8})).intValue() == 0) goto L_0x0031;
     */
    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
            } catch (Throwable th) {
                return false;
            }
        }
        boolean z = true;
        return z;
    }

    public static String getAndroidId(Context context) {
        if (context != null) {
            try {
                return Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w("can't read android id");
                }
            }
        }
        return null;
    }

    public static String getAppHashKey(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                return Base64.encodeToString(instance.digest(), 0).trim();
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String getAppMD5Signature(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return byte2HexFormatted(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(LOG_TAG, th);
            return null;
        }
    }

    public static String getAppSHA1Key(Context context) {
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        return UMUtils.getAppVersionCode(context);
    }

    public static String getAppVersionName(Context context) {
        return UMUtils.getAppVersionName(context);
    }

    public static String getApplicationLable(Context context) {
        return context == null ? "" : context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021 A[SYNTHETIC, Splitter:B:11:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0029 A[SYNTHETIC, Splitter:B:17:0x0029] */
    private static Properties getBuildProp() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                    }
                }
                throw th;
            }
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            throw th;
        }
        return properties;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    public static String getCPU() {
        String str = null;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
                str = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (FileNotFoundException e) {
                    e = e;
                    MLog.e(LOG_TAG, "Could not open file /proc/cpuinfo", (Throwable) e);
                    if (str != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    MLog.e(LOG_TAG, "Could not read from file /proc/cpuinfo", th);
                    if (str != null) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        }
        return str != null ? str.substring(str.indexOf(58) + 1).trim() : "";
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static String getDBencryptID(Context context) {
        String string;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = (telephonyManager == null || !checkPermission(context, "android.permission.READ_PHONE_STATE")) ? null : telephonyManager.getDeviceId();
            try {
                if (!TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                string = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
                if (!TextUtils.isEmpty(string) || Build.VERSION.SDK_INT < 9) {
                    return string;
                }
                if (Build.VERSION.SDK_INT < 26) {
                    return Build.SERIAL;
                }
                Class<?> cls = Class.forName("android.os.Build");
                return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable th) {
                return deviceId;
            }
        } catch (Throwable th2) {
            return string;
        }
    }

    public static String getDeviceId(Context context) {
        return AnalyticsConstants.getDeviceType() == 2 ? getDeviceIdForBox(context) : getDeviceIdForGeneral(context);
    }

    public static String getDeviceIdForBox(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            String string = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str = LOG_TAG;
                MLog.i(str, "getDeviceId, ANDROID_ID: " + string);
            }
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String macBySystemInterface = getMacBySystemInterface(context);
            if (AnalyticsConstants.UM_DEBUG) {
                String str2 = LOG_TAG;
                MLog.i(str2, "getDeviceId, MAC: " + macBySystemInterface);
            }
            if (!TextUtils.isEmpty(macBySystemInterface)) {
                return macBySystemInterface;
            }
            String serialNo = getSerialNo();
            return TextUtils.isEmpty(serialNo) ? getIMEI(context) : serialNo;
        } else if (Build.VERSION.SDK_INT == 23) {
            String string2 = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str3 = LOG_TAG;
                MLog.i(str3, "getDeviceId, ANDROID_ID: " + string2);
            }
            if (!TextUtils.isEmpty(string2)) {
                return string2;
            }
            String macByJavaAPI = getMacByJavaAPI();
            if (TextUtils.isEmpty(macByJavaAPI)) {
                macByJavaAPI = AnalyticsConstants.CHECK_DEVICE ? getMacShell() : getMacBySystemInterface(context);
            }
            if (AnalyticsConstants.UM_DEBUG) {
                String str4 = LOG_TAG;
                MLog.i(str4, "getDeviceId, MAC: " + macByJavaAPI);
            }
            if (!TextUtils.isEmpty(macByJavaAPI)) {
                return macByJavaAPI;
            }
            String serialNo2 = getSerialNo();
            return TextUtils.isEmpty(serialNo2) ? getIMEI(context) : serialNo2;
        } else {
            String string3 = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str5 = LOG_TAG;
                MLog.i(str5, "getDeviceId: ANDROID_ID: " + string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String serialNo3 = getSerialNo();
            if (!TextUtils.isEmpty(serialNo3)) {
                return serialNo3;
            }
            String imei = getIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            String macByJavaAPI2 = getMacByJavaAPI();
            if (!TextUtils.isEmpty(macByJavaAPI2)) {
                return macByJavaAPI2;
            }
            String macBySystemInterface2 = getMacBySystemInterface(context);
            if (!AnalyticsConstants.UM_DEBUG) {
                return macBySystemInterface2;
            }
            String str6 = LOG_TAG;
            MLog.i(str6, "getDeviceId, MAC: " + macBySystemInterface2);
            return macBySystemInterface2;
        }
    }

    public static String getDeviceIdForGeneral(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            String imei = getIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(LOG_TAG, "No IMEI.");
            }
            String macBySystemInterface = getMacBySystemInterface(context);
            if (!TextUtils.isEmpty(macBySystemInterface)) {
                return macBySystemInterface;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str = LOG_TAG;
                MLog.i(str, "getDeviceId, ANDROID_ID: " + string);
            }
            return TextUtils.isEmpty(string) ? getSerialNo() : string;
        } else if (Build.VERSION.SDK_INT == 23) {
            String imei2 = getIMEI(context);
            if (!TextUtils.isEmpty(imei2)) {
                return imei2;
            }
            String macByJavaAPI = getMacByJavaAPI();
            if (TextUtils.isEmpty(macByJavaAPI)) {
                macByJavaAPI = AnalyticsConstants.CHECK_DEVICE ? getMacShell() : getMacBySystemInterface(context);
            }
            if (AnalyticsConstants.UM_DEBUG) {
                String str2 = LOG_TAG;
                MLog.i(str2, "getDeviceId, MAC: " + macByJavaAPI);
            }
            if (!TextUtils.isEmpty(macByJavaAPI)) {
                return macByJavaAPI;
            }
            String string2 = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str3 = LOG_TAG;
                MLog.i(str3, "getDeviceId, ANDROID_ID: " + string2);
            }
            return TextUtils.isEmpty(string2) ? getSerialNo() : string2;
        } else {
            String imei3 = getIMEI(context);
            if (!TextUtils.isEmpty(imei3)) {
                return imei3;
            }
            String serialNo = getSerialNo();
            if (!TextUtils.isEmpty(serialNo)) {
                return serialNo;
            }
            String string3 = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (AnalyticsConstants.UM_DEBUG) {
                String str4 = LOG_TAG;
                MLog.i(str4, "getDeviceId, ANDROID_ID: " + string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String macByJavaAPI2 = getMacByJavaAPI();
            if (!TextUtils.isEmpty(macByJavaAPI2)) {
                return macByJavaAPI2;
            }
            String macBySystemInterface2 = getMacBySystemInterface(context);
            if (!AnalyticsConstants.UM_DEBUG) {
                return macBySystemInterface2;
            }
            String str5 = LOG_TAG;
            MLog.i(str5, "getDeviceId, MAC: " + macBySystemInterface2);
            return macBySystemInterface2;
        }
    }

    public static String getDeviceIdUmengMD5(Context context) {
        return HelperUtils.getUmengMD5(getDeviceId(context));
    }

    public static String getDeviceType(Context context) {
        if (context == null) {
            return "Phone";
        }
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "Tablet" : "Phone";
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return String.valueOf(i2) + "*" + String.valueOf(i);
        } catch (Throwable th) {
            return "";
        }
    }

    private static String getEmuiVersion(Properties properties) {
        try {
            return properties.getProperty(KEY_EMUI_VERSION_CODE, (String) null);
        } catch (Exception e) {
            return null;
        }
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(LOG_TAG, "Could not read gpu infor:", th);
            }
            return new String[0];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    private static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        String str;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return "";
        }
        try {
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            str = telephonyManager.getDeviceId();
            try {
                if (!AnalyticsConstants.UM_DEBUG) {
                    return str;
                }
                String str2 = LOG_TAG;
                MLog.i(str2, "getDeviceId, IMEI: " + str);
                return str;
            } catch (Throwable th) {
                th = th;
                if (AnalyticsConstants.UM_DEBUG) {
                    return str;
                }
                MLog.w(LOG_TAG, "No IMEI.", th);
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
            str = "";
            if (AnalyticsConstants.UM_DEBUG) {
            }
        }
    }

    public static String getImei(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    return telephonyManager.getDeviceId();
                }
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w("No IMEI.", (Throwable) e);
                }
            }
        }
        return null;
    }

    public static String getImeiNew(Context context) {
        String str;
        String str2 = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    if (Build.VERSION.SDK_INT < 26) {
                        return telephonyManager.getDeviceId();
                    }
                    try {
                        Method method = telephonyManager.getClass().getMethod("getImei", new Class[0]);
                        method.setAccessible(true);
                        str = (String) method.invoke(telephonyManager, new Object[0]);
                    } catch (Exception e) {
                        str = null;
                    }
                    try {
                        if (TextUtils.isEmpty(str)) {
                            return telephonyManager.getDeviceId();
                        }
                        str2 = str;
                    } catch (Exception e2) {
                        e = e2;
                        str2 = str;
                        if (AnalyticsConstants.UM_DEBUG) {
                            MLog.w("No IMEI.", (Throwable) e);
                        }
                        return str2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return str2;
    }

    public static String getImsi(Context context) {
        if (context == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) ? null : telephonyManager.getSubscriberId();
    }

    private static Locale getLocale(Context context) {
        Locale locale;
        if (context == null) {
            return Locale.getDefault();
        }
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Throwable th) {
            MLog.e(LOG_TAG, "fail to read user config locale");
            locale = null;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = {"Unknown", "Unknown"};
        if (context != null) {
            try {
                Locale locale = getLocale(context);
                if (locale != null) {
                    strArr[0] = locale.getCountry();
                    strArr[1] = locale.getLanguage();
                }
                if (TextUtils.isEmpty(strArr[0])) {
                    strArr[0] = "Unknown";
                }
                if (TextUtils.isEmpty(strArr[1])) {
                    strArr[1] = "Unknown";
                }
            } catch (Throwable th) {
                MLog.e(LOG_TAG, "error in getLocaleInfo", th);
            }
        }
        return strArr;
    }

    public static String getMCCMNC(Context context) {
        if (context == null || getImsi(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", new Object[]{Integer.valueOf(i2)});
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(i));
        stringBuffer.append(valueOf);
        return stringBuffer.toString();
    }

    public static String getMac(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            return getMacBySystemInterface(context);
        }
        if (Build.VERSION.SDK_INT == 23) {
            String macByJavaAPI = getMacByJavaAPI();
            return TextUtils.isEmpty(macByJavaAPI) ? AnalyticsConstants.CHECK_DEVICE ? getMacShell() : getMacBySystemInterface(context) : macByJavaAPI;
        }
        String macByJavaAPI2 = getMacByJavaAPI();
        return TextUtils.isEmpty(macByJavaAPI2) ? getMacBySystemInterface(context) : macByJavaAPI2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x000c A[Catch:{ Throwable -> 0x0076 }] */
    private static String getMacByJavaAPI() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if ("wlan0".equals(nextElement.getName()) || "eth0".equals(nextElement.getName())) {
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
                    return sb.toString().toLowerCase(Locale.getDefault());
                }
                while (networkInterfaces.hasMoreElements()) {
                }
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    private static String getMacBySystemInterface(Context context) {
        if (context == null) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager != null ? wifiManager.getConnectionInfo().getMacAddress() : "";
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                String str = LOG_TAG;
                MLog.w(str, "Could not get mac address." + th.toString());
            }
            return "";
        }
    }

    private static String getMacShell() {
        int i = 0;
        try {
            String[] strArr = new String[3];
            strArr[0] = "/sys/class/net/wlan0/address";
            strArr[1] = "/sys/class/net/eth0/address";
            strArr[2] = "/sys/devices/virtual/net/wlan0/address";
            while (true) {
                int i2 = i;
                if (i2 >= strArr.length) {
                    break;
                }
                String reaMac = reaMac(strArr[i2]);
                if (reaMac != null) {
                    return reaMac;
                }
                i = i2 + 1;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {"", ""};
        if (context == null) {
            return strArr;
        }
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                strArr[0] = "";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "";
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
        } catch (Throwable th) {
            return strArr;
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String getPackageName(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) ? null : telephonyManager.getNetworkOperator();
    }

    public static int[] getResolutionArray(Context context) {
        int i;
        int i2;
        int i3;
        if (context == null) {
            return null;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return null;
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                i = reflectMetrics(displayMetrics, "noncompatWidthPixels");
                i2 = reflectMetrics(displayMetrics, "noncompatHeightPixels");
            } else {
                i = -1;
                i2 = -1;
            }
            if (i == -1 || i2 == -1) {
                i = displayMetrics.widthPixels;
                i3 = displayMetrics.heightPixels;
            } else {
                i3 = i2;
            }
            int[] iArr = new int[2];
            if (i > i3) {
                iArr[0] = i3;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = i3;
            return iArr;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(LOG_TAG, "read resolution fail", th);
            }
            return null;
        }
    }

    public static String getSerial() {
        if (Build.VERSION.SDK_INT < 9) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        try {
            Class<?> cls = Class.forName("android.os.Build");
            return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    private static String getSerialNo() {
        String str;
        if (Build.VERSION.SDK_INT < 9) {
            str = "";
        } else if (Build.VERSION.SDK_INT >= 26) {
            try {
                Class<?> cls = Class.forName("android.os.Build");
                str = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable th) {
                str = "";
            }
        } else {
            str = Build.SERIAL;
        }
        if (AnalyticsConstants.UM_DEBUG) {
            String str2 = LOG_TAG;
            MLog.i(str2, "getDeviceId, serial no: " + str);
        }
        return str;
    }

    public static String getSubOSName(Context context) {
        Properties buildProp = getBuildProp();
        try {
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            return TextUtils.isEmpty(property) ? isFlyMe() ? "Flyme" : isEmui(buildProp) ? "Emui" : !TextUtils.isEmpty(getYunOSVersion(buildProp)) ? "YunOS" : property : RomUtil.ROM_MIUI;
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getSubOSVersion(Context context) {
        Properties buildProp = getBuildProp();
        try {
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (!isFlyMe()) {
                return isEmui(buildProp) ? getEmuiVersion(buildProp) : getYunOSVersion(buildProp);
            }
            try {
                return getFlymeVersion(buildProp);
            } catch (Throwable th) {
                return property;
            }
        } catch (Throwable th2) {
            return null;
        }
    }

    public static int getTimeZone(Context context) {
        if (context == null) {
            return 8;
        }
        try {
            Calendar instance = Calendar.getInstance(getLocale(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
            return 8;
        } catch (Throwable th) {
            MLog.i(LOG_TAG, "error in getTimeZone", th);
            return 8;
        }
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        return null;
    }

    public static boolean isChineseAera(Context context) {
        if (context == null) {
            return false;
        }
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, e.N, "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            return imprintProperty.equals("cn");
        }
        if (getImsi(context) == null) {
            String str = getLocaleInfo(context)[0];
            return !TextUtils.isEmpty(str) && str.equalsIgnoreCase("cn");
        }
        int i = context.getResources().getConfiguration().mcc;
        if (i == 460) {
            return true;
        }
        if (i == 461) {
            return true;
        }
        if (i != 0) {
            return false;
        }
        String str2 = getLocaleInfo(context)[0];
        return !TextUtils.isEmpty(str2) && str2.equalsIgnoreCase("cn");
    }

    private static boolean isEmui(Properties properties) {
        try {
            return properties.getProperty(KEY_EMUI_VERSION_CODE, (String) null) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean isWiFiAvailable(Context context) {
        if (context == null) {
            return false;
        }
        return "Wi-Fi".equals(getNetworkAccessMode(context)[0]);
    }

    private static String reaMac(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            FileReader fileReader = new FileReader(str);
            try {
                BufferedReader bufferedReader2 = new BufferedReader(fileReader, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
                try {
                    str2 = bufferedReader2.readLine();
                    try {
                        fileReader.close();
                    } catch (Throwable th2) {
                    }
                    try {
                        bufferedReader2.close();
                    } catch (Throwable th3) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader = bufferedReader2;
                }
            } catch (Throwable th5) {
                bufferedReader = null;
                th = th5;
                try {
                    fileReader.close();
                } catch (Throwable th6) {
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th7) {
                    }
                }
                throw th;
            }
        } catch (Throwable th8) {
        }
        return str2;
    }

    private static int reflectMetrics(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Throwable th) {
            return -1;
        }
    }
}
