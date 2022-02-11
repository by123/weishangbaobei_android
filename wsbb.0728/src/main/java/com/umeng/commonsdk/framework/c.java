package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.stub.StubApp;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

public class c {
    public static final String a = "content";
    public static final String b = "header";
    public static final String c = "exception";
    private static HandlerThread d = null;
    private static Handler e = null;
    private static b f = null;
    private static Object g = new Object();
    private static final int h = 768;
    private static final int i = 769;
    private static final int j = 770;
    private static final int k = 784;

    private c() {
    }

    public static void a() {
        if (e != null) {
            Message obtainMessage = e.obtainMessage();
            obtainMessage.what = k;
            e.sendMessage(obtainMessage);
        }
    }

    public static void a(long j2) {
        if (e == null) {
            return;
        }
        if (e.hasMessages(j)) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
            return;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
        Message obtainMessage = e.obtainMessage();
        obtainMessage.what = j;
        e.sendMessageDelayed(obtainMessage, j2);
    }

    public static void a(Context context, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        if (context == null || uMLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
            if (d == null || e == null) {
                e();
            }
            try {
                if (e != null) {
                    if (UMGlobalContext.getInstance().isMainProcess(context) && f == null) {
                        synchronized (g) {
                            UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                            f = new b(context, e);
                        }
                    }
                    Message obtainMessage = e.obtainMessage();
                    obtainMessage.what = h;
                    obtainMessage.arg1 = i2;
                    obtainMessage.obj = obj;
                    e.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    public static void a(UMSenderStateNotify uMSenderStateNotify) {
        if (f != null) {
            b.a(uMSenderStateNotify);
        }
    }

    public static boolean a(int i2) {
        synchronized (c.class) {
            try {
                if (e == null) {
                    return false;
                }
                boolean hasMessages = e.hasMessages(i2);
                return hasMessages;
            } catch (Throwable th) {
                Class<c> cls = c.class;
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Message message) {
        int i2 = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i2));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i2) + "]");
            callbackFromModuleName.workEvent(obj, i2);
        }
    }

    /* access modifiers changed from: private */
    public static void d() {
        JSONObject jSONObject;
        JSONObject buildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext != null && UMFrUtils.isOnline(appContext)) {
            long maxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
            UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
            if (callbackFromModuleName != null) {
                try {
                    JSONObject jSONObject2 = callbackFromModuleName.setupReportData(maxDataSpace);
                    if (jSONObject2 == null) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                        return;
                    }
                    jSONObject = jSONObject2;
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                    return;
                }
            } else {
                jSONObject = null;
            }
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
                JSONObject jSONObject4 = (JSONObject) jSONObject.opt("content");
                if (appContext != null && jSONObject3 != null && jSONObject4 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject3, jSONObject4)) != null) {
                    try {
                        if (buildEnvelopeWithExtHeader.has("exception")) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                        }
                    } catch (Throwable th2) {
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
                    callbackFromModuleName.removeCacheData(buildEnvelopeWithExtHeader);
                }
            }
        }
    }

    private static void e() {
        synchronized (c.class) {
            try {
                ULog.d("--->>> Dispatch: init Enter...");
                if (d == null) {
                    d = new HandlerThread("work_thread");
                    d.start();
                    if (e == null) {
                        e = new Handler(d.getLooper()) {
                            public void handleMessage(Message message) {
                                int i = message.what;
                                if (i != c.k) {
                                    switch (i) {
                                        case c.h /*768*/:
                                            c.b(message);
                                            return;
                                        case c.j /*770*/:
                                            c.d();
                                            return;
                                        default:
                                            return;
                                    }
                                } else {
                                    c.g();
                                }
                            }
                        };
                    }
                }
            } catch (Throwable th) {
                Class<c> cls = c.class;
                throw th;
            }
            ULog.d("--->>> Dispatch: init Exit...");
        }
    }

    private static void f() {
        if (d != null) {
            d = null;
        }
        if (e != null) {
            e = null;
        }
        if (f != null) {
            f = null;
        }
    }

    /* access modifiers changed from: private */
    public static void g() {
        if (f != null && d != null) {
            b.c();
            ULog.d("--->>> handleQuit: Quit dispatch thread.");
            d.quit();
            f();
        }
    }
}
