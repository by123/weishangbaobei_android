package com.umeng.analytics.pro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: UMDBUtils */
public class f {
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r4, android.database.sqlite.SQLiteDatabase r5) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r2.<init>()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r3 = "select count(*) as c from sqlite_master where type ='table' and name ='"
            r2.append(r3)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r4 = r4.trim()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            r2.append(r4)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r4 = "' "
            r2.append(r4)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            android.database.Cursor r4 = r5.rawQuery(r4, r1)     // Catch:{ Exception -> 0x0043, all -> 0x003c }
            boolean r5 = r4.moveToNext()     // Catch:{ Exception -> 0x003a, all -> 0x0037 }
            if (r5 == 0) goto L_0x0031
            int r5 = r4.getInt(r0)     // Catch:{ Exception -> 0x003a, all -> 0x0037 }
            if (r5 <= 0) goto L_0x0031
            r5 = 1
            r0 = 1
        L_0x0031:
            if (r4 == 0) goto L_0x0049
            r4.close()
            goto L_0x0049
        L_0x0037:
            r5 = move-exception
            r1 = r4
            goto L_0x003d
        L_0x003a:
            r1 = r4
            goto L_0x0044
        L_0x003c:
            r5 = move-exception
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()
        L_0x0042:
            throw r5
        L_0x0043:
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.f.a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r2) {
        /*
            if (r2 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = "ua.db"
            java.io.File r0 = r2.getDatabasePath(r0)     // Catch:{ Throwable -> 0x001e, all -> 0x001c }
            if (r0 == 0) goto L_0x0014
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x001e, all -> 0x001c }
            if (r1 == 0) goto L_0x0014
            r0.delete()     // Catch:{ Throwable -> 0x001e, all -> 0x001c }
        L_0x0014:
            com.umeng.analytics.pro.d r2 = com.umeng.analytics.pro.d.a((android.content.Context) r2)     // Catch:{ Throwable -> 0x001e, all -> 0x001c }
            r2.a()     // Catch:{ Throwable -> 0x001e, all -> 0x001c }
            goto L_0x001e
        L_0x001c:
            r2 = move-exception
            throw r2
        L_0x001e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.f.a(android.content.Context):void");
    }

    public static String b(Context context) {
        File databasePath = context.getDatabasePath(c.b);
        return databasePath.getParent() + File.separator;
    }

    public static String c(Context context) {
        return b(context) + "subprocess/";
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String next : list) {
                if (Collections.frequency(arrayList, next) < 1) {
                    arrayList.add(next);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursor = null;
        boolean z = false;
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", (String[]) null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getColumnIndex(str2) != -1) {
                        z = true;
                    }
                } catch (Exception unused) {
                    cursor = rawQuery;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        } catch (Exception unused2) {
            cursor.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            cursor.close();
            throw th;
        }
        return z;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + " " + str3);
    }
}
