package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class b extends a {
    private static final String a = "android_id";
    private Context b;

    public b(Context context) {
        super("android_id");
        this.b = context;
    }

    public String f() {
        return DeviceConfig.getAndroidId(this.b);
    }
}
