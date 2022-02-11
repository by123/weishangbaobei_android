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

public class GroupSendCollectionToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCollectionToGroupUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int circulateInnerSize = 1;
    private int circulateMode = 0;
    private int circulateOuterSize = 1;
    /* access modifiers changed from: private */
    public List<String> groupList = new ArrayList();
    List<AccessibilityNodeInfo> groupNames = null;
    private boolean isFirstDelay = true;
    /* access modifiers changed from: private */
    public boolean isFirstFav = true;
    private boolean isScrollBottom = false;
    private String jumpGroupName = "";
    /* access modifiers changed from: private */
    public int jumpNum = 0;
    /* access modifiers changed from: private */
    public List<String> jumpStartLists = new ArrayList();
    private String lastName = "";
    private String nowName = "";
    private OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public String sayContent;
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

    private GroupSendCollectionToGroupUtils() {
    }

    static /* synthetic */ int access$008(GroupSendCollectionToGroupUtils groupSendCollectionToGroupUtils) {
        int i = groupSendCollectionToGroupUtils.sendGroupNum;
        groupSendCollectionToGroupUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(GroupSendCollectionToGroupUtils groupSendCollectionToGroupUtils) {
        int i = groupSendCollectionToGroupUtils.startIndex;
        groupSendCollectionToGroupUtils.startIndex = i + 1;
        return i;
    }

    public static GroupSendCollectionToGroupUtils getInstance() {
        instance = new GroupSendCollectionToGroupUtils();
        return instance;
    }

    public void clickCollection() {
        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.2");
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCollectionToGroupUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.3");
                GroupSendCollectionToGroupUtils.this.initFavSelectUI();
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
            if (this.operationParameterModel != null) {
                updateBottom(0);
            } else {
                MyWindowManager myWindowManager = this.mMyManager;
                showBottomView(myWindowManager, "从第" + this.startIndexFromUser + "个开始群发收藏\n请不要操作微信");
            }
            LogUtils.log("WS_BABY_END_SHOW");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendCollectionToGroupUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendCollectionToGroupUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendCollectionToGroupUtils.this.autoService, "群聊");
                            GroupSendCollectionToGroupUtils.this.initChatRoomContactUI();
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
                            initFirstChattingUI(100);
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
                        initFirstChattingUI(100);
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
                            int unused = GroupSendCollectionToGroupUtils.this.startIndex = 1;
                            GroupSendCollectionToGroupUtils.this.executeSendGroup(list);
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
                    this.isFirstDelay = true;
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendCollectionToGroupUtils.this.executeMain();
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

    public void initBackChattingUI() {
        this.circulateInnerSize--;
        updateBottom(0);
        if (this.circulateInnerSize == 0 || !MyApplication.enforceable) {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    GroupSendCollectionToGroupUtils.this.executeMain();
                }
            });
        } else {
            new PerformClickUtils2().check(this.autoService, voice_left_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.ChattingUI.back1");
                    GroupSendCollectionToGroupUtils.this.initFirstChattingUI(0);
                }

                public void nuFind() {
                }
            });
        }
    }

    public void initChatRoomContactUI() {
        this.circulateMode = this.operationParameterModel.getCirculateMode();
        this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
        if (this.spaceTime <= 0 || this.isFirstDelay) {
            this.isFirstDelay = false;
            initRoomContact();
            return;
        }
        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
            public void end() {
                GroupSendCollectionToGroupUtils.this.updateBottom(0);
                GroupSendCollectionToGroupUtils.this.initRoomContact();
            }

            public void surplus(int i) {
                GroupSendCollectionToGroupUtils.this.updateBottom(i);
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.startIndex = 0;
        this.isFirstDelay = true;
        this.sendGroupNum = 0;
        this.jumpNum = 0;
        this.scrollCount = 0;
        this.isFirstFav = true;
        this.isScrollBottom = false;
        this.lastName = "";
        this.nowName = "";
        this.groupList = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.beforeLists = new ArrayList();
        this.shieldLists = new ArrayList();
        this.operationParameterModel = operationParameterModel2;
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
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

    public void initFavSelectUI() {
        try {
            LogUtils.log("WS_BABY_FavSelectUI.into");
            new PerformClickUtils2().checkFavListNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    boolean z = false;
                    LogUtils.log("WS_BABY_FavSelectUI.into2");
                    AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToGroupUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.collection_list_id);
                        if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_FavSelectUI.into3");
                            if (findAccessibilityNodeInfosByViewId.get(0) != null && "android.widget.ListView".equals(findAccessibilityNodeInfosByViewId.get(0).getClassName())) {
                                LogUtils.log("WS_BABY_FavSelectUI.into4");
                                if (findAccessibilityNodeInfosByViewId.get(0).getChildCount() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into5");
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 < findAccessibilityNodeInfosByViewId.get(0).getChildCount()) {
                                            if (MyApplication.enforceable) {
                                                AccessibilityNodeInfo child = findAccessibilityNodeInfosByViewId.get(0).getChild(i2);
                                                if (child != null && child.getClassName() != null && "android.widget.FrameLayout".equals(child.getClassName()) && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_FavSelectUI.into6");
                                                    LogUtils.log("WS_BABY_FavSelectUI.into7");
                                                    PerformClickUtils.performClick(child);
                                                    z = true;
                                                    break;
                                                }
                                                i2++;
                                            } else {
                                                return;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (!z) {
                            LogUtils.log("WS_BABY_FavSelectUI.into77777");
                            try {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToGroupUtils.this.autoService, BaseServiceUtils.collection_list_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && findViewIdList.get(0).getChildCount() > 1) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0).getChild(1));
                                }
                            } catch (Exception e) {
                            }
                        }
                        LogUtils.log("WS_BABY_FavSelectUI.into8");
                        new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                try {
                                    GroupSendCollectionToGroupUtils.this.sleep(10);
                                    PerformClickUtils.findViewByIdAndPasteContent(GroupSendCollectionToGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendCollectionToGroupUtils.this.sayContent);
                                } catch (Exception e) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into9");
                                }
                                LogUtils.log("WS_BABY_FavSelectUI.into10");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into11");
                                    findViewIdList.get(0).performAction(16);
                                    if (GroupSendCollectionToGroupUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                        GroupSendCollectionToGroupUtils.access$508(GroupSendCollectionToGroupUtils.this);
                                        GroupSendCollectionToGroupUtils.access$008(GroupSendCollectionToGroupUtils.this);
                                        GroupSendCollectionToGroupUtils.this.saveRecord(GroupSendCollectionToGroupUtils.this.startIndexFromUser == 1 ? GroupSendCollectionToGroupUtils.this.sendGroupNum + 1 + GroupSendCollectionToGroupUtils.this.jumpNum : GroupSendCollectionToGroupUtils.this.sendGroupNum + GroupSendCollectionToGroupUtils.this.startIndexFromUser + GroupSendCollectionToGroupUtils.this.jumpNum);
                                        GroupSendCollectionToGroupUtils.this.updateBottom(0);
                                    }
                                    new PerformClickUtils2().checkNodeId(GroupSendCollectionToGroupUtils.this.autoService, BaseServiceUtils.voice_left_id, 1500, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            boolean unused = GroupSendCollectionToGroupUtils.this.isFirstFav = false;
                                            GroupSendCollectionToGroupUtils.this.initBackChattingUI();
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
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFirstChattingUI(int i) {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + i, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCollectionToGroupUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 10, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY_FavSelectUI.initFirstChattingUI.0");
                            PerformClickUtils.inputText(GroupSendCollectionToGroupUtils.this.autoService, "");
                            GroupSendCollectionToGroupUtils.this.clickCollection();
                            return;
                        }
                        LogUtils.log("WS_BABY_FavSelectUI.initFirstChattingUI.1");
                        GroupSendCollectionToGroupUtils.this.clickCollection();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initRoomContact() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_sendGroupNum = " + GroupSendCollectionToGroupUtils.this.sendGroupNum + ",jumpNum= " + GroupSendCollectionToGroupUtils.this.jumpNum + ",startIndexFromUser = " + GroupSendCollectionToGroupUtils.this.startIndexFromUser + "");
                if (GroupSendCollectionToGroupUtils.this.sendGroupNum + GroupSendCollectionToGroupUtils.this.jumpNum + GroupSendCollectionToGroupUtils.this.startIndexFromUser <= 1 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_initRoomContact_从1开始");
                    GroupSendCollectionToGroupUtils.this.executeSendGroup(GroupSendCollectionToGroupUtils.this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact_从" + (GroupSendCollectionToGroupUtils.this.sendGroupNum + GroupSendCollectionToGroupUtils.this.jumpNum + GroupSendCollectionToGroupUtils.this.startIndexFromUser) + "开始");
                GroupSendCollectionToGroupUtils.this.jumpStartLists.clear();
                int unused = GroupSendCollectionToGroupUtils.this.scrollCount = 0;
                int unused2 = GroupSendCollectionToGroupUtils.this.startIndex = 0;
                GroupSendCollectionToGroupUtils.this.jumpStartPosition();
            }

            public void nuFind() {
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
                            int unused = GroupSendCollectionToGroupUtils.this.startIndex = 1;
                            GroupSendCollectionToGroupUtils.this.jumpStartPosition();
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
                    this.isFirstDelay = true;
                    updateBottom(0);
                    PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendCollectionToGroupUtils.this.executeMain();
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
            updateMiddleText(myWindowManager, "群发收藏结束", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发收藏结束", this.sendResult);
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "collection_to_group_num_shield", Integer.valueOf(i));
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
                    sb.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                    sb2.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                    sb3.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb4.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb5.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb6.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb7.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb8.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
                sb9.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
            sb10.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
            sb11.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
            sb12.append(" 个开始群发收藏，请不要操作微信\n已发送 ");
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
