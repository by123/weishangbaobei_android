package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.ULog;

public class b {
    private static final String b = "BaseStationUtils";
    private static boolean c;
    /* access modifiers changed from: private */
    public static Context d;
    PhoneStateListener a;
    /* access modifiers changed from: private */
    public TelephonyManager e;

    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b(b.d);

        private a() {
        }
    }

    private b(Context context) {
        this.a = new PhoneStateListener() {
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                ULog.e(b.b, "base station onSignalStrengthsChanged");
                TelephonyManager unused = b.this.e = (TelephonyManager) b.d.getSystemService("phone");
                String[] split = signalStrength.toString().split(" ");
                String str = null;
                if (b.this.e != null && b.this.e.getNetworkType() == 13) {
                    str = "" + Integer.parseInt(split[9]);
                } else if (b.this.e == null || !(b.this.e.getNetworkType() == 8 || b.this.e.getNetworkType() == 10 || b.this.e.getNetworkType() == 9 || b.this.e.getNetworkType() == 3)) {
                    int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                    StringBuilder sb = new StringBuilder();
                    sb.append((gsmSignalStrength * 2) - 113);
                    sb.append("");
                    str = sb.toString();
                } else {
                    String b = b.this.e();
                    if (TextUtils.isEmpty(b) || !b.equals("中国移动")) {
                        try {
                            if (!TextUtils.isEmpty(b) && b.equals("中国联通")) {
                                str = signalStrength.getCdmaDbm() + "";
                            } else if (!TextUtils.isEmpty(b) && b.equals("中国电信")) {
                                str = signalStrength.getEvdoDbm() + "";
                            }
                        } catch (Exception e) {
                            return;
                        }
                    } else {
                        str = "0";
                    }
                }
                ULog.e(b.b, "stationStrength is " + str);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        UMWorkDispatch.sendEvent(b.d, com.umeng.commonsdk.internal.a.h, com.umeng.commonsdk.internal.b.a(b.d).a(), str);
                    } catch (Throwable th) {
                    }
                }
                b.this.c();
            }
        };
        if (context != null) {
            try {
                this.e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable th) {
            }
        }
    }

    public static b a(Context context) {
        if (d == null && context != null) {
            d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
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
            if (!TextUtils.isEmpty(simOperator)) {
                if (simOperator.equals("46000") || simOperator.equals("46002")) {
                    str = "中国移动";
                    return str;
                }
                if (simOperator.equals("46001")) {
                    str = "中国联通";
                } else if (simOperator.equals("46003")) {
                    str = "中国电信";
                }
                return str;
            }
            str = null;
            return str;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this) {
            z = c;
        }
        return z;
    }

    public void b() {
        synchronized (this) {
            ULog.e(b, "base station registerListener");
            try {
                if (this.e != null) {
                    this.e.listen(this.a, 256);
                }
                c = true;
            } catch (Throwable th) {
            }
        }
    }

    public void c() {
        synchronized (this) {
            ULog.e(b, "base station unRegisterListener");
            try {
                if (this.e != null) {
                    this.e.listen(this.a, 0);
                }
                c = false;
            } catch (Throwable th) {
            }
        }
    }
}
