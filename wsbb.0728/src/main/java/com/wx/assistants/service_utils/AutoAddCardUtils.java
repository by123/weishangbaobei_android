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
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;

public class AutoAddCardUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddCardUtils instance;
    /* access modifiers changed from: private */
    public int backTime = 100;
    /* access modifiers changed from: private */
    public int executeIndex;
    /* access modifiers changed from: private */
    public int jumpTime = 100;
    /* access modifiers changed from: private */
    public List<String> recordNames = new ArrayList();
    /* access modifiers changed from: private */
    public int recordNum = 0;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public String remarkPrefix;
    private String sayContent;
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    private String sendResult = "";
    /* access modifiers changed from: private */
    public Gender sex;
    private int verifyCode = 1;

    private AutoAddCardUtils() {
    }

    static /* synthetic */ int access$508(AutoAddCardUtils autoAddCardUtils) {
        int i = autoAddCardUtils.executeIndex;
        autoAddCardUtils.executeIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(AutoAddCardUtils autoAddCardUtils) {
        int i = autoAddCardUtils.scrollNum;
        autoAddCardUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AutoAddCardUtils autoAddCardUtils) {
        int i = autoAddCardUtils.recordNum;
        autoAddCardUtils.recordNum = i + 1;
        return i;
    }

    public static AutoAddCardUtils getInstance() {
        instance = new AutoAddCardUtils();
        return instance;
    }

    public void addContact() {
        try {
            if (!PerformClickUtils.findNodeIsExistByText(this.autoService, "添加到通讯录") || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY_发消息@Back1");
                sleep(this.backTime);
                PerformClickUtils.performBack(this.autoService);
                executeMain();
                return;
            }
            new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "朋友圈", "个人相册", "个性签名", "来源", 0, 100, 30, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AutoAddCardUtils.this.executeAddFans();
                }

                public void nuFind() {
                    AutoAddCardUtils.this.executeAddFans();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在批量加名片，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                        public void run() {
                            AutoAddCardUtils.this.executeMain();
                        }
                    }, 10);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAddFans() {
        if (this.remarkLabel == null || "".equals(this.remarkLabel)) {
            executedTask();
        } else {
            new PerformClickUtils2().checkString(this.autoService, "设置备注和标签", 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactInfoUI_2");
                    PerformClickUtils.findTextAndClick(AutoAddCardUtils.this.autoService, "设置备注和标签");
                    AutoAddCardUtils.this.sleep(BannerConfig.DURATION);
                    if (PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, AutoAddCardUtils.this.remarkLabel)) {
                        PerformClickUtils.findTextAndClick(AutoAddCardUtils.this.autoService, "保存");
                        AutoAddCardUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                        AutoAddCardUtils.this.executedTask();
                        return;
                    }
                    new PerformClickUtils2().checkNodeIds(AutoAddCardUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (i == 0) {
                                PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.input_remark_label);
                            } else if (i == 1) {
                                PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                            }
                            new PerformClickUtils2().checkString(AutoAddCardUtils.this.autoService, AutoAddCardUtils.this.remarkLabel, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(AutoAddCardUtils.this.autoService, AutoAddCardUtils.this.remarkLabel);
                                    AutoAddCardUtils.this.sleep(300);
                                    PerformClickUtils.findTextAndClick(AutoAddCardUtils.this.autoService, "保存");
                                    new PerformClickUtils2().checkStringAndIdSomeOne(AutoAddCardUtils.this.autoService, "设置备注", BaseServiceUtils.dialog_ok_id, SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            if (i == 0) {
                                                PerformClickUtils.findTextAndClick(AutoAddCardUtils.this.autoService, "保存");
                                                AutoAddCardUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                                AutoAddCardUtils.this.executedTask();
                                            } else if (i == 1) {
                                                PerformClickUtils.executedLabelBack(AutoAddCardUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        AutoAddCardUtils.this.executedTask();
                                                    }
                                                });
                                            }
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.executedLabelBack(AutoAddCardUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    AutoAddCardUtils.this.executedTask();
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    PerformClickUtils.executedLabelBack(AutoAddCardUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            AutoAddCardUtils.this.executedTask();
                                        }
                                    });
                                }
                            });
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                    AutoAddCardUtils.this.executedTask();
                }
            });
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, contact_title_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String str;
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    List<AccessibilityNodeInfo> findViewTextList = PerformClickUtils.findViewTextList(AutoAddCardUtils.this.autoService, "个人名片");
                    if (findViewTextList != null && findViewTextList.size() > 0 && AutoAddCardUtils.this.executeIndex < findViewTextList.size()) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = findViewTextList.get(AutoAddCardUtils.this.executeIndex);
                        if (accessibilityNodeInfo2.getParent() != null) {
                            AutoAddCardUtils.access$508(AutoAddCardUtils.this);
                            AccessibilityNodeInfo parent = accessibilityNodeInfo2.getParent();
                            if (parent == null || (findAccessibilityNodeInfosByViewId = parent.findAccessibilityNodeInfosByViewId(BaseServiceUtils.group_public_card_name)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) == null || accessibilityNodeInfo.getText() == null) {
                                str = "";
                            } else {
                                str = accessibilityNodeInfo.getText() + "";
                            }
                            System.out.println("WS_BABY_NAME_" + str);
                            if (AutoAddCardUtils.this.recordNames.contains(str)) {
                                AutoAddCardUtils.access$508(AutoAddCardUtils.this);
                                AutoAddCardUtils.this.executeMain();
                                return;
                            }
                            int unused = AutoAddCardUtils.this.scrollNum = 0;
                            AutoAddCardUtils.access$808(AutoAddCardUtils.this);
                            AutoAddCardUtils.this.recordNames.add(str);
                            PerformClickUtils.performClick(parent);
                            AutoAddCardUtils.this.initContactFirstInfo();
                            return;
                        }
                        AutoAddCardUtils.access$508(AutoAddCardUtils.this);
                        AutoAddCardUtils.this.executeMain();
                    } else if (AutoAddCardUtils.this.scrollNum >= 2147483640) {
                        BaseServiceUtils.removeEndView(AutoAddCardUtils.this.mMyManager);
                    } else {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.room_list_id);
                        if (findViewIdList == null || findViewIdList.size() <= 0) {
                            AutoAddCardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            System.out.println("WS_BABY_SCROLL_11111111");
                            if (AutoAddCardUtils.this.scrollNum > 1) {
                                BaseServiceUtils.updateBottomText(AutoAddCardUtils.this.mMyManager, "已执行了 " + AutoAddCardUtils.this.recordNum + " 人,正在等待 " + AutoAddCardUtils.this.scrollNum + " s");
                            }
                            AutoAddCardUtils.this.executeMain();
                            return;
                        }
                        AutoAddCardUtils.access$708(AutoAddCardUtils.this);
                        int unused2 = AutoAddCardUtils.this.executeIndex = 0;
                        if (findViewIdList.get(0) == null || findViewIdList.get(0).getChildCount() <= 0) {
                            AutoAddCardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            System.out.println("WS_BABY_SCROLL_11111111");
                            if (AutoAddCardUtils.this.scrollNum > 1) {
                                BaseServiceUtils.updateBottomText(AutoAddCardUtils.this.mMyManager, "已执行了 " + AutoAddCardUtils.this.recordNum + " 人,正在等待 " + AutoAddCardUtils.this.scrollNum + " s");
                            }
                            AutoAddCardUtils.this.executeMain();
                            return;
                        }
                        findViewIdList.get(0).getChild(0).performAction(4096);
                        AutoAddCardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        System.out.println("WS_BABY_SCROLL_11111111");
                        if (AutoAddCardUtils.this.scrollNum > 1) {
                            BaseServiceUtils.updateBottomText(AutoAddCardUtils.this.mMyManager, "已执行了 " + AutoAddCardUtils.this.recordNum + " 人,正在等待 " + AutoAddCardUtils.this.scrollNum + " s");
                        }
                        AutoAddCardUtils.this.executeMain();
                    }
                }

                public void nuFind() {
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddCardUtils.this.autoService, "添加到通讯录", "发消息", 0, 100, 10, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                            AutoAddCardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            AutoAddCardUtils.this.executeMain();
                        }

                        public void nuFind() {
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executedTask() {
        sleep(100);
        PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
        LogUtils.log("WS_BABY_添加到通讯录.1.1");
        new PerformClickUtils2().checkString(this.autoService, "正在添加", 0, 40, 70, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkIsContactAddress2(AutoAddCardUtils.this.autoService, 0, new PerformClickUtils2.OnCheckListener4() {
                    public void goValidate() {
                    }

                    public void nilAdding() {
                        LogUtils.log("WS_BABY_添加到通讯录.2");
                        new Thread(new Runnable() {
                            public void run() {
                                AutoAddCardUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "视频动态");
                                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "申请添加朋友");
                                if (findNodeIsExistByText || findNodeIsExistByText2) {
                                    AutoAddCardUtils.this.inputSayContent();
                                } else if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "发消息");
                                    boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "添加到通讯录");
                                    if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                        if (findNodeIsExistByText3 && MyApplication.enforceable) {
                                            AutoAddCardUtils.this.updateBottomText(false, 0);
                                            LogUtils.log("WS_BABY_添加到通讯录_END_1");
                                        }
                                        if (AutoAddCardUtils.this.remarkPrefix == null || AutoAddCardUtils.this.remarkPrefix.equals("") || !PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "发消息") || !MyApplication.enforceable) {
                                            AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id) || !MyApplication.enforceable) {
                                                new PerformClickUtils2().checkNodeId(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id, 0, 300, 300, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        LogUtils.log("WS_BABY_添加到通讯录_END2222");
                                                        PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                                        AutoAddCardUtils.this.executeMain();
                                                    }

                                                    public void nuFind() {
                                                    }
                                                });
                                                return;
                                            }
                                            LogUtils.log("WS_BABY_添加到通讯录_END11111");
                                            PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                            AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                            if (!PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "添加到通讯录") || !MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY_添加到通讯录_END11111333333");
                                                AutoAddCardUtils.this.executeMain();
                                                return;
                                            }
                                            LogUtils.log("WS_BABY_添加到通讯录_END1111122222");
                                            PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                                            AutoAddCardUtils.this.executeMain();
                                            return;
                                        }
                                        LogUtils.log("WS_BABY_添加到通讯录_END_2");
                                        AutoAddCardUtils.this.remarkInfo();
                                        return;
                                    }
                                    AutoAddCardUtils.this.sleep(600);
                                    if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                        boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "视频动态");
                                        boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "申请添加朋友");
                                        if ((findNodeIsExistByText5 || findNodeIsExistByText6) && MyApplication.enforceable) {
                                            AutoAddCardUtils.this.inputSayContent();
                                            return;
                                        }
                                        return;
                                    }
                                    try {
                                        LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                                        AutoAddCardUtils.this.updateBottomText(false, 0);
                                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                        AutoAddCardUtils.this.executeMain();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        LogUtils.log("WS_BABY_添加到通讯录.dialog0");
                                        AutoAddCardUtils.this.updateBottomText(false, 0);
                                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                        AutoAddCardUtils.this.executeMain();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "视频动态");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddCardUtils.this.autoService, "申请添加朋友");
                if (findNodeIsExistByText || findNodeIsExistByText2) {
                    AutoAddCardUtils.this.inputSayContent();
                } else if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                    new PerformClickUtils2().checkNodeId(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id, 0, 300, 300, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_添加到通讯录_END2222");
                            PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                            AutoAddCardUtils.this.executeMain();
                        }

                        public void nuFind() {
                        }
                    });
                } else {
                    try {
                        LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                        AutoAddCardUtils.this.updateBottomText(false, 0);
                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                        AutoAddCardUtils.this.executeMain();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void initContactBackInfo() {
        try {
            LogUtils.log("WS_BABY_initContactBackInfo");
            new Thread(new Runnable() {
                public void run() {
                    LogUtils.log("WS_BABY.isBackContactInfoUI.1");
                    AutoAddCardUtils.this.updateBottomText(false, 0);
                    LogUtils.log("WS_BABY.ContactInfoUI.1");
                    AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                    PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                    LogUtils.log("WS_BABY.ContactInfoUI.20");
                    AutoAddCardUtils.this.executeMain();
                }
            }).start();
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    public void initContactFirstInfo() {
        updateBottomText(false, 0);
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "添加到通讯录", "发消息", this.sex == Gender.ALL ? CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION : 1500, 100, 40, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (i == 0 && MyApplication.enforceable) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                LogUtils.log("WS_BABY.ContactInfoUI.2");
                                if (AutoAddCardUtils.this.sex == Gender.MAN || AutoAddCardUtils.this.sex == Gender.WOMEN) {
                                    try {
                                        AccessibilityNodeInfo rootInActiveWindow = AutoAddCardUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow != null) {
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.user_sex);
                                            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.66.不男不女返回");
                                                AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                                PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                                AutoAddCardUtils.this.executeMain();
                                                return;
                                            }
                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                                            if (accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null || !MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.66.不男不女返回");
                                                AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                                PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                                AutoAddCardUtils.this.executeMain();
                                                return;
                                            }
                                            String charSequence = accessibilityNodeInfo.getContentDescription().toString();
                                            if (AutoAddCardUtils.this.sex == Gender.MAN && "男".equals(charSequence) && MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.男");
                                                AutoAddCardUtils.this.addContact();
                                            } else if (AutoAddCardUtils.this.sex != Gender.WOMEN || !"女".equals(charSequence) || !MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.6.不男不女返回");
                                                AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                                PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                                AutoAddCardUtils.this.executeMain();
                                            } else {
                                                LogUtils.log("WS_BABY.ContactInfoUI.女");
                                                AutoAddCardUtils.this.addContact();
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (AutoAddCardUtils.this.sex == Gender.ALL) {
                                            LogUtils.log("WS_BABY.ContactInfoUI.9");
                                            AutoAddCardUtils.this.addContact();
                                            return;
                                        }
                                        LogUtils.log("WS_BABY.ContactInfoUI.6.不男不女返回");
                                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                                        AutoAddCardUtils.this.executeMain();
                                    }
                                } else {
                                    LogUtils.log("WS_BABY.ContactInfoUI.99");
                                    AutoAddCardUtils.this.addContact();
                                }
                            } catch (Exception e2) {
                            }
                        }
                    }).start();
                } else if (i == 1 && MyApplication.enforceable) {
                    new Thread(new Runnable() {
                        public void run() {
                            AutoAddCardUtils.this.updateBottomText(false, 0);
                            AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                            PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                            AutoAddCardUtils.this.executeMain();
                        }
                    }).start();
                }
            }

            public void nuFind() {
                new Thread(new Runnable() {
                    public void run() {
                        LogUtils.log("WS_BABY_发消息@Back2");
                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                        AutoAddCardUtils.this.executeMain();
                    }
                }).start();
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = AutoAddCardUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null) {
                        accessibilityNodeInfo.performAction(16);
                        PerformClickUtils.inputPrefixText(AutoAddCardUtils.this.autoService, AutoAddCardUtils.this.remarkPrefix);
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddCardUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            AutoAddCardUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                            AutoAddCardUtils.this.initContactBackInfo();
                        }
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
        this.sendResult = "";
        this.remarkLabel = operationParameterModel.getSingLabelStr();
        this.jumpTime = 400;
        this.backTime = 600;
        this.executeIndex = 0;
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.recordNames = new ArrayList();
        this.scrollNum = 0;
        this.recordNum = 0;
        this.sayContent = operationParameterModel.getSayContent();
        this.sex = operationParameterModel.getSex();
        this.remarkPrefix = operationParameterModel.getRemarkPrefix();
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void inputSayContent() {
        try {
            if (this.verifyCode == 0) {
                PerformClickUtils.performBack(this.autoService);
                sleep(this.backTime);
                initContactBackInfo();
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.0");
            if (MyApplication.enforceable) {
                try {
                    if (this.sayContent != null && !"".equals(this.sayContent) && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.111");
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_say_content);
                        sleep(100);
                        PerformClickUtils.inputText(this.autoService, this.sayContent);
                    }
                } catch (Exception e) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.sayContent.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
                try {
                    if (this.remarkPrefix != null && !"".equals(this.remarkPrefix) && MyApplication.enforceable) {
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                        sleep(100);
                        PerformClickUtils.inputPrefixText(this.autoService, this.remarkPrefix);
                    }
                } catch (Exception e2) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.3");
                updateBottomText(false, 0);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.4");
                PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
                sleep(300);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.5");
                new PerformClickUtils2().checkIsSending(this.autoService, 0, new PerformClickUtils2.OnCheckListener3() {
                    public void backContactInfo() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.backContactInfo");
                        AutoAddCardUtils.this.initContactBackInfo();
                    }

                    public void executeBack() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack");
                        PerformClickUtils.findViewIdAndClick(AutoAddCardUtils.this.autoService, BaseServiceUtils.back_contact_id);
                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                        AutoAddCardUtils.this.initContactBackInfo();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack_unfind");
                        PerformClickUtils.performBack(AutoAddCardUtils.this.autoService);
                        AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.backTime);
                        AutoAddCardUtils.this.initContactBackInfo();
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
            updateBottomText(false, 0);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            if (this.sendResult == null || "".equals(this.sendResult)) {
                MyWindowManager myWindowManager = this.mMyManager;
                updateMiddleText(myWindowManager, "批量加名片", "共检索了" + this.recordNum + "人");
                return;
            }
            updateMiddleText(this.mMyManager, "批量加名片", this.sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remarkInfo() {
        LogUtils.log("WS_BABY.ContactInfoUI.22");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                AccessibilityNodeInfo rootInActiveWindow = AutoAddCardUtils.this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.right_more_id);
                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.ContactInfoUI.5.点开更多");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                    }
                    AutoAddCardUtils.this.sleep(AutoAddCardUtils.this.jumpTime);
                    AccessibilityNodeInfo rootInActiveWindow2 = AutoAddCardUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.ContactInfoUI.33");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow2.findAccessibilityNodeInfosByText("设置备注和标签");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow2.findAccessibilityNodeInfosByText("设置备注及标签");
                        if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactInfoUI.10.设置备注和标签");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText.get(0));
                            AutoAddCardUtils.this.initContactRemarkInfoModUI();
                        } else if (findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactInfoUI.11.设置备注及标签");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText2.get(0));
                            AutoAddCardUtils.this.initContactRemarkInfoModUI();
                        }
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void updateBottomText(boolean z, int i) {
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "已执行了 " + this.recordNum + " 人");
    }
}
