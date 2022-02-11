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

public class u {
    private static volatile u c;
    private s a = new t();
    private String b;
    private List<a> d;
    private String e;

    public interface a {
        void a(String str, long j, long j2);

        void a(String str, String str2, long j, long j2);
    }

    private u() {
    }

    private long a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0);
        } catch (Exception e2) {
            j = 0;
        }
        return j <= 0 ? System.currentTimeMillis() : j;
    }

    public static u a() {
        if (c == null) {
            synchronized (u.class) {
                try {
                    if (c == null) {
                        c = new u();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<u> cls = u.class;
                        throw th;
                    }
                }
            }
        }
        return c;
    }

    private void a(long j, long j2, String str, boolean z) {
        if (this.d != null) {
            for (a next : this.d) {
                if (z) {
                    try {
                        next.a(str, this.b, j, j2);
                    } catch (Exception e2) {
                    }
                } else {
                    next.a(this.b, j, j2);
                }
            }
        }
    }

    private String f(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(q.d, d(context));
            edit.commit();
        } catch (Exception e2) {
        }
        long h = h(context);
        long i = i(context);
        String str = this.b;
        a(i, h, str, false);
        this.b = this.a.a(context);
        a(i, h, str, true);
        this.a.a(context, this.b);
        return this.b;
    }

    private boolean g(Context context) {
        return !TextUtils.isEmpty(this.b) && g.a(context).a(this.b) > 0;
    }

    private long h(Context context) {
        return a(context, q.f);
    }

    private long i(Context context) {
        return a(context, q.a);
    }

    private boolean j(Context context) {
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(UMGlobalContext.getAppContext(context));
            long j = sharedPreferences.getLong(q.e, 0);
            long j2 = sharedPreferences.getLong(q.f, 0);
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.a.a(j, j2);
        } catch (Exception e2) {
            return false;
        }
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
                    str = PreferenceWrapper.getDefault(appContext).getString(q.d, "");
                    try {
                        return str;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } catch (Exception e2) {
            return str;
        }
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

    public void a(long j) {
        this.a.a(j);
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

    public long b() {
        return this.a.a();
    }

    public String b(Context context) {
        synchronized (this) {
            Context appContext = UMGlobalContext.getAppContext(context);
            if (appContext == null) {
                return "";
            }
            this.b = d(appContext);
            if (e(appContext)) {
                try {
                    this.b = f(appContext);
                } catch (Exception e2) {
                }
            }
            String str = this.b;
            return str;
        }
    }

    public void b(a aVar) {
        if (aVar != null && this.d != null && this.d.size() != 0) {
            this.d.remove(aVar);
        }
    }

    public String c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.b = f(appContext);
        } catch (Exception e2) {
        }
        return this.b;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            try {
                this.b = PreferenceWrapper.getDefault(context).getString(q.c, (String) null);
            } catch (Exception e2) {
            }
        }
        return this.b;
    }

    public boolean e(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = d(context);
        }
        return TextUtils.isEmpty(this.b) || j(context) || g(context);
    }
}
