package com.wx.assistants.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Base64;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class QRCodeUtil {
    private static int IMAGE_HALFWIDTH = 50;

    public static Bitmap base64ToBitmap(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d A[SYNTHETIC, Splitter:B:13:0x002d] */
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str;
        ByteArrayOutputStream byteArrayOutputStream2;
        Throwable th;
        if (bitmap != null) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    str = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                } catch (IOException e) {
                    e = e;
                }
            } catch (IOException e2) {
                e = e2;
                byteArrayOutputStream = null;
                try {
                    e.printStackTrace();
                    if (byteArrayOutputStream == null) {
                        return null;
                    }
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        return null;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                } catch (Throwable th2) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    th = th2;
                    if (byteArrayOutputStream2 != null) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                byteArrayOutputStream2 = null;
                th = th3;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.flush();
                        byteArrayOutputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } else {
            byteArrayOutputStream = null;
            str = null;
        }
        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                return str;
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        }
        return str;
    }

    public static Bitmap createBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect transparentBounds = getTransparentBounds(bitmap);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        canvas.drawBitmap(bitmap2, (float) transparentBounds.left, (float) transparentBounds.top, (Paint) null);
        canvas.save();
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap createQRCode(String str) {
        return createQRCode(str, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
    }

    public static Bitmap createQRCode(String str, int i) {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo2(String str, int i, Bitmap bitmap) {
        try {
            IMAGE_HALFWIDTH = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = createScaledBitmap.getPixel(i3, i2);
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo3(String str, int i, Bitmap bitmap) {
        try {
            IMAGE_HALFWIDTH = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -448714;
                    } else {
                        iArr[(i2 * i) + i3] = createScaledBitmap.getPixel(i3, i2) & 1728053247;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo4(String str, int i, Bitmap bitmap) {
        try {
            IMAGE_HALFWIDTH = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int[] iArr = new int[(i * i)];
            boolean z = true;
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (!encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -1;
                    } else if (z) {
                        iArr[(i2 * i) + i3] = -16777216;
                        z = false;
                    } else {
                        iArr[(i2 * i) + i3] = createScaledBitmap.getPixel(i3, i2);
                        z = true;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo5(String str, int i, Bitmap bitmap) {
        try {
            IMAGE_HALFWIDTH = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int width = encode.getWidth();
            int i2 = width / 2;
            int height = encode.getHeight() / 2;
            Matrix matrix = new Matrix();
            matrix.setScale((((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getWidth()), (((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getHeight()));
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, false);
            int[] iArr = new int[(i * i)];
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (i4 > i2 - IMAGE_HALFWIDTH && i4 < IMAGE_HALFWIDTH + i2 && i3 > height - IMAGE_HALFWIDTH && i3 < IMAGE_HALFWIDTH + height) {
                        iArr[(i3 * width) + i4] = createBitmap.getPixel((i4 - i2) + IMAGE_HALFWIDTH, (i3 - height) + IMAGE_HALFWIDTH);
                    } else if (encode.get(i4, i3)) {
                        iArr[(i3 * i) + i4] = -13127266;
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap2.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap2;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo6(String str, int i, Bitmap bitmap) {
        try {
            IMAGE_HALFWIDTH = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int width = encode.getWidth();
            int i2 = width / 2;
            int height = encode.getHeight() / 2;
            Matrix matrix = new Matrix();
            matrix.setScale((((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getWidth()), (((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getHeight()));
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, false);
            int[] iArr = new int[(i * i)];
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (i4 > i2 - IMAGE_HALFWIDTH && i4 < IMAGE_HALFWIDTH + i2 && i3 > height - IMAGE_HALFWIDTH && i3 < IMAGE_HALFWIDTH + height) {
                        iArr[(i3 * width) + i4] = createBitmap.getPixel((i4 - i2) + IMAGE_HALFWIDTH, (i3 - height) + IMAGE_HALFWIDTH);
                    } else if (encode.get(i4, i3)) {
                        int i5 = (i3 * i) + i4;
                        iArr[i5] = -15658735;
                        if ((i4 < 115 && (i3 < 115 || i3 >= i - 115)) || (i3 < 115 && i4 >= i - 115)) {
                            iArr[i5] = -448714;
                        }
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap2.setPixels(iArr, 0, i, 0, 0, i, i);
            return createBitmap2;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Rect getTransparentBounds(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < width; i5++) {
            for (int i6 = 0; i6 < height; i6++) {
                if (bitmap.getPixel(i5, i6) == 0) {
                    if (i4 == -1) {
                        i4 = i5;
                    } else if (i4 != -1) {
                        i4 = Math.min(i5, i4);
                    }
                    if (i3 == -1) {
                        i3 = i6;
                    } else if (i3 != -1) {
                        i3 = Math.min(i6, i3);
                    }
                    if (i2 == -1) {
                        i2 = i5;
                    } else if (i2 != -1) {
                        i2 = Math.max(i5, i2);
                    }
                    if (i == -1) {
                        i = i6;
                    } else if (i != -1) {
                        i = Math.max(i6, i);
                    }
                }
            }
        }
        Log.i("MainActivity", String.format("(%s,%s,%s,%s)", new Object[]{String.valueOf(i4), String.valueOf(i3), String.valueOf(i2), String.valueOf(i)}));
        return new Rect(i4, i3, i2, i);
    }

    private static Bitmap zoomImg(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
