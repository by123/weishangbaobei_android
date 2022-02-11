package com.mp4parser.iso23001.part7;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.UUIDConverter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class ProtectionSystemSpecificHeaderBox extends AbstractFullBox {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static byte[] OMA2_SYSTEM_ID = UUIDConverter.convert(UUID.fromString("A2B55680-6F43-11E0-9A3F-0002A5D5C51B"));
    public static byte[] PLAYREADY_SYSTEM_ID = UUIDConverter.convert(UUID.fromString("9A04F079-9840-4286-AB92-E65BE0885F95"));
    public static final String TYPE = "pssh";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    byte[] content;
    List<UUID> keyIds = new ArrayList();
    byte[] systemId;

    static {
        ajc$preClinit();
    }

    public ProtectionSystemSpecificHeaderBox() {
        super(TYPE);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("ProtectionSystemSpecificHeaderBox.java", ProtectionSystemSpecificHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getKeyIds", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "java.util.List"), 43);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setKeyIds", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "java.util.List", "keyIds", "", "void"), 47);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSystemId", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 54);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSystemId", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "systemId", "", "void"), 58);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContent", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "", "", "", "[B"), 63);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setContent", "com.mp4parser.iso23001.part7.ProtectionSystemSpecificHeaderBox", "[B", "content", "", "void"), 67);
    }

    /* access modifiers changed from: protected */
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.systemId = new byte[16];
        byteBuffer.get(this.systemId);
        if (getVersion() > 0) {
            for (int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer)); l2i > 0; l2i--) {
                byte[] bArr = new byte[16];
                byteBuffer.get(bArr);
                this.keyIds.add(UUIDConverter.convert(bArr));
            }
        }
        IsoTypeReader.readUInt32(byteBuffer);
        this.content = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.content);
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(this.systemId, 0, 16);
        if (getVersion() > 0) {
            IsoTypeWriter.writeUInt32(byteBuffer, (long) this.keyIds.size());
            for (UUID convert : this.keyIds) {
                byteBuffer.put(UUIDConverter.convert(convert));
            }
        }
        IsoTypeWriter.writeUInt32(byteBuffer, (long) this.content.length);
        byteBuffer.put(this.content);
    }

    public byte[] getContent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.content;
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        long length = (long) (this.content.length + 24);
        return getVersion() > 0 ? length + 4 + ((long) (this.keyIds.size() * 16)) : length;
    }

    public List<UUID> getKeyIds() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.keyIds;
    }

    public byte[] getSystemId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.systemId;
    }

    public void setContent(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, bArr));
        this.content = bArr;
    }

    public void setKeyIds(List<UUID> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, list));
        this.keyIds = list;
    }

    public void setSystemId(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, bArr));
        this.systemId = bArr;
    }
}
