package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.Thread;
import java.util.HashMap;

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

    private static String a(Throwable th, int i2) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (i2 > 0) {
                        if (sb.length() >= i2) {
                            sb.append("\n[Stack over limit size :" + i2 + " , has been cutted !]");
                            return sb.toString();
                        }
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
        } catch (Throwable th2) {
            x.e("gen stack error %s", th2.toString());
        }
        return sb.toString();
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

    private static boolean a(Thread thread) {
        synchronized (i) {
            if (h != null && thread.getName().equals(h)) {
                return true;
            }
            h = thread.getName();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x024b A[Catch:{ Throwable -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x024e A[Catch:{ Throwable -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x025f A[Catch:{ Throwable -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0278  */
    private CrashDetailBean b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String a2;
        boolean z2;
        boolean z3;
        if (th == null) {
            x.d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        boolean k = c.a().k();
        String str2 = (!k || !z) ? "" : " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
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
        x.a("user log size:%d", Integer.valueOf(crashDetailBean.y == null ? 0 : crashDetailBean.y.length));
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
        x.e("stack frame :%d, has cause %b", Integer.valueOf(th.getStackTrace().length), Boolean.valueOf(th.getCause() != null));
        String str3 = "";
        if (th.getStackTrace().length > 0) {
            str3 = th.getStackTrace()[0].toString();
        }
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.n = name;
            crashDetailBean.o = b2 + str2;
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = str3;
            a2 = a(th, c.f);
            crashDetailBean.q = a2;
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
            sb.append(str3);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.n);
            sb.append(":");
            sb.append(crashDetailBean.o);
            sb.append("\n");
            a2 = a(th2, c.f);
            sb.append(a2);
            crashDetailBean.q = sb.toString();
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.z = z.a(c.f, false);
            crashDetailBean.A = this.d.d;
            crashDetailBean.B = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.z.put(crashDetailBean.B, a2);
            crashDetailBean.I = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.J();
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            if (z) {
                this.b.d(crashDetailBean);
            } else {
                if (str != null) {
                    if (str.length() > 0) {
                        z2 = true;
                        z3 = bArr != null && bArr.length > 0;
                        if (z2) {
                            crashDetailBean.O = new HashMap(1);
                            crashDetailBean.O.put("UserData", str);
                        }
                        if (z3) {
                            crashDetailBean.U = bArr;
                        }
                    }
                }
                z2 = false;
                if (bArr != null || bArr.length > 0) {
                }
                if (z2) {
                }
                if (z3) {
                }
            }
            crashDetailBean.Q = this.d.H();
            crashDetailBean.R = this.d.I();
            crashDetailBean.S = this.d.B();
            crashDetailBean.T = this.d.G();
            return crashDetailBean;
        } catch (Throwable th3) {
            x.e("handle crash error %s", th3.toString());
            return crashDetailBean;
        }
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

    public final void a() {
        synchronized (this) {
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
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    public final void a(StrategyBean strategyBean) {
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != this.g) {
                    x.a("java changed to %b", Boolean.valueOf(strategyBean.g));
                    if (strategyBean.g) {
                        a();
                        return;
                    }
                    b();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ac, code lost:
        if (r8.f == null) goto L_0x01ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01ae, code lost:
        com.tencent.bugly.proguard.x.e("crashreport last handle start!", new java.lang.Object[0]);
        com.tencent.bugly.proguard.x.e("current process die", new java.lang.Object[0]);
        android.os.Process.killProcess(android.os.Process.myPid());
        java.lang.System.exit(1);
        com.tencent.bugly.proguard.x.e("crashreport last handle end!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01e9, code lost:
        if (r8.f != null) goto L_0x0085;
     */
    public final void a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (z) {
            x.e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (a(thread)) {
                x.a("this class has handled this exception", new Object[0]);
                if (this.f != null) {
                    x.a("call system handler", new Object[0]);
                    this.f.uncaughtException(thread, th);
                } else {
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            x.e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.g) {
                x.c("Java crash handler is disable. Just return.", new Object[0]);
                if (z) {
                    if (this.e == null || !a(this.e)) {
                        if (this.f == null) {
                            x.e("crashreport last handle start!", new Object[0]);
                            x.e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            x.e("crashreport last handle end!", new Object[0]);
                            return;
                        }
                        x.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, th);
                        x.e("system handle end!", new Object[0]);
                        return;
                    }
                    x.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    x.e("sys default last handle end!", new Object[0]);
                }
                return;
            }
            if (!this.c.b()) {
                x.d("no remote but still store!", new Object[0]);
            }
            if (this.c.c().g || !this.c.b()) {
                CrashDetailBean b2 = b(thread, th, z, str, bArr);
                if (b2 == null) {
                    x.e("pkg crash datas fail!", new Object[0]);
                    if (z) {
                        if (this.e == null || !a(this.e)) {
                            if (this.f == null) {
                                x.e("crashreport last handle start!", new Object[0]);
                                x.e("current process die", new Object[0]);
                                Process.killProcess(Process.myPid());
                                System.exit(1);
                                x.e("crashreport last handle end!", new Object[0]);
                                return;
                            }
                            x.e("system handle start!", new Object[0]);
                            this.f.uncaughtException(thread, th);
                            x.e("system handle end!", new Object[0]);
                            return;
                        }
                        x.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, th);
                        x.e("sys default last handle end!", new Object[0]);
                    }
                    return;
                }
                b.a(z ? "JAVA_CRASH" : "JAVA_CATCH", z.a(), this.d.d, thread.getName(), z.a(th), b2);
                if (!this.b.a(b2)) {
                    this.b.a(b2, 3000, z);
                }
                if (z) {
                    this.b.c(b2);
                }
                if (z) {
                    if (this.e == null || !a(this.e)) {
                    }
                    x.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    x.e("sys default last handle end!", new Object[0]);
                }
                return;
            }
            x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            b.a(z ? "JAVA_CRASH" : "JAVA_CATCH", z.a(), this.d.d, thread.getName(), z.a(th), (CrashDetailBean) null);
            if (z) {
                if (this.e == null || !a(this.e)) {
                    if (this.f == null) {
                        x.e("crashreport last handle start!", new Object[0]);
                        x.e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        x.e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                    x.e("system handle start!", new Object[0]);
                    this.f.uncaughtException(thread, th);
                    x.e("system handle end!", new Object[0]);
                    return;
                }
                x.e("sys default last handle start!", new Object[0]);
                this.e.uncaughtException(thread, th);
                x.e("sys default last handle end!", new Object[0]);
            }
        } catch (Throwable th2) {
            if (z) {
                if (this.e != null && a(this.e)) {
                    x.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    x.e("sys default last handle end!", new Object[0]);
                } else if (this.f != null) {
                    x.e("system handle start!", new Object[0]);
                    this.f.uncaughtException(thread, th);
                    x.e("system handle end!", new Object[0]);
                } else {
                    x.e("crashreport last handle start!", new Object[0]);
                    x.e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    x.e("crashreport last handle end!", new Object[0]);
                }
            }
            throw th2;
        }
    }

    public final void b() {
        synchronized (this) {
            this.g = false;
            x.a("close java monitor!", new Object[0]);
            if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
                x.a("Java monitor to unregister: %s", toString());
                Thread.setDefaultUncaughtExceptionHandler(this.e);
                this.j--;
            }
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (i) {
            a(thread, th, true, (String) null, (byte[]) null);
        }
    }
}
