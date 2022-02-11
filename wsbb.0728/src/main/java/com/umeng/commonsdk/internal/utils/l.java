package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class l {
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e = new Object();

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
        } catch (InterruptedException e2) {
            return -1;
        }
    }

    public static String a() {
        int i;
        try {
            i = a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable th) {
            i = -1;
        }
        return i > 0 ? "thermal_zone" : i < 0 ? "noper" : "unknown";
    }

    public static String a(Context context) {
        try {
            SharedPreferences sharedPreferences = StubApp.getOrigApplicationContext(context.getApplicationContext()).getSharedPreferences(a, 0);
            if (sharedPreferences != null) {
                JSONObject jSONObject = new JSONObject();
                synchronized (e) {
                    jSONObject.put(b, sharedPreferences.getString(b, ""));
                    jSONObject.put(c, sharedPreferences.getString(c, ""));
                    jSONObject.put(d, sharedPreferences.getString(d, ""));
                }
                return jSONObject.toString();
            }
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
        }
        return null;
    }

    public static String b() {
        int i;
        try {
            i = a("ls /", "goldfish");
        } catch (Throwable th) {
            i = -1;
        }
        return i > 0 ? "goldfish" : i < 0 ? "noper" : "unknown";
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

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020 A[SYNTHETIC, Splitter:B:10:0x0020] */
    public static String c() {
        BufferedReader bufferedReader;
        String str = "unknown";
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/diskstats"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (!readLine.contains("mmcblk")) {
                            if (!readLine.contains("sda")) {
                                if (readLine.contains("mtd")) {
                                    str = "mtd";
                                    break;
                                }
                            } else {
                                str = "sda";
                                break;
                            }
                        } else {
                            str = "mmcblk";
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
            bufferedReader = null;
            str = "noper";
            if (bufferedReader != null) {
            }
            return str;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Throwable th3) {
                return str;
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r1 = com.stub.StubApp.getOrigApplicationContext(r4.getApplicationContext()).getSharedPreferences(a, 0);
     */
    public static boolean c(Context context) {
        SharedPreferences sharedPreferences;
        return (context == null || sharedPreferences == null || TextUtils.isEmpty(sharedPreferences.getString(b, ""))) ? false : true;
    }
}
