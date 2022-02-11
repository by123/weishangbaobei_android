package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

public class k {
    private b a = new b(this.b);
    private n b;

    private k(String str) {
        this.b = new n(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x001a A[LOOP:0: B:1:0x001a->B:4:0x002c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(android.app.Activity r3, java.lang.String r4, com.umeng.qq.tencent.j r5, java.lang.String r6) {
        /*
            r2 = this;
            android.content.Context r6 = r3.getApplicationContext()
            android.content.Context r6 = com.stub.StubApp.getOrigApplicationContext(r6)
            java.lang.String r6 = r6.getPackageName()
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            r1 = 128(0x80, float:1.794E-43)
            java.util.List r0 = r0.getInstalledApplications(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x001a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002e
            java.lang.Object r1 = r0.next()
            android.content.pm.ApplicationInfo r1 = (android.content.pm.ApplicationInfo) r1
            java.lang.String r1 = r1.packageName
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L_0x001a
        L_0x002e:
            r6 = 0
            com.umeng.qq.tencent.h.f = r6
            com.umeng.qq.tencent.b r0 = r2.a
            int r3 = r0.a(r3, r4, r5, r6)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.qq.tencent.k.a(android.app.Activity, java.lang.String, com.umeng.qq.tencent.j, java.lang.String):int");
    }

    public static k a(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.umeng.qq.tencent.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.umeng.qq.tencent.AssistActivity"), 0);
            return new k(str);
        } catch (PackageManager.NameNotFoundException unused) {
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
