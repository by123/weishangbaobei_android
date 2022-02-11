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

public class LabelRemarkAllUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LabelRemarkAllUtils instance;
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
    List<AccessibilityNodeInfo> itemList0 = null;
    private List<String> jumpStartLists = new ArrayList();
    /* access modifiers changed from: private */
    public int labelIndex = 1;
    /* access modifiers changed from: private */
    public String labelName = "";
    private int labelNum = 0;
    private int maxNum = 5000;
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public String nameLists = "";
    /* access modifiers changed from: private */
    public String nowName = "";
    String reLabel = "";
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

    private LabelRemarkAllUtils() {
    }

    static /* synthetic */ int access$008(LabelRemarkAllUtils labelRemarkAllUtils) {
        int i = labelRemarkAllUtils.excFriendsNum;
        labelRemarkAllUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1008(LabelRemarkAllUtils labelRemarkAllUtils) {
        int i = labelRemarkAllUtils.remarkNum;
        labelRemarkAllUtils.remarkNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(LabelRemarkAllUtils labelRemarkAllUtils) {
        int i = labelRemarkAllUtils.startIndex;
        labelRemarkAllUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(LabelRemarkAllUtils labelRemarkAllUtils) {
        int i = labelRemarkAllUtils.labelNum;
        labelRemarkAllUtils.labelNum = i + 1;
        return i;
    }

    public static LabelRemarkAllUtils getInstance() {
        instance = new LabelRemarkAllUtils();
        return instance;
    }

    public void checkZombieFan(final String str) {
        if (this.excFriendsNum >= this.maxNum) {
            removeEndView(this.mMyManager);
            return;
        }
        this.isJumpedStart = false;
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_item_name_id, list_item_layout_id, 100, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                AccessibilityNodeInfo rootInActiveWindow;
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_LauncherUI.startIndex" + LabelRemarkAllUtils.this.startIndex + "@" + findViewIdList.size());
                    if (LabelRemarkAllUtils.this.startIndex < findViewIdList.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(LabelRemarkAllUtils.this.startIndex);
                        if (accessibilityNodeInfo != null) {
                            if (accessibilityNodeInfo.getText() != null) {
                                String unused = LabelRemarkAllUtils.this.nowName = accessibilityNodeInfo.getText() + "";
                                LogUtils.log("WS_BABY_LauncherUI.name=" + LabelRemarkAllUtils.this.nowName);
                            }
                            if (LabelRemarkAllUtils.this.beforeLists != null && LabelRemarkAllUtils.this.beforeLists.size() > 30) {
                                for (int i2 = 0; i2 < 5; i2++) {
                                    LabelRemarkAllUtils.this.beforeLists.remove(0);
                                }
                            }
                            if (LabelRemarkAllUtils.this.isScrollBottom && LabelRemarkAllUtils.this.beforeLists.contains(LabelRemarkAllUtils.this.nowName)) {
                                LabelRemarkAllUtils.access$1208(LabelRemarkAllUtils.this);
                                LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + LabelRemarkAllUtils.this.nowName);
                                LabelRemarkAllUtils.this.checkZombieFan(str);
                            } else if ("微信团队".equals(LabelRemarkAllUtils.this.nowName) || "文件传输助手".equals(LabelRemarkAllUtils.this.nowName)) {
                                LabelRemarkAllUtils.access$1208(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                BaseServiceUtils.updateBottomText(LabelRemarkAllUtils.this.mMyManager, "正在智能分组,已执行 " + LabelRemarkAllUtils.this.excFriendsNum + " 人");
                                LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                                LabelRemarkAllUtils.this.checkZombieFan(str);
                            } else {
                                LogUtils.log("WS_BABY_LauncherUI.check");
                                LabelRemarkAllUtils.this.beforeLists.add(LabelRemarkAllUtils.this.nowName);
                                final AccessibilityNodeInfo accessibilityNodeInfo2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.list_item_layout_id).get(LabelRemarkAllUtils.this.startIndex);
                                if (LabelRemarkAllUtils.this.spaceTime <= 0 || LabelRemarkAllUtils.this.excFriendsNum <= 0) {
                                    LabelRemarkAllUtils.access$1208(LabelRemarkAllUtils.this);
                                    LogUtils.log("WS_BABY_LauncherUI.check." + LabelRemarkAllUtils.this.startIndex);
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    LabelRemarkAllUtils.this.initFirstContactInfoMSGUI();
                                    return;
                                }
                                new PerformClickUtils2().countDown2(LabelRemarkAllUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                    public void end() {
                                        MyWindowManager myWindowManager = LabelRemarkAllUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + LabelRemarkAllUtils.this.startInt + " 个开始,已执行 " + LabelRemarkAllUtils.this.excFriendsNum + " 人 ");
                                        LabelRemarkAllUtils.access$1208(LabelRemarkAllUtils.this);
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("WS_BABY_LauncherUI.check.");
                                        sb.append(LabelRemarkAllUtils.this.startIndex);
                                        LogUtils.log(sb.toString());
                                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                        LabelRemarkAllUtils.this.initFirstContactInfoMSGUI();
                                    }

                                    public void surplus(int i) {
                                        MyWindowManager myWindowManager = LabelRemarkAllUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "已执行 " + LabelRemarkAllUtils.this.excFriendsNum + " 人,延时等待 " + i + " 秒");
                                    }
                                });
                            }
                        }
                    } else if (LabelRemarkAllUtils.this.startIndex >= findViewIdList.size() && MyApplication.enforceable && (rootInActiveWindow = LabelRemarkAllUtils.this.autoService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.grout_friend_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                        int unused2 = LabelRemarkAllUtils.this.startIndex = 1;
                        LabelRemarkAllUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        if (LabelRemarkAllUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(LabelRemarkAllUtils.this.mMyManager);
                            return;
                        }
                        boolean unused3 = LabelRemarkAllUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkAllUtils.this.autoService, "位联系人");
                        LabelRemarkAllUtils.this.checkZombieFan(str);
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
            showBottomView(this.mMyManager, "正在智能分组，请不要操作微信！");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
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
                    if (LabelRemarkAllUtils.this.startInt <= 1 || !LabelRemarkAllUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        LabelRemarkAllUtils.this.checkZombieFan(str);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + LabelRemarkAllUtils.this.startInt + "开始");
                    LabelRemarkAllUtils.this.initCheck();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeRemarkLabel() {
        this.labelName = this.model.getSingLabelStr();
        this.reLabel = this.labelName;
        if (this.isAutoGroup) {
            this.reLabel += "【" + this.labelIndex + "】";
        }
        if (PerformClickUtils.findNodeIsExistByText(this.autoService, this.reLabel)) {
            List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, user_label);
            if (findViewIdList == null || findViewIdList.size() <= 0) {
                initExMore();
            } else {
                PerformClickUtils.executedRemarkBackHome(this.autoService, 20, new PerformClickUtils.OnBackMainPageListener2() {
                    public void find(boolean z) {
                        LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                        LabelRemarkAllUtils.access$508(LabelRemarkAllUtils.this);
                        LabelRemarkAllUtils.this.statisticsNum();
                        LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                    }
                });
            }
        } else {
            initExMore();
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
                if (this.labelName == null || "".equals(this.labelName)) {
                    PerformClickUtils.executedRemarkBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                        public void find(boolean z) {
                            LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                            LabelRemarkAllUtils.this.statisticsNum();
                            LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                        }
                    });
                    return;
                } else {
                    executeRemarkLabel();
                    return;
                }
            }
        }
        initExMore();
    }

    public void initCheck() {
        LogUtils.log("WS_BABY_LauncherUI.start...");
        if (this.itemList0 == null) {
            this.itemList0 = PerformClickUtils.findViewIdList((AccessibilityService) this.autoService, list_item_name_id);
        }
        if (this.itemList0 != null && this.itemList0.size() > 0 && MyApplication.enforceable) {
            if (this.startIndex < this.itemList0.size() && MyApplication.enforceable) {
                AccessibilityNodeInfo accessibilityNodeInfo = this.itemList0.get(this.startIndex);
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
            } else if (this.startIndex >= this.itemList0.size() && MyApplication.enforceable) {
                this.itemList0 = null;
                PerformClickUtils.scroll(this.autoService, grout_friend_list_id);
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 150, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = LabelRemarkAllUtils.this.startIndex = 1;
                        if (LabelRemarkAllUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(LabelRemarkAllUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = LabelRemarkAllUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkAllUtils.this.autoService, "位联系人");
                        LabelRemarkAllUtils.this.initCheck();
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
                    boolean z;
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.11");
                        findViewIdList.get(0).performAction(16);
                        LabelRemarkAllUtils.this.sleep(LabelRemarkAllUtils.this.residenceTime);
                        String unused = LabelRemarkAllUtils.this.remarkNickName = LabelRemarkAllUtils.this.model.getRemarkPrefix();
                        if (LabelRemarkAllUtils.this.remarkNickName == null || "".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                            z = false;
                        } else {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.111");
                            LabelRemarkAllUtils.this.reRemark = LabelRemarkAllUtils.this.remarkNickName;
                            if (LabelRemarkAllUtils.this.isAutoGroup) {
                                LabelRemarkAllUtils.this.reRemark = LabelRemarkAllUtils.this.reRemark + "【" + LabelRemarkAllUtils.this.remarkIndex + "】";
                            }
                            z = !LabelRemarkAllUtils.this.nowName.startsWith(LabelRemarkAllUtils.this.reRemark + "_");
                            if (z) {
                                LabelRemarkAllUtils.access$1008(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.this.inputText(LabelRemarkAllUtils.this.reRemark + "_");
                            }
                            LabelRemarkAllUtils.this.sleep(LabelRemarkAllUtils.this.residenceTime);
                        }
                        String unused2 = LabelRemarkAllUtils.this.labelName = LabelRemarkAllUtils.this.model.getSingLabelStr();
                        if (LabelRemarkAllUtils.this.labelName == null || "".equals(LabelRemarkAllUtils.this.labelName)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.6");
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findViewIdList2.get(0));
                            }
                            PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                                public void find(boolean z) {
                                    LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                    LabelRemarkAllUtils.this.statisticsNum();
                                    LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1111");
                        LabelRemarkAllUtils.this.reLabel = LabelRemarkAllUtils.this.labelName;
                        if (LabelRemarkAllUtils.this.isAutoGroup) {
                            LabelRemarkAllUtils.this.reLabel = LabelRemarkAllUtils.this.reLabel + "【" + LabelRemarkAllUtils.this.labelIndex + "】";
                        }
                        if (!PerformClickUtils.findNodeIsExistByText(LabelRemarkAllUtils.this.autoService, LabelRemarkAllUtils.this.reLabel)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                            new PerformClickUtils2().checkNodeIds(LabelRemarkAllUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(LabelRemarkAllUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(LabelRemarkAllUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(LabelRemarkAllUtils.this.autoService, "添加标签", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findViewByIdAndPasteContent(LabelRemarkAllUtils.this.autoService, BaseServiceUtils.search_id, LabelRemarkAllUtils.this.reLabel);
                                            LabelRemarkAllUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(LabelRemarkAllUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(LabelRemarkAllUtils.this.autoService, "完成", BaseServiceUtils.executeSpeed + 350 + BaseServiceUtils.executeSpeedSetting, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.22");
                                                    PerformClickUtils.findTextAndClick(LabelRemarkAllUtils.this.autoService, "完成");
                                                    PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                                                        public void find(boolean z) {
                                                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.222");
                                                            LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                                            LabelRemarkAllUtils.access$508(LabelRemarkAllUtils.this);
                                                            LabelRemarkAllUtils.this.statisticsNum();
                                                            LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                                        }
                                                    });
                                                }

                                                public void nuFind() {
                                                }
                                            });
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
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.3");
                        if (z) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.4");
                            List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.complete_id);
                            if (findViewIdList3 != null && findViewIdList3.size() > 0 && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findViewIdList3.get(0));
                            }
                            PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                                public void find(boolean z) {
                                    LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                    LabelRemarkAllUtils.access$508(LabelRemarkAllUtils.this);
                                    LabelRemarkAllUtils.this.statisticsNum();
                                    LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                }
                            });
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                        PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener2() {
                            public void find(boolean z) {
                                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.access$508(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.this.statisticsNum();
                                LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
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
        this.labelNum = 0;
        this.labelIndex = 1;
        this.model = operationParameterModel;
        this.isAutoGroup = operationParameterModel.isAutoGroup();
        this.groupNumber = operationParameterModel.getGroupNumber();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.labelName = operationParameterModel.getSingLabelStr();
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
                PerformClickUtils.findViewIdAndClick(LabelRemarkAllUtils.this.autoService, BaseServiceUtils.right_more_id);
                LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(LabelRemarkAllUtils.this.autoService, "设置备注和标签", "设置备注及标签", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 8), 100, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                        if (i == 0) {
                            List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(LabelRemarkAllUtils.this.autoService, "设置备注和标签");
                            if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                PerformClickUtils.performClick(findViewStringList.get(0));
                                LabelRemarkAllUtils.this.initContactRemarkInfoModUI();
                                return;
                            }
                            return;
                        }
                        List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(LabelRemarkAllUtils.this.autoService, "设置备注及标签");
                        if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                            PerformClickUtils.performClick(findViewStringList2.get(0));
                            LabelRemarkAllUtils.this.initContactRemarkInfoModUI();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.4");
                PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener2() {
                    public void find(boolean z) {
                        LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                        LabelRemarkAllUtils.this.statisticsNum();
                        LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                    }
                });
            }
        });
    }

    public void initFirstContactInfoMSGUI() {
        this.labelName = this.model.getSingLabelStr();
        this.remarkNickName = this.model.getRemarkPrefix();
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:61:0x017c, code lost:
                r0 = com.wx.assistants.service_utils.LabelRemarkAllUtils.access$100(r5.this$0).contains(r3[r2]);
             */
            /* JADX WARNING: Removed duplicated region for block: B:64:0x018c  */
            /* JADX WARNING: Removed duplicated region for block: B:66:0x019e  */
            public void find(int i) {
                int i2 = 0;
                if (LabelRemarkAllUtils.this.model.getSendCardType() == 0) {
                    if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                        LabelRemarkAllUtils.this.executeRemarkNickName();
                    } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                        LabelRemarkAllUtils.this.executeRemarkLabel();
                    }
                } else if (LabelRemarkAllUtils.this.model.getSendCardType() == 1) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList != null && findViewIdList.size() > 0) {
                        PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                            public void find(boolean z) {
                                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.this.statisticsNum();
                                LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                            }
                        });
                    } else if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                        LabelRemarkAllUtils.this.executeRemarkNickName();
                    } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                        LabelRemarkAllUtils.this.executeRemarkLabel();
                    }
                } else if (LabelRemarkAllUtils.this.model.getSendCardType() == 2) {
                    if (PerformClickUtils.findNodeIsExistByText(LabelRemarkAllUtils.this.autoService, "昵称")) {
                        PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                            public void find(boolean z) {
                                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                LabelRemarkAllUtils.this.statisticsNum();
                                LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                            }
                        });
                    } else if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                        LabelRemarkAllUtils.this.executeRemarkNickName();
                    } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                        LabelRemarkAllUtils.this.executeRemarkLabel();
                    }
                } else if (LabelRemarkAllUtils.this.model.getSendCardType() == 4) {
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.0");
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkAllUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList2 != null && findViewIdList2.size() > 0) {
                        System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.1");
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(0);
                        if (accessibilityNodeInfo.getText() == null || accessibilityNodeInfo.getText() == null) {
                            PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                                public void find(boolean z) {
                                    LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                    LabelRemarkAllUtils.this.statisticsNum();
                                    LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                }
                            });
                            return;
                        }
                        System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.2");
                        String charSequence = accessibilityNodeInfo.getText().toString();
                        if (charSequence.contains("，")) {
                            System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.3");
                            if (charSequence.contains("，")) {
                                String[] split = charSequence.split("，");
                                boolean z = false;
                                while (true) {
                                    int i3 = i2;
                                    if (i3 < split.length && !z) {
                                        i2 = i3 + 1;
                                    } else if (!z) {
                                        PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener2() {
                                            public void find(boolean z) {
                                                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                                LabelRemarkAllUtils.this.statisticsNum();
                                                LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                            }
                                        });
                                        return;
                                    } else if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                                        LabelRemarkAllUtils.this.executeRemarkNickName();
                                        return;
                                    } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                                        LabelRemarkAllUtils.this.executeRemarkLabel();
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                if (!z) {
                                }
                            }
                        } else if (LabelRemarkAllUtils.this.nameLists.contains(charSequence)) {
                            PerformClickUtils.executedRemarkBackHome(LabelRemarkAllUtils.this.autoService, 20, new PerformClickUtils.OnBackMainPageListener2() {
                                public void find(boolean z) {
                                    LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                                    LabelRemarkAllUtils.this.statisticsNum();
                                    LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
                                }
                            });
                        } else if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                            LabelRemarkAllUtils.this.executeRemarkNickName();
                        } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                            LabelRemarkAllUtils.this.executeRemarkLabel();
                        }
                    } else if (LabelRemarkAllUtils.this.remarkNickName != null && !"".equals(LabelRemarkAllUtils.this.remarkNickName)) {
                        LabelRemarkAllUtils.this.executeRemarkNickName();
                    } else if (LabelRemarkAllUtils.this.labelName != null && !"".equals(LabelRemarkAllUtils.this.labelName)) {
                        LabelRemarkAllUtils.this.executeRemarkLabel();
                    }
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.performBackNoDeep(LabelRemarkAllUtils.this.autoService);
                LabelRemarkAllUtils.access$008(LabelRemarkAllUtils.this);
                LabelRemarkAllUtils.this.statisticsNum();
                LabelRemarkAllUtils.this.executeMain(LabelRemarkAllUtils.this.nameLists);
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
                setMiddleText(this.mMyManager, "智能分组结束", this.sendResult);
            } else if (this.isSearchResult) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "智能分组结束", "从第 " + this.startInt + " 个好友开始\n共执行 " + this.excFriendsNum + " 人 ");
            } else {
                setMiddleText(this.mMyManager, "智能分组结束", "没有找到好友，起始点可能超出好友范围！！！！");
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
        updateBottomText(myWindowManager, "正在智能分组,已执行 " + this.excFriendsNum + " 人");
        tjRemarkLabel();
    }

    public void tjRemarkLabel() {
        if (this.isAutoGroup) {
            if (this.remarkNum >= this.groupNumber) {
                this.remarkIndex++;
                this.remarkNum = 0;
            }
            if (this.labelNum >= this.groupNumber) {
                this.labelIndex++;
                this.labelNum = 0;
            }
        }
    }
}
