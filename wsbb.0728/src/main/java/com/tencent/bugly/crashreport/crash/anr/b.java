package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class b implements ac {
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final w e;
    private final com.tencent.bugly.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.crashreport.crash.b h;
    private FileObserver i;
    private boolean j = true;
    private ab k;
    private int l;
    private ActivityManager.ProcessErrorStateInfo m;

    public b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, w wVar, com.tencent.bugly.crashreport.crash.b bVar) {
        this.c = z.a(context);
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = wVar;
        this.f = aVar;
        this.h = bVar;
        this.m = new ActivityManager.ProcessErrorStateInfo();
    }

    private ActivityManager.ProcessErrorStateInfo a(Context context, long j2) {
        int i2 = 0;
        try {
            x.c("to find!", new Object[0]);
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            while (true) {
                int i3 = i2;
                x.c("waiting!", new Object[0]);
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo next : processesInErrorState) {
                        if (next.condition == 2) {
                            x.c("found!", new Object[0]);
                            return next;
                        }
                    }
                }
                z.b(500);
                if (((long) i3) >= 20) {
                    x.c("end!", new Object[0]);
                    break;
                }
                i2 = i3 + 1;
            }
        } catch (Exception e2) {
            x.b(e2);
        } catch (OutOfMemoryError e3) {
            this.m.pid = Process.myPid();
            ActivityManager.ProcessErrorStateInfo processErrorStateInfo = this.m;
            processErrorStateInfo.shortMsg = "bugly sdk waitForAnrProcessStateChanged encount error:" + e3.getMessage();
            return this.m;
        }
        return null;
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.k();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.m();
            crashDetailBean.F = this.d.p();
            crashDetailBean.G = this.d.o();
            crashDetailBean.H = this.d.q();
            Context context = this.c;
            if (!com.tencent.bugly.crashreport.common.info.b.t()) {
                crashDetailBean.w = z.a(this.c, c.e, (String) null);
            }
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.k;
            crashDetailBean.g = this.d.w();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.P = new HashMap();
            crashDetailBean.P.put("BUGLY_CR_01", aVar.e);
            int i2 = -1;
            if (crashDetailBean.q != null) {
                i2 = crashDetailBean.q.indexOf("\n");
            }
            crashDetailBean.p = i2 > 0 ? crashDetailBean.q.substring(0, i2) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = aVar.b;
            crashDetailBean.A = aVar.a;
            crashDetailBean.B = "main(1)";
            crashDetailBean.I = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.J();
            crashDetailBean.v = aVar.d;
            crashDetailBean.L = this.d.o;
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            Context context2 = this.c;
            if (!com.tencent.bugly.crashreport.common.info.b.t()) {
                this.h.d(crashDetailBean);
            }
            crashDetailBean.Q = this.d.H();
            crashDetailBean.R = this.d.I();
            crashDetailBean.S = this.d.B();
            crashDetailBean.T = this.d.G();
            crashDetailBean.y = y.a();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    private boolean a(Context context, String str, ActivityManager.ProcessErrorStateInfo processErrorStateInfo, long j2, Map<String, String> map) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "bugly/bugly_trace_" + j2 + ".txt");
        a aVar = new a();
        aVar.c = j2;
        aVar.d = file.getAbsolutePath();
        aVar.a = processErrorStateInfo != null ? processErrorStateInfo.processName : "";
        aVar.f = processErrorStateInfo != null ? processErrorStateInfo.shortMsg : "";
        aVar.e = processErrorStateInfo != null ? processErrorStateInfo.longMsg : "";
        aVar.b = map;
        Thread thread = Looper.getMainLooper().getThread();
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (next.startsWith(thread.getName())) {
                    aVar.g = map.get(next);
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(aVar.g)) {
            aVar.g = "main stack is null , some error may be encountered.";
        }
        x.c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", Long.valueOf(aVar.c), aVar.d, aVar.a, aVar.g, aVar.f, aVar.e, Integer.valueOf(aVar.b == null ? 0 : aVar.b.size()));
        if (!this.f.c().j) {
            x.d("ANR Report is closed! print local for helpful!", new Object[0]);
            com.tencent.bugly.crashreport.crash.b.a("ANR", z.a(), aVar.a, "main", aVar.g, (CrashDetailBean) null);
            return false;
        }
        x.a("found visiable anr , start to upload!", new Object[0]);
        CrashDetailBean a2 = a(aVar);
        if (a2 == null) {
            x.e("pack anr fail!", new Object[0]);
            return false;
        }
        c.a().a(a2);
        if (a2.a >= 0) {
            x.a("backup anr record success!", new Object[0]);
        } else {
            x.d("backup anr record fail!", new Object[0]);
        }
        if (str != null && new File(str).exists()) {
            this.a.set(3);
            if (a(str, aVar.d, aVar.a)) {
                x.a("backup trace success", new Object[0]);
            }
        }
        com.tencent.bugly.crashreport.crash.b.a("ANR", z.a(), aVar.a, "main", aVar.g, a2);
        if (!this.h.a(a2)) {
            this.h.a(a2, 3000, true);
        }
        this.h.c(a2);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0131 A[Catch:{ all -> 0x017a }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0161 A[SYNTHETIC, Splitter:B:48:0x0161] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017e A[SYNTHETIC, Splitter:B:62:0x017e] */
    private static boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter = null;
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.size() <= 0) {
            x.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (!file.exists() || !file.canWrite()) {
                x.e("backup file create fail %s", str2);
                return false;
            }
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, false));
                try {
                    String[] strArr = readTargetDumpInfo.d.get("main");
                    if (strArr != null && strArr.length >= 3) {
                        String str4 = strArr[0];
                        String str5 = strArr[1];
                        String str6 = strArr[2];
                        bufferedWriter2.write("\"main\" tid=" + str6 + " :\n" + str4 + "\n" + str5 + "\n\n");
                        bufferedWriter2.flush();
                    }
                    for (Map.Entry next : readTargetDumpInfo.d.entrySet()) {
                        if (!((String) next.getKey()).equals("main") && next.getValue() != null && ((String[]) next.getValue()).length >= 3) {
                            String str7 = ((String[]) next.getValue())[0];
                            String str8 = ((String[]) next.getValue())[1];
                            String str9 = ((String[]) next.getValue())[2];
                            bufferedWriter2.write("\"" + ((String) next.getKey()) + "\" tid=" + str9 + " :\n" + str7 + "\n" + str8 + "\n\n");
                            bufferedWriter2.flush();
                        }
                    }
                    try {
                        bufferedWriter2.close();
                        return true;
                    } catch (IOException e2) {
                        if (!x.a(e2)) {
                            e2.printStackTrace();
                        }
                        return true;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter = bufferedWriter2;
                    try {
                        if (!x.a(e)) {
                        }
                        x.e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                        if (bufferedWriter != null) {
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter2 = bufferedWriter;
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e4) {
                                if (!x.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter2 != null) {
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                if (!x.a(e)) {
                    e.printStackTrace();
                }
                x.e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                        return false;
                    } catch (IOException e6) {
                        if (!x.a(e6)) {
                            e6.printStackTrace();
                        }
                        return false;
                    }
                }
                return false;
            }
        } catch (Exception e7) {
            if (!x.a(e7)) {
                e7.printStackTrace();
            }
            x.e("backup file create error! %s  %s", e7.getClass().getName() + ":" + e7.getMessage(), str2);
            return false;
        }
    }

    private void b(boolean z) {
        synchronized (this) {
            if (z) {
                f();
            } else {
                g();
            }
        }
    }

    private void c(boolean z) {
        synchronized (this) {
            if (this.j != z) {
                x.a("user change anr %b", Boolean.valueOf(z));
                this.j = z;
            }
        }
    }

    private void f() {
        synchronized (this) {
            if (h()) {
                x.d("start when started!", new Object[0]);
                return;
            }
            this.i = new FileObserver("/data/anr/", 8) {
                public final void onEvent(int i, String str) {
                    if (str != null) {
                        String str2 = "/data/anr/" + str;
                        if (!str2.contains("trace")) {
                            x.d("not anr file %s", str2);
                            return;
                        }
                        b.this.a(str2);
                    }
                }
            };
            try {
                this.i.startWatching();
                x.a("start anr monitor!", new Object[0]);
                this.e.a(new Runnable() {
                    public final void run() {
                        b.this.b();
                    }
                });
            } catch (Throwable th) {
                this.i = null;
                x.d("start anr monitor failed!", new Object[0]);
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void g() {
        synchronized (this) {
            if (!h()) {
                x.d("close when closed!", new Object[0]);
                return;
            }
            try {
                this.i.stopWatching();
                this.i = null;
                x.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                x.d("stop anr monitor failed!", new Object[0]);
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private boolean h() {
        boolean z;
        synchronized (this) {
            z = this.i != null;
        }
        return z;
    }

    private boolean i() {
        boolean z;
        synchronized (this) {
            z = this.j;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.tencent.bugly.proguard.x.c("read trace first dump for create time!", new java.lang.Object[0]);
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (r2 == null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r4 = r2.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r4 != -1) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        com.tencent.bugly.proguard.x.d("trace dump fail could not get time!", new java.lang.Object[0]);
        r4 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        if (java.lang.Math.abs(r4 - r10.b) >= 10000) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        com.tencent.bugly.proguard.x.d("should not process ANR too Fre in %d", 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r10.b = r4;
        r10.a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r6 = com.tencent.bugly.proguard.z.a(com.tencent.bugly.crashreport.crash.c.f, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0072, code lost:
        if (r6 == null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0078, code lost:
        if (r6.size() > 0) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007a, code lost:
        com.tencent.bugly.proguard.x.d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        if (com.tencent.bugly.proguard.x.a(r0) == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008a, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008d, code lost:
        com.tencent.bugly.proguard.x.e("handle anr error %s", r0.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r10.m = a(r10.c, 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b3, code lost:
        if (r10.m != null) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b5, code lost:
        com.tencent.bugly.proguard.x.c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bf, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c4, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cd, code lost:
        if (r10.m.pid == android.os.Process.myPid()) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cf, code lost:
        com.tencent.bugly.proguard.x.c("not mind proc!", r10.m.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        com.tencent.bugly.proguard.x.a("found visiable anr , start to process!", new java.lang.Object[0]);
        a(r10.c, r11, r10.m, r4, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f3, code lost:
        com.tencent.bugly.proguard.x.a(r0);
        com.tencent.bugly.proguard.x.e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0103, code lost:
        r4 = -1;
     */
    public final void a(String str) {
        synchronized (this) {
            if (this.a.get() != 0) {
                x.c("trace started return ", new Object[0]);
                return;
            }
            this.a.set(1);
        }
        this.a.set(0);
        return;
        this.a.set(0);
    }

    public final void a(boolean z) {
        c(z);
        boolean i2 = i();
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 != null) {
            i2 = i2 && a2.c().g;
        }
        if (i2 != h()) {
            x.a("anr changed to %b", Boolean.valueOf(i2));
            b(i2);
        }
    }

    public final boolean a() {
        return this.a.get() != 0;
    }

    public final boolean a(aa aaVar) {
        Map hashMap = new HashMap();
        if (aaVar.e().equals(Looper.getMainLooper())) {
            this.m = a(this.c, 10000);
            if (this.m == null) {
                x.c("anr handler onThreadBlock proc state is unvisiable!", new Object[0]);
                return false;
            } else if (this.m.pid != Process.myPid()) {
                x.c("onThreadBlock not mind proc!", this.m.processName);
                return false;
            } else {
                try {
                    hashMap = z.a(200000, false);
                } catch (Throwable th) {
                    x.b(th);
                    hashMap.put("main", th.getMessage());
                }
                x.c("onThreadBlock found visiable anr , start to process!", new Object[0]);
                a(this.c, "", this.m, System.currentTimeMillis(), hashMap);
                return true;
            }
        } else {
            x.c("anr handler onThreadBlock only care main thread ,current thread is: %s", aaVar.d());
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public final void b() {
        String name;
        long b2 = z.b();
        long j2 = c.g;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    int length = "bugly_trace_".length();
                    int i2 = 0;
                    for (File file2 : listFiles) {
                        name = file2.getName();
                        if (name.startsWith("bugly_trace_")) {
                            int indexOf = name.indexOf(".txt");
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b2 - j2) {
                            }
                            if (file2.delete()) {
                                i2++;
                            }
                        }
                    }
                    x.c("Number of overdue trace files that has deleted: " + i2, new Object[0]);
                }
            } catch (Throwable th) {
                x.a(th);
            }
        }
    }

    public final void c() {
        synchronized (this) {
            x.d("customer decides whether to open or close.", new Object[0]);
        }
    }

    public final boolean d() {
        if (this.k != null && this.k.isAlive()) {
            return false;
        }
        this.k = new ab();
        ab abVar = this.k;
        StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
        int i2 = this.l;
        this.l = i2 + 1;
        sb.append(i2);
        abVar.setName(sb.toString());
        this.k.a();
        this.k.a(this);
        return this.k.d();
    }

    public final boolean e() {
        if (this.k == null) {
            return false;
        }
        boolean c2 = this.k.c();
        this.k.b();
        this.k.b(this);
        this.k = null;
        return c2;
    }
}
