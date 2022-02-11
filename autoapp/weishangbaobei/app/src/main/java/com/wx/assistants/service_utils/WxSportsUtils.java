package com.wx.assistants.service_utils;

import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import java.util.LinkedHashSet;

public class WxSportsUtils extends BaseServiceUtils implements MyWindowManager.MiddleViewListener, MyWindowManager.EndViewListener {
    private static WxSportsUtils instance;
    /* access modifiers changed from: private */
    public int clickNum = 0;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> friendNames = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public boolean isEnd;
    /* access modifiers changed from: private */
    public boolean isFirstScroll = true;
    /* access modifiers changed from: private */
    public boolean isTopEnd = true;
    /* access modifiers changed from: private */
    public String jumpFriendNames = "";
    /* access modifiers changed from: private */
    public String jumpSelfName;
    /* access modifiers changed from: private */
    public int minimumStep = 1;
    /* access modifiers changed from: private */
    public int scrollNum = 0;
    /* access modifiers changed from: private */
    public int wechatSportsType = 0;

    public void middleViewDismiss() {
    }

    static /* synthetic */ int access$608(WxSportsUtils wxSportsUtils) {
        int i = wxSportsUtils.clickNum;
        wxSportsUtils.clickNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(WxSportsUtils wxSportsUtils) {
        int i = wxSportsUtils.scrollNum;
        wxSportsUtils.scrollNum = i + 1;
        return i;
    }

    private WxSportsUtils() {
    }

    public static WxSportsUtils getInstance() {
        instance = new WxSportsUtils();
        return instance;
    }

    public void initData(OperationParameterModel operationParameterModel) {
        this.isEnd = false;
        this.isFirstScroll = true;
        this.jumpSelfName = "";
        this.clickNum = 0;
        this.scrollNum = 0;
        this.isTopEnd = true;
        this.minimumStep = operationParameterModel.getMinimumStep();
        this.wechatSportsType = operationParameterModel.getWechatSportsType();
        this.jumpFriendNames = operationParameterModel.getJumpFriendNames();
        this.friendNames = operationParameterModel.getFriendsNameSet();
        this.mMyManager.setMiddleViewListener(this);
        this.mMyManager.setEndViewListener(this);
    }

    public void executeMain() {
        try {
            new Thread(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:58:0x0136 A[SYNTHETIC, Splitter:B:58:0x0136] */
                /* JADX WARNING: Removed duplicated region for block: B:82:0x0192 A[Catch:{ Exception -> 0x02b2 }] */
                /* JADX WARNING: Removed duplicated region for block: B:86:0x01da A[Catch:{ Exception -> 0x02b2 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r17 = this;
                        r1 = r17
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0305 }
                        android.view.accessibility.AccessibilityNodeInfo r2 = r0.getRootInActiveWindow()     // Catch:{ Exception -> 0x0305 }
                        if (r2 == 0) goto L_0x0305
                    L_0x000c:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean r0 = r0.isTopEnd     // Catch:{ Exception -> 0x0305 }
                        r3 = 0
                        if (r0 == 0) goto L_0x004b
                        java.lang.String r0 = com.wx.assistants.service_utils.BaseServiceUtils.sport_list_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r0 = r2.findAccessibilityNodeInfosByViewId(r0)     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x000c
                        int r4 = r0.size()     // Catch:{ Exception -> 0x0305 }
                        if (r4 <= 0) goto L_0x000c
                        java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x0305 }
                        android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x000c
                        java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.cover_the_cover_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r4 = r2.findAccessibilityNodeInfosByViewId(r4)     // Catch:{ Exception -> 0x0305 }
                        if (r4 == 0) goto L_0x0040
                        int r4 = r4.size()     // Catch:{ Exception -> 0x0305 }
                        if (r4 != 0) goto L_0x003a
                        goto L_0x0040
                    L_0x003a:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean unused = r0.isTopEnd = r3     // Catch:{ Exception -> 0x0305 }
                        goto L_0x000c
                    L_0x0040:
                        com.wx.assistants.utils.PerformClickUtils.scrollTop(r0)     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        r3 = 100
                        r0.sleep(r3)     // Catch:{ Exception -> 0x0305 }
                        goto L_0x000c
                    L_0x004b:
                        r4 = 0
                    L_0x004c:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean r0 = r0.isEnd     // Catch:{ Exception -> 0x0305 }
                        if (r0 != 0) goto L_0x0305
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x0305
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service.AutoService r0 = r0.autoService     // Catch:{ Exception -> 0x0305 }
                        android.view.accessibility.AccessibilityNodeInfo r0 = r0.getRootInActiveWindow()     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x0305
                        java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.sport_list_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r5 = r0.findAccessibilityNodeInfosByViewId(r5)     // Catch:{ Exception -> 0x0305 }
                        r6 = 1
                        if (r5 == 0) goto L_0x004c
                        int r7 = r5.size()     // Catch:{ Exception -> 0x0305 }
                        if (r7 <= 0) goto L_0x004c
                        java.lang.Object r5 = r5.get(r3)     // Catch:{ Exception -> 0x0305 }
                        android.view.accessibility.AccessibilityNodeInfo r5 = (android.view.accessibility.AccessibilityNodeInfo) r5     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r7 = com.wx.assistants.service_utils.BaseServiceUtils.list_item_nickname_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r7 = r2.findAccessibilityNodeInfosByViewId(r7)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r8 = com.wx.assistants.service_utils.BaseServiceUtils.list_item_ranking_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r8 = r0.findAccessibilityNodeInfosByViewId(r8)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r9 = com.wx.assistants.service_utils.BaseServiceUtils.list_item_zan_img_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r9 = r0.findAccessibilityNodeInfosByViewId(r9)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r10 = com.wx.assistants.service_utils.BaseServiceUtils.list_item_step_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r10 = r0.findAccessibilityNodeInfosByViewId(r10)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r11 = com.wx.assistants.service_utils.BaseServiceUtils.list_item_nickname_id     // Catch:{ Exception -> 0x0305 }
                        java.util.List r11 = r0.findAccessibilityNodeInfosByViewId(r11)     // Catch:{ Exception -> 0x0305 }
                        if (r7 == 0) goto L_0x00b8
                        int r0 = r7.size()     // Catch:{ Exception -> 0x0305 }
                        if (r0 <= 0) goto L_0x00b8
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean r0 = r0.isFirstScroll     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x00b8
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        java.lang.Object r12 = r7.get(r3)     // Catch:{ Exception -> 0x0305 }
                        android.view.accessibility.AccessibilityNodeInfo r12 = (android.view.accessibility.AccessibilityNodeInfo) r12     // Catch:{ Exception -> 0x0305 }
                        java.lang.CharSequence r12 = r12.getText()     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x0305 }
                        java.lang.String unused = r0.jumpSelfName = r12     // Catch:{ Exception -> 0x0305 }
                    L_0x00b8:
                        r12 = r4
                    L_0x00b9:
                        int r0 = r9.size()     // Catch:{ Exception -> 0x0305 }
                        if (r12 >= r0) goto L_0x02bc
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0305 }
                        r0.<init>()     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r13 = "WS_BABY_"
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        int r13 = r9.size()     // Catch:{ Exception -> 0x0305 }
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r13 = "#list"
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        int r13 = r7.size()     // Catch:{ Exception -> 0x0305 }
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r13 = "#setp"
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        int r13 = r10.size()     // Catch:{ Exception -> 0x0305 }
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r13 = "#name"
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        int r13 = r11.size()     // Catch:{ Exception -> 0x0305 }
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r13 = "@index"
                        r0.append(r13)     // Catch:{ Exception -> 0x0305 }
                        r0.append(r4)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x0305 }
                        boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0305 }
                        if (r0 != 0) goto L_0x0108
                        return
                    L_0x0108:
                        java.lang.Object r0 = r9.get(r12)     // Catch:{ Exception -> 0x02b2 }
                        android.view.accessibility.AccessibilityNodeInfo r0 = (android.view.accessibility.AccessibilityNodeInfo) r0     // Catch:{ Exception -> 0x02b2 }
                        if (r10 == 0) goto L_0x0131
                        int r13 = r10.size()     // Catch:{ Exception -> 0x02b2 }
                        if (r13 <= r12) goto L_0x0131
                        java.lang.Object r13 = r10.get(r12)     // Catch:{ Exception -> 0x02b2 }
                        android.view.accessibility.AccessibilityNodeInfo r13 = (android.view.accessibility.AccessibilityNodeInfo) r13     // Catch:{ Exception -> 0x02b2 }
                        if (r13 == 0) goto L_0x0131
                        java.lang.CharSequence r14 = r13.getText()     // Catch:{ Exception -> 0x02b2 }
                        if (r14 == 0) goto L_0x0131
                        java.lang.CharSequence r13 = r13.getText()     // Catch:{ Exception -> 0x0131 }
                        java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x0131 }
                        int r13 = java.lang.Integer.parseInt(r13)     // Catch:{ Exception -> 0x0131 }
                        goto L_0x0132
                    L_0x0131:
                        r13 = 0
                    L_0x0132:
                        java.lang.String r14 = ""
                        if (r11 == 0) goto L_0x0152
                        int r15 = r11.size()     // Catch:{ Exception -> 0x02b2 }
                        if (r15 <= r12) goto L_0x0152
                        java.lang.Object r15 = r11.get(r12)     // Catch:{ Exception -> 0x02b2 }
                        android.view.accessibility.AccessibilityNodeInfo r15 = (android.view.accessibility.AccessibilityNodeInfo) r15     // Catch:{ Exception -> 0x02b2 }
                        if (r15 == 0) goto L_0x0152
                        java.lang.CharSequence r16 = r15.getText()     // Catch:{ Exception -> 0x02b2 }
                        if (r16 == 0) goto L_0x0152
                        java.lang.CharSequence r14 = r15.getText()     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x02b2 }
                    L_0x0152:
                        java.lang.String r15 = ""
                        if (r8 == 0) goto L_0x0172
                        int r3 = r8.size()     // Catch:{ Exception -> 0x02b2 }
                        if (r3 <= r12) goto L_0x0172
                        java.lang.Object r3 = r8.get(r12)     // Catch:{ Exception -> 0x02b2 }
                        android.view.accessibility.AccessibilityNodeInfo r3 = (android.view.accessibility.AccessibilityNodeInfo) r3     // Catch:{ Exception -> 0x02b2 }
                        if (r3 == 0) goto L_0x0172
                        java.lang.CharSequence r16 = r3.getText()     // Catch:{ Exception -> 0x02b2 }
                        if (r16 == 0) goto L_0x0172
                        java.lang.CharSequence r3 = r3.getText()     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r15 = r3.toString()     // Catch:{ Exception -> 0x02b2 }
                    L_0x0172:
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.jumpSelfName     // Catch:{ Exception -> 0x02b2 }
                        boolean r3 = r3.equals(r14)     // Catch:{ Exception -> 0x02b2 }
                        if (r3 != 0) goto L_0x02b6
                        if (r15 == 0) goto L_0x02b6
                        java.lang.String r3 = ""
                        boolean r3 = r3.equals(r15)     // Catch:{ Exception -> 0x02b2 }
                        if (r3 != 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.wechatSportsType     // Catch:{ Exception -> 0x02b2 }
                        r15 = 20
                        if (r3 != 0) goto L_0x01da
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.minimumStep     // Catch:{ Exception -> 0x02b2 }
                        if (r13 < r3) goto L_0x01cc
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils.access$608(r3)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        r3.sleep(r15)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r0)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02b2 }
                        r3.<init>()     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r13 = "已帮你点赞了 "
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r13 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r13 = r13.clickNum     // Catch:{ Exception -> 0x02b2 }
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r13 = " 个好友"
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r3)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02b6
                    L_0x01cc:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        boolean unused = r0.isEnd = r6     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r0)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02bc
                    L_0x01da:
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.wechatSportsType     // Catch:{ Exception -> 0x02b2 }
                        if (r3 != r6) goto L_0x0249
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.util.LinkedHashSet r3 = r3.friendNames     // Catch:{ Exception -> 0x02b2 }
                        if (r3 == 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.util.LinkedHashSet r3 = r3.friendNames     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.size()     // Catch:{ Exception -> 0x02b2 }
                        if (r3 <= 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.minimumStep     // Catch:{ Exception -> 0x02b2 }
                        if (r13 < r3) goto L_0x023c
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.util.LinkedHashSet r3 = r3.friendNames     // Catch:{ Exception -> 0x02b2 }
                        boolean r3 = r3.contains(r14)     // Catch:{ Exception -> 0x02b2 }
                        if (r3 == 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils.access$608(r3)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        r3.sleep(r15)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r0)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02b2 }
                        r3.<init>()     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r13 = "已帮你点赞了 "
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r13 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r13 = r13.clickNum     // Catch:{ Exception -> 0x02b2 }
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r13 = " 个好友"
                        r3.append(r13)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r3)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02b6
                    L_0x023c:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        boolean unused = r0.isEnd = r6     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r0)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02bc
                    L_0x0249:
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.jumpFriendNames     // Catch:{ Exception -> 0x02b2 }
                        if (r3 == 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.jumpFriendNames     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r6 = ""
                        boolean r3 = r3.equals(r6)     // Catch:{ Exception -> 0x02b2 }
                        if (r3 != 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r3 = r3.minimumStep     // Catch:{ Exception -> 0x02b2 }
                        if (r13 < r3) goto L_0x02a4
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.jumpFriendNames     // Catch:{ Exception -> 0x02b2 }
                        boolean r3 = r3.contains(r14)     // Catch:{ Exception -> 0x02b2 }
                        if (r3 != 0) goto L_0x02b6
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils.access$608(r3)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r3 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        r3.sleep(r15)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.utils.PerformClickUtils.performClick(r0)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02b2 }
                        r3.<init>()     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r6 = "已帮你点赞了 "
                        r3.append(r6)     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r6 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        int r6 = r6.clickNum     // Catch:{ Exception -> 0x02b2 }
                        r3.append(r6)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r6 = " 个好友"
                        r3.append(r6)     // Catch:{ Exception -> 0x02b2 }
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.updateBottomText(r0, r3)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02b6
                    L_0x02a4:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        r3 = 1
                        boolean unused = r0.isEnd = r3     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x02b2 }
                        com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r0)     // Catch:{ Exception -> 0x02b2 }
                        goto L_0x02bc
                    L_0x02b2:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch:{ Exception -> 0x0305 }
                    L_0x02b6:
                        int r12 = r12 + 1
                        r3 = 0
                        r6 = 1
                        goto L_0x00b9
                    L_0x02bc:
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean r0 = r0.isEnd     // Catch:{ Exception -> 0x0305 }
                        if (r0 != 0) goto L_0x0302
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        r3 = 0
                        boolean unused = r0.isFirstScroll = r3     // Catch:{ Exception -> 0x0305 }
                        r0 = 4096(0x1000, float:5.74E-42)
                        r5.performAction(r0)     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        int r4 = com.wx.assistants.application.MyApplication.SCROLL_SPEED     // Catch:{ Exception -> 0x0305 }
                        r0.sleep(r4)     // Catch:{ Exception -> 0x0305 }
                        java.lang.String r0 = "邀请朋友"
                        java.util.List r0 = r2.findAccessibilityNodeInfosByText(r0)     // Catch:{ Exception -> 0x0305 }
                        if (r0 == 0) goto L_0x02ff
                        int r0 = r0.size()     // Catch:{ Exception -> 0x0305 }
                        if (r0 <= 0) goto L_0x02ff
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.WxSportsUtils.access$908(r0)     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        int r0 = r0.scrollNum     // Catch:{ Exception -> 0x0305 }
                        r4 = 1
                        if (r0 <= r4) goto L_0x004c
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        boolean unused = r0.isEnd = r4     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.WxSportsUtils r0 = com.wx.assistants.service_utils.WxSportsUtils.this     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service.MyWindowManager r0 = r0.mMyManager     // Catch:{ Exception -> 0x0305 }
                        com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r0)     // Catch:{ Exception -> 0x0305 }
                        goto L_0x0305
                    L_0x02ff:
                        r4 = 1
                        goto L_0x004c
                    L_0x0302:
                        r3 = 0
                        goto L_0x004c
                    L_0x0305:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.service_utils.WxSportsUtils.AnonymousClass1.run():void");
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void middleViewShow() {
        LogUtils.log("WS_BABY_middleViewShow");
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "微信运动自动点赞", "已帮你点赞了" + this.clickNum + "个好友");
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在自动微信运动点赞，请不要操作微信");
            this.clickNum = 0;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        WxSportsUtils.this.executeMain();
                    } catch (Exception unused) {
                    }
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
