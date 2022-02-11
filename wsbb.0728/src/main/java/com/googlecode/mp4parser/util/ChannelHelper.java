package com.googlecode.mp4parser.util;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class ChannelHelper {
    private static ByteBuffer empty = ByteBuffer.allocate(0).asReadOnlyBuffer();

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f  */
    public static int readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i) throws IOException {
        int read;
        int i2 = 0;
        do {
            read = readableByteChannel.read(byteBuffer);
            if (-1 == read || (i2 = i2 + read) == i) {
                if (read == -1) {
                    return i2;
                }
                throw new EOFException("End of file. No more boxes.");
            }
            read = readableByteChannel.read(byteBuffer);
            break;
        } while ((i2 = i2 + read) == i);
        if (read == -1) {
        }
    }

    public static void readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer) throws IOException {
        readFully(readableByteChannel, byteBuffer, byteBuffer.remaining());
    }
}
