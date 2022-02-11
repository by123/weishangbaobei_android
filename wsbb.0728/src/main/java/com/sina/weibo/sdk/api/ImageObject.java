package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageObject extends BaseMediaObject {
    public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator() {
        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    };
    private static final int DATA_SIZE = 2097152;
    public byte[] imageData;
    public String imagePath;

    public ImageObject() {
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            return false;
        }
        if (this.imageData != null && this.imageData.length > DATA_SIZE) {
            return false;
        }
        if (this.imagePath != null && this.imagePath.length() > 512) {
            return false;
        }
        if (this.imagePath != null) {
            File file = new File(this.imagePath);
            try {
                if (!file.exists() || file.length() == 0 || file.length() > 10485760) {
                    return false;
                }
            } catch (SecurityException e) {
                return false;
            }
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public int getObjType() {
        return 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b A[SYNTHETIC, Splitter:B:10:0x001b] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A[SYNTHETIC, Splitter:B:18:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    public final void setImageObject(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
                this.imageData = byteArrayOutputStream.toByteArray();
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
    public BaseMediaObject toExtraMediaObject(String str) {
        return this;
    }

    /* access modifiers changed from: protected */
    public String toExtraMediaString() {
        return "";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }
}
