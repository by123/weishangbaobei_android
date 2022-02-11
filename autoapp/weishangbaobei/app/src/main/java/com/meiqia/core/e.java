package com.meiqia.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002e, code lost:
        com.meiqia.core.a.f.b("升级数据库   5 => 6");
        r3.execSQL("ALTER TABLE mq_message ADD COLUMN is_already_feedback INTEGER");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
        com.meiqia.core.a.f.b("升级数据库   6 => 7");
        d(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0042, code lost:
        com.meiqia.core.a.f.b("升级数据库   7 => 8");
        c(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004b, code lost:
        com.meiqia.core.a.f.b("升级数据库成功");
        r3.setTransactionSuccessful();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r3, int r4, int r5) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onUpgrade 升级数据库   "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = " => "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.meiqia.core.a.f.b(r5)
            r3.beginTransaction()
            switch(r4) {
                case 4: goto L_0x0023;
                case 5: goto L_0x002e;
                case 6: goto L_0x0039;
                case 7: goto L_0x0042;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x004b
        L_0x0023:
            java.lang.String r4 = "升级数据库   4 => 5"
            com.meiqia.core.a.f.b(r4)     // Catch:{ Exception -> 0x005a }
            java.lang.String r4 = "ALTER TABLE mq_message ADD COLUMN extra"
            r3.execSQL(r4)     // Catch:{ Exception -> 0x005a }
        L_0x002e:
            java.lang.String r4 = "升级数据库   5 => 6"
            com.meiqia.core.a.f.b(r4)     // Catch:{ Exception -> 0x005a }
            java.lang.String r4 = "ALTER TABLE mq_message ADD COLUMN is_already_feedback INTEGER"
            r3.execSQL(r4)     // Catch:{ Exception -> 0x005a }
        L_0x0039:
            java.lang.String r4 = "升级数据库   6 => 7"
            com.meiqia.core.a.f.b(r4)     // Catch:{ Exception -> 0x005a }
            r2.d(r3)     // Catch:{ Exception -> 0x005a }
        L_0x0042:
            java.lang.String r4 = "升级数据库   7 => 8"
            com.meiqia.core.a.f.b(r4)     // Catch:{ Exception -> 0x005a }
            r2.c(r3)     // Catch:{ Exception -> 0x005a }
        L_0x004b:
            java.lang.String r4 = "升级数据库成功"
            com.meiqia.core.a.f.b(r4)     // Catch:{ Exception -> 0x005a }
            r3.setTransactionSuccessful()     // Catch:{ Exception -> 0x005a }
        L_0x0054:
            r3.endTransaction()
            goto L_0x0075
        L_0x0058:
            r4 = move-exception
            goto L_0x0076
        L_0x005a:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
            r5.<init>()     // Catch:{ all -> 0x0058 }
            java.lang.String r0 = "升级数据库出错"
            r5.append(r0)     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0058 }
            r5.append(r4)     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0058 }
            com.meiqia.core.a.f.b(r4)     // Catch:{ all -> 0x0058 }
            goto L_0x0054
        L_0x0075:
            return
        L_0x0076:
            r3.endTransaction()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.e.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }
}
