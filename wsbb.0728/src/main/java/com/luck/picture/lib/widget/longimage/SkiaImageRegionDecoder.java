package com.luck.picture.lib.widget.longimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import java.io.InputStream;
import java.util.List;

public class SkiaImageRegionDecoder implements ImageRegionDecoder {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final String FILE_PREFIX = "file://";
    private static final String RESOURCE_PREFIX = "android.resource://";
    private BitmapRegionDecoder decoder;
    private final Object decoderLock = new Object();

    public Bitmap decodeRegion(Rect rect, int i) {
        Bitmap decodeRegion;
        synchronized (this.decoderLock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            decodeRegion = this.decoder.decodeRegion(rect, options);
            if (decodeRegion == null) {
                throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
            }
        }
        return decodeRegion;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e4 A[SYNTHETIC, Splitter:B:40:0x00e4] */
    public Point init(Context context, Uri uri) throws Exception {
        InputStream inputStream;
        int i;
        String uri2 = uri.toString();
        if (uri2.startsWith(RESOURCE_PREFIX)) {
            String authority = uri.getAuthority();
            Resources resources = context.getPackageName().equals(authority) ? context.getResources() : context.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            int size = pathSegments.size();
            if (size != 2 || !pathSegments.get(0).equals("drawable")) {
                if (size == 1 && TextUtils.isDigitsOnly(pathSegments.get(0))) {
                    try {
                        i = Integer.parseInt(pathSegments.get(0));
                    } catch (NumberFormatException e) {
                    }
                }
                i = 0;
            } else {
                i = resources.getIdentifier(pathSegments.get(1), "drawable", authority);
            }
            this.decoder = BitmapRegionDecoder.newInstance(context.getResources().openRawResource(i), false);
        } else if (uri2.startsWith(ASSET_PREFIX)) {
            this.decoder = BitmapRegionDecoder.newInstance(context.getAssets().open(uri2.substring(ASSET_PREFIX.length()), 1), false);
        } else if (uri2.startsWith(FILE_PREFIX)) {
            this.decoder = BitmapRegionDecoder.newInstance(uri2.substring(FILE_PREFIX.length()), false);
        } else {
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
                try {
                    this.decoder = BitmapRegionDecoder.newInstance(inputStream, false);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
        }
        return new Point(this.decoder.getWidth(), this.decoder.getHeight());
    }

    public boolean isReady() {
        return this.decoder != null && !this.decoder.isRecycled();
    }

    public void recycle() {
        this.decoder.recycle();
    }
}
