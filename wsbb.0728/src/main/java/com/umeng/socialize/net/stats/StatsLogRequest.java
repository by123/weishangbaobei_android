package com.umeng.socialize.net.stats;

import android.content.Context;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.URequest;
import java.util.HashMap;
import java.util.Map;

public class StatsLogRequest extends StatsRequest {
    public StatsLogRequest(Class<? extends SocializeReseponse> cls) {
        super((Context) null, "", cls, 0, URequest.RequestMethod.POST);
        this.mRequestPath += "?test=";
        StringBuilder sb = new StringBuilder();
        sb.append(this.mRequestPath);
        sb.append(SocializeConstants.DEBUG_MODE ? "1" : "0");
        this.mRequestPath = sb.toString();
        this.mMimeType = URequest.MIME.JSON;
    }

    public Map<String, Object> getBodyPair() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mParams);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return this.mRequestPath;
    }

    public void onPrepareRequest() {
    }

    public String toGetUrl() {
        return generateGetURL(getBaseUrl(), buildParams());
    }
}
