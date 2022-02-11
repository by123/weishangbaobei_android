package com.luck.picture.lib.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class LocalMediaFolder implements Parcelable {
    public static final Parcelable.Creator<LocalMediaFolder> CREATOR = new Parcelable.Creator<LocalMediaFolder>() {
        public LocalMediaFolder createFromParcel(Parcel parcel) {
            return new LocalMediaFolder(parcel);
        }

        public LocalMediaFolder[] newArray(int i) {
            return new LocalMediaFolder[i];
        }
    };
    private int checkedNum;
    private String firstImagePath;
    private int imageNum;
    private List<LocalMedia> images = new ArrayList();
    private boolean isChecked;
    private String name;
    private String path;

    public LocalMediaFolder() {
    }

    protected LocalMediaFolder(Parcel parcel) {
        this.name = parcel.readString();
        this.path = parcel.readString();
        this.firstImagePath = parcel.readString();
        this.imageNum = parcel.readInt();
        this.checkedNum = parcel.readInt();
        this.isChecked = parcel.readByte() != 0;
        this.images = parcel.createTypedArrayList(LocalMedia.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public int getCheckedNum() {
        return this.checkedNum;
    }

    public String getFirstImagePath() {
        return this.firstImagePath;
    }

    public int getImageNum() {
        return this.imageNum;
    }

    public List<LocalMedia> getImages() {
        if (this.images == null) {
            this.images = new ArrayList();
        }
        return this.images;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void setCheckedNum(int i) {
        this.checkedNum = i;
    }

    public void setFirstImagePath(String str) {
        this.firstImagePath = str;
    }

    public void setImageNum(int i) {
        this.imageNum = i;
    }

    public void setImages(List<LocalMedia> list) {
        this.images = list;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
