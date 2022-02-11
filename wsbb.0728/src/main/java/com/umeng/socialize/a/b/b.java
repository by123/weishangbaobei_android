package com.umeng.socialize.a.b;

import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class b {
    private static b b = new b();
    private String a = "";

    private b() {
        try {
            this.a = ContextUtil.getContext().getCacheDir().getCanonicalPath();
        } catch (IOException e) {
            Log.um(UmengText.FET_CACHE_PATH_ERROR + e.getMessage());
        }
    }

    public static b a() {
        return b == null ? new b() : b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036 A[SYNTHETIC, Splitter:B:15:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b A[Catch:{ IOException -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069 A[SYNTHETIC, Splitter:B:29:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e A[Catch:{ IOException -> 0x009b }] */
    public byte[] a(File file) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        fileInputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        Log.um(UmengText.READ_IMAGE_ERROR + e.getMessage());
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                byteArrayOutputStream = null;
                try {
                    Log.um(UmengText.READ_IMAGE_ERROR + e.getMessage());
                    if (fileInputStream != null) {
                    }
                    if (byteArrayOutputStream != null) {
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                    }
                    if (byteArrayOutputStream != null) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                if (fileInputStream != null) {
                }
                if (byteArrayOutputStream != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            byteArrayOutputStream = null;
            Log.um(UmengText.READ_IMAGE_ERROR + e.getMessage());
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    Log.um(UmengText.READ_IMAGE_ERROR + e5.getMessage());
                }
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return bArr;
        } catch (Throwable th4) {
            byteArrayOutputStream = null;
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    Log.um(UmengText.READ_IMAGE_ERROR + e6.getMessage());
                    throw th;
                }
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return bArr;
    }

    public File b() throws IOException {
        a.b();
        File file = new File(c(), d());
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public File c() throws IOException {
        String str;
        if (Environment.getExternalStorageDirectory() != null && !TextUtils.isEmpty(Environment.getExternalStorageDirectory().getCanonicalPath())) {
            str = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else if (!TextUtils.isEmpty(this.a)) {
            str = this.a;
            Log.um(UmengText.SD_NOT_FOUNT);
        } else {
            throw new IOException("dirpath is unknow");
        }
        File file = new File(str + c.f);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public String d() {
        return AesHelper.md5(String.valueOf(System.currentTimeMillis()));
    }
}
