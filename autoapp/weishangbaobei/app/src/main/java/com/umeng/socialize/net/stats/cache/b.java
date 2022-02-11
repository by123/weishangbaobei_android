package com.umeng.socialize.net.stats.cache;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.umeng.socialize.net.stats.cache.c;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import java.io.File;

/* compiled from: StatsCacheApis */
public class b {
    static String a = "b";
    private HandlerThread b;
    private Handler c;
    /* access modifiers changed from: private */
    public c d;

    /* compiled from: StatsCacheApis */
    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b();

        private a() {
        }
    }

    public static final b a() {
        return a.a;
    }

    private b() {
        this.b = new HandlerThread(Log.TAG, 10);
        this.b.start();
        this.c = new Handler(this.b.getLooper());
        String b2 = b();
        if (!TextUtils.isEmpty(b2)) {
            this.d = new c(b2);
        }
    }

    public void a(final String str, final UMCacheListener uMCacheListener) {
        if (this.d != null) {
            this.c.post(new Runnable() {
                public void run() {
                    String str = b.a;
                    Log.d(str, "save:" + Thread.currentThread().getId());
                    boolean a2 = b.this.d.a(str);
                    if (uMCacheListener != null) {
                        uMCacheListener.onResult(a2, (c.a) null);
                    }
                }
            });
        }
    }

    public void a(final UMCacheListener uMCacheListener) {
        if (this.d != null) {
            this.c.post(new Runnable() {
                public void run() {
                    String str = b.a;
                    Log.d(str, "read:" + Thread.currentThread().getId());
                    c.a a2 = b.this.d.a();
                    if (uMCacheListener != null) {
                        uMCacheListener.onResult(a2 != null, a2);
                    }
                }
            });
        }
    }

    public void b(final String str, final UMCacheListener uMCacheListener) {
        if (this.d != null) {
            this.c.post(new Runnable() {
                public void run() {
                    String str = b.a;
                    Log.d(str, "delete:" + Thread.currentThread().getId());
                    boolean b2 = b.this.d.b(str);
                    if (uMCacheListener != null) {
                        uMCacheListener.onResult(b2, (c.a) null);
                    }
                }
            });
        }
    }

    private String b() {
        if (ContextUtil.getContext() == null) {
            return null;
        }
        String packageName = ContextUtil.getContext().getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        return File.separator + "data" + File.separator + "data" + File.separator + packageName + File.separator + "files" + File.separator + "umSocialStateLog";
    }
}
