package com.tencent.mm.opensdk.openapi;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.mm.opensdk.channel.a.a;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.wxop.stat.MtaSDkException;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatReportStrategy;
import com.tencent.wxop.stat.StatService;
import com.tencent.wxop.stat.common.StatConstants;
import java.util.Properties;

final class WXApiImplV10 extends BaseWXApiImplV10 {
    /* access modifiers changed from: private */
    public static ActivityLifecycleCb activityCb;

    private static final class ActivityLifecycleCb implements Application.ActivityLifecycleCallbacks {
        private static final int DELAYED = 800;
        private static final String TAG = "MicroMsg.SDK.WXApiImplV10.ActivityLifecycleCb";
        /* access modifiers changed from: private */
        public Context context;
        private Handler handler;
        /* access modifiers changed from: private */
        public boolean isForeground;
        private Runnable onPausedRunnable;
        private Runnable onResumedRunnable;

        private ActivityLifecycleCb(Context context2) {
            this.isForeground = false;
            this.handler = new Handler(Looper.getMainLooper());
            this.onPausedRunnable = new Runnable() {
                public void run() {
                    if (WXApiImplV10.activityCb != null && ActivityLifecycleCb.this.isForeground) {
                        Log.v(ActivityLifecycleCb.TAG, "WXStat trigger onBackground");
                        StatService.trackCustomKVEvent(ActivityLifecycleCb.this.context, "onBackground_WX", (Properties) null);
                        boolean unused = ActivityLifecycleCb.this.isForeground = false;
                    }
                }
            };
            this.onResumedRunnable = new Runnable() {
                public void run() {
                    if (WXApiImplV10.activityCb != null && !ActivityLifecycleCb.this.isForeground) {
                        Log.v(ActivityLifecycleCb.TAG, "WXStat trigger onForeground");
                        StatService.trackCustomKVEvent(ActivityLifecycleCb.this.context, "onForeground_WX", (Properties) null);
                        boolean unused = ActivityLifecycleCb.this.isForeground = true;
                    }
                }
            };
            this.context = context2;
        }

        public final void detach() {
            this.handler.removeCallbacks(this.onResumedRunnable);
            this.handler.removeCallbacks(this.onPausedRunnable);
            this.context = null;
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
            Log.v(TAG, activity.getComponentName().getClassName() + "  onActivityPaused");
            this.handler.removeCallbacks(this.onResumedRunnable);
            this.handler.postDelayed(this.onPausedRunnable, 800);
        }

        public final void onActivityResumed(Activity activity) {
            Log.v(TAG, activity.getComponentName().getClassName() + "  onActivityResumed");
            this.handler.removeCallbacks(this.onPausedRunnable);
            this.handler.postDelayed(this.onResumedRunnable, 800);
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }
    }

    WXApiImplV10(Context context, String str, boolean z) {
        super(context, str, z);
    }

    private void initMta(Context context, String str) {
        String str2 = "AWXOP" + str;
        StatConfig.setAppKey(StubApp.getOrigApplicationContext(context.getApplicationContext()), str2);
        StatConfig.setEnableSmartReporting(true);
        StatConfig.setStatSendStrategy(StatReportStrategy.PERIOD);
        StatConfig.setSendPeriodMinutes(60);
        StatConfig.setInstallChannel(StubApp.getOrigApplicationContext(context.getApplicationContext()), "Wechat_Sdk");
        try {
            StatService.startStatService(StubApp.getOrigApplicationContext(context.getApplicationContext()), str2, StatConstants.VERSION);
        } catch (MtaSDkException e) {
            com.tencent.mm.opensdk.utils.Log.e("MicroMsg.SDK.WXApiImplV10", "initMta exception:" + e.getMessage());
        }
    }

    public final void detach() {
        Application application;
        if (activityCb != null && Build.VERSION.SDK_INT >= 14) {
            if (this.context instanceof Activity) {
                application = ((Activity) this.context).getApplication();
            } else {
                if (this.context instanceof Service) {
                    application = ((Service) this.context).getApplication();
                }
                activityCb.detach();
            }
            application.unregisterActivityLifecycleCallbacks(activityCb);
            activityCb.detach();
        }
        super.detach();
    }

    public final boolean registerApp(String str, long j) {
        Application application;
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            com.tencent.mm.opensdk.utils.Log.e("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        } else {
            com.tencent.mm.opensdk.utils.Log.d("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            if (activityCb == null && Build.VERSION.SDK_INT >= 14) {
                if (this.context instanceof Activity) {
                    initMta(this.context, str);
                    activityCb = new ActivityLifecycleCb(this.context);
                    application = ((Activity) this.context).getApplication();
                } else if (this.context instanceof Service) {
                    initMta(this.context, str);
                    activityCb = new ActivityLifecycleCb(this.context);
                    application = ((Service) this.context).getApplication();
                } else {
                    com.tencent.mm.opensdk.utils.Log.w("MicroMsg.SDK.WXApiImplV10", "context is not instanceof Activity or Service, disable WXStat");
                }
                application.registerActivityLifecycleCallbacks(activityCb);
            }
            com.tencent.mm.opensdk.utils.Log.d("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            com.tencent.mm.opensdk.utils.Log.d("MicroMsg.SDK.WXApiImplV10", "register app " + this.context.getPackageName());
            a.C0002a aVar = new a.C0002a();
            aVar.W = "com.tencent.mm";
            aVar.action = ConstantsAPI.ACTION_HANDLE_APP_REGISTER;
            aVar.content = "weixin://registerapp?appid=" + this.appId;
            aVar.X = j;
            return a.a(this.context, aVar);
        }
    }
}
