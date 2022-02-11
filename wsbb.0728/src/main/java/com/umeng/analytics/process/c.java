package com.umeng.analytics.process;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class c {
    private static c a;
    private ConcurrentHashMap<String, a> b = new ConcurrentHashMap<>();
    private Context c;

    static class a {
        private AtomicInteger a = new AtomicInteger();
        private SQLiteOpenHelper b;
        private SQLiteDatabase c;

        private a() {
        }

        static a a(Context context, String str) {
            Context appContext = UMGlobalContext.getAppContext(context);
            a aVar = new a();
            aVar.b = b.a(appContext, str);
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public SQLiteDatabase a() {
            SQLiteDatabase sQLiteDatabase;
            synchronized (this) {
                if (this.a.incrementAndGet() == 1) {
                    this.c = this.b.getWritableDatabase();
                }
                sQLiteDatabase = this.c;
            }
            return sQLiteDatabase;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        public void b() {
            synchronized (this) {
                if (this.a.decrementAndGet() == 0) {
                    this.c.close();
                }
            }
        }
    }

    private c() {
    }

    static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                try {
                    if (a == null) {
                        a = new c();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<c> cls = c.class;
                        throw th;
                    }
                }
            }
        }
        a.c = context;
        return a;
    }

    private a c(String str) {
        if (this.b.get(str) != null) {
            return this.b.get(str);
        }
        a a2 = a.a(this.c, str);
        this.b.put(str, a2);
        return a2;
    }

    /* access modifiers changed from: package-private */
    public SQLiteDatabase a(String str) {
        SQLiteDatabase a2;
        synchronized (this) {
            a2 = c(str).a();
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        synchronized (this) {
            c(str).b();
        }
    }
}
