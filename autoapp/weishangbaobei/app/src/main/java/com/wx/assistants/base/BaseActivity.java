package com.wx.assistants.base;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.fb.jjyyzjy.watermark.utils.BitmapUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMusic;
import com.wx.assistants.activity.DeviceChangeActivity;
import com.wx.assistants.activity.LaunchActivity;
import com.wx.assistants.activity.LoginActivity;
import com.wx.assistants.activity.MainActivity;
import com.wx.assistants.activity.MemberCenterListActivity;
import com.wx.assistants.activity.PreviewSingleImageActivity;
import com.wx.assistants.activity.PreviewSingleVideoActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.bean.VoiceBean;
import com.wx.assistants.dialog.config.MToastConfig;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.service.AccessibilityFloatWindowOpenService;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.AndroidBarUtils;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.CheckRuleValidateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.OpenAccessibilitySettingHelper;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.StartOtherAppUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import com.yongchun.library.view.ImageSelectorActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import jpush.ExampleUtil;
import jpush.LocalBroadcastManager;
import jpush.TagAliasOperatorHelper;
import permission.PermissionListener;
import permission.rom.FloatManager;

public class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TITLE = "title";
    public static final String TAG = "★微信辅助★";
    public static boolean isForeground;
    /* access modifiers changed from: private */
    public String appendSign = "";
    private OnUserInfoListener infoListener;
    /* access modifiers changed from: private */
    public boolean isCheckJumpWX = false;
    /* access modifiers changed from: private */
    public boolean isExecuteVoiceSend = false;
    public boolean isHasAuthority = false;
    /* access modifiers changed from: private */
    public boolean isPause = false;
    private LoadingDialog loadingDialog;
    private OnAlbumResultListener mListener;
    private MessageReceiver mMessageReceiver;
    private BGASwipeBackHelper mSwipeBackHelper;
    /* access modifiers changed from: private */
    public OperationParameterModel mmodel;
    /* access modifiers changed from: private */
    public int savePicImageIndex = 0;
    /* access modifiers changed from: private */
    public String taskNum = "-1";
    private int wxVersionCode;
    private String wxVersionName;

    public interface OnAlbumResultListener {
        void result(ArrayList<String> arrayList);
    }

    public interface OnLoadingDownListener {
        void downEnd();

        void progress(int i);
    }

    public interface OnSaveImgListener {
        void saveFail();

        void saveSuccess();
    }

    public interface OnStartCheckListener {
        void checkEnd();
    }

    public interface OnUserInfoListener {
        void setUserInfo(UserInfoBean.UserBean userBean);
    }

    static {
        StubApp.interface11(6763);
    }

    /* access modifiers changed from: private */
    public void setCostomMsg(String str) {
    }

    public boolean isSupportSwipeBack() {
        return true;
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onSwipeBackLayoutCancel() {
    }

    public void onSwipeBackLayoutSlide(float f) {
    }

    static /* synthetic */ int access$608(BaseActivity baseActivity) {
        int i = baseActivity.savePicImageIndex;
        baseActivity.savePicImageIndex = i + 1;
        return i;
    }

    public String getAppendSign() {
        return this.appendSign;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
    public void back() {
        if (this.mSwipeBackHelper != null) {
            this.mSwipeBackHelper.backward();
            AppManager.getAppManager().removeActivityFromStack(this);
            return;
        }
        AppManager.getAppManager().finishActivity((Activity) this);
    }

    public void setOnAlbumResultListener(OnAlbumResultListener onAlbumResultListener) {
        this.mListener = onAlbumResultListener;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        BaseActivity.super.onStart();
        MyApplication.isValidationService = false;
        try {
            MyApplication.isNeedSign = ((Boolean) SPUtils.get(this, "isNeedSign", false)).booleanValue();
            if (!MyApplication.isNeedSign) {
                this.appendSign = "";
            }
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception unused) {
        }
        if (!(this instanceof MainActivity) && !(this instanceof LaunchActivity)) {
            try {
                AndroidBarUtils.setBarDarkMode(this, true);
            } catch (Exception unused2) {
                LogUtils.log("WS_BABY_BaseActivity_onStart_error");
            }
        }
    }

    public void setOnUserInfoListener(OnUserInfoListener onUserInfoListener) {
        this.infoListener = onUserInfoListener;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                if (this.infoListener != null) {
                    this.infoListener.setUserInfo(userBean);
                }
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
                initJPushInterface();
            } catch (Exception unused) {
            }
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                String str2 = userBean.getLevelName() + "";
                if (str2 == null) {
                    MyApplication.isNeedSign = false;
                    this.appendSign = "";
                    SPUtils.put(this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                } else if (str2.contains("体验")) {
                    MyApplication.isNeedSign = true;
                    SPUtils.put(this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                    this.appendSign = "  内容由微商宝贝推送、免费试用： " + MyApplication.SHARE_DOWN_URL;
                } else {
                    MyApplication.isNeedSign = false;
                    this.appendSign = "";
                    SPUtils.put(this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                }
            } else {
                MyApplication.isNeedSign = false;
                this.appendSign = "";
                SPUtils.put(this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
            }
        }
        if (userBean != null) {
            String memberStatus2 = userBean.getMemberStatus();
            if ("1".equals(memberStatus2) || "1.0".equals(memberStatus2)) {
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.isHasAuthority = false;
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.isHasAuthority = true;
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.isHasAuthority = true;
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.isHasAuthority = true;
                } else {
                    this.isHasAuthority = false;
                }
            } else {
                this.isHasAuthority = false;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void imgLongClickPreview(List<String> list) {
        String str;
        if (list != null && list.size() > 0 && (str = list.get(0)) != null) {
            if (str.endsWith(PictureFileUtils.POST_VIDEO)) {
                Intent intent = new Intent(this, PreviewSingleVideoActivity.class);
                intent.putExtra("videoUrl", str);
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(this, PreviewSingleImageActivity.class);
            intent2.putExtra("imgUrl", str);
            startActivity(intent2);
        }
    }

    public void getUserInfoData() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r4v3, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r4v9, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|24) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004f */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r4) {
                /*
                    r3 = this;
                    if (r4 == 0) goto L_0x0074
                    r0 = 0
                    int r1 = r4.getCode()     // Catch:{ Exception -> 0x0055 }
                    r2 = 1
                    if (r1 != r2) goto L_0x0033
                    java.lang.String r1 = r4.getMessage()     // Catch:{ Exception -> 0x0055 }
                    if (r1 == 0) goto L_0x0033
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r1 = "请重新登录"
                    boolean r4 = r4.contains(r1)     // Catch:{ Exception -> 0x0055 }
                    if (r4 == 0) goto L_0x0074
                    com.wx.assistants.application.MyApplication.isNeedSign = r0     // Catch:{ Exception -> 0x0055 }
                    com.wx.assistants.base.BaseActivity r4 = com.wx.assistants.base.BaseActivity.this     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r1 = ""
                    java.lang.String unused = r4.appendSign = r1     // Catch:{ Exception -> 0x0055 }
                    com.wx.assistants.base.BaseActivity r4 = com.wx.assistants.base.BaseActivity.this     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r1 = "isNeedSign"
                    boolean r2 = com.wx.assistants.application.MyApplication.isNeedSign     // Catch:{ Exception -> 0x0055 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0055 }
                    com.wx.assistants.utils.SPUtils.put(r4, r1, r2)     // Catch:{ Exception -> 0x0055 }
                    goto L_0x0074
                L_0x0033:
                    int r1 = r4.getCode()     // Catch:{ Exception -> 0x0055 }
                    if (r1 != 0) goto L_0x0074
                    com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0055 }
                    r1.<init>()     // Catch:{ Exception -> 0x0055 }
                    java.lang.Object r4 = r4.getData()     // Catch:{ Exception -> 0x0055 }
                    java.lang.String r4 = r1.toJson(r4)     // Catch:{ Exception -> 0x0055 }
                    android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x004f }
                    java.lang.String r2 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r1, r2, r4)     // Catch:{ Exception -> 0x004f }
                L_0x004f:
                    com.wx.assistants.base.BaseActivity r1 = com.wx.assistants.base.BaseActivity.this     // Catch:{ Exception -> 0x0055 }
                    r1.setUserInfo(r4)     // Catch:{ Exception -> 0x0055 }
                    goto L_0x0074
                L_0x0055:
                    r4 = move-exception
                    java.lang.String r1 = "WS_BABY_Catch_35"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    r4.printStackTrace()
                    com.wx.assistants.application.MyApplication.isNeedSign = r0
                    com.wx.assistants.base.BaseActivity r4 = com.wx.assistants.base.BaseActivity.this
                    java.lang.String r0 = ""
                    java.lang.String unused = r4.appendSign = r0
                    com.wx.assistants.base.BaseActivity r4 = com.wx.assistants.base.BaseActivity.this
                    java.lang.String r0 = "isNeedSign"
                    boolean r1 = com.wx.assistants.application.MyApplication.isNeedSign
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                    com.wx.assistants.utils.SPUtils.put(r4, r0, r1)
                L_0x0074:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.base.BaseActivity.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            /* JADX WARNING: type inference failed for: r3v3, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    MyApplication.isNeedSign = false;
                    String unused = BaseActivity.this.appendSign = "";
                    SPUtils.put(BaseActivity.this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                }
            }
        });
    }

    public void startWX(OperationParameterModel operationParameterModel) {
        boolean z = MyApplication.isValidationService;
        runWx(operationParameterModel);
    }

    public void startWXCompany(OperationParameterModel operationParameterModel) {
        runWxCompany(operationParameterModel);
    }

    public void initWXVersion() {
        try {
            this.wxVersionName = PackageUtils.getWXVersionName(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.wxVersionCode = PackageUtils.getWXVersionCode(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0032 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048 A[Catch:{ Exception -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0093 A[Catch:{ Exception -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0 A[Catch:{ Exception -> 0x00be }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void runWx(com.wx.assistants.bean.OperationParameterModel model/*r4*/) {
        /*
            1. 检查辅助功能权限
            2. 检查浮动窗权限
            r3 = this;
            boolean r0 = r3.isAccessibilitySettingsOn()     // Catch:{ Exception -> 0x00be }
            if (r0 == 0) goto L_0x00b1
            permission.rom.FloatManager r0 = permission.rom.FloatManager.getInstance()     // Catch:{ Exception -> 0x00be }
            boolean r0 = r0.checkPermission(r3)     // Catch:{ Exception -> 0x00be }
            if (r0 == 0) goto L_0x00ad
            com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x0021 }
            com.wx.assistants.bean.OperationParameterModel r0 = r0.getOperationParameterModel()     // Catch:{ Exception -> 0x0021 }
            r3.mmodel = r0     // Catch:{ Exception -> 0x0021 }
            com.wx.assistants.bean.OperationParameterModel r0 = r3.mmodel     // Catch:{ Exception -> 0x0021 }
            java.lang.String r0 = r0.getTaskNum()     // Catch:{ Exception -> 0x0021 }
            r3.taskNum = r0     // Catch:{ Exception -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00be }
        L_0x0025:
            3. 启动辅助功能服务
            Intent intent = new android.content.Intent();
            intent.setClass(this, com.wx.assistants.service.AutoService.class);
            this.startService(intent);
        L_0x0032:
            this.statisticalCounting(r4)     // Catch:{ Exception -> 0x0035 }
        L_0x0035:
            java.lang.String r0 = "★微信辅助★"
            java.lang.String r1 = "辅助功能已开启！"
            android.util.Log.d(r0, r1)     // Catch:{ Exception -> 0x00be }
            java.lang.String r0 = "50"
            java.lang.String r1 = r3.taskNum     // Catch:{ Exception -> 0x00be }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x00be }
            if (r0 != 0) goto L_0x0061
            java.lang.String r0 = "49"
            java.lang.String r1 = r3.taskNum     // Catch:{ Exception -> 0x00be }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x00be }
            if (r0 == 0) goto L_0x0053
            goto L_0x0061
        L_0x0053:
            //
            Thread thread = new java.lang.Thread(); // r4
            BaseActivity$4 r0 = new com.wx.assistants.base.BaseActivity$4(r0);
            thread.start();
            goto L_0x00c3
        L_0x0061:
            this.showLoadingDialog("正在处理转发数据")     // Catch:{ Exception -> 0x00be }
            r0 = 0
            this.isPause = false;
            this.isExecuteVoiceSend = false;
            this.isCheckJumpWX = false;

            String voicePath = model.getVoicePath()
            File file = new File();

            if (voicePath.endsWith(".amr")) {
                ApiWrapper apiWrapper = com.wx.assistants.globle.ApiWrapper.getInstance();
                apiWrapper.addAmrMaterial(
                    MultipartBody.Part.createFormData("file", file.getName(),
                    RequestBody.create(MediaType.parse("application/octet-stream"), file)),
                    new com.wx.assistants.base.BaseActivity$2()
                );
                return

            }

            com.wx.assistants.globle.ApiWrapper.getInstance().addMaterialToAli(part, new com.wx.assistants.base.BaseActivity$3());
            return

        L_0x00ad:
            this.toastFloatWindowOpenDialog(r3)     // Catch:{ Exception -> 0x00be }
            goto L_0x00c3
        L_0x00b1:
            Log.d("★微信辅助★", "辅助功能未开启！");
            r3.openAlertDialog();
            goto L_0x00c3
        L_0x00be:
            com.wx.assistants.utils.LogUtils.log("WS_BABY_runWx_ERROR2")
        L_0x00c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.base.BaseActivity.runWx(com.wx.assistants.bean.OperationParameterModel):void");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:(2:5|6)|10|11|12|13|14|16|17|(2:19|(1:21)(2:22|32))|23|(2:25|33)(2:26|34)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0028 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c A[Catch:{ Exception -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0097 A[Catch:{ Exception -> 0x00c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a4 A[Catch:{ Exception -> 0x00c2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void runWxCompany(com.wx.assistants.bean.OperationParameterModel r4) {
        /*
            r3 = this;
            boolean r0 = r3.isAccessibilitySettingsOn()     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x00b5
            permission.rom.FloatManager r0 = permission.rom.FloatManager.getInstance()     // Catch:{ Exception -> 0x00c2 }
            boolean r0 = r0.checkPermission(r3)     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x00b1
            com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x0021 }
            com.wx.assistants.bean.OperationParameterModel r0 = r0.getOperationParameterModel()     // Catch:{ Exception -> 0x0021 }
            r3.mmodel = r0     // Catch:{ Exception -> 0x0021 }
            com.wx.assistants.bean.OperationParameterModel r0 = r3.mmodel     // Catch:{ Exception -> 0x0021 }
            java.lang.String r0 = r0.getTaskNum()     // Catch:{ Exception -> 0x0021 }
            r3.taskNum = r0     // Catch:{ Exception -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00c2 }
        L_0x0025:
            r3.statisticalCounting(r4)     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x0039 }
            r0.<init>()     // Catch:{ Exception -> 0x0039 }
            android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0039 }
            java.lang.Class<com.wx.assistants.service.AutoService> r2 = com.wx.assistants.service.AutoService.class
            r0.setClass(r1, r2)     // Catch:{ Exception -> 0x0039 }
            r3.startService(r0)     // Catch:{ Exception -> 0x0039 }
        L_0x0039:
            java.lang.String r0 = "★微信辅助★"
            java.lang.String r1 = "辅助功能已开启！"
            android.util.Log.d(r0, r1)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r0 = "10050"
            java.lang.String r1 = r3.taskNum     // Catch:{ Exception -> 0x00c2 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x00c2 }
            if (r0 != 0) goto L_0x0065
            java.lang.String r0 = "10049"
            java.lang.String r1 = r3.taskNum     // Catch:{ Exception -> 0x00c2 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x0057
            goto L_0x0065
        L_0x0057:
            java.lang.Thread r4 = new java.lang.Thread     // Catch:{ Exception -> 0x00c2 }
            com.wx.assistants.base.BaseActivity$7 r0 = new com.wx.assistants.base.BaseActivity$7     // Catch:{ Exception -> 0x00c2 }
            r0.<init>()     // Catch:{ Exception -> 0x00c2 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x00c2 }
            r4.start()     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c7
        L_0x0065:
            java.lang.String r0 = "正在处理转发数据"
            r3.showLoadingDialog(r0)     // Catch:{ Exception -> 0x00c2 }
            r0 = 0
            r3.isPause = r0     // Catch:{ Exception -> 0x00c2 }
            r3.isExecuteVoiceSend = r0     // Catch:{ Exception -> 0x00c2 }
            r3.isCheckJumpWX = r0     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r4 = r4.getVoicePath()     // Catch:{ Exception -> 0x00c2 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x00c2 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r1 = "application/octet-stream"
            okhttp3.MediaType r1 = okhttp3.MediaType.parse(r1)     // Catch:{ Exception -> 0x00c2 }
            okhttp3.RequestBody r1 = okhttp3.RequestBody.create((okhttp3.MediaType) r1, (java.io.File) r0)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = "file"
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x00c2 }
            okhttp3.MultipartBody$Part r0 = okhttp3.MultipartBody.Part.createFormData(r2, r0, r1)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r1 = ".amr"
            boolean r4 = r4.endsWith(r1)     // Catch:{ Exception -> 0x00c2 }
            if (r4 == 0) goto L_0x00a4
            com.wx.assistants.globle.ApiWrapper r4 = com.wx.assistants.globle.ApiWrapper.getInstance()     // Catch:{ Exception -> 0x00c2 }
            com.wx.assistants.base.BaseActivity$5 r1 = new com.wx.assistants.base.BaseActivity$5     // Catch:{ Exception -> 0x00c2 }
            r1.<init>()     // Catch:{ Exception -> 0x00c2 }
            r4.addAmrMaterial(r0, r1)     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c7
        L_0x00a4:
            com.wx.assistants.globle.ApiWrapper r4 = com.wx.assistants.globle.ApiWrapper.getInstance()     // Catch:{ Exception -> 0x00c2 }
            com.wx.assistants.base.BaseActivity$6 r1 = new com.wx.assistants.base.BaseActivity$6     // Catch:{ Exception -> 0x00c2 }
            r1.<init>()     // Catch:{ Exception -> 0x00c2 }
            r4.addMaterialToAli(r0, r1)     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c7
        L_0x00b1:
            r3.toastFloatWindowOpenDialog(r3)     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c7
        L_0x00b5:
            java.lang.String r4 = "★微信辅助★"
            java.lang.String r0 = "辅助功能未开启！"
            android.util.Log.d(r4, r0)     // Catch:{ Exception -> 0x00c2 }
            r3.openAlertDialog()     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c7
        L_0x00c2:
            java.lang.String r4 = "WS_BABY_runWx_ERROR2"
            com.wx.assistants.utils.LogUtils.log(r4)
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.base.BaseActivity.runWxCompany(com.wx.assistants.bean.OperationParameterModel):void");
    }

    public void sharedVoice(OperationParameterModel operationParameterModel, final VoiceBean voiceBean) {
        runOnUiThread(new Runnable() {
            /* JADX WARNING: type inference failed for: r3v4, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r2v9, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r2v11, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void run() {
                String mp3Url = voiceBean.getMp3Url();
                UMusic uMusic = new UMusic(mp3Url);
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_mp3url= " + mp3Url);
                PrintStream printStream2 = System.out;
                printStream2.println("WS_BABY_h5url= " + voiceBean.getH5Url());
                OperationParameterModel operationParameterModel = MyApplication.instance.getOperationParameterModel();
                uMusic.setTitle(operationParameterModel.getVoiceTitle());
                uMusic.setDescription(operationParameterModel.getVoiceDesc());
                uMusic.setThumb(new UMImage((Context) BaseActivity.this, 2131558404));
                uMusic.setmTargetUrl(voiceBean.getH5Url());
                if (operationParameterModel.getOtherSendType() == 0) {
                    new ShareAction(BaseActivity.this).withMedia(uMusic).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE.toSnsPlatform().mPlatform).setCallback(new UMShareListener() {
                        public void onStart(SHARE_MEDIA share_media) {
                            boolean unused = BaseActivity.this.isExecuteVoiceSend = true;
                            LogUtils.log("WS_BABY_auto_service_onStart");
                            final AutoService autoService = MyApplication.instance.getAutoService();
                            new Thread(new Runnable() {
                                public void run() {
                                    int i = 0;
                                    while (i < 1000) {
                                        if (BaseActivity.this.isPause) {
                                            LogUtils.log("WS_BABY_auto_service_isPause");
                                            return;
                                        }
                                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "收藏");
                                        if (!findNodeIsExistByText || !findNodeIsExistByText2) {
                                            try {
                                                Thread.sleep(200);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            i++;
                                        } else {
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e2) {
                                                e2.printStackTrace();
                                            }
                                            boolean unused = BaseActivity.this.isCheckJumpWX = true;
                                            LogUtils.log("WS_BABY_auto_service_has_collection");
                                            PerformClickUtils.findTextAndClick((AccessibilityService) MyApplication.instance.getAutoService(), "收藏", false);
                                            BaseActivity.this.runOnUiThread(new Runnable() {
                                                public void run() {
                                                    MyApplication.instance.getMyWindowManager().showFloatWindow(BaseActivity.this.taskNum);
                                                }
                                            });
                                            return;
                                        }
                                    }
                                }
                            }).start();
                        }

                        public void onResult(SHARE_MEDIA share_media) {
                            LogUtils.log("WS_BABY_auto_service_onResult" + share_media.toString());
                            if (BaseActivity.this.isCheckJumpWX && share_media == SHARE_MEDIA.WEIXIN_FAVORITE) {
                                try {
                                    Thread.sleep(1000);
                                    LogUtils.log("WS_BABY_auto_service_isCheckJumpWX");
                                    if (PerformClickUtils.findNodeIsExistByText(MyApplication.instance.getAutoService(), "启动微信")) {
                                        LogUtils.log("WS_BABY_auto_service_isCheckJumpWX.0");
                                        new Thread(new Runnable() {
                                            public void run() {
                                                BaseActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        StartOtherAppUtils.startWeChat();
                                                        MyApplication.instance.getMyWindowManager().showFloatWindow(BaseActivity.this.taskNum);
                                                    }
                                                });
                                            }
                                        }).start();
                                        return;
                                    }
                                    LogUtils.log("WS_BABY_auto_service_isCheckJumpWX.1");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        public void onError(SHARE_MEDIA share_media, Throwable th) {
                            LogUtils.log("WS_BABY_auto_service_onError");
                        }

                        public void onCancel(SHARE_MEDIA share_media) {
                            LogUtils.log("WS_BABY_auto_service_onCancel");
                        }
                    }).share();
                    return;
                }
                boolean unused = BaseActivity.this.isExecuteVoiceSend = false;
                boolean unused2 = BaseActivity.this.isPause = false;
                new ShareAction(BaseActivity.this).withMedia(uMusic).setPlatform(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                MyWindowManager myWindowManager = MyApplication.instance.getMyWindowManager();
                if (myWindowManager != null) {
                    myWindowManager.toastToUser3("微信启动后，请手动选择您要群发的对象", 4000, (MyWindowManager.OnToastEndListener) null);
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void openAlertDialog() {
        DialogUIUtils.openAncillaryServices(this, "\n1.开启辅助服务，微商宝贝才能完成一系列自动化操作。\n2.进入辅助页面后，找到微商宝贝，辅助开关打开即可。\n3.若发现已经开启，请先关闭在开启一次。", new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r2v1, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r2v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onClick(View view) {
                OpenAccessibilitySettingHelper.jumpToSettingPage(BaseActivity.this);
                AccessibilityFloatWindowOpenService.startWithOpen(BaseActivity.this, 0);
            }
        });
    }

    public void toastFloatWindowOpenDialog(final Context context) {
        DialogUIUtils.openFloatWindowServices(context, new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r2v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onClick(View view) {
                FloatManager.getInstance().applyPermission(context);
                AccessibilityFloatWindowOpenService.startWithOpen(BaseActivity.this, 1);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void checkUserRule(final OperationParameterModel operationParameterModel, final CheckRuleValidateUtils.CheckResultListener2 checkResultListener2) {
        if ("".equals((String) SPUtils.get(this, "user_token", ""))) {
            LogUtils.log("WS_BABY_GO_LOGIN");
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("isNeedBack", true);
            startActivity(intent);
        } else if ("1".equals(operationParameterModel.getTaskNum()) || "65".equals(operationParameterModel.getTaskNum()) || "5".equals(operationParameterModel.getTaskNum()) || "11".equals(operationParameterModel.getTaskNum()) || "14".equals(operationParameterModel.getTaskNum()) || "140".equals(operationParameterModel.getTaskNum())) {
            if (checkResultListener2 != null) {
                checkResultListener2.permitExecution(operationParameterModel);
            }
        } else if (!"13".equals(operationParameterModel.getTaskNum()) && !"27".equals(operationParameterModel.getTaskNum()) && !"38".equals(operationParameterModel.getTaskNum()) && !"49".equals(operationParameterModel.getTaskNum()) && !"50".equals(operationParameterModel.getTaskNum()) && !"58".equals(operationParameterModel.getTaskNum()) && !"59".equals(operationParameterModel.getTaskNum()) && !"66".equals(operationParameterModel.getTaskNum())) {
            showLoadingDialog("..正在启动..");
            CheckRuleValidateUtils.getInstance(this).checkRuleValidate(operationParameterModel.getTaskNum(), new CheckRuleValidateUtils.CheckResultListener() {
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    if (checkResultListener2 != null) {
                        checkResultListener2.permitExecution(operationParameterModel);
                    }
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(BaseActivity.this, conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r4v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
            });
        } else if (!this.isHasAuthority) {
            DialogUIUtils.dialogOpenMember(this, "此功能仅对半年以上会员用户授权使用,请充值半年以上会员", (View.OnClickListener) null, new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void onClick(View view) {
                    BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                }
            });
        } else if (checkResultListener2 != null) {
            checkResultListener2.permitExecution(operationParameterModel);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void checkUserRule(final Class cls) {
        if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            showLoadingDialog("请耐心等待");
            CheckRuleValidateUtils.getInstance(this).checkRuleValidate("40", new CheckRuleValidateUtils.CheckResultListener() {
                /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    BaseActivity.this.startActivity(new Intent(BaseActivity.this, cls));
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r4v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
            });
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isNeedBack", true);
        startActivity(intent);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectMultiAlbum(final int i, OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.startImageVideo((Activity) BaseActivity.this, i, 1, true, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectMultiPicAlbum(final int i, OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.start((Activity) BaseActivity.this, i, 1, true, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectMultiVideoAlbum(final int i, OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.startVideo((Activity) BaseActivity.this, i, 1, false, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void checkUserVideoEditRule() {
        if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            showLoadingDialog("请耐心等待");
            CheckRuleValidateUtils.getInstance(this).checkRuleValidate("42", new CheckRuleValidateUtils.CheckResultListener() {
                /* JADX WARNING: type inference failed for: r5v2, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    PictureSelector.create((Activity) BaseActivity.this).openGallery(PictureMimeType.ofAll()).theme(R.style.picture_default_style).maxSelectNum(9).minSelectNum(1).imageSpanCount(4).selectionMode(2).previewImage(false).previewVideo(false).enablePreviewAudio(false).isCamera(true).isZoomAnim(false).compress(false).synOrAsy(true).glideOverride(160, 160).withAspectRatio(3, 4).hideBottomControls(true).isGif(true).freeStyleCropEnabled(true).circleDimmedLayer(false).showCropFrame(true).showCropGrid(true).openClickSound(false).minimumCompressSize(100).forResult(PictureConfig.CHOOSE_REQUEST);
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r4v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
            });
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isNeedBack", true);
        startActivity(intent);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper$Delegate, android.app.Activity] */
    private void initSwipeBackFinish() {
        this.mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        this.mSwipeBackHelper.setSwipeBackEnable(true);
        this.mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        this.mSwipeBackHelper.setIsWeChatStyle(true);
        this.mSwipeBackHelper.setShadowResId(2131230866);
        this.mSwipeBackHelper.setIsNeedShowShadow(true);
        this.mSwipeBackHelper.setIsShadowAlphaGradient(true);
        this.mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        this.mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(4:3|4|5|6)|9|10|(3:14|(3:17|(2:22|19)|15)|23)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009a A[Catch:{ Exception -> 0x00a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isAccessibilitySettingsOn() {
        /*
            r8 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a5 }
            r1.<init>()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = r8.getPackageName()     // Catch:{ Exception -> 0x00a5 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<com.wx.assistants.service.AutoService> r2 = com.wx.assistants.service.AutoService.class
            java.lang.String r2 = r2.getCanonicalName()     // Catch:{ Exception -> 0x00a5 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00a5 }
            android.content.Context r2 = r8.getApplicationContext()     // Catch:{ SettingNotFoundException -> 0x004e }
            android.content.Context r2 = com.stub.StubApp.getOrigApplicationContext(r2)     // Catch:{ SettingNotFoundException -> 0x004e }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x004e }
            java.lang.String r3 = "accessibility_enabled"
            int r2 = android.provider.Settings.Secure.getInt(r2, r3)     // Catch:{ SettingNotFoundException -> 0x004e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.<init>()     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r4 = "WS_BABY_A_"
            r3.append(r4)     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.append(r1)     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r4 = ","
            r3.append(r4)     // Catch:{ SettingNotFoundException -> 0x004f }
            r3.append(r2)     // Catch:{ SettingNotFoundException -> 0x004f }
            java.lang.String r3 = r3.toString()     // Catch:{ SettingNotFoundException -> 0x004f }
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ SettingNotFoundException -> 0x004f }
            goto L_0x004f
        L_0x004e:
            r2 = 0
        L_0x004f:
            android.text.TextUtils$SimpleStringSplitter r3 = new android.text.TextUtils$SimpleStringSplitter     // Catch:{ Exception -> 0x00a5 }
            r4 = 58
            r3.<init>(r4)     // Catch:{ Exception -> 0x00a5 }
            r4 = 1
            if (r2 != r4) goto L_0x00a5
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ Exception -> 0x00a5 }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ Exception -> 0x00a5 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r6 = "enabled_accessibility_services"
            java.lang.String r5 = android.provider.Settings.Secure.getString(r5, r6)     // Catch:{ Exception -> 0x00a5 }
            if (r5 == 0) goto L_0x00a5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a5 }
            r6.<init>()     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r7 = "WS_BABY_B_"
            r6.append(r7)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r1)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r7 = ","
            r6.append(r7)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r2)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = ","
            r6.append(r2)     // Catch:{ Exception -> 0x00a5 }
            r6.append(r5)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r2 = r6.toString()     // Catch:{ Exception -> 0x00a5 }
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x00a5 }
            r3.setString(r5)     // Catch:{ Exception -> 0x00a5 }
        L_0x0094:
            boolean r2 = r3.hasNext()     // Catch:{ Exception -> 0x00a5 }
            if (r2 == 0) goto L_0x00a5
            java.lang.String r2 = r3.next()     // Catch:{ Exception -> 0x00a5 }
            boolean r2 = r2.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x00a5 }
            if (r2 == 0) goto L_0x0094
            return r4
        L_0x00a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.base.BaseActivity.isAccessibilitySettingsOn():boolean");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* access modifiers changed from: protected */
    public void onDestroy() {
        BaseActivity.super.onDestroy();
        System.out.println("WS_BABY_BASE_ACTIVITY_onDestroy");
        AppManager.getAppManager().finishActivity((Activity) this);
        this.isPause = false;
        try {
            if (this.mMessageReceiver != null) {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mMessageReceiver);
            }
        } catch (Exception unused) {
        }
    }

    public void toastToUser(String str) {
        new MToastConfig.Builder().setGravity(MToastConfig.MToastGravity.BOTTOM, 0, 30).setTextColor(getResources().getColor(com.ilike.voicerecorder.R.color.color_ffffff)).setBackgroundColor(getResources().getColor(R.color.main_color)).setBackgroundCornerRadius(10.0f).setBackgroundStrokeColor(-1).setBackgroundStrokeWidth(1.0f).setTextSize(15.0f).setPadding(80, 30, 80, 30).build();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void showLoadingDialog(String str) {
        this.loadingDialog = new LoadingDialog(this);
        this.loadingDialog.setInterceptBack(false);
        this.loadingDialog.setLoadingText(str).show();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void showLoadingDialog(String str, boolean z) {
        this.loadingDialog = new LoadingDialog(this);
        this.loadingDialog.setInterceptBack(!z);
        this.loadingDialog.setLoadingText(str).show();
    }

    public void updateLoadingDialog(String str) {
        if (this.loadingDialog != null) {
            this.loadingDialog.setLoadingText(str);
        }
    }

    public void dismissLoadingDialog() {
        if (this.loadingDialog != null) {
            this.loadingDialog.close();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectSingleAlbum(OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.start((Activity) BaseActivity.this, 1, 2, false, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectSingleVideoAlbum(OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.startVideo((Activity) BaseActivity.this, 1, 2, false, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectSingleImageVideoAlbum(OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.startImageVideo((Activity) BaseActivity.this, 1, 2, true, false, false);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void selectMoreAlbum(OnAlbumResultListener onAlbumResultListener) {
        if (onAlbumResultListener != null) {
            setOnAlbumResultListener(onAlbumResultListener);
        }
        PermissionUtils.checkCameraAndExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            public void permissionGranted(@NonNull String[] strArr) {
                ImageSelectorActivity.start((Activity) BaseActivity.this, 9, 1, false, false, false);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 66) {
            try {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("outputList");
                if (this.mListener != null) {
                    this.mListener.result(stringArrayListExtra);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void savePicImages(List<String> list, OnLoadingDownListener onLoadingDownListener) {
        this.savePicImageIndex = 0;
        saveSendImages(list, onLoadingDownListener);
    }

    /* access modifiers changed from: private */
    public void saveSendImages(final List<String> list, final OnLoadingDownListener onLoadingDownListener) {
        try {
            if (this.savePicImageIndex >= list.size()) {
                onLoadingDownListener.downEnd();
                return;
            }
            onLoadingDownListener.progress(this.savePicImageIndex);
            if (list.get(this.savePicImageIndex).endsWith(PictureFileUtils.POST_VIDEO)) {
                compressSaveVideo(list.get(this.savePicImageIndex), (FileUtil.OnConvertMp4Listener) new FileUtil.OnConvertMp4Listener() {
                    public void convertProgress(int i) {
                    }

                    public void convertSuccess(String str) {
                        BaseActivity.access$608(BaseActivity.this);
                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                    }

                    public void convertFail() {
                        BaseActivity.access$608(BaseActivity.this);
                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                    }
                });
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                BaseActivity.this.compressSaveImg((String) list.get(BaseActivity.this.savePicImageIndex), (OnSaveImgListener) new OnSaveImgListener() {
                                    public void saveSuccess() {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        BaseActivity.access$608(BaseActivity.this);
                                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                                    }

                                    public void saveFail() {
                                        BaseActivity.access$608(BaseActivity.this);
                                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        } catch (Exception unused) {
        }
    }

    public void compressSaveImg(String str, final OnSaveImgListener onSaveImgListener) {
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditFile().getAbsolutePath();
            BitmapUtils.saveOriginBitmap(str, absolutePath, new BitmapUtils.OnSaveBitmapListener() {
                public void saveSuccess() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void run() {
                            AlbumUtil.insertImageToMediaStore(BaseActivity.this, absolutePath);
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveSuccess();
                            }
                        }
                    });
                }

                public void saveFail() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveFail();
                            }
                        }
                    });
                }
            });
        } catch (Exception unused) {
            File file = new File(str);
            file.renameTo(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"));
            File file2 = new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png");
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file2));
            sendBroadcast(intent);
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), file2.getAbsolutePath(), file2.getName(), (String) null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(file2.getAbsolutePath())));
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"))));
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void compressSaveImg(List<String> list, final OnSaveImgListener onSaveImgListener) {
        LogUtils.log("@@@@@@@@@@==" + list.get(0));
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditFile().getAbsolutePath();
            BitmapUtils.saveOriginBitmap(list.get(0), absolutePath, new BitmapUtils.OnSaveBitmapListener() {
                public void saveSuccess() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void run() {
                            AlbumUtil.insertImageToMediaStore(BaseActivity.this, absolutePath);
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveSuccess();
                            }
                        }
                    });
                }

                public void saveFail() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveFail();
                            }
                        }
                    });
                }
            });
        } catch (Exception unused) {
            LogUtils.log("@@@@@@@@@@==" + list.get(0));
            File file = new File(list.get(0));
            file.renameTo(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"));
            File file2 = new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png");
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file2));
            sendBroadcast(intent);
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), file2.getAbsolutePath(), file2.getName(), (String) null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(file2.getAbsolutePath())));
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"))));
        }
        Toast.makeText(this, "保存成功", 0).show();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void startCheck(String str, boolean z, OnStartCheckListener onStartCheckListener) {
        if (z) {
            try {
                if (!isAccessibilitySettingsOn()) {
                    Log.d(TAG, "辅助功能未开启！");
                    openAlertDialog();
                } else if (!FloatManager.getInstance().checkPermission(this)) {
                    toastFloatWindowOpenDialog(this);
                } else if (this.wxVersionCode >= 1360) {
                    LogUtils.log("WS_BABY_VerName" + this.wxVersionName + "CODE" + this.wxVersionCode);
                    checkMember(str, onStartCheckListener);
                } else {
                    ToastUtils.showToast(this, "目前只支持 微信【6.7.3 - 7.0.14】版本用户使用");
                }
            } catch (Exception unused) {
                LogUtils.log("WS_BABY_runWx_ERROR2");
            }
        } else {
            checkMember(str, onStartCheckListener);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void checkMember(String str, final OnStartCheckListener onStartCheckListener) {
        if ("".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            LogUtils.log("WS_BABY_GO_LOGIN");
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("isNeedBack", true);
            startActivity(intent);
        } else if ("1".equals(str) || "65".equals(str) || "5".equals(str) || "11".equals(str) || "14".equals(str) || "140".equals(str)) {
            if (onStartCheckListener != null) {
                onStartCheckListener.checkEnd();
            }
        } else if (!"13".equals(str) && !"27".equals(str) && !"38".equals(str) && !"49".equals(str) && !"50".equals(str) && !"58".equals(str) && !"59".equals(str) && !"66".equals(str)) {
            showLoadingDialog(".正在启动.");
            CheckRuleValidateUtils instance = CheckRuleValidateUtils.getInstance(this);
            instance.checkRuleValidate(str + "", new CheckRuleValidateUtils.CheckResultListener() {
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    if (onStartCheckListener != null) {
                        onStartCheckListener.checkEnd();
                    }
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r4v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
            });
        } else if (!this.isHasAuthority) {
            DialogUIUtils.dialogOpenMember(this, "此功能仅对半年以上会员用户授权使用,请充值半年以上会员", (View.OnClickListener) null, new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void onClick(View view) {
                    BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                }
            });
        } else if (onStartCheckListener != null) {
            onStartCheckListener.checkEnd();
        }
    }

    public void compressSaveVideo(String str, final FileUtil.OnConvertMp4Listener onConvertMp4Listener) {
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditVideoFile().getAbsolutePath();
            LogUtils.log("WS_BABY_" + absolutePath);
            FileUtil.mp4ToMp4(str, absolutePath, new FileUtil.OnConvertMp4Listener() {
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void convertSuccess(String str) {
                    LogUtils.log("WS_BABY.convertSuccess");
                    AlbumUtil.insertVideoToMediaStore2(BaseActivity.this, absolutePath);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertSuccess(str);
                    }
                }

                public void convertProgress(int i) {
                    LogUtils.log("WS_BABY." + i);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertProgress(i);
                    }
                }

                public void convertFail() {
                    LogUtils.log("WS_BABY.ERROR");
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertFail();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public void compressSaveVideo(List<String> list, final FileUtil.OnConvertMp4Listener onConvertMp4Listener) {
        LogUtils.log("WS_BABY_" + list.get(0));
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditVideoFile().getAbsolutePath();
            LogUtils.log("WS_BABY_" + absolutePath);
            FileUtil.mp4ToMp4(list.get(0), absolutePath, new FileUtil.OnConvertMp4Listener() {
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void convertSuccess(String str) {
                    LogUtils.log("WS_BABY.convertSuccess");
                    AlbumUtil.insertVideoToMediaStore2(BaseActivity.this, absolutePath);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertSuccess(str);
                    }
                }

                public void convertProgress(int i) {
                    LogUtils.log("WS_BABY." + i);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertProgress(i);
                    }
                }

                public void convertFail() {
                    LogUtils.log("WS_BABY.ERROR");
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertFail();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007a, code lost:
        if (r3.isExecuteVoiceSend == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0067, code lost:
        if (r3.isExecuteVoiceSend != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0069, code lost:
        r3.isPause = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        getUserInfoData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume() {
        /*
            r3 = this;
            com.wx.assistants.base.BaseActivity.super.onResume()
            r0 = 0
            r1 = 1
            isForeground = r1     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "back"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "end"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom_operation"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom_error"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "middle"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "praise_start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "comment_start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "back"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "end"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom_operation"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "bottom_error"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "middle"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "praise_start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "comment_start"
            com.wx.assistants.floatwindow.FloatWindow.destroy(r2)     // Catch:{ Exception -> 0x0071 }
            com.wx.assistants.application.MyApplication.countSend = r0     // Catch:{ Exception -> 0x0071 }
            com.wx.assistants.application.MyApplication.enforceable = r0
            boolean r0 = r3.isExecuteVoiceSend
            if (r0 == 0) goto L_0x006b
        L_0x0069:
            r3.isPause = r1
        L_0x006b:
            r3.getUserInfoData()     // Catch:{ Exception -> 0x007d }
            goto L_0x007d
        L_0x006f:
            r2 = move-exception
            goto L_0x007e
        L_0x0071:
            java.lang.String r2 = "WS_BABY_onResume_error"
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ all -> 0x006f }
            com.wx.assistants.application.MyApplication.enforceable = r0
            boolean r0 = r3.isExecuteVoiceSend
            if (r0 == 0) goto L_0x006b
            goto L_0x0069
        L_0x007d:
            return
        L_0x007e:
            com.wx.assistants.application.MyApplication.enforceable = r0
            boolean r0 = r3.isExecuteVoiceSend
            if (r0 == 0) goto L_0x0086
            r3.isPause = r1
        L_0x0086:
            r3.getUserInfoData()     // Catch:{ Exception -> 0x0089 }
        L_0x0089:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.base.BaseActivity.onResume():void");
    }

    public void onSwipeBackLayoutExecuted() {
        if (this.mSwipeBackHelper != null) {
            this.mSwipeBackHelper.swipeBackward();
        }
    }

    public void onBackPressed() {
        if (this.mSwipeBackHelper != null && !this.mSwipeBackHelper.isSliding()) {
            this.mSwipeBackHelper.backward();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void startActivity(Class cls, boolean z, boolean z2) {
        if (!z) {
            startActivity(new Intent(this, cls));
        } else if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            startActivity(new Intent(this, cls));
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("isNeedBack", z2);
            startActivity(intent);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void toastNilLogin() {
        String str = (String) SPUtils.get(this, "ws_baby_phone", "");
        if (str == null || "".equals(str)) {
            DialogUIUtils.goLogin(this, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, true);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public boolean isLoginIng() {
        String str = (String) SPUtils.get(this, "ws_baby_phone", "");
        return str != null && !"".equals(str);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void statisticalCounting(OperationParameterModel operationParameterModel) {
        if (operationParameterModel != null) {
            int parseInt = Integer.parseInt(operationParameterModel.getTaskNum());
            if (parseInt == 60) {
                MobclickAgent.onEvent(this, "NO_60_AUTO_PASS_NIL_VALIDATION");
                MobclickAgent.onEvent((Context) this, "NO_60_AUTO_PASS_NIL_VALIDATION", "自动通过免验证");
            } else if (parseInt == 1005) {
                MobclickAgent.onEvent(this, "NO_1005_SEND_FRIEND_COMPANY");
                MobclickAgent.onEvent((Context) this, "NO_1005_SEND_FRIEND_COMPANY", "群发企业好友");
            } else if (parseInt != 10012) {
                switch (parseInt) {
                    case 1:
                        MobclickAgent.onEvent(this, "NO_1_DETECT_ZOMBIE");
                        MobclickAgent.onEvent((Context) this, "NO_1_DETECT_ZOMBIE", "检测僵尸粉");
                        return;
                    case 2:
                        MobclickAgent.onEvent(this, "NO_2_ADD_NEARBY_PEOPLE");
                        MobclickAgent.onEvent((Context) this, "NO_2_ADD_NEARBY_PEOPLE", "添加附近人");
                        return;
                    case 3:
                        MobclickAgent.onEvent(this, "NO_3_ONE_KEY_FORWARD");
                        MobclickAgent.onEvent((Context) this, "NO_3_ONE_KEY_FORWARD", "一键转发");
                        return;
                    case 4:
                        MobclickAgent.onEvent(this, "NO_4_BATCH_SEND_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_4_BATCH_SEND_FRIEND", "分批群发");
                        return;
                    case 5:
                        MobclickAgent.onEvent(this, "NO_5_SEND_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_5_SEND_FRIEND", "群发好友");
                        return;
                    case 6:
                        MobclickAgent.onEvent(this, "NO_6_SEND_GROUP_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_6_SEND_GROUP_GROUP", "群发群");
                        return;
                    case 7:
                        MobclickAgent.onEvent(this, "NO_7_TAG_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_7_TAG_FRIEND", "群发标签好友");
                        return;
                    case 8:
                        MobclickAgent.onEvent(this, "NO_8_AUTOMATICALLY_PULL_PEOPLE_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_8_AUTOMATICALLY_PULL_PEOPLE_GROUP", "自动拉人进群");
                        return;
                    case 9:
                        MobclickAgent.onEvent(this, "NO_9_ONE_KEY_CLICK_COMMENT");
                        MobclickAgent.onEvent((Context) this, "NO_9_ONE_KEY_CLICK_COMMENT", "一键点赞_评论");
                        return;
                    case 10:
                        MobclickAgent.onEvent(this, "NO_10_CARD_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_10_CARD_GROUP", "群名片到群");
                        return;
                    case 11:
                        MobclickAgent.onEvent(this, "NO_11_LABEL_SELECT");
                        MobclickAgent.onEvent((Context) this, "NO_11_LABEL_SELECT", "标签选择");
                        return;
                    case 12:
                        MobclickAgent.onEvent(this, "NO_12_GROUP_INNER_FANS");
                        MobclickAgent.onEvent((Context) this, "NO_12_GROUP_INNER_FANS", "群内加粉");
                        return;
                    case 13:
                        MobclickAgent.onEvent(this, "NO_13_CLONE_CIRCLE");
                        MobclickAgent.onEvent((Context) this, "NO_13_CLONE_CIRCLE", "克隆朋友圈");
                        return;
                    case 14:
                        MobclickAgent.onEvent(this, "NO_14_SELECT_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_14_SELECT_GROUP", "选择群");
                        return;
                    case 15:
                        MobclickAgent.onEvent(this, "NO_15_WINDOW_FORWARD");
                        MobclickAgent.onEvent((Context) this, "NO_15_WINDOW_FORWARD", "窗口转发");
                        return;
                    case 16:
                        MobclickAgent.onEvent(this, "NO_16_CONTACT_Add_FRIENDS");
                        MobclickAgent.onEvent((Context) this, "NO_16_CONTACT_Add_FRIENDS", "通讯录添加好友");
                        return;
                    case 17:
                        MobclickAgent.onEvent(this, "NO_17_TAG_OUTSIZE_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_17_TAG_OUTSIZE_FRIEND", "群发标签以外好友");
                        return;
                    case 18:
                        MobclickAgent.onEvent(this, "NO_18_COLLECTION_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_18_COLLECTION_GROUP", "群发收藏到群");
                        return;
                    case 19:
                        MobclickAgent.onEvent(this, "NO_19_COLLECTION_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_19_COLLECTION_FRIEND", "群发收藏到好友");
                        return;
                    case 20:
                        MobclickAgent.onEvent(this, "NO_20_CLEAN_FRIEND_CIRCLE");
                        MobclickAgent.onEvent((Context) this, "NO_20_CLEAN_FRIEND_CIRCLE", "清理朋友圈");
                        return;
                    case 21:
                        MobclickAgent.onEvent(this, "NO_21_CLEAN_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_21_CLEAN_FRIEND", "清理好友");
                        return;
                    case 22:
                        MobclickAgent.onEvent(this, "NO_22_MARK_READ");
                        MobclickAgent.onEvent((Context) this, "NO_22_MARK_READ", "标记已读");
                        return;
                    case 23:
                        MobclickAgent.onEvent(this, "NO_23_AUTOMATICALLY_ACCEPT_REQUESTS");
                        MobclickAgent.onEvent((Context) this, "NO_23_AUTOMATICALLY_ACCEPT_REQUESTS", "自动接受请求");
                        return;
                    case 24:
                        MobclickAgent.onEvent(this, "NO_24_WECHAT_SPORT");
                        MobclickAgent.onEvent((Context) this, "NO_24_WECHAT_SPORT", "微信运动");
                        return;
                    case 25:
                        MobclickAgent.onEvent(this, "NO_25_FRIENDS_CIRCLE_MATERIAL");
                        MobclickAgent.onEvent((Context) this, "NO_25_FRIENDS_CIRCLE_MATERIAL", "朋友圈素材");
                        return;
                    case 26:
                        MobclickAgent.onEvent(this, "NO_26_OPTIONAL_CONTACT");
                        MobclickAgent.onEvent((Context) this, "NO_26_OPTIONAL_CONTACT", "自选添加联系人");
                        return;
                    case 27:
                        MobclickAgent.onEvent(this, "NO_27_DND_CLEAN_ZOMBIE");
                        MobclickAgent.onEvent((Context) this, "NO_27_DND_CLEAN_ZOMBIE", "无打扰清理僵尸粉");
                        return;
                    case 28:
                        MobclickAgent.onEvent(this, "NO_28_PULL_GROUP_CLEAN_ZOMBIE");
                        MobclickAgent.onEvent((Context) this, "NO_28_PULL_GROUP_CLEAN_ZOMBIE", "拉群清理僵尸粉");
                        return;
                    case 29:
                        MobclickAgent.onEvent(this, "NO_29_PUBLIC_NUM_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_29_PUBLIC_NUM_FRIEND", "群发公众号到好友");
                        return;
                    case 30:
                        MobclickAgent.onEvent(this, "NO_30_PUBLIC_NUM_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_30_PUBLIC_NUM_GROUP", "群发公众号到群");
                        return;
                    case 31:
                        MobclickAgent.onEvent(this, "NO_31_CHANGER_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_31_CHANGER_GROUP", "换群专享");
                        return;
                    case 32:
                        MobclickAgent.onEvent(this, "NO_32_MINI_PROGRAMS_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_32_MINI_PROGRAMS_FRIEND", "群发小程序到好友");
                        return;
                    case 33:
                        MobclickAgent.onEvent(this, "NO_33_MINI_PROGRAMS_GROUP");
                        MobclickAgent.onEvent((Context) this, "NO_33_MINI_PROGRAMS_GROUP", "群发小程序到群");
                        return;
                    case 34:
                        MobclickAgent.onEvent(this, "NO_34_RECOVER_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_34_RECOVER_FRIEND", "恢复清理好友");
                        return;
                    case 35:
                        MobclickAgent.onEvent(this, "NO_35_CONTACT_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_35_CONTACT_FRIEND", "通讯录朋友");
                        return;
                    case 36:
                        MobclickAgent.onEvent(this, "NO_36_CARD_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_36_CARD_FRIEND", "群发名片到好友");
                        return;
                    case 37:
                        MobclickAgent.onEvent(this, "NO_37_BATCH_ALL_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_37_BATCH_ALL_FRIEND", "分批所有好友");
                        return;
                    case 38:
                        MobclickAgent.onEvent(this, "NO_38_CLEAN_ZOMBIE_CIRCLE");
                        MobclickAgent.onEvent((Context) this, "NO_38_CLEAN_ZOMBIE_CIRCLE", "清理僵尸粉_朋友圈");
                        return;
                    case 39:
                        MobclickAgent.onEvent(this, "NO_39_CLEAN_MSG");
                        MobclickAgent.onEvent((Context) this, "NO_39_CLEAN_MSG", "清理消息");
                        return;
                    case 40:
                        MobclickAgent.onEvent(this, "NO_40_VIDEO_WATERMARK");
                        MobclickAgent.onEvent((Context) this, "NO_40_VIDEO_WATERMARK", "视频水印");
                        return;
                    case 41:
                        MobclickAgent.onEvent(this, "NO_41_CLEAN_VIDEO_WATERMARK");
                        MobclickAgent.onEvent((Context) this, "NO_41_CLEAN_VIDEO_WATERMARK", "清除视频水印");
                        return;
                    case 42:
                        MobclickAgent.onEvent(this, "NO_42_VIDEO_STITCHING");
                        MobclickAgent.onEvent((Context) this, "NO_42_VIDEO_STITCHING", "视频拼接");
                        return;
                    case 43:
                        MobclickAgent.onEvent(this, "NO_43_BATCH_VIDEO_WATERMARK");
                        MobclickAgent.onEvent((Context) this, "NO_43_BATCH_VIDEO_WATERMARK", "批量水印");
                        return;
                    case 44:
                        MobclickAgent.onEvent(this, "NO_44_COPY_WECHCODE");
                        MobclickAgent.onEvent((Context) this, "NO_44_COPY_WECHCODE", "拷贝微信号");
                        return;
                    case 45:
                        MobclickAgent.onEvent(this, "NO_34_RECOVER_COPY_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_34_RECOVER_COPY_FRIEND", "恢复拷贝好友");
                        return;
                    default:
                        switch (parseInt) {
                            case 48:
                                MobclickAgent.onEvent(this, "NO_48_CAMERA_ADD_FANS");
                                MobclickAgent.onEvent((Context) this, "NO_48_CAMERA_ADD_FANS", "拍照加人");
                                break;
                            case 49:
                                break;
                            case 50:
                                MobclickAgent.onEvent(this, "NO_50_VOICE_GROUP");
                                MobclickAgent.onEvent((Context) this, "NO_50_VOICE_GROUP", "群发语音到群");
                                return;
                            case 51:
                                MobclickAgent.onEvent(this, "NO_51_MORE_LIKE_COMMENT_FRIEND");
                                MobclickAgent.onEvent((Context) this, "NO_51_MORE_LIKE_COMMENT_FRIEND", "全部好友点赞与评论");
                                return;
                            case 52:
                                MobclickAgent.onEvent(this, "NO_52_MORE_LIKE_COMMENT_FRIEND");
                                MobclickAgent.onEvent((Context) this, "NO_52_MORE_LIKE_COMMENT_FRIEND", "单个好友点赞与评论");
                                return;
                            case 53:
                                MobclickAgent.onEvent(this, "NO_53_BATCH_CAPTURE");
                                MobclickAgent.onEvent((Context) this, "NO_53_BATCH_CAPTURE", "批量抓图");
                                return;
                            default:
                                switch (parseInt) {
                                    case 55:
                                        MobclickAgent.onEvent(this, "NO_55_WX_CLEAN_GROUPS");
                                        MobclickAgent.onEvent((Context) this, "NO_55_WX_CLEAN_GROUPS", "微信群清理");
                                        return;
                                    case 56:
                                        MobclickAgent.onEvent(this, "NO_56_LABEL_REMARK");
                                        MobclickAgent.onEvent((Context) this, "NO_56_LABEL_REMARK", "标签备注");
                                        return;
                                    case 57:
                                        MobclickAgent.onEvent(this, "NO_57_AUTO_ADD_CARD");
                                        MobclickAgent.onEvent((Context) this, "NO_57_AUTO_ADD_CARD", "自动添加名片");
                                        return;
                                    default:
                                        switch (parseInt) {
                                            case 65:
                                                MobclickAgent.onEvent(this, "NO_70_SEARCH_ADD");
                                                MobclickAgent.onEvent((Context) this, "NO_70_SEARCH_ADD", "搜索加人");
                                                return;
                                            case 66:
                                                MobclickAgent.onEvent(this, "NO_66_FORWARD_BIG_VIDEO");
                                                MobclickAgent.onEvent((Context) this, "NO_66_FORWARD_BIG_VIDEO", "发布大视频");
                                                return;
                                            case 67:
                                                MobclickAgent.onEvent(this, "NO_67_AUTO_ADD_MY_CUSTOMER");
                                                MobclickAgent.onEvent((Context) this, "NO_67_AUTO_ADD_MY_CUSTOMER", "我的客源加人");
                                                return;
                                            case 68:
                                                MobclickAgent.onEvent(this, "NO_66_FORWARD_IMAGE_ADD_VOICE");
                                                MobclickAgent.onEvent((Context) this, "NO_66_FORWARD_IMAGE_ADD_VOICE", "转发图片加语音");
                                                return;
                                            case 69:
                                                MobclickAgent.onEvent(this, "NO_69_FORWARD_VIDEO_ADD_COVER");
                                                MobclickAgent.onEvent((Context) this, "NO_69_FORWARD_VIDEO_ADD_COVER", "转发视频换封面");
                                                return;
                                            default:
                                                return;
                                        }
                                }
                        }
                        MobclickAgent.onEvent(this, "NO_49_VOICE_FRIEND");
                        MobclickAgent.onEvent((Context) this, "NO_49_VOICE_FRIEND", "群发语音到好友");
                        return;
                }
            } else {
                MobclickAgent.onEvent(this, "NO_12_GROUP_INNER_FANS_COMPANY");
                MobclickAgent.onEvent((Context) this, "NO_12_GROUP_INNER_FANS_COMPANY", "企业群内加粉");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void initJPushInterface() {
        String str = (String) SPUtils.get(MyApplication.getConText(), "ws_baby_user_id", "");
        if (str != null && !"".equals(str)) {
            JPushInterface.setDebugMode(true);
            JPushInterface.init(StubApp.getOrigApplicationContext(getApplicationContext()));
            TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
            tagAliasBean.action = 2;
            TagAliasOperatorHelper.sequence++;
            tagAliasBean.alias = str;
            tagAliasBean.isAliasAction = true;
            TagAliasOperatorHelper.getInstance().handleAction(StubApp.getOrigApplicationContext(getApplicationContext()), TagAliasOperatorHelper.sequence, tagAliasBean);
            BasicPushNotificationBuilder basicPushNotificationBuilder = new BasicPushNotificationBuilder(this);
            basicPushNotificationBuilder.statusBarDrawable = 2131558404;
            basicPushNotificationBuilder.notificationFlags = 16;
            basicPushNotificationBuilder.notificationDefaults = 1;
            JPushInterface.setPushNotificationBuilder(1, basicPushNotificationBuilder);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        BaseActivity.super.onPause();
        isForeground = false;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void registerMessageReceiver() {
        this.mMessageReceiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(SocializeConstants.CANCLE_RESULTCODE);
        intentFilter.addAction(MyApplication.MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, intentFilter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        public MessageReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (MyApplication.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("message");
                    String stringExtra2 = intent.getStringExtra(BaseActivity.KEY_EXTRAS);
                    StringBuilder sb = new StringBuilder();
                    sb.append("message : " + stringExtra + "\n");
                    if (!ExampleUtil.isEmpty(stringExtra2)) {
                        sb.append("extras : " + stringExtra2 + "\n");
                    }
                    BaseActivity.this.setCostomMsg(sb.toString());
                }
            } catch (Exception unused) {
            }
        }
    }

    public static boolean checkCompanyWxApp(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
                if (packageInfo.applicationInfo.packageName.equals("com.tencent.wework")) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public void blackFill() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().clearFlags(201326592);
                getWindow().getDecorView().setSystemUiVisibility(10000);
                getWindow().addFlags(Integer.MIN_VALUE);
            }
        } catch (Exception unused) {
            LogUtils.log("WS_BABY_MainActivity_window_error");
        }
    }

    public void whiteFill() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().clearFlags(201326592);
                getWindow().getDecorView().setSystemUiVisibility(1792);
                getWindow().addFlags(Integer.MIN_VALUE);
            }
        } catch (Exception unused) {
            LogUtils.log("WS_BABY_MainActivity_window_error");
        }
    }

    public static boolean checkWxApp(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
                if (packageInfo.applicationInfo.packageName.equals("com.tencent.mm")) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
