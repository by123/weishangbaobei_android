package com.wx.assistants.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.github.promeg.pinyinhelper.Pinyin;
import com.wx.assistants.application.MyApplication;

public class PackageUtils {
    public static String getAppNamePy() {
        try {
            return Pinyin.toPinyin("微上包被", "/");
        } catch (Exception e) {
            return "weishangbaobei";
        }
    }

    public static String getAppNamePy(String str) {
        try {
            return Pinyin.toPinyin(str, "/");
        } catch (Exception e) {
            return "weishangbaobei";
        }
    }

    public static int getVersionCode(Context context) {
        int i;
        synchronized (PackageUtils.class) {
            try {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Class<PackageUtils> cls = PackageUtils.class;
                return 1;
            } catch (Throwable th) {
                Class<PackageUtils> cls2 = PackageUtils.class;
                throw th;
            }
        }
        return i;
    }

    public static String getVersionName(Context context) {
        String str;
        synchronized (PackageUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Class<PackageUtils> cls = PackageUtils.class;
                return "未知版本名";
            } catch (Throwable th) {
                Class<PackageUtils> cls2 = PackageUtils.class;
                throw th;
            }
        }
        return str;
    }

    public static String getWSBYAppName(Context context) {
        String string;
        synchronized (PackageUtils.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
                if (string != null && !"".equals(string)) {
                    string = getAppNamePy(string);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Class<PackageUtils> cls = PackageUtils.class;
                return null;
            } catch (Throwable th) {
                Class<PackageUtils> cls2 = PackageUtils.class;
                throw th;
            }
        }
        return string;
    }

    public static String[] getWSBabyVersion(Context context) throws PackageManager.NameNotFoundException {
        String str;
        String str2;
        synchronized (PackageUtils.class) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(MyApplication.WS, 0);
                int i = packageInfo.versionCode;
                str = packageInfo.versionName;
                str2 = i + "";
            } catch (Throwable th) {
                Class<PackageUtils> cls = PackageUtils.class;
                throw th;
            }
        }
        return new String[]{str2, str};
    }

    public static int getWXVersionCode(Context context) throws PackageManager.NameNotFoundException {
        int i;
        synchronized (PackageUtils.class) {
            try {
                i = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionCode;
            } catch (Throwable th) {
                Class<PackageUtils> cls = PackageUtils.class;
                throw th;
            }
        }
        return i;
    }

    public static int getWXVersionCodeCompany(Context context) throws PackageManager.NameNotFoundException {
        int i = 0;
        synchronized (PackageUtils.class) {
            try {
                i = context.getPackageManager().getPackageInfo("com.tencent.wework", 0).versionCode;
            } catch (Exception e) {
                Class<PackageUtils> cls = PackageUtils.class;
            } catch (Throwable th) {
                Class<PackageUtils> cls2 = PackageUtils.class;
                throw th;
            }
        }
        return i;
    }

    public static String getWXVersionName(Context context) throws PackageManager.NameNotFoundException {
        String str;
        synchronized (PackageUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
            } catch (Throwable th) {
                Class<PackageUtils> cls = PackageUtils.class;
                throw th;
            }
        }
        return str;
    }

    public static String getWXVersionNameCompany(Context context) throws PackageManager.NameNotFoundException {
        String str;
        synchronized (PackageUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo("com.tencent.wework", 0).versionName;
            } catch (Exception e) {
                Class<PackageUtils> cls = PackageUtils.class;
                return "";
            } catch (Throwable th) {
                Class<PackageUtils> cls2 = PackageUtils.class;
                throw th;
            }
        }
        return str;
    }

    public static long getWxLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        long j;
        synchronized (PackageUtils.class) {
            try {
                j = context.getPackageManager().getPackageInfo("com.tencent.mm", 0).lastUpdateTime;
            } catch (Throwable th) {
                Class<PackageUtils> cls = PackageUtils.class;
                throw th;
            }
        }
        return j;
    }
}
