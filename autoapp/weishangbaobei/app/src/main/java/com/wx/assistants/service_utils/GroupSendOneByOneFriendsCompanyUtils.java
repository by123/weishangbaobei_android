package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
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
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendOneByOneFriendsCompanyUtils extends BaseServiceCompanyUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendOneByOneFriendsCompanyUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    boolean isCanWhile = true;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
    /* access modifiers changed from: private */
    public int isOriginPic = 1;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    private boolean isWhile = true;
    List<AccessibilityNodeInfo> itemList = null;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpTags = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String localImgUrl;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public int picCount = 1;
    /* access modifiers changed from: private */
    public int saveCount = 1;
    /* access modifiers changed from: private */
    public String sayContent;
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    private int sendPicVideoCode = 0;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;
    /* access modifiers changed from: private */
    public int tagFriendType = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1308(GroupSendOneByOneFriendsCompanyUtils groupSendOneByOneFriendsCompanyUtils) {
        int i = groupSendOneByOneFriendsCompanyUtils.startIndex;
        groupSendOneByOneFriendsCompanyUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408(GroupSendOneByOneFriendsCompanyUtils groupSendOneByOneFriendsCompanyUtils) {
        int i = groupSendOneByOneFriendsCompanyUtils.scrollCount;
        groupSendOneByOneFriendsCompanyUtils.scrollCount = i + 1;
        return i;
    }

    private GroupSendOneByOneFriendsCompanyUtils() {
    }

    public static GroupSendOneByOneFriendsCompanyUtils getInstance() {
        instance = new GroupSendOneByOneFriendsCompanyUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.excFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.startIndex = 0;
        this.scrollCount = 0;
        this.saveCount = 1;
        this.isWhile = true;
        this.isFirstSelectImg = true;
        this.operationParameterModel = operationParameterModel2;
        this.tagFriendType = operationParameterModel2.getTagFriendType();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.jumpTags = operationParameterModel2.getTagListNames();
        this.startInt = operationParameterModel2.getStartIndex();
        this.sayContent = operationParameterModel2.getSayContent();
        this.localImgUrl = operationParameterModel2.getLocalImgUrl();
        this.sendOrder = operationParameterModel2.getSendOrder();
        this.isOriginPic = operationParameterModel2.getIsOriginPic();
        this.picCount = operationParameterModel2.getMaterialPicCount();
        this.nowName = "";
        this.sendPicVideoCode = operationParameterModel2.getSendCardType();
        this.isScrollBottom = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initAlbumPreviewUI() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY_AlbumPreviewUI_1");
            if (this.sendPicVideoCode == 0) {
                new PerformClickUtils2().checkNodeId(this.autoService, company_img_first_check_layout_id, executeSpeedSetting + 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    LogUtils.log("WS_BABY_AlbumPreviewUI_2");
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_img_first_check_layout_id);
                                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                        GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                        return;
                                    }
                                    int access$000 = GroupSendOneByOneFriendsCompanyUtils.this.picCount - 1;
                                    while (true) {
                                        if (access$000 <= -1) {
                                            break;
                                        } else if (!MyApplication.enforceable) {
                                            break;
                                        } else {
                                            GroupSendOneByOneFriendsCompanyUtils.this.sleep(5);
                                            PerformClickUtils.performClick(findViewIdList.get(access$000));
                                            access$000--;
                                        }
                                    }
                                    GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                                    if (GroupSendOneByOneFriendsCompanyUtils.this.isOriginPic == 0) {
                                        PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "原图");
                                        GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                                    }
                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_complete_id);
                                    if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                        GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                        return;
                                    }
                                    PerformClickUtils.performClick(findViewIdList2.get(0));
                                    LogUtils.log("WS_BABY_AlbumPreviewUI_3");
                                    new PerformClickUtils2().checkNilStringTwo(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "请稍候", "正在发送中", 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            GroupSendOneByOneFriendsCompanyUtils.this.albumSelect();
                                        }
                                    });
                                } catch (Exception unused) {
                                }
                            }
                        }).start();
                    }
                });
            } else {
                new PerformClickUtils2().checkNodeId(this.autoService, company_select_video_id, executeSpeedSetting + 600, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    LogUtils.log("WS_BABY_AlbumPreviewUI_2");
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_select_video_id);
                                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                        GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                        return;
                                    }
                                    PerformClickUtils.performClick(findViewIdList.get(0));
                                    new PerformClickUtils2().check(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_complete_id, 300, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_complete_id);
                                            if (findViewIdList == null || findViewIdList.size() <= 0 || !findViewIdList.get(0).isEnabled()) {
                                                GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                                return;
                                            }
                                            PerformClickUtils.performClick(findViewIdList.get(0));
                                            LogUtils.log("WS_BABY_AlbumPreviewUI_3");
                                            new PerformClickUtils2().checkNilStringTwo(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "请稍候", "正在发送中", 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    GroupSendOneByOneFriendsCompanyUtils.this.albumSelect();
                                                }
                                            });
                                        }
                                    });
                                } catch (Exception unused) {
                                }
                            }
                        }).start();
                    }
                });
            }
        }
    }

    public void albumSelect() {
        if (this.startInt == 1) {
            this.saveCount = this.excFriendsNum;
        } else {
            this.saveCount = this.excFriendsNum + this.startInt;
        }
        if (this.tagFriendType == 0) {
            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_all", Integer.valueOf(this.saveCount));
        } else if (this.tagFriendType == 1) {
            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_part", Integer.valueOf(this.saveCount));
        } else if (this.tagFriendType == 2) {
            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_shield", Integer.valueOf(this.saveCount));
        }
        if (this.operationParameterModel.getMessageSendType() != 2) {
            LogUtils.log("WS_BABY_AlbumPreviewUI_5");
            PerformClickUtils.executedBackHomeCompany(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                }
            });
        } else if (this.sendOrder == 0) {
            sendText();
        } else {
            LogUtils.log("WS_BABY_AlbumPreviewUI_5");
            PerformClickUtils.executedBackHomeCompany(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                }
            });
        }
    }

    public void initContactInfoUI() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startInt + " 个客户开始\n正在发送，请不要操作微信！");
        new PerformClickUtils2().checkString(this.autoService, "发消息", executeSpeedSetting + 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                GroupSendOneByOneFriendsCompanyUtils.this.getRemarkFriendInfo(GroupSendOneByOneFriendsCompanyUtils.this.operationParameterModel.getIntimateMode(), GroupSendOneByOneFriendsCompanyUtils.this.nowName);
                PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_voice_left_id, 20, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        GroupSendOneByOneFriendsCompanyUtils.this.jumpFirstChattingUI(0);
                    }

                    public void nuFind() {
                        GroupSendOneByOneFriendsCompanyUtils.this.getRemarkFriendInfo(GroupSendOneByOneFriendsCompanyUtils.this.operationParameterModel.getIntimateMode(), GroupSendOneByOneFriendsCompanyUtils.this.nowName);
                        PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_voice_left_id, 20, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                GroupSendOneByOneFriendsCompanyUtils.this.jumpFirstChattingUI(0);
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                                PerformClickUtils.executedBackHomeCompany(GroupSendOneByOneFriendsCompanyUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                                    }
                                });
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.executedBackHomeCompany(GroupSendOneByOneFriendsCompanyUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                    }
                });
            }
        });
    }

    public void jumpFirstChattingUI(int i) {
        LogUtils.log("WS_BABY_ChattingUI_3");
        new PerformClickUtils2().checkNodeId(this.autoService, company_voice_left_id, i + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "按住 说话") && MyApplication.enforceable) {
                    PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_voice_left_id);
                    GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                }
                new PerformClickUtils2().check(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_contact_input_edit_id, 20, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ChattingUI_2");
                        if (GroupSendOneByOneFriendsCompanyUtils.this.operationParameterModel.getMessageSendType() == 0) {
                            if (GroupSendOneByOneFriendsCompanyUtils.this.sayContent != null && !"".equals(GroupSendOneByOneFriendsCompanyUtils.this.sayContent)) {
                                new Thread(new Runnable() {
                                    public void run() {
                                        try {
                                            LogUtils.log("WS_BABY_ChattingUI_3");
                                            AutoService autoService = GroupSendOneByOneFriendsCompanyUtils.this.autoService;
                                            String str = BaseServiceCompanyUtils.company_contact_input_edit_id;
                                            PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceCompanyUtils.remarkName + "" + GroupSendOneByOneFriendsCompanyUtils.this.sayContent);
                                            new PerformClickUtils2().checkString(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发送", 200, 100, 50, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发送");
                                                    MyWindowManager myWindowManager = GroupSendOneByOneFriendsCompanyUtils.this.mMyManager;
                                                    BaseServiceCompanyUtils.updateBottomText(myWindowManager, "从第 " + GroupSendOneByOneFriendsCompanyUtils.this.startInt + " 个客户开始\n正在发送，请不要操作微信");
                                                    if (GroupSendOneByOneFriendsCompanyUtils.this.startInt == 1) {
                                                        int unused = GroupSendOneByOneFriendsCompanyUtils.this.saveCount = GroupSendOneByOneFriendsCompanyUtils.this.excFriendsNum;
                                                    } else {
                                                        int unused2 = GroupSendOneByOneFriendsCompanyUtils.this.saveCount = GroupSendOneByOneFriendsCompanyUtils.this.excFriendsNum + GroupSendOneByOneFriendsCompanyUtils.this.startInt;
                                                    }
                                                    if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 0) {
                                                        SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_all", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                                                    } else if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 1) {
                                                        SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_part", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                                                    } else if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 2) {
                                                        SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_shield", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                                                    }
                                                    PerformClickUtils.executedBackHomeCompany(GroupSendOneByOneFriendsCompanyUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                                                        }
                                                    });
                                                }
                                            });
                                        } catch (Exception unused) {
                                        }
                                    }
                                }).start();
                            }
                        } else if (GroupSendOneByOneFriendsCompanyUtils.this.operationParameterModel.getMessageSendType() == 1) {
                            LogUtils.log("WS_BABY_ChattingUI_4");
                            if (GroupSendOneByOneFriendsCompanyUtils.this.localImgUrl != null && !GroupSendOneByOneFriendsCompanyUtils.this.localImgUrl.equals("")) {
                                if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id)) {
                                    LogUtils.log("WS_BABY_ChattingUI_44");
                                    PerformClickUtils.inputText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "");
                                    GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                                }
                                PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id);
                                LogUtils.log("WS_BABY_ChattingUI_5");
                                new PerformClickUtils2().checkStringsFromPhotos(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", "你可能要发送的照片", GroupSendOneByOneFriendsCompanyUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : BaseServiceCompanyUtils.executeSpeed, 100, 65, new PerformClickUtils2.OnCheckListener5() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        boolean unused = GroupSendOneByOneFriendsCompanyUtils.this.isFirstSelectImg = false;
                                        switch (i) {
                                            case 0:
                                                LogUtils.log("WS_BABY_ChattingUI_6");
                                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                                                GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                                return;
                                            case 1:
                                                LogUtils.log("WS_BABY_ChattingUI_7");
                                                GroupSendOneByOneFriendsCompanyUtils.this.autoService.performGlobalAction(1);
                                                GroupSendOneByOneFriendsCompanyUtils.this.sleep(350);
                                                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片")) {
                                                    LogUtils.log("WS_BABY_ChattingUI_8");
                                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                                                    GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                                    return;
                                                }
                                                LogUtils.log("WS_BABY_ChattingUI_9");
                                                if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id)) {
                                                    PerformClickUtils.inputText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "");
                                                    GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                                                }
                                                PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id);
                                                GroupSendOneByOneFriendsCompanyUtils.this.sleep(350);
                                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                                                GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                                return;
                                            default:
                                                return;
                                        }
                                    }

                                    public void execute(int i) {
                                        if (i == 30) {
                                            LogUtils.log("WS_BABY.ChattingUI_7");
                                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id)) {
                                                LogUtils.log("WS_BABY.ChattingUI_8");
                                                GroupSendOneByOneFriendsCompanyUtils.this.autoService.performGlobalAction(1);
                                                return;
                                            }
                                            LogUtils.log("WS_BABY.ChattingUI_9");
                                            PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id);
                                        }
                                    }
                                });
                            }
                        } else if (GroupSendOneByOneFriendsCompanyUtils.this.operationParameterModel.getMessageSendType() == 2) {
                            LogUtils.log("WS_BABY_ChattingUI_5");
                            if (GroupSendOneByOneFriendsCompanyUtils.this.sayContent != null && !GroupSendOneByOneFriendsCompanyUtils.this.sayContent.equals("")) {
                                if (GroupSendOneByOneFriendsCompanyUtils.this.sendOrder == 0) {
                                    GroupSendOneByOneFriendsCompanyUtils.this.sendImg();
                                } else {
                                    GroupSendOneByOneFriendsCompanyUtils.this.sendText();
                                }
                            }
                        }
                    }

                    public void nuFind() {
                        if (GroupSendOneByOneFriendsCompanyUtils.this.startInt == 1) {
                            int unused = GroupSendOneByOneFriendsCompanyUtils.this.saveCount = GroupSendOneByOneFriendsCompanyUtils.this.excFriendsNum;
                        } else {
                            int unused2 = GroupSendOneByOneFriendsCompanyUtils.this.saveCount = GroupSendOneByOneFriendsCompanyUtils.this.excFriendsNum + GroupSendOneByOneFriendsCompanyUtils.this.startInt;
                        }
                        if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 0) {
                            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_all", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                        } else if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 1) {
                            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_part", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                        } else if (GroupSendOneByOneFriendsCompanyUtils.this.tagFriendType == 2) {
                            SPUtils.put(MyApplication.getConText(), "company_one_bye_one_text_img_num_shield", Integer.valueOf(GroupSendOneByOneFriendsCompanyUtils.this.saveCount));
                        }
                        PerformClickUtils.executedBackHomeCompany(GroupSendOneByOneFriendsCompanyUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                            }
                        });
                    }
                });
            }
        });
    }

    public void sendImg() {
        if (this.localImgUrl != null && !this.localImgUrl.equals("")) {
            LogUtils.log("WS_BABY_ChattingUI_8");
            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, company_send_add_id)) {
                PerformClickUtils.inputText(this.autoService, "");
                sleep(100);
            }
            PerformClickUtils.findViewIdAndClick(this.autoService, company_send_add_id);
            new PerformClickUtils2().checkStringsFromPhotos(this.autoService, "图片", "你可能要发送的照片", this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed, 100, 65, new PerformClickUtils2.OnCheckListener5() {
                public void nuFind() {
                }

                public void find(int i) {
                    boolean unused = GroupSendOneByOneFriendsCompanyUtils.this.isFirstSelectImg = false;
                    switch (i) {
                        case 0:
                            LogUtils.log("WS_BABY_ChattingUI_9");
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                            GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                            return;
                        case 1:
                            LogUtils.log("WS_BABY_ChattingUI_10");
                            GroupSendOneByOneFriendsCompanyUtils.this.autoService.performGlobalAction(1);
                            GroupSendOneByOneFriendsCompanyUtils.this.sleep(350);
                            if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片")) {
                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                                GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id)) {
                                PerformClickUtils.inputText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "");
                                GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                            }
                            PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id);
                            GroupSendOneByOneFriendsCompanyUtils.this.sleep(350);
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, "图片", BaseServiceCompanyUtils.company_album_id);
                            GroupSendOneByOneFriendsCompanyUtils.this.initAlbumPreviewUI();
                            return;
                        default:
                            return;
                    }
                }

                public void execute(int i) {
                    if (i == 30) {
                        LogUtils.log("WS_BABY.ChattingUI_7");
                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id)) {
                            LogUtils.log("WS_BABY.ChattingUI_8");
                            GroupSendOneByOneFriendsCompanyUtils.this.autoService.performGlobalAction(1);
                            return;
                        }
                        LogUtils.log("WS_BABY.ChattingUI_9");
                        PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_send_add_id);
                    }
                }
            });
        }
    }

    public void sendText() {
        new PerformClickUtils2().check(this.autoService, company_voice_left_id, this.sendOrder == 0 ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed, 100, 1200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "按住 说话")) {
                    LogUtils.log("WS_BABY_ChattingUI_6");
                    PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_voice_left_id);
                    GroupSendOneByOneFriendsCompanyUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_contact_input_edit_id, BaseServiceCompanyUtils.executeSpeed + BaseServiceCompanyUtils.executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY_ChattingUI_7");
                        AutoService autoService = GroupSendOneByOneFriendsCompanyUtils.this.autoService;
                        String str = BaseServiceCompanyUtils.company_contact_input_edit_id;
                        PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceCompanyUtils.remarkName + "" + GroupSendOneByOneFriendsCompanyUtils.this.sayContent);
                        new PerformClickUtils2().checkString(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发送", 100, 100, 50, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "发送");
                                if (GroupSendOneByOneFriendsCompanyUtils.this.sendOrder == 0) {
                                    PerformClickUtils.executedBackHomeCompany(GroupSendOneByOneFriendsCompanyUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                                        }
                                    });
                                } else {
                                    GroupSendOneByOneFriendsCompanyUtils.this.sendImg();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public void inputText(String str) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                boolean z = true;
                AccessibilityNodeInfo findFocus = rootInActiveWindow.findFocus(1);
                if (findFocus != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (findFocus == null) {
                            z = false;
                        }
                        if (z && "android.widget.EditText".equals(findFocus.getClassName())) {
                            Bundle bundle = new Bundle();
                            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str + findFocus.getText() + "");
                            findFocus.performAction(2097152, bundle);
                            return;
                        }
                        return;
                    }
                    Object text = findFocus.getText();
                    if (text == null) {
                        text = "";
                    }
                    ((ClipboardManager) this.autoService.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str + text));
                    if (Build.VERSION.SDK_INT >= 18) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                        bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", (str + text).length());
                        findFocus.performAction(1);
                        findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                        findFocus.performAction(32768);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().checkString3(this.autoService, "我的客户", 10, 50, 100, new PerformClickUtils2.OnCheckListener5() {
                public void find(int i) {
                    PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "我的客户");
                    new PerformClickUtils2().checkNodeAllIds(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_customer_list_id, BaseServiceCompanyUtils.company_group_member_name_id, BaseServiceCompanyUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_LauncherUI_从头开始");
                            GroupSendOneByOneFriendsCompanyUtils.this.checkZombieFan(GroupSendOneByOneFriendsCompanyUtils.this.jumpTags);
                        }

                        public void nuFind() {
                            BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
                        }
                    });
                }

                public void nuFind() {
                    BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
                }

                public void execute(int i) {
                    PerformClickUtils.scrollTop(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_contact_list_id);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkZombieFan(LinkedHashSet<String> linkedHashSet) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
        boolean z;
        AccessibilityNodeInfo accessibilityNodeInfo;
        boolean z2;
        AccessibilityNodeInfo accessibilityNodeInfo2;
        System.out.println("WS_BABY_jumps_" + linkedHashSet);
        this.isCanWhile = true;
        while (true) {
            if (!this.isWhile || !this.isCanWhile || !MyApplication.enforceable) {
                break;
            }
            LogUtils.log("WS_BABY_LauncherUI.start");
            AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(company_group_member_name_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_LauncherUI.startIndex" + this.startIndex + "@" + findAccessibilityNodeInfosByViewId.size());
                if (this.startIndex < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                    final AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId.get(this.startIndex);
                    if (accessibilityNodeInfo3 == null) {
                        continue;
                    } else {
                        if (accessibilityNodeInfo3.getChild(0).getText() != null) {
                            this.nowName = accessibilityNodeInfo3.getChild(0).getText().toString();
                            LogUtils.log("WS_BABY_LauncherUI.name=" + this.nowName);
                        }
                        if (this.beforeLists != null && this.beforeLists.size() > 30) {
                            for (int i = 0; i < 5; i++) {
                                this.beforeLists.remove(0);
                            }
                        }
                        if (this.isScrollBottom && this.beforeLists.contains(this.nowName)) {
                            this.startIndex++;
                            LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + this.nowName);
                        } else if ("微信团队".equals(this.nowName) || "文件传输助手".equals(this.nowName) || "标签".equals(this.nowName)) {
                            this.excFriendsNum++;
                            this.startIndex++;
                            LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                        } else {
                            if (this.tagFriendType == 1) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList(accessibilityNodeInfo3.getParent(), company_tag_group_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && (accessibilityNodeInfo2 = findViewIdList.get(0)) != null && accessibilityNodeInfo2.getChildCount() > 0) {
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 >= accessibilityNodeInfo2.getChildCount()) {
                                            break;
                                        }
                                        AccessibilityNodeInfo child = accessibilityNodeInfo2.getChild(i2);
                                        if (!(child == null || child.getText() == null)) {
                                            String str = child.getText() + "";
                                            System.out.println("WS_BABY_TAG = " + str);
                                            if (linkedHashSet.contains(str)) {
                                                z2 = true;
                                                break;
                                            }
                                        }
                                        i2++;
                                    }
                                }
                                z2 = false;
                                if (!z2) {
                                    this.startIndex++;
                                    this.excFriendsNum++;
                                    updateBottomText(this.mMyManager, "已跳过客户【 " + this.nowName + " 】");
                                    sleep(100);
                                }
                            } else {
                                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList(accessibilityNodeInfo3.getParent(), company_tag_group_id);
                                if (findViewIdList2 != null && findViewIdList2.size() > 0 && (accessibilityNodeInfo = findViewIdList2.get(0)) != null && accessibilityNodeInfo.getChildCount() > 0) {
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= accessibilityNodeInfo.getChildCount()) {
                                            break;
                                        }
                                        AccessibilityNodeInfo child2 = accessibilityNodeInfo.getChild(i3);
                                        if (!(child2 == null || child2.getText() == null)) {
                                            String str2 = child2.getText() + "";
                                            System.out.println("WS_BABY_TAG = " + str2);
                                            if (linkedHashSet.contains(str2)) {
                                                z = true;
                                                break;
                                            }
                                        }
                                        i3++;
                                    }
                                }
                                z = false;
                                if (z) {
                                    this.startIndex++;
                                    this.excFriendsNum++;
                                    updateBottomText(this.mMyManager, "已跳过客户【 " + this.nowName + " 】");
                                    sleep(100);
                                }
                            }
                            this.excFriendsNum++;
                            LogUtils.log("WS_BABY_LauncherUI.check");
                            this.beforeLists.add(this.nowName);
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(company_group_member_name_id);
                            if (findAccessibilityNodeInfosByViewId3 == null || this.startIndex >= findAccessibilityNodeInfosByViewId3.size()) {
                                this.startIndex++;
                            } else {
                                final AccessibilityNodeInfo accessibilityNodeInfo4 = findAccessibilityNodeInfosByViewId3.get(this.startIndex);
                                this.isCanWhile = false;
                                this.startIndex++;
                                if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                                    PerformClickUtils.performClick(accessibilityNodeInfo4);
                                    PerformClickUtils.performClick(accessibilityNodeInfo3);
                                    LogUtils.log("WS_BABY.FTSMainUI.into");
                                    initContactInfoUI();
                                } else {
                                    new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                        public void surplus(int i) {
                                            MyWindowManager myWindowManager = GroupSendOneByOneFriendsCompanyUtils.this.mMyManager;
                                            BaseServiceCompanyUtils.updateBottomText(myWindowManager, "正在发送，请不要操作微信\n倒计时 " + i + " 秒");
                                        }

                                        public void end() {
                                            PerformClickUtils.performClick(accessibilityNodeInfo4);
                                            PerformClickUtils.performClick(accessibilityNodeInfo3);
                                            LogUtils.log("WS_BABY.FTSMainUI.into");
                                            GroupSendOneByOneFriendsCompanyUtils.this.initContactInfoUI();
                                        }
                                    });
                                }
                            }
                        }
                    }
                } else if (this.startIndex >= findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable && (rootInActiveWindow = this.autoService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(company_customer_list_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                    findAccessibilityNodeInfosByViewId2.get(0).performAction(4096);
                    this.startIndex = 1;
                    sleep(MyApplication.SCROLL_SPEED);
                    if (this.isScrollBottom) {
                        this.isWhile = false;
                    }
                    this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(this.autoService, "个客户");
                }
            }
        }
        if (!this.isWhile && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_LauncherUI.END");
            removeEndView(this.mMyManager);
        }
    }

    public void middleViewShow() {
        setMiddleText(this.mMyManager, "发送结果", "群发客户结束");
    }

    public void endViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个客户开始,发送消息");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (GroupSendOneByOneFriendsCompanyUtils.this.startInt > 1) {
                            System.out.println("WS_BABY_jumpStartPos");
                            GroupSendOneByOneFriendsCompanyUtils.this.jumpStartPos();
                            return;
                        }
                        System.out.println("WS_BABY_executeMain");
                        GroupSendOneByOneFriendsCompanyUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpStartPos() {
        System.out.println("WS_BABY.jumpStartPos.0");
        PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
        System.out.println("WS_BABY.jumpStartPos.1");
        new PerformClickUtils2().checkString3(this.autoService, "我的客户", 10, 50, 100, new PerformClickUtils2.OnCheckListener5() {
            public void find(int i) {
                System.out.println("WS_BABY.jumpStartPos.2");
                PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "我的客户");
                new PerformClickUtils2().checkNodeAllIds(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_customer_list_id, BaseServiceCompanyUtils.company_group_member_name_id, BaseServiceCompanyUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        System.out.println("WS_BABY.jumpStartPos.5");
                        GroupSendOneByOneFriendsCompanyUtils.this.jumpStartWhile();
                    }

                    public void nuFind() {
                        System.out.println("WS_BABY.jumpStartPos.6");
                        BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
                    }
                });
            }

            public void nuFind() {
                System.out.println("WS_BABY.jumpStartPos.3");
                BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
            }

            public void execute(int i) {
                System.out.println("WS_BABY.jumpStartPos.4");
                PerformClickUtils.scrollTop(GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_contact_list_id);
            }
        });
    }

    public void jumpStartWhile() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("WS_BABY.jumpStartWhile.0");
                    if (GroupSendOneByOneFriendsCompanyUtils.this.itemList == null) {
                        GroupSendOneByOneFriendsCompanyUtils.this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_group_member_name_id);
                    }
                    if (GroupSendOneByOneFriendsCompanyUtils.this.itemList == null || GroupSendOneByOneFriendsCompanyUtils.this.itemList.size() <= 0 || !MyApplication.enforceable) {
                        System.out.println("WS_BABY.jumpStartWhile.11.");
                        BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
                        return;
                    }
                    System.out.println("WS_BABY.jumpStartWhile.1");
                    if (GroupSendOneByOneFriendsCompanyUtils.this.startIndex < GroupSendOneByOneFriendsCompanyUtils.this.itemList.size() && MyApplication.enforceable) {
                        System.out.println("WS_BABY.jumpStartWhile.2");
                        AccessibilityNodeInfo accessibilityNodeInfo = GroupSendOneByOneFriendsCompanyUtils.this.itemList.get(GroupSendOneByOneFriendsCompanyUtils.this.startIndex);
                        if (accessibilityNodeInfo != null) {
                            String unused = GroupSendOneByOneFriendsCompanyUtils.this.nowName = accessibilityNodeInfo.getChild(0).getText().toString();
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABY.jumpStartWhile.3." + GroupSendOneByOneFriendsCompanyUtils.this.nowName);
                        }
                        PrintStream printStream2 = System.out;
                        printStream2.println("WS_BABY.jumpStartWhile.4.@" + GroupSendOneByOneFriendsCompanyUtils.this.scrollCount + "@" + GroupSendOneByOneFriendsCompanyUtils.this.startInt);
                        if (GroupSendOneByOneFriendsCompanyUtils.this.scrollCount == GroupSendOneByOneFriendsCompanyUtils.this.startInt) {
                            System.out.println("WS_BABY.jumpStartWhile.5.");
                            GroupSendOneByOneFriendsCompanyUtils.this.checkZombieFan(GroupSendOneByOneFriendsCompanyUtils.this.jumpTags);
                            return;
                        }
                        System.out.println("WS_BABY.jumpStartWhile.6.");
                        GroupSendOneByOneFriendsCompanyUtils.access$1408(GroupSendOneByOneFriendsCompanyUtils.this);
                        GroupSendOneByOneFriendsCompanyUtils.this.beforeLists.add(GroupSendOneByOneFriendsCompanyUtils.this.nowName);
                        GroupSendOneByOneFriendsCompanyUtils.access$1308(GroupSendOneByOneFriendsCompanyUtils.this);
                        MyWindowManager myWindowManager = GroupSendOneByOneFriendsCompanyUtils.this.mMyManager;
                        BaseServiceCompanyUtils.updateBottomText(myWindowManager, "从第 " + GroupSendOneByOneFriendsCompanyUtils.this.startInt + " 个客户开始\n已跳过 " + GroupSendOneByOneFriendsCompanyUtils.this.scrollCount + " 个客户，请不要操作微信");
                        GroupSendOneByOneFriendsCompanyUtils.this.jumpStartWhile();
                    } else if (GroupSendOneByOneFriendsCompanyUtils.this.startIndex >= GroupSendOneByOneFriendsCompanyUtils.this.itemList.size() && MyApplication.enforceable) {
                        System.out.println("WS_BABY.jumpStartWhile.7.");
                        GroupSendOneByOneFriendsCompanyUtils.this.itemList = null;
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_customer_list_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            GroupSendOneByOneFriendsCompanyUtils.this.jumpStartWhile();
                            return;
                        }
                        System.out.println("WS_BABY.jumpStartWhile.8.");
                        findViewIdList.get(0).performAction(4096);
                        int unused2 = GroupSendOneByOneFriendsCompanyUtils.this.startIndex = 1;
                        GroupSendOneByOneFriendsCompanyUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        if (GroupSendOneByOneFriendsCompanyUtils.this.isScrollBottom) {
                            System.out.println("WS_BABY.jumpStartWhile.9.");
                            BaseServiceCompanyUtils.removeEndView(GroupSendOneByOneFriendsCompanyUtils.this.mMyManager);
                        }
                        System.out.println("WS_BABY.jumpStartWhile.10.");
                        boolean unused3 = GroupSendOneByOneFriendsCompanyUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsCompanyUtils.this.autoService, "个客户");
                        GroupSendOneByOneFriendsCompanyUtils.this.jumpStartWhile();
                    }
                } catch (Exception unused4) {
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
