package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import java.nio.ByteBuffer;

public class TemporalLayerSampleGroup extends GroupEntry {
    public static final String TYPE = "tscl";
    int temporalLayerId;
    int tlAvgBitRate;
    int tlAvgFrameRate;
    int tlConstantFrameRate;
    int tlMaxBitRate;
    long tlconstraint_indicator_flags;
    int tllevel_idc;
    long tlprofile_compatibility_flags;
    int tlprofile_idc;
    int tlprofile_space;
    boolean tltier_flag;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TemporalLayerSampleGroup temporalLayerSampleGroup = (TemporalLayerSampleGroup) obj;
        if (this.temporalLayerId != temporalLayerSampleGroup.temporalLayerId) {
            return false;
        }
        if (this.tlAvgBitRate != temporalLayerSampleGroup.tlAvgBitRate) {
            return false;
        }
        if (this.tlAvgFrameRate != temporalLayerSampleGroup.tlAvgFrameRate) {
            return false;
        }
        if (this.tlConstantFrameRate != temporalLayerSampleGroup.tlConstantFrameRate) {
            return false;
        }
        if (this.tlMaxBitRate != temporalLayerSampleGroup.tlMaxBitRate) {
            return false;
        }
        if (this.tlconstraint_indicator_flags != temporalLayerSampleGroup.tlconstraint_indicator_flags) {
            return false;
        }
        if (this.tllevel_idc != temporalLayerSampleGroup.tllevel_idc) {
            return false;
        }
        if (this.tlprofile_compatibility_flags != temporalLayerSampleGroup.tlprofile_compatibility_flags) {
            return false;
        }
        if (this.tlprofile_idc != temporalLayerSampleGroup.tlprofile_idc) {
            return false;
        }
        if (this.tlprofile_space != temporalLayerSampleGroup.tlprofile_space) {
            return false;
        }
        return this.tltier_flag == temporalLayerSampleGroup.tltier_flag;
    }

    public ByteBuffer get() {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        IsoTypeWriter.writeUInt8(allocate, this.temporalLayerId);
        IsoTypeWriter.writeUInt8(allocate, (this.tltier_flag ? 32 : 0) + (this.tlprofile_space << 6) + this.tlprofile_idc);
        IsoTypeWriter.writeUInt32(allocate, this.tlprofile_compatibility_flags);
        IsoTypeWriter.writeUInt48(allocate, this.tlconstraint_indicator_flags);
        IsoTypeWriter.writeUInt8(allocate, this.tllevel_idc);
        IsoTypeWriter.writeUInt16(allocate, this.tlMaxBitRate);
        IsoTypeWriter.writeUInt16(allocate, this.tlAvgBitRate);
        IsoTypeWriter.writeUInt8(allocate, this.tlConstantFrameRate);
        IsoTypeWriter.writeUInt16(allocate, this.tlAvgFrameRate);
        return (ByteBuffer) allocate.rewind();
    }

    public int getTemporalLayerId() {
        return this.temporalLayerId;
    }

    public int getTlAvgBitRate() {
        return this.tlAvgBitRate;
    }

    public int getTlAvgFrameRate() {
        return this.tlAvgFrameRate;
    }

    public int getTlConstantFrameRate() {
        return this.tlConstantFrameRate;
    }

    public int getTlMaxBitRate() {
        return this.tlMaxBitRate;
    }

    public long getTlconstraint_indicator_flags() {
        return this.tlconstraint_indicator_flags;
    }

    public int getTllevel_idc() {
        return this.tllevel_idc;
    }

    public long getTlprofile_compatibility_flags() {
        return this.tlprofile_compatibility_flags;
    }

    public int getTlprofile_idc() {
        return this.tlprofile_idc;
    }

    public int getTlprofile_space() {
        return this.tlprofile_space;
    }

    public String getType() {
        return TYPE;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public int hashCode() {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public boolean isTltier_flag() {
        return this.tltier_flag;
    }

    public void parse(ByteBuffer byteBuffer) {
        this.temporalLayerId = IsoTypeReader.readUInt8(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.tlprofile_space = (readUInt8 & 192) >> 6;
        this.tltier_flag = (readUInt8 & 32) > 0;
        this.tlprofile_idc = readUInt8 & 31;
        this.tlprofile_compatibility_flags = IsoTypeReader.readUInt32(byteBuffer);
        this.tlconstraint_indicator_flags = IsoTypeReader.readUInt48(byteBuffer);
        this.tllevel_idc = IsoTypeReader.readUInt8(byteBuffer);
        this.tlMaxBitRate = IsoTypeReader.readUInt16(byteBuffer);
        this.tlAvgBitRate = IsoTypeReader.readUInt16(byteBuffer);
        this.tlConstantFrameRate = IsoTypeReader.readUInt8(byteBuffer);
        this.tlAvgFrameRate = IsoTypeReader.readUInt16(byteBuffer);
    }

    public void setTemporalLayerId(int i) {
        this.temporalLayerId = i;
    }

    public void setTlAvgBitRate(int i) {
        this.tlAvgBitRate = i;
    }

    public void setTlAvgFrameRate(int i) {
        this.tlAvgFrameRate = i;
    }

    public void setTlConstantFrameRate(int i) {
        this.tlConstantFrameRate = i;
    }

    public void setTlMaxBitRate(int i) {
        this.tlMaxBitRate = i;
    }

    public void setTlconstraint_indicator_flags(long j) {
        this.tlconstraint_indicator_flags = j;
    }

    public void setTllevel_idc(int i) {
        this.tllevel_idc = i;
    }

    public void setTlprofile_compatibility_flags(long j) {
        this.tlprofile_compatibility_flags = j;
    }

    public void setTlprofile_idc(int i) {
        this.tlprofile_idc = i;
    }

    public void setTlprofile_space(int i) {
        this.tlprofile_space = i;
    }

    public void setTltier_flag(boolean z) {
        this.tltier_flag = z;
    }

    public int size() {
        return 20;
    }

    public String toString() {
        return "TemporalLayerSampleGroup{temporalLayerId=" + this.temporalLayerId + ", tlprofile_space=" + this.tlprofile_space + ", tltier_flag=" + this.tltier_flag + ", tlprofile_idc=" + this.tlprofile_idc + ", tlprofile_compatibility_flags=" + this.tlprofile_compatibility_flags + ", tlconstraint_indicator_flags=" + this.tlconstraint_indicator_flags + ", tllevel_idc=" + this.tllevel_idc + ", tlMaxBitRate=" + this.tlMaxBitRate + ", tlAvgBitRate=" + this.tlAvgBitRate + ", tlConstantFrameRate=" + this.tlConstantFrameRate + ", tlAvgFrameRate=" + this.tlAvgFrameRate + '}';
    }
}
