package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.ShareContent;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AppInfo {
    private static ActivityManager a;

    static {
        "@buglyAllChannel@".split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        "@buglyAllChannelPriority@".split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d A[SYNTHETIC, Splitter:B:19:0x003d] */
    public static String a(int i) {
        FileReader fileReader;
        String valueOf;
        FileReader fileReader2 = null;
        int i2 = 0;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
            try {
                char[] cArr = new char[WXMediaMessage.TITLE_LENGTH_LIMIT];
                fileReader.read(cArr);
                while (i2 < cArr.length && cArr[i2] != 0) {
                    i2++;
                }
                valueOf = new String(cArr).substring(0, i2);
                try {
                    fileReader.close();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable th3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileReader = fileReader2;
            if (fileReader != null) {
            }
            throw th;
        }
        return valueOf;
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length > 0) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                if (instance == null) {
                    return null;
                }
                X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(new ByteArrayInputStream(bArr));
                if (x509Certificate == null) {
                    return null;
                }
                sb.append("Issuer|");
                Principal issuerDN = x509Certificate.getIssuerDN();
                if (issuerDN != null) {
                    sb.append(issuerDN.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append("\n");
                sb.append("SerialNumber|");
                BigInteger serialNumber = x509Certificate.getSerialNumber();
                if (issuerDN != null) {
                    sb.append(serialNumber.toString(16));
                } else {
                    sb.append("unknown");
                }
                sb.append("\n");
                sb.append("NotBefore|");
                Date notBefore = x509Certificate.getNotBefore();
                if (issuerDN != null) {
                    sb.append(notBefore.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append("\n");
                sb.append("NotAfter|");
                Date notAfter = x509Certificate.getNotAfter();
                if (issuerDN != null) {
                    sb.append(notAfter.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append("\n");
                sb.append("SHA1|");
                String a2 = z.a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
                if (a2 == null || a2.length() <= 0) {
                    sb.append("unknown");
                } else {
                    sb.append(a2.toString());
                }
                sb.append("\n");
                sb.append("MD5|");
                String a3 = z.a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
                if (a3 == null || a3.length() <= 0) {
                    sb.append("unknown");
                } else {
                    sb.append(a3.toString());
                }
            } catch (CertificateException e) {
                if (!x.a(e)) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return sb.length() == 0 ? "unknown" : sb.toString();
    }

    public static List<String> a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get("BUGLY_DISABLE");
            if (str == null) {
                return null;
            }
            if (str.length() == 0) {
                return null;
            }
            String[] split = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            return Arrays.asList(split);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (String equals : strArr) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String c(Context context) {
        CharSequence applicationLabel;
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (packageManager == null || applicationInfo == null || (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) == null) {
                return null;
            }
            return applicationLabel.toString();
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> d(Context context) {
        HashMap hashMap;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo.metaData != null) {
                hashMap = new HashMap();
                Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
                if (obj != null) {
                    hashMap.put("BUGLY_DISABLE", obj.toString());
                }
                Object obj2 = applicationInfo.metaData.get("BUGLY_APPID");
                if (obj2 != null) {
                    hashMap.put("BUGLY_APPID", obj2.toString());
                }
                Object obj3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
                if (obj3 != null) {
                    hashMap.put("BUGLY_APP_CHANNEL", obj3.toString());
                }
                Object obj4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
                if (obj4 != null) {
                    hashMap.put("BUGLY_APP_VERSION", obj4.toString());
                }
                Object obj5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
                if (obj5 != null) {
                    hashMap.put("BUGLY_ENABLE_DEBUG", obj5.toString());
                }
                Object obj6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
                if (obj6 != null) {
                    hashMap.put("com.tencent.rdm.uuid", obj6.toString());
                }
                Object obj7 = applicationInfo.metaData.get("BUGLY_APP_BUILD_NO");
                if (obj7 != null) {
                    hashMap.put("BUGLY_APP_BUILD_NO", obj7.toString());
                }
                Object obj8 = applicationInfo.metaData.get("BUGLY_AREA");
                if (obj8 != null) {
                    hashMap.put("BUGLY_AREA", obj8.toString());
                }
            } else {
                hashMap = null;
            }
            return hashMap;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static String e(Context context) {
        Signature[] signatureArr;
        String a2 = a(context);
        if (a2 == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a2, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length == 0) {
                return null;
            }
            return a(packageInfo.signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean f(Context context) {
        if (context == null) {
            return false;
        }
        if (a == null) {
            a = (ActivityManager) context.getSystemService("activity");
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            a.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            x.c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public static String g(Context context) {
        if (context == null) {
            return "";
        }
        String h = h(context);
        return z.a(h) ? i(context) : h;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        com.tencent.bugly.proguard.x.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0091 A[SYNTHETIC, Splitter:B:36:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x003d] */
    private static String h(Context context) {
        String str;
        InputStream inputStream = null;
        try {
            String string = z.a("DENGTA_META", context).getString("key_channelpath", "");
            if (z.a(string)) {
                string = "channel.ini";
            }
            x.a("[AppInfo] Beacon channel file path: " + string, new Object[0]);
            if (!string.equals("")) {
                inputStream = context.getAssets().open(string);
                try {
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    str = properties.getProperty("CHANNEL", "");
                    x.a("[AppInfo] Beacon channel read from assert: " + str, new Object[0]);
                    if (!z.a(str)) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                x.a(e);
                            }
                        }
                        return str;
                    }
                } catch (Exception e2) {
                } catch (Throwable th) {
                }
            } else {
                str = "";
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e3) {
            str = "";
        }
        return str;
        try {
            x.d("[AppInfo] Failed to get get beacon channel", new Object[0]);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    x.a(e4);
                }
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
            }
            throw th;
        }
    }

    private static String i(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE).metaData.get("CHANNEL_DENGTA");
            if (obj != null) {
                return obj.toString();
            }
        } catch (Throwable th) {
            x.d("[AppInfo] Failed to read beacon channel from manifest.", new Object[0]);
        }
        return "";
    }
}
