package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: BUGLY */
public class b {
    private static final String[] a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static final String[] b = {"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};
    private static final String[] c = {"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};
    private static String d = null;
    private static String e = null;

    public static String d() {
        return "null";
    }

    public static String e() {
        return "null";
    }

    public static String f() {
        return "null";
    }

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = "fail"
            if (r3 != 0) goto L_0x0005
            return r0
        L_0x0005:
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Throwable -> 0x001e }
            java.lang.String r1 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r3, r1)     // Catch:{ Throwable -> 0x001e }
            if (r3 != 0) goto L_0x0019
            java.lang.String r0 = "null"
            goto L_0x002d
        L_0x0014:
            r0 = move-exception
            r2 = r0
            r0 = r3
            r3 = r2
            goto L_0x001f
        L_0x0019:
            java.lang.String r0 = r3.toLowerCase()     // Catch:{ Throwable -> 0x0014 }
            goto L_0x002d
        L_0x001e:
            r3 = move-exception
        L_0x001f:
            boolean r3 = com.tencent.bugly.proguard.x.a(r3)
            if (r3 != 0) goto L_0x002d
            java.lang.String r3 = "Failed to get Android ID."
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.a(r3, r1)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.a(android.content.Context):java.lang.String");
    }

    public static String b(Context context) {
        if (context == null) {
            return "fail";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "fail";
            }
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            return simSerialNumber == null ? "null" : simSerialNumber;
        } catch (Throwable unused) {
            x.a("Failed to get SIM serial number.", new Object[0]);
            return "fail";
        }
    }

    public static String g() {
        try {
            return Build.SERIAL;
        } catch (Throwable unused) {
            x.a("Failed to get hardware serial number.", new Object[0]);
            return "fail";
        }
    }

    private static boolean u() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static String a(Context context, boolean z) {
        String str = null;
        if (z) {
            try {
                String a2 = z.a(context, "ro.product.cpu.abilist");
                if (z.a(a2) || a2.equals("fail")) {
                    a2 = z.a(context, "ro.product.cpu.abi");
                }
                if (!z.a(a2)) {
                    if (!a2.equals("fail")) {
                        x.b(b.class, "ABI list: " + a2, new Object[0]);
                        str = a2.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[0];
                    }
                }
            } catch (Throwable th) {
                if (x.a(th)) {
                    return "fail";
                }
                th.printStackTrace();
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return str;
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x008d A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0092 A[SYNTHETIC, Splitter:B:51:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a2 A[SYNTHETIC, Splitter:B:58:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b6 A[SYNTHETIC, Splitter:B:66:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00c6 A[SYNTHETIC, Splitter:B:73:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long j() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0083, all -> 0x007e }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0083, all -> 0x007e }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0079, all -> 0x0074 }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0079, all -> 0x0074 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x0072 }
            if (r1 != 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0023
        L_0x0019:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0023
            r0.printStackTrace()
        L_0x0023:
            r2.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0031
        L_0x0027:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0031
            r0.printStackTrace()
        L_0x0031:
            r0 = -1
            return r0
        L_0x0034:
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r1 = r1.split(r3, r4)     // Catch:{ Throwable -> 0x0072 }
            r3 = 1
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r3 = "kb"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0072 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x0072 }
            r1 = 10
            long r3 = r3 << r1
            r0.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0063
        L_0x0059:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0063
            r0.printStackTrace()
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0071
        L_0x0067:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0071
            r0.printStackTrace()
        L_0x0071:
            return r3
        L_0x0072:
            r1 = move-exception
            goto L_0x0087
        L_0x0074:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00b4
        L_0x0079:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0087
        L_0x007e:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x00b4
        L_0x0083:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x0087:
            boolean r3 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x00b3 }
            if (r3 != 0) goto L_0x0090
            r1.printStackTrace()     // Catch:{ all -> 0x00b3 }
        L_0x0090:
            if (r0 == 0) goto L_0x00a0
            r0.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x00a0
        L_0x0096:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00a0
            r0.printStackTrace()
        L_0x00a0:
            if (r2 == 0) goto L_0x00b0
            r2.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00b0
        L_0x00a6:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00b0
            r0.printStackTrace()
        L_0x00b0:
            r0 = -2
            return r0
        L_0x00b3:
            r1 = move-exception
        L_0x00b4:
            if (r0 == 0) goto L_0x00c4
            r0.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00c4
        L_0x00ba:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x00c4
            r0.printStackTrace()
        L_0x00c4:
            if (r2 == 0) goto L_0x00d4
            r2.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00d4
        L_0x00ca:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x00d4
            r0.printStackTrace()
        L_0x00d4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.j():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x013e A[SYNTHETIC, Splitter:B:102:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x014e A[SYNTHETIC, Splitter:B:109:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0115 A[Catch:{ all -> 0x013b }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x011a A[SYNTHETIC, Splitter:B:87:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x012a A[SYNTHETIC, Splitter:B:94:0x012a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long k() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x010b, all -> 0x0106 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x010b, all -> 0x0106 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0101, all -> 0x00fc }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0101, all -> 0x00fc }
            r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            r3 = -1
            if (r1 != 0) goto L_0x0037
            r0.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0028
        L_0x001e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0028
            r0.printStackTrace()
        L_0x0028:
            r2.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0036
        L_0x002c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0036
            r0.printStackTrace()
        L_0x0036:
            return r3
        L_0x0037:
            java.lang.String r5 = ":\\s+"
            r6 = 2
            java.lang.String[] r1 = r1.split(r5, r6)     // Catch:{ Throwable -> 0x00fa }
            r5 = 1
            r1 = r1[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = "kb"
            java.lang.String r8 = ""
            java.lang.String r1 = r1.replace(r7, r8)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x00fa }
            r7 = 0
            long r9 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x00fa }
            r1 = 10
            long r9 = r9 << r1
            long r9 = r9 + r7
            java.lang.String r7 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            if (r7 != 0) goto L_0x007e
            r0.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006f
        L_0x0065:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x006f
            r0.printStackTrace()
        L_0x006f:
            r2.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007d
        L_0x0073:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x007d
            r0.printStackTrace()
        L_0x007d:
            return r3
        L_0x007e:
            java.lang.String r8 = ":\\s+"
            java.lang.String[] r7 = r7.split(r8, r6)     // Catch:{ Throwable -> 0x00fa }
            r7 = r7[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = r7.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r8 = "kb"
            java.lang.String r11 = ""
            java.lang.String r7 = r7.replace(r8, r11)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = r7.trim()     // Catch:{ Throwable -> 0x00fa }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ Throwable -> 0x00fa }
            long r7 = r7 << r1
            long r9 = r9 + r7
            java.lang.String r7 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            if (r7 != 0) goto L_0x00bf
            r0.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00b0
        L_0x00a6:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00b0
            r0.printStackTrace()
        L_0x00b0:
            r2.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00be
        L_0x00b4:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00be
            r0.printStackTrace()
        L_0x00be:
            return r3
        L_0x00bf:
            java.lang.String r3 = ":\\s+"
            java.lang.String[] r3 = r7.split(r3, r6)     // Catch:{ Throwable -> 0x00fa }
            r3 = r3[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r4 = "kb"
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r3 = r3.trim()     // Catch:{ Throwable -> 0x00fa }
            long r3 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00fa }
            long r3 = r3 << r1
            long r9 = r9 + r3
            r0.close()     // Catch:{ IOException -> 0x00e1 }
            goto L_0x00eb
        L_0x00e1:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00eb
            r0.printStackTrace()
        L_0x00eb:
            r2.close()     // Catch:{ IOException -> 0x00ef }
            goto L_0x00f9
        L_0x00ef:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00f9
            r0.printStackTrace()
        L_0x00f9:
            return r9
        L_0x00fa:
            r1 = move-exception
            goto L_0x010f
        L_0x00fc:
            r0 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
            goto L_0x013c
        L_0x0101:
            r0 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
            goto L_0x010f
        L_0x0106:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x013c
        L_0x010b:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x010f:
            boolean r3 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x013b }
            if (r3 != 0) goto L_0x0118
            r1.printStackTrace()     // Catch:{ all -> 0x013b }
        L_0x0118:
            if (r0 == 0) goto L_0x0128
            r0.close()     // Catch:{ IOException -> 0x011e }
            goto L_0x0128
        L_0x011e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0128
            r0.printStackTrace()
        L_0x0128:
            if (r2 == 0) goto L_0x0138
            r2.close()     // Catch:{ IOException -> 0x012e }
            goto L_0x0138
        L_0x012e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0138
            r0.printStackTrace()
        L_0x0138:
            r0 = -2
            return r0
        L_0x013b:
            r1 = move-exception
        L_0x013c:
            if (r0 == 0) goto L_0x014c
            r0.close()     // Catch:{ IOException -> 0x0142 }
            goto L_0x014c
        L_0x0142:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x014c
            r0.printStackTrace()
        L_0x014c:
            if (r2 == 0) goto L_0x015c
            r2.close()     // Catch:{ IOException -> 0x0152 }
            goto L_0x015c
        L_0x0152:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x015c
            r0.printStackTrace()
        L_0x015c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.k():long");
    }

    public static long l() {
        if (!u()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static long m() {
        if (!u()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static String n() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String o() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String c(Context context) {
        TelephonyManager telephonyManager;
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                int networkType = telephonyManager.getNetworkType();
                switch (networkType) {
                    case 1:
                        return "GPRS";
                    case 2:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case 4:
                        return "CDMA";
                    case 5:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "iDen";
                    case 12:
                        return "EVDO_B";
                    case 13:
                        return "LTE";
                    case 14:
                        return "eHRPD";
                    case 15:
                        return "HSPA+";
                    default:
                        str = "MOBILE(" + networkType + ")";
                        break;
                }
            }
            return str;
        } catch (Exception e2) {
            if (!x.a(e2)) {
                e2.printStackTrace();
            }
        }
    }

    public static String d(Context context) {
        String a2 = z.a(context, "ro.miui.ui.version.name");
        if (z.a(a2) || a2.equals("fail")) {
            String a3 = z.a(context, "ro.build.version.emui");
            if (z.a(a3) || a3.equals("fail")) {
                String a4 = z.a(context, "ro.lenovo.series");
                if (z.a(a4) || a4.equals("fail")) {
                    String a5 = z.a(context, "ro.build.nubia.rom.name");
                    if (z.a(a5) || a5.equals("fail")) {
                        String a6 = z.a(context, "ro.meizu.product.model");
                        if (z.a(a6) || a6.equals("fail")) {
                            String a7 = z.a(context, "ro.build.version.opporom");
                            if (z.a(a7) || a7.equals("fail")) {
                                String a8 = z.a(context, "ro.vivo.os.build.display.id");
                                if (z.a(a8) || a8.equals("fail")) {
                                    String a9 = z.a(context, "ro.aa.romver");
                                    if (z.a(a9) || a9.equals("fail")) {
                                        String a10 = z.a(context, "ro.lewa.version");
                                        if (z.a(a10) || a10.equals("fail")) {
                                            String a11 = z.a(context, "ro.gn.gnromvernumber");
                                            if (z.a(a11) || a11.equals("fail")) {
                                                String a12 = z.a(context, "ro.build.tyd.kbstyle_version");
                                                if (z.a(a12) || a12.equals("fail")) {
                                                    return z.a(context, "ro.build.fingerprint") + "/" + z.a(context, "ro.build.rom.id");
                                                }
                                                return "dido/" + a12;
                                            }
                                            return "amigo/" + a11 + "/" + z.a(context, "ro.build.display.id");
                                        }
                                        return "tcl/" + a10 + "/" + z.a(context, "ro.build.display.id");
                                    }
                                    return "htc/" + a9 + "/" + z.a(context, "ro.build.description");
                                }
                                return "vivo/FUNTOUCH/" + a8;
                            }
                            return "Oppo/COLOROS/" + a7;
                        }
                        return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
                    }
                    return "Zte/NUBIA/" + a5 + "_" + z.a(context, "ro.build.nubia.rom.code");
                }
                String a13 = z.a(context, "ro.build.version.incremental");
                return "Lenovo/VIBE/" + a13;
            }
            return "HuaWei/EMOTION/" + a3;
        }
        return "XiaoMi/MIUI/" + a2;
    }

    public static String e(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean p() {
        boolean z;
        String[] strArr = a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (new File(strArr[i]).exists()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x009e A[SYNTHETIC, Splitter:B:47:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00aa A[SYNTHETIC, Splitter:B:55:0x00aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String q() {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            r1.<init>()     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            java.lang.String r3 = "/sys/block/mmcblk0/device/type"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            if (r2 == 0) goto L_0x002c
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/type"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a7, all -> 0x0099 }
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            if (r3 == 0) goto L_0x0028
            r1.append(r3)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
        L_0x0028:
            r2.close()     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            goto L_0x002d
        L_0x002c:
            r2 = r0
        L_0x002d:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/name"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            boolean r3 = r3.exists()     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            if (r3 == 0) goto L_0x005e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r5 = "/sys/block/mmcblk0/device/name"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r2 == 0) goto L_0x0054
            r1.append(r2)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
        L_0x0054:
            r3.close()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            r2 = r3
            goto L_0x005e
        L_0x0059:
            r0 = move-exception
            r2 = r3
            goto L_0x009c
        L_0x005c:
            r2 = r3
            goto L_0x00a8
        L_0x005e:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/cid"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            boolean r3 = r3.exists()     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            if (r3 == 0) goto L_0x0086
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r5 = "/sys/block/mmcblk0/device/cid"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r2 == 0) goto L_0x0085
            r1.append(r2)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
        L_0x0085:
            r2 = r3
        L_0x0086:
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0095, all -> 0x0097 }
            if (r2 == 0) goto L_0x0094
            r2.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0094:
            return r1
        L_0x0095:
            goto L_0x00a8
        L_0x0097:
            r0 = move-exception
            goto L_0x009c
        L_0x0099:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009c:
            if (r2 == 0) goto L_0x00a6
            r2.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x00a6
        L_0x00a2:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00a6:
            throw r0
        L_0x00a7:
            r2 = r0
        L_0x00a8:
            if (r2 == 0) goto L_0x00b2
            r2.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00b2
        L_0x00ae:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00b2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.q():java.lang.String");
    }

    public static String f(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "ro.genymotion.version");
        if (a2 != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(a2);
            sb.append("\n");
        }
        String a3 = z.a(context, "androVM.vbox_dpi");
        if (a3 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(a3);
            sb.append("\n");
        }
        String a4 = z.a(context, "qemu.sf.fake_camera");
        if (a4 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(a4);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c A[Catch:{ Throwable -> 0x0091 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a0 A[SYNTHETIC, Splitter:B:36:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b0 A[SYNTHETIC, Splitter:B:44:0x00b0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String g(android.content.Context r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = d
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = "ro.secure"
            java.lang.String r1 = com.tencent.bugly.proguard.z.a((android.content.Context) r5, (java.lang.String) r1)
            d = r1
        L_0x0011:
            java.lang.String r1 = d
            if (r1 == 0) goto L_0x002a
            java.lang.String r1 = "ro.secure"
            r0.append(r1)
            java.lang.String r1 = "|"
            r0.append(r1)
            java.lang.String r1 = d
            r0.append(r1)
            java.lang.String r1 = "\n"
            r0.append(r1)
        L_0x002a:
            java.lang.String r1 = e
            if (r1 != 0) goto L_0x0036
            java.lang.String r1 = "ro.debuggable"
            java.lang.String r5 = com.tencent.bugly.proguard.z.a((android.content.Context) r5, (java.lang.String) r1)
            e = r5
        L_0x0036:
            java.lang.String r5 = e
            if (r5 == 0) goto L_0x004f
            java.lang.String r5 = "ro.debuggable"
            r0.append(r5)
            java.lang.String r5 = "|"
            r0.append(r5)
            java.lang.String r5 = e
            r0.append(r5)
            java.lang.String r5 = "\n"
            r0.append(r5)
        L_0x004f:
            r5 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0097, all -> 0x0093 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0097, all -> 0x0093 }
            java.lang.String r3 = "/proc/self/status"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0097, all -> 0x0093 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        L_0x005c:
            java.lang.String r5 = r1.readLine()     // Catch:{ Throwable -> 0x0091 }
            if (r5 == 0) goto L_0x006a
            java.lang.String r2 = "TracerPid:"
            boolean r2 = r5.startsWith(r2)     // Catch:{ Throwable -> 0x0091 }
            if (r2 == 0) goto L_0x005c
        L_0x006a:
            if (r5 == 0) goto L_0x0084
            r2 = 10
            java.lang.String r5 = r5.substring(r2)     // Catch:{ Throwable -> 0x0091 }
            java.lang.String r5 = r5.trim()     // Catch:{ Throwable -> 0x0091 }
            java.lang.String r2 = "tracer_pid"
            r0.append(r2)     // Catch:{ Throwable -> 0x0091 }
            java.lang.String r2 = "|"
            r0.append(r2)     // Catch:{ Throwable -> 0x0091 }
            r0.append(r5)     // Catch:{ Throwable -> 0x0091 }
        L_0x0084:
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0091 }
            r1.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0090:
            return r5
        L_0x0091:
            r5 = move-exception
            goto L_0x009b
        L_0x0093:
            r0 = move-exception
            r1 = r5
            r5 = r0
            goto L_0x00ae
        L_0x0097:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
        L_0x009b:
            com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00ad }
            if (r1 == 0) goto L_0x00a8
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r5 = move-exception
            com.tencent.bugly.proguard.x.a(r5)
        L_0x00a8:
            java.lang.String r5 = r0.toString()
            return r5
        L_0x00ad:
            r5 = move-exception
        L_0x00ae:
            if (r1 == 0) goto L_0x00b8
            r1.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00b8
        L_0x00b4:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x00b8:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.g(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b7 A[SYNTHETIC, Splitter:B:43:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c2 A[SYNTHETIC, Splitter:B:49:0x00c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String r() {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r3 = "/sys/class/power_supply/ac/online"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            if (r2 == 0) goto L_0x003f
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r4 = "/sys/class/power_supply/ac/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            if (r1 == 0) goto L_0x0033
            java.lang.String r3 = "ac_online"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
        L_0x0033:
            r2.close()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r1 = r2
            goto L_0x003f
        L_0x0038:
            r0 = move-exception
            r1 = r2
            goto L_0x00b5
        L_0x003c:
            r1 = r2
            goto L_0x00c0
        L_0x003f:
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r3 = "/sys/class/power_supply/usb/online"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            if (r2 == 0) goto L_0x0076
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r4 = "/sys/class/power_supply/usb/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            if (r1 == 0) goto L_0x0072
            java.lang.String r3 = "usb_online"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
        L_0x0072:
            r2.close()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r1 = r2
        L_0x0076:
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r3 = "/sys/class/power_supply/battery/capacity"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            if (r2 == 0) goto L_0x00ac
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r4 = "/sys/class/power_supply/battery/capacity"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00b4 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            if (r1 == 0) goto L_0x00a8
            java.lang.String r3 = "battery_capacity"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
        L_0x00a8:
            r2.close()     // Catch:{ Throwable -> 0x003c, all -> 0x0038 }
            r1 = r2
        L_0x00ac:
            if (r1 == 0) goto L_0x00ca
            r1.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x00ca
        L_0x00b2:
            goto L_0x00c0
        L_0x00b4:
            r0 = move-exception
        L_0x00b5:
            if (r1 == 0) goto L_0x00bf
            r1.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00bf
        L_0x00bb:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00bf:
            throw r0
        L_0x00c0:
            if (r1 == 0) goto L_0x00ca
            r1.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x00ca
        L_0x00c6:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00ca:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.r():java.lang.String");
    }

    public static String h(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "gsm.sim.state");
        if (a2 != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(a2);
        }
        sb.append("\n");
        String a3 = z.a(context, "gsm.sim.state2");
        if (a3 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(a3);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0044 A[SYNTHETIC, Splitter:B:22:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004b A[SYNTHETIC, Splitter:B:27:0x004b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long s() {
        /*
            r0 = 0
            r1 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x003b }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x003b }
            java.lang.String r5 = "/proc/uptime"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x003b }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x003b }
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            if (r2 == 0) goto L_0x002b
            java.lang.String r4 = " "
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            r2 = r2[r0]     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r6
            float r4 = (float) r4     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ Throwable -> 0x0037, all -> 0x0034 }
            float r4 = r4 - r2
            r1 = r4
        L_0x002b:
            r3.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0047
        L_0x002f:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
            goto L_0x0047
        L_0x0034:
            r0 = move-exception
            r2 = r3
            goto L_0x0049
        L_0x0037:
            r2 = r3
            goto L_0x003b
        L_0x0039:
            r0 = move-exception
            goto L_0x0049
        L_0x003b:
            java.lang.String r3 = "Failed to get boot time of device."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0039 }
            com.tencent.bugly.proguard.x.a(r3, r0)     // Catch:{ all -> 0x0039 }
            if (r2 == 0) goto L_0x0047
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x0047:
            long r0 = (long) r1
            return r0
        L_0x0049:
            if (r2 == 0) goto L_0x0053
            r2.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x0053:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.s():long");
    }

    public static boolean i(Context context) {
        if (k(context) != null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            if (i == 0) {
                if (new File(c[i]).exists()) {
                }
            } else if (!new File(c[i]).exists()) {
            }
            arrayList.add(Integer.valueOf(i));
        }
        return (arrayList.isEmpty() ? null : arrayList.toString()) != null;
    }

    private static String k(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < b.length; i++) {
            try {
                packageManager.getPackageInfo(b[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toString();
    }

    public static boolean j(Context context) {
        return (((l(context) | w()) | x()) | v()) > 0;
    }

    private static int v() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            if (method.invoke((Object) null, new Object[0]).getClass().getName().startsWith("$Proxy")) {
                return 256;
            }
            return 0;
        } catch (Exception unused) {
            return 256;
        }
    }

    private static int l(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception unused) {
            i = 0;
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception unused2) {
            return i;
        }
    }

    private static int w() {
        try {
            throw new Exception("detect hook");
        } catch (Exception e2) {
            int i = 0;
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    i |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i2 = i2 + 1) == 2) {
                    i |= 32;
                }
            }
            return i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d A[SYNTHETIC, Splitter:B:34:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a9 A[SYNTHETIC, Splitter:B:41:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b5 A[SYNTHETIC, Splitter:B:48:0x00b5] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bc A[SYNTHETIC, Splitter:B:53:0x00bc] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x00b0=Splitter:B:45:0x00b0, B:38:0x00a4=Splitter:B:38:0x00a4, B:31:0x0098=Splitter:B:31:0x0098} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int x() {
        /*
            r0 = 0
            r1 = 0
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            r2.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.lang.String r7 = "/proc/"
            r6.<init>(r7)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            int r7 = android.os.Process.myPid()     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.lang.String r7 = "/maps"
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.lang.String r6 = r6.toString()     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            r5.<init>(r6)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            java.lang.String r6 = "utf-8"
            r4.<init>(r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
            r3.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ad, FileNotFoundException -> 0x00a1, IOException -> 0x0095, all -> 0x0092 }
        L_0x0030:
            java.lang.String r1 = r3.readLine()     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r1 == 0) goto L_0x0056
            java.lang.String r4 = ".so"
            boolean r4 = r1.endsWith(r4)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r4 != 0) goto L_0x0046
            java.lang.String r4 = ".jar"
            boolean r4 = r1.endsWith(r4)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r4 == 0) goto L_0x0030
        L_0x0046:
            java.lang.String r4 = " "
            int r4 = r1.lastIndexOf(r4)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            int r4 = r4 + 1
            java.lang.String r1 = r1.substring(r4)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            r2.add(r1)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            goto L_0x0030
        L_0x0056:
            java.util.Iterator r1 = r2.iterator()     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
        L_0x005a:
            boolean r2 = r1.hasNext()     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r2 == 0) goto L_0x0083
            java.lang.Object r2 = r1.next()     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            java.lang.String r5 = "xposed"
            boolean r4 = r4.contains(r5)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r4 == 0) goto L_0x0076
            r0 = r0 | 64
        L_0x0076:
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            java.lang.String r4 = "com.saurik.substrate"
            boolean r2 = r2.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x0090, FileNotFoundException -> 0x008e, IOException -> 0x008c }
            if (r2 == 0) goto L_0x005a
            r0 = r0 | 128(0x80, float:1.794E-43)
            goto L_0x005a
        L_0x0083:
            r3.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x00b8
        L_0x0087:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00b8
        L_0x008c:
            r1 = move-exception
            goto L_0x0098
        L_0x008e:
            r1 = move-exception
            goto L_0x00a4
        L_0x0090:
            r1 = move-exception
            goto L_0x00b0
        L_0x0092:
            r0 = move-exception
            r3 = r1
            goto L_0x00ba
        L_0x0095:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x0098:
            r1.printStackTrace()     // Catch:{ all -> 0x00b9 }
            if (r3 == 0) goto L_0x00b8
            r3.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x00b8
        L_0x00a1:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x00a4:
            r1.printStackTrace()     // Catch:{ all -> 0x00b9 }
            if (r3 == 0) goto L_0x00b8
            r3.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x00b8
        L_0x00ad:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x00b0:
            r1.printStackTrace()     // Catch:{ all -> 0x00b9 }
            if (r3 == 0) goto L_0x00b8
            r3.close()     // Catch:{ IOException -> 0x0087 }
        L_0x00b8:
            return r0
        L_0x00b9:
            r0 = move-exception
        L_0x00ba:
            if (r3 == 0) goto L_0x00c4
            r3.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00c4
        L_0x00c0:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00c4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.x():int");
    }

    public static boolean t() {
        double maxMemory = (double) Runtime.getRuntime().maxMemory();
        Double.isNaN(maxMemory);
        float f = (float) (maxMemory / 1048576.0d);
        double d2 = (double) Runtime.getRuntime().totalMemory();
        Double.isNaN(d2);
        float f2 = (float) (d2 / 1048576.0d);
        float f3 = f - f2;
        x.c("maxMemory : %f", Float.valueOf(f));
        x.c("totalMemory : %f", Float.valueOf(f2));
        x.c("freeMemory : %f", Float.valueOf(f3));
        if (f3 < 10.0f) {
            return true;
        }
        return false;
    }
}
