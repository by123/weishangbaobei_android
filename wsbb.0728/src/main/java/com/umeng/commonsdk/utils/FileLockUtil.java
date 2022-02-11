package com.umeng.commonsdk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockUtil {
    private final Object lockObject = new Object();

    /* JADX WARNING: Removed duplicated region for block: B:18:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[SYNTHETIC, Splitter:B:9:0x0018] */
    private static FileLock getFileLock(String str) {
        FileChannel fileChannel;
        try {
            fileChannel = new RandomAccessFile(str, "rw").getChannel();
            try {
                return fileChannel.lock();
            } catch (FileNotFoundException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                if (fileChannel == null) {
                }
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            fileChannel = null;
        } catch (IOException e4) {
            e = e4;
            fileChannel = null;
            e.printStackTrace();
            if (fileChannel == null) {
                return null;
            }
            try {
                fileChannel.close();
                return null;
            } catch (IOException e5) {
                e5.printStackTrace();
                return null;
            }
        }
        e.printStackTrace();
        if (fileChannel == null) {
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0025=Splitter:B:11:0x0025, B:28:0x0049=Splitter:B:28:0x0049, B:18:0x002b=Splitter:B:18:0x002b} */
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
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0021=Splitter:B:11:0x0021, B:28:0x0045=Splitter:B:28:0x0045, B:18:0x0027=Splitter:B:18:0x0027} */
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
                            th.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        fileLock.release();
                        fileLock.channel().close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0025=Splitter:B:11:0x0025, B:28:0x0049=Splitter:B:28:0x0049, B:18:0x002b=Splitter:B:18:0x002b} */
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
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0026=Splitter:B:11:0x0026, B:28:0x004a=Splitter:B:28:0x004a, B:18:0x002c=Splitter:B:18:0x002c} */
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
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }
}
