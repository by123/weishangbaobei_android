package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class AutoAddMyCustomerUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddMyCustomerUtils instance;
    private int breakAdd = 0;
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
    /* access modifiers changed from: private */
    public String remark = "";
    private String sayContent;
    /* access modifiers changed from: private */
    public String singleLabel = "";
    private int spaceTime = 0;

    public void middleViewDismiss() {
    }

    private AutoAddMyCustomerUtils() {
    }

    public static AutoAddMyCustomerUtils getInstance() {
        instance = new AutoAddMyCustomerUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.middleString = "";
        this.recordSearchNum = 0;
        this.currentContactPhone = "";
        this.breakAdd = operationParameterModel.isBreakAdd();
        this.fans = operationParameterModel.getAddressBookPhones();
        this.sayContent = operationParameterModel.getSayContent();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.ffModel = operationParameterModel.getFfModel();
        this.singleLabel = operationParameterModel.getSingLabelStr();
        this.remark = operationParameterModel.getRemarkPrefix();
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
                SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(this.recordSearchNum));
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
                                BaseServiceUtils.removeBottomOperationView(AutoAddMyCustomerUtils.this.mMyManager);
                                MyWindowManager myWindowManager = AutoAddMyCustomerUtils.this.mMyManager;
                                BaseServiceUtils.showBottomView(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人");
                                SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerUtils.this.recordSearchNum));
                                PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        AutoAddMyCustomerUtils.this.executeMain();
                                    }
                                });
                            } catch (Exception unused) {
                            }
                        }
                    }).start();
                }
            }, "允许添加", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(AutoAddMyCustomerUtils.this.mMyManager);
                    LogUtils.log("WS_BABY.ContactInfoUI.222222222");
                    MyWindowManager myWindowManager = AutoAddMyCustomerUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人");
                    SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerUtils.this.recordSearchNum));
                    AutoAddMyCustomerUtils.this.addContact();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(6:8|9|10|(1:14)|19|24)(2:20|25)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070 A[Catch:{ Exception -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014 A[Catch:{ Exception -> 0x00b0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inputSayContent() {
        /*
            r4 = this;
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.0"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.utils.SQLiteUtils r0 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x0010 }
            java.lang.String r1 = r4.currentContactPhone     // Catch:{ Exception -> 0x0010 }
            java.lang.String r2 = "待好友通过"
            r0.updateContact(r1, r2)     // Catch:{ Exception -> 0x0010 }
        L_0x0010:
            boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00b0 }
            if (r0 == 0) goto L_0x0070
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.1"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r0 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0040 }
            if (r0 != 0) goto L_0x0045
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.111"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x0040 }
            java.lang.String r1 = input_say_content     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x0040 }
            r0 = 50
            r4.sleep(r0)     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x0040 }
            java.lang.String r1 = r4.sayContent     // Catch:{ Exception -> 0x0040 }
            com.wx.assistants.utils.PerformClickUtils.inputText(r0, r1)     // Catch:{ Exception -> 0x0040 }
            goto L_0x0045
        L_0x0040:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.sayContent.error"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
        L_0x0045:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
            int r0 = executeSpeed     // Catch:{ Exception -> 0x00b0 }
            int r1 = executeSpeedSetting     // Catch:{ Exception -> 0x00b0 }
            int r0 = r0 + r1
            r4.sleep(r0)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.3"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r1 = complete_id     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x00b0 }
            r0.<init>()     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x00b0 }
            r2 = 300(0x12c, float:4.2E-43)
            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$3 r3 = new com.wx.assistants.service_utils.AutoAddMyCustomerUtils$3     // Catch:{ Exception -> 0x00b0 }
            r3.<init>()     // Catch:{ Exception -> 0x00b0 }
            r0.checkIsSending(r1, r2, r3)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x00b4
        L_0x0070:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00b0 }
            android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r1 = "auto_add_customer_num"
            int r2 = r4.recordSearchNum     // Catch:{ Exception -> 0x00b0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.utils.SPUtils.put(r0, r1, r2)     // Catch:{ Exception -> 0x00b0 }
            com.wx.assistants.service.MyWindowManager r0 = r4.mMyManager     // Catch:{ Exception -> 0x00b0 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b0 }
            r1.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = "自选添加手机联系人\n已检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00b0 }
            int r2 = r4.recordSearchNum     // Catch:{ Exception -> 0x00b0 }
            r1.append(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = " 人，待检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00b0 }
            java.util.ArrayList<com.wx.assistants.Enity.ContactBean> r2 = r4.fans     // Catch:{ Exception -> 0x00b0 }
            int r2 = r2.size()     // Catch:{ Exception -> 0x00b0 }
            r1.append(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = " 人"
            r1.append(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00b0 }
            updateBottomText(r0, r1)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddMyCustomerUtils.inputSayContent():void");
    }

    public void addContact() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    LogUtils.log("WS_BABY_添加到通讯录.0");
                    if (PerformClickUtils.findNodeIsExistByText(AutoAddMyCustomerUtils.this.autoService, "添加到通讯录")) {
                        LogUtils.log("WS_BABY_添加到通讯录.1");
                        if ((AutoAddMyCustomerUtils.this.remark == null || "".equals(AutoAddMyCustomerUtils.this.remark)) && (AutoAddMyCustomerUtils.this.singleLabel == null || "".equals(AutoAddMyCustomerUtils.this.singleLabel))) {
                            AutoAddMyCustomerUtils.this.addAddressContact();
                        } else {
                            AutoAddMyCustomerUtils.this.remarkSign();
                        }
                    } else {
                        try {
                            SQLiteUtils.getInstance().updateContact(AutoAddMyCustomerUtils.this.currentContactPhone, "已添加");
                        } catch (Exception unused) {
                        }
                        LogUtils.log("WS_BABY_发消息@Back1");
                        PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddMyCustomerUtils.this.executeMain();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void remarkSign() {
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "设置备注和标签", "设置备注及标签", (executeSpeedSetting / 8) + 50, 100, 10, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                if (i == 0) {
                    List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(AutoAddMyCustomerUtils.this.autoService, "设置备注和标签");
                    if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                        PerformClickUtils.performClick(findViewStringList.get(0));
                        AutoAddMyCustomerUtils.this.initContactRemarkInfoModUI();
                        return;
                    }
                    return;
                }
                List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(AutoAddMyCustomerUtils.this.autoService, "设置备注及标签");
                if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                    PerformClickUtils.performClick(findViewStringList2.get(0));
                    AutoAddMyCustomerUtils.this.initContactRemarkInfoModUI();
                }
            }

            public void nuFind() {
                AutoAddMyCustomerUtils.this.addAddressContact();
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (AutoAddMyCustomerUtils.this.remark == null || "".equals(AutoAddMyCustomerUtils.this.remark)) {
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.6");
                        AutoAddMyCustomerUtils.this.signLabel();
                        return;
                    }
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    if (!PerformClickUtils.findNodeIsExistByText(AutoAddMyCustomerUtils.this.autoService, AutoAddMyCustomerUtils.this.remark)) {
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.4");
                            AutoAddMyCustomerUtils.this.signLabel();
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.3");
                        findViewIdList.get(0).performAction(16);
                        AutoAddMyCustomerUtils.this.sleep(100);
                        AutoService autoService = AutoAddMyCustomerUtils.this.autoService;
                        PerformClickUtils.inputPrefixText(autoService, AutoAddMyCustomerUtils.this.remark + "");
                        AutoAddMyCustomerUtils.this.sleep(100);
                        AutoAddMyCustomerUtils.this.signLabel();
                        return;
                    }
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                    AutoAddMyCustomerUtils.this.signLabel();
                }

                public void nuFind() {
                    PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddMyCustomerUtils.this.addAddressContact();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signLabel() {
        if (this.singleLabel == null || "".equals(this.singleLabel)) {
            PerformClickUtils.findTextAndClick(this.autoService, "保存");
            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.9");
            PerformClickUtils.executedBackAddress(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    AutoAddMyCustomerUtils.this.addAddressContact();
                }
            });
            return;
        }
        boolean z = !PerformClickUtils.findNodeIsExistByText(this.autoService, this.singleLabel);
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.7");
        if (z) {
            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.8");
            new PerformClickUtils2().checkNodeIds(this.autoService, input_remark_label, input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.10");
                    if (i == 0) {
                        PerformClickUtils.findViewIdAndClick(AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.input_remark_label);
                    } else if (i == 1) {
                        PerformClickUtils.findViewIdAndClick(AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                    }
                    new PerformClickUtils2().checkString(AutoAddMyCustomerUtils.this.autoService, "添加标签", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.11");
                            PerformClickUtils.findViewByIdAndPasteContent(AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.search_id, AutoAddMyCustomerUtils.this.singleLabel);
                            AutoAddMyCustomerUtils.this.sleep(100);
                            PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "保存");
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.12");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddMyCustomerUtils.this.autoService, "完成", "保存", BaseServiceUtils.executeSpeedSetting + SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.13");
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "完成");
                                    } else {
                                        PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "保存");
                                    }
                                    new PerformClickUtils2().checkNilString(AutoAddMyCustomerUtils.this.autoService, "保存", (int) SocializeConstants.CANCLE_RESULTCODE, 10, 2, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.18");
                                                    AutoAddMyCustomerUtils.this.addAddressContact();
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "保存");
                                            AutoAddMyCustomerUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                            PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.18");
                                                    AutoAddMyCustomerUtils.this.addAddressContact();
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.14");
                                    PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            AutoAddMyCustomerUtils.this.addAddressContact();
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.15");
                            PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddMyCustomerUtils.this.addAddressContact();
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.16");
                    PerformClickUtils.executedBackAddress(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddMyCustomerUtils.this.addAddressContact();
                        }
                    });
                }
            });
            return;
        }
        PerformClickUtils.findTextAndClick(this.autoService, "保存");
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.17");
        PerformClickUtils.executedBackAddress(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
            public void find() {
                AutoAddMyCustomerUtils.this.addAddressContact();
            }
        });
    }

    public void addAddressContact() {
        sleep(100);
        PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
        LogUtils.log("WS_BABY_添加到通讯录.1.1");
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加", 1500, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new PerformClickUtils2().checkAddContact(AutoAddMyCustomerUtils.this.autoService, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (i == 0) {
                            try {
                                SQLiteUtils.getInstance().updateContact(AutoAddMyCustomerUtils.this.currentContactPhone, "已添加");
                            } catch (Exception unused) {
                            }
                            LogUtils.log("WS_BABY_添加到通讯录_END_1");
                            MyWindowManager myWindowManager = AutoAddMyCustomerUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddMyCustomerUtils.this.executeMain();
                                }
                            });
                        } else if (i == 1) {
                            try {
                                SQLiteUtils.getInstance().updateContact(AutoAddMyCustomerUtils.this.currentContactPhone, "添加失败");
                            } catch (Exception unused2) {
                            }
                            MyWindowManager myWindowManager2 = AutoAddMyCustomerUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager2, "自选添加手机联系人\n已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人");
                            LogUtils.log("WS_BABY_添加到通讯录_END1111122222");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddMyCustomerUtils.this.executeMain();
                                }
                            });
                        } else if (i == 2) {
                            LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                            AutoAddMyCustomerUtils.this.inputSayContent();
                        } else if (i == 3) {
                            LogUtils.log("WS_BABY_添加到通讯录.dialog");
                            MyWindowManager myWindowManager3 = AutoAddMyCustomerUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager3, "自选添加手机联系人\n已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddMyCustomerUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddMyCustomerUtils.this.executeMain();
                                }
                            });
                        }
                    }
                });
            }
        });
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
                                MyWindowManager myWindowManager = AutoAddMyCustomerUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + AutoAddMyCustomerUtils.this.recordSearchNum + " 人，待检索 " + AutoAddMyCustomerUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }

                            public void end() {
                                AutoAddMyCustomerUtils.this.initContactPhone();
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
                PerformClickUtils.findViewIdAndClick(AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.search_id);
                AutoAddMyCustomerUtils.this.sleep(350);
                String unused = AutoAddMyCustomerUtils.this.currentContactPhone = ((ContactBean) AutoAddMyCustomerUtils.this.fans.get(0)).getNumber();
                LogUtils.log("WS_BABY.FTSMainUI.3." + AutoAddMyCustomerUtils.this.currentContactPhone);
                PerformClickUtils.inputText(AutoAddMyCustomerUtils.this.autoService, AutoAddMyCustomerUtils.this.currentContactPhone);
                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddMyCustomerUtils.this.autoService, "查找手机/QQ号", "查找微信号", 600, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            AutoAddMyCustomerUtils.this.fans.remove(0);
                            int unused = AutoAddMyCustomerUtils.this.recordSearchNum = AutoAddMyCustomerUtils.this.recordSearchNum + 1;
                            LogUtils.log("WS_BABY.FTSMainUI.2");
                            if (MyApplication.enforceable) {
                                if (i == 0) {
                                    PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "查找手机/QQ号");
                                } else {
                                    PerformClickUtils.findTextAndClick(AutoAddMyCustomerUtils.this.autoService, "查找微信号");
                                }
                                MyWindowManager myWindowManager = AutoAddMyCustomerUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在添加：" + AutoAddMyCustomerUtils.this.currentContactPhone + "");
                                new PerformClickUtils2().checkNodeIdNameHasSomeOne3(AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "添加到通讯录", "发消息", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|19) */
                                    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x004c */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void find(int r3) {
                                        /*
                                            r2 = this;
                                            if (r3 != 0) goto L_0x00c1
                                            boolean r3 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00bc }
                                            if (r3 == 0) goto L_0x00ca
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r0 = "操作过于频繁"
                                            boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r0)     // Catch:{ Exception -> 0x00bc }
                                            if (r3 == 0) goto L_0x002e
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r0 = "操作过于频繁，请稍后在试"
                                            java.lang.String unused = r3.middleString = r0     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service.MyWindowManager r3 = r3.mMyManager     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r3)     // Catch:{ Exception -> 0x00bc }
                                            goto L_0x00ca
                                        L_0x002e:
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r0 = ""
                                            java.lang.String unused = r3.middleString = r0     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.utils.SQLiteUtils r3 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r0 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r0 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x004c }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r0 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x004c }
                                            java.lang.String r0 = r0.currentContactPhone     // Catch:{ Exception -> 0x004c }
                                            java.lang.String r1 = "无效微信号"
                                            r3.updateContact(r0, r1)     // Catch:{ Exception -> 0x004c }
                                        L_0x004c:
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service.MyWindowManager r3 = r3.mMyManager     // Catch:{ Exception -> 0x00bc }
                                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
                                            r0.<init>()     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r1 = "自选添加手机联系人\n已检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            int r1 = r1.recordSearchNum     // Catch:{ Exception -> 0x00bc }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r1 = " 人，待检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            java.util.ArrayList r1 = r1.fans     // Catch:{ Exception -> 0x00bc }
                                            int r1 = r1.size()     // Catch:{ Exception -> 0x00bc }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r1 = " 人"
                                            r0.append(r1)     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r3, r0)     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r3 = "WS_BABY.com.tencent.mm.ui.widget.a.c0"
                                            com.wx.assistants.utils.LogUtils.log(r3)     // Catch:{ Exception -> 0x00bc }
                                            android.content.Context r3 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x00bc }
                                            java.lang.String r0 = "auto_add_customer_num"
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r1 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            int r1 = r1.recordSearchNum     // Catch:{ Exception -> 0x00bc }
                                            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.utils.SPUtils.put(r3, r0, r1)     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.service.AutoService r3 = r3.autoService     // Catch:{ Exception -> 0x00bc }
                                            r0 = 30
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1$1$1 r1 = new com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1$1$1     // Catch:{ Exception -> 0x00bc }
                                            r1.<init>()     // Catch:{ Exception -> 0x00bc }
                                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r3, r0, r1)     // Catch:{ Exception -> 0x00bc }
                                            goto L_0x00ca
                                        L_0x00bc:
                                            r3 = move-exception
                                            r3.printStackTrace()
                                            goto L_0x00ca
                                        L_0x00c1:
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12$1 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.this
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils$12 r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.this
                                            com.wx.assistants.service_utils.AutoAddMyCustomerUtils r3 = com.wx.assistants.service_utils.AutoAddMyCustomerUtils.this
                                            r3.initFirstContactInfo()
                                        L_0x00ca:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddMyCustomerUtils.AnonymousClass12.AnonymousClass1.AnonymousClass1.find(int):void");
                                    }
                                });
                            }
                        }
                    }

                    public void nuFind() {
                        try {
                            SQLiteUtils.getInstance().updateContact(AutoAddMyCustomerUtils.this.currentContactPhone, "无效微信号");
                        } catch (Exception unused) {
                        }
                        int unused2 = AutoAddMyCustomerUtils.this.recordSearchNum = AutoAddMyCustomerUtils.this.recordSearchNum + 1;
                        AutoAddMyCustomerUtils.this.fans.remove(0);
                        PerformClickUtils.executedBackHome(AutoAddMyCustomerUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddMyCustomerUtils.this.executeMain();
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
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddMyCustomerUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(AutoAddMyCustomerUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.get(0) == null)) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        AutoAddMyCustomerUtils.this.initFirstFTSMain();
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
                SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(this.recordSearchNum));
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
                            AutoAddMyCustomerUtils.this.executeMain();
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
