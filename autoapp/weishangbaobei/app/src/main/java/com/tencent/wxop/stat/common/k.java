package com.tencent.wxop.stat.common;

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
        this.a = bArr;
        boolean z = true;
        this.d = (i2 & 1) == 0;
        this.e = (i2 & 2) == 0;
        this.f = (i2 & 4) == 0 ? false : z;
        this.l = (i2 & 8) == 0 ? h : i;
        this.j = new byte[2];
        this.c = 0;
        this.k = this.e ? 19 : -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0056, code lost:
        if (r2 == -1) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0058, code lost:
        r4[0] = r3[(r2 >> 18) & 63];
        r4[1] = r3[(r2 >> 12) & 63];
        r4[2] = r3[(r2 >> 6) & 63];
        r4[3] = r3[r2 & 63];
        r5 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0079, code lost:
        if (r5 != 0) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007d, code lost:
        if (r0.f == false) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007f, code lost:
        r2 = 5;
        r4[4] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0083, code lost:
        r2 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0084, code lost:
        r5 = r2 + 1;
        r4[r2] = 10;
        r2 = 19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008b, code lost:
        r2 = r5;
        r5 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008e, code lost:
        r2 = r5;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0090, code lost:
        r8 = r7 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0092, code lost:
        if (r8 > r6) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0094, code lost:
        r7 = (r19[r7 + 2] & 255) | (((r19[r7] & 255) << com.umeng.commonsdk.proguard.ap.n) | ((r19[r7 + 1] & 255) << 8));
        r4[r5] = r3[(r7 >> 18) & 63];
        r4[r5 + 1] = r3[(r7 >> 12) & 63];
        r4[r5 + 2] = r3[(r7 >> 6) & 63];
        r4[r5 + 3] = r3[r7 & 63];
        r5 = r5 + 4;
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d2, code lost:
        if (r2 != 0) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00d6, code lost:
        if (r0.f == false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d8, code lost:
        r2 = r5 + 1;
        r4[r5] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00dd, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00de, code lost:
        r5 = r2 + 1;
        r4[r2] = 10;
        r7 = r8;
        r2 = 19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00e6, code lost:
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e9, code lost:
        if (r22 == false) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f1, code lost:
        if ((r7 - r0.c) != (r6 - 1)) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f5, code lost:
        if (r0.c <= 0) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00f7, code lost:
        r1 = r0.j[0];
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fd, code lost:
        r1 = r19[r7];
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0102, code lost:
        r1 = (r1 & 255) << 4;
        r0.c -= r9;
        r8 = r5 + 1;
        r4[r5] = r3[(r1 >> 6) & 63];
        r5 = r8 + 1;
        r4[r8] = r3[r1 & 63];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011e, code lost:
        if (r0.d == false) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0120, code lost:
        r1 = r5 + 1;
        r4[r5] = 61;
        r5 = r1 + 1;
        r4[r1] = 61;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x012c, code lost:
        if (r0.e == false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0130, code lost:
        if (r0.f == false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0132, code lost:
        r1 = r5 + 1;
        r4[r5] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0137, code lost:
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0138, code lost:
        r5 = r1 + 1;
        r4[r1] = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0144, code lost:
        if ((r7 - r0.c) != (r6 - 2)) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0148, code lost:
        if (r0.c <= 1) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x014a, code lost:
        r9 = 1;
        r8 = r7;
        r7 = r0.j[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0155, code lost:
        r8 = r7 + 1;
        r7 = r19[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0159, code lost:
        r7 = (r7 & 255) << 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x015e, code lost:
        if (r0.c <= 0) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0160, code lost:
        r11 = r9 + 1;
        r1 = r0.j[r9];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0167, code lost:
        r1 = r19[r8];
        r8 = r8 + 1;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016d, code lost:
        r1 = ((r1 & 255) << 2) | r7;
        r0.c -= r11;
        r7 = r5 + 1;
        r4[r5] = r3[(r1 >> 12) & 63];
        r5 = r7 + 1;
        r4[r7] = r3[(r1 >> 6) & 63];
        r7 = r5 + 1;
        r4[r5] = r3[r1 & 63];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0195, code lost:
        if (r0.d == false) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0197, code lost:
        r1 = r7 + 1;
        r4[r7] = 61;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x019e, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01a1, code lost:
        if (r0.e == false) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a5, code lost:
        if (r0.f == false) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a7, code lost:
        r4[r1] = com.umeng.commonsdk.proguard.ap.k;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ac, code lost:
        r4[r1] = 10;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b1, code lost:
        r5 = r1;
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b6, code lost:
        if (r0.e == false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b8, code lost:
        if (r5 <= 0) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01bc, code lost:
        if (r2 == 19) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c0, code lost:
        if (r0.f == false) goto L_0x01c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01c2, code lost:
        r1 = r5 + 1;
        r4[r5] = com.umeng.commonsdk.proguard.ap.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01c7, code lost:
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01c8, code lost:
        r4[r1] = 10;
        r5 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01cf, code lost:
        if (g != false) goto L_0x01dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01d3, code lost:
        if (r0.c != 0) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01db, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01de, code lost:
        if (g != false) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01e0, code lost:
        if (r7 != r6) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004a, code lost:
        r0.c = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01e8, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01eb, code lost:
        if (r7 != (r6 - 1)) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01ed, code lost:
        r3 = r0.j;
        r4 = r0.c;
        r0.c = r4 + 1;
        r3[r4] = r19[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01fc, code lost:
        if (r7 != (r6 - 2)) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01fe, code lost:
        r3 = r0.j;
        r4 = r0.c;
        r0.c = r4 + 1;
        r3[r4] = r19[r7];
        r3 = r0.j;
        r4 = r0.c;
        r0.c = r4 + 1;
        r3[r4] = r19[r7 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0217, code lost:
        r0.b = r5;
        r0.k = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x021b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004d, code lost:
        r7 = r20;
        r2 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(byte[] r19, int r20, int r21, boolean r22) {
        /*
            r18 = this;
            r0 = r18
            byte[] r3 = r0.l
            byte[] r4 = r0.a
            int r5 = r0.k
            int r6 = r21 + r20
            int r7 = r0.c
            r8 = -1
            r9 = 0
            r10 = 1
            switch(r7) {
                case 0: goto L_0x004d;
                case 1: goto L_0x002e;
                case 2: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x004d
        L_0x0013:
            int r7 = r20 + 1
            if (r7 > r6) goto L_0x004d
            byte[] r11 = r0.j
            byte r11 = r11[r9]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 16
            byte[] r12 = r0.j
            byte r12 = r12[r10]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 8
            r11 = r11 | r12
            byte r2 = r19[r20]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r2 | r11
            goto L_0x004a
        L_0x002e:
            int r7 = r20 + 2
            if (r7 > r6) goto L_0x004d
            byte[] r7 = r0.j
            byte r7 = r7[r9]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 << 16
            int r11 = r20 + 1
            byte r2 = r19[r20]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r7
            int r7 = r11 + 1
            byte r11 = r19[r11]
            r11 = r11 & 255(0xff, float:3.57E-43)
            r2 = r2 | r11
        L_0x004a:
            r0.c = r9
            goto L_0x0050
        L_0x004d:
            r7 = r20
            r2 = -1
        L_0x0050:
            r12 = 4
            r13 = 13
            r14 = 10
            r15 = 2
            if (r2 == r8) goto L_0x008e
            int r8 = r2 >> 18
            r8 = r8 & 63
            byte r8 = r3[r8]
            r4[r9] = r8
            int r8 = r2 >> 12
            r8 = r8 & 63
            byte r8 = r3[r8]
            r4[r10] = r8
            int r8 = r2 >> 6
            r8 = r8 & 63
            byte r8 = r3[r8]
            r4[r15] = r8
            r2 = r2 & 63
            byte r2 = r3[r2]
            r8 = 3
            r4[r8] = r2
            int r5 = r5 + -1
            if (r5 != 0) goto L_0x008b
            boolean r2 = r0.f
            if (r2 == 0) goto L_0x0083
            r2 = 5
            r4[r12] = r13
            goto L_0x0084
        L_0x0083:
            r2 = 4
        L_0x0084:
            int r5 = r2 + 1
            r4[r2] = r14
            r2 = 19
            goto L_0x0090
        L_0x008b:
            r2 = r5
            r5 = 4
            goto L_0x0090
        L_0x008e:
            r2 = r5
            r5 = 0
        L_0x0090:
            int r8 = r7 + 3
            if (r8 > r6) goto L_0x00e9
            byte r11 = r19[r7]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 16
            int r16 = r7 + 1
            byte r15 = r19[r16]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r11 = r11 | r15
            int r7 = r7 + 2
            byte r7 = r19[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r7 = r7 | r11
            int r11 = r7 >> 18
            r11 = r11 & 63
            byte r11 = r3[r11]
            r4[r5] = r11
            int r11 = r5 + 1
            int r15 = r7 >> 12
            r15 = r15 & 63
            byte r15 = r3[r15]
            r4[r11] = r15
            int r11 = r5 + 2
            int r15 = r7 >> 6
            r15 = r15 & 63
            byte r15 = r3[r15]
            r4[r11] = r15
            int r11 = r5 + 3
            r7 = r7 & 63
            byte r7 = r3[r7]
            r4[r11] = r7
            int r5 = r5 + 4
            int r2 = r2 + -1
            if (r2 != 0) goto L_0x00e6
            boolean r2 = r0.f
            if (r2 == 0) goto L_0x00dd
            int r2 = r5 + 1
            r4[r5] = r13
            goto L_0x00de
        L_0x00dd:
            r2 = r5
        L_0x00de:
            int r5 = r2 + 1
            r4[r2] = r14
            r7 = r8
            r2 = 19
            goto L_0x00e7
        L_0x00e6:
            r7 = r8
        L_0x00e7:
            r15 = 2
            goto L_0x0090
        L_0x00e9:
            if (r22 == 0) goto L_0x01e9
            int r8 = r0.c
            int r8 = r7 - r8
            int r11 = r6 + -1
            if (r8 != r11) goto L_0x013e
            int r8 = r0.c
            if (r8 <= 0) goto L_0x00fd
            byte[] r1 = r0.j
            byte r1 = r1[r9]
            r9 = 1
            goto L_0x0102
        L_0x00fd:
            int r8 = r7 + 1
            byte r1 = r19[r7]
            r7 = r8
        L_0x0102:
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r12
            int r8 = r0.c
            int r8 = r8 - r9
            r0.c = r8
            int r8 = r5 + 1
            int r9 = r1 >> 6
            r9 = r9 & 63
            byte r9 = r3[r9]
            r4[r5] = r9
            int r5 = r8 + 1
            r1 = r1 & 63
            byte r1 = r3[r1]
            r4[r8] = r1
            boolean r1 = r0.d
            if (r1 == 0) goto L_0x012a
            int r1 = r5 + 1
            r3 = 61
            r4[r5] = r3
            int r5 = r1 + 1
            r4[r1] = r3
        L_0x012a:
            boolean r1 = r0.e
            if (r1 == 0) goto L_0x01cd
            boolean r1 = r0.f
            if (r1 == 0) goto L_0x0137
            int r1 = r5 + 1
            r4[r5] = r13
            goto L_0x0138
        L_0x0137:
            r1 = r5
        L_0x0138:
            int r5 = r1 + 1
            r4[r1] = r14
            goto L_0x01cd
        L_0x013e:
            int r8 = r0.c
            int r8 = r7 - r8
            int r11 = r6 + -2
            if (r8 != r11) goto L_0x01b4
            int r8 = r0.c
            if (r8 <= r10) goto L_0x0155
            byte[] r8 = r0.j
            byte r8 = r8[r9]
            r9 = 1
            r17 = r8
            r8 = r7
            r7 = r17
            goto L_0x0159
        L_0x0155:
            int r8 = r7 + 1
            byte r7 = r19[r7]
        L_0x0159:
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 << r14
            int r11 = r0.c
            if (r11 <= 0) goto L_0x0167
            byte[] r1 = r0.j
            int r11 = r9 + 1
            byte r1 = r1[r9]
            goto L_0x016d
        L_0x0167:
            int r11 = r8 + 1
            byte r1 = r19[r8]
            r8 = r11
            r11 = r9
        L_0x016d:
            r1 = r1 & 255(0xff, float:3.57E-43)
            r9 = 2
            int r1 = r1 << r9
            r1 = r1 | r7
            int r7 = r0.c
            int r7 = r7 - r11
            r0.c = r7
            int r7 = r5 + 1
            int r9 = r1 >> 12
            r9 = r9 & 63
            byte r9 = r3[r9]
            r4[r5] = r9
            int r5 = r7 + 1
            int r9 = r1 >> 6
            r9 = r9 & 63
            byte r9 = r3[r9]
            r4[r7] = r9
            int r7 = r5 + 1
            r1 = r1 & 63
            byte r1 = r3[r1]
            r4[r5] = r1
            boolean r1 = r0.d
            if (r1 == 0) goto L_0x019e
            int r1 = r7 + 1
            r3 = 61
            r4[r7] = r3
            goto L_0x019f
        L_0x019e:
            r1 = r7
        L_0x019f:
            boolean r3 = r0.e
            if (r3 == 0) goto L_0x01b1
            boolean r3 = r0.f
            if (r3 == 0) goto L_0x01ac
            int r3 = r1 + 1
            r4[r1] = r13
            r1 = r3
        L_0x01ac:
            int r3 = r1 + 1
            r4[r1] = r14
            r1 = r3
        L_0x01b1:
            r5 = r1
            r7 = r8
            goto L_0x01cd
        L_0x01b4:
            boolean r1 = r0.e
            if (r1 == 0) goto L_0x01cd
            if (r5 <= 0) goto L_0x01cd
            r1 = 19
            if (r2 == r1) goto L_0x01cd
            boolean r1 = r0.f
            if (r1 == 0) goto L_0x01c7
            int r1 = r5 + 1
            r4[r5] = r13
            goto L_0x01c8
        L_0x01c7:
            r1 = r5
        L_0x01c8:
            int r3 = r1 + 1
            r4[r1] = r14
            r5 = r3
        L_0x01cd:
            boolean r1 = g
            if (r1 != 0) goto L_0x01dc
            int r1 = r0.c
            if (r1 != 0) goto L_0x01d6
            goto L_0x01dc
        L_0x01d6:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x01dc:
            boolean r1 = g
            if (r1 != 0) goto L_0x0217
            if (r7 != r6) goto L_0x01e3
            goto L_0x0217
        L_0x01e3:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x01e9:
            int r3 = r6 + -1
            if (r7 != r3) goto L_0x01fa
            byte[] r3 = r0.j
            int r4 = r0.c
            int r6 = r4 + 1
            r0.c = r6
            byte r1 = r19[r7]
            r3[r4] = r1
            goto L_0x0217
        L_0x01fa:
            r3 = 2
            int r6 = r6 - r3
            if (r7 != r6) goto L_0x0217
            byte[] r3 = r0.j
            int r4 = r0.c
            int r6 = r4 + 1
            r0.c = r6
            byte r6 = r19[r7]
            r3[r4] = r6
            byte[] r3 = r0.j
            int r4 = r0.c
            int r6 = r4 + 1
            r0.c = r6
            int r7 = r7 + r10
            byte r1 = r19[r7]
            r3[r4] = r1
        L_0x0217:
            r0.b = r5
            r0.k = r2
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.common.k.a(byte[], int, int, boolean):boolean");
    }
}
