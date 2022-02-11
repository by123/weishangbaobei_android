package com.tencent.wxop.stat.common;

import com.umeng.commonsdk.proguard.ap;

class k extends i {
    static final /* synthetic */ boolean g = (!h.class.desiredAssertionStatus());
    private static final byte[] h = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] i = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    int c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    private final byte[] j;
    private int k;
    private final byte[] l;

    public k(int i2, byte[] bArr) {
        boolean z = true;
        this.a = bArr;
        this.d = (i2 & 1) == 0;
        this.e = (i2 & 2) == 0;
        this.f = (i2 & 4) == 0 ? false : z;
        this.l = (i2 & 8) == 0 ? h : i;
        this.j = new byte[2];
        this.c = 0;
        this.k = this.e ? 19 : -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0044, code lost:
        r5 = r0 + 1;
        r9[r0] = 10;
        r6 = 19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        r7 = r2 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        if (r7 > r10) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r0 = (((r12[r2] & 255) << com.umeng.commonsdk.proguard.ap.n) | ((r12[r2 + 1] & 255) << 8)) | (r12[r2 + 2] & 255);
        r9[r5] = r8[(r0 >> 18) & 63];
        r9[r5 + 1] = r8[(r0 >> 12) & 63];
        r9[r5 + 2] = r8[(r0 >> 6) & 63];
        r9[r5 + 3] = r8[r0 & 63];
        r1 = r5 + 4;
        r0 = r6 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008f, code lost:
        if (r0 != 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0093, code lost:
        if (r11.f == false) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0095, code lost:
        r0 = r1 + 1;
        r9[r1] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009b, code lost:
        r1 = r0 + 1;
        r9[r0] = 10;
        r0 = 19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a3, code lost:
        r6 = r0;
        r2 = r7;
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c3, code lost:
        r11.c = 0;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e7, code lost:
        r0 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ea, code lost:
        r5 = 4;
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ee, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r1 = -1;
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f1, code lost:
        if (r15 == false) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f9, code lost:
        if ((r2 - r11.c) != (r10 - 1)) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00fd, code lost:
        if (r11.c <= 0) goto L_0x015b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ff, code lost:
        r0 = r11.j[0];
        r3 = 1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0106, code lost:
        r2 = (r0 & 255) << 4;
        r11.c -= r3;
        r3 = r5 + 1;
        r9[r5] = r8[(r2 >> 6) & 63];
        r0 = r3 + 1;
        r9[r3] = r8[r2 & 63];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0123, code lost:
        if (r11.d == false) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0125, code lost:
        r2 = r0 + 1;
        r9[r0] = 61;
        r0 = r2 + 1;
        r9[r2] = 61;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0133, code lost:
        if (r11.e == false) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0137, code lost:
        if (r11.f == false) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0139, code lost:
        r9[r0] = com.umeng.commonsdk.proguard.ap.k;
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0140, code lost:
        r5 = r0 + 1;
        r9[r0] = 10;
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0149, code lost:
        if (g != false) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x014d, code lost:
        if (r11.c != 0) goto L_0x01f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        if (r1 == -1) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0151, code lost:
        if (g != false) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0153, code lost:
        if (r2 != r10) goto L_0x01f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0155, code lost:
        r11.b = r5;
        r11.k = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x015a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x015b, code lost:
        r0 = r12[r2];
        r1 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0166, code lost:
        if ((r2 - r11.c) != (r10 - 2)) goto L_0x01d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016b, code lost:
        if (r11.c <= 1) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016d, code lost:
        r0 = r11.j[0];
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r9[0] = r8[(r1 >> 18) & 63];
        r9[1] = r8[(r1 >> 12) & 63];
        r9[2] = r8[(r1 >> 6) & 63];
        r9[3] = r8[r1 & 63];
        r0 = r6 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0175, code lost:
        if (r11.c <= 0) goto L_0x01cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0177, code lost:
        r4 = r1 + 1;
        r3 = r11.j[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x017e, code lost:
        r0 = ((r0 & 255) << 10) | ((r3 & 255) << 2);
        r11.c -= r4;
        r1 = r5 + 1;
        r9[r5] = r8[(r0 >> 12) & 63];
        r3 = r1 + 1;
        r9[r1] = r8[(r0 >> 6) & 63];
        r1 = r3 + 1;
        r9[r3] = r8[r0 & 63];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01aa, code lost:
        if (r11.d == false) goto L_0x0231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01ac, code lost:
        r0 = r1 + 1;
        r9[r1] = 61;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01b4, code lost:
        if (r11.e == false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b8, code lost:
        if (r11.f == false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0038, code lost:
        if (r0 != 0) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ba, code lost:
        r9[r0] = com.umeng.commonsdk.proguard.ap.k;
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01c0, code lost:
        r9[r0] = 10;
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01c6, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c8, code lost:
        r0 = r12[r2];
        r1 = 0;
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01cf, code lost:
        r3 = r12[r2];
        r2 = r2 + 1;
        r4 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01d7, code lost:
        if (r11.e == false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01d9, code lost:
        if (r5 <= 0) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dd, code lost:
        if (r6 == 19) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e1, code lost:
        if (r11.f == false) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01e3, code lost:
        r0 = r5 + 1;
        r9[r5] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01e9, code lost:
        r9[r0] = 10;
        r5 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01f6, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01fc, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01ff, code lost:
        if (r2 != (r10 - 1)) goto L_0x020f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003c, code lost:
        if (r11.f == false) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0201, code lost:
        r0 = r11.j;
        r1 = r11.c;
        r11.c = r1 + 1;
        r0[r1] = r12[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0211, code lost:
        if (r2 != (r10 - 2)) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0213, code lost:
        r0 = r11.j;
        r1 = r11.c;
        r11.c = r1 + 1;
        r0[r1] = r12[r2];
        r0 = r11.j;
        r1 = r11.c;
        r11.c = r1 + 1;
        r0[r1] = r12[r2 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x022f, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0231, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0233, code lost:
        r2 = r1;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0237, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        r0 = 5;
        r9[4] = com.umeng.commonsdk.proguard.ap.k;
     */
    public boolean a(byte[] bArr, int i2, int i3, boolean z) {
        int i4;
        byte b;
        byte[] bArr2 = this.l;
        byte[] bArr3 = this.a;
        int i5 = this.k;
        int i6 = i3 + i2;
        int i7 = 0;
        switch (this.c) {
            case 1:
                if (i2 + 2 <= i6) {
                    int i8 = i2 + 1;
                    i4 = i8 + 1;
                    b = ((this.j[0] & 255) << ap.n) | ((bArr[i2] & 255) << 8) | (bArr[i8] & 255);
                    break;
                }
                break;
            case 2:
                i4 = i2 + 1;
                if (i4 <= i6) {
                    b = ((this.j[0] & 255) << ap.n) | ((this.j[1] & 255) << 8) | (bArr[i2] & 255);
                    break;
                }
                break;
        }
    }
}
