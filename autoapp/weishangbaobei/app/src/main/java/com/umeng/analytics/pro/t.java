package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: SessionIdGenerateServiceImpl */
class t implements s {
    private long a = AnalyticsConfig.kContinueSessionMillis;

    t() {
    }

    public void a(long j) {
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public String a(Context context) {
        String deviceId = DeviceConfig.getDeviceId(context);
        String appkey = UMUtils.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey != null) {
            return UMUtils.MD5(currentTimeMillis + appkey + deviceId);
        }
        throw new RuntimeException("Appkey is null or empty, Please check!");
    }

    public boolean a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((j == 0 || currentTimeMillis - j >= this.a) && j2 > 0 && currentTimeMillis - j2 > this.a) {
            return true;
        }
        return false;
    }

    public void a(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(q.c, str);
            edit.putLong(q.b, 0);
            edit.putLong(q.e, currentTimeMillis);
            edit.putLong(q.f, 0);
            edit.commit();
        } catch (Exception unused) {
        }
    }
}
