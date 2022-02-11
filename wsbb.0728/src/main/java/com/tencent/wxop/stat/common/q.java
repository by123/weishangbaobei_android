package com.tencent.wxop.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class q {
    private static SharedPreferences a;

    public static int a(Context context, String str, int i) {
        return a(context).getInt(l.a(context, StatConstants.MTA_COOPERATION_TAG + str), i);
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(l.a(context, StatConstants.MTA_COOPERATION_TAG + str), j);
    }

    static SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (q.class) {
            try {
                SharedPreferences sharedPreferences2 = context.getSharedPreferences(".mta-wxop", 0);
                a = sharedPreferences2;
                if (sharedPreferences2 == null) {
                    a = PreferenceManager.getDefaultSharedPreferences(context);
                }
                sharedPreferences = a;
            } catch (Throwable th) {
                Class<q> cls = q.class;
                throw th;
            }
        }
        return sharedPreferences;
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(l.a(context, StatConstants.MTA_COOPERATION_TAG + str), str2);
    }

    public static void b(Context context, String str, int i) {
        String a2 = l.a(context, StatConstants.MTA_COOPERATION_TAG + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putInt(a2, i);
        edit.commit();
    }

    public static void b(Context context, String str, long j) {
        String a2 = l.a(context, StatConstants.MTA_COOPERATION_TAG + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putLong(a2, j);
        edit.commit();
    }

    public static void b(Context context, String str, String str2) {
        String a2 = l.a(context, StatConstants.MTA_COOPERATION_TAG + str);
        SharedPreferences.Editor edit = a(context).edit();
        edit.putString(a2, str2);
        edit.commit();
    }
}
