package com.luck.picture.lib.widget.longimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import java.io.InputStream;
import java.util.List;

public class SkiaImageDecoder implements ImageDecoder {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final String FILE_PREFIX = "file://";
    private static final String RESOURCE_PREFIX = "android.resource://";

    public Bitmap decode(Context context, Uri uri) throws Exception {
        Throwable th;
        Bitmap bitmap;
        int i;
        InputStream inputStream = null;
        String uri2 = uri.toString();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (uri2.startsWith(RESOURCE_PREFIX)) {
            String authority = uri.getAuthority();
            Resources resources = context.getPackageName().equals(authority) ? context.getResources() : context.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            int size = pathSegments.size();
            if (size == 2 && pathSegments.get(0).equals("drawable")) {
                i = resources.getIdentifier(pathSegments.get(1), "drawable", authority);
            } else if (size != 1 || !TextUtils.isDigitsOnly(pathSegments.get(0))) {
                i = 0;
            } else {
                try {
                    i = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException e) {
                    i = 0;
                }
            }
            bitmap = BitmapFactory.decodeResource(context.getResources(), i, options);
        } else if (uri2.startsWith(ASSET_PREFIX)) {
            bitmap = BitmapFactory.decodeStream(context.getAssets().open(uri2.substring(ASSET_PREFIX.length())), (Rect) null, options);
        } else if (uri2.startsWith(FILE_PREFIX)) {
            bitmap = BitmapFactory.decodeFile(uri2.substring(FILE_PREFIX.length()), options);
        } else {
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (Exception e2) {
                            bitmap = decodeStream;
                        }
                    }
                    bitmap = decodeStream;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = openInputStream;
                }
            } catch (Throwable th3) {
                th = th3;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
        }
        if (bitmap != null) {
            return bitmap;
        }
        throw new RuntimeException("Skia image region decoder returned null bitmap - image format may not be supported");
    }
}
