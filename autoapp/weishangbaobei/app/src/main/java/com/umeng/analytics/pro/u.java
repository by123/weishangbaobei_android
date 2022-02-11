package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SessionIdManager */
public class u {
    private static volatile u c;
    private s a = new t();
    private String b;
    private List<a> d;
    private String e;

    /* compiled from: SessionIdManager */
    public interface a {
        void a(String str, long j, long j2);

        void a(String str, String str2, long j, long j2);
    }

    private u() {
    }

    public static u a() {
        if (c == null) {
            synchronized (u.class) {
                if (c == null) {
                    c = new u();
                }
            }
        }
        return c;
    }

    public void a(long j) {
        this.a.a(j);
    }

    public long b() {
        return this.a.a();
    }

    public String a(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        String str = "";
        try {
            synchronized (u.class) {
                try {
                    String string = PreferenceWrapper.getDefault(appContext).getString(q.d, "");
                    try {
                        return string;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        str = string;
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        } catch (Exception unused) {
            return str;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|(2:10|11)|12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String b(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.content.Context r2 = com.umeng.commonsdk.service.UMGlobalContext.getAppContext(r2)     // Catch:{ all -> 0x0021 }
            if (r2 != 0) goto L_0x000b
            java.lang.String r2 = ""
            monitor-exit(r1)
            return r2
        L_0x000b:
            java.lang.String r0 = r1.d(r2)     // Catch:{ all -> 0x0021 }
            r1.b = r0     // Catch:{ all -> 0x0021 }
            boolean r0 = r1.e(r2)     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x001d
            java.lang.String r2 = r1.f(r2)     // Catch:{ Exception -> 0x001d }
            r1.b = r2     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            java.lang.String r2 = r1.b     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)
            return r2
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.u.b(android.content.Context):java.lang.String");
    }

    public String c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.b = f(appContext);
        } catch (Exception unused) {
        }
        return this.b;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            try {
                this.b = PreferenceWrapper.getDefault(context).getString(q.c, (String) null);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }

    public String a(Context context, long j) {
        if (TextUtils.isEmpty(this.e)) {
            String str = "SUB" + j;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(String.format("%0" + (32 - str.length()) + e.am, new Object[]{0}));
            this.e = sb.toString();
        }
        return this.e;
    }

    public boolean e(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = d(context);
        }
        return TextUtils.isEmpty(this.b) || j(context) || g(context);
    }

    private String f(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(q.d, d(context));
            edit.commit();
        } catch (Exception unused) {
        }
        long h = h(context);
        long i = i(context);
        long j = h;
        String str = this.b;
        a(i, j, str, false);
        this.b = this.a.a(context);
        a(i, j, str, true);
        this.a.a(context, this.b);
        return this.b;
    }

    private boolean g(Context context) {
        if (!TextUtils.isEmpty(this.b) && g.a(context).a(this.b) > 0) {
            return true;
        }
        return false;
    }

    private long a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0);
        } catch (Exception unused) {
            j = 0;
        }
        return j <= 0 ? System.currentTimeMillis() : j;
    }

    private long h(Context context) {
        return a(context, q.f);
    }

    private long i(Context context) {
        return a(context, q.a);
    }

    private void a(long j, long j2, String str, boolean z) {
        if (this.d != null) {
            for (a next : this.d) {
                if (z) {
                    try {
                        next.a(str, this.b, j, j2);
                    } catch (Exception unused) {
                    }
                } else {
                    next.a(this.b, j, j2);
                }
            }
        }
    }

    private boolean j(Context context) {
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(UMGlobalContext.getAppContext(context));
            long j = sharedPreferences.getLong(q.e, 0);
            long j2 = sharedPreferences.getLong(q.f, 0);
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.a.a(j, j2);
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            if (!this.d.contains(aVar)) {
                this.d.add(aVar);
            }
        }
    }

    public void b(a aVar) {
        if (aVar != null && this.d != null && this.d.size() != 0) {
            this.d.remove(aVar);
        }
    }
}
