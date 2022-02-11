package com.googlecode.mp4parser.h264.write;

import com.googlecode.mp4parser.h264.Debug;
import java.io.IOException;
import java.io.OutputStream;

public class BitstreamWriter {
    private int curBit;
    private int[] curByte = new int[8];
    private final OutputStream os;

    public BitstreamWriter(OutputStream outputStream) {
        this.os = outputStream;
    }

    private void writeCurByte() throws IOException {
        int i = this.curByte[0];
        int i2 = this.curByte[1];
        int i3 = this.curByte[2];
        int i4 = this.curByte[3];
        int i5 = this.curByte[4];
        int i6 = this.curByte[5];
        int i7 = this.curByte[6];
        this.os.write((i << 7) | (i2 << 6) | (i3 << 5) | (i4 << 4) | (i5 << 3) | (i6 << 2) | (i7 << 1) | this.curByte[7]);
    }

    public void flush() throws IOException {
        for (int i = this.curBit; i < 8; i++) {
            this.curByte[i] = 0;
        }
        this.curBit = 0;
        writeCurByte();
    }

    public void write1Bit(int i) throws IOException {
        Debug.print(i);
        if (this.curBit == 8) {
            this.curBit = 0;
            writeCurByte();
        }
        int[] iArr = this.curByte;
        int i2 = this.curBit;
        this.curBit = i2 + 1;
        iArr[i2] = i;
    }

    public void writeByte(int i) throws IOException {
        this.os.write(i);
    }

    public void writeNBit(long j, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            write1Bit(((int) (j >> ((i - i2) - 1))) & 1);
        }
    }

    public void writeRemainingZero() throws IOException {
        writeNBit(0, 8 - this.curBit);
    }
}
