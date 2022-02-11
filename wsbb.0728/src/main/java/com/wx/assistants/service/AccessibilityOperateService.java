package com.wx.assistants.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

@SuppressLint({"Registered"})
public class AccessibilityOperateService extends IntentService {
    public AccessibilityOperateService() {
        super("AccessibilityOperateService");
    }

    public static void startWithEvent(Context context, AccessibilityEvent accessibilityEvent) {
        context.startService(new Intent(context, AccessibilityOperateService.class));
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
    }
}
