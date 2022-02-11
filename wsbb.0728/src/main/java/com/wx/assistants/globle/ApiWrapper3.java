package com.wx.assistants.globle;

import com.umeng.commonsdk.proguard.e;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.BindInvitationModel;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.bean.VoiceBean;
import com.wx.assistants.dialog.MProgressDialog;
import com.wx.assistants.server.BaseServer;
import com.wx.assistants.server.request.LoginRequest;
import com.wx.assistants.server.request.MaterialForwardRequest;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.UserActivationCodeRequest;
import com.wx.assistants.server.request.UserExtractRequest;
import com.wx.assistants.server.request.VerificationCodeRequest;
import com.wx.assistants.server.request.WaterMarkRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.server.request.ZFBAccountRequest;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ApiWrapper3 {
    private static final String TAG = "ApiWrapper3";
    private static ApiWrapper3 instance;
    private boolean isOutLog = true;
    private final BaseServer mApi = ((BaseServer) this.retrofit.create(BaseServer.class));
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(QBangApis.DEV_BASE_URL).addConverterFactory(MyConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            String str;
            String str2;
            String str3;
            Request request = interceptor$Chain.request();
            String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
            try {
                String[] wSBabyVersion = PackageUtils.getWSBabyVersion(MyApplication.getConText());
                String str4 = wSBabyVersion[0];
                str = wSBabyVersion[1];
                str2 = str4;
            } catch (Exception e) {
                str = "3.5.0.7";
                str2 = "";
            }
            try {
                str3 = PackageUtils.getWSBYAppName(MyApplication.getConText());
            } catch (Exception e2) {
                str3 = "";
            }
            Request build = request.newBuilder().header("equipment", macAddress).header("token", (String) SPUtils.get(MyApplication.getConText(), "user_token", "")).header("Content-Type", "application/json").header("platform", "android").header("version_code", str2).header("version", str).header("app_name", str3).header("bottom_tab_num", "4").header(e.n, MyApplication.WS).method(request.method(), request.body()).build();
            ApiWrapper3.this.logRequest(build);
            return interceptor$Chain.proceed(build);
        }
    }).connectTimeout(8, TimeUnit.SECONDS).readTimeout(8, TimeUnit.SECONDS).writeTimeout(8, TimeUnit.SECONDS).build()).build();

    public interface CallbackListener<T> {
        void connectException();

        void onFailure(FailureModel failureModel);

        void onFinish(T t);

        void onSocketTimeout();
    }

    public static class MyCallback<T> implements Callback<T> {
        private CallbackListener callbackListener;

        public MyCallback(CallbackListener callbackListener2) {
            this.callbackListener = callbackListener2;
        }

        public void onFailure(Call<T> call, Throwable th) {
            if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
                MProgressDialog.dismissProgress();
                ToastUtils.showToast(MyApplication.getConText(), "网络连接异常，请检测网络是否可用！");
                if (this.callbackListener != null) {
                    this.callbackListener.connectException();
                }
            } else if (th instanceof SocketTimeoutException) {
                MProgressDialog.dismissProgress();
                ToastUtils.showToast(MyApplication.getConText(), "访问服务超时");
                if (this.callbackListener != null) {
                    this.callbackListener.onSocketTimeout();
                }
            }
        }

        public void onResponse(Call<T> call, retrofit2.Response<T> response) {
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
                T body = response.body();
                if (this.callbackListener != null) {
                    this.callbackListener.onFinish(body);
                }
            }
        }
    }

    private ApiWrapper3() {
    }

    public static ApiWrapper3 getInstance() {
        if (instance == null) {
            synchronized (ApiWrapper3.class) {
                try {
                    if (instance == null) {
                        instance = new ApiWrapper3();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<ApiWrapper3> cls = ApiWrapper3.class;
                        throw th;
                    }
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: private */
    public void logRequest(Request request) {
        StringBuilder sb;
        LogUtils.d("api->", request.toString());
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            try {
                request.body().writeTo(buffer);
                if (this.isOutLog) {
                    LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                    LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                    LogUtils.log("WS_BABY_HEARD(equipment)##" + request.header("equipment"));
                    LogUtils.log("WS_BABY_HEARD(token)##" + request.header("token"));
                    LogUtils.log("WS_BABY_HEARD(platform)##" + request.header("platform"));
                    LogUtils.log("WS_BABY_HEARD(version)##" + request.header("version"));
                    LogUtils.log("WS_BABY_HEARD(version_code)##" + request.header("version_code"));
                    LogUtils.log("WS_BABY_HEARD(app_name)##" + request.header("app_name"));
                    LogUtils.log("WS_BABY_HEARD(bottom_tab_num)##" + request.header("bottom_tab_num"));
                    LogUtils.log("WS_BABY_HEARD(Content-Type)##" + request.header("Content-Type"));
                    sb = new StringBuilder();
                    sb.append("request_body##");
                    sb.append(buffer.readUtf8());
                    LogUtils.d("api->", sb.toString());
                }
            } catch (IOException e) {
                if (this.isOutLog) {
                    LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                    LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                    LogUtils.log("WS_BABY_HEARD(equipment)##" + request.header("equipment"));
                    LogUtils.log("WS_BABY_HEARD(token)##" + request.header("token"));
                    LogUtils.log("WS_BABY_HEARD(platform)##" + request.header("platform"));
                    LogUtils.log("WS_BABY_HEARD(version)##" + request.header("version"));
                    LogUtils.log("WS_BABY_HEARD(version_code)##" + request.header("version_code"));
                    LogUtils.log("WS_BABY_HEARD(app_name)##" + request.header("app_name"));
                    LogUtils.log("WS_BABY_HEARD(bottom_tab_num)##" + request.header("bottom_tab_num"));
                    LogUtils.log("WS_BABY_HEARD(Content-Type)##" + request.header("Content-Type"));
                    sb = new StringBuilder();
                }
            } catch (Throwable th) {
                if (this.isOutLog) {
                    LogUtils.log("WS_BABY_REQUEST_URL##" + request.url());
                    LogUtils.log("WS_BABY_REQUEST_PARAMS##" + buffer.readUtf8());
                    LogUtils.log("WS_BABY_HEARD(equipment)##" + request.header("equipment"));
                    LogUtils.log("WS_BABY_HEARD(token)##" + request.header("token"));
                    LogUtils.log("WS_BABY_HEARD(platform)##" + request.header("platform"));
                    LogUtils.log("WS_BABY_HEARD(version)##" + request.header("version"));
                    LogUtils.log("WS_BABY_HEARD(version_code)##" + request.header("version_code"));
                    LogUtils.log("WS_BABY_HEARD(app_name)##" + request.header("app_name"));
                    LogUtils.log("WS_BABY_HEARD(bottom_tab_num)##" + request.header("bottom_tab_num"));
                    LogUtils.log("WS_BABY_HEARD(Content-Type)##" + request.header("Content-Type"));
                    LogUtils.d("api->", "request_body##" + buffer.readUtf8());
                }
                throw th;
            }
        }
    }

    public Object addAmrMaterial(MultipartBody.Part part, CallbackListener<ConmdBean<VoiceBean>> callbackListener) {
        Call<ConmdBean<VoiceBean>> addAmrMaterial = this.mApi.addAmrMaterial(part);
        addAmrMaterial.enqueue(new MyCallback(callbackListener));
        return addAmrMaterial.request();
    }

    public Object addMaterialToAli(MultipartBody.Part part, CallbackListener<ConmdBean<VoiceBean>> callbackListener) {
        Call<ConmdBean<VoiceBean>> addMaterialToAli = this.mApi.addMaterialToAli(part);
        addMaterialToAli.enqueue(new MyCallback(callbackListener));
        return addMaterialToAli.request();
    }

    public Object advertisementImg(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> pic = this.mApi.getPic();
        pic.enqueue(new MyCallback(callbackListener));
        return pic.request();
    }

    public Object alipay(PayRequest payRequest, CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> postZfbSave = this.mApi.postZfbSave(payRequest);
        postZfbSave.enqueue(new MyCallback(callbackListener));
        return postZfbSave.request();
    }

    public Object applyExtract(UserExtractRequest userExtractRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> applyExtract = this.mApi.applyExtract(userExtractRequest);
        applyExtract.enqueue(new MyCallback(callbackListener));
        return applyExtract.request();
    }

    public Object bannerImgs(CallbackListener<ConmdBean<List<String>>> callbackListener) {
        Call<ConmdBean<List<String>>> banner = this.mApi.getBanner();
        banner.enqueue(new MyCallback(callbackListener));
        return banner.request();
    }

    public Object checkRuleValidate(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> checkRuleValidate = this.mApi.checkRuleValidate(str);
        checkRuleValidate.enqueue(new MyCallback(callbackListener));
        return checkRuleValidate.request();
    }

    public <T> T create(Class<T> cls) {
        return this.retrofit.create(cls);
    }

    public Object getInviteData(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> inviteData = this.mApi.getInviteData();
        inviteData.enqueue(new MyCallback(callbackListener));
        return inviteData.request();
    }

    public Object getMemberRatingList(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> memberPrice = this.mApi.getMemberPrice();
        memberPrice.enqueue(new MyCallback(callbackListener));
        return memberPrice.request();
    }

    public Object getMessageVerificationCode(VerificationCodeRequest verificationCodeRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> phoneNumber = this.mApi.getPhoneNumber(verificationCodeRequest);
        phoneNumber.enqueue(new MyCallback(callbackListener));
        return phoneNumber.request();
    }

    public Object getScroll(String str, String str2, CallbackListener<ConmdBean<List<HomeBannerBean>>> callbackListener) {
        Call<ConmdBean<List<HomeBannerBean>>> scroll = this.mApi.getScroll(str, str2);
        scroll.enqueue(new MyCallback(callbackListener));
        return scroll.request();
    }

    public Object getUser(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> user = this.mApi.getUser(str);
        user.enqueue(new MyCallback(callbackListener));
        return user.request();
    }

    public Object getVersion(CallbackListener<ConmdBean<AppVersionModel>> callbackListener) {
        Call<ConmdBean<AppVersionModel>> version = this.mApi.getVersion();
        version.enqueue(new MyCallback(callbackListener));
        return version.request();
    }

    public Object getWatermarkUrl(WaterMarkRequest waterMarkRequest, CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> watermarkUrl = this.mApi.getWatermarkUrl(waterMarkRequest);
        watermarkUrl.enqueue(new MyCallback(callbackListener));
        return watermarkUrl.request();
    }

    public Object invitationBind(BindInvitationModel bindInvitationModel, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> invitationBind = this.mApi.invitationBind(bindInvitationModel);
        invitationBind.enqueue(new MyCallback(callbackListener));
        return invitationBind.request();
    }

    public Object launchValidate(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> launchValidate = this.mApi.launchValidate(str);
        launchValidate.enqueue(new MyCallback(callbackListener));
        return launchValidate.request();
    }

    public Object materialForward(MaterialForwardRequest materialForwardRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> materialForward = this.mApi.materialForward(materialForwardRequest);
        materialForward.enqueue(new MyCallback(callbackListener));
        return materialForward.request();
    }

    public Object materialList(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> materialList = this.mApi.materialList(i, i2, str);
        materialList.enqueue(new MyCallback(callbackListener));
        return materialList.request();
    }

    public Object myActivationCode(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> myActivationCode = this.mApi.myActivationCode(str);
        myActivationCode.enqueue(new MyCallback(callbackListener));
        return myActivationCode.request();
    }

    public Object myExtractList(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myExtractList = this.mApi.myExtractList(i, i2, str);
        myExtractList.enqueue(new MyCallback(callbackListener));
        return myExtractList.request();
    }

    public Object myInviteProgress(String str, CallbackListener<ConmdBean<Integer>> callbackListener) {
        Call<ConmdBean<Integer>> myInviteProgress = this.mApi.myInviteProgress(str);
        myInviteProgress.enqueue(new MyCallback(callbackListener));
        return myInviteProgress.request();
    }

    public Object myMemberCard(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myMemberCard = this.mApi.myMemberCard(i, i2, str);
        myMemberCard.enqueue(new MyCallback(callbackListener));
        return myMemberCard.request();
    }

    public Object myMemberInvite(int i, int i2, String str, int i3, CallbackListener<ConmdList2Bean> callbackListener) {
        Call<ConmdList2Bean> myMemberInvite = this.mApi.myMemberInvite(i, i2, str, i3);
        myMemberInvite.enqueue(new MyCallback(callbackListener));
        return myMemberInvite.request();
    }

    public Object myMemberInvite(int i, int i2, String str, String str2, CallbackListener<ConmdList2Bean> callbackListener) {
        Call<ConmdList2Bean> myMemberInvite = this.mApi.myMemberInvite(i, i2, str);
        myMemberInvite.enqueue(new MyCallback(callbackListener));
        return myMemberInvite.request();
    }

    public Object myNotice(int i, int i2, String str, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myNotice = this.mApi.myNotice(i, i2, str);
        myNotice.enqueue(new MyCallback(callbackListener));
        return myNotice.request();
    }

    public Object myUsersByPhone(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> myUsersByPhone = this.mApi.myUsersByPhone(str);
        myUsersByPhone.enqueue(new MyCallback(callbackListener));
        return myUsersByPhone.request();
    }

    public Object queryMemberAllOrders(String str, String str2, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> queryMemberAllOrdersByPhoneNumber = this.mApi.queryMemberAllOrdersByPhoneNumber(str);
        queryMemberAllOrdersByPhoneNumber.enqueue(new MyCallback(callbackListener));
        return queryMemberAllOrdersByPhoneNumber.request();
    }

    public Object queryMemberOrders(int i, int i2, String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> queryMemberOrdersByPhoneNumber = this.mApi.queryMemberOrdersByPhoneNumber(i, i2, str);
        queryMemberOrdersByPhoneNumber.enqueue(new MyCallback(callbackListener));
        return queryMemberOrdersByPhoneNumber.request();
    }

    public Object updateUser(UserInfoBean.UserBean userBean, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdBean> updateUser = this.mApi.updateUser(userBean);
        updateUser.enqueue(new MyCallback(callbackListener));
        return updateUser.request();
    }

    public Object updateZFBAccount(ZFBAccountRequest zFBAccountRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> updateAccount = this.mApi.updateAccount(zFBAccountRequest);
        updateAccount.enqueue(new MyCallback(callbackListener));
        return updateAccount.request();
    }

    public Object userActivationCode(UserActivationCodeRequest userActivationCodeRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> addUserActivationCode = this.mApi.addUserActivationCode(userActivationCodeRequest);
        addUserActivationCode.enqueue(new MyCallback(callbackListener));
        return addUserActivationCode.request();
    }

    public Object userLogin(LoginRequest loginRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> addUserPhoneNumber = this.mApi.addUserPhoneNumber(loginRequest);
        addUserPhoneNumber.enqueue(new MyCallback(callbackListener));
        return addUserPhoneNumber.request();
    }

    public Object validateVersion(CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> validateVersion = this.mApi.validateVersion();
        validateVersion.enqueue(new MyCallback(callbackListener));
        return validateVersion.request();
    }

    public Object wechatPay(WxPayRequest wxPayRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> postWxSave = this.mApi.postWxSave(wxPayRequest);
        postWxSave.enqueue(new MyCallback(callbackListener));
        return postWxSave.request();
    }
}
