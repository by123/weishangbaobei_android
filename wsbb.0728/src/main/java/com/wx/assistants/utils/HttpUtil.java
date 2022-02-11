package com.wx.assistants.utils;

import android.net.Uri;
import com.umeng.commonsdk.proguard.e;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.globle.LogUtils;
import com.wx.assistants.globle.MyConverterFactory;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.server.BaseServer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;

public class HttpUtil {
    private static final HttpUtil ourInstance = new HttpUtil();
    private BaseServer baseServer = ((BaseServer) this.retrofit.create(BaseServer.class));
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(QBangApis.DEV_BASE_URL).addConverterFactory(MyConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            Request request = interceptor$Chain.request();
            HttpUtil.this.logRequest(HttpUtil.this.addBasicParams(request));
            return interceptor$Chain.proceed(request.newBuilder().header("equipment", MacAddressUtils.getMacAddress(MyApplication.mContext)).header("platform", "android").header("version_code", String.valueOf(350)).header("version_name", "3.5.0.7").header(e.n, MyApplication.WS).method(request.method(), request.body()).build());
        }
    }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()).build();

    private HttpUtil() {
    }

    /* access modifiers changed from: private */
    public Request addBasicParams(Request request) {
        HashMap hashMap = new HashMap();
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        hashMap.put("token", MyApplication.getConText().getSharedPreferences("login", 0).getString("user_token", ""));
        hashMap.put("platform", "android");
        hashMap.put("version_code", String.valueOf(350));
        hashMap.put("version_name", "3.5.0.7");
        hashMap.put(e.n, MyApplication.WS);
        hashMap.put("equipment", macAddress);
        Uri.Builder buildUpon = Uri.parse(request.url().toString()).buildUpon();
        for (Map.Entry entry : hashMap.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return request.newBuilder().url(buildUpon.build().toString()).method(request.method(), request.body()).build();
    }

    public static HttpUtil getInstance() {
        return ourInstance;
    }

    /* access modifiers changed from: private */
    public void logRequest(Request request) {
        StringBuilder sb;
        LogUtils.d("api->", request.toString());
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            try {
                request.body().writeTo(buffer);
                sb = new StringBuilder();
            } catch (IOException e) {
                sb = new StringBuilder();
            } catch (Throwable th) {
                LogUtils.log("WS_BABY_APIrequest_body##" + buffer.readUtf8());
                throw th;
            }
            sb.append("WS_BABY_APIrequest_body##");
            sb.append(buffer.readUtf8());
            LogUtils.log(sb.toString());
        }
    }

    public BaseServer getBaseServer() {
        return this.baseServer;
    }
}
