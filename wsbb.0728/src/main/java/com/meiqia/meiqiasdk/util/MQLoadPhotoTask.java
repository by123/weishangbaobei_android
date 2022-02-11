package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.ImageFolderModel;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.stub.StubApp;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MQLoadPhotoTask extends MQAsyncTask<Void, ArrayList<ImageFolderModel>> {
    private Context mContext;
    private boolean mTakePhotoEnabled;

    public MQLoadPhotoTask(MQAsyncTask.Callback<ArrayList<ImageFolderModel>> callback, Context context, boolean z) {
        super(callback);
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.mTakePhotoEnabled = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0152  */
    public ArrayList<ImageFolderModel> doInBackground(Void... voidArr) {
        boolean z;
        ImageFolderModel imageFolderModel;
        int lastIndexOf;
        Cursor cursor = null;
        ArrayList<ImageFolderModel> arrayList = new ArrayList<>();
        ImageFolderModel imageFolderModel2 = new ImageFolderModel(this.mTakePhotoEnabled);
        HashMap hashMap = new HashMap();
        try {
            Cursor cursor2 = this.mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", SocializeProtocolConstants.WIDTH, SocializeProtocolConstants.HEIGHT, "mime_type"}, "width>? and height>?", new String[]{String.valueOf(0), String.valueOf(0)}, "date_added DESC");
            if (cursor2 != null) {
                try {
                    if (cursor2.getCount() > 0) {
                        boolean z2 = true;
                        while (cursor2.moveToNext()) {
                            String string = cursor2.getString(cursor2.getColumnIndex("_data"));
                            String string2 = cursor2.getString(cursor2.getColumnIndex("mime_type"));
                            int i = cursor2.getInt(cursor2.getColumnIndex("_id"));
                            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                                if (z2) {
                                    imageFolderModel2.name = this.mContext.getString(R.string.mq_all_image);
                                    imageFolderModel2.coverPath = string;
                                    z = false;
                                } else {
                                    z = z2;
                                }
                                imageFolderModel2.addLastImage(string);
                                if (Build.VERSION.SDK_INT >= 29) {
                                    imageFolderModel2.addLastImageUri(Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i));
                                }
                                File parentFile = new File(string).getParentFile();
                                String absolutePath = parentFile != null ? parentFile.getAbsolutePath() : null;
                                if (TextUtils.isEmpty(absolutePath) && (lastIndexOf = string.lastIndexOf(File.separator)) != -1) {
                                    absolutePath = string.substring(0, lastIndexOf);
                                }
                                if (!TextUtils.isEmpty(absolutePath)) {
                                    if (hashMap.containsKey(absolutePath)) {
                                        imageFolderModel = (ImageFolderModel) hashMap.get(absolutePath);
                                    } else {
                                        String substring = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1);
                                        ImageFolderModel imageFolderModel3 = new ImageFolderModel(TextUtils.isEmpty(substring) ? "/" : substring, string);
                                        hashMap.put(absolutePath, imageFolderModel3);
                                        imageFolderModel = imageFolderModel3;
                                    }
                                    imageFolderModel.addLastImage(string);
                                    z2 = z;
                                } else {
                                    z2 = z;
                                }
                            }
                        }
                        arrayList.add(imageFolderModel2);
                        for (Map.Entry value : hashMap.entrySet()) {
                            arrayList.add(value.getValue());
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    cursor = cursor2;
                    try {
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
                return arrayList;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return arrayList;
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
