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

public class CleanZombieFanCircleUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanZombieFanCircleUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int beforeReSendNum = 0;
    /* access modifiers changed from: private */
    public int continuityNilFriendCount = 0;
    /* access modifiers changed from: private */
    public int dndMode = 0;
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    /* access modifiers changed from: private */
    public int friendStatus = 0;
    /* access modifiers changed from: private */
    public boolean isDeleteNoFriend;
    /* access modifiers changed from: private */
    public boolean isDeleteShield;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isFromMsgRemark = false;
    /* access modifiers changed from: private */
    public boolean isHasLabel = false;
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
    private int shieldNum = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private CleanZombieFanCircleUtils() {
    }

    static /* synthetic */ int access$1108(CleanZombieFanCircleUtils cleanZombieFanCircleUtils) {
        int i = cleanZombieFanCircleUtils.noFriendsNum;
        cleanZombieFanCircleUtils.noFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(CleanZombieFanCircleUtils cleanZombieFanCircleUtils) {
        int i = cleanZombieFanCircleUtils.shieldNum;
        cleanZombieFanCircleUtils.shieldNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(CleanZombieFanCircleUtils cleanZombieFanCircleUtils) {
        int i = cleanZombieFanCircleUtils.excFriendsNum;
        cleanZombieFanCircleUtils.excFriendsNum = i + 1;
        return i;
    }

    public static CleanZombieFanCircleUtils getInstance() {
        instance = new CleanZombieFanCircleUtils();
        return instance;
    }

    public void checkFriend() {
        LogUtils.log("WS_BABY_checkFriendCircle");
        if (this.dndMode == 2) {
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人,被屏蔽 " + this.shieldNum + " 人");
        } else if (this.dndMode == 1) {
            MyWindowManager myWindowManager2 = this.mMyManager;
            updateBottomText(myWindowManager2, "从第 " + this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + this.excFriendsNum + " 人 ,非好友 " + this.noFriendsNum + " 人");
        }
        saveIndex();
        new PerformClickUtils2().checkNilString(this.autoService, "正在加载", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_Circle");
                new PerformClickUtils2().checkNodeIdOrName(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.list_circle_layout_id, "朋友仅展示最近", 100, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY_Circle_0");
                            PerformClickUtils.scroll(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.list_circle_id);
                            new PerformClickUtils2().checkNilStringScroll(CleanZombieFanCircleUtils.this.autoService, "正在加载", BaseServiceUtils.list_circle_id, SocializeConstants.CANCLE_RESULTCODE, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCircleUtils.this.autoService, "非对方的朋友只显示最近十条朋友圈", "非朋友最多显示十张照片", 100, 100, 5, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            LogUtils.log("WS_BABY_Circle_1");
                                            CleanZombieFanCircleUtils.access$1108(CleanZombieFanCircleUtils.this);
                                            int unused = CleanZombieFanCircleUtils.this.friendStatus = 1;
                                            PerformClickUtils.performBack(CleanZombieFanCircleUtils.this.autoService);
                                            CleanZombieFanCircleUtils.this.sleep(300);
                                            if (CleanZombieFanCircleUtils.this.isDeleteNoFriend) {
                                                CleanZombieFanCircleUtils.this.initFirstContactInfoDelete(false);
                                            } else {
                                                CleanZombieFanCircleUtils.this.initFirstContactInfoRemark();
                                            }
                                        }

                                        public void nuFind() {
                                            LogUtils.log("WS_BABY_Circle_2");
                                            int unused = CleanZombieFanCircleUtils.this.friendStatus = 0;
                                            PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                    } else {
                                                        CleanZombieFanCircleUtils.this.executePartMain();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_Circle_3");
                                    int unused = CleanZombieFanCircleUtils.this.friendStatus = 0;
                                    PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                            } else {
                                                CleanZombieFanCircleUtils.this.executePartMain();
                                            }
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_Circle_4");
                        int unused = CleanZombieFanCircleUtils.this.friendStatus = 0;
                        PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                } else {
                                    CleanZombieFanCircleUtils.this.executePartMain();
                                }
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_Circle_5_应该是只有一条线");
                        int unused = CleanZombieFanCircleUtils.this.friendStatus = 2;
                        PerformClickUtils.performBack(CleanZombieFanCircleUtils.this.autoService);
                        if (CleanZombieFanCircleUtils.this.dndMode == 1) {
                            CleanZombieFanCircleUtils.this.initFirstContactInfoSendMsgUI();
                            return;
                        }
                        CleanZombieFanCircleUtils.access$1208(CleanZombieFanCircleUtils.this);
                        if (CleanZombieFanCircleUtils.this.isDeleteShield) {
                            CleanZombieFanCircleUtils.this.initFirstContactInfoDelete(true);
                        } else {
                            CleanZombieFanCircleUtils.this.initFirstContactInfoRemark();
                        }
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_Circle_6一直正在加载,不确定");
                int unused = CleanZombieFanCircleUtils.this.friendStatus = 2;
                PerformClickUtils.performBack(CleanZombieFanCircleUtils.this.autoService);
                if (CleanZombieFanCircleUtils.this.dndMode == 1) {
                    CleanZombieFanCircleUtils.this.initFirstContactInfoSendMsgUI();
                } else {
                    PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                            } else {
                                CleanZombieFanCircleUtils.this.executePartMain();
                            }
                        }
                    });
                }
            }
        });
    }

    public void checkZombieFan(final LinkedHashSet<String> linkedHashSet) {
        this.friendStatus = 0;
        this.isJumpedStart = false;
        LogUtils.log("WS_BABY_LauncherUI.start");
        if (this.itemList == null) {
            this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.itemList != null && this.itemList.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_LauncherUI.startIndex" + this.startIndex + "@" + this.itemList.size());
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = this.itemList.get(this.startIndex);
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
                        this.excFriendsNum++;
                        this.startIndex++;
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
                        this.friendStatus = 0;
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_layout_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || findViewIdList.size() <= this.startIndex) {
                            this.excFriendsNum++;
                            this.startIndex++;
                            checkZombieFan(linkedHashSet);
                        } else if (this.startIndex < findViewIdList.size()) {
                            this.startIndex++;
                            PerformClickUtils.performClick(findViewIdList.get(this.startIndex));
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            this.isHasLabel = false;
                            this.isFromMsgRemark = false;
                            initFirstContactInfoCircleUI();
                        } else {
                            this.excFriendsNum++;
                            this.startIndex++;
                            LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                            checkZombieFan(linkedHashSet);
                        }
                    }
                }
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = CleanZombieFanCircleUtils.this.startIndex = 1;
                        if (CleanZombieFanCircleUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanCircleUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanCircleUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "位联系人");
                        CleanZombieFanCircleUtils.this.checkZombieFan(linkedHashSet);
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
                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                        } else {
                            CleanZombieFanCircleUtils.this.executePartMain();
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
            LogUtils.log("WS_BABY.LauncherUI");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep((executeSpeedSetting / 10) + 400);
            }
            new PerformClickUtils2().checkNodeIds(this.autoService, grout_friend_list_id, list_item_name_id, (executeSpeedSetting / 4) + 100, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CleanZombieFanCircleUtils.this.startInt <= 1 || !CleanZombieFanCircleUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        CleanZombieFanCircleUtils.this.checkZombieFan(linkedHashSet);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + CleanZombieFanCircleUtils.this.startInt + "开始");
                    CleanZombieFanCircleUtils.this.initCheck();
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
                        AccessibilityNodeInfo rootInActiveWindow = CleanZombieFanCircleUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                PerformClickUtils.findTextAndClickFirst(CleanZombieFanCircleUtils.this.autoService, "搜索");
                            } else {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                                }
                            }
                            CleanZombieFanCircleUtils.this.searchNickName();
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
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = CleanZombieFanCircleUtils.this.startIndex = 1;
                        if (CleanZombieFanCircleUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(CleanZombieFanCircleUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = CleanZombieFanCircleUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "位联系人");
                        CleanZombieFanCircleUtils.this.initCheck();
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + 200, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                    CleanZombieFanCircleUtils.this.sleep(CleanZombieFanCircleUtils.this.residenceTime);
                    if (CleanZombieFanCircleUtils.this.friendStatus == 2 && CleanZombieFanCircleUtils.this.dndMode == 2) {
                        PerformClickUtils.inputPrefixText(CleanZombieFanCircleUtils.this.autoService, "A000被屏蔽_");
                    } else {
                        PerformClickUtils.inputPrefixText(CleanZombieFanCircleUtils.this.autoService, "A000非好友_");
                    }
                    CleanZombieFanCircleUtils.this.sleep(CleanZombieFanCircleUtils.this.residenceTime);
                    if (CleanZombieFanCircleUtils.this.remarkLabel == null || "".equals(CleanZombieFanCircleUtils.this.remarkLabel) || CleanZombieFanCircleUtils.this.isHasLabel) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList == null || !findViewIdList.get(0).isEnabled()) {
                            PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCircleUtils.this.executePartMain();
                                    }
                                }
                            });
                            return;
                        }
                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.complete_id);
                        CleanZombieFanCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                    CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                } else {
                                    CleanZombieFanCircleUtils.this.executePartMain();
                                }
                            }
                        });
                        return;
                    }
                    new PerformClickUtils2().checkNodeIds(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (i == 0) {
                                PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.input_remark_label);
                            } else if (i == 1) {
                                PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                            }
                            new PerformClickUtils2().checkString(CleanZombieFanCircleUtils.this.autoService, CleanZombieFanCircleUtils.this.remarkLabel, 200, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, CleanZombieFanCircleUtils.this.remarkLabel);
                                    CleanZombieFanCircleUtils.this.sleep(300);
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "保存");
                                    new PerformClickUtils2().checkString(CleanZombieFanCircleUtils.this.autoService, "完成", (BaseServiceUtils.executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "完成");
                                            boolean unused = CleanZombieFanCircleUtils.this.isHasLabel = false;
                                            CleanZombieFanCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                    } else {
                                                        CleanZombieFanCircleUtils.this.executePartMain();
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
        this.shieldNum = 0;
        this.noFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.startIndex = 0;
        this.residenceTime = 100;
        this.isHasLabel = false;
        this.nowName = "";
        this.continuityNilFriendCount = 0;
        this.sendResult = "";
        this.isJumpedStart = true;
        this.isFromMsgRemark = false;
        this.isFirst = true;
        this.scrollCount = 0;
        this.isSearchResult = true;
        this.isScrollBottom = false;
        this.operationParameterModel = operationParameterModel2;
        this.dndMode = operationParameterModel2.getDndMode();
        this.remarkLabel = operationParameterModel2.getSingLabelStr();
        this.isDeleteNoFriend = operationParameterModel2.isDeleteNoFriends();
        this.isDeleteShield = operationParameterModel2.isDeleteShieldFriends();
        this.sayContent = operationParameterModel2.getSayContent();
        this.startInt = operationParameterModel2.getStartIndex();
        this.nameLists = operationParameterModel2.getTagListPeoples();
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

    public void initFirstContactInfoCircleUI() {
        new PerformClickUtils2().check(this.autoService, back_contact_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.ContactInfoUI.initFirstContactInfoCircleUI.1");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCircleUtils.this.autoService, "朋友圈", "个人相册", 10, 100, 25, new PerformClickUtils2.OnCheckListener() {
                    public void find(final int i) {
                        new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCircleUtils.this.autoService, "发消息", "音视频通话", 10, 100, 25, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                if (CleanZombieFanCircleUtils.this.remarkLabel != null && !"".equals(CleanZombieFanCircleUtils.this.remarkLabel)) {
                                    boolean unused = CleanZombieFanCircleUtils.this.isHasLabel = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, CleanZombieFanCircleUtils.this.remarkLabel);
                                }
                                if (i == 0) {
                                    LogUtils.log("WS_BABY.ContactInfoUI.isFirstContactInfoMSGUI222");
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "朋友圈");
                                } else if (i == 1) {
                                    LogUtils.log("WS_BABY.ContactInfoUI.isFirstContactInfoMSGUI333");
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "个人相册");
                                }
                                CleanZombieFanCircleUtils.access$908(CleanZombieFanCircleUtils.this);
                                CleanZombieFanCircleUtils.this.saveIndex();
                                if (BaseServiceUtils.wxVersionCode >= 1440 || "7.0.4".equals(BaseServiceUtils.wxVersionName) || "7.0.3".equals(BaseServiceUtils.wxVersionName) || "7.0.0".equals(BaseServiceUtils.wxVersionName) || "6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                                    new PerformClickUtils2().check(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.album_head_img, 100, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            CleanZombieFanCircleUtils.this.checkFriend();
                                        }

                                        public void nuFind() {
                                            LogUtils.log("WS_BABY.ContactInfoUI.initFirstContactInfoCircleUI.2");
                                            if (CleanZombieFanCircleUtils.this.dndMode == 1) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.initFirstContactInfoCircleUI.3");
                                                CleanZombieFanCircleUtils.this.initFirstContactInfoSendMsgUI();
                                            } else if (CleanZombieFanCircleUtils.this.dndMode == 2) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                                                PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                        } else {
                                                            CleanZombieFanCircleUtils.this.executePartMain();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                } else {
                                    CleanZombieFanCircleUtils.this.checkFriend();
                                }
                            }

                            public void nuFind() {
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.ContactInfoUI.initFirstContactInfoCircleUI.2");
                        if (CleanZombieFanCircleUtils.this.dndMode == 1) {
                            CleanZombieFanCircleUtils.access$908(CleanZombieFanCircleUtils.this);
                            LogUtils.log("WS_BABY.ContactInfoUI.initFirstContactInfoCircleUI.3");
                            CleanZombieFanCircleUtils.this.initFirstContactInfoSendMsgUI();
                        } else if (CleanZombieFanCircleUtils.this.dndMode == 2) {
                            CleanZombieFanCircleUtils.access$908(CleanZombieFanCircleUtils.this);
                            LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                            PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                    } else {
                                        CleanZombieFanCircleUtils.this.executePartMain();
                                    }
                                }
                            });
                        }
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initFirstContactInfoDelete(final boolean z) {
        if ((z || !this.isDeleteNoFriend) && (!z || !this.isDeleteShield)) {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                    } else {
                        CleanZombieFanCircleUtils.this.executePartMain();
                    }
                }
            });
        } else {
            new PerformClickUtils2().check(this.autoService, right_more_id, (executeSpeedSetting / 8) + 200, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (((!z && CleanZombieFanCircleUtils.this.isDeleteNoFriend) || (z && CleanZombieFanCircleUtils.this.isDeleteShield)) && CleanZombieFanCircleUtils.this.excFriendsNum <= 1000 && MyApplication.enforceable) {
                        CleanZombieFanCircleUtils.this.saveFriendInfo(CleanZombieFanCircleUtils.this.nowName);
                    }
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                    new PerformClickUtils2().checkString(CleanZombieFanCircleUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (((!z && CleanZombieFanCircleUtils.this.isDeleteNoFriend) || (z && CleanZombieFanCircleUtils.this.isDeleteShield)) && CleanZombieFanCircleUtils.this.excFriendsNum <= 1000 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.ContactInfoUI.6.允许删除");
                                if (!PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                                    PerformClickUtils.scroll(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                                    CleanZombieFanCircleUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "删除");
                                } else {
                                    PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "删除");
                                }
                                new PerformClickUtils2().check(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 0, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        LogUtils.log("WS_BABY.ContactInfoUI.9.确认删除");
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCircleUtils.this.executePartMain();
                                        }
                                    }

                                    public void nuFind() {
                                        PerformClickUtils.executedBackHomeDeep(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                    CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                } else {
                                                    CleanZombieFanCircleUtils.this.executePartMain();
                                                }
                                            }
                                        });
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
        }
    }

    public void initFirstContactInfoRemark() {
        boolean z;
        boolean z2;
        LogUtils.log("WS_BABY_isNeedRemark0" + this.nowName);
        if (this.friendStatus == 2 && this.dndMode == 2) {
            z = !this.nowName.startsWith("A000被屏蔽_");
            LogUtils.log("WS_BABY_isNeedRemark1" + z);
        } else {
            z = !this.nowName.startsWith("A000非好友_");
            LogUtils.log("WS_BABY_isNeedRemark2" + z);
        }
        if (this.remarkLabel != null && !"".equals(this.remarkLabel)) {
            this.isHasLabel = PerformClickUtils.findNodeIsExistByText(this.autoService, this.remarkLabel);
            z2 = !this.isHasLabel || z2;
            LogUtils.log("WS_BABY_isNeedRemark3" + z2);
        }
        LogUtils.log("WS_BABY_isNeedRemark" + z2);
        if (z2) {
            new PerformClickUtils2().check(this.autoService, right_more_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCircleUtils.this.autoService, "设置备注和标签", "设置备注及标签", 100, 100, 50, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (i == 0) {
                                PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "设置备注和标签");
                                CleanZombieFanCircleUtils.this.initContactRemarkInfoModUI();
                            } else if (i == 1) {
                                PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "设置备注及标签");
                                CleanZombieFanCircleUtils.this.initContactRemarkInfoModUI();
                            }
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                }
            });
        } else {
            PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                        CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                    } else {
                        CleanZombieFanCircleUtils.this.executePartMain();
                    }
                }
            });
        }
    }

    public void initFirstContactInfoSendMsgUI() {
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", (executeSpeedSetting / 8) + 200, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick2(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.msg_layout);
                PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "发消息");
                LogUtils.log("WS_BABY_ContactInfoUI.22.发消息");
                new PerformClickUtils2().checkNodeId(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.voice_left_id, 0, 100, 15, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        CleanZombieFanCircleUtils.this.initJumpChattingUI();
                    }

                    public void nuFind() {
                        PerformClickUtils.findViewIdAndClick2(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.msg_layout);
                        PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "发消息");
                        new PerformClickUtils2().checkNodeId(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.voice_left_id, 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                CleanZombieFanCircleUtils.this.initJumpChattingUI();
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                                PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCircleUtils.this.executePartMain();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.ContactInfoUI.12.Back");
                PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                        } else {
                            CleanZombieFanCircleUtils.this.executePartMain();
                        }
                    }
                });
            }
        });
    }

    public void initJumpChattingUI() {
        LogUtils.log("WS_BABY_ChattingUI.2");
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, executeSpeedSetting + 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "按住 说话")) {
                    PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.voice_left_id);
                    CleanZombieFanCircleUtils.this.sleep(100);
                }
                int unused = CleanZombieFanCircleUtils.this.beforeReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanCircleUtils.this.autoService, "重发");
                PerformClickUtils.findViewByIdAndPasteContent(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.input_edit_text_id, CleanZombieFanCircleUtils.this.sayContent);
                new PerformClickUtils2().checkString(CleanZombieFanCircleUtils.this.autoService, "发送", (BaseServiceUtils.executeSpeedSetting / 10) + 50, 100, 50, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(CleanZombieFanCircleUtils.this.autoService, "发送");
                        LogUtils.log("WS_BABY_ChattingUI.2.发送");
                        CleanZombieFanCircleUtils.this.saveIndex();
                        new PerformClickUtils2().checkNodeIdNilExist(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.chatting_progress_id, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 200, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_ChattingUI.2.发送1");
                                PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                        } else {
                                            CleanZombieFanCircleUtils.this.executePartMain();
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
                                    int unused = CleanZombieFanCircleUtils.this.lastReSendNum = PerformClickUtils.findViewStringListSize(CleanZombieFanCircleUtils.this.autoService, "重发");
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_beforeReSendNum" + CleanZombieFanCircleUtils.this.beforeReSendNum);
                                    PrintStream printStream2 = System.out;
                                    printStream2.println("WS_BABY_lastReSendNum" + CleanZombieFanCircleUtils.this.lastReSendNum);
                                    if (CleanZombieFanCircleUtils.this.lastReSendNum > CleanZombieFanCircleUtils.this.beforeReSendNum) {
                                        int unused2 = CleanZombieFanCircleUtils.this.continuityNilFriendCount = CleanZombieFanCircleUtils.this.continuityNilFriendCount + 1;
                                        CleanZombieFanCircleUtils.access$1108(CleanZombieFanCircleUtils.this);
                                        boolean unused3 = CleanZombieFanCircleUtils.this.isFromMsgRemark = true;
                                        if (CleanZombieFanCircleUtils.this.excFriendsNum < 500 || CleanZombieFanCircleUtils.this.continuityNilFriendCount < 3) {
                                            try {
                                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.contact_title_id);
                                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.6.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList2.get(findViewIdList2.size() - 1));
                                                        CleanZombieFanCircleUtils.this.initSingleChatInfoUI();
                                                    }
                                                } else {
                                                    try {
                                                        str = findViewIdList.get(0).getText().toString();
                                                    } catch (Exception e) {
                                                        str = "";
                                                    }
                                                    if (str != null) {
                                                        if (str.contains("A000非好友_") && MyApplication.enforceable) {
                                                            if (CleanZombieFanCircleUtils.this.isDeleteNoFriend) {
                                                                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList3 != null && findViewIdList3.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.4.右上更多.走删除逻辑");
                                                                    PerformClickUtils.performClick(findViewIdList3.get(findViewIdList3.size() - 1));
                                                                    CleanZombieFanCircleUtils.this.initSingleChatInfoUI();
                                                                }
                                                            } else if (CleanZombieFanCircleUtils.this.remarkLabel == null || "".equals(CleanZombieFanCircleUtils.this.remarkLabel)) {
                                                                LogUtils.log("WS_BABY_ChattingUI.3.不可删除");
                                                                PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                    public void find() {
                                                                        if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                                            CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                                        } else {
                                                                            CleanZombieFanCircleUtils.this.executePartMain();
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                List<AccessibilityNodeInfo> findViewIdList4 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                                if (findViewIdList4 != null && findViewIdList4.size() > 0 && MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_ChattingUI.4.右上更多");
                                                                    PerformClickUtils.performClick(findViewIdList4.get(findViewIdList4.size() - 1));
                                                                    CleanZombieFanCircleUtils.this.initSingleChatInfoUI();
                                                                }
                                                            }
                                                        }
                                                    }
                                                    List<AccessibilityNodeInfo> findViewIdList5 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                    if (findViewIdList5 != null && findViewIdList5.size() > 0 && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_ChattingUI.5.右上更多");
                                                        PerformClickUtils.performClick(findViewIdList5.get(findViewIdList5.size() - 1));
                                                        CleanZombieFanCircleUtils.this.initSingleChatInfoUI();
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                List<AccessibilityNodeInfo> findViewIdList6 = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                                if (findViewIdList6 != null && findViewIdList6.size() > 0 && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_ChattingUI.7.右上更多");
                                                    PerformClickUtils.performClick(findViewIdList6.get(findViewIdList6.size() - 1));
                                                    CleanZombieFanCircleUtils.this.initSingleChatInfoUI();
                                                }
                                            }
                                        } else {
                                            String unused4 = CleanZombieFanCircleUtils.this.sendResult = "由于您连续检测了500个以上的好友，由于微信限制规则，微商宝贝检测到了连续三条非好友，为了防止检测异常（检测错误），希望您隔3小时后，继续检测。";
                                            BaseServiceUtils.removeEndView(CleanZombieFanCircleUtils.this.mMyManager);
                                        }
                                    } else {
                                        int unused5 = CleanZombieFanCircleUtils.this.continuityNilFriendCount = 0;
                                        LogUtils.log("WS_BABY_ChattingUI.8.BACK");
                                        PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                    CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                                } else {
                                                    CleanZombieFanCircleUtils.this.executePartMain();
                                                }
                                            }
                                        });
                                    }
                                    myWindowManager = CleanZombieFanCircleUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Exception e3) {
                                    LogUtils.log("WS_BABY_ChattingUI.9.BACK");
                                    PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            if (CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 0 || CleanZombieFanCircleUtils.this.operationParameterModel.getSendCardType() == 2) {
                                                CleanZombieFanCircleUtils.this.executeMain(CleanZombieFanCircleUtils.this.nameLists);
                                            } else {
                                                CleanZombieFanCircleUtils.this.executePartMain();
                                            }
                                        }
                                    });
                                    myWindowManager = CleanZombieFanCircleUtils.this.mMyManager;
                                    sb = new StringBuilder();
                                } catch (Throwable th) {
                                    MyWindowManager myWindowManager2 = CleanZombieFanCircleUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager2, "从第 " + CleanZombieFanCircleUtils.this.startInt + " 个好友，开始检测僵尸粉\n已执行 " + CleanZombieFanCircleUtils.this.excFriendsNum + " 人 ,非好友 " + CleanZombieFanCircleUtils.this.noFriendsNum + " 人");
                                    throw th;
                                }
                                sb.append("从第 ");
                                sb.append(CleanZombieFanCircleUtils.this.startInt);
                                sb.append(" 个好友，开始检测僵尸粉\n已执行 ");
                                sb.append(CleanZombieFanCircleUtils.this.excFriendsNum);
                                sb.append(" 人 ,非好友 ");
                                sb.append(CleanZombieFanCircleUtils.this.noFriendsNum);
                                sb.append(" 人");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
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

    public void initSingleChatInfoUI() {
        LogUtils.log("WS_BABY_SingleChatInfoUI.2");
        new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeedSetting + 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                if (CleanZombieFanCircleUtils.this.isDeleteNoFriend) {
                    CleanZombieFanCircleUtils.this.initFirstContactInfoDelete(false);
                } else {
                    CleanZombieFanCircleUtils.this.initFirstContactInfoRemark();
                }
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
        int i = this.startInt == 1 ? this.excFriendsNum + 1 : this.excFriendsNum + this.startInt;
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
                    if (CleanZombieFanCircleUtils.this.nameLists != null && CleanZombieFanCircleUtils.this.nameLists.size() > 0 && MyApplication.enforceable) {
                        String str = (String) CleanZombieFanCircleUtils.this.nameLists.iterator().next();
                        String unused = CleanZombieFanCircleUtils.this.nowName = str;
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.search_id);
                        CleanZombieFanCircleUtils.this.sleep(350);
                        PerformClickUtils.inputText(CleanZombieFanCircleUtils.this.autoService, str);
                        CleanZombieFanCircleUtils.this.nameLists.remove(CleanZombieFanCircleUtils.this.nameLists.iterator().next());
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(CleanZombieFanCircleUtils.this.autoService, "联系人", "最常使用", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "联系人");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CleanZombieFanCircleUtils.this.autoService, "最常使用");
                            if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.search_into");
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0) {
                                    CleanZombieFanCircleUtils.access$908(CleanZombieFanCircleUtils.this);
                                    CleanZombieFanCircleUtils.this.saveIndex();
                                    PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 10, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            CleanZombieFanCircleUtils.this.executePartMain();
                                        }
                                    });
                                    return;
                                }
                                PerformClickUtils.performClick(findViewIdList.get(0));
                                new PerformClickUtils2().check(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.right_more_id);
                                        new PerformClickUtils2().check(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id, BaseServiceUtils.executeSpeedSetting + 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findViewIdAndClick(CleanZombieFanCircleUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                                                CleanZombieFanCircleUtils.this.initFirstContactInfoCircleUI();
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

                        public void nuFind() {
                            CleanZombieFanCircleUtils.access$908(CleanZombieFanCircleUtils.this);
                            CleanZombieFanCircleUtils.this.saveIndex();
                            PerformClickUtils.executedBackHome(CleanZombieFanCircleUtils.this.autoService, 10, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CleanZombieFanCircleUtils.this.executePartMain();
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
