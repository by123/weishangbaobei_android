package com.tencent.wxop.stat.common;

class j extends i {
    private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private int e;
    private int f;
    private final int[] g;

    public j(int i, byte[] bArr) {
        this.a = bArr;
        this.g = (i & 8) == 0 ? c : d;
        this.e = 0;
        this.f = 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bb, code lost:
        r2 = (r2 << 6) | r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f3 A[SYNTHETIC] */
    public boolean a(byte[] bArr, int i, int i2, boolean z) {
        if (this.e == 6) {
            return false;
        }
        int i3 = i2 + i;
        int i4 = this.e;
        int i5 = this.f;
        byte[] bArr2 = this.a;
        int[] iArr = this.g;
        int i6 = 0;
        int i7 = i;
        while (i7 < i3) {
            if (i4 == 0) {
                while (true) {
                    int i8 = i7 + 4;
                    if (i8 <= i3 && (i5 = (iArr[bArr[i7] & 255] << 18) | (iArr[bArr[i7 + 1] & 255] << 12) | (iArr[bArr[i7 + 2] & 255] << 6) | iArr[bArr[i7 + 3] & 255]) >= 0) {
                        bArr2[i6 + 2] = (byte) i5;
                        bArr2[i6 + 1] = (byte) (i5 >> 8);
                        bArr2[i6] = (byte) (i5 >> 16);
                        i6 += 3;
                        i7 = i8;
                    } else if (i7 >= i3) {
                        int i9 = i6;
                        if (z) {
                            this.e = i4;
                            this.f = i5;
                        } else {
                            switch (i4) {
                                case 1:
                                    this.e = 6;
                                    return false;
                                case 2:
                                    bArr2[i9] = (byte) (i5 >> 4);
                                    i9++;
                                    break;
                                case 3:
                                    int i10 = i9 + 1;
                                    bArr2[i9] = (byte) (i5 >> 10);
                                    i9 = i10 + 1;
                                    bArr2[i10] = (byte) (i5 >> 2);
                                    break;
                                case 4:
                                    this.e = 6;
                                    return false;
                            }
                            this.e = i4;
                        }
                        this.b = i9;
                        return true;
                    }
                }
                if (i7 >= i3) {
                }
            }
            int i11 = i7 + 1;
            int i12 = iArr[bArr[i7] & 255];
            switch (i4) {
                case 0:
                    if (i12 >= 0) {
                        i4++;
                        i5 = i12;
                    } else if (i12 != -1) {
                        this.e = 6;
                        return false;
                    }
                case 1:
                    if (i12 < 0) {
                        if (i12 != -1) {
                            this.e = 6;
                            return false;
                        }
                    }
                    break;
                case 2:
                    if (i12 < 0) {
                        if (i12 == -2) {
                            bArr2[i6] = (byte) (i5 >> 4);
                            i6++;
                            i4 = 4;
                            i7 = i11;
                            break;
                        } else if (i12 != -1) {
                            this.e = 6;
                            return false;
                        }
                    }
                    break;
                case 3:
                    if (i12 >= 0) {
                        i5 = (i5 << 6) | i12;
                        bArr2[i6 + 2] = (byte) i5;
                        bArr2[i6 + 1] = (byte) (i5 >> 8);
                        bArr2[i6] = (byte) (i5 >> 16);
                        i6 += 3;
                        i4 = 0;
                        i7 = i11;
                        break;
                    } else if (i12 == -2) {
                        bArr2[i6 + 1] = (byte) (i5 >> 2);
                        bArr2[i6] = (byte) (i5 >> 10);
                        i6 += 2;
                        i4 = 5;
                    } else if (i12 != -1) {
                        this.e = 6;
                        return false;
                    }
                case 4:
                    if (i12 != -2) {
                        if (i12 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i7 = i11;
                        break;
                    }
                case 5:
                    if (i12 != -1) {
                        this.e = 6;
                        return false;
                    }
                    i7 = i11;
                    break;
            }
        }
        int i92 = i6;
        if (z) {
        }
        this.b = i92;
        return true;
    }
}
