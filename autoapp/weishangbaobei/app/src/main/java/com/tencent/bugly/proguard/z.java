package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: BUGLY */
public class z {
    private static Map<String, String> a;
    private static boolean b;

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (x.a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    public static String a() {
        return a(System.currentTimeMillis());
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    private static byte[] a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        x.c("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        try {
            aj a2 = a.a(i);
            if (a2 == null) {
                return null;
            }
            a2.a(str);
            return a2.b(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static byte[] b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        try {
            aj a2 = a.a(i);
            if (a2 == null) {
                return null;
            }
            a2.a(str);
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            x.d("encrytype %d %s", Integer.valueOf(i), str);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e A[Catch:{ all -> 0x0083 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0073 A[SYNTHETIC, Splitter:B:30:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0086 A[SYNTHETIC, Splitter:B:38:0x0086] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.io.File r4, java.lang.String r5, java.lang.String r6) {
        /*
            r4 = 0
            if (r5 == 0) goto L_0x0096
            int r0 = r5.length()
            if (r0 != 0) goto L_0x000b
            goto L_0x0096
        L_0x000b:
            java.lang.String r0 = "rqdp{  ZF start}"
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r0, r2)
            java.lang.String r0 = "UTF-8"
            byte[] r5 = r5.getBytes(r0)     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            r0.<init>(r5)     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            r5.<init>()     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x0066, all -> 0x0062 }
            r3 = 8
            r2.setMethod(r3)     // Catch:{ Throwable -> 0x0060 }
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x0060 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x0060 }
            r2.putNextEntry(r3)     // Catch:{ Throwable -> 0x0060 }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ Throwable -> 0x0060 }
        L_0x0039:
            int r3 = r0.read(r6)     // Catch:{ Throwable -> 0x0060 }
            if (r3 <= 0) goto L_0x0043
            r2.write(r6, r1, r3)     // Catch:{ Throwable -> 0x0060 }
            goto L_0x0039
        L_0x0043:
            r2.closeEntry()     // Catch:{ Throwable -> 0x0060 }
            r2.flush()     // Catch:{ Throwable -> 0x0060 }
            r2.finish()     // Catch:{ Throwable -> 0x0060 }
            byte[] r5 = r5.toByteArray()     // Catch:{ Throwable -> 0x0060 }
            r2.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0058:
            java.lang.String r4 = "rqdp{  ZF end}"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r4, r6)
            return r5
        L_0x0060:
            r5 = move-exception
            goto L_0x0068
        L_0x0062:
            r5 = move-exception
            r2 = r4
            r4 = r5
            goto L_0x0084
        L_0x0066:
            r5 = move-exception
            r2 = r4
        L_0x0068:
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0083 }
            if (r6 != 0) goto L_0x0071
            r5.printStackTrace()     // Catch:{ all -> 0x0083 }
        L_0x0071:
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x007b
        L_0x0077:
            r5 = move-exception
            r5.printStackTrace()
        L_0x007b:
            java.lang.String r5 = "rqdp{  ZF end}"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            return r4
        L_0x0083:
            r4 = move-exception
        L_0x0084:
            if (r2 == 0) goto L_0x008e
            r2.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x008e
        L_0x008a:
            r5 = move-exception
            r5.printStackTrace()
        L_0x008e:
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r6 = "rqdp{  ZF end}"
            com.tencent.bugly.proguard.x.c(r6, r5)
            throw r4
        L_0x0096:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, java.lang.String, java.lang.String):byte[]");
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        x.c("[Util] Zip %d bytes data with type %s", objArr);
        try {
            ae a2 = ad.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        x.c("[Util] Unzip %d bytes data with type %s", objArr);
        try {
            ae a2 = ad.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.b(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return a(a(bArr, 2), 1, str);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i, int i2, String str) {
        try {
            return b(b(bArr, 1, str), 2);
        } catch (Exception e) {
            if (x.a(e)) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    public static long b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            return a(instance.digest());
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c5 A[Catch:{ all -> 0x00e4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ca A[SYNTHETIC, Splitter:B:64:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d4 A[SYNTHETIC, Splitter:B:69:0x00d4] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e8 A[SYNTHETIC, Splitter:B:78:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00f2 A[SYNTHETIC, Splitter:B:83:0x00f2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r5, java.io.File r6, int r7) {
        /*
            java.lang.String r7 = "rqdp{  ZF start}"
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r7, r1)
            if (r5 == 0) goto L_0x010b
            if (r6 == 0) goto L_0x010b
            boolean r7 = r5.equals(r6)
            if (r7 == 0) goto L_0x0014
            goto L_0x010b
        L_0x0014:
            boolean r7 = r5.exists()
            if (r7 == 0) goto L_0x0103
            boolean r7 = r5.canRead()
            if (r7 != 0) goto L_0x0022
            goto L_0x0103
        L_0x0022:
            java.io.File r7 = r6.getParentFile()     // Catch:{ Throwable -> 0x0043 }
            if (r7 == 0) goto L_0x0039
            java.io.File r7 = r6.getParentFile()     // Catch:{ Throwable -> 0x0043 }
            boolean r7 = r7.exists()     // Catch:{ Throwable -> 0x0043 }
            if (r7 != 0) goto L_0x0039
            java.io.File r7 = r6.getParentFile()     // Catch:{ Throwable -> 0x0043 }
            r7.mkdirs()     // Catch:{ Throwable -> 0x0043 }
        L_0x0039:
            boolean r7 = r6.exists()     // Catch:{ Throwable -> 0x0043 }
            if (r7 != 0) goto L_0x004d
            r6.createNewFile()     // Catch:{ Throwable -> 0x0043 }
            goto L_0x004d
        L_0x0043:
            r7 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r7)
            if (r1 != 0) goto L_0x004d
            r7.printStackTrace()
        L_0x004d:
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0102
            boolean r7 = r6.canRead()
            if (r7 != 0) goto L_0x005b
            goto L_0x0102
        L_0x005b:
            r7 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00bd, all -> 0x00b9 }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x00bd, all -> 0x00b9 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            r4.<init>(r6)     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00b5, all -> 0x00b2 }
            r6 = 8
            r2.setMethod(r6)     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            java.util.zip.ZipEntry r6 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            java.lang.String r5 = r5.getName()     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            r6.<init>(r5)     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            r2.putNextEntry(r6)     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            r5 = 5000(0x1388, float:7.006E-42)
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
        L_0x0085:
            int r6 = r1.read(r5)     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            if (r6 <= 0) goto L_0x008f
            r2.write(r5, r0, r6)     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            goto L_0x0085
        L_0x008f:
            r2.flush()     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            r2.closeEntry()     // Catch:{ Throwable -> 0x00b0, all -> 0x00ae }
            r1.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r5 = move-exception
            r5.printStackTrace()
        L_0x009d:
            r2.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00a5
        L_0x00a1:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a5:
            java.lang.String r5 = "rqdp{  ZF end}"
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r5, r6)
            r5 = 1
            return r5
        L_0x00ae:
            r5 = move-exception
            goto L_0x00e6
        L_0x00b0:
            r5 = move-exception
            goto L_0x00b7
        L_0x00b2:
            r5 = move-exception
            r2 = r7
            goto L_0x00e6
        L_0x00b5:
            r5 = move-exception
            r2 = r7
        L_0x00b7:
            r7 = r1
            goto L_0x00bf
        L_0x00b9:
            r5 = move-exception
            r1 = r7
            r2 = r1
            goto L_0x00e6
        L_0x00bd:
            r5 = move-exception
            r2 = r7
        L_0x00bf:
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00e4 }
            if (r6 != 0) goto L_0x00c8
            r5.printStackTrace()     // Catch:{ all -> 0x00e4 }
        L_0x00c8:
            if (r7 == 0) goto L_0x00d2
            r7.close()     // Catch:{ IOException -> 0x00ce }
            goto L_0x00d2
        L_0x00ce:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00d2:
            if (r2 == 0) goto L_0x00dc
            r2.close()     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00dc
        L_0x00d8:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00dc:
            java.lang.String r5 = "rqdp{  ZF end}"
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r5, r6)
            return r0
        L_0x00e4:
            r5 = move-exception
            r1 = r7
        L_0x00e6:
            if (r1 == 0) goto L_0x00f0
            r1.close()     // Catch:{ IOException -> 0x00ec }
            goto L_0x00f0
        L_0x00ec:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00f0:
            if (r2 == 0) goto L_0x00fa
            r2.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00fa
        L_0x00f6:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00fa:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r7 = "rqdp{  ZF end}"
            com.tencent.bugly.proguard.x.c(r7, r6)
            throw r5
        L_0x0102:
            return r0
        L_0x0103:
            java.lang.String r5 = "rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}"
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        L_0x010b:
            java.lang.String r5 = "rqdp{  err ZF 1R!}"
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, java.io.File, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b3 A[Catch:{ all -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b8 A[SYNTHETIC, Splitter:B:44:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c2 A[SYNTHETIC, Splitter:B:49:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00cf A[SYNTHETIC, Splitter:B:57:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d9 A[SYNTHETIC, Splitter:B:62:0x00d9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<java.lang.String> c(android.content.Context r5, java.lang.String r6) {
        /*
            boolean r5 = com.tencent.bugly.crashreport.common.info.AppInfo.f(r5)
            if (r5 == 0) goto L_0x0016
            java.util.ArrayList r5 = new java.util.ArrayList
            java.lang.String r6 = "unknown(low memory)"
            java.lang.String[] r6 = new java.lang.String[]{r6}
            java.util.List r6 = java.util.Arrays.asList(r6)
            r5.<init>(r6)
            return r5
        L_0x0016:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0 = 0
            java.lang.String r1 = "/system/bin/sh"
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            if (r2 == 0) goto L_0x0034
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            boolean r2 = r2.canExecute()     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            if (r2 != 0) goto L_0x0036
        L_0x0034:
            java.lang.String r1 = "sh"
        L_0x0036:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r4 = 0
            r3[r4] = r1     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r1 = 1
            java.lang.String r4 = "-c"
            r3[r1] = r4     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.util.List r1 = java.util.Arrays.asList(r3)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r2.add(r6)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.lang.Runtime r6 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r1 = 3
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.lang.Object[] r1 = r2.toArray(r1)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.lang.Process r6 = r6.exec(r1)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            java.io.InputStream r3 = r6.getInputStream()     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        L_0x006c:
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            if (r2 == 0) goto L_0x0076
            r5.add(r2)     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            goto L_0x006c
        L_0x0076:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            java.io.InputStream r6 = r6.getErrorStream()     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a4, all -> 0x00a1 }
        L_0x0084:
            java.lang.String r6 = r2.readLine()     // Catch:{ Throwable -> 0x009f }
            if (r6 == 0) goto L_0x008e
            r5.add(r6)     // Catch:{ Throwable -> 0x009f }
            goto L_0x0084
        L_0x008e:
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0096:
            r2.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r6 = move-exception
            r6.printStackTrace()
        L_0x009e:
            return r5
        L_0x009f:
            r5 = move-exception
            goto L_0x00ad
        L_0x00a1:
            r5 = move-exception
            r2 = r0
            goto L_0x00cc
        L_0x00a4:
            r5 = move-exception
            r2 = r0
            goto L_0x00ad
        L_0x00a7:
            r5 = move-exception
            r2 = r0
            goto L_0x00cd
        L_0x00aa:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x00ad:
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00cb }
            if (r6 != 0) goto L_0x00b6
            r5.printStackTrace()     // Catch:{ all -> 0x00cb }
        L_0x00b6:
            if (r1 == 0) goto L_0x00c0
            r1.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00c0:
            if (r2 == 0) goto L_0x00ca
            r2.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x00ca
        L_0x00c6:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00ca:
            return r0
        L_0x00cb:
            r5 = move-exception
        L_0x00cc:
            r0 = r1
        L_0x00cd:
            if (r0 == 0) goto L_0x00d7
            r0.close()     // Catch:{ IOException -> 0x00d3 }
            goto L_0x00d7
        L_0x00d3:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00d7:
            if (r2 == 0) goto L_0x00e1
            r2.close()     // Catch:{ IOException -> 0x00dd }
            goto L_0x00e1
        L_0x00dd:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00e1:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.c(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public static String a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (a == null) {
            a = new HashMap();
            ArrayList<String> c = c(context, "getprop");
            if (c != null && c.size() > 0) {
                x.b(z.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : c) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                x.b(z.class, "System properties number: %d.", Integer.valueOf(a.size()));
            }
        }
        return a.containsKey(str) ? a.get(str) : "fail";
    }

    public static void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static void b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    public static byte[] c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long c(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = com.stub.StubApp.getOrigApplicationContext(r1.getApplicationContext());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context a(android.content.Context r1) {
        /*
            if (r1 != 0) goto L_0x0003
            return r1
        L_0x0003:
            android.content.Context r0 = r1.getApplicationContext()
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(android.content.Context):android.content.Context");
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set((Object) null, obj);
        } catch (Exception unused) {
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object) null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            String string = readBundle.getString("pluginVal" + i2 + "plugInId");
            String string2 = readBundle.getString("pluginVal" + i2 + "plugInUUID");
            arrayList2.add(new PlugInBean(string, readBundle.getString("pluginVal" + i2 + "plugInVersion"), string2));
        }
        if (arrayList.size() == arrayList2.size()) {
            hashMap = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            x.e("map plugin parcel error!", new Object[0]);
        }
        return hashMap;
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static Map<String, String> b(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            x.e("map parcel error!", new Object[0]);
        } else {
            hashMap = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        }
        return hashMap;
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain != null) {
                obtain.recycle();
            }
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b3 A[Catch:{ all -> 0x00aa }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d6 A[SYNTHETIC, Splitter:B:46:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fd A[SYNTHETIC, Splitter:B:60:0x00fd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r4, int r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "android.permission.READ_LOGS"
            boolean r4 = com.tencent.bugly.crashreport.common.info.AppInfo.a(r4, r0)
            r0 = 0
            r1 = 0
            if (r4 != 0) goto L_0x0012
            java.lang.String r4 = "no read_log permission!"
            java.lang.Object[] r5 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.d(r4, r5)
            return r0
        L_0x0012:
            if (r6 != 0) goto L_0x0021
            java.lang.String r4 = "logcat"
            java.lang.String r6 = "-d"
            java.lang.String r2 = "-v"
            java.lang.String r3 = "threadtime"
            java.lang.String[] r4 = new java.lang.String[]{r4, r6, r2, r3}
            goto L_0x003f
        L_0x0021:
            r4 = 6
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.String r2 = "logcat"
            r4[r1] = r2
            r2 = 1
            java.lang.String r3 = "-d"
            r4[r2] = r3
            r2 = 2
            java.lang.String r3 = "-v"
            r4[r2] = r3
            r2 = 3
            java.lang.String r3 = "threadtime"
            r4[r2] = r3
            r2 = 4
            java.lang.String r3 = "-s"
            r4[r2] = r3
            r2 = 5
            r4[r2] = r6
        L_0x003f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00ac }
            java.lang.Process r4 = r2.exec(r4)     // Catch:{ Throwable -> 0x00ac }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            java.io.InputStream r3 = r4.getInputStream()     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
        L_0x005a:
            java.lang.String r2 = r0.readLine()     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            if (r2 == 0) goto L_0x0079
            r6.append(r2)     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            java.lang.String r2 = "\n"
            r6.append(r2)     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            if (r5 <= 0) goto L_0x005a
            int r2 = r6.length()     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            if (r2 <= r5) goto L_0x005a
            int r2 = r6.length()     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            int r2 = r2 - r5
            r6.delete(r1, r2)     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            goto L_0x005a
        L_0x0079:
            java.lang.String r5 = r6.toString()     // Catch:{ Throwable -> 0x00a7, all -> 0x00a4 }
            if (r4 == 0) goto L_0x00a3
            java.io.OutputStream r6 = r4.getOutputStream()     // Catch:{ IOException -> 0x0087 }
            r6.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r6 = move-exception
            r6.printStackTrace()
        L_0x008b:
            java.io.InputStream r6 = r4.getInputStream()     // Catch:{ IOException -> 0x0093 }
            r6.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0097:
            java.io.InputStream r4 = r4.getErrorStream()     // Catch:{ IOException -> 0x009f }
            r4.close()     // Catch:{ IOException -> 0x009f }
            goto L_0x00a3
        L_0x009f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x00a3:
            return r5
        L_0x00a4:
            r5 = move-exception
            r0 = r4
            goto L_0x00fb
        L_0x00a7:
            r5 = move-exception
            r0 = r4
            goto L_0x00ad
        L_0x00aa:
            r5 = move-exception
            goto L_0x00fb
        L_0x00ac:
            r5 = move-exception
        L_0x00ad:
            boolean r4 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00aa }
            if (r4 != 0) goto L_0x00b6
            r5.printStackTrace()     // Catch:{ all -> 0x00aa }
        L_0x00b6:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00aa }
            java.lang.String r1 = "\n[error:"
            r4.<init>(r1)     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00aa }
            r4.append(r5)     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = "]"
            r4.append(r5)     // Catch:{ all -> 0x00aa }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00aa }
            r6.append(r4)     // Catch:{ all -> 0x00aa }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x00aa }
            if (r0 == 0) goto L_0x00fa
            java.io.OutputStream r5 = r0.getOutputStream()     // Catch:{ IOException -> 0x00de }
            r5.close()     // Catch:{ IOException -> 0x00de }
            goto L_0x00e2
        L_0x00de:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00e2:
            java.io.InputStream r5 = r0.getInputStream()     // Catch:{ IOException -> 0x00ea }
            r5.close()     // Catch:{ IOException -> 0x00ea }
            goto L_0x00ee
        L_0x00ea:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00ee:
            java.io.InputStream r5 = r0.getErrorStream()     // Catch:{ IOException -> 0x00f6 }
            r5.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00fa
        L_0x00f6:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00fa:
            return r4
        L_0x00fb:
            if (r0 == 0) goto L_0x0121
            java.io.OutputStream r4 = r0.getOutputStream()     // Catch:{ IOException -> 0x0105 }
            r4.close()     // Catch:{ IOException -> 0x0105 }
            goto L_0x0109
        L_0x0105:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0109:
            java.io.InputStream r4 = r0.getInputStream()     // Catch:{ IOException -> 0x0111 }
            r4.close()     // Catch:{ IOException -> 0x0111 }
            goto L_0x0115
        L_0x0111:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0115:
            java.io.InputStream r4 = r0.getErrorStream()     // Catch:{ IOException -> 0x011d }
            r4.close()     // Catch:{ IOException -> 0x011d }
            goto L_0x0121
        L_0x011d:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0121:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(android.content.Context, int, java.lang.String):java.lang.String");
    }

    public static Map<String, String> a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : allStackTraces.entrySet()) {
            int i2 = 0;
            sb.setLength(0);
            if (!(next.getValue() == null || ((StackTraceElement[]) next.getValue()).length == 0)) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
                int length = stackTraceElementArr.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                    i2++;
                }
                hashMap.put(((Thread) next.getKey()).getName() + "(" + ((Thread) next.getKey()).getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC, Splitter:B:20:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0055 A[SYNTHETIC, Splitter:B:27:0x0055] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized byte[] a(int r6) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.z> r6 = com.tencent.bugly.proguard.z.class
            monitor-enter(r6)
            r0 = 16
            r1 = 0
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            java.lang.String r5 = "/dev/urandom"
            r4.<init>(r5)     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0026, all -> 0x0023 }
            r2.readFully(r0)     // Catch:{ Exception -> 0x0021 }
            r2.close()     // Catch:{ Exception -> 0x005b }
            monitor-exit(r6)
            return r0
        L_0x0021:
            r0 = move-exception
            goto L_0x0028
        L_0x0023:
            r0 = move-exception
            r2 = r1
            goto L_0x0053
        L_0x0026:
            r0 = move-exception
            r2 = r1
        L_0x0028:
            java.lang.String r3 = "Failed to read from /dev/urandom : %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0052 }
            r5 = 0
            r4[r5] = r0     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.x.e(r3, r4)     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ Exception -> 0x005b }
        L_0x0038:
            java.lang.String r0 = "AES"
            javax.crypto.KeyGenerator r0 = javax.crypto.KeyGenerator.getInstance(r0)     // Catch:{ Exception -> 0x005b }
            r2 = 128(0x80, float:1.794E-43)
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch:{ Exception -> 0x005b }
            r3.<init>()     // Catch:{ Exception -> 0x005b }
            r0.init(r2, r3)     // Catch:{ Exception -> 0x005b }
            javax.crypto.SecretKey r0 = r0.generateKey()     // Catch:{ Exception -> 0x005b }
            byte[] r0 = r0.getEncoded()     // Catch:{ Exception -> 0x005b }
            monitor-exit(r6)
            return r0
        L_0x0052:
            r0 = move-exception
        L_0x0053:
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ Exception -> 0x005b }
            goto L_0x005d
        L_0x0059:
            r0 = move-exception
            goto L_0x0069
        L_0x005b:
            r0 = move-exception
            goto L_0x005e
        L_0x005d:
            throw r0     // Catch:{ Exception -> 0x005b }
        L_0x005e:
            boolean r2 = com.tencent.bugly.proguard.x.b(r0)     // Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x0067
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
        L_0x0067:
            monitor-exit(r6)
            return r1
        L_0x0069:
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(int):byte[]");
    }

    @TargetApi(19)
    public static byte[] a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            if (Build.VERSION.SDK_INT >= 21) {
                if (!b) {
                    instance.init(i, secretKeySpec, new GCMParameterSpec(instance.getBlockSize() << 3, bArr2));
                    return instance.doFinal(bArr);
                }
            }
            instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException e) {
            b = true;
            throw e;
        } catch (Exception e2) {
            if (x.b(e2)) {
                return null;
            }
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] b(int i, byte[] bArr, byte[] bArr2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (x.b(e)) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(Context context, String str, long j) {
        x.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                x.c("[Util] Lock file (%s) is expired, unlock it.", str);
                b(context, str);
            }
            if (file.createNewFile()) {
                x.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            x.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        x.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            x.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006a A[SYNTHETIC, Splitter:B:32:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r5, int r6, boolean r7) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x007f
            boolean r1 = r5.exists()
            if (r1 == 0) goto L_0x007f
            boolean r1 = r5.canRead()
            if (r1 != 0) goto L_0x0011
            goto L_0x007f
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            r1.<init>()     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            java.lang.String r5 = "utf-8"
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0063, all -> 0x0060 }
        L_0x0027:
            java.lang.String r5 = r2.readLine()     // Catch:{ Throwable -> 0x005e }
            if (r5 == 0) goto L_0x0051
            r1.append(r5)     // Catch:{ Throwable -> 0x005e }
            java.lang.String r5 = "\n"
            r1.append(r5)     // Catch:{ Throwable -> 0x005e }
            if (r6 <= 0) goto L_0x0027
            int r5 = r1.length()     // Catch:{ Throwable -> 0x005e }
            if (r5 <= r6) goto L_0x0027
            if (r7 == 0) goto L_0x0047
            int r5 = r1.length()     // Catch:{ Throwable -> 0x005e }
            r1.delete(r6, r5)     // Catch:{ Throwable -> 0x005e }
            goto L_0x0051
        L_0x0047:
            r5 = 0
            int r3 = r1.length()     // Catch:{ Throwable -> 0x005e }
            int r3 = r3 - r6
            r1.delete(r5, r3)     // Catch:{ Throwable -> 0x005e }
            goto L_0x0027
        L_0x0051:
            java.lang.String r5 = r1.toString()     // Catch:{ Throwable -> 0x005e }
            r2.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x005d:
            return r5
        L_0x005e:
            r5 = move-exception
            goto L_0x0065
        L_0x0060:
            r5 = move-exception
            r2 = r0
            goto L_0x0074
        L_0x0063:
            r5 = move-exception
            r2 = r0
        L_0x0065:
            com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch:{ Exception -> 0x006e }
            goto L_0x0072
        L_0x006e:
            r5 = move-exception
            com.tencent.bugly.proguard.x.a(r5)
        L_0x0072:
            return r0
        L_0x0073:
            r5 = move-exception
        L_0x0074:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch:{ Exception -> 0x007a }
            goto L_0x007e
        L_0x007a:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x007e:
            throw r5
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, int, boolean):java.lang.String");
    }

    private static BufferedReader a(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (Throwable th) {
            x.a(th);
            return null;
        }
    }

    public static BufferedReader a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists()) {
                if (file.canRead()) {
                    return a(file);
                }
            }
            return null;
        } catch (NullPointerException e) {
            x.a(e);
            return null;
        }
    }

    public static Thread a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            x.e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    public static boolean a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        w a2 = w.a();
        if (a2 != null) {
            return a2.a(runnable);
        }
        String[] split = runnable.getClass().getName().split("\\.");
        return a(runnable, split[split.length - 1]) != null;
    }

    public static boolean c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            x.a("URL(%s)'s length is larger than 255.", str);
            return false;
        } else if (str.toLowerCase().startsWith("http")) {
            return true;
        } else {
            x.a("URL(%s) is not start with \"http\".", str);
            return false;
        }
    }

    public static SharedPreferences a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    public static String b(String str, String str2) {
        return (a.b() == null || a.b().G == null) ? "" : a.b().G.getString(str, str2);
    }
}
