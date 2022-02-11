package com.umeng.socialize.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.sina.helper.Base64;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.umeng.socialize.sina.util.Utility;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.DeviceConfig;
import io.reactivex.annotations.SchedulerSupport;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends URequest {
    public a(String str) {
        super("https://api.weibo.com/oauth2/getaid.json");
        this.mMethod = URequest.RequestMethod.GET;
        String packageName = ContextUtil.getPackageName();
        String sign = Utility.getSign(ContextUtil.getContext(), packageName);
        String a = a(ContextUtil.getContext());
        addStringParams("appkey", str);
        addStringParams("mfp", a);
        addStringParams(ShareRequestParam.REQ_PARAM_PACKAGENAME, packageName);
        addStringParams(ShareRequestParam.REQ_PARAM_KEY_HASH, sign);
        this.mResponseClz = b.class;
    }

    private static int a(byte[] bArr, int i, int i2) {
        if (i >= bArr.length) {
            return -1;
        }
        return Math.min(bArr.length - i, i2);
    }

    private static String a() {
        return "Android 1";
    }

    private static String a(Context context) {
        String str = "";
        try {
            str = new String(b(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        try {
            return a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
        } catch (Exception unused2) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a A[SYNTHETIC, Splitter:B:18:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r0 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)
            java.security.PublicKey r5 = a((java.lang.String) r5)
            r1 = 1
            r0.init(r1, r5)
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)
            r5 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0056 }
            r1.<init>()     // Catch:{ all -> 0x0056 }
            r5 = 0
        L_0x001b:
            r2 = 117(0x75, float:1.64E-43)
            int r2 = a(r4, r5, r2)     // Catch:{ all -> 0x0054 }
            r3 = -1
            if (r2 == r3) goto L_0x002d
            byte[] r3 = r0.doFinal(r4, r5, r2)     // Catch:{ all -> 0x0054 }
            r1.write(r3)     // Catch:{ all -> 0x0054 }
            int r5 = r5 + r2
            goto L_0x001b
        L_0x002d:
            r1.flush()     // Catch:{ all -> 0x0054 }
            byte[] r4 = r1.toByteArray()     // Catch:{ all -> 0x0054 }
            byte[] r4 = com.umeng.socialize.sina.helper.Base64.encodebyte(r4)     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0054 }
            java.lang.String r0 = "UTF-8"
            r5.<init>(r4, r0)     // Catch:{ all -> 0x0054 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0054 }
            r4.<init>()     // Catch:{ all -> 0x0054 }
            java.lang.String r0 = "01"
            r4.append(r0)     // Catch:{ all -> 0x0054 }
            r4.append(r5)     // Catch:{ all -> 0x0054 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0054 }
            r1.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            return r4
        L_0x0054:
            r4 = move-exception
            goto L_0x0058
        L_0x0056:
            r4 = move-exception
            r1 = r5
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static PublicKey a(String str) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes())));
    }

    private static String b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception unused) {
            return "";
        }
    }

    private static String b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            String a = a();
            if (!TextUtils.isEmpty(a)) {
                jSONObject.put("1", a);
            }
            String deviceId = DeviceConfig.getDeviceId(context);
            if (!TextUtils.isEmpty(deviceId)) {
                jSONObject.put("2", deviceId);
            }
            String c = c(context);
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("3", c);
            }
            String d = d(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("4", d);
            }
            String e = e(context);
            if (!TextUtils.isEmpty(e)) {
                jSONObject.put("5", e);
            }
            String f = f(context);
            if (!TextUtils.isEmpty(f)) {
                jSONObject.put("6", f);
            }
            String b = b();
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("7", b);
            }
            String g = g(context);
            if (!TextUtils.isEmpty(g)) {
                jSONObject.put("10", g);
            }
            String c2 = c();
            if (!TextUtils.isEmpty(c2)) {
                jSONObject.put("13", c2);
            }
            String d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                jSONObject.put("14", d2);
            }
            String e2 = e();
            if (!TextUtils.isEmpty(e2)) {
                jSONObject.put("15", e2);
            }
            String h = h(context);
            if (!TextUtils.isEmpty(h)) {
                jSONObject.put("16", h);
            }
            String i = i(context);
            if (!TextUtils.isEmpty(i)) {
                jSONObject.put("17", i);
            }
            String f2 = f();
            if (!TextUtils.isEmpty(f2)) {
                jSONObject.put("18", f2);
            }
            String j = j(context);
            if (!TextUtils.isEmpty(j)) {
                jSONObject.put("19", j);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private static String c() {
        try {
            return Build.CPU_ABI;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String c(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String d() {
        try {
            return Build.MODEL;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        r1 = r1.getConnectionInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String e(android.content.Context r1) {
        /*
            java.lang.String r0 = "wifi"
            java.lang.Object r1 = r1.getSystemService(r0)     // Catch:{ Exception -> 0x001b }
            android.net.wifi.WifiManager r1 = (android.net.wifi.WifiManager) r1     // Catch:{ Exception -> 0x001b }
            if (r1 != 0) goto L_0x000d
            java.lang.String r1 = ""
            return r1
        L_0x000d:
            android.net.wifi.WifiInfo r1 = r1.getConnectionInfo()     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x0018
            java.lang.String r1 = r1.getMacAddress()     // Catch:{ Exception -> 0x001b }
            goto L_0x001a
        L_0x0018:
            java.lang.String r1 = ""
        L_0x001a:
            return r1
        L_0x001b:
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.a.e(android.content.Context):java.lang.String");
    }

    private static String f() {
        try {
            return Build.BRAND;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + displayMetrics.heightPixels;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String i(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String j(Context context) {
        String str;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return SchedulerSupport.NONE;
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        str = "2G";
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        str = "3G";
                        break;
                    case 13:
                        str = "4G";
                        break;
                    default:
                        str = SchedulerSupport.NONE;
                        break;
                }
            } else if (activeNetworkInfo.getType() != 1) {
                return SchedulerSupport.NONE;
            } else {
                str = "wifi";
            }
            return str;
        } catch (Exception unused) {
            return SchedulerSupport.NONE;
        }
    }

    public Map<String, Object> buildParams() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mParams);
        return hashMap;
    }

    public String toGetUrl() {
        return generateGetURL(getBaseUrl(), buildParams());
    }

    public JSONObject toJson() {
        return null;
    }
}
