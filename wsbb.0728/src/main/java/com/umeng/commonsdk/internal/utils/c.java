package com.umeng.commonsdk.internal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.meiqia.core.bean.MQInquireForm;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONObject;

public class c {
    private static final String a = "BatteryUtils";
    private static boolean b;
    /* access modifiers changed from: private */
    public static Context c;
    private BroadcastReceiver d;

    private static class a {
        /* access modifiers changed from: private */
        public static final c a = new c();

        private a() {
        }
    }

    private c() {
        this.d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int i = 0;
                try {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("le", intent.getIntExtra("level", 0));
                        } catch (Exception e) {
                        }
                        try {
                            jSONObject.put("vol", intent.getIntExtra("voltage", 0));
                        } catch (Exception e2) {
                        }
                        try {
                            jSONObject.put("temp", intent.getIntExtra("temperature", 0));
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception e3) {
                        }
                        int i2 = -1;
                        switch (intent.getIntExtra(MQInquireForm.KEY_STATUS, 0)) {
                            case 2:
                                i2 = 1;
                                break;
                            case 4:
                                i2 = 0;
                                break;
                            case 5:
                                i2 = 2;
                                break;
                        }
                        try {
                            jSONObject.put("st", i2);
                        } catch (Exception e4) {
                        }
                        switch (intent.getIntExtra("plugged", 0)) {
                            case 1:
                                i = 1;
                                break;
                            case 2:
                                i = 2;
                                break;
                        }
                        try {
                            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT, i);
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception e5) {
                        }
                        ULog.i(c.a, jSONObject.toString());
                        UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.g, b.a(c.c).a(), jSONObject.toString());
                        c.this.c();
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(c.c, th);
                }
            }
        };
    }

    public static c a(Context context) {
        if (c == null && context != null) {
            c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        return a.a;
    }

    public boolean a() {
        boolean z;
        synchronized (this) {
            z = b;
        }
        return z;
    }

    public void b() {
        synchronized (this) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
                c.registerReceiver(this.d, intentFilter);
                b = true;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(c, th);
            }
        }
        return;
    }

    public void c() {
        synchronized (this) {
            try {
                c.unregisterReceiver(this.d);
                b = false;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(c, th);
            }
        }
        return;
    }
}
