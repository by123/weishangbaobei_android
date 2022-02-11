package com.tencent.wxop.stat.event;

import com.tencent.bugly.CrashModule;
import com.umeng.socialize.common.SocializeConstants;

public enum EventType {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(SocializeConstants.CANCLE_RESULTCODE),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(1003),
    NETWORK_MONITOR(CrashModule.MODULE_ID),
    NETWORK_DETECTOR(1005);
    
    private int a;

    private EventType(int i) {
        this.a = i;
    }

    public final int a() {
        return this.a;
    }
}
