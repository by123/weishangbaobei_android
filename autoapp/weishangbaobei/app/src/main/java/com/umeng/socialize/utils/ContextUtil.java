package com.umeng.socialize.utils;

import android.content.Context;

public class ContextUtil {
    private static Context context;

    public static Context getContext() {
        if (context == null) {
            Log.um(UmengText.CONTEXT_ERROR);
        }
        return context;
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static final String getPackageName() {
        return context == null ? "" : context.getPackageName();
    }
}
