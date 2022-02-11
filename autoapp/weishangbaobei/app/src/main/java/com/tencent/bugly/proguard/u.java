package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.bugly.b;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.umeng.commonsdk.proguard.c;
import com.umeng.socialize.ShareContent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BUGLY */
public final class u {
    private static u b;
    public boolean a = true;
    private final p c;
    private final Context d;
    private Map<Integer, Long> e = new HashMap();
    private long f;
    private long g;
    private LinkedBlockingQueue<Runnable> h = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Runnable> i = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public final Object j = new Object();
    private String k = null;
    private byte[] l = null;
    private long m = 0;
    /* access modifiers changed from: private */
    public byte[] n = null;
    private long o = 0;
    /* access modifiers changed from: private */
    public String p = null;
    private long q = 0;
    private final Object r = new Object();
    /* access modifiers changed from: private */
    public boolean s = false;
    /* access modifiers changed from: private */
    public final Object t = new Object();
    private int u = 0;

    static /* synthetic */ int b(u uVar) {
        int i2 = uVar.u - 1;
        uVar.u = i2;
        return i2;
    }

    private u(Context context) {
        this.d = context;
        this.c = p.a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException unused) {
            x.a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.a = false;
        }
        if (this.a) {
            this.k = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/" + "fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB";
        }
    }

    public static synchronized u a(Context context) {
        u uVar;
        synchronized (u.class) {
            if (b == null) {
                b = new u(context);
            }
            uVar = b;
        }
        return uVar;
    }

    public static synchronized u a() {
        u uVar;
        synchronized (u.class) {
            uVar = b;
        }
        return uVar;
    }

    public final void a(int i2, ap apVar, String str, String str2, t tVar, long j2, boolean z) {
        try {
            a(new v(this.d, i2, apVar.g, a.a((Object) apVar), str, str2, tVar, this.a, z), true, true, j2);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i2, int i3, byte[] bArr, String str, String str2, t tVar, int i4, int i5, boolean z, Map<String, String> map) {
        try {
            a(new v(this.d, i2, i3, bArr, str, str2, tVar, this.a, i4, i5, false, map), z, false, 0);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i2, ap apVar, String str, String str2, t tVar, boolean z) {
        a(i2, apVar.g, a.a((Object) apVar), str, str2, tVar, 0, 0, z, (Map<String, String>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(boolean r12) {
        /*
            r11 = this;
            long r0 = com.tencent.bugly.proguard.z.b()
            r2 = 3
            if (r12 == 0) goto L_0x0009
            r3 = 5
            goto L_0x000a
        L_0x0009:
            r3 = 3
        L_0x000a:
            com.tencent.bugly.proguard.p r4 = r11.c
            java.util.List r4 = r4.a((int) r3)
            r5 = 0
            if (r4 == 0) goto L_0x004d
            int r6 = r4.size()
            if (r6 <= 0) goto L_0x004d
            r6 = 0
            java.lang.Object r12 = r4.get(r5)     // Catch:{ Throwable -> 0x003c }
            com.tencent.bugly.proguard.r r12 = (com.tencent.bugly.proguard.r) r12     // Catch:{ Throwable -> 0x003c }
            long r8 = r12.e     // Catch:{ Throwable -> 0x003c }
            int r10 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r10 < 0) goto L_0x003a
            byte[] r0 = r12.g     // Catch:{ Throwable -> 0x003c }
            long r0 = com.tencent.bugly.proguard.z.c((byte[]) r0)     // Catch:{ Throwable -> 0x003c }
            if (r3 != r2) goto L_0x0034
            r11.f = r0     // Catch:{ Throwable -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r12 = move-exception
            goto L_0x003e
        L_0x0034:
            r11.g = r0     // Catch:{ Throwable -> 0x0032 }
        L_0x0036:
            r4.remove(r12)     // Catch:{ Throwable -> 0x0032 }
            goto L_0x0041
        L_0x003a:
            r0 = r6
            goto L_0x0041
        L_0x003c:
            r12 = move-exception
            r0 = r6
        L_0x003e:
            com.tencent.bugly.proguard.x.a(r12)
        L_0x0041:
            int r12 = r4.size()
            if (r12 <= 0) goto L_0x0054
            com.tencent.bugly.proguard.p r12 = r11.c
            r12.a((java.util.List<com.tencent.bugly.proguard.r>) r4)
            goto L_0x0054
        L_0x004d:
            if (r12 == 0) goto L_0x0052
            long r0 = r11.g
            goto L_0x0054
        L_0x0052:
            long r0 = r11.f
        L_0x0054:
            java.lang.String r12 = "[UploadManager] Local network consume: %d KB"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 1024(0x400, double:5.06E-321)
            long r3 = r0 / r3
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2[r5] = r3
            com.tencent.bugly.proguard.x.c(r12, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(boolean):long");
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j2, boolean z) {
        int i2 = z ? 5 : 3;
        r rVar = new r();
        rVar.b = i2;
        rVar.e = z.b();
        rVar.c = "";
        rVar.d = "";
        rVar.g = z.c(j2);
        this.c.b(i2);
        this.c.a(rVar);
        if (z) {
            this.g = j2;
        } else {
            this.f = j2;
        }
        x.c("[UploadManager] Network total consume: %d KB", Long.valueOf(j2 / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS));
    }

    public final synchronized void a(int i2, long j2) {
        if (i2 >= 0) {
            this.e.put(Integer.valueOf(i2), Long.valueOf(j2));
            r rVar = new r();
            rVar.b = i2;
            rVar.e = j2;
            rVar.c = "";
            rVar.d = "";
            rVar.g = new byte[0];
            this.c.b(i2);
            this.c.a(rVar);
            x.c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i2), z.a(j2));
            return;
        }
        x.e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i2));
    }

    public final synchronized long a(int i2) {
        long j2;
        j2 = 0;
        if (i2 >= 0) {
            try {
                Long l2 = this.e.get(Integer.valueOf(i2));
                if (l2 != null) {
                    return l2.longValue();
                }
                List<r> a2 = this.c.a(i2);
                if (a2 != null && a2.size() > 0) {
                    if (a2.size() > 1) {
                        for (r next : a2) {
                            if (next.e > j2) {
                                j2 = next.e;
                            }
                        }
                        this.c.b(i2);
                    } else {
                        j2 = a2.get(0).e;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            x.e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i2));
        }
        return j2;
    }

    public final boolean b(int i2) {
        if (b.c) {
            x.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - a(i2);
        x.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i2));
        if (currentTimeMillis >= c.d) {
            return true;
        }
        x.a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    private static boolean c() {
        x.c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            p a2 = p.a();
            if (a2 != null) {
                return a2.a(555, "security_info", (o) null, true);
            }
            x.d("[UploadManager] Failed to get Database", new Object[0]);
            return false;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    private boolean d() {
        x.c("[UploadManager] Record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            p a2 = p.a();
            if (a2 == null) {
                x.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.n != null) {
                sb.append(Base64.encodeToString(this.n, 0));
                sb.append("#");
                if (this.o != 0) {
                    sb.append(Long.toString(this.o));
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.p != null) {
                    sb.append(this.p);
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.q != 0) {
                    sb.append(Long.toString(this.q));
                } else {
                    sb.append("null");
                }
                a2.a(555, "security_info", sb.toString().getBytes(), (o) null, true);
                return true;
            }
            x.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            x.a(th);
            c();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean e() {
        boolean z;
        x.c("[UploadManager] Load security info from database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            p a2 = p.a();
            if (a2 == null) {
                x.d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            Map<String, byte[]> a3 = a2.a(555, (o) null, true);
            if (a3 != null && a3.containsKey("security_info")) {
                String str = new String(a3.get("security_info"));
                String[] split = str.split("#");
                if (split.length == 4) {
                    if (!split[0].isEmpty() && !split[0].equals("null")) {
                        this.n = Base64.decode(split[0], 0);
                    }
                    z = false;
                    if (!z && !split[1].isEmpty() && !split[1].equals("null")) {
                        try {
                            this.o = Long.parseLong(split[1]);
                        } catch (Throwable th) {
                            x.a(th);
                            z = true;
                        }
                    }
                    if (!z && !split[2].isEmpty() && !split[2].equals("null")) {
                        this.p = split[2];
                    }
                    if (!z && !split[3].isEmpty() && !split[3].equals("null")) {
                        try {
                            this.q = Long.parseLong(split[3]);
                        } catch (Throwable th2) {
                            x.a(th2);
                        }
                    }
                } else {
                    x.a("SecurityInfo = %s, Strings.length = %d", str, Integer.valueOf(split.length));
                    z = true;
                }
                if (z) {
                    c();
                }
            }
            return true;
        } catch (Throwable th3) {
            x.a(th3);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        if (this.p == null || this.q == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.m;
        if (this.q >= currentTimeMillis) {
            return true;
        }
        x.c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.q), new Date(this.q).toString(), Long.valueOf(currentTimeMillis), new Date(currentTimeMillis).toString());
        return false;
    }

    public final void b(boolean z) {
        synchronized (this.r) {
            x.c("[UploadManager] Clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.n = null;
            this.p = null;
            this.q = 0;
        }
        if (z) {
            c();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b9, code lost:
        if (r5 <= 0) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00bb, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r5), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dc, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dd, code lost:
        if (r7 >= r5) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00df, code lost:
        r8 = (java.lang.Runnable) r2.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e5, code lost:
        if (r8 == null) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e7, code lost:
        r10 = r13.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e9, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ec, code lost:
        if (r13.u < 2) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ee, code lost:
        if (r1 == null) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f0, code lost:
        r1.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f3, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f5, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f6, code lost:
        com.tencent.bugly.proguard.x.a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010c, code lost:
        if (com.tencent.bugly.proguard.z.a((java.lang.Runnable) new com.tencent.bugly.proguard.u.AnonymousClass1(r13), "BUGLY_ASYNC_UPLOAD") == null) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010e, code lost:
        r8 = r13.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0110, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r13.u++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x011b, code lost:
        com.tencent.bugly.proguard.x.d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new java.lang.Object[0]);
        a(r8, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0125, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x012b, code lost:
        if (r14 <= 0) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x012d, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r14), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x014e, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0150, code lost:
        r1.a(new com.tencent.bugly.proguard.u.AnonymousClass2(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006c A[Catch:{ Throwable -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0093 A[Catch:{ Throwable -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(int r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 >= 0) goto L_0x000b
            java.lang.String r14 = "[UploadManager] Number of task to execute should >= 0"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.a(r14, r0)
            return
        L_0x000b:
            com.tencent.bugly.proguard.w r1 = com.tencent.bugly.proguard.w.a()
            java.util.concurrent.LinkedBlockingQueue r2 = new java.util.concurrent.LinkedBlockingQueue
            r2.<init>()
            java.util.concurrent.LinkedBlockingQueue r3 = new java.util.concurrent.LinkedBlockingQueue
            r3.<init>()
            java.lang.Object r4 = r13.j
            monitor-enter(r4)
            java.lang.String r5 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x0159 }
            int r8 = android.os.Process.myPid()     // Catch:{ all -> 0x0159 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0159 }
            r7[r0] = r8     // Catch:{ all -> 0x0159 }
            int r8 = android.os.Process.myTid()     // Catch:{ all -> 0x0159 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0159 }
            r9 = 1
            r7[r9] = r8     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.x.c(r5, r7)     // Catch:{ all -> 0x0159 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r5 = r13.h     // Catch:{ all -> 0x0159 }
            int r5 = r5.size()     // Catch:{ all -> 0x0159 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r7 = r13.i     // Catch:{ all -> 0x0159 }
            int r7 = r7.size()     // Catch:{ all -> 0x0159 }
            if (r5 != 0) goto L_0x0052
            if (r7 != 0) goto L_0x0052
            java.lang.String r14 = "[UploadManager] There is no upload task in queue."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.x.c(r14, r0)     // Catch:{ all -> 0x0159 }
            monitor-exit(r4)     // Catch:{ all -> 0x0159 }
            return
        L_0x0052:
            if (r14 == 0) goto L_0x005f
            if (r14 >= r5) goto L_0x0059
            r5 = r14
            r14 = 0
            goto L_0x0060
        L_0x0059:
            int r8 = r5 + r7
            if (r14 >= r8) goto L_0x005f
            int r14 = r14 - r5
            goto L_0x0060
        L_0x005f:
            r14 = r7
        L_0x0060:
            if (r1 == 0) goto L_0x0068
            boolean r7 = r1.c()     // Catch:{ all -> 0x0159 }
            if (r7 != 0) goto L_0x0069
        L_0x0068:
            r14 = 0
        L_0x0069:
            r7 = 0
        L_0x006a:
            if (r7 >= r5) goto L_0x0090
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r8 = r13.h     // Catch:{ all -> 0x0159 }
            java.lang.Object r8 = r8.peek()     // Catch:{ all -> 0x0159 }
            java.lang.Runnable r8 = (java.lang.Runnable) r8     // Catch:{ all -> 0x0159 }
            if (r8 == 0) goto L_0x0090
            r2.put(r8)     // Catch:{ Throwable -> 0x007f }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r8 = r13.h     // Catch:{ Throwable -> 0x007f }
            r8.poll()     // Catch:{ Throwable -> 0x007f }
            goto L_0x008d
        L_0x007f:
            r8 = move-exception
            java.lang.String r10 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            java.lang.Object[] r11 = new java.lang.Object[r9]     // Catch:{ all -> 0x0159 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x0159 }
            r11[r0] = r8     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.x.e(r10, r11)     // Catch:{ all -> 0x0159 }
        L_0x008d:
            int r7 = r7 + 1
            goto L_0x006a
        L_0x0090:
            r7 = 0
        L_0x0091:
            if (r7 >= r14) goto L_0x00b7
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r8 = r13.i     // Catch:{ all -> 0x0159 }
            java.lang.Object r8 = r8.peek()     // Catch:{ all -> 0x0159 }
            java.lang.Runnable r8 = (java.lang.Runnable) r8     // Catch:{ all -> 0x0159 }
            if (r8 == 0) goto L_0x00b7
            r3.put(r8)     // Catch:{ Throwable -> 0x00a6 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r8 = r13.i     // Catch:{ Throwable -> 0x00a6 }
            r8.poll()     // Catch:{ Throwable -> 0x00a6 }
            goto L_0x00b4
        L_0x00a6:
            r8 = move-exception
            java.lang.String r10 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            java.lang.Object[] r11 = new java.lang.Object[r9]     // Catch:{ all -> 0x0159 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x0159 }
            r11[r0] = r8     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.x.e(r10, r11)     // Catch:{ all -> 0x0159 }
        L_0x00b4:
            int r7 = r7 + 1
            goto L_0x0091
        L_0x00b7:
            monitor-exit(r4)     // Catch:{ all -> 0x0159 }
            r4 = 3
            if (r5 <= 0) goto L_0x00dc
            java.lang.String r7 = "[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r8 = new java.lang.Object[r4]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            r8[r0] = r10
            int r10 = android.os.Process.myPid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r8[r9] = r10
            int r10 = android.os.Process.myTid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r8[r6] = r10
            com.tencent.bugly.proguard.x.c(r7, r8)
        L_0x00dc:
            r7 = 0
        L_0x00dd:
            if (r7 >= r5) goto L_0x012b
            java.lang.Object r8 = r2.poll()
            java.lang.Runnable r8 = (java.lang.Runnable) r8
            if (r8 == 0) goto L_0x012b
            java.lang.Object r10 = r13.j
            monitor-enter(r10)
            int r11 = r13.u     // Catch:{ all -> 0x0128 }
            if (r11 < r6) goto L_0x00f5
            if (r1 == 0) goto L_0x00f5
            r1.a(r8)     // Catch:{ all -> 0x0128 }
            monitor-exit(r10)     // Catch:{ all -> 0x0128 }
            goto L_0x0125
        L_0x00f5:
            monitor-exit(r10)
            java.lang.String r10 = "[UploadManager] Create and start a new thread to execute a upload task: %s"
            java.lang.Object[] r11 = new java.lang.Object[r9]
            java.lang.String r12 = "BUGLY_ASYNC_UPLOAD"
            r11[r0] = r12
            com.tencent.bugly.proguard.x.a(r10, r11)
            com.tencent.bugly.proguard.u$1 r10 = new com.tencent.bugly.proguard.u$1
            r10.<init>(r8)
            java.lang.String r11 = "BUGLY_ASYNC_UPLOAD"
            java.lang.Thread r10 = com.tencent.bugly.proguard.z.a((java.lang.Runnable) r10, (java.lang.String) r11)
            if (r10 == 0) goto L_0x011b
            java.lang.Object r8 = r13.j
            monitor-enter(r8)
            int r10 = r13.u     // Catch:{ all -> 0x0118 }
            int r10 = r10 + r9
            r13.u = r10     // Catch:{ all -> 0x0118 }
            monitor-exit(r8)     // Catch:{ all -> 0x0118 }
            goto L_0x0125
        L_0x0118:
            r14 = move-exception
            monitor-exit(r8)
            throw r14
        L_0x011b:
            java.lang.String r10 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time."
            java.lang.Object[] r11 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.d(r10, r11)
            r13.a((java.lang.Runnable) r8, (boolean) r9)
        L_0x0125:
            int r7 = r7 + 1
            goto L_0x00dd
        L_0x0128:
            r14 = move-exception
            monitor-exit(r10)
            throw r14
        L_0x012b:
            if (r14 <= 0) goto L_0x014e
            java.lang.String r2 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)
            r4[r0] = r5
            int r0 = android.os.Process.myPid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4[r9] = r0
            int r0 = android.os.Process.myTid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4[r6] = r0
            com.tencent.bugly.proguard.x.c(r2, r4)
        L_0x014e:
            if (r1 == 0) goto L_0x0158
            com.tencent.bugly.proguard.u$2 r0 = new com.tencent.bugly.proguard.u$2
            r0.<init>(r13, r14, r3)
            r1.a(r0)
        L_0x0158:
            return
        L_0x0159:
            r14 = move-exception
            monitor-exit(r4)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.c(int):void");
    }

    private boolean a(Runnable runnable, boolean z) {
        if (runnable == null) {
            x.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            x.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.j) {
                if (z) {
                    this.h.put(runnable);
                } else {
                    this.i.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            x.e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(Runnable runnable, long j2) {
        if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        x.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread a2 = z.a(runnable, "BUGLY_SYNC_UPLOAD");
        if (a2 == null) {
            x.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            a(runnable, true);
            return;
        }
        try {
            a2.join(j2);
        } catch (Throwable th) {
            x.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            a(runnable, true);
            c(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a7, code lost:
        if (r14 == false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a9, code lost:
        a((java.lang.Runnable) new com.tencent.bugly.proguard.u.a(r11, r7.d, r12, r15), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b9, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ba, code lost:
        a(r12, r13);
        r0 = new com.tencent.bugly.proguard.u.a(r11, r7.d);
        com.tencent.bugly.proguard.x.a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d5, code lost:
        if (com.tencent.bugly.proguard.z.a((java.lang.Runnable) r0, "BUGLY_ASYNC_UPLOAD") != null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d7, code lost:
        com.tencent.bugly.proguard.x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new java.lang.Object[0]);
        r1 = com.tencent.bugly.proguard.w.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e2, code lost:
        if (r1 == null) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e4, code lost:
        r1.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e7, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e8, code lost:
        com.tencent.bugly.proguard.x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new java.lang.Object[0]);
        r1 = r7.t;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f1, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r7.s = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f4, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f5, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f9, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.Runnable r12, boolean r13, boolean r14, long r15) {
        /*
            r11 = this;
            r7 = r11
            r0 = r12
            r2 = 0
            if (r0 != 0) goto L_0x000c
            java.lang.String r3 = "[UploadManager] Upload task should not be null"
            java.lang.Object[] r4 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.d(r3, r4)
        L_0x000c:
            java.lang.String r3 = "[UploadManager] Add upload task (pid=%d | tid=%d)"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]
            int r6 = android.os.Process.myPid()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r2] = r6
            int r6 = android.os.Process.myTid()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8 = 1
            r5[r8] = r6
            com.tencent.bugly.proguard.x.c(r3, r5)
            java.lang.String r3 = r7.p
            if (r3 == 0) goto L_0x007c
            boolean r3 = r11.b()
            if (r3 == 0) goto L_0x005c
            java.lang.String r3 = "[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            int r5 = android.os.Process.myPid()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r2] = r5
            int r5 = android.os.Process.myTid()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r8] = r5
            com.tencent.bugly.proguard.x.c(r3, r4)
            if (r14 == 0) goto L_0x0055
            r5 = r15
            r11.a((java.lang.Runnable) r12, (long) r5)
            return
        L_0x0055:
            r11.a((java.lang.Runnable) r12, (boolean) r13)
            r11.c((int) r2)
            return
        L_0x005c:
            r5 = r15
            java.lang.String r3 = "[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)"
            java.lang.Object[] r9 = new java.lang.Object[r4]
            int r10 = android.os.Process.myPid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9[r2] = r10
            int r10 = android.os.Process.myTid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9[r8] = r10
            com.tencent.bugly.proguard.x.a(r3, r9)
            r11.b((boolean) r2)
            goto L_0x007d
        L_0x007c:
            r5 = r15
        L_0x007d:
            java.lang.Object r3 = r7.t
            monitor-enter(r3)
            boolean r9 = r7.s     // Catch:{ all -> 0x00fa }
            if (r9 == 0) goto L_0x0089
            r11.a((java.lang.Runnable) r12, (boolean) r13)     // Catch:{ all -> 0x00fa }
            monitor-exit(r3)     // Catch:{ all -> 0x00fa }
            return
        L_0x0089:
            r7.s = r8     // Catch:{ all -> 0x00fa }
            monitor-exit(r3)     // Catch:{ all -> 0x00fa }
            java.lang.String r3 = "[UploadManager] Initialize security context now (pid=%d | tid=%d)"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            int r9 = android.os.Process.myPid()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r4[r2] = r9
            int r9 = android.os.Process.myTid()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r4[r8] = r9
            com.tencent.bugly.proguard.x.c(r3, r4)
            if (r14 == 0) goto L_0x00ba
            com.tencent.bugly.proguard.u$a r8 = new com.tencent.bugly.proguard.u$a
            android.content.Context r3 = r7.d
            r1 = r8
            r2 = r11
            r4 = r12
            r5 = r15
            r1.<init>(r3, r4, r5)
            r0 = 0
            r11.a((java.lang.Runnable) r8, (long) r0)
            return
        L_0x00ba:
            r11.a((java.lang.Runnable) r12, (boolean) r13)
            com.tencent.bugly.proguard.u$a r0 = new com.tencent.bugly.proguard.u$a
            android.content.Context r1 = r7.d
            r0.<init>(r1)
            java.lang.String r1 = "[UploadManager] Create and start a new thread to execute a task of initializing security context: %s"
            java.lang.Object[] r3 = new java.lang.Object[r8]
            java.lang.String r4 = "BUGLY_ASYNC_UPLOAD"
            r3[r2] = r4
            com.tencent.bugly.proguard.x.a(r1, r3)
            java.lang.String r1 = "BUGLY_ASYNC_UPLOAD"
            java.lang.Thread r1 = com.tencent.bugly.proguard.z.a((java.lang.Runnable) r0, (java.lang.String) r1)
            if (r1 != 0) goto L_0x00f9
            java.lang.String r1 = "[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool."
            java.lang.Object[] r3 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.d(r1, r3)
            com.tencent.bugly.proguard.w r1 = com.tencent.bugly.proguard.w.a()
            if (r1 == 0) goto L_0x00e8
            r1.a(r0)
            return
        L_0x00e8:
            java.lang.String r0 = "[UploadManager] Asynchronous thread pool is unavailable now, try next time."
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.e(r0, r1)
            java.lang.Object r1 = r7.t
            monitor-enter(r1)
            r7.s = r2     // Catch:{ all -> 0x00f6 }
            monitor-exit(r1)     // Catch:{ all -> 0x00f6 }
            return
        L_0x00f6:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x00f9:
            return
        L_0x00fa:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(java.lang.Runnable, boolean, boolean, long):void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        	at java.util.ArrayList.get(ArrayList.java:433)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final void a(int r8, com.tencent.bugly.proguard.aq r9) {
        /*
            r7 = this;
            boolean r0 = r7.a
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 2
            r1 = 1
            r2 = 0
            if (r8 != r0) goto L_0x002a
            java.lang.String r8 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)"
            java.lang.Object[] r9 = new java.lang.Object[r0]
            int r0 = android.os.Process.myPid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9[r2] = r0
            int r0 = android.os.Process.myTid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9[r1] = r0
            com.tencent.bugly.proguard.x.c(r8, r9)
            r7.b((boolean) r1)
            goto L_0x0126
        L_0x002a:
            java.lang.Object r8 = r7.t
            monitor-enter(r8)
            boolean r3 = r7.s     // Catch:{ all -> 0x013b }
            if (r3 != 0) goto L_0x0033
            monitor-exit(r8)     // Catch:{ all -> 0x013b }
            return
        L_0x0033:
            monitor-exit(r8)
            if (r9 == 0) goto L_0x0108
            java.lang.String r8 = "[UploadManager] Record security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r0]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            com.tencent.bugly.proguard.x.c(r8, r3)
            java.util.Map<java.lang.String, java.lang.String> r8 = r9.g     // Catch:{ Throwable -> 0x00fe }
            if (r8 == 0) goto L_0x0102
            java.lang.String r3 = "S1"
            boolean r3 = r8.containsKey(r3)     // Catch:{ Throwable -> 0x00fe }
            if (r3 == 0) goto L_0x0102
            java.lang.String r3 = "S2"
            boolean r3 = r8.containsKey(r3)     // Catch:{ Throwable -> 0x00fe }
            if (r3 == 0) goto L_0x0102
            long r3 = r9.e     // Catch:{ Throwable -> 0x00fe }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00fe }
            r9 = 0
            long r3 = r3 - r5
            r7.m = r3     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r9 = "[UploadManager] Time lag of server is: %d"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00fe }
            long r4 = r7.m     // Catch:{ Throwable -> 0x00fe }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x00fe }
            r3[r2] = r4     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.x.c(r9, r3)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r9 = "S1"
            java.lang.Object r9 = r8.get(r9)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x00fe }
            r7.p = r9     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r9 = "[UploadManager] Session ID from server is: %s"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r4 = r7.p     // Catch:{ Throwable -> 0x00fe }
            r3[r2] = r4     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.x.c(r9, r3)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r9 = r7.p     // Catch:{ Throwable -> 0x00fe }
            int r9 = r9.length()     // Catch:{ Throwable -> 0x00fe }
            if (r9 <= 0) goto L_0x00f6
            r3 = 259200000(0xf731400, double:1.280618154E-315)
            java.lang.String r9 = "S2"
            java.lang.Object r8 = r8.get(r9)     // Catch:{ NumberFormatException -> 0x00da }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x00da }
            long r8 = java.lang.Long.parseLong(r8)     // Catch:{ NumberFormatException -> 0x00da }
            r7.q = r8     // Catch:{ NumberFormatException -> 0x00da }
            java.lang.String r8 = "[UploadManager] Session expired time from server is: %d(%s)"
            java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch:{ NumberFormatException -> 0x00da }
            long r5 = r7.q     // Catch:{ NumberFormatException -> 0x00da }
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch:{ NumberFormatException -> 0x00da }
            r9[r2] = r0     // Catch:{ NumberFormatException -> 0x00da }
            java.util.Date r0 = new java.util.Date     // Catch:{ NumberFormatException -> 0x00da }
            long r5 = r7.q     // Catch:{ NumberFormatException -> 0x00da }
            r0.<init>(r5)     // Catch:{ NumberFormatException -> 0x00da }
            java.lang.String r0 = r0.toString()     // Catch:{ NumberFormatException -> 0x00da }
            r9[r1] = r0     // Catch:{ NumberFormatException -> 0x00da }
            com.tencent.bugly.proguard.x.c(r8, r9)     // Catch:{ NumberFormatException -> 0x00da }
            long r8 = r7.q     // Catch:{ NumberFormatException -> 0x00da }
            r5 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e3
            java.lang.String r8 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ NumberFormatException -> 0x00da }
            com.tencent.bugly.proguard.x.d(r8, r9)     // Catch:{ NumberFormatException -> 0x00da }
            r7.q = r3     // Catch:{ NumberFormatException -> 0x00da }
            goto L_0x00e3
        L_0x00da:
            java.lang.String r8 = "[UploadManager] Session expired time is invalid, will set to default value"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.x.d(r8, r9)     // Catch:{ Throwable -> 0x00fe }
            r7.q = r3     // Catch:{ Throwable -> 0x00fe }
        L_0x00e3:
            boolean r8 = r7.d()     // Catch:{ Throwable -> 0x00fe }
            if (r8 == 0) goto L_0x00eb
            r1 = 0
            goto L_0x00f2
        L_0x00eb:
            java.lang.String r8 = "[UploadManager] Failed to record database"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.x.c(r8, r9)     // Catch:{ Throwable -> 0x00fe }
        L_0x00f2:
            r7.c((int) r2)     // Catch:{ Throwable -> 0x00fe }
            goto L_0x0102
        L_0x00f6:
            java.lang.String r8 = "[UploadManager] Session ID from server is invalid, try next time"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.x.c(r8, r9)     // Catch:{ Throwable -> 0x00fe }
            goto L_0x0102
        L_0x00fe:
            r8 = move-exception
            com.tencent.bugly.proguard.x.a(r8)
        L_0x0102:
            if (r1 == 0) goto L_0x0126
            r7.b((boolean) r2)
            goto L_0x0126
        L_0x0108:
            java.lang.String r8 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)"
            java.lang.Object[] r9 = new java.lang.Object[r0]
            int r0 = android.os.Process.myPid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9[r2] = r0
            int r0 = android.os.Process.myTid()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9[r1] = r0
            com.tencent.bugly.proguard.x.c(r8, r9)
            r7.b((boolean) r2)
        L_0x0126:
            java.lang.Object r8 = r7.t
            monitor-enter(r8)
            boolean r9 = r7.s     // Catch:{ all -> 0x0138 }
            if (r9 == 0) goto L_0x0136
            r7.s = r2     // Catch:{ all -> 0x0138 }
            android.content.Context r9 = r7.d     // Catch:{ all -> 0x0138 }
            java.lang.String r0 = "security_info"
            com.tencent.bugly.proguard.z.b((android.content.Context) r9, (java.lang.String) r0)     // Catch:{ all -> 0x0138 }
        L_0x0136:
            monitor-exit(r8)     // Catch:{ all -> 0x0138 }
            return
        L_0x0138:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        L_0x013b:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(int, com.tencent.bugly.proguard.aq):void");
    }

    /* compiled from: BUGLY */
    class a implements Runnable {
        private final Context a;
        private final Runnable b;
        private final long c;

        public a(Context context) {
            this.a = context;
            this.b = null;
            this.c = 0;
        }

        public a(Context context, Runnable runnable, long j) {
            this.a = context;
            this.b = runnable;
            this.c = j;
        }

        public final void run() {
            if (!z.a(this.a, "security_info", (long) c.d)) {
                x.c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", 5000, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                z.b(5000);
                if (z.a((Runnable) this, "BUGLY_ASYNC_UPLOAD") == null) {
                    x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                    w a2 = w.a();
                    if (a2 != null) {
                        a2.a(this);
                    } else {
                        x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                    }
                }
            } else {
                if (!u.this.e()) {
                    x.d("[UploadManager] Failed to load security info from database", new Object[0]);
                    u.this.b(false);
                }
                if (u.this.p != null) {
                    if (u.this.b()) {
                        x.c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.b != null) {
                            u.this.a(this.b, this.c);
                        }
                        u.this.c(0);
                        z.b(this.a, "security_info");
                        synchronized (u.this.t) {
                            boolean unused = u.this.s = false;
                        }
                        return;
                    }
                    x.a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    u.this.b(true);
                }
                byte[] a3 = z.a((int) ShareContent.MINAPP_STYLE);
                if (a3 == null || (a3.length << 3) != 128) {
                    x.d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    u.this.b(false);
                    z.b(this.a, "security_info");
                    synchronized (u.this.t) {
                        boolean unused2 = u.this.s = false;
                    }
                    return;
                }
                byte[] unused3 = u.this.n = a3;
                x.c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.b != null) {
                    u.this.a(this.b, this.c);
                } else {
                    u.this.c(1);
                }
            }
        }
    }

    public final byte[] a(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(1, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final byte[] b(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(2, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        x.c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.p != null) {
            map.put("secureSessionId", this.p);
            return true;
        } else if (this.n == null || (this.n.length << 3) != 128) {
            x.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.l == null) {
                this.l = Base64.decode(this.k, 0);
                if (this.l == null) {
                    x.d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b2 = z.b(1, this.n, this.l);
            if (b2 == null) {
                x.d("[UploadManager] Failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b2, 0);
            if (TextUtils.isEmpty(encodeToString)) {
                x.e("[UploadManager] Failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
