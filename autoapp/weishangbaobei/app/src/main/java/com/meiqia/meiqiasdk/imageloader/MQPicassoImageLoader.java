package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.stub.StubApp;

public class MQPicassoImageLoader extends MQImageLoader {
    public void displayImage(Activity activity, final ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        ImageView imageView2 = imageView;
        String str2 = str;
        if (Build.VERSION.SDK_INT >= 29) {
            displayImage(activity, imageView, MQUtils.getImageContentUri(activity, str), i, i2, i3, i4, mQDisplayImageListener);
            return;
        }
        Activity activity2 = activity;
        final String path = getPath(str);
        int i5 = i;
        int i6 = i2;
        final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
        Picasso.with(activity).load(path).placeholder(i).error(i2).resize(i3, i4).centerInside().into(imageView, new Callback.EmptyCallback() {
            public void onSuccess() {
                if (mQDisplayImageListener2 != null) {
                    mQDisplayImageListener2.onSuccess(imageView, path);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void displayImage(Activity activity, ImageView imageView, Uri uri, int i, int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
        final Activity activity2 = activity;
        final Uri uri2 = uri;
        final ImageView imageView2 = imageView;
        Picasso.with(activity).load(uri).placeholder(i).error(i2).resize(i3, i4).centerInside().into(imageView, new Callback.EmptyCallback() {
            public void onSuccess() {
                if (mQDisplayImageListener2 != null) {
                    mQDisplayImageListener2.onSuccess(imageView2, MQUtils.getRealPathByUri(activity2, uri2));
                }
            }
        });
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        final String path = getPath(str);
        Picasso.with(StubApp.getOrigApplicationContext(context.getApplicationContext())).load(path).into(new Target() {
            public void onPrepareLoad(Drawable drawable) {
            }

            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onSuccess(path, bitmap);
                }
            }

            public void onBitmapFailed(Drawable drawable) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onFailed(path);
                }
            }
        });
    }
}
