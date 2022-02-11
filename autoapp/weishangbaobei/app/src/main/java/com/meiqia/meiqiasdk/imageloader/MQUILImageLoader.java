package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.stub.StubApp;

public class MQUILImageLoader extends MQImageLoader {
    private void initImageLoader(Context context) {
        if (!ImageLoader.getInstance().isInited()) {
            ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(StubApp.getOrigApplicationContext(context.getApplicationContext())).threadPoolSize(3).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
        }
    }

    public void displayImage(Activity activity, ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        String str2 = str;
        initImageLoader(activity);
        if (Build.VERSION.SDK_INT >= 29) {
            displayImage(activity, imageView, MQUtils.getImageContentUri(activity, str), i, i2, i3, i4, mQDisplayImageListener);
            return;
        }
        int i5 = i;
        int i6 = i2;
        DisplayImageOptions build = new DisplayImageOptions.Builder().showImageOnLoading(i).showImageOnFail(i2).cacheInMemory(true).build();
        ImageSize imageSize = new ImageSize(i3, i4);
        String path = getPath(str);
        ImageView imageView2 = imageView;
        final MQImageLoader.MQDisplayImageListener mQDisplayImageListener2 = mQDisplayImageListener;
        ImageLoader.getInstance().displayImage(path, new ImageViewAware(imageView), build, imageSize, new SimpleImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (mQDisplayImageListener2 != null) {
                    mQDisplayImageListener2.onSuccess(view, str);
                }
            }
        }, (ImageLoadingProgressListener) null);
    }

    /* access modifiers changed from: protected */
    public void displayImage(Activity activity, ImageView imageView, Uri uri, int i, int i2, int i3, int i4, final MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        DisplayImageOptions build = new DisplayImageOptions.Builder().showImageOnLoading(i).showImageOnFail(i2).cacheInMemory(true).build();
        ImageSize imageSize = new ImageSize(i3, i4);
        ImageLoader instance = ImageLoader.getInstance();
        String uri2 = uri.toString();
        ImageViewAware imageViewAware = new ImageViewAware(imageView);
        instance.displayImage(uri2, imageViewAware, build, imageSize, new SimpleImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (mQDisplayImageListener != null) {
                    mQDisplayImageListener.onSuccess(view, str);
                }
            }
        }, (ImageLoadingProgressListener) null);
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        initImageLoader(context);
        ImageLoader.getInstance().loadImage(getPath(str), new SimpleImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onSuccess(str, bitmap);
                }
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
                if (mQDownloadImageListener != null) {
                    mQDownloadImageListener.onFailed(str);
                }
            }
        });
    }
}
