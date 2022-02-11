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

    private PerformClickUtils() {
    }

    static /* synthetic */ int access$010(PerformClickUtils performClickUtils2) {
        int i = performClickUtils2.countSend;
        performClickUtils2.countSend = i - 1;
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

    public static void executedBackAddress(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(800);
                        } catch (Exception e) {
                        }
                        if (!Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "添加到通讯录")).booleanValue()) {
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText2).booleanValue() && (Boolean.valueOf(findNodeIsExistById2).booleanValue() || findNodeIsExistByText3)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackAddress(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackEditLabel(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(800);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "编辑标签");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "保存");
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue()) {
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById2).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackEditLabel(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackGroupMember(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(600);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, "android:id/text1");
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "聊天信息");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "聊天成员");
                        if (!Boolean.valueOf(findNodeIsExistById).booleanValue() || (!Boolean.valueOf(findNodeIsExistByText).booleanValue() && !Boolean.valueOf(findNodeIsExistByText2).booleanValue())) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            }
                            PerformClickUtils.executedBackGroupMember(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackHome(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackHome(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackHome(final AutoService autoService, final int i, final boolean z, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            if (!z) {
                                Thread.sleep(200);
                            }
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackHome(autoService, i - 1, false, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackHomeCompany(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "消息");
                        boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "工作台");
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceCompanyUtils.company_back);
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                            } else if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackHomeCompany(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackHomeDeep(final AutoService autoService, final int i, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0) {
                        try {
                            Thread.sleep(400);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (!Boolean.valueOf(findNodeIsExistByText4).booleanValue() || (!Boolean.valueOf(findNodeIsExistById3).booleanValue() && !findNodeIsExistByText5)) {
                                PerformClickUtils.performBack(autoService);
                            } else {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackHomeDeep(autoService, i - 1, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedBackHomeDialogBack(final AutoService autoService, final int i, final boolean z, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            if (!z) {
                                Thread.sleep(200);
                            }
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.performBack(autoService);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedBackHomeDialogBack(autoService, i - 1, false, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedLabelBack(final AutoService autoService, final int i, final String str, final OnBackMainPageListener onBackMainPageListener) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(600);
                        } catch (Exception e) {
                        }
                        if (!Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, str)).booleanValue()) {
                            boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "不保存");
                            boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "保存");
                            if (Boolean.valueOf(findNodeIsExistByText).booleanValue()) {
                                PerformClickUtils.findTextAndClick(autoService, "不保存");
                            } else if (Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                                if (Boolean.valueOf(PerformClickUtils.findNodeIsExistByText(autoService, "不保存")).booleanValue()) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText2).booleanValue() && Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            }
                            PerformClickUtils.executedLabelBack(autoService, i - 1, str, onBackMainPageListener);
                        } else if (onBackMainPageListener != null) {
                            onBackMainPageListener.find();
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void executedRemarkBackHome(final AutoService autoService, final int i, final OnBackMainPageListener2 onBackMainPageListener2) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = i;
                    if (i >= 0 && MyApplication.enforceable) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        boolean findNodeIsExistByText = PerformClickUtils.findNodeIsExistByText(autoService, "通讯录");
                        boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "发现");
                        boolean findNodeIsExistById = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.home_tab_layout_id);
                        if (!Boolean.valueOf(findNodeIsExistByText).booleanValue() || !Boolean.valueOf(findNodeIsExistByText2).booleanValue() || !Boolean.valueOf(findNodeIsExistById).booleanValue()) {
                            boolean findNodeIsExistById2 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                            boolean findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "返回");
                            boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "取消");
                            boolean findNodeIsExistById3 = PerformClickUtils.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.album_head_img);
                            boolean findNodeIsExistByText5 = PerformClickUtils.findNodeIsExistByText(autoService, "清除");
                            if (Boolean.valueOf(findNodeIsExistById2).booleanValue()) {
                                if (PerformClickUtils.findNodeIsExistByText(autoService, "不保存")) {
                                    PerformClickUtils.findTextAndClick(autoService, "不保存");
                                    PerformClickUtils.isSaveSuccess = false;
                                } else {
                                    PerformClickUtils.findViewIdAndClick(autoService, BaseServiceUtils.dialog_ok_id);
                                }
                            } else if (Boolean.valueOf(findNodeIsExistByText3).booleanValue()) {
                                PerformClickUtils.findBackAndClick(autoService);
                            } else if (Boolean.valueOf(findNodeIsExistByText4).booleanValue() && (Boolean.valueOf(findNodeIsExistById3).booleanValue() || findNodeIsExistByText5)) {
                                PerformClickUtils.findTextAndClick(autoService, "取消");
                            }
                            PerformClickUtils.executedRemarkBackHome(autoService, i - 1, onBackMainPageListener2);
                        } else if (onBackMainPageListener2 != null) {
                            onBackMainPageListener2.find(PerformClickUtils.isSaveSuccess);
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }).start();
    }

    public static void findBackAndClick(AccessibilityService accessibilityService) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null && MyApplication.enforceable) {
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

    public static boolean findNodeGroupBottom(AccessibilityService accessibilityService) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("个群聊");
            if (findAccessibilityNodeInfosByText == null || findAccessibilityNodeInfosByText.size() <= 0 || findAccessibilityNodeInfosByText.isEmpty()) {
                return false;
            }
            try {
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1);
                if (accessibilityNodeInfo == null) {
                    return true;
                }
                String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
                return viewIdResourceName == null || !BaseServiceUtils.group_list_item_name_id.equals(viewIdResourceName);
            } catch (Exception e) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r1 = r1.findAccessibilityNodeInfosByViewId(r4);
     */
    public static boolean findNodeIsExistById(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            return rootInActiveWindow != null && findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0;
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

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r1 = r1.findAccessibilityNodeInfosByText(r4);
     */
    public static boolean findNodeIsExistByText(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            return rootInActiveWindow != null && findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty() && findAccessibilityNodeInfosByText.size() > 0;
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

    public static void findTextAndClick(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null && MyApplication.enforceable && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0) {
                LogUtils.log("FIND_TEXT" + str);
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
                            if ((next.getText() + "") != null || str2 != null) {
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
            } catch (Exception e) {
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String str) {
        try {
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
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                if (accessibilityNodeInfo.getText() != null) {
                    return accessibilityNodeInfo.getText().toString();
                }
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

    public static String getGroupNickName(AccessibilityService accessibilityService) {
        String charSequence;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null || !MyApplication.enforceable) {
                return "";
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText("群昵称");
            if (findAccessibilityNodeInfosByText != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1);
                if (accessibilityNodeInfo.getText() != null && (charSequence = accessibilityNodeInfo.getText().toString()) != null && !"".equals(charSequence) && charSequence.contains("群昵称") && charSequence.length() > 4) {
                    return charSequence.substring(4);
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PerformClickUtils getInstance() {
        if (performClickUtils == null) {
            performClickUtils = new PerformClickUtils();
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
        try {
            accessibilityService.performGlobalAction(1);
        } catch (Exception e) {
        }
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

    public static void longClick(AccessibilityNodeInfo accessibilityNodeInfo, OnLongClickListener onLongClickListener) {
        if (Build.VERSION.SDK_INT >= 24) {
            if (onLongClickListener != null) {
                onLongClickListener.execute6();
            }
        } else if (onLongClickListener != null) {
            onLongClickListener.execute6();
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
            } catch (Exception e) {
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
            } catch (Exception e) {
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
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0 || (accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1)) == null || !MyApplication.enforceable) {
                return;
            }
            if (accessibilityNodeInfo.isLongClickable()) {
                accessibilityNodeInfo.performAction(32);
            } else {
                performLongClick(accessibilityNodeInfo.getParent());
            }
        } catch (Exception e) {
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
                    } catch (Exception e) {
                    }
                }
            };
            this.timerSend.schedule(this.timerTask, 0, 1000);
        } catch (Exception e) {
        }
    }
}
