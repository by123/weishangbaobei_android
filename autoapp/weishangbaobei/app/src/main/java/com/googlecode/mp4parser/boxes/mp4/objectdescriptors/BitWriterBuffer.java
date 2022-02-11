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
        int i4 = 1;
        if (i2 <= i3) {
            int i5 = this.buffer.get(this.initialPos + (this.position / 8));
            if (i5 < 0) {
                i5 += 256;
            }
            int i6 = i5 + (i << (i3 - i2));
            ByteBuffer byteBuffer = this.buffer;
            int i7 = this.initialPos + (this.position / 8);
            if (i6 > 127) {
                i6 -= 256;
            }
            byteBuffer.put(i7, (byte) i6);
            this.position += i2;
        } else {
            int i8 = i2 - i3;
            writeBits(i >> i8, i3);
            writeBits(i & ((1 << i8) - 1), i8);
        }
        ByteBuffer byteBuffer2 = this.buffer;
        int i9 = this.initialPos + (this.position / 8);
        if (this.position % 8 <= 0) {
            i4 = 0;
        }
        byteBuffer2.position(i9 + i4);
    }
}
