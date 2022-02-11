package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class CleanHomeMsgUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanHomeMsgUtils instance;
    /* access modifiers changed from: private */
    public int deleteMode = 1;
    /* access modifiers changed from: private */
    public int executionNum = 0;
    /* access modifiers changed from: private */
    public List<String> groupList = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isFirstLauncherUI = true;
    /* access modifiers changed from: private */
    public boolean isNeedScroll = true;
    /* access modifiers changed from: private */
    public int jumpNum = 1;
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    private int maxNum = 0;
    /* access modifiers changed from: private */
    public List<String> recordList = new ArrayList();
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(CleanHomeMsgUtils cleanHomeMsgUtils) {
        int i = cleanHomeMsgUtils.startIndex;
        cleanHomeMsgUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(CleanHomeMsgUtils cleanHomeMsgUtils) {
        int i = cleanHomeMsgUtils.jumpNum;
        cleanHomeMsgUtils.jumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(CleanHomeMsgUtils cleanHomeMsgUtils) {
        int i = cleanHomeMsgUtils.executionNum;
        cleanHomeMsgUtils.executionNum = i + 1;
        return i;
    }

    private CleanHomeMsgUtils() {
    }

    public static CleanHomeMsgUtils getInstance() {
        instance = new CleanHomeMsgUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startInt = operationParameterModel.getStartIndex();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.deleteMode = operationParameterModel.getDeleteMode();
        this.jumpNum = 1;
        this.startIndex = 0;
        this.executionNum = 0;
        this.lastScrollNickName = "";
        this.recordList = new ArrayList();
        this.isNeedScroll = true;
        this.isFirstLauncherUI = true;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (operationParameterModel.getGroupNames() != null && !"".equals(operationParameterModel.getGroupNames())) {
            String groupNames = operationParameterModel.getGroupNames();
            if (groupNames.contains(";")) {
                String[] split = groupNames.split(";");
                for (String add : split) {
                    this.groupList.add(add);
                }
                return;
            }
            this.groupList.add(groupNames);
        }
    }

    public void executeMain() {
        if (this.isFirstLauncherUI && MyApplication.enforceable) {
            this.isFirstLauncherUI = false;
            try {
                if (this.executionNum >= this.maxNum) {
                    removeEndView(this.mMyManager);
                } else {
                    new PerformClickUtils2().checkNodeAllIds(this.autoService, home_msg_list_item_id, home_msg_list_name_id, 100, 100, 5, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
                            boolean z;
                            final List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanHomeMsgUtils.this.autoService, BaseServiceUtils.home_msg_list_item_id);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanHomeMsgUtils.this.autoService, BaseServiceUtils.home_msg_list_name_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                BaseServiceUtils.removeEndView(CleanHomeMsgUtils.this.mMyManager);
                                return;
                            }
                            boolean z2 = false;
                            if (CleanHomeMsgUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_@" + CleanHomeMsgUtils.this.startIndex);
                                if (CleanHomeMsgUtils.this.jumpNum >= CleanHomeMsgUtils.this.startInt) {
                                    String str = "";
                                    try {
                                        str = findViewIdList2.get(CleanHomeMsgUtils.this.startIndex).getText() + "";
                                    } catch (Exception unused) {
                                    }
                                    if (CleanHomeMsgUtils.this.deleteMode == 0) {
                                        try {
                                            AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CleanHomeMsgUtils.this.startIndex);
                                            List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList(accessibilityNodeInfo, BaseServiceUtils.read_remark_1_id);
                                            List<AccessibilityNodeInfo> findViewIdList4 = PerformClickUtils.findViewIdList(accessibilityNodeInfo, BaseServiceUtils.read_remark_2_id);
                                            if ((findViewIdList3 != null && findViewIdList3.size() > 0) || (findViewIdList4 != null && findViewIdList4.size() > 0)) {
                                                z = true;
                                                if (findViewIdList2 != null && findViewIdList2.size() > 0 && CleanHomeMsgUtils.this.recordList.contains(str)) {
                                                    z2 = true;
                                                }
                                                boolean contains = CleanHomeMsgUtils.this.groupList.contains(str);
                                                LogUtils.log("WS_BABY_@_" + str + "@=" + z + "@=" + contains);
                                                if (!z2 || z || contains) {
                                                    CleanHomeMsgUtils.access$008(CleanHomeMsgUtils.this);
                                                    boolean unused2 = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                    BaseServiceUtils.updateBottomText(CleanHomeMsgUtils.this.mMyManager, "已删除 " + CleanHomeMsgUtils.this.executionNum + " 条消息,已跳过：" + str);
                                                    CleanHomeMsgUtils.this.executeMain();
                                                    return;
                                                }
                                                AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(CleanHomeMsgUtils.this.startIndex);
                                                if (accessibilityNodeInfo2 != null && MyApplication.enforceable) {
                                                    PerformClickUtils.performLongClick(accessibilityNodeInfo2);
                                                    new PerformClickUtils2().checkString(CleanHomeMsgUtils.this.autoService, "删除该聊天", (BaseServiceUtils.executeSpeedSetting / 5) + 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            if (PerformClickUtils.findNodeIsExistByText(CleanHomeMsgUtils.this.autoService, "删除该聊天")) {
                                                                PerformClickUtils.findTextAndClick(CleanHomeMsgUtils.this.autoService, "删除该聊天");
                                                                new PerformClickUtils2().check(CleanHomeMsgUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        PerformClickUtils.findViewIdAndClick(CleanHomeMsgUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                                        CleanHomeMsgUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 5) + 600);
                                                                        CleanHomeMsgUtils.access$708(CleanHomeMsgUtils.this);
                                                                        boolean unused = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                                        MyWindowManager myWindowManager = CleanHomeMsgUtils.this.mMyManager;
                                                                        BaseServiceUtils.updateBottomText(myWindowManager, "已删除 " + CleanHomeMsgUtils.this.executionNum + " 条消息");
                                                                        if (CleanHomeMsgUtils.this.isNeedScroll) {
                                                                            CleanHomeMsgUtils.this.executeMain();
                                                                        } else if (CleanHomeMsgUtils.this.startIndex >= findViewIdList.size() - 1) {
                                                                            BaseServiceUtils.removeEndView(CleanHomeMsgUtils.this.mMyManager);
                                                                        } else {
                                                                            CleanHomeMsgUtils.this.executeMain();
                                                                        }
                                                                    }

                                                                    public void nuFind() {
                                                                        CleanHomeMsgUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 10) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                                        CleanHomeMsgUtils.access$708(CleanHomeMsgUtils.this);
                                                                        boolean unused = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                                        MyWindowManager myWindowManager = CleanHomeMsgUtils.this.mMyManager;
                                                                        BaseServiceUtils.updateBottomText(myWindowManager, "已删除 " + CleanHomeMsgUtils.this.executionNum + " 条消息");
                                                                        CleanHomeMsgUtils.this.executeMain();
                                                                    }
                                                                });
                                                                return;
                                                            }
                                                            CleanHomeMsgUtils.access$008(CleanHomeMsgUtils.this);
                                                            CleanHomeMsgUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 10) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                            CleanHomeMsgUtils.access$708(CleanHomeMsgUtils.this);
                                                            PerformClickUtils.performBack(CleanHomeMsgUtils.this.autoService);
                                                            boolean unused = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                            CleanHomeMsgUtils.this.executeMain();
                                                        }

                                                        public void nuFind() {
                                                            CleanHomeMsgUtils.access$008(CleanHomeMsgUtils.this);
                                                            CleanHomeMsgUtils.access$708(CleanHomeMsgUtils.this);
                                                            CleanHomeMsgUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 10) + 100);
                                                            boolean unused = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                            CleanHomeMsgUtils.this.executeMain();
                                                        }
                                                    });
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (Exception unused3) {
                                        }
                                    }
                                    z = false;
                                    z2 = true;
                                    boolean contains2 = CleanHomeMsgUtils.this.groupList.contains(str);
                                    LogUtils.log("WS_BABY_@_" + str + "@=" + z + "@=" + contains2);
                                    if (!z2) {
                                    }
                                    CleanHomeMsgUtils.access$008(CleanHomeMsgUtils.this);
                                    boolean unused4 = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                    BaseServiceUtils.updateBottomText(CleanHomeMsgUtils.this.mMyManager, "已删除 " + CleanHomeMsgUtils.this.executionNum + " 条消息,已跳过：" + str);
                                    CleanHomeMsgUtils.this.executeMain();
                                    return;
                                }
                                try {
                                    AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList2.get(CleanHomeMsgUtils.this.startIndex);
                                    if (!(accessibilityNodeInfo3 == null || accessibilityNodeInfo3.getText() == null)) {
                                        String str2 = accessibilityNodeInfo3.getText() + "";
                                        if (str2 != null) {
                                            CleanHomeMsgUtils.this.recordList.add(str2);
                                        }
                                    }
                                } catch (Exception unused5) {
                                }
                                LogUtils.log("WS_BABY_@@@@@@@@@+startIndex= " + CleanHomeMsgUtils.this.startIndex + "@jumpNum= " + CleanHomeMsgUtils.this.jumpNum);
                                CleanHomeMsgUtils.access$008(CleanHomeMsgUtils.this);
                                CleanHomeMsgUtils.access$108(CleanHomeMsgUtils.this);
                                BaseServiceUtils.updateBottomText(CleanHomeMsgUtils.this.mMyManager, "已跳过 " + CleanHomeMsgUtils.this.jumpNum + " 条消息");
                                boolean unused6 = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                CleanHomeMsgUtils.this.executeMain();
                            } else if (CleanHomeMsgUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_@@@@@@@@@scroll=");
                                if (!CleanHomeMsgUtils.this.isNeedScroll || !MyApplication.enforceable) {
                                    BaseServiceUtils.removeEndView(CleanHomeMsgUtils.this.mMyManager);
                                    return;
                                }
                                LogUtils.log("WS_BABY_@@@@@@@@@7");
                                AccessibilityNodeInfo rootInActiveWindow = CleanHomeMsgUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_msg_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                    PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                    LogUtils.log("WS_BABY_@@@@@@@@@8");
                                    CleanHomeMsgUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                    AccessibilityNodeInfo rootInActiveWindow2 = CleanHomeMsgUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_msg_list_name_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_@@@@@@@@@9");
                                        try {
                                            String charSequence = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1).getText().toString();
                                            if (charSequence == null || !CleanHomeMsgUtils.this.lastScrollNickName.equals(charSequence)) {
                                                LogUtils.log("WS_BABY_@@@@@@@@@10");
                                                String unused7 = CleanHomeMsgUtils.this.lastScrollNickName = charSequence;
                                                int unused8 = CleanHomeMsgUtils.this.startIndex = 1;
                                                boolean unused9 = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                                CleanHomeMsgUtils.this.executeMain();
                                                return;
                                            }
                                            LogUtils.log("WS_BABY_@@@@@@@@@11" + CleanHomeMsgUtils.this.lastScrollNickName + "#" + charSequence);
                                            BaseServiceUtils.removeEndView(CleanHomeMsgUtils.this.mMyManager);
                                        } catch (Exception unused10) {
                                            LogUtils.log("WS_BABY_@@@@@@@@@12");
                                            int unused11 = CleanHomeMsgUtils.this.startIndex = 1;
                                            boolean unused12 = CleanHomeMsgUtils.this.isFirstLauncherUI = true;
                                            CleanHomeMsgUtils.this.executeMain();
                                        }
                                    }
                                }
                            }
                        }

                        public void nuFind() {
                            BaseServiceUtils.removeEndView(CleanHomeMsgUtils.this.mMyManager);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "清理消息", "已清理了" + this.executionNum + "条消息");
    }

    public void endViewShow() {
        this.isFirstLauncherUI = true;
        showBottomView(this.mMyManager, "正在自动清理消息，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    PerformClickUtils.findTextAndClick(CleanHomeMsgUtils.this.autoService, "微信");
                    CleanHomeMsgUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(CleanHomeMsgUtils.this.autoService, "微信");
                    CleanHomeMsgUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(CleanHomeMsgUtils.this.autoService, "微信");
                    CleanHomeMsgUtils.this.sleep(100);
                    CleanHomeMsgUtils.this.executeMain();
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
