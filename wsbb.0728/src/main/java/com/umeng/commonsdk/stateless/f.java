package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class f {
    public static int a;
    private static final byte[] b = {10, 1, 11, 5, 4, ap.m, 7, 9, 23, 3, 1, 6, 8, 12, ap.k, 91};
    private static Object c = new Object();

    public static File a(Context context) {
        File[] listFiles;
        File[] listFiles2;
        File file = null;
        try {
            synchronized (c) {
                try {
                    ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                    if (!(context == null || StubApp.getOrigApplicationContext(context.getApplicationContext()) == null)) {
                        String str = StubApp.getOrigApplicationContext(context.getApplicationContext()).getFilesDir() + "/" + a.e;
                        if (!TextUtils.isEmpty(str)) {
                            File file2 = new File(str);
                            if (file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
                                int i = 0;
                                while (i < listFiles.length) {
                                    try {
                                        File file3 = listFiles[i];
                                        if (file3 != null) {
                                            if (file3.isDirectory() && (listFiles2 = file3.listFiles()) != null && listFiles2.length > 0) {
                                                Arrays.sort(listFiles2, new Comparator<File>() {
                                                    /* renamed from: a */
                                                    public int compare(File file, File file2) {
                                                        long lastModified = file.lastModified() - file2.lastModified();
                                                        if (lastModified > 0) {
                                                            return 1;
                                                        }
                                                        return lastModified == 0 ? 0 : -1;
                                                    }
                                                });
                                                File file4 = listFiles2[0];
                                                if (file4 != null) {
                                                    if (file != null) {
                                                        if (file.lastModified() <= file4.lastModified()) {
                                                        }
                                                    }
                                                    file = file4;
                                                }
                                            }
                                        }
                                        i++;
                                    } catch (Throwable th) {
                                        th = th;
                                        throw th;
                                    }
                                }
                            }
                        }
                    }
                    ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } catch (Throwable th3) {
            UMCrashManager.reportCrash(context, th3);
        }
        return file;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static void a(Context context, String str, int i) {
        if (str == null) {
            try {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        } else {
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (c) {
                File[] listFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles == null || listFiles.length < i) {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                } else {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator<File>() {
                            /* renamed from: a */
                            public int compare(File file, File file2) {
                                if (file == null || file2 == null || file.lastModified() >= file2.lastModified()) {
                                    return (file == null || file2 == null || file.lastModified() != file2.lastModified()) ? 1 : 0;
                                }
                                return -1;
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList.get(i3) != null) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        }
    }

    public static boolean a(long j, long j2) {
        return j > j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011a, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011b, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0141 A[SYNTHETIC, Splitter:B:43:0x0141] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:40:0x011c=Splitter:B:40:0x011c, B:28:0x00cd=Splitter:B:28:0x00cd} */
    public static boolean a(Context context, String str, String str2, byte[] bArr) {
        boolean z;
        boolean z2;
        Object[] objArr;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        if (context == null) {
            return false;
        }
        try {
            synchronized (c) {
                try {
                    ULog.i("walle", "[stateless] begin write envelope, thread is " + Thread.currentThread());
                    File file = new File(context.getFilesDir() + "/" + a.e);
                    if (!file.isDirectory()) {
                        file.mkdir();
                    }
                    File file2 = new File(file.getPath() + "/" + str);
                    if (!file2.isDirectory()) {
                        file2.mkdir();
                    }
                    File file3 = new File(file2.getPath() + "/" + str2);
                    if (!file3.exists()) {
                        file3.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file3);
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                        try {
                            ULog.i("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                            return true;
                        } catch (Throwable th) {
                            th = th;
                            z = true;
                            while (true) {
                                try {
                                    break;
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        z = false;
                        fileOutputStream2 = fileOutputStream;
                        while (true) {
                            break;
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    z = false;
                    fileOutputStream2 = fileOutputStream;
                    while (true) {
                        break;
                    }
                    throw th;
                }
            }
        } catch (IOException e) {
            e = e;
            z = false;
            try {
                ULog.i("walle", "[stateless] write envelope, e is " + e.getMessage());
                UMCrashManager.reportCrash(context, e);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                    }
                }
                objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
                z2 = z;
                ULog.i("walle", objArr);
                return z2;
            } catch (Throwable th5) {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                    }
                }
                ULog.i("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                throw th5;
            }
        } catch (Throwable th6) {
            th = th6;
            z2 = false;
            ULog.i("walle", "[stateless] write envelope, e is " + th.getMessage());
            UMCrashManager.reportCrash(context, th);
            if (fileOutputStream2 != null) {
            }
            objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
            ULog.i("walle", objArr);
            return z2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ac A[SYNTHETIC, Splitter:B:26:0x00ac] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x0082=Splitter:B:14:0x0082, B:28:0x00af=Splitter:B:28:0x00af} */
    public static byte[] a(String str) throws IOException {
        IOException e;
        Throwable th;
        byte[] bArr;
        FileChannel fileChannel = null;
        synchronized (c) {
            ULog.i("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                try {
                    MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
                    System.out.println(load.isLoaded());
                    bArr = new byte[((int) channel.size())];
                    if (load.remaining() > 0) {
                        load.get(bArr, 0, load.remaining());
                    }
                    ULog.i("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileChannel = channel;
                    try {
                        ULog.i("walle", "[stateless] write envelope, e is " + e.getMessage());
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        if (fileChannel != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = channel;
                    th = th;
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Throwable th4) {
                th = th4;
                if (fileChannel != null) {
                }
                throw th;
            }
        }
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    public static byte[] a(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int deflate = deflater.deflate(bArr2);
                    a += deflate;
                    byteArrayOutputStream.write(bArr2, 0, deflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
            }
            throw th;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(b));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }
}
