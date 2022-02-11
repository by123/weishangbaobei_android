package com.umeng.weixin.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.stub.StubApp;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import com.umeng.weixin.umengwx.WeChat;
import com.umeng.weixin.umengwx.e;
import com.umeng.weixin.umengwx.h;
import com.umeng.weixin.umengwx.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UmengWXHandler extends UMSSOHandler {
    public static final int WXSceneFavorite = 2;
    public static final int WXSceneSession = 0;
    public static final int WXSceneTimeline = 1;
    private static final int h = 604800;
    private static final int i = 1;
    private static final int j = 2;
    private static String l = "snsapi_userinfo,snsapi_friend,snsapi_message";
    private static final String m = "refresh_token_expires";
    private static final String n = "nickname";
    private static final String o = "language";
    private static final String p = "headimgurl";
    private static final String q = "sex";
    private static final String r = "privilege";
    private static final String s = "errcode";
    private static final String t = "errmsg";
    private static final String u = "40001";
    private static final String v = "40030";
    private static final String w = "42002";
    private q a;
    private WeChat b;
    private String c = "6.4.5";
    private s d;
    private PlatformConfig.APPIDPlatform e;
    /* access modifiers changed from: private */
    public SHARE_MEDIA f = SHARE_MEDIA.WEIXIN;
    /* access modifiers changed from: private */
    public UMAuthListener g;
    /* access modifiers changed from: private */
    public UMShareListener k;
    private e x = new l(this);

    private ShareContent a(ShareContent shareContent) {
        if (shareContent.getShareType() == 128 && getWXAppSupportAPI() < 620756993) {
            UMMin uMMin = (UMMin) shareContent.mMedia;
            UMWeb uMWeb = new UMWeb(uMMin.toUrl());
            uMWeb.setThumb(uMMin.getThumbImage());
            uMWeb.setDescription(uMMin.getDescription());
            uMWeb.setTitle(uMMin.getTitle());
            shareContent.mMedia = uMWeb;
        }
        return shareContent;
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        if (this.a != null) {
            this.a.a(bundle).k();
        }
    }

    /* access modifiers changed from: private */
    public void a(UMAuthListener uMAuthListener) {
        Runnable runnable;
        String f2 = f();
        String h2 = h();
        String a2 = r.a("https://api.weixin.qq.com/sns/userinfo?access_token=" + h2 + "&openid=" + f2 + "&lang=zh_CN");
        if (TextUtils.isEmpty(a2) || a2.startsWith("##")) {
            QueuedWork.runInMain(new b(this, uMAuthListener, a2));
            return;
        }
        Map e2 = e(a2);
        if (e2 == null) {
            QueuedWork.runInMain(new c(this, uMAuthListener, a2));
            return;
        }
        if (!e2.containsKey(s)) {
            runnable = new e(this, uMAuthListener, e2);
        } else if (((String) e2.get(s)).equals(u)) {
            c();
            authorize(uMAuthListener);
            return;
        } else {
            runnable = new d(this, uMAuthListener, e2);
        }
        QueuedWork.runInMain(runnable);
    }

    private void a(String str) {
        a(d(r.a(str)));
    }

    private void a(String str, UMAuthListener uMAuthListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        sb.append("appid=");
        sb.append(this.e.appId);
        sb.append("&secret=");
        sb.append(this.e.appkey);
        sb.append("&code=");
        sb.append(str);
        sb.append("&grant_type=authorization_code");
        new Thread(new m(this, sb, uMAuthListener)).start();
    }

    private boolean a() {
        if (this.a != null) {
            return this.a.h();
        }
        return false;
    }

    private Map b(String str) {
        try {
            Map<String, String> jsonToMap = SocializeUtils.jsonToMap(r.a("https://api.weixin.qq.com/sns/oauth2/refresh_token?" + "appid=" + this.e.appId + "&grant_type=refresh_token" + "&refresh_token=" + str));
            try {
                jsonToMap.put("unionid", g());
                return jsonToMap;
            } catch (Exception unused) {
                return jsonToMap;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    private boolean b() {
        if (this.a != null) {
            return this.a.e();
        }
        return false;
    }

    private String c(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }

    private void c() {
        if (this.a != null) {
            this.a.j();
        }
    }

    /* access modifiers changed from: private */
    public Bundle d(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            return bundle;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            bundle.putLong(m, 604800);
            bundle.putString("accessToken", bundle.getString("access_token"));
            bundle.putString("expiration", bundle.getString("expires_in"));
            bundle.putString("refreshToken", bundle.getString("refresh_token"));
            bundle.putString(SocializeProtocolConstants.PROTOCOL_KEY_UID, bundle.getString("unionid"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return bundle;
    }

    private String d() {
        return this.a != null ? this.a.c() : "";
    }

    /* access modifiers changed from: private */
    public Map e() {
        if (this.a != null) {
            return this.a.d();
        }
        return null;
    }

    private Map e(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(s)) {
                hashMap.put(s, jSONObject.getString(s));
                hashMap.put(t, jSONObject.getString(t));
                return hashMap;
            }
            hashMap.put("openid", jSONObject.optString("openid"));
            hashMap.put("screen_name", jSONObject.optString(n));
            hashMap.put("name", jSONObject.optString(n));
            hashMap.put("language", jSONObject.optString("language"));
            hashMap.put("city", jSONObject.optString("city"));
            hashMap.put("province", jSONObject.optString("province"));
            hashMap.put(com.umeng.commonsdk.proguard.e.N, jSONObject.optString(com.umeng.commonsdk.proguard.e.N));
            hashMap.put("profile_image_url", jSONObject.optString(p));
            hashMap.put("iconurl", jSONObject.optString(p));
            hashMap.put("unionid", jSONObject.optString("unionid"));
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, jSONObject.optString("unionid"));
            hashMap.put("gender", getGender(jSONObject.optString(q)));
            JSONArray jSONArray = jSONObject.getJSONArray(r);
            int length = jSONArray.length();
            if (length > 0) {
                String[] strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    strArr[i2] = jSONArray.get(i2).toString();
                }
                hashMap.put(r, strArr.toString());
            }
            hashMap.put("access_token", h());
            hashMap.put("refreshToken", d());
            hashMap.put("expires_in", String.valueOf(i()));
            hashMap.put("accessToken", h());
            hashMap.put("refreshToken", d());
            hashMap.put("expiration", String.valueOf(i()));
            return hashMap;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return Collections.emptyMap();
        }
    }

    private String f() {
        return this.a != null ? this.a.b() : "";
    }

    private String g() {
        return this.a != null ? this.a.a() : "";
    }

    private String h() {
        return this.a != null ? this.a.f() : "";
    }

    private long i() {
        if (this.a != null) {
            return this.a.g();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void a(i iVar) {
        if (iVar.a == 0) {
            a(iVar.e, this.g);
        } else if (iVar.a == -2) {
            if (this.g != null) {
                this.g.onCancel(SHARE_MEDIA.WEIXIN, 0);
            }
        } else if (iVar.a != -6) {
            CharSequence concat = TextUtils.concat(new CharSequence[]{"weixin auth error (", String.valueOf(iVar.a), "):", iVar.b});
            if (this.g != null) {
                UMAuthListener uMAuthListener = this.g;
                SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
                uMAuthListener.onError(share_media, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + concat.toString()));
            }
        } else if (this.g != null) {
            UMAuthListener uMAuthListener2 = this.g;
            SHARE_MEDIA share_media2 = SHARE_MEDIA.WEIXIN;
            uMAuthListener2.onError(share_media2, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + UmengText.errorWithUrl(UmengText.AUTH_DENIED, UrlUtil.WX_ERROR_SIGN)));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bc, code lost:
        r6.onError(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0029, code lost:
        r0.onError(r1, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.umeng.weixin.umengwx.k r6) {
        /*
            r5 = this;
            int r0 = r6.a
            switch(r0) {
                case -6: goto L_0x0092;
                case -5: goto L_0x006d;
                case -4: goto L_0x0005;
                case -3: goto L_0x0048;
                case -2: goto L_0x003b;
                case -1: goto L_0x0048;
                case 0: goto L_0x002e;
                default: goto L_0x0005;
            }
        L_0x0005:
            com.umeng.socialize.UMShareListener r0 = r5.k
            if (r0 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r0 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r1 = r5.f
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            com.umeng.socialize.bean.UmengErrorCode r4 = com.umeng.socialize.bean.UmengErrorCode.ShareFailed
            java.lang.String r4 = r4.getMessage()
            r3.append(r4)
            java.lang.String r6 = r6.b
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r2.<init>(r6)
        L_0x0029:
            r0.onError(r1, r2)
            goto L_0x00bf
        L_0x002e:
            com.umeng.socialize.UMShareListener r6 = r5.k
            if (r6 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r6 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r0 = r5.f
            r6.onResult(r0)
            goto L_0x00bf
        L_0x003b:
            com.umeng.socialize.UMShareListener r6 = r5.k
            if (r6 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r6 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r0 = r5.f
            r6.onCancel(r0)
            goto L_0x00bf
        L_0x0048:
            com.umeng.socialize.UMShareListener r0 = r5.k
            if (r0 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r0 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r1 = r5.f
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            com.umeng.socialize.bean.UmengErrorCode r4 = com.umeng.socialize.bean.UmengErrorCode.ShareFailed
            java.lang.String r4 = r4.getMessage()
            r3.append(r4)
            java.lang.String r6 = r6.b
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r2.<init>(r6)
            goto L_0x0029
        L_0x006d:
            com.umeng.socialize.UMShareListener r6 = r5.k
            if (r6 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r6 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r0 = r5.f
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.umeng.socialize.bean.UmengErrorCode r3 = com.umeng.socialize.bean.UmengErrorCode.ShareFailed
            java.lang.String r3 = r3.getMessage()
            r2.append(r3)
            java.lang.String r3 = com.umeng.socialize.utils.UmengText.VERSION_NOT_SUPPORT
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            goto L_0x00bc
        L_0x0092:
            com.umeng.socialize.UMShareListener r6 = r5.k
            if (r6 == 0) goto L_0x00bf
            com.umeng.socialize.UMShareListener r6 = r5.k
            com.umeng.socialize.bean.SHARE_MEDIA r0 = r5.f
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.umeng.socialize.bean.UmengErrorCode r3 = com.umeng.socialize.bean.UmengErrorCode.ShareFailed
            java.lang.String r3 = r3.getMessage()
            r2.append(r3)
            java.lang.String r3 = com.umeng.socialize.utils.UmengText.AUTH_DENIED
            java.lang.String r4 = "https://at.umeng.com/f8HHDi?cid=476"
            java.lang.String r3 = com.umeng.socialize.utils.UmengText.errorWithUrl(r3, r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
        L_0x00bc:
            r6.onError(r0, r1)
        L_0x00bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.weixin.handler.UmengWXHandler.a(com.umeng.weixin.umengwx.k):void");
    }

    public void authorize(UMAuthListener uMAuthListener) {
        this.g = uMAuthListener;
        this.f = this.e.getName();
        if (!isInstall()) {
            if (Config.isJumptoAppStore) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SocializeConstants.DOWN_URL_WX));
                ((Activity) this.mWeakAct.get()).startActivity(intent);
            }
            QueuedWork.runInMain(new j(this, uMAuthListener));
        } else if (a()) {
            if (!b()) {
                String d2 = d();
                a("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + this.e.appId + "&grant_type=refresh_token&refresh_token=" + d2);
            }
            Map b2 = b(d());
            if (!b2.containsKey(s) || (!((String) b2.get(s)).equals(v) && !((String) b2.get(s)).equals(w))) {
                QueuedWork.runInMain(new k(this, b2));
                return;
            }
            c();
            authorize(uMAuthListener);
        } else {
            h hVar = new h();
            hVar.c = l;
            hVar.d = "123";
            this.b.sendReq(hVar);
        }
    }

    public void deleteAuth(UMAuthListener uMAuthListener) {
        c();
        QueuedWork.runInMain(new i(this, uMAuthListener));
    }

    public String getGender(Object obj) {
        String string = ResContainer.getString(ContextUtil.getContext(), "umeng_socialize_male");
        String string2 = ResContainer.getString(ContextUtil.getContext(), "umeng_socialize_female");
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (obj.equals("m") || obj.equals("1") || obj.equals(UmengText.MAN)) ? string : (obj.equals("f") || obj.equals("2") || obj.equals(UmengText.WOMAN)) ? string2 : obj.toString();
        }
        if (!(obj instanceof Integer)) {
            return obj.toString();
        }
        Integer num = (Integer) obj;
        return num.intValue() == 1 ? string : num.intValue() == 2 ? string2 : obj.toString();
    }

    public void getPlatformInfo(UMAuthListener uMAuthListener) {
        if (getShareConfig().isNeedAuthOnGetUserInfo()) {
            c();
        }
        authorize(new o(this, uMAuthListener));
    }

    /* access modifiers changed from: protected */
    public String getToName() {
        return "wxsession";
    }

    public WeChat getWXApi() {
        return this.b;
    }

    public int getWXAppSupportAPI() {
        if (!isInstall()) {
            return 0;
        }
        try {
            return getContext().getPackageManager().getApplicationInfo("com.tencent.mm", ShareContent.MINAPP_STYLE).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    public e getWXEventHandler() {
        return this.x;
    }

    public boolean isAuthorize() {
        if (this.a != null) {
            return this.a.i();
        }
        return false;
    }

    public boolean isInstall() {
        return this.b.isWXAppInstalled();
    }

    public boolean isSupportAuth() {
        return true;
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        super.onCreate(context, platform);
        this.a = new q(StubApp.getOrigApplicationContext(context.getApplicationContext()), "weixin");
        this.e = (PlatformConfig.APPIDPlatform) platform;
        this.b = new WeChat(StubApp.getOrigApplicationContext(context.getApplicationContext()), this.e.appId);
        this.b.registerApp(this.e.appId);
        Log.um("wechat simplify:" + this.c);
    }

    public void release() {
        super.release();
        this.g = null;
    }

    public void setAuthListener(UMAuthListener uMAuthListener) {
        super.setAuthListener(uMAuthListener);
        this.g = uMAuthListener;
    }

    public boolean share(ShareContent shareContent, UMShareListener uMShareListener) {
        this.f = this.e.getName();
        if (!isInstall()) {
            if (Config.isJumptoAppStore) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SocializeConstants.DOWN_URL_WX));
                ((Activity) this.mWeakAct.get()).startActivity(intent);
            }
            QueuedWork.runInMain(new a(this, uMShareListener));
            return false;
        }
        this.d = new s(a(shareContent));
        if (this.d != null && this.d.getmStyle() == 64 && (this.f == SHARE_MEDIA.WEIXIN_CIRCLE || this.f == SHARE_MEDIA.WEIXIN_FAVORITE)) {
            QueuedWork.runInMain(new g(this, uMShareListener));
            Toast.makeText(getContext(), UmengText.WX_CIRCLE_NOT_SUPPORT_EMOJ, 0).show();
            return false;
        }
        this.k = uMShareListener;
        return shareTo(new s(shareContent));
    }

    public boolean shareTo(s sVar) {
        Bundle a2 = sVar.a();
        a2.putString("_wxapi_basereq_transaction", c(this.d.getStrStyle()));
        if (!TextUtils.isEmpty(a2.getString("error"))) {
            QueuedWork.runInMain(new h(this, a2));
            return false;
        }
        switch (f.a[this.f.ordinal()]) {
            case 1:
                a2.putInt("_wxapi_sendmessagetowx_req_scene", 0);
                break;
            case 2:
                a2.putInt("_wxapi_sendmessagetowx_req_scene", 1);
                break;
            default:
                a2.putInt("_wxapi_sendmessagetowx_req_scene", 2);
                break;
        }
        this.b.pushare(a2);
        return true;
    }
}
