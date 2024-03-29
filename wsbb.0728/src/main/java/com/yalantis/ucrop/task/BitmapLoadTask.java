package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo) {
            this.mBitmapResult = bitmap;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception exc) {
            this.mBitmapWorkerException = exc;
        }
    }

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.mContext = context;
        this.mInputUri = uri;
        this.mOutputUri = uri2;
        this.mRequiredWidth = i;
        this.mRequiredHeight = i2;
        this.mBitmapLoadCallback = bitmapLoadCallback;
    }

    private void copyFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Log.d(TAG, "copyFile");
        if (uri2 != null) {
            try {
                inputStream = this.mContext.getContentResolver().openInputStream(uri);
                try {
                    fileOutputStream = new FileOutputStream(new File(uri2.getPath()));
                    if (inputStream != null) {
                        try {
                            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read > 0) {
                                    fileOutputStream.write(bArr, 0, read);
                                } else {
                                    BitmapLoadUtils.close(fileOutputStream);
                                    BitmapLoadUtils.close(inputStream);
                                    this.mInputUri = this.mOutputUri;
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            BitmapLoadUtils.close(fileOutputStream);
                            BitmapLoadUtils.close(inputStream);
                            this.mInputUri = this.mOutputUri;
                            throw th;
                        }
                    } else {
                        throw new NullPointerException("InputStream for given input Uri is null");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                    BitmapLoadUtils.close(fileOutputStream);
                    BitmapLoadUtils.close(inputStream);
                    this.mInputUri = this.mOutputUri;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                fileOutputStream = null;
            }
        } else {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0070  */
    private void downloadFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        BufferedSource bufferedSource;
        Response response;
        Sink sink;
        Sink sink2 = null;
        Log.d(TAG, "downloadFile");
        if (uri2 != null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                response = okHttpClient.newCall(new Request.Builder().url(uri.toString()).build()).execute();
                try {
                    bufferedSource = response.body().source();
                    try {
                        OutputStream openOutputStream = this.mContext.getContentResolver().openOutputStream(uri2);
                        if (openOutputStream != null) {
                            sink2 = Okio.sink(openOutputStream);
                            try {
                                bufferedSource.readAll(sink2);
                                BitmapLoadUtils.close(bufferedSource);
                                BitmapLoadUtils.close(sink2);
                                if (response != null) {
                                    BitmapLoadUtils.close(response.body());
                                }
                                okHttpClient.dispatcher().cancelAll();
                                this.mInputUri = this.mOutputUri;
                            } catch (Throwable th) {
                                th = th;
                                sink = sink2;
                                BitmapLoadUtils.close(bufferedSource);
                                BitmapLoadUtils.close(sink);
                                if (response != null) {
                                    BitmapLoadUtils.close(response.body());
                                }
                                okHttpClient.dispatcher().cancelAll();
                                this.mInputUri = this.mOutputUri;
                                throw th;
                            }
                        } else {
                            throw new NullPointerException("OutputStream for given output Uri is null");
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        sink = sink2;
                        BitmapLoadUtils.close(bufferedSource);
                        BitmapLoadUtils.close(sink);
                        if (response != null) {
                        }
                        okHttpClient.dispatcher().cancelAll();
                        this.mInputUri = this.mOutputUri;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedSource = null;
                    sink = null;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedSource = null;
                response = null;
                sink = null;
            }
        } else {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        String scheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + scheme);
        if ("http".equals(scheme) || "https".equals(scheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Downloading failed", e);
                throw e;
            }
        } else if ("content".equals(scheme)) {
            String path = FileUtils.getPath(this.mContext, this.mInputUri);
            if (TextUtils.isEmpty(path) || !new File(path).exists()) {
                try {
                    copyFile(this.mInputUri, this.mOutputUri);
                } catch (IOException | NullPointerException e2) {
                    Log.e(TAG, "Copying failed", e2);
                    throw e2;
                }
            } else {
                this.mInputUri = Uri.fromFile(new File(path));
            }
        } else if (!"file".equals(scheme)) {
            Log.e(TAG, "Invalid Uri scheme " + scheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + scheme);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public BitmapWorkerResult doInBackground(Void... voidArr) {
        boolean z = false;
        Bitmap bitmap = null;
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            try {
                ParcelFileDescriptor openFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInputUri, "r");
                if (openFileDescriptor != null) {
                    FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
                    if (options.outWidth == -1 || options.outHeight == -1) {
                        return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                    }
                    options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
                    options.inJustDecodeBounds = false;
                    while (!z) {
                        try {
                            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
                            z = true;
                        } catch (OutOfMemoryError e) {
                            Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                            options.inSampleSize *= 2;
                        }
                    }
                    if (bitmap == null) {
                        return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
                    }
                    if (Build.VERSION.SDK_INT >= 16) {
                        BitmapLoadUtils.close(openFileDescriptor);
                    }
                    int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
                    int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                    int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                    ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
                    Matrix matrix = new Matrix();
                    if (exifToDegrees != 0) {
                        matrix.preRotate((float) exifToDegrees);
                    }
                    if (exifToTranslation != 1) {
                        matrix.postScale((float) exifToTranslation, 1.0f);
                    }
                    return !matrix.isIdentity() ? new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo) : new BitmapWorkerResult(bitmap, exifInfo);
                }
                return new BitmapWorkerResult(new NullPointerException("ParcelFileDescriptor was null for given Uri: [" + this.mInputUri + "]"));
            } catch (FileNotFoundException e2) {
                return new BitmapWorkerResult(e2);
            }
        } catch (IOException | NullPointerException e3) {
            return new BitmapWorkerResult(e3);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(@NonNull BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(bitmapWorkerResult.mBitmapResult, bitmapWorkerResult.mExifInfo, this.mInputUri.getPath(), this.mOutputUri == null ? null : this.mOutputUri.getPath());
        } else {
            this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
        }
    }
}
