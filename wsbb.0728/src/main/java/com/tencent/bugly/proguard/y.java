package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static final class a {
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

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[SYNTHETIC, Splitter:B:14:0x0034] */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0042 A[SYNTHETIC, Splitter:B:22:0x0042] */
        public final boolean a(String str) {
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2 = null;
            if (!this.a) {
                return false;
            }
            try {
                fileOutputStream = new FileOutputStream(this.b, true);
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.d += (long) bytes.length;
                    this.a = true;
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (IOException e2) {
                        return true;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                }
                throw th;
            }
        }
    }

    static {
        try {
            c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            x.b(th.getCause());
        }
    }

    private static String a(String str, String str2, String str3, long j2) {
        e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        String format = c != null ? c.format(date) : date.toString();
        StringBuilder sb = e;
        sb.append(format);
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

    public static void a(Context context) {
        synchronized (y.class) {
            try {
                if (!m && context != null && a) {
                    try {
                        o = Executors.newSingleThreadExecutor();
                        f = new StringBuilder(0);
                        e = new StringBuilder(0);
                        k = context;
                        com.tencent.bugly.crashreport.common.info.a a2 = com.tencent.bugly.crashreport.common.info.a.a(context);
                        i = a2.d;
                        a2.getClass();
                        j = "";
                        l = k.getFilesDir().getPath() + "/buglylog_" + i + "_" + j + ".txt";
                        p = Process.myPid();
                    } catch (Throwable th) {
                    }
                    m = true;
                }
            } catch (Throwable th2) {
                Class<y> cls = y.class;
                throw th2;
            }
        }
    }

    public static void a(final String str, final String str2, final String str3) {
        synchronized (y.class) {
            try {
                if (m && a) {
                    o.execute(new Runnable() {
                        public final void run() {
                            y.c(str, str2, str3);
                        }
                    });
                }
            } catch (Exception e2) {
                x.b(e2);
                Class<y> cls = y.class;
            } catch (Throwable th) {
                Class<y> cls2 = y.class;
                throw th;
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

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3) {
        synchronized (y.class) {
            try {
                if (b) {
                    d(str, str2, str3);
                } else {
                    e(str, str2, str3);
                }
            } catch (Throwable th) {
                Class<y> cls = y.class;
                throw th;
            }
        }
    }

    private static void d(String str, String str2, String str3) {
        synchronized (y.class) {
            try {
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
            } catch (Throwable th2) {
                Class<y> cls = y.class;
                throw th2;
            }
        }
    }

    private static void e(String str, String str2, String str3) {
        synchronized (y.class) {
            try {
                String a2 = a(str, str2, str3, (long) Process.myTid());
                synchronized (q) {
                    try {
                        f.append(a2);
                        if (f.length() > d) {
                            if (!g) {
                                g = true;
                                if (h == null) {
                                    h = new a(l);
                                } else if (h.b == null || h.b.length() + ((long) f.length()) > h.e) {
                                    boolean unused = h.a();
                                }
                                if (h.a(f.toString())) {
                                    f.setLength(0);
                                    g = false;
                                }
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
                Class<y> cls = y.class;
                throw th2;
            }
        }
    }
}
