package com.googlecode.mp4parser.boxes.mp4;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ObjectDescriptorFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.reflect.Factory;

public class AbstractDescriptorBox extends AbstractFullBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static Logger log = Logger.getLogger(AbstractDescriptorBox.class.getName());
    protected ByteBuffer data;
    protected BaseDescriptor descriptor;

    static {
        ajc$preClinit();
    }

    public AbstractDescriptorBox(String str) {
        super(str);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("AbstractDescriptorBox.java", AbstractDescriptorBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getData", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "java.nio.ByteBuffer"), 42);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDescriptor", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor"), 58);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDescriptorAsString", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "java.lang.String"), 62);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDescriptor", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor", "descriptor", "", "void"), 66);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setData", "com.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "java.nio.ByteBuffer", "data", "", "void"), 70);
    }

    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.data = byteBuffer.slice();
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
        try {
            this.data.rewind();
            this.descriptor = ObjectDescriptorFactory.createFrom(-1, this.data);
        } catch (IOException e) {
            log.log(Level.WARNING, "Error parsing ObjectDescriptor", e);
        } catch (IndexOutOfBoundsException e2) {
            log.log(Level.WARNING, "Error parsing ObjectDescriptor", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        this.data.rewind();
        byteBuffer.put(this.data);
    }

    /* access modifiers changed from: protected */
    public long getContentSize() {
        return (long) (this.data.limit() + 4);
    }

    public ByteBuffer getData() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.data;
    }

    public BaseDescriptor getDescriptor() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.descriptor;
    }

    public String getDescriptorAsString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.descriptor.toString();
    }

    public void setData(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, byteBuffer));
        this.data = byteBuffer;
    }

    public void setDescriptor(BaseDescriptor baseDescriptor) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, baseDescriptor));
        this.descriptor = baseDescriptor;
    }
}
