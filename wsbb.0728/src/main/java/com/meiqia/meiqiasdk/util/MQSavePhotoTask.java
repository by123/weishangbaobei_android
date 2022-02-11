package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

public class MQSavePhotoTask extends MQAsyncTask<Void, Void> {
    private SoftReference<Bitmap> mBitmap;
    private Context mContext;
    private File mNewFile;

    public MQSavePhotoTask(MQAsyncTask.Callback<Void> callback, Context context, File file) {
        super(callback);
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.mNewFile = file;
    }

    private void recycleBitmap() {
        if (this.mBitmap != null && this.mBitmap.get() != null && !this.mBitmap.get().isRecycled()) {
            this.mBitmap.get().recycle();
            this.mBitmap = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0054 A[SYNTHETIC, Splitter:B:12:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0066 A[SYNTHETIC, Splitter:B:21:0x0066] */
    public Void doInBackground(Void... voidArr) {
        FileOutputStream fileOutputStream;
        Throwable th;
        Throwable th2;
        FileOutputStream fileOutputStream2;
        try {
            FileOutputStream fileOutputStream3 = new FileOutputStream(this.mNewFile);
            try {
                this.mBitmap.get().compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream3);
                fileOutputStream3.flush();
                this.mContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(this.mNewFile)));
                MQUtils.showSafe(this.mContext, (CharSequence) this.mContext.getString(R.string.mq_save_img_success_folder, new Object[]{this.mNewFile.getParentFile().getAbsolutePath()}));
                try {
                    fileOutputStream3.close();
                } catch (IOException e) {
                    MQUtils.showSafe(this.mContext, R.string.mq_save_img_failure);
                }
            } catch (Exception e2) {
                fileOutputStream2 = fileOutputStream3;
                try {
                    MQUtils.showSafe(this.mContext, R.string.mq_save_img_failure);
                    if (fileOutputStream2 != null) {
                    }
                    recycleBitmap();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream2;
                    th2 = th;
                    if (fileOutputStream != null) {
                    }
                    recycleBitmap();
                    throw th2;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = fileOutputStream3;
                th2 = th;
                if (fileOutputStream != null) {
                }
                recycleBitmap();
                throw th2;
            }
        } catch (Exception e3) {
            fileOutputStream2 = null;
            MQUtils.showSafe(this.mContext, R.string.mq_save_img_failure);
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            recycleBitmap();
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    MQUtils.showSafe(this.mContext, R.string.mq_save_img_failure);
                }
            }
            recycleBitmap();
            throw th2;
        }
        recycleBitmap();
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        recycleBitmap();
    }

    public void setBitmapAndPerform(Bitmap bitmap) {
        this.mBitmap = new SoftReference<>(bitmap);
        if (Build.VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            execute(new Void[0]);
        }
    }
}
