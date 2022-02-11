package com.meiqia.meiqiasdk.imageloader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

public abstract class MQImageLoader {

    public interface MQDisplayImageListener {
        void onSuccess(View view, String str);
    }

    public interface MQDownloadImageListener {
        void onFailed(String str);

        void onSuccess(String str, Bitmap bitmap);
    }

    /* access modifiers changed from: protected */
    @TargetApi(29)
    public void displayImage(Activity activity, ImageView imageView, Uri uri, int i, int i2, int i3, int i4, MQDisplayImageListener mQDisplayImageListener) {
    }

    public abstract void displayImage(Activity activity, ImageView imageView, String str, @DrawableRes int i, @DrawableRes int i2, int i3, int i4, MQDisplayImageListener mQDisplayImageListener);

    public abstract void downloadImage(Context context, String str, MQDownloadImageListener mQDownloadImageListener);

    /* access modifiers changed from: protected */
    public String getPath(String str) {
        if (str == null) {
            str = "";
        }
        if (str.startsWith("http") || str.startsWith("file")) {
            return str;
        }
        return "file://" + str;
    }

    /* access modifiers changed from: protected */
    public String getRealFilePath(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }
}
