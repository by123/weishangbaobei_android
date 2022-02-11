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
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoAddGroupFansUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoAddGroupFansUtils instance;
    /* access modifiers changed from: private */
    public int backTime = 100;
    /* access modifiers changed from: private */
    public int executeSayHellNum = 0;
    /* access modifiers changed from: private */
    public int fans = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> fansRecord = null;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> fansRecord0 = null;
    /* access modifiers changed from: private */
    public int ffModel = 0;
    /* access modifiers changed from: private */
    public int ffModelEndTime = 10;
    /* access modifiers changed from: private */
    public int ffModelStartTime = 0;
    private LinkedHashSet<String> filterNames = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public int formerFriendNum = 0;
    /* access modifiers changed from: private */
    public String groupNickName = "";
    /* access modifiers changed from: private */
    public boolean isFirstCheck = true;
    /* access modifiers changed from: private */
    public boolean isFirstJump = true;
    /* access modifiers changed from: private */
    public boolean isRemoveIndex = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom0 = false;
    /* access modifiers changed from: private */
    public boolean isSeeMore = false;
    /* access modifiers changed from: private */
    public int jumpTime = 100;
    List<AccessibilityNodeInfo> lists = null;
    /* access modifiers changed from: private */
    public int maxNum;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public int recordSearchNum = 0;
    /* access modifiers changed from: private */
    public int recordSendNum = 0;
    /* access modifiers changed from: private */
    public String remarkLabel = "";
    /* access modifiers changed from: private */
    public String remarkPrefix;
    private String sayContent;
    /* access modifiers changed from: private */
    public int sayHelloNum = 20;
    private int sendApply = 0;
    /* access modifiers changed from: private */
    public String sendResult = "";
    /* access modifiers changed from: private */
    public Gender sex;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;
    private int verifyCode = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$2008(AutoAddGroupFansUtils autoAddGroupFansUtils) {
        int i = autoAddGroupFansUtils.formerFriendNum;
        autoAddGroupFansUtils.formerFriendNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(AutoAddGroupFansUtils autoAddGroupFansUtils) {
        int i = autoAddGroupFansUtils.startIndex;
        autoAddGroupFansUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$2108(AutoAddGroupFansUtils autoAddGroupFansUtils) {
        int i = autoAddGroupFansUtils.recordSendNum;
        autoAddGroupFansUtils.recordSendNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$2708(AutoAddGroupFansUtils autoAddGroupFansUtils) {
        int i = autoAddGroupFansUtils.executeSayHellNum;
        autoAddGroupFansUtils.executeSayHellNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(AutoAddGroupFansUtils autoAddGroupFansUtils) {
        int i = autoAddGroupFansUtils.recordSearchNum;
        autoAddGroupFansUtils.recordSearchNum = i + 1;
        return i;
    }

    private AutoAddGroupFansUtils() {
    }

    public static AutoAddGroupFansUtils getInstance() {
        instance = new AutoAddGroupFansUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.sendResult = "";
        this.remarkLabel = operationParameterModel.getSingLabelStr();
        this.isSeeMore = false;
        this.startIndex = 0;
        this.sendApply = 0;
        this.jumpTime = 400;
        this.backTime = 600;
        this.executeSayHellNum = 0;
        this.groupNickName = "";
        this.nowName = "";
        this.sayHelloNum = operationParameterModel.getMaxPraiseNum();
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.ffModel = operationParameterModel.getFfModel();
        this.ffModelStartTime = operationParameterModel.getFfModelStartTime();
        this.ffModelEndTime = operationParameterModel.getFfModelEndTime();
        this.isFirstCheck = true;
        this.isFirstJump = true;
        this.isScrollBottom0 = false;
        this.isScrollBottom = false;
        this.fans = 0;
        this.fansRecord0 = new LinkedHashSet<>();
        this.fansRecord = new LinkedHashSet<>();
        this.isRemoveIndex = false;
        this.recordSendNum = 0;
        this.formerFriendNum = 0;
        this.recordSearchNum = 0;
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.sayContent = operationParameterModel.getSayContent();
        this.startInt = operationParameterModel.getStartIndex();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.sex = operationParameterModel.getSex();
        this.remarkPrefix = operationParameterModel.getRemarkPrefix();
        this.verifyCode = operationParameterModel.getVerifyCode();
        this.filterNames = operationParameterModel.getFilterNickNames();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initContactRemarkInfoModUI() {
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, (int) CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    AccessibilityNodeInfo rootInActiveWindow = AutoAddGroupFansUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.remark_edit_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) != null) {
                        accessibilityNodeInfo.performAction(16);
                        if (AutoAddGroupFansUtils.this.groupNickName != null && !"".equals(AutoAddGroupFansUtils.this.groupNickName)) {
                            PerformClickUtils.findViewByIdAndPasteContent(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.remark_edit_id, AutoAddGroupFansUtils.this.groupNickName);
                        }
                        PerformClickUtils.inputPrefixText(AutoAddGroupFansUtils.this.autoService, AutoAddGroupFansUtils.this.remarkPrefix);
                        AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findViewIdList.get(0));
                            AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                            PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                        AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                        return;
                                    }
                                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                }
                            });
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChatroomInfoUI() {
        try {
            LogUtils.log("WS_BABY.isFirstChatroomInfoUI");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                if (PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "查看全部群成员")) {
                                    boolean unused = AutoAddGroupFansUtils.this.isFirstCheck = false;
                                    boolean unused2 = AutoAddGroupFansUtils.this.isSeeMore = true;
                                    LogUtils.log("WS_BABY_查看全部群成员");
                                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "查看全部群成员");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                    return;
                                }
                                if (AutoAddGroupFansUtils.this.isFirstCheck && MyApplication.enforceable) {
                                    AutoAddGroupFansUtils.this.sleep(100);
                                    PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                }
                                if (PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "查看全部群成员") && MyApplication.enforceable) {
                                    boolean unused3 = AutoAddGroupFansUtils.this.isFirstCheck = false;
                                    boolean unused4 = AutoAddGroupFansUtils.this.isSeeMore = true;
                                    LogUtils.log("WS_BABY_查看全部群成员");
                                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "查看全部群成员");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                } else if (AutoAddGroupFansUtils.this.fans > 50) {
                                    AutoAddGroupFansUtils.this.sleep(100);
                                    PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                    boolean unused5 = AutoAddGroupFansUtils.this.isFirstCheck = false;
                                    boolean unused6 = AutoAddGroupFansUtils.this.isSeeMore = true;
                                    LogUtils.log("WS_BABY_查看全部群成员");
                                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "查看全部群成员");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                } else {
                                    if (AutoAddGroupFansUtils.this.isFirstCheck) {
                                        PerformClickUtils.scrollTop(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                        AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                    }
                                    boolean unused7 = AutoAddGroupFansUtils.this.isFirstCheck = false;
                                    AutoAddGroupFansUtils.this.executeSmallGroup();
                                }
                            } catch (Exception unused8) {
                            }
                        }
                    }).start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBottomText(boolean z, int i) {
        int i2;
        try {
            int i3 = this.recordSearchNum;
            int i4 = this.formerFriendNum;
            int i5 = this.recordSendNum;
            int i6 = this.recordSearchNum - this.startInt > 0 ? this.recordSearchNum - this.startInt : 0;
            if ((this.fans - this.recordSearchNum) + 1 > 0) {
                int i7 = this.fans;
                int i8 = this.recordSearchNum;
            }
            String str = "";
            if (this.sex == Gender.WOMEN) {
                str = "只加女 。";
            } else if (this.sex == Gender.MAN) {
                str = "只加男 。";
            }
            if (this.verifyCode == 0) {
                if (z) {
                    MyWindowManager myWindowManager = this.mMyManager;
                    updateBottomText(myWindowManager, "从第 " + this.startInt + " 个开始 , 已执行 " + i6 + " 人 。\n" + str + "，已是好友 " + this.formerFriendNum + " 人。倒计时 " + i + " 秒");
                } else {
                    MyWindowManager myWindowManager2 = this.mMyManager;
                    updateBottomText(myWindowManager2, "从第 " + this.startInt + " 个开始 , 已执行 " + i6 + " 人 。\n" + str + "，已是好友 " + this.formerFriendNum + " 人");
                }
            } else if (z) {
                MyWindowManager myWindowManager3 = this.mMyManager;
                updateBottomText(myWindowManager3, "从第 " + this.startInt + " 个开始 , 已执行 " + i6 + " 人 。" + str + "\n发送申请 " + this.sendApply + " 人，已是好友 " + this.formerFriendNum + " 人。倒计时 " + i + " 秒");
            } else {
                MyWindowManager myWindowManager4 = this.mMyManager;
                updateBottomText(myWindowManager4, "从第 " + this.startInt + " 个开始 , 已执行 " + i6 + " 人 。" + str + "\n发送申请 " + this.sendApply + " 人，已是好友 " + this.formerFriendNum + " 人");
            }
            if (this.startInt == 1) {
                i2 = this.recordSearchNum + 1;
            } else {
                i2 = this.recordSearchNum + 1;
            }
            SPUtils.put(MyApplication.getConText(), "group_inner_add_fans_num", Integer.valueOf(i2));
        } catch (Exception unused) {
        }
    }

    public void executeSmallGroup() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.small.group_fans=" + AutoAddGroupFansUtils.this.fans + ",recordSearchNum=" + AutoAddGroupFansUtils.this.recordSearchNum);
                if (AutoAddGroupFansUtils.this.fans - AutoAddGroupFansUtils.this.recordSearchNum <= 0 || (AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1 == AutoAddGroupFansUtils.this.maxNum) {
                    LogUtils.log("WS_BABY_END_4");
                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                }
                new PerformClickUtils2().checkNodeAllIds(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id, BaseServiceUtils.list_head_img_id, 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        AccessibilityNodeInfo accessibilityNodeInfo;
                        AccessibilityNodeInfo child;
                        LogUtils.log("WS_BABY.small.group_0");
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.list_head_img_id);
                        if (findViewIdList != null && findViewIdList.size() > 0) {
                            LogUtils.log("WS_BABY.small.group_1");
                            if (AutoAddGroupFansUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY.small.group_2");
                                AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(AutoAddGroupFansUtils.this.startIndex);
                                if (AutoAddGroupFansUtils.this.isRemoveIndex || AutoAddGroupFansUtils.this.startInt <= 1 || !MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.small.group_4");
                                    AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.jumpTime);
                                    if (accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getContentDescription() == null) {
                                        LogUtils.log("WS_BABY.small.group_5");
                                        return;
                                    }
                                    String str = accessibilityNodeInfo2.getContentDescription() + "";
                                    LogUtils.log("WS_BABY.small.group_6");
                                    if (("添加成员".equals(str) || !MyApplication.enforceable) && ("删除成员".equals(str) || !MyApplication.enforceable)) {
                                        LogUtils.log("WS_BABY_END_5");
                                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                                    if (findViewIdList2 == null) {
                                        LogUtils.log("WS_BABY_END_5");
                                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                    } else if (findViewIdList2.size() > AutoAddGroupFansUtils.this.startIndex) {
                                        AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList2.get(AutoAddGroupFansUtils.this.startIndex);
                                        if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.getText() == null) {
                                            LogUtils.log("WS_BABY_ChatroomInfoUI.111111_");
                                            BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                            return;
                                        }
                                        String unused = AutoAddGroupFansUtils.this.nowName = accessibilityNodeInfo3.getText() + "";
                                        LogUtils.log("WS_BABY_ChatroomInfoUI.3_" + AutoAddGroupFansUtils.this.nowName);
                                        if (!AutoAddGroupFansUtils.this.isScrollBottom || !AutoAddGroupFansUtils.this.fansRecord.contains(AutoAddGroupFansUtils.this.nowName)) {
                                            AutoAddGroupFansUtils.this.executeStart("", accessibilityNodeInfo2);
                                            return;
                                        }
                                        LogUtils.log("WS_BABY.small.group_9");
                                        AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                        AutoAddGroupFansUtils.this.executeSmallGroup();
                                    } else {
                                        AutoAddGroupFansUtils.this.executeStart("", accessibilityNodeInfo2);
                                    }
                                } else if (AutoAddGroupFansUtils.this.startInt > AutoAddGroupFansUtils.this.fans) {
                                    String unused2 = AutoAddGroupFansUtils.this.sendResult = "您设置的加人起点高于群成员数量，导致加人结束。";
                                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                } else {
                                    LogUtils.log("WS_BABY.small.group_3_jump");
                                    AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                                }
                            } else if (AutoAddGroupFansUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                                int i2 = 5;
                                try {
                                    List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    if (findViewIdList3 != null && findViewIdList3.size() > 0 && (accessibilityNodeInfo = findViewIdList3.get(0)) != null && accessibilityNodeInfo.getChildCount() > 0 && (child = accessibilityNodeInfo.getChild(0)) != null && child.getChildCount() > 0) {
                                        int childCount = child.getChildCount();
                                        try {
                                            LogUtils.log("WS_BABY_ROW_NUM" + childCount);
                                        } catch (Exception unused3) {
                                        }
                                        i2 = childCount;
                                    }
                                } catch (Exception unused4) {
                                }
                                PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                int unused5 = AutoAddGroupFansUtils.this.startIndex = i2;
                                AutoAddGroupFansUtils.this.executeSmallGroup();
                            }
                        }
                    }
                });
            }
        });
    }

    public void executeStart(String str, final AccessibilityNodeInfo accessibilityNodeInfo) {
        LogUtils.log("WS_BABY.small.group_10_" + this.recordSearchNum);
        this.fansRecord.add(str);
        this.startIndex = this.startIndex + 1;
        this.recordSearchNum = this.recordSearchNum + 1;
        if (this.ffModel == 1) {
            this.spaceTime = PerformClickUtils.getRandomTime(this.ffModel, this.ffModelStartTime, this.ffModelEndTime);
            LogUtils.log("WS_BABY.small_spaceTime=" + this.spaceTime);
        }
        if (this.spaceTime <= 0 || this.recordSearchNum <= 1) {
            LogUtils.log("WS_BABY_ChatroomInfoUI.6666____");
            PerformClickUtils.performClick(accessibilityNodeInfo);
            initContactFirstInfo();
            return;
        }
        int i = this.recordSearchNum;
        int i2 = this.formerFriendNum;
        int i3 = this.recordSendNum;
        new PerformClickUtils2().countDown2(this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
            public void surplus(int i) {
                AutoAddGroupFansUtils.this.updateBottomText(true, i);
            }

            public void end() {
                LogUtils.log("WS_BABY_ChatroomInfoUI.5555____");
                PerformClickUtils.performClick(accessibilityNodeInfo);
                AutoAddGroupFansUtils.this.initContactFirstInfo();
            }
        });
    }

    public void seeRoomMember() {
        LogUtils.log("WS_BABY.SeeRoomMemberUI0");
        try {
            new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.SeeRoomMemberUI_fans=" + AutoAddGroupFansUtils.this.fans);
                    if (AutoAddGroupFansUtils.this.maxNum >= 1 && MyApplication.enforceable) {
                        if ((AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1 == AutoAddGroupFansUtils.this.maxNum || AutoAddGroupFansUtils.this.fans < AutoAddGroupFansUtils.this.startInt || AutoAddGroupFansUtils.this.fans - AutoAddGroupFansUtils.this.recordSearchNum <= 0) {
                            if (AutoAddGroupFansUtils.this.fans < AutoAddGroupFansUtils.this.startInt) {
                                String unused = AutoAddGroupFansUtils.this.sendResult = "您设置的加人起点高于群成员数量，导致加人结束。";
                            }
                            LogUtils.log("WS_BABY_END_6");
                            BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                            return;
                        }
                        AutoAddGroupFansUtils.this.initSeeRoomMember(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSeeRoomMember(final int i) {
        LogUtils.log("WS_BABY.SeeRoomMemberUI.1." + i);
        updateBottomText(false, 0);
        LogUtils.log("WS_BABY.SeeRoomMemberUI.2");
        new PerformClickUtils2().checkNodeAllIds(this.autoService, search_list_id, search_list_item_name, i, 100, 50, new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Removed duplicated region for block: B:80:0x0299  */
            /* JADX WARNING: Removed duplicated region for block: B:81:0x02a4  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void find(int r7) {
                /*
                    r6 = this;
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.3"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean r7 = r7.isFirstJump
                    r0 = 0
                    if (r7 == 0) goto L_0x0028
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean unused = r7.isFirstJump = r0
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.AutoService r7 = r7.autoService
                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id
                    com.wx.assistants.utils.PerformClickUtils.scrollTop(r7, r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = com.wx.assistants.application.MyApplication.SCROLL_SPEED
                    r7.sleep(r1)
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.4"
                    com.wx.assistants.utils.LogUtils.log(r7)
                L_0x0028:
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.5"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.AutoService r7 = r7.autoService
                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id
                    java.util.List r7 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r7, (java.lang.String) r1)
                    if (r7 == 0) goto L_0x02d6
                    int r1 = r7.size()
                    if (r1 <= 0) goto L_0x02d6
                    java.lang.Object r1 = r7.get(r0)
                    android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1
                    int r1 = r1.getChildCount()
                    if (r1 <= 0) goto L_0x02d6
                    java.lang.String r1 = "WS_BABY.SeeRoomMemberUI.6"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    java.lang.Object r7 = r7.get(r0)
                    android.view.accessibility.AccessibilityNodeInfo r7 = (android.view.accessibility.AccessibilityNodeInfo) r7
                    java.lang.String r1 = "WS_BABY_ChatroomInfoUI.7"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.startIndex
                    int r2 = r7.getChildCount()
                    r3 = 1
                    if (r1 >= r2) goto L_0x022a
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable
                    if (r1 == 0) goto L_0x022a
                    java.lang.String r1 = "WS_BABY_ChatroomInfoUI.8"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.startIndex
                    android.view.accessibility.AccessibilityNodeInfo r7 = r7.getChild(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean r1 = r1.isRemoveIndex
                    if (r1 != 0) goto L_0x00b9
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.startInt
                    if (r1 <= r3) goto L_0x00b9
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable
                    if (r1 == 0) goto L_0x00b9
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r7 = r7.startInt
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r0 = r0.fans
                    if (r7 <= r0) goto L_0x00ad
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.lang.String r0 = "您设置的加人起点高于群成员数量，导致加人结束。"
                    java.lang.String unused = r7.sendResult = r0
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)
                    goto L_0x02db
                L_0x00ad:
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.9"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    r7.jumpStartIndex()
                    goto L_0x02db
                L_0x00b9:
                    java.lang.String r1 = "WS_BABY.SeeRoomMemberUI.10"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r2 = r10
                    r1.sleep(r2)
                    if (r7 != 0) goto L_0x00cd
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.11"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    return
                L_0x00cd:
                    java.lang.String r1 = "WS_BABY.SeeRoomMemberUI.12"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    int r1 = r7.getChildCount()
                    if (r1 <= 0) goto L_0x021c
                    java.lang.String r1 = "WS_BABY.SeeRoomMemberUI.13"
                    com.wx.assistants.utils.LogUtils.log(r1)
                    r1 = 0
                L_0x00de:
                    int r2 = r7.getChildCount()
                    if (r1 >= r2) goto L_0x020c
                    android.view.accessibility.AccessibilityNodeInfo r2 = r7.getChild(r1)
                    if (r2 == 0) goto L_0x0208
                    java.lang.CharSequence r4 = r2.getText()
                    if (r4 == 0) goto L_0x0208
                    java.lang.String r4 = "android.widget.TextView"
                    java.lang.CharSequence r5 = r2.getClassName()
                    boolean r4 = r4.equals(r5)
                    if (r4 == 0) goto L_0x0208
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.CharSequence r2 = r2.getText()
                    r4.append(r2)
                    java.lang.String r2 = ""
                    r4.append(r2)
                    java.lang.String r2 = r4.toString()
                    java.lang.String unused = r1.nowName = r2
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "WS_BABY_ChatroomInfoUI.3_"
                    r1.append(r2)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.lang.String r2 = r2.nowName
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.wx.assistants.utils.LogUtils.log(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean r1 = r1.isScrollBottom
                    if (r1 == 0) goto L_0x0156
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.util.LinkedHashSet r1 = r1.fansRecord
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.lang.String r2 = r2.nowName
                    boolean r1 = r1.contains(r2)
                    if (r1 == 0) goto L_0x0156
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$208(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    r7.initSeeRoomMember(r0)
                    goto L_0x0206
                L_0x0156:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.util.LinkedHashSet r0 = r0.fansRecord
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    java.lang.String r1 = r1.nowName
                    r0.add(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "WS_BABY_ChatroomInfoUI.3____"
                    r0.append(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.recordSearchNum
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.wx.assistants.utils.LogUtils.log(r0)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$208(r0)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$608(r0)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r0 = r0.ffModel
                    if (r0 != r3) goto L_0x01c6
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.ffModel
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r2 = r2.ffModelStartTime
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r4 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r4 = r4.ffModelEndTime
                    int r1 = com.wx.assistants.utils.PerformClickUtils.getRandomTime(r1, r2, r4)
                    int unused = r0.spaceTime = r1
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "WS_BABY_ChatroomInfoUI.spaceTime="
                    r0.append(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.spaceTime
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.wx.assistants.utils.LogUtils.log(r0)
                L_0x01c6:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r0 = r0.spaceTime
                    if (r0 <= 0) goto L_0x01f9
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r0 = r0.recordSearchNum
                    if (r0 <= r3) goto L_0x01f9
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int unused = r0.recordSearchNum
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int unused = r0.formerFriendNum
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int unused = r0.recordSendNum
                    com.wx.assistants.utils.PerformClickUtils2 r0 = new com.wx.assistants.utils.PerformClickUtils2
                    r0.<init>()
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.spaceTime
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils$6$1 r2 = new com.wx.assistants.service_utils.AutoAddGroupFansUtils$6$1
                    r2.<init>(r7)
                    r0.countDown2(r1, r2)
                    goto L_0x0206
                L_0x01f9:
                    java.lang.String r0 = "WS_BABY_ChatroomInfoUI.6666____"
                    com.wx.assistants.utils.LogUtils.log(r0)
                    com.wx.assistants.utils.PerformClickUtils.performClick(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    r7.initContactFirstInfo()
                L_0x0206:
                    r0 = 1
                    goto L_0x020c
                L_0x0208:
                    int r1 = r1 + 1
                    goto L_0x00de
                L_0x020c:
                    if (r0 != 0) goto L_0x02db
                    java.lang.String r7 = "WS_BABY_END_7"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)
                    goto L_0x02db
                L_0x021c:
                    java.lang.String r7 = "WS_BABY_END_8"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)
                    goto L_0x02db
                L_0x022a:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = r1.startIndex
                    int r7 = r7.getChildCount()
                    if (r1 < r7) goto L_0x02db
                    boolean r7 = com.wx.assistants.application.MyApplication.enforceable
                    if (r7 == 0) goto L_0x02db
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.15"
                    com.wx.assistants.utils.LogUtils.log(r7)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.AutoService r7 = r7.autoService
                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id
                    com.wx.assistants.utils.PerformClickUtils.scroll(r7, r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    int r1 = com.wx.assistants.application.MyApplication.SCROLL_SPEED
                    r7.sleep(r1)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    com.wx.assistants.service.AutoService r7 = r7.autoService
                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id
                    java.util.List r7 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r7, (java.lang.String) r1)
                    if (r7 == 0) goto L_0x02c8
                    int r1 = r7.size()
                    if (r1 <= 0) goto L_0x02c8
                    java.lang.Object r7 = r7.get(r0)
                    android.view.accessibility.AccessibilityNodeInfo r7 = (android.view.accessibility.AccessibilityNodeInfo) r7
                    int r1 = r7.getChildCount()
                    if (r1 <= 0) goto L_0x02c8
                    int r1 = r7.getChildCount()
                    int r1 = r1 - r3
                    android.view.accessibility.AccessibilityNodeInfo r7 = r7.getChild(r1)
                    if (r7 == 0) goto L_0x0296
                    r1 = 0
                L_0x0279:
                    int r2 = r7.getChildCount()
                    if (r1 >= r2) goto L_0x0296
                    android.view.accessibility.AccessibilityNodeInfo r2 = r7.getChild(r1)
                    if (r2 == 0) goto L_0x0293
                    java.lang.String r4 = "android.widget.TextView"
                    java.lang.CharSequence r2 = r2.getClassName()
                    boolean r2 = r4.equals(r2)
                    if (r2 == 0) goto L_0x0293
                    r7 = 1
                    goto L_0x0297
                L_0x0293:
                    int r1 = r1 + 1
                    goto L_0x0279
                L_0x0296:
                    r7 = 0
                L_0x0297:
                    if (r7 != 0) goto L_0x02a4
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean unused = r7.isScrollBottom0 = r3
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean unused = r7.isScrollBottom = r3
                    goto L_0x02ae
                L_0x02a4:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean unused = r7.isScrollBottom0 = r0
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean unused = r7.isScrollBottom = r3
                L_0x02ae:
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder
                    r7.<init>()
                    java.lang.String r0 = "WS_BAB_SCROLLBOTTOM_"
                    r7.append(r0)
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    boolean r0 = r0.isScrollBottom
                    r7.append(r0)
                    java.lang.String r7 = r7.toString()
                    com.wx.assistants.utils.LogUtils.log(r7)
                L_0x02c8:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    r0 = 5
                    int unused = r7.startIndex = r0
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this
                    r0 = 500(0x1f4, float:7.0E-43)
                    r7.initSeeRoomMember(r0)
                    goto L_0x02db
                L_0x02d6:
                    java.lang.String r7 = "WS_BABY.SeeRoomMemberUI.16"
                    com.wx.assistants.utils.LogUtils.log(r7)
                L_0x02db:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddGroupFansUtils.AnonymousClass6.find(int):void");
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.SeeRoomMemberUI.222222");
            }
        });
    }

    public void jumpStartIndex() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            /* JADX WARNING: Removed duplicated region for block: B:77:0x0219 A[Catch:{ Exception -> 0x0249 }] */
            /* JADX WARNING: Removed duplicated region for block: B:78:0x021f A[Catch:{ Exception -> 0x0249 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void find(int r7) {
                /*
                    r6 = this;
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.fans     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r0 = r0.recordSearchNum     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7 - r0
                    r0 = 1
                    if (r7 <= 0) goto L_0x0026
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.recordSearchNum     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r1 = r1.startInt     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7 - r1
                    int r7 = r7 + r0
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x0249 }
                    if (r7 != r1) goto L_0x0032
                L_0x0026:
                    java.lang.String r7 = "WS_BABY_END_1"
                    com.wx.assistants.utils.LogUtils.log(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)     // Catch:{ Exception -> 0x0249 }
                L_0x0032:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    if (r7 != 0) goto L_0x0046
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id     // Catch:{ Exception -> 0x0249 }
                    java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0249 }
                    r7.lists = r1     // Catch:{ Exception -> 0x0249 }
                L_0x0046:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.size()     // Catch:{ Exception -> 0x0249 }
                    if (r7 <= 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    r1 = 0
                    java.lang.Object r7 = r7.get(r1)     // Catch:{ Exception -> 0x0249 }
                    android.view.accessibility.AccessibilityNodeInfo r7 = (android.view.accessibility.AccessibilityNodeInfo) r7     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r7 <= 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    java.lang.Object r7 = r7.get(r1)     // Catch:{ Exception -> 0x0249 }
                    android.view.accessibility.AccessibilityNodeInfo r7 = (android.view.accessibility.AccessibilityNodeInfo) r7     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x0249 }
                    int r3 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 >= r3) goto L_0x0198
                    boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0249 }
                    if (r2 == 0) goto L_0x0198
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x0249 }
                    android.view.accessibility.AccessibilityNodeInfo r7 = r7.getChild(r2)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean r2 = r2.isRemoveIndex     // Catch:{ Exception -> 0x0249 }
                    if (r2 != 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.startInt     // Catch:{ Exception -> 0x0249 }
                    if (r2 <= r0) goto L_0x0249
                    boolean r2 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0249 }
                    if (r2 == 0) goto L_0x0249
                    int r2 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 < r0) goto L_0x018a
                    r2 = 0
                L_0x00a6:
                    int r3 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 >= r3) goto L_0x017e
                    android.view.accessibility.AccessibilityNodeInfo r3 = r7.getChild(r2)     // Catch:{ Exception -> 0x0249 }
                    if (r3 == 0) goto L_0x017a
                    java.lang.String r4 = "android.widget.TextView"
                    java.lang.CharSequence r5 = r3.getClassName()     // Catch:{ Exception -> 0x0249 }
                    boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0249 }
                    if (r4 == 0) goto L_0x017a
                    java.lang.CharSequence r7 = r3.getText()     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x00de
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0249 }
                    r1.<init>()     // Catch:{ Exception -> 0x0249 }
                    java.lang.CharSequence r2 = r3.getText()     // Catch:{ Exception -> 0x0249 }
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = ""
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0249 }
                    java.lang.String unused = r7.nowName = r1     // Catch:{ Exception -> 0x0249 }
                L_0x00de:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean r7 = r7.isScrollBottom0     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x0104
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.LinkedHashSet r7 = r7.fansRecord0     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r1 = r1.nowName     // Catch:{ Exception -> 0x0249 }
                    boolean r7 = r7.contains(r1)     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x0104
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$208(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r7.jumpStartIndex()     // Catch:{ Exception -> 0x0249 }
                    goto L_0x017f
                L_0x0104:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.LinkedHashSet r7 = r7.fansRecord0     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r1 = r1.nowName     // Catch:{ Exception -> 0x0249 }
                    r7.add(r1)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$208(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$608(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils.access$2108(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager     // Catch:{ Exception -> 0x0249 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0249 }
                    r1.<init>()     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = "从第 "
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.startInt     // Catch:{ Exception -> 0x0249 }
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = " 人开始,已经跳过 "
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.recordSearchNum     // Catch:{ Exception -> 0x0249 }
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = " 人。"
                    r1.append(r2)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r7, r1)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.recordSearchNum     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r1 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r1 = r1.startInt     // Catch:{ Exception -> 0x0249 }
                    int r1 = r1 - r0
                    if (r7 != r1) goto L_0x0174
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r1 = 0
                    r7.lists = r1     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean unused = r7.isRemoveIndex = r0     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r1 = 500(0x1f4, float:7.0E-43)
                    r7.initSeeRoomMember(r1)     // Catch:{ Exception -> 0x0249 }
                    goto L_0x017f
                L_0x0174:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r7.jumpStartIndex()     // Catch:{ Exception -> 0x0249 }
                    goto L_0x017f
                L_0x017a:
                    int r2 = r2 + 1
                    goto L_0x00a6
                L_0x017e:
                    r0 = 0
                L_0x017f:
                    if (r0 != 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)     // Catch:{ Exception -> 0x0249 }
                    goto L_0x0249
                L_0x018a:
                    java.lang.String r7 = "WS_BABY_END_2"
                    com.wx.assistants.utils.LogUtils.log(r7)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.MyWindowManager r7 = r7.mMyManager     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r7)     // Catch:{ Exception -> 0x0249 }
                    goto L_0x0249
                L_0x0198:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 < r7) goto L_0x0249
                    boolean r7 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x0249
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.AutoService r7 = r7.autoService     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.utils.PerformClickUtils.scroll(r7, r2)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    int r2 = com.wx.assistants.application.MyApplication.SCROLL_SPEED     // Catch:{ Exception -> 0x0249 }
                    r7.sleep(r2)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r2 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service.AutoService r2 = r2.autoService     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.search_list_id     // Catch:{ Exception -> 0x0249 }
                    java.util.List r2 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x0249 }
                    r7.lists = r2     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x023e
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    int r7 = r7.size()     // Catch:{ Exception -> 0x0249 }
                    if (r7 <= 0) goto L_0x023e
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r7 = r7.lists     // Catch:{ Exception -> 0x0249 }
                    java.lang.Object r7 = r7.get(r1)     // Catch:{ Exception -> 0x0249 }
                    android.view.accessibility.AccessibilityNodeInfo r7 = (android.view.accessibility.AccessibilityNodeInfo) r7     // Catch:{ Exception -> 0x0249 }
                    int r2 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 <= 0) goto L_0x023e
                    int r2 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    int r2 = r2 - r0
                    android.view.accessibility.AccessibilityNodeInfo r7 = r7.getChild(r2)     // Catch:{ Exception -> 0x0249 }
                    if (r7 == 0) goto L_0x0216
                    int r2 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 <= 0) goto L_0x0216
                    r2 = 0
                L_0x01f8:
                    int r3 = r7.getChildCount()     // Catch:{ Exception -> 0x0249 }
                    if (r2 >= r3) goto L_0x0214
                    android.view.accessibility.AccessibilityNodeInfo r3 = r7.getChild(r2)     // Catch:{ Exception -> 0x0249 }
                    if (r3 == 0) goto L_0x0211
                    java.lang.String r4 = "android.widget.TextView"
                    java.lang.CharSequence r3 = r3.getClassName()     // Catch:{ Exception -> 0x0249 }
                    boolean r3 = r4.equals(r3)     // Catch:{ Exception -> 0x0249 }
                    if (r3 == 0) goto L_0x0211
                    goto L_0x0216
                L_0x0211:
                    int r2 = r2 + 1
                    goto L_0x01f8
                L_0x0214:
                    r7 = 0
                    goto L_0x0217
                L_0x0216:
                    r7 = 1
                L_0x0217:
                    if (r7 != 0) goto L_0x021f
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean unused = r7.isScrollBottom0 = r0     // Catch:{ Exception -> 0x0249 }
                    goto L_0x0224
                L_0x021f:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean unused = r7.isScrollBottom0 = r1     // Catch:{ Exception -> 0x0249 }
                L_0x0224:
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0249 }
                    r7.<init>()     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r0 = "WS_BAB_SCROLLBOTTOM_"
                    r7.append(r0)     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r0 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    boolean r0 = r0.isScrollBottom     // Catch:{ Exception -> 0x0249 }
                    r7.append(r0)     // Catch:{ Exception -> 0x0249 }
                    java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.utils.LogUtils.log(r7)     // Catch:{ Exception -> 0x0249 }
                L_0x023e:
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r0 = 5
                    int unused = r7.startIndex = r0     // Catch:{ Exception -> 0x0249 }
                    com.wx.assistants.service_utils.AutoAddGroupFansUtils r7 = com.wx.assistants.service_utils.AutoAddGroupFansUtils.this     // Catch:{ Exception -> 0x0249 }
                    r7.jumpStartIndex()     // Catch:{ Exception -> 0x0249 }
                L_0x0249:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddGroupFansUtils.AnonymousClass7.find(int):void");
            }
        });
    }

    public void jumpStartIndexFromSmallGroup() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                AccessibilityNodeInfo accessibilityNodeInfo;
                AccessibilityNodeInfo child;
                LogUtils.log("WS_BABY_jumpStartIndexFromSmallGroup_fans=" + AutoAddGroupFansUtils.this.fans + "，recordSearchNum=" + AutoAddGroupFansUtils.this.recordSearchNum);
                if (AutoAddGroupFansUtils.this.fans - AutoAddGroupFansUtils.this.recordSearchNum <= 0 || (AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1 == AutoAddGroupFansUtils.this.maxNum) {
                    LogUtils.log("WS_BABY_END_3");
                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                }
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.list_head_img_id);
                if (findViewIdList != null && findViewIdList.size() > 0) {
                    if (AutoAddGroupFansUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList.get(AutoAddGroupFansUtils.this.startIndex);
                        if (!AutoAddGroupFansUtils.this.isRemoveIndex && AutoAddGroupFansUtils.this.startInt > 1 && MyApplication.enforceable) {
                            String str = accessibilityNodeInfo2.getContentDescription() + "";
                            if ((!str.equals("添加成员") && MyApplication.enforceable) || (!str.equals("删除成员") && MyApplication.enforceable)) {
                                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                                if (findViewIdList2.size() > AutoAddGroupFansUtils.this.startIndex) {
                                    AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList2.get(AutoAddGroupFansUtils.this.startIndex);
                                    if (accessibilityNodeInfo3 == null || accessibilityNodeInfo3.getText() == null) {
                                        LogUtils.log("WS_BABY_ChatroomInfoUI.333333_");
                                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                        return;
                                    }
                                    String str2 = accessibilityNodeInfo3.getText() + "";
                                    if (!AutoAddGroupFansUtils.this.isScrollBottom0 || !AutoAddGroupFansUtils.this.fansRecord0.contains(str2)) {
                                        AutoAddGroupFansUtils.this.fansRecord0.add(str2);
                                        AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                        AutoAddGroupFansUtils.access$608(AutoAddGroupFansUtils.this);
                                        AutoAddGroupFansUtils.access$2108(AutoAddGroupFansUtils.this);
                                        BaseServiceUtils.updateBottomText(AutoAddGroupFansUtils.this.mMyManager, "从第 " + AutoAddGroupFansUtils.this.startInt + " 人开始,已跳过 " + AutoAddGroupFansUtils.this.recordSearchNum + " 人。");
                                        if (AutoAddGroupFansUtils.this.recordSearchNum == AutoAddGroupFansUtils.this.startInt - 1) {
                                            boolean unused = AutoAddGroupFansUtils.this.isRemoveIndex = true;
                                            AutoAddGroupFansUtils.this.executeSmallGroup();
                                            return;
                                        }
                                        AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                                        return;
                                    }
                                    AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                    AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                                    return;
                                }
                                AutoAddGroupFansUtils.this.fansRecord0.add("");
                                AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                AutoAddGroupFansUtils.access$608(AutoAddGroupFansUtils.this);
                                AutoAddGroupFansUtils.access$2108(AutoAddGroupFansUtils.this);
                                BaseServiceUtils.updateBottomText(AutoAddGroupFansUtils.this.mMyManager, "从第 " + AutoAddGroupFansUtils.this.startInt + " 人开始,已跳过 " + AutoAddGroupFansUtils.this.recordSearchNum + " 人。");
                                if (AutoAddGroupFansUtils.this.recordSearchNum == AutoAddGroupFansUtils.this.startInt - 1) {
                                    boolean unused2 = AutoAddGroupFansUtils.this.isRemoveIndex = true;
                                    AutoAddGroupFansUtils.this.executeSmallGroup();
                                    return;
                                }
                                AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                            }
                        }
                    } else if (AutoAddGroupFansUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                        int i2 = 5;
                        try {
                            List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                            if (findViewIdList3 != null && findViewIdList3.size() > 0 && (accessibilityNodeInfo = findViewIdList3.get(0)) != null && accessibilityNodeInfo.getChildCount() > 0 && (child = accessibilityNodeInfo.getChild(0)) != null && child.getChildCount() > 0) {
                                int childCount = child.getChildCount();
                                try {
                                    LogUtils.log("WS_BABY_ROW_NUM" + childCount);
                                } catch (Exception unused3) {
                                }
                                i2 = childCount;
                            }
                        } catch (Exception unused4) {
                        }
                        PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                        AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        int unused5 = AutoAddGroupFansUtils.this.startIndex = i2;
                        AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                    }
                }
            }
        });
    }

    public void initContactFirstInfo() {
        updateBottomText(false, 0);
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "添加到通讯录", "发消息", this.sex == Gender.ALL ? CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION : 1500, 100, 40, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                if (i == 0 && MyApplication.enforceable) {
                    new Thread(new Runnable() {
                        public void run() {
                            LogUtils.log("WS_BABY.ContactInfoUI.2");
                            if (AutoAddGroupFansUtils.this.sex == Gender.MAN || AutoAddGroupFansUtils.this.sex == Gender.WOMEN) {
                                try {
                                    AccessibilityNodeInfo rootInActiveWindow = AutoAddGroupFansUtils.this.autoService.getRootInActiveWindow();
                                    if (rootInActiveWindow != null) {
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.user_sex);
                                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY.ContactInfoUI.66.不男不女返回");
                                            AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                                            PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                                            if (AutoAddGroupFansUtils.this.isSeeMore) {
                                                AutoAddGroupFansUtils.this.seeRoomMember();
                                            } else {
                                                AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                            }
                                        } else {
                                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                                            if (accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null || !MyApplication.enforceable) {
                                                LogUtils.log("WS_BABY.ContactInfoUI.66.不男不女返回");
                                                AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                                                PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                                                if (AutoAddGroupFansUtils.this.isSeeMore) {
                                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                                } else {
                                                    AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                                }
                                            } else {
                                                String charSequence = accessibilityNodeInfo.getContentDescription().toString();
                                                if (AutoAddGroupFansUtils.this.sex == Gender.MAN && "男".equals(charSequence) && MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY.ContactInfoUI.男");
                                                    AutoAddGroupFansUtils.this.addContact();
                                                } else if (AutoAddGroupFansUtils.this.sex != Gender.WOMEN || !"女".equals(charSequence) || !MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY.ContactInfoUI.6.不男不女返回");
                                                    AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                                                    PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                                                    if (AutoAddGroupFansUtils.this.isSeeMore) {
                                                        AutoAddGroupFansUtils.this.seeRoomMember();
                                                    } else {
                                                        AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                                    }
                                                } else {
                                                    LogUtils.log("WS_BABY.ContactInfoUI.女");
                                                    AutoAddGroupFansUtils.this.addContact();
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception unused) {
                                    if (AutoAddGroupFansUtils.this.sex == Gender.ALL) {
                                        LogUtils.log("WS_BABY.ContactInfoUI.9");
                                        AutoAddGroupFansUtils.this.addContact();
                                        return;
                                    }
                                    LogUtils.log("WS_BABY.ContactInfoUI.6.不男不女返回");
                                    AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                                    PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                                    if (AutoAddGroupFansUtils.this.isSeeMore) {
                                        AutoAddGroupFansUtils.this.seeRoomMember();
                                    } else {
                                        AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                    }
                                }
                            } else {
                                LogUtils.log("WS_BABY.ContactInfoUI.99");
                                AutoAddGroupFansUtils.this.addContact();
                            }
                        }
                    }).start();
                } else if (i == 1 && MyApplication.enforceable) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                AutoAddGroupFansUtils.access$2008(AutoAddGroupFansUtils.this);
                                AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                                AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                                PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                                if (AutoAddGroupFansUtils.this.isSeeMore) {
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                } else {
                                    AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }).start();
                }
            }

            public void nuFind() {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            LogUtils.log("WS_BABY_发消息@Back2");
                            AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.backTime);
                            PerformClickUtils.performBack(AutoAddGroupFansUtils.this.autoService);
                            if (AutoAddGroupFansUtils.this.isSeeMore) {
                                AutoAddGroupFansUtils.this.seeRoomMember();
                            } else {
                                AutoAddGroupFansUtils.this.initChatroomInfoUI();
                            }
                        } catch (Exception unused) {
                        }
                    }
                }).start();
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0082, code lost:
        r0 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addContact() {
        /*
            r7 = this;
            com.wx.assistants.service.AutoService r0 = r7.autoService     // Catch:{ Exception -> 0x00a6 }
            java.lang.String r0 = com.wx.assistants.utils.PerformClickUtils.getGroupNickName(r0)     // Catch:{ Exception -> 0x00a6 }
            r7.groupNickName = r0     // Catch:{ Exception -> 0x00a6 }
            java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x00a6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a6 }
            r1.<init>()     // Catch:{ Exception -> 0x00a6 }
            java.lang.String r2 = "WS_BABY_GROUP_NICKNAME_"
            r1.append(r2)     // Catch:{ Exception -> 0x00a6 }
            java.lang.String r2 = r7.groupNickName     // Catch:{ Exception -> 0x00a6 }
            r1.append(r2)     // Catch:{ Exception -> 0x00a6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00a6 }
            r0.println(r1)     // Catch:{ Exception -> 0x00a6 }
            java.util.LinkedHashSet<java.lang.String> r0 = r7.filterNames     // Catch:{ Exception -> 0x00a6 }
            if (r0 == 0) goto L_0x00a2
            java.util.LinkedHashSet<java.lang.String> r0 = r7.filterNames     // Catch:{ Exception -> 0x00a6 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x00a6 }
            if (r0 <= 0) goto L_0x00a2
            r0 = 0
            java.util.LinkedHashSet<java.lang.String> r1 = r7.filterNames     // Catch:{ Exception -> 0x009b }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x009b }
        L_0x0033:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x009b }
            r3 = 1
            if (r2 == 0) goto L_0x0083
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x009b }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b }
            r5.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.String r6 = "WS_BABY_FILTER_NAME_"
            r5.append(r6)     // Catch:{ Exception -> 0x009b }
            r5.append(r2)     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x009b }
            r4.println(r5)     // Catch:{ Exception -> 0x009b }
            java.lang.String r4 = r7.nowName     // Catch:{ Exception -> 0x009b }
            if (r4 == 0) goto L_0x006c
            java.lang.String r4 = ""
            java.lang.String r5 = r7.nowName     // Catch:{ Exception -> 0x009b }
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x009b }
            if (r4 != 0) goto L_0x006c
            java.lang.String r4 = r7.nowName     // Catch:{ Exception -> 0x009b }
            boolean r4 = r4.contains(r2)     // Catch:{ Exception -> 0x009b }
            if (r4 != 0) goto L_0x0082
        L_0x006c:
            java.lang.String r4 = r7.groupNickName     // Catch:{ Exception -> 0x009b }
            if (r4 == 0) goto L_0x0033
            java.lang.String r4 = ""
            java.lang.String r5 = r7.groupNickName     // Catch:{ Exception -> 0x009b }
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x009b }
            if (r4 != 0) goto L_0x0033
            java.lang.String r4 = r7.groupNickName     // Catch:{ Exception -> 0x009b }
            boolean r2 = r4.contains(r2)     // Catch:{ Exception -> 0x009b }
            if (r2 == 0) goto L_0x0033
        L_0x0082:
            r0 = 1
        L_0x0083:
            if (r0 != 0) goto L_0x0089
            r7.executeAdd()     // Catch:{ Exception -> 0x009b }
            goto L_0x00aa
        L_0x0089:
            int r1 = r7.formerFriendNum     // Catch:{ Exception -> 0x009b }
            int r1 = r1 + r3
            r7.formerFriendNum = r1     // Catch:{ Exception -> 0x009b }
            com.wx.assistants.service.AutoService r1 = r7.autoService     // Catch:{ Exception -> 0x009b }
            r2 = 30
            com.wx.assistants.service_utils.AutoAddGroupFansUtils$10 r3 = new com.wx.assistants.service_utils.AutoAddGroupFansUtils$10     // Catch:{ Exception -> 0x009b }
            r3.<init>()     // Catch:{ Exception -> 0x009b }
            com.wx.assistants.utils.PerformClickUtils.executedBackGroupMember(r1, r2, r3)     // Catch:{ Exception -> 0x009b }
            goto L_0x00aa
        L_0x009b:
            if (r0 != 0) goto L_0x00aa
            r7.executeAdd()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00aa
        L_0x00a2:
            r7.executeAdd()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00aa
        L_0x00a6:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00aa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoAddGroupFansUtils.addContact():void");
    }

    public void executeAdd() {
        if (!PerformClickUtils.findNodeIsExistByText(this.autoService, "添加到通讯录") || !MyApplication.enforceable) {
            this.formerFriendNum++;
            PerformClickUtils.executedBackGroupMember(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                public void find() {
                    if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                        AutoAddGroupFansUtils.this.initChatroomInfoUI();
                        return;
                    }
                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                    AutoAddGroupFansUtils.this.seeRoomMember();
                }
            });
            return;
        }
        new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "朋友圈", "个人相册", "个性签名", 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                AutoAddGroupFansUtils.this.executeAddFans();
            }

            public void nuFind() {
                AutoAddGroupFansUtils.this.executeAddFans();
            }
        });
    }

    public void executeAddFans() {
        if (this.remarkLabel == null || "".equals(this.remarkLabel)) {
            executedTask();
        } else {
            new PerformClickUtils2().checkString(this.autoService, "设置备注和标签", 0, 50, 20, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactInfoUI_2");
                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "设置备注和标签");
                    AutoAddGroupFansUtils.this.sleep(BannerConfig.DURATION);
                    if (PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, AutoAddGroupFansUtils.this.remarkLabel)) {
                        PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "保存");
                        AutoAddGroupFansUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                        AutoAddGroupFansUtils.this.executedTask();
                        return;
                    }
                    new PerformClickUtils2().checkNodeIds(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 0, 100, 20, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            if (i == 0) {
                                PerformClickUtils.findViewIdAndClick(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.input_remark_label);
                            } else if (i == 1) {
                                PerformClickUtils.findViewIdAndClick(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                            }
                            new PerformClickUtils2().checkString(AutoAddGroupFansUtils.this.autoService, AutoAddGroupFansUtils.this.remarkLabel, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, AutoAddGroupFansUtils.this.remarkLabel);
                                    AutoAddGroupFansUtils.this.sleep(300);
                                    PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "保存");
                                    new PerformClickUtils2().checkStringAndIdSomeOne(AutoAddGroupFansUtils.this.autoService, "设置备注", BaseServiceUtils.dialog_ok_id, SocializeConstants.CANCLE_RESULTCODE, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            if (i == 0) {
                                                PerformClickUtils.findTextAndClick(AutoAddGroupFansUtils.this.autoService, "保存");
                                                AutoAddGroupFansUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                                AutoAddGroupFansUtils.this.executedTask();
                                            } else if (i == 1) {
                                                PerformClickUtils.executedLabelBack(AutoAddGroupFansUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        AutoAddGroupFansUtils.this.executedTask();
                                                    }
                                                });
                                            }
                                        }

                                        public void nuFind() {
                                            PerformClickUtils.executedLabelBack(AutoAddGroupFansUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                                public void find() {
                                                    AutoAddGroupFansUtils.this.executedTask();
                                                }
                                            });
                                        }
                                    });
                                }

                                public void nuFind() {
                                    PerformClickUtils.executedLabelBack(AutoAddGroupFansUtils.this.autoService, 30, "添加到通讯录", new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            AutoAddGroupFansUtils.this.executedTask();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                public void nuFind() {
                    AutoAddGroupFansUtils.this.executedTask();
                }
            });
        }
    }

    public void executedTask() {
        sleep(100);
        PerformClickUtils.findTextAndClick(this.autoService, "添加到通讯录");
        LogUtils.log("WS_BABY_添加到通讯录.1.1");
        new PerformClickUtils2().checkString(this.autoService, "正在添加", 0, 40, 70, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                new PerformClickUtils2().checkIsContactAddress2(AutoAddGroupFansUtils.this.autoService, 0, new PerformClickUtils2.OnCheckListener4() {
                    public void goValidate() {
                    }

                    public void nuFind() {
                    }

                    public void nilAdding() {
                        LogUtils.log("WS_BABY_添加到通讯录.2");
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    AutoAddGroupFansUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "视频动态");
                                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "申请添加朋友");
                                    if (!findNodeIsExistByText) {
                                        if (!findNodeIsExistByText2) {
                                            if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                                boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "发消息");
                                                boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "添加到通讯录");
                                                if (!findNodeIsExistByText3) {
                                                    if (!findNodeIsExistByText4) {
                                                        AutoAddGroupFansUtils.this.sleep(600);
                                                        if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                                            LogUtils.log("WS_BABY_SayHiWithSnsPermissionUI.视频动态");
                                                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "视频动态");
                                                            boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "申请添加朋友");
                                                            if ((findNodeIsExistByText5 || findNodeIsExistByText6) && MyApplication.enforceable) {
                                                                AutoAddGroupFansUtils.this.inputSayContent();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        try {
                                                            LogUtils.log("WS_BABY_添加到通讯录.dialog1");
                                                            AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                                                            PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                                public void find() {
                                                                    if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                                                        AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                                                        return;
                                                                    }
                                                                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                                                }
                                                            });
                                                            return;
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            return;
                                                        }
                                                    }
                                                }
                                                if (findNodeIsExistByText3 && MyApplication.enforceable) {
                                                    AutoAddGroupFansUtils.access$2008(AutoAddGroupFansUtils.this);
                                                    AutoAddGroupFansUtils.access$2108(AutoAddGroupFansUtils.this);
                                                    AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                                                    LogUtils.log("WS_BABY_添加到通讯录_END_1");
                                                }
                                                if (AutoAddGroupFansUtils.this.remarkPrefix == null || AutoAddGroupFansUtils.this.remarkPrefix.equals("") || !PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "发消息") || !MyApplication.enforceable) {
                                                    PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                                                AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                                                return;
                                                            }
                                                            LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                                            AutoAddGroupFansUtils.this.seeRoomMember();
                                                        }
                                                    });
                                                    return;
                                                }
                                                LogUtils.log("WS_BABY_添加到通讯录_END_2");
                                                AutoAddGroupFansUtils.this.remarkInfo();
                                                return;
                                            }
                                            try {
                                                LogUtils.log("WS_BABY_添加到通讯录.dialog0");
                                                AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                                                PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                    public void find() {
                                                        if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                                            AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                                            return;
                                                        }
                                                        LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                                        AutoAddGroupFansUtils.this.seeRoomMember();
                                                    }
                                                });
                                                return;
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                                return;
                                            }
                                        }
                                    }
                                    AutoAddGroupFansUtils.this.inputSayContent();
                                } catch (Exception unused) {
                                }
                            }
                        }).start();
                    }
                });
            }

            public void nuFind() {
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "视频动态");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "申请添加朋友");
                if (findNodeIsExistByText || findNodeIsExistByText2) {
                    AutoAddGroupFansUtils.this.inputSayContent();
                    return;
                }
                AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                            AutoAddGroupFansUtils.this.initChatroomInfoUI();
                            return;
                        }
                        LogUtils.log("WS_BABY.ContactInfoUI.1111");
                        AutoAddGroupFansUtils.this.seeRoomMember();
                    }
                });
            }
        });
    }

    public void remarkInfo() {
        LogUtils.log("WS_BABY.ContactInfoUI.22");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                AccessibilityNodeInfo rootInActiveWindow = AutoAddGroupFansUtils.this.autoService.getRootInActiveWindow();
                if (rootInActiveWindow != null && MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.right_more_id);
                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.ContactInfoUI.5.点开更多");
                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                    }
                    AutoAddGroupFansUtils.this.sleep(AutoAddGroupFansUtils.this.jumpTime);
                    AccessibilityNodeInfo rootInActiveWindow2 = AutoAddGroupFansUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow2 != null && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.ContactInfoUI.33");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow2.findAccessibilityNodeInfosByText("设置备注和标签");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText2 = rootInActiveWindow2.findAccessibilityNodeInfosByText("设置备注及标签");
                        if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactInfoUI.10.设置备注和标签");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText.get(0));
                            AutoAddGroupFansUtils.this.initContactRemarkInfoModUI();
                        } else if (findAccessibilityNodeInfosByText2 != null && findAccessibilityNodeInfosByText2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactInfoUI.11.设置备注及标签");
                            PerformClickUtils.performClick(findAccessibilityNodeInfosByText2.get(0));
                            AutoAddGroupFansUtils.this.initContactRemarkInfoModUI();
                        }
                    }
                }
            }
        });
    }

    public void middleViewShow() {
        try {
            if (this.sendResult == null || "".equals(this.sendResult)) {
                int i = this.recordSendNum - this.startInt;
                if (this.verifyCode == 0) {
                    MyWindowManager myWindowManager = this.mMyManager;
                    updateMiddleText(myWindowManager, "一键自动群内加粉", "共检索了" + ((this.recordSearchNum - this.startInt) + 1) + "人");
                    return;
                }
                MyWindowManager myWindowManager2 = this.mMyManager;
                StringBuilder sb = new StringBuilder();
                sb.append("共检索了");
                sb.append((this.recordSearchNum - this.startInt) + 1);
                sb.append("人，已向 ");
                if (i <= 0) {
                    i = 0;
                }
                sb.append(i);
                sb.append(" 个好友，发起添加请求。");
                updateMiddleText(myWindowManager2, "一键自动群内加粉", sb.toString());
                return;
            }
            updateMiddleText(this.mMyManager, "自动群内加粉结束", this.sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputSayContent() {
        try {
            if (this.verifyCode == 0) {
                this.recordSendNum++;
                updateBottomText(false, 0);
                PerformClickUtils.executedBackGroupMember(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                            AutoAddGroupFansUtils.this.initChatroomInfoUI();
                            return;
                        }
                        LogUtils.log("WS_BABY.ContactInfoUI.1111");
                        AutoAddGroupFansUtils.this.seeRoomMember();
                    }
                });
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
                } catch (Exception unused) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.sayContent.error");
                }
                if (this.groupNickName != null && !"".equals(this.groupNickName)) {
                    PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                    sleep(100);
                    PerformClickUtils.findViewByIdAndPasteContent(this.autoService, input_remark, this.groupNickName);
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
                try {
                    if (this.remarkPrefix != null && !"".equals(this.remarkPrefix) && MyApplication.enforceable) {
                        PerformClickUtils.findViewIdAndClick(this.autoService, input_remark);
                        sleep(100);
                        PerformClickUtils.inputPrefixText(this.autoService, this.remarkPrefix);
                    }
                } catch (Exception unused2) {
                    LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.remarkPrefix.error");
                }
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.3");
                this.recordSendNum++;
                this.sendApply++;
                updateBottomText(false, 0);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.4");
                PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
                sleep(300);
                LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.5");
                new PerformClickUtils2().checkIsSending(this.autoService, 0, new PerformClickUtils2.OnCheckListener3() {
                    public void executeBack() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack");
                        AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                        PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddGroupFansUtils.access$2708(AutoAddGroupFansUtils.this);
                                PrintStream printStream = System.out;
                                printStream.println("WS_BABY_NUM2_" + AutoAddGroupFansUtils.this.executeSayHellNum + ListUtils.DEFAULT_JOIN_SEPARATOR + AutoAddGroupFansUtils.this.sayHelloNum);
                                if (AutoAddGroupFansUtils.this.executeSayHellNum >= AutoAddGroupFansUtils.this.sayHelloNum) {
                                    AutoAddGroupFansUtils autoAddGroupFansUtils = AutoAddGroupFansUtils.this;
                                    String unused = autoAddGroupFansUtils.sendResult = "共检索了" + ((AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1) + "人，打招呼 " + AutoAddGroupFansUtils.this.executeSayHellNum + " 人。";
                                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                } else if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                    AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                } else {
                                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                }
                            }
                        });
                    }

                    public void backContactInfo() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.backContactInfo");
                        AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                        PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddGroupFansUtils.access$2708(AutoAddGroupFansUtils.this);
                                PrintStream printStream = System.out;
                                printStream.println("WS_BABY_NUM2_" + AutoAddGroupFansUtils.this.executeSayHellNum + ListUtils.DEFAULT_JOIN_SEPARATOR + AutoAddGroupFansUtils.this.sayHelloNum);
                                if (AutoAddGroupFansUtils.this.executeSayHellNum >= AutoAddGroupFansUtils.this.sayHelloNum) {
                                    AutoAddGroupFansUtils autoAddGroupFansUtils = AutoAddGroupFansUtils.this;
                                    String unused = autoAddGroupFansUtils.sendResult = "共检索了" + ((AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1) + "人，打招呼 " + AutoAddGroupFansUtils.this.executeSayHellNum + " 人。";
                                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                } else if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                    AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                } else {
                                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                }
                            }
                        });
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.executeBack_unfind");
                        AutoAddGroupFansUtils.this.updateBottomText(false, 0);
                        PerformClickUtils.executedBackGroupMember(AutoAddGroupFansUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                AutoAddGroupFansUtils.access$2708(AutoAddGroupFansUtils.this);
                                PrintStream printStream = System.out;
                                printStream.println("WS_BABY_NUM2_" + AutoAddGroupFansUtils.this.executeSayHellNum + ListUtils.DEFAULT_JOIN_SEPARATOR + AutoAddGroupFansUtils.this.sayHelloNum);
                                if (AutoAddGroupFansUtils.this.executeSayHellNum >= AutoAddGroupFansUtils.this.sayHelloNum) {
                                    AutoAddGroupFansUtils autoAddGroupFansUtils = AutoAddGroupFansUtils.this;
                                    String unused = autoAddGroupFansUtils.sendResult = "共检索了" + ((AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1) + "人，打招呼 " + AutoAddGroupFansUtils.this.executeSayHellNum + " 人。";
                                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                } else if (!AutoAddGroupFansUtils.this.isSeeMore || !MyApplication.enforceable) {
                                    AutoAddGroupFansUtils.this.initChatroomInfoUI();
                                } else {
                                    LogUtils.log("WS_BABY.ContactInfoUI.1111");
                                    AutoAddGroupFansUtils.this.seeRoomMember();
                                }
                            }
                        });
                    }
                });
                return;
            }
            LogUtils.log("WS_BABY.SayHiWithSnsPermissionUI.2");
            updateBottomText(false, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, contact_title_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    String textByNodeId = PerformClickUtils.getTextByNodeId(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.contact_title_id);
                    if (textByNodeId.contains("(") && textByNodeId.contains(")")) {
                        int unused = AutoAddGroupFansUtils.this.fans = Integer.parseInt(textByNodeId.substring(textByNodeId.lastIndexOf("(") + 1, textByNodeId.lastIndexOf(")")));
                    }
                    LogUtils.log("WS_BABY_COUNT." + AutoAddGroupFansUtils.this.fans);
                    new PerformClickUtils2().check(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.right_more_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.right_more_id);
                            AutoAddGroupFansUtils.this.initChatroomInfoUI();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在自动添加粉丝，请不要操作微信");
            new Thread(new Runnable() {
                public void run() {
                    BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                        public void run() {
                            AutoAddGroupFansUtils.this.executeMain();
                        }
                    }, 10);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }
}
