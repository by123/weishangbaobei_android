package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.umeng.socialize.ShareContent;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HevcDecoderConfigurationRecord {
    List<Array> arrays = new ArrayList();
    int avgFrameRate;
    int bitDepthChromaMinus8;
    int bitDepthLumaMinus8;
    int chromaFormat;
    int configurationVersion;
    int constantFrameRate;
    boolean frame_only_constraint_flag;
    long general_constraint_indicator_flags;
    int general_level_idc;
    long general_profile_compatibility_flags;
    int general_profile_idc;
    int general_profile_space;
    boolean general_tier_flag;
    boolean interlaced_source_flag;
    int lengthSizeMinusOne;
    int min_spatial_segmentation_idc;
    boolean non_packed_constraint_flag;
    int numTemporalLayers;
    int parallelismType;
    boolean progressive_source_flag;
    int reserved1 = 15;
    int reserved2 = 63;
    int reserved3 = 63;
    int reserved4 = 31;
    int reserved5 = 31;
    boolean temporalIdNested;

    public static class Array {
        public boolean array_completeness;
        public List<byte[]> nalUnits;
        public int nal_unit_type;
        public boolean reserved;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Array array = (Array) obj;
            if (this.array_completeness != array.array_completeness || this.nal_unit_type != array.nal_unit_type || this.reserved != array.reserved) {
                return false;
            }
            ListIterator<byte[]> listIterator = this.nalUnits.listIterator();
            ListIterator<byte[]> listIterator2 = array.nalUnits.listIterator();
            while (listIterator.hasNext() && listIterator2.hasNext()) {
                byte[] next = listIterator.next();
                byte[] next2 = listIterator2.next();
                if (next == null) {
                    if (next2 != null) {
                        return false;
                    }
                } else if (!Arrays.equals(next, next2)) {
                    return false;
                }
            }
            return !listIterator.hasNext() && !listIterator2.hasNext();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
        public int hashCode() {
            throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
        }

        public String toString() {
            return "Array{nal_unit_type=" + this.nal_unit_type + ", reserved=" + this.reserved + ", array_completeness=" + this.array_completeness + ", num_nals=" + this.nalUnits.size() + '}';
        }
    }

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
        HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord = (HevcDecoderConfigurationRecord) obj;
        if (this.avgFrameRate != hevcDecoderConfigurationRecord.avgFrameRate) {
            return false;
        }
        if (this.bitDepthChromaMinus8 != hevcDecoderConfigurationRecord.bitDepthChromaMinus8) {
            return false;
        }
        if (this.bitDepthLumaMinus8 != hevcDecoderConfigurationRecord.bitDepthLumaMinus8) {
            return false;
        }
        if (this.chromaFormat != hevcDecoderConfigurationRecord.chromaFormat) {
            return false;
        }
        if (this.configurationVersion != hevcDecoderConfigurationRecord.configurationVersion) {
            return false;
        }
        if (this.constantFrameRate != hevcDecoderConfigurationRecord.constantFrameRate) {
            return false;
        }
        if (this.general_constraint_indicator_flags != hevcDecoderConfigurationRecord.general_constraint_indicator_flags) {
            return false;
        }
        if (this.general_level_idc != hevcDecoderConfigurationRecord.general_level_idc) {
            return false;
        }
        if (this.general_profile_compatibility_flags != hevcDecoderConfigurationRecord.general_profile_compatibility_flags) {
            return false;
        }
        if (this.general_profile_idc != hevcDecoderConfigurationRecord.general_profile_idc) {
            return false;
        }
        if (this.general_profile_space != hevcDecoderConfigurationRecord.general_profile_space) {
            return false;
        }
        if (this.general_tier_flag != hevcDecoderConfigurationRecord.general_tier_flag) {
            return false;
        }
        if (this.lengthSizeMinusOne != hevcDecoderConfigurationRecord.lengthSizeMinusOne) {
            return false;
        }
        if (this.min_spatial_segmentation_idc != hevcDecoderConfigurationRecord.min_spatial_segmentation_idc) {
            return false;
        }
        if (this.numTemporalLayers != hevcDecoderConfigurationRecord.numTemporalLayers) {
            return false;
        }
        if (this.parallelismType != hevcDecoderConfigurationRecord.parallelismType) {
            return false;
        }
        if (this.reserved1 != hevcDecoderConfigurationRecord.reserved1) {
            return false;
        }
        if (this.reserved2 != hevcDecoderConfigurationRecord.reserved2) {
            return false;
        }
        if (this.reserved3 != hevcDecoderConfigurationRecord.reserved3) {
            return false;
        }
        if (this.reserved4 != hevcDecoderConfigurationRecord.reserved4) {
            return false;
        }
        if (this.reserved5 != hevcDecoderConfigurationRecord.reserved5) {
            return false;
        }
        if (this.temporalIdNested != hevcDecoderConfigurationRecord.temporalIdNested) {
            return false;
        }
        return this.arrays != null ? this.arrays.equals(hevcDecoderConfigurationRecord.arrays) : hevcDecoderConfigurationRecord.arrays == null;
    }

    public List<Array> getArrays() {
        return this.arrays;
    }

    public int getAvgFrameRate() {
        return this.avgFrameRate;
    }

    public int getBitDepthChromaMinus8() {
        return this.bitDepthChromaMinus8;
    }

    public int getBitDepthLumaMinus8() {
        return this.bitDepthLumaMinus8;
    }

    public int getChromaFormat() {
        return this.chromaFormat;
    }

    public int getConfigurationVersion() {
        return this.configurationVersion;
    }

    public int getConstantFrameRate() {
        return this.constantFrameRate;
    }

    public long getGeneral_constraint_indicator_flags() {
        return this.general_constraint_indicator_flags;
    }

    public int getGeneral_level_idc() {
        return this.general_level_idc;
    }

    public long getGeneral_profile_compatibility_flags() {
        return this.general_profile_compatibility_flags;
    }

    public int getGeneral_profile_idc() {
        return this.general_profile_idc;
    }

    public int getGeneral_profile_space() {
        return this.general_profile_space;
    }

    public int getLengthSizeMinusOne() {
        return this.lengthSizeMinusOne;
    }

    public int getMin_spatial_segmentation_idc() {
        return this.min_spatial_segmentation_idc;
    }

    public int getNumTemporalLayers() {
        return this.numTemporalLayers;
    }

    public int getParallelismType() {
        return this.parallelismType;
    }

    public int getSize() {
        Iterator<Array> it = this.arrays.iterator();
        int i = 23;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = i2 + 3;
            for (byte[] length : it.next().nalUnits) {
                i = length.length + i + 2;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public int hashCode() {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public boolean isFrame_only_constraint_flag() {
        return this.frame_only_constraint_flag;
    }

    public boolean isGeneral_tier_flag() {
        return this.general_tier_flag;
    }

    public boolean isInterlaced_source_flag() {
        return this.interlaced_source_flag;
    }

    public boolean isNon_packed_constraint_flag() {
        return this.non_packed_constraint_flag;
    }

    public boolean isProgressive_source_flag() {
        return this.progressive_source_flag;
    }

    public boolean isTemporalIdNested() {
        return this.temporalIdNested;
    }

    public void parse(ByteBuffer byteBuffer) {
        this.configurationVersion = IsoTypeReader.readUInt8(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.general_profile_space = (readUInt8 & 192) >> 6;
        this.general_tier_flag = (readUInt8 & 32) > 0;
        this.general_profile_idc = readUInt8 & 31;
        this.general_profile_compatibility_flags = IsoTypeReader.readUInt32(byteBuffer);
        this.general_constraint_indicator_flags = IsoTypeReader.readUInt48(byteBuffer);
        this.frame_only_constraint_flag = ((this.general_constraint_indicator_flags >> 44) & 8) > 0;
        this.non_packed_constraint_flag = ((this.general_constraint_indicator_flags >> 44) & 4) > 0;
        this.interlaced_source_flag = ((this.general_constraint_indicator_flags >> 44) & 2) > 0;
        this.progressive_source_flag = ((this.general_constraint_indicator_flags >> 44) & 1) > 0;
        this.general_constraint_indicator_flags &= 140737488355327L;
        this.general_level_idc = IsoTypeReader.readUInt8(byteBuffer);
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        this.reserved1 = (61440 & readUInt16) >> 12;
        this.min_spatial_segmentation_idc = readUInt16 & 4095;
        int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved2 = (readUInt82 & 252) >> 2;
        this.parallelismType = readUInt82 & 3;
        int readUInt83 = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved3 = (readUInt83 & 252) >> 2;
        this.chromaFormat = readUInt83 & 3;
        int readUInt84 = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved4 = (readUInt84 & 248) >> 3;
        this.bitDepthLumaMinus8 = readUInt84 & 7;
        int readUInt85 = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved5 = (readUInt85 & 248) >> 3;
        this.bitDepthChromaMinus8 = readUInt85 & 7;
        this.avgFrameRate = IsoTypeReader.readUInt16(byteBuffer);
        int readUInt86 = IsoTypeReader.readUInt8(byteBuffer);
        this.constantFrameRate = (readUInt86 & 192) >> 6;
        this.numTemporalLayers = (readUInt86 & 56) >> 3;
        this.temporalIdNested = (readUInt86 & 4) > 0;
        this.lengthSizeMinusOne = readUInt86 & 3;
        int readUInt87 = IsoTypeReader.readUInt8(byteBuffer);
        this.arrays = new ArrayList();
        for (int i = 0; i < readUInt87; i++) {
            Array array = new Array();
            int readUInt88 = IsoTypeReader.readUInt8(byteBuffer);
            array.array_completeness = (readUInt88 & ShareContent.MINAPP_STYLE) > 0;
            array.reserved = (readUInt88 & 64) > 0;
            array.nal_unit_type = readUInt88 & 63;
            int readUInt162 = IsoTypeReader.readUInt16(byteBuffer);
            array.nalUnits = new ArrayList();
            for (int i2 = 0; i2 < readUInt162; i2++) {
                byte[] bArr = new byte[IsoTypeReader.readUInt16(byteBuffer)];
                byteBuffer.get(bArr);
                array.nalUnits.add(bArr);
            }
            this.arrays.add(array);
        }
    }

    public void setArrays(List<Array> list) {
        this.arrays = list;
    }

    public void setAvgFrameRate(int i) {
        this.avgFrameRate = i;
    }

    public void setBitDepthChromaMinus8(int i) {
        this.bitDepthChromaMinus8 = i;
    }

    public void setBitDepthLumaMinus8(int i) {
        this.bitDepthLumaMinus8 = i;
    }

    public void setChromaFormat(int i) {
        this.chromaFormat = i;
    }

    public void setConfigurationVersion(int i) {
        this.configurationVersion = i;
    }

    public void setConstantFrameRate(int i) {
        this.constantFrameRate = i;
    }

    public void setFrame_only_constraint_flag(boolean z) {
        this.frame_only_constraint_flag = z;
    }

    public void setGeneral_constraint_indicator_flags(long j) {
        this.general_constraint_indicator_flags = j;
    }

    public void setGeneral_level_idc(int i) {
        this.general_level_idc = i;
    }

    public void setGeneral_profile_compatibility_flags(long j) {
        this.general_profile_compatibility_flags = j;
    }

    public void setGeneral_profile_idc(int i) {
        this.general_profile_idc = i;
    }

    public void setGeneral_profile_space(int i) {
        this.general_profile_space = i;
    }

    public void setGeneral_tier_flag(boolean z) {
        this.general_tier_flag = z;
    }

    public void setInterlaced_source_flag(boolean z) {
        this.interlaced_source_flag = z;
    }

    public void setLengthSizeMinusOne(int i) {
        this.lengthSizeMinusOne = i;
    }

    public void setMin_spatial_segmentation_idc(int i) {
        this.min_spatial_segmentation_idc = i;
    }

    public void setNon_packed_constraint_flag(boolean z) {
        this.non_packed_constraint_flag = z;
    }

    public void setNumTemporalLayers(int i) {
        this.numTemporalLayers = i;
    }

    public void setParallelismType(int i) {
        this.parallelismType = i;
    }

    public void setProgressive_source_flag(boolean z) {
        this.progressive_source_flag = z;
    }

    public void setTemporalIdNested(boolean z) {
        this.temporalIdNested = z;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb = new StringBuilder("HEVCDecoderConfigurationRecord{configurationVersion=");
        sb.append(this.configurationVersion);
        sb.append(", general_profile_space=");
        sb.append(this.general_profile_space);
        sb.append(", general_tier_flag=");
        sb.append(this.general_tier_flag);
        sb.append(", general_profile_idc=");
        sb.append(this.general_profile_idc);
        sb.append(", general_profile_compatibility_flags=");
        sb.append(this.general_profile_compatibility_flags);
        sb.append(", general_constraint_indicator_flags=");
        sb.append(this.general_constraint_indicator_flags);
        sb.append(", general_level_idc=");
        sb.append(this.general_level_idc);
        if (this.reserved1 != 15) {
            str = ", reserved1=" + this.reserved1;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(", min_spatial_segmentation_idc=");
        sb.append(this.min_spatial_segmentation_idc);
        if (this.reserved2 != 63) {
            str2 = ", reserved2=" + this.reserved2;
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(", parallelismType=");
        sb.append(this.parallelismType);
        if (this.reserved3 != 63) {
            str3 = ", reserved3=" + this.reserved3;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", chromaFormat=");
        sb.append(this.chromaFormat);
        if (this.reserved4 != 31) {
            str4 = ", reserved4=" + this.reserved4;
        } else {
            str4 = "";
        }
        sb.append(str4);
        sb.append(", bitDepthLumaMinus8=");
        sb.append(this.bitDepthLumaMinus8);
        if (this.reserved5 != 31) {
            str5 = ", reserved5=" + this.reserved5;
        } else {
            str5 = "";
        }
        sb.append(str5);
        sb.append(", bitDepthChromaMinus8=");
        sb.append(this.bitDepthChromaMinus8);
        sb.append(", avgFrameRate=");
        sb.append(this.avgFrameRate);
        sb.append(", constantFrameRate=");
        sb.append(this.constantFrameRate);
        sb.append(", numTemporalLayers=");
        sb.append(this.numTemporalLayers);
        sb.append(", temporalIdNested=");
        sb.append(this.temporalIdNested);
        sb.append(", lengthSizeMinusOne=");
        sb.append(this.lengthSizeMinusOne);
        sb.append(", arrays=");
        sb.append(this.arrays);
        sb.append('}');
        return sb.toString();
    }

    public void write(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt8(byteBuffer, this.configurationVersion);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.general_tier_flag ? 32 : 0) + (this.general_profile_space << 6) + this.general_profile_idc);
        IsoTypeWriter.writeUInt32(byteBuffer, this.general_profile_compatibility_flags);
        long j = this.general_constraint_indicator_flags;
        if (this.frame_only_constraint_flag) {
            j |= 140737488355328L;
        }
        if (this.non_packed_constraint_flag) {
            j |= 70368744177664L;
        }
        if (this.interlaced_source_flag) {
            j |= 35184372088832L;
        }
        if (this.progressive_source_flag) {
            j |= 17592186044416L;
        }
        IsoTypeWriter.writeUInt48(byteBuffer, j);
        IsoTypeWriter.writeUInt8(byteBuffer, this.general_level_idc);
        IsoTypeWriter.writeUInt16(byteBuffer, (this.reserved1 << 12) + this.min_spatial_segmentation_idc);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved2 << 2) + this.parallelismType);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved3 << 2) + this.chromaFormat);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved4 << 3) + this.bitDepthLumaMinus8);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved5 << 3) + this.bitDepthChromaMinus8);
        IsoTypeWriter.writeUInt16(byteBuffer, this.avgFrameRate);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.temporalIdNested ? 4 : 0) + (this.constantFrameRate << 6) + (this.numTemporalLayers << 3) + this.lengthSizeMinusOne);
        IsoTypeWriter.writeUInt8(byteBuffer, this.arrays.size());
        for (Array next : this.arrays) {
            IsoTypeWriter.writeUInt8(byteBuffer, (next.array_completeness ? ShareContent.MINAPP_STYLE : 0) + (next.reserved ? 64 : 0) + next.nal_unit_type);
            IsoTypeWriter.writeUInt16(byteBuffer, next.nalUnits.size());
            for (byte[] next2 : next.nalUnits) {
                IsoTypeWriter.writeUInt16(byteBuffer, next2.length);
                byteBuffer.put(next2);
            }
        }
    }
}
