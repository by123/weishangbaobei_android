package com.luck.picture.lib.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StyleRes;
import com.luck.picture.lib.R;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import java.util.List;

public final class PictureSelectionConfig implements Parcelable {
    public static final Parcelable.Creator<PictureSelectionConfig> CREATOR = new Parcelable.Creator<PictureSelectionConfig>() {
        public PictureSelectionConfig createFromParcel(Parcel parcel) {
            return new PictureSelectionConfig(parcel);
        }

        public PictureSelectionConfig[] newArray(int i) {
            return new PictureSelectionConfig[i];
        }
    };
    public int aspect_ratio_x;
    public int aspect_ratio_y;
    public boolean camera;
    public boolean checkNumMode;
    public boolean circleDimmedLayer;
    public String compressSavePath;
    public int cropCompressQuality;
    public int cropHeight;
    public int cropWidth;
    public boolean enPreviewVideo;
    public boolean enableCrop;
    public boolean enablePreview;
    public boolean enablePreviewAudio;
    public boolean freeStyleCropEnabled;
    public boolean hideBottomControls;
    public int imageSpanCount;
    public boolean isCamera;
    public boolean isCompress;
    public boolean isDragFrame;
    public boolean isGif;
    public int maxSelectNum;
    public int mimeType;
    public int minSelectNum;
    public int minimumCompressSize;
    public boolean openClickSound;
    public String outputCameraPath;
    public int overrideHeight;
    public int overrideWidth;
    public boolean previewEggs;
    public int recordVideoSecond;
    public boolean rotateEnabled;
    public boolean scaleEnabled;
    public List<LocalMedia> selectionMedias;
    public int selectionMode;
    public boolean showCropFrame;
    public boolean showCropGrid;
    public float sizeMultiplier;
    public String suffixType;
    public boolean synOrAsy;
    @StyleRes
    public int themeStyleId;
    public int videoMaxSecond;
    public int videoMinSecond;
    public int videoQuality;
    public boolean zoomAnim;

    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final PictureSelectionConfig INSTANCE = new PictureSelectionConfig();

        private InstanceHolder() {
        }
    }

    public PictureSelectionConfig() {
    }

    protected PictureSelectionConfig(Parcel parcel) {
        boolean z = true;
        this.mimeType = parcel.readInt();
        this.camera = parcel.readByte() != 0;
        this.outputCameraPath = parcel.readString();
        this.compressSavePath = parcel.readString();
        this.suffixType = parcel.readString();
        this.themeStyleId = parcel.readInt();
        this.selectionMode = parcel.readInt();
        this.maxSelectNum = parcel.readInt();
        this.minSelectNum = parcel.readInt();
        this.videoQuality = parcel.readInt();
        this.cropCompressQuality = parcel.readInt();
        this.videoMaxSecond = parcel.readInt();
        this.videoMinSecond = parcel.readInt();
        this.recordVideoSecond = parcel.readInt();
        this.minimumCompressSize = parcel.readInt();
        this.imageSpanCount = parcel.readInt();
        this.overrideWidth = parcel.readInt();
        this.overrideHeight = parcel.readInt();
        this.aspect_ratio_x = parcel.readInt();
        this.aspect_ratio_y = parcel.readInt();
        this.sizeMultiplier = parcel.readFloat();
        this.cropWidth = parcel.readInt();
        this.cropHeight = parcel.readInt();
        this.zoomAnim = parcel.readByte() != 0;
        this.isCompress = parcel.readByte() != 0;
        this.isCamera = parcel.readByte() != 0;
        this.isGif = parcel.readByte() != 0;
        this.enablePreview = parcel.readByte() != 0;
        this.enPreviewVideo = parcel.readByte() != 0;
        this.enablePreviewAudio = parcel.readByte() != 0;
        this.checkNumMode = parcel.readByte() != 0;
        this.openClickSound = parcel.readByte() != 0;
        this.enableCrop = parcel.readByte() != 0;
        this.freeStyleCropEnabled = parcel.readByte() != 0;
        this.circleDimmedLayer = parcel.readByte() != 0;
        this.showCropFrame = parcel.readByte() != 0;
        this.showCropGrid = parcel.readByte() != 0;
        this.hideBottomControls = parcel.readByte() != 0;
        this.rotateEnabled = parcel.readByte() != 0;
        this.scaleEnabled = parcel.readByte() != 0;
        this.previewEggs = parcel.readByte() != 0;
        this.synOrAsy = parcel.readByte() != 0;
        this.isDragFrame = parcel.readByte() == 0 ? false : z;
        this.selectionMedias = parcel.createTypedArrayList(LocalMedia.CREATOR);
    }

    public static PictureSelectionConfig getCleanInstance() {
        PictureSelectionConfig instance = getInstance();
        instance.reset();
        return instance;
    }

    public static PictureSelectionConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void reset() {
        this.mimeType = 1;
        this.camera = false;
        this.themeStyleId = R.style.picture_default_style;
        this.selectionMode = 2;
        this.maxSelectNum = 9;
        this.minSelectNum = 0;
        this.videoQuality = 1;
        this.cropCompressQuality = 90;
        this.videoMaxSecond = 0;
        this.videoMinSecond = 0;
        this.recordVideoSecond = 60;
        this.minimumCompressSize = 100;
        this.imageSpanCount = 4;
        this.overrideWidth = 0;
        this.overrideHeight = 0;
        this.isCompress = false;
        this.aspect_ratio_x = 0;
        this.aspect_ratio_y = 0;
        this.cropWidth = 0;
        this.cropHeight = 0;
        this.isCamera = true;
        this.isGif = false;
        this.enablePreview = true;
        this.enPreviewVideo = true;
        this.enablePreviewAudio = true;
        this.checkNumMode = false;
        this.openClickSound = false;
        this.enableCrop = false;
        this.freeStyleCropEnabled = false;
        this.circleDimmedLayer = false;
        this.showCropFrame = true;
        this.showCropGrid = true;
        this.hideBottomControls = true;
        this.rotateEnabled = true;
        this.scaleEnabled = true;
        this.previewEggs = false;
        this.synOrAsy = true;
        this.zoomAnim = true;
        this.isDragFrame = true;
        this.outputCameraPath = "";
        this.compressSavePath = "";
        this.suffixType = ".JPEG";
        this.sizeMultiplier = 0.5f;
        this.selectionMedias = new ArrayList();
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
