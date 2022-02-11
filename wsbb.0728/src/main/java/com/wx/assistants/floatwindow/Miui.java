package com.wx.assistants.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.wx.assistants.utils.LogUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class Miui {
    /* access modifiers changed from: private */
    public static PermissionListener mPermissionListener = null;
    /* access modifiers changed from: private */
    public static List<PermissionListener> mPermissionListenerList = null;
    private static final String miui = "ro.miui.ui.version.name";
    private static final String miui5 = "V5";
    private static final String miui6 = "V6";
    private static final String miui7 = "V7";
    private static final String miui8 = "V8";
    private static final String miui9 = "V9";

    Miui() {
    }

    private static void addViewToWindow(WindowManager windowManager, View view, WindowManager.LayoutParams layoutParams) {
        setMiUI_International(true);
        windowManager.addView(view, layoutParams);
        setMiUI_International(false);
    }

    private static String getProp() {
        return Rom.getProp(miui);
    }

    static void req(Context context, PermissionListener permissionListener) {
        if (PermissionUtil.hasPermission(context)) {
            permissionListener.onSuccess();
            return;
        }
        if (mPermissionListenerList == null) {
            mPermissionListenerList = new ArrayList();
            mPermissionListener = new PermissionListener() {
                public void onFail() {
                    for (PermissionListener onFail : Miui.mPermissionListenerList) {
                        onFail.onFail();
                    }
                    Miui.mPermissionListenerList.clear();
                }

                public void onSuccess() {
                    for (PermissionListener onSuccess : Miui.mPermissionListenerList) {
                        onSuccess.onSuccess();
                    }
                    Miui.mPermissionListenerList.clear();
                }
            };
            req_(context);
        }
        mPermissionListenerList.add(permissionListener);
    }

    private static void reqForMiui5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", packageName, (String) null));
        intent.setFlags(268435456);
        if (Rom.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            LogUtil.e("intent is not available!");
        }
    }

    private static void reqForMiui67(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (Rom.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            LogUtil.e("intent is not available!");
        }
    }

    private static void reqForMiui89(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (Rom.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }
        Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent2.setPackage("com.miui.securitycenter");
        intent2.putExtra("extra_pkgname", context.getPackageName());
        intent2.setFlags(268435456);
        if (Rom.isIntentAvailable(intent2, context)) {
            context.startActivity(intent2);
        } else {
            LogUtil.e("intent is not available!");
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private static void req_(final Context context) {
        char c;
        String prop = getProp();
        switch (prop.hashCode()) {
            case 2719:
                if (prop.equals(miui5)) {
                    c = 0;
                    break;
                }
            case 2720:
                if (prop.equals(miui6)) {
                    c = 1;
                    break;
                }
            case 2721:
                if (prop.equals(miui7)) {
                    c = 2;
                    break;
                }
            case 2722:
                if (prop.equals(miui8)) {
                    c = 3;
                    break;
                }
            case 2723:
                if (prop.equals(miui9)) {
                    c = 4;
                    break;
                }
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                reqForMiui5(context);
                break;
            case 1:
            case 2:
                reqForMiui67(context);
                break;
            case 3:
            case 4:
                reqForMiui89(context);
                break;
        }
        FloatLifecycle.setResumedListener(new ResumedListener() {
            public void onResumed() {
                if (PermissionUtil.hasPermission(context)) {
                    Miui.mPermissionListener.onSuccess();
                } else {
                    Miui.mPermissionListener.onFail();
                }
            }
        });
    }

    static boolean rom() {
        LogUtil.d(" Miui  : " + getProp());
        return Build.MANUFACTURER.equals("Xiaomi");
    }

    private static void setMiUI_International(boolean z) {
        try {
            Field declaredField = Class.forName("miui.os.Build").getDeclaredField("IS_INTERNATIONAL_BUILD");
            declaredField.setAccessible(true);
            declaredField.setBoolean((Object) null, z);
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_25");
            e.printStackTrace();
        }
    }
}
