package com.meiqia.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.meiqia.core.a.c;
import com.meiqia.core.a.i;
import com.meiqia.core.bean.MQClient;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.meiqiasdk.activity.MQCollectInfoActivity;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class f {
    private static f a;
    private e b;
    private i c;
    private AtomicInteger d = new AtomicInteger();
    private SQLiteDatabase e;

    private f(Context context) {
        this.b = new e(context);
        this.c = new i(context);
    }

    private MQClient a(Cursor cursor) {
        MQClient mQClient = new MQClient();
        mQClient.setAppKey(cursor.getString(cursor.getColumnIndex("appkey")));
        mQClient.setAESKey(cursor.getString(cursor.getColumnIndex("aeskey")));
        mQClient.setBindUserId(cursor.getString(cursor.getColumnIndex("customized_id")));
        mQClient.setBrowserId(cursor.getString(cursor.getColumnIndex("browser_id")));
        mQClient.setEnterpriseId(cursor.getString(cursor.getColumnIndex("enterprise_id")));
        mQClient.setTrackId(cursor.getString(cursor.getColumnIndex("track_id")));
        mQClient.setVisitId(cursor.getString(cursor.getColumnIndex("visit_id")));
        mQClient.setVisitPageId(cursor.getString(cursor.getColumnIndex("visit_page_id")));
        return mQClient;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: com.meiqia.core.bean.MQClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.meiqia.core.bean.MQClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: com.meiqia.core.bean.MQClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.meiqia.core.bean.MQClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r7v11, types: [com.meiqia.core.bean.MQClient] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.meiqia.core.bean.MQClient a(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7, boolean r8) {
        /*
            r5 = this;
            com.meiqia.core.a.i r0 = r5.c
            java.lang.String r0 = r0.a()
            if (r8 == 0) goto L_0x000b
            java.lang.String r8 = "SELECT * FROM mq_client WHERE customized_id=? and appkey=?"
            goto L_0x000d
        L_0x000b:
            java.lang.String r8 = "SELECT * FROM mq_client WHERE track_id=? and appkey=?"
        L_0x000d:
            r1 = 2
            r2 = 0
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ Exception -> 0x003b }
            r3 = 0
            r1[r3] = r7     // Catch:{ Exception -> 0x003b }
            r7 = 1
            r1[r7] = r0     // Catch:{ Exception -> 0x003b }
            android.database.Cursor r6 = r6.rawQuery(r8, r1)     // Catch:{ Exception -> 0x003b }
        L_0x001b:
            boolean r7 = r6.moveToNext()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r7 == 0) goto L_0x0027
            com.meiqia.core.bean.MQClient r7 = r5.a((android.database.Cursor) r6)     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            r2 = r7
            goto L_0x001b
        L_0x0027:
            if (r6 == 0) goto L_0x002c
            r6.close()
        L_0x002c:
            r5.d()
            r6 = r2
            goto L_0x005d
        L_0x0031:
            r7 = move-exception
            r2 = r6
            goto L_0x005e
        L_0x0034:
            r7 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L_0x003d
        L_0x0039:
            r7 = move-exception
            goto L_0x005e
        L_0x003b:
            r7 = move-exception
            r6 = r2
        L_0x003d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0039 }
            r8.<init>()     // Catch:{ all -> 0x0039 }
            java.lang.String r0 = "findClientByKey error : "
            r8.append(r0)     // Catch:{ all -> 0x0039 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0039 }
            r8.append(r7)     // Catch:{ all -> 0x0039 }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x0039 }
            com.meiqia.core.a.f.a((java.lang.String) r7)     // Catch:{ all -> 0x0039 }
            if (r2 == 0) goto L_0x005a
            r2.close()
        L_0x005a:
            r5.d()
        L_0x005d:
            return r6
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.close()
        L_0x0063:
            r5.d()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.f.a(android.database.sqlite.SQLiteDatabase, java.lang.String, boolean):com.meiqia.core.bean.MQClient");
    }

    public static f a(Context context) {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f(context);
                }
            }
        }
        return a;
    }

    private void a(SQLiteDatabase sQLiteDatabase, MQClient mQClient) {
        ContentValues contentValues = new ContentValues();
        a(mQClient, contentValues);
        sQLiteDatabase.insert("mq_client", (String) null, contentValues);
    }

    private void a(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        ContentValues contentValues = new ContentValues();
        a(mQMessage, contentValues);
        sQLiteDatabase.insert("mq_message", (String) null, contentValues);
    }

    private void a(MQClient mQClient, ContentValues contentValues) {
        contentValues.put("appkey", mQClient.getAppKey());
        contentValues.put("aeskey", mQClient.getAESKey());
        contentValues.put("browser_id", mQClient.getBrowserId());
        contentValues.put("customized_id", mQClient.getBindUserId());
        contentValues.put("enterprise_id", mQClient.getEnterpriseId());
        contentValues.put("track_id", mQClient.getTrackId());
        contentValues.put("visit_id", mQClient.getVisitId());
        contentValues.put("visit_page_id", mQClient.getVisitPageId());
    }

    private void a(MQMessage mQMessage, ContentValues contentValues) {
        contentValues.put("id", Long.valueOf(mQMessage.getId()));
        contentValues.put(MQCollectInfoActivity.AGENT_ID, mQMessage.getAgent_id());
        contentValues.put("content", mQMessage.getContent());
        contentValues.put("content_type", mQMessage.getContent_type());
        contentValues.put("conversation_id", Long.valueOf(mQMessage.getConversation_id()));
        contentValues.put("created_on", Long.valueOf(mQMessage.getCreated_on()));
        contentValues.put("enterprise_id", Long.valueOf(mQMessage.getEnterprise_id()));
        contentValues.put("from_type", mQMessage.getFrom_type());
        contentValues.put("track_id", mQMessage.getTrack_id());
        contentValues.put("type", mQMessage.getType());
        contentValues.put("avatar", mQMessage.getAvatar());
        contentValues.put("isRead", Boolean.valueOf(mQMessage.is_read()));
        contentValues.put(MQInquireForm.KEY_STATUS, mQMessage.getStatus());
        contentValues.put("agent_nickname", mQMessage.getAgent_nickname());
        contentValues.put("media_url", mQMessage.getMedia_url());
        contentValues.put("extra", mQMessage.getExtra());
        if (mQMessage.isAlreadyFeedback()) {
            contentValues.put("is_already_feedback", Boolean.valueOf(mQMessage.isAlreadyFeedback()));
        }
    }

    private MQMessage b(Cursor cursor) {
        MQMessage mQMessage = new MQMessage();
        mQMessage.setId(cursor.getLong(cursor.getColumnIndex("id")));
        mQMessage.setAgent_id(cursor.getString(cursor.getColumnIndex(MQCollectInfoActivity.AGENT_ID)));
        mQMessage.setContent(cursor.getString(cursor.getColumnIndex("content")));
        mQMessage.setContent_type(cursor.getString(cursor.getColumnIndex("content_type")));
        mQMessage.setTrack_id(cursor.getString(cursor.getColumnIndex("track_id")));
        mQMessage.setAgent_nickname(cursor.getString(cursor.getColumnIndex("agent_nickname")));
        mQMessage.setConversation_id((long) cursor.getInt(cursor.getColumnIndex("conversation_id")));
        mQMessage.setCreated_on(cursor.getLong(cursor.getColumnIndex("created_on")));
        mQMessage.setEnterprise_id((long) cursor.getInt(cursor.getColumnIndex("enterprise_id")));
        mQMessage.setFrom_type(cursor.getString(cursor.getColumnIndex("from_type")));
        mQMessage.setStatus(cursor.getString(cursor.getColumnIndex(MQInquireForm.KEY_STATUS)));
        mQMessage.setType(cursor.getString(cursor.getColumnIndex("type")));
        mQMessage.setAvatar(cursor.getString(cursor.getColumnIndex("avatar")));
        mQMessage.setMedia_url(cursor.getString(cursor.getColumnIndex("media_url")));
        mQMessage.setExtra(cursor.getString(cursor.getColumnIndex("extra")));
        c.a(mQMessage);
        boolean z = false;
        mQMessage.setAlreadyFeedback(cursor.getInt(cursor.getColumnIndex("is_already_feedback")) != 0);
        if (cursor.getInt(cursor.getColumnIndex("isRead")) != 0) {
            z = true;
        }
        mQMessage.setIs_read(z);
        return mQMessage;
    }

    private void b(SQLiteDatabase sQLiteDatabase, MQClient mQClient) {
        String[] strArr = {mQClient.getTrackId() + ""};
        ContentValues contentValues = new ContentValues();
        a(mQClient, contentValues);
        sQLiteDatabase.update("mq_client", contentValues, "track_id=?", strArr);
    }

    private void b(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        if (d(sQLiteDatabase, mQMessage)) {
            c(sQLiteDatabase, mQMessage);
        } else {
            a(sQLiteDatabase, mQMessage);
        }
    }

    private synchronized SQLiteDatabase c() {
        if (this.d.incrementAndGet() == 1) {
            this.e = this.b.getWritableDatabase();
        }
        return this.e;
    }

    private void c(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        try {
            String[] strArr = {mQMessage.getId() + ""};
            ContentValues contentValues = new ContentValues();
            a(mQMessage, contentValues);
            sQLiteDatabase.update("mq_message", contentValues, "id=?", strArr);
        } catch (Exception unused) {
            Log.d("meiqia", "updateMessage(SQLiteDatabase db, MQMessage message) error");
        }
    }

    private boolean c(MQMessage mQMessage) {
        return TextUtils.equals("reply", mQMessage.getSub_type()) || TextUtils.equals("redirect", mQMessage.getSub_type());
    }

    private synchronized void d() {
        if (this.d.decrementAndGet() == 0 && this.e.isOpen()) {
            this.e.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean d(android.database.sqlite.SQLiteDatabase r7, com.meiqia.core.bean.MQMessage r8) {
        /*
            r6 = this;
            java.lang.String r0 = "sdk"
            java.lang.String r1 = r8.getType()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003d
            java.lang.String r0 = r8.getExtra()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x003d
            java.lang.String r0 = r8.getExtra()
            boolean r0 = android.text.TextUtils.isDigitsOnly(r0)
            if (r0 == 0) goto L_0x003d
            java.lang.String r8 = r8.getExtra()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SELECT * FROM "
            r0.append(r1)
            java.lang.String r1 = r6.b()
            r0.append(r1)
            java.lang.String r1 = " WHERE "
            r0.append(r1)
            java.lang.String r1 = "extra"
            goto L_0x006a
        L_0x003d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            long r1 = r8.getId()
            r0.append(r1)
            java.lang.String r8 = ""
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SELECT * FROM "
            r0.append(r1)
            java.lang.String r1 = r6.b()
            r0.append(r1)
            java.lang.String r1 = " WHERE "
            r0.append(r1)
            java.lang.String r1 = "id"
        L_0x006a:
            r0.append(r1)
            java.lang.String r1 = "=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r5.<init>()     // Catch:{ Exception -> 0x00aa }
            r5.append(r8)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r8 = ""
            r5.append(r8)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r8 = r5.toString()     // Catch:{ Exception -> 0x00aa }
            r4[r3] = r8     // Catch:{ Exception -> 0x00aa }
            android.database.Cursor r7 = r7.rawQuery(r0, r4)     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x00a2
            boolean r8 = r7.moveToFirst()     // Catch:{ Exception -> 0x009f, all -> 0x009c }
            if (r8 == 0) goto L_0x00a2
            r3 = 1
            goto L_0x00a2
        L_0x009c:
            r8 = move-exception
            r1 = r7
            goto L_0x00c9
        L_0x009f:
            r8 = move-exception
            r1 = r7
            goto L_0x00ab
        L_0x00a2:
            if (r7 == 0) goto L_0x00c8
            r7.close()
            goto L_0x00c8
        L_0x00a8:
            r8 = move-exception
            goto L_0x00c9
        L_0x00aa:
            r8 = move-exception
        L_0x00ab:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            r7.<init>()     // Catch:{ all -> 0x00a8 }
            java.lang.String r0 = "findMessage() : "
            r7.append(r0)     // Catch:{ all -> 0x00a8 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00a8 }
            r7.append(r8)     // Catch:{ all -> 0x00a8 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00a8 }
            com.meiqia.core.a.f.a((java.lang.String) r7)     // Catch:{ all -> 0x00a8 }
            if (r1 == 0) goto L_0x00c8
            r1.close()
        L_0x00c8:
            return r3
        L_0x00c9:
            if (r1 == 0) goto L_0x00ce
            r1.close()
        L_0x00ce:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.f.d(android.database.sqlite.SQLiteDatabase, com.meiqia.core.bean.MQMessage):boolean");
    }

    public MQClient a(String str) {
        return a(c(), str, false);
    }

    public void a() {
        try {
            c().execSQL("DELETE FROM mq_message");
        } catch (Exception unused) {
            Log.d("meiqia", "deleteAllMessage error");
        } catch (Throwable th) {
            d();
            throw th;
        }
        d();
    }

    public void a(long j) {
        SQLiteDatabase c2 = c();
        try {
            c2.delete("mq_message", "id=?", new String[]{j + ""});
        } catch (Exception unused) {
            Log.d("meiqia", "deleteMessage error");
        } catch (Throwable th) {
            d();
            throw th;
        }
        d();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00a0, code lost:
        if (r7 != null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a2, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00a5, code lost:
        d();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d2, code lost:
        if (r7 != null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d5, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r7, int r9, com.meiqia.core.callback.OnGetMessageListCallback r10) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r6.c()
            com.meiqia.core.bean.MQClient r2 = com.meiqia.core.d.a
            java.lang.String r2 = r2.getTrackId()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "select * from "
            r3.append(r4)
            java.lang.String r4 = r6.b()
            r3.append(r4)
            java.lang.String r4 = " where "
            r3.append(r4)
            java.lang.String r4 = "created_on"
            r3.append(r4)
            java.lang.String r4 = " < "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = " and "
            r3.append(r7)
            java.lang.String r7 = "track_id"
            r3.append(r7)
            java.lang.String r7 = " = '"
            r3.append(r7)
            r3.append(r2)
            java.lang.String r7 = "' order by "
            r3.append(r7)
            java.lang.String r7 = "created_on"
            r3.append(r7)
            java.lang.String r7 = " DESC limit "
            r3.append(r7)
            r3.append(r9)
            java.lang.String r7 = r3.toString()
            r8 = 0
            android.database.Cursor r7 = r1.rawQuery(r7, r8)     // Catch:{ Exception -> 0x00b0, all -> 0x00ab }
        L_0x005f:
            boolean r8 = r7.moveToNext()     // Catch:{ Exception -> 0x00a9 }
            if (r8 == 0) goto L_0x0095
            com.meiqia.core.bean.MQMessage r8 = r6.b((android.database.Cursor) r7)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r9 = "sending"
            java.lang.String r1 = r8.getStatus()     // Catch:{ Exception -> 0x00a9 }
            boolean r9 = r9.equals(r1)     // Catch:{ Exception -> 0x00a9 }
            if (r9 == 0) goto L_0x007a
            java.lang.String r9 = "failed"
            r8.setStatus(r9)     // Catch:{ Exception -> 0x00a9 }
        L_0x007a:
            java.lang.String r9 = "client"
            java.lang.String r1 = r8.getFrom_type()     // Catch:{ Exception -> 0x00a9 }
            boolean r9 = android.text.TextUtils.equals(r9, r1)     // Catch:{ Exception -> 0x00a9 }
            if (r9 == 0) goto L_0x0091
            com.meiqia.core.a.i r9 = r6.c     // Catch:{ Exception -> 0x00a9 }
            com.meiqia.core.bean.MQClient r1 = com.meiqia.core.d.a     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r9 = r9.j(r1)     // Catch:{ Exception -> 0x00a9 }
            r8.setAvatar(r9)     // Catch:{ Exception -> 0x00a9 }
        L_0x0091:
            r0.add(r8)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x005f
        L_0x0095:
            com.meiqia.core.a.h r8 = new com.meiqia.core.a.h     // Catch:{ Exception -> 0x00a9 }
            r8.<init>()     // Catch:{ Exception -> 0x00a9 }
            java.util.Collections.sort(r0, r8)     // Catch:{ Exception -> 0x00a9 }
            r10.onSuccess(r0)     // Catch:{ Exception -> 0x00a9 }
            if (r7 == 0) goto L_0x00a5
        L_0x00a2:
            r7.close()
        L_0x00a5:
            r6.d()
            goto L_0x00d5
        L_0x00a9:
            r8 = move-exception
            goto L_0x00b4
        L_0x00ab:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x00d7
        L_0x00b0:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x00b4:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            r9.<init>()     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = "getMessageList(String id, int length) :"
            r9.append(r0)     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00d6 }
            r9.append(r8)     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x00d6 }
            com.meiqia.core.a.f.a((java.lang.String) r8)     // Catch:{ all -> 0x00d6 }
            r8 = 0
            java.lang.String r9 = ""
            r10.onFailure(r8, r9)     // Catch:{ all -> 0x00d6 }
            if (r7 == 0) goto L_0x00a5
            goto L_0x00a2
        L_0x00d5:
            return
        L_0x00d6:
            r8 = move-exception
        L_0x00d7:
            if (r7 == 0) goto L_0x00dc
            r7.close()
        L_0x00dc:
            r6.d()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.f.a(long, int, com.meiqia.core.callback.OnGetMessageListCallback):void");
    }

    public void a(MQClient mQClient) {
        if (mQClient == null || TextUtils.isEmpty(mQClient.getTrackId())) {
            com.meiqia.core.a.f.a("updateOrSaveClient error : client == null or id == null");
            return;
        }
        boolean z = a(mQClient.getTrackId()) != null;
        SQLiteDatabase c2 = c();
        if (z) {
            try {
                b(c2, mQClient);
            } catch (Exception e2) {
                com.meiqia.core.a.f.a("updateOrSaveClient error : " + e2.toString());
            }
        } else {
            a(c2, mQClient);
        }
    }

    public void a(MQMessage mQMessage) {
        if (!c(mQMessage)) {
            try {
                b(c(), mQMessage);
            } catch (Exception unused) {
                Log.d("meiqia", "updateOrSaveMessage(MQMessage message) error");
            } catch (Throwable th) {
                d();
                throw th;
            }
            d();
        }
    }

    public void a(MQMessage mQMessage, long j) {
        SQLiteDatabase c2 = c();
        try {
            String[] strArr = {j + ""};
            ContentValues contentValues = new ContentValues();
            a(mQMessage, contentValues);
            c2.update("mq_message", contentValues, "id=?", strArr);
        } catch (Exception unused) {
            Log.d("meiqia", "updateMessageId error");
        } catch (Throwable th) {
            d();
            throw th;
        }
        d();
    }

    public void a(List<MQMessage> list) {
        SQLiteDatabase c2 = c();
        c2.beginTransaction();
        try {
            for (MQMessage next : list) {
                if (!c(next)) {
                    if (!d(c2, next)) {
                        a(c2, next);
                    }
                }
            }
            c2.setTransactionSuccessful();
        } catch (Exception unused) {
            Log.d("meiqia", "saveMessageList error");
        } catch (Throwable th) {
            c2.endTransaction();
            d();
            throw th;
        }
        c2.endTransaction();
        d();
    }

    public MQClient b(String str) {
        return a(c(), str, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: com.meiqia.core.bean.MQMessage} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: com.meiqia.core.bean.MQMessage} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: com.meiqia.core.bean.MQMessage} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: com.meiqia.core.bean.MQMessage} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r9v9, types: [com.meiqia.core.bean.MQMessage] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.meiqia.core.bean.MQMessage b(long r8) {
        /*
            r7 = this;
            android.database.sqlite.SQLiteDatabase r0 = r7.c()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "select * from "
            r1.append(r2)
            java.lang.String r2 = r7.b()
            r1.append(r2)
            java.lang.String r2 = " where "
            r1.append(r2)
            java.lang.String r2 = "id"
            r1.append(r2)
            java.lang.String r2 = " =?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 1
            r3 = 0
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0064 }
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0064 }
            r5.<init>()     // Catch:{ Exception -> 0x0064 }
            r5.append(r8)     // Catch:{ Exception -> 0x0064 }
            java.lang.String r8 = ""
            r5.append(r8)     // Catch:{ Exception -> 0x0064 }
            java.lang.String r8 = r5.toString()     // Catch:{ Exception -> 0x0064 }
            r2[r4] = r8     // Catch:{ Exception -> 0x0064 }
            android.database.Cursor r8 = r0.rawQuery(r1, r2)     // Catch:{ Exception -> 0x0064 }
        L_0x0044:
            boolean r9 = r8.moveToNext()     // Catch:{ Exception -> 0x005d, all -> 0x005a }
            if (r9 == 0) goto L_0x0050
            com.meiqia.core.bean.MQMessage r9 = r7.b((android.database.Cursor) r8)     // Catch:{ Exception -> 0x005d, all -> 0x005a }
            r3 = r9
            goto L_0x0044
        L_0x0050:
            if (r8 == 0) goto L_0x0055
            r8.close()
        L_0x0055:
            r7.d()
            r8 = r3
            goto L_0x0086
        L_0x005a:
            r9 = move-exception
            r3 = r8
            goto L_0x0087
        L_0x005d:
            r9 = move-exception
            r6 = r3
            r3 = r8
            r8 = r6
            goto L_0x0066
        L_0x0062:
            r9 = move-exception
            goto L_0x0087
        L_0x0064:
            r9 = move-exception
            r8 = r3
        L_0x0066:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r0.<init>()     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = "getMessageList(String id, int length) : "
            r0.append(r1)     // Catch:{ all -> 0x0062 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0062 }
            r0.append(r9)     // Catch:{ all -> 0x0062 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0062 }
            com.meiqia.core.a.f.a((java.lang.String) r9)     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0083
            r3.close()
        L_0x0083:
            r7.d()
        L_0x0086:
            return r8
        L_0x0087:
            if (r3 == 0) goto L_0x008c
            r3.close()
        L_0x008c:
            r7.d()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.f.b(long):com.meiqia.core.bean.MQMessage");
    }

    public String b() {
        return "mq_message";
    }

    public void b(List<MQMessage> list) {
        SQLiteDatabase c2 = c();
        c2.beginTransaction();
        try {
            for (MQMessage b2 : list) {
                b(c2, b2);
            }
            c2.setTransactionSuccessful();
        } catch (Exception unused) {
            Log.d("meiqia", "saveMessageList error");
        } catch (Throwable th) {
            c2.endTransaction();
            d();
            throw th;
        }
        c2.endTransaction();
        d();
    }

    public boolean b(MQMessage mQMessage) {
        return d(c(), mQMessage);
    }
}
