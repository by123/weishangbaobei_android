package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.List;

public class CleanFriendsTagUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CleanFriendsTagUtils instance;
    /* access modifiers changed from: private */
    public int deleteMaxNum = 5000;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> deleteTags = null;
    /* access modifiers changed from: private */
    public int deletedFriendNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> fansRecord = null;
    boolean isJumpExecute = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastTagName = "";
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public String nowNameNum = "";
    /* access modifiers changed from: private */
    public String nowTag = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndex2 = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1008(CleanFriendsTagUtils cleanFriendsTagUtils) {
        int i = cleanFriendsTagUtils.startIndex2;
        cleanFriendsTagUtils.startIndex2 = i + 1;
        return i;
    }

    static /* synthetic */ int access$108(CleanFriendsTagUtils cleanFriendsTagUtils) {
        int i = cleanFriendsTagUtils.startIndex;
        cleanFriendsTagUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(CleanFriendsTagUtils cleanFriendsTagUtils) {
        int i = cleanFriendsTagUtils.deletedFriendNum;
        cleanFriendsTagUtils.deletedFriendNum = i + 1;
        return i;
    }

    private CleanFriendsTagUtils() {
    }

    public static CleanFriendsTagUtils getInstance() {
        instance = new CleanFriendsTagUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        System.out.println("WS_BABY_initData");
        this.startIndex = 0;
        this.startIndex2 = 0;
        this.deletedFriendNum = 0;
        this.lastTagName = "";
        this.nowTag = "";
        this.nowNameNum = "0";
        this.nowName = "";
        this.isWhile = true;
        this.isScrollBottom = false;
        this.deleteMaxNum = operationParameterModel.getMaxOperaNum();
        this.fansRecord = new LinkedHashSet<>();
        this.deleteTags = operationParameterModel.getTagListNames();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initContactLabelManagerUI() {
        LogUtils.log("WS_BABY_ContactLabelManagerUI.1");
        try {
            LogUtils.log("WS_BABY.ContactLabelManagerUI.2");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, tag_list_item_num_id, tag_list_item_name_id, executeSpeedSetting + executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
                    String str;
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.3");
                    while (CleanFriendsTagUtils.this.isWhile && MyApplication.enforceable) {
                        AccessibilityNodeInfo rootInActiveWindow = CleanFriendsTagUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactLabelManagerUI.4");
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_name_id);
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_num_id);
                            if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                if (CleanFriendsTagUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId3.get(CleanFriendsTagUtils.this.startIndex);
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId4.get(CleanFriendsTagUtils.this.startIndex);
                                    try {
                                        String unused = CleanFriendsTagUtils.this.nowTag = accessibilityNodeInfo.getText() + "";
                                        String unused2 = CleanFriendsTagUtils.this.nowNameNum = accessibilityNodeInfo2.getText() + "";
                                    } catch (Exception unused3) {
                                    }
                                    if (!CleanFriendsTagUtils.this.deleteTags.contains(CleanFriendsTagUtils.this.nowTag)) {
                                        CleanFriendsTagUtils.access$108(CleanFriendsTagUtils.this);
                                    } else if (!"(0)".equals(CleanFriendsTagUtils.this.nowNameNum)) {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.6");
                                        CleanFriendsTagUtils.this.deleteTags.remove(CleanFriendsTagUtils.this.nowTag);
                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(CleanFriendsTagUtils.this.startIndex));
                                        int unused4 = CleanFriendsTagUtils.this.startIndex = 0;
                                        String unused5 = CleanFriendsTagUtils.this.lastTagName = "";
                                        CleanFriendsTagUtils.this.initContactLabelEditUI();
                                        return;
                                    } else {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.7");
                                        CleanFriendsTagUtils.access$108(CleanFriendsTagUtils.this);
                                    }
                                } else if (CleanFriendsTagUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.ContactLabelManagerUI.8");
                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                        CleanFriendsTagUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        AccessibilityNodeInfo rootInActiveWindow2 = CleanFriendsTagUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1);
                                            try {
                                                str = accessibilityNodeInfo3.getText() + "";
                                            } catch (Exception unused6) {
                                                str = "";
                                            }
                                            if (str.equals(CleanFriendsTagUtils.this.lastTagName)) {
                                                boolean unused7 = CleanFriendsTagUtils.this.isWhile = false;
                                                BaseServiceUtils.removeEndView(CleanFriendsTagUtils.this.mMyManager);
                                            } else {
                                                String unused8 = CleanFriendsTagUtils.this.lastTagName = str;
                                            }
                                            int unused9 = CleanFriendsTagUtils.this.startIndex = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.unfind");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactInfoUIByTag() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    new PerformClickUtils2().checkString(CleanFriendsTagUtils.this.autoService, "发消息", 10, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_ContactInfoUI");
                            CleanFriendsTagUtils.this.saveFriendInfo(CleanFriendsTagUtils.this.nowName);
                            PerformClickUtils.findViewIdAndClick(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.right_more_id);
                            new PerformClickUtils2().checkString(CleanFriendsTagUtils.this.autoService, "黑名单", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (!PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "删除") || !MyApplication.enforceable) {
                                        PerformClickUtils.scroll(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.more_recycle_view_id);
                                        CleanFriendsTagUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                        PerformClickUtils.findTextAndClick(CleanFriendsTagUtils.this.autoService, "删除");
                                    } else {
                                        PerformClickUtils.findTextAndClick(CleanFriendsTagUtils.this.autoService, "删除");
                                    }
                                    new PerformClickUtils2().check(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.dialog_ok_id, 100, 50, 40, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findViewIdAndClick(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                            CleanFriendsTagUtils.access$708(CleanFriendsTagUtils.this);
                                            MyWindowManager myWindowManager = CleanFriendsTagUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "已删除了 " + CleanFriendsTagUtils.this.deletedFriendNum + " 个好友");
                                            PrintStream printStream = System.out;
                                            printStream.println("WS_BABY_deletedFriendNum11 = " + CleanFriendsTagUtils.this.deletedFriendNum);
                                            new PerformClickUtils2().check(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    if (CleanFriendsTagUtils.this.deletedFriendNum >= CleanFriendsTagUtils.this.deleteMaxNum) {
                                                        BaseServiceUtils.removeEndView(CleanFriendsTagUtils.this.mMyManager);
                                                    } else {
                                                        CleanFriendsTagUtils.this.initContactLabelEditUI();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    new PerformClickUtils2().check(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 150, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            CleanFriendsTagUtils.this.initContactLabelEditUI();
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            PerformClickUtils.performBack(CleanFriendsTagUtils.this.autoService);
                            CleanFriendsTagUtils.this.sleep(BannerConfig.DURATION);
                            CleanFriendsTagUtils.this.initContactLabelEditUI();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactLabelEditUI() {
        try {
            LogUtils.log("WS_BABY.ContactLabelEditUI.7");
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, this.isJumpExecute ? 100 : CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.ContactLabelEditUI.8");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CleanFriendsTagUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        LogUtils.log("WS_BAB_ContactLabelEditUI.100");
                        return;
                    }
                    LogUtils.log("WS_BABY.ContactLabelEditUI.9");
                    if (CleanFriendsTagUtils.this.nowNameNum != null) {
                        String access$300 = CleanFriendsTagUtils.this.nowNameNum;
                        if (access$300.equals(findViewIdList.size() + "")) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.10");
                            boolean unused = CleanFriendsTagUtils.this.isScrollBottom = true;
                        }
                    }
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "删除标签");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "添加成员");
                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "删除成员");
                    if (findNodeIsExistByText || findNodeIsExistByText2 || findNodeIsExistByText3) {
                        LogUtils.log("WS_BABY_ContactLabelEditUI.11");
                        boolean unused2 = CleanFriendsTagUtils.this.isScrollBottom = true;
                    }
                    LogUtils.log("WS_BABY_ContactLabelEditUI.22..startIndex." + CleanFriendsTagUtils.this.startIndex2 + ListUtils.DEFAULT_JOIN_SEPARATOR + findViewIdList.size());
                    if (CleanFriendsTagUtils.this.startIndex2 < findViewIdList.size() && MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CleanFriendsTagUtils.this.startIndex2);
                        LogUtils.log("WS_BABY_ContactLabelEditUI.33");
                        if (accessibilityNodeInfo == null) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.44");
                            return;
                        }
                        CleanFriendsTagUtils cleanFriendsTagUtils = CleanFriendsTagUtils.this;
                        String unused3 = cleanFriendsTagUtils.nowName = accessibilityNodeInfo.getText() + "";
                        Rect rect = new Rect();
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        CleanFriendsTagUtils cleanFriendsTagUtils2 = CleanFriendsTagUtils.this;
                        String unused4 = cleanFriendsTagUtils2.nowName = CleanFriendsTagUtils.this.nowName + rect.toString();
                        if (!CleanFriendsTagUtils.this.isScrollBottom || !CleanFriendsTagUtils.this.fansRecord.contains(CleanFriendsTagUtils.this.nowName)) {
                            CleanFriendsTagUtils.this.isJumpExecute = false;
                            CleanFriendsTagUtils.this.fansRecord.add(CleanFriendsTagUtils.this.nowName);
                            CleanFriendsTagUtils.access$1008(CleanFriendsTagUtils.this);
                            LogUtils.log("WS_BABY_ContactLabelEditUI.66");
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            CleanFriendsTagUtils.this.initContactInfoUIByTag();
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactLabelEditUI.55");
                        CleanFriendsTagUtils.access$1008(CleanFriendsTagUtils.this);
                        CleanFriendsTagUtils.this.isJumpExecute = true;
                        CleanFriendsTagUtils.this.initContactLabelEditUI();
                    } else if (CleanFriendsTagUtils.this.startIndex2 >= findViewIdList.size() && MyApplication.enforceable) {
                        if (!CleanFriendsTagUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.88");
                            PerformClickUtils.scroll(CleanFriendsTagUtils.this.autoService, BaseServiceUtils.default_list_id);
                            CleanFriendsTagUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "删除标签");
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "添加成员");
                            boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(CleanFriendsTagUtils.this.autoService, "删除成员");
                            if (findNodeIsExistByText4 || findNodeIsExistByText5 || findNodeIsExistByText6) {
                                LogUtils.log("WS_BABY_ContactLabelEditUI.99");
                                boolean unused5 = CleanFriendsTagUtils.this.isScrollBottom = true;
                            }
                            int unused6 = CleanFriendsTagUtils.this.startIndex2 = 5;
                            CleanFriendsTagUtils.this.initContactLabelEditUI();
                        } else if (CleanFriendsTagUtils.this.deleteTags == null || CleanFriendsTagUtils.this.deleteTags.size() <= 0) {
                            BaseServiceUtils.removeEndView(CleanFriendsTagUtils.this.mMyManager);
                        } else {
                            MyWindowManager myWindowManager = CleanFriendsTagUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已删除【 " + CleanFriendsTagUtils.this.nowTag + " 】中的全部好友");
                            LogUtils.log("WS_BABY_ContactLabelEditUI.77");
                            PerformClickUtils.performBack(CleanFriendsTagUtils.this.autoService);
                            CleanFriendsTagUtils.this.sleep(600);
                            int unused7 = CleanFriendsTagUtils.this.startIndex2 = 0;
                            CleanFriendsTagUtils.this.initContactLabelManagerUI();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeTagsMain(LinkedHashSet<String> linkedHashSet) {
        try {
            LogUtils.log("WS_BABY_executeTagsMain");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().checkString(this.autoService, "标签", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(CleanFriendsTagUtils.this.autoService, "标签");
                    CleanFriendsTagUtils.this.initContactLabelManagerUI();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_deletedFriendNum22 = " + this.deletedFriendNum);
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键删除标签好友", "已删除了" + this.deletedFriendNum + "个好友");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在一键删除标签好友，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CleanFriendsTagUtils.this.executeTagsMain(CleanFriendsTagUtils.this.deleteTags);
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
