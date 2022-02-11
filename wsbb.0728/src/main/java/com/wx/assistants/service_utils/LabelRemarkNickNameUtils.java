package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class LabelRemarkNickNameUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LabelRemarkNickNameUtils instance;
    /* access modifiers changed from: private */
    public List<String> beforeLists = new ArrayList();
    /* access modifiers changed from: private */
    public int excFriendsNum = 0;
    private int groupNumber = SubsamplingScaleImageView.ORIENTATION_180;
    /* access modifiers changed from: private */
    public boolean isAutoGroup = false;
    private boolean isFirst = true;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    private boolean isSearchResult = true;
    private List<String> jumpStartLists = new ArrayList();
    private int maxNum = 5000;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public String nameLists = "";
    /* access modifiers changed from: private */
    public String nowName = "";
    String reRemark = "";
    /* access modifiers changed from: private */
    public int remarkIndex = 1;
    /* access modifiers changed from: private */
    public String remarkNickName = "";
    private int remarkNum = 0;
    /* access modifiers changed from: private */
    public int residenceTime = 0;
    private int scrollCount = 0;
    private String sendResult = "";
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startInt = 0;

    private LabelRemarkNickNameUtils() {
    }

    static /* synthetic */ int access$008(LabelRemarkNickNameUtils labelRemarkNickNameUtils) {
        int i = labelRemarkNickNameUtils.excFriendsNum;
        labelRemarkNickNameUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(LabelRemarkNickNameUtils labelRemarkNickNameUtils) {
        int i = labelRemarkNickNameUtils.remarkNum;
        labelRemarkNickNameUtils.remarkNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(LabelRemarkNickNameUtils labelRemarkNickNameUtils) {
        int i = labelRemarkNickNameUtils.startIndex;
        labelRemarkNickNameUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$910(LabelRemarkNickNameUtils labelRemarkNickNameUtils) {
        int i = labelRemarkNickNameUtils.startIndex;
        labelRemarkNickNameUtils.startIndex = i - 1;
        return i;
    }

    public static LabelRemarkNickNameUtils getInstance() {
        instance = new LabelRemarkNickNameUtils();
        return instance;
    }

    public void checkZombieFan(final String str) {
        if (this.excFriendsNum >= this.maxNum) {
            removeEndView(this.mMyManager);
            return;
        }
        this.remarkNickName = this.model.getRemarkPrefix();
        this.isJumpedStart = false;
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_item_name_id, list_item_layout_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                List<AccessibilityNodeInfo> findViewIdList;
                LogUtils.log("WS_BABY_LauncherUI.start");
                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_LauncherUI.startIndex" + LabelRemarkNickNameUtils.this.startIndex + "@" + findViewIdList2.size());
                    if (LabelRemarkNickNameUtils.this.startIndex < findViewIdList2.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(LabelRemarkNickNameUtils.this.startIndex);
                        final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList3.get(LabelRemarkNickNameUtils.this.startIndex);
                        if (accessibilityNodeInfo != null) {
                            if (accessibilityNodeInfo.getText() != null) {
                                String unused = LabelRemarkNickNameUtils.this.nowName = accessibilityNodeInfo.getText() + "";
                                LogUtils.log("WS_BABY_LauncherUI.name=" + LabelRemarkNickNameUtils.this.nowName);
                            }
                            if (LabelRemarkNickNameUtils.this.beforeLists != null && LabelRemarkNickNameUtils.this.beforeLists.size() > 30) {
                                for (int i2 = 0; i2 < 5; i2++) {
                                    LabelRemarkNickNameUtils.this.beforeLists.remove(0);
                                }
                            }
                            LabelRemarkNickNameUtils.access$908(LabelRemarkNickNameUtils.this);
                            LogUtils.log("WS_BABY_LauncherUI.startIndex." + LabelRemarkNickNameUtils.this.startIndex);
                            if (!LabelRemarkNickNameUtils.this.isScrollBottom || !LabelRemarkNickNameUtils.this.beforeLists.contains(LabelRemarkNickNameUtils.this.nowName)) {
                                if (LabelRemarkNickNameUtils.this.nowName.startsWith(LabelRemarkNickNameUtils.this.remarkNickName + "_")) {
                                    LabelRemarkNickNameUtils.this.checkZombieFan(str);
                                } else if ("微信团队".equals(LabelRemarkNickNameUtils.this.nowName) || "文件传输助手".equals(LabelRemarkNickNameUtils.this.nowName)) {
                                    LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                    BaseServiceUtils.updateBottomText(LabelRemarkNickNameUtils.this.mMyManager, "正在智能备注分组,已执行 " + LabelRemarkNickNameUtils.this.excFriendsNum + " 人");
                                    LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                                    LabelRemarkNickNameUtils.this.checkZombieFan(str);
                                } else {
                                    LogUtils.log("WS_BABY_LauncherUI.check");
                                    LabelRemarkNickNameUtils.this.beforeLists.add(LabelRemarkNickNameUtils.this.nowName);
                                    if (LabelRemarkNickNameUtils.this.spaceTime <= 0 || LabelRemarkNickNameUtils.this.excFriendsNum <= 0) {
                                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                        LabelRemarkNickNameUtils.this.initFirstContactInfoMSGUI();
                                        return;
                                    }
                                    new PerformClickUtils2().countDown2(LabelRemarkNickNameUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                        public void end() {
                                            MyWindowManager myWindowManager = LabelRemarkNickNameUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + LabelRemarkNickNameUtils.this.startInt + " 个开始,已执行 " + LabelRemarkNickNameUtils.this.excFriendsNum + " 人 ");
                                            PerformClickUtils.performClick(accessibilityNodeInfo2);
                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                            LabelRemarkNickNameUtils.this.initFirstContactInfoMSGUI();
                                        }

                                        public void surplus(int i) {
                                            MyWindowManager myWindowManager = LabelRemarkNickNameUtils.this.mMyManager;
                                            BaseServiceUtils.updateBottomText(myWindowManager, "已执行 " + LabelRemarkNickNameUtils.this.excFriendsNum + " 人,延时等待 " + i + " 秒");
                                        }
                                    });
                                }
                            } else {
                                LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + LabelRemarkNickNameUtils.this.nowName);
                                LabelRemarkNickNameUtils.this.checkZombieFan(str);
                            }
                        }
                    } else if (LabelRemarkNickNameUtils.this.startIndex >= findViewIdList2.size() && MyApplication.enforceable && (findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.grout_friend_list_id)) != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        findViewIdList.get(0).performAction(4096);
                        int unused2 = LabelRemarkNickNameUtils.this.startIndex = 1;
                        LabelRemarkNickNameUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        if (LabelRemarkNickNameUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(LabelRemarkNickNameUtils.this.mMyManager);
                            return;
                        }
                        boolean unused3 = LabelRemarkNickNameUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkNickNameUtils.this.autoService, "位联系人");
                        LabelRemarkNickNameUtils.this.checkZombieFan(str);
                    }
                }
            }

            public void nuFind() {
            }
        });
    }

    public void endViewDismiss() {
        try {
            this.mMyManager.stopAccessibilityService();
            this.mMyManager.removeBottomView();
            this.mMyManager.showMiddleView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endViewShow() {
        try {
            this.isFirst = true;
            showBottomView(this.mMyManager, "正在智能备注分组，请不要操作微信！");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain(final String str) {
        try {
            this.isSearchResult = true;
            LogUtils.log("WS_BABY_LauncherUI");
            if (this.isFirst && MyApplication.enforceable) {
                this.isFirst = false;
                this.isFirst = false;
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(this.autoService, "群聊");
                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(this.autoService, "标签");
                if (!findNodeIsExistByText && !findNodeIsExistByText2) {
                    LogUtils.log("WS_BABY_LauncherUI_从头开始000000000");
                    sleep((executeSpeedSetting / 8) + 400);
                }
            }
            new PerformClickUtils2().checkNodeIds(this.autoService, grout_friend_list_id, list_item_name_id, executeSpeedSetting + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (LabelRemarkNickNameUtils.this.startInt <= 1 || !LabelRemarkNickNameUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        LabelRemarkNickNameUtils.this.checkZombieFan(str);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + LabelRemarkNickNameUtils.this.startInt + "开始");
                    LabelRemarkNickNameUtils.this.initCheck();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeRemarkNickName() {
        this.remarkNickName = this.model.getRemarkPrefix();
        this.reRemark = this.remarkNickName;
        if (this.isAutoGroup) {
            this.reRemark = this.remarkNickName + "【" + this.remarkIndex + "】";
        }
        if (this.nowName != null) {
            if (this.nowName.startsWith(this.reRemark + "_") && MyApplication.enforceable) {
                this.remarkNum++;
                PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                        LabelRemarkNickNameUtils.this.statisticsNum();
                        LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                    }
                });
                return;
            }
        }
        initExMore();
    }

    public void initCheck() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(this.startIndex);
                if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                    this.nowName = accessibilityNodeInfo.getText() + "";
                }
                if (!this.isScrollBottom || !this.jumpStartLists.contains(this.nowName)) {
                    this.scrollCount++;
                }
                LogUtils.log("WS_BABY_JUMP_NUM@" + this.scrollCount + "@" + this.startInt);
                if (this.scrollCount == this.startInt) {
                    checkZombieFan(this.nameLists);
                    return;
                }
                this.jumpStartLists.add(this.nowName);
                this.startIndex++;
                updateBottomText(this.mMyManager, "从第 " + this.startInt + " 个开始，已跳过 " + this.scrollCount + " 个。");
                initCheck();
            } else if (this.startIndex >= findViewIdList.size() && MyApplication.enforceable) {
                new PerformClickUtils2().check(this.autoService, grout_friend_list_id, 10, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.grout_friend_list_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            findViewIdList.get(0).performAction(4096);
                            int unused = LabelRemarkNickNameUtils.this.startIndex = 1;
                            LabelRemarkNickNameUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            if (LabelRemarkNickNameUtils.this.isScrollBottom) {
                                LogUtils.log("WS_BABY_LauncherUI.END");
                                BaseServiceUtils.removeEndView(LabelRemarkNickNameUtils.this.mMyManager);
                                return;
                            }
                            boolean unused2 = LabelRemarkNickNameUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkNickNameUtils.this.autoService, "位联系人");
                            LabelRemarkNickNameUtils.this.initCheck();
                        }
                    }

                    public void nuFind() {
                    }
                });
            }
        }
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeed + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        findViewIdList.get(0).performAction(16);
                        LabelRemarkNickNameUtils.this.sleep(LabelRemarkNickNameUtils.this.residenceTime);
                        String unused = LabelRemarkNickNameUtils.this.remarkNickName = LabelRemarkNickNameUtils.this.model.getRemarkPrefix();
                        if (LabelRemarkNickNameUtils.this.remarkNickName != null && !"".equals(LabelRemarkNickNameUtils.this.remarkNickName)) {
                            LabelRemarkNickNameUtils.this.reRemark = LabelRemarkNickNameUtils.this.remarkNickName;
                            if (LabelRemarkNickNameUtils.this.isAutoGroup) {
                                LabelRemarkNickNameUtils labelRemarkNickNameUtils = LabelRemarkNickNameUtils.this;
                                labelRemarkNickNameUtils.reRemark = LabelRemarkNickNameUtils.this.reRemark + "【" + LabelRemarkNickNameUtils.this.remarkIndex + "】";
                            }
                            String access$700 = LabelRemarkNickNameUtils.this.nowName;
                            if (!access$700.startsWith(LabelRemarkNickNameUtils.this.reRemark + "_")) {
                                LabelRemarkNickNameUtils.access$808(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils labelRemarkNickNameUtils2 = LabelRemarkNickNameUtils.this;
                                labelRemarkNickNameUtils2.inputText(LabelRemarkNickNameUtils.this.reRemark + "_");
                            }
                            LabelRemarkNickNameUtils.this.sleep(LabelRemarkNickNameUtils.this.residenceTime);
                        }
                        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findViewIdList2.get(0));
                        }
                        PerformClickUtils.executedRemarkBackHome(LabelRemarkNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                            public void find(boolean z) {
                                if (z) {
                                    LabelRemarkNickNameUtils.access$910(LabelRemarkNickNameUtils.this);
                                }
                                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils.this.statisticsNum();
                                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                            }
                        });
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
        this.excFriendsNum = 0;
        this.beforeLists = new ArrayList();
        this.jumpStartLists = new ArrayList();
        this.startIndex = 0;
        this.residenceTime = 100;
        this.sendResult = "";
        this.nowName = "";
        this.isJumpedStart = true;
        this.isScrollBottom = false;
        this.isFirst = true;
        this.scrollCount = 0;
        this.isSearchResult = true;
        this.remarkNum = 0;
        this.remarkIndex = 1;
        this.model = operationParameterModel;
        this.isAutoGroup = operationParameterModel.isAutoGroup();
        this.groupNumber = operationParameterModel.getGroupNumber();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.remarkNickName = operationParameterModel.getRemarkPrefix();
        this.startInt = operationParameterModel.getStartIndex();
        this.nameLists = operationParameterModel.getTagNamesStr();
        this.maxNum = operationParameterModel.getMaxOperaNum();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initExMore() {
        LogUtils.log("WS_BABY_ContactInfoUI.initExMore.1");
        new PerformClickUtils2().check(this.autoService, right_more_id, 100, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.2");
                PerformClickUtils.findViewIdAndClick(LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.right_more_id);
                LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(LabelRemarkNickNameUtils.this.autoService, "设置备注和标签", "设置备注及标签", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 8), 100, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                        if (i == 0) {
                            List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(LabelRemarkNickNameUtils.this.autoService, "设置备注和标签");
                            if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                PerformClickUtils.performClick(findViewStringList.get(0));
                                LabelRemarkNickNameUtils.this.initContactRemarkInfoModUI();
                                return;
                            }
                            return;
                        }
                        List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(LabelRemarkNickNameUtils.this.autoService, "设置备注及标签");
                        if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                            PerformClickUtils.performClick(findViewStringList2.get(0));
                            LabelRemarkNickNameUtils.this.initContactRemarkInfoModUI();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.4");
                PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                        LabelRemarkNickNameUtils.this.statisticsNum();
                        LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                    }
                });
            }
        });
    }

    public void initFirstContactInfoMSGUI() {
        this.remarkNickName = this.model.getRemarkPrefix();
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f6, code lost:
                r0 = com.wx.assistants.service_utils.LabelRemarkNickNameUtils.access$100(r5.this$0).contains(r3[r2]);
             */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x0106  */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x0118  */
            public void find(int i) {
                int i2 = 0;
                if (LabelRemarkNickNameUtils.this.model.getSendCardType() == 0) {
                    LabelRemarkNickNameUtils.this.executeRemarkNickName();
                } else if (LabelRemarkNickNameUtils.this.model.getSendCardType() == 1) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        LabelRemarkNickNameUtils.this.executeRemarkNickName();
                    } else {
                        PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils.this.statisticsNum();
                                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                            }
                        });
                    }
                } else if (LabelRemarkNickNameUtils.this.model.getSendCardType() == 2) {
                    if (PerformClickUtils.findNodeIsExistByText(LabelRemarkNickNameUtils.this.autoService, "昵称")) {
                        PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils.this.statisticsNum();
                                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                            }
                        });
                    } else {
                        LabelRemarkNickNameUtils.this.executeRemarkNickName();
                    }
                } else if (LabelRemarkNickNameUtils.this.model.getSendCardType() == 4) {
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.0");
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkNickNameUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList2 == null || findViewIdList2.size() <= 0) {
                        LabelRemarkNickNameUtils.this.executeRemarkNickName();
                        return;
                    }
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.1");
                    AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(0);
                    if (accessibilityNodeInfo.getText() == null || accessibilityNodeInfo.getText().toString() == null) {
                        PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils.this.statisticsNum();
                                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                            }
                        });
                        return;
                    }
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.2");
                    String str = accessibilityNodeInfo.getText() + "";
                    if (str.contains("，")) {
                        System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.3");
                        if (str.contains("，")) {
                            String[] split = str.split("，");
                            boolean z = false;
                            while (true) {
                                int i3 = i2;
                                if (i3 < split.length && !z) {
                                    i2 = i3 + 1;
                                } else if (!z) {
                                    PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                            LabelRemarkNickNameUtils.this.statisticsNum();
                                            LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                                        }
                                    });
                                    return;
                                } else {
                                    LabelRemarkNickNameUtils.this.executeRemarkNickName();
                                    return;
                                }
                            }
                            if (!z) {
                            }
                        }
                    } else if (LabelRemarkNickNameUtils.this.nameLists.contains(str)) {
                        PerformClickUtils.executedBackHome(LabelRemarkNickNameUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                                LabelRemarkNickNameUtils.this.statisticsNum();
                                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
                            }
                        });
                    } else {
                        LabelRemarkNickNameUtils.this.executeRemarkNickName();
                    }
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.performBackNoDeep(LabelRemarkNickNameUtils.this.autoService);
                LabelRemarkNickNameUtils.access$008(LabelRemarkNickNameUtils.this);
                LabelRemarkNickNameUtils.this.statisticsNum();
                LabelRemarkNickNameUtils.this.executeMain(LabelRemarkNickNameUtils.this.nameLists);
            }
        });
    }

    public void inputText(String str) {
        AccessibilityNodeInfo findFocus;
        boolean z = true;
        try {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow != null && (findFocus = rootInActiveWindow.findFocus(1)) != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (findFocus == null) {
                        z = false;
                    }
                    if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str + findFocus.getText() + "");
                        findFocus.performAction(2097152, bundle);
                        return;
                    }
                    return;
                }
                CharSequence text = findFocus.getText();
                ((ClipboardManager) this.autoService.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str + (text == null ? "" : text)));
                if (Build.VERSION.SDK_INT >= 18) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str.length());
                    findFocus.performAction(1);
                    findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                    findFocus.performAction(32768);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        try {
            if (this.sendResult != null && !"".equals(this.sendResult)) {
                setMiddleText(this.mMyManager, "智能备注分组结束", this.sendResult);
            } else if (this.isSearchResult) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "智能备注分组结束", "从第 " + this.startInt + " 个好友开始\n共执行 " + this.excFriendsNum + " 人 ");
            } else {
                setMiddleText(this.mMyManager, "智能备注分组结束", "没有找到好友，起始点可能超出好友范围！！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void statisticsNum() {
        int i = this.startInt == 1 ? this.excFriendsNum + 1 : this.excFriendsNum + this.startInt;
        LogUtils.log("WS_BABY_LauncherUI.statisticsNum." + i);
        if (this.model.getSendCardType() == 0) {
            SPUtils.put(MyApplication.getConText(), "label_remark_num_all", Integer.valueOf(i));
        } else if (this.model.getSendCardType() == 1) {
            SPUtils.put(MyApplication.getConText(), "label_remark_num_un_label", Integer.valueOf(i));
        } else if (this.model.getSendCardType() == 2) {
            SPUtils.put(MyApplication.getConText(), "label_remark_num_un_remark", Integer.valueOf(i));
        } else if (this.model.getSendCardType() == 4) {
            SPUtils.put(MyApplication.getConText(), "label_remark_num_shield", Integer.valueOf(i));
        }
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "正在智能备注分组,已执行 " + this.excFriendsNum + " 人");
        tjRemarkLabel();
    }

    public void tjRemarkLabel() {
        if (this.isAutoGroup && this.remarkNum >= this.groupNumber) {
            this.remarkIndex++;
            this.remarkNum = 0;
        }
    }
}
