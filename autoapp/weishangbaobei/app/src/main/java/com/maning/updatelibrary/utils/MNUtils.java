package com.maning.updatelibrary.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public class MNUtils {
    public static String getCachePath(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }

    public static void changeApkFileMode(File file) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + file.getParent());
            Runtime.getRuntime().exec("chmod 777 " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
