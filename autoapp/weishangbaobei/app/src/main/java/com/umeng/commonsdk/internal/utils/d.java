package com.umeng.commonsdk.internal.utils;

import java.io.InputStream;

/* compiled from: CpuUtil */
public class d {

    /* compiled from: CpuUtil */
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.io.FileReader} */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|(12:1|2|3|4|5|6|7|8|9|(3:10|11|(2:13|(2:126|15)(14:16|(1:21)|(1:27)|(1:33)|(1:39)|(1:45)|(1:51)|(1:57)|(1:63)|(1:69)|(1:75)|(1:81)|(2:87|127)|88))(0))|89|90)|91|92|123|125|(1:(0))) */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0126, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0127, code lost:
        r3 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0137, code lost:
        r8 = 0;
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0142, code lost:
        if (r4 == null) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0119, code lost:
        r0 = th;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x011b, code lost:
        r8 = 0;
        r3 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0113 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0126 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x012c A[SYNTHETIC, Splitter:B:107:0x012c] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0133 A[SYNTHETIC, Splitter:B:111:0x0133] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x013d A[SYNTHETIC, Splitter:B:119:0x013d] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0119 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0013] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.umeng.commonsdk.internal.utils.d.a a() {
        /*
            r0 = 0
            r1 = 0
            com.umeng.commonsdk.internal.utils.d$a r2 = new com.umeng.commonsdk.internal.utils.d$a     // Catch:{ Exception -> 0x0137, all -> 0x0126 }
            r2.<init>()     // Catch:{ Exception -> 0x0137, all -> 0x0126 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x0123, all -> 0x0126 }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ Exception -> 0x0123, all -> 0x0126 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0120, all -> 0x011d }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0120, all -> 0x011d }
            java.lang.String r0 = r4.readLine()     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r5 = 1
            r6 = 0
            r7 = 1
            r8 = 0
        L_0x001b:
            boolean r9 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 != 0) goto L_0x0110
            int r6 = r6 + r5
            r9 = 30
            if (r6 < r9) goto L_0x0028
            goto L_0x0110
        L_0x0028:
            java.lang.String r9 = ":\\s+"
            r10 = 2
            java.lang.String[] r0 = r0.split(r9, r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r7 == 0) goto L_0x003b
            if (r0 == 0) goto L_0x003b
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x003b
            r7 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.a = r7     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r7 = 0
        L_0x003b:
            if (r0 == 0) goto L_0x004c
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x004c
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "processor"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x004c
            int r8 = r8 + 1
        L_0x004c:
            if (r0 == 0) goto L_0x005f
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x005f
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "Features"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x005f
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.d = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x005f:
            if (r0 == 0) goto L_0x0072
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x0072
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "implementer"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x0072
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.e = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x0072:
            if (r0 == 0) goto L_0x0085
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x0085
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "architecture"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x0085
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.f = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x0085:
            if (r0 == 0) goto L_0x0098
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x0098
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "variant"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x0098
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.g = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x0098:
            if (r0 == 0) goto L_0x00ab
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x00ab
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "part"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x00ab
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.h = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x00ab:
            if (r0 == 0) goto L_0x00be
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x00be
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "revision"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x00be
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.i = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x00be:
            if (r0 == 0) goto L_0x00d1
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x00d1
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "Hardware"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x00d1
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.j = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x00d1:
            if (r0 == 0) goto L_0x00e4
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x00e4
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "Revision"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x00e4
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.k = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x00e4:
            if (r0 == 0) goto L_0x00f7
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x00f7
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "Serial"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x00f7
            r9 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.l = r9     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x00f7:
            if (r0 == 0) goto L_0x010a
            int r9 = r0.length     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 <= r5) goto L_0x010a
            r9 = r0[r1]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            java.lang.String r10 = "implementer"
            boolean r9 = r9.contains(r10)     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            if (r9 == 0) goto L_0x010a
            r0 = r0[r5]     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            r2.e = r0     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
        L_0x010a:
            java.lang.String r0 = r4.readLine()     // Catch:{ Exception -> 0x0117, all -> 0x0119 }
            goto L_0x001b
        L_0x0110:
            r3.close()     // Catch:{ IOException -> 0x0113 }
        L_0x0113:
            r4.close()     // Catch:{ IOException -> 0x0145 }
            goto L_0x0145
        L_0x0117:
            goto L_0x013b
        L_0x0119:
            r0 = move-exception
            goto L_0x012a
        L_0x011b:
            r8 = 0
            goto L_0x013b
        L_0x011d:
            r1 = move-exception
            r4 = r0
            goto L_0x0129
        L_0x0120:
            r8 = 0
            r4 = r0
            goto L_0x013b
        L_0x0123:
            r8 = 0
            r3 = r0
            goto L_0x013a
        L_0x0126:
            r1 = move-exception
            r3 = r0
            r4 = r3
        L_0x0129:
            r0 = r1
        L_0x012a:
            if (r3 == 0) goto L_0x0131
            r3.close()     // Catch:{ IOException -> 0x0130 }
            goto L_0x0131
        L_0x0130:
        L_0x0131:
            if (r4 == 0) goto L_0x0136
            r4.close()     // Catch:{ IOException -> 0x0136 }
        L_0x0136:
            throw r0
        L_0x0137:
            r8 = 0
            r2 = r0
            r3 = r2
        L_0x013a:
            r4 = r3
        L_0x013b:
            if (r3 == 0) goto L_0x0142
            r3.close()     // Catch:{ IOException -> 0x0141 }
            goto L_0x0142
        L_0x0141:
        L_0x0142:
            if (r4 == 0) goto L_0x0145
            goto L_0x0113
        L_0x0145:
            r2.c = r8
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.d.a():com.umeng.commonsdk.internal.utils.d$a");
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
        } catch (Exception unused) {
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
        } catch (Exception unused) {
        }
        return str.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0024 A[SYNTHETIC, Splitter:B:15:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x0028, all -> 0x0021 }
            java.lang.String r3 = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0028, all -> 0x0021 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0028, all -> 0x0021 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0028, all -> 0x0021 }
            java.lang.String r1 = r3.readLine()     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r3.close()     // Catch:{ Throwable -> 0x001a }
        L_0x001a:
            r0 = r1
            goto L_0x002d
        L_0x001c:
            r0 = move-exception
            r1 = r3
            goto L_0x0022
        L_0x001f:
            r1 = r3
            goto L_0x0028
        L_0x0021:
            r0 = move-exception
        L_0x0022:
            if (r1 == 0) goto L_0x0027
            r1.close()     // Catch:{ Throwable -> 0x0027 }
        L_0x0027:
            throw r0
        L_0x0028:
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ Throwable -> 0x002d }
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.d.d():java.lang.String");
    }
}
