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
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendVoiceToFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendVoiceToFriendsUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int circulateInnerSize = 1;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> friendsList = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public boolean isCanExecute = true;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFirstFav = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    boolean jumpWhile = true;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String recordNowName = "";
    /* access modifiers changed from: private */
    public int saveCount = 1;
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private GroupSendVoiceToFriendsUtils() {
    }

    static /* synthetic */ int access$1208(GroupSendVoiceToFriendsUtils groupSendVoiceToFriendsUtils) {
        int i = groupSendVoiceToFriendsUtils.excFriendsNum;
        groupSendVoiceToFriendsUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1508(GroupSendVoiceToFriendsUtils groupSendVoiceToFriendsUtils) {
        int i = groupSendVoiceToFriendsUtils.scrollCount;
        groupSendVoiceToFriendsUtils.scrollCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(GroupSendVoiceToFriendsUtils groupSendVoiceToFriendsUtils) {
        int i = groupSendVoiceToFriendsUtils.startIndex;
        groupSendVoiceToFriendsUtils.startIndex = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    public void executeMain() {
        if (this.sendType == 0) {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始群发语音");
        } else {
            showBottomView(this.mMyManager, "正在给微信好友发送语音");
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (GroupSendVoiceToFriendsUtils.this.sendType == 0) {
                        GroupSendVoiceToFriendsUtils.this.executeAllMain(GroupSendVoiceToFriendsUtils.this.sendType);
                    } else if (GroupSendVoiceToFriendsUtils.this.sendType == 1) {
                        if (GroupSendVoiceToFriendsUtils.this.friendsList == null || GroupSendVoiceToFriendsUtils.this.friendsList.size() == 0) {
                            LogUtils.log("WS_BABY_LauncherUI.Part.END");
                            BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                            return;
                        }
                        GroupSendVoiceToFriendsUtils.this.executePartMain();
                    } else if (GroupSendVoiceToFriendsUtils.this.sendType == 2) {
                        GroupSendVoiceToFriendsUtils.this.executeNoSendMain(GroupSendVoiceToFriendsUtils.this.model);
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public static GroupSendVoiceToFriendsUtils getInstance() {
        instance = new GroupSendVoiceToFriendsUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始,发送语音");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GroupSendVoiceToFriendsUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAllMain(int i) {
        try {
            LogUtils.log("WS_BABY.LauncherUI.executeAllMain");
            this.circulateInnerSize = this.model.getCirculateInnerSize();
            LogUtils.log("WS_BABY.LauncherUI.executeAllMain2");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep(400);
            }
            new PerformClickUtils2().checkNodeId(this.autoService, list_item_name_id, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (GroupSendVoiceToFriendsUtils.this.startInt <= 1 || !GroupSendVoiceToFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        GroupSendVoiceToFriendsUtils.this.executeFriends();
                        return;
                    }
                    boolean unused = GroupSendVoiceToFriendsUtils.this.isJumpedStart = false;
                    GroupSendVoiceToFriendsUtils.this.jumpStartPosition();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeFriends() {
        if (MyApplication.enforceable) {
            LogUtils.log("WS_BABY.LauncherUI.executeFriends_0");
            new PerformClickUtils2().checkNodeAllIdsAsy(this.autoService, list_item_layout_id, list_item_name_id, 0, 100, 50, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_1111");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_5555_" + GroupSendVoiceToFriendsUtils.this.startIndex);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_names_null");
                        return;
                    }
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_6666_" + findViewIdList.size());
                    if (GroupSendVoiceToFriendsUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(GroupSendVoiceToFriendsUtils.this.startIndex);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            LogUtils.log("WS_BABY.LauncherUI.executeFriends_name_" + str);
                            String unused = GroupSendVoiceToFriendsUtils.this.recordNowName = str;
                        }
                        GroupSendVoiceToFriendsUtils.access$1508(GroupSendVoiceToFriendsUtils.this);
                        if (GroupSendVoiceToFriendsUtils.this.beforeLists != null && GroupSendVoiceToFriendsUtils.this.beforeLists.size() > 50) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                GroupSendVoiceToFriendsUtils.this.beforeLists.remove(0);
                            }
                        }
                        if (GroupSendVoiceToFriendsUtils.this.isScrollBottom && GroupSendVoiceToFriendsUtils.this.beforeLists.contains(GroupSendVoiceToFriendsUtils.this.recordNowName)) {
                            GroupSendVoiceToFriendsUtils.access$808(GroupSendVoiceToFriendsUtils.this);
                            GroupSendVoiceToFriendsUtils.this.executeFriends();
                        } else if ("微信团队".equals(GroupSendVoiceToFriendsUtils.this.recordNowName) || "文件传输助手".equals(GroupSendVoiceToFriendsUtils.this.recordNowName)) {
                            GroupSendVoiceToFriendsUtils.access$808(GroupSendVoiceToFriendsUtils.this);
                            GroupSendVoiceToFriendsUtils.this.executeFriends();
                        } else if (GroupSendVoiceToFriendsUtils.this.sendType != 2 || GroupSendVoiceToFriendsUtils.this.friendsList == null || !GroupSendVoiceToFriendsUtils.this.friendsList.contains(GroupSendVoiceToFriendsUtils.this.recordNowName)) {
                            final AccessibilityNodeInfo accessibilityNodeInfo2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.list_item_layout_id).get(GroupSendVoiceToFriendsUtils.this.startIndex);
                            GroupSendVoiceToFriendsUtils.access$808(GroupSendVoiceToFriendsUtils.this);
                            if (GroupSendVoiceToFriendsUtils.this.recordNowName != null && !"".equals(GroupSendVoiceToFriendsUtils.this.recordNowName)) {
                                GroupSendVoiceToFriendsUtils.this.beforeLists.add(GroupSendVoiceToFriendsUtils.this.recordNowName);
                            }
                            if (GroupSendVoiceToFriendsUtils.this.spaceTime <= 0 || GroupSendVoiceToFriendsUtils.this.excFriendsNum <= 0) {
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                GroupSendVoiceToFriendsUtils.this.initFirstContactInfoUI();
                                return;
                            }
                            new PerformClickUtils2().countDown2(GroupSendVoiceToFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    GroupSendVoiceToFriendsUtils.this.initFirstContactInfoUI();
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                                }
                            });
                        } else {
                            GroupSendVoiceToFriendsUtils.access$808(GroupSendVoiceToFriendsUtils.this);
                            if (GroupSendVoiceToFriendsUtils.this.sendType == 2) {
                                BaseServiceUtils.updateBottomText(GroupSendVoiceToFriendsUtils.this.mMyManager, "已跳过【 " + GroupSendVoiceToFriendsUtils.this.recordNowName + " 】");
                                GroupSendVoiceToFriendsUtils.this.sleep(100);
                            }
                            GroupSendVoiceToFriendsUtils.this.executeFriends();
                        }
                    } else if (GroupSendVoiceToFriendsUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                        PerformClickUtils.scroll(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                        new PerformClickUtils2().check(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (GroupSendVoiceToFriendsUtils.this.isScrollBottom) {
                                    BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean unused = GroupSendVoiceToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "位联系人");
                                int unused2 = GroupSendVoiceToFriendsUtils.this.startIndex = 1;
                                GroupSendVoiceToFriendsUtils.this.executeFriends();
                            }

                            public void nuFind() {
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

    public void executeNoSendMain(OperationParameterModel operationParameterModel) {
        this.circulateInnerSize = operationParameterModel.getCirculateInnerSize();
        executeAllMain(this.sendType);
    }

    public void executePartMain() {
        try {
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep(200);
            }
            new PerformClickUtils2().checkNodeId(this.autoService, contact_nav_search_viewgroup_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList;
                    int unused = GroupSendVoiceToFriendsUtils.this.circulateInnerSize = GroupSendVoiceToFriendsUtils.this.model.getCirculateInnerSize();
                    LogUtils.log("WS_BABY.LauncherUI.executePartMain");
                    if (GroupSendVoiceToFriendsUtils.this.isCanExecute && MyApplication.enforceable && (findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(GroupSendVoiceToFriendsUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && findAccessibilityNodeInfosByViewId.get(0) != null && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        GroupSendVoiceToFriendsUtils.this.initFirstFTSMainUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initBackChattingUI() {
        this.circulateInnerSize--;
        if (this.circulateInnerSize == 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY.ChattingUI.back2");
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    GroupSendVoiceToFriendsUtils.this.executeMain();
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChattingUI.back1");
        int circulateInnerSize2 = this.model.getCirculateInnerSize();
        updateBottomText(this.mMyManager, "已发送 " + this.excFriendsNum + " 个好友\n已循环群发 " + (circulateInnerSize2 - this.circulateInnerSize) + " 次，剩余 " + this.circulateInnerSize + " 次");
        LogUtils.log("WS_BABY_ChattingUI_into");
        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) this.autoService, send_add_id)) {
            LogUtils.log("WS_BABY_ChattingUI_into1");
            PerformClickUtils.inputText(this.autoService, "");
        }
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, executeSpeed + executeSpeedSetting, 400, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ChattingUI_into_2");
                PerformClickUtils.findTextAndClick(GroupSendVoiceToFriendsUtils.this.autoService, "我的收藏");
                GroupSendVoiceToFriendsUtils.this.initFavSelectUI();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ChattingUI_into_3_null");
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.excFriendsNum = 0;
        this.isFirst = true;
        this.beforeLists = new ArrayList();
        this.startIndex = 0;
        this.scrollCount = 0;
        this.saveCount = 1;
        this.isJumpedStart = true;
        this.recordNowName = "";
        this.isScrollBottom = false;
        this.isFirstFav = true;
        this.jumpWhile = true;
        this.model = operationParameterModel;
        this.startInt = operationParameterModel.getStartIndex();
        this.friendsList = new LinkedHashSet<>();
        this.friendsList = operationParameterModel.getTagListPeoples();
        this.isCanExecute = true;
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.sayContent = operationParameterModel.getSayContent();
        this.sendType = operationParameterModel.getSendCardType();
        this.circulateInnerSize = operationParameterModel.getCirculateInnerSize();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (this.sendType == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.friendsList.size() > 0) {
                            this.friendsList.remove(this.friendsList.iterator().next());
                        }
                    }
                    return;
                }
                LogUtils.log("WS_BABY.st.2");
            } catch (Exception e) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
    }

    public void initFavSelectUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + (executeSpeedSetting / 2), 100, 600, new PerformClickUtils2.OnCheckListener() {
                /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    boolean z;
                    AccessibilityNodeInfo rootInActiveWindow = GroupSendVoiceToFriendsUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.collection_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
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
                                if (!z) {
                                    LogUtils.log("WS_BABY_FavSelectUI.into77777");
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.collection_list_id);
                                    if (findViewIdList != null && findViewIdList.size() > 0 && findViewIdList.get(0).getChildCount() > 1) {
                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0).getChild(1));
                                    }
                                }
                                if (GroupSendVoiceToFriendsUtils.this.sayContent != null && !"".equals(GroupSendVoiceToFriendsUtils.this.sayContent)) {
                                    GroupSendVoiceToFriendsUtils.this.sleep(10);
                                    PerformClickUtils.inputText(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.remarkName + "" + GroupSendVoiceToFriendsUtils.this.sayContent);
                                }
                                new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                            findViewIdList.get(0).performAction(16);
                                            if (GroupSendVoiceToFriendsUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                GroupSendVoiceToFriendsUtils.access$1208(GroupSendVoiceToFriendsUtils.this);
                                                if (GroupSendVoiceToFriendsUtils.this.startInt == 1) {
                                                    int unused = GroupSendVoiceToFriendsUtils.this.saveCount = GroupSendVoiceToFriendsUtils.this.excFriendsNum + 1;
                                                } else {
                                                    int unused2 = GroupSendVoiceToFriendsUtils.this.saveCount = GroupSendVoiceToFriendsUtils.this.excFriendsNum + GroupSendVoiceToFriendsUtils.this.startInt + 1;
                                                }
                                                if (GroupSendVoiceToFriendsUtils.this.sendType == 0) {
                                                    SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_all", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                                } else if (GroupSendVoiceToFriendsUtils.this.sendType == 1) {
                                                    SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_part", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                                } else if (GroupSendVoiceToFriendsUtils.this.sendType == 2) {
                                                    SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_shield", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                                }
                                                MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友");
                                            }
                                            new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    boolean unused = GroupSendVoiceToFriendsUtils.this.isFirstFav = false;
                                                    GroupSendVoiceToFriendsUtils.this.initBackChattingUI();
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
                        z = false;
                        if (!z) {
                        }
                        GroupSendVoiceToFriendsUtils.this.sleep(10);
                        PerformClickUtils.inputText(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.remarkName + "" + GroupSendVoiceToFriendsUtils.this.sayContent);
                        new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 50, 100, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                    findViewIdList.get(0).performAction(16);
                                    if (GroupSendVoiceToFriendsUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                        GroupSendVoiceToFriendsUtils.access$1208(GroupSendVoiceToFriendsUtils.this);
                                        if (GroupSendVoiceToFriendsUtils.this.startInt == 1) {
                                            int unused = GroupSendVoiceToFriendsUtils.this.saveCount = GroupSendVoiceToFriendsUtils.this.excFriendsNum + 1;
                                        } else {
                                            int unused2 = GroupSendVoiceToFriendsUtils.this.saveCount = GroupSendVoiceToFriendsUtils.this.excFriendsNum + GroupSendVoiceToFriendsUtils.this.startInt + 1;
                                        }
                                        if (GroupSendVoiceToFriendsUtils.this.sendType == 0) {
                                            SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_all", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                        } else if (GroupSendVoiceToFriendsUtils.this.sendType == 1) {
                                            SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_part", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                        } else if (GroupSendVoiceToFriendsUtils.this.sendType == 2) {
                                            SPUtils.put(MyApplication.getConText(), "voice_to_friends_num_shield", Integer.valueOf(GroupSendVoiceToFriendsUtils.this.saveCount));
                                        }
                                        MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友");
                                    }
                                    new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 100, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            boolean unused = GroupSendVoiceToFriendsUtils.this.isFirstFav = false;
                                            GroupSendVoiceToFriendsUtils.this.initBackChattingUI();
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

    public void initFirstChattingUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                        PerformClickUtils.inputText(GroupSendVoiceToFriendsUtils.this.autoService, "");
                        GroupSendVoiceToFriendsUtils.this.sleep(100);
                    }
                    LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
                    PerformClickUtils.findViewIdAndClick(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    new PerformClickUtils2().checkCardCollectionName(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_FavSelectUI.clickCollection.4");
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                            LogUtils.log("WS_BABY_FavSelectUI.clickCollection.5");
                            GroupSendVoiceToFriendsUtils.this.initFavSelectUI();
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

    public void initFirstContactInfoUI() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友群发语音\n已发送 " + this.excFriendsNum + " 个好友");
            new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", 10, 100, 30, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    GroupSendVoiceToFriendsUtils.this.getRemarkFriendInfo(GroupSendVoiceToFriendsUtils.this.model.getIntimateMode(), GroupSendVoiceToFriendsUtils.this.recordNowName);
                    PerformClickUtils.findViewIdAndClick2(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                    PerformClickUtils.findTextAndClick(GroupSendVoiceToFriendsUtils.this.autoService, "发消息");
                    LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                    new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 0, 100, 15, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            GroupSendVoiceToFriendsUtils.this.initFirstChattingUI();
                        }

                        public void nuFind() {
                            PerformClickUtils.findViewIdAndClick2(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                            PerformClickUtils.findTextAndClick(GroupSendVoiceToFriendsUtils.this.autoService, "发消息");
                            new PerformClickUtils2().checkNodeId(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    GroupSendVoiceToFriendsUtils.this.getRemarkFriendInfo(GroupSendVoiceToFriendsUtils.this.model.getIntimateMode(), GroupSendVoiceToFriendsUtils.this.recordNowName);
                                    GroupSendVoiceToFriendsUtils.this.initFirstChattingUI();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                                    PerformClickUtils.executedBackHome(GroupSendVoiceToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendVoiceToFriendsUtils.this.executeMain();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                    PerformClickUtils.executedBackHome(GroupSendVoiceToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendVoiceToFriendsUtils.this.executeMain();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFirstFTSMainUI() {
        try {
            if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                LogUtils.log("WS_BABY.FTSMainUI.into");
                searchNickName();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into");
                    GroupSendVoiceToFriendsUtils.this.searchNickName();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
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
                    public void find(int i) {
                        if (GroupSendVoiceToFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                        }
                        boolean unused = GroupSendVoiceToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "位联系人");
                        int unused2 = GroupSendVoiceToFriendsUtils.this.startIndex = 1;
                        GroupSendVoiceToFriendsUtils.this.jumpStartPosition();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "给好友发送语音", "共发送给了 " + this.excFriendsNum + " 个好友 ");
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.search_id);
                    if (GroupSendVoiceToFriendsUtils.this.friendsList != null && GroupSendVoiceToFriendsUtils.this.friendsList.size() > 0 && MyApplication.enforceable) {
                        String str = (String) GroupSendVoiceToFriendsUtils.this.friendsList.iterator().next();
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        GroupSendVoiceToFriendsUtils.this.sleep(350);
                        PerformClickUtils.inputText(GroupSendVoiceToFriendsUtils.this.autoService, str);
                        GroupSendVoiceToFriendsUtils.this.friendsList.remove(GroupSendVoiceToFriendsUtils.this.friendsList.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendVoiceToFriendsUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 6) + BannerConfig.DURATION, 100, 30, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList != null && findViewIdList.size() > 0) {
                                    PerformClickUtils.performClick(findViewIdList.get(0));
                                    GroupSendVoiceToFriendsUtils.this.initFirstChattingUI();
                                } else if (MyApplication.enforceable) {
                                    PerformClickUtils.executedBackHome(GroupSendVoiceToFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendVoiceToFriendsUtils.this.executeMain();
                                        }
                                    });
                                }
                            } else if (MyApplication.enforceable) {
                                PerformClickUtils.executedBackHome(GroupSendVoiceToFriendsUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        GroupSendVoiceToFriendsUtils.this.executeMain();
                                    }
                                });
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
}
