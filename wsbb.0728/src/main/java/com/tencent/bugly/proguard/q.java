package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.a;
import com.tencent.bugly.crashreport.common.info.b;
import java.io.File;
import java.util.List;

public final class q extends SQLiteOpenHelper {
    public static String a = "bugly_db";
    private static int b = 15;
    private Context c;
    private List<a> d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q(Context context, List<a> list) {
        super(context, a + "_", (SQLiteDatabase.CursorFactory) null, b);
        com.tencent.bugly.crashreport.common.info.a.a(context).getClass();
        this.c = context;
        this.d = list;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase) {
        synchronized (this) {
            try {
                String[] strArr = new String[3];
                strArr[0] = "t_lr";
                strArr[1] = "t_ui";
                strArr[2] = "t_pf";
                for (String str : strArr) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str, new String[0]);
                }
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    public final SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    x.d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                    if (i == 5) {
                        x.e("[Database] Failed to get db.", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public final SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable th) {
                    x.d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                    if (i == 5) {
                        x.e("[Database] Failed to get db.", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sQLiteDatabase == null) {
                x.d("[Database] db error delay error record 1min.", new Object[0]);
            }
        }
        return sQLiteDatabase;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (this) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS t_ui");
                sb.append(" ( _id");
                sb.append(" INTEGER PRIMARY KEY");
                sb.append(" , _tm");
                sb.append(" int");
                sb.append(" , _ut");
                sb.append(" int");
                sb.append(" , _tp");
                sb.append(" int");
                sb.append(" , _dt");
                sb.append(" blob");
                sb.append(" , _pc");
                sb.append(" text");
                sb.append(" ) ");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS t_lr");
                sb.append(" ( _id");
                sb.append(" INTEGER PRIMARY KEY");
                sb.append(" , _tp");
                sb.append(" int");
                sb.append(" , _tm");
                sb.append(" int");
                sb.append(" , _pc");
                sb.append(" text");
                sb.append(" , _th");
                sb.append(" text");
                sb.append(" , _dt");
                sb.append(" blob");
                sb.append(" ) ");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS t_pf");
                sb.append(" ( _id");
                sb.append(" integer");
                sb.append(" , _tp");
                sb.append(" text");
                sb.append(" , _tm");
                sb.append(" int");
                sb.append(" , _dt");
                sb.append(" blob");
                sb.append(",primary key(_id");
                sb.append(",_tp");
                sb.append(" )) ");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS t_cr");
                sb.append(" ( _id");
                sb.append(" INTEGER PRIMARY KEY");
                sb.append(" , _tm");
                sb.append(" int");
                sb.append(" , _s1");
                sb.append(" text");
                sb.append(" , _up");
                sb.append(" int");
                sb.append(" , _me");
                sb.append(" int");
                sb.append(" , _uc");
                sb.append(" int");
                sb.append(" , _dt");
                sb.append(" blob");
                sb.append(" ) ");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS dl_1002");
                sb.append(" (_id");
                sb.append(" integer primary key autoincrement, _dUrl");
                sb.append(" varchar(100), _sFile");
                sb.append(" varchar(100), _sLen");
                sb.append(" INTEGER, _tLen");
                sb.append(" INTEGER, _MD5");
                sb.append(" varchar(100), _DLTIME");
                sb.append(" INTEGER)");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append("CREATE TABLE IF NOT EXISTS ge_1002");
                sb.append(" (_id");
                sb.append(" integer primary key autoincrement, _time");
                sb.append(" INTEGER, _datas");
                sb.append(" blob)");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
                sb.setLength(0);
                sb.append(" CREATE TABLE IF NOT EXISTS st_1002");
                sb.append(" ( _id");
                sb.append(" integer");
                sb.append(" , _tp");
                sb.append(" text");
                sb.append(" , _tm");
                sb.append(" int");
                sb.append(" , _dt");
                sb.append(" blob");
                sb.append(",primary key(_id");
                sb.append(",_tp");
                sb.append(" )) ");
                x.c(sb.toString(), new Object[0]);
                sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
            if (this.d != null) {
                for (a onDbCreate : this.d) {
                    try {
                        onDbCreate.onDbCreate(sQLiteDatabase);
                    } catch (Throwable th2) {
                        if (!x.b(th2)) {
                            th2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    @TargetApi(11)
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        synchronized (this) {
            if (b.c() >= 11) {
                x.d("[Database] Downgrade %d to %d drop tables.", Integer.valueOf(i), Integer.valueOf(i2));
                if (this.d != null) {
                    for (a onDbDowngrade : this.d) {
                        try {
                            onDbDowngrade.onDbDowngrade(sQLiteDatabase, i, i2);
                        } catch (Throwable th) {
                            if (!x.b(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                }
                if (a(sQLiteDatabase)) {
                    onCreate(sQLiteDatabase);
                    return;
                }
                x.d("[Database] Failed to drop, delete db.", new Object[0]);
                File databasePath = this.c.getDatabasePath(a);
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        synchronized (this) {
            x.d("[Database] Upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.d != null) {
                for (a onDbUpgrade : this.d) {
                    try {
                        onDbUpgrade.onDbUpgrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!x.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
                return;
            }
            x.d("[Database] Failed to drop, delete db.", new Object[0]);
            File databasePath = this.c.getDatabasePath(a);
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }
}
