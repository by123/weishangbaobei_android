package com.umeng.socialize.net.stats.cache;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class a {
    private final File a;
    private final File b;

    public a(File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".bak");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    private static void a(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                        Log.d("AtomicFile", read + "");
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        Log.d("AtomicFile", "comsum time:" + (System.currentTimeMillis() - currentTimeMillis));
                        return;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            fileOutputStream = null;
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        }
    }

    static boolean c(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.getFD().sync();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public File a() {
        return this.a;
    }

    public FileOutputStream a(boolean z) throws IOException {
        if (this.a.exists()) {
            if (this.b.exists()) {
                this.a.delete();
            } else if (!this.a.renameTo(this.b)) {
                Log.w("AtomicFile", "Couldn't rename file " + this.a + " to backup file " + this.b);
            } else {
                a(this.b, this.a);
            }
        }
        try {
            return new FileOutputStream(this.a, z);
        } catch (FileNotFoundException e) {
            if (this.a.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.a, z);
                } catch (FileNotFoundException e2) {
                    throw new IOException("Couldn't create " + this.a);
                }
            } else {
                throw new IOException("Couldn't create directory " + this.a);
            }
        }
    }

    public void a(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            c(fileOutputStream);
            try {
                fileOutputStream.close();
                this.b.delete();
            } catch (IOException e) {
                Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    public void b() {
        this.a.delete();
        this.b.delete();
    }

    public void b(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            c(fileOutputStream);
            try {
                fileOutputStream.close();
                this.a.delete();
                this.b.renameTo(this.a);
            } catch (IOException e) {
                Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    public FileInputStream c() throws FileNotFoundException {
        if (this.b.exists()) {
            this.a.delete();
            this.b.renameTo(this.a);
        }
        return new FileInputStream(this.a);
    }

    public byte[] d() throws IOException {
        FileInputStream c = c();
        try {
            byte[] bArr = new byte[c.available()];
            int i = 0;
            while (true) {
                int read = c.read(bArr, i, bArr.length - i);
                if (read <= 0) {
                    return bArr;
                }
                i += read;
                int available = c.available();
                if (available > bArr.length - i) {
                    byte[] bArr2 = new byte[(available + i)];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            c.close();
        }
    }
}
