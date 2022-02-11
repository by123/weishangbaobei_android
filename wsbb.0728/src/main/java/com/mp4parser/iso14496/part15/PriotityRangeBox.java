package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class PriotityRangeBox extends AbstractBox {
    public static final String TYPE = "svpr";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    int max_priorityId;
    int min_priorityId;
    int reserved1 = 0;
    int reserved2 = 0;

    static {
        ajc$preClinit();
    }

    public PriotityRangeBox() {
        super(TYPE);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("PriotityRangeBox.java", PriotityRangeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved1", "com.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 45);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved1", "com.mp4parser.iso14496.part15.PriotityRangeBox", "int", "reserved1", "", "void"), 49);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMin_priorityId", "com.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 53);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMin_priorityId", "com.mp4parser.iso14496.part15.PriotityRangeBox", "int", "min_priorityId", "", "void"), 57);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved2", "com.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 61);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved2", "com.mp4parser.iso14496.part15.PriotityRangeBox", "int", "reserved2", "", "void"), 65);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMax_priorityId", "com.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 69);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMax_priorityId", "com.mp4parser.iso14496.part15.PriotityRangeBox", "int", "max_priorityId", "", "void"), 73);
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.min_priorityId = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved1 = (this.min_priorityId & 192) >> 6;
        this.min_priorityId &= 63;
        this.max_priorityId = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved2 = (this.max_priorityId & 192) >> 6;
        this.max_priorityId &= 63;
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved1 << 6) + this.min_priorityId);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved2 << 6) + this.max_priorityId);
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        return 2;
    }

    public int getMax_priorityId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.max_priorityId;
    }

    public int getMin_priorityId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.min_priorityId;
    }

    public int getReserved1() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.reserved1;
    }

    public int getReserved2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.reserved2;
    }

    public void setMax_priorityId(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.max_priorityId = i;
    }

    public void setMin_priorityId(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.min_priorityId = i;
    }

    public void setReserved1(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.reserved1 = i;
    }

    public void setReserved2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.reserved2 = i;
    }
}
