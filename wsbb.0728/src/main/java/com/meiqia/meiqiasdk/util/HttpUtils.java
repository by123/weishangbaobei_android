package com.meiqia.meiqiasdk.util;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static HttpUtils sInstance;
    private static OkHttpClient sOkHttpClient;

    private HttpUtils() {
        sOkHttpClient = new OkHttpClient();
    }

    public static HttpUtils getInstance() {
        if (sInstance == null) {
            sInstance = new HttpUtils();
        }
        return sInstance;
    }

    public JSONObject getAuthCode() throws IOException, JSONException {
        RequestBody create = RequestBody.create(JSON, new byte[0]);
        Request.Builder builder = new Request.Builder();
        JSONObject jSONObject = new JSONObject(sOkHttpClient.newCall(builder.url("https://eco-api.meiqia.com/" + "/captchas").post(create).build()).execute().body().string());
        String optString = jSONObject.optString("captcha_image_url");
        jSONObject.put("captcha_image_url", "https://eco-api.meiqia.com/" + optString);
        return jSONObject;
    }
}
