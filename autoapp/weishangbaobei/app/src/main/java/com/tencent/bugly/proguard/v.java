package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i2, i3, bArr, str, str2, tVar, z, 2, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH, z2, (Map<String, String>) null);
    }

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, int i4, int i5, boolean z2, Map<String, String> map) {
        int i6 = i4;
        int i7 = i5;
        this.a = 2;
        this.b = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        this.m = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i2;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        u uVar = this.i;
        this.l = null;
        this.s = z;
        this.d = i3;
        if (i6 > 0) {
            this.a = i6;
        }
        if (i7 > 0) {
            this.b = i7;
        }
        this.t = z2;
        this.o = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.tencent.bugly.proguard.aq r5, boolean r6, int r7, java.lang.String r8, int r9) {
        /*
            r4 = this;
            int r5 = r4.d
            r0 = 630(0x276, float:8.83E-43)
            if (r5 == r0) goto L_0x001c
            r0 = 640(0x280, float:8.97E-43)
            if (r5 == r0) goto L_0x0019
            r0 = 830(0x33e, float:1.163E-42)
            if (r5 == r0) goto L_0x001c
            r0 = 840(0x348, float:1.177E-42)
            if (r5 == r0) goto L_0x0019
            int r5 = r4.d
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L_0x001e
        L_0x0019:
            java.lang.String r5 = "userinfo"
            goto L_0x001e
        L_0x001c:
            java.lang.String r5 = "crash"
        L_0x001e:
            r0 = 0
            r1 = 1
            if (r6 == 0) goto L_0x002c
            java.lang.String r7 = "[Upload] Success: %s"
            java.lang.Object[] r8 = new java.lang.Object[r1]
            r8[r0] = r5
            com.tencent.bugly.proguard.x.a(r7, r8)
            goto L_0x0049
        L_0x002c:
            java.lang.String r2 = "[Upload] Failed to upload(%d) %s: %s"
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3[r0] = r7
            r3[r1] = r5
            r5 = 2
            r3[r5] = r8
            com.tencent.bugly.proguard.x.e(r2, r3)
            boolean r5 = r4.s
            if (r5 == 0) goto L_0x0049
            com.tencent.bugly.proguard.u r5 = r4.i
            r7 = 0
            r5.a((int) r9, (com.tencent.bugly.proguard.aq) r7)
        L_0x0049:
            long r7 = r4.q
            long r0 = r4.r
            long r7 = r7 + r0
            r0 = 0
            int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r5 <= 0) goto L_0x0069
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r7 = r4.t
            long r7 = r5.a((boolean) r7)
            long r0 = r4.q
            long r7 = r7 + r0
            long r0 = r4.r
            long r7 = r7 + r0
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r9 = r4.t
            r5.a((long) r7, (boolean) r9)
        L_0x0069:
            com.tencent.bugly.proguard.t r5 = r4.k
            if (r5 == 0) goto L_0x0078
            com.tencent.bugly.proguard.t r5 = r4.k
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L_0x0078:
            com.tencent.bugly.proguard.t r5 = r4.l
            if (r5 == 0) goto L_0x0087
            com.tencent.bugly.proguard.t r5 = r4.l
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L_0x0087:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.a(com.tencent.bugly.proguard.aq, boolean, int, java.lang.String, int):void");
    }

    private static boolean a(aq aqVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (aqVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        } else if (aqVar.a != 0) {
            x.e("resp result error %d", Byte.valueOf(aqVar.a));
            return false;
        } else {
            try {
                if (!z.a(aqVar.d) && !a.b().i().equals(aqVar.d)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "gateway", aqVar.d.getBytes("UTF-8"), (o) null, true);
                    aVar.d(aqVar.d);
                }
                if (!z.a(aqVar.f) && !a.b().j().equals(aqVar.f)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "device", aqVar.f.getBytes("UTF-8"), (o) null, true);
                    aVar.e(aqVar.f);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            aVar.j = aqVar.e;
            if (aqVar.b == 510) {
                if (aqVar.c == null) {
                    x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(aqVar.b));
                    return false;
                }
                as asVar = (as) a.a(aqVar.c, as.class);
                if (asVar == null) {
                    x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(aqVar.b));
                    return false;
                }
                aVar2.a(asVar);
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02ba, code lost:
        r7.i.a(r13, (com.tencent.bugly.proguard.aq) null);
        com.tencent.bugly.proguard.x.a("[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
        r7.i.a(r7.j, r7.d, r7.e, r7.m, r7.n, r7.k, r7.a, r7.b, true, r7.o);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02f8, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02f9, code lost:
        a((com.tencent.bugly.proguard.aq) null, false, 1, "status of server is " + r13, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0310, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0311, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0293, code lost:
        if (r13 == 0) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0295, code lost:
        if (r13 != 2) goto L_0x02f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02a1, code lost:
        if ((r7.q + r7.r) <= 0) goto L_0x02ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02a3, code lost:
        r7.i.a((r7.i.a(r7.t) + r7.q) + r7.r, r7.t);
     */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01fe A[Catch:{ Throwable -> 0x0313, Throwable -> 0x042b }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0266  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r19 = this;
            r7 = r19
            r0 = 0
            r7.p = r0     // Catch:{ Throwable -> 0x042b }
            r1 = 0
            r7.q = r1     // Catch:{ Throwable -> 0x042b }
            r7.r = r1     // Catch:{ Throwable -> 0x042b }
            byte[] r3 = r7.e     // Catch:{ Throwable -> 0x042b }
            android.content.Context r4 = r7.c     // Catch:{ Throwable -> 0x042b }
            java.lang.String r4 = com.tencent.bugly.crashreport.common.info.b.c(r4)     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x0021
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "network is not available"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x0021:
            if (r3 == 0) goto L_0x041f
            int r4 = r3.length     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x0028
            goto L_0x041f
        L_0x0028:
            java.lang.String r4 = "[Upload] Run upload task with cmd: %d"
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            int r8 = r7.d     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x042b }
            r6[r0] = r8     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ Throwable -> 0x042b }
            android.content.Context r4 = r7.c     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x0413
            com.tencent.bugly.crashreport.common.info.a r4 = r7.f     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x0413
            com.tencent.bugly.crashreport.common.strategy.a r4 = r7.g     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x0413
            com.tencent.bugly.proguard.s r4 = r7.h     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x004a
            goto L_0x0413
        L_0x004a:
            com.tencent.bugly.crashreport.common.strategy.a r4 = r7.g     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r4 = r4.c()     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x005e
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "illegal local strategy"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x005e:
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ Throwable -> 0x042b }
            r6.<init>()     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "prodId"
            com.tencent.bugly.crashreport.common.info.a r9 = r7.f     // Catch:{ Throwable -> 0x042b }
            java.lang.String r9 = r9.f()     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r9)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "bundleId"
            com.tencent.bugly.crashreport.common.info.a r9 = r7.f     // Catch:{ Throwable -> 0x042b }
            java.lang.String r9 = r9.c     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r9)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "appVer"
            com.tencent.bugly.crashreport.common.info.a r9 = r7.f     // Catch:{ Throwable -> 0x042b }
            java.lang.String r9 = r9.k     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r9)     // Catch:{ Throwable -> 0x042b }
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.o     // Catch:{ Throwable -> 0x042b }
            if (r8 == 0) goto L_0x0089
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.o     // Catch:{ Throwable -> 0x042b }
            r6.putAll(r8)     // Catch:{ Throwable -> 0x042b }
        L_0x0089:
            boolean r8 = r7.s     // Catch:{ Throwable -> 0x042b }
            r9 = 2
            if (r8 == 0) goto L_0x00f0
            java.lang.String r8 = "cmd"
            int r10 = r7.d     // Catch:{ Throwable -> 0x042b }
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r10)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "platformId"
            java.lang.String r10 = java.lang.Byte.toString(r5)     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r10)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.a r10 = r7.f     // Catch:{ Throwable -> 0x042b }
            java.lang.String r10 = r10.f     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r10)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r8 = "strategylastUpdateTime"
            long r10 = r4.p     // Catch:{ Throwable -> 0x042b }
            java.lang.String r4 = java.lang.Long.toString(r10)     // Catch:{ Throwable -> 0x042b }
            r6.put(r8, r4)     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x042b }
            boolean r4 = r4.a((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x00ca
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to add security info to HTTP headers"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x00ca:
            byte[] r3 = com.tencent.bugly.proguard.z.a((byte[]) r3, (int) r9)     // Catch:{ Throwable -> 0x042b }
            if (r3 != 0) goto L_0x00dc
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to zip request body"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x00dc:
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x042b }
            byte[] r3 = r4.a((byte[]) r3)     // Catch:{ Throwable -> 0x042b }
            if (r3 != 0) goto L_0x00f0
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to encrypt request body"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x00f0:
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x042b }
            int r8 = r7.j     // Catch:{ Throwable -> 0x042b }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x042b }
            r4.a((int) r8, (long) r10)     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.t r4 = r7.k     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x0103
            com.tencent.bugly.proguard.t r4 = r7.k     // Catch:{ Throwable -> 0x042b }
            int r4 = r7.d     // Catch:{ Throwable -> 0x042b }
        L_0x0103:
            com.tencent.bugly.proguard.t r4 = r7.l     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x010b
            com.tencent.bugly.proguard.t r4 = r7.l     // Catch:{ Throwable -> 0x042b }
            int r4 = r7.d     // Catch:{ Throwable -> 0x042b }
        L_0x010b:
            java.lang.String r4 = r7.m     // Catch:{ Throwable -> 0x042b }
            r8 = -1
            r10 = r4
            r4 = 0
            r8 = 0
            r11 = -1
        L_0x0112:
            int r12 = r4 + 1
            int r13 = r7.a     // Catch:{ Throwable -> 0x042b }
            if (r4 >= r13) goto L_0x0407
            if (r12 <= r5) goto L_0x013e
            java.lang.String r4 = "[Upload] Failed to upload last time, wait and try(%d) again."
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x042b }
            r8[r0] = r13     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.d(r4, r8)     // Catch:{ Throwable -> 0x042b }
            int r4 = r7.b     // Catch:{ Throwable -> 0x042b }
            long r13 = (long) r4     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.z.b((long) r13)     // Catch:{ Throwable -> 0x042b }
            int r4 = r7.a     // Catch:{ Throwable -> 0x042b }
            if (r12 != r4) goto L_0x013e
            java.lang.String r4 = "[Upload] Use the back-up url at the last time: %s"
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            java.lang.String r10 = r7.n     // Catch:{ Throwable -> 0x042b }
            r8[r0] = r10     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.d(r4, r8)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r10 = r7.n     // Catch:{ Throwable -> 0x042b }
        L_0x013e:
            java.lang.String r4 = "[Upload] Send %d bytes"
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            int r13 = r3.length     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x042b }
            r8[r0] = r13     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r4, r8)     // Catch:{ Throwable -> 0x042b }
            boolean r4 = r7.s     // Catch:{ Throwable -> 0x042b }
            if (r4 == 0) goto L_0x0155
            java.lang.String r4 = a((java.lang.String) r10)     // Catch:{ Throwable -> 0x042b }
            r10 = r4
        L_0x0155:
            java.lang.String r4 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
            r8 = 4
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x042b }
            r8[r0] = r10     // Catch:{ Throwable -> 0x042b }
            int r13 = r7.d     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x042b }
            r8[r5] = r13     // Catch:{ Throwable -> 0x042b }
            int r13 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x042b }
            r8[r9] = r13     // Catch:{ Throwable -> 0x042b }
            int r13 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x042b }
            r14 = 3
            r8[r14] = r13     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r4, r8)     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.s r4 = r7.h     // Catch:{ Throwable -> 0x042b }
            byte[] r4 = r4.a((java.lang.String) r10, (byte[]) r3, (com.tencent.bugly.proguard.v) r7, (java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x0199
            java.lang.String r4 = "Failed to upload for no response!"
            java.lang.String r8 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r13 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x042b }
            r13[r0] = r14     // Catch:{ Throwable -> 0x042b }
            r13[r5] = r4     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.e(r8, r13)     // Catch:{ Throwable -> 0x042b }
            r4 = r12
        L_0x0196:
            r8 = 1
            goto L_0x0112
        L_0x0199:
            com.tencent.bugly.proguard.s r8 = r7.h     // Catch:{ Throwable -> 0x042b }
            java.util.Map<java.lang.String, java.lang.String> r8 = r8.a     // Catch:{ Throwable -> 0x042b }
            boolean r13 = r7.s     // Catch:{ Throwable -> 0x042b }
            if (r13 == 0) goto L_0x033e
            if (r8 == 0) goto L_0x01f4
            int r13 = r8.size()     // Catch:{ Throwable -> 0x042b }
            if (r13 != 0) goto L_0x01aa
            goto L_0x01f4
        L_0x01aa:
            java.lang.String r13 = "status"
            boolean r13 = r8.containsKey(r13)     // Catch:{ Throwable -> 0x042b }
            if (r13 != 0) goto L_0x01be
            java.lang.String r13 = "[Upload] Headers does not contain %s"
            java.lang.Object[] r15 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            java.lang.String r16 = "status"
            r15[r0] = r16     // Catch:{ Throwable -> 0x042b }
        L_0x01ba:
            com.tencent.bugly.proguard.x.d(r13, r15)     // Catch:{ Throwable -> 0x042b }
            goto L_0x01fb
        L_0x01be:
            java.lang.String r13 = "Bugly-Version"
            boolean r13 = r8.containsKey(r13)     // Catch:{ Throwable -> 0x042b }
            if (r13 != 0) goto L_0x01cf
            java.lang.String r13 = "[Upload] Headers does not contain %s"
            java.lang.Object[] r15 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            java.lang.String r16 = "Bugly-Version"
            r15[r0] = r16     // Catch:{ Throwable -> 0x042b }
            goto L_0x01ba
        L_0x01cf:
            java.lang.String r13 = "Bugly-Version"
            java.lang.Object r13 = r8.get(r13)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x042b }
            java.lang.String r15 = "bugly"
            boolean r15 = r13.contains(r15)     // Catch:{ Throwable -> 0x042b }
            if (r15 != 0) goto L_0x01e9
            java.lang.String r15 = "[Upload] Bugly version is not valid: %s"
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            r1[r0] = r13     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.d(r15, r1)     // Catch:{ Throwable -> 0x042b }
            goto L_0x01fb
        L_0x01e9:
            java.lang.String r1 = "[Upload] Bugly version from headers is: %s"
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            r2[r0] = r13     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x042b }
            r1 = 1
            goto L_0x01fc
        L_0x01f4:
            java.lang.String r1 = "[Upload] Headers is empty."
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.d(r1, r2)     // Catch:{ Throwable -> 0x042b }
        L_0x01fb:
            r1 = 0
        L_0x01fc:
            if (r1 != 0) goto L_0x0266
            java.lang.String r1 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            int r4 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x042b }
            r2[r0] = r4     // Catch:{ Throwable -> 0x042b }
            int r4 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x042b }
            r2[r5] = r4     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r1 = "[Upload] Failed to upload for no status header."
            java.lang.String r2 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r4 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x042b }
            r4[r0] = r13     // Catch:{ Throwable -> 0x042b }
            r4[r5] = r1     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ Throwable -> 0x042b }
            if (r8 == 0) goto L_0x025a
            java.util.Set r1 = r8.entrySet()     // Catch:{ Throwable -> 0x042b }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x042b }
        L_0x0234:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x042b }
            if (r2 == 0) goto L_0x025a
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x042b }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Throwable -> 0x042b }
            java.lang.String r4 = "[key]: %s, [value]: %s"
            java.lang.Object[] r8 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            java.lang.Object r13 = r2.getKey()     // Catch:{ Throwable -> 0x042b }
            r8[r0] = r13     // Catch:{ Throwable -> 0x042b }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Throwable -> 0x042b }
            r8[r5] = r2     // Catch:{ Throwable -> 0x042b }
            java.lang.String r2 = java.lang.String.format(r4, r8)     // Catch:{ Throwable -> 0x042b }
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r2, r4)     // Catch:{ Throwable -> 0x042b }
            goto L_0x0234
        L_0x025a:
            java.lang.String r1 = "[Upload] Failed to upload for no status header."
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x042b }
            r4 = r12
            r1 = 0
            goto L_0x0196
        L_0x0266:
            java.lang.String r1 = "status"
            java.lang.Object r1 = r8.get(r1)     // Catch:{ Throwable -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0317 }
            int r13 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0317 }
            java.lang.String r1 = "[Upload] Status from server is %d (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r14]     // Catch:{ Throwable -> 0x0313 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0313 }
            r2[r0] = r11     // Catch:{ Throwable -> 0x0313 }
            int r11 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0313 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x0313 }
            r2[r5] = r11     // Catch:{ Throwable -> 0x0313 }
            int r11 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0313 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x0313 }
            r2[r9] = r11     // Catch:{ Throwable -> 0x0313 }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x0313 }
            if (r13 == 0) goto L_0x0311
            if (r13 != r9) goto L_0x02f9
            long r1 = r7.q     // Catch:{ Throwable -> 0x042b }
            long r3 = r7.r     // Catch:{ Throwable -> 0x042b }
            r6 = 0
            long r1 = r1 + r3
            r14 = 0
            int r3 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r3 <= 0) goto L_0x02ba
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x042b }
            boolean r2 = r7.t     // Catch:{ Throwable -> 0x042b }
            long r1 = r1.a((boolean) r2)     // Catch:{ Throwable -> 0x042b }
            long r3 = r7.q     // Catch:{ Throwable -> 0x042b }
            r6 = 0
            long r1 = r1 + r3
            long r3 = r7.r     // Catch:{ Throwable -> 0x042b }
            r6 = 0
            long r1 = r1 + r3
            com.tencent.bugly.proguard.u r3 = r7.i     // Catch:{ Throwable -> 0x042b }
            boolean r4 = r7.t     // Catch:{ Throwable -> 0x042b }
            r3.a((long) r1, (boolean) r4)     // Catch:{ Throwable -> 0x042b }
        L_0x02ba:
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x042b }
            r2 = 0
            r1.a((int) r13, (com.tencent.bugly.proguard.aq) r2)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r1 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            int r3 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x042b }
            r2[r0] = r3     // Catch:{ Throwable -> 0x042b }
            int r0 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x042b }
            r2[r5] = r0     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.a(r1, r2)     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.u r8 = r7.i     // Catch:{ Throwable -> 0x042b }
            int r9 = r7.j     // Catch:{ Throwable -> 0x042b }
            int r10 = r7.d     // Catch:{ Throwable -> 0x042b }
            byte[] r11 = r7.e     // Catch:{ Throwable -> 0x042b }
            java.lang.String r12 = r7.m     // Catch:{ Throwable -> 0x042b }
            java.lang.String r13 = r7.n     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.t r14 = r7.k     // Catch:{ Throwable -> 0x042b }
            int r15 = r7.a     // Catch:{ Throwable -> 0x042b }
            int r0 = r7.b     // Catch:{ Throwable -> 0x042b }
            r17 = 1
            java.util.Map<java.lang.String, java.lang.String> r1 = r7.o     // Catch:{ Throwable -> 0x042b }
            r16 = r0
            r18 = r1
            r8.a(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x02f9:
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x042b }
            java.lang.String r1 = "status of server is "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x042b }
            r0.append(r13)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x042b }
            r1 = r19
            r6 = r13
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x0311:
            r11 = r13
            goto L_0x033e
        L_0x0313:
            r14 = 0
            r11 = r13
            goto L_0x0319
        L_0x0317:
            r14 = 0
        L_0x0319:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x042b }
            java.lang.String r2 = "[Upload] Failed to upload for format of status header is invalid: "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r2 = java.lang.Integer.toString(r11)     // Catch:{ Throwable -> 0x042b }
            r1.append(r2)     // Catch:{ Throwable -> 0x042b }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x042b }
            java.lang.String r2 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r4 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x042b }
            r4[r0] = r8     // Catch:{ Throwable -> 0x042b }
            r4[r5] = r1     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ Throwable -> 0x042b }
            r4 = r12
            r1 = r14
            goto L_0x0196
        L_0x033e:
            java.lang.String r1 = "[Upload] Received %d bytes"
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x042b }
            int r3 = r4.length     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x042b }
            r2[r0] = r3     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x042b }
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x042b }
            if (r1 == 0) goto L_0x03ad
            int r1 = r4.length     // Catch:{ Throwable -> 0x042b }
            if (r1 != 0) goto L_0x0387
            java.util.Set r1 = r8.entrySet()     // Catch:{ Throwable -> 0x042b }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x042b }
        L_0x035b:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x042b }
            if (r2 == 0) goto L_0x037b
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x042b }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Throwable -> 0x042b }
            java.lang.String r3 = "[Upload] HTTP headers from server: key = %s, value = %s"
            java.lang.Object[] r4 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            java.lang.Object r6 = r2.getKey()     // Catch:{ Throwable -> 0x042b }
            r4[r0] = r6     // Catch:{ Throwable -> 0x042b }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Throwable -> 0x042b }
            r4[r5] = r2     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r3, r4)     // Catch:{ Throwable -> 0x042b }
            goto L_0x035b
        L_0x037b:
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "response data from server is empty"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x0387:
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x042b }
            byte[] r1 = r1.b((byte[]) r4)     // Catch:{ Throwable -> 0x042b }
            if (r1 != 0) goto L_0x039b
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed to decrypt response from server"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x039b:
            byte[] r4 = com.tencent.bugly.proguard.z.b((byte[]) r1, (int) r9)     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x03ad
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed unzip(Gzip) response from server"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x03ad:
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.aq r2 = com.tencent.bugly.proguard.a.a((byte[]) r4, (boolean) r1)     // Catch:{ Throwable -> 0x042b }
            if (r2 != 0) goto L_0x03c1
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed to decode response package"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x03c1:
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x042b }
            if (r1 == 0) goto L_0x03ca
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x042b }
            r1.a((int) r11, (com.tencent.bugly.proguard.aq) r2)     // Catch:{ Throwable -> 0x042b }
        L_0x03ca:
            java.lang.String r1 = "[Upload] Response cmd is: %d, length of sBuffer is: %d"
            java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x042b }
            int r4 = r2.b     // Catch:{ Throwable -> 0x042b }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x042b }
            r3[r0] = r4     // Catch:{ Throwable -> 0x042b }
            byte[] r4 = r2.c     // Catch:{ Throwable -> 0x042b }
            if (r4 != 0) goto L_0x03db
            goto L_0x03de
        L_0x03db:
            byte[] r0 = r2.c     // Catch:{ Throwable -> 0x042b }
            int r0 = r0.length     // Catch:{ Throwable -> 0x042b }
        L_0x03de:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x042b }
            r3[r5] = r0     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.proguard.x.c(r1, r3)     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.crashreport.common.info.a r0 = r7.f     // Catch:{ Throwable -> 0x042b }
            com.tencent.bugly.crashreport.common.strategy.a r1 = r7.g     // Catch:{ Throwable -> 0x042b }
            boolean r0 = a(r2, r0, r1)     // Catch:{ Throwable -> 0x042b }
            if (r0 != 0) goto L_0x03fc
            r3 = 0
            r4 = 2
            java.lang.String r5 = "failed to process response package"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x03fc:
            r3 = 1
            r4 = 2
            java.lang.String r5 = "successfully uploaded"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x0407:
            r2 = 0
            r3 = 0
            java.lang.String r5 = "failed after many attempts"
            r6 = 0
            r1 = r19
            r4 = r8
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x0413:
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "illegal access error"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x041f:
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "request package is empty!"
            r6 = 0
            r1 = r19
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x042b }
            return
        L_0x042b:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0435
            r0.printStackTrace()
        L_0x0435:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j2) {
        this.p++;
        this.q += j2;
    }

    public final void b(long j2) {
        this.r += j2;
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }
}
