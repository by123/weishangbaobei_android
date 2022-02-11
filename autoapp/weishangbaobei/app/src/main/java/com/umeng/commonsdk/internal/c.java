package com.umeng.commonsdk.internal;

import android.content.Context;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import org.json.JSONObject;

/* compiled from: UMInternalDataProtocol */
public class c implements UMLogDataProtocol {
    private static final String b = "info";
    private static final String c = "stat";
    private Context a;

    public void removeCacheData(Object obj) {
    }

    public JSONObject setupReportData(long j) {
        return null;
    }

    public c(Context context) {
        if (context != null) {
            this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void workEvent(java.lang.Object r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = "walle"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "[internal] workEvent"
            r4 = 0
            r2[r4] = r3
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r0, (java.lang.Object[]) r2)
            r0 = 2
            switch(r8) {
                case 32769: goto L_0x016c;
                case 32770: goto L_0x0194;
                case 32771: goto L_0x0128;
                case 32772: goto L_0x00e4;
                case 32773: goto L_0x00bf;
                case 32774: goto L_0x00a0;
                case 32775: goto L_0x0079;
                case 32776: goto L_0x0056;
                case 32777: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0194
        L_0x0013:
            java.lang.String r7 = "walle"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{  }
            java.lang.String r0 = "[internal] workEvent send envelope"
            r8[r4] = r0     // Catch:{  }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r7, (java.lang.Object[]) r8)     // Catch:{  }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{  }
            r7.<init>()     // Catch:{  }
            java.lang.String r8 = "i_sdk_v"
            java.lang.String r0 = "1.2.0"
            r7.put(r8, r0)     // Catch:{  }
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{  }
            r8.<init>()     // Catch:{  }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{  }
            r0.<init>()     // Catch:{  }
            java.lang.String r2 = "inner"
            r8.put(r2, r0)     // Catch:{  }
            android.content.Context r0 = r6.a     // Catch:{  }
            org.json.JSONObject r7 = com.umeng.commonsdk.framework.UMEnvelopeBuild.buildEnvelopeWithExtHeader(r0, r7, r8)     // Catch:{  }
            if (r7 == 0) goto L_0x0194
            java.lang.String r8 = "exception"
            boolean r7 = r7.has(r8)     // Catch:{  }
            if (r7 != 0) goto L_0x0194
            java.lang.String r7 = "walle"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{  }
            java.lang.String r0 = "[internal] workEvent send envelope back, result is ok"
            r8[r4] = r0     // Catch:{  }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r7, (java.lang.Object[]) r8)     // Catch:{  }
            goto L_0x0194
        L_0x0056:
            android.content.Context r8 = r6.a
            android.content.Context r8 = r8.getApplicationContext()
            android.content.Context r8 = com.stub.StubApp.getOrigApplicationContext(r8)
            java.lang.String r0 = "info"
            android.content.SharedPreferences r8 = r8.getSharedPreferences(r0, r4)
            if (r8 == 0) goto L_0x0194
            android.content.SharedPreferences$Editor r8 = r8.edit()
            java.lang.String r0 = "stat"
            java.lang.String r7 = (java.lang.String) r7
            android.content.SharedPreferences$Editor r7 = r8.putString(r0, r7)
            r7.commit()
            goto L_0x0194
        L_0x0079:
            java.lang.String r8 = "com.umeng.commonsdk.internal.utils.InfoPreferenceAgent"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{  }
            if (r8 == 0) goto L_0x0194
            java.lang.String r2 = "saveUA"
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch:{  }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r3[r4] = r5     // Catch:{  }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r1] = r5     // Catch:{  }
            java.lang.reflect.Method r2 = r8.getMethod(r2, r3)     // Catch:{  }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{  }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{  }
            android.content.Context r3 = r6.a     // Catch:{  }
            r0[r4] = r3     // Catch:{  }
            r0[r1] = r7     // Catch:{  }
            r2.invoke(r8, r0)     // Catch:{  }
            goto L_0x0194
        L_0x00a0:
            java.lang.String r7 = "com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{  }
            if (r7 == 0) goto L_0x0194
            java.lang.String r8 = "wifiChange"
            java.lang.Class[] r0 = new java.lang.Class[r1]     // Catch:{  }
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r0[r4] = r2     // Catch:{  }
            java.lang.reflect.Method r8 = r7.getMethod(r8, r0)     // Catch:{  }
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{  }
            android.content.Context r1 = r6.a     // Catch:{  }
            r0[r4] = r1     // Catch:{  }
            r8.invoke(r7, r0)     // Catch:{  }
            goto L_0x0194
        L_0x00bf:
            java.lang.String r8 = "com.umeng.commonsdk.internal.utils.InfoPreferenceAgent"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{  }
            if (r8 == 0) goto L_0x0194
            java.lang.String r2 = "saveBluetoothInfo"
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch:{  }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r3[r4] = r5     // Catch:{  }
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            r3[r1] = r5     // Catch:{  }
            java.lang.reflect.Method r2 = r8.getMethod(r2, r3)     // Catch:{  }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{  }
            android.content.Context r3 = r6.a     // Catch:{  }
            r0[r4] = r3     // Catch:{  }
            r0[r1] = r7     // Catch:{  }
            r2.invoke(r8, r0)     // Catch:{  }
            goto L_0x0194
        L_0x00e4:
            java.lang.String r8 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "[internal] workEvent cache station, event is "
            r3.append(r5)
            java.lang.String r5 = r7.toString()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r2[r4] = r3
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r8, (java.lang.Object[]) r2)
            java.lang.String r8 = "com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{  }
            if (r8 == 0) goto L_0x0194
            java.lang.String r2 = "saveBaseStationStrength"
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch:{  }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r3[r4] = r5     // Catch:{  }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r1] = r5     // Catch:{  }
            java.lang.reflect.Method r2 = r8.getMethod(r2, r3)     // Catch:{  }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{  }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{  }
            android.content.Context r3 = r6.a     // Catch:{  }
            r0[r4] = r3     // Catch:{  }
            r0[r1] = r7     // Catch:{  }
            r2.invoke(r8, r0)     // Catch:{  }
            goto L_0x0194
        L_0x0128:
            java.lang.String r8 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "[internal] workEvent cache battery, event is "
            r3.append(r5)
            java.lang.String r5 = r7.toString()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r2[r4] = r3
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r8, (java.lang.Object[]) r2)
            java.lang.String r8 = "com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{  }
            if (r8 == 0) goto L_0x0194
            java.lang.String r2 = "saveBattery"
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch:{  }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r3[r4] = r5     // Catch:{  }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r1] = r5     // Catch:{  }
            java.lang.reflect.Method r2 = r8.getMethod(r2, r3)     // Catch:{  }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{  }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{  }
            android.content.Context r3 = r6.a     // Catch:{  }
            r0[r4] = r3     // Catch:{  }
            r0[r1] = r7     // Catch:{  }
            r2.invoke(r8, r0)     // Catch:{  }
            goto L_0x0194
        L_0x016c:
            java.lang.String r7 = "walle"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{  }
            java.lang.String r0 = "[internal] workEvent send envelope"
            r8[r4] = r0     // Catch:{  }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r7, (java.lang.Object[]) r8)     // Catch:{  }
            java.lang.String r7 = "com.umeng.commonsdk.internal.UMInternalManagerAgent"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ Exception -> 0x0194 }
            if (r7 == 0) goto L_0x0194
            java.lang.String r8 = "sendInternalEnvelopeByStateful2"
            java.lang.Class[] r0 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0194 }
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r0[r4] = r2     // Catch:{ Exception -> 0x0194 }
            java.lang.reflect.Method r8 = r7.getMethod(r8, r0)     // Catch:{ Exception -> 0x0194 }
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0194 }
            android.content.Context r1 = r6.a     // Catch:{ Exception -> 0x0194 }
            r0[r4] = r1     // Catch:{ Exception -> 0x0194 }
            r8.invoke(r7, r0)     // Catch:{ Exception -> 0x0194 }
        L_0x0194:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.c.workEvent(java.lang.Object, int):void");
    }
}
