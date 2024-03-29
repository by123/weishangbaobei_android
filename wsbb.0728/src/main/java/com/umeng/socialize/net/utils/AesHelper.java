package com.umeng.socialize.net.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesHelper {
    private static final String UTF_8 = "UTF-8";
    private static byte[] iv = "nmeug.f9/Om+L823".getBytes();
    private static byte[] pwd;

    public static String decryptNoPadding(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        instance.init(2, new SecretKeySpec(pwd, "AES"), new IvParameterSpec(iv));
        return new String(instance.doFinal(Base64.decodeBase64(str)), str2);
    }

    public static String encryptNoPadding(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        int blockSize = instance.getBlockSize();
        byte[] bytes = str.getBytes(str2);
        int length = bytes.length;
        int i = length % blockSize;
        if (i != 0) {
            length += blockSize - i;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        instance.init(1, new SecretKeySpec(pwd, "AES"), new IvParameterSpec(iv));
        return Base64.encodeBase64String(instance.doFinal(bArr));
    }

    public static byte[] getBytesUnchecked(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            throw newIllegalStateException(str2, e);
        }
    }

    public static byte[] getBytesUtf8(String str) {
        return getBytesUnchecked(str, UTF_8);
    }

    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    private static IllegalStateException newIllegalStateException(String str, UnsupportedEncodingException unsupportedEncodingException) {
        return new IllegalStateException(str + ": " + unsupportedEncodingException);
    }

    public static String newString(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            throw newIllegalStateException(str, e);
        }
    }

    public static String newStringUtf8(byte[] bArr) {
        return newString(bArr, UTF_8);
    }

    public static void setPassword(String str) {
        if (!TextUtils.isEmpty(str)) {
            String md5 = md5(str);
            if (md5.length() >= 16) {
                md5 = md5.substring(0, 16);
            }
            pwd = md5.getBytes();
        }
    }
}
