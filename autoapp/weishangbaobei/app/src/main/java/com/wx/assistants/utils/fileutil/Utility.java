package com.wx.assistants.utils.fileutil;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import com.wx.assistants.application.MyApplication;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Utility {
    public static String getExternalCacheDir(Context context) {
        APIUtils.hasFroyo();
        context.getExternalCacheDir();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            externalStorageDirectory = context.getCacheDir();
        }
        if (externalStorageDirectory == null) {
            return null;
        }
        return externalStorageDirectory.getAbsolutePath();
    }

    public static String getPkgName() {
        Context conText = MyApplication.getConText();
        return conText != null ? conText.getPackageName() : "";
    }

    public static void closeSafely(Cursor cursor) {
        try {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isActivityFinishing(Activity activity) {
        return activity != null && activity.isFinishing();
    }

    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static String urlEncode(String str) {
        TextUtils.isEmpty(str);
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
