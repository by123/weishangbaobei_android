package com.wx.assistants.service_utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.LinkedHashSet;
import java.util.List;

public class LabelRemarkTagUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static LabelRemarkTagUtils instance;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> deleteTags = null;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> fansRecord = null;
    private int groupNumber = SubsamplingScaleImageView.ORIENTATION_180;
    /* access modifiers changed from: private */
    public boolean isAutoGroup = false;
    boolean isJumpExecute = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastTagName = "";
    /* access modifiers changed from: private */
    public OperationParameterModel model;
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public String nowNameNum = "";
    /* access modifiers changed from: private */
    public String nowNickName = "";
    /* access modifiers changed from: private */
    public String nowTag = "";
    String reRemark;
    private int remarkFriendNum = 0;
    /* access modifiers changed from: private */
    public int remarkIndex = 1;
    /* access modifiers changed from: private */
    public String remarkNickName;
    private int remarkNum = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndex2 = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$108(LabelRemarkTagUtils labelRemarkTagUtils) {
        int i = labelRemarkTagUtils.startIndex;
        labelRemarkTagUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1108(LabelRemarkTagUtils labelRemarkTagUtils) {
        int i = labelRemarkTagUtils.remarkFriendNum;
        labelRemarkTagUtils.remarkFriendNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1608(LabelRemarkTagUtils labelRemarkTagUtils) {
        int i = labelRemarkTagUtils.remarkNum;
        labelRemarkTagUtils.remarkNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$708(LabelRemarkTagUtils labelRemarkTagUtils) {
        int i = labelRemarkTagUtils.startIndex2;
        labelRemarkTagUtils.startIndex2 = i + 1;
        return i;
    }

    private LabelRemarkTagUtils() {
    }

    public static LabelRemarkTagUtils getInstance() {
        instance = new LabelRemarkTagUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        System.out.println("WS_BABY_initData");
        this.startIndex = 0;
        this.startIndex2 = 0;
        this.remarkFriendNum = 0;
        this.lastTagName = "";
        this.nowTag = "";
        this.nowNameNum = "0";
        this.nowName = "";
        this.nowNickName = "";
        this.isWhile = true;
        this.isScrollBottom = false;
        this.remarkNum = 0;
        this.remarkIndex = 1;
        this.model = operationParameterModel;
        this.isAutoGroup = operationParameterModel.isAutoGroup();
        this.groupNumber = operationParameterModel.getGroupNumber();
        this.fansRecord = new LinkedHashSet<>();
        this.deleteTags = operationParameterModel.getTagListNames();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.remarkNickName = operationParameterModel.getRemarkPrefix();
    }

    public void initContactLabelManagerUI() {
        LogUtils.log("WS_BABY_ContactLabelManagerUI.1");
        try {
            LogUtils.log("WS_BABY.ContactLabelManagerUI.2");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, tag_list_item_num_id, tag_list_item_name_id, executeSpeedSetting + executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    String str;
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.3");
                    while (LabelRemarkTagUtils.this.isWhile && MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.tag_list_id);
                        if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactLabelManagerUI.4");
                            List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.tag_list_item_name_id);
                            List<AccessibilityNodeInfo> findViewIdList3 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.tag_list_item_num_id);
                            if (findViewIdList2 != null && !findViewIdList2.isEmpty() && MyApplication.enforceable) {
                                if (LabelRemarkTagUtils.this.startIndex < findViewIdList2.size() && MyApplication.enforceable) {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList2.get(LabelRemarkTagUtils.this.startIndex);
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findViewIdList3.get(LabelRemarkTagUtils.this.startIndex);
                                    try {
                                        String unused = LabelRemarkTagUtils.this.nowTag = accessibilityNodeInfo.getText() + "";
                                        String unused2 = LabelRemarkTagUtils.this.nowNameNum = accessibilityNodeInfo2.getText() + "";
                                    } catch (Exception unused3) {
                                    }
                                    if (!LabelRemarkTagUtils.this.deleteTags.contains(LabelRemarkTagUtils.this.nowTag)) {
                                        LabelRemarkTagUtils.access$108(LabelRemarkTagUtils.this);
                                    } else if (!"(0)".equals(LabelRemarkTagUtils.this.nowNameNum)) {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.6");
                                        LabelRemarkTagUtils.this.deleteTags.remove(LabelRemarkTagUtils.this.nowTag);
                                        PerformClickUtils.performClick(findViewIdList2.get(LabelRemarkTagUtils.this.startIndex));
                                        int unused4 = LabelRemarkTagUtils.this.startIndex = 0;
                                        String unused5 = LabelRemarkTagUtils.this.lastTagName = "";
                                        LabelRemarkTagUtils.this.initContactLabelEditUI();
                                        return;
                                    } else {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.7");
                                        LabelRemarkTagUtils.access$108(LabelRemarkTagUtils.this);
                                    }
                                } else if (LabelRemarkTagUtils.this.startIndex >= findViewIdList2.size() && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.ContactLabelManagerUI.8");
                                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(findViewIdList.get(0));
                                        LabelRemarkTagUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        List<AccessibilityNodeInfo> findViewIdList4 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.tag_list_item_name_id);
                                        if (findViewIdList4 != null && findViewIdList4.size() > 0 && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo3 = findViewIdList4.get(findViewIdList4.size() - 1);
                                            try {
                                                str = accessibilityNodeInfo3.getText() + "";
                                            } catch (Exception unused6) {
                                                str = "";
                                            }
                                            if (str.equals(LabelRemarkTagUtils.this.lastTagName)) {
                                                boolean unused7 = LabelRemarkTagUtils.this.isWhile = false;
                                                BaseServiceUtils.removeEndView(LabelRemarkTagUtils.this.mMyManager);
                                            } else {
                                                String unused8 = LabelRemarkTagUtils.this.lastTagName = str;
                                            }
                                            int unused9 = LabelRemarkTagUtils.this.startIndex = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.unfind");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactLabelEditUI() {
        try {
            LogUtils.log("WS_BABY.ContactLabelEditUI.7");
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, this.isJumpExecute ? 100 : CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY.ContactLabelEditUI.8");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        LogUtils.log("WS_BAB_ContactLabelEditUI.100");
                        return;
                    }
                    LogUtils.log("WS_BABY.ContactLabelEditUI.9");
                    if (LabelRemarkTagUtils.this.nowNameNum != null) {
                        String access$300 = LabelRemarkTagUtils.this.nowNameNum;
                        if (access$300.equals(findViewIdList.size() + "")) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.10");
                            boolean unused = LabelRemarkTagUtils.this.isScrollBottom = true;
                        }
                    }
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "删除标签");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "添加成员");
                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "删除成员");
                    if (findNodeIsExistByText || findNodeIsExistByText2 || findNodeIsExistByText3) {
                        LogUtils.log("WS_BABY_ContactLabelEditUI.11");
                        boolean unused2 = LabelRemarkTagUtils.this.isScrollBottom = true;
                    }
                    LogUtils.log("WS_BABY_ContactLabelEditUI.22..startIndex." + LabelRemarkTagUtils.this.startIndex2 + ListUtils.DEFAULT_JOIN_SEPARATOR + findViewIdList.size());
                    if (LabelRemarkTagUtils.this.startIndex2 < findViewIdList.size() && MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(LabelRemarkTagUtils.this.startIndex2);
                        LogUtils.log("WS_BABY_ContactLabelEditUI.33");
                        if (accessibilityNodeInfo == null) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.44");
                            return;
                        }
                        LabelRemarkTagUtils labelRemarkTagUtils = LabelRemarkTagUtils.this;
                        String unused3 = labelRemarkTagUtils.nowNickName = accessibilityNodeInfo.getText() + "";
                        LabelRemarkTagUtils labelRemarkTagUtils2 = LabelRemarkTagUtils.this;
                        String unused4 = labelRemarkTagUtils2.nowName = accessibilityNodeInfo.getText() + "";
                        Rect rect = new Rect();
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        LabelRemarkTagUtils labelRemarkTagUtils3 = LabelRemarkTagUtils.this;
                        String unused5 = labelRemarkTagUtils3.nowName = LabelRemarkTagUtils.this.nowName + rect.toString();
                        if (!LabelRemarkTagUtils.this.isScrollBottom || !LabelRemarkTagUtils.this.fansRecord.contains(LabelRemarkTagUtils.this.nowName)) {
                            LabelRemarkTagUtils.this.isJumpExecute = false;
                            LabelRemarkTagUtils.this.fansRecord.add(LabelRemarkTagUtils.this.nowName);
                            LabelRemarkTagUtils.access$708(LabelRemarkTagUtils.this);
                            LogUtils.log("WS_BABY_ContactLabelEditUI.66");
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            LabelRemarkTagUtils.this.initContactInfoUIByTag();
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactLabelEditUI.55");
                        LabelRemarkTagUtils.access$708(LabelRemarkTagUtils.this);
                        LabelRemarkTagUtils.this.isJumpExecute = true;
                        LabelRemarkTagUtils.this.initContactLabelEditUI();
                    } else if (LabelRemarkTagUtils.this.startIndex2 >= findViewIdList.size() && MyApplication.enforceable) {
                        if (!LabelRemarkTagUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.88");
                            PerformClickUtils.scroll(LabelRemarkTagUtils.this.autoService, BaseServiceUtils.default_list_id);
                            LabelRemarkTagUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "删除标签");
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "添加成员");
                            boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(LabelRemarkTagUtils.this.autoService, "删除成员");
                            if (findNodeIsExistByText4 || findNodeIsExistByText5 || findNodeIsExistByText6) {
                                LogUtils.log("WS_BABY_ContactLabelEditUI.99");
                                boolean unused6 = LabelRemarkTagUtils.this.isScrollBottom = true;
                            }
                            int unused7 = LabelRemarkTagUtils.this.startIndex2 = 5;
                            LabelRemarkTagUtils.this.initContactLabelEditUI();
                        } else if (LabelRemarkTagUtils.this.deleteTags == null || LabelRemarkTagUtils.this.deleteTags.size() <= 0) {
                            BaseServiceUtils.removeEndView(LabelRemarkTagUtils.this.mMyManager);
                        } else {
                            MyWindowManager myWindowManager = LabelRemarkTagUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已备注【 " + LabelRemarkTagUtils.this.nowTag + " 】中的全部好友");
                            LogUtils.log("WS_BABY_ContactLabelEditUI.77");
                            PerformClickUtils.performBack(LabelRemarkTagUtils.this.autoService);
                            LabelRemarkTagUtils.this.sleep(600);
                            int unused8 = LabelRemarkTagUtils.this.startIndex2 = 0;
                            LabelRemarkTagUtils.this.initContactLabelManagerUI();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactInfoUIByTag() {
        new PerformClickUtils2().checkNodeIdOrStringAll(this.autoService, msg_layout, "发消息", executeSpeed + executeSpeedSetting, 100, 30, new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                LabelRemarkTagUtils.access$1108(LabelRemarkTagUtils.this);
                String unused = LabelRemarkTagUtils.this.remarkNickName = LabelRemarkTagUtils.this.model.getRemarkPrefix();
                LabelRemarkTagUtils.this.reRemark = LabelRemarkTagUtils.this.remarkNickName;
                if (LabelRemarkTagUtils.this.isAutoGroup) {
                    LabelRemarkTagUtils labelRemarkTagUtils = LabelRemarkTagUtils.this;
                    labelRemarkTagUtils.reRemark = LabelRemarkTagUtils.this.reRemark + "【" + LabelRemarkTagUtils.this.remarkIndex + "】";
                }
                if (LabelRemarkTagUtils.this.nowNickName != null) {
                    String access$800 = LabelRemarkTagUtils.this.nowNickName;
                    if (access$800.startsWith(LabelRemarkTagUtils.this.reRemark + "_")) {
                        PerformClickUtils.executedBackEditLabel(LabelRemarkTagUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkTagUtils.access$1608(LabelRemarkTagUtils.this);
                                LabelRemarkTagUtils.this.statisticsNum();
                                LabelRemarkTagUtils.this.initContactLabelEditUI();
                            }
                        });
                        return;
                    }
                }
                if (LabelRemarkTagUtils.this.remarkNickName != null && !"".equals(LabelRemarkTagUtils.this.remarkNickName)) {
                    new PerformClickUtils2().check(LabelRemarkTagUtils.this.autoService, BaseServiceUtils.right_more_id, 50, 100, 10, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(LabelRemarkTagUtils.this.autoService, BaseServiceUtils.right_more_id);
                            LogUtils.log("WS_BABY_ContactInfoUI.10.备注");
                            new PerformClickUtils2().checkNodeStringsHasSomeOne(LabelRemarkTagUtils.this.autoService, "设置备注和标签", "设置备注及标签", (BaseServiceUtils.executeSpeedSetting / 8) + BaseServiceUtils.executeSpeed, 100, 10, new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注00");
                                    if (i == 0) {
                                        List<AccessibilityNodeInfo> findViewStringList = PerformClickUtils.findViewStringList(LabelRemarkTagUtils.this.autoService, "设置备注和标签");
                                        if (findViewStringList != null && findViewStringList.size() > 0 && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_ContactInfoUI.11.设置备注和标签");
                                            PerformClickUtils.performClick(findViewStringList.get(0));
                                            LabelRemarkTagUtils.this.initContactRemarkInfoModUI();
                                            return;
                                        }
                                        return;
                                    }
                                    List<AccessibilityNodeInfo> findViewStringList2 = PerformClickUtils.findViewStringList(LabelRemarkTagUtils.this.autoService, "设置备注及标签");
                                    if (findViewStringList2 != null && findViewStringList2.size() > 0 && MyApplication.enforceable) {
                                        LogUtils.log("WS_BABY_ContactInfoUI.12.设置备注及标签");
                                        PerformClickUtils.performClick(findViewStringList2.get(0));
                                        LabelRemarkTagUtils.this.initContactRemarkInfoModUI();
                                    }
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_ContactInfoUI.10.备注11");
                                }
                            });
                        }

                        public void nuFind() {
                            PerformClickUtils.executedBackEditLabel(LabelRemarkTagUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    LabelRemarkTagUtils.this.statisticsNum();
                                    LabelRemarkTagUtils.this.initContactLabelEditUI();
                                }
                            });
                        }
                    });
                }
            }

            public void nuFind() {
                LogUtils.log("WS_BABY_ContactInfoUI.12.Back");
                PerformClickUtils.performBackNoDeep(LabelRemarkTagUtils.this.autoService);
                LabelRemarkTagUtils.this.initContactLabelEditUI();
            }
        });
    }

    public void initContactRemarkInfoModUI() {
        LogUtils.log("WS_BABY_ContactRemarkInfoModUI.0");
        try {
            new PerformClickUtils2().check(this.autoService, remark_edit_id, executeSpeedSetting + executeSpeed, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_ContactRemarkInfoModUI.1");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.remark_edit_id);
                    if (findViewIdList != null && findViewIdList.size() > 0 && MyApplication.enforceable) {
                        findViewIdList.get(0).performAction(16);
                        LabelRemarkTagUtils.this.sleep(50);
                        String unused = LabelRemarkTagUtils.this.remarkNickName = LabelRemarkTagUtils.this.model.getRemarkPrefix();
                        LabelRemarkTagUtils.this.reRemark = LabelRemarkTagUtils.this.remarkNickName;
                        if (LabelRemarkTagUtils.this.isAutoGroup) {
                            LabelRemarkTagUtils labelRemarkTagUtils = LabelRemarkTagUtils.this;
                            labelRemarkTagUtils.reRemark = LabelRemarkTagUtils.this.reRemark + "【" + LabelRemarkTagUtils.this.remarkIndex + "】";
                        }
                        String access$800 = LabelRemarkTagUtils.this.nowNickName;
                        if (!access$800.startsWith(LabelRemarkTagUtils.this.reRemark + "_")) {
                            LabelRemarkTagUtils labelRemarkTagUtils2 = LabelRemarkTagUtils.this;
                            labelRemarkTagUtils2.inputText(LabelRemarkTagUtils.this.reRemark + "_" + LabelRemarkTagUtils.this.nowNickName);
                        }
                        LabelRemarkTagUtils.this.sleep(50);
                        List<AccessibilityNodeInfo> findViewIdList2 = PerformClickUtils.findViewIdList((AccessibilityService) LabelRemarkTagUtils.this.autoService, BaseServiceUtils.complete_id);
                        if (findViewIdList2 != null && findViewIdList2.size() > 0 && MyApplication.enforceable) {
                            PerformClickUtils.performClick(findViewIdList2.get(0));
                        }
                        PerformClickUtils.executedBackEditLabel(LabelRemarkTagUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                            public void find() {
                                LabelRemarkTagUtils.access$1608(LabelRemarkTagUtils.this);
                                LabelRemarkTagUtils.this.statisticsNum();
                                LabelRemarkTagUtils.this.initContactLabelEditUI();
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeTagsMain(LinkedHashSet<String> linkedHashSet) {
        try {
            LogUtils.log("WS_BABY_executeTagsMain");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().checkString(this.autoService, "标签", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    PerformClickUtils.findTextAndClick(LabelRemarkTagUtils.this.autoService, "标签");
                    LabelRemarkTagUtils.this.initContactLabelManagerUI();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "自动备注结束", "共执行了" + this.remarkFriendNum + "人");
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在自动备注，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        new Thread(new Runnable() {
            public void run() {
                try {
                    LabelRemarkTagUtils.this.executeTagsMain(LabelRemarkTagUtils.this.deleteTags);
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void statisticsNum() {
        LogUtils.log("WS_BABY_LauncherUI.statisticsNum." + this.remarkFriendNum);
        SPUtils.put(MyApplication.getConText(), "label_remark_num_part", Integer.valueOf(this.remarkFriendNum));
        MyWindowManager myWindowManager = this.mMyManager;
        updateBottomText(myWindowManager, "正在自动备注,已执行 " + this.remarkFriendNum + " 人");
        tjRemark();
    }

    public void tjRemark() {
        if (this.isAutoGroup && this.remarkNum >= this.groupNumber) {
            this.remarkIndex++;
            this.remarkNum = 0;
        }
    }
}
