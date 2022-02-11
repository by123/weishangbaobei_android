package com.scwang.smartrefresh.layout.util;

import android.content.res.Resources;

public class DensityUtil {
    public float density = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(float f) {
        return (int) ((Resources.getSystem().getDisplayMetrics().density * f) + 0.5f);
    }

    public static float px2dp(int i) {
        return ((float) i) / Resources.getSystem().getDisplayMetrics().density;
    }

    public int dip2px(float f) {
        return (int) ((this.density * f) + 0.5f);
    }

    public float px2dip(int i) {
        return ((float) i) / this.density;
    }
}
