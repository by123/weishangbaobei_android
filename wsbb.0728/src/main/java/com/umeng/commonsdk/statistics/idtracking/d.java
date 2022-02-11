package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class d extends a {
    private static final String a = "idmd5";
    private Context b;

    public d(Context context) {
        super("idmd5");
        this.b = context;
    }

    public String f() {
        return DeviceConfig.getDeviceIdUmengMD5(this.b);
    }
}
