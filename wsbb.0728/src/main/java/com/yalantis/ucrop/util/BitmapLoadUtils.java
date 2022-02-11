package com.yalantis.ucrop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.task.BitmapLoadTask;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BitmapLoadUtils {
    private static final String TAG = "BitmapLoadUtils";

    public static int calculateInSampleSize(@NonNull BitmapFactory.Options options, int i, int i2) {
        int i3 = 1;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            while (true) {
                if (i4 / i3 <= i2 && i5 / i3 <= i) {
                    break;
                }
                i3 *= 2;
            }
        }
        return i3;
    }

    public static int calculateMaxBitmapSize(@NonNull Context context) {
        int width;
        int height;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
            width = point.x;
            height = point.y;
        } else {
            width = defaultDisplay.getWidth();
            height = defaultDisplay.getHeight();
        }
        int sqrt = (int) Math.sqrt(Math.pow((double) height, 2.0d) + Math.pow((double) width, 2.0d));
        Canvas canvas = new Canvas();
        int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        if (min > 0) {
            sqrt = Math.min(sqrt, min);
        }
        int maxTextureSize = EglUtils.getMaxTextureSize();
        if (maxTextureSize > 0) {
            sqrt = Math.min(sqrt, maxTextureSize);
        }
        Log.d(TAG, "maxBitmapSize: " + sqrt);
        return sqrt;
    }

    public static void close(@Nullable Closeable closeable) {
        if (closeable != null && (closeable instanceof Closeable)) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void decodeBitmapInBackground(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        new BitmapLoadTask(context, uri, uri2, i, i2, bitmapLoadCallback).execute(new Void[0]);
    }

    public static int exifToDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return SubsamplingScaleImageView.ORIENTATION_180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return SubsamplingScaleImageView.ORIENTATION_270;
            default:
                return 0;
        }
    }

    public static int exifToTranslation(int i) {
        if (!(i == 2 || i == 7)) {
            switch (i) {
                case 4:
                case 5:
                    break;
                default:
                    return 1;
            }
        }
        return -1;
    }

    public static int getExifOrientation(@NonNull Context context, @NonNull Uri uri) {
        int i = 0;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                i = new ImageHeaderParser(openInputStream).getOrientation();
                try {
                    close(openInputStream);
                } catch (IOException e) {
                    e = e;
                }
            }
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "getExifOrientation: " + uri.toString(), e);
            return i;
        }
        return i;
    }

    public static Bitmap transformBitmap(@NonNull Bitmap bitmap, @NonNull Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return !bitmap.sameAs(createBitmap) ? createBitmap : bitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "transformBitmap: ", e);
            return bitmap;
        }
    }
}
