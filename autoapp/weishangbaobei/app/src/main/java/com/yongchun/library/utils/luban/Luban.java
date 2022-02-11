package com.yongchun.library.utils.luban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Luban {
    private static String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";
    public static final int FIRST_GEAR = 1;
    private static volatile Luban INSTANCE = null;
    private static final String TAG = "Luban";
    public static final int THIRD_GEAR = 3;
    /* access modifiers changed from: private */
    public OnCompressListener compressListener;
    private String filename;
    private int gear = 3;
    private final File mCacheDir;
    private File mFile;

    private Luban(File file) {
        this.mCacheDir = file;
    }

    private static synchronized File getPhotoCacheDir(Context context) {
        File photoCacheDir;
        synchronized (Luban.class) {
            photoCacheDir = getPhotoCacheDir(context, DEFAULT_DISK_CACHE_DIR);
        }
        return photoCacheDir;
    }

    private static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (!file.mkdirs() && (!file.exists() || !file.isDirectory())) {
                return null;
            }
            File file2 = new File(cacheDir + "/.nomedia");
            if (file2.mkdirs() || (file2.exists() && file2.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    public static Luban get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Luban(getPhotoCacheDir(context));
        }
        return INSTANCE;
    }

    public Luban launch() {
        Preconditions.checkNotNull(this.mFile, "the image file cannot be null, please call .load() before this method!");
        if (this.compressListener != null) {
            this.compressListener.onStart();
        }
        if (this.gear == 1) {
            Observable.just(this.mFile).map(new Func1<File, File>() {
                public File call(File file) {
                    return Luban.this.firstCompress(file);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Action1<Throwable>() {
                public void call(Throwable th) {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onError(th);
                    }
                }
            }).onErrorResumeNext(Observable.empty()).filter(new Func1<File, Boolean>() {
                public Boolean call(File file) {
                    return Boolean.valueOf(file != null);
                }
            }).subscribe(new Action1<File>() {
                public void call(File file) {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onSuccess(file);
                    }
                }
            });
        } else if (this.gear == 3) {
            Observable.just(this.mFile).map(new Func1<File, File>() {
                public File call(File file) {
                    return Luban.this.thirdCompress(file);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Action1<Throwable>() {
                public void call(Throwable th) {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onError(th);
                    }
                }
            }).onErrorResumeNext(Observable.empty()).filter(new Func1<File, Boolean>() {
                public Boolean call(File file) {
                    return Boolean.valueOf(file != null);
                }
            }).subscribe(new Action1<File>() {
                public void call(File file) {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onSuccess(file);
                    }
                }
            });
        }
        return this;
    }

    public Luban load(File file) {
        this.mFile = file;
        return this;
    }

    public Luban setCompressListener(OnCompressListener onCompressListener) {
        this.compressListener = onCompressListener;
        return this;
    }

    public Luban putGear(int i) {
        this.gear = i;
        return this;
    }

    public Luban setFilename(String str) {
        this.filename = str;
        return this;
    }

    public Observable<File> asObservable() {
        if (this.gear == 1) {
            return Observable.just(this.mFile).map(new Func1<File, File>() {
                public File call(File file) {
                    return Luban.this.firstCompress(file);
                }
            });
        }
        if (this.gear == 3) {
            return Observable.just(this.mFile).map(new Func1<File, File>() {
                public File call(File file) {
                    return Luban.this.thirdCompress(file);
                }
            });
        }
        return Observable.empty();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fd, code lost:
        if (r3 < 100.0d) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011c, code lost:
        if (r3 < 100.0d) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0153, code lost:
        if (r3 < 100.0d) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0177, code lost:
        if (r3 < 100.0d) goto L_0x0179;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File thirdCompress(@android.support.annotation.NonNull java.io.File r26) {
        /*
            r25 = this;
            r8 = r25
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r2 = r8.mCacheDir
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            java.lang.String r2 = r8.filename
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x002e
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "-"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.replace(r3, r4)
            goto L_0x0030
        L_0x002e:
            java.lang.String r2 = r8.filename
        L_0x0030:
            r1.append(r2)
            java.lang.String r2 = ".jpg"
            r1.append(r2)
            java.lang.String r2 = r1.toString()
            java.lang.String r1 = r26.getAbsolutePath()
            int r5 = r8.getImageSpinAngle(r1)
            int[] r3 = r8.getImageSize(r1)
            r4 = 0
            r3 = r3[r4]
            int[] r4 = r8.getImageSize(r1)
            r6 = 1
            r4 = r4[r6]
            int r7 = r3 % 2
            if (r7 != r6) goto L_0x0058
            int r3 = r3 + 1
        L_0x0058:
            int r7 = r4 % 2
            if (r7 != r6) goto L_0x005e
            int r4 = r4 + 1
        L_0x005e:
            if (r3 <= r4) goto L_0x0062
            r7 = r4
            goto L_0x0063
        L_0x0062:
            r7 = r3
        L_0x0063:
            if (r3 <= r4) goto L_0x0067
            r9 = r3
            goto L_0x0068
        L_0x0067:
            r9 = r4
        L_0x0068:
            double r10 = (double) r7
            double r12 = (double) r9
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r12)
            double r10 = r10 / r12
            r14 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r16 = 1024(0x400, double:5.06E-321)
            r18 = 4603241769126068224(0x3fe2000000000000, double:0.5625)
            r20 = 4636737291354636288(0x4059000000000000, double:100.0)
            int r22 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r22 > 0) goto L_0x011f
            int r14 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r14 <= 0) goto L_0x011f
            r10 = 1664(0x680, float:2.332E-42)
            r11 = 4633641066610819072(0x404e000000000000, double:60.0)
            r13 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r9 >= r10) goto L_0x00b1
            long r18 = r26.length()
            long r18 = r18 / r16
            r15 = 150(0x96, double:7.4E-322)
            int r6 = (r18 > r15 ? 1 : (r18 == r15 ? 0 : -1))
            if (r6 >= 0) goto L_0x0096
            return r26
        L_0x0096:
            int r7 = r7 * r9
            double r6 = (double) r7
            r9 = 4655033164840828928(0x409a000000000000, double:1664.0)
            double r9 = java.lang.Math.pow(r9, r13)
            java.lang.Double.isNaN(r6)
            double r6 = r6 / r9
            r9 = 4639481672377565184(0x4062c00000000000, double:150.0)
            double r6 = r6 * r9
            int r0 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x0181
            r6 = r11
            goto L_0x0181
        L_0x00b1:
            r0 = 4990(0x137e, float:6.992E-42)
            if (r9 < r10) goto L_0x00dd
            if (r9 >= r0) goto L_0x00dd
            int r7 = r7 / 2
            int r9 = r9 / 2
            int r0 = r7 * r9
            double r11 = (double) r0
            r3 = 4657704978096324608(0x40a37e0000000000, double:2495.0)
            double r3 = java.lang.Math.pow(r3, r13)
            java.lang.Double.isNaN(r11)
            double r11 = r11 / r3
            r3 = 4643985272004935680(0x4072c00000000000, double:300.0)
            double r11 = r11 * r3
            r3 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r0 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x00da
            goto L_0x017b
        L_0x00da:
            r3 = r11
            goto L_0x017b
        L_0x00dd:
            r3 = 4657847914607935488(0x40a4000000000000, double:2560.0)
            if (r9 < r0) goto L_0x0101
            r0 = 10240(0x2800, float:1.4349E-41)
            if (r9 >= r0) goto L_0x0101
            int r7 = r7 / 4
            int r9 = r9 / 4
            int r0 = r7 * r9
            double r10 = (double) r0
            double r3 = java.lang.Math.pow(r3, r13)
            java.lang.Double.isNaN(r10)
            double r10 = r10 / r3
            r3 = 4643985272004935680(0x4072c00000000000, double:300.0)
            double r3 = r3 * r10
            int r0 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r0 >= 0) goto L_0x017b
            goto L_0x0179
        L_0x0101:
            int r0 = r9 / 1280
            if (r0 != 0) goto L_0x0106
            r0 = 1
        L_0x0106:
            int r7 = r7 / r0
            int r9 = r9 / r0
            int r0 = r7 * r9
            double r10 = (double) r0
            double r3 = java.lang.Math.pow(r3, r13)
            java.lang.Double.isNaN(r10)
            double r10 = r10 / r3
            r3 = 4643985272004935680(0x4072c00000000000, double:300.0)
            double r3 = r3 * r10
            int r0 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r0 >= 0) goto L_0x017b
            goto L_0x0179
        L_0x011f:
            int r3 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r3 > 0) goto L_0x0156
            r3 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r14 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r14 <= 0) goto L_0x0156
            r3 = 1280(0x500, float:1.794E-42)
            if (r9 >= r3) goto L_0x013a
            long r3 = r26.length()
            long r3 = r3 / r16
            r10 = 200(0xc8, double:9.9E-322)
            int r12 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r12 >= 0) goto L_0x013a
            return r26
        L_0x013a:
            int r0 = r9 / 1280
            if (r0 != 0) goto L_0x013f
            r0 = 1
        L_0x013f:
            int r7 = r7 / r0
            int r9 = r9 / r0
            int r0 = r7 * r9
            double r3 = (double) r0
            r10 = 4705170895067414528(0x414c200000000000, double:3686400.0)
            java.lang.Double.isNaN(r3)
            double r3 = r3 / r10
            r10 = 4645744490609377280(0x4079000000000000, double:400.0)
            double r3 = r3 * r10
            int r0 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r0 >= 0) goto L_0x017b
            goto L_0x0179
        L_0x0156:
            r3 = 4653344314980564992(0x4094000000000000, double:1280.0)
            double r10 = r3 / r10
            java.lang.Double.isNaN(r12)
            double r12 = r12 / r10
            double r12 = java.lang.Math.ceil(r12)
            int r0 = (int) r12
            int r7 = r7 / r0
            int r9 = r9 / r0
            int r0 = r7 * r9
            double r12 = (double) r0
            double r10 = r10 * r3
            java.lang.Double.isNaN(r12)
            double r12 = r12 / r10
            r3 = 4647503709213818880(0x407f400000000000, double:500.0)
            double r3 = r3 * r12
            int r0 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r0 >= 0) goto L_0x017b
        L_0x0179:
            r3 = r20
        L_0x017b:
            r23 = r3
            r3 = r7
            r6 = r23
            r4 = r9
        L_0x0181:
            long r6 = (long) r6
            r0 = r25
            java.io.File r0 = r0.compress(r1, r2, r3, r4, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.utils.luban.Luban.thirdCompress(java.io.File):java.io.File");
    }

    /* access modifiers changed from: private */
    public File firstCompress(@NonNull File file) {
        long j;
        int i;
        int i2;
        int i3;
        String absolutePath = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCacheDir.getAbsolutePath());
        sb.append(File.separator);
        sb.append(TextUtils.isEmpty(this.filename) ? UUID.randomUUID().toString().replace("-", "") : this.filename);
        sb.append(".jpg");
        String sb2 = sb.toString();
        long length = file.length() / 5;
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        int[] imageSize = getImageSize(absolutePath);
        int i4 = 0;
        if (imageSize[0] <= imageSize[1]) {
            double d = (double) imageSize[0];
            double d2 = (double) imageSize[1];
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = d / d2;
            if (d3 <= 1.0d && d3 > 0.5625d) {
                int i5 = imageSize[0] > 1280 ? 1280 : imageSize[0];
                j = (long) 60;
                i3 = (imageSize[1] * i5) / imageSize[0];
                i4 = i5;
            } else if (d3 <= 0.5625d) {
                i3 = imageSize[1] > 720 ? 720 : imageSize[1];
                i4 = (imageSize[0] * i3) / imageSize[1];
                j = length;
            } else {
                j = 0;
                i3 = 0;
            }
            i2 = i4;
            i = i3;
        } else {
            double d4 = (double) imageSize[1];
            double d5 = (double) imageSize[0];
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = d4 / d5;
            if (d6 <= 1.0d && d6 > 0.5625d) {
                i = 1280;
                if (imageSize[1] <= 1280) {
                    i = imageSize[1];
                }
                i2 = (imageSize[0] * i) / imageSize[1];
                j = (long) 60;
            } else if (d6 <= 0.5625d) {
                int i6 = 720;
                if (imageSize[0] <= 720) {
                    i6 = imageSize[0];
                }
                j = length;
                int i7 = i6;
                i = (imageSize[1] * i6) / imageSize[0];
                i2 = i7;
            } else {
                i2 = 0;
                i = 0;
                j = 0;
            }
        }
        return compress(absolutePath, sb2, i2, i, imageSpinAngle, j);
    }

    public int[] getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    private Bitmap compress(String str, int i, int i2) {
        int i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int i6 = i4 / 2;
            int i7 = i5 / 2;
            i3 = 1;
            while (i6 / i3 > i2 && i7 / i3 > i) {
                i3 *= 2;
            }
        } else {
            i3 = 1;
        }
        options.inSampleSize = i3;
        options.inJustDecodeBounds = false;
        int ceil = (int) Math.ceil((double) (((float) options.outHeight) / ((float) i2)));
        int ceil2 = (int) Math.ceil((double) (((float) options.outWidth) / ((float) i)));
        if (ceil > 1 || ceil2 > 1) {
            if (ceil > ceil2) {
                options.inSampleSize = ceil;
            } else {
                options.inSampleSize = ceil2;
            }
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private int getImageSpinAngle(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
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
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private File compress(String str, String str2, int i, int i2, int i3, long j) {
        return saveImage(str2, rotatingImage(i3, compress(str, i, i2)), j);
    }

    private static Bitmap rotatingImage(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private File saveImage(String str, Bitmap bitmap, long j) {
        Preconditions.checkNotNull(bitmap, "Lubanbitmap cannot be null");
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (((long) (byteArrayOutputStream.toByteArray().length / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT)) > j && i > 6) {
            byteArrayOutputStream.reset();
            i -= 6;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        bitmap.recycle();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(str);
    }
}
