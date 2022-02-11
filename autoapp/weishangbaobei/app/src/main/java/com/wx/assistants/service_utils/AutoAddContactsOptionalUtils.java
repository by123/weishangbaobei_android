package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SQLiteUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class AutoAddContactsOptionalUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddContactsOptionalUtils instance;
    private int breakAdd = 0;
    /* access modifiers changed from: private */
    public String currentContactName = "";
    /* access modifiers changed from: private */
    public String currentContactPhone = "";
    /* access modifiers changed from: private */
    public ArrayList<ContactBean> fans = new ArrayList<>();
    private int ffModel = 0;
    private int ffModelEndTime = 10;
    private int ffModelStartTime = 0;
    private int maxNum = 20;
    /* access modifiers changed from: private */
    public String middleString = "";
    /* access modifiers changed from: private */
    public int recordSearchNum = 1;
    private boolean remarkPrefix;
    private String sayContent;
    private int spaceTime = 0;

    public void middleViewDismiss() {
    }

    private AutoAddContactsOptionalUtils() {
    }

    public static AutoAddContactsOptionalUtils getInstance() {
        instance = new AutoAddContactsOptionalUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.middleString = "";
        this.recordSearchNum = 0;
        this.currentContactPhone = "";
        this.currentContactName = "";
        this.breakAdd = operationParameterModel.isBreakAdd();
        this.fans = operationParameterModel.getAddressBookPhones();
        this.sayContent = operationParameterModel.getSayContent();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.remarkPrefix = operationParameterModel.isAutoRemarkContactsName();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstContactInfo() {
        try {
            if (this.breakAdd == 0) {
                LogUtils.log("WS_BABY.ContactInfoUI.2");
                MyWindowManager myWindowManager = this.mMyManager;
                updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
                LogUtils.log("WS_BABY.ContactInfoUI.9");
                addContact();
                return;
            }
            removeBottomView(this.mMyManager);
            updateBottomOperationLayout(this.mMyManager, "由于您设置了断点添加，请执行断点操作。", "跳过，执行下一个", new View.OnClickListener() {
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                BaseServiceUtils.removeBottomOperationView(AutoAddContactsOptionalUtils.this.mMyManager);
                                MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                BaseServiceUtils.showBottomView(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                                PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        AutoAddContactsOptionalUtils.this.executeMain();
                                    }
                                });
                            } catch (Exception unused) {
                            }
                        }
                    }).start();
                }
            }, "允许添加", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(AutoAddContactsOptionalUtils.this.mMyManager);
                    LogUtils.log("WS_BABY.ContactInfoUI.222222222");
                    MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                    AutoAddContactsOptionalUtils.this.addContact();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(11:8|9|10|11|(1:15)|20|21|22|(1:24)|29|34)(2:30|35)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008b A[Catch:{ Exception -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014 A[Catch:{ Exception -> 0x00bc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inputSayContent() {
        /*
            r4 = this;
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.0"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.utils.SQLiteUtils r0 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x0010 }
            java.lang.String r1 = r4.currentContactPhone     // Catch:{ Exception -> 0x0010 }
            java.lang.String r2 = "待好友通过"
            r0.updateContact(r1, r2)     // Catch:{ Exception -> 0x0010 }
        L_0x0010:
            boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00bc }
            if (r0 == 0) goto L_0x008b
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.1"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00bc }
            r0 = 50
            java.lang.String r1 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x0040 }
            if (r1 != 0) goto L_0x0045
            java.lang.String r1 = "WS_BABY.SayHiWithSnsPermissionUI.111"
            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x0040 }
            java.lang.String r2 = input_say_content     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x0040 }
            r4.sleep(r0)     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x0040 }
            java.lang.String r2 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.utils.PerformClickUtils.inputText(r1, r2)     // Catch:{ Exception -> 0x0040 }
            goto L_0x0045
        L_0x0040:
            java.lang.String r1 = "WS_BABY.SayHiWithSnsPermissionUI.sayContent.error"
            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x00bc }
        L_0x0045:
            java.lang.String r1 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x00bc }
            boolean r1 = r4.remarkPrefix     // Catch:{ Exception -> 0x0060 }
            if (r1 == 0) goto L_0x0065
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x0060 }
            java.lang.String r2 = input_remark     // Catch:{ Exception -> 0x0060 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x0060 }
            r4.sleep(r0)     // Catch:{ Exception -> 0x0060 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x0060 }
            java.lang.String r1 = r4.currentContactName     // Catch:{ Exception -> 0x0060 }
            com.wx.assistants.utils.PerformClickUtils.inputPrefixText(r0, r1)     // Catch:{ Exception -> 0x0060 }
            goto L_0x0065
        L_0x0060:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00bc }
        L_0x0065:
            int r0 = executeSpeed     // Catch:{ Exception -> 0x00bc }
            int r1 = executeSpeedSetting     // Catch:{ Exception -> 0x00bc }
            int r0 = r0 + r1
            r4.sleep(r0)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.3"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x00bc }
            java.lang.String r1 = complete_id     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x00bc }
            r0.<init>()     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x00bc }
            r2 = 300(0x12c, float:4.2E-43)
            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$3 r3 = new com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$3     // Catch:{ Exception -> 0x00bc }
            r3.<init>()     // Catch:{ Exception -> 0x00bc }
            r0.checkIsSending(r1, r2, r3)     // Catch:{ Exception -> 0x00bc }
            goto L_0x00c0
        L_0x008b:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00bc }
            com.wx.assistants.service.MyWindowManager r0 = r4.mMyManager     // Catch:{ Exception -> 0x00bc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            r1.<init>()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "自选添加手机联系人\n已检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            int r2 = r4.recordSearchNum     // Catch:{ Exception -> 0x00bc }
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = " 人，待检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.util.ArrayList<com.wx.assistants.Enity.ContactBean> r2 = r4.fans     // Catch:{ Exception -> 0x00bc }
            int r2 = r2.size()     // Catch:{ Exception -> 0x00bc }
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = " 人"
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00bc }
            updateBottomText(r0, r1)     // Catch:{ Exception -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.inputSayContent():void");
    }

    public void addContact() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    LogUtils.log("WS_BABY_添加到通讯录.0");
                    if (PerformClickUtils.findNodeIsExistByText(AutoAddContactsOptionalUtils.this.autoService, "添加到通讯录")) {
                        LogUtils.log("WS_BABY_添加到通讯录.1");
                        AutoAddContactsOptionalUtils.this.sleep(100);
                        PerformClickUtils.findTextAndClick(AutoAddContactsOptionalUtils.this.autoService, "添加到通讯录");
                        LogUtils.log("WS_BABY_添加到通讯录.1.1");
                        new PerformClickUtils2().checkNilString(AutoAddContactsOptionalUtils.this.autoService, "正在添加", 1500, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                new PerformClickUtils2().checkAddContact(AutoAddContactsOptionalUtils.this.autoService, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    public void find(int i) {
                                        if (i == 0) {
                                            try {
                                                SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "已添加");
                                            } catch (Exception unused) {
                                            }
                                            LogUtils.log("WS_BABY_添加到通讯录_END_1");
                                            MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                                            PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    AutoAddContactsOptionalUtils.this.executeMain();
                                                }
                                            });
                                        } else if (i == 1) {
                                            try {
                                                SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "添加失败");
                                            } catch (Exception unused2) {
                                            }
                                            MyWindowManager myWindowManager2 = AutoAddContactsOptionalUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager2, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                                            LogUtils.log("WS_BABY_添加到通讯录_END1111122222");
                                            PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    AutoAddContactsOptionalUtils.this.executeMain();
                                                }
                                            });
                                        } else if (i == 2) {
                                            LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                            AutoAddContactsOptionalUtils.this.inputSayContent();
                                        } else if (i == 3) {
                                            LogUtils.log("WS_BABY_添加到通讯录.dialog");
                                            MyWindowManager myWindowManager3 = AutoAddContactsOptionalUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager3, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                                            PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    AutoAddContactsOptionalUtils.this.executeMain();
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        });
                        return;
                    }
                    try {
                        SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "已添加");
                    } catch (Exception unused) {
                    }
                    LogUtils.log("WS_BABY_发消息@Back1");
                    PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddContactsOptionalUtils.this.executeMain();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void initFirstFTSMain() {
        LogUtils.log("WS_BABY.FTSMainUI.into");
        try {
            if (this.fans == null || this.fans.size() <= 0 || !MyApplication.enforceable) {
                removeEndView(this.mMyManager);
            } else if (this.maxNum >= 1 && MyApplication.enforceable) {
                if (this.recordSearchNum != this.maxNum || !MyApplication.enforceable) {
                    if (this.ffModel == 1) {
                        this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
                        LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
                    }
                    if (this.spaceTime <= 0 || this.recordSearchNum <= 0) {
                        initContactPhone();
                    } else {
                        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void surplus(int i) {
                                MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }

                            public void end() {
                                AutoAddContactsOptionalUtils.this.initContactPhone();
                            }
                        });
                    }
                } else {
                    LogUtils.log("WS_BABY.FTSMainUI.end");
                    removeEndView(this.mMyManager);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactPhone() {
        LogUtils.log("WS_BABY.FTSMainUI.1");
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        new PerformClickUtils2().check(this.autoService, search_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.FTSMainUI.2");
                PerformClickUtils.findViewIdAndClick(AutoAddContactsOptionalUtils.this.autoService, BaseServiceUtils.search_id);
                AutoAddContactsOptionalUtils.this.sleep(350);
                String unused = AutoAddContactsOptionalUtils.this.currentContactPhone = ((ContactBean) AutoAddContactsOptionalUtils.this.fans.get(0)).getNumber();
                String unused2 = AutoAddContactsOptionalUtils.this.currentContactName = ((ContactBean) AutoAddContactsOptionalUtils.this.fans.get(0)).getNickName();
                LogUtils.log("WS_BABY.FTSMainUI.3." + AutoAddContactsOptionalUtils.this.currentContactPhone);
                PerformClickUtils.inputText(AutoAddContactsOptionalUtils.this.autoService, AutoAddContactsOptionalUtils.this.currentContactPhone);
                new PerformClickUtils2().checkString(AutoAddContactsOptionalUtils.this.autoService, "查找手机/QQ号", 600, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            AutoAddContactsOptionalUtils.this.fans.remove(0);
                            int unused = AutoAddContactsOptionalUtils.this.recordSearchNum = AutoAddContactsOptionalUtils.this.recordSearchNum + 1;
                            LogUtils.log("WS_BABY.FTSMainUI.2");
                            if (MyApplication.enforceable) {
                                PerformClickUtils.findTextAndClick(AutoAddContactsOptionalUtils.this.autoService, "查找手机/QQ号");
                                MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在添加：" + AutoAddContactsOptionalUtils.this.currentContactName + "(" + AutoAddContactsOptionalUtils.this.currentContactPhone + ")");
                                new PerformClickUtils2().checkNodeIdNameHasSomeOne3(AutoAddContactsOptionalUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "添加到通讯录", "发消息", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|19) */
                                    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x004c */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void find(int r3) {
                                        /*
                                            r2 = this;
                                            if (r3 != 0) goto L_0x00aa
                                            boolean r3 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00a5 }
                                            if (r3 == 0) goto L_0x00b3
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r0 = "操作过于频繁"
                                            boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r0)     // Catch:{ Exception -> 0x00a5 }
                                            if (r3 == 0) goto L_0x002e
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r0 = "操作过于频繁，请稍后在试"
                                            java.lang.String unused = r3.middleString = r0     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service.MyWindowManager r3 = r3.mMyManager     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r3)     // Catch:{ Exception -> 0x00a5 }
                                            goto L_0x00b3
                                        L_0x002e:
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r0 = ""
                                            java.lang.String unused = r3.middleString = r0     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.utils.SQLiteUtils r3 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r0 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r0 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r0 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x004c }
                                            java.lang.String r0 = r0.currentContactPhone     // Catch:{ Exception -> 0x004c }
                                            java.lang.String r1 = "无效微信号"
                                            r3.updateContact(r0, r1)     // Catch:{ Exception -> 0x004c }
                                        L_0x004c:
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service.MyWindowManager r3 = r3.mMyManager     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a5 }
                                            r0.<init>()     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r1 = "自选添加手机联系人\n已检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            int r1 = r1.recordSearchNum     // Catch:{ Exception -> 0x00a5 }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r1 = " 人，待检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r1 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            java.util.ArrayList r1 = r1.fans     // Catch:{ Exception -> 0x00a5 }
                                            int r1 = r1.size()     // Catch:{ Exception -> 0x00a5 }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r1 = " 人"
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r3, r0)     // Catch:{ Exception -> 0x00a5 }
                                            java.lang.String r3 = "WS_BABY.com.tencent.mm.ui.widget.a.c0"
                                            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x00a5 }
                                            r0 = 30
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1$1$1 r1 = new com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1$1$1     // Catch:{ Exception -> 0x00a5 }
                                            r1.<init>()     // Catch:{ Exception -> 0x00a5 }
                                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r3, r0, r1)     // Catch:{ Exception -> 0x00a5 }
                                            goto L_0x00b3
                                        L_0x00a5:
                                            r3 = move-exception
                                            r3.printStackTrace()
                                            goto L_0x00b3
                                        L_0x00aa:
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6$1 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.this
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils$6 r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.this
                                            com.wx.assistants.service_utils.AutoAddContactsOptionalUtils r3 = com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.this
                                            r3.initFirstContactInfo()
                                        L_0x00b3:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddContactsOptionalUtils.AnonymousClass6.AnonymousClass1.AnonymousClass1.find(int):void");
                                    }
                                });
                            }
                        }
                    }

                    public void nuFind() {
                        try {
                            SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "无效微信号");
                        } catch (Exception unused) {
                        }
                        int unused2 = AutoAddContactsOptionalUtils.this.recordSearchNum = AutoAddContactsOptionalUtils.this.recordSearchNum + 1;
                        AutoAddContactsOptionalUtils.this.fans.remove(0);
                        PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsOptionalUtils.this.executeMain();
                            }
                        });
                    }
                });
            }
        });
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsOptionalUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(AutoAddContactsOptionalUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.get(0) == null)) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        AutoAddContactsOptionalUtils.this.initFirstFTSMain();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        try {
            if ("".equals(this.middleString)) {
                MyWindowManager myWindowManager = this.mMyManager;
                updateMiddleText(myWindowManager, "自选手机联系人", "共检索了" + this.recordSearchNum + "个手机联系人");
                return;
            }
            updateMiddleText(this.mMyManager, "自选手机联系人", this.middleString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在自选添加联系人，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                        public void run() {
                            AutoAddContactsOptionalUtils.this.executeMain();
                        }
                    }, 10);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_START_ERROR");
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.removeBottomOperationView();
        this.mMyManager.showMiddleView();
    }
}
