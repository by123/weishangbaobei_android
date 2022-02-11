package com.wx.assistants.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import permission.rom.FloatManager;

@SuppressLint({"Registered"})
public class AccessibilityFloatWindowOpenService extends IntentService {
    private MyOpenHandler myOpenHandler = new MyOpenHandler();
    /* access modifiers changed from: private */
    public int tag = 0;

    public AccessibilityFloatWindowOpenService() {
        super("AccessibilityFloatWindowOpenService");
    }

    public static void startWithOpen(Context context, int i) {
        try {
            LogUtils.log("WS_AccessibilityFloatWindowOpenService.startWithOpen");
            Intent intent = new Intent(context, AccessibilityFloatWindowOpenService.class);
            intent.putExtra("open_tag", i);
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent");
            this.tag = intent.getIntExtra("open_tag", -1);
            if (this.tag == 0) {
                int i = 0;
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
                int i2 = 0;
                while (i2 < 600) {
                    boolean checkPermission = FloatManager.getInstance().checkPermission(this);
                    LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent." + i2);
                    if (checkPermission) {
                        LogUtils.log("WS_AccessibilityFloatWindowOpenService.onHandleIntent.open");
                        try {
                            this.myOpenHandler.sendEmptyMessage(0);
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            i2 = 600;
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e4) {
                        e4.printStackTrace();
                    }
                    i2++;
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(4:3|4|5|6)|9|10|(3:14|(3:17|(2:22|19)|15)|23)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009a A[Catch:{ Exception -> 0x00a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isAccessibilitySettingsOn() {
        /*
            r8 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a5 }
            r1.<init>()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = r8.getPackageName()     // Catch:{ Exception -> 0x00a5 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<com.wx.assistants.service.AutoService> r2 = com.wx.assistants.service.AutoService.class
            java.lang.String r2 = r2.getCanonicalName()     // Catch:{ Exception -> 0x00a5 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00a5 }
            android.content.Context r2 = r8.getApplicationContext()     // Catch:{ SettingNotFoundException -> 0x004e }
            android.content.Context r2 = com.stub.StubApp.getOrigApplicationContext(r2)     // Catch:{ SettingNotFoundException -> 0x004e }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x004e }
            java.lang.String r3 = "accessibility_enabled"
            int r2 = android.provider.Settings.Secure.getInt(r2, r3)     // Catch:{ SettingNotFoundException -> 0x004e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.<init>()     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r4 = "WS_BABY_A_"
            r3.append(r4)     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.append(r1)     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r4 = ","
            r3.append(r4)     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.append(r2)     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r3 = r3.toString()     // Catch:{ SettingNotFoundException -> 0x004f }
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ SettingNotFoundException -> 0x004f }
            goto L_0x004f
        L_0x004e:
            r2 = 0
        L_0x004f:
            android.text.TextUtils$SimpleStringSplitter r3 = new android.text.TextUtils$SimpleStringSplitter     // Catch:{ Exception -> 0x00a5 }
            r4 = 58
            r3.<init>(r4)     // Catch:{ Exception -> 0x00a5 }
            r4 = 1
            if (r2 != r4) goto L_0x00a5
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ Exception -> 0x00a5 }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ Exception -> 0x00a5 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r6 = "enabled_accessibility_services"
            java.lang.String r5 = android.provider.Settings.Secure.getString(r5, r6)     // Catch:{ Exception -> 0x00a5 }
            if (r5 == 0) goto L_0x00a5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a5 }
            r6.<init>()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r7 = "WS_BABY_B_"
            r6.append(r7)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r1)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r7 = ","
            r6.append(r7)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = ","
            r6.append(r2)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r5)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = r6.toString()     // Catch:{ Exception -> 0x00a5 }
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x00a5 }
            r3.setString(r5)     // Catch:{ Exception -> 0x00a5 }
        L_0x0094:
            boolean r2 = r3.hasNext()     // Catch:{ Exception -> 0x00a5 }
            if (r2 == 0) goto L_0x00a5
            java.lang.String r2 = r3.next()     // Catch:{ Exception -> 0x00a5 }
            boolean r2 = r2.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x00a5 }
            if (r2 == 0) goto L_0x0094
            return r4
        L_0x00a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service.AccessibilityFloatWindowOpenService.isAccessibilitySettingsOn():boolean");
    }

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
}
