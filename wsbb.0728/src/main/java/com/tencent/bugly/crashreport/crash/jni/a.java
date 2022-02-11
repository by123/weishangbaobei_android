package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, (String[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0144 A[Catch:{ Throwable -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0162 A[Catch:{ Throwable -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0210  */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        String str10;
        boolean z;
        String str11;
        String str12;
        String str13;
        boolean z2;
        x.a("Native Crash Happen v2", new Object[0]);
        try {
            String a2 = b.a(str3);
            String str14 = "UNKNOWN";
            if (i3 > 0) {
                str10 = str + "(" + str5 + ")";
                str8 = "UNKNOWN";
                str9 = "KERNEL";
            } else {
                if (i4 > 0) {
                    Context context = this.a;
                    str14 = AppInfo.a(i4);
                }
                if (!str14.equals(String.valueOf(i4))) {
                    str14 = str14 + "(" + i4 + ")";
                }
                str8 = str14;
                str9 = str5;
                str10 = str;
            }
            HashMap hashMap = new HashMap();
            if (strArr != null) {
                for (int i7 = 0; i7 < strArr.length; i7++) {
                    String str15 = strArr[i7];
                    if (str15 != null) {
                        x.a("Extra message[%d]: %s", Integer.valueOf(i7), str15);
                        String[] split = str15.split("=");
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            x.d("bad extraMsg %s", str15);
                        }
                    }
                }
            } else {
                x.c("not found extraMsg", new Object[0]);
            }
            String str16 = (String) hashMap.get("HasPendingException");
            if (str16 == null || !str16.equals("true")) {
                z = false;
            } else {
                x.a("Native crash happened with a Java pending exception.", new Object[0]);
                z = true;
            }
            String str17 = (String) hashMap.get("ExceptionProcessName");
            if (str17 == null || str17.length() == 0) {
                str11 = this.c.d;
            } else {
                x.c("Name of crash process: %s", str17);
                str11 = str17;
            }
            String str18 = (String) hashMap.get("ExceptionThreadName");
            if (str18 == null || str18.length() == 0) {
                Thread currentThread = Thread.currentThread();
                str13 = currentThread.getName() + "(" + currentThread.getId() + ")";
            } else {
                x.c("Name of crash thread: %s", str18);
                Iterator<Thread> it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        str12 = str18;
                        break;
                    }
                    Thread next = it.next();
                    if (next.getName().equals(str18)) {
                        str12 = str18 + "(" + next.getId() + ")";
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    str13 = str12 + "(" + i2 + ")";
                }
                long j3 = j2 / 1000;
                String str19 = (String) hashMap.get("SysLogPath");
                String str20 = (String) hashMap.get("JniLogPath");
                if (!this.d.b()) {
                    x.d("no remote but still store!", new Object[0]);
                }
                if (this.d.c().g || !this.d.b()) {
                    try {
                        CrashDetailBean packageCrashDatas = packageCrashDatas(str11, str12, (1000 * j) + j3, str10, str2, a2, str9, str8, str4, str19, str20, str7, (byte[]) null, (Map<String, String>) null, true, z);
                        if (packageCrashDatas == null) {
                            x.e("pkg crash datas fail!", new Object[0]);
                            return;
                        }
                        b.a("NATIVE_CRASH", z.a(), str11, str12, str10 + "\n" + str2 + "\n" + a2, packageCrashDatas);
                        try {
                            boolean b2 = this.b.b(packageCrashDatas);
                            String str21 = null;
                            NativeCrashHandler instance = NativeCrashHandler.getInstance();
                            if (instance != null) {
                                str21 = instance.getDumpFilePath();
                            }
                            b.a(true, str21);
                            if (!b2) {
                                this.b.a(packageCrashDatas, 3000, true);
                            }
                            this.b.c(packageCrashDatas);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (!x.a(th)) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (!x.a(th)) {
                            th.printStackTrace();
                            return;
                        }
                        return;
                    }
                } else {
                    x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    b.a("NATIVE_CRASH", z.a(), str11, str12, str10 + "\n" + str2 + "\n" + a2, (CrashDetailBean) null);
                    z.b(str4);
                    return;
                }
            }
            str12 = str13;
            long j32 = j2 / 1000;
            String str192 = (String) hashMap.get("SysLogPath");
            String str202 = (String) hashMap.get("JniLogPath");
            if (!this.d.b()) {
            }
            if (this.d.c().g || !this.d.b()) {
            }
        } catch (Throwable th3) {
            th = th3;
            if (!x.a(th)) {
            }
        }
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int length;
        String str12;
        int indexOf;
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
        if (str5 == null) {
            str5 = "";
        }
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        crashDetailBean.A = str;
        crashDetailBean.B = str2;
        crashDetailBean.I = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.J();
        crashDetailBean.v = str8;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        String dumpFilePath = instance != null ? instance.getDumpFilePath() : null;
        String a2 = b.a(dumpFilePath, str8);
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
            if (indexOf2 > 0 && (length = "java:\n".length() + indexOf2) < crashDetailBean.q.length()) {
                String substring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B) && (indexOf = str12.indexOf(substring)) > 0) {
                    String substring2 = (str12 = crashDetailBean.z.get(crashDetailBean.B)).substring(indexOf);
                    crashDetailBean.z.put(crashDetailBean.B, substring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, length);
                    crashDetailBean.q += substring2;
                }
            }
            if (str == null) {
                crashDetailBean.A = this.c.d;
            }
            this.b.d(crashDetailBean);
            crashDetailBean.Q = this.c.H();
            crashDetailBean.R = this.c.I();
            crashDetailBean.S = this.c.B();
            crashDetailBean.T = this.c.G();
            return crashDetailBean;
        }
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
        if (str == null) {
            crashDetailBean.A = "unknown(record)";
        }
        if (bArr != null) {
            crashDetailBean.y = bArr;
        }
        return crashDetailBean;
    }
}
