package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class BaseMediaObject implements Parcelable {
    public static final int MEDIA_TYPE_CMD = 7;
    public static final int MEDIA_TYPE_IMAGE = 2;
    public static final int MEDIA_TYPE_MUSIC = 3;
    public static final int MEDIA_TYPE_TEXT = 1;
    public static final int MEDIA_TYPE_VIDEO = 4;
    public static final int MEDIA_TYPE_VOICE = 6;
    public static final int MEDIA_TYPE_WEBPAGE = 5;
    public String actionUrl;
    public String description;
    public String identify;
    public String schema;
    public byte[] thumbData;
    public String title;

    public BaseMediaObject() {
    }

    public BaseMediaObject(Parcel parcel) {
        this.actionUrl = parcel.readString();
        this.schema = parcel.readString();
        this.identify = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbData = parcel.createByteArray();
    }

    /* access modifiers changed from: protected */
    public boolean checkArgs() {
        return this.actionUrl != null && this.actionUrl.length() <= 512 && this.identify != null && this.identify.length() <= 512 && this.thumbData != null && this.thumbData.length <= 32768 && this.title != null && this.title.length() <= 512 && this.description != null && this.description.length() <= 1024;
    }

    public int describeContents() {
        return 0;
    }

    public abstract int getObjType();

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b A[SYNTHETIC, Splitter:B:10:0x001b] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A[SYNTHETIC, Splitter:B:18:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    public final void setThumbImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
                this.thumbData = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    if (byteArrayOutputStream == null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                }
                throw th;
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            e.printStackTrace();
            if (byteArrayOutputStream == null) {
                byteArrayOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = byteArrayOutputStream2;
            if (byteArrayOutputStream != null) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public abstract BaseMediaObject toExtraMediaObject(String str);

    /* access modifiers changed from: protected */
    public abstract String toExtraMediaString();

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.schema);
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.thumbData);
    }
}
