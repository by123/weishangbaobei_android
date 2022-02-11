package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import java.util.Formatter;
import java.util.Locale;

public class ULog {
    public static boolean DEBUG = false;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_WARN = 4;
    private static int LOG_MAXLENGTH = 2000;
    private static String TAG = "ULog";

    private ULog() {
    }

    public static void i(Locale locale, String str, Object... objArr) {
        try {
            i(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            d(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            e(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void v(Locale locale, String str, Object... objArr) {
        try {
            v(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void w(Locale locale, String str, Object... objArr) {
        try {
            w(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void i(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                i(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            i(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                d(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            d(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                e(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            e(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void v(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                v(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            v(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void w(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                w(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            w(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void i(Throwable th) {
        i(TAG, (String) null, th);
    }

    public static void v(Throwable th) {
        v(TAG, (String) null, th);
    }

    public static void w(Throwable th) {
        w(TAG, (String) null, th);
    }

    public static void d(Throwable th) {
        d(TAG, (String) null, th);
    }

    public static void e(Throwable th) {
        e(TAG, (String) null, th);
    }

    public static void i(String str, Throwable th) {
        i(TAG, str, th);
    }

    public static void v(String str, Throwable th) {
        v(TAG, str, th);
    }

    public static void w(String str, Throwable th) {
        w(TAG, str, th);
    }

    public static void d(String str, Throwable th) {
        d(TAG, str, th);
    }

    public static void e(String str, Throwable th) {
        e(TAG, str, th);
    }

    public static void v(String str) {
        v(TAG, str, (Throwable) null);
    }

    public static void d(String str) {
        d(TAG, str, (Throwable) null);
    }

    public static void i(String str) {
        i(TAG, str, (Throwable) null);
    }

    public static void w(String str) {
        w(TAG, str, (Throwable) null);
    }

    public static void e(String str) {
        e(TAG, str, (Throwable) null);
    }

    public static void v(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(1, str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(2, str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(3, str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(4, str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(5, str, str2, th);
        }
    }

    private static void print(int i, String str, String str2, Throwable th) {
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = LOG_MAXLENGTH;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 < 100) {
                    if (length <= i2) {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i4, length));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i4, length));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i4, length));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i4, length));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i4, length));
                                break;
                        }
                    } else {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i4, i2));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i4, i2));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i4, i2));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i4, i2));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i4, i2));
                                break;
                        }
                        i3++;
                        i4 = i2;
                        i2 = LOG_MAXLENGTH + i2;
                    }
                }
            }
        }
        if (th != null) {
            String stackTrace = getStackTrace(th);
            if (!TextUtils.isEmpty(stackTrace)) {
                switch (i) {
                    case 1:
                        Log.v(str, stackTrace);
                        return;
                    case 2:
                        Log.d(str, stackTrace);
                        return;
                    case 3:
                        Log.i(str, stackTrace);
                        return;
                    case 4:
                        Log.w(str, stackTrace);
                        return;
                    case 5:
                        Log.e(str, stackTrace);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A[SYNTHETIC, Splitter:B:20:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x003c A[SYNTHETIC, Splitter:B:29:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getStackTrace(java.lang.Throwable r4) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ Throwable -> 0x0039, all -> 0x002a }
            r2.<init>()     // Catch:{ Throwable -> 0x0039, all -> 0x002a }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x0028, all -> 0x0026 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0028, all -> 0x0026 }
            r4.printStackTrace(r3)     // Catch:{ Throwable -> 0x0024, all -> 0x0021 }
            r3.flush()     // Catch:{ Throwable -> 0x0024, all -> 0x0021 }
            r2.flush()     // Catch:{ Throwable -> 0x0024, all -> 0x0021 }
            java.lang.String r4 = r2.toString()     // Catch:{ Throwable -> 0x0024, all -> 0x0021 }
            r2.close()     // Catch:{ Throwable -> 0x001d }
        L_0x001d:
            r3.close()
            goto L_0x0047
        L_0x0021:
            r4 = move-exception
            r1 = r3
            goto L_0x002c
        L_0x0024:
            r1 = r3
            goto L_0x003a
        L_0x0026:
            r4 = move-exception
            goto L_0x002c
        L_0x0028:
            goto L_0x003a
        L_0x002a:
            r4 = move-exception
            r2 = r1
        L_0x002c:
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ Throwable -> 0x0032 }
            goto L_0x0033
        L_0x0032:
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            throw r4
        L_0x0039:
            r2 = r1
        L_0x003a:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ Throwable -> 0x0040 }
            goto L_0x0041
        L_0x0040:
        L_0x0041:
            if (r1 == 0) goto L_0x0046
            r1.close()
        L_0x0046:
            r4 = r0
        L_0x0047:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.ULog.getStackTrace(java.lang.Throwable):java.lang.String");
    }
}
