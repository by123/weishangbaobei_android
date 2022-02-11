package com.wx.assistants.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.github.promeg.pinyinhelper.Pinyin;
import com.wx.assistants.application.MyApplication;

public class PackageUtils {
    public static synchronized String getVersionName(Context context) {
        String str;
        synchronized (PackageUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "未知版本名";
            }
        }
        return str;
    }

    public static synchronized int getVersionCode(Context context) {
        int i;
        synchronized (PackageUtils.class) {
            try {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return 1;
            }
        }
        return i;
    }

    public static synchronized String getWXVersionName(Context context) throws PackageManager.NameNotFoundException {
        String str;
        synchronized (PackageUtils.class) {
            str = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
        }
        return str;
    }

    public static synchronized int getWXVersionCode(Context context) throws PackageManager.NameNotFoundException {
        int i;
        synchronized (PackageUtils.class) {
            i = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionCode;
        }
        return i;
    }

    public static synchronized long getWxLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        long j;
        synchronized (PackageUtils.class) {
            j = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).lastUpdateTime;
        }
        return j;
    }

    public static synchronized String getWXVersionNameCompany(Context context) throws PackageManager.NameNotFoundException {
        String str;
        synchronized (PackageUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo("com.tencent.wework", 0).versionName;
            } catch (Exception unused) {
                return "";
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        return 0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int getWXVersionCodeCompany(android.content.Context r3) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            java.lang.Class<com.wx.assistants.utils.PackageUtils> r0 = com.wx.assistants.utils.PackageUtils.class
            monitor-enter(r0)
            r1 = 0
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Exception -> 0x0015, all -> 0x0012 }
            java.lang.String r2 = "com.tencent.wework"
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r2, r1)     // Catch:{ Exception -> 0x0015, all -> 0x0012 }
            int r3 = r3.versionCode     // Catch:{ Exception -> 0x0015, all -> 0x0012 }
            monitor-exit(r0)
            return r3
        L_0x0012:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0015:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.PackageUtils.getWXVersionCodeCompany(android.content.Context):int");
    }

    public static synchronized String[] getWSBabyVersion(Context context) throws PackageManager.NameNotFoundException {
        String[] strArr;
        synchronized (PackageUtils.class) {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(MyApplication.WS, 0);
            int i = packageInfo.versionCode;
            String str = packageInfo.versionName;
            strArr = new String[]{i + "", str};
        }
        return strArr;
    }

    public static synchronized String getWSBYAppName(Context context) {
        String string;
        synchronized (PackageUtils.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
                if (string != null && !"".equals(string)) {
                    string = getAppNamePy(string);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return string;
    }

    public static String getAppNamePy() {
        try {
            return Pinyin.toPinyin("微上包被", "/");
        } catch (Exception unused) {
            return "weishangbaobei";
        }
    }

    public static String getAppNamePy(String str) {
        try {
            return Pinyin.toPinyin(str, "/");
        } catch (Exception unused) {
            return "weishangbaobei";
        }
    }
}
