package com.luck.picture.lib.immersive;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;

public class LightStatusBarUtils {
    private static final int VERSION_7 = 7;

    private static void initStatusBarStyle(Activity activity, boolean z, boolean z2) {
        if (Build.VERSION.SDK_INT < 16) {
            return;
        }
        if (z && z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(256);
        } else if (!z && !z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (!z && z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        }
    }

    @TargetApi(11)
    private static void setAndroidNativeLightStatusBar(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        if (z3) {
            try {
                Window window = activity.getWindow();
                if (Build.VERSION.SDK_INT < 21) {
                    return;
                }
                if (!z || !z2) {
                    if (!z && !z2) {
                        if (z4) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                window.getDecorView().setSystemUiVisibility(9472);
                                return;
                            }
                        }
                        window.getDecorView().setSystemUiVisibility(1280);
                    } else if (!z && z2) {
                        if (!z4 || Build.VERSION.SDK_INT < 23) {
                            window.getDecorView().setSystemUiVisibility(1280);
                        } else {
                            window.getDecorView().setSystemUiVisibility(9472);
                        }
                    }
                } else if (!z4 || Build.VERSION.SDK_INT < 23) {
                    window.getDecorView().setSystemUiVisibility(256);
                } else {
                    window.getDecorView().setSystemUiVisibility(8448);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            View decorView = activity.getWindow().getDecorView();
            if (!z4 || Build.VERSION.SDK_INT < 23) {
                decorView.setSystemUiVisibility(0);
            } else {
                decorView.setSystemUiVisibility(8192);
            }
        }
    }

    private static boolean setFlymeLightStatusBar(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        if (activity != null) {
            initStatusBarStyle(activity, z, z2);
            try {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt((Object) null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z4 ? i | i2 : (i ^ -1) & i2);
                activity.getWindow().setAttributes(attributes);
                return true;
            } catch (Exception e) {
                setAndroidNativeLightStatusBar(activity, z, z2, z3, z4);
            }
        }
        return false;
    }

    public static void setLightStatusBar(Activity activity, boolean z) {
        setLightStatusBar(activity, false, false, false, z);
    }

    public static void setLightStatusBar(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        switch (RomUtils.getLightStatusBarAvailableRomType()) {
            case 1:
                if (RomUtils.getMIUIVersionCode() >= 7) {
                    setAndroidNativeLightStatusBar(activity, z, z2, z3, z4);
                    return;
                } else {
                    setMIUILightStatusBar(activity, z, z2, z3, z4);
                    return;
                }
            case 2:
                setFlymeLightStatusBar(activity, z, z2, z3, z4);
                return;
            case 3:
                setAndroidNativeLightStatusBar(activity, z, z2, z3, z4);
                return;
            default:
                return;
        }
    }

    private static boolean setMIUILightStatusBar(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        initStatusBarStyle(activity, z, z2);
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(activity.getWindow(), new Object[]{Integer.valueOf(z4 ? i : 0), Integer.valueOf(i)});
            return true;
        } catch (Exception e) {
            setAndroidNativeLightStatusBar(activity, z, z2, z3, z4);
            return false;
        }
    }
}
