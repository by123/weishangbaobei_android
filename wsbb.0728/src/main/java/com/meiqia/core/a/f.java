package com.meiqia.core.a;

import android.util.Log;
import com.meiqia.core.MeiQiaService;
import okhttp3.Request;

public class f {
    public static void a(String str) {
        if (MeiQiaService.a) {
            Log.e("meiqia_log", str);
        }
    }

    public static void a(Request request) {
    }

    public static void b(String str) {
        if (MeiQiaService.a) {
            Log.d("meiqia_log", str);
        }
    }
}
