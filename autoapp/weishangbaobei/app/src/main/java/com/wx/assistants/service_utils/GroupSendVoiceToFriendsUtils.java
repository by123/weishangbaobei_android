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

    public void middleViewDismiss() {
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

    private GroupSendVoiceToFriendsUtils() {
    }

    public static GroupSendVoiceToFriendsUtils getInstance() {
        instance = new GroupSendVoiceToFriendsUtils();
        return instance;
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
            } catch (Exception unused) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
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
                        if (GroupSendVoiceToFriendsUtils.this.friendsList != null) {
                            if (GroupSendVoiceToFriendsUtils.this.friendsList.size() != 0) {
                                GroupSendVoiceToFriendsUtils.this.executePartMain();
                                return;
                            }
                        }
                        LogUtils.log("WS_BABY_LauncherUI.Part.END");
                        BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                    } else if (GroupSendVoiceToFriendsUtils.this.sendType == 2) {
                        GroupSendVoiceToFriendsUtils.this.executeNoSendMain(GroupSendVoiceToFriendsUtils.this.model);
                    }
                } catch (Exception unused) {
                }
            }
        }).start();
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
                public void nuFind() {
                }

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
            });
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
            new PerformClickUtils2().checkNodeId(this.autoService, list_item_name_id, executeSpeedSetting + executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (GroupSendVoiceToFriendsUtils.this.startInt <= 1 || !GroupSendVoiceToFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        GroupSendVoiceToFriendsUtils.this.executeFriends();
                        return;
                    }
                    boolean unused = GroupSendVoiceToFriendsUtils.this.isJumpedStart = false;
                    GroupSendVoiceToFriendsUtils.this.jumpStartPosition();
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
                        if (GroupSendVoiceToFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                        }
                        boolean unused = GroupSendVoiceToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "位联系人");
                        int unused2 = GroupSendVoiceToFriendsUtils.this.startIndex = 1;
                        GroupSendVoiceToFriendsUtils.this.jumpStartPosition();
                    }
                });
            }
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

    public void initFirstChattingUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id)) {
                        PerformClickUtils.inputText(GroupSendVoiceToFriendsUtils.this.autoService, "");
                        GroupSendVoiceToFriendsUtils.this.sleep(100);
                    }
                    LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
                    PerformClickUtils.findViewIdAndClick(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id);
                    new PerformClickUtils2().checkCardCollectionName(GroupSendVoiceToFriendsUtils.this.autoService, BaseServiceUtils.send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            LogUtils.log("WS_BABY_FavSelectUI.clickCollection.4");
                            PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendVoiceToFriendsUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                            LogUtils.log("WS_BABY_FavSelectUI.clickCollection.5");
                            GroupSendVoiceToFriendsUtils.this.initFavSelectUI();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFavSelectUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + (executeSpeedSetting / 2), 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                /* JADX WARNING: Removed duplicated region for block: B:34:0x009e  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void find(int r9) {
                    /*
                        r8 = this;
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r9 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        com.wx.assistants.service.AutoService r9 = r9.autoService
                        android.view.accessibility.AccessibilityNodeInfo r9 = r9.getRootInActiveWindow()
                        if (r9 == 0) goto L_0x0127
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r9 = r9.findAccessibilityNodeInfosByViewId(r0)
                        if (r9 == 0) goto L_0x0127
                        int r0 = r9.size()
                        if (r0 <= 0) goto L_0x0127
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable
                        if (r0 == 0) goto L_0x0127
                        java.lang.String r0 = "WS_BABY_FavSelectUI.into3"
                        com.wx.assistants.utils.LogUtils.log(r0)
                        r0 = 0
                        java.lang.Object r1 = r9.get(r0)
                        r2 = 1
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "android.widget.ListView"
                        java.lang.Object r3 = r9.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        java.lang.CharSequence r3 = r3.getClassName()
                        boolean r1 = r1.equals(r3)
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into4"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        java.lang.Object r1 = r9.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
                        int r1 = r1.getChildCount()
                        if (r1 <= 0) goto L_0x009b
                        boolean r1 = com.wx.assistants.application.MyApplication.enforceable
                        if (r1 == 0) goto L_0x009b
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into5"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        r1 = 0
                    L_0x0056:
                        java.lang.Object r3 = r9.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        int r3 = r3.getChildCount()
                        if (r1 >= r3) goto L_0x009b
                        boolean r3 = com.wx.assistants.application.MyApplication.enforceable
                        if (r3 != 0) goto L_0x0067
                        return
                    L_0x0067:
                        java.lang.Object r3 = r9.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3
                        android.view.accessibility.AccessibilityNodeInfo r3 = r3.getChild(r1)
                        if (r3 == 0) goto L_0x0098
                        java.lang.CharSequence r4 = r3.getClassName()
                        if (r4 == 0) goto L_0x0098
                        java.lang.String r4 = "android.widget.FrameLayout"
                        java.lang.CharSequence r5 = r3.getClassName()
                        boolean r4 = r4.equals(r5)
                        if (r4 == 0) goto L_0x0098
                        boolean r4 = com.wx.assistants.application.MyApplication.enforceable
                        if (r4 == 0) goto L_0x0098
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into6"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into7"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r3)
                        r1 = 1
                        goto L_0x009c
                    L_0x0098:
                        int r1 = r1 + 1
                        goto L_0x0056
                    L_0x009b:
                        r1 = 0
                    L_0x009c:
                        if (r1 != 0) goto L_0x00ce
                        java.lang.String r1 = "WS_BABY_FavSelectUI.into77777"
                        com.wx.assistants.utils.LogUtils.log(r1)
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        com.wx.assistants.service.AutoService r1 = r1.autoService
                        java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.collection_list_id
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r3)
                        if (r1 == 0) goto L_0x00ce
                        int r3 = r1.size()
                        if (r3 <= 0) goto L_0x00ce
                        java.lang.Object r1 = r1.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
                        int r1 = r1.getChildCount()
                        if (r1 <= r2) goto L_0x00ce
                        java.lang.Object r9 = r9.get(r0)
                        android.view.accessibility.AccessibilityNodeInfo r9 = (android.view.accessibility.AccessibilityNodeInfo) r9
                        android.view.accessibility.AccessibilityNodeInfo r9 = r9.getChild(r2)
                        com.wx.assistants.utils.PerformClickUtils.performClick(r9)
                    L_0x00ce:
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r9 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        java.lang.String r9 = r9.sayContent
                        if (r9 == 0) goto L_0x010e
                        java.lang.String r9 = ""
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r0 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        java.lang.String r0 = r0.sayContent
                        boolean r9 = r9.equals(r0)
                        if (r9 != 0) goto L_0x010e
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r9 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        r0 = 10
                        r9.sleep(r0)
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r9 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        com.wx.assistants.service.AutoService r9 = r9.autoService
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        r0.<init>()
                        java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.remarkName
                        r0.append(r1)
                        java.lang.String r1 = ""
                        r0.append(r1)
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r1 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        java.lang.String r1 = r1.sayContent
                        r0.append(r1)
                        java.lang.String r0 = r0.toString()
                        com.wx.assistants.utils.PerformClickUtils.inputText(r9, r0)
                    L_0x010e:
                        com.wx.assistants.utils.PerformClickUtils2 r1 = new com.wx.assistants.utils.PerformClickUtils2
                        r1.<init>()
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils r9 = com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.this
                        com.wx.assistants.service.AutoService r2 = r9.autoService
                        java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id
                        r4 = 50
                        r5 = 50
                        r6 = 100
                        com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils$7$1 r7 = new com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils$7$1
                        r7.<init>()
                        r1.checkNodeId(r2, r3, r4, r5, r6, r7)
                    L_0x0127:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.GroupSendVoiceToFriendsUtils.AnonymousClass7.find(int):void");
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

    public void initFirstFTSMainUI() {
        try {
            if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                LogUtils.log("WS_BABY.FTSMainUI.into");
                searchNickName();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                }

                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into");
                    GroupSendVoiceToFriendsUtils.this.searchNickName();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

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
                        public void nuFind() {
                        }

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
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeNoSendMain(OperationParameterModel operationParameterModel) {
        this.circulateInnerSize = operationParameterModel.getCirculateInnerSize();
        executeAllMain(this.sendType);
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
                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = GroupSendVoiceToFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendVoiceToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                                }

                                public void end() {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    GroupSendVoiceToFriendsUtils.this.initFirstContactInfoUI();
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
                            public void nuFind() {
                            }

                            public void find(int i) {
                                if (GroupSendVoiceToFriendsUtils.this.isScrollBottom) {
                                    BaseServiceUtils.removeEndView(GroupSendVoiceToFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean unused = GroupSendVoiceToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendVoiceToFriendsUtils.this.autoService, "位联系人");
                                int unused2 = GroupSendVoiceToFriendsUtils.this.startIndex = 1;
                                GroupSendVoiceToFriendsUtils.this.executeFriends();
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
        setMiddleText(myWindowManager, "给好友发送语音", "共发送给了 " + this.excFriendsNum + " 个好友 ");
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
