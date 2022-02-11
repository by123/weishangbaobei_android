package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.pro.c;

class d extends SQLiteOpenHelper {
    /* access modifiers changed from: private */
    public static Context b;
    private String a;

    private static class a {
        /* access modifiers changed from: private */
        public static final d a = new d(d.b, f.b(d.b), c.b, (SQLiteDatabase.CursorFactory) null, 2);

        private a() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private d(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, TextUtils.isEmpty(str) ? c.b : str, cursorFactory, i);
        this.a = null;
        a();
    }

    private d(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new a(context, str), str2, cursorFactory, i);
    }

    public static d a(Context context) {
        if (b == null) {
            b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        } catch (SQLException e) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        try {
            this.a = "create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL(this.a);
        } catch (SQLException e) {
        }
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
        } catch (Exception e) {
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            b(sQLiteDatabase);
            a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (SQLiteDatabaseCorruptException e) {
            f.a(b);
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                }
            }
            throw th;
        }
        try {
            sQLiteDatabase.endTransaction();
        } catch (Throwable th3) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 > i && i == 1) {
            try {
                e(sQLiteDatabase);
            } catch (Exception e) {
                try {
                    e(sQLiteDatabase);
                } catch (Exception e2) {
                    f(sQLiteDatabase);
                }
            }
        }
    }
}
