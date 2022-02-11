package com.umeng.commonsdk.internal.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* compiled from: ProcessUtil */
public class g {
    private static final String a = "\n";
    private static final byte[] b = "\nexit\n".getBytes();
    private static byte[] c = new byte[32];

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v3, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r1v27 */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        c(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006e, code lost:
        r0 = th;
        r1 = r1;
        r3 = r3;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
        r7 = null;
        r1 = r1;
        r3 = r3;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0074, code lost:
        r7 = null;
        r1 = r1;
        r3 = r3;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00cc, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00d9, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00dc, code lost:
        if (r7 != null) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00de, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x00e3, code lost:
        return r7.toString();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String... r9) {
        /*
            r0 = 0
            java.lang.ProcessBuilder r1 = new java.lang.ProcessBuilder     // Catch:{ IOException -> 0x00cf, Exception -> 0x00c2, all -> 0x00b1 }
            r2 = 0
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ IOException -> 0x00cf, Exception -> 0x00c2, all -> 0x00b1 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x00cf, Exception -> 0x00c2, all -> 0x00b1 }
            java.lang.ProcessBuilder r9 = r1.command(r9)     // Catch:{ IOException -> 0x00cf, Exception -> 0x00c2, all -> 0x00b1 }
            java.lang.Process r9 = r9.start()     // Catch:{ IOException -> 0x00cf, Exception -> 0x00c2, all -> 0x00b1 }
            java.io.OutputStream r1 = r9.getOutputStream()     // Catch:{ IOException -> 0x00af, Exception -> 0x00ad, all -> 0x00a5 }
            java.io.InputStream r2 = r9.getInputStream()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a1, all -> 0x009a }
            java.io.InputStream r3 = r9.getErrorStream()     // Catch:{ IOException -> 0x0097, Exception -> 0x0095, all -> 0x008f }
            byte[] r4 = b     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            r1.write(r4)     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            r1.flush()     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            r9.waitFor()     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x008c, Exception -> 0x0089, all -> 0x0083 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0080, Exception -> 0x007d, all -> 0x0077 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0080, Exception -> 0x007d, all -> 0x0077 }
            java.lang.String r6 = r5.readLine()     // Catch:{ IOException -> 0x0074, Exception -> 0x0071, all -> 0x006e }
            if (r6 == 0) goto L_0x0054
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0074, Exception -> 0x0071, all -> 0x006e }
            r7.<init>()     // Catch:{ IOException -> 0x0074, Exception -> 0x0071, all -> 0x006e }
            r7.append(r6)     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            java.lang.String r6 = a     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            r7.append(r6)     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
        L_0x0045:
            java.lang.String r6 = r5.readLine()     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            if (r6 == 0) goto L_0x0055
            r7.append(r6)     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            java.lang.String r6 = a     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            r7.append(r6)     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            goto L_0x0045
        L_0x0054:
            r7 = r0
        L_0x0055:
            byte[] r6 = c     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            int r6 = r3.read(r6)     // Catch:{ IOException -> 0x006b, Exception -> 0x0068, all -> 0x006e }
            if (r6 <= 0) goto L_0x005e
            goto L_0x0055
        L_0x005e:
            a(r1, r3, r2, r4, r5)
            if (r9 == 0) goto L_0x00dc
        L_0x0063:
            c(r9)
            goto L_0x00dc
        L_0x0068:
            goto L_0x00c9
        L_0x006b:
            goto L_0x00d6
        L_0x006e:
            r0 = move-exception
            goto L_0x00b9
        L_0x0071:
            r7 = r0
            goto L_0x00c9
        L_0x0074:
            r7 = r0
            goto L_0x00d6
        L_0x0077:
            r5 = move-exception
            r8 = r5
            r5 = r0
            r0 = r8
            goto L_0x00b9
        L_0x007d:
            r5 = r0
            goto L_0x00c8
        L_0x0080:
            r5 = r0
            goto L_0x00d5
        L_0x0083:
            r4 = move-exception
            r5 = r0
            r0 = r4
            r4 = r5
            goto L_0x00b9
        L_0x0089:
            r4 = r0
            goto L_0x00c7
        L_0x008c:
            r4 = r0
            goto L_0x00d4
        L_0x008f:
            r3 = move-exception
            r4 = r0
            r5 = r4
            r0 = r3
            r3 = r5
            goto L_0x00b9
        L_0x0095:
            r3 = r0
            goto L_0x00c6
        L_0x0097:
            r3 = r0
            goto L_0x00d3
        L_0x009a:
            r2 = move-exception
            r3 = r0
            r4 = r3
            r5 = r4
            r0 = r2
            r2 = r5
            goto L_0x00b9
        L_0x00a1:
            r2 = r0
            goto L_0x00c5
        L_0x00a3:
            r2 = r0
            goto L_0x00d2
        L_0x00a5:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r0 = r1
            r1 = r5
            goto L_0x00b9
        L_0x00ad:
            r1 = r0
            goto L_0x00c4
        L_0x00af:
            r1 = r0
            goto L_0x00d1
        L_0x00b1:
            r9 = move-exception
            r1 = r0
            r2 = r1
            r3 = r2
            r4 = r3
            r5 = r4
            r0 = r9
            r9 = r5
        L_0x00b9:
            a(r1, r3, r2, r4, r5)
            if (r9 == 0) goto L_0x00c1
            c(r9)
        L_0x00c1:
            throw r0
        L_0x00c2:
            r9 = r0
            r1 = r9
        L_0x00c4:
            r2 = r1
        L_0x00c5:
            r3 = r2
        L_0x00c6:
            r4 = r3
        L_0x00c7:
            r5 = r4
        L_0x00c8:
            r7 = r5
        L_0x00c9:
            a(r1, r3, r2, r4, r5)
            if (r9 == 0) goto L_0x00dc
            goto L_0x0063
        L_0x00cf:
            r9 = r0
            r1 = r9
        L_0x00d1:
            r2 = r1
        L_0x00d2:
            r3 = r2
        L_0x00d3:
            r4 = r3
        L_0x00d4:
            r5 = r4
        L_0x00d5:
            r7 = r5
        L_0x00d6:
            a(r1, r3, r2, r4, r5)
            if (r9 == 0) goto L_0x00dc
            goto L_0x0063
        L_0x00dc:
            if (r7 != 0) goto L_0x00df
            return r0
        L_0x00df:
            java.lang.String r9 = r7.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.g.a(java.lang.String[]):java.lang.String");
    }

    private static void a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException unused4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|4|5|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.Process r1) {
        /*
            int r0 = b(r1)
            if (r0 == 0) goto L_0x000d
            android.os.Process.killProcess(r0)     // Catch:{ Exception -> 0x000a }
            goto L_0x000d
        L_0x000a:
            r1.destroy()     // Catch:{ Exception -> 0x000d }
        L_0x000d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.g.a(java.lang.Process):void");
    }

    private static int b(Process process) {
        String obj = process.toString();
        try {
            return Integer.parseInt(obj.substring(obj.indexOf("=") + 1, obj.indexOf("]")));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            } catch (IllegalThreadStateException unused) {
                a(process);
            }
        }
    }
}
