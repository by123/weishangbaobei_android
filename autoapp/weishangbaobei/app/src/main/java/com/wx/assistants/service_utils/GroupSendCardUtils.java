package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GroupSendCardUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCardUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public String cardName = "";
    /* access modifiers changed from: private */
    public int circulateInnerSize = 1;
    private int circulateMode = 0;
    private int circulateOuterSize = 1;
    /* access modifiers changed from: private */
    public List<String> groupList = new ArrayList();
    List<AccessibilityNodeInfo> groupNames = null;
    private boolean isFirstDelay = true;
    private boolean isScrollBottom = false;
    private String jumpGroupName = "";
    /* access modifiers changed from: private */
    public int jumpNum = 0;
    /* access modifiers changed from: private */
    public List<String> jumpStartLists = new ArrayList();
    private String lastName;
    private String nowName = "";
    private OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendGroupNum = 0;
    private String sendResult = "";
    private List<String> shieldLists = new ArrayList();
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(GroupSendCardUtils groupSendCardUtils) {
        int i = groupSendCardUtils.sendGroupNum;
        groupSendCardUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(GroupSendCardUtils groupSendCardUtils) {
        int i = groupSendCardUtils.startIndex;
        groupSendCardUtils.startIndex = i + 1;
        return i;
    }

    private GroupSendCardUtils() {
    }

    public static GroupSendCardUtils getInstance() {
        instance = new GroupSendCardUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.startIndex = 0;
        this.sendGroupNum = 0;
        this.lastName = "";
        this.operationParameterModel = operationParameterModel2;
        this.cardName = operationParameterModel2.getCardName();
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.groupList = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.beforeLists = new ArrayList();
        this.shieldLists = new ArrayList();
        this.jumpNum = 0;
        this.scrollCount = 0;
        this.nowName = "";
        this.isScrollBottom = false;
        this.isFirstDelay = true;
        if (this.operationParameterModel.getSendCardType() != 0 && this.jumpGroupName != null && !"".equals(this.jumpGroupName)) {
            this.groupList = new ArrayList();
            if (this.jumpGroupName.contains(";")) {
                String[] split = this.jumpGroupName.split(";");
                for (int i = 0; i < split.length; i++) {
                    if (split[i] != null && !split[i].equals("")) {
                        this.groupList.add(split[i]);
                    }
                }
                return;
            }
            this.groupList.add(this.jumpGroupName);
        }
    }

    public void initRoomContact() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_sendGroupNum = " + GroupSendCardUtils.this.sendGroupNum + ",jumpNum= " + GroupSendCardUtils.this.jumpNum + ",startIndexFromUser = " + GroupSendCardUtils.this.startIndexFromUser + "");
                if (GroupSendCardUtils.this.sendGroupNum + GroupSendCardUtils.this.jumpNum + GroupSendCardUtils.this.startIndexFromUser <= 1 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_initRoomContact_从1开始");
                    GroupSendCardUtils.this.executeSendGroup(GroupSendCardUtils.this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact_从" + (GroupSendCardUtils.this.sendGroupNum + GroupSendCardUtils.this.jumpNum + GroupSendCardUtils.this.startIndexFromUser) + "开始");
                GroupSendCardUtils.this.jumpStartLists.clear();
                int unused = GroupSendCardUtils.this.scrollCount = 0;
                int unused2 = GroupSendCardUtils.this.startIndex = 0;
                GroupSendCardUtils.this.jumpStartPosition();
            }
        });
    }

    public void jumpStartPosition() {
        if (this.groupNames == null) {
            this.groupNames = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, group_list_item_name_id);
            this.isScrollBottom = PerformClickUtils.findNodeGroupBottom(this.autoService);
        }
        if (this.groupNames != null && this.groupNames.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.groupNames.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = this.groupNames.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.nowName = accessibilityNodeInfo.getText() + "";
                }
                LogUtils.log("WS_BABY_initRoomContact.nowName = " + this.nowName + ",lastName = " + this.lastName);
                if ((this.lastName == null || "".equals(this.lastName)) && this.isScrollBottom) {
                    LogUtils.log("WS_BABY_initRoomContact.0");
                    try {
                        this.lastName = this.groupNames.get(this.groupNames.size() - 1).getText().toString();
                    } catch (Exception unused) {
                    }
                }
                if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                    LogUtils.log("WS_BABY_initRoomContact.2");
                    this.scrollCount++;
                } else {
                    LogUtils.log("WS_BABY_initRoomContact.1");
                }
                LogUtils.log("WS_BABY_initRoomContact.@" + this.scrollCount + "@" + (this.sendGroupNum + this.startIndexFromUser));
                if (this.scrollCount == this.sendGroupNum + this.jumpNum + this.startIndexFromUser) {
                    LogUtils.log("WS_BABY_initRoomContact.3.groupList = " + this.groupList);
                    this.groupNames = null;
                    executeSendGroup(this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact.4");
                this.jumpStartLists.add(this.nowName);
                this.startIndex++;
                jumpStartPosition();
            } else if (this.startIndex >= this.groupNames.size() && MyApplication.enforceable) {
                this.groupNames = null;
                LogUtils.log("WS_BABY_initRoomContact.5.nowname = " + this.nowName + ",lastname =" + this.lastName + ",isscrollbottom = " + this.isScrollBottom);
                if (!this.nowName.equals(this.lastName) || !this.isScrollBottom || !MyApplication.enforceable) {
                    PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                    new PerformClickUtils2().check(this.autoService, group_list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            int unused = GroupSendCardUtils.this.startIndex = 1;
                            GroupSendCardUtils.this.jumpStartPosition();
                        }
                    });
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact.END");
                if (this.circulateMode == 0) {
                    this.circulateOuterSize--;
                    if (this.circulateOuterSize <= 0) {
                        this.isScrollBottom = false;
                        if (this.sendGroupNum == 0) {
                            this.sendResult = "你设置的开始起点:" + this.startIndexFromUser + " 高于群数量";
                        }
                        saveRecord(1);
                        removeEndView(this.mMyManager);
                        return;
                    }
                    saveRecord(1);
                    this.sendGroupNum = 0;
                    this.jumpNum = 0;
                    this.startIndexFromUser = 1;
                    this.operationParameterModel.setStartIndex(1);
                    this.jumpStartLists = new ArrayList();
                    this.beforeLists = new ArrayList();
                    this.shieldLists = new ArrayList();
                    this.scrollCount = 0;
                    this.startIndex = 0;
                    this.isScrollBottom = false;
                    this.isFirstDelay = true;
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendCardUtils.this.executeMain();
                        }
                    });
                    return;
                }
                this.isScrollBottom = false;
                if (this.sendGroupNum == 0) {
                    this.sendResult = "你设置的开始起点:" + this.startIndexFromUser + " 高于群数量";
                }
                saveRecord(1);
                removeEndView(this.mMyManager);
            }
        }
    }

    public void executeSendGroup(final List<String> list) {
        LogUtils.log("executeSendGroup.start");
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, group_list_item_name_id);
        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_initRoomContact.6.startIndex = " + this.startIndex);
            if (this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(this.startIndex);
                if (accessibilityNodeInfo != null) {
                    if (accessibilityNodeInfo.getText() != null) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
                    }
                    LogUtils.log("WS_BABY_initRoomContact.7");
                    if (this.beforeLists != null && this.beforeLists.size() > 30) {
                        for (int i = 0; i < 5; i++) {
                            this.beforeLists.remove(0);
                        }
                    }
                    this.isScrollBottom = PerformClickUtils.findNodeGroupBottom(this.autoService);
                    if ((this.lastName == null || "".equals(this.lastName)) && this.isScrollBottom) {
                        try {
                            this.lastName = findViewIdList.get(findViewIdList.size() - 1).getText().toString();
                        } catch (Exception unused) {
                        }
                    }
                    if (this.operationParameterModel.getSendCardType() == 1) {
                        if (this.isScrollBottom && this.beforeLists.contains(this.nowName)) {
                            LogUtils.log("WS_BABY_initRoomContact.beforeLists.contains." + this.nowName);
                            LogUtils.log("WS_BABY_initRoomContact.9");
                            this.startIndex = this.startIndex + 1;
                            executeSendGroup(list);
                        } else if (list == null || list.size() <= 0 || !list.contains(this.nowName)) {
                            LogUtils.log("WS_BABY_initRoomContact.8");
                            this.startIndex++;
                            if (!this.shieldLists.contains(this.nowName)) {
                                this.jumpNum++;
                                this.shieldLists.add(this.nowName);
                                updateBottom(0);
                            }
                            executeSendGroup(list);
                        } else {
                            LogUtils.log("WS_BABY_initRoomContact.10.sendCount = " + this.sendGroupNum);
                            this.beforeLists.add(this.nowName);
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            initFirstChattingUI();
                        }
                    } else if (list != null && list.size() > 0 && list.contains(this.nowName)) {
                        LogUtils.log("WS_BABY_initRoomContact.8");
                        this.startIndex++;
                        if (!this.shieldLists.contains(this.nowName)) {
                            this.jumpNum++;
                            this.shieldLists.add(this.nowName);
                        }
                        updateBottom(0);
                        executeSendGroup(list);
                    } else if (!this.isScrollBottom || !this.beforeLists.contains(this.nowName)) {
                        LogUtils.log("WS_BABY_initRoomContact.10.sendCount = " + this.sendGroupNum);
                        this.beforeLists.add(this.nowName);
                        PerformClickUtils.performClick(accessibilityNodeInfo);
                        initFirstChattingUI();
                    } else {
                        LogUtils.log("WS_BABY_initRoomContact.beforeLists.contains." + this.nowName);
                        LogUtils.log("WS_BABY_initRoomContact.9");
                        this.startIndex = this.startIndex + 1;
                        executeSendGroup(list);
                    }
                }
            } else if (this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                if (!this.nowName.equals(this.lastName) || !this.isScrollBottom || !MyApplication.enforceable) {
                    PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                    new PerformClickUtils2().check(this.autoService, group_list_item_name_id, 200, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            int unused = GroupSendCardUtils.this.startIndex = 1;
                            GroupSendCardUtils.this.executeSendGroup(list);
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                        }
                    });
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact.END");
                if (this.circulateMode == 0) {
                    this.circulateOuterSize--;
                    if (this.circulateOuterSize <= 0) {
                        this.isScrollBottom = false;
                        if (this.sendGroupNum == 0) {
                            this.sendResult = "你设置的开始起点:" + this.startIndexFromUser + " 高于群数量";
                        }
                        saveRecord(1);
                        removeEndView(this.mMyManager);
                        return;
                    }
                    saveRecord(1);
                    this.sendGroupNum = 0;
                    this.jumpNum = 0;
                    this.startIndexFromUser = 1;
                    this.operationParameterModel.setStartIndex(1);
                    this.jumpStartLists = new ArrayList();
                    this.beforeLists = new ArrayList();
                    this.shieldLists = new ArrayList();
                    this.scrollCount = 0;
                    this.startIndex = 0;
                    this.isScrollBottom = false;
                    this.isFirstDelay = true;
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendCardUtils.this.executeMain();
                        }
                    });
                    return;
                }
                this.isScrollBottom = false;
                if (this.sendGroupNum == 0) {
                    this.sendResult = "你设置的开始起点:" + this.startIndexFromUser + " 高于群数量";
                }
                saveRecord(1);
                removeEndView(this.mMyManager);
            }
        }
    }

    public void initSelectContactUI() {
        try {
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_SelectContactUI_2");
                    PerformClickUtils.findViewByIdAndPasteContent(GroupSendCardUtils.this.autoService, BaseServiceUtils.list_select_search_id, GroupSendCardUtils.this.cardName);
                    new PerformClickUtils2().checkNilNodeId(GroupSendCardUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, 150, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            new PerformClickUtils2().checkNodeHasSomeOne(GroupSendCardUtils.this.autoService, "最常使用", BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 40, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            boolean z;
                                            try {
                                                LogUtils.log("WS_BABY_SelectContactUI_3");
                                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCardUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                        z = true;
                                                    } else {
                                                        z = true;
                                                        int i = 0;
                                                        while (z && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                            i++;
                                                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                                String str = "";
                                                                try {
                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                } catch (Exception unused) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                }
                                                                if (GroupSendCardUtils.this.cardName.trim().equalsIgnoreCase(str.trim())) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                                                    PerformClickUtils.performClick2(accessibilityNodeInfo);
                                                                    z = false;
                                                                } else {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_7");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (z && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_9");
                                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendCardUtils.this.autoService.getRootInActiveWindow();
                                                        if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.search_card_wxh);
                                                            if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_14-null");
                                                            } else {
                                                                int i2 = 0;
                                                                while (z && i2 < findAccessibilityNodeInfosByViewId2.size() && MyApplication.enforceable) {
                                                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(i2);
                                                                    i2++;
                                                                    if (accessibilityNodeInfo2 != null && MyApplication.enforceable) {
                                                                        String str2 = accessibilityNodeInfo2.getText() + "";
                                                                        LogUtils.log("WS_BABY_SelectContactUI10_" + str2);
                                                                        if (str2 != null && str2.contains(":")) {
                                                                            str2 = str2.split(":")[1];
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI11_" + str2);
                                                                        if (GroupSendCardUtils.this.cardName.trim().equalsIgnoreCase(str2.trim())) {
                                                                            LogUtils.log("WS_BABY_SelectContactUI12_" + str2);
                                                                            PerformClickUtils.performClick2(accessibilityNodeInfo2);
                                                                            z = false;
                                                                        } else {
                                                                            LogUtils.log("WS_BABY_SelectContactUI_13");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (z || !MyApplication.enforceable) {
                                                        BaseServiceUtils.updateBottomText(GroupSendCardUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称/微信号是否正确");
                                                        return;
                                                    }
                                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                                    new PerformClickUtils2().checkNodeAllIds(GroupSendCardUtils.this.autoService, BaseServiceUtils.toast_edit_id, BaseServiceUtils.dialog_ok_id, 20, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            int i2;
                                                            if (GroupSendCardUtils.this.sayContent != null && !GroupSendCardUtils.this.sayContent.equals("")) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_7");
                                                                GroupSendCardUtils.this.sleep(10);
                                                                PerformClickUtils.findViewByIdAndPasteContent(GroupSendCardUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendCardUtils.this.sayContent);
                                                            }
                                                            GroupSendCardUtils.this.sleep(50);
                                                            PerformClickUtils.findViewIdAndClick(GroupSendCardUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                            LogUtils.log("WS_BABY_SelectContactUI_8");
                                                            if (GroupSendCardUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                                GroupSendCardUtils.access$508(GroupSendCardUtils.this);
                                                                GroupSendCardUtils.access$008(GroupSendCardUtils.this);
                                                                if (GroupSendCardUtils.this.startIndexFromUser == 1) {
                                                                    i2 = GroupSendCardUtils.this.sendGroupNum + 1 + GroupSendCardUtils.this.jumpNum;
                                                                } else {
                                                                    i2 = GroupSendCardUtils.this.sendGroupNum + GroupSendCardUtils.this.jumpNum + GroupSendCardUtils.this.startIndexFromUser;
                                                                }
                                                                GroupSendCardUtils.this.updateBottom(0);
                                                                GroupSendCardUtils.this.saveRecord(i2);
                                                            }
                                                            GroupSendCardUtils.this.initBackChattingUI();
                                                        }

                                                        public void nuFind() {
                                                            BaseServiceUtils.updateBottomText(GroupSendCardUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称/微信号是否存在");
                                                        }
                                                    });
                                                }
                                            } catch (Exception unused2) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    BaseServiceUtils.updateBottomText(GroupSendCardUtils.this.mMyManager, "没有找到要发送的名片\n请确认名片昵称或微信号是否正确");
                                }
                            });
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatRoomContactUI() {
        try {
            this.circulateMode = this.operationParameterModel.getCirculateMode();
            this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
            if (this.spaceTime <= 0 || this.isFirstDelay) {
                this.isFirstDelay = false;
                initRoomContact();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void surplus(int i) {
                    GroupSendCardUtils.this.updateBottom(i);
                }

                public void end() {
                    GroupSendCardUtils.this.updateBottom(0);
                    GroupSendCardUtils.this.initRoomContact();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY.ChattingUI_1");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_2");
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCardUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY.ChattingUI_3");
                            PerformClickUtils.inputText(GroupSendCardUtils.this.autoService, "");
                            GroupSendCardUtils.this.clickCard();
                            return;
                        }
                        LogUtils.log("WS_BABY.ChattingUI_4");
                        GroupSendCardUtils.this.clickCard();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.ChattingUI_5");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ChattingUI_6");
            }
        });
    }

    public void clickCard() {
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.ChattingUI_8");
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCardUtils.this.autoService, "名片", BaseServiceUtils.album_id);
                GroupSendCardUtils.this.initSelectContactUI();
            }
        });
    }

    public void initBackChattingUI() {
        this.circulateInnerSize--;
        updateBottom(0);
        if (this.circulateInnerSize == 0 || !MyApplication.enforceable) {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    LogUtils.log("WS_BABY.ChattingUI_BACK");
                    GroupSendCardUtils.this.executeMain();
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChattingUI_10");
        initFirstChattingUI();
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendCardUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCardUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCardUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendCardUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendCardUtils.this.autoService, "群聊");
                            GroupSendCardUtils.this.initChatRoomContactUI();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "card_to_group_num_shield", Integer.valueOf(i));
        }
    }

    public void updateBottom(int i) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        int circulateOutSize = this.operationParameterModel.getCirculateOutSize();
        if (this.circulateMode != 0) {
            int circulateInnerSize2 = this.operationParameterModel.getCirculateInnerSize();
            if (circulateInnerSize2 > 1) {
                if (this.operationParameterModel.getSendCardType() == 0) {
                    MyWindowManager myWindowManager = this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("从第 ");
                    sb.append(this.startIndexFromUser);
                    sb.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                    sb.append(this.sendGroupNum);
                    sb.append(" 个群，内循环 ");
                    sb.append(circulateInnerSize2 - this.circulateInnerSize);
                    sb.append(" 次");
                    if (i > 0) {
                        str6 = "，延迟等待：" + i + " 秒";
                    } else {
                        str6 = "";
                    }
                    sb.append(str6);
                    showBottomView(myWindowManager, sb.toString());
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    MyWindowManager myWindowManager2 = this.mMyManager;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("从第 ");
                    sb2.append(this.startIndexFromUser);
                    sb2.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                    sb2.append(this.sendGroupNum);
                    sb2.append(" 个群，已屏蔽 ");
                    sb2.append(this.jumpNum);
                    sb2.append(" 个群\n内循环 ");
                    sb2.append(circulateInnerSize2 - this.circulateInnerSize);
                    sb2.append(" 次");
                    if (i > 0) {
                        str5 = "，延迟等待：" + i + " 秒";
                    } else {
                        str5 = "";
                    }
                    sb2.append(str5);
                    showBottomView(myWindowManager2, sb2.toString());
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    MyWindowManager myWindowManager3 = this.mMyManager;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("从第 ");
                    sb3.append(this.startIndexFromUser);
                    sb3.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                    sb3.append(this.sendGroupNum);
                    sb3.append(" 个群，已屏蔽 ");
                    sb3.append(this.jumpNum);
                    sb3.append(" 个群\n内循环 ");
                    sb3.append(circulateInnerSize2 - this.circulateInnerSize);
                    sb3.append(" 次");
                    if (i > 0) {
                        str4 = "，延迟等待：" + i + " 秒";
                    } else {
                        str4 = "";
                    }
                    sb3.append(str4);
                    showBottomView(myWindowManager3, sb3.toString());
                }
            } else if (this.operationParameterModel.getSendCardType() == 0) {
                MyWindowManager myWindowManager4 = this.mMyManager;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("从第 ");
                sb4.append(this.startIndexFromUser);
                sb4.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb4.append(this.sendGroupNum);
                sb4.append(" 个群");
                if (i > 0) {
                    str3 = "，延迟等待：" + i + " 秒";
                } else {
                    str3 = "";
                }
                sb4.append(str3);
                showBottomView(myWindowManager4, sb4.toString());
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                MyWindowManager myWindowManager5 = this.mMyManager;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("从第 ");
                sb5.append(this.startIndexFromUser);
                sb5.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb5.append(this.sendGroupNum);
                sb5.append(" 个群，已屏蔽 ");
                sb5.append(this.jumpNum);
                sb5.append(" 个群");
                if (i > 0) {
                    str2 = "，延迟等待：" + i + " 秒";
                } else {
                    str2 = "";
                }
                sb5.append(str2);
                showBottomView(myWindowManager5, sb5.toString());
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                MyWindowManager myWindowManager6 = this.mMyManager;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("从第 ");
                sb6.append(this.startIndexFromUser);
                sb6.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb6.append(this.sendGroupNum);
                sb6.append(" 个群，已屏蔽 ");
                sb6.append(this.jumpNum);
                sb6.append(" 个群");
                if (i > 0) {
                    str = "，延迟等待：" + i + " 秒";
                } else {
                    str = "";
                }
                sb6.append(str);
                showBottomView(myWindowManager6, sb6.toString());
            }
        } else if (circulateOutSize > 1) {
            if (this.operationParameterModel.getSendCardType() == 0) {
                MyWindowManager myWindowManager7 = this.mMyManager;
                StringBuilder sb7 = new StringBuilder();
                sb7.append("从第 ");
                sb7.append(this.startIndexFromUser);
                sb7.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb7.append(this.sendGroupNum);
                sb7.append(" 个群，外循环第 ");
                if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                    circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                }
                sb7.append(circulateOutSize);
                sb7.append(" 轮");
                if (i > 0) {
                    str12 = "，延迟等待：" + i + " 秒";
                } else {
                    str12 = "";
                }
                sb7.append(str12);
                showBottomView(myWindowManager7, sb7.toString());
            } else if (this.operationParameterModel.getSendCardType() == 1) {
                MyWindowManager myWindowManager8 = this.mMyManager;
                StringBuilder sb8 = new StringBuilder();
                sb8.append("从第 ");
                sb8.append(this.startIndexFromUser);
                sb8.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb8.append(this.sendGroupNum);
                sb8.append(" 个群，已屏蔽 ");
                sb8.append(this.jumpNum);
                sb8.append(" 个群\n外循环第 ");
                if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                    circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                }
                sb8.append(circulateOutSize);
                sb8.append(" 轮");
                if (i > 0) {
                    str11 = "，延迟等待：" + i + " 秒";
                } else {
                    str11 = "";
                }
                sb8.append(str11);
                showBottomView(myWindowManager8, sb8.toString());
            } else if (this.operationParameterModel.getSendCardType() == 2) {
                MyWindowManager myWindowManager9 = this.mMyManager;
                StringBuilder sb9 = new StringBuilder();
                sb9.append("从第 ");
                sb9.append(this.startIndexFromUser);
                sb9.append(" 个开始群发名片，请不要操作微信\n已发送 ");
                sb9.append(this.sendGroupNum);
                sb9.append(" 个群，已屏蔽 ");
                sb9.append(this.jumpNum);
                sb9.append(" 个群\n外循环第 ");
                if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                    circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                }
                sb9.append(circulateOutSize);
                sb9.append(" 轮");
                if (i > 0) {
                    str10 = "，延迟等待：" + i + " 秒";
                } else {
                    str10 = "";
                }
                sb9.append(str10);
                showBottomView(myWindowManager9, sb9.toString());
            }
        } else if (this.operationParameterModel.getSendCardType() == 0) {
            MyWindowManager myWindowManager10 = this.mMyManager;
            StringBuilder sb10 = new StringBuilder();
            sb10.append("从第 ");
            sb10.append(this.startIndexFromUser);
            sb10.append(" 个开始群发名片，请不要操作微信\n已发送 ");
            sb10.append(this.sendGroupNum);
            sb10.append(" 个群");
            if (i > 0) {
                str9 = "，延迟等待：" + i + " 秒";
            } else {
                str9 = "";
            }
            sb10.append(str9);
            showBottomView(myWindowManager10, sb10.toString());
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            MyWindowManager myWindowManager11 = this.mMyManager;
            StringBuilder sb11 = new StringBuilder();
            sb11.append("从第 ");
            sb11.append(this.startIndexFromUser);
            sb11.append(" 个开始群发名片，请不要操作微信\n已发送 ");
            sb11.append(this.sendGroupNum);
            sb11.append(" 个群，已屏蔽 ");
            sb11.append(this.jumpNum);
            sb11.append(" 个群");
            if (i > 0) {
                str8 = "，延迟等待：" + i + " 秒";
            } else {
                str8 = "";
            }
            sb11.append(str8);
            showBottomView(myWindowManager11, sb11.toString());
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            MyWindowManager myWindowManager12 = this.mMyManager;
            StringBuilder sb12 = new StringBuilder();
            sb12.append("从第 ");
            sb12.append(this.startIndexFromUser);
            sb12.append(" 个开始群发名片，请不要操作微信\n已发送 ");
            sb12.append(this.sendGroupNum);
            sb12.append(" 个群，已屏蔽 ");
            sb12.append(this.jumpNum);
            sb12.append(" 个群");
            if (i > 0) {
                str7 = "，延迟等待：" + i + " 秒";
            } else {
                str7 = "";
            }
            sb12.append(str7);
            showBottomView(myWindowManager12, sb12.toString());
        }
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "群发名片", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发名片结束", this.sendResult);
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                int circulateOutSize = this.operationParameterModel.getCirculateOutSize();
                if (this.circulateMode != 0 || circulateOutSize <= 1) {
                    if (this.operationParameterModel.getSendCardType() == 0) {
                        MyWindowManager myWindowManager = this.mMyManager;
                        showBottomView(myWindowManager, "正在群发名片[" + this.cardName + "]\n从第 " + this.startIndexFromUser + " 个微信群开始群发");
                    } else if (this.operationParameterModel.getSendCardType() == 1) {
                        MyWindowManager myWindowManager2 = this.mMyManager;
                        showBottomView(myWindowManager2, "正在群发名片[" + this.cardName + "]\n从第 " + this.startIndexFromUser + " 个微信群开始群发");
                    } else if (this.operationParameterModel.getSendCardType() == 2) {
                        MyWindowManager myWindowManager3 = this.mMyManager;
                        showBottomView(myWindowManager3, "正在群发名片[" + this.cardName + "]\n从第 " + this.startIndexFromUser + " 个微信群开始群发");
                    }
                } else if (this.operationParameterModel.getSendCardType() == 0) {
                    MyWindowManager myWindowManager4 = this.mMyManager;
                    StringBuilder sb = new StringBuilder();
                    sb.append("正在群发名片[");
                    sb.append(this.cardName);
                    sb.append("]\n从第");
                    sb.append(this.startIndexFromUser);
                    sb.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb.append(circulateOutSize);
                    sb.append("轮群发");
                    showBottomView(myWindowManager4, sb.toString());
                } else if (this.operationParameterModel.getSendCardType() == 1) {
                    MyWindowManager myWindowManager5 = this.mMyManager;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("正在群发名片[");
                    sb2.append(this.cardName);
                    sb2.append("]\n从第");
                    sb2.append(this.startIndexFromUser);
                    sb2.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb2.append(circulateOutSize);
                    sb2.append("轮群发");
                    showBottomView(myWindowManager5, sb2.toString());
                } else if (this.operationParameterModel.getSendCardType() == 2) {
                    MyWindowManager myWindowManager6 = this.mMyManager;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("正在群发名片[");
                    sb3.append(this.cardName);
                    sb3.append("]\n从第");
                    sb3.append(this.startIndexFromUser);
                    sb3.append("个微信群开始群发\n正在执行第");
                    if ((circulateOutSize - this.circulateOuterSize) + 1 <= circulateOutSize) {
                        circulateOutSize = (circulateOutSize - this.circulateOuterSize) + 1;
                    }
                    sb3.append(circulateOutSize);
                    sb3.append("轮群发");
                    showBottomView(myWindowManager6, sb3.toString());
                }
            } else {
                showBottomView(this.mMyManager, "正在向微信群群发名片\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendCardUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        try {
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showMiddleView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
