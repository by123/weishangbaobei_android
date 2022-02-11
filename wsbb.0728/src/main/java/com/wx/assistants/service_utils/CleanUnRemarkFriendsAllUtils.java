package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class CleanUnRemarkFriendsAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanUnRemarkFriendsAllUtils instance;
    /* access modifiers changed from: private */
    public int cleanMaxNum = 200;
    /* access modifiers changed from: private */
    public int deleteType = 3;
    /* access modifiers changed from: private */
    public int deletedNum = 0;
    /* access modifiers changed from: private */
    public boolean isFirstDelete = true;
    /* access modifiers changed from: private */
    public int isJumpCount = 0;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPeoples = null;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;

    private CleanUnRemarkFriendsAllUtils() {
    }

    static /* synthetic */ int access$208(CleanUnRemarkFriendsAllUtils cleanUnRemarkFriendsAllUtils) {
        int i = cleanUnRemarkFriendsAllUtils.deletedNum;
        cleanUnRemarkFriendsAllUtils.deletedNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(CleanUnRemarkFriendsAllUtils cleanUnRemarkFriendsAllUtils) {
        int i = cleanUnRemarkFriendsAllUtils.isJumpCount;
        cleanUnRemarkFriendsAllUtils.isJumpCount = i + 1;
        return i;
    }

    public static CleanUnRemarkFriendsAllUtils getInstance() {
        instance = new CleanUnRemarkFriendsAllUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在一键删除好友，请不要操作微信！");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(CleanUnRemarkFriendsAllUtils.this.cleanMaxNum, true);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void executeAllMain(final int i, final boolean z) {
        try {
            LogUtils.log("WS_BABY.CleanMaxNum" + i);
            new Thread(new Runnable() {
                public void run() {
                    if (CleanUnRemarkFriendsAllUtils.this.isFirstDelete) {
                        boolean unused = CleanUnRemarkFriendsAllUtils.this.isFirstDelete = false;
                        PerformClickUtils.findTextAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录");
                        PerformClickUtils.findTextAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录");
                        PerformClickUtils.findTextAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录");
                    }
                    if (z) {
                        CleanUnRemarkFriendsAllUtils.this.sleep(BannerConfig.DURATION);
                    } else {
                        CleanUnRemarkFriendsAllUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 10) + 100);
                    }
                    new PerformClickUtils2().checkNodeId(CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_name_id, 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                            if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.@@@@@@@@");
                                BaseServiceUtils.removeEndView(CleanUnRemarkFriendsAllUtils.this.mMyManager);
                                return;
                            }
                            LogUtils.log("WS_BABY.deletedNum" + CleanUnRemarkFriendsAllUtils.this.deletedNum);
                            if (CleanUnRemarkFriendsAllUtils.this.deletedNum >= i) {
                                BaseServiceUtils.removeEndView(CleanUnRemarkFriendsAllUtils.this.mMyManager);
                                return;
                            }
                            int unused = CleanUnRemarkFriendsAllUtils.this.startIndex = CleanUnRemarkFriendsAllUtils.this.isJumpCount;
                            if (CleanUnRemarkFriendsAllUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_1");
                                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CleanUnRemarkFriendsAllUtils.this.startIndex);
                                AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(CleanUnRemarkFriendsAllUtils.this.startIndex);
                                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                    CleanUnRemarkFriendsAllUtils cleanUnRemarkFriendsAllUtils = CleanUnRemarkFriendsAllUtils.this;
                                    String unused2 = cleanUnRemarkFriendsAllUtils.nowName = accessibilityNodeInfo.getText() + "";
                                }
                                if (CleanUnRemarkFriendsAllUtils.this.nowName != null && !"".equals(CleanUnRemarkFriendsAllUtils.this.nowName) && CleanUnRemarkFriendsAllUtils.this.jumpPeoples.contains(CleanUnRemarkFriendsAllUtils.this.nowName)) {
                                    CleanUnRemarkFriendsAllUtils.access$408(CleanUnRemarkFriendsAllUtils.this);
                                    MyWindowManager myWindowManager = CleanUnRemarkFriendsAllUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已跳过【 " + CleanUnRemarkFriendsAllUtils.this.nowName + " 】");
                                    CleanUnRemarkFriendsAllUtils.this.sleep(100);
                                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(i, false);
                                } else if ("微信团队".equals(CleanUnRemarkFriendsAllUtils.this.nowName) || "文件传输助手".equals(CleanUnRemarkFriendsAllUtils.this.nowName)) {
                                    CleanUnRemarkFriendsAllUtils.access$408(CleanUnRemarkFriendsAllUtils.this);
                                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(i, false);
                                } else {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    CleanUnRemarkFriendsAllUtils.this.initContactInfoAll();
                                }
                            } else if (CleanUnRemarkFriendsAllUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_2");
                                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                                if (findViewIdList3 != null && findViewIdList3.size() != 0) {
                                    findViewIdList3.get(0).performAction(4096);
                                    int unused3 = CleanUnRemarkFriendsAllUtils.this.startIndex = 1;
                                    int unused4 = CleanUnRemarkFriendsAllUtils.this.isJumpCount = 1;
                                    CleanUnRemarkFriendsAllUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                    if (CleanUnRemarkFriendsAllUtils.this.isScrollBottom) {
                                        BaseServiceUtils.removeEndView(CleanUnRemarkFriendsAllUtils.this.mMyManager);
                                    }
                                    boolean unused5 = CleanUnRemarkFriendsAllUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanUnRemarkFriendsAllUtils.this.autoService, "位联系人");
                                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(i, false);
                                }
                            }
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY.@@@@@@@@");
                            BaseServiceUtils.removeEndView(CleanUnRemarkFriendsAllUtils.this.mMyManager);
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactInfoAll() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    new PerformClickUtils2().checkNilString(CleanUnRemarkFriendsAllUtils.this.autoService, "添加到通讯录", 0, 100, 5, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (CleanUnRemarkFriendsAllUtils.this.deleteType == 3 ? !PerformClickUtils.findNodeIsExistByText(CleanUnRemarkFriendsAllUtils.this.autoService, "昵称") : CleanUnRemarkFriendsAllUtils.this.deleteType == 4 ? PerformClickUtils.findNodeIsExistByText(CleanUnRemarkFriendsAllUtils.this.autoService, "设置备注和标签") : true) {
                                CleanUnRemarkFriendsAllUtils.this.saveFriendInfo(CleanUnRemarkFriendsAllUtils.this.nowName);
                                PerformClickUtils.findViewIdAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.right_more_id);
                                new PerformClickUtils2().checkString(CleanUnRemarkFriendsAllUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (!PerformClickUtils.findNodeIsExistByText(CleanUnRemarkFriendsAllUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                                            PerformClickUtils.scroll(CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                                            CleanUnRemarkFriendsAllUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            PerformClickUtils.findTextAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, "删除");
                                        } else {
                                            PerformClickUtils.findTextAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, "删除");
                                        }
                                        new PerformClickUtils2().check(CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                PerformClickUtils.findViewIdAndClick(CleanUnRemarkFriendsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                CleanUnRemarkFriendsAllUtils.access$208(CleanUnRemarkFriendsAllUtils.this);
                                                MyWindowManager myWindowManager = CleanUnRemarkFriendsAllUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "已删除了 " + CleanUnRemarkFriendsAllUtils.this.deletedNum + " 个好友");
                                                new PerformClickUtils2().checkStringsAll(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录", "发现", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        CleanUnRemarkFriendsAllUtils.this.executeAllMain(CleanUnRemarkFriendsAllUtils.this.cleanMaxNum, true);
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
                                return;
                            }
                            int unused = CleanUnRemarkFriendsAllUtils.this.isJumpCount = CleanUnRemarkFriendsAllUtils.this.isJumpCount + 1;
                            PerformClickUtils.performBack(CleanUnRemarkFriendsAllUtils.this.autoService);
                            new PerformClickUtils2().checkStringsAll(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录", "发现", (BaseServiceUtils.executeSpeedSetting / 2) + 300, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(CleanUnRemarkFriendsAllUtils.this.cleanMaxNum, true);
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            int unused = CleanUnRemarkFriendsAllUtils.this.isJumpCount = CleanUnRemarkFriendsAllUtils.this.isJumpCount + 1;
                            PerformClickUtils.performBack(CleanUnRemarkFriendsAllUtils.this.autoService);
                            new PerformClickUtils2().checkStringsAll(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录", "发现", (BaseServiceUtils.executeSpeedSetting / 2) + 300, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    CleanUnRemarkFriendsAllUtils.this.executeAllMain(CleanUnRemarkFriendsAllUtils.this.cleanMaxNum, true);
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    int unused = CleanUnRemarkFriendsAllUtils.this.isJumpCount = CleanUnRemarkFriendsAllUtils.this.isJumpCount + 1;
                    PerformClickUtils.performBack(CleanUnRemarkFriendsAllUtils.this.autoService);
                    new PerformClickUtils2().checkStringsAll(CleanUnRemarkFriendsAllUtils.this.autoService, "通讯录", "发现", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 300, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            CleanUnRemarkFriendsAllUtils.this.executeAllMain(CleanUnRemarkFriendsAllUtils.this.cleanMaxNum, true);
                        }

                        public void nuFind() {
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_69");
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.isJumpCount = 0;
        this.isFirstDelete = true;
        this.deletedNum = 0;
        this.nowName = "";
        this.deleteType = operationParameterModel.getDeleteFriendsType();
        this.isScrollBottom = false;
        this.cleanMaxNum = operationParameterModel.getMaxOperaNum();
        this.jumpPeoples = operationParameterModel.getTagListPeoples();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除好友", "已删除了" + this.deletedNum + "个好友");
    }
}
