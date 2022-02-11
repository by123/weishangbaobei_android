package com.wx.assistants.globle;

import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.WaterMarkResultModel;
import com.wx.assistants.dialog.MProgressDialog;
import com.wx.assistants.server.request.CleanWaterMarkRequest;
import com.wx.assistants.utils.LogUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ApiWrapper2 {
    private static ApiWrapper2 instance;
    private final MyWaterMarkServer myWaterMarkServer = ((MyWaterMarkServer) this.f4retrofit2.create(MyWaterMarkServer.class));

    /* renamed from: retrofit2  reason: collision with root package name */
    private Retrofit f4retrofit2 = new Retrofit.Builder().baseUrl("https://service.iiilab.com").addConverterFactory(MyConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            Request request = interceptor$Chain.request();
            Request build = request.newBuilder().header("Content-Type", "application/x-www-form-urlencoded").method(request.method(), request.body()).build();
            ApiWrapper2.this.logRequest(build);
            return interceptor$Chain.proceed(build);
        }
    }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()).build();

    public interface CallbackListener2<T> {
        void onFailure(FailureModel failureModel);

        void onFinish(WaterMarkResultModel waterMarkResultModel);
    }

    public interface MyWaterMarkServer {
        @FormUrlEncoded
        @POST("/video/download")
        Call<WaterMarkResultModel> getWaterMark(@Field("link") String str, @Field("timestamp") String str2, @Field("sign") String str3, @Field("client") String str4);

        @POST("/video/download")
        Call<WaterMarkResultModel> getWaterMark2(@Body CleanWaterMarkRequest cleanWaterMarkRequest);
    }

    private ApiWrapper2() {
    }

    /* access modifiers changed from: private */
    public void logRequest(Request request) {
        StringBuilder sb;
        LogUtils.d("api->", request.toString());
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            try {
                request.body().writeTo(buffer);
                LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                sb = new StringBuilder();
            } catch (IOException unused) {
                LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                sb = new StringBuilder();
            } catch (Throwable th) {
                LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                LogUtils.log("WS_BABY_HEARD(Content-Type)##" + request.header("Content-Type"));
                throw th;
            }
            sb.append("WS_BABY_HEARD(Content-Type)##");
            sb.append(request.header("Content-Type"));
            LogUtils.log(sb.toString());
        }
    }

    public static ApiWrapper2 getInstance() {
        if (instance == null) {
            synchronized (ApiWrapper2.class) {
                if (instance == null) {
                    instance = new ApiWrapper2();
                }
            }
        }
        return instance;
    }

    public <WaterMarkResultModel> WaterMarkResultModel create(Class<WaterMarkResultModel> cls) {
        return this.f4retrofit2.create(cls);
    }

    public static class MyCallback<WaterMarkResultModel> implements Callback<WaterMarkResultModel> {
        private CallbackListener2 callbackListener;

        public void onFailure(Call<WaterMarkResultModel> call, Throwable th) {
        }

        public MyCallback(CallbackListener2 callbackListener2) {
            this.callbackListener = callbackListener2;
        }

        public void onResponse(Call<WaterMarkResultModel> call, retrofit2.Response<WaterMarkResultModel> response) {
            if (response.code() != 200) {
                MProgressDialog.dismissProgress();
                int code = response.code();
                String obj = response.errorBody().toString();
                String message = response.message();
                if (this.callbackListener != null) {
                    FailureModel failureModel = new FailureModel();
                    failureModel.setCode(code);
                    failureModel.setErrorMessage(message);
                    failureModel.setErrorBody(obj);
                    this.callbackListener.onFailure(failureModel);
                    return;
                }
                return;
            }
            MProgressDialog.dismissProgress();
            if (response.isSuccessful()) {
                LogUtils.log("WS_BABY_RESPONSE_BODY##" + response.body().toString());
                WaterMarkResultModel body = response.body();
                if (this.callbackListener != null) {
                    this.callbackListener.onFinish((WaterMarkResultModel) body);
                }
            }
        }
    }

    public Object getWaterMark(String str, String str2, String str3, String str4, CallbackListener2<WaterMarkResultModel> callbackListener2) {
        Call<WaterMarkResultModel> waterMark = this.myWaterMarkServer.getWaterMark(str, str2, str3, str4);
        waterMark.enqueue(new MyCallback(callbackListener2));
        return waterMark.request();
    }

    public Object getWaterMark2(CleanWaterMarkRequest cleanWaterMarkRequest, CallbackListener2<WaterMarkResultModel> callbackListener2) {
        Call<WaterMarkResultModel> waterMark2 = this.myWaterMarkServer.getWaterMark2(cleanWaterMarkRequest);
        waterMark2.enqueue(new MyCallback(callbackListener2));
        return waterMark2.request();
    }
}
