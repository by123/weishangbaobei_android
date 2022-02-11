package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.stub.StubApp;

public class MQGlideImageLoader4 extends MQImageLoader {
    private MQGlideImageLoader3 mGlideImageLoader3;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    public void displayImage(Activity activity, ImageView imageView, Uri uri, int i, int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        try {
            try {
                try {
                    try {
                        try {
                            final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
                            final ImageView imageView2 = imageView;
                            final Activity activity2 = activity;
                            final Uri uri2 = uri;
                            try {
                                Glide.with(activity).load(uri).apply(new RequestOptions().placeholder(i).error(i2).override(i3, i4)).listener(new RequestListener<Drawable>() {
                                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                                        return false;
                                    }

                                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                                        if (mQDisplayImageListener2 == null) {
                                            return false;
                                        }
                                        mQDisplayImageListener2.onSuccess(imageView2, MQGlideImageLoader4.this.getRealFilePath(activity2, uri2));
                                        return false;
                                    }
                                }).into(imageView);
                            } catch (Error e) {
                            }
                        } catch (Error e2) {
                        }
                    } catch (Error e3) {
                        if (this.mGlideImageLoader3 == null) {
                        }
                        this.mGlideImageLoader3.displayImage(activity, imageView, uri, i, i2, i3, i4, mQDisplayImageListener);
                    }
                } catch (Error e4) {
                    if (this.mGlideImageLoader3 == null) {
                    }
                    this.mGlideImageLoader3.displayImage(activity, imageView, uri, i, i2, i3, i4, mQDisplayImageListener);
                }
            } catch (Error e5) {
                if (this.mGlideImageLoader3 == null) {
                    this.mGlideImageLoader3 = new MQGlideImageLoader3();
                }
                this.mGlideImageLoader3.displayImage(activity, imageView, uri, i, i2, i3, i4, mQDisplayImageListener);
            }
        } catch (Error e6) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    public void displayImage(Activity activity, final ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        final String path = getPath(str);
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    displayImage(activity, imageView, MQUtils.getImageContentUri(activity, str), i, i2, i3, i4, mQDisplayImageListener);
                } catch (Error e) {
                    if (this.mGlideImageLoader3 == null) {
                    }
                    this.mGlideImageLoader3.displayImage(activity, imageView, str, i, i2, i3, i4, mQDisplayImageListener);
                }
            } else {
                try {
                    try {
                        try {
                            try {
                                final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
                                Glide.with(activity).load(path).apply(new RequestOptions().placeholder(i).error(i2).override(i3, i4)).listener(new RequestListener<Drawable>() {
                                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                                        return false;
                                    }

                                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                                        if (mQDisplayImageListener2 == null) {
                                            return false;
                                        }
                                        mQDisplayImageListener2.onSuccess(imageView, path);
                                        return false;
                                    }
                                }).into(imageView);
                            } catch (Error e2) {
                            }
                        } catch (Error e3) {
                        }
                    } catch (Error e4) {
                        if (this.mGlideImageLoader3 == null) {
                        }
                        this.mGlideImageLoader3.displayImage(activity, imageView, str, i, i2, i3, i4, mQDisplayImageListener);
                    }
                } catch (Error e5) {
                    if (this.mGlideImageLoader3 == null) {
                    }
                    this.mGlideImageLoader3.displayImage(activity, imageView, str, i, i2, i3, i4, mQDisplayImageListener);
                }
            }
        } catch (Error e6) {
            if (this.mGlideImageLoader3 == null) {
                this.mGlideImageLoader3 = new MQGlideImageLoader3();
            }
            this.mGlideImageLoader3.displayImage(activity, imageView, str, i, i2, i3, i4, mQDisplayImageListener);
        }
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        final String path = getPath(str);
        try {
            Glide.with(StubApp.getOrigApplicationContext(context.getApplicationContext())).load(path).into(new SimpleTarget<Drawable>() {
                public void onLoadFailed(@Nullable Drawable drawable) {
                    if (mQDownloadImageListener != null) {
                        mQDownloadImageListener.onFailed(path);
                    }
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    if (mQDownloadImageListener != null) {
                        mQDownloadImageListener.onSuccess(path, MQUtils.drawableToBitmap(drawable));
                    }
                }
            });
        } catch (Error e) {
            if (this.mGlideImageLoader3 == null) {
                this.mGlideImageLoader3 = new MQGlideImageLoader3();
            }
            this.mGlideImageLoader3.downloadImage(context, str, mQDownloadImageListener);
        }
    }
}
