package com.meiqia.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.meiqia.core.a.c;
import com.meiqia.core.a.h;
import com.meiqia.core.a.i;
import com.meiqia.core.bean.MQClient;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.meiqiasdk.activity.MQCollectInfoActivity;
import java.util.ArrayList;
import java.util.Collections;
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

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    private MQClient a(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        Cursor cursor;
        MQClient mQClient;
        try {
            cursor = sQLiteDatabase.rawQuery(z ? "SELECT * FROM mq_client WHERE customized_id=? and appkey=?" : "SELECT * FROM mq_client WHERE track_id=? and appkey=?", new String[]{str, this.c.a()});
            mQClient = null;
            while (cursor.moveToNext()) {
                try {
                    mQClient = a(cursor);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.meiqia.core.a.f.a("findClientByKey error : " + e.toString());
                        if (cursor != null) {
                        }
                        d();
                        return mQClient;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        d();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    d();
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            d();
        } catch (Exception e3) {
            e = e3;
            mQClient = null;
            cursor = null;
            com.meiqia.core.a.f.a("findClientByKey error : " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            d();
            return mQClient;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            d();
            throw th;
        }
        return mQClient;
    }

    public static f a(Context context) {
        if (a == null) {
            synchronized (f.class) {
                try {
                    if (a == null) {
                        a = new f(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<f> cls = f.class;
                        throw th;
                    }
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
        boolean z = true;
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
        mQMessage.setAlreadyFeedback(cursor.getInt(cursor.getColumnIndex("is_already_feedback")) != 0);
        if (cursor.getInt(cursor.getColumnIndex("isRead")) == 0) {
            z = false;
        }
        mQMessage.setIs_read(z);
        return mQMessage;
    }

    private void b(SQLiteDatabase sQLiteDatabase, MQClient mQClient) {
        ContentValues contentValues = new ContentValues();
        a(mQClient, contentValues);
        sQLiteDatabase.update("mq_client", contentValues, "track_id=?", new String[]{mQClient.getTrackId() + ""});
    }

    private void b(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        if (d(sQLiteDatabase, mQMessage)) {
            c(sQLiteDatabase, mQMessage);
        } else {
            a(sQLiteDatabase, mQMessage);
        }
    }

    private SQLiteDatabase c() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (this.d.incrementAndGet() == 1) {
                this.e = this.b.getWritableDatabase();
            }
            sQLiteDatabase = this.e;
        }
        return sQLiteDatabase;
    }

    private void c(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        try {
            ContentValues contentValues = new ContentValues();
            a(mQMessage, contentValues);
            sQLiteDatabase.update("mq_message", contentValues, "id=?", new String[]{mQMessage.getId() + ""});
        } catch (Exception e2) {
            Log.d("meiqia", "updateMessage(SQLiteDatabase db, MQMessage message) error");
        }
    }

    private boolean c(MQMessage mQMessage) {
        return TextUtils.equals("reply", mQMessage.getSub_type()) || TextUtils.equals("redirect", mQMessage.getSub_type());
    }

    private void d() {
        synchronized (this) {
            if (this.d.decrementAndGet() == 0 && this.e.isOpen()) {
                this.e.close();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    private boolean d(SQLiteDatabase sQLiteDatabase, MQMessage mQMessage) {
        String str;
        StringBuilder sb;
        String str2;
        Cursor cursor;
        boolean z;
        Cursor cursor2 = null;
        if (!MQMessage.TYPE_SDK.equals(mQMessage.getType()) || TextUtils.isEmpty(mQMessage.getExtra()) || !TextUtils.isDigitsOnly(mQMessage.getExtra())) {
            str = mQMessage.getId() + "";
            sb = new StringBuilder();
            sb.append("SELECT * FROM ");
            sb.append(b());
            sb.append(" WHERE ");
            str2 = "id";
        } else {
            str = mQMessage.getExtra();
            sb = new StringBuilder();
            sb.append("SELECT * FROM ");
            sb.append(b());
            sb.append(" WHERE ");
            str2 = "extra";
        }
        sb.append(str2);
        sb.append("=?");
        try {
            cursor = sQLiteDatabase.rawQuery(sb.toString(), new String[]{str + ""});
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        z = true;
                        if (cursor != null) {
                            return z;
                        }
                        cursor.close();
                        return z;
                    }
                } catch (Exception e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            z = false;
            if (cursor != null) {
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
            }
            throw th;
        }
        try {
            com.meiqia.core.a.f.a("findMessage() : " + e.toString());
            if (cursor == null) {
                return false;
            }
            cursor.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public MQClient a(String str) {
        return a(c(), str, false);
    }

    public void a() {
        try {
            c().execSQL("DELETE FROM mq_message");
        } catch (Exception e2) {
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
        } catch (Exception e2) {
            Log.d("meiqia", "deleteMessage error");
        } catch (Throwable th) {
            d();
            throw th;
        }
        d();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b5, code lost:
        if (r1 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b7, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ba, code lost:
        d();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00bd, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c9, code lost:
        if (r1 != null) goto L_0x00b7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00cf  */
    public void a(long j, int i, OnGetMessageListCallback onGetMessageListCallback) {
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase c2 = c();
        String trackId = d.a.getTrackId();
        try {
            cursor = c2.rawQuery("select * from " + b() + " where " + "created_on" + " < " + j + " and " + "track_id" + " = '" + trackId + "' order by " + "created_on" + " DESC limit " + i, (String[]) null);
            while (cursor.moveToNext()) {
                try {
                    MQMessage b2 = b(cursor);
                    if ("sending".equals(b2.getStatus())) {
                        b2.setStatus("failed");
                    }
                    if (TextUtils.equals("client", b2.getFrom_type())) {
                        b2.setAvatar(this.c.j(d.a));
                    }
                    arrayList.add(b2);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.meiqia.core.a.f.a("getMessageList(String id, int length) :" + e.toString());
                        onGetMessageListCallback.onFailure(0, "");
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        d();
                        throw th;
                    }
                }
            }
            Collections.sort(arrayList, new h());
            onGetMessageListCallback.onSuccess(arrayList);
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            d();
            throw th;
        }
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
            } catch (Exception e2) {
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
            ContentValues contentValues = new ContentValues();
            a(mQMessage, contentValues);
            c2.update("mq_message", contentValues, "id=?", new String[]{j + ""});
        } catch (Exception e2) {
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
                if (!c(next) && !d(c2, next)) {
                    a(c2, next);
                }
            }
            c2.setTransactionSuccessful();
        } catch (Exception e2) {
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

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081  */
    public MQMessage b(long j) {
        Cursor cursor;
        MQMessage mQMessage;
        Cursor cursor2 = null;
        try {
            cursor = c().rawQuery("select * from " + b() + " where " + "id" + " =?", new String[]{j + ""});
            mQMessage = null;
            while (cursor.moveToNext()) {
                try {
                    mQMessage = b(cursor);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.meiqia.core.a.f.a("getMessageList(String id, int length) : " + e.toString());
                        if (cursor != null) {
                        }
                        d();
                        return mQMessage;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        cursor = cursor2;
                        if (cursor != null) {
                        }
                        d();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    d();
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            d();
        } catch (Exception e3) {
            e = e3;
            mQMessage = null;
            cursor = null;
            com.meiqia.core.a.f.a("getMessageList(String id, int length) : " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            d();
            return mQMessage;
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            d();
            throw th;
        }
        return mQMessage;
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
        } catch (Exception e2) {
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
