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

    final class a implements Runnable {
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

    private u(Context context) {
        this.d = context;
        this.c = p.a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e2) {
            x.a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.a = false;
        }
        if (this.a) {
            this.k = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/" + "fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB";
        }
    }

    public static u a() {
        u uVar;
        synchronized (u.class) {
            try {
                uVar = b;
            } catch (Throwable th) {
                Class<u> cls = u.class;
                throw th;
            }
        }
        return uVar;
    }

    public static u a(Context context) {
        u uVar;
        synchronized (u.class) {
            try {
                if (b == null) {
                    b = new u(context);
                }
                uVar = b;
            } catch (Throwable th) {
                Class<u> cls = u.class;
                throw th;
            }
        }
        return uVar;
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

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008a, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a5, code lost:
        if (r9 == false) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a7, code lost:
        a((java.lang.Runnable) new com.tencent.bugly.proguard.u.a(r6, r6.d, r7, r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b7, code lost:
        a(r7, r8);
        r0 = new com.tencent.bugly.proguard.u.a(r6, r6.d);
        com.tencent.bugly.proguard.x.a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d2, code lost:
        if (com.tencent.bugly.proguard.z.a((java.lang.Runnable) r0, "BUGLY_ASYNC_UPLOAD") != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d4, code lost:
        com.tencent.bugly.proguard.x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new java.lang.Object[0]);
        r1 = com.tencent.bugly.proguard.w.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00df, code lost:
        if (r1 == null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e1, code lost:
        r1.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e6, code lost:
        com.tencent.bugly.proguard.x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new java.lang.Object[0]);
        r1 = r6.t;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ef, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r6.s = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f3, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return;
     */
    private void a(Runnable runnable, boolean z, boolean z2, long j2) {
        if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        x.c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.p != null) {
            if (b()) {
                x.c("[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z2) {
                    a(runnable, j2);
                    return;
                }
                a(runnable, z);
                c(0);
                return;
            }
            x.a("[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            b(false);
        }
        synchronized (this.t) {
            if (this.s) {
                a(runnable, z);
                return;
            }
            this.s = true;
        }
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

    static /* synthetic */ int b(u uVar) {
        int i2 = uVar.u - 1;
        uVar.u = i2;
        return i2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bd, code lost:
        if (r15 <= 0) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bf, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r15), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e1, code lost:
        if (r3 >= r15) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e3, code lost:
        r0 = (java.lang.Runnable) r5.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e9, code lost:
        if (r0 == null) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00eb, code lost:
        r7 = r14.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ed, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f0, code lost:
        if (r14.u < 2) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f2, code lost:
        if (r4 == null) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00f4, code lost:
        r4.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f7, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f8, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fc, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00fd, code lost:
        com.tencent.bugly.proguard.x.a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0113, code lost:
        if (com.tencent.bugly.proguard.z.a((java.lang.Runnable) new com.tencent.bugly.proguard.u.AnonymousClass1(r14), "BUGLY_ASYNC_UPLOAD") == null) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0115, code lost:
        r7 = r14.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0117, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r14.u++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011e, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0123, code lost:
        com.tencent.bugly.proguard.x.d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new java.lang.Object[0]);
        a(r0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0131, code lost:
        if (r1 <= 0) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0133, code lost:
        com.tencent.bugly.proguard.x.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r1), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0156, code lost:
        r4.a(new com.tencent.bugly.proguard.u.AnonymousClass2(r14));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061 A[SYNTHETIC, Splitter:B:19:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b A[Catch:{ Throwable -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0095 A[Catch:{ Throwable -> 0x0081 }] */
    public void c(int i2) {
        final int i3;
        int i4;
        int i5;
        if (i2 < 0) {
            x.a("[UploadManager] Number of task to execute should >= 0", new Object[0]);
            return;
        }
        w a2 = w.a();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        synchronized (this.j) {
            x.c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            int size = this.h.size();
            int size2 = this.i.size();
            if (size == 0 && size2 == 0) {
                x.c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (i2 != 0) {
                if (i2 < size) {
                    size2 = 0;
                } else if (i2 < size + size2) {
                    size2 = i2 - size;
                    i2 = size;
                }
                if (a2 != null) {
                    if (a2.c()) {
                        i3 = size2;
                        for (i4 = 0; i4 < i2; i4++) {
                            Runnable peek = this.h.peek();
                            if (peek == null) {
                                break;
                            }
                            try {
                                linkedBlockingQueue.put(peek);
                                this.h.poll();
                            } catch (Throwable th) {
                                x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
                            }
                        }
                        for (i5 = 0; i5 < i3; i5++) {
                            Runnable peek2 = this.i.peek();
                            if (peek2 == null) {
                                break;
                            }
                            try {
                                linkedBlockingQueue2.put(peek2);
                                this.i.poll();
                            } catch (Throwable th2) {
                                x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th2.getMessage());
                            }
                        }
                    }
                }
                i3 = 0;
                while (i4 < i2) {
                }
                while (i5 < i3) {
                }
            }
            i2 = size;
            if (a2 != null) {
            }
            i3 = 0;
            while (i4 < i2) {
            }
            while (i5 < i3) {
            }
        }
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
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
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
                    if (!z) {
                        if (!split[1].isEmpty() && !split[1].equals("null")) {
                            try {
                                this.o = Long.parseLong(split[1]);
                            } catch (Throwable th) {
                                x.a(th);
                                z = true;
                            }
                        }
                    }
                    if (!z) {
                        if (!split[2].isEmpty() && !split[2].equals("null")) {
                            this.p = split[2];
                        }
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

    public final long a(int i2) {
        long j2;
        synchronized (this) {
            long j3 = 0;
            if (i2 >= 0) {
                Long l2 = this.e.get(Integer.valueOf(i2));
                if (l2 != null) {
                    j2 = l2.longValue();
                } else {
                    List<r> a2 = this.c.a(i2);
                    if (a2 == null || a2.size() <= 0) {
                        j2 = 0;
                    } else if (a2.size() > 1) {
                        for (r next : a2) {
                            if (next.e > j3) {
                                j3 = next.e;
                            }
                        }
                        this.c.b(i2);
                        j2 = j3;
                    } else {
                        try {
                            j2 = a2.get(0).e;
                        } catch (Throwable th) {
                            x.a(th);
                            j2 = 0;
                        }
                    }
                }
            } else {
                x.e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i2));
                j2 = 0;
            }
        }
        return j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    public final long a(boolean z) {
        long j2;
        long j3 = 0;
        long b2 = z.b();
        int i2 = z ? 5 : 3;
        List<r> a2 = this.c.a(i2);
        if (a2 == null || a2.size() <= 0) {
            j3 = z ? this.g : this.f;
        } else {
            try {
                r rVar = a2.get(0);
                if (rVar.e >= b2) {
                    j3 = z.c(rVar.g);
                    if (i2 == 3) {
                        try {
                            this.f = j3;
                        } catch (Throwable th) {
                            th = th;
                            j2 = j3;
                        }
                    } else {
                        this.g = j3;
                    }
                    a2.remove(rVar);
                }
            } catch (Throwable th2) {
                th = th2;
                j2 = 0;
                x.a(th);
                j3 = j2;
                if (a2.size() > 0) {
                }
                x.c("[UploadManager] Local network consume: %d KB", Long.valueOf(j3 / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS));
                return j3;
            }
            if (a2.size() > 0) {
                this.c.a(a2);
            }
        }
        x.c("[UploadManager] Local network consume: %d KB", Long.valueOf(j3 / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS));
        return j3;
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

    public final void a(int i2, long j2) {
        synchronized (this) {
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

    public final void a(int i2, ap apVar, String str, String str2, t tVar, boolean z) {
        a(i2, apVar.g, a.a((Object) apVar), str, str2, tVar, 0, 0, z, (Map<String, String>) null);
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final void a(int r9, com.tencent.bugly.proguard.aq r10) {
        /*
            r8 = this;
            r4 = 2
            r2 = 1
            r1 = 0
            boolean r0 = r8.a
            if (r0 != 0) goto L_0x0008
        L_0x0007:
            return
        L_0x0008:
            if (r9 != r4) goto L_0x003e
            java.lang.String r0 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r1 = android.os.Process.myTid()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3[r2] = r1
            com.tencent.bugly.proguard.x.c(r0, r3)
            r8.b((boolean) r2)
        L_0x0028:
            java.lang.Object r1 = r8.t
            monitor-enter(r1)
            boolean r0 = r8.s     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0039
            r0 = 0
            r8.s = r0     // Catch:{ all -> 0x003b }
            android.content.Context r0 = r8.d     // Catch:{ all -> 0x003b }
            java.lang.String r2 = "security_info"
            com.tencent.bugly.proguard.z.b((android.content.Context) r0, (java.lang.String) r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            goto L_0x0007
        L_0x003b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x003e:
            java.lang.Object r3 = r8.t
            monitor-enter(r3)
            boolean r0 = r8.s     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x004a
            monitor-exit(r3)     // Catch:{ all -> 0x0047 }
            goto L_0x0007
        L_0x0047:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x004a:
            monitor-exit(r3)
            if (r10 == 0) goto L_0x0134
            java.lang.String r0 = "[UploadManager] Record security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.proguard.x.c(r0, r3)
            java.util.Map<java.lang.String, java.lang.String> r3 = r10.g     // Catch:{ Throwable -> 0x0119 }
            if (r3 == 0) goto L_0x0103
            java.lang.String r0 = "S1"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0119 }
            if (r0 == 0) goto L_0x0103
            java.lang.String r0 = "S2"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0119 }
            if (r0 == 0) goto L_0x0103
            long r4 = r10.e     // Catch:{ Throwable -> 0x0119 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0119 }
            long r4 = r4 - r6
            r8.m = r4     // Catch:{ Throwable -> 0x0119 }
            java.lang.String r0 = "[UploadManager] Time lag of server is: %d"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0119 }
            r5 = 0
            long r6 = r8.m     // Catch:{ Throwable -> 0x0119 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x0119 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x0119 }
            com.tencent.bugly.proguard.x.c(r0, r4)     // Catch:{ Throwable -> 0x0119 }
            java.lang.String r0 = "S1"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ Throwable -> 0x0119 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0119 }
            r8.p = r0     // Catch:{ Throwable -> 0x0119 }
            java.lang.String r0 = "[UploadManager] Session ID from server is: %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0119 }
            r5 = 0
            java.lang.String r6 = r8.p     // Catch:{ Throwable -> 0x0119 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x0119 }
            com.tencent.bugly.proguard.x.c(r0, r4)     // Catch:{ Throwable -> 0x0119 }
            java.lang.String r0 = r8.p     // Catch:{ Throwable -> 0x0119 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x0119 }
            if (r0 <= 0) goto L_0x012b
            java.lang.String r0 = "S2"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ NumberFormatException -> 0x010a }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x010a }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x010a }
            r8.q = r4     // Catch:{ NumberFormatException -> 0x010a }
            java.lang.String r0 = "[UploadManager] Session expired time from server is: %d(%s)"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x010a }
            r4 = 0
            long r6 = r8.q     // Catch:{ NumberFormatException -> 0x010a }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ NumberFormatException -> 0x010a }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x010a }
            r4 = 1
            java.util.Date r5 = new java.util.Date     // Catch:{ NumberFormatException -> 0x010a }
            long r6 = r8.q     // Catch:{ NumberFormatException -> 0x010a }
            r5.<init>(r6)     // Catch:{ NumberFormatException -> 0x010a }
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x010a }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x010a }
            com.tencent.bugly.proguard.x.c(r0, r3)     // Catch:{ NumberFormatException -> 0x010a }
            long r4 = r8.q     // Catch:{ NumberFormatException -> 0x010a }
            r6 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f7
            java.lang.String r0 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x010a }
            com.tencent.bugly.proguard.x.d(r0, r3)     // Catch:{ NumberFormatException -> 0x010a }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.q = r4     // Catch:{ NumberFormatException -> 0x010a }
        L_0x00f7:
            boolean r0 = r8.d()     // Catch:{ Throwable -> 0x0119 }
            if (r0 == 0) goto L_0x0121
            r0 = r1
        L_0x00fe:
            r2 = 0
            r8.c((int) r2)     // Catch:{ Throwable -> 0x0154 }
            r2 = r0
        L_0x0103:
            if (r2 == 0) goto L_0x0028
            r8.b((boolean) r1)
            goto L_0x0028
        L_0x010a:
            r0 = move-exception
            java.lang.String r0 = "[UploadManager] Session expired time is invalid, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0119 }
            com.tencent.bugly.proguard.x.d(r0, r3)     // Catch:{ Throwable -> 0x0119 }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.q = r4     // Catch:{ Throwable -> 0x0119 }
            goto L_0x00f7
        L_0x0119:
            r0 = move-exception
            r3 = r0
            r4 = r2
        L_0x011c:
            com.tencent.bugly.proguard.x.a(r3)
            r2 = r4
            goto L_0x0103
        L_0x0121:
            java.lang.String r0 = "[UploadManager] Failed to record database"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0119 }
            com.tencent.bugly.proguard.x.c(r0, r3)     // Catch:{ Throwable -> 0x0119 }
            r0 = r2
            goto L_0x00fe
        L_0x012b:
            java.lang.String r0 = "[UploadManager] Session ID from server is invalid, try next time"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0119 }
            com.tencent.bugly.proguard.x.c(r0, r3)     // Catch:{ Throwable -> 0x0119 }
            goto L_0x0103
        L_0x0134:
            java.lang.String r0 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.proguard.x.c(r0, r3)
            r8.b((boolean) r1)
            goto L_0x0028
        L_0x0154:
            r2 = move-exception
            r3 = r2
            r4 = r0
            goto L_0x011c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.a(int, com.tencent.bugly.proguard.aq):void");
    }

    /* access modifiers changed from: protected */
    public final void a(long j2, boolean z) {
        synchronized (this) {
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

    public final byte[] a(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(1, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
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

    public final byte[] b(byte[] bArr) {
        if (this.n != null && (this.n.length << 3) == 128) {
            return z.a(2, bArr, this.n);
        }
        x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }
}
