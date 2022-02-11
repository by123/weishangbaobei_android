package com.umeng.socialize;

import com.tencent.bugly.BuglyStrategy;

public class Config {
    public static boolean DEBUG = false;
    public static String Descriptor = "com.umeng.share";
    public static String EntityKey = null;
    public static String EntityName = "share";
    @Deprecated
    public static int KaKaoLoginType = 0;
    @Deprecated
    public static int LinkedInProfileScope = 0;
    @Deprecated
    public static int LinkedInShareCode = 0;
    public static String MORE_TITLE = "分享";
    @Deprecated
    public static boolean OpenEditor = true;
    @Deprecated
    public static String QQAPPNAME = "";
    @Deprecated
    public static int QQWITHQZONE = 2;
    public static String SessionId = null;
    public static String UID = null;
    @Deprecated
    public static String appName = null;
    public static int connectionTimeOut = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
    public static boolean isFacebookRead = false;
    public static boolean isJumptoAppStore = false;
    @Deprecated
    public static boolean isNeedAuth = false;
    public static Boolean isUmengQQ = true;
    public static Boolean isUmengSina = true;
    public static Boolean isUmengWx = true;
    public static final boolean mEncrypt = true;
    public static int readSocketTimeOut = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
    public static String shareType = "native";
}
