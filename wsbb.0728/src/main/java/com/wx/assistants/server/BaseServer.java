package com.wx.assistants.server;

import com.wx.assistants.bean.AddCardRes;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.BindInvitationModel;
import com.wx.assistants.bean.CardPassiveRes;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.EditCardRes;
import com.wx.assistants.bean.HomeBannerBean;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.bean.VoiceBean;
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
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface BaseServer {
    @POST("/material/addAmrMaterial")
    @Multipart
    Call<ConmdBean<VoiceBean>> addAmrMaterial(@Part MultipartBody.Part part);

    @POST("/card/addCardInfo")
    Call<ConmdBean> addCardInfo(@Body AddCardRes addCardRes);

    @POST("/material/addMaterialToAli")
    @Multipart
    Call<ConmdBean<VoiceBean>> addMaterialToAli(@Part MultipartBody.Part part);

    @POST("/user/addUserActivationCode")
    Call<ConmdBean> addUserActivationCode(@Body UserActivationCodeRequest userActivationCodeRequest);

    @POST("/user/addUserPhoneNumber")
    Call<ConmdBean> addUserPhoneNumber(@Body LoginRequest loginRequest);

    @POST("/user/applyExtract")
    Call<ConmdBean> applyExtract(@Body UserExtractRequest userExtractRequest);

    @POST("/z/code")
    Call<ConmdBean> checkCode(@Body CheckCodeRequest checkCodeRequest);

    @GET("/privilege/permission")
    Call<ConmdBean> checkRuleValidate(@Query("equipment") String str);

    @GET("/card/delCardInfo")
    Call<ConmdBean> delCardInfo(@Query("id") Integer num);

    @POST("/card/editCardInfo")
    Call<ConmdBean> editCardInfo(@Body EditCardRes editCardRes);

    @POST("/user/queryVersion")
    Call<ConmdBean> getAppVersion();

    @POST("/user/pic")
    Call<ConmdBean<List<String>>> getBanner();

    @GET("/card/fee")
    Call<ConmdBean> getFee();

    @POST("/qrcode/createqrcode")
    Call<ConmdBean> getInviteData();

    @GET("/user/memberAllLevel")
    Call<ConmdBean> getMemberPrice();

    @GET("/card/list/my")
    Call<ConmdListBean> getMyCardList(@Query("page") Integer num, @Query("pageSize") Integer num2);

    @GET("/card/list")
    Call<ConmdListBean> getPassiveCardList(@Query("page") Integer num, @Query("pageSize") Integer num2);

    @POST("/user/getPhoneNumber")
    Call<ConmdBean> getPhoneNumber(@Body VerificationCodeRequest verificationCodeRequest);

    @POST("/user/picSingle")
    Call<ConmdBean> getPic();

    @GET("/user/getScroll")
    Call<ConmdBean<List<HomeBannerBean>>> getScroll(@Query("equipment") String str, @Query("type") String str2);

    @GET("/user/getUser")
    Call<ConmdBean> getUser(@Query("equipment") String str);

    @POST("/user/getVersion")
    Call<ConmdBean<AppVersionModel>> getVersion();

    @POST("/material/getWatermarkUrl")
    Call<ConmdBean<String>> getWatermarkUrl(@Body WaterMarkRequest waterMarkRequest);

    @POST("/user/supplement")
    Call<ConmdBean> invitationBind(@Body BindInvitationModel bindInvitationModel);

    @FormUrlEncoded
    @POST("/user/appentrance")
    Call<ConmdBean> launchValidate(@Field("devicecode") String str);

    @POST("/user/reset")
    Call<ConmdBean> loginPasswordSetting(@Body PasswordSettingRequest passwordSettingRequest);

    @POST("/material/forward")
    Call<ConmdBean> materialForward(@Body MaterialForwardRequest materialForwardRequest);

    @GET("/material/list")
    Call<ConmdListBean> materialList(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str);

    @GET("/user/myActivationCode")
    Call<ConmdBean> myActivationCode(@Query("equipment") String str);

    @GET("user/myExtract")
    Call<ConmdListBean> myExtractList(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str);

    @GET("/user/myInviteProgress")
    Call<ConmdBean<Integer>> myInviteProgress(@Query("equipment") String str);

    @GET("/user/myCard")
    Call<ConmdListBean> myMemberCard(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str);

    @GET("/user/myInvite")
    Call<ConmdList2Bean> myMemberInvite(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str);

    @GET("/user/myInvite")
    Call<ConmdList2Bean> myMemberInvite(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str, @Query("type") int i3);

    @GET("/user/myNotice")
    Call<ConmdListBean> myNotice(@Query("page") int i, @Query("pageSize") int i2, @Query("isRead") String str);

    @GET("user/myUsersByPhone")
    Call<ConmdBean> myUsersByPhone(@Query("equipment") String str);

    @POST("/card/passive")
    Call<ConmdBean> passiveCard(@Body CardPassiveRes cardPassiveRes);

    @POST("/wxpay/wxsave")
    Call<ConmdBean> postWxSave(@Body WxPayRequest wxPayRequest);

    @POST("/alipay/pay")
    Call<ConmdBean<String>> postZfbSave(@Body PayRequest payRequest);

    @GET("/order/queryMemberAllOrdersByPhoneNumber")
    Call<ConmdBean> queryMemberAllOrdersByPhoneNumber(@Query("equipment") String str);

    @GET("/order/queryMemberAllOrdersByPhoneNumber")
    Call<ConmdBean> queryMemberOrdersByPhoneNumber(@Query("page") int i, @Query("pageSize") int i2, @Query("equipment") String str);

    @POST("/user/updateAccount")
    Call<ConmdBean> updateAccount(@Body ZFBAccountRequest zFBAccountRequest);

    @POST("/user/updateUser")
    Call<ConmdBean> updateUser(@Body UserInfoBean.UserBean userBean);

    @POST("/user/login")
    Call<ConmdBean> userLogin(@Body PasswordLoginRequest passwordLoginRequest);

    @POST("/user/valiteVersion")
    Call<ConmdBean<String>> validateVersion();
}
