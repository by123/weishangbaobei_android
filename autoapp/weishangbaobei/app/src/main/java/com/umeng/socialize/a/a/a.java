package com.umeng.socialize.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.umeng.socialize.a.b.b;
import com.umeng.socialize.a.b.d;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: ImageImpl */
public class a {
    static {
        com.umeng.socialize.a.b.a.a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077 A[SYNTHETIC, Splitter:B:24:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0098 A[SYNTHETIC, Splitter:B:31:0x0098] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(android.graphics.Bitmap r6, android.graphics.Bitmap.CompressFormat r7) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x00b6
            boolean r1 = r6.isRecycled()
            if (r1 == 0) goto L_0x000b
            goto L_0x00b6
        L_0x000b:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            r1.<init>()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            int r2 = r6.getRowBytes()     // Catch:{ Exception -> 0x0067 }
            int r3 = r6.getHeight()     // Catch:{ Exception -> 0x0067 }
            int r2 = r2 * r3
            int r2 = r2 / 1024
            r3 = 100
            float r2 = (float) r2     // Catch:{ Exception -> 0x0067 }
            float r4 = com.umeng.socialize.a.b.c.g     // Catch:{ Exception -> 0x0067 }
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x002c
            float r4 = com.umeng.socialize.a.b.c.g     // Catch:{ Exception -> 0x0067 }
            float r4 = r4 / r2
            float r2 = (float) r3     // Catch:{ Exception -> 0x0067 }
            float r4 = r4 * r2
            int r3 = (int) r4     // Catch:{ Exception -> 0x0067 }
        L_0x002c:
            java.lang.String r2 = "BitmapUtil"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0067 }
            r4.<init>()     // Catch:{ Exception -> 0x0067 }
            java.lang.String r5 = "compress quality:"
            r4.append(r5)     // Catch:{ Exception -> 0x0067 }
            r4.append(r3)     // Catch:{ Exception -> 0x0067 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0067 }
            com.umeng.socialize.utils.Log.d(r2, r4)     // Catch:{ Exception -> 0x0067 }
            r6.compress(r7, r3, r1)     // Catch:{ Exception -> 0x0067 }
            byte[] r6 = r1.toByteArray()     // Catch:{ Exception -> 0x0067 }
            r1.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0066
        L_0x004d:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bitmap2Bytes exception:"
            r0.append(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.umeng.socialize.utils.Log.um(r7)
        L_0x0066:
            return r6
        L_0x0067:
            r6 = move-exception
            goto L_0x006e
        L_0x0069:
            r6 = move-exception
            r1 = r0
            goto L_0x0096
        L_0x006c:
            r6 = move-exception
            r1 = r0
        L_0x006e:
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0095 }
            com.umeng.socialize.utils.Log.um(r6)     // Catch:{ all -> 0x0095 }
            if (r1 == 0) goto L_0x0094
            r1.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x0094
        L_0x007b:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "bitmap2Bytes exception:"
            r7.append(r1)
            java.lang.String r6 = r6.getMessage()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.umeng.socialize.utils.Log.um(r6)
        L_0x0094:
            return r0
        L_0x0095:
            r6 = move-exception
        L_0x0096:
            if (r1 == 0) goto L_0x00b5
            r1.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00b5
        L_0x009c:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bitmap2Bytes exception:"
            r0.append(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.umeng.socialize.utils.Log.um(r7)
        L_0x00b5:
            throw r6
        L_0x00b6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.a.a.a.a(android.graphics.Bitmap, android.graphics.Bitmap$CompressFormat):byte[]");
    }

    public static BitmapFactory.Options a(byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int ceil = (int) Math.ceil((double) (options.outWidth / UMImage.MAX_WIDTH));
        int ceil2 = (int) Math.ceil((double) (options.outHeight / UMImage.MAX_HEIGHT));
        if (ceil2 <= 1 || ceil <= 1) {
            if (ceil2 > 2) {
                options.inSampleSize = ceil2;
            } else if (ceil > 2) {
                options.inSampleSize = ceil;
            }
        } else if (ceil2 > ceil) {
            options.inSampleSize = ceil2;
        } else {
            options.inSampleSize = ceil;
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    public static byte[] a(UMImage uMImage, int i) {
        if (uMImage == null || uMImage.asBinImage() == null || a(uMImage) < i) {
            return uMImage.asBinImage();
        }
        if (uMImage.compressStyle == UMImage.CompressStyle.QUALITY) {
            return a(uMImage.asBinImage(), i, uMImage.compressFormat);
        }
        try {
            byte[] asBinImage = uMImage.asBinImage();
            if (asBinImage.length <= 0) {
                return uMImage.asBinImage();
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(asBinImage, 0, asBinImage.length);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(asBinImage, 0, asBinImage.length);
            while (byteArrayOutputStream.toByteArray().length > i) {
                double length = (double) asBinImage.length;
                Double.isNaN(length);
                double d = (double) i;
                Double.isNaN(d);
                double sqrt = Math.sqrt((length * 1.0d) / d);
                double width = (double) decodeByteArray.getWidth();
                Double.isNaN(width);
                int i2 = (int) (width / sqrt);
                double height = (double) decodeByteArray.getHeight();
                Double.isNaN(height);
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray, i2, (int) (height / sqrt), true);
                byteArrayOutputStream.reset();
                decodeByteArray.compress(uMImage.compressFormat, 100, byteArrayOutputStream);
                asBinImage = byteArrayOutputStream.toByteArray();
            }
            if (byteArrayOutputStream.toByteArray().length > i) {
                return null;
            }
            return asBinImage;
        } catch (Error e) {
            Log.um(UmengText.OOM + e.getMessage());
            return null;
        }
    }

    public static byte[] a(String str) {
        return SocializeNetUtils.getNetData(str);
    }

    public static Bitmap b(byte[] bArr) {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public static File c(byte[] bArr) {
        try {
            return a(bArr, b.a().b());
        } catch (IOException e) {
            Log.um("binary2File:" + e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035 A[SYNTHETIC, Splitter:B:16:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003b A[SYNTHETIC, Splitter:B:21:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File a(byte[] r3, java.io.File r4) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x001a }
            r1.<init>(r4)     // Catch:{ Exception -> 0x001a }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x001a }
            r2.<init>(r1)     // Catch:{ Exception -> 0x001a }
            r2.write(r3)     // Catch:{ Exception -> 0x0015, all -> 0x0012 }
            r2.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0038
        L_0x0012:
            r3 = move-exception
            r0 = r2
            goto L_0x0039
        L_0x0015:
            r3 = move-exception
            r0 = r2
            goto L_0x001b
        L_0x0018:
            r3 = move-exception
            goto L_0x0039
        L_0x001a:
            r3 = move-exception
        L_0x001b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r1.<init>()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = com.umeng.socialize.utils.UmengText.GET_FILE_FROM_BINARY     // Catch:{ all -> 0x0018 }
            r1.append(r2)     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0018 }
            r1.append(r3)     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0018 }
            com.umeng.socialize.utils.Log.um(r3)     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r4
        L_0x0039:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003e }
        L_0x003e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.a.a.a.a(byte[], java.io.File):java.io.File");
    }

    public static byte[] b(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        return a(bitmap, compressFormat);
    }

    static Bitmap a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static byte[] a(Context context, int i, boolean z, Bitmap.CompressFormat compressFormat) {
        Drawable drawable;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!z) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT >= 21) {
                drawable = resources.getDrawable(i, (Resources.Theme) null);
            } else {
                drawable = resources.getDrawable(i);
            }
            a(drawable).compress(compressFormat, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        byte[] bArr = new byte[0];
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeStream(context.getResources().openRawResource(i), (Rect) null, options).compress(compressFormat, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Error e) {
            Log.um("加载图片过大=" + e.getMessage());
            return bArr;
        }
    }

    public static byte[] a(File file, Bitmap.CompressFormat compressFormat) {
        return b(file, compressFormat);
    }

    public static String d(byte[] bArr) {
        return d.a(bArr);
    }

    public static int a(UMImage uMImage) {
        if (uMImage.getImageStyle() == UMImage.FILE_IMAGE) {
            return a(uMImage.asFileImage());
        }
        return e(uMImage.asBinImage());
    }

    private static byte[] b(File file, Bitmap.CompressFormat compressFormat) {
        if (file == null || !file.getAbsoluteFile().exists()) {
            return null;
        }
        byte[] a = b.a().a(file);
        if (!SocializeUtils.assertBinaryInvalid(a)) {
            return null;
        }
        if (d.m[1].equals(d.a(a))) {
            return a;
        }
        return a(a, compressFormat);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0061 A[SYNTHETIC, Splitter:B:21:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0083 A[SYNTHETIC, Splitter:B:28:0x0083] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(byte[] r4, android.graphics.Bitmap.CompressFormat r5) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = a((byte[]) r4)     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            r2 = 0
            int r3 = r4.length     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r4, r2, r3, r1)     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            r1.<init>()     // Catch:{ Exception -> 0x0045, all -> 0x0042 }
            if (r4 == 0) goto L_0x0020
            r2 = 100
            r4.compress(r5, r2, r1)     // Catch:{ Exception -> 0x001e }
            r4.recycle()     // Catch:{ Exception -> 0x001e }
            java.lang.System.gc()     // Catch:{ Exception -> 0x001e }
            goto L_0x0020
        L_0x001e:
            r4 = move-exception
            goto L_0x0047
        L_0x0020:
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x001e }
            r1.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x007f
        L_0x0028:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.FILE_TO_BINARY_ERROR
            r0.append(r1)
            java.lang.String r5 = r5.getMessage()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.umeng.socialize.utils.Log.um(r5)
            goto L_0x007f
        L_0x0042:
            r4 = move-exception
            r1 = r0
            goto L_0x0081
        L_0x0045:
            r4 = move-exception
            r1 = r0
        L_0x0047:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r5.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r2 = com.umeng.socialize.utils.UmengText.FILE_TO_BINARY_ERROR     // Catch:{ all -> 0x0080 }
            r5.append(r2)     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0080 }
            r5.append(r4)     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0080 }
            com.umeng.socialize.utils.Log.um(r4)     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x007e
        L_0x0065:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.FILE_TO_BINARY_ERROR
            r5.append(r1)
            java.lang.String r4 = r4.getMessage()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.umeng.socialize.utils.Log.um(r4)
        L_0x007e:
            r4 = r0
        L_0x007f:
            return r4
        L_0x0080:
            r4 = move-exception
        L_0x0081:
            if (r1 == 0) goto L_0x00a0
            r1.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x00a0
        L_0x0087:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.FILE_TO_BINARY_ERROR
            r0.append(r1)
            java.lang.String r5 = r5.getMessage()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.umeng.socialize.utils.Log.um(r5)
        L_0x00a0:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.a.a.a.a(byte[], android.graphics.Bitmap$CompressFormat):byte[]");
    }

    public static byte[] a(byte[] bArr, int i, Bitmap.CompressFormat compressFormat) {
        if (bArr == null || bArr.length < i) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        int i2 = 1;
        while (!z && i2 <= 10) {
            decodeByteArray.compress(compressFormat, (int) (Math.pow(0.8d, (double) i2) * 100.0d), byteArrayOutputStream);
            if (byteArrayOutputStream.size() < i) {
                z = true;
            } else {
                byteArrayOutputStream.reset();
                i2++;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (!decodeByteArray.isRecycled()) {
            decodeByteArray.recycle();
        }
        if (byteArray != null && byteArray.length <= 0) {
            Log.um(UmengText.THUMB_ERROR);
        }
        return byteArray;
    }

    private static int e(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private static int a(File file) {
        if (file == null) {
            return 0;
        }
        try {
            return new FileInputStream(file).available();
        } catch (FileNotFoundException e) {
            Log.um(UmengText.GET_IMAGE_SCALE_ERROR + e.getMessage());
            return 0;
        } catch (IOException e2) {
            Log.um(UmengText.GET_IMAGE_SCALE_ERROR + e2.getMessage());
            return 0;
        }
    }
}
