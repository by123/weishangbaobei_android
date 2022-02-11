package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.io.IOException;
import java.nio.ByteBuffer;

@Descriptor(tags = {6})
public class SLConfigDescriptor extends BaseDescriptor {
    int predefined;

    public int serializedSize() {
        return 3;
    }

    public int getPredefined() {
        return this.predefined;
    }

    public void setPredefined(int i) {
        this.predefined = i;
    }

    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.predefined = IsoTypeReader.readUInt8(byteBuffer);
    }

    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(3);
        IsoTypeWriter.writeUInt8(allocate, 6);
        IsoTypeWriter.writeUInt8(allocate, 1);
        IsoTypeWriter.writeUInt8(allocate, this.predefined);
        return allocate;
    }

    public String toString() {
        return "SLConfigDescriptor" + "{predefined=" + this.predefined + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.predefined == ((SLConfigDescriptor) obj).predefined;
    }

    public int hashCode() {
        return this.predefined;
    }
}
