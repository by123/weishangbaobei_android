package com.umeng.socialize.net.analytics;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.base.SocializeReseponse;
import java.util.Map;
import org.json.JSONObject;

public class b extends SocializeReseponse {
    public Map<SHARE_MEDIA, Integer> a;
    public String b;
    public SHARE_MEDIA c;

    public b(Integer num, JSONObject jSONObject) {
        super(jSONObject);
    }

    public String toString() {
        return "ShareMultiResponse [mInfoMap=" + this.a + ", mWeiboId=" + this.b + ", mMsg=" + this.mMsg + ", mStCode=" + this.mStCode + "]";
    }
}
