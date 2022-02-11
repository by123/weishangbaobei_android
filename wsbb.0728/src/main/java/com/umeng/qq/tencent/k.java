package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.stub.StubApp;
import com.umeng.socialize.ShareContent;
import java.util.Iterator;

public class k {
    private b a = new b(this.b);
    private n b;

    private k(String str) {
        this.b = new n(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x001b A[LOOP:0: B:1:0x001b->B:4:0x002d, LOOP_START] */
    private int a(Activity activity, String str, j jVar, String str2) {
        String packageName = StubApp.getOrigApplicationContext(activity.getApplicationContext()).getPackageName();
        Iterator<ApplicationInfo> it = activity.getPackageManager().getInstalledApplications(ShareContent.MINAPP_STYLE).iterator();
        while (it.hasNext() && !packageName.equals(it.next().packageName)) {
            while (it.hasNext() && !packageName.equals(it.next().packageName)) {
            }
        }
        h.f = false;
        return this.a.a(activity, str, jVar, false);
    }

    public static k a(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.umeng.qq.tencent.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.umeng.qq.tencent.AssistActivity"), 0);
            return new k(str);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public int a(Activity activity, String str, j jVar) {
        return a(activity, str, jVar, "");
    }

    public n a() {
        return this.b;
    }
}
