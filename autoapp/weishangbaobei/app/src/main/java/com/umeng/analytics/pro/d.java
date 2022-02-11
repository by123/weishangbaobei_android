package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.pro.c;

/* compiled from: UMDBCreater */
class d extends SQLiteOpenHelper {
    /* access modifiers changed from: private */
    public static Context b;
    private String a;

    /* compiled from: UMDBCreater */
    private static class a {
        /* access modifiers changed from: private */
        public static final d a = new d(d.b, f.b(d.b), c.b, (SQLiteDatabase.CursorFactory) null, 2);

        private a() {
        }
    }

    public static d a(Context context) {
        if (b == null) {
            b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    private d(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new a(context, str), str2, cursorFactory, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private d(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, TextUtils.isEmpty(str) ? c.b : str, cursorFactory, i);
        this.a = null;
        a();
    }

    public void a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!f.a(c.d.a, writableDatabase)) {
                c(writableDatabase);
            }
            if (!f.a(c.C0005c.a, writableDatabase)) {
                d(writableDatabase);
            }
            if (!f.a(c.b.a, writableDatabase)) {
                b(writableDatabase);
            }
            if (!f.a(c.a.a, writableDatabase)) {
                a(writableDatabase);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.database.sqlite.SQLiteDatabase r2) {
        /*
            r1 = this;
            r2.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            r1.c(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            r1.d(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            r1.b(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            r1.a((android.database.sqlite.SQLiteDatabase) r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            r2.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x001d, Throwable -> 0x001a }
            if (r2 == 0) goto L_0x0025
        L_0x0014:
            r2.endTransaction()     // Catch:{ Throwable -> 0x0025 }
            goto L_0x0025
        L_0x0018:
            r0 = move-exception
            goto L_0x0026
        L_0x001a:
            if (r2 == 0) goto L_0x0025
            goto L_0x0014
        L_0x001d:
            android.content.Context r0 = b     // Catch:{ all -> 0x0018 }
            com.umeng.analytics.pro.f.a((android.content.Context) r0)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0025
            goto L_0x0014
        L_0x0025:
            return
        L_0x0026:
            if (r2 == 0) goto L_0x002b
            r2.endTransaction()     // Catch:{ Throwable -> 0x002b }
        L_0x002b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.d.onCreate(android.database.sqlite.SQLiteDatabase):void");
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException unused) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException unused) {
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException unused) {
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException unused) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|5|6|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        f(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r1, int r2, int r3) {
        /*
            r0 = this;
            if (r3 <= r2) goto L_0x0010
            r3 = 1
            if (r2 != r3) goto L_0x0010
            r0.e(r1)     // Catch:{ Exception -> 0x0009 }
            goto L_0x0010
        L_0x0009:
            r0.e(r1)     // Catch:{ Exception -> 0x000d }
            goto L_0x0010
        L_0x000d:
            r0.f(r1)
        L_0x0010:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.d.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        if (!f.a(sQLiteDatabase, c.d.a, "__av")) {
            f.a(sQLiteDatabase, c.d.a, "__sp", "TEXT");
            f.a(sQLiteDatabase, c.d.a, "__pp", "TEXT");
            f.a(sQLiteDatabase, c.d.a, "__av", "TEXT");
            f.a(sQLiteDatabase, c.d.a, "__vc", "TEXT");
        }
        if (!f.a(sQLiteDatabase, c.b.a, "__av")) {
            f.a(sQLiteDatabase, c.b.a, "__av", "TEXT");
            f.a(sQLiteDatabase, c.b.a, "__vc", "TEXT");
        }
        if (!f.a(sQLiteDatabase, c.a.a, "__av")) {
            f.a(sQLiteDatabase, c.a.a, "__av", "TEXT");
            f.a(sQLiteDatabase, c.a.a, "__vc", "TEXT");
        }
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, c.d.a);
        a(sQLiteDatabase, c.b.a);
        a(sQLiteDatabase, c.a.a);
        a();
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        } catch (SQLException unused) {
        }
    }
}
