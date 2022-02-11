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

public class f {
    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public static void a(Context context) {
        if (context != null) {
            File databasePath = context.getDatabasePath(c.b);
            if (databasePath != null && databasePath.exists()) {
                databasePath.delete();
            }
            d.a(context).a();
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + " " + str3);
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        boolean z = false;
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", (String[]) null);
            if (cursor != null) {
                try {
                    if (cursor.getColumnIndex(str2) != -1) {
                        z = true;
                    }
                } catch (Exception e) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e2) {
        } catch (Throwable th2) {
            th = th2;
            cursor.close();
            throw th;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    public static boolean a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", (String[]) null);
                try {
                    if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                        z = true;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Exception e) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                }
                throw th;
            }
        }
        return z;
    }

    public static String b(Context context) {
        File databasePath = context.getDatabasePath(c.b);
        return databasePath.getParent() + File.separator;
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

    public static String c(Context context) {
        return b(context) + "subprocess/";
    }
}
