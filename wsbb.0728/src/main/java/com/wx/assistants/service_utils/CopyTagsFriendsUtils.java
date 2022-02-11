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
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class CopyTagsFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static CopyTagsFriendsUtils instance;
    /* access modifiers changed from: private */
    public int copyNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> deleteTags = null;
    /* access modifiers changed from: private */
    public int deleteType = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> fansRecord = null;
    boolean isJumpExecute = false;
    /* access modifiers changed from: private */
    public boolean isScrollBottom = false;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    private LinkedHashSet<String> keyWords = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String lastTagName = "";
    /* access modifiers changed from: private */
    public String nowName = "";
    /* access modifiers changed from: private */
    public String nowNameNum = "";
    /* access modifiers changed from: private */
    public String nowTag = "";
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startIndex2 = 0;

    private CopyTagsFriendsUtils() {
    }

    static /* synthetic */ int access$108(CopyTagsFriendsUtils copyTagsFriendsUtils) {
        int i = copyTagsFriendsUtils.copyNum;
        copyTagsFriendsUtils.copyNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(CopyTagsFriendsUtils copyTagsFriendsUtils) {
        int i = copyTagsFriendsUtils.startIndex;
        copyTagsFriendsUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(CopyTagsFriendsUtils copyTagsFriendsUtils) {
        int i = copyTagsFriendsUtils.startIndex2;
        copyTagsFriendsUtils.startIndex2 = i + 1;
        return i;
    }

    public static CopyTagsFriendsUtils getInstance() {
        instance = new CopyTagsFriendsUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在一键备份好友，请不要操作微信！");
        LogUtils.log("WS_BABY.END_SHOW");
        getMainHandler().postDelayed(new Runnable() {
            public void run() {
                if (CopyTagsFriendsUtils.this.deleteType == 1) {
                    CopyTagsFriendsUtils.this.executeKeyWordMain();
                } else if (CopyTagsFriendsUtils.this.deleteType == 2) {
                    CopyTagsFriendsUtils.this.executeTagsMain(CopyTagsFriendsUtils.this.deleteTags);
                }
            }
        }, 10);
    }

    public void executeKeyWordMain() {
        try {
            LogUtils.log("WS_BABY.executeKeyWordMain");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().check(this.autoService, contact_nav_search_viewgroup_id, executeSpeed + executeSpeedSetting, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    AccessibilityNodeInfo rootInActiveWindow = CopyTagsFriendsUtils.this.autoService.getRootInActiveWindow();
                    if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_viewgroup_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                        if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                            PerformClickUtils.findTextAndClickFirst(CopyTagsFriendsUtils.this.autoService, "搜索");
                        } else {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = findAccessibilityNodeInfosByViewId.get(0).findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_nav_search_img_id);
                            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && findAccessibilityNodeInfosByViewId2.get(0) != null && MyApplication.enforceable) {
                                PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId2.get(0));
                            }
                        }
                        CopyTagsFriendsUtils.this.initFirstFTSMainUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeTagsMain(LinkedHashSet<String> linkedHashSet) {
        try {
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            PerformClickUtils.findTextAndClick(this.autoService, "通讯录");
            new PerformClickUtils2().checkString(this.autoService, "标签", executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    if (CopyTagsFriendsUtils.this.autoService.getRootInActiveWindow() != null) {
                        PerformClickUtils.findTextAndClick(CopyTagsFriendsUtils.this.autoService, "标签");
                        CopyTagsFriendsUtils.this.initContactLabelManagerUI();
                    }
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initChattingUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.right_more_id);
                    CopyTagsFriendsUtils.this.initSingleChatInfo();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContactInfoUI() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    CopyTagsFriendsUtils.access$108(CopyTagsFriendsUtils.this);
                    MyWindowManager myWindowManager = CopyTagsFriendsUtils.this.mMyManager;
                    BaseServiceUtils.updateBottomText(myWindowManager, "已备份了 " + CopyTagsFriendsUtils.this.copyNum + " 个好友");
                    CopyTagsFriendsUtils.this.saveCopyFriendInfo(CopyTagsFriendsUtils.this.nowName);
                    PerformClickUtils.executedBackHome(CopyTagsFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                        public void find() {
                            CopyTagsFriendsUtils.this.executeKeyWordMain();
                        }
                    });
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_75");
            e.printStackTrace();
        }
    }

    public void initContactInfoUIByTag() {
        try {
            new PerformClickUtils2().check(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 50, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    new PerformClickUtils2().checkString(CopyTagsFriendsUtils.this.autoService, "发消息", 10, 100, 2, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            LogUtils.log("WS_BABY_ContactInfoUI");
                            CopyTagsFriendsUtils.this.saveCopyFriendInfo(CopyTagsFriendsUtils.this.nowName);
                            CopyTagsFriendsUtils.this.sleep(100);
                            PerformClickUtils.findViewIdAndClick(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.back_contact_id);
                            CopyTagsFriendsUtils.access$108(CopyTagsFriendsUtils.this);
                            MyWindowManager myWindowManager = CopyTagsFriendsUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已备份了 " + CopyTagsFriendsUtils.this.copyNum + " 个好友");
                            new PerformClickUtils2().check(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id, 200, 100, 300, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    CopyTagsFriendsUtils.this.initContactLabelEditUI();
                                }

                                public void nuFind() {
                                }
                            });
                        }

                        public void nuFind() {
                            PerformClickUtils.performBack(CopyTagsFriendsUtils.this.autoService);
                            CopyTagsFriendsUtils.this.sleep(BannerConfig.DURATION);
                            CopyTagsFriendsUtils.this.initContactLabelEditUI();
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

    public void initContactLabelEditUI() {
        try {
            LogUtils.log("WS_BABY.ContactLabelEditUI.index" + this.startIndex2);
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, this.isJumpExecute ? executeSpeed + executeSpeedSetting : CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY.ContactLabelEditUI.8");
                    List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    if (findViewIdList == null || findViewIdList.size() <= 0) {
                        LogUtils.log("WS_BAB_ContactLabelEditUI.100");
                        return;
                    }
                    LogUtils.log("WS_BABY.ContactLabelEditUI.9");
                    if (CopyTagsFriendsUtils.this.nowNameNum != null) {
                        String access$500 = CopyTagsFriendsUtils.this.nowNameNum;
                        if (access$500.equals(findViewIdList.size() + "")) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.10");
                            boolean unused = CopyTagsFriendsUtils.this.isScrollBottom = true;
                        }
                    }
                    boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "删除标签");
                    boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "添加成员");
                    boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "删除成员");
                    if (findNodeIsExistByText || findNodeIsExistByText2 || findNodeIsExistByText3) {
                        LogUtils.log("WS_BABY_ContactLabelEditUI.11");
                        boolean unused2 = CopyTagsFriendsUtils.this.isScrollBottom = true;
                    }
                    LogUtils.log("WS_BABY_ContactLabelEditUI.22..startIndex." + CopyTagsFriendsUtils.this.startIndex2 + ListUtils.DEFAULT_JOIN_SEPARATOR + findViewIdList.size());
                    if (CopyTagsFriendsUtils.this.startIndex2 < findViewIdList.size() && MyApplication.enforceable) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findViewIdList.get(CopyTagsFriendsUtils.this.startIndex2);
                        LogUtils.log("WS_BABY_ContactLabelEditUI.33");
                        if (accessibilityNodeInfo == null) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.44");
                            return;
                        }
                        CopyTagsFriendsUtils copyTagsFriendsUtils = CopyTagsFriendsUtils.this;
                        String unused3 = copyTagsFriendsUtils.nowName = accessibilityNodeInfo.getText() + "";
                        if (!CopyTagsFriendsUtils.this.isScrollBottom || !CopyTagsFriendsUtils.this.fansRecord.contains(CopyTagsFriendsUtils.this.nowName)) {
                            CopyTagsFriendsUtils.this.isJumpExecute = false;
                            CopyTagsFriendsUtils.this.fansRecord.add(CopyTagsFriendsUtils.this.nowName);
                            CopyTagsFriendsUtils.access$908(CopyTagsFriendsUtils.this);
                            LogUtils.log("WS_BABY_ContactLabelEditUI.66");
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            CopyTagsFriendsUtils.this.initContactInfoUIByTag();
                            return;
                        }
                        LogUtils.log("WS_BABY_ContactLabelEditUI.55");
                        CopyTagsFriendsUtils.access$908(CopyTagsFriendsUtils.this);
                        CopyTagsFriendsUtils.this.isJumpExecute = true;
                        CopyTagsFriendsUtils.this.initContactLabelEditUI();
                    } else if (CopyTagsFriendsUtils.this.startIndex2 >= findViewIdList.size() && MyApplication.enforceable) {
                        if (!CopyTagsFriendsUtils.this.isScrollBottom) {
                            LogUtils.log("WS_BABY_ContactLabelEditUI.88");
                            PerformClickUtils.scroll(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.default_list_id);
                            CopyTagsFriendsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "删除标签");
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "添加成员");
                            boolean findNodeIsExistByText6 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "删除成员");
                            if (findNodeIsExistByText4 || findNodeIsExistByText5 || findNodeIsExistByText6) {
                                LogUtils.log("WS_BABY_ContactLabelEditUI.99");
                                boolean unused4 = CopyTagsFriendsUtils.this.isScrollBottom = true;
                            }
                            int unused5 = CopyTagsFriendsUtils.this.startIndex2 = 5;
                            CopyTagsFriendsUtils.this.initContactLabelEditUI();
                        } else if (CopyTagsFriendsUtils.this.deleteTags == null || CopyTagsFriendsUtils.this.deleteTags.size() <= 0) {
                            BaseServiceUtils.removeEndView(CopyTagsFriendsUtils.this.mMyManager);
                        } else {
                            MyWindowManager myWindowManager = CopyTagsFriendsUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已备份【 " + CopyTagsFriendsUtils.this.nowTag + " 】中的全部好友");
                            LogUtils.log("WS_BABY_ContactLabelEditUI.77");
                            PerformClickUtils.performBack(CopyTagsFriendsUtils.this.autoService);
                            CopyTagsFriendsUtils.this.sleep(BannerConfig.DURATION);
                            int unused6 = CopyTagsFriendsUtils.this.startIndex2 = 0;
                            CopyTagsFriendsUtils.this.initContactLabelManagerUI();
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

    public void initContactLabelManagerUI() {
        try {
            LogUtils.log("WS_BABY.ContactLabelManagerUI.2");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, tag_list_item_num_id, tag_list_item_name_id, executeSpeed + executeSpeedSetting, 200, 200, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2;
                    String str;
                    LogUtils.log("WS_BABY.ContactLabelManagerUI.3");
                    while (CopyTagsFriendsUtils.this.isWhile && MyApplication.enforceable) {
                        AccessibilityNodeInfo rootInActiveWindow = CopyTagsFriendsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                            LogUtils.log("WS_BABY.ContactLabelManagerUI.4");
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_name_id);
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_num_id);
                            if (findAccessibilityNodeInfosByViewId3 != null && !findAccessibilityNodeInfosByViewId3.isEmpty() && MyApplication.enforceable) {
                                if (CopyTagsFriendsUtils.this.startIndex < findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId3.get(CopyTagsFriendsUtils.this.startIndex);
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId4.get(CopyTagsFriendsUtils.this.startIndex);
                                    try {
                                        if (accessibilityNodeInfo.getText() != null) {
                                            String unused = CopyTagsFriendsUtils.this.nowTag = accessibilityNodeInfo.getText() + "";
                                        }
                                        if (accessibilityNodeInfo2.getText() != null) {
                                            String unused2 = CopyTagsFriendsUtils.this.nowNameNum = accessibilityNodeInfo2.getText() + "";
                                        }
                                    } catch (Exception e) {
                                    }
                                    if (!CopyTagsFriendsUtils.this.deleteTags.contains(CopyTagsFriendsUtils.this.nowTag)) {
                                        CopyTagsFriendsUtils.access$308(CopyTagsFriendsUtils.this);
                                    } else if (!"(0)".equals(CopyTagsFriendsUtils.this.nowNameNum)) {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.6");
                                        CopyTagsFriendsUtils.this.deleteTags.remove(CopyTagsFriendsUtils.this.nowTag);
                                        PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId3.get(CopyTagsFriendsUtils.this.startIndex));
                                        int unused3 = CopyTagsFriendsUtils.this.startIndex = 0;
                                        String unused4 = CopyTagsFriendsUtils.this.lastTagName = "";
                                        CopyTagsFriendsUtils.this.initContactLabelEditUI();
                                        return;
                                    } else {
                                        LogUtils.log("WS_BABY.ContactLabelManagerUI.7");
                                        CopyTagsFriendsUtils.access$308(CopyTagsFriendsUtils.this);
                                    }
                                } else if (CopyTagsFriendsUtils.this.startIndex >= findAccessibilityNodeInfosByViewId3.size() && MyApplication.enforceable) {
                                    LogUtils.log("WS_BABY.ContactLabelManagerUI.8");
                                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                                        PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId.get(0));
                                        CopyTagsFriendsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        AccessibilityNodeInfo rootInActiveWindow2 = CopyTagsFriendsUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_list_item_name_id)) != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                                            AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId2.get(findAccessibilityNodeInfosByViewId2.size() - 1);
                                            try {
                                                str = accessibilityNodeInfo3.getText() + "";
                                            } catch (Exception e2) {
                                                str = "";
                                            }
                                            if (str.equals(CopyTagsFriendsUtils.this.lastTagName)) {
                                                boolean unused5 = CopyTagsFriendsUtils.this.isWhile = false;
                                                BaseServiceUtils.removeEndView(CopyTagsFriendsUtils.this.mMyManager);
                                            } else {
                                                String unused6 = CopyTagsFriendsUtils.this.lastTagName = str;
                                            }
                                            int unused7 = CopyTagsFriendsUtils.this.startIndex = 0;
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

    public void initData(OperationParameterModel operationParameterModel) {
        this.startIndex = 0;
        this.startIndex2 = 0;
        this.copyNum = 0;
        this.lastTagName = "";
        this.nowTag = "";
        this.nowNameNum = "0";
        this.nowName = "";
        this.isWhile = true;
        this.isScrollBottom = false;
        this.fansRecord = new LinkedHashSet<>();
        String friendsNameStr = operationParameterModel.getFriendsNameStr();
        this.keyWords = new LinkedHashSet<>();
        if (friendsNameStr != null && !"".equals(friendsNameStr)) {
            if (friendsNameStr.contains("##")) {
                String[] split = friendsNameStr.split("##");
                for (String str : split) {
                    if (str != null && !"".equals(str)) {
                        this.keyWords.add(str);
                    }
                }
            } else {
                this.keyWords.add(friendsNameStr);
            }
        }
        this.deleteTags = operationParameterModel.getTagListNames();
        this.deleteType = operationParameterModel.getDeleteFriendsType();
        this.keyWords = operationParameterModel.getTagListPeoples();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void initFirstFTSMainUI() {
        searchNickName(this.keyWords);
    }

    public void initSingleChatInfo() {
        try {
            new PerformClickUtils2().check(this.autoService, single_contact_nick_name_id, executeSpeed + executeSpeedSetting, 100, 20, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    PerformClickUtils.findViewIdAndClick(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.single_contact_nick_name_id);
                    CopyTagsFriendsUtils.this.initContactInfoUI();
                }

                public void nuFind() {
                }
            });
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_74");
            e.printStackTrace();
        }
    }

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        setMiddleText(myWindowManager, "一键备份好友", "已备份了" + this.copyNum + "个好友");
    }

    public void searchNickName(final LinkedHashSet<String> linkedHashSet) {
        if (linkedHashSet != null) {
            try {
                if (linkedHashSet.size() > 0) {
                    sleep(SocializeConstants.CANCLE_RESULTCODE);
                    if (linkedHashSet != null && !"".equals(linkedHashSet) && MyApplication.enforceable) {
                        String str = (String) linkedHashSet.iterator().next();
                        if (str != null && !str.equals("") && str.length() > 20) {
                            str = str.substring(0, 20);
                        }
                        PerformClickUtils.inputText(this.autoService, str);
                    }
                    new PerformClickUtils2().checkNodeStringsHasSomeOne(this.autoService, "联系人", "最常使用", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 6, new PerformClickUtils2.OnCheckListener() {
                        public void find(int i) {
                            AccessibilityNodeInfo rootInActiveWindow = CopyTagsFriendsUtils.this.autoService.getRootInActiveWindow();
                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                    BaseServiceUtils.removeEndView(CopyTagsFriendsUtils.this.mMyManager);
                                    return;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "联系人");
                                boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(CopyTagsFriendsUtils.this.autoService, "最常使用");
                                if ((findNodeIsExistByText || findNodeIsExistByText2) && MyApplication.enforceable) {
                                    linkedHashSet.remove(linkedHashSet.iterator().next());
                                    LogUtils.log("WS_BABY.search_into");
                                    PerformClickUtils.performClick(findAccessibilityNodeInfosByViewId.get(0));
                                    try {
                                        String unused = CopyTagsFriendsUtils.this.nowName = PerformClickUtils.getTextFirstByNodeId(CopyTagsFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id);
                                        LogUtils.log("WS_BABY.search_into." + CopyTagsFriendsUtils.this.nowName);
                                    } catch (Exception e) {
                                    }
                                    CopyTagsFriendsUtils.this.initChattingUI();
                                    return;
                                }
                                linkedHashSet.remove(linkedHashSet.iterator().next());
                                if (linkedHashSet == null || linkedHashSet.size() <= 0 || !MyApplication.enforceable) {
                                    BaseServiceUtils.removeEndView(CopyTagsFriendsUtils.this.mMyManager);
                                } else {
                                    PerformClickUtils.executedBackHome(CopyTagsFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                        public void find() {
                                            CopyTagsFriendsUtils.this.executeKeyWordMain();
                                        }
                                    });
                                }
                            }
                        }

                        public void nuFind() {
                            linkedHashSet.remove(linkedHashSet.iterator().next());
                            if (linkedHashSet == null || linkedHashSet.size() <= 0) {
                                BaseServiceUtils.removeEndView(CopyTagsFriendsUtils.this.mMyManager);
                                return;
                            }
                            CopyTagsFriendsUtils.access$108(CopyTagsFriendsUtils.this);
                            MyWindowManager myWindowManager = CopyTagsFriendsUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "已备份了 " + CopyTagsFriendsUtils.this.copyNum + " 个好友");
                            PerformClickUtils.executedBackHome(CopyTagsFriendsUtils.this.autoService, 30, new PerformClickUtils.OnBackMainPageListener() {
                                public void find() {
                                    CopyTagsFriendsUtils.this.executeKeyWordMain();
                                }
                            });
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        removeEndView(this.mMyManager);
    }
}
