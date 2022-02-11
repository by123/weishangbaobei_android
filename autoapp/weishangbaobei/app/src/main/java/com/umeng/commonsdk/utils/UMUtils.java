package com.umeng.commonsdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
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

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setMultiProcessSP(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.Object r0 = spLock     // Catch:{ Exception | Throwable -> 0x005f }
            monitor-enter(r0)     // Catch:{ Exception | Throwable -> 0x005f }
            if (r4 == 0) goto L_0x005b
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0059 }
            if (r1 != 0) goto L_0x005b
            if (r6 != 0) goto L_0x000e
            goto L_0x005b
        L_0x000e:
            boolean r1 = isMainProgress(r4)     // Catch:{ all -> 0x0059 }
            r2 = 0
            if (r1 == 0) goto L_0x0024
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x0059 }
            android.content.Context r4 = com.stub.StubApp.getOrigApplicationContext(r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "umeng_common_config"
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0059 }
            goto L_0x004a
        L_0x0024:
            java.lang.String r1 = com.umeng.commonsdk.framework.UMFrUtils.getSubProcessName(r4)     // Catch:{ all -> 0x0059 }
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x0059 }
            android.content.Context r4 = com.stub.StubApp.getOrigApplicationContext(r4)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r3.<init>()     // Catch:{ all -> 0x0059 }
            r3.append(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "_"
            r3.append(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "umeng_common_config"
            r3.append(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0059 }
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0059 }
        L_0x004a:
            if (r4 == 0) goto L_0x0057
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ all -> 0x0059 }
            android.content.SharedPreferences$Editor r4 = r4.putString(r5, r6)     // Catch:{ all -> 0x0059 }
            r4.commit()     // Catch:{ all -> 0x0059 }
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            goto L_0x005f
        L_0x0059:
            r4 = move-exception
            goto L_0x005d
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            return
        L_0x005d:
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            throw r4     // Catch:{ Exception | Throwable -> 0x005f }
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.setMultiProcessSP(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMultiProcessSP(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Object r1 = spLock     // Catch:{ Exception -> 0x005a, Throwable -> 0x0059 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x005a, Throwable -> 0x0059 }
            if (r5 == 0) goto L_0x0055
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x000d
            goto L_0x0055
        L_0x000d:
            boolean r2 = isMainProgress(r5)     // Catch:{ all -> 0x0053 }
            r3 = 0
            if (r2 == 0) goto L_0x0023
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ all -> 0x0053 }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "umeng_common_config"
            android.content.SharedPreferences r5 = r5.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x0053 }
            goto L_0x0049
        L_0x0023:
            java.lang.String r2 = com.umeng.commonsdk.framework.UMFrUtils.getSubProcessName(r5)     // Catch:{ all -> 0x0053 }
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ all -> 0x0053 }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ all -> 0x0053 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0053 }
            r4.<init>()     // Catch:{ all -> 0x0053 }
            r4.append(r2)     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "_"
            r4.append(r2)     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "umeng_common_config"
            r4.append(r2)     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0053 }
            android.content.SharedPreferences r5 = r5.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x0053 }
        L_0x0049:
            if (r5 == 0) goto L_0x0051
            java.lang.String r5 = r5.getString(r6, r0)     // Catch:{ all -> 0x0053 }
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            return r5
        L_0x0051:
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            return r0
        L_0x0053:
            r5 = move-exception
            goto L_0x0057
        L_0x0055:
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            return r0
        L_0x0057:
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            throw r5     // Catch:{ Exception -> 0x005a, Throwable -> 0x0059 }
        L_0x0059:
            return r0
        L_0x005a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getMultiProcessSP(android.content.Context, java.lang.String):java.lang.String");
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        return readUTDId(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        android.util.Log.e(TAG, "get utiid e is " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002d */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x002b A[ExcHandler: Throwable (r7v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:4:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUTDID(android.content.Context r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "com.ut.device.UTDevice"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.String r2 = "getUtdid"
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r6 = 0
            r4[r6] = r5     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.reflect.Method r1 = r1.getMethod(r2, r4)     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            android.content.Context r3 = r7.getApplicationContext()     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            android.content.Context r3 = com.stub.StubApp.getOrigApplicationContext(r3)     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            r2[r6] = r3     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.Object r1 = r1.invoke(r0, r2)     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x002d, Throwable -> 0x002b }
            return r1
        L_0x002b:
            r7 = move-exception
            goto L_0x0032
        L_0x002d:
            java.lang.String r7 = readUTDId(r7)     // Catch:{ Exception -> 0x004d, Throwable -> 0x002b }
            return r7
        L_0x0032:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x004c
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get utiid e is "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.e(r1, r7)
        L_0x004c:
            return r0
        L_0x004d:
            r7 = move-exception
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x0068
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get utiid e is "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.e(r1, r7)
        L_0x0068:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getUTDID(android.content.Context):java.lang.String");
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
        } catch (Exception unused) {
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
            } catch (Exception unused) {
            }
        }
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

    private static File getFile(Context context) {
        if (context != null && checkPermission(context, SD_PERMISSION) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
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

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0028 A[Catch:{ FileNotFoundException -> 0x0043 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048 A[Catch:{ Exception -> 0x0041, Throwable -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060 A[Catch:{ Exception -> 0x0041, Throwable -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071 A[Catch:{ Exception -> 0x0041, Throwable -> 0x003f }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCPU() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0043 }
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0043 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0023 }
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1, r3)     // Catch:{ IOException -> 0x0023 }
            java.lang.String r3 = r2.readLine()     // Catch:{ IOException -> 0x0023 }
            r2.close()     // Catch:{ IOException -> 0x001f, FileNotFoundException -> 0x001b }
            r1.close()     // Catch:{ IOException -> 0x001f, FileNotFoundException -> 0x001b }
            r0 = r3
            goto L_0x005e
        L_0x001b:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L_0x0044
        L_0x001f:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L_0x0024
        L_0x0023:
            r1 = move-exception
        L_0x0024:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ FileNotFoundException -> 0x0043 }
            if (r2 == 0) goto L_0x005e
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0043 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0043 }
            java.lang.String r4 = "Could not read from file /proc/cpuinfo, e is "
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x0043 }
            r3.append(r1)     // Catch:{ FileNotFoundException -> 0x0043 }
            java.lang.String r1 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0043 }
            android.util.Log.e(r2, r1)     // Catch:{ FileNotFoundException -> 0x0043 }
            goto L_0x005e
        L_0x003f:
            r0 = move-exception
            goto L_0x0074
        L_0x0041:
            r0 = move-exception
            goto L_0x0091
        L_0x0043:
            r1 = move-exception
        L_0x0044:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            if (r2 == 0) goto L_0x005e
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            r3.<init>()     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            java.lang.String r4 = "Could not read from file /proc/cpuinfo, e is "
            r3.append(r4)     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            r3.append(r1)     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            android.util.Log.e(r2, r1)     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
        L_0x005e:
            if (r0 == 0) goto L_0x0071
            r1 = 58
            int r1 = r0.indexOf(r1)     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x0041, Throwable -> 0x003f }
            return r0
        L_0x0071:
            java.lang.String r0 = ""
            return r0
        L_0x0074:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x008e
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get cpu e is "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x008e:
            java.lang.String r0 = ""
            return r0
        L_0x0091:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x00ab
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get cpu e is "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x00ab:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getCPU():java.lang.String");
    }

    public static String getImsi(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
                return null;
            }
            return telephonyManager.getSubscriberId();
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

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getNetworkOperator();
            }
            return null;
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

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
            return "";
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
        }
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        android.util.Log.e(TAG, "fail to read user config locale, e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        android.util.Log.e(TAG, "get locale e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x001d;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0016 A[ExcHandler: Throwable (r1v6 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Locale getLocale(android.content.Context r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.content.res.Configuration r1 = new android.content.res.Configuration     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            r1.<init>()     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            r1.setToDefaults()     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            android.content.ContentResolver r2 = r5.getContentResolver()     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            android.provider.Settings.System.getConfiguration(r2, r1)     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            java.util.Locale r1 = r1.locale     // Catch:{ Exception -> 0x0018, Throwable -> 0x0016 }
            goto L_0x0034
        L_0x0016:
            r1 = move-exception
            goto L_0x003b
        L_0x0018:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            r3.<init>()     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            java.lang.String r4 = "fail to read user config locale, e is "
            r3.append(r4)     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            r3.append(r1)     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
            android.util.Log.e(r2, r1)     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
        L_0x0033:
            r1 = r0
        L_0x0034:
            if (r1 != 0) goto L_0x003a
            java.util.Locale r1 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0059, Throwable -> 0x0016 }
        L_0x003a:
            return r1
        L_0x003b:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0055
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get locale e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x0055:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        L_0x0059:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0074
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get locale e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x0074:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getLocale(android.content.Context):java.util.Locale");
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
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
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

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "Unknown";
            }
            return telephonyManager.getNetworkOperator();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "Unknown";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "Unknown";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003a, code lost:
        android.util.Log.e(TAG, "get sub os name e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0050, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0059, code lost:
        android.util.Log.e(TAG, "get sub os name e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0035 A[ExcHandler: Throwable (r1v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSName(android.content.Context r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.Properties r1 = getBuildProp()     // Catch:{ Exception -> 0x0054, Throwable -> 0x0035 }
            java.lang.String r2 = "ro.miui.ui.version.name"
            java.lang.String r2 = r1.getProperty(r2)     // Catch:{ Exception -> 0x0030, Throwable -> 0x0035 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0030, Throwable -> 0x0035 }
            if (r3 == 0) goto L_0x002c
            boolean r3 = isFlyMe()     // Catch:{ Exception -> 0x0030, Throwable -> 0x0035 }
            if (r3 == 0) goto L_0x001d
            java.lang.String r1 = "Flyme"
            goto L_0x002e
        L_0x001d:
            java.lang.String r1 = getYunOSVersion(r1)     // Catch:{ Exception -> 0x0030, Throwable -> 0x0035 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0030, Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x002a
            java.lang.String r1 = "YunOS"
            goto L_0x002e
        L_0x002a:
            r0 = r2
            goto L_0x0034
        L_0x002c:
            java.lang.String r1 = "MIUI"
        L_0x002e:
            r0 = r1
            goto L_0x0034
        L_0x0030:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)     // Catch:{ Exception -> 0x0054, Throwable -> 0x0035 }
        L_0x0034:
            return r0
        L_0x0035:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0050
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get sub os name e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x0050:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        L_0x0054:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x006f
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get sub os name e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x006f:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSName(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002f, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0031, code lost:
        android.util.Log.e(TAG, "get sub os version e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004a, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004e, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
        android.util.Log.e(TAG, "get sub os version e is " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0066, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c A[ExcHandler: Throwable (r1v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSVersion(android.content.Context r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.Properties r1 = getBuildProp()     // Catch:{ Exception -> 0x004b, Throwable -> 0x002c }
            java.lang.String r2 = "ro.miui.ui.version.name"
            java.lang.String r2 = r1.getProperty(r2)     // Catch:{ Exception -> 0x0027, Throwable -> 0x002c }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0027, Throwable -> 0x002c }
            if (r3 == 0) goto L_0x0025
            boolean r3 = isFlyMe()     // Catch:{ Exception -> 0x0027, Throwable -> 0x002c }
            if (r3 == 0) goto L_0x0020
            java.lang.String r1 = getFlymeVersion(r1)     // Catch:{ Exception -> 0x0025, Throwable -> 0x002c }
        L_0x001e:
            r0 = r1
            goto L_0x002b
        L_0x0020:
            java.lang.String r1 = getYunOSVersion(r1)     // Catch:{ Exception -> 0x0025, Throwable -> 0x002c }
            goto L_0x001e
        L_0x0025:
            r0 = r2
            goto L_0x002b
        L_0x0027:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)     // Catch:{ Exception -> 0x004b, Throwable -> 0x002c }
        L_0x002b:
            return r0
        L_0x002c:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0047
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get sub os version e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x0047:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        L_0x004b:
            r1 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0066
            java.lang.String r2 = "UMUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "get sub os version e is "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L_0x0066:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSVersion(android.content.Context):java.lang.String");
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        return null;
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[SYNTHETIC, Splitter:B:13:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002c A[SYNTHETIC, Splitter:B:19:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties getBuildProp() {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0029, all -> 0x0022 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0029, all -> 0x0022 }
            java.io.File r4 = android.os.Environment.getRootDirectory()     // Catch:{ IOException -> 0x0029, all -> 0x0022 }
            java.lang.String r5 = "build.prop"
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x0029, all -> 0x0022 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0029, all -> 0x0022 }
            r0.load(r2)     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x002f
        L_0x001d:
            r0 = move-exception
            r1 = r2
            goto L_0x0023
        L_0x0020:
            r1 = r2
            goto L_0x002a
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            if (r1 == 0) goto L_0x0028
            r1.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            throw r0
        L_0x0029:
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getBuildProp():java.util.Properties");
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
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

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version code e is " + e);
            return "";
        } catch (Throwable th) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version code e is " + th);
            return "";
        }
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (Exception e) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version code e is " + e);
            return "";
        } catch (Throwable th) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version code e is " + th);
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
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version name e is " + e);
            return "";
        } catch (Throwable th) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "get app version name e is " + th);
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

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0041 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkPermission(android.content.Context r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            r3 = 1
            if (r1 < r2) goto L_0x0033
            java.lang.String r1 = "android.content.Context"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x002e }
            java.lang.String r2 = "checkSelfPermission"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x002e }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r0] = r5     // Catch:{ Exception -> 0x002e }
            java.lang.reflect.Method r1 = r1.getMethod(r2, r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x002e }
            r2[r0] = r7     // Catch:{ Exception -> 0x002e }
            java.lang.Object r7 = r1.invoke(r6, r2)     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x002e }
            int r7 = r7.intValue()     // Catch:{ Exception -> 0x002e }
            if (r7 != 0) goto L_0x0042
            goto L_0x0041
        L_0x002e:
            r7 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r7)
            goto L_0x0042
        L_0x0033:
            android.content.pm.PackageManager r1 = r6.getPackageManager()
            java.lang.String r6 = r6.getPackageName()
            int r6 = r1.checkPermission(r7, r6)
            if (r6 != 0) goto L_0x0042
        L_0x0041:
            r0 = 1
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.checkPermission(android.content.Context, java.lang.String):boolean");
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        android.util.Log.e(TAG, "MD5 e is " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        android.util.Log.e(TAG, "MD5 e is " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007f, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e A[ExcHandler: Throwable (r8v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String MD5(java.lang.String r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            byte[] r1 = r8.getBytes()     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r2.reset()     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r2.update(r1)     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            byte[] r1 = r2.digest()     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r2.<init>()     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r3 = 0
            r4 = 0
        L_0x001f:
            int r5 = r1.length     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            if (r4 >= r5) goto L_0x0039
            java.lang.String r5 = "%02X"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            byte r7 = r1[r4]     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r6[r3] = r7     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            r2.append(r5)     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            int r4 = r4 + 1
            goto L_0x001f
        L_0x0039:
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0040, Throwable -> 0x003e }
            return r1
        L_0x003e:
            r8 = move-exception
            goto L_0x0049
        L_0x0040:
            java.lang.String r1 = "[^[a-z][A-Z][0-9][.][_]]"
            java.lang.String r2 = ""
            java.lang.String r8 = r8.replaceAll(r1, r2)     // Catch:{ Exception -> 0x0064, Throwable -> 0x003e }
            return r8
        L_0x0049:
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x0063
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "MD5 e is "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            android.util.Log.e(r1, r8)
        L_0x0063:
            return r0
        L_0x0064:
            r8 = move-exception
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x007f
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "MD5 e is "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            android.util.Log.e(r1, r8)
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.MD5(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        android.util.Log.e(TAG, "get file MD5 e is " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A[ExcHandler: Throwable (r7v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0003] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileMD5(java.io.File r7) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            r1 = 0
            byte[] r2 = new byte[r0]     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            boolean r3 = r7.isFile()     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            if (r3 != 0) goto L_0x000e
            java.lang.String r7 = ""
            return r7
        L_0x000e:
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            r4.<init>(r7)     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
        L_0x0019:
            r7 = 0
            int r5 = r4.read(r2, r7, r0)     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            r6 = -1
            if (r5 == r6) goto L_0x0025
            r3.update(r2, r7, r5)     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            goto L_0x0019
        L_0x0025:
            r4.close()     // Catch:{ Exception -> 0x003d, Throwable -> 0x003e }
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            byte[] r2 = r3.digest()     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            r3 = 1
            r0.<init>(r3, r2)     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            java.lang.String r2 = "%1$032x"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            r3[r7] = r0     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            java.lang.String r7 = java.lang.String.format(r2, r3)     // Catch:{ Exception -> 0x005a, Throwable -> 0x003e }
            return r7
        L_0x003d:
            return r1
        L_0x003e:
            r7 = move-exception
            boolean r0 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get file MD5 e is "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.e(r0, r7)
        L_0x0059:
            return r1
        L_0x005a:
            r7 = move-exception
            boolean r0 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r0 == 0) goto L_0x0075
            java.lang.String r0 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "get file MD5 e is "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            android.util.Log.e(r0, r7)
        L_0x0075:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getFileMD5(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        android.util.Log.e(TAG, "encrypt by SHA1 e is " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[ExcHandler: Throwable (r4v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encryptBySHA1(java.lang.String r4) {
        /*
            r0 = 0
            byte[] r4 = r4.getBytes()     // Catch:{ Exception -> 0x0034, Throwable -> 0x0018 }
            java.lang.String r1 = "SHA1"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ Exception -> 0x0017, Throwable -> 0x0018 }
            r1.update(r4)     // Catch:{ Exception -> 0x0017, Throwable -> 0x0018 }
            byte[] r4 = r1.digest()     // Catch:{ Exception -> 0x0017, Throwable -> 0x0018 }
            java.lang.String r4 = bytes2Hex(r4)     // Catch:{ Exception -> 0x0017, Throwable -> 0x0018 }
            return r4
        L_0x0017:
            return r0
        L_0x0018:
            r4 = move-exception
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "encrypt by SHA1 e is "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            android.util.Log.e(r1, r4)
        L_0x0033:
            return r0
        L_0x0034:
            r4 = move-exception
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L_0x004f
            java.lang.String r1 = "UMUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "encrypt by SHA1 e is "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            android.util.Log.e(r1, r4)
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.encryptBySHA1(java.lang.String):java.lang.String");
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

    public static String getDeviceToken(Context context) {
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        if (context == null) {
            return null;
        }
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        try {
            Class<?> cls = Class.forName("com.umeng.message.MessageSharedPrefs");
            if (cls == null || (method = cls.getMethod("getInstance", new Class[]{Context.class})) == null || (invoke = method.invoke(cls, new Object[]{origApplicationContext})) == null || (method2 = cls.getMethod("getDeviceToken", new Class[0])) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null || !(invoke2 instanceof String)) {
                return null;
            }
            return (String) invoke2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("UMENG_APPKEY");
            if (string != null) {
                return string.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getChannelByXML(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return null;
            }
            String obj2 = obj.toString();
            if (obj2 != null) {
                return obj2.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean checkPath(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkAndroidManifest(Context context, String str) {
        try {
            StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getActivityInfo(new ComponentName(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName(), str), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
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

    public static boolean checkResource(Context context, String str, String str2) {
        return StubApp.getOrigApplicationContext(context.getApplicationContext()).getResources().getIdentifier(str, str2, StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName()) > 0;
    }

    public static boolean checkMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getApplicationInfo(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo == null || applicationInfo.metaData.get(str) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static String getAppMD5Signature(Context context) {
        String appMD5Signature = DeviceConfig.getAppMD5Signature(context);
        return !TextUtils.isEmpty(appMD5Signature) ? appMD5Signature.replace(":", "").toLowerCase() : appMD5Signature;
    }

    public static String getAppSHA1Key(Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }

    public static String getAppHashKey(Context context) {
        return DeviceConfig.getAppHashKey(context);
    }

    public static int getTargetSdkVersion(Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public static boolean isMainProgress(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
            if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isApplication(Context context) {
        try {
            String name = StubApp.getOrigApplicationContext(context.getApplicationContext()).getClass().getSuperclass().getName();
            if (TextUtils.isEmpty(name) || !name.equals("android.app.Application")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
