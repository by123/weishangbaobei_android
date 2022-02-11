package com.meiqia.core;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.meiqia.core.a.b;
import com.meiqia.core.a.c;
import com.meiqia.core.a.f;
import com.meiqia.core.a.h;
import com.meiqia.core.a.i;
import com.meiqia.core.a.j;
import com.meiqia.core.a.k;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQClient;
import com.meiqia.core.bean.MQClientEvent;
import com.meiqia.core.bean.MQConversation;
import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnEndConversationCallback;
import com.meiqia.core.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.core.callback.OnFailureCallBack;
import com.meiqia.core.callback.OnGetClientCallback;
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
import com.umeng.socialize.sina.params.ShareRequestParam;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

class d {
    protected static MQClient a = null;
    private static int b = 100;
    /* access modifiers changed from: private */
    public final i c;
    private final Handler d;
    /* access modifiers changed from: private */
    public final f e;
    /* access modifiers changed from: private */
    public final Context f;
    /* access modifiers changed from: private */
    public MQAgent g;
    /* access modifiers changed from: private */
    public MQConversation h;
    private MQEnterpriseConfig i;
    private g j;
    private String k;
    private String l;
    private MQScheduleRule m = MQScheduleRule.REDIRECT_ENTERPRISE;
    /* access modifiers changed from: private */
    public boolean n = false;
    private boolean o = true;

    private class a implements OnGetClientCallback {
        /* access modifiers changed from: private */
        public OnGetMQClientIdCallBackOn b;

        public a(OnGetMQClientIdCallBackOn onGetMQClientIdCallBackOn) {
            this.b = onGetMQClientIdCallBackOn;
        }

        public void onFailure(int i, String str) {
            if (this.b != null) {
                this.b.onFailure(i, str);
            }
        }

        public void onSuccess(boolean z, String str, final String str2, String str3, String str4, String str5, String str6) {
            d.this.e.a(new MQClient(d.this.c.a(), "", str2, str, str3, str4, str5, str6));
            String str7 = str2;
            d.this.a((Runnable) new Runnable() {
                public void run() {
                    if (a.this.b != null) {
                        a.this.b.onSuccess(str2);
                    }
                }
            });
        }
    }

    public d(Context context, i iVar, f fVar, Handler handler) {
        this.f = context;
        this.c = iVar;
        this.d = handler;
        this.j = g.a();
        this.e = fVar;
    }

