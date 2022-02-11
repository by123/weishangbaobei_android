package com.umeng.socialize;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.tencent.mm.opensdk.channel.MMessageActV2;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

public class UmengTool {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static void checkAlipay(Context context) {
        String packageName = context.getPackageName();
        try {
            if (Class.forName(packageName + ".apshare.ShareEntryActivity") == null) {
                showDialog(context, "没有配置支付宝回调activity或配置不正确");
            } else {
                showDialog(context, "支付宝配置正确");
            }
        } catch (ClassNotFoundException e) {
            showDialog(context, "没有配置支付宝回调activity或配置不正确");
            e.printStackTrace();
        }
    }

    @TargetApi(9)
    public static String checkFBByself(Context context) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.umeng.facebook.FacebookActivity"), 0);
            try {
                context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "com.umeng.facebook.FacebookContentProvider"), 0);
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
                    if (applicationInfo != null && applicationInfo.metaData.get("com.facebook.sdk.ApplicationId") == null) {
                        return "没有在AndroidManifest中配置facebook的appid";
                    }
                    if (context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName()) <= 0) {
                        return "没有找到facebook_app_id，facebook的id必须写在string文件中且名字必须用facebook_app_id";
                    }
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                        return "facebook 配置正确，请检查fb后台签名:" + facebookHashKey(packageInfo);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        return "";
                    }
                } catch (Exception e2) {
                    return "没有在AndroidManifest中配置facebook的appid";
                }
            } catch (PackageManager.NameNotFoundException e3) {
                return "没有在AndroidManifest.xml中配置com.umeng.facebook.FacebookContentProvider,请阅读友盟官方文档";
            }
        } catch (PackageManager.NameNotFoundException e4) {
            return "没有在AndroidManifest.xml中配置com.umeng.facebook.FacebookActivity,请阅读友盟官方文档";
        }
    }

    public static void checkFacebook(Context context) {
        showDialog(context, checkFBByself(context));
    }

    public static String checkKakao(Context context) {
        String packageName = context.getPackageName();
        context.getPackageManager();
        if (TextUtils.isEmpty(packageName)) {
            return "包名为空";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            return "kakao 配置正确，请检查kakao后台签名:" + facebookHashKey(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            return "签名获取失败";
        }
    }

    public static String checkLinkin(Context context) {
        String packageName = context.getPackageName();
        context.getPackageManager();
        if (TextUtils.isEmpty(packageName)) {
            return "包名为空";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            return "领英 配置正确，请检查领英后台签名:" + facebookHashKey(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            return "签名获取失败";
        }
    }

    public static void checkQQ(Context context) {
        showDialog(context, checkQQByself(context));
    }

    public static String checkQQByself(Context context) {
        boolean z = false;
        String str = "com.tencent.tauth.AuthActivity";
        String str2 = "com.tencent.connect.common.AssistActivity";
        if (Config.isUmengQQ.booleanValue()) {
            str = "com.umeng.qq.tencent.AuthActivity";
            str2 = "com.umeng.qq.tencent.AssistActivity";
        }
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), str), 0);
            try {
                ComponentName componentName = new ComponentName(context.getPackageName(), str2);
                PackageManager packageManager = context.getPackageManager();
                packageManager.getActivityInfo(componentName, 0);
                if (packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", context.getPackageName()) == 0) {
                    z = true;
                }
                if (!z) {
                    return "qq 权限配置不正确，缺少android.permission.WRITE_EXTERNAL_STORAGE";
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                PlatformConfig.APPIDPlatform aPPIDPlatform = (PlatformConfig.APPIDPlatform) PlatformConfig.getPlatform(SHARE_MEDIA.QQ);
                if (aPPIDPlatform == null || TextUtils.isEmpty(aPPIDPlatform.appId)) {
                    return "qq配置不正确，没有检测到qq的id配置";
                }
                intent.setData(Uri.parse(SocializeProtocolConstants.PROTOCOL_KEY_TENCENT + aPPIDPlatform.appId + ":"));
                List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
                if (queryIntentActivities.size() <= 0) {
                    return "qq配置不正确，AndroidManifest中AuthActivity的data中要加入自己的qq应用id";
                }
                for (ResolveInfo next : queryIntentActivities) {
                    if (next.activityInfo != null && next.activityInfo.packageName.equals(context.getPackageName())) {
                        return "qq配置正确";
                    }
                }
                return "qq配置不正确，AndroidManifest中AuthActivity的data中要加入自己的qq应用id";
            } catch (PackageManager.NameNotFoundException e) {
                return "没有在AndroidManifest.xml中检测到" + str2 + ",请加上" + str2 + ",详细信息请查看官网文档.";
            }
        } catch (PackageManager.NameNotFoundException e2) {
            return "没有在AndroidManifest.xml中检测到" + str + ",请加上" + str + ",并配置<data android:scheme=\"tencent" + "appid" + "\" />,详细信息请查看官网文档.";
        }
    }

    public static void checkSina(Context context) {
        showDialog(context, checkSinaBySelf(context));
    }

    public static String checkSinaBySelf(Context context) {
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        String signatureDigest = getSignatureDigest(packageInfo);
        try {
            if (Class.forName("com.sina.weibo.sdk.share.WbShareTransActivity") == null) {
                return "请检查sina后台注册签名：" + signatureDigest.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "回调地址：" + getStrRedicrectUrl() + "\n" + "没有配置新浪回调activity或配置不正确";
            }
            return "请检查sina后台注册签名：" + signatureDigest.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "回调地址：" + getStrRedicrectUrl() + "新浪配置正确";
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return "请检查sina后台注册签名：" + signatureDigest.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "回调地址：" + getStrRedicrectUrl() + "没有配置新浪回调activity或配置不正确";
        }
    }

    public static void checkVK(Context context) {
        showDialog(context, checkVKByself(context));
    }

    public static String checkVKByself(Context context) {
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (TextUtils.isEmpty(packageName)) {
            return "包名为空";
        }
        try {
            String certificateSHA1Fingerprint = getCertificateSHA1Fingerprint(packageManager.getPackageInfo(packageName, 64));
            return "你使用的签名：" + certificateSHA1Fingerprint.replace(":", "");
        } catch (PackageManager.NameNotFoundException e) {
            return "签名获取失败";
        }
    }

    public static void checkWx(Context context) {
        showDialog(context, checkWxBySelf(context));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a A[Catch:{ ClassNotFoundException -> 0x0104 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d A[SYNTHETIC, Splitter:B:14:0x005d] */
    public static String checkWxBySelf(Context context) {
        PackageManager packageManager;
        Class<?> cls;
        PackageInfo packageInfo = null;
        String packageName = context.getPackageName();
        String str = packageName + MMessageActV2.DEFAULT_ENTRY_CLASS_NAME;
        try {
            packageManager = context.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(packageName, 64);
            } catch (PackageManager.NameNotFoundException e) {
                e = e;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            packageManager = null;
            e.printStackTrace();
            String signatureDigest = getSignatureDigest(packageInfo);
            cls = Class.forName(str);
            if (cls != null) {
            }
        }
        String signatureDigest2 = getSignatureDigest(packageInfo);
        try {
            cls = Class.forName(str);
            if (cls != null) {
                return "请检查微信后台注册签名：" + signatureDigest2.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "没有配置微信回调activity或配置不正确";
            }
            if (Config.isUmengWx.booleanValue()) {
                if (cls.getSuperclass() == null || !cls.getSuperclass().toString().contains("com.umeng.weixin")) {
                    return "WXEntryActivity配置不正确，您使用的是精简版，请使WXEntryActivity继承com.umeng.weixin.callback.WXCallbackActivity";
                }
            } else if (cls.getSuperclass() == null || !cls.getSuperclass().toString().contains("com.umeng.socialize")) {
                return "WXEntryActivity配置不正确，您使用的是完整版，请使WXEntryActivity继承com.umeng.socialize.weixin.view.WXCallbackActivity";
            }
            try {
                packageManager.getActivityInfo(new ComponentName(context.getPackageName(), str), 0);
                return "请检查微信后台注册签名：" + signatureDigest2.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "Activity微信配置正确";
            } catch (PackageManager.NameNotFoundException e3) {
                e3.printStackTrace();
                return "请检查微信后台注册签名：" + signatureDigest2.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "没有配置微信回调activity没有在Manifest中配置";
            }
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            return "请检查微信后台注册签名：" + signatureDigest2.toLowerCase() + "\n" + "包名：" + packageName + "\n" + "没有配置微信回调activity或配置不正确";
        }
    }

    private static String facebookHashKey(PackageInfo packageInfo) {
        try {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                return Base64.encodeToString(instance.digest(), 0);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String getCertificateSHA1Fingerprint(PackageInfo packageInfo) {
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e2) {
            e2.printStackTrace();
            x509Certificate = null;
        }
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (CertificateEncodingException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static void getREDICRECT_URL(Context context) {
        showDialog(context, getStrRedicrectUrl());
    }

    public static void getSignature(Context context) {
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            Toast.makeText(context, "应用程序的包名不能为空！", 0).show();
            return;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
            String signatureDigest = getSignatureDigest(packageInfo);
            showDialog(context, "包名：" + packageName + "\n" + "签名:" + signatureDigest.toLowerCase() + "\n" + "facebook keyhash:" + facebookHashKey(packageInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getSignatureDigest(PackageInfo packageInfo) {
        if (packageInfo.signatures.length <= 0) {
            return "";
        }
        Signature signature = packageInfo.signatures[0];
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return toHexString(messageDigest.digest(signature.toByteArray()));
    }

    public static String getStrRedicrectUrl() {
        return ((PlatformConfig.APPIDPlatform) PlatformConfig.configs.get(SHARE_MEDIA.SINA)).redirectUrl;
    }

    public static void showDialog(Context context, String str) {
        new AlertDialog.Builder(context).setTitle("友盟Debug模式自检").setMessage(str).setPositiveButton("关闭", (DialogInterface.OnClickListener) null).show();
    }

    public static void showDialogWithURl(final Context context, String str, final String str2) {
        new AlertDialog.Builder(context).setTitle("友盟Debug模式自检").setMessage(str).setPositiveButton("关闭", (DialogInterface.OnClickListener) null).setNeutralButton("解决方案", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str2));
                context.startActivity(intent);
            }
        }).show();
    }

    private static String toHexString(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = HEX_CHAR[(b >>> 4) & 15];
            cArr[i2 + 1] = HEX_CHAR[b & ap.m];
        }
        return new String(cArr);
    }
}
