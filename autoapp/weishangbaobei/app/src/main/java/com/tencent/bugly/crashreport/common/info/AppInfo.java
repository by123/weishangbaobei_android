package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.socialize.ShareContent;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.ByteArrayInputStream;
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

/* compiled from: BUGLY */
public class AppInfo {
    private static ActivityManager a;

    static {
        "@buglyAllChannel@".split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        "@buglyAllChannelPriority@".split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0);
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String equals : strArr) {
                    if (str.equals(equals)) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A[Catch:{ all -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[SYNTHETIC, Splitter:B:28:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC, Splitter:B:33:0x0059] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(int r6) {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x0043 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "/proc/"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0043 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "/cmdline"
            r2.append(r3)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0043 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0043 }
            r0 = 512(0x200, float:7.175E-43)
            char[] r0 = new char[r0]     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r1.read(r0)     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r2 = 0
            r3 = 0
        L_0x0022:
            int r4 = r0.length     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            if (r3 >= r4) goto L_0x002c
            char r4 = r0[r3]     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            if (r4 == 0) goto L_0x002c
            int r3 = r3 + 1
            goto L_0x0022
        L_0x002c:
            java.lang.String r4 = new java.lang.String     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            java.lang.String r0 = r4.substring(r2, r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r1.close()     // Catch:{ Throwable -> 0x0038 }
        L_0x0038:
            return r0
        L_0x0039:
            r6 = move-exception
            r0 = r1
            goto L_0x0057
        L_0x003c:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0044
        L_0x0041:
            r6 = move-exception
            goto L_0x0057
        L_0x0043:
            r1 = move-exception
        L_0x0044:
            boolean r2 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x0041 }
            if (r2 != 0) goto L_0x004d
            r1.printStackTrace()     // Catch:{ all -> 0x0041 }
        L_0x004d:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0056
            r0.close()     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            return r6
        L_0x0057:
            if (r0 == 0) goto L_0x005c
            r0.close()     // Catch:{ Throwable -> 0x005c }
        L_0x005c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.AppInfo.a(int):java.lang.String");
    }

    public static String c(Context context) {
        CharSequence applicationLabel;
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (!(packageManager == null || applicationInfo == null || (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) == null)) {
                return applicationLabel.toString();
            }
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Map<String, String> d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo.metaData == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
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
            return hashMap;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static List<String> a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get("BUGLY_DISABLE");
            if (str != null) {
                if (str.length() != 0) {
                    String[] split = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    for (int i = 0; i < split.length; i++) {
                        split[i] = split[i].trim();
                    }
                    return Arrays.asList(split);
                }
            }
            return null;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static String a(byte[] bArr) {
        X509Certificate x509Certificate;
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length > 0) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                if (instance == null || (x509Certificate = (X509Certificate) instance.generateCertificate(new ByteArrayInputStream(bArr))) == null) {
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
        if (sb.length() == 0) {
            return "unknown";
        }
        return sb.toString();
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
        } catch (PackageManager.NameNotFoundException unused) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0077, code lost:
        r3 = r6;
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076 A[ExcHandler: all (r0v6 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0093 A[SYNTHETIC, Splitter:B:38:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0099 A[SYNTHETIC, Splitter:B:42:0x0099] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String h(android.content.Context r6) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "DENGTA_META"
            android.content.SharedPreferences r1 = com.tencent.bugly.proguard.z.a((java.lang.String) r1, (android.content.Context) r6)
            r2 = 0
            r3 = 0
            java.lang.String r4 = "key_channelpath"
            java.lang.String r5 = ""
            java.lang.String r1 = r1.getString(r4, r5)     // Catch:{ Exception -> 0x008a }
            boolean r4 = com.tencent.bugly.proguard.z.a((java.lang.String) r1)     // Catch:{ Exception -> 0x008a }
            if (r4 == 0) goto L_0x001a
            java.lang.String r1 = "channel.ini"
        L_0x001a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008a }
            java.lang.String r5 = "[AppInfo] Beacon channel file path: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x008a }
            r4.append(r1)     // Catch:{ Exception -> 0x008a }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x008a }
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x008a }
            com.tencent.bugly.proguard.x.a(r4, r5)     // Catch:{ Exception -> 0x008a }
            java.lang.String r4 = ""
            boolean r4 = r1.equals(r4)     // Catch:{ Exception -> 0x008a }
            if (r4 != 0) goto L_0x007c
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ Exception -> 0x008a }
            java.io.InputStream r6 = r6.open(r1)     // Catch:{ Exception -> 0x008a }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            r1.<init>()     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            r1.load(r6)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            java.lang.String r3 = "CHANNEL"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ Exception -> 0x007a, all -> 0x0076 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            java.lang.String r3 = "[AppInfo] Beacon channel read from assert: "
            r0.<init>(r3)     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            r0.append(r1)     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            com.tencent.bugly.proguard.x.a(r0, r3)     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            boolean r0 = com.tencent.bugly.proguard.z.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0073, all -> 0x0076 }
            if (r0 != 0) goto L_0x0071
            if (r6 == 0) goto L_0x0070
            r6.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x0070:
            return r1
        L_0x0071:
            r0 = r1
            goto L_0x007d
        L_0x0073:
            r3 = r6
            r0 = r1
            goto L_0x008a
        L_0x0076:
            r0 = move-exception
            r3 = r6
            r6 = r0
            goto L_0x0097
        L_0x007a:
            r3 = r6
            goto L_0x008a
        L_0x007c:
            r6 = r3
        L_0x007d:
            if (r6 == 0) goto L_0x0096
            r6.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0096
        L_0x0083:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
            goto L_0x0096
        L_0x0088:
            r6 = move-exception
            goto L_0x0097
        L_0x008a:
            java.lang.String r6 = "[AppInfo] Failed to get get beacon channel"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0088 }
            com.tencent.bugly.proguard.x.d(r6, r1)     // Catch:{ all -> 0x0088 }
            if (r3 == 0) goto L_0x0096
            r3.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0096:
            return r0
        L_0x0097:
            if (r3 == 0) goto L_0x00a1
            r3.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a1
        L_0x009d:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x00a1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.AppInfo.h(android.content.Context):java.lang.String");
    }

    private static String i(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE).metaData.get("CHANNEL_DENGTA");
            if (obj != null) {
                return obj.toString();
            }
            return "";
        } catch (Throwable unused) {
            x.d("[AppInfo] Failed to read beacon channel from manifest.", new Object[0]);
            return "";
        }
    }

    public static String g(Context context) {
        if (context == null) {
            return "";
        }
        String h = h(context);
        if (!z.a(h)) {
            return h;
        }
        return i(context);
    }
}
