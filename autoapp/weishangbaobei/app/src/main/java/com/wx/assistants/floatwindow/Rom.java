package com.wx.assistants.floatwindow;

import android.content.Context;
import android.content.Intent;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

class Rom {
    Rom() {
    }

    static boolean isIntentAvailable(Intent intent, Context context) {
        return intent != null && context.getPackageManager().queryIntentActivities(intent, WXMediaMessage.THUMB_LENGTH_LIMIT).size() > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049 A[SYNTHETIC, Splitter:B:18:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b A[SYNTHETIC, Splitter:B:26:0x005b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getProp(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            r2.<init>()     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            r2.append(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.lang.Process r4 = r1.exec(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch:{ IOException -> 0x0041, all -> 0x003f }
            java.lang.String r4 = r1.readLine()     // Catch:{ IOException -> 0x0042 }
            r1.close()     // Catch:{ IOException -> 0x0042 }
            r1.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x003e
        L_0x0035:
            r0 = move-exception
            java.lang.String r1 = "WS_BABY_Catch_23"
            com.wx.assistants.utils.LogUtils.log(r1)
            r0.printStackTrace()
        L_0x003e:
            return r4
        L_0x003f:
            r4 = move-exception
            goto L_0x0059
        L_0x0041:
            r1 = r0
        L_0x0042:
            java.lang.String r4 = "WS_BABY_Catch_22"
            com.wx.assistants.utils.LogUtils.log(r4)     // Catch:{ all -> 0x0057 }
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0056
        L_0x004d:
            r4 = move-exception
            java.lang.String r1 = "WS_BABY_Catch_23"
            com.wx.assistants.utils.LogUtils.log(r1)
            r4.printStackTrace()
        L_0x0056:
            return r0
        L_0x0057:
            r4 = move-exception
            r0 = r1
        L_0x0059:
            if (r0 == 0) goto L_0x0068
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0068
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "WS_BABY_Catch_23"
            com.wx.assistants.utils.LogUtils.log(r1)
            r0.printStackTrace()
        L_0x0068:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.floatwindow.Rom.getProp(java.lang.String):java.lang.String");
    }
}
