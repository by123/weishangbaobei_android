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

public class LabelRemarkLabelUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LabelRemarkLabelUtils instance;
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

    private LabelRemarkLabelUtils() {
    }

    static /* synthetic */ int access$008(LabelRemarkLabelUtils labelRemarkLabelUtils) {
        int i = labelRemarkLabelUtils.excFriendsNum;
        labelRemarkLabelUtils.excFriendsNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(LabelRemarkLabelUtils labelRemarkLabelUtils) {
        int i = labelRemarkLabelUtils.labelNum;
        labelRemarkLabelUtils.labelNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(LabelRemarkLabelUtils labelRemarkLabelUtils) {
        int i = labelRemarkLabelUtils.startIndex;
        labelRemarkLabelUtils.startIndex = i + 1;
        return i;
    }

    public static LabelRemarkLabelUtils getInstance() {
        instance = new LabelRemarkLabelUtils();
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
                List<AccessibilityNodeInfo> findViewIdList;
                List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.list_item_name_id);
                if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_LauncherUI.startIndex" + LabelRemarkLabelUtils.this.startIndex + "@" + findViewIdList2.size());
                    if (LabelRemarkLabelUtils.this.startIndex < findViewIdList2.size() && MyApplication.enforceable) {
                        final AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(LabelRemarkLabelUtils.this.startIndex);
                        if (accessibilityNodeInfo != null) {
                            if (accessibilityNodeInfo.getText() != null) {
                                String unused = LabelRemarkLabelUtils.this.nowName = accessibilityNodeInfo.getText() + "";
                                LogUtils.log("WS_BABY_LauncherUI.name=" + LabelRemarkLabelUtils.this.nowName);
                            }
                            if (LabelRemarkLabelUtils.this.beforeLists != null && LabelRemarkLabelUtils.this.beforeLists.size() > 30) {
                                for (int i2 = 0; i2 < 5; i2++) {
                                    LabelRemarkLabelUtils.this.beforeLists.remove(0);
                                }
                            }
                            if (LabelRemarkLabelUtils.this.isScrollBottom && LabelRemarkLabelUtils.this.beforeLists.contains(LabelRemarkLabelUtils.this.nowName)) {
                                LabelRemarkLabelUtils.access$808(LabelRemarkLabelUtils.this);
                                LogUtils.log("WS_BABY_LauncherUI.beforeLists.contains." + LabelRemarkLabelUtils.this.nowName);
                                LabelRemarkLabelUtils.this.checkZombieFan(str);
                            } else if ("微信团队".equals(LabelRemarkLabelUtils.this.nowName) || "文件传输助手".equals(LabelRemarkLabelUtils.this.nowName)) {
                                LabelRemarkLabelUtils.access$808(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                BaseServiceUtils.updateBottomText(LabelRemarkLabelUtils.this.mMyManager, "正在智能标签分组,已执行 " + LabelRemarkLabelUtils.this.excFriendsNum + " 人");
                                LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                                LabelRemarkLabelUtils.this.checkZombieFan(str);
                            } else {
                                LogUtils.log("WS_BABY_LauncherUI.check");
                                LabelRemarkLabelUtils.this.beforeLists.add(LabelRemarkLabelUtils.this.nowName);
                                List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.list_item_layout_id);
                                if (findViewIdList3 == null || LabelRemarkLabelUtils.this.startIndex >= findViewIdList3.size()) {
                                    LabelRemarkLabelUtils.access$808(LabelRemarkLabelUtils.this);
                                    LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                    BaseServiceUtils.updateBottomText(LabelRemarkLabelUtils.this.mMyManager, "正在智能标签分组,已执行 " + LabelRemarkLabelUtils.this.excFriendsNum + " 人");
                                    LogUtils.log("WS_BABY_LauncherUI.微信团队.");
                                    LabelRemarkLabelUtils.this.checkZombieFan(str);
                                    return;
                                }
                                final AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList3.get(LabelRemarkLabelUtils.this.startIndex);
                                LabelRemarkLabelUtils.access$808(LabelRemarkLabelUtils.this);
                                if (LabelRemarkLabelUtils.this.spaceTime <= 0 || LabelRemarkLabelUtils.this.excFriendsNum <= 0) {
                                    PerformClickUtils.performClick(accessibilityNodeInfo2);
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    LabelRemarkLabelUtils.this.initFirstContactInfoMSGUI();
                                    return;
                                }
                                new PerformClickUtils2().countDown2(LabelRemarkLabelUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                                    public void end() {
                                        MyWindowManager myWindowManager = LabelRemarkLabelUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "从第 " + LabelRemarkLabelUtils.this.startInt + " 个开始,已执行 " + LabelRemarkLabelUtils.this.excFriendsNum + " 人 ");
                                        PerformClickUtils.performClick(accessibilityNodeInfo2);
                                        PerformClickUtils.performClick(accessibilityNodeInfo);
                                        LabelRemarkLabelUtils.this.initFirstContactInfoMSGUI();
                                    }

                                    public void surplus(int i) {
                                        MyWindowManager myWindowManager = LabelRemarkLabelUtils.this.mMyManager;
                                        BaseServiceUtils.updateBottomText(myWindowManager, "已执行 " + LabelRemarkLabelUtils.this.excFriendsNum + " 人,延时等待 " + i + " 秒");
                                    }
                                });
                            }
                        }
                    } else if (LabelRemarkLabelUtils.this.startIndex >= findViewIdList2.size() && MyApplication.enforceable && (findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.grout_friend_list_id)) != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        findViewIdList.get(0).performAction(4096);
                        int unused2 = LabelRemarkLabelUtils.this.startIndex = 1;
                        LabelRemarkLabelUtils.this.sleep(MyApplication.SCROLL_SPEED);
                        if (LabelRemarkLabelUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(LabelRemarkLabelUtils.this.mMyManager);
                            return;
                        }
                        boolean unused3 = LabelRemarkLabelUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkLabelUtils.this.autoService, "位联系人");
                        LabelRemarkLabelUtils.this.checkZombieFan(str);
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
            showBottomView(this.mMyManager, "正在智能标签分组，请不要操作微信！");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
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
                    if (LabelRemarkLabelUtils.this.startInt <= 1 || !LabelRemarkLabelUtils.this.isJumpedStart || !MyApplication.enforceable) {
                        LogUtils.log("WS_BABY_LauncherUI_从头开始");
                        LabelRemarkLabelUtils.this.checkZombieFan(str);
                        return;
                    }
                    LogUtils.log("WS_BABY_LauncherUI_从" + LabelRemarkLabelUtils.this.startInt + "开始");
                    LabelRemarkLabelUtils.this.initCheck();
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
                PerformClickUtils.executedBackHome(this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                        LabelRemarkLabelUtils.access$308(LabelRemarkLabelUtils.this);
                        LabelRemarkLabelUtils.this.statisticsNum();
                        LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                    }
                });
            }
        } else {
            initExMore();
        }
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
                new PerformClickUtils2().check(this.autoService, list_item_name_id, 100, 50, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        int unused = LabelRemarkLabelUtils.this.startIndex = 1;
                        if (LabelRemarkLabelUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_LauncherUI.END");
                            BaseServiceUtils.removeEndView(LabelRemarkLabelUtils.this.mMyManager);
                            return;
                        }
                        boolean unused2 = LabelRemarkLabelUtils.this.isScrollBottom = PerformClickUtils.findNodeIsExistByText(LabelRemarkLabelUtils.this.autoService, "位联系人");
                        LabelRemarkLabelUtils.this.initCheck();
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
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        findViewIdList.get(0).performAction(16);
                        LabelRemarkLabelUtils.this.sleep(LabelRemarkLabelUtils.this.residenceTime);
                        String unused = LabelRemarkLabelUtils.this.labelName = LabelRemarkLabelUtils.this.model.getSingLabelStr();
                        LabelRemarkLabelUtils.this.reLabel = LabelRemarkLabelUtils.this.labelName;
                        if (LabelRemarkLabelUtils.this.isAutoGroup) {
                            LabelRemarkLabelUtils labelRemarkLabelUtils = LabelRemarkLabelUtils.this;
                            labelRemarkLabelUtils.reLabel = LabelRemarkLabelUtils.this.reLabel + "【" + LabelRemarkLabelUtils.this.labelIndex + "】";
                        }
                        if (!PerformClickUtils.findNodeIsExistByText(LabelRemarkLabelUtils.this.autoService, LabelRemarkLabelUtils.this.reLabel)) {
                            LogUtils.log("WS_BABY_ContactRemarkInfoModUI.2");
                            new PerformClickUtils2().checkNodeIds(LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.input_remark_label, BaseServiceUtils.input_remark_label_view_group, 100, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    if (i == 0) {
                                        PerformClickUtils.findViewIdAndClick(LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.input_remark_label);
                                    } else if (i == 1) {
                                        PerformClickUtils.findViewIdAndClick(LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.input_remark_label_view_group);
                                    }
                                    new PerformClickUtils2().checkString(LabelRemarkLabelUtils.this.autoService, "添加标签", BaseServiceUtils.executeSpeed + BaseServiceUtils.executeSpeedSetting, 100, 20, new PerformClickUtils2.OnCheckListener() {
                                        public void find(int i) {
                                            PerformClickUtils.findViewByIdAndPasteContent(LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.search_id, LabelRemarkLabelUtils.this.reLabel);
                                            LabelRemarkLabelUtils.this.sleep(100);
                                            PerformClickUtils.findTextAndClick(LabelRemarkLabelUtils.this.autoService, "保存");
                                            new PerformClickUtils2().checkString(LabelRemarkLabelUtils.this.autoService, "完成", BaseServiceUtils.executeSpeed + 350 + BaseServiceUtils.executeSpeedSetting, 200, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(LabelRemarkLabelUtils.this.autoService, "完成");
                                                    PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                                        public void find() {
                                                            LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                                            LabelRemarkLabelUtils.access$308(LabelRemarkLabelUtils.this);
                                                            LabelRemarkLabelUtils.this.statisticsNum();
                                                            LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
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
                        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.5");
                        PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.access$308(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.this.statisticsNum();
                                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
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
        this.labelNum = 0;
        this.labelIndex = 1;
        this.model = operationParameterModel;
        this.isAutoGroup = operationParameterModel.isAutoGroup();
        this.groupNumber = operationParameterModel.getGroupNumber();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.labelName = operationParameterModel.getSingLabelStr();
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
                PerformClickUtils.findViewIdAndClick(LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.right_more_id);
                LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                new PerformClickUtils2().checkNodeStringsHasSomeOne(LabelRemarkLabelUtils.this.autoService, "设置备注和标签", "设置备注及标签", BaseServiceUtils.executeSpeed + (BaseServiceUtils.executeSpeedSetting / 8), 100, 10, new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                        if (i == 0) {
                            List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(LabelRemarkLabelUtils.this.autoService, "设置备注和标签");
                            if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                PerformClickUtils.performClick(findViewStringList.get(0));
                                LabelRemarkLabelUtils.this.initContactRemarkInfoModUI();
                                return;
                            }
                            return;
                        }
                        List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(LabelRemarkLabelUtils.this.autoService, "设置备注及标签");
                        if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                            PerformClickUtils.performClick(findViewStringList2.get(0));
                            LabelRemarkLabelUtils.this.initContactRemarkInfoModUI();
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                    }
                });
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.initExMore.4");
                PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                    public void find() {
                        LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                        LabelRemarkLabelUtils.this.statisticsNum();
                        LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                    }
                });
            }
        });
    }

    public void initFirstContactInfoMSGUI() {
        this.labelName = this.model.getSingLabelStr();
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f4, code lost:
                r0 = com.wx.assistants.service_utils.LabelRemarkLabelUtils.access$100(r5.this$0).contains(r3[r2]);
             */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x0104  */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x0116  */
            public void find(int i) {
                int i2 = 0;
                if (LabelRemarkLabelUtils.this.model.getSendCardType() == 0) {
                    LabelRemarkLabelUtils.this.executeRemarkLabel();
                } else if (LabelRemarkLabelUtils.this.model.getSendCardType() == 1) {
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        LabelRemarkLabelUtils.this.executeRemarkLabel();
                    } else {
                        PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.this.statisticsNum();
                                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                            }
                        });
                    }
                } else if (LabelRemarkLabelUtils.this.model.getSendCardType() == 2) {
                    if (PerformClickUtils.findNodeIsExistByText(LabelRemarkLabelUtils.this.autoService, "昵称")) {
                        PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.this.statisticsNum();
                                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                            }
                        });
                    } else {
                        LabelRemarkLabelUtils.this.executeRemarkLabel();
                    }
                } else if (LabelRemarkLabelUtils.this.model.getSendCardType() == 4) {
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.0");
                    List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkLabelUtils.this.autoService, BaseServiceUtils.user_label);
                    if (findViewIdList2 == null || findViewIdList2.size() <= 0) {
                        LabelRemarkLabelUtils.this.executeRemarkLabel();
                        return;
                    }
                    System.out.println("WS_BABY_SFDJLFJFLKJFLDLFDJFLJSLDFJSLDF.1");
                    AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(0);
                    if (accessibilityNodeInfo.getText() == null || accessibilityNodeInfo.getText().toString() == null) {
                        PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.this.statisticsNum();
                                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
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
                                    PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                            LabelRemarkLabelUtils.this.statisticsNum();
                                            LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                                        }
                                    });
                                    return;
                                } else {
                                    LabelRemarkLabelUtils.this.executeRemarkLabel();
                                    return;
                                }
                            }
                            if (!z) {
                            }
                        }
                    } else if (LabelRemarkLabelUtils.this.nameLists.contains(str)) {
                        PerformClickUtils.executedBackHome(LabelRemarkLabelUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                                LabelRemarkLabelUtils.this.statisticsNum();
                                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
                            }
                        });
                    } else {
                        LabelRemarkLabelUtils.this.executeRemarkLabel();
                    }
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.performBackNoDeep(LabelRemarkLabelUtils.this.autoService);
                LabelRemarkLabelUtils.access$008(LabelRemarkLabelUtils.this);
                LabelRemarkLabelUtils.this.statisticsNum();
                LabelRemarkLabelUtils.this.executeMain(LabelRemarkLabelUtils.this.nameLists);
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
                setMiddleText(this.mMyManager, "智能标签分组结束", this.sendResult);
            } else if (this.isSearchResult) {
                MyWindowManager myWindowManager = this.mMyManager;
                setMiddleText(myWindowManager, "智能标签分组结束", "从第 " + this.startInt + " 个好友开始\n共执行 " + this.excFriendsNum + " 人 ");
            } else {
                setMiddleText(this.mMyManager, "智能标签分组结束", "没有找到好友，起始点可能超出好友范围！！！！");
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
        updateBottomText(myWindowManager, "正在智能标签分组,已执行 " + this.excFriendsNum + " 人");
        tjRemarkLabel();
    }

    public void tjRemarkLabel() {
        if (this.isAutoGroup && this.labelNum >= this.groupNumber) {
            this.labelIndex++;
            this.labelNum = 0;
        }
    }
}
