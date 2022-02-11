package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MQImageCaptureManager {
    private static final String CAPTURED_PHOTO_PATH_KEY = "CAPTURED_PHOTO_PATH_KEY";
    private Context mContext;
    private String mCurrentPhotoPath;
    private File mImageDir;

    public MQImageCaptureManager(Context context, File file) {
        this.mContext = context;
        this.mImageDir = file;
    }

    private File createImageFile() throws IOException {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File createTempFile = File.createTempFile("JPEG_" + format + "_", ".jpg", this.mImageDir);
        this.mCurrentPhotoPath = createTempFile.getAbsolutePath();
        return createTempFile;
    }

    public void deletePhotoFile() {
        if (!TextUtils.isEmpty(this.mCurrentPhotoPath)) {
            try {
                new File(this.mCurrentPhotoPath).delete();
                this.mCurrentPhotoPath = null;
            } catch (Exception e) {
            }
        }
    }

    public String getCurrentPhotoPath() {
        return this.mCurrentPhotoPath;
    }

    public Intent getTakePictureIntent() throws IOException {
        File createImageFile;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (!(intent.resolveActivity(this.mContext.getPackageManager()) == null || (createImageFile = createImageFile()) == null)) {
            intent.putExtra("output", Uri.fromFile(createImageFile));
        }
        return intent;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null && bundle.containsKey(CAPTURED_PHOTO_PATH_KEY)) {
            this.mCurrentPhotoPath = bundle.getString(CAPTURED_PHOTO_PATH_KEY);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null && this.mCurrentPhotoPath != null) {
            bundle.putString(CAPTURED_PHOTO_PATH_KEY, this.mCurrentPhotoPath);
        }
    }

    public void refreshGallery() {
        if (!TextUtils.isEmpty(this.mCurrentPhotoPath)) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(new File(this.mCurrentPhotoPath)));
            this.mContext.sendBroadcast(intent);
            this.mCurrentPhotoPath = null;
        }
    }
}
