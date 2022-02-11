package com.meiqia.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.meiqia.core.a.c;
import com.meiqia.core.a.f;
import com.meiqia.core.a.i;
import com.meiqia.core.a.j;
import com.meiqia.core.a.k;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.core.callback.SimpleCallback;
import com.stub.StubApp;
import com.umeng.qq.tencent.AuthActivity;
import com.umeng.socialize.common.SocializeConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONException;
import org.json.JSONObject;

public class MeiQiaService extends Service {
    public static boolean a;
    public static boolean b;
    public static boolean c;
    protected static boolean d;
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    private int f = 0;
    /* access modifiers changed from: private */
    public i g;
    private a h;
    /* access modifiers changed from: private */
    public c i;
    /* access modifiers changed from: private */
    public Handler j;
    private OkHttpClient k;
    /* access modifiers changed from: private */
    public WebSocket l;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public boolean n = false;
    private String o;
    private boolean p;
    /* access modifiers changed from: private */
    public AtomicBoolean q = new AtomicBoolean(false);

    private class a extends BroadcastReceiver {
        private boolean b;

        private a() {
            this.b = true;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                if (k.f(context) && !this.b) {
                    f.b("socket net reconnect");
                    MeiQiaService.this.d();
                }
                this.b = false;
            } else if ("ACTION_MQ_CONVERSATION_CLOSE".equals(action)) {
                MeiQiaService.this.l();
                f.b("reset retryCount");
            }
        }
    }

    static {
        StubApp.interface11(5850);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r5 = this;
            com.meiqia.core.MeiQiaService$2 r0 = new com.meiqia.core.MeiQiaService$2
            r0.<init>()
            r1 = 1
            javax.net.ssl.TrustManager[] r1 = new javax.net.ssl.TrustManager[r1]
            r2 = 0
            r1[r2] = r0
            r2 = 0
            java.lang.String r3 = "SSL"
            javax.net.ssl.SSLContext r3 = javax.net.ssl.SSLContext.getInstance(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0025, KeyManagementException -> 0x001f }
            java.security.SecureRandom r4 = new java.security.SecureRandom     // Catch:{ NoSuchAlgorithmException -> 0x001d, KeyManagementException -> 0x001b }
            r4.<init>()     // Catch:{ NoSuchAlgorithmException -> 0x001d, KeyManagementException -> 0x001b }
            r3.init(r2, r1, r4)     // Catch:{ NoSuchAlgorithmException -> 0x001d, KeyManagementException -> 0x001b }
            goto L_0x002a
        L_0x001b:
            r1 = move-exception
            goto L_0x0021
        L_0x001d:
            r1 = move-exception
            goto L_0x0027
        L_0x001f:
            r1 = move-exception
            r3 = r2
        L_0x0021:
            r1.printStackTrace()
            goto L_0x002a
        L_0x0025:
            r1 = move-exception
            r3 = r2
        L_0x0027:
            r1.printStackTrace()
        L_0x002a:
            okhttp3.OkHttpClient$Builder r1 = new okhttp3.OkHttpClient$Builder
            r1.<init>()
            if (r3 == 0) goto L_0x0048
            javax.net.ssl.SSLSocketFactory r2 = r3.getSocketFactory()
            okhttp3.OkHttpClient$Builder r0 = r1.sslSocketFactory(r2, r0)
            com.meiqia.core.MeiQiaService$3 r1 = new com.meiqia.core.MeiQiaService$3
            r1.<init>()
            okhttp3.OkHttpClient$Builder r0 = r0.hostnameVerifier(r1)
            okhttp3.OkHttpClient r0 = r0.build()
            r5.k = r0
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.MeiQiaService.a():void");
    }

    /* access modifiers changed from: private */
    public void a(MQAgent mQAgent) {
        MQManager.getInstance(this).a(mQAgent);
        Intent intent = new Intent("agent_change_action");
        intent.putExtra("client_is_redirected", true);
        k.a((Context) this, intent);
        if (a) {
            f.b("action directAgent : agentName = " + mQAgent.getNickname());
        }
    }

    /* access modifiers changed from: private */
    public void a(MQMessage mQMessage) {
        Intent intent = new Intent("agent_send_card");
        intent.putExtra("clueCardMessageId", String.valueOf(mQMessage.getId()));
        MQMessageManager.getInstance(this).setAgentClueCardMessage(mQMessage);
        k.a((Context) this, intent);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        MQManager.getInstance(this).a((MQAgent) null);
        k.a((Context) this, new Intent(str));
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("body");
        if (optJSONObject != null) {
            MQMessage b2 = c.b(optJSONObject);
            d.a(b2, System.currentTimeMillis());
            b(b2);
            this.g.c(d.a, System.currentTimeMillis());
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if ((this.l == null || !d) && d.a != null && !this.m) {
            if (this.k == null) {
                this.k = new OkHttpClient.Builder().build();
            }
            this.o = d.a.getTrackId();
            f.b("socket init");
            this.m = true;
            d.a.setBrowserId(f());
            f.a((Context) this).a(d.a);
            String browserId = d.a.getBrowserId();
            String trackId = d.a.getTrackId();
            String visitId = d.a.getVisitId();
            String str = "?browser_id=" + browserId + "&ent_id=" + (d.a.getEnterpriseId() + "") + "&visit_id=" + visitId + "&visit_page_id=" + d.a.getVisitPageId() + "&track_id=" + trackId + "&time=" + (System.currentTimeMillis() + "");
            f.b("socket: t = " + trackId + " b = " + browserId + " v = " + visitId);
            try {
                this.l = this.k.newWebSocket(new Request.Builder().header("User-Agent", ("Mozilla/5.0 (Linux; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " " + Build.DEVICE + ") MeiqiaSDK/ Source/SDK " + MQManager.getMeiqiaSDKVersion() + " Language/" + Locale.getDefault().getLanguage()).replaceAll("[^\\x00-\\x7F]", "")).url("wss://eco-push-api-client.meiqia.com/pusher/websocket" + str).build(), new WebSocketListener() {
                    public void onClosed(WebSocket webSocket, int i, String str) {
                        f.b("socket close: i = " + i + " s = " + str);
                        MeiQiaService.d = false;
                        boolean unused = MeiQiaService.this.m = false;
                        MeiQiaService.this.d();
                    }

                    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                        if (th instanceof SSLHandshakeException) {
                            MeiQiaService.this.a();
                        }
                        MeiQiaService.d = false;
                        boolean unused = MeiQiaService.this.m = false;
                        MeiQiaService.this.d();
                        f.b("socket error: message = " + th.getMessage() + " class = " + th.getClass().getSimpleName());
                    }

                    public void onMessage(WebSocket webSocket, String str) {
                        if (!TextUtils.isEmpty(str) && !MeiQiaService.b) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String optString = jSONObject.optString(AuthActivity.ACTION_KEY);
                                MeiQiaService.this.l.send(jSONObject.optString("id"));
                                if ("message".equals(optString)) {
                                    MeiQiaService.this.b(c.b(jSONObject));
                                } else if ("agent_send_card".equals(optString)) {
                                    MeiQiaService.this.a(c.a(jSONObject));
                                } else if ("ticket_reply".equals(optString)) {
                                    MeiQiaService.this.a(jSONObject);
                                } else if (MQMessageManager.ACTION_RECALL_MESSAGE.equals(optString)) {
                                    MeiQiaService.this.c(jSONObject);
                                } else if ("agent_redirect".equals(optString) || "timeout_redirect".equals(optString)) {
                                    MeiQiaService.this.a(c.c(jSONObject.optJSONObject("body").optJSONObject("to")));
                                } else if ("agent_inputting".equals(optString)) {
                                    k.a((Context) MeiQiaService.this, new Intent("agent_inputting_action"));
                                } else {
                                    if (!"invite_evaluation".equals(optString)) {
                                        if (MQMessageManager.ACTION_END_CONV_TIMEOUT.equals(optString)) {
                                            MeiQiaService.this.a(optString);
                                            return;
                                        } else if (MQMessageManager.ACTION_END_CONV_AGENT.equals(optString)) {
                                            MeiQiaService.this.a(optString);
                                            JSONObject optJSONObject = jSONObject.optJSONObject("body");
                                            if (optJSONObject == null || !optJSONObject.optBoolean("evaluation")) {
                                                return;
                                            }
                                        } else if ("agent_update".equals(optString)) {
                                            MeiQiaService.this.d(jSONObject);
                                            return;
                                        } else if (TextUtils.equals("visit_black_add", optString)) {
                                            MeiQiaService.this.e(jSONObject);
                                            return;
                                        } else if (TextUtils.equals("visit_black_del", optString)) {
                                            MeiQiaService.this.f(jSONObject);
                                            return;
                                        } else if ("queueing_remove".equals(optString)) {
                                            MeiQiaService.this.e();
                                            return;
                                        } else if ("init_conv".equals(optString)) {
                                            MeiQiaService.this.g(jSONObject);
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                    MeiQiaService.this.b(jSONObject);
                                }
                            } catch (JSONException unused) {
                            }
                        }
                    }

                    public void onOpen(WebSocket webSocket, Response response) {
                        f.b("socket open");
                        MeiQiaService.d = true;
                        MeiQiaService.this.q.set(false);
                        boolean unused = MeiQiaService.this.m = false;
                        MeiQiaService.this.j.removeMessages(3);
                        if (!MeiQiaService.this.n) {
                            MeiQiaService.this.j.sendEmptyMessageDelayed(3, 2000);
                        }
                        boolean unused2 = MeiQiaService.this.n = false;
                        MeiQiaService.this.j.removeMessages(1);
                        MeiQiaService.this.j();
                        k.a((Context) MeiQiaService.this, new Intent(MQMessageManager.ACTION_SOCKET_OPEN));
                    }
                });
            } catch (Exception unused) {
                d = false;
                this.m = false;
                f.a("socket AssertionError");
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(MQMessage mQMessage) {
        if ("ending".equals(mQMessage.getType())) {
            MQManager.getInstance(this).a((MQAgent) null);
        }
        if ("audio".equals(mQMessage.getType())) {
            mQMessage.setIs_read(false);
            c(mQMessage);
            return;
        }
        this.i.a(mQMessage);
    }

    /* access modifiers changed from: private */
    public void b(JSONObject jSONObject) {
        String optString = jSONObject.optString("target_id");
        if (!TextUtils.isEmpty(optString)) {
            Intent intent = new Intent("invite_evaluation");
            intent.putExtra("conversation_id", optString);
            k.a((Context) this, intent);
        }
    }

    private void c() {
        if (this.l != null) {
            d = false;
            this.l.close(SocializeConstants.CANCLE_RESULTCODE, "manual");
        }
    }

    private void c(final MQMessage mQMessage) {
        File externalCacheDir = getExternalCacheDir();
        String media_url = mQMessage.getMedia_url();
        if (externalCacheDir == null || !k.a()) {
            b(mQMessage);
            return;
        }
        String absolutePath = externalCacheDir.getAbsolutePath();
        g.a().a(media_url, absolutePath, mQMessage.getId() + "", (SimpleCallback) new SimpleCallback() {
            public void onFailure(int i, String str) {
                MeiQiaService.this.b(mQMessage);
            }

            public void onSuccess() {
                MeiQiaService.this.b(mQMessage);
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(JSONObject jSONObject) {
        Intent intent = new Intent(MQMessageManager.ACTION_RECALL_MESSAGE);
        intent.putExtra("nickname", jSONObject.optString("agent_nickname"));
        long optLong = jSONObject.optJSONObject("body").optLong("msg_id");
        intent.putExtra("id", optLong);
        f.a((Context) this).a(optLong);
        k.a((Context) this, intent);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!d && !this.q.get() && !b && k.f(this) && d.a != null) {
            this.q.set(true);
            this.j.sendEmptyMessageDelayed(1, 5000);
            h();
        }
    }

    /* access modifiers changed from: private */
    public void d(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("body");
        if (optJSONObject != null) {
            MQAgent c2 = c.c(optJSONObject);
            MQAgent currentAgent = MQManager.getInstance(this).getCurrentAgent();
            if (currentAgent != null) {
                c2.setNickname(currentAgent.getNickname());
                MQManager.getInstance(this).a(c2);
                k.a((Context) this, new Intent("action_agent_status_update_event"));
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.p = true;
        MQManager.getInstance(this).a((MQAgent) null);
        MQManager.getInstance(this).a(false);
        k.a((Context) this, new Intent("action_queueing_remove"));
    }

    /* access modifiers changed from: private */
    public void e(JSONObject jSONObject) {
        if (TextUtils.equals(jSONObject.optString("track_id"), this.o)) {
            MQManager.getInstance(this).a((MQAgent) null);
            k.a((Context) this, new Intent("action_black_add"));
        }
    }

    private String f() {
        String str = "";
        String str2 = System.currentTimeMillis() + "";
        Random random = new Random();
        for (int i2 = 0; i2 < 5; i2++) {
            str = str + random.nextInt(10);
        }
        return str + str2;
    }

    /* access modifiers changed from: private */
    public void f(JSONObject jSONObject) {
        if (TextUtils.equals(jSONObject.optString("track_id"), this.o)) {
            k.a((Context) this, new Intent("action_black_del"));
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        f.b("service synMessages");
        MQManager.getInstance(this).a((OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(List<MQMessage> list) {
                for (MQMessage a2 : list) {
                    MeiQiaService.this.i.a(a2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void g(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (this.p && (optJSONObject = jSONObject.optJSONObject("body")) != null && (optJSONObject2 = optJSONObject.optJSONObject("agent")) != null) {
            MQAgent c2 = c.c(optJSONObject2);
            c2.setIsOnline(true);
            MQManager.getInstance(this).a(c2);
            k.a((Context) this, new Intent("action_queueing_init_conv"));
            this.p = false;
        }
    }

    private void h() {
        if (i()) {
            this.e.set(true);
            this.j.sendEmptyMessageDelayed(2, k());
        }
    }

    private boolean i() {
        return !d && !this.e.get() && !b && k.f(this) && d.a != null && 50 >= ((long) this.f) && !c;
    }

    /* access modifiers changed from: private */
    public void j() {
        this.f = 0;
        this.e.set(false);
        this.j.removeMessages(2);
    }

    /* access modifiers changed from: private */
    public long k() {
        int nextInt = new Random().nextInt(6) * SocializeConstants.CANCLE_RESULTCODE;
        if (nextInt < 3000) {
            nextInt = 3000;
        }
        return (long) (nextInt + (this.f * CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION));
    }

    /* access modifiers changed from: private */
    public void l() {
        this.f = 0;
    }

    /* access modifiers changed from: private */
    public void m() {
        if (i()) {
            final long b2 = this.g.b(d.a);
            String a2 = j.a(b2);
            HashMap hashMap = new HashMap();
            hashMap.put("socket_error", "true");
            hashMap.put("limit", "100");
            hashMap.put("ent_id", d.a.getEnterpriseId());
            hashMap.put("last_message_created_on", a2);
            hashMap.put("ascending", "1");
            hashMap.put("_time", String.valueOf(System.currentTimeMillis()));
            g.a().a((Map<String, String>) hashMap, (OnGetMessageListCallback) new OnGetMessageListCallback() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(@NonNull List<MQMessage> list) {
                    for (MQMessage next : list) {
                        if (next.getCreated_on() > b2) {
                            MeiQiaService.this.g.b(d.a, next.getCreated_on());
                            MeiQiaService.this.l();
                        }
                        MeiQiaService.this.i.a(next);
                    }
                }
            });
            this.f++;
            f.b("pollMessages retryCount = " + this.f);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.j = new Handler();
        this.h = new a();
        this.g = new i(this);
        this.i = c.a((Context) this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("ACTION_MQ_CONVERSATION_CLOSE");
        registerReceiver(this.h, intentFilter);
        this.j = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (1 == i) {
                    f.b("socket reconnect");
                    MeiQiaService.this.q.set(false);
                    MeiQiaService.this.b();
                } else if (2 == i) {
                    MeiQiaService.this.e.set(false);
                    MeiQiaService.this.m();
                    MeiQiaService.this.j.sendEmptyMessageDelayed(2, MeiQiaService.this.k());
                } else if (3 == i) {
                    MeiQiaService.this.g();
                }
                return false;
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.h);
            c();
        } catch (Exception unused) {
        }
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        if (d.a == null) {
            return super.onStartCommand(intent, i2, i3);
        }
        if ("ACTION_OPEN_SOCKET".equals(intent != null ? intent.getAction() : "ACTION_OPEN_SOCKET")) {
            boolean z = false;
            b = false;
            if (!TextUtils.isEmpty(this.o) && !TextUtils.isEmpty(d.a.getTrackId()) && !d.a.getTrackId().equals(this.o)) {
                c();
            }
            if (intent != null && intent.getBooleanExtra("KEY_BOOLEAN_SYNC_MSG", false)) {
                z = true;
            }
            this.n = z;
            b();
        } else {
            c();
        }
        return super.onStartCommand(intent, i2, i3);
    }
}
