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

    private AutoAddContactsOptionalUtils() {
    }

    public static AutoAddContactsOptionalUtils getInstance() {
        instance = new AutoAddContactsOptionalUtils();
        return instance;
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
                            public void find(int i) {
                                new PerformClickUtils2().checkAddContact(AutoAddContactsOptionalUtils.this.autoService, new PerformClickUtils2.OnCheckListener() {
                                    public void find(int i) {
                                        if (i == 0) {
                                            try {
                                                SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "已添加");
                                            } catch (Exception e) {
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
                                            } catch (Exception e2) {
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

                                    public void nuFind() {
                                    }
                                });
                            }

                            public void nuFind() {
                            }
                        });
                        return;
                    }
                    try {
                        SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "已添加");
                    } catch (Exception e) {
                    }
                    LogUtils.log("WS_BABY_发消息@Back1");
                    PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            AutoAddContactsOptionalUtils.this.executeMain();
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.removeBottomOperationView();
        this.mMyManager.showMiddleView();
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

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactPhone() {
        LogUtils.log("WS_BABY.FTSMainUI.1");
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        new PerformClickUtils2().check(this.autoService, search_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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
                                    public void find(int i) {
                                        if (i == 0) {
                                            try {
                                                if (!MyApplication.enforceable) {
                                                    return;
                                                }
                                                if (PerformClickUtils.findNodeIsExistByText(AutoAddContactsOptionalUtils.this.autoService, "操作过于频繁")) {
                                                    String unused = AutoAddContactsOptionalUtils.this.middleString = "操作过于频繁，请稍后在试";
                                                    BaseServiceUtils.removeEndView(AutoAddContactsOptionalUtils.this.mMyManager);
                                                    return;
                                                }
                                                String unused2 = AutoAddContactsOptionalUtils.this.middleString = "";
                                                try {
                                                    SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "无效微信号");
                                                } catch (Exception e) {
                                                }
                                                MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                                BaseServiceUtils.updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人");
                                                LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c0");
                                                PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        AutoAddContactsOptionalUtils.this.executeMain();
                                                    }
                                                });
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        } else {
                                            AutoAddContactsOptionalUtils.this.initFirstContactInfo();
                                        }
                                    }

                                    public void nuFind() {
                                    }
                                });
                            }
                        }
                    }

                    public void nuFind() {
                        try {
                            SQLiteUtils.getInstance().updateContact(AutoAddContactsOptionalUtils.this.currentContactPhone, "无效微信号");
                        } catch (Exception e) {
                        }
                        int unused = AutoAddContactsOptionalUtils.this.recordSearchNum = AutoAddContactsOptionalUtils.this.recordSearchNum + 1;
                        AutoAddContactsOptionalUtils.this.fans.remove(0);
                        PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsOptionalUtils.this.executeMain();
                            }
                        });
                    }
                });
            }

            public void nuFind() {
            }
        });
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
                            } catch (Exception e) {
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
                            public void end() {
                                AutoAddContactsOptionalUtils.this.initContactPhone();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = AutoAddContactsOptionalUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + AutoAddContactsOptionalUtils.this.recordSearchNum + " 人，待检索 " + AutoAddContactsOptionalUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
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

    public void inputSayContent() {
        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.0");
        try {
            SQLiteUtils.getInstance().updateContact(this.currentContactPhone, "待好友通过");
        } catch (Exception e) {
        }
        try {
            if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.1");
                try {
                    if (this.sayContent != null && !this.sayContent.equals("")) {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.111");
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_say_content);
                        sleep(50);
                        PerformClickUtils.inputText(this.autoService, this.sayContent);
                    }
                } catch (Exception e2) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.sayContent.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
                try {
                    if (this.remarkPrefix) {
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                        sleep(50);
                        PerformClickUtils.inputPrefixText(this.autoService, this.currentContactName);
                    }
                } catch (Exception e3) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error");
                }
                sleep(executeSpeed + executeSpeedSetting);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.3");
                PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
                new PerformClickUtils2().checkIsSending(this.autoService, 300, new PerformClickUtils2.OnCheckListener3() {
                    public void backContactInfo() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.backContactInfo");
                        PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsOptionalUtils.this.executeMain();
                            }
                        });
                    }

                    public void executeBack() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack");
                        PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsOptionalUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack_unfind");
                        PerformClickUtils.executedBackHome(AutoAddContactsOptionalUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddContactsOptionalUtils.this.executeMain();
                            }
                        });
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "自选添加手机联系人\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void middleViewDismiss() {
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
}
