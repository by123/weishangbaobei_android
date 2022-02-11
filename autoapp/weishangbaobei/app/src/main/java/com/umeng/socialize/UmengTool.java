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

    private static String facebookHashKey(PackageInfo packageInfo) {
        try {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr.length <= 0) {
                return null;
            }
            Signature signature = signatureArr[0];
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(signature.toByteArray());
            return Base64.encodeToString(instance.digest(), 0);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
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

    public static void getREDICRECT_URL(Context context) {
        showDialog(context, getStrRedicrectUrl());
    }

    public static String getStrRedicrectUrl() {
        return ((PlatformConfig.APPIDPlatform) PlatformConfig.configs.get(SHARE_MEDIA.SINA)).redirectUrl;
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

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033 A[Catch:{ ClassNotFoundException -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0060 A[Catch:{ ClassNotFoundException -> 0x010c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String checkWxBySelf(android.content.Context r6) {
        /*
            java.lang.String r0 = r6.getPackageName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ".wxapi.WXEntryActivity"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0024 }
            r4 = 64
            android.content.pm.PackageInfo r4 = r3.getPackageInfo(r0, r4)     // Catch:{ NameNotFoundException -> 0x0022 }
            r2 = r4
            goto L_0x0029
        L_0x0022:
            r4 = move-exception
            goto L_0x0026
        L_0x0024:
            r4 = move-exception
            r3 = r2
        L_0x0026:
            r4.printStackTrace()
        L_0x0029:
            java.lang.String r2 = getSignatureDigest(r2)
            java.lang.Class r4 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r4 != 0) goto L_0x0060
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x010c }
            r6.<init>()     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = "请检查微信后台注册签名："
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = r2.toLowerCase()     // Catch:{ ClassNotFoundException -> 0x010c }
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = "包名："
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            r6.append(r0)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r1 = "没有配置微信回调activity或配置不正确"
            r6.append(r1)     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r6 = r6.toString()     // Catch:{ ClassNotFoundException -> 0x010c }
            return r6
        L_0x0060:
            java.lang.Boolean r5 = com.umeng.socialize.Config.isUmengWx     // Catch:{ ClassNotFoundException -> 0x010c }
            boolean r5 = r5.booleanValue()     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r5 == 0) goto L_0x0085
            java.lang.Class r5 = r4.getSuperclass()     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r5 == 0) goto L_0x0082
            java.lang.Class r4 = r4.getSuperclass()     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r4 = r4.toString()     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r5 = "com.umeng.weixin"
            boolean r4 = r4.contains(r5)     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r4 == 0) goto L_0x007f
            goto L_0x009b
        L_0x007f:
            java.lang.String r6 = "WXEntryActivity配置不正确，您使用的是精简版，请使WXEntryActivity继承com.umeng.weixin.callback.WXCallbackActivity"
            return r6
        L_0x0082:
            java.lang.String r6 = "WXEntryActivity配置不正确，您使用的是精简版，请使WXEntryActivity继承com.umeng.weixin.callback.WXCallbackActivity"
            return r6
        L_0x0085:
            java.lang.Class r5 = r4.getSuperclass()     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r5 == 0) goto L_0x0109
            java.lang.Class r4 = r4.getSuperclass()     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r4 = r4.toString()     // Catch:{ ClassNotFoundException -> 0x010c }
            java.lang.String r5 = "com.umeng.socialize"
            boolean r4 = r4.contains(r5)     // Catch:{ ClassNotFoundException -> 0x010c }
            if (r4 == 0) goto L_0x0106
        L_0x009b:
            android.content.ComponentName r4 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NameNotFoundException -> 0x00d5 }
            r4.<init>(r6, r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            r6 = 0
            r3.getActivityInfo(r4, r6)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x00d5 }
            r6.<init>()     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = "请检查微信后台注册签名："
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = r2.toLowerCase()     // Catch:{ NameNotFoundException -> 0x00d5 }
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = "包名："
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            r6.append(r0)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r1 = "Activity微信配置正确"
            r6.append(r1)     // Catch:{ NameNotFoundException -> 0x00d5 }
            java.lang.String r6 = r6.toString()     // Catch:{ NameNotFoundException -> 0x00d5 }
            return r6
        L_0x00d5:
            r6 = move-exception
            r6.printStackTrace()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "请检查微信后台注册签名："
            r6.append(r1)
            java.lang.String r1 = r2.toLowerCase()
            r6.append(r1)
            java.lang.String r1 = "\n"
            r6.append(r1)
            java.lang.String r1 = "包名："
            r6.append(r1)
            r6.append(r0)
            java.lang.String r0 = "\n"
            r6.append(r0)
            java.lang.String r0 = "没有配置微信回调activity没有在Manifest中配置"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            return r6
        L_0x0106:
            java.lang.String r6 = "WXEntryActivity配置不正确，您使用的是完整版，请使WXEntryActivity继承com.umeng.socialize.weixin.view.WXCallbackActivity"
            return r6
        L_0x0109:
            java.lang.String r6 = "WXEntryActivity配置不正确，您使用的是完整版，请使WXEntryActivity继承com.umeng.socialize.weixin.view.WXCallbackActivity"
            return r6
        L_0x010c:
            r6 = move-exception
            r6.printStackTrace()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "请检查微信后台注册签名："
            r6.append(r1)
            java.lang.String r1 = r2.toLowerCase()
            r6.append(r1)
            java.lang.String r1 = "\n"
            r6.append(r1)
            java.lang.String r1 = "包名："
            r6.append(r1)
            r6.append(r0)
            java.lang.String r0 = "\n"
            r6.append(r0)
            java.lang.String r0 = "没有配置微信回调activity或配置不正确"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.UmengTool.checkWxBySelf(android.content.Context):java.lang.String");
    }

    public static void checkWx(Context context) {
        showDialog(context, checkWxBySelf(context));
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

    public static void checkSina(Context context) {
        showDialog(context, checkSinaBySelf(context));
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
                    if (applicationInfo != null) {
                        if (applicationInfo.metaData.get("com.facebook.sdk.ApplicationId") == null) {
                            return "没有在AndroidManifest中配置facebook的appid";
                        }
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
                } catch (Exception unused) {
                    return "没有在AndroidManifest中配置facebook的appid";
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                return "没有在AndroidManifest.xml中配置com.umeng.facebook.FacebookContentProvider,请阅读友盟官方文档";
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            return "没有在AndroidManifest.xml中配置com.umeng.facebook.FacebookActivity,请阅读友盟官方文档";
        }
    }

    public static String checkQQByself(Context context) {
        String str = "com.tencent.tauth.AuthActivity";
        String str2 = "com.tencent.connect.common.AssistActivity";
        if (Config.isUmengQQ.booleanValue()) {
            str = "com.umeng.qq.tencent.AuthActivity";
            str2 = "com.umeng.qq.tencent.AssistActivity";
        }
        try {
            boolean z = false;
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
            } catch (PackageManager.NameNotFoundException unused) {
                return "没有在AndroidManifest.xml中检测到" + str2 + ",请加上" + str2 + ",详细信息请查看官网文档.";
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            return "没有在AndroidManifest.xml中检测到" + str + ",请加上" + str + ",并配置<data android:scheme=\"tencent" + "appid" + "\" />,详细信息请查看官网文档.";
        }
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
        } catch (PackageManager.NameNotFoundException unused) {
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
        } catch (PackageManager.NameNotFoundException unused) {
            return "签名获取失败";
        }
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
        } catch (PackageManager.NameNotFoundException unused) {
            return "签名获取失败";
        }
    }

    public static void checkQQ(Context context) {
        showDialog(context, checkQQByself(context));
    }

    public static void checkFacebook(Context context) {
        showDialog(context, checkFBByself(context));
    }

    public static void checkVK(Context context) {
        showDialog(context, checkVKByself(context));
    }
}
