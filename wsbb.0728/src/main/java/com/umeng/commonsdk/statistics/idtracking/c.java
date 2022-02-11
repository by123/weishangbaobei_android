package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.a;

public class c extends a {
    private static final String a = "idfa";
    private Context b;

    public c(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String a2 = a.a(this.b);
        return a2 == null ? "" : a2;
    }
}
