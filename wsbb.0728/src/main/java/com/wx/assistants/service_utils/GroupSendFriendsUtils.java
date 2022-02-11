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
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
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

    private GroupSendFriendsUtils() {
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

    public static GroupSendFriendsUtils getInstance() {
        instance = new GroupSendFriendsUtils();
        return instance;
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

    public void continueExecute() {
        this.lastOperationNum = this.startPullIndex + this.pullRecordNum;
        saveRecord(this.lastOperationNum);
        updateBottomText(this.mMyManager, "分批群发消息\n正在执行实际发送操作,请不要操作微信");
        if (this.model.getMessageSendType() != 2) {
            newGroupSend();
        } else if (this.sendOrder == 0) {
            new PerformClickUtils2().checkString(this.autoService, "再发一条", executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
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

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + (this.lastOperationNum + 1) + " 个好友开始群发，请不要操作微信");
        LogUtils.log("WS_BABY_END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    GroupSendFriendsUtils.this.executeMain();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkString(this.autoService, "我", 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "我");
                    new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "设置", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "设置");
                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "通用", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "通用");
                                    new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "辅助功能", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "辅助功能");
                                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "群发助手", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "群发助手");
                                                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendFriendsUtils.this.autoService, "启用该功能", "开始群发", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            if (i == 0) {
                                                                PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "启用该功能");
                                                                new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "开始群发", 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "开始群发");
                                                                        GroupSendFriendsUtils.this.newGroupSend();
                                                                    }

                                                                    public void nuFind() {
                                                                    }
                                                                });
                                                                return;
                                                            }
                                                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "开始群发", 50, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "开始群发");
                                                                    GroupSendFriendsUtils.this.newGroupSend();
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

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_67");
            e.printStackTrace();
        }
    }

    public void executeSelectContact() {
        LogUtils.log("WS_BABY.MassSendSelectContactUI_4");
        new PerformClickUtils2().check(this.autoService, list_select_checkbox, executeSpeed + (executeSpeedSetting / 2), 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_whileExecuteTask.8");
                GroupSendFriendsUtils.this.whileExecuteTask();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.MassSendSelectContactUI_5_nuFind");
            }
        });
    }

    public void initAlbumPreviewUI() {
        try {
            if ((!"7.0.15".equals(wxVersionName) || wxVersionCode != 1660) && wxVersionCode <= 1660) {
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
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

                    public void nuFind() {
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
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "完成", "再发一条", executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findTextAndClick(GroupSendFriendsUtils.this.autoService, "完成");
                        GroupSendFriendsUtils.this.sleep(100);
                        GroupSendFriendsUtils.this.continueExecute();
                        return;
                    }
                    GroupSendFriendsUtils.this.continueExecute();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_62");
            e.printStackTrace();
        }
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

    public void middleViewDismiss() {
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

    public void newGroupSend() {
        LogUtils.log("WS_BABY.MassSendHistoryUI");
        try {
            new PerformClickUtils2().checkNodeIds(this.autoService, new_group_send_big_id, new_group_send_id, executeSpeed, 100, 600, new PerformClickUtils2.OnCheckListener() {
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

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_66");
            e.printStackTrace();
        }
    }

    public void saveRecord(int i) {
        if (this.pullType == 0) {
            SPUtils.put(MyApplication.getConText(), "batch_text_img_num_all", Integer.valueOf(i));
        } else if (this.pullType == 2) {
            SPUtils.put(MyApplication.getConText(), "batch_text_img_num_shield", Integer.valueOf(i));
        }
    }

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("") && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_MassSendMsgUI_IMG");
            new PerformClickUtils2().check(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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
                        public void execute(int i) {
                            if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                GroupSendFriendsUtils.this.autoService.performGlobalAction(1);
                            }
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

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                }
            });
        }
    }

    public void sendText() {
        if (this.sayContent != null && !this.sayContent.equals("") && MyApplication.enforceable) {
            new PerformClickUtils2().checkSendAddNodeId(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
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
                            new PerformClickUtils2().checkString(GroupSendFriendsUtils.this.autoService, "再发一条", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
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

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
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
        LogUtils.log("WS_BABY_Catch_64");
        e.printStackTrace();
    }

    public void whileExecuteTask() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        LogUtils.log("WS_BABY_excSelectTask.入口");
                        if (GroupSendFriendsUtils.this.nameNodes == null) {
                            GroupSendFriendsUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                            System.out.println("WS_BABY_excSelectTask.入口.1");
                        }
                        if (GroupSendFriendsUtils.this.nameNodes == null || GroupSendFriendsUtils.this.nameNodes.size() <= 0 || !MyApplication.enforceable) {
                            System.out.println("WS_BABY_excSelectTask.888888888888888888.");
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        GroupSendFriendsUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                        System.out.println("WS_BABY_whileExecuteTask.7");
                                        GroupSendFriendsUtils.this.whileExecuteTask();
                                    } catch (Exception e) {
                                    }
                                }
                            }).start();
                            return;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfo = GroupSendFriendsUtils.this.nameNodes.get(GroupSendFriendsUtils.this.nameNodes.size() - 1);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            if (str == null || "".equals(str)) {
                                str = "";
                            }
                            Rect rect = new Rect();
                            accessibilityNodeInfo.getBoundsInScreen(rect);
                            String unused = GroupSendFriendsUtils.this.lastName = str + rect.toString();
                            LogUtils.log("WS_BABY_excSelectTask.111111111111.lastname = " + GroupSendFriendsUtils.this.lastName);
                        }
                        if (GroupSendFriendsUtils.this.operationFirstNum == GroupSendFriendsUtils.this.maxNum && MyApplication.enforceable) {
                            System.out.println("WS_BABY_excSelectTask.8");
                            int unused2 = GroupSendFriendsUtils.this.lastOperationNum = GroupSendFriendsUtils.this.startPullIndex + GroupSendFriendsUtils.this.pullRecordNum;
                            GroupSendFriendsUtils.this.saveRecord(GroupSendFriendsUtils.this.lastOperationNum);
                            int unused3 = GroupSendFriendsUtils.this.operationFirstNum = 0;
                            int unused4 = GroupSendFriendsUtils.this.scrollNum = 1;
                            if (GroupSendFriendsUtils.this.currentSelectNum == 0) {
                                System.out.println("WS_BABY_excSelectTask.88");
                                PerformClickUtils.performBack(GroupSendFriendsUtils.this.autoService);
                                GroupSendFriendsUtils.this.sleep(BannerConfig.DURATION);
                                return;
                            }
                            int unused5 = GroupSendFriendsUtils.this.currentSelectNum = 0;
                            System.out.println("WS_BABY_excSelectTask.888");
                            GroupSendFriendsUtils.this.confirmCompleted(GroupSendFriendsUtils.this.lastOperationNum);
                        } else if (GroupSendFriendsUtils.this.pullRecordNum >= GroupSendFriendsUtils.this.pullMaxNum) {
                            System.out.println("WS_BABY_excSelectTask.9");
                            boolean unused6 = GroupSendFriendsUtils.this.isFilled = true;
                            int unused7 = GroupSendFriendsUtils.this.lastOperationNum = GroupSendFriendsUtils.this.startPullIndex + GroupSendFriendsUtils.this.pullRecordNum;
                            int unused8 = GroupSendFriendsUtils.this.scrollNum = 1;
                            int unused9 = GroupSendFriendsUtils.this.operationFirstNum = 0;
                            int unused10 = GroupSendFriendsUtils.this.currentSelectNum = 0;
                            GroupSendFriendsUtils.this.confirmCompleted(GroupSendFriendsUtils.this.lastOperationNum);
                        } else {
                            if (GroupSendFriendsUtils.this.nameNodes == null) {
                                GroupSendFriendsUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                            }
                            if (GroupSendFriendsUtils.this.startIndex < GroupSendFriendsUtils.this.nameNodes.size()) {
                                System.out.println("WS_BABY_excSelectTask.startIndex = " + GroupSendFriendsUtils.this.startIndex + ",namesize = " + GroupSendFriendsUtils.this.nameNodes.size());
                                System.out.println("WS_BABY_excSelectTask.last = " + GroupSendFriendsUtils.this.lastOperationNum + "，pulled = " + GroupSendFriendsUtils.this.pullRecordNum + "，start = " + GroupSendFriendsUtils.this.startPullIndex);
                                String str2 = "";
                                try {
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = GroupSendFriendsUtils.this.nameNodes.get(GroupSendFriendsUtils.this.startIndex);
                                    if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                        String str3 = accessibilityNodeInfo2.getText() + "";
                                        if (str3 != null) {
                                            try {
                                                if (!"".equals(str3)) {
                                                    System.out.println("WS_BABY_excSelectTask.name = " + str3);
                                                }
                                            } catch (Exception e) {
                                                str2 = str3;
                                            }
                                        }
                                        str2 = str3;
                                    }
                                } catch (Exception e2) {
                                }
                                if (GroupSendFriendsUtils.this.scrollNum < GroupSendFriendsUtils.this.lastOperationNum) {
                                    System.out.println("WS_BABY_excSelectTask.跳过 ");
                                    GroupSendFriendsUtils.access$608(GroupSendFriendsUtils.this);
                                    GroupSendFriendsUtils.access$1108(GroupSendFriendsUtils.this);
                                    BaseServiceUtils.updateBottomText(GroupSendFriendsUtils.this.mMyManager, "正在分批群发消息\n已跳过 " + GroupSendFriendsUtils.this.scrollNum + " 个");
                                    System.out.println("WS_BABY_whileExecuteTask.1");
                                    GroupSendFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_checkbox);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || GroupSendFriendsUtils.this.startIndex >= findViewIdList.size()) {
                                    GroupSendFriendsUtils.access$608(GroupSendFriendsUtils.this);
                                    GroupSendFriendsUtils.access$308(GroupSendFriendsUtils.this);
                                    GroupSendFriendsUtils.access$908(GroupSendFriendsUtils.this);
                                    System.out.println("WS_BABY_whileExecuteTask.4");
                                    GroupSendFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                System.out.println("WS_BABY_excSelectTask.0");
                                AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList.get(GroupSendFriendsUtils.this.startIndex);
                                if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.isChecked() || GroupSendFriendsUtils.this.jumpPersons.contains(str2)) {
                                    GroupSendFriendsUtils.access$608(GroupSendFriendsUtils.this);
                                    GroupSendFriendsUtils.access$308(GroupSendFriendsUtils.this);
                                    GroupSendFriendsUtils.access$908(GroupSendFriendsUtils.this);
                                    if (GroupSendFriendsUtils.this.jumpPersons.contains(str2)) {
                                        GroupSendFriendsUtils.access$1608(GroupSendFriendsUtils.this);
                                    }
                                    System.out.println("WS_BABY_whileExecuteTask.2");
                                    GroupSendFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                System.out.println("WS_BABY_excSelectTask.2");
                                GroupSendFriendsUtils.access$608(GroupSendFriendsUtils.this);
                                GroupSendFriendsUtils.access$308(GroupSendFriendsUtils.this);
                                GroupSendFriendsUtils.access$908(GroupSendFriendsUtils.this);
                                GroupSendFriendsUtils.access$1208(GroupSendFriendsUtils.this);
                                GroupSendFriendsUtils.this.sleep(5);
                                PerformClickUtils.performClick(accessibilityNodeInfo3);
                                if (GroupSendFriendsUtils.this.pullType == 0) {
                                    BaseServiceUtils.updateBottomText(GroupSendFriendsUtils.this.mMyManager, "正在分批群发消息\n已选择 " + GroupSendFriendsUtils.this.currentSelectNum + " 个");
                                } else {
                                    BaseServiceUtils.updateBottomText(GroupSendFriendsUtils.this.mMyManager, "正在分批群发消息\n已选择 " + GroupSendFriendsUtils.this.currentSelectNum + " 个 ，已屏蔽 " + GroupSendFriendsUtils.this.shieldNum + " 个");
                                }
                                System.out.println("WS_BABY_whileExecuteTask.3");
                                GroupSendFriendsUtils.this.whileExecuteTask();
                            } else if (GroupSendFriendsUtils.this.startIndex >= GroupSendFriendsUtils.this.nameNodes.size()) {
                                System.out.println("WS_BABY_TIME. 开始滚动 = " + GroupSendFriendsUtils.this.startIndex + ListUtils.DEFAULT_JOIN_SEPARATOR + GroupSendFriendsUtils.this.nameNodes.size());
                                GroupSendFriendsUtils.this.nameNodes = null;
                                PerformClickUtils.scroll(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_id);
                                int unused11 = GroupSendFriendsUtils.this.startIndex = 1;
                                new PerformClickUtils2().check(GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id, (GroupSendFriendsUtils.this.currentSelectNum != 0 || GroupSendFriendsUtils.this.lastOperationNum - GroupSendFriendsUtils.this.scrollNum <= 30) ? 350 : GroupSendFriendsUtils.this.scrollSpeed, 5, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        AccessibilityNodeInfo accessibilityNodeInfo;
                                        System.out.println("WS_BABY_TIME. 开始滚动6 = " + System.currentTimeMillis());
                                        GroupSendFriendsUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                        if (GroupSendFriendsUtils.this.nameNodes == null || GroupSendFriendsUtils.this.nameNodes.size() <= 0) {
                                            GroupSendFriendsUtils.this.whileExecuteTask();
                                            return;
                                        }
                                        String str = "";
                                        AccessibilityNodeInfo accessibilityNodeInfo2 = GroupSendFriendsUtils.this.nameNodes.get(GroupSendFriendsUtils.this.nameNodes.size() - 1);
                                        if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                                            String str2 = accessibilityNodeInfo2.getText() + "";
                                            if (str2 == null || "".equals(str2)) {
                                                str2 = str;
                                            }
                                            Rect rect = new Rect();
                                            accessibilityNodeInfo2.getBoundsInScreen(rect);
                                            str = str2 + rect.toString();
                                        }
                                        if (str.equals(GroupSendFriendsUtils.this.lastName)) {
                                            GroupSendFriendsUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                            if (!(findViewIdList == null || findViewIdList.size() <= 0 || (accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1)) == null || accessibilityNodeInfo.getText() == null)) {
                                                String str3 = accessibilityNodeInfo.getText() + "";
                                                if (str3 == null || "".equals(str3)) {
                                                    str3 = "";
                                                }
                                                Rect rect2 = new Rect();
                                                accessibilityNodeInfo.getBoundsInScreen(rect2);
                                                str = str3 + rect2.toString();
                                            }
                                            if (str.equals(GroupSendFriendsUtils.this.lastName)) {
                                                System.out.println("WS_BABY_TIME. 停止 = ");
                                                boolean unused = GroupSendFriendsUtils.this.isFilled = true;
                                                int unused2 = GroupSendFriendsUtils.this.lastOperationNum = GroupSendFriendsUtils.this.startPullIndex + GroupSendFriendsUtils.this.pullRecordNum;
                                                if (GroupSendFriendsUtils.this.currentSelectNum == 0) {
                                                    String unused3 = GroupSendFriendsUtils.this.middleStr = "您设置的起点 高于 好友的数量\n共群发 0 人";
                                                    BaseServiceUtils.removeEndView(GroupSendFriendsUtils.this.mMyManager);
                                                    return;
                                                }
                                                GroupSendFriendsUtils.this.confirmCompleted(1);
                                                return;
                                            }
                                            System.out.println("WS_BABY_whileExecuteTask.5");
                                            GroupSendFriendsUtils.this.whileExecuteTask();
                                            return;
                                        }
                                        System.out.println("WS_BABY_whileExecuteTask.6");
                                        GroupSendFriendsUtils.this.whileExecuteTask();
                                    }

                                    public void nuFind() {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_TIME. 开始滚动7 = " + System.currentTimeMillis());
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
}
