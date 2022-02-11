package com.wx.assistants.utils.glide;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.wx.assistants.application.MyApplication;

public class GlideUtils {
    public static GlideUtils mGl;

    public interface OnLoadImgListener {
        void loadResult(int i);
    }

    public static GlideUtils getIantance() {
        if (mGl == null) {
            synchronized (GlideUtils.class) {
                if (mGl == null) {
                    mGl = new GlideUtils();
                }
            }
        }
        return mGl;
    }

    public static void showImageView(Context context, int i, String str, ImageView imageView) {
        Glide.with(MyApplication.getConText()).load(str).error(i).crossFade().placeholder(i).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    public static void showImageViewGone(Context context, ImageView imageView, String str, final OnLoadImgListener onLoadImgListener) {
        try {
            Glide.with(MyApplication.getConText()).load(str).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<String, GlideDrawable>() {
                public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                    if (onLoadImgListener == null) {
                        return false;
                    }
                    onLoadImgListener.loadResult(1);
                    return false;
                }

                public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
                    if (onLoadImgListener != null) {
                        onLoadImgListener.loadResult(0);
                    }
                    return false;
                }
            }).into(imageView);
        } catch (Exception unused) {
        }
    }

    public static void showImageView(Context context, int i, String str, final RelativeLayout relativeLayout) {
        Glide.with(MyApplication.getConText()).load(str).asBitmap().error(i).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(i).into(new SimpleTarget<Bitmap>() {
            @SuppressLint({"NewApi"})
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                relativeLayout.setBackground(new BitmapDrawable(bitmap));
            }

            public void onLoadFailed(Exception exc, Drawable drawable) {
                GlideUtils.super.onLoadFailed(exc, drawable);
                relativeLayout.setBackgroundDrawable(drawable);
            }
        });
    }

    public static void showImageViewToBlur(Context context, int i, String str, final LinearLayout linearLayout) {
        Glide.with(MyApplication.getConText()).load(str).asBitmap().error(i).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(i).transform(new Transformation[]{new BlurTransformation(context)}).into(new SimpleTarget<Bitmap>() {
            @SuppressLint({"NewApi"})
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                linearLayout.setBackground(new BitmapDrawable(bitmap));
            }

            public void onLoadFailed(Exception exc, Drawable drawable) {
                GlideUtils.super.onLoadFailed(exc, drawable);
                linearLayout.setBackgroundDrawable(drawable);
            }
        });
    }

    public static void showImageViewToCircle(Application application, int i, String str, ImageView imageView) {
        Glide.with(MyApplication.getConText()).load(str).error(i).crossFade().placeholder(i).transform(new BitmapTransformation[]{new GlideCircleTransform(application)}).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    public static void showImageViewAnim(Context context, int i, String str, int i2, ImageView imageView) {
        Glide.with(MyApplication.getConText()).load(str).animate(i2).error(i).placeholder(i).into(imageView);
    }

    public static void loadImageViewListener(Context context, String str, ImageView imageView, RequestListener<String, GlideDrawable> requestListener) {
        Glide.with(MyApplication.getConText()).load(str).listener(requestListener).into(imageView);
    }

    public static void loadImageViewContent(Context context, String str, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(MyApplication.getConText()).load(str).centerCrop().into(simpleTarget);
    }

    public static void GlideClearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    public static void GlideClearMemory(Context context) {
        Glide.get(context).clearMemory();
    }
}
