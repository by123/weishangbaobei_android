package com.umeng.socialize.net.utils;

import android.os.Bundle;
import com.umeng.socialize.utils.ContextUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class SocializeNetUtils {
    private static final String TAG = "SocializeNetUtils";

    public static boolean isConSpeCharacters(String str) {
        return str.replaceAll("[一-龥]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() != 0;
    }

    public static boolean isSelfAppkey(String str) {
        return str.equals("5126ff896c738f2bfa000438") && !ContextUtil.getPackageName().equals("com.umeng.soexample");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ae, code lost:
        if (r0 != null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00bb, code lost:
        if (r0 == null) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c5, code lost:
        if (r0 != null) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00d2, code lost:
        if (r0 == null) goto L_0x00d5;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ab A[SYNTHETIC, Splitter:B:46:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00c2 A[SYNTHETIC, Splitter:B:63:0x00c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getNetData(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x008e, all -> 0x008a }
            r0.<init>()     // Catch:{ Exception -> 0x008e, all -> 0x008a }
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r3 = 1
            r2.setInstanceFollowRedirects(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            int r3 = com.umeng.socialize.Config.connectionTimeOut     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.setConnectTimeout(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            int r3 = com.umeng.socialize.Config.readSocketTimeOut     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.setReadTimeout(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            int r3 = r2.getResponseCode()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4 = 301(0x12d, float:4.22E-43)
            if (r3 != r4) goto L_0x0045
            java.lang.String r3 = "Location"
            java.lang.String r2 = r2.getHeaderField(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            boolean r6 = r2.equals(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            if (r6 == 0) goto L_0x0040
            java.lang.String r6 = com.umeng.socialize.utils.UmengText.NET_AGAIN_ERROR     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            com.umeng.socialize.utils.Log.um(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            return r1
        L_0x0040:
            byte[] r6 = getNetData(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            return r6
        L_0x0045:
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.String r3 = "image"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082 }
            r4.<init>()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r5 = "getting image from url"
            r4.append(r5)     // Catch:{ Exception -> 0x0082 }
            r4.append(r6)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r6 = r4.toString()     // Catch:{ Exception -> 0x0082 }
            com.umeng.socialize.utils.Log.d(r3, r6)     // Catch:{ Exception -> 0x0082 }
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0082 }
        L_0x0063:
            int r3 = r2.read(r6)     // Catch:{ Exception -> 0x0082 }
            r4 = -1
            if (r3 == r4) goto L_0x006f
            r4 = 0
            r0.write(r6, r4, r3)     // Catch:{ Exception -> 0x0082 }
            goto L_0x0063
        L_0x006f:
            byte[] r6 = r0.toByteArray()     // Catch:{ Exception -> 0x0082 }
            if (r2 == 0) goto L_0x0081
            r2.close()     // Catch:{ IOException -> 0x0078, all -> 0x007c }
        L_0x0078:
            r0.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0081
        L_0x007c:
            r6 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            throw r6
        L_0x0081:
            return r6
        L_0x0082:
            r6 = move-exception
            goto L_0x0091
        L_0x0084:
            r6 = move-exception
            r2 = r1
            goto L_0x00c0
        L_0x0087:
            r6 = move-exception
            r2 = r1
            goto L_0x0091
        L_0x008a:
            r6 = move-exception
            r0 = r1
            r2 = r0
            goto L_0x00c0
        L_0x008e:
            r6 = move-exception
            r0 = r1
            r2 = r0
        L_0x0091:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r3.<init>()     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = com.umeng.socialize.utils.UmengText.IMAGE_DOWNLOAD_ERROR     // Catch:{ all -> 0x00bf }
            r3.append(r4)     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x00bf }
            r3.append(r6)     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00bf }
            com.umeng.socialize.utils.Log.um(r6)     // Catch:{ all -> 0x00bf }
            if (r2 == 0) goto L_0x00be
            r2.close()     // Catch:{ IOException -> 0x00bb, all -> 0x00b4 }
            if (r0 == 0) goto L_0x00be
        L_0x00b0:
            r0.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00be
        L_0x00b4:
            r6 = move-exception
            if (r0 == 0) goto L_0x00ba
            r0.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00ba:
            throw r6
        L_0x00bb:
            if (r0 == 0) goto L_0x00be
            goto L_0x00b0
        L_0x00be:
            return r1
        L_0x00bf:
            r6 = move-exception
        L_0x00c0:
            if (r2 == 0) goto L_0x00d5
            r2.close()     // Catch:{ IOException -> 0x00d2, all -> 0x00cb }
            if (r0 == 0) goto L_0x00d5
        L_0x00c7:
            r0.close()     // Catch:{ IOException -> 0x00d5 }
            goto L_0x00d5
        L_0x00cb:
            r6 = move-exception
            if (r0 == 0) goto L_0x00d1
            r0.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00d1:
            throw r6
        L_0x00d2:
            if (r0 == 0) goto L_0x00d5
            goto L_0x00c7
        L_0x00d5:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.SocializeNetUtils.getNetData(java.lang.String):byte[]");
    }

    public static boolean startWithHttp(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    public static Bundle parseUri(String str) {
        try {
            return decodeUrl(new URI(str).getQuery());
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                try {
                    bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static String request(String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection == null) {
                return "";
            }
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            if (inputStream == null) {
                return "";
            }
            return convertStreamToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + "/n");
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                inputStream.close();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        inputStream.close();
        return sb.toString();
    }
}
