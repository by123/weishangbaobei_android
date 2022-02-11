package com.wx.assistants.globle;

import android.text.TextUtils;
import android.util.Log;
import com.orhanobut.logger.Logger;

public class LogUtils {
    public static final boolean ENABLE_LOG = true;

    private LogUtils() {
    }

    public static void d(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            Log.d(str, str2);
        }
    }

    public static void e(String str) {
        Logger.e(str, new Object[0]);
    }

    public static void e(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            Log.e(str, str2);
        }
    }

    public static void e(Throwable th, String str, Object... objArr) {
        Logger.e(th, str, objArr);
    }

    public static void i(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            Log.i(str, str2);
        }
    }

    public static void json(String str) {
        Logger.json(str);
    }

    public static void json(String str, String str2) {
        Logger.t(str).json(str2);
    }

    public static void netJson(String str) {
        Logger.t("网络调试-》").json(str);
    }

    public static void netUrl(String str) {
        Logger.t("网络调试-》").e(str, new Object[0]);
    }

    public static void v(String str, Object... objArr) {
        Logger.v(str, objArr);
    }

    public static void w(String str, Object... objArr) {
        Logger.w(str, objArr);
    }

    public static void wtf(String str, Object... objArr) {
        Logger.wtf(str, objArr);
    }

    public static void xml(String str) {
        Logger.xml(str);
    }
}
