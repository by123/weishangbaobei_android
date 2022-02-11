package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;

public class MLog {
    public static boolean DEBUG = false;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_WARN = 4;
    private static int LOG_MAXLENGTH = 2000;
    private static String TAG = "MobclickAgent";

    private MLog() {
    }

    public static void d(String str) {
        d(TAG, str, (Throwable) null);
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(2, str, str2, th);
        }
    }

    public static void d(String str, Throwable th) {
        d(TAG, str, th);
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

    public static void d(Throwable th) {
        d(TAG, (String) null, th);
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            d(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str) {
        e(TAG, str, (Throwable) null);
    }

    public static void e(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(5, str, str2, th);
        }
    }

    public static void e(String str, Throwable th) {
        e(TAG, str, th);
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

    public static void e(Throwable th) {
        e(TAG, (String) null, th);
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            e(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[SYNTHETIC, Splitter:B:13:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0032 A[SYNTHETIC, Splitter:B:21:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0037  */
    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        StringWriter stringWriter2;
        try {
            stringWriter2 = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter2);
            } catch (Throwable th2) {
                th = th2;
                printWriter = null;
                if (stringWriter2 != null) {
                }
                if (printWriter != null) {
                }
                throw th;
            }
            try {
                th.printStackTrace(printWriter);
                printWriter.flush();
                stringWriter2.flush();
                String stringWriter3 = stringWriter2.toString();
                try {
                    stringWriter2.close();
                } catch (Throwable th3) {
                }
                printWriter.close();
                return stringWriter3;
            } catch (Throwable th4) {
                th = th4;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Throwable th5) {
                    }
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            printWriter = null;
            stringWriter2 = null;
            if (stringWriter2 != null) {
            }
            if (printWriter != null) {
            }
            throw th;
        }
    }

    public static void i(String str) {
        i(TAG, str, (Throwable) null);
    }

    public static void i(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(3, str, str2, th);
        }
    }

    public static void i(String str, Throwable th) {
        i(TAG, str, th);
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

    public static void i(Throwable th) {
        i(TAG, (String) null, th);
    }

    public static void i(Locale locale, String str, Object... objArr) {
        try {
            i(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    private static void print(int i, String str, String str2, Throwable th) {
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = LOG_MAXLENGTH;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 < 100) {
                    if (length <= i2) {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i3, length));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i3, length));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i3, length));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i3, length));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i3, length));
                                break;
                        }
                    } else {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i3, i2));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i3, i2));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i3, i2));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i3, i2));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i3, i2));
                                break;
                        }
                        i3 = i2;
                        i4++;
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

    public static void v(String str) {
        v(TAG, str, (Throwable) null);
    }

    public static void v(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(1, str, str2, th);
        }
    }

    public static void v(String str, Throwable th) {
        v(TAG, str, th);
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

    public static void v(Throwable th) {
        v(TAG, (String) null, th);
    }

    public static void v(Locale locale, String str, Object... objArr) {
        try {
            v(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void w(String str) {
        w(TAG, str, (Throwable) null);
    }

    public static void w(String str, String str2, Throwable th) {
        if (DEBUG) {
            print(4, str, str2, th);
        }
    }

    public static void w(String str, Throwable th) {
        w(TAG, str, th);
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

    public static void w(Throwable th) {
        w(TAG, (String) null, th);
    }

    public static void w(Locale locale, String str, Object... objArr) {
        try {
            w(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }
}
