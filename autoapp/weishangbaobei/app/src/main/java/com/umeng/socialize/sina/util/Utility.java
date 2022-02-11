package com.umeng.socialize.sina.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.socialize.net.a;
import com.umeng.socialize.net.b;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.sina.helper.MD5;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.util.UUID;

public class Utility {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String WEIBO_IDENTITY_ACTION = "com.sina.weibo.action.sdkidentity";
    private static String aid = "";

    public static String generateGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateUA(Context context) {
        return Build.MANUFACTURER + "-" + Build.MODEL + "_" + Build.VERSION.RELEASE + "_" + "weibosdk" + "_" + "0031405000" + "_android";
    }

    public static String getAid(Context context, String str) {
        if (TextUtils.isEmpty(aid)) {
            b bVar = (b) new SocializeClient().execute(new a(str));
            if (bVar == null || !TextUtils.isEmpty(bVar.c)) {
                Log.um(UmengText.SINA_AID_ERROR);
            } else {
                aid = bVar.a;
            }
        }
        return aid;
    }

    public static String getSign(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature byteArray : packageInfo.signatures) {
                byte[] byteArray2 = byteArray.toByteArray();
                if (byteArray2 != null) {
                    return MD5.hexdigest(byteArray2);
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }
}
