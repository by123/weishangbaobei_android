package com.wx.assistants.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
        public void onPositionUpdate(int i, int i2) {
            MyApplication.startX = i;
            MyApplication.startY = i2;
            LogUtils.log("onPositionUpdate: x=" + i + " y=" + i2);
            LogUtils.log("onPositionUpdate: xxxx=" + MyApplication.startX + " y=" + MyApplication.startY);
        }

        public void onShow() {
            LogUtils.log("onShow");
        }

        public void onHide() {
            LogUtils.log("onHide");
        }

        public void onDismiss() {
            LogUtils.log("onDismiss");
        }

        public void onMoveAnimStart() {
            LogUtils.log("onMoveAnimStart");
        }

        public void onMoveAnimEnd() {
            LogUtils.log("onMoveAnimEnd");
        }

        public void onBackToDesktop() {
            LogUtils.log("onBackToDesktop");
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

    public static MyWindowManager newInstance() {
        try {
            if (windowManager == null) {
                windowManager = new MyWindowManager();
                initWXVersion();
                initVersion();
            }
        } catch (Exception unused) {
        }
        return windowManager;
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

    public AutoService getAutoService() {
        return MyApplication.instance.getAutoService();
    }

    public void setExecuteTag(String str) {
        this.executeTag = str;
    }

    public void setEndViewListener(EndViewListener endViewListener2) {
        if (endViewListener2 != null) {
            try {
                this.endViewListener = endViewListener2;
            } catch (Exception unused) {
            }
        }
    }

    public void setMiddleViewListener(MiddleViewListener middleViewListener2) {
        if (middleViewListener2 != null) {
            try {
                this.middleViewListener = middleViewListener2;
            } catch (Exception unused) {
            }
        }
    }

    public Context getContext() {
        return MyApplication.getConText();
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
        } catch (Exception unused) {
        }
    }

    public void removeMiddleView() {
        try {
            FloatWindow.destroy("middle");
        } catch (Exception unused) {
        }
    }

    public FloatBottomView getBottomView() {
        try {
            if (this.mBottomView != null) {
                return this.mBottomView;
            }
            return new FloatBottomView(MyApplication.getConText());
        } catch (Exception unused) {
            return null;
        }
    }

    public FloatBottomOperationView getmBottomOperationView() {
        try {
            if (this.mBottomOperationView != null) {
                return this.mBottomOperationView;
            }
            return new FloatBottomOperationView(MyApplication.getConText());
        } catch (Exception unused) {
            return null;
        }
    }

    public FloatBottomErrorView getBottomErrorView() {
        try {
            if (this.mBottomErrorView != null) {
                return this.mBottomErrorView;
            }
            return new FloatBottomErrorView(MyApplication.getConText());
        } catch (Exception unused) {
            return null;
        }
    }

    public FloatMiddleView getMiddleView() {
        try {
            if (this.mMiddleView != null) {
                return this.mMiddleView;
            }
            return new FloatMiddleView(MyApplication.getConText());
        } catch (Exception unused) {
            return null;
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
        } catch (Exception unused) {
        }
    }

    public void removeStartView() {
        try {
            FloatWindow.destroy("start");
        } catch (Exception unused) {
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
            } catch (Exception unused) {
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
        } catch (Exception unused2) {
        }
    }

    public void removePraiseStartView() {
        try {
            FloatWindow.destroy("praise_start");
        } catch (Exception unused) {
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
        } catch (Exception unused) {
        }
    }

    public void removeCommentStartView() {
        try {
            FloatWindow.destroy("comment_start");
        } catch (Exception unused) {
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
        } catch (Exception unused) {
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
        } catch (Exception unused) {
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
        } catch (Exception unused) {
        }
    }

    public void removeBottomErrorView() {
        try {
            FloatWindow.destroy("bottom_error");
        } catch (Exception unused) {
        }
    }

    public void removeBottomView() {
        try {
            FloatWindow.destroy("bottom");
        } catch (Exception unused) {
        }
    }

    public void removeBottomOperationView() {
        try {
            FloatWindow.destroy("bottom_operation");
        } catch (Exception unused) {
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
        } catch (Exception unused) {
        }
    }

    public void removeEndView() {
        try {
            FloatWindow.destroy("end");
            if (this.endViewListener != null) {
                this.endViewListener.endViewDismiss();
            }
        } catch (Exception unused) {
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
                        } catch (Exception unused) {
                        }
                    }
                });
                FloatWindow.get("back").show();
            }
        } catch (Exception unused) {
        }
    }

    public void removeBackView() {
        try {
            FloatWindow.destroy("back");
        } catch (Exception unused) {
        }
    }

    public int[] getScreenSize() {
        WindowManager windowManager2 = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager2.getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private WindowManager getWindowManager() {
        try {
            if (this.mWindowManager == null) {
                this.mWindowManager = (WindowManager) MyApplication.getConText().getSystemService("window");
            }
            return this.mWindowManager;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a2, code lost:
        if (com.wx.assistants.application.MyApplication.instance == null) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ac, code lost:
        r0 = new android.content.Intent();
        com.wx.assistants.utils.LogUtils.log("WS_BABY_JUMP_00");
        r0.setClassName(com.wx.assistants.application.MyApplication.WS, com.wx.assistants.application.MyApplication.WS_MainActivity);
        com.wx.assistants.application.MyApplication.instance.getBaseActivity().startActivity(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00cb, code lost:
        if (com.wx.assistants.application.MyApplication.getConText() != null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00cd, code lost:
        r1 = new android.content.Intent();
        com.wx.assistants.utils.LogUtils.log("WS_BABY_JUMP_11");
        r1.setFlags(268435456);
        r1.setClassName(com.wx.assistants.application.MyApplication.WS, com.wx.assistants.application.MyApplication.WS_MainActivity);
        com.wx.assistants.application.MyApplication.getConText().startActivity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x00a0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void jumpWSBaby() {
        /*
            r5 = this;
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x00a0 }
            r1.<init>()     // Catch:{ Exception -> 0x00a0 }
            android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00a0 }
            if (r2 == 0) goto L_0x0057
            com.wx.assistants.utils.AppManager r2 = com.wx.assistants.utils.AppManager.getAppManager()     // Catch:{ Exception -> 0x00a0 }
            if (r2 == 0) goto L_0x0057
            com.wx.assistants.utils.AppManager r2 = com.wx.assistants.utils.AppManager.getAppManager()     // Catch:{ Exception -> 0x00a0 }
            android.app.Activity r2 = r2.currentActivity()     // Catch:{ Exception -> 0x00a0 }
            if (r2 == 0) goto L_0x0057
            com.wx.assistants.utils.AppManager r2 = com.wx.assistants.utils.AppManager.getAppManager()     // Catch:{ Exception -> 0x00a0 }
            android.app.Activity r2 = r2.currentActivity()     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r3 = "WS_BABY_JUMP_1"
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x00a0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a0 }
            r3.<init>()     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r4 = "WS_BABY_JUMP_1"
            r3.append(r4)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r4 = r2.getLocalClassName()     // Catch:{ Exception -> 0x00a0 }
            r3.append(r4)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00a0 }
            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x00a0 }
            r1.setFlags(r0)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r3 = "com.example.wx.assistant"
            java.lang.String r2 = r2.getLocalClassName()     // Catch:{ Exception -> 0x00a0 }
            r1.setClassName(r3, r2)     // Catch:{ Exception -> 0x00a0 }
            android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00a0 }
            r2.startActivity(r1)     // Catch:{ Exception -> 0x00a0 }
            goto L_0x00e8
        L_0x0057:
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00a0 }
            if (r1 == 0) goto L_0x007e
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00a0 }
            android.app.Activity r1 = r1.getBaseActivity()     // Catch:{ Exception -> 0x00a0 }
            if (r1 == 0) goto L_0x007e
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x00a0 }
            r1.<init>()     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r2 = "WS_BABY_JUMP_2"
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r2 = "com.example.wx.assistant"
            java.lang.String r3 = "com.wx.assistants.activity.MainActivity"
            r1.setClassName(r2, r3)     // Catch:{ Exception -> 0x00a0 }
            com.wx.assistants.application.MyApplication r2 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00a0 }
            android.app.Activity r2 = r2.getBaseActivity()     // Catch:{ Exception -> 0x00a0 }
            r2.startActivity(r1)     // Catch:{ Exception -> 0x00a0 }
            goto L_0x00e8
        L_0x007e:
            android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00a0 }
            if (r1 == 0) goto L_0x00e8
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x00a0 }
            r1.<init>()     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r2 = "WS_BABY_JUMP_3"
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x00a0 }
            r1.setFlags(r0)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r2 = "com.example.wx.assistant"
            java.lang.String r3 = "com.wx.assistants.activity.MainActivity"
            r1.setClassName(r2, r3)     // Catch:{ Exception -> 0x00a0 }
            android.content.Context r2 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00a0 }
            r2.startActivity(r1)     // Catch:{ Exception -> 0x00a0 }
            goto L_0x00e8
        L_0x00a0:
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00e8 }
            if (r1 == 0) goto L_0x00c7
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00e8 }
            android.app.Activity r1 = r1.getBaseActivity()     // Catch:{ Exception -> 0x00e8 }
            if (r1 == 0) goto L_0x00c7
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x00e8 }
            r0.<init>()     // Catch:{ Exception -> 0x00e8 }
            java.lang.String r1 = "WS_BABY_JUMP_00"
            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x00e8 }
            java.lang.String r1 = "com.example.wx.assistant"
            java.lang.String r2 = "com.wx.assistants.activity.MainActivity"
            r0.setClassName(r1, r2)     // Catch:{ Exception -> 0x00e8 }
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x00e8 }
            android.app.Activity r1 = r1.getBaseActivity()     // Catch:{ Exception -> 0x00e8 }
            r1.startActivity(r0)     // Catch:{ Exception -> 0x00e8 }
            goto L_0x00e8
        L_0x00c7:
            android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00e8 }
            if (r1 == 0) goto L_0x00e8
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x00e8 }
            r1.<init>()     // Catch:{ Exception -> 0x00e8 }
            java.lang.String r2 = "WS_BABY_JUMP_11"
            com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x00e8 }
            r1.setFlags(r0)     // Catch:{ Exception -> 0x00e8 }
            java.lang.String r0 = "com.example.wx.assistant"
            java.lang.String r2 = "com.wx.assistants.activity.MainActivity"
            r1.setClassName(r0, r2)     // Catch:{ Exception -> 0x00e8 }
            android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00e8 }
            r0.startActivity(r1)     // Catch:{ Exception -> 0x00e8 }
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service.MyWindowManager.jumpWSBaby():void");
    }

    public void stopAccessibilityService() {
        try {
            AutoService autoService = MyApplication.instance.getAutoService();
            MyApplication.enforceable = false;
            if (autoService != null) {
                autoService.stopSelf();
            }
        } catch (Exception unused) {
        }
    }

    public void showFloatWindow(String str) {
        LogUtils.log("WS_BABY_showFloatWindow");
        try {
            this.model = MyApplication.instance.getOperationParameterModel();
            if (str == null) {
                str = this.model.getTaskNum();
            }
        } catch (Exception unused) {
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
        } catch (Exception unused) {
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

    public void startImgClick(Context context) {
        Integer num;
        if (!PerformClickUtils.isFastClick()) {
            if (isAccessibilitySettingsOn()) {
                try {
                    num = Integer.valueOf(Integer.parseInt(this.executeTag));
                    try {
                        if (num.intValue() > 1000) {
                            companyWx(num.intValue());
                        } else {
                            p2pWx(num.intValue());
                        }
                    } catch (Exception unused) {
                        p2pWx(num.intValue());
                    }
                } catch (Exception unused2) {
                    num = -1;
                    p2pWx(num.intValue());
                }
            } else {
                Log.d(BaseActivity.TAG, "辅助功能未开启！");
                openAlertDialog();
            }
        }
    }

    public void companyWx(int i) {
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        try {
            LogUtils.log("WS_BABY_TAGS" + this.executeTag);
            if (i == 1003) {
                z = isWXMomentsAndAlbum();
                str = "请前往【朋友圈或相册列表页】，再点开始";
                LogUtils.log("WS_BABY_请进入朋友圈/相册，再点开始" + z);
            } else if (i == 1009) {
                z = isWXMoments();
                str = "请前往【朋友圈页】，再点开始";
                LogUtils.log("WS_BABY_请进入朋友圈，再点开始" + z);
            } else {
                if (!(i == 1008 || i == 10012)) {
                    if (i != 10057) {
                        if (i != 10013) {
                            if (i != 10052) {
                                if (i == 10024) {
                                    z = isWXSportsUI();
                                    str = "请进入【微信运动步数排行榜】，点开始";
                                    LogUtils.log("WS_BABY_请进入微信排行榜，点开始" + z);
                                } else if (i == 10015) {
                                    if (!isWXP2P()) {
                                        if (!isWXGroupCompanyOut()) {
                                            z = false;
                                            str = "请进入【聊天页】，再点开始";
                                            LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                        }
                                    }
                                    z = true;
                                    str = "请进入【聊天页】，再点开始";
                                    LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                } else {
                                    if (!(i == 10029 || i == 10030 || i == 10032)) {
                                        if (i != 10033) {
                                            if (i == 10025) {
                                                if (!isWXMainUICompany()) {
                                                    if (!isWXMoments()) {
                                                        z3 = false;
                                                        str = "请进入【微信主页或朋友圈页】，再点开始";
                                                        LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                                    }
                                                }
                                                z3 = true;
                                                str = "请进入【微信主页或朋友圈页】，再点开始";
                                                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                            } else if (i == 10035) {
                                                z = isWXNewFriends();
                                                str = "请进入【新的朋友】页面，再点开始";
                                                LogUtils.log("WS_BABY_请进入新的朋友，再点开始" + z);
                                            } else if (i == 10037) {
                                                z = isWXP2P();
                                                str = "请进入【聊天会话】页面，再点开始";
                                                LogUtils.log("WS_BABY_请进入聊天🎨，再点开始" + z);
                                            } else {
                                                z = isWXMainUICompany();
                                                str = "请前往【微信主界面】";
                                                LogUtils.log("WS_BABY_请前往微信主界面" + z);
                                            }
                                        }
                                    }
                                    if (!isWXP2P()) {
                                        if (!isWXGroupCompanyOut()) {
                                            z2 = false;
                                            str = "请进入【聊天页】，再点开始";
                                            LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                        }
                                    }
                                    z2 = true;
                                    str = "请进入【聊天页】，再点开始";
                                    LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                }
                            }
                        }
                        z = isFriendsDesc();
                        str = "请进入【好友详情页】，再点开始";
                        LogUtils.log("WS_BABY_请进入好友详情页🎨，再点开始" + z);
                    }
                }
                z = isWXGroupCompanyOut();
                str = "请进入【外部群群聊聊天页】，再点开始";
                LogUtils.log("WS_BABY_请从群聊中进入一个群，再点开始" + z);
            }
            if (z) {
                initUtils(MyApplication.instance.getOperationParameterModel(), i);
                initStartWx(i);
                return;
            }
            toastToUser2(str);
        } catch (Exception unused) {
        }
    }

    public void p2pWx(int i) {
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        try {
            LogUtils.log("WS_BABY_TAGS" + this.executeTag);
            if (i == 3) {
                z = isWXMomentsAndAlbum();
                str = "请前往【朋友圈或相册列表页】，再点开始";
                LogUtils.log("WS_BABY_请进入朋友圈/相册，再点开始" + z);
            } else if (i == 9) {
                z = isWXMoments();
                str = "请前往【朋友圈页】，再点开始";
                LogUtils.log("WS_BABY_请进入朋友圈，再点开始" + z);
            } else {
                if (!(i == 8 || i == 12)) {
                    if (i != 57) {
                        if (i != 13) {
                            if (i != 52) {
                                if (i == 24) {
                                    z = isWXSportsUI();
                                    str = "请进入【微信运动步数排行榜】，点开始";
                                    LogUtils.log("WS_BABY_请进入微信排行榜，点开始" + z);
                                } else if (i == 15) {
                                    if (!isWXP2P()) {
                                        if (!isWXGroup()) {
                                            z = false;
                                            str = "请进入【聊天页】，再点开始";
                                            LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                        }
                                    }
                                    z = true;
                                    str = "请进入【聊天页】，再点开始";
                                    LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                } else {
                                    if (!(i == 29 || i == 30 || i == 32)) {
                                        if (i != 33) {
                                            if (i == 25) {
                                                if (!isWXMainUI()) {
                                                    if (!isWXMoments()) {
                                                        z3 = false;
                                                        str = "请进入【微信主页或朋友圈页】，再点开始";
                                                        LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                                    }
                                                }
                                                z3 = true;
                                                str = "请进入【微信主页或朋友圈页】，再点开始";
                                                LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                            } else if (i == 35) {
                                                z = isWXNewFriends();
                                                str = "请进入【新的朋友】页面，再点开始";
                                                LogUtils.log("WS_BABY_请进入新的朋友，再点开始" + z);
                                            } else if (i == 66) {
                                                str = "请前往微信主界面";
                                                z = isError();
                                            } else {
                                                z = isWXMainUI();
                                                str = "请前往【微信主界面】";
                                                LogUtils.log("WS_BABY_请前往微信主界面" + z);
                                            }
                                        }
                                    }
                                    if (!isWXP2P()) {
                                        if (!isWXGroup()) {
                                            z2 = false;
                                            str = "请进入【聊天页】，再点开始";
                                            LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                        }
                                    }
                                    z2 = true;
                                    str = "请进入【聊天页】，再点开始";
                                    LogUtils.log("WS_BABY_请进入聊天页，再点开始" + z);
                                }
                            }
                        }
                        z = isFriendsDesc();
                        str = "请进入【好友详情页】，再点开始";
                        LogUtils.log("WS_BABY_请进入好友详情页🎨，再点开始" + z);
                    }
                }
                z = isWXGroup();
                str = "请进入【群聊聊天页】，再点开始";
                LogUtils.log("WS_BABY_请从群聊中进入一个群，再点开始" + z);
            }
            if (z) {
                initUtils(MyApplication.instance.getOperationParameterModel(), i);
                initStartWx(i);
                return;
            }
            toastToUser2(str);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkError() {
        /*
            r3 = this;
            java.lang.String r0 = "\n检测到【辅助服务】启动异常\n请把辅助服务重新开启！"
            r3.errorString = r0
            com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x001f }
            if (r0 == 0) goto L_0x001f
            com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x001f }
            com.wx.assistants.service.AutoService r0 = r0.getAutoService()     // Catch:{ Exception -> 0x001f }
            if (r0 == 0) goto L_0x001f
            com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x001f }
            com.wx.assistants.service.AutoService r0 = r0.getAutoService()     // Catch:{ Exception -> 0x001f }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x001f }
            r2 = 24
            if (r1 < r2) goto L_0x001f
            r0.disableSelf()     // Catch:{ Exception -> 0x001f }
        L_0x001f:
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x0038 }
            android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0038 }
            java.lang.Class<com.wx.assistants.activity.AccessibilityOpenHelperActivity> r2 = com.wx.assistants.activity.AccessibilityOpenHelperActivity.class
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0038 }
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r1)     // Catch:{ Exception -> 0x0038 }
            com.wx.assistants.application.MyApplication r1 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x0038 }
            android.app.Activity r1 = r1.getBaseActivity()     // Catch:{ Exception -> 0x0038 }
            r1.startActivity(r0)     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service.MyWindowManager.checkError():void");
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
                if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText2 == null || findAccessibilityNodeInfosByText2.size() <= 0 || findAccessibilityNodeInfosByText2.get(0).getText().toString().contains("(")) {
                    return false;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        } catch (Exception unused) {
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
            } catch (Settings.SettingNotFoundException unused) {
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
        } catch (Exception unused2) {
        }
        return false;
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
        } catch (Exception unused) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWXSportsUI() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        try {
            this.errorString = "";
            AutoService autoService = getAutoService();
            if (autoService == null) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.wx_sports_title_id);
                if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) == null)) {
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
                if (!findNodeIsExistById || (!findNodeIsExistByText && !findNodeIsExistByText2)) {
                    return false;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
                if (!findNodeIsExistByText || !findNodeIsExistByText2 || !findNodeIsExistByText3) {
                    return false;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || findAccessibilityNodeInfosByViewId2.get(0).getText().toString().contains("(")) {
                    return false;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
                if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0) {
                    return true;
                }
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                    return false;
                }
                return true;
            }
            checkError();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
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
        }
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
                } catch (Exception unused2) {
                }
            }
        });
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
                } catch (Exception unused2) {
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
                } catch (Exception unused2) {
                }
            }
        });
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

    public static void initVersion() {
        BaseServiceUtils.formWindowManagerSetting();
        BaseServiceCompanyUtils.formWindowManagerSetting();
    }
}
