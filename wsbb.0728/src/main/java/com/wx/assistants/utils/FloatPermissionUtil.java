package com.wx.assistants.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import java.lang.reflect.Field;

public class FloatPermissionUtil {
    public static boolean checkFloatPermission(Context context) {
        boolean z;
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            try {
                Class<?> cls = Class.forName("android.content.Context");
                Field declaredField = cls.getDeclaredField("APP_OPS_SERVICE");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(cls);
                if (!(obj instanceof String)) {
                    return false;
                }
                Object invoke = cls.getMethod("getSystemService", new Class[]{String.class}).invoke(context, new Object[]{(String) obj});
                Class<?> cls2 = Class.forName("android.app.AppOpsManager");
                Field declaredField2 = cls2.getDeclaredField("MODE_ALLOWED");
                declaredField2.setAccessible(true);
                return ((Integer) cls2.getMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(invoke, new Object[]{24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == declaredField2.getInt(cls2);
            } catch (Exception e) {
                return false;
            }
        } else if (Build.VERSION.SDK_INT < 26) {
            return Settings.canDrawOverlays(context);
        } else {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            if (appOpsManager == null) {
                return false;
            }
            int checkOpNoThrow = appOpsManager.checkOpNoThrow("android:system_alert_window", Process.myUid(), context.getPackageName());
            if (checkOpNoThrow == 0) {
                z = true;
            } else if (checkOpNoThrow == 1) {
                return true;
            } else {
                z = false;
            }
            return z;
        }
    }
}
