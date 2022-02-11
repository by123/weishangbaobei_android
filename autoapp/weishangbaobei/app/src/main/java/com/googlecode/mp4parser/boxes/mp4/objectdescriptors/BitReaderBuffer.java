package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

public class BitReaderBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    public BitReaderBuffer(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
        this.initialPos = byteBuffer.position();
    }

    public boolean readBool() {
        return readBits(1) == 1;
    }

    public int readBits(int i) {
        int i2;
        int i3 = this.buffer.get(this.initialPos + (this.position / 8));
        if (i3 < 0) {
            i3 += 256;
        }
        int i4 = 8 - (this.position % 8);
        if (i <= i4) {
            i2 = ((i3 << (this.position % 8)) & 255) >> ((this.position % 8) + (i4 - i));
            this.position += i;
        } else {
            int i5 = i - i4;
            i2 = (readBits(i4) << i5) + readBits(i5);
        }
        ByteBuffer byteBuffer = this.buffer;
        int i6 = this.initialPos;
        double d = (double) this.position;
        Double.isNaN(d);
        byteBuffer.position(i6 + ((int) Math.ceil(d / 8.0d)));
        return i2;
    }

    public int getPosition() {
        return this.position;
    }

    public int byteSync() {
        int i = 8 - (this.position % 8);
        if (i == 8) {
            i = 0;
        }
        readBits(i);
        return i;
    }

    public int remainingBits() {
        return (this.buffer.limit() * 8) - this.position;
    }
}
