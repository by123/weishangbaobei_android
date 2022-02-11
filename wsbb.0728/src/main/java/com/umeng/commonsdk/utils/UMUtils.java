package com.umeng.commonsdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import ezy.assist.compat.RomUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

public class UMUtils {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_LAST_APP_KEY = "last_appkey";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_SHARED_PREFERENCES_NAME = "umeng_common_config";
    public static final String MOBILE_NETWORK = "2G/3G";
    private static final String SD_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String TAG = "UMUtils";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";
    private static final Pattern pattern = Pattern.compile("UTDID\">([^<]+)");
    private static Object spLock = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0048, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        android.util.Log.e(TAG, "MD5 e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        android.util.Log.e(TAG, "MD5 e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048 A[ExcHandler: Throwable (r1v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:2:0x0005] */
    public static String MD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        } catch (Throwable th) {
        }
    }

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
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    private static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    public static boolean checkAndroidManifest(Context context, String str) {
        try {
            StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getActivityInfo(new ComponentName(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName(), str), 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean checkIntentFilterData(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse(SocializeProtocolConstants.PROTOCOL_KEY_TENCENT + str + ":"));
        List<ResolveInfo> queryIntentActivities = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().queryIntentActivities(intent, 64);
        if (queryIntentActivities.size() <= 0) {
            return false;
        }
        for (ResolveInfo next : queryIntentActivities) {
            if (next.activityInfo != null && next.activityInfo.packageName.equals(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getApplicationInfo(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName(), ShareContent.MINAPP_STYLE);
            return (applicationInfo == null || applicationInfo.metaData.get(str) == null) ? false : true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean checkPath(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        if (r7.getPackageManager().checkPermission(r8, r7.getPackageName()) != 0) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
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
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
                return false;
            }
        }
        boolean z = true;
        return z;
    }

    public static boolean checkResource(Context context, String str, String str2) {
        return StubApp.getOrigApplicationContext(context.getApplicationContext()).getResources().getIdentifier(str, str2, StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName()) > 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0033, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        android.util.Log.e(TAG, "encrypt by SHA1 e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        android.util.Log.e(TAG, "encrypt by SHA1 e is " + r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017 A[ExcHandler: Throwable (r1v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    public static String encryptBySHA1(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(bytes);
            return bytes2Hex(instance.digest());
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
        }
    }

    public static String getAppHashKey(Context context) {
        return DeviceConfig.getAppHashKey(context);
    }

    public static String getAppMD5Signature(Context context) {
        String appMD5Signature = DeviceConfig.getAppMD5Signature(context);
        return !TextUtils.isEmpty(appMD5Signature) ? appMD5Signature.replace(":", "").toLowerCase() : appMD5Signature;
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppSHA1Key(Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String getAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "appkey");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021 A[SYNTHETIC, Splitter:B:11:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0029 A[SYNTHETIC, Splitter:B:17:0x0029] */
    public static Properties getBuildProp() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
            } catch (IOException e) {
                if (fileInputStream != null) {
                }
                return properties;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                }
                throw th;
            }
            try {
                fileInputStream.close();
            } catch (IOException e2) {
            }
        } catch (IOException e3) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return properties;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
        return properties;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004d A[Catch:{ Exception -> 0x0064, Throwable -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082 A[RETURN, SYNTHETIC] */
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
                } catch (IOException e) {
                    e = e;
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e);
                    }
                    if (str != null) {
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e);
                    }
                    if (str != null) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            if (AnalyticsConstants.UM_DEBUG) {
            }
            if (str != null) {
            }
        }
        if (str != null) {
            return "";
        }
        try {
            return str.substring(str.indexOf(58) + 1).trim();
        } catch (Exception e5) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get cpu e is " + e5);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get cpu e is " + th);
            }
            return "";
        }
    }

    public static String getChannel(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "channel");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getChannelByXML(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (!(applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null)) {
                String obj2 = obj.toString();
                if (obj2 != null) {
                    return obj2.trim();
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String getDeviceToken(Context context) {
        String str;
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            try {
                Class<?> cls = Class.forName("com.umeng.message.MessageSharedPrefs");
                if (!(cls == null || (method = cls.getMethod("getInstance", new Class[]{Context.class})) == null || (invoke = method.invoke(cls, new Object[]{origApplicationContext})) == null || (method2 = cls.getMethod("getDeviceToken", new Class[0])) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null || !(invoke2 instanceof String))) {
                    str = (String) invoke2;
                    return str;
                }
            } catch (Throwable th) {
                return null;
            }
        }
        str = null;
        return str;
    }

    public static String getDeviceType(Context context) {
        if (context == null) {
            return "Phone";
        }
        try {
            return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "Tablet" : "Phone";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
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
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    private static File getFile(Context context) {
        if (context == null || !checkPermission(context, SD_PERMISSION) || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        android.util.Log.e(TAG, "get file MD5 e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
        android.util.Log.e(TAG, "get file MD5 e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044 A[ExcHandler: Throwable (r1v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0003] */
    public static String getFileMD5(File file) {
        try {
            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
            if (!file.isFile()) {
                return "";
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return String.format("%1$032x", new Object[]{new BigInteger(1, instance.digest())});
                }
            }
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
        }
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + e);
            }
            return new String[0];
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + th);
            }
            return new String[0];
        }
    }

    public static String getImsi(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) ? null : telephonyManager.getSubscriberId();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getLastAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, KEY_LAST_APP_KEY);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        android.util.Log.e(TAG, "get locale e is " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        android.util.Log.e(TAG, "get locale e is " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0075, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a A[ExcHandler: Throwable (r0v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0005] */
    public static Locale getLocale(Context context) {
        Locale locale;
        if (context == null) {
            return null;
        }
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "fail to read user config locale, e is " + e);
            }
            locale = null;
        } catch (Throwable th) {
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getMac(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }
            return "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    public static String getMultiProcessSP(Context context, String str) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        if (isMainProgress(context)) {
                            sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences("umeng_common_config", 0);
                        } else {
                            String subProcessName = UMFrUtils.getSubProcessName(context);
                            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                            sharedPreferences = origApplicationContext.getSharedPreferences(subProcessName + "_" + "umeng_common_config", 0);
                        }
                        if (sharedPreferences == null) {
                            return null;
                        }
                        String string = sharedPreferences.getString(str, (String) null);
                        return string;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
            return null;
        }
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
                if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return strArr;
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager == null ? "Unknown" : telephonyManager.getNetworkOperator();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
        return "Unknown";
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            return checkPermission(context, "android.permission.READ_PHONE_STATE") ? telephonyManager.getNetworkOperator() : null;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0054, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
        android.util.Log.e(TAG, "get sub os name e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A[ExcHandler: Throwable (r1v0 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:2:0x0004] */
    public static String getSubOSName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Properties buildProp = getBuildProp();
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            return TextUtils.isEmpty(property) ? isFlyMe() ? "Flyme" : !TextUtils.isEmpty(getYunOSVersion(buildProp)) ? "YunOS" : property : RomUtil.ROM_MIUI;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        android.util.Log.e(TAG, "get sub os version e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b A[ExcHandler: Throwable (r1v0 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:2:0x0004] */
    public static String getSubOSVersion(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Properties buildProp = getBuildProp();
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            if (TextUtils.isEmpty(property)) {
                return isFlyMe() ? getFlymeVersion(buildProp) : getYunOSVersion(buildProp);
            }
            return property;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os version e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
        }
    }

    public static int getTargetSdkVersion(Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public static String getUMId(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return UMEnvelopeBuild.imprintProperty(StubApp.getOrigApplicationContext(context.getApplicationContext()), e.f, (String) null);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        android.util.Log.e(TAG, "get utiid e is " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0057, code lost:
        android.util.Log.e(TAG, "get utiid e is " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0035 A[ExcHandler: Throwable (r0v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:4:0x0007] */
    public static String getUTDID(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke((Object) null, new Object[]{StubApp.getOrigApplicationContext(context.getApplicationContext())});
        } catch (Exception e) {
            return readUTDId(context);
        } catch (Throwable th) {
        }
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        return null;
    }

    public static boolean isApplication(Context context) {
        try {
            String name = StubApp.getOrigApplicationContext(context.getApplicationContext()).getClass().getSuperclass().getName();
            return !TextUtils.isEmpty(name) && name.equals("android.app.Application");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDebug(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return false;
        }
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMainProgress(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
            return !TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static String parseId(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 == read) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }

    private static String readUTDId(Context context) {
        File file;
        FileInputStream fileInputStream;
        if (context == null || (file = getFile(context)) == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            String parseId = parseId(readStreamToString(fileInputStream));
            safeClose(fileInputStream);
            return parseId;
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
            safeClose(fileInputStream);
            throw th;
        }
    }

    private static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static void setAppkey(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, "appkey", str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set app key e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set app key e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static void setChannel(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, "channel", str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set channel e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set channel e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static void setLastAppkey(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, KEY_LAST_APP_KEY, str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set last app key e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set last app key e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    public static void setMultiProcessSP(Context context, String str, String str2) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str) && str2 != null) {
                        if (isMainProgress(context)) {
                            sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences("umeng_common_config", 0);
                        } else {
                            String subProcessName = UMFrUtils.getSubProcessName(context);
                            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                            sharedPreferences = origApplicationContext.getSharedPreferences(subProcessName + "_" + "umeng_common_config", 0);
                        }
                        if (sharedPreferences != null) {
                            sharedPreferences.edit().putString(str, str2).commit();
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
