package com.wx.assistants.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wx.assistants.application.MyApplication;

public class Global {
    public static Context sContext;
    public static float sDensity;
    public static int sScreenHeight;
    public static int sScreenWidth;

    public static void init(Context context) {
        sContext = context;
        initScreenSize();
    }

    private static void initScreenSize() {
        DisplayMetrics displayMetrics = sContext.getResources().getDisplayMetrics();
        sDensity = displayMetrics.density;
        sScreenWidth = displayMetrics.widthPixels;
        sScreenHeight = displayMetrics.heightPixels;
    }

    public static void displayBitmap(String str, final ImageView imageView) {
        Glide.with(MyApplication.getConText()).load(str).asBitmap().into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                double d = (double) Global.sScreenWidth;
                Double.isNaN(d);
                layoutParams.height = (((int) (d / 3.6d)) * height) / width;
                double d2 = (double) Global.sScreenWidth;
                Double.isNaN(d2);
                layoutParams.width = (int) (d2 / 3.6d);
                if (height > width) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
