package com.umeng.socialize.net.utils;

import com.umeng.commonsdk.proguard.ap;

public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    static final byte[] CHUNK_SEPARATOR = {ap.k, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, ap.k, ap.l, ap.m, ap.n, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private static final int MASK_6BITS = 63;
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private int mBitWorkArea;
    private final int mDecodeSize;
    private final byte[] mDecodeTable;
    private final int mEncodeSize;
    private final byte[] mEncodeTable;
    private final byte[] mLineSeparator;

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.mDecodeTable = DECODE_TABLE;
        if (bArr == null) {
            this.mEncodeSize = 4;
            this.mLineSeparator = null;
        } else if (containsAlphabetOrPad(bArr)) {
            String newStringUtf8 = AesHelper.newStringUtf8(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + newStringUtf8 + "]");
        } else if (i > 0) {
            this.mEncodeSize = bArr.length + 4;
            this.mLineSeparator = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mLineSeparator, 0, bArr.length);
        } else {
            this.mEncodeSize = 4;
            this.mLineSeparator = null;
        }
        this.mDecodeSize = this.mEncodeSize - 1;
        this.mEncodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v37, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(byte[] r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r0 = r7.mEof
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r10 >= 0) goto L_0x00d9
            r7.mEof = r1
            int r8 = r7.mModulus
            if (r8 != 0) goto L_0x0014
            int r8 = r7.mLineLength
            if (r8 != 0) goto L_0x0014
            return
        L_0x0014:
            int r8 = r7.mEncodeSize
            r7.ensureBufferSize(r8)
            int r8 = r7.mPos
            int r9 = r7.mModulus
            r10 = 61
            switch(r9) {
                case 1: goto L_0x0071;
                case 2: goto L_0x0024;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x00b3
        L_0x0024:
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            byte[] r2 = r7.mEncodeTable
            int r3 = r7.mBitWorkArea
            int r3 = r3 >> 10
            r3 = r3 & 63
            byte r2 = r2[r3]
            r9[r1] = r2
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            byte[] r2 = r7.mEncodeTable
            int r3 = r7.mBitWorkArea
            int r3 = r3 >> 4
            r3 = r3 & 63
            byte r2 = r2[r3]
            r9[r1] = r2
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            byte[] r2 = r7.mEncodeTable
            int r3 = r7.mBitWorkArea
            int r3 = r3 << 2
            r3 = r3 & 63
            byte r2 = r2[r3]
            r9[r1] = r2
            byte[] r9 = r7.mEncodeTable
            byte[] r1 = STANDARD_ENCODE_TABLE
            if (r9 != r1) goto L_0x00b3
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            r9[r1] = r10
            goto L_0x00b3
        L_0x0071:
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            byte[] r2 = r7.mEncodeTable
            int r3 = r7.mBitWorkArea
            int r3 = r3 >> 2
            r3 = r3 & 63
            byte r2 = r2[r3]
            r9[r1] = r2
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            byte[] r2 = r7.mEncodeTable
            int r3 = r7.mBitWorkArea
            int r3 = r3 << 4
            r3 = r3 & 63
            byte r2 = r2[r3]
            r9[r1] = r2
            byte[] r9 = r7.mEncodeTable
            byte[] r1 = STANDARD_ENCODE_TABLE
            if (r9 != r1) goto L_0x00b3
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            r9[r1] = r10
            byte[] r9 = r7.mBuffer
            int r1 = r7.mPos
            int r2 = r1 + 1
            r7.mPos = r2
            r9[r1] = r10
        L_0x00b3:
            int r9 = r7.mCurrentLinePos
            int r10 = r7.mPos
            int r10 = r10 - r8
            int r9 = r9 + r10
            r7.mCurrentLinePos = r9
            int r8 = r7.mLineLength
            if (r8 <= 0) goto L_0x0175
            int r8 = r7.mCurrentLinePos
            if (r8 <= 0) goto L_0x0175
            byte[] r8 = r7.mLineSeparator
            byte[] r9 = r7.mBuffer
            int r10 = r7.mPos
            byte[] r1 = r7.mLineSeparator
            int r1 = r1.length
            java.lang.System.arraycopy(r8, r0, r9, r10, r1)
            int r8 = r7.mPos
            byte[] r9 = r7.mLineSeparator
            int r9 = r9.length
            int r8 = r8 + r9
            r7.mPos = r8
            goto L_0x0175
        L_0x00d9:
            r2 = r9
            r9 = 0
        L_0x00db:
            if (r9 >= r10) goto L_0x0175
            int r3 = r7.mEncodeSize
            r7.ensureBufferSize(r3)
            int r3 = r7.mModulus
            int r3 = r3 + r1
            int r3 = r3 % 3
            r7.mModulus = r3
            int r3 = r2 + 1
            byte r2 = r8[r2]
            if (r2 >= 0) goto L_0x00f1
            int r2 = r2 + 256
        L_0x00f1:
            int r4 = r7.mBitWorkArea
            int r4 = r4 << 8
            int r4 = r4 + r2
            r7.mBitWorkArea = r4
            int r2 = r7.mModulus
            if (r2 != 0) goto L_0x0170
            byte[] r2 = r7.mBuffer
            int r4 = r7.mPos
            int r5 = r4 + 1
            r7.mPos = r5
            byte[] r5 = r7.mEncodeTable
            int r6 = r7.mBitWorkArea
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r2[r4] = r5
            byte[] r2 = r7.mBuffer
            int r4 = r7.mPos
            int r5 = r4 + 1
            r7.mPos = r5
            byte[] r5 = r7.mEncodeTable
            int r6 = r7.mBitWorkArea
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r2[r4] = r5
            byte[] r2 = r7.mBuffer
            int r4 = r7.mPos
            int r5 = r4 + 1
            r7.mPos = r5
            byte[] r5 = r7.mEncodeTable
            int r6 = r7.mBitWorkArea
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r2[r4] = r5
            byte[] r2 = r7.mBuffer
            int r4 = r7.mPos
            int r5 = r4 + 1
            r7.mPos = r5
            byte[] r5 = r7.mEncodeTable
            int r6 = r7.mBitWorkArea
            r6 = r6 & 63
            byte r5 = r5[r6]
            r2[r4] = r5
            int r2 = r7.mCurrentLinePos
            int r2 = r2 + 4
            r7.mCurrentLinePos = r2
            int r2 = r7.mLineLength
            if (r2 <= 0) goto L_0x0170
            int r2 = r7.mLineLength
            int r4 = r7.mCurrentLinePos
            if (r2 > r4) goto L_0x0170
            byte[] r2 = r7.mLineSeparator
            byte[] r4 = r7.mBuffer
            int r5 = r7.mPos
            byte[] r6 = r7.mLineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r2, r0, r4, r5, r6)
            int r2 = r7.mPos
            byte[] r4 = r7.mLineSeparator
            int r4 = r4.length
            int r2 = r2 + r4
            r7.mPos = r2
            r7.mCurrentLinePos = r0
        L_0x0170:
            int r9 = r9 + 1
            r2 = r3
            goto L_0x00db
        L_0x0175:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.Base64.encode(byte[], int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void decode(byte[] bArr, int i, int i2) {
        byte b;
        if (!this.mEof) {
            if (i2 < 0) {
                this.mEof = true;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                ensureBufferSize(this.mDecodeSize);
                int i4 = i + 1;
                byte b2 = bArr[i];
                if (b2 == 61) {
                    this.mEof = true;
                    break;
                }
                if (b2 >= 0 && b2 < DECODE_TABLE.length && (b = DECODE_TABLE[b2]) >= 0) {
                    this.mModulus = (this.mModulus + 1) % 4;
                    this.mBitWorkArea = (this.mBitWorkArea << 6) + b;
                    if (this.mModulus == 0) {
                        byte[] bArr2 = this.mBuffer;
                        int i5 = this.mPos;
                        this.mPos = i5 + 1;
                        bArr2[i5] = (byte) ((this.mBitWorkArea >> 16) & 255);
                        byte[] bArr3 = this.mBuffer;
                        int i6 = this.mPos;
                        this.mPos = i6 + 1;
                        bArr3[i6] = (byte) ((this.mBitWorkArea >> 8) & 255);
                        byte[] bArr4 = this.mBuffer;
                        int i7 = this.mPos;
                        this.mPos = i7 + 1;
                        bArr4[i7] = (byte) (this.mBitWorkArea & 255);
                    }
                }
                i3++;
                i = i4;
            }
            if (this.mEof && this.mModulus != 0) {
                ensureBufferSize(this.mDecodeSize);
                switch (this.mModulus) {
                    case 2:
                        this.mBitWorkArea >>= 4;
                        byte[] bArr5 = this.mBuffer;
                        int i8 = this.mPos;
                        this.mPos = i8 + 1;
                        bArr5[i8] = (byte) (this.mBitWorkArea & 255);
                        return;
                    case 3:
                        this.mBitWorkArea >>= 2;
                        byte[] bArr6 = this.mBuffer;
                        int i9 = this.mPos;
                        this.mPos = i9 + 1;
                        bArr6[i9] = (byte) ((this.mBitWorkArea >> 8) & 255);
                        byte[] bArr7 = this.mBuffer;
                        int i10 = this.mPos;
                        this.mPos = i10 + 1;
                        bArr7[i10] = (byte) (this.mBitWorkArea & 255);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static String encodeBase64String(byte[] bArr) {
        return AesHelper.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= ((long) i)) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    /* access modifiers changed from: protected */
    public boolean isInAlphabet(byte b) {
        return b >= 0 && b < this.mDecodeTable.length && this.mDecodeTable[b] != -1;
    }
}
