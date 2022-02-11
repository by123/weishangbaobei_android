package com.wx.assistants.utils;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

@TargetApi(16)
public class AccessibilityOperator {
    private static AccessibilityOperator mInstance = new AccessibilityOperator();
    private AccessibilityEvent mAccessibilityEvent;
    private AccessibilityService mAccessibilityService;
    private Context mContext;

    private AccessibilityOperator() {
    }

    public static AccessibilityOperator getInstance() {
        return mInstance;
    }

    private AccessibilityNodeInfo getRootNodeInfo() {
        AccessibilityEvent accessibilityEvent = this.mAccessibilityEvent;
        if (Build.VERSION.SDK_INT < 16) {
            return accessibilityEvent.getSource();
        }
        if (this.mAccessibilityService == null) {
            return null;
        }
        AccessibilityNodeInfo rootInActiveWindow = this.mAccessibilityService.getRootInActiveWindow();
        AccessibilityLog.printLog("nodeInfo: " + rootInActiveWindow);
        return rootInActiveWindow;
    }

    private boolean performClick(List<AccessibilityNodeInfo> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            AccessibilityNodeInfo accessibilityNodeInfo = list.get(i);
            if (accessibilityNodeInfo.isEnabled()) {
                return accessibilityNodeInfo.performAction(16);
            }
        }
        return false;
    }

    private boolean performGlobalAction(int i) {
        return this.mAccessibilityService.performGlobalAction(i);
    }

    public boolean clickBackKey() {
        return performGlobalAction(1);
    }

    public boolean clickById(String str) {
        return performClick(findNodesById(str));
    }

    public boolean clickByText(String str) {
        return performClick(findNodesByText(str));
    }

    public List<AccessibilityNodeInfo> findNodesById(String str) {
        AccessibilityNodeInfo rootNodeInfo = getRootNodeInfo();
        if (rootNodeInfo == null || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        return rootNodeInfo.findAccessibilityNodeInfosByViewId(str);
    }

    public List<AccessibilityNodeInfo> findNodesByText(String str) {
        AccessibilityNodeInfo rootNodeInfo = getRootNodeInfo();
        if (rootNodeInfo != null) {
            return rootNodeInfo.findAccessibilityNodeInfosByText(str);
        }
        return null;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public boolean isServiceRunning() {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) this.mContext.getSystemService("activity")).getRunningServices(32767)) {
            String className = runningServiceInfo.service.getClassName();
            if (className.equals(this.mContext.getPackageName() + ".AccessibilitySampleService")) {
                return true;
            }
        }
        return false;
    }

    public void updateEvent(AccessibilityService accessibilityService, AccessibilityEvent accessibilityEvent) {
        if (accessibilityService != null && this.mAccessibilityService == null) {
            this.mAccessibilityService = accessibilityService;
        }
        if (accessibilityEvent != null) {
            this.mAccessibilityEvent = accessibilityEvent;
        }
    }
}
