package com.umeng.commonsdk.internal.crash;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.ULog;

public class UMCrashManager {
    /* access modifiers changed from: private */
    public static boolean isReportCrash;
    /* access modifiers changed from: private */
    public static Object mObject = new Object();

    public static void reportCrash(final Context context, final Throwable th) {
        if (!isReportCrash) {
            ULog.i("walle-crash", "report is " + isReportCrash);
            new Thread(new Runnable() {
                /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(2:10|(4:12|13|14|(1:16)))|17|18) */
                /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x00b8 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r7 = this;
                        java.lang.Object r0 = com.umeng.commonsdk.internal.crash.UMCrashManager.mObject     // Catch:{ Throwable -> 0x00bd }
                        monitor-enter(r0)     // Catch:{ Throwable -> 0x00bd }
                        android.content.Context r1 = r5     // Catch:{ all -> 0x00ba }
                        if (r1 == 0) goto L_0x00b8
                        java.lang.Throwable r1 = r6     // Catch:{ all -> 0x00ba }
                        if (r1 == 0) goto L_0x00b8
                        boolean r1 = com.umeng.commonsdk.internal.crash.UMCrashManager.isReportCrash     // Catch:{ all -> 0x00ba }
                        if (r1 != 0) goto L_0x00b8
                        r1 = 1
                        boolean unused = com.umeng.commonsdk.internal.crash.UMCrashManager.isReportCrash = r1     // Catch:{ all -> 0x00ba }
                        java.lang.String r2 = "walle-crash"
                        java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ba }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
                        r3.<init>()     // Catch:{ all -> 0x00ba }
                        java.lang.String r4 = "report thread is "
                        r3.append(r4)     // Catch:{ all -> 0x00ba }
                        boolean r4 = com.umeng.commonsdk.internal.crash.UMCrashManager.isReportCrash     // Catch:{ all -> 0x00ba }
                        r3.append(r4)     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ba }
                        r4 = 0
                        r1[r4] = r3     // Catch:{ all -> 0x00ba }
                        com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r2, (java.lang.Object[]) r1)     // Catch:{ all -> 0x00ba }
                        java.lang.Throwable r1 = r6     // Catch:{ all -> 0x00ba }
                        java.lang.String r1 = com.umeng.commonsdk.internal.crash.a.a(r1)     // Catch:{ all -> 0x00ba }
                        boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00ba }
                        if (r2 != 0) goto L_0x00b8
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
                        r2.<init>()     // Catch:{ all -> 0x00ba }
                        android.content.Context r3 = r5     // Catch:{ all -> 0x00ba }
                        java.io.File r3 = r3.getFilesDir()     // Catch:{ all -> 0x00ba }
                        r2.append(r3)     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = "/"
                        r2.append(r3)     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = "stateless"
                        r2.append(r3)     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = "/"
                        r2.append(r3)     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = "umpx_internal"
                        byte[] r3 = r3.getBytes()     // Catch:{ all -> 0x00ba }
                        java.lang.String r3 = android.util.Base64.encodeToString(r3, r4)     // Catch:{ all -> 0x00ba }
                        r2.append(r3)     // Catch:{ all -> 0x00ba }
                        java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ba }
                        android.content.Context r3 = r5     // Catch:{ all -> 0x00ba }
                        r4 = 10
                        com.umeng.commonsdk.stateless.f.a(r3, r2, r4)     // Catch:{ all -> 0x00ba }
                        com.umeng.commonsdk.stateless.UMSLEnvelopeBuild r2 = new com.umeng.commonsdk.stateless.UMSLEnvelopeBuild     // Catch:{ all -> 0x00ba }
                        r2.<init>()     // Catch:{ all -> 0x00ba }
                        android.content.Context r3 = r5     // Catch:{ all -> 0x00ba }
                        org.json.JSONObject r3 = r2.buildSLBaseHeader(r3)     // Catch:{ all -> 0x00ba }
                        org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b8 }
                        r4.<init>()     // Catch:{ JSONException -> 0x00b8 }
                        java.lang.String r5 = "content"
                        r4.put(r5, r1)     // Catch:{ JSONException -> 0x00b8 }
                        java.lang.String r1 = "ts"
                        long r5 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x00b8 }
                        r4.put(r1, r5)     // Catch:{ JSONException -> 0x00b8 }
                        org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b8 }
                        r1.<init>()     // Catch:{ JSONException -> 0x00b8 }
                        java.lang.String r5 = "crash"
                        r1.put(r5, r4)     // Catch:{ JSONException -> 0x00b8 }
                        org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b8 }
                        r4.<init>()     // Catch:{ JSONException -> 0x00b8 }
                        java.lang.String r5 = "tp"
                        r4.put(r5, r1)     // Catch:{ JSONException -> 0x00b8 }
                        android.content.Context r1 = r5     // Catch:{ JSONException -> 0x00b8 }
                        java.lang.String r5 = "umpx_internal"
                        org.json.JSONObject r1 = r2.buildSLEnvelope(r1, r3, r4, r5)     // Catch:{ JSONException -> 0x00b8 }
                        if (r1 == 0) goto L_0x00b8
                        java.lang.String r2 = "exception"
                        r1.has(r2)     // Catch:{ JSONException -> 0x00b8 }
                    L_0x00b8:
                        monitor-exit(r0)     // Catch:{ all -> 0x00ba }
                        goto L_0x00bd
                    L_0x00ba:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x00ba }
                        throw r1     // Catch:{ Throwable -> 0x00bd }
                    L_0x00bd:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.crash.UMCrashManager.AnonymousClass1.run():void");
                }
            }).start();
        }
    }
}
