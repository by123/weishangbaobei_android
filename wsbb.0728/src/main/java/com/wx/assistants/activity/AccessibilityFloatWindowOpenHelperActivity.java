package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import permission.rom.FloatManager;

public class AccessibilityFloatWindowOpenHelperActivity extends BaseActivity {
    static {
        StubApp.interface11(8213);
    }

    private void finishCurrentActivity() {
        finish();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AccessibilityFloatWindowOpenHelperActivity] */
    private void jumpActivities() {
        try {
            FloatManager.getInstance().applyPermission(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishCurrentActivity();
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
