package com.tencent.mm.opensdk.utils;

public final class d {
    public static boolean c(int i) {
        return i == 36 || i == 46;
    }

    public static boolean i(String str) {
        return str == null || str.length() <= 0;
    }

    public static int j(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Integer.parseInt(str);
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }
}
