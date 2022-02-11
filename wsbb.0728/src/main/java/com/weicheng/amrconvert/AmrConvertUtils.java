package com.weicheng.amrconvert;

import android.content.Context;
import android.util.Log;
import java.io.File;

public class AmrConvertUtils {
    private static final String TAG = "AmrConvertUtils";

    static {
        System.loadLibrary("amrconvert");
    }

    private static native int amr2Mp3(String str, String str2, String str3);

    public static boolean amr2Mp3(Context context, String str, String str2) {
        String str3 = "/data/data/" + context.getPackageName() + File.separator + "t.t";
        File file = new File(str3);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Log.v(TAG, "createNewFile mp32Amr :" + e.toString());
                return false;
            }
        }
        if (!file.exists()) {
            return false;
        }
        int amr2Mp3 = amr2Mp3(str, str2, str3);
        file.delete();
        return amr2Mp3 == 0;
    }

    private static native int mp32Amr(String str, String str2, String str3);

    public static boolean mp32Amr(Context context, String str, String str2) {
        String str3 = "/data/data/" + context.getPackageName() + File.separator + "t1.t";
        File file = new File(str3);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Log.v(TAG, "createNewFile mp32Amr :" + e.toString());
                return false;
            }
        }
        if (!file.exists()) {
            return false;
        }
        int mp32Amr = mp32Amr(str, str2, str3);
        file.delete();
        return mp32Amr == 0;
    }

    private static native int mp32MultAmr(String str, String str2, String str3);

    public static boolean mp32MultAmr(Context context, String str, String str2) {
        String str3 = "/data/data/" + context.getPackageName() + File.separator + "t0.pcm";
        File file = new File(str3);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Log.v(TAG, "createNewFile mp32MultAmr :" + e.toString());
                return false;
            }
        }
        return file.exists() && mp32MultAmr(str, str2, str3) == 0;
    }
}
