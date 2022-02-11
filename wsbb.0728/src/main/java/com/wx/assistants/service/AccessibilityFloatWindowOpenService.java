package com.wx.assistants.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.stub.StubApp;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import org.greenrobot.eventbus.EventBus;
import permission.rom.FloatManager;

@SuppressLint({"Registered"})
public class AccessibilityFloatWindowOpenService extends IntentService {
    private MyOpenHandler myOpenHandler = new MyOpenHandler();
    /* access modifiers changed from: private */
    public int tag = 0;

    class MyOpenHandler extends Handler {
        MyOpenHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent.handleMessage");
                Intent intent = new Intent();
                Activity currentActivity = AppManager.getAppManager().currentActivity();
                if (currentActivity != null && currentActivity.getClass() != null) {
                    LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent.start" + currentActivity.getClass().toString());
                    intent.setClass(AccessibilityFloatWindowOpenService.this, currentActivity.getClass());
                    currentActivity.startActivity(intent);
                    EventBus.getDefault().post(new AccessibilityOpenTagEvent(AccessibilityFloatWindowOpenService.this.tag));
                }
            }
        }
    }

    public AccessibilityFloatWindowOpenService() {
        super("AccessibilityFloatWindowOpenService");
    }

    public static void startWithOpen(Context context, int i) {
        try {
            LogUtils.log("WS_AccessibilityFloatWindowOpenService.startWithOpen");
            Intent intent = new Intent(context, AccessibilityFloatWindowOpenService.class);
            intent.putExtra("open_tag", i);
            context.startService(intent);
        } catch (Exception e) {
        }
    }

    public boolean isAccessibilitySettingsOn() {
        int i;
        String string;
        try {
            String str = getPackageName() + "/" + AutoService.class.getCanonicalName();
            try {
                i = Settings.Secure.getInt(StubApp.getOrigApplicationContext(getApplicationContext()).getContentResolver(), "accessibility_enabled");
                try {
                    LogUtils.log("WS_BABY_A_" + str + ListUtils.DEFAULT_JOIN_SEPARATOR + i);
                } catch (Settings.SettingNotFoundException e) {
                }
            } catch (Settings.SettingNotFoundException e2) {
                i = 0;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
            if (i == 1 && (string = Settings.Secure.getString(StubApp.getOrigApplicationContext(getApplicationContext()).getContentResolver(), "enabled_accessibility_services")) != null) {
                LogUtils.log("WS_BABY_B_" + str + ListUtils.DEFAULT_JOIN_SEPARATOR + i + ListUtils.DEFAULT_JOIN_SEPARATOR + string);
                simpleStringSplitter.setString(string);
                while (simpleStringSplitter.hasNext()) {
                    if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e3) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        int i = 0;
        if (intent != null) {
            LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent");
            this.tag = intent.getIntExtra("open_tag", -1);
            if (this.tag == 0) {
                while (i < 600) {
                    LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent." + i);
                    if (isAccessibilitySettingsOn()) {
                        LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent.open");
                        try {
                            this.myOpenHandler.sendEmptyMessage(0);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            i = 600;
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    i++;
                }
            } else if (this.tag == 1) {
                while (i < 600) {
                    boolean checkPermission = FloatManager.getInstance().checkPermission(this);
                    LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent." + i);
                    if (checkPermission) {
                        LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent.open");
                        try {
                            this.myOpenHandler.sendEmptyMessage(0);
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            i = 600;
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e4) {
                        e4.printStackTrace();
                    }
                    i++;
                }
            }
        }
    }
}
