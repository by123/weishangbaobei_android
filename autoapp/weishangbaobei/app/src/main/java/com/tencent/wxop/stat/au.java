package com.tencent.wxop.stat;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.stub.StubApp;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.l;
import com.umeng.analytics.pro.b;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
                if (j == null) {
                    j = new au(context);
                }
            }
        }
        return j;
    }

    private String a(List<bd> list) {
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
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(int r4, boolean r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r3.a     // Catch:{ Throwable -> 0x0076 }
            if (r0 <= 0) goto L_0x0072
            if (r4 <= 0) goto L_0x0072
            boolean r0 = com.tencent.wxop.stat.StatServiceImpl.a()     // Catch:{ Throwable -> 0x0076 }
            if (r0 == 0) goto L_0x000e
            goto L_0x0072
        L_0x000e:
            boolean r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x0076 }
            if (r0 == 0) goto L_0x002e
            com.tencent.wxop.stat.common.StatLogger r0 = h     // Catch:{ Throwable -> 0x0076 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r2 = "Load "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0076 }
            int r2 = r3.a     // Catch:{ Throwable -> 0x0076 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r2 = " unsent events"
            r1.append(r2)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0076 }
            r0.i(r1)     // Catch:{ Throwable -> 0x0076 }
        L_0x002e:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0076 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0076 }
            r3.b(r0, r4, r5)     // Catch:{ Throwable -> 0x0076 }
            int r4 = r0.size()     // Catch:{ Throwable -> 0x0076 }
            if (r4 <= 0) goto L_0x0070
            boolean r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x0076 }
            if (r4 == 0) goto L_0x005e
            com.tencent.wxop.stat.common.StatLogger r4 = h     // Catch:{ Throwable -> 0x0076 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r2 = "Peek "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0076 }
            int r2 = r0.size()     // Catch:{ Throwable -> 0x0076 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r2 = " unsent events."
            r1.append(r2)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0076 }
            r4.i(r1)     // Catch:{ Throwable -> 0x0076 }
        L_0x005e:
            r4 = 2
            r3.a((java.util.List<com.tencent.wxop.stat.bd>) r0, (int) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0076 }
            android.content.Context r4 = i     // Catch:{ Throwable -> 0x0076 }
            com.tencent.wxop.stat.i r4 = com.tencent.wxop.stat.i.b(r4)     // Catch:{ Throwable -> 0x0076 }
            com.tencent.wxop.stat.ba r1 = new com.tencent.wxop.stat.ba     // Catch:{ Throwable -> 0x0076 }
            r1.<init>(r3, r0, r5)     // Catch:{ Throwable -> 0x0076 }
            r4.b(r0, r1)     // Catch:{ Throwable -> 0x0076 }
        L_0x0070:
            monitor-exit(r3)
            return
        L_0x0072:
            monitor-exit(r3)
            return
        L_0x0074:
            r4 = move-exception
            goto L_0x007e
        L_0x0076:
            r4 = move-exception
            com.tencent.wxop.stat.common.StatLogger r5 = h     // Catch:{ all -> 0x0074 }
            r5.e((java.lang.Throwable) r4)     // Catch:{ all -> 0x0074 }
            monitor-exit(r3)
            return
        L_0x007e:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(int, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009d A[SYNTHETIC, Splitter:B:28:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e8 A[SYNTHETIC, Splitter:B:40:0x00e8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.tencent.wxop.stat.event.e r8, com.tencent.wxop.stat.h r9, boolean r10) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r7.c(r10)     // Catch:{ Throwable -> 0x0093 }
            r2.beginTransaction()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            if (r10 != 0) goto L_0x002d
            int r10 = r7.a     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            int r3 = com.tencent.wxop.stat.StatConfig.getMaxStoreEventCount()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            if (r10 <= r3) goto L_0x002d
            com.tencent.wxop.stat.common.StatLogger r10 = h     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = "Too many events stored in db."
            r10.warn(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            int r10 = r7.a     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            com.tencent.wxop.stat.bc r3 = r7.c     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r4 = "events"
            java.lang.String r5 = "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)"
            int r3 = r3.delete(r4, r5, r1)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            int r10 = r10 - r3
            r7.a = r10     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
        L_0x002d:
            android.content.ContentValues r10 = new android.content.ContentValues     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r10.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = r8.g()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            boolean r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            if (r4 == 0) goto L_0x004f
            com.tencent.wxop.stat.common.StatLogger r4 = h     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r6 = "insert 1 event, content:"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r5.append(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r4.i(r5)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
        L_0x004f:
            java.lang.String r3 = com.tencent.wxop.stat.common.r.b((java.lang.String) r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r4 = "content"
            r10.put(r4, r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = "send_count"
            java.lang.String r4 = "0"
            r10.put(r3, r4)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = "status"
            java.lang.String r4 = java.lang.Integer.toString(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r10.put(r3, r4)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = "timestamp"
            long r4 = r8.c()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r10.put(r3, r4)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            java.lang.String r3 = "events"
            long r3 = r2.insert(r3, r1, r10)     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            r2.setTransactionSuccessful()     // Catch:{ Throwable -> 0x008d, all -> 0x008b }
            if (r2 == 0) goto L_0x00a0
            r2.endTransaction()     // Catch:{ Throwable -> 0x0084 }
            goto L_0x00a0
        L_0x0084:
            r10 = move-exception
            com.tencent.wxop.stat.common.StatLogger r1 = h
            r1.e((java.lang.Throwable) r10)
            goto L_0x00a0
        L_0x008b:
            r8 = move-exception
            goto L_0x00e6
        L_0x008d:
            r10 = move-exception
            r1 = r2
            goto L_0x0094
        L_0x0090:
            r8 = move-exception
            r2 = r1
            goto L_0x00e6
        L_0x0093:
            r10 = move-exception
        L_0x0094:
            r3 = -1
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0090 }
            r2.e((java.lang.Throwable) r10)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x00a0
            r1.endTransaction()     // Catch:{ Throwable -> 0x0084 }
        L_0x00a0:
            r1 = 0
            int r10 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r10 <= 0) goto L_0x00ce
            int r10 = r7.a
            int r10 = r10 + r0
            r7.a = r10
            boolean r10 = com.tencent.wxop.stat.StatConfig.isDebugEnable()
            if (r10 == 0) goto L_0x00c8
            com.tencent.wxop.stat.common.StatLogger r10 = h
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "directStoreEvent insert event to db, event:"
            r0.<init>(r1)
            java.lang.String r8 = r8.g()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r10.d(r8)
        L_0x00c8:
            if (r9 == 0) goto L_0x00e5
            r9.a()
            return
        L_0x00ce:
            com.tencent.wxop.stat.common.StatLogger r9 = h
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "Failed to store event:"
            r10.<init>(r0)
            java.lang.String r8 = r8.g()
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r9.error((java.lang.Object) r8)
        L_0x00e5:
            return
        L_0x00e6:
            if (r2 == 0) goto L_0x00f2
            r2.endTransaction()     // Catch:{ Throwable -> 0x00ec }
            goto L_0x00f2
        L_0x00ec:
            r9 = move-exception
            com.tencent.wxop.stat.common.StatLogger r10 = h
            r10.e((java.lang.Throwable) r9)
        L_0x00f2:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(com.tencent.wxop.stat.event.e, com.tencent.wxop.stat.h, boolean):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c3 A[SYNTHETIC, Splitter:B:46:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d4 A[SYNTHETIC, Splitter:B:58:0x00d4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.util.List<com.tencent.wxop.stat.bd> r5, int r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r5.size()     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            int r0 = r4.b((boolean) r7)     // Catch:{ all -> 0x00df }
            r1 = 0
            android.database.sqlite.SQLiteDatabase r7 = r4.c(r7)     // Catch:{ Throwable -> 0x00bb }
            r2 = 2
            if (r6 != r2) goto L_0x0037
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r2 = "update events set status="
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r0.append(r6)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r6 = ", send_count=send_count+1  where "
            r0.append(r6)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r5 = r4.a((java.util.List<com.tencent.wxop.stat.bd>) r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r0.append(r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            goto L_0x006c
        L_0x0030:
            r5 = move-exception
            goto L_0x00d2
        L_0x0033:
            r5 = move-exception
            r1 = r7
            goto L_0x00bc
        L_0x0037:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r3 = "update events set status="
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r6 = " where "
            r2.append(r6)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r5 = r4.a((java.util.List<com.tencent.wxop.stat.bd>) r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r2.append(r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r5 = r2.toString()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            int r6 = r4.k     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            int r6 = r6 % 3
            if (r6 != 0) goto L_0x0066
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r1 = "delete from events where send_count>"
            r6.<init>(r1)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r6.append(r0)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r1 = r6
        L_0x0066:
            int r6 = r4.k     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            int r6 = r6 + 1
            r4.k = r6     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        L_0x006c:
            boolean r6 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            if (r6 == 0) goto L_0x0085
            com.tencent.wxop.stat.common.StatLogger r6 = h     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r2 = "update sql:"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r0.append(r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r6.i(r0)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        L_0x0085:
            r7.beginTransaction()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r7.execSQL(r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            if (r1 == 0) goto L_0x00a6
            com.tencent.wxop.stat.common.StatLogger r5 = h     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r0 = "update for delete sql:"
            r6.<init>(r0)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r6.append(r1)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r5.i(r6)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r7.execSQL(r1)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r4.f()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        L_0x00a6:
            r7.setTransactionSuccessful()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            if (r7 == 0) goto L_0x00d0
            r7.endTransaction()     // Catch:{ Throwable -> 0x00b0 }
            monitor-exit(r4)
            return
        L_0x00b0:
            r5 = move-exception
            com.tencent.wxop.stat.common.StatLogger r6 = h     // Catch:{ all -> 0x00df }
            r6.e((java.lang.Throwable) r5)     // Catch:{ all -> 0x00df }
            monitor-exit(r4)
            return
        L_0x00b8:
            r5 = move-exception
            r7 = r1
            goto L_0x00d2
        L_0x00bb:
            r5 = move-exception
        L_0x00bc:
            com.tencent.wxop.stat.common.StatLogger r6 = h     // Catch:{ all -> 0x00b8 }
            r6.e((java.lang.Throwable) r5)     // Catch:{ all -> 0x00b8 }
            if (r1 == 0) goto L_0x00d0
            r1.endTransaction()     // Catch:{ Throwable -> 0x00c8 }
            monitor-exit(r4)
            return
        L_0x00c8:
            r5 = move-exception
            com.tencent.wxop.stat.common.StatLogger r6 = h     // Catch:{ all -> 0x00df }
            r6.e((java.lang.Throwable) r5)     // Catch:{ all -> 0x00df }
            monitor-exit(r4)
            return
        L_0x00d0:
            monitor-exit(r4)
            return
        L_0x00d2:
            if (r7 == 0) goto L_0x00de
            r7.endTransaction()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x00de
        L_0x00d8:
            r6 = move-exception
            com.tencent.wxop.stat.common.StatLogger r7 = h     // Catch:{ all -> 0x00df }
            r7.e((java.lang.Throwable) r6)     // Catch:{ all -> 0x00df }
        L_0x00de:
            throw r5     // Catch:{ all -> 0x00df }
        L_0x00df:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(java.util.List, int, boolean):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d6 A[SYNTHETIC, Splitter:B:45:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e7 A[SYNTHETIC, Splitter:B:57:0x00e7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.util.List<com.tencent.wxop.stat.bd> r7, boolean r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r7.size()     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r6)
            return
        L_0x0009:
            boolean r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x002e
            com.tencent.wxop.stat.common.StatLogger r0 = h     // Catch:{ all -> 0x00f2 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = "Delete "
            r1.<init>(r2)     // Catch:{ all -> 0x00f2 }
            int r2 = r7.size()     // Catch:{ all -> 0x00f2 }
            r1.append(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = " events, important:"
            r1.append(r2)     // Catch:{ all -> 0x00f2 }
            r1.append(r8)     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f2 }
            r0.i(r1)     // Catch:{ all -> 0x00f2 }
        L_0x002e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            int r1 = r7.size()     // Catch:{ all -> 0x00f2 }
            int r1 = r1 * 3
            r0.<init>(r1)     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = "event_id in ("
            r0.append(r1)     // Catch:{ all -> 0x00f2 }
            r1 = 0
            int r2 = r7.size()     // Catch:{ all -> 0x00f2 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x00f2 }
        L_0x0047:
            boolean r3 = r7.hasNext()     // Catch:{ all -> 0x00f2 }
            if (r3 == 0) goto L_0x0064
            java.lang.Object r3 = r7.next()     // Catch:{ all -> 0x00f2 }
            com.tencent.wxop.stat.bd r3 = (com.tencent.wxop.stat.bd) r3     // Catch:{ all -> 0x00f2 }
            long r3 = r3.a     // Catch:{ all -> 0x00f2 }
            r0.append(r3)     // Catch:{ all -> 0x00f2 }
            int r3 = r2 + -1
            if (r1 == r3) goto L_0x0061
            java.lang.String r3 = ","
            r0.append(r3)     // Catch:{ all -> 0x00f2 }
        L_0x0061:
            int r1 = r1 + 1
            goto L_0x0047
        L_0x0064:
            java.lang.String r7 = ")"
            r0.append(r7)     // Catch:{ all -> 0x00f2 }
            r7 = 0
            android.database.sqlite.SQLiteDatabase r8 = r6.c(r8)     // Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
            r8.beginTransaction()     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r1 = "events"
            java.lang.String r3 = r0.toString()     // Catch:{ Throwable -> 0x00c4 }
            int r7 = r8.delete(r1, r3, r7)     // Catch:{ Throwable -> 0x00c4 }
            boolean r1 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x00c4 }
            if (r1 == 0) goto L_0x00a8
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ Throwable -> 0x00c4 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r4 = "delete "
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00c4 }
            r3.append(r2)     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r2 = " event "
            r3.append(r2)     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00c4 }
            r3.append(r0)     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r0 = ", success delete:"
            r3.append(r0)     // Catch:{ Throwable -> 0x00c4 }
            r3.append(r7)     // Catch:{ Throwable -> 0x00c4 }
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x00c4 }
            r1.i(r0)     // Catch:{ Throwable -> 0x00c4 }
        L_0x00a8:
            int r0 = r6.a     // Catch:{ Throwable -> 0x00c4 }
            int r0 = r0 - r7
            r6.a = r0     // Catch:{ Throwable -> 0x00c4 }
            r8.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00c4 }
            r6.f()     // Catch:{ Throwable -> 0x00c4 }
            if (r8 == 0) goto L_0x00e3
            r8.endTransaction()     // Catch:{ Throwable -> 0x00ba }
            monitor-exit(r6)
            return
        L_0x00ba:
            r7 = move-exception
            com.tencent.wxop.stat.common.StatLogger r8 = h     // Catch:{ all -> 0x00f2 }
            r8.e((java.lang.Throwable) r7)     // Catch:{ all -> 0x00f2 }
            monitor-exit(r6)
            return
        L_0x00c2:
            r7 = move-exception
            goto L_0x00e5
        L_0x00c4:
            r7 = move-exception
            goto L_0x00cf
        L_0x00c6:
            r8 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x00e5
        L_0x00cb:
            r8 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x00cf:
            com.tencent.wxop.stat.common.StatLogger r0 = h     // Catch:{ all -> 0x00c2 }
            r0.e((java.lang.Throwable) r7)     // Catch:{ all -> 0x00c2 }
            if (r8 == 0) goto L_0x00e3
            r8.endTransaction()     // Catch:{ Throwable -> 0x00db }
            monitor-exit(r6)
            return
        L_0x00db:
            r7 = move-exception
            com.tencent.wxop.stat.common.StatLogger r8 = h     // Catch:{ all -> 0x00f2 }
            r8.e((java.lang.Throwable) r7)     // Catch:{ all -> 0x00f2 }
            monitor-exit(r6)
            return
        L_0x00e3:
            monitor-exit(r6)
            return
        L_0x00e5:
            if (r8 == 0) goto L_0x00f1
            r8.endTransaction()     // Catch:{ Throwable -> 0x00eb }
            goto L_0x00f1
        L_0x00eb:
            r8 = move-exception
            com.tencent.wxop.stat.common.StatLogger r0 = h     // Catch:{ all -> 0x00f2 }
            r0.e((java.lang.Throwable) r8)     // Catch:{ all -> 0x00f2 }
        L_0x00f1:
            throw r7     // Catch:{ all -> 0x00f2 }
        L_0x00f2:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(java.util.List, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c A[SYNTHETIC, Splitter:B:22:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072 A[SYNTHETIC, Splitter:B:25:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r9) {
        /*
            r8 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r9 = r8.c(r9)     // Catch:{ Throwable -> 0x0061, all -> 0x005c }
            r9.beginTransaction()     // Catch:{ Throwable -> 0x005a }
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ Throwable -> 0x005a }
            r0.<init>()     // Catch:{ Throwable -> 0x005a }
            java.lang.String r1 = "status"
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x005a }
            r0.put(r1, r3)     // Catch:{ Throwable -> 0x005a }
            java.lang.String r1 = "events"
            java.lang.String r3 = "status=?"
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Throwable -> 0x005a }
            r4 = 0
            r5 = 2
            java.lang.String r5 = java.lang.Long.toString(r5)     // Catch:{ Throwable -> 0x005a }
            r2[r4] = r5     // Catch:{ Throwable -> 0x005a }
            int r0 = r9.update(r1, r0, r3, r2)     // Catch:{ Throwable -> 0x005a }
            boolean r1 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x005a }
            if (r1 == 0) goto L_0x0048
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ Throwable -> 0x005a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005a }
            java.lang.String r3 = "update "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x005a }
            r2.append(r0)     // Catch:{ Throwable -> 0x005a }
            java.lang.String r0 = " unsent events."
            r2.append(r0)     // Catch:{ Throwable -> 0x005a }
            java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x005a }
            r1.i(r0)     // Catch:{ Throwable -> 0x005a }
        L_0x0048:
            r9.setTransactionSuccessful()     // Catch:{ Throwable -> 0x005a }
            if (r9 == 0) goto L_0x0057
            r9.endTransaction()     // Catch:{ Throwable -> 0x0051 }
            goto L_0x0057
        L_0x0051:
            r9 = move-exception
            com.tencent.wxop.stat.common.StatLogger r0 = h
            r0.e((java.lang.Throwable) r9)
        L_0x0057:
            return
        L_0x0058:
            r0 = move-exception
            goto L_0x0070
        L_0x005a:
            r0 = move-exception
            goto L_0x0065
        L_0x005c:
            r9 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x0070
        L_0x0061:
            r9 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
        L_0x0065:
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ all -> 0x0058 }
            r1.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x0058 }
            if (r9 == 0) goto L_0x006f
            r9.endTransaction()     // Catch:{ Throwable -> 0x0051 }
        L_0x006f:
            return
        L_0x0070:
            if (r9 == 0) goto L_0x007c
            r9.endTransaction()     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007c
        L_0x0076:
            r9 = move-exception
            com.tencent.wxop.stat.common.StatLogger r1 = h
            r1.e((java.lang.Throwable) r9)
        L_0x007c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(boolean):void");
    }

    private int b(boolean z) {
        return !z ? StatConfig.getMaxSendRetryCount() : StatConfig.getMaxImportantDataSendRetryCount();
    }

    public static au b() {
        return j;
    }

    /* access modifiers changed from: private */
    public void b(int i2, boolean z) {
        if (i2 == -1) {
            i2 = !z ? g() : h();
        }
        if (i2 > 0) {
            int sendPeriodMinutes = StatConfig.getSendPeriodMinutes() * 60 * StatConfig.getNumEventsCommitPerSec();
            if (i2 > sendPeriodMinutes && sendPeriodMinutes > 0) {
                i2 = sendPeriodMinutes;
            }
            int a2 = StatConfig.a();
            int i3 = i2 / a2;
            int i4 = i2 % a2;
            if (StatConfig.isDebugEnable()) {
                StatLogger statLogger = h;
                statLogger.i("sentStoreEventsByDb sendNumbers=" + i2 + ",important=" + z + ",maxSendNumPerFor1Period=" + sendPeriodMinutes + ",maxCount=" + i3 + ",restNumbers=" + i4);
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
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0089, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.tencent.wxop.stat.event.e r2, com.tencent.wxop.stat.h r3, boolean r4, boolean r5) {
        /*
            r1 = this;
            monitor-enter(r1)
            int r0 = com.tencent.wxop.stat.StatConfig.getMaxStoreEventCount()     // Catch:{ all -> 0x008a }
            if (r0 <= 0) goto L_0x0088
            int r0 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x008a }
            if (r0 <= 0) goto L_0x0083
            if (r4 != 0) goto L_0x0083
            if (r5 == 0) goto L_0x0010
            goto L_0x0083
        L_0x0010:
            int r4 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x008a }
            if (r4 <= 0) goto L_0x0088
            boolean r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ all -> 0x008a }
            if (r4 == 0) goto L_0x005e
            com.tencent.wxop.stat.common.StatLogger r4 = h     // Catch:{ all -> 0x008a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            java.lang.String r0 = "cacheEventsInMemory.size():"
            r5.<init>(r0)     // Catch:{ all -> 0x008a }
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r0 = r1.l     // Catch:{ all -> 0x008a }
            int r0 = r0.size()     // Catch:{ all -> 0x008a }
            r5.append(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = ",numEventsCachedInMemory:"
            r5.append(r0)     // Catch:{ all -> 0x008a }
            int r0 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x008a }
            r5.append(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = ",numStoredEvents:"
            r5.append(r0)     // Catch:{ all -> 0x008a }
            int r0 = r1.a     // Catch:{ all -> 0x008a }
            r5.append(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x008a }
            r4.i(r5)     // Catch:{ all -> 0x008a }
            com.tencent.wxop.stat.common.StatLogger r4 = h     // Catch:{ all -> 0x008a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            java.lang.String r0 = "cache event:"
            r5.<init>(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r2.g()     // Catch:{ all -> 0x008a }
            r5.append(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x008a }
            r4.i(r5)     // Catch:{ all -> 0x008a }
        L_0x005e:
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r4 = r1.l     // Catch:{ all -> 0x008a }
            java.lang.String r5 = ""
            r4.put(r2, r5)     // Catch:{ all -> 0x008a }
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r2 = r1.l     // Catch:{ all -> 0x008a }
            int r2 = r2.size()     // Catch:{ all -> 0x008a }
            int r4 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x008a }
            if (r2 < r4) goto L_0x0072
            r1.i()     // Catch:{ all -> 0x008a }
        L_0x0072:
            if (r3 == 0) goto L_0x0088
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r2 = r1.l     // Catch:{ all -> 0x008a }
            int r2 = r2.size()     // Catch:{ all -> 0x008a }
            if (r2 <= 0) goto L_0x007f
            r1.i()     // Catch:{ all -> 0x008a }
        L_0x007f:
            r3.a()     // Catch:{ all -> 0x008a }
            goto L_0x0088
        L_0x0083:
            r1.a((com.tencent.wxop.stat.event.e) r2, (com.tencent.wxop.stat.h) r3, (boolean) r4)     // Catch:{ all -> 0x008a }
            monitor-exit(r1)
            return
        L_0x0088:
            monitor-exit(r1)
            return
        L_0x008a:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.b(com.tencent.wxop.stat.event.e, com.tencent.wxop.stat.h, boolean, boolean):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:33|34|(0)|52|53|54|55) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0106 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e5 A[SYNTHETIC, Splitter:B:39:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f7 A[SYNTHETIC, Splitter:B:49:0x00f7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.tencent.wxop.stat.f r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            r0 = 0
            java.lang.String r1 = r13.a()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r2 = com.tencent.wxop.stat.common.l.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x00dd }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Throwable -> 0x00dd }
            r3.<init>()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r4 = "content"
            org.json.JSONObject r5 = r13.b     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x00dd }
            r3.put(r4, r5)     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r4 = "md5sum"
            r3.put(r4, r2)     // Catch:{ Throwable -> 0x00dd }
            r13.c = r2     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r2 = "version"
            int r4 = r13.d     // Catch:{ Throwable -> 0x00dd }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x00dd }
            r3.put(r2, r4)     // Catch:{ Throwable -> 0x00dd }
            com.tencent.wxop.stat.bc r2 = r12.c     // Catch:{ Throwable -> 0x00dd }
            android.database.sqlite.SQLiteDatabase r4 = r2.getReadableDatabase()     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r5 = "config"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x00dd }
        L_0x003e:
            boolean r4 = r2.moveToNext()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0050
            int r4 = r2.getInt(r6)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            int r7 = r13.a     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            if (r4 != r7) goto L_0x003e
            r4 = 1
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            com.tencent.wxop.stat.bc r7 = r12.c     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            android.database.sqlite.SQLiteDatabase r7 = r7.getWritableDatabase()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r7.beginTransaction()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            if (r5 != r4) goto L_0x0076
            com.tencent.wxop.stat.bc r0 = r12.c     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r4 = "config"
            java.lang.String r7 = "type=?"
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            int r13 = r13.a     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r13 = java.lang.Integer.toString(r13)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r5[r6] = r13     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            int r13 = r0.update(r4, r3, r7, r5)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            long r3 = (long) r13     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            goto L_0x008d
        L_0x0076:
            java.lang.String r4 = "type"
            int r13 = r13.a     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r3.put(r4, r13)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            com.tencent.wxop.stat.bc r13 = r12.c     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            android.database.sqlite.SQLiteDatabase r13 = r13.getWritableDatabase()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r4 = "config"
            long r3 = r13.insert(r4, r0, r3)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
        L_0x008d:
            r5 = -1
            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r13 != 0) goto L_0x00a7
            com.tencent.wxop.stat.common.StatLogger r13 = h     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r3 = "Failed to store cfg:"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.append(r1)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r13.e((java.lang.Object) r0)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            goto L_0x00ba
        L_0x00a7:
            com.tencent.wxop.stat.common.StatLogger r13 = h     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r3 = "Sucessed to store cfg:"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r0.append(r1)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r13.d(r0)     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
        L_0x00ba:
            com.tencent.wxop.stat.bc r13 = r12.c     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            android.database.sqlite.SQLiteDatabase r13 = r13.getWritableDatabase()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            r13.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00d7, all -> 0x00d5 }
            if (r2 == 0) goto L_0x00c8
            r2.close()     // Catch:{ all -> 0x00fb }
        L_0x00c8:
            com.tencent.wxop.stat.bc r13 = r12.c     // Catch:{ Exception -> 0x00d3 }
            android.database.sqlite.SQLiteDatabase r13 = r13.getWritableDatabase()     // Catch:{ Exception -> 0x00d3 }
            r13.endTransaction()     // Catch:{ Exception -> 0x00d3 }
            monitor-exit(r12)
            return
        L_0x00d3:
            monitor-exit(r12)
            return
        L_0x00d5:
            r13 = move-exception
            goto L_0x00f5
        L_0x00d7:
            r13 = move-exception
            r0 = r2
            goto L_0x00de
        L_0x00da:
            r13 = move-exception
            r2 = r0
            goto L_0x00f5
        L_0x00dd:
            r13 = move-exception
        L_0x00de:
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ all -> 0x00da }
            r1.e((java.lang.Throwable) r13)     // Catch:{ all -> 0x00da }
            if (r0 == 0) goto L_0x00e8
            r0.close()     // Catch:{ all -> 0x00fb }
        L_0x00e8:
            com.tencent.wxop.stat.bc r13 = r12.c     // Catch:{ Exception -> 0x00f3 }
            android.database.sqlite.SQLiteDatabase r13 = r13.getWritableDatabase()     // Catch:{ Exception -> 0x00f3 }
            r13.endTransaction()     // Catch:{ Exception -> 0x00f3 }
            monitor-exit(r12)
            return
        L_0x00f3:
            monitor-exit(r12)
            return
        L_0x00f5:
            if (r2 == 0) goto L_0x00fd
            r2.close()     // Catch:{ all -> 0x00fb }
            goto L_0x00fd
        L_0x00fb:
            r13 = move-exception
            goto L_0x0107
        L_0x00fd:
            com.tencent.wxop.stat.bc r0 = r12.c     // Catch:{ Exception -> 0x0106 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Exception -> 0x0106 }
            r0.endTransaction()     // Catch:{ Exception -> 0x0106 }
        L_0x0106:
            throw r13     // Catch:{ all -> 0x00fb }
        L_0x0107:
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.b(com.tencent.wxop.stat.f):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.util.List<com.tencent.wxop.stat.bd> r12, int r13, boolean r14) {
        /*
            r11 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.d(r14)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r2 = "events"
            r3 = 0
            java.lang.String r4 = "status=?"
            r14 = 1
            java.lang.String[] r5 = new java.lang.String[r14]     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r6 = java.lang.Integer.toString(r14)     // Catch:{ Throwable -> 0x0088 }
            r10 = 0
            r5[r10] = r6     // Catch:{ Throwable -> 0x0088 }
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = java.lang.Integer.toString(r13)     // Catch:{ Throwable -> 0x0088 }
            android.database.Cursor r13 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0088 }
        L_0x001f:
            boolean r0 = r13.moveToNext()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r0 == 0) goto L_0x007a
            long r7 = r13.getLong(r10)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r0 = r13.getString(r14)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            boolean r1 = com.tencent.wxop.stat.StatConfig.g     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r1 != 0) goto L_0x0035
            java.lang.String r0 = com.tencent.wxop.stat.common.r.a((java.lang.String) r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
        L_0x0035:
            r4 = r0
            r0 = 2
            int r5 = r13.getInt(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0 = 3
            int r0 = r13.getInt(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            com.tencent.wxop.stat.bd r9 = new com.tencent.wxop.stat.bd     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r1 = r9
            r2 = r7
            r6 = r0
            r1.<init>(r2, r4, r5, r6)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            boolean r1 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            if (r1 == 0) goto L_0x0076
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r3 = "peek event, id="
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r2.append(r7)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r3 = ",send_count="
            r2.append(r3)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r2.append(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r0 = ",timestamp="
            r2.append(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r0 = 4
            long r3 = r13.getLong(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r2.append(r3)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            r1.i(r0)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
        L_0x0076:
            r12.add(r9)     // Catch:{ Throwable -> 0x0082, all -> 0x0080 }
            goto L_0x001f
        L_0x007a:
            if (r13 == 0) goto L_0x0093
            r13.close()
            return
        L_0x0080:
            r12 = move-exception
            goto L_0x0094
        L_0x0082:
            r12 = move-exception
            r0 = r13
            goto L_0x0089
        L_0x0085:
            r12 = move-exception
            r13 = r0
            goto L_0x0094
        L_0x0088:
            r12 = move-exception
        L_0x0089:
            com.tencent.wxop.stat.common.StatLogger r13 = h     // Catch:{ all -> 0x0085 }
            r13.e((java.lang.Throwable) r12)     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0093
            r0.close()
        L_0x0093:
            return
        L_0x0094:
            if (r13 == 0) goto L_0x0099
            r13.close()
        L_0x0099:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.b(java.util.List, int, boolean):void");
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
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e2 A[SYNTHETIC, Splitter:B:41:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f6 A[Catch:{ Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0127 A[SYNTHETIC, Splitter:B:52:0x0127] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i() {
        /*
            r11 = this;
            boolean r0 = r11.m
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r0 = r11.l
            monitor-enter(r0)
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r1 = r11.l     // Catch:{ all -> 0x0135 }
            int r1 = r1.size()     // Catch:{ all -> 0x0135 }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x0135 }
            return
        L_0x0012:
            r1 = 1
            r11.m = r1     // Catch:{ all -> 0x0135 }
            boolean r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ all -> 0x0135 }
            if (r2 == 0) goto L_0x0048
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0135 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = "insert "
            r3.<init>(r4)     // Catch:{ all -> 0x0135 }
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r4 = r11.l     // Catch:{ all -> 0x0135 }
            int r4 = r4.size()     // Catch:{ all -> 0x0135 }
            r3.append(r4)     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = " events ,numEventsCachedInMemory:"
            r3.append(r4)     // Catch:{ all -> 0x0135 }
            int r4 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x0135 }
            r3.append(r4)     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = ",numStoredEvents:"
            r3.append(r4)     // Catch:{ all -> 0x0135 }
            int r4 = r11.a     // Catch:{ all -> 0x0135 }
            r3.append(r4)     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0135 }
            r2.i(r3)     // Catch:{ all -> 0x0135 }
        L_0x0048:
            r2 = 0
            com.tencent.wxop.stat.bc r3 = r11.c     // Catch:{ Throwable -> 0x00da }
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch:{ Throwable -> 0x00da }
            r3.beginTransaction()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r4 = r11.l     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
        L_0x005c:
            boolean r5 = r4.hasNext()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r5 == 0) goto L_0x00bf
            java.lang.Object r5 = r4.next()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Object r5 = r5.getKey()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            com.tencent.wxop.stat.event.e r5 = (com.tencent.wxop.stat.event.e) r5     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r6.<init>()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r7 = r5.g()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            boolean r8 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r8 == 0) goto L_0x0090
            com.tencent.wxop.stat.common.StatLogger r8 = h     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r10 = "insert content:"
            r9.<init>(r10)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r9.append(r7)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r8.i(r9)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
        L_0x0090:
            java.lang.String r7 = com.tencent.wxop.stat.common.r.b((java.lang.String) r7)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r8 = "content"
            r6.put(r8, r7)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r7 = "send_count"
            java.lang.String r8 = "0"
            r6.put(r7, r8)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r7 = "status"
            java.lang.String r8 = java.lang.Integer.toString(r1)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r6.put(r7, r8)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r7 = "timestamp"
            long r8 = r5.c()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.Long r5 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r6.put(r7, r5)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            java.lang.String r5 = "events"
            r3.insert(r5, r2, r6)     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            r4.remove()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            goto L_0x005c
        L_0x00bf:
            r3.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00d4, all -> 0x00d2 }
            if (r3 == 0) goto L_0x00ed
            r3.endTransaction()     // Catch:{ Throwable -> 0x00cb }
            r11.f()     // Catch:{ Throwable -> 0x00cb }
            goto L_0x00ed
        L_0x00cb:
            r1 = move-exception
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0135 }
        L_0x00ce:
            r2.e((java.lang.Throwable) r1)     // Catch:{ all -> 0x0135 }
            goto L_0x00ed
        L_0x00d2:
            r1 = move-exception
            goto L_0x0125
        L_0x00d4:
            r1 = move-exception
            r2 = r3
            goto L_0x00db
        L_0x00d7:
            r1 = move-exception
            r3 = r2
            goto L_0x0125
        L_0x00da:
            r1 = move-exception
        L_0x00db:
            com.tencent.wxop.stat.common.StatLogger r3 = h     // Catch:{ all -> 0x00d7 }
            r3.e((java.lang.Throwable) r1)     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x00ed
            r2.endTransaction()     // Catch:{ Throwable -> 0x00e9 }
            r11.f()     // Catch:{ Throwable -> 0x00e9 }
            goto L_0x00ed
        L_0x00e9:
            r1 = move-exception
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0135 }
            goto L_0x00ce
        L_0x00ed:
            r1 = 0
            r11.m = r1     // Catch:{ all -> 0x0135 }
            boolean r1 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ all -> 0x0135 }
            if (r1 == 0) goto L_0x0123
            com.tencent.wxop.stat.common.StatLogger r1 = h     // Catch:{ all -> 0x0135 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = "after insert, cacheEventsInMemory.size():"
            r2.<init>(r3)     // Catch:{ all -> 0x0135 }
            java.util.concurrent.ConcurrentHashMap<com.tencent.wxop.stat.event.e, java.lang.String> r3 = r11.l     // Catch:{ all -> 0x0135 }
            int r3 = r3.size()     // Catch:{ all -> 0x0135 }
            r2.append(r3)     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = ",numEventsCachedInMemory:"
            r2.append(r3)     // Catch:{ all -> 0x0135 }
            int r3 = com.tencent.wxop.stat.StatConfig.m     // Catch:{ all -> 0x0135 }
            r2.append(r3)     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = ",numStoredEvents:"
            r2.append(r3)     // Catch:{ all -> 0x0135 }
            int r3 = r11.a     // Catch:{ all -> 0x0135 }
            r2.append(r3)     // Catch:{ all -> 0x0135 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0135 }
            r1.i(r2)     // Catch:{ all -> 0x0135 }
        L_0x0123:
            monitor-exit(r0)     // Catch:{ all -> 0x0135 }
            return
        L_0x0125:
            if (r3 == 0) goto L_0x0134
            r3.endTransaction()     // Catch:{ Throwable -> 0x012e }
            r11.f()     // Catch:{ Throwable -> 0x012e }
            goto L_0x0134
        L_0x012e:
            r2 = move-exception
            com.tencent.wxop.stat.common.StatLogger r3 = h     // Catch:{ all -> 0x0135 }
            r3.e((java.lang.Throwable) r2)     // Catch:{ all -> 0x0135 }
        L_0x0134:
            throw r1     // Catch:{ all -> 0x0135 }
        L_0x0135:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0135 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.i():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void j() {
        /*
            r11 = this;
            r0 = 0
            com.tencent.wxop.stat.bc r1 = r11.c     // Catch:{ Throwable -> 0x0036, all -> 0x0031 }
            android.database.sqlite.SQLiteDatabase r2 = r1.getReadableDatabase()     // Catch:{ Throwable -> 0x0036, all -> 0x0031 }
            java.lang.String r3 = "keyvalues"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0036, all -> 0x0031 }
        L_0x0013:
            boolean r0 = r1.moveToNext()     // Catch:{ Throwable -> 0x002f }
            if (r0 == 0) goto L_0x0029
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r11.n     // Catch:{ Throwable -> 0x002f }
            r2 = 0
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Throwable -> 0x002f }
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Throwable -> 0x002f }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x002f }
            goto L_0x0013
        L_0x0029:
            if (r1 == 0) goto L_0x0044
            r1.close()
            return
        L_0x002f:
            r0 = move-exception
            goto L_0x003a
        L_0x0031:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x0046
        L_0x0036:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L_0x003a:
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0045 }
            r2.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0044
            r1.close()
        L_0x0044:
            return
        L_0x0045:
            r0 = move-exception
        L_0x0046:
            if (r1 == 0) goto L_0x004b
            r1.close()
        L_0x004b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.j():void");
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

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0202 A[SYNTHETIC, Splitter:B:105:0x0202] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00bb A[Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d2 A[Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0125 A[Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0138 A[Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01e6 A[SYNTHETIC, Splitter:B:91:0x01e6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.tencent.wxop.stat.common.a b(android.content.Context r19) {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            com.tencent.wxop.stat.common.a r0 = r1.b     // Catch:{ all -> 0x0218 }
            if (r0 == 0) goto L_0x000b
            com.tencent.wxop.stat.common.a r0 = r1.b     // Catch:{ all -> 0x0218 }
            monitor-exit(r18)
            return r0
        L_0x000b:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            r0.beginTransaction()     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            boolean r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            if (r0 == 0) goto L_0x002b
            com.tencent.wxop.stat.common.StatLogger r0 = h     // Catch:{ Throwable -> 0x0027, all -> 0x0022 }
            java.lang.String r3 = "try to load user info from db."
            r0.i(r3)     // Catch:{ Throwable -> 0x0027, all -> 0x0022 }
            goto L_0x002b
        L_0x0022:
            r0 = move-exception
            r2 = r0
            r3 = 0
            goto L_0x0200
        L_0x0027:
            r0 = move-exception
            r2 = 0
            goto L_0x01df
        L_0x002b:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            android.database.sqlite.SQLiteDatabase r3 = r0.getReadableDatabase()     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            java.lang.String r4 = "user"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x01dc, all -> 0x01d7 }
            boolean r0 = r3.moveToNext()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4 = 1000(0x3e8, double:4.94E-321)
            r6 = 0
            r7 = 1
            if (r0 == 0) goto L_0x0145
            java.lang.String r0 = r3.getString(r6)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r8 = com.tencent.wxop.stat.common.r.a((java.lang.String) r0)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            int r9 = r3.getInt(r7)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r10 = 2
            java.lang.String r11 = r3.getString(r10)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r12 = 3
            long r12 = r3.getLong(r12)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            long r14 = r14 / r4
            if (r9 == r7) goto L_0x0079
            long r12 = r12 * r4
            java.lang.String r12 = com.tencent.wxop.stat.common.l.a((long) r12)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            long r16 = r14 * r4
            java.lang.String r13 = com.tencent.wxop.stat.common.l.a((long) r16)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            boolean r12 = r12.equals(r13)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r12 != 0) goto L_0x0079
            r12 = 1
            goto L_0x007a
        L_0x0079:
            r12 = r9
        L_0x007a:
            java.lang.String r13 = com.tencent.wxop.stat.common.l.l(r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            boolean r11 = r11.equals(r13)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r11 != 0) goto L_0x0086
            r12 = r12 | 2
        L_0x0086:
            java.lang.String r11 = ","
            java.lang.String[] r11 = r8.split(r11)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r11 == 0) goto L_0x00b0
            int r13 = r11.length     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r13 <= 0) goto L_0x00b0
            r13 = r11[r6]     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r13 == 0) goto L_0x00a0
            int r4 = r13.length()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r5 = 11
            if (r4 >= r5) goto L_0x009e
            goto L_0x00a0
        L_0x009e:
            r2 = 0
            goto L_0x00b6
        L_0x00a0:
            java.lang.String r4 = com.tencent.wxop.stat.common.r.a((android.content.Context) r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r4 == 0) goto L_0x009e
            int r5 = r4.length()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2 = 10
            if (r5 <= r2) goto L_0x009e
            r13 = r4
            goto L_0x00b5
        L_0x00b0:
            java.lang.String r8 = com.tencent.wxop.stat.common.l.b((android.content.Context) r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r13 = r8
        L_0x00b5:
            r2 = 1
        L_0x00b6:
            if (r11 == 0) goto L_0x00d2
            int r4 = r11.length     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r4 < r10) goto L_0x00d2
            r4 = r11[r7]     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r5.<init>()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r5.append(r13)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r8 = ","
            r5.append(r8)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r5.append(r4)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r8 = r5.toString()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            goto L_0x00f3
        L_0x00d2:
            java.lang.String r4 = com.tencent.wxop.stat.common.l.c((android.content.Context) r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r4 == 0) goto L_0x00f3
            int r5 = r4.length()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r5 <= 0) goto L_0x00f3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2.<init>()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2.append(r13)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = ","
            r2.append(r5)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2.append(r4)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r8 = r2.toString()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2 = 1
        L_0x00f3:
            com.tencent.wxop.stat.common.a r5 = new com.tencent.wxop.stat.common.a     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r5.<init>(r13, r4, r12)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r1.b = r5     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.<init>()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = com.tencent.wxop.stat.common.r.b((java.lang.String) r8)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r8 = "uid"
            r4.put(r8, r5)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = "user_type"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.put(r5, r8)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = "app_ver"
            java.lang.String r8 = com.tencent.wxop.stat.common.l.l(r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.put(r5, r8)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = "ts"
            java.lang.Long r8 = java.lang.Long.valueOf(r14)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.put(r5, r8)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r2 == 0) goto L_0x0136
            com.tencent.wxop.stat.bc r2 = r1.c     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = "user"
            java.lang.String r8 = "uid=?"
            java.lang.String[] r10 = new java.lang.String[r7]     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r10[r6] = r0     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r2.update(r5, r4, r8, r10)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
        L_0x0136:
            if (r12 == r9) goto L_0x0146
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r2 = "user"
            r5 = 0
            r0.replace(r2, r5, r4)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            goto L_0x0146
        L_0x0145:
            r7 = 0
        L_0x0146:
            if (r7 != 0) goto L_0x01b1
            java.lang.String r0 = com.tencent.wxop.stat.common.l.b((android.content.Context) r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r2 = com.tencent.wxop.stat.common.l.c((android.content.Context) r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r2 == 0) goto L_0x016d
            int r4 = r2.length()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r4 <= 0) goto L_0x016d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.<init>()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.append(r0)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.append(r2)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            goto L_0x016e
        L_0x016d:
            r4 = r0
        L_0x016e:
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r9
            java.lang.String r5 = com.tencent.wxop.stat.common.l.l(r19)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.content.ContentValues r9 = new android.content.ContentValues     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r9.<init>()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r4 = com.tencent.wxop.stat.common.r.b((java.lang.String) r4)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r10 = "uid"
            r9.put(r10, r4)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r4 = "user_type"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r9.put(r4, r10)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r4 = "app_ver"
            r9.put(r4, r5)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r4 = "ts"
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r9.put(r4, r5)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            com.tencent.wxop.stat.bc r4 = r1.c     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.database.sqlite.SQLiteDatabase r4 = r4.getWritableDatabase()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            java.lang.String r5 = "user"
            r7 = 0
            r4.insert(r5, r7, r9)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            com.tencent.wxop.stat.common.a r4 = new com.tencent.wxop.stat.common.a     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r4.<init>(r0, r2, r6)     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r1.b = r4     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
        L_0x01b1:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            r0.setTransactionSuccessful()     // Catch:{ Throwable -> 0x01d4, all -> 0x01d2 }
            if (r3 == 0) goto L_0x01c2
            r3.close()     // Catch:{ Throwable -> 0x01c0 }
            goto L_0x01c2
        L_0x01c0:
            r0 = move-exception
            goto L_0x01cc
        L_0x01c2:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01c0 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x01c0 }
            r0.endTransaction()     // Catch:{ Throwable -> 0x01c0 }
            goto L_0x01f9
        L_0x01cc:
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0218 }
        L_0x01ce:
            r2.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x0218 }
            goto L_0x01f9
        L_0x01d2:
            r0 = move-exception
            goto L_0x01ff
        L_0x01d4:
            r0 = move-exception
            r2 = r3
            goto L_0x01df
        L_0x01d7:
            r0 = move-exception
            r7 = 0
            r2 = r0
            r3 = r7
            goto L_0x0200
        L_0x01dc:
            r0 = move-exception
            r7 = 0
            r2 = r7
        L_0x01df:
            com.tencent.wxop.stat.common.StatLogger r3 = h     // Catch:{ all -> 0x01fd }
            r3.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x01fd }
            if (r2 == 0) goto L_0x01ec
            r2.close()     // Catch:{ Throwable -> 0x01ea }
            goto L_0x01ec
        L_0x01ea:
            r0 = move-exception
            goto L_0x01f6
        L_0x01ec:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x01ea }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x01ea }
            r0.endTransaction()     // Catch:{ Throwable -> 0x01ea }
            goto L_0x01f9
        L_0x01f6:
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0218 }
            goto L_0x01ce
        L_0x01f9:
            com.tencent.wxop.stat.common.a r0 = r1.b     // Catch:{ all -> 0x0218 }
            monitor-exit(r18)
            return r0
        L_0x01fd:
            r0 = move-exception
            r3 = r2
        L_0x01ff:
            r2 = r0
        L_0x0200:
            if (r3 == 0) goto L_0x0208
            r3.close()     // Catch:{ Throwable -> 0x0206 }
            goto L_0x0208
        L_0x0206:
            r0 = move-exception
            goto L_0x0212
        L_0x0208:
            com.tencent.wxop.stat.bc r0 = r1.c     // Catch:{ Throwable -> 0x0206 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x0206 }
            r0.endTransaction()     // Catch:{ Throwable -> 0x0206 }
            goto L_0x0217
        L_0x0212:
            com.tencent.wxop.stat.common.StatLogger r3 = h     // Catch:{ all -> 0x0218 }
            r3.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x0218 }
        L_0x0217:
            throw r2     // Catch:{ all -> 0x0218 }
        L_0x0218:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.b(android.content.Context):com.tencent.wxop.stat.common.a");
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
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r11 = this;
            r0 = 0
            com.tencent.wxop.stat.bc r1 = r11.c     // Catch:{ Throwable -> 0x0052, all -> 0x004d }
            android.database.sqlite.SQLiteDatabase r2 = r1.getReadableDatabase()     // Catch:{ Throwable -> 0x0052, all -> 0x004d }
            java.lang.String r3 = "config"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0052, all -> 0x004d }
        L_0x0013:
            boolean r0 = r1.moveToNext()     // Catch:{ Throwable -> 0x004b }
            if (r0 == 0) goto L_0x0045
            r0 = 0
            int r0 = r1.getInt(r0)     // Catch:{ Throwable -> 0x004b }
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Throwable -> 0x004b }
            r3 = 2
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Throwable -> 0x004b }
            r4 = 3
            int r4 = r1.getInt(r4)     // Catch:{ Throwable -> 0x004b }
            com.tencent.wxop.stat.f r5 = new com.tencent.wxop.stat.f     // Catch:{ Throwable -> 0x004b }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x004b }
            r5.a = r0     // Catch:{ Throwable -> 0x004b }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004b }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x004b }
            r5.b = r0     // Catch:{ Throwable -> 0x004b }
            r5.c = r3     // Catch:{ Throwable -> 0x004b }
            r5.d = r4     // Catch:{ Throwable -> 0x004b }
            android.content.Context r0 = i     // Catch:{ Throwable -> 0x004b }
            com.tencent.wxop.stat.StatConfig.a((android.content.Context) r0, (com.tencent.wxop.stat.f) r5)     // Catch:{ Throwable -> 0x004b }
            goto L_0x0013
        L_0x0045:
            if (r1 == 0) goto L_0x0060
            r1.close()
            return
        L_0x004b:
            r0 = move-exception
            goto L_0x0056
        L_0x004d:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x0062
        L_0x0052:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L_0x0056:
            com.tencent.wxop.stat.common.StatLogger r2 = h     // Catch:{ all -> 0x0061 }
            r2.e((java.lang.Throwable) r0)     // Catch:{ all -> 0x0061 }
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            return
        L_0x0061:
            r0 = move-exception
        L_0x0062:
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.d():void");
    }
}
