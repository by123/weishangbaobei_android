package com.wx.assistants.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Path;
import android.graphics.Rect;
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

public class PerformClickUtilsCompany {
    private static final int MIN_DELAY_TIME = 1000;
    private static final int MIN_DELAY_TIME2 = 2000;
    private static long lastClickTime;
    private static long lastClickTime2;
    public static PerformClickUtilsCompany performClickUtils;
    /* access modifiers changed from: private */
    public int countSend = 0;
    /* access modifiers changed from: private */
    public Timer timerSend = new Timer();
    /* access modifiers changed from: private */
    public TimerTask timerTask;

    public interface OnBackMainPageListener {
        void find();
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

    private PerformClickUtilsCompany() {
    }

    static /* synthetic */ int access$010(PerformClickUtilsCompany performClickUtilsCompany) {
        int i = performClickUtilsCompany.countSend;
        performClickUtilsCompany.countSend = i - 1;
        return i;
    }

    public static void backUpPageCompany(AccessibilityService accessibilityService) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceCompanyUtils.company_back)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                try {
                    performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void executedBackEditLabel(AutoService autoService, OnBackMainPageListener onBackMainPageListener) {
        try {
            Thread.sleep(800);
        } catch (Exception e) {
        }
        boolean findNodeIsExistByText = findNodeIsExistByText(autoService, "编辑标签");
        boolean findNodeIsExistByText2 = findNodeIsExistByText(autoService, "保存");
        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue()) {
            boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
            boolean findNodeIsExistByText3 = findNodeIsExistByText(autoService, "返回");
            boolean findNodeIsExistByText4 = findNodeIsExistByText(autoService, "取消");
            boolean findNodeIsExistById2 = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
            boolean findNodeIsExistByText5 = findNodeIsExistByText(autoService, "清除");
            if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                findBackAndClick(autoService);
            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById2).booleanValue() || findNodeIsExistByText5)) {
                findTextAndClick(autoService, "取消");
            }
            executedBackEditLabel(autoService, onBackMainPageListener);
        } else if (onBackMainPageListener != null) {
            onBackMainPageListener.find();
        }
    }

    public static void executedBackHome(AutoService autoService, OnBackMainPageListener onBackMainPageListener) {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        boolean findNodeIsExistByText = findNodeIsExistByText(autoService, "通讯录");
        boolean findNodeIsExistByText2 = findNodeIsExistByText(autoService, "发现");
        boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
            boolean findNodeIsExistById2 = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
            boolean findNodeIsExistByText3 = findNodeIsExistByText(autoService, "返回");
            boolean findNodeIsExistByText4 = findNodeIsExistByText(autoService, "取消");
            boolean findNodeIsExistById3 = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
            boolean findNodeIsExistByText5 = findNodeIsExistByText(autoService, "清除");
            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                findBackAndClick(autoService);
            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                findTextAndClick(autoService, "取消");
            }
            executedBackHome(autoService, onBackMainPageListener);
        } else if (onBackMainPageListener != null) {
            onBackMainPageListener.find();
        }
    }

    public static void executedBackHomeDeep(AutoService autoService, OnBackMainPageListener onBackMainPageListener) {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
        }
        boolean findNodeIsExistByText = findNodeIsExistByText(autoService, "通讯录");
        boolean findNodeIsExistByText2 = findNodeIsExistByText(autoService, "发现");
        boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
            boolean findNodeIsExistById2 = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
            boolean findNodeIsExistByText3 = findNodeIsExistByText(autoService, "返回");
            boolean findNodeIsExistByText4 = findNodeIsExistByText(autoService, "取消");
            boolean findNodeIsExistById3 = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
            boolean findNodeIsExistByText5 = findNodeIsExistByText(autoService, "清除");
            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                findBackAndClick(autoService);
            } else if (!Boolean.valueOf(findNodeIsExistByText4).booleanValue() || (!Boolean.valueOf(findNodeIsExistById3).booleanValue() && !findNodeIsExistByText5)) {
                performBack(autoService);
            } else {
                findTextAndClick(autoService, "取消");
            }
            executedBackHome(autoService, onBackMainPageListener);
        } else if (onBackMainPageListener != null) {
            onBackMainPageListener.find();
        }
    }

    public static void executedLabelBack(AutoService autoService, String str, OnBackMainPageListener onBackMainPageListener) {
        try {
            Thread.sleep(600);
        } catch (Exception e) {
        }
        if (!Boolean.valueOf(findNodeIsExistByText(autoService, str)).booleanValue()) {
            boolean findNodeIsExistByText = findNodeIsExistByText(autoService, "不保存");
            boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
            boolean findNodeIsExistByText2 = findNodeIsExistByText(autoService, "返回");
            boolean findNodeIsExistByText3 = findNodeIsExistByText(autoService, "保存");
            if (Boolean.valueOf(findNodeIsExistByText).booleanValue()) {
                findTextAndClick(autoService, "不保存");
            } else if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
            } else if (Boolean.valueOf(findNodeIsExistByText2).booleanValue() && Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                findBackAndClick(autoService);
            }
            executedLabelBack(autoService, str, onBackMainPageListener);
        } else if (onBackMainPageListener != null) {
            onBackMainPageListener.find();
        }
    }

    public static void findBackAndClick(AccessibilityService accessibilityService) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("返回");
                if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() != 1) {
                    System.out.println("WS_BABY_findBackAndClick.1");
                    accessibilityService.performGlobalAction(1);
                    return;
                }
                System.out.println("WS_BABY_findBackAndClick.0");
                try {
                    performClick4(findAccessibilityNodeInfosByText.get(0));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static AccessibilityNodeInfo findNodeById(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return null;
            }
            if (!MyApplication.enforceable) {
                return null;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                try {
                    return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void findNodeByIdFirstAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                try {
                    performClick(findAccessibilityNodeInfosByViewId.get(0));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        r1 = (r1 = r3.getRootInActiveWindow()).findAccessibilityNodeInfosByViewId(r4);
     */
    public static boolean findNodeIsExistById(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            return PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && rootInActiveWindow != null && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0014, code lost:
        r1 = r3.findAccessibilityNodeInfosByViewId(r4);
     */
    public static boolean findNodeIsExistById(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            return PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        r1 = (r1 = r3.getRootInActiveWindow()).findAccessibilityNodeInfosByText(r4);
     */
    public static boolean findNodeIsExistByText(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            return PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && rootInActiveWindow != null && findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty() && findAccessibilityNodeInfosByText.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void findTabTextAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        boolean z = false;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(BaseServiceUtils.home_tab_layout_id);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                    findTextAndClick(accessibilityService, str);
                    return;
                }
                int i = 0;
                while (true) {
                    if (i >= findAccessibilityNodeInfosByViewId.size()) {
                        break;
                    } else if (str.equals(findAccessibilityNodeInfosByViewId.get(i).getText().toString())) {
                        findTextAndClick(accessibilityService, str);
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!z) {
                    findTextAndClick(accessibilityService, str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0) {
                LogUtils.log("WS_BABY_FIND_TEXT" + str);
                try {
                    if (findAccessibilityNodeInfosByText.size() == 1) {
                        performClick4(findAccessibilityNodeInfosByText.get(0));
                    } else {
                        performClick4(findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1));
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str, String str2) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        int i = 0;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0) {
                LogUtils.log("FIND_TEXT" + str);
                try {
                    if (findAccessibilityNodeInfosByText.size() == 1) {
                        performClick4(findAccessibilityNodeInfosByText.get(0));
                        return;
                    }
                    while (true) {
                        int i2 = i;
                        if (i2 < findAccessibilityNodeInfosByText.size()) {
                            AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(i2);
                            String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
                            if (viewIdResourceName == null || !str2.equals(viewIdResourceName)) {
                                i = i2 + 1;
                            } else {
                                performClick4(accessibilityNodeInfo);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
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
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void findTextAndClickFirst(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                for (AccessibilityNodeInfo next : findAccessibilityNodeInfosByText) {
                    if (next != null) {
                        try {
                            String str2 = next.getContentDescription() + "";
                            if (next.getText().toString() != null || str2 != null) {
                                performClick5(next);
                                return;
                            } else if (next.getText() != null || next.getContentDescription() != null) {
                                performClick5(next);
                                return;
                            }
                        } catch (Exception e) {
                            if (next.getText() != null || next.getContentDescription() != null) {
                                performClick5(next);
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean findViewByIdAndPasteContent(AccessibilityService accessibilityService, String str, String str2) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str2);
            findAccessibilityNodeInfosByViewId.get(0).performAction(2097152, bundle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String str) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                if (rootInActiveWindow == null || !MyApplication.enforceable) {
                    LogUtils.log("BABY_findViewIdAndClick_accessibilityNodeInfo.null." + str);
                    return;
                }
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                    LogUtils.log("BABY_findViewIdAndClick.nodeInfoList.null" + str);
                    return;
                }
                LogUtils.log("BABY_findViewIdAndClick.0." + str);
                performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() + -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findViewIdAndClick2(AccessibilityService accessibilityService, String str) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                if (rootInActiveWindow == null || !MyApplication.enforceable) {
                    LogUtils.log("BABY_findViewIdAndClick_accessibilityNodeInfo.null." + str);
                    return;
                }
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                    LogUtils.log("BABY_findViewIdAndClick.nodeInfoList.null" + str);
                    return;
                }
                LogUtils.log("BABY_findViewIdAndClick.0." + str);
                performClick2(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() + -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String findViewIdAndGetText(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null || !MyApplication.enforceable) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty()) {
                return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).getText().toString();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean findViewIdIsEnabled(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return false;
            }
            if (!MyApplication.enforceable) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                return false;
            }
            return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public static List<AccessibilityNodeInfo> findViewIdList(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityNodeInfo != null && MyApplication.enforceable && (findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty()) {
                return findAccessibilityNodeInfosByViewId;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<AccessibilityNodeInfo> findViewStringList(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                return findAccessibilityNodeInfosByText;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int findViewStringListSize(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                return findAccessibilityNodeInfosByText.size();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<AccessibilityNodeInfo> findViewTextList(AutoService autoService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = autoService.getRootInActiveWindow()) != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                return findAccessibilityNodeInfosByText;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PerformClickUtilsCompany getInstance() {
        if (performClickUtils == null) {
            performClickUtils = new PerformClickUtilsCompany();
        }
        return performClickUtils;
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

    public static String getTextByNodeId(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty()) {
                return findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1).getText() + "";
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTextFirstByNodeId(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty()) {
                return findAccessibilityNodeInfosByViewId.get(0).getText() + "";
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideInputKey(AccessibilityService accessibilityService) {
        accessibilityService.performGlobalAction(1);
    }

    public static void inputPrefixText(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        AccessibilityNodeInfo findFocus;
        String str2;
        String str3;
        boolean z = true;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findFocus = rootInActiveWindow.findFocus(1)) != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (findFocus == null) {
                        z = false;
                    }
                    if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                        String str4 = findFocus.getText() + "";
                        if (str4 == null) {
                            str4 = "";
                        }
                        Bundle bundle = new Bundle();
                        if (!str4.startsWith(str)) {
                            str4 = str + str4;
                        }
                        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str4);
                        findFocus.performAction(2097152, bundle);
                        return;
                    }
                    return;
                }
                String str5 = findFocus.getText() + "";
                if (str5 == null) {
                    str5 = "";
                }
                ClipboardManager clipboardManager = (ClipboardManager) accessibilityService.getSystemService("clipboard");
                if (str5.startsWith(str)) {
                    str2 = str5;
                } else {
                    str2 = str + str5;
                }
                clipboardManager.setPrimaryClip(ClipData.newPlainText("label", str2));
                Bundle bundle2 = new Bundle();
                bundle2.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 0);
                if (str5.startsWith(str)) {
                    str3 = str5;
                } else {
                    str3 = str + str5;
                }
                bundle2.putInt("ACTION_ARGUMENT_SELECTION_END_INT", str3.length());
                findFocus.performAction(1);
                findFocus.performAction(WXMediaMessage.MINI_PROGRAM__THUMB_LENGHT, bundle2);
                findFocus.performAction(32768);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputText(AccessibilityService accessibilityService, String str) {
        boolean z = true;
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
            AccessibilityNodeInfo findFocus = rootInActiveWindow.findFocus(1);
            if (findFocus == null) {
                System.out.println("WS_BABY_inputText.3");
            } else if (Build.VERSION.SDK_INT >= 21) {
                System.out.println("WS_BABY_inputText.4");
                if (findFocus == null) {
                    z = false;
                }
                if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                    System.out.println("WS_BABY_inputText.5.text = " + str);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str);
                    findFocus.performAction(2097152, bundle);
                }
            } else {
                System.out.println("WS_BABY_inputText.6");
                findFocus.getText() + "";
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

    public static void longClick(final AccessibilityNodeInfo accessibilityNodeInfo, final OnLongClickListener onLongClickListener) {
        if (Build.VERSION.SDK_INT >= 24) {
            BaseServiceUtils.getMainHandler().post(new Runnable() {
                @RequiresApi(api = 24)
                public void run() {
                    PerformClickUtilsCompany.getLongClickNode(accessibilityNodeInfo, new OnGetLongClickListener() {
                        public void get(AccessibilityNodeInfo accessibilityNodeInfo) {
                            if (accessibilityNodeInfo != null && MyApplication.enforceable) {
                                Rect rect = new Rect();
                                accessibilityNodeInfo.getBoundsInScreen(rect);
                                GestureDescription.Builder builder = new GestureDescription.Builder();
                                Path path = new Path();
                                path.moveTo((float) rect.centerX(), (float) rect.centerY());
                                MyApplication.instance.getMyWindowManager().getAutoService().dispatchGesture(builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 800)).build(), new AccessibilityService.GestureResultCallback() {
                                    public void onCancelled(GestureDescription gestureDescription) {
                                        super.onCancelled(gestureDescription);
                                    }

                                    public void onCompleted(GestureDescription gestureDescription) {
                                        super.onCompleted(gestureDescription);
                                        if (onLongClickListener != null) {
                                            onLongClickListener.execute7();
                                        }
                                    }
                                }, new Handler());
                            }
                        }
                    });
                }
            });
        } else if (onLongClickListener != null) {
            onLongClickListener.execute6();
        }
    }

    public static void performBack(AccessibilityService accessibilityService) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityService != null) {
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
        }
    }

    public static void performBackNoDeep(AccessibilityService accessibilityService) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityService != null) {
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
        }
    }

    public static void performClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_findViewIdAndClick.performClick1.nullllllll");
                } else if (accessibilityNodeInfo.isClickable()) {
                    accessibilityNodeInfo.performAction(16);
                } else {
                    performClick(accessibilityNodeInfo.getParent());
                }
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

    public static void performClick2(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                    LogUtils.log("BABY_findViewIdAndClick.performClick2.nullllllll");
                } else if (accessibilityNodeInfo.isClickable()) {
                    LogUtils.log("BABY_findViewIdAndClick.clickable2 = true ");
                    accessibilityNodeInfo.performAction(16);
                    accessibilityNodeInfo.performAction(16);
                } else {
                    LogUtils.log("BABY_findViewIdAndClick.performClick2.parent");
                    performClick2(accessibilityNodeInfo.getParent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick4(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                    LogUtils.log("BABY_findViewIdAndClick.performClick4.nullllllll");
                } else if (accessibilityNodeInfo.isClickable()) {
                    LogUtils.log("BABY_findViewIdAndClick.clickable4 = true ");
                    accessibilityNodeInfo.performAction(16);
                } else {
                    LogUtils.log("BABY_findViewIdAndClick.performClick4.parent");
                    performClick4(accessibilityNodeInfo.getParent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClick5(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                    LogUtils.log("BABY_findViewIdAndClick.performClick5.nullllllll");
                } else if (accessibilityNodeInfo.isClickable()) {
                    LogUtils.log("BABY_findViewIdAndClick.clickable4 = true ");
                    accessibilityNodeInfo.performAction(16);
                } else {
                    LogUtils.log("BABY_findViewIdAndClick.performClick5.parent");
                    performClick5(accessibilityNodeInfo.getParent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performClickEnabled(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                if (accessibilityNodeInfo == null || !MyApplication.enforceable) {
                    LogUtils.log("WS_BABY_findViewIdAndClick.performClick1.nullllllll");
                } else if (accessibilityNodeInfo.isClickable() || accessibilityNodeInfo.isEnabled()) {
                    accessibilityNodeInfo.performAction(16);
                } else {
                    performClickEnabled(accessibilityNodeInfo.getParent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performLongClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo accessibilityNodeInfo;
        if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) == null || !MyApplication.enforceable) {
            return;
        }
        if (accessibilityNodeInfo.isLongClickable()) {
            accessibilityNodeInfo.performAction(32);
        } else {
            performLongClick(accessibilityNodeInfo.getParent());
        }
    }

    public static void performLongClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || accessibilityNodeInfo == null || !MyApplication.enforceable) {
                return;
            }
            if (accessibilityNodeInfo.isLongClickable()) {
                accessibilityNodeInfo.performAction(32);
            } else {
                performLongClick(accessibilityNodeInfo.getParent());
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
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable && accessibilityNodeInfo.isScrollable()) {
                accessibilityNodeInfo.performAction(4096);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scroll(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityNodeInfo != null && MyApplication.enforceable && accessibilityNodeInfo.isScrollable()) {
                accessibilityNodeInfo.performAction(4096);
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
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) != null && MyApplication.enforceable && accessibilityNodeInfo.isScrollable()) {
                accessibilityNodeInfo.performAction(8192);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scrollTop(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityNodeInfo != null && MyApplication.enforceable && accessibilityNodeInfo.isScrollable()) {
                accessibilityNodeInfo.performAction(8192);
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
                            public void onCancelled(GestureDescription gestureDescription) {
                                super.onCancelled(gestureDescription);
                            }

                            public void onCompleted(GestureDescription gestureDescription) {
                                super.onCompleted(gestureDescription);
                            }
                        }, new Handler());
                    }
                }
            });
        }
    }

    public void countDown(int i, final OnCheckListener onCheckListener) {
        this.countSend = i;
        this.timerSend = new Timer(true);
        this.timerTask = new TimerTask() {
            public void run() {
                PerformClickUtilsCompany.access$010(PerformClickUtilsCompany.this);
                if (PerformClickUtilsCompany.this.countSend >= 0) {
                    if (PerformClickUtilsCompany.this.timerTask != null) {
                        PerformClickUtilsCompany.this.timerTask.cancel();
                    }
                    if (PerformClickUtilsCompany.this.timerSend != null) {
                        PerformClickUtilsCompany.this.timerSend.cancel();
                        PerformClickUtilsCompany.this.timerSend.purge();
                    }
                    if (onCheckListener != null) {
                        int unused = PerformClickUtilsCompany.this.countSend = -1;
                        onCheckListener.find(PerformClickUtilsCompany.this.countSend);
                        return;
                    }
                    return;
                }
                if (PerformClickUtilsCompany.this.timerTask != null) {
                    PerformClickUtilsCompany.this.timerTask.cancel();
                }
                if (PerformClickUtilsCompany.this.timerSend != null) {
                    PerformClickUtilsCompany.this.timerSend.cancel();
                    PerformClickUtilsCompany.this.timerSend.purge();
                }
                if (onCheckListener != null) {
                    onCheckListener.nuFind();
                }
            }
        };
        this.timerSend.schedule(this.timerTask, 0, 1000);
    }
}
