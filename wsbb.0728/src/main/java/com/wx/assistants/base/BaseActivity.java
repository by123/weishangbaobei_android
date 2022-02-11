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
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.wx.assistants.floatwindow.FloatWindow;
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
import com.wx.assistants.utils.fileutil.ListUtils;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
            } catch (Exception e) {
            }
        }
    }

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

    static /* synthetic */ int access$608(BaseActivity baseActivity) {
        int i = baseActivity.savePicImageIndex;
        baseActivity.savePicImageIndex = i + 1;
        return i;
    }

    public static boolean checkCompanyWxApp(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
                if (packageInfo.applicationInfo.packageName.equals("com.tencent.wework")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkWxApp(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
                if (packageInfo.applicationInfo.packageName.equals("com.tencent.mm")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
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
                    public void convertFail() {
                        BaseActivity.access$608(BaseActivity.this);
                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                    }

                    public void convertProgress(int i) {
                    }

                    public void convertSuccess(String str) {
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
                                    public void saveFail() {
                                        BaseActivity.access$608(BaseActivity.this);
                                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                                    }

                                    public void saveSuccess() {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        BaseActivity.access$608(BaseActivity.this);
                                        BaseActivity.this.saveSendImages(list, onLoadingDownListener);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    public void setCostomMsg(String str) {
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

    public void blackFill() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().clearFlags(201326592);
                getWindow().getDecorView().setSystemUiVisibility(10000);
                getWindow().addFlags(Integer.MIN_VALUE);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_MainActivity_window_error");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
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
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r2v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    if (onStartCheckListener != null) {
                        onStartCheckListener.checkEnd();
                    }
                }
            });
        } else if (!this.isHasAuthority) {
            DialogUIUtils.dialogOpenMember(this, "此功能仅对半年以上会员用户授权使用,请充值半年以上会员", (View.OnClickListener) null, new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void onClick(View view) {
                    BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                }
            });
        } else if (onStartCheckListener != null) {
            onStartCheckListener.checkEnd();
        }
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
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r2v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(BaseActivity.this, conmdBean.getMessage());
                }

                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    if (checkResultListener2 != null) {
                        checkResultListener2.permitExecution(operationParameterModel);
                    }
                }
            });
        } else if (!this.isHasAuthority) {
            DialogUIUtils.dialogOpenMember(this, "此功能仅对半年以上会员用户授权使用,请充值半年以上会员", (View.OnClickListener) null, new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r2v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    BaseActivity.this.startActivity(new Intent(BaseActivity.this, cls));
                }
            });
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isNeedBack", true);
        startActivity(intent);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, android.app.Activity] */
    public void checkUserVideoEditRule() {
        if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            showLoadingDialog("请耐心等待");
            CheckRuleValidateUtils.getInstance(this).checkRuleValidate("42", new CheckRuleValidateUtils.CheckResultListener() {
                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void changeDevice(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(BaseActivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r2v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                        public void onClick(View view) {
                            BaseActivity.this.startActivity(new Intent(BaseActivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }

                public void nilPermitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    BaseActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
                public void permitExecution(Object obj) {
                    BaseActivity.this.dismissLoadingDialog();
                    PictureSelector.create((Activity) BaseActivity.this).openGallery(PictureMimeType.ofAll()).theme(R.style.picture_default_style).maxSelectNum(9).minSelectNum(1).imageSpanCount(4).selectionMode(2).previewImage(false).previewVideo(false).enablePreviewAudio(false).isCamera(true).isZoomAnim(false).compress(false).synOrAsy(true).glideOverride(160, 160).withAspectRatio(3, 4).hideBottomControls(true).isGif(true).freeStyleCropEnabled(true).circleDimmedLayer(false).showCropFrame(true).showCropGrid(true).openClickSound(false).minimumCompressSize(100).forResult(PictureConfig.CHOOSE_REQUEST);
                }
            });
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isNeedBack", true);
        startActivity(intent);
    }

    public void compressSaveImg(String str, final OnSaveImgListener onSaveImgListener) {
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditFile().getAbsolutePath();
            BitmapUtils.saveOriginBitmap(str, absolutePath, new BitmapUtils.OnSaveBitmapListener() {
                public void saveFail() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveFail();
                            }
                        }
                    });
                }

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
            });
        } catch (Exception e) {
            File file = new File(str);
            file.renameTo(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"));
            File file2 = new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png");
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file2));
            sendBroadcast(intent);
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), file2.getAbsolutePath(), file2.getName(), (String) null);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(file2.getAbsolutePath())));
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"))));
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void compressSaveImg(List<String> list, final OnSaveImgListener onSaveImgListener) {
        LogUtils.log("@@@@@@@@@@==" + list.get(0));
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditFile().getAbsolutePath();
            BitmapUtils.saveOriginBitmap(list.get(0), absolutePath, new BitmapUtils.OnSaveBitmapListener() {
                public void saveFail() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (onSaveImgListener != null) {
                                onSaveImgListener.saveFail();
                            }
                        }
                    });
                }

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
            });
        } catch (Exception e) {
            LogUtils.log("@@@@@@@@@@==" + list.get(0));
            File file = new File(list.get(0));
            file.renameTo(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"));
            File file2 = new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png");
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file2));
            sendBroadcast(intent);
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), file2.getAbsolutePath(), file2.getName(), (String) null);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(file2.getAbsolutePath())));
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(FileUtils.getSDPath() + "/tencent/microMsg/WeiXin/ws_baby.png"))));
        }
        Toast.makeText(this, "保存成功", 0).show();
    }

    public void compressSaveVideo(String str, final FileUtil.OnConvertMp4Listener onConvertMp4Listener) {
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditVideoFile().getAbsolutePath();
            LogUtils.log("WS_BABY_" + absolutePath);
            FileUtil.mp4ToMp4(str, absolutePath, new FileUtil.OnConvertMp4Listener() {
                public void convertFail() {
                    LogUtils.log("WS_BABY.ERROR");
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertFail();
                    }
                }

                public void convertProgress(int i) {
                    LogUtils.log("WS_BABY." + i);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertProgress(i);
                    }
                }

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
            });
        } catch (Exception e) {
        }
    }

    public void compressSaveVideo(List<String> list, final FileUtil.OnConvertMp4Listener onConvertMp4Listener) {
        LogUtils.log("WS_BABY_" + list.get(0));
        try {
            final String absolutePath = com.fb.jjyyzjy.watermark.utils.FileUtil.genEditVideoFile().getAbsolutePath();
            LogUtils.log("WS_BABY_" + absolutePath);
            FileUtil.mp4ToMp4(list.get(0), absolutePath, new FileUtil.OnConvertMp4Listener() {
                public void convertFail() {
                    LogUtils.log("WS_BABY.ERROR");
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertFail();
                    }
                }

                public void convertProgress(int i) {
                    LogUtils.log("WS_BABY." + i);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertProgress(i);
                    }
                }

                /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
                public void convertSuccess(String str) {
                    LogUtils.log("WS_BABY.convertSuccess");
                    AlbumUtil.insertVideoToMediaStore2(BaseActivity.this, absolutePath);
                    if (onConvertMp4Listener != null) {
                        onConvertMp4Listener.convertSuccess(str);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    public void dismissLoadingDialog() {
        if (this.loadingDialog != null) {
            this.loadingDialog.close();
        }
    }

    public String getAppendSign() {
        return this.appendSign;
    }

    public void getUserInfoData() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    MyApplication.isNeedSign = false;
                    String unused = BaseActivity.this.appendSign = "";
                    SPUtils.put(BaseActivity.this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                }
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() != 1 || conmdBean.getMessage() == null) {
                            if (conmdBean.getCode() == 0) {
                                String json = new Gson().toJson(conmdBean.getData());
                                try {
                                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                                } catch (Exception e) {
                                }
                                BaseActivity.this.setUserInfo(json);
                            }
                        } else if (conmdBean.getMessage().contains("请重新登录")) {
                            MyApplication.isNeedSign = false;
                            String unused = BaseActivity.this.appendSign = "";
                            SPUtils.put(BaseActivity.this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                        }
                    } catch (Exception e2) {
                        LogUtils.log("WS_BABY_Catch_35");
                        e2.printStackTrace();
                        MyApplication.isNeedSign = false;
                        String unused2 = BaseActivity.this.appendSign = "";
                        SPUtils.put(BaseActivity.this, "isNeedSign", Boolean.valueOf(MyApplication.isNeedSign));
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    public boolean isAccessibilitySettingsOn() {
        int i;
        String string;
        try {
            String str = getPackageName() + "/" + AutoService.class.getCanonicalName();
            try {
                i = Settings.Secure.getInt(StubApp.getOrigApplicationContext(getApplicationContext()).getContentResolver(), "accessibility_enabled");
                try {
                    LogUtils.log("WS_BABY_A_" + str + ListUtils.DEFAULT_JOIN_SEPARATOR + i);
                } catch (Settings.SettingNotFoundException e) {
                }
            } catch (Settings.SettingNotFoundException e2) {
                i = 0;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
            if (i == 1 && (string = Settings.Secure.getString(StubApp.getOrigApplicationContext(getApplicationContext()).getContentResolver(), "enabled_accessibility_services")) != null) {
                LogUtils.log("WS_BABY_B_" + str + ListUtils.DEFAULT_JOIN_SEPARATOR + i + ListUtils.DEFAULT_JOIN_SEPARATOR + string);
                simpleStringSplitter.setString(string);
                while (simpleStringSplitter.hasNext()) {
                    if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e3) {
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public boolean isLoginIng() {
        String str = (String) SPUtils.get(this, "ws_baby_phone", "");
        return str != null && !"".equals(str);
    }

    public boolean isSupportSwipeBack() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 66) {
            try {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("outputList");
                if (this.mListener != null) {
                    this.mListener.result(stringArrayListExtra);
                }
            } catch (Exception e) {
            }
        }
    }

    public void onBackPressed() {
        if (this.mSwipeBackHelper != null && !this.mSwipeBackHelper.isSliding()) {
            this.mSwipeBackHelper.backward();
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

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
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        BaseActivity.super.onPause();
        isForeground = false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007b, code lost:
        if (r3.isExecuteVoiceSend == false) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0069, code lost:
        if (r3.isExecuteVoiceSend != false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x006b, code lost:
        r3.isPause = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        getUserInfoData();
     */
    public void onResume() {
        BaseActivity.super.onResume();
        try {
            isForeground = true;
            FloatWindow.destroy("back");
            FloatWindow.destroy("start");
            FloatWindow.destroy("end");
            FloatWindow.destroy("bottom");
            FloatWindow.destroy("bottom_operation");
            FloatWindow.destroy("bottom_error");
            FloatWindow.destroy("middle");
            FloatWindow.destroy("praise_start");
            FloatWindow.destroy("comment_start");
            FloatWindow.destroy("back");
            FloatWindow.destroy("start");
            FloatWindow.destroy("end");
            FloatWindow.destroy("bottom");
            FloatWindow.destroy("bottom_operation");
            FloatWindow.destroy("bottom_error");
            FloatWindow.destroy("middle");
            FloatWindow.destroy("praise_start");
            FloatWindow.destroy("comment_start");
            MyApplication.countSend = 0;
            MyApplication.enforceable = false;
        } catch (Exception e) {
            LogUtils.log("WS_BABY_onResume_error");
            MyApplication.enforceable = false;
        } catch (Throwable th) {
            MyApplication.enforceable = false;
            if (this.isExecuteVoiceSend) {
                this.isPause = true;
            }
            try {
                getUserInfoData();
            } catch (Exception e2) {
            }
            throw th;
        }
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
        } catch (Exception e) {
        }
        if (!(this instanceof MainActivity) && !(this instanceof LaunchActivity)) {
            try {
                AndroidBarUtils.setBarDarkMode(this, true);
            } catch (Exception e2) {
                LogUtils.log("WS_BABY_BaseActivity_onStart_error");
            }
        }
    }

    public void onSwipeBackLayoutCancel() {
    }

    public void onSwipeBackLayoutExecuted() {
        if (this.mSwipeBackHelper != null) {
            this.mSwipeBackHelper.swipeBackward();
        }
    }

    public void onSwipeBackLayoutSlide(float f) {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void openAlertDialog() {
        DialogUIUtils.openAncillaryServices(this, "\n1.开启辅助服务，微商宝贝才能完成一系列自动化操作。\n2.进入辅助页面后，找到微商宝贝，辅助开关打开即可。\n3.若发现已经开启，请先关闭在开启一次。", new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onClick(View view) {
                OpenAccessibilitySettingHelper.jumpToSettingPage(BaseActivity.this);
                AccessibilityFloatWindowOpenService.startWithOpen(BaseActivity.this, 0);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void registerMessageReceiver() {
        this.mMessageReceiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(SocializeConstants.CANCLE_RESULTCODE);
        intentFilter.addAction(MyApplication.MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, intentFilter);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void runWx(OperationParameterModel operationParameterModel) {
        try {
            if (!isAccessibilitySettingsOn()) {
                Log.d(TAG, "辅助功能未开启！");
                openAlertDialog();
            } else if (FloatManager.getInstance().checkPermission(this)) {
                try {
                    this.mmodel = MyApplication.instance.getOperationParameterModel();
                    this.taskNum = this.mmodel.getTaskNum();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Intent intent = new Intent();
                    intent.setClass(this, AutoService.class);
                    startService(intent);
                } catch (Exception e2) {
                }
                try {
                    statisticalCounting(operationParameterModel);
                } catch (Exception e3) {
                }
                Log.d(TAG, "辅助功能已开启！");
                if ("50".equals(this.taskNum) || "49".equals(this.taskNum)) {
                    showLoadingDialog("正在处理转发数据");
                    this.isPause = false;
                    this.isExecuteVoiceSend = false;
                    this.isCheckJumpWX = false;
                    String voicePath = operationParameterModel.getVoicePath();
                    File file = new File(voicePath);
                    MultipartBody.Part createFormData = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
                    if (voicePath.endsWith(".amr")) {
                        ApiWrapper.getInstance().addAmrMaterial(createFormData, new ApiWrapper.CallbackListener<ConmdBean<VoiceBean>>() {
                            public void onFailure(FailureModel failureModel) {
                                BaseActivity.this.dismissLoadingDialog();
                                LogUtils.log("WS_BABY_2=" + failureModel.toString());
                            }

                            public void onFinish(ConmdBean<VoiceBean> conmdBean) {
                                BaseActivity.this.dismissLoadingDialog();
                                BaseActivity.this.sharedVoice(BaseActivity.this.mmodel, conmdBean.getData());
                                LogUtils.log("WS_BABY_1=" + conmdBean);
                            }
                        });
                    } else {
                        ApiWrapper.getInstance().addMaterialToAli(createFormData, new ApiWrapper.CallbackListener<ConmdBean<VoiceBean>>() {
                            public void onFailure(FailureModel failureModel) {
                                BaseActivity.this.dismissLoadingDialog();
                                LogUtils.log("WS_BABY_4=" + failureModel);
                            }

                            public void onFinish(ConmdBean<VoiceBean> conmdBean) {
                                BaseActivity.this.dismissLoadingDialog();
                                BaseActivity.this.sharedVoice(BaseActivity.this.mmodel, conmdBean.getData());
                                LogUtils.log("WS_BABY_3=" + conmdBean);
                            }
                        });
                    }
                } else {
                    new Thread(new Runnable() {
                        public void run() {
                            BaseActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (!"47".equals(BaseActivity.this.taskNum)) {
                                        StartOtherAppUtils.startWeChat();
                                        MyApplication.instance.getMyWindowManager().showFloatWindow(BaseActivity.this.taskNum);
                                        return;
                                    }
                                    MyApplication.instance.getMyWindowManager().showFloatWindow(BaseActivity.this.taskNum);
                                }
                            });
                        }
                    }).start();
                }
            } else {
                toastFloatWindowOpenDialog(this);
            }
        } catch (Exception e4) {
            LogUtils.log("WS_BABY_runWx_ERROR2");
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void runWxCompany(OperationParameterModel operationParameterModel) {
        try {
            if (!isAccessibilitySettingsOn()) {
                Log.d(TAG, "辅助功能未开启！");
                openAlertDialog();
            } else if (FloatManager.getInstance().checkPermission(this)) {
                try {
                    this.mmodel = MyApplication.instance.getOperationParameterModel();
                    this.taskNum = this.mmodel.getTaskNum();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    statisticalCounting(operationParameterModel);
                } catch (Exception e2) {
                }
                try {
                    Intent intent = new Intent();
                    intent.setClass(MyApplication.getConText(), AutoService.class);
                    startService(intent);
                } catch (Exception e3) {
                }
                Log.d(TAG, "辅助功能已开启！");
                if ("10050".equals(this.taskNum) || "10049".equals(this.taskNum)) {
                    showLoadingDialog("正在处理转发数据");
                    this.isPause = false;
                    this.isExecuteVoiceSend = false;
                    this.isCheckJumpWX = false;
                    String voicePath = operationParameterModel.getVoicePath();
                    File file = new File(voicePath);
                    MultipartBody.Part createFormData = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
                    if (voicePath.endsWith(".amr")) {
                        ApiWrapper.getInstance().addAmrMaterial(createFormData, new ApiWrapper.CallbackListener<ConmdBean<VoiceBean>>() {
                            public void onFailure(FailureModel failureModel) {
                                BaseActivity.this.dismissLoadingDialog();
                                LogUtils.log("WS_BABY_2=" + failureModel.toString());
                            }

                            public void onFinish(ConmdBean<VoiceBean> conmdBean) {
                                BaseActivity.this.dismissLoadingDialog();
                                BaseActivity.this.sharedVoice(BaseActivity.this.mmodel, conmdBean.getData());
                                LogUtils.log("WS_BABY_1=" + conmdBean);
                            }
                        });
                    } else {
                        ApiWrapper.getInstance().addMaterialToAli(createFormData, new ApiWrapper.CallbackListener<ConmdBean<VoiceBean>>() {
                            public void onFailure(FailureModel failureModel) {
                                BaseActivity.this.dismissLoadingDialog();
                                LogUtils.log("WS_BABY_4=" + failureModel);
                            }

                            public void onFinish(ConmdBean<VoiceBean> conmdBean) {
                                BaseActivity.this.dismissLoadingDialog();
                                BaseActivity.this.sharedVoice(BaseActivity.this.mmodel, conmdBean.getData());
                                LogUtils.log("WS_BABY_3=" + conmdBean);
                            }
                        });
                    }
                } else {
                    new Thread(new Runnable() {
                        public void run() {
                            BaseActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (!"10047".equals(BaseActivity.this.taskNum)) {
                                        StartOtherAppUtils.startWeChatCompany();
                                        MyApplication.instance.getMyWindowManager().showFloatWindowCompany(BaseActivity.this.taskNum);
                                        return;
                                    }
                                    MyApplication.instance.getMyWindowManager().showFloatWindowCompany(BaseActivity.this.taskNum);
                                }
                            });
                        }
                    }).start();
                }
            } else {
                toastFloatWindowOpenDialog(this);
            }
        } catch (Exception e4) {
            LogUtils.log("WS_BABY_runWx_ERROR2");
        }
    }

    public void savePicImages(List<String> list, OnLoadingDownListener onLoadingDownListener) {
        this.savePicImageIndex = 0;
        saveSendImages(list, onLoadingDownListener);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    public void setOnAlbumResultListener(OnAlbumResultListener onAlbumResultListener) {
        this.mListener = onAlbumResultListener;
    }

    public void setOnUserInfoListener(OnUserInfoListener onUserInfoListener) {
        this.infoListener = onUserInfoListener;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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
            } catch (Exception e) {
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

    public void sharedVoice(OperationParameterModel operationParameterModel, final VoiceBean voiceBean) {
        runOnUiThread(new Runnable() {
            /* JADX WARNING: type inference failed for: r3v4, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r2v8, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r2v10, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
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
                        public void onCancel(SHARE_MEDIA share_media) {
                            LogUtils.log("WS_BABY_auto_service_onCancel");
                        }

                        public void onError(SHARE_MEDIA share_media, Throwable th) {
                            LogUtils.log("WS_BABY_auto_service_onError");
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
    public void showLoadingDialog(String str) {
        this.loadingDialog = new LoadingDialog(this);
        this.loadingDialog.setInterceptBack(false);
        this.loadingDialog.setLoadingText(str).show();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    public void showLoadingDialog(String str, boolean z) {
        this.loadingDialog = new LoadingDialog(this);
        this.loadingDialog.setInterceptBack(!z);
        this.loadingDialog.setLoadingText(str).show();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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
            } catch (Exception e) {
                LogUtils.log("WS_BABY_runWx_ERROR2");
            }
        } else {
            checkMember(str, onStartCheckListener);
        }
    }

    public void startWX(OperationParameterModel operationParameterModel) {
        boolean z = MyApplication.isValidationService;
        runWx(operationParameterModel);
    }

    public void startWXCompany(OperationParameterModel operationParameterModel) {
        runWxCompany(operationParameterModel);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
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

    public void toastFloatWindowOpenDialog(final Context context) {
        DialogUIUtils.openFloatWindowServices(context, new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r0v1, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
            public void onClick(View view) {
                FloatManager.getInstance().applyPermission(context);
                AccessibilityFloatWindowOpenService.startWithOpen(BaseActivity.this, 1);
            }
        });
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

    public void toastToUser(String str) {
        new MToastConfig.Builder().setGravity(MToastConfig.MToastGravity.BOTTOM, 0, 30).setTextColor(getResources().getColor(com.ilike.voicerecorder.R.color.color_ffffff)).setBackgroundColor(getResources().getColor(R.color.main_color)).setBackgroundCornerRadius(10.0f).setBackgroundStrokeColor(-1).setBackgroundStrokeWidth(1.0f).setTextSize(15.0f).setPadding(80, 30, 80, 30).build();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
            }
        });
    }

    public void updateLoadingDialog(String str) {
        if (this.loadingDialog != null) {
            this.loadingDialog.setLoadingText(str);
        }
    }

    public void whiteFill() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().clearFlags(201326592);
                getWindow().getDecorView().setSystemUiVisibility(1792);
                getWindow().addFlags(Integer.MIN_VALUE);
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_MainActivity_window_error");
        }
    }
}
