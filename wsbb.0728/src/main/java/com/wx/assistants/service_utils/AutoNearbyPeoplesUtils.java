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
import java.util.List;

public class AutoNearbyPeoplesUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoNearbyPeoplesUtils instance;
    /* access modifiers changed from: private */
    public int failNum = 0;
    private int ffModel = 0;
    private int ffModelEndTime = 10;
    private int ffModelStartTime = 0;
    /* access modifiers changed from: private */
    public Gender gender;
    /* access modifiers changed from: private */
    public boolean isDesignC = true;
    private boolean isExecuteMain = true;
    /* access modifiers changed from: private */
    public boolean isExecutedMore = false;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    private boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public String lastName;
    private int maxOperaNum = 0;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public String remarkPrefix = "";
    private String sayContent;
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt;
    /* access modifiers changed from: private */
    public int successNum = 0;

    private AutoNearbyPeoplesUtils() {
    }

    static /* synthetic */ int access$508(AutoNearbyPeoplesUtils autoNearbyPeoplesUtils) {
        int i = autoNearbyPeoplesUtils.startIndex;
        autoNearbyPeoplesUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(AutoNearbyPeoplesUtils autoNearbyPeoplesUtils) {
        int i = autoNearbyPeoplesUtils.scrollCount;
        autoNearbyPeoplesUtils.scrollCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$610(AutoNearbyPeoplesUtils autoNearbyPeoplesUtils) {
        int i = autoNearbyPeoplesUtils.scrollCount;
        autoNearbyPeoplesUtils.scrollCount = i - 1;
        return i;
    }

    public static AutoNearbyPeoplesUtils getInstance() {
        instance = new AutoNearbyPeoplesUtils();
        return instance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01c7, code lost:
        r1 = r0.findAccessibilityNodeInfosByViewId(nealy_list_name_id);
     */
    public void addNearbyPeoples() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
        this.isJumpedStart = false;
        AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
        if (rootInActiveWindow != null) {
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(nealy_list_id);
            if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() == 0 || findAccessibilityNodeInfosByViewId3.get(0).getChildCount() == 0) {
                LogUtils.log("WS_BABY.addNearbyPeoples.0");
                return;
            }
            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId3.get(0);
            if (this.startIndex < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.addNearbyPeoples.1.index=" + this.startIndex);
                final AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(this.startIndex);
                if (child == null) {
                    LogUtils.log("WS_BABY.addNearbyPeoples.11");
                    return;
                }
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = child.findAccessibilityNodeInfosByViewId(nealy_list_name_id);
                if (findAccessibilityNodeInfosByViewId4 == null || findAccessibilityNodeInfosByViewId4.size() <= 0) {
                    LogUtils.log("WS_BABY.addNearbyPeoples.10");
                    LogUtils.log("WS_BABY_count<3");
                    this.startIndex++;
                    addNearbyPeoples();
                    return;
                }
                LogUtils.log("WS_BABY.addNearbyPeoples.4");
                AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId4.get(0);
                if (accessibilityNodeInfo2 != null) {
                    String str = accessibilityNodeInfo2.getText() + "";
                    LogUtils.log("WS_BABY.addNearbyPeoples.5" + str);
                    if (str == null || "".equals(str)) {
                        LogUtils.log("WS_BABY.addNearbyPeoples.7");
                        PerformClickUtils.performClick(child);
                        this.startIndex++;
                        initContactInfoUI();
                        return;
                    }
                    LogUtils.log("WS_BABY.addNearbyPeoples.8");
                    if (!str.contains("王卡申请")) {
                        LogUtils.log("WS_BABY.addNearbyPeoples.9");
                        if (this.startIndex == accessibilityNodeInfo.getChildCount() - 1) {
                            this.lastName = str;
                        }
                        if (str != null && !"".equals(str) && MyApplication.enforceable) {
                            if (this.ffModel == 1) {
                                this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
                                LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
                            }
                            if (this.spaceTime <= 0 || this.successNum <= 0) {
                                LogUtils.log("WS_BABY.addNearbyPeoples.12");
                                PerformClickUtils.performClick(child);
                                this.startIndex++;
                                initContactInfoUI();
                                return;
                            }
                            LogUtils.log("WS_BABY.addNearbyPeoples.11");
                            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    PerformClickUtils.performClick(child);
                                    AutoNearbyPeoplesUtils.access$508(AutoNearbyPeoplesUtils.this);
                                    AutoNearbyPeoplesUtils.this.initContactInfoUI();
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = AutoNearbyPeoplesUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已打招呼 " + AutoNearbyPeoplesUtils.this.successNum + " 人 ,添加失败 " + AutoNearbyPeoplesUtils.this.failNum + " 人\n正在延时等待，倒计时 " + i + " 秒");
                                }
                            });
                            return;
                        }
                        return;
                    }
                    LogUtils.log("WS_BABY.addNearbyPeoples.10");
                    LogUtils.log("WS_BABY_count<3");
                    this.startIndex++;
                    addNearbyPeoples();
                    return;
                }
                LogUtils.log("WS_BABY.addNearbyPeoples.6");
                PerformClickUtils.performClick(child);
                this.startIndex++;
                initContactInfoUI();
            } else if (this.startIndex >= accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.addNearbyPeoples.2");
                AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow2 != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(nealy_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                    sleep(MyApplication.SCROLL_SPEED);
                    AccessibilityNodeInfo rootInActiveWindow3 = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow3 != null && findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                        if (findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1).getText().toString().equals(this.lastName)) {
                            this.isScrollBottom = true;
                        } else {
                            this.isScrollBottom = false;
                        }
                        if (findAccessibilityNodeInfosByViewId2 != null && !findAccessibilityNodeInfosByViewId2.isEmpty()) {
                            if (this.ffModel == 1) {
                                this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
                                LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
                            }
                            if (this.spaceTime <= 0 || this.successNum <= 0) {
                                this.startIndex = 2;
                                if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 1) {
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(1));
                                    initContactInfoUI();
                                    return;
                                }
                                return;
                            }
                            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    int unused = AutoNearbyPeoplesUtils.this.startIndex = 2;
                                    if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 1) {
                                        PerformClickUtils.performClick((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId2.get(1));
                                        AutoNearbyPeoplesUtils.this.initContactInfoUI();
                                    }
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = AutoNearbyPeoplesUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "已打招呼 " + AutoNearbyPeoplesUtils.this.successNum + " 人 ,添加失败 " + AutoNearbyPeoplesUtils.this.failNum + " 人\n正在延时等待，倒计时 " + i + " 秒");
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在自动添加附近人，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                    public void run() {
                        AutoNearbyPeoplesUtils.this.executeMain();
                    }
                }, 10);
            }
        }).start();
    }

    public void exeTask(final int i) {
        try {
            if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY_exeTask");
                new PerformClickUtils2().checkNodeAllIds(this.autoService, nealy_list_id, nealy_list_name_id, executeSpeed + executeSpeedSetting, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_exeTask2");
                        AutoNearbyPeoplesUtils.this.initAddNearbyPeoples(i);
                    }

                    public void nuFind() {
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public void executeMain() {
        try {
            if (this.isExecuteMain && MyApplication.enforceable) {
                this.isExecuteMain = false;
                new PerformClickUtils2().checkString(this.autoService, "发现", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "发现");
                        LogUtils.log("WS_BABY_END_EXECUTED.0");
                        new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "附近的人", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                LogUtils.log("WS_BABY_END_EXECUTED.00");
                                PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "附近的人");
                                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoNearbyPeoplesUtils.this.autoService, "始终允许", "开始查看", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 7, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            LogUtils.log("WS_BABY_END_EXECUTED.2");
                                            PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nearly_0);
                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "始终允许");
                                            new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoNearbyPeoplesUtils.this.autoService, "开始查看", "查看附近的人", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    LogUtils.log("WS_BABY_NearbyFriendsIntroUI");
                                                    PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "查看附近的人");
                                                    PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "开始查看");
                                                    AutoNearbyPeoplesUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                    LogUtils.log("WS_BABY_END_EXECUTED.3");
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nearly_1);
                                                    AutoNearbyPeoplesUtils.this.sleep(100);
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                    AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                        } else if (i == 1) {
                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "开始查看");
                                            AutoNearbyPeoplesUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                            LogUtils.log("WS_BABY_END_EXECUTED.3");
                                            PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nearly_1);
                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                            PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                            AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
                                        }
                                    }

                                    public void nuFind() {
                                        LogUtils.log("WS_BABY_END_EXECUTED.3");
                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nearly_1);
                                        AutoNearbyPeoplesUtils.this.sleep(100);
                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                        AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
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
        } catch (Exception e) {
        }
    }

    public void executeTask() {
        LogUtils.log("WS_BABY_ContactInfoUI_1");
        PerformClickUtils.findTextAndClick(this.autoService, "打招呼");
        new PerformClickUtils2().checkString(this.autoService, "发送", (executeSpeedSetting / 8) + 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                AutoNearbyPeoplesUtils.this.initSayHiEditUI();
            }

            public void nuFind() {
            }
        });
    }

    public void initAddNearbyPeoples(final int i) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (i <= 1 || !AutoNearbyPeoplesUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        AutoNearbyPeoplesUtils.this.addNearbyPeoples();
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + i + "开始");
                    List<AccessibilityNodeInfo> list = null;
                    boolean z = true;
                    while (z && MyApplication.enforceable) {
                        if (list == null) {
                            list = PerformClickUtils.findViewIdList((AccessibilityService) AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nealy_list_name_id);
                        }
                        if (list != null && list.size() > 0 && MyApplication.enforceable) {
                            if (AutoNearbyPeoplesUtils.this.startIndex < list.size() && MyApplication.enforceable) {
                                AutoNearbyPeoplesUtils.access$608(AutoNearbyPeoplesUtils.this);
                                if (AutoNearbyPeoplesUtils.this.scrollCount > i) {
                                    AutoNearbyPeoplesUtils.this.addNearbyPeoples();
                                    return;
                                }
                                AutoNearbyPeoplesUtils.access$508(AutoNearbyPeoplesUtils.this);
                            } else if (AutoNearbyPeoplesUtils.this.startIndex >= list.size() && MyApplication.enforceable) {
                                PerformClickUtils.scroll(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nealy_list_id);
                                if (AutoNearbyPeoplesUtils.this.scrollCount > 0) {
                                    AutoNearbyPeoplesUtils.access$610(AutoNearbyPeoplesUtils.this);
                                }
                                int unused = AutoNearbyPeoplesUtils.this.startIndex = 0;
                                AutoNearbyPeoplesUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nealy_list_name_id);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                                    list = findViewIdList;
                                } else {
                                    String str = findViewIdList.get(findViewIdList.size() - 1).getText() + "";
                                    if (str.equals(AutoNearbyPeoplesUtils.this.lastName)) {
                                        LogUtils.log("WS_BABY_END3333");
                                        BaseServiceUtils.removeEndView(AutoNearbyPeoplesUtils.this.mMyManager);
                                        list = findViewIdList;
                                        z = false;
                                    } else {
                                        String unused2 = AutoNearbyPeoplesUtils.this.lastName = str;
                                        list = findViewIdList;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void initContactInfoUI() {
        LogUtils.log("WS_BABY_ContactInfoUI_0");
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "自动添加附近人\n已打招呼 " + this.successNum + " 人 ,添加失败 " + this.failNum + " 人");
        new PerformClickUtils2().checkString(this.autoService, "打招呼", executeSpeed + executeSpeedSetting, 100, 15, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI_1");
                if ((AutoNearbyPeoplesUtils.this.remarkPrefix == null || "".equals(AutoNearbyPeoplesUtils.this.remarkPrefix)) && (AutoNearbyPeoplesUtils.this.remarkLabel == null || "".equals(AutoNearbyPeoplesUtils.this.remarkLabel))) {
                    AutoNearbyPeoplesUtils.this.executeTask();
                } else {
                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "设置备注和标签", 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_ContactInfoUI_2");
                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "设置备注和标签");
                            new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "保存", BaseServiceUtils.executeSpeed + 150 + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (AutoNearbyPeoplesUtils.this.remarkPrefix != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkPrefix) && AutoNearbyPeoplesUtils.this.remarkLabel != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                        LogUtils.log("WS_BABY_ContactInfoUI_3");
                                        if (AutoNearbyPeoplesUtils.this.remarkPrefix != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkPrefix)) {
                                            LogUtils.log("WS_BABY_ContactInfoUI_4");
                                            PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                            PerformClickUtils.inputPrefixText(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkPrefix);
                                        }
                                        if (AutoNearbyPeoplesUtils.this.remarkLabel != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                            LogUtils.log("WS_BABY_ContactInfoUI_5");
                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                            if (PerformClickUtils.findNodeIsExistByText(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                                PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                LogUtils.log("WS_BABY_ContactInfoUI_10");
                                                new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", BaseServiceUtils.executeSpeed + 100 + (BaseServiceUtils.executeSpeedSetting / 8), 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        AutoNearbyPeoplesUtils.this.executeTask();
                                                    }

                                                    public void nuFind() {
                                                    }
                                                });
                                                return;
                                            }
                                            new PerformClickUtils2().checkNodeIds(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    if (i == 0) {
                                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                                        LogUtils.log("WS_BABY_ContactInfoUI_6");
                                                    } else if (i == 1) {
                                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                                        LogUtils.log("WS_BABY_ContactInfoUI_7");
                                                    }
                                                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel, (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                        public void find(int i) {
                                                            LogUtils.log("WS_BABY_ContactInfoUI_8");
                                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel);
                                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                            new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "保存", (BaseServiceUtils.executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    LogUtils.log("WS_BABY_ContactInfoUI_9");
                                                                    PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                                    LogUtils.log("WS_BABY_ContactInfoUI_10");
                                                                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                        public void find(int i) {
                                                                            AutoNearbyPeoplesUtils.this.executeTask();
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
                                        }
                                    } else if (AutoNearbyPeoplesUtils.this.remarkPrefix != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkPrefix)) {
                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                                        AutoNearbyPeoplesUtils.this.sleep(100);
                                        PerformClickUtils.inputPrefixText(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkPrefix);
                                        AutoNearbyPeoplesUtils.this.sleep(100);
                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                        new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                AutoNearbyPeoplesUtils.this.executeTask();
                                            }

                                            public void nuFind() {
                                            }
                                        });
                                    } else if (AutoNearbyPeoplesUtils.this.remarkLabel != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                        AutoNearbyPeoplesUtils.this.sleep(100);
                                        if (PerformClickUtils.findNodeIsExistByText(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    AutoNearbyPeoplesUtils.this.executeTask();
                                                }

                                                public void nuFind() {
                                                }
                                            });
                                            return;
                                        }
                                        new PerformClickUtils2().checkNodeIds(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                if (i == 0) {
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                                } else if (i == 1) {
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                                }
                                                new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel, (BaseServiceUtils.executeSpeedSetting / 8) + 200, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel);
                                                        AutoNearbyPeoplesUtils.this.sleep(300);
                                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                        new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "保存", (BaseServiceUtils.executeSpeedSetting / 4) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                            public void find(int i) {
                                                                PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                                new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                    public void find(int i) {
                                                                        AutoNearbyPeoplesUtils.this.executeTask();
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
                                    }
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            AutoNearbyPeoplesUtils.this.executeTask();
                        }
                    });
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI_2122");
                PerformClickUtils.performBack(AutoNearbyPeoplesUtils.this.autoService);
                AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel) {
        LogUtils.log("WS_BABY_LauncherUI_initData");
        this.startIndex = 0;
        this.successNum = 0;
        this.isDesignC = true;
        this.isExecutedMore = false;
        this.isJumpedStart = true;
        this.isExecuteMain = true;
        this.scrollCount = 0;
        this.lastName = "";
        this.failNum = 0;
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.isScrollBottom = false;
        this.gender = operationParameterModel.getSex();
        this.remarkPrefix = operationParameterModel.getRemarkPrefix();
        this.remarkLabel = operationParameterModel.getSingLabelStr();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.startInt = operationParameterModel.getDistance();
        this.sayContent = operationParameterModel.getSayContent();
        this.maxOperaNum = operationParameterModel.getMaxOperaNum();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initNearbyFriendsUI() {
        LogUtils.log("WS_BABY_NearbyFriendsUI");
        if (this.isScrollBottom) {
            LogUtils.log("WS_BABY_NearbyFriendsUI_END");
            removeEndView(this.mMyManager);
        } else if (this.isExecutedMore || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_NearbyFriendsUI-0");
            sleep(100);
            exeTask(this.startInt);
        } else {
            LogUtils.log("WS_BABY_NearbyFriendsUI-1");
            this.isDesignC = true;
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeed + 150 + executeSpeedSetting, 200, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.right_more_id);
                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "只看女生", (BaseServiceUtils.executeSpeedSetting / 8) + 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (AutoNearbyPeoplesUtils.this.isDesignC && MyApplication.enforceable) {
                                boolean unused = AutoNearbyPeoplesUtils.this.isDesignC = false;
                                boolean unused2 = AutoNearbyPeoplesUtils.this.isExecutedMore = true;
                                if (AutoNearbyPeoplesUtils.this.gender.getValue() != null && !AutoNearbyPeoplesUtils.this.gender.getValue().isEmpty()) {
                                    if (AutoNearbyPeoplesUtils.this.gender.getValue().equals("ALL") && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_NearbyFriendsUI-ALL");
                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "查看全部");
                                    } else if (AutoNearbyPeoplesUtils.this.gender.getValue().equals("MAN") && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_NearbyFriendsUI-MAN");
                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "只看男生");
                                    } else if (AutoNearbyPeoplesUtils.this.gender.getValue().equals("WOMEN") && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_NearbyFriendsUI-WOMEN");
                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "只看女生");
                                    }
                                }
                                new PerformClickUtils2().checkNilString(AutoNearbyPeoplesUtils.this.autoService, "正在查找附近的人", (BaseServiceUtils.executeSpeedSetting / 4) + SocializeConstants.CANCLE_RESULTCODE, 100, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        AutoNearbyPeoplesUtils.this.exeTask(AutoNearbyPeoplesUtils.this.startInt);
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

                public void nuFind() {
                    LogUtils.log("WS_BABY_NearbyFriendsUI-2");
                }
            });
        }
    }

    public void initSayHiEditUI() {
        LogUtils.log("WS_BABY_SayHiEditUI");
        sendText(this.sayContent);
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "自动添加附近人", "已打招呼 " + this.successNum + " 人 ,添加失败 " + this.failNum + " 人");
    }

    public void sendText(String str) {
        try {
            LogUtils.log("WS_BABY_sendText");
            if (str != null && !"".equals(str) && MyApplication.enforceable) {
                PerformClickUtils.findViewByIdAndPasteContent(this.autoService, input_say_content, str);
            }
            LogUtils.log("WS_BABY_SEND_TEXT1");
            sleep(150);
            PerformClickUtils.findTextAndClick(this.autoService, "发送");
            this.successNum++;
            LogUtils.log("WS_BABY_SEND_TEXT2.successNum=" + this.successNum + ",maxOperaNum=" + this.maxOperaNum);
            if (this.successNum >= this.maxOperaNum) {
                removeEndView(this.mMyManager);
            } else {
                updateBottomText(this.mMyManager, "自动添加附近人\n已打招呼 " + this.successNum + " 人 ,添加失败 " + this.failNum + " 人");
            }
            LogUtils.log("WS_BABY_SEND_TEXT2——正在发送");
            new PerformClickUtils2().checkNilString(this.autoService, "正在发送", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_SEND_TEXT2——正在发送——end");
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoNearbyPeoplesUtils.this.autoService, "来自附近的人", "设置备注和标签", 100, 100, 6, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            AutoNearbyPeoplesUtils.this.sleep(100);
                            PerformClickUtils.performBack(AutoNearbyPeoplesUtils.this.autoService);
                            AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
                        }

                        public void nuFind() {
                            AutoNearbyPeoplesUtils.this.sleep(100);
                            if (PerformClickUtils.findNodeIsExistByText(AutoNearbyPeoplesUtils.this.autoService, "说句话")) {
                                LogUtils.log("WS_BABY_SEND_TEXT2——正在发送——end-验证失败");
                                PerformClickUtils.performBack(AutoNearbyPeoplesUtils.this.autoService);
                                AutoNearbyPeoplesUtils.this.sleep(600);
                                PerformClickUtils.performBack(AutoNearbyPeoplesUtils.this.autoService);
                                AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
                                return;
                            }
                            PerformClickUtils.performBack(AutoNearbyPeoplesUtils.this.autoService);
                            AutoNearbyPeoplesUtils.this.initNearbyFriendsUI();
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
