package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendCardToFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCardToFriendsUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public String cardName = "";
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> friendsList = new LinkedHashSet<>();
    private boolean isCanExecute = true;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    boolean jumpWhile = true;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String nowSearchName = "";
    /* access modifiers changed from: private */
    public int realExcFriendNum = 0;
    /* access modifiers changed from: private */
    public String recordNowName = "";
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private GroupSendCardToFriendsUtils() {
    }

    static /* synthetic */ int access$008(GroupSendCardToFriendsUtils groupSendCardToFriendsUtils) {
        int i = groupSendCardToFriendsUtils.realExcFriendNum;
        groupSendCardToFriendsUtils.realExcFriendNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(GroupSendCardToFriendsUtils groupSendCardToFriendsUtils) {
        int i = groupSendCardToFriendsUtils.startIndex;
        groupSendCardToFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1308(GroupSendCardToFriendsUtils groupSendCardToFriendsUtils) {
        int i = groupSendCardToFriendsUtils.scrollCount;
        groupSendCardToFriendsUtils.scrollCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendCardToFriendsUtils groupSendCardToFriendsUtils) {
        int i = groupSendCardToFriendsUtils.excFriendsNum;
        groupSendCardToFriendsUtils.excFriendsNum = i + 1;
        return i;
    }

    public static GroupSendCardToFriendsUtils getInstance() {
        instance = new GroupSendCardToFriendsUtils();
        return instance;
    }

    public void clickCard() {
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCardToFriendsUtils.this.autoService, "名片", BaseServiceUtils.album_id);
                GroupSendCardToFriendsUtils.this.initSelectContactUI();
            }

            public void nuFind() {
            }
        });
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            if (this.sendType == 0) {
                MyWindowManager myWindowManager = this.mMyManager;
                showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始群发名片");
            } else {
                showBottomView(this.mMyManager, "正在给微信好友发送名片");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (GroupSendCardToFriendsUtils.this.sendType == 0) {
                            GroupSendCardToFriendsUtils.this.executeAllMain(GroupSendCardToFriendsUtils.this.sendType);
                        } else if (GroupSendCardToFriendsUtils.this.sendType == 1) {
                            GroupSendCardToFriendsUtils.this.executePartMain();
                        } else if (GroupSendCardToFriendsUtils.this.sendType == 2) {
                            GroupSendCardToFriendsUtils.this.executeNoSendMain();
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAllMain(int i) {
        try {
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 10) + 400);
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_item_name_id, list_item_layout_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (GroupSendCardToFriendsUtils.this.startInt <= 1 || !GroupSendCardToFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        GroupSendCardToFriendsUtils.this.executeFriends();
                        return;
                    }
                    boolean unused = GroupSendCardToFriendsUtils.this.isJumpedStart = false;
                    LogUtils.log("WS_BABY_LauncherUI_从" + GroupSendCardToFriendsUtils.this.startInt + "开始" + GroupSendCardToFriendsUtils.this.jumpWhile);
                    GroupSendCardToFriendsUtils.this.jumpStartPosition();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeFriends() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_0");
            new PerformClickUtils2().checkNodeAllIdsAsy(this.autoService, list_item_layout_id, list_item_name_id, 10, 100, 50, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_1111");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_5555_" + GroupSendCardToFriendsUtils.this.startIndex);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_names_null");
                        return;
                    }
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_6666_" + findViewIdList.size());
                    if (GroupSendCardToFriendsUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(GroupSendCardToFriendsUtils.this.startIndex);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            LogUtils.log("WS_BABY.LauncherUI.executeFriends_name_" + str);
                            String unused = GroupSendCardToFriendsUtils.this.recordNowName = str;
                        }
                        GroupSendCardToFriendsUtils.access$1308(GroupSendCardToFriendsUtils.this);
                        if (GroupSendCardToFriendsUtils.this.beforeLists != null && GroupSendCardToFriendsUtils.this.beforeLists.size() > 50) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                GroupSendCardToFriendsUtils.this.beforeLists.remove(0);
                            }
                        }
                        if (GroupSendCardToFriendsUtils.this.isScrollBottom && GroupSendCardToFriendsUtils.this.beforeLists.contains(GroupSendCardToFriendsUtils.this.recordNowName)) {
                            GroupSendCardToFriendsUtils.access$1208(GroupSendCardToFriendsUtils.this);
                            GroupSendCardToFriendsUtils.this.executeFriends();
                        } else if ("微信团队".equals(GroupSendCardToFriendsUtils.this.recordNowName) || "文件传输助手".equals(GroupSendCardToFriendsUtils.this.recordNowName)) {
                            GroupSendCardToFriendsUtils.access$1208(GroupSendCardToFriendsUtils.this);
                            GroupSendCardToFriendsUtils.this.executeFriends();
                        } else if (GroupSendCardToFriendsUtils.this.sendType != 2 || GroupSendCardToFriendsUtils.this.friendsList == null || !GroupSendCardToFriendsUtils.this.friendsList.contains(GroupSendCardToFriendsUtils.this.recordNowName)) {
                            GroupSendCardToFriendsUtils.this.beforeLists.add(GroupSendCardToFriendsUtils.this.recordNowName);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || GroupSendCardToFriendsUtils.this.startIndex >= findViewIdList2.size()) {
                                GroupSendCardToFriendsUtils.access$1208(GroupSendCardToFriendsUtils.this);
                                GroupSendCardToFriendsUtils.this.executeFriends();
                                return;
                            }
                            final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(GroupSendCardToFriendsUtils.this.startIndex);
                            GroupSendCardToFriendsUtils.access$1208(GroupSendCardToFriendsUtils.this);
                            if (GroupSendCardToFriendsUtils.this.spaceTime <= 0 || GroupSendCardToFriendsUtils.this.realExcFriendNum <= 0) {
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                GroupSendCardToFriendsUtils.this.initContactInfoUI();
                                return;
                            }
                            new PerformClickUtils2().countDown2(GroupSendCardToFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    GroupSendCardToFriendsUtils.this.initContactInfoUI();
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = GroupSendCardToFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCardToFriendsUtils.this.realExcFriendNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                                }
                            });
                        } else {
                            GroupSendCardToFriendsUtils.access$1208(GroupSendCardToFriendsUtils.this);
                            if (GroupSendCardToFriendsUtils.this.sendType == 2) {
                                BaseServiceUtils.updateBottomText(GroupSendCardToFriendsUtils.this.mMyManager, "已跳过【 " + GroupSendCardToFriendsUtils.this.recordNowName + " 】");
                                GroupSendCardToFriendsUtils.this.sleep(100);
                            }
                            GroupSendCardToFriendsUtils.this.executeFriends();
                        }
                    } else if (GroupSendCardToFriendsUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                        PerformClickUtils.scroll(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                        new PerformClickUtils2().check(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (GroupSendCardToFriendsUtils.this.isScrollBottom) {
                                    BaseServiceUtils.removeEndView(GroupSendCardToFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean unused = GroupSendCardToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendCardToFriendsUtils.this.autoService, "位联系人");
                                int unused2 = GroupSendCardToFriendsUtils.this.startIndex = 1;
                                GroupSendCardToFriendsUtils.this.executeFriends();
                            }

                            public void nuFind() {
                            }
                        });
                    }
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_2222");
                }
            });
        }
    }

    public void executeNoSendMain() {
        executeAllMain(this.sendType);
    }

    public void executePartMain() {
        try {
            if (this.friendsList == null || this.friendsList.size() == 0) {
                removeEndView(this.mMyManager);
                return;
            }
            LogUtils.log("WS_BABY.LauncherUI.executePartMain");
            if (this.isCanExecute && MyApplication.enforceable) {
                if (this.isFirst && MyApplication.enforceable) {
                    this.isFirst = false;
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    sleep(200);
                }
                new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_viewgroup_id, contact_nav_search_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = GroupSendCardToFriendsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(GroupSendCardToFriendsUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            GroupSendCardToFriendsUtils.this.initFirstFTSMainUI();
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingFirstUI(int i) {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + i, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCardToFriendsUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 10, 50, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            PerformClickUtils.inputText(GroupSendCardToFriendsUtils.this.autoService, "");
                            GroupSendCardToFriendsUtils.this.sleep(50);
                            GroupSendCardToFriendsUtils.this.clickCard();
                            return;
                        }
                        GroupSendCardToFriendsUtils.this.clickCard();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initContactInfoUI() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友群发名片\n已发送 " + this.realExcFriendNum + " 个好友");
            LogUtils.log("WS_BABY.ContactInfoUI.1");
            new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (PerformClickUtils.findNodeIsExistByText(GroupSendCardToFriendsUtils.this.autoService, GroupSendCardToFriendsUtils.this.cardName)) {
                        LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                        PerformClickUtils.performBack(GroupSendCardToFriendsUtils.this.autoService);
                        GroupSendCardToFriendsUtils.this.executeAllMain(GroupSendCardToFriendsUtils.this.sendType);
                        return;
                    }
                    GroupSendCardToFriendsUtils.this.getRemarkFriendInfo(GroupSendCardToFriendsUtils.this.model.getIntimateMode(), GroupSendCardToFriendsUtils.this.recordNowName);
                    PerformClickUtils.findViewIdAndClick2(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                    PerformClickUtils.findTextAndClick(GroupSendCardToFriendsUtils.this.autoService, "发消息");
                    LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                    new PerformClickUtils2().checkNodeId(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 100, 100, 15, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            GroupSendCardToFriendsUtils.this.initChattingFirstUI(0);
                        }

                        public void nuFind() {
                            PerformClickUtils.findViewIdAndClick2(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                            PerformClickUtils.findTextAndClick(GroupSendCardToFriendsUtils.this.autoService, "发消息");
                            new PerformClickUtils2().checkNodeId(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 100, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    GroupSendCardToFriendsUtils.this.getRemarkFriendInfo(GroupSendCardToFriendsUtils.this.model.getIntimateMode(), GroupSendCardToFriendsUtils.this.recordNowName);
                                    GroupSendCardToFriendsUtils.this.initChattingFirstUI(0);
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                                    PerformClickUtils.performBack(GroupSendCardToFriendsUtils.this.autoService);
                                    GroupSendCardToFriendsUtils.this.executeAllMain(GroupSendCardToFriendsUtils.this.sendType);
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                    PerformClickUtils.performBack(GroupSendCardToFriendsUtils.this.autoService);
                    GroupSendCardToFriendsUtils.this.executeAllMain(GroupSendCardToFriendsUtils.this.sendType);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.excFriendsNum = 0;
        this.realExcFriendNum = 0;
        this.isFirst = true;
        this.beforeLists = new ArrayList();
        this.startIndex = 0;
        this.scrollCount = 0;
        this.isJumpedStart = true;
        this.jumpWhile = true;
        this.recordNowName = "";
        this.nowSearchName = "";
        this.isScrollBottom = false;
        this.model = operationParameterModel;
        this.cardName = operationParameterModel.getCardName();
        this.startInt = operationParameterModel.getStartIndex();
        this.friendsList = new LinkedHashSet<>();
        this.friendsList = operationParameterModel.getTagListPeoples();
        this.isCanExecute = true;
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.sayContent = operationParameterModel.getSayContent();
        this.sendType = operationParameterModel.getSendCardType();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (this.sendType == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.friendsList.size() > 0) {
                            this.friendsList.remove(this.friendsList.iterator().next());
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

    public void initFirstFTSMainUI() {
        try {
            if (this.spaceTime <= 0 || this.realExcFriendNum <= 0) {
                LogUtils.log("WS_BABY.FTSMainUI.into");
                searchNickName();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into");
                    GroupSendCardToFriendsUtils.this.searchNickName();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendCardToFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCardToFriendsUtils.this.realExcFriendNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSelectContactUI() {
        try {
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SelectContactUI_2");
                    PerformClickUtils.findViewByIdAndPasteContent(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_select_search_id, GroupSendCardToFriendsUtils.this.cardName);
                    new PerformClickUtils2().checkNilNodeId(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, 150, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            new PerformClickUtils2().checkNodeHasSomeOne(GroupSendCardToFriendsUtils.this.autoService, "最常使用", BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 80, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            boolean z = true;
                                            try {
                                                LogUtils.log("WS_BABY_SelectContactUI_3");
                                                AccessibilityNodeInfo rootInActiveWindow = GroupSendCardToFriendsUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                                        int i = 0;
                                                        boolean z2 = true;
                                                        while (z2 && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                            int i2 = i + 1;
                                                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                                String str = "";
                                                                try {
                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                } catch (Exception e) {
                                                                }
                                                                if (GroupSendCardToFriendsUtils.this.cardName.trim().equalsIgnoreCase(str.trim())) {
                                                                    LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                                                    i = i2;
                                                                    z2 = false;
                                                                }
                                                            }
                                                            i = i2;
                                                        }
                                                        z = z2;
                                                    }
                                                    if (z && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_5656");
                                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendCardToFriendsUtils.this.autoService.getRootInActiveWindow();
                                                        if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.search_card_wxh);
                                                            if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || !MyApplication.enforceable) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_5656-null");
                                                            } else {
                                                                int i3 = 0;
                                                                boolean z3 = z;
                                                                while (z3 && i3 < findAccessibilityNodeInfosByViewId2.size() && MyApplication.enforceable) {
                                                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(i3);
                                                                    i3++;
                                                                    if (accessibilityNodeInfo2 != null) {
                                                                        String str2 = "";
                                                                        if (accessibilityNodeInfo2.getText() != null) {
                                                                            str2 = accessibilityNodeInfo2.getText() + "";
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI2_" + str2);
                                                                        if (str2 != null && str2.contains(":")) {
                                                                            str2 = str2.split(":")[1];
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI3_" + str2);
                                                                        if (GroupSendCardToFriendsUtils.this.cardName.trim().equalsIgnoreCase(str2.trim())) {
                                                                            LogUtils.log("WS_BABY_SelectContactUI4_" + str2);
                                                                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                                                                            z3 = false;
                                                                        } else {
                                                                            LogUtils.log("WS_BABY_SelectContactUI_5");
                                                                        }
                                                                    }
                                                                }
                                                                z = z3;
                                                            }
                                                        }
                                                    }
                                                    if (z || !MyApplication.enforceable) {
                                                        BaseServiceUtils.updateBottomText(GroupSendCardToFriendsUtils.this.mMyManager, "没有找到要发送的名片\n请核查您检索的内容");
                                                        return;
                                                    }
                                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                                    new PerformClickUtils2().checkNodeAllIds(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.toast_edit_id, BaseServiceUtils.dialog_ok_id, 50, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            if (GroupSendCardToFriendsUtils.this.sayContent != null && !"".equals(GroupSendCardToFriendsUtils.this.sayContent)) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_7");
                                                                GroupSendCardToFriendsUtils.this.sleep(10);
                                                                AutoService autoService = GroupSendCardToFriendsUtils.this.autoService;
                                                                String str = BaseServiceUtils.toast_edit_id;
                                                                PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceUtils.remarkName + "" + GroupSendCardToFriendsUtils.this.sayContent);
                                                            }
                                                            GroupSendCardToFriendsUtils.this.sleep(50);
                                                            PerformClickUtils.findViewIdAndClick(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                            LogUtils.log("WS_BABY_SelectContactUI_8");
                                                            GroupSendCardToFriendsUtils.access$008(GroupSendCardToFriendsUtils.this);
                                                            GroupSendCardToFriendsUtils.access$608(GroupSendCardToFriendsUtils.this);
                                                            int access$600 = GroupSendCardToFriendsUtils.this.startInt == 1 ? GroupSendCardToFriendsUtils.this.excFriendsNum + 1 : GroupSendCardToFriendsUtils.this.excFriendsNum + GroupSendCardToFriendsUtils.this.startInt;
                                                            if (GroupSendCardToFriendsUtils.this.model.getSendCardType() == 0) {
                                                                SPUtils.put(MyApplication.getConText(), "card_to_friend_num_all", Integer.valueOf(access$600));
                                                            } else if (GroupSendCardToFriendsUtils.this.model.getSendCardType() == 1) {
                                                                SPUtils.put(MyApplication.getConText(), "card_to_friend_num_part", Integer.valueOf(access$600));
                                                            } else {
                                                                SPUtils.put(MyApplication.getConText(), "card_to_friend_num_shield", Integer.valueOf(access$600));
                                                            }
                                                            MyWindowManager myWindowManager = GroupSendCardToFriendsUtils.this.mMyManager;
                                                            BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + GroupSendCardToFriendsUtils.this.startInt + " 个开始群发名片\n已发送 " + GroupSendCardToFriendsUtils.this.realExcFriendNum + " 个好友");
                                                            PerformClickUtils.executedBackHome(GroupSendCardToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                public void find() {
                                                                    if (GroupSendCardToFriendsUtils.this.sendType == 1) {
                                                                        LogUtils.log("WS_BABY.ChattingUI.back3");
                                                                        GroupSendCardToFriendsUtils.this.executePartMain();
                                                                        return;
                                                                    }
                                                                    GroupSendCardToFriendsUtils.this.executeAllMain(GroupSendCardToFriendsUtils.this.sendType);
                                                                }
                                                            });
                                                        }

                                                        public void nuFind() {
                                                        }
                                                    });
                                                }
                                            } catch (Exception e2) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    BaseServiceUtils.updateBottomText(GroupSendCardToFriendsUtils.this.mMyManager, "没有找到您要发送的名片\n请您核查检索的内容");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpStartPosition() {
        LogUtils.log("WS_BABY.LauncherUI.jumpFriends");
        if (this.nameNodes == null) {
            this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.nameNodes != null && this.nameNodes.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY.LauncherUI.jumpFriends_b");
            if (this.startIndex < this.nameNodes.size() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.LauncherUI.jumpFriends_c" + this.startIndex);
                AccessibilityNodeInfo accessibilityNodeInfo = this.nameNodes.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.recordNowName = accessibilityNodeInfo.getText() + "";
                }
                LogUtils.log("WS_BABY.LauncherUI.jumpFriends_d" + this.recordNowName);
                if (this.scrollCount >= this.startInt - 1) {
                    executeFriends();
                    return;
                }
                this.startIndex++;
                if (!this.isScrollBottom || !this.beforeLists.contains(this.recordNowName)) {
                    this.scrollCount++;
                    updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个开始，已跳过 " + this.scrollCount + " 个好友");
                    LogUtils.log("WS_BABY.LauncherUI.jumpFriends_add");
                    if (this.recordNowName != null && !"".equals(this.recordNowName)) {
                        this.beforeLists.add(this.recordNowName);
                    }
                    jumpStartPosition();
                    return;
                }
                jumpStartPosition();
            } else if (this.startIndex >= this.nameNodes.size() && MyApplication.enforceable) {
                this.nameNodes = null;
                LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (GroupSendCardToFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(GroupSendCardToFriendsUtils.this.mMyManager);
                        }
                        boolean unused = GroupSendCardToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendCardToFriendsUtils.this.autoService, "位联系人");
                        int unused2 = GroupSendCardToFriendsUtils.this.startIndex = 1;
                        GroupSendCardToFriendsUtils.this.jumpStartPosition();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "给好友发送名片", "共发送给了 " + this.realExcFriendNum + " 个好友 ");
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, 200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String unused = GroupSendCardToFriendsUtils.this.nowSearchName = "";
                    if (GroupSendCardToFriendsUtils.this.friendsList != null && GroupSendCardToFriendsUtils.this.friendsList.size() > 0 && MyApplication.enforceable) {
                        String str = (String) GroupSendCardToFriendsUtils.this.friendsList.iterator().next();
                        String unused2 = GroupSendCardToFriendsUtils.this.nowSearchName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.search_id);
                        GroupSendCardToFriendsUtils.this.sleep(350);
                        PerformClickUtils.inputText(GroupSendCardToFriendsUtils.this.autoService, str);
                        GroupSendCardToFriendsUtils.this.friendsList.remove(GroupSendCardToFriendsUtils.this.friendsList.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendCardToFriendsUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendCardToFriendsUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendCardToFriendsUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCardToFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    PerformClickUtils.performBack(GroupSendCardToFriendsUtils.this.autoService);
                                    LogUtils.log("WS_BABY.search_back1");
                                    GroupSendCardToFriendsUtils.this.executePartMain();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                if (GroupSendCardToFriendsUtils.this.model.getSendCardType() == 1 && GroupSendCardToFriendsUtils.this.model.getIntimateMode() == 0) {
                                    BaseServiceUtils.remarkName = GroupSendCardToFriendsUtils.this.nowSearchName;
                                }
                                GroupSendCardToFriendsUtils.this.initChattingFirstUI(100);
                            }
                        }

                        public void nuFind() {
                            PerformClickUtils.performBack(GroupSendCardToFriendsUtils.this.autoService);
                            LogUtils.log("WS_BABY.search_back1");
                            GroupSendCardToFriendsUtils.this.executePartMain();
                        }
                    });
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
