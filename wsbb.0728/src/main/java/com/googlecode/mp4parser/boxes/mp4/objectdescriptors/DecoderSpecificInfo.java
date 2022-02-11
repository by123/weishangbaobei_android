package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.Hex;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

@Descriptor(tags = {5})
public class DecoderSpecificInfo extends BaseDescriptor {
    byte[] bytes;

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
        return Arrays.equals(this.bytes, ((DecoderSpecificInfo) obj).bytes);
    }

    public int hashCode() {
        if (this.bytes != null) {
            return Arrays.hashCode(this.bytes);
        }
        return 0;
    }

    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        if (this.sizeOfInstance > 0) {
            this.bytes = new byte[this.sizeOfInstance];
            byteBuffer.get(this.bytes);
        }
    }

    public ByteBuffer serialize() {
        return ByteBuffer.wrap(this.bytes);
    }

    public int serializedSize() {
        return this.bytes.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderSpecificInfo");
        sb.append("{bytes=");
        sb.append(this.bytes == null ? "null" : Hex.encodeHex(this.bytes));
        sb.append('}');
        return sb.toString();
    }
}
