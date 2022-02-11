package com.umeng.socialize.a.b;

import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.File;
import java.io.IOException;

/* compiled from: FileUtil */
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
        if (b == null) {
            return new b();
        }
        return b;
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

    /* JADX WARNING: Removed duplicated region for block: B:29:0x006c A[SYNTHETIC, Splitter:B:29:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[Catch:{ IOException -> 0x0070 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0094 A[SYNTHETIC, Splitter:B:39:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009c A[Catch:{ IOException -> 0x0098 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(java.io.File r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            r1.<init>(r7)     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0047, all -> 0x0042 }
            r7.<init>()     // Catch:{ Exception -> 0x0047, all -> 0x0042 }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0040 }
        L_0x000f:
            int r3 = r1.read(r2)     // Catch:{ Exception -> 0x0040 }
            r4 = -1
            if (r3 == r4) goto L_0x001b
            r4 = 0
            r7.write(r2, r4, r3)     // Catch:{ Exception -> 0x0040 }
            goto L_0x000f
        L_0x001b:
            byte[] r2 = r7.toByteArray()     // Catch:{ Exception -> 0x0040 }
            r1.close()     // Catch:{ IOException -> 0x0026 }
            r7.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x003f
        L_0x0026:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.umeng.socialize.utils.UmengText.READ_IMAGE_ERROR
            r0.append(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.umeng.socialize.utils.Log.um(r7)
        L_0x003f:
            return r2
        L_0x0040:
            r2 = move-exception
            goto L_0x0052
        L_0x0042:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0092
        L_0x0047:
            r2 = move-exception
            r7 = r0
            goto L_0x0052
        L_0x004a:
            r7 = move-exception
            r1 = r0
            r0 = r7
            r7 = r1
            goto L_0x0092
        L_0x004f:
            r2 = move-exception
            r7 = r0
            r1 = r7
        L_0x0052:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r3.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = com.umeng.socialize.utils.UmengText.READ_IMAGE_ERROR     // Catch:{ all -> 0x0091 }
            r3.append(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0091 }
            r3.append(r2)     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0091 }
            com.umeng.socialize.utils.Log.um(r2)     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0072
            r1.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0072
        L_0x0070:
            r7 = move-exception
            goto L_0x0078
        L_0x0072:
            if (r7 == 0) goto L_0x0090
            r7.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0090
        L_0x0078:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.umeng.socialize.utils.UmengText.READ_IMAGE_ERROR
            r1.append(r2)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.umeng.socialize.utils.Log.um(r7)
        L_0x0090:
            return r0
        L_0x0091:
            r0 = move-exception
        L_0x0092:
            if (r1 == 0) goto L_0x009a
            r1.close()     // Catch:{ IOException -> 0x0098 }
            goto L_0x009a
        L_0x0098:
            r7 = move-exception
            goto L_0x00a0
        L_0x009a:
            if (r7 == 0) goto L_0x00b8
            r7.close()     // Catch:{ IOException -> 0x0098 }
            goto L_0x00b8
        L_0x00a0:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.umeng.socialize.utils.UmengText.READ_IMAGE_ERROR
            r1.append(r2)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.umeng.socialize.utils.Log.um(r7)
        L_0x00b8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.a.b.b.a(java.io.File):byte[]");
    }

    public String d() {
        return AesHelper.md5(String.valueOf(System.currentTimeMillis()));
    }
}
