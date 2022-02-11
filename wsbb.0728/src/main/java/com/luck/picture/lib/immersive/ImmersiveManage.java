package com.luck.picture.lib.immersive;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class ImmersiveManage {
    public static void immersiveAboveAPI19(AppCompatActivity appCompatActivity, int i, int i2, boolean z) {
        immersiveAboveAPI23(appCompatActivity, false, false, i, i2, z);
    }

    public static void immersiveAboveAPI23(Activity activity, int i, int i2, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            immersiveAboveAPI23(activity, false, false, i, i2, z);
        }
    }

    public static void immersiveAboveAPI23(Activity activity, boolean z, boolean z2, int i, int i2, boolean z3) {
        boolean z4 = true;
        try {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                window.setFlags(67108864, 67108864);
            } else if (Build.VERSION.SDK_INT >= 21) {
                if (z && z2) {
                    window.clearFlags(201326592);
                    if (i != 0) {
                        z4 = false;
                    }
                    LightStatusBarUtils.setLightStatusBar(activity, z, z2, z4, z3);
                    window.addFlags(Integer.MIN_VALUE);
                } else if (!z && !z2) {
                    window.requestFeature(1);
                    window.clearFlags(201326592);
                    if (i != 0) {
                        z4 = false;
                    }
                    LightStatusBarUtils.setLightStatusBar(activity, z, z2, z4, z3);
                    window.addFlags(Integer.MIN_VALUE);
                } else if (!z && z2) {
                    window.requestFeature(1);
                    window.clearFlags(201326592);
                    if (i != 0) {
                        z4 = false;
                    }
                    LightStatusBarUtils.setLightStatusBar(activity, z, z2, z4, z3);
                    window.addFlags(Integer.MIN_VALUE);
                } else {
                    return;
                }
                window.setStatusBarColor(i);
                window.setNavigationBarColor(i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
