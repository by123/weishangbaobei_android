package com.umeng.socialize.net.stats.cache;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: AtomicFile */
public class a {
    private final File a;
    private final File b;

    public a(File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".bak");
    }

    public File a() {
        return this.a;
    }

    public void b() {
        this.a.delete();
        this.b.delete();
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
        } catch (FileNotFoundException unused) {
            if (this.a.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.a, z);
                } catch (FileNotFoundException unused2) {
                    throw new IOException("Couldn't create " + this.a);
                }
            } else {
                throw new IOException("Couldn't create directory " + this.a);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r6, java.io.File r7) throws java.io.IOException {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x005b }
            r3.<init>(r6)     // Catch:{ all -> 0x005b }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x0058 }
            r6.<init>(r7)     // Catch:{ all -> 0x0058 }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0056 }
        L_0x0013:
            int r2 = r3.read(r7)     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x0034
            r4 = 0
            r6.write(r7, r4, r2)     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = "AtomicFile"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r5.<init>()     // Catch:{ all -> 0x0056 }
            r5.append(r2)     // Catch:{ all -> 0x0056 }
            java.lang.String r2 = ""
            r5.append(r2)     // Catch:{ all -> 0x0056 }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x0056 }
            android.util.Log.d(r4, r2)     // Catch:{ all -> 0x0056 }
            goto L_0x0013
        L_0x0034:
            r3.close()
            r6.close()
            java.lang.String r6 = "AtomicFile"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "comsum time:"
            r7.append(r2)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r6, r7)
            return
        L_0x0056:
            r7 = move-exception
            goto L_0x005e
        L_0x0058:
            r7 = move-exception
            r6 = r2
            goto L_0x005e
        L_0x005b:
            r7 = move-exception
            r6 = r2
            r3 = r6
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.close()
        L_0x0063:
            if (r6 == 0) goto L_0x0068
            r6.close()
        L_0x0068:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.stats.cache.a.a(java.io.File, java.io.File):void");
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

    static boolean c(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return true;
        }
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
