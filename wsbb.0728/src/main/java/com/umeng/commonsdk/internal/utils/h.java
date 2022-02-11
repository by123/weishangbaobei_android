package com.umeng.commonsdk.internal.utils;

import android.os.Build;
import com.umeng.commonsdk.internal.utils.e;
import java.io.File;

public class h {
    public static boolean a() {
        return b() || c() || d() || e();
    }

    private static boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean c() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            return new File("/system/app/Kinguser.apk").exists();
        } catch (Exception e2) {
        }
    }

    private static boolean d() {
        return new e().a(e.a.check_su_binary) != null;
    }

    private static boolean e() {
        for (String str : new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
