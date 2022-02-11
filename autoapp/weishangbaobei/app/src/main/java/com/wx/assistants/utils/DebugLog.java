package com.wx.assistants.utils;

import android.text.TextUtils;
import android.util.Log;

public class DebugLog {
    public static final char LOG_PARAM_SIGN = '?';
    static String appTag = "WaterMark";
    static boolean sIsDebug = true;

    private DebugLog() {
    }

    public static void initDebug(String str, boolean z) {
        sIsDebug = z;
        appTag = str;
    }

    public static void setAppTag(String str) {
        appTag = str;
    }

    public static void setDebugEnable(boolean z) {
        sIsDebug = z;
    }

    public static boolean isDebug() {
        return sIsDebug;
    }

    private static String getLogInfo(StackTraceElement[] stackTraceElementArr) {
        try {
            return stackTraceElementArr[1].toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String createLog(String str, String str2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            stringBuffer.append(str2);
            stringBuffer.append("]");
            stringBuffer.append(str);
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static void e(String str) {
        if (isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void e(String str, boolean z) {
        if (z || isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void e(String str, Throwable th) {
        if (isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]), th);
        }
    }

    public static void e(String str, Throwable th, boolean z) {
        if (z || isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]), th);
        }
    }

    public static void e(String str, Object... objArr) {
        if (isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void e(Object... objArr) {
        if (isDebug()) {
            Log.e(appTag, getLogMessage((String) null, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void e(String str, Throwable th, Object... objArr) {
        if (isDebug()) {
            Log.e(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr), th);
        }
    }

    public static void i(String str) {
        if (isDebug()) {
            Log.i(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void i(String str, boolean z) {
        if (z || isDebug()) {
            Log.i(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void i(Object... objArr) {
        if (isDebug()) {
            Log.i(appTag, getLogMessage((String) null, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void i(String str, Object... objArr) {
        if (isDebug()) {
            Log.i(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void d(String str) {
        if (isDebug()) {
            Log.d(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void d(String str, boolean z) {
        if (z || isDebug()) {
            Log.d(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void d(Object... objArr) {
        if (isDebug()) {
            Log.d(appTag, getLogMessage((String) null, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void d(String str, Object... objArr) {
        if (isDebug()) {
            Log.d(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void v(String str) {
        if (isDebug()) {
            Log.v(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void v(String str, boolean z) {
        if (z || isDebug()) {
            Log.v(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void v(Object... objArr) {
        if (isDebug()) {
            Log.v(appTag, getLogMessage((String) null, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void v(String str, Object... objArr) {
        if (isDebug()) {
            Log.v(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void w(String str) {
        if (isDebug()) {
            Log.w(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void w(String str, boolean z) {
        if (z || isDebug()) {
            Log.w(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), new Object[0]));
        }
    }

    public static void w(Object... objArr) {
        if (isDebug()) {
            Log.w(appTag, getLogMessage((String) null, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    public static void w(String str, Object... objArr) {
        if (isDebug()) {
            Log.w(appTag, getLogMessage(str, getLogInfo(new Throwable().getStackTrace()), objArr));
        }
    }

    private static String getLogMessage(String str, String str2, Object... objArr) {
        if (TextUtils.isEmpty(str) && (objArr == null || objArr.length == 0)) {
            return "";
        }
        String logContent = getLogContent(str, objArr);
        if (TextUtils.isEmpty(str2)) {
            return logContent;
        }
        return createLog(logContent, str2);
    }

    private static String getLogContent(String str, Object... objArr) {
        if (TextUtils.isEmpty(str) && (objArr == null || objArr.length <= 0)) {
            return "";
        }
        StringBuilder sb = null;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            int length = objArr.length;
            while (i < length) {
                Object obj = objArr[i];
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(obj);
                sb.append(" ");
                i++;
            }
            if (sb == null) {
                return "";
            }
            return sb.toString();
        } else if (objArr == null || objArr.length <= 0) {
            return str;
        } else {
            StringBuilder sb2 = new StringBuilder();
            if (str.contains(String.valueOf(LOG_PARAM_SIGN))) {
                int i2 = 0;
                while (i < str.length()) {
                    if ('?' != str.charAt(i)) {
                        sb2.append(str.charAt(i));
                    } else if (i2 < objArr.length) {
                        sb2.append(" ");
                        sb2.append(objArr[i2]);
                        sb2.append(" ");
                        i2++;
                    } else {
                        sb2.append(str.charAt(i));
                    }
                    i++;
                }
                i = i2;
            } else {
                sb2.append(str);
            }
            while (i < objArr.length) {
                sb2.append(" ");
                sb2.append(objArr[i]);
                sb2.append(" ");
                i++;
            }
            return sb2.toString();
        }
    }
}
