package com.xw.repo;

import android.content.res.Resources;
import android.util.TypedValue;
import java.util.Properties;

class BubbleUtils {
    private static final String KEY_MIUI_MANE = "ro.miui.ui.version.name";
    private static Boolean miui;
    private static Properties sProperties = new Properties();

    BubbleUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[SYNTHETIC, Splitter:B:21:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0052 A[SYNTHETIC, Splitter:B:27:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean isMIUI() {
        /*
            java.lang.Boolean r0 = miui
            if (r0 == 0) goto L_0x000b
            java.lang.Boolean r0 = miui
            boolean r0 = r0.booleanValue()
            return r0
        L_0x000b:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            r2 = 0
            if (r0 >= r1) goto L_0x005b
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0033 }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0033 }
            java.io.File r3 = android.os.Environment.getRootDirectory()     // Catch:{ IOException -> 0x0033 }
            java.lang.String r4 = "build.prop"
            r1.<init>(r3, r4)     // Catch:{ IOException -> 0x0033 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0033 }
            java.util.Properties r1 = sProperties     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            r1.load(r0)     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            r0.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x002b:
            r1 = move-exception
            r2 = r0
            goto L_0x0050
        L_0x002e:
            r1 = move-exception
            r2 = r0
            goto L_0x0034
        L_0x0031:
            r1 = move-exception
            goto L_0x0050
        L_0x0033:
            r1 = move-exception
        L_0x0034:
            r1.printStackTrace()     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0041:
            java.util.Properties r0 = sProperties
            java.lang.String r1 = "ro.miui.ui.version.name"
            boolean r0 = r0.containsKey(r1)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            miui = r0
            goto L_0x008d
        L_0x0050:
            if (r2 == 0) goto L_0x005a
            r2.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005a:
            throw r1
        L_0x005b:
            r0 = 0
            java.lang.String r1 = "android.os.SystemProperties"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0087 }
            java.lang.String r3 = "get"
            r4 = 1
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x0087 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r0] = r6     // Catch:{ Exception -> 0x0087 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r3, r5)     // Catch:{ Exception -> 0x0087 }
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0087 }
            java.lang.String r5 = "ro.miui.ui.version.name"
            r3[r0] = r5     // Catch:{ Exception -> 0x0087 }
            java.lang.Object r1 = r1.invoke(r2, r3)     // Catch:{ Exception -> 0x0087 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0087 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0087 }
            r1 = r1 ^ r4
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0087 }
            miui = r1     // Catch:{ Exception -> 0x0087 }
            goto L_0x008d
        L_0x0087:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            miui = r0
        L_0x008d:
            java.lang.Boolean r0 = miui
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xw.repo.BubbleUtils.isMIUI():boolean");
    }

    static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, Resources.getSystem().getDisplayMetrics());
    }
}
