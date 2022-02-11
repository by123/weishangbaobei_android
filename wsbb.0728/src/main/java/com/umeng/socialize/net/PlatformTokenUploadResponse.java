package com.umeng.socialize.net;

import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class PlatformTokenUploadResponse extends SocializeReseponse {
    public String mExpiresIn;
    public String mTencentUid;

    public PlatformTokenUploadResponse(Integer num, JSONObject jSONObject) {
        super(jSONObject);
    }

    private void a() {
        if (this.mJsonData != null) {
            try {
                JSONObject jSONObject = this.mJsonData.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_TENCENT);
                if (jSONObject != null) {
                    String optString = jSONObject.optString(SocializeConstants.TENCENT_UID);
                    if (!TextUtils.isEmpty(optString)) {
                        this.mTencentUid = optString;
                    }
                }
            } catch (JSONException e) {
            }
        }
    }

    private void b() {
    }

    public void parseJsonObject() {
        super.parseJsonObject();
        a();
        b();
    }
}
