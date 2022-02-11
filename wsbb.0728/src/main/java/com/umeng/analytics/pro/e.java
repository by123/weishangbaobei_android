package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.stub.StubApp;
import java.util.concurrent.atomic.AtomicInteger;

class e {
    private static SQLiteOpenHelper b;
    private static Context d;
    private AtomicInteger a;
    private SQLiteDatabase c;

    private static class a {
        /* access modifiers changed from: private */
        public static final e a = new e();

        private a() {
        }
    }

    private e() {
        this.a = new AtomicInteger();
    }

    public static e a(Context context) {
        if (d == null && context != null) {
            d = StubApp.getOrigApplicationContext(context.getApplicationContext());
            b = d.a(d);
        }
        return a.a;
    }

    public SQLiteDatabase a() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (this.a.incrementAndGet() == 1) {
                this.c = b.getWritableDatabase();
            }
            sQLiteDatabase = this.c;
        }
        return sQLiteDatabase;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public void b() {
        synchronized (this) {
            if (this.a.decrementAndGet() == 0) {
                this.c.close();
            }
        }
    }
}
