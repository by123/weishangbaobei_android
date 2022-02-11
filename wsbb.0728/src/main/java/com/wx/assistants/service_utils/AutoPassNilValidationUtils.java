package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class AutoPassNilValidationUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPassNilValidationUtils instance;
    /* access modifiers changed from: private */
    public int executionNum = 0;
    /* access modifiers changed from: private */
    public String filterContent = "";
    /* access modifiers changed from: private */
    public boolean isEnd = false;
    List<AccessibilityNodeInfo> itemList = null;
    List<AccessibilityNodeInfo> itemList2 = null;
    private int jumpNum = 1;
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    /* access modifiers changed from: private */
    public String leaveMsg = "";
    private int maxNum = 5000;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPosit = 1;

    private AutoPassNilValidationUtils() {
    }

    static /* synthetic */ int access$208(AutoPassNilValidationUtils autoPassNilValidationUtils) {
        int i = autoPassNilValidationUtils.startIndex;
        autoPassNilValidationUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPassNilValidationUtils autoPassNilValidationUtils) {
        int i = autoPassNilValidationUtils.executionNum;
        autoPassNilValidationUtils.executionNum = i + 1;
        return i;
    }

    public static AutoPassNilValidationUtils getInstance() {
        instance = new AutoPassNilValidationUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "自动通过免验证，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    PerformClickUtils.findTextAndClick(AutoPassNilValidationUtils.this.autoService, "微信");
                    AutoPassNilValidationUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(AutoPassNilValidationUtils.this.autoService, "微信");
                    AutoPassNilValidationUtils.this.sleep(100);
                    PerformClickUtils.findTextAndClick(AutoPassNilValidationUtils.this.autoService, "微信");
                    AutoPassNilValidationUtils.this.sleep(200);
                    if (AutoPassNilValidationUtils.this.startPosit > 1) {
                        new PerformClickUtils2().check(AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.home_msg_list_item_id, 100, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                AutoPassNilValidationUtils.this.executeJumpMain();
                            }

                            public void nuFind() {
                                BaseServiceUtils.removeEndView(AutoPassNilValidationUtils.this.mMyManager);
                            }
                        });
                    } else {
                        AutoPassNilValidationUtils.this.executeMain();
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void executeCheck() {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        if (this.itemList2 == null) {
            this.itemList2 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, home_msg_list_desc_id);
        }
        if (this.itemList2 != null && this.itemList2.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.itemList2.size() && MyApplication.enforceable) {
                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@4");
                AccessibilityNodeInfo accessibilityNodeInfo = this.itemList2.get(this.startIndex);
                if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                    if (this.executionNum >= this.maxNum) {
                        removeEndView(this.mMyManager);
                        return;
                    }
                    String str = "";
                    if (accessibilityNodeInfo.getText() != null) {
                        str = accessibilityNodeInfo.getText() + "";
                    }
                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@4" + str);
                    if (str == null || "".equals(str)) {
                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@44");
                        this.startIndex++;
                        executeCheck();
                    } else if (str.contains("刚刚把") || str.contains("我通过了你的")) {
                        PerformClickUtils.performClick(accessibilityNodeInfo);
                        this.jumpNum++;
                        SPUtils.put(MyApplication.getConText(), "auto_pass_nil_validation_num", Integer.valueOf(this.jumpNum));
                        new PerformClickUtils2().checkNodeAllIds(this.autoService, contact_title_id, right_more_id, 300, 100, 10, new PerformClickUtils2.OnCheckListener() {
                            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
                                if (r2.contains(com.wx.assistants.service_utils.AutoPassNilValidationUtils.access$300(r7.this$0)) == false) goto L_0x0069;
                             */
                            /* JADX WARNING: Removed duplicated region for block: B:13:0x004b  */
                            /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
                            public void find(int i) {
                                boolean z;
                                String textByNodeId = PerformClickUtils.getTextByNodeId(AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.contact_title_id);
                                if (AutoPassNilValidationUtils.this.filterContent != null && !AutoPassNilValidationUtils.this.filterContent.equals("")) {
                                    if (AutoPassNilValidationUtils.this.filterContent.contains("@")) {
                                        String[] split = AutoPassNilValidationUtils.this.filterContent.split("@");
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= split.length) {
                                                break;
                                            } else if (textByNodeId.contains(split[i2])) {
                                                break;
                                            } else {
                                                i2++;
                                            }
                                        }
                                    }
                                    z = true;
                                    if (!z) {
                                        PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                                            public void find() {
                                                AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                                AutoPassNilValidationUtils.this.executeMain();
                                            }
                                        });
                                        return;
                                    } else {
                                        new PerformClickUtils2().check(AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.send_add_id, 0, 20, 2, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                            public void find(int i) {
                                                System.out.println("WS_BABY_22222");
                                                new PerformClickUtils2().checkString(AutoPassNilValidationUtils.this.autoService, "添加对方为微信好友", 100, 100, 15, new PerformClickUtils2.OnCheckListener() {
                                                    public void find(int i) {
                                                        System.out.println("WS_BABY_44444");
                                                        if (i == 0) {
                                                            PerformClickUtils.findTextAndClick(AutoPassNilValidationUtils.this.autoService, "添加");
                                                            AutoPassNilValidationUtils.access$408(AutoPassNilValidationUtils.this);
                                                            AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                                            MyWindowManager myWindowManager = AutoPassNilValidationUtils.this.mMyManager;
                                                            BaseServiceUtils.updateBottomText(myWindowManager, "自动通过免验证，已通过了 " + AutoPassNilValidationUtils.this.executionNum + "个好友");
                                                            new PerformClickUtils2().checkNilString(AutoPassNilValidationUtils.this.autoService, "正在添加", (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                                                public void find(int i) {
                                                                    if (AutoPassNilValidationUtils.this.leaveMsg == null || "".equals(AutoPassNilValidationUtils.this.leaveMsg)) {
                                                                        PerformClickUtils.executedBackHomeDialogBack(AutoPassNilValidationUtils.this.autoService, 30, false, new PerformClickUtils.OnBackMainPageListener() {
                                                                            public void find() {
                                                                                AutoPassNilValidationUtils.this.executeMain();
                                                                            }
                                                                        });
                                                                    } else {
                                                                        AutoPassNilValidationUtils.this.initFirstChattingUI();
                                                                    }
                                                                }

                                                                public void nuFind() {
                                                                }
                                                            });
                                                        }
                                                    }

                                                    public void nuFind() {
                                                        System.out.println("WS_BABY_5555555");
                                                        PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                                                            public void find() {
                                                                AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                                                AutoPassNilValidationUtils.this.executeMain();
                                                            }
                                                        });
                                                    }
                                                });
                                            }

                                            public void nuFind() {
                                                System.out.println("WS_BABY_3333");
                                                PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                                        AutoPassNilValidationUtils.this.executeMain();
                                                    }
                                                });
                                            }
                                        });
                                        return;
                                    }
                                }
                                z = false;
                                if (!z) {
                                }
                            }

                            public void nuFind() {
                                System.out.println("WS_BABY_00000");
                                PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                                    public void find() {
                                        AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                        AutoPassNilValidationUtils.this.executeMain();
                                    }
                                });
                            }
                        });
                    } else {
                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@44");
                        this.startIndex++;
                        executeCheck();
                    }
                }
            } else if (this.startIndex >= this.itemList2.size() && MyApplication.enforceable) {
                this.itemList2 = null;
                if (this.isEnd || !MyApplication.enforceable) {
                    removeEndView(this.mMyManager);
                    return;
                }
                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@7");
                AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(home_msg_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                    PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@8");
                    sleep(MyApplication.SCROLL_SPEED);
                    AccessibilityNodeInfo rootInActiveWindow2 = this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(home_msg_list_name_id);
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(home_msg_list_desc_id);
                        if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@9");
                            try {
                                String charSequence = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1).getText().toString();
                                String charSequence2 = findAccessibilityNodeInfosByViewId3.get(findAccessibilityNodeInfosByViewId2.size() - 1).getText().toString();
                                if (charSequence != null) {
                                    if (this.lastScrollNickName.equals(charSequence + charSequence2)) {
                                        this.isEnd = true;
                                        removeEndView(this.mMyManager);
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@11" + this.lastScrollNickName + "#" + charSequence);
                                        return;
                                    }
                                }
                                this.lastScrollNickName = charSequence + charSequence2;
                                this.startIndex = 1;
                                executeMain();
                            } catch (Exception e) {
                                this.startIndex = 1;
                                executeMain();
                                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@12");
                            }
                        }
                    }
                }
            }
        }
    }

    public void executeJumpMain() {
        try {
            if (this.itemList == null) {
                this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, home_msg_list_item_id);
            }
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@4");
                this.startIndex++;
                this.jumpNum++;
                if (this.jumpNum >= this.startPosit) {
                    updateBottomText(this.mMyManager, "自动通过免验证，已通过了 " + this.executionNum + "个好友");
                    executeMain();
                    return;
                }
                executeJumpMain();
                updateBottomText(this.mMyManager, "自动通过免验证，已跳过" + this.jumpNum + "条");
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                new PerformClickUtils2().check(this.autoService, home_msg_list_id, 10, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.home_msg_list_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtils.scroll(findViewIdList.get(0));
                            new PerformClickUtils2().check(AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.home_msg_list_name_id, 10, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.home_msg_list_name_id);
                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.home_msg_list_desc_id);
                                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@9");
                                        try {
                                            String charSequence = findViewIdList.get(findViewIdList.size() - 1).getText().toString();
                                            String charSequence2 = findViewIdList2.get(findViewIdList.size() - 1).getText().toString();
                                            if (charSequence != null) {
                                                String access$000 = AutoPassNilValidationUtils.this.lastScrollNickName;
                                                if (access$000.equals(charSequence + charSequence2)) {
                                                    boolean unused = AutoPassNilValidationUtils.this.isEnd = true;
                                                    BaseServiceUtils.removeEndView(AutoPassNilValidationUtils.this.mMyManager);
                                                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@11" + AutoPassNilValidationUtils.this.lastScrollNickName + "#" + charSequence);
                                                    return;
                                                }
                                            }
                                            AutoPassNilValidationUtils autoPassNilValidationUtils = AutoPassNilValidationUtils.this;
                                            String unused2 = autoPassNilValidationUtils.lastScrollNickName = charSequence + charSequence2;
                                            int unused3 = AutoPassNilValidationUtils.this.startIndex = 1;
                                            AutoPassNilValidationUtils.this.executeJumpMain();
                                        } catch (Exception e) {
                                            int unused4 = AutoPassNilValidationUtils.this.startIndex = 1;
                                            AutoPassNilValidationUtils.this.executeJumpMain();
                                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@12");
                                        }
                                    }
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    }

                    public void nuFind() {
                        BaseServiceUtils.removeEndView(AutoPassNilValidationUtils.this.mMyManager);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, home_msg_list_desc_id, 50, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AutoPassNilValidationUtils.this.itemList2 = null;
                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@3");
                    AutoPassNilValidationUtils.this.executeCheck();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.executionNum = 0;
        this.lastScrollNickName = "";
        this.isEnd = false;
        this.jumpNum = 1;
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.filterContent = operationParameterModel.getSayContent();
        this.startPosit = operationParameterModel.getStartIndex();
        this.leaveMsg = operationParameterModel.getMaterialText();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstChattingUI() {
        new PerformClickUtils2().checkNodeId(this.autoService, voice_left_id, 50, 50, 20, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ChattingUI.2");
                PerformClickUtils.findViewByIdAndPasteContent(AutoPassNilValidationUtils.this.autoService, BaseServiceUtils.input_edit_text_id, AutoPassNilValidationUtils.this.leaveMsg);
                LogUtils.log("WS_BABY_ChattingUI.4");
                new PerformClickUtils2().checkString(AutoPassNilValidationUtils.this.autoService, "发送", 50, 100, 25, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(AutoPassNilValidationUtils.this.autoService, "发送");
                        PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                AutoPassNilValidationUtils.this.executeMain();
                            }
                        });
                    }

                    public void nuFind() {
                        PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                                AutoPassNilValidationUtils.this.executeMain();
                            }
                        });
                    }
                });
            }

            public void nuFind() {
                PerformClickUtils.executedBackHome(AutoPassNilValidationUtils.this.autoService, 30, true, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        AutoPassNilValidationUtils.access$208(AutoPassNilValidationUtils.this);
                        AutoPassNilValidationUtils.this.executeMain();
                    }
                });
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "自动通过免验证", "已通过了" + this.executionNum + "个好友请求");
    }
}
