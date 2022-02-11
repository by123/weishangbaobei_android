package com.wx.assistants.floatwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.wx.assistants.utils.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class FloatToast extends FloatView {
    private Method hide;
    private boolean isLightAla = true;
    private int mHeight;
    private Object mTN;
    private int mWidth;
    private Method show;
    private Toast toast;

    FloatToast(Context context) {
        this.toast = new Toast(context);
    }

    FloatToast(Context context, boolean z) {
        this.toast = new Toast(context);
        this.isLightAla = z;
    }

    private void initTN() {
        try {
            Field declaredField = this.toast.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.mTN = declaredField.get(this.toast);
            this.show = this.mTN.getClass().getMethod("show", new Class[0]);
            this.hide = this.mTN.getClass().getMethod("hide", new Class[0]);
            Field declaredField2 = this.mTN.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) declaredField2.get(this.mTN);
            if (this.isLightAla) {
                layoutParams.flags = 168;
            } else {
                layoutParams.flags = 40;
            }
            layoutParams.width = this.mWidth;
            layoutParams.height = this.mHeight;
            layoutParams.windowAnimations = 0;
            Field declaredField3 = this.mTN.getClass().getDeclaredField("mNextView");
            declaredField3.setAccessible(true);
            declaredField3.set(this.mTN, this.toast.getView());
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_44");
            e.printStackTrace();
        }
    }

    public void dismiss() {
        try {
            this.hide.invoke(this.mTN, new Object[0]);
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_43");
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            this.show.invoke(this.mTN, new Object[0]);
        } catch (Exception e) {
            LogUtils.log("WS_BABY_Catch_41");
            e.printStackTrace();
        }
    }

    public void setGravity(int i, int i2, int i3) {
        this.toast.setGravity(i, i2, i3);
    }

    public void setSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void setView(View view) {
        this.toast.setView(view);
        initTN();
    }
}
