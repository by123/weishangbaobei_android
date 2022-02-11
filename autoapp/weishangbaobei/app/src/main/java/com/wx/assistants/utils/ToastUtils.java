package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;
import com.janedler.V2.JToast;
import java.io.File;

public class ToastUtils {
    public static File cameraFile;

    public static int[] getScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.heightPixels, displayMetrics.widthPixels};
    }

    public static int getSecreenW(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean GetSDState() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File deleteFile : listFiles) {
                    deleteFile(deleteFile);
                }
            }
            file.delete();
        }
    }

    public static void showToast(Context context, String str) {
        if (context != null && str != null) {
            if (AppManager.getAppManager() == null || AppManager.getAppManager().currentActivity() == null) {
                Toast.makeText(context, str, 1).show();
                return;
            }
            try {
                JToast.make(AppManager.getAppManager().currentActivity().getWindow().getDecorView(), (CharSequence) str, 0).show();
            } catch (Exception unused) {
                Toast.makeText(context, str, 1).show();
            }
        }
    }

    public static int getBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 38;
        }
    }

    public static long getSeq() {
        return System.currentTimeMillis();
    }

    public static boolean isNetUseable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }
        showToast(context, "无网络连接");
        return false;
    }
}
