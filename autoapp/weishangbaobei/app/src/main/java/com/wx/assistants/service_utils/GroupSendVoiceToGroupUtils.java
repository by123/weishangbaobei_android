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

public class GroupSendVoiceToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendVoiceToGroupUtils instance;
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
    private String lastName;
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

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(GroupSendVoiceToGroupUtils groupSendVoiceToGroupUtils) {
        int i = groupSendVoiceToGroupUtils.sendGroupNum;
        groupSendVoiceToGroupUtils.sendGroupNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(GroupSendVoiceToGroupUtils groupSendVoiceToGroupUtils) {
        int i = groupSendVoiceToGroupUtils.startIndex;
        groupSendVoiceToGroupUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$710(GroupSendVoiceToGroupUtils groupSendVoiceToGroupUtils) {
        int i = groupSendVoiceToGroupUtils.circulateInnerSize;
        groupSendVoiceToGroupUtils.circulateInnerSize = i - 1;
        return i;
    }

    private GroupSendVoiceToGroupUtils() {
    }

    public static GroupSendVoiceToGroupUtils getInstance() {
        instance = new GroupSendVoiceToGroupUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendResult = "";
        this.startIndex = 0;
        this.sendGroupNum = 0;
        this.isFirstFav = true;
        this.lastName = "";
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
                LogUtils.log("WS_BABY_initRoomContact_sendGroupNum = " + GroupSendVoiceToGroupUtils.this.sendGroupNum + ",jumpNum= " + GroupSendVoiceToGroupUtils.this.jumpNum + ",startIndexFromUser = " + GroupSendVoiceToGroupUtils.this.startIndexFromUser + "");
                if (GroupSendVoiceToGroupUtils.this.sendGroupNum + GroupSendVoiceToGroupUtils.this.jumpNum + GroupSendVoiceToGroupUtils.this.startIndexFromUser <= 1 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_initRoomContact_从1开始");
                    GroupSendVoiceToGroupUtils.this.executeSendGroup(GroupSendVoiceToGroupUtils.this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact_从" + (GroupSendVoiceToGroupUtils.this.sendGroupNum + GroupSendVoiceToGroupUtils.this.jumpNum + GroupSendVoiceToGroupUtils.this.startIndexFromUser) + "开始");
                GroupSendVoiceToGroupUtils.this.jumpStartLists.clear();
                int unused = GroupSendVoiceToGroupUtils.this.scrollCount = 0;
                int unused2 = GroupSendVoiceToGroupUtils.this.startIndex = 0;
                GroupSendVoiceToGroupUtils.this.jumpStartPosition();
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
                    new PerformClickUtils2().check(this.autoService, group_list_item_name_id, 200, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            int unused = GroupSendVoiceToGroupUtils.this.startIndex = 1;
                            GroupSendVoiceToGroupUtils.this.jumpStartPosition();
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
                    PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendVoiceToGroupUtils.this.executeMain();
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
                            int unused = GroupSendVoiceToGroupUtils.this.startIndex = 1;
                            GroupSendVoiceToGroupUtils.this.executeSendGroup(list);
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
                            GroupSendVoiceToGroupUtils.this.executeMain();
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
        this.circulateMode = this.operationParameterModel.getCirculateMode();
        this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
        if (this.spaceTime <= 0 || this.isFirstDelay) {
            this.isFirstDelay = false;
            initRoomContact();
            return;
        }
        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
            public void surplus(int i) {
                GroupSendVoiceToGroupUtils.this.updateBottom(i);
            }

            public void end() {
                GroupSendVoiceToGroupUtils.this.updateBottom(0);
                GroupSendVoiceToGroupUtils.this.initRoomContact();
            }
        });
    }

    public void initBackChattingUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 10, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                GroupSendVoiceToGroupUtils.access$710(GroupSendVoiceToGroupUtils.this);
                GroupSendVoiceToGroupUtils.this.updateBottom(0);
                if (GroupSendVoiceToGroupUtils.this.circulateInnerSize == 0 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_ChattingUI_BACK3");
                    PerformClickUtils.executedBackHome(GroupSendVoiceToGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendVoiceToGroupUtils.this.executeMain();
                        }
                    });
                    return;
                }
                GroupSendVoiceToGroupUtils.this.initFirstChattingUI();
            }
        });
    }

    public void initFirstChattingUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, contact_bottom_layout_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendVoiceToGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                    LogUtils.log("WS_BABY_ChattingUI_into1");
                    PerformClickUtils.inputText(GroupSendVoiceToGroupUtils.this.autoService, "");
                }
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
                PerformClickUtils.findViewIdAndClick(GroupSendVoiceToGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                new PerformClickUtils2().checkCardCollectionName(GroupSendVoiceToGroupUtils.this.autoService, BaseServiceUtils.send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.4");
                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendVoiceToGroupUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.5");
                        GroupSendVoiceToGroupUtils.this.initFavSelectUI();
                    }
                });
            }
        });
    }

    public void initFavSelectUI() {
        try {
            LogUtils.log("WS_BABY_FavSelectUI.into");
            new PerformClickUtils2().checkFavListNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + (executeSpeedSetting / 2), 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void find(int r8) {
                    /*
                        r7 = this;
                        java.lang.String r8 = "WS_BABY_FavSelectUI.into2"
                        com.wx.assistants.utils.LogUtils.log(r8)
                        com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils r8 = com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils.this
                        com.wx.assistants.service.AutoService r8 = r8.autoService
                        android.view.accessibility.AccessibilityNodeInfo r8 = r8.getRootInActiveWindow()
                        if (r8 == 0) goto L_0x00f5
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable
                        if (r0 == 0) goto L_0x00f5
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r8 = r8.findAccessibilityNodeInfosByViewId(r0)
                        r0 = 1
                        r1 = 0
                        if (r8 == 0) goto L_0x00a4
                        int r2 = r8.size()
                        if (r2 <= 0) goto L_0x00a4
                        boolean r2 = com.wx.assistants.application.MyApplication.enforceable
                        if (r2 == 0) goto L_0x00a4
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into3"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        java.lang.Object r2 = r8.get(r1)
                        if (r2 == 0) goto L_0x00a4
                        java.lang.String r2 = "android.widget.ListView"
                        java.lang.Object r3 = r8.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        java.lang.CharSequence r3 = r3.getClassName()
                        boolean r2 = r2.equals(r3)
                        if (r2 == 0) goto L_0x00a4
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into4"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        java.lang.Object r2 = r8.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r2 = (android.view.accessibility.AccessibilityNodeInfo) r2
                        int r2 = r2.getChildCount()
                        if (r2 <= 0) goto L_0x00a4
                        boolean r2 = com.wx.assistants.application.MyApplication.enforceable
                        if (r2 == 0) goto L_0x00a4
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into5"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        r2 = 0
                    L_0x005f:
                        java.lang.Object r3 = r8.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        int r3 = r3.getChildCount()
                        if (r2 >= r3) goto L_0x00a4
                        boolean r3 = com.wx.assistants.application.MyApplication.enforceable
                        if (r3 != 0) goto L_0x0070
                        return
                    L_0x0070:
                        java.lang.Object r3 = r8.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        android.view.accessibility.AccessibilityNodeInfo r3 = r3.getChild(r2)
                        if (r3 == 0) goto L_0x00a1
                        java.lang.CharSequence r4 = r3.getClassName()
                        if (r4 == 0) goto L_0x00a1
                        java.lang.String r4 = "android.widget.FrameLayout"
                        java.lang.CharSequence r5 = r3.getClassName()
                        boolean r4 = r4.equals(r5)
                        if (r4 == 0) goto L_0x00a1
                        boolean r4 = com.wx.assistants.application.MyApplication.enforceable
                        if (r4 == 0) goto L_0x00a1
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into6"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into7"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r3)
                        r2 = 1
                        goto L_0x00a5
                    L_0x00a1:
                        int r2 = r2 + 1
                        goto L_0x005f
                    L_0x00a4:
                        r2 = 0
                    L_0x00a5:
                        if (r2 != 0) goto L_0x00d7
                        java.lang.String r2 = "WS_BABY_FavSelectUI.into77777"
                        com.wx.assistants.utils.LogUtils.log(r2)
                        com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils r2 = com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils.this
                        com.wx.assistants.service.AutoService r2 = r2.autoService
                        java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r2 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r2, (java.lang.String) r3)
                        if (r2 == 0) goto L_0x00d7
                        int r3 = r2.size()
                        if (r3 <= 0) goto L_0x00d7
                        java.lang.Object r2 = r2.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r2 = (android.view.accessibility.AccessibilityNodeInfo) r2
                        int r2 = r2.getChildCount()
                        if (r2 <= r0) goto L_0x00d7
                        java.lang.Object r8 = r8.get(r1)
                        android.view.accessibility.AccessibilityNodeInfo r8 = (android.view.accessibility.AccessibilityNodeInfo) r8
                        android.view.accessibility.AccessibilityNodeInfo r8 = r8.getChild(r0)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r8)
                    L_0x00d7:
                        java.lang.String r8 = "WS_BABY_FavSelectUI.into8"
                        com.wx.assistants.utils.LogUtils.log(r8)
                        com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
                        r0.<init>()
                        com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils r8 = com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils.this
                        com.wx.assistants.service.AutoService r1 = r8.autoService
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id
                        r3 = 80
                        r4 = 50
                        r5 = 100
                        com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils$9$1 r6 = new com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils$9$1
                        r6.<init>()
                        r0.checkNodeId(r1, r2, r3, r4, r5, r6)
                    L_0x00f5:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.GroupSendVoiceToGroupUtils.AnonymousClass9.find(int):void");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            prepareComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRecord(int i) {
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "voice_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "voice_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "voice_to_group_num_shield", Integer.valueOf(i));
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
                    sb.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                    sb2.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                    sb3.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb4.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb5.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb6.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb7.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb8.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
                sb9.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
            sb10.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
            sb11.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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
            sb12.append(" 个开始群发语音，请不要操作微信\n已发送 ");
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

    public void prepareComplete() {
        new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(GroupSendVoiceToGroupUtils.this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(GroupSendVoiceToGroupUtils.this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(GroupSendVoiceToGroupUtils.this.autoService, "通讯录");
                new PerformClickUtils2().checkString(GroupSendVoiceToGroupUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(GroupSendVoiceToGroupUtils.this.autoService, "群聊");
                        GroupSendVoiceToGroupUtils.this.initChatRoomContactUI();
                    }
                });
            }
        });
    }

    public void middleViewShow() {
        if (this.sendResult == null || "".equals(this.sendResult)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "群发语音", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发语音结束", this.sendResult);
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                updateBottom(0);
            } else {
                showBottomView(this.mMyManager, "正在向微信群群发语音\n请勿操作微信.");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendVoiceToGroupUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
