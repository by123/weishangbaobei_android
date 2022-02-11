package com.tencent.wxop.stat.common;

import android.util.Base64;

public class f {
    static byte[] a() {
        return Base64.decode("MDNhOTc2NTExZTJjYmUzYTdmMjY4MDhmYjdhZjNjMDU=", 0);
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, a());
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v5, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    static byte[] a(byte[] bArr, byte[] r9) {
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int length = r9.length;
        if (length <= 0 || length > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        }
        for (int i = 0; i < 256; i++) {
            iArr[i] = i;
            iArr2[i] = r9[i % length];
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = (i2 + iArr[i3] + iArr2[i3]) & 255;
            int i4 = iArr[i3];
            iArr[i3] = iArr[i2];
            iArr[i2] = i4;
        }
        byte[] bArr2 = new byte[bArr.length];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < bArr.length) {
            i5 = (i5 + 1) & 255;
            int i8 = (iArr[i5] + i6) & 255;
            int i9 = iArr[i5];
            iArr[i5] = iArr[i8];
            iArr[i8] = i9;
            bArr2[i7] = (byte) (iArr[(iArr[i5] + iArr[i8]) & 255] ^ bArr[i7]);
            i7++;
            i6 = i8;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr) {
        return b(bArr, a());
    }

    static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2);
    }
}
