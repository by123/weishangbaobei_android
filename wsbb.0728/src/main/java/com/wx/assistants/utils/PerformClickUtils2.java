package com.wx.assistants.utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.service.AutoService;
import com.wx.assistants.service_utils.BaseServiceUtils;
import java.io.PrintStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PerformClickUtils2 {
    int checkNodeIdCount = 0;
    int countNum = 0;
    int countNum2 = 0;
    /* access modifiers changed from: private */
    public int executeX = 0;
    /* access modifiers changed from: private */
    public Timer timerSend = new Timer();
    /* access modifiers changed from: private */
    public TimerTask timerTask;

    interface IRunnable {
        void run();
    }

    public interface OnCheckListener {
        void find(int i);

        void nuFind();
    }

    public interface OnCheckListener2 {
        void end();

        void executeIndex(int i);

        void find();

        void nuFind();
    }

    public interface OnCheckListener3 {
        void backContactInfo();

        void executeBack();

        void nuFind();
    }

    public interface OnCheckListener4 {
        void goValidate();

        void nilAdding();

        void nuFind();
    }

    public interface OnCheckListener5 {
        void execute(int i);

        void find(int i);

        void nuFind();
    }

    public interface OnCountDownListener {
        void end();

        void surplus(int i);
    }

    public static AccessibilityNodeInfo findNodeByIdFirst(AccessibilityService accessibilityService, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return null;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow == null) {
                return null;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                try {
                    return findAccessibilityNodeInfosByViewId.get(0);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_3");
        }
    }

    public static boolean findNodeIsExistById(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                rootInActiveWindow.recycle();
                return false;
            } else if (findAccessibilityNodeInfosByViewId.size() > 0) {
                rootInActiveWindow.recycle();
                return true;
            } else {
                rootInActiveWindow.recycle();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_8");
            return false;
        }
    }

    public static boolean findNodeIsExistById(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                accessibilityNodeInfo.recycle();
                return false;
            } else if (findAccessibilityNodeInfosByViewId.size() > 0) {
                accessibilityNodeInfo.recycle();
                return true;
            } else {
                accessibilityNodeInfo.recycle();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_9");
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
            LogUtils.log("WS_BABY_TRY_CATCH_7");
            return false;
        }
    }

    public static boolean findNodeNumIsExistByIdOne(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) || (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) == null) {
                return false;
            }
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                rootInActiveWindow.recycle();
                return false;
            } else if (findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.size() != 1) {
                rootInActiveWindow.recycle();
                return false;
            } else {
                rootInActiveWindow.recycle();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_10");
            return false;
        }
    }

    public static List<AccessibilityNodeInfo> findNodesById(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0) {
                return findAccessibilityNodeInfosByViewId;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_2");
            return null;
        }
    }

    public static void findTextAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0) {
                LogUtils.log("FIND_TEXT" + str);
                try {
                    performClick(findAccessibilityNodeInfosByText.get(findAccessibilityNodeInfosByText.size() - 1));
                    rootInActiveWindow.recycle();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_5");
        }
    }

    public static void findTextAndClickFirst(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) != null && findAccessibilityNodeInfosByText.size() > 0 && !findAccessibilityNodeInfosByText.isEmpty()) {
                for (AccessibilityNodeInfo next : findAccessibilityNodeInfosByText) {
                    if (next != null) {
                        try {
                            String str2 = next.getContentDescription() + "";
                            if ((next.getText() + "") != null || str2 != null) {
                                performClick(next);
                                return;
                            } else if (next.getText() != null || next.getContentDescription() != null) {
                                performClick(next);
                                return;
                            }
                        } catch (Exception e) {
                            if (next.getText() != null || next.getContentDescription() != null) {
                                performClick(next);
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_6");
        }
    }

    public static boolean findViewByIdAndPasteContent(AccessibilityService accessibilityService, String str, String str2) {
        try {
            if (!PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                return false;
            }
            AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty()) {
                    rootInActiveWindow.recycle();
                    return false;
                }
                Bundle bundle = new Bundle();
                bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str2);
                findAccessibilityNodeInfosByViewId.get(0).performAction(2097152, bundle);
                rootInActiveWindow.recycle();
                return true;
            }
            rootInActiveWindow.recycle();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void findViewIdAndClick(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) != null && findAccessibilityNodeInfosByViewId.size() > 0 && !findAccessibilityNodeInfosByViewId.isEmpty() && findAccessibilityNodeInfosByViewId.size() > 0) {
                performClick(findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1));
                rootInActiveWindow.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("WS_BABY_TRY_CATCH_11");
        }
    }

    public static void inputText(AccessibilityService accessibilityService, String str) {
        AccessibilityNodeInfo rootInActiveWindow;
        AccessibilityNodeInfo findFocus;
        boolean z = true;
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null && (findFocus = rootInActiveWindow.findFocus(1)) != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (findFocus == null) {
                        z = false;
                    }
                    if (z && findFocus.getClassName().equals("android.widget.EditText")) {
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str);
                        findFocus.performAction(2097152, bundle);
                        return;
                    }
                    return;
                }
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

    public static void performBack(AccessibilityService accessibilityService) {
        try {
            if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText())) && accessibilityService != null) {
                String str = "";
                if ("7.0.4".equals(BaseServiceUtils.wxVersionName)) {
                    str = "com.tencent.mm:id/kn";
                } else if ("7.0.3".equals(BaseServiceUtils.wxVersionName)) {
                    str = "com.tencent.mm:id/k2";
                } else if ("7.0.0".equals(BaseServiceUtils.wxVersionName)) {
                    str = "com.tencent.mm:id/jv";
                } else if ("6.7.3".equals(BaseServiceUtils.wxVersionName)) {
                    str = "com.tencent.mm:id/j5";
                }
                try {
                    if (!"".equals(str)) {
                        AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                        if (rootInActiveWindow != null) {
                            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
                            if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0) {
                                accessibilityService.performGlobalAction(1);
                                rootInActiveWindow.recycle();
                                LogUtils.log("@@@@@@@@@@@000!!!!!!@@@@");
                                return;
                            }
                            performClick(findAccessibilityNodeInfosByViewId.get(0));
                            rootInActiveWindow.recycle();
                            LogUtils.log("@@@@@@@@@@@000!!!@@@@");
                            return;
                        }
                        accessibilityService.performGlobalAction(1);
                        rootInActiveWindow.recycle();
                        LogUtils.log("@@@@@@@@@@@000!!!!!!@@@@@@@@@@@@");
                        return;
                    }
                    LogUtils.log("@@@@@@@@@@@000!!!!!!222@@@@@@@@@@@@@@@@@@");
                    accessibilityService.performGlobalAction(1);
                } catch (Exception e) {
                    LogUtils.log("@@@@@@@@@@@111@@@@");
                    accessibilityService.performGlobalAction(1);
                }
            }
        } catch (Exception e2) {
        }
    }

    public static void performClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        synchronized (PerformClickUtils2.class) {
            try {
                if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                    if (accessibilityNodeInfo != null) {
                        if (accessibilityNodeInfo.isClickable()) {
                            LogUtils.log("FIND_TEXTisClickable");
                            accessibilityNodeInfo.performAction(16);
                            accessibilityNodeInfo.recycle();
                        } else {
                            performClick(accessibilityNodeInfo.getParent());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                Class<PerformClickUtils2> cls = PerformClickUtils2.class;
                throw th;
            }
        }
    }

    public static void performLongClick(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        synchronized (PerformClickUtils2.class) {
            try {
                AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0)) {
                    AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                    if (accessibilityNodeInfo != null) {
                        if (accessibilityNodeInfo.isLongClickable()) {
                            accessibilityNodeInfo.performAction(32);
                            accessibilityNodeInfo.recycle();
                        } else {
                            performLongClick(accessibilityNodeInfo.getParent());
                        }
                    }
                }
            } catch (Exception e) {
            } catch (Throwable th) {
                Class<PerformClickUtils2> cls = PerformClickUtils2.class;
                throw th;
            }
        }
    }

    public static void performLongClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        synchronized (PerformClickUtils2.class) {
            try {
                if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                    if (accessibilityNodeInfo != null) {
                        if (accessibilityNodeInfo.isLongClickable()) {
                            accessibilityNodeInfo.performAction(32);
                            accessibilityNodeInfo.recycle();
                        } else {
                            performLongClick(accessibilityNodeInfo.getParent());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                Class<PerformClickUtils2> cls = PerformClickUtils2.class;
                throw th;
            }
        }
    }

    public static void scroll(AccessibilityService accessibilityService, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        synchronized (PerformClickUtils2.class) {
            try {
                if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                    AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                    if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.size() <= 0)) {
                        AccessibilityNodeInfo accessibilityNodeInfo = findAccessibilityNodeInfosByViewId.get(findAccessibilityNodeInfosByViewId.size() - 1);
                        if (accessibilityNodeInfo != null) {
                            if (accessibilityNodeInfo.isScrollable()) {
                                accessibilityNodeInfo.performAction(4096);
                                accessibilityNodeInfo.recycle();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                Class<PerformClickUtils2> cls = PerformClickUtils2.class;
                throw th;
            }
        }
    }

    public static void scroll(AccessibilityNodeInfo accessibilityNodeInfo) {
        synchronized (PerformClickUtils2.class) {
            try {
                if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
                    if (accessibilityNodeInfo != null) {
                        if (accessibilityNodeInfo.isScrollable()) {
                            accessibilityNodeInfo.performAction(4096);
                            accessibilityNodeInfo.recycle();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                Class<PerformClickUtils2> cls = PerformClickUtils2.class;
                throw th;
            }
        }
    }

    public void check(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (!PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) || onCheckListener52 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    if (onCheckListener52 != null) {
                                        onCheckListener52.execute(i);
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener52.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener52.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void check(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (!PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void check(AutoService autoService, String str, int i, int i2, int i3, boolean z, OnCheckListener2 onCheckListener2) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener2 onCheckListener22 = onCheckListener2;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final boolean z2 = z;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i == i4 - 1 && onCheckListener22 != null) {
                                onCheckListener22.end();
                            }
                            if (!z2) {
                                try {
                                    Thread.sleep((long) i5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                z = true;
                            } else {
                                z = z2;
                            }
                            if (onCheckListener22 != null) {
                                onCheckListener22.executeIndex(i4 - i);
                            }
                            if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) && onCheckListener22 != null) {
                                if (MyApplication.enforceable) {
                                    onCheckListener22.find();
                                }
                                if (z2) {
                                    int i2 = i4;
                                    return;
                                }
                            }
                            try {
                                Thread.sleep((long) i6);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                            i++;
                            z2 = z;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void check(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    int i;
                    int i2;
                    boolean z2;
                    boolean z3 = false;
                    int i3 = 0;
                    while (i3 < i4 && MyApplication.enforceable) {
                        try {
                            if (i3 != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z3) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z3;
                                }
                                if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3)) {
                                    if (!PerformClickUtils2.findNodeNumIsExistByIdOne(autoService2, str3)) {
                                        AccessibilityNodeInfo findNodeByIdFirst = PerformClickUtils2.findNodeByIdFirst(autoService2, str3);
                                        if (findNodeByIdFirst != null) {
                                            String str = "";
                                            if (findNodeByIdFirst.getText() != null) {
                                                str = findNodeByIdFirst.getText() + "";
                                            }
                                            if (str4 == null || !str4.equals(str)) {
                                                List<AccessibilityNodeInfo> findNodesById = PerformClickUtils2.findNodesById(autoService2, str3);
                                                if (findNodesById != null && findNodesById.size() > 0) {
                                                    int i4 = 0;
                                                    boolean z4 = false;
                                                    while (true) {
                                                        if (i4 >= findNodesById.size()) {
                                                            i2 = i3;
                                                            break;
                                                        }
                                                        AccessibilityNodeInfo accessibilityNodeInfo = findNodesById.get(i4);
                                                        if (!(accessibilityNodeInfo == null || accessibilityNodeInfo.getText() == null)) {
                                                            String str2 = accessibilityNodeInfo.getText() + "";
                                                            if (str4 != null && str4.equals(str2)) {
                                                                if (onCheckListener2 != null) {
                                                                    onCheckListener2.find(i3);
                                                                    i2 = i4 + 1;
                                                                    z4 = true;
                                                                    break;
                                                                }
                                                                z2 = true;
                                                                i4++;
                                                                z4 = z2;
                                                            }
                                                        }
                                                        z2 = z4;
                                                        i4++;
                                                        z4 = z2;
                                                    }
                                                    if (z4) {
                                                        return;
                                                    }
                                                    if (findNodesById == null || findNodesById.size() <= 0 || findNodesById.get(0) == null || onCheckListener2 == null) {
                                                        i = i2;
                                                        Thread.sleep((long) i6);
                                                        i3 = i + 1;
                                                        z3 = z;
                                                    } else {
                                                        if (MyApplication.enforceable) {
                                                            onCheckListener2.find(0);
                                                        }
                                                        int i5 = i4;
                                                        return;
                                                    }
                                                }
                                            } else if (onCheckListener2 != null) {
                                                if (MyApplication.enforceable) {
                                                    onCheckListener2.find(0);
                                                }
                                                int i6 = i4;
                                                return;
                                            }
                                        }
                                    } else if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i7 = i4;
                                        return;
                                    }
                                }
                                i = i3;
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i3 = i + 1;
                                z3 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkAddContact(final AutoService autoService, final OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < 600) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != 599 || onCheckListener == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep(300);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService, "发消息");
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService, "添加到通讯录");
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService, "视频动态");
                                    boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService, BaseServiceUtils.dialog_ok_id);
                                    if (!findNodeIsExistByText3) {
                                        findNodeIsExistByText3 = PerformClickUtils.findNodeIsExistByText(autoService, "申请添加朋友");
                                    }
                                    if ((!findNodeIsExistByText && !findNodeIsExistByText2 && !findNodeIsExistByText3 && !findNodeIsExistById) || onCheckListener == null) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = 601;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = 601;
                                        return;
                                    } else if (findNodeIsExistByText3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = 601;
                                        return;
                                    } else if (findNodeIsExistById) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener.find(3);
                                        }
                                        int unused6 = PerformClickUtils2.this.executeX = 601;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkAutoPassValidation(AutoService autoService, String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str6 = str;
            final String str7 = str2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str6);
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str7);
                                    boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str8);
                                    boolean findNodeIsExistById3 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str9);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str10);
                                    if ((!findNodeIsExistById && !findNodeIsExistByText && !findNodeIsExistById2 && !findNodeIsExistById3 && !findNodeIsExistByText2) || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistById) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(3);
                                        }
                                        int unused6 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(4);
                                        }
                                        int unused7 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener2.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkCardCollectionName(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, "收藏");
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, "名片");
                                boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService2, "相册");
                                boolean findNodeIsExistByText4 = PerformClickUtils2.findNodeIsExistByText(autoService2, "位置");
                                boolean findNodeIsExistByText5 = PerformClickUtils2.findNodeIsExistByText(autoService2, "语音输入");
                                if (!findNodeIsExistByText || !findNodeIsExistByText2 || !findNodeIsExistByText3 || !findNodeIsExistByText4 || !findNodeIsExistByText5) {
                                    System.out.println("WS_BABY_checkCardCollectionName.1");
                                    PerformClickUtils2.findViewIdAndClick(autoService2, str2);
                                } else {
                                    System.out.println("WS_BABY_checkCardCollectionName.0");
                                    if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkCloneCircle(AutoService autoService, String str, String str2, String str3, String str4, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str5);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str6);
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str7);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str8);
                                if (!findNodeIsExistByText || !findNodeIsExistByText2) {
                                    if (findNodeIsExistById && findNodeIsExistById2 && onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                } else if (onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkDialogToBack(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3)) {
                                    if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                } else if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4)) {
                                    PerformClickUtils.findViewIdAndClick(autoService2, str4);
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkExistString(final AutoService autoService, final OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    while (i < 20) {
                        try {
                            PrintStream printStream = System.out;
                            printStream.println("WS_BABYxxx_......" + i);
                            if (MyApplication.enforceable) {
                                if (i != 9 || onCheckListener == null || !MyApplication.enforceable) {
                                    if (!PerformClickUtils2.findNodeIsExistByText(autoService, "删除并退出")) {
                                        PerformClickUtils.scroll(autoService, BaseServiceUtils.default_list_id);
                                        try {
                                            Thread.sleep(20);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (onCheckListener != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener.find(0);
                                            return;
                                        }
                                        return;
                                    }
                                    i++;
                                } else {
                                    onCheckListener.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkFavListNodeId(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                List<AccessibilityNodeInfo> findViewIdList = PerformClickUtils.findViewIdList((AccessibilityService) autoService2, str2);
                                if (findViewIdList == null || findViewIdList.size() <= 0 || findViewIdList.get(0).getChildCount() <= 1 || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkGroupNodeAllIds(AutoService autoService, String str, String str2, String str3, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            this.countNum2 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i4 = i;
            final AutoService autoService2 = autoService;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final int i5 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < PerformClickUtils2.this.countNum2 && MyApplication.enforceable) {
                        if (i != PerformClickUtils2.this.countNum2 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                            if (!z2) {
                                try {
                                    Thread.sleep((long) i4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                z = true;
                            } else {
                                z = z2;
                            }
                            try {
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str5);
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str6);
                                if (!findNodeIsExistById || !findNodeIsExistById2) {
                                    if (findNodeIsExistByText && onCheckListener2 != null) {
                                        PerformClickUtils2.this.countNum2 = 0;
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                            return;
                                        }
                                        return;
                                    }
                                } else if (onCheckListener2 != null) {
                                    PerformClickUtils2.this.countNum2 = 0;
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                        return;
                                    }
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i5);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        } else {
                            onCheckListener2.nuFind();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIdIsEnable(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                AccessibilityNodeInfo rootInActiveWindow = autoService2.getRootInActiveWindow();
                                if (rootInActiveWindow != null) {
                                    List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str2);
                                    if (findAccessibilityNodeInfosByViewId == null || findAccessibilityNodeInfosByViewId.size() <= 0 || findAccessibilityNodeInfosByViewId.isEmpty() || findAccessibilityNodeInfosByViewId.size() <= 0 || !findAccessibilityNodeInfosByViewId.get(0).isEnabled() || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        i++;
                                        z2 = z;
                                    } else {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIsContactAddress(final AutoService autoService, final int i, final OnCheckListener4 onCheckListener4) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    boolean z = false;
                    while (true) {
                        int i2 = i;
                        if (i2 < 500) {
                            if (i2 == 499) {
                                try {
                                    if (onCheckListener4 != null && MyApplication.enforceable) {
                                        onCheckListener4.nuFind();
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (!z) {
                                try {
                                    Thread.sleep((long) i);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                z = true;
                            }
                            boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService, "视频动态");
                            boolean findNodeIsExistByText2 = PerformClickUtils.findNodeIsExistByText(autoService, "申请添加朋友");
                            if (findNodeIsExistByText || findNodeIsExistByText2) {
                                LogUtils.log("WS_BABY视频动态");
                                if (onCheckListener4 != null) {
                                    onCheckListener4.goValidate();
                                    return;
                                }
                            }
                            if (i2 < 20 || PerformClickUtils2.findNodeIsExistByText(autoService, "正在添加") || onCheckListener4 == null) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e3) {
                                    e3.printStackTrace();
                                }
                                i = i2 + 1;
                            } else {
                                onCheckListener4.nilAdding();
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIsContactAddress2(final AutoService autoService, final int i, final OnCheckListener4 onCheckListener4) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    boolean z = false;
                    while (true) {
                        int i2 = i;
                        if (i2 < 500) {
                            if (i2 == 499) {
                                try {
                                    if (onCheckListener4 != null && MyApplication.enforceable) {
                                        onCheckListener4.nuFind();
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (!z) {
                                try {
                                    Thread.sleep((long) i);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                z = true;
                            }
                            if (PerformClickUtils2.findNodeIsExistByText(autoService, "正在添加") || onCheckListener4 == null) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e3) {
                                    e3.printStackTrace();
                                }
                                i = i2 + 1;
                            } else {
                                onCheckListener4.nilAdding();
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIsMainPage(AutoService autoService, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (!PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, BaseServiceUtils.home_tab_layout_id)) {
                                    PerformClickUtils2.performBack(autoService2);
                                } else if (onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIsSending(final AutoService autoService, final int i, final OnCheckListener3 onCheckListener3) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    boolean z = false;
                    while (true) {
                        int i2 = i;
                        if (i2 < 400) {
                            try {
                                LogUtils.log("WS_BABY_checkIsSending" + i2);
                                if (i2 != 399 || onCheckListener3 == null || !MyApplication.enforceable) {
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService, "添加到通讯录");
                                    LogUtils.log("WS_BABY_添加到通讯录" + findNodeIsExistByText);
                                    if (!findNodeIsExistByText || onCheckListener3 == null) {
                                        if (!z) {
                                            try {
                                                Thread.sleep((long) i);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            z = true;
                                        }
                                        if (i2 >= 22) {
                                            boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService, "正在发送");
                                            LogUtils.log("WS_BABY_正在发送" + findNodeIsExistByText2);
                                            if (!findNodeIsExistByText2) {
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e2) {
                                                    e2.printStackTrace();
                                                }
                                                boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService, "视频动态");
                                                boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "申请添加朋友");
                                                if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                                    LogUtils.log("WS_BABY_视频动态");
                                                    if (onCheckListener3 != null) {
                                                        onCheckListener3.executeBack();
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e3) {
                                            e3.printStackTrace();
                                        }
                                        i = i2 + 1;
                                    } else {
                                        onCheckListener3.backContactInfo();
                                        return;
                                    }
                                } else {
                                    onCheckListener3.nuFind();
                                    return;
                                }
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkIsSendingByAddContact(final AutoService autoService, final int i, final OnCheckListener3 onCheckListener3) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    boolean z = false;
                    while (true) {
                        int i2 = i;
                        if (i2 < 400) {
                            if (i2 == 399) {
                                try {
                                    if (onCheckListener3 != null && MyApplication.enforceable) {
                                        onCheckListener3.nuFind();
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService, "添加到通讯录");
                            boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService, "发消息");
                            if (findNodeIsExistByText || findNodeIsExistByText2) {
                                LogUtils.log("WS_BABY_添加到通讯录" + findNodeIsExistByText + "@发消息" + findNodeIsExistByText2);
                                if (onCheckListener3 != null) {
                                    onCheckListener3.backContactInfo();
                                    return;
                                }
                            }
                            if (!z) {
                                try {
                                    Thread.sleep((long) i);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                z = true;
                            }
                            if (i2 >= 25) {
                                if (!PerformClickUtils2.findNodeIsExistByText(autoService, "正在发送")) {
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService, "视频动态");
                                    boolean findNodeIsExistByText4 = PerformClickUtils.findNodeIsExistByText(autoService, "申请添加朋友");
                                    if (findNodeIsExistByText3 || findNodeIsExistByText4) {
                                        LogUtils.log("WS_BABY_视频动态");
                                        if (onCheckListener3 != null) {
                                            onCheckListener3.executeBack();
                                            return;
                                        }
                                    }
                                }
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                            i = i2 + 1;
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkLoad(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        LogUtils.log("WS_BABY_TRY_CATCH_13");
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNilNodeId(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNilString(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                if (onCheckListener52 != null && MyApplication.enforceable) {
                                    onCheckListener52.execute(i);
                                }
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str2) || onCheckListener52 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener52.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener52.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNilString(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNilStringScroll(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str3) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNilStringTwo(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if (findNodeIsExistByText || findNodeIsExistByText2 || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeAllIds(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            this.countNum = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i4 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i5 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < PerformClickUtils2.this.countNum && MyApplication.enforceable) {
                        try {
                            if (i != PerformClickUtils2.this.countNum - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i4);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                if (!findNodeIsExistById || !findNodeIsExistById2 || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    PerformClickUtils2.this.countNum = 0;
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                        return;
                                    }
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeAllIdsAsy(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            boolean z = false;
            int i4 = 0;
            while (i4 < i3) {
                try {
                    if (!MyApplication.enforceable) {
                        return;
                    }
                    if (i4 != i3 - 1 || onCheckListener == null || !MyApplication.enforceable) {
                        if (!z) {
                            try {
                                Thread.sleep((long) i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            z = true;
                        }
                        boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, str);
                        boolean findNodeIsExistById2 = findNodeIsExistById((AccessibilityService) autoService, str2);
                        if (!findNodeIsExistById || !findNodeIsExistById2 || onCheckListener == null) {
                            try {
                                Thread.sleep((long) i2);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                            i4++;
                        } else if (MyApplication.enforceable) {
                            onCheckListener.find(0);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        onCheckListener.nuFind();
                        return;
                    }
                } catch (Exception e3) {
                    return;
                }
            }
        }
    }

    public void checkNodeHasSomeOne(AutoService autoService, String str, String str2, String str3, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                    boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str5);
                                    boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str6);
                                    if ((!findNodeIsExistByText && !findNodeIsExistById && !findNodeIsExistById2) || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener2.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkNodeId(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            this.checkNodeIdCount = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i4 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i5 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < PerformClickUtils2.this.checkNodeIdCount && MyApplication.enforceable) {
                        try {
                            if (i != PerformClickUtils2.this.checkNodeIdCount - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i4);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (!PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    PerformClickUtils2.this.checkNodeIdCount = 0;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdNameHasSomeOne3(AutoService autoService, String str, String str2, String str3, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str5);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str6);
                                LogUtils.log("WS_BABY_isHas1" + findNodeIsExistById + "_isHas2" + findNodeIsExistByText + "_isHas3" + findNodeIsExistByText2);
                                if ((findNodeIsExistById || findNodeIsExistByText || findNodeIsExistByText2) && onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        if (findNodeIsExistById) {
                                            onCheckListener2.find(0);
                                        } else if (findNodeIsExistByText) {
                                            onCheckListener2.find(1);
                                        } else if (findNodeIsExistByText2) {
                                            onCheckListener2.find(2);
                                        }
                                    }
                                    int i2 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdNilExist(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i == i4 - 1 && onCheckListener2 != null && MyApplication.enforceable) {
                                onCheckListener2.find(0);
                            }
                            if (!z2) {
                                try {
                                    Thread.sleep((long) i5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                z = true;
                            } else {
                                z = z2;
                            }
                            if (PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2) || onCheckListener2 == null) {
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                if (MyApplication.enforceable) {
                                    onCheckListener2.nuFind();
                                }
                                int i2 = i4;
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdOrName(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3);
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if (findNodeIsExistById) {
                                    if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                } else if (findNodeIsExistByText && onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(1);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdOrStringAll(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3);
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if (!findNodeIsExistById || !findNodeIsExistByText || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdSyn(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            boolean z = false;
            int i4 = 0;
            while (i4 < i3) {
                try {
                    if (!MyApplication.enforceable) {
                        return;
                    }
                    if (i4 != i3 - 1 || onCheckListener == null || !MyApplication.enforceable) {
                        if (!z) {
                            try {
                                Thread.sleep((long) i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            z = true;
                        }
                        if (!findNodeIsExistById((AccessibilityService) autoService, str) || onCheckListener == null) {
                            try {
                                Thread.sleep((long) i2);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                            i4++;
                        } else if (MyApplication.enforceable) {
                            onCheckListener.find(0);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        onCheckListener.nuFind();
                        return;
                    }
                } catch (Exception e3) {
                    return;
                }
            }
        }
    }

    public void checkNodeIds(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                if ((!findNodeIsExistById && !findNodeIsExistById2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else if (findNodeIsExistById) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(1);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdsHasSomeOne(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str3);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                if (findNodeIsExistById || findNodeIsExistById2) {
                                    if (findNodeIsExistById && onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    } else if (findNodeIsExistById2 && onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int i3 = i4;
                                        return;
                                    }
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeIdsHasSomeOne3(AutoService autoService, String str, String str2, String str3, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str5);
                                boolean findNodeIsExistById3 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str6);
                                if ((findNodeIsExistById || findNodeIsExistById2 || findNodeIsExistById3) && onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkNodeStringsHasSomeOne(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                    if ((!findNodeIsExistByText && !findNodeIsExistByText2) || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener2.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkNodeStringsHasSomeOne(AutoService autoService, String str, String str2, String str3, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str5);
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService2, str6);
                                    if ((!findNodeIsExistByText && !findNodeIsExistByText2 && !findNodeIsExistByText3) || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener2.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkNodeStringsHasSomeOne(AutoService autoService, String str, String str2, String str3, String str4, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str5);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str6);
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService2, str7);
                                    boolean findNodeIsExistByText4 = PerformClickUtils2.findNodeIsExistByText(autoService2, str8);
                                    if ((!findNodeIsExistByText && !findNodeIsExistByText2 && !findNodeIsExistByText3 && !findNodeIsExistByText4) || onCheckListener2 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText4) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(3);
                                        }
                                        int unused6 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener2.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkScanQrCode(AutoService autoService, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str8 = str;
            final String str9 = str2;
            final String str10 = str3;
            final String str11 = str4;
            final String str12 = str5;
            final String str13 = str6;
            final String str14 = str7;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    try {
                        int unused = PerformClickUtils2.this.executeX = 0;
                        int access$000 = PerformClickUtils2.this.executeX;
                        while (access$000 < i4) {
                            int unused2 = PerformClickUtils2.this.executeX = access$000;
                            if (MyApplication.enforceable) {
                                if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                    if (!z2) {
                                        try {
                                            Thread.sleep((long) i5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        z = true;
                                    } else {
                                        z = z2;
                                    }
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str8);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str9);
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService2, str10);
                                    boolean findNodeIsExistByText4 = PerformClickUtils2.findNodeIsExistByText(autoService2, str11);
                                    boolean findNodeIsExistByText5 = PerformClickUtils2.findNodeIsExistByText(autoService2, str12);
                                    boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str13);
                                    boolean findNodeIsExistByText6 = PerformClickUtils2.findNodeIsExistByText(autoService2, str14);
                                    boolean findNodeIsExistByText7 = PerformClickUtils2.findNodeIsExistByText(autoService2, "通讯录");
                                    boolean findNodeIsExistByText8 = PerformClickUtils2.findNodeIsExistByText(autoService2, "发现");
                                    boolean findNodeIsExistByText9 = PerformClickUtils2.findNodeIsExistByText(autoService2, "微信");
                                    if ((!findNodeIsExistByText && !findNodeIsExistByText2 && !findNodeIsExistByText3 && !findNodeIsExistByText4 && !findNodeIsExistByText5 && !findNodeIsExistById && !findNodeIsExistByText6 && (!findNodeIsExistByText7 || !findNodeIsExistByText8 || !findNodeIsExistByText9)) || onCheckListener52 == null) {
                                        if (onCheckListener52 != null && MyApplication.enforceable) {
                                            onCheckListener52.execute(access$000);
                                        }
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText4) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(3);
                                        }
                                        int unused6 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText5) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(4);
                                        }
                                        int unused7 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(5);
                                        }
                                        int unused8 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText6) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(6);
                                        }
                                        int unused9 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText7 && findNodeIsExistByText8 && findNodeIsExistByText9) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(10);
                                        }
                                        int unused10 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    onCheckListener52.nuFind();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void checkSendAddNodeId(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (!PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str2)) {
                                    PerformClickUtils2.inputText(autoService2, "");
                                } else if (onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkString(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str2)) {
                                    System.out.println("WS_BABY_......" + i);
                                    if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkString3(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str2 = str;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                if (PerformClickUtils2.findNodeIsExistByText(autoService2, str2)) {
                                    System.out.println("WS_BABY_......" + i);
                                    if (onCheckListener52 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                }
                                if (onCheckListener52 != null) {
                                    onCheckListener52.execute(i);
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener52.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkStringAndIdSomeOne(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str4);
                                if (findNodeIsExistByText) {
                                    if (onCheckListener2 != null) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener2.find(0);
                                        }
                                        int i2 = i4;
                                        return;
                                    }
                                } else if (findNodeIsExistById && onCheckListener2 != null) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(1);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                                try {
                                    Thread.sleep((long) i6);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i++;
                                z2 = z;
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkStringsAll(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if (!findNodeIsExistByText || !findNodeIsExistByText2 || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkStringsFromPhotos(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if ((!findNodeIsExistByText && !findNodeIsExistByText2) || onCheckListener52 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    if (onCheckListener52 != null) {
                                        onCheckListener52.execute(i);
                                    }
                                    i++;
                                    z2 = z;
                                } else if (findNodeIsExistByText) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener52.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener52.find(1);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener52.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkStringsSyn(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener onCheckListener2 = onCheckListener;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str3 = str;
            final String str4 = str2;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int i = 0;
                    while (i < i4 && MyApplication.enforceable) {
                        try {
                            if (i != i4 - 1 || onCheckListener2 == null || !MyApplication.enforceable) {
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str3);
                                boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str4);
                                if ((!findNodeIsExistByText && !findNodeIsExistByText2) || onCheckListener2 == null) {
                                    try {
                                        Thread.sleep((long) i6);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                    i++;
                                    z2 = z;
                                } else if (findNodeIsExistByText) {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(0);
                                    }
                                    int i2 = i4;
                                    return;
                                } else {
                                    if (MyApplication.enforceable) {
                                        onCheckListener2.find(1);
                                    }
                                    int i3 = i4;
                                    return;
                                }
                            } else {
                                onCheckListener2.nuFind();
                                return;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public void checkSyn(AutoService autoService, String str, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            boolean z = false;
            int i4 = 0;
            while (i4 < i3) {
                try {
                    if (MyApplication.enforceable && MyApplication.enforceable) {
                        if (i4 != i3 - 1 || onCheckListener == null || !MyApplication.enforceable) {
                            if (!z) {
                                try {
                                    Thread.sleep((long) i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                z = true;
                            }
                            if (!findNodeIsExistById((AccessibilityService) autoService, str) || onCheckListener == null) {
                                try {
                                    Thread.sleep((long) i2);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i4++;
                            } else if (MyApplication.enforceable) {
                                onCheckListener.find(0);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            onCheckListener.nuFind();
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e3) {
                    return;
                }
            }
        }
    }

    public void checkSyn2(AutoService autoService, String str, String str2, int i, int i2, int i3, OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            boolean z = false;
            int i4 = 0;
            while (i4 < i3) {
                try {
                    if (MyApplication.enforceable && MyApplication.enforceable) {
                        if (i4 != i3 - 1 || onCheckListener == null || !MyApplication.enforceable) {
                            if (!z) {
                                try {
                                    Thread.sleep((long) i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                z = true;
                            }
                            boolean findNodeIsExistById = findNodeIsExistById((AccessibilityService) autoService, str);
                            boolean findNodeIsExistById2 = findNodeIsExistById((AccessibilityService) autoService, str2);
                            if (!findNodeIsExistById || !findNodeIsExistById2 || onCheckListener == null) {
                                try {
                                    Thread.sleep((long) i2);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                i4++;
                            } else if (MyApplication.enforceable) {
                                onCheckListener.find(0);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            onCheckListener.nuFind();
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e3) {
                    return;
                }
            }
        }
    }

    public void countDown(int i, final OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            try {
                MyApplication.countSend = i;
                this.timerSend = new Timer(true);
                this.timerTask = new TimerTask() {
                    public void run() {
                        try {
                            MyApplication.countSend--;
                            if (MyApplication.countSend >= 0) {
                                if (PerformClickUtils2.this.timerTask != null) {
                                    PerformClickUtils2.this.timerTask.cancel();
                                }
                                if (PerformClickUtils2.this.timerSend != null) {
                                    PerformClickUtils2.this.timerSend.cancel();
                                    PerformClickUtils2.this.timerSend.purge();
                                }
                                if (onCheckListener != null) {
                                    MyApplication.countSend = -1;
                                    onCheckListener.find(MyApplication.countSend);
                                    return;
                                }
                                return;
                            }
                            if (PerformClickUtils2.this.timerTask != null) {
                                PerformClickUtils2.this.timerTask.cancel();
                            }
                            if (PerformClickUtils2.this.timerSend != null) {
                                PerformClickUtils2.this.timerSend.cancel();
                                PerformClickUtils2.this.timerSend.purge();
                            }
                            if (onCheckListener != null && MyApplication.enforceable) {
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

    public void countDown2(final int i, final OnCountDownListener onCountDownListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        MyApplication.countSend = i;
                        while (MyApplication.countSend > 0 && MyApplication.enforceable) {
                            if (onCountDownListener != null) {
                                onCountDownListener.surplus(MyApplication.countSend);
                            }
                            MyApplication.countSend--;
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                        if (onCountDownListener != null && MyApplication.enforceable) {
                            onCountDownListener.end();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void delay(final int i, final OnCheckListener onCheckListener) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        BaseServiceUtils.getMainHandler().postDelayed(new Runnable() {
                            public void run() {
                                if (MyApplication.enforceable) {
                                    onCheckListener.find(0);
                                }
                            }
                        }, (long) i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void initScanQrCode(AutoService autoService, String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, OnCheckListener5 onCheckListener5) {
        if (PackageUtils.getAppNamePy().equals(PackageUtils.getWSBYAppName(MyApplication.getConText()))) {
            final int i4 = i3;
            final OnCheckListener5 onCheckListener52 = onCheckListener5;
            final int i5 = i;
            final AutoService autoService2 = autoService;
            final String str6 = str;
            final String str7 = str2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            final int i6 = i2;
            new Thread(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    int unused = PerformClickUtils2.this.executeX = 0;
                    int access$000 = PerformClickUtils2.this.executeX;
                    while (access$000 < i4) {
                        int unused2 = PerformClickUtils2.this.executeX = access$000;
                        if (MyApplication.enforceable) {
                            if (PerformClickUtils2.this.executeX != i4 - 1 || onCheckListener52 == null || !MyApplication.enforceable) {
                                if (onCheckListener52 != null && MyApplication.enforceable) {
                                    onCheckListener52.execute(access$000);
                                }
                                if (!z2) {
                                    try {
                                        Thread.sleep((long) i5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                try {
                                    boolean findNodeIsExistById = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str6);
                                    boolean findNodeIsExistById2 = PerformClickUtils2.findNodeIsExistById((AccessibilityService) autoService2, str7);
                                    boolean findNodeIsExistByText = PerformClickUtils2.findNodeIsExistByText(autoService2, str8);
                                    boolean findNodeIsExistByText2 = PerformClickUtils2.findNodeIsExistByText(autoService2, str9);
                                    boolean findNodeIsExistByText3 = PerformClickUtils2.findNodeIsExistByText(autoService2, str10);
                                    if ((!findNodeIsExistById && !findNodeIsExistById2 && !findNodeIsExistByText && !findNodeIsExistByText2 && !findNodeIsExistByText3) || onCheckListener52 == null) {
                                        try {
                                            Thread.sleep((long) i6);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        access$000++;
                                        z2 = z;
                                    } else if (findNodeIsExistById) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(0);
                                        }
                                        int unused3 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistById2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(1);
                                        }
                                        int unused4 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(2);
                                        }
                                        int unused5 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText2) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(3);
                                        }
                                        int unused6 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else if (findNodeIsExistByText3) {
                                        if (MyApplication.enforceable) {
                                            onCheckListener52.find(4);
                                        }
                                        int unused7 = PerformClickUtils2.this.executeX = i4 + 1;
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            } else {
                                onCheckListener52.nuFind();
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }
}
