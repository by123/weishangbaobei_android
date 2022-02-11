package com.googlecode.mp4parser.boxes.mp4.samplegrouping;

import com.coremedia.iso.Hex;
import java.nio.ByteBuffer;

public class UnknownEntry extends GroupEntry {
    private ByteBuffer content;
    private String type;

    public UnknownEntry(String str) {
        this.type = str;
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
        UnknownEntry unknownEntry = (UnknownEntry) obj;
        return this.content != null ? this.content.equals(unknownEntry.content) : unknownEntry.content == null;
    }

    public ByteBuffer get() {
        return this.content.duplicate();
    }

    public ByteBuffer getContent() {
        return this.content;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        if (this.content != null) {
            return this.content.hashCode();
        }
        return 0;
    }

    public void parse(ByteBuffer byteBuffer) {
        this.content = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public void setContent(ByteBuffer byteBuffer) {
        this.content = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public String toString() {
        ByteBuffer duplicate = this.content.duplicate();
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        return "UnknownEntry{content=" + Hex.encodeHex(bArr) + '}';
    }
}
