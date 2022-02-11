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
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c0, code lost:
        r3 = (r3 << 6) | r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c3, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(byte[] r12, int r13, int r14, boolean r15) {
        /*
            r11 = this;
            int r0 = r11.e
            r1 = 0
            r2 = 6
            if (r0 != r2) goto L_0x0007
            return r1
        L_0x0007:
            int r14 = r14 + r13
            int r0 = r11.e
            int r3 = r11.f
            byte[] r4 = r11.a
            int[] r5 = r11.g
            r6 = 4
            r7 = 0
        L_0x0012:
            if (r13 >= r14) goto L_0x00d9
            if (r0 != 0) goto L_0x0059
        L_0x0016:
            int r8 = r13 + 4
            if (r8 > r14) goto L_0x0057
            byte r3 = r12[r13]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r5[r3]
            int r3 = r3 << 18
            int r9 = r13 + 1
            byte r9 = r12[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r9 = r5[r9]
            int r9 = r9 << 12
            r3 = r3 | r9
            int r9 = r13 + 2
            byte r9 = r12[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r9 = r5[r9]
            int r9 = r9 << r2
            r3 = r3 | r9
            int r9 = r13 + 3
            byte r9 = r12[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r9 = r5[r9]
            r3 = r3 | r9
            if (r3 < 0) goto L_0x0057
            int r13 = r7 + 2
            byte r9 = (byte) r3
            r4[r13] = r9
            int r13 = r7 + 1
            int r9 = r3 >> 8
            byte r9 = (byte) r9
            r4[r13] = r9
            int r13 = r3 >> 16
            byte r13 = (byte) r13
            r4[r7] = r13
            int r7 = r7 + 3
            r13 = r8
            goto L_0x0016
        L_0x0057:
            if (r13 >= r14) goto L_0x00d9
        L_0x0059:
            int r8 = r13 + 1
            byte r13 = r12[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r13 = r5[r13]
            r9 = -2
            r10 = -1
            switch(r0) {
                case 0: goto L_0x00cb;
                case 1: goto L_0x00be;
                case 2: goto L_0x00a8;
                case 3: goto L_0x0075;
                case 4: goto L_0x006d;
                case 5: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x00d6
        L_0x0068:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x006d:
            if (r13 != r9) goto L_0x0070
            goto L_0x00c3
        L_0x0070:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x0075:
            if (r13 < 0) goto L_0x0091
            int r0 = r3 << 6
            r3 = r0 | r13
            int r13 = r7 + 2
            byte r0 = (byte) r3
            r4[r13] = r0
            int r13 = r7 + 1
            int r0 = r3 >> 8
            byte r0 = (byte) r0
            r4[r13] = r0
            int r13 = r3 >> 16
            byte r13 = (byte) r13
            r4[r7] = r13
            int r7 = r7 + 3
            r13 = r8
            r0 = 0
            goto L_0x0012
        L_0x0091:
            if (r13 != r9) goto L_0x00a3
            int r13 = r7 + 1
            int r0 = r3 >> 2
            byte r0 = (byte) r0
            r4[r13] = r0
            int r13 = r3 >> 10
            byte r13 = (byte) r13
            r4[r7] = r13
            int r7 = r7 + 2
            r0 = 5
            goto L_0x00d6
        L_0x00a3:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x00a8:
            if (r13 < 0) goto L_0x00ab
            goto L_0x00c0
        L_0x00ab:
            if (r13 != r9) goto L_0x00b9
            int r13 = r7 + 1
            int r0 = r3 >> 4
            byte r0 = (byte) r0
            r4[r7] = r0
            r7 = r13
            r13 = r8
            r0 = 4
            goto L_0x0012
        L_0x00b9:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x00be:
            if (r13 < 0) goto L_0x00c6
        L_0x00c0:
            int r3 = r3 << 6
            r3 = r3 | r13
        L_0x00c3:
            int r0 = r0 + 1
            goto L_0x00d6
        L_0x00c6:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x00cb:
            if (r13 < 0) goto L_0x00d1
            int r0 = r0 + 1
            r3 = r13
            goto L_0x00d6
        L_0x00d1:
            if (r13 == r10) goto L_0x00d6
            r11.e = r2
            return r1
        L_0x00d6:
            r13 = r8
            goto L_0x0012
        L_0x00d9:
            r12 = 1
            if (r15 != 0) goto L_0x00e3
            r11.e = r0
            r11.f = r3
        L_0x00e0:
            r11.b = r7
            return r12
        L_0x00e3:
            switch(r0) {
                case 0: goto L_0x0105;
                case 1: goto L_0x0102;
                case 2: goto L_0x00f9;
                case 3: goto L_0x00ea;
                case 4: goto L_0x00e7;
                default: goto L_0x00e6;
            }
        L_0x00e6:
            goto L_0x0105
        L_0x00e7:
            r11.e = r2
            return r1
        L_0x00ea:
            int r13 = r7 + 1
            int r14 = r3 >> 10
            byte r14 = (byte) r14
            r4[r7] = r14
            int r7 = r13 + 1
            int r14 = r3 >> 2
            byte r14 = (byte) r14
            r4[r13] = r14
            goto L_0x0105
        L_0x00f9:
            int r13 = r7 + 1
            int r14 = r3 >> 4
            byte r14 = (byte) r14
            r4[r7] = r14
            r7 = r13
            goto L_0x0105
        L_0x0102:
            r11.e = r2
            return r1
        L_0x0105:
            r11.e = r0
            goto L_0x00e0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.common.j.a(byte[], int, int, boolean):boolean");
    }
}
