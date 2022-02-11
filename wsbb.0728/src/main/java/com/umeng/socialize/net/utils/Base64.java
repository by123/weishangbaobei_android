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

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
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

    public static String encodeBase64String(byte[] bArr) {
        return AesHelper.newStringUtf8(encodeBase64(bArr, false));
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
                byte b2 = bArr[i];
                if (b2 == 61) {
                    this.mEof = true;
                    break;
                }
                if (b2 >= 0 && b2 < DECODE_TABLE.length && (b = DECODE_TABLE[b2]) >= 0) {
                    this.mModulus = (this.mModulus + 1) % 4;
                    this.mBitWorkArea = b + (this.mBitWorkArea << 6);
                    if (this.mModulus == 0) {
                        byte[] bArr2 = this.mBuffer;
                        int i4 = this.mPos;
                        this.mPos = i4 + 1;
                        bArr2[i4] = (byte) ((this.mBitWorkArea >> 16) & 255);
                        byte[] bArr3 = this.mBuffer;
                        int i5 = this.mPos;
                        this.mPos = i5 + 1;
                        bArr3[i5] = (byte) ((this.mBitWorkArea >> 8) & 255);
                        byte[] bArr4 = this.mBuffer;
                        int i6 = this.mPos;
                        this.mPos = i6 + 1;
                        bArr4[i6] = (byte) (this.mBitWorkArea & 255);
                    }
                }
                i3++;
                i++;
            }
            if (this.mEof && this.mModulus != 0) {
                ensureBufferSize(this.mDecodeSize);
                switch (this.mModulus) {
                    case 2:
                        this.mBitWorkArea >>= 4;
                        byte[] bArr5 = this.mBuffer;
                        int i7 = this.mPos;
                        this.mPos = i7 + 1;
                        bArr5[i7] = (byte) (this.mBitWorkArea & 255);
                        return;
                    case 3:
                        this.mBitWorkArea >>= 2;
                        byte[] bArr6 = this.mBuffer;
                        int i8 = this.mPos;
                        this.mPos = i8 + 1;
                        bArr6[i8] = (byte) ((this.mBitWorkArea >> 8) & 255);
                        byte[] bArr7 = this.mBuffer;
                        int i9 = this.mPos;
                        this.mPos = i9 + 1;
                        bArr7[i9] = (byte) (this.mBitWorkArea & 255);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v35, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    public void encode(byte[] bArr, int i, int i2) {
        if (!this.mEof) {
            if (i2 < 0) {
                this.mEof = true;
                if (this.mModulus != 0 || this.mLineLength != 0) {
                    ensureBufferSize(this.mEncodeSize);
                    int i3 = this.mPos;
                    switch (this.mModulus) {
                        case 1:
                            byte[] bArr2 = this.mBuffer;
                            int i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea >> 2) & 63];
                            byte[] bArr3 = this.mBuffer;
                            int i5 = this.mPos;
                            this.mPos = i5 + 1;
                            bArr3[i5] = this.mEncodeTable[(this.mBitWorkArea << 4) & 63];
                            if (this.mEncodeTable == STANDARD_ENCODE_TABLE) {
                                byte[] bArr4 = this.mBuffer;
                                int i6 = this.mPos;
                                this.mPos = i6 + 1;
                                bArr4[i6] = 61;
                                byte[] bArr5 = this.mBuffer;
                                int i7 = this.mPos;
                                this.mPos = i7 + 1;
                                bArr5[i7] = 61;
                                break;
                            }
                            break;
                        case 2:
                            byte[] bArr6 = this.mBuffer;
                            int i8 = this.mPos;
                            this.mPos = i8 + 1;
                            bArr6[i8] = this.mEncodeTable[(this.mBitWorkArea >> 10) & 63];
                            byte[] bArr7 = this.mBuffer;
                            int i9 = this.mPos;
                            this.mPos = i9 + 1;
                            bArr7[i9] = this.mEncodeTable[(this.mBitWorkArea >> 4) & 63];
                            byte[] bArr8 = this.mBuffer;
                            int i10 = this.mPos;
                            this.mPos = i10 + 1;
                            bArr8[i10] = this.mEncodeTable[(this.mBitWorkArea << 2) & 63];
                            if (this.mEncodeTable == STANDARD_ENCODE_TABLE) {
                                byte[] bArr9 = this.mBuffer;
                                int i11 = this.mPos;
                                this.mPos = i11 + 1;
                                bArr9[i11] = 61;
                                break;
                            }
                            break;
                    }
                    this.mCurrentLinePos = (this.mPos - i3) + this.mCurrentLinePos;
                    if (this.mLineLength > 0 && this.mCurrentLinePos > 0) {
                        System.arraycopy(this.mLineSeparator, 0, this.mBuffer, this.mPos, this.mLineSeparator.length);
                        this.mPos += this.mLineSeparator.length;
                        return;
                    }
                    return;
                }
                return;
            }
            for (int i12 = 0; i12 < i2; i12++) {
                ensureBufferSize(this.mEncodeSize);
                this.mModulus = (this.mModulus + 1) % 3;
                byte b = bArr[i];
                int i13 = b;
                if (b < 0) {
                    i13 = b + 256;
                }
                this.mBitWorkArea = i13 + (this.mBitWorkArea << 8);
                if (this.mModulus == 0) {
                    byte[] bArr10 = this.mBuffer;
                    int i14 = this.mPos;
                    this.mPos = i14 + 1;
                    bArr10[i14] = this.mEncodeTable[(this.mBitWorkArea >> 18) & 63];
                    byte[] bArr11 = this.mBuffer;
                    int i15 = this.mPos;
                    this.mPos = i15 + 1;
                    bArr11[i15] = this.mEncodeTable[(this.mBitWorkArea >> 12) & 63];
                    byte[] bArr12 = this.mBuffer;
                    int i16 = this.mPos;
                    this.mPos = i16 + 1;
                    bArr12[i16] = this.mEncodeTable[(this.mBitWorkArea >> 6) & 63];
                    byte[] bArr13 = this.mBuffer;
                    int i17 = this.mPos;
                    this.mPos = i17 + 1;
                    bArr13[i17] = this.mEncodeTable[this.mBitWorkArea & 63];
                    this.mCurrentLinePos += 4;
                    if (this.mLineLength > 0 && this.mLineLength <= this.mCurrentLinePos) {
                        System.arraycopy(this.mLineSeparator, 0, this.mBuffer, this.mPos, this.mLineSeparator.length);
                        this.mPos += this.mLineSeparator.length;
                        this.mCurrentLinePos = 0;
                    }
                }
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isInAlphabet(byte b) {
        return b >= 0 && b < this.mDecodeTable.length && this.mDecodeTable[b] != -1;
    }
}
