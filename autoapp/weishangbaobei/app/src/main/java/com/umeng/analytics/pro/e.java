package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.stub.StubApp;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UMDBManager */
class e {
    private static SQLiteOpenHelper b;
    private static Context d;
    private AtomicInteger a;
    private SQLiteDatabase c;

    private e() {
        this.a = new AtomicInteger();
    }

    /* compiled from: UMDBManager */
    private static class a {
        /* access modifiers changed from: private */
        public static final e a = new e();

        private a() {
        }
    }

    public static e a(Context context) {
        if (d == null && context != null) {
            d = StubApp.getOrigApplicationContext(context.getApplicationContext());
            b = d.a(d);
        }
        return a.a;
    }

    public synchronized SQLiteDatabase a() {
        if (this.a.incrementAndGet() == 1) {
            this.c = b.getWritableDatabase();
        }
        return this.c;
    }

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
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.e.b():void");
    }
}
