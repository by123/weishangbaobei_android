package com.meiqia.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.meiqia.core.a.f;

class e extends SQLiteOpenHelper {
    private Context a;

    public e(Context context) {
        super(context, "meiqia.db", (SQLiteDatabase.CursorFactory) null, 8);
        this.a = context;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mq_message(id_ INTEGER PRIMARY KEY AUTOINCREMENT,id INTEGER,agent_id INTEGER,content TEXT,content_type TEXT,conversation_id INTEGER,created_on INTEGER,enterprise_id INTEGER,from_type TEXT,track_id TEXT,avatar TEXT,status TEXT,agent_nickname TEXT,media_url TEXT,isRead INTEGER,type TEXT,extra TEXT,is_already_feedback INTEGER)");
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mq_conversation(id INTEGER PRIMARY KEY,agent_id INTEGER,created_on TEXT,ended_by TEXT,ended_on TEXT,enterprise_id INTEGER,last_msg_content TEXT,last_updated TEXT,track_id TEXT,visit_id TEXT,last_msg_created_on TEXT)");
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mq_client(id_ INTEGER PRIMARY KEY AUTOINCREMENT,appkey TEXT,track_id TEXT,customized_id TEXT,aeskey TEXT,enterprise_id TEXT,browser_id TEXT,visit_page_id TEXT,visit_id TEXT)");
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("alter table mq_message rename to mq_message_temp");
        a(sQLiteDatabase);
        sQLiteDatabase.execSQL("INSERT INTO mq_message(id,agent_id,content,content_type,conversation_id,created_on,enterprise_id,from_type,track_id,avatar,status,agent_nickname,media_url,isRead,type,extra,is_already_feedback) SELECT id,agent_id,content,content_type,conversation_id,created_on,enterprise_id,from_type,track_id,avatar,status,agent_nickname,media_url,isRead,type,extra,is_already_feedback FROM mq_message_temp");
        sQLiteDatabase.execSQL("drop table if exists mq_message_temp");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        b(sQLiteDatabase);
        c(sQLiteDatabase);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        com.meiqia.core.a.f.b("升级数据库   5 => 6");
        r4.execSQL("ALTER TABLE mq_message ADD COLUMN is_already_feedback INTEGER");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0042, code lost:
        com.meiqia.core.a.f.b("升级数据库   6 => 7");
        d(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004a, code lost:
        com.meiqia.core.a.f.b("升级数据库   7 => 8");
        c(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        com.meiqia.core.a.f.b("升级数据库出错" + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006e, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        com.meiqia.core.a.f.b("升级数据库成功");
        r4.setTransactionSuccessful();
     */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        f.b("onUpgrade 升级数据库   " + i + " => " + i2);
        sQLiteDatabase.beginTransaction();
        switch (i) {
            case 4:
                f.b("升级数据库   4 => 5");
                sQLiteDatabase.execSQL("ALTER TABLE mq_message ADD COLUMN extra");
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        sQLiteDatabase.endTransaction();
    }
}
