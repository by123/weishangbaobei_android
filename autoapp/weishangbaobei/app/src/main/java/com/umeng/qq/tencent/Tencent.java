package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.utils.DeviceConfig;

public class Tencent {
    private static Tencent a;
    private k b;

    private Tencent(String str, Context context) {
        this.b = k.a(str, context);
    }

    public static synchronized Tencent createInstance(String str, Context context) {
        Tencent tencent;
        Tencent tencent2;
        synchronized (Tencent.class) {
            if (a == null) {
                tencent2 = new Tencent(str, context);
            } else {
                if (!str.equals(a.getAppId())) {
                    a.logout();
                    tencent2 = new Tencent(str, context);
                }
                tencent = a;
            }
            a = tencent2;
            tencent = a;
        }
        return tencent;
    }

    public static void handleResultData(Intent intent, j jVar) {
        p.a().a(intent, jVar);
    }

    public static boolean onActivityResultData(int i, int i2, Intent intent, j jVar) {
        return p.a().a(i, i2, intent, jVar);
    }

    public String getAppId() {
        return this.b.a().b();
    }

    public n getQQToken() {
        return this.b.a();
    }

    public boolean isSupportSSOLogin(Activity activity) {
        if ((l.c((Context) activity) && DeviceConfig.getAppVersion("com.tencent.minihd.qq", activity) != null) || DeviceConfig.getAppVersion("com.tencent.tim", activity) != null) {
            return true;
        }
        if (DeviceConfig.getAppVersion("com.tencent.mobileqq", activity) == null) {
            return false;
        }
        return s.b(activity);
    }

    public int login(Activity activity, String str, j jVar) {
        return this.b.a(activity, str, jVar);
    }

    public void logout() {
        String str = null;
        this.b.a().a(str, "0");
        this.b.a().a(str);
    }

    public void release() {
        a = null;
        this.b = null;
    }

    public void setAccessToken(String str, String str2) {
        this.b.a().a(str, str2);
    }

    public void setOpenId(String str) {
        this.b.a().a(str);
    }

    public void shareToQQ(Activity activity, Bundle bundle, j jVar) {
        new m(activity, this.b.a()).a(activity, bundle, jVar);
    }

    public void shareToQzone(Activity activity, Bundle bundle, j jVar) {
        new o(activity, this.b.a()).a(activity, bundle, jVar);
    }
}
