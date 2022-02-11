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
import java.util.Hashtable;

public class QRCodeUtil {
    private static int IMAGE_HALFWIDTH = 50;

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
            int i2 = 0;
            boolean z = true;
            while (i2 < i) {
                boolean z2 = z;
                for (int i3 = 0; i3 < i; i3++) {
                    if (!encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -1;
                    } else if (z2) {
                        iArr[(i2 * i) + i3] = -16777216;
                        z2 = false;
                    } else {
                        iArr[(i2 * i) + i3] = createScaledBitmap.getPixel(i3, i2);
                        z2 = true;
                    }
                }
                i2++;
                z = z2;
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
        int i2 = i;
        try {
            IMAGE_HALFWIDTH = i2 / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int width = encode.getWidth();
            int i3 = width / 2;
            int height = encode.getHeight() / 2;
            Matrix matrix = new Matrix();
            matrix.setScale((((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getWidth()), (((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getHeight()));
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, false);
            int[] iArr = new int[(i2 * i2)];
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = 0; i5 < i2; i5++) {
                    if (i5 > i3 - IMAGE_HALFWIDTH && i5 < IMAGE_HALFWIDTH + i3 && i4 > height - IMAGE_HALFWIDTH && i4 < IMAGE_HALFWIDTH + height) {
                        iArr[(i4 * width) + i5] = createBitmap.getPixel((i5 - i3) + IMAGE_HALFWIDTH, (i4 - height) + IMAGE_HALFWIDTH);
                    } else if (encode.get(i5, i4)) {
                        iArr[(i4 * i2) + i5] = -13127266;
                    } else {
                        iArr[(i4 * i2) + i5] = -1;
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
        int i2 = i;
        try {
            IMAGE_HALFWIDTH = i2 / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            int width = encode.getWidth();
            int i3 = width / 2;
            int height = encode.getHeight() / 2;
            Matrix matrix = new Matrix();
            matrix.setScale((((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getWidth()), (((float) IMAGE_HALFWIDTH) * 2.0f) / ((float) createScaledBitmap.getHeight()));
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, false);
            int[] iArr = new int[(i2 * i2)];
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = 0; i5 < i2; i5++) {
                    if (i5 > i3 - IMAGE_HALFWIDTH && i5 < IMAGE_HALFWIDTH + i3 && i4 > height - IMAGE_HALFWIDTH && i4 < IMAGE_HALFWIDTH + height) {
                        iArr[(i4 * width) + i5] = createBitmap.getPixel((i5 - i3) + IMAGE_HALFWIDTH, (i4 - height) + IMAGE_HALFWIDTH);
                    } else if (encode.get(i5, i4)) {
                        int i6 = (i4 * i2) + i5;
                        iArr[i6] = -15658735;
                        if ((i5 < 115 && (i4 < 115 || i4 >= i2 - 115)) || (i4 < 115 && i5 >= i2 - 115)) {
                            iArr[i6] = -448714;
                        }
                    } else {
                        iArr[(i4 * i2) + i5] = -1;
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
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        while (i < width) {
            int i6 = i5;
            int i7 = i4;
            int i8 = i3;
            int i9 = i2;
            for (int i10 = 0; i10 < height; i10++) {
                if (bitmap.getPixel(i, i10) == 0) {
                    if (i9 == -1) {
                        i9 = i;
                    } else if (i9 != -1) {
                        i9 = Math.min(i, i9);
                    }
                    if (i8 == -1) {
                        i8 = i10;
                    } else if (i8 != -1) {
                        i8 = Math.min(i10, i8);
                    }
                    if (i7 == -1) {
                        i7 = i;
                    } else if (i7 != -1) {
                        i7 = Math.max(i, i7);
                    }
                    if (i6 == -1) {
                        i6 = i10;
                    } else if (i6 != -1) {
                        i6 = Math.max(i10, i6);
                    }
                }
            }
            i++;
            i2 = i9;
            i3 = i8;
            i4 = i7;
            i5 = i6;
        }
        Log.i("MainActivity", String.format("(%s,%s,%s,%s)", new Object[]{String.valueOf(i2), String.valueOf(i3), String.valueOf(i4), String.valueOf(i5)}));
        return new Rect(i2, i3, i4, i5);
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

    private static Bitmap zoomImg(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c A[SYNTHETIC, Splitter:B:15:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c A[SYNTHETIC, Splitter:B:22:0x003c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String bitmapToBase64(android.graphics.Bitmap r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0048
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
            r1.<init>()     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ IOException -> 0x0020 }
            r3 = 100
            r4.compress(r2, r3, r1)     // Catch:{ IOException -> 0x0020 }
            r1.flush()     // Catch:{ IOException -> 0x0020 }
            r1.close()     // Catch:{ IOException -> 0x0020 }
            byte[] r4 = r1.toByteArray()     // Catch:{ IOException -> 0x0020 }
            r2 = 0
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r2)     // Catch:{ IOException -> 0x0020 }
            r0 = r1
            goto L_0x0049
        L_0x0020:
            r4 = move-exception
            goto L_0x0027
        L_0x0022:
            r4 = move-exception
            r1 = r0
            goto L_0x003a
        L_0x0025:
            r4 = move-exception
            r1 = r0
        L_0x0027:
            r4.printStackTrace()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0037
            r1.flush()     // Catch:{ IOException -> 0x0033 }
            r1.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0037:
            r4 = r0
            goto L_0x0056
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            if (r1 == 0) goto L_0x0047
            r1.flush()     // Catch:{ IOException -> 0x0043 }
            r1.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0047:
            throw r4
        L_0x0048:
            r4 = r0
        L_0x0049:
            if (r0 == 0) goto L_0x0056
            r0.flush()     // Catch:{ IOException -> 0x0052 }
            r0.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0056:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.QRCodeUtil.bitmapToBase64(android.graphics.Bitmap):java.lang.String");
    }

    public static Bitmap base64ToBitmap(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }
}
