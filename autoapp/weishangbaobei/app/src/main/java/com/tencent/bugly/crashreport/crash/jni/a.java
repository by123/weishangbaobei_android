package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.Map;

/* compiled from: BUGLY */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int length;
        String str12;
        int indexOf;
        String str13 = str;
        String str14 = str8;
        byte[] bArr2 = bArr;
        boolean k = c.a().k();
        if (k) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.k;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = k ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        crashDetailBean.q = str5 == null ? "" : str5;
        crashDetailBean.r = j;
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        crashDetailBean.A = str13;
        crashDetailBean.B = str2;
        crashDetailBean.I = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.J();
        crashDetailBean.v = str14;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        String dumpFilePath = instance != null ? instance.getDumpFilePath() : null;
        String a2 = b.a(dumpFilePath, str14);
        if (!z.a(a2)) {
            crashDetailBean.V = a2;
        }
        crashDetailBean.W = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.e, (String) null, false);
        crashDetailBean.x = b.a(str10, c.e, (String) null, true);
        crashDetailBean.J = str7;
        crashDetailBean.K = str6;
        crashDetailBean.L = str11;
        crashDetailBean.F = this.c.p();
        crashDetailBean.G = this.c.o();
        crashDetailBean.H = this.c.q();
        if (z) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.k();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.m();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.a, c.e, (String) null);
            }
            crashDetailBean.y = y.a();
            crashDetailBean.M = this.c.a;
            crashDetailBean.N = this.c.a();
            crashDetailBean.z = z.a(c.f, false);
            int indexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (indexOf2 > 0 && (length = indexOf2 + "java:\n".length()) < crashDetailBean.q.length()) {
                String substring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B) && (indexOf = str12.indexOf(substring)) > 0) {
                    String substring2 = (str12 = crashDetailBean.z.get(crashDetailBean.B)).substring(indexOf);
                    crashDetailBean.z.put(crashDetailBean.B, substring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, length);
                    crashDetailBean.q += substring2;
                }
            }
            if (str13 == null) {
                crashDetailBean.A = this.c.d;
            }
            this.b.d(crashDetailBean);
            crashDetailBean.Q = this.c.H();
            crashDetailBean.R = this.c.I();
            crashDetailBean.S = this.c.B();
            crashDetailBean.T = this.c.G();
        } else {
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.E = -1;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.M = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = -1;
            crashDetailBean.S = map;
            crashDetailBean.T = this.c.G();
            crashDetailBean.z = null;
            if (str13 == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (bArr2 != null) {
                crashDetailBean.y = bArr2;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, (String[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fc A[Catch:{ Throwable -> 0x029f }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01bd A[Catch:{ Throwable -> 0x029f }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01d6 A[Catch:{ Throwable -> 0x029f }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleNativeException2(int r26, int r27, long r28, long r30, java.lang.String r32, java.lang.String r33, java.lang.String r34, java.lang.String r35, int r36, java.lang.String r37, int r38, int r39, int r40, java.lang.String r41, java.lang.String r42, java.lang.String[] r43) {
        /*
            r25 = this;
            r14 = r25
            r0 = r33
            r1 = r38
            r2 = r43
            java.lang.String r3 = "Native Crash Happen v2"
            r13 = 0
            java.lang.Object[] r4 = new java.lang.Object[r13]
            com.tencent.bugly.proguard.x.a(r3, r4)
            java.lang.String r12 = com.tencent.bugly.crashreport.crash.jni.b.a((java.lang.String) r34)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r3 = "UNKNOWN"
            if (r36 <= 0) goto L_0x003b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r1.<init>()     // Catch:{ Throwable -> 0x029f }
            r4 = r32
            r1.append(r4)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = "("
            r1.append(r4)     // Catch:{ Throwable -> 0x029f }
            r5 = r37
            r1.append(r5)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = ")"
            r1.append(r4)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = "KERNEL"
            r11 = r1
            r10 = r3
            r9 = r4
            goto L_0x006f
        L_0x003b:
            r4 = r32
            r5 = r37
            if (r1 <= 0) goto L_0x0047
            android.content.Context r3 = r14.a     // Catch:{ Throwable -> 0x029f }
            java.lang.String r3 = com.tencent.bugly.crashreport.common.info.AppInfo.a((int) r38)     // Catch:{ Throwable -> 0x029f }
        L_0x0047:
            java.lang.String r6 = java.lang.String.valueOf(r38)     // Catch:{ Throwable -> 0x029f }
            boolean r6 = r3.equals(r6)     // Catch:{ Throwable -> 0x029f }
            if (r6 != 0) goto L_0x006c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r6.<init>()     // Catch:{ Throwable -> 0x029f }
            r6.append(r3)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r3 = "("
            r6.append(r3)     // Catch:{ Throwable -> 0x029f }
            r6.append(r1)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r1 = ")"
            r6.append(r1)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r1 = r6.toString()     // Catch:{ Throwable -> 0x029f }
            r10 = r1
            goto L_0x006d
        L_0x006c:
            r10 = r3
        L_0x006d:
            r11 = r4
            r9 = r5
        L_0x006f:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x029f }
            r1.<init>()     // Catch:{ Throwable -> 0x029f }
            r8 = 1
            if (r2 == 0) goto L_0x00ac
            r3 = 0
        L_0x0078:
            int r4 = r2.length     // Catch:{ Throwable -> 0x029f }
            if (r3 >= r4) goto L_0x00b3
            r4 = r2[r3]     // Catch:{ Throwable -> 0x029f }
            if (r4 == 0) goto L_0x00a9
            java.lang.String r5 = "Extra message[%d]: %s"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x029f }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x029f }
            r7[r13] = r15     // Catch:{ Throwable -> 0x029f }
            r7[r8] = r4     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.a(r5, r7)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r5 = "="
            java.lang.String[] r5 = r4.split(r5)     // Catch:{ Throwable -> 0x029f }
            int r7 = r5.length     // Catch:{ Throwable -> 0x029f }
            if (r7 != r6) goto L_0x00a0
            r4 = r5[r13]     // Catch:{ Throwable -> 0x029f }
            r5 = r5[r8]     // Catch:{ Throwable -> 0x029f }
            r1.put(r4, r5)     // Catch:{ Throwable -> 0x029f }
            goto L_0x00a9
        L_0x00a0:
            java.lang.String r5 = "bad extraMsg %s"
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x029f }
            r6[r13] = r4     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ Throwable -> 0x029f }
        L_0x00a9:
            int r3 = r3 + 1
            goto L_0x0078
        L_0x00ac:
            java.lang.String r2 = "not found extraMsg"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch:{ Throwable -> 0x029f }
        L_0x00b3:
            java.lang.String r2 = "HasPendingException"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x029f }
            if (r2 == 0) goto L_0x00cf
            java.lang.String r3 = "true"
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x029f }
            if (r2 == 0) goto L_0x00cf
            java.lang.String r2 = "Native crash happened with a Java pending exception."
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.a(r2, r3)     // Catch:{ Throwable -> 0x029f }
            r18 = 1
            goto L_0x00d1
        L_0x00cf:
            r18 = 0
        L_0x00d1:
            java.lang.String r2 = "ExceptionProcessName"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x029f }
            if (r2 == 0) goto L_0x00ec
            int r3 = r2.length()     // Catch:{ Throwable -> 0x029f }
            if (r3 != 0) goto L_0x00e2
            goto L_0x00ec
        L_0x00e2:
            java.lang.String r3 = "Name of crash process: %s"
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x029f }
            r4[r13] = r2     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.c(r3, r4)     // Catch:{ Throwable -> 0x029f }
            goto L_0x00f0
        L_0x00ec:
            com.tencent.bugly.crashreport.common.info.a r2 = r14.c     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = r2.d     // Catch:{ Throwable -> 0x029f }
        L_0x00f0:
            r19 = r2
            java.lang.String r2 = "ExceptionThreadName"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x029f }
            if (r2 == 0) goto L_0x0172
            int r3 = r2.length()     // Catch:{ Throwable -> 0x029f }
            if (r3 != 0) goto L_0x0104
            goto L_0x0172
        L_0x0104:
            java.lang.String r3 = "Name of crash thread: %s"
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x029f }
            r4[r13] = r2     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.c(r3, r4)     // Catch:{ Throwable -> 0x029f }
            java.util.Map r3 = java.lang.Thread.getAllStackTraces()     // Catch:{ Throwable -> 0x029f }
            java.util.Set r3 = r3.keySet()     // Catch:{ Throwable -> 0x029f }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x029f }
        L_0x0119:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x029f }
            if (r4 == 0) goto L_0x014f
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x029f }
            java.lang.Thread r4 = (java.lang.Thread) r4     // Catch:{ Throwable -> 0x029f }
            java.lang.String r5 = r4.getName()     // Catch:{ Throwable -> 0x029f }
            boolean r5 = r5.equals(r2)     // Catch:{ Throwable -> 0x029f }
            if (r5 == 0) goto L_0x0119
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r3.<init>()     // Catch:{ Throwable -> 0x029f }
            r3.append(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = "("
            r3.append(r2)     // Catch:{ Throwable -> 0x029f }
            long r4 = r4.getId()     // Catch:{ Throwable -> 0x029f }
            r3.append(r4)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = ")"
            r3.append(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = r3.toString()     // Catch:{ Throwable -> 0x029f }
            r3 = r2
            r2 = 1
            goto L_0x0151
        L_0x014f:
            r3 = r2
            r2 = 0
        L_0x0151:
            if (r2 != 0) goto L_0x016f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r2.<init>()     // Catch:{ Throwable -> 0x029f }
            r2.append(r3)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r3 = "("
            r2.append(r3)     // Catch:{ Throwable -> 0x029f }
            r3 = r27
            r2.append(r3)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r3 = ")"
            r2.append(r3)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x029f }
            goto L_0x0197
        L_0x016f:
            r20 = r3
            goto L_0x0199
        L_0x0172:
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x029f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r3.<init>()     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = r2.getName()     // Catch:{ Throwable -> 0x029f }
            r3.append(r4)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = "("
            r3.append(r4)     // Catch:{ Throwable -> 0x029f }
            long r4 = r2.getId()     // Catch:{ Throwable -> 0x029f }
            r3.append(r4)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = ")"
            r3.append(r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = r3.toString()     // Catch:{ Throwable -> 0x029f }
        L_0x0197:
            r20 = r2
        L_0x0199:
            r2 = 1000(0x3e8, double:4.94E-321)
            long r4 = r28 * r2
            long r2 = r30 / r2
            r6 = 0
            long r4 = r4 + r2
            java.lang.String r2 = "SysLogPath"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x029f }
            r21 = r2
            java.lang.String r21 = (java.lang.String) r21     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = "JniLogPath"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ Throwable -> 0x029f }
            r22 = r1
            java.lang.String r22 = (java.lang.String) r22     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ Throwable -> 0x029f }
            boolean r1 = r1.b()     // Catch:{ Throwable -> 0x029f }
            if (r1 != 0) goto L_0x01c4
            java.lang.String r1 = "no remote but still store!"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.d(r1, r2)     // Catch:{ Throwable -> 0x029f }
        L_0x01c4:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.c()     // Catch:{ Throwable -> 0x029f }
            boolean r1 = r1.g     // Catch:{ Throwable -> 0x029f }
            if (r1 != 0) goto L_0x0213
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ Throwable -> 0x029f }
            boolean r1 = r1.b()     // Catch:{ Throwable -> 0x029f }
            if (r1 == 0) goto L_0x0213
            java.lang.String r1 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.x.e(r1, r2)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r1 = "NATIVE_CRASH"
            java.lang.String r2 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x029f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f }
            r3.<init>()     // Catch:{ Throwable -> 0x029f }
            r3.append(r11)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r4 = "\n"
            r3.append(r4)     // Catch:{ Throwable -> 0x029f }
            r3.append(r0)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r0 = "\n"
            r3.append(r0)     // Catch:{ Throwable -> 0x029f }
            r3.append(r12)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x029f }
            r3 = 0
            r26 = r1
            r27 = r2
            r28 = r19
            r29 = r20
            r30 = r0
            r31 = r3
            com.tencent.bugly.crashreport.crash.b.a(r26, r27, r28, r29, r30, r31)     // Catch:{ Throwable -> 0x029f }
            com.tencent.bugly.proguard.z.b((java.lang.String) r35)     // Catch:{ Throwable -> 0x029f }
            return
        L_0x0213:
            r15 = 0
            r16 = 0
            r17 = 1
            r1 = r25
            r2 = r19
            r3 = r20
            r6 = r11
            r7 = r33
            r8 = r12
            r23 = r11
            r11 = r35
            r24 = r12
            r12 = r21
            r13 = r22
            r14 = r42
            com.tencent.bugly.crashreport.crash.CrashDetailBean r1 = r1.packageCrashDatas(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x029b }
            if (r1 != 0) goto L_0x023d
            java.lang.String r0 = "pkg crash datas fail!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x029b }
            com.tencent.bugly.proguard.x.e(r0, r1)     // Catch:{ Throwable -> 0x029b }
            return
        L_0x023d:
            java.lang.String r2 = "NATIVE_CRASH"
            java.lang.String r3 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x029b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029b }
            r4.<init>()     // Catch:{ Throwable -> 0x029b }
            r5 = r23
            r4.append(r5)     // Catch:{ Throwable -> 0x029b }
            java.lang.String r5 = "\n"
            r4.append(r5)     // Catch:{ Throwable -> 0x029b }
            r4.append(r0)     // Catch:{ Throwable -> 0x029b }
            java.lang.String r0 = "\n"
            r4.append(r0)     // Catch:{ Throwable -> 0x029b }
            r0 = r24
            r4.append(r0)     // Catch:{ Throwable -> 0x029b }
            java.lang.String r0 = r4.toString()     // Catch:{ Throwable -> 0x029b }
            r26 = r2
            r27 = r3
            r28 = r19
            r29 = r20
            r30 = r0
            r31 = r1
            com.tencent.bugly.crashreport.crash.b.a(r26, r27, r28, r29, r30, r31)     // Catch:{ Throwable -> 0x029b }
            r2 = r25
            com.tencent.bugly.crashreport.crash.b r0 = r2.b     // Catch:{ Throwable -> 0x0299 }
            boolean r0 = r0.b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ Throwable -> 0x0299 }
            r3 = 1
            r0 = r0 ^ r3
            r4 = 0
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r5 = com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.getInstance()     // Catch:{ Throwable -> 0x0299 }
            if (r5 == 0) goto L_0x0287
            java.lang.String r4 = r5.getDumpFilePath()     // Catch:{ Throwable -> 0x0299 }
        L_0x0287:
            com.tencent.bugly.crashreport.crash.jni.b.a((boolean) r3, (java.lang.String) r4)     // Catch:{ Throwable -> 0x0299 }
            if (r0 == 0) goto L_0x0293
            com.tencent.bugly.crashreport.crash.b r0 = r2.b     // Catch:{ Throwable -> 0x0299 }
            r4 = 3000(0xbb8, double:1.482E-320)
            r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (long) r4, (boolean) r3)     // Catch:{ Throwable -> 0x0299 }
        L_0x0293:
            com.tencent.bugly.crashreport.crash.b r0 = r2.b     // Catch:{ Throwable -> 0x0299 }
            r0.c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ Throwable -> 0x0299 }
            return
        L_0x0299:
            r0 = move-exception
            goto L_0x02a1
        L_0x029b:
            r0 = move-exception
            r2 = r25
            goto L_0x02a1
        L_0x029f:
            r0 = move-exception
            r2 = r14
        L_0x02a1:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x02aa
            r0.printStackTrace()
        L_0x02aa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.a.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }
}
