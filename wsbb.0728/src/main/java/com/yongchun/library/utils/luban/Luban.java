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
import com.tencent.mm.opensdk.constants.ConstantsAPI;
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

    private File compress(String str, String str2, int i, int i2, int i3, long j) {
        return saveImage(str2, rotatingImage(i3, compress(str, i, i2)), j);
    }

    /* access modifiers changed from: private */
    public File firstCompress(@NonNull File file) {
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
        if (imageSize[0] <= imageSize[1]) {
            double d = (double) imageSize[0];
            double d2 = (double) imageSize[1];
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = d / d2;
            if (d3 <= 1.0d && d3 > 0.5625d) {
                int i4 = imageSize[0] > 1280 ? 1280 : imageSize[0];
                length = (long) 60;
                i2 = (imageSize[1] * i4) / imageSize[0];
                i3 = i4;
            } else if (d3 <= 0.5625d) {
                int i5 = imageSize[1] > 720 ? 720 : imageSize[1];
                int i6 = (imageSize[0] * i5) / imageSize[1];
                i2 = i5;
                i3 = i6;
            } else {
                length = 0;
                i2 = 0;
                i3 = 0;
            }
            i = i3;
        } else {
            double d4 = (double) imageSize[1];
            double d5 = (double) imageSize[0];
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = d4 / d5;
            if (d6 <= 1.0d && d6 > 0.5625d) {
                int i7 = 1280;
                if (imageSize[1] <= 1280) {
                    i7 = imageSize[1];
                }
                i = (imageSize[0] * i7) / imageSize[1];
                length = (long) 60;
                i2 = i7;
            } else if (d6 <= 0.5625d) {
                int i8 = 720;
                if (imageSize[0] <= 720) {
                    i8 = imageSize[0];
                }
                i2 = (imageSize[1] * i8) / imageSize[0];
                i = i8;
            } else {
                i = 0;
                i2 = 0;
                length = 0;
            }
        }
        return compress(absolutePath, sb2, i, i2, imageSpinAngle, length);
    }

    public static Luban get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Luban(getPhotoCacheDir(context));
        }
        return INSTANCE;
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

    private static File getPhotoCacheDir(Context context) {
        File photoCacheDir;
        synchronized (Luban.class) {
            try {
                photoCacheDir = getPhotoCacheDir(context, DEFAULT_DISK_CACHE_DIR);
            } catch (Throwable th) {
                Class<Luban> cls = Luban.class;
                throw th;
            }
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
        } else if (!Log.isLoggable(TAG, 6)) {
            return null;
        } else {
            Log.e(TAG, "default disk cache dir is null");
            return null;
        }
    }

    private static Bitmap rotatingImage(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private File saveImage(String str, Bitmap bitmap, long j) {
        int i = 100;
        Preconditions.checkNotNull(bitmap, "Lubanbitmap cannot be null");
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
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

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010d, code lost:
        if (r8 < 100.0d) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x019a, code lost:
        if (r8 >= 100.0d) goto L_0x019c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019c, code lost:
        r6 = r3;
     */
    public File thirdCompress(@NonNull File file) {
        double d;
        int i;
        int i2;
        int i3;
        int i4;
        double d2;
        int i5;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCacheDir.getAbsolutePath());
        sb.append(File.separator);
        sb.append(TextUtils.isEmpty(this.filename) ? UUID.randomUUID().toString().replace("-", "") : this.filename);
        sb.append(".jpg");
        String sb2 = sb.toString();
        String absolutePath = file.getAbsolutePath();
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        int i6 = getImageSize(absolutePath)[0];
        int i7 = getImageSize(absolutePath)[1];
        if (i6 % 2 == 1) {
            i6++;
        }
        if (i7 % 2 == 1) {
            i7++;
        }
        int i8 = i6 > i7 ? i7 : i6;
        int i9 = i6 > i7 ? i6 : i7;
        double d3 = (double) i8;
        double d4 = (double) i9;
        Double.isNaN(d3);
        Double.isNaN(d4);
        double d5 = d3 / d4;
        if (d5 > 1.0d || d5 <= 0.5625d) {
            if (d5 > 0.5625d || d5 <= 0.5d) {
                double d6 = 1280.0d / d5;
                Double.isNaN(d4);
                int ceil = (int) Math.ceil(d4 / d6);
                i3 = i8 / ceil;
                i2 = i9 / ceil;
                double d7 = (double) (i3 * i2);
                Double.isNaN(d7);
                d2 = 500.0d * (d7 / (d6 * 1280.0d));
            } else if (i9 < 1280 && file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS < 200) {
                return file;
            } else {
                int i10 = i9 / 1280;
                if (i10 == 0) {
                    i10 = 1;
                }
                i = i8 / i10;
                i2 = i9 / i10;
                double d8 = (double) (i * i2);
                Double.isNaN(d8);
                d = 400.0d * (d8 / 3686400.0d);
                if (d < 100.0d) {
                    i3 = i;
                    d = 100.0d;
                    i4 = i2;
                    i5 = i3;
                    i7 = i4;
                }
                i3 = i;
                i4 = i2;
                i5 = i3;
                i7 = i4;
            }
        } else if (i9 < 1664) {
            if (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS < 150) {
                return file;
            }
            double d9 = (double) (i9 * i8);
            double pow = Math.pow(1664.0d, 2.0d);
            Double.isNaN(d9);
            d = 150.0d * (d9 / pow);
            if (d < 60.0d) {
                d = 60.0d;
                i5 = i6;
            } else {
                i5 = i6;
            }
        } else if (i9 >= 1664 && i9 < 4990) {
            i3 = i8 / 2;
            int i11 = i9 / 2;
            double d10 = (double) (i3 * i11);
            double pow2 = Math.pow(2495.0d, 2.0d);
            Double.isNaN(d10);
            double d11 = (d10 / pow2) * 300.0d;
            d = 60.0d;
            if (d11 < 60.0d) {
                i4 = i11;
            } else {
                i4 = i11;
                d = d11;
            }
            i5 = i3;
            i7 = i4;
        } else if (i9 < 4990 || i9 >= 10240) {
            int i12 = i9 / 1280;
            if (i12 == 0) {
                i12 = 1;
            }
            i = i8 / i12;
            i2 = i9 / i12;
            double d12 = (double) (i * i2);
            double pow3 = Math.pow(2560.0d, 2.0d);
            Double.isNaN(d12);
            d = 300.0d * (d12 / pow3);
            if (d < 100.0d) {
                i3 = i;
                d = 100.0d;
                i4 = i2;
                i5 = i3;
                i7 = i4;
            }
            i3 = i;
            i4 = i2;
            i5 = i3;
            i7 = i4;
        } else {
            i3 = i8 / 4;
            i2 = i9 / 4;
            double d13 = (double) (i3 * i2);
            double pow4 = Math.pow(2560.0d, 2.0d);
            Double.isNaN(d13);
            d2 = 300.0d * (d13 / pow4);
        }
        return compress(absolutePath, sb2, i5, i7, imageSpinAngle, (long) d);
    }

    public Observable<File> asObservable() {
        return this.gear == 1 ? Observable.just(this.mFile).map(new Func1<File, File>() {
            public File call(File file) {
                return Luban.this.firstCompress(file);
            }
        }) : this.gear == 3 ? Observable.just(this.mFile).map(new Func1<File, File>() {
            public File call(File file) {
                return Luban.this.thirdCompress(file);
            }
        }) : Observable.empty();
    }

    public int[] getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
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

    public Luban putGear(int i) {
        this.gear = i;
        return this;
    }

    public Luban setCompressListener(OnCompressListener onCompressListener) {
        this.compressListener = onCompressListener;
        return this;
    }

    public Luban setFilename(String str) {
        this.filename = str;
        return this;
    }
}
