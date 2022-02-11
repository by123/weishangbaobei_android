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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendOneByOneFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendOneByOneFriendsUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    private boolean isCanExecute = true;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFirstSelectImg = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public int isOriginPic = 1;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String localImgUrl;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String nowSearchName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public int picCount = 1;
    /* access modifiers changed from: private */
    public int realExcFriendNum = 0;
    /* access modifiers changed from: private */
    public String recordNowName = "";
    /* access modifiers changed from: private */
    public int saveCount = 1;
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;
    /* access modifiers changed from: private */
    public int tagFriendType = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1708(GroupSendOneByOneFriendsUtils groupSendOneByOneFriendsUtils) {
        int i = groupSendOneByOneFriendsUtils.startIndex;
        groupSendOneByOneFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1808(GroupSendOneByOneFriendsUtils groupSendOneByOneFriendsUtils) {
        int i = groupSendOneByOneFriendsUtils.scrollCount;
        groupSendOneByOneFriendsUtils.scrollCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(GroupSendOneByOneFriendsUtils groupSendOneByOneFriendsUtils) {
        int i = groupSendOneByOneFriendsUtils.excFriendsNum;
        groupSendOneByOneFriendsUtils.excFriendsNum = i + 1;
        return i;
    }

    private GroupSendOneByOneFriendsUtils() {
    }

    public static GroupSendOneByOneFriendsUtils getInstance() {
        instance = new GroupSendOneByOneFriendsUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel2) {
        this.excFriendsNum = 0;
        this.realExcFriendNum = 0;
        this.isFirst = true;
        this.beforeLists = new ArrayList();
        this.startIndex = 0;
        this.scrollCount = 0;
        this.saveCount = 1;
        this.recordNowName = "";
        this.isCanExecute = true;
        this.isJumpedStart = true;
        this.isFirstSelectImg = true;
        this.operationParameterModel = operationParameterModel2;
        this.tagFriendType = operationParameterModel2.getTagFriendType();
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.jumpPersons = operationParameterModel2.getTagListPeoples();
        this.startInt = operationParameterModel2.getStartIndex();
        this.sayContent = operationParameterModel2.getSayContent();
        this.localImgUrl = operationParameterModel2.getLocalImgUrl();
        this.sendOrder = operationParameterModel2.getSendOrder();
        this.isOriginPic = operationParameterModel2.getIsOriginPic();
        this.picCount = operationParameterModel2.getMaterialPicCount();
        this.isScrollBottom = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (this.tagFriendType == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.jumpPersons.size() > 0) {
                            this.jumpPersons.remove(this.jumpPersons.iterator().next());
                        }
                    }
                    return;
                }
                LogUtils.log("WS_BABY.st.2");
            } catch (Exception unused) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
    }

    public void initAlbumPreviewUI() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY_AlbumPreviewUI_1");
            new PerformClickUtils2().checkNodeId(this.autoService, img_first_check_layout_id, executeSpeedSetting + 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                LogUtils.log("WS_BABY_AlbumPreviewUI_2");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.img_first_check_layout_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                    GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                    return;
                                }
                                int access$000 = GroupSendOneByOneFriendsUtils.this.picCount - 1;
                                while (true) {
                                    if (access$000 <= -1) {
                                        break;
                                    } else if (!MyApplication.enforceable) {
                                        break;
                                    } else {
                                        GroupSendOneByOneFriendsUtils.this.sleep(5);
                                        PerformClickUtils.performClick(findViewIdList.get(access$000));
                                        access$000--;
                                    }
                                }
                                GroupSendOneByOneFriendsUtils.this.sleep(100);
                                if (GroupSendOneByOneFriendsUtils.this.isOriginPic == 0) {
                                    PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsUtils.this.autoService, "原图");
                                    GroupSendOneByOneFriendsUtils.this.sleep(100);
                                }
                                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.complete_id);
                                if (findViewIdList2 == null || findViewIdList2.size() <= 0 || !findViewIdList2.get(0).isEnabled()) {
                                    GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList2.get(0));
                                LogUtils.log("WS_BABY_AlbumPreviewUI_3");
                                new PerformClickUtils2().checkNilStringTwo(GroupSendOneByOneFriendsUtils.this.autoService, "请稍候", "正在发送中", 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        GroupSendOneByOneFriendsUtils.this.albumSelect();
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

    public void albumSelect() {
        this.excFriendsNum++;
        updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个好友开始\n已发送 " + this.realExcFriendNum + " 个好友");
        if (this.startInt == 1) {
            this.saveCount = this.excFriendsNum + 1;
        } else {
            this.saveCount = this.excFriendsNum + this.startInt;
        }
        if (this.tagFriendType == 0) {
            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_all", Integer.valueOf(this.saveCount));
        } else if (this.tagFriendType == 1) {
            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_part", Integer.valueOf(this.saveCount));
        } else if (this.tagFriendType == 2) {
            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_shield", Integer.valueOf(this.saveCount));
        }
        if (this.operationParameterModel.getMessageSendType() != 2) {
            LogUtils.log("WS_BABY_AlbumPreviewUI_5");
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                        GroupSendOneByOneFriendsUtils.this.executePartMain();
                    } else {
                        GroupSendOneByOneFriendsUtils.this.executeMain();
                    }
                }
            });
        } else if (this.sendOrder == 0) {
            sendText();
        } else {
            LogUtils.log("WS_BABY_AlbumPreviewUI_5");
            PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                        GroupSendOneByOneFriendsUtils.this.executePartMain();
                    } else {
                        GroupSendOneByOneFriendsUtils.this.executeMain();
                    }
                }
            });
        }
    }

    public void initContactInfoUI() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始\n已发送 " + this.realExcFriendNum + " 个好友");
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + 350 + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                GroupSendOneByOneFriendsUtils.this.getRemarkFriendInfo(GroupSendOneByOneFriendsUtils.this.operationParameterModel.getIntimateMode(), GroupSendOneByOneFriendsUtils.this.recordNowName);
                PerformClickUtils.findViewIdAndClick2(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 20, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        GroupSendOneByOneFriendsUtils.this.jumpFirstChattingUI(0);
                    }

                    public void nuFind() {
                        GroupSendOneByOneFriendsUtils.this.getRemarkFriendInfo(GroupSendOneByOneFriendsUtils.this.operationParameterModel.getIntimateMode(), GroupSendOneByOneFriendsUtils.this.recordNowName);
                        PerformClickUtils.findViewIdAndClick2(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                        PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 20, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                GroupSendOneByOneFriendsUtils.this.jumpFirstChattingUI(0);
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                                PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                            GroupSendOneByOneFriendsUtils.this.executePartMain();
                                        } else {
                                            GroupSendOneByOneFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                            GroupSendOneByOneFriendsUtils.this.executePartMain();
                        } else {
                            GroupSendOneByOneFriendsUtils.this.executeMain();
                        }
                    }
                });
            }
        });
    }

    public void executePartMain() {
        try {
            if (this.isCanExecute && MyApplication.enforceable) {
                if (this.isFirst && MyApplication.enforceable) {
                    this.isFirst = false;
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    sleep(200);
                }
                new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_img_id, contact_nav_search_viewgroup_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = GroupSendOneByOneFriendsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(GroupSendOneByOneFriendsUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            GroupSendOneByOneFriendsUtils.this.initFirstFTSMainUI();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendOneByOneFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendOneByOneFriendsUtils.this.realExcFriendNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                }

                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into");
                    GroupSendOneByOneFriendsUtils.this.searchNickName();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, executeSpeed + 300 + executeSpeedSetting, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (GroupSendOneByOneFriendsUtils.this.jumpPersons == null || GroupSendOneByOneFriendsUtils.this.jumpPersons.size() <= 0 || !MyApplication.enforceable) {
                        BaseServiceUtils.removeEndView(GroupSendOneByOneFriendsUtils.this.mMyManager);
                    } else {
                        LogUtils.log("WS_BABY.search_into.1");
                        String str = (String) GroupSendOneByOneFriendsUtils.this.jumpPersons.iterator().next();
                        String unused = GroupSendOneByOneFriendsUtils.this.nowSearchName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.search_id);
                        GroupSendOneByOneFriendsUtils.this.sleep(350);
                        PerformClickUtils.inputText(GroupSendOneByOneFriendsUtils.this.autoService, str);
                        GroupSendOneByOneFriendsUtils.this.jumpPersons.remove(GroupSendOneByOneFriendsUtils.this.jumpPersons.iterator().next());
                    }
                    LogUtils.log("WS_BABY.search_into.2");
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendOneByOneFriendsUtils.this.autoService, "联系人", "最常使用", 600, 100, 25, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    if (GroupSendOneByOneFriendsUtils.this.operationParameterModel.getSendCardType() == 1 && GroupSendOneByOneFriendsUtils.this.operationParameterModel.getIntimateMode() == 0) {
                                        BaseServiceUtils.remarkName = GroupSendOneByOneFriendsUtils.this.nowSearchName;
                                    }
                                    PerformClickUtils.performClick(findViewIdList.get(0));
                                    GroupSendOneByOneFriendsUtils.this.jumpFirstChattingUI(300);
                                } else if (MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.search_back1");
                                    PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                                GroupSendOneByOneFriendsUtils.this.executePartMain();
                                            } else {
                                                GroupSendOneByOneFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                }
                            } else if (MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_back1");
                                PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                            GroupSendOneByOneFriendsUtils.this.executePartMain();
                                        } else {
                                            GroupSendOneByOneFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
                        }

                        public void nuFind() {
                            if (MyApplication.enforceable) {
                                PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                            GroupSendOneByOneFriendsUtils.this.executePartMain();
                                        } else {
                                            GroupSendOneByOneFriendsUtils.this.executeMain();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpFirstChattingUI(int i) {
        this.realExcFriendNum++;
        LogUtils.log("WS_BABY_ChattingUI_3");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, i + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "按住 说话") && MyApplication.enforceable) {
                    PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendOneByOneFriendsUtils.this.sleep(100);
                }
                new PerformClickUtils2().check(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.input_edit_text_id, 20, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ChattingUI_2");
                        if (GroupSendOneByOneFriendsUtils.this.operationParameterModel.getMessageSendType() == 0) {
                            if (GroupSendOneByOneFriendsUtils.this.sayContent != null && !"".equals(GroupSendOneByOneFriendsUtils.this.sayContent)) {
                                new Thread(new Runnable() {
                                    public void run() {
                                        try {
                                            LogUtils.log("WS_BABY_ChattingUI_3");
                                            AutoService autoService = GroupSendOneByOneFriendsUtils.this.autoService;
                                            String str = BaseServiceUtils.input_edit_text_id;
                                            PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceUtils.remarkName + "" + GroupSendOneByOneFriendsUtils.this.sayContent);
                                            new PerformClickUtils2().checkString(GroupSendOneByOneFriendsUtils.this.autoService, "发送", 200, 100, 50, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsUtils.this.autoService, "发送");
                                                    GroupSendOneByOneFriendsUtils.access$908(GroupSendOneByOneFriendsUtils.this);
                                                    MyWindowManager myWindowManager = GroupSendOneByOneFriendsUtils.this.mMyManager;
                                                    BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + GroupSendOneByOneFriendsUtils.this.startInt + " 个好友开始\n已发送 " + GroupSendOneByOneFriendsUtils.this.realExcFriendNum + " 个好友");
                                                    if (GroupSendOneByOneFriendsUtils.this.startInt == 1) {
                                                        int unused = GroupSendOneByOneFriendsUtils.this.saveCount = GroupSendOneByOneFriendsUtils.this.excFriendsNum + 1;
                                                    } else {
                                                        int unused2 = GroupSendOneByOneFriendsUtils.this.saveCount = GroupSendOneByOneFriendsUtils.this.excFriendsNum + GroupSendOneByOneFriendsUtils.this.startInt;
                                                    }
                                                    if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 0) {
                                                        SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_all", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                                                    } else if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                                        SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_part", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                                                    } else if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 2) {
                                                        SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_shield", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                                                    }
                                                    PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                                                GroupSendOneByOneFriendsUtils.this.executePartMain();
                                                            } else {
                                                                GroupSendOneByOneFriendsUtils.this.executeMain();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        } catch (Exception unused) {
                                        }
                                    }
                                }).start();
                            }
                        } else if (GroupSendOneByOneFriendsUtils.this.operationParameterModel.getMessageSendType() == 1) {
                            LogUtils.log("WS_BABY_ChattingUI_4");
                            if (GroupSendOneByOneFriendsUtils.this.localImgUrl != null && !GroupSendOneByOneFriendsUtils.this.localImgUrl.equals("")) {
                                if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                    LogUtils.log("WS_BABY_ChattingUI_44");
                                    PerformClickUtils.inputText(GroupSendOneByOneFriendsUtils.this.autoService, "");
                                    GroupSendOneByOneFriendsUtils.this.sleep(100);
                                }
                                PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                                LogUtils.log("WS_BABY_ChattingUI_5");
                                new PerformClickUtils2().checkStringsFromPhotos(GroupSendOneByOneFriendsUtils.this.autoService, "相册", "你可能要发送的照片", GroupSendOneByOneFriendsUtils.this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : BaseServiceUtils.executeSpeed, 100, 65, new PerformClickUtils2.OnCheckListener5() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        boolean unused = GroupSendOneByOneFriendsUtils.this.isFirstSelectImg = false;
                                        switch (i) {
                                            case 0:
                                                LogUtils.log("WS_BABY_ChattingUI_6");
                                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                                GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                                return;
                                            case 1:
                                                LogUtils.log("WS_BABY_ChattingUI_7");
                                                GroupSendOneByOneFriendsUtils.this.autoService.performGlobalAction(1);
                                                GroupSendOneByOneFriendsUtils.this.sleep(350);
                                                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "相册")) {
                                                    LogUtils.log("WS_BABY_ChattingUI_8");
                                                    PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                                    GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                                    return;
                                                }
                                                LogUtils.log("WS_BABY_ChattingUI_9");
                                                if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                                    PerformClickUtils.inputText(GroupSendOneByOneFriendsUtils.this.autoService, "");
                                                    GroupSendOneByOneFriendsUtils.this.sleep(100);
                                                }
                                                PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                                                GroupSendOneByOneFriendsUtils.this.sleep(350);
                                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                                GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                                return;
                                            default:
                                                return;
                                        }
                                    }

                                    public void execute(int i) {
                                        if (i == 30) {
                                            LogUtils.log("WS_BABY.ChattingUI_7");
                                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                                LogUtils.log("WS_BABY.ChattingUI_8");
                                                GroupSendOneByOneFriendsUtils.this.autoService.performGlobalAction(1);
                                                return;
                                            }
                                            LogUtils.log("WS_BABY.ChattingUI_9");
                                            PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                                        }
                                    }
                                });
                            }
                        } else if (GroupSendOneByOneFriendsUtils.this.operationParameterModel.getMessageSendType() == 2) {
                            LogUtils.log("WS_BABY_ChattingUI_5");
                            if (GroupSendOneByOneFriendsUtils.this.sayContent != null && !GroupSendOneByOneFriendsUtils.this.sayContent.equals("")) {
                                if (GroupSendOneByOneFriendsUtils.this.sendOrder == 0) {
                                    GroupSendOneByOneFriendsUtils.this.sendImg();
                                } else {
                                    GroupSendOneByOneFriendsUtils.this.sendText();
                                }
                            }
                        }
                    }

                    public void nuFind() {
                        GroupSendOneByOneFriendsUtils.access$908(GroupSendOneByOneFriendsUtils.this);
                        MyWindowManager myWindowManager = GroupSendOneByOneFriendsUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + GroupSendOneByOneFriendsUtils.this.startInt + " 个好友开始\n已发送 " + GroupSendOneByOneFriendsUtils.this.realExcFriendNum + " 个好友");
                        if (GroupSendOneByOneFriendsUtils.this.startInt == 1) {
                            int unused = GroupSendOneByOneFriendsUtils.this.saveCount = GroupSendOneByOneFriendsUtils.this.excFriendsNum + 1;
                        } else {
                            int unused2 = GroupSendOneByOneFriendsUtils.this.saveCount = GroupSendOneByOneFriendsUtils.this.excFriendsNum + GroupSendOneByOneFriendsUtils.this.startInt;
                        }
                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 0) {
                            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_all", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                        } else if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_part", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                        } else if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 2) {
                            SPUtils.put(MyApplication.getConText(), "one_bye_one_text_img_num_shield", Integer.valueOf(GroupSendOneByOneFriendsUtils.this.saveCount));
                        }
                        PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                    GroupSendOneByOneFriendsUtils.this.executePartMain();
                                } else {
                                    GroupSendOneByOneFriendsUtils.this.executeMain();
                                }
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
            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, send_add_id)) {
                PerformClickUtils.inputText(this.autoService, "");
                sleep(100);
            }
            PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
            new PerformClickUtils2().checkStringsFromPhotos(this.autoService, "相册", "你可能要发送的照片", this.isFirstSelectImg ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed, 100, 65, new PerformClickUtils2.OnCheckListener5() {
                public void nuFind() {
                }

                public void find(int i) {
                    boolean unused = GroupSendOneByOneFriendsUtils.this.isFirstSelectImg = false;
                    switch (i) {
                        case 0:
                            LogUtils.log("WS_BABY_ChattingUI_9");
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                            return;
                        case 1:
                            LogUtils.log("WS_BABY_ChattingUI_10");
                            GroupSendOneByOneFriendsUtils.this.autoService.performGlobalAction(1);
                            GroupSendOneByOneFriendsUtils.this.sleep(350);
                            if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "相册")) {
                                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                                GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                                return;
                            }
                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                                PerformClickUtils.inputText(GroupSendOneByOneFriendsUtils.this.autoService, "");
                                GroupSendOneByOneFriendsUtils.this.sleep(100);
                            }
                            PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                            GroupSendOneByOneFriendsUtils.this.sleep(350);
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, "相册", BaseServiceUtils.album_id);
                            GroupSendOneByOneFriendsUtils.this.initAlbumPreviewUI();
                            return;
                        default:
                            return;
                    }
                }

                public void execute(int i) {
                    if (i == 30) {
                        LogUtils.log("WS_BABY.ChattingUI_7");
                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                            LogUtils.log("WS_BABY.ChattingUI_8");
                            GroupSendOneByOneFriendsUtils.this.autoService.performGlobalAction(1);
                            return;
                        }
                        LogUtils.log("WS_BABY.ChattingUI_9");
                        PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    }
                }
            });
        }
    }

    public void sendText() {
        new PerformClickUtils2().check(this.autoService, voice_left_id, this.sendOrder == 0 ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed, 100, 1200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "按住 说话")) {
                    LogUtils.log("WS_BABY_ChattingUI_6");
                    PerformClickUtils.findViewIdAndClick(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    GroupSendOneByOneFriendsUtils.this.sleep(100);
                }
                new PerformClickUtils2().checkNodeId(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.input_edit_text_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY_ChattingUI_7");
                        AutoService autoService = GroupSendOneByOneFriendsUtils.this.autoService;
                        String str = BaseServiceUtils.input_edit_text_id;
                        PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceUtils.remarkName + "" + GroupSendOneByOneFriendsUtils.this.sayContent);
                        new PerformClickUtils2().checkString(GroupSendOneByOneFriendsUtils.this.autoService, "发送", 100, 100, 50, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendOneByOneFriendsUtils.this.autoService, "发送");
                                if (GroupSendOneByOneFriendsUtils.this.sendOrder == 0) {
                                    PerformClickUtils.executedBackHome(GroupSendOneByOneFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                                                GroupSendOneByOneFriendsUtils.this.executePartMain();
                                            } else {
                                                GroupSendOneByOneFriendsUtils.this.executeMain();
                                            }
                                        }
                                    });
                                } else {
                                    GroupSendOneByOneFriendsUtils.this.sendImg();
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
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 10) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (GroupSendOneByOneFriendsUtils.this.startInt <= 1 || !GroupSendOneByOneFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        GroupSendOneByOneFriendsUtils.this.executeFriends();
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + GroupSendOneByOneFriendsUtils.this.startInt + "开始");
                    boolean unused = GroupSendOneByOneFriendsUtils.this.isJumpedStart = false;
                    GroupSendOneByOneFriendsUtils.this.jumpStartPosition();
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
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (GroupSendOneByOneFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(GroupSendOneByOneFriendsUtils.this.mMyManager);
                        }
                        boolean unused = GroupSendOneByOneFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "位联系人");
                        int unused2 = GroupSendOneByOneFriendsUtils.this.startIndex = 1;
                        GroupSendOneByOneFriendsUtils.this.jumpStartPosition();
                    }
                });
            }
        }
    }

    public void executeFriends() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_0");
            new PerformClickUtils2().checkNodeAllIdsAsy(this.autoService, list_item_layout_id, list_item_name_id, 10, 100, 50, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_1111");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_5555_" + GroupSendOneByOneFriendsUtils.this.startIndex);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_names_null");
                        return;
                    }
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_6666_" + findViewIdList.size());
                    if (GroupSendOneByOneFriendsUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(GroupSendOneByOneFriendsUtils.this.startIndex);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            LogUtils.log("WS_BABY.LauncherUI.executeFriends_name_" + str);
                            String unused = GroupSendOneByOneFriendsUtils.this.recordNowName = str;
                        }
                        GroupSendOneByOneFriendsUtils.access$1808(GroupSendOneByOneFriendsUtils.this);
                        if (GroupSendOneByOneFriendsUtils.this.beforeLists != null && GroupSendOneByOneFriendsUtils.this.beforeLists.size() > 50) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                GroupSendOneByOneFriendsUtils.this.beforeLists.remove(0);
                            }
                        }
                        if (GroupSendOneByOneFriendsUtils.this.jumpPersons != null && GroupSendOneByOneFriendsUtils.this.jumpPersons.size() > 0 && GroupSendOneByOneFriendsUtils.this.jumpPersons.contains(GroupSendOneByOneFriendsUtils.this.recordNowName)) {
                            GroupSendOneByOneFriendsUtils.access$1708(GroupSendOneByOneFriendsUtils.this);
                            GroupSendOneByOneFriendsUtils.access$908(GroupSendOneByOneFriendsUtils.this);
                            BaseServiceUtils.updateBottomText(GroupSendOneByOneFriendsUtils.this.mMyManager, "已跳过【 " + GroupSendOneByOneFriendsUtils.this.recordNowName + " 】");
                            GroupSendOneByOneFriendsUtils.this.sleep(10);
                            GroupSendOneByOneFriendsUtils.this.executeFriends();
                        } else if (GroupSendOneByOneFriendsUtils.this.isScrollBottom && GroupSendOneByOneFriendsUtils.this.beforeLists.contains(GroupSendOneByOneFriendsUtils.this.recordNowName)) {
                            GroupSendOneByOneFriendsUtils.access$1708(GroupSendOneByOneFriendsUtils.this);
                            LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + GroupSendOneByOneFriendsUtils.this.recordNowName);
                            GroupSendOneByOneFriendsUtils.this.executeFriends();
                        } else if ("微信团队".equals(GroupSendOneByOneFriendsUtils.this.recordNowName) || "文件传输助手".equals(GroupSendOneByOneFriendsUtils.this.recordNowName)) {
                            GroupSendOneByOneFriendsUtils.access$908(GroupSendOneByOneFriendsUtils.this);
                            GroupSendOneByOneFriendsUtils.access$1708(GroupSendOneByOneFriendsUtils.this);
                            LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                            GroupSendOneByOneFriendsUtils.this.executeFriends();
                        } else {
                            GroupSendOneByOneFriendsUtils.this.beforeLists.add(GroupSendOneByOneFriendsUtils.this.recordNowName);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || GroupSendOneByOneFriendsUtils.this.startIndex >= findViewIdList2.size()) {
                                GroupSendOneByOneFriendsUtils.access$1708(GroupSendOneByOneFriendsUtils.this);
                                GroupSendOneByOneFriendsUtils.this.executeFriends();
                                return;
                            }
                            final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(GroupSendOneByOneFriendsUtils.this.startIndex);
                            GroupSendOneByOneFriendsUtils.access$1708(GroupSendOneByOneFriendsUtils.this);
                            if (GroupSendOneByOneFriendsUtils.this.spaceTime <= 0 || GroupSendOneByOneFriendsUtils.this.realExcFriendNum <= 0) {
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                GroupSendOneByOneFriendsUtils.this.initContactInfoUI();
                                return;
                            }
                            new PerformClickUtils2().countDown2(GroupSendOneByOneFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = GroupSendOneByOneFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendOneByOneFriendsUtils.this.realExcFriendNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                                }

                                public void end() {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    GroupSendOneByOneFriendsUtils.this.initContactInfoUI();
                                }
                            });
                        }
                    } else if (GroupSendOneByOneFriendsUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                        PerformClickUtils.scroll(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                        new PerformClickUtils2().check(GroupSendOneByOneFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                if (GroupSendOneByOneFriendsUtils.this.isScrollBottom) {
                                    BaseServiceUtils.removeEndView(GroupSendOneByOneFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean unused = GroupSendOneByOneFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendOneByOneFriendsUtils.this.autoService, "位联系人");
                                int unused2 = GroupSendOneByOneFriendsUtils.this.startIndex = 1;
                                GroupSendOneByOneFriendsUtils.this.executeFriends();
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

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "发送结果", "从第" + this.startInt + "个好友开始,本次共发送了 " + this.realExcFriendNum + " 个好友 ");
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始,发送消息");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (GroupSendOneByOneFriendsUtils.this.tagFriendType == 1) {
                            GroupSendOneByOneFriendsUtils.this.executePartMain();
                        } else {
                            GroupSendOneByOneFriendsUtils.this.executeMain();
                        }
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
