package com.luck.picture.lib.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class LocalMedia implements Parcelable {
    public static final Parcelable.Creator<LocalMedia> CREATOR = new Parcelable.Creator<LocalMedia>() {
        public LocalMedia createFromParcel(Parcel parcel) {
            return new LocalMedia(parcel);
        }

        public LocalMedia[] newArray(int i) {
            return new LocalMedia[i];
        }
    };
    private String compressPath;
    private boolean compressed;
    private String cutPath;
    private long duration;
    private int height;
    private boolean isChecked;
    private boolean isCut;
    private int mimeType;
    private int num;
    private String path;
    private String pictureType;
    public int position;
    private int width;

    public LocalMedia() {
    }

    protected LocalMedia(Parcel parcel) {
        boolean z = true;
        this.path = parcel.readString();
        this.compressPath = parcel.readString();
        this.cutPath = parcel.readString();
        this.duration = parcel.readLong();
        this.isChecked = parcel.readByte() != 0;
        this.isCut = parcel.readByte() != 0;
        this.position = parcel.readInt();
        this.num = parcel.readInt();
        this.mimeType = parcel.readInt();
        this.pictureType = parcel.readString();
        this.compressed = parcel.readByte() == 0 ? false : z;
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public LocalMedia(String str, long j, int i, String str2) {
        this.path = str;
        this.duration = j;
        this.mimeType = i;
        this.pictureType = str2;
    }

    public LocalMedia(String str, long j, int i, String str2, int i2, int i3) {
        this.path = str;
        this.duration = j;
        this.mimeType = i;
        this.pictureType = str2;
        this.width = i2;
        this.height = i3;
    }

    public LocalMedia(String str, long j, boolean z, int i, int i2, int i3) {
        this.path = str;
        this.duration = j;
        this.isChecked = z;
        this.position = i;
        this.num = i2;
        this.mimeType = i3;
    }

    public int describeContents() {
        return 0;
    }

    public String getCompressPath() {
        return this.compressPath;
    }

    public String getCutPath() {
        return this.cutPath;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMimeType() {
        return this.mimeType;
    }

    public int getNum() {
        return this.num;
    }

    public String getPath() {
        return this.path;
    }

    public String getPictureType() {
        if (TextUtils.isEmpty(this.pictureType)) {
            this.pictureType = "image/jpeg";
        }
        return this.pictureType;
    }

    public int getPosition() {
        return this.position;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isCompressed() {
        return this.compressed;
    }

    public boolean isCut() {
        return this.isCut;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void setCompressPath(String str) {
        this.compressPath = str;
    }

    public void setCompressed(boolean z) {
        this.compressed = z;
    }

    public void setCut(boolean z) {
        this.isCut = z;
    }

    public void setCutPath(String str) {
        this.cutPath = str;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setMimeType(int i) {
        this.mimeType = i;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setPictureType(String str) {
        this.pictureType = str;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        return "LocalMedia{path='" + this.path + '\'' + ", compressPath='" + this.compressPath + '\'' + ", cutPath='" + this.cutPath + '\'' + ", duration=" + this.duration + ", isChecked=" + this.isChecked + ", isCut=" + this.isCut + ", position=" + this.position + ", num=" + this.num + ", mimeType=" + this.mimeType + ", pictureType='" + this.pictureType + '\'' + ", compressed=" + this.compressed + ", width=" + this.width + ", height=" + this.height + '}';
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
