package com.umeng.socialize.net.utils;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class SocializeNetUtils {
    private static final String TAG = "SocializeNetUtils";

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + "/n");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
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

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b5, code lost:
        if (r1 != null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00d6, code lost:
        if (r1 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005c A[SYNTHETIC, Splitter:B:15:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b2 A[SYNTHETIC, Splitter:B:45:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    public static byte[] getNetData(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setConnectTimeout(Config.connectionTimeOut);
                httpURLConnection.setReadTimeout(Config.readSocketTimeOut);
                if (httpURLConnection.getResponseCode() == 301) {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (!headerField.equals(str)) {
                        return getNetData(headerField);
                    }
                    Log.um(UmengText.NET_AGAIN_ERROR);
                    return null;
                }
                inputStream = httpURLConnection.getInputStream();
                try {
                    Log.d("image", "getting image from url" + str);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    if (inputStream == null) {
                        return byteArray;
                    }
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    } catch (Throwable th) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                        }
                        throw th;
                    }
                    try {
                        byteArrayOutputStream.close();
                        return byteArray;
                    } catch (IOException e3) {
                        return byteArray;
                    }
                } catch (Exception e4) {
                    e = e4;
                    try {
                        Log.um(UmengText.IMAGE_DOWNLOAD_ERROR + e.getMessage());
                        if (inputStream == null) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e5) {
                            } catch (Throwable th3) {
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                throw th3;
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e7) {
                e = e7;
                inputStream = null;
                Log.um(UmengText.IMAGE_DOWNLOAD_ERROR + e.getMessage());
                if (inputStream == null) {
                    return null;
                }
                try {
                    inputStream.close();
                    if (byteArrayOutputStream == null) {
                        return null;
                    }
                } catch (IOException e8) {
                    if (byteArrayOutputStream == null) {
                        return null;
                    }
                } catch (Throwable th4) {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e9) {
                        }
                    }
                    throw th4;
                }
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (IOException e10) {
                    return null;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
                if (inputStream != null) {
                }
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
            byteArrayOutputStream = null;
            inputStream = null;
            Log.um(UmengText.IMAGE_DOWNLOAD_ERROR + e.getMessage());
            if (inputStream == null) {
            }
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            byteArrayOutputStream = null;
            if (inputStream != null) {
            }
            throw th;
        }
    }

    public static boolean isConSpeCharacters(String str) {
        return str.replaceAll("[一-龥]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() != 0;
    }

    public static boolean isSelfAppkey(String str) {
        return str.equals("5126ff896c738f2bfa000438") && !ContextUtil.getPackageName().equals("com.umeng.soexample");
    }

    public static Bundle parseUri(String str) {
        try {
            return decodeUrl(new URI(str).getQuery());
        } catch (Exception e) {
            return new Bundle();
        }
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static String request(String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection == null) {
                return "";
            }
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            return inputStream == null ? "" : convertStreamToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean startWithHttp(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }
}