    private void a(long j2, final g.i iVar) {
        this.j.a(j2, (g.e) new g.e() {
            public void a(JSONObject jSONObject) {
                String optString = jSONObject.optString(MQInquireForm.KEY_STATUS);
                if ("closed".equals(optString) || "processed".equals(optString)) {
                    iVar.b();
                } else {
                    iVar.a();
                }
            }

            public void onFailure(int i, String str) {
                iVar.onFailure(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(@NonNull MQClient mQClient, String str, @NonNull final List<MQMessage> list, final OnGetMessageListCallback onGetMessageListCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("enterprise_id", mQClient.getEnterpriseId());
        hashMap.put("begin", str);
        this.j.a((Map<String, String>) hashMap, mQClient.getTrackId(), (OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                onGetMessageListCallback.onFailure(i, str);
            }

            public void onSuccess(List<MQMessage> list) {
                int i = 0;
                for (MQMessage next : list) {
                    d.a(next, (long) i);
                    next.setTrack_id(d.a.getTrackId());
                    i++;
                }
                if (list.size() > 0) {
                    d.this.c.c(d.a, list.get(list.size() - 1).getCreated_on());
                }
                list.addAll(list);
                Collections.sort(list, new h());
                onGetMessageListCallback.onSuccess(list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(MQConversation mQConversation) {
        this.h = mQConversation;
    }

    private void a(MQMessage mQMessage) {
        mQMessage.setAvatar(this.c.j(a));
        mQMessage.setFrom_type("client");
        mQMessage.setType("message");
        String trackId = a.getTrackId();
        if (!TextUtils.isEmpty(trackId)) {
            mQMessage.setTrack_id(trackId);
        }
        if (this.h != null && this.g != null) {
            mQMessage.setAgent_nickname(this.g.getNickname());
            mQMessage.setConversation_id(this.h.getId());
            mQMessage.setAgent_id(this.h.getAgent_id());
            mQMessage.setEnterprise_id(this.h.getEnterprise_id());
        }
    }

    protected static void a(MQMessage mQMessage, long j2) {
        mQMessage.setExtra(mQMessage.getId() + "");
        mQMessage.setId(j2);
    }

    /* access modifiers changed from: private */
    public void a(final MQMessage mQMessage, final OnMessageSendCallback onMessageSendCallback) {
        if (this.g != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("browser_id", a.getBrowserId());
            hashMap.put("track_id", a.getTrackId());
            hashMap.put("ent_id", a.getEnterpriseId());
            hashMap.put("type", mQMessage.getContent_type());
            hashMap.put("content", mQMessage.getContent());
            this.j.a("https://eco-api.meiqia.com/client/send_msg", (Map<String, Object>) hashMap, (g.h) new g.h() {
                public void a(String str, long j, String str2) {
                    long a2 = j.a(str);
                    long id = mQMessage.getId();
                    mQMessage.setCreated_on(a2);
                    mQMessage.setId(j);
                    mQMessage.setStatus("arrived");
                    if (!TextUtils.isEmpty(str2)) {
                        mQMessage.setExtra(str2);
                        try {
                            JSONObject jSONObject = new JSONObject(str2);
                            boolean optBoolean = jSONObject.optBoolean("contains_sensitive_words", false);
                            String optString = jSONObject.optString("replaced_content");
                            if (optBoolean) {
                                mQMessage.setContent(optString);
                            } else {
                                mQMessage.setContent(mQMessage.getContent());
                            }
                        } catch (Exception unused) {
                        }
                    }
                    if (d.this.e() != null) {
                        mQMessage.setAgent_nickname(d.this.e().getNickname());
                    }
                    d.this.e.a(mQMessage, id);
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            if (onMessageSendCallback != null) {
                                onMessageSendCallback.onSuccess(mQMessage, 1);
                            }
                        }
                    });
                }

                public void onFailure(int i, String str) {
                    if (i != 19997) {
                        if (i == 20009) {
                            d.this.a((MQAgent) null);
                        }
                        mQMessage.setStatus("failed");
                        d.this.e.a(mQMessage);
                        if (onMessageSendCallback != null) {
                            onMessageSendCallback.onFailure(mQMessage, i, str);
                            return;
                        }
                        return;
                    }
                    d.this.a((MQConversation) null);
                    d.this.a((MQAgent) null);
                    d.this.b(mQMessage, onMessageSendCallback);
                }
            });
            return;
        }
        b(mQMessage, onMessageSendCallback);
    }

    /* access modifiers changed from: private */
    public void a(MQMessage mQMessage, Map<String, String> map, g.f fVar) {
        long a2 = this.c.a(a);
        HashMap hashMap = new HashMap();
        hashMap.put("enterprise_id", a.getEnterpriseId());
        hashMap.put("track_id", a.getTrackId());
        hashMap.put("visit_id", a.getVisitId());
        hashMap.put("channel", MQMessage.TYPE_SDK);
        if (a2 != -1) {
            hashMap.put("conv_id", Long.valueOf(a2));
        }
        hashMap.put("content_type", mQMessage.getContent_type());
        hashMap.put("content", mQMessage.getContent());
        this.j.a((Map<String, Object>) hashMap, fVar);
        a(false, map, (OnClientInfoCallback) null);
    }

    /* access modifiers changed from: private */
    public void a(final g.a aVar) {
        a((Runnable) new Runnable() {
            public void run() {
                if (aVar != null) {
                    MQManager.getInstance(d.this.f).getMQMessageFromDatabase(Long.MAX_VALUE, 10, new OnGetMessageListCallback() {
                        public void onFailure(int i, String str) {
                            aVar.onFailure(i, str);
                        }

                        public void onSuccess(List<MQMessage> list) {
                            aVar.a(d.this.n, d.this.g, d.this.h, list);
                        }
                    });
                }
                Intent intent = new Intent(d.this.f, MeiQiaService.class);
                intent.setAction("ACTION_OPEN_SOCKET");
                intent.putExtra("KEY_BOOLEAN_SYNC_MSG", true);
                try {
                    d.this.f.startService(intent);
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Runnable runnable) {
        this.d.post(runnable);
    }

    /* access modifiers changed from: private */
    public void a(String str, final SimpleCallback simpleCallback) {
        try {
            String g2 = this.c.g(a);
            final MQClient a2 = this.e.a(str);
            final String g3 = this.c.g(a2);
            Map<String, Object> e2 = k.e(this.f);
            final String jSONObject = c.a((Map<?, ?>) e2).toString();
            if (!TextUtils.isEmpty(g3) && !TextUtils.isEmpty(g2)) {
                if (TextUtils.isEmpty(g2) || g2.equals(jSONObject)) {
                    if (simpleCallback != null) {
                        a((Runnable) new Runnable() {
                            public void run() {
                                simpleCallback.onSuccess();
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            final SimpleCallback simpleCallback2 = simpleCallback;
            this.j.a(str, e2, (SimpleCallback) new OnClientInfoCallback() {
                public void onFailure(int i, String str) {
                    if (simpleCallback2 != null) {
                        simpleCallback2.onFailure(i, str);
                    }
                }

                public void onSuccess() {
                    i a2;
                    MQClient mQClient;
                    if (TextUtils.isEmpty(g3)) {
                        a2 = d.this.c;
                        mQClient = a2;
                    } else {
                        a2 = d.this.c;
                        mQClient = d.a;
                    }
                    a2.b(mQClient, jSONObject);
                    if (simpleCallback2 != null) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                simpleCallback2.onSuccess();
                            }
                        });
                    }
                }
            });
        } catch (Exception unused) {
            if (simpleCallback != null) {
                a((Runnable) new Runnable() {
                    public void run() {
                        simpleCallback.onFailure(ErrorCode.UNKNOWN, "UNKNOW");
                    }
                });
            }
        }
    }

    private void a(String str, String str2, final g.j jVar) {
        try {
            File file = new File(str2);
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 3143036) {
                if (hashCode != 93166550) {
                    if (hashCode == 106642994 && str.equals("photo")) {
                        c2 = 0;
                    }
                } else if (str.equals("audio")) {
                    c2 = 1;
                }
            } else if (str.equals("file")) {
                c2 = 2;
            }
            switch (c2) {
                case 0:
                    String a2 = k.a(this.f);
                    File file2 = new File(a2, System.currentTimeMillis() + "");
                    if (Build.VERSION.SDK_INT >= 29) {
                        try {
                            k.a(this.f.getContentResolver().openFileDescriptor(k.a(this.f, str2), "r").getFileDescriptor(), file2.getAbsolutePath());
                            file = file2;
                        } catch (Exception e2) {
                            a((Runnable) new Runnable() {
                                public void run() {
                                    if (jVar != null) {
                                        g.j jVar = jVar;
                                        jVar.onFailure(ErrorCode.UNKNOWN, "uri not valid e = " + e2.toString());
                                    }
                                }
                            });
                        }
                    }
                    if (this.o) {
                        b.a(file, file2);
                    } else {
                        file2 = file;
                    }
                    this.j.a(file2, (g.b) new g.b() {
                        public void a(JSONObject jSONObject, Response response) {
                            final String optString = jSONObject.optString("photo_url");
                            final String optString2 = jSONObject.optString("photo_key");
                            d.this.a((Runnable) new Runnable() {
                                public void run() {
                                    if (jVar != null) {
                                        jVar.a(optString2, optString);
                                    }
                                }
                            });
                        }
                    }, (OnFailureCallBack) jVar);
                    return;
                case 1:
                    this.j.b(file, new g.b() {
                        public void a(JSONObject jSONObject, Response response) {
                            final String optString = jSONObject.optString(com.umeng.qq.handler.a.j);
                            final String optString2 = jSONObject.optString("audio_key");
                            d.this.a((Runnable) new Runnable() {
                                public void run() {
                                    if (jVar != null) {
                                        jVar.a(optString2, optString);
                                    }
                                }
                            });
                        }
                    }, jVar);
                    return;
                case 2:
                    return;
                default:
                    jVar.onFailure(ErrorCode.PARAMETER_ERROR, "unknown contentType");
                    return;
            }
        } catch (Exception unused) {
            jVar.onFailure(ErrorCode.FILE_NOT_FOUND, "file not found");
        }
    }

    /* access modifiers changed from: private */
    public void a(List<MQMessage> list, long j2) {
        Iterator<MQMessage> it = list.iterator();
        while (it.hasNext()) {
            MQMessage next = it.next();
            if ("ending".equals(next.getType()) || next.getCreated_on() <= j2 || "client".equals(next.getFrom_type())) {
                it.remove();
            }
        }
    }

    private void a(List<MQMessage> list, List<String> list2, Map<String, String> map, SimpleCallback simpleCallback) {
        int[] iArr = {0, 0};
        for (String a2 : list2) {
            final int[] iArr2 = iArr;
            final List<MQMessage> list3 = list;
            final List<String> list4 = list2;
            final Map<String, String> map2 = map;
            final SimpleCallback simpleCallback2 = simpleCallback;
            a("photo", a2, (g.j) new g.j() {
                public void a(String str, String str2) {
                    iArr2[0] = iArr2[0] + 1;
                    MQMessage mQMessage = new MQMessage("photo");
                    mQMessage.setContent(str);
                    mQMessage.setMedia_url(str2);
                    list3.add(mQMessage);
                    if (iArr2[0] + iArr2[1] != list4.size()) {
                        return;
                    }
                    if (iArr2[0] == list4.size()) {
                        d.this.a((List<MQMessage>) list3, (Map<String, String>) map2, simpleCallback2);
                    } else if (simpleCallback2 != null) {
                        simpleCallback2.onFailure(20002, "upload photo failed");
                    }
                }

                public void onFailure(int i, String str) {
                    iArr2[1] = iArr2[1] + 1;
                    if (iArr2[0] + iArr2[1] == list4.size() && simpleCallback2 != null) {
                        simpleCallback2.onFailure(i, str);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(final List<MQMessage> list, final Map<String, String> map, final SimpleCallback simpleCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("browser_id", a.getBrowserId());
        hashMap.put("track_id", a.getTrackId());
        hashMap.put("enterprise_id", a.getEnterpriseId());
        hashMap.put("visit_id", a.getVisitId());
        ArrayList arrayList = new ArrayList();
        for (MQMessage next : list) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("content_type", next.getContent_type());
            hashMap2.put("content", next.getContent());
            arrayList.add(hashMap2);
        }
        hashMap.put("replies", arrayList);
        this.j.a((Map<String, Object>) hashMap, (g.C0000g) new g.C0000g() {
            public void a(JSONArray jSONArray) {
                for (int i = 0; i < list.size(); i++) {
                    MQMessage mQMessage = (MQMessage) list.get(i);
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        mQMessage.setCreated_on(j.a(optJSONObject.optString("created_on")));
                        mQMessage.setId(optJSONObject.optLong("id"));
                        mQMessage.setStatus("arrived");
                        mQMessage.setFrom_type("client");
                        mQMessage.setType("message");
                        mQMessage.setTrack_id(d.a.getTrackId());
                        if (!(d.this.h == null || d.this.g == null)) {
                            mQMessage.setAgent_nickname(d.this.g.getNickname());
                            mQMessage.setConversation_id(d.this.h.getId());
                            mQMessage.setAgent_id(d.this.h.getAgent_id());
                            mQMessage.setEnterprise_id(d.this.h.getEnterprise_id());
                        }
                        d.this.e.a(mQMessage);
                    }
                }
                if (map != null && map.keySet().size() != 0) {
                    d.this.a((Map<String, String>) map, simpleCallback);
                } else if (simpleCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            simpleCallback.onSuccess();
                        }
                    });
                }
            }

            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Map<String, String> map, final SimpleCallback simpleCallback) {
        a(map, (OnClientInfoCallback) new OnClientInfoCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                if (simpleCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            simpleCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final Map<String, Object> map, final List<MQMessage> list, final g.a aVar) {
        a(a.getTrackId(), (SimpleCallback) new OnClientInfoCallback() {
            public void onFailure(int i, String str) {
                d.this.b((Map<String, Object>) map, (List<MQMessage>) list, aVar);
            }

            public void onSuccess() {
                d.this.b((Map<String, Object>) map, (List<MQMessage>) list, aVar);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final MQMessage mQMessage, final OnMessageSendCallback onMessageSendCallback) {
        a(this.e, this.k, this.l, false, this.m, new g.a() {
            public void a(boolean z, MQAgent mQAgent, MQConversation mQConversation, List<MQMessage> list) {
                d.this.a(z);
                if (z) {
                    Intent intent = new Intent(d.this.f, MeiQiaService.class);
                    intent.setAction("ACTION_OPEN_SOCKET");
                    try {
                        d.this.f.startService(intent);
                    } catch (Throwable unused) {
                    }
                    mQMessage.setStatus("failed");
                    d.this.e.a(mQMessage);
                    onMessageSendCallback.onFailure(mQMessage, ErrorCode.QUEUEING, (String) null);
                    return;
                }
                MQMessageManager.getInstance(d.this.f).setCurrentAgent(mQAgent);
                Intent intent2 = new Intent("agent_change_action");
                intent2.putExtra("conversation_id", String.valueOf(mQConversation.getId()));
                k.a(d.this.f, intent2);
                d.this.a(mQAgent);
                d.this.a(mQMessage, (OnMessageSendCallback) new OnMessageSendCallback() {
                    public void onFailure(MQMessage mQMessage, int i, String str) {
                        onMessageSendCallback.onFailure(mQMessage, i, str);
                    }

                    public void onSuccess(MQMessage mQMessage, int i) {
                        Intent intent = new Intent(d.this.f, MeiQiaService.class);
                        intent.setAction("ACTION_OPEN_SOCKET");
                        try {
                            d.this.f.startService(intent);
                        } catch (Throwable unused) {
                        }
                        onMessageSendCallback.onSuccess(mQMessage, i);
                    }
                });
            }

            public void onFailure(int i, String str) {
                if (i != 19998) {
                    mQMessage.setStatus("failed");
                    d.this.e.a(mQMessage);
                    if (onMessageSendCallback != null) {
                        onMessageSendCallback.onFailure(mQMessage, i, str);
                        return;
                    }
                    return;
                }
                d.this.a((MQAgent) null);
                d.this.a(true, mQMessage, (Map<String, String>) null, onMessageSendCallback);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(MQMessage mQMessage, Map<String, String> map, g.f fVar) {
        long f2 = this.c.f(a);
        HashMap hashMap = new HashMap();
        hashMap.put("enterprise_id", a.getEnterpriseId());
        hashMap.put("type", MQMessage.TYPE_SDK);
        hashMap.put("content_type", mQMessage.getContent_type());
        hashMap.put(ShareRequestParam.REQ_PARAM_SOURCE, "client");
        hashMap.put("content", mQMessage.getContent());
        this.j.a((Map<String, Object>) hashMap, f2, fVar);
        a(false, map, (OnClientInfoCallback) null);
    }

    private void b(final OnGetMessageListCallback onGetMessageListCallback) {
        long b2 = this.c.b(a);
        int parseInt = Integer.parseInt(a.getEnterpriseId());
        String a2 = j.a(b2);
        this.j.a(a.getTrackId(), b, 0, parseInt, a2, 1, (OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                if (onGetMessageListCallback != null) {
                    onGetMessageListCallback.onFailure(i, str);
                }
            }

            public void onSuccess(@NonNull final List<MQMessage> list) {
                for (MQMessage next : list) {
                    if (TextUtils.equals("client", next.getFrom_type())) {
                        next.setAvatar(d.this.c.j(d.a));
                    }
                }
                if (onGetMessageListCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            onGetMessageListCallback.onSuccess(list);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Map<String, Object> map, final List<MQMessage> list, final g.a aVar) {
        this.j.a(map, (g.a) new g.a() {
            public void a(boolean z, MQAgent mQAgent, MQConversation mQConversation, List<MQMessage> list) {
                if (mQConversation != null) {
                    d.this.c.a(d.a, mQConversation.getId());
                }
                if (!(list == null || list == null)) {
                    list.addAll(0, list);
                }
                d.this.a(z);
                if (!z) {
                    d.this.c.a(d.a, true);
                    d.this.a(mQAgent);
                    d.this.a(mQConversation);
                    d.this.e.b(list);
                }
                d.this.a(aVar);
                d.this.m();
            }

            public void onFailure(int i, String str) {
                if (i == 20004 || i == 19998) {
                    try {
                        Intent intent = new Intent(d.this.f, MeiQiaService.class);
                        intent.setAction("ACTION_OPEN_SOCKET");
                        d.this.f.startService(intent);
                    } catch (Throwable unused) {
                    }
                    if (i == 19998) {
                        d.this.a(false);
                        d.this.a((MQAgent) null);
                    }
                }
                if (aVar != null) {
                    aVar.onFailure(i, str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void m() {
        if (!this.c.n(a)) {
            this.j.a(this.c.m(a), (OnRegisterDeviceTokenCallback) new OnRegisterDeviceTokenCallback() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess() {
                    d.this.c.b(d.a, true);
                }
            });
        }
    }

    public void a() {
        b((OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(List<MQMessage> list) {
                for (MQMessage a2 : list) {
                    c.a(d.this.f).a(a2);
                }
            }
        });
    }

    public void a(int i2, int i3, long j2, int i4, OnGetMessageListCallback onGetMessageListCallback) {
        int parseInt = Integer.parseInt(a.getEnterpriseId());
        String a2 = j.a(j2);
        final long j3 = j2;
        final int i5 = i2;
        final OnGetMessageListCallback onGetMessageListCallback2 = onGetMessageListCallback;
        this.j.a(a.getTrackId(), i2, i3, parseInt, a2, i4, (OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                if (onGetMessageListCallback2 != null) {
                    onGetMessageListCallback2.onFailure(i, str);
                }
            }

            public void onSuccess(List<MQMessage> list) {
                d.this.e.b(list);
                MQManager.getInstance(d.this.f).getMQMessageFromDatabase(j3, i5, new OnGetMessageListCallback() {
                    public void onFailure(final int i, final String str) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                if (onGetMessageListCallback2 != null) {
                                    onGetMessageListCallback2.onFailure(i, str);
                                }
                            }
                        });
                    }

                    public void onSuccess(final List<MQMessage> list) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                if (onGetMessageListCallback2 != null) {
                                    onGetMessageListCallback2.onSuccess(list);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public void a(long j2) {
        this.e.a(j2);
    }

    public void a(final long j2, long j3, int i2, final OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback) {
        this.j.a(j3, i2, (OnEvaluateRobotAnswerCallback) new OnEvaluateRobotAnswerCallback() {
            public void onFailure(int i, String str) {
                onEvaluateRobotAnswerCallback.onFailure(i, str);
            }

            public void onSuccess(final String str) {
                d.this.b(j2);
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        onEvaluateRobotAnswerCallback.onSuccess(str);
                    }
                });
            }
        });
    }

    public void a(long j2, boolean z) {
        MQMessage b2 = this.e.b(j2);
        if (b2 != null) {
            b2.setIs_read(z);
            this.e.a(b2);
        }
    }

    public void a(Context context) {
        try {
            Intent intent = new Intent(context, MeiQiaService.class);
            intent.setAction("ACTION_OPEN_SOCKET");
            context.startService(intent);
            a();
        } catch (Throwable unused) {
        }
    }

    public void a(MQAgent mQAgent) {
        this.g = mQAgent;
        if (this.g != null && !this.g.isRobot()) {
            this.c.a(a, (String) null);
        }
        MQMessageManager.getInstance(this.f).setCurrentAgent(mQAgent);
    }

    public void a(MQClient mQClient) {
        if (mQClient != null) {
            a = mQClient;
            this.c.b(a.getTrackId());
            f.b((("current info: t = " + mQClient.getTrackId()) + " b " + mQClient.getBrowserId()) + " e " + mQClient.getEnterpriseId());
        }
    }

    public void a(@NonNull MQClientEvent mQClientEvent, @Nullable final OnClientInfoCallback onClientInfoCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("ent_id", Integer.valueOf(Integer.parseInt(a.getEnterpriseId())));
        hashMap.put("track_id", a.getTrackId());
        hashMap.put("name", mQClientEvent.getName());
        hashMap.put("metadata", mQClientEvent.getEvent().toString());
        this.j.b((Map<String, Object>) hashMap, (OnClientInfoCallback) new OnClientInfoCallback() {
            public void onFailure(final int i, final String str) {
                if (onClientInfoCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            onClientInfoCallback.onFailure(i, str);
                        }
                    });
                }
            }

            public void onSuccess() {
                if (onClientInfoCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            onClientInfoCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }

    public void a(final MQMessage mQMessage, long j2, Map<String, String> map, final OnMessageSendCallback onMessageSendCallback) {
        long a2 = this.c.a(a);
        HashMap hashMap = new HashMap();
        if (j2 != -1) {
            hashMap.put("category_id", Long.valueOf(j2));
        }
        if (a2 != -1) {
            hashMap.put("conv_id", Long.valueOf(a2));
        }
        hashMap.put("enterprise_id", a.getEnterpriseId());
        hashMap.put("track_id", a.getTrackId());
        hashMap.put("visit_id", a.getVisitId());
        hashMap.put("channel", MQMessage.TYPE_SDK);
        hashMap.put("content_type", mQMessage.getContent_type());
        hashMap.put("content", mQMessage.getContent());
        this.j.a((Map<String, Object>) hashMap, (g.f) new g.f() {
            public void a(String str, long j) {
                mQMessage.setCreated_on(j.a(str));
                mQMessage.setId(j);
                mQMessage.setStatus("arrived");
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        onMessageSendCallback.onSuccess(mQMessage, 1);
                    }
                });
            }

            public void onFailure(int i, String str) {
                mQMessage.setStatus("failed");
                onMessageSendCallback.onFailure(mQMessage, i, str);
            }
        });
        a(false, map, (OnClientInfoCallback) null);
    }

    public void a(MQMessage mQMessage, final OnProgressCallback onProgressCallback) {
        File file;
        Runnable r10;
        if (!k.a()) {
            r10 = new Runnable() {
                public void run() {
                    onProgressCallback.onFailure(20005, "sdcard is not available");
                }
            };
        } else {
            this.j.a(mQMessage.getConversation_id(), mQMessage.getId(), a.getTrackId(), Long.parseLong(a.getEnterpriseId()), (SimpleCallback) null);
            if (Build.VERSION.SDK_INT >= 29) {
                file = this.f.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                if (file != null) {
                    file.mkdirs();
                } else {
                    r10 = new Runnable() {
                        public void run() {
                            onProgressCallback.onFailure(20005, "sdcard is not available");
                        }
                    };
                }
            } else {
                file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            }
            String absolutePath = file.getAbsolutePath();
            try {
                String optString = new JSONObject(mQMessage.getExtra()).optString("filename");
                int lastIndexOf = optString.lastIndexOf(".");
                final String str = optString.substring(0, lastIndexOf) + mQMessage.getId() + optString.substring(lastIndexOf, optString.length());
                File file2 = new File(absolutePath);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                final File file3 = new File(file2, str);
                this.j.a(mQMessage, file3, (OnProgressCallback) new OnProgressCallback() {
                    public void onFailure(final int i, final String str) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                onProgressCallback.onFailure(i, str);
                            }
                        });
                    }

                    public void onProgress(final int i) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                onProgressCallback.onProgress(i);
                            }
                        });
                    }

                    public void onSuccess() {
                        if (Build.VERSION.SDK_INT >= 29) {
                            k.a(d.this.f, file3.getAbsolutePath(), str);
                        }
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                onProgressCallback.onSuccess();
                            }
                        });
                    }
                });
                return;
            } catch (Exception unused) {
                a((Runnable) new Runnable() {
                    public void run() {
                        onProgressCallback.onFailure(ErrorCode.UNKNOWN, "download file failed");
                    }
                });
                return;
            }
        }
        a(r10);
    }

    public void a(MQMessage mQMessage, Map<String, String> map, OnMessageSendCallback onMessageSendCallback) {
        a(mQMessage, -1, map, onMessageSendCallback);
    }

    public void a(final OnEndConversationCallback onEndConversationCallback) {
        this.j.a((SimpleCallback) new OnClientInfoCallback() {
            public void onFailure(int i, String str) {
                if (onEndConversationCallback != null) {
                    onEndConversationCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                d.this.c.a(d.a, (String) null);
                d.this.a((MQAgent) null);
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        if (onEndConversationCallback != null) {
                            onEndConversationCallback.onSuccess();
                        }
                    }
                });
                MQManager.getInstance(d.this.f).closeMeiqiaService();
            }
        });
    }

    public void a(OnGetMQClientIdCallBackOn onGetMQClientIdCallBackOn) {
        this.j.a((OnGetClientCallback) new a(onGetMQClientIdCallBackOn));
    }

    /* access modifiers changed from: protected */
    public void a(final OnGetMessageListCallback onGetMessageListCallback) {
        if (!this.c.d(a)) {
            a((Runnable) new Runnable() {
                public void run() {
                    onGetMessageListCallback.onSuccess(new ArrayList());
                }
            });
        } else {
            b((OnGetMessageListCallback) new OnGetMessageListCallback() {
                public void onFailure(int i, String str) {
                    if (i == 20010) {
                        onGetMessageListCallback.onFailure(i, str);
                        return;
                    }
                    d.this.a(d.a, j.a(d.this.c.c(d.a)), (List<MQMessage>) new ArrayList(), onGetMessageListCallback);
                }

                public void onSuccess(@NonNull List<MQMessage> list) {
                    d.this.a(d.a, j.a(d.this.c.c(d.a)), list, onGetMessageListCallback);
                }
            });
        }
    }

    public void a(final OnInitCallback onInitCallback) {
        MQClient d2 = d();
        if (d2 == null) {
            d2 = l();
        }
        if (!(d2 != null)) {
            a((OnGetMQClientIdCallBackOn) new OnGetMQClientIdCallBackOn() {
                public void onFailure(int i, String str) {
                    if (onInitCallback != null) {
                        onInitCallback.onFailure(i, str);
                    }
                }

                public void onSuccess(String str) {
                    if (onInitCallback != null) {
                        onInitCallback.onSuccess(str);
                    }
                    d.this.a(str, (SimpleCallback) null);
                }
            });
        } else if (onInitCallback != null) {
            onInitCallback.onSuccess(d2.getTrackId());
        }
    }

    public void a(final OnTicketCategoriesCallback onTicketCategoriesCallback) {
        this.j.a((g.e) new g.e() {
            public void a(final JSONObject jSONObject) {
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        onTicketCategoriesCallback.onSuccess(jSONObject.optJSONArray("categories"));
                    }
                });
            }

            public void onFailure(int i, String str) {
                onTicketCategoriesCallback.onFailure(i, str);
            }
        });
    }

    public void a(@Nullable final SimpleCallback simpleCallback) {
        if (System.currentTimeMillis() - this.c.p(a) >= 600000) {
            HashMap hashMap = new HashMap();
            hashMap.put("ent_id", a.getEnterpriseId());
            hashMap.put("track_id", a.getTrackId());
            this.j.a((Map<String, Object>) hashMap, (g.e) new g.e() {
                public void a(JSONObject jSONObject) {
                    d.this.c.h(d.a, System.currentTimeMillis());
                    d.this.c.g(d.a, jSONObject.toString());
                    c.a(d.this.h(), jSONObject, d.this.c, d.a);
                    if (simpleCallback != null) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                simpleCallback.onSuccess();
                            }
                        });
                    }
                }

                public void onFailure(int i, String str) {
                    if (simpleCallback != null) {
                        simpleCallback.onFailure(i, str);
                    }
                }
            });
        } else if (simpleCallback != null) {
            simpleCallback.onFailure(ErrorCode.UNKNOWN, "request limit");
        }
    }

    public void a(final f fVar, String str, String str2, boolean z, MQScheduleRule mQScheduleRule, @NonNull final g.a aVar) {
        if (z || !MeiQiaService.d || this.g == null || aVar == null || !this.c.d(a)) {
            String trackId = a.getTrackId();
            String visitId = a.getVisitId();
            String enterpriseId = a.getEnterpriseId();
            final HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("group_token", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("agent_token", str2);
            }
            hashMap.put("fallback", Integer.valueOf(mQScheduleRule.getValue()));
            hashMap.put("visit_id", visitId);
            hashMap.put("track_id", trackId);
            hashMap.put("ent_id", Long.valueOf(enterpriseId));
            if (z && this.g != null) {
                hashMap.put("exclude_agent_tokens", Arrays.asList(new String[]{this.g.getId()}));
            }
            if (!TextUtils.isEmpty(this.c.e(a))) {
                hashMap.put("exclude_agent_tokens", Arrays.asList(new String[]{this.c.e(a)}));
            }
            hashMap.put("queueing", true);
            if (!this.c.d(a)) {
                a((Map<String, Object>) hashMap, (List<MQMessage>) null, aVar);
            } else {
                a((OnGetMessageListCallback) new OnGetMessageListCallback() {
                    public void onFailure(int i, String str) {
                        if (i == 20010) {
                            aVar.onFailure(i, str);
                        } else {
                            d.this.a((Map<String, Object>) hashMap, (List<MQMessage>) null, aVar);
                        }
                    }

                    public void onSuccess(List<MQMessage> list) {
                        fVar.a(list);
                        d.this.a((Map<String, Object>) hashMap, list, aVar);
                    }
                });
            }
        } else {
            a(aVar);
        }
    }

    public void a(final g.d dVar) {
        this.j.a((g.d) new g.d() {
            public void a(final int i) {
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        dVar.a(d.this.n ? i : 0);
                    }
                });
            }

            public void onFailure(int i, String str) {
                dVar.onFailure(i, str);
            }
        });
    }

    public void a(String str) {
        this.j.a(str, this.g != null ? this.g.getAgentId() : -1);
    }

    public void a(String str, int i2, String str2, final SimpleCallback simpleCallback) {
        this.j.a(str, i2, str2, (SimpleCallback) new SimpleCallback() {
            public void onFailure(int i, String str) {
                simpleCallback.onFailure(i, str);
            }

            public void onSuccess() {
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        simpleCallback.onSuccess();
                    }
                });
            }
        });
    }

    public void a(String str, OnGetMQClientIdCallBackOn onGetMQClientIdCallBackOn) {
        this.j.a(str, (OnGetClientCallback) new a(onGetMQClientIdCallBackOn));
    }

    public void a(String str, OnGetMessageListCallback onGetMessageListCallback) {
        String str2;
        int i2;
        String str3;
        final long j2;
        final MQClient mQClient;
        String str4 = str;
        if (TextUtils.isEmpty(str)) {
            long k2 = this.c.k(a);
            long l2 = this.c.l(a);
            if (k2 <= l2) {
                k2 = l2;
            }
            String a2 = j.a(k2);
            int parseInt = Integer.parseInt(a.getEnterpriseId());
            OnGetMessageListCallback onGetMessageListCallback2 = onGetMessageListCallback;
            str2 = a2;
            i2 = parseInt;
            str3 = a.getTrackId();
            mQClient = a;
            j2 = k2;
        } else {
            MQClient a3 = this.e.a(str4);
            if (a3 == null) {
                a3 = this.e.b(str4);
            }
            if (a3 == null) {
                a((String) null, onGetMessageListCallback);
                return;
            }
            OnGetMessageListCallback onGetMessageListCallback3 = onGetMessageListCallback;
            long k3 = this.c.k(a3);
            long l3 = this.c.l(a3);
            if (k3 <= l3) {
                k3 = l3;
            }
            String a4 = j.a(k3);
            str2 = a4;
            i2 = Integer.parseInt(a3.getEnterpriseId());
            str3 = a3.getTrackId();
            j2 = k3;
            mQClient = a3;
        }
        final String str5 = str2;
        final OnGetMessageListCallback onGetMessageListCallback4 = onGetMessageListCallback;
        this.j.a(str3, b, 0, i2, str2, 1, (OnGetMessageListCallback) new OnGetMessageListCallback() {
            public void onFailure(int i, String str) {
                if (onGetMessageListCallback4 == null) {
                    return;
                }
                if (i == 404) {
                    onGetMessageListCallback4.onSuccess(new ArrayList());
                } else {
                    onGetMessageListCallback4.onFailure(i, str);
                }
            }

            public void onSuccess(List<MQMessage> list) {
                d.this.a(mQClient, str5, list, (OnGetMessageListCallback) new OnGetMessageListCallback() {
                    public void onFailure(int i, String str) {
                        if (onGetMessageListCallback4 == null) {
                            return;
                        }
                        if (i == 404) {
                            onGetMessageListCallback4.onSuccess(new ArrayList());
                        } else {
                            onGetMessageListCallback4.onFailure(i, str);
                        }
                    }

                    public void onSuccess(final List<MQMessage> list) {
                        d.this.a(list, j2);
                        if (onGetMessageListCallback4 != null) {
                            d.this.e.a(list);
                            d.this.a((Runnable) new Runnable() {
                                public void run() {
                                    onGetMessageListCallback4.onSuccess(list);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void a(final String str, final OnInitCallback onInitCallback) {
        if (!TextUtils.isEmpty(str)) {
            MQClient b2 = this.e.b(str);
            if (b2 == null) {
                this.j.b(str, (OnGetClientCallback) new OnGetClientCallback() {
                    public void onFailure(int i, String str) {
                        if (onInitCallback != null) {
                            onInitCallback.onFailure(i, str);
                        }
                    }

                    public void onSuccess(boolean z, String str, final String str2, String str3, String str4, String str5, String str6) {
                        d.this.e.a(new MQClient(d.this.c.a(), str, str2, str, str3, str4, str5, str6));
                        String str7 = str2;
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                if (onInitCallback != null) {
                                    onInitCallback.onSuccess(str2);
                                }
                            }
                        });
                    }
                });
            } else if (onInitCallback != null) {
                onInitCallback.onSuccess(b2.getTrackId());
            }
        } else if (onInitCallback != null) {
            onInitCallback.onFailure(ErrorCode.PARAMETER_ERROR, "customizedId can't be empty");
        }
    }

    public void a(String str, final OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        Runnable r3;
        if (TextUtils.isEmpty(str)) {
            r3 = new Runnable() {
                public void run() {
                    onRegisterDeviceTokenCallback.onFailure(ErrorCode.PARAMETER_ERROR, "token is null");
                }
            };
        } else {
            this.c.f(a, str);
            r3 = new Runnable() {
                public void run() {
                    onRegisterDeviceTokenCallback.onSuccess();
                }
            };
        }
        a(r3);
    }

    public void a(String str, String str2, MQScheduleRule mQScheduleRule) {
        this.l = str;
        this.k = str2;
        this.m = mQScheduleRule;
    }

    public void a(String str, final String str2, String str3, final OnMessageSendCallback onMessageSendCallback) {
        final MQMessage mQMessage = new MQMessage(str2);
        mQMessage.setContent(str);
        mQMessage.setMedia_url(str3);
        mQMessage.setFrom_type("client");
        a(mQMessage);
        this.e.a(mQMessage);
        if ("text".equals(str2)) {
            a(mQMessage, onMessageSendCallback);
        } else {
            a(str2, str3, (g.j) new g.j() {
                public void a(String str, String str2) {
                    mQMessage.setMedia_url(str2);
                    mQMessage.setContent(str);
                    if ("file".equals(str2)) {
                        mQMessage.setExtra("");
                    }
                    d.this.a(mQMessage, onMessageSendCallback);
                }

                public void onFailure(int i, String str) {
                    mQMessage.setStatus("failed");
                    d.this.e.a(mQMessage);
                    if (onMessageSendCallback != null) {
                        onMessageSendCallback.onFailure(mQMessage, i, str);
                    }
                }
            });
        }
    }

    public void a(String str, List<String> list, Map<String, String> map, SimpleCallback simpleCallback) {
        MQMessage mQMessage = new MQMessage("text");
        mQMessage.setContent(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(mQMessage);
        if (list == null || list.size() <= 0) {
            a((List<MQMessage>) arrayList, map, simpleCallback);
        } else {
            a((List<MQMessage>) arrayList, list, map, simpleCallback);
        }
    }

    public void a(String str, final Map<String, Object> map, final Map<String, String> map2, final SimpleCallback simpleCallback) {
        MQClient a2 = this.e.a(str);
        if (a2 == null && (a2 = this.e.b(str)) == null) {
            a(str, (OnInitCallback) new OnInitCallback() {
                public void onFailure(int i, String str) {
                    if (simpleCallback != null) {
                        simpleCallback.onFailure(i, str);
                    }
                }

                public void onSuccess(String str) {
                    d.this.b(str, (Map<String, Object>) map, (Map<String, String>) map2, simpleCallback);
                }
            });
        } else {
            b(a2.getTrackId(), map, map2, simpleCallback);
        }
    }

    public void a(Map<String, String> map, @Nullable final OnClientInfoCallback onClientInfoCallback) {
        try {
            final String jSONObject = c.a((Map<?, ?>) map).toString();
            if (!jSONObject.equals(this.c.h(a)) || onClientInfoCallback == null) {
                String trackId = a.getTrackId();
                String enterpriseId = a.getEnterpriseId();
                JSONObject a2 = c.a((Map<?, ?>) new HashMap(map));
                HashMap hashMap = new HashMap();
                hashMap.put("attrs", a2);
                hashMap.put("track_id", trackId);
                hashMap.put("ent_id", enterpriseId);
                hashMap.put("visit_id", a.getVisitId());
                if (map.containsKey("avatar")) {
                    this.c.e(a, map.get("avatar"));
                }
                this.j.a((Map<String, Object>) hashMap, (OnClientInfoCallback) new OnClientInfoCallback() {
                    public void onFailure(int i, String str) {
                        if (onClientInfoCallback != null) {
                            onClientInfoCallback.onFailure(i, str);
                        }
                    }

                    public void onSuccess() {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                d.this.c.c(d.a, jSONObject);
                                if (onClientInfoCallback != null) {
                                    onClientInfoCallback.onSuccess();
                                }
                            }
                        });
                    }
                });
                return;
            }
            onClientInfoCallback.onSuccess();
        } catch (Exception unused) {
            if (onClientInfoCallback != null) {
                onClientInfoCallback.onFailure(ErrorCode.PARAMETER_ERROR, "parameter error");
            }
        }
    }

    public void a(JSONObject jSONObject, SuccessCallback successCallback) {
        long a2 = this.c.a(a);
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", a.getTrackId());
        hashMap.put("visit_id", a.getVisitId());
        hashMap.put("card_type", "client_card");
        hashMap.put("attrs", jSONObject);
        if (a2 != -1) {
            hashMap.put("conv_id", Long.valueOf(a2));
        }
        this.j.a((Map<String, Object>) hashMap, successCallback);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        i iVar;
        String str;
        MQClient mQClient;
        this.n = z;
        if (!this.n) {
            iVar = this.c;
            mQClient = a;
            str = null;
        } else if (this.g != null && this.g.isRobot()) {
            iVar = this.c;
            mQClient = a;
            str = this.g.getId();
        } else {
            return;
        }
        iVar.a(mQClient, str);
    }

    public void a(final boolean z, final MQMessage mQMessage, final Map<String, String> map, final OnMessageSendCallback onMessageSendCallback) {
        final AnonymousClass26 r0 = new g.f() {
            public void a(String str, long j) {
                d.this.c.c(d.a, j.a(str));
                d.this.c.d(d.a, j);
                long a2 = j.a(str);
                long id = mQMessage.getId();
                mQMessage.setCreated_on(a2);
                d.a(mQMessage, System.currentTimeMillis());
                mQMessage.setStatus("arrived");
                mQMessage.setType(MQMessage.TYPE_SDK);
                if (z) {
                    d.this.e.a(mQMessage, id);
                }
                d.this.a((Runnable) new Runnable() {
                    public void run() {
                        onMessageSendCallback.onSuccess(mQMessage, ErrorCode.NO_AGENT_ONLINE);
                    }
                });
            }

            public void onFailure(int i, String str) {
                mQMessage.setStatus("failed");
                if (z) {
                    d.this.e.a(mQMessage);
                }
                onMessageSendCallback.onFailure(mQMessage, i, str);
            }
        };
        long f2 = this.c.f(a);
        if (f2 == -1) {
            a(mQMessage, map, (g.f) r0);
        } else {
            a(f2, (g.i) new g.i() {
                public void a() {
                    d.this.b(mQMessage, (Map<String, String>) map, r0);
                }

                public void b() {
                    d.this.a(mQMessage, (Map<String, String>) map, r0);
                }

                public void onFailure(int i, String str) {
                    r0.onFailure(i, str);
                }
            });
        }
    }

    public void a(final boolean z, @NonNull final Map<String, String> map, @Nullable final OnClientInfoCallback onClientInfoCallback) {
        try {
            if (map.containsKey("avatar")) {
                this.c.e(a, map.get("avatar"));
            }
            if (TextUtils.isEmpty(this.c.h(a))) {
                a(map, onClientInfoCallback);
                return;
            }
            if (z) {
                if (c.a((Map<?, ?>) map).toString().equals(this.c.i(a)) && onClientInfoCallback != null) {
                    onClientInfoCallback.onSuccess();
                    return;
                }
            }
            String trackId = a.getTrackId();
            String enterpriseId = a.getEnterpriseId();
            JSONObject a2 = c.a((Map<?, ?>) map);
            HashMap hashMap = new HashMap();
            hashMap.put("attrs", a2);
            hashMap.put("track_id", trackId);
            hashMap.put("ent_id", enterpriseId);
            hashMap.put("visit_id", a.getVisitId());
            hashMap.put("overwrite", true);
            this.j.a((Map<String, Object>) hashMap, (OnClientInfoCallback) new OnClientInfoCallback() {
                public void onFailure(int i, String str) {
                    if (onClientInfoCallback != null) {
                        onClientInfoCallback.onFailure(i, str);
                    }
                }

                public void onSuccess() {
                    if (z) {
                        d.this.c.d(d.a, c.a((Map<?, ?>) map).toString());
                    }
                    if (onClientInfoCallback != null) {
                        d.this.a((Runnable) new Runnable() {
                            public void run() {
                                onClientInfoCallback.onSuccess();
                            }
                        });
                    }
                }
            });
        } catch (Exception unused) {
            if (onClientInfoCallback != null) {
                onClientInfoCallback.onFailure(ErrorCode.PARAMETER_ERROR, "parameter error");
            }
        }
    }

    public void b() {
        this.e.a();
    }

    public void b(long j2) {
        MQMessage b2 = this.e.b(j2);
        if (b2 != null) {
            b2.setAlreadyFeedback(true);
            this.e.a(b2);
        }
    }

    public void b(String str) {
        this.j.a(str);
    }

    public void b(@NonNull String str, final OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        long o2 = this.c.o(a);
        long currentTimeMillis = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance(Locale.getDefault());
        instance.setTimeInMillis(o2);
        instance.add(5, 2);
        if (currentTimeMillis < instance.getTimeInMillis()) {
            onRegisterDeviceTokenCallback.onSuccess();
        } else {
            this.j.b(str, (OnRegisterDeviceTokenCallback) new OnRegisterDeviceTokenCallback() {
                public void onFailure(int i, String str) {
                    if (onRegisterDeviceTokenCallback != null) {
                        onRegisterDeviceTokenCallback.onFailure(i, str);
                    }
                }

                public void onSuccess() {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            d.this.c.g(d.a, System.currentTimeMillis());
                            if (onRegisterDeviceTokenCallback != null) {
                                onRegisterDeviceTokenCallback.onSuccess();
                            }
                        }
                    });
                }
            });
        }
    }

    public void b(String str, Map<String, Object> map, Map<String, String> map2, final SimpleCallback simpleCallback) {
        this.j.a(str, map, map2, (SimpleCallback) new SimpleCallback() {
            public void onFailure(int i, String str) {
                if (simpleCallback != null) {
                    simpleCallback.onFailure(i, str);
                }
            }

            public void onSuccess() {
                d.this.h().survey.setHas_submitted_form(true);
                d.this.c.a(d.a, "has_submitted_form", true);
                if (simpleCallback != null) {
                    d.this.a((Runnable) new Runnable() {
                        public void run() {
                            simpleCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }

    public void b(boolean z) {
        this.o = z;
    }

    public String c() {
        return a.getTrackId();
    }

    public MQClient d() {
        String b2 = this.c.b();
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        return this.e.a(b2);
    }

    public MQAgent e() {
        return this.g;
    }

    public boolean f() {
        return this.n;
    }

    public boolean g() {
        return MeiQiaService.d;
    }

    public MQEnterpriseConfig h() {
        if (this.i == null) {
            this.i = new MQEnterpriseConfig();
            String q = this.c.q(a);
            if (!TextUtils.isEmpty(q)) {
                try {
                    c.a(this.i, new JSONObject(q), this.c, a);
                } catch (Exception unused) {
                }
            }
        }
        return this.i;
    }

    public MQInquireForm i() {
        String form_def = h().form.getForm_def();
        MQInquireForm mQInquireForm = new MQInquireForm();
        try {
            JSONObject jSONObject = new JSONObject(form_def);
            mQInquireForm.setVersion((long) jSONObject.optInt("version"));
            mQInquireForm.setCaptcha(jSONObject.optBoolean(MQInquireForm.KEY_CAPTCHA));
            mQInquireForm.setInputs(jSONObject.optJSONObject(MQInquireForm.KEY_INPUTS));
            mQInquireForm.setMenus(jSONObject.optJSONObject(MQInquireForm.KEY_MENUS));
            mQInquireForm.setSubmitForm(h().survey.isHas_submitted_form());
        } catch (Exception unused) {
        }
        return mQInquireForm;
    }

    public void j() {
        g.a().c();
    }

    public void k() {
        a((MQAgent) null);
        this.i = null;
    }

    public MQClient l() {
        i iVar = new i(this.f);
        String c2 = iVar.c();
        String a2 = iVar.a();
        iVar.b(c2);
        MQClient a3 = k.a(c2, iVar);
        if (a3 != null) {
            a3.setAppKey(a2);
            this.e.a(a3);
        }
        return a3;
    }
}
