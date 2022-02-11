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
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoAddContactsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddContactsUtils instance;
    private AccessibilityNodeInfo accessibilityNodeInfoAdd = null;
    /* access modifiers changed from: private */
    public int countSend = 200;
    private int ffModel = 0;
    private int ffModelEndTime = 10;
    private int ffModelStartTime = 0;
    private boolean isAutoRemarkContactsName = true;
    private boolean isFirstExecute = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    List<AccessibilityNodeInfo> itemList = null;
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    List<AccessibilityNodeInfo> listName1 = null;
    List<AccessibilityNodeInfo> listName2 = null;
    List<AccessibilityNodeInfo> listViews = null;
    private int maxNum = 50;
    private LinkedHashSet<String> recordData = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int recordSendNum = 0;
    private String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollCount = 0;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 1;

    private AutoAddContactsUtils() {
    }

    static /* synthetic */ int access$108(AutoAddContactsUtils autoAddContactsUtils) {
        int i = autoAddContactsUtils.recordSendNum;
        autoAddContactsUtils.recordSendNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoAddContactsUtils autoAddContactsUtils) {
        int i = autoAddContactsUtils.startIndex;
        autoAddContactsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(AutoAddContactsUtils autoAddContactsUtils) {
        int i = autoAddContactsUtils.scrollCount;
        autoAddContactsUtils.scrollCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$510(AutoAddContactsUtils autoAddContactsUtils) {
        int i = autoAddContactsUtils.scrollCount;
        autoAddContactsUtils.scrollCount = i - 1;
        return i;
    }

    public static AutoAddContactsUtils getInstance() {
        instance = new AutoAddContactsUtils();
        return instance;
    }

    public void addContact() {
        PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
        LogUtils.log("WS_BABY_添加到通讯录.1.1");
        new PerformClickUtils2().checkString(this.autoService, "正在添加", 0, 40, 70, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkIsContactAddress2(AutoAddContactsUtils.this.autoService, 0, new PerformClickUtils2.OnCheckListener4() {
                    public void goValidate() {
                    }

                    public void nilAdding() {
                        LogUtils.log("WS_BABY_添加到通讯录.2");
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    AutoAddContactsUtils.this.sleep(1500);
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "视频动态");
                                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "申请添加朋友");
                                    if (findNodeIsExistByText || findNodeIsExistByText2) {
                                        AutoAddContactsUtils.this.inputSayContent();
                                    } else if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                        boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "发消息");
                                        boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "添加到通讯录");
                                        if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                            if (findNodeIsExistByText3 && MyApplication.enforceable) {
                                                AutoAddContactsUtils.access$108(AutoAddContactsUtils.this);
                                                LogUtils.log("WS_BABY_添加到通讯录_END_1");
                                            }
                                            MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                                            SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                                            LogUtils.log("WS_BABY_添加到通讯录_END2222");
                                            PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                                            AutoAddContactsUtils.this.sleep(100);
                                            AutoAddContactsUtils.this.initMobileFriendUI();
                                            return;
                                        }
                                        AutoAddContactsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "视频动态");
                                            boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "申请添加朋友");
                                            if ((findNodeIsExistByText5 || findNodeIsExistByText6) && MyApplication.enforceable) {
                                                AutoAddContactsUtils.this.inputSayContent();
                                                return;
                                            }
                                            return;
                                        }
                                        try {
                                            LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                                            MyWindowManager myWindowManager2 = AutoAddContactsUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager2, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                                            SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                                            PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                                            AutoAddContactsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                            PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                                            AutoAddContactsUtils.this.sleep(100);
                                            AutoAddContactsUtils.this.initMobileFriendUI();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            LogUtils.log("WS_BABY_添加到通讯录.dialog0");
                                            MyWindowManager myWindowManager3 = AutoAddContactsUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager3, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                                            SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                                            PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                                            AutoAddContactsUtils.this.sleep(600);
                                            PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                                            AutoAddContactsUtils.this.sleep(100);
                                            AutoAddContactsUtils.this.initMobileFriendUI();
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
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
            }

            public void nuFind() {
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "视频动态");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddContactsUtils.this.autoService, "申请添加朋友");
                if (findNodeIsExistByText || findNodeIsExistByText2) {
                    AutoAddContactsUtils.this.inputSayContent();
                } else if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                    new PerformClickUtils2().checkNodeId(AutoAddContactsUtils.this.autoService, BaseServiceUtils.back_contact_id, 0, 300, 300, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                            SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                            LogUtils.log("WS_BABY_添加到通讯录_END2222");
                            PerformClickUtils.findViewIdAndClick(AutoAddContactsUtils.this.autoService, BaseServiceUtils.back_contact_id);
                            AutoAddContactsUtils.this.initMobileFriendUI();
                        }

                        public void nuFind() {
                        }
                    });
                } else {
                    try {
                        MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                        SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                        LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                        PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                        AutoAddContactsUtils.this.sleep(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                        PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                        AutoAddContactsUtils.this.initMobileFriendUI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void checkContactFriend() {
        this.isJumpedStart = false;
        if (this.listViews == null) {
            this.listViews = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_id);
        }
        if (this.listViews == null || this.listViews.size() <= 0 || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY_13");
            removeEndView(this.mMyManager);
            return;
        }
        LogUtils.log("WS_BABY_3");
        final AccessibilityNodeInfo accessibilityNodeInfo = this.listViews.get(0);
        if (this.listName1 == null) {
            this.listName1 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_name1);
        }
        if (this.listName2 == null) {
            this.listName2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(list_layout_name2);
        }
        if (accessibilityNodeInfo == null || (accessibilityNodeInfo.getChildCount() == 0 && MyApplication.enforceable)) {
            LogUtils.log("WS_BABY_5");
            removeEndView(this.mMyManager);
        } else if (this.recordSendNum >= this.maxNum && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_666");
            removeEndView(this.mMyManager);
        } else if (this.startIndex < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
            LogUtils.log("WS_BABY_7");
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(this.startIndex);
            String str = "";
            String str2 = "";
            if (child != null) {
                if (!(this.listName1 == null || this.listName1.size() <= this.startIndex || this.listName1.get(this.startIndex).getText() == null)) {
                    str = this.listName1.get(this.startIndex).getText().toString();
                }
                if (!(this.listName2 == null || this.listName2.size() <= this.startIndex || this.listName2.get(this.startIndex).getText() == null)) {
                    str2 = this.listName2.get(this.startIndex).getText().toString();
                }
            }
            try {
                this.accessibilityNodeInfoAdd = null;
                findNode(child);
                this.startIndex++;
                String str3 = "";
                if (!(this.accessibilityNodeInfoAdd == null || this.accessibilityNodeInfoAdd.getText() == null)) {
                    str3 = this.accessibilityNodeInfoAdd.getText().toString();
                    LogUtils.log("WS_BABY_777.text=" + str3);
                }
                if ("添加".equals(str3)) {
                    if (!this.recordData.contains(str + str2)) {
                        this.recordData.add(str + str2);
                        this.recordSendNum = this.recordSendNum + 1;
                        LogUtils.log("WS_BABY_8_accept");
                        PerformClickUtils.performClick(this.accessibilityNodeInfoAdd.getParent().getParent());
                        if (this.ffModel == 1) {
                            this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
                            LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
                        }
                        if (this.spaceTime <= 0 || this.recordSendNum <= 1) {
                            initContactInfo();
                        } else {
                            new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                public void end() {
                                    AutoAddContactsUtils.this.initContactInfo();
                                }

                                public void surplus(int i) {
                                    MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("已添加 ");
                                    sb.append(AutoAddContactsUtils.this.recordSendNum - 1);
                                    sb.append(" 个通讯录朋友\n正在延时等待，倒计时 ");
                                    sb.append(i);
                                    sb.append(" 秒");
                                    BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                                }
                            });
                        }
                    } else {
                        checkContactFriend();
                    }
                } else {
                    this.recordData.add(str + str2);
                    checkContactFriend();
                }
            } catch (Exception e) {
                LogUtils.log("WS_BABY_9");
            }
        } else if (this.startIndex >= accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
            this.listViews = null;
            this.listName1 = null;
            this.listName2 = null;
            this.startIndex = 1;
            PerformClickUtils.scroll(accessibilityNodeInfo);
            new PerformClickUtils2().check(this.autoService, list_layout_name1, 300, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String str;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    AccessibilityNodeInfo accessibilityNodeInfo2;
                    AutoAddContactsUtils.this.listViews = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.list_id);
                    AutoAddContactsUtils.this.listName1 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_name1);
                    AutoAddContactsUtils.this.listName2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_name2);
                    String str2 = "";
                    if (!(AutoAddContactsUtils.this.listName1 == null || AutoAddContactsUtils.this.listName1.size() <= 0 || (accessibilityNodeInfo2 = AutoAddContactsUtils.this.listName1.get(AutoAddContactsUtils.this.listName1.size() - 1)) == null || accessibilityNodeInfo2.getText() == null)) {
                        str2 = accessibilityNodeInfo2.getText() + "";
                    }
                    if (AutoAddContactsUtils.this.listName2 == null || AutoAddContactsUtils.this.listName2.size() <= 0 || (accessibilityNodeInfo = AutoAddContactsUtils.this.listName2.get(AutoAddContactsUtils.this.listName2.size() - 1)) == null || accessibilityNodeInfo.getText() == null) {
                        str = "";
                    } else {
                        str = accessibilityNodeInfo.getText() + "";
                    }
                    if ((str2 + "" + str).equals(AutoAddContactsUtils.this.lastScrollNickName)) {
                        LogUtils.log("WS_BABY_11");
                        SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", 1);
                        BaseServiceUtils.removeEndView(AutoAddContactsUtils.this.mMyManager);
                        return;
                    }
                    LogUtils.log("WS_BABY_12");
                    String unused = AutoAddContactsUtils.this.lastScrollNickName = str2 + "" + str;
                    AutoAddContactsUtils.this.checkContactFriend();
                }

                public void nuFind() {
                }
            });
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startInt + " 个开始\n正在自动添加手机联系人");
        new Thread(new Runnable() {
            public void run() {
                BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                    public void run() {
                        AutoAddContactsUtils.this.executeMain();
                    }
                }, 10);
            }
        }).start();
    }

    public void executeMain() {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().check(this.autoService, news_friend_id, executeSpeed + executeSpeedSetting, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.news_friend_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable && (accessibilityNodeInfo = findViewIdList.get(0)) != null) {
                        PerformClickUtils.performClick(accessibilityNodeInfo);
                        AutoAddContactsUtils.this.initFMessageConversationUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findNode(AccessibilityNodeInfo accessibilityNodeInfo) {
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
            if ("android.widget.FrameLayout".equals(child.getClassName())) {
                this.accessibilityNodeInfoAdd = child.getChild(0);
                return;
            }
            if (child.getChildCount() > 0) {
                findNode(child);
            }
        }
    }

    public void initAddMoreFriendsUI() {
        LogUtils.log("WS_BABY.AddMoreFriendsUI");
        new PerformClickUtils2().checkString(this.autoService, "手机联系人", executeSpeed + executeSpeedSetting, 50, 200, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findTextAndClick(AutoAddContactsUtils.this.autoService, "手机联系人");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddContactsUtils.this.autoService, "上传通讯录", "查看手机通讯录", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 8), 50, 18, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            AutoAddContactsUtils.this.initBindMContactIntroUI();
                        } else if (i == 1) {
                            PerformClickUtils.findTextAndClick(AutoAddContactsUtils.this.autoService, "查看手机通讯录");
                            AutoAddContactsUtils.this.initMobileFriendUI();
                        }
                    }

                    public void nuFind() {
                        AutoAddContactsUtils.this.initMobileFriendUI();
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initBindMContactIntroUI() {
        PerformClickUtils.findTextAndClick(this.autoService, "上传通讯录");
        new PerformClickUtils2().check(this.autoService, dialog_ok_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtils.findViewIdAndClick(AutoAddContactsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                new PerformClickUtils2().checkString(AutoAddContactsUtils.this.autoService, "查看手机通讯录", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 8), 100, 30, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(AutoAddContactsUtils.this.autoService, "查看手机通讯录");
                        AutoAddContactsUtils.this.initMobileFriendUI();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void initContactInfo() {
        try {
            LogUtils.log("WS_BABY.isFirstContactInfoUI.1");
            new PerformClickUtils2().checkString(this.autoService, "添加到通讯录", executeSpeed + executeSpeedSetting, 100, 34, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AutoAddContactsUtils.this.addContact();
                }

                public void nuFind() {
                    MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                    SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                    LogUtils.log("WS_BABY_发消息@Back2");
                    PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                    AutoAddContactsUtils.this.sleep(100);
                    AutoAddContactsUtils.this.initMobileFriendUI();
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY.ContactInfoUI.10");
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.recordSendNum = 0;
        this.lastScrollNickName = "";
        this.isJumpedStart = true;
        this.isFirstExecute = true;
        this.scrollCount = 0;
        this.recordData = new LinkedHashSet<>();
        this.sayContent = operationParameterModel.getSayContent();
        this.isAutoRemarkContactsName = operationParameterModel.isAutoRemarkContactsName();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.startInt = operationParameterModel.getStartIndex();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFMessageConversationUI() {
        new PerformClickUtils2().checkString(this.autoService, "添加朋友", executeSpeed + executeSpeedSetting, 50, 200, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.FMessageConversationUI");
                PerformClickUtils.findTextAndClick(AutoAddContactsUtils.this.autoService, "添加朋友");
                AutoAddContactsUtils.this.initAddMoreFriendsUI();
            }

            public void nuFind() {
            }
        });
    }

    public void initMobileFriendUI() {
        try {
            if (this.isFirstExecute && MyApplication.enforceable) {
                this.isFirstExecute = false;
                sleep(200);
                PerformClickUtils.findViewIdAndClick(this.autoService, "com.android.packageinstaller:id/do_not_ask_checkbox");
                sleep(100);
                PerformClickUtils.findViewIdAndClick(this.autoService, "com.android.packageinstaller:id/permission_allow_button");
                sleep(200);
                PerformClickUtils.findViewIdAndClick(this.autoService, "com.android.packageinstaller:id/do_not_ask_checkbox");
                sleep(100);
                PerformClickUtils.findViewIdAndClick(this.autoService, "com.android.packageinstaller:id/permission_allow_button");
            }
            new PerformClickUtils2().checkNodeAllIds(this.autoService, list_id, list_layout_name1, executeSpeed + executeSpeedSetting, 100, 600, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (AutoAddContactsUtils.this.startInt <= 1 || !AutoAddContactsUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        AutoAddContactsUtils.this.checkContactFriend();
                        return;
                    }
                    AutoAddContactsUtils.this.jumpStartFriends();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputSayContent() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            updateBottomText(myWindowManager, "从第" + this.startInt + "个开始\n已添加 " + this.recordSendNum + " 个通讯录朋友");
            if (this.sayContent != null && !this.sayContent.equals("") && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.sayContent");
                PerformClickUtils.findViewIdAndClick(this.autoService, input_say_content);
                PerformClickUtils.inputText(this.autoService, this.sayContent);
            }
            if (this.isAutoRemarkContactsName && MyApplication.enforceable) {
                try {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入");
                    AccessibilityNodeInfo findNodeById = PerformClickUtils.findNodeById(this.autoService, remarkContactName);
                    if (findNodeById != null && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入1");
                        String str = "";
                        CharSequence text = findNodeById.getText();
                        if (text != null && !"".equals(text)) {
                            str = text.toString();
                        }
                        if (str != null && !"".equals(str)) {
                            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入2");
                            str = str.substring(str.indexOf("“") + 1, str.lastIndexOf("”"));
                        }
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入3");
                        PerformClickUtils.findViewByIdAndPasteContent(this.autoService, input_remark, str);
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入4");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.植入5");
                }
            }
            sleep(executeSpeed + executeSpeedSetting);
            PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
            new PerformClickUtils2().checkIsSendingByAddContact(this.autoService, 200, new PerformClickUtils2.OnCheckListener3() {
                public void backContactInfo() {
                    try {
                        MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                        SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                        LogUtils.log("WS_BABY.ContactInfoUI.0");
                        PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                        AutoAddContactsUtils.this.initMobileFriendUI();
                    } catch (Exception e) {
                        LogUtils.log("WS_BABY.ContactInfoUI.10");
                        e.printStackTrace();
                    }
                }

                public void executeBack() {
                    MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                    SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                    PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                    AutoAddContactsUtils.this.sleep(BannerConfig.DURATION);
                    PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                    AutoAddContactsUtils.this.initMobileFriendUI();
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_countSayHiWithSnsPermissionUI@@" + AutoAddContactsUtils.this.countSend);
                    MyWindowManager myWindowManager = AutoAddContactsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "从第" + AutoAddContactsUtils.this.startInt + "个开始\n已添加 " + AutoAddContactsUtils.this.recordSendNum + " 个通讯录朋友");
                    SPUtils.put(MyApplication.getConText(), "auto_add_contact_num", Integer.valueOf(AutoAddContactsUtils.this.recordSendNum + AutoAddContactsUtils.this.startInt));
                    PerformClickUtils.performBackNoDeep(AutoAddContactsUtils.this.autoService);
                    AutoAddContactsUtils.this.initMobileFriendUI();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void jumpStartFriends() {
        new Thread(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
                r8 = r9.this$0.itemList.get(0);
             */
            public void run() {
                final AccessibilityNodeInfo accessibilityNodeInfo;
                try {
                    if (AutoAddContactsUtils.this.itemList == null) {
                        AutoAddContactsUtils.this.itemList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddContactsUtils.this.autoService, BaseServiceUtils.list_id);
                    }
                    if (AutoAddContactsUtils.this.itemList != null && AutoAddContactsUtils.this.itemList.size() > 0 && MyApplication.enforceable && accessibilityNodeInfo != null && accessibilityNodeInfo.getChildCount() > 0 && MyApplication.enforceable) {
                        if (AutoAddContactsUtils.this.startIndex < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                            AutoAddContactsUtils.access$508(AutoAddContactsUtils.this);
                            if (AutoAddContactsUtils.this.scrollCount == AutoAddContactsUtils.this.startInt) {
                                AutoAddContactsUtils.this.checkContactFriend();
                                return;
                            }
                            AutoAddContactsUtils.access$408(AutoAddContactsUtils.this);
                            AutoAddContactsUtils.this.jumpStartFriends();
                        } else if (AutoAddContactsUtils.this.startIndex >= accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                            AutoAddContactsUtils.this.itemList = null;
                            LogUtils.log("WS_BABY_10");
                            if (AutoAddContactsUtils.this.scrollCount > 0) {
                                AutoAddContactsUtils.access$510(AutoAddContactsUtils.this);
                            }
                            int unused = AutoAddContactsUtils.this.startIndex = 0;
                            PerformClickUtils.scroll(accessibilityNodeInfo);
                            new PerformClickUtils2().check(AutoAddContactsUtils.this.autoService, BaseServiceUtils.list_layout_name1, 200, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    String str;
                                    AccessibilityNodeInfo accessibilityNodeInfo;
                                    AccessibilityNodeInfo accessibilityNodeInfo2;
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_name1);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_layout_name2);
                                    String str2 = "";
                                    if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) == null || accessibilityNodeInfo2.getText() == null)) {
                                        str2 = accessibilityNodeInfo2.getText() + "";
                                    }
                                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1)) == null || accessibilityNodeInfo.getText() == null) {
                                        str = "";
                                    } else {
                                        str = accessibilityNodeInfo.getText() + "";
                                    }
                                    if ((str2 + "" + str).equals(AutoAddContactsUtils.this.lastScrollNickName)) {
                                        LogUtils.log("WS_BABY_11");
                                        BaseServiceUtils.removeEndView(AutoAddContactsUtils.this.mMyManager);
                                        return;
                                    }
                                    LogUtils.log("WS_BABY_12");
                                    String unused = AutoAddContactsUtils.this.lastScrollNickName = str2 + "" + str;
                                    AutoAddContactsUtils.this.jumpStartFriends();
                                }

                                public void nuFind() {
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "自动添加手机联系人", "从第" + this.startInt + "个开始\n已添加 " + this.recordSendNum + " 个通讯录朋友");
    }
}
