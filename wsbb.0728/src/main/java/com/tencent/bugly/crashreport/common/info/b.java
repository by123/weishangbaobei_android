package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class b {
    private static final String[] a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static final String[] b = {"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};
    private static final String[] c = {"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};
    private static String d = null;
    private static String e = null;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "fail";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (string == null) {
                return "null";
            }
            try {
                return string.toLowerCase();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (x.a(th)) {
            return "fail";
        }
        x.a("Failed to get Android ID.", new Object[0]);
        return "fail";
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e A[Catch:{ Throwable -> 0x0061 }] */
    public static String a(Context context, boolean z) {
        String str;
        if (z) {
            try {
                String a2 = z.a(context, "ro.product.cpu.abilist");
                if (z.a(a2) || a2.equals("fail")) {
                    a2 = z.a(context, "ro.product.cpu.abi");
                }
                if (!z.a(a2)) {
                    if (a2.equals("fail")) {
                        str = null;
                    } else {
                        x.b(b.class, "ABI list: " + a2, new Object[0]);
                        str = a2.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[0];
                    }
                    if (str == null) {
                        str = System.getProperty("os.arch");
                    }
                    return str;
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        str = null;
        if (str == null) {
        }
        return str;
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b(Context context) {
        if (context == null) {
            return "fail";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String simSerialNumber = telephonyManager.getSimSerialNumber();
                return simSerialNumber == null ? "null" : simSerialNumber;
            }
        } catch (Throwable th) {
            x.a("Failed to get SIM serial number.", new Object[0]);
        }
        return "fail";
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String c(Context context) {
        TelephonyManager telephonyManager;
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
                        return "MOBILE(" + networkType + ")";
                }
            }
            return "unknown";
        } catch (Exception e2) {
            if (!x.a(e2)) {
                e2.printStackTrace();
                return "unknown";
            }
        }
    }

    public static String d() {
        return "null";
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

    public static String e() {
        return "null";
    }

    public static String e(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static String f() {
        return "null";
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

    public static String g() {
        try {
            return Build.SERIAL;
        } catch (Throwable th) {
            x.a("Failed to get hardware serial number.", new Object[0]);
            return "fail";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a A[Catch:{ Throwable -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095 A[SYNTHETIC, Splitter:B:31:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a6 A[SYNTHETIC, Splitter:B:39:0x00a6] */
    public static String g(Context context) {
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        StringBuilder sb = new StringBuilder();
        if (d == null) {
            d = z.a(context, "ro.secure");
        }
        if (d != null) {
            sb.append("ro.secure");
            sb.append("|");
            sb.append(d);
            sb.append("\n");
        }
        if (e == null) {
            e = z.a(context, "ro.debuggable");
        }
        if (e != null) {
            sb.append("ro.debuggable");
            sb.append("|");
            sb.append(e);
            sb.append("\n");
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null || readLine.startsWith("TracerPid:")) {
                        if (readLine != null) {
                            String trim = readLine.substring(10).trim();
                            sb.append("tracer_pid");
                            sb.append("|");
                            sb.append(trim);
                        }
                    }
                    readLine = bufferedReader.readLine();
                    break;
                } catch (Throwable th) {
                    th = th;
                    try {
                        x.a(th);
                        if (bufferedReader != null) {
                        }
                        return sb.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                x.a(e2);
                            }
                        }
                        throw th;
                    }
                }
            } while (readLine.startsWith("TracerPid:"));
            if (readLine != null) {
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
                return sb2;
            } catch (IOException e3) {
                x.a(e3);
                return sb2;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
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

    public static boolean i(Context context) {
        if (k(context) == null) {
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
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078 A[SYNTHETIC, Splitter:B:42:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007d A[SYNTHETIC, Splitter:B:45:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A[Catch:{ all -> 0x00cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x008e A[SYNTHETIC, Splitter:B:55:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0093 A[SYNTHETIC, Splitter:B:58:0x0093] */
    public static long j() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e3) {
                            if (!x.a(e3)) {
                                e3.printStackTrace();
                            }
                        }
                        return -1;
                    }
                    long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        if (!x.a(e4)) {
                            e4.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                        return parseLong;
                    } catch (IOException e5) {
                        if (!x.a(e5)) {
                            e5.printStackTrace();
                        }
                        return parseLong;
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    if (!x.a(e6)) {
                        e6.printStackTrace();
                    }
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e7) {
                    if (!x.a(e7)) {
                        e7.printStackTrace();
                    }
                }
            }
            throw th;
        }
    }

    public static boolean j(Context context) {
        return (((l(context) | w()) | x()) | v()) > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x010e A[SYNTHETIC, Splitter:B:79:0x010e] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0113 A[SYNTHETIC, Splitter:B:82:0x0113] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x011f A[Catch:{ all -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0124 A[SYNTHETIC, Splitter:B:92:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0129 A[SYNTHETIC, Splitter:B:95:0x0129] */
    public static long k() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                try {
                    bufferedReader.readLine();
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (IOException e3) {
                            if (!x.a(e3)) {
                                e3.printStackTrace();
                            }
                            return -1;
                        }
                    } else {
                        long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim());
                        String readLine2 = bufferedReader.readLine();
                        if (readLine2 == null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                if (!x.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                            try {
                                fileReader.close();
                                return -1;
                            } catch (IOException e5) {
                                if (!x.a(e5)) {
                                    e5.printStackTrace();
                                }
                                return -1;
                            }
                        } else {
                            long parseLong2 = Long.parseLong(readLine2.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim());
                            String readLine3 = bufferedReader.readLine();
                            if (readLine3 == null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e6) {
                                    if (!x.a(e6)) {
                                        e6.printStackTrace();
                                    }
                                }
                                try {
                                    fileReader.close();
                                    return -1;
                                } catch (IOException e7) {
                                    if (!x.a(e7)) {
                                        e7.printStackTrace();
                                    }
                                    return -1;
                                }
                            } else {
                                long parseLong3 = (Long.parseLong(readLine3.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + (parseLong << 10) + 0 + (parseLong2 << 10);
                                try {
                                    bufferedReader.close();
                                } catch (IOException e8) {
                                    if (!x.a(e8)) {
                                        e8.printStackTrace();
                                    }
                                }
                                try {
                                    fileReader.close();
                                    return parseLong3;
                                } catch (IOException e9) {
                                    if (!x.a(e9)) {
                                        e9.printStackTrace();
                                    }
                                    return parseLong3;
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                if (fileReader != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
            }
            if (fileReader != null) {
            }
            throw th;
        }
    }

    private static String k(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < b.length; i++) {
            try {
                packageManager.getPackageInfo(b[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException e2) {
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toString();
    }

    private static int l(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception e2) {
            i = 0;
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception e3) {
            return i;
        }
    }

    public static long l() {
        if (!u()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            int blockSize = statFs.getBlockSize();
            return ((long) blockSize) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long m() {
        if (!u()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            int blockSize = statFs.getBlockSize();
            return ((long) blockSize) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String n() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String o() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:38:0x008d A[SYNTHETIC, Splitter:B:38:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009a A[SYNTHETIC, Splitter:B:46:0x009a] */
    public static String q() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader4;
        String str = null;
        try {
            StringBuilder sb = new StringBuilder();
            if (new File("/sys/block/mmcblk0/device/type").exists()) {
                bufferedReader3 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/type"));
                try {
                    String readLine = bufferedReader3.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    }
                    bufferedReader3.close();
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader4 = bufferedReader3;
                    th = th;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            x.a(e2);
                        }
                    }
                    throw th;
                }
            } else {
                bufferedReader3 = null;
            }
            sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            if (new File("/sys/block/mmcblk0/device/name").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/name"));
                try {
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 != null) {
                        sb.append(readLine2);
                    }
                    bufferedReader.close();
                } catch (Throwable th4) {
                    th2 = th4;
                    bufferedReader2 = bufferedReader;
                    th = th2;
                    if (bufferedReader2 != null) {
                    }
                    throw th;
                }
            } else {
                bufferedReader = bufferedReader3;
            }
            try {
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                if (new File("/sys/block/mmcblk0/device/cid").exists()) {
                    BufferedReader bufferedReader5 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/cid"));
                    try {
                        String readLine3 = bufferedReader5.readLine();
                        if (readLine3 != null) {
                            sb.append(readLine3);
                        }
                        bufferedReader = bufferedReader5;
                    } catch (Throwable th5) {
                        th2 = th5;
                        bufferedReader2 = bufferedReader5;
                        th = th2;
                        if (bufferedReader2 != null) {
                        }
                        throw th;
                    }
                }
                str = sb.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        x.a(e3);
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                bufferedReader4 = bufferedReader;
                th = th;
                if (bufferedReader2 != null) {
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cb, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cc, code lost:
        r2 = r1;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d8, code lost:
        r2 = r0;
        r3 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ad A[SYNTHETIC, Splitter:B:40:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bb A[SYNTHETIC, Splitter:B:47:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r1 
  PHI: (r1v8 java.io.BufferedReader) = (r1v15 java.io.BufferedReader), (r1v16 java.io.BufferedReader) binds: [B:26:0x0088, B:15:0x0053] A[DONT_GENERATE, DONT_INLINE], Splitter:B:15:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c9 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r0 
  PHI: (r0v11 java.io.BufferedReader) = (r0v10 java.io.BufferedReader), (r0v13 java.io.BufferedReader) binds: [B:11:0x0037, B:22:0x006c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:11:0x0037] */
    public static String r() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        StringBuilder sb = new StringBuilder();
        try {
            if (new File("/sys/class/power_supply/ac/online").exists()) {
                bufferedReader3 = new BufferedReader(new FileReader("/sys/class/power_supply/ac/online"));
                try {
                    String readLine = bufferedReader3.readLine();
                    if (readLine != null) {
                        sb.append("ac_online");
                        sb.append("|");
                        sb.append(readLine);
                    }
                    bufferedReader3.close();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader3;
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
            } else {
                bufferedReader3 = null;
            }
            try {
                sb.append("\n");
                if (new File("/sys/class/power_supply/usb/online").exists()) {
                    bufferedReader2 = new BufferedReader(new FileReader("/sys/class/power_supply/usb/online"));
                    try {
                        String readLine2 = bufferedReader2.readLine();
                        if (readLine2 != null) {
                            sb.append("usb_online");
                            sb.append("|");
                            sb.append(readLine2);
                        }
                        bufferedReader2.close();
                        bufferedReader3 = bufferedReader2;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                }
                sb.append("\n");
                if (new File("/sys/class/power_supply/battery/capacity").exists()) {
                    bufferedReader2 = new BufferedReader(new FileReader("/sys/class/power_supply/battery/capacity"));
                    String readLine3 = bufferedReader2.readLine();
                    if (readLine3 != null) {
                        sb.append("battery_capacity");
                        sb.append("|");
                        sb.append(readLine3);
                    }
                    bufferedReader2.close();
                } else {
                    bufferedReader2 = bufferedReader3;
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e2) {
                        x.a(e2);
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = bufferedReader3;
                if (bufferedReader != null) {
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    x.a(e3);
                }
            }
            throw th;
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[SYNTHETIC, Splitter:B:17:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A[SYNTHETIC, Splitter:B:26:0x0049] */
    public static long s() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(" ")[0]);
                }
            } catch (Throwable th) {
                th = th;
                if (bufferedReader != null) {
                }
                throw th;
            }
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                x.a(e2);
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReader2;
            if (bufferedReader != null) {
            }
            throw th;
        }
        return (long) f;
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
        return f3 < 10.0f;
    }

    private static boolean u() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private static int v() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            return method.invoke((Object) null, new Object[0]).getClass().getName().startsWith("$Proxy") ? 256 : 0;
        } catch (Exception e2) {
            return 256;
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
                    i2 |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i2 |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i2 |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i = i + 1) == 2) {
                    i2 |= 32;
                }
            }
            return i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005c A[SYNTHETIC, Splitter:B:16:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d A[SYNTHETIC, Splitter:B:39:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8 A[SYNTHETIC, Splitter:B:46:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b9 A[SYNTHETIC, Splitter:B:57:0x00b9] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:43:0x00a3=Splitter:B:43:0x00a3, B:13:0x0057=Splitter:B:13:0x0057, B:36:0x0098=Splitter:B:36:0x0098} */
    private static int x() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        int i = 0;
        try {
            HashSet hashSet = new HashSet();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/maps"), "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                        hashSet.add(readLine.substring(readLine.lastIndexOf(" ") + 1));
                    }
                } catch (UnsupportedEncodingException e2) {
                    e = e2;
                    e = e;
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    e = e;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return i;
                } catch (IOException e6) {
                    e = e6;
                    e = e;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return i;
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                int i2 = ((String) next).toLowerCase().contains("xposed") ? i | 64 : i;
                try {
                    i = ((String) next).contains("com.saurik.substrate") ? i2 | ShareContent.MINAPP_STYLE : i2;
                } catch (UnsupportedEncodingException e7) {
                    e = e7;
                    i = i2;
                } catch (FileNotFoundException e8) {
                    e = e8;
                    i = i2;
                    e = e;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                    }
                    return i;
                } catch (IOException e9) {
                    e = e9;
                    i = i2;
                    e = e;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                    }
                    return i;
                }
            }
            bufferedReader.close();
        } catch (UnsupportedEncodingException e10) {
            e = e10;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
            }
            return i;
        } catch (FileNotFoundException e11) {
            e = e11;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
            }
            return i;
        } catch (IOException e12) {
            e = e12;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
        return i;
    }
}
