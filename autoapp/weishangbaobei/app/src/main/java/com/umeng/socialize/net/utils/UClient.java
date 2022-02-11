package com.umeng.socialize.net.utils;

import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class UClient {
    private static final String END = "\r\n";
    private static final String TAG = "UClient";

    public <T extends UResponse> T execute(URequest uRequest, Class<T> cls) {
        ResponseObj responseObj;
        uRequest.onPrepareRequest();
        String trim = uRequest.getHttpMethod().trim();
        verifyMethod(trim);
        if (URequest.GET.equals(trim)) {
            responseObj = httpGetRequest(uRequest);
        } else {
            responseObj = URequest.POST.equals(trim) ? httpPostRequest(uRequest) : null;
        }
        return createResponse(responseObj, cls);
    }

    /* access modifiers changed from: protected */
    public <T extends UResponse> T createResponse(ResponseObj responseObj, Class<T> cls) {
        if (responseObj == null) {
            return null;
        }
        try {
            return (UResponse) cls.getConstructor(new Class[]{Integer.class, JSONObject.class}).newInstance(new Object[]{Integer.valueOf(responseObj.httpResponseCode), responseObj.jsonObject});
        } catch (SecurityException e) {
            Log.e(TAG, "SecurityException", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "NoSuchMethodException", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "IllegalArgumentException", e3);
            return null;
        } catch (InstantiationException e4) {
            Log.e(TAG, "InstantiationException", e4);
            return null;
        } catch (IllegalAccessException e5) {
            Log.e(TAG, "IllegalAccessException", e5);
            return null;
        } catch (InvocationTargetException e6) {
            Log.e(TAG, "InvocationTargetException", e6);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0102, code lost:
        if (r4.size() <= 0) goto L_0x0104;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.umeng.socialize.net.utils.UClient.ResponseObj httpPostRequest(com.umeng.socialize.net.utils.URequest r8) {
        /*
            r7 = this;
            org.json.JSONObject r0 = r8.toJson()
            if (r0 != 0) goto L_0x0009
            java.lang.String r0 = ""
            goto L_0x0011
        L_0x0009:
            org.json.JSONObject r0 = r8.toJson()
            java.lang.String r0 = r0.toString()
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "URequest  = "
            r1.append(r2)
            java.lang.Class r2 = r8.getClass()
            java.lang.String r2 = r2.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.umeng.socialize.utils.Log.net(r1)
            java.util.UUID r1 = java.util.UUID.randomUUID()
            java.lang.String r1 = r1.toString()
            r2 = 0
            java.net.HttpURLConnection r3 = r7.openUrlConnection(r8)     // Catch:{ IOException -> 0x01cb, all -> 0x01c7 }
            if (r3 != 0) goto L_0x0048
            r7.closeQuietly(r2)
            r7.closeQuietly(r2)
            if (r3 == 0) goto L_0x0047
            r3.disconnect()
        L_0x0047:
            return r2
        L_0x0048:
            java.util.Map r4 = r8.getBodyPair()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.net.utils.URequest$MIME r5 = r8.mMimeType     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            if (r5 == 0) goto L_0x0077
            java.lang.String r0 = "data"
            java.lang.Object r0 = r4.get(r0)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = "Content-Type"
            com.umeng.socialize.net.utils.URequest$MIME r4 = r8.mMimeType     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r3.setRequestProperty(r1, r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.OutputStream r1 = r3.getOutputStream()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r1.write(r0)     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            goto L_0x016e
        L_0x0070:
            r8 = move-exception
            goto L_0x01e4
        L_0x0073:
            r8 = move-exception
        L_0x0074:
            r0 = r2
            goto L_0x01cf
        L_0x0077:
            com.umeng.socialize.net.utils.URequest$PostStyle r5 = r8.postStyle     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.net.utils.URequest$PostStyle r6 = com.umeng.socialize.net.utils.URequest.PostStyle.APPLICATION     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            if (r5 != r6) goto L_0x00fc
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r5 = "message:"
            r1.append(r5)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.append(r0)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.utils.Log.net(r0)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = "application/x-www-form-urlencoded"
            r3.setRequestProperty(r0, r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            android.net.Uri$Builder r0 = new android.net.Uri$Builder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r0.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.util.Set r1 = r4.keySet()     // Catch:{ Throwable -> 0x00bd }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x00bd }
        L_0x00a5:
            boolean r5 = r1.hasNext()     // Catch:{ Throwable -> 0x00bd }
            if (r5 == 0) goto L_0x00e0
            java.lang.Object r5 = r1.next()     // Catch:{ Throwable -> 0x00bd }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x00bd }
            java.lang.Object r6 = r4.get(r5)     // Catch:{ Throwable -> 0x00bd }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00bd }
            r0.appendQueryParameter(r5, r6)     // Catch:{ Throwable -> 0x00bd }
            goto L_0x00a5
        L_0x00bd:
            r1 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r4.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r5 = com.umeng.socialize.utils.UmengText.UPLOADFAIL     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r4.append(r5)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r5 = "["
            r4.append(r5)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r4.append(r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = "]"
            r4.append(r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.utils.Log.um(r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
        L_0x00e0:
            android.net.Uri r0 = r0.build()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r0 = r0.getEncodedQuery()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.OutputStream r4 = r3.getOutputStream()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            if (r0 == 0) goto L_0x016e
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r1.write(r0)     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            goto L_0x016e
        L_0x00fc:
            if (r4 == 0) goto L_0x0104
            int r4 = r4.size()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            if (r4 > 0) goto L_0x010a
        L_0x0104:
            com.umeng.socialize.net.utils.URequest$PostStyle r4 = r8.postStyle     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.net.utils.URequest$PostStyle r5 = com.umeng.socialize.net.utils.URequest.PostStyle.MULTIPART     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            if (r4 != r5) goto L_0x0131
        L_0x010a:
            java.lang.String r0 = "Content-Type"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r4.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r5 = "multipart/form-data; boundary="
            r4.append(r5)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r4.append(r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r3.setRequestProperty(r0, r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.OutputStream r0 = r3.getOutputStream()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r7.addBodyParams(r8, r0, r1)     // Catch:{ IOException -> 0x012d, all -> 0x0129 }
            r1 = r0
            goto L_0x016e
        L_0x0129:
            r8 = move-exception
            r1 = r0
            goto L_0x01e4
        L_0x012d:
            r8 = move-exception
            r1 = r0
            goto L_0x0074
        L_0x0131:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r4 = "message:"
            r1.append(r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.append(r0)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            com.umeng.socialize.utils.Log.net(r1)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r1 = "Content-Type"
            java.lang.String r4 = "application/x-www-form-urlencoded"
            r3.setRequestProperty(r1, r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            android.net.Uri$Builder r1 = new android.net.Uri$Builder     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.<init>()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r4 = "content"
            r1.appendQueryParameter(r4, r0)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            android.net.Uri r0 = r1.build()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.lang.String r0 = r0.getEncodedQuery()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            java.io.OutputStream r4 = r3.getOutputStream()     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x01c3, all -> 0x01c0 }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r1.write(r0)     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
        L_0x016e:
            r1.flush()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            int r0 = r3.getResponseCode()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            com.umeng.socialize.net.utils.UClient$ResponseObj r4 = new com.umeng.socialize.net.utils.UClient$ResponseObj     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r4.<init>()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r4.httpResponseCode = r0     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r0 != r5) goto L_0x01b4
            java.io.InputStream r0 = r3.getInputStream()     // Catch:{ IOException -> 0x0073, all -> 0x0070 }
            java.lang.String r5 = r3.getContentEncoding()     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r6 = r3.getRequestMethod()     // Catch:{ IOException -> 0x01b2 }
            org.json.JSONObject r8 = r7.parseResult(r8, r6, r5, r0)     // Catch:{ IOException -> 0x01b2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01b2 }
            r5.<init>()     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r6 = "requestMethod:POST;json data:"
            r5.append(r6)     // Catch:{ IOException -> 0x01b2 }
            r5.append(r8)     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x01b2 }
            com.umeng.socialize.utils.Log.net(r5)     // Catch:{ IOException -> 0x01b2 }
            r4.jsonObject = r8     // Catch:{ IOException -> 0x01b2 }
            r7.closeQuietly(r0)
            r7.closeQuietly(r1)
            if (r3 == 0) goto L_0x01b1
            r3.disconnect()
        L_0x01b1:
            return r4
        L_0x01b2:
            r8 = move-exception
            goto L_0x01cf
        L_0x01b4:
            r7.closeQuietly(r2)
            r7.closeQuietly(r1)
            if (r3 == 0) goto L_0x01bf
            r3.disconnect()
        L_0x01bf:
            return r2
        L_0x01c0:
            r8 = move-exception
            r1 = r2
            goto L_0x01e4
        L_0x01c3:
            r8 = move-exception
            r0 = r2
            r1 = r0
            goto L_0x01cf
        L_0x01c7:
            r8 = move-exception
            r1 = r2
            r3 = r1
            goto L_0x01e4
        L_0x01cb:
            r8 = move-exception
            r0 = r2
            r1 = r0
            r3 = r1
        L_0x01cf:
            java.lang.String r4 = "UClient"
            java.lang.String r5 = "Caught Exception in httpPostRequest()"
            com.umeng.socialize.utils.Log.e(r4, r5, r8)     // Catch:{ all -> 0x01e2 }
            r7.closeQuietly(r0)
            r7.closeQuietly(r1)
            if (r3 == 0) goto L_0x01e1
            r3.disconnect()
        L_0x01e1:
            return r2
        L_0x01e2:
            r8 = move-exception
            r2 = r0
        L_0x01e4:
            r7.closeQuietly(r2)
            r7.closeQuietly(r1)
            if (r3 == 0) goto L_0x01ef
            r3.disconnect()
        L_0x01ef:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.httpPostRequest(com.umeng.socialize.net.utils.URequest):com.umeng.socialize.net.utils.UClient$ResponseObj");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.umeng.socialize.net.utils.UClient.ResponseObj httpGetRequest(com.umeng.socialize.net.utils.URequest r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "URequest  = "
            r0.append(r1)
            java.lang.Class r1 = r7.getClass()
            java.lang.String r1 = r1.getName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.umeng.socialize.utils.Log.net(r0)
            r0 = 0
            java.net.HttpURLConnection r1 = r6.openUrlConnection(r7)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r1 != 0) goto L_0x002c
            r6.closeQuietly(r0)
            if (r1 == 0) goto L_0x002b
            r1.disconnect()
        L_0x002b:
            return r0
        L_0x002c:
            int r2 = r1.getResponseCode()     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            com.umeng.socialize.net.utils.UClient$ResponseObj r3 = new com.umeng.socialize.net.utils.UClient$ResponseObj     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            r3.<init>()     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            r3.httpResponseCode = r2     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x006c
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            java.lang.String r4 = r1.getContentEncoding()     // Catch:{ Exception -> 0x006a }
            java.lang.String r5 = r1.getRequestMethod()     // Catch:{ Exception -> 0x006a }
            org.json.JSONObject r7 = r6.parseResult(r7, r5, r4, r2)     // Catch:{ Exception -> 0x006a }
            r3.jsonObject = r7     // Catch:{ Exception -> 0x006a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006a }
            r4.<init>()     // Catch:{ Exception -> 0x006a }
            java.lang.String r5 = "result  = "
            r4.append(r5)     // Catch:{ Exception -> 0x006a }
            r4.append(r7)     // Catch:{ Exception -> 0x006a }
            java.lang.String r7 = r4.toString()     // Catch:{ Exception -> 0x006a }
            com.umeng.socialize.utils.Log.net(r7)     // Catch:{ Exception -> 0x006a }
            r6.closeQuietly(r2)
            if (r1 == 0) goto L_0x0069
            r1.disconnect()
        L_0x0069:
            return r3
        L_0x006a:
            r7 = move-exception
            goto L_0x0082
        L_0x006c:
            r6.closeQuietly(r0)
            if (r1 == 0) goto L_0x0074
            r1.disconnect()
        L_0x0074:
            return r0
        L_0x0075:
            r7 = move-exception
            r2 = r0
            goto L_0x0093
        L_0x0078:
            r7 = move-exception
            r2 = r0
            goto L_0x0082
        L_0x007b:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0093
        L_0x007f:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x0082:
            java.lang.String r3 = "UClient"
            java.lang.String r4 = "Caught Exception in httpGetRequest()"
            com.umeng.socialize.utils.Log.e(r3, r4, r7)     // Catch:{ all -> 0x0092 }
            r6.closeQuietly(r2)
            if (r1 == 0) goto L_0x0091
            r1.disconnect()
        L_0x0091:
            return r0
        L_0x0092:
            r7 = move-exception
        L_0x0093:
            r6.closeQuietly(r2)
            if (r1 == 0) goto L_0x009b
            r1.disconnect()
        L_0x009b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.httpGetRequest(com.umeng.socialize.net.utils.URequest):com.umeng.socialize.net.utils.UClient$ResponseObj");
    }

    private HttpURLConnection openUrlConnection(URequest uRequest) throws IOException {
        String str;
        HttpURLConnection httpURLConnection;
        String trim = uRequest.getHttpMethod().trim();
        if (URequest.GET.equals(trim)) {
            str = uRequest.toGetUrl();
        } else {
            str = URequest.POST.equals(trim) ? uRequest.mBaseUrl : null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        URL url = new URL(str);
        if ("https".equals(url.getProtocol())) {
            httpURLConnection = (HttpsURLConnection) url.openConnection();
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(Config.connectionTimeOut);
        httpURLConnection.setReadTimeout(Config.readSocketTimeOut);
        httpURLConnection.setRequestMethod(trim);
        if (URequest.GET.equals(trim)) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            if (uRequest.mHeaders != null && uRequest.mHeaders.size() > 0) {
                for (String next : uRequest.mHeaders.keySet()) {
                    httpURLConnection.setRequestProperty(next, uRequest.mHeaders.get(next));
                }
            }
        } else if (URequest.POST.equals(trim)) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    private void verifyMethod(String str) {
        if (TextUtils.isEmpty(str) || !(URequest.GET.equals(str.trim()) ^ URequest.POST.equals(str.trim()))) {
            throw new RuntimeException(UmengText.netMethodError(str));
        }
    }

    private void addBodyParams(URequest uRequest, OutputStream outputStream, String str) throws IOException {
        boolean z;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> bodyPair = uRequest.getBodyPair();
        for (String next : bodyPair.keySet()) {
            if (bodyPair.get(next) != null) {
                addFormField(sb, next, bodyPair.get(next).toString(), str);
            }
        }
        if (sb.length() > 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(sb.toString().getBytes());
            outputStream = dataOutputStream;
            z = true;
        } else {
            z = false;
        }
        Map<String, URequest.FilePair> filePair = uRequest.getFilePair();
        if (filePair != null && filePair.size() > 0) {
            for (String str2 : filePair.keySet()) {
                URequest.FilePair filePair2 = filePair.get(str2);
                byte[] bArr = filePair2.mBinaryData;
                if (bArr != null && bArr.length >= 1) {
                    addFilePart(filePair2.mFileName, bArr, str, outputStream);
                    z = true;
                }
            }
        }
        if (z) {
            finishWrite(outputStream, str);
        }
    }

    private void addFormField(StringBuilder sb, String str, String str2, String str3) {
        sb.append("--");
        sb.append(str3);
        sb.append(END);
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(str);
        sb.append("\"");
        sb.append(END);
        sb.append("Content-Type: text/plain; charset=");
        sb.append("UTF-8");
        sb.append(END);
        sb.append(END);
        sb.append(str2);
        sb.append(END);
    }

    private void addFilePart(String str, byte[] bArr, String str2, OutputStream outputStream) throws IOException {
        outputStream.write(("--" + str2 + END + "Content-Disposition: form-data; name=\"" + SocializeConstants.KEY_PIC + "\"; filename=\"" + str + "\"" + END + "Content-Type: " + "application/octet-stream" + END + "Content-Transfer-Encoding: binary" + END + END).getBytes());
        outputStream.write(bArr);
        outputStream.write(END.getBytes());
    }

    private void finishWrite(OutputStream outputStream, String str) throws IOException {
        outputStream.write(END.getBytes());
        outputStream.write(("--" + str + "--").getBytes());
        outputStream.write(END.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r4 = decryptData(r4, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        closeQuietly(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        return r4;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject parseResult(com.umeng.socialize.net.utils.URequest r4, java.lang.String r5, java.lang.String r6, java.io.InputStream r7) {
        /*
            r3 = this;
            r0 = 0
            java.io.InputStream r6 = r3.wrapStream(r6, r7)     // Catch:{ IOException -> 0x005f, all -> 0x005c }
            java.lang.String r7 = r3.convertStreamToString(r6)     // Catch:{ IOException -> 0x005a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x005a }
            r1.<init>()     // Catch:{ IOException -> 0x005a }
            java.lang.String r2 = "requestMethod:"
            r1.append(r2)     // Catch:{ IOException -> 0x005a }
            r1.append(r5)     // Catch:{ IOException -> 0x005a }
            java.lang.String r2 = ";origin data:"
            r1.append(r2)     // Catch:{ IOException -> 0x005a }
            r1.append(r7)     // Catch:{ IOException -> 0x005a }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x005a }
            com.umeng.socialize.utils.Log.net(r1)     // Catch:{ IOException -> 0x005a }
            java.lang.String r1 = "POST"
            boolean r1 = r1.equals(r5)     // Catch:{ IOException -> 0x005a }
            if (r1 == 0) goto L_0x003e
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x0036 }
            r5.<init>(r7)     // Catch:{ Exception -> 0x0036 }
            r3.closeQuietly(r6)
            return r5
        L_0x0036:
            org.json.JSONObject r4 = r3.decryptData(r4, r7)     // Catch:{ IOException -> 0x005a }
            r3.closeQuietly(r6)
            return r4
        L_0x003e:
            java.lang.String r1 = "GET"
            boolean r5 = r1.equals(r5)     // Catch:{ IOException -> 0x005a }
            if (r5 == 0) goto L_0x0068
            boolean r5 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IOException -> 0x005a }
            if (r5 == 0) goto L_0x0050
            r3.closeQuietly(r6)
            return r0
        L_0x0050:
            org.json.JSONObject r4 = r3.decryptData(r4, r7)     // Catch:{ IOException -> 0x005a }
            r3.closeQuietly(r6)
            return r4
        L_0x0058:
            r4 = move-exception
            goto L_0x006c
        L_0x005a:
            r4 = move-exception
            goto L_0x0061
        L_0x005c:
            r4 = move-exception
            r6 = r0
            goto L_0x006c
        L_0x005f:
            r4 = move-exception
            r6 = r0
        L_0x0061:
            java.lang.String r5 = "UClient"
            java.lang.String r7 = "Caught IOException in parseResult()"
            com.umeng.socialize.utils.Log.e(r5, r7, r4)     // Catch:{ all -> 0x0058 }
        L_0x0068:
            r3.closeQuietly(r6)
            return r0
        L_0x006c:
            r3.closeQuietly(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.parseResult(com.umeng.socialize.net.utils.URequest, java.lang.String, java.lang.String, java.io.InputStream):org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public InputStream wrapStream(String str, InputStream inputStream) throws IOException {
        if (str == null || "identity".equalsIgnoreCase(str)) {
            return inputStream;
        }
        if ("gzip".equalsIgnoreCase(str)) {
            return new GZIPInputStream(inputStream);
        }
        if ("deflate".equalsIgnoreCase(str)) {
            return new InflaterInputStream(inputStream, new Inflater(false), WXMediaMessage.TITLE_LENGTH_LIMIT);
        }
        throw new RuntimeException("unsupported content-encoding: " + str);
    }

    /* access modifiers changed from: protected */
    public String convertStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, WXMediaMessage.TITLE_LENGTH_LIMIT);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + "\n");
                } else {
                    closeQuietly(inputStreamReader);
                    closeQuietly(bufferedReader);
                    return sb.toString();
                }
            } catch (IOException e) {
                Log.e(TAG, "Caught IOException in convertStreamToString()", e);
                closeQuietly(inputStreamReader);
                closeQuietly(bufferedReader);
                return null;
            } catch (Throwable th) {
                closeQuietly(inputStreamReader);
                closeQuietly(bufferedReader);
                throw th;
            }
        }
    }

    private JSONObject decryptData(URequest uRequest, String str) {
        try {
            return new JSONObject(uRequest.getDecryptString(str));
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception in decryptData()", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, "Caught IOException in closeQuietly()", e);
            }
        }
    }

    protected static class ResponseObj {
        public int httpResponseCode;
        public JSONObject jsonObject;

        protected ResponseObj() {
        }
    }
}
