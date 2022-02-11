package com.umeng.qq.tencent;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.tencent.bugly.Bugly;
import com.umeng.socialize.utils.DeviceConfig;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.json.JSONObject;

public class l {
    public static Bundle a(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length == 2) {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
            return bundle;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (c(context)) {
            try {
                packageManager.getPackageInfo("com.tencent.minihd.qq", 0);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        try {
            return s.a(packageManager.getPackageInfo("com.tencent.mobileqq", 0).versionName, "4.1") >= 0;
        } catch (PackageManager.NameNotFoundException e2) {
            try {
                packageManager.getPackageInfo("com.tencent.tim", 0);
                return true;
            } catch (PackageManager.NameNotFoundException e3) {
                return false;
            }
        }
    }

    public static boolean a(Context context, String str) {
        boolean z = !c(context) || DeviceConfig.getAppVersion("com.tencent.minihd.qq", context) == null;
        if (z && DeviceConfig.getAppVersion("com.tencent.tim", context) != null) {
            z = false;
        }
        return z ? s.a(context, str) < 0 : z;
    }

    public static final String b(Context context) {
        CharSequence applicationLabel;
        if (context == null || (applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo())) == null) {
            return null;
        }
        return applicationLabel.toString();
    }

    public static JSONObject b(String str) {
        if (str.equals(Bugly.SDK_IS_DEV)) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = "{online:" + str.charAt(str.length() - 2) + "}";
        }
        return new JSONObject(str);
    }

    public static boolean c(Context context) {
        double d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            d = Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) f, 2.0d));
        } catch (Throwable th) {
            d = 0.0d;
        }
        return d > 6.5d;
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean d(String str) {
        return str != null && new File(str).exists();
    }

    public static byte[] e(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
