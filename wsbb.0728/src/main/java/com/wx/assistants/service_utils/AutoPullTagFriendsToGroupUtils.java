package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoPullTagFriendsToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullTagFriendsToGroupUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public String currentTagName = "";
    /* access modifiers changed from: private */
    public boolean isExist = false;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public int lastOperationNum = 0;
    /* access modifiers changed from: private */
    public int maxNum = 40;
    /* access modifiers changed from: private */
    public String middleStr = "";
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String nodeText;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = 40;
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int scrollSpeed = 150;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags = new LinkedHashSet<>();

    private AutoPullTagFriendsToGroupUtils() {
    }

    static /* synthetic */ int access$1208(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.pullRecordNum;
        autoPullTagFriendsToGroupUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1308(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.scrollNum;
        autoPullTagFriendsToGroupUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.startIndex;
        autoPullTagFriendsToGroupUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.currentSelectNum;
        autoPullTagFriendsToGroupUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.operationFirstNum;
        autoPullTagFriendsToGroupUtils.operationFirstNum = i + 1;
        return i;
    }

    public static AutoPullTagFriendsToGroupUtils getInstance() {
        instance = new AutoPullTagFriendsToGroupUtils();
        return instance;
    }

    public void backChatRoomInfoUI() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加联系人", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().check(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + 100, (int) SocializeConstants.CANCLE_RESULTCODE, 4, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void end() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                        new PerformClickUtils2().checkLoad(AutoPullTagFriendsToGroupUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                AutoPullTagFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void executeIndex(int i) {
                        if (AutoPullTagFriendsToGroupUtils.this.isFilled) {
                            MyWindowManager myWindowManager = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "邀请好友即将结束，倒计时 " + i + " 秒");
                            return;
                        }
                        MyWindowManager myWindowManager2 = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager2, "执行下一个批，倒计时 " + i + " 秒");
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        if (PerformClickUtils.findNodeIsExistByText(AutoPullTagFriendsToGroupUtils.this.autoService, "操作太频繁")) {
                            BaseServiceUtils.removeEndView(AutoPullTagFriendsToGroupUtils.this.mMyManager);
                            return;
                        }
                        if (AutoPullTagFriendsToGroupUtils.this.sayContent != null && !"".equals(AutoPullTagFriendsToGroupUtils.this.sayContent)) {
                            PerformClickUtils.findViewByIdAndPasteContent(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullTagFriendsToGroupUtils.this.sayContent);
                            AutoPullTagFriendsToGroupUtils.this.sleep(100);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void confirmCompleted(int i) {
        SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_part", Integer.valueOf(i));
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_num_part", 1)).intValue();
        LogUtils.log("WS_BABY_" + this.lastOperationNum + ListUtils.DEFAULT_JOIN_SEPARATOR + intValue);
        sleep(100);
        updateBottomText(this.mMyManager, "正在拉人进群\n正在发起进群邀请");
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        sleep(BannerConfig.DURATION);
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        backChatRoomInfoUI();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在拉人进群，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    AutoPullTagFriendsToGroupUtils.this.executeMain();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void excSelectTask() {
        updateBottomText(this.mMyManager, "正在执行好友选择操作");
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                int unused = AutoPullTagFriendsToGroupUtils.this.startIndex = 0;
                String unused2 = AutoPullTagFriendsToGroupUtils.this.middleStr = "";
                int unused3 = AutoPullTagFriendsToGroupUtils.this.currentSelectNum = 0;
                AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
            }

            public void nuFind() {
            }
        });
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY_executeMain.0");
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain.1");
                    PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                    AutoPullTagFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_executeMain.2");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.tags = operationParameterModel.getTagListNames();
        this.lastName = "";
        this.nodeText = "";
        this.middleStr = "";
        this.currentTagName = "";
        this.isExist = false;
        this.maxNum = 40;
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.operationFirstNum = 0;
        this.isJumpedStart = true;
        this.isFilled = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.sayContent = operationParameterModel.getSayContent();
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullRecordNum = 0;
        this.lastOperationNum = this.startPullIndex - 1;
        this.scrollNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.scrollSpeed = operationParameterModel.getScrollSpeed();
    }

    public void initFirstChatRoomInfoUI() {
        if (!this.isFilled) {
            LogUtils.log("WS_BABY.initFirstChatRoomInfoUI.1");
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, dialog_ok_id, default_list_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                        AutoPullTagFriendsToGroupUtils.this.preChatRoomInfo();
                        return;
                    }
                    AutoPullTagFriendsToGroupUtils.this.preChatRoomInfo();
                }

                public void nuFind() {
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_5.END");
        removeEndView(this.mMyManager);
    }

    public void initFirstSelectContactUI() {
        LogUtils.log("WS_BABY.SelectContactUI_2");
        new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.SelectContactUI_3");
                PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_search_id);
                new PerformClickUtils2().check(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.tag_view_group_id, (BaseServiceUtils.executeSpeedSetting / 10) + 50, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = AutoPullTagFriendsToGroupUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_view_group_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                            for (int i2 = 0; i2 < accessibilityNodeInfo.getChildCount(); i2++) {
                                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
                                AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils = AutoPullTagFriendsToGroupUtils.this;
                                String unused = autoPullTagFriendsToGroupUtils.nodeText = child.getText() + "";
                                String unused2 = AutoPullTagFriendsToGroupUtils.this.currentTagName = AutoPullTagFriendsToGroupUtils.this.nodeText;
                                MyWindowManager myWindowManager = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在拉取标签【 " + AutoPullTagFriendsToGroupUtils.this.currentTagName + " 】中的好友");
                                if (AutoPullTagFriendsToGroupUtils.this.tags.contains(AutoPullTagFriendsToGroupUtils.this.nodeText)) {
                                    PerformClickUtils.performClick(child);
                                    AutoPullTagFriendsToGroupUtils.this.initSelectLabelContactUI();
                                    return;
                                }
                            }
                        }
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initSelectLabelContactUI() {
        this.startIndex = 0;
        excSelectTask();
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        updateMiddleText(this.mMyManager, "自动拉人进群", this.middleStr == "" ? "完成拉人进群邀请！" : this.middleStr);
    }

    public void preChatRoomInfo() {
        this.isExist = false;
        new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, executeSpeed, (executeSpeedSetting / 10) + 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                AccessibilityNodeInfo rootInActiveWindow;
                while (!AutoPullTagFriendsToGroupUtils.this.isExist && MyApplication.enforceable && (rootInActiveWindow = AutoPullTagFriendsToGroupUtils.this.autoService.getRootInActiveWindow()) != null) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.default_list_id);
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_head_img_id);
                    if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= findAccessibilityNodeInfosByViewId2.size()) {
                                break;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i2);
                            if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                                if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员")) {
                                    boolean unused = AutoPullTagFriendsToGroupUtils.this.isExist = true;
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    AutoPullTagFriendsToGroupUtils.this.initFirstSelectContactUI();
                                    break;
                                }
                            }
                            i2++;
                        }
                    }
                    if (!AutoPullTagFriendsToGroupUtils.this.isExist && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                        AutoPullTagFriendsToGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void whileExecuteTask() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (AutoPullTagFriendsToGroupUtils.this.nameNodes == null) {
                            AutoPullTagFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                        }
                        if (AutoPullTagFriendsToGroupUtils.this.nameNodes == null || AutoPullTagFriendsToGroupUtils.this.nameNodes.size() <= 0 || !MyApplication.enforceable) {
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        AutoPullTagFriendsToGroupUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                        AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                    } catch (Exception e) {
                                    }
                                }
                            }).start();
                            return;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfo = AutoPullTagFriendsToGroupUtils.this.nameNodes.get(AutoPullTagFriendsToGroupUtils.this.nameNodes.size() - 1);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            if (str == null || "".equals(str)) {
                                str = "";
                            }
                            Rect rect = new Rect();
                            accessibilityNodeInfo.getBoundsInScreen(rect);
                            String unused = AutoPullTagFriendsToGroupUtils.this.lastName = str + rect.toString();
                        }
                        if (AutoPullTagFriendsToGroupUtils.this.operationFirstNum == AutoPullTagFriendsToGroupUtils.this.maxNum && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_excSelectTask.8");
                            int unused2 = AutoPullTagFriendsToGroupUtils.this.lastOperationNum = AutoPullTagFriendsToGroupUtils.this.startPullIndex + AutoPullTagFriendsToGroupUtils.this.pullRecordNum;
                            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_part", Integer.valueOf(AutoPullTagFriendsToGroupUtils.this.lastOperationNum));
                            int unused3 = AutoPullTagFriendsToGroupUtils.this.operationFirstNum = 0;
                            int unused4 = AutoPullTagFriendsToGroupUtils.this.scrollNum = 1;
                            if (AutoPullTagFriendsToGroupUtils.this.currentSelectNum == 0) {
                                LogUtils.log("WS_BABY_excSelectTask.88");
                                PerformClickUtils.performBack(AutoPullTagFriendsToGroupUtils.this.autoService);
                                AutoPullTagFriendsToGroupUtils.this.sleep(600);
                                PerformClickUtils.performBack(AutoPullTagFriendsToGroupUtils.this.autoService);
                                AutoPullTagFriendsToGroupUtils.this.sleep(600);
                                AutoPullTagFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                                return;
                            }
                            int unused5 = AutoPullTagFriendsToGroupUtils.this.currentSelectNum = 0;
                            LogUtils.log("WS_BABY_excSelectTask.888");
                            AutoPullTagFriendsToGroupUtils.this.confirmCompleted(AutoPullTagFriendsToGroupUtils.this.lastOperationNum);
                        } else if (AutoPullTagFriendsToGroupUtils.this.pullRecordNum >= AutoPullTagFriendsToGroupUtils.this.pullMaxNum) {
                            LogUtils.log("WS_BABY_excSelectTask.9");
                            boolean unused6 = AutoPullTagFriendsToGroupUtils.this.isFilled = true;
                            int unused7 = AutoPullTagFriendsToGroupUtils.this.lastOperationNum = AutoPullTagFriendsToGroupUtils.this.startPullIndex + AutoPullTagFriendsToGroupUtils.this.pullRecordNum;
                            int unused8 = AutoPullTagFriendsToGroupUtils.this.scrollNum = 1;
                            int unused9 = AutoPullTagFriendsToGroupUtils.this.operationFirstNum = 0;
                            int unused10 = AutoPullTagFriendsToGroupUtils.this.currentSelectNum = 0;
                            AutoPullTagFriendsToGroupUtils.this.confirmCompleted(AutoPullTagFriendsToGroupUtils.this.lastOperationNum);
                        } else {
                            if (AutoPullTagFriendsToGroupUtils.this.nameNodes == null) {
                                AutoPullTagFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                            }
                            if (AutoPullTagFriendsToGroupUtils.this.startIndex < AutoPullTagFriendsToGroupUtils.this.nameNodes.size()) {
                                LogUtils.log("WS_BABY_excSelectTask.startIndex = " + AutoPullTagFriendsToGroupUtils.this.startIndex + ",namesize = " + AutoPullTagFriendsToGroupUtils.this.nameNodes.size());
                                LogUtils.log("WS_BABY_excSelectTask.slast = " + AutoPullTagFriendsToGroupUtils.this.lastOperationNum + "，，，pull = " + AutoPullTagFriendsToGroupUtils.this.pullRecordNum + "，，，start = " + AutoPullTagFriendsToGroupUtils.this.startPullIndex);
                                try {
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = AutoPullTagFriendsToGroupUtils.this.nameNodes.get(AutoPullTagFriendsToGroupUtils.this.startIndex);
                                    if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                        String str2 = accessibilityNodeInfo2.getText() + "";
                                        if (str2 != null && !"".equals(str2)) {
                                            LogUtils.log("WS_BABY_excSelectTask.name = " + str2 + ",isJumpedStart = " + AutoPullTagFriendsToGroupUtils.this.isJumpedStart);
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                if (AutoPullTagFriendsToGroupUtils.this.scrollNum < AutoPullTagFriendsToGroupUtils.this.lastOperationNum) {
                                    AutoPullTagFriendsToGroupUtils.access$408(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.access$1308(AutoPullTagFriendsToGroupUtils.this);
                                    BaseServiceUtils.updateBottomText(AutoPullTagFriendsToGroupUtils.this.mMyManager, "正在分批拉好友,已跳过 " + AutoPullTagFriendsToGroupUtils.this.scrollNum + " 个");
                                    AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_checkbox);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || AutoPullTagFriendsToGroupUtils.this.startIndex >= findViewIdList.size()) {
                                    LogUtils.log("WS_BABY_excSelectTask.7");
                                    AutoPullTagFriendsToGroupUtils.access$408(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.access$1208(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.access$808(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                LogUtils.log("WS_BABY_excSelectTask.0");
                                AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList.get(AutoPullTagFriendsToGroupUtils.this.startIndex);
                                if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.isChecked()) {
                                    AutoPullTagFriendsToGroupUtils.access$408(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.access$1208(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.access$808(AutoPullTagFriendsToGroupUtils.this);
                                    AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                LogUtils.log("WS_BABY_excSelectTask.2");
                                AutoPullTagFriendsToGroupUtils.access$408(AutoPullTagFriendsToGroupUtils.this);
                                AutoPullTagFriendsToGroupUtils.access$1208(AutoPullTagFriendsToGroupUtils.this);
                                AutoPullTagFriendsToGroupUtils.access$808(AutoPullTagFriendsToGroupUtils.this);
                                AutoPullTagFriendsToGroupUtils.access$608(AutoPullTagFriendsToGroupUtils.this);
                                AutoPullTagFriendsToGroupUtils.this.sleep(5);
                                PerformClickUtils.performClick(accessibilityNodeInfo3);
                                BaseServiceUtils.updateBottomText(AutoPullTagFriendsToGroupUtils.this.mMyManager, "正在分批拉好友\n已执行 " + AutoPullTagFriendsToGroupUtils.this.operationFirstNum + " 个，已选择 " + AutoPullTagFriendsToGroupUtils.this.currentSelectNum + " 个");
                                AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                            } else if (AutoPullTagFriendsToGroupUtils.this.startIndex >= AutoPullTagFriendsToGroupUtils.this.nameNodes.size()) {
                                AutoPullTagFriendsToGroupUtils.this.nameNodes = null;
                                PerformClickUtils.scroll(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_id);
                                int unused11 = AutoPullTagFriendsToGroupUtils.this.startIndex = 1;
                                new PerformClickUtils2().check(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id, (AutoPullTagFriendsToGroupUtils.this.currentSelectNum != 0 || AutoPullTagFriendsToGroupUtils.this.lastOperationNum - AutoPullTagFriendsToGroupUtils.this.scrollNum <= 30) ? 350 : AutoPullTagFriendsToGroupUtils.this.scrollSpeed, 5, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        System.out.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                        AutoPullTagFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                        if (AutoPullTagFriendsToGroupUtils.this.nameNodes == null || AutoPullTagFriendsToGroupUtils.this.nameNodes.size() <= 0) {
                                            AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                            return;
                                        }
                                        String str = "";
                                        AccessibilityNodeInfo accessibilityNodeInfo = AutoPullTagFriendsToGroupUtils.this.nameNodes.get(AutoPullTagFriendsToGroupUtils.this.nameNodes.size() - 1);
                                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                            String str2 = accessibilityNodeInfo.getText() + "";
                                            if (str2 == null || "".equals(str2)) {
                                                str2 = str;
                                            }
                                            Rect rect = new Rect();
                                            accessibilityNodeInfo.getBoundsInScreen(rect);
                                            str = str2 + rect.toString();
                                        }
                                        LogUtils.log("WS_BABY_excSelectTask.11.lastname = " + str);
                                        if (str.equals(AutoPullTagFriendsToGroupUtils.this.lastName)) {
                                            boolean unused = AutoPullTagFriendsToGroupUtils.this.isFilled = true;
                                            int unused2 = AutoPullTagFriendsToGroupUtils.this.lastOperationNum = AutoPullTagFriendsToGroupUtils.this.startPullIndex + AutoPullTagFriendsToGroupUtils.this.pullRecordNum;
                                            if (AutoPullTagFriendsToGroupUtils.this.currentSelectNum == 0) {
                                                String unused3 = AutoPullTagFriendsToGroupUtils.this.middleStr = "您设置的起点 高于 标签好友的数量，共邀请0人";
                                                BaseServiceUtils.removeEndView(AutoPullTagFriendsToGroupUtils.this.mMyManager);
                                                return;
                                            }
                                            AutoPullTagFriendsToGroupUtils.this.confirmCompleted(1);
                                            return;
                                        }
                                        AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
            }).start();
        }
    }
}
