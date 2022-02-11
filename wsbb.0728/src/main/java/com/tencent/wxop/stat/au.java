package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.meiqia.core.bean.MQInquireForm;
import com.stub.StubApp;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.l;
import com.tencent.wxop.stat.common.r;
import com.umeng.analytics.pro.b;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class au {
    /* access modifiers changed from: private */
    public static StatLogger h = l.b();
    private static Context i = null;
    private static au j = null;
    volatile int a = 0;
    a b = null;
    private bc c = null;
    private bc d = null;
    private e e = null;
    private String f = "";
    private String g = "";
    private int k = 0;
    private ConcurrentHashMap<com.tencent.wxop.stat.event.e, String> l = null;
    private boolean m = false;
    private HashMap<String, String> n = new HashMap<>();

    private au(Context context) {
        try {
            this.e = new e();
            i = StubApp.getOrigApplicationContext(context.getApplicationContext());
            this.l = new ConcurrentHashMap<>();
            this.f = l.p(context);
            this.g = "pri_" + l.p(context);
            this.c = new bc(i, this.f);
            this.d = new bc(i, this.g);
            a(true);
            a(false);
            f();
            b(i);
            d();
            j();
        } catch (Throwable th) {
            h.e(th);
        }
    }

    public static au a(Context context) {
        if (j == null) {
            synchronized (au.class) {
                try {
                    if (j == null) {
                        j = new au(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<au> cls = au.class;
                        throw th;
                    }
                }
            }
        }
        return j;
    }

    private String a(List<bd> list) {
        StringBuilder sb = new StringBuilder(list.size() * 3);
        sb.append("event_id in (");
        int size = list.size();
        Iterator<bd> it = list.iterator();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (it.hasNext()) {
                sb.append(it.next().a);
                if (i3 != size - 1) {
                    sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                }
                i2 = i3 + 1;
            } else {
                sb.append(")");
                return sb.toString();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    private void a(int i2, boolean z) {
        synchronized (this) {
            try {
                if (this.a > 0 && i2 > 0 && !StatServiceImpl.a()) {
                    if (StatConfig.isDebugEnable()) {
                        StatLogger statLogger = h;
                        statLogger.i("Load " + this.a + " unsent events");
                    }
                    ArrayList arrayList = new ArrayList(i2);
                    b(arrayList, i2, z);
                    if (arrayList.size() > 0) {
                        if (StatConfig.isDebugEnable()) {
                            StatLogger statLogger2 = h;
                            statLogger2.i("Peek " + arrayList.size() + " unsent events.");
                        }
                        a((List<bd>) arrayList, 2, z);
                        i.b(i).b(arrayList, new ba(this, arrayList, z));
                    }
                }
            } catch (Throwable th) {
                h.e(th);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e4 A[SYNTHETIC, Splitter:B:35:0x00e4] */
    private void a(com.tencent.wxop.stat.event.e eVar, h hVar, boolean z) {
        long j2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c(z);
            try {
                sQLiteDatabase.beginTransaction();
                if (!z && this.a > StatConfig.getMaxStoreEventCount()) {
                    h.warn("Too many events stored in db.");
                    this.a -= this.c.getWritableDatabase().delete(b.ao, "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", (String[]) null);
                }
                ContentValues contentValues = new ContentValues();
                String g2 = eVar.g();
                if (StatConfig.isDebugEnable()) {
                    h.i("insert 1 event, content:" + g2);
                }
                contentValues.put("content", r.b(g2));
                contentValues.put("send_count", "0");
                contentValues.put(MQInquireForm.KEY_STATUS, Integer.toString(1));
                contentValues.put("timestamp", Long.valueOf(eVar.c()));
                j2 = sQLiteDatabase.insert(b.ao, (String) null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th) {
                        h.e(th);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (j2 <= 0) {
            this.a++;
            if (StatConfig.isDebugEnable()) {
                h.d("directStoreEvent insert event to db, event:" + eVar.g());
            }
            if (hVar != null) {
                hVar.a();
                return;
            }
            return;
        }
        h.error((Object) "Failed to store event:" + eVar.g());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b0 A[SYNTHETIC, Splitter:B:34:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d1 A[SYNTHETIC, Splitter:B:50:0x00d1] */
    public void a(List<bd> list, int i2, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        String str;
        String str2 = null;
        synchronized (this) {
            if (list.size() != 0) {
                int b2 = b(z);
                try {
                    sQLiteDatabase = c(z);
                    if (i2 == 2) {
                        try {
                            str = "update events set status=" + i2 + ", send_count=send_count+1  where " + a(list);
                        } catch (Throwable th) {
                            th = th;
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    } else {
                        str = "update events set status=" + i2 + " where " + a(list);
                        if (this.k % 3 == 0) {
                            str2 = "delete from events where send_count>" + b2;
                        }
                        this.k++;
                    }
                    if (StatConfig.isDebugEnable()) {
                        h.i("update sql:" + str);
                    }
                    sQLiteDatabase.beginTransaction();
                    sQLiteDatabase.execSQL(str);
                    if (str2 != null) {
                        h.i("update for delete sql:" + str2);
                        sQLiteDatabase.execSQL(str2);
                        f();
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                            return;
                        } catch (Throwable th2) {
                            h.e(th2);
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    sQLiteDatabase = null;
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e5 A[SYNTHETIC, Splitter:B:47:0x00e5] */
    public void a(List<bd> list, boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (list.size() != 0) {
                if (StatConfig.isDebugEnable()) {
                    h.i("Delete " + list.size() + " events, important:" + z);
                }
                StringBuilder sb = new StringBuilder(list.size() * 3);
                sb.append("event_id in (");
                int size = list.size();
                int i2 = 0;
                for (bd bdVar : list) {
                    sb.append(bdVar.a);
                    if (i2 != size - 1) {
                        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    }
                    i2++;
                }
                sb.append(")");
                try {
                    sQLiteDatabase = c(z);
                    try {
                        sQLiteDatabase.beginTransaction();
                        int delete = sQLiteDatabase.delete(b.ao, sb.toString(), (String[]) null);
                        if (StatConfig.isDebugEnable()) {
                            h.i("delete " + size + " event " + sb.toString() + ", success delete:" + delete);
                        }
                        this.a -= delete;
                        sQLiteDatabase.setTransactionSuccessful();
                        f();
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                                return;
                            } catch (Throwable th) {
                                h.e(th);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            h.e(th);
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                    return;
                                } catch (Throwable th3) {
                                    h.e(th3);
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th4) {
                            th = th4;
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Throwable th5) {
                                    h.e(th5);
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068 A[SYNTHETIC, Splitter:B:21:0x0068] */
    private void a(boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c(z);
            try {
                sQLiteDatabase.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MQInquireForm.KEY_STATUS, 1);
                int update = sQLiteDatabase.update(b.ao, contentValues, "status=?", new String[]{Long.toString(2)});
                if (StatConfig.isDebugEnable()) {
                    StatLogger statLogger = h;
                    statLogger.i("update " + update + " unsent events.");
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th) {
                        h.e(th);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    h.e(th);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th5) {
                    h.e(th5);
                }
            }
            throw th;
        }
    }

    private int b(boolean z) {
        return !z ? StatConfig.getMaxSendRetryCount() : StatConfig.getMaxImportantDataSendRetryCount();
    }

    public static au b() {
        return j;
    }

    /* access modifiers changed from: private */
    public void b(int i2, boolean z) {
        int g2 = i2 == -1 ? !z ? g() : h() : i2;
        if (g2 > 0) {
            int sendPeriodMinutes = StatConfig.getSendPeriodMinutes() * 60 * StatConfig.getNumEventsCommitPerSec();
            if (g2 > sendPeriodMinutes && sendPeriodMinutes > 0) {
                g2 = sendPeriodMinutes;
            }
            int a2 = StatConfig.a();
            int i3 = g2 / a2;
            int i4 = g2 % a2;
            if (StatConfig.isDebugEnable()) {
                StatLogger statLogger = h;
                statLogger.i("sentStoreEventsByDb sendNumbers=" + g2 + ",important=" + z + ",maxSendNumPerFor1Period=" + sendPeriodMinutes + ",maxCount=" + i3 + ",restNumbers=" + i4);
            }
            for (int i5 = 0; i5 < i3; i5++) {
                a(a2, z);
            }
            if (i4 > 0) {
                a(i4, z);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    public void b(com.tencent.wxop.stat.event.e eVar, h hVar, boolean z, boolean z2) {
        synchronized (this) {
            if (StatConfig.getMaxStoreEventCount() > 0) {
                if (StatConfig.m <= 0 || z || z2) {
                    a(eVar, hVar, z);
                } else if (StatConfig.m > 0) {
                    if (StatConfig.isDebugEnable()) {
                        StatLogger statLogger = h;
                        statLogger.i("cacheEventsInMemory.size():" + this.l.size() + ",numEventsCachedInMemory:" + StatConfig.m + ",numStoredEvents:" + this.a);
                        StatLogger statLogger2 = h;
                        StringBuilder sb = new StringBuilder("cache event:");
                        sb.append(eVar.g());
                        statLogger2.i(sb.toString());
                    }
                    this.l.put(eVar, "");
                    if (this.l.size() >= StatConfig.m) {
                        i();
                    }
                    if (hVar != null) {
                        if (this.l.size() > 0) {
                            i();
                        }
                        hVar.a();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f2 A[SYNTHETIC, Splitter:B:41:0x00f2] */
    public void b(f fVar) {
        Cursor cursor;
        boolean z;
        long insert;
        Cursor cursor2 = null;
        synchronized (this) {
            try {
                String a2 = fVar.a();
                String a3 = l.a(a2);
                ContentValues contentValues = new ContentValues();
                contentValues.put("content", fVar.b.toString());
                contentValues.put("md5sum", a3);
                fVar.c = a3;
                contentValues.put("version", Integer.valueOf(fVar.d));
                cursor = this.c.getReadableDatabase().query("config", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                while (true) {
                    try {
                        if (cursor.moveToNext()) {
                            if (cursor.getInt(0) == fVar.a) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        this.c.getWritableDatabase().endTransaction();
                        throw th;
                    }
                }
                this.c.getWritableDatabase().beginTransaction();
                if (true == z) {
                    insert = (long) this.c.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(fVar.a)});
                } else {
                    contentValues.put("type", Integer.valueOf(fVar.a));
                    insert = this.c.getWritableDatabase().insert("config", (String) null, contentValues);
                }
                if (insert == -1) {
                    h.e((Object) "Failed to store cfg:" + a2);
                } else {
                    h.d("Sucessed to store cfg:" + a2);
                }
                this.c.getWritableDatabase().setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
                try {
                    this.c.getWritableDatabase().endTransaction();
                    return;
                } catch (Exception e2) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursor2;
                if (cursor != null) {
                }
                this.c.getWritableDatabase().endTransaction();
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0090  */
    private void b(List<bd> list, int i2, boolean z) {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3 = null;
        try {
            cursor2 = d(z).query(b.ao, (String[]) null, "status=?", new String[]{Integer.toString(1)}, (String) null, (String) null, (String) null, Integer.toString(i2));
            while (cursor2.moveToNext()) {
                try {
                    long j2 = cursor2.getLong(0);
                    String string = cursor2.getString(1);
                    if (!StatConfig.g) {
                        string = r.a(string);
                    }
                    int i3 = cursor2.getInt(2);
                    int i4 = cursor2.getInt(3);
                    bd bdVar = new bd(j2, string, i3, i4);
                    if (StatConfig.isDebugEnable()) {
                        h.i("peek event, id=" + j2 + ",send_count=" + i4 + ",timestamp=" + cursor2.getLong(4));
                    }
                    list.add(bdVar);
                } catch (Throwable th) {
                    th = th;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor3;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    private SQLiteDatabase c(boolean z) {
        return (!z ? this.c : this.d).getWritableDatabase();
    }

    private SQLiteDatabase d(boolean z) {
        return (!z ? this.c : this.d).getReadableDatabase();
    }

    private void f() {
        this.a = g() + h();
    }

    private int g() {
        return (int) DatabaseUtils.queryNumEntries(this.c.getReadableDatabase(), b.ao);
    }

    private int h() {
        return (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), b.ao);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r1.endTransaction();
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0129, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x012a, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0130, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0131, code lost:
        h.e(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00db A[Catch:{ Throwable -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0122 A[SYNTHETIC, Splitter:B:51:0x0122] */
    public void i() {
        StatLogger statLogger;
        SQLiteDatabase sQLiteDatabase = null;
        if (!this.m) {
            synchronized (this.l) {
                if (this.l.size() != 0) {
                    this.m = true;
                    if (StatConfig.isDebugEnable()) {
                        StatLogger statLogger2 = h;
                        statLogger2.i("insert " + this.l.size() + " events ,numEventsCachedInMemory:" + StatConfig.m + ",numStoredEvents:" + this.a);
                    }
                    try {
                        sQLiteDatabase = this.c.getWritableDatabase();
                        try {
                            sQLiteDatabase.beginTransaction();
                            Iterator<Map.Entry<com.tencent.wxop.stat.event.e, String>> it = this.l.entrySet().iterator();
                            while (it.hasNext()) {
                                com.tencent.wxop.stat.event.e eVar = (com.tencent.wxop.stat.event.e) it.next().getKey();
                                ContentValues contentValues = new ContentValues();
                                String g2 = eVar.g();
                                if (StatConfig.isDebugEnable()) {
                                    StatLogger statLogger3 = h;
                                    statLogger3.i("insert content:" + g2);
                                }
                                contentValues.put("content", r.b(g2));
                                contentValues.put("send_count", "0");
                                contentValues.put(MQInquireForm.KEY_STATUS, Integer.toString(1));
                                contentValues.put("timestamp", Long.valueOf(eVar.c()));
                                sQLiteDatabase.insert(b.ao, (String) null, contentValues);
                                it.remove();
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                    f();
                                } catch (Throwable th) {
                                    th = th;
                                    statLogger = h;
                                    statLogger.e(th);
                                    this.m = false;
                                    if (StatConfig.isDebugEnable()) {
                                    }
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            if (sQLiteDatabase != null) {
                            }
                            throw th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    this.m = false;
                    if (StatConfig.isDebugEnable()) {
                        StatLogger statLogger4 = h;
                        statLogger4.i("after insert, cacheEventsInMemory.size():" + this.l.size() + ",numEventsCachedInMemory:" + StatConfig.m + ",numStoredEvents:" + this.a);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    private void j() {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = this.c.getReadableDatabase().query("keyvalues", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            while (cursor.moveToNext()) {
                try {
                    this.n.put(cursor.getString(0), cursor.getString(1));
                } catch (Throwable th) {
                    th = th;
                    try {
                        h.e(th);
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public int a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.e.a(new bb(this, i2));
    }

    /* access modifiers changed from: package-private */
    public void a(com.tencent.wxop.stat.event.e eVar, h hVar, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new ay(this, eVar, hVar, z, z2));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(f fVar) {
        if (fVar != null) {
            this.e.a(new az(this, fVar));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<bd> list, int i2, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new av(this, list, i2, z, z2));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<bd> list, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new aw(this, list, z, z2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0093, code lost:
        if (r4.length() < 11) goto L_0x0095;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f7 A[Catch:{ Throwable -> 0x0205, all -> 0x0201 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010c A[Catch:{ Throwable -> 0x0205, all -> 0x0201 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a5 A[SYNTHETIC, Splitter:B:70:0x01a5] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01be A[SYNTHETIC, Splitter:B:83:0x01be] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01da A[Catch:{ Throwable -> 0x0205, all -> 0x0201 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:73:0x01b1=Splitter:B:73:0x01b1, B:64:0x019b=Splitter:B:64:0x019b} */
    public a b(Context context) {
        Cursor cursor;
        Throwable th;
        StatLogger statLogger;
        a aVar;
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z2;
        String c2;
        boolean z3;
        synchronized (this) {
            if (this.b != null) {
                aVar = this.b;
            } else {
                try {
                    this.c.getWritableDatabase().beginTransaction();
                    if (StatConfig.isDebugEnable()) {
                        try {
                            h.i("try to load user info from db.");
                        } catch (Throwable th2) {
                            cursor = null;
                            th = th2;
                            if (cursor != null) {
                                try {
                                    cursor.close();
                                } catch (Throwable th3) {
                                    h.e(th3);
                                    throw th;
                                }
                            }
                            this.c.getWritableDatabase().endTransaction();
                            throw th;
                        }
                    }
                    Cursor query = this.c.getReadableDatabase().query("user", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
                    try {
                        if (query.moveToNext()) {
                            String string = query.getString(0);
                            String a2 = r.a(string);
                            int i2 = query.getInt(1);
                            String string2 = query.getString(2);
                            long j2 = query.getLong(3);
                            long currentTimeMillis = System.currentTimeMillis() / 1000;
                            int i3 = (i2 == 1 || l.a(j2 * 1000).equals(l.a(1000 * currentTimeMillis))) ? i2 : 1;
                            int i4 = !string2.equals(l.l(context)) ? i3 | 2 : i3;
                            String[] split = a2.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                            if (split == null || split.length <= 0) {
                                String b2 = l.b(context);
                                str2 = b2;
                                str3 = b2;
                            } else {
                                String str5 = split[0];
                                if (str5 != null) {
                                }
                                str2 = r.a(context);
                                if (str2 != null && str2.length() > 10) {
                                    str3 = a2;
                                }
                                z2 = false;
                                str4 = str5;
                                if (split == null || split.length < 2) {
                                    c2 = l.c(context);
                                    if (c2 == null || c2.length() <= 0) {
                                        z3 = z2;
                                    } else {
                                        a2 = str4 + ListUtils.DEFAULT_JOIN_SEPARATOR + c2;
                                        z3 = true;
                                    }
                                } else {
                                    c2 = split[1];
                                    a2 = str4 + ListUtils.DEFAULT_JOIN_SEPARATOR + c2;
                                    z3 = z2;
                                }
                                this.b = new a(str4, c2, i4);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, r.b(a2));
                                contentValues.put("user_type", Integer.valueOf(i4));
                                contentValues.put("app_ver", l.l(context));
                                contentValues.put("ts", Long.valueOf(currentTimeMillis));
                                if (z3) {
                                    this.c.getWritableDatabase().update("user", contentValues, "uid=?", new String[]{string});
                                }
                                if (i4 == i2) {
                                    this.c.getWritableDatabase().replace("user", (String) null, contentValues);
                                    z = true;
                                } else {
                                    z = true;
                                }
                            }
                            z2 = true;
                            str4 = str2;
                            a2 = str3;
                            if (split == null || split.length < 2) {
                            }
                            this.b = new a(str4, c2, i4);
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, r.b(a2));
                            contentValues2.put("user_type", Integer.valueOf(i4));
                            contentValues2.put("app_ver", l.l(context));
                            contentValues2.put("ts", Long.valueOf(currentTimeMillis));
                            if (z3) {
                            }
                            if (i4 == i2) {
                            }
                        } else {
                            z = false;
                        }
                        if (!z) {
                            String b3 = l.b(context);
                            String c3 = l.c(context);
                            if (c3 == null || c3.length() <= 0) {
                                str = b3;
                            } else {
                                str = b3 + ListUtils.DEFAULT_JOIN_SEPARATOR + c3;
                            }
                            String l2 = l.l(context);
                            ContentValues contentValues3 = new ContentValues();
                            contentValues3.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, r.b(str));
                            contentValues3.put("user_type", 0);
                            contentValues3.put("app_ver", l2);
                            contentValues3.put("ts", Long.valueOf(System.currentTimeMillis() / 1000));
                            this.c.getWritableDatabase().insert("user", (String) null, contentValues3);
                            this.b = new a(b3, c3, 0);
                        }
                        this.c.getWritableDatabase().setTransactionSuccessful();
                        if (query != null) {
                            try {
                                query.close();
                            } catch (Throwable th4) {
                                th = th4;
                                statLogger = h;
                                statLogger.e(th);
                                aVar = this.b;
                                return aVar;
                            }
                        }
                        this.c.getWritableDatabase().endTransaction();
                    } catch (Throwable th5) {
                        th = th5;
                        cursor = query;
                        th = th;
                        if (cursor != null) {
                        }
                        this.c.getWritableDatabase().endTransaction();
                        throw th;
                    }
                } catch (Throwable th6) {
                    cursor = null;
                    th = th6;
                    if (cursor != null) {
                    }
                    this.c.getWritableDatabase().endTransaction();
                    throw th;
                }
                aVar = this.b;
            }
        }
        return aVar;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (StatConfig.isEnableStatService()) {
            try {
                this.e.a(new ax(this));
            } catch (Throwable th) {
                h.e(th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e  */
    public void d() {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = this.c.getReadableDatabase().query("config", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            while (cursor.moveToNext()) {
                try {
                    int i2 = cursor.getInt(0);
                    String string = cursor.getString(1);
                    String string2 = cursor.getString(2);
                    int i3 = cursor.getInt(3);
                    f fVar = new f(i2);
                    fVar.a = i2;
                    fVar.b = new JSONObject(string);
                    fVar.c = string2;
                    fVar.d = i3;
                    StatConfig.a(i, fVar);
                } catch (Throwable th) {
                    th = th;
                    try {
                        h.e(th);
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }
}
