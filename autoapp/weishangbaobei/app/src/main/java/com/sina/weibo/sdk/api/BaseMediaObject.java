package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

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

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0025 A[SYNTHETIC, Splitter:B:16:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0030 A[SYNTHETIC, Splitter:B:21:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setThumbImage(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x001f }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
            r3.thumbData = r4     // Catch:{ Exception -> 0x0019, all -> 0x0017 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.BaseMediaObject.setThumbImage(android.graphics.Bitmap):void");
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
