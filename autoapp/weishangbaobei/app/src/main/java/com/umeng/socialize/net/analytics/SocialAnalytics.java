package com.umeng.socialize.net.analytics;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.bugly.Bugly;
import com.umeng.socialize.Config;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.stats.AuthStatsRequest;
import com.umeng.socialize.net.stats.DauStatsRequest;
import com.umeng.socialize.net.stats.ShareStatsRequest;
import com.umeng.socialize.net.stats.ShareboardStatsRequest;
import com.umeng.socialize.net.stats.StatsAPIs;
import com.umeng.socialize.net.stats.UserInfoStatsRequest;
import com.umeng.socialize.uploadlog.UMLog;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeSpUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SocialAnalytics {
    /* access modifiers changed from: private */
    public static SocializeClient a = new SocializeClient();
    private static ExecutorService b = Executors.newCachedThreadPool();

    public static void log(final Context context, final String str, final String str2, final UMediaObject uMediaObject) {
        a(new Runnable() {
            public void run() {
                a aVar = new a(context, str, str2);
                aVar.a(uMediaObject);
                b bVar = (b) SocialAnalytics.a.execute(aVar);
                if (bVar == null || !bVar.isOk()) {
                    Log.d(" fail to send log");
                } else {
                    Log.d(" send log succeed");
                }
            }
        });
    }

    public static void authstart(Context context, SHARE_MEDIA share_media, String str, boolean z, String str2) {
        final Context context2 = context;
        final SHARE_MEDIA share_media2 = share_media;
        final boolean z2 = z;
        final String str3 = str;
        final String str4 = str2;
        a(new Runnable() {
            public void run() {
                AuthStatsRequest authStatsRequest = new AuthStatsRequest(context2, SocializeReseponse.class);
                authStatsRequest.addStringParams("style", share_media2.getauthstyle(z2));
                authStatsRequest.addStringParams("platform", share_media2.toString().toLowerCase());
                authStatsRequest.addStringParams("version", str3);
                authStatsRequest.addStringParams("tag", str4);
                if (share_media2 == SHARE_MEDIA.QQ) {
                    if (Config.isUmengQQ.booleanValue()) {
                        authStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        authStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media2 == SHARE_MEDIA.SINA) {
                    if (Config.isUmengSina.booleanValue()) {
                        authStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        authStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media2 == SHARE_MEDIA.WEIXIN || share_media2 == SHARE_MEDIA.WEIXIN_CIRCLE || share_media2 == SHARE_MEDIA.WEIXIN_FAVORITE) {
                    if (Config.isUmengWx.booleanValue()) {
                        authStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        authStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                StatsAPIs.authStatsStart(authStatsRequest);
            }
        });
    }

    public static void authendt(Context context, String str, String str2, String str3, String str4) {
        final Context context2 = context;
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str;
        final String str8 = str4;
        a(new Runnable() {
            public void run() {
                AuthStatsRequest authStatsRequest = new AuthStatsRequest(context2, SocializeReseponse.class);
                authStatsRequest.addStringParams("result", str5);
                if (!TextUtils.isEmpty(str6)) {
                    authStatsRequest.addStringParams("errormsg", str6);
                }
                authStatsRequest.addStringParams("platform", str7);
                authStatsRequest.addStringParams("tag", str8);
                StatsAPIs.authStatsEnd(authStatsRequest);
            }
        });
    }

    public static void sharestart(Context context, SHARE_MEDIA share_media, String str, boolean z, int i, String str2, boolean z2) {
        final Context context2 = context;
        final SHARE_MEDIA share_media2 = share_media;
        final boolean z3 = z;
        final String str3 = str;
        final int i2 = i;
        final String str4 = str2;
        final boolean z4 = z2;
        a(new Runnable() {
            public void run() {
                ShareStatsRequest shareStatsRequest = new ShareStatsRequest(context2, SocializeReseponse.class);
                shareStatsRequest.addStringParams("style", share_media2.getsharestyle(z3));
                shareStatsRequest.addStringParams("platform", share_media2.toString().toLowerCase());
                shareStatsRequest.addStringParams("version", str3);
                shareStatsRequest.addStringParams("sharetype", String.valueOf(i2));
                shareStatsRequest.addStringParams("tag", str4);
                shareStatsRequest.addStringParams("usecompose", z4 + "");
                if (share_media2 == SHARE_MEDIA.QQ) {
                    if (Config.isUmengQQ.booleanValue()) {
                        shareStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        shareStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media2 == SHARE_MEDIA.SINA) {
                    if (Config.isUmengSina.booleanValue()) {
                        shareStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        shareStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media2 == SHARE_MEDIA.WEIXIN || share_media2 == SHARE_MEDIA.WEIXIN_CIRCLE || share_media2 == SHARE_MEDIA.WEIXIN_FAVORITE) {
                    if (Config.isUmengWx.booleanValue()) {
                        shareStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        shareStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                StatsAPIs.shareStatsStart(shareStatsRequest);
            }
        });
    }

    public static void shareend(Context context, String str, String str2, String str3, String str4) {
        final Context context2 = context;
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str;
        final String str8 = str4;
        a(new Runnable() {
            public void run() {
                ShareStatsRequest shareStatsRequest = new ShareStatsRequest(context2, SocializeReseponse.class);
                shareStatsRequest.addStringParams("result", str5);
                if (!TextUtils.isEmpty(str6)) {
                    shareStatsRequest.addStringParams("errormsg", str6);
                }
                shareStatsRequest.addStringParams("platform", str7);
                shareStatsRequest.addStringParams("tag", str8);
                StatsAPIs.shareStatsEnd(shareStatsRequest);
            }
        });
    }

    public static void getInfostart(final Context context, final SHARE_MEDIA share_media, final String str, final String str2) {
        a(new Runnable() {
            public void run() {
                UserInfoStatsRequest userInfoStatsRequest = new UserInfoStatsRequest(context, SocializeReseponse.class);
                userInfoStatsRequest.addStringParams("platform", share_media.toString().toLowerCase());
                userInfoStatsRequest.addStringParams("version", str);
                userInfoStatsRequest.addStringParams("tag", str2);
                if (share_media == SHARE_MEDIA.QQ) {
                    if (Config.isUmengQQ.booleanValue()) {
                        userInfoStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        userInfoStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media == SHARE_MEDIA.SINA) {
                    if (Config.isUmengSina.booleanValue()) {
                        userInfoStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        userInfoStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                if (share_media == SHARE_MEDIA.WEIXIN || share_media == SHARE_MEDIA.WEIXIN_CIRCLE || share_media == SHARE_MEDIA.WEIXIN_FAVORITE) {
                    if (Config.isUmengWx.booleanValue()) {
                        userInfoStatsRequest.addStringParams("isumeng", "true");
                    } else {
                        userInfoStatsRequest.addStringParams("isumeng", Bugly.SDK_IS_DEV);
                    }
                }
                StatsAPIs.userInfoStatsStart(userInfoStatsRequest);
            }
        });
    }

    public static void getInfoendt(Context context, String str, String str2, String str3, String str4) {
        final Context context2 = context;
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str4;
        final String str8 = str;
        a(new Runnable() {
            public void run() {
                UserInfoStatsRequest userInfoStatsRequest = new UserInfoStatsRequest(context2, SocializeReseponse.class);
                userInfoStatsRequest.addStringParams("result", str5);
                if (!TextUtils.isEmpty(str6)) {
                    userInfoStatsRequest.addStringParams("errormsg", str6);
                }
                userInfoStatsRequest.addStringParams("tag", str7);
                userInfoStatsRequest.addStringParams("platform", str8);
                StatsAPIs.userInfoStatsEnd(userInfoStatsRequest);
            }
        });
    }

    public static void dauStats(final Context context, final boolean z) {
        a(new Runnable() {
            public void run() {
                try {
                    DauStatsRequest dauStatsRequest = new DauStatsRequest(context, SocializeReseponse.class);
                    Bundle shareAndAuth = UMLog.getShareAndAuth();
                    if (shareAndAuth != null) {
                        dauStatsRequest.addStringParams("isshare", String.valueOf(shareAndAuth.getBoolean("share")));
                        dauStatsRequest.addStringParams("isauth", String.valueOf(shareAndAuth.getBoolean("auth")));
                        dauStatsRequest.addStringParams("isjump", String.valueOf(shareAndAuth.getBoolean("isjump")));
                        dauStatsRequest.addStringParams(SocializeConstants.USHARETYPE, Config.shareType);
                        dauStatsRequest.addStringParams("ni", z ? "1" : "0");
                        dauStatsRequest.addStringParams("pkname", ContextUtil.getPackageName());
                        dauStatsRequest.addStringParams("useshareview", String.valueOf(UMLog.isOpenShareEdit()));
                    }
                    StatsAPIs.dauStats(dauStatsRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void shareBoardStats(final Context context) {
        a(new Runnable() {
            public void run() {
                String shareBoardConfig = SocializeSpUtils.getShareBoardConfig(context);
                if (!TextUtils.isEmpty(shareBoardConfig)) {
                    try {
                        String[] split = shareBoardConfig.split(";");
                        if (split.length == 2) {
                            String str = split[0];
                            String str2 = split[1];
                            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                                ShareboardStatsRequest shareboardStatsRequest = new ShareboardStatsRequest(context, SocializeReseponse.class);
                                shareboardStatsRequest.addStringParams("position", str2);
                                shareboardStatsRequest.addStringParams("menubg", str);
                                StatsAPIs.shareboardStats(shareboardStatsRequest);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static void a(Runnable runnable) {
        if (b != null && runnable != null) {
            b.submit(runnable);
        }
    }
}
