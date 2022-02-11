package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;

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
        if (this.imagePath == null) {
            return true;
        }
        File file = new File(this.imagePath);
        try {
            return file.exists() && file.length() != 0 && file.length() <= 10485760;
        } catch (SecurityException unused) {
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getObjType() {
        return 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0025 A[SYNTHETIC, Splitter:B:16:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0030 A[SYNTHETIC, Splitter:B:21:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setImageObject(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x001f }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            r3.imageData = r4     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            r1.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0017:
            r4 = move-exception
            goto L_0x002e
        L_0x0019:
            r4 = move-exception
            r0 = r1
            goto L_0x0020
        L_0x001c:
            r4 = move-exception
            r1 = r0
            goto L_0x002e
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r4.printStackTrace()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x002d
            r0.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r4 = move-exception
            r4.printStackTrace()
        L_0x002d:
            return
        L_0x002e:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0038:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.ImageObject.setImageObject(android.graphics.Bitmap):void");
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
