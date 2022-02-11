package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* compiled from: UOPTracker */
public class q extends a {
    public static final String a = "uopdta";
    private static final String b = "uop";
    private Context c;

    public q(Context context) {
        super(b);
        this.c = context;
    }

    public String f() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.c);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(a, "");
        }
        return "";
    }
}
