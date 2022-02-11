package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

public class BitWriterBuffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBuffer buffer;
    int initialPos;
    int position = 0;

    public BitWriterBuffer(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
        this.initialPos = byteBuffer.position();
    }

    public void writeBits(int i, int i2) {
        int i3 = 8 - (this.position % 8);
        if (i2 <= i3) {
            int i4 = this.buffer.get(this.initialPos + (this.position / 8));
            if (i4 < 0) {
                i4 += 256;
            }
            int i5 = i4 + (i << (i3 - i2));
            ByteBuffer byteBuffer = this.buffer;
            int i6 = this.initialPos;
            int i7 = this.position / 8;
            if (i5 > 127) {
                i5 -= 256;
            }
            byteBuffer.put(i6 + i7, (byte) i5);
            this.position += i2;
        } else {
            int i8 = i2 - i3;
            writeBits(i >> i8, i3);
            writeBits(((1 << i8) - 1) & i, i8);
        }
        this.buffer.position((this.position % 8 > 0 ? 1 : 0) + this.initialPos + (this.position / 8));
    }
}
