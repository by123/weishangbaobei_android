package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.ApiWrapper;
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

public class CleanZombieFanUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanZombieFanUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int beforeReSendNum = 0;
    /* access modifiers changed from: private */
    public int continuityNilFriendCount = 0;
    /* access modifiers changed from: private */
    public int excDelete = 0;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public int executeRate = 100;
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
    public int nowRate = 0;
    private OperationParameterModel operationParameterModel;
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

    private CleanZombieFanUtils() {
    }

    static /* synthetic */ int access$1208(CleanZombieFanUtils cleanZombieFanUtils) {
        int i = cleanZombieFanUtils.noFriendsNum;
        cleanZombieFanUtils.noFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(CleanZombieFanUtils cleanZombieFanUtils) {
        int i = cleanZombieFanUtils.excFriendsNum;
        cleanZombieFanUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(CleanZombieFanUtils cleanZombieFanUtils) {
        int i = cleanZombieFanUtils.excDelete;
        cleanZombieFanUtils.excDelete = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(CleanZombieFanUtils cleanZombieFanUtils) {
        int i = cleanZombieFanUtils.nowRate;
        cleanZombieFanUtils.nowRate = i + 1;
        return i;
    }

    public static CleanZombieFanUtils getInstance() {
        instance = new CleanZombieFanUtils();
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
                                MyWindowManager myWindowManager = CleanZombieFanUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanUtils.this.noFriendsNum + " 人");
                                PerformClickUtils.performClick(accessibilityNodeInfo2);
                                PerformClickUtils.performClick(accessibilityNodeInfo);
                                CleanZombieFanUtils.this.initFirstContactInfoMSGUI();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = CleanZombieFanUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + CleanZombieFanUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanUtils.this.excFriendsNum + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }
                        });
                    }
                }
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = CleanZombieFanUtils.this.startIndex = 1;
                        if (CleanZombieFanUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, "位联系人");
                        CleanZombieFanUtils.this.checkZombieFan(linkedHashSet);
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
                        CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
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
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 8) + 400);
            }
            new PerformClickUtils2().checkNodeIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeed + 350 + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CleanZombieFanUtils.this.startInt <= 1 || !CleanZombieFanUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        CleanZombieFanUtils.this.checkZombieFan(linkedHashSet);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + CleanZombieFanUtils.this.startInt + "开始");
                    CleanZombieFanUtils.this.initCheck();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCheck() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        if (this.itemList0 == null) {
            this.itemList0 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
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
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = CleanZombieFanUtils.this.startIndex = 1;
                        if (CleanZombieFanUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, "位联系人");
                        CleanZombieFanUtils.this.initCheck();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void initCheckUrl() {
        if (this.executeRate - this.nowRate == 1) {
            ApiWrapper.getInstance().getVersion(new ApiWrapper.CallbackListener<ConmdBean<AppVersionModel>>() {
                public void onFailure(FailureModel failureModel) {
                }

                public void onFinish(ConmdBean<AppVersionModel> conmdBean) {
                    try {
                        try {
                            String shareUrl = conmdBean.getData().getShareUrl();
                            if (shareUrl != null) {
                                CleanZombieFanUtils cleanZombieFanUtils = CleanZombieFanUtils.this;
                                String unused = cleanZombieFanUtils.sayContent = "" + shareUrl;
                            }
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeed + 350 + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(16);
                        CleanZombieFanUtils.this.sleep(CleanZombieFanUtils.this.residenceTime);
                        boolean z = !CleanZombieFanUtils.this.nowName.startsWith("A000非好友_");
                        if (z) {
                            CleanZombieFanUtils.this.inputText("A000非好友_");
                        }
                        CleanZombieFanUtils.this.sleep(CleanZombieFanUtils.this.residenceTime);
                        if (CleanZombieFanUtils.this.remarkLabel == null || "".equals(CleanZombieFanUtils.this.remarkLabel)) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.complete_id);
                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            }
                            PerformClickUtils.executedBackHome(CleanZombieFanUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                                }
                            });
                        } else if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, CleanZombieFanUtils.this.remarkLabel)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                            new PerformClickUtils2().checkNodeIds(CleanZombieFanUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(CleanZombieFanUtils.this.autoService, CleanZombieFanUtils.this.remarkLabel, BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, CleanZombieFanUtils.this.remarkLabel);
                                            CleanZombieFanUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(CleanZombieFanUtils.this.autoService, "完成", BaseServiceUtils.executeSpeed + 350 + BaseServiceUtils.executeSpeedSetting, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "完成");
                                                    PerformClickUtils.executedBackHome(CleanZombieFanUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
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
                                PerformClickUtils.executedBackHome(CleanZombieFanUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                                    }
                                });
                                return;
                            }
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                            PerformClickUtils.executedBackHome(CleanZombieFanUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
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
        this.executeRate = 100;
        this.nowRate = 0;
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
    }

    public void initExMore() {
        LogUtils.log("WS_BABY_ContactInfoUI.initExMore.1");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeedSetting + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.2");
                CleanZombieFanUtils.this.sleep(CleanZombieFanUtils.this.residenceTime);
                if (CleanZombieFanUtils.this.isCanDelete && CleanZombieFanUtils.this.excFriendsNum <= 1000 && MyApplication.enforceable) {
                    CleanZombieFanUtils.this.saveFriendInfo(CleanZombieFanUtils.this.nowName);
                }
                PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                new PerformClickUtils2().checkString(CleanZombieFanUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (!CleanZombieFanUtils.this.isCanDelete || CleanZombieFanUtils.this.excFriendsNum > 1000 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanUtils.this.autoService, "设置备注和标签", "设置备注及标签", (BaseServiceUtils.executeSpeedSetting / 8) + 100, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                                    if (i == 0) {
                                        List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(CleanZombieFanUtils.this.autoService, "设置备注和标签");
                                        if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                            PerformClickUtils.performClick(findViewStringList.get(0));
                                            CleanZombieFanUtils.this.initContactRemarkInfoModUI();
                                            return;
                                        }
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(CleanZombieFanUtils.this.autoService, "设置备注及标签");
                                    if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                                        PerformClickUtils.performClick(findViewStringList2.get(0));
                                        CleanZombieFanUtils.this.initContactRemarkInfoModUI();
                                    }
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactInfoUI.6.允许删除");
                        if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                            PerformClickUtils.scroll(CleanZombieFanUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                            CleanZombieFanUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "删除");
                        } else {
                            PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "删除");
                        }
                        new PerformClickUtils2().check(CleanZombieFanUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 50, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ContactInfoUI.9.确认删除");
                                PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                CleanZombieFanUtils.access$408(CleanZombieFanUtils.this);
                                CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
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

    public void initFirstChattingUI() {
        LogUtils.log("WS_BABY_ChattingUI.000");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                String str;
                LogUtils.log("WS_BABY_ChattingUI.2");
                if (PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, "按住 说话")) {
                    LogUtils.log("WS_BABY_ChattingUI.3");
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    CleanZombieFanUtils.this.sleep(100);
                }
                int unused = CleanZombieFanUtils.this.beforeReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanUtils.this.autoService, "重发");
                try {
                    double random = Math.random();
                    double length = (double) MyApplication.checkStr.length;
                    Double.isNaN(length);
                    int i2 = (int) (random * length);
                    str = MyApplication.checkStr[i2] + CleanZombieFanUtils.this.sayContent;
                } catch (Exception e) {
                    str = MyApplication.checkStr[0] + CleanZombieFanUtils.this.sayContent;
                }
                PerformClickUtils.findViewByIdAndPasteContent(CleanZombieFanUtils.this.autoService, BaseServiceUtils.input_edit_text_id, str);
                LogUtils.log("WS_BABY_ChattingUI.4");
                new PerformClickUtils2().checkString(CleanZombieFanUtils.this.autoService, "发送", 50, 100, 25, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "发送");
                        LogUtils.log("WS_BABY_ChattingUI.5.发送");
                        CleanZombieFanUtils.access$208(CleanZombieFanUtils.this);
                        CleanZombieFanUtils.access$908(CleanZombieFanUtils.this);
                        if (CleanZombieFanUtils.this.nowRate == CleanZombieFanUtils.this.executeRate) {
                            int unused = CleanZombieFanUtils.this.nowRate = 0;
                        }
                        CleanZombieFanUtils.this.initCheckUrl();
                        SPUtils.put(MyApplication.getConText(), "zombie_num", Integer.valueOf(CleanZombieFanUtils.this.startInt == 1 ? (CleanZombieFanUtils.this.excFriendsNum + 1) - CleanZombieFanUtils.this.excDelete : (CleanZombieFanUtils.this.excFriendsNum + CleanZombieFanUtils.this.startInt) - CleanZombieFanUtils.this.excDelete));
                        Context conText = MyApplication.getConText();
                        SPUtils.put(conText, "zombie_text", "上次共执行 " + CleanZombieFanUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanUtils.this.noFriendsNum + " 人");
                        new PerformClickUtils2().checkNodeIdNilExist(CleanZombieFanUtils.this.autoService, BaseServiceUtils.chatting_progress_id, SocializeConstants.CANCLE_RESULTCODE, 100, 600, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ChattingUI.2.发送1");
                                PerformClickUtils.performBack(CleanZombieFanUtils.this.autoService);
                                CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                            }

                            public void nuFind() {
                                MyWindowManager myWindowManager;
                                StringBuilder sb;
                                String str;
                                LogUtils.log("WS_BABY_ChattingUI.2.发送2");
                                try {
                                    int unused = CleanZombieFanUtils.this.lastReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanUtils.this.autoService, "重发");
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_beforeReSendNum" + CleanZombieFanUtils.this.beforeReSendNum);
                                    PrintStream printStream2 = System.out;
                                    printStream2.println("WS_BABY_lastReSendNum" + CleanZombieFanUtils.this.lastReSendNum);
                                    if (CleanZombieFanUtils.this.lastReSendNum > CleanZombieFanUtils.this.beforeReSendNum) {
                                        int unused2 = CleanZombieFanUtils.this.continuityNilFriendCount = CleanZombieFanUtils.this.continuityNilFriendCount + 1;
                                        CleanZombieFanUtils.access$1208(CleanZombieFanUtils.this);
                                        if (CleanZombieFanUtils.this.excFriendsNum < 300 || CleanZombieFanUtils.this.continuityNilFriendCount < 3) {
                                            try {
                                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.contact_title_id);
                                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.6.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList2.get(findViewIdList2.size() - 1));
                                                        CleanZombieFanUtils.this.initFirstSingleChatInfoUI();
                                                    }
                                                } else {
                                                    try {
                                                        str = findViewIdList.get(0).getText().toString();
                                                    } catch (Exception e) {
                                                        str = "";
                                                    }
                                                    if (str != null) {
                                                        if (str.startsWith("A000非好友_") && MyApplication.enforceable) {
                                                            if (CleanZombieFanUtils.this.isCanDelete) {
                                                                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList3 != null && findViewIdList3.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.4.右上更多");
                                                                    PerformClickUtils.performClick(findViewIdList3.get(findViewIdList3.size() - 1));
                                                                    CleanZombieFanUtils.this.initFirstSingleChatInfoUI();
                                                                }
                                                            } else if (CleanZombieFanUtils.this.remarkLabel == null || "".equals(CleanZombieFanUtils.this.remarkLabel)) {
                                                                LogUtils.log("WS_BABY_ChattingUI.3.不可删除");
                                                                PerformClickUtils.performBack(CleanZombieFanUtils.this.autoService);
                                                                CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                                                            } else {
                                                                List<AccessibilityNodeInfo> findViewIdList4 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList4 != null && findViewIdList4.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.5.右上更多");
                                                                    PerformClickUtils.performClick(findViewIdList4.get(findViewIdList4.size() - 1));
                                                                    CleanZombieFanUtils.this.initFirstSingleChatInfoUI();
                                                                }
                                                            }
                                                        }
                                                    }
                                                    List<AccessibilityNodeInfo> findViewIdList5 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList5 != null && findViewIdList5.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.5.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList5.get(findViewIdList5.size() - 1));
                                                        CleanZombieFanUtils.this.initFirstSingleChatInfoUI();
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                List<AccessibilityNodeInfo> findViewIdList6 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                if (findViewIdList6 != null && findViewIdList6.size() > 0 && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_ChattingUI.7.右上更多");
                                                    PerformClickUtils.performClick(findViewIdList6.get(findViewIdList6.size() - 1));
                                                    CleanZombieFanUtils.this.initFirstSingleChatInfoUI();
                                                }
                                            }
                                        } else {
                                            String unused3 = CleanZombieFanUtils.this.sendResult = "由于您连续检测了300个以上的好友，由于微信限制规则，检测到了连续三条非好友，为了防止检测异常（检测错误），希望您隔3小时后，继续检测。";
                                            BaseServiceUtils.removeEndView(CleanZombieFanUtils.this.mMyManager);
                                        }
                                    } else {
                                        int unused4 = CleanZombieFanUtils.this.continuityNilFriendCount = 0;
                                        LogUtils.log("WS_BABY_ChattingUI.8.BACK");
                                        PerformClickUtils.performBack(CleanZombieFanUtils.this.autoService);
                                        CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                                    }
                                    myWindowManager = CleanZombieFanUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Exception e3) {
                                    LogUtils.log("WS_BABY_ChattingUI.9.BACK");
                                    PerformClickUtils.performBack(CleanZombieFanUtils.this.autoService);
                                    CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                                    myWindowManager = CleanZombieFanUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Throwable th) {
                                    MyWindowManager myWindowManager2 = CleanZombieFanUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager2, "从第 " + CleanZombieFanUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanUtils.this.noFriendsNum + " 人");
                                    throw th;
                                }
                                sb.append("从第 ");
                                sb.append(CleanZombieFanUtils.this.startInt);
                                sb.append(" 个好友，开始检测僵尸粉\n已执行 ");
                                sb.append(CleanZombieFanUtils.this.excFriendsNum);
                                sb.append(" 人 ,非好友 ");
                                sb.append(CleanZombieFanUtils.this.noFriendsNum);
                                sb.append(" 人");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                            }
                        });
                    }

                    public void nuFind() {
                        if (PerformClickUtils.findNodeIsExistByText(CleanZombieFanUtils.this.autoService, "按住 说话")) {
                            PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.voice_left_id);
                            CleanZombieFanUtils.this.initFirstChattingUI();
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
                PerformClickUtils.findViewIdAndClick2(CleanZombieFanUtils.this.autoService, BaseServiceUtils.msg_layout);
                PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(CleanZombieFanUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        CleanZombieFanUtils.this.initFirstChattingUI();
                    }

                    public void nuFind() {
                        PerformClickUtils.findViewIdAndClick2(CleanZombieFanUtils.this.autoService, BaseServiceUtils.msg_layout);
                        PerformClickUtils.findTextAndClick(CleanZombieFanUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(CleanZombieFanUtils.this.autoService, BaseServiceUtils.voice_left_id, 10, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                CleanZombieFanUtils.this.initFirstChattingUI();
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                                PerformClickUtils.performBackNoDeep(CleanZombieFanUtils.this.autoService);
                                CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.performBackNoDeep(CleanZombieFanUtils.this.autoService);
                CleanZombieFanUtils.this.executeMain(CleanZombieFanUtils.this.nameLists);
            }
        });
    }

    public void initFirstSingleChatInfoUI() {
        LogUtils.log("WS_BABY_SingleChatInfoUI.2");
        new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeed + 350 + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CleanZombieFanUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                LogUtils.log("WS_BABY_SingleChatInfoUI.3");
                CleanZombieFanUtils.this.initExMore();
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
}
