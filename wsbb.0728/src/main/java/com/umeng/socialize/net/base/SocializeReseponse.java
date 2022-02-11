package com.umeng.socialize.net.base;

import android.text.TextUtils;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.UResponse;
import com.umeng.socialize.utils.Log;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SocializeReseponse extends UResponse {
    protected static final String TAG = "SocializeReseponse";
    private int mHttpCode;
    protected JSONObject mJsonData;
    public String mMsg;
    public int mStCode;

    public SocializeReseponse(Integer num, JSONObject jSONObject) {
        this(jSONObject);
        this.mHttpCode = num == null ? -1 : num.intValue();
    }

    public SocializeReseponse(JSONObject jSONObject) {
        super(jSONObject);
        this.mStCode = StatusCode.ST_CODE_SDK_NORESPONSE;
        this.mJsonData = parseStatus(jSONObject);
        parseJsonObject();
    }

    private void parseErrorMsg(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                String string = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
                if (!TextUtils.isEmpty(string)) {
                    printLog(next, string);
                } else {
                    printLog(next, jSONObject2.getJSONObject("data").getString(SocializeProtocolConstants.PROTOCOL_KEY_PLATFORM_ERROR));
                }
            }
        } catch (Exception e) {
        }
    }

    private JSONObject parseStatus(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            this.mStCode = jSONObject.optInt("st", SocializeConstants.SERVER_RETURN_PARAMS_ILLEGAL);
            if (this.mStCode == 0) {
                Log.e(TAG, "no status code in response.");
                return null;
            }
            this.mMsg = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG, "");
            String optString = jSONObject.optString("data", (String) null);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            if (this.mStCode != 200) {
                parseErrorMsg(optString);
            }
            return new JSONObject(optString);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Data body can`t convert to json ");
            return null;
        }
    }

    private void printLog(String str, String str2) {
        Log.e(TAG, "error message -> " + str + " : " + str2);
    }

    public JSONObject getJsonData() {
        return this.mJsonData;
    }

    public boolean isHttpOK() {
        return this.mHttpCode == 200;
    }

    public boolean isOk() {
        StringBuilder sb = new StringBuilder();
        sb.append("is http 200:");
        sb.append(this.mStCode == 200);
        Log.d("umeng_share_response", sb.toString());
        return this.mStCode == 200;
    }

    public void parseJsonObject() {
    }
}
