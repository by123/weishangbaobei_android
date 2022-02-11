package com.meiqia.core;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.meiqia.core.bean.MQAgent;
import com.meiqia.core.bean.MQConversation;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnClientInfoCallback;
import com.meiqia.core.callback.OnEvaluateRobotAnswerCallback;
import com.meiqia.core.callback.OnFailureCallBack;
import com.meiqia.core.callback.OnGetClientCallback;
import com.meiqia.core.callback.OnGetMessageListCallback;
import com.meiqia.core.callback.OnProgressCallback;
import com.meiqia.core.callback.OnRegisterDeviceTokenCallback;
import com.meiqia.core.callback.SimpleCallback;
import com.meiqia.core.callback.SuccessCallback;
import com.meiqia.meiqiasdk.activity.MQCollectInfoActivity;
import com.meiqia.meiqiasdk.util.ErrorCode;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    public static boolean a;
    public static final MediaType b = MediaType.parse("application/json; charset=utf-8");
    private static g c;
    private static OkHttpClient d;
    /* access modifiers changed from: private */
    public Handler e = new Handler(Looper.getMainLooper());

    public interface a extends OnFailureCallBack {
        void a(boolean z, MQAgent mQAgent, MQConversation mQConversation, List<MQMessage> list);
    }

    public interface b {
        void a(@NonNull JSONObject jSONObject, Response response);
    }

    private class c implements b {
        private c() {
        }

        public void a(JSONObject jSONObject, Response response) {
        }
    }

    public interface d extends OnFailureCallBack {
        void a(int i);
    }

    public interface e extends OnFailureCallBack {
        void a(JSONObject jSONObject);
    }

    public interface f extends OnFailureCallBack {
        void a(String str, long j);
    }

    /* renamed from: com.meiqia.core.g$g  reason: collision with other inner class name */
    public interface C0000g extends OnFailureCallBack {
        void a(JSONArray jSONArray);
    }

    public interface h extends OnFailureCallBack {
        void a(String str, long j, String str2);
    }

    public interface i extends OnFailureCallBack {
        void a();

        void b();
    }

    public interface j extends OnFailureCallBack {
        void a(String str, String str2);
    }

    private g() {
        d = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
    }

    public static g a() {
        if (c == null) {
            synchronized (g.class) {
                if (c == null) {
                    c = new g();
                }
            }
        }
        return c;
    }

    private String a(Map<String, Object> map) {
        return com.meiqia.core.a.a.a(d.a != null ? d.a.getAESKey() : "", com.meiqia.core.a.c.a((Map<?, ?>) map).toString());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        r13.onFailure(com.meiqia.meiqiasdk.util.ErrorCode.UNKNOWN, "download file failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0075 A[SYNTHETIC, Splitter:B:38:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a0 A[SYNTHETIC, Splitter:B:47:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ad A[SYNTHETIC, Splitter:B:53:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00bb A[SYNTHETIC, Splitter:B:60:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00c8 A[SYNTHETIC, Splitter:B:66:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.io.File r11, okhttp3.Response r12, com.meiqia.core.callback.OnProgressCallback r13) {
        /*
            r10 = this;
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = 20000(0x4e20, float:2.8026E-41)
            okhttp3.ResponseBody r3 = r12.body()     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            java.io.InputStream r3 = r3.byteStream()     // Catch:{ IOException -> 0x0071, all -> 0x006e }
            okhttp3.ResponseBody r12 = r12.body()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            long r4 = r12.contentLength()     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            r6 = 0
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
            r12.<init>(r11)     // Catch:{ IOException -> 0x006a, all -> 0x0066 }
        L_0x001e:
            int r11 = r3.read(r0)     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            r1 = -1
            if (r11 == r1) goto L_0x0044
            long r8 = (long) r11     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            long r6 = r6 + r8
            r1 = 0
            r12.write(r0, r1, r11)     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            if (r13 == 0) goto L_0x001e
            float r11 = (float) r6     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            r1 = 1065353216(0x3f800000, float:1.0)
            float r11 = r11 * r1
            float r1 = (float) r4     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            float r11 = r11 / r1
            r1 = 1120403456(0x42c80000, float:100.0)
            float r11 = r11 * r1
            int r11 = (int) r11     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            r13.onProgress(r11)     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            r1 = 100
            if (r11 != r1) goto L_0x001e
            r13.onSuccess()     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            goto L_0x001e
        L_0x0044:
            r12.flush()     // Catch:{ IOException -> 0x0064, all -> 0x0062 }
            if (r3 == 0) goto L_0x0054
            r3.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0054
        L_0x004d:
            if (r13 == 0) goto L_0x0054
            java.lang.String r11 = "download file failed"
            r13.onFailure(r2, r11)
        L_0x0054:
            r12.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x00cf
        L_0x0059:
            if (r13 == 0) goto L_0x00cf
        L_0x005b:
            java.lang.String r11 = "download file failed"
            r13.onFailure(r2, r11)
            goto L_0x00cf
        L_0x0062:
            r11 = move-exception
            goto L_0x0068
        L_0x0064:
            r11 = move-exception
            goto L_0x006c
        L_0x0066:
            r11 = move-exception
            r12 = r1
        L_0x0068:
            r1 = r3
            goto L_0x009e
        L_0x006a:
            r11 = move-exception
            r12 = r1
        L_0x006c:
            r1 = r3
            goto L_0x0073
        L_0x006e:
            r11 = move-exception
            r12 = r1
            goto L_0x009e
        L_0x0071:
            r11 = move-exception
            r12 = r1
        L_0x0073:
            if (r13 == 0) goto L_0x00b9
            java.lang.String r0 = r11.getMessage()     // Catch:{ all -> 0x009d }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x009d }
            if (r0 != 0) goto L_0x0097
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x009d }
            java.lang.String r11 = r11.toLowerCase()     // Catch:{ all -> 0x009d }
            java.lang.String r0 = "cancel"
            boolean r11 = r11.contains(r0)     // Catch:{ all -> 0x009d }
            if (r11 == 0) goto L_0x0097
            r11 = 20006(0x4e26, float:2.8034E-41)
            java.lang.String r0 = "download is cancel"
            r13.onFailure(r11, r0)     // Catch:{ all -> 0x009d }
            goto L_0x00b9
        L_0x0097:
            java.lang.String r11 = "download file failed"
            r13.onFailure(r2, r11)     // Catch:{ all -> 0x009d }
            goto L_0x00b9
        L_0x009d:
            r11 = move-exception
        L_0x009e:
            if (r1 == 0) goto L_0x00ab
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00ab
        L_0x00a4:
            if (r13 == 0) goto L_0x00ab
            java.lang.String r0 = "download file failed"
            r13.onFailure(r2, r0)
        L_0x00ab:
            if (r12 == 0) goto L_0x00b8
            r12.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b8
        L_0x00b1:
            if (r13 == 0) goto L_0x00b8
            java.lang.String r12 = "download file failed"
            r13.onFailure(r2, r12)
        L_0x00b8:
            throw r11
        L_0x00b9:
            if (r1 == 0) goto L_0x00c6
            r1.close()     // Catch:{ IOException -> 0x00bf }
            goto L_0x00c6
        L_0x00bf:
            if (r13 == 0) goto L_0x00c6
            java.lang.String r11 = "download file failed"
            r13.onFailure(r2, r11)
        L_0x00c6:
            if (r12 == 0) goto L_0x00cf
            r12.close()     // Catch:{ IOException -> 0x00cc }
            goto L_0x00cf
        L_0x00cc:
            if (r13 == 0) goto L_0x00cf
            goto L_0x005b
        L_0x00cf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.g.a(java.io.File, okhttp3.Response, com.meiqia.core.callback.OnProgressCallback):void");
    }

    private void a(String str, Map<String, Object> map, b bVar, OnFailureCallBack onFailureCallBack) {
        a(true, str, map, bVar, onFailureCallBack);
    }

    private void a(final Request request, final b bVar, final OnFailureCallBack onFailureCallBack) {
        com.meiqia.core.a.f.a(request);
        d.newCall(request).enqueue(new Callback() {
            public void onFailure(final Call call, final IOException iOException) {
                if (iOException instanceof SSLHandshakeException) {
                    g.this.d();
                }
                g.this.e.post(new Runnable() {
                    public void run() {
                        OnFailureCallBack onFailureCallBack;
                        int i;
                        String str = "IOException";
                        if (iOException != null) {
                            str = iOException.getMessage();
                        }
                        if (onFailureCallBack != null) {
                            if (call.isCanceled()) {
                                onFailureCallBack = onFailureCallBack;
                                i = ErrorCode.CANCEL;
                            } else {
                                onFailureCallBack = onFailureCallBack;
                                i = ErrorCode.NET_NOT_WORK;
                            }
                            onFailureCallBack.onFailure(i, str);
                        }
                    }
                });
            }

            public void onResponse(Call call, final Response response) {
                Runnable runnable;
                Handler handler;
                final String str;
                final int i;
                if (bVar != null) {
                    if (bVar instanceof c) {
                        bVar.a((JSONObject) null, response);
                        return;
                    }
                    final JSONObject a2 = com.meiqia.core.a.c.a(response);
                    String optString = a2.optString("ret");
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            a2 = new JSONObject(com.meiqia.core.a.a.b(d.a.getAESKey(), optString));
                        } catch (Exception unused) {
                            g.this.e.post(new Runnable() {
                                public void run() {
                                    if (onFailureCallBack != null) {
                                        onFailureCallBack.onFailure(-1, "GeneralSecurityException");
                                    }
                                }
                            });
                            return;
                        }
                    }
                    if (!response.isSuccessful()) {
                        handler = g.this.e;
                        runnable = new Runnable() {
                            public void run() {
                                String jSONObject = a2.toString();
                                if (onFailureCallBack != null) {
                                    OnFailureCallBack onFailureCallBack = onFailureCallBack;
                                    int code = response.code();
                                    onFailureCallBack.onFailure(code, "code = " + response.code() + " msg = " + response.message() + " details = " + jSONObject);
                                }
                            }
                        };
                    } else if (a2.has(SocializeProtocolConstants.PROTOCOL_KEY_MSG) && "conversation not found".equals(a2.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG))) {
                        g.this.e.post(new Runnable() {
                            public void run() {
                                if (onFailureCallBack != null) {
                                    onFailureCallBack.onFailure(ErrorCode.CONV_END, "conversation not found");
                                }
                            }
                        });
                        return;
                    } else if (!a2.has("success") || a2.optBoolean("success") || TextUtils.equals(a2.optString("result"), "queueing")) {
                        bVar.a(a2, response);
                        return;
                    } else {
                        response.code();
                        a2.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
                        if (a2.optBoolean("black")) {
                            i = ErrorCode.BLACKLIST;
                            str = "blacklist state";
                        } else {
                            i = ErrorCode.NO_AGENT_ONLINE;
                            str = "no agent online";
                            if (request.url().toString().contains("https://eco-api.meiqia.com/client/send_msg")) {
                                i = 20009;
                            }
                        }
                        handler = g.this.e;
                        runnable = new Runnable() {
                            public void run() {
                                if (onFailureCallBack != null) {
                                    onFailureCallBack.onFailure(i, str);
                                }
                            }
                        };
                    }
                    handler.post(runnable);
                }
            }
        });
    }

    private void a(boolean z, String str, String str2, Map<String, Object> map, Map<String, String> map2, b bVar, final OnFailureCallBack onFailureCallBack) {
        RequestBody requestBody;
        try {
            Request.Builder b2 = b(str);
            if (map2 != null) {
                for (Map.Entry next : map2.entrySet()) {
                    b2.addHeader((String) next.getKey(), (String) next.getValue());
                }
            }
            if (!z) {
                requestBody = RequestBody.create(b, com.meiqia.core.a.c.a((Map<?, ?>) map).toString());
                b2.removeHeader("Authorization");
            } else {
                requestBody = RequestBody.create(b, a(map));
            }
            HashMap hashMap = new HashMap();
            if (d.a != null) {
                hashMap.put("ent_id", d.a.getEnterpriseId());
            }
            hashMap.put("src", "android_sdk");
            b2.url(str2 + com.meiqia.core.a.c.a(str2, (Map<String, String>) hashMap)).post(requestBody);
            a(b2.build(), bVar, onFailureCallBack);
        } catch (Exception unused) {
            if (onFailureCallBack != null) {
                this.e.post(new Runnable() {
                    public void run() {
                        onFailureCallBack.onFailure(-1, "GeneralSecurityException");
                    }
                });
            }
        }
    }

    private void a(boolean z, String str, Map<String, Object> map, b bVar, OnFailureCallBack onFailureCallBack) {
        a(z, e(), str, map, (Map<String, String>) null, bVar, onFailureCallBack);
    }

    public static String b() {
        return MQMessage.TYPE_SDK;
    }

    private Request.Builder b(String str) {
        Request.Builder builder = new Request.Builder();
        String str2 = MQManager.a + ":" + str + ":" + (((System.currentTimeMillis() / 1000) + 60) + "");
        String replaceAll = ("Mozilla/5.0 (Linux; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " " + Build.DEVICE + ") MeiqiaSDK/ Source/SDK " + MQManager.getMeiqiaSDKVersion() + " Language/" + Locale.getDefault().getLanguage()).replaceAll("[^\\x00-\\x7F]", "");
        builder.addHeader("Authorization", str2);
        builder.addHeader("User-Agent", replaceAll);
        builder.addHeader("app_version", MQManager.getMeiqiaSDKVersion());
        builder.addHeader("app_platform", "android_sdk");
        builder.addHeader("app_channel", b());
        if (a) {
            builder.addHeader("alpha", "true");
        }
        return builder;
    }

    private void b(String str, @Nullable Map<String, String> map, b bVar, final OnFailureCallBack onFailureCallBack) {
        try {
            if (!(d.a == null || map == null)) {
                map.put("src", "android_sdk");
                map.put("ent_id", d.a.getEnterpriseId());
            }
            String a2 = com.meiqia.core.a.c.a(str, map);
            Request.Builder f2 = f();
            if (bVar != null && (bVar instanceof c)) {
                f2.removeHeader("Authorization");
            }
            f2.tag(str);
            a(f2.url(str + a2).get().build(), bVar, onFailureCallBack);
        } catch (Exception unused) {
            this.e.post(new Runnable() {
                public void run() {
                    onFailureCallBack.onFailure(-1, "GeneralSecurityException");
                }
            });
        }
    }

    private void c(String str, Map<String, Object> map, b bVar, final OnFailureCallBack onFailureCallBack) {
        try {
            a(f().url(str).put(RequestBody.create(b, a(map))).build(), bVar, onFailureCallBack);
        } catch (Exception unused) {
            this.e.post(new Runnable() {
                public void run() {
                    onFailureCallBack.onFailure(-1, "GeneralSecurityException");
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r5 = this;
            com.meiqia.core.g$1 r0 = new com.meiqia.core.g$1
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
            com.meiqia.core.g$12 r1 = new com.meiqia.core.g$12
            r1.<init>()
            okhttp3.OkHttpClient$Builder r0 = r0.hostnameVerifier(r1)
            okhttp3.OkHttpClient r0 = r0.build()
            d = r0
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.g.d():void");
    }

    private String e() {
        return d.a != null ? d.a.getTrackId() : "0";
    }

    private Request.Builder f() {
        return b(e());
    }

    public void a(long j2, int i2, final OnEvaluateRobotAnswerCallback onEvaluateRobotAnswerCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", d.a.getTrackId());
        hashMap.put("ent_id", d.a.getEnterpriseId());
        hashMap.put("useful", Integer.valueOf(i2));
        hashMap.put("question_id", Long.valueOf(j2));
        a(false, "https://eco-api.meiqia.com/knowledge/questions/" + j2 + "/evaluate", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                onEvaluateRobotAnswerCallback.onSuccess(jSONObject.optString("message"));
            }
        }, (OnFailureCallBack) onEvaluateRobotAnswerCallback);
    }

    public void a(long j2, long j3, String str, long j4, final SimpleCallback simpleCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("conversation_id", Long.valueOf(j2));
        hashMap.put("msg_id", Long.valueOf(j3));
        hashMap.put("track_id", str);
        hashMap.put("ent_id", Long.valueOf(j4));
        a(false, "https://eco-api.meiqia.com/client/file_downloaded", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                if (simpleCallback != null) {
                    simpleCallback.onSuccess();
                }
            }
        }, (OnFailureCallBack) simpleCallback);
    }

    public void a(long j2, final e eVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", d.a.getTrackId());
        b("https://eco-api.meiqia.com/client/tickets_v2/" + j2, hashMap, new b() {
            public void a(JSONObject jSONObject, Response response) {
                eVar.a(jSONObject);
            }
        }, eVar);
    }

    public void a(MQMessage mQMessage, final File file, final OnProgressCallback onProgressCallback) {
        b(mQMessage.getMedia_url(), (Map<String, String>) null, new c() {
            public void a(JSONObject jSONObject, Response response) {
                g.this.a(file, response, onProgressCallback);
            }
        }, onProgressCallback);
    }

    public void a(OnGetClientCallback onGetClientCallback) {
        a((String) null, onGetClientCallback);
    }

    public void a(final SimpleCallback simpleCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", d.a.getTrackId());
        hashMap.put("ent_id", d.a.getEnterpriseId());
        a("https://eco-api.meiqia.com/client/end_conversation", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                simpleCallback.onSuccess();
            }
        }, (OnFailureCallBack) simpleCallback);
    }

    public void a(final d dVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", d.a.getTrackId());
        hashMap.put("ent_id", d.a.getEnterpriseId());
        b("https://eco-api.meiqia.com/client/queue/position", hashMap, new b() {
            public void a(JSONObject jSONObject, Response response) {
                dVar.a(jSONObject.optInt("position", -1));
            }
        }, dVar);
    }

    public void a(final e eVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("enterprise_id", d.a.getEnterpriseId());
        b("https://eco-api.meiqia.com/client/tickets_v2/categories", hashMap, new b() {
            public void a(JSONObject jSONObject, Response response) {
                eVar.a(jSONObject);
            }
        }, eVar);
    }

    public void a(File file, final b bVar, OnFailureCallBack onFailureCallBack) {
        String str = "";
        if (d.a != null) {
            str = d.a.getEnterpriseId();
        }
        a(new Request.Builder().url("https://eco-api-upload.meiqia.com/upload?user_id=" + e() + "&ent_id=" + str).post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", "file.jpeg", RequestBody.create(MediaType.parse("image/jpeg"), file)).build()).build(), (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                bVar.a(jSONObject, response);
            }
        }, onFailureCallBack);
    }

    public void a(String str) {
        for (Call next : d.dispatcher().queuedCalls()) {
            if (next.request().tag().equals(str)) {
                next.cancel();
            }
        }
    }

    public void a(String str, int i2) {
        String trackId = d.a.getTrackId();
        String enterpriseId = d.a.getEnterpriseId();
        HashMap hashMap = new HashMap();
        if (i2 != -1) {
            hashMap.put(MQCollectInfoActivity.AGENT_ID, Integer.valueOf(i2));
        }
        hashMap.put("track_id", trackId);
        hashMap.put("ent_id", enterpriseId);
        hashMap.put("type", "text");
        hashMap.put("content", str);
        a("https://eco-api.meiqia.com/client/inputting", (Map<String, Object>) hashMap, (b) null, (OnFailureCallBack) null);
    }

    public void a(String str, int i2, int i3, int i4, String str2, int i5, final OnGetMessageListCallback onGetMessageListCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("limit", i2 + "");
        hashMap.put("ent_id", i4 + "");
        hashMap.put("last_message_created_on", str2);
        hashMap.put("ascending", i5 + "");
        hashMap.put("_time", String.valueOf(System.currentTimeMillis()));
        b("https://eco-api.meiqia.com/conversation/" + str + "/messages_streams", hashMap, new b() {
            public void a(JSONObject jSONObject, Response response) {
                List<MQMessage> a2 = com.meiqia.core.a.c.a(jSONObject.optJSONArray("messages"));
                Collections.sort(a2, new com.meiqia.core.a.h());
                onGetMessageListCallback.onSuccess(a2);
            }
        }, onGetMessageListCallback);
    }

    public void a(String str, int i2, String str2, final SimpleCallback simpleCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("app_key", MQManager.a);
        hashMap.put("level", Integer.valueOf(i2));
        hashMap.put("content", str2);
        a(false, "https://eco-api.meiqia.com/conversation/" + str + "/evaluation", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                simpleCallback.onSuccess();
            }
        }, (OnFailureCallBack) simpleCallback);
    }

    public void a(String str, final OnGetClientCallback onGetClientCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("app_key", MQManager.a);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("track_id", str);
        }
        a(false, "https://eco-api.meiqia.com/sdk/init_sdk_user", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                String optString = jSONObject.optString("track_id");
                String optString2 = jSONObject.optString("enterprise_id");
                String optString3 = jSONObject.optString("browser_id");
                String optString4 = jSONObject.optString("visit_page_id");
                String optString5 = jSONObject.optString("visit_id");
                onGetClientCallback.onSuccess(true, jSONObject.optString("aes_key"), optString, optString2, optString3, optString4, optString5);
            }
        }, (OnFailureCallBack) onGetClientCallback);
    }

    public void a(String str, final OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("device_token", str);
        a("https://eco-api.meiqia.com/client/device_token", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                onRegisterDeviceTokenCallback.onSuccess();
            }
        }, (OnFailureCallBack) onRegisterDeviceTokenCallback);
    }

    public void a(String str, String str2, String str3, final SimpleCallback simpleCallback) {
        final File file = new File(str2, str3);
        d.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                simpleCallback.onFailure(0, "download failed");
            }

            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    BufferedSink buffer = Okio.buffer(Okio.sink(file));
                    buffer.writeAll(response.body().source());
                    buffer.close();
                    simpleCallback.onSuccess();
                    return;
                }
                simpleCallback.onFailure(0, "download failed");
            }
        });
    }

    public void a(final String str, Map<String, Object> map, final SimpleCallback simpleCallback) {
        a(true, str, "https://eco-api.meiqia.com/sdk/statistics", map, (Map<String, String>) null, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                com.meiqia.core.a.f.b("DvcInfo " + str);
                simpleCallback.onSuccess();
            }
        }, (OnFailureCallBack) simpleCallback);
    }

    public void a(String str, Map<String, Object> map, final h hVar) {
        a(str, map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                JSONObject optJSONObject = jSONObject.optJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
                String optString = optJSONObject.optString("created_on");
                long optLong = optJSONObject.optLong("id");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("sensitive_content");
                hVar.a(optString, optLong, (optJSONObject2 == null || !optJSONObject2.optBoolean("contains_sensitive_words", false)) ? null : optJSONObject2.toString());
            }
        }, (OnFailureCallBack) hVar);
    }

    public void a(String str, Map<String, Object> map, Map<String, String> map2, final SimpleCallback simpleCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", str);
        hashMap.put("ent_id", d.a.getEnterpriseId());
        hashMap.put("data", map);
        a(false, e(), "https://eco-api.meiqia.com/client/forms", (Map<String, Object>) hashMap, map2, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                simpleCallback.onSuccess();
            }
        }, (OnFailureCallBack) simpleCallback);
    }

    public void a(Map<String, Object> map, long j2, final f fVar) {
        a(false, "https://eco-api.meiqia.com/client/tickets_v2/" + j2 + "/replies", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                fVar.a(jSONObject.optString("created_at"), jSONObject.optLong("ticket_id"));
            }
        }, (OnFailureCallBack) fVar);
    }

    public void a(Map<String, Object> map, final OnClientInfoCallback onClientInfoCallback) {
        com.meiqia.core.a.f.b("setAttrs");
        c("https://eco-api.meiqia.com/client/attrs", map, new b() {
            public void a(JSONObject jSONObject, Response response) {
                if (onClientInfoCallback != null) {
                    onClientInfoCallback.onSuccess();
                }
            }
        }, onClientInfoCallback);
    }

    public void a(Map<String, String> map, final OnGetMessageListCallback onGetMessageListCallback) {
        b("https://eco-api.meiqia.com/conversation/" + d.a.getTrackId() + "/messages_streams", map, new b() {
            public void a(JSONObject jSONObject, Response response) {
                List<MQMessage> a2 = com.meiqia.core.a.c.a(jSONObject.optJSONArray("messages"));
                Collections.sort(a2, new com.meiqia.core.a.h());
                onGetMessageListCallback.onSuccess(a2);
            }
        }, onGetMessageListCallback);
    }

    public void a(Map<String, Object> map, final SuccessCallback successCallback) {
        map.put("track_id", d.a.getTrackId());
        a(false, "https://eco-api.meiqia.com/client/" + d.a.getTrackId() + "/reply_card", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                g.this.e.post(new Runnable() {
                    public void run() {
                        successCallback.onSuccess();
                    }
                });
            }
        }, (OnFailureCallBack) successCallback);
    }

    public void a(final Map<String, Object> map, final a aVar) {
        a("https://eco-api.meiqia.com/scheduler", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                boolean equals = TextUtils.equals(jSONObject.optString("result"), "queueing");
                if (equals) {
                    aVar.a(equals, (MQAgent) null, (MQConversation) null, (List<MQMessage>) null);
                } else {
                    JSONObject optJSONObject = jSONObject.optJSONObject("agent");
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("conv");
                    if (optJSONObject == null || optJSONObject2 == null) {
                        aVar.onFailure(ErrorCode.UNKNOWN, "agent == null or conv == null");
                        return;
                    }
                    MQAgent c2 = com.meiqia.core.a.c.c(optJSONObject);
                    MQConversation d = com.meiqia.core.a.c.d(optJSONObject2);
                    d.setAgent_id(c2.getId());
                    aVar.a(false, c2, d, com.meiqia.core.a.c.a(jSONObject.optJSONArray("messages")));
                }
                com.meiqia.core.a.f.b("scheduler " + map.get("track_id"));
            }
        }, (OnFailureCallBack) aVar);
    }

    public void a(Map<String, Object> map, final e eVar) {
        a(false, "https://eco-api.meiqia.com/sdk/init", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                if (jSONObject != null) {
                    eVar.a(jSONObject);
                } else {
                    g.this.e.post(new Runnable() {
                        public void run() {
                            eVar.onFailure(ErrorCode.UNKNOWN, "UNKNOW");
                        }
                    });
                }
            }
        }, (OnFailureCallBack) eVar);
    }

    public void a(Map<String, Object> map, final f fVar) {
        a(false, "https://eco-api.meiqia.com/client/tickets_v2", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                fVar.a(jSONObject.optString("created_at"), jSONObject.optLong("id"));
            }
        }, (OnFailureCallBack) fVar);
    }

    public void a(Map<String, Object> map, final C0000g gVar) {
        a("https://eco-api.meiqia.com/client/tickets", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                gVar.a(jSONObject.optJSONArray("replies"));
            }
        }, (OnFailureCallBack) gVar);
    }

    public void a(Map<String, String> map, String str, final OnGetMessageListCallback onGetMessageListCallback) {
        b("https://eco-api.meiqia.com/client/tickets_v2/" + str + "/replies", map, new b() {
            public void a(JSONObject jSONObject, Response response) {
                List<MQMessage> a2 = com.meiqia.core.a.c.a(jSONObject.optJSONArray("replies"));
                Collections.sort(a2, new com.meiqia.core.a.h());
                onGetMessageListCallback.onSuccess(a2);
            }
        }, onGetMessageListCallback);
    }

    public void b(File file, final b bVar, OnFailureCallBack onFailureCallBack) {
        String str = "";
        if (d.a != null) {
            str = d.a.getEnterpriseId();
        }
        file.exists();
        a(new Request.Builder().url("https://eco-api-upload.meiqia.com/upload?user_id=" + e() + "&ent_id=" + str).post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", "file.amr", RequestBody.create(MediaType.parse("audio/amr"), file)).build()).build(), (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                bVar.a(jSONObject, response);
            }
        }, onFailureCallBack);
    }

    public void b(String str, final OnGetClientCallback onGetClientCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("dev_client_id", str);
        a("https://eco-api.meiqia.com/sdk/get_dev_client_id", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                boolean optBoolean = jSONObject.optBoolean("found_client");
                String optString = jSONObject.optString("track_id");
                String optString2 = jSONObject.optString("enterprise_id");
                String optString3 = jSONObject.optString("browser_id");
                String optString4 = jSONObject.optString("visit_page_id");
                String optString5 = jSONObject.optString("visit_id");
                onGetClientCallback.onSuccess(optBoolean, jSONObject.optString("aes_key"), optString, optString2, optString3, optString4, optString5);
            }
        }, (OnFailureCallBack) onGetClientCallback);
    }

    public void b(String str, final OnRegisterDeviceTokenCallback onRegisterDeviceTokenCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("device_token", str);
        a("https://eco-api.meiqia.com/sdk/refresh_push_info", (Map<String, Object>) hashMap, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                onRegisterDeviceTokenCallback.onSuccess();
            }
        }, (OnFailureCallBack) onRegisterDeviceTokenCallback);
    }

    public void b(Map<String, Object> map, final OnClientInfoCallback onClientInfoCallback) {
        com.meiqia.core.a.f.b("setEvents");
        a(false, "https://eco-api.meiqia.com/client/client_events", map, (b) new b() {
            public void a(JSONObject jSONObject, Response response) {
                if (onClientInfoCallback != null) {
                    onClientInfoCallback.onSuccess();
                }
            }
        }, (OnFailureCallBack) onClientInfoCallback);
    }

    public void c() {
        d.dispatcher().cancelAll();
    }
}
