package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class TierBitRateBox extends AbstractBox {
    public static final String TYPE = "tibr";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    long avgBitRate;
    long baseBitRate;
    long maxBitRate;
    long tierAvgBitRate;
    long tierBaseBitRate;
    long tierMaxBitRate;

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("TierBitRateBox.java", TierBitRateBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBaseBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 52);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBaseBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "baseBitRate", "", "void"), 56);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTierAvgBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 92);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTierAvgBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "tierAvgBitRate", "", "void"), 96);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMaxBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 60);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMaxBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "maxBitRate", "", "void"), 64);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAvgBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 68);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAvgBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "avgBitRate", "", "void"), 72);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTierBaseBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 76);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTierBaseBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "tierBaseBitRate", "", "void"), 80);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTierMaxBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "", "", "", "long"), 84);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTierMaxBitRate", "com.mp4parser.iso14496.part15.TierBitRateBox", "long", "tierMaxBitRate", "", "void"), 88);
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        return 24;
    }

    public TierBitRateBox() {
        super(TYPE);
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt32(byteBuffer, this.baseBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.avgBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.tierBaseBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.tierMaxBitRate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.tierAvgBitRate);
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.baseBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.maxBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.avgBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.tierBaseBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.tierMaxBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.tierAvgBitRate = IsoTypeReader.readUInt32(byteBuffer);
    }

    public long getBaseBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.baseBitRate;
    }

    public void setBaseBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.longObject(j)));
        this.baseBitRate = j;
    }

    public long getMaxBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.maxBitRate;
    }

    public void setMaxBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.longObject(j)));
        this.maxBitRate = j;
    }

    public long getAvgBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.avgBitRate;
    }

    public void setAvgBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.longObject(j)));
        this.avgBitRate = j;
    }

    public long getTierBaseBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.tierBaseBitRate;
    }

    public void setTierBaseBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.longObject(j)));
        this.tierBaseBitRate = j;
    }

    public long getTierMaxBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.tierMaxBitRate;
    }

    public void setTierMaxBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.longObject(j)));
        this.tierMaxBitRate = j;
    }

    public long getTierAvgBitRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.tierAvgBitRate;
    }

    public void setTierAvgBitRate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.longObject(j)));
        this.tierAvgBitRate = j;
    }
}
