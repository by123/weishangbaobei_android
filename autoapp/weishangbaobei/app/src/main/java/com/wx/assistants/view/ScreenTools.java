package com.wx.assistants.view;

import android.content.Context;
import com.stub.StubApp;

public class ScreenTools {
    private static ScreenTools mScreenTools;
    private Context context;

    private ScreenTools(Context context2) {
        this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
    }

    public static ScreenTools instance(Context context2) {
        if (mScreenTools == null) {
            mScreenTools = new ScreenTools(context2);
        }
        return mScreenTools;
    }

    public int dip2px(float f) {
        double density = (double) (f * getDensity(this.context));
        Double.isNaN(density);
        return (int) (density + 0.5d);
    }

    public int dip2px(int i) {
        double density = (double) (getDensity(this.context) * ((float) i));
        Double.isNaN(density);
        return (int) (density + 0.5d);
    }

    public int get480Height(int i) {
        return (i * getScreenWidth()) / 480;
    }

    public float getDensity(Context context2) {
        return context2.getResources().getDisplayMetrics().density;
    }

    public int getScal() {
        return (getScreenWidth() * 100) / 480;
    }

    public int getScreenDensityDpi() {
        return this.context.getResources().getDisplayMetrics().densityDpi;
    }

    public int getScreenHeight() {
        return this.context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return this.context.getResources().getDisplayMetrics().widthPixels;
    }

    public float getXdpi() {
        return this.context.getResources().getDisplayMetrics().xdpi;
    }

    public float getYdpi() {
        return this.context.getResources().getDisplayMetrics().ydpi;
    }

    public int px2dip(float f) {
        float density = getDensity(this.context);
        double d = (double) f;
        Double.isNaN(d);
        double d2 = (double) density;
        Double.isNaN(d2);
        return (int) ((d - 0.5d) / d2);
    }

    public int px2dip(int i) {
        float density = getDensity(this.context);
        double d = (double) i;
        Double.isNaN(d);
        double d2 = (double) density;
        Double.isNaN(d2);
        return (int) ((d - 0.5d) / d2);
    }
}
