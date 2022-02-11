package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
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

public class CleanZombieFanCustomUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanZombieFanCustomUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int beforeReSendNum = 0;
    /* access modifiers changed from: private */
    public int continuityNilFriendCount = 0;
    private int excDelete = 0;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public boolean isCanDelete;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    private boolean isSearchResult = true;
    List<AccessibilityNodeInfo> itemList = null;
    List<AccessibilityNodeInfo> itemList0 = null;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public int lastReSendNum = 0;
    private int maxNum = 300;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> nameLists = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int noFriendsNum = 0;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel operationParameterModel;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public int residenceTime = 0;
    /* access modifiers changed from: private */
    public String sayContent;
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private CleanZombieFanCustomUtils() {
    }

    static /* synthetic */ int access$1208(CleanZombieFanCustomUtils cleanZombieFanCustomUtils) {
        int i = cleanZombieFanCustomUtils.noFriendsNum;
        cleanZombieFanCustomUtils.noFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(CleanZombieFanCustomUtils cleanZombieFanCustomUtils) {
        int i = cleanZombieFanCustomUtils.excFriendsNum;
        cleanZombieFanCustomUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(CleanZombieFanCustomUtils cleanZombieFanCustomUtils) {
        int i = cleanZombieFanCustomUtils.excDelete;
        cleanZombieFanCustomUtils.excDelete = i + 1;
        return i;
    }

    public static CleanZombieFanCustomUtils getInstance() {
        instance = new CleanZombieFanCustomUtils();
        return instance;
    }

    public void checkZombieFan(final LinkedHashSet<String> linkedHashSet) {
        if (this.excFriendsNum >= this.maxNum) {
            removeEndView(this.mMyManager);
            return;
        }
        this.isJumpedStart = false;
        if (this.itemList == null) {
            this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.itemList != null && this.itemList.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_LauncherUI.startIndex" + this.startIndex + "@" + this.itemList.size());
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                final AccessibilityNodeInfo accessibilityNodeInfo = this.itemList.get(this.startIndex);
                if (accessibilityNodeInfo != null) {
                    if (accessibilityNodeInfo.getText() != null) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
                    }
                    if (this.beforeLists != null && this.beforeLists.size() > 30) {
                        for (int i = 0; i < 5; i++) {
                            this.beforeLists.remove(0);
                        }
                    }
                    if (linkedHashSet != null && linkedHashSet.size() > 0 && linkedHashSet.contains(this.nowName)) {
                        this.startIndex++;
                        this.excFriendsNum++;
                        updateBottomText(this.mMyManager, "已跳过【 " + this.nowName + " 】");
                        sleep(200);
                        checkZombieFan(linkedHashSet);
                    } else if (this.isScrollBottom && this.beforeLists.contains(this.nowName)) {
                        this.startIndex++;
                        LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + this.nowName);
                        checkZombieFan(linkedHashSet);
                    } else if ("微信团队".equals(this.nowName) || "文件传输助手".equals(this.nowName)) {
                        this.excFriendsNum++;
                        this.startIndex++;
                        LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                        checkZombieFan(linkedHashSet);
                    } else {
                        LogUtils.log("WS_BABY_LauncherUI.check");
                        this.beforeLists.add(this.nowName);
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_layout_id);
                        if (findViewIdList == null || this.startIndex >= findViewIdList.size()) {
                            this.excFriendsNum++;
                            this.startIndex++;
                            LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                            checkZombieFan(linkedHashSet);
                            return;
                        }
                        final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(this.startIndex);
                        this.startIndex++;
                        if (this.spaceTime <= 0 || this.excFriendsNum <= 0) {
                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            initFirstContactInfoMSGUI();
                            return;
                        }
                        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void end() {
                                MyWindowManager myWindowManager = CleanZombieFanCustomUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanCustomUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCustomUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanCustomUtils.this.noFriendsNum + " 人");
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                CleanZombieFanCustomUtils.this.initFirstContactInfoMSGUI();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = CleanZombieFanCustomUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanCustomUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCustomUtils.this.excFriendsNum + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }
                        });
                    }
                }
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = CleanZombieFanCustomUtils.this.startIndex = 1;
                        if (CleanZombieFanCustomUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanCustomUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanCustomUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "位联系人");
                        CleanZombieFanCustomUtils.this.checkZombieFan(linkedHashSet);
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void endViewDismiss() {
        try {
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showMiddleView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                        } else {
                            CleanZombieFanCustomUtils.this.executePartMain();
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain(final LinkedHashSet<String> linkedHashSet) {
        try {
            this.isSearchResult = true;
            LogUtils.log("WS_BABY_LauncherUI");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 8) + 400);
            }
            new PerformClickUtils2().checkNodeIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeed + 350 + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CleanZombieFanCustomUtils.this.startInt <= 1 || !CleanZombieFanCustomUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        CleanZombieFanCustomUtils.this.checkZombieFan(linkedHashSet);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + CleanZombieFanCustomUtils.this.startInt + "开始");
                    CleanZombieFanCustomUtils.this.initCheck();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePartMain() {
        try {
            if (this.nameLists == null || this.nameLists.size() == 0) {
                removeEndView(this.mMyManager);
                return;
            }
            LogUtils.log("WS_BABY.LauncherUI.executePartMain");
            if (MyApplication.enforceable) {
                if (this.isFirst && MyApplication.enforceable) {
                    this.isFirst = false;
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                    sleep(200);
                }
                new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_nav_search_viewgroup_id, contact_nav_search_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanCustomUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(CleanZombieFanCustomUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            CleanZombieFanCustomUtils.this.searchNickName();
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

    public void initCheck() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
        if (rootInActiveWindow != null && MyApplication.enforceable) {
            if (this.itemList0 == null) {
                this.itemList0 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_item_name_id);
            }
            if (this.itemList0 != null && this.itemList0.size() > 0 && MyApplication.enforceable) {
                if (this.startIndex < this.itemList0.size() && MyApplication.enforceable) {
                    AccessibilityNodeInfo accessibilityNodeInfo = this.itemList0.get(this.startIndex);
                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
                    }
                    if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                        this.scrollCount++;
                    }
                    LogUtils.log("WS_BABY_JUMP_NUM@" + this.scrollCount + "@" + this.startInt);
                    if (this.scrollCount == this.startInt) {
                        checkZombieFan(this.nameLists);
                        return;
                    }
                    this.jumpStartLists.add(this.nowName);
                    this.startIndex++;
                    updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n已跳过 " + this.scrollCount + " 个好友，请不要操作微信");
                    initCheck();
                } else if (this.startIndex >= this.itemList0.size() && MyApplication.enforceable) {
                    this.itemList0 = null;
                    PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                    new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            int unused = CleanZombieFanCustomUtils.this.startIndex = 1;
                            if (CleanZombieFanCustomUtils.this.isScrollBottom) {
                                LogUtils.log("WS_BABY_LauncherUI.END");
                                BaseServiceUtils.removeEndView(CleanZombieFanCustomUtils.this.mMyManager);
                                return;
                            }
                            boolean unused2 = CleanZombieFanCustomUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "位联系人");
                            CleanZombieFanCustomUtils.this.initCheck();
                        }

                        public void nuFind() {
                        }
                    });
                }
            }
        }
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanCustomUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(16);
                        CleanZombieFanCustomUtils.this.sleep(CleanZombieFanCustomUtils.this.residenceTime);
                        boolean z = !CleanZombieFanCustomUtils.this.nowName.startsWith("A000非好友_");
                        if (z) {
                            CleanZombieFanCustomUtils.this.inputText("A000非好友_");
                        }
                        CleanZombieFanCustomUtils.this.sleep(CleanZombieFanCustomUtils.this.residenceTime);
                        if (CleanZombieFanCustomUtils.this.remarkLabel == null || "".equals(CleanZombieFanCustomUtils.this.remarkLabel)) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            }
                            PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCustomUtils.this.executePartMain();
                                    }
                                }
                            });
                        } else if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, CleanZombieFanCustomUtils.this.remarkLabel)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                            new PerformClickUtils2().checkNodeIds(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(CleanZombieFanCustomUtils.this.autoService, CleanZombieFanCustomUtils.this.remarkLabel, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, CleanZombieFanCustomUtils.this.remarkLabel);
                                            CleanZombieFanCustomUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(CleanZombieFanCustomUtils.this.autoService, "完成", BaseServiceUtils.executeSpeedSetting + 650, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "完成");
                                                    PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                                CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                                            } else {
                                                                CleanZombieFanCustomUtils.this.executePartMain();
                                                            }
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
                        } else {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.3");
                            if (z) {
                                LogUtils.log("WS_BABY_ContactRemarkInfoModUI.4");
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                                if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0 && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(0));
                                }
                                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCustomUtils.this.executePartMain();
                                        }
                                    }
                                });
                                return;
                            }
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                            PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCustomUtils.this.executePartMain();
                                    }
                                }
                            });
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

    public void initData(OperationParameterModel operationParameterModel2) {
        this.excFriendsNum = 0;
        this.excDelete = 0;
        this.noFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.startIndex = 0;
        this.residenceTime = 100;
        this.continuityNilFriendCount = 0;
        this.sendResult = "";
        this.nowName = "";
        this.isJumpedStart = true;
        this.isScrollBottom = false;
        this.isFirst = true;
        this.scrollCount = 0;
        this.spaceTime = operationParameterModel2.getSpaceTime();
        this.isSearchResult = true;
        this.operationParameterModel = operationParameterModel2;
        this.remarkLabel = operationParameterModel2.getSingLabelStr();
        this.isCanDelete = operationParameterModel2.isDeleteNoFriends();
        this.sayContent = operationParameterModel2.getSayContent();
        this.startInt = operationParameterModel2.getStartIndex();
        this.nameLists = operationParameterModel2.getTagListPeoples();
        this.maxNum = operationParameterModel2.getMaxOperaNum();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        if (operationParameterModel2.getSendCardType() == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.startInt > 1);
                LogUtils.log(sb.toString());
                if (this.startInt > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.startInt - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.nameLists.size() > 0) {
                            this.nameLists.remove(this.nameLists.iterator().next());
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

    public void initExMore() {
        LogUtils.log("WS_BABY_ContactInfoUI.initExMore.1");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.2");
                CleanZombieFanCustomUtils.this.sleep(CleanZombieFanCustomUtils.this.residenceTime);
                if (CleanZombieFanCustomUtils.this.isCanDelete && CleanZombieFanCustomUtils.this.excFriendsNum <= 1000 && MyApplication.enforceable) {
                    CleanZombieFanCustomUtils.this.saveFriendInfo(CleanZombieFanCustomUtils.this.nowName);
                }
                PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                new PerformClickUtils2().checkString(CleanZombieFanCustomUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (!CleanZombieFanCustomUtils.this.isCanDelete || CleanZombieFanCustomUtils.this.excFriendsNum > 1000 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCustomUtils.this.autoService, "设置备注和标签", "设置备注及标签", (BaseServiceUtils.executeSpeedSetting / 8) + 100, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                                    if (i == 0) {
                                        List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(CleanZombieFanCustomUtils.this.autoService, "设置备注和标签");
                                        if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                            PerformClickUtils.performClick(findViewStringList.get(0));
                                            CleanZombieFanCustomUtils.this.initContactRemarkInfoModUI();
                                            return;
                                        }
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(CleanZombieFanCustomUtils.this.autoService, "设置备注及标签");
                                    if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                                        PerformClickUtils.performClick(findViewStringList2.get(0));
                                        CleanZombieFanCustomUtils.this.initContactRemarkInfoModUI();
                                    }
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactInfoUI.6.允许删除");
                        if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                            PerformClickUtils.scroll(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                            CleanZombieFanCustomUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "删除");
                        } else {
                            PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "删除");
                        }
                        new PerformClickUtils2().check(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ContactInfoUI.9.确认删除");
                                PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                CleanZombieFanCustomUtils.access$408(CleanZombieFanCustomUtils.this);
                                if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                } else {
                                    CleanZombieFanCustomUtils.this.executePartMain();
                                }
                            }

                            public void nuFind() {
                                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        CleanZombieFanCustomUtils.access$408(CleanZombieFanCustomUtils.this);
                                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCustomUtils.this.executePartMain();
                                        }
                                    }
                                });
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

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY_ChattingUI.000");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ChattingUI.2");
                if (PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "按住 说话")) {
                    LogUtils.log("WS_BABY_ChattingUI.3");
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    CleanZombieFanCustomUtils.this.sleep(100);
                }
                int unused = CleanZombieFanCustomUtils.this.beforeReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanCustomUtils.this.autoService, "重发");
                PerformClickUtils.findViewByIdAndPasteContent(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.input_edit_text_id, CleanZombieFanCustomUtils.this.sayContent);
                LogUtils.log("WS_BABY_ChattingUI.4");
                new PerformClickUtils2().checkString(CleanZombieFanCustomUtils.this.autoService, "发送", 50, 100, 25, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "发送");
                        LogUtils.log("WS_BABY_ChattingUI.5.发送");
                        CleanZombieFanCustomUtils.access$208(CleanZombieFanCustomUtils.this);
                        CleanZombieFanCustomUtils.this.saveIndex();
                        new PerformClickUtils2().checkNodeIdNilExist(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.chatting_progress_id, 600, 100, 600, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ChattingUI.2.发送1");
                                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCustomUtils.this.executePartMain();
                                        }
                                    }
                                });
                            }

                            public void nuFind() {
                                MyWindowManager myWindowManager;
                                StringBuilder sb;
                                String str;
                                LogUtils.log("WS_BABY_ChattingUI.2.发送2");
                                try {
                                    int unused = CleanZombieFanCustomUtils.this.lastReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanCustomUtils.this.autoService, "重发");
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_beforeReSendNum" + CleanZombieFanCustomUtils.this.beforeReSendNum);
                                    PrintStream printStream2 = System.out;
                                    printStream2.println("WS_BABY_lastReSendNum" + CleanZombieFanCustomUtils.this.lastReSendNum);
                                    if (CleanZombieFanCustomUtils.this.lastReSendNum > CleanZombieFanCustomUtils.this.beforeReSendNum) {
                                        int unused2 = CleanZombieFanCustomUtils.this.continuityNilFriendCount = CleanZombieFanCustomUtils.this.continuityNilFriendCount + 1;
                                        CleanZombieFanCustomUtils.access$1208(CleanZombieFanCustomUtils.this);
                                        if (CleanZombieFanCustomUtils.this.excFriendsNum < 300 || CleanZombieFanCustomUtils.this.continuityNilFriendCount < 3) {
                                            try {
                                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.contact_title_id);
                                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.6.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList2.get(findViewIdList2.size() - 1));
                                                        CleanZombieFanCustomUtils.this.initFirstSingleChatInfoUI();
                                                    }
                                                } else {
                                                    try {
                                                        str = findViewIdList.get(0).getText().toString();
                                                    } catch (Exception e) {
                                                        str = "";
                                                    }
                                                    if (str != null) {
                                                        if (str.startsWith("A000非好友_") && MyApplication.enforceable) {
                                                            if (CleanZombieFanCustomUtils.this.isCanDelete) {
                                                                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList3 != null && findViewIdList3.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.4.右上更多");
                                                                    PerformClickUtils.performClick(findViewIdList3.get(findViewIdList3.size() - 1));
                                                                    CleanZombieFanCustomUtils.this.initFirstSingleChatInfoUI();
                                                                }
                                                            } else if (CleanZombieFanCustomUtils.this.remarkLabel == null || "".equals(CleanZombieFanCustomUtils.this.remarkLabel)) {
                                                                LogUtils.log("WS_BABY_ChattingUI.3.不可删除");
                                                                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                    public void find() {
                                                                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                                                        } else {
                                                                            CleanZombieFanCustomUtils.this.executePartMain();
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                List<AccessibilityNodeInfo> findViewIdList4 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList4 != null && findViewIdList4.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.5.右上更多");
                                                                    PerformClickUtils.performClick(findViewIdList4.get(findViewIdList4.size() - 1));
                                                                    CleanZombieFanCustomUtils.this.initFirstSingleChatInfoUI();
                                                                }
                                                            }
                                                        }
                                                    }
                                                    List<AccessibilityNodeInfo> findViewIdList5 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList5 != null && findViewIdList5.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.5.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList5.get(findViewIdList5.size() - 1));
                                                        CleanZombieFanCustomUtils.this.initFirstSingleChatInfoUI();
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                List<AccessibilityNodeInfo> findViewIdList6 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                if (findViewIdList6 != null && findViewIdList6.size() > 0 && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_ChattingUI.7.右上更多");
                                                    PerformClickUtils.performClick(findViewIdList6.get(findViewIdList6.size() - 1));
                                                    CleanZombieFanCustomUtils.this.initFirstSingleChatInfoUI();
                                                }
                                            }
                                        } else {
                                            String unused3 = CleanZombieFanCustomUtils.this.sendResult = "由于您连续检测了300个以上的好友，由于微信限制规则，检测到了连续三条非好友，为了防止检测异常（检测错误），希望您隔3小时后，继续检测。";
                                            BaseServiceUtils.removeEndView(CleanZombieFanCustomUtils.this.mMyManager);
                                        }
                                    } else {
                                        int unused4 = CleanZombieFanCustomUtils.this.continuityNilFriendCount = 0;
                                        LogUtils.log("WS_BABY_ChattingUI.8.BACK");
                                        PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                    CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                                } else {
                                                    CleanZombieFanCustomUtils.this.executePartMain();
                                                }
                                            }
                                        });
                                    }
                                    myWindowManager = CleanZombieFanCustomUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Exception e3) {
                                    LogUtils.log("WS_BABY_ChattingUI.9.BACK");
                                    PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                            } else {
                                                CleanZombieFanCustomUtils.this.executePartMain();
                                            }
                                        }
                                    });
                                    myWindowManager = CleanZombieFanCustomUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Throwable th) {
                                    MyWindowManager myWindowManager2 = CleanZombieFanCustomUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager2, "从第 " + CleanZombieFanCustomUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCustomUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanCustomUtils.this.noFriendsNum + " 人");
                                    throw th;
                                }
                                sb.append("从第 ");
                                sb.append(CleanZombieFanCustomUtils.this.startInt);
                                sb.append(" 个好友，开始检测僵尸粉\n已执行 ");
                                sb.append(CleanZombieFanCustomUtils.this.excFriendsNum);
                                sb.append(" 人 ,非好友 ");
                                sb.append(CleanZombieFanCustomUtils.this.noFriendsNum);
                                sb.append(" 人");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                            }
                        });
                    }

                    public void nuFind() {
                        if (PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "按住 说话")) {
                            PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.voice_left_id);
                            CleanZombieFanCustomUtils.this.initFirstChattingUI();
                        }
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initFirstContactInfoMSGUI() {
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + 350 + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick2(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.msg_layout);
                PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        CleanZombieFanCustomUtils.this.initFirstChattingUI();
                    }

                    public void nuFind() {
                        PerformClickUtils.findViewIdAndClick2(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.msg_layout);
                        PerformClickUtils.findTextAndClick(CleanZombieFanCustomUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                CleanZombieFanCustomUtils.this.initFirstChattingUI();
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCustomUtils.this.executePartMain();
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
                PerformClickUtils.executedBackHome(CleanZombieFanCustomUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCustomUtils.this.operationParameterModel.getSendCardType() == 2) {
                            CleanZombieFanCustomUtils.this.executeMain(CleanZombieFanCustomUtils.this.nameLists);
                        } else {
                            CleanZombieFanCustomUtils.this.executePartMain();
                        }
                    }
                });
            }
        });
    }

    public void initFirstSingleChatInfoUI() {
        LogUtils.log("WS_BABY_SingleChatInfoUI.2");
        new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeedSetting + 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                LogUtils.log("WS_BABY_SingleChatInfoUI.3");
                CleanZombieFanCustomUtils.this.initExMore();
            }

            public void nuFind() {
            }
        });
    }

    public void inputText(String str) {
        AccessibilityNodeInfo findFocus;
        boolean z = true;
        try {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null && (findFocus = rootInActiveWindow.findFocus(1)) != null) {
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            if (this.sendResult != null && !"".equals(this.sendResult)) {
                setMiddleText(this.mMyManager, "僵尸粉检索结束", this.sendResult);
            } else if (this.isSearchResult) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "僵尸粉检索结束", "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n本次已执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
            } else {
                setMiddleText(this.mMyManager, "僵尸粉检索结束", "没有找到好友，起始点可能超出检测范围！！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveIndex() {
        int i = this.startInt == 1 ? (this.excFriendsNum + 1) - this.excDelete : (this.excFriendsNum + this.startInt) - this.excDelete;
        if (this.operationParameterModel.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_all", Integer.valueOf(i));
            Context conText = MyApplication.getConText();
            SPUtils.put(conText, "check_zombies_friend_num_all_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        } else if (this.operationParameterModel.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_part", Integer.valueOf(i));
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "check_zombies_friend_num_part_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        } else if (this.operationParameterModel.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "check_zombies_friend_num_shield", Integer.valueOf(i));
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "check_zombies_num_shield_text", "上次共执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        }
    }

    public void searchNickName() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, search_id, 200, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CleanZombieFanCustomUtils.this.nameLists != null && CleanZombieFanCustomUtils.this.nameLists.size() > 0 && MyApplication.enforceable) {
                        String str = (String) CleanZombieFanCustomUtils.this.nameLists.iterator().next();
                        String unused = CleanZombieFanCustomUtils.this.nowName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.search_id);
                        CleanZombieFanCustomUtils.this.sleep(350);
                        PerformClickUtils.inputText(CleanZombieFanCustomUtils.this.autoService, str);
                        CleanZombieFanCustomUtils.this.nameLists.remove(CleanZombieFanCustomUtils.this.nameLists.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCustomUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCustomUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCustomUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    CleanZombieFanCustomUtils.access$208(CleanZombieFanCustomUtils.this);
                                    CleanZombieFanCustomUtils.this.saveIndex();
                                    PerformClickUtils.performBack(CleanZombieFanCustomUtils.this.autoService);
                                    LogUtils.log("WS_BABY.search_back1");
                                    CleanZombieFanCustomUtils.this.executePartMain();
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                CleanZombieFanCustomUtils.this.initFirstChattingUI();
                            }
                        }

                        public void nuFind() {
                            CleanZombieFanCustomUtils.access$208(CleanZombieFanCustomUtils.this);
                            CleanZombieFanCustomUtils.this.saveIndex();
                            PerformClickUtils.performBack(CleanZombieFanCustomUtils.this.autoService);
                            LogUtils.log("WS_BABY.search_back1");
                            CleanZombieFanCustomUtils.this.executePartMain();
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
