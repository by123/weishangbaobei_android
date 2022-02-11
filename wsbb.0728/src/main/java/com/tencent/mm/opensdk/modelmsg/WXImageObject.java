package com.tencent.mm.opensdk.modelmsg;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.utils.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class WXImageObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 26214400;
    private static final int PATH_LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXImageObject";
    public byte[] imageData;
    public String imagePath;

    public WXImageObject() {
    }

    public WXImageObject(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, "WXImageObject <init>, exception:" + e.getMessage());
        }
    }

    public WXImageObject(byte[] bArr) {
        this.imageData = bArr;
    }

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }

    public boolean checkArgs() {
        String str;
        if ((this.imageData == null || this.imageData.length == 0) && (this.imagePath == null || this.imagePath.length() == 0)) {
            str = "checkArgs fail, all arguments are null";
        } else if (this.imageData != null && this.imageData.length > CONTENT_LENGTH_LIMIT) {
            str = "checkArgs fail, content is too large";
        } else if (this.imagePath != null && this.imagePath.length() > PATH_LENGTH_LIMIT) {
            str = "checkArgs fail, path is invalid";
        } else if (this.imagePath == null || getFileSize(this.imagePath) <= CONTENT_LENGTH_LIMIT) {
            return true;
        } else {
            str = "checkArgs fail, image content is too large";
        }
        Log.e(TAG, str);
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public int type() {
        return 2;
    }

    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
    }
}
