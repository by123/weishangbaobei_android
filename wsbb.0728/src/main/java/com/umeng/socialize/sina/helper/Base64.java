package com.umeng.socialize.sina.helper;

public final class Base64 {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            codes[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            codes[i3] = (byte) ((i3 + 26) - 97);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            codes[i4] = (byte) ((i4 + 52) - 48);
        }
        codes[43] = 62;
        codes[47] = 63;
    }

    public static byte[] decode(byte[] bArr) {
        byte b;
        int length = ((bArr.length + 3) / 4) * 3;
        if (bArr.length > 0 && bArr[bArr.length - 1] == 61) {
            length--;
        }
        if (bArr.length > 1 && bArr[bArr.length - 2] == 61) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        int i = 0;
        byte b2 = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < bArr.length) {
            byte b3 = codes[bArr[i3] & 255];
            if (b3 >= 0) {
                i += 6;
                b = b3 | (b2 << 6);
                if (i >= 8) {
                    i -= 8;
                    bArr2[i2] = (byte) ((b >> i) & 255);
                    i2++;
                }
            } else {
                b = b2;
            }
            i3++;
            b2 = b;
        }
        if (i2 == bArr2.length) {
            return bArr2;
        }
        throw new RuntimeException("miscalculated data length!");
    }

    public static char[] encode(byte[] bArr) {
        boolean z;
        boolean z2;
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
                z2 = true;
            } else {
                z2 = false;
            }
            cArr[i2 + 3] = alphabet[z2 ? i5 & 63 : 64];
            int i7 = i5 >> 6;
            cArr[i2 + 2] = alphabet[z ? i7 & 63 : 64];
            int i8 = i7 >> 6;
            cArr[i2 + 1] = alphabet[i8 & 63];
            cArr[i2 + 0] = alphabet[(i8 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return cArr;
    }

    public static byte[] encodebyte(byte[] bArr) {
        boolean z;
        boolean z2;
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
                z2 = true;
            } else {
                z2 = false;
            }
            bArr2[i2 + 3] = (byte) alphabet[z2 ? i5 & 63 : 64];
            int i7 = i5 >> 6;
            bArr2[i2 + 2] = (byte) alphabet[z ? i7 & 63 : 64];
            int i8 = i7 >> 6;
            bArr2[i2 + 1] = (byte) alphabet[i8 & 63];
            bArr2[i2 + 0] = (byte) alphabet[(i8 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return bArr2;
    }
}
