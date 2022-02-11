package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.g;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ViewPageTracker */
public class r {
    private static final int b = 5;
    private static JSONArray c = new JSONArray();
    private static Object d = new Object();
    Stack<String> a = new Stack<>();
    private final Map<String, Long> e = new HashMap();

    /* access modifiers changed from: protected */
    public int a() {
        return 2;
    }

    public static void a(Context context) {
        String jSONArray;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (d) {
                    jSONArray = c.toString();
                    c = new JSONArray();
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("__a", new JSONArray(jSONArray));
                    if (jSONObject.length() > 0) {
                        g.a(context).a(q.a().c(), jSONObject, g.a.PAGE);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (UMConfigure.isDebugLog() && this.a.size() != 0) {
                String[] strArr = {this.a.peek()};
                UMLog.aq(h.F, 0, "\\|", new String[]{"@"}, strArr, (String[]) null, (String[]) null);
            }
            synchronized (this.e) {
                this.e.put(str, Long.valueOf(System.currentTimeMillis()));
                if (UMConfigure.isDebugLog()) {
                    this.a.push(str);
                }
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0088 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r11) {
        /*
            r10 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 != 0) goto L_0x00d3
            java.util.Map<java.lang.String, java.lang.Long> r0 = r10.e
            boolean r0 = r0.containsKey(r11)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00b1
            java.util.Map<java.lang.String, java.lang.Long> r0 = r10.e
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.Long> r3 = r10.e     // Catch:{ all -> 0x00ae }
            java.lang.Object r3 = r3.get(r11)     // Catch:{ all -> 0x00ae }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x00ae }
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            if (r3 != 0) goto L_0x001f
            return
        L_0x001f:
            boolean r0 = com.umeng.commonsdk.UMConfigure.isDebugLog()
            if (r0 == 0) goto L_0x003e
            java.util.Stack<java.lang.String> r0 = r10.a
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x003e
            java.util.Stack<java.lang.String> r0 = r10.a
            java.lang.Object r0 = r0.peek()
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x003e
            java.util.Stack<java.lang.String> r0 = r10.a
            r0.pop()
        L_0x003e:
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r3.longValue()
            long r4 = r4 - r6
            java.lang.Object r6 = d
            monitor-enter(r6)
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0088 }
            r0.<init>()     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r7 = "page_name"
            r0.put(r7, r11)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r7 = "duration"
            r0.put(r7, r4)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r4 = "page_start"
            r0.put(r4, r3)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r3 = "type"
            int r4 = r10.a()     // Catch:{ Throwable -> 0x0088 }
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0088 }
            org.json.JSONArray r3 = c     // Catch:{ Throwable -> 0x0088 }
            r3.put(r0)     // Catch:{ Throwable -> 0x0088 }
            org.json.JSONArray r0 = c     // Catch:{ Throwable -> 0x0088 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x0088 }
            r3 = 5
            if (r0 < r3) goto L_0x0088
            r0 = 0
            android.content.Context r3 = com.umeng.commonsdk.service.UMGlobalContext.getAppContext(r0)     // Catch:{ Throwable -> 0x0088 }
            if (r3 == 0) goto L_0x0088
            r4 = 4099(0x1003, float:5.744E-42)
            com.umeng.analytics.CoreProtocol r5 = com.umeng.analytics.CoreProtocol.getInstance(r3)     // Catch:{ Throwable -> 0x0088 }
            com.umeng.commonsdk.framework.UMWorkDispatch.sendEvent(r3, r4, r5, r0)     // Catch:{ Throwable -> 0x0088 }
            goto L_0x0088
        L_0x0086:
            r11 = move-exception
            goto L_0x00ac
        L_0x0088:
            monitor-exit(r6)     // Catch:{ all -> 0x0086 }
            boolean r0 = com.umeng.commonsdk.UMConfigure.isDebugLog()
            if (r0 == 0) goto L_0x00d3
            java.util.Stack<java.lang.String> r0 = r10.a
            int r0 = r0.size()
            if (r0 == 0) goto L_0x00d3
            java.lang.String r0 = "@"
            java.lang.String[] r6 = new java.lang.String[]{r0}
            java.lang.String[] r7 = new java.lang.String[r2]
            r7[r1] = r11
            java.lang.String r3 = com.umeng.analytics.pro.h.E
            r4 = 0
            java.lang.String r5 = "\\|"
            r8 = 0
            r9 = 0
            com.umeng.commonsdk.debug.UMLog.aq(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00d3
        L_0x00ac:
            monitor-exit(r6)     // Catch:{ all -> 0x0086 }
            throw r11
        L_0x00ae:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            throw r11
        L_0x00b1:
            boolean r0 = com.umeng.commonsdk.UMConfigure.isDebugLog()
            if (r0 == 0) goto L_0x00d3
            java.util.Stack<java.lang.String> r0 = r10.a
            int r0 = r0.size()
            if (r0 != 0) goto L_0x00d3
            java.lang.String r0 = "@"
            java.lang.String[] r6 = new java.lang.String[]{r0}
            java.lang.String[] r7 = new java.lang.String[r2]
            r7[r1] = r11
            java.lang.String r3 = com.umeng.analytics.pro.h.G
            r4 = 0
            java.lang.String r5 = "\\|"
            r8 = 0
            r9 = 0
            com.umeng.commonsdk.debug.UMLog.aq(r3, r4, r5, r6, r7, r8, r9)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.r.b(java.lang.String):void");
    }

    public void b() {
        String str;
        synchronized (this.e) {
            str = null;
            long j = 0;
            for (Map.Entry next : this.e.entrySet()) {
                if (((Long) next.getValue()).longValue() > j) {
                    long longValue = ((Long) next.getValue()).longValue();
                    str = (String) next.getKey();
                    j = longValue;
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }
}
