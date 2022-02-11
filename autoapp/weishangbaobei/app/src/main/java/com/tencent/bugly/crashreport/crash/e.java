package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.Thread;
import java.util.HashMap;

/* compiled from: BUGLY */
public final class e implements Thread.UncaughtExceptionHandler {
    private static String h;
    private static final Object i = new Object();
    private Context a;
    private b b;
    private a c;
    private com.tencent.bugly.crashreport.common.info.a d;
    private Thread.UncaughtExceptionHandler e;
    private Thread.UncaughtExceptionHandler f;
    private boolean g = false;
    private int j;

    public e(Context context, b bVar, a aVar, com.tencent.bugly.crashreport.common.info.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final synchronized void a() {
        if (this.j >= 10) {
            x.a("java crash handler over %d, no need set.", 10);
            return;
        }
        this.g = true;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (!getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    x.a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f = defaultUncaughtExceptionHandler;
                    this.e = defaultUncaughtExceptionHandler;
                } else {
                    x.a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.e = defaultUncaughtExceptionHandler;
                }
            } else {
                return;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.j++;
        x.a("registered java monitor: %s", toString());
    }

    public final synchronized void b() {
        this.g = false;
        x.a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            x.a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.e);
            this.j--;
        }
    }

    private CrashDetailBean b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String str2;
        if (th == null) {
            x.d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        boolean k = c.a().k();
        String str3 = (!k || !z) ? "" : " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        if (k && z) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.C = b.k();
        crashDetailBean.D = b.i();
        crashDetailBean.E = b.m();
        crashDetailBean.F = this.d.p();
        crashDetailBean.G = this.d.o();
        crashDetailBean.H = this.d.q();
        crashDetailBean.w = z.a(this.a, c.e, (String) null);
        crashDetailBean.y = y.a();
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.y == null ? 0 : crashDetailBean.y.length);
        x.a("user log size:%d", objArr);
        crashDetailBean.b = z ? 0 : 2;
        crashDetailBean.e = this.d.h();
        crashDetailBean.f = this.d.k;
        crashDetailBean.g = this.d.w();
        crashDetailBean.m = this.d.g();
        String name = th.getClass().getName();
        String b2 = b(th, SocializeConstants.CANCLE_RESULTCODE);
        if (b2 == null) {
            b2 = "";
        }
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        x.e("stack frame :%d, has cause %b", objArr2);
        String str4 = "";
        if (th.getStackTrace().length > 0) {
            str4 = th.getStackTrace()[0].toString();
        }
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.n = name;
            crashDetailBean.o = b2 + str3;
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = str4;
            str2 = a(th, c.f);
            crashDetailBean.q = str2;
        } else {
            crashDetailBean.n = th2.getClass().getName();
            crashDetailBean.o = b(th2, SocializeConstants.CANCLE_RESULTCODE);
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            if (th2.getStackTrace().length > 0) {
                crashDetailBean.p = th2.getStackTrace()[0].toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":");
            sb.append(b2);
            sb.append("\n");
            sb.append(str4);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.n);
            sb.append(":");
            sb.append(crashDetailBean.o);
            sb.append("\n");
            str2 = a(th2, c.f);
            sb.append(str2);
            crashDetailBean.q = sb.toString();
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.z = z.a(c.f, false);
            crashDetailBean.A = this.d.d;
            crashDetailBean.B = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.z.put(crashDetailBean.B, str2);
            crashDetailBean.I = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.J();
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            if (z) {
                this.b.d(crashDetailBean);
            } else {
                boolean z2 = str != null && str.length() > 0;
                boolean z3 = bArr != null && bArr.length > 0;
                if (z2) {
                    crashDetailBean.O = new HashMap(1);
                    crashDetailBean.O.put("UserData", str);
                }
                if (z3) {
                    crashDetailBean.U = bArr;
                }
            }
            crashDetailBean.Q = this.d.H();
            crashDetailBean.R = this.d.I();
            crashDetailBean.S = this.d.B();
            crashDetailBean.T = this.d.G();
        } catch (Throwable th3) {
            x.e("handle crash error %s", th3.toString());
        }
        return crashDetailBean;
    }

    private static boolean a(Thread thread) {
        synchronized (i) {
            if (h != null && thread.getName().equals(h)) {
                return true;
            }
            h = thread.getName();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01b9, code lost:
        if (r8.f != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01bd, code lost:
        com.tencent.bugly.proguard.x.e("crashreport last handle start!", new java.lang.Object[0]);
        com.tencent.bugly.proguard.x.e("current process die", new java.lang.Object[0]);
        android.os.Process.killProcess(android.os.Process.myPid());
        java.lang.System.exit(1);
        com.tencent.bugly.proguard.x.e("crashreport last handle end!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01dc, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01fb, code lost:
        if (r8.f != null) goto L_0x0084;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Thread r9, java.lang.Throwable r10, boolean r11, java.lang.String r12, byte[] r13) {
        /*
            r8 = this;
            r0 = 1
            r1 = 0
            if (r11 == 0) goto L_0x004c
            java.lang.String r2 = "Java Crash Happen cause by %s(%d)"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = r9.getName()
            r3[r1] = r4
            long r4 = r9.getId()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r3[r0] = r4
            com.tencent.bugly.proguard.x.e(r2, r3)
            boolean r2 = a((java.lang.Thread) r9)
            if (r2 == 0) goto L_0x0053
            java.lang.String r2 = "this class has handled this exception"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.a(r2, r3)
            java.lang.Thread$UncaughtExceptionHandler r2 = r8.f
            if (r2 == 0) goto L_0x003a
            java.lang.String r2 = "call system handler"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.a(r2, r3)
            java.lang.Thread$UncaughtExceptionHandler r2 = r8.f
            r2.uncaughtException(r9, r10)
            goto L_0x0053
        L_0x003a:
            java.lang.String r2 = "current process die"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r2, r3)
            int r2 = android.os.Process.myPid()
            android.os.Process.killProcess(r2)
            java.lang.System.exit(r0)
            goto L_0x0053
        L_0x004c:
            java.lang.String r2 = "Java Catch Happen"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r2, r3)
        L_0x0053:
            boolean r2 = r8.g     // Catch:{ Throwable -> 0x01df }
            if (r2 != 0) goto L_0x00b8
            java.lang.String r12 = "Java crash handler is disable. Just return."
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.proguard.x.c(r12, r13)     // Catch:{ Throwable -> 0x01df }
            if (r11 == 0) goto L_0x00b7
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x0080
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 == 0) goto L_0x0080
        L_0x006c:
            java.lang.String r11 = "sys default last handle start!"
            java.lang.Object[] r12 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r11, r12)
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            r11.uncaughtException(r9, r10)
            java.lang.String r9 = "sys default last handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x0080:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x0098
        L_0x0084:
            java.lang.String r11 = "system handle start!"
            java.lang.Object[] r12 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r11, r12)
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            r11.uncaughtException(r9, r10)
            java.lang.String r9 = "system handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x0098:
            java.lang.String r9 = "crashreport last handle start!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            java.lang.String r9 = "current process die"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            int r9 = android.os.Process.myPid()
            android.os.Process.killProcess(r9)
            java.lang.System.exit(r0)
            java.lang.String r9 = "crashreport last handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
        L_0x00b7:
            return
        L_0x00b8:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r8.c     // Catch:{ Throwable -> 0x01df }
            boolean r2 = r2.b()     // Catch:{ Throwable -> 0x01df }
            if (r2 != 0) goto L_0x00c7
            java.lang.String r2 = "no remote but still store!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.proguard.x.d(r2, r3)     // Catch:{ Throwable -> 0x01df }
        L_0x00c7:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r8.c     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r2 = r2.c()     // Catch:{ Throwable -> 0x01df }
            boolean r2 = r2.g     // Catch:{ Throwable -> 0x01df }
            if (r2 != 0) goto L_0x0132
            com.tencent.bugly.crashreport.common.strategy.a r2 = r8.c     // Catch:{ Throwable -> 0x01df }
            boolean r2 = r2.b()     // Catch:{ Throwable -> 0x01df }
            if (r2 == 0) goto L_0x0132
            java.lang.String r12 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.proguard.x.e(r12, r13)     // Catch:{ Throwable -> 0x01df }
            if (r11 == 0) goto L_0x00e5
            java.lang.String r12 = "JAVA_CRASH"
            goto L_0x00e7
        L_0x00e5:
            java.lang.String r12 = "JAVA_CATCH"
        L_0x00e7:
            r2 = r12
            java.lang.String r3 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.crashreport.common.info.a r12 = r8.d     // Catch:{ Throwable -> 0x01df }
            java.lang.String r4 = r12.d     // Catch:{ Throwable -> 0x01df }
            java.lang.String r5 = r9.getName()     // Catch:{ Throwable -> 0x01df }
            java.lang.String r6 = com.tencent.bugly.proguard.z.a((java.lang.Throwable) r10)     // Catch:{ Throwable -> 0x01df }
            r7 = 0
            com.tencent.bugly.crashreport.crash.b.a(r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x01df }
            if (r11 == 0) goto L_0x0131
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x010c
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 == 0) goto L_0x010c
            goto L_0x006c
        L_0x010c:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x0112
            goto L_0x0084
        L_0x0112:
            java.lang.String r9 = "crashreport last handle start!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            java.lang.String r9 = "current process die"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            int r9 = android.os.Process.myPid()
            android.os.Process.killProcess(r9)
            java.lang.System.exit(r0)
            java.lang.String r9 = "crashreport last handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
        L_0x0131:
            return
        L_0x0132:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r12 = r8.b(r9, r10, r11, r12, r13)     // Catch:{ Throwable -> 0x01df }
            if (r12 != 0) goto L_0x0175
            java.lang.String r12 = "pkg crash datas fail!"
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.proguard.x.e(r12, r13)     // Catch:{ Throwable -> 0x01df }
            if (r11 == 0) goto L_0x0174
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x014f
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 == 0) goto L_0x014f
            goto L_0x006c
        L_0x014f:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x0155
            goto L_0x0084
        L_0x0155:
            java.lang.String r9 = "crashreport last handle start!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            java.lang.String r9 = "current process die"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            int r9 = android.os.Process.myPid()
            android.os.Process.killProcess(r9)
            java.lang.System.exit(r0)
            java.lang.String r9 = "crashreport last handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
        L_0x0174:
            return
        L_0x0175:
            if (r11 == 0) goto L_0x017a
            java.lang.String r13 = "JAVA_CRASH"
            goto L_0x017c
        L_0x017a:
            java.lang.String r13 = "JAVA_CATCH"
        L_0x017c:
            r2 = r13
            java.lang.String r3 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.crashreport.common.info.a r13 = r8.d     // Catch:{ Throwable -> 0x01df }
            java.lang.String r4 = r13.d     // Catch:{ Throwable -> 0x01df }
            java.lang.String r5 = r9.getName()     // Catch:{ Throwable -> 0x01df }
            java.lang.String r6 = com.tencent.bugly.proguard.z.a((java.lang.Throwable) r10)     // Catch:{ Throwable -> 0x01df }
            r7 = r12
            com.tencent.bugly.crashreport.crash.b.a(r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x01df }
            com.tencent.bugly.crashreport.crash.b r13 = r8.b     // Catch:{ Throwable -> 0x01df }
            boolean r13 = r13.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r12)     // Catch:{ Throwable -> 0x01df }
            if (r13 != 0) goto L_0x01a0
            com.tencent.bugly.crashreport.crash.b r13 = r8.b     // Catch:{ Throwable -> 0x01df }
            r2 = 3000(0xbb8, double:1.482E-320)
            r13.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r12, (long) r2, (boolean) r11)     // Catch:{ Throwable -> 0x01df }
        L_0x01a0:
            if (r11 == 0) goto L_0x01a7
            com.tencent.bugly.crashreport.crash.b r13 = r8.b     // Catch:{ Throwable -> 0x01df }
            r13.c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r12)     // Catch:{ Throwable -> 0x01df }
        L_0x01a7:
            if (r11 == 0) goto L_0x01ff
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x01b7
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 == 0) goto L_0x01b7
            goto L_0x006c
        L_0x01b7:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x01bd
            goto L_0x0084
        L_0x01bd:
            java.lang.String r9 = "crashreport last handle start!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            java.lang.String r9 = "current process die"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            int r9 = android.os.Process.myPid()
            android.os.Process.killProcess(r9)
            java.lang.System.exit(r0)
            java.lang.String r9 = "crashreport last handle end!"
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x01dd:
            r12 = move-exception
            goto L_0x0200
        L_0x01df:
            r12 = move-exception
            boolean r13 = com.tencent.bugly.proguard.x.a(r12)     // Catch:{ all -> 0x01dd }
            if (r13 != 0) goto L_0x01e9
            r12.printStackTrace()     // Catch:{ all -> 0x01dd }
        L_0x01e9:
            if (r11 == 0) goto L_0x01ff
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x01f9
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 == 0) goto L_0x01f9
            goto L_0x006c
        L_0x01f9:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x01bd
            goto L_0x0084
        L_0x01ff:
            return
        L_0x0200:
            if (r11 == 0) goto L_0x025a
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            if (r11 == 0) goto L_0x0223
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            boolean r11 = a((java.lang.Thread.UncaughtExceptionHandler) r11)
            if (r11 != 0) goto L_0x020f
            goto L_0x0223
        L_0x020f:
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.String r13 = "sys default last handle start!"
            com.tencent.bugly.proguard.x.e(r13, r11)
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.e
            r11.uncaughtException(r9, r10)
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "sys default last handle end!"
            com.tencent.bugly.proguard.x.e(r10, r9)
            goto L_0x025a
        L_0x0223:
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            if (r11 == 0) goto L_0x023b
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.String r13 = "system handle start!"
            com.tencent.bugly.proguard.x.e(r13, r11)
            java.lang.Thread$UncaughtExceptionHandler r11 = r8.f
            r11.uncaughtException(r9, r10)
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "system handle end!"
            com.tencent.bugly.proguard.x.e(r10, r9)
            goto L_0x025a
        L_0x023b:
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "crashreport last handle start!"
            com.tencent.bugly.proguard.x.e(r10, r9)
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "current process die"
            com.tencent.bugly.proguard.x.e(r10, r9)
            int r9 = android.os.Process.myPid()
            android.os.Process.killProcess(r9)
            java.lang.System.exit(r0)
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "crashreport last handle end!"
            com.tencent.bugly.proguard.x.e(r10, r9)
        L_0x025a:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.e.a(java.lang.Thread, java.lang.Throwable, boolean, java.lang.String, byte[]):void");
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (i) {
            a(thread, th, true, (String) null, (byte[]) null);
        }
    }

    private static boolean a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && "uncaughtException".equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(com.tencent.bugly.crashreport.common.strategy.StrategyBean r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x002a
            boolean r0 = r5.g     // Catch:{ all -> 0x0027 }
            boolean r1 = r4.g     // Catch:{ all -> 0x0027 }
            if (r0 == r1) goto L_0x002a
            java.lang.String r0 = "java changed to %b"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0027 }
            r2 = 0
            boolean r3 = r5.g     // Catch:{ all -> 0x0027 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0027 }
            r1[r2] = r3     // Catch:{ all -> 0x0027 }
            com.tencent.bugly.proguard.x.a(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r5 = r5.g     // Catch:{ all -> 0x0027 }
            if (r5 == 0) goto L_0x0023
            r4.a()     // Catch:{ all -> 0x0027 }
            monitor-exit(r4)
            return
        L_0x0023:
            r4.b()     // Catch:{ all -> 0x0027 }
            goto L_0x002a
        L_0x0027:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x002a:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.e.a(com.tencent.bugly.crashreport.common.strategy.StrategyBean):void");
    }

    private static String a(Throwable th, int i2) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i3 = 0;
                while (i3 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i3];
                    if (i2 <= 0 || sb.length() < i2) {
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                        i3++;
                    } else {
                        sb.append("\n[Stack over limit size :" + i2 + " , has been cutted !]");
                        return sb.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            x.e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    private static String b(Throwable th, int i2) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, SocializeConstants.CANCLE_RESULTCODE) + "\n[Message over limit size:1000" + ", has been cutted!]";
    }
}
