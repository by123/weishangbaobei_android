package com.umeng.weixin.umengwx;

import android.os.Bundle;

public class f {
    public static int a(Bundle bundle, String str) {
        if (bundle == null) {
            return -1;
        }
        try {
            return bundle.getInt(str, -1);
        } catch (Exception e) {
            return -1;
        }
    }

    public static String b(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            return null;
        }
    }
}
