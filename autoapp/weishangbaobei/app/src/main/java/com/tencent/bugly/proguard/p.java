package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class p {
    private static p a;
    private static q b;
    private static boolean c;

    private p(Context context, List<com.tencent.bugly.a> list) {
        b = new q(context, list);
    }

    public static synchronized p a(Context context, List<com.tencent.bugly.a> list) {
        p pVar;
        synchronized (p.class) {
            if (a == null) {
                a = new p(context, list);
            }
            pVar = a;
        }
        return pVar;
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            pVar = a;
        }
        return pVar;
    }

    public final long a(String str, ContentValues contentValues, o oVar, boolean z) {
        return a(str, contentValues, (o) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, o oVar, boolean z) {
        return a(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null, (o) null);
    }

    public final int a(String str, String str2, String[] strArr, o oVar, boolean z) {
        return a(str, str2, (String[]) null, (o) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        java.lang.Long.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long a(java.lang.String r7, android.content.ContentValues r8, com.tencent.bugly.proguard.o r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            com.tencent.bugly.proguard.q r2 = b     // Catch:{ Throwable -> 0x0035 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ Throwable -> 0x0035 }
            if (r2 == 0) goto L_0x002d
            if (r8 == 0) goto L_0x002d
            java.lang.String r3 = "_id"
            long r2 = r2.replace(r7, r3, r8)     // Catch:{ Throwable -> 0x0035 }
            r8 = 0
            r4 = 1
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 < 0) goto L_0x0023
            java.lang.String r5 = "[Database] insert %s success."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0035 }
            r4[r8] = r7     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.proguard.x.c(r5, r4)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x002c
        L_0x0023:
            java.lang.String r5 = "[Database] replace %s error."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0035 }
            r4[r8] = r7     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.proguard.x.d(r5, r4)     // Catch:{ Throwable -> 0x0035 }
        L_0x002c:
            r0 = r2
        L_0x002d:
            if (r9 == 0) goto L_0x0042
        L_0x002f:
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x004a }
            goto L_0x0042
        L_0x0033:
            r7 = move-exception
            goto L_0x0044
        L_0x0035:
            r7 = move-exception
            boolean r8 = com.tencent.bugly.proguard.x.a(r7)     // Catch:{ all -> 0x0033 }
            if (r8 != 0) goto L_0x003f
            r7.printStackTrace()     // Catch:{ all -> 0x0033 }
        L_0x003f:
            if (r9 == 0) goto L_0x0042
            goto L_0x002f
        L_0x0042:
            monitor-exit(r6)
            return r0
        L_0x0044:
            if (r9 == 0) goto L_0x004c
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x004a }
            goto L_0x004c
        L_0x004a:
            r7 = move-exception
            goto L_0x004d
        L_0x004c:
            throw r7     // Catch:{ all -> 0x004a }
        L_0x004d:
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.lang.String, android.content.ContentValues, com.tencent.bugly.proguard.o):long");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.Cursor a(boolean r14, java.lang.String r15, java.lang.String[] r16, java.lang.String r17, java.lang.String[] r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, com.tencent.bugly.proguard.o r23) {
        /*
            r13 = this;
            monitor-enter(r13)
            r2 = 0
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ Throwable -> 0x0022 }
            android.database.sqlite.SQLiteDatabase r3 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x0022 }
            if (r3 == 0) goto L_0x002c
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            r12 = r22
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Throwable -> 0x0022 }
            r2 = r0
            goto L_0x002c
        L_0x0020:
            r0 = move-exception
            goto L_0x002e
        L_0x0022:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x002c
            r0.printStackTrace()     // Catch:{ all -> 0x0020 }
        L_0x002c:
            monitor-exit(r13)
            return r2
        L_0x002e:
            throw r0     // Catch:{ all -> 0x002f }
        L_0x002f:
            r0 = move-exception
            r1 = r0
            monitor-exit(r13)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.bugly.proguard.o):android.database.Cursor");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        if (r6 != null) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        if (r6 != null) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        java.lang.Integer.valueOf(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int a(java.lang.String r3, java.lang.String r4, java.lang.String[] r5, com.tencent.bugly.proguard.o r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ Throwable -> 0x0017 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0017 }
            if (r1 == 0) goto L_0x000f
            int r3 = r1.delete(r3, r4, r5)     // Catch:{ Throwable -> 0x0017 }
            r0 = r3
        L_0x000f:
            if (r6 == 0) goto L_0x0024
        L_0x0011:
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x002c }
            goto L_0x0024
        L_0x0015:
            r3 = move-exception
            goto L_0x0026
        L_0x0017:
            r3 = move-exception
            boolean r4 = com.tencent.bugly.proguard.x.a(r3)     // Catch:{ all -> 0x0015 }
            if (r4 != 0) goto L_0x0021
            r3.printStackTrace()     // Catch:{ all -> 0x0015 }
        L_0x0021:
            if (r6 == 0) goto L_0x0024
            goto L_0x0011
        L_0x0024:
            monitor-exit(r2)
            return r0
        L_0x0026:
            if (r6 == 0) goto L_0x002e
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x002c }
            goto L_0x002e
        L_0x002c:
            r3 = move-exception
            goto L_0x002f
        L_0x002e:
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002f:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.lang.String, java.lang.String, java.lang.String[], com.tencent.bugly.proguard.o):int");
    }

    public final boolean a(int i, String str, byte[] bArr, o oVar, boolean z) {
        if (z) {
            return a(i, str, bArr, (o) null);
        }
        a aVar = new a(4, (o) null);
        aVar.a(i, str, bArr);
        w.a().a(aVar);
        return true;
    }

    public final Map<String, byte[]> a(int i, o oVar, boolean z) {
        return a(i, (o) null);
    }

    public final boolean a(int i, String str, o oVar, boolean z) {
        return a(555, str, (o) null);
    }

    /* access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, o oVar) {
        try {
            r rVar = new r();
            rVar.a = (long) i;
            rVar.f = str;
            rVar.e = System.currentTimeMillis();
            rVar.g = bArr;
            boolean b2 = b(rVar);
            if (oVar == null) {
                return b2;
            }
            Boolean.valueOf(b2);
            return b2;
        } catch (Throwable th) {
            if (oVar != null) {
                Boolean.valueOf(false);
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, byte[]> a(int r4, com.tencent.bugly.proguard.o r5) {
        /*
            r3 = this;
            r0 = 0
            java.util.List r4 = r3.c((int) r4)     // Catch:{ Throwable -> 0x002d }
            if (r4 == 0) goto L_0x0037
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x002d }
            r1.<init>()     // Catch:{ Throwable -> 0x002d }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x0028 }
        L_0x0010:
            boolean r0 = r4.hasNext()     // Catch:{ Throwable -> 0x0028 }
            if (r0 == 0) goto L_0x0026
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x0028 }
            com.tencent.bugly.proguard.r r0 = (com.tencent.bugly.proguard.r) r0     // Catch:{ Throwable -> 0x0028 }
            byte[] r2 = r0.g     // Catch:{ Throwable -> 0x0028 }
            if (r2 == 0) goto L_0x0010
            java.lang.String r0 = r0.f     // Catch:{ Throwable -> 0x0028 }
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x0028 }
            goto L_0x0010
        L_0x0026:
            r0 = r1
            goto L_0x0037
        L_0x0028:
            r4 = move-exception
            r0 = r1
            goto L_0x002e
        L_0x002b:
            r4 = move-exception
            goto L_0x0038
        L_0x002d:
            r4 = move-exception
        L_0x002e:
            boolean r1 = com.tencent.bugly.proguard.x.a(r4)     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0037
            r4.printStackTrace()     // Catch:{ all -> 0x002b }
        L_0x0037:
            return r0
        L_0x0038:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int, com.tencent.bugly.proguard.o):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(com.tencent.bugly.proguard.r r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ Throwable -> 0x0038 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0038 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = c((com.tencent.bugly.proguard.r) r8)     // Catch:{ Throwable -> 0x0038 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_lr"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ Throwable -> 0x0038 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0038 }
            java.lang.String r6 = "t_lr"
            r5[r0] = r6     // Catch:{ Throwable -> 0x0038 }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ Throwable -> 0x0038 }
            r8.a = r1     // Catch:{ Throwable -> 0x0038 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            goto L_0x0044
        L_0x0038:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r8)     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0042
            r8.printStackTrace()     // Catch:{ all -> 0x0036 }
        L_0x0042:
            monitor-exit(r7)
            return r0
        L_0x0044:
            throw r8     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(com.tencent.bugly.proguard.r):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean b(com.tencent.bugly.proguard.r r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ Throwable -> 0x0038 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0038 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = d(r8)     // Catch:{ Throwable -> 0x0038 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_pf"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ Throwable -> 0x0038 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0038 }
            java.lang.String r6 = "t_pf"
            r5[r0] = r6     // Catch:{ Throwable -> 0x0038 }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ Throwable -> 0x0038 }
            r8.a = r1     // Catch:{ Throwable -> 0x0038 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            goto L_0x0044
        L_0x0038:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r8)     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0042
            r8.printStackTrace()     // Catch:{ all -> 0x0036 }
        L_0x0042:
            monitor-exit(r7)
            return r0
        L_0x0044:
            throw r8     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.b(com.tencent.bugly.proguard.r):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a5, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ad A[Catch:{ all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b2 A[SYNTHETIC, Splitter:B:48:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b9 A[Catch:{ all -> 0x00b6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.tencent.bugly.proguard.r> a(int r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ all -> 0x00bf }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x00bf }
            r9 = 0
            if (r0 == 0) goto L_0x00bd
            if (r11 < 0) goto L_0x0026
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0021, all -> 0x001c }
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0021, all -> 0x001c }
            r1.append(r11)     // Catch:{ Throwable -> 0x0021, all -> 0x001c }
            java.lang.String r11 = r1.toString()     // Catch:{ Throwable -> 0x0021, all -> 0x001c }
            r4 = r11
            goto L_0x0027
        L_0x001c:
            r11 = move-exception
            r0 = r11
            r11 = r9
            goto L_0x00b7
        L_0x0021:
            r11 = move-exception
            r0 = r11
            r11 = r9
            goto L_0x00a7
        L_0x0026:
            r4 = r9
        L_0x0027:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0021, all -> 0x001c }
            if (r11 != 0) goto L_0x003c
            if (r11 == 0) goto L_0x003a
            r11.close()     // Catch:{ all -> 0x00bf }
        L_0x003a:
            monitor-exit(r10)
            return r9
        L_0x003c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a6 }
            r1.<init>()     // Catch:{ Throwable -> 0x00a6 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00a6 }
            r2.<init>()     // Catch:{ Throwable -> 0x00a6 }
        L_0x0046:
            boolean r3 = r11.moveToNext()     // Catch:{ Throwable -> 0x00a6 }
            r4 = 0
            if (r3 == 0) goto L_0x0077
            com.tencent.bugly.proguard.r r3 = a((android.database.Cursor) r11)     // Catch:{ Throwable -> 0x00a6 }
            if (r3 == 0) goto L_0x0057
            r2.add(r3)     // Catch:{ Throwable -> 0x00a6 }
            goto L_0x0046
        L_0x0057:
            java.lang.String r3 = "_id"
            int r3 = r11.getColumnIndex(r3)     // Catch:{ Throwable -> 0x006f }
            long r5 = r11.getLong(r3)     // Catch:{ Throwable -> 0x006f }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ Throwable -> 0x006f }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ Throwable -> 0x006f }
            r1.append(r5)     // Catch:{ Throwable -> 0x006f }
            goto L_0x0046
        L_0x006f:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00a6 }
            com.tencent.bugly.proguard.x.d(r3, r4)     // Catch:{ Throwable -> 0x00a6 }
            goto L_0x0046
        L_0x0077:
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00a6 }
            int r3 = r1.length()     // Catch:{ Throwable -> 0x00a6 }
            if (r3 <= 0) goto L_0x009f
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ Throwable -> 0x00a6 }
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch:{ Throwable -> 0x00a6 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00a6 }
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch:{ Throwable -> 0x00a6 }
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x00a6 }
            r3[r4] = r0     // Catch:{ Throwable -> 0x00a6 }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ Throwable -> 0x00a6 }
        L_0x009f:
            if (r11 == 0) goto L_0x00a4
            r11.close()     // Catch:{ all -> 0x00bf }
        L_0x00a4:
            monitor-exit(r10)
            return r2
        L_0x00a6:
            r0 = move-exception
        L_0x00a7:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x00b6 }
            if (r1 != 0) goto L_0x00b0
            r0.printStackTrace()     // Catch:{ all -> 0x00b6 }
        L_0x00b0:
            if (r11 == 0) goto L_0x00bd
            r11.close()     // Catch:{ all -> 0x00bf }
            goto L_0x00bd
        L_0x00b6:
            r0 = move-exception
        L_0x00b7:
            if (r11 == 0) goto L_0x00bc
            r11.close()     // Catch:{ all -> 0x00bf }
        L_0x00bc:
            throw r0     // Catch:{ all -> 0x00bf }
        L_0x00bd:
            monitor-exit(r10)
            return r9
        L_0x00bf:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.util.List<com.tencent.bugly.proguard.r> r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x007a
            int r0 = r5.size()     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x000a
            goto L_0x007a
        L_0x000a:
            com.tencent.bugly.proguard.q r0 = b     // Catch:{ all -> 0x0077 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0075
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r1.<init>()     // Catch:{ all -> 0x0077 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0077 }
        L_0x001b:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0077 }
            com.tencent.bugly.proguard.r r2 = (com.tencent.bugly.proguard.r) r2     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0077 }
            long r2 = r2.a     // Catch:{ all -> 0x0077 }
            r1.append(r2)     // Catch:{ all -> 0x0077 }
            goto L_0x001b
        L_0x0037:
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0077 }
            int r2 = r5.length()     // Catch:{ all -> 0x0077 }
            if (r2 <= 0) goto L_0x0046
            r2 = 4
            java.lang.String r5 = r5.substring(r2)     // Catch:{ all -> 0x0077 }
        L_0x0046:
            r2 = 0
            r1.setLength(r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "t_lr"
            r3 = 0
            int r5 = r0.delete(r1, r5, r3)     // Catch:{ Throwable -> 0x0068 }
            java.lang.String r0 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0068 }
            java.lang.String r3 = "t_lr"
            r1[r2] = r3     // Catch:{ Throwable -> 0x0068 }
            r2 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0068 }
            r1[r2] = r5     // Catch:{ Throwable -> 0x0068 }
            com.tencent.bugly.proguard.x.c(r0, r1)     // Catch:{ Throwable -> 0x0068 }
            monitor-exit(r4)
            return
        L_0x0066:
            r5 = move-exception
            goto L_0x0074
        L_0x0068:
            r5 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0072
            r5.printStackTrace()     // Catch:{ all -> 0x0066 }
        L_0x0072:
            monitor-exit(r4)
            return
        L_0x0074:
            throw r5     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r4)
            return
        L_0x0077:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x007a:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(java.util.List):void");
    }

    public final synchronized void b(int i) {
        String str;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    return;
                }
            } else {
                str = null;
            }
            x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, (String[]) null)));
            return;
        }
        return;
    }

    private static ContentValues c(r rVar) {
        if (rVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(rVar.b));
            contentValues.put("_pc", rVar.c);
            contentValues.put("_th", rVar.d);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            rVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            rVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        return r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ae A[Catch:{ all -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b3 A[SYNTHETIC, Splitter:B:44:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bb A[SYNTHETIC, Splitter:B:50:0x00bb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.List<com.tencent.bugly.proguard.r> c(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            if (r1 == 0) goto L_0x00b6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r2.append(r12)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r10 = r2.toString()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r3 = "t_pf"
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r1
            r5 = r10
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            if (r2 != 0) goto L_0x002e
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ all -> 0x00bf }
        L_0x002c:
            monitor-exit(r11)
            return r0
        L_0x002e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a1 }
            r3.<init>()     // Catch:{ Throwable -> 0x00a1 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00a1 }
            r4.<init>()     // Catch:{ Throwable -> 0x00a1 }
        L_0x0038:
            boolean r5 = r2.moveToNext()     // Catch:{ Throwable -> 0x00a1 }
            r6 = 0
            if (r5 == 0) goto L_0x0069
            com.tencent.bugly.proguard.r r5 = b((android.database.Cursor) r2)     // Catch:{ Throwable -> 0x00a1 }
            if (r5 == 0) goto L_0x0049
            r4.add(r5)     // Catch:{ Throwable -> 0x00a1 }
            goto L_0x0038
        L_0x0049:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r7 = " or _tp"
            r3.append(r7)     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r7 = " = "
            r3.append(r7)     // Catch:{ Throwable -> 0x0061 }
            r3.append(r5)     // Catch:{ Throwable -> 0x0061 }
            goto L_0x0038
        L_0x0061:
            java.lang.String r5 = "[Database] unknown id."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x00a1 }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ Throwable -> 0x00a1 }
            goto L_0x0038
        L_0x0069:
            int r5 = r3.length()     // Catch:{ Throwable -> 0x00a1 }
            if (r5 <= 0) goto L_0x009a
            java.lang.String r5 = " and _id"
            r3.append(r5)     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r5 = " = "
            r3.append(r5)     // Catch:{ Throwable -> 0x00a1 }
            r3.append(r12)     // Catch:{ Throwable -> 0x00a1 }
            r12 = 4
            java.lang.String r12 = r10.substring(r12)     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r3 = "t_pf"
            int r12 = r1.delete(r3, r12, r0)     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d."
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r5 = "t_pf"
            r3[r6] = r5     // Catch:{ Throwable -> 0x00a1 }
            r5 = 1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x00a1 }
            r3[r5] = r12     // Catch:{ Throwable -> 0x00a1 }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ Throwable -> 0x00a1 }
        L_0x009a:
            if (r2 == 0) goto L_0x009f
            r2.close()     // Catch:{ all -> 0x00bf }
        L_0x009f:
            monitor-exit(r11)
            return r4
        L_0x00a1:
            r12 = move-exception
            goto L_0x00a8
        L_0x00a3:
            r12 = move-exception
            r2 = r0
            goto L_0x00b9
        L_0x00a6:
            r12 = move-exception
            r2 = r0
        L_0x00a8:
            boolean r1 = com.tencent.bugly.proguard.x.a(r12)     // Catch:{ all -> 0x00b8 }
            if (r1 != 0) goto L_0x00b1
            r12.printStackTrace()     // Catch:{ all -> 0x00b8 }
        L_0x00b1:
            if (r2 == 0) goto L_0x00b6
            r2.close()     // Catch:{ all -> 0x00bf }
        L_0x00b6:
            monitor-exit(r11)
            return r0
        L_0x00b8:
            r12 = move-exception
        L_0x00b9:
            if (r2 == 0) goto L_0x00c1
            r2.close()     // Catch:{ all -> 0x00bf }
            goto L_0x00c1
        L_0x00bf:
            r12 = move-exception
            goto L_0x00c2
        L_0x00c1:
            throw r12     // Catch:{ all -> 0x00bf }
        L_0x00c2:
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        java.lang.Boolean.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(int r5, java.lang.String r6, com.tencent.bugly.proguard.o r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = b     // Catch:{ Throwable -> 0x0064 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0064 }
            if (r1 == 0) goto L_0x005c
            boolean r2 = com.tencent.bugly.proguard.z.a((java.lang.String) r6)     // Catch:{ Throwable -> 0x0064 }
            if (r2 == 0) goto L_0x001f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r2 = "_id = "
            r6.<init>(r2)     // Catch:{ Throwable -> 0x0064 }
            r6.append(r5)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r5 = r6.toString()     // Catch:{ Throwable -> 0x0064 }
            goto L_0x003f
        L_0x001f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0064 }
            r2.append(r5)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r5 = " and _tp"
            r2.append(r5)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r5 = " = \""
            r2.append(r5)     // Catch:{ Throwable -> 0x0064 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r5 = "\""
            r2.append(r5)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r5 = r2.toString()     // Catch:{ Throwable -> 0x0064 }
        L_0x003f:
            java.lang.String r6 = "t_pf"
            r2 = 0
            int r5 = r1.delete(r6, r5, r2)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r6 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r2 = "t_pf"
            r1[r0] = r2     // Catch:{ Throwable -> 0x0064 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0064 }
            r3 = 1
            r1[r3] = r2     // Catch:{ Throwable -> 0x0064 }
            com.tencent.bugly.proguard.x.c(r6, r1)     // Catch:{ Throwable -> 0x0064 }
            if (r5 <= 0) goto L_0x005c
            r0 = 1
        L_0x005c:
            if (r7 == 0) goto L_0x0071
        L_0x005e:
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0079 }
            goto L_0x0071
        L_0x0062:
            r5 = move-exception
            goto L_0x0073
        L_0x0064:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0062 }
            if (r6 != 0) goto L_0x006e
            r5.printStackTrace()     // Catch:{ all -> 0x0062 }
        L_0x006e:
            if (r7 == 0) goto L_0x0071
            goto L_0x005e
        L_0x0071:
            monitor-exit(r4)
            return r0
        L_0x0073:
            if (r7 == 0) goto L_0x007b
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0079 }
            goto L_0x007b
        L_0x0079:
            r5 = move-exception
            goto L_0x007c
        L_0x007b:
            throw r5     // Catch:{ all -> 0x0079 }
        L_0x007c:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int, java.lang.String, com.tencent.bugly.proguard.o):boolean");
    }

    private static ContentValues d(r rVar) {
        if (rVar == null || z.a(rVar.f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", rVar.f);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i2, o oVar) {
            this.a = i2;
            this.b = oVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i2, String str, byte[] bArr) {
            this.o = i2;
            this.p = str;
            this.q = bArr;
        }

        public final void run() {
            switch (this.a) {
                case 1:
                    long unused = p.this.a(this.c, this.d, this.b);
                    return;
                case 2:
                    int unused2 = p.this.a(this.c, this.m, this.n, this.b);
                    return;
                case 3:
                    Cursor a2 = p.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
                    return;
                case 4:
                    boolean unused3 = p.this.a(this.o, this.p, this.q, this.b);
                    return;
                case 5:
                    Map unused4 = p.this.a(this.o, this.b);
                    return;
                case 6:
                    boolean unused5 = p.this.a(this.o, this.p, this.b);
                    return;
                default:
                    return;
            }
        }
    }
}
