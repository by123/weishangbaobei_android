package com.tencent.bugly.proguard;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class ah implements aj {
    private String a = null;

    public final void a(String str) {
        if (str != null) {
            for (int length = str.length(); length < 16; length++) {
                str = str + "0";
            }
            this.a = str.substring(0, 16);
        }
    }

    public final byte[] a(byte[] bArr) throws Exception {
        if (this.a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(this.a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(b2 + " ");
        }
        return doFinal;
    }

    public final byte[] b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(this.a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(b2 + " ");
        }
        return doFinal;
    }
}
