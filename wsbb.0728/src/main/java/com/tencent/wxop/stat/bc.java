package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.stub.StubApp;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.r;
import com.umeng.analytics.pro.b;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.ArrayList;

class bc extends SQLiteOpenHelper {
    private String a = "";
    private Context b = null;

    public bc(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 3);
        this.a = str;
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (StatConfig.isDebugEnable()) {
            StatLogger e = au.h;
            e.i("SQLiteOpenHelper " + this.a);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
    private void a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Cursor cursor2;
        String str = null;
        try {
            Cursor query = sQLiteDatabase.query("user", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            try {
                ContentValues contentValues = new ContentValues();
                if (query.moveToNext()) {
                    str = query.getString(0);
                    query.getInt(1);
                    query.getString(2);
                    query.getLong(3);
                    contentValues.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, r.b(str));
                }
                if (str != null) {
                    sQLiteDatabase.update("user", contentValues, "uid=?", new String[]{str});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = query;
                if (cursor2 != 0) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = str;
            if (cursor2 != 0) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x007c  */
    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3 = null;
        try {
            cursor2 = sQLiteDatabase.query(b.ao, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            try {
                ArrayList<bd> arrayList = new ArrayList<>();
                while (cursor2.moveToNext()) {
                    arrayList.add(new bd(cursor2.getLong(0), cursor2.getString(1), cursor2.getInt(2), cursor2.getInt(3)));
                }
                ContentValues contentValues = new ContentValues();
                for (bd bdVar : arrayList) {
                    contentValues.put("content", r.b(bdVar.b));
                    sQLiteDatabase.update(b.ao, contentValues, "event_id=?", new String[]{Long.toString(bdVar.a)});
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
            } catch (Throwable th) {
                th = th;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public void close() {
        synchronized (this) {
            super.close();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        sQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
        sQLiteDatabase.execSQL("CREATE INDEX if not exists status_idx ON events(status)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StatLogger e = au.h;
        e.debug("upgrade DB from oldVersion " + i + " to newVersion " + i2);
        if (i == 1) {
            sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
        if (i == 2) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
    }
}
