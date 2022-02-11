package com.umeng.qq.handler;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class j implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ i c;

    j(i iVar, Object obj, Bundle bundle) {
        this.c = iVar;
        this.a = obj;
        this.b = bundle;
    }

    public void run() {
        String a2 = this.c.b.a("https://graph.qq.com/oauth2.0/me?access_token=" + this.c.b.b() + "&unionid=1");
        if (!TextUtils.isEmpty(a2)) {
            try {
                JSONObject jSONObject = new JSONObject(a2.replace("callback", "").replace("(", "").replace(")", ""));
                String optString = jSONObject.optString("unionid");
                String optString2 = jSONObject.optString("openid");
                if (this.c.b.p != null) {
                    this.c.b.p.c(optString2);
                    this.c.b.p.b(optString);
                    this.c.b.p.f();
                }
                String optString3 = jSONObject.optString("error_description");
                if (!TextUtils.isEmpty(optString3)) {
                    Log.um(optString3);
                }
            } catch (JSONException e) {
                Log.um(e.getMessage());
                e.printStackTrace();
            }
        }
        this.c.b.initOpenidAndToken((JSONObject) this.a);
        Map<String, String> bundleTomap = SocializeUtils.bundleTomap(this.b);
        bundleTomap.put("unionid", this.c.b.c());
        if (this.c.a != null) {
            QueuedWork.runInMain(new k(this, bundleTomap));
        }
        this.c.b.uploadAuthData(this.b);
    }
}
