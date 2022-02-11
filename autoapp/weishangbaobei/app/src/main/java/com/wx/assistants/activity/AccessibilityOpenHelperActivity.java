package com.wx.assistants.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.utils.AccessibilityUtil;
import com.wx.assistants.utils.AppManager;
import java.util.Timer;
import java.util.TimerTask;

public class AccessibilityOpenHelperActivity extends BaseActivity {
    private static final String ACTION = "action";
    private static final String ACTION_FINISH_SELF = "action_finis_self";
    protected static Handler mHandle = new Handler();
    protected static Runnable tipToastDelayRunnable;
    /* access modifiers changed from: private */
    public int TIMEOUT_MAX_INTERVAL = 120;
    private long TIMER_CHECK_INTERVAL = 1000;
    private boolean isFirstCome = true;
    /* access modifiers changed from: private */
    public int mTimeoutCounter = 0;
    private Timer timer;
    private TimerTask timerTask;

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static /* synthetic */ int access$108(AccessibilityOpenHelperActivity accessibilityOpenHelperActivity) {
        int i = accessibilityOpenHelperActivity.mTimeoutCounter;
        accessibilityOpenHelperActivity.mTimeoutCounter = i + 1;
        return i;
    }

    static {
        StubApp.interface11(8216);
    }

    private static void removeDelayedToastTask() {
        if (mHandle != null && tipToastDelayRunnable != null) {
            mHandle.removeCallbacks(tipToastDelayRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishCurrentActivity();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getExtras() != null && ACTION_FINISH_SELF.equals(intent.getStringExtra("action"))) {
            finishCurrentActivity();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.isFirstCome) {
            removeDelayedToastTask();
            finishCurrentActivity();
        } else {
            jumpActivities();
            startCheckAccessibilityOpen();
        }
        this.isFirstCome = false;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AccessibilityOpenHelperActivity] */
    private void jumpActivities() {
        try {
            startActivity(AccessibilityUtil.getAccessibilitySettingPageIntent(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        freeTimeTask();
        super.onDestroy();
    }

    private void finishCurrentActivity() {
        freeTimeTask();
        finish();
    }

    private void startCheckAccessibilityOpen() {
        freeTimeTask();
        initTimeTask();
        this.timer.schedule(this.timerTask, 0, this.TIMER_CHECK_INTERVAL);
    }

    private void initTimeTask() {
        this.timer = new Timer();
        this.mTimeoutCounter = 0;
        this.timerTask = new TimerTask() {
            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.AccessibilityOpenHelperActivity] */
            public void run() {
                if (AccessibilityUtil.isAccessibilitySettingsOn(AccessibilityOpenHelperActivity.this)) {
                    AccessibilityOpenHelperActivity.this.freeTimeTask();
                    Looper.prepare();
                    try {
                        AccessibilityOpenHelperActivity.this.runOnUiThread(new Runnable() {
                            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.AccessibilityOpenHelperActivity] */
                            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.wx.assistants.activity.AccessibilityOpenHelperActivity] */
                            public void run() {
                                Toast.makeText(AccessibilityOpenHelperActivity.this, "辅助功能开启成功", 0).show();
                                AccessibilityOpenHelperActivity.this.finish();
                                Intent intent = new Intent();
                                Activity currentActivity = AppManager.getAppManager().currentActivity();
                                if (currentActivity != null && currentActivity.getClass() != null) {
                                    intent.setClass(AccessibilityOpenHelperActivity.this, currentActivity.getClass());
                                    AccessibilityOpenHelperActivity.this.startActivity(intent);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Looper.loop();
                }
                AccessibilityOpenHelperActivity.access$108(AccessibilityOpenHelperActivity.this);
                if (AccessibilityOpenHelperActivity.this.mTimeoutCounter > AccessibilityOpenHelperActivity.this.TIMEOUT_MAX_INTERVAL) {
                    AccessibilityOpenHelperActivity.this.freeTimeTask();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void freeTimeTask() {
        if (this.timerTask != null) {
            this.timerTask.cancel();
            this.timerTask = null;
        }
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    private String getRunningActivityName() {
        return ((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName();
    }
}
