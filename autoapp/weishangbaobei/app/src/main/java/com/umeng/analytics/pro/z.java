package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* compiled from: SPHelper */
public final class z {
    private static z a = null;
    private static Context b = null;
    private static String c = null;
    private static final String d = "mobclick_agent_user_";

    /* compiled from: SPHelper */
    private static final class a {
        /* access modifiers changed from: private */
        public static final z a = new z();

        private a() {
        }
    }

    public static synchronized z a(Context context) {
        z a2;
        synchronized (z.class) {
            if (b == null && context != null) {
                b = StubApp.getOrigApplicationContext(context.getApplicationContext());
            }
            if (b != null) {
                c = context.getPackageName();
            }
            a2 = a.a;
        }
        return a2;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = e().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] a() {
        SharedPreferences e = e();
        if (e != null) {
            String string = e.getString("au_p", (String) null);
            String string2 = e.getString("au_u", (String) null);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                return new String[]{string, string2};
            }
        }
        return null;
    }

    public void b() {
        SharedPreferences e = e();
        if (e != null) {
            e.edit().remove("au_p").remove("au_u").commit();
        }
    }

    public String c() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", (String) null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    public void a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    public int d() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    private SharedPreferences e() {
        if (b == null) {
            return null;
        }
        Context context = b;
        return context.getSharedPreferences(d + c, 0);
    }
}
