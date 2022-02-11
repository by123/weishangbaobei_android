package com.wx.assistants.service_utils;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PerformClickUtils2;
import com.wx.assistants.utils.SPUtils;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupSendPublicNumberToFriendsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static GroupSendPublicNumberToFriendsUtils instance;
    /* access modifiers changed from: private */
    public List<AccessibilityNodeInfo> accessibilityNodeInfo = null;
    /* access modifiers changed from: private */
    public boolean isCanExecuteSelect = true;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isFirstExecute = true;
    /* access modifiers changed from: private */
    public boolean isShowMiddleView = true;
    /* access modifiers changed from: private */
    public boolean isWhile = true;
    /* access modifiers changed from: private */
    public String lastName = "";
    /* access modifiers changed from: private */
    public int maxNum = 1;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int operationJumpNum = 1;
    /* access modifiers changed from: private */
    public int otherSendType = 0;
    /* access modifiers changed from: private */
    public String otherSendTypeName = "公众号";
    /* access modifiers changed from: private */
    public int pullRecordNum = 1;
    private String pullResult = "";
    /* access modifiers changed from: private */
    public List<String> recordList = new ArrayList();
    /* access modifiers changed from: private */
    public int recordNum = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int spaceTime = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagListPeoples;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$108(GroupSendPublicNumberToFriendsUtils groupSendPublicNumberToFriendsUtils) {
        int i = groupSendPublicNumberToFriendsUtils.pullRecordNum;
        groupSendPublicNumberToFriendsUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208(GroupSendPublicNumberToFriendsUtils groupSendPublicNumberToFriendsUtils) {
        int i = groupSendPublicNumberToFriendsUtils.recordNum;
        groupSendPublicNumberToFriendsUtils.recordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(GroupSendPublicNumberToFriendsUtils groupSendPublicNumberToFriendsUtils) {
        int i = groupSendPublicNumberToFriendsUtils.operationJumpNum;
        groupSendPublicNumberToFriendsUtils.operationJumpNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(GroupSendPublicNumberToFriendsUtils groupSendPublicNumberToFriendsUtils) {
        int i = groupSendPublicNumberToFriendsUtils.operationFirstNum;
        groupSendPublicNumberToFriendsUtils.operationFirstNum = i + 1;
        return i;
    }

    private GroupSendPublicNumberToFriendsUtils() {
    }

    public static GroupSendPublicNumberToFriendsUtils getInstance() {
        instance = new GroupSendPublicNumberToFriendsUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isFilled = false;
        this.lastName = "";
        this.pullResult = "";
        this.maxNum = 9;
        this.isShowMiddleView = true;
        this.recordNum = 0;
        this.otherSendType = operationParameterModel.getOtherSendType();
        this.sendType = operationParameterModel.getSendCardType();
        this.pullRecordNum = operationParameterModel.getStartIndex();
        this.tagListPeoples = operationParameterModel.getTagListPeoples();
        this.sayContent = operationParameterModel.getSayContent();
        this.spaceTime = operationParameterModel.getSpaceTime();
        this.operationJumpNum = 1;
        this.operationFirstNum = 0;
        this.isWhile = true;
        this.isFirstExecute = true;
        this.isCanExecuteSelect = true;
        this.recordList = new ArrayList();
        if (this.otherSendType == 0) {
            this.otherSendTypeName = "公众号";
        } else if (this.otherSendType == 1) {
            this.otherSendTypeName = "小程序";
        }
        if (this.sendType == 1) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("WS_BABY.st.0");
                sb.append(this.pullRecordNum > 1);
                LogUtils.log(sb.toString());
                if (this.pullRecordNum > 1) {
                    LogUtils.log("WS_BABY.st.1");
                    for (int i = 0; i < this.pullRecordNum - 1; i++) {
                        LogUtils.log("WS_BABY.st.st" + i);
                        if (this.tagListPeoples.size() > 0) {
                            this.tagListPeoples.remove(this.tagListPeoples.iterator().next());
                        }
                    }
                } else {
                    LogUtils.log("WS_BABY.st.2");
                }
            } catch (Exception unused) {
                LogUtils.log("WS_BABY.st.3");
            }
        }
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void excSelectTask() {
        try {
            if (this.isCanExecuteSelect && MyApplication.enforceable) {
                this.isCanExecuteSelect = false;
                this.operationJumpNum = 1;
                updateBottomText(this.mMyManager, "正在执行好友选择操作");
                LogUtils.log("WS_BABY_SelectContactUI_111111");
                new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeedSetting + executeSpeed, 100, 300, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        new Thread(new Runnable() {
                            public void run() {
                                int i;
                                AccessibilityNodeInfo accessibilityNodeInfo;
                                AccessibilityNodeInfo rootInActiveWindow = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow();
                                if (rootInActiveWindow != null) {
                                    LogUtils.log("WS_BABY_SelectContactUI_111111" + GroupSendPublicNumberToFriendsUtils.this.isWhile + "@" + MyApplication.enforceable);
                                    boolean z = true;
                                    loop0:
                                    while (true) {
                                        i = 0;
                                        while (z && GroupSendPublicNumberToFriendsUtils.this.pullRecordNum > 1 && MyApplication.enforceable) {
                                            LogUtils.log("WS_BABY_SelectContactUI_111111000000000");
                                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_id);
                                            if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0)) == null)) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() <= 0) {
                                                    z = false;
                                                } else {
                                                    int i2 = i;
                                                    i = 0;
                                                    while (true) {
                                                        if (i < findAccessibilityNodeInfosByViewId2.size() - 1) {
                                                            if (GroupSendPublicNumberToFriendsUtils.this.operationJumpNum >= GroupSendPublicNumberToFriendsUtils.this.pullRecordNum) {
                                                                z = false;
                                                                break;
                                                            }
                                                            AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId2.get(i);
                                                            String str = "";
                                                            if (accessibilityNodeInfo2.getText() != null) {
                                                                str = accessibilityNodeInfo2.getText() + "";
                                                            }
                                                            GroupSendPublicNumberToFriendsUtils.this.recordList.add(str);
                                                            GroupSendPublicNumberToFriendsUtils.access$208(GroupSendPublicNumberToFriendsUtils.this);
                                                            BaseServiceUtils.updateBottomText(GroupSendPublicNumberToFriendsUtils.this.mMyManager, "从 " + GroupSendPublicNumberToFriendsUtils.this.pullRecordNum + " 个开始分批群发,已跳过 " + GroupSendPublicNumberToFriendsUtils.this.operationJumpNum + " 个");
                                                            i2 = i;
                                                            i++;
                                                        } else {
                                                            i = i2;
                                                            break;
                                                        }
                                                    }
                                                    if (z) {
                                                        PerformClickUtils.scroll(accessibilityNodeInfo);
                                                        GroupSendPublicNumberToFriendsUtils.this.sleep(100);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    while (true) {
                                        if (!GroupSendPublicNumberToFriendsUtils.this.isWhile || !MyApplication.enforceable) {
                                            break;
                                        }
                                        LogUtils.log("WS_BABY_SelectContactUI_111111111111111");
                                        AccessibilityNodeInfo rootInActiveWindow2 = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow();
                                        if (rootInActiveWindow2 == null) {
                                            LogUtils.log("WS_BABY_SelectContactUI_nullll");
                                            break;
                                        }
                                        LogUtils.log("WS_BABY_SelectContactUI_11111111111111111111");
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                        if (findAccessibilityNodeInfosByViewId3 == null || findAccessibilityNodeInfosByViewId3.size() <= 0) {
                                            LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@55555551111111");
                                        } else {
                                            LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111");
                                            while (true) {
                                                if (i >= findAccessibilityNodeInfosByViewId3.size() - 1) {
                                                    break;
                                                } else if (!MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@");
                                                    return;
                                                } else {
                                                    AccessibilityNodeInfo accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId3.get(i);
                                                    String str2 = accessibilityNodeInfo3.getText() + "";
                                                    LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@");
                                                    if (GroupSendPublicNumberToFriendsUtils.this.recordList.contains(str2) || (GroupSendPublicNumberToFriendsUtils.this.sendType == 2 && GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.contains(str2))) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@#####");
                                                    } else {
                                                        GroupSendPublicNumberToFriendsUtils.access$608(GroupSendPublicNumberToFriendsUtils.this);
                                                        if (GroupSendPublicNumberToFriendsUtils.this.operationFirstNum <= GroupSendPublicNumberToFriendsUtils.this.maxNum) {
                                                            GroupSendPublicNumberToFriendsUtils.access$108(GroupSendPublicNumberToFriendsUtils.this);
                                                            GroupSendPublicNumberToFriendsUtils.this.recordList.add(str2);
                                                            GroupSendPublicNumberToFriendsUtils.this.sleep(1);
                                                            PerformClickUtils.performClick(accessibilityNodeInfo3);
                                                            BaseServiceUtils.updateBottomText(GroupSendPublicNumberToFriendsUtils.this.mMyManager, "正在执行分批群发,已勾选 " + GroupSendPublicNumberToFriendsUtils.this.operationFirstNum + " 个");
                                                            if (GroupSendPublicNumberToFriendsUtils.this.operationFirstNum == GroupSendPublicNumberToFriendsUtils.this.maxNum) {
                                                                boolean unused = GroupSendPublicNumberToFriendsUtils.this.isWhile = false;
                                                                break;
                                                            }
                                                        } else {
                                                            continue;
                                                        }
                                                    }
                                                    i++;
                                                }
                                            }
                                            if (GroupSendPublicNumberToFriendsUtils.this.operationFirstNum == GroupSendPublicNumberToFriendsUtils.this.maxNum) {
                                                int unused2 = GroupSendPublicNumberToFriendsUtils.this.operationFirstNum = 0;
                                                break;
                                            }
                                            LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@111111111");
                                            GroupSendPublicNumberToFriendsUtils.this.sleep(100);
                                            AccessibilityNodeInfo rootInActiveWindow3 = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow3 != null) {
                                                LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@2222222");
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow3.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_id);
                                                if (findAccessibilityNodeInfosByViewId4 != null) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@33333");
                                                    PerformClickUtils.scroll(findAccessibilityNodeInfosByViewId4.get(0));
                                                    GroupSendPublicNumberToFriendsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                                    AccessibilityNodeInfo rootInActiveWindow4 = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow();
                                                    if (rootInActiveWindow4 != null) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@44444");
                                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = rootInActiveWindow4.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                        if (findAccessibilityNodeInfosByViewId5 != null && findAccessibilityNodeInfosByViewId5.size() > 0) {
                                                            LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@66666");
                                                            String charSequence = findAccessibilityNodeInfosByViewId5.get(findAccessibilityNodeInfosByViewId5.size() - 1).getText().toString();
                                                            Rect rect = new Rect();
                                                            findAccessibilityNodeInfosByViewId5.get(findAccessibilityNodeInfosByViewId5.size() - 1).getBoundsInScreen(rect);
                                                            String str3 = charSequence + rect.toString();
                                                            if (str3.equals(GroupSendPublicNumberToFriendsUtils.this.lastName)) {
                                                                LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@77777");
                                                                boolean unused3 = GroupSendPublicNumberToFriendsUtils.this.isFilled = true;
                                                                boolean unused4 = GroupSendPublicNumberToFriendsUtils.this.isWhile = false;
                                                                int unused5 = GroupSendPublicNumberToFriendsUtils.this.operationFirstNum = 0;
                                                                break;
                                                            }
                                                            LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@99999");
                                                            String unused6 = GroupSendPublicNumberToFriendsUtils.this.lastName = str3;
                                                        }
                                                    } else {
                                                        LogUtils.log("WS_BABY_SelectContactUI_1111111111111111111111111@@@@@@@@@@@5555555");
                                                    }
                                                }
                                            }
                                            i = 0;
                                        }
                                    }
                                    LogUtils.log("WS_BABY_SelectContactUI_22222");
                                    BaseServiceUtils.updateBottomText(GroupSendPublicNumberToFriendsUtils.this.mMyManager, "正在分批群发 " + GroupSendPublicNumberToFriendsUtils.this.otherSendTypeName + "\n已向 " + (GroupSendPublicNumberToFriendsUtils.this.pullRecordNum - 1) + " 个好友，发起群发。");
                                    GroupSendPublicNumberToFriendsUtils.this.sleep(100);
                                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.complete_id);
                                    GroupSendPublicNumberToFriendsUtils.this.sleep(BannerConfig.DURATION);
                                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.complete_id);
                                    LogUtils.log("WS_BABY_SelectContactUI_6");
                                    new PerformClickUtils2().checkNodeAllIds(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.toast_edit_id, BaseServiceUtils.dialog_ok_id, 100, 50, 600, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            LogUtils.log("WS_BABY_SelectContactUI_777777777777");
                                            if (GroupSendPublicNumberToFriendsUtils.this.sayContent != null && !GroupSendPublicNumberToFriendsUtils.this.sayContent.equals("")) {
                                                LogUtils.log("WS_BABY_SelectContactUI_7");
                                                GroupSendPublicNumberToFriendsUtils.this.sleep(10);
                                                BaseServiceUtils.getMainHandler().post(new Runnable() {
                                                    public void run() {
                                                        PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendPublicNumberToFriendsUtils.this.sayContent);
                                                    }
                                                });
                                            }
                                            GroupSendPublicNumberToFriendsUtils.this.sleep(10);
                                            PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                                            LogUtils.log("WS_BABY_SelectContactUI_8");
                                            new PerformClickUtils2().checkNodeId(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.contact_list_img_id, SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    GroupSendPublicNumberToFriendsUtils.this.searchStartNode();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSelectNickName() {
        if (MyApplication.enforceable) {
            try {
                LogUtils.log("WS_BABY_" + this.tagListPeoples.toString());
                new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeed, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void find(int i) {
                        Iterator it = GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.iterator();
                        if (it.hasNext()) {
                            final String str = (String) it.next();
                            LogUtils.log("WS_BABY_SelectContactUI_2");
                            PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.list_select_search_id, str);
                            GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.remove(str);
                            new PerformClickUtils2().check(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.list_select_name_id, (BaseServiceUtils.executeSpeedSetting / 2) + 600, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                                public void find(int i) {
                                    new Thread(new Runnable() {
                                        public void run() {
                                            LogUtils.log("WS_BABY_SelectContactUI_3");
                                            AccessibilityNodeInfo rootInActiveWindow = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow();
                                            if (rootInActiveWindow != null && MyApplication.enforceable) {
                                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_select_name_id);
                                                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || !MyApplication.enforceable) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_3_nufind");
                                                    if (GroupSendPublicNumberToFriendsUtils.this.recordNum == GroupSendPublicNumberToFriendsUtils.this.maxNum || GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                        if (GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                            boolean unused = GroupSendPublicNumberToFriendsUtils.this.isFilled = true;
                                                        }
                                                        GroupSendPublicNumberToFriendsUtils.this.searchEnd();
                                                        return;
                                                    }
                                                    GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                                    return;
                                                }
                                                boolean z = true;
                                                int i = 0;
                                                boolean z2 = false;
                                                while (z && i < findAccessibilityNodeInfosByViewId.size() && MyApplication.enforceable) {
                                                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(i);
                                                    i++;
                                                    if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                                        LogUtils.log("WS_BABY_SelectContactUI_4_" + accessibilityNodeInfo.getText() + "");
                                                        String str = "";
                                                        try {
                                                            str = accessibilityNodeInfo.getText() + "";
                                                        } catch (Exception unused2) {
                                                        }
                                                        if (str.trim().equals(str.trim())) {
                                                            LogUtils.log("WS_BABY_SelectContactUI_5");
                                                            PerformClickUtils.performClick(accessibilityNodeInfo);
                                                            GroupSendPublicNumberToFriendsUtils.access$1208(GroupSendPublicNumberToFriendsUtils.this);
                                                            GroupSendPublicNumberToFriendsUtils.access$108(GroupSendPublicNumberToFriendsUtils.this);
                                                            if (GroupSendPublicNumberToFriendsUtils.this.recordNum == GroupSendPublicNumberToFriendsUtils.this.maxNum || GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                                if (GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                                    boolean unused3 = GroupSendPublicNumberToFriendsUtils.this.isFilled = true;
                                                                }
                                                                GroupSendPublicNumberToFriendsUtils.this.searchEnd();
                                                            } else {
                                                                GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                                            }
                                                            z = false;
                                                            z2 = true;
                                                        }
                                                    }
                                                }
                                                if (!z2) {
                                                    LogUtils.log("WS_BABY_SelectContactUI_2_nufind");
                                                    if (GroupSendPublicNumberToFriendsUtils.this.recordNum == GroupSendPublicNumberToFriendsUtils.this.maxNum || GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                        if (GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                                            boolean unused4 = GroupSendPublicNumberToFriendsUtils.this.isFilled = true;
                                                        }
                                                        GroupSendPublicNumberToFriendsUtils.this.searchEnd();
                                                        return;
                                                    }
                                                    GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                                }
                                            }
                                        }
                                    }).start();
                                }

                                public void nuFind() {
                                    LogUtils.log("WS_BABY_SelectContactUI_4_nufind");
                                    if (GroupSendPublicNumberToFriendsUtils.this.recordNum == GroupSendPublicNumberToFriendsUtils.this.maxNum || GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                        if (GroupSendPublicNumberToFriendsUtils.this.tagListPeoples.size() == 0) {
                                            boolean unused = GroupSendPublicNumberToFriendsUtils.this.isFilled = true;
                                        }
                                        GroupSendPublicNumberToFriendsUtils.this.searchEnd();
                                        return;
                                    }
                                    GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                }
                            });
                        }
                    }

                    public void nuFind() {
                        LogUtils.log("WS_BABY_SelectContactUI_5_nufind");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void searchEnd() {
        if (MyApplication.enforceable) {
            this.recordNum = 0;
            sleep(100);
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("正在分批群发 ");
            sb.append(this.otherSendTypeName);
            sb.append("\n已向 ");
            sb.append(this.pullRecordNum - 1);
            sb.append(" 个好友，发起群发。");
            updateBottomText(myWindowManager, sb.toString());
            PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
            sleep(BannerConfig.DURATION);
            PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
            saveRecord(this.pullRecordNum);
            LogUtils.log("WS_BABY_SelectContactUI_6");
            new PerformClickUtils2().checkNodeAllIds(this.autoService, toast_edit_id, dialog_ok_id, 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_SelectContactUI_777777777777");
                    if (GroupSendPublicNumberToFriendsUtils.this.sayContent != null && !GroupSendPublicNumberToFriendsUtils.this.sayContent.equals("")) {
                        LogUtils.log("WS_BABY_SelectContactUI_7");
                        GroupSendPublicNumberToFriendsUtils.this.sleep(10);
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                PerformClickUtils.findViewByIdAndPasteContent(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.toast_edit_id, GroupSendPublicNumberToFriendsUtils.this.sayContent);
                            }
                        });
                    }
                    GroupSendPublicNumberToFriendsUtils.this.sleep(10);
                    PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    new PerformClickUtils2().checkNodeId(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.contact_list_img_id, SocializeConstants.CANCLE_RESULTCODE, 100, 100, new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            GroupSendPublicNumberToFriendsUtils.this.searchStartNode();
                        }
                    });
                }
            });
        }
    }

    public void searchStartNode() {
        try {
            if (MyApplication.enforceable) {
                LogUtils.log("WS_BABY_MAIN.1");
                this.isShowMiddleView = true;
                this.lastName = "";
                saveRecord(this.pullRecordNum);
                if (this.isFilled) {
                    saveRecord(1);
                    removeEndView(this.mMyManager);
                    return;
                }
                MyWindowManager myWindowManager = this.mMyManager;
                updateBottomText(myWindowManager, "正在分批群发 " + this.otherSendTypeName + "\n已向 " + (this.pullRecordNum - 1) + " 个好友，发起群发。\n若出现卡顿，请耐心等待几秒..");
                new PerformClickUtils2().delay(100, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        if (GroupSendPublicNumberToFriendsUtils.this.spaceTime <= 0 || GroupSendPublicNumberToFriendsUtils.this.isFirstExecute) {
                            boolean unused = GroupSendPublicNumberToFriendsUtils.this.isFirstExecute = false;
                            LogUtils.log("WS_BABY_executeMain4");
                            GroupSendPublicNumberToFriendsUtils.this.startSend();
                            return;
                        }
                        LogUtils.log("WS_BABY_executeMain2");
                        new PerformClickUtils2().countDown2(GroupSendPublicNumberToFriendsUtils.this.spaceTime, new PerformClickUtils2.OnCountDownListener() {
                            public void surplus(int i) {
                                MyWindowManager myWindowManager = GroupSendPublicNumberToFriendsUtils.this.mMyManager;
                                StringBuilder sb = new StringBuilder();
                                sb.append("已向 ");
                                sb.append(GroupSendPublicNumberToFriendsUtils.this.pullRecordNum - 1);
                                sb.append(" 个好友，发起群发。\n若出现卡顿，请耐心等待..\n正在延时等待，倒计时 ");
                                sb.append(i);
                                sb.append(" 秒");
                                BaseServiceUtils.updateBottomText(myWindowManager, sb.toString());
                            }

                            public void end() {
                                LogUtils.log("WS_BABY_executeMain3");
                                GroupSendPublicNumberToFriendsUtils.this.startSend();
                            }
                        });
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startSend() {
        new PerformClickUtils2().check(this.autoService, contact_list_img_id, (executeSpeedSetting / 8) + 50, 100, 30, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void find(int i) {
                boolean z;
                LogUtils.log("WS_BABY_MAIN.2");
                LogUtils.log("WS_BABY_MAIN.3");
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = GroupSendPublicNumberToFriendsUtils.this.autoService.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(BaseServiceUtils.contact_list_img_id);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    LogUtils.log("WS_BABY_MAIN.4");
                    int size = findAccessibilityNodeInfosByViewId.size();
                    while (true) {
                        z = false;
                        if (size <= 0) {
                            break;
                        }
                        size--;
                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(size);
                        if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 0) {
                            List unused = GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText("公众号名片");
                        } else if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 1) {
                            List unused2 = GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText("小程序");
                        }
                        if (GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo != null && GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo.size() > 0) {
                            LogUtils.log("WS_BABY_000000000has");
                            boolean unused3 = GroupSendPublicNumberToFriendsUtils.this.isCanExecuteSelect = true;
                            boolean unused4 = GroupSendPublicNumberToFriendsUtils.this.isWhile = true;
                            PerformClickUtils.longClick((AccessibilityNodeInfo) GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo.get(0), new PerformClickUtils.OnLongClickListener() {
                                public void execute7() {
                                    LogUtils.log("WS_BABY_executeMain_phone7+");
                                    GroupSendPublicNumberToFriendsUtils.this.executeStartSend();
                                }

                                public void execute6() {
                                    LogUtils.log("WS_BABY_executeMain_phone7-");
                                    PerformClickUtils.performLongClick((AccessibilityNodeInfo) GroupSendPublicNumberToFriendsUtils.this.accessibilityNodeInfo.get(0));
                                    GroupSendPublicNumberToFriendsUtils.this.executeStartSend();
                                }
                            });
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        LogUtils.log("WS_BABY_000000000nohas");
                        BaseServiceUtils.getMainHandler().post(new Runnable() {
                            public void run() {
                                boolean unused = GroupSendPublicNumberToFriendsUtils.this.isShowMiddleView = false;
                                BaseServiceUtils.removeEndView(GroupSendPublicNumberToFriendsUtils.this.mMyManager);
                                GroupSendPublicNumberToFriendsUtils.this.mMyManager.showStartView();
                                GroupSendPublicNumberToFriendsUtils.this.mMyManager.showBackView();
                                GroupSendPublicNumberToFriendsUtils.this.mMyManager.removeBottomView();
                                if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 0) {
                                    GroupSendPublicNumberToFriendsUtils.this.mMyManager.toastToUserError("没有找到要转发的公众号\n请将要转发的公众号,放在聊天页的底部");
                                } else if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 1) {
                                    GroupSendPublicNumberToFriendsUtils.this.mMyManager.toastToUserError("没有找到要转发的小程序\n请将要转发的小程序,放在聊天页的底部");
                                }
                            }
                        });
                    }
                }
            }

            public void nuFind() {
                BaseServiceUtils.getMainHandler().post(new Runnable() {
                    public void run() {
                        boolean unused = GroupSendPublicNumberToFriendsUtils.this.isShowMiddleView = false;
                        BaseServiceUtils.removeEndView(GroupSendPublicNumberToFriendsUtils.this.mMyManager);
                        GroupSendPublicNumberToFriendsUtils.this.mMyManager.showStartView();
                        GroupSendPublicNumberToFriendsUtils.this.mMyManager.showBackView();
                        GroupSendPublicNumberToFriendsUtils.this.mMyManager.removeBottomView();
                        if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 0) {
                            GroupSendPublicNumberToFriendsUtils.this.mMyManager.toastToUserError("没有找到要转发的公众号\n请将要转发的公众号,放在聊天页的底部");
                        } else if (GroupSendPublicNumberToFriendsUtils.this.otherSendType == 1) {
                            GroupSendPublicNumberToFriendsUtils.this.mMyManager.toastToUserError("没有找到要转发的小程序\n请将要转发的小程序,放在聊天页的底部");
                        }
                    }
                });
            }
        });
    }

    public void executeStartSend() {
        new PerformClickUtils2().checkString(this.autoService, "发送给朋友", 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "发送给朋友");
                new PerformClickUtils2().checkString(GroupSendPublicNumberToFriendsUtils.this.autoService, "多选", (BaseServiceUtils.executeSpeedSetting / 2) + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "多选");
                        new PerformClickUtils2().checkString(GroupSendPublicNumberToFriendsUtils.this.autoService, "更多联系人", (BaseServiceUtils.executeSpeedSetting / 2) + 50, 50, 200, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "更多联系人");
                                if (GroupSendPublicNumberToFriendsUtils.this.sendType == 0) {
                                    GroupSendPublicNumberToFriendsUtils.this.excSelectTask();
                                } else if (GroupSendPublicNumberToFriendsUtils.this.sendType == 1) {
                                    GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                } else if (GroupSendPublicNumberToFriendsUtils.this.sendType == 2) {
                                    GroupSendPublicNumberToFriendsUtils.this.excSelectTask();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public void saveRecord(int i) {
        if (this.otherSendType == 0) {
            if (this.sendType == 0) {
                SPUtils.put(this.autoService, "public_number_to_friend_num_all", Integer.valueOf(i));
            } else if (this.sendType == 1) {
                SPUtils.put(this.autoService, "public_number_to_friend_num_part", Integer.valueOf(i));
            } else if (this.sendType == 2) {
                SPUtils.put(this.autoService, "public_number_to_friend_num_shield", Integer.valueOf(i));
            }
        } else if (this.otherSendType != 1) {
        } else {
            if (this.sendType == 0) {
                SPUtils.put(this.autoService, "mini_programs_to_friend_num_all", Integer.valueOf(i));
            } else if (this.sendType == 1) {
                SPUtils.put(this.autoService, "mini_programs_to_friend_num_part", Integer.valueOf(i));
            } else if (this.sendType == 2) {
                SPUtils.put(this.autoService, "mini_programs_to_friend_num_shield", Integer.valueOf(i));
            }
        }
    }

    public void executeMain() {
        try {
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("正在分批群发 ");
            sb.append(this.otherSendTypeName);
            sb.append("\n已向 ");
            sb.append(this.pullRecordNum - 1);
            sb.append(" 个好友，发起群发。\n若出现卡顿，请耐心等待几秒..");
            updateBottomText(myWindowManager, sb.toString());
            saveRecord(this.pullRecordNum);
            LogUtils.log("WS_BABY_executeMain0");
            new PerformClickUtils2().delay(100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain1");
                    if (GroupSendPublicNumberToFriendsUtils.this.isFilled || !MyApplication.enforceable) {
                        BaseServiceUtils.removeEndView(GroupSendPublicNumberToFriendsUtils.this.mMyManager);
                        return;
                    }
                    boolean unused = GroupSendPublicNumberToFriendsUtils.this.isCanExecuteSelect = true;
                    boolean unused2 = GroupSendPublicNumberToFriendsUtils.this.isWhile = true;
                    LogUtils.log("WS_BABY_executeMain3");
                    new PerformClickUtils2().check(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.right_more_id, BaseServiceUtils.executeSpeedSetting + BaseServiceUtils.executeSpeed, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                        public void nuFind() {
                        }

                        public void find(int i) {
                            PerformClickUtils.findViewIdAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, BaseServiceUtils.right_more_id);
                            new PerformClickUtils2().checkString(GroupSendPublicNumberToFriendsUtils.this.autoService, "推荐给朋友", 50, 50, 600, new PerformClickUtils2.OnCheckListener() {
                                public void nuFind() {
                                }

                                public void find(int i) {
                                    PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "推荐给朋友");
                                    new PerformClickUtils2().checkString(GroupSendPublicNumberToFriendsUtils.this.autoService, "多选", (BaseServiceUtils.executeSpeedSetting / 2) + 50, 50, 400, new PerformClickUtils2.OnCheckListener() {
                                        public void nuFind() {
                                        }

                                        public void find(int i) {
                                            PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "多选");
                                            new PerformClickUtils2().checkString(GroupSendPublicNumberToFriendsUtils.this.autoService, "更多联系人", (BaseServiceUtils.executeSpeedSetting / 2) + 50, 50, 400, new PerformClickUtils2.OnCheckListener() {
                                                public void nuFind() {
                                                }

                                                public void find(int i) {
                                                    PerformClickUtils.findTextAndClick(GroupSendPublicNumberToFriendsUtils.this.autoService, "更多联系人");
                                                    if (GroupSendPublicNumberToFriendsUtils.this.sendType == 0) {
                                                        GroupSendPublicNumberToFriendsUtils.this.excSelectTask();
                                                    } else if (GroupSendPublicNumberToFriendsUtils.this.sendType == 1) {
                                                        GroupSendPublicNumberToFriendsUtils.this.initSelectNickName();
                                                    } else if (GroupSendPublicNumberToFriendsUtils.this.sendType == 2) {
                                                        GroupSendPublicNumberToFriendsUtils.this.excSelectTask();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        if (this.pullResult == null || "".equals(this.pullResult)) {
            StringBuilder sb = new StringBuilder();
            sb.append("本次共向");
            sb.append(this.pullRecordNum - 1);
            sb.append("个好友，发起了群发");
            sb.append(this.otherSendTypeName);
            sb.append("！");
            updateMiddleText(this.mMyManager, "自动分批群发" + this.otherSendTypeName + "", sb.toString());
            return;
        }
        updateMiddleText(this.mMyManager, "自动分批群发" + this.otherSendTypeName + "", this.pullResult);
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.pullRecordNum + " 个好友群发" + this.otherSendTypeName + "\n请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    GroupSendPublicNumberToFriendsUtils.this.searchStartNode();
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        if (this.isShowMiddleView) {
            this.mMyManager.showMiddleView();
        }
    }
}
