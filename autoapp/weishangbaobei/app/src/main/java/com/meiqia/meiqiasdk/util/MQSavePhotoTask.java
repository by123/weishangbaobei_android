package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.stub.StubApp;
import java.io.File;
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

    public void setBitmapAndPerform(Bitmap bitmap) {
        this.mBitmap = new SoftReference<>(bitmap);
        if (Build.VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            execute(new Void[0]);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:11|12|(2:14|15)) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.meiqia.meiqiasdk.util.MQUtils.showSafe(r8.mContext, com.meiqia.meiqiasdk.R.string.mq_save_img_failure);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005a, code lost:
        if (r0 != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
        r9 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0053 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006e A[SYNTHETIC, Splitter:B:22:0x006e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Void doInBackground(java.lang.Void... r9) {
        /*
            r8 = this;
            r9 = 0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            java.io.File r1 = r8.mNewFile     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            java.lang.ref.SoftReference<android.graphics.Bitmap> r1 = r8.mBitmap     // Catch:{ Exception -> 0x0053 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0053 }
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1     // Catch:{ Exception -> 0x0053 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0053 }
            r3 = 100
            r1.compress(r2, r3, r0)     // Catch:{ Exception -> 0x0053 }
            r0.flush()     // Catch:{ Exception -> 0x0053 }
            android.content.Context r1 = r8.mContext     // Catch:{ Exception -> 0x0053 }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Exception -> 0x0053 }
            java.lang.String r3 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            java.io.File r4 = r8.mNewFile     // Catch:{ Exception -> 0x0053 }
            android.net.Uri r4 = android.net.Uri.fromFile(r4)     // Catch:{ Exception -> 0x0053 }
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0053 }
            r1.sendBroadcast(r2)     // Catch:{ Exception -> 0x0053 }
            android.content.Context r1 = r8.mContext     // Catch:{ Exception -> 0x0053 }
            android.content.Context r2 = r8.mContext     // Catch:{ Exception -> 0x0053 }
            int r3 = com.meiqia.meiqiasdk.R.string.mq_save_img_success_folder     // Catch:{ Exception -> 0x0053 }
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0053 }
            r5 = 0
            java.io.File r6 = r8.mNewFile     // Catch:{ Exception -> 0x0053 }
            java.io.File r6 = r6.getParentFile()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ Exception -> 0x0053 }
            r4[r5] = r6     // Catch:{ Exception -> 0x0053 }
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ Exception -> 0x0053 }
            com.meiqia.meiqiasdk.util.MQUtils.showSafe((android.content.Context) r1, (java.lang.CharSequence) r2)     // Catch:{ Exception -> 0x0053 }
            r0.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0067
        L_0x004d:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x006c
        L_0x0052:
            r0 = r9
        L_0x0053:
            android.content.Context r1 = r8.mContext     // Catch:{ all -> 0x006b }
            int r2 = com.meiqia.meiqiasdk.R.string.mq_save_img_failure     // Catch:{ all -> 0x006b }
            com.meiqia.meiqiasdk.util.MQUtils.showSafe((android.content.Context) r1, (int) r2)     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0067
        L_0x0060:
            android.content.Context r0 = r8.mContext
            int r1 = com.meiqia.meiqiasdk.R.string.mq_save_img_failure
            com.meiqia.meiqiasdk.util.MQUtils.showSafe((android.content.Context) r0, (int) r1)
        L_0x0067:
            r8.recycleBitmap()
            return r9
        L_0x006b:
            r9 = move-exception
        L_0x006c:
            if (r0 == 0) goto L_0x0079
            r0.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0079
        L_0x0072:
            android.content.Context r0 = r8.mContext
            int r1 = com.meiqia.meiqiasdk.R.string.mq_save_img_failure
            com.meiqia.meiqiasdk.util.MQUtils.showSafe((android.content.Context) r0, (int) r1)
        L_0x0079:
            r8.recycleBitmap()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.MQSavePhotoTask.doInBackground(java.lang.Void[]):java.lang.Void");
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        recycleBitmap();
    }

    private void recycleBitmap() {
        if (this.mBitmap != null && this.mBitmap.get() != null && !this.mBitmap.get().isRecycled()) {
            this.mBitmap.get().recycle();
            this.mBitmap = null;
        }
    }
}
