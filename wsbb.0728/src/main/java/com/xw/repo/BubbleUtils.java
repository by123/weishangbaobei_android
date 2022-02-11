package com.xw.repo;

import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class BubbleUtils {
    private static final String KEY_MIUI_MANE = "ro.miui.ui.version.name";
    private static Boolean miui;
    private static Properties sProperties = new Properties();

    BubbleUtils() {
    }

    static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, Resources.getSystem().getDisplayMetrics());
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044 A[SYNTHETIC, Splitter:B:16:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004f A[SYNTHETIC, Splitter:B:24:0x004f] */
    static boolean isMIUI() {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (miui != null) {
            return miui.booleanValue();
        }
        if (Build.VERSION.SDK_INT < 26) {
            try {
                fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                try {
                    sProperties.load(fileInputStream);
                } catch (IOException e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                        return miui.booleanValue();
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                    }
                    throw th;
                }
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e = e3;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                return miui.booleanValue();
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
            miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
        } else {
            try {
                miui = Boolean.valueOf(!TextUtils.isEmpty((String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{KEY_MIUI_MANE})));
            } catch (Exception e5) {
                miui = false;
            }
        }
        return miui.booleanValue();
    }

    static int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, Resources.getSystem().getDisplayMetrics());
    }
}
