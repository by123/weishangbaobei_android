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
import com.yalantis.ucrop.view.CropImageView;
import java.util.LinkedHashSet;
import java.util.List;

public class AutoPullFriendsToGroupUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static AutoPullFriendsToGroupUtils instance;
    /* access modifiers changed from: private */
    public int currentSelectNum = 0;
    /* access modifiers changed from: private */
    public boolean isFilled = false;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
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
    public int operationFirstNum = 0;
    /* access modifiers changed from: private */
    public int pullMaxNum = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    /* access modifiers changed from: private */
    public int pullRecordNum = 0;
    /* access modifiers changed from: private */
    public int pullType = 0;
    /* access modifiers changed from: private */
    public String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int scrollSpeed = 150;
    /* access modifiers changed from: private */
    public int shieldNum = 0;
    /* access modifiers changed from: private */
    public int startIndex = 0;
    /* access modifiers changed from: private */
    public int startPullIndex = 1;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$008(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.startIndex;
        autoPullFriendsToGroupUtils.startIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1008(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.scrollNum;
        autoPullFriendsToGroupUtils.scrollNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.shieldNum;
        autoPullFriendsToGroupUtils.shieldNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.currentSelectNum;
        autoPullFriendsToGroupUtils.currentSelectNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.operationFirstNum;
        autoPullFriendsToGroupUtils.operationFirstNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AutoPullFriendsToGroupUtils autoPullFriendsToGroupUtils) {
        int i = autoPullFriendsToGroupUtils.pullRecordNum;
        autoPullFriendsToGroupUtils.pullRecordNum = i + 1;
        return i;
    }

    private AutoPullFriendsToGroupUtils() {
    }

    public static AutoPullFriendsToGroupUtils getInstance() {
        instance = new AutoPullFriendsToGroupUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.lastName = "";
        this.maxNum = 40;
        this.pullRecordNum = 0;
        this.startPullIndex = operationParameterModel.getStartIndex();
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullType = operationParameterModel.getAutoPullType();
        this.jumpPersons = operationParameterModel.getTagListPeoples();
        this.operationFirstNum = 0;
        this.isFilled = false;
        this.sayContent = operationParameterModel.getSayContent();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
        this.pullMaxNum = operationParameterModel.getMaxOperaNum();
        this.pullRecordNum = 0;
        this.lastOperationNum = this.startPullIndex - 1;
        this.scrollNum = 0;
        this.currentSelectNum = 0;
        this.startIndex = 0;
        this.shieldNum = 0;
        this.scrollSpeed = operationParameterModel.getScrollSpeed();
    }

    public void initFirstChatRoomInfoUI() {
        if (this.isFilled || !MyApplication.enforceable) {
            LogUtils.log("WS_BABY.ChatroomInfoUI_4.END");
            removeEndView(this.mMyManager);
            return;
        }
        LogUtils.log("WS_BABY.ChatroomInfoUI_0");
        new PerformClickUtils2().checkNodeIdsHasSomeOne(this.autoService, dialog_ok_id, default_list_id, executeSpeedSetting + executeSpeed, 100, 100, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                if (i == 0) {
                    PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    AutoPullFriendsToGroupUtils.this.preChatRoomInfo();
                    return;
                }
                AutoPullFriendsToGroupUtils.this.preChatRoomInfo();
            }
        });
    }

    public void preChatRoomInfo() {
        boolean z = false;
        while (!z && MyApplication.enforceable) {
            AccessibilityNodeInfo rootInActiveWindow = this.autoService.getRootInActiveWindow();
            if (rootInActiveWindow == null || !MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_3");
                return;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(default_list_id);
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(list_head_img_id);
            if (findAccessibilityNodeInfosByViewId2 != null && findAccessibilityNodeInfosByViewId2.size() > 0 && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_1");
                int i = 0;
                while (true) {
                    if (i >= findAccessibilityNodeInfosByViewId2.size() || !MyApplication.enforceable) {
                        break;
                    }
                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId2.get(i);
                    if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getContentDescription() == null)) {
                        if ((accessibilityNodeInfo.getContentDescription() + "").equals("添加成员") && MyApplication.enforceable) {
                            PerformClickUtils.performClick(accessibilityNodeInfo);
                            excSelectTask();
                            z = true;
                            break;
                        }
                    }
                    i++;
                }
            }
            if (!z && MyApplication.enforceable && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && MyApplication.enforceable) {
                LogUtils.log("WS_BABY.ChatroomInfoUI_2");
                findAccessibilityNodeInfosByViewId.get(0).performAction(4096);
                sleep(MyApplication.SCROLL_SPEED);
            }
        }
    }

    public void excSelectTask() {
        new PerformClickUtils2().checkNodeAllIds(this.autoService, list_select_name_id, list_select_checkbox, executeSpeed + executeSpeedSetting, 100, 300, new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                int unused = AutoPullFriendsToGroupUtils.this.startIndex = 0;
                String unused2 = AutoPullFriendsToGroupUtils.this.middleStr = "";
                int unused3 = AutoPullFriendsToGroupUtils.this.currentSelectNum = 0;
                AutoPullFriendsToGroupUtils.this.whileExecuteTask();
            }
        });
    }

    public void whileExecuteTask() {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0288 */
            /* JADX WARNING: Removed duplicated region for block: B:58:0x0296 A[Catch:{ Exception -> 0x04d7 }] */
            /* JADX WARNING: Removed duplicated region for block: B:61:0x0302 A[Catch:{ Exception -> 0x04d7 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r9 = this;
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time1_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x002e
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x04d7 }
                    java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x04d7 }
                    r0.nameNodes = r1     // Catch:{ Exception -> 0x04d7 }
                L_0x002e:
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time3_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04ca
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.size()     // Catch:{ Exception -> 0x04d7 }
                    if (r0 <= 0) goto L_0x04ca
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04ca
                    java.lang.String r0 = ""
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r2 = r2.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.size()     // Catch:{ Exception -> 0x04d7 }
                    r3 = 1
                    int r2 = r2 - r3
                    java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x04d7 }
                    android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x04d7 }
                    if (r1 == 0) goto L_0x00ba
                    java.lang.CharSequence r2 = r1.getText()     // Catch:{ Exception -> 0x04d7 }
                    if (r2 == 0) goto L_0x00ba
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r2.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.CharSequence r4 = r1.getText()     // Catch:{ Exception -> 0x04d7 }
                    r2.append(r4)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r4 = ""
                    r2.append(r4)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x04d7 }
                    if (r2 == 0) goto L_0x009a
                    java.lang.String r4 = ""
                    boolean r4 = r4.equals(r2)     // Catch:{ Exception -> 0x04d7 }
                    if (r4 != 0) goto L_0x009a
                    r0 = r2
                L_0x009a:
                    android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ Exception -> 0x04d7 }
                    r2.<init>()     // Catch:{ Exception -> 0x04d7 }
                    r1.getBoundsInScreen(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r4.<init>()     // Catch:{ Exception -> 0x04d7 }
                    r4.append(r0)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x04d7 }
                    r4.append(r0)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String unused = r1.lastName = r0     // Catch:{ Exception -> 0x04d7 }
                L_0x00ba:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.operationFirstNum     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x04d7 }
                    r2 = 0
                    if (r0 != r1) goto L_0x0156
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x0156
                    java.lang.String r0 = "WS_BABY_excSelectTask.8"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1 + r4
                    int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.pullType     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x0100
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "pull_fri_to_group_num_all"
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r4 = r4.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r4)     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x0113
                L_0x0100:
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "pull_fri_to_group_num_shield"
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r4 = r4.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r4)     // Catch:{ Exception -> 0x04d7 }
                L_0x0113:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x013f
                    java.lang.String r0 = "WS_BABY_excSelectTask.88"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.PerformClickUtils.performBack(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r1 = 600(0x258, float:8.41E-43)
                    r0.sleep(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r0.initFirstChatRoomInfoUI()     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x013f:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = "WS_BABY_excSelectTask.888"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x0156:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.pullRecordNum     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.pullMaxNum     // Catch:{ Exception -> 0x04d7 }
                    if (r0 < r1) goto L_0x019c
                    java.lang.String r0 = "WS_BABY_excSelectTask.9"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    boolean unused = r0.isFilled = r3     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r4 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r4 = r4.pullRecordNum     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1 + r4
                    int unused = r0.lastOperationNum = r1     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.scrollNum = r3     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.operationFirstNum = r2     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.currentSelectNum = r2     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    r0.confirmCompleted(r1)     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x019c:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r0 = r0.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x01b0
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x04d7 }
                    java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x04d7 }
                    r0.nameNodes = r1     // Catch:{ Exception -> 0x04d7 }
                L_0x01b0:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.startIndex     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.size()     // Catch:{ Exception -> 0x04d7 }
                    if (r0 >= r1) goto L_0x0451
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time4_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r0.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "WS_BABY_excSelectTask.startIndex = "
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.startIndex     // Catch:{ Exception -> 0x04d7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = ",namesize = "
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.size()     // Catch:{ Exception -> 0x04d7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r0.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "WS_BABY_excSelectTask.slast = "
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "，，，pull = "
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.pullRecordNum     // Catch:{ Exception -> 0x04d7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = "，，，start = "
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.startPullIndex     // Catch:{ Exception -> 0x04d7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r0 = ""
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x0288 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x0288 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x0288 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x0288 }
                    java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x0288 }
                    android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x0288 }
                    if (r1 == 0) goto L_0x0288
                    java.lang.CharSequence r2 = r1.getText()     // Catch:{ Exception -> 0x0288 }
                    if (r2 == 0) goto L_0x0288
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0288 }
                    r2.<init>()     // Catch:{ Exception -> 0x0288 }
                    java.lang.CharSequence r1 = r1.getText()     // Catch:{ Exception -> 0x0288 }
                    r2.append(r1)     // Catch:{ Exception -> 0x0288 }
                    java.lang.String r1 = ""
                    r2.append(r1)     // Catch:{ Exception -> 0x0288 }
                    java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0288 }
                    if (r1 == 0) goto L_0x0287
                    java.lang.String r0 = ""
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0287 }
                    if (r0 != 0) goto L_0x0287
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0287 }
                    r0.<init>()     // Catch:{ Exception -> 0x0287 }
                    java.lang.String r2 = "WS_BABY_excSelectTask.name = "
                    r0.append(r2)     // Catch:{ Exception -> 0x0287 }
                    r0.append(r1)     // Catch:{ Exception -> 0x0287 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0287 }
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0287 }
                L_0x0287:
                    r0 = r1
                L_0x0288:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.scrollNum     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    if (r1 >= r2) goto L_0x0302
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$008(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$1008(r0)     // Catch:{ Exception -> 0x04d7 }
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time5_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "正在分批拉好友进群\n已跳过 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.scrollNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x04d7 }
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time6_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04d7
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r0.whileExecuteTask()     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x0302:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r1 = r1.autoService     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_checkbox     // Catch:{ Exception -> 0x04d7 }
                    java.util.List r1 = com.wx.assistants.utils.PerformClickUtils.findViewIdList((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x04d7 }
                    java.io.PrintStream r2 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r3.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r4 = "WS_BABY_time7_"
                    r3.append(r4)     // Catch:{ Exception -> 0x04d7 }
                    long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r3.append(r4)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x04d7 }
                    r2.println(r3)     // Catch:{ Exception -> 0x04d7 }
                    if (r1 == 0) goto L_0x0432
                    int r2 = r1.size()     // Catch:{ Exception -> 0x04d7 }
                    if (r2 <= 0) goto L_0x0432
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x04d7 }
                    int r3 = r1.size()     // Catch:{ Exception -> 0x04d7 }
                    if (r2 >= r3) goto L_0x0432
                    java.lang.String r2 = "WS_BABY_excSelectTask.0"
                    com.wx.assistants.utils.LogUtils.log(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.startIndex     // Catch:{ Exception -> 0x04d7 }
                    java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x04d7 }
                    android.view.accessibility.AccessibilityNodeInfo r1 = (android.view.accessibility.AccessibilityNodeInfo) r1     // Catch:{ Exception -> 0x04d7 }
                    if (r1 == 0) goto L_0x0407
                    boolean r2 = r1.isChecked()     // Catch:{ Exception -> 0x04d7 }
                    if (r2 != 0) goto L_0x0407
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.LinkedHashSet r2 = r2.jumpPersons     // Catch:{ Exception -> 0x04d7 }
                    boolean r2 = r2.contains(r0)     // Catch:{ Exception -> 0x04d7 }
                    if (r2 == 0) goto L_0x0361
                    goto L_0x0407
                L_0x0361:
                    java.lang.String r0 = "WS_BABY_excSelectTask.2"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$008(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$808(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$408(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$208(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r2 = 5
                    r0.sleep(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.PerformClickUtils.performClick(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.pullType     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x03bd
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "正在分批拉好友进群\n已执行 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.operationFirstNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个，已选择 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.currentSelectNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x03fc
                L_0x03bd:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "正在分批拉好友进群\n已执行 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.operationFirstNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个，已选择 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.currentSelectNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个 ，已屏蔽 "
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r2 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r2 = r2.shieldNum     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = " 个"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r1)     // Catch:{ Exception -> 0x04d7 }
                L_0x03fc:
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04d7
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r0.whileExecuteTask()     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x0407:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$008(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$808(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$408(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.LinkedHashSet r1 = r1.jumpPersons     // Catch:{ Exception -> 0x04d7 }
                    boolean r0 = r1.contains(r0)     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x0427
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$1408(r0)     // Catch:{ Exception -> 0x04d7 }
                L_0x0427:
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04d7
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r0.whileExecuteTask()     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x0432:
                    java.lang.String r0 = "WS_BABY_excSelectTask.7"
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$008(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$808(r0)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.access$408(r0)     // Catch:{ Exception -> 0x04d7 }
                    boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x04d7 }
                    if (r0 == 0) goto L_0x04d7
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r0.whileExecuteTask()     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x0451:
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.startIndex     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    java.util.List<android.view.accessibility.AccessibilityNodeInfo> r1 = r1.nameNodes     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.size()     // Catch:{ Exception -> 0x04d7 }
                    if (r0 < r1) goto L_0x04d7
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    r1 = 0
                    r0.nameNodes = r1     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_id     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.PerformClickUtils.scroll(r0, r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int unused = r0.startIndex = r3     // Catch:{ Exception -> 0x04d7 }
                    java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x04d7 }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r2 = "WS_BABY_time8_"
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x04d7 }
                    r1.append(r2)     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x04d7 }
                    r0.println(r1)     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.utils.PerformClickUtils2 r2 = new com.wx.assistants.utils.PerformClickUtils2     // Catch:{ Exception -> 0x04d7 }
                    r2.<init>()     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service.AutoService r3 = r0.autoService     // Catch:{ Exception -> 0x04d7 }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.list_select_name_id     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.currentSelectNum     // Catch:{ Exception -> 0x04d7 }
                    if (r0 != 0) goto L_0x04ba
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.lastOperationNum     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r1 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r1 = r1.scrollNum     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0 - r1
                    r1 = 30
                    if (r0 <= r1) goto L_0x04ba
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils r0 = com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.this     // Catch:{ Exception -> 0x04d7 }
                    int r0 = r0.scrollSpeed     // Catch:{ Exception -> 0x04d7 }
                    r5 = r0
                    goto L_0x04be
                L_0x04ba:
                    r0 = 350(0x15e, float:4.9E-43)
                    r5 = 350(0x15e, float:4.9E-43)
                L_0x04be:
                    r6 = 5
                    r7 = 200(0xc8, float:2.8E-43)
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils$3$1 r8 = new com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils$3$1     // Catch:{ Exception -> 0x04d7 }
                    r8.<init>()     // Catch:{ Exception -> 0x04d7 }
                    r2.check((com.wx.assistants.service.AutoService) r3, (java.lang.String) r4, (int) r5, (int) r6, (int) r7, (com.wx.assistants.utils.PerformClickUtils2.OnCheckListener) r8)     // Catch:{ Exception -> 0x04d7 }
                    goto L_0x04d7
                L_0x04ca:
                    java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x04d7 }
                    com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils$3$2 r1 = new com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils$3$2     // Catch:{ Exception -> 0x04d7 }
                    r1.<init>()     // Catch:{ Exception -> 0x04d7 }
                    r0.<init>(r1)     // Catch:{ Exception -> 0x04d7 }
                    r0.start()     // Catch:{ Exception -> 0x04d7 }
                L_0x04d7:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.AutoPullFriendsToGroupUtils.AnonymousClass3.run():void");
            }
        }).start();
    }

    public void confirmCompleted(int i) {
        if (this.pullType == 0) {
            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_all", Integer.valueOf(i));
        } else {
            SPUtils.put(MyApplication.getConText(), "pull_fri_to_group_num_shield", Integer.valueOf(i));
        }
        sleep(100);
        updateBottomText(this.mMyManager, "正在分批拉好友进群\n正在发起进群邀请");
        PerformClickUtils.findViewIdAndClick(this.autoService, complete_id);
        backChatRoomInfoUI();
    }

    public void backChatRoomInfoUI() {
        new PerformClickUtils2().checkNilString(this.autoService, "正在添加联系人", (executeSpeedSetting / 8) + SocializeConstants.CANCLE_RESULTCODE, 100, 600, (PerformClickUtils2.OnCheckListener) new PerformClickUtils2.OnCheckListener() {
            public void nuFind() {
            }

            public void find(int i) {
                new PerformClickUtils2().check(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id, (BaseServiceUtils.executeSpeedSetting / 10) + 100, (int) SocializeConstants.CANCLE_RESULTCODE, 4, false, (PerformClickUtils2.OnCheckListener2) new PerformClickUtils2.OnCheckListener2() {
                    public void nuFind() {
                    }

                    public void find() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.000");
                        if (PerformClickUtils.findNodeIsExistByText(AutoPullFriendsToGroupUtils.this.autoService, "操作太频繁")) {
                            BaseServiceUtils.removeEndView(AutoPullFriendsToGroupUtils.this.mMyManager);
                            return;
                        }
                        if (AutoPullFriendsToGroupUtils.this.sayContent != null && !"".equals(AutoPullFriendsToGroupUtils.this.sayContent)) {
                            PerformClickUtils.findViewByIdAndPasteContent(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.toast_edit_id, AutoPullFriendsToGroupUtils.this.sayContent);
                            AutoPullFriendsToGroupUtils.this.sleep(100);
                        }
                        PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.dialog_ok_id);
                    }

                    public void end() {
                        LogUtils.log("WS_BABY.com.tencent.mm.ui.widget.a.c.end");
                        new PerformClickUtils2().checkLoad(AutoPullFriendsToGroupUtils.this.autoService, "正在发送", 0, 100, 100, new PerformClickUtils2.OnCheckListener() {
                            public void nuFind() {
                            }

                            public void find(int i) {
                                AutoPullFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
                            }
                        });
                    }

                    public void executeIndex(int i) {
                        if (AutoPullFriendsToGroupUtils.this.isFilled) {
                            MyWindowManager myWindowManager = AutoPullFriendsToGroupUtils.this.mMyManager;
                            BaseServiceUtils.updateBottomText(myWindowManager, "邀请好友即将结束，倒计时 " + i + " 秒");
                            return;
                        }
                        MyWindowManager myWindowManager2 = AutoPullFriendsToGroupUtils.this.mMyManager;
                        BaseServiceUtils.updateBottomText(myWindowManager2, "执行下一个批，倒计时 " + i + " 秒");
                    }
                });
            }
        });
    }

    public void executeMain() {
        try {
            new PerformClickUtils2().checkNodeId(this.autoService, right_more_id, executeSpeed + executeSpeedSetting, 100, 100, new PerformClickUtils2.OnCheckListener() {
                public void find(int i) {
                    LogUtils.log("WS_BABY_executeMain.1");
                    PerformClickUtils.findViewIdAndClick(AutoPullFriendsToGroupUtils.this.autoService, BaseServiceUtils.right_more_id);
                    AutoPullFriendsToGroupUtils.this.initFirstChatRoomInfoUI();
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
        if (this.middleStr == null || "".equals(this.middleStr)) {
            MyWindowManager myWindowManager = this.mMyManager;
            StringBuilder sb = new StringBuilder();
            sb.append("本次共向");
            sb.append(this.lastOperationNum > 0 ? this.lastOperationNum - this.startPullIndex : 0);
            sb.append("个好友，发起了进群邀请！");
            updateMiddleText(myWindowManager, "自动拉人进群", sb.toString());
            return;
        }
        updateMiddleText(this.mMyManager, "自动拉人进群", this.middleStr);
    }

    public void endViewShow() {
        MyWindowManager myWindowManager = this.mMyManager;
        showBottomView(myWindowManager, "从第 " + this.startPullIndex + " 个好友开始拉人进群\n请您不要操作微信");
        new Thread(new Runnable() {
            public void run() {
                try {
                    AutoPullFriendsToGroupUtils.this.executeMain();
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
