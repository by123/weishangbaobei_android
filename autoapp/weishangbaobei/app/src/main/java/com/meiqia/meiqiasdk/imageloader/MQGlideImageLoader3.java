package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.stub.StubApp;

public class MQGlideImageLoader3 extends MQImageLoader {
    public void displayImage(Activity activity, final ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        ImageView imageView2 = imageView;
        String str2 = str;
        final String path = getPath(str);
        if (Build.VERSION.SDK_INT >= 29) {
            Activity activity2 = activity;
            displayImage(activity, imageView, MQUtils.getImageContentUri(activity, str), i, i2, i3, i4, mQDisplayImageListener);
            return;
        }
        Activity activity3 = activity;
        int i5 = i;
        int i6 = i2;
        final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
        Glide.with(activity).load(path).asBitmap().placeholder(i).error(i2).override(i3, i4).listener(new RequestListener<String, Bitmap>() {
            public boolean onException(Exception exc, String str, Target<Bitmap> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Bitmap bitmap, String str, Target<Bitmap> target, boolean z, boolean z2) {
                if (mQDisplayImageListener2 == null) {
                    return false;
                }
                mQDisplayImageListener2.onSuccess(imageView, path);
                return false;
            }
        }).into(imageView);
    }

    /* access modifiers changed from: protected */
    public void displayImage(Activity activity, ImageView imageView, Uri uri, int i, int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
        final ImageView imageView2 = imageView;
        final Activity activity2 = activity;
        final Uri uri2 = uri;
        Glide.with(activity).load(uri).asBitmap().placeholder(i).error(i2).override(i3, i4).listener(new RequestListener<Uri, Bitmap>() {
            public boolean onException(Exception exc, Uri uri, Target<Bitmap> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Bitmap bitmap, Uri uri, Target<Bitmap> target, boolean z, boolean z2) {
                if (mQDisplayImageListener2 == null) {
                    return false;
                }
                mQDisplayImageListener2.onSuccess(imageView2, MQGlideImageLoader3.this.getRealFilePath(activity2, uri2));
                return false;
            }
        }).into(imageView);
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        final String path = getPath(str);
        Glide.with(StubApp.getOrigApplicationContext(context.getApplicationContext())).load(path).asBitmap().into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onSuccess(path, bitmap);
                }
            }

            public void onLoadFailed(Exception exc, Drawable drawable) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onFailed(path);
                }
            }
        });
    }
}
