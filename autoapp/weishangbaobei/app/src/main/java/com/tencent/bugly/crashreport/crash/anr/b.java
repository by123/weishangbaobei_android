package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
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
        try {
            x.c("to find!", new Object[0]);
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int i2 = 0;
            while (true) {
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
                int i3 = i2 + 1;
                if (((long) i2) >= 20) {
                    x.c("end!", new Object[0]);
                    return null;
                }
                i2 = i3;
            }
        } catch (Exception e2) {
            x.b(e2);
            return null;
        } catch (OutOfMemoryError e3) {
            this.m.pid = Process.myPid();
            ActivityManager.ProcessErrorStateInfo processErrorStateInfo = this.m;
            processErrorStateInfo.shortMsg = "bugly sdk waitForAnrProcessStateChanged encount error:" + e3.getMessage();
            return this.m;
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:53:0x012f A[Catch:{ all -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015d A[SYNTHETIC, Splitter:B:56:0x015d] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016e A[SYNTHETIC, Splitter:B:64:0x016e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 1
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r9 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTargetDumpInfo(r11, r9, r0)
            r1 = 0
            if (r9 == 0) goto L_0x01bd
            java.util.Map<java.lang.String, java.lang.String[]> r2 = r9.d
            if (r2 == 0) goto L_0x01bd
            java.util.Map<java.lang.String, java.lang.String[]> r2 = r9.d
            int r2 = r2.size()
            if (r2 > 0) goto L_0x0016
            goto L_0x01bd
        L_0x0016:
            java.io.File r11 = new java.io.File
            r11.<init>(r10)
            r2 = 2
            boolean r3 = r11.exists()     // Catch:{ Exception -> 0x0187 }
            if (r3 != 0) goto L_0x0036
            java.io.File r3 = r11.getParentFile()     // Catch:{ Exception -> 0x0187 }
            boolean r3 = r3.exists()     // Catch:{ Exception -> 0x0187 }
            if (r3 != 0) goto L_0x0033
            java.io.File r3 = r11.getParentFile()     // Catch:{ Exception -> 0x0187 }
            r3.mkdirs()     // Catch:{ Exception -> 0x0187 }
        L_0x0033:
            r11.createNewFile()     // Catch:{ Exception -> 0x0187 }
        L_0x0036:
            boolean r3 = r11.exists()
            if (r3 == 0) goto L_0x017d
            boolean r3 = r11.canWrite()
            if (r3 != 0) goto L_0x0044
            goto L_0x017d
        L_0x0044:
            r10 = 0
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0128 }
            java.io.FileWriter r4 = new java.io.FileWriter     // Catch:{ IOException -> 0x0128 }
            r4.<init>(r11, r1)     // Catch:{ IOException -> 0x0128 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0128 }
            java.util.Map<java.lang.String, java.lang.String[]> r10 = r9.d     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r11 = "main"
            java.lang.Object r10 = r10.get(r11)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r11 = 3
            if (r10 == 0) goto L_0x008e
            int r4 = r10.length     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 < r11) goto L_0x008e
            r4 = r10[r1]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r5 = r10[r0]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r10 = r10[r2]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r7 = "\"main\" tid="
            r6.<init>(r7)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = " :\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r4)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = r6.toString()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.write(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.flush()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
        L_0x008e:
            java.util.Map<java.lang.String, java.lang.String[]> r9 = r9.d     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Set r9 = r9.entrySet()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
        L_0x0098:
            boolean r10 = r9.hasNext()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r10 == 0) goto L_0x0111
            java.lang.Object r10 = r9.next()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r4 = r10.getKey()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r5 = "main"
            boolean r4 = r4.equals(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 != 0) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 == 0) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            int r4 = r4.length     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 < r11) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r4 = r4[r1]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r5 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r5 = r5[r0]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r6 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6 = r6[r2]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r8 = "\""
            r7.<init>(r8)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r10 = r10.getKey()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\" tid="
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r6)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = " :\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r4)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = r7.toString()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.write(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.flush()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            goto L_0x0098
        L_0x0111:
            r3.close()     // Catch:{ IOException -> 0x0115 }
            goto L_0x011f
        L_0x0115:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.a(r9)
            if (r10 != 0) goto L_0x011f
            r9.printStackTrace()
        L_0x011f:
            return r0
        L_0x0120:
            r9 = move-exception
            goto L_0x016c
        L_0x0122:
            r9 = move-exception
            r10 = r3
            goto L_0x0129
        L_0x0125:
            r9 = move-exception
            r3 = r10
            goto L_0x016c
        L_0x0128:
            r9 = move-exception
        L_0x0129:
            boolean r11 = com.tencent.bugly.proguard.x.a(r9)     // Catch:{ all -> 0x0125 }
            if (r11 != 0) goto L_0x0132
            r9.printStackTrace()     // Catch:{ all -> 0x0125 }
        L_0x0132:
            java.lang.String r11 = "dump trace fail %s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0125 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0125 }
            r2.<init>()     // Catch:{ all -> 0x0125 }
            java.lang.Class r3 = r9.getClass()     // Catch:{ all -> 0x0125 }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x0125 }
            r2.append(r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0125 }
            r2.append(r9)     // Catch:{ all -> 0x0125 }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x0125 }
            r0[r1] = r9     // Catch:{ all -> 0x0125 }
            com.tencent.bugly.proguard.x.e(r11, r0)     // Catch:{ all -> 0x0125 }
            if (r10 == 0) goto L_0x016b
            r10.close()     // Catch:{ IOException -> 0x0161 }
            goto L_0x016b
        L_0x0161:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.a(r9)
            if (r10 != 0) goto L_0x016b
            r9.printStackTrace()
        L_0x016b:
            return r1
        L_0x016c:
            if (r3 == 0) goto L_0x017c
            r3.close()     // Catch:{ IOException -> 0x0172 }
            goto L_0x017c
        L_0x0172:
            r10 = move-exception
            boolean r11 = com.tencent.bugly.proguard.x.a(r10)
            if (r11 != 0) goto L_0x017c
            r10.printStackTrace()
        L_0x017c:
            throw r9
        L_0x017d:
            java.lang.String r9 = "backup file create fail %s"
            java.lang.Object[] r11 = new java.lang.Object[r0]
            r11[r1] = r10
            com.tencent.bugly.proguard.x.e(r9, r11)
            return r1
        L_0x0187:
            r9 = move-exception
            boolean r11 = com.tencent.bugly.proguard.x.a(r9)
            if (r11 != 0) goto L_0x0191
            r9.printStackTrace()
        L_0x0191:
            java.lang.String r11 = "backup file create error! %s  %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r9.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = ":"
            r3.append(r4)
            java.lang.String r9 = r9.getMessage()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            r2[r1] = r9
            r2[r0] = r10
            com.tencent.bugly.proguard.x.e(r11, r2)
            return r1
        L_0x01bd:
            java.lang.String r9 = "not found trace dump for %s"
            java.lang.Object[] r10 = new java.lang.Object[r0]
            r10[r1] = r11
            com.tencent.bugly.proguard.x.e(r9, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public final boolean a() {
        return this.a.get() != 0;
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
        Object[] objArr = new Object[7];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.g;
        objArr[4] = aVar.f;
        objArr[5] = aVar.e;
        objArr[6] = Integer.valueOf(aVar.b == null ? 0 : aVar.b.size());
        x.c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", objArr);
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

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.tencent.bugly.proguard.x.c("read trace first dump for create time!", new java.lang.Object[0]);
        r0 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r0 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r5 = r0.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r5 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r5 != -1) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        com.tencent.bugly.proguard.x.d("trace dump fail could not get time!", new java.lang.Object[0]);
        r5 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (java.lang.Math.abs(r7 - r10.b) >= 10000) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        com.tencent.bugly.proguard.x.d("should not process ANR too Fre in %d", 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r10.b = r7;
        r10.a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r9 = com.tencent.bugly.proguard.z.a(com.tencent.bugly.crashreport.crash.c.f, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006e, code lost:
        if (r9 == null) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0074, code lost:
        if (r9.size() > 0) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        r10.m = a(r10.c, 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        if (r10.m != null) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0083, code lost:
        com.tencent.bugly.proguard.x.c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0093, code lost:
        if (r10.m.pid == android.os.Process.myPid()) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0095, code lost:
        com.tencent.bugly.proguard.x.c("not mind proc!", r10.m.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a3, code lost:
        com.tencent.bugly.proguard.x.a("found visiable anr , start to process!", new java.lang.Object[0]);
        a(r10.c, r11, r10.m, r7, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        com.tencent.bugly.proguard.x.d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c1, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c2, code lost:
        com.tencent.bugly.proguard.x.a(r11);
        com.tencent.bugly.proguard.x.e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cd, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cf, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d4, code lost:
        if (com.tencent.bugly.proguard.x.a(r11) == false) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d6, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d9, code lost:
        com.tencent.bugly.proguard.x.e("handle anr error %s", r11.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00eb, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f0, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ all -> 0x00f1 }
            int r0 = r0.get()     // Catch:{ all -> 0x00f1 }
            r1 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r11 = "trace started return "
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x00f1 }
            monitor-exit(r10)     // Catch:{ all -> 0x00f1 }
            return
        L_0x0013:
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ all -> 0x00f1 }
            r2 = 1
            r0.set(r2)     // Catch:{ all -> 0x00f1 }
            monitor-exit(r10)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = "read trace first dump for create time!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.c(r0, r3)     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r0 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, r1)     // Catch:{ Throwable -> 0x00cf }
            r3 = -1
            if (r0 == 0) goto L_0x002c
            long r5 = r0.c     // Catch:{ Throwable -> 0x00cf }
            goto L_0x002d
        L_0x002c:
            r5 = r3
        L_0x002d:
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = "trace dump fail could not get time!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.d(r0, r3)     // Catch:{ Throwable -> 0x00cf }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00cf }
        L_0x003c:
            r7 = r5
            long r3 = r10.b     // Catch:{ Throwable -> 0x00cf }
            r0 = 0
            long r3 = r7 - r3
            long r3 = java.lang.Math.abs(r3)     // Catch:{ Throwable -> 0x00cf }
            r5 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0061
            java.lang.String r11 = "should not process ANR too Fre in %d"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00cf }
            r3 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x00cf }
            r0[r1] = r3     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ Throwable -> 0x00cf }
        L_0x005b:
            java.util.concurrent.atomic.AtomicInteger r11 = r10.a
            r11.set(r1)
            return
        L_0x0061:
            r10.b = r7     // Catch:{ Throwable -> 0x00cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ Throwable -> 0x00cf }
            r0.set(r2)     // Catch:{ Throwable -> 0x00cf }
            int r0 = com.tencent.bugly.crashreport.crash.c.f     // Catch:{ Throwable -> 0x00c1 }
            java.util.Map r9 = com.tencent.bugly.proguard.z.a((int) r0, (boolean) r1)     // Catch:{ Throwable -> 0x00c1 }
            if (r9 == 0) goto L_0x00b9
            int r0 = r9.size()     // Catch:{ Throwable -> 0x00cf }
            if (r0 > 0) goto L_0x0077
            goto L_0x00b9
        L_0x0077:
            android.content.Context r0 = r10.c     // Catch:{ Throwable -> 0x00cf }
            android.app.ActivityManager$ProcessErrorStateInfo r0 = r10.a(r0, r5)     // Catch:{ Throwable -> 0x00cf }
            r10.m = r0     // Catch:{ Throwable -> 0x00cf }
            android.app.ActivityManager$ProcessErrorStateInfo r0 = r10.m     // Catch:{ Throwable -> 0x00cf }
            if (r0 != 0) goto L_0x008b
            java.lang.String r11 = "proc state is unvisiable!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ Throwable -> 0x00cf }
            goto L_0x005b
        L_0x008b:
            android.app.ActivityManager$ProcessErrorStateInfo r0 = r10.m     // Catch:{ Throwable -> 0x00cf }
            int r0 = r0.pid     // Catch:{ Throwable -> 0x00cf }
            int r3 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x00cf }
            if (r0 == r3) goto L_0x00a3
            java.lang.String r11 = "not mind proc!"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00cf }
            android.app.ActivityManager$ProcessErrorStateInfo r3 = r10.m     // Catch:{ Throwable -> 0x00cf }
            java.lang.String r3 = r3.processName     // Catch:{ Throwable -> 0x00cf }
            r0[r1] = r3     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ Throwable -> 0x00cf }
            goto L_0x005b
        L_0x00a3:
            java.lang.String r0 = "found visiable anr , start to process!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.a(r0, r3)     // Catch:{ Throwable -> 0x00cf }
            android.content.Context r4 = r10.c     // Catch:{ Throwable -> 0x00cf }
            android.app.ActivityManager$ProcessErrorStateInfo r6 = r10.m     // Catch:{ Throwable -> 0x00cf }
            r3 = r10
            r5 = r11
            r3.a(r4, r5, r6, r7, r9)     // Catch:{ Throwable -> 0x00cf }
        L_0x00b3:
            java.util.concurrent.atomic.AtomicInteger r11 = r10.a
            r11.set(r1)
            return
        L_0x00b9:
            java.lang.String r11 = "can't get all thread skip this anr"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ Throwable -> 0x00cf }
            goto L_0x005b
        L_0x00c1:
            r11 = move-exception
            com.tencent.bugly.proguard.x.a(r11)     // Catch:{ Throwable -> 0x00cf }
            java.lang.String r11 = "get all thread stack fail!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00cf }
            com.tencent.bugly.proguard.x.e(r11, r0)     // Catch:{ Throwable -> 0x00cf }
            goto L_0x005b
        L_0x00cd:
            r11 = move-exception
            goto L_0x00eb
        L_0x00cf:
            r11 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r11)     // Catch:{ all -> 0x00cd }
            if (r0 != 0) goto L_0x00d9
            r11.printStackTrace()     // Catch:{ all -> 0x00cd }
        L_0x00d9:
            java.lang.String r0 = "handle anr error %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00cd }
            java.lang.Class r11 = r11.getClass()     // Catch:{ all -> 0x00cd }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00cd }
            r2[r1] = r11     // Catch:{ all -> 0x00cd }
            com.tencent.bugly.proguard.x.e(r0, r2)     // Catch:{ all -> 0x00cd }
            goto L_0x00b3
        L_0x00eb:
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a
            r0.set(r1)
            throw r11
        L_0x00f1:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    private synchronized void f() {
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

    private synchronized void g() {
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

    private synchronized boolean h() {
        return this.i != null;
    }

    private synchronized void b(boolean z) {
        if (z) {
            f();
        } else {
            g();
        }
    }

    private synchronized boolean i() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            x.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
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

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        com.tencent.bugly.proguard.x.c("Trace file that has invalid format: " + r11, new java.lang.Object[0]);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r14 = this;
            long r0 = com.tencent.bugly.proguard.z.b()
            long r2 = com.tencent.bugly.crashreport.crash.c.g
            long r0 = r0 - r2
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r14.g
            r2.<init>(r3)
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x0088
            boolean r3 = r2.isDirectory()
            if (r3 == 0) goto L_0x0088
            java.io.File[] r2 = r2.listFiles()     // Catch:{ Throwable -> 0x0084 }
            if (r2 == 0) goto L_0x0083
            int r3 = r2.length     // Catch:{ Throwable -> 0x0084 }
            if (r3 != 0) goto L_0x0024
            goto L_0x0083
        L_0x0024:
            java.lang.String r3 = "bugly_trace_"
            java.lang.String r4 = ".txt"
            int r5 = r3.length()     // Catch:{ Throwable -> 0x0084 }
            int r6 = r2.length     // Catch:{ Throwable -> 0x0084 }
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0030:
            if (r8 >= r6) goto L_0x006f
            r10 = r2[r8]     // Catch:{ Throwable -> 0x0084 }
            java.lang.String r11 = r10.getName()     // Catch:{ Throwable -> 0x0084 }
            boolean r12 = r11.startsWith(r3)     // Catch:{ Throwable -> 0x0084 }
            if (r12 == 0) goto L_0x006c
            int r12 = r11.indexOf(r4)     // Catch:{ Throwable -> 0x0051 }
            if (r12 <= 0) goto L_0x0064
            java.lang.String r12 = r11.substring(r5, r12)     // Catch:{ Throwable -> 0x0051 }
            long r12 = java.lang.Long.parseLong(r12)     // Catch:{ Throwable -> 0x0051 }
            int r11 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r11 < 0) goto L_0x0064
            goto L_0x006c
        L_0x0051:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0084 }
            java.lang.String r13 = "Trace file that has invalid format: "
            r12.<init>(r13)     // Catch:{ Throwable -> 0x0084 }
            r12.append(r11)     // Catch:{ Throwable -> 0x0084 }
            java.lang.String r11 = r12.toString()     // Catch:{ Throwable -> 0x0084 }
            java.lang.Object[] r12 = new java.lang.Object[r7]     // Catch:{ Throwable -> 0x0084 }
            com.tencent.bugly.proguard.x.c(r11, r12)     // Catch:{ Throwable -> 0x0084 }
        L_0x0064:
            boolean r10 = r10.delete()     // Catch:{ Throwable -> 0x0084 }
            if (r10 == 0) goto L_0x006c
            int r9 = r9 + 1
        L_0x006c:
            int r8 = r8 + 1
            goto L_0x0030
        L_0x006f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0084 }
            java.lang.String r1 = "Number of overdue trace files that has deleted: "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0084 }
            r0.append(r9)     // Catch:{ Throwable -> 0x0084 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0084 }
            java.lang.Object[] r1 = new java.lang.Object[r7]     // Catch:{ Throwable -> 0x0084 }
            com.tencent.bugly.proguard.x.c(r0, r1)     // Catch:{ Throwable -> 0x0084 }
            return
        L_0x0083:
            return
        L_0x0084:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.b():void");
    }

    public final synchronized void c() {
        x.d("customer decides whether to open or close.", new Object[0]);
    }

    public final boolean a(aa aaVar) {
        Map map;
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
                    map = z.a(200000, false);
                } catch (Throwable th) {
                    x.b(th);
                    hashMap.put("main", th.getMessage());
                    map = hashMap;
                }
                x.c("onThreadBlock found visiable anr , start to process!", new Object[0]);
                a(this.c, "", this.m, System.currentTimeMillis(), map);
            }
        } else {
            x.c("anr handler onThreadBlock only care main thread ,current thread is: %s", aaVar.d());
        }
        return true;
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
