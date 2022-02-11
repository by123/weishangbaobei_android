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

    public void middleViewDismiss() {
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

    private AutoNearbyPeoplesUtils() {
    }

    public static AutoNearbyPeoplesUtils getInstance() {
        instance = new AutoNearbyPeoplesUtils();
        return instance;
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

    public void executeMain() {
        try {
            if (this.isExecuteMain && MyApplication.enforceable) {
                this.isExecuteMain = false;
                new PerformClickUtils2().checkString(this.autoService, "发现", executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "发现");
                        LogUtils.log("WS_BABY_END_EXECUTED.0");
                        new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "附近的人", BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

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
                                                public void nuFind() {
                                                }

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
                        });
                    }
                });
            }
        } catch (Exception unused) {
        }
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
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeedSetting + executeSpeed + 150, 200, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.right_more_id);
                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "只看女生", (BaseServiceUtils.executeSpeedSetting / 8) + 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

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
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        AutoNearbyPeoplesUtils.this.exeTask(AutoNearbyPeoplesUtils.this.startInt);
                                    }
                                });
                            }
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_NearbyFriendsUI-2");
                }
            });
        }
    }

    public void exeTask(final int i) {
        try {
            if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY_exeTask");
                new PerformClickUtils2().checkNodeAllIds(this.autoService, nealy_list_id, nealy_list_name_id, executeSpeedSetting + executeSpeed, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        LogUtils.log("WS_BABY_exeTask2");
                        AutoNearbyPeoplesUtils.this.initAddNearbyPeoples(i);
                    }
                });
            }
        } catch (Exception unused) {
        }
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
                                list = PerformClickUtils.findViewIdList((AccessibilityService) AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.nealy_list_name_id);
                                if (list != null && list.size() > 0 && MyApplication.enforceable) {
                                    String str = list.get(list.size() - 1).getText() + "";
                                    if (str.equals(AutoNearbyPeoplesUtils.this.lastName)) {
                                        LogUtils.log("WS_BABY_END3333");
                                        BaseServiceUtils.removeEndView(AutoNearbyPeoplesUtils.this.mMyManager);
                                        z = false;
                                    } else {
                                        String unused2 = AutoNearbyPeoplesUtils.this.lastName = str;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception unused3) {
                }
            }
        }).start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01c1, code lost:
        r1 = r1.findAccessibilityNodeInfosByViewId(nealy_list_name_id);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addNearbyPeoples() {
        /*
            r6 = this;
            r0 = 0
            r6.isJumpedStart = r0
            com.wx.assistants.service.AutoService r1 = r6.autoService
            android.view.accessibility.AccessibilityNodeInfo r1 = r1.getRootInActiveWindow()
            if (r1 != 0) goto L_0x000c
            return
        L_0x000c:
            java.lang.String r2 = nealy_list_id
            java.util.List r1 = r1.findAccessibilityNodeInfosByViewId(r2)
            if (r1 == 0) goto L_0x0251
            int r2 = r1.size()
            if (r2 == 0) goto L_0x0251
            java.lang.Object r2 = r1.get(r0)
            android.view.accessibility.AccessibilityNodeInfo r2 = (android.view.accessibility.AccessibilityNodeInfo) r2
            int r2 = r2.getChildCount()
            if (r2 != 0) goto L_0x0028
            goto L_0x0251
        L_0x0028:
            java.lang.Object r1 = r1.get(r0)
            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
            int r2 = r6.startIndex
            int r3 = r1.getChildCount()
            r4 = 1
            if (r2 >= r3) goto L_0x017c
            boolean r2 = com.wx.assistants.application.MyApplication.enforceable
            if (r2 == 0) goto L_0x017c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "WS_BABY.addNearbyPeoples.1.index="
            r2.append(r3)
            int r3 = r6.startIndex
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.wx.assistants.utils.LogUtils.log(r2)
            int r2 = r6.startIndex
            android.view.accessibility.AccessibilityNodeInfo r2 = r1.getChild(r2)
            if (r2 != 0) goto L_0x005f
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.11"
            com.wx.assistants.utils.LogUtils.log(r0)
            return
        L_0x005f:
            java.lang.String r3 = nealy_list_name_id
            java.util.List r3 = r2.findAccessibilityNodeInfosByViewId(r3)
            if (r3 == 0) goto L_0x0168
            int r5 = r3.size()
            if (r5 <= 0) goto L_0x0168
            java.lang.String r5 = "WS_BABY.addNearbyPeoples.4"
            com.wx.assistants.utils.LogUtils.log(r5)
            java.lang.Object r0 = r3.get(r0)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            if (r0 == 0) goto L_0x0156
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.CharSequence r0 = r0.getText()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "WS_BABY.addNearbyPeoples.5"
            r3.append(r5)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.wx.assistants.utils.LogUtils.log(r3)
            if (r0 == 0) goto L_0x0144
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x00af
            goto L_0x0144
        L_0x00af:
            java.lang.String r3 = "WS_BABY.addNearbyPeoples.8"
            com.wx.assistants.utils.LogUtils.log(r3)
            java.lang.String r3 = "王卡申请"
            boolean r3 = r0.contains(r3)
            if (r3 != 0) goto L_0x0130
            java.lang.String r3 = "WS_BABY.addNearbyPeoples.9"
            com.wx.assistants.utils.LogUtils.log(r3)
            int r3 = r6.startIndex
            int r1 = r1.getChildCount()
            int r1 = r1 - r4
            if (r3 != r1) goto L_0x00cc
            r6.lastName = r0
        L_0x00cc:
            if (r0 == 0) goto L_0x0250
            java.lang.String r1 = ""
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0250
            boolean r0 = com.wx.assistants.application.MyApplication.enforceable
            if (r0 == 0) goto L_0x0250
            int r0 = r6.ffModel
            if (r0 != r4) goto L_0x0100
            int r0 = r6.ffModel
            int r1 = r6.ffModelStartTime
            int r3 = r6.ffModelEndTime
            int r0 = com.wx.assistants.utils.PerformClickUtils.getRandomTime(r0, r1, r3)
            r6.spaceTime = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WS_BABY.small_spaceTime="
            r0.append(r1)
            int r1 = r6.spaceTime
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
        L_0x0100:
            int r0 = r6.spaceTime
            if (r0 <= 0) goto L_0x011e
            int r0 = r6.successNum
            if (r0 <= 0) goto L_0x011e
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.11"
            com.wx.assistants.utils.LogUtils.log(r0)
            com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
            r0.<init>()
            int r1 = r6.spaceTime
            com.wx.assistants.service_utils.AutoNearbyPeoplesUtils$5 r3 = new com.wx.assistants.service_utils.AutoNearbyPeoplesUtils$5
            r3.<init>(r2)
            r0.countDown2(r1, r3)
            goto L_0x0250
        L_0x011e:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.12"
            com.wx.assistants.utils.LogUtils.log(r0)
            com.wx.assistants.utils.PerformClickUtils.performClick(r2)
            int r0 = r6.startIndex
            int r0 = r0 + r4
            r6.startIndex = r0
            r6.initContactInfoUI()
            goto L_0x0250
        L_0x0130:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.10"
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.String r0 = "WS_BABY_count<3"
            com.wx.assistants.utils.LogUtils.log(r0)
            int r0 = r6.startIndex
            int r0 = r0 + r4
            r6.startIndex = r0
            r6.addNearbyPeoples()
            goto L_0x0250
        L_0x0144:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.7"
            com.wx.assistants.utils.LogUtils.log(r0)
            com.wx.assistants.utils.PerformClickUtils.performClick(r2)
            int r0 = r6.startIndex
            int r0 = r0 + r4
            r6.startIndex = r0
            r6.initContactInfoUI()
            goto L_0x0250
        L_0x0156:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.6"
            com.wx.assistants.utils.LogUtils.log(r0)
            com.wx.assistants.utils.PerformClickUtils.performClick(r2)
            int r0 = r6.startIndex
            int r0 = r0 + r4
            r6.startIndex = r0
            r6.initContactInfoUI()
            goto L_0x0250
        L_0x0168:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.10"
            com.wx.assistants.utils.LogUtils.log(r0)
            java.lang.String r0 = "WS_BABY_count<3"
            com.wx.assistants.utils.LogUtils.log(r0)
            int r0 = r6.startIndex
            int r0 = r0 + r4
            r6.startIndex = r0
            r6.addNearbyPeoples()
            goto L_0x0250
        L_0x017c:
            int r2 = r6.startIndex
            int r1 = r1.getChildCount()
            if (r2 < r1) goto L_0x0250
            boolean r1 = com.wx.assistants.application.MyApplication.enforceable
            if (r1 == 0) goto L_0x0250
            java.lang.String r1 = "WS_BABY.addNearbyPeoples.2"
            com.wx.assistants.utils.LogUtils.log(r1)
            com.wx.assistants.service.AutoService r1 = r6.autoService
            android.view.accessibility.AccessibilityNodeInfo r1 = r1.getRootInActiveWindow()
            if (r1 != 0) goto L_0x0196
            return
        L_0x0196:
            java.lang.String r2 = nealy_list_id
            java.util.List r1 = r1.findAccessibilityNodeInfosByViewId(r2)
            if (r1 == 0) goto L_0x0250
            int r2 = r1.size()
            if (r2 <= 0) goto L_0x0250
            boolean r2 = com.wx.assistants.application.MyApplication.enforceable
            if (r2 == 0) goto L_0x0250
            java.lang.Object r1 = r1.get(r0)
            android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
            r2 = 4096(0x1000, float:5.74E-42)
            r1.performAction(r2)
            int r1 = com.wx.assistants.application.MyApplication.SCROLL_SPEED
            r6.sleep(r1)
            com.wx.assistants.service.AutoService r1 = r6.autoService
            android.view.accessibility.AccessibilityNodeInfo r1 = r1.getRootInActiveWindow()
            if (r1 != 0) goto L_0x01c1
            return
        L_0x01c1:
            java.lang.String r2 = nealy_list_name_id
            java.util.List r1 = r1.findAccessibilityNodeInfosByViewId(r2)
            if (r1 == 0) goto L_0x0250
            int r2 = r1.size()
            if (r2 <= 0) goto L_0x0250
            boolean r2 = com.wx.assistants.application.MyApplication.enforceable
            if (r2 == 0) goto L_0x0250
            int r2 = r1.size()
            int r2 = r2 - r4
            java.lang.Object r2 = r1.get(r2)
            android.view.accessibility.AccessibilityNodeInfo r2 = (android.view.accessibility.AccessibilityNodeInfo) r2
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = r6.lastName
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x01f1
            r6.isScrollBottom = r4
            goto L_0x01f3
        L_0x01f1:
            r6.isScrollBottom = r0
        L_0x01f3:
            if (r1 == 0) goto L_0x0250
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0250
            int r0 = r6.ffModel
            if (r0 != r4) goto L_0x0221
            int r0 = r6.ffModel
            int r2 = r6.ffModelStartTime
            int r3 = r6.ffModelEndTime
            int r0 = com.wx.assistants.utils.PerformClickUtils.getRandomTime(r0, r2, r3)
            r6.spaceTime = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "WS_BABY.small_spaceTime="
            r0.append(r2)
            int r2 = r6.spaceTime
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.wx.assistants.utils.LogUtils.log(r0)
        L_0x0221:
            int r0 = r6.spaceTime
            if (r0 <= 0) goto L_0x0239
            int r0 = r6.successNum
            if (r0 <= 0) goto L_0x0239
            com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
            r0.<init>()
            int r2 = r6.spaceTime
            com.wx.assistants.service_utils.AutoNearbyPeoplesUtils$6 r3 = new com.wx.assistants.service_utils.AutoNearbyPeoplesUtils$6
            r3.<init>(r1)
            r0.countDown2(r2, r3)
            goto L_0x0250
        L_0x0239:
            r0 = 2
            r6.startIndex = r0
            if (r1 == 0) goto L_0x0250
            int r0 = r1.size()
            if (r0 <= r4) goto L_0x0250
            java.lang.Object r0 = r1.get(r4)
            android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0
            com.wx.assistants.utils.PerformClickUtils.performClick(r0)
            r6.initContactInfoUI()
        L_0x0250:
            return
        L_0x0251:
            java.lang.String r0 = "WS_BABY.addNearbyPeoples.0"
            com.wx.assistants.utils.LogUtils.log(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoNearbyPeoplesUtils.addNearbyPeoples():void");
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
                                public void nuFind() {
                                }

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
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        AutoNearbyPeoplesUtils.this.executeTask();
                                                    }
                                                });
                                                return;
                                            }
                                            new PerformClickUtils2().checkNodeIds(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    if (i == 0) {
                                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                                        LogUtils.log("WS_BABY_ContactInfoUI_6");
                                                    } else if (i == 1) {
                                                        PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                                        LogUtils.log("WS_BABY_ContactInfoUI_7");
                                                    }
                                                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel, (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                        public void nuFind() {
                                                        }

                                                        public void find(int i) {
                                                            LogUtils.log("WS_BABY_ContactInfoUI_8");
                                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel);
                                                            AutoNearbyPeoplesUtils.this.sleep(100);
                                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                            new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "保存", (BaseServiceUtils.executeSpeedSetting / 8) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                                public void nuFind() {
                                                                }

                                                                public void find(int i) {
                                                                    LogUtils.log("WS_BABY_ContactInfoUI_9");
                                                                    PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                                    LogUtils.log("WS_BABY_ContactInfoUI_10");
                                                                    new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                        public void nuFind() {
                                                                        }

                                                                        public void find(int i) {
                                                                            AutoNearbyPeoplesUtils.this.executeTask();
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                        }
                                                    });
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
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                AutoNearbyPeoplesUtils.this.executeTask();
                                            }
                                        });
                                    } else if (AutoNearbyPeoplesUtils.this.remarkLabel != null && !"".equals(AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                        AutoNearbyPeoplesUtils.this.sleep(100);
                                        if (PerformClickUtils.findNodeIsExistByText(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel)) {
                                            PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    AutoNearbyPeoplesUtils.this.executeTask();
                                                }
                                            });
                                            return;
                                        }
                                        new PerformClickUtils2().checkNodeIds(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                            public void nuFind() {
                                            }

                                            public void find(int i) {
                                                if (i == 0) {
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                                } else if (i == 1) {
                                                    PerformClickUtils.findViewIdAndClick(AutoNearbyPeoplesUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                                }
                                                new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel, (BaseServiceUtils.executeSpeedSetting / 8) + 200, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                                    public void nuFind() {
                                                    }

                                                    public void find(int i) {
                                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, AutoNearbyPeoplesUtils.this.remarkLabel);
                                                        AutoNearbyPeoplesUtils.this.sleep(300);
                                                        PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                        new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "保存", (BaseServiceUtils.executeSpeedSetting / 4) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                PerformClickUtils.findTextAndClick(AutoNearbyPeoplesUtils.this.autoService, "保存");
                                                                new PerformClickUtils2().checkString(AutoNearbyPeoplesUtils.this.autoService, "打招呼", (BaseServiceUtils.executeSpeedSetting / 8) + 300, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                    public void nuFind() {
                                                                    }

                                                                    public void find(int i) {
                                                                        AutoNearbyPeoplesUtils.this.executeTask();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
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

    public void executeTask() {
        LogUtils.log("WS_BABY_ContactInfoUI_1");
        PerformClickUtils.findTextAndClick(this.autoService, "打招呼");
        new PerformClickUtils2().checkString(this.autoService, "发送", (executeSpeedSetting / 8) + 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                AutoNearbyPeoplesUtils.this.initSayHiEditUI();
            }
        });
    }

    public void initSayHiEditUI() {
        LogUtils.log("WS_BABY_SayHiEditUI");
        sendText(this.sayContent);
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
                public void nuFind() {
                }

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
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "自动添加附近人", "已打招呼 " + this.successNum + " 人 ,添加失败 " + this.failNum + " 人");
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

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
