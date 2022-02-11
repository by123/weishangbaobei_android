package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;

public class a {
    private static Context a;
    private String b;
    private String c;

    /* renamed from: com.umeng.commonsdk.statistics.internal.a$a  reason: collision with other inner class name */
    private static class C0010a {
        /* access modifiers changed from: private */
        public static final a a = new a();

        private C0010a() {
        }
    }

    private a() {
        this.b = null;
        this.c = null;
    }

    public static a a(Context context) {
        if (a == null && context != null) {
            a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return C0010a.a;
    }

    private void d(String str) {
        try {
            String replaceAll = str.replaceAll("&=", " ").replaceAll("&&", " ").replaceAll("==", "/");
            this.b = replaceAll + "/" + "Android" + "/" + Build.DISPLAY + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE + " " + HelperUtils.getUmengMD5(UMUtils.getAppkey(a));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(a, th);
        }
    }

    private void e(String str) {
        try {
            String str2 = str.split("&&")[0];
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split("&=");
                StringBuilder sb = new StringBuilder();
                sb.append(e.ax);
                for (String str3 : split) {
                    if (!TextUtils.isEmpty(str3)) {
                        String substring = str3.substring(0, 2);
                        if (substring.endsWith("=")) {
                            substring = substring.replace("=", "");
                        }
                        sb.append(substring);
                    }
                }
                this.c = sb.toString();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(a, th);
        }
    }

    public String a() {
        return this.c;
    }

    public boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(e.al);
        }
        return false;
    }

    public String b() {
        return this.b;
    }

    public boolean b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(e.ar);
        }
        return false;
    }

    public void c(String str) {
        String substring = str.substring(0, str.indexOf(95));
        e(substring);
        d(substring);
    }
}
