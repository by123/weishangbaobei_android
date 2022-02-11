package com.wx.assistants.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service_utils.BaseServiceCompanyUtils;
import com.wx.assistants.service_utils.BaseServiceUtils;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PerformClickUtils {
    private static final int MIN_DELAY_TIME = 1000;
    private static final int MIN_DELAY_TIME2 = 2000;
    static boolean isSaveSuccess = true;
    private static long lastClickTime;
    private static long lastClickTime2;
    public static PerformClickUtils performClickUtils;
    /* access modifiers changed from: private */
    public int countSend = 0;
    /* access modifiers changed from: private */
    public Timer timerSend = new Timer();
    /* access modifiers changed from: private */
    public TimerTask timerTask;

    public interface OnBackMainPageListener {
        void find();
    }

    public interface OnBackMainPageListener2 {
        void find(boolean z);
    }

    public interface OnCheckListener {
        void find(int i);

        void nuFind();
    }

    public interface OnGetLongClickListener {
        void get(AccessibilityNodeInfo accessibilityNodeInfo);
    }

    public interface OnLongClickListener {
        void execute6();

        void execute7();
    }

    static /* synthetic */ int access$010(PerformClickUtils performClickUtils2) {
        int i = performClickUtils2.countSend;
        performClickUtils2.countSend = i - 1;
        return i;
    }

    private PerformClickUtils() {
    }

    public static PerformClickUtils getInstance() {
        if (performClickUtils == null) {
            performClickUtils = new PerformClickUtils();
        }
        return performClickUtils;
    }

    public static void executedBackEditLabel(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0031 A[Catch:{ Exception -> 0x00c6 }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x003c A[Catch:{ Exception -> 0x00c6 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00c6 }
                    if (r0 < 0) goto L_0x00c6
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x00c6
                    r1 = 800(0x320, double:3.953E-321)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = "编辑标签"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r3 = "保存"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00c6 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x003c
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x003c
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00c6 }
                    if (r0 == 0) goto L_0x00c6
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00c6 }
                    r0.find()     // Catch:{ Exception -> 0x00c6 }
                    goto L_0x00c6
                L_0x003c:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00c6 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00c6 }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00c6 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x009c
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00c6 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00c6 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x0094
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00c6 }
                    goto L_0x00bd
                L_0x0094:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00c6 }
                    goto L_0x00bd
                L_0x009c:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x00a8
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00c6 }
                    goto L_0x00bd
                L_0x00a8:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 == 0) goto L_0x00bd
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00c6 }
                    if (r1 != 0) goto L_0x00b6
                    if (r5 == 0) goto L_0x00bd
                L_0x00b6:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00c6 }
                L_0x00bd:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00c6 }
                    com.wx.assistants.utils.PerformClickUtils.executedBackEditLabel(r1, r0, r2)     // Catch:{ Exception -> 0x00c6 }
                L_0x00c6:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass1.run():void");
            }
        }).start();
    }

    public static void findTabTextAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_tab_layout_id);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                    findTextAndClick(accessibilityService, str);
                    return;
                }
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= findAccessibilityNodeInfosByViewId.size()) {
                        break;
                    }
                    if (str.equals(findAccessibilityNodeInfosByViewId.get(i).getText() + "")) {
                        findTextAndClick(accessibilityService, str);
                        z = true;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    findTextAndClick(accessibilityService, str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AccessibilityNodeInfo findNodeById(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                    if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                        try {
                            return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                        } catch (Exception unused) {
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void backUpPageCompany(AccessibilityService accessibilityService) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_back);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    try {
                        performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1));
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findNodeByIdFirstAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                    try {
                        performClick(findAccessibilityNodeInfosByViewId.get(0));
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<AccessibilityNodeInfo> findViewIdList(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityNodeInfo != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str);
                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                        return null;
                    }
                    return findAccessibilityNodeInfosByViewId;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<AccessibilityNodeInfo> findViewTextList(AutoService autoService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = autoService.getRootInActiveWindow()) != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
                    if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText.isEmpty()) {
                        return null;
                    }
                    return findAccessibilityNodeInfosByText;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<AccessibilityNodeInfo> findViewStringList(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
                    if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText.isEmpty()) {
                        return null;
                    }
                    return findAccessibilityNodeInfosByText;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findViewStringListSize(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
                    if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                        return findAccessibilityNodeInfosByText.size();
                    }
                    return 0;
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
                if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0) {
                    LogUtils.log("FIND_TEXT" + str);
                    try {
                        if (findAccessibilityNodeInfosByText.size() == 1) {
                            performClick4(findAccessibilityNodeInfosByText.get(0));
                        } else {
                            performClick4(findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findBackAndClick(AccessibilityService accessibilityService) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("返回");
                if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() != 1) {
                    System.out.println("WS_BABY_findBackAndClick.1");
                    accessibilityService.performGlobalAction(1);
                    return;
                }
                System.out.println("WS_BABY_findBackAndClick.0");
                try {
                    performClick4(findAccessibilityNodeInfosByText.get(0));
                } catch (Exception unused) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str, String str2) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return;
            }
            if (MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
                if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0) {
                    LogUtils.log("FIND_TEXT" + str);
                    try {
                        int i = 0;
                        if (findAccessibilityNodeInfosByText.size() == 1) {
                            performClick4(findAccessibilityNodeInfosByText.get(0));
                            return;
                        }
                        while (i < findAccessibilityNodeInfosByText.size()) {
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(i);
                            String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
                            if (viewIdResourceName == null || !str2.equals(viewIdResourceName)) {
                                i++;
                            } else {
                                performClick4(accessibilityNodeInfo);
                                return;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str, boolean z) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return;
            }
            if ((!z || MyApplication.enforceable) && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0) {
                LogUtils.log("FIND_TEXT" + str);
                try {
                    if (findAccessibilityNodeInfosByText.size() == 1) {
                        performClick(findAccessibilityNodeInfosByText.get(0), z);
                    } else {
                        performClick(findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1), z);
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008a, code lost:
        if (r4.getText() != null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
        performClick5(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0086 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void findTextAndClickFirst(android.accessibilityservice.AccessibilityService r3, java.lang.String r4) {
        /*
            java.lang.String r0 = com.wx.assistants.utils.PackageUtils.getAppNamePy()     // Catch:{ Exception -> 0x0097 }
            android.content.Context r1 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0097 }
            java.lang.String r1 = com.wx.assistants.utils.PackageUtils.getWSBYAppName(r1)     // Catch:{ Exception -> 0x0097 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0097 }
            if (r0 != 0) goto L_0x0013
            return
        L_0x0013:
            android.view.accessibility.AccessibilityNodeInfo r3 = r3.getRootInActiveWindow()     // Catch:{ Exception -> 0x0097 }
            if (r3 == 0) goto L_0x0096
            boolean r0 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x0097 }
            if (r0 != 0) goto L_0x001f
            goto L_0x0096
        L_0x001f:
            java.util.List r3 = r3.findAccessibilityNodeInfosByText(r4)     // Catch:{ Exception -> 0x0097 }
            if (r3 == 0) goto L_0x009b
            int r4 = r3.size()     // Catch:{ Exception -> 0x0097 }
            if (r4 <= 0) goto L_0x009b
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x0097 }
            if (r4 != 0) goto L_0x009b
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x0097 }
        L_0x0035:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x0097 }
            if (r4 == 0) goto L_0x009b
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x0097 }
            android.view.accessibility.AccessibilityNodeInfo r4 = (android.view.accessibility.AccessibilityNodeInfo) r4     // Catch:{ Exception -> 0x0097 }
            if (r4 == 0) goto L_0x0035
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0086 }
            r0.<init>()     // Catch:{ Exception -> 0x0086 }
            java.lang.CharSequence r1 = r4.getText()     // Catch:{ Exception -> 0x0086 }
            r0.append(r1)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0086 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0086 }
            r1.<init>()     // Catch:{ Exception -> 0x0086 }
            java.lang.CharSequence r2 = r4.getContentDescription()     // Catch:{ Exception -> 0x0086 }
            r1.append(r2)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0086 }
            if (r0 != 0) goto L_0x0082
            if (r1 == 0) goto L_0x0072
            goto L_0x0082
        L_0x0072:
            java.lang.CharSequence r0 = r4.getText()     // Catch:{ Exception -> 0x0086 }
            if (r0 != 0) goto L_0x007e
            java.lang.CharSequence r0 = r4.getContentDescription()     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x0035
        L_0x007e:
            performClick5(r4)     // Catch:{ Exception -> 0x0086 }
            goto L_0x009b
        L_0x0082:
            performClick5(r4)     // Catch:{ Exception -> 0x0086 }
            goto L_0x009b
        L_0x0086:
            java.lang.CharSequence r0 = r4.getText()     // Catch:{ Exception -> 0x0097 }
            if (r0 != 0) goto L_0x0092
            java.lang.CharSequence r0 = r4.getContentDescription()     // Catch:{ Exception -> 0x0097 }
            if (r0 == 0) goto L_0x0035
        L_0x0092:
            performClick5(r4)     // Catch:{ Exception -> 0x0097 }
            goto L_0x009b
        L_0x0096:
            return
        L_0x0097:
            r3 = move-exception
            r3.printStackTrace()
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.findTextAndClickFirst(android.accessibilityservice.AccessibilityService, java.lang.String):void");
    }

    public static boolean findNodeIsExistByText(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty() && findAccessibilityNodeInfosByText.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean findNodeGroupBottom(AccessibilityService accessibilityService) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        String viewIdResourceName;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null || (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("个群聊")) == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText.isEmpty()) {
                return false;
            }
            try {
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1);
                if (accessibilityNodeInfo == null || (viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName()) == null || !BaseServiceUtils.group_list_item_name_id.equals(viewIdResourceName)) {
                    return true;
                }
                return false;
            } catch (Exception unused) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getTextByNodeId(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return "";
            }
            return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).getText() + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTextFirstByNodeId(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return "";
            }
            return findAccessibilityNodeInfosByViewId.get(0).getText() + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean findNodeIsExistById(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean findNodeIsExistById(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String str) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                        LogUtils.log("BABY_findViewIdAndClick.nodeInfoList.null" + str);
                        return;
                    }
                    LogUtils.log("BABY_findViewIdAndClick.0." + str);
                    performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() + -1));
                    return;
                }
            }
            LogUtils.log("BABY_findViewIdAndClick_accessibilityNodeInfo.null." + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findViewIdAndClick2(AccessibilityService accessibilityService, String str) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                if (rootInActiveWindow != null) {
                    if (MyApplication.enforceable) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                        if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                            LogUtils.log("BABY_findViewIdAndClick.nodeInfoList.null" + str);
                            return;
                        }
                        LogUtils.log("BABY_findViewIdAndClick.0." + str);
                        performClick2(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() + -1));
                        return;
                    }
                }
                LogUtils.log("BABY_findViewIdAndClick_accessibilityNodeInfo.null." + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<AccessibilityNodeInfo> findViewIdList(AccessibilityService accessibilityService, String str) {
        try {
            if (!MyApplication.enforceable) {
                System.out.println("WSBABY_0");
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                System.out.println("WSBABY_1");
                return null;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                if (findAccessibilityNodeInfosByViewId.size() == 0) {
                    System.out.println("WSBABY_3");
                }
                System.out.println("WSBABY_4");
                return null;
            }
            System.out.println("WSBABY_2");
            return findAccessibilityNodeInfosByViewId;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean findViewIdIsEnabled(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                if (MyApplication.enforceable) {
                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                        return false;
                    }
                    return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).isEnabled();
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String findViewIdAndGetText(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return "";
            }
            if (!MyApplication.enforceable) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return "";
            }
            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
            if (accessibilityNodeInfo.getText() != null) {
                return accessibilityNodeInfo.getText().toString();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getGroupNickName(AccessibilityService accessibilityService) {
        String charSequence;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return "";
            }
            if (!MyApplication.enforceable) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("群昵称");
            if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText.isEmpty()) {
                return "";
            }
            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1);
            if (accessibilityNodeInfo.getText() == null || (charSequence = accessibilityNodeInfo.getText().toString()) == null || "".equals(charSequence) || !charSequence.contains("群昵称") || charSequence.length() <= 4) {
                return "";
            }
            return charSequence.substring(4);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void countDown(int i, final OnCheckListener onCheckListener) {
        try {
            this.countSend = i;
            this.timerSend = new Timer(true);
            this.timerTask = new TimerTask() {
                public void run() {
                    try {
                        PerformClickUtils.access$010(PerformClickUtils.this);
                        if (PerformClickUtils.this.countSend >= 0) {
                            if (PerformClickUtils.this.timerTask != null) {
                                PerformClickUtils.this.timerTask.cancel();
                            }
                            if (PerformClickUtils.this.timerSend != null) {
                                PerformClickUtils.this.timerSend.cancel();
                                PerformClickUtils.this.timerSend.purge();
                            }
                            if (onCheckListener != null) {
                                int unused = PerformClickUtils.this.countSend = -1;
                                onCheckListener.find(PerformClickUtils.this.countSend);
                                return;
                            }
                            return;
                        }
                        if (PerformClickUtils.this.timerTask != null) {
                            PerformClickUtils.this.timerTask.cancel();
                        }
                        if (PerformClickUtils.this.timerSend != null) {
                            PerformClickUtils.this.timerSend.cancel();
                            PerformClickUtils.this.timerSend.purge();
                        }
                        if (onCheckListener != null) {
                            onCheckListener.nuFind();
                        }
                    } catch (Exception unused2) {
                    }
                }
            };
            this.timerSend.schedule(this.timerTask, 0, 1000);
        } catch (Exception unused) {
        }
    }

    public static void inputText(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                System.out.println("WS_BABY_inputText.0");
                return;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                System.out.println("WS_BABY_inputText.2");
                return;
            }
            boolean z = true;
            AccessibilityNodeInfo findFocus = rootInActiveWindow.findFocus(1);
            if (findFocus == null) {
                System.out.println("WS_BABY_inputText.3");
            } else if (Build.VERSION.SDK_INT >= 21) {
                System.out.println("WS_BABY_inputText.4");
                if (findFocus == null) {
                    z = false;
                }
                if (findFocus.getClassName().equals("android.widget.EditText") && z) {
                    System.out.println("WS_BABY_inputText.5.text = " + str);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str);
                    findFocus.performAction(2097152, bundle);
                }
            } else {
                System.out.println("WS_BABY_inputText.6");
                String str2 = findFocus.getText() + "";
                ((ClipboardManager) accessibilityService.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
                Bundle bundle2 = new Bundle();
                bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str.length());
                findFocus.performAction(1);
                findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                findFocus.performAction(32768);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputPrefixText(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        String str2;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                boolean z = true;
                AccessibilityNodeInfo findFocus = rootInActiveWindow.findFocus(1);
                if (findFocus != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (findFocus == null) {
                            z = false;
                        }
                        if (findFocus.getClassName().equals("android.widget.EditText") && z) {
                            String str3 = findFocus.getText() + "";
                            if (str3 == null) {
                                str3 = "";
                            }
                            Bundle bundle = new Bundle();
                            if (!str3.startsWith(str)) {
                                str3 = str + str3;
                            }
                            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str3);
                            findFocus.performAction(2097152, bundle);
                            return;
                        }
                        return;
                    }
                    String str4 = findFocus.getText() + "";
                    if (str4 == null) {
                        str4 = "";
                    }
                    ClipboardManager clipboardManager = (ClipboardManager) accessibilityService.getSystemService("clipboard");
                    if (str4.startsWith(str)) {
                        str2 = str4;
                    } else {
                        str2 = str + str4;
                    }
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("label", str2));
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                    if (!str4.startsWith(str)) {
                        str4 = str + str4;
                    }
                    bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str4.length());
                    findFocus.performAction(1);
                    findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                    findFocus.performAction(32768);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scroll(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || accessibilityNodeInfo == null) {
                return;
            }
            if (MyApplication.enforceable) {
                if (accessibilityNodeInfo.isScrollable()) {
                    accessibilityNodeInfo.performAction(4096);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scroll(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo accessibilityNodeInfo;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null) {
                if (MyApplication.enforceable) {
                    if (accessibilityNodeInfo.isScrollable()) {
                        accessibilityNodeInfo.performAction(4096);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scrollTop(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || accessibilityNodeInfo == null) {
                return;
            }
            if (MyApplication.enforceable) {
                if (accessibilityNodeInfo.isScrollable()) {
                    accessibilityNodeInfo.performAction(8192);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scrollTop(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo accessibilityNodeInfo;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null) {
                if (MyApplication.enforceable) {
                    if (accessibilityNodeInfo.isScrollable()) {
                        accessibilityNodeInfo.performAction(8192);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo != null) {
                    if (MyApplication.enforceable) {
                        if (accessibilityNodeInfo.isClickable()) {
                            accessibilityNodeInfo.performAction(16);
                            return;
                        } else {
                            performClick(accessibilityNodeInfo.getParent());
                            return;
                        }
                    }
                }
                LogUtils.log("WS_BABY_findViewIdAndClick.performClick1.nullllllll");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClickEnabled(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo != null) {
                    if (MyApplication.enforceable) {
                        if (!accessibilityNodeInfo.isClickable()) {
                            if (!accessibilityNodeInfo.isEnabled()) {
                                performClickEnabled(accessibilityNodeInfo.getParent());
                                return;
                            }
                        }
                        accessibilityNodeInfo.performAction(16);
                        return;
                    }
                }
                LogUtils.log("WS_BABY_findViewIdAndClick.performClick1.nullllllll");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick2(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo != null) {
                    if (MyApplication.enforceable) {
                        if (accessibilityNodeInfo.isClickable()) {
                            LogUtils.log("BABY_findViewIdAndClick.clickable2 = true ");
                            accessibilityNodeInfo.performAction(16);
                            accessibilityNodeInfo.performAction(16);
                            return;
                        }
                        LogUtils.log("BABY_findViewIdAndClick.performClick2.parent");
                        performClick2(accessibilityNodeInfo.getParent());
                        return;
                    }
                }
                LogUtils.log("BABY_findViewIdAndClick.performClick2.nullllllll");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick4(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            try {
                if (MyApplication.enforceable) {
                    if (accessibilityNodeInfo.isClickable()) {
                        LogUtils.log("BABY_findViewIdAndClick.clickable4 = true ");
                        accessibilityNodeInfo.performAction(16);
                        return;
                    }
                    LogUtils.log("BABY_findViewIdAndClick.performClick4.parent");
                    performClick4(accessibilityNodeInfo.getParent());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        LogUtils.log("BABY_findViewIdAndClick.performClick4.nullllllll");
    }

    public static void performClick5(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo != null) {
                    if (MyApplication.enforceable) {
                        if (accessibilityNodeInfo.isClickable()) {
                            LogUtils.log("BABY_findViewIdAndClick.clickable4 = true ");
                            accessibilityNodeInfo.performAction(16);
                            return;
                        }
                        LogUtils.log("BABY_findViewIdAndClick.performClick5.parent");
                        performClick5(accessibilityNodeInfo.getParent());
                        return;
                    }
                }
                LogUtils.log("BABY_findViewIdAndClick.performClick5.nullllllll");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || accessibilityNodeInfo == null) {
                return;
            }
            if (z && !MyApplication.enforceable) {
                return;
            }
            if (accessibilityNodeInfo.isClickable()) {
                LogUtils.log("BABY_TEXTisClickable");
                accessibilityNodeInfo.performAction(16);
                return;
            }
            performClick(accessibilityNodeInfo.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performLongClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || accessibilityNodeInfo == null) {
                return;
            }
            if (MyApplication.enforceable) {
                if (accessibilityNodeInfo.isLongClickable()) {
                    accessibilityNodeInfo.performAction(32);
                } else {
                    performLongClick(accessibilityNodeInfo.getParent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getLongClickNode(AccessibilityNodeInfo accessibilityNodeInfo, OnGetLongClickListener onGetLongClickListener) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (!accessibilityNodeInfo.isLongClickable()) {
                    getLongClickNode(accessibilityNodeInfo.getParent(), onGetLongClickListener);
                } else if (onGetLongClickListener != null) {
                    onGetLongClickListener.get(accessibilityNodeInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void upPull() {
        if (Build.VERSION.SDK_INT >= 24) {
            BaseServiceUtils.getMainHandler().post(new Runnable() {
                @RequiresApi(api = 24)
                public void run() {
                    if (MyApplication.enforceable) {
                        GestureDescription.Builder builder = new GestureDescription.Builder();
                        Path path = new Path();
                        path.moveTo(300.0f, 600.0f);
                        path.lineTo(600.0f, 100.0f);
                        MyApplication.instance.getMyWindowManager().getAutoService().dispatchGesture(builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 1000)).build(), new AccessibilityService.GestureResultCallback() {
                            public void onCompleted(GestureDescription gestureDescription) {
                                super.onCompleted(gestureDescription);
                            }

                            public void onCancelled(GestureDescription gestureDescription) {
                                super.onCancelled(gestureDescription);
                            }
                        }, new Handler());
                    }
                }
            });
        }
    }

    public static void executedBackHome(final AutoService autoService, final int i, final boolean z, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[Catch:{ Exception -> 0x00dd }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[Catch:{ Exception -> 0x00dd }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00dd }
                    if (r0 < 0) goto L_0x00dd
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x00dd
                    boolean r1 = r4     // Catch:{ Exception -> 0x0011 }
                    if (r1 != 0) goto L_0x0011
                    r1 = 200(0xc8, double:9.9E-322)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x0011 }
                L_0x0011:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r3 = "发现"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x00dd }
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00dd }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x0052
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x0052
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x0052
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00dd }
                    if (r0 == 0) goto L_0x00dd
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00dd }
                    r0.find()     // Catch:{ Exception -> 0x00dd }
                    goto L_0x00dd
                L_0x0052:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00dd }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00dd }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00dd }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x00b2
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00dd }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00dd }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x00aa
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00dd }
                    goto L_0x00d3
                L_0x00aa:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00dd }
                    goto L_0x00d3
                L_0x00b2:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x00be
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00dd }
                    goto L_0x00d3
                L_0x00be:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 == 0) goto L_0x00d3
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00dd }
                    if (r1 != 0) goto L_0x00cc
                    if (r5 == 0) goto L_0x00d3
                L_0x00cc:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00dd }
                L_0x00d3:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00dd }
                    r2 = 0
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r3 = r5     // Catch:{ Exception -> 0x00dd }
                    com.wx.assistants.utils.PerformClickUtils.executedBackHome(r1, r0, r2, r3)     // Catch:{ Exception -> 0x00dd }
                L_0x00dd:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass4.run():void");
            }
        }).start();
    }

    public static void executedBackHomeDialogBack(final AutoService autoService, final int i, final boolean z, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[Catch:{ Exception -> 0x00db }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[Catch:{ Exception -> 0x00db }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00db }
                    if (r0 < 0) goto L_0x00db
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x00db
                    boolean r1 = r4     // Catch:{ Exception -> 0x0011 }
                    if (r1 != 0) goto L_0x0011
                    r1 = 200(0xc8, double:9.9E-322)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x0011 }
                L_0x0011:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r3 = "发现"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x00db }
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00db }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x0052
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x0052
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x0052
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00db }
                    if (r0 == 0) goto L_0x00db
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00db }
                    r0.find()     // Catch:{ Exception -> 0x00db }
                    goto L_0x00db
                L_0x0052:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00db }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00db }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00db }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x00b0
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00db }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00db }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x00aa
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00db }
                    goto L_0x00d1
                L_0x00aa:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.utils.PerformClickUtils.performBack(r1)     // Catch:{ Exception -> 0x00db }
                    goto L_0x00d1
                L_0x00b0:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x00bc
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00db }
                    goto L_0x00d1
                L_0x00bc:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 == 0) goto L_0x00d1
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00db }
                    if (r1 != 0) goto L_0x00ca
                    if (r5 == 0) goto L_0x00d1
                L_0x00ca:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00db }
                L_0x00d1:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00db }
                    r2 = 0
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r3 = r5     // Catch:{ Exception -> 0x00db }
                    com.wx.assistants.utils.PerformClickUtils.executedBackHomeDialogBack(r1, r0, r2, r3)     // Catch:{ Exception -> 0x00db }
                L_0x00db:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass5.run():void");
            }
        }).start();
    }

    public static void longClick(AccessibilityNodeInfo accessibilityNodeInfo, OnLongClickListener onLongClickListener) {
        if (Build.VERSION.SDK_INT >= 24) {
            if (onLongClickListener != null) {
                onLongClickListener.execute6();
            }
        } else if (onLongClickListener != null) {
            onLongClickListener.execute6();
        }
    }

    public static void performLongClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo accessibilityNodeInfo;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null) {
                if (MyApplication.enforceable) {
                    if (accessibilityNodeInfo.isLongClickable()) {
                        accessibilityNodeInfo.performAction(32);
                    } else {
                        performLongClick(accessibilityNodeInfo.getParent());
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void performBack(AccessibilityService accessibilityService) {
        if (accessibilityService != null) {
            try {
                boolean findNodeIsExistByText = findNodeIsExistByText(accessibilityService, "返回");
                boolean findNodeIsExistByText2 = findNodeIsExistByText(accessibilityService, "取消");
                boolean findNodeIsExistById = findNodeIsExistById(accessibilityService, BaseServiceUtils.album_head_img);
                boolean findNodeIsExistByText3 = findNodeIsExistByText(accessibilityService, "清除");
                if (findNodeIsExistByText) {
                    System.out.println("WS_BABY_BACKBACK.1");
                    findBackAndClick(accessibilityService);
                } else if (!findNodeIsExistByText2 || (!findNodeIsExistById && !findNodeIsExistByText3)) {
                    System.out.println("WS_BABY_BACKBACK.3");
                    accessibilityService.performGlobalAction(1);
                } else {
                    System.out.println("WS_BABY_BACKBACK.2");
                    findTextAndClick(accessibilityService, "取消");
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void performBackNoDeep(AccessibilityService accessibilityService) {
        if (accessibilityService != null) {
            try {
                boolean findNodeIsExistByText = findNodeIsExistByText(accessibilityService, "返回");
                boolean findNodeIsExistByText2 = findNodeIsExistByText(accessibilityService, "取消");
                boolean findNodeIsExistById = findNodeIsExistById(accessibilityService, BaseServiceUtils.album_head_img);
                boolean findNodeIsExistByText3 = findNodeIsExistByText(accessibilityService, "清除");
                if (findNodeIsExistByText) {
                    System.out.println("WS_BABY_BACKBACK.1");
                    findBackAndClick(accessibilityService);
                } else if (!findNodeIsExistByText2) {
                } else {
                    if (findNodeIsExistById || findNodeIsExistByText3) {
                        System.out.println("WS_BABY_BACKBACK.2");
                        findTextAndClick(accessibilityService, "取消");
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void hideInputKey(AccessibilityService accessibilityService) {
        try {
            accessibilityService.performGlobalAction(1);
        } catch (Exception unused) {
        }
    }

    public static boolean findViewByIdAndPasteContent(AccessibilityService accessibilityService, String str, String str2) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str2);
            try {
                findAccessibilityNodeInfosByViewId.get(0).performAction(2097152, bundle);
                return true;
            } catch (Exception unused) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }

    public static boolean isFastClick2000() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime2 < 2000;
        lastClickTime2 = currentTimeMillis;
        return z;
    }

    public static int getRandomTime(int i, int i2, int i3) {
        if (i != 1 || i2 > i3) {
            return 0;
        }
        double random = Math.random();
        double d = (double) (i3 - i2);
        Double.isNaN(d);
        double d2 = (double) i2;
        Double.isNaN(d2);
        return (int) ((random * d) + d2);
    }

    public static void executedBackHome(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0043 A[Catch:{ Exception -> 0x00d8 }] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x004e A[Catch:{ Exception -> 0x00d8 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00d8 }
                    if (r0 < 0) goto L_0x00d8
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x00d8
                    r1 = 100
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r3 = "发现"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x00d8 }
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00d8 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x004e
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x004e
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x004e
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00d8 }
                    if (r0 == 0) goto L_0x00d8
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00d8 }
                    r0.find()     // Catch:{ Exception -> 0x00d8 }
                    goto L_0x00d8
                L_0x004e:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00d8 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00d8 }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00d8 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x00ae
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00d8 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00d8 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x00a6
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00d8 }
                    goto L_0x00cf
                L_0x00a6:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00d8 }
                    goto L_0x00cf
                L_0x00ae:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x00ba
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00d8 }
                    goto L_0x00cf
                L_0x00ba:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 == 0) goto L_0x00cf
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00d8 }
                    if (r1 != 0) goto L_0x00c8
                    if (r5 == 0) goto L_0x00cf
                L_0x00c8:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00d8 }
                L_0x00cf:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00d8 }
                    com.wx.assistants.utils.PerformClickUtils.executedBackHome(r1, r0, r2)     // Catch:{ Exception -> 0x00d8 }
                L_0x00d8:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass6.run():void");
            }
        }).start();
    }

    public static void executedRemarkBackHome(final AutoService autoService, final int i, final OnBackMainPageListener2 onBackMainPageListener2) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0043 A[Catch:{ Exception -> 0x00d5 }] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0050 A[Catch:{ Exception -> 0x00d5 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00d5 }
                    if (r0 < 0) goto L_0x00d5
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x00d5
                    r1 = 100
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r3 = "发现"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x00d5 }
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00d5 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x0050
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x0050
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x0050
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener2 r0 = r4     // Catch:{ Exception -> 0x00d5 }
                    if (r0 == 0) goto L_0x00d5
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener2 r0 = r4     // Catch:{ Exception -> 0x00d5 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.isSaveSuccess     // Catch:{ Exception -> 0x00d5 }
                    r0.find(r1)     // Catch:{ Exception -> 0x00d5 }
                    goto L_0x00d5
                L_0x0050:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00d5 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00d5 }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00d5 }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00d5 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x00ab
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x00a3
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00d5 }
                    r1 = 0
                    com.wx.assistants.utils.PerformClickUtils.isSaveSuccess = r1     // Catch:{ Exception -> 0x00d5 }
                    goto L_0x00cc
                L_0x00a3:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00d5 }
                    goto L_0x00cc
                L_0x00ab:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x00b7
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00d5 }
                    goto L_0x00cc
                L_0x00b7:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 == 0) goto L_0x00cc
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00d5 }
                    if (r1 != 0) goto L_0x00c5
                    if (r5 == 0) goto L_0x00cc
                L_0x00c5:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00d5 }
                L_0x00cc:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener2 r2 = r4     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.utils.PerformClickUtils.executedRemarkBackHome(r1, r0, r2)     // Catch:{ Exception -> 0x00d5 }
                L_0x00d5:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass7.run():void");
            }
        }).start();
    }

    public static void executedBackAddress(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ Exception -> 0x00b4 }] */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[Catch:{ Exception -> 0x00b4 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00b4 }
                    if (r0 < 0) goto L_0x00b4
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x00b4
                    r1 = 800(0x320, double:3.953E-321)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = "添加到通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00b4 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x002a
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00b4 }
                    if (r0 == 0) goto L_0x00b4
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00b4 }
                    r0.find()     // Catch:{ Exception -> 0x00b4 }
                    goto L_0x00b4
                L_0x002a:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00b4 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00b4 }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00b4 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x008a
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00b4 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00b4 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x0082
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00b4 }
                    goto L_0x00ab
                L_0x0082:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00b4 }
                    goto L_0x00ab
                L_0x008a:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x0096
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00b4 }
                    goto L_0x00ab
                L_0x0096:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 == 0) goto L_0x00ab
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00b4 }
                    if (r1 != 0) goto L_0x00a4
                    if (r5 == 0) goto L_0x00ab
                L_0x00a4:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00b4 }
                L_0x00ab:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00b4 }
                    com.wx.assistants.utils.PerformClickUtils.executedBackAddress(r1, r0, r2)     // Catch:{ Exception -> 0x00b4 }
                L_0x00b4:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass8.run():void");
            }
        }).start();
    }

    public static void executedBackHomeCompany(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0043 A[Catch:{ Exception -> 0x00bd }] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x004d A[Catch:{ Exception -> 0x00bd }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00bd }
                    if (r0 < 0) goto L_0x00bd
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x00bd
                    r1 = 100
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r3 = "消息"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r4 = "工作台"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00bd }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x004d
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x004d
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x004d
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00bd }
                    if (r0 == 0) goto L_0x00bd
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00bd }
                    r0.find()     // Catch:{ Exception -> 0x00bd }
                    goto L_0x00bd
                L_0x004d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00bd }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceCompanyUtils.company_back     // Catch:{ Exception -> 0x00bd }
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00bd }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00bd }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00bd }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x0093
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00bd }
                    goto L_0x00b4
                L_0x0093:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x009f
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00bd }
                    goto L_0x00b4
                L_0x009f:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 == 0) goto L_0x00b4
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00bd }
                    if (r1 != 0) goto L_0x00ad
                    if (r5 == 0) goto L_0x00b4
                L_0x00ad:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00bd }
                L_0x00b4:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00bd }
                    com.wx.assistants.utils.PerformClickUtils.executedBackHomeCompany(r1, r0, r2)     // Catch:{ Exception -> 0x00bd }
                L_0x00bd:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass9.run():void");
            }
        }).start();
    }

    public static void executedLabelBack(final AutoService autoService, final int i, final String str, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ Exception -> 0x00ac }] */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[Catch:{ Exception -> 0x00ac }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00ac }
                    if (r0 < 0) goto L_0x00ac
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x00ac
                    r1 = 600(0x258, double:2.964E-321)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = r4     // Catch:{ Exception -> 0x00ac }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00ac }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x002a
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00ac }
                    if (r0 == 0) goto L_0x00ac
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r5     // Catch:{ Exception -> 0x00ac }
                    r0.find()     // Catch:{ Exception -> 0x00ac }
                    goto L_0x00ac
                L_0x002a:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r3 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00ac }
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r4 = "返回"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r5 = "保存"
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r4, r5)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00ac }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x0068
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    goto L_0x00a1
                L_0x0068:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x0090
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00ac }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x0088
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    goto L_0x00a1
                L_0x0088:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00ac }
                    goto L_0x00a1
                L_0x0090:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x00a1
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00ac }
                    if (r1 == 0) goto L_0x00a1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00ac }
                L_0x00a1:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00ac }
                    java.lang.String r2 = r4     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r3 = r5     // Catch:{ Exception -> 0x00ac }
                    com.wx.assistants.utils.PerformClickUtils.executedLabelBack(r1, r0, r2, r3)     // Catch:{ Exception -> 0x00ac }
                L_0x00ac:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass10.run():void");
            }
        }).start();
    }

    public static void executedBackHomeDeep(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0009 */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x003f A[Catch:{ Exception -> 0x00da }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x004a A[Catch:{ Exception -> 0x00da }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00da }
                    if (r0 < 0) goto L_0x00da
                    r1 = 400(0x190, double:1.976E-321)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x0009 }
                L_0x0009:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "通讯录"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r3 = "发现"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r4 = com.wx.assistants.service_utils.BaseServiceUtils.home_tab_layout_id     // Catch:{ Exception -> 0x00da }
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00da }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x004a
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x004a
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x004a
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00da }
                    if (r0 == 0) goto L_0x00da
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00da }
                    r0.find()     // Catch:{ Exception -> 0x00da }
                    goto L_0x00da
                L_0x004a:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00da }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r4 = "取消"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r4 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r5 = com.wx.assistants.service_utils.BaseServiceUtils.album_head_img     // Catch:{ Exception -> 0x00da }
                    boolean r4 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.service.AutoService r5 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r6 = "清除"
                    boolean r5 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r5, r6)     // Catch:{ Exception -> 0x00da }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x00aa
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00da }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x00a2
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00da }
                    goto L_0x00d1
                L_0x00a2:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00da }
                    goto L_0x00d1
                L_0x00aa:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x00b6
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00da }
                    goto L_0x00d1
                L_0x00b6:
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x00cc
                    boolean r1 = r4.booleanValue()     // Catch:{ Exception -> 0x00da }
                    if (r1 != 0) goto L_0x00c4
                    if (r5 == 0) goto L_0x00cc
                L_0x00c4:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "取消"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00da }
                    goto L_0x00d1
                L_0x00cc:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.utils.PerformClickUtils.performBack(r1)     // Catch:{ Exception -> 0x00da }
                L_0x00d1:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00da }
                    com.wx.assistants.utils.PerformClickUtils.executedBackHomeDeep(r1, r0, r2)     // Catch:{ Exception -> 0x00da }
                L_0x00da:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass11.run():void");
            }
        }).start();
    }

    public static void executedBackGroupMember(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0043 A[Catch:{ Exception -> 0x00a1 }] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x004d A[Catch:{ Exception -> 0x00a1 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    int r0 = r3     // Catch:{ Exception -> 0x00a1 }
                    if (r0 < 0) goto L_0x00a1
                    boolean r1 = com.wx.assistants.application.MyApplication.enforceable     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x00a1
                    r1 = 600(0x258, double:2.964E-321)
                    java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x000d }
                L_0x000d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r2 = "android:id/text1"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r3 = "聊天信息"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.service.AutoService r3 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r4 = "聊天成员"
                    boolean r3 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r3, r4)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x00a1 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x004d
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 != 0) goto L_0x0043
                    boolean r1 = r3.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x004d
                L_0x0043:
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00a1 }
                    if (r0 == 0) goto L_0x00a1
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r0 = r4     // Catch:{ Exception -> 0x00a1 }
                    r0.find()     // Catch:{ Exception -> 0x00a1 }
                    goto L_0x00a1
                L_0x004d:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00a1 }
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistById((android.accessibilityservice.AccessibilityService) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.service.AutoService r2 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r3 = "返回"
                    boolean r2 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r2, r3)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00a1 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x008d
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r2 = "不保存"
                    boolean r1 = com.wx.assistants.utils.PerformClickUtils.findNodeIsExistByText(r1, r2)     // Catch:{ Exception -> 0x00a1 }
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00a1 }
                    boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x0085
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r2 = "不保存"
                    com.wx.assistants.utils.PerformClickUtils.findTextAndClick(r1, r2)     // Catch:{ Exception -> 0x00a1 }
                    goto L_0x0098
                L_0x0085:
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    java.lang.String r2 = com.wx.assistants.service_utils.BaseServiceUtils.dialog_ok_id     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.utils.PerformClickUtils.findViewIdAndClick(r1, r2)     // Catch:{ Exception -> 0x00a1 }
                    goto L_0x0098
                L_0x008d:
                    boolean r1 = r2.booleanValue()     // Catch:{ Exception -> 0x00a1 }
                    if (r1 == 0) goto L_0x0098
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.utils.PerformClickUtils.findBackAndClick(r1)     // Catch:{ Exception -> 0x00a1 }
                L_0x0098:
                    int r0 = r0 + -1
                    com.wx.assistants.service.AutoService r1 = r2     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.utils.PerformClickUtils$OnBackMainPageListener r2 = r4     // Catch:{ Exception -> 0x00a1 }
                    com.wx.assistants.utils.PerformClickUtils.executedBackGroupMember(r1, r0, r2)     // Catch:{ Exception -> 0x00a1 }
                L_0x00a1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PerformClickUtils.AnonymousClass12.run():void");
            }
        }).start();
    }
}
