package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.a;
import java.util.List;

/* compiled from: BUGLY */
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

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
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

    private synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        try {
            for (String str : new String[]{"t_lr", "t_ui", "t_pf"}) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str, new String[0]);
            }
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onUpgrade(android.database.sqlite.SQLiteDatabase r6, int r7, int r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "[Database] Upgrade %d to %d , drop tables!"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0063 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0063 }
            r3 = 0
            r1[r3] = r2     // Catch:{ all -> 0x0063 }
            r2 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0063 }
            r1[r2] = r4     // Catch:{ all -> 0x0063 }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x0063 }
            java.util.List<com.tencent.bugly.a> r0 = r5.d     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x003c
            java.util.List<com.tencent.bugly.a> r0 = r5.d     // Catch:{ all -> 0x0063 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0063 }
        L_0x0021:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0063 }
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0063 }
            com.tencent.bugly.a r1 = (com.tencent.bugly.a) r1     // Catch:{ all -> 0x0063 }
            r1.onDbUpgrade(r6, r7, r8)     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0021
        L_0x0031:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.b(r1)     // Catch:{ all -> 0x0063 }
            if (r2 != 0) goto L_0x0021
            r1.printStackTrace()     // Catch:{ all -> 0x0063 }
            goto L_0x0021
        L_0x003c:
            boolean r7 = r5.a(r6)     // Catch:{ all -> 0x0063 }
            if (r7 == 0) goto L_0x0047
            r5.onCreate(r6)     // Catch:{ all -> 0x0063 }
            monitor-exit(r5)
            return
        L_0x0047:
            java.lang.String r6 = "[Database] Failed to drop, delete db."
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x0063 }
            com.tencent.bugly.proguard.x.d(r6, r7)     // Catch:{ all -> 0x0063 }
            android.content.Context r6 = r5.c     // Catch:{ all -> 0x0063 }
            java.lang.String r7 = a     // Catch:{ all -> 0x0063 }
            java.io.File r6 = r6.getDatabasePath(r7)     // Catch:{ all -> 0x0063 }
            if (r6 == 0) goto L_0x0061
            boolean r7 = r6.canWrite()     // Catch:{ all -> 0x0063 }
            if (r7 == 0) goto L_0x0061
            r6.delete()     // Catch:{ all -> 0x0063 }
        L_0x0061:
            monitor-exit(r5)
            return
        L_0x0063:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.q.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        return;
     */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onDowngrade(android.database.sqlite.SQLiteDatabase r6, int r7, int r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = com.tencent.bugly.crashreport.common.info.b.c()     // Catch:{ all -> 0x006b }
            r1 = 11
            if (r0 < r1) goto L_0x0069
            java.lang.String r0 = "[Database] Downgrade %d to %d drop tables."
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x006b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x006b }
            r3 = 0
            r1[r3] = r2     // Catch:{ all -> 0x006b }
            r2 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x006b }
            r1[r2] = r4     // Catch:{ all -> 0x006b }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x006b }
            java.util.List<com.tencent.bugly.a> r0 = r5.d     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x0044
            java.util.List<com.tencent.bugly.a> r0 = r5.d     // Catch:{ all -> 0x006b }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x006b }
        L_0x0029:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0044
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x006b }
            com.tencent.bugly.a r1 = (com.tencent.bugly.a) r1     // Catch:{ all -> 0x006b }
            r1.onDbDowngrade(r6, r7, r8)     // Catch:{ Throwable -> 0x0039 }
            goto L_0x0029
        L_0x0039:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.b(r1)     // Catch:{ all -> 0x006b }
            if (r2 != 0) goto L_0x0029
            r1.printStackTrace()     // Catch:{ all -> 0x006b }
            goto L_0x0029
        L_0x0044:
            boolean r7 = r5.a(r6)     // Catch:{ all -> 0x006b }
            if (r7 == 0) goto L_0x004f
            r5.onCreate(r6)     // Catch:{ all -> 0x006b }
            monitor-exit(r5)
            return
        L_0x004f:
            java.lang.String r6 = "[Database] Failed to drop, delete db."
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x006b }
            com.tencent.bugly.proguard.x.d(r6, r7)     // Catch:{ all -> 0x006b }
            android.content.Context r6 = r5.c     // Catch:{ all -> 0x006b }
            java.lang.String r7 = a     // Catch:{ all -> 0x006b }
            java.io.File r6 = r6.getDatabasePath(r7)     // Catch:{ all -> 0x006b }
            if (r6 == 0) goto L_0x0069
            boolean r7 = r6.canWrite()     // Catch:{ all -> 0x006b }
            if (r7 == 0) goto L_0x0069
            r6.delete()     // Catch:{ all -> 0x006b }
        L_0x0069:
            monitor-exit(r5)
            return
        L_0x006b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.q.onDowngrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getReadableDatabase();
            } catch (Throwable unused) {
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
        return sQLiteDatabase;
    }

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (Throwable unused) {
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
        return sQLiteDatabase;
    }
}
