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
import com.wx.assistants.utils.PerformClickUtilsCompany;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class AutoAddGroupFansCompanyUtils extends BaseServiceCompanyUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddGroupFansCompanyUtils instance;
    /* access modifiers changed from: private */
    public int executionNum = 0;
    /* access modifiers changed from: private */
    public int fans = 0;
    private int ffModel = 0;
    private int ffModelEndTime = 10;
    private int ffModelStartTime = 0;
    /* access modifiers changed from: private */
    public boolean isEnd = false;
    List<AccessibilityNodeInfo> itemList = null;
    /* access modifiers changed from: private */
    public int jumpNum = 1;
    /* access modifiers changed from: private */
    public String lastScrollNickName;
    /* access modifiers changed from: private */
    public int maxNum = 5000;
    /* access modifiers changed from: private */
    public List<String> names = new ArrayList();
    private int recordSearchNum = 0;
    private int recordSendNum = 0;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public String remarkPrefix;
    /* access modifiers changed from: private */
    public String resultStr = "";
    /* access modifiers changed from: private */
    public String sayContent;
    private int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPosit = 1;
    /* access modifiers changed from: private */
    public int verifyCode = 1;

    private AutoAddGroupFansCompanyUtils() {
    }

    static /* synthetic */ int access$508(AutoAddGroupFansCompanyUtils autoAddGroupFansCompanyUtils) {
        int i = autoAddGroupFansCompanyUtils.startIndex;
        autoAddGroupFansCompanyUtils.startIndex = i + 1;
        return i;
    }

    public static AutoAddGroupFansCompanyUtils getInstance() {
        instance = new AutoAddGroupFansCompanyUtils();
        return instance;
    }

    public void clickItem(AccessibilityNodeInfo accessibilityNodeInfo) {
        updateBottomText(this.mMyManager, "自动群内加人，请不要操作企业微信");
        PerformClickUtilsCompany.performClick(accessibilityNodeInfo);
        LogUtils.log("WS_BABY.executeMainMemberList.222");
        this.jumpNum++;
        SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "个人信息", "客户信息", 100, 10, 200, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddGroupFansCompanyUtils.this.autoService, "发消息", "添加为联系人", 100, 10, 200, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        if (i == 0) {
                            LogUtils.log("WS_BABY.executeMainMemberList.4");
                            AutoAddGroupFansCompanyUtils.this.executeNext();
                        } else if (AutoAddGroupFansCompanyUtils.this.remarkPrefix == null || "".equals(AutoAddGroupFansCompanyUtils.this.remarkPrefix)) {
                            LogUtils.log("WS_BABY.executeMainMemberList.3");
                            AutoAddGroupFansCompanyUtils.this.executeOther();
                        } else {
                            LogUtils.log("WS_BABY.executeMainMemberList.2");
                            PerformClickUtilsCompany.findTextAndClick(AutoAddGroupFansCompanyUtils.this.autoService, "设置备注");
                            AutoAddGroupFansCompanyUtils.this.initContactRemarkInfoModUI();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.executeMainMemberList.6");
                        AutoAddGroupFansCompanyUtils.this.executeNext();
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.executeMainMemberList.7");
                AutoAddGroupFansCompanyUtils.this.executeNext();
            }
        });
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在群内加人，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    BaseServiceCompanyUtils.getMainHandler().postDelayed(new Runnable() {
                        public void run() {
                            AutoAddGroupFansCompanyUtils.this.executeMain();
                        }
                    }, 10);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAdd() {
        LogUtils.log("WS_BABY.executeAdd.1");
        PerformClickUtilsCompany.findTextAndClick(this.autoService, "添加为联系人");
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "发送申请", "发消息", 1500, 100, 20, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (i != 0) {
                    LogUtils.log("WS_BABY.executeAdd.5");
                    AutoAddGroupFansCompanyUtils.this.executeNext();
                } else if (AutoAddGroupFansCompanyUtils.this.verifyCode == 0) {
                    PerformClickUtilsCompany.backUpPageCompany(AutoAddGroupFansCompanyUtils.this.autoService);
                    AutoAddGroupFansCompanyUtils.this.sleep(600);
                    PerformClickUtilsCompany.backUpPageCompany(AutoAddGroupFansCompanyUtils.this.autoService);
                    AutoAddGroupFansCompanyUtils.access$508(AutoAddGroupFansCompanyUtils.this);
                    AutoAddGroupFansCompanyUtils.this.executeMainMemberList();
                } else if (AutoAddGroupFansCompanyUtils.this.sayContent == null || "".equals(AutoAddGroupFansCompanyUtils.this.sayContent)) {
                    LogUtils.log("WS_BABY.executeAdd.44");
                    AutoAddGroupFansCompanyUtils.this.executeSend(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                } else {
                    LogUtils.log("WS_BABY.executeAdd.3");
                    PerformClickUtilsCompany.findViewIdAndClick(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_edit_img);
                    AutoAddGroupFansCompanyUtils.this.sleep(300);
                    PerformClickUtilsCompany.findViewIdAndClick(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_edit_img);
                    AutoAddGroupFansCompanyUtils.this.sleep(300);
                    PerformClickUtilsCompany.findViewByIdAndPasteContent(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_edit_text, AutoAddGroupFansCompanyUtils.this.sayContent);
                    LogUtils.log("WS_BABY.executeAdd.4");
                    AutoAddGroupFansCompanyUtils.this.executeSend(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.executeAdd.6");
                AutoAddGroupFansCompanyUtils.this.executeNext();
            }
        });
    }

    public void executeAddLabel() {
        PerformClickUtilsCompany.findTextAndClick(this.autoService, "标签");
        new PerformClickUtils2().checkString(this.autoService, "设置标签", 10, 10, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtilsCompany.findTextAndClick(AutoAddGroupFansCompanyUtils.this.autoService, AutoAddGroupFansCompanyUtils.this.remarkLabel);
                AutoAddGroupFansCompanyUtils.this.sleep(100);
                PerformClickUtilsCompany.findTextAndClick(AutoAddGroupFansCompanyUtils.this.autoService, "确定");
                new PerformClickUtils2().checkString(AutoAddGroupFansCompanyUtils.this.autoService, "添加为联系人", 100, 100, 60, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        AutoAddGroupFansCompanyUtils.this.executeAdd();
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
            }
        });
    }

    public void executeJumpMain() {
        try {
            if (this.itemList == null) {
                this.itemList = PerformClickUtilsCompany.findViewIdList((AccessibilityService) this.autoService, company_group_member_name_id);
            }
            if (this.startIndex < this.itemList.size() && MyApplication.enforceable) {
                LogUtils.log("WS_BABY_executeJumpMain" + this.startIndex);
                this.startIndex = this.startIndex + 1;
                if (this.jumpNum >= this.startPosit - 1) {
                    System.out.println("WS_BABY_executeJumpMain.XXX1");
                    this.jumpNum++;
                    SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                    updateBottomText(this.mMyManager, "自动群内加人，已执行了 " + this.executionNum + "个好友");
                    executeMainMemberList();
                    return;
                }
                try {
                    if (this.fans <= 0 || this.fans - this.jumpNum >= 12) {
                        System.out.println("WS_BABY_executeJumpMain.XXX3");
                        this.jumpNum++;
                        SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                        executeJumpMain();
                        updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                        return;
                    }
                    System.out.println("WS_BABY_executeJumpMain.XXX2");
                    AccessibilityNodeInfo accessibilityNodeInfo = this.itemList.get(this.startIndex);
                    if (accessibilityNodeInfo == null || accessibilityNodeInfo.getChildCount() <= 0) {
                        System.out.println("WS_BABY_executeJumpMain.XXX7");
                        this.jumpNum++;
                        SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                        executeJumpMain();
                        updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                        return;
                    }
                    AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(0);
                    if (child == null || child.getText() == null) {
                        System.out.println("WS_BABY_executeJumpMain.XXX6");
                        this.jumpNum++;
                        SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                        executeJumpMain();
                        updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                        return;
                    }
                    String str = child.getText() + "";
                    System.out.println("WS_BABY_NAME . " + str);
                    if (str == null || "".equals(str) || !this.names.contains(str)) {
                        System.out.println("WS_BABY_executeJumpMain.XXX5");
                        this.names.add(str);
                        this.jumpNum++;
                        SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                        executeJumpMain();
                        updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                        return;
                    }
                    System.out.println("WS_BABY_executeJumpMain.XXX4");
                    executeJumpMain();
                    updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                } catch (Exception e) {
                    this.jumpNum++;
                    SPUtils.put(MyApplication.getConText(), "auto_group_add_num_company", Integer.valueOf(this.jumpNum));
                    executeJumpMain();
                    updateBottomText(this.mMyManager, "自动群内加人，已跳过" + this.jumpNum + "条");
                }
            } else if (this.startIndex >= this.itemList.size() && MyApplication.enforceable) {
                this.itemList = null;
                new PerformClickUtils2().check(this.autoService, company_group_member_list_id, 10, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtilsCompany.findViewIdList((AccessibilityService) AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_group_member_list_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtilsCompany.scroll(findViewIdList.get(0));
                            new PerformClickUtils2().check(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_group_member_name_id, 10, 10, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtilsCompany.findViewIdList((AccessibilityService) AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_group_member_name_id);
                                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@9");
                                        try {
                                            String charSequence = findViewIdList.get(findViewIdList.size() - 1).getChild(0).getText().toString();
                                            if (charSequence == null || !AutoAddGroupFansCompanyUtils.this.lastScrollNickName.equals(charSequence)) {
                                                String unused = AutoAddGroupFansCompanyUtils.this.lastScrollNickName = charSequence;
                                                int unused2 = AutoAddGroupFansCompanyUtils.this.startIndex = 1;
                                                AutoAddGroupFansCompanyUtils.this.executeJumpMain();
                                                return;
                                            }
                                            boolean unused3 = AutoAddGroupFansCompanyUtils.this.isEnd = true;
                                            BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@11" + AutoAddGroupFansCompanyUtils.this.lastScrollNickName + "#" + charSequence);
                                        } catch (Exception e) {
                                            int unused4 = AutoAddGroupFansCompanyUtils.this.startIndex = 1;
                                            AutoAddGroupFansCompanyUtils.this.executeJumpMain();
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
                        BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void executeList() {
        if (this.startPosit > 1) {
            new PerformClickUtils2().check(this.autoService, company_group_member_name_id, 100, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    AutoAddGroupFansCompanyUtils.this.executeJumpMain();
                }

                public void nuFind() {
                    BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                }
            });
        } else {
            executeMainMemberList();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, company_contact_title_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String textByNodeId = PerformClickUtilsCompany.getTextByNodeId(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_contact_title_id);
                    if (textByNodeId.contains("(") && textByNodeId.contains(")")) {
                        int unused = AutoAddGroupFansCompanyUtils.this.fans = Integer.parseInt(textByNodeId.substring(textByNodeId.lastIndexOf("(") + 1, textByNodeId.lastIndexOf(")")));
                    }
                    LogUtils.log("WS_BABY_COUNT." + AutoAddGroupFansCompanyUtils.this.fans + ListUtils.DEFAULT_JOIN_SEPARATOR + AutoAddGroupFansCompanyUtils.this.startPosit);
                    if (AutoAddGroupFansCompanyUtils.this.fans == 0 || AutoAddGroupFansCompanyUtils.this.fans >= AutoAddGroupFansCompanyUtils.this.startPosit) {
                        new PerformClickUtils2().check(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_right_more_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                            public void find(int i) {
                                PerformClickUtilsCompany.findViewIdAndClick(AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_right_more_id);
                                AutoAddGroupFansCompanyUtils.this.jumpGroupMember();
                            }

                            public void nuFind() {
                            }
                        });
                        return;
                    }
                    String unused2 = AutoAddGroupFansCompanyUtils.this.resultStr = "设置的起点高于群成员数量！";
                    BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMainMemberList() {
        try {
            LogUtils.log("WS_BABY.executeMainMemberList.0");
            new PerformClickUtils2().check(this.autoService, company_group_member_name_id, 100, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
                    LogUtils.log("WS_BABY.executeMainMemberList.1");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtilsCompany.findViewIdList((AccessibilityService) AutoAddGroupFansCompanyUtils.this.autoService, BaseServiceCompanyUtils.company_group_member_name_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        if (AutoAddGroupFansCompanyUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                            AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(AutoAddGroupFansCompanyUtils.this.startIndex);
                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                if (AutoAddGroupFansCompanyUtils.this.executionNum >= AutoAddGroupFansCompanyUtils.this.maxNum) {
                                    BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                                    return;
                                }
                                try {
                                    System.out.println("WS_BABY_executeJumpMain.XXXxxx." + (AutoAddGroupFansCompanyUtils.this.fans - AutoAddGroupFansCompanyUtils.this.jumpNum));
                                    if (AutoAddGroupFansCompanyUtils.this.fans <= 0 || AutoAddGroupFansCompanyUtils.this.fans - AutoAddGroupFansCompanyUtils.this.jumpNum >= 12) {
                                        AutoAddGroupFansCompanyUtils.this.clickItem(accessibilityNodeInfo);
                                    } else if (accessibilityNodeInfo == null || accessibilityNodeInfo.getChildCount() <= 0) {
                                        AutoAddGroupFansCompanyUtils.this.clickItem(accessibilityNodeInfo);
                                    } else {
                                        AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(0);
                                        if (child == null || child.getText() == null) {
                                            AutoAddGroupFansCompanyUtils.this.clickItem(accessibilityNodeInfo);
                                            return;
                                        }
                                        String str = child.getText() + "";
                                        System.out.println("WS_BABY_NAME . " + str);
                                        if (str == null || "".equals(str) || !AutoAddGroupFansCompanyUtils.this.names.contains(str)) {
                                            System.out.println("WS_BABY_executeJumpMain.XXX5");
                                            AutoAddGroupFansCompanyUtils.this.names.add(str);
                                            AutoAddGroupFansCompanyUtils.this.clickItem(accessibilityNodeInfo);
                                            return;
                                        }
                                        System.out.println("WS_BABY_executeJumpMain.XXX4");
                                        AutoAddGroupFansCompanyUtils.access$508(AutoAddGroupFansCompanyUtils.this);
                                        AutoAddGroupFansCompanyUtils.this.executeMainMemberList();
                                    }
                                } catch (Exception e) {
                                    AutoAddGroupFansCompanyUtils.this.clickItem(accessibilityNodeInfo);
                                }
                            }
                        } else if (AutoAddGroupFansCompanyUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                            if (AutoAddGroupFansCompanyUtils.this.isEnd || !MyApplication.enforceable) {
                                BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                                return;
                            }
                            LogUtils.log("@@@@@@@@@@@@@@@@@@@@@7");
                            AccessibilityNodeInfo rootInActiveWindow = AutoAddGroupFansCompanyUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_group_member_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtilsCompany.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                LogUtils.log("@@@@@@@@@@@@@@@@@@@@@8");
                                AutoAddGroupFansCompanyUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                AccessibilityNodeInfo rootInActiveWindow2 = AutoAddGroupFansCompanyUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_group_member_name_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                    LogUtils.log("@@@@@@@@@@@@@@@@@@@@@9");
                                    try {
                                        String charSequence = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1).getChild(0).getText().toString();
                                        if (charSequence == null || !AutoAddGroupFansCompanyUtils.this.lastScrollNickName.equals(charSequence)) {
                                            String unused = AutoAddGroupFansCompanyUtils.this.lastScrollNickName = charSequence;
                                            int unused2 = AutoAddGroupFansCompanyUtils.this.startIndex = 1;
                                            AutoAddGroupFansCompanyUtils.this.executeMainMemberList();
                                            return;
                                        }
                                        boolean unused3 = AutoAddGroupFansCompanyUtils.this.isEnd = true;
                                        BaseServiceCompanyUtils.removeEndView(AutoAddGroupFansCompanyUtils.this.mMyManager);
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@11" + AutoAddGroupFansCompanyUtils.this.lastScrollNickName + "#" + charSequence);
                                    } catch (Exception e2) {
                                        int unused4 = AutoAddGroupFansCompanyUtils.this.startIndex = 1;
                                        AutoAddGroupFansCompanyUtils.this.executeMainMemberList();
                                        LogUtils.log("@@@@@@@@@@@@@@@@@@@@@12");
                                    }
                                }
                            }
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

    public void executeNext() {
        this.recordSearchNum++;
        if (this.ffModel == 1) {
            this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
            LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
        }
        if (this.spaceTime <= 0 || this.recordSearchNum <= 1) {
            PerformClickUtilsCompany.backUpPageCompany(this.autoService);
            this.startIndex++;
            executeMainMemberList();
            return;
        }
        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
            public void end() {
                PerformClickUtilsCompany.backUpPageCompany(AutoAddGroupFansCompanyUtils.this.autoService);
                AutoAddGroupFansCompanyUtils.access$508(AutoAddGroupFansCompanyUtils.this);
                AutoAddGroupFansCompanyUtils.this.executeMainMemberList();
            }

            public void surplus(int i) {
                MyWindowManager myWindowManager = AutoAddGroupFansCompanyUtils.this.mMyManager;
                BaseServiceCompanyUtils.updateBottomText(myWindowManager, "正在群内加人，请不要操作微信，" + i);
            }
        });
    }

    public void executeOther() {
        LogUtils.log("WS_BABY.executeOther.1");
        if (this.remarkLabel == null || "".equals(this.remarkLabel)) {
            LogUtils.log("WS_BABY.executeOther.3");
            executeAdd();
            return;
        }
        LogUtils.log("WS_BABY.executeOther.2");
        if (PerformClickUtilsCompany.findNodeIsExistByText(this.autoService, this.remarkLabel)) {
            LogUtils.log("WS_BABY.executeOther.4");
            executeAdd();
            return;
        }
        LogUtils.log("WS_BABY.executeOther.5");
        executeAddLabel();
    }

    public void executeSend(int i) {
        LogUtils.log("WS_BABY.executeSend.1");
        new PerformClickUtils2().checkString(this.autoService, "发送申请", i, 100, 10, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.executeSend.3");
                PerformClickUtilsCompany.findTextAndClick(AutoAddGroupFansCompanyUtils.this.autoService, "发送申请");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(AutoAddGroupFansCompanyUtils.this.autoService, "添加为联系人", "发消息", SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY.executeSend.5");
                        AutoAddGroupFansCompanyUtils.this.executeNext();
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.executeSend.6");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.executeSend.8");
                PerformClickUtilsCompany.hideInputKey(AutoAddGroupFansCompanyUtils.this.autoService);
                AutoAddGroupFansCompanyUtils.this.executeSend(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        try {
            LogUtils.log("WS_BABY.initContactRemarkInfoModUI.1");
            new PerformClickUtils2().check(this.autoService, company_remark_edit_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    LogUtils.log("WS_BABY.initContactRemarkInfoModUI.2");
                    AccessibilityNodeInfo rootInActiveWindow = AutoAddGroupFansCompanyUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null) {
                        accessibilityNodeInfo.performAction(16);
                        PerformClickUtilsCompany.inputPrefixText(AutoAddGroupFansCompanyUtils.this.autoService, AutoAddGroupFansCompanyUtils.this.remarkPrefix);
                        AutoAddGroupFansCompanyUtils.access$508(AutoAddGroupFansCompanyUtils.this);
                        List<AccessibilityNodeInfo> findViewTextList = PerformClickUtilsCompany.findViewTextList(AutoAddGroupFansCompanyUtils.this.autoService, "保存");
                        if (findViewTextList != null && findViewTextList.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtilsCompany.performClick(findViewTextList.get(0));
                            LogUtils.log("WS_BABY.initContactRemarkInfoModUI.3");
                            new PerformClickUtils2().checkString(AutoAddGroupFansCompanyUtils.this.autoService, "添加为联系人", SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY.initContactRemarkInfoModUI.5");
                                    AutoAddGroupFansCompanyUtils.this.executeOther();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY.initContactRemarkInfoModUI.6");
                                }
                            });
                        }
                    }
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.initContactRemarkInfoModUI.3");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.remarkLabel = operationParameterModel.getSingLabelStr();
        this.startIndex = 0;
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.resultStr = "";
        this.fans = 0;
        this.recordSendNum = 0;
        this.recordSearchNum = 0;
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.sayContent = operationParameterModel.getSayContent();
        this.startPosit = operationParameterModel.getStartIndex();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.remarkPrefix = operationParameterModel.getRemarkPrefix();
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.startIndex = 0;
        this.executionNum = 0;
        this.lastScrollNickName = "";
        this.isEnd = false;
        this.jumpNum = 1;
        this.maxNum = operationParameterModel.getMaxOperaNum();
    }

    public void jumpGroupMember() {
        new PerformClickUtils2().checkString(this.autoService, "群成员", 10, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                PerformClickUtilsCompany.findTextAndClickFirst(AutoAddGroupFansCompanyUtils.this.autoService, "群成员");
                AutoAddGroupFansCompanyUtils.this.executeList();
            }

            public void nuFind() {
            }
        });
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            if (this.resultStr == null || "".equals(this.resultStr)) {
                MyWindowManager myWindowManager = this.mMyManager;
                updateMiddleText(myWindowManager, "一键自动群内加粉", "已向 " + this.recordSendNum + " 个好友，发起添加请求。");
                return;
            }
            updateMiddleText(this.mMyManager, "自动群内加粉结束", this.resultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
