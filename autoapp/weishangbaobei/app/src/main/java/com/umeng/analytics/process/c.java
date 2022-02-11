package com.umeng.analytics.process;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UMProcessDBManager */
class c {
    private static c a;
    private ConcurrentHashMap<String, a> b = new ConcurrentHashMap<>();
    private Context c;

    private c() {
    }

    static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        a.c = context;
        return a;
    }

    /* access modifiers changed from: package-private */
    public synchronized SQLiteDatabase a(String str) {
        return c(str).a();
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(String str) {
        c(str).b();
    }

    private a c(String str) {
        if (this.b.get(str) != null) {
            return this.b.get(str);
        }
        a a2 = a.a(this.c, str);
        this.b.put(str, a2);
        return a2;
    }

    /* compiled from: UMProcessDBManager */
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
        public synchronized SQLiteDatabase a() {
            if (this.a.incrementAndGet() == 1) {
                this.c = this.b.getWritableDatabase();
            }
            return this.c;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void b() {
            /*
                r1 = this;
                monitor-enter(r1)
                java.util.concurrent.atomic.AtomicInteger r0 = r1.a     // Catch:{ Throwable -> 0x0012, all -> 0x000f }
                int r0 = r0.decrementAndGet()     // Catch:{ Throwable -> 0x0012, all -> 0x000f }
                if (r0 != 0) goto L_0x0012
                android.database.sqlite.SQLiteDatabase r0 = r1.c     // Catch:{ Throwable -> 0x0012, all -> 0x000f }
                r0.close()     // Catch:{ Throwable -> 0x0012, all -> 0x000f }
                goto L_0x0012
            L_0x000f:
                r0 = move-exception
                monitor-exit(r1)
                throw r0
            L_0x0012:
                monitor-exit(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.c.a.b():void");
        }
    }
}
