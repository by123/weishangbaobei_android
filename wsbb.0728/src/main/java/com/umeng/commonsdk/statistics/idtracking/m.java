package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

public class m extends a {
    private static final String a = "umtt1";
    private Context b;

    public m(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls == null) {
                return null;
            }
            return (String) cls.getMethod("getUmtt1", new Class[]{Context.class}).invoke(cls, new Object[]{this.b});
        } catch (Throwable th) {
            return null;
        }
    }
}
