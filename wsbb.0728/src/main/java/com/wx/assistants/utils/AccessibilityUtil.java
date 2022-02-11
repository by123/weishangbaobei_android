package com.wx.assistants.utils;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;

public class AccessibilityUtil {
    private static final String ACCESSIBILITY_SERVICE_PATH = AutoService.class.getCanonicalName();

    public static Intent getAccessibilitySettingPageIntent(Context context) {
        return new Intent("android.settings.ACCESSIBILITY_SETTINGS");
    }

    public static boolean isAccessibilitySettingsOn(Context context) {
        int i;
        if (context == null) {
            return false;
        }
        try {
            i = Settings.Secure.getInt(StubApp.getOrigApplicationContext(context.getApplicationContext()).getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        try {
            String str = context.getPackageName() + "/" + ACCESSIBILITY_SERVICE_PATH;
            AccessibilityLog.printLog("serviceStr: " + str);
            if (i != 1) {
                return false;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
            String string = Settings.Secure.getString(StubApp.getOrigApplicationContext(context.getApplicationContext()).getContentResolver(), "enabled_accessibility_services");
            if (string == null) {
                return false;
            }
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext() && MyApplication.enforceable) {
                if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            return false;
        }
    }
}
