package com.googlecode.mp4parser.boxes.dece;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class ContentInformationBox extends AbstractFullBox {
    public static final String TYPE = "cinf";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    Map<String, String> brandEntries = new LinkedHashMap();
    String codecs;
    Map<String, String> idEntries = new LinkedHashMap();
    String languages;
    String mimeSubtypeName;
    String profileLevelIdc;
    String protection;

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("ContentInformationBox.java", ContentInformationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMimeSubtypeName", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 144);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMimeSubtypeName", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "mimeSubtypeName", "", "void"), 148);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBrandEntries", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 184);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBrandEntries", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "brandEntries", "", "void"), PictureConfig.CHOOSE_REQUEST);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getIdEntries", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 192);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setIdEntries", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "idEntries", "", "void"), 196);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProfileLevelIdc", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 152);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProfileLevelIdc", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "profileLevelIdc", "", "void"), 156);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCodecs", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 160);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCodecs", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "codecs", "", "void"), 164);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProtection", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 168);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProtection", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "protection", "", "void"), 172);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguages", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 176);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguages", "com.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "languages", "", "void"), SubsamplingScaleImageView.ORIENTATION_180);
    }

    public ContentInformationBox() {
        super(TYPE);
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        long utf8StringLengthInBytes = ((long) (Utf8.utf8StringLengthInBytes(this.mimeSubtypeName) + 1)) + 4 + ((long) (Utf8.utf8StringLengthInBytes(this.profileLevelIdc) + 1)) + ((long) (Utf8.utf8StringLengthInBytes(this.codecs) + 1)) + ((long) (Utf8.utf8StringLengthInBytes(this.protection) + 1)) + ((long) (Utf8.utf8StringLengthInBytes(this.languages) + 1)) + 1;
        for (Map.Entry next : this.brandEntries.entrySet()) {
            utf8StringLengthInBytes = utf8StringLengthInBytes + ((long) (Utf8.utf8StringLengthInBytes((String) next.getKey()) + 1)) + ((long) (Utf8.utf8StringLengthInBytes((String) next.getValue()) + 1));
        }
        long j = utf8StringLengthInBytes + 1;
        for (Map.Entry next2 : this.idEntries.entrySet()) {
            j = j + ((long) (Utf8.utf8StringLengthInBytes((String) next2.getKey()) + 1)) + ((long) (Utf8.utf8StringLengthInBytes((String) next2.getValue()) + 1));
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.mimeSubtypeName);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.profileLevelIdc);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.codecs);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.protection);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.languages);
        IsoTypeWriter.writeUInt8(byteBuffer, this.brandEntries.size());
        for (Map.Entry next : this.brandEntries.entrySet()) {
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, (String) next.getKey());
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, (String) next.getValue());
        }
        IsoTypeWriter.writeUInt8(byteBuffer, this.idEntries.size());
        for (Map.Entry next2 : this.idEntries.entrySet()) {
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, (String) next2.getKey());
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, (String) next2.getValue());
        }
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.mimeSubtypeName = IsoTypeReader.readString(byteBuffer);
        this.profileLevelIdc = IsoTypeReader.readString(byteBuffer);
        this.codecs = IsoTypeReader.readString(byteBuffer);
        this.protection = IsoTypeReader.readString(byteBuffer);
        this.languages = IsoTypeReader.readString(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        while (true) {
            int i = readUInt8 - 1;
            if (readUInt8 <= 0) {
                break;
            }
            this.brandEntries.put(IsoTypeReader.readString(byteBuffer), IsoTypeReader.readString(byteBuffer));
            readUInt8 = i;
        }
        int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
        while (true) {
            int i2 = readUInt82 - 1;
            if (readUInt82 > 0) {
                this.idEntries.put(IsoTypeReader.readString(byteBuffer), IsoTypeReader.readString(byteBuffer));
                readUInt82 = i2;
            } else {
                return;
            }
        }
    }

    public static class BrandEntry {
        String iso_brand;
        String version;

        public BrandEntry(String str, String str2) {
            this.iso_brand = str;
            this.version = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BrandEntry brandEntry = (BrandEntry) obj;
            if (this.iso_brand == null ? brandEntry.iso_brand == null : this.iso_brand.equals(brandEntry.iso_brand)) {
                return this.version == null ? brandEntry.version == null : this.version.equals(brandEntry.version);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (this.iso_brand != null ? this.iso_brand.hashCode() : 0) * 31;
            if (this.version != null) {
                i = this.version.hashCode();
            }
            return hashCode + i;
        }
    }

    public String getMimeSubtypeName() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.mimeSubtypeName;
    }

    public void setMimeSubtypeName(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.mimeSubtypeName = str;
    }

    public String getProfileLevelIdc() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.profileLevelIdc;
    }

    public void setProfileLevelIdc(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.profileLevelIdc = str;
    }

    public String getCodecs() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.codecs;
    }

    public void setCodecs(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, str));
        this.codecs = str;
    }

    public String getProtection() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.protection;
    }

    public void setProtection(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, str));
        this.protection = str;
    }

    public String getLanguages() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.languages;
    }

    public void setLanguages(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, str));
        this.languages = str;
    }

    public Map<String, String> getBrandEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.brandEntries;
    }

    public void setBrandEntries(Map<String, String> map) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, map));
        this.brandEntries = map;
    }

    public Map<String, String> getIdEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.idEntries;
    }

    public void setIdEntries(Map<String, String> map) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, map));
        this.idEntries = map;
    }
}
