package com.umeng.socialize.net.utils;

public abstract class BaseNCodec {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD = 61;
    protected static final byte PAD_DEFAULT = 61;
    protected byte[] mBuffer;
    private final int mChunkSeparatorLength;
    protected int mCurrentLinePos;
    private final int mEncodedBlockSize;
    protected boolean mEof;
    protected final int mLineLength;
    protected int mModulus;
    protected int mPos;
    private int mReadPos;
    private final int mUnencodedBlockSize;

    protected BaseNCodec(int i, int i2, int i3, int i4) {
        this.mUnencodedBlockSize = i;
        this.mEncodedBlockSize = i2;
        this.mLineLength = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.mChunkSeparatorLength = i4;
    }

    protected static boolean isWhiteSpace(byte b) {
        if (!(b == 13 || b == 32)) {
            switch (b) {
                case 9:
                case 10:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void reset() {
        this.mBuffer = null;
        this.mPos = 0;
        this.mReadPos = 0;
        this.mCurrentLinePos = 0;
        this.mModulus = 0;
        this.mEof = false;
    }

    private void resizeBuffer() {
        if (this.mBuffer == null) {
            this.mBuffer = new byte[getDefaultBufferSize()];
            this.mPos = 0;
            this.mReadPos = 0;
            return;
        }
        byte[] bArr = new byte[(this.mBuffer.length * 2)];
        System.arraycopy(this.mBuffer, 0, bArr, 0, this.mBuffer.length);
        this.mBuffer = bArr;
    }

    /* access modifiers changed from: package-private */
    public int available() {
        if (this.mBuffer != null) {
            return this.mPos - this.mReadPos;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (61 == bArr[i] || isInAlphabet(bArr[i])) {
                return true;
            }
        }
        return false;
    }

    public Object decode(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new Exception("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    /* access modifiers changed from: package-private */
    public abstract void decode(byte[] bArr, int i, int i2);

    public byte[] decode(String str) {
        return decode(AesHelper.getBytesUtf8(str));
    }

    public byte[] decode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        decode(bArr, 0, bArr.length);
        decode(bArr, 0, -1);
        byte[] bArr2 = new byte[this.mPos];
        readResults(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public Object encode(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new Exception("Parameter supplied to Base-N encode is not a byte[]");
    }

    /* access modifiers changed from: package-private */
    public abstract void encode(byte[] bArr, int i, int i2);

    public byte[] encode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        encode(bArr, 0, bArr.length);
        encode(bArr, 0, -1);
        byte[] bArr2 = new byte[(this.mPos - this.mReadPos)];
        readResults(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public String encodeAsString(byte[] bArr) {
        return AesHelper.newStringUtf8(encode(bArr));
    }

    public String encodeToString(byte[] bArr) {
        return AesHelper.newStringUtf8(encode(bArr));
    }

    /* access modifiers changed from: protected */
    public void ensureBufferSize(int i) {
        if (this.mBuffer == null || this.mBuffer.length < this.mPos + i) {
            resizeBuffer();
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

    public long getEncodedLength(byte[] bArr) {
        long length = ((long) (((bArr.length + this.mUnencodedBlockSize) - 1) / this.mUnencodedBlockSize)) * ((long) this.mEncodedBlockSize);
        return this.mLineLength > 0 ? length + ((((((long) this.mLineLength) + length) - 1) / ((long) this.mLineLength)) * ((long) this.mChunkSeparatorLength)) : length;
    }

    /* access modifiers changed from: package-private */
    public boolean hasData() {
        return this.mBuffer != null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isInAlphabet(byte b);

    public boolean isInAlphabet(String str) {
        return isInAlphabet(AesHelper.getBytesUtf8(str), true);
    }

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isInAlphabet(bArr[i])) {
                if (!z) {
                    return false;
                }
                if (bArr[i] != 61 && !isWhiteSpace(bArr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int readResults(byte[] bArr, int i, int i2) {
        if (this.mBuffer == null) {
            return this.mEof ? -1 : 0;
        }
        int min = Math.min(available(), i2);
        System.arraycopy(this.mBuffer, this.mReadPos, bArr, i, min);
        this.mReadPos += min;
        if (this.mReadPos < this.mPos) {
            return min;
        }
        this.mBuffer = null;
        return min;
    }
}
