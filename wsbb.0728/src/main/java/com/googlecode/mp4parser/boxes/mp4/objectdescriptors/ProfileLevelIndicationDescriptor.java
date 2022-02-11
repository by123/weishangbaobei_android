package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.io.IOException;
import java.nio.ByteBuffer;

@Descriptor(tags = {20})
public class ProfileLevelIndicationDescriptor extends BaseDescriptor {
    int profileLevelIndicationIndex;

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
        return this.profileLevelIndicationIndex == ((ProfileLevelIndicationDescriptor) obj).profileLevelIndicationIndex;
    }

    public int hashCode() {
        return this.profileLevelIndicationIndex;
    }

    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.profileLevelIndicationIndex = IsoTypeReader.readUInt8(byteBuffer);
    }

    public String toString() {
        return "ProfileLevelIndicationDescriptor" + "{profileLevelIndicationIndex=" + Integer.toHexString(this.profileLevelIndicationIndex) + '}';
    }
}
