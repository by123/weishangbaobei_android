package com.googlecode.mp4parser.boxes.mp4.samplegrouping;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class SampleToGroupBox extends AbstractFullBox {
    public static final String TYPE = "sbgp";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    List<Entry> entries = new LinkedList();
    private String groupingType;
    private String groupingTypeParameter;

    public static class Entry {
        private int groupDescriptionIndex;
        private long sampleCount;

        public Entry(long j, int i) {
            this.sampleCount = j;
            this.groupDescriptionIndex = i;
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
            Entry entry = (Entry) obj;
            if (this.groupDescriptionIndex != entry.groupDescriptionIndex) {
                return false;
            }
            return this.sampleCount == entry.sampleCount;
        }

        public int getGroupDescriptionIndex() {
            return this.groupDescriptionIndex;
        }

        public long getSampleCount() {
            return this.sampleCount;
        }

        public int hashCode() {
            return (((int) (this.sampleCount ^ (this.sampleCount >>> 32))) * 31) + this.groupDescriptionIndex;
        }

        public void setGroupDescriptionIndex(int i) {
            this.groupDescriptionIndex = i;
        }

        public void setSampleCount(long j) {
            this.sampleCount = j;
        }

        public String toString() {
            return "Entry{sampleCount=" + this.sampleCount + ", groupDescriptionIndex=" + this.groupDescriptionIndex + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public SampleToGroupBox() {
        super(TYPE);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("SampleToGroupBox.java", SampleToGroupBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGroupingType", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "", "", "", "java.lang.String"), 150);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGroupingType", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "java.lang.String", "groupingType", "", "void"), 154);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGroupingTypeParameter", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "", "", "", "java.lang.String"), 158);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGroupingTypeParameter", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "java.lang.String", "groupingTypeParameter", "", "void"), 162);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "", "", "", "java.util.List"), 166);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox", "java.util.List", "entries", "", "void"), 170);
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.groupingType = IsoTypeReader.read4cc(byteBuffer);
        if (getVersion() == 1) {
            this.groupingTypeParameter = IsoTypeReader.read4cc(byteBuffer);
        }
        for (long readUInt32 = IsoTypeReader.readUInt32(byteBuffer); readUInt32 > 0; readUInt32--) {
            this.entries.add(new Entry((long) CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer)), CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer))));
        }
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(this.groupingType.getBytes());
        if (getVersion() == 1) {
            byteBuffer.put(this.groupingTypeParameter.getBytes());
        }
        IsoTypeWriter.writeUInt32(byteBuffer, (long) this.entries.size());
        for (Entry next : this.entries) {
            IsoTypeWriter.writeUInt32(byteBuffer, next.getSampleCount());
            IsoTypeWriter.writeUInt32(byteBuffer, (long) next.getGroupDescriptionIndex());
        }
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        return (long) (getVersion() == 1 ? (this.entries.size() * 8) + 16 : (this.entries.size() * 8) + 12);
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.entries;
    }

    public String getGroupingType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.groupingType;
    }

    public String getGroupingTypeParameter() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.groupingTypeParameter;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, list));
        this.entries = list;
    }

    public void setGroupingType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.groupingType = str;
    }

    public void setGroupingTypeParameter(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.groupingTypeParameter = str;
    }
}
