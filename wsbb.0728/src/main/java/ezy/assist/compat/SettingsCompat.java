package ezy.assist.compat;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

public class SettingsCompat {
    private static final String HUAWEI_PACKAGE = "com.huawei.systemmanager";
    private static final int OP_SYSTEM_ALERT_WINDOW = 24;
    private static final int OP_WRITE_SETTINGS = 23;
    private static final String TAG = "ezy-settings-compat";

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return checkOp(context, 24);
        }
        return true;
    }

    public static boolean canWriteSettings(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.System.canWrite(context);
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return checkOp(context, 23);
        }
        return true;
    }

    private static boolean checkOp(Context context, int i) {
        try {
            return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke((AppOpsManager) context.getSystemService("appops"), new Object[]{Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }

    public static void manageDrawOverlays(Context context) {
        if ((Build.VERSION.SDK_INT < 18 || !manageDrawOverlaysForRom(context)) && Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

    private static boolean manageDrawOverlaysForEmui(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 21) {
            intent.setClassName(HUAWEI_PACKAGE, "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
            if (startSafely(context, intent)) {
                return true;
            }
        }
        intent.setClassName(HUAWEI_PACKAGE, "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
        intent.putExtra("showTabsNumber", 1);
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName(HUAWEI_PACKAGE, "com.huawei.permissionmanager.ui.MainActivity");
        return startSafely(context, intent);
    }

    private static boolean manageDrawOverlaysForFlyme(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        return startSafely(context, intent);
    }

    private static boolean manageDrawOverlaysForMiui(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return false;
        }
        Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent2.setData(Uri.fromParts("package", context.getPackageName(), (String) null));
        return startSafely(context, intent2);
    }

    private static boolean manageDrawOverlaysForOppo(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setAction("com.oppo.safe");
        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.floatwindow.FloatWindowListActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setAction("com.color.safecenter");
        intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setAction("com.coloros.safecenter");
        intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
        return startSafely(context, intent);
    }

    private static boolean manageDrawOverlaysForQihu(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        return startSafely(context, intent);
    }

    private static boolean manageDrawOverlaysForRom(Context context) {
        if (RomUtil.isMiui()) {
            return manageDrawOverlaysForMiui(context);
        }
        if (RomUtil.isEmui()) {
            return manageDrawOverlaysForEmui(context);
        }
        if (RomUtil.isFlyme()) {
            return manageDrawOverlaysForFlyme(context);
        }
        if (RomUtil.isOppo()) {
            return manageDrawOverlaysForOppo(context);
        }
        if (RomUtil.isVivo()) {
            return manageDrawOverlaysForVivo(context);
        }
        if (RomUtil.isQiku()) {
            return manageDrawOverlaysForQihu(context);
        }
        if (RomUtil.isSmartisan()) {
            return manageDrawOverlaysForSmartisan(context);
        }
        return false;
    }

    private static boolean manageDrawOverlaysForSmartisan(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Intent intent = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS_NEW");
            intent.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
            intent.putExtra("index", 17);
            return startSafely(context, intent);
        }
        Intent intent2 = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS");
        intent2.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
        intent2.putExtra("permission", new String[]{"android.permission.SYSTEM_ALERT_WINDOW"});
        return startSafely(context, intent2);
    }

    private static boolean manageDrawOverlaysForVivo(Context context) {
        Intent intent = new Intent("com.iqoo.secure");
        intent.setClassName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        return startSafely(context, intent);
    }

    public static void manageWriteSettings(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

    public static boolean setDrawOverlays(Context context, boolean z) {
        return setMode(context, 24, z);
    }

    private static boolean setMode(Context context, int i, boolean z) {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return false;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        Class<AppOpsManager> cls = AppOpsManager.class;
        try {
            cls.getDeclaredMethod("setMode", new Class[]{Integer.TYPE, Integer.TYPE, String.class, Integer.TYPE}).invoke(appOpsManager, new Object[]{Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName(), Integer.valueOf(z ^ true ? 1 : 0)});
            return true;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }

    public static boolean setWriteSettings(Context context, boolean z) {
        return setMode(context, 23, z);
    }

    private static boolean startSafely(Context context, Intent intent) {
        if (context.getPackageManager().queryIntentActivities(intent, WXMediaMessage.THUMB_LENGTH_LIMIT).size() > 0) {
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        }
        Log.e(TAG, "Intent is not available! " + intent);
        return false;
    }
}
