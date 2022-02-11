package com.umeng.qq.handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.qq.tencent.Tencent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class b extends UMSSOHandler {
    protected static final String f = "nickname";
    protected static final String g = "figureurl_qq_2";
    protected static final String h = "is_yellow_year_vip";
    protected static final String i = "yellow_vip_level";
    protected static final String j = "msg";
    protected static final String k = "vip";
    protected static final String l = "level";
    protected static final String m = "ret";
    protected static final String n = "is_yellow_vip";
    private static final String o = "UmengQBaseHandler";
    protected ProgressDialog a = null;
    protected String b = "6.4.5";
    protected UMAuthListener c;
    public PlatformConfig.APPIDPlatform config = null;
    protected Tencent d;
    protected UMShareListener e;

    /* access modifiers changed from: protected */
    public Bundle a(Object obj) {
        JSONObject jSONObject;
        Bundle bundle = new Bundle();
        if (obj != null) {
            String trim = obj.toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                try {
                    jSONObject = new JSONObject(trim);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    bundle.putString("auth_time", jSONObject.optString("auth_time", ""));
                    bundle.putString("pay_token", jSONObject.optString("pay_token", ""));
                    bundle.putString("pf", jSONObject.optString("pf", ""));
                    bundle.putString(m, String.valueOf(jSONObject.optInt(m, -1)));
                    bundle.putString("sendinstall", jSONObject.optString("sendinstall", ""));
                    bundle.putString("page_type", jSONObject.optString("page_type", ""));
                    bundle.putString("appid", jSONObject.optString("appid", ""));
                    bundle.putString("openid", jSONObject.optString("openid", ""));
                    bundle.putString(SocializeProtocolConstants.PROTOCOL_KEY_UID, jSONObject.optString("openid", ""));
                    bundle.putString("expires_in", jSONObject.optString("expires_in", ""));
                    bundle.putString("pfkey", jSONObject.optString("pfkey", ""));
                    bundle.putString("access_token", jSONObject.optString("access_token", ""));
                    bundle.putString("accessToken", jSONObject.optString("access_token", ""));
                }
            }
        }
        return bundle;
    }

    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        Log.um("qq simplify version:" + this.b);
        this.config = (PlatformConfig.APPIDPlatform) platform;
        Log.d("appid", "appid qq:" + this.config.appId);
        this.d = Tencent.createInstance(this.config.appId, context);
        if (this.d == null) {
            Log.e(o, UmengText.QQ_TENCENT_ERROR);
            throw new SocializeException(UmengText.QQ_TENCENT_ERROR);
        }
    }
}
