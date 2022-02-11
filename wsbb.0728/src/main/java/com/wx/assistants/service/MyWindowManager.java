package com.wx.assistants.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.activity.AccessibilityOpenHelperActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.floatwindow.FloatWindow;
import com.wx.assistants.floatwindow.PermissionListener;
import com.wx.assistants.floatwindow.ViewStateListener;
import com.wx.assistants.service_utils.AutoAddCardUtils;
import com.wx.assistants.service_utils.AutoAddContactsCameraUtils;
import com.wx.assistants.service_utils.AutoAddContactsNewFriendsUtils;
import com.wx.assistants.service_utils.AutoAddContactsOptionalUtils;
import com.wx.assistants.service_utils.AutoAddContactsUtils;
import com.wx.assistants.service_utils.AutoAddGroupFansCompanyUtils;
import com.wx.assistants.service_utils.AutoAddGroupFansUtils;
import com.wx.assistants.service_utils.AutoAddMyCustomerUtils;
import com.wx.assistants.service_utils.AutoAddSearchUtils;
import com.wx.assistants.service_utils.AutoNearbyPeoplesUtils;
import com.wx.assistants.service_utils.AutoPassNilValidationUtils;
import com.wx.assistants.service_utils.AutoPassValidationUtils;
import com.wx.assistants.service_utils.AutoPullFriendsToAllGroupSearchUtils;
import com.wx.assistants.service_utils.AutoPullFriendsToAllGroupUtils;
import com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils;
import com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils;
import com.wx.assistants.service_utils.BaseServiceCompanyUtils;
import com.wx.assistants.service_utils.BaseServiceUtils;
import com.wx.assistants.service_utils.CleanCircleAllUtils;
import com.wx.assistants.service_utils.CleanFriendsAllUtils;
import com.wx.assistants.service_utils.CleanFriendsNickNameUtils;
import com.wx.assistants.service_utils.CleanFriendsTagUtils;
import com.wx.assistants.service_utils.CleanGroupsAllUtils;
import com.wx.assistants.service_utils.CleanGroupsOtherUtils;
import com.wx.assistants.service_utils.CleanGroupsSpecifiedUtils;
import com.wx.assistants.service_utils.CleanHomeMsgUtils;
import com.wx.assistants.service_utils.CleanUnReadUtils;
import com.wx.assistants.service_utils.CleanZombieFanCircleUtils;
import com.wx.assistants.service_utils.CleanZombieFanCollectUtils;
import com.wx.assistants.service_utils.CleanZombieFanCustomUtils;
import com.wx.assistants.service_utils.CleanZombieFanUtils;
import com.wx.assistants.service_utils.CleanZombiePullGroupUtils;
import com.wx.assistants.service_utils.ClearFriendRecoverUtils;
import com.wx.assistants.service_utils.CloneCircleUtils;
import com.wx.assistants.service_utils.CopyFriendRecoverUtils;
import com.wx.assistants.service_utils.CopyTagsFriendsUtils;
import com.wx.assistants.service_utils.GroupSendBatchUtils;
import com.wx.assistants.service_utils.GroupSendCardSearchUtils;
import com.wx.assistants.service_utils.GroupSendCardToFriendsUtils;
import com.wx.assistants.service_utils.GroupSendCardUtils;
import com.wx.assistants.service_utils.GroupSendCollectionToFriendsUtils;
import com.wx.assistants.service_utils.GroupSendCollectionToGroupSearchUtils;
import com.wx.assistants.service_utils.GroupSendCollectionToGroupUtils;
import com.wx.assistants.service_utils.GroupSendFriendsUtils;
import com.wx.assistants.service_utils.GroupSendGroupSearchUtils;
import com.wx.assistants.service_utils.GroupSendGroupUtils;
import com.wx.assistants.service_utils.GroupSendOneByOneFriendsCompanyUtils;
import com.wx.assistants.service_utils.GroupSendOneByOneFriendsUtils;
import com.wx.assistants.service_utils.GroupSendPublicNumberToFriendsUtils;
import com.wx.assistants.service_utils.GroupSendPublicNumberToGroupsUtils;
import com.wx.assistants.service_utils.GroupSendTagFriendsUtils;
import com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils;
import com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils;
import com.wx.assistants.service_utils.LabelRemarkAllUtils;
import com.wx.assistants.service_utils.LabelRemarkLabelUtils;
import com.wx.assistants.service_utils.LabelRemarkNickNameUtils;
import com.wx.assistants.service_utils.LabelRemarkTagUtils;
import com.wx.assistants.service_utils.LikeCommentFriendsUtils;
import com.wx.assistants.service_utils.LikeCommentSingleFriendUtils;
import com.wx.assistants.service_utils.ObtainGroupAllUtils;
import com.wx.assistants.service_utils.ObtainGroupUtils;
import com.wx.assistants.service_utils.ObtainTagCompanyUtils;
import com.wx.assistants.service_utils.ObtainTagUtils;
import com.wx.assistants.service_utils.OneKeyClickCommentUtils;
import com.wx.assistants.service_utils.OneKeyCopyWXHUtils;
import com.wx.assistants.service_utils.OneKeyForwardAlbumUtils;
import com.wx.assistants.service_utils.OneKeyForwardBigVideoUtils;
import com.wx.assistants.service_utils.OneKeyForwardChatRoomUtils;
import com.wx.assistants.service_utils.OneKeyForwardMaterialUtils;
import com.wx.assistants.service_utils.OneKeyForwardUtils;
import com.wx.assistants.service_utils.WxSportsUtils;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.DensityUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.ToastUtils;
import java.io.PrintStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyWindowManager {
    public static int forwardTag = 0;
    public static MyWindowManager windowManager = null;
    public static int wxVersionCode = 0;
    public static String wxVersionName = "7.0.4";
    /* access modifiers changed from: private */
    public EndViewListener endViewListener;
    /* access modifiers changed from: private */
    public String errorString = "";
    /* access modifiers changed from: private */
    public String executeTag = "-1";
    private FloatBackView mBackView;
    private FloatBottomErrorView mBottomErrorView;
    private FloatBottomOperationView mBottomOperationView;
    private FloatBottomView mBottomView;
    private FloatCommentView mCommentView;
    private FloatEndView mEndView;
    private FloatMiddleView mMiddleView;
    private FloatPraiseView mPraiseView;
    private FloatStartView mStartView;
    private WindowManager mWindowManager;
    private MiddleViewListener middleViewListener;
    private OperationParameterModel model;
    private ViewStateListener startViewStateListener = new ViewStateListener() {
        public void onBackToDesktop() {
            LogUtils.log("onBackToDesktop");
        }

        public void onDismiss() {
            LogUtils.log("onDismiss");
        }

        public void onHide() {
            LogUtils.log("onHide");
        }

        public void onMoveAnimEnd() {
            LogUtils.log("onMoveAnimEnd");
        }

        public void onMoveAnimStart() {
            LogUtils.log("onMoveAnimStart");
        }

        public void onPositionUpdate(int i, int i2) {
            MyApplication.startX = i;
            MyApplication.startY = i2;
            LogUtils.log("onPositionUpdate: x=" + i + " y=" + i2);
            LogUtils.log("onPositionUpdate: xxxx=" + MyApplication.startX + " y=" + MyApplication.startY);
        }

        public void onShow() {
            LogUtils.log("onShow");
        }
    };
    /* access modifiers changed from: private */
    public Timer timer = new Timer();

    public interface EndViewListener {
        void endViewDismiss();

        void endViewShow();
    }

    public interface MiddleViewListener {
        void middleViewDismiss();

        void middleViewShow();
    }

    public interface OnToastEndListener {
        void toastEnd();
    }

    private MyWindowManager() {
    }

    private WindowManager getWindowManager() {
        try {
            if (this.mWindowManager == null) {
                this.mWindowManager = (WindowManager) MyApplication.getConText().getSystemService("window");
            }
            return this.mWindowManager;
        } catch (Exception e) {
            return null;
        }
    }

    public static void initVersion() {
        BaseServiceUtils.formWindowManagerSetting();
        BaseServiceCompanyUtils.formWindowManagerSetting();
    }

    public static void initWXVersion() {
        try {
            wxVersionName = PackageUtils.getWXVersionName(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wxVersionCode = PackageUtils.getWXVersionCode(MyApplication.getConText());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static MyWindowManager newInstance() {
        try {
            if (windowManager == null) {
                windowManager = new MyWindowManager();
                initWXVersion();
                initVersion();
            }
        } catch (Exception e) {
        }
        return windowManager;
    }

    public void checkError() {
        this.errorString = "\n检测到【辅助服务】启动异常\n请把辅助服务重新开启！";
        try {
            if (!(MyApplication.instance == null || MyApplication.instance.getAutoService() == null)) {
                AutoService autoService = MyApplication.instance.getAutoService();
                if (Build.VERSION.SDK_INT >= 24) {
                    autoService.disableSelf();
                }
            }
        } catch (Exception e) {
        }
        try {
            Intent intent = new Intent(MyApplication.getConText(), AccessibilityOpenHelperActivity.class);
            intent.addFlags(268435456);
            MyApplication.instance.getBaseActivity().startActivity(intent);
        } catch (Exception e2) {
        }
    }

    public void companyWx(int i) {
        boolean isWXGroupCompanyOut;
        String str;
        boolean z = false;
        try {
            LogUtils.log("WS_BABY_TAGS" + this.executeTag);
            if (i == 1003) {
                isWXGroupCompanyOut = isWXMomentsAndAlbum();
                LogUtils.log("WS_BABY_请进入朋友圈/相册，再点开始" + isWXGroupCompanyOut);
                str = "请前往【朋友圈或相册列表页】，再点开始";
            } else if (i == 1009) {
                isWXGroupCompanyOut = isWXMoments();
                LogUtils.log("WS_BABY_请进入朋友圈，再点开始" + isWXGroupCompanyOut);
                str = "请前往【朋友圈页】，再点开始";
            } else if (i == 1008 || i == 10012 || i == 10057) {
                isWXGroupCompanyOut = isWXGroupCompanyOut();
                LogUtils.log("WS_BABY_请从群聊中进入一个群，再点开始" + isWXGroupCompanyOut);
                str = "请进入【外部群群聊聊天页】，再点开始";
            } else if (i == 10013 || i == 10052) {
                isWXGroupCompanyOut = isFriendsDesc();
                LogUtils.log("WS_BABY_请进入好友详情页🎨，再点开始" + isWXGroupCompanyOut);
                str = "请进入【好友详情页】，再点开始";
            } else if (i == 10024) {
                isWXGroupCompanyOut = isWXSportsUI();
                LogUtils.log("WS_BABY_请进入微信排行榜，点开始" + isWXGroupCompanyOut);
                str = "请进入【微信运动步数排行榜】，点开始";
            } else if (i == 10015) {
                if (isWXP2P() || isWXGroupCompanyOut()) {
                    z = true;
                }
                str = "请进入【聊天页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroupCompanyOut = z;
            } else if (i == 10029 || i == 10030 || i == 10032 || i == 10033) {
                if (isWXP2P() || isWXGroupCompanyOut()) {
                    z = true;
                }
                str = "请进入【聊天页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroupCompanyOut = z;
            } else if (i == 10025) {
                if (isWXMainUICompany() || isWXMoments()) {
                    z = true;
                }
                str = "请进入【微信主页或朋友圈页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroupCompanyOut = z;
            } else if (i == 10035) {
                isWXGroupCompanyOut = isWXNewFriends();
                LogUtils.log("WS_BABY_请进入新的朋友，再点开始" + isWXGroupCompanyOut);
                str = "请进入【新的朋友】页面，再点开始";
            } else if (i == 10037) {
                isWXGroupCompanyOut = isWXP2P();
                LogUtils.log("WS_BABY_请进入聊天🎨，再点开始" + isWXGroupCompanyOut);
                str = "请进入【聊天会话】页面，再点开始";
            } else {
                isWXGroupCompanyOut = isWXMainUICompany();
                LogUtils.log("WS_BABY_请前往微信主界面" + isWXGroupCompanyOut);
                str = "请前往【微信主界面】";
            }
            if (isWXGroupCompanyOut) {
                initUtils(MyApplication.instance.getOperationParameterModel(), i);
                initStartWx(i);
                return;
            }
            toastToUser2(str);
        } catch (Exception e) {
        }
    }

    public AutoService getAutoService() {
        return MyApplication.instance.getAutoService();
    }

    public FloatBottomErrorView getBottomErrorView() {
        try {
            return this.mBottomErrorView != null ? this.mBottomErrorView : new FloatBottomErrorView(MyApplication.getConText());
        } catch (Exception e) {
            return null;
        }
    }

    public FloatBottomView getBottomView() {
        try {
            return this.mBottomView != null ? this.mBottomView : new FloatBottomView(MyApplication.getConText());
        } catch (Exception e) {
            return null;
        }
    }

    public Context getContext() {
        return MyApplication.getConText();
    }

    public FloatMiddleView getMiddleView() {
        try {
            return this.mMiddleView != null ? this.mMiddleView : new FloatMiddleView(MyApplication.getConText());
        } catch (Exception e) {
            return null;
        }
    }

    public int[] getScreenSize() {
        WindowManager windowManager2 = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager2.getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    public FloatBottomOperationView getmBottomOperationView() {
        try {
            return this.mBottomOperationView != null ? this.mBottomOperationView : new FloatBottomOperationView(MyApplication.getConText());
        } catch (Exception e) {
            return null;
        }
    }

    public void initStartWx(int i) {
        try {
            if (MyApplication.instance.getAutoService() == null) {
                LogUtils.log("WS_BABY_initStartWx_SERVER_0");
                Intent intent = new Intent();
                intent.setClass(MyApplication.getConText(), AutoService.class);
                MyApplication.getConText().startService(intent);
            } else {
                LogUtils.log("WS_BABY_initStartWx_SERVER_1");
            }
        } catch (Exception e) {
        }
        try {
            MyApplication.enforceable = true;
            if (i != 9) {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        MyWindowManager.this.removeStartView();
                        MyWindowManager.this.removeBackView();
                        MyWindowManager.this.showEndView();
                    }
                });
            } else {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        MyWindowManager.this.removePraiseStartView();
                        MyWindowManager.this.removeCommentStartView();
                        MyWindowManager.this.removeBackView();
                        MyWindowManager.this.showEndView();
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void initUtils(OperationParameterModel operationParameterModel, int i) {
        if (i == 60) {
            AutoPassNilValidationUtils.getInstance().initData(operationParameterModel);
        } else if (i == 140) {
            ObtainGroupAllUtils.getInstance().initData();
        } else if (i != 1005) {
            switch (i) {
                case 1:
                    CleanZombieFanUtils.getInstance().initData(operationParameterModel);
                    return;
                case 2:
                    AutoNearbyPeoplesUtils.getInstance().initData(operationParameterModel);
                    return;
                case 3:
                    switch (forwardTag) {
                        case 0:
                            OneKeyForwardUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 1:
                            OneKeyForwardAlbumUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 2:
                            OneKeyForwardChatRoomUtils.getInstance().initData(operationParameterModel);
                            return;
                        default:
                            return;
                    }
                case 4:
                    GroupSendBatchUtils.getInstance().initData(operationParameterModel);
                    return;
                case 5:
                    GroupSendOneByOneFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 6:
                    if (operationParameterModel.getSendGroupMethod() == 0) {
                        GroupSendGroupUtils.getInstance().initData(operationParameterModel);
                        return;
                    } else {
                        GroupSendGroupSearchUtils.getInstance().initData(operationParameterModel);
                        return;
                    }
                case 7:
                    GroupSendTagFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 8:
                    if (operationParameterModel.getAutoPullType() == 0 || operationParameterModel.getAutoPullType() == 2) {
                        AutoPullFriendsToGroupUtils.getInstance().initData(operationParameterModel);
                        return;
                    } else {
                        AutoPullTagFriendsToGroupUtils.getInstance().initData(operationParameterModel);
                        return;
                    }
                case 9:
                    OneKeyClickCommentUtils.getInstance().initData(operationParameterModel);
                    return;
                case 10:
                    if (operationParameterModel.getSendGroupMethod() == 0) {
                        GroupSendCardUtils.getInstance().initData(operationParameterModel);
                        return;
                    } else {
                        GroupSendCardSearchUtils.getInstance().initData(operationParameterModel);
                        return;
                    }
                case 11:
                    ObtainTagUtils.getInstance().initData();
                    return;
                case 12:
                    AutoAddGroupFansUtils.getInstance().initData(operationParameterModel);
                    return;
                case 13:
                    CloneCircleUtils.getInstance().initData(operationParameterModel);
                    return;
                case 14:
                    ObtainGroupUtils.getInstance().initData();
                    return;
                case 15:
                    OneKeyForwardChatRoomUtils.getInstance().initData(operationParameterModel);
                    return;
                case 16:
                    AutoAddContactsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 17:
                    GroupSendFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 18:
                    if (operationParameterModel.getSendGroupMethod() == 0) {
                        GroupSendCollectionToGroupUtils.getInstance().initData(operationParameterModel);
                        return;
                    } else {
                        GroupSendCollectionToGroupSearchUtils.getInstance().initData(operationParameterModel);
                        return;
                    }
                case 19:
                    GroupSendCollectionToFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 20:
                    CleanCircleAllUtils.getInstance().initData(operationParameterModel);
                    return;
                case 21:
                    if (operationParameterModel != null) {
                        switch (operationParameterModel.getDeleteFriendsType()) {
                            case 0:
                                CleanFriendsAllUtils.getInstance().initData(operationParameterModel);
                                return;
                            case 1:
                                CleanFriendsNickNameUtils.getInstance().initData(operationParameterModel);
                                return;
                            case 2:
                                CleanFriendsTagUtils.getInstance().initData(operationParameterModel);
                                return;
                            default:
                                return;
                        }
                    } else {
                        return;
                    }
                case 22:
                    CleanUnReadUtils.getInstance().initData();
                    return;
                case 23:
                    AutoPassValidationUtils.getInstance().initData(operationParameterModel);
                    return;
                case 24:
                    WxSportsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 25:
                    OneKeyForwardMaterialUtils.getInstance().initData(operationParameterModel);
                    return;
                case 26:
                    AutoAddContactsOptionalUtils.getInstance().initData(operationParameterModel);
                    return;
                case 27:
                    CleanZombieFanCustomUtils.getInstance().initData(operationParameterModel);
                    return;
                case 28:
                    CleanZombiePullGroupUtils.getInstance().initData(operationParameterModel);
                    return;
                case 29:
                    GroupSendPublicNumberToFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 30:
                    GroupSendPublicNumberToGroupsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 31:
                    if (operationParameterModel.getSendGroupMethod() == 0) {
                        AutoPullFriendsToAllGroupUtils.getInstance().initData(operationParameterModel);
                        return;
                    } else {
                        AutoPullFriendsToAllGroupSearchUtils.getInstance().initData(operationParameterModel);
                        return;
                    }
                case 32:
                    GroupSendPublicNumberToFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 33:
                    GroupSendPublicNumberToGroupsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 34:
                    ClearFriendRecoverUtils.getInstance().initData(operationParameterModel);
                    return;
                case 35:
                    AutoAddContactsNewFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 36:
                    GroupSendCardToFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 37:
                    GroupSendFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                case 38:
                    CleanZombieFanCircleUtils.getInstance().initData(operationParameterModel);
                    return;
                case 39:
                    CleanHomeMsgUtils.getInstance().initData(operationParameterModel);
                    return;
                case 40:
                case 41:
                case 42:
                case 43:
                    return;
                case 44:
                    OneKeyCopyWXHUtils.getInstance().initData(operationParameterModel);
                    return;
                case 45:
                    CopyFriendRecoverUtils.getInstance().initData(operationParameterModel);
                    return;
                case 46:
                    CopyTagsFriendsUtils.getInstance().initData(operationParameterModel);
                    return;
                default:
                    switch (i) {
                        case 48:
                            AutoAddContactsCameraUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 49:
                            GroupSendVoiceToFriendsUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 50:
                            GroupSendVoiceToGroupUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 51:
                            LikeCommentFriendsUtils.getInstance().initData(operationParameterModel);
                            return;
                        case 52:
                            LikeCommentSingleFriendUtils.getInstance().initData(operationParameterModel);
                            return;
                        default:
                            switch (i) {
                                case 55:
                                    if (operationParameterModel.getDeleteFriendsType() == 0) {
                                        CleanGroupsAllUtils.getInstance().initData(operationParameterModel);
                                        return;
                                    } else if (operationParameterModel.getDeleteFriendsType() == 1 || operationParameterModel.getDeleteFriendsType() == 2 || operationParameterModel.getDeleteFriendsType() == 3) {
                                        CleanGroupsOtherUtils.getInstance().initData(operationParameterModel);
                                        return;
                                    } else if (operationParameterModel.getDeleteFriendsType() == 4) {
                                        CleanGroupsSpecifiedUtils.getInstance().initData(operationParameterModel);
                                        return;
                                    } else {
                                        return;
                                    }
                                case 56:
                                    if (operationParameterModel.getSendCardType() == 0 || operationParameterModel.getSendCardType() == 1 || operationParameterModel.getSendCardType() == 2 || operationParameterModel.getSendCardType() == 4) {
                                        if (operationParameterModel.getIntimateMode() == 0) {
                                            LabelRemarkNickNameUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        } else if (operationParameterModel.getIntimateMode() == 1) {
                                            LabelRemarkLabelUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        } else {
                                            LabelRemarkAllUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        }
                                    } else if (operationParameterModel.getSendCardType() == 3) {
                                        LabelRemarkTagUtils.getInstance().initData(operationParameterModel);
                                        return;
                                    } else {
                                        return;
                                    }
                                case 57:
                                    AutoAddCardUtils.getInstance().initData(operationParameterModel);
                                    return;
                                case 58:
                                    CleanZombieFanCollectUtils.getInstance().initData(operationParameterModel);
                                    return;
                                default:
                                    switch (i) {
                                        case 65:
                                            AutoAddSearchUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        case 66:
                                        case 68:
                                            OneKeyForwardBigVideoUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        case 67:
                                            AutoAddMyCustomerUtils.getInstance().initData(operationParameterModel);
                                            return;
                                        default:
                                            switch (i) {
                                                case 10011:
                                                    ObtainTagCompanyUtils.getInstance().initData();
                                                    return;
                                                case 10012:
                                                    AutoAddGroupFansCompanyUtils.getInstance().initData(operationParameterModel);
                                                    return;
                                                default:
                                                    return;
                                            }
                                    }
                            }
                    }
            }
        } else {
            GroupSendOneByOneFriendsCompanyUtils.getInstance().initData(operationParameterModel);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAccessibilitySettingsOn() {
        int i;
        String string;
        try {
            String str = MyApplication.getConText().getPackageName() + "/" + AutoService.class.getCanonicalName();
            try {
                i = Settings.Secure.getInt(MyApplication.getConText().getContentResolver(), "accessibility_enabled");
            } catch (Settings.SettingNotFoundException e) {
                i = 0;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
            if (i == 1 && (string = Settings.Secure.getString(MyApplication.getConText().getContentResolver(), "enabled_accessibility_services")) != null) {
                simpleStringSplitter.setString(string);
                while (simpleStringSplitter.hasNext()) {
                    if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    public boolean isError() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            if (autoService.getRootInActiveWindow() != null) {
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFriendsDesc() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("朋友圈");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow.findAccessibilityNodeInfosByText("发消息");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.album_head_img);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_child_id);
                if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText2 == null || findAccessibilityNodeInfosByText2.size() <= 0) {
                    return findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXGroup() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_bottom_layout_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_title_id);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0).getText().toString().contains("(")) {
                    return true;
                }
                LogUtils.log("WS_BABY_node==NULL");
                return false;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXGroupCompanyOut() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("外部群，含");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow.findAccessibilityNodeInfosByText("群主");
                if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0) {
                    return true;
                }
                LogUtils.log("WS_BABY_node==NULL");
                return false;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXMainUI() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            if (autoService.getRootInActiveWindow() != null) {
                boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_isWXMain_" + findNodeIsExistById + "@" + findNodeIsExistByText + "@" + findNodeIsExistByText2 + "@" + wxVersionName);
                if (findNodeIsExistById) {
                    return findNodeIsExistByText || findNodeIsExistByText2;
                }
                return false;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXMainUICompany() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            if (autoService.getRootInActiveWindow() != null) {
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "工作台");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "消息");
                boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_isWXMain_" + findNodeIsExistByText + "@" + findNodeIsExistByText2 + "@" + findNodeIsExistByText3 + "@" + wxVersionName);
                return findNodeIsExistByText && findNodeIsExistByText2 && findNodeIsExistByText3;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXMoments() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.friends_circle_head_img_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.expand_more_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.right_more_id);
                boolean z = findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0;
                boolean z2 = findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0;
                boolean z3 = findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0;
                if (!z || (!z2 && !z3)) {
                    forwardTag = 0;
                    LogUtils.log("WS_BABY_isWXMomentsAndAlbum_NULL");
                    return false;
                }
                forwardTag = 0;
                return true;
            }
            checkError();
            LogUtils.log("WS_BABY_isWXMomentsAndAlbum_rootInActiveWindow_NULL");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_isWXMomentsAndAlbum_ERROR");
            return false;
        }
    }

    public boolean isWXMomentsAndAlbum() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.friends_circle_head_img_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.expand_more_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.right_more_id);
                boolean z = findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0;
                boolean z2 = findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0;
                boolean z3 = findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0;
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_circle_layout_id);
                boolean z4 = findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0;
                if (z && (z2 || z3)) {
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(getAutoService(), "详情");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(getAutoService(), "表情");
                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(getAutoService(), "发送");
                    if (!findNodeIsExistByText || !findNodeIsExistByText2 || !findNodeIsExistByText3) {
                        forwardTag = 0;
                        LogUtils.log("WS_BABY_isWXMomentsAndAlbum_0_1");
                        return true;
                    }
                    forwardTag = 0;
                    return false;
                } else if (z4) {
                    boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(getAutoService(), "详情");
                    boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(getAutoService(), "表情");
                    boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(getAutoService(), "发送");
                    if (!findNodeIsExistByText4 || !findNodeIsExistByText5 || !findNodeIsExistByText6) {
                        forwardTag = 1;
                        LogUtils.log("WS_BABY_isWXMomentsAndAlbum_1_1");
                        return true;
                    }
                    forwardTag = 1;
                    return false;
                } else {
                    LogUtils.log("WS_BABY_isWXMomentsAndAlbum_NULL");
                    forwardTag = 0;
                    return false;
                }
            } else {
                checkError();
                LogUtils.log("WS_BABY_isWXMomentsAndAlbum_rootInActiveWindow_NULL");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_isWXMomentsAndAlbum_ERROR");
            return false;
        }
    }

    public boolean isWXNewFriends() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("新的朋友");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow.findAccessibilityNodeInfosByText("添加朋友");
                return findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0 && !findAccessibilityNodeInfosByText2.get(0).getText().toString().contains("(");
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXP2P() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_bottom_layout_id);
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_title_id);
                return findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && !findAccessibilityNodeInfosByViewId2.get(0).getText().toString().contains("(");
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWXSportsUI() {
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.wx_sports_title_id);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                    return false;
                }
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                if (accessibilityNodeInfo != null) {
                    if ("排行榜".equals(accessibilityNodeInfo.getText() + "")) {
                        return true;
                    }
                }
                return false;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void jumpWSBaby() {
        try {
            Intent intent = new Intent();
            if (MyApplication.getConText() != null && AppManager.getAppManager() != null && AppManager.getAppManager().currentActivity() != null) {
                Activity currentActivity = AppManager.getAppManager().currentActivity();
                LogUtils.log("WS_BABY_JUMP_1");
                LogUtils.log("WS_BABY_JUMP_1" + currentActivity.getLocalClassName());
                intent.setFlags(268435456);
                intent.setClassName(MyApplication.WS, currentActivity.getLocalClassName());
                MyApplication.getConText().startActivity(intent);
            } else if (MyApplication.instance != null && MyApplication.instance.getBaseActivity() != null) {
                Intent intent2 = new Intent();
                LogUtils.log("WS_BABY_JUMP_2");
                intent2.setClassName(MyApplication.WS, MyApplication.WS_MainActivity);
                MyApplication.instance.getBaseActivity().startActivity(intent2);
            } else if (MyApplication.getConText() != null) {
                Intent intent3 = new Intent();
                LogUtils.log("WS_BABY_JUMP_3");
                intent3.setFlags(268435456);
                intent3.setClassName(MyApplication.WS, MyApplication.WS_MainActivity);
                MyApplication.getConText().startActivity(intent3);
            }
        } catch (Exception e) {
            try {
                if (MyApplication.instance != null && MyApplication.instance.getBaseActivity() != null) {
                    Intent intent4 = new Intent();
                    LogUtils.log("WS_BABY_JUMP_00");
                    intent4.setClassName(MyApplication.WS, MyApplication.WS_MainActivity);
                    MyApplication.instance.getBaseActivity().startActivity(intent4);
                } else if (MyApplication.getConText() != null) {
                    Intent intent5 = new Intent();
                    LogUtils.log("WS_BABY_JUMP_11");
                    intent5.setFlags(268435456);
                    intent5.setClassName(MyApplication.WS, MyApplication.WS_MainActivity);
                    MyApplication.getConText().startActivity(intent5);
                }
            } catch (Exception e2) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void openAlertDialog() {
        try {
            ToastUtils.showToast(MyApplication.getConText(), "请先开启微商宝贝服务功能");
            FloatWindow.destroy("back");
            FloatWindow.destroy("start");
            FloatWindow.destroy("end");
            FloatWindow.destroy("bottom_operation");
            FloatWindow.destroy("bottom");
            FloatWindow.destroy("bottom_error");
            FloatWindow.destroy("middle");
            FloatWindow.destroy("praise_start");
            FloatWindow.destroy("comment_start");
            Intent intent = new Intent(MyApplication.getConText(), AccessibilityOpenHelperActivity.class);
            intent.addFlags(268435456);
            MyApplication.instance.getBaseActivity().startActivity(intent);
        } catch (Exception e) {
        }
    }

    public void p2pWx(int i) {
        boolean isWXGroup;
        String str;
        boolean z = false;
        try {
            LogUtils.log("WS_BABY_TAGS" + this.executeTag);
            if (i == 3) {
                isWXGroup = isWXMomentsAndAlbum();
                LogUtils.log("WS_BABY_请进入朋友圈/相册，再点开始" + isWXGroup);
                str = "请前往【朋友圈或相册列表页】，再点开始";
            } else if (i == 9) {
                isWXGroup = isWXMoments();
                LogUtils.log("WS_BABY_请进入朋友圈，再点开始" + isWXGroup);
                str = "请前往【朋友圈页】，再点开始";
            } else if (i == 8 || i == 12 || i == 57) {
                isWXGroup = isWXGroup();
                LogUtils.log("WS_BABY_请从群聊中进入一个群，再点开始" + isWXGroup);
                str = "请进入【群聊聊天页】，再点开始";
            } else if (i == 13 || i == 52) {
                isWXGroup = isFriendsDesc();
                LogUtils.log("WS_BABY_请进入好友详情页🎨，再点开始" + isWXGroup);
                str = "请进入【好友详情页】，再点开始";
            } else if (i == 24) {
                isWXGroup = isWXSportsUI();
                LogUtils.log("WS_BABY_请进入微信排行榜，点开始" + isWXGroup);
                str = "请进入【微信运动步数排行榜】，点开始";
            } else if (i == 15) {
                if (isWXP2P() || isWXGroup()) {
                    z = true;
                }
                str = "请进入【聊天页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroup = z;
            } else if (i == 29 || i == 30 || i == 32 || i == 33) {
                if (isWXP2P() || isWXGroup()) {
                    z = true;
                }
                str = "请进入【聊天页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroup = z;
            } else if (i == 25) {
                if (isWXMainUI() || isWXMoments()) {
                    z = true;
                }
                str = "请进入【微信主页或朋友圈页】，再点开始";
                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                isWXGroup = z;
            } else if (i == 35) {
                isWXGroup = isWXNewFriends();
                LogUtils.log("WS_BABY_请进入新的朋友，再点开始" + isWXGroup);
                str = "请进入【新的朋友】页面，再点开始";
            } else if (i == 66) {
                isWXGroup = isError();
                str = "请前往微信主界面";
            } else {
                isWXGroup = isWXMainUI();
                LogUtils.log("WS_BABY_请前往微信主界面" + isWXGroup);
                str = "请前往【微信主界面】";
            }
            if (isWXGroup) {
                initUtils(MyApplication.instance.getOperationParameterModel(), i);
                initStartWx(i);
                return;
            }
            toastToUser2(str);
        } catch (Exception e) {
        }
    }

    public void removeBackView() {
        try {
            FloatWindow.destroy("back");
        } catch (Exception e) {
        }
    }

    public void removeBottomErrorView() {
        try {
            FloatWindow.destroy("bottom_error");
        } catch (Exception e) {
        }
    }

    public void removeBottomOperationView() {
        try {
            FloatWindow.destroy("bottom_operation");
        } catch (Exception e) {
        }
    }

    public void removeBottomView() {
        try {
            FloatWindow.destroy("bottom");
        } catch (Exception e) {
        }
    }

    public void removeCommentStartView() {
        try {
            FloatWindow.destroy("comment_start");
        } catch (Exception e) {
        }
    }

    public void removeEndView() {
        try {
            FloatWindow.destroy("end");
            if (this.endViewListener != null) {
                this.endViewListener.endViewDismiss();
            }
        } catch (Exception e) {
        }
    }

    public void removeMiddleView() {
        try {
            FloatWindow.destroy("middle");
        } catch (Exception e) {
        }
    }

    public void removePraiseStartView() {
        try {
            FloatWindow.destroy("praise_start");
        } catch (Exception e) {
        }
    }

    public void removeStartView() {
        try {
            FloatWindow.destroy("start");
        } catch (Exception e) {
        }
    }

    public void setEndViewListener(EndViewListener endViewListener2) {
        if (endViewListener2 != null) {
            try {
                this.endViewListener = endViewListener2;
            } catch (Exception e) {
            }
        }
    }

    public void setExecuteTag(String str) {
        this.executeTag = str;
    }

    public void setMiddleViewListener(MiddleViewListener middleViewListener2) {
        if (middleViewListener2 != null) {
            try {
                this.middleViewListener = middleViewListener2;
            } catch (Exception e) {
            }
        }
    }

    public void showBackView() {
        try {
            if (FloatWindow.get("back") == null || !FloatWindow.get("back").isShowing()) {
                this.mBackView = new FloatBackView(MyApplication.getConText());
                int[] screenSize = getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(false).setView((View) this.mBackView).setWidth(this.mBackView.width).setHeight(0, 0.2f).setX(screenSize[0] - this.mBackView.width).setTag("back").setHeight(this.mBackView.height).setY((screenSize[1] / 2) - DensityUtil.dp2px(MyApplication.getConText(), 12.0f)).setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                this.mBackView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            if ("9".equals(MyWindowManager.this.executeTag)) {
                                FloatWindow.destroy("back");
                                FloatWindow.destroy("praise_start");
                                FloatWindow.destroy("comment_start");
                            } else {
                                FloatWindow.destroy("start");
                                FloatWindow.destroy("back");
                            }
                            MyWindowManager.this.jumpWSBaby();
                        } catch (Exception e) {
                        }
                    }
                });
                FloatWindow.get("back").show();
            }
        } catch (Exception e) {
        }
    }

    public void showBottomErrorView(int i) {
        try {
            if (FloatWindow.get("bottom_error") == null || !FloatWindow.get("bottom_error").isShowing()) {
                this.mBottomErrorView = new FloatBottomErrorView(MyApplication.getConText());
                if (i != -1) {
                    this.mBottomErrorView.setBackGround(i);
                }
                getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(true).setView((View) this.mBottomErrorView).setGravity(81).setWidth(this.mBottomErrorView.width).setTouchEnable(false).setMoveType(1).setTag("bottom_error").setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                FloatWindow.get("bottom_error").show();
            }
        } catch (Exception e) {
        }
    }

    public void showBottomOperationView() {
        try {
            if (FloatWindow.get("bottom_operation") == null || !FloatWindow.get("bottom_operation").isShowing()) {
                this.mBottomOperationView = new FloatBottomOperationView(MyApplication.getConText());
                getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(true).setView((View) this.mBottomOperationView).setGravity(81).setWidth(this.mBottomOperationView.width).setDesktopShow(true).setTag("bottom_operation").setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                FloatWindow.get("bottom_operation").show();
            }
        } catch (Exception e) {
        }
    }

    public void showBottomView() {
        try {
            if (FloatWindow.get("bottom") == null || !FloatWindow.get("bottom").isShowing()) {
                this.mBottomView = new FloatBottomView(MyApplication.getConText());
                getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(true).setView((View) this.mBottomView).setGravity(81).setWidth(this.mBottomView.width).setTouchEnable(false).setMoveType(1).setTag("bottom").setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                FloatWindow.get("bottom").show();
            }
        } catch (Exception e) {
        }
    }

    public void showCommentStartView() {
        try {
            if (FloatWindow.get("comment_start") == null || !FloatWindow.get("comment_start").isShowing()) {
                this.mCommentView = new FloatCommentView(MyApplication.getConText());
                int[] screenSize = getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(false).setView((View) this.mCommentView).setWidth(this.mCommentView.width).setHeight(0, 0.2f).setX(screenSize[0] - this.mCommentView.width).setTag("comment_start").setHeight(this.mCommentView.height).setY((screenSize[1] / 2) - DensityUtil.dp2px(MyApplication.getConText(), 80.0f)).setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                this.mCommentView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyApplication.isComment = true;
                        MyWindowManager.this.startImgClick(MyApplication.getConText());
                    }
                });
                FloatWindow.get("comment_start").show();
            }
        } catch (Exception e) {
        }
    }

    public void showEndView() {
        try {
            if (FloatWindow.get("end") == null || !FloatWindow.get("end").isShowing()) {
                this.mEndView = new FloatEndView(MyApplication.getConText());
                int[] screenSize = getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(true).setView((View) this.mEndView).setWidth(this.mEndView.width).setHeight(0, 0.2f).setX(screenSize[0] - this.mEndView.width).setTag("end").setHeight(this.mEndView.height).setY((screenSize[1] / 2) - DensityUtil.dp2px(MyApplication.getConText(), 30.0f)).setDesktopShow(true).setMoveType(1).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                if (this.endViewListener != null) {
                    this.endViewListener.endViewShow();
                }
                this.mEndView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!PerformClickUtils.isFastClick2000()) {
                            System.out.println("WS_BABY_END_VIEW");
                            FloatWindow.destroy("end");
                            if (MyWindowManager.this.endViewListener != null) {
                                MyWindowManager.this.endViewListener.endViewDismiss();
                            }
                        }
                    }
                });
                FloatWindow.get("end").show();
            }
        } catch (Exception e) {
        }
    }

    public void showFloatWindow(String str) {
        LogUtils.log("WS_BABY_showFloatWindow");
        try {
            this.model = MyApplication.instance.getOperationParameterModel();
            if (str == null) {
                str = this.model.getTaskNum();
            }
        } catch (Exception e) {
        }
        setExecuteTag(str);
        if (!"9".equals(str)) {
            if ("47".equals(str)) {
                showEndView();
                return;
            }
            showStartView();
            showBackView();
        } else if (this.model.getPraiseCommentType() == 0) {
            showPraiseStartView();
            showCommentStartView();
            showBackView();
        } else if (this.model.getPraiseCommentType() == 1) {
            showPraiseStartView();
            showBackView();
        } else {
            showCommentStartView();
            showBackView();
        }
    }

    public void showFloatWindowCompany(String str) {
        LogUtils.log("WS_BABY_showFloatWindow");
        try {
            this.model = MyApplication.instance.getOperationParameterModel();
            if (str == null) {
                str = this.model.getTaskNum();
            }
        } catch (Exception e) {
        }
        setExecuteTag(str);
        if (!"1009".equals(str)) {
            if ("10047".equals(str)) {
                showEndView();
                return;
            }
            showStartView();
            showBackView();
        } else if (this.model.getPraiseCommentType() == 0) {
            showPraiseStartView();
            showCommentStartView();
            showBackView();
        } else if (this.model.getPraiseCommentType() == 1) {
            showPraiseStartView();
            showBackView();
        } else {
            showCommentStartView();
            showBackView();
        }
    }

    public void showMiddleView() {
        try {
            if (FloatWindow.get("middle") == null || !FloatWindow.get("middle").isShowing()) {
                this.mMiddleView = new FloatMiddleView(MyApplication.getConText());
                getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(false).setView((View) this.mMiddleView).setGravity(17).setWidth(this.mMiddleView.width).setHeight(this.mMiddleView.height).setTag("middle").setDesktopShow(true).setViewStateListener(new ViewStateListener() {
                    public void onBackToDesktop() {
                    }

                    public void onDismiss() {
                    }

                    public void onHide() {
                    }

                    public void onMoveAnimEnd() {
                    }

                    public void onMoveAnimStart() {
                    }

                    public void onPositionUpdate(int i, int i2) {
                    }

                    public void onShow() {
                        LogUtils.log("WS_BABY_MiddleView.onShow");
                    }
                }).setPermissionListener((PermissionListener) null).build();
                FloatWindow.get("middle").show();
                if (this.middleViewListener != null) {
                    this.middleViewListener.middleViewShow();
                }
            }
        } catch (Exception e) {
        }
    }

    public void showPraiseStartView() {
        try {
            int dp2px = DensityUtil.dp2px(MyApplication.getConText(), 160.0f);
            try {
                OperationParameterModel operationParameterModel = MyApplication.instance.getOperationParameterModel();
                if (operationParameterModel != null && operationParameterModel.getPraiseCommentType() == 1) {
                    dp2px = DensityUtil.dp2px(MyApplication.getConText(), 80.0f);
                }
            } catch (Exception e) {
            }
            if (FloatWindow.get("praise_start") == null || !FloatWindow.get("praise_start").isShowing()) {
                this.mPraiseView = new FloatPraiseView(MyApplication.getConText());
                int[] screenSize = getScreenSize();
                FloatWindow.with(MyApplication.getConText()).setScreenLight(false).setView((View) this.mPraiseView).setWidth(this.mPraiseView.width).setHeight(0, 0.2f).setX(screenSize[0] - this.mPraiseView.width).setTag("praise_start").setHeight(this.mPraiseView.height).setY((screenSize[1] / 2) - dp2px).setDesktopShow(true).setViewStateListener((ViewStateListener) null).setPermissionListener((PermissionListener) null).build();
                this.mPraiseView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyApplication.isComment = false;
                        MyWindowManager.this.startImgClick(MyApplication.getConText());
                    }
                });
                FloatWindow.get("praise_start").show();
            }
        } catch (Exception e2) {
        }
    }

    public void showStartView() {
        try {
            if (FloatWindow.get("start") == null || !FloatWindow.get("start").isShowing()) {
                this.mStartView = new FloatStartView(MyApplication.getConText());
                int[] screenSize = getScreenSize();
                MyApplication.startX = screenSize[0] - this.mStartView.width;
                MyApplication.startY = (screenSize[1] / 2) - DensityUtil.dp2px(MyApplication.getConText(), 80.0f);
                FloatWindow.with(MyApplication.getConText()).setScreenLight(false).setView((View) this.mStartView).setWidth(this.mStartView.width).setHeight(0, 0.2f).setX(screenSize[0] - this.mStartView.width).setTag("start").setHeight(this.mStartView.height).setY((screenSize[1] / 2) - DensityUtil.dp2px(MyApplication.getConText(), 80.0f)).setDesktopShow(true).setViewStateListener(this.startViewStateListener).setPermissionListener((PermissionListener) null).build();
                this.mStartView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyWindowManager.this.startImgClick(MyApplication.getConText());
                    }
                });
                FloatWindow.get("start").show();
            }
        } catch (Exception e) {
        }
    }

    public void startImgClick(Context context) {
        Integer num;
        if (!PerformClickUtils.isFastClick()) {
            if (isAccessibilitySettingsOn()) {
                try {
                    num = Integer.valueOf(Integer.parseInt(this.executeTag));
                    try {
                        if (num.intValue() > 1000) {
                            companyWx(num.intValue());
                            return;
                        } else {
                            p2pWx(num.intValue());
                            return;
                        }
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    num = -1;
                }
            } else {
                Log.d(BaseActivity.TAG, "辅助功能未开启！");
                openAlertDialog();
                return;
            }
        } else {
            return;
        }
        p2pWx(num.intValue());
    }

    public void stopAccessibilityService() {
        try {
            AutoService autoService = MyApplication.instance.getAutoService();
            MyApplication.enforceable = false;
            if (autoService != null) {
                autoService.stopSelf();
            }
        } catch (Exception e) {
        }
    }

    public void toastToUser2(final String str) {
        BaseServiceUtils.getMainHandler().post(new Runnable() {
            public void run() {
                try {
                    MyWindowManager.this.showBottomErrorView(2131230814);
                    FloatBottomErrorView bottomErrorView = MyWindowManager.this.getBottomErrorView();
                    bottomErrorView.setBottomText(str + MyWindowManager.this.errorString, true);
                    Timer unused = MyWindowManager.this.timer = new Timer();
                    MyWindowManager.this.timer.schedule(new TimerTask() {
                        public void run() {
                            if (MyWindowManager.this.timer != null) {
                                MyWindowManager.this.timer.cancel();
                                Timer unused = MyWindowManager.this.timer = null;
                            }
                            BaseServiceUtils.getMainHandler().post(new Runnable() {
                                public void run() {
                                    MyWindowManager.this.removeBottomErrorView();
                                }
                            });
                        }
                    }, 2000);
                } catch (Exception e) {
                }
            }
        });
    }

    public void toastToUser3(final String str, final int i, final OnToastEndListener onToastEndListener) {
        BaseServiceUtils.getMainHandler().post(new Runnable() {
            public void run() {
                try {
                    MyWindowManager.this.showBottomErrorView(2131230814);
                    FloatBottomErrorView bottomErrorView = MyWindowManager.this.getBottomErrorView();
                    bottomErrorView.setBottomText(str + MyWindowManager.this.errorString);
                    Timer unused = MyWindowManager.this.timer = new Timer();
                    MyWindowManager.this.timer.schedule(new TimerTask() {
                        public void run() {
                            if (MyWindowManager.this.timer != null) {
                                MyWindowManager.this.timer.cancel();
                                Timer unused = MyWindowManager.this.timer = null;
                            }
                            BaseServiceUtils.getMainHandler().post(new Runnable() {
                                public void run() {
                                    if (onToastEndListener != null) {
                                        onToastEndListener.toastEnd();
                                    }
                                    MyWindowManager.this.removeBottomErrorView();
                                }
                            });
                        }
                    }, (long) i);
                } catch (Exception e) {
                }
            }
        });
    }

    public void toastToUserError(final String str) {
        BaseServiceUtils.getMainHandler().post(new Runnable() {
            public void run() {
                try {
                    MyWindowManager.this.showBottomErrorView(2131230814);
                    FloatBottomErrorView bottomErrorView = MyWindowManager.this.getBottomErrorView();
                    bottomErrorView.setBottomText(str + MyWindowManager.this.errorString, true);
                    Timer unused = MyWindowManager.this.timer = new Timer();
                    MyWindowManager.this.timer.schedule(new TimerTask() {
                        public void run() {
                            if (MyWindowManager.this.timer != null) {
                                MyWindowManager.this.timer.cancel();
                                Timer unused = MyWindowManager.this.timer = null;
                            }
                            BaseServiceUtils.getMainHandler().post(new Runnable() {
                                public void run() {
                                    MyWindowManager.this.removeBottomErrorView();
                                }
                            });
                        }
                    }, 3500);
                } catch (Exception e) {
                }
            }
        });
    }
}
