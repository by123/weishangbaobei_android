package com.wx.assistants.service_utils;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.activity.ContactActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.FloatMiddleView;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class CleanZombiePullGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static String contact_nav_search_img_id = "com.tencent.mm:id/ij";
    private static String contact_nav_search_viewgroup_id = "com.tencent.mm:id/jf";
    /* access modifiers changed from: private */
    public static String delete_title_id = "android:id/text1";
    /* access modifiers changed from: private */
    public static String dialog_ok_id = "com.tencent.mm:id/ayb";
    /* access modifiers changed from: private */
    public static String dialog_text = "com.tencent.mm:id/d6y";
    private static CleanZombiePullGroupUtils instance = null;
    /* access modifiers changed from: private */
    public static String list_head_img_id = "com.tencent.mm:id/dwu";
    /* access modifiers changed from: private */
    public static String list_select_checkbox = "com.tencent.mm:id/zk";
    /* access modifiers changed from: private */
    public static String list_select_confirm_id = "com.tencent.mm:id/jq";
    /* access modifiers changed from: private */
    public static String list_select_delete_checkbox = "com.tencent.mm:id/aus";
    /* access modifiers changed from: private */
    public static String list_select_delete_id = "com.tencent.mm:id/e4j";
    /* access modifiers changed from: private */
    public static String list_select_delete_name_id = "com.tencent.mm:id/e4n";
    /* access modifiers changed from: private */
    public static String list_select_id = "com.tencent.mm:id/i2";
    /* access modifiers changed from: private */
    public static String list_select_name_id = "com.tencent.mm:id/pp";
    /* access modifiers changed from: private */
    public static String more_recycle_view_id = "com.tencent.mm:id/d3p";
    /* access modifiers changed from: private */
    public static String remark_edit_id = "com.tencent.mm:id/b3v";
    /* access modifiers changed from: private */
    public static String right_more_id = "com.tencent.mm:id/jr";
    private static String search_page_back = "com.tencent.mm:id/kf";
    private int backTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public int deletedNum = 0;
    private boolean isBackFTSMainUI = true;
    /* access modifiers changed from: private */
    public boolean isCanDelete = false;
    /* access modifiers changed from: private */
    public boolean isCanExecuteRemarkDelete = false;
    /* access modifiers changed from: private */
    public boolean isChatroomInfoUI = true;
    private boolean isChattingUI = true;
    private boolean isExistGroup = false;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    private boolean isFirstChatroomContactUI = true;
    private boolean isFirstChatroomContactUI2 = true;
    private boolean isFirstChattingUI = true;
    private boolean isFirstContactInfoUI = true;
    /* access modifiers changed from: private */
    public boolean isFirstFTSMainUI = true;
    /* access modifiers changed from: private */
    public boolean isFromBack;
    /* access modifiers changed from: private */
    public boolean isFromRemarkDeleteBack;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    private boolean isLauncherUI = true;
    private boolean isPullEnd = false;
    private boolean isSingleChatInfoUI = true;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public int jumpCheckedNum = 0;
    /* access modifiers changed from: private */
    public int jumpTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public int lastOperationNum = 0;
    /* access modifiers changed from: private */
    public int maxCreateGroupNum = 10;
    /* access modifiers changed from: private */
    public int maxPullGroupNum = 20;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> nilFriends = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int operationJumpNum = 1;
    /* access modifiers changed from: private */
    public int operationNum = 1;
    private LinkedHashSet<String> oriFriends = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public int scrollBottomNum = 3;
    /* access modifiers changed from: private */
    public int scrollRecordBottomNum = 1;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$2108(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.operationFirstNum;
        cleanZombiePullGroupUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$2508(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.operationJumpNum;
        cleanZombiePullGroupUtils.operationJumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$2708(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.jumpCheckedNum;
        cleanZombiePullGroupUtils.jumpCheckedNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$2808(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.operationNum;
        cleanZombiePullGroupUtils.operationNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$2908(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.pullRecordNum;
        cleanZombiePullGroupUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$3208(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.scrollRecordBottomNum;
        cleanZombiePullGroupUtils.scrollRecordBottomNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(CleanZombiePullGroupUtils cleanZombiePullGroupUtils) {
        int i = cleanZombiePullGroupUtils.deletedNum;
        cleanZombiePullGroupUtils.deletedNum = i + 1;
        return i;
    }

    private CleanZombiePullGroupUtils() {
    }

    public static CleanZombiePullGroupUtils getInstance() {
        if (instance == null) {
            synchronized (CleanZombiePullGroupUtils.class) {
                if (instance == null) {
                    instance = new CleanZombiePullGroupUtils();
                }
            }
        }
        initVersion();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.lastName = "";
        this.maxPullGroupNum = 20;
        this.maxCreateGroupNum = 10;
        this.deletedNum = 0;
        this.jumpTime = 200;
        this.backTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.isPullEnd = false;
        this.isExistGroup = false;
        this.pullRecordNum = 0;
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.isCanDelete = operationParameterModel.isDeleteNoFriends();
        this.operationNum = 1;
        this.operationJumpNum = 1;
        this.operationFirstNum = 0;
        this.lastOperationNum = this.startPullIndex;
        this.jumpCheckedNum = 0;
        this.scrollBottomNum = 3;
        this.scrollRecordBottomNum = 1;
        this.isFromBack = false;
        this.isFromRemarkDeleteBack = false;
        this.isJumpedStart = true;
        this.isFilled = false;
        this.isWhile = true;
        this.isFirstChatroomContactUI = true;
        this.isFirstChatroomContactUI2 = true;
        this.isChatroomInfoUI = true;
        this.isLauncherUI = true;
        this.isChattingUI = true;
        this.isFirstFTSMainUI = true;
        this.isFirstContactInfoUI = true;
        this.isBackFTSMainUI = true;
        this.isSingleChatInfoUI = true;
        this.isFirstChattingUI = true;
        this.isCanExecuteRemarkDelete = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void checkZombieFan(AccessibilityEvent accessibilityEvent, OperationParameterModel operationParameterModel) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            this.startPullIndex = operationParameterModel.getStartIndex();
            if (accessibilityEvent.getEventType() == 32 && MyApplication.enforceable) {
                this.mMyManager.setMiddleViewListener(this);
                this.mMyManager.setEndViewListener(this);
                if (ContactActivity.LauncherUI.equals(accessibilityEvent.getClassName())) {
                    if (!isWXGroup() || !this.isLauncherUI || this.isCanExecuteRemarkDelete) {
                        boolean z = this.isCanExecuteRemarkDelete;
                        return;
                    }
                    LogUtils.log("WS_BABY.LauncherUI");
                    this.isLauncherUI = false;
                    sleep(this.jumpTime);
                    executeMain();
                } else if ("com.tencent.mm.ui.chatting.ChattingUI".equals(accessibilityEvent.getClassName())) {
                    if (this.isCanExecuteRemarkDelete) {
                        try {
                            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && this.isFirstChattingUI) {
                                this.isFirstChattingUI = false;
                                LogUtils.log("WS_BABY_ChattingUI.2");
                                sleep(this.jumpTime);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(right_more_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                    LogUtils.log("WS_BABY_ChattingUI.7.右上更多");
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (this.isChattingUI && MyApplication.enforceable) {
                        this.isChattingUI = false;
                        LogUtils.log("WS_BABY.ChattingUI");
                        sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        LogUtils.log("WS_BABY.right_more_id");
                        PerformClickUtils.findViewIdAndClick(this.autoService, right_more_id);
                        sleep(200);
                        this.isFromBack = false;
                        new PerformClickUtils2().check(this.autoService, list_head_img_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY.list_head_img_id");
                                CleanZombiePullGroupUtils.this.initChatroomInfoUI();
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY.list_head_img_id.NULL");
                            }
                        });
                    }
                } else if ("com.tencent.mm.ui.SingleChatInfoUI".equals(accessibilityEvent.getClassName())) {
                    try {
                        if (this.isSingleChatInfoUI) {
                            this.isSingleChatInfoUI = false;
                            LogUtils.log("WS_BABY_SingleChatInfoUI.2");
                            sleep(this.jumpTime);
                            AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow2 != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(single_contact_nick_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if ("com.tencent.mm.plugin.profile.ui.ContactInfoUI".equals(accessibilityEvent.getClassName())) {
                    LogUtils.log("WS_BABY.ContactInfoUI.1" + this.isFirstContactInfoUI);
                    try {
                        if (this.isFirstContactInfoUI) {
                            this.isFirstContactInfoUI = false;
                            new PerformClickUtils2().check(this.autoService, right_more_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (CleanZombiePullGroupUtils.this.isCanDelete) {
                                        CleanZombiePullGroupUtils.this.saveFriendInfo("");
                                    }
                                    PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.right_more_id);
                                    CleanZombiePullGroupUtils.this.sleep(CleanZombiePullGroupUtils.this.jumpTime);
                                    if (CleanZombiePullGroupUtils.this.isCanDelete) {
                                        AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow != null) {
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("删除");
                                            if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0) {
                                                AccessibilityNodeInfo rootInActiveWindow2 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow2 != null) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.more_recycle_view_id);
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                                        PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                                        CleanZombiePullGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                                        AccessibilityNodeInfo rootInActiveWindow3 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                                        if (rootInActiveWindow3 != null) {
                                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow3.findAccessibilityNodeInfosByText("删除");
                                                            if (findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0) {
                                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByText2.get(0));
                                                            }
                                                        } else {
                                                            return;
                                                        }
                                                    }
                                                } else {
                                                    return;
                                                }
                                            } else {
                                                PerformClickUtils.performClick(findAccessibilityNodeInfosByText.get(0));
                                            }
                                            CleanZombiePullGroupUtils.this.sleep(CleanZombiePullGroupUtils.this.jumpTime);
                                            AccessibilityNodeInfo rootInActiveWindow4 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow4 != null) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow4.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.dialog_ok_id);
                                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                                    findAccessibilityNodeInfosByViewId2.get(0).performAction(16);
                                                }
                                                CleanZombiePullGroupUtils.access$508(CleanZombiePullGroupUtils.this);
                                                MyWindowManager myWindowManager = CleanZombiePullGroupUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已删除了 " + CleanZombiePullGroupUtils.this.deletedNum + " 个，待删除 " + CleanZombiePullGroupUtils.this.nilFriends.size() + " 个");
                                                CleanZombiePullGroupUtils.this.backMainPage();
                                                return;
                                            }
                                            return;
                                        }
                                        return;
                                    }
                                    AccessibilityNodeInfo rootInActiveWindow5 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow5 != null) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText3 = rootInActiveWindow5.findAccessibilityNodeInfosByText("设置备注和标签");
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText4 = rootInActiveWindow5.findAccessibilityNodeInfosByText("设置备注及标签");
                                        if (findAccessibilityNodeInfosByText3 != null && findAccessibilityNodeInfosByText3.size() > 0) {
                                            LogUtils.log("WS_BABY.ContactInfoUI.10.设置备注和标签");
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText3.get(0));
                                        } else if (findAccessibilityNodeInfosByText4 != null && findAccessibilityNodeInfosByText4.size() > 0) {
                                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText4.get(0));
                                        }
                                        try {
                                            CleanZombiePullGroupUtils.this.sleep(CleanZombiePullGroupUtils.this.jumpTime);
                                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                                            AccessibilityNodeInfo rootInActiveWindow6 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow6 != null) {
                                                rootInActiveWindow6.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.remark_edit_id).get(0).performAction(16);
                                                CleanZombiePullGroupUtils.this.sleep(CleanZombiePullGroupUtils.this.jumpTime);
                                                PerformClickUtils.inputPrefixText(CleanZombiePullGroupUtils.this.autoService, "A000非好友_");
                                                CleanZombiePullGroupUtils.this.sleep(CleanZombiePullGroupUtils.this.jumpTime);
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow6.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_confirm_id);
                                                if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0) {
                                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                                }
                                                CleanZombiePullGroupUtils.access$508(CleanZombiePullGroupUtils.this);
                                                MyWindowManager myWindowManager2 = CleanZombiePullGroupUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager2, "已备注了 " + CleanZombiePullGroupUtils.this.deletedNum + " 个好友");
                                                CleanZombiePullGroupUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                CleanZombiePullGroupUtils.this.backMainPage();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else if (!"com.tencent.mm.chatroom.ui.SelectDelMemberUI".equals(accessibilityEvent.getClassName())) {
                    "com.tencent.mm.chatroom.ui.ChatroomInfoUI".equals(accessibilityEvent.getClassName());
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void initChatroomInfoUI() {
        if (this.isChatroomInfoUI && !this.isCanExecuteRemarkDelete && MyApplication.enforceable) {
            this.isChatroomInfoUI = false;
            if (this.isFirstChatroomContactUI2) {
                this.isFirstChatroomContactUI2 = false;
                if (this.isExistGroup) {
                    sleep(SocializeConstants.CANCLE_RESULTCODE);
                    AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId("android:id/text1");
                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                            executedAddMemberBtn();
                            return;
                        }
                        String str = findAccessibilityNodeInfosByViewId.get(0).getText() + "";
                        if (str.contains("(") && str.contains(")")) {
                            if (Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")"))) > 1) {
                                executeDeleteMemberBtn();
                            } else {
                                executedAddMemberBtn();
                            }
                        }
                    } else {
                        executedAddMemberBtn();
                    }
                } else {
                    LogUtils.log("WS_BABY.isFirstChatroomContactUI2");
                    sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    PerformClickUtils.findViewIdAndClick(this.autoService, dialog_ok_id);
                    PerformClickUtils.findTextAndClick(this.autoService, "群聊名称");
                    sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    PerformClickUtils.inputText(this.autoService, "微商宝贝检测僵尸粉群");
                    sleep(100);
                    PerformClickUtils.findViewIdAndClick(this.autoService, list_select_confirm_id);
                    sleep(SocializeConstants.CANCLE_RESULTCODE);
                    AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow2 != null) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId("android:id/text1");
                        if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                            executedAddMemberBtn();
                            return;
                        }
                        String str2 = findAccessibilityNodeInfosByViewId2.get(0).getText() + "";
                        if (str2.contains("(") && str2.contains(")")) {
                            if (Integer.parseInt(str2.substring(str2.indexOf("(") + 1, str2.indexOf(")"))) > 1) {
                                executeDeleteMemberBtn();
                            } else {
                                executedAddMemberBtn();
                            }
                        }
                    } else {
                        executedAddMemberBtn();
                    }
                }
            } else if (this.isFromBack) {
                this.isFromBack = false;
                LogUtils.log("WS_BABY.ChatroomInfoUI111111");
                new PerformClickUtils2().check(this.autoService, dialog_ok_id, 100, (int) SocializeConstants.CANCLE_RESULTCODE, 4, true, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void executeIndex(int i) {
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        CleanZombiePullGroupUtils.this.splitDialogText(PerformClickUtils.findViewIdAndGetText(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.dialog_text));
                        CleanZombiePullGroupUtils.this.sleep(200);
                        PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.dialog_ok_id);
                        CleanZombiePullGroupUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end2");
                        AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId("android:id/text1");
                            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                                return;
                            }
                            String str = findAccessibilityNodeInfosByViewId.get(0).getText() + "";
                            if (str.contains("(") && str.contains(")")) {
                                if (Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")"))) > 1) {
                                    CleanZombiePullGroupUtils.this.executeDeleteMemberBtn();
                                } else {
                                    CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                                }
                            }
                        } else {
                            CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.ChatroomInfoUI1111111111111111");
                    }

                    public void end() {
                        if (!CleanZombiePullGroupUtils.this.isCanExecuteRemarkDelete) {
                            AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId("android:id/text1");
                                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                    CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                                } else {
                                    String str = findAccessibilityNodeInfosByViewId.get(0).getText() + "";
                                    if (str.contains("(") && str.contains(")")) {
                                        if (Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")"))) > 1) {
                                            CleanZombiePullGroupUtils.this.executeDeleteMemberBtn();
                                        } else {
                                            CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                                        }
                                    }
                                }
                            } else {
                                CleanZombiePullGroupUtils.this.executedAddMemberBtn();
                            }
                            LogUtils.log("WS_BABY.ChatroomInfoUI1111111111111111111111111");
                        }
                    }
                });
            } else if (this.isPullEnd) {
                removeEndView(this.mMyManager);
            } else {
                LogUtils.log("WS_BABY.ChatroomInfoUI222222");
                sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                executedAddMemberBtn();
            }
        }
    }

    public void executedAddMemberBtn() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        if (!this.isFilled) {
            LogUtils.log("WS_BABY.executedAddMemberBtn");
            boolean z = true;
            while (z && MyApplication.enforceable) {
                AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_head_img_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    LogUtils.log("WS_BABY.ChatroomInfoUI_ADD_BTN");
                    int i = 0;
                    while (true) {
                        if (i >= findAccessibilityNodeInfosByViewId.size()) {
                            break;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                            if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员")) {
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                sleep(200);
                                excAddMemberTask();
                                z = false;
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
        removeEndView(this.mMyManager);
    }

    public void executeDeleteMemberBtn() {
        new PerformClickUtils2().checkNodeId(this.autoService, list_head_img_id, 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                boolean z = true;
                while (z && MyApplication.enforceable) {
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_head_img_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        LogUtils.log("WS_BABY.ChatroomInfoUI_DELETE_BTN");
                        int i2 = 0;
                        while (true) {
                            if (i2 >= findAccessibilityNodeInfosByViewId.size()) {
                                break;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                            if ((accessibilityNodeInfo.getContentDescription() + "").equals("删除成员")) {
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                CleanZombiePullGroupUtils.this.sleep(200);
                                CleanZombiePullGroupUtils.this.exeDeleteMemberTask();
                                z = false;
                                break;
                            }
                            i2++;
                        }
                    }
                }
            }
        });
    }

    public void exeDeleteMemberTask() {
        try {
            LogUtils.log("WS_BABY_DELETE_MEMBER");
            updateBottomText(this.mMyManager, "正在执行好友退群操作");
            new PerformClickUtils2().check(this.autoService, list_select_delete_name_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_FIND" + i);
                    String findViewIdAndGetText = PerformClickUtils.findViewIdAndGetText(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.delete_title_id);
                    int parseInt = (!findViewIdAndGetText.contains("(") || !findViewIdAndGetText.contains(")")) ? 0 : Integer.parseInt(findViewIdAndGetText.substring(findViewIdAndGetText.indexOf("(") + 1, findViewIdAndGetText.indexOf(")"))) - 1;
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null) {
                        boolean z = true;
                        boolean z2 = false;
                        int i2 = 0;
                        while (true) {
                            if (z && MyApplication.enforceable) {
                                if (rootInActiveWindow == null) {
                                    LogUtils.log("WS_BABY_FIND2");
                                    break;
                                }
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_delete_checkbox);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_delete_name_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                    int i3 = i2;
                                    int i4 = 0;
                                    while (true) {
                                        if (i4 >= findAccessibilityNodeInfosByViewId2.size()) {
                                            i2 = i3;
                                            break;
                                        }
                                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i4);
                                        if (accessibilityNodeInfo != null && !accessibilityNodeInfo.isChecked()) {
                                            if (i3 == parseInt) {
                                                LogUtils.log("WS_BABY_check1");
                                                i2 = i3;
                                                z = false;
                                                z2 = true;
                                                break;
                                            }
                                            LogUtils.log("WS_BABY_check0");
                                            i3++;
                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                        } else {
                                            LogUtils.log("WS_BABY_check");
                                        }
                                        i4++;
                                    }
                                }
                                if (!z2) {
                                    LogUtils.log("WS_BABY_check3");
                                    CleanZombiePullGroupUtils.this.sleep(100);
                                    AccessibilityNodeInfo rootInActiveWindow2 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow2 == null) {
                                        continue;
                                    } else {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_delete_id);
                                        if (findAccessibilityNodeInfosByViewId3 != null || findAccessibilityNodeInfosByViewId3.size() > 0) {
                                            findAccessibilityNodeInfosByViewId3.get(0).performAction(4096);
                                            CleanZombiePullGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        if (z2) {
                            LogUtils.log("WS_BABY_check4");
                            CleanZombiePullGroupUtils.this.sleep(100);
                            PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.list_select_confirm_id);
                            CleanZombiePullGroupUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.dialog_ok_id);
                            CleanZombiePullGroupUtils.this.sleep(200);
                            boolean unused = CleanZombiePullGroupUtils.this.isChatroomInfoUI = true;
                            boolean unused2 = CleanZombiePullGroupUtils.this.isFromBack = false;
                            new PerformClickUtils2().check(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.list_head_img_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    LogUtils.log("WS_BABY_check5");
                                    CleanZombiePullGroupUtils.this.initChatroomInfoUI();
                                }
                            });
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excAddMemberTask() {
        try {
            LogUtils.log("WS_BABY_excAddMemberTask");
            this.scrollBottomNum = 3;
            this.scrollRecordBottomNum = 1;
            this.isWhile = true;
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, 300, 300, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    int i2;
                    LogUtils.log("WS_BABY_excAddMemberTask1");
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_excAddMemberTask2");
                        while (true) {
                            i2 = 1;
                            if (CleanZombiePullGroupUtils.this.isWhile && MyApplication.enforceable) {
                                if (rootInActiveWindow == null) {
                                    LogUtils.log("WS_BABY_excAddMemberTask17");
                                    break;
                                }
                                LogUtils.log("WS_BABY_excAddMemberTask3");
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_checkbox);
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_name_id);
                                if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                                    LogUtils.log("WS_BABY_excAddMemberTask16");
                                } else {
                                    LogUtils.log("WS_BABY_excAddMemberTask4");
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= findAccessibilityNodeInfosByViewId2.size() - 1) {
                                            break;
                                        }
                                        String charSequence = findAccessibilityNodeInfosByViewId2.get(i3).getText().toString();
                                        if (CleanZombiePullGroupUtils.this.operationFirstNum == CleanZombiePullGroupUtils.this.maxPullGroupNum) {
                                            LogUtils.log("WS_BABY_excAddMemberTask5");
                                            break;
                                        }
                                        LogUtils.log("WS_BABY_excAddMemberTask6#operationFirstNum" + CleanZombiePullGroupUtils.this.operationFirstNum + "#maxNum" + CleanZombiePullGroupUtils.this.maxPullGroupNum);
                                        LogUtils.log("WS_BABY_excAddMemberTask6#startPullIndex" + CleanZombiePullGroupUtils.this.startPullIndex + "#isJumpedStart" + CleanZombiePullGroupUtils.this.isJumpedStart);
                                        if (CleanZombiePullGroupUtils.this.startPullIndex <= 1 || !CleanZombiePullGroupUtils.this.isJumpedStart) {
                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i3);
                                            if (accessibilityNodeInfo == null || accessibilityNodeInfo.isChecked() || (charSequence != null && !"".equals(charSequence) && charSequence.startsWith("A000非好友"))) {
                                                LogUtils.log("WS_BABY_excAddMemberTask9");
                                                CleanZombiePullGroupUtils.access$2708(CleanZombiePullGroupUtils.this);
                                                if (CleanZombiePullGroupUtils.this.isCanDelete) {
                                                    CleanZombiePullGroupUtils.this.nilFriends.add(charSequence);
                                                }
                                                MyWindowManager myWindowManager = CleanZombiePullGroupUtils.this.mMyManager;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("从 ");
                                                sb.append(CleanZombiePullGroupUtils.this.lastOperationNum == 0 ? 1 : CleanZombiePullGroupUtils.this.lastOperationNum);
                                                sb.append(" 个开始检测僵尸粉\n已跳过【 ");
                                                sb.append(charSequence);
                                                sb.append(" 】");
                                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                                            } else {
                                                LogUtils.log("WS_BABY_excAddMemberTask10###operationNum" + CleanZombiePullGroupUtils.this.operationNum + "#lastOperationNum" + CleanZombiePullGroupUtils.this.lastOperationNum);
                                                if (CleanZombiePullGroupUtils.this.operationNum > CleanZombiePullGroupUtils.this.lastOperationNum) {
                                                    LogUtils.log("WS_BABY_excAddMemberTask12");
                                                    CleanZombiePullGroupUtils.access$2908(CleanZombiePullGroupUtils.this);
                                                    CleanZombiePullGroupUtils.access$2108(CleanZombiePullGroupUtils.this);
                                                    CleanZombiePullGroupUtils.this.sleep(20);
                                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                                    MyWindowManager myWindowManager2 = CleanZombiePullGroupUtils.this.mMyManager;
                                                    BaseServiceUtils.updateBottomText(myWindowManager2, "正在执行分批检测僵尸粉\n已勾选 " + CleanZombiePullGroupUtils.this.operationFirstNum + " 个");
                                                } else {
                                                    LogUtils.log("WS_BABY_excAddMemberTask13");
                                                    CleanZombiePullGroupUtils.access$2808(CleanZombiePullGroupUtils.this);
                                                    MyWindowManager myWindowManager3 = CleanZombiePullGroupUtils.this.mMyManager;
                                                    StringBuilder sb2 = new StringBuilder();
                                                    sb2.append("从 ");
                                                    sb2.append(CleanZombiePullGroupUtils.this.lastOperationNum == 0 ? 1 : CleanZombiePullGroupUtils.this.lastOperationNum);
                                                    sb2.append(" 个开始检测僵尸粉\n已跳过 ");
                                                    sb2.append(CleanZombiePullGroupUtils.this.operationNum);
                                                    sb2.append(" 个");
                                                    BaseServiceUtils.updateBottomText(myWindowManager3, sb2.toString());
                                                }
                                            }
                                        } else {
                                            LogUtils.log("WS_BABY_excAddMemberTask6666");
                                            if (CleanZombiePullGroupUtils.this.operationJumpNum < CleanZombiePullGroupUtils.this.startPullIndex - 1) {
                                                CleanZombiePullGroupUtils.access$2508(CleanZombiePullGroupUtils.this);
                                                LogUtils.log("WS_BABY_excAddMemberTask7");
                                                MyWindowManager myWindowManager4 = CleanZombiePullGroupUtils.this.mMyManager;
                                                StringBuilder sb3 = new StringBuilder();
                                                sb3.append("从 ");
                                                sb3.append(CleanZombiePullGroupUtils.this.lastOperationNum == 0 ? 1 : CleanZombiePullGroupUtils.this.lastOperationNum);
                                                sb3.append(" 个开始检测僵尸粉\n已跳过 ");
                                                sb3.append(CleanZombiePullGroupUtils.this.operationJumpNum);
                                                sb3.append(" 个\n");
                                                BaseServiceUtils.updateBottomText(myWindowManager4, sb3.toString());
                                            } else {
                                                boolean unused = CleanZombiePullGroupUtils.this.isJumpedStart = false;
                                                LogUtils.log("WS_BABY_excAddMemberTask8");
                                            }
                                        }
                                        i3++;
                                    }
                                    if (CleanZombiePullGroupUtils.this.operationFirstNum == CleanZombiePullGroupUtils.this.maxPullGroupNum) {
                                        LogUtils.log("WS_BABYS_lastOperationNum:" + CleanZombiePullGroupUtils.this.lastOperationNum + "_operationFirstNum" + CleanZombiePullGroupUtils.this.operationFirstNum + "_startPullIndex" + CleanZombiePullGroupUtils.this.startPullIndex + "_jumpCheckedNum" + CleanZombiePullGroupUtils.this.jumpCheckedNum + "(count)" + CleanZombiePullGroupUtils.this.lastOperationNum);
                                        int unused2 = CleanZombiePullGroupUtils.this.lastOperationNum = CleanZombiePullGroupUtils.this.startPullIndex + CleanZombiePullGroupUtils.this.pullRecordNum + CleanZombiePullGroupUtils.this.jumpCheckedNum;
                                        int unused3 = CleanZombiePullGroupUtils.this.jumpCheckedNum = 0;
                                        int unused4 = CleanZombiePullGroupUtils.this.operationNum = 1;
                                        int unused5 = CleanZombiePullGroupUtils.this.operationFirstNum = 0;
                                        break;
                                    }
                                    LogUtils.log("WS_BABY_excAddMemberTask15");
                                    CleanZombiePullGroupUtils.this.sleep(300);
                                    AccessibilityNodeInfo rootInActiveWindow2 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow2 != null) {
                                        LogUtils.log("WS_BABY_excAddMemberTask1515");
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_id);
                                        if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0) {
                                            findAccessibilityNodeInfosByViewId3.get(0).performAction(4096);
                                            CleanZombiePullGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                            AccessibilityNodeInfo rootInActiveWindow3 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow3 != null) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow3.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_name_id);
                                                if (findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0) {
                                                    String charSequence2 = findAccessibilityNodeInfosByViewId4.get(findAccessibilityNodeInfosByViewId4.size() - 1).getText().toString();
                                                    if (!charSequence2.equals(CleanZombiePullGroupUtils.this.lastName)) {
                                                        String unused6 = CleanZombiePullGroupUtils.this.lastName = charSequence2;
                                                    } else if (CleanZombiePullGroupUtils.this.scrollRecordBottomNum == CleanZombiePullGroupUtils.this.scrollBottomNum) {
                                                        boolean unused7 = CleanZombiePullGroupUtils.this.isFilled = true;
                                                        boolean unused8 = CleanZombiePullGroupUtils.this.isWhile = false;
                                                        int unused9 = CleanZombiePullGroupUtils.this.operationNum = 1;
                                                        int unused10 = CleanZombiePullGroupUtils.this.operationFirstNum = 0;
                                                        break;
                                                    } else {
                                                        CleanZombiePullGroupUtils.access$3208(CleanZombiePullGroupUtils.this);
                                                        String unused11 = CleanZombiePullGroupUtils.this.lastName = charSequence2;
                                                    }
                                                }
                                            } else {
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        LogUtils.log("WS_BABY_excAddMemberTask18");
                        CleanZombiePullGroupUtils.this.sleep(200);
                        boolean unused12 = CleanZombiePullGroupUtils.this.isChatroomInfoUI = true;
                        boolean unused13 = CleanZombiePullGroupUtils.this.isFromBack = true;
                        SPUtils.put(MyApplication.getConText(), "zombie_pull_group_num", Integer.valueOf(CleanZombiePullGroupUtils.this.startPullIndex + CleanZombiePullGroupUtils.this.pullRecordNum + CleanZombiePullGroupUtils.this.jumpCheckedNum));
                        Context conText = MyApplication.getConText();
                        SPUtils.put(conText, "zombie_pull_group_text", "检测" + CleanZombiePullGroupUtils.this.startPullIndex + CleanZombiePullGroupUtils.this.pullRecordNum + CleanZombiePullGroupUtils.this.jumpCheckedNum + "个好友，非好友" + CleanZombiePullGroupUtils.this.nilFriends + "人");
                        MyWindowManager myWindowManager5 = CleanZombiePullGroupUtils.this.mMyManager;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("微商宝贝正在检测僵尸粉\n已拉取 ");
                        if (CleanZombiePullGroupUtils.this.lastOperationNum != 0) {
                            i2 = CleanZombiePullGroupUtils.this.lastOperationNum;
                        }
                        sb4.append(i2);
                        sb4.append(" 个好友");
                        BaseServiceUtils.updateBottomText(myWindowManager5, sb4.toString());
                        PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.list_select_confirm_id);
                        CleanZombiePullGroupUtils.this.sleep(100);
                        CleanZombiePullGroupUtils.this.initChatroomInfoUI();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excCreateGroupTask() {
        try {
            LogUtils.log("WS_BABY_CREATE_GROUP");
            updateBottomText(this.mMyManager, "微商宝贝正在创建检粉群组");
            new PerformClickUtils2().check(this.autoService, list_select_name_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                        boolean z = true;
                        int i2 = 0;
                        while (z && MyApplication.enforceable && rootInActiveWindow != null) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_checkbox);
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_name_id);
                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                                int i3 = i2;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= findAccessibilityNodeInfosByViewId2.size() - 1) {
                                        break;
                                    }
                                    String str = findAccessibilityNodeInfosByViewId2.get(i4).getText() + "";
                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i4);
                                    if (accessibilityNodeInfo != null && !accessibilityNodeInfo.isChecked()) {
                                        if (str == null || "".equals(str) || !str.startsWith("A000非好友")) {
                                            if (i3 >= CleanZombiePullGroupUtils.this.maxCreateGroupNum) {
                                                z = false;
                                                break;
                                            } else {
                                                i3++;
                                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                            }
                                        } else if (CleanZombiePullGroupUtils.this.isCanDelete) {
                                            CleanZombiePullGroupUtils.this.nilFriends.add(str);
                                        }
                                    }
                                    i4++;
                                }
                                if (z) {
                                    CleanZombiePullGroupUtils.this.sleep(100);
                                    AccessibilityNodeInfo rootInActiveWindow2 = CleanZombiePullGroupUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow2 != null) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(CleanZombiePullGroupUtils.list_select_id);
                                        if (findAccessibilityNodeInfosByViewId3 != null || findAccessibilityNodeInfosByViewId3.size() > 0) {
                                            findAccessibilityNodeInfosByViewId3.get(0).performAction(4096);
                                            CleanZombiePullGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        } else {
                                            return;
                                        }
                                    }
                                }
                                i2 = i3;
                            }
                        }
                        CleanZombiePullGroupUtils.this.sleep(200);
                        boolean unused = CleanZombiePullGroupUtils.this.isChatroomInfoUI = true;
                        boolean unused2 = CleanZombiePullGroupUtils.this.isFromBack = true;
                        BaseServiceUtils.updateBottomText(CleanZombiePullGroupUtils.this.mMyManager, "微商宝贝正在创建检粉群组");
                        PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.list_select_confirm_id);
                        CleanZombiePullGroupUtils.this.sleep(200);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (this.isLauncherUI && MyApplication.enforceable) {
                this.isLauncherUI = false;
                LogUtils.log("WS_BABY.LauncherUI");
                sleep(100);
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep(100);
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                if (this.isFirstChatroomContactUI) {
                    this.isFirstChatroomContactUI = false;
                    LogUtils.log("WS_BABY_isFirstChatroomContactUI");
                    AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        if ("6.7.3".equals(wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(this.autoService, "搜索");
                            sleep(this.jumpTime);
                            initFTSMainUI0();
                            return;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(contact_nav_search_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null) {
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            sleep(this.jumpTime);
                            initFTSMainUI0();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backMainPage() {
        new PerformClickUtils2().checkIsMainPage(this.autoService, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, SocializeConstants.CANCLE_RESULTCODE, 10, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                boolean unused = CleanZombiePullGroupUtils.this.isFromRemarkDeleteBack = false;
                boolean unused2 = CleanZombiePullGroupUtils.this.isFirstFTSMainUI = true;
                CleanZombiePullGroupUtils.this.executeRemarkDelete();
            }
        });
    }

    public void executeRemarkDelete() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            MyApplication.enforceable = true;
            if (this.isCanDelete) {
                showBottomView(this.mMyManager, "微商宝贝正在执行删除非好友");
            } else {
                showBottomView(this.mMyManager, "微商宝贝正在执行备注非好友");
            }
            sleep(100);
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            if (this.nilFriends != null) {
                if (this.nilFriends.size() > 0) {
                    AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        if ("6.7.3".equals(wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(this.autoService, "搜索");
                            sleep(this.jumpTime);
                            initFTSMainUI();
                            return;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(contact_nav_search_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null) {
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            sleep(this.jumpTime);
                            initFTSMainUI();
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            removeEndView(this.mMyManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFTSMainUI0() {
        try {
            sleep(SocializeConstants.CANCLE_RESULTCODE);
            PerformClickUtils.inputText(this.autoService, "微商宝贝检测僵尸粉群");
            sleep(1500);
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_select_name_id);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                    this.isExistGroup = false;
                    sleep(this.backTime);
                    PerformClickUtils.performBack(this.autoService);
                    sleep(this.backTime);
                    PerformClickUtils.findTextAndClick(this.autoService, "群聊");
                    new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, 100, 300, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.right_more_id);
                            CleanZombiePullGroupUtils.this.excCreateGroupTask();
                        }
                    });
                } else if (PerformClickUtils.findNodeIsExistByText(this.autoService, "群聊")) {
                    this.isExistGroup = true;
                    LogUtils.log("WS_BABY.微商宝贝检测僵尸粉群");
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                } else {
                    this.isExistGroup = false;
                    sleep(this.backTime);
                    PerformClickUtils.performBack(this.autoService);
                    sleep(this.backTime);
                    PerformClickUtils.findTextAndClick(this.autoService, "群聊");
                    new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, 100, 300, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(CleanZombiePullGroupUtils.this.autoService, CleanZombiePullGroupUtils.right_more_id);
                            CleanZombiePullGroupUtils.this.excCreateGroupTask();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFTSMainUI() {
        try {
            LogUtils.log("WS_BABY.FTSMainUI");
            if (this.isFromRemarkDeleteBack) {
                if (this.isBackFTSMainUI) {
                    this.isBackFTSMainUI = false;
                    this.isFirstChattingUI = true;
                    this.isSingleChatInfoUI = true;
                    LogUtils.log("WS_BABY.FTSMainUI_BACK");
                    sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    PerformClickUtils.performBack(this.autoService);
                }
            } else if (this.isFirstFTSMainUI) {
                LogUtils.log("WS_BABY.isFirstFTSMainUI");
                this.isFirstFTSMainUI = false;
                this.isFirstChattingUI = true;
                this.isSingleChatInfoUI = true;
                this.isFirstContactInfoUI = true;
                LogUtils.log("WS_BABY.FTSMainUI.into");
                searchNickName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchNickName() {
        try {
            if (this.nilFriends != null && this.nilFriends.size() > 0) {
                sleep(SocializeConstants.CANCLE_RESULTCODE);
                String str = "";
                if (this.nilFriends != null && !"".equals(this.nilFriends)) {
                    Iterator it = this.nilFriends.iterator();
                    if (it.hasNext()) {
                        str = (String) it.next();
                    }
                    PerformClickUtils.inputText(this.autoService, str);
                }
                sleep(1500);
                AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_select_name_id);
                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                        this.nilFriends.remove(this.nilFriends.iterator().next());
                        if (this.nilFriends == null || this.nilFriends.size() <= 0) {
                            removeEndView(this.mMyManager);
                            return;
                        }
                        this.isFirstFTSMainUI = true;
                        sleep(this.backTime);
                        PerformClickUtils.performBack(this.autoService);
                        backMainPage();
                        return;
                    }
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(this.autoService, "联系人");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(this.autoService, "最常使用");
                    if (!findNodeIsExistByText) {
                        if (!findNodeIsExistByText2) {
                            this.nilFriends.remove(this.nilFriends.iterator().next());
                            if (this.nilFriends == null || this.nilFriends.size() <= 0) {
                                removeEndView(this.mMyManager);
                                return;
                            }
                            this.isFirstFTSMainUI = true;
                            sleep(this.backTime);
                            PerformClickUtils.findBackAndClick(this.autoService);
                            backMainPage();
                            return;
                        }
                    }
                    this.nilFriends.remove(this.nilFriends.iterator().next());
                    LogUtils.log("WS_BABY.search_into");
                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        if (this.isCanExecuteRemarkDelete) {
            MyApplication.enforceable = true;
            return;
        }
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startPullIndex + " 个好友拉群检测僵尸粉\n请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanZombiePullGroupUtils.this.executeMain();
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void splitDialogText(String str) {
        if (str != null && !"".equals(str) && str.contains("未把你添加到通讯录")) {
            if (str.contains("拒绝加入群聊。")) {
                String str2 = str.split("拒绝加入群聊。")[1];
                if (str2.contains("、")) {
                    String[] split = str2.split("、");
                    for (String str3 : split) {
                        if (str3.contains("未把你添加到通讯录")) {
                            str3 = str3.substring(0, str3.indexOf("未把你添加到通讯录"));
                        }
                        this.nilFriends.add(str3);
                        this.oriFriends.add(str3);
                    }
                    return;
                }
                if (str2.contains("未把你添加到通讯录")) {
                    str2 = str2.substring(0, str2.indexOf("未把你添加到通讯录"));
                }
                this.nilFriends.add(str2);
                this.oriFriends.add(str2);
            } else if (str.contains("、")) {
                String[] split2 = str.split("、");
                for (String str4 : split2) {
                    if (str4.contains("未把你添加到通讯录")) {
                        str4 = str4.substring(0, str4.indexOf("未把你添加到通讯录"));
                    }
                    this.nilFriends.add(str4);
                    this.oriFriends.add(str4);
                }
            } else {
                if (str.contains("未把你添加到通讯录")) {
                    str.substring(0, str.indexOf("未把你添加到通讯录"));
                }
                this.nilFriends.add(str);
                this.oriFriends.add(str);
            }
        }
    }

    public void middleViewShow() {
        if (this.isCanExecuteRemarkDelete) {
            this.isCanExecuteRemarkDelete = false;
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "拉群检测僵尸粉", "本次共检测了" + this.lastOperationNum + "个好友\n检测非好友【" + this.oriFriends.size() + "】人");
            return;
        }
        this.isCanExecuteRemarkDelete = true;
        MyWindowManager myWindowManager2 = this.mMyManager;
        updatePullGroupMiddleText(myWindowManager2, "拉群检测僵尸粉", "本次共检测了" + this.lastOperationNum + "个好友\n检测非好友【" + this.nilFriends.size() + "】人,非好友清单：\n" + this.nilFriends.toString(), "放弃", new FloatMiddleView.OnLeftBtnListener() {
            public void leftListener() {
                BaseServiceUtils.removeMiddleView(CleanZombiePullGroupUtils.this.mMyManager);
            }
        }, this.isCanDelete ? "自动删除" : "自动备注", new FloatMiddleView.OnRightBtnListener() {
            public void rightListener() {
                BaseServiceUtils.removeMiddleView(CleanZombiePullGroupUtils.this.mMyManager);
                BaseServiceUtils.showEndView(CleanZombiePullGroupUtils.this.mMyManager);
                CleanZombiePullGroupUtils.this.backMainPage();
            }
        });
    }

    public void endViewDismiss() {
        if (this.isCanExecuteRemarkDelete) {
            MyApplication.enforceable = false;
        } else {
            this.mMyManager.stopAccessibilityService();
        }
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public static void initVersion() {
        if ("7.0.5".equals(wxVersionName)) {
            int i = wxVersionCode;
        } else if ("7.0.3".equals(wxVersionName)) {
            right_more_id = "com.tencent.mm:id/jy";
            list_head_img_id = "com.tencent.mm:id/e0c";
            list_select_id = "com.tencent.mm:id/i9";
            list_select_name_id = "com.tencent.mm:id/q0";
            list_select_checkbox = "com.tencent.mm:id/a08";
            list_select_confirm_id = "com.tencent.mm:id/jx";
            dialog_ok_id = "com.tencent.mm:id/az_";
            dialog_text = "com.tencent.mm:id/d6y";
            list_select_delete_id = "com.tencent.mm:id/e4j";
            list_select_delete_name_id = "com.tencent.mm:id/e4n";
            list_select_delete_checkbox = "com.tencent.mm:id/aus";
            delete_title_id = "android:id/text1";
            contact_nav_search_img_id = "com.tencent.mm:id/iq";
            contact_nav_search_viewgroup_id = "com.tencent.mm:id/jf";
            search_page_back = "com.tencent.mm:id/kf";
            more_recycle_view_id = "com.tencent.mm:id/d78";
            remark_edit_id = "com.tencent.mm:id/b4v";
        } else if ("7.0.0".equals(wxVersionName)) {
            right_more_id = "com.tencent.mm:id/jr";
            list_head_img_id = "com.tencent.mm:id/dwu";
            list_select_id = "com.tencent.mm:id/i2";
            list_select_name_id = "com.tencent.mm:id/pp";
            list_select_checkbox = "com.tencent.mm:id/zk";
            list_select_confirm_id = "com.tencent.mm:id/jq";
            dialog_ok_id = "com.tencent.mm:id/ayb";
            dialog_text = "com.tencent.mm:id/d3f";
            list_select_delete_id = "com.tencent.mm:id/e11";
            list_select_delete_name_id = "com.tencent.mm:id/e15";
            list_select_delete_checkbox = "com.tencent.mm:id/atu";
            delete_title_id = "android:id/text1";
            contact_nav_search_img_id = "com.tencent.mm:id/ij";
            contact_nav_search_viewgroup_id = "com.tencent.mm:id/j9";
            search_page_back = "com.tencent.mm:id/k9";
            more_recycle_view_id = "com.tencent.mm:id/d3p";
            remark_edit_id = "com.tencent.mm:id/b3v";
        } else if ("6.7.3".equals(wxVersionName)) {
            right_more_id = "com.tencent.mm:id/j1";
            list_head_img_id = "com.tencent.mm:id/dnm";
            list_select_id = "com.tencent.mm:id/hc";
            list_select_name_id = "com.tencent.mm:id/om";
            list_select_checkbox = "com.tencent.mm:id/x8";
            list_select_confirm_id = "com.tencent.mm:id/j0";
            dialog_ok_id = "com.tencent.mm:id/au_";
            dialog_text = "";
            list_select_delete_id = "";
            list_select_delete_name_id = "";
            list_select_delete_checkbox = "";
            delete_title_id = "android:id/text1";
            contact_nav_search_img_id = "";
            contact_nav_search_viewgroup_id = "com.tencent.mm:id/ij";
            search_page_back = "com.tencent.mm:id/jg";
            more_recycle_view_id = "com.tencent.mm:id/cvy";
            remark_edit_id = "com.tencent.mm:id/ays";
        }
    }
}
