package com.umeng.commonsdk.internal.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class d {

    public static class a {
        public String a;
        public String b;
        public int c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x013b, code lost:
        r8 = r3;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x013f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0140, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x015a, code lost:
        r0 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x015e, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x015f, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0172, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0131, code lost:
        r3 = r8;
        r0 = r7;
        r2 = r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0136 A[SYNTHETIC, Splitter:B:100:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x013f A[ExcHandler: all (r0v18 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:7:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0143 A[SYNTHETIC, Splitter:B:107:0x0143] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0148 A[SYNTHETIC, Splitter:B:110:0x0148] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x015e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0003] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0172  */
    public static a a() {
        a aVar;
        FileReader fileReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        int i;
        int i2 = 0;
        try {
            a aVar2 = new a();
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader);
                try {
                    int i3 = 0;
                    boolean z = true;
                    int i4 = 0;
                    for (String readLine = bufferedReader2.readLine(); !TextUtils.isEmpty(readLine) && (i3 = i3 + 1) < 30; readLine = bufferedReader2.readLine()) {
                        String[] split = readLine.split(":\\s+", 2);
                        if (z && split != null && split.length > 1) {
                            aVar2.a = split[1];
                            z = false;
                        }
                        if (split != null && split.length > 1 && split[0].contains("processor")) {
                            i4++;
                        }
                        if (split != null && split.length > 1 && split[0].contains("Features")) {
                            aVar2.d = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("implementer")) {
                            aVar2.e = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("architecture")) {
                            aVar2.f = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("variant")) {
                            aVar2.g = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("part")) {
                            aVar2.h = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("revision")) {
                            aVar2.i = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("Hardware")) {
                            aVar2.j = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("Revision")) {
                            aVar2.k = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("Serial")) {
                            aVar2.l = split[1];
                        }
                        if (split != null && split.length > 1 && split[0].contains("implementer")) {
                            aVar2.e = split[1];
                        }
                    }
                    try {
                        fileReader.close();
                        aVar = aVar2;
                        i = i4;
                    } catch (IOException e) {
                        aVar = aVar2;
                        i = i4;
                    }
                } catch (Exception e2) {
                    bufferedReader = bufferedReader2;
                    aVar = aVar2;
                } catch (Throwable th) {
                }
            } catch (Exception e3) {
                bufferedReader = null;
                aVar = aVar2;
                if (fileReader != null) {
                }
                if (bufferedReader != null) {
                }
            } catch (Throwable th2) {
                th = th2;
                FileReader fileReader2 = fileReader;
                bufferedReader2 = null;
                fileReader = fileReader2;
                if (fileReader != null) {
                }
                if (bufferedReader2 != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            aVar = null;
            fileReader = null;
            bufferedReader = null;
            if (fileReader != null) {
            }
            if (bufferedReader != null) {
            }
        } catch (Throwable th3) {
        }
        try {
            bufferedReader2.close();
        } catch (IOException e5) {
        }
        aVar.c = i;
        return aVar;
    }

    public static String b() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    public static String c() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0027 A[SYNTHETIC, Splitter:B:16:0x0027] */
    public static String d() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
            try {
                String trim = bufferedReader2.readLine().trim();
                try {
                    bufferedReader2.close();
                    return trim;
                } catch (Throwable th) {
                    return trim;
                }
            } catch (Exception e) {
                bufferedReader = bufferedReader2;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader2 != null) {
                }
                throw th;
            }
        } catch (Exception e2) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable th3) {
                    return "";
                }
            }
            return "";
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Throwable th5) {
                }
            }
            throw th;
        }
    }
}
