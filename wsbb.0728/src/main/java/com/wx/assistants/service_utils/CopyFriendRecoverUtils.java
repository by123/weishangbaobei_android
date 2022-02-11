package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class CopyFriendRecoverUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CopyFriendRecoverUtils instance;
    private int breakAdd = 0;
    /* access modifiers changed from: private */
    public String currentContactName = "";
    /* access modifiers changed from: private */
    public String currentContactPhone = "";
    /* access modifiers changed from: private */
    public LinkedHashSet<CopyFriendBean> fans = new LinkedHashSet<>();
    private int maxNum = 20;
    /* access modifiers changed from: private */
    public int recordSearchNum = 1;
    private boolean remarkPrefix;
    private String sayContent;
    private int spaceTime = 0;

    private CopyFriendRecoverUtils() {
    }

    public static CopyFriendRecoverUtils getInstance() {
        instance = new CopyFriendRecoverUtils();
        return instance;
    }

    public void addContact() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
            LogUtils.log("WS_BABY_添加到通讯录.1.1");
            new PerformClickUtils2().checkIsContactAddress(this.autoService, 100, new PerformClickUtils2.OnCheckListener4() {
                public void goValidate() {
                    CopyFriendRecoverUtils.this.inputSayContent();
                }

                public void nilAdding() {
                    LogUtils.log("WS_BABY_添加到通讯录.2");
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                CopyFriendRecoverUtils.this.sleep(600);
                                if (PerformClickUtils.findNodeIsExistById((AccessibilityService) CopyFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id)) {
                                    try {
                                        LogUtils.log("WS_BABY_添加到通讯录.dialog0");
                                        MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "自动恢复的好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CopyFriendRecoverUtils.this.executeMain();
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CopyFriendRecoverUtils.this.autoService, "发消息");
                                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CopyFriendRecoverUtils.this.autoService, "添加到通讯录");
                                    if (findNodeIsExistByText || findNodeIsExistByText2) {
                                        if (findNodeIsExistByText) {
                                            LogUtils.log("WS_BABY_添加到通讯录_END_1");
                                            MyWindowManager myWindowManager2 = CopyFriendRecoverUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager2, "自动恢复的好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                                        }
                                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                CopyFriendRecoverUtils.this.executeMain();
                                            }
                                        });
                                        return;
                                    }
                                    CopyFriendRecoverUtils.this.sleep(600);
                                    if (PerformClickUtils.findNodeIsExistById((AccessibilityService) CopyFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id)) {
                                        try {
                                            LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                                            MyWindowManager myWindowManager3 = CopyFriendRecoverUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager3, "自动恢复的好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                                            PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    CopyFriendRecoverUtils.this.executeMain();
                                                }
                                            });
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    } else {
                                        LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                        boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(CopyFriendRecoverUtils.this.autoService, "视频动态");
                                        boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(CopyFriendRecoverUtils.this.autoService, "申请添加朋友");
                                        if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                            CopyFriendRecoverUtils.this.inputSayContent();
                                        }
                                    }
                                }
                            } catch (Exception e3) {
                            }
                        }
                    }).start();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.removeBottomOperationView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在恢复的好友，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        CopyFriendRecoverUtils.this.executeMain();
                    } catch (Exception e) {
                    }
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
            new PerformClickUtils2().checkNodeId(this.autoService, contact_nav_search_viewgroup_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CopyFriendRecoverUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(CopyFriendRecoverUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.get(0) == null)) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        CopyFriendRecoverUtils.this.initFirstFTSMain();
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
        updateBottomText(myWindowManager, "自动恢复的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        new PerformClickUtils2().check(this.autoService, search_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(CopyFriendRecoverUtils.this.autoService, BaseServiceUtils.search_id);
                CopyFriendRecoverUtils.this.sleep(350);
                Iterator it = CopyFriendRecoverUtils.this.fans.iterator();
                if (it.hasNext()) {
                    final CopyFriendBean copyFriendBean = (CopyFriendBean) it.next();
                    String unused = CopyFriendRecoverUtils.this.currentContactPhone = copyFriendBean.getWxNum();
                    String unused2 = CopyFriendRecoverUtils.this.currentContactName = copyFriendBean.getWxNickName();
                    CopyFriendRecoverUtils.this.updateCopyFriendInfoAdded(copyFriendBean);
                    PerformClickUtils.inputText(CopyFriendRecoverUtils.this.autoService, CopyFriendRecoverUtils.this.currentContactPhone);
                    CopyFriendRecoverUtils.this.sleep(100);
                    PerformClickUtils.hideInputKey(CopyFriendRecoverUtils.this.autoService);
                    new PerformClickUtils2().checkString(CopyFriendRecoverUtils.this.autoService, "查找微信号", 600, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 10, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (MyApplication.enforceable) {
                                CopyFriendRecoverUtils.this.fans.remove(copyFriendBean);
                                int unused = CopyFriendRecoverUtils.this.recordSearchNum = CopyFriendRecoverUtils.this.recordSearchNum + 1;
                                LogUtils.log("WS_BABY.FTSMainUI.2");
                                if (MyApplication.enforceable) {
                                    PerformClickUtils.findTextAndClick(CopyFriendRecoverUtils.this.autoService, "查找微信号");
                                    MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "正在添加：\n" + CopyFriendRecoverUtils.this.currentContactName + "【微信号:" + CopyFriendRecoverUtils.this.currentContactPhone + "】");
                                    new PerformClickUtils2().checkNodeIdNameHasSomeOne3(CopyFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "添加到通讯录", "发消息", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 200, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            if (i == 0) {
                                                LogUtils.log("WS_BABY.FTSMainUI.3");
                                                try {
                                                    if (MyApplication.enforceable) {
                                                        MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                                                        BaseServiceUtils.updateBottomText(myWindowManager, "自动恢复的好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                                                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c0");
                                                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                CopyFriendRecoverUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            } else if (i == 1) {
                                                LogUtils.log("WS_BABY.FTSMainUI.4");
                                                CopyFriendRecoverUtils.this.initFirstContactInfo();
                                            } else {
                                                LogUtils.log("WS_BABY.FTSMainUI.5");
                                                PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        CopyFriendRecoverUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        }

                                        public void nuFind() {
                                        }
                                    });
                                }
                            }
                        }

                        public void nuFind() {
                            int unused = CopyFriendRecoverUtils.this.recordSearchNum = CopyFriendRecoverUtils.this.recordSearchNum + 1;
                            CopyFriendRecoverUtils.this.fans.remove(copyFriendBean);
                            PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CopyFriendRecoverUtils.this.executeMain();
                                }
                            });
                        }
                    });
                }
            }

            public void nuFind() {
            }
        });
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.sayContent = "";
        this.recordSearchNum = 0;
        this.currentContactPhone = "";
        this.currentContactName = "";
        this.breakAdd = operationParameterModel.isBreakAdd();
        this.fans = operationParameterModel.getCopyFriends();
        this.sayContent = operationParameterModel.getSayContent();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.remarkPrefix = operationParameterModel.isAutoRemarkContactsName();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstContactInfo() {
        try {
            if (this.breakAdd == 0) {
                LogUtils.log("WS_BABY.ContactInfoUI.2");
                MyWindowManager myWindowManager = this.mMyManager;
                updateBottomText(myWindowManager, "自动恢复的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
                LogUtils.log("WS_BABY.ContactInfoUI.9");
                addContact();
                return;
            }
            removeBottomView(this.mMyManager);
            updateBottomOperationLayout(this.mMyManager, "由于您设置了断点添加，请执行断点操作。", "跳过，执行下一个", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(CopyFriendRecoverUtils.this.mMyManager);
                    MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自动恢复好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                    PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            CopyFriendRecoverUtils.this.executeMain();
                        }
                    });
                }
            }, "允许添加", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(CopyFriendRecoverUtils.this.mMyManager);
                    LogUtils.log("WS_BABY.ContactInfoUI.222222222");
                    MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自动恢复好友\n已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人");
                    CopyFriendRecoverUtils.this.addContact();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    public void initFirstFTSMain() {
        try {
            LogUtils.log("WS_BABY.FTSMainUI.into");
            try {
                if (this.fans == null || this.fans.size() <= 0 || !MyApplication.enforceable) {
                    removeEndView(this.mMyManager);
                } else if (this.maxNum >= 1 && MyApplication.enforceable) {
                    if (this.recordSearchNum == this.maxNum && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.FTSMainUI.end");
                        removeEndView(this.mMyManager);
                    } else if (this.spaceTime <= 0 || this.recordSearchNum <= 0) {
                        initContactPhone();
                    } else {
                        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void end() {
                                CopyFriendRecoverUtils.this.initContactPhone();
                            }

                            public void surplus(int i) {
                                MyWindowManager myWindowManager = CopyFriendRecoverUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + CopyFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + CopyFriendRecoverUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void inputSayContent() {
        try {
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.0");
            if (MyApplication.enforceable) {
                try {
                    if (this.sayContent != null && !this.sayContent.equals("")) {
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
                    if (this.remarkPrefix) {
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                        sleep(100);
                        PerformClickUtils.inputPrefixText(this.autoService, this.currentContactName);
                    }
                } catch (Exception e2) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.3");
                sleep(executeSpeed + executeSpeedSetting);
                PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
                new PerformClickUtils2().checkIsSending(this.autoService, 300, new PerformClickUtils2.OnCheckListener3() {
                    public void backContactInfo() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.backContactInfo");
                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                CopyFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }

                    public void executeBack() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack");
                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                CopyFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack_unfind");
                        PerformClickUtils.executedBackHome(CopyFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                CopyFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "自动恢复的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "自动恢复的好友", "共检索了" + this.recordSearchNum + "个好友");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
