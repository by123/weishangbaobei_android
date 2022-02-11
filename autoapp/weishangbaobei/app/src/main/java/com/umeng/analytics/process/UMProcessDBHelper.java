package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.b;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.process.DBFileTraversalUtil;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private InsertEventCallback ekvCallBack = new InsertEventCallback();
    /* access modifiers changed from: private */
    public Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();

    private int getDataSource() {
        return 0;
    }

    private class InsertEventCallback implements FileLockCallback {
        public boolean onFileLock(File file, int i) {
            return false;
        }

        public boolean onFileLock(String str) {
            return false;
        }

        private InsertEventCallback() {
        }

        public boolean onFileLock(String str, Object obj) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(a.c)) {
                str = str.replaceFirst(a.c, "");
            }
            UMProcessDBHelper.this.insertEvents(str.replace(a.d, ""), (JSONArray) obj);
            return true;
        }
    }

    private UMProcessDBHelper() {
    }

    private UMProcessDBHelper(Context context) {
        w.a().a(context);
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBHelper(context);
                }
            }
        }
        mInstance.mContext = context;
        return mInstance;
    }

    public void createDBByProcess(String str) {
        try {
            c.a(this.mContext).a(str);
            c.a(this.mContext).b(str);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void insertEvents(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
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

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014a, code lost:
        if (r4 != null) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x015d, code lost:
        if (r4 != null) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0177 A[SYNTHETIC, Splitter:B:76:0x0177] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readMainEvents(long r17, java.util.List<java.lang.Integer> r19) {
        /*
            r16 = this;
            r1 = r16
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r0 = ""
            r3 = 0
            android.content.Context r4 = r1.mContext     // Catch:{ Exception -> 0x0153, all -> 0x014f }
            com.umeng.analytics.process.c r4 = com.umeng.analytics.process.c.a((android.content.Context) r4)     // Catch:{ Exception -> 0x0153, all -> 0x014f }
            java.lang.String r5 = "_main_"
            android.database.sqlite.SQLiteDatabase r4 = r4.a((java.lang.String) r5)     // Catch:{ Exception -> 0x0153, all -> 0x014f }
            r4.beginTransaction()     // Catch:{ Exception -> 0x014d }
            java.lang.String r5 = "select *  from __et_p"
            android.database.Cursor r5 = r4.rawQuery(r5, r3)     // Catch:{ Exception -> 0x014d }
            if (r5 == 0) goto L_0x0142
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r3.<init>()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
        L_0x0026:
            boolean r6 = r5.moveToNext()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r6 == 0) goto L_0x00fb
            java.lang.String r6 = "id"
            int r6 = r5.getColumnIndex(r6)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            int r6 = r5.getInt(r6)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r7 = "__t"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            int r7 = r5.getInt(r7)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r8 = "__i"
            int r8 = r5.getColumnIndex(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r8 = r5.getString(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = "__s"
            int r9 = r5.getColumnIndex(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = r5.getString(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r10 = "__pn"
            int r10 = r5.getColumnIndex(r10)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r10 = r5.getString(r10)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r11 = "__av"
            int r11 = r5.getColumnIndex(r11)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r11 = r5.getString(r11)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            boolean r12 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r12 == 0) goto L_0x006f
            goto L_0x0026
        L_0x006f:
            boolean r12 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r12 == 0) goto L_0x0076
            r0 = r11
        L_0x0076:
            boolean r12 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r12 != 0) goto L_0x00f7
            r12 = 2049(0x801, float:2.871E-42)
            if (r7 != r12) goto L_0x00f7
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            com.umeng.analytics.pro.w r12 = com.umeng.analytics.pro.w.a()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = r12.b(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r7.<init>(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = "pn"
            java.lang.String r9 = r7.optString(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            boolean r12 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r12 != 0) goto L_0x00a1
            java.lang.String r12 = "unknown"
            boolean r9 = r12.equals(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r9 == 0) goto L_0x00c0
        L_0x00a1:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r9.<init>()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            android.content.Context r12 = r1.mContext     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r12 = r12.getPackageName()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r9.append(r12)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r12 = ":"
            r9.append(r12)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r9.append(r10)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r10 = "pn"
            r7.put(r10, r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
        L_0x00c0:
            boolean r9 = r3.has(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r9 == 0) goto L_0x00cb
            org.json.JSONArray r9 = r3.optJSONArray(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            goto L_0x00d0
        L_0x00cb:
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r9.<init>()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
        L_0x00d0:
            long r12 = com.umeng.analytics.pro.m.a((org.json.JSONArray) r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            long r14 = com.umeng.analytics.pro.m.a((org.json.JSONObject) r7)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r10 = 0
            long r14 = r14 + r12
            int r10 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r10 <= 0) goto L_0x00df
            goto L_0x00fb
        L_0x00df:
            boolean r10 = r0.equalsIgnoreCase(r11)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r10 != 0) goto L_0x00e6
            goto L_0x00fb
        L_0x00e6:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r10 = r19
            r10.add(r6)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r9.put(r7)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r3.put(r8, r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            goto L_0x0026
        L_0x00f7:
            r10 = r19
            goto L_0x0026
        L_0x00fb:
            int r0 = r3.length()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r0 <= 0) goto L_0x0142
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r0.<init>()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.util.Iterator r6 = r3.keys()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
        L_0x010a:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r7 == 0) goto L_0x0131
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r7.<init>()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.Object r8 = r6.next()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            java.lang.String r9 = r3.optString(r8)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            org.json.JSONArray r10 = new org.json.JSONArray     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r10.<init>(r9)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            r7.put(r8, r10)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            int r8 = r7.length()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r8 <= 0) goto L_0x010a
            r0.put(r7)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            goto L_0x010a
        L_0x0131:
            int r3 = r0.length()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r3 <= 0) goto L_0x0142
            java.lang.String r3 = "ekv"
            r2.put(r3, r0)     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            goto L_0x0142
        L_0x013d:
            r0 = move-exception
            goto L_0x0170
        L_0x013f:
            r0 = move-exception
            r3 = r5
            goto L_0x0155
        L_0x0142:
            r4.setTransactionSuccessful()     // Catch:{ Exception -> 0x013f, all -> 0x013d }
            if (r5 == 0) goto L_0x014a
            r5.close()
        L_0x014a:
            if (r4 == 0) goto L_0x0162
            goto L_0x015f
        L_0x014d:
            r0 = move-exception
            goto L_0x0155
        L_0x014f:
            r0 = move-exception
            r4 = r3
            r5 = r4
            goto L_0x0170
        L_0x0153:
            r0 = move-exception
            r4 = r3
        L_0x0155:
            r0.printStackTrace()     // Catch:{ all -> 0x016e }
            if (r3 == 0) goto L_0x015d
            r3.close()
        L_0x015d:
            if (r4 == 0) goto L_0x0162
        L_0x015f:
            r4.endTransaction()     // Catch:{ Throwable -> 0x0162 }
        L_0x0162:
            android.content.Context r0 = r1.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            java.lang.String r3 = "_main_"
            r0.b(r3)
            return r2
        L_0x016e:
            r0 = move-exception
            r5 = r3
        L_0x0170:
            if (r5 == 0) goto L_0x0175
            r5.close()
        L_0x0175:
            if (r4 == 0) goto L_0x017a
            r4.endTransaction()     // Catch:{ Throwable -> 0x017a }
        L_0x017a:
            android.content.Context r2 = r1.mContext
            com.umeng.analytics.process.c r2 = com.umeng.analytics.process.c.a((android.content.Context) r2)
            java.lang.String r3 = "_main_"
            r2.b(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readMainEvents(long, java.util.List):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        if (r1 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        com.umeng.analytics.process.c.a(r6.mContext).b(com.umeng.analytics.process.a.h);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r1 != null) goto L_0x0052;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteMainProcessEventDatasByIds(java.util.List<java.lang.Integer> r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = r6.mContext     // Catch:{ Exception -> 0x004f, all -> 0x003c }
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.c.a((android.content.Context) r1)     // Catch:{ Exception -> 0x004f, all -> 0x003c }
            java.lang.String r2 = "_main_"
            android.database.sqlite.SQLiteDatabase r1 = r1.a((java.lang.String) r2)     // Catch:{ Exception -> 0x004f, all -> 0x003c }
            r1.beginTransaction()     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
        L_0x0014:
            boolean r0 = r7.hasNext()     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r7.next()     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            java.lang.String r2 = "__et_p"
            java.lang.String r3 = "id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            r5 = 0
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            r4[r5] = r0     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            r1.delete(r2, r3, r4)     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            goto L_0x0014
        L_0x0032:
            r1.setTransactionSuccessful()     // Catch:{ Exception -> 0x003a, all -> 0x0038 }
            if (r1 == 0) goto L_0x0055
            goto L_0x0052
        L_0x0038:
            r7 = move-exception
            goto L_0x003e
        L_0x003a:
            goto L_0x0050
        L_0x003c:
            r7 = move-exception
            r1 = r0
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.endTransaction()
        L_0x0043:
            android.content.Context r0 = r6.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            java.lang.String r1 = "_main_"
            r0.b(r1)
            throw r7
        L_0x004f:
            r1 = r0
        L_0x0050:
            if (r1 == 0) goto L_0x0055
        L_0x0052:
            r1.endTransaction()
        L_0x0055:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a((android.content.Context) r7)
            java.lang.String r0 = "_main_"
            r7.b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteMainProcessEventDatasByIds(java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r0 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        if (r0 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        com.umeng.analytics.process.c.a(r4.mContext).b(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006f, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteEventDatas(java.lang.String r5, java.lang.String r6, java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> r7) {
        /*
            r4 = this;
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L_0x0007
            return
        L_0x0007:
            r6 = 0
            android.content.Context r0 = r4.mContext     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            android.database.sqlite.SQLiteDatabase r0 = r0.a((java.lang.String) r5)     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            r0.beginTransaction()     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            int r1 = r7.size()     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            if (r7 == 0) goto L_0x003f
            if (r1 <= 0) goto L_0x003f
            r6 = 0
        L_0x001e:
            if (r6 >= r1) goto L_0x0044
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            r2.<init>()     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            java.lang.String r3 = "delete from __et_p where rowid="
            r2.append(r3)     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            java.lang.Object r3 = r7.get(r6)     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            com.umeng.analytics.process.UMProcessDBHelper$a r3 = (com.umeng.analytics.process.UMProcessDBHelper.a) r3     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            int r3 = r3.a     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            r2.append(r3)     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            r0.execSQL(r2)     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            int r6 = r6 + 1
            goto L_0x001e
        L_0x003f:
            java.lang.String r7 = "__et_p"
            r0.delete(r7, r6, r6)     // Catch:{ Exception -> 0x004c, all -> 0x004a }
        L_0x0044:
            r0.setTransactionSuccessful()     // Catch:{ Exception -> 0x004c, all -> 0x004a }
            if (r0 == 0) goto L_0x0066
            goto L_0x0063
        L_0x004a:
            r6 = move-exception
            goto L_0x0051
        L_0x004c:
            goto L_0x0061
        L_0x004e:
            r7 = move-exception
            r0 = r6
            r6 = r7
        L_0x0051:
            if (r0 == 0) goto L_0x0056
            r0.endTransaction()
        L_0x0056:
            android.content.Context r7 = r4.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a((android.content.Context) r7)
            r7.b(r5)
            throw r6
        L_0x0060:
            r0 = r6
        L_0x0061:
            if (r0 == 0) goto L_0x0066
        L_0x0063:
            r0.endTransaction()
        L_0x0066:
            android.content.Context r6 = r4.mContext
            com.umeng.analytics.process.c r6 = com.umeng.analytics.process.c.a((android.content.Context) r6)
            r6.b(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteEventDatas(java.lang.String, java.lang.String, java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0081, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0087 A[SYNTHETIC, Splitter:B:28:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0098 A[SYNTHETIC, Splitter:B:38:0x0098] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean insertEvents_(java.lang.String r8, java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> r9) {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 1
            if (r0 != 0) goto L_0x00a5
            if (r9 == 0) goto L_0x00a5
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x0011
            goto L_0x00a5
        L_0x0011:
            r0 = 0
            android.content.Context r2 = r7.mContext     // Catch:{ Exception -> 0x0094, all -> 0x0083 }
            com.umeng.analytics.process.c r2 = com.umeng.analytics.process.c.a((android.content.Context) r2)     // Catch:{ Exception -> 0x0094, all -> 0x0083 }
            android.database.sqlite.SQLiteDatabase r2 = r2.a((java.lang.String) r8)     // Catch:{ Exception -> 0x0094, all -> 0x0083 }
            r2.beginTransaction()     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
        L_0x0023:
            boolean r3 = r9.hasNext()     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
            if (r3 == 0) goto L_0x006f
            java.lang.Object r3 = r9.next()     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
            com.umeng.analytics.process.UMProcessDBHelper$a r3 = (com.umeng.analytics.process.UMProcessDBHelper.a) r3     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.<init>()     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__i"
            java.lang.String r6 = r3.b     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__e"
            java.lang.String r6 = r3.c     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__t"
            int r6 = r3.e     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__pn"
            java.lang.String r6 = r3.f     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__av"
            java.lang.String r6 = r3.g     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__vc"
            java.lang.String r6 = r3.h     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r5 = "__s"
            java.lang.String r3 = r3.d     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            r4.put(r5, r3)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            java.lang.String r3 = "__et_p"
            r2.insert(r3, r0, r4)     // Catch:{ Exception -> 0x0023, all -> 0x0081 }
            goto L_0x0023
        L_0x006f:
            r2.setTransactionSuccessful()     // Catch:{ Exception -> 0x0095, all -> 0x0081 }
            if (r2 == 0) goto L_0x0077
            r2.endTransaction()     // Catch:{ Throwable -> 0x0077 }
        L_0x0077:
            android.content.Context r9 = r7.mContext
            com.umeng.analytics.process.c r9 = com.umeng.analytics.process.c.a((android.content.Context) r9)
            r9.b(r8)
            return r1
        L_0x0081:
            r9 = move-exception
            goto L_0x0085
        L_0x0083:
            r9 = move-exception
            r2 = r0
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r2.endTransaction()     // Catch:{ Throwable -> 0x008a }
        L_0x008a:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            r0.b(r8)
            throw r9
        L_0x0094:
            r2 = r0
        L_0x0095:
            r9 = 0
            if (r2 == 0) goto L_0x009b
            r2.endTransaction()     // Catch:{ Throwable -> 0x009b }
        L_0x009b:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            r0.b(r8)
            return r9
        L_0x00a5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.insertEvents_(java.lang.String, java.util.List):boolean");
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
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r7v5, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0092 A[SYNTHETIC, Splitter:B:41:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0097 A[Catch:{ Exception -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00aa A[SYNTHETIC, Splitter:B:49:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00af A[Catch:{ Exception -> 0x00b2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readVersionInfoFromColumId(java.lang.Integer r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "select *  from __et_p where rowid="
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r0 = 0
            android.content.Context r1 = r6.mContext     // Catch:{ Exception -> 0x0088, all -> 0x0083 }
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.c.a((android.content.Context) r1)     // Catch:{ Exception -> 0x0088, all -> 0x0083 }
            java.lang.String r2 = "_main_"
            android.database.sqlite.SQLiteDatabase r1 = r1.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0088, all -> 0x0083 }
            r1.beginTransaction()     // Catch:{ Exception -> 0x0080, all -> 0x007b }
            android.database.Cursor r7 = r1.rawQuery(r7, r0)     // Catch:{ Exception -> 0x0080, all -> 0x007b }
            if (r7 == 0) goto L_0x0065
            boolean r2 = r7.moveToNext()     // Catch:{ Exception -> 0x0060 }
            if (r2 == 0) goto L_0x0065
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0060 }
            r2.<init>()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r0 = "__av"
            int r0 = r7.getColumnIndex(r0)     // Catch:{ Exception -> 0x005e }
            java.lang.String r0 = r7.getString(r0)     // Catch:{ Exception -> 0x005e }
            java.lang.String r3 = "__vc"
            int r3 = r7.getColumnIndex(r3)     // Catch:{ Exception -> 0x005e }
            java.lang.String r3 = r7.getString(r3)     // Catch:{ Exception -> 0x005e }
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x005e }
            if (r4 != 0) goto L_0x0051
            java.lang.String r4 = "__av"
            r2.put(r4, r0)     // Catch:{ Exception -> 0x005e }
        L_0x0051:
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x005e }
            if (r0 != 0) goto L_0x005c
            java.lang.String r0 = "__vc"
            r2.put(r0, r3)     // Catch:{ Exception -> 0x005e }
        L_0x005c:
            r0 = r2
            goto L_0x0065
        L_0x005e:
            r0 = move-exception
            goto L_0x008d
        L_0x0060:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x008d
        L_0x0065:
            if (r7 == 0) goto L_0x006a
            r7.close()     // Catch:{ Exception -> 0x006f }
        L_0x006a:
            if (r1 == 0) goto L_0x006f
            r1.endTransaction()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a((android.content.Context) r7)
            java.lang.String r1 = "_main_"
            r7.b(r1)
            goto L_0x00a6
        L_0x007b:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x00a8
        L_0x0080:
            r7 = move-exception
            r2 = r0
            goto L_0x008b
        L_0x0083:
            r7 = move-exception
            r1 = r0
            r0 = r7
            r7 = r1
            goto L_0x00a8
        L_0x0088:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x008b:
            r0 = r7
            r7 = r2
        L_0x008d:
            r0.printStackTrace()     // Catch:{ all -> 0x00a7 }
            if (r7 == 0) goto L_0x0095
            r7.close()     // Catch:{ Exception -> 0x009a }
        L_0x0095:
            if (r1 == 0) goto L_0x009a
            r1.endTransaction()     // Catch:{ Exception -> 0x009a }
        L_0x009a:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a((android.content.Context) r7)
            java.lang.String r0 = "_main_"
            r7.b(r0)
            r0 = r2
        L_0x00a6:
            return r0
        L_0x00a7:
            r0 = move-exception
        L_0x00a8:
            if (r7 == 0) goto L_0x00ad
            r7.close()     // Catch:{ Exception -> 0x00b2 }
        L_0x00ad:
            if (r1 == 0) goto L_0x00b2
            r1.endTransaction()     // Catch:{ Exception -> 0x00b2 }
        L_0x00b2:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a((android.content.Context) r7)
            java.lang.String r1 = "_main_"
            r7.b(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readVersionInfoFromColumId(java.lang.Integer):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008c, code lost:
        if (r3 != null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a7, code lost:
        if (r3 != null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a9, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a4 A[SYNTHETIC, Splitter:B:26:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b9 A[SYNTHETIC, Splitter:B:35:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00be A[Catch:{ Exception -> 0x00c1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> readEventByProcess(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "select *  from __et_p"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.content.Context r3 = r7.mContext     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            com.umeng.analytics.process.c r3 = com.umeng.analytics.process.c.a((android.content.Context) r3)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            android.database.sqlite.SQLiteDatabase r3 = r3.a((java.lang.String) r8)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            r3.beginTransaction()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            android.database.Cursor r0 = r3.rawQuery(r0, r2)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r0 == 0) goto L_0x0087
        L_0x001b:
            boolean r4 = r0.moveToNext()     // Catch:{ Exception -> 0x0085 }
            if (r4 == 0) goto L_0x0087
            com.umeng.analytics.process.UMProcessDBHelper$a r4 = new com.umeng.analytics.process.UMProcessDBHelper$a     // Catch:{ Exception -> 0x0085 }
            r4.<init>()     // Catch:{ Exception -> 0x0085 }
            r5 = 0
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0085 }
            r4.a = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__i"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.b = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__e"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.c = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__s"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.d = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__t"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0085 }
            r4.e = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__pn"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.f = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__av"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.g = r5     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "__vc"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0085 }
            r4.h = r5     // Catch:{ Exception -> 0x0085 }
            r1.add(r4)     // Catch:{ Exception -> 0x0085 }
            goto L_0x001b
        L_0x0085:
            r2 = move-exception
            goto L_0x009f
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            r0.close()     // Catch:{ Exception -> 0x00ac }
        L_0x008c:
            if (r3 == 0) goto L_0x00ac
            goto L_0x00a9
        L_0x008f:
            r1 = move-exception
            r0 = r2
            goto L_0x00b7
        L_0x0092:
            r0 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x009f
        L_0x0097:
            r1 = move-exception
            r0 = r2
            r3 = r0
            goto L_0x00b7
        L_0x009b:
            r0 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
        L_0x009f:
            r2.printStackTrace()     // Catch:{ all -> 0x00b6 }
            if (r0 == 0) goto L_0x00a7
            r0.close()     // Catch:{ Exception -> 0x00ac }
        L_0x00a7:
            if (r3 == 0) goto L_0x00ac
        L_0x00a9:
            r3.endTransaction()     // Catch:{ Exception -> 0x00ac }
        L_0x00ac:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            r0.b(r8)
            return r1
        L_0x00b6:
            r1 = move-exception
        L_0x00b7:
            if (r0 == 0) goto L_0x00bc
            r0.close()     // Catch:{ Exception -> 0x00c1 }
        L_0x00bc:
            if (r3 == 0) goto L_0x00c1
            r3.endTransaction()     // Catch:{ Exception -> 0x00c1 }
        L_0x00c1:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a((android.content.Context) r0)
            r0.b(r8)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readEventByProcess(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean dbIsExists(java.lang.String r3) {
        /*
            r2 = this;
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            android.content.Context r1 = r2.mContext     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            java.lang.String r3 = com.umeng.analytics.process.b.b(r1, r3)     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            boolean r3 = r0.exists()     // Catch:{ Throwable -> 0x0015, all -> 0x0013 }
            if (r3 == 0) goto L_0x0015
            r3 = 1
            return r3
        L_0x0013:
            r3 = move-exception
            throw r3
        L_0x0015:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.dbIsExists(java.lang.String):boolean");
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

    private class ProcessToMainCallback implements FileLockCallback {
        public boolean onFileLock(File file, int i) {
            return false;
        }

        public boolean onFileLock(String str, Object obj) {
            return false;
        }

        private ProcessToMainCallback() {
        }

        public boolean onFileLock(String str) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(a.c)) {
                str = str.replaceFirst(a.c, "");
            }
            UMProcessDBHelper.this.processToMain(str.replace(a.d, ""));
            return true;
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
        } catch (Exception unused) {
        }
    }

    private boolean processIsService(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }
}
