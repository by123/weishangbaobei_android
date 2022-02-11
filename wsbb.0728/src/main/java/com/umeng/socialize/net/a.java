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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
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
        String str;
        try {
            str = new String(b(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = "";
        }
        try {
            return a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
        } catch (Exception e2) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057 A[SYNTHETIC, Splitter:B:16:0x0057] */
    private static String a(String str, String str2) {
        ByteArrayOutputStream byteArrayOutputStream;
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, a(str2));
        byte[] bytes = str.getBytes("UTF-8");
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (true) {
                try {
                    int a = a(bytes, i, 117);
                    if (a == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(instance.doFinal(bytes, i, a));
                    i += a;
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                    }
                    throw th;
                }
            }
            byteArrayOutputStream.flush();
            String str3 = "01" + new String(Base64.encodebyte(byteArrayOutputStream.toByteArray()), "UTF-8");
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
            }
            return str3;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    private static PublicKey a(String str) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes())));
    }

    private static String b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception e) {
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
        } catch (JSONException e3) {
            return "";
        }
    }

    private static String c() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return "";
        }
    }

    private static String c(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String d() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private static String d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    private static String e() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private static String e(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getMacAddress() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String f() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    private static String g(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + displayMetrics.heightPixels;
        } catch (Exception e) {
            return "";
        }
    }

    private static String i(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static String j(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return "2G";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return "3G";
                        case 13:
                            return "4G";
                        default:
                            return SchedulerSupport.NONE;
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    return "wifi";
                }
            }
            return SchedulerSupport.NONE;
        } catch (Exception e) {
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
