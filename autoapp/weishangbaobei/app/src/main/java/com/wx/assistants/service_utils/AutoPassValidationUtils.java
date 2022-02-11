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
import java.util.List;

public class AutoPassValidationUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPassValidationUtils instance;
    /* access modifiers changed from: private */
    public int executeIndex = 0;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastScrollNickName = "";
    /* access modifiers changed from: private */
    public int recordCount = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public String singleLabel = "";

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$108(AutoPassValidationUtils autoPassValidationUtils) {
        int i = autoPassValidationUtils.recordCount;
        autoPassValidationUtils.recordCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(AutoPassValidationUtils autoPassValidationUtils) {
        int i = autoPassValidationUtils.executeIndex;
        autoPassValidationUtils.executeIndex = i + 1;
        return i;
    }

    private AutoPassValidationUtils() {
    }

    public static AutoPassValidationUtils getInstance() {
        instance = new AutoPassValidationUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.lastScrollNickName = "";
        this.isWhile = true;
        this.executeIndex = 0;
        this.recordCount = 0;
        this.singleLabel = operationParameterModel.getSingLabelStr();
        this.sayContent = operationParameterModel.getSayContent();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initSaySayHiWithSnsPermissionUI() {
        new PerformClickUtils2().checkString(this.autoService, "朋友验证", executeSpeed + (executeSpeedSetting / 2), 100, 80, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI");
                if (AutoPassValidationUtils.this.autoService.getRootInActiveWindow() != null) {
                    if (AutoPassValidationUtils.this.sayContent != null && !"".equals(AutoPassValidationUtils.this.sayContent)) {
                        PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.input_remark);
                        AutoPassValidationUtils.this.sleep(100);
                        PerformClickUtils.inputPrefixText(AutoPassValidationUtils.this.autoService, AutoPassValidationUtils.this.sayContent);
                    }
                    AutoPassValidationUtils.this.excCompleted();
                }
            }
        });
    }

    public void excCompleted() {
        new PerformClickUtils2().check(this.autoService, complete_id, executeSpeed + executeSpeedSetting, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                AutoPassValidationUtils.access$108(AutoPassValidationUtils.this);
                PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.complete_id);
                MyWindowManager myWindowManager = AutoPassValidationUtils.this.mMyManager;
                BaseServiceUtils.updateBottomText(myWindowManager, "正在自动接受好友请求，请不要操作微信 \n已接受 " + AutoPassValidationUtils.this.recordCount + " 人");
                new PerformClickUtils2().checkNilString(AutoPassValidationUtils.this.autoService, "正在处理", (int) SocializeConstants.CANCLE_RESULTCODE, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        new PerformClickUtils2().checkAutoPassValidation(AutoPassValidationUtils.this.autoService, BaseServiceUtils.new_friends_list_id, "朋友验证", BaseServiceUtils.msg_layout, BaseServiceUtils.dialog_ok_id, "前往验证", SocializeConstants.CANCLE_RESULTCODE, 100, 30, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                if (i == 0) {
                                    AutoPassValidationUtils.this.sleep(100);
                                    LogUtils.log("WS_BABY_FMessageConversationUI.-2");
                                    AutoPassValidationUtils.this.excAutoPassValidation();
                                } else if (i == 1) {
                                    AutoPassValidationUtils.access$308(AutoPassValidationUtils.this);
                                    PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                    AutoPassValidationUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    LogUtils.log("WS_BABY_FMessageConversationUI..-1");
                                    new PerformClickUtils2().checkString(AutoPassValidationUtils.this.autoService, "前往验证", 100, 1, 2, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                            AutoPassValidationUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            AutoPassValidationUtils.this.excAutoPassValidation();
                                        }

                                        public void nuFind() {
                                            AutoPassValidationUtils.this.excAutoPassValidation();
                                        }
                                    });
                                } else if (i == 2) {
                                    LogUtils.log("WS_BABY_ContactInfoUI");
                                    PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                    AutoPassValidationUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    LogUtils.log("WS_BABY_FMessageConversationUI...0");
                                    AutoPassValidationUtils.this.excAutoPassValidation();
                                } else if (i == 3) {
                                    PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                    AutoPassValidationUtils.this.sleep(BannerConfig.DURATION);
                                    AutoPassValidationUtils.access$308(AutoPassValidationUtils.this);
                                    PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                    AutoPassValidationUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    LogUtils.log("WS_BABY_FMessageConversationUI..1");
                                    AutoPassValidationUtils.this.excAutoPassValidation();
                                } else if (i == 4) {
                                    AutoPassValidationUtils.access$308(AutoPassValidationUtils.this);
                                    PerformClickUtils.performBack(AutoPassValidationUtils.this.autoService);
                                    AutoPassValidationUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                    LogUtils.log("WS_BABY_FMessageConversationUI..2");
                                    AutoPassValidationUtils.this.excAutoPassValidation();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void excAutoPassValidation() {
        try {
            if (!this.isWhile && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_1");
                removeEndView(this.mMyManager);
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_layout_id, list_layout_name, executeSpeedSetting + executeSpeed, 250, 10, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_2");
                    new Thread(new Runnable() {
                        public void run() {
                            while (AutoPassValidationUtils.this.isWhile && MyApplication.enforceable) {
                                try {
                                    AccessibilityNodeInfo rootInActiveWindow = AutoPassValidationUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow == null) {
                                        boolean unused = AutoPassValidationUtils.this.isWhile = false;
                                        LogUtils.log("WS_BABY_22");
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.new_friends_list_id);
                                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                        BaseServiceUtils.removeEndView(AutoPassValidationUtils.this.mMyManager);
                                    } else {
                                        LogUtils.log("WS_BABY_3");
                                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                                        AccessibilityNodeInfo rootInActiveWindow2 = AutoPassValidationUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null || !MyApplication.enforceable) {
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_name);
                                            if (findAccessibilityNodeInfosByViewId2 != null) {
                                                if (findAccessibilityNodeInfosByViewId2.size() != 0) {
                                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_accept_id);
                                                    if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() - AutoPassValidationUtils.this.executeIndex <= 0 || !MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_10");
                                                        PerformClickUtils.scroll(accessibilityNodeInfo);
                                                        AutoPassValidationUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                                        int unused2 = AutoPassValidationUtils.this.executeIndex = 0;
                                                        new PerformClickUtils2().check(AutoPassValidationUtils.this.autoService, BaseServiceUtils.list_layout_name, 20, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                            public void nuFind() {
                                                            }

                                                            public void find(int i) {
                                                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPassValidationUtils.this.autoService, BaseServiceUtils.list_layout_name);
                                                                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                                                    String str = "";
                                                                    CharSequence text = findViewIdList.get(findViewIdList.size() - 1).getText();
                                                                    if (text != null && !"".equals(text)) {
                                                                        str = text.toString();
                                                                    }
                                                                    if (str.equals(AutoPassValidationUtils.this.lastScrollNickName)) {
                                                                        LogUtils.log("WS_BABY_11");
                                                                        boolean unused = AutoPassValidationUtils.this.isWhile = false;
                                                                        BaseServiceUtils.removeEndView(AutoPassValidationUtils.this.mMyManager);
                                                                        return;
                                                                    }
                                                                    LogUtils.log("WS_BABY_12");
                                                                    String unused2 = AutoPassValidationUtils.this.lastScrollNickName = str;
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        LogUtils.log("WS_BABY_7");
                                                        AutoPassValidationUtils.this.sleep(300);
                                                        AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId3.get(AutoPassValidationUtils.this.executeIndex);
                                                        if (accessibilityNodeInfo2 != null) {
                                                            try {
                                                                if (MyApplication.enforceable) {
                                                                    LogUtils.log("WS_BABY_8_accept");
                                                                    if (AutoPassValidationUtils.this.singleLabel == null || "".equals(AutoPassValidationUtils.this.singleLabel)) {
                                                                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                                                                        new PerformClickUtils2().checkNodeIdOrName(AutoPassValidationUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "朋友验证", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                            public void nuFind() {
                                                                            }

                                                                            public void find(int i) {
                                                                                if (i == 0) {
                                                                                    PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                                                }
                                                                                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoPassValidationUtils.this.autoService, "前往验证", "朋友验证", 200, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                                    public void nuFind() {
                                                                                    }

                                                                                    public void find(int i) {
                                                                                        if (i == 0) {
                                                                                            PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "前往验证");
                                                                                            AutoPassValidationUtils.this.initSaySayHiWithSnsPermissionUI();
                                                                                            return;
                                                                                        }
                                                                                        AutoPassValidationUtils.this.initSaySayHiWithSnsPermissionUI();
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        return;
                                                                    }
                                                                    PerformClickUtils.performClickEnabled(accessibilityNodeInfo2.getParent());
                                                                    new PerformClickUtils2().checkNodeIdOrName(AutoPassValidationUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "设置备注和标签", BaseServiceUtils.executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                                        public void nuFind() {
                                                                        }

                                                                        public void find(int i) {
                                                                            if (i == 0) {
                                                                                PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                                                            }
                                                                            AutoPassValidationUtils.this.goRemarkLabel();
                                                                        }
                                                                    });
                                                                    return;
                                                                }
                                                            } catch (Exception unused3) {
                                                                LogUtils.log("WS_BABY_9");
                                                            }
                                                        } else {
                                                            continue;
                                                        }
                                                    }
                                                }
                                            }
                                            LogUtils.log("WS_BABY_6");
                                            BaseServiceUtils.removeEndView(AutoPassValidationUtils.this.mMyManager);
                                            return;
                                        }
                                        LogUtils.log("WS_BABY_4");
                                        BaseServiceUtils.removeEndView(AutoPassValidationUtils.this.mMyManager);
                                        return;
                                    }
                                } catch (Exception unused4) {
                                    return;
                                }
                            }
                        }
                    }).start();
                }

                public void nuFind() {
                    BaseServiceUtils.removeEndView(AutoPassValidationUtils.this.mMyManager);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().check(this.autoService, news_friend_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPassValidationUtils.this.autoService, BaseServiceUtils.news_friend_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        PerformClickUtils.performClick(findViewIdList.get(0));
                        LogUtils.log("WS_BABY_FMessageConversationUI.11");
                        AutoPassValidationUtils.this.excAutoPassValidation();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goRemarkLabel() {
        if (this.singleLabel == null || "".equals(this.singleLabel)) {
            goInput();
        } else {
            new PerformClickUtils2().checkString(this.autoService, "设置备注和标签", executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactInfoUI_2");
                    PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "设置备注和标签");
                    new PerformClickUtils2().checkString(AutoPassValidationUtils.this.autoService, "保存", BaseServiceUtils.executeSpeed + 150 + BaseServiceUtils.executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            AutoPassValidationUtils.this.sleep(100);
                            if (PerformClickUtils.findNodeIsExistByText(AutoPassValidationUtils.this.autoService, AutoPassValidationUtils.this.singleLabel)) {
                                PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "保存");
                                AutoPassValidationUtils.this.goInput();
                                return;
                            }
                            new PerformClickUtils2().checkNodeIds(AutoPassValidationUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(AutoPassValidationUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(AutoPassValidationUtils.this.autoService, AutoPassValidationUtils.this.singleLabel, BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 2), 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, AutoPassValidationUtils.this.singleLabel);
                                            AutoPassValidationUtils.this.sleep(300);
                                            PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(AutoPassValidationUtils.this.autoService, "保存", (BaseServiceUtils.executeSpeedSetting / 2) + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "保存");
                                                    AutoPassValidationUtils.this.goInput();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    AutoPassValidationUtils.this.goInput();
                }
            });
        }
    }

    public void goInput() {
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "通过验证", "前往验证", executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY_go_input." + i);
                PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "前往验证");
                PerformClickUtils.findTextAndClick(AutoPassValidationUtils.this.autoService, "通过验证");
                AutoPassValidationUtils.this.initSaySayHiWithSnsPermissionUI();
            }
        });
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "自动接受好友请求", "成功接受好友请求" + this.recordCount + "人");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "自动通过好友验证请求，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    AutoPassValidationUtils.this.executeMain();
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
