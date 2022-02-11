package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* compiled from: UMProbe */
public class l {
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e = new Object();

    public static String a(Context context) {
        try {
            SharedPreferences sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences(a, 0);
            if (sharedPreferences == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            synchronized (e) {
                jSONObject.put(b, sharedPreferences.getString(b, ""));
                jSONObject.put(c, sharedPreferences.getString(c, ""));
                jSONObject.put(d, sharedPreferences.getString(d, ""));
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static void b(final Context context) {
        if (!c(context)) {
            final String[] strArr = {"unknown", "unknown", "unknown"};
            new Thread() {
                public void run() {
                    super.run();
                    try {
                        strArr[0] = l.c();
                        strArr[1] = l.a();
                        strArr[2] = l.b();
                        ULog.i("diskType = " + strArr[0] + "; ThremalZone = " + strArr[1] + "; GoldFishRc = " + strArr[2]);
                        l.b(context, strArr);
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(context, th);
                    }
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context != null && (sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences(a, 0)) != null) {
            synchronized (e) {
                sharedPreferences.edit().putString(b, strArr[0]).putString(c, strArr[1]).putString(d, strArr[2]).commit();
            }
        }
    }

    public static boolean c(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences(a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(b, ""))) {
            return false;
        }
        return true;
    }

    public static int a(String str, String str2) throws IOException {
        int i;
        Process exec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (readLine.contains(str2)) {
                    i = 1;
                    break;
                }
            } else {
                i = -1;
                break;
            }
        }
        try {
            if (exec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException unused) {
            return -1;
        }
    }

    public static String a() {
        int i;
        try {
            i = a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable unused) {
            i = -1;
        }
        if (i > 0) {
            return "thermal_zone";
        }
        return i < 0 ? "noper" : "unknown";
    }

    public static String b() {
        int i;
        try {
            i = a("ls /", "goldfish");
        } catch (Throwable unused) {
            i = -1;
        }
        if (i > 0) {
            return "goldfish";
        }
        return i < 0 ? "noper" : "unknown";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003b A[SYNTHETIC, Splitter:B:21:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c() {
        /*
            java.lang.String r0 = "unknown"
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0036 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0036 }
            java.lang.String r4 = "/proc/diskstats"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0036 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0036 }
            java.lang.String r1 = "mmcblk"
            java.lang.String r3 = "sda"
            java.lang.String r4 = "mtd"
        L_0x0015:
            java.lang.String r5 = r2.readLine()     // Catch:{ Throwable -> 0x0037 }
            if (r5 == 0) goto L_0x0039
            boolean r6 = r5.contains(r1)     // Catch:{ Throwable -> 0x0037 }
            if (r6 == 0) goto L_0x0024
            java.lang.String r0 = "mmcblk"
            goto L_0x0039
        L_0x0024:
            boolean r6 = r5.contains(r3)     // Catch:{ Throwable -> 0x0037 }
            if (r6 == 0) goto L_0x002d
            java.lang.String r0 = "sda"
            goto L_0x0039
        L_0x002d:
            boolean r5 = r5.contains(r4)     // Catch:{ Throwable -> 0x0037 }
            if (r5 == 0) goto L_0x0015
            java.lang.String r0 = "mtd"
            goto L_0x0039
        L_0x0036:
            r2 = r1
        L_0x0037:
            java.lang.String r0 = "noper"
        L_0x0039:
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ Throwable -> 0x003e }
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.l.c():java.lang.String");
    }
}
