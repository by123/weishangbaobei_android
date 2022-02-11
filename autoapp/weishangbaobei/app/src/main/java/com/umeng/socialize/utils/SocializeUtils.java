package com.umeng.socialize.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class SocializeUtils {
    protected static final String TAG = "SocializeUtils";
    public static Set<Uri> deleteUris = new HashSet();
    private static Pattern mDoubleByte_Pattern = null;
    private static int smDip = 0;

    public static String getAppkey(Context context) {
        if (context == null) {
            return "";
        }
        String str = SocializeConstants.APPKEY;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), ShareContent.MINAPP_STYLE);
            if (applicationInfo == null) {
                return str;
            }
            Object obj = applicationInfo.metaData.get("UMENG_APPKEY");
            if (obj != null) {
                return obj.toString();
            }
            Log.e("com.umeng.socialize", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return str;
        } catch (Exception e) {
            Log.e("com.umeng.socialize", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
            return str;
        }
    }

    public static void safeCloseDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (Exception e) {
                Log.e(TAG, "dialog dismiss error", e);
            }
        }
    }

    public static void openApplicationMarket(Context context, String str) throws Exception {
        if (Config.isJumptoAppStore) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=" + str));
            context.startActivity(intent);
        }
    }

    public static void safeShowDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            } catch (Exception e) {
                Log.e(TAG, "dialog show error", e);
            }
        }
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
            }
        }
        return bundle;
    }

    public static int countContentLength(String str) {
        String trim = str.trim();
        int i = 0;
        while (getDoubleBytePattern().matcher(trim).find()) {
            i++;
        }
        int length = trim.length() - i;
        if (length % 2 != 0) {
            return i + ((length + 1) / 2);
        }
        return i + (length / 2);
    }

    private static Pattern getDoubleBytePattern() {
        if (mDoubleByte_Pattern == null) {
            mDoubleByte_Pattern = Pattern.compile("[^\\x00-\\xff]");
        }
        return mDoubleByte_Pattern;
    }

    public static int[] getFloatWindowSize(Context context) {
        if (context == null) {
            return new int[2];
        }
        ResContainer resContainer = ResContainer.get(context);
        Resources resources = context.getResources();
        return new int[]{(int) resources.getDimension(resContainer.dimen("umeng_socialize_pad_window_width")), (int) resources.getDimension(resContainer.dimen("umeng_socialize_pad_window_height"))};
    }

    public static boolean isFloatWindowStyle(Context context) {
        if (context != null && SocializeConstants.SUPPORT_PAD) {
            if (smDip == 0) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                int width = defaultDisplay.getWidth();
                int height = defaultDisplay.getHeight();
                if (width <= height) {
                    height = width;
                }
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                smDip = (int) ((((float) height) / displayMetrics.density) + 0.5f);
            }
            if ((context.getResources().getConfiguration().screenLayout & 15) < 3 || smDip < 550) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static Uri insertImage(Context context, String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        try {
            String insertImage = MediaStore.Images.Media.insertImage(context.getContentResolver(), str, "umeng_social_shareimg", (String) null);
            if (TextUtils.isEmpty(insertImage)) {
                return null;
            }
            return Uri.parse(insertImage);
        } catch (IllegalArgumentException e) {
            Log.e("com.umeng.socialize", "", e);
            return null;
        } catch (Exception e2) {
            Log.e("com.umeng.socialize", "", e2);
            return null;
        }
    }

    public static int dip2Px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Map<String, String> jsonToMap(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next) + "");
            }
        } catch (Exception e) {
            Log.e("social", "jsontomap fail=" + e);
        }
        return hashMap;
    }

    public static byte[] File2byte(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            Log.um(UmengText.FILE_TO_BINARY_ERROR + e.getMessage());
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> bundleTomap(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        HashMap hashMap = new HashMap();
        for (String str : keySet) {
            if (str.equals("com.sina.weibo.intent.extra.USER_ICON")) {
                hashMap.put("icon_url", bundle.getString(str));
            }
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public static Bundle mapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            bundle.putString(next, map.get(next));
        }
        return bundle;
    }

    public static boolean assertBinaryInvalid(byte[] bArr) {
        return bArr != null && bArr.length > 0;
    }

    public static boolean isToday(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(System.currentTimeMillis()));
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(new Date(j));
        if (instance2.get(1) == instance.get(1) && instance2.get(6) - instance.get(6) == 0) {
            return true;
        }
        return false;
    }
}
