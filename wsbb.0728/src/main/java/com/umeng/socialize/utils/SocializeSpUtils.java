package com.umeng.socialize.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

public class SocializeSpUtils {
    public static int getInt(Context context, String str, int i) {
        return getSharedPreferences(context).getInt(str, i);
    }

    public static String getMac(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(SocializeProtocolConstants.PROTOCOL_KEY_MAC, (String) null);
        }
        return null;
    }

    public static String getShareBoardConfig(Context context) {
        String str = null;
        synchronized (SocializeSpUtils.class) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences(context);
                if (sharedPreferences != null) {
                    str = sharedPreferences.getString("shareboardconfig", (String) null);
                }
            } catch (Throwable th) {
                Class<SocializeSpUtils> cls = SocializeSpUtils.class;
                throw th;
            }
        }
        return str;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0);
    }

    public static String getString(Context context, String str) {
        return getSharedPreferences(context).getString(str, "");
    }

    public static long getTime(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(SocializeConstants.TIME, 0);
        }
        return 0;
    }

    public static String getUMEk(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, (String) null);
        }
        return null;
    }

    public static String getUMId(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID, (String) null);
        }
        return null;
    }

    public static void putInt(Context context, String str, int i) {
        getSharedPreferences(context).edit().putInt(str, i).commit();
    }

    public static boolean putMac(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return sharedPreferences.edit().putString(SocializeProtocolConstants.PROTOCOL_KEY_MAC, str).commit();
    }

    public static boolean putShareBoardConfig(Context context, String str) {
        synchronized (SocializeSpUtils.class) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences(context);
                if (sharedPreferences == null) {
                    return false;
                }
                boolean commit = sharedPreferences.edit().putString("shareboardconfig", str).commit();
                return commit;
            } catch (Throwable th) {
                Class<SocializeSpUtils> cls = SocializeSpUtils.class;
                throw th;
            }
        }
    }

    public static void putString(Context context, String str, String str2) {
        getSharedPreferences(context).edit().putString(str, str2).commit();
    }

    public static boolean putTime(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences != null && sharedPreferences.edit().putLong(SocializeConstants.TIME, System.currentTimeMillis()).commit();
    }

    public static boolean putUMEk(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return sharedPreferences.edit().putString(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, str).commit();
    }

    public static boolean putUMId(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return sharedPreferences.edit().putString(SocializeProtocolConstants.PROTOCOL_KEY_UID, str).commit();
    }

    public static void remove(Context context, String str) {
        getSharedPreferences(context).edit().remove(str).commit();
    }
}
