package com.wx.assistants.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.wx.assistants.application.MyApplication;
import java.util.Collections;
import java.util.List;

public class StartOtherAppUtils {
    private static final String WX_MAIN_CLASS = "com.tencent.mm.ui.LauncherUI";
    private static final String WX_MAIN_CLASS_COMPANY = "com.tencent.wework.launch.WwMainActivity";
    private static final String WX_PACKAGE = "com.tencent.mm";
    private static final String WX_PACKAGE_COMPANY = "com.tencent.wework";
    private static List<ResolveInfo> mAllApps;
    private static Context mContext;
    private static PackageManager mPackageManager;

    public static void SystemOutApp(Context context) {
        mContext = context;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        mPackageManager = mContext.getPackageManager();
        mAllApps = mPackageManager.queryIntentActivities(intent, 0);
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));
        for (ResolveInfo next : mAllApps) {
            String charSequence = next.loadLabel(mPackageManager).toString();
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            LogUtils.log("WS_BABY_labelName:" + charSequence);
            LogUtils.log("WS_BABY_packageName:" + str);
            LogUtils.log("WS_BABY_className:" + str2);
            LogUtils.log("WS_BABY_className:" + str2);
        }
    }

    public static boolean openApp(Context context, String str) {
        mContext = context;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        mPackageManager = mContext.getPackageManager();
        mAllApps = mPackageManager.queryIntentActivities(intent, 0);
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));
        for (ResolveInfo next : mAllApps) {
            if (str.equals(next.loadLabel(mPackageManager).toString())) {
                ComponentName componentName = new ComponentName(next.activityInfo.packageName, next.activityInfo.name);
                Intent intent2 = new Intent();
                intent2.setComponent(componentName);
                intent2.addFlags(268435456);
                mContext.startActivity(intent2);
                return true;
            }
        }
        return false;
    }

    public static void startWeChat() {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setClassName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        MyApplication.instance.startActivity(intent);
    }

    public static void startWeChatCompany() {
        MyApplication.instance.startActivity(MyApplication.instance.getPackageManager().getLaunchIntentForPackage(WX_PACKAGE_COMPANY));
    }
}
