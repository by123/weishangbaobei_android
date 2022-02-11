package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import com.meiqia.meiqiasdk.model.ImageFolderModel;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.stub.StubApp;
import java.util.ArrayList;

public class MQLoadPhotoTask extends MQAsyncTask<Void, ArrayList<ImageFolderModel>> {
    private Context mContext;
    private boolean mTakePhotoEnabled;

    public MQLoadPhotoTask(MQAsyncTask.Callback<ArrayList<ImageFolderModel>> callback, Context context, boolean z) {
        super(callback);
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.mTakePhotoEnabled = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.meiqia.meiqiasdk.model.ImageFolderModel> doInBackground(java.lang.Void... r13) {
        /*
            r12 = this;
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            com.meiqia.meiqiasdk.model.ImageFolderModel r0 = new com.meiqia.meiqiasdk.model.ImageFolderModel
            boolean r1 = r12.mTakePhotoEnabled
            r0.<init>(r1)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r2 = 0
            android.content.Context r3 = r12.mContext     // Catch:{ Exception -> 0x0137 }
            android.content.ContentResolver r4 = r3.getContentResolver()     // Catch:{ Exception -> 0x0137 }
            android.net.Uri r5 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ Exception -> 0x0137 }
            java.lang.String r3 = "_data"
            java.lang.String r6 = "_id"
            java.lang.String r7 = "width"
            java.lang.String r8 = "height"
            java.lang.String r9 = "mime_type"
            java.lang.String[] r6 = new java.lang.String[]{r3, r6, r7, r8, r9}     // Catch:{ Exception -> 0x0137 }
            java.lang.String r7 = "width>? and height>?"
            r3 = 2
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0137 }
            r3 = 0
            java.lang.String r9 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x0137 }
            r8[r3] = r9     // Catch:{ Exception -> 0x0137 }
            java.lang.String r9 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x0137 }
            r10 = 1
            r8[r10] = r9     // Catch:{ Exception -> 0x0137 }
            java.lang.String r9 = "date_added DESC"
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0137 }
            if (r4 == 0) goto L_0x012e
            int r5 = r4.getCount()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r5 <= 0) goto L_0x012e
            r5 = 1
        L_0x004c:
            boolean r6 = r4.moveToNext()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r6 == 0) goto L_0x010a
            java.lang.String r6 = "_data"
            int r6 = r4.getColumnIndex(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r7 = "mime_type"
            int r7 = r4.getColumnIndex(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r7 = r4.getString(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r8 = "_id"
            int r8 = r4.getColumnIndex(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r8 = r4.getInt(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            boolean r9 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r9 != 0) goto L_0x004c
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r7 != 0) goto L_0x004c
            if (r5 == 0) goto L_0x008b
            android.content.Context r5 = r12.mContext     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r7 = com.meiqia.meiqiasdk.R.string.mq_all_image     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r5 = r5.getString(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r0.name = r5     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r0.coverPath = r6     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r5 = 0
        L_0x008b:
            r0.addLastImage(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r9 = 29
            if (r7 < r9) goto L_0x00b2
            java.lang.String r7 = "content://media/external/images/media"
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r9.<init>()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r11 = ""
            r9.append(r11)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r9.append(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.String r8 = r9.toString()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            android.net.Uri r7 = android.net.Uri.withAppendedPath(r7, r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r0.addLastImageUri(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
        L_0x00b2:
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.io.File r7 = r7.getParentFile()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r7 == 0) goto L_0x00c2
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            goto L_0x00c3
        L_0x00c2:
            r7 = r2
        L_0x00c3:
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r8 == 0) goto L_0x00d6
            java.lang.String r8 = java.io.File.separator     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r8 = r6.lastIndexOf(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r9 = -1
            if (r8 == r9) goto L_0x00d6
            java.lang.String r7 = r6.substring(r3, r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
        L_0x00d6:
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r8 != 0) goto L_0x004c
            boolean r8 = r1.containsKey(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r8 == 0) goto L_0x00e9
            java.lang.Object r7 = r1.get(r7)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            com.meiqia.meiqiasdk.model.ImageFolderModel r7 = (com.meiqia.meiqiasdk.model.ImageFolderModel) r7     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            goto L_0x0105
        L_0x00e9:
            java.lang.String r8 = java.io.File.separator     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r8 = r7.lastIndexOf(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            int r8 = r8 + r10
            java.lang.String r8 = r7.substring(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r9 == 0) goto L_0x00fc
            java.lang.String r8 = "/"
        L_0x00fc:
            com.meiqia.meiqiasdk.model.ImageFolderModel r9 = new com.meiqia.meiqiasdk.model.ImageFolderModel     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r9.<init>(r8, r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r1.put(r7, r9)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r7 = r9
        L_0x0105:
            r7.addLastImage(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            goto L_0x004c
        L_0x010a:
            r13.add(r0)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.util.Set r0 = r1.entrySet()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
        L_0x0115:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            if (r1 == 0) goto L_0x012e
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            r13.add(r1)     // Catch:{ Exception -> 0x012b, all -> 0x0129 }
            goto L_0x0115
        L_0x0129:
            r13 = move-exception
            goto L_0x0141
        L_0x012b:
            r0 = move-exception
            r2 = r4
            goto L_0x0138
        L_0x012e:
            if (r4 == 0) goto L_0x0140
            r4.close()
            goto L_0x0140
        L_0x0134:
            r13 = move-exception
            r4 = r2
            goto L_0x0141
        L_0x0137:
            r0 = move-exception
        L_0x0138:
            r0.printStackTrace()     // Catch:{ all -> 0x0134 }
            if (r2 == 0) goto L_0x0140
            r2.close()
        L_0x0140:
            return r13
        L_0x0141:
            if (r4 == 0) goto L_0x0146
            r4.close()
        L_0x0146:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.MQLoadPhotoTask.doInBackground(java.lang.Void[]):java.util.ArrayList");
    }

    public MQLoadPhotoTask perform() {
        if (Build.VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            execute(new Void[0]);
        }
        return this;
    }
}
