package com.tencent.wxop.stat.common;

import android.util.Base64;

public class f {
    static byte[] a() {
        return Base64.decode("MDNhOTc2NTExZTJjYmUzYTdmMjY4MDhmYjdhZjNjMDU=", 0);
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, a());
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r6v2, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] a(byte[] r7, byte[] r8) {
        /*
            r0 = 256(0x100, float:3.59E-43)
            int[] r1 = new int[r0]
            int[] r2 = new int[r0]
            int r3 = r8.length
            if (r3 <= 0) goto L_0x005d
            if (r3 > r0) goto L_0x005d
            r4 = 0
            r5 = 0
        L_0x000d:
            if (r5 >= r0) goto L_0x001a
            r1[r5] = r5
            int r6 = r5 % r3
            byte r6 = r8[r6]
            r2[r5] = r6
            int r5 = r5 + 1
            goto L_0x000d
        L_0x001a:
            r8 = 0
            r3 = 0
        L_0x001c:
            if (r8 >= r0) goto L_0x0031
            r5 = r1[r8]
            int r3 = r3 + r5
            r5 = r2[r8]
            int r3 = r3 + r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            r5 = r1[r8]
            r6 = r1[r3]
            r1[r8] = r6
            r1[r3] = r5
            int r8 = r8 + 1
            goto L_0x001c
        L_0x0031:
            int r8 = r7.length
            byte[] r8 = new byte[r8]
            r0 = 0
            r2 = 0
        L_0x0036:
            int r3 = r7.length
            if (r4 >= r3) goto L_0x005c
            int r0 = r0 + 1
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = r1[r0]
            int r2 = r2 + r3
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3 = r1[r0]
            r5 = r1[r2]
            r1[r0] = r5
            r1[r2] = r3
            r3 = r1[r0]
            r5 = r1[r2]
            int r3 = r3 + r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r1[r3]
            byte r5 = r7[r4]
            r3 = r3 ^ r5
            byte r3 = (byte) r3
            r8[r4] = r3
            int r4 = r4 + 1
            goto L_0x0036
        L_0x005c:
            return r8
        L_0x005d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "key must be between 1 and 256 bytes"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.common.f.a(byte[], byte[]):byte[]");
    }

    public static byte[] b(byte[] bArr) {
        return b(bArr, a());
    }

    static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2);
    }
}
