package com.meiqia.meiqiasdk.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;

public class MQImage {
    private static MQImageLoader sImageLoader;

    private MQImage() {
    }

    private static final MQImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (MQImage.class) {
                if (sImageLoader == null) {
                    if (isClassExists("com.bumptech.glide.Glide")) {
                        sImageLoader = new MQGlideImageLoader4();
                    } else if (isClassExists("com.squareup.picasso.Picasso")) {
                        sImageLoader = new MQPicassoImageLoader();
                    } else if (isClassExists("com.nostra13.universalimageloader.core.ImageLoader")) {
                        sImageLoader = new MQUILImageLoader();
                    } else if (isClassExists("org.xutils.x")) {
                        sImageLoader = new MQXUtilsImageLoader();
                    } else {
                        throw new RuntimeException("必须在你的 build.gradle 文件中配置「Glide、Picasso、universal-image-loader、XUtils3」中的某一个图片加载库的依赖,或者检查是否添加了图库的混淆配置");
                    }
                }
            }
        }
        return sImageLoader;
    }

    public static void setImageLoader(MQImageLoader mQImageLoader) {
        sImageLoader = mQImageLoader;
    }

    private static final boolean isClassExists(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void displayImage(Activity activity, ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQImageLoader.MQDisplayImageListener mQDisplayImageListener) {
        try {
            getImageLoader().displayImage(activity, imageView, str, i, i2, i3, i4, mQDisplayImageListener);
        } catch (Exception e) {
            Log.d("meiqia", "displayImage exception " + e.toString());
        } catch (Error e2) {
            Log.d("meiqia", "displayImage error " + e2.toString());
        }
    }

    public static void downloadImage(Context context, String str, MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        try {
            getImageLoader().downloadImage(context, str, mQDownloadImageListener);
        } catch (Exception unused) {
            Log.d("meiqia", "downloadImage exception");
        } catch (Error e) {
            Log.d("meiqia", "displayImage error " + e.toString());
        }
    }
}
