package com.wx.assistants.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import com.wx.assistants.application.MyApplication;

public class CommonUtils {
    public static boolean checkNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return !(connectivityManager.getActiveNetworkInfo() != null ? connectivityManager.getActiveNetworkInfo().isAvailable() : false);
    }

    public static int dip2px(int i) {
        double d = (double) (getResources().getDisplayMetrics().density * ((float) i));
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    public static ColorStateList getColorStateList(int i) {
        return getResources().getColorStateList(i);
    }

    public static Context getContext() {
        return MyApplication.getConText();
    }

    public static int getDimens(int i) {
        return getResources().getDimensionPixelSize(i);
    }

    public static Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    public static Thread getMainThread() {
        return MyApplication.getMainThread();
    }

    public static int getMainThreadId() {
        return MyApplication.getMainId();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int i) {
        return getResources().getString(i);
    }

    public static String[] getStringArray(int i) {
        return getResources().getStringArray(i);
    }

    public static View inflate(int i) {
        return View.inflate(getContext(), i, (ViewGroup) null);
    }

    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isRunInMainThread() {
        return getMainThreadId() == Process.myTid();
    }

    public static void postDelayed(Runnable runnable, int i) {
        getHandler().postDelayed(runnable, (long) i);
    }

    public static int px2dip(int i) {
        double d = (double) (((float) i) / getResources().getDisplayMetrics().density);
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    public static void runOnMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }
}
