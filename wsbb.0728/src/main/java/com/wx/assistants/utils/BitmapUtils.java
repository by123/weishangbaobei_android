package com.wx.assistants.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
    public static final long MAX_SZIE = 524288;
    private static final String TAG = "BitmapUtils";

    public static class BitmapSize {
        public int height;
        public int width;

        public BitmapSize(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public static byte[] bitmapTobytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] bitmapTobytesNoCompress(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = 1;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int i6 = i4 / 2;
            int i7 = i5 / 2;
            while (i6 / i3 >= i2 && i7 / i3 >= i) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static Bitmap circleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float f = (float) (width / 2);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, paint);
        return createBitmap;
    }

    public static Bitmap compress(Bitmap bitmap) {
        int i = 1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT > 1024) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, (Rect) null, options);
        options.inJustDecodeBounds = false;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int i4 = (i2 <= i3 || ((float) i2) <= 480.0f) ? (i2 >= i3 || ((float) i3) <= 800.0f) ? 1 : (int) (((float) options.outHeight) / 800.0f) : (int) (((float) options.outWidth) / 480.0f);
        if (i4 > 0) {
            i = i4;
        }
        options.inSampleSize = i;
        return compressImage(BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, options));
    }

    private static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 100;
        while (byteArrayOutputStream.toByteArray().length / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT > 100) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
    }

    public static Bitmap genRotateBitmap(byte[] bArr) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.postRotate(90.0f);
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
        decodeByteArray.recycle();
        System.gc();
        return createBitmap;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static BitmapSize getBitmapSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new BitmapSize(options.outWidth, options.outHeight);
    }

    public static Bitmap getImageCompress(String str) {
        int i = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int i4 = (i2 <= i3 || ((float) i2) <= 480.0f) ? (i2 >= i3 || ((float) i3) <= 800.0f) ? 1 : (int) (((float) options.outHeight) / 800.0f) : (int) (((float) options.outWidth) / 480.0f);
        if (i4 > 0) {
            i = i4;
        }
        options.inSampleSize = i;
        return compressImage(BitmapFactory.decodeFile(str, options));
    }

    public static int getOrientation(String str) {
        try {
            int attributeInt = new ExifInterface(new File(str).getAbsolutePath()).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return SubsamplingScaleImageView.ORIENTATION_180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return SubsamplingScaleImageView.ORIENTATION_270;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap getSampledBitmap(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static BitmapSize getScaledSize(int i, int i2, int i3) {
        float f = ((float) i) / ((float) i2);
        double d = (double) (((float) i3) / f);
        int sqrt = (int) Math.sqrt(d);
        double d2 = (double) f;
        double sqrt2 = Math.sqrt(d);
        Double.isNaN(d2);
        return new BitmapSize((int) (sqrt2 * d2), sqrt);
    }

    public static Bitmap getScreenViewBitmap(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        return decorView.getDrawingCache();
    }

    public static Bitmap getViewBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2) throws OutOfMemoryError {
        return resizeBitmap(bitmap, i, i2, 0);
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2, int i3) throws OutOfMemoryError {
        int i4;
        int i5;
        boolean z;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i3 == 90 || i3 == 270) {
            i5 = i2;
            i4 = i;
        } else {
            i5 = i;
            i4 = i2;
        }
        if (width > i5 || height > i4) {
            if (width <= height || width <= i5) {
                i5 = (int) ((((float) i4) / ((float) height)) * ((float) width));
            } else {
                i4 = (int) ((((float) i5) / ((float) width)) * ((float) height));
            }
            z = true;
        } else {
            i5 = width;
            z = false;
            i4 = height;
        }
        if (!z && i3 == 0) {
            return bitmap;
        }
        if (i3 == 0) {
            return Bitmap.createScaledBitmap(bitmap, i5, i4, true);
        }
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i5) / ((float) width), ((float) i4) / ((float) height));
        matrix.postRotate((float) i3);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveBitmap2file(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return false;
            }
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory.getFreeSpace() < 10000) {
                Log.e("Utils", "存储空间不够");
                return false;
            }
            new File(externalStorageDirectory.getPath() + str).getParentFile().mkdirs();
            fileOutputStream = new FileOutputStream(externalStorageDirectory.getPath() + str);
            return bitmap.compress(compressFormat, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
    }

    public static Bitmap zoom(Bitmap bitmap, float f, float f2) {
        Matrix matrix = new Matrix();
        try {
            matrix.postScale(f / ((float) bitmap.getWidth()), f2 / ((float) bitmap.getHeight()));
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printscreen_share(View view, Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        decorView.layout(0, 0, defaultDisplay.getWidth(), defaultDisplay.getHeight());
        decorView.setDrawingCacheEnabled(true);
        Bitmap.createBitmap(decorView.getDrawingCache());
    }
}
