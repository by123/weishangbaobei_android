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
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendFriendsUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
    private boolean isNextIng = false;
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
    public int pullMaxNum = 5000;
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

    static /* synthetic */ int access$1108(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.scrollNum;
        groupSendFriendsUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.currentSelectNum;
        groupSendFriendsUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1608(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.shieldNum;
        groupSendFriendsUtils.shieldNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.pullRecordNum;
        groupSendFriendsUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.startIndex;
        groupSendFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendFriendsUtils groupSendFriendsUtils) {
        int i = groupSendFriendsUtils.operationFirstNum;
        groupSendFriendsUtils.operationFirstNum = i + 1;
        return i;
    }

    private GroupSendFriendsUtils() {
    }

    public static GroupSendFriendsUtils getInstance() {
        instance = new GroupSendFriendsUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.model = operationParameterModel;
        this.jumpPersons = this.model.getTagListPeoples();
        this.sayContent = this.model.getSayContent();
        this.localImgUrl = this.model.getLocalImgUrl();
        this.isNextIng = false;
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
        this.pullMaxNum = 5000;
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
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI_2");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.video_first_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            GroupSendFriendsUtils.this.initAlbumPreviewUI();
                            return;
                        }
                        PerformClickUtils.performClick(findViewIdList.get(0));
                        GroupSendFriendsUtils.this.initCropImageNewUI();
                    }
                });
            } else {
                new PerformClickUtils2().check(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.33");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                        if (findViewIdList != null && !findViewIdList.isEmpty() && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.66");
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            GroupSendFriendsUtils.this.sleep(50);
                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "原图");
                            GroupSendFriendsUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                GroupSendFriendsUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            findViewIdList2.get(0).performAction(16);
                            GroupSendFriendsUtils.this.initCropImageNewUI();
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
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "完成", "再发一条", executeSpeedSetting + executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "完成");
                        GroupSendFriendsUtils.this.sleep(100);
                        GroupSendFriendsUtils.this.continueExecute();
                        return;
                    }
                    GroupSendFriendsUtils.this.continueExecute();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_62");
            e.printStackTrace();
        }
    }

    public void continueExecute() {
        this.lastOperationNum = this.startPullIndex + this.pullRecordNum;
        saveRecord(this.lastOperationNum);
        updateBottomText(this.mMyManager, "分批群发消息\n正在执行实际发送操作,请不要操作微信");
        if (this.model.getMessageSendType() != 2) {
            newGroupSend();
        } else if (this.sendOrder == 0) {
            new PerformClickUtils2().checkString(this.autoService, "再发一条", executeSpeedSetting + executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.MassSendHistoryUI_22");
                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "再发一条");
                    GroupSendFriendsUtils.this.sendText();
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.MassSendHistoryUI_222");
                }
            });
        } else {
            newGroupSend();
        }
    }

    public void sendText() {
        if (this.sayContent != null && !this.sayContent.equals("") && MyApplication.enforceable) {
            new PerformClickUtils2().checkSendAddNodeId(this.autoService, voice_left_id, executeSpeedSetting + executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (PerformClickUtils.findNodeIsExistByText(GroupSendFriendsUtils.this.autoService, "按住 说话") && MyApplication.enforceable) {
                        PerformClickUtils.findViewIdAndClick(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id);
                        GroupSendFriendsUtils.this.sleep(100);
                    }
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id) && MyApplication.enforceable) {
                        PerformClickUtils.inputText(GroupSendFriendsUtils.this.autoService, "");
                        GroupSendFriendsUtils.this.sleep(100);
                    }
                    LogUtils.log("WS_BABY_MassSendMsgUI_TEXT1");
                    PerformClickUtils.inputText(GroupSendFriendsUtils.this.autoService, GroupSendFriendsUtils.this.sayContent);
                    new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "发送", 80, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "发送");
                            int unused = GroupSendFriendsUtils.this.lastOperationNum = GroupSendFriendsUtils.this.startPullIndex + GroupSendFriendsUtils.this.pullRecordNum;
                            GroupSendFriendsUtils.this.saveRecord(GroupSendFriendsUtils.this.lastOperationNum);
                            BaseServiceUtils.updateBottomText(GroupSendFriendsUtils.this.mMyManager, "分批群发消息\n正在执行实际发送操作,请不要操作微信");
                            GroupSendFriendsUtils.this.sleep(100);
                            if (GroupSendFriendsUtils.this.model.getMessageSendType() == 0 || GroupSendFriendsUtils.this.sendOrder == 0) {
                                int unused2 = GroupSendFriendsUtils.this.startIndex = 0;
                                LogUtils.log("WS_BABY.newGroupSend1");
                                GroupSendFriendsUtils.this.newGroupSend();
                                return;
                            }
                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "再发一条", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.MassSendHistoryUI_22");
                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "再发一条");
                                    GroupSendFriendsUtils.this.sendImg();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.MassSendHistoryUI_222");
                                }
                            });
                        }
                    });
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
                    GroupSendFriendsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendFriendsUtils.this.autoService, "我的收藏");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendFriendsUtils.this.autoService, "拍摄");
                    if (!findNodeIsExistByText && !findNodeIsExistByText2) {
                        LogUtils.log("WS_BABY_MassSendMsgUI_IMG_1");
                        PerformClickUtils.inputText(GroupSendFriendsUtils.this.autoService, "");
                        GroupSendFriendsUtils.this.sleep(100);
                        PerformClickUtils.findViewIdAndClick(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    }
                    LogUtils.log("WS_BABY_MassSendMsgUI_IMG_2");
                    new PerformClickUtils2().checkStringsFromPhotos(GroupSendFriendsUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendFriendsUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 400, 200, 65, new PerformClickUtils2.OnCheckListener5() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            boolean unused = GroupSendFriendsUtils.this.isFirstSelectImg = false;
                            switch (i) {
                                case 0:
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.0" + BaseServiceUtils.album_id);
                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.1");
                                    GroupSendFriendsUtils.this.initAlbumPreviewUI();
                                    return;
                                case 1:
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.1");
                                    GroupSendFriendsUtils.this.autoService.performGlobalAction(1);
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.2");
                                    GroupSendFriendsUtils.this.sleep(350);
                                    if (PerformClickUtils.findNodeIsExistByText(GroupSendFriendsUtils.this.autoService, "相册")) {
                                        LogUtils.log("WS_BABY.checkStringsFromPhotos.3");
                                        PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                        GroupSendFriendsUtils.this.initAlbumPreviewUI();
                                        return;
                                    }
                                    LogUtils.log("WS_BABY.checkStringsFromPhotos.4");
                                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                        PerformClickUtils.inputText(GroupSendFriendsUtils.this.autoService, "");
                                        GroupSendFriendsUtils.this.sleep(100);
                                    }
                                    PerformClickUtils.findViewIdAndClick(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                                    GroupSendFriendsUtils.this.sleep(400);
                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                    GroupSendFriendsUtils.this.initAlbumPreviewUI();
                                    return;
                                default:
                                    return;
                            }
                        }

                        public void execute(int i) {
                            if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                GroupSendFriendsUtils.this.autoService.performGlobalAction(1);
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
        new PerformClickUtils2().check(this.autoService, list_select_checkbox, (executeSpeedSetting / 2) + executeSpeed, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_whileExecuteTask.8");
                GroupSendFriendsUtils.this.whileExecuteTask();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.MassSendSelectContactUI_5_nuFind");
            }
        });
    }

    public void whileExecuteTask() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                /* JADX WARNING: Can't wrap try/catch for region: R(7:37|38|39|(3:43|(4:45|46|47|(1:49))|50)|52|53|(2:55|95)(2:56|(2:78|98)(6:62|(2:66|(1:68)(4:69|(1:71)(1:72)|73|96))|74|(1:76)|77|97))) */
                /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0245 */
                /* JADX WARNING: Removed duplicated region for block: B:55:0x0253 A[Catch:{ Exception -> 0x0455 }] */
                /* JADX WARNING: Removed duplicated region for block: B:56:0x0295 A[Catch:{ Exception -> 0x0455 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r11 = this;
                        java.lang.String r0 = "WS_BABY_excSelectTask.入口"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x0455 }
                        if (r0 != 0) goto L_0x0020
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x0455 }
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0455 }
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x0455 }
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.入口.1"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                    L_0x0020:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x0455 }
                        if (r0 == 0) goto L_0x0441
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.size()     // Catch:{ Exception -> 0x0455 }
                        if (r0 <= 0) goto L_0x0441
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0455 }
                        if (r0 == 0) goto L_0x0441
                        java.lang.String r0 = ""
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r2 = r2.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.size()     // Catch:{ Exception -> 0x0455 }
                        r3 = 1
                        int r2 = r2 - r3
                        java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x0455 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x0455 }
                        if (r1 == 0) goto L_0x00ac
                        java.lang.CharSequence r2 = r1.getText()     // Catch:{ Exception -> 0x0455 }
                        if (r2 == 0) goto L_0x00ac
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r2.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.CharSequence r4 = r1.getText()     // Catch:{ Exception -> 0x0455 }
                        r2.append(r4)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r4 = ""
                        r2.append(r4)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0455 }
                        if (r2 == 0) goto L_0x0072
                        java.lang.String r4 = ""
                        boolean r4 = r4.equals(r2)     // Catch:{ Exception -> 0x0455 }
                        if (r4 != 0) goto L_0x0072
                        r0 = r2
                    L_0x0072:
                        android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ Exception -> 0x0455 }
                        r2.<init>()     // Catch:{ Exception -> 0x0455 }
                        r1.getBoundsInScreen(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r4.<init>()     // Catch:{ Exception -> 0x0455 }
                        r4.append(r0)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0455 }
                        r4.append(r0)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String unused = r1.lastName = r0     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r0.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.111111111111.lastname = "
                        r0.append(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.lastName     // Catch:{ Exception -> 0x0455 }
                        r0.append(r1)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0455 }
                    L_0x00ac:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.operationFirstNum     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.maxNum     // Catch:{ Exception -> 0x0455 }
                        r2 = 0
                        if (r0 != r1) goto L_0x0125
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0455 }
                        if (r0 == 0) goto L_0x0125
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.8"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r4 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1 + r4
                        int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        r0.saveRecord(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x0455 }
                        if (r0 != 0) goto L_0x010c
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.88"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.utils.PerformClickUtils.performBack(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r1 = 800(0x320, float:1.121E-42)
                        r0.sleep(r1)     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x010c:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x0455 }
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.888"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x0125:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.pullRecordNum     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.pullMaxNum     // Catch:{ Exception -> 0x0455 }
                        if (r0 < r1) goto L_0x016d
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.9"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        boolean unused = r0.isFilled = r3     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r4 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1 + r4
                        int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x016d:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x0455 }
                        if (r0 != 0) goto L_0x0181
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x0455 }
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0455 }
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x0455 }
                    L_0x0181:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.startIndex     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.size()     // Catch:{ Exception -> 0x0455 }
                        if (r0 >= r1) goto L_0x03b6
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "WS_BABY_excSelectTask.startIndex = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.startIndex     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = ",namesize = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r2 = r2.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.size()     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "WS_BABY_excSelectTask.last = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "，pulled = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.pullRecordNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "，start = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.startPullIndex     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r0 = ""
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0245 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x0245 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0245 }
                        int r2 = r2.startIndex     // Catch:{ Exception -> 0x0245 }
                        java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x0245 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x0245 }
                        if (r1 == 0) goto L_0x0245
                        java.lang.CharSequence r2 = r1.getText()     // Catch:{ Exception -> 0x0245 }
                        if (r2 == 0) goto L_0x0245
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0245 }
                        r2.<init>()     // Catch:{ Exception -> 0x0245 }
                        java.lang.CharSequence r1 = r1.getText()     // Catch:{ Exception -> 0x0245 }
                        r2.append(r1)     // Catch:{ Exception -> 0x0245 }
                        java.lang.String r1 = ""
                        r2.append(r1)     // Catch:{ Exception -> 0x0245 }
                        java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0245 }
                        if (r1 == 0) goto L_0x0244
                        java.lang.String r0 = ""
                        boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0244 }
                        if (r0 != 0) goto L_0x0244
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0244 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0244 }
                        r2.<init>()     // Catch:{ Exception -> 0x0244 }
                        java.lang.String r3 = "WS_BABY_excSelectTask.name = "
                        r2.append(r3)     // Catch:{ Exception -> 0x0244 }
                        r2.append(r1)     // Catch:{ Exception -> 0x0244 }
                        java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0244 }
                        r0.println(r2)     // Catch:{ Exception -> 0x0244 }
                    L_0x0244:
                        r0 = r1
                    L_0x0245:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.scrollNum     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        if (r1 >= r2) goto L_0x0295
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.跳过 "
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$608(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$1108(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "正在分批群发消息\n已跳过 "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.scrollNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = " 个"
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x0455 }
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_whileExecuteTask.1"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x0295:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_checkbox     // Catch:{ Exception -> 0x0455 }
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0455 }
                        if (r1 == 0) goto L_0x0399
                        int r2 = r1.size()     // Catch:{ Exception -> 0x0455 }
                        if (r2 <= 0) goto L_0x0399
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.startIndex     // Catch:{ Exception -> 0x0455 }
                        int r3 = r1.size()     // Catch:{ Exception -> 0x0455 }
                        if (r2 >= r3) goto L_0x0399
                        java.io.PrintStream r2 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r3 = "WS_BABY_excSelectTask.0"
                        r2.println(r3)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.startIndex     // Catch:{ Exception -> 0x0455 }
                        java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x0455 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x0455 }
                        if (r1 == 0) goto L_0x036b
                        boolean r2 = r1.isChecked()     // Catch:{ Exception -> 0x0455 }
                        if (r2 != 0) goto L_0x036b
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.LinkedHashSet r2 = r2.jumpPersons     // Catch:{ Exception -> 0x0455 }
                        boolean r2 = r2.contains(r0)     // Catch:{ Exception -> 0x0455 }
                        if (r2 == 0) goto L_0x02dc
                        goto L_0x036b
                    L_0x02dc:
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "WS_BABY_excSelectTask.2"
                        r0.println(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$608(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$308(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$908(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$1208(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r2 = 5
                        r0.sleep(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.pullType     // Catch:{ Exception -> 0x0455 }
                        if (r0 != 0) goto L_0x032c
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "正在分批群发消息\n已选择 "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.currentSelectNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = " 个"
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x0455 }
                        goto L_0x035d
                    L_0x032c:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "正在分批群发消息\n已选择 "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.currentSelectNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = " 个 ，已屏蔽 "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.shieldNum     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = " 个"
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x0455 }
                    L_0x035d:
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_whileExecuteTask.3"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x036b:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$608(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$308(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$908(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.LinkedHashSet r1 = r1.jumpPersons     // Catch:{ Exception -> 0x0455 }
                        boolean r0 = r1.contains(r0)     // Catch:{ Exception -> 0x0455 }
                        if (r0 == 0) goto L_0x038b
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$1608(r0)     // Catch:{ Exception -> 0x0455 }
                    L_0x038b:
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_whileExecuteTask.2"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x0399:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$608(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$308(r0)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils.access$908(r0)     // Catch:{ Exception -> 0x0455 }
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_whileExecuteTask.4"
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x03b6:
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.startIndex     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.size()     // Catch:{ Exception -> 0x0455 }
                        if (r0 < r1) goto L_0x0455
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = "WS_BABY_TIME. 开始滚动 = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.startIndex     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r2 = ","
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r2 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r2 = r2.nameNodes     // Catch:{ Exception -> 0x0455 }
                        int r2 = r2.size()     // Catch:{ Exception -> 0x0455 }
                        r1.append(r2)     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0455 }
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        r1 = 0
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_id     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.utils.PerformClickUtils.scroll(r0, r1)     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int unused = r0.startIndex = r3     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.utils.PerformClickUtils2 r4 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x0455 }
                        r4.<init>()     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service.AutoService r5 = r0.autoService     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r6 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x0455 }
                        if (r0 != 0) goto L_0x0431
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.lastOperationNum     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r1 = r1.scrollNum     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0 - r1
                        r1 = 30
                        if (r0 <= r1) goto L_0x0431
                        com.wx.assistants.service_utils.GroupSendFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendFriendsUtils.this     // Catch:{ Exception -> 0x0455 }
                        int r0 = r0.scrollSpeed     // Catch:{ Exception -> 0x0455 }
                        r7 = r0
                        goto L_0x0435
                    L_0x0431:
                        r0 = 350(0x15e, float:4.9E-43)
                        r7 = 350(0x15e, float:4.9E-43)
                    L_0x0435:
                        r8 = 5
                        r9 = 200(0xc8, float:2.8E-43)
                        com.wx.assistants.service_utils.GroupSendFriendsUtils$8$1 r10 = new com.wx.assistants.service_utils.GroupSendFriendsUtils$8$1     // Catch:{ Exception -> 0x0455 }
                        r10.<init>()     // Catch:{ Exception -> 0x0455 }
                        r4.check((com.wx.assistants.service.AutoService) r5, (java.lang.String) r6, (int) r7, (int) r8, (int) r9, (com.wx.assistants.utils.PerformClickUtils2.OnCheckListener) r10)     // Catch:{ Exception -> 0x0455 }
                        goto L_0x0455
                    L_0x0441:
                        java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x0455 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.888888888888888888."
                        r0.println(r1)     // Catch:{ Exception -> 0x0455 }
                        java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x0455 }
                        com.wx.assistants.service_utils.GroupSendFriendsUtils$8$2 r1 = new com.wx.assistants.service_utils.GroupSendFriendsUtils$8$2     // Catch:{ Exception -> 0x0455 }
                        r1.<init>()     // Catch:{ Exception -> 0x0455 }
                        r0.<init>(r1)     // Catch:{ Exception -> 0x0455 }
                        r0.start()     // Catch:{ Exception -> 0x0455 }
                    L_0x0455:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.GroupSendFriendsUtils.AnonymousClass8.run():void");
                }
            }).start();
        }
    }

    public void confirmCompleted(int i) {
        this.isNextIng = true;
        if (this.isFilled) {
            saveRecord(1);
        } else {
            saveRecord(i);
        }
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        sleep(100);
        typeSend();
    }

    public void saveRecord(int i) {
        if (this.pullType == 0) {
            SPUtils.put(MyApplication.getConText(), "batch_text_img_num_all", Integer.valueOf(i));
        } else if (this.pullType == 2) {
            SPUtils.put(MyApplication.getConText(), "batch_text_img_num_shield", Integer.valueOf(i));
        }
    }

    public void newGroupSend() {
        LogUtils.log("WS_BABY.MassSendHistoryUI");
        try {
            new PerformClickUtils2().checkNodeIds(this.autoService, new_group_send_big_id, new_group_send_id, executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (MyApplication.enforceable) {
                        if (GroupSendFriendsUtils.this.isFilled) {
                            BaseServiceUtils.removeEndView(GroupSendFriendsUtils.this.mMyManager);
                            return;
                        }
                        switch (i) {
                            case 0:
                                LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed7");
                                PerformClickUtils.findViewIdAndClick(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.new_group_send_big_id);
                                GroupSendFriendsUtils.this.executeSelectContact();
                                return;
                            case 1:
                                LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed8");
                                PerformClickUtils.findViewIdAndClick(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.new_group_send_id);
                                GroupSendFriendsUtils.this.executeSelectContact();
                                return;
                            default:
                                return;
                        }
                    }
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_66");
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "我");
                    new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "设置", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "设置");
                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "通用", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "通用");
                                    new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "辅助功能", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "辅助功能");
                                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "群发助手", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "群发助手");
                                                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendFriendsUtils.this.autoService, "启用该功能", "开始群发", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                        public void nuFind() {
                                                        }

                                                        public void find(int i) {
                                                            if (i == 0) {
                                                                PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "启用该功能");
                                                                new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "开始群发", 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                    public void nuFind() {
                                                                    }

                                                                    public void find(int i) {
                                                                        PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "开始群发");
                                                                        GroupSendFriendsUtils.this.newGroupSend();
                                                                    }
                                                                });
                                                                return;
                                                            }
                                                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "开始群发", 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void nuFind() {
                                                                }

                                                                public void find(int i) {
                                                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "开始群发");
                                                                    GroupSendFriendsUtils.this.newGroupSend();
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
            StringBuilder sb = new StringBuilder();
            sb.append("一共发送了");
            sb.append(this.lastOperationNum > 0 ? this.lastOperationNum - 1 : 0);
            sb.append("个好友");
            updateMiddleText(myWindowManager, "分批群发好友结束", sb.toString());
            return;
        }
        updateMiddleText(this.mMyManager, "分批群发好友结束", this.middleStr);
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + (this.lastOperationNum + 1) + " 个好友开始群发，请不要操作微信");
        LogUtils.log("WS_BABY_END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    GroupSendFriendsUtils.this.executeMain();
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
