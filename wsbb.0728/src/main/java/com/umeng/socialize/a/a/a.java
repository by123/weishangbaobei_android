package com.umeng.socialize.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.a.b.b;
import com.umeng.socialize.a.b.c;
import com.umeng.socialize.a.b.d;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class a {
    static {
        com.umeng.socialize.a.b.a.a();
    }

    public static int a(UMImage uMImage) {
        return uMImage.getImageStyle() == UMImage.FILE_IMAGE ? a(uMImage.asFileImage()) : e(uMImage.asBinImage());
    }

    private static int a(File file) {
        if (file != null) {
            try {
                return new FileInputStream(file).available();
            } catch (FileNotFoundException e) {
                Log.um(UmengText.GET_IMAGE_SCALE_ERROR + e.getMessage());
            } catch (IOException e2) {
                Log.um(UmengText.GET_IMAGE_SCALE_ERROR + e2.getMessage());
            }
        }
        return 0;
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

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A[SYNTHETIC, Splitter:B:13:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037 A[SYNTHETIC, Splitter:B:18:0x0037] */
    private static File a(byte[] bArr, File file) {
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bufferedOutputStream.write(bArr);
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.um(UmengText.GET_FILE_FROM_BINARY + e.getMessage());
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return file;
                } catch (Throwable th) {
                    th = th;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedOutputStream != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedOutputStream = null;
            Log.um(UmengText.GET_FILE_FROM_BINARY + e.getMessage());
            if (bufferedOutputStream != null) {
            }
            return file;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            if (bufferedOutputStream != null) {
            }
            throw th;
        }
        return file;
    }

    public static byte[] a(Context context, int i, boolean z, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!z) {
            Resources resources = context.getResources();
            a(Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, (Resources.Theme) null) : resources.getDrawable(i)).compress(compressFormat, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeStream(context.getResources().openRawResource(i), (Rect) null, options).compress(compressFormat, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Error e) {
            Log.um("加载图片过大=" + e.getMessage());
            return new byte[0];
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070 A[SYNTHETIC, Splitter:B:21:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0093 A[SYNTHETIC, Splitter:B:28:0x0093] */
    public static byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream;
        int i = 100;
        ? r2 = 0;
        if (bitmap != null && !bitmap.isRecycled()) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    float rowBytes = (float) ((bitmap.getRowBytes() * bitmap.getHeight()) / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
                    if (rowBytes > c.g) {
                        i = (int) (((float) 100) * (c.g / rowBytes));
                    }
                    Log.d("BitmapUtil", "compress quality:" + i);
                    bitmap.compress(compressFormat, i, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                        r2 = byteArray;
                    } catch (IOException e) {
                        Log.um("bitmap2Bytes exception:" + e.getMessage());
                        r2 = byteArray;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.um(e.getMessage());
                        if (byteArrayOutputStream != null) {
                        }
                        return r2;
                    } catch (Throwable th) {
                        th = th;
                        r2 = byteArrayOutputStream;
                        if (r2 != 0) {
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                byteArrayOutputStream = null;
                Log.um(e.getMessage());
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                        Log.um("bitmap2Bytes exception:" + e4.getMessage());
                    }
                }
                return r2;
            } catch (Throwable th2) {
                th = th2;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e5) {
                        Log.um("bitmap2Bytes exception:" + e5.getMessage());
                    }
                }
                throw th;
            }
        }
        return r2;
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
            if (byteArrayOutputStream.toByteArray().length <= i) {
                return asBinImage;
            }
            return null;
        } catch (Error e) {
            Log.um(UmengText.OOM + e.getMessage());
            return null;
        }
    }

    public static byte[] a(File file, Bitmap.CompressFormat compressFormat) {
        return b(file, compressFormat);
    }

    public static byte[] a(String str) {
        return SocializeNetUtils.getNetData(str);
    }

    public static byte[] a(byte[] bArr, int i, Bitmap.CompressFormat compressFormat) {
        boolean z = false;
        if (bArr != null && bArr.length >= i) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
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
            bArr = byteArrayOutputStream.toByteArray();
            if (!decodeByteArray.isRecycled()) {
                decodeByteArray.recycle();
            }
            if (bArr != null && bArr.length <= 0) {
                Log.um(UmengText.THUMB_ERROR);
            }
        }
        return bArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b A[SYNTHETIC, Splitter:B:18:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007d A[SYNTHETIC, Splitter:B:25:0x007d] */
    private static byte[] a(byte[] bArr, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream;
        ? r2 = 0;
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, a(bArr));
            byteArrayOutputStream = new ByteArrayOutputStream();
            if (decodeByteArray != null) {
                try {
                    decodeByteArray.compress(compressFormat, 100, byteArrayOutputStream);
                    decodeByteArray.recycle();
                    System.gc();
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.um(UmengText.FILE_TO_BINARY_ERROR + e.getMessage());
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                                Log.um(UmengText.FILE_TO_BINARY_ERROR + e2.getMessage());
                            }
                        }
                        return r2;
                    } catch (Throwable th) {
                        th = th;
                        r2 = byteArrayOutputStream;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e3) {
                                Log.um(UmengText.FILE_TO_BINARY_ERROR + e3.getMessage());
                            }
                        }
                        throw th;
                    }
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                r2 = byteArray;
            } catch (IOException e4) {
                Log.um(UmengText.FILE_TO_BINARY_ERROR + e4.getMessage());
                r2 = byteArray;
            }
        } catch (Exception e5) {
            e = e5;
            byteArrayOutputStream = null;
            Log.um(UmengText.FILE_TO_BINARY_ERROR + e.getMessage());
            if (byteArrayOutputStream != null) {
            }
            return r2;
        } catch (Throwable th2) {
            th = th2;
            if (r2 != 0) {
            }
            throw th;
        }
        return r2;
    }

    public static Bitmap b(byte[] bArr) {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public static byte[] b(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        return a(bitmap, compressFormat);
    }

    private static byte[] b(File file, Bitmap.CompressFormat compressFormat) {
        if (file == null || !file.getAbsoluteFile().exists()) {
            return null;
        }
        byte[] a = b.a().a(file);
        if (!SocializeUtils.assertBinaryInvalid(a)) {
            return null;
        }
        return d.m[1].equals(d.a(a)) ? a : a(a, compressFormat);
    }

    public static File c(byte[] bArr) {
        try {
            return a(bArr, b.a().b());
        } catch (IOException e) {
            Log.um("binary2File:" + e.getMessage());
            return null;
        }
    }

    public static String d(byte[] bArr) {
        return d.a(bArr);
    }

    private static int e(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }
}
