package com.wx.assistants.service_utils;

import android.view.accessibility.AccessibilityNodeInfo;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.service.MyWindowManager;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import java.util.LinkedHashSet;
import java.util.List;

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

    private WxSportsUtils() {
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

    public static WxSportsUtils getInstance() {
        instance = new WxSportsUtils();
        return instance;
    }

    public void endViewDismiss() {
        this.mMyManager.stopAccessibilityService();
        this.mMyManager.removeBottomView();
        this.mMyManager.showMiddleView();
    }

    public void endViewShow() {
        try {
            showBottomView(this.mMyManager, "正在自动微信运动点赞，请不要操作微信");
            this.clickNum = 0;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        WxSportsUtils.this.executeMain();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeMain() {
        try {
            new Thread(new Runnable() {
                /* JADX WARNING: CFG modification limit reached, blocks count: 243 */
                /* JADX WARNING: Code restructure failed: missing block: B:100:0x0246, code lost:
                    com.wx.assistants.service_utils.WxSportsUtils.access$102(r16.this$0, true);
                    com.wx.assistants.service_utils.BaseServiceUtils.removeEndView(r16.this$0.mMyManager);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:98:0x0233, code lost:
                    com.wx.assistants.service_utils.WxSportsUtils.access$908(r16.this$0);
                    r7 = 1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:99:0x0244, code lost:
                    if (com.wx.assistants.service_utils.WxSportsUtils.access$900(r16.this$0) <= 1) goto L_0x0054;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:101:0x0259  */
                /* JADX WARNING: Removed duplicated region for block: B:57:0x0147 A[SYNTHETIC, Splitter:B:57:0x0147] */
                /* JADX WARNING: Removed duplicated region for block: B:68:0x0168 A[SYNTHETIC, Splitter:B:68:0x0168] */
                /* JADX WARNING: Removed duplicated region for block: B:83:0x01a6 A[Catch:{ Exception -> 0x02d0 }] */
                public void run() {
                    AccessibilityNodeInfo rootInActiveWindow;
                    int i;
                    String str;
                    AccessibilityNodeInfo accessibilityNodeInfo;
                    AccessibilityNodeInfo accessibilityNodeInfo2;
                    AccessibilityNodeInfo accessibilityNodeInfo3;
                    AccessibilityNodeInfo accessibilityNodeInfo4;
                    try {
                        AccessibilityNodeInfo rootInActiveWindow2 = WxSportsUtils.this.autoService.getRootInActiveWindow();
                        if (rootInActiveWindow2 != null) {
                            while (WxSportsUtils.this.isTopEnd) {
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.sport_list_id);
                                if (!(findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo4 = findAccessibilityNodeInfosByViewId.get(0)) == null)) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.cover_the_cover_id);
                                    if (findAccessibilityNodeInfosByViewId2 == null || findAccessibilityNodeInfosByViewId2.size() == 0) {
                                        PerformClickUtils.scrollTop(accessibilityNodeInfo4);
                                        WxSportsUtils.this.sleep(100);
                                    } else {
                                        boolean unused = WxSportsUtils.this.isTopEnd = false;
                                    }
                                }
                            }
                            int i2 = 0;
                            while (true) {
                                if (WxSportsUtils.this.isEnd || !MyApplication.enforceable || (rootInActiveWindow = WxSportsUtils.this.autoService.getRootInActiveWindow()) == null) {
                                    break;
                                }
                                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.sport_list_id);
                                if (findAccessibilityNodeInfosByViewId3 != null && findAccessibilityNodeInfosByViewId3.size() > 0) {
                                    AccessibilityNodeInfo accessibilityNodeInfo5 = findAccessibilityNodeInfosByViewId3.get(0);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = rootInActiveWindow2.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_item_nickname_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_item_ranking_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId6 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_item_zan_img_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId7 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_item_step_id);
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId8 = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.list_item_nickname_id);
                                    if (findAccessibilityNodeInfosByViewId4 != null && findAccessibilityNodeInfosByViewId4.size() > 0 && WxSportsUtils.this.isFirstScroll) {
                                        String unused2 = WxSportsUtils.this.jumpSelfName = findAccessibilityNodeInfosByViewId4.get(0).getText().toString();
                                    }
                                    int i3 = i2;
                                    while (true) {
                                        if (i3 < findAccessibilityNodeInfosByViewId6.size()) {
                                            LogUtils.log("WS_BABY_" + findAccessibilityNodeInfosByViewId6.size() + "#list" + findAccessibilityNodeInfosByViewId4.size() + "#setp" + findAccessibilityNodeInfosByViewId7.size() + "#name" + findAccessibilityNodeInfosByViewId8.size() + "@index" + i2);
                                            if (!MyApplication.enforceable) {
                                                break;
                                            }
                                            try {
                                                AccessibilityNodeInfo accessibilityNodeInfo6 = findAccessibilityNodeInfosByViewId6.get(i3);
                                                if (!(findAccessibilityNodeInfosByViewId7 == null || findAccessibilityNodeInfosByViewId7.size() <= i3 || (accessibilityNodeInfo3 = findAccessibilityNodeInfosByViewId7.get(i3)) == null || accessibilityNodeInfo3.getText() == null)) {
                                                    try {
                                                        i = Integer.parseInt(accessibilityNodeInfo3.getText().toString());
                                                    } catch (Exception e) {
                                                    }
                                                    String str2 = "";
                                                    if (findAccessibilityNodeInfosByViewId8 != null) {
                                                        if (!(findAccessibilityNodeInfosByViewId8.size() <= i3 || (accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId8.get(i3)) == null || accessibilityNodeInfo2.getText() == null)) {
                                                            str2 = accessibilityNodeInfo2.getText().toString();
                                                        }
                                                    }
                                                    if (findAccessibilityNodeInfosByViewId5 != null) {
                                                        if (!(findAccessibilityNodeInfosByViewId5.size() <= i3 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId5.get(i3)) == null || accessibilityNodeInfo.getText() == null)) {
                                                            str = accessibilityNodeInfo.getText().toString();
                                                            if (!WxSportsUtils.this.jumpSelfName.equals(str2) && str != null && !"".equals(str)) {
                                                                if (WxSportsUtils.this.wechatSportsType != 0) {
                                                                    if (WxSportsUtils.this.wechatSportsType != 1) {
                                                                        if (WxSportsUtils.this.jumpFriendNames != null && !WxSportsUtils.this.jumpFriendNames.equals("")) {
                                                                            if (i < WxSportsUtils.this.minimumStep) {
                                                                                boolean unused3 = WxSportsUtils.this.isEnd = true;
                                                                                BaseServiceUtils.removeEndView(WxSportsUtils.this.mMyManager);
                                                                                break;
                                                                            } else if (!WxSportsUtils.this.jumpFriendNames.contains(str2)) {
                                                                                WxSportsUtils.access$608(WxSportsUtils.this);
                                                                                WxSportsUtils.this.sleep(20);
                                                                                PerformClickUtils.performClick(accessibilityNodeInfo6);
                                                                                BaseServiceUtils.updateBottomText(WxSportsUtils.this.mMyManager, "已帮你点赞了 " + WxSportsUtils.this.clickNum + " 个好友");
                                                                            }
                                                                        }
                                                                    } else if (WxSportsUtils.this.friendNames != null && WxSportsUtils.this.friendNames.size() > 0) {
                                                                        if (i < WxSportsUtils.this.minimumStep) {
                                                                            boolean unused4 = WxSportsUtils.this.isEnd = true;
                                                                            BaseServiceUtils.removeEndView(WxSportsUtils.this.mMyManager);
                                                                            break;
                                                                        } else if (WxSportsUtils.this.friendNames.contains(str2)) {
                                                                            WxSportsUtils.access$608(WxSportsUtils.this);
                                                                            WxSportsUtils.this.sleep(20);
                                                                            PerformClickUtils.performClick(accessibilityNodeInfo6);
                                                                            BaseServiceUtils.updateBottomText(WxSportsUtils.this.mMyManager, "已帮你点赞了 " + WxSportsUtils.this.clickNum + " 个好友");
                                                                        }
                                                                    }
                                                                } else if (i < WxSportsUtils.this.minimumStep) {
                                                                    boolean unused5 = WxSportsUtils.this.isEnd = true;
                                                                    BaseServiceUtils.removeEndView(WxSportsUtils.this.mMyManager);
                                                                    break;
                                                                } else {
                                                                    WxSportsUtils.access$608(WxSportsUtils.this);
                                                                    WxSportsUtils.this.sleep(20);
                                                                    PerformClickUtils.performClick(accessibilityNodeInfo6);
                                                                    BaseServiceUtils.updateBottomText(WxSportsUtils.this.mMyManager, "已帮你点赞了 " + WxSportsUtils.this.clickNum + " 个好友");
                                                                }
                                                            }
                                                            i3++;
                                                        }
                                                    }
                                                    str = "";
                                                    if (WxSportsUtils.this.wechatSportsType != 0) {
                                                    }
                                                }
                                                i = 0;
                                                String str22 = "";
                                                if (findAccessibilityNodeInfosByViewId8 != null) {
                                                }
                                                if (findAccessibilityNodeInfosByViewId5 != null) {
                                                }
                                                str = "";
                                                if (WxSportsUtils.this.wechatSportsType != 0) {
                                                }
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                    }
                                    if (!WxSportsUtils.this.isEnd) {
                                        boolean unused6 = WxSportsUtils.this.isFirstScroll = false;
                                        accessibilityNodeInfo5.performAction(4096);
                                        WxSportsUtils.this.sleep(MyApplication.SCROLL_SPEED);
                                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow2.findAccessibilityNodeInfosByText("邀请朋友");
                                        if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0) {
                                            break;
                                        }
                                        i2 = 1;
                                    }
                                }
                            }
                        }
                    } catch (Exception e3) {
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void middleViewDismiss() {
    }

    public void middleViewShow() {
        LogUtils.log("WS_BABY_middleViewShow");
        MyWindowManager myWindowManager = this.mMyManager;
        updateMiddleText(myWindowManager, "微信运动自动点赞", "已帮你点赞了" + this.clickNum + "个好友");
    }
}
