package com.wx.assistants.service_utils;

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
import com.youth.banner.BannerConfig;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoPullTagFriendsToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullTagFriendsToGroupUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public String currentTagName = "";
    /* access modifiers changed from: private */
    public boolean isExist = false;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public boolean isJumpedStart = true;
    /* access modifiers changed from: private */
    public String lastName;
    /* access modifiers changed from: private */
    public int lastOperationNum = 0;
    /* access modifiers changed from: private */
    public int maxNum = 40;
    /* access modifiers changed from: private */
    public String middleStr = "";
    List<AccessibilityNodeInfo> nameNodes = null;
    /* access modifiers changed from: private */
    public String nodeText;
    /* access modifiers changed from: private */
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = 40;
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int scrollSpeed = 150;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags = new LinkedHashSet<>();

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$1208(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.pullRecordNum;
        autoPullTagFriendsToGroupUtils.pullRecordNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1308(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.scrollNum;
        autoPullTagFriendsToGroupUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.startIndex;
        autoPullTagFriendsToGroupUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.currentSelectNum;
        autoPullTagFriendsToGroupUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils) {
        int i = autoPullTagFriendsToGroupUtils.operationFirstNum;
        autoPullTagFriendsToGroupUtils.operationFirstNum = i + 1;
        return i;
    }

    private AutoPullTagFriendsToGroupUtils() {
    }

    public static AutoPullTagFriendsToGroupUtils getInstance() {
        instance = new AutoPullTagFriendsToGroupUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.tags = operationParameterModel.getTagListNames();
        this.lastName = "";
        this.nodeText = "";
        this.middleStr = "";
        this.currentTagName = "";
        this.isExist = false;
        this.maxNum = 40;
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.operationFirstNum = 0;
        this.isJumpedStart = true;
        this.isFilled = false;
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.sayContent = operationParameterModel.getSayContent();
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullRecordNum = 0;
        this.lastOperationNum = this.startPullIndex - 1;
        this.scrollNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.scrollSpeed = operationParameterModel.getScrollSpeed();
    }

    public void initFirstChatRoomInfoUI() {
        if (!this.isFilled) {
            LogUtils.log("WS_BABY.initFirstChatRoomInfoUI.1");
            new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, dialog_ok_id, default_list_id, executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void nuFind() {
                }

                public void find(int i) {
                    if (i == 0) {
                        PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                        AutoPullTagFriendsToGroupUtils.this.preChatRoomInfo();
                        return;
                    }
                    AutoPullTagFriendsToGroupUtils.this.preChatRoomInfo();
                }
            });
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_5.END");
        removeEndView(this.mMyManager);
    }

    public void preChatRoomInfo() {
        this.isExist = false;
        new PerformClickUtils2().checkNodeAllIds(this.autoService, default_list_id, list_head_img_id, executeSpeed, (executeSpeedSetting / 10) + 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                AccessibilityNodeInfo rootInActiveWindow;
                while (!AutoPullTagFriendsToGroupUtils.this.isExist && MyApplication.enforceable && (rootInActiveWindow = AutoPullTagFriendsToGroupUtils.this.autoService.getRootInActiveWindow()) != null) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.default_list_id);
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_head_img_id);
                    if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= findAccessibilityNodeInfosByViewId2.size()) {
                                break;
                            }
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i2);
                            if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                                if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员")) {
                                    boolean unused = AutoPullTagFriendsToGroupUtils.this.isExist = true;
                                    PerformClickUtils.performClick(accessibilityNodeInfo);
                                    AutoPullTagFriendsToGroupUtils.this.initFirstSelectContactUI();
                                    break;
                                }
                            }
                            i2++;
                        }
                    }
                    if (!AutoPullTagFriendsToGroupUtils.this.isExist && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                        AutoPullTagFriendsToGroupUtils.this.sleep(MyApplication.SCROLL_SPEED);
                    }
                }
            }
        });
    }

    public void initFirstSelectContactUI() {
        LogUtils.log("WS_BABY.SelectContactUI_2");
        new PerformClickUtils2().check(this.autoService, list_select_search_id, executeSpeedSetting + executeSpeed, 100, 100, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                LogUtils.log("WS_BABY.SelectContactUI_3");
                PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.list_select_search_id);
                new PerformClickUtils2().check(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.tag_view_group_id, (BaseServiceUtils.executeSpeedSetting / 10) + 50, 50, 200, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
                    public void nuFind() {
                    }

                    public void find(int i) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
                        AccessibilityNodeInfo rootInActiveWindow = AutoPullTagFriendsToGroupUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.tag_view_group_id)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(0);
                            for (int i2 = 0; i2 < accessibilityNodeInfo.getChildCount(); i2++) {
                                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
                                AutoPullTagFriendsToGroupUtils autoPullTagFriendsToGroupUtils = AutoPullTagFriendsToGroupUtils.this;
                                String unused = autoPullTagFriendsToGroupUtils.nodeText = child.getText() + "";
                                String unused2 = AutoPullTagFriendsToGroupUtils.this.currentTagName = AutoPullTagFriendsToGroupUtils.this.nodeText;
                                MyWindowManager myWindowManager = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                                BaseServiceUtils.updateBottomText(myWindowManager, "正在拉取标签【 " + AutoPullTagFriendsToGroupUtils.this.currentTagName + " 】中的好友");
                                if (AutoPullTagFriendsToGroupUtils.this.tags.contains(AutoPullTagFriendsToGroupUtils.this.nodeText)) {
                                    PerformClickUtils.performClick(child);
                                    AutoPullTagFriendsToGroupUtils.this.initSelectLabelContactUI();
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    public void initSelectLabelContactUI() {
        this.startIndex = 0;
        excSelectTask();
    }

    public void excSelectTask() {
        updateBottomText(this.mMyManager, "正在执行好友选择操作");
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                int unused = AutoPullTagFriendsToGroupUtils.this.startIndex = 0;
                String unused2 = AutoPullTagFriendsToGroupUtils.this.middleStr = "";
                int unused3 = AutoPullTagFriendsToGroupUtils.this.currentSelectNum = 0;
                AutoPullTagFriendsToGroupUtils.this.whileExecuteTask();
            }
        });
    }

    public void whileExecuteTask() {
        if (MyApplication.enforceable) {
            new Thread(new Runnable() {
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0235 */
                /* JADX WARNING: Removed duplicated region for block: B:50:0x0243 A[Catch:{ Exception -> 0x03a5 }] */
                /* JADX WARNING: Removed duplicated region for block: B:51:0x0277 A[Catch:{ Exception -> 0x03a5 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r11 = this;
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        if (r0 != 0) goto L_0x0014
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x03a5 }
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x03a5 }
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x03a5 }
                    L_0x0014:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        if (r0 == 0) goto L_0x0398
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.size()     // Catch:{ Exception -> 0x03a5 }
                        if (r0 <= 0) goto L_0x0398
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x03a5 }
                        if (r0 == 0) goto L_0x0398
                        java.lang.String r0 = ""
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r2 = r2.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        int r2 = r2.size()     // Catch:{ Exception -> 0x03a5 }
                        r3 = 1
                        int r2 = r2 - r3
                        java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x03a5 }
                        android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x03a5 }
                        if (r1 == 0) goto L_0x0086
                        java.lang.CharSequence r2 = r1.getText()     // Catch:{ Exception -> 0x03a5 }
                        if (r2 == 0) goto L_0x0086
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r2.<init>()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.CharSequence r4 = r1.getText()     // Catch:{ Exception -> 0x03a5 }
                        r2.append(r4)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r4 = ""
                        r2.append(r4)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x03a5 }
                        if (r2 == 0) goto L_0x0066
                        java.lang.String r4 = ""
                        boolean r4 = r4.equals(r2)     // Catch:{ Exception -> 0x03a5 }
                        if (r4 != 0) goto L_0x0066
                        r0 = r2
                    L_0x0066:
                        android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ Exception -> 0x03a5 }
                        r2.<init>()     // Catch:{ Exception -> 0x03a5 }
                        r1.getBoundsInScreen(r2)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r4.<init>()     // Catch:{ Exception -> 0x03a5 }
                        r4.append(r0)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x03a5 }
                        r4.append(r0)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String unused = r1.lastName = r0     // Catch:{ Exception -> 0x03a5 }
                    L_0x0086:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.operationFirstNum     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.maxNum     // Catch:{ Exception -> 0x03a5 }
                        r2 = 0
                        if (r0 != r1) goto L_0x0112
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x03a5 }
                        if (r0 == 0) goto L_0x0112
                        java.lang.String r0 = "WS_BABY_excSelectTask.8"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1 + r4
                        int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x03a5 }
                        android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = "pull_fri_to_group_num_part"
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r4 = r4.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.SPUtils.put(r0, r1, r4)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x03a5 }
                        if (r0 != 0) goto L_0x00fb
                        java.lang.String r0 = "WS_BABY_excSelectTask.88"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.PerformClickUtils.performBack(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r1 = 600(0x258, float:8.41E-43)
                        r0.sleep(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.PerformClickUtils.performBack(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.sleep(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.initFirstChatRoomInfoUI()     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x00fb:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r0 = "WS_BABY_excSelectTask.888"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0112:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.pullRecordNum     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.pullMaxNum     // Catch:{ Exception -> 0x03a5 }
                        if (r0 < r1) goto L_0x0158
                        java.lang.String r0 = "WS_BABY_excSelectTask.9"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        boolean unused = r0.isFilled = r3     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1 + r4
                        int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0158:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        if (r0 != 0) goto L_0x016c
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x03a5 }
                        java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x03a5 }
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x03a5 }
                    L_0x016c:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.startIndex     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.size()     // Catch:{ Exception -> 0x03a5 }
                        if (r0 >= r1) goto L_0x0339
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r0.<init>()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.startIndex = "
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startIndex     // Catch:{ Exception -> 0x03a5 }
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = ",namesize = "
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.size()     // Catch:{ Exception -> 0x03a5 }
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r0.<init>()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = "WS_BABY_excSelectTask.slast = "
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = "，，，pull = "
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.pullRecordNum     // Catch:{ Exception -> 0x03a5 }
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = "，，，start = "
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x03a5 }
                        r0.append(r1)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x0235 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x0235 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x0235 }
                        int r1 = r1.startIndex     // Catch:{ Exception -> 0x0235 }
                        java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x0235 }
                        android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0     // Catch:{ Exception -> 0x0235 }
                        if (r0 == 0) goto L_0x0235
                        java.lang.CharSequence r1 = r0.getText()     // Catch:{ Exception -> 0x0235 }
                        if (r1 == 0) goto L_0x0235
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0235 }
                        r1.<init>()     // Catch:{ Exception -> 0x0235 }
                        java.lang.CharSequence r0 = r0.getText()     // Catch:{ Exception -> 0x0235 }
                        r1.append(r0)     // Catch:{ Exception -> 0x0235 }
                        java.lang.String r0 = ""
                        r1.append(r0)     // Catch:{ Exception -> 0x0235 }
                        java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0235 }
                        if (r0 == 0) goto L_0x0235
                        java.lang.String r1 = ""
                        boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0235 }
                        if (r1 != 0) goto L_0x0235
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0235 }
                        r1.<init>()     // Catch:{ Exception -> 0x0235 }
                        java.lang.String r2 = "WS_BABY_excSelectTask.name = "
                        r1.append(r2)     // Catch:{ Exception -> 0x0235 }
                        r1.append(r0)     // Catch:{ Exception -> 0x0235 }
                        java.lang.String r0 = ",isJumpedStart = "
                        r1.append(r0)     // Catch:{ Exception -> 0x0235 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x0235 }
                        boolean r0 = r0.isJumpedStart     // Catch:{ Exception -> 0x0235 }
                        r1.append(r0)     // Catch:{ Exception -> 0x0235 }
                        java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0235 }
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0235 }
                    L_0x0235:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.scrollNum     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        if (r0 >= r1) goto L_0x0277
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$408(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$1308(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x03a5 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r1.<init>()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = "正在分批拉好友,已跳过 "
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r2 = r2.scrollNum     // Catch:{ Exception -> 0x03a5 }
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = " 个"
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0277:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_checkbox     // Catch:{ Exception -> 0x03a5 }
                        java.util.List r0 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x03a5 }
                        if (r0 == 0) goto L_0x031f
                        int r1 = r0.size()     // Catch:{ Exception -> 0x03a5 }
                        if (r1 <= 0) goto L_0x031f
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startIndex     // Catch:{ Exception -> 0x03a5 }
                        int r2 = r0.size()     // Catch:{ Exception -> 0x03a5 }
                        if (r1 >= r2) goto L_0x031f
                        java.lang.String r1 = "WS_BABY_excSelectTask.0"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.startIndex     // Catch:{ Exception -> 0x03a5 }
                        java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x03a5 }
                        android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0     // Catch:{ Exception -> 0x03a5 }
                        if (r0 == 0) goto L_0x0309
                        boolean r1 = r0.isChecked()     // Catch:{ Exception -> 0x03a5 }
                        if (r1 == 0) goto L_0x02af
                        goto L_0x0309
                    L_0x02af:
                        java.lang.String r1 = "WS_BABY_excSelectTask.2"
                        com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$408(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$1208(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$808(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$608(r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r2 = 5
                        r1.sleep(r2)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x03a5 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a5 }
                        r1.<init>()     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = "正在分批拉好友\n已执行 "
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r2 = r2.operationFirstNum     // Catch:{ Exception -> 0x03a5 }
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = " 个，已选择 "
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r2 = r2.currentSelectNum     // Catch:{ Exception -> 0x03a5 }
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r2 = " 个"
                        r1.append(r2)     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0309:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$408(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$1208(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$808(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x031f:
                        java.lang.String r0 = "WS_BABY_excSelectTask.7"
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$408(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$1208(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.access$808(r0)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r0.whileExecuteTask()     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0339:
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.startIndex     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.size()     // Catch:{ Exception -> 0x03a5 }
                        if (r0 < r1) goto L_0x03a5
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        r1 = 0
                        r0.nameNodes = r1     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_id     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.PerformClickUtils.scroll(r0, r1)     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int unused = r0.startIndex = r3     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.utils.PerformClickUtils2 r4 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x03a5 }
                        r4.<init>()     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service.AutoService r5 = r0.autoService     // Catch:{ Exception -> 0x03a5 }
                        java.lang.String r6 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x03a5 }
                        if (r0 != 0) goto L_0x0388
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.lastOperationNum     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r1 = r1.scrollNum     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0 - r1
                        r1 = 30
                        if (r0 <= r1) goto L_0x0388
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.this     // Catch:{ Exception -> 0x03a5 }
                        int r0 = r0.scrollSpeed     // Catch:{ Exception -> 0x03a5 }
                        r7 = r0
                        goto L_0x038c
                    L_0x0388:
                        r0 = 350(0x15e, float:4.9E-43)
                        r7 = 350(0x15e, float:4.9E-43)
                    L_0x038c:
                        r8 = 5
                        r9 = 200(0xc8, float:2.8E-43)
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils$5$1 r10 = new com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils$5$1     // Catch:{ Exception -> 0x03a5 }
                        r10.<init>()     // Catch:{ Exception -> 0x03a5 }
                        r4.check((com.wx.assistants.service.AutoService) r5, (java.lang.String) r6, (int) r7, (int) r8, (int) r9, (com.wx.assistants.utils.PerformClickUtils2.OnCheckListener) r10)     // Catch:{ Exception -> 0x03a5 }
                        goto L_0x03a5
                    L_0x0398:
                        java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x03a5 }
                        com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils$5$2 r1 = new com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils$5$2     // Catch:{ Exception -> 0x03a5 }
                        r1.<init>()     // Catch:{ Exception -> 0x03a5 }
                        r0.<init>(r1)     // Catch:{ Exception -> 0x03a5 }
                        r0.start()     // Catch:{ Exception -> 0x03a5 }
                    L_0x03a5:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoPullTagFriendsToGroupUtils.AnonymousClass5.run():void");
                }
            }).start();
        }
    }

    public void confirmCompleted(int i) {
        SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_part", Integer.valueOf(i));
        int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "pull_fri_to_group_num_part", 1)).intValue();
        LogUtils.log("WS_BABY_" + this.lastOperationNum + ListUtils.DEFAULT_JOIN_SEPARATOR + intValue);
        sleep(100);
        updateBottomText(this.mMyManager, "正在拉人进群\n正在发起进群邀请");
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        sleep(BannerConfig.DURATION);
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        backChatRoomInfoUI();
    }

    public void backChatRoomInfoUI() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加联系人", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new PerformClickUtils2().check(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + 100, (int) SocializeConstants.CANCLE_RESULTCODE, 4, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void nuFind() {
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        if (PerformClickUtils.findNodeIsExistByText(AutoPullTagFriendsToGroupUtils.this.autoService, "操作太频繁")) {
                            BaseServiceUtils.removeEndView(AutoPullTagFriendsToGroupUtils.this.mMyManager);
                            return;
                        }
                        if (AutoPullTagFriendsToGroupUtils.this.sayContent != null && !"".equals(AutoPullTagFriendsToGroupUtils.this.sayContent)) {
                            PerformClickUtils.findViewByIdAndPasteContent(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullTagFriendsToGroupUtils.this.sayContent);
                            AutoPullTagFriendsToGroupUtils.this.sleep(100);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    }

                    public void end() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                        new PerformClickUtils2().checkLoad(AutoPullTagFriendsToGroupUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                AutoPullTagFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                            }
                        });
                    }

                    public void executeIndex(int i) {
                        if (AutoPullTagFriendsToGroupUtils.this.isFilled) {
                            MyWindowManager myWindowManager = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "邀请好友即将结束，倒计时 " + i + " 秒");
                            return;
                        }
                        MyWindowManager myWindowManager2 = AutoPullTagFriendsToGroupUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager2, "执行下一个批，倒计时 " + i + " 秒");
                    }
                });
            }
        });
    }

    public void executeMain() {
        try {
            LogUtils.log("WS_BABY_executeMain.0");
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain.1");
                    PerformClickUtils.findViewIdAndClick(AutoPullTagFriendsToGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                    AutoPullTagFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                }

                public void nuFind() {
                    LogUtils.log("WS_BABY_executeMain.2");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        updateMiddleText(this.mMyManager, "自动拉人进群", this.middleStr == "" ? "完成拉人进群邀请！" : this.middleStr);
    }

    public void endViewShow() {
        showBottomView(this.mMyManager, "正在拉人进群，请不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    AutoPullTagFriendsToGroupUtils.this.executeMain();
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
}
