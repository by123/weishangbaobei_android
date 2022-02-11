package com.wx.assistants.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.stub.StubApp;
import com.wx.assistants.dialog.config.MToastConfig;
import com.wx.assistants.dialog.utils.MSizeUtils;

public class MToast {
    private static Toast currentToast;

    public static void cancelToast() {
        if (currentToast != null) {
            currentToast.cancel();
            currentToast = null;
        }
    }

    private static Toast make(MToastConfig mToastConfig, @NonNull Context context, @NonNull CharSequence charSequence, int i) {
        cancelToast();
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (currentToast == null) {
            currentToast = new Toast(origApplicationContext);
        }
        View inflate = ((LayoutInflater) origApplicationContext.getSystemService("layout_inflater")).inflate(2131427590, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(2131297543);
        ImageView imageView = (ImageView) inflate.findViewById(2131296839);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131297524);
        currentToast.setView(inflate);
        if (mToastConfig == null) {
            mToastConfig = new MToastConfig.Builder().build();
        }
        MToastConfig.MToastGravity mToastGravity = mToastConfig.toastGravity;
        int i2 = mToastConfig.toastTextColor;
        float f = mToastConfig.toastTextSize;
        int i3 = mToastConfig.toastBackgroundColor;
        float f2 = mToastConfig.toastBackgroundCornerRadius;
        Drawable drawable = mToastConfig.toastIcon;
        int i4 = mToastConfig.toastBackgroundStrokeColor;
        float f3 = mToastConfig.toastBackgroundStrokeWidth;
        if (drawable == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            imageView.setImageDrawable(drawable);
        }
        textView.setTextColor(i2);
        textView.setTextSize(2, f);
        textView.setText(charSequence);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) MSizeUtils.dp2px(origApplicationContext, f2));
        gradientDrawable.setColor(i3);
        gradientDrawable.setStroke(MSizeUtils.dp2px(origApplicationContext, f3), i4);
        if (Build.VERSION.SDK_INT >= 16) {
            linearLayout.setBackground(gradientDrawable);
        } else {
            linearLayout.setBackgroundDrawable(gradientDrawable);
        }
        linearLayout.setPadding(MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.paddingLeft), MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.paddingTop), MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.paddingRight), MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.paddingBottom));
        if (mToastGravity == MToastConfig.MToastGravity.CENTRE) {
            currentToast.setGravity(17, mToastConfig.xOffsets, mToastConfig.yOffsets);
        } else {
            currentToast.setGravity(80, mToastConfig.xOffsets, MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.yOffsets));
        }
        if (mToastConfig.imgWidth > 0 && mToastConfig.imgHeight > 0) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.imgWidth);
            layoutParams.height = MSizeUtils.dp2px(origApplicationContext, (float) mToastConfig.imgHeight);
            imageView.setLayoutParams(layoutParams);
        }
        currentToast.setDuration(i);
        return currentToast;
    }

    private static void makeText(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        make((MToastConfig) null, context, charSequence, i).show();
    }

    private static void makeText(MToastConfig mToastConfig, @NonNull Context context, @NonNull CharSequence charSequence, int i) {
        make(mToastConfig, context, charSequence, i).show();
    }

    public static void makeTextLong(@NonNull Context context, @NonNull CharSequence charSequence) {
        make((MToastConfig) null, context, charSequence, 1).show();
    }

    public static void makeTextLong(@NonNull Context context, @NonNull CharSequence charSequence, MToastConfig mToastConfig) {
        make(mToastConfig, context, charSequence, 1).show();
    }

    public static void makeTextShort(@NonNull Context context, @NonNull CharSequence charSequence) {
        make((MToastConfig) null, context, charSequence, 0).show();
    }

    public static void makeTextShort(@NonNull Context context, @NonNull CharSequence charSequence, MToastConfig mToastConfig) {
        make(mToastConfig, context, charSequence, 0).show();
    }
}
