package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class j extends a {
    private static final String a = "serial";

    public j() {
        super(a);
    }

    public String f() {
        return DeviceConfig.getSerial();
    }
}
