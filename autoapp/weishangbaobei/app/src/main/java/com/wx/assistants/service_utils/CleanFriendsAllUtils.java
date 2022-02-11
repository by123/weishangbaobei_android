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

public class CleanFriendsAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanFriendsAllUtils instance;
    /* access modifiers changed from: private */
    public int cleanMaxNum = 200;
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

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$108(CleanFriendsAllUtils cleanFriendsAllUtils) {
        int i = cleanFriendsAllUtils.deletedNum;
        cleanFriendsAllUtils.deletedNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(CleanFriendsAllUtils cleanFriendsAllUtils) {
        int i = cleanFriendsAllUtils.isJumpCount;
        cleanFriendsAllUtils.isJumpCount = i + 1;
        return i;
    }

    private CleanFriendsAllUtils() {
    }

    public static CleanFriendsAllUtils getInstance() {
        instance = new CleanFriendsAllUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.isJumpCount = 0;
        this.isFirstDelete = true;
        this.deletedNum = 0;
        this.nowName = "";
        this.isScrollBottom = false;
        this.cleanMaxNum = operationParameterModel.getMaxOperaNum();
        this.jumpPeoples = operationParameterModel.getTagListPeoples();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initContactInfoAll() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    new PerformClickUtils2().checkNilString(CleanFriendsAllUtils.this.autoService, "添加到通讯录", 0, 100, 5, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            System.out.println("WS_BABY_DELETE_1");
                            CleanFriendsAllUtils.this.saveFriendInfo(CleanFriendsAllUtils.this.nowName);
                            PerformClickUtils.findViewIdAndClick(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.right_more_id);
                            new PerformClickUtils2().checkString(CleanFriendsAllUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    System.out.println("WS_BABY_DELETE_2");
                                    if (!PerformClickUtils.findNodeIsExistByText(CleanFriendsAllUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                                        System.out.println("WS_BABY_DELETE_4");
                                        PerformClickUtils.scroll(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                                        CleanFriendsAllUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                        PerformClickUtils.findTextAndClick(CleanFriendsAllUtils.this.autoService, "删除");
                                    } else {
                                        System.out.println("WS_BABY_DELETE_3");
                                        PerformClickUtils.findTextAndClick(CleanFriendsAllUtils.this.autoService, "删除");
                                    }
                                    new PerformClickUtils2().check(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            System.out.println("WS_BABY_DELETE_5");
                                            PerformClickUtils.findViewIdAndClick(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                            CleanFriendsAllUtils.access$108(CleanFriendsAllUtils.this);
                                            MyWindowManager myWindowManager = CleanFriendsAllUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "已删除了 " + CleanFriendsAllUtils.this.deletedNum + " 个好友");
                                            new PerformClickUtils2().checkStringsAll(CleanFriendsAllUtils.this.autoService, "通讯录", "发现", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 100, 300, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    CleanFriendsAllUtils.this.executeAllMain(CleanFriendsAllUtils.this.cleanMaxNum, true);
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            System.out.println("WS_BABY_DELETE_6");
                                        }
                                    });
                                }

                                public void nuFind() {
                                    System.out.println("WS_BABY_DELETE_7");
                                }
                            });
                        }

                        public void nuFind() {
                            System.out.println("WS_BABY_DELETE_8");
                            int unused = CleanFriendsAllUtils.this.isJumpCount = CleanFriendsAllUtils.this.isJumpCount + 1;
                            PerformClickUtils.performBack(CleanFriendsAllUtils.this.autoService);
                            new PerformClickUtils2().checkStringsAll(CleanFriendsAllUtils.this.autoService, "通讯录", "发现", (BaseServiceUtils.executeSpeedSetting / 2) + 300, 100, 300, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    CleanFriendsAllUtils.this.executeAllMain(CleanFriendsAllUtils.this.cleanMaxNum, true);
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    System.out.println("WS_BABY_DELETE_9");
                    int unused = CleanFriendsAllUtils.this.isJumpCount = CleanFriendsAllUtils.this.isJumpCount + 1;
                    PerformClickUtils.performBack(CleanFriendsAllUtils.this.autoService);
                    new PerformClickUtils2().checkStringsAll(CleanFriendsAllUtils.this.autoService, "通讯录", "发现", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 300, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            CleanFriendsAllUtils.this.executeAllMain(CleanFriendsAllUtils.this.cleanMaxNum, true);
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_69");
            e.printStackTrace();
        }
    }

    public void executeAllMain(final int i, final boolean z) {
        try {
            LogUtils.log("WS_BABY.CleanMaxNum" + i);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (CleanFriendsAllUtils.this.isFirstDelete) {
                            boolean unused = CleanFriendsAllUtils.this.isFirstDelete = false;
                            PerformClickUtils.findTextAndClick(CleanFriendsAllUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(CleanFriendsAllUtils.this.autoService, "通讯录");
                            PerformClickUtils.findTextAndClick(CleanFriendsAllUtils.this.autoService, "通讯录");
                        }
                        if (z) {
                            CleanFriendsAllUtils.this.sleep(BannerConfig.DURATION);
                        } else {
                            CleanFriendsAllUtils.this.sleep((BaseServiceUtils.executeSpeedSetting / 10) + 100);
                        }
                        new PerformClickUtils2().checkNodeId(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_name_id, 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.@@@@@@@@");
                                    BaseServiceUtils.removeEndView(CleanFriendsAllUtils.this.mMyManager);
                                    return;
                                }
                                LogUtils.log("WS_BABY.deletedNum" + CleanFriendsAllUtils.this.deletedNum);
                                if (CleanFriendsAllUtils.this.deletedNum >= i) {
                                    BaseServiceUtils.removeEndView(CleanFriendsAllUtils.this.mMyManager);
                                    return;
                                }
                                int unused = CleanFriendsAllUtils.this.startIndex = CleanFriendsAllUtils.this.isJumpCount;
                                if (CleanFriendsAllUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_1");
                                    AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CleanFriendsAllUtils.this.startIndex);
                                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                        CleanFriendsAllUtils cleanFriendsAllUtils = CleanFriendsAllUtils.this;
                                        String unused2 = cleanFriendsAllUtils.nowName = accessibilityNodeInfo.getText() + "";
                                    }
                                    if (CleanFriendsAllUtils.this.nowName != null && !"".equals(CleanFriendsAllUtils.this.nowName) && CleanFriendsAllUtils.this.jumpPeoples.contains(CleanFriendsAllUtils.this.nowName)) {
                                        CleanFriendsAllUtils.access$308(CleanFriendsAllUtils.this);
                                        MyWindowManager myWindowManager = CleanFriendsAllUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "已跳过【 " + CleanFriendsAllUtils.this.nowName + " 】");
                                        CleanFriendsAllUtils.this.sleep(100);
                                        CleanFriendsAllUtils.this.executeAllMain(i, false);
                                    } else if ("微信团队".equals(CleanFriendsAllUtils.this.nowName) || "文件传输助手".equals(CleanFriendsAllUtils.this.nowName)) {
                                        CleanFriendsAllUtils.access$308(CleanFriendsAllUtils.this);
                                        CleanFriendsAllUtils.this.executeAllMain(i, false);
                                    } else {
                                        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) CleanFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                                        if (findViewIdList2 == null || findViewIdList2.size() <= 0 || findViewIdList2.size() <= CleanFriendsAllUtils.this.startIndex) {
                                            CleanFriendsAllUtils.this.executeAllMain(i, false);
                                            return;
                                        }
                                        PerformClickUtils.performClick(findViewIdList2.get(CleanFriendsAllUtils.this.startIndex));
                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                        CleanFriendsAllUtils.this.initContactInfoAll();
                                    }
                                } else if (CleanFriendsAllUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY_2");
                                    PerformClickUtils.scroll(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                                    int unused3 = CleanFriendsAllUtils.this.startIndex = 1;
                                    int unused4 = CleanFriendsAllUtils.this.isJumpCount = 1;
                                    new PerformClickUtils2().check(CleanFriendsAllUtils.this.autoService, BaseServiceUtils.list_item_name_id, 300, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            if (CleanFriendsAllUtils.this.isScrollBottom) {
                                                BaseServiceUtils.removeEndView(CleanFriendsAllUtils.this.mMyManager);
                                                return;
                                            }
                                            boolean unused = CleanFriendsAllUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(CleanFriendsAllUtils.this.autoService, "位联系人");
                                            CleanFriendsAllUtils.this.executeAllMain(i, false);
                                        }
                                    });
                                }
                            }

                            public void nuFind() {
                                LogUtils.log("WS_BABY.@@@@@@@@");
                                BaseServiceUtils.removeEndView(CleanFriendsAllUtils.this.mMyManager);
                            }
                        });
                    } catch (Exception unused2) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除好友", "已删除了" + this.deletedNum + "个好友");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在一键删除好友，请不要操作微信！");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanFriendsAllUtils.this.executeAllMain(CleanFriendsAllUtils.this.cleanMaxNum, true);
                } catch (Exception unused) {
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
