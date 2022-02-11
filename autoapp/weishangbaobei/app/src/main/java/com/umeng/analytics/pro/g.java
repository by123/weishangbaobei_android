package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.stub.StubApp;
import com.umeng.analytics.pro.c;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: UMStoreManager */
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

    /* compiled from: UMStoreManager */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* compiled from: UMStoreManager */
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

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    public void a() {
        this.i.clear();
    }

    public void b() {
        this.l.clear();
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009a, code lost:
        if (r1 != null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a5, code lost:
        if (r1 != null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b4 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bb A[SYNTHETIC, Splitter:B:37:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c2 A[SYNTHETIC, Splitter:B:41:0x00c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONArray r8) {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00b4, Throwable -> 0x00a4 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00b4, Throwable -> 0x00a4 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00b4, Throwable -> 0x00a4 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x009f, Throwable -> 0x00a5, all -> 0x009d }
            r2 = 0
        L_0x000f:
            int r3 = r8.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x009f, Throwable -> 0x00a5, all -> 0x009d }
            if (r2 >= r3) goto L_0x0097
            org.json.JSONObject r3 = r8.getJSONObject(r2)     // Catch:{ Exception -> 0x0093 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ Exception -> 0x0093 }
            r4.<init>()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__i"
            java.lang.String r5 = r3.optString(r5)     // Catch:{ Exception -> 0x0093 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0093 }
            if (r6 != 0) goto L_0x0032
            java.lang.String r6 = "-1"
            boolean r6 = r6.equals(r5)     // Catch:{ Exception -> 0x0093 }
            if (r6 == 0) goto L_0x0042
        L_0x0032:
            com.umeng.analytics.pro.q r5 = com.umeng.analytics.pro.q.a()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = r5.b()     // Catch:{ Exception -> 0x0093 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0093 }
            if (r6 == 0) goto L_0x0042
            java.lang.String r5 = "-1"
        L_0x0042:
            java.lang.String r6 = "__i"
            r4.put(r6, r5)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__e"
            java.lang.String r6 = "id"
            java.lang.String r6 = r3.optString(r6)     // Catch:{ Exception -> 0x0093 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__t"
            java.lang.String r6 = "__t"
            int r6 = r3.optInt(r6)     // Catch:{ Exception -> 0x0093 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0093 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__av"
            android.content.Context r6 = d     // Catch:{ Exception -> 0x0093 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch:{ Exception -> 0x0093 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__vc"
            android.content.Context r6 = d     // Catch:{ Exception -> 0x0093 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch:{ Exception -> 0x0093 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__i"
            r3.remove(r5)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__t"
            r3.remove(r5)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r5 = "__s"
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r3 = r7.c(r3)     // Catch:{ Exception -> 0x0093 }
            r4.put(r5, r3)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r3 = "__et"
            r1.insert(r3, r0, r4)     // Catch:{ Exception -> 0x0093 }
        L_0x0093:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x0097:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x009f, Throwable -> 0x00a5, all -> 0x009d }
            if (r1 == 0) goto L_0x00aa
            goto L_0x00a7
        L_0x009d:
            r8 = move-exception
            goto L_0x00c0
        L_0x009f:
            r0 = r1
            goto L_0x00b4
        L_0x00a1:
            r8 = move-exception
            r1 = r0
            goto L_0x00c0
        L_0x00a4:
            r1 = r0
        L_0x00a5:
            if (r1 == 0) goto L_0x00aa
        L_0x00a7:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00aa }
        L_0x00aa:
            android.content.Context r8 = d
            com.umeng.analytics.pro.e r8 = com.umeng.analytics.pro.e.a(r8)
            r8.b()
            goto L_0x00bf
        L_0x00b4:
            android.content.Context r8 = d     // Catch:{ all -> 0x00a1 }
            com.umeng.analytics.pro.f.a((android.content.Context) r8)     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x00aa
            r0.endTransaction()     // Catch:{ Throwable -> 0x00aa }
            goto L_0x00aa
        L_0x00bf:
            return
        L_0x00c0:
            if (r1 == 0) goto L_0x00c5
            r1.endTransaction()     // Catch:{ Throwable -> 0x00c5 }
        L_0x00c5:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r1 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004e, code lost:
        if (r1 != null) goto L_0x005b;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0068 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f A[SYNTHETIC, Splitter:B:25:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077 A[SYNTHETIC, Splitter:B:29:0x0077] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r3 = "__i"
            r2.put(r3, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r5 = r4.c(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            if (r6 != 0) goto L_0x004b
            java.lang.String r6 = "__a"
            r2.put(r6, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r5 = "__t"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r5 = "__av"
            android.content.Context r6 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r5 = "__vc"
            android.content.Context r6 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            java.lang.String r5 = "__er"
            r1.insert(r5, r0, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
        L_0x004b:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0053, Throwable -> 0x0059, all -> 0x0051 }
            if (r1 == 0) goto L_0x005e
            goto L_0x005b
        L_0x0051:
            r5 = move-exception
            goto L_0x0075
        L_0x0053:
            r0 = r1
            goto L_0x0068
        L_0x0055:
            r5 = move-exception
            r1 = r0
            goto L_0x0075
        L_0x0058:
            r1 = r0
        L_0x0059:
            if (r1 == 0) goto L_0x005e
        L_0x005b:
            r1.endTransaction()     // Catch:{ Throwable -> 0x005e }
        L_0x005e:
            android.content.Context r5 = d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            goto L_0x0073
        L_0x0068:
            android.content.Context r5 = d     // Catch:{ all -> 0x0055 }
            com.umeng.analytics.pro.f.a((android.content.Context) r5)     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x005e
            r0.endTransaction()     // Catch:{ Throwable -> 0x005e }
            goto L_0x005e
        L_0x0073:
            r5 = 0
            return r5
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r1.endTransaction()     // Catch:{ Throwable -> 0x007a }
        L_0x007a:
            android.content.Context r6 = d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, java.lang.String, int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        if (r1 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        if (r1 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0086 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008d A[SYNTHETIC, Splitter:B:36:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0094 A[SYNTHETIC, Splitter:B:40:0x0094] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0086, Throwable -> 0x0076 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0086, Throwable -> 0x0076 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0086, Throwable -> 0x0076 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            com.umeng.analytics.pro.q r0 = com.umeng.analytics.pro.q.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r0 = r0.c()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            if (r2 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0021
            r1.endTransaction()     // Catch:{ Throwable -> 0x0021 }
        L_0x0021:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            return
        L_0x002b:
            java.lang.String r2 = ""
            java.lang.String r3 = "-1"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r3 = 0
        L_0x0034:
            int r4 = r2.length     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            if (r3 >= r4) goto L_0x0067
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r5 = "update __et set __i=\""
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r4.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r5 = "\" where "
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r5 = "__i"
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r5 = "=\""
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r5 = r2[r3]     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r5 = "\""
            r4.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            r1.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            int r3 = r3 + 1
            goto L_0x0034
        L_0x0067:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006f, Throwable -> 0x0077, all -> 0x006d }
            if (r1 == 0) goto L_0x007c
            goto L_0x0079
        L_0x006d:
            r0 = move-exception
            goto L_0x0092
        L_0x006f:
            r0 = r1
            goto L_0x0086
        L_0x0071:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0092
        L_0x0076:
            r1 = r0
        L_0x0077:
            if (r1 == 0) goto L_0x007c
        L_0x0079:
            r1.endTransaction()     // Catch:{ Throwable -> 0x007c }
        L_0x007c:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x0091
        L_0x0086:
            android.content.Context r1 = d     // Catch:{ all -> 0x0071 }
            com.umeng.analytics.pro.f.a((android.content.Context) r1)     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x007c
            r0.endTransaction()     // Catch:{ Throwable -> 0x007c }
            goto L_0x007c
        L_0x0091:
            return
        L_0x0092:
            if (r1 == 0) goto L_0x0097
            r1.endTransaction()     // Catch:{ Throwable -> 0x0097 }
        L_0x0097:
            android.content.Context r1 = d
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.d():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007f, code lost:
        if (r2 != null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008a, code lost:
        if (r2 != null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0099 */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0 A[SYNTHETIC, Splitter:B:43:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a7 A[SYNTHETIC, Splitter:B:47:0x00a7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, org.json.JSONObject r7, com.umeng.analytics.pro.g.a r8) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            android.content.Context r2 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0099, Throwable -> 0x0089 }
            com.umeng.analytics.pro.e r2 = com.umeng.analytics.pro.e.a(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0099, Throwable -> 0x0089 }
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0099, Throwable -> 0x0089 }
            r2.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            com.umeng.analytics.pro.g$a r3 = com.umeng.analytics.pro.g.a.BEGIN     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r3) goto L_0x0051
            java.lang.String r8 = "__e"
            java.lang.Object r7 = r7.opt(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            long r7 = r7.longValue()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r4 = "__ii"
            r3.put(r4, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r6 = "__e"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            r3.put(r6, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r6 = "__av"
            android.content.Context r7 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r7 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            r3.put(r6, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r6 = "__vc"
            android.content.Context r7 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r7 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            r3.put(r6, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            java.lang.String r6 = "__sd"
            r2.insert(r6, r1, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            goto L_0x007c
        L_0x0051:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.INSTANTSESSIONBEGIN     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r1) goto L_0x0059
            r5.b(r6, r7, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            goto L_0x007c
        L_0x0059:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.END     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r1) goto L_0x0061
            r5.a((java.lang.String) r6, (org.json.JSONObject) r7, (android.database.sqlite.SQLiteDatabase) r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            goto L_0x007c
        L_0x0061:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.PAGE     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r1) goto L_0x006b
            java.lang.String r8 = "__a"
            r5.a(r6, r7, r2, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            goto L_0x007c
        L_0x006b:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.AUTOPAGE     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r1) goto L_0x0075
            java.lang.String r8 = "__b"
            r5.a(r6, r7, r2, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            goto L_0x007c
        L_0x0075:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.NEWSESSION     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r8 != r1) goto L_0x007c
            r5.c(r6, r7, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
        L_0x007c:
            r2.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x008a, all -> 0x0082 }
            if (r2 == 0) goto L_0x008f
            goto L_0x008c
        L_0x0082:
            r6 = move-exception
            goto L_0x00a5
        L_0x0084:
            r1 = r2
            goto L_0x0099
        L_0x0086:
            r6 = move-exception
            r2 = r1
            goto L_0x00a5
        L_0x0089:
            r2 = r1
        L_0x008a:
            if (r2 == 0) goto L_0x008f
        L_0x008c:
            r2.endTransaction()     // Catch:{ Throwable -> 0x008f }
        L_0x008f:
            android.content.Context r6 = d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            goto L_0x00a4
        L_0x0099:
            android.content.Context r6 = d     // Catch:{ all -> 0x0086 }
            com.umeng.analytics.pro.f.a((android.content.Context) r6)     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x008f
            r1.endTransaction()     // Catch:{ Throwable -> 0x008f }
            goto L_0x008f
        L_0x00a4:
            return r0
        L_0x00a5:
            if (r2 == 0) goto L_0x00aa
            r2.endTransaction()     // Catch:{ Throwable -> 0x00aa }
        L_0x00aa:
            android.content.Context r7 = d
            com.umeng.analytics.pro.e r7 = com.umeng.analytics.pro.e.a(r7)
            r7.b()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, org.json.JSONObject, com.umeng.analytics.pro.g$a):boolean");
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.opt(c.d.a.g)).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String str3 = "";
            if (optJSONObject != null && optJSONObject.length() > 0) {
                str2 = c(optJSONObject.toString());
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = c(optJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + longValue + "\", " + "__sp" + "=\"" + str2 + "\", " + "__pp" + "=\"" + str3 + "\" where " + "__ii" + "=\"" + str + "\"");
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.lang.String r7, org.json.JSONObject r8, android.database.sqlite.SQLiteDatabase r9) {
        /*
            r6 = this;
            java.lang.String r0 = "__e"
            java.lang.Object r0 = r8.get(r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            long r0 = r0.longValue()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r2 = "__sp"
            org.json.JSONObject r2 = r8.optJSONObject(r2)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r3 = "__pp"
            org.json.JSONObject r8 = r8.optJSONObject(r3)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r3 = ""
            java.lang.String r4 = ""
            if (r2 == 0) goto L_0x002c
            int r5 = r2.length()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            if (r5 <= 0) goto L_0x002c
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r3 = r6.c(r2)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
        L_0x002c:
            if (r8 == 0) goto L_0x003c
            int r2 = r8.length()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            if (r2 <= 0) goto L_0x003c
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r4 = r6.c(r8)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
        L_0x003c:
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            r8.<init>()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r2 = "__ii"
            r8.put(r2, r7)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__e"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            r8.put(r7, r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__sp"
            r8.put(r7, r3)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__pp"
            r8.put(r7, r4)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__av"
            com.umeng.commonsdk.service.UMGlobalContext r0 = com.umeng.commonsdk.service.UMGlobalContext.getInstance()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r0 = r0.getAppVersion()     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            r8.put(r7, r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__vc"
            android.content.Context r0 = d     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r0 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            r8.put(r7, r0)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            java.lang.String r7 = "__is"
            r0 = 0
            r9.insert(r7, r0, r8)     // Catch:{ Throwable -> 0x007a, all -> 0x0078 }
            goto L_0x007a
        L_0x0078:
            r7 = move-exception
            throw r7
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (r3 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        if (r3 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0050 A[SYNTHETIC, Splitter:B:21:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A[Catch:{ Exception -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0065 A[SYNTHETIC, Splitter:B:32:0x0065] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "select __f from __sd where __ii=\""
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = "\""
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r0 = 0
            r1 = 0
            android.content.Context r3 = d     // Catch:{ Exception -> 0x0062, all -> 0x004c }
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)     // Catch:{ Exception -> 0x0062, all -> 0x004c }
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch:{ Exception -> 0x0062, all -> 0x004c }
            r3.beginTransaction()     // Catch:{ Exception -> 0x0063, all -> 0x004a }
            android.database.Cursor r8 = r3.rawQuery(r8, r0)     // Catch:{ Exception -> 0x0063, all -> 0x004a }
            if (r8 == 0) goto L_0x0042
            r8.moveToFirst()     // Catch:{ Exception -> 0x0040, all -> 0x003b }
            java.lang.String r0 = "__f"
            int r0 = r8.getColumnIndex(r0)     // Catch:{ Exception -> 0x0040, all -> 0x003b }
            long r4 = r8.getLong(r0)     // Catch:{ Exception -> 0x0040, all -> 0x003b }
            r1 = r4
            goto L_0x0042
        L_0x003b:
            r0 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
            goto L_0x004e
        L_0x0040:
            r0 = r8
            goto L_0x0063
        L_0x0042:
            if (r8 == 0) goto L_0x0047
            r8.close()     // Catch:{ Exception -> 0x006d }
        L_0x0047:
            if (r3 == 0) goto L_0x006d
            goto L_0x006a
        L_0x004a:
            r8 = move-exception
            goto L_0x004e
        L_0x004c:
            r8 = move-exception
            r3 = r0
        L_0x004e:
            if (r0 == 0) goto L_0x0053
            r0.close()     // Catch:{ Exception -> 0x0058 }
        L_0x0053:
            if (r3 == 0) goto L_0x0058
            r3.endTransaction()     // Catch:{ Exception -> 0x0058 }
        L_0x0058:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r8
        L_0x0062:
            r3 = r0
        L_0x0063:
            if (r0 == 0) goto L_0x0068
            r0.close()     // Catch:{ Exception -> 0x006d }
        L_0x0068:
            if (r3 == 0) goto L_0x006d
        L_0x006a:
            r3.endTransaction()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            android.content.Context r8 = d
            com.umeng.analytics.pro.e r8 = com.umeng.analytics.pro.e.a(r8)
            r8.b()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.lang.String r6, org.json.JSONObject r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "__d"
            org.json.JSONObject r1 = r7.optJSONObject(r1)     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            if (r1 == 0) goto L_0x003a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            r2.<init>()     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            java.lang.String r3 = "select __d from __sd where __ii=\""
            r2.append(r3)     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            android.database.Cursor r2 = r8.rawQuery(r2, r0)     // Catch:{ Throwable -> 0x0111, all -> 0x0109 }
            if (r2 == 0) goto L_0x003b
        L_0x0025:
            boolean r3 = r2.moveToNext()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r3 == 0) goto L_0x003b
            java.lang.String r0 = "__d"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r2.getString(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r5.d(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            goto L_0x0025
        L_0x003a:
            r2 = r0
        L_0x003b:
            if (r1 == 0) goto L_0x0090
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r3.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r4 != 0) goto L_0x004d
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
        L_0x004d:
            r3.put(r1)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r5.c(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r1 != 0) goto L_0x0090
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "update  __sd set __d=\""
            r1.append(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "\" where "
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "__ii"
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "=\""
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.append(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "\""
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r1.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r8.execSQL(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            goto L_0x0090
        L_0x008a:
            r6 = move-exception
            goto L_0x010b
        L_0x008d:
            goto L_0x0112
        L_0x0090:
            java.lang.String r0 = "__c"
            org.json.JSONObject r0 = r7.optJSONObject(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r0 == 0) goto L_0x00d1
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r5.c(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r1 != 0) goto L_0x00d1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "update  __sd set __c=\""
            r1.append(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "\" where "
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "__ii"
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "=\""
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r1.append(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "\""
            r1.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = r1.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r8.execSQL(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
        L_0x00d1:
            java.lang.String r0 = "__f"
            long r0 = r7.optLong(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r7.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "update  __sd set __f=\""
            r7.append(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r7.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "\" where "
            r7.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "__ii"
            r7.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r0 = "=\""
            r7.append(r0)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r7.append(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r6 = "\""
            r7.append(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            java.lang.String r6 = r7.toString()     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            r8.execSQL(r6)     // Catch:{ Throwable -> 0x008d, all -> 0x008a }
            if (r2 == 0) goto L_0x0117
            goto L_0x0114
        L_0x0109:
            r6 = move-exception
            r2 = r0
        L_0x010b:
            if (r2 == 0) goto L_0x0110
            r2.close()
        L_0x0110:
            throw r6
        L_0x0111:
            r2 = r0
        L_0x0112:
            if (r2 == 0) goto L_0x0117
        L_0x0114:
            r2.close()
        L_0x0117:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.c(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r5, org.json.JSONObject r6, android.database.sqlite.SQLiteDatabase r7, java.lang.String r8) throws org.json.JSONException {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "__a"
            boolean r1 = r1.equals(r8)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r1 == 0) goto L_0x0018
            java.lang.String r1 = "__a"
            org.json.JSONArray r6 = r6.optJSONArray(r1)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r6 == 0) goto L_0x0017
            int r1 = r6.length()     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r1 > 0) goto L_0x0030
        L_0x0017:
            return
        L_0x0018:
            java.lang.String r1 = "__b"
            boolean r1 = r1.equals(r8)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = "__b"
            org.json.JSONArray r6 = r6.optJSONArray(r1)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r6 == 0) goto L_0x002e
            int r1 = r6.length()     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r1 > 0) goto L_0x0030
        L_0x002e:
            return
        L_0x002f:
            r6 = r0
        L_0x0030:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            r1.<init>()     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = "select "
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            r1.append(r8)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = " from "
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = "__sd"
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = " where "
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = "__ii"
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = "=\""
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            r1.append(r5)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r2 = "\""
            r1.append(r2)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            android.database.Cursor r1 = r7.rawQuery(r1, r0)     // Catch:{ Throwable -> 0x00fe, all -> 0x00f6 }
            if (r1 == 0) goto L_0x0081
        L_0x0068:
            boolean r2 = r1.moveToNext()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            if (r2 == 0) goto L_0x0081
            int r0 = r1.getColumnIndex(r8)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r0 = r4.d(r0)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            goto L_0x0068
        L_0x007b:
            r5 = move-exception
            goto L_0x00f8
        L_0x007e:
            goto L_0x00ff
        L_0x0081:
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r2.<init>()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            if (r3 != 0) goto L_0x0091
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
        L_0x0091:
            int r0 = r2.length()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r0 <= r3) goto L_0x009f
            if (r1 == 0) goto L_0x009e
            r1.close()
        L_0x009e:
            return
        L_0x009f:
            r0 = 0
        L_0x00a0:
            int r3 = r6.length()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            if (r0 >= r3) goto L_0x00b2
            org.json.JSONObject r3 = r6.getJSONObject(r0)     // Catch:{ JSONException -> 0x00af }
            if (r3 == 0) goto L_0x00af
            r2.put(r3)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
        L_0x00af:
            int r0 = r0 + 1
            goto L_0x00a0
        L_0x00b2:
            java.lang.String r6 = r2.toString()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r6 = r4.c(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            if (r0 != 0) goto L_0x00f3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r0.<init>()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r2 = "update __sd set "
            r0.append(r2)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r0.append(r8)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r8 = "=\""
            r0.append(r8)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r0.append(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r6 = "\" where "
            r0.append(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r6 = "__ii"
            r0.append(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r6 = "=\""
            r0.append(r6)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r0.append(r5)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r5 = "\""
            r0.append(r5)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
            r7.execSQL(r5)     // Catch:{ Throwable -> 0x007e, all -> 0x007b }
        L_0x00f3:
            if (r1 == 0) goto L_0x0104
            goto L_0x0101
        L_0x00f6:
            r5 = move-exception
            r1 = r0
        L_0x00f8:
            if (r1 == 0) goto L_0x00fd
            r1.close()
        L_0x00fd:
            throw r5
        L_0x00fe:
            r1 = r0
        L_0x00ff:
            if (r1 == 0) goto L_0x0104
        L_0x0101:
            r1.close()
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    public boolean e() {
        return this.i.isEmpty();
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: android.database.Cursor} */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0068, code lost:
        r6 = r2;
        r2 = r1;
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006d, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0097, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009a, code lost:
        if (r0 == null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r0.endTransaction();
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b2, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b5, code lost:
        if (r0 == null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bc, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067 A[ExcHandler: all (r1v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c1 A[SYNTHETIC, Splitter:B:61:0x00c1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject f() {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.l
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            android.content.Context r0 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = "select *  from __is where __ii=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.util.List<java.lang.String> r3 = r7.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            android.database.Cursor r2 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            if (r2 == 0) goto L_0x0073
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            if (r3 == 0) goto L_0x0073
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            java.lang.String r1 = "__av"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r1 = r2.getString(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r4 = "__vc"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r4 = r2.getString(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r5 = "__av"
            r3.put(r5, r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r1 = "__vc"
            r3.put(r1, r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            r1 = r3
            goto L_0x0073
        L_0x0067:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L_0x00ba
        L_0x006d:
            r3 = r1
        L_0x006e:
            r1 = r2
            goto L_0x0095
        L_0x0070:
            r3 = r1
        L_0x0071:
            r1 = r2
            goto L_0x00ab
        L_0x0073:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            if (r2 == 0) goto L_0x007b
            r2.close()
        L_0x007b:
            if (r0 == 0) goto L_0x0080
            r0.endTransaction()     // Catch:{ Throwable -> 0x0080 }
        L_0x0080:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            r3 = r1
            goto L_0x00b8
        L_0x008b:
            r3 = r1
            goto L_0x0095
        L_0x008d:
            r3 = r1
            goto L_0x00ab
        L_0x008f:
            r0 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x00ba
        L_0x0093:
            r0 = r1
            r3 = r0
        L_0x0095:
            if (r1 == 0) goto L_0x009a
            r1.close()
        L_0x009a:
            if (r0 == 0) goto L_0x009f
        L_0x009c:
            r0.endTransaction()     // Catch:{ Throwable -> 0x009f }
        L_0x009f:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x00b8
        L_0x00a9:
            r0 = r1
            r3 = r0
        L_0x00ab:
            android.content.Context r2 = d     // Catch:{ all -> 0x00b9 }
            com.umeng.analytics.pro.f.a((android.content.Context) r2)     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00b5
            r1.close()
        L_0x00b5:
            if (r0 == 0) goto L_0x009f
            goto L_0x009c
        L_0x00b8:
            return r3
        L_0x00b9:
            r2 = move-exception
        L_0x00ba:
            if (r1 == 0) goto L_0x00bf
            r1.close()
        L_0x00bf:
            if (r0 == 0) goto L_0x00c4
            r0.endTransaction()     // Catch:{ Throwable -> 0x00c4 }
        L_0x00c4:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.f():org.json.JSONObject");
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x016d, code lost:
        if (r1 != null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017d, code lost:
        if (r1 != null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0197, code lost:
        if (r1 == null) goto L_0x0182;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x018d */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01a4 A[SYNTHETIC, Splitter:B:95:0x01a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r10, java.lang.String r11) {
        /*
            r9 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x018c, Throwable -> 0x0176, all -> 0x0172 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018c, Throwable -> 0x0176, all -> 0x0172 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x018c, Throwable -> 0x0176, all -> 0x0172 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            java.lang.String r2 = "select *  from __et"
            boolean r3 = android.text.TextUtils.isEmpty(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            if (r3 != 0) goto L_0x002c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            java.lang.String r3 = "select *  from __et where __i=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            r2.append(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            java.lang.String r11 = "\""
            r2.append(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
        L_0x002c:
            android.database.Cursor r11 = r1.rawQuery(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x018d, Throwable -> 0x0170 }
            if (r11 == 0) goto L_0x0165
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            com.umeng.analytics.pro.q r3 = com.umeng.analytics.pro.q.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r3 = r3.b()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x0044:
            boolean r4 = r11.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r4 == 0) goto L_0x00dc
            java.lang.String r4 = "__t"
            int r4 = r11.getColumnIndex(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            int r4 = r11.getInt(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r5 = "__i"
            int r5 = r11.getColumnIndex(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r5 = r11.getString(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = "__s"
            int r6 = r11.getColumnIndex(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = r11.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r7 != 0) goto L_0x0076
            java.lang.String r7 = "-1"
            boolean r7 = r7.equals(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r7 == 0) goto L_0x007d
        L_0x0076:
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r5 != 0) goto L_0x0044
            r5 = r3
        L_0x007d:
            r7 = 0
            int r7 = r11.getInt(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.util.List<java.lang.Integer> r8 = r9.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r8.add(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            switch(r4) {
                case 2049: goto L_0x00b5;
                case 2050: goto L_0x008f;
                default: goto L_0x008e;
            }     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x008e:
            goto L_0x0044
        L_0x008f:
            boolean r4 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r4 != 0) goto L_0x0044
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = r9.d(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r4.<init>(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            boolean r6 = r2.has(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r6 == 0) goto L_0x00a9
            org.json.JSONArray r6 = r2.optJSONArray(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x00ae
        L_0x00a9:
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r6.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x00ae:
            r6.put(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x0044
        L_0x00b5:
            boolean r4 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r4 != 0) goto L_0x0044
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = r9.d(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r4.<init>(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            boolean r6 = r0.has(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r6 == 0) goto L_0x00cf
            org.json.JSONArray r6 = r0.optJSONArray(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x00d4
        L_0x00cf:
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r6.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x00d4:
            r6.put(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r0.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x0044
        L_0x00dc:
            int r3 = r0.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r3 <= 0) goto L_0x011d
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.util.Iterator r4 = r0.keys()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x00eb:
            boolean r5 = r4.hasNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r5 == 0) goto L_0x0112
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r5.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.Object r6 = r4.next()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r7 = r0.optString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r8.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r5.put(r6, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            int r6 = r5.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r6 <= 0) goto L_0x00eb
            r3.put(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x00eb
        L_0x0112:
            int r0 = r3.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r0 <= 0) goto L_0x011d
            java.lang.String r0 = "ekv"
            r10.put(r0, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x011d:
            int r0 = r2.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r0 <= 0) goto L_0x0165
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.util.Iterator r3 = r2.keys()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
        L_0x012c:
            boolean r4 = r3.hasNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r4 == 0) goto L_0x0153
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.Object r5 = r3.next()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            java.lang.String r6 = r2.optString(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r7.<init>(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            r4.put(r5, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            int r5 = r4.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r5 <= 0) goto L_0x012c
            r0.put(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x012c
        L_0x0153:
            int r2 = r0.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r2 <= 0) goto L_0x0165
            java.lang.String r2 = "gkv"
            r10.put(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            goto L_0x0165
        L_0x015f:
            r10 = move-exception
            goto L_0x019d
        L_0x0161:
            goto L_0x0178
        L_0x0163:
            r0 = r11
            goto L_0x018d
        L_0x0165:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0163, Throwable -> 0x0161, all -> 0x015f }
            if (r11 == 0) goto L_0x016d
            r11.close()
        L_0x016d:
            if (r1 == 0) goto L_0x0182
            goto L_0x017f
        L_0x0170:
            r11 = r0
            goto L_0x0178
        L_0x0172:
            r10 = move-exception
            r11 = r0
            r1 = r11
            goto L_0x019d
        L_0x0176:
            r11 = r0
            r1 = r11
        L_0x0178:
            if (r11 == 0) goto L_0x017d
            r11.close()
        L_0x017d:
            if (r1 == 0) goto L_0x0182
        L_0x017f:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0182 }
        L_0x0182:
            android.content.Context r10 = d
            com.umeng.analytics.pro.e r10 = com.umeng.analytics.pro.e.a(r10)
            r10.b()
            goto L_0x019a
        L_0x018c:
            r1 = r0
        L_0x018d:
            android.content.Context r10 = d     // Catch:{ all -> 0x019b }
            com.umeng.analytics.pro.f.a((android.content.Context) r10)     // Catch:{ all -> 0x019b }
            if (r0 == 0) goto L_0x0197
            r0.close()
        L_0x0197:
            if (r1 == 0) goto L_0x0182
            goto L_0x017f
        L_0x019a:
            return
        L_0x019b:
            r10 = move-exception
            r11 = r0
        L_0x019d:
            if (r11 == 0) goto L_0x01a2
            r11.close()
        L_0x01a2:
            if (r1 == 0) goto L_0x01a7
            r1.endTransaction()     // Catch:{ Throwable -> 0x01a7 }
        L_0x01a7:
            android.content.Context r11 = d
            com.umeng.analytics.pro.e r11 = com.umeng.analytics.pro.e.a(r11)
            r11.b()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0075, code lost:
        if (r1 != null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0084, code lost:
        if (r1 != null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        if (r1 == null) goto L_0x0089;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0094 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00aa A[SYNTHETIC, Splitter:B:54:0x00aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x007d, all -> 0x007a }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x007d, all -> 0x007a }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x007d, all -> 0x007a }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            java.lang.String r2 = "select *  from __er"
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            if (r3 != 0) goto L_0x002c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            java.lang.String r3 = "select *  from __er where __i=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            r2.append(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            java.lang.String r6 = "\""
            r2.append(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
        L_0x002c:
            android.database.Cursor r6 = r1.rawQuery(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0094, Throwable -> 0x0078 }
            if (r6 == 0) goto L_0x006d
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
        L_0x0037:
            boolean r2 = r6.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            if (r2 == 0) goto L_0x005a
            java.lang.String r2 = "__a"
            int r2 = r6.getColumnIndex(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            java.lang.String r2 = r6.getString(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            if (r3 != 0) goto L_0x0037
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            java.lang.String r2 = r4.d(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            r3.<init>(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            r0.put(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            goto L_0x0037
        L_0x005a:
            int r2 = r0.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            if (r2 <= 0) goto L_0x006d
            java.lang.String r2 = "error"
            r5.put(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            goto L_0x006d
        L_0x0066:
            r5 = move-exception
            r0 = r6
            goto L_0x00a3
        L_0x0069:
            goto L_0x007f
        L_0x006b:
            r0 = r6
            goto L_0x0094
        L_0x006d:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006b, Throwable -> 0x0069, all -> 0x0066 }
            if (r6 == 0) goto L_0x0075
            r6.close()
        L_0x0075:
            if (r1 == 0) goto L_0x0089
            goto L_0x0086
        L_0x0078:
            r6 = r0
            goto L_0x007f
        L_0x007a:
            r5 = move-exception
            r1 = r0
            goto L_0x00a3
        L_0x007d:
            r6 = r0
            r1 = r6
        L_0x007f:
            if (r6 == 0) goto L_0x0084
            r6.close()
        L_0x0084:
            if (r1 == 0) goto L_0x0089
        L_0x0086:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0089 }
        L_0x0089:
            android.content.Context r5 = d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            goto L_0x00a1
        L_0x0093:
            r1 = r0
        L_0x0094:
            android.content.Context r5 = d     // Catch:{ all -> 0x00a2 }
            com.umeng.analytics.pro.f.a((android.content.Context) r5)     // Catch:{ all -> 0x00a2 }
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            if (r1 == 0) goto L_0x0089
            goto L_0x0086
        L_0x00a1:
            return
        L_0x00a2:
            r5 = move-exception
        L_0x00a3:
            if (r0 == 0) goto L_0x00a8
            r0.close()
        L_0x00a8:
            if (r1 == 0) goto L_0x00ad
            r1.endTransaction()     // Catch:{ Throwable -> 0x00ad }
        L_0x00ad:
            android.content.Context r6 = d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: android.database.Cursor} */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0068, code lost:
        r6 = r2;
        r2 = r1;
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006d, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0097, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009a, code lost:
        if (r0 == null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r0.endTransaction();
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b2, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b5, code lost:
        if (r0 == null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bc, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067 A[ExcHandler: all (r1v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c1 A[SYNTHETIC, Splitter:B:61:0x00c1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject g() {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.i
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            android.content.Context r0 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00a9, Throwable -> 0x0093, all -> 0x008f }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = "select *  from __sd where __ii=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.util.List<java.lang.String> r3 = r7.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            android.database.Cursor r2 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008d, Throwable -> 0x008b }
            if (r2 == 0) goto L_0x0073
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            if (r3 == 0) goto L_0x0073
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            java.lang.String r1 = "__av"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r1 = r2.getString(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r4 = "__vc"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r4 = r2.getString(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r5 = "__av"
            r3.put(r5, r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            java.lang.String r1 = "__vc"
            r3.put(r1, r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, Throwable -> 0x006e, all -> 0x0067 }
            r1 = r3
            goto L_0x0073
        L_0x0067:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L_0x00ba
        L_0x006d:
            r3 = r1
        L_0x006e:
            r1 = r2
            goto L_0x0095
        L_0x0070:
            r3 = r1
        L_0x0071:
            r1 = r2
            goto L_0x00ab
        L_0x0073:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0070, Throwable -> 0x006d, all -> 0x0067 }
            if (r2 == 0) goto L_0x007b
            r2.close()
        L_0x007b:
            if (r0 == 0) goto L_0x0080
            r0.endTransaction()     // Catch:{ Throwable -> 0x0080 }
        L_0x0080:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            r3 = r1
            goto L_0x00b8
        L_0x008b:
            r3 = r1
            goto L_0x0095
        L_0x008d:
            r3 = r1
            goto L_0x00ab
        L_0x008f:
            r0 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x00ba
        L_0x0093:
            r0 = r1
            r3 = r0
        L_0x0095:
            if (r1 == 0) goto L_0x009a
            r1.close()
        L_0x009a:
            if (r0 == 0) goto L_0x009f
        L_0x009c:
            r0.endTransaction()     // Catch:{ Throwable -> 0x009f }
        L_0x009f:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x00b8
        L_0x00a9:
            r0 = r1
            r3 = r0
        L_0x00ab:
            android.content.Context r2 = d     // Catch:{ all -> 0x00b9 }
            com.umeng.analytics.pro.f.a((android.content.Context) r2)     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00b5
            r1.close()
        L_0x00b5:
            if (r0 == 0) goto L_0x009f
            goto L_0x009c
        L_0x00b8:
            return r3
        L_0x00b9:
            r2 = move-exception
        L_0x00ba:
            if (r1 == 0) goto L_0x00bf
            r1.close()
        L_0x00bf:
            if (r0 == 0) goto L_0x00c4
            r0.endTransaction()     // Catch:{ Throwable -> 0x00c4 }
        L_0x00c4:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.g():org.json.JSONObject");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v37, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v38, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v39 */
    /* JADX WARNING: type inference failed for: r2v41 */
    /* JADX WARNING: type inference failed for: r2v42 */
    /* JADX WARNING: type inference failed for: r2v43 */
    /* JADX WARNING: type inference failed for: r2v44 */
    /* JADX WARNING: type inference failed for: r2v45 */
    /* JADX WARNING: type inference failed for: r2v46 */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01ce, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0153, code lost:
        r2 = r2;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015a, code lost:
        if (r1.i.size() >= 1) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x015c, code lost:
        if (r4 == null) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x015e, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0161, code lost:
        if (r3 == null) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0170, code lost:
        r2 = r2;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0174, code lost:
        if (r0.length() <= 0) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0176, code lost:
        r19.put(com.umeng.analytics.pro.b.n, r0);
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x017e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0180, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0182, code lost:
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x018d, code lost:
        if (r3 != null) goto L_0x01a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x019c, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x019f, code lost:
        if (r3 == null) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r3.endTransaction();
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b7, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01d3 A[SYNTHETIC, Splitter:B:105:0x01d3] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017e A[Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }, ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0018] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01bc A[SYNTHETIC, Splitter:B:96:0x01bc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(org.json.JSONObject r19, boolean r20) {
        /*
            r18 = this;
            r1 = r18
            r2 = 0
            android.content.Context r0 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x01ae, Throwable -> 0x0198, all -> 0x0194 }
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x01ae, Throwable -> 0x0198, all -> 0x0194 }
            android.database.sqlite.SQLiteDatabase r3 = r0.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x01ae, Throwable -> 0x0198, all -> 0x0194 }
            r3.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0192, Throwable -> 0x0190 }
            java.lang.String r0 = "select *  from __sd"
            android.database.Cursor r4 = r3.rawQuery(r0, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0192, Throwable -> 0x0190 }
            if (r4 == 0) goto L_0x0185
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
        L_0x001d:
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            if (r5 == 0) goto L_0x0153
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            r5.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r6 = "__f"
            int r6 = r4.getColumnIndex(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r7 = "__e"
            int r7 = r4.getColumnIndex(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r8 = "__ii"
            int r8 = r4.getColumnIndex(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            java.lang.String r8 = r4.getString(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x014e
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x014e
            long r9 = java.lang.Long.parseLong(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            long r11 = java.lang.Long.parseLong(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r2 = 0
            long r9 = r9 - r11
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 <= 0) goto L_0x014a
            java.lang.String r2 = "__a"
            int r2 = r4.getColumnIndex(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r2 = r4.getString(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r9 = "__b"
            int r9 = r4.getColumnIndex(r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r10 = "__c"
            int r10 = r4.getColumnIndex(r10)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r10 = r4.getString(r10)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r11 = "__d"
            int r11 = r4.getColumnIndex(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r11 = r4.getString(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.util.List<java.lang.String> r12 = r1.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r12.add(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r12 = "__sp"
            int r12 = r4.getColumnIndex(r12)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r12 = r4.getString(r12)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r13 = "__pp"
            int r13 = r4.getColumnIndex(r13)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r13 = r4.getString(r13)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r14 = "id"
            r5.put(r14, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r14 = "start_time"
            r5.put(r14, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r14 = "end_time"
            r5.put(r14, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r14 = "duration"
            long r15 = java.lang.Long.parseLong(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            long r6 = java.lang.Long.parseLong(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r17 = 0
            long r6 = r15 - r6
            r5.put(r14, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            boolean r6 = android.text.TextUtils.isEmpty(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r6 != 0) goto L_0x00d7
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r2 = r1.d(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r6.<init>(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r2 = "pages"
            r5.put(r2, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x00d7:
            boolean r2 = android.text.TextUtils.isEmpty(r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x00f1
            com.umeng.analytics.MobclickAgent$PageMode r2 = com.umeng.analytics.AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            com.umeng.analytics.MobclickAgent$PageMode r6 = com.umeng.analytics.MobclickAgent.PageMode.AUTO     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != r6) goto L_0x00f1
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r6 = r1.d(r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r2.<init>(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r6 = "autopages"
            r5.put(r6, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x00f1:
            boolean r2 = android.text.TextUtils.isEmpty(r10)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x0105
            java.lang.String r2 = "traffic"
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r7 = r1.d(r10)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r6.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r5.put(r2, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x0105:
            boolean r2 = android.text.TextUtils.isEmpty(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x0119
            java.lang.String r2 = "locations"
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r7 = r1.d(r11)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r6.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r5.put(r2, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x0119:
            boolean r2 = android.text.TextUtils.isEmpty(r12)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x012d
            java.lang.String r2 = "_$sp"
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r7 = r1.d(r12)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r6.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r5.put(r2, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x012d:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 != 0) goto L_0x0141
            java.lang.String r2 = "_$pp"
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            java.lang.String r7 = r1.d(r13)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r6.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            r5.put(r2, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x0141:
            int r2 = r5.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
            if (r2 <= 0) goto L_0x014a
            r0.put(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0183, Throwable -> 0x0151, all -> 0x017e }
        L_0x014a:
            if (r20 == 0) goto L_0x014e
            r2 = r8
            goto L_0x0153
        L_0x014e:
            r2 = r8
            goto L_0x001d
        L_0x0151:
            r2 = r8
            goto L_0x019a
        L_0x0153:
            java.util.List<java.lang.String> r5 = r1.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            int r5 = r5.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            r6 = 1
            if (r5 >= r6) goto L_0x0170
            if (r4 == 0) goto L_0x0161
            r4.close()
        L_0x0161:
            if (r3 == 0) goto L_0x0166
            r3.endTransaction()     // Catch:{ Throwable -> 0x0166 }
        L_0x0166:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            return r2
        L_0x0170:
            int r5 = r0.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            if (r5 <= 0) goto L_0x0185
            java.lang.String r5 = "sessions"
            r6 = r19
            r6.put(r5, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            goto L_0x0185
        L_0x017e:
            r0 = move-exception
            goto L_0x01cc
        L_0x0180:
            goto L_0x019a
        L_0x0182:
            r8 = r2
        L_0x0183:
            r2 = r4
            goto L_0x01b0
        L_0x0185:
            r3.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0182, Throwable -> 0x0180, all -> 0x017e }
            if (r4 == 0) goto L_0x018d
            r4.close()
        L_0x018d:
            if (r3 == 0) goto L_0x01a4
            goto L_0x01a1
        L_0x0190:
            r4 = r2
            goto L_0x019a
        L_0x0192:
            r8 = r2
            goto L_0x01b0
        L_0x0194:
            r0 = move-exception
            r3 = r2
            r4 = r3
            goto L_0x01cc
        L_0x0198:
            r3 = r2
            r4 = r3
        L_0x019a:
            if (r4 == 0) goto L_0x019f
            r4.close()
        L_0x019f:
            if (r3 == 0) goto L_0x01a4
        L_0x01a1:
            r3.endTransaction()     // Catch:{ Throwable -> 0x01a4 }
        L_0x01a4:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x01c9
        L_0x01ae:
            r3 = r2
            r8 = r3
        L_0x01b0:
            android.content.Context r0 = d     // Catch:{ all -> 0x01ca }
            com.umeng.analytics.pro.f.a((android.content.Context) r0)     // Catch:{ all -> 0x01ca }
            if (r2 == 0) goto L_0x01ba
            r2.close()
        L_0x01ba:
            if (r3 == 0) goto L_0x01bf
            r3.endTransaction()     // Catch:{ Throwable -> 0x01bf }
        L_0x01bf:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            r2 = r8
        L_0x01c9:
            return r2
        L_0x01ca:
            r0 = move-exception
            r4 = r2
        L_0x01cc:
            if (r4 == 0) goto L_0x01d1
            r4.close()
        L_0x01d1:
            if (r3 == 0) goto L_0x01d6
            r3.endTransaction()     // Catch:{ Throwable -> 0x01d6 }
        L_0x01d6:
            android.content.Context r2 = d
            com.umeng.analytics.pro.e r2 = com.umeng.analytics.pro.e.a(r2)
            r2.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v19 */
    /* JADX WARNING: type inference failed for: r0v28 */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r0v31 */
    /* JADX WARNING: type inference failed for: r0v32 */
    /* JADX WARNING: type inference failed for: r0v33 */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009c, code lost:
        r0 = r0;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        if (r3.length() <= 0) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a2, code lost:
        r11.put(com.umeng.analytics.pro.b.n, r3);
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a8, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00aa, code lost:
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ac, code lost:
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b7, code lost:
        if (r1 != null) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c6, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c9, code lost:
        if (r1 == null) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e1, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00f8, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a8 A[Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0016] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e6 A[SYNTHETIC, Splitter:B:66:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fd A[SYNTHETIC, Splitter:B:75:0x00fd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(org.json.JSONObject r11, boolean r12) {
        /*
            r10 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00d8, Throwable -> 0x00c2, all -> 0x00be }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00d8, Throwable -> 0x00c2, all -> 0x00be }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00d8, Throwable -> 0x00c2, all -> 0x00be }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00bc, Throwable -> 0x00ba }
            java.lang.String r2 = "select *  from __is"
            android.database.Cursor r2 = r1.rawQuery(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00bc, Throwable -> 0x00ba }
            if (r2 == 0) goto L_0x00af
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
        L_0x001b:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            if (r4 == 0) goto L_0x009c
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            java.lang.String r5 = "__e"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            java.lang.String r6 = "__ii"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            java.lang.String r6 = r2.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            java.util.List<java.lang.String> r0 = r10.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            r0.add(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r0 = "__sp"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r0 = r2.getString(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r7 = "__pp"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            boolean r8 = android.text.TextUtils.isEmpty(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            if (r8 != 0) goto L_0x0067
            java.lang.String r8 = "_$sp"
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r0 = r10.d(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            r9.<init>(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            r4.put(r8, r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
        L_0x0067:
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            if (r0 != 0) goto L_0x007b
            java.lang.String r0 = "_$pp"
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r7 = r10.d(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            r8.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            r4.put(r0, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
        L_0x007b:
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            if (r0 != 0) goto L_0x0098
            java.lang.String r0 = "id"
            r4.put(r0, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            java.lang.String r0 = "start_time"
            r4.put(r0, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            int r0 = r4.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
            if (r0 <= 0) goto L_0x0094
            r3.put(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, Throwable -> 0x009a, all -> 0x00a8 }
        L_0x0094:
            if (r12 == 0) goto L_0x0098
            r0 = r6
            goto L_0x009c
        L_0x0098:
            r0 = r6
            goto L_0x001b
        L_0x009a:
            r0 = r6
            goto L_0x00c4
        L_0x009c:
            int r12 = r3.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            if (r12 <= 0) goto L_0x00af
            java.lang.String r12 = "sessions"
            r11.put(r12, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            goto L_0x00af
        L_0x00a8:
            r11 = move-exception
            goto L_0x00f6
        L_0x00aa:
            goto L_0x00c4
        L_0x00ac:
            r6 = r0
        L_0x00ad:
            r0 = r2
            goto L_0x00da
        L_0x00af:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, Throwable -> 0x00aa, all -> 0x00a8 }
            if (r2 == 0) goto L_0x00b7
            r2.close()
        L_0x00b7:
            if (r1 == 0) goto L_0x00ce
            goto L_0x00cb
        L_0x00ba:
            r2 = r0
            goto L_0x00c4
        L_0x00bc:
            r6 = r0
            goto L_0x00da
        L_0x00be:
            r11 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x00f6
        L_0x00c2:
            r1 = r0
            r2 = r1
        L_0x00c4:
            if (r2 == 0) goto L_0x00c9
            r2.close()
        L_0x00c9:
            if (r1 == 0) goto L_0x00ce
        L_0x00cb:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00ce }
        L_0x00ce:
            android.content.Context r11 = d
            com.umeng.analytics.pro.e r11 = com.umeng.analytics.pro.e.a(r11)
            r11.b()
            goto L_0x00f3
        L_0x00d8:
            r1 = r0
            r6 = r1
        L_0x00da:
            android.content.Context r11 = d     // Catch:{ all -> 0x00f4 }
            com.umeng.analytics.pro.f.a((android.content.Context) r11)     // Catch:{ all -> 0x00f4 }
            if (r0 == 0) goto L_0x00e4
            r0.close()
        L_0x00e4:
            if (r1 == 0) goto L_0x00e9
            r1.endTransaction()     // Catch:{ Throwable -> 0x00e9 }
        L_0x00e9:
            android.content.Context r11 = d
            com.umeng.analytics.pro.e r11 = com.umeng.analytics.pro.e.a(r11)
            r11.b()
            r0 = r6
        L_0x00f3:
            return r0
        L_0x00f4:
            r11 = move-exception
            r2 = r0
        L_0x00f6:
            if (r2 == 0) goto L_0x00fb
            r2.close()
        L_0x00fb:
            if (r1 == 0) goto L_0x0100
            r1.endTransaction()     // Catch:{ Throwable -> 0x0100 }
        L_0x0100:
            android.content.Context r12 = d
            com.umeng.analytics.pro.e r12 = com.umeng.analytics.pro.e.a(r12)
            r12.b()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r1 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        if (r1 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006b A[SYNTHETIC, Splitter:B:31:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0072 A[SYNTHETIC, Splitter:B:35:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, Throwable -> 0x0054 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, Throwable -> 0x0054 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, Throwable -> 0x0054 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            if (r5 == 0) goto L_0x0018
            if (r4 == 0) goto L_0x0047
            java.lang.String r4 = "delete from __is"
            r1.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            goto L_0x0047
        L_0x0018:
            java.util.List<java.lang.String> r4 = r3.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            int r4 = r4.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            if (r4 <= 0) goto L_0x0047
            r5 = 0
        L_0x0021:
            if (r5 >= r4) goto L_0x0047
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.lang.String r2 = "delete from __is where __ii=\""
            r0.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.util.List<java.lang.String> r2 = r3.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            r0.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.lang.String r2 = "\""
            r0.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            java.lang.String r0 = r0.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            r1.execSQL(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            int r5 = r5 + 1
            goto L_0x0021
        L_0x0047:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004f, Throwable -> 0x0055, all -> 0x004d }
            if (r1 == 0) goto L_0x005a
            goto L_0x0057
        L_0x004d:
            r4 = move-exception
            goto L_0x0070
        L_0x004f:
            r0 = r1
            goto L_0x0064
        L_0x0051:
            r4 = move-exception
            r1 = r0
            goto L_0x0070
        L_0x0054:
            r1 = r0
        L_0x0055:
            if (r1 == 0) goto L_0x005a
        L_0x0057:
            r1.endTransaction()     // Catch:{ Throwable -> 0x005a }
        L_0x005a:
            android.content.Context r4 = d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            goto L_0x006f
        L_0x0064:
            android.content.Context r4 = d     // Catch:{ all -> 0x0051 }
            com.umeng.analytics.pro.f.a((android.content.Context) r4)     // Catch:{ all -> 0x0051 }
            if (r0 == 0) goto L_0x005a
            r0.endTransaction()     // Catch:{ Throwable -> 0x005a }
            goto L_0x005a
        L_0x006f:
            return
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.endTransaction()     // Catch:{ Throwable -> 0x0075 }
        L_0x0075:
            android.content.Context r5 = d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(boolean, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        if (r1 != null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        if (r1 != null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006a */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0071 A[SYNTHETIC, Splitter:B:32:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0078 A[SYNTHETIC, Splitter:B:36:0x0078] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(boolean r3, boolean r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x006a, Throwable -> 0x005a }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006a, Throwable -> 0x005a }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006a, Throwable -> 0x005a }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            if (r4 == 0) goto L_0x0018
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = "delete from __sd"
            r1.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            goto L_0x004d
        L_0x0018:
            java.util.List<java.lang.String> r3 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            int r3 = r3.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            if (r3 <= 0) goto L_0x004d
            r3 = 0
        L_0x0021:
            java.util.List<java.lang.String> r4 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            int r4 = r4.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            if (r3 >= r4) goto L_0x004d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.lang.String r0 = "delete from __sd where __ii=\""
            r4.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.util.List<java.lang.String> r0 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            r4.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.lang.String r0 = "\""
            r4.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            r1.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            int r3 = r3 + 1
            goto L_0x0021
        L_0x004d:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x005b, all -> 0x0053 }
            if (r1 == 0) goto L_0x0060
            goto L_0x005d
        L_0x0053:
            r3 = move-exception
            goto L_0x0076
        L_0x0055:
            r0 = r1
            goto L_0x006a
        L_0x0057:
            r3 = move-exception
            r1 = r0
            goto L_0x0076
        L_0x005a:
            r1 = r0
        L_0x005b:
            if (r1 == 0) goto L_0x0060
        L_0x005d:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0060 }
        L_0x0060:
            android.content.Context r3 = d
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)
            r3.b()
            goto L_0x0075
        L_0x006a:
            android.content.Context r3 = d     // Catch:{ all -> 0x0057 }
            com.umeng.analytics.pro.f.a((android.content.Context) r3)     // Catch:{ all -> 0x0057 }
            if (r0 == 0) goto L_0x0060
            r0.endTransaction()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x0060
        L_0x0075:
            return
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r1.endTransaction()     // Catch:{ Throwable -> 0x007b }
        L_0x007b:
            android.content.Context r4 = d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(boolean, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        if (r1 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r1 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0060 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0067 A[SYNTHETIC, Splitter:B:28:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e A[SYNTHETIC, Splitter:B:32:0x006e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0060, Throwable -> 0x0050 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0060, Throwable -> 0x0050 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0060, Throwable -> 0x0050 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            java.util.List<java.lang.Integer> r0 = r5.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            int r0 = r0.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            if (r0 <= 0) goto L_0x003c
            r0 = 0
        L_0x0017:
            java.util.List<java.lang.Integer> r2 = r5.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            int r2 = r2.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            if (r0 >= r2) goto L_0x003c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            java.lang.String r3 = "delete from __et where rowid="
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            java.util.List<java.lang.Integer> r3 = r5.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            r1.execSQL(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            int r0 = r0 + 1
            goto L_0x0017
        L_0x003c:
            java.util.List<java.lang.Integer> r0 = r5.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            r0.clear()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x0051, all -> 0x0047 }
            if (r1 == 0) goto L_0x0056
            goto L_0x0053
        L_0x0047:
            r0 = move-exception
            goto L_0x006c
        L_0x0049:
            r0 = r1
            goto L_0x0060
        L_0x004b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x006c
        L_0x0050:
            r1 = r0
        L_0x0051:
            if (r1 == 0) goto L_0x0056
        L_0x0053:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x006b
        L_0x0060:
            android.content.Context r1 = d     // Catch:{ all -> 0x004b }
            com.umeng.analytics.pro.f.a((android.content.Context) r1)     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x0056
            r0.endTransaction()     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0056
        L_0x006b:
            return
        L_0x006c:
            if (r1 == 0) goto L_0x0071
            r1.endTransaction()     // Catch:{ Throwable -> 0x0071 }
        L_0x0071:
            android.content.Context r1 = d
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.h():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        if (r1 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r1 != null) goto L_0x0025;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0032 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A[SYNTHETIC, Splitter:B:22:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0040 A[SYNTHETIC, Splitter:B:26:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i() {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0032, Throwable -> 0x0022 }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0032, Throwable -> 0x0022 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0032, Throwable -> 0x0022 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x001e, Throwable -> 0x0023, all -> 0x0019 }
            java.lang.String r0 = "delete from __er"
            r1.execSQL(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x001e, Throwable -> 0x0023, all -> 0x0019 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x001e, Throwable -> 0x0023, all -> 0x0019 }
            if (r1 == 0) goto L_0x0028
            goto L_0x0025
        L_0x0019:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x003e
        L_0x001e:
            r0 = r1
            goto L_0x0032
        L_0x0020:
            r1 = move-exception
            goto L_0x003e
        L_0x0022:
            r1 = r0
        L_0x0023:
            if (r1 == 0) goto L_0x0028
        L_0x0025:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0028 }
        L_0x0028:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x003d
        L_0x0032:
            android.content.Context r1 = d     // Catch:{ all -> 0x0020 }
            com.umeng.analytics.pro.f.a((android.content.Context) r1)     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0028
            r0.endTransaction()     // Catch:{ Throwable -> 0x0028 }
            goto L_0x0028
        L_0x003d:
            return
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.endTransaction()     // Catch:{ Throwable -> 0x0043 }
        L_0x0043:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.i():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0058, code lost:
        if (r0 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        if (r0 == null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x004f, code lost:
        if (r0 != null) goto L_0x005a;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0068 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0073 A[SYNTHETIC, Splitter:B:24:0x0073] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j() {
        /*
            r5 = this;
            java.lang.String r0 = r5.k
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L_0x0080
            android.content.Context r0 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, Throwable -> 0x0057, all -> 0x0052 }
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, Throwable -> 0x0057, all -> 0x0052 }
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, Throwable -> 0x0057, all -> 0x0052 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = "delete from __er where __i=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = r5.k     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r0.execSQL(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = "delete from __et where __i=\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = r5.k     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r0.execSQL(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, Throwable -> 0x0058 }
            if (r0 == 0) goto L_0x005d
            goto L_0x005a
        L_0x0052:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0071
        L_0x0057:
            r0 = r1
        L_0x0058:
            if (r0 == 0) goto L_0x005d
        L_0x005a:
            r0.endTransaction()     // Catch:{ Throwable -> 0x005d }
        L_0x005d:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L_0x0080
        L_0x0067:
            r0 = r1
        L_0x0068:
            android.content.Context r2 = d     // Catch:{ all -> 0x0070 }
            com.umeng.analytics.pro.f.a((android.content.Context) r2)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x005d
            goto L_0x005a
        L_0x0070:
            r1 = move-exception
        L_0x0071:
            if (r0 == 0) goto L_0x0076
            r0.endTransaction()     // Catch:{ Throwable -> 0x0076 }
        L_0x0076:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r1
        L_0x0080:
            r5.k = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.j():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0073, code lost:
        if (r0 != null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0067, code lost:
        if (r0 != null) goto L_0x0075;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0082 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0089 A[SYNTHETIC, Splitter:B:25:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090 A[SYNTHETIC, Splitter:B:29:0x0090] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r3, java.lang.String r4) {
        /*
            r2 = this;
            r3 = 0
            android.content.Context r0 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0082, Throwable -> 0x0072 }
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0082, Throwable -> 0x0072 }
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0082, Throwable -> 0x0072 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            if (r3 != 0) goto L_0x0064
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r1 = "delete from __er where __i=\""
            r3.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r1 = "\""
            r3.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r0.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r1 = "delete from __et where __i=\""
            r3.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r1 = "\""
            r3.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r0.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.util.List<java.lang.Integer> r3 = r2.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.clear()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r1 = "delete from __sd where __ii=\""
            r3.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r4 = "\""
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            r0.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
        L_0x0064:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x006c, Throwable -> 0x0073, all -> 0x006a }
            if (r0 == 0) goto L_0x0078
            goto L_0x0075
        L_0x006a:
            r3 = move-exception
            goto L_0x008e
        L_0x006c:
            r3 = r0
            goto L_0x0082
        L_0x006e:
            r4 = move-exception
            r0 = r3
            r3 = r4
            goto L_0x008e
        L_0x0072:
            r0 = r3
        L_0x0073:
            if (r0 == 0) goto L_0x0078
        L_0x0075:
            r0.endTransaction()     // Catch:{ Throwable -> 0x0078 }
        L_0x0078:
            android.content.Context r3 = d
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)
            r3.b()
            goto L_0x008d
        L_0x0082:
            android.content.Context r4 = d     // Catch:{ all -> 0x006e }
            com.umeng.analytics.pro.f.a((android.content.Context) r4)     // Catch:{ all -> 0x006e }
            if (r3 == 0) goto L_0x0078
            r3.endTransaction()     // Catch:{ Throwable -> 0x0078 }
            goto L_0x0078
        L_0x008d:
            return
        L_0x008e:
            if (r0 == 0) goto L_0x0093
            r0.endTransaction()     // Catch:{ Throwable -> 0x0093 }
        L_0x0093:
            android.content.Context r4 = d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(boolean, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r1 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        if (r1 != null) goto L_0x003d;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x004a */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[SYNTHETIC, Splitter:B:25:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0058 A[SYNTHETIC, Splitter:B:29:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = d     // Catch:{ SQLiteDatabaseCorruptException -> 0x004a, Throwable -> 0x003a }
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x004a, Throwable -> 0x003a }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x004a, Throwable -> 0x003a }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            if (r0 != 0) goto L_0x002d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            r0.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            java.lang.String r2 = "delete from __is where __ii=\""
            r0.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            r0.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            java.lang.String r4 = "\""
            r0.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            java.lang.String r4 = r0.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            r1.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
        L_0x002d:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x003b, all -> 0x0033 }
            if (r1 == 0) goto L_0x0040
            goto L_0x003d
        L_0x0033:
            r4 = move-exception
            goto L_0x0056
        L_0x0035:
            r0 = r1
            goto L_0x004a
        L_0x0037:
            r4 = move-exception
            r1 = r0
            goto L_0x0056
        L_0x003a:
            r1 = r0
        L_0x003b:
            if (r1 == 0) goto L_0x0040
        L_0x003d:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0040 }
        L_0x0040:
            android.content.Context r4 = d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            goto L_0x0055
        L_0x004a:
            android.content.Context r4 = d     // Catch:{ all -> 0x0037 }
            com.umeng.analytics.pro.f.a((android.content.Context) r4)     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0040
            r0.endTransaction()     // Catch:{ Throwable -> 0x0040 }
            goto L_0x0040
        L_0x0055:
            return
        L_0x0056:
            if (r1 == 0) goto L_0x005b
            r1.endTransaction()     // Catch:{ Throwable -> 0x005b }
        L_0x005b:
            android.content.Context r0 = d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(java.lang.String):void");
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
        } catch (Throwable unused) {
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), e.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), e.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }
}
