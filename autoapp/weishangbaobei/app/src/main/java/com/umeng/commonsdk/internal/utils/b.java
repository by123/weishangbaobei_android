package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.statistics.common.ULog;

/* compiled from: BaseStationUtils */
public class b {
    private static final String b = "BaseStationUtils";
    private static boolean c;
    /* access modifiers changed from: private */
    public static Context d;
    PhoneStateListener a;
    /* access modifiers changed from: private */
    public TelephonyManager e;

    private b(Context context) {
        this.a = new PhoneStateListener() {
            /* JADX WARNING: Can't wrap try/catch for region: R(8:1|2|(2:7|(1:33)(3:17|(2:22|(2:27|(1:31))(1:26))(1:21)|32))(1:6)|34|(2:36|37)|38|39|41) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0149 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSignalStrengthsChanged(android.telephony.SignalStrength r8) {
                /*
                    r7 = this;
                    super.onSignalStrengthsChanged(r8)
                    java.lang.String r0 = "BaseStationUtils"
                    r1 = 1
                    java.lang.Object[] r2 = new java.lang.Object[r1]
                    java.lang.String r3 = "base station onSignalStrengthsChanged"
                    r4 = 0
                    r2[r4] = r3
                    com.umeng.commonsdk.statistics.common.ULog.e((java.lang.String) r0, (java.lang.Object[]) r2)
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.content.Context r2 = com.umeng.commonsdk.internal.utils.b.d     // Catch:{ Exception -> 0x014e }
                    java.lang.String r3 = "phone"
                    java.lang.Object r2 = r2.getSystemService(r3)     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager unused = r0.e = r2     // Catch:{ Exception -> 0x014e }
                    java.lang.String r0 = r8.toString()     // Catch:{ Exception -> 0x014e }
                    java.lang.String r2 = " "
                    java.lang.String[] r0 = r0.split(r2)     // Catch:{ Exception -> 0x014e }
                    r2 = 0
                    com.umeng.commonsdk.internal.utils.b r3 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r3 = r3.e     // Catch:{ Exception -> 0x014e }
                    r5 = 9
                    if (r3 == 0) goto L_0x005d
                    com.umeng.commonsdk.internal.utils.b r3 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r3 = r3.e     // Catch:{ Exception -> 0x014e }
                    int r3 = r3.getNetworkType()     // Catch:{ Exception -> 0x014e }
                    r6 = 13
                    if (r3 != r6) goto L_0x005d
                    r8 = r0[r5]     // Catch:{ Exception -> 0x014e }
                    int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014e }
                    r0.<init>()     // Catch:{ Exception -> 0x014e }
                    java.lang.String r2 = ""
                    r0.append(r2)     // Catch:{ Exception -> 0x014e }
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r8 = r0.toString()     // Catch:{ Exception -> 0x014e }
                    goto L_0x0113
                L_0x005d:
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r0 = r0.e     // Catch:{ Exception -> 0x014e }
                    if (r0 == 0) goto L_0x00fa
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r0 = r0.e     // Catch:{ Exception -> 0x014e }
                    int r0 = r0.getNetworkType()     // Catch:{ Exception -> 0x014e }
                    r3 = 8
                    if (r0 == r3) goto L_0x009a
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r0 = r0.e     // Catch:{ Exception -> 0x014e }
                    int r0 = r0.getNetworkType()     // Catch:{ Exception -> 0x014e }
                    r3 = 10
                    if (r0 == r3) goto L_0x009a
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r0 = r0.e     // Catch:{ Exception -> 0x014e }
                    int r0 = r0.getNetworkType()     // Catch:{ Exception -> 0x014e }
                    if (r0 == r5) goto L_0x009a
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    android.telephony.TelephonyManager r0 = r0.e     // Catch:{ Exception -> 0x014e }
                    int r0 = r0.getNetworkType()     // Catch:{ Exception -> 0x014e }
                    r3 = 3
                    if (r0 != r3) goto L_0x00fa
                L_0x009a:
                    com.umeng.commonsdk.internal.utils.b r0 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    java.lang.String r0 = r0.e()     // Catch:{ Exception -> 0x014e }
                    boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014e }
                    if (r3 != 0) goto L_0x00b1
                    java.lang.String r3 = "中国移动"
                    boolean r3 = r0.equals(r3)     // Catch:{ Exception -> 0x014e }
                    if (r3 == 0) goto L_0x00b1
                    java.lang.String r2 = "0"
                    goto L_0x00f8
                L_0x00b1:
                    boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014e }
                    if (r3 != 0) goto L_0x00d5
                    java.lang.String r3 = "中国联通"
                    boolean r3 = r0.equals(r3)     // Catch:{ Exception -> 0x014e }
                    if (r3 == 0) goto L_0x00d5
                    int r8 = r8.getCdmaDbm()     // Catch:{ Exception -> 0x014e }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014e }
                    r0.<init>()     // Catch:{ Exception -> 0x014e }
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r8 = ""
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r2 = r0.toString()     // Catch:{ Exception -> 0x014e }
                    goto L_0x00f8
                L_0x00d5:
                    boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014e }
                    if (r3 != 0) goto L_0x00f8
                    java.lang.String r3 = "中国电信"
                    boolean r0 = r0.equals(r3)     // Catch:{ Exception -> 0x014e }
                    if (r0 == 0) goto L_0x00f8
                    int r8 = r8.getEvdoDbm()     // Catch:{ Exception -> 0x014e }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014e }
                    r0.<init>()     // Catch:{ Exception -> 0x014e }
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r8 = ""
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r2 = r0.toString()     // Catch:{ Exception -> 0x014e }
                L_0x00f8:
                    r8 = r2
                    goto L_0x0113
                L_0x00fa:
                    int r8 = r8.getGsmSignalStrength()     // Catch:{ Exception -> 0x014e }
                    int r8 = r8 * 2
                    int r8 = r8 + -113
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014e }
                    r0.<init>()     // Catch:{ Exception -> 0x014e }
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r8 = ""
                    r0.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r8 = r0.toString()     // Catch:{ Exception -> 0x014e }
                L_0x0113:
                    java.lang.String r0 = "BaseStationUtils"
                    java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x014e }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014e }
                    r2.<init>()     // Catch:{ Exception -> 0x014e }
                    java.lang.String r3 = "stationStrength is "
                    r2.append(r3)     // Catch:{ Exception -> 0x014e }
                    r2.append(r8)     // Catch:{ Exception -> 0x014e }
                    java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x014e }
                    r1[r4] = r2     // Catch:{ Exception -> 0x014e }
                    com.umeng.commonsdk.statistics.common.ULog.e((java.lang.String) r0, (java.lang.Object[]) r1)     // Catch:{ Exception -> 0x014e }
                    boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x014e }
                    if (r0 != 0) goto L_0x0149
                    android.content.Context r0 = com.umeng.commonsdk.internal.utils.b.d     // Catch:{ Throwable -> 0x0149 }
                    r1 = 32772(0x8004, float:4.5923E-41)
                    android.content.Context r2 = com.umeng.commonsdk.internal.utils.b.d     // Catch:{ Throwable -> 0x0149 }
                    com.umeng.commonsdk.internal.b r2 = com.umeng.commonsdk.internal.b.a(r2)     // Catch:{ Throwable -> 0x0149 }
                    com.umeng.commonsdk.internal.c r2 = r2.a()     // Catch:{ Throwable -> 0x0149 }
                    com.umeng.commonsdk.framework.UMWorkDispatch.sendEvent(r0, r1, r2, r8)     // Catch:{ Throwable -> 0x0149 }
                L_0x0149:
                    com.umeng.commonsdk.internal.utils.b r8 = com.umeng.commonsdk.internal.utils.b.this     // Catch:{ Exception -> 0x014e }
                    r8.c()     // Catch:{ Exception -> 0x014e }
                L_0x014e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.b.AnonymousClass1.onSignalStrengthsChanged(android.telephony.SignalStrength):void");
            }
        };
        if (context != null) {
            try {
                this.e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: BaseStationUtils */
    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b(b.d);

        private a() {
        }
    }

    public static b a(Context context) {
        if (d == null && context != null) {
            d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    public synchronized boolean a() {
        return c;
    }

    /* access modifiers changed from: private */
    public String e() {
        String str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) d.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (!simOperator.equals("46000")) {
                if (!simOperator.equals("46002")) {
                    if (simOperator.equals("46001")) {
                        str = "中国联通";
                    } else if (!simOperator.equals("46003")) {
                        return null;
                    } else {
                        str = "中国电信";
                    }
                    return str;
                }
            }
            str = "中国移动";
            return str;
        } catch (Throwable unused) {
            return null;
        }
    }

    public synchronized void b() {
        ULog.e(b, "base station registerListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 256);
            }
            c = true;
        } catch (Throwable unused) {
        }
    }

    public synchronized void c() {
        ULog.e(b, "base station unRegisterListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 0);
            }
            c = false;
        } catch (Throwable unused) {
        }
    }
}
