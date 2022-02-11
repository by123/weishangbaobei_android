package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.m;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.proto.c;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: IdTracker */
public class e {
    public static final long a = 86400000;
    public static e b;
    private static Object j = new Object();
    private final String c = "umeng_it.cache";
    private File d;
    private c e = null;
    private long f;
    private long g;
    private Set<a> h = new HashSet();
    private a i = null;

    public String c() {
        return null;
    }

    e(Context context) {
        this.d = new File(context.getFilesDir(), "umeng_it.cache");
        this.g = 86400000;
        this.i = new a(context);
        this.i.b();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e(context);
                b.a((a) new f(context));
                b.a((a) new b(context));
                b.a((a) new r(context));
                b.a((a) new d(context));
                b.a((a) new c(context));
                b.a((a) new g(context));
                b.a((a) new j());
                b.a((a) new s(context));
                q qVar = new q(context);
                if (!TextUtils.isEmpty(qVar.f())) {
                    b.a((a) qVar);
                }
                i iVar = new i(context);
                if (iVar.g()) {
                    b.a((a) iVar);
                    b.a((a) new h(context));
                    iVar.i();
                }
                if (SdkVersion.SDK_TYPE != 1) {
                    b.a((a) new p(context));
                    b.a((a) new m(context));
                    b.a((a) new o(context));
                    b.a((a) new n(context));
                    b.a((a) new l(context));
                    b.a((a) new k(context));
                }
                b.e();
            }
            eVar = b;
        }
        return eVar;
    }

    private boolean a(a aVar) {
        if (this.i.a(aVar.b())) {
            return this.h.add(aVar);
        }
        if (!AnalyticsConstants.UM_DEBUG) {
            return false;
        }
        MLog.w("invalid domain: " + aVar.b());
        return false;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public synchronized void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f >= this.g) {
            boolean z = false;
            for (a next : this.h) {
                if (next.c()) {
                    if (next.a()) {
                        z = true;
                        if (!next.c()) {
                            this.i.b(next.b());
                        }
                    }
                }
            }
            if (z) {
                g();
                this.i.a();
                f();
            }
            this.f = currentTimeMillis;
        }
    }

    public synchronized c b() {
        return this.e;
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private synchronized void g() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.umeng.commonsdk.statistics.proto.c r0 = new com.umeng.commonsdk.statistics.proto.c     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x0061 }
            r1.<init>()     // Catch:{ all -> 0x0061 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0061 }
            r2.<init>()     // Catch:{ all -> 0x0061 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r3 = r7.h     // Catch:{ all -> 0x0061 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0061 }
        L_0x0016:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0061 }
            if (r4 == 0) goto L_0x0052
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0061 }
            com.umeng.commonsdk.statistics.idtracking.a r4 = (com.umeng.commonsdk.statistics.idtracking.a) r4     // Catch:{ all -> 0x0061 }
            boolean r5 = r4.c()     // Catch:{ all -> 0x0061 }
            if (r5 != 0) goto L_0x0029
            goto L_0x0016
        L_0x0029:
            com.umeng.commonsdk.statistics.proto.b r5 = r4.d()     // Catch:{ all -> 0x0061 }
            if (r5 == 0) goto L_0x003a
            java.lang.String r5 = r4.b()     // Catch:{ all -> 0x0061 }
            com.umeng.commonsdk.statistics.proto.b r6 = r4.d()     // Catch:{ all -> 0x0061 }
            r1.put(r5, r6)     // Catch:{ all -> 0x0061 }
        L_0x003a:
            java.util.List r5 = r4.e()     // Catch:{ all -> 0x0061 }
            if (r5 == 0) goto L_0x0016
            java.util.List r5 = r4.e()     // Catch:{ all -> 0x0061 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0061 }
            if (r5 != 0) goto L_0x0016
            java.util.List r4 = r4.e()     // Catch:{ all -> 0x0061 }
            r2.addAll(r4)     // Catch:{ all -> 0x0061 }
            goto L_0x0016
        L_0x0052:
            r0.a((java.util.List<com.umeng.commonsdk.statistics.proto.a>) r2)     // Catch:{ all -> 0x0061 }
            r0.a((java.util.Map<java.lang.String, com.umeng.commonsdk.statistics.proto.b>) r1)     // Catch:{ all -> 0x0061 }
            monitor-enter(r7)     // Catch:{ all -> 0x0061 }
            r7.e = r0     // Catch:{ all -> 0x005e }
            monitor-exit(r7)     // Catch:{ all -> 0x005e }
            monitor-exit(r7)
            return
        L_0x005e:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x005e }
            throw r0     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.g():void");
    }

    public synchronized void d() {
        boolean z = false;
        for (a next : this.h) {
            if (next.c()) {
                if (next.e() != null && !next.e().isEmpty()) {
                    next.a((List<com.umeng.commonsdk.statistics.proto.a>) null);
                    z = true;
                }
            }
        }
        if (z) {
            this.e.b(false);
            f();
        }
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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized void e() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.umeng.commonsdk.statistics.proto.c r0 = r4.h()     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0057 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r2 = r4.h     // Catch:{ all -> 0x0057 }
            int r2 = r2.size()     // Catch:{ all -> 0x0057 }
            r1.<init>(r2)     // Catch:{ all -> 0x0057 }
            monitor-enter(r4)     // Catch:{ all -> 0x0057 }
            r4.e = r0     // Catch:{ all -> 0x0054 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r0 = r4.h     // Catch:{ all -> 0x0054 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0054 }
        L_0x001d:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0054 }
            if (r2 == 0) goto L_0x0038
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0054 }
            com.umeng.commonsdk.statistics.idtracking.a r2 = (com.umeng.commonsdk.statistics.idtracking.a) r2     // Catch:{ all -> 0x0054 }
            com.umeng.commonsdk.statistics.proto.c r3 = r4.e     // Catch:{ all -> 0x0054 }
            r2.a((com.umeng.commonsdk.statistics.proto.c) r3)     // Catch:{ all -> 0x0054 }
            boolean r3 = r2.c()     // Catch:{ all -> 0x0054 }
            if (r3 != 0) goto L_0x001d
            r1.add(r2)     // Catch:{ all -> 0x0054 }
            goto L_0x001d
        L_0x0038:
            java.util.Iterator r0 = r1.iterator()     // Catch:{ all -> 0x0054 }
        L_0x003c:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x004e
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0054 }
            com.umeng.commonsdk.statistics.idtracking.a r1 = (com.umeng.commonsdk.statistics.idtracking.a) r1     // Catch:{ all -> 0x0054 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r2 = r4.h     // Catch:{ all -> 0x0054 }
            r2.remove(r1)     // Catch:{ all -> 0x0054 }
            goto L_0x003c
        L_0x004e:
            monitor-exit(r4)     // Catch:{ all -> 0x0054 }
            r4.g()     // Catch:{ all -> 0x0057 }
            monitor-exit(r4)
            return
        L_0x0054:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0054 }
            throw r0     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.e():void");
    }

    public synchronized void f() {
        if (this.e != null) {
            a(this.e);
        }
    }

    private c h() {
        FileInputStream fileInputStream;
        synchronized (j) {
            if (!this.d.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.d);
                try {
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                    c cVar = new c();
                    new m().a((j) cVar, readStreamToByteArray);
                    HelperUtils.safeClose((InputStream) fileInputStream);
                    return cVar;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        HelperUtils.safeClose((InputStream) fileInputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        HelperUtils.safeClose((InputStream) fileInputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                e.printStackTrace();
                HelperUtils.safeClose((InputStream) fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                HelperUtils.safeClose((InputStream) fileInputStream);
                throw th;
            }
        }
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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private void a(com.umeng.commonsdk.statistics.proto.c r3) {
        /*
            r2 = this;
            java.lang.Object r0 = j
            monitor-enter(r0)
            if (r3 == 0) goto L_0x0021
            monitor-enter(r2)     // Catch:{ Exception -> 0x001d }
            com.umeng.commonsdk.proguard.s r1 = new com.umeng.commonsdk.proguard.s     // Catch:{ all -> 0x0018 }
            r1.<init>()     // Catch:{ all -> 0x0018 }
            byte[] r3 = r1.a(r3)     // Catch:{ all -> 0x0018 }
            monitor-exit(r2)     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x0021
            java.io.File r1 = r2.d     // Catch:{ Exception -> 0x001d }
            com.umeng.commonsdk.statistics.common.HelperUtils.writeFile((java.io.File) r1, (byte[]) r3)     // Catch:{ Exception -> 0x001d }
            goto L_0x0021
        L_0x0018:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0018 }
            throw r3     // Catch:{ Exception -> 0x001d }
        L_0x001b:
            r3 = move-exception
            goto L_0x0023
        L_0x001d:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x001b }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.a(com.umeng.commonsdk.statistics.proto.c):void");
    }

    /* compiled from: IdTracker */
    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public synchronized boolean a(String str) {
            return !this.b.contains(str);
        }

        public synchronized void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public synchronized void a() {
            if (!this.b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String append : this.b) {
                    sb.append(append);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        public synchronized void b() {
            String[] split;
            String string = PreferenceWrapper.getDefault(this.a).getString("invld_id", (String) null);
            if (!TextUtils.isEmpty(string) && (split = string.split(ListUtils.DEFAULT_JOIN_SEPARATOR)) != null) {
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.b.add(str);
                    }
                }
            }
        }
    }
}
