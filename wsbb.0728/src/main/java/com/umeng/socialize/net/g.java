package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.sina.params.ShareRequestParam;
import org.json.JSONObject;

public class g extends SocializeReseponse {
    public int a;
    public String b;

    public g(Integer num, JSONObject jSONObject) {
        super(num, jSONObject);
        this.a = jSONObject.optInt(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, -2);
        this.b = jSONObject.optString("data", "");
    }
}
