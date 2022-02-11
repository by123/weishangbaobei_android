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
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoPullFriendsToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullFriendsToGroupUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
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
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public int pullType = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int scrollSpeed = 150;
    /* access modifiers changed from: private */
    public int shieldNum = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;

    private AutoPullFriendsToGroupUtils() {
    }

    static /* synthetic */ int access$008(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.startIndex;
        autoPullFriendsToGroupUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1008(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.scrollNum;
        autoPullFriendsToGroupUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.shieldNum;
        autoPullFriendsToGroupUtils.shieldNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.currentSelectNum;
        autoPullFriendsToGroupUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.operationFirstNum;
        autoPullFriendsToGroupUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.pullRecordNum;
        autoPullFriendsToGroupUtils.pullRecordNum = i + 1;
        return i;
    }

    public static AutoPullFriendsToGroupUtils getInstance() {
        instance = new AutoPullFriendsToGroupUtils();
        return instance;
    }

    public void backChatRoomInfoUI() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加联系人", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().check(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + 100, (int) SocializeConstants.CANCLE_RESULTCODE, 4, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void end() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                        new PerformClickUtils2().checkLoad(AutoPullFriendsToGroupUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                AutoPullFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void executeIndex(int i) {
                        if (AutoPullFriendsToGroupUtils.this.isFilled) {
                            MyWindowManager myWindowManager = AutoPullFriendsToGroupUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "邀请好友即将结束，倒计时 " + i + " 秒");
                            return;
                        }
                        MyWindowManager myWindowManager2 = AutoPullFriendsToGroupUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager2, "执行下一个批，倒计时 " + i + " 秒");
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        if (PerformClickUtils.findNodeIsExistByText(AutoPullFriendsToGroupUtils.this.autoService, "操作太频繁")) {
                            BaseServiceUtils.removeEndView(AutoPullFriendsToGroupUtils.this.mMyManager);
                            return;
                        }
                        if (AutoPullFriendsToGroupUtils.this.sayContent != null && !"".equals(AutoPullFriendsToGroupUtils.this.sayContent)) {
                            PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullFriendsToGroupUtils.this.sayContent);
                            AutoPullFriendsToGroupUtils.this.sleep(100);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
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
        if (this.pullType == 0) {
            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_all", Integer.valueOf(i));
        } else {
            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_shield", Integer.valueOf(i));
        }
        sleep(100);
        updateBottomText(this.mMyManager, "正在分批拉好友进群\n正在发起进群邀请");
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        backChatRoomInfoUI();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startPullIndex + " 个好友开始拉人进群\n请您不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    AutoPullFriendsToGroupUtils.this.executeMain();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void excSelectTask() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                int unused = AutoPullFriendsToGroupUtils.this.startIndex = 0;
                String unused2 = AutoPullFriendsToGroupUtils.this.middleStr = "";
                int unused3 = AutoPullFriendsToGroupUtils.this.currentSelectNum = 0;
                AutoPullFriendsToGroupUtils.this.whileExecuteTask();
            }

            public void nuFind() {
            }
        });
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain.1");
                    PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                    AutoPullFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
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
        this.lastName = "";
        this.maxNum = 40;
        this.pullRecordNum = 0;
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullType = operationParameterModel.getAutoPullType();
        this.jumpPersons = operationParameterModel.getTagListPeoples();
        this.operationFirstNum = 0;
        this.isFilled = false;
        this.sayContent = operationParameterModel.getSayContent();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullRecordNum = 0;
        this.lastOperationNum = this.startPullIndex - 1;
        this.scrollNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.shieldNum = 0;
        this.scrollSpeed = operationParameterModel.getScrollSpeed();
    }

    public void initFirstChatRoomInfoUI() {
        if (this.isFilled || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
            removeEndView(this.mMyManager);
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_0");
        new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, dialog_ok_id, default_list_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (i == 0) {
                    PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    AutoPullFriendsToGroupUtils.this.preChatRoomInfo();
                    return;
                }
                AutoPullFriendsToGroupUtils.this.preChatRoomInfo();
            }

            public void nuFind() {
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.middleStr == null || "".equals(this.middleStr)) {
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("本次共向");
            sb.append(this.lastOperationNum > 0 ? this.lastOperationNum - this.startPullIndex : 0);
            sb.append("个好友，发起了进群邀请！");
            updateMiddleText(myWindowManager, "自动拉人进群", sb.toString());
            return;
        }
        updateMiddleText(this.mMyManager, "自动拉人进群", this.middleStr);
    }

    public void preChatRoomInfo() {
        boolean z;
        boolean z2 = false;
        while (!z2 && MyApplication.enforceable) {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow == null || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_3");
                return;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(default_list_id);
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_head_img_id);
            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                int i = 0;
                while (true) {
                    if (i >= findAccessibilityNodeInfosByViewId2.size()) {
                        break;
                    } else if (!MyApplication.enforceable) {
                        z = z2;
                        break;
                    } else {
                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                            if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员") && MyApplication.enforceable) {
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                excSelectTask();
                                z = true;
                                break;
                            }
                        }
                        i++;
                    }
                }
                if (!z || MyApplication.enforceable || findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                    z2 = z;
                } else {
                    LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                    findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                    sleep(MyApplication.SCROLL_SPEED);
                    z2 = z;
                }
            }
            z = z2;
            if (!z && MyApplication.enforceable) {
            }
            z2 = z;
        }
    }

    public void whileExecuteTask() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("WS_BABY_time1_" + System.currentTimeMillis());
                    if (AutoPullFriendsToGroupUtils.this.nameNodes == null) {
                        AutoPullFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                    }
                    System.out.println("WS_BABY_time3_" + System.currentTimeMillis());
                    if (AutoPullFriendsToGroupUtils.this.nameNodes == null || AutoPullFriendsToGroupUtils.this.nameNodes.size() <= 0 || !MyApplication.enforceable) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    AutoPullFriendsToGroupUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_time8_" + System.currentTimeMillis());
                                    if (MyApplication.enforceable) {
                                        AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }).start();
                        return;
                    }
                    AccessibilityNodeInfo accessibilityNodeInfo = AutoPullFriendsToGroupUtils.this.nameNodes.get(AutoPullFriendsToGroupUtils.this.nameNodes.size() - 1);
                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                        String str = accessibilityNodeInfo.getText() + "";
                        if (str == null || "".equals(str)) {
                            str = "";
                        }
                        Rect rect = new Rect();
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        String unused = AutoPullFriendsToGroupUtils.this.lastName = str + rect.toString();
                    }
                    if (AutoPullFriendsToGroupUtils.this.operationFirstNum == AutoPullFriendsToGroupUtils.this.maxNum && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_excSelectTask.8");
                        int unused2 = AutoPullFriendsToGroupUtils.this.lastOperationNum = AutoPullFriendsToGroupUtils.this.startPullIndex + AutoPullFriendsToGroupUtils.this.pullRecordNum;
                        if (AutoPullFriendsToGroupUtils.this.pullType == 0) {
                            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_all", Integer.valueOf(AutoPullFriendsToGroupUtils.this.lastOperationNum));
                        } else {
                            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_shield", Integer.valueOf(AutoPullFriendsToGroupUtils.this.lastOperationNum));
                        }
                        int unused3 = AutoPullFriendsToGroupUtils.this.operationFirstNum = 0;
                        int unused4 = AutoPullFriendsToGroupUtils.this.scrollNum = 1;
                        if (AutoPullFriendsToGroupUtils.this.currentSelectNum == 0) {
                            LogUtils.log("WS_BABY_excSelectTask.88");
                            PerformClickUtils.performBack(AutoPullFriendsToGroupUtils.this.autoService);
                            AutoPullFriendsToGroupUtils.this.sleep(600);
                            AutoPullFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                            return;
                        }
                        int unused5 = AutoPullFriendsToGroupUtils.this.currentSelectNum = 0;
                        LogUtils.log("WS_BABY_excSelectTask.888");
                        AutoPullFriendsToGroupUtils.this.confirmCompleted(AutoPullFriendsToGroupUtils.this.lastOperationNum);
                    } else if (AutoPullFriendsToGroupUtils.this.pullRecordNum >= AutoPullFriendsToGroupUtils.this.pullMaxNum) {
                        LogUtils.log("WS_BABY_excSelectTask.9");
                        boolean unused6 = AutoPullFriendsToGroupUtils.this.isFilled = true;
                        int unused7 = AutoPullFriendsToGroupUtils.this.lastOperationNum = AutoPullFriendsToGroupUtils.this.startPullIndex + AutoPullFriendsToGroupUtils.this.pullRecordNum;
                        int unused8 = AutoPullFriendsToGroupUtils.this.scrollNum = 1;
                        int unused9 = AutoPullFriendsToGroupUtils.this.operationFirstNum = 0;
                        int unused10 = AutoPullFriendsToGroupUtils.this.currentSelectNum = 0;
                        AutoPullFriendsToGroupUtils.this.confirmCompleted(AutoPullFriendsToGroupUtils.this.lastOperationNum);
                    } else {
                        if (AutoPullFriendsToGroupUtils.this.nameNodes == null) {
                            AutoPullFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                        }
                        if (AutoPullFriendsToGroupUtils.this.startIndex < AutoPullFriendsToGroupUtils.this.nameNodes.size()) {
                            System.out.println("WS_BABY_time4_" + System.currentTimeMillis());
                            LogUtils.log("WS_BABY_excSelectTask.startIndex = " + AutoPullFriendsToGroupUtils.this.startIndex + ",namesize = " + AutoPullFriendsToGroupUtils.this.nameNodes.size());
                            LogUtils.log("WS_BABY_excSelectTask.slast = " + AutoPullFriendsToGroupUtils.this.lastOperationNum + "，，，pull = " + AutoPullFriendsToGroupUtils.this.pullRecordNum + "，，，start = " + AutoPullFriendsToGroupUtils.this.startPullIndex);
                            String str2 = "";
                            try {
                                AccessibilityNodeInfo accessibilityNodeInfo2 = AutoPullFriendsToGroupUtils.this.nameNodes.get(AutoPullFriendsToGroupUtils.this.startIndex);
                                if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                    String str3 = accessibilityNodeInfo2.getText() + "";
                                    if (str3 != null) {
                                        try {
                                            if (!"".equals(str3)) {
                                                LogUtils.log("WS_BABY_excSelectTask.name = " + str3);
                                            }
                                        } catch (Exception e) {
                                            str2 = str3;
                                        }
                                    }
                                    str2 = str3;
                                }
                            } catch (Exception e2) {
                            }
                            if (AutoPullFriendsToGroupUtils.this.scrollNum < AutoPullFriendsToGroupUtils.this.lastOperationNum) {
                                AutoPullFriendsToGroupUtils.access$008(AutoPullFriendsToGroupUtils.this);
                                AutoPullFriendsToGroupUtils.access$1008(AutoPullFriendsToGroupUtils.this);
                                System.out.println("WS_BABY_time5_" + System.currentTimeMillis());
                                BaseServiceUtils.updateBottomText(AutoPullFriendsToGroupUtils.this.mMyManager, "正在分批拉好友进群\n已跳过 " + AutoPullFriendsToGroupUtils.this.scrollNum + " 个");
                                System.out.println("WS_BABY_time6_" + System.currentTimeMillis());
                                if (MyApplication.enforceable) {
                                    AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                return;
                            }
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_checkbox);
                            System.out.println("WS_BABY_time7_" + System.currentTimeMillis());
                            if (findViewIdList == null || findViewIdList.size() <= 0 || AutoPullFriendsToGroupUtils.this.startIndex >= findViewIdList.size()) {
                                LogUtils.log("WS_BABY_excSelectTask.7");
                                AutoPullFriendsToGroupUtils.access$008(AutoPullFriendsToGroupUtils.this);
                                AutoPullFriendsToGroupUtils.access$808(AutoPullFriendsToGroupUtils.this);
                                AutoPullFriendsToGroupUtils.access$408(AutoPullFriendsToGroupUtils.this);
                                if (MyApplication.enforceable) {
                                    AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                return;
                            }
                            LogUtils.log("WS_BABY_excSelectTask.0");
                            AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList.get(AutoPullFriendsToGroupUtils.this.startIndex);
                            if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.isChecked() || AutoPullFriendsToGroupUtils.this.jumpPersons.contains(str2)) {
                                AutoPullFriendsToGroupUtils.access$008(AutoPullFriendsToGroupUtils.this);
                                AutoPullFriendsToGroupUtils.access$808(AutoPullFriendsToGroupUtils.this);
                                AutoPullFriendsToGroupUtils.access$408(AutoPullFriendsToGroupUtils.this);
                                if (AutoPullFriendsToGroupUtils.this.jumpPersons.contains(str2)) {
                                    AutoPullFriendsToGroupUtils.access$1408(AutoPullFriendsToGroupUtils.this);
                                }
                                if (MyApplication.enforceable) {
                                    AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                    return;
                                }
                                return;
                            }
                            LogUtils.log("WS_BABY_excSelectTask.2");
                            AutoPullFriendsToGroupUtils.access$008(AutoPullFriendsToGroupUtils.this);
                            AutoPullFriendsToGroupUtils.access$808(AutoPullFriendsToGroupUtils.this);
                            AutoPullFriendsToGroupUtils.access$408(AutoPullFriendsToGroupUtils.this);
                            AutoPullFriendsToGroupUtils.access$208(AutoPullFriendsToGroupUtils.this);
                            AutoPullFriendsToGroupUtils.this.sleep(5);
                            PerformClickUtils.performClick(accessibilityNodeInfo3);
                            if (AutoPullFriendsToGroupUtils.this.pullType == 0) {
                                BaseServiceUtils.updateBottomText(AutoPullFriendsToGroupUtils.this.mMyManager, "正在分批拉好友进群\n已执行 " + AutoPullFriendsToGroupUtils.this.operationFirstNum + " 个，已选择 " + AutoPullFriendsToGroupUtils.this.currentSelectNum + " 个");
                            } else {
                                BaseServiceUtils.updateBottomText(AutoPullFriendsToGroupUtils.this.mMyManager, "正在分批拉好友进群\n已执行 " + AutoPullFriendsToGroupUtils.this.operationFirstNum + " 个，已选择 " + AutoPullFriendsToGroupUtils.this.currentSelectNum + " 个 ，已屏蔽 " + AutoPullFriendsToGroupUtils.this.shieldNum + " 个");
                            }
                            if (MyApplication.enforceable) {
                                AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                            }
                        } else if (AutoPullFriendsToGroupUtils.this.startIndex >= AutoPullFriendsToGroupUtils.this.nameNodes.size()) {
                            AutoPullFriendsToGroupUtils.this.nameNodes = null;
                            PerformClickUtils.scroll(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_id);
                            int unused11 = AutoPullFriendsToGroupUtils.this.startIndex = 1;
                            System.out.println("WS_BABY_time8_" + System.currentTimeMillis());
                            new PerformClickUtils2().check(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id, (AutoPullFriendsToGroupUtils.this.currentSelectNum != 0 || AutoPullFriendsToGroupUtils.this.lastOperationNum - AutoPullFriendsToGroupUtils.this.scrollNum <= 30) ? 350 : AutoPullFriendsToGroupUtils.this.scrollSpeed, 5, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    System.out.println("WS_BABY_time9_" + System.currentTimeMillis());
                                    AutoPullFriendsToGroupUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                    if (AutoPullFriendsToGroupUtils.this.nameNodes == null || AutoPullFriendsToGroupUtils.this.nameNodes.size() <= 0) {
                                        AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                        return;
                                    }
                                    String str = "";
                                    AccessibilityNodeInfo accessibilityNodeInfo = AutoPullFriendsToGroupUtils.this.nameNodes.get(AutoPullFriendsToGroupUtils.this.nameNodes.size() - 1);
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
                                    if (str.equals(AutoPullFriendsToGroupUtils.this.lastName)) {
                                        boolean unused = AutoPullFriendsToGroupUtils.this.isFilled = true;
                                        int unused2 = AutoPullFriendsToGroupUtils.this.lastOperationNum = AutoPullFriendsToGroupUtils.this.startPullIndex + AutoPullFriendsToGroupUtils.this.pullRecordNum;
                                        if (AutoPullFriendsToGroupUtils.this.currentSelectNum == 0) {
                                            String unused3 = AutoPullFriendsToGroupUtils.this.middleStr = "您设置的起点 高于 标签好友的数量，共邀请0人";
                                            BaseServiceUtils.removeEndView(AutoPullFriendsToGroupUtils.this.mMyManager);
                                            return;
                                        }
                                        AutoPullFriendsToGroupUtils.this.confirmCompleted(1);
                                    } else if (MyApplication.enforceable) {
                                        AutoPullFriendsToGroupUtils.this.whileExecuteTask();
                                    }
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    }
                } catch (Exception e3) {
                }
            }
        }).start();
    }
}
