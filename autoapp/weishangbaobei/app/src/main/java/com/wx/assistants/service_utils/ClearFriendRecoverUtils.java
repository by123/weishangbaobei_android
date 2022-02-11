package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.Enity.RecoverFriendBean;
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

public class ClearFriendRecoverUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static ClearFriendRecoverUtils instance;
    private int breakAdd = 0;
    /* access modifiers changed from: private */
    public String currentContactName = "";
    /* access modifiers changed from: private */
    public String currentContactPhone = "";
    /* access modifiers changed from: private */
    public LinkedHashSet<RecoverFriendBean> fans = new LinkedHashSet<>();
    private int maxNum = 20;
    /* access modifiers changed from: private */
    public int recordSearchNum = 1;
    private boolean remarkPrefix;
    private String sayContent;
    private int spaceTime = 0;

    public void middleViewDismiss() {
    }

    private ClearFriendRecoverUtils() {
    }

    public static ClearFriendRecoverUtils getInstance() {
        instance = new ClearFriendRecoverUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.sayContent = "";
        this.recordSearchNum = 0;
        this.currentContactPhone = "";
        this.currentContactName = "";
        this.breakAdd = operationParameterModel.isBreakAdd();
        this.fans = operationParameterModel.getRecoverFriends();
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
                updateBottomText(myWindowManager, "自动恢复删除的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
                addContact();
                return;
            }
            removeBottomView(this.mMyManager);
            updateBottomOperationLayout(this.mMyManager, "由于您设置了断点添加，请执行断点操作。", "跳过，执行下一个", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(ClearFriendRecoverUtils.this.mMyManager);
                    MyWindowManager myWindowManager = ClearFriendRecoverUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自动恢复删除的好友\n已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人");
                    PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            ClearFriendRecoverUtils.this.executeMain();
                        }
                    });
                }
            }, "允许添加", new View.OnClickListener() {
                public void onClick(View view) {
                    BaseServiceUtils.removeBottomOperationView(ClearFriendRecoverUtils.this.mMyManager);
                    LogUtils.log("WS_BABY.ContactInfoUI.222222222");
                    MyWindowManager myWindowManager = ClearFriendRecoverUtils.this.mMyManager;
                    BaseServiceUtils.showBottomView(myWindowManager, "自动恢复删除的好友\n已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人");
                    ClearFriendRecoverUtils.this.addContact();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
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
                } catch (Exception unused) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.sayContent.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
                try {
                    if (this.remarkPrefix) {
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                        sleep(100);
                        PerformClickUtils.inputPrefixText(this.autoService, this.currentContactName);
                    }
                } catch (Exception unused2) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error");
                }
                sleep(executeSpeed + executeSpeedSetting);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.4");
                PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
                new PerformClickUtils2().checkIsSending(this.autoService, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new PerformClickUtils2.OnCheckListener3() {
                    public void executeBack() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack");
                        PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                ClearFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }

                    public void backContactInfo() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.backContactInfo");
                        PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                ClearFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack_unfind");
                        PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                ClearFriendRecoverUtils.this.executeMain();
                            }
                        });
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "自动恢复删除的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContact() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
            new PerformClickUtils2().checkIsContactAddress(this.autoService, 100, new PerformClickUtils2.OnCheckListener4() {
                public void nuFind() {
                }

                public void nilAdding() {
                    LogUtils.log("WS_BABY_添加到通讯录.2");
                    new Thread(new Runnable() {
                        public void run() {
                            ClearFriendRecoverUtils.this.sleep(600);
                            if (PerformClickUtils.findNodeIsExistById((AccessibilityService) ClearFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id)) {
                                try {
                                    LogUtils.log("WS_BABY_添加到通讯录.dialog0");
                                    MyWindowManager myWindowManager = ClearFriendRecoverUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "自动恢复删除的好友\n已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人");
                                    PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            ClearFriendRecoverUtils.this.executeMain();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(ClearFriendRecoverUtils.this.autoService, "发消息");
                                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(ClearFriendRecoverUtils.this.autoService, "添加到通讯录");
                                if (findNodeIsExistByText || findNodeIsExistByText2) {
                                    if (findNodeIsExistByText) {
                                        MyWindowManager myWindowManager2 = ClearFriendRecoverUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager2, "自动恢复删除的好友\n已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人");
                                    }
                                    PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            ClearFriendRecoverUtils.this.executeMain();
                                        }
                                    });
                                    return;
                                }
                                ClearFriendRecoverUtils.this.sleep(600);
                                if (PerformClickUtils.findNodeIsExistById((AccessibilityService) ClearFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id)) {
                                    try {
                                        LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                                        MyWindowManager myWindowManager3 = ClearFriendRecoverUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager3, "自动恢复删除的好友\n已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人");
                                        PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                ClearFriendRecoverUtils.this.executeMain();
                                            }
                                        });
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                } else {
                                    LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(ClearFriendRecoverUtils.this.autoService, "视频动态");
                                    boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(ClearFriendRecoverUtils.this.autoService, "申请添加朋友");
                                    if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                        ClearFriendRecoverUtils.this.inputSayContent();
                                    }
                                }
                            }
                        }
                    }).start();
                }

                public void goValidate() {
                    ClearFriendRecoverUtils.this.inputSayContent();
                }
            });
        } catch (Exception e) {
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
                            public void surplus(int i) {
                                MyWindowManager myWindowManager = ClearFriendRecoverUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "已检索 " + ClearFriendRecoverUtils.this.recordSearchNum + " 人，待检索 " + ClearFriendRecoverUtils.this.fans.size() + " 人\n正在延时等待，倒计时 " + i + " 秒");
                            }

                            public void end() {
                                ClearFriendRecoverUtils.this.initContactPhone();
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

    public void initContactPhone() {
        LogUtils.log("WS_BABY.FTSMainUI.1");
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "自动恢复删除的好友\n已检索 " + this.recordSearchNum + " 人，待检索 " + this.fans.size() + " 人");
        new PerformClickUtils2().check(this.autoService, search_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(ClearFriendRecoverUtils.this.autoService, BaseServiceUtils.search_id);
                ClearFriendRecoverUtils.this.sleep(350);
                Iterator it = ClearFriendRecoverUtils.this.fans.iterator();
                if (it.hasNext()) {
                    final RecoverFriendBean recoverFriendBean = (RecoverFriendBean) it.next();
                    String unused = ClearFriendRecoverUtils.this.currentContactPhone = recoverFriendBean.getWxNum();
                    String unused2 = ClearFriendRecoverUtils.this.currentContactName = recoverFriendBean.getWxNickName();
                    ClearFriendRecoverUtils.this.updateFriendInfoAdded(recoverFriendBean);
                    PerformClickUtils.inputText(ClearFriendRecoverUtils.this.autoService, ClearFriendRecoverUtils.this.currentContactPhone);
                    ClearFriendRecoverUtils.this.sleep(100);
                    PerformClickUtils.hideInputKey(ClearFriendRecoverUtils.this.autoService);
                    new PerformClickUtils2().checkString(ClearFriendRecoverUtils.this.autoService, "查找微信号", 600, SocializeConstants.CANCLE_RESULTCODE, 5, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            if (MyApplication.enforceable) {
                                ClearFriendRecoverUtils.this.fans.remove(recoverFriendBean);
                                int unused = ClearFriendRecoverUtils.this.recordSearchNum = ClearFriendRecoverUtils.this.recordSearchNum + 1;
                                LogUtils.log("WS_BABY.FTSMainUI.2");
                                if (MyApplication.enforceable) {
                                    PerformClickUtils.findTextAndClick(ClearFriendRecoverUtils.this.autoService, "查找微信号");
                                    MyWindowManager myWindowManager = ClearFriendRecoverUtils.this.mMyManager;
                                    BaseServiceUtils.updateBottomText(myWindowManager, "正在添加：\n" + ClearFriendRecoverUtils.this.currentContactName + "【微信号:" + ClearFriendRecoverUtils.this.currentContactPhone + "】");
                                    new PerformClickUtils2().checkNodeIdNameHasSomeOne3(ClearFriendRecoverUtils.this.autoService, BaseServiceUtils.dialog_ok_id, "添加到通讯录", "发消息", BaseServiceUtils.executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            if (i == 0) {
                                                LogUtils.log("WS_BABY.FTSMainUI.3");
                                                PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        ClearFriendRecoverUtils.this.executeMain();
                                                    }
                                                });
                                            } else if (i == 1) {
                                                LogUtils.log("WS_BABY.FTSMainUI.4");
                                                ClearFriendRecoverUtils.this.initFirstContactInfo();
                                            } else {
                                                PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        ClearFriendRecoverUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        }

                        public void nuFind() {
                            int unused = ClearFriendRecoverUtils.this.recordSearchNum = ClearFriendRecoverUtils.this.recordSearchNum + 1;
                            ClearFriendRecoverUtils.this.fans.remove(recoverFriendBean);
                            PerformClickUtils.executedBackHome(ClearFriendRecoverUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    ClearFriendRecoverUtils.this.executeMain();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().checkNodeId(this.autoService, contact_nav_search_viewgroup_id, executeSpeed + executeSpeedSetting, 100, 10, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) ClearFriendRecoverUtils.this.autoService, BaseServiceUtils.contact_nav_search_viewgroup_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(ClearFriendRecoverUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = findViewIdList.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.get(0) == null)) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                            }
                        }
                        ClearFriendRecoverUtils.this.initFirstFTSMain();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            updateMiddleText(myWindowManager, "自动恢复删除的好友", "共检索了" + this.recordSearchNum + "个好友");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在恢复删除的好友，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ClearFriendRecoverUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
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
