package com.luck.picture.lib.immersive;

import android.os.Build;
import android.text.TextUtils;

public class RomUtils {

    public class AvailableRomType {
        public static final int ANDROID_NATIVE = 3;
        public static final int FLYME = 2;
        public static final int MIUI = 1;
        public static final int NA = 4;

        public AvailableRomType() {
        }
    }

    public static boolean isLightStatusBarAvailable() {
        return isMIUIV6OrAbove() || isFlymeV4OrAbove() || isAndroidMOrAbove();
    }

    public static int getLightStatusBarAvailableRomType() {
        if (isMIUIV6OrAbove()) {
            return 1;
        }
        if (isFlymeV4OrAbove()) {
            return 2;
        }
        return isAndroid5OrAbove() ? 3 : 4;
    }

    private static boolean isFlymeV4OrAbove() {
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && str.contains("Flyme")) {
            for (String matches : str.split(" ")) {
                if (matches.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isMIUIV6OrAbove() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.code");
        if (TextUtils.isEmpty(systemProperty)) {
            return false;
        }
        try {
            return Integer.parseInt(systemProperty) >= 4;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getMIUIVersionCode() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.code");
        if (TextUtils.isEmpty(systemProperty)) {
            return 0;
        }
        try {
            return Integer.parseInt(systemProperty);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static boolean isAndroidMOrAbove() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private static boolean isAndroid5OrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static int getMiuiVersion() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.name");
        if (systemProperty == null) {
            return -1;
        }
        try {
            return Integer.parseInt(systemProperty.substring(1));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d A[SYNTHETIC, Splitter:B:14:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A[SYNTHETIC, Splitter:B:21:0x0044] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSystemProperty(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            r2.<init>()     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            r2.append(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.lang.Process r4 = r1.exec(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch:{ IOException -> 0x0041, all -> 0x003a }
            java.lang.String r4 = r1.readLine()     // Catch:{ IOException -> 0x0038, all -> 0x0035 }
            r1.close()     // Catch:{ IOException -> 0x0038, all -> 0x0035 }
            r1.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r4
        L_0x0035:
            r4 = move-exception
            r0 = r1
            goto L_0x003b
        L_0x0038:
            goto L_0x0042
        L_0x003a:
            r4 = move-exception
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            r0.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            throw r4
        L_0x0041:
            r1 = r0
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.immersive.RomUtils.getSystemProperty(java.lang.String):java.lang.String");
    }
}
