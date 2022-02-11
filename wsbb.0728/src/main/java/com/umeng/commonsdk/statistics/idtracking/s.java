package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

public class s extends a {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b = null;
    private String c = null;
    private String d = null;

    public s(Context context) {
        super(a);
        this.b = context;
        this.c = null;
        this.d = null;
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (Exception e2) {
            return str2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00de, code lost:
        if (r0 != null) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e0, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0103, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x011c, code lost:
        if (r0 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0139, code lost:
        r3 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d6 A[SYNTHETIC, Splitter:B:32:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00db A[SYNTHETIC, Splitter:B:35:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f4 A[SYNTHETIC, Splitter:B:46:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9 A[SYNTHETIC, Splitter:B:49:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fe A[SYNTHETIC, Splitter:B:52:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0119 A[SYNTHETIC, Splitter:B:67:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0139 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x00a3] */
    private String b(String str) {
        DataOutputStream dataOutputStream;
        HttpsURLConnection httpsURLConnection;
        BufferedReader bufferedReader;
        Throwable th;
        InputStream inputStream;
        String str2;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        this.d = a("ro.yunos.openuuid", "");
        if (!TextUtils.isEmpty(this.d)) {
            return this.d;
        }
        this.c = a("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(this.c)) {
            this.c = a("ro.sys.aliyun.clouduuid", "");
        }
        if (!TextUtils.isEmpty(this.c)) {
            try {
                httpsURLConnection = (HttpsURLConnection) new URL("https://cmnsguider.yunos.com:443/genDeviceToken").openConnection();
                try {
                    httpsURLConnection.setConnectTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                    httpsURLConnection.setReadTimeout(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setUseCaches(false);
                    httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String str, SSLSession sSLSession) {
                            return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sSLSession);
                        }
                    });
                    str2 = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                    dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                } catch (Exception e2) {
                    dataOutputStream = null;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = null;
                    bufferedReader = null;
                    th = th;
                    inputStream = null;
                    if (dataOutputStream != null) {
                    }
                    if (bufferedReader != null) {
                    }
                    if (inputStream != null) {
                    }
                    if (httpsURLConnection != null) {
                    }
                    throw th;
                }
                try {
                    dataOutputStream.writeBytes(str2);
                    dataOutputStream.flush();
                    if (httpsURLConnection.getResponseCode() == 200) {
                        inputStream = httpsURLConnection.getInputStream();
                        try {
                            BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(inputStream));
                            try {
                                StringBuffer stringBuffer = new StringBuffer();
                                while (true) {
                                    String readLine = bufferedReader4.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    stringBuffer.append(readLine);
                                }
                                this.d = stringBuffer.toString();
                                bufferedReader3 = bufferedReader4;
                            } catch (Exception e4) {
                                bufferedReader3 = bufferedReader4;
                            } catch (Throwable th3) {
                                bufferedReader = bufferedReader4;
                                th = th3;
                                if (dataOutputStream != null) {
                                }
                                if (bufferedReader != null) {
                                }
                                if (inputStream != null) {
                                }
                                if (httpsURLConnection != null) {
                                }
                                throw th;
                            }
                        } catch (Exception e5) {
                        } catch (Throwable th4) {
                            bufferedReader = null;
                            th = th4;
                            if (dataOutputStream != null) {
                            }
                            if (bufferedReader != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (httpsURLConnection != null) {
                            }
                            throw th;
                        }
                        bufferedReader2 = bufferedReader3;
                        dataOutputStream.close();
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e6) {
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e7) {
                            }
                        }
                    }
                    bufferedReader2 = null;
                    inputStream = null;
                    try {
                        dataOutputStream.close();
                    } catch (Exception e8) {
                    }
                    if (bufferedReader2 != null) {
                    }
                    if (inputStream != null) {
                    }
                } catch (Exception e9) {
                    if (dataOutputStream != null) {
                    }
                } catch (Throwable th5) {
                }
            } catch (Exception e10) {
                dataOutputStream = null;
                httpsURLConnection = null;
                if (dataOutputStream != null) {
                }
            } catch (Throwable th6) {
                th = th6;
                dataOutputStream = null;
                httpsURLConnection = null;
                bufferedReader = null;
                th = th;
                inputStream = null;
                if (dataOutputStream != null) {
                }
                if (bufferedReader != null) {
                }
                if (inputStream != null) {
                }
                if (httpsURLConnection != null) {
                }
                throw th;
            }
        }
        return this.d;
    }

    public String f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor edit;
        try {
            if (!(TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.b)) == null)) {
                String string = sharedPreferences.getString(e, "");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
                this.d = b(f);
                if (!(TextUtils.isEmpty(this.d) || this.b == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null)) {
                    edit.putString(e, this.d).commit();
                }
                return this.d;
            }
        } catch (Exception e2) {
        }
        return null;
    }
}
