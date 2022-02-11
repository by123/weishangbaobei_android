package com.meiqia.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.meiqia.core.a.e;
import com.meiqia.core.a.f;
import com.meiqia.core.a.i;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQClient;
import com.meiqia.core.bean.MQClientEvent;
import com.meiqia.core.bean.MQConversation;
import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.AppLifecycleListener;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnClientOnlineCallback;
import com.meiqia.core.callback.OnClientPositionInQueueCallback;
import com.meiqia.core.callback.OnEndConversationCallback;
import com.meiqia.core.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.core.callback.OnFailureCallBack;
import com.meiqia.core.callback.OnGetMQClientIdCallBackOn;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.core.callback.OnMessageSendCallback;
import com.meiqia.core.callback.OnProgressCallback;
import com.meiqia.core.callback.OnRegisterDeviceTokenCallback;
import com.meiqia.core.callback.OnTicketCategoriesCallback;
import com.meiqia.core.callback.SimpleCallback;
import com.meiqia.core.callback.SuccessCallback;
import com.meiqia.core.g;
import com.meiqia.meiqiasdk.util.ErrorCode;
import com.stub.StubApp;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MQManager {
    protected static String a;
    private static MQManager b;
    /* access modifiers changed from: private */
    public static d c;
    /* access modifiers changed from: private */
    public static i d;
    /* access modifiers changed from: private */
    public static boolean m;
    /* access modifiers changed from: private */
    public f e;
    private Handler f;
    /* access modifiers changed from: private */
    public boolean g = true;
    private String h = "";
    private String i = "";
    private MQScheduleRule j = MQScheduleRule.REDIRECT_ENTERPRISE;
    private boolean k;
    private Context l;

    private MQManager(Context context) {
        d = new i(context);
        this.e = f.a(context);
        this.f = new Handler(Looper.getMainLooper());
        c = new d(context, d, this.e, this.f);
        this.l = context;
    }

    /* access modifiers changed from: private */
    public void a(@NonNull final OnClientOnlineCallback onClientOnlineCallback) {
        c.a(this.e, this.h, this.i, this.k, this.j, new g.a() {
            public void a(boolean z, MQAgent mQAgent, MQConversation mQConversation, List<MQMessage> list) {
                onClientOnlineCallback.onSuccess(mQAgent, mQConversation != null ? String.valueOf(mQConversation.getId()) : null, list);
            }

            public void onFailure(int i, String str) {
                onClientOnlineCallback.onFailure(i, str);
            }
        });
        this.k = false;
    }

    private void a(final SuccessCallback successCallback, final OnFailureCallBack onFailureCallBack) {
        if (successCallback == null) {
            successCallback = new SuccessCallback();
        }
        if (onFailureCallBack == null) {
            onFailureCallBack = new b();
        }
        if (m) {
            successCallback.onSuccess();
        } else {
            init(this.l, a, new OnInitCallback() {
                public void onFailure(int i, String str) {
                    onFailureCallBack.onFailure(i, str);
                }

                public void onSuccess(String str) {
                    successCallback.onSuccess();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        c.a(this.e.a(str));
        closeMeiqiaService();
    }

    private void a(String str, String str2, MQScheduleRule mQScheduleRule) {
        boolean z;
        boolean z2 = true;
        if ((!TextUtils.isEmpty(this.i) || !TextUtils.isEmpty(str)) && !TextUtils.equals(this.i, str)) {
            d.a(d.a, (String) null);
            z = true;
        } else {
            z = false;
        }
        boolean z3 = (!TextUtils.isEmpty(this.h) || !TextUtils.isEmpty(str2)) && !TextUtils.equals(this.h, str2);
        if (this.j == mQScheduleRule) {
            z2 = false;
        }
        if (z || z3 || z2) {
            c();
        }
    }

    private static boolean a(Context context) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            String packageName = context.getPackageName();
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.pid == myPid && packageName.equals(next.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean a(OnFailureCallBack onFailureCallBack) {
        if (onFailureCallBack == null) {
            onFailureCallBack = new b();
        }
        if (m) {
            return true;
        }
        onFailureCallBack.onFailure(ErrorCode.INIT_FAILED, "meiqia sdk init failed");
        return false;
    }

    private boolean a(String str, String str2, String str3, OnMessageSendCallback onMessageSendCallback) {
        if (onMessageSendCallback == null) {
            onMessageSendCallback = new b();
        }
        if (m) {
            return true;
        }
        MQMessage mQMessage = new MQMessage(str);
        mQMessage.setContent(str3);
        mQMessage.setMedia_url(str2);
        mQMessage.setFrom_type("client");
        mQMessage.setStatus("failed");
        onMessageSendCallback.onFailure(mQMessage, ErrorCode.INIT_FAILED, "meiqia sdk init failed");
        return true;
    }

    /* access modifiers changed from: private */
    public void c() {
        a((MQAgent) null);
    }

    public static MQManager getInstance(Context context) {
        if (b == null) {
            synchronized (MQManager.class) {
                if (b == null) {
                    b = new MQManager(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                }
            }
        }
        return b;
    }

    public static String getMeiqiaSDKVersion() {
        return "3.6.2";
    }

    public static void init(final Context context, String str, final OnInitCallback onInitCallback) {
        if (onInitCallback == null) {
            onInitCallback = new b();
        }
        if (a(context)) {
            b = getInstance(StubApp.getOrigApplicationContext(context.getApplicationContext()));
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("BuglySdkInfos", 0).edit();
                edit.putString("d298be5fd8", "3.6.2");
                edit.apply();
            } catch (Exception unused) {
            }
            final boolean z = !TextUtils.equals(str, d.a());
            if (TextUtils.isEmpty(str)) {
                str = d.a();
            } else {
                d.a(str);
            }
            a = str;
            c.a((OnInitCallback) new OnInitCallback() {
                public void onFailure(int i, String str) {
                    onInitCallback.onFailure(i, str);
                }

                public void onSuccess(String str) {
                    MQClient a2 = f.a(context).a(str);
                    if (a2 == null) {
                        onInitCallback.onFailure(ErrorCode.INIT_FAILED, "meiqia sdk init failed");
                        return;
                    }
                    if (z) {
                        MQManager.c.k();
                    }
                    MQManager.c.a(a2);
                    boolean unused = MQManager.m = true;
                    onInitCallback.onSuccess(str);
                    try {
                        if (MQManager.d.r(d.a)) {
                            MQManager.c.a((SimpleCallback) null);
                            MQManager.d.c(d.a, false);
                        }
                    } catch (Exception unused2) {
                    }
                }
            });
        }
    }

    @TargetApi(14)
    public static void setAppLifecycleListener(Application application, final AppLifecycleListener appLifecycleListener) {
        if (Build.VERSION.SDK_INT >= 14) {
            new a(application, new AppLifecycleListener() {
                public void background() {
                    f.b("background");
                    if (appLifecycleListener != null) {
                        appLifecycleListener.background();
                    }
                }

                public void foreground() {
                    f.b("foreground");
                    if (appLifecycleListener != null) {
                        appLifecycleListener.foreground();
                    }
                }
            });
        }
    }

    public static void setDebugMode(boolean z) {
        MeiQiaService.a = z;
    }

    /* access modifiers changed from: protected */
    public void a(MQAgent mQAgent) {
        c.a(mQAgent);
    }

    /* access modifiers changed from: protected */
    public void a(final OnGetMessageListCallback onGetMessageListCallback) {
        if (onGetMessageListCallback == null) {
            onGetMessageListCallback = new b();
        }
        c.a((OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                onGetMessageListCallback.onFailure(i, str);
            }

            public void onSuccess(List<MQMessage> list) {
                onGetMessageListCallback.onSuccess(list);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        c.a(z);
    }

    public void cancelDownload(String str) {
        c.b(str);
    }

    public void closeMeiqiaService() {
        setClientOffline();
    }

    public void createMQClient(OnGetMQClientIdCallBackOn onGetMQClientIdCallBackOn) {
        if (onGetMQClientIdCallBackOn == null) {
            onGetMQClientIdCallBackOn = new b();
        }
        c.a(onGetMQClientIdCallBackOn);
    }

    public void deleteAllMessage() {
        c.b();
    }

    public void deleteMessage(long j2) {
        c.a(j2);
    }

    public void downloadFile(MQMessage mQMessage, OnProgressCallback onProgressCallback) {
        if (onProgressCallback == null) {
            onProgressCallback = new b();
        }
        if (a((OnFailureCallBack) onProgressCallback)) {
            c.a(mQMessage, onProgressCallback);
        }
    }

    public void endCurrentConversation(OnEndConversationCallback onEndConversationCallback) {
        if (onEndConversationCallback == null) {
            onEndConversationCallback = new b();
        }
        if (a((OnFailureCallBack) onEndConversationCallback)) {
            c.a(onEndConversationCallback);
        }
    }

    public void evaluateRobotAnswer(long j2, long j3, int i2, OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback) {
        if (onEvaluateRobotAnswerCallback == null) {
            onEvaluateRobotAnswerCallback = new b();
        }
        OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback2 = onEvaluateRobotAnswerCallback;
        if (a((OnFailureCallBack) onEvaluateRobotAnswerCallback2)) {
            c.a(j2, j3, i2, onEvaluateRobotAnswerCallback2);
        }
    }

    public void executeEvaluate(String str, int i2, String str2, SimpleCallback simpleCallback) {
        if (simpleCallback == null) {
            simpleCallback = new b();
        }
        if (a((OnFailureCallBack) simpleCallback)) {
            c.a(str, i2, str2, simpleCallback);
        }
    }

    public void getClientPositionInQueue(final OnClientPositionInQueueCallback onClientPositionInQueueCallback) {
        if (onClientPositionInQueueCallback == null) {
            onClientPositionInQueueCallback = new b();
        }
        c.a((g.d) new g.d() {
            public void a(int i) {
                onClientPositionInQueueCallback.onSuccess(i);
            }

            public void onFailure(int i, String str) {
                onClientPositionInQueueCallback.onFailure(i, str);
            }
        });
    }

    public MQAgent getCurrentAgent() {
        return c.e();
    }

    public String getCurrentClientId() {
        if (!m) {
            return null;
        }
        return c.c();
    }

    public MQEnterpriseConfig getEnterpriseConfig() {
        return c.h();
    }

    public boolean getIsWaitingInQueue() {
        return c.f();
    }

    public MQInquireForm getMQInquireForm() {
        return c.i();
    }

    public void getMQMessageFromDatabase(long j2, int i2, final OnGetMessageListCallback onGetMessageListCallback) {
        if (onGetMessageListCallback == null) {
            onGetMessageListCallback = new b();
        }
        if (a((OnFailureCallBack) onGetMessageListCallback)) {
            this.e.a(j2, i2, (OnGetMessageListCallback) new OnGetMessageListCallback() {
                public void onFailure(int i, String str) {
                    onGetMessageListCallback.onFailure(i, str);
                }

                public void onSuccess(List<MQMessage> list) {
                    onGetMessageListCallback.onSuccess(list);
                }
            });
        }
    }

    public void getMQMessageFromService(long j2, int i2, OnGetMessageListCallback onGetMessageListCallback) {
        if (onGetMessageListCallback == null) {
            onGetMessageListCallback = new b();
        }
        OnGetMessageListCallback onGetMessageListCallback2 = onGetMessageListCallback;
        if (a((OnFailureCallBack) onGetMessageListCallback2)) {
            c.a(i2, 0, j2, 2, onGetMessageListCallback2);
        }
    }

    public void getTicketCategories(OnTicketCategoriesCallback onTicketCategoriesCallback) {
        if (onTicketCategoriesCallback == null) {
            onTicketCategoriesCallback = new b();
        }
        if (a((OnFailureCallBack) onTicketCategoriesCallback)) {
            c.a(onTicketCategoriesCallback);
        }
    }

    public void getUnreadMessages(OnGetMessageListCallback onGetMessageListCallback) {
        getUnreadMessages((String) null, onGetMessageListCallback);
    }

    public void getUnreadMessages(String str, OnGetMessageListCallback onGetMessageListCallback) {
        if (onGetMessageListCallback == null) {
            onGetMessageListCallback = new b();
        }
        if (a((OnFailureCallBack) onGetMessageListCallback)) {
            c.a(str, onGetMessageListCallback);
        }
    }

    public boolean isSocketConnect() {
        return c.g();
    }

    public void onConversationClose() {
        MeiQiaService.c = true;
        e.a(this.l).b();
        if (c != null) {
            c.j();
        }
        this.l.sendBroadcast(new Intent("ACTION_MQ_CONVERSATION_CLOSE"));
    }

    public void onConversationOpen() {
        MeiQiaService.c = false;
        e.a(this.l).c();
        e.a(this.l).a();
    }

    public void openMeiqiaService() {
        if (m) {
            c.a(this.l);
        }
    }

    public void refreshDeviceToken(String str, OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        if (onRegisterDeviceTokenCallback == null) {
            onRegisterDeviceTokenCallback = new b();
        }
        if (a((OnFailureCallBack) onRegisterDeviceTokenCallback)) {
            c.b(str, onRegisterDeviceTokenCallback);
        }
    }

    public void refreshEnterpriseConfig(SimpleCallback simpleCallback) {
        if (simpleCallback == null) {
            simpleCallback = new b();
        }
        if (a((OnFailureCallBack) simpleCallback)) {
            c.a(simpleCallback);
        }
    }

    public void registerDeviceToken(String str, OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        if (onRegisterDeviceTokenCallback == null) {
            onRegisterDeviceTokenCallback = new b();
        }
        if (a((OnFailureCallBack) onRegisterDeviceTokenCallback)) {
            c.a(str, onRegisterDeviceTokenCallback);
        }
    }

    public void replyClueCard(JSONObject jSONObject, SuccessCallback successCallback) {
        c.a(jSONObject, successCallback);
    }

    public void saveConversationLastMessageTime(long j2) {
        d.f(d.a, j2);
    }

    public void saveConversationOnStopTime(long j2) {
        d.e(d.a, j2);
    }

    public void sendClientInputtingWithContent(String str) {
        if (!TextUtils.isEmpty(str) && m && this.g) {
            this.g = false;
            c.a(str);
            this.f.postDelayed(new Runnable() {
                public void run() {
                    boolean unused = MQManager.this.g = true;
                }
            }, 5000);
        }
    }

    public void sendMQPhotoMessage(String str, OnMessageSendCallback onMessageSendCallback) {
        if (onMessageSendCallback == null) {
            onMessageSendCallback = new b();
        }
        if (a("photo", str, "", onMessageSendCallback)) {
            c.a("", "photo", str, onMessageSendCallback);
        }
    }

    public void sendMQTextMessage(String str, OnMessageSendCallback onMessageSendCallback) {
        if (onMessageSendCallback == null) {
            onMessageSendCallback = new b();
        }
        if (a("text", "", str, onMessageSendCallback)) {
            c.a(str, "text", (String) null, onMessageSendCallback);
        }
    }

    public void sendMQVoiceMessage(String str, OnMessageSendCallback onMessageSendCallback) {
        if (onMessageSendCallback == null) {
            onMessageSendCallback = new b();
        }
        if (a("audio", str, "", onMessageSendCallback)) {
            c.a("", "audio", str, onMessageSendCallback);
        }
    }

    public void setClientEvent(MQClientEvent mQClientEvent, OnClientInfoCallback onClientInfoCallback) {
        if (onClientInfoCallback == null) {
            onClientInfoCallback = new b();
        }
        if (a((OnFailureCallBack) onClientInfoCallback)) {
            if (TextUtils.isEmpty(mQClientEvent.getName()) || mQClientEvent.getEvent().length() == 0) {
                onClientInfoCallback.onFailure(ErrorCode.PARAMETER_ERROR, "event is null or metadata length is 0");
            } else {
                c.a(mQClientEvent, onClientInfoCallback);
            }
        }
    }

    public void setClientInfo(Map<String, String> map, OnClientInfoCallback onClientInfoCallback) {
        if (onClientInfoCallback == null) {
            onClientInfoCallback = new b();
        }
        if (a((OnFailureCallBack) onClientInfoCallback)) {
            c.a(map, onClientInfoCallback);
        }
    }

    public void setClientOffline() {
        MeiQiaService.b = true;
        Intent intent = new Intent(this.l, MeiQiaService.class);
        intent.setAction("ACTION_CLOSE_SOCKET");
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.l.stopService(intent);
            } else {
                this.l.startService(intent);
            }
        } catch (Throwable unused) {
        }
    }

    public void setClientOnlineWithClientId(final String str, final OnClientOnlineCallback onClientOnlineCallback) {
        if (onClientOnlineCallback == null) {
            onClientOnlineCallback = new b();
        }
        a((SuccessCallback) new SuccessCallback() {
            public void onSuccess() {
                MQClient a2 = MQManager.this.e.a(str);
                if (a2 != null && !a2.getTrackId().equals(d.a.getTrackId())) {
                    MQManager.d.a(d.a, (String) null);
                    MQManager.this.c();
                }
                if (a2 != null) {
                    MQManager.c.a(a2);
                    MQManager.this.a(onClientOnlineCallback);
                    return;
                }
                MQManager.c.a(str, (OnGetMQClientIdCallBackOn) new OnGetMQClientIdCallBackOn() {
                    public void onFailure(int i, String str) {
                        onClientOnlineCallback.onFailure(20003, "clientId is wrong");
                    }

                    public void onSuccess(String str) {
                        MQManager.this.setClientOnlineWithClientId(str, onClientOnlineCallback);
                    }
                });
            }
        }, (OnFailureCallBack) onClientOnlineCallback);
    }

    public void setClientOnlineWithCustomizedId(final String str, final OnClientOnlineCallback onClientOnlineCallback) {
        if (onClientOnlineCallback == null) {
            onClientOnlineCallback = new b();
        }
        a((SuccessCallback) new SuccessCallback() {
            public void onSuccess() {
                MQManager.c.a(str, (OnInitCallback) new OnInitCallback() {
                    public void onFailure(int i, String str) {
                        onClientOnlineCallback.onFailure(i, str);
                    }

                    public void onSuccess(String str) {
                        MQClient a2 = MQManager.this.e.a(str);
                        if (a2 != null && !a2.getTrackId().equals(d.a.getTrackId())) {
                            MQManager.d.a(d.a, (String) null);
                            MQManager.this.c();
                        }
                        MQManager.c.a(a2);
                        MQManager.this.a(onClientOnlineCallback);
                    }
                });
            }
        }, (OnFailureCallBack) onClientOnlineCallback);
    }

    public void setCurrentClient(String str, final SimpleCallback simpleCallback) {
        if (simpleCallback == null) {
            simpleCallback = new b();
        }
        if (a((OnFailureCallBack) simpleCallback)) {
            if (TextUtils.isEmpty(str)) {
                simpleCallback.onFailure(ErrorCode.PARAMETER_ERROR, "parameter error");
                return;
            }
            if (this.e.a(str) == null) {
                MQClient b2 = this.e.b(str);
                if (b2 == null) {
                    c.a(str, (OnInitCallback) new OnInitCallback() {
                        public void onFailure(int i, String str) {
                            simpleCallback.onFailure(i, str);
                        }

                        public void onSuccess(String str) {
                            MQManager.this.a(str);
                            simpleCallback.onSuccess();
                        }
                    });
                    return;
                }
                str = b2.getTrackId();
            }
            a(str);
            simpleCallback.onSuccess();
        }
    }

    public void setCurrentClientOnline(final OnClientOnlineCallback onClientOnlineCallback) {
        if (onClientOnlineCallback == null) {
            onClientOnlineCallback = new b();
        }
        a((SuccessCallback) new SuccessCallback() {
            public void onSuccess() {
                MQManager.this.a(onClientOnlineCallback);
            }
        }, (OnFailureCallBack) onClientOnlineCallback);
    }

    public void setForceRedirectHuman(boolean z) {
        this.k = z;
    }

    public void setPhotoCompressd(boolean z) {
        c.b(z);
    }

    public void setScheduledAgentOrGroupWithId(String str, String str2) {
        setScheduledAgentOrGroupWithId(str, str2, this.j);
    }

    public void setScheduledAgentOrGroupWithId(String str, String str2, MQScheduleRule mQScheduleRule) {
        a(str, str2, mQScheduleRule);
        this.i = str;
        this.h = str2;
        this.j = mQScheduleRule;
        c.a(str, str2, mQScheduleRule);
    }

    public void submitInquireForm(String str, Map<String, Object> map, Map<String, String> map2, SimpleCallback simpleCallback) {
        if (a((OnFailureCallBack) simpleCallback)) {
            if (TextUtils.isEmpty(str)) {
                simpleCallback.onFailure(ErrorCode.PARAMETER_ERROR, "clientIdOrCustomizedId is null");
            } else {
                c.a(str, map, map2, simpleCallback);
            }
        }
    }

    public void submitMessageForm(String str, List<String> list, Map<String, String> map, SimpleCallback simpleCallback) {
        if (simpleCallback == null) {
            simpleCallback = new b();
        }
        if (a((OnFailureCallBack) simpleCallback)) {
            c.a(str, list, map, simpleCallback);
        }
    }

    public void submitTickets(MQMessage mQMessage, String str, Map<String, String> map, OnMessageSendCallback onMessageSendCallback) {
        if (onMessageSendCallback == null) {
            onMessageSendCallback = new b();
        }
        try {
            c.a(mQMessage, Long.parseLong(str), map, onMessageSendCallback);
        } catch (Exception unused) {
            c.a(mQMessage, map, onMessageSendCallback);
        }
    }

    public void updateClientInfo(Map<String, String> map, OnClientInfoCallback onClientInfoCallback) {
        if (onClientInfoCallback == null) {
            onClientInfoCallback = new b();
        }
        if (a((OnFailureCallBack) onClientInfoCallback)) {
            if (map == null) {
                onClientInfoCallback.onFailure(ErrorCode.PARAMETER_ERROR, "parameter error");
            } else {
                c.a(true, map, onClientInfoCallback);
            }
        }
    }

    public void updateMessage(long j2, boolean z) {
        c.a(j2, z);
    }
}
