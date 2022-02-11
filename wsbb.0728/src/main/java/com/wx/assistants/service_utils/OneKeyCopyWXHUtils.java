package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import java.util.ArrayList;
import java.util.List;

public class OneKeyCopyWXHUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static OneKeyCopyWXHUtils instance;
    private List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    List<AccessibilityNodeInfo> itemList = null;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public boolean launchFlag = true;
    /* access modifiers changed from: private */
    public String nowName = "";
    private int scrollCount = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private OneKeyCopyWXHUtils() {
    }

    static /* synthetic */ int access$208(OneKeyCopyWXHUtils oneKeyCopyWXHUtils) {
        int i = oneKeyCopyWXHUtils.excFriendsNum;
        oneKeyCopyWXHUtils.excFriendsNum = i + 1;
        return i;
    }

    public static OneKeyCopyWXHUtils getInstance() {
        instance = new OneKeyCopyWXHUtils();
        return instance;
    }

    public void copyFans() {
        this.isJumpedStart = false;
        LogUtils.log("WS_BABY_LauncherUI.start");
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_LauncherUI.startIndex" + this.startIndex + "@" + findViewIdList.size());
            if (this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(this.startIndex);
                if (accessibilityNodeInfo != null) {
                    if (accessibilityNodeInfo.getText() != null) {
                        this.nowName = accessibilityNodeInfo.getText() + "";
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
                        copyFans();
                    } else if ("微信团队".equals(this.nowName) || "文件传输助手".equals(this.nowName)) {
                        this.excFriendsNum++;
                        this.startIndex++;
                        LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                        SPUtils.put(this.autoService, "copy_friend_index", Integer.valueOf(this.excFriendsNum + this.startInt));
                        copyFans();
                    } else {
                        SPUtils.put(this.autoService, "copy_friend_index", Integer.valueOf(this.excFriendsNum + this.startInt));
                        AccessibilityNodeInfo accessibilityNodeInfo2 = null;
                        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_layout_id);
                        if (findViewIdList2 != null && findViewIdList2.size() > this.startIndex) {
                            accessibilityNodeInfo2 = findViewIdList2.get(this.startIndex);
                        }
                        this.startIndex++;
                        if (accessibilityNodeInfo2 != null) {
                            LogUtils.log("WS_BABY_LauncherUI.check");
                            this.beforeLists.add(this.nowName);
                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            initFirstContactInfoMSGUI();
                            return;
                        }
                        SPUtils.put(this.autoService, "copy_friend_index", Integer.valueOf(this.excFriendsNum + this.startInt));
                        this.excFriendsNum++;
                        copyFans();
                    }
                } else {
                    copyFans();
                }
            } else if (this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 350, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = OneKeyCopyWXHUtils.this.startIndex = 1;
                        if (OneKeyCopyWXHUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(OneKeyCopyWXHUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = OneKeyCopyWXHUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(OneKeyCopyWXHUtils.this.autoService, "位联系人");
                        OneKeyCopyWXHUtils.this.copyFans();
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
            this.launchFlag = true;
            MyWindowManager myWindowManager = this.mMyManager;
            showBottomView(myWindowManager, "从第 " + this.startInt + " 个好友开始备份\n请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneKeyCopyWXHUtils.this.executeMain();
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
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                sleep(400);
            }
            new PerformClickUtils2().check(this.autoService, grout_friend_list_id, executeSpeedSetting + 200, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (OneKeyCopyWXHUtils.this.startInt > 1 && OneKeyCopyWXHUtils.this.isJumpedStart && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从" + OneKeyCopyWXHUtils.this.startInt + "开始");
                        new Thread(new Runnable() {
                            public void run() {
                                OneKeyCopyWXHUtils.this.jumpStartPosition();
                            }
                        }).start();
                    } else if (OneKeyCopyWXHUtils.this.launchFlag) {
                        boolean unused = OneKeyCopyWXHUtils.this.launchFlag = false;
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        OneKeyCopyWXHUtils.this.copyFans();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.excFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.startIndex = 0;
        this.nowName = "";
        this.isJumpedStart = true;
        this.isScrollBottom = false;
        this.isFirst = true;
        this.scrollCount = 0;
        this.launchFlag = true;
        this.startInt = operationParameterModel.getStartIndex();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstContactInfoMSGUI() {
        LogUtils.log("WS_BABY_ContactInfoUI.111111");
        new PerformClickUtils2().checkNodeId(this.autoService, msg_layout, executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                OneKeyCopyWXHUtils.this.saveCopyFriendInfo(OneKeyCopyWXHUtils.this.nowName);
                OneKeyCopyWXHUtils.this.sleep(100);
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                boolean unused = OneKeyCopyWXHUtils.this.launchFlag = true;
                OneKeyCopyWXHUtils.access$208(OneKeyCopyWXHUtils.this);
                MyWindowManager myWindowManager = OneKeyCopyWXHUtils.this.mMyManager;
                BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + OneKeyCopyWXHUtils.this.startInt + " 个好友开始备份\n已备份 " + OneKeyCopyWXHUtils.this.excFriendsNum + " 个好友");
                PerformClickUtils.findViewIdAndClick(OneKeyCopyWXHUtils.this.autoService, BaseServiceUtils.back_contact_id);
                OneKeyCopyWXHUtils.this.executeMain();
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                boolean unused = OneKeyCopyWXHUtils.this.launchFlag = true;
                PerformClickUtils.findViewIdAndClick(OneKeyCopyWXHUtils.this.autoService, BaseServiceUtils.back_contact_id);
                OneKeyCopyWXHUtils.this.executeMain();
            }
        });
    }

    public void jumpStartPosition() {
        if (this.itemList == null) {
            this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.itemList != null && this.itemList.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = this.itemList.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.nowName = accessibilityNodeInfo.getText() + "";
                }
                if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                    this.scrollCount++;
                }
                LogUtils.log("WS_BABY_JUMP_NUM@" + this.scrollCount + "@" + this.startInt);
                if (this.scrollCount == this.startInt) {
                    copyFans();
                    return;
                }
                this.jumpStartLists.add(this.nowName);
                this.startIndex++;
                updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个好友开始备份\n已跳过 " + this.scrollCount + " 个好友");
                jumpStartPosition();
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                this.startIndex = 1;
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (OneKeyCopyWXHUtils.this.isScrollBottom) {
                            BaseServiceUtils.removeEndView(OneKeyCopyWXHUtils.this.mMyManager);
                            return;
                        }
                        boolean unused = OneKeyCopyWXHUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(OneKeyCopyWXHUtils.this.autoService, "位联系人");
                        OneKeyCopyWXHUtils.this.jumpStartPosition();
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
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            setMiddleText(myWindowManager, "好友备份结束", "从第 " + this.startInt + " 个好友开始备份\n已备份 " + this.excFriendsNum + " 个好友");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
