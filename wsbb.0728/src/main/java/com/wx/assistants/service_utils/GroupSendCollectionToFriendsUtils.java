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

public class GroupSendCollectionToFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendCollectionToFriendsUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int circulateInnerSize = 1;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> friendsList = new LinkedHashSet<>();
    private boolean isCanExecute = true;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFirstFav = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String nowSearchName = "";
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

    private GroupSendCollectionToFriendsUtils() {
    }

    static /* synthetic */ int access$008(GroupSendCollectionToFriendsUtils groupSendCollectionToFriendsUtils) {
        int i = groupSendCollectionToFriendsUtils.excFriendsNum;
        groupSendCollectionToFriendsUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1308(GroupSendCollectionToFriendsUtils groupSendCollectionToFriendsUtils) {
        int i = groupSendCollectionToFriendsUtils.startIndex;
        groupSendCollectionToFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408(GroupSendCollectionToFriendsUtils groupSendCollectionToFriendsUtils) {
        int i = groupSendCollectionToFriendsUtils.scrollCount;
        groupSendCollectionToFriendsUtils.scrollCount = i + 1;
        return i;
    }

    public static GroupSendCollectionToFriendsUtils getInstance() {
        instance = new GroupSendCollectionToFriendsUtils();
        return instance;
    }

    public void clickCollection() {
        LogUtils.log("WS_BABY_FavSelectUI.clickCollection.1");
        PerformClickUtils.findViewIdAndClick(this.autoService, send_add_id);
        new PerformClickUtils2().checkCardCollectionName(this.autoService, send_add_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.4");
                PerformClickUtils.findTextAndClick((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, "我的收藏", BaseServiceUtils.album_id);
                LogUtils.log("WS_BABY_FavSelectUI.clickCollection.5");
                GroupSendCollectionToFriendsUtils.this.initFavSelectUI();
            }

            public void nuFind() {
            }
        });
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            if (this.sendType == 0) {
                MyWindowManager myWindowManager = this.mMyManager;
                showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始群发收藏");
            } else {
                showBottomView(this.mMyManager, "正在给微信好友发送收藏");
            }
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (GroupSendCollectionToFriendsUtils.this.sendType == 0) {
                            GroupSendCollectionToFriendsUtils.this.executeAllMain(GroupSendCollectionToFriendsUtils.this.sendType);
                        } else if (GroupSendCollectionToFriendsUtils.this.sendType == 1) {
                            GroupSendCollectionToFriendsUtils.this.executePartMain();
                        } else if (GroupSendCollectionToFriendsUtils.this.sendType == 2) {
                            GroupSendCollectionToFriendsUtils.this.executeNoSendMain(GroupSendCollectionToFriendsUtils.this.model);
                        }
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
            this.circulateInnerSize = this.model.getCirculateInnerSize();
            LogUtils.log("WS_BABY.LauncherUI.executeAllMain2");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 10) + 400);
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_item_name_id, list_item_layout_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (GroupSendCollectionToFriendsUtils.this.startInt <= 1 || !GroupSendCollectionToFriendsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        GroupSendCollectionToFriendsUtils.this.executeFriends();
                        return;
                    }
                    boolean unused = GroupSendCollectionToFriendsUtils.this.isJumpedStart = false;
                    LogUtils.log("WS_BABY_LauncherUI_从" + GroupSendCollectionToFriendsUtils.this.startInt + "开始");
                    GroupSendCollectionToFriendsUtils.this.jumpStartPosition();
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
            new PerformClickUtils2().checkNodeAllIdsAsy(this.autoService, list_item_layout_id, list_item_name_id, 10, 100, 50, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_1111");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_5555_" + GroupSendCollectionToFriendsUtils.this.startIndex);
                    if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_names_null");
                        return;
                    }
                    LogUtils.log("WS_BABY.LauncherUI.executeFriends_6666_" + findViewIdList.size());
                    if (GroupSendCollectionToFriendsUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(GroupSendCollectionToFriendsUtils.this.startIndex);
                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                            String str = accessibilityNodeInfo.getText() + "";
                            LogUtils.log("WS_BABY.LauncherUI.executeFriends_name_" + str);
                            String unused = GroupSendCollectionToFriendsUtils.this.recordNowName = str;
                        }
                        GroupSendCollectionToFriendsUtils.access$1408(GroupSendCollectionToFriendsUtils.this);
                        if (GroupSendCollectionToFriendsUtils.this.beforeLists != null && GroupSendCollectionToFriendsUtils.this.beforeLists.size() > 50) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                GroupSendCollectionToFriendsUtils.this.beforeLists.remove(0);
                            }
                        }
                        if (GroupSendCollectionToFriendsUtils.this.isScrollBottom && GroupSendCollectionToFriendsUtils.this.beforeLists.contains(GroupSendCollectionToFriendsUtils.this.recordNowName)) {
                            GroupSendCollectionToFriendsUtils.access$1308(GroupSendCollectionToFriendsUtils.this);
                            GroupSendCollectionToFriendsUtils.this.executeFriends();
                        } else if ("微信团队".equals(GroupSendCollectionToFriendsUtils.this.recordNowName) || "文件传输助手".equals(GroupSendCollectionToFriendsUtils.this.recordNowName)) {
                            GroupSendCollectionToFriendsUtils.access$1308(GroupSendCollectionToFriendsUtils.this);
                            GroupSendCollectionToFriendsUtils.this.executeFriends();
                        } else if (GroupSendCollectionToFriendsUtils.this.sendType != 2 || GroupSendCollectionToFriendsUtils.this.friendsList == null || !GroupSendCollectionToFriendsUtils.this.friendsList.contains(GroupSendCollectionToFriendsUtils.this.recordNowName)) {
                            GroupSendCollectionToFriendsUtils.this.beforeLists.add(GroupSendCollectionToFriendsUtils.this.recordNowName);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                            if (findViewIdList2 == null || findViewIdList2.size() <= 0 || GroupSendCollectionToFriendsUtils.this.startIndex >= findViewIdList2.size()) {
                                GroupSendCollectionToFriendsUtils.access$1308(GroupSendCollectionToFriendsUtils.this);
                                GroupSendCollectionToFriendsUtils.this.executeFriends();
                                return;
                            }
                            final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(GroupSendCollectionToFriendsUtils.this.startIndex);
                            GroupSendCollectionToFriendsUtils.access$1308(GroupSendCollectionToFriendsUtils.this);
                            if (GroupSendCollectionToFriendsUtils.this.spaceTime <= 0 || GroupSendCollectionToFriendsUtils.this.excFriendsNum <= 0) {
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                GroupSendCollectionToFriendsUtils.this.initFirstContactInfoUI();
                                return;
                            }
                            new PerformClickUtils2().countDown2(GroupSendCollectionToFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    GroupSendCollectionToFriendsUtils.this.initFirstContactInfoUI();
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                                }
                            });
                        } else {
                            GroupSendCollectionToFriendsUtils.access$1308(GroupSendCollectionToFriendsUtils.this);
                            if (GroupSendCollectionToFriendsUtils.this.sendType == 2) {
                                BaseServiceUtils.updateBottomText(GroupSendCollectionToFriendsUtils.this.mMyManager, "已跳过【 " + GroupSendCollectionToFriendsUtils.this.recordNowName + " 】");
                                GroupSendCollectionToFriendsUtils.this.sleep(100);
                            }
                            GroupSendCollectionToFriendsUtils.this.executeFriends();
                        }
                    } else if (GroupSendCollectionToFriendsUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.LauncherUI.executeFriends_scroll");
                        PerformClickUtils.scroll(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                        new PerformClickUtils2().check(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (GroupSendCollectionToFriendsUtils.this.isScrollBottom) {
                                    BaseServiceUtils.removeEndView(GroupSendCollectionToFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean unused = GroupSendCollectionToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendCollectionToFriendsUtils.this.autoService, "位联系人");
                                int unused2 = GroupSendCollectionToFriendsUtils.this.startIndex = 1;
                                GroupSendCollectionToFriendsUtils.this.executeFriends();
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
            if (this.friendsList == null || this.friendsList.size() == 0) {
                removeEndView(this.mMyManager);
                return;
            }
            this.circulateInnerSize = this.model.getCirculateInnerSize();
            LogUtils.log("WS_BABY.LauncherUI.executePartMain");
            if (this.isCanExecute && MyApplication.enforceable) {
                if (this.isFirst && MyApplication.enforceable) {
                    this.isFirst = false;
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    sleep(200);
                }
                new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_viewgroup_id, contact_nav_search_img_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToFriendsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(GroupSendCollectionToFriendsUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            GroupSendCollectionToFriendsUtils.this.initFTSMainUI();
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingBackUI() {
        try {
            this.circulateInnerSize--;
            if (this.circulateInnerSize == 0 || !MyApplication.enforceable) {
                PerformClickUtils.executedBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (GroupSendCollectionToFriendsUtils.this.sendType == 1) {
                            GroupSendCollectionToFriendsUtils.this.executePartMain();
                        } else {
                            GroupSendCollectionToFriendsUtils.this.executeAllMain(GroupSendCollectionToFriendsUtils.this.sendType);
                        }
                    }
                });
            } else {
                new PerformClickUtils2().check(this.autoService, voice_left_id, executeSpeed + (executeSpeedSetting / 10), 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.ChattingUI.back1");
                        int circulateInnerSize = GroupSendCollectionToFriendsUtils.this.model.getCirculateInnerSize();
                        MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToFriendsUtils.this.excFriendsNum + " 个好友\n已循环 " + (circulateInnerSize - GroupSendCollectionToFriendsUtils.this.circulateInnerSize) + " 次，剩余 " + GroupSendCollectionToFriendsUtils.this.circulateInnerSize + " 次");
                        LogUtils.log("WS_BABY_ChattingUI_into");
                        GroupSendCollectionToFriendsUtils.this.initChattingFirstUI(0);
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingFirstUI(int i) {
        LogUtils.log("WS_BABY.ChattingUI.into");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, i, 50, 600, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkStringAndIdSomeOne(GroupSendCollectionToFriendsUtils.this.autoService, "发送", BaseServiceUtils.send_add_id, 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            PerformClickUtils.inputText(GroupSendCollectionToFriendsUtils.this.autoService, "");
                            GroupSendCollectionToFriendsUtils.this.clickCollection();
                            return;
                        }
                        GroupSendCollectionToFriendsUtils.this.clickCollection();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
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
        this.isFirstFav = true;
        this.isJumpedStart = true;
        this.recordNowName = "";
        this.nowSearchName = "";
        this.isScrollBottom = false;
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

    public void initFTSMainUI() {
        try {
            if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                LogUtils.log("WS_BABY.FTSMainUI.into");
                searchNickName();
                return;
            }
            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                public void end() {
                    LogUtils.log("WS_BABY.FTSMainUI.into");
                    GroupSendCollectionToFriendsUtils.this.searchNickName();
                }

                public void surplus(int i) {
                    MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToFriendsUtils.this.excFriendsNum + " 个好友\n正在延时等待，倒计时 " + i + " 秒");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFavSelectUI() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, collection_list_id, this.isFirstFav ? SocializeConstants.CANCLE_RESULTCODE : executeSpeed + (executeSpeedSetting / 2), 100, 600, new PerformClickUtils2.OnCheckListener() {
                /* JADX WARNING: Removed duplicated region for block: B:31:0x0099  */
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    boolean z;
                    AccessibilityNodeInfo rootInActiveWindow = GroupSendCollectionToFriendsUtils.this.autoService.getRootInActiveWindow();
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
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.collection_list_id);
                                    if (findViewIdList != null && findViewIdList.size() > 0 && findViewIdList.get(0).getChildCount() > 1) {
                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0).getChild(1));
                                    }
                                }
                                new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (GroupSendCollectionToFriendsUtils.this.sayContent != null && !"".equals(GroupSendCollectionToFriendsUtils.this.sayContent)) {
                                            GroupSendCollectionToFriendsUtils.this.sleep(10);
                                            AutoService autoService = GroupSendCollectionToFriendsUtils.this.autoService;
                                            String str = BaseServiceUtils.toast_edit_id;
                                            PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceUtils.remarkName + "" + GroupSendCollectionToFriendsUtils.this.sayContent);
                                        }
                                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                            findViewIdList.get(0).performAction(16);
                                            if (GroupSendCollectionToFriendsUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                                GroupSendCollectionToFriendsUtils.access$008(GroupSendCollectionToFriendsUtils.this);
                                                if (GroupSendCollectionToFriendsUtils.this.startInt == 1) {
                                                    int unused = GroupSendCollectionToFriendsUtils.this.saveCount = GroupSendCollectionToFriendsUtils.this.excFriendsNum + 1;
                                                } else {
                                                    int unused2 = GroupSendCollectionToFriendsUtils.this.saveCount = GroupSendCollectionToFriendsUtils.this.excFriendsNum + GroupSendCollectionToFriendsUtils.this.startInt + 1;
                                                }
                                                if (GroupSendCollectionToFriendsUtils.this.sendType == 0) {
                                                    SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_all", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                                } else if (GroupSendCollectionToFriendsUtils.this.sendType == 1) {
                                                    SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_part", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                                } else if (GroupSendCollectionToFriendsUtils.this.sendType == 2) {
                                                    SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_shield", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                                }
                                                MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToFriendsUtils.this.excFriendsNum + " 个好友");
                                            }
                                            new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 1500, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    boolean unused = GroupSendCollectionToFriendsUtils.this.isFirstFav = false;
                                                    GroupSendCollectionToFriendsUtils.this.initChattingBackUI();
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
                        new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (GroupSendCollectionToFriendsUtils.this.sayContent != null && !"".equals(GroupSendCollectionToFriendsUtils.this.sayContent)) {
                                    GroupSendCollectionToFriendsUtils.this.sleep(10);
                                    AutoService autoService = GroupSendCollectionToFriendsUtils.this.autoService;
                                    String str = BaseServiceUtils.toast_edit_id;
                                    PerformClickUtils.findViewByIdAndPasteContent(autoService, str, BaseServiceUtils.remarkName + "" + GroupSendCollectionToFriendsUtils.this.sayContent);
                                }
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                    findViewIdList.get(0).performAction(16);
                                    if (GroupSendCollectionToFriendsUtils.this.circulateInnerSize == 1 && MyApplication.enforceable) {
                                        GroupSendCollectionToFriendsUtils.access$008(GroupSendCollectionToFriendsUtils.this);
                                        if (GroupSendCollectionToFriendsUtils.this.startInt == 1) {
                                            int unused = GroupSendCollectionToFriendsUtils.this.saveCount = GroupSendCollectionToFriendsUtils.this.excFriendsNum + 1;
                                        } else {
                                            int unused2 = GroupSendCollectionToFriendsUtils.this.saveCount = GroupSendCollectionToFriendsUtils.this.excFriendsNum + GroupSendCollectionToFriendsUtils.this.startInt + 1;
                                        }
                                        if (GroupSendCollectionToFriendsUtils.this.sendType == 0) {
                                            SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_all", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                        } else if (GroupSendCollectionToFriendsUtils.this.sendType == 1) {
                                            SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_part", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                        } else if (GroupSendCollectionToFriendsUtils.this.sendType == 2) {
                                            SPUtils.put(MyApplication.getConText(), "collection_to_friends_num_shield", Integer.valueOf(GroupSendCollectionToFriendsUtils.this.saveCount));
                                        }
                                        MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "已发送 " + GroupSendCollectionToFriendsUtils.this.excFriendsNum + " 个好友");
                                    }
                                    new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 1500, 100, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            boolean unused = GroupSendCollectionToFriendsUtils.this.isFirstFav = false;
                                            GroupSendCollectionToFriendsUtils.this.initChattingBackUI();
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

    public void initFirstContactInfoUI() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友群发收藏\n已发送 " + this.excFriendsNum + " 个好友");
            LogUtils.log("WS_BABY.ContactInfoUI.1");
            new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    GroupSendCollectionToFriendsUtils.this.getRemarkFriendInfo(GroupSendCollectionToFriendsUtils.this.model.getIntimateMode(), GroupSendCollectionToFriendsUtils.this.recordNowName);
                    PerformClickUtils.findViewIdAndClick2(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                    PerformClickUtils.findTextAndClick(GroupSendCollectionToFriendsUtils.this.autoService, "发消息");
                    LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                    new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 15, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            GroupSendCollectionToFriendsUtils.this.initChattingFirstUI(0);
                        }

                        public void nuFind() {
                            PerformClickUtils.findViewIdAndClick2(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.msg_layout);
                            PerformClickUtils.findTextAndClick(GroupSendCollectionToFriendsUtils.this.autoService, "发消息");
                            new PerformClickUtils2().checkNodeId(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    GroupSendCollectionToFriendsUtils.this.getRemarkFriendInfo(GroupSendCollectionToFriendsUtils.this.model.getIntimateMode(), GroupSendCollectionToFriendsUtils.this.recordNowName);
                                    GroupSendCollectionToFriendsUtils.this.initChattingFirstUI(0);
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                                    PerformClickUtils.executedBackHome(GroupSendCollectionToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            GroupSendCollectionToFriendsUtils.this.executeAllMain(GroupSendCollectionToFriendsUtils.this.sendType);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                    PerformClickUtils.executedBackHome(GroupSendCollectionToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            GroupSendCollectionToFriendsUtils.this.executeAllMain(GroupSendCollectionToFriendsUtils.this.sendType);
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputText(String str) {
        boolean z = true;
        try {
            AccessibilityNodeInfo findFocus = this.autoService.getRootInActiveWindow().findFocus(1);
            if (findFocus != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (findFocus == null) {
                        z = false;
                    }
                    if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str + findFocus.getText() + "");
                        findFocus.performAction(2097152, bundle);
                        return;
                    }
                    return;
                }
                CharSequence text = findFocus.getText();
                ((ClipboardManager) this.autoService.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str + (text == null ? "" : text)));
                if (Build.VERSION.SDK_INT >= 18) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str.length());
                    findFocus.performAction(1);
                    findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                    findFocus.performAction(32768);
                }
            }
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
                        if (GroupSendCollectionToFriendsUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(GroupSendCollectionToFriendsUtils.this.mMyManager);
                            return;
                        }
                        boolean unused = GroupSendCollectionToFriendsUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(GroupSendCollectionToFriendsUtils.this.autoService, "位联系人");
                        int unused2 = GroupSendCollectionToFriendsUtils.this.startIndex = 1;
                        GroupSendCollectionToFriendsUtils.this.jumpStartPosition();
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
        setMiddleText(myWindowManager, "给好友发送收藏", "共发送给了 " + this.excFriendsNum + " 个好友 ");
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, 200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String unused = GroupSendCollectionToFriendsUtils.this.nowSearchName = "";
                    MyWindowManager myWindowManager = GroupSendCollectionToFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "正在发送第 " + (GroupSendCollectionToFriendsUtils.this.excFriendsNum + 1) + " 个好友");
                    if (GroupSendCollectionToFriendsUtils.this.friendsList != null && GroupSendCollectionToFriendsUtils.this.friendsList.size() > 0 && MyApplication.enforceable) {
                        String str = (String) GroupSendCollectionToFriendsUtils.this.friendsList.iterator().next();
                        String unused2 = GroupSendCollectionToFriendsUtils.this.nowSearchName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.search_id);
                        GroupSendCollectionToFriendsUtils.this.sleep(350);
                        PerformClickUtils.inputText(GroupSendCollectionToFriendsUtils.this.autoService, str);
                        GroupSendCollectionToFriendsUtils.this.friendsList.remove(GroupSendCollectionToFriendsUtils.this.friendsList.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(GroupSendCollectionToFriendsUtils.this.autoService, "联系人", "最常使用", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 12, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_searchNickName.1");
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) GroupSendCollectionToFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                            if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(GroupSendCollectionToFriendsUtils.this.autoService, "联系人");
                                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(GroupSendCollectionToFriendsUtils.this.autoService, "最常使用");
                                if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.initChattingUI");
                                    PerformClickUtils.performClick(findViewIdList.get(0));
                                    if (GroupSendCollectionToFriendsUtils.this.model.getSendCardType() == 1 && GroupSendCollectionToFriendsUtils.this.model.getIntimateMode() == 0) {
                                        BaseServiceUtils.remarkName = GroupSendCollectionToFriendsUtils.this.nowSearchName;
                                    }
                                    GroupSendCollectionToFriendsUtils.this.initChattingFirstUI(100);
                                }
                            }
                        }

                        public void nuFind() {
                            PerformClickUtils.executedBackHome(GroupSendCollectionToFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    GroupSendCollectionToFriendsUtils.this.executePartMain();
                                }
                            });
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
