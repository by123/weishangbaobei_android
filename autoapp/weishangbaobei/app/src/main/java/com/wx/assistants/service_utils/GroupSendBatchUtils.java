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
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendBatchUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendBatchUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public int lastOperationNum = 0;
    private String localImgUrl = "";
    /* access modifiers changed from: private */
    public int maxNum = 200;
    /* access modifiers changed from: private */
    public String middleStr = "";
    /* access modifiers changed from: private */
    public OperationParameterModel model = null;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = 200;
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
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public int shieldNum = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1008(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.operationFirstNum;
        groupSendBatchUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.scrollNum;
        groupSendBatchUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1308(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.currentSelectNum;
        groupSendBatchUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1608(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.shieldNum;
        groupSendBatchUtils.shieldNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.pullRecordNum;
        groupSendBatchUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendBatchUtils groupSendBatchUtils) {
        int i = groupSendBatchUtils.startIndex;
        groupSendBatchUtils.startIndex = i + 1;
        return i;
    }

    private GroupSendBatchUtils() {
    }

    public static GroupSendBatchUtils getInstance() {
        instance = new GroupSendBatchUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.model = operationParameterModel;
        this.jumpPersons = this.model.getTagListPeoples();
        this.sayContent = this.model.getSayContent();
        this.localImgUrl = this.model.getLocalImgUrl();
        this.isFilled = false;
        this.startIndex = 0;
        this.operationFirstNum = 0;
        this.lastName = "";
        this.isFirstSelectImg = true;
        this.sendOrder = operationParameterModel.getSendOrder();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.startPullIndex = this.model.getStartIndex();
        this.lastOperationNum = this.startPullIndex - 1;
        this.maxNum = 200;
        this.pullMaxNum = 200;
        this.pullRecordNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.middleStr = "";
        this.shieldNum = 0;
        this.pullType = this.model.getTagFriendType();
        this.scrollSpeed = operationParameterModel.getScrollSpeed();
    }

    public void initAlbumPreviewUI() {
        try {
            if ((!"7.0.15".equals(wxVersionName) || wxVersionCode != 1660) && wxVersionCode <= 1660) {
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 300, 200, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI_2");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.video_first_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            GroupSendBatchUtils.this.initAlbumPreviewUI();
                            return;
                        }
                        PerformClickUtils.performClick(findViewIdList.get(0));
                        GroupSendBatchUtils.this.initCropImageNewUI();
                    }
                });
            } else {
                new PerformClickUtils2().check(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.33");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                        if (findViewIdList != null && !findViewIdList.isEmpty() && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.66");
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            GroupSendBatchUtils.this.sleep(50);
                            PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "原图");
                            GroupSendBatchUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                GroupSendBatchUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            findViewIdList2.get(0).performAction(16);
                            GroupSendBatchUtils.this.initCropImageNewUI();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.44");
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_61");
            e.printStackTrace();
        }
    }

    public void initCropImageNewUI() {
        try {
            LogUtils.log("WS_BABY.CropImageNewUI");
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "完成", "再发一条", executeSpeedSetting + executeSpeed, 100, 1200, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (i == 0) {
                        LogUtils.log("WS_BABY.CropImageNewUI.1");
                        PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "完成");
                        GroupSendBatchUtils.this.sleep(300);
                        GroupSendBatchUtils.this.continueExecute();
                        return;
                    }
                    GroupSendBatchUtils.this.continueExecute();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_62");
            e.printStackTrace();
        }
    }

    public void continueExecute() {
        this.lastOperationNum = this.startPullIndex + this.pullRecordNum;
        updateBottomText(this.mMyManager, "分批群发消息\n正在执行实际发送操作,请不要操作微信");
        if (this.model.getMessageSendType() == 2) {
            LogUtils.log("WS_BABY.CropImageNewUI.2");
            if (this.sendOrder == 0) {
                LogUtils.log("WS_BABY.CropImageNewUI.3");
                new PerformClickUtils2().checkString(this.autoService, "再发一条", executeSpeedSetting + executeSpeed, 300, 400, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.CropImageNewUI.5");
                        PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "再发一条");
                        GroupSendBatchUtils.this.sendText();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.CropImageNewUI.6");
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.CropImageNewUI.4");
            this.isFilled = true;
            newGroupSend();
            return;
        }
        this.isFilled = true;
        newGroupSend();
    }

    public void sendText() {
        if (this.sayContent != null && !this.sayContent.equals("") && MyApplication.enforceable) {
            new PerformClickUtils2().checkSendAddNodeId(this.autoService, voice_left_id, executeSpeedSetting + executeSpeed, 300, 400, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (PerformClickUtils.findNodeIsExistByText(GroupSendBatchUtils.this.autoService, "按住 说话") && MyApplication.enforceable) {
                        PerformClickUtils.findViewIdAndClick(GroupSendBatchUtils.this.autoService, BaseServiceUtils.voice_left_id);
                        GroupSendBatchUtils.this.sleep(100);
                    }
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.send_add_id) && MyApplication.enforceable) {
                        PerformClickUtils.inputText(GroupSendBatchUtils.this.autoService, "");
                        GroupSendBatchUtils.this.sleep(100);
                    }
                    LogUtils.log("WS_BABY_MassSendMsgUI_TEXT1");
                    PerformClickUtils.inputText(GroupSendBatchUtils.this.autoService, GroupSendBatchUtils.this.sayContent);
                    new Thread(new Runnable() {
                        public void run() {
                            GroupSendBatchUtils.this.sleep(300);
                            PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "发送");
                            int unused = GroupSendBatchUtils.this.lastOperationNum = GroupSendBatchUtils.this.startPullIndex + GroupSendBatchUtils.this.pullRecordNum;
                            BaseServiceUtils.updateBottomText(GroupSendBatchUtils.this.mMyManager, "分批群发消息\n正在执行实际发送操作,请不要操作微信");
                            GroupSendBatchUtils.this.sleep(1500);
                            if (GroupSendBatchUtils.this.model.getMessageSendType() == 0 || GroupSendBatchUtils.this.sendOrder == 0) {
                                int unused2 = GroupSendBatchUtils.this.startIndex = 0;
                                boolean unused3 = GroupSendBatchUtils.this.isFilled = true;
                                LogUtils.log("WS_BABY.newGroupSend1");
                                GroupSendBatchUtils.this.newGroupSend();
                                return;
                            }
                            new PerformClickUtils2().checkString(GroupSendBatchUtils.this.autoService, "再发一条", 50, 300, 400, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.MassSendHistoryUI_22");
                                    PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "再发一条");
                                    GroupSendBatchUtils.this.sendImg();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.MassSendHistoryUI_222");
                                }
                            });
                        }
                    }).start();
                }
            });
        }
    }

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("") && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_MassSendMsgUI_IMG");
            new PerformClickUtils2().check(this.autoService, voice_left_id, executeSpeedSetting + executeSpeed, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    GroupSendBatchUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendBatchUtils.this.autoService, "我的收藏");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendBatchUtils.this.autoService, "拍摄");
                    if (!findNodeIsExistByText && !findNodeIsExistByText2) {
                        PerformClickUtils.inputText(GroupSendBatchUtils.this.autoService, "");
                        GroupSendBatchUtils.this.sleep(100);
                        PerformClickUtils.findViewIdAndClick(GroupSendBatchUtils.this.autoService, BaseServiceUtils.send_add_id);
                    }
                    new PerformClickUtils2().checkStringsFromPhotos(GroupSendBatchUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendBatchUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 400, 200, 65, new PerformClickUtils2.OnCheckListener5() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            boolean unused = GroupSendBatchUtils.this.isFirstSelectImg = false;
                            switch (i) {
                                case 0:
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.0");
                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendBatchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                    GroupSendBatchUtils.this.initAlbumPreviewUI();
                                    return;
                                case 1:
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.1");
                                    GroupSendBatchUtils.this.autoService.performGlobalAction(1);
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.2");
                                    GroupSendBatchUtils.this.sleep(350);
                                    if (PerformClickUtils.findNodeIsExistByText(GroupSendBatchUtils.this.autoService, "相册")) {
                                        LogUtils.log("WS_BABY.checkStringsFromPhotos.3");
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendBatchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendBatchUtils.this.initAlbumPreviewUI();
                                        return;
                                    }
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.4");
                                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                        PerformClickUtils.inputText(GroupSendBatchUtils.this.autoService, "");
                                        GroupSendBatchUtils.this.sleep(100);
                                    }
                                    PerformClickUtils.findViewIdAndClick(GroupSendBatchUtils.this.autoService, BaseServiceUtils.send_add_id);
                                    GroupSendBatchUtils.this.sleep(400);
                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendBatchUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                    GroupSendBatchUtils.this.initAlbumPreviewUI();
                                    return;
                                default:
                                    return;
                            }
                        }

                        public void execute(int i) {
                            if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                GroupSendBatchUtils.this.autoService.performGlobalAction(1);
                            }
                        }
                    });
                }
            });
        }
    }

    public void typeSend() {
        try {
            switch (this.model.getMessageSendType()) {
                case 0:
                    sendText();
                    return;
                case 1:
                    sendImg();
                    return;
                case 2:
                    if (this.sendOrder == 0) {
                        sendImg();
                        return;
                    } else {
                        sendText();
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_64");
            e.printStackTrace();
        }
    }

    public void executeSelectContact() {
        LogUtils.log("WS_BABY.MassSendSelectContactUI_4");
        new PerformClickUtils2().check(this.autoService, list_select_checkbox, 100, 300, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_START_SELECT_CONTACT");
                GroupSendBatchUtils.this.whileExecuteTask();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.MassSendSelectContactUI_5_nuFind");
            }
        });
    }

    public void whileExecuteTask() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    if (GroupSendBatchUtils.this.nameNodes == null) {
                        GroupSendBatchUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                    }
                    if (GroupSendBatchUtils.this.nameNodes == null || GroupSendBatchUtils.this.nameNodes.size() <= 0 || !MyApplication.enforceable) {
                        new Thread(new Runnable() {
                            public void run() {
                                GroupSendBatchUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                GroupSendBatchUtils.this.whileExecuteTask();
                            }
                        }).start();
                        return;
                    }
                    String str = "";
                    AccessibilityNodeInfo accessibilityNodeInfo = GroupSendBatchUtils.this.nameNodes.get(GroupSendBatchUtils.this.nameNodes.size() - 1);
                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                        String str2 = accessibilityNodeInfo.getText() + "";
                        if (str2 != null && !"".equals(str2)) {
                            str = str2;
                        }
                        Rect rect = new Rect();
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        String unused = GroupSendBatchUtils.this.lastName = str + rect.toString();
                    }
                    if (GroupSendBatchUtils.this.operationFirstNum == GroupSendBatchUtils.this.maxNum && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_excSelectTask.8");
                        int unused2 = GroupSendBatchUtils.this.lastOperationNum = GroupSendBatchUtils.this.startPullIndex + GroupSendBatchUtils.this.pullRecordNum;
                        int unused3 = GroupSendBatchUtils.this.operationFirstNum = 0;
                        int unused4 = GroupSendBatchUtils.this.scrollNum = 1;
                        if (GroupSendBatchUtils.this.currentSelectNum == 0) {
                            LogUtils.log("WS_BABY_excSelectTask.88");
                            PerformClickUtils.performBack(GroupSendBatchUtils.this.autoService);
                            GroupSendBatchUtils.this.sleep(BannerConfig.DURATION);
                            return;
                        }
                        LogUtils.log("WS_BABY_excSelectTask.888");
                        GroupSendBatchUtils.this.confirmCompleted(GroupSendBatchUtils.this.lastOperationNum);
                    } else if (GroupSendBatchUtils.this.pullRecordNum >= GroupSendBatchUtils.this.pullMaxNum) {
                        LogUtils.log("WS_BABY_excSelectTask.9");
                        boolean unused5 = GroupSendBatchUtils.this.isFilled = true;
                        int unused6 = GroupSendBatchUtils.this.lastOperationNum = GroupSendBatchUtils.this.startPullIndex + GroupSendBatchUtils.this.pullRecordNum;
                        int unused7 = GroupSendBatchUtils.this.scrollNum = 1;
                        int unused8 = GroupSendBatchUtils.this.operationFirstNum = 0;
                        GroupSendBatchUtils.this.confirmCompleted(GroupSendBatchUtils.this.lastOperationNum);
                    } else {
                        if (GroupSendBatchUtils.this.nameNodes == null) {
                            GroupSendBatchUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                        }
                        if (GroupSendBatchUtils.this.startIndex < GroupSendBatchUtils.this.nameNodes.size()) {
                            LogUtils.log("WS_BABY_excSelectTask.startIndex = " + GroupSendBatchUtils.this.startIndex + ",namesize = " + GroupSendBatchUtils.this.nameNodes.size());
                            LogUtils.log("WS_BABY_excSelectTask.slast = " + GroupSendBatchUtils.this.lastOperationNum + "，，，pull = " + GroupSendBatchUtils.this.pullRecordNum + "，，，start = " + GroupSendBatchUtils.this.startPullIndex);
                            String str3 = "";
                            try {
                                AccessibilityNodeInfo accessibilityNodeInfo2 = GroupSendBatchUtils.this.nameNodes.get(GroupSendBatchUtils.this.startIndex);
                                if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                    String str4 = accessibilityNodeInfo2.getText() + "";
                                    if (str4 != null) {
                                        try {
                                            if (!"".equals(str4)) {
                                                LogUtils.log("WS_BABY_excSelectTask.name = " + str4);
                                            }
                                        } catch (Exception unused9) {
                                        }
                                    }
                                    str3 = str4;
                                }
                            } catch (Exception unused10) {
                            }
                            if (GroupSendBatchUtils.this.scrollNum < GroupSendBatchUtils.this.lastOperationNum) {
                                GroupSendBatchUtils.access$608(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.access$1208(GroupSendBatchUtils.this);
                                BaseServiceUtils.updateBottomText(GroupSendBatchUtils.this.mMyManager, "正在分批群发消息\n已跳过 " + GroupSendBatchUtils.this.scrollNum + " 个");
                                GroupSendBatchUtils.this.whileExecuteTask();
                                return;
                            }
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_checkbox);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || GroupSendBatchUtils.this.startIndex >= findViewIdList.size()) {
                                LogUtils.log("WS_BABY_excSelectTask.7");
                                GroupSendBatchUtils.access$608(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.access$308(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.access$1008(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.this.whileExecuteTask();
                                return;
                            }
                            LogUtils.log("WS_BABY_excSelectTask.0");
                            AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList.get(GroupSendBatchUtils.this.startIndex);
                            if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.isChecked() || GroupSendBatchUtils.this.jumpPersons.contains(str3)) {
                                GroupSendBatchUtils.access$608(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.access$308(GroupSendBatchUtils.this);
                                GroupSendBatchUtils.access$1008(GroupSendBatchUtils.this);
                                if (GroupSendBatchUtils.this.jumpPersons.contains(str3)) {
                                    GroupSendBatchUtils.access$1608(GroupSendBatchUtils.this);
                                }
                                GroupSendBatchUtils.this.whileExecuteTask();
                                return;
                            }
                            LogUtils.log("WS_BABY_excSelectTask.2");
                            GroupSendBatchUtils.access$608(GroupSendBatchUtils.this);
                            GroupSendBatchUtils.access$308(GroupSendBatchUtils.this);
                            GroupSendBatchUtils.access$1008(GroupSendBatchUtils.this);
                            GroupSendBatchUtils.access$1308(GroupSendBatchUtils.this);
                            GroupSendBatchUtils.this.sleep(5);
                            PerformClickUtils.performClick(accessibilityNodeInfo3);
                            if (GroupSendBatchUtils.this.pullType == 0) {
                                BaseServiceUtils.updateBottomText(GroupSendBatchUtils.this.mMyManager, "正在分批群发消息\n已选择 " + GroupSendBatchUtils.this.currentSelectNum + " 个");
                            } else {
                                BaseServiceUtils.updateBottomText(GroupSendBatchUtils.this.mMyManager, "正在分批群发消息\n已选择 " + GroupSendBatchUtils.this.currentSelectNum + " 个 ，已屏蔽 " + GroupSendBatchUtils.this.shieldNum + " 个");
                            }
                            GroupSendBatchUtils.this.whileExecuteTask();
                        } else if (GroupSendBatchUtils.this.startIndex >= GroupSendBatchUtils.this.nameNodes.size()) {
                            GroupSendBatchUtils.this.nameNodes = null;
                            PerformClickUtils.scroll(GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_id);
                            int unused11 = GroupSendBatchUtils.this.startIndex = 1;
                            new PerformClickUtils2().check(GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_name_id, (GroupSendBatchUtils.this.currentSelectNum != 0 || GroupSendBatchUtils.this.lastOperationNum - GroupSendBatchUtils.this.scrollNum <= 30) ? 350 : GroupSendBatchUtils.this.scrollSpeed, 5, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    AccessibilityNodeInfo accessibilityNodeInfo;
                                    System.out.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                    GroupSendBatchUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                    if (GroupSendBatchUtils.this.nameNodes == null || GroupSendBatchUtils.this.nameNodes.size() <= 0) {
                                        GroupSendBatchUtils.this.whileExecuteTask();
                                        return;
                                    }
                                    String str = "";
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = GroupSendBatchUtils.this.nameNodes.get(GroupSendBatchUtils.this.nameNodes.size() - 1);
                                    if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                        String str2 = accessibilityNodeInfo2.getText() + "";
                                        if (str2 != null && !"".equals(str2)) {
                                            str = str2;
                                        }
                                        Rect rect = new Rect();
                                        accessibilityNodeInfo2.getBoundsInScreen(rect);
                                        str = str + rect.toString();
                                    }
                                    LogUtils.log("WS_BABY_excSelectTask.11.lastname = " + str);
                                    if (str.equals(GroupSendBatchUtils.this.lastName)) {
                                        GroupSendBatchUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendBatchUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                        if (!(findViewIdList == null || findViewIdList.size() <= 0 || (accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1)) == null || accessibilityNodeInfo.getText() == null)) {
                                            String str3 = accessibilityNodeInfo.getText() + "";
                                            if (str3 == null || "".equals(str3)) {
                                                str3 = "";
                                            }
                                            Rect rect2 = new Rect();
                                            accessibilityNodeInfo.getBoundsInScreen(rect2);
                                            str = str3 + rect2.toString();
                                        }
                                        if (str.equals(GroupSendBatchUtils.this.lastName)) {
                                            boolean unused = GroupSendBatchUtils.this.isFilled = true;
                                            int unused2 = GroupSendBatchUtils.this.lastOperationNum = GroupSendBatchUtils.this.startPullIndex + GroupSendBatchUtils.this.pullRecordNum;
                                            if (GroupSendBatchUtils.this.currentSelectNum == 0) {
                                                String unused3 = GroupSendBatchUtils.this.middleStr = "您设置的起点 高于 好友的数量，共群发了0人";
                                                BaseServiceUtils.removeEndView(GroupSendBatchUtils.this.mMyManager);
                                                return;
                                            }
                                            GroupSendBatchUtils.this.confirmCompleted(1);
                                            return;
                                        }
                                        GroupSendBatchUtils.this.whileExecuteTask();
                                        return;
                                    }
                                    GroupSendBatchUtils.this.whileExecuteTask();
                                }
                            });
                        }
                    }
                }
            }).start();
        }
    }

    public void confirmCompleted(int i) {
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        typeSend();
    }

    public void newGroupSend() {
        new PerformClickUtils2().checkNodeIds(this.autoService, new_group_send_big_id, new_group_send_id, executeSpeed, 250, 400, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (MyApplication.enforceable) {
                    if (GroupSendBatchUtils.this.isFilled) {
                        BaseServiceUtils.removeEndView(GroupSendBatchUtils.this.mMyManager);
                        return;
                    }
                    switch (i) {
                        case 0:
                            LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed7");
                            PerformClickUtils.findViewIdAndClick(GroupSendBatchUtils.this.autoService, BaseServiceUtils.new_group_send_big_id);
                            GroupSendBatchUtils.this.sleep(BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2));
                            GroupSendBatchUtils.this.executeSelectContact();
                            return;
                        case 1:
                            LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed8");
                            PerformClickUtils.findViewIdAndClick(GroupSendBatchUtils.this.autoService, BaseServiceUtils.new_group_send_id);
                            GroupSendBatchUtils.this.sleep(BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2));
                            GroupSendBatchUtils.this.executeSelectContact();
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "我");
            new PerformClickUtils2().checkString(this.autoService, "设置", executeSpeed + (executeSpeedSetting / 2), 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "设置");
                    new PerformClickUtils2().checkString(GroupSendBatchUtils.this.autoService, "通用", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 200, 60, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "通用");
                            new PerformClickUtils2().checkString(GroupSendBatchUtils.this.autoService, "辅助功能", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 200, 60, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "辅助功能");
                                    new PerformClickUtils2().checkString(GroupSendBatchUtils.this.autoService, "群发助手", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 200, 50, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "群发助手");
                                            new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendBatchUtils.this.autoService, "启用该功能", "开始群发", (BaseServiceUtils.executeSpeedSetting / 2) + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    if (i == 0) {
                                                        PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "启用该功能");
                                                        new PerformClickUtils2().checkString(GroupSendBatchUtils.this.autoService, "开始群发", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "开始群发");
                                                                GroupSendBatchUtils.this.newGroupSend();
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    PerformClickUtils.findTextAndClick(GroupSendBatchUtils.this.autoService, "开始群发");
                                                    GroupSendBatchUtils.this.newGroupSend();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_67");
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        if (this.middleStr == null || "".equals(this.middleStr)) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "分批群发好友结束", "一共发送了" + this.currentSelectNum + "个好友");
            return;
        }
        updateMiddleText(this.mMyManager, "分批群发好友结束", this.middleStr);
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "正在群发[ " + (this.lastOperationNum + 1) + "-" + (this.lastOperationNum + 200) + " ]个好友，请不要操作微信");
        LogUtils.log("WS_BABY_END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    GroupSendBatchUtils.this.executeMain();
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
