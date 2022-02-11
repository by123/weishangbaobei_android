package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
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
import java.util.ArrayList;
import java.util.List;

public class AutoPullFriendsToAllGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullFriendsToAllGroupUtils instance;
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
    /* access modifiers changed from: private */
    public boolean isExecutedBack = false;
    /* access modifiers changed from: private */
    public boolean isFromBack;
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
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    private AutoPullFriendsToAllGroupUtils() {
    }

    static /* synthetic */ int access$008(AutoPullFriendsToAllGroupUtils autoPullFriendsToAllGroupUtils) {
        int i = autoPullFriendsToAllGroupUtils.sendGroupNum;
        autoPullFriendsToAllGroupUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(AutoPullFriendsToAllGroupUtils autoPullFriendsToAllGroupUtils) {
        int i = autoPullFriendsToAllGroupUtils.jumpNum;
        autoPullFriendsToAllGroupUtils.jumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(AutoPullFriendsToAllGroupUtils autoPullFriendsToAllGroupUtils) {
        int i = autoPullFriendsToAllGroupUtils.startIndex;
        autoPullFriendsToAllGroupUtils.startIndex = i + 1;
        return i;
    }

    public static AutoPullFriendsToAllGroupUtils getInstance() {
        instance = new AutoPullFriendsToAllGroupUtils();
        return instance;
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

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                updateBottom(0);
            } else {
                showBottomView(this.mMyManager, "正在向微信群拉人进群\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        AutoPullFriendsToAllGroupUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAddFriend() {
        this.isExecutedBack = false;
        if (MyApplication.enforceable) {
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    int i2;
                    LogUtils.log("WS_BABY.ChatroomInfoUI_0");
                    int i3 = 2;
                    for (int i4 = 0; i4 < i3; i4++) {
                        AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow == null || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_3");
                            return;
                        }
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.default_list_id);
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_head_img_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                            int i5 = 0;
                            while (true) {
                                if (i5 >= findAccessibilityNodeInfosByViewId2.size()) {
                                    break;
                                } else if (!MyApplication.enforceable) {
                                    i2 = i3;
                                    break;
                                } else {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i5);
                                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                                        if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员") && MyApplication.enforceable) {
                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                            AutoPullFriendsToAllGroupUtils.this.initSelectNickName();
                                            i2 = 0;
                                            break;
                                        }
                                    }
                                    i5++;
                                }
                            }
                            if (MyApplication.enforceable && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                                findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                                AutoPullFriendsToAllGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            }
                            i3 = i2;
                        }
                        i2 = i3;
                        LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                        findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                        AutoPullFriendsToAllGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        i3 = i2;
                    }
                }

                public void nuFind() {
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
        removeEndView(this.mMyManager);
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    boolean unused = AutoPullFriendsToAllGroupUtils.this.isFromBack = false;
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(AutoPullFriendsToAllGroupUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, "群聊");
                            AutoPullFriendsToAllGroupUtils.this.initChatRoomContactUI();
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

    public void executeSendGroup(final List<String> list) {
        LogUtils.log("WS_BABY_initRoomContact.start");
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
                        } catch (Exception e) {
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
                            initChattingUI();
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
                        initChattingUI();
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
                        public void find(int i) {
                            int unused = AutoPullFriendsToAllGroupUtils.this.startIndex = 1;
                            AutoPullFriendsToAllGroupUtils.this.executeSendGroup(list);
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                        }

                        public void nuFind() {
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
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoPullFriendsToAllGroupUtils.this.executeMain();
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

    public void initChatRoomContactUI() {
        try {
            this.isFromBack = false;
            this.circulateMode = this.operationParameterModel.getCirculateMode();
            this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
            initRoomContact();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatRoomInfoUI() {
        LogUtils.log("WS_BABY.ChatroomInfoUI");
        if (!this.isFromBack || !MyApplication.enforceable) {
            executeAddFriend();
        } else {
            new PerformClickUtils2().checkNilString(this.autoService, "正在添加", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                    new PerformClickUtils2().check(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + 100, (int) SocializeConstants.CANCLE_RESULTCODE, AutoPullFriendsToAllGroupUtils.this.spaceTime, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                        public void end() {
                            if (MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                                new PerformClickUtils2().checkLoad(AutoPullFriendsToAllGroupUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end2");
                                        new PerformClickUtils2().checkDialogToBack(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.back_contact_id, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 50, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                                LogUtils.log("WS_BABY_SelectContactUI_8");
                                                boolean unused = AutoPullFriendsToAllGroupUtils.this.isFromBack = true;
                                                if (AutoPullFriendsToAllGroupUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                    AutoPullFriendsToAllGroupUtils.access$508(AutoPullFriendsToAllGroupUtils.this);
                                                    AutoPullFriendsToAllGroupUtils.this.updateBottom(0);
                                                }
                                                AutoPullFriendsToAllGroupUtils.this.initChattingUI();
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

                        public void executeIndex(int i) {
                            MyWindowManager myWindowManager = AutoPullFriendsToAllGroupUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "执行下一个群，倒计时 " + i + " 秒");
                        }

                        public void find() {
                            LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                            if (PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id)) {
                                try {
                                    if (AutoPullFriendsToAllGroupUtils.this.sayContent != null && !AutoPullFriendsToAllGroupUtils.this.sayContent.equals("")) {
                                        LogUtils.log("WS_BABY_SelectContactUI_7");
                                        AutoPullFriendsToAllGroupUtils.this.sleep(10);
                                        PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullFriendsToAllGroupUtils.this.sayContent);
                                    }
                                } catch (Exception e) {
                                }
                                AutoPullFriendsToAllGroupUtils.this.sleep(10);
                            }
                            PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ChatroomInfoUI_ADD");
                }
            });
        }
    }

    public void initChattingUI() {
        try {
            if (!this.isFromBack || !MyApplication.enforceable) {
                new PerformClickUtils2().check(this.autoService, contact_title_id, 0, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (PerformClickUtils.getTextByNodeId(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.contact_title_id).contains("(500)")) {
                            AutoPullFriendsToAllGroupUtils.access$108(AutoPullFriendsToAllGroupUtils.this);
                            AutoPullFriendsToAllGroupUtils.this.saveRecord(AutoPullFriendsToAllGroupUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser);
                            PerformClickUtils.performBack(AutoPullFriendsToAllGroupUtils.this.autoService);
                            AutoPullFriendsToAllGroupUtils.this.executeMain();
                            return;
                        }
                        LogUtils.log("WS_BABY.ChattingUI_0");
                        new PerformClickUtils2().check(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.right_more_id, 200, 100, 18, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                                AutoPullFriendsToAllGroupUtils.this.initChatRoomInfoUI();
                            }

                            public void nuFind() {
                                if (MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_TYPE_4");
                                    PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            AutoPullFriendsToAllGroupUtils.this.executeMain();
                                        }
                                    });
                                }
                            }
                        });
                    }

                    public void nuFind() {
                    }
                });
                return;
            }
            this.circulateInnerSize--;
            updateBottom(0);
            if (this.circulateInnerSize != 0 && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChattingUI_1");
                new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.ChattingUI_2");
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                        AutoPullFriendsToAllGroupUtils.this.initChatRoomInfoUI();
                    }

                    public void nuFind() {
                        if (MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_TYPE_4");
                            PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoPullFriendsToAllGroupUtils.this.executeMain();
                                }
                            });
                        }
                    }
                });
            } else if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY_TYPE_4");
                PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        AutoPullFriendsToAllGroupUtils.this.executeMain();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.startIndex = 0;
        this.sendGroupNum = 0;
        this.isFromBack = false;
        this.isExecutedBack = false;
        this.lastName = "";
        this.operationParameterModel = operationParameterModel2;
        this.cardName = operationParameterModel2.getCardName();
        if (this.cardName != null && !"".equals(this.cardName)) {
            this.cardName = this.cardName.replace(" ", "");
        }
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
            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_sendGroupNum = " + AutoPullFriendsToAllGroupUtils.this.sendGroupNum + ",jumpNum= " + AutoPullFriendsToAllGroupUtils.this.jumpNum + ",startIndexFromUser = " + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser + "");
                if (AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser <= 1 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_initRoomContact_从1开始");
                    AutoPullFriendsToAllGroupUtils.this.executeSendGroup(AutoPullFriendsToAllGroupUtils.this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact_从" + (AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser) + "开始");
                AutoPullFriendsToAllGroupUtils.this.jumpStartLists.clear();
                int unused = AutoPullFriendsToAllGroupUtils.this.scrollCount = 0;
                int unused2 = AutoPullFriendsToAllGroupUtils.this.startIndex = 0;
                AutoPullFriendsToAllGroupUtils.this.jumpStartPosition();
            }

            public void nuFind() {
            }
        });
    }

    public void initSelectNickName() {
        try {
            new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + 350 + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SelectContactUI_2");
                    PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.list_select_search_id, AutoPullFriendsToAllGroupUtils.this.cardName);
                    new PerformClickUtils2().checkNilNodeId(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.list_select_letter_slip_id, (BaseServiceUtils.executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 80, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            new PerformClickUtils2().checkNodeIdsHasSomeOne(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.list_select_name_id, BaseServiceUtils.search_card_wxh, 0, 100, 80, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            boolean z;
                                            try {
                                                LogUtils.log("WS_BABY_SelectContactUI_3");
                                                AccessibilityNodeInfo rootInActiveWindow = AutoPullFriendsToAllGroupUtils.this.autoService.getRootInActiveWindow();
                                                if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                        z = true;
                                                    } else {
                                                        int i = 0;
                                                        boolean z2 = true;
                                                        while (z2 && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                            int i2 = i + 1;
                                                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_4_" + accessibilityNodeInfo.getText().toString());
                                                                String str = "";
                                                                try {
                                                                    str = accessibilityNodeInfo.getText() + "";
                                                                    if (str != null) {
                                                                        try {
                                                                            if (!"".equals(str)) {
                                                                                str = str.replace(" ", "");
                                                                            }
                                                                        } catch (Exception e) {
                                                                        }
                                                                    }
                                                                } catch (Exception e2) {
                                                                }
                                                                if (AutoPullFriendsToAllGroupUtils.this.cardName.trim().equals(str.trim())) {
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
                                                        AccessibilityNodeInfo rootInActiveWindow2 = AutoPullFriendsToAllGroupUtils.this.autoService.getRootInActiveWindow();
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
                                                                        if (str2 != null && !"".equals(str2)) {
                                                                            str2 = str2.replace(" ", "");
                                                                        }
                                                                        LogUtils.log("WS_BABY_SelectContactUI3_" + str2);
                                                                        if (AutoPullFriendsToAllGroupUtils.this.cardName.trim().equals(str2.trim())) {
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
                                                        BaseServiceUtils.updateBottomText(AutoPullFriendsToAllGroupUtils.this.mMyManager, "没有找到您要拉人的昵称\n核查您检索的内容");
                                                        return;
                                                    }
                                                    AutoPullFriendsToAllGroupUtils.this.sleep(100);
                                                    if (PerformClickUtils.findViewIdIsEnabled(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.complete_id)) {
                                                        AutoPullFriendsToAllGroupUtils.access$008(AutoPullFriendsToAllGroupUtils.this);
                                                        AutoPullFriendsToAllGroupUtils.this.saveRecord(AutoPullFriendsToAllGroupUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser);
                                                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToAllGroupUtils.this.autoService, BaseServiceUtils.complete_id);
                                                        boolean unused = AutoPullFriendsToAllGroupUtils.this.isFromBack = true;
                                                        AutoPullFriendsToAllGroupUtils.this.initChatRoomInfoUI();
                                                    } else if (!AutoPullFriendsToAllGroupUtils.this.isExecutedBack) {
                                                        boolean unused2 = AutoPullFriendsToAllGroupUtils.this.isExecutedBack = true;
                                                        AutoPullFriendsToAllGroupUtils.access$108(AutoPullFriendsToAllGroupUtils.this);
                                                        AutoPullFriendsToAllGroupUtils.this.saveRecord(AutoPullFriendsToAllGroupUtils.this.startIndexFromUser == 1 ? AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + 1 : AutoPullFriendsToAllGroupUtils.this.sendGroupNum + AutoPullFriendsToAllGroupUtils.this.jumpNum + AutoPullFriendsToAllGroupUtils.this.startIndexFromUser);
                                                        PerformClickUtils.executedBackHome(AutoPullFriendsToAllGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                AutoPullFriendsToAllGroupUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                }
                                            } catch (Exception e3) {
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    BaseServiceUtils.updateBottomText(AutoPullFriendsToAllGroupUtils.this.mMyManager, "没有找到您要拉人的昵称\n核查您检索的内容");
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
                    } catch (Exception e) {
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
                    new PerformClickUtils2().check(this.autoService, group_list_item_name_id, 200, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            int unused = AutoPullFriendsToAllGroupUtils.this.startIndex = 1;
                            AutoPullFriendsToAllGroupUtils.this.jumpStartPosition();
                        }

                        public void nuFind() {
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
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoPullFriendsToAllGroupUtils.this.executeMain();
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "拉人进群", "微商宝贝帮您邀请了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "拉人进群结束", this.sendResult);
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "pull_friend_to_all_group_num_shield", Integer.valueOf(i));
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
                    sb.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
                    sb.append(this.sendGroupNum);
                    sb.append(" 个群，已屏蔽500人群 ");
                    sb.append(this.jumpNum);
                    sb.append(" 个\n内循环 ");
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
                    sb2.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
                    sb3.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
                sb4.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
                sb4.append(this.sendGroupNum);
                sb4.append(" 个群，已屏蔽500人群 ");
                sb4.append(this.jumpNum);
                sb4.append(" 个");
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
                sb5.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
                sb6.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
                sb7.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
                sb7.append(this.sendGroupNum);
                sb7.append(" 个群，已屏蔽500人群 ");
                sb7.append(this.jumpNum);
                sb7.append(" 个\n外循环第 ");
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
                sb8.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
                sb9.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
            sb10.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
            sb10.append(this.sendGroupNum);
            sb10.append(" 个群，已屏蔽500人群 ");
            sb10.append(this.jumpNum);
            sb10.append(" 个");
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
            sb11.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
            sb12.append(" 个开始拉人进群，请不要操作微信\n已邀请 ");
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
}
