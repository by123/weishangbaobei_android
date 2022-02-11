package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.stub.StubApp;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class MQXUtilsImageLoader extends MQImageLoader {
    public void displayImage(Activity activity, final ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, final MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        x.Ext.init(activity.getApplication());
        ImageOptions build = new ImageOptions.Builder().setLoadingDrawableId(i).setFailureDrawableId(i2).setSize(i3, i4).build();
        final String path = getPath(str);
        x.image().bind(imageView, path, build, new Callback.CommonCallback<Drawable>() {
            public void onCancelled(Callback.CancelledException cancelledException) {
            }

            public void onError(Throwable th, boolean z) {
            }

            public void onFinished() {
            }

            public void onSuccess(Drawable drawable) {
                if (mQDisplayImageListener != null) {
                    mQDisplayImageListener.onSuccess(imageView, path);
                }
            }
        });
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        x.Ext.init((Application) StubApp.getOrigApplicationContext(context.getApplicationContext()));
        final String path = getPath(str);
        x.image().loadDrawable(path, new ImageOptions.Builder().build(), new Callback.CommonCallback<Drawable>() {
            public void onCancelled(Callback.CancelledException cancelledException) {
            }

            public void onFinished() {
            }

            public void onSuccess(Drawable drawable) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onSuccess(path, ((BitmapDrawable) drawable).getBitmap());
                }
            }

            public void onError(Throwable th, boolean z) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onFailed(path);
                }
            }
        });
    }
}
