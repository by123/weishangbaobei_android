package com.wx.assistants.service_utils;

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
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GroupSendPublicNumberToGroupsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendPublicNumberToGroupsUtils instance;
    /* access modifiers changed from: private */
    public List<AccessibilityNodeInfo> accessibilityNodeInfo = null;
    /* access modifiers changed from: private */
    public boolean initWhile = true;
    /* access modifiers changed from: private */
    public boolean isCanExecuteSelect = true;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isFirstExecute = true;
    private boolean isFirstJump = true;
    /* access modifiers changed from: private */
    public boolean isShowMiddleView = true;
    /* access modifiers changed from: private */
    public boolean isTopEnd = true;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    private String jumpGroupNames;
    /* access modifiers changed from: private */
    public List<String> jumpGroupNamesList = new ArrayList();
    private int jumpTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public int maxNum = 1;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int operationJumpNum = 1;
    /* access modifiers changed from: private */
    public int otherSendType = 0;
    /* access modifiers changed from: private */
    public String otherSendTypeName = "公众号";
    /* access modifiers changed from: private */
    public int position = 0;
    /* access modifiers changed from: private */
    public int pullRecordNum = 1;
    private String pullResult = "";
    /* access modifiers changed from: private */
    public List<String> recordList = new ArrayList();
    /* access modifiers changed from: private */
    public int recordNum = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollNumber = 0;
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;

    private GroupSendPublicNumberToGroupsUtils() {
    }

    static /* synthetic */ int access$108(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.scrollNumber;
        groupSendPublicNumberToGroupsUtils.scrollNumber = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.scrollNumber;
        groupSendPublicNumberToGroupsUtils.scrollNumber = i - 1;
        return i;
    }

    static /* synthetic */ int access$1208(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.operationFirstNum;
        groupSendPublicNumberToGroupsUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1608(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.recordNum;
        groupSendPublicNumberToGroupsUtils.recordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.operationJumpNum;
        groupSendPublicNumberToGroupsUtils.operationJumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendPublicNumberToGroupsUtils groupSendPublicNumberToGroupsUtils) {
        int i = groupSendPublicNumberToGroupsUtils.pullRecordNum;
        groupSendPublicNumberToGroupsUtils.pullRecordNum = i + 1;
        return i;
    }

    public static GroupSendPublicNumberToGroupsUtils getInstance() {
        instance = new GroupSendPublicNumberToGroupsUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        if (this.isShowMiddleView) {
            this.mMyManager.showMiddleView();
        }
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "正从第 " + this.pullRecordNum + " 个微信群，群发" + this.otherSendTypeName + "\n请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    GroupSendPublicNumberToGroupsUtils.this.searchStartNode();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void excSelectTask() {
        try {
            if (this.isCanExecuteSelect && MyApplication.enforceable) {
                this.isCanExecuteSelect = false;
                updateBottomText(this.mMyManager, "正在执行多群选择操作");
                if (this.isFirstJump) {
                    this.isFirstJump = false;
                    new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_name_id, list_select_checkbox, executeSpeed, executeSpeed, 300, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            AccessibilityNodeInfo accessibilityNodeInfo;
                            final AccessibilityNodeInfo rootInActiveWindow = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                while (GroupSendPublicNumberToGroupsUtils.this.initWhile) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_id);
                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(accessibilityNodeInfo);
                                        new PerformClickUtils2().checkSyn2(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.list_select_group_id, BaseServiceUtils.list_select_group_name_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                GroupSendPublicNumberToGroupsUtils.access$108(GroupSendPublicNumberToGroupsUtils.this);
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_name_id);
                                                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                                                    String str = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).getText() + "";
                                                    if (!str.equals(GroupSendPublicNumberToGroupsUtils.this.lastName)) {
                                                        String unused = GroupSendPublicNumberToGroupsUtils.this.lastName = str;
                                                    } else {
                                                        boolean unused2 = GroupSendPublicNumberToGroupsUtils.this.initWhile = false;
                                                    }
                                                }
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                    }
                                }
                                while (GroupSendPublicNumberToGroupsUtils.this.scrollNumber != 0) {
                                    new PerformClickUtils2().checkSyn(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.list_select_group_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            AccessibilityNodeInfo accessibilityNodeInfo;
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_id);
                                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null && MyApplication.enforceable) {
                                                PerformClickUtils.scrollTop(accessibilityNodeInfo);
                                                GroupSendPublicNumberToGroupsUtils.access$110(GroupSendPublicNumberToGroupsUtils.this);
                                            }
                                        }

                                        public void nuFind() {
                                        }
                                    });
                                }
                                LogUtils.log("WS_BABY_ContactLabelManagerUI_lastName" + GroupSendPublicNumberToGroupsUtils.this.lastName);
                                GroupSendPublicNumberToGroupsUtils.this.initExecuted();
                            }
                        }

                        public void nuFind() {
                        }
                    });
                    return;
                }
                initExecuted();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("正在分批群发 ");
            sb.append(this.otherSendTypeName);
            sb.append("\n已向 ");
            sb.append(this.pullRecordNum - 1);
            sb.append(" 个微信群，发起群发。\n若出现卡顿，请耐心等待几秒..");
            updateBottomText(myWindowManager, sb.toString());
            saveRecord(this.pullRecordNum);
            new PerformClickUtils2().delay(100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain1");
                    if (GroupSendPublicNumberToGroupsUtils.this.isFilled || !MyApplication.enforceable) {
                        BaseServiceUtils.removeEndView(GroupSendPublicNumberToGroupsUtils.this.mMyManager);
                    } else if (GroupSendPublicNumberToGroupsUtils.this.spaceTime > 0) {
                        LogUtils.log("WS_BABY_executeMain2");
                        new PerformClickUtils2().countDown2(GroupSendPublicNumberToGroupsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void end() {
                                LogUtils.log("WS_BABY_executeMain3");
                                GroupSendPublicNumberToGroupsUtils.this.initStartExecuteSend();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = GroupSendPublicNumberToGroupsUtils.this.mMyManager;
                                StringBuilder sb = new StringBuilder();
                                sb.append("已向 ");
                                sb.append(GroupSendPublicNumberToGroupsUtils.this.pullRecordNum - 1);
                                sb.append(" 个微信群，发起群发。\n若出现卡顿，请耐心等待..\n正在延时等待，倒计时 ");
                                sb.append(i);
                                sb.append(" 秒");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                            }
                        });
                    } else {
                        LogUtils.log("WS_BABY_executeMain4");
                        GroupSendPublicNumberToGroupsUtils.this.initStartExecuteSend();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeStartSend() {
        new PerformClickUtils2().checkString(this.autoService, "发送给朋友", (executeSpeedSetting / 10) + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "发送给朋友");
                new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "多选", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "多选");
                        if (GroupSendPublicNumberToGroupsUtils.this.sendType == 1) {
                            GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                        } else {
                            new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "更多联系人", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "更多联系人");
                                    new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "选择群聊", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "选择群聊");
                                            if (GroupSendPublicNumberToGroupsUtils.this.sendType == 0) {
                                                GroupSendPublicNumberToGroupsUtils.this.excSelectTask();
                                            } else if (GroupSendPublicNumberToGroupsUtils.this.sendType != 1 && GroupSendPublicNumberToGroupsUtils.this.sendType == 2) {
                                                GroupSendPublicNumberToGroupsUtils.this.excSelectTask();
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
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isFilled = false;
        this.lastName = "";
        this.pullResult = "";
        this.maxNum = 9;
        this.jumpTime = 200;
        this.recordNum = 0;
        this.position = 0;
        this.isFirstExecute = true;
        this.isFirstJump = true;
        this.isShowMiddleView = true;
        this.scrollNumber = 0;
        this.initWhile = true;
        this.isTopEnd = true;
        this.otherSendType = operationParameterModel.getOtherSendType();
        this.sendType = operationParameterModel.getSendCardType();
        this.pullRecordNum = operationParameterModel.getStartIndex();
        this.jumpGroupNames = operationParameterModel.getJumpGroupNames();
        this.jumpGroupNamesList = new ArrayList();
        this.spaceTime = operationParameterModel.getSpaceTime();
        initJumpData();
        this.sayContent = operationParameterModel.getSayContent();
        this.recordList = new ArrayList();
        this.operationJumpNum = 1;
        this.operationFirstNum = 0;
        this.isWhile = true;
        this.isCanExecuteSelect = true;
        if (this.otherSendType == 0) {
            this.otherSendTypeName = "公众号";
        } else if (this.otherSendType == 1) {
            this.otherSendTypeName = "小程序";
        }
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initExecuted() {
        LogUtils.log("WS_BABY_SelectContactUI_111111");
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_group_name_id, list_select_checkbox, executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                AccessibilityNodeInfo accessibilityNodeInfo;
                final AccessibilityNodeInfo rootInActiveWindow = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null) {
                    boolean unused = GroupSendPublicNumberToGroupsUtils.this.isTopEnd = true;
                    int unused2 = GroupSendPublicNumberToGroupsUtils.this.position = 0;
                    int unused3 = GroupSendPublicNumberToGroupsUtils.this.operationJumpNum = 1;
                    LogUtils.log("WS_BABY_SelectContactUI_pullrecordnum" + GroupSendPublicNumberToGroupsUtils.this.pullRecordNum + "#" + GroupSendPublicNumberToGroupsUtils.this.isTopEnd);
                    while (GroupSendPublicNumberToGroupsUtils.this.isTopEnd && GroupSendPublicNumberToGroupsUtils.this.pullRecordNum > 1 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_SelectContactUI_pullrecordnum_@_" + GroupSendPublicNumberToGroupsUtils.this.pullRecordNum + "@" + GroupSendPublicNumberToGroupsUtils.this.operationJumpNum);
                        new PerformClickUtils2().checkSyn2(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.list_select_group_id, BaseServiceUtils.list_select_group_name_id, 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                AccessibilityNodeInfo accessibilityNodeInfo;
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_id);
                                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                    LogUtils.log("WS_BABY_null_1");
                                    return;
                                }
                                AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(0);
                                if (accessibilityNodeInfo2 != null) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_name_id);
                                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                                        LogUtils.log("WS_BABY_null_3");
                                        boolean unused = GroupSendPublicNumberToGroupsUtils.this.isTopEnd = false;
                                        return;
                                    }
                                    LogUtils.log("WS_BABY_@@@@@@position=" + GroupSendPublicNumberToGroupsUtils.this.position + "@topNodes.size()" + findAccessibilityNodeInfosByViewId2.size());
                                    int access$400 = GroupSendPublicNumberToGroupsUtils.this.position;
                                    while (true) {
                                        int i2 = access$400;
                                        if (i2 < findAccessibilityNodeInfosByViewId2.size()) {
                                            if (GroupSendPublicNumberToGroupsUtils.this.operationJumpNum >= GroupSendPublicNumberToGroupsUtils.this.pullRecordNum) {
                                                LogUtils.log("WS_BABY_4");
                                                boolean unused2 = GroupSendPublicNumberToGroupsUtils.this.isTopEnd = false;
                                                break;
                                            }
                                            LogUtils.log("WS_BABY_@@@@@@@@@@@position=" + GroupSendPublicNumberToGroupsUtils.this.position + "@topNodes.size()" + findAccessibilityNodeInfosByViewId2.size());
                                            if (findAccessibilityNodeInfosByViewId2.size() > i2 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i2)) != null) {
                                                String str = accessibilityNodeInfo.getText() + "";
                                                LogUtils.log("WS_BABY_@@@@@@name" + str + "@" + GroupSendPublicNumberToGroupsUtils.this.lastName);
                                                GroupSendPublicNumberToGroupsUtils.access$508(GroupSendPublicNumberToGroupsUtils.this);
                                                GroupSendPublicNumberToGroupsUtils.this.recordList.add(str);
                                                if (GroupSendPublicNumberToGroupsUtils.this.lastName.equals(str)) {
                                                    LogUtils.log("WS_BABY_==");
                                                    boolean unused3 = GroupSendPublicNumberToGroupsUtils.this.isTopEnd = false;
                                                    BaseServiceUtils.removeEndView(GroupSendPublicNumberToGroupsUtils.this.mMyManager);
                                                    break;
                                                }
                                                BaseServiceUtils.updateBottomText(GroupSendPublicNumberToGroupsUtils.this.mMyManager, "从 " + GroupSendPublicNumberToGroupsUtils.this.pullRecordNum + " 个开始分批群发,已跳过 " + GroupSendPublicNumberToGroupsUtils.this.operationJumpNum + " 个");
                                            }
                                            access$400 = i2 + 1;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (GroupSendPublicNumberToGroupsUtils.this.isTopEnd) {
                                        int unused4 = GroupSendPublicNumberToGroupsUtils.this.position = 1;
                                        PerformClickUtils.scroll(accessibilityNodeInfo2);
                                        return;
                                    }
                                    return;
                                }
                                LogUtils.log("WS_BABY_null_2");
                            }

                            public void nuFind() {
                            }
                        });
                    }
                    LogUtils.log("WS_BABY_SelectContactUI_pullrecordnum" + GroupSendPublicNumberToGroupsUtils.this.pullRecordNum + "##" + GroupSendPublicNumberToGroupsUtils.this.isTopEnd);
                    while (true) {
                        if (!GroupSendPublicNumberToGroupsUtils.this.isWhile || !MyApplication.enforceable) {
                            break;
                        }
                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow2 == null) {
                            LogUtils.log("WS_BABY_SelectContactUI_nullll");
                            break;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_name_id);
                        if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                            LogUtils.log("WS_BABY_SelectContactUI_1111111");
                        } else {
                            int i2 = 0;
                            while (true) {
                                if (i2 >= findAccessibilityNodeInfosByViewId2.size()) {
                                    break;
                                } else if (MyApplication.enforceable) {
                                    if (findAccessibilityNodeInfosByViewId2.size() > i2 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i2)) != null) {
                                        String str = accessibilityNodeInfo.getText() + "";
                                        if (GroupSendPublicNumberToGroupsUtils.this.lastName.equals(str)) {
                                            boolean unused4 = GroupSendPublicNumberToGroupsUtils.this.isWhile = false;
                                            boolean unused5 = GroupSendPublicNumberToGroupsUtils.this.isFilled = true;
                                        }
                                        if ((GroupSendPublicNumberToGroupsUtils.this.sendType != 2 || !GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.contains(str)) && !GroupSendPublicNumberToGroupsUtils.this.recordList.contains(str)) {
                                            GroupSendPublicNumberToGroupsUtils.access$1208(GroupSendPublicNumberToGroupsUtils.this);
                                            if (GroupSendPublicNumberToGroupsUtils.this.operationFirstNum <= GroupSendPublicNumberToGroupsUtils.this.maxNum) {
                                                LogUtils.log("WS_BABY_SelectContactUI_executed" + str);
                                                GroupSendPublicNumberToGroupsUtils.this.recordList.add(str);
                                                GroupSendPublicNumberToGroupsUtils.access$608(GroupSendPublicNumberToGroupsUtils.this);
                                                GroupSendPublicNumberToGroupsUtils.this.sleep(10);
                                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                                BaseServiceUtils.updateBottomText(GroupSendPublicNumberToGroupsUtils.this.mMyManager, "正在执行分批群发,已勾选 " + GroupSendPublicNumberToGroupsUtils.this.operationFirstNum + " 个");
                                                if (GroupSendPublicNumberToGroupsUtils.this.operationFirstNum == GroupSendPublicNumberToGroupsUtils.this.maxNum) {
                                                    boolean unused6 = GroupSendPublicNumberToGroupsUtils.this.isWhile = false;
                                                    break;
                                                }
                                            } else {
                                                continue;
                                            }
                                        }
                                    }
                                    i2++;
                                } else {
                                    return;
                                }
                            }
                            if (GroupSendPublicNumberToGroupsUtils.this.operationFirstNum == GroupSendPublicNumberToGroupsUtils.this.maxNum) {
                                int unused7 = GroupSendPublicNumberToGroupsUtils.this.operationFirstNum = 0;
                                break;
                            }
                            AccessibilityNodeInfo rootInActiveWindow3 = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow();
                            if (!(rootInActiveWindow3 == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow3.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_group_id)) == null)) {
                                PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                GroupSendPublicNumberToGroupsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            }
                        }
                    }
                    MyWindowManager myWindowManager = GroupSendPublicNumberToGroupsUtils.this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("正在分批群发 ");
                    sb.append(GroupSendPublicNumberToGroupsUtils.this.otherSendTypeName);
                    sb.append("\n已向 ");
                    sb.append(GroupSendPublicNumberToGroupsUtils.this.pullRecordNum - 1);
                    sb.append(" 个微信群，发起群发。");
                    BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.complete_id);
                    GroupSendPublicNumberToGroupsUtils.this.sleep(BannerConfig.DURATION);
                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.complete_id);
                    GroupSendPublicNumberToGroupsUtils.this.sleep(BannerConfig.DURATION);
                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.complete_id);
                    LogUtils.log("WS_BABY_SelectContactUI_6");
                    new PerformClickUtils2().checkNodeAllIds(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.toast_edit_id, BaseServiceUtils.dialog_ok_id, 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (GroupSendPublicNumberToGroupsUtils.this.sayContent != null && !GroupSendPublicNumberToGroupsUtils.this.sayContent.equals("")) {
                                LogUtils.log("WS_BABY_SelectContactUI_7");
                                GroupSendPublicNumberToGroupsUtils.this.sleep(10);
                                PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendPublicNumberToGroupsUtils.this.sayContent);
                            }
                            PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                            LogUtils.log("WS_BABY_SelectContactUI_8");
                            new PerformClickUtils2().checkNodeId(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.contact_list_img_id, SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    GroupSendPublicNumberToGroupsUtils.this.searchStartNode();
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                        }
                    });
                }
            }

            public void nuFind() {
            }
        });
    }

    public void initJumpData() {
        if (this.jumpGroupNames != null && !"".equals(this.jumpGroupNames) && this.sendType != 0) {
            if (this.jumpGroupNames.contains(";")) {
                this.jumpGroupNamesList.addAll(Arrays.asList(this.jumpGroupNames.split(";")));
            } else {
                this.jumpGroupNamesList.add(this.jumpGroupNames);
            }
            if (this.sendType == 1) {
                try {
                    if (this.pullRecordNum > 1) {
                        LogUtils.log("WS_BABY.st.1");
                        for (int i = 0; i < this.pullRecordNum - 1; i++) {
                            LogUtils.log("WS_BABY.st.st" + i);
                            if (this.jumpGroupNamesList.size() > 0) {
                                this.jumpGroupNamesList.remove(this.jumpGroupNamesList.get(0));
                            }
                        }
                        return;
                    }
                    LogUtils.log("WS_BABY.st.2");
                } catch (Exception e) {
                    LogUtils.log("WS_BABY.st.3");
                }
            }
        }
    }

    public void initSelectNickName() {
        if (MyApplication.enforceable) {
            try {
                if (this.jumpGroupNamesList == null || this.jumpGroupNamesList.size() == 0) {
                    removeEndView(this.mMyManager);
                }
                LogUtils.log("WS_BABY_" + this.jumpGroupNames.toString());
                new PerformClickUtils2().check(this.autoService, list_select_search_id, this.jumpTime, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        Iterator it = GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.iterator();
                        if (it.hasNext()) {
                            final String str = (String) it.next();
                            LogUtils.log("WS_BABY_SelectContactUI_2");
                            PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.list_select_search_id, str);
                            GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.remove(str);
                            new PerformClickUtils2().check(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.list_select_name_id, 600, 200, 15, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            LogUtils.log("WS_BABY_SelectContactUI_3");
                                            AccessibilityNodeInfo rootInActiveWindow = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_3_nufind");
                                                    if (GroupSendPublicNumberToGroupsUtils.this.recordNum == GroupSendPublicNumberToGroupsUtils.this.maxNum || GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                        if (GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                            boolean unused = GroupSendPublicNumberToGroupsUtils.this.isFilled = true;
                                                        }
                                                        GroupSendPublicNumberToGroupsUtils.this.searchEnd();
                                                        return;
                                                    }
                                                    GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                                                    return;
                                                }
                                                boolean z = true;
                                                boolean z2 = false;
                                                int i = 0;
                                                while (z && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                    int i2 = i + 1;
                                                    if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_4_" + accessibilityNodeInfo.getText() + "");
                                                        String str = "";
                                                        try {
                                                            str = accessibilityNodeInfo.getText() + "";
                                                        } catch (Exception e) {
                                                        }
                                                        if (str.trim().equals(str.trim())) {
                                                            GroupSendPublicNumberToGroupsUtils.access$608(GroupSendPublicNumberToGroupsUtils.this);
                                                            LogUtils.log("WS_BABY_SelectContactUI_5");
                                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                                            GroupSendPublicNumberToGroupsUtils.access$1608(GroupSendPublicNumberToGroupsUtils.this);
                                                            if (GroupSendPublicNumberToGroupsUtils.this.recordNum == GroupSendPublicNumberToGroupsUtils.this.maxNum || GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                                if (GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                                    boolean unused2 = GroupSendPublicNumberToGroupsUtils.this.isFilled = true;
                                                                }
                                                                GroupSendPublicNumberToGroupsUtils.this.searchEnd();
                                                            } else {
                                                                GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                                                            }
                                                            z = false;
                                                            z2 = true;
                                                            i = i2;
                                                        }
                                                    }
                                                    i = i2;
                                                }
                                                if (!z2) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_2_nufind");
                                                    if (GroupSendPublicNumberToGroupsUtils.this.recordNum == GroupSendPublicNumberToGroupsUtils.this.maxNum || GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                        if (GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                                            boolean unused3 = GroupSendPublicNumberToGroupsUtils.this.isFilled = true;
                                                        }
                                                        GroupSendPublicNumberToGroupsUtils.this.searchEnd();
                                                        return;
                                                    }
                                                    GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                                                }
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_SelectContactUI_4_nufind");
                                    if (GroupSendPublicNumberToGroupsUtils.this.recordNum == GroupSendPublicNumberToGroupsUtils.this.maxNum || GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                        if (GroupSendPublicNumberToGroupsUtils.this.jumpGroupNamesList.size() == 0) {
                                            boolean unused = GroupSendPublicNumberToGroupsUtils.this.isFilled = true;
                                        }
                                        GroupSendPublicNumberToGroupsUtils.this.searchEnd();
                                        return;
                                    }
                                    GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                                }
                            });
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_SelectContactUI_5_nufind");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initStartExecuteSend() {
        LogUtils.log("WS_BABY_executeMain2");
        this.isCanExecuteSelect = true;
        this.isWhile = true;
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.right_more_id);
                new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "推荐给朋友", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "推荐给朋友");
                        new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "多选", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "多选");
                                if (GroupSendPublicNumberToGroupsUtils.this.sendType == 1) {
                                    GroupSendPublicNumberToGroupsUtils.this.initSelectNickName();
                                } else {
                                    new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "更多联系人", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "更多联系人");
                                            new PerformClickUtils2().checkString(GroupSendPublicNumberToGroupsUtils.this.autoService, "选择群聊", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, "选择群聊");
                                                    if (GroupSendPublicNumberToGroupsUtils.this.sendType == 0) {
                                                        GroupSendPublicNumberToGroupsUtils.this.excSelectTask();
                                                    } else if (GroupSendPublicNumberToGroupsUtils.this.sendType != 1 && GroupSendPublicNumberToGroupsUtils.this.sendType == 2) {
                                                        GroupSendPublicNumberToGroupsUtils.this.excSelectTask();
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
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.pullResult == null || "".equals(this.pullResult)) {
            StringBuilder sb = new StringBuilder();
            sb.append("本次共向");
            sb.append(this.pullRecordNum - 1);
            sb.append("个微信群，发起了群发");
            sb.append(this.otherSendTypeName);
            sb.append("！");
            updateMiddleText(this.mMyManager, "自动分批群发" + this.otherSendTypeName + "", sb.toString());
            return;
        }
        updateMiddleText(this.mMyManager, "自动分批群发" + this.otherSendTypeName + "", this.pullResult);
    }

    public void saveRecord(int i) {
        if (this.otherSendType == 0) {
            if (this.sendType == 0) {
                SPUtils.put(this.autoService, "public_number_to_group_num_all", Integer.valueOf(i));
            } else if (this.sendType == 1) {
                SPUtils.put(this.autoService, "public_number_to_group_num_part", Integer.valueOf(i));
            } else if (this.sendType == 2) {
                SPUtils.put(this.autoService, "public_number_to_group_num_shield", Integer.valueOf(i));
            }
        } else if (this.sendType == 0) {
            SPUtils.put(this.autoService, "mini_programs_to_group_num_all", Integer.valueOf(i));
        } else if (this.sendType == 1) {
            SPUtils.put(this.autoService, "mini_programs_to_group_num_part", Integer.valueOf(i));
        } else if (this.sendType == 2) {
            SPUtils.put(this.autoService, "mini_programs_to_group_num_shield", Integer.valueOf(i));
        }
    }

    public void searchEnd() {
        if (MyApplication.enforceable) {
            this.recordNum = 0;
            LogUtils.log("WS_BABY_SelectContactUI_22222");
            sleep(10);
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("正在分批群发 ");
            sb.append(this.otherSendTypeName);
            sb.append("\n已向 ");
            sb.append(this.pullRecordNum - 1);
            sb.append(" 个微信群，发起群发。");
            updateBottomText(myWindowManager, sb.toString());
            PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
            LogUtils.log("WS_BABY_SelectContactUI_6");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, toast_edit_id, dialog_ok_id, 100, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (GroupSendPublicNumberToGroupsUtils.this.sayContent != null && !GroupSendPublicNumberToGroupsUtils.this.sayContent.equals("")) {
                        LogUtils.log("WS_BABY_SelectContactUI_7");
                        GroupSendPublicNumberToGroupsUtils.this.sleep(10);
                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendPublicNumberToGroupsUtils.this.sayContent);
                    }
                    GroupSendPublicNumberToGroupsUtils.this.sleep(10);
                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    LogUtils.log("WS_BABY_SelectContactUI_8");
                    new PerformClickUtils2().checkNodeId(GroupSendPublicNumberToGroupsUtils.this.autoService, BaseServiceUtils.contact_list_img_id, SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            GroupSendPublicNumberToGroupsUtils.this.searchStartNode();
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                }
            });
        }
    }

    public void searchStartNode() {
        try {
            if (MyApplication.enforceable) {
                this.isShowMiddleView = true;
                if (this.isFilled) {
                    saveRecord(1);
                    removeEndView(this.mMyManager);
                    return;
                }
                MyWindowManager myWindowManager = this.mMyManager;
                StringBuilder sb = new StringBuilder();
                sb.append("正在分批群发 ");
                sb.append(this.otherSendTypeName);
                sb.append("\n已向 ");
                sb.append(this.pullRecordNum - 1);
                sb.append(" 个微信群，发起群发。\n若出现卡顿，请耐心等待几秒..");
                updateBottomText(myWindowManager, sb.toString());
                saveRecord(this.pullRecordNum);
                new PerformClickUtils2().delay(100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_MAIN.1");
                        if (GroupSendPublicNumberToGroupsUtils.this.spaceTime <= 0 || GroupSendPublicNumberToGroupsUtils.this.isFirstExecute) {
                            boolean unused = GroupSendPublicNumberToGroupsUtils.this.isFirstExecute = false;
                            LogUtils.log("WS_BABY_executeMain4");
                            GroupSendPublicNumberToGroupsUtils.this.startSend();
                            return;
                        }
                        LogUtils.log("WS_BABY_executeMain2");
                        new PerformClickUtils2().countDown2(GroupSendPublicNumberToGroupsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void end() {
                                LogUtils.log("WS_BABY_executeMain3");
                                GroupSendPublicNumberToGroupsUtils.this.startSend();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = GroupSendPublicNumberToGroupsUtils.this.mMyManager;
                                StringBuilder sb = new StringBuilder();
                                sb.append("已向 ");
                                sb.append(GroupSendPublicNumberToGroupsUtils.this.pullRecordNum - 1);
                                sb.append(" 个微信群，发起群发。\n若出现卡顿，请耐心等待..\n正在延时等待，倒计时 ");
                                sb.append(i);
                                sb.append(" 秒");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startSend() {
        new PerformClickUtils2().check(this.autoService, contact_list_img_id, 10, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                boolean z;
                LogUtils.log("WS_BABY_MAIN.3");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = GroupSendPublicNumberToGroupsUtils.this.autoService.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_list_img_id);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    LogUtils.log("WS_BABY_MAIN.4");
                    int size = findAccessibilityNodeInfosByViewId.size();
                    while (true) {
                        if (size <= 0) {
                            z = false;
                            break;
                        }
                        int i2 = size - 1;
                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i2);
                        if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 0) {
                            List unused = GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText("公众号名片");
                        } else if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 1) {
                            List unused2 = GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText("小程序");
                        }
                        if (GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo != null && GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo.size() > 0) {
                            LogUtils.log("WS_BABY_000000000has");
                            boolean unused3 = GroupSendPublicNumberToGroupsUtils.this.isCanExecuteSelect = true;
                            boolean unused4 = GroupSendPublicNumberToGroupsUtils.this.isWhile = true;
                            LogUtils.log("WS_BABY_executeMain3");
                            PerformClickUtils.longClick((AccessibilityNodeInfo) GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo.get(0), new PerformClickUtils.OnLongClickListener() {
                                public void execute6() {
                                    LogUtils.log("WS_BABY_executeMain_phone7-");
                                    PerformClickUtils.performLongClick((AccessibilityNodeInfo) GroupSendPublicNumberToGroupsUtils.this.accessibilityNodeInfo.get(0));
                                    GroupSendPublicNumberToGroupsUtils.this.executeStartSend();
                                }

                                public void execute7() {
                                    LogUtils.log("WS_BABY_executeMain_phone7+");
                                    GroupSendPublicNumberToGroupsUtils.this.executeStartSend();
                                }
                            });
                            z = true;
                            break;
                        }
                        size = i2;
                    }
                    if (!z) {
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                boolean unused = GroupSendPublicNumberToGroupsUtils.this.isShowMiddleView = false;
                                BaseServiceUtils.removeEndView(GroupSendPublicNumberToGroupsUtils.this.mMyManager);
                                GroupSendPublicNumberToGroupsUtils.this.mMyManager.showStartView();
                                GroupSendPublicNumberToGroupsUtils.this.mMyManager.showBackView();
                                GroupSendPublicNumberToGroupsUtils.this.mMyManager.removeBottomView();
                                if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 0) {
                                    GroupSendPublicNumberToGroupsUtils.this.mMyManager.toastToUserError("没有找到要转发的公众号\n请将要转发的公众号,放在聊天页的底部");
                                } else if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 1) {
                                    GroupSendPublicNumberToGroupsUtils.this.mMyManager.toastToUserError("没有找到要转发的小程序\n请将要转发的小程序,放在聊天页的底部");
                                }
                            }
                        });
                    }
                }
            }

            public void nuFind() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        boolean unused = GroupSendPublicNumberToGroupsUtils.this.isShowMiddleView = false;
                        BaseServiceUtils.removeEndView(GroupSendPublicNumberToGroupsUtils.this.mMyManager);
                        GroupSendPublicNumberToGroupsUtils.this.mMyManager.showStartView();
                        GroupSendPublicNumberToGroupsUtils.this.mMyManager.showBackView();
                        GroupSendPublicNumberToGroupsUtils.this.mMyManager.removeBottomView();
                        if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 0) {
                            GroupSendPublicNumberToGroupsUtils.this.mMyManager.toastToUserError("没有找到要转发的公众号\n请将要转发的公众号,放在聊天页的底部");
                        } else if (GroupSendPublicNumberToGroupsUtils.this.otherSendType == 1) {
                            GroupSendPublicNumberToGroupsUtils.this.mMyManager.toastToUserError("没有找到要转发的小程序\n请将要转发的小程序,放在聊天页的底部");
                        }
                    }
                });
            }
        });
    }
}
