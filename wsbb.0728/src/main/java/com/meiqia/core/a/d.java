package com.meiqia.core.a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class d {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v0, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    private static String a(int i) {
        if (i < 0) {
            i += 256;
        }
        return a[i / 16] + a[i % 16];
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
