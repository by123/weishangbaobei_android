package com.googlecode.mp4parser.boxes.mp4.samplegrouping;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import com.mp4parser.iso14496.part15.StepwiseTemporalLayerEntry;
import com.mp4parser.iso14496.part15.SyncSampleEntry;
import com.mp4parser.iso14496.part15.TemporalLayerSampleGroup;
import com.mp4parser.iso14496.part15.TemporalSubLayerSampleGroup;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class SampleGroupDescriptionBox extends AbstractFullBox {
    public static final String TYPE = "sgpd";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private int defaultLength;
    private List<GroupEntry> groupEntries = new LinkedList();

    static {
        ajc$preClinit();
    }

    public SampleGroupDescriptionBox() {
        super(TYPE);
        setVersion(1);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("SampleGroupDescriptionBox.java", SampleGroupDescriptionBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultLength", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 145);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultLength", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "int", "defaultLength", "", "void"), 149);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGroupEntries", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.util.List"), 153);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGroupEntries", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "java.util.List", "groupEntries", "", "void"), 157);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "java.lang.Object", "o", "", "boolean"), 162);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 183);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.lang.String"), 191);
    }

    private GroupEntry parseGroupEntry(ByteBuffer byteBuffer, String str) {
        GroupEntry rollRecoveryEntry = RollRecoveryEntry.TYPE.equals(str) ? new RollRecoveryEntry() : RateShareEntry.TYPE.equals(str) ? new RateShareEntry() : CencSampleEncryptionInformationGroupEntry.TYPE.equals(str) ? new CencSampleEncryptionInformationGroupEntry() : VisualRandomAccessEntry.TYPE.equals(str) ? new VisualRandomAccessEntry() : TemporalLevelEntry.TYPE.equals(str) ? new TemporalLevelEntry() : SyncSampleEntry.TYPE.equals(str) ? new SyncSampleEntry() : TemporalLayerSampleGroup.TYPE.equals(str) ? new TemporalLayerSampleGroup() : TemporalSubLayerSampleGroup.TYPE.equals(str) ? new TemporalSubLayerSampleGroup() : StepwiseTemporalLayerEntry.TYPE.equals(str) ? new StepwiseTemporalLayerEntry() : new UnknownEntry(str);
        rollRecoveryEntry.parse(byteBuffer);
        return rollRecoveryEntry;
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        if (getVersion() == 1) {
            String read4cc = IsoTypeReader.read4cc(byteBuffer);
            if (getVersion() == 1) {
                this.defaultLength = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
            }
            long readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
            while (true) {
                long j = readUInt32;
                if (j > 0) {
                    int i = this.defaultLength;
                    if (getVersion() == 1) {
                        if (this.defaultLength == 0) {
                            i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
                        }
                        int position = byteBuffer.position();
                        ByteBuffer slice = byteBuffer.slice();
                        slice.limit(i);
                        this.groupEntries.add(parseGroupEntry(slice, read4cc));
                        byteBuffer.position(i + position);
                        readUInt32 = j - 1;
                    } else {
                        throw new RuntimeException("This should be implemented");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new RuntimeException("SampleGroupDescriptionBox are only supported in version 1");
        }
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SampleGroupDescriptionBox sampleGroupDescriptionBox = (SampleGroupDescriptionBox) obj;
        if (this.defaultLength != sampleGroupDescriptionBox.defaultLength) {
            return false;
        }
        return this.groupEntries != null ? this.groupEntries.equals(sampleGroupDescriptionBox.groupEntries) : sampleGroupDescriptionBox.groupEntries == null;
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(IsoFile.fourCCtoBytes(this.groupEntries.get(0).getType()));
        if (getVersion() == 1) {
            IsoTypeWriter.writeUInt32(byteBuffer, (long) this.defaultLength);
        }
        IsoTypeWriter.writeUInt32(byteBuffer, (long) this.groupEntries.size());
        for (GroupEntry next : this.groupEntries) {
            if (getVersion() == 1 && this.defaultLength == 0) {
                IsoTypeWriter.writeUInt32(byteBuffer, (long) next.get().limit());
            }
            byteBuffer.put(next.get());
        }
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        long j = (getVersion() == 1 ? 12 : 8) + 4;
        Iterator<GroupEntry> it = this.groupEntries.iterator();
        while (true) {
            long j2 = j;
            if (!it.hasNext()) {
                return j2;
            }
            GroupEntry next = it.next();
            if (getVersion() == 1 && this.defaultLength == 0) {
                j2 += 4;
            }
            j = ((long) next.size()) + j2;
        }
    }

    public int getDefaultLength() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.defaultLength;
    }

    public List<GroupEntry> getGroupEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.groupEntries;
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        int i = this.defaultLength;
        int i2 = 0;
        if (this.groupEntries != null) {
            i2 = this.groupEntries.hashCode();
        }
        return i2 + ((i + 0) * 31);
    }

    public void setDefaultLength(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.defaultLength = i;
    }

    public void setGroupEntries(List<GroupEntry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, list));
        this.groupEntries = list;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        StringBuilder sb = new StringBuilder("SampleGroupDescriptionBox{groupingType='");
        sb.append(this.groupEntries.size() > 0 ? this.groupEntries.get(0).getType() : "????");
        sb.append('\'');
        sb.append(", defaultLength=");
        sb.append(this.defaultLength);
        sb.append(", groupEntries=");
        sb.append(this.groupEntries);
        sb.append('}');
        return sb.toString();
    }
}
