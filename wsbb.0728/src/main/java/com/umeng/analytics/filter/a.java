package com.umeng.analytics.filter;

import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;

public class a extends EventList {
    private d a;
    private Object b = new Object();

    public a(String str, String str2) {
        super(str, str2);
    }

    /* access modifiers changed from: protected */
    public void eventListChange() {
        if (!TextUtils.isEmpty(this.mEventList)) {
            synchronized (this.b) {
                this.a = null;
                this.a = new d(false, this.mEventList);
            }
        }
    }

    public boolean matchHit(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(this.mEventList)) {
            synchronized (this.b) {
                if (this.a == null) {
                    this.a = new d(false, this.mEventList);
                }
                z = this.a.a(str);
            }
        }
        return z;
    }

    public void setMD5ClearFlag(boolean z) {
        AnalyticsConfig.CLEAR_EKV_BL = z;
    }
}
