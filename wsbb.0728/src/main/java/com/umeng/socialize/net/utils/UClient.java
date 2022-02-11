package com.umeng.socialize.net.utils;

import android.net.Uri;
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
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class UClient {
    private static final String END = "\r\n";
    private static final String TAG = "UClient";

    protected static class ResponseObj {
        public int httpResponseCode;
        public JSONObject jsonObject;

        protected ResponseObj() {
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
            z = true;
            outputStream = dataOutputStream;
        } else {
            z = false;
        }
        Map<String, URequest.FilePair> filePair = uRequest.getFilePair();
        if (filePair != null && filePair.size() > 0) {
            boolean z2 = z;
            for (String str2 : filePair.keySet()) {
                URequest.FilePair filePair2 = filePair.get(str2);
                byte[] bArr = filePair2.mBinaryData;
                if (bArr != null && bArr.length >= 1) {
                    addFilePart(filePair2.mFileName, bArr, str, outputStream);
                    z2 = true;
                }
            }
            z = z2;
        }
        if (z) {
            finishWrite(outputStream, str);
        }
    }

    private void addFilePart(String str, byte[] bArr, String str2, OutputStream outputStream) throws IOException {
        outputStream.write(("--" + str2 + END + "Content-Disposition: form-data; name=\"" + SocializeConstants.KEY_PIC + "\"; filename=\"" + str + "\"" + END + "Content-Type: " + "application/octet-stream" + END + "Content-Transfer-Encoding: binary" + END + END).getBytes());
        outputStream.write(bArr);
        outputStream.write(END.getBytes());
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

    private JSONObject decryptData(URequest uRequest, String str) {
        try {
            return new JSONObject(uRequest.getDecryptString(str));
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception in decryptData()", e);
            return null;
        }
    }

    private void finishWrite(OutputStream outputStream, String str) throws IOException {
        outputStream.write(END.getBytes());
        outputStream.write(("--" + str + "--").getBytes());
        outputStream.write(END.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    private ResponseObj httpGetRequest(URequest uRequest) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        InputStream inputStream2;
        Log.net("URequest  = " + uRequest.getClass().getName());
        try {
            httpURLConnection = openUrlConnection(uRequest);
            if (httpURLConnection == null) {
                closeQuietly((Closeable) null);
                if (httpURLConnection == null) {
                    return null;
                }
                httpURLConnection.disconnect();
                return null;
            }
            try {
                int responseCode = httpURLConnection.getResponseCode();
                ResponseObj responseObj = new ResponseObj();
                responseObj.httpResponseCode = responseCode;
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        JSONObject parseResult = parseResult(uRequest, httpURLConnection.getRequestMethod(), httpURLConnection.getContentEncoding(), inputStream);
                        responseObj.jsonObject = parseResult;
                        Log.net("result  = " + parseResult);
                        closeQuietly(inputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return responseObj;
                    } catch (Exception e) {
                        e = e;
                        try {
                            Log.e(TAG, "Caught Exception in httpGetRequest()", e);
                            closeQuietly(inputStream);
                            if (httpURLConnection != null) {
                            }
                        } catch (Throwable th) {
                            inputStream2 = inputStream;
                            th = th;
                            closeQuietly(inputStream2);
                            if (httpURLConnection != null) {
                            }
                            throw th;
                        }
                    }
                } else {
                    closeQuietly((Closeable) null);
                    if (httpURLConnection == null) {
                        return null;
                    }
                    httpURLConnection.disconnect();
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                inputStream = null;
                Log.e(TAG, "Caught Exception in httpGetRequest()", e);
                closeQuietly(inputStream);
                if (httpURLConnection != null) {
                    return null;
                }
                httpURLConnection.disconnect();
                return null;
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = null;
                closeQuietly(inputStream2);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            httpURLConnection = null;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream2 = null;
            httpURLConnection = null;
            closeQuietly(inputStream2);
            if (httpURLConnection != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0165, code lost:
        if (r1.size() <= 0) goto L_0x0167;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    private ResponseObj httpPostRequest(URequest uRequest) {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        InputStream inputStream;
        InputStream inputStream2;
        String jSONObject = uRequest.toJson() == null ? "" : uRequest.toJson().toString();
        Log.net("URequest  = " + uRequest.getClass().getName());
        String uuid = UUID.randomUUID().toString();
        try {
            httpURLConnection = openUrlConnection(uRequest);
            if (httpURLConnection == null) {
                closeQuietly((Closeable) null);
                closeQuietly((Closeable) null);
                if (httpURLConnection == null) {
                    return null;
                }
                httpURLConnection.disconnect();
                return null;
            }
            try {
                Map<String, Object> bodyPair = uRequest.getBodyPair();
                if (uRequest.mMimeType != null) {
                    String str = (String) bodyPair.get("data");
                    httpURLConnection.setRequestProperty("Content-Type", uRequest.mMimeType.toString());
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        outputStream.write(str.getBytes());
                    } catch (IOException e) {
                        e = e;
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = null;
                        closeQuietly(inputStream2);
                        closeQuietly(outputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } else if (uRequest.postStyle == URequest.PostStyle.APPLICATION) {
                    Log.net("message:" + jSONObject);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    Uri.Builder builder = new Uri.Builder();
                    try {
                        for (String next : bodyPair.keySet()) {
                            builder.appendQueryParameter(next, bodyPair.get(next).toString());
                        }
                    } catch (Throwable th2) {
                        Log.um(UmengText.UPLOADFAIL + "[" + th2.getMessage() + "]");
                    }
                    String encodedQuery = builder.build().getEncodedQuery();
                    outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    if (encodedQuery != null) {
                        outputStream.write(encodedQuery.getBytes());
                    }
                } else {
                    if (bodyPair != null) {
                    }
                    if (uRequest.postStyle != URequest.PostStyle.MULTIPART) {
                        Log.net("message:" + jSONObject);
                        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        Uri.Builder builder2 = new Uri.Builder();
                        builder2.appendQueryParameter("content", jSONObject);
                        String encodedQuery2 = builder2.build().getEncodedQuery();
                        outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        outputStream.write(encodedQuery2.getBytes());
                    }
                    httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + uuid);
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        addBodyParams(uRequest, outputStream, uuid);
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream2 = null;
                        closeQuietly(inputStream2);
                        closeQuietly(outputStream);
                        if (httpURLConnection != null) {
                        }
                        throw th;
                    }
                }
                outputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                ResponseObj responseObj = new ResponseObj();
                responseObj.httpResponseCode = responseCode;
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        JSONObject parseResult = parseResult(uRequest, httpURLConnection.getRequestMethod(), httpURLConnection.getContentEncoding(), inputStream);
                        Log.net("requestMethod:POST;json data:" + parseResult);
                        responseObj.jsonObject = parseResult;
                        closeQuietly(inputStream);
                        closeQuietly(outputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return responseObj;
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            Log.e(TAG, "Caught Exception in httpPostRequest()", e);
                            closeQuietly(inputStream);
                            closeQuietly(outputStream);
                            if (httpURLConnection == null) {
                                return null;
                            }
                            httpURLConnection.disconnect();
                            return null;
                        } catch (Throwable th4) {
                            inputStream2 = inputStream;
                            outputStream = outputStream;
                            th = th4;
                            closeQuietly(inputStream2);
                            closeQuietly(outputStream);
                            if (httpURLConnection != null) {
                            }
                            throw th;
                        }
                    }
                } else {
                    closeQuietly((Closeable) null);
                    closeQuietly(outputStream);
                    if (httpURLConnection == null) {
                        return null;
                    }
                    httpURLConnection.disconnect();
                    return null;
                }
            } catch (IOException e4) {
                e = e4;
                outputStream = null;
                inputStream = null;
                Log.e(TAG, "Caught Exception in httpPostRequest()", e);
                closeQuietly(inputStream);
                closeQuietly(outputStream);
                if (httpURLConnection == null) {
                }
            } catch (Throwable th5) {
                th = th5;
                outputStream = null;
                inputStream2 = null;
                closeQuietly(inputStream2);
                closeQuietly(outputStream);
                if (httpURLConnection != null) {
                }
                throw th;
            }
            inputStream = null;
            Log.e(TAG, "Caught Exception in httpPostRequest()", e);
            closeQuietly(inputStream);
            closeQuietly(outputStream);
            if (httpURLConnection == null) {
            }
        } catch (IOException e5) {
            e = e5;
            httpURLConnection = null;
            outputStream = null;
            inputStream = null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            outputStream = null;
            inputStream2 = null;
            closeQuietly(inputStream2);
            closeQuietly(outputStream);
            if (httpURLConnection != null) {
            }
            throw th;
        }
    }

    private HttpURLConnection openUrlConnection(URequest uRequest) throws IOException {
        String trim = uRequest.getHttpMethod().trim();
        String getUrl = URequest.GET.equals(trim) ? uRequest.toGetUrl() : URequest.POST.equals(trim) ? uRequest.mBaseUrl : null;
        if (TextUtils.isEmpty(getUrl)) {
            return null;
        }
        URL url = new URL(getUrl);
        HttpsURLConnection httpsURLConnection = "https".equals(url.getProtocol()) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
        httpsURLConnection.setConnectTimeout(Config.connectionTimeOut);
        httpsURLConnection.setReadTimeout(Config.readSocketTimeOut);
        httpsURLConnection.setRequestMethod(trim);
        if (URequest.GET.equals(trim)) {
            httpsURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            if (uRequest.mHeaders != null && uRequest.mHeaders.size() > 0) {
                for (String next : uRequest.mHeaders.keySet()) {
                    httpsURLConnection.setRequestProperty(next, uRequest.mHeaders.get(next));
                }
            }
        } else if (URequest.POST.equals(trim)) {
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
        }
        return httpsURLConnection;
    }

    private void verifyMethod(String str) {
        if (TextUtils.isEmpty(str) || !(URequest.GET.equals(str.trim()) ^ URequest.POST.equals(str.trim()))) {
            throw new RuntimeException(UmengText.netMethodError(str));
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

    public <T extends UResponse> T execute(URequest uRequest, Class<T> cls) {
        uRequest.onPrepareRequest();
        String trim = uRequest.getHttpMethod().trim();
        verifyMethod(trim);
        return createResponse(URequest.GET.equals(trim) ? httpGetRequest(uRequest) : URequest.POST.equals(trim) ? httpPostRequest(uRequest) : null, cls);
    }

    /* access modifiers changed from: protected */
    public JSONObject parseResult(URequest uRequest, String str, String str2, InputStream inputStream) {
        InputStream inputStream2;
        InputStream inputStream3 = null;
        try {
            inputStream2 = wrapStream(str2, inputStream);
            try {
                String convertStreamToString = convertStreamToString(inputStream2);
                Log.net("requestMethod:" + str + ";origin data:" + convertStreamToString);
                if ("POST".equals(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(convertStreamToString);
                        closeQuietly(inputStream2);
                        return jSONObject;
                    } catch (Exception e) {
                        JSONObject decryptData = decryptData(uRequest, convertStreamToString);
                        closeQuietly(inputStream2);
                        return decryptData;
                    }
                } else {
                    if ("GET".equals(str)) {
                        if (TextUtils.isEmpty(convertStreamToString)) {
                            closeQuietly(inputStream2);
                            return null;
                        }
                        JSONObject decryptData2 = decryptData(uRequest, convertStreamToString);
                        closeQuietly(inputStream2);
                        return decryptData2;
                    }
                    closeQuietly(inputStream2);
                    return null;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            inputStream2 = null;
        } catch (Throwable th) {
            th = th;
            closeQuietly(inputStream3);
            throw th;
        }
        try {
            Log.e(TAG, "Caught IOException in parseResult()", e);
            closeQuietly(inputStream2);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream3 = inputStream2;
            closeQuietly(inputStream3);
            throw th;
        }
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
}
