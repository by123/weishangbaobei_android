package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.umeng.analytics.pro.b;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.process.DBFileTraversalUtil;
import com.umeng.analytics.process.a;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private InsertEventCallback ekvCallBack = new InsertEventCallback();
    /* access modifiers changed from: private */
    public Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();

    private class InsertEventCallback implements FileLockCallback {
        private InsertEventCallback() {
        }

        public boolean onFileLock(File file, int i) {
            return false;
        }

        public boolean onFileLock(String str) {
            return false;
        }

        public boolean onFileLock(String str, Object obj) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith(a.c)) {
                    str = str.replaceFirst(a.c, "");
                }
                UMProcessDBHelper.this.insertEvents(str.replace(a.d, ""), (JSONArray) obj);
            }
            return true;
        }
    }

    private class ProcessToMainCallback implements FileLockCallback {
        private ProcessToMainCallback() {
        }

        public boolean onFileLock(File file, int i) {
            return false;
        }

        public boolean onFileLock(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith(a.c)) {
                    str = str.replaceFirst(a.c, "");
                }
                UMProcessDBHelper.this.processToMain(str.replace(a.d, ""));
            }
            return true;
        }

        public boolean onFileLock(String str, Object obj) {
            return false;
        }
    }

    private class a implements Serializable {
        int a;
        String b;
        String c;
        String d;
        int e;
        String f;
        String g;
        String h;

        private a() {
        }
    }

    private UMProcessDBHelper() {
    }

    private UMProcessDBHelper(Context context) {
        w.a().a(context);
    }

    private List<a> datasAdapter(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a();
                aVar.c = jSONObject.optString("id");
                aVar.g = UMUtils.getAppVersionName(this.mContext);
                aVar.h = UMUtils.getAppVersionCode(this.mContext);
                aVar.b = jSONObject.optString("__i");
                aVar.e = jSONObject.optInt("__t");
                aVar.f = str;
                if (jSONObject.has(b.ac)) {
                    jSONObject.remove(b.ac);
                }
                jSONObject.put(b.ac, getDataSource());
                jSONObject.remove("__i");
                jSONObject.remove("__t");
                aVar.d = w.a().a(jSONObject.toString());
                jSONObject.remove(b.ac);
                arrayList.add(aVar);
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    private boolean dbIsExists(String str) {
        return new File(b.b(this.mContext, str)).exists();
    }

    private int getDataSource() {
        return 0;
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                try {
                    if (mInstance == null) {
                        mInstance = new UMProcessDBHelper(context);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<UMProcessDBHelper> cls = UMProcessDBHelper.class;
                        throw th;
                    }
                }
            }
        }
        mInstance.mContext = context;
        return mInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088 A[SYNTHETIC, Splitter:B:24:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x001c] */
    private boolean insertEvents_(String str, List<a> list) {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        if (TextUtils.isEmpty(str) || list == null) {
            return true;
        }
        if (list.isEmpty()) {
            return true;
        }
        try {
            sQLiteDatabase2 = c.a(this.mContext).a(str);
            try {
                sQLiteDatabase2.beginTransaction();
                for (a next : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("__i", next.b);
                    contentValues.put("__e", next.c);
                    contentValues.put("__t", Integer.valueOf(next.e));
                    contentValues.put(a.C0006a.f, next.f);
                    contentValues.put("__av", next.g);
                    contentValues.put("__vc", next.h);
                    contentValues.put("__s", next.d);
                    sQLiteDatabase2.insert(a.C0006a.a, (String) null, contentValues);
                }
                sQLiteDatabase2.setTransactionSuccessful();
                if (sQLiteDatabase2 != null) {
                    try {
                        sQLiteDatabase2.endTransaction();
                    } catch (Throwable th) {
                    }
                }
                c.a(this.mContext).b(str);
                return true;
            } catch (Exception e) {
                sQLiteDatabase = sQLiteDatabase2;
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th2) {
                    }
                }
                c.a(this.mContext).b(str);
                return false;
            } catch (Throwable th3) {
            }
        } catch (Exception e2) {
            sQLiteDatabase = null;
        } catch (Throwable th4) {
            th = th4;
            if (sQLiteDatabase2 != null) {
            }
            c.a(this.mContext).b(str);
            throw th;
        }
    }

    private boolean processIsService(Context context) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void processToMain(String str) {
        if (dbIsExists(str)) {
            List<a> readEventByProcess = readEventByProcess(str);
            if (!readEventByProcess.isEmpty() && insertEvents_(a.h, readEventByProcess)) {
                deleteEventDatas(str, (String) null, readEventByProcess);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0090, code lost:
        if (r3 != null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0092, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a4, code lost:
        if (r3 != null) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab A[SYNTHETIC, Splitter:B:27:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b0 A[Catch:{ Exception -> 0x00c8 }] */
    private List<a> readEventByProcess(String str) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        ArrayList arrayList = new ArrayList();
        try {
            sQLiteDatabase = c.a(this.mContext).a(str);
            try {
                sQLiteDatabase.beginTransaction();
                cursor = sQLiteDatabase.rawQuery("select *  from __et_p", (String[]) null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            a aVar = new a();
                            aVar.a = cursor.getInt(0);
                            aVar.b = cursor.getString(cursor.getColumnIndex("__i"));
                            aVar.c = cursor.getString(cursor.getColumnIndex("__e"));
                            aVar.d = cursor.getString(cursor.getColumnIndex("__s"));
                            aVar.e = cursor.getInt(cursor.getColumnIndex("__t"));
                            aVar.f = cursor.getString(cursor.getColumnIndex(a.C0006a.f));
                            aVar.g = cursor.getString(cursor.getColumnIndex("__av"));
                            aVar.h = cursor.getString(cursor.getColumnIndex("__vc"));
                            arrayList.add(aVar);
                        } catch (Exception e) {
                            e = e;
                            try {
                                e.printStackTrace();
                                if (cursor != null) {
                                    try {
                                        cursor.close();
                                    } catch (Exception e2) {
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (cursor != null) {
                                }
                                if (sQLiteDatabase != null) {
                                }
                                c.a(this.mContext).b(str);
                                throw th;
                            }
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e4) {
                        c.a(this.mContext).b(str);
                        throw th;
                    }
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                c.a(this.mContext).b(str);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            cursor = null;
            sQLiteDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            c.a(this.mContext).b(str);
            throw th;
        }
        c.a(this.mContext).b(str);
        return arrayList;
    }

    public void createDBByProcess(String str) {
        try {
            c.a(this.mContext).a(str);
            c.a(this.mContext).b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        if (r0 == null) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    public void deleteEventDatas(String str, String str2, List<a> list) {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                sQLiteDatabase2 = c.a(this.mContext).a(str);
                try {
                    sQLiteDatabase2.beginTransaction();
                    int size = list.size();
                    if (list == null || size <= 0) {
                        sQLiteDatabase2.delete(a.C0006a.a, (String) null, (String[]) null);
                    } else {
                        for (int i = 0; i < size; i++) {
                            sQLiteDatabase2.execSQL("delete from __et_p where rowid=" + list.get(i).a);
                        }
                    }
                    sQLiteDatabase2.setTransactionSuccessful();
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase = sQLiteDatabase2;
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e) {
                    sQLiteDatabase = sQLiteDatabase2;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.endTransaction();
                    }
                    c.a(this.mContext).b(str);
                    throw th;
                }
            } catch (Exception e2) {
                sQLiteDatabase = null;
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase2 != null) {
                }
                c.a(this.mContext).b(str);
                throw th;
            }
            c.a(this.mContext).b(str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        if (r0 == null) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    public void deleteMainProcessEventDatasByIds(List<Integer> list) {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase2 = c.a(this.mContext).a(a.h);
            try {
                sQLiteDatabase2.beginTransaction();
                for (Integer valueOf : list) {
                    sQLiteDatabase2.delete(a.C0006a.a, "id=?", new String[]{String.valueOf(valueOf)});
                }
                sQLiteDatabase2.setTransactionSuccessful();
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase = sQLiteDatabase2;
                    sQLiteDatabase.endTransaction();
                }
            } catch (Exception e) {
                sQLiteDatabase = sQLiteDatabase2;
            } catch (Throwable th) {
                th = th;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.endTransaction();
                }
                c.a(this.mContext).b(a.h);
                throw th;
            }
        } catch (Exception e2) {
            sQLiteDatabase = null;
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase2 != null) {
            }
            c.a(this.mContext).b(a.h);
            throw th;
        }
        c.a(this.mContext).b(a.h);
    }

    public void insertEvents(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
        }
    }

    public void insertEventsInSubProcess(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            File file = new File(b.b(this.mContext, str));
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, (FileLockCallback) this.ekvCallBack, (Object) jSONArray);
            } else {
                insertEvents(str, jSONArray);
            }
        }
    }

    public void processDBToMain() {
        try {
            DBFileTraversalUtil.traverseDBFiles(b.a(this.mContext), new ProcessToMainCallback(), new DBFileTraversalUtil.a() {
                public void a() {
                    if (AnalyticsConstants.SUB_PROCESS_EVENT) {
                        UMWorkDispatch.sendEvent(UMProcessDBHelper.this.mContext, UMProcessDBDatasSender.UM_PROCESS_CONSTRUCTMESSAGE, UMProcessDBDatasSender.getInstance(UMProcessDBHelper.this.mContext), (Object) null);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011c, code lost:
        if (r4 != null) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0179, code lost:
        if (r4 != null) goto L_0x011e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013e A[SYNTHETIC, Splitter:B:55:0x013e] */
    public JSONObject readMainEvents(long j, List<Integer> list) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        JSONObject jSONObject = new JSONObject();
        Cursor cursor = null;
        Cursor cursor2 = null;
        try {
            sQLiteDatabase = c.a(this.mContext).a(a.h);
            try {
                sQLiteDatabase.beginTransaction();
                Cursor rawQuery = sQLiteDatabase.rawQuery("select *  from __et_p", (String[]) null);
                if (rawQuery != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        String str = "";
                        while (rawQuery.moveToNext()) {
                            int i = rawQuery.getInt(rawQuery.getColumnIndex("id"));
                            int i2 = rawQuery.getInt(rawQuery.getColumnIndex("__t"));
                            String string = rawQuery.getString(rawQuery.getColumnIndex("__i"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex("__s"));
                            String string3 = rawQuery.getString(rawQuery.getColumnIndex(a.C0006a.f));
                            String string4 = rawQuery.getString(rawQuery.getColumnIndex("__av"));
                            if (!TextUtils.isEmpty(string)) {
                                if (TextUtils.isEmpty(str)) {
                                    str = string4;
                                }
                                if (!TextUtils.isEmpty(string2) && i2 == 2049) {
                                    JSONObject jSONObject3 = new JSONObject(w.a().b(string2));
                                    String optString = jSONObject3.optString(b.ad);
                                    if (TextUtils.isEmpty(optString) || "unknown".equals(optString)) {
                                        jSONObject3.put(b.ad, this.mContext.getPackageName() + ":" + string3);
                                    }
                                    JSONArray optJSONArray = jSONObject2.has(string) ? jSONObject2.optJSONArray(string) : new JSONArray();
                                    if (m.a(optJSONArray) + m.a(jSONObject3) <= j) {
                                        if (!str.equalsIgnoreCase(string4)) {
                                            break;
                                        }
                                        list.add(Integer.valueOf(i));
                                        optJSONArray.put(jSONObject3);
                                        jSONObject2.put(string, optJSONArray);
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        if (jSONObject2.length() > 0) {
                            JSONArray jSONArray = new JSONArray();
                            Iterator<String> keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                JSONObject jSONObject4 = new JSONObject();
                                String next = keys.next();
                                jSONObject4.put(next, new JSONArray(jSONObject2.optString(next)));
                                if (jSONObject4.length() > 0) {
                                    jSONArray.put(jSONObject4);
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(b.R, jSONArray);
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor2 = rawQuery;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = rawQuery;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        c.a(this.mContext).b(a.h);
                        throw th;
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                c.a(this.mContext).b(a.h);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            sQLiteDatabase = null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            c.a(this.mContext).b(a.h);
            throw th;
        }
        c.a(this.mContext).b(a.h);
        return jSONObject;
        try {
            e.printStackTrace();
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th5) {
            th = th5;
            cursor = cursor2;
            th = th;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th6) {
                }
            }
            c.a(this.mContext).b(a.h);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0080, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        r3 = r0;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009c, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b5, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b6, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f A[SYNTHETIC, Splitter:B:19:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[Catch:{ Exception -> 0x00bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[SYNTHETIC, Splitter:B:30:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0080 A[Catch:{ Exception -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097 A[SYNTHETIC, Splitter:B:40:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009c A[Catch:{ Exception -> 0x00b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b5 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0028] */
    public JSONObject readVersionInfoFromColumId(Integer num) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        JSONObject jSONObject;
        Exception e;
        String str = "select *  from __et_p where rowid=" + num;
        try {
            sQLiteDatabase = c.a(this.mContext).a(a.h);
            try {
                sQLiteDatabase.beginTransaction();
                Cursor rawQuery = sQLiteDatabase.rawQuery(str, (String[]) null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.moveToNext()) {
                            jSONObject = new JSONObject();
                            String string = rawQuery.getString(rawQuery.getColumnIndex("__av"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex("__vc"));
                            if (!TextUtils.isEmpty(string)) {
                                jSONObject.put("__av", string);
                            }
                            if (!TextUtils.isEmpty(string2)) {
                                jSONObject.put("__vc", string2);
                            }
                            if (rawQuery != null) {
                                try {
                                    rawQuery.close();
                                } catch (Exception e2) {
                                }
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            c.a(this.mContext).b(a.h);
                            return jSONObject;
                        }
                    } catch (Exception e3) {
                        cursor = rawQuery;
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            c.a(this.mContext).b(a.h);
                            return jSONObject;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            th = th3;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            c.a(this.mContext).b(a.h);
                            throw th;
                        }
                    } catch (Throwable th4) {
                    }
                }
                jSONObject = null;
                if (rawQuery != null) {
                }
                if (sQLiteDatabase != null) {
                }
                c.a(this.mContext).b(a.h);
            } catch (Exception e4) {
                e = e4;
            } catch (Throwable th5) {
                th = th5;
                cursor = null;
                if (cursor != null) {
                }
                if (sQLiteDatabase != null) {
                }
                c.a(this.mContext).b(a.h);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            sQLiteDatabase = null;
        } catch (Throwable th6) {
            th = th6;
            cursor = null;
            sQLiteDatabase = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase != null) {
            }
            c.a(this.mContext).b(a.h);
            throw th;
        }
        return jSONObject;
        cursor = null;
        jSONObject = null;
        e = e;
        e.printStackTrace();
        if (cursor != null) {
        }
        if (sQLiteDatabase != null) {
        }
        c.a(this.mContext).b(a.h);
        return jSONObject;
    }
}
