package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.CameraContactEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class AutoAddContactsCameraUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddContactsCameraUtils instance;
    private int breakAdd = 0;
    /* access modifiers changed from: private */
    public String currentContactPhone = "";
    /* access modifiers changed from: private */
    public List<ContactScanBean> fans = new ArrayList();
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

    private AutoAddContactsCameraUtils() {
    }

    public static AutoAddContactsCameraUtils getInstance() {
        instance = new AutoAddContactsCameraUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.middleString = "";
        this.recordSearchNum = 0;
        this.currentContactPhone = "";
        this.breakAdd = operationParameterModel.isBreakAdd();
        this.fans = operationParameterModel.getScanPhones();
        this.sayContent = operationParameterModel.getSayContent();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.singleLabel = operationParameterModel.getSingLabelStr();
        this.remark = operationParameterModel.getRemarkPrefix();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstContactInfo() {
        try {
            if (this.breakAdd == 0) {
                LogUtils.log("WS_BABY.ContactInfoUI.2");
                MyWindowManager myWindowManager = this.mMyManager;
                updateBottomText(myWindowManager, "已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
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
                                BaseServiceUtils.removeBottomOperationView(AutoAddContactsCameraUtils.this.mMyManager);
                                MyWindowManager myWindowManager = AutoAddContactsCameraUtils.this.mMyManager;
                                BaseServiceUtils.showBottomView(myWindowManager, "已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人");
                                PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        AutoAddContactsCameraUtils.this.executeMain();
                                    }
                                });
                            } catch (Exception unused) {
                            }
                        }
                    }).start();
                }
            }, "允许添加", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(AutoAddContactsCameraUtils.this.mMyManager);
                    LogUtils.log("WS_BABY.ContactInfoUI.222222222");
                    MyWindowManager myWindowManager = AutoAddContactsCameraUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自动添加好友\n已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人");
                    AutoAddContactsCameraUtils.this.addContact();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(6:8|9|10|(1:14)|19|24)(2:20|25)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0011 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071 A[Catch:{ Exception -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015 A[Catch:{ Exception -> 0x00a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inputSayContent() {
        /*
            r4 = this;
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.0"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.utils.SQLiteUtils r0 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x0011 }
            java.lang.String r1 = r4.currentContactPhone     // Catch:{ Exception -> 0x0011 }
            r2 = 3
            java.lang.String r3 = "待好友通过"
            r0.updateContactScan(r1, r2, r3)     // Catch:{ Exception -> 0x0011 }
        L_0x0011:
            boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00a2 }
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.1"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = r4.sayContent     // Catch:{ Exception -> 0x0041 }
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = r4.sayContent     // Catch:{ Exception -> 0x0041 }
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0041 }
            if (r0 != 0) goto L_0x0046
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.111"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0041 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x0041 }
            java.lang.String r1 = input_say_content     // Catch:{ Exception -> 0x0041 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x0041 }
            r0 = 50
            r4.sleep(r0)     // Catch:{ Exception -> 0x0041 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x0041 }
            java.lang.String r1 = r4.sayContent     // Catch:{ Exception -> 0x0041 }
            com.wx.assistants.utils.PerformClickUtils.inputText(r0, r1)     // Catch:{ Exception -> 0x0041 }
            goto L_0x0046
        L_0x0041:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.sayContent.error"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
        L_0x0046:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
            int r0 = executeSpeed     // Catch:{ Exception -> 0x00a2 }
            int r1 = executeSpeedSetting     // Catch:{ Exception -> 0x00a2 }
            int r0 = r0 + r1
            r4.sleep(r0)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.3"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.service.AutoService r0 = r4.autoService     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r1 = complete_id     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r0, r1)     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x00a2 }
            r0.<init>()     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.service.AutoService r1 = r4.autoService     // Catch:{ Exception -> 0x00a2 }
            r2 = 300(0x12c, float:4.2E-43)
            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$3 r3 = new com.wx.assistants.service_utils.AutoAddContactsCameraUtils$3     // Catch:{ Exception -> 0x00a2 }
            r3.<init>()     // Catch:{ Exception -> 0x00a2 }
            r0.checkIsSending(r1, r2, r3)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00a6
        L_0x0071:
            java.lang.String r0 = "WS_BABY.SayHiWithSnsPermissionUI.2"
            com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x00a2 }
            com.wx.assistants.service.MyWindowManager r0 = r4.mMyManager     // Catch:{ Exception -> 0x00a2 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2 }
            r1.<init>()     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r2 = "已检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00a2 }
            int r2 = r4.recordSearchNum     // Catch:{ Exception -> 0x00a2 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r2 = " 人，待检索 "
            r1.append(r2)     // Catch:{ Exception -> 0x00a2 }
            java.util.List<com.wx.assistants.Enity.ContactScanBean> r2 = r4.fans     // Catch:{ Exception -> 0x00a2 }
            int r2 = r2.size()     // Catch:{ Exception -> 0x00a2 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r2 = " 人"
            r1.append(r2)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00a2 }
            updateBottomText(r0, r1)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00a6
        L_0x00a2:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddContactsCameraUtils.inputSayContent():void");
    }

    public void addContact() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    LogUtils.log("WS_BABY_添加到通讯录.0");
                    if (PerformClickUtils.findNodeIsExistByText(AutoAddContactsCameraUtils.this.autoService, "添加到通讯录")) {
                        LogUtils.log("WS_BABY_添加到通讯录.1");
                        if ((AutoAddContactsCameraUtils.this.remark == null || "".equals(AutoAddContactsCameraUtils.this.remark)) && (AutoAddContactsCameraUtils.this.singleLabel == null || "".equals(AutoAddContactsCameraUtils.this.singleLabel))) {
                            AutoAddContactsCameraUtils.this.addAddressContact();
                        } else {
                            AutoAddContactsCameraUtils.this.remarkSign();
                        }
                    } else {
                        try {
                            SQLiteUtils.getInstance().updateContactScan(AutoAddContactsCameraUtils.this.currentContactPhone, 1, "已添加");
                        } catch (Exception unused) {
                        }
                        LogUtils.log("WS_BABY_发消息@Back1");
                        PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsCameraUtils.this.executeMain();
                            }
                        });
                    }
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
                                MyWindowManager myWindowManager = AutoAddContactsCameraUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }

                            public void end() {
                                AutoAddContactsCameraUtils.this.initContactPhone();
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
        updateBottomText(myWindowManager, "已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        new PerformClickUtils2().check(this.autoService, search_id, executeSpeed + 150 + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.FTSMainUI.2");
                PerformClickUtils.findViewIdAndClick(AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.search_id);
                AutoAddContactsCameraUtils.this.sleep(350);
                if (AutoAddContactsCameraUtils.this.fans == null || AutoAddContactsCameraUtils.this.fans.size() == 0) {
                    BaseServiceUtils.removeEndView(AutoAddContactsCameraUtils.this.mMyManager);
                    return;
                }
                String unused = AutoAddContactsCameraUtils.this.currentContactPhone = ((ContactScanBean) AutoAddContactsCameraUtils.this.fans.get(0)).getNumber();
                LogUtils.log("WS_BABY.FTSMainUI.3." + AutoAddContactsCameraUtils.this.currentContactPhone);
                PerformClickUtils.inputText(AutoAddContactsCameraUtils.this.autoService, AutoAddContactsCameraUtils.this.currentContactPhone);
                new PerformClickUtils2().checkString(AutoAddContactsCameraUtils.this.autoService, "查找手机/QQ号", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (MyApplication.enforceable) {
                            AutoAddContactsCameraUtils.this.fans.remove(0);
                            int unused = AutoAddContactsCameraUtils.this.recordSearchNum = AutoAddContactsCameraUtils.this.recordSearchNum + 1;
                            LogUtils.log("WS_BABY.FTSMainUI.2");
                            if (MyApplication.enforceable) {
                                PerformClickUtils.findTextAndClick(AutoAddContactsCameraUtils.this.autoService, "查找手机/QQ号");
                                MyWindowManager myWindowManager = AutoAddContactsCameraUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在添加：" + AutoAddContactsCameraUtils.this.currentContactPhone + "");
                                new PerformClickUtils2().checkNodeIdNameHasSomeOne3(AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "添加到通讯录", "发消息", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                    public void nuFind() {
                                    }

                                    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|19) */
                                    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x004d */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void find(int r4) {
                                        /*
                                            r3 = this;
                                            if (r4 != 0) goto L_0x00ab
                                            boolean r4 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00a6 }
                                            if (r4 == 0) goto L_0x00b4
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service.AutoService r4 = r4.autoService     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r0 = "操作过于频繁"
                                            boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r4, r0)     // Catch:{ Exception -> 0x00a6 }
                                            if (r4 == 0) goto L_0x002e
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r0 = "操作过于频繁，请稍后在试"
                                            java.lang.String unused = r4.middleString = r0     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service.MyWindowManager r4 = r4.mMyManager     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r4)     // Catch:{ Exception -> 0x00a6 }
                                            goto L_0x00b4
                                        L_0x002e:
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r0 = ""
                                            java.lang.String unused = r4.middleString = r0     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.utils.SQLiteUtils r4 = com.wx.assistants.utils.SQLiteUtils.getInstance()     // Catch:{ Exception -> 0x004d }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r0 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x004d }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r0 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x004d }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r0 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x004d }
                                            java.lang.String r0 = r0.currentContactPhone     // Catch:{ Exception -> 0x004d }
                                            r1 = 2
                                            java.lang.String r2 = "无效微信号"
                                            r4.updateContactScan(r0, r1, r2)     // Catch:{ Exception -> 0x004d }
                                        L_0x004d:
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service.MyWindowManager r4 = r4.mMyManager     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a6 }
                                            r0.<init>()     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r1 = "已检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            int r1 = r1.recordSearchNum     // Catch:{ Exception -> 0x00a6 }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r1 = " 人，待检索 "
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r1 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            java.util.List r1 = r1.fans     // Catch:{ Exception -> 0x00a6 }
                                            int r1 = r1.size()     // Catch:{ Exception -> 0x00a6 }
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r1 = " 人"
                                            r0.append(r1)     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r4, r0)     // Catch:{ Exception -> 0x00a6 }
                                            java.lang.String r4 = "WS_BABY.com.tencent.mm.ui.widget.a.c0"
                                            com.wx.assistants.utils.LogUtils.log(r4)     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.service.AutoService r4 = r4.autoService     // Catch:{ Exception -> 0x00a6 }
                                            r0 = 30
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1$1$1 r1 = new com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1$1$1     // Catch:{ Exception -> 0x00a6 }
                                            r1.<init>()     // Catch:{ Exception -> 0x00a6 }
                                            com.wx.assistants.utils.PerformClickUtils.executedBackHome(r4, r0, r1)     // Catch:{ Exception -> 0x00a6 }
                                            goto L_0x00b4
                                        L_0x00a6:
                                            r4 = move-exception
                                            r4.printStackTrace()
                                            goto L_0x00b4
                                        L_0x00ab:
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6$1 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.this
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils$6 r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.this
                                            com.wx.assistants.service_utils.AutoAddContactsCameraUtils r4 = com.wx.assistants.service_utils.AutoAddContactsCameraUtils.this
                                            r4.initFirstContactInfo()
                                        L_0x00b4:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddContactsCameraUtils.AnonymousClass6.AnonymousClass1.AnonymousClass1.find(int):void");
                                    }
                                });
                            }
                        }
                    }

                    public void nuFind() {
                        try {
                            SQLiteUtils.getInstance().updateContactScan(AutoAddContactsCameraUtils.this.currentContactPhone, 2, "无效微信号");
                        } catch (Exception unused) {
                        }
                        int unused2 = AutoAddContactsCameraUtils.this.recordSearchNum = AutoAddContactsCameraUtils.this.recordSearchNum + 1;
                        AutoAddContactsCameraUtils.this.fans.remove(0);
                        PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsCameraUtils.this.executeMain();
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
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(AutoAddContactsCameraUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.get(0) == null)) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        AutoAddContactsCameraUtils.this.initFirstFTSMain();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remarkSign() {
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "设置备注和标签", "设置备注及标签", (executeSpeedSetting / 8) + 50, 100, 10, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                if (i == 0) {
                    List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(AutoAddContactsCameraUtils.this.autoService, "设置备注和标签");
                    if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                        PerformClickUtils.performClick(findViewStringList.get(0));
                        AutoAddContactsCameraUtils.this.initContactRemarkInfoModUI();
                        return;
                    }
                    return;
                }
                List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(AutoAddContactsCameraUtils.this.autoService, "设置备注及标签");
                if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                    PerformClickUtils.performClick(findViewStringList2.get(0));
                    AutoAddContactsCameraUtils.this.initContactRemarkInfoModUI();
                }
            }

            public void nuFind() {
                AutoAddContactsCameraUtils.this.addAddressContact();
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (AutoAddContactsCameraUtils.this.remark == null || "".equals(AutoAddContactsCameraUtils.this.remark)) {
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.6");
                        AutoAddContactsCameraUtils.this.signLabel();
                        return;
                    }
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    if (!PerformClickUtils.findNodeIsExistByText(AutoAddContactsCameraUtils.this.autoService, AutoAddContactsCameraUtils.this.remark)) {
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0 || !MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.4");
                            AutoAddContactsCameraUtils.this.signLabel();
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.3");
                        findViewIdList.get(0).performAction(16);
                        AutoAddContactsCameraUtils.this.sleep(100);
                        AutoService autoService = AutoAddContactsCameraUtils.this.autoService;
                        PerformClickUtils.inputPrefixText(autoService, AutoAddContactsCameraUtils.this.remark + "");
                        AutoAddContactsCameraUtils.this.sleep(100);
                        AutoAddContactsCameraUtils.this.signLabel();
                        return;
                    }
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                    AutoAddContactsCameraUtils.this.signLabel();
                }

                public void nuFind() {
                    PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddContactsCameraUtils.this.addAddressContact();
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
                    AutoAddContactsCameraUtils.this.addAddressContact();
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
                        PerformClickUtils.findViewIdAndClick(AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.input_remark_label);
                    } else if (i == 1) {
                        PerformClickUtils.findViewIdAndClick(AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                    }
                    new PerformClickUtils2().checkString(AutoAddContactsCameraUtils.this.autoService, "添加标签", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.11");
                            PerformClickUtils.findViewByIdAndPasteContent(AutoAddContactsCameraUtils.this.autoService, BaseServiceUtils.search_id, AutoAddContactsCameraUtils.this.singleLabel);
                            AutoAddContactsCameraUtils.this.sleep(100);
                            PerformClickUtils.findTextAndClick(AutoAddContactsCameraUtils.this.autoService, "保存");
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.12");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddContactsCameraUtils.this.autoService, "完成", "保存", BaseServiceUtils.executeSpeedSetting + SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.13");
                                    if (i == 0) {
                                        PerformClickUtils.findTextAndClick(AutoAddContactsCameraUtils.this.autoService, "完成");
                                    } else {
                                        PerformClickUtils.findTextAndClick(AutoAddContactsCameraUtils.this.autoService, "保存");
                                    }
                                    new PerformClickUtils2().checkNilString(AutoAddContactsCameraUtils.this.autoService, "保存", (int) SocializeConstants.CANCLE_RESULTCODE, 10, 2, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.18");
                                                    AutoAddContactsCameraUtils.this.addAddressContact();
                                                }
                                            });
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.findTextAndClick(AutoAddContactsCameraUtils.this.autoService, "保存");
                                            AutoAddContactsCameraUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                            PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.18");
                                                    AutoAddContactsCameraUtils.this.addAddressContact();
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.14");
                                    PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            AutoAddContactsCameraUtils.this.addAddressContact();
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.15");
                            PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddContactsCameraUtils.this.addAddressContact();
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.16");
                    PerformClickUtils.executedBackAddress(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddContactsCameraUtils.this.addAddressContact();
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
                AutoAddContactsCameraUtils.this.addAddressContact();
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
                new PerformClickUtils2().checkAddContact(AutoAddContactsCameraUtils.this.autoService, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (i == 0) {
                            try {
                                SQLiteUtils.getInstance().updateContactScan(AutoAddContactsCameraUtils.this.currentContactPhone, 1, "已添加");
                            } catch (Exception unused) {
                            }
                            LogUtils.log("WS_BABY_添加到通讯录_END_1");
                            MyWindowManager myWindowManager = AutoAddContactsCameraUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddContactsCameraUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddContactsCameraUtils.this.executeMain();
                                }
                            });
                        } else if (i == 1) {
                            try {
                                SQLiteUtils.getInstance().updateContactScan(AutoAddContactsCameraUtils.this.currentContactPhone, 4, "添加失败");
                            } catch (Exception unused2) {
                            }
                            MyWindowManager myWindowManager2 = AutoAddContactsCameraUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager2, "已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人");
                            LogUtils.log("WS_BABY_添加到通讯录_END1111122222");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddContactsCameraUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddContactsCameraUtils.this.executeMain();
                                }
                            });
                        } else if (i == 2) {
                            LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                            AutoAddContactsCameraUtils.this.inputSayContent();
                        } else if (i == 3) {
                            LogUtils.log("WS_BABY_添加到通讯录.dialog");
                            MyWindowManager myWindowManager3 = AutoAddContactsCameraUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager3, "已检索 " + AutoAddContactsCameraUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsCameraUtils.this.fans.size() + " 人");
                            SPUtils.put(MyApplication.getConText(), "auto_add_customer_num", Integer.valueOf(AutoAddContactsCameraUtils.this.recordSearchNum));
                            PerformClickUtils.executedBackHome(AutoAddContactsCameraUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    AutoAddContactsCameraUtils.this.executeMain();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void middleViewShow() {
        try {
            if ("".equals(this.middleString)) {
                MyWindowManager myWindowManager = this.mMyManager;
                updateMiddleText(myWindowManager, "自动添加好友", "共检索了" + this.recordSearchNum + "个");
            } else {
                updateMiddleText(this.mMyManager, "自动添加好友", this.middleString);
            }
            DateUtils.putCameraNum(this.recordSearchNum);
            EventBus.getDefault().post(new CameraContactEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "自动添加好友，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                        public void run() {
                            AutoAddContactsCameraUtils.this.executeMain();
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
