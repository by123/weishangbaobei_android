package com.umeng.commonsdk.utils;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLock;

public class FileLockUtil {
    private final Object lockObject = new Object();

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, Object obj) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName(), obj);
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        try {
                            e2.printStackTrace();
                        } finally {
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, int i) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file, i);
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Exception e) {
                        try {
                            e.printStackTrace();
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
            return;
        }
        return;
        th.printStackTrace();
        return;
        throw th;
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName());
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        try {
                            e2.printStackTrace();
                        } finally {
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void doFileOperateion(String str, FileLockCallback fileLockCallback) {
        File file = new File(str);
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(str);
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName());
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        try {
                            e2.printStackTrace();
                        } finally {
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0022 A[SYNTHETIC, Splitter:B:15:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.channels.FileLock getFileLock(java.lang.String r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x001b, IOException -> 0x0015 }
            java.lang.String r2 = "rw"
            r1.<init>(r3, r2)     // Catch:{ FileNotFoundException -> 0x001b, IOException -> 0x0015 }
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch:{ FileNotFoundException -> 0x001b, IOException -> 0x0015 }
            java.nio.channels.FileLock r1 = r3.lock()     // Catch:{ FileNotFoundException -> 0x0013, IOException -> 0x0011 }
            return r1
        L_0x0011:
            r1 = move-exception
            goto L_0x0017
        L_0x0013:
            r1 = move-exception
            goto L_0x001d
        L_0x0015:
            r1 = move-exception
            r3 = r0
        L_0x0017:
            r1.printStackTrace()
            goto L_0x0020
        L_0x001b:
            r1 = move-exception
            r3 = r0
        L_0x001d:
            r1.printStackTrace()
        L_0x0020:
            if (r3 == 0) goto L_0x002a
            r3.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.FileLockUtil.getFileLock(java.lang.String):java.nio.channels.FileLock");
    }
}
