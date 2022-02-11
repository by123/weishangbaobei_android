package com.umeng.qq.handler;

import android.text.TextUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

class n implements Runnable {
    final /* synthetic */ UMAuthListener a;
    final /* synthetic */ UmengQQHandler b;

    n(UmengQQHandler umengQQHandler, UMAuthListener uMAuthListener) {
        this.b = umengQQHandler;
        this.a = uMAuthListener;
    }

    public void run() {
        try {
            JSONObject d = this.b.g();
            HashMap hashMap = new HashMap();
            hashMap.put("screen_name", d.optString("nickname"));
            hashMap.put("name", d.optString("nickname"));
            hashMap.put("gender", this.b.getGender(d.optString("gender")));
            hashMap.put("profile_image_url", d.optString("figureurl_qq_2"));
            hashMap.put("iconurl", d.optString("figureurl_qq_2"));
            hashMap.put("is_yellow_year_vip", d.optString("is_yellow_year_vip"));
            hashMap.put("yellow_vip_level", d.optString("yellow_vip_level"));
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MSG, d.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            hashMap.put("city", d.optString("city"));
            hashMap.put("vip", d.optString("vip"));
            hashMap.put("level", d.optString("level"));
            hashMap.put("ret", d.optString("ret"));
            hashMap.put("province", d.optString("province"));
            hashMap.put("is_yellow_vip", d.optString("is_yellow_vip"));
            hashMap.put("openid", this.b.e());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_UID, this.b.e());
            hashMap.put("access_token", this.b.b());
            hashMap.put("expires_in", this.b.d() + "");
            hashMap.put("accessToken", this.b.b());
            hashMap.put("expiration", this.b.d() + "");
            hashMap.put("unionid", this.b.c());
            String str = (String) hashMap.get("ret");
            if (TextUtils.isEmpty(str) || !str.equals("0")) {
                QueuedWork.runInMain(new p(this, str, hashMap));
            } else {
                QueuedWork.runInMain(new o(this, hashMap));
            }
        } catch (JSONException e) {
            QueuedWork.runInMain(new q(this, e));
            e.printStackTrace();
        }
    }
}
