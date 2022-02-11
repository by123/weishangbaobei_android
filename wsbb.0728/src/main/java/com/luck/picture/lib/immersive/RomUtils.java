package com.luck.picture.lib.immersive;

import android.os.Build;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtils {

    public class AvailableRomType {
        public static final int ANDROID_NATIVE = 3;
        public static final int FLYME = 2;
        public static final int MIUI = 1;
        public static final int NA = 4;

        public AvailableRomType() {
        }
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

    public static int getMIUIVersionCode() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.code");
        if (!TextUtils.isEmpty(systemProperty)) {
            try {
                return Integer.parseInt(systemProperty);
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static int getMiuiVersion() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.name");
        if (systemProperty != null) {
            try {
                return Integer.parseInt(systemProperty.substring(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039 A[SYNTHETIC, Splitter:B:10:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042 A[SYNTHETIC, Splitter:B:16:0x0042] */
    public static String getSystemProperty(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            try {
                String readLine = bufferedReader2.readLine();
                bufferedReader2.close();
                try {
                    bufferedReader2.close();
                    return readLine;
                } catch (IOException e) {
                    return readLine;
                }
            } catch (IOException e2) {
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader2 != null) {
                }
                throw th;
            }
        } catch (IOException e3) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
    }

    private static boolean isAndroid5OrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private static boolean isAndroidMOrAbove() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private static boolean isFlymeV4OrAbove() {
        String str = Build.DISPLAY;
        if (TextUtils.isEmpty(str) || !str.contains("Flyme")) {
            return false;
        }
        for (String matches : str.split(" ")) {
            if (matches.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLightStatusBarAvailable() {
        return isMIUIV6OrAbove() || isFlymeV4OrAbove() || isAndroidMOrAbove();
    }

    private static boolean isMIUIV6OrAbove() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.code");
        if (!TextUtils.isEmpty(systemProperty)) {
            try {
                if (Integer.parseInt(systemProperty) >= 4) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }
}
