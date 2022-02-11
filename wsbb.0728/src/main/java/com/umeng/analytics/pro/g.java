package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import com.stub.StubApp;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.c;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    public static final int a = 2049;
    public static final int b = 2050;
    private static final int c = 1000;
    private static Context d = null;
    private static String e = null;
    private static final String f = "umeng+";
    private static final String g = "ek__id";
    private static final String h = "ek_key";
    private List<String> i;
    private List<Integer> j;
    private String k;
    private List<String> l;

    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    private static class b {
        /* access modifiers changed from: private */
        public static final g a = new g();

        private b() {
        }
    }

    private g() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = null;
        this.l = new ArrayList();
    }

    public static g a(Context context) {
        g a2 = b.a;
        if (d == null && context != null) {
            d = StubApp.getOrigApplicationContext(context.getApplicationContext());
            a2.k();
        }
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f9, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01fe, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0192, code lost:
        if (r4 != null) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01a9, code lost:
        if (r4 != null) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01b5, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01f2, code lost:
        r2 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01ba A[SYNTHETIC, Splitter:B:78:0x01ba] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01d2 A[SYNTHETIC, Splitter:B:86:0x01d2] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01f2 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x001d] */
    private String a(JSONObject jSONObject, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        String str = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery("select *  from __sd", (String[]) null);
                if (cursor != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (cursor.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            String string = cursor.getString(cursor.getColumnIndex(c.d.a.g));
                            String string2 = cursor.getString(cursor.getColumnIndex("__e"));
                            str = cursor.getString(cursor.getColumnIndex("__ii"));
                            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                                if (Long.parseLong(string) - Long.parseLong(string2) > 0) {
                                    String string3 = cursor.getString(cursor.getColumnIndex("__a"));
                                    String string4 = cursor.getString(cursor.getColumnIndex(c.d.a.c));
                                    String string5 = cursor.getString(cursor.getColumnIndex(c.d.a.d));
                                    String string6 = cursor.getString(cursor.getColumnIndex(c.d.a.e));
                                    this.i.add(str);
                                    String string7 = cursor.getString(cursor.getColumnIndex("__sp"));
                                    String string8 = cursor.getString(cursor.getColumnIndex("__pp"));
                                    jSONObject2.put("id", str);
                                    jSONObject2.put(b.p, string2);
                                    jSONObject2.put(b.q, string);
                                    jSONObject2.put("duration", Long.parseLong(string) - Long.parseLong(string2));
                                    if (!TextUtils.isEmpty(string3)) {
                                        jSONObject2.put(b.s, new JSONArray(d(string3)));
                                    }
                                    if (!TextUtils.isEmpty(string4) && AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                                        jSONObject2.put(b.t, new JSONArray(d(string4)));
                                    }
                                    if (!TextUtils.isEmpty(string5)) {
                                        jSONObject2.put(b.E, new JSONObject(d(string5)));
                                    }
                                    if (!TextUtils.isEmpty(string6)) {
                                        jSONObject2.put(b.A, new JSONArray(d(string6)));
                                    }
                                    if (!TextUtils.isEmpty(string7)) {
                                        jSONObject2.put(b.ar, new JSONObject(d(string7)));
                                    }
                                    if (!TextUtils.isEmpty(string8)) {
                                        jSONObject2.put(b.as, new JSONObject(d(string8)));
                                    }
                                    if (jSONObject2.length() > 0) {
                                        jSONArray.put(jSONObject2);
                                    }
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                        if (this.i.size() < 1) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Throwable th) {
                                }
                            }
                            e.a(d).b();
                            return str;
                        } else if (jSONArray.length() > 0) {
                            jSONObject.put(b.n, jSONArray);
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        try {
                            f.a(d);
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            throw th;
                        }
                    } catch (Throwable th3) {
                    }
                } else {
                    str = null;
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteDatabaseCorruptException e3) {
                str = null;
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th5) {
                    }
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            str = null;
            sQLiteDatabase = null;
        } catch (Throwable th6) {
            th = th6;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            e.a(d).b();
            throw th;
        }
        e.a(d).b();
        return str;
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.opt(c.d.a.g)).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String str3 = "";
            if (optJSONObject != null) {
                if (optJSONObject.length() > 0) {
                    str2 = c(optJSONObject.toString());
                }
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = c(optJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + longValue + "\", " + "__sp" + "=\"" + str2 + "\", " + "__pp" + "=\"" + str3 + "\" where " + "__ii" + "=\"" + str + "\"");
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068 A[LOOP:0: B:17:0x0068->B:20:0x006e, LOOP_START, PHI: r1 
  PHI: (r1v16 java.lang.String) = (r1v0 java.lang.String), (r1v19 java.lang.String) binds: [B:16:0x0066, B:20:0x006e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:17:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0087 A[Catch:{ Throwable -> 0x010e, all -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0113  */
    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws JSONException {
        Cursor cursor;
        Throwable th;
        Cursor cursor2;
        JSONArray jSONArray;
        String str3;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        String str4 = null;
        try {
            if ("__a".equals(str2)) {
                jSONArray3 = jSONObject.optJSONArray("__a");
                if (jSONArray3 == null || jSONArray3.length() <= 0) {
                    return;
                }
            } else if (c.d.a.c.equals(str2)) {
                jSONArray3 = jSONObject.optJSONArray(c.d.a.c);
                if (jSONArray3 == null || jSONArray3.length() <= 0) {
                    return;
                }
            } else {
                jSONArray = null;
                cursor = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.d.a + " where " + "__ii" + "=\"" + str + "\"", (String[]) null);
                if (cursor == null) {
                    while (cursor.moveToNext()) {
                        try {
                            str4 = d(cursor.getString(cursor.getColumnIndex(str2)));
                        } catch (Throwable th2) {
                            cursor2 = cursor;
                            th = th2;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    }
                    str3 = str4;
                } else {
                    str3 = null;
                }
                jSONArray2 = TextUtils.isEmpty(str3) ? new JSONArray(str3) : new JSONArray();
                if (jSONArray2.length() > 1000) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                            if (jSONObject2 != null) {
                                jSONArray2.put(jSONObject2);
                            }
                        } catch (JSONException e2) {
                        }
                    }
                    String c2 = c(jSONArray2.toString());
                    if (!TextUtils.isEmpty(c2)) {
                        sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + c2 + "\" where " + "__ii" + "=\"" + str + "\"");
                    }
                    if (cursor == null) {
                        return;
                    }
                    cursor.close();
                } else if (cursor != null) {
                    cursor.close();
                    return;
                } else {
                    return;
                }
            }
            jSONArray = jSONArray3;
            cursor = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.d.a + " where " + "__ii" + "=\"" + str + "\"", (String[]) null);
            if (cursor == null) {
            }
            if (TextUtils.isEmpty(str3)) {
            }
            if (jSONArray2.length() > 1000) {
            }
        } catch (Throwable th3) {
            cursor2 = null;
            th = th3;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f5, code lost:
        if (r1 != null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01a4, code lost:
        if (r1 != null) goto L_0x00c0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0107 A[SYNTHETIC, Splitter:B:63:0x0107] */
    private void a(JSONObject jSONObject, String str) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase3;
        Cursor cursor2;
        Cursor cursor3;
        String str2;
        Cursor cursor4 = null;
        try {
            sQLiteDatabase2 = e.a(d).a();
            try {
                sQLiteDatabase2.beginTransaction();
                String str3 = "select *  from __et";
                if (!TextUtils.isEmpty(str)) {
                    str3 = "select *  from __et where __i=\"" + str + "\"";
                }
                Cursor rawQuery = sQLiteDatabase2.rawQuery(str3, (String[]) null);
                if (rawQuery != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        JSONObject jSONObject3 = new JSONObject();
                        String b2 = q.a().b();
                        while (rawQuery.moveToNext()) {
                            int i2 = rawQuery.getInt(rawQuery.getColumnIndex("__t"));
                            String string = rawQuery.getString(rawQuery.getColumnIndex("__i"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex("__s"));
                            if (!TextUtils.isEmpty(string) && !"-1".equals(string)) {
                                str2 = string;
                            } else if (!TextUtils.isEmpty(b2)) {
                                str2 = b2;
                            }
                            this.j.add(Integer.valueOf(rawQuery.getInt(0)));
                            switch (i2) {
                                case a /*2049*/:
                                    if (TextUtils.isEmpty(string2)) {
                                        break;
                                    } else {
                                        JSONObject jSONObject4 = new JSONObject(d(string2));
                                        JSONArray optJSONArray = jSONObject2.has(str2) ? jSONObject2.optJSONArray(str2) : new JSONArray();
                                        optJSONArray.put(jSONObject4);
                                        jSONObject2.put(str2, optJSONArray);
                                        break;
                                    }
                                case b /*2050*/:
                                    if (TextUtils.isEmpty(string2)) {
                                        break;
                                    } else {
                                        JSONObject jSONObject5 = new JSONObject(d(string2));
                                        JSONArray optJSONArray2 = jSONObject3.has(str2) ? jSONObject3.optJSONArray(str2) : new JSONArray();
                                        optJSONArray2.put(jSONObject5);
                                        jSONObject3.put(str2, optJSONArray2);
                                        break;
                                    }
                            }
                        }
                        if (jSONObject2.length() > 0) {
                            JSONArray jSONArray = new JSONArray();
                            Iterator<String> keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                JSONObject jSONObject6 = new JSONObject();
                                String next = keys.next();
                                jSONObject6.put(next, new JSONArray(jSONObject2.optString(next)));
                                if (jSONObject6.length() > 0) {
                                    jSONArray.put(jSONObject6);
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(b.R, jSONArray);
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            JSONArray jSONArray2 = new JSONArray();
                            Iterator<String> keys2 = jSONObject3.keys();
                            while (keys2.hasNext()) {
                                JSONObject jSONObject7 = new JSONObject();
                                String next2 = keys2.next();
                                jSONObject7.put(next2, new JSONArray(jSONObject3.optString(next2)));
                                if (jSONObject7.length() > 0) {
                                    jSONArray2.put(jSONObject7);
                                }
                            }
                            if (jSONArray2.length() > 0) {
                                jSONObject.put(b.S, jSONArray2);
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        sQLiteDatabase3 = sQLiteDatabase2;
                        cursor2 = rawQuery;
                    } catch (Throwable th3) {
                        th2 = th3;
                        cursor = rawQuery;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase2 != null) {
                        }
                        e.a(d).b();
                        throw th2;
                    }
                }
                sQLiteDatabase2.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteDatabaseCorruptException e3) {
                sQLiteDatabase3 = sQLiteDatabase2;
                cursor2 = null;
                try {
                    f.a(d);
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase3 != null) {
                        sQLiteDatabase2 = sQLiteDatabase3;
                        try {
                            sQLiteDatabase2.endTransaction();
                        } catch (Throwable th4) {
                        }
                    }
                    e.a(d).b();
                } catch (Throwable th5) {
                    th = th5;
                    cursor4 = cursor2;
                    sQLiteDatabase = sQLiteDatabase3;
                    sQLiteDatabase2 = sQLiteDatabase;
                    cursor = cursor4;
                    th2 = th;
                    if (cursor != null) {
                    }
                    if (sQLiteDatabase2 != null) {
                    }
                    e.a(d).b();
                    throw th2;
                }
            } catch (Throwable th6) {
                th = th6;
                sQLiteDatabase = sQLiteDatabase2;
                sQLiteDatabase2 = sQLiteDatabase;
                cursor = cursor4;
                th2 = th;
                if (cursor != null) {
                }
                if (sQLiteDatabase2 != null) {
                }
                e.a(d).b();
                throw th2;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            sQLiteDatabase3 = null;
            cursor2 = null;
        } catch (Throwable th7) {
            th2 = th7;
            sQLiteDatabase2 = null;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase2 != null) {
                try {
                    sQLiteDatabase2.endTransaction();
                } catch (Throwable th8) {
                }
            }
            e.a(d).b();
            throw th2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00aa, code lost:
        if (r2 != null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ba, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d2, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0106, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0107, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d7 A[SYNTHETIC, Splitter:B:50:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ef A[SYNTHETIC, Splitter:B:58:0x00ef] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0106 A[ExcHandler: all (r0v18 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x011d  */
    private String b(JSONObject jSONObject, boolean z) {
        String str;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        String str2;
        String str3 = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery("select *  from __is", (String[]) null);
                if (cursor != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (cursor.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            String string = cursor.getString(cursor.getColumnIndex("__e"));
                            str3 = cursor.getString(cursor.getColumnIndex("__ii"));
                            this.l.add(str3);
                            String string2 = cursor.getString(cursor.getColumnIndex("__sp"));
                            String string3 = cursor.getString(cursor.getColumnIndex("__pp"));
                            if (!TextUtils.isEmpty(string2)) {
                                jSONObject2.put(b.ar, new JSONObject(d(string2)));
                            }
                            if (!TextUtils.isEmpty(string3)) {
                                jSONObject2.put(b.as, new JSONObject(d(string3)));
                            }
                            if (!TextUtils.isEmpty(string)) {
                                jSONObject2.put("id", str3);
                                jSONObject2.put(b.p, string);
                                if (jSONObject2.length() > 0) {
                                    jSONArray.put(jSONObject2);
                                }
                                if (!z) {
                                }
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put(b.n, jSONArray);
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        str = str3;
                        try {
                            f.a(d);
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            return str;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            throw th;
                        }
                    } catch (Throwable th2) {
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteDatabaseCorruptException e3) {
                str = null;
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            str = null;
            sQLiteDatabase = null;
            cursor = null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th5) {
                }
            }
            e.a(d).b();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    private void b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        long longValue = ((Long) jSONObject.get("__e")).longValue();
        JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
        String str2 = "";
        String str3 = "";
        if (optJSONObject != null) {
            if (optJSONObject.length() > 0) {
                str2 = c(optJSONObject.toString());
            }
        }
        if (optJSONObject2 != null && optJSONObject2.length() > 0) {
            str3 = c(optJSONObject2.toString());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("__ii", str);
        contentValues.put("__e", String.valueOf(longValue));
        contentValues.put("__sp", str2);
        contentValues.put("__pp", str3);
        contentValues.put("__av", UMGlobalContext.getInstance().getAppVersion());
        contentValues.put("__vc", UMUtils.getAppVersionCode(d));
        sQLiteDatabase.insert(c.C0005c.a, (String) null, contentValues);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008b, code lost:
        if (r1 != null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0095, code lost:
        if (r1 != null) goto L_0x006b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a2 A[SYNTHETIC, Splitter:B:49:0x00a2] */
    private void b(JSONObject jSONObject, String str) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase2;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase3;
        Cursor cursor2;
        Cursor cursor3;
        Cursor cursor4 = null;
        try {
            sQLiteDatabase2 = e.a(d).a();
            try {
                sQLiteDatabase2.beginTransaction();
                String str2 = "select *  from __er";
                if (!TextUtils.isEmpty(str)) {
                    str2 = "select *  from __er where __i=\"" + str + "\"";
                }
                Cursor rawQuery = sQLiteDatabase2.rawQuery(str2, (String[]) null);
                if (rawQuery != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (rawQuery.moveToNext()) {
                            String string = rawQuery.getString(rawQuery.getColumnIndex("__a"));
                            if (!TextUtils.isEmpty(string)) {
                                jSONArray.put(new JSONObject(d(string)));
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put("error", jSONArray);
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        sQLiteDatabase3 = sQLiteDatabase2;
                        cursor2 = rawQuery;
                        try {
                            f.a(d);
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (sQLiteDatabase3 != null) {
                                sQLiteDatabase2 = sQLiteDatabase3;
                                try {
                                    sQLiteDatabase2.endTransaction();
                                } catch (Throwable th3) {
                                }
                            }
                            e.a(d).b();
                        } catch (Throwable th4) {
                            th = th4;
                            sQLiteDatabase = sQLiteDatabase3;
                            cursor4 = cursor2;
                            cursor = cursor4;
                            sQLiteDatabase2 = sQLiteDatabase;
                            th2 = th;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase2 != null) {
                            }
                            e.a(d).b();
                            throw th2;
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase2 != null) {
                            try {
                                sQLiteDatabase2.endTransaction();
                            } catch (Throwable th6) {
                            }
                        }
                        e.a(d).b();
                        throw th2;
                    }
                }
                sQLiteDatabase2.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteDatabaseCorruptException e3) {
                sQLiteDatabase3 = sQLiteDatabase2;
                cursor2 = null;
                f.a(d);
                if (cursor2 != null) {
                }
                if (sQLiteDatabase3 != null) {
                }
                e.a(d).b();
            } catch (Throwable th7) {
                th = th7;
                sQLiteDatabase = sQLiteDatabase2;
                cursor = cursor4;
                sQLiteDatabase2 = sQLiteDatabase;
                th2 = th;
                if (cursor != null) {
                }
                if (sQLiteDatabase2 != null) {
                }
                e.a(d).b();
                throw th2;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            sQLiteDatabase3 = null;
            cursor2 = null;
        } catch (Throwable th8) {
            th2 = th8;
            cursor = null;
            sQLiteDatabase2 = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase2 != null) {
            }
            e.a(d).b();
            throw th2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v34, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r0v33, types: [java.lang.String] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    private void c(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        String str2;
        Cursor cursor2;
        Cursor cursor3 = null;
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(c.d.a.e);
            if (optJSONObject != null) {
                cursor2 = sQLiteDatabase.rawQuery("select __d from __sd where __ii=\"" + str + "\"", (String[]) null);
                if (cursor2 != null) {
                    while (cursor2.moveToNext()) {
                        try {
                            cursor3 = d(cursor2.getString(cursor2.getColumnIndex(c.d.a.e)));
                        } catch (Throwable th2) {
                            cursor = cursor2;
                            th = th2;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    }
                    str2 = cursor3;
                } else {
                    str2 = null;
                }
            } else {
                str2 = null;
                cursor2 = null;
            }
            if (optJSONObject != null) {
                JSONArray jSONArray = new JSONArray();
                if (!TextUtils.isEmpty(str2)) {
                    jSONArray = new JSONArray(str2);
                }
                jSONArray.put(optJSONObject);
                String c2 = c(jSONArray.toString());
                if (!TextUtils.isEmpty(c2)) {
                    sQLiteDatabase.execSQL("update  __sd set __d=\"" + c2 + "\" where " + "__ii" + "=\"" + str + "\"");
                }
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(c.d.a.d);
            if (optJSONObject2 != null) {
                String c3 = c(optJSONObject2.toString());
                if (!TextUtils.isEmpty(c3)) {
                    sQLiteDatabase.execSQL("update  __sd set __c=\"" + c3 + "\" where " + "__ii" + "=\"" + str + "\"");
                }
            }
            long optLong = jSONObject.optLong(c.d.a.g);
            sQLiteDatabase.execSQL("update  __sd set __f=\"" + String.valueOf(optLong) + "\" where " + "__ii" + "=\"" + str + "\"");
            if (cursor2 != null) {
                cursor3 = cursor2;
                cursor3.close();
            }
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    private void l() {
        try {
            if (TextUtils.isEmpty(e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(d, g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = DeviceConfig.getDBencryptID(d);
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(d, g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String substring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < substring.length(); i2++) {
                        char charAt = substring.charAt(i2);
                        if (!Character.isDigit(charAt)) {
                            sb.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            sb.append(0);
                        } else {
                            sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    e = sb.toString();
                }
                if (!TextUtils.isEmpty(e)) {
                    e += new StringBuilder(e).reverse().toString();
                    String multiProcessSP2 = UMUtils.getMultiProcessSP(d, h);
                    if (TextUtils.isEmpty(multiProcessSP2)) {
                        UMUtils.setMultiProcessSP(d, h, c(f));
                    } else if (!f.equals(d(multiProcessSP2))) {
                        b(true, false);
                        a(true, false);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003f, code lost:
        if (r2 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0070, code lost:
        if (r2 != null) goto L_0x0041;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0052 A[SYNTHETIC, Splitter:B:18:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[Catch:{ Exception -> 0x0076 }] */
    public long a(String str) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        String str2 = "select __f from __sd where __ii=\"" + str + "\"";
        long j2 = 0;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery(str2, (String[]) null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                        j2 = cursor.getLong(cursor.getColumnIndex(c.d.a.g));
                    } catch (Exception e2) {
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        e.a(d).b();
                        throw th;
                    }
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Exception e4) {
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e5) {
                        e.a(d).b();
                        throw th;
                    }
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                e.a(d).b();
                throw th;
            }
        } catch (Exception e6) {
            cursor = null;
            sQLiteDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            cursor = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            e.a(d).b();
            throw th;
        }
        e.a(d).b();
        return j2;
        if (cursor != null) {
            cursor.close();
        }
    }

    public JSONObject a(boolean z) {
        a();
        this.j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a(jSONObject, z);
            b(jSONObject, (String) null);
            a(jSONObject, (String) null);
        } else {
            String a2 = a(jSONObject, z);
            if (!TextUtils.isEmpty(a2)) {
                b(jSONObject, a2);
                a(jSONObject, a2);
            }
        }
        return jSONObject;
    }

    public void a() {
        this.i.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x009b, code lost:
        if (r0 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ab, code lost:
        if (r0 != null) goto L_0x009d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bf A[SYNTHETIC, Splitter:B:37:0x00bf] */
    public void a(JSONArray jSONArray) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        ContentValues contentValues = new ContentValues();
                        String optString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(optString) || "-1".equals(optString)) {
                            optString = q.a().b();
                            if (TextUtils.isEmpty(optString)) {
                                optString = "-1";
                            }
                        }
                        contentValues.put("__i", optString);
                        contentValues.put("__e", jSONObject.optString("id"));
                        contentValues.put("__t", Integer.valueOf(jSONObject.optInt("__t")));
                        contentValues.put("__av", UMUtils.getAppVersionName(d));
                        contentValues.put("__vc", UMUtils.getAppVersionCode(d));
                        jSONObject.remove("__i");
                        jSONObject.remove("__t");
                        contentValues.put("__s", c(jSONObject.toString()));
                        sQLiteDatabase.insert(c.b.a, (String) null, contentValues);
                    } catch (Exception e2) {
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e3) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th3) {
                    }
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0077, code lost:
        if (r0 != null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0067, code lost:
        if (r0 != null) goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008b A[SYNTHETIC, Splitter:B:24:0x008b] */
    public void a(boolean z, String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabase.execSQL("delete from __er where __i=\"" + str + "\"");
                    sQLiteDatabase.execSQL("delete from __et where __i=\"" + str + "\"");
                    this.j.clear();
                    sQLiteDatabase.execSQL("delete from __sd where __ii=\"" + str + "\"");
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005f A[SYNTHETIC, Splitter:B:24:0x005f] */
    public void a(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        SQLiteDatabase sQLiteDatabase3 = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                if (!z2) {
                    int size = this.l.size();
                    if (size > 0) {
                        for (int i2 = 0; i2 < size; i2++) {
                            sQLiteDatabase.execSQL("delete from __is where __ii=\"" + this.l.get(i2) + "\"");
                        }
                    }
                } else if (z) {
                    sQLiteDatabase.execSQL("delete from __is");
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                sQLiteDatabase3 = sQLiteDatabase;
                try {
                    f.a(d);
                    if (sQLiteDatabase3 != null) {
                        sQLiteDatabase3.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th2) {
                    th = th2;
                    sQLiteDatabase2 = sQLiteDatabase3;
                    if (sQLiteDatabase2 != null) {
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase2 = sQLiteDatabase;
                if (sQLiteDatabase2 != null) {
                    try {
                        sQLiteDatabase2.endTransaction();
                    } catch (Throwable th4) {
                    }
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th5) {
            sQLiteDatabase = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004f, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074 A[SYNTHETIC, Splitter:B:24:0x0074] */
    public boolean a(String str, String str2, int i2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("__i", str);
                String c2 = c(str2);
                if (!TextUtils.isEmpty(c2)) {
                    contentValues.put("__a", c2);
                    contentValues.put("__t", Integer.valueOf(i2));
                    contentValues.put("__av", UMUtils.getAppVersionName(d));
                    contentValues.put("__vc", UMUtils.getAppVersionCode(d));
                    sQLiteDatabase.insert(c.a.a, (String) null, contentValues);
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0054, code lost:
        if (r1 != null) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085 A[Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0082, all -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0095 A[SYNTHETIC, Splitter:B:39:0x0095] */
    public boolean a(String str, JSONObject jSONObject, a aVar) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        SQLiteDatabase sQLiteDatabase3 = null;
        if (jSONObject != null) {
            try {
                sQLiteDatabase = e.a(d).a();
                try {
                    sQLiteDatabase.beginTransaction();
                    if (aVar == a.BEGIN) {
                        long longValue = ((Long) jSONObject.opt("__e")).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__ii", str);
                        contentValues.put("__e", String.valueOf(longValue));
                        contentValues.put("__av", UMUtils.getAppVersionName(d));
                        contentValues.put("__vc", UMUtils.getAppVersionCode(d));
                        sQLiteDatabase.insert(c.d.a, (String) null, contentValues);
                    } else if (aVar == a.INSTANTSESSIONBEGIN) {
                        b(str, jSONObject, sQLiteDatabase);
                    } else if (aVar == a.END) {
                        a(str, jSONObject, sQLiteDatabase);
                    } else if (aVar == a.PAGE) {
                        a(str, jSONObject, sQLiteDatabase, "__a");
                    } else if (aVar == a.AUTOPAGE) {
                        a(str, jSONObject, sQLiteDatabase, c.d.a.c);
                    } else if (aVar == a.NEWSESSION) {
                        c(str, jSONObject, sQLiteDatabase);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLiteDatabaseCorruptException e2) {
                    sQLiteDatabase3 = sQLiteDatabase;
                    try {
                        f.a(d);
                        if (sQLiteDatabase3 != null) {
                            sQLiteDatabase3.endTransaction();
                        }
                        e.a(d).b();
                        return false;
                    } catch (Throwable th2) {
                        sQLiteDatabase2 = sQLiteDatabase3;
                        th = th2;
                        if (sQLiteDatabase2 != null) {
                        }
                        e.a(d).b();
                        throw th;
                    }
                } catch (Throwable th3) {
                    sQLiteDatabase2 = sQLiteDatabase;
                    th = th3;
                    if (sQLiteDatabase2 != null) {
                        try {
                            sQLiteDatabase2.endTransaction();
                        } catch (Throwable th4) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (SQLiteDatabaseCorruptException e3) {
            } catch (Throwable th5) {
                sQLiteDatabase = null;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                return false;
            }
        }
        return false;
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    public void b() {
        this.l.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        if (r0 != null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        if (r0 != null) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054 A[SYNTHETIC, Splitter:B:24:0x0054] */
    public void b(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabase.execSQL("delete from __is where __ii=\"" + str + "\"");
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0087, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065 A[SYNTHETIC, Splitter:B:25:0x0065] */
    public void b(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        SQLiteDatabase sQLiteDatabase3 = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                if (z2) {
                    if (z) {
                        sQLiteDatabase.execSQL("delete from __sd");
                    }
                } else if (this.i.size() > 0) {
                    int i2 = 0;
                    while (true) {
                        int i3 = i2;
                        if (i3 >= this.i.size()) {
                            break;
                        }
                        sQLiteDatabase.execSQL("delete from __sd where __ii=\"" + this.i.get(i3) + "\"");
                        i2 = i3 + 1;
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                sQLiteDatabase3 = sQLiteDatabase;
                try {
                    f.a(d);
                    if (sQLiteDatabase3 != null) {
                        sQLiteDatabase3.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th2) {
                    th = th2;
                    sQLiteDatabase2 = sQLiteDatabase3;
                    if (sQLiteDatabase2 != null) {
                        try {
                            sQLiteDatabase2.endTransaction();
                        } catch (Throwable th3) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabase2 = sQLiteDatabase;
                if (sQLiteDatabase2 != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th5) {
            sQLiteDatabase = null;
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), e.getBytes()), 0);
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    public String d(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), e.getBytes()));
        } catch (Exception e2) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007e, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0092 A[SYNTHETIC, Splitter:B:34:0x0092] */
    public void d() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                String c2 = q.a().c();
                if (TextUtils.isEmpty(c2)) {
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th) {
                        }
                    }
                    e.a(d).b();
                    return;
                }
                String[] strArr = new String[2];
                strArr[0] = "";
                strArr[1] = "-1";
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    sQLiteDatabase.execSQL("update __et set __i=\"" + c2 + "\" where " + "__i" + "=\"" + strArr[i2] + "\"");
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th3) {
                    }
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
        try {
            f.a(d);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            e.a(d).b();
        } catch (Throwable th5) {
            th = th5;
            if (sQLiteDatabase != null) {
            }
            e.a(d).b();
            throw th;
        }
        e.a(d).b();
    }

    public boolean e() {
        return this.i.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0083, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        if (r3 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a0, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
        if (r3 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ab, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d5, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00da, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071 A[SYNTHETIC, Splitter:B:20:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b0 A[SYNTHETIC, Splitter:B:43:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x003e] */
    public JSONObject f() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        JSONObject jSONObject;
        Cursor cursor2 = null;
        if (this.l.isEmpty()) {
            return null;
        }
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery("select *  from __is where __ii=\"" + this.l.get(0) + "\"", (String[]) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToNext()) {
                            jSONObject = new JSONObject();
                            String string = cursor.getString(cursor.getColumnIndex("__av"));
                            String string2 = cursor.getString(cursor.getColumnIndex("__vc"));
                            jSONObject.put("__av", string);
                            jSONObject.put("__vc", string2);
                            sQLiteDatabase.setTransactionSuccessful();
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Throwable th) {
                                }
                            }
                            e.a(d).b();
                            return jSONObject;
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        try {
                            f.a(d);
                            if (cursor != null) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            cursor = cursor2;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            throw th;
                        }
                    } catch (Throwable th3) {
                    }
                }
                jSONObject = null;
                sQLiteDatabase.setTransactionSuccessful();
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                return jSONObject;
            } catch (SQLiteDatabaseCorruptException e3) {
                cursor = null;
                jSONObject = null;
            } catch (Throwable th4) {
                th = th4;
                cursor = cursor2;
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            cursor = null;
            sQLiteDatabase = null;
            jSONObject = null;
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            e.a(d).b();
            throw th;
        }
        e.a(d).b();
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0083, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        if (r3 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a0, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
        if (r3 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ab, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d5, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00da, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071 A[SYNTHETIC, Splitter:B:20:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b0 A[SYNTHETIC, Splitter:B:43:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x003e] */
    public JSONObject g() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        JSONObject jSONObject;
        Cursor cursor2 = null;
        if (this.i.isEmpty()) {
            return null;
        }
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery("select *  from __sd where __ii=\"" + this.i.get(0) + "\"", (String[]) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToNext()) {
                            jSONObject = new JSONObject();
                            String string = cursor.getString(cursor.getColumnIndex("__av"));
                            String string2 = cursor.getString(cursor.getColumnIndex("__vc"));
                            jSONObject.put("__av", string);
                            jSONObject.put("__vc", string2);
                            sQLiteDatabase.setTransactionSuccessful();
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Throwable th) {
                                }
                            }
                            e.a(d).b();
                            return jSONObject;
                        }
                    } catch (SQLiteDatabaseCorruptException e2) {
                        try {
                            f.a(d);
                            if (cursor != null) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            cursor = cursor2;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            e.a(d).b();
                            throw th;
                        }
                    } catch (Throwable th3) {
                    }
                }
                jSONObject = null;
                sQLiteDatabase.setTransactionSuccessful();
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                return jSONObject;
            } catch (SQLiteDatabaseCorruptException e3) {
                cursor = null;
                jSONObject = null;
            } catch (Throwable th4) {
                th = th4;
                cursor = cursor2;
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e4) {
            cursor = null;
            sQLiteDatabase = null;
            jSONObject = null;
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            e.a(d).b();
            throw th;
        }
        e.a(d).b();
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        if (r0 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
        if (r0 != null) goto L_0x0046;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068 A[SYNTHETIC, Splitter:B:27:0x0068] */
    public void h() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                if (this.j.size() > 0) {
                    for (int i2 = 0; i2 < this.j.size(); i2++) {
                        sQLiteDatabase.execSQL("delete from __et where rowid=" + this.j.get(i2));
                    }
                }
                this.j.clear();
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r0 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r0 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a A[SYNTHETIC, Splitter:B:21:0x003a] */
    public void i() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a(d).a();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("delete from __er");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLiteDatabaseCorruptException e2) {
                try {
                    f.a(d);
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    e.a(d).b();
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    e.a(d).b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                }
                e.a(d).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
        } catch (Throwable th4) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        if (r0 == null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x004f, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0065 A[SYNTHETIC, Splitter:B:15:0x0065] */
    public void j() {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2;
        Throwable th;
        if (!TextUtils.isEmpty(this.k)) {
            try {
                sQLiteDatabase = e.a(d).a();
                try {
                    sQLiteDatabase.beginTransaction();
                    sQLiteDatabase.execSQL("delete from __er where __i=\"" + this.k + "\"");
                    sQLiteDatabase.execSQL("delete from __et where __i=\"" + this.k + "\"");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLiteDatabaseCorruptException e2) {
                    try {
                        f.a(d);
                    } catch (Throwable th2) {
                        sQLiteDatabase2 = sQLiteDatabase;
                        th = th2;
                        if (sQLiteDatabase2 != null) {
                            try {
                                sQLiteDatabase2.endTransaction();
                            } catch (Throwable th3) {
                            }
                        }
                        e.a(d).b();
                        throw th;
                    }
                } catch (Throwable th4) {
                }
            } catch (SQLiteDatabaseCorruptException e3) {
                sQLiteDatabase = null;
            } catch (Throwable th5) {
                sQLiteDatabase2 = null;
                th = th5;
                if (sQLiteDatabase2 != null) {
                }
                e.a(d).b();
                throw th;
            }
        }
        this.k = null;
        e.a(d).b();
        this.k = null;
    }
}
