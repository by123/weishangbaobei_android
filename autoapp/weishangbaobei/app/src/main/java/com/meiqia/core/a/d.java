package com.meiqia.core.a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class d {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v0, types: [byte, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(int r3) {
        /*
            if (r3 >= 0) goto L_0x0004
            int r3 = r3 + 256
        L_0x0004:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = a
            r0 = r2[r0]
            r1.append(r0)
            java.lang.String[] r0 = a
            r3 = r0[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.a.d.a(byte):java.lang.String");
    }

    public static String a(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte a2 : bArr) {
            stringBuffer.append(a(a2));
        }
        return stringBuffer.toString();
    }
}
