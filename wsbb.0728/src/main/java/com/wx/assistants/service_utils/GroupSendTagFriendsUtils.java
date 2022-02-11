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
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendTagFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendTagFriendsUtils instance;
    /* access modifiers changed from: private */
    public String currentLabel = "";
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
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
    public OperationParameterModel model;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = 5000;
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public String sayContent;
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int totalNum = 0;

    private GroupSendTagFriendsUtils() {
    }

    static /* synthetic */ int access$1108(GroupSendTagFriendsUtils groupSendTagFriendsUtils) {
        int i = groupSendTagFriendsUtils.operationFirstNum;
        groupSendTagFriendsUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408(GroupSendTagFriendsUtils groupSendTagFriendsUtils) {
        int i = groupSendTagFriendsUtils.pullRecordNum;
        groupSendTagFriendsUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1508(GroupSendTagFriendsUtils groupSendTagFriendsUtils) {
        int i = groupSendTagFriendsUtils.scrollNum;
        groupSendTagFriendsUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(GroupSendTagFriendsUtils groupSendTagFriendsUtils) {
        int i = groupSendTagFriendsUtils.startIndex;
        groupSendTagFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendTagFriendsUtils groupSendTagFriendsUtils) {
        int i = groupSendTagFriendsUtils.currentSelectNum;
        groupSendTagFriendsUtils.currentSelectNum = i + 1;
        return i;
    }

    public static GroupSendTagFriendsUtils getInstance() {
        instance = new GroupSendTagFriendsUtils();
        return instance;
    }

    public void confirmCompleted(int i) {
        SPUtils.put(MyApplication.getConText(), "batch_text_img_num_part", Integer.valueOf(i));
        sleep(200);
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        sleep(BannerConfig.DURATION);
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "正在群发标签[ " + this.currentLabel + " ]中的好友\n正在执行发送操作");
        typeSend();
    }

    public void continueExecute() {
        this.lastOperationNum = this.startPullIndex + this.pullRecordNum;
        if (this.model.getMessageSendType() != 2) {
            newGroupSend();
        } else if (this.sendOrder == 0) {
            new PerformClickUtils2().checkString(this.autoService, "再发一条", executeSpeed + executeSpeedSetting, 300, 400, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.MassSendHistoryUI_22");
                    PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "再发一条");
                    GroupSendTagFriendsUtils.this.sendText();
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
        try {
            showBottomView(this.mMyManager, "正在群发标签中的好友\n请不要操作微信");
            LogUtils.log("WS_BABY_END_SHOW");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendTagFriendsUtils.this.executeMain();
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
            new PerformClickUtils2().checkString(this.autoService, "我", executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "我");
                    new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "设置", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "设置");
                            try {
                                new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "通用", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (MyApplication.enforceable) {
                                            PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "通用");
                                            try {
                                                new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "辅助功能", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "辅助功能");
                                                        try {
                                                            new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "群发助手", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "群发助手");
                                                                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendTagFriendsUtils.this.autoService, "启用该功能", "开始群发", 150, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                        public void find(int i) {
                                                                            if (i == 0) {
                                                                                PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "启用该功能");
                                                                                new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "开始群发", 150, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                                    public void find(int i) {
                                                                                        PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "开始群发");
                                                                                        GroupSendTagFriendsUtils.this.newGroupSend();
                                                                                    }

                                                                                    public void nuFind() {
                                                                                        PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "开始群发");
                                                                                        GroupSendTagFriendsUtils.this.newGroupSend();
                                                                                    }
                                                                                });
                                                                                return;
                                                                            }
                                                                            PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "开始群发");
                                                                            GroupSendTagFriendsUtils.this.newGroupSend();
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

                                                    public void nuFind() {
                                                    }
                                                });
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    public void nuFind() {
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

    public void initAlbumPreviewUI() {
        try {
            if ((!"7.0.15".equals(wxVersionName) || wxVersionCode != 1660) && wxVersionCode <= 1660) {
                new PerformClickUtils2().checkNodeId(this.autoService, video_first_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI_2");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.video_first_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            GroupSendTagFriendsUtils.this.initAlbumPreviewUI();
                            return;
                        }
                        PerformClickUtils.performClick(findViewIdList.get(0));
                        GroupSendTagFriendsUtils.this.initCropImageNewUI();
                    }

                    public void nuFind() {
                    }
                });
            } else {
                new PerformClickUtils2().check(this.autoService, img_first_check_layout_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.AlbumPreviewUI.33");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                        if (findViewIdList != null && !findViewIdList.isEmpty() && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.AlbumPreviewUI.66");
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            GroupSendTagFriendsUtils.this.sleep(50);
                            PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "原图");
                            GroupSendTagFriendsUtils.this.sleep(100);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                GroupSendTagFriendsUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            findViewIdList2.get(0).performAction(16);
                            GroupSendTagFriendsUtils.this.initCropImageNewUI();
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
                        PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "完成");
                        GroupSendTagFriendsUtils.this.sleep(300);
                        GroupSendTagFriendsUtils.this.continueExecute();
                        return;
                    }
                    GroupSendTagFriendsUtils.this.continueExecute();
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
        this.operationFirstNum = 0;
        this.tagList = new LinkedHashSet<>();
        this.lastName = "";
        this.isFirstSelectImg = true;
        this.tagList = operationParameterModel.getTagListNames();
        this.sayContent = operationParameterModel.getSayContent();
        this.localImgUrl = operationParameterModel.getLocalImgUrl();
        this.sendOrder = operationParameterModel.getSendOrder();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.lastOperationNum = this.startPullIndex - 1;
        this.maxNum = 200;
        this.pullMaxNum = 5000;
        this.pullRecordNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.middleStr = "";
        this.totalNum = 0;
        this.scrollNum = 0;
        PrintStream printStream = System.out;
        printStream.println("WS_BBBB0_scrollNum = " + this.scrollNum + ", lastOperationNum = " + this.lastOperationNum);
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "群发标签好友", "一共群发" + this.totalNum + "个好友");
    }

    public void newGroupSend() {
        new PerformClickUtils2().checkNodeIds(this.autoService, new_group_send_big_id, new_group_send_id, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (MyApplication.enforceable) {
                    if (GroupSendTagFriendsUtils.this.tagList == null || GroupSendTagFriendsUtils.this.tagList.size() == 0) {
                        SPUtils.put(MyApplication.getConText(), "batch_text_img_num_part", 1);
                        BaseServiceUtils.removeEndView(GroupSendTagFriendsUtils.this.mMyManager);
                        return;
                    }
                    String unused = GroupSendTagFriendsUtils.this.currentLabel = (String) GroupSendTagFriendsUtils.this.tagList.iterator().next();
                    MyWindowManager myWindowManager = GroupSendTagFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "正在群发标签[ " + GroupSendTagFriendsUtils.this.currentLabel + " ]中的好友\n请不要操作微信");
                    SPUtils.put(MyApplication.getConText(), "batch_text_img_num_part", Integer.valueOf(GroupSendTagFriendsUtils.this.lastOperationNum == 0 ? 1 : GroupSendTagFriendsUtils.this.lastOperationNum));
                    switch (i) {
                        case 0:
                            LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed7");
                            PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.new_group_send_big_id);
                            GroupSendTagFriendsUtils.this.selectSendTag();
                            return;
                        case 1:
                            LogUtils.log("WS_BABY.WS_BABY_MassSendHistoryUI_img_completed8");
                            PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.new_group_send_id);
                            GroupSendTagFriendsUtils.this.selectSendTag();
                            return;
                        default:
                            return;
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void selectContacts() {
        try {
            LogUtils.log("WS_BABY.SelectLabelContactUI");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    int unused = GroupSendTagFriendsUtils.this.startIndex = 0;
                    String unused2 = GroupSendTagFriendsUtils.this.middleStr = "";
                    int unused3 = GroupSendTagFriendsUtils.this.currentSelectNum = 0;
                    GroupSendTagFriendsUtils.this.whileExecuteTask();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectSendTag() {
        LogUtils.log("WS_BABY.MassSendSelectContactUI_1");
        new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_search_id);
                new PerformClickUtils2().checkNodeId(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.tag_view_group_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.tag_view_group_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && (accessibilityNodeInfo = findViewIdList.get(0)) != null) {
                            for (int i2 = 0; i2 < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable; i2++) {
                                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
                                if (child != null && MyApplication.enforceable) {
                                    if ((child.getText() + "").equals(GroupSendTagFriendsUtils.this.currentLabel)) {
                                        PerformClickUtils.performClick(child);
                                        GroupSendTagFriendsUtils.this.selectContacts();
                                        return;
                                    }
                                }
                            }
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

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("") && MyApplication.enforceable) {
            new PerformClickUtils2().check(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_MassSendMsgUI_IMG");
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                        PerformClickUtils.inputText(GroupSendTagFriendsUtils.this.autoService, "");
                        GroupSendTagFriendsUtils.this.sleep(100);
                        PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    } else {
                        PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    }
                    new PerformClickUtils2().checkStringsFromPhotos(GroupSendTagFriendsUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendTagFriendsUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : 400, 200, 65, new PerformClickUtils2.OnCheckListener5() {
                        public void execute(int i) {
                            if (i == 15 && !PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                GroupSendTagFriendsUtils.this.autoService.performGlobalAction(1);
                            }
                        }

                        public void find(int i) {
                            boolean unused = GroupSendTagFriendsUtils.this.isFirstSelectImg = false;
                            switch (i) {
                                case 0:
                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                    GroupSendTagFriendsUtils.this.initAlbumPreviewUI();
                                    return;
                                case 1:
                                    new Thread(new Runnable() {
                                        public void run() {
                                            GroupSendTagFriendsUtils.this.autoService.performGlobalAction(1);
                                            GroupSendTagFriendsUtils.this.sleep(350);
                                            if (PerformClickUtils.findNodeIsExistByText(GroupSendTagFriendsUtils.this.autoService, "相册")) {
                                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                                GroupSendTagFriendsUtils.this.initAlbumPreviewUI();
                                                return;
                                            }
                                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                                PerformClickUtils.inputText(GroupSendTagFriendsUtils.this.autoService, "");
                                                GroupSendTagFriendsUtils.this.sleep(100);
                                            }
                                            PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                                            GroupSendTagFriendsUtils.this.sleep(400);
                                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                            GroupSendTagFriendsUtils.this.initAlbumPreviewUI();
                                        }
                                    }).start();
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
                    if (PerformClickUtils.findNodeIsExistByText(GroupSendTagFriendsUtils.this.autoService, "按住 说话") && MyApplication.enforceable) {
                        PerformClickUtils.findViewIdAndClick(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id);
                        GroupSendTagFriendsUtils.this.sleep(100);
                    }
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.send_add_id) && MyApplication.enforceable) {
                        PerformClickUtils.inputText(GroupSendTagFriendsUtils.this.autoService, "");
                        GroupSendTagFriendsUtils.this.sleep(100);
                    }
                    LogUtils.log("WS_BABY_MassSendMsgUI_TEXT1");
                    PerformClickUtils.inputText(GroupSendTagFriendsUtils.this.autoService, GroupSendTagFriendsUtils.this.sayContent);
                    new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "发送", 100, 50, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "发送");
                            GroupSendTagFriendsUtils.this.sleep(100);
                            if (GroupSendTagFriendsUtils.this.model.getMessageSendType() == 0 || GroupSendTagFriendsUtils.this.sendOrder == 0) {
                                LogUtils.log("WS_BABY.newGroupSend1");
                                GroupSendTagFriendsUtils.this.newGroupSend();
                                return;
                            }
                            new PerformClickUtils2().checkString(GroupSendTagFriendsUtils.this.autoService, "再发一条", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 300, 400, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.MassSendHistoryUI_22");
                                    PerformClickUtils.findTextAndClick(GroupSendTagFriendsUtils.this.autoService, "再发一条");
                                    GroupSendTagFriendsUtils.this.sendImg();
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
                    if (GroupSendTagFriendsUtils.this.nameNodes == null) {
                        GroupSendTagFriendsUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                    }
                    if (GroupSendTagFriendsUtils.this.nameNodes != null && GroupSendTagFriendsUtils.this.nameNodes.size() > 0 && MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = GroupSendTagFriendsUtils.this.nameNodes.get(GroupSendTagFriendsUtils.this.nameNodes.size() - 1);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            if (str == null || "".equals(str)) {
                                str = "";
                            }
                            Rect rect = new Rect();
                            accessibilityNodeInfo.getBoundsInScreen(rect);
                            String unused = GroupSendTagFriendsUtils.this.lastName = str + rect.toString();
                        }
                        if (GroupSendTagFriendsUtils.this.operationFirstNum == GroupSendTagFriendsUtils.this.maxNum && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_excSelectTask.8");
                            int unused2 = GroupSendTagFriendsUtils.this.lastOperationNum = GroupSendTagFriendsUtils.this.startPullIndex + GroupSendTagFriendsUtils.this.pullRecordNum;
                            int unused3 = GroupSendTagFriendsUtils.this.operationFirstNum = 0;
                            int unused4 = GroupSendTagFriendsUtils.this.scrollNum = 1;
                            if (GroupSendTagFriendsUtils.this.currentSelectNum == 0) {
                                LogUtils.log("WS_BABY_excSelectTask.88");
                                PerformClickUtils.performBack(GroupSendTagFriendsUtils.this.autoService);
                                GroupSendTagFriendsUtils.this.sleep(BannerConfig.DURATION);
                                PerformClickUtils.performBack(GroupSendTagFriendsUtils.this.autoService);
                                GroupSendTagFriendsUtils.this.sleep(BannerConfig.DURATION);
                                return;
                            }
                            int unused5 = GroupSendTagFriendsUtils.this.currentSelectNum = 0;
                            LogUtils.log("WS_BABY_excSelectTask.888");
                            GroupSendTagFriendsUtils.this.confirmCompleted(GroupSendTagFriendsUtils.this.lastOperationNum);
                        } else if (GroupSendTagFriendsUtils.this.pullRecordNum >= GroupSendTagFriendsUtils.this.pullMaxNum) {
                            LogUtils.log("WS_BABY_excSelectTask.9");
                            int unused6 = GroupSendTagFriendsUtils.this.lastOperationNum = GroupSendTagFriendsUtils.this.startPullIndex + GroupSendTagFriendsUtils.this.pullRecordNum;
                            int unused7 = GroupSendTagFriendsUtils.this.scrollNum = 1;
                            int unused8 = GroupSendTagFriendsUtils.this.operationFirstNum = 0;
                            int unused9 = GroupSendTagFriendsUtils.this.currentSelectNum = 0;
                            GroupSendTagFriendsUtils.this.confirmCompleted(GroupSendTagFriendsUtils.this.lastOperationNum);
                        } else {
                            if (GroupSendTagFriendsUtils.this.nameNodes == null) {
                                GroupSendTagFriendsUtils.this.nameNodes = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                            }
                            if (GroupSendTagFriendsUtils.this.startIndex < GroupSendTagFriendsUtils.this.nameNodes.size()) {
                                LogUtils.log("WS_BABY_excSelectTask.startIndex = " + GroupSendTagFriendsUtils.this.startIndex + ",namesize = " + GroupSendTagFriendsUtils.this.nameNodes.size());
                                LogUtils.log("WS_BABY_excSelectTask.slast = " + GroupSendTagFriendsUtils.this.lastOperationNum + "，，，pull = " + GroupSendTagFriendsUtils.this.pullRecordNum + "，，，start = " + GroupSendTagFriendsUtils.this.startPullIndex);
                                try {
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = GroupSendTagFriendsUtils.this.nameNodes.get(GroupSendTagFriendsUtils.this.startIndex);
                                    if (accessibilityNodeInfo2 != null) {
                                        String str2 = accessibilityNodeInfo2.getText() + "";
                                        if (str2 != null && !"".equals(str2)) {
                                            LogUtils.log("WS_BABY_excSelectTask.name = " + str2);
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                System.out.println("WS_BBBB_scrollNum = " + GroupSendTagFriendsUtils.this.scrollNum + ", lastOperationNum = " + GroupSendTagFriendsUtils.this.lastOperationNum);
                                if (GroupSendTagFriendsUtils.this.scrollNum < GroupSendTagFriendsUtils.this.lastOperationNum) {
                                    GroupSendTagFriendsUtils.access$708(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.access$1508(GroupSendTagFriendsUtils.this);
                                    BaseServiceUtils.updateBottomText(GroupSendTagFriendsUtils.this.mMyManager, "正在群发标签[ " + GroupSendTagFriendsUtils.this.currentLabel + " ]中的好友\n已跳过 " + GroupSendTagFriendsUtils.this.scrollNum + " 个");
                                    GroupSendTagFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_checkbox);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || GroupSendTagFriendsUtils.this.startIndex >= findViewIdList.size()) {
                                    LogUtils.log("WS_BABY_excSelectTask.7");
                                    GroupSendTagFriendsUtils.access$708(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.access$1408(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.access$1108(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                LogUtils.log("WS_BABY_excSelectTask.0");
                                AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList.get(GroupSendTagFriendsUtils.this.startIndex);
                                if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.isChecked()) {
                                    GroupSendTagFriendsUtils.access$708(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.access$1408(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.access$1108(GroupSendTagFriendsUtils.this);
                                    GroupSendTagFriendsUtils.this.whileExecuteTask();
                                    return;
                                }
                                LogUtils.log("WS_BABY_excSelectTask.2");
                                GroupSendTagFriendsUtils.access$708(GroupSendTagFriendsUtils.this);
                                GroupSendTagFriendsUtils.access$1408(GroupSendTagFriendsUtils.this);
                                GroupSendTagFriendsUtils.access$1108(GroupSendTagFriendsUtils.this);
                                GroupSendTagFriendsUtils.access$908(GroupSendTagFriendsUtils.this);
                                GroupSendTagFriendsUtils.this.sleep(5);
                                PerformClickUtils.performClick(accessibilityNodeInfo3);
                                BaseServiceUtils.updateBottomText(GroupSendTagFriendsUtils.this.mMyManager, "正在群发标签[ " + GroupSendTagFriendsUtils.this.currentLabel + " ]中的好友\n已选择 " + GroupSendTagFriendsUtils.this.currentSelectNum + " 个");
                                GroupSendTagFriendsUtils.this.whileExecuteTask();
                            } else if (GroupSendTagFriendsUtils.this.startIndex >= GroupSendTagFriendsUtils.this.nameNodes.size()) {
                                GroupSendTagFriendsUtils.this.nameNodes = null;
                                new PerformClickUtils2().checkNodeId(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_id, 10, 10, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_id);
                                        if (findViewIdList == null || findViewIdList.size() <= 0) {
                                            GroupSendTagFriendsUtils.this.whileExecuteTask();
                                            return;
                                        }
                                        findViewIdList.get(0).performAction(4096);
                                        int unused = GroupSendTagFriendsUtils.this.startIndex = 1;
                                        new PerformClickUtils2().checkNodeAllIds(GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_id, BaseServiceUtils.list_select_name_id, 100, 5, 200, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                System.out.println("WS_BABY_TIME.end2 = " + System.currentTimeMillis());
                                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendTagFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                                    GroupSendTagFriendsUtils.this.whileExecuteTask();
                                                    return;
                                                }
                                                String str = "";
                                                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(findViewIdList.size() - 1);
                                                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                                    String str2 = accessibilityNodeInfo.getText() + "";
                                                    if (str2 == null || "".equals(str2)) {
                                                        str2 = str;
                                                    }
                                                    Rect rect = new Rect();
                                                    accessibilityNodeInfo.getBoundsInScreen(rect);
                                                    str = str2 + rect.toString();
                                                }
                                                LogUtils.log("WS_BABY_excSelectTask.11.lastname = " + str);
                                                if (str.equals(GroupSendTagFriendsUtils.this.lastName)) {
                                                    GroupSendTagFriendsUtils.this.tagList.remove(GroupSendTagFriendsUtils.this.currentLabel);
                                                    int unused = GroupSendTagFriendsUtils.this.lastOperationNum = GroupSendTagFriendsUtils.this.startPullIndex + GroupSendTagFriendsUtils.this.pullRecordNum;
                                                    if (GroupSendTagFriendsUtils.this.currentSelectNum == 0) {
                                                        String unused2 = GroupSendTagFriendsUtils.this.middleStr = "您设置的起点 高于 标签好友的数量\n共群发 0 人";
                                                        BaseServiceUtils.removeEndView(GroupSendTagFriendsUtils.this.mMyManager);
                                                        return;
                                                    }
                                                    int unused3 = GroupSendTagFriendsUtils.this.totalNum = (GroupSendTagFriendsUtils.this.lastOperationNum + GroupSendTagFriendsUtils.this.totalNum) - 1;
                                                    int unused4 = GroupSendTagFriendsUtils.this.operationFirstNum = 0;
                                                    int unused5 = GroupSendTagFriendsUtils.this.startPullIndex = 0;
                                                    int unused6 = GroupSendTagFriendsUtils.this.lastOperationNum = 0;
                                                    int unused7 = GroupSendTagFriendsUtils.this.maxNum = 200;
                                                    int unused8 = GroupSendTagFriendsUtils.this.pullMaxNum = 5000;
                                                    int unused9 = GroupSendTagFriendsUtils.this.pullRecordNum = 0;
                                                    int unused10 = GroupSendTagFriendsUtils.this.currentSelectNum = 0;
                                                    int unused11 = GroupSendTagFriendsUtils.this.startIndex = 0;
                                                    String unused12 = GroupSendTagFriendsUtils.this.middleStr = "";
                                                    GroupSendTagFriendsUtils.this.confirmCompleted(1);
                                                    return;
                                                }
                                                GroupSendTagFriendsUtils.this.whileExecuteTask();
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
                    }
                }
            }).start();
        }
    }
}
