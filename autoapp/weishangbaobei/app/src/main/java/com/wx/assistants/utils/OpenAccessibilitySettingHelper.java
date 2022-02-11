package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Intent;
import com.wx.assistants.activity.AccessibilityFloatWindowOpenHelperActivity;
import com.wx.assistants.activity.AccessibilityOpenHelperActivity;

public class OpenAccessibilitySettingHelper {
    public static void jumpToSettingPage(Activity activity) {
        try {
            LogUtils.log("WS_BABY_XXXXXXXXXX1");
            activity.startActivity(new Intent(activity, AccessibilityOpenHelperActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jumpToFloatWindowSettingPage(Activity activity) {
        try {
            LogUtils.log("WS_BABY_XXXXXXXXXX2");
            activity.startActivity(new Intent(activity, AccessibilityFloatWindowOpenHelperActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
