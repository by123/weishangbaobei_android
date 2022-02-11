package com.umeng.socialize.utils;

import android.content.Context;
import android.widget.Toast;
import com.umeng.socialize.Config;

public class Log {
    public static boolean LOG = true;
    private static boolean LOGNET = false;
    public static final String LOGTAG = "6.4.5umeng_tool----";
    public static final String NETTAG = "6.4.5net_test----";
    public static final String TAG = "umengsocial";

    public static void i(String str, String str2) {
        if (LOG && Config.DEBUG) {
            android.util.Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Exception exc) {
        if (LOG && Config.DEBUG) {
            android.util.Log.i(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void e(String str, String str2) {
        if (LOG && Config.DEBUG) {
            android.util.Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Exception exc) {
        android.util.Log.e(str, exc.toString() + ":  [" + str2 + "]");
        for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
            android.util.Log.e(str, "        at\t " + stackTraceElement.toString());
        }
    }

    public static void d(String str, String str2) {
        if (LOG && Config.DEBUG) {
            android.util.Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Exception exc) {
        if (LOG && Config.DEBUG) {
            android.util.Log.d(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void v(String str, String str2) {
        if (LOG && Config.DEBUG) {
            android.util.Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Exception exc) {
        if (LOG && Config.DEBUG) {
            android.util.Log.v(str, exc.toString() + ":  [" + str2 + "]");
        }
    }

    public static void w(String str, String str2) {
        if (LOG && Config.DEBUG) {
            android.util.Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Exception exc) {
        if (LOG && Config.DEBUG) {
            android.util.Log.w(str, exc.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
                android.util.Log.w(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }

    public static void i(String str) {
        if (LOG && Config.DEBUG) {
            android.util.Log.i(TAG, str);
        }
    }

    public static void e(String str) {
        if (LOG && Config.DEBUG) {
            android.util.Log.e(TAG, str);
        }
    }

    public static void um(String str) {
        e(LOGTAG + str);
    }

    public static void umd(String str) {
        d(LOGTAG + str);
    }

    public static void y(String str) {
        if (LOG) {
            android.util.Log.e(TAG, str);
        }
    }

    public static void d(String str) {
        if (LOG && Config.DEBUG) {
            android.util.Log.d(TAG, str);
        }
    }

    public static void v(String str) {
        if (LOG && Config.DEBUG) {
            android.util.Log.v(TAG, str);
        }
    }

    public static void w(String str) {
        if (LOG && Config.DEBUG) {
            android.util.Log.w(TAG, str);
        }
    }

    public static void net(String str) {
        if (LOGNET) {
            android.util.Log.d(NETTAG, str);
        }
    }

    public static void toast(Context context, String str) {
        if (LOG && Config.DEBUG) {
            Toast.makeText(context, str, 1).show();
        }
    }

    public static void url(String str, String str2) {
        if (Config.DEBUG) {
            d("6.4.5umeng_tool--------------------问题---------------");
            d(LOGTAG + str);
            d("6.4.5umeng_tool--------------------解决方案------------");
            d("6.4.5umeng_tool----请访问：" + str2);
            d("6.4.5umeng_tool----|-------------------------------|");
        }
    }

    public static void url(String str, String str2, String str3) {
        if (Config.DEBUG) {
            d("6.4.5umeng_tool--------------------" + str + "---------------");
            StringBuilder sb = new StringBuilder();
            sb.append(LOGTAG);
            sb.append(str2);
            d(sb.toString());
            d("6.4.5umeng_tool--------------------解决方案------------");
            d("6.4.5umeng_tool----请访问：" + str3);
            d("6.4.5umeng_tool----|-------------------------------|");
        }
    }
}
