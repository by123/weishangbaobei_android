package com.wx.assistants.globle;

import com.umeng.commonsdk.proguard.e;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.AddCardRes;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.BindInvitationModel;
import com.wx.assistants.bean.CardPassiveRes;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.EditCardRes;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.bean.VoiceBean;
import com.wx.assistants.dialog.MProgressDialog;
import com.wx.assistants.server.BaseServer;
import com.wx.assistants.server.request.CheckCodeRequest;
import com.wx.assistants.server.request.LoginRequest;
import com.wx.assistants.server.request.MaterialForwardRequest;
import com.wx.assistants.server.request.PasswordLoginRequest;
import com.wx.assistants.server.request.PasswordSettingRequest;
import com.wx.assistants.server.request.PayRequest;
import com.wx.assistants.server.request.UserActivationCodeRequest;
import com.wx.assistants.server.request.UserExtractRequest;
import com.wx.assistants.server.request.VerificationCodeRequest;
import com.wx.assistants.server.request.WaterMarkRequest;
import com.wx.assistants.server.request.WxPayRequest;
import com.wx.assistants.server.request.ZFBAccountRequest;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWrapper {
    private static final String TAG = "ApiWrapper";
    private static ApiWrapper instance;
    private boolean isOutLog = true;
    private final BaseServer mApi = ((BaseServer) this.retrofit.create(BaseServer.class));
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(QBangApis.DEV_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            String str;
            String str2;
            Request request = interceptor$Chain.request();
            String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
            String str3 = "";
            try {
                String[] wSBabyVersion = PackageUtils.getWSBabyVersion(MyApplication.getConText());
                str = wSBabyVersion[0];
                try {
                    str2 = wSBabyVersion[1];
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                str = "";
                str2 = "3.5.0.10";
                str3 = PackageUtils.getWSBYAppName(MyApplication.getConText());
                Request build = request.newBuilder().header("equipment", macAddress).header("token", (String) SPUtils.get(MyApplication.getConText(), "user_token", "")).header("Content-Type", "application/json").header("platform", "android").header("version_code", str).header("version", str2).header("app_name", str3).header("bottom_tab_num", "4").header(e.n, MyApplication.WS).method(request.method(), request.body()).build();
                Response proceed = interceptor$Chain.proceed(build);
                ApiWrapper.this.logRequest(build, proceed);
                return proceed;
            }
            try {
                str3 = PackageUtils.getWSBYAppName(MyApplication.getConText());
            } catch (Exception unused3) {
            }
            Request build2 = request.newBuilder().header("equipment", macAddress).header("token", (String) SPUtils.get(MyApplication.getConText(), "user_token", "")).header("Content-Type", "application/json").header("platform", "android").header("version_code", str).header("version", str2).header("app_name", str3).header("bottom_tab_num", "4").header(e.n, MyApplication.WS).method(request.method(), request.body()).build();
            Response proceed2 = interceptor$Chain.proceed(build2);
            ApiWrapper.this.logRequest(build2, proceed2);
            return proceed2;
        }
    }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()).build();

    public interface CallbackListener<T> {
        void onFailure(FailureModel failureModel);

        void onFinish(T t);
    }

    private ApiWrapper() {
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|(3:4|5|6)|7|8|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x012e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logRequest(okhttp3.Request r3, okhttp3.Response r4) {
        /*
            r2 = this;
            boolean r0 = r2.isOutLog
            if (r0 == 0) goto L_0x0166
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_REQUEST_URL##"
            r0.append(r1)
            okhttp3.HttpUrl r1 = r3.url()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(equipment)##"
            r0.append(r1)
            java.lang.String r1 = "equipment"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(token)##"
            r0.append(r1)
            java.lang.String r1 = "token"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(platform)##"
            r0.append(r1)
            java.lang.String r1 = "platform"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(version)##"
            r0.append(r1)
            java.lang.String r1 = "version"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(version_code)##"
            r0.append(r1)
            java.lang.String r1 = "version_code"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(app_name)##"
            r0.append(r1)
            java.lang.String r1 = "app_name"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(bottom_tab_num)##"
            r0.append(r1)
            java.lang.String r1 = "bottom_tab_num"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY_HEARD(Content-Type)##"
            r0.append(r1)
            java.lang.String r1 = "Content-Type"
            java.lang.String r1 = r3.header(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
            okhttp3.RequestBody r0 = r3.body()
            if (r0 == 0) goto L_0x012e
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okhttp3.RequestBody r3 = r3.body()     // Catch:{ IOException -> 0x012e }
            r3.writeTo(r0)     // Catch:{ IOException -> 0x012e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x012e }
            r3.<init>()     // Catch:{ IOException -> 0x012e }
            java.lang.String r1 = "WS_BABY_REQUEST_BODY##"
            r3.append(r1)     // Catch:{ IOException -> 0x012e }
            java.lang.String r1 = r0.readUtf8()     // Catch:{ IOException -> 0x012e }
            r3.append(r1)     // Catch:{ IOException -> 0x012e }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x012e }
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ IOException -> 0x012e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x012e }
            r3.<init>()     // Catch:{ IOException -> 0x012e }
            java.lang.String r1 = "WS_BABY_REQUEST_PARAMS##"
            r3.append(r1)     // Catch:{ IOException -> 0x012e }
            java.lang.String r0 = r0.readUtf8()     // Catch:{ IOException -> 0x012e }
            r3.append(r0)     // Catch:{ IOException -> 0x012e }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x012e }
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ IOException -> 0x012e }
        L_0x012e:
            okhttp3.ResponseBody r3 = r4.body()     // Catch:{ Exception -> 0x0166 }
            okio.BufferedSource r3 = r3.source()     // Catch:{ Exception -> 0x0166 }
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r3.request(r0)     // Catch:{ Exception -> 0x0166 }
            okio.Buffer r3 = r3.buffer()     // Catch:{ Exception -> 0x0166 }
            okio.Buffer r3 = r3.clone()     // Catch:{ Exception -> 0x0166 }
            java.lang.String r4 = "UTF-8"
            java.nio.charset.Charset r4 = java.nio.charset.Charset.forName(r4)     // Catch:{ Exception -> 0x0166 }
            java.lang.String r3 = r3.readString(r4)     // Catch:{ Exception -> 0x0166 }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x0166 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0166 }
            r0.<init>()     // Catch:{ Exception -> 0x0166 }
            java.lang.String r1 = "WS_BABY_RESPONSE_BODY##"
            r0.append(r1)     // Catch:{ Exception -> 0x0166 }
            r0.append(r3)     // Catch:{ Exception -> 0x0166 }
            java.lang.String r3 = r0.toString()     // Catch:{ Exception -> 0x0166 }
            r4.println(r3)     // Catch:{ Exception -> 0x0166 }
        L_0x0166:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.globle.ApiWrapper.logRequest(okhttp3.Request, okhttp3.Response):void");
    }

    public static ApiWrapper getInstance() {
        if (instance == null) {
            synchronized (ApiWrapper.class) {
                if (instance == null) {
                    instance = new ApiWrapper();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> cls) {
        return this.retrofit.create(cls);
    }

    public static class MyCallback<T> implements Callback<T> {
        private CallbackListener callbackListener;

        public MyCallback(CallbackListener callbackListener2) {
            this.callbackListener = callbackListener2;
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
                T body = response.body();
                if (this.callbackListener != null) {
                    this.callbackListener.onFinish(body);
                }
            }
        }

        public void onFailure(Call<T> call, Throwable th) {
            if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
                MProgressDialog.dismissProgress();
                ToastUtils.showToast(MyApplication.getConText(), "网络连接异常，请检测网络是否可用！");
            } else if (th instanceof SocketTimeoutException) {
                MProgressDialog.dismissProgress();
                ToastUtils.showToast(MyApplication.getConText(), "访问服务超时");
            }
        }
    }

    public Object queryVersion(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> appVersion = this.mApi.getAppVersion();
        appVersion.enqueue(new MyCallback(callbackListener));
        return appVersion.request();
    }

    public Object getVersion(CallbackListener<ConmdBean<AppVersionModel>> callbackListener) {
        Call<ConmdBean<AppVersionModel>> version = this.mApi.getVersion();
        version.enqueue(new MyCallback(callbackListener));
        return version.request();
    }

    public Object invitationBind(BindInvitationModel bindInvitationModel, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> invitationBind = this.mApi.invitationBind(bindInvitationModel);
        invitationBind.enqueue(new MyCallback(callbackListener));
        return invitationBind.request();
    }

    public Object getMessageVerificationCode(VerificationCodeRequest verificationCodeRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> phoneNumber = this.mApi.getPhoneNumber(verificationCodeRequest);
        phoneNumber.enqueue(new MyCallback(callbackListener));
        return phoneNumber.request();
    }

    public Object userLogin(LoginRequest loginRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> addUserPhoneNumber = this.mApi.addUserPhoneNumber(loginRequest);
        addUserPhoneNumber.enqueue(new MyCallback(callbackListener));
        return addUserPhoneNumber.request();
    }

    public Object userLogin(PasswordLoginRequest passwordLoginRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> userLogin = this.mApi.userLogin(passwordLoginRequest);
        userLogin.enqueue(new MyCallback(callbackListener));
        return userLogin.request();
    }

    public Object userActivationCode(UserActivationCodeRequest userActivationCodeRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> addUserActivationCode = this.mApi.addUserActivationCode(userActivationCodeRequest);
        addUserActivationCode.enqueue(new MyCallback(callbackListener));
        return addUserActivationCode.request();
    }

    public Object getMemberRatingList(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> memberPrice = this.mApi.getMemberPrice();
        memberPrice.enqueue(new MyCallback(callbackListener));
        return memberPrice.request();
    }

    public Object alipay(PayRequest payRequest, CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> postZfbSave = this.mApi.postZfbSave(payRequest);
        postZfbSave.enqueue(new MyCallback(callbackListener));
        return postZfbSave.request();
    }

    public Object wechatPay(WxPayRequest wxPayRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> postWxSave = this.mApi.postWxSave(wxPayRequest);
        postWxSave.enqueue(new MyCallback(callbackListener));
        return postWxSave.request();
    }

    public Object getScroll(String str, String str2, CallbackListener<ConmdBean<List<HomeBannerBean>>> callbackListener) {
        Call<ConmdBean<List<HomeBannerBean>>> scroll = this.mApi.getScroll(str, str2);
        scroll.enqueue(new MyCallback(callbackListener));
        return scroll.request();
    }

    public Object advertisementImg(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> pic = this.mApi.getPic();
        pic.enqueue(new MyCallback(callbackListener));
        return pic.request();
    }

    public Object bannerImgs(CallbackListener<ConmdBean<List<String>>> callbackListener) {
        Call<ConmdBean<List<String>>> banner = this.mApi.getBanner();
        banner.enqueue(new MyCallback(callbackListener));
        return banner.request();
    }

    public Object getInviteData(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> inviteData = this.mApi.getInviteData();
        inviteData.enqueue(new MyCallback(callbackListener));
        return inviteData.request();
    }

    public Object getUser(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> user = this.mApi.getUser(str);
        user.enqueue(new MyCallback(callbackListener));
        return user.request();
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

    public Object myMemberCard(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myMemberCard = this.mApi.myMemberCard(i, i2, str);
        myMemberCard.enqueue(new MyCallback(callbackListener));
        return myMemberCard.request();
    }

    public Object myMemberInvite(int i, int i2, String str, String str2, CallbackListener<ConmdList2Bean> callbackListener) {
        Call<ConmdList2Bean> myMemberInvite = this.mApi.myMemberInvite(i, i2, str);
        myMemberInvite.enqueue(new MyCallback(callbackListener));
        return myMemberInvite.request();
    }

    public Object myMemberInvite(int i, int i2, String str, int i3, CallbackListener<ConmdList2Bean> callbackListener) {
        Call<ConmdList2Bean> myMemberInvite = this.mApi.myMemberInvite(i, i2, str, i3);
        myMemberInvite.enqueue(new MyCallback(callbackListener));
        return myMemberInvite.request();
    }

    public Object materialList(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> materialList = this.mApi.materialList(i, i2, str);
        materialList.enqueue(new MyCallback(callbackListener));
        return materialList.request();
    }

    public Object applyExtract(UserExtractRequest userExtractRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> applyExtract = this.mApi.applyExtract(userExtractRequest);
        applyExtract.enqueue(new MyCallback(callbackListener));
        return applyExtract.request();
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

    public Object myExtractList(int i, int i2, String str, String str2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myExtractList = this.mApi.myExtractList(i, i2, str);
        myExtractList.enqueue(new MyCallback(callbackListener));
        return myExtractList.request();
    }

    public Object myActivationCode(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> myActivationCode = this.mApi.myActivationCode(str);
        myActivationCode.enqueue(new MyCallback(callbackListener));
        return myActivationCode.request();
    }

    public Object loginPasswordSetting(PasswordSettingRequest passwordSettingRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> loginPasswordSetting = this.mApi.loginPasswordSetting(passwordSettingRequest);
        loginPasswordSetting.enqueue(new MyCallback(callbackListener));
        return loginPasswordSetting.request();
    }

    public Object launchValidate(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> launchValidate = this.mApi.launchValidate(str);
        launchValidate.enqueue(new MyCallback(callbackListener));
        return launchValidate.request();
    }

    public Object checkRuleValidate(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> checkRuleValidate = this.mApi.checkRuleValidate(str);
        checkRuleValidate.enqueue(new MyCallback(callbackListener));
        return checkRuleValidate.request();
    }

    public Object validateVersion(CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> validateVersion = this.mApi.validateVersion();
        validateVersion.enqueue(new MyCallback(callbackListener));
        return validateVersion.request();
    }

    public Object myInviteProgress(String str, CallbackListener<ConmdBean<Integer>> callbackListener) {
        Call<ConmdBean<Integer>> myInviteProgress = this.mApi.myInviteProgress(str);
        myInviteProgress.enqueue(new MyCallback(callbackListener));
        return myInviteProgress.request();
    }

    public Object materialForward(MaterialForwardRequest materialForwardRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> materialForward = this.mApi.materialForward(materialForwardRequest);
        materialForward.enqueue(new MyCallback(callbackListener));
        return materialForward.request();
    }

    public Object myUsersByPhone(String str, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> myUsersByPhone = this.mApi.myUsersByPhone(str);
        myUsersByPhone.enqueue(new MyCallback(callbackListener));
        return myUsersByPhone.request();
    }

    public Object getWatermarkUrl(WaterMarkRequest waterMarkRequest, CallbackListener<ConmdBean<String>> callbackListener) {
        Call<ConmdBean<String>> watermarkUrl = this.mApi.getWatermarkUrl(waterMarkRequest);
        watermarkUrl.enqueue(new MyCallback(callbackListener));
        return watermarkUrl.request();
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

    public Object myNotice(int i, int i2, String str, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myNotice = this.mApi.myNotice(i, i2, str);
        myNotice.enqueue(new MyCallback(callbackListener));
        return myNotice.request();
    }

    public Object getFee(CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> fee = this.mApi.getFee();
        fee.enqueue(new MyCallback(callbackListener));
        return fee.request();
    }

    public Object getMyCardList(int i, int i2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> myCardList = this.mApi.getMyCardList(Integer.valueOf(i), Integer.valueOf(i2));
        myCardList.enqueue(new MyCallback(callbackListener));
        return myCardList.request();
    }

    public Object getPassiveCardList(int i, int i2, CallbackListener<ConmdListBean> callbackListener) {
        Call<ConmdListBean> passiveCardList = this.mApi.getPassiveCardList(Integer.valueOf(i), Integer.valueOf(i2));
        passiveCardList.enqueue(new MyCallback(callbackListener));
        return passiveCardList.request();
    }

    public Object delCardInfo(Integer num, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> delCardInfo = this.mApi.delCardInfo(num);
        delCardInfo.enqueue(new MyCallback(callbackListener));
        return delCardInfo.request();
    }

    public Object addCardInfo(AddCardRes addCardRes, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> addCardInfo = this.mApi.addCardInfo(addCardRes);
        addCardInfo.enqueue(new MyCallback(callbackListener));
        return addCardInfo.request();
    }

    public Object editCardInfo(EditCardRes editCardRes, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> editCardInfo = this.mApi.editCardInfo(editCardRes);
        editCardInfo.enqueue(new MyCallback(callbackListener));
        return editCardInfo.request();
    }

    public Object passiveCard(CardPassiveRes cardPassiveRes, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> passiveCard = this.mApi.passiveCard(cardPassiveRes);
        passiveCard.enqueue(new MyCallback(callbackListener));
        return passiveCard.request();
    }

    public Object checkCode(CheckCodeRequest checkCodeRequest, CallbackListener<ConmdBean> callbackListener) {
        Call<ConmdBean> checkCode = this.mApi.checkCode(checkCodeRequest);
        checkCode.enqueue(new MyCallback(callbackListener));
        return checkCode.request();
    }
}
