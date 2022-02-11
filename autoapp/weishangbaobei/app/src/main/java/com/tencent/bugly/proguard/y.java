package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/* compiled from: BUGLY */
public final class y {
    public static boolean a = true;
    private static boolean b = true;
    private static SimpleDateFormat c = null;
    private static int d = 30720;
    private static StringBuilder e;
    private static StringBuilder f;
    private static boolean g;
    private static a h;
    private static String i;
    private static String j;
    private static Context k;
    private static String l;
    private static boolean m;
    private static boolean n;
    private static ExecutorService o;
    private static int p;
    private static final Object q = new Object();

    static {
        try {
            c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            x.b(th.getCause());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0071, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r3) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.y> r0 = com.tencent.bugly.proguard.y.class
            monitor-enter(r0)
            boolean r1 = m     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x0070
            if (r3 == 0) goto L_0x0070
            boolean r1 = a     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x000e
            goto L_0x0070
        L_0x000e:
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()     // Catch:{ Throwable -> 0x006b }
            o = r1     // Catch:{ Throwable -> 0x006b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006b }
            r2 = 0
            r1.<init>(r2)     // Catch:{ Throwable -> 0x006b }
            f = r1     // Catch:{ Throwable -> 0x006b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006b }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x006b }
            e = r1     // Catch:{ Throwable -> 0x006b }
            k = r3     // Catch:{ Throwable -> 0x006b }
            com.tencent.bugly.crashreport.common.info.a r3 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r3)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = r3.d     // Catch:{ Throwable -> 0x006b }
            i = r1     // Catch:{ Throwable -> 0x006b }
            r3.getClass()     // Catch:{ Throwable -> 0x006b }
            java.lang.String r3 = ""
            j = r3     // Catch:{ Throwable -> 0x006b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006b }
            r3.<init>()     // Catch:{ Throwable -> 0x006b }
            android.content.Context r1 = k     // Catch:{ Throwable -> 0x006b }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = r1.getPath()     // Catch:{ Throwable -> 0x006b }
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = "/buglylog_"
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = i     // Catch:{ Throwable -> 0x006b }
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = "_"
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = j     // Catch:{ Throwable -> 0x006b }
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r1 = ".txt"
            r3.append(r1)     // Catch:{ Throwable -> 0x006b }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x006b }
            l = r3     // Catch:{ Throwable -> 0x006b }
            int r3 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x006b }
            p = r3     // Catch:{ Throwable -> 0x006b }
        L_0x006b:
            r3 = 1
            m = r3     // Catch:{ all -> 0x0072 }
            monitor-exit(r0)
            return
        L_0x0070:
            monitor-exit(r0)
            return
        L_0x0072:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.y.a(android.content.Context):void");
    }

    public static void a(int i2) {
        synchronized (q) {
            d = i2;
            if (i2 < 0) {
                d = 0;
            } else if (i2 > 30720) {
                d = 30720;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            a(str, str2, message + 10 + z.b(th));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(final java.lang.String r3, final java.lang.String r4, final java.lang.String r5) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.y> r0 = com.tencent.bugly.proguard.y.class
            monitor-enter(r0)
            boolean r1 = m     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            boolean r1 = a     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x000c
            goto L_0x001e
        L_0x000c:
            java.util.concurrent.ExecutorService r1 = o     // Catch:{ Exception -> 0x0018 }
            com.tencent.bugly.proguard.y$1 r2 = new com.tencent.bugly.proguard.y$1     // Catch:{ Exception -> 0x0018 }
            r2.<init>(r3, r4, r5)     // Catch:{ Exception -> 0x0018 }
            r1.execute(r2)     // Catch:{ Exception -> 0x0018 }
            monitor-exit(r0)
            return
        L_0x0018:
            r3 = move-exception
            com.tencent.bugly.proguard.x.b(r3)     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)
            return
        L_0x001e:
            monitor-exit(r0)
            return
        L_0x0020:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.y.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public static synchronized void c(String str, String str2, String str3) {
        synchronized (y.class) {
            if (b) {
                d(str, str2, str3);
            } else {
                e(str, str2, str3);
            }
        }
    }

    private static synchronized void d(String str, String str2, String str3) {
        synchronized (y.class) {
            String a2 = a(str, str2, str3, (long) Process.myTid());
            synchronized (q) {
                try {
                    f.append(a2);
                    if (f.length() >= d) {
                        f = f.delete(0, f.indexOf("\u0001\r\n") + 1);
                    }
                } catch (Throwable th) {
                    if (!x.b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:19|20|21|(1:23)(2:24|(1:28))|29|(1:31)|33|34|35|36) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x007b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void e(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.y> r0 = com.tencent.bugly.proguard.y.class
            monitor-enter(r0)
            int r1 = android.os.Process.myTid()     // Catch:{ all -> 0x0080 }
            long r1 = (long) r1     // Catch:{ all -> 0x0080 }
            java.lang.String r5 = a(r5, r6, r7, r1)     // Catch:{ all -> 0x0080 }
            java.lang.Object r6 = q     // Catch:{ all -> 0x0080 }
            monitor-enter(r6)     // Catch:{ all -> 0x0080 }
            java.lang.StringBuilder r7 = f     // Catch:{ Throwable -> 0x007b }
            r7.append(r5)     // Catch:{ Throwable -> 0x007b }
            java.lang.StringBuilder r5 = f     // Catch:{ Throwable -> 0x007b }
            int r5 = r5.length()     // Catch:{ Throwable -> 0x007b }
            int r7 = d     // Catch:{ Throwable -> 0x007b }
            if (r5 > r7) goto L_0x0021
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x0021:
            boolean r5 = g     // Catch:{ Throwable -> 0x007b }
            if (r5 == 0) goto L_0x0028
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x0028:
            r5 = 1
            g = r5     // Catch:{ Throwable -> 0x007b }
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            if (r5 != 0) goto L_0x0039
            com.tencent.bugly.proguard.y$a r5 = new com.tencent.bugly.proguard.y$a     // Catch:{ Throwable -> 0x007b }
            java.lang.String r7 = l     // Catch:{ Throwable -> 0x007b }
            r5.<init>(r7)     // Catch:{ Throwable -> 0x007b }
            h = r5     // Catch:{ Throwable -> 0x007b }
            goto L_0x0062
        L_0x0039:
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            java.io.File r5 = r5.b     // Catch:{ Throwable -> 0x007b }
            if (r5 == 0) goto L_0x005d
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            java.io.File r5 = r5.b     // Catch:{ Throwable -> 0x007b }
            long r1 = r5.length()     // Catch:{ Throwable -> 0x007b }
            java.lang.StringBuilder r5 = f     // Catch:{ Throwable -> 0x007b }
            int r5 = r5.length()     // Catch:{ Throwable -> 0x007b }
            long r3 = (long) r5     // Catch:{ Throwable -> 0x007b }
            long r1 = r1 + r3
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            long r3 = r5.e     // Catch:{ Throwable -> 0x007b }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0062
        L_0x005d:
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            boolean unused = r5.a()     // Catch:{ Throwable -> 0x007b }
        L_0x0062:
            com.tencent.bugly.proguard.y$a r5 = h     // Catch:{ Throwable -> 0x007b }
            java.lang.StringBuilder r7 = f     // Catch:{ Throwable -> 0x007b }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x007b }
            boolean r5 = r5.a((java.lang.String) r7)     // Catch:{ Throwable -> 0x007b }
            if (r5 == 0) goto L_0x007b
            java.lang.StringBuilder r5 = f     // Catch:{ Throwable -> 0x007b }
            r7 = 0
            r5.setLength(r7)     // Catch:{ Throwable -> 0x007b }
            g = r7     // Catch:{ Throwable -> 0x007b }
            goto L_0x007b
        L_0x0079:
            r5 = move-exception
            goto L_0x007e
        L_0x007b:
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x007e:
            monitor-exit(r6)     // Catch:{ all -> 0x0080 }
            throw r5     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.y.e(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static String a(String str, String str2, String str3, long j2) {
        String str4;
        e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        if (c != null) {
            str4 = c.format(date);
        } else {
            str4 = date.toString();
        }
        StringBuilder sb = e;
        sb.append(str4);
        sb.append(" ");
        sb.append(p);
        sb.append(" ");
        sb.append(j2);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str3);
        sb.append("\u0001\r\n");
        return e.toString();
    }

    public static byte[] a() {
        if (!b) {
            return b();
        }
        if (!a) {
            return null;
        }
        return z.a((File) null, f.toString(), "BuglyLog.txt");
    }

    private static byte[] b() {
        if (!a) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        synchronized (q) {
            if (h != null && h.a && h.b != null && h.b.length() > 0) {
                sb.append(z.a(h.b, 30720, true));
            }
            if (f != null && f.length() > 0) {
                sb.append(f.toString());
            }
        }
        return z.a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* compiled from: BUGLY */
    public static class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public File b;
        private String c;
        private long d;
        /* access modifiers changed from: private */
        public long e = 30720;

        public a(String str) {
            if (str != null && !str.equals("")) {
                this.c = str;
                this.a = a();
            }
        }

        /* access modifiers changed from: private */
        public boolean a() {
            try {
                this.b = new File(this.c);
                if (this.b.exists() && !this.b.delete()) {
                    this.a = false;
                    return false;
                } else if (this.b.createNewFile()) {
                    return true;
                } else {
                    this.a = false;
                    return false;
                }
            } catch (Throwable th) {
                x.a(th);
                this.a = false;
                return false;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x003b A[SYNTHETIC, Splitter:B:22:0x003b] */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0041 A[SYNTHETIC, Splitter:B:27:0x0041] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(java.lang.String r10) {
            /*
                r9 = this;
                boolean r0 = r9.a
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                r0 = 0
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0033 }
                java.io.File r3 = r9.b     // Catch:{ Throwable -> 0x0033 }
                r4 = 1
                r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x0033 }
                java.lang.String r0 = "UTF-8"
                byte[] r10 = r10.getBytes(r0)     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                r2.write(r10)     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                r2.flush()     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                r2.close()     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                long r5 = r9.d     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                int r10 = r10.length     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                long r7 = (long) r10     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                long r5 = r5 + r7
                r9.d = r5     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                r9.a = r4     // Catch:{ Throwable -> 0x002d, all -> 0x002b }
                r2.close()     // Catch:{ IOException -> 0x002a }
            L_0x002a:
                return r4
            L_0x002b:
                r10 = move-exception
                goto L_0x003f
            L_0x002d:
                r10 = move-exception
                r0 = r2
                goto L_0x0034
            L_0x0030:
                r10 = move-exception
                r2 = r0
                goto L_0x003f
            L_0x0033:
                r10 = move-exception
            L_0x0034:
                com.tencent.bugly.proguard.x.a(r10)     // Catch:{ all -> 0x0030 }
                r9.a = r1     // Catch:{ all -> 0x0030 }
                if (r0 == 0) goto L_0x003e
                r0.close()     // Catch:{ IOException -> 0x003e }
            L_0x003e:
                return r1
            L_0x003f:
                if (r2 == 0) goto L_0x0044
                r2.close()     // Catch:{ IOException -> 0x0044 }
            L_0x0044:
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.y.a.a(java.lang.String):boolean");
        }
    }
}
