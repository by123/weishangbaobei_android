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

    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public void a() {
            synchronized (this) {
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
        }

        public boolean a(String str) {
            boolean contains;
            synchronized (this) {
                contains = this.b.contains(str);
            }
            return !contains;
        }

        public void b() {
            String[] split;
            synchronized (this) {
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

        public void b(String str) {
            synchronized (this) {
                this.b.add(str);
            }
        }

        public void c(String str) {
            this.b.remove(str);
        }
    }

    e(Context context) {
        this.d = new File(context.getFilesDir(), "umeng_it.cache");
        this.g = 86400000;
        this.i = new a(context);
        this.i.b();
    }

    public static e a(Context context) {
        e eVar;
        synchronized (e.class) {
            try {
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
            } catch (Throwable th) {
                Class<e> cls = e.class;
                throw th;
            }
        }
        return eVar;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Unknown Source)
        	at java.base/java.util.Objects.checkIndex(Unknown Source)
        	at java.base/java.util.ArrayList.get(Unknown Source)
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
    private void a(com.umeng.commonsdk.statistics.proto.c r4) {
        /*
            r3 = this;
            java.lang.Object r1 = j
            monitor-enter(r1)
            if (r4 == 0) goto L_0x0017
            monitor-enter(r3)     // Catch:{ Exception -> 0x001c }
            com.umeng.commonsdk.proguard.s r0 = new com.umeng.commonsdk.proguard.s     // Catch:{ all -> 0x0019 }
            r0.<init>()     // Catch:{ all -> 0x0019 }
            byte[] r0 = r0.a(r4)     // Catch:{ all -> 0x0019 }
            monitor-exit(r3)     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0017
            java.io.File r2 = r3.d     // Catch:{ Exception -> 0x001c }
            com.umeng.commonsdk.statistics.common.HelperUtils.writeFile((java.io.File) r2, (byte[]) r0)     // Catch:{ Exception -> 0x001c }
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            return
        L_0x0019:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0019 }
            throw r0     // Catch:{ Exception -> 0x001c }
        L_0x001c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x0017
        L_0x0021:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.a(com.umeng.commonsdk.statistics.proto.c):void");
    }

    private boolean a(a aVar) {
        if (this.i.a(aVar.b())) {
            return this.h.add(aVar);
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.w("invalid domain: " + aVar.b());
        }
        return false;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Unknown Source)
        	at java.base/java.util.Objects.checkIndex(Unknown Source)
        	at java.base/java.util.ArrayList.get(Unknown Source)
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
    private void g() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.umeng.commonsdk.statistics.proto.c r1 = new com.umeng.commonsdk.statistics.proto.c     // Catch:{ all -> 0x0051 }
            r1.<init>()     // Catch:{ all -> 0x0051 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x0051 }
            r2.<init>()     // Catch:{ all -> 0x0051 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0051 }
            r3.<init>()     // Catch:{ all -> 0x0051 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r0 = r7.h     // Catch:{ all -> 0x0051 }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x0051 }
        L_0x0016:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x0051 }
            if (r0 == 0) goto L_0x0054
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x0051 }
            com.umeng.commonsdk.statistics.idtracking.a r0 = (com.umeng.commonsdk.statistics.idtracking.a) r0     // Catch:{ all -> 0x0051 }
            boolean r5 = r0.c()     // Catch:{ all -> 0x0051 }
            if (r5 == 0) goto L_0x0016
            com.umeng.commonsdk.statistics.proto.b r5 = r0.d()     // Catch:{ all -> 0x0051 }
            if (r5 == 0) goto L_0x0039
            java.lang.String r5 = r0.b()     // Catch:{ all -> 0x0051 }
            com.umeng.commonsdk.statistics.proto.b r6 = r0.d()     // Catch:{ all -> 0x0051 }
            r2.put(r5, r6)     // Catch:{ all -> 0x0051 }
        L_0x0039:
            java.util.List r5 = r0.e()     // Catch:{ all -> 0x0051 }
            if (r5 == 0) goto L_0x0016
            java.util.List r5 = r0.e()     // Catch:{ all -> 0x0051 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0051 }
            if (r5 != 0) goto L_0x0016
            java.util.List r0 = r0.e()     // Catch:{ all -> 0x0051 }
            r3.addAll(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0016
        L_0x0051:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x0054:
            r1.a((java.util.List<com.umeng.commonsdk.statistics.proto.a>) r3)     // Catch:{ all -> 0x0051 }
            r1.a((java.util.Map<java.lang.String, com.umeng.commonsdk.statistics.proto.b>) r2)     // Catch:{ all -> 0x0051 }
            monitor-enter(r7)     // Catch:{ all -> 0x0051 }
            r7.e = r1     // Catch:{ all -> 0x0060 }
            monitor-exit(r7)     // Catch:{ all -> 0x0060 }
            monitor-exit(r7)
            return
        L_0x0060:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0060 }
            throw r0     // Catch:{ all -> 0x0051 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.g():void");
    }

    private c h() {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
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
                        fileInputStream2 = fileInputStream;
                        HelperUtils.safeClose((InputStream) fileInputStream2);
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
                HelperUtils.safeClose((InputStream) fileInputStream2);
                throw th;
            }
        }
    }

    public void a() {
        synchronized (this) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f >= this.g) {
                boolean z = false;
                for (a next : this.h) {
                    if (next.c() && next.a()) {
                        z = true;
                        if (!next.c()) {
                            this.i.b(next.b());
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
    }

    public void a(long j2) {
        this.g = j2;
    }

    public c b() {
        c cVar;
        synchronized (this) {
            cVar = this.e;
        }
        return cVar;
    }

    public String c() {
        return null;
    }

    public void d() {
        synchronized (this) {
            boolean z = false;
            for (a next : this.h) {
                if (next.c() && next.e() != null && !next.e().isEmpty()) {
                    next.a((List<com.umeng.commonsdk.statistics.proto.a>) null);
                    z = true;
                }
            }
            if (z) {
                this.e.b(false);
                f();
            }
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Unknown Source)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Unknown Source)
        	at java.base/java.util.Objects.checkIndex(Unknown Source)
        	at java.base/java.util.ArrayList.get(Unknown Source)
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
    public void e() {
        /*
            r4 = this;
            monitor-enter(r4)
            com.umeng.commonsdk.statistics.proto.c r0 = r4.h()     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)
        L_0x0008:
            return
        L_0x0009:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x003b }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r2 = r4.h     // Catch:{ all -> 0x003b }
            int r2 = r2.size()     // Catch:{ all -> 0x003b }
            r1.<init>(r2)     // Catch:{ all -> 0x003b }
            monitor-enter(r4)     // Catch:{ all -> 0x003b }
            r4.e = r0     // Catch:{ all -> 0x0038 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r0 = r4.h     // Catch:{ all -> 0x0038 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0038 }
        L_0x001d:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x003e
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0038 }
            com.umeng.commonsdk.statistics.idtracking.a r0 = (com.umeng.commonsdk.statistics.idtracking.a) r0     // Catch:{ all -> 0x0038 }
            com.umeng.commonsdk.statistics.proto.c r3 = r4.e     // Catch:{ all -> 0x0038 }
            r0.a((com.umeng.commonsdk.statistics.proto.c) r3)     // Catch:{ all -> 0x0038 }
            boolean r3 = r0.c()     // Catch:{ all -> 0x0038 }
            if (r3 != 0) goto L_0x001d
            r1.add(r0)     // Catch:{ all -> 0x0038 }
            goto L_0x001d
        L_0x0038:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0038 }
            throw r0     // Catch:{ all -> 0x003b }
        L_0x003b:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x003e:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0038 }
        L_0x0042:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0054
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x0038 }
            com.umeng.commonsdk.statistics.idtracking.a r0 = (com.umeng.commonsdk.statistics.idtracking.a) r0     // Catch:{ all -> 0x0038 }
            java.util.Set<com.umeng.commonsdk.statistics.idtracking.a> r2 = r4.h     // Catch:{ all -> 0x0038 }
            r2.remove(r0)     // Catch:{ all -> 0x0038 }
            goto L_0x0042
        L_0x0054:
            monitor-exit(r4)     // Catch:{ all -> 0x0038 }
            r4.g()     // Catch:{ all -> 0x003b }
            monitor-exit(r4)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.e.e():void");
    }

    public void f() {
        synchronized (this) {
            if (this.e != null) {
                a(this.e);
            }
        }
    }
}
