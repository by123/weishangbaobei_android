package com.tencent.wxop.stat.common;

import java.io.File;

class p {
    private static int a = -1;

    public static boolean a() {
        if (a == 1) {
            return true;
        }
        if (a == 0) {
            return false;
        }
        String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 6) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    a = 1;
                    return true;
                }
                i++;
            } catch (Exception unused) {
            }
        }
        a = 0;
        return false;
    }
}
