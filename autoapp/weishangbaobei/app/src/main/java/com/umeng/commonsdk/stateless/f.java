package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: UMSLUtils */
public class f {
    public static int a;
    private static final byte[] b = {10, 1, 11, 5, 4, ap.m, 7, 9, 23, 3, 1, 6, 8, 12, ap.k, 91};
    private static Object c = new Object();

    public static boolean a(long j, long j2) {
        return j > j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d1, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d3, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ff A[SYNTHETIC, Splitter:B:43:0x00ff] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r8, java.lang.String r9, java.lang.String r10, byte[] r11) {
        /*
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L_0x0189
            r2 = 0
            java.lang.Object r3 = c     // Catch:{ IOException -> 0x011e, Throwable -> 0x00da }
            monitor-enter(r3)     // Catch:{ IOException -> 0x011e, Throwable -> 0x00da }
            java.lang.String r4 = "walle"
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ all -> 0x00cd }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cd }
            r6.<init>()     // Catch:{ all -> 0x00cd }
            java.lang.String r7 = "[stateless] begin write envelope, thread is "
            r6.append(r7)     // Catch:{ all -> 0x00cd }
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00cd }
            r6.append(r7)     // Catch:{ all -> 0x00cd }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00cd }
            r5[r1] = r6     // Catch:{ all -> 0x00cd }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r4, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00cd }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x00cd }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cd }
            r5.<init>()     // Catch:{ all -> 0x00cd }
            java.io.File r6 = r8.getFilesDir()     // Catch:{ all -> 0x00cd }
            r5.append(r6)     // Catch:{ all -> 0x00cd }
            java.lang.String r6 = "/"
            r5.append(r6)     // Catch:{ all -> 0x00cd }
            java.lang.String r6 = "stateless"
            r5.append(r6)     // Catch:{ all -> 0x00cd }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00cd }
            r4.<init>(r5)     // Catch:{ all -> 0x00cd }
            boolean r5 = r4.isDirectory()     // Catch:{ all -> 0x00cd }
            if (r5 != 0) goto L_0x004e
            r4.mkdir()     // Catch:{ all -> 0x00cd }
        L_0x004e:
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x00cd }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cd }
            r6.<init>()     // Catch:{ all -> 0x00cd }
            java.lang.String r4 = r4.getPath()     // Catch:{ all -> 0x00cd }
            r6.append(r4)     // Catch:{ all -> 0x00cd }
            java.lang.String r4 = "/"
            r6.append(r4)     // Catch:{ all -> 0x00cd }
            r6.append(r9)     // Catch:{ all -> 0x00cd }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x00cd }
            r5.<init>(r9)     // Catch:{ all -> 0x00cd }
            boolean r9 = r5.isDirectory()     // Catch:{ all -> 0x00cd }
            if (r9 != 0) goto L_0x0074
            r5.mkdir()     // Catch:{ all -> 0x00cd }
        L_0x0074:
            java.io.File r9 = new java.io.File     // Catch:{ all -> 0x00cd }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cd }
            r4.<init>()     // Catch:{ all -> 0x00cd }
            java.lang.String r5 = r5.getPath()     // Catch:{ all -> 0x00cd }
            r4.append(r5)     // Catch:{ all -> 0x00cd }
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch:{ all -> 0x00cd }
            r4.append(r10)     // Catch:{ all -> 0x00cd }
            java.lang.String r10 = r4.toString()     // Catch:{ all -> 0x00cd }
            r9.<init>(r10)     // Catch:{ all -> 0x00cd }
            boolean r10 = r9.exists()     // Catch:{ all -> 0x00cd }
            if (r10 != 0) goto L_0x009a
            r9.createNewFile()     // Catch:{ all -> 0x00cd }
        L_0x009a:
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ all -> 0x00cd }
            r10.<init>(r9)     // Catch:{ all -> 0x00cd }
            r10.write(r11)     // Catch:{ all -> 0x00ca }
            r10.close()     // Catch:{ all -> 0x00ca }
            monitor-exit(r3)     // Catch:{ all -> 0x00c7 }
            java.lang.String r8 = "walle"
            java.lang.Object[] r9 = new java.lang.Object[r0]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "[stateless] end write envelope, thread id "
            r10.append(r11)
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9[r1] = r10
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r8, (java.lang.Object[]) r9)
            r10 = 1
            goto L_0x018a
        L_0x00c7:
            r9 = move-exception
            r10 = 1
            goto L_0x00cf
        L_0x00ca:
            r9 = move-exception
            r2 = r10
            goto L_0x00ce
        L_0x00cd:
            r9 = move-exception
        L_0x00ce:
            r10 = 0
        L_0x00cf:
            monitor-exit(r3)     // Catch:{ all -> 0x00d5 }
            throw r9     // Catch:{ IOException -> 0x00d3, Throwable -> 0x00d1 }
        L_0x00d1:
            r9 = move-exception
            goto L_0x00dc
        L_0x00d3:
            r9 = move-exception
            goto L_0x0120
        L_0x00d5:
            r9 = move-exception
            goto L_0x00cf
        L_0x00d7:
            r8 = move-exception
            goto L_0x0165
        L_0x00da:
            r9 = move-exception
            r10 = 0
        L_0x00dc:
            java.lang.String r11 = "walle"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x00d7 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d7 }
            r4.<init>()     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = "[stateless] write envelope, e is "
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = r9.getMessage()     // Catch:{ all -> 0x00d7 }
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00d7 }
            r3[r1] = r4     // Catch:{ all -> 0x00d7 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r11, (java.lang.Object[]) r3)     // Catch:{ all -> 0x00d7 }
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r8, r9)     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0102
            r2.close()     // Catch:{ IOException -> 0x0102 }
        L_0x0102:
            java.lang.String r8 = "walle"
            java.lang.Object[] r9 = new java.lang.Object[r0]
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "[stateless] end write envelope, thread id "
            r11.append(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            r9[r1] = r11
            goto L_0x0161
        L_0x011e:
            r9 = move-exception
            r10 = 0
        L_0x0120:
            java.lang.String r11 = "walle"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x00d7 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d7 }
            r4.<init>()     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = "[stateless] write envelope, e is "
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r5 = r9.getMessage()     // Catch:{ all -> 0x00d7 }
            r4.append(r5)     // Catch:{ all -> 0x00d7 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00d7 }
            r3[r1] = r4     // Catch:{ all -> 0x00d7 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r11, (java.lang.Object[]) r3)     // Catch:{ all -> 0x00d7 }
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r8, r9)     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0146
            r2.close()     // Catch:{ IOException -> 0x0146 }
        L_0x0146:
            java.lang.String r8 = "walle"
            java.lang.Object[] r9 = new java.lang.Object[r0]
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "[stateless] end write envelope, thread id "
            r11.append(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            r9[r1] = r11
        L_0x0161:
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r8, (java.lang.Object[]) r9)
            goto L_0x018a
        L_0x0165:
            if (r2 == 0) goto L_0x016a
            r2.close()     // Catch:{ IOException -> 0x016a }
        L_0x016a:
            java.lang.Object[] r9 = new java.lang.Object[r0]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "[stateless] end write envelope, thread id "
            r10.append(r11)
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9[r1] = r10
            java.lang.String r10 = "walle"
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r10, (java.lang.Object[]) r9)
            throw r8
        L_0x0189:
            r10 = 0
        L_0x018a:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:2|3|4|5|6|7|8|9|(1:11)|12|(2:14|15)|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:28|(2:30|31)|32|33) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0080 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x00b2 */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00af A[SYNTHETIC, Splitter:B:30:0x00af] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:16:0x0080=Splitter:B:16:0x0080, B:32:0x00b2=Splitter:B:32:0x00b2} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r13) throws java.io.IOException {
        /*
            java.lang.Object r0 = c
            monitor-enter(r0)
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r4.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r5 = "[stateless] begin read envelope, thread is "
            r4.append(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00b3 }
            r4.append(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00b3 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x00b3 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r3)     // Catch:{ all -> 0x00b3 }
            r1 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0089, all -> 0x0084 }
            java.lang.String r4 = "r"
            r3.<init>(r13, r4)     // Catch:{ IOException -> 0x0089, all -> 0x0084 }
            java.nio.channels.FileChannel r13 = r3.getChannel()     // Catch:{ IOException -> 0x0089, all -> 0x0084 }
            java.nio.channels.FileChannel$MapMode r7 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ IOException -> 0x0082 }
            r8 = 0
            long r10 = r13.size()     // Catch:{ IOException -> 0x0082 }
            r6 = r13
            java.nio.MappedByteBuffer r1 = r6.map(r7, r8, r10)     // Catch:{ IOException -> 0x0082 }
            java.nio.MappedByteBuffer r1 = r1.load()     // Catch:{ IOException -> 0x0082 }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ IOException -> 0x0082 }
            boolean r4 = r1.isLoaded()     // Catch:{ IOException -> 0x0082 }
            r3.println(r4)     // Catch:{ IOException -> 0x0082 }
            long r3 = r13.size()     // Catch:{ IOException -> 0x0082 }
            int r3 = (int) r3     // Catch:{ IOException -> 0x0082 }
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x0082 }
            int r4 = r1.remaining()     // Catch:{ IOException -> 0x0082 }
            if (r4 <= 0) goto L_0x005d
            int r4 = r1.remaining()     // Catch:{ IOException -> 0x0082 }
            r1.get(r3, r5, r4)     // Catch:{ IOException -> 0x0082 }
        L_0x005d:
            java.lang.String r1 = "walle"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0082 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0082 }
            r6.<init>()     // Catch:{ IOException -> 0x0082 }
            java.lang.String r7 = "[stateless] end read envelope, thread id "
            r6.append(r7)     // Catch:{ IOException -> 0x0082 }
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ IOException -> 0x0082 }
            r6.append(r7)     // Catch:{ IOException -> 0x0082 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0082 }
            r4[r5] = r6     // Catch:{ IOException -> 0x0082 }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r1, (java.lang.Object[]) r4)     // Catch:{ IOException -> 0x0082 }
            if (r13 == 0) goto L_0x0080
            r13.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            monitor-exit(r0)     // Catch:{ all -> 0x00b3 }
            return r3
        L_0x0082:
            r1 = move-exception
            goto L_0x008d
        L_0x0084:
            r13 = move-exception
            r12 = r1
            r1 = r13
            r13 = r12
            goto L_0x00ad
        L_0x0089:
            r13 = move-exception
            r12 = r1
            r1 = r13
            r13 = r12
        L_0x008d:
            java.lang.String r3 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00ac }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ac }
            r4.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = "[stateless] write envelope, e is "
            r4.append(r6)     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = r1.getMessage()     // Catch:{ all -> 0x00ac }
            r4.append(r6)     // Catch:{ all -> 0x00ac }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ac }
            r2[r5] = r4     // Catch:{ all -> 0x00ac }
            com.umeng.commonsdk.statistics.common.ULog.i((java.lang.String) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x00ac }
            throw r1     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r1 = move-exception
        L_0x00ad:
            if (r13 == 0) goto L_0x00b2
            r13.close()     // Catch:{ IOException -> 0x00b2 }
        L_0x00b2:
            throw r1     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00b3 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(java.lang.String):byte[]");
    }

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
                                File file3 = null;
                                int i = 0;
                                while (i < listFiles.length) {
                                    try {
                                        File file4 = listFiles[i];
                                        if (file4 != null && file4.isDirectory() && (listFiles2 = file4.listFiles()) != null && listFiles2.length > 0) {
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
                                            File file5 = listFiles2[0];
                                            if (file5 != null && (file3 == null || file3.lastModified() > file5.lastModified())) {
                                                file3 = file5;
                                            }
                                        }
                                        i++;
                                    } catch (Throwable th) {
                                        th = th;
                                        file = file3;
                                        throw th;
                                    }
                                }
                                file = file3;
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
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r5) throws java.io.IOException {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x0046
            int r1 = r5.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0046
        L_0x0007:
            java.util.zip.Deflater r1 = new java.util.zip.Deflater
            r1.<init>()
            r1.setInput(r5)
            r1.finish()
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]
            r2 = 0
            a = r2
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x003e }
            r3.<init>()     // Catch:{ all -> 0x003e }
        L_0x001e:
            boolean r0 = r1.finished()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x0031
            int r0 = r1.deflate(r5)     // Catch:{ all -> 0x003c }
            int r4 = a     // Catch:{ all -> 0x003c }
            int r4 = r4 + r0
            a = r4     // Catch:{ all -> 0x003c }
            r3.write(r5, r2, r0)     // Catch:{ all -> 0x003c }
            goto L_0x001e
        L_0x0031:
            r1.end()     // Catch:{ all -> 0x003c }
            r3.close()
            byte[] r5 = r3.toByteArray()
            return r5
        L_0x003c:
            r5 = move-exception
            goto L_0x0040
        L_0x003e:
            r5 = move-exception
            r3 = r0
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()
        L_0x0045:
            throw r5
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(byte[]):byte[]");
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
        } catch (Exception unused) {
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
