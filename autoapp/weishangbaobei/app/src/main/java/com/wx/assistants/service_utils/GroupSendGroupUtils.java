package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GroupSendGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendGroupUtils instance;
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
    public boolean isFirstSelectImg = true;
    /* access modifiers changed from: private */
    public int isOriginPic = 1;
    private boolean isScrollBottom = false;
    private String jumpGroupName;
    /* access modifiers changed from: private */
    public int jumpNum = 0;
    /* access modifiers changed from: private */
    public List<String> jumpStartLists = new ArrayList();
    private String lastName = "";
    private String localImgUrl;
    private String nowName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public int picCount = 1;
    /* access modifiers changed from: private */
    public String sayContent;
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendGroupNum = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    private String sendResult = "";
    /* access modifiers changed from: private */
    public int sendTextCount = 10;
    private List<String> shieldLists = new ArrayList();
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndexFromUser = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1210(GroupSendGroupUtils groupSendGroupUtils) {
        int i = groupSendGroupUtils.circulateInnerSize;
        groupSendGroupUtils.circulateInnerSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$1410(GroupSendGroupUtils groupSendGroupUtils) {
        int i = groupSendGroupUtils.sendTextCount;
        groupSendGroupUtils.sendTextCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$408(GroupSendGroupUtils groupSendGroupUtils) {
        int i = groupSendGroupUtils.sendGroupNum;
        groupSendGroupUtils.sendGroupNum = i + 1;
        return i;
    }

    private GroupSendGroupUtils() {
    }

    public static GroupSendGroupUtils getInstance() {
        instance = new GroupSendGroupUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.sendTextCount = 10;
        this.sendResult = "";
        this.startIndex = 0;
        this.isFirstSelectImg = true;
        this.sendGroupNum = 0;
        this.lastName = "";
        this.operationParameterModel = operationParameterModel2;
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.sayContent = operationParameterModel2.getSayContent();
        this.jumpGroupName = operationParameterModel2.getJumpGroupNames();
        this.localImgUrl = operationParameterModel2.getLocalImgUrl();
        this.startIndexFromUser = operationParameterModel2.getStartIndex();
        this.sendOrder = operationParameterModel2.getSendOrder();
        this.circulateMode = operationParameterModel2.getCirculateMode();
        this.circulateInnerSize = operationParameterModel2.getCirculateInnerSize();
        this.circulateOuterSize = operationParameterModel2.getCirculateOutSize();
        this.isOriginPic = operationParameterModel2.getIsOriginPic();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.picCount = operationParameterModel2.getMaterialPicCount();
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

    public void initAlbumPreviewUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.AlbumPreviewUI_2");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        GroupSendGroupUtils.this.initAlbumPreviewUI();
                        return;
                    }
                    int access$000 = GroupSendGroupUtils.this.picCount;
                    while (true) {
                        access$000--;
                        if (access$000 <= -1 || !MyApplication.enforceable) {
                            GroupSendGroupUtils.this.sleep(100);
                        } else {
                            GroupSendGroupUtils.this.sleep(5);
                            PerformClickUtils.performClick(findViewIdList.get(access$000));
                        }
                    }
                    GroupSendGroupUtils.this.sleep(100);
                    if (GroupSendGroupUtils.this.isOriginPic == 0) {
                        PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "原图");
                        GroupSendGroupUtils.this.sleep(100);
                    }
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.complete_id);
                    if (findViewIdList2 == null || findViewIdList2.size() <= 0) {
                        GroupSendGroupUtils.this.initAlbumPreviewUI();
                    } else if (findViewIdList2.get(0).isEnabled()) {
                        findViewIdList2.get(0).performAction(16);
                        if (GroupSendGroupUtils.this.operationParameterModel.getMessageSendType() != 2) {
                            GroupSendGroupUtils.this.initBackChattingUI();
                        } else if (GroupSendGroupUtils.this.sendOrder == 0) {
                            GroupSendGroupUtils.this.sendText();
                        } else {
                            GroupSendGroupUtils.this.initBackChattingUI();
                        }
                    } else {
                        GroupSendGroupUtils.this.initAlbumPreviewUI();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRoomContact() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, group_list_item_name_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_initRoomContact_sendGroupNum = " + GroupSendGroupUtils.this.sendGroupNum + ",jumpNum= " + GroupSendGroupUtils.this.jumpNum + ",startIndexFromUser = " + GroupSendGroupUtils.this.startIndexFromUser + "");
                if (GroupSendGroupUtils.this.sendGroupNum + GroupSendGroupUtils.this.jumpNum + GroupSendGroupUtils.this.startIndexFromUser <= 1 || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_initRoomContact_从1开始");
                    GroupSendGroupUtils.this.executeSendGroup(GroupSendGroupUtils.this.groupList);
                    return;
                }
                LogUtils.log("WS_BABY_initRoomContact_从" + (GroupSendGroupUtils.this.sendGroupNum + GroupSendGroupUtils.this.jumpNum + GroupSendGroupUtils.this.startIndexFromUser) + "开始");
                GroupSendGroupUtils.this.jumpStartLists.clear();
                int unused = GroupSendGroupUtils.this.scrollCount = 0;
                int unused2 = GroupSendGroupUtils.this.startIndex = 0;
                GroupSendGroupUtils.this.jumpStartPosition();
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
                if (this.scrollCount >= this.sendGroupNum + this.jumpNum + this.startIndexFromUser) {
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
                            int unused = GroupSendGroupUtils.this.startIndex = 1;
                            GroupSendGroupUtils.this.jumpStartPosition();
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
                            GroupSendGroupUtils.this.executeMain();
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
                            int unused = GroupSendGroupUtils.this.startIndex = 1;
                            GroupSendGroupUtils.this.executeSendGroup(list);
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
                            GroupSendGroupUtils.this.executeMain();
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
            this.circulateMode = this.operationParameterModel.getCirculateMode();
            this.circulateInnerSize = this.operationParameterModel.getCirculateInnerSize();
            if (this.spaceTime <= 0 || this.isFirstDelay) {
                this.isFirstDelay = false;
                initRoomContact();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void surplus(int i) {
                    GroupSendGroupUtils.this.updateBottom(i);
                }

                public void end() {
                    GroupSendGroupUtils.this.updateBottom(0);
                    GroupSendGroupUtils.this.initRoomContact();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initInputSayContent() {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                GroupSendGroupUtils.this.sleep(50);
                if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupUtils.this.autoService, "按住 说话")) {
                    PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendGroupUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeIdSyn(GroupSendGroupUtils.this.autoService, BaseServiceUtils.input_edit_text_id, 300, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        int i2;
                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendGroupUtils.this.autoService, BaseServiceUtils.input_edit_text_id, GroupSendGroupUtils.this.sayContent);
                        GroupSendGroupUtils.this.sleep(300);
                        PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "发送");
                        GroupSendGroupUtils.access$1210(GroupSendGroupUtils.this);
                        GroupSendGroupUtils.this.updateBottom(0);
                        if (GroupSendGroupUtils.this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                            GroupSendGroupUtils.this.initInputSayContent();
                            return;
                        }
                        GroupSendGroupUtils.access$408(GroupSendGroupUtils.this);
                        if (GroupSendGroupUtils.this.startIndexFromUser == 1) {
                            i2 = GroupSendGroupUtils.this.sendGroupNum + 1 + GroupSendGroupUtils.this.jumpNum;
                        } else {
                            i2 = GroupSendGroupUtils.this.sendGroupNum + GroupSendGroupUtils.this.startIndexFromUser + GroupSendGroupUtils.this.jumpNum;
                        }
                        GroupSendGroupUtils.this.saveRecord(i2);
                        GroupSendGroupUtils.this.updateBottom(0);
                        PerformClickUtils.executedBackHome(GroupSendGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupUtils.this.executeMain();
                            }
                        });
                    }
                });
            }
        });
    }

    public void initFirstChattingUI() {
        this.sendTextCount = 10;
        LogUtils.log("WS_BABY.ChattingUI_1");
        switch (this.operationParameterModel.getMessageSendType()) {
            case 0:
                initInputSayContent();
                return;
            case 1:
                new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                            PerformClickUtils.inputText(GroupSendGroupUtils.this.autoService, "");
                            GroupSendGroupUtils.this.sleep(100);
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                        } else {
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                        }
                        new PerformClickUtils2().checkStringsFromPhotos(GroupSendGroupUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendGroupUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 200, 100, 60, new PerformClickUtils2.OnCheckListener5() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                boolean unused = GroupSendGroupUtils.this.isFirstSelectImg = false;
                                switch (i) {
                                    case 0:
                                        LogUtils.log("WS_BABY.ChattingUI_2");
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendGroupUtils.this.initAlbumPreviewUI();
                                        return;
                                    case 1:
                                        LogUtils.log("WS_BABY.ChattingUI_3");
                                        GroupSendGroupUtils.this.autoService.performGlobalAction(1);
                                        GroupSendGroupUtils.this.sleep(350);
                                        if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupUtils.this.autoService, "相册")) {
                                            LogUtils.log("WS_BABY.ChattingUI_4");
                                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                            GroupSendGroupUtils.this.initAlbumPreviewUI();
                                            return;
                                        }
                                        LogUtils.log("WS_BABY.ChattingUI_5");
                                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                            PerformClickUtils.inputText(GroupSendGroupUtils.this.autoService, "");
                                            GroupSendGroupUtils.this.sleep(100);
                                        }
                                        LogUtils.log("WS_BABY.ChattingUI_6");
                                        PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                                        GroupSendGroupUtils.this.sleep(350);
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendGroupUtils.this.initAlbumPreviewUI();
                                        return;
                                    default:
                                        return;
                                }
                            }

                            public void execute(int i) {
                                if (i == 30) {
                                    LogUtils.log("WS_BABY.ChattingUI_7");
                                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                        LogUtils.log("WS_BABY.ChattingUI_8");
                                        GroupSendGroupUtils.this.autoService.performGlobalAction(1);
                                        return;
                                    }
                                    LogUtils.log("WS_BABY.ChattingUI_9");
                                    PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                                }
                            }
                        });
                    }
                });
                return;
            case 2:
                if (this.sayContent != null && !this.sayContent.equals("")) {
                    new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            if (GroupSendGroupUtils.this.sendOrder == 0) {
                                GroupSendGroupUtils.this.sendImg();
                            } else {
                                GroupSendGroupUtils.this.sendText();
                            }
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("") && MyApplication.enforceable) {
            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, send_add_id)) {
                PerformClickUtils.inputText(this.autoService, "");
                sleep(100);
                PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
            } else {
                PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
            }
            new PerformClickUtils2().checkStringsFromPhotos(this.autoService, "相册", "你可能要发送的照片", this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 200, 100, 60, new PerformClickUtils2.OnCheckListener5() {
                public void nuFind() {
                }

                public void find(int i) {
                    boolean unused = GroupSendGroupUtils.this.isFirstSelectImg = false;
                    switch (i) {
                        case 0:
                            System.out.println("WS_BABY_sendImg.1");
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendGroupUtils.this.initAlbumPreviewUI();
                            return;
                        case 1:
                            System.out.println("WS_BABY_sendImg.2");
                            GroupSendGroupUtils.this.autoService.performGlobalAction(1);
                            GroupSendGroupUtils.this.sleep(350);
                            if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupUtils.this.autoService, "相册")) {
                                System.out.println("WS_BABY_sendImg.3");
                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                GroupSendGroupUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            System.out.println("WS_BABY_sendImg.4");
                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                PerformClickUtils.inputText(GroupSendGroupUtils.this.autoService, "");
                                GroupSendGroupUtils.this.sleep(100);
                            }
                            System.out.println("WS_BABY_sendImg.5");
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                            GroupSendGroupUtils.this.sleep(350);
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendGroupUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendGroupUtils.this.initAlbumPreviewUI();
                            return;
                        default:
                            return;
                    }
                }

                public void execute(int i) {
                    if (i == 30) {
                        System.out.println("WS_BABY_sendImg.6");
                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                            GroupSendGroupUtils.this.autoService.performGlobalAction(1);
                        } else {
                            PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.send_add_id);
                        }
                    }
                }
            });
        }
    }

    public void sendText() {
        new PerformClickUtils2().check(this.autoService, voice_left_id, this.sendOrder == 0 ? SocializeConstants.CANCLE_RESULTCODE : 100, 100, 1200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(GroupSendGroupUtils.this.autoService, "按住 说话")) {
                    PerformClickUtils.findViewIdAndClick(GroupSendGroupUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendGroupUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeIdSyn(GroupSendGroupUtils.this.autoService, BaseServiceUtils.input_edit_text_id, 300, 100, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendGroupUtils.this.autoService, BaseServiceUtils.input_edit_text_id, GroupSendGroupUtils.this.sayContent);
                        new PerformClickUtils2().checkString(GroupSendGroupUtils.this.autoService, "发送", 100, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "发送");
                                if (GroupSendGroupUtils.this.operationParameterModel.getMessageSendType() == 0 || GroupSendGroupUtils.this.sendOrder == 0) {
                                    GroupSendGroupUtils.this.initBackChattingUI();
                                } else {
                                    GroupSendGroupUtils.this.sendImg();
                                }
                            }

                            public void nuFind() {
                                GroupSendGroupUtils.access$1410(GroupSendGroupUtils.this);
                                if (GroupSendGroupUtils.this.sendTextCount >= 0) {
                                    GroupSendGroupUtils.this.sendText();
                                }
                            }
                        });
                    }

                    public void nuFind() {
                        GroupSendGroupUtils.access$1410(GroupSendGroupUtils.this);
                        if (GroupSendGroupUtils.this.sendTextCount >= 0) {
                            GroupSendGroupUtils.this.sendText();
                        }
                    }
                });
            }
        });
    }

    public void initBackChattingUI() {
        AnonymousClass13 r9;
        int i;
        int i2;
        int i3;
        String str;
        String str2;
        AutoService autoService;
        PerformClickUtils2 performClickUtils2;
        int i4;
        int i5;
        LogUtils.log("WS_BABY.ChattingUI_2");
        this.circulateInnerSize--;
        try {
            if (this.operationParameterModel.getMessageSendType() != 1 || !MyApplication.enforceable) {
                if (this.operationParameterModel.getMessageSendType() == 2 && MyApplication.enforceable && this.circulateInnerSize <= 0) {
                    this.sendGroupNum++;
                    if (this.startIndexFromUser == 1) {
                        i4 = this.sendGroupNum + 1 + this.jumpNum;
                    } else {
                        i4 = this.sendGroupNum + this.startIndexFromUser + this.jumpNum;
                    }
                    updateBottom(0);
                    saveRecord(i4);
                }
            } else if (this.circulateInnerSize <= 0) {
                this.sendGroupNum++;
                if (this.startIndexFromUser == 1) {
                    i5 = this.sendGroupNum + 1 + this.jumpNum;
                } else {
                    i5 = this.sendGroupNum + this.startIndexFromUser + this.jumpNum;
                }
                updateBottom(0);
                saveRecord(i5);
            }
            updateBottom(0);
            if (this.circulateInnerSize <= 0 && MyApplication.enforceable) {
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                performClickUtils2 = new PerformClickUtils2();
                autoService = this.autoService;
                str2 = "请稍候";
                str = "正在发送中";
                i3 = BannerConfig.DURATION;
                i2 = 100;
                i = 100;
                r9 = new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupUtils.this.executeMain();
                            }
                        });
                    }
                };
                performClickUtils2.checkNilStringTwo(autoService, str2, str, i3, i2, i, r9);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.circulateInnerSize <= 0 && MyApplication.enforceable) {
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                performClickUtils2 = new PerformClickUtils2();
                autoService = this.autoService;
                str2 = "请稍候";
                str = "正在发送中";
                i3 = BannerConfig.DURATION;
                i2 = 100;
                i = 100;
                r9 = new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupUtils.this.executeMain();
                            }
                        });
                    }
                };
            }
        } catch (Throwable th) {
            if (this.circulateInnerSize > 0 || !MyApplication.enforceable) {
                System.out.println("WS_BABY_YYYYYYYYYYYY");
                initFirstChattingUI();
            } else {
                System.out.println("WS_BABY_XXXXXXXXXXXXXXXXX");
                new PerformClickUtils2().checkNilStringTwo(this.autoService, "请稍候", "正在发送中", BannerConfig.DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.executedBackHome(GroupSendGroupUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendGroupUtils.this.executeMain();
                            }
                        });
                    }
                });
            }
            throw th;
        }
        System.out.println("WS_BABY_YYYYYYYYYYYY");
        initFirstChattingUI();
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY.LauncherUI");
            new PerformClickUtils2().checkString(this.autoService, "通讯录", 100, 50, 200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "通讯录");
                    new PerformClickUtils2().checkString(GroupSendGroupUtils.this.autoService, "群聊", BaseServiceUtils.executeSpeedSetting + 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendGroupUtils.this.autoService, "群聊");
                            GroupSendGroupUtils.this.initChatRoomContactUI();
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
            SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_all", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_part", Integer.valueOf(i));
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "img_text_to_group_num_shield", Integer.valueOf(i));
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
                    sb.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                    sb2.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                    sb3.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb4.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb5.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb6.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb7.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb8.append(" 个开始群发，请不要操作微信\n已发送 ");
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
                sb9.append(" 个开始群发，请不要操作微信\n已发送 ");
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
            sb10.append(" 个开始群发，请不要操作微信\n已发送 ");
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
            sb11.append(" 个开始群发，请不要操作微信\n已发送 ");
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
            sb12.append(" 个开始群发，请不要操作微信\n已发送 ");
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
            updateMiddleText(myWindowManager, "群发消息", "发送了" + this.sendGroupNum + "个群");
            return;
        }
        updateMiddleText(this.mMyManager, "群发结束", this.sendResult);
    }

    public void endViewShow() {
        try {
            if (this.operationParameterModel != null) {
                updateBottom(0);
            } else {
                showBottomView(this.mMyManager, "正在向微信群发送消息\n请不要操作微信");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendGroupUtils.this.executeMain();
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
