package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class f extends a {
    private static final String a = "imei";
    private Context b;

    public f(Context context) {
        super("imei");
        this.b = context;
    }

    public String f() {
        return DeviceConfig.getImeiNew(this.b);
    }
}
