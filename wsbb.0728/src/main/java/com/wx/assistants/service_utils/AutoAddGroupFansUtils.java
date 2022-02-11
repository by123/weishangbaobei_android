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
import java.util.Iterator;
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

    private AutoAddGroupFansUtils() {
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

    public static AutoAddGroupFansUtils getInstance() {
        instance = new AutoAddGroupFansUtils();
        return instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009e A[SYNTHETIC, Splitter:B:29:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[Catch:{ Exception -> 0x009b }, RETURN, SYNTHETIC] */
    public void addContact() {
        boolean z;
        try {
            this.groupNickName = PerformClickUtils.getGroupNickName(this.autoService);
            System.out.println("WS_BABY_GROUP_NICKNAME_" + this.groupNickName);
            if (this.filterNames == null || this.filterNames.size() <= 0) {
                executeAdd();
                return;
            }
            try {
                Iterator it = this.filterNames.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    String str = (String) it.next();
                    System.out.println("WS_BABY_FILTER_NAME_" + str);
                    if ((this.nowName == null || "".equals(this.nowName) || !this.nowName.contains(str)) && (this.groupNickName == null || "".equals(this.groupNickName) || !this.groupNickName.contains(str))) {
                    }
                }
                z = true;
                if (!z) {
                    try {
                        executeAdd();
                    } catch (Exception e) {
                        if (z) {
                            executeAdd();
                        }
                    }
                } else {
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
                }
            } catch (Exception e2) {
                z = false;
                if (z) {
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
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

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                    AutoAddGroupFansUtils.this.executedTask();
                }
            });
        }
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().check(this.autoService, contact_title_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String textByNodeId = PerformClickUtils.getTextByNodeId(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.contact_title_id);
                    if (textByNodeId.contains("(") && textByNodeId.contains(")")) {
                        int unused = AutoAddGroupFansUtils.this.fans = Integer.parseInt(textByNodeId.substring(textByNodeId.lastIndexOf("(") + 1, textByNodeId.lastIndexOf(")")));
                    }
                    LogUtils.log("WS_BABY_COUNT." + AutoAddGroupFansUtils.this.fans);
                    new PerformClickUtils2().check(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.right_more_id, 100, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.right_more_id);
                            AutoAddGroupFansUtils.this.initChatroomInfoUI();
                        }

                        public void nuFind() {
                        }
                    });
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeSmallGroup() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY.small.group_fans=" + AutoAddGroupFansUtils.this.fans + ",recordSearchNum=" + AutoAddGroupFansUtils.this.recordSearchNum);
                if (AutoAddGroupFansUtils.this.fans - AutoAddGroupFansUtils.this.recordSearchNum <= 0 || (AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1 == AutoAddGroupFansUtils.this.maxNum) {
                    LogUtils.log("WS_BABY_END_4");
                    BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                }
                new PerformClickUtils2().checkNodeAllIds(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id, BaseServiceUtils.list_head_img_id, 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int i2;
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
                                try {
                                    List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    if (findViewIdList3 == null || findViewIdList3.size() <= 0 || (accessibilityNodeInfo = findViewIdList3.get(0)) == null || accessibilityNodeInfo.getChildCount() <= 0 || (child = accessibilityNodeInfo.getChild(0)) == null || child.getChildCount() <= 0) {
                                        i2 = 5;
                                        PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                        AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        int unused3 = AutoAddGroupFansUtils.this.startIndex = i2;
                                        AutoAddGroupFansUtils.this.executeSmallGroup();
                                    }
                                    i2 = child.getChildCount();
                                    try {
                                        LogUtils.log("WS_BABY_ROW_NUM" + i2);
                                    } catch (Exception e) {
                                    }
                                    PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                    AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                    int unused4 = AutoAddGroupFansUtils.this.startIndex = i2;
                                    AutoAddGroupFansUtils.this.executeSmallGroup();
                                } catch (Exception e2) {
                                    i2 = 5;
                                }
                            }
                        }
                    }

                    public void nuFind() {
                    }
                });
            }

            public void nuFind() {
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
            public void end() {
                LogUtils.log("WS_BABY_ChatroomInfoUI.5555____");
                PerformClickUtils.performClick(accessibilityNodeInfo);
                AutoAddGroupFansUtils.this.initContactFirstInfo();
            }

            public void surplus(int i) {
                AutoAddGroupFansUtils.this.updateBottomText(true, i);
            }
        });
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

                    public void nilAdding() {
                        LogUtils.log("WS_BABY_添加到通讯录.2");
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    AutoAddGroupFansUtils.this.sleep(SocializeConstants.CANCLE_RESULTCODE);
                                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "视频动态");
                                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "申请添加朋友");
                                    if (findNodeIsExistByText || findNodeIsExistByText2) {
                                        AutoAddGroupFansUtils.this.inputSayContent();
                                    } else if (!PerformClickUtils.findNodeIsExistById((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.dialog_ok_id) || !MyApplication.enforceable) {
                                        boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "发消息");
                                        boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(AutoAddGroupFansUtils.this.autoService, "添加到通讯录");
                                        if (findNodeIsExistByText3 || findNodeIsExistByText4) {
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
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
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

    public void initChatroomInfoUI() {
        try {
            LogUtils.log("WS_BABY.isFirstChatroomInfoUI");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
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
                            } catch (Exception e) {
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
                                } catch (Exception e) {
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
                            } catch (Exception e) {
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
                        } catch (Exception e) {
                        }
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

    public void initSeeRoomMember(final int i) {
        LogUtils.log("WS_BABY.SeeRoomMemberUI.1." + i);
        updateBottomText(false, 0);
        LogUtils.log("WS_BABY.SeeRoomMemberUI.2");
        new PerformClickUtils2().checkNodeAllIds(this.autoService, search_list_id, search_list_item_name, i, 100, 50, new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Removed duplicated region for block: B:76:0x0292  */
            /* JADX WARNING: Removed duplicated region for block: B:81:0x02ca  */
            public void find(int i) {
                boolean z;
                boolean z2 = true;
                LogUtils.log("WS_BABY.SeeRoomMemberUI.3");
                if (AutoAddGroupFansUtils.this.isFirstJump) {
                    boolean unused = AutoAddGroupFansUtils.this.isFirstJump = false;
                    PerformClickUtils.scrollTop(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                    AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                    LogUtils.log("WS_BABY.SeeRoomMemberUI.4");
                }
                LogUtils.log("WS_BABY.SeeRoomMemberUI.5");
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                if (findViewIdList == null || findViewIdList.size() <= 0 || findViewIdList.get(0).getChildCount() <= 0) {
                    LogUtils.log("WS_BABY.SeeRoomMemberUI.16");
                    return;
                }
                LogUtils.log("WS_BABY.SeeRoomMemberUI.6");
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(0);
                LogUtils.log("WS_BABY_ChatroomInfoUI.7");
                if (AutoAddGroupFansUtils.this.startIndex < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_ChatroomInfoUI.8");
                    final AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(AutoAddGroupFansUtils.this.startIndex);
                    if (AutoAddGroupFansUtils.this.isRemoveIndex || AutoAddGroupFansUtils.this.startInt <= 1 || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY.SeeRoomMemberUI.10");
                        AutoAddGroupFansUtils.this.sleep(i);
                        if (child == null) {
                            LogUtils.log("WS_BABY.SeeRoomMemberUI.11");
                            return;
                        }
                        LogUtils.log("WS_BABY.SeeRoomMemberUI.12");
                        if (child.getChildCount() > 0) {
                            LogUtils.log("WS_BABY.SeeRoomMemberUI.13");
                            int i2 = 0;
                            while (true) {
                                if (i2 >= child.getChildCount()) {
                                    z2 = false;
                                    break;
                                }
                                AccessibilityNodeInfo child2 = child.getChild(i2);
                                if (child2 == null || child2.getText() == null || !"android.widget.TextView".equals(child2.getClassName())) {
                                    i2++;
                                } else {
                                    String unused2 = AutoAddGroupFansUtils.this.nowName = child2.getText() + "";
                                    LogUtils.log("WS_BABY_ChatroomInfoUI.3_" + AutoAddGroupFansUtils.this.nowName);
                                    if (!AutoAddGroupFansUtils.this.isScrollBottom || !AutoAddGroupFansUtils.this.fansRecord.contains(AutoAddGroupFansUtils.this.nowName)) {
                                        AutoAddGroupFansUtils.this.fansRecord.add(AutoAddGroupFansUtils.this.nowName);
                                        LogUtils.log("WS_BABY_ChatroomInfoUI.3____" + AutoAddGroupFansUtils.this.recordSearchNum);
                                        AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                        AutoAddGroupFansUtils.access$608(AutoAddGroupFansUtils.this);
                                        if (AutoAddGroupFansUtils.this.ffModel == 1) {
                                            int unused3 = AutoAddGroupFansUtils.this.spaceTime = PerformClickUtils.getRandomTime(AutoAddGroupFansUtils.this.ffModel, AutoAddGroupFansUtils.this.ffModelStartTime, AutoAddGroupFansUtils.this.ffModelEndTime);
                                            LogUtils.log("WS_BABY_ChatroomInfoUI.spaceTime=" + AutoAddGroupFansUtils.this.spaceTime);
                                        }
                                        if (AutoAddGroupFansUtils.this.spaceTime <= 0 || AutoAddGroupFansUtils.this.recordSearchNum <= 1) {
                                            LogUtils.log("WS_BABY_ChatroomInfoUI.6666____");
                                            PerformClickUtils.performClick(child);
                                            AutoAddGroupFansUtils.this.initContactFirstInfo();
                                        } else {
                                            int unused4 = AutoAddGroupFansUtils.this.recordSearchNum;
                                            int unused5 = AutoAddGroupFansUtils.this.formerFriendNum;
                                            int unused6 = AutoAddGroupFansUtils.this.recordSendNum;
                                            new PerformClickUtils2().countDown2(AutoAddGroupFansUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                                public void end() {
                                                    LogUtils.log("WS_BABY_ChatroomInfoUI.5555____");
                                                    PerformClickUtils.performClick(child);
                                                    AutoAddGroupFansUtils.this.initContactFirstInfo();
                                                }

                                                public void surplus(int i) {
                                                    AutoAddGroupFansUtils.this.updateBottomText(true, i);
                                                }
                                            });
                                        }
                                    } else {
                                        AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                        AutoAddGroupFansUtils.this.initSeeRoomMember(0);
                                    }
                                }
                            }
                            if (!z2) {
                                LogUtils.log("WS_BABY_END_7");
                                BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                return;
                            }
                            return;
                        }
                        LogUtils.log("WS_BABY_END_8");
                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                    } else if (AutoAddGroupFansUtils.this.startInt > AutoAddGroupFansUtils.this.fans) {
                        String unused7 = AutoAddGroupFansUtils.this.sendResult = "您设置的加人起点高于群成员数量，导致加人结束。";
                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                    } else {
                        LogUtils.log("WS_BABY.SeeRoomMemberUI.9");
                        AutoAddGroupFansUtils.this.jumpStartIndex();
                    }
                } else if (AutoAddGroupFansUtils.this.startIndex >= accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY.SeeRoomMemberUI.15");
                    PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                    AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                    if (findViewIdList2 != null && findViewIdList2.size() > 0) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList2.get(0);
                        if (accessibilityNodeInfo2.getChildCount() > 0) {
                            AccessibilityNodeInfo child3 = accessibilityNodeInfo2.getChild(accessibilityNodeInfo2.getChildCount() - 1);
                            if (child3 != null) {
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= child3.getChildCount()) {
                                        break;
                                    }
                                    AccessibilityNodeInfo child4 = child3.getChild(i3);
                                    if (child4 != null && "android.widget.TextView".equals(child4.getClassName())) {
                                        z = true;
                                        break;
                                    }
                                    i3++;
                                }
                                if (z) {
                                    boolean unused8 = AutoAddGroupFansUtils.this.isScrollBottom0 = true;
                                    boolean unused9 = AutoAddGroupFansUtils.this.isScrollBottom = true;
                                } else {
                                    boolean unused10 = AutoAddGroupFansUtils.this.isScrollBottom0 = false;
                                    boolean unused11 = AutoAddGroupFansUtils.this.isScrollBottom = true;
                                }
                                LogUtils.log("WS_BAB_SCROLLBOTTOM_" + AutoAddGroupFansUtils.this.isScrollBottom);
                            }
                            z = false;
                            if (z) {
                            }
                            LogUtils.log("WS_BAB_SCROLLBOTTOM_" + AutoAddGroupFansUtils.this.isScrollBottom);
                        }
                    }
                    int unused12 = AutoAddGroupFansUtils.this.startIndex = 5;
                    AutoAddGroupFansUtils.this.initSeeRoomMember(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY.SeeRoomMemberUI.222222");
            }
        });
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
                } catch (Exception e) {
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
                } catch (Exception e2) {
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
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void jumpStartIndex() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Removed duplicated region for block: B:75:0x0215 A[Catch:{ Exception -> 0x0181 }] */
            /* JADX WARNING: Removed duplicated region for block: B:78:0x0242 A[Catch:{ Exception -> 0x0181 }] */
            public void find(int i) {
                boolean z;
                boolean z2 = true;
                try {
                    if (AutoAddGroupFansUtils.this.fans - AutoAddGroupFansUtils.this.recordSearchNum <= 0 || (AutoAddGroupFansUtils.this.recordSearchNum - AutoAddGroupFansUtils.this.startInt) + 1 == AutoAddGroupFansUtils.this.maxNum) {
                        LogUtils.log("WS_BABY_END_1");
                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                    }
                    if (AutoAddGroupFansUtils.this.lists == null) {
                        AutoAddGroupFansUtils.this.lists = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                    }
                    if (AutoAddGroupFansUtils.this.lists != null && AutoAddGroupFansUtils.this.lists.size() > 0 && AutoAddGroupFansUtils.this.lists.get(0).getChildCount() > 0) {
                        AccessibilityNodeInfo accessibilityNodeInfo = AutoAddGroupFansUtils.this.lists.get(0);
                        if (AutoAddGroupFansUtils.this.startIndex < accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(AutoAddGroupFansUtils.this.startIndex);
                            if (!AutoAddGroupFansUtils.this.isRemoveIndex && AutoAddGroupFansUtils.this.startInt > 1 && MyApplication.enforceable) {
                                if (child.getChildCount() >= 1) {
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 >= child.getChildCount()) {
                                            z2 = false;
                                            break;
                                        }
                                        AccessibilityNodeInfo child2 = child.getChild(i2);
                                        if (child2 == null || !"android.widget.TextView".equals(child2.getClassName())) {
                                            i2++;
                                        } else {
                                            if (child2.getText() != null) {
                                                String unused = AutoAddGroupFansUtils.this.nowName = child2.getText() + "";
                                            }
                                            if (!AutoAddGroupFansUtils.this.isScrollBottom0 || !AutoAddGroupFansUtils.this.fansRecord0.contains(AutoAddGroupFansUtils.this.nowName)) {
                                                AutoAddGroupFansUtils.this.fansRecord0.add(AutoAddGroupFansUtils.this.nowName);
                                                AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                                AutoAddGroupFansUtils.access$608(AutoAddGroupFansUtils.this);
                                                AutoAddGroupFansUtils.access$2108(AutoAddGroupFansUtils.this);
                                                BaseServiceUtils.updateBottomText(AutoAddGroupFansUtils.this.mMyManager, "从第 " + AutoAddGroupFansUtils.this.startInt + " 人开始,已经跳过 " + AutoAddGroupFansUtils.this.recordSearchNum + " 人。");
                                                if (AutoAddGroupFansUtils.this.recordSearchNum == AutoAddGroupFansUtils.this.startInt - 1) {
                                                    AutoAddGroupFansUtils.this.lists = null;
                                                    boolean unused2 = AutoAddGroupFansUtils.this.isRemoveIndex = true;
                                                    AutoAddGroupFansUtils.this.initSeeRoomMember(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
                                                } else {
                                                    AutoAddGroupFansUtils.this.jumpStartIndex();
                                                }
                                            } else {
                                                AutoAddGroupFansUtils.access$208(AutoAddGroupFansUtils.this);
                                                AutoAddGroupFansUtils.this.jumpStartIndex();
                                            }
                                        }
                                    }
                                    if (!z2) {
                                        BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                                        return;
                                    }
                                    return;
                                }
                                LogUtils.log("WS_BABY_END_2");
                                BaseServiceUtils.removeEndView(AutoAddGroupFansUtils.this.mMyManager);
                            }
                        } else if (AutoAddGroupFansUtils.this.startIndex >= accessibilityNodeInfo.getChildCount() && MyApplication.enforceable) {
                            PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                            AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            AutoAddGroupFansUtils.this.lists = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.search_list_id);
                            if (AutoAddGroupFansUtils.this.lists != null && AutoAddGroupFansUtils.this.lists.size() > 0) {
                                AccessibilityNodeInfo accessibilityNodeInfo2 = AutoAddGroupFansUtils.this.lists.get(0);
                                if (accessibilityNodeInfo2.getChildCount() > 0) {
                                    AccessibilityNodeInfo child3 = accessibilityNodeInfo2.getChild(accessibilityNodeInfo2.getChildCount() - 1);
                                    if (child3 != null && child3.getChildCount() > 0) {
                                        int i3 = 0;
                                        while (true) {
                                            if (i3 >= child3.getChildCount()) {
                                                z = false;
                                                break;
                                            }
                                            AccessibilityNodeInfo child4 = child3.getChild(i3);
                                            if (child4 != null && "android.widget.TextView".equals(child4.getClassName())) {
                                                break;
                                            }
                                            i3++;
                                        }
                                        if (z) {
                                            boolean unused3 = AutoAddGroupFansUtils.this.isScrollBottom0 = true;
                                        } else {
                                            boolean unused4 = AutoAddGroupFansUtils.this.isScrollBottom0 = false;
                                        }
                                        LogUtils.log("WS_BAB_SCROLLBOTTOM_" + AutoAddGroupFansUtils.this.isScrollBottom);
                                    }
                                    z = true;
                                    if (z) {
                                    }
                                    LogUtils.log("WS_BAB_SCROLLBOTTOM_" + AutoAddGroupFansUtils.this.isScrollBottom);
                                }
                            }
                            int unused5 = AutoAddGroupFansUtils.this.startIndex = 5;
                            AutoAddGroupFansUtils.this.jumpStartIndex();
                        }
                    }
                } catch (Exception e) {
                }
            }

            public void nuFind() {
            }
        });
    }

    public void jumpStartIndexFromSmallGroup() {
        new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                int i2;
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
                        try {
                            List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                            if (findViewIdList3 == null || findViewIdList3.size() <= 0 || (accessibilityNodeInfo = findViewIdList3.get(0)) == null || accessibilityNodeInfo.getChildCount() <= 0 || (child = accessibilityNodeInfo.getChild(0)) == null || child.getChildCount() <= 0) {
                                i2 = 5;
                                PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                                AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                int unused3 = AutoAddGroupFansUtils.this.startIndex = i2;
                                AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                            }
                            i2 = child.getChildCount();
                            try {
                                LogUtils.log("WS_BABY_ROW_NUM" + i2);
                            } catch (Exception e) {
                            }
                            PerformClickUtils.scroll(AutoAddGroupFansUtils.this.autoService, BaseServiceUtils.default_list_id);
                            AutoAddGroupFansUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            int unused4 = AutoAddGroupFansUtils.this.startIndex = i2;
                            AutoAddGroupFansUtils.this.jumpStartIndexFromSmallGroup();
                        } catch (Exception e2) {
                            i2 = 5;
                        }
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void middleViewDismiss() {
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

    public void remarkInfo() {
        LogUtils.log("WS_BABY.ContactInfoUI.22");
        new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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

            public void nuFind() {
            }
        });
    }

    public void seeRoomMember() {
        LogUtils.log("WS_BABY.SeeRoomMemberUI0");
        try {
            new PerformClickUtils2().check(this.autoService, "android:id/text1", 0, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
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

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBottomText(boolean z, int i) {
        try {
            int i2 = this.recordSearchNum;
            int i3 = this.formerFriendNum;
            int i4 = this.recordSendNum;
            int i5 = this.recordSearchNum - this.startInt > 0 ? this.recordSearchNum - this.startInt : 0;
            if ((this.fans - this.recordSearchNum) + 1 > 0) {
                int i6 = this.fans;
                int i7 = this.recordSearchNum;
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
                    updateBottomText(myWindowManager, "从第 " + this.startInt + " 个开始 , 已执行 " + i5 + " 人 。\n" + str + "，已是好友 " + this.formerFriendNum + " 人。倒计时 " + i + " 秒");
                } else {
                    MyWindowManager myWindowManager2 = this.mMyManager;
                    updateBottomText(myWindowManager2, "从第 " + this.startInt + " 个开始 , 已执行 " + i5 + " 人 。\n" + str + "，已是好友 " + this.formerFriendNum + " 人");
                }
            } else if (z) {
                MyWindowManager myWindowManager3 = this.mMyManager;
                updateBottomText(myWindowManager3, "从第 " + this.startInt + " 个开始 , 已执行 " + i5 + " 人 。" + str + "\n发送申请 " + this.sendApply + " 人，已是好友 " + this.formerFriendNum + " 人。倒计时 " + i + " 秒");
            } else {
                MyWindowManager myWindowManager4 = this.mMyManager;
                updateBottomText(myWindowManager4, "从第 " + this.startInt + " 个开始 , 已执行 " + i5 + " 人 。" + str + "\n发送申请 " + this.sendApply + " 人，已是好友 " + this.formerFriendNum + " 人");
            }
            SPUtils.put(MyApplication.getConText(), "group_inner_add_fans_num", Integer.valueOf(this.startInt == 1 ? this.recordSearchNum + 1 : this.recordSearchNum + 1));
        } catch (Exception e) {
        }
    }
}
