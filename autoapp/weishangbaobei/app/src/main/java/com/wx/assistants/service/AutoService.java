package com.wx.assistants.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.LogUtils;

public class AutoService extends AccessibilityService {
    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        super.onServiceConnected();
        LogUtils.log("WS_BABY_ACCESSIBILITY.onServiceConnected");
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (!MyApplication.isValidationService) {
            MyApplication.isValidationService = true;
        }
    }

    public void onInterrupt() {
        LogUtils.log("WS_BABY_ACCESSIBILITY.onInterrupt");
        MyApplication.instance.setAutoService((AutoService) null);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        startForeground(0, new Notification());
        LogUtils.log("WS_BABY_ACCESSIBILITY.onStartCommand");
        MyApplication.instance.setAutoService(this);
        try {
            AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
            accessibilityServiceInfo.eventTypes = -1;
            accessibilityServiceInfo.feedbackType = 16;
            accessibilityServiceInfo.flags = 81;
            accessibilityServiceInfo.packageNames = new String[]{"com.tencent.wework", "com.tencent.mm", "dkplugin.xjg.tkz", "com.ptg.ratelBox_sub1", "com.ptg.ratelBox_sub2", "com.ptg.ratelBox_sub3", "com.excelliance.dualaid", "com.xunrui.duokai_box", "com.kuihua.wxfs1.ttrom.multplugin31", MyApplication.WS, "marvelous.assist", "com.guding.vssq"};
            accessibilityServiceInfo.notificationTimeout = 1000;
            setServiceInfo(accessibilityServiceInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        LogUtils.log("WS_BABY_AUTO_SERVICE_onDestroy");
        super.onDestroy();
    }
}
