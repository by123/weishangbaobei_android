package com.umeng.socialize.net;

import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.sina.params.ShareRequestParam;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class f extends e {
    public f(String str, String str2, String str3) {
        super(ShareRequestParam.UPLOAD_PIC_URL);
        this.mMethod = URequest.RequestMethod.POST;
        this.mResponseClz = g.class;
        this.postStyle = URequest.PostStyle.APPLICATION;
        addStringParams(ShareRequestParam.REQ_PARAM_AID, str);
        addStringParams("oauth_sign", a(str, a(), str2, str3));
        addStringParams("oauth_timestamp", a());
    }

    public Map<String, Object> buildParams() {
        return null;
    }

    public Map<String, Object> getBodyPair() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mParams);
        return hashMap;
    }

    public String toGetUrl() {
        return null;
    }

    public JSONObject toJson() {
        return null;
    }
}
