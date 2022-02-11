package com.wx.assistants.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LabelBean implements Parcelable {
    public static final Parcelable.Creator<LabelBean> CREATOR = new Parcelable.Creator<LabelBean>() {
        public LabelBean createFromParcel(Parcel parcel) {
            return new LabelBean(parcel);
        }

        public LabelBean[] newArray(int i) {
            return new LabelBean[i];
        }
    };
    private boolean isSelected = false;
    private String labelName;
    private String labelNameText;

    public LabelBean() {
    }

    protected LabelBean(Parcel parcel) {
        boolean z = false;
        this.labelName = parcel.readString();
        this.labelNameText = parcel.readString();
        this.isSelected = parcel.readByte() != 0 ? true : z;
    }

    public LabelBean(String str, String str2) {
        this.labelName = str;
        this.labelNameText = str2;
    }

    public static Parcelable.Creator<LabelBean> getCREATOR() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public String getLabelNameText() {
        return this.labelNameText;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setLabelName(String str) {
        this.labelName = str;
    }

    public void setLabelNameText(String str) {
        this.labelNameText = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String toString() {
        return "LabelBean{labelName='" + this.labelName + '\'' + ", labelNameText='" + this.labelNameText + '\'' + '}';
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
