package com.meiqia.core.a;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.meiqia.core.MQManager;
import com.meiqia.core.g;
import com.umeng.analytics.pro.b;
import com.umeng.socialize.sina.params.ShareRequestParam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements Thread.UncaughtExceptionHandler {
    public static final MediaType a = MediaType.parse("application/json; charset=utf-8");
    private static final String b = e.class.getSimpleName();
    private static volatile e c;
    private Thread.UncaughtExceptionHandler d;
    private Context e;
    private OkHttpClient f = new OkHttpClient();

    private e(Context context) {
        this.e = context;
    }

    public static e a(Context context) {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e(context);
                }
            }
        }
        return c;
    }

    private void a(String str, Callback callback) {
        this.f.newCall(new Request.Builder().url("https://notify.bugsnag.com").post(RequestBody.create(a, str)).build()).enqueue(callback);
    }

    private void a(Throwable th) {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                long currentTimeMillis = System.currentTimeMillis();
                String b2 = b(th);
                if (!TextUtils.isEmpty(b2)) {
                    File d2 = d();
                    File file = new File(d2, "log_" + currentTimeMillis + ".txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                    printWriter.print(b2);
                    printWriter.close();
                }
            }
        } catch (Exception unused) {
            Log.e(b, "dump crash info failed");
        }
    }

    private String b(Throwable th) {
        String str;
        int i;
        JSONObject jSONObject = new JSONObject();
        i iVar = new i(this.e);
        String str2 = "";
        try {
            JSONObject jSONObject2 = new JSONObject();
            String name = th.getClass().getName();
            int i2 = 0;
            if (th.getStackTrace() == null || th.getStackTrace().length <= 0 || th.getStackTrace()[0] == null) {
                str = "";
                i = 0;
            } else {
                StackTraceElement stackTraceElement = th.getStackTrace()[0];
                String stackTraceElement2 = stackTraceElement.toString();
                str = stackTraceElement.getMethodName();
                int lineNumber = stackTraceElement.getLineNumber();
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i3 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement3 = stackTrace[i2];
                    String stackTraceElement4 = stackTraceElement3.toString();
                    if (!TextUtils.isEmpty(stackTraceElement4) && stackTraceElement4.contains("com.meiqia")) {
                        i3 = 1;
                    }
                    jSONObject2.put(String.valueOf(stackTraceElement3.getLineNumber()), stackTraceElement4);
                    i2++;
                }
                i2 = i3;
                String str3 = stackTraceElement2;
                i = lineNumber;
                str2 = str3;
            }
            if (i2 == 0) {
                return "";
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("name", "android sdk");
            jSONObject3.put("version", MQManager.getMeiqiaSDKVersion());
            jSONObject3.put("url", "http://meiqia.com/");
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("id", iVar.b());
            jSONObject4.put("channel", g.b());
            jSONObject4.put("appkey", iVar.a());
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("version", MQManager.getMeiqiaSDKVersion());
            jSONObject5.put("name", k.d(this.e));
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("osVersion", Build.VERSION.RELEASE);
            jSONObject6.put("hostname", Build.BRAND + " " + Build.MODEL + " " + Build.DEVICE);
            jSONObject6.put("manufacturer", Build.MANUFACTURER);
            jSONObject6.put("model", Build.MODEL);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject7 = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject8 = new JSONObject();
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("file", str2);
            jSONObject9.put("lineNumber", i);
            jSONObject9.put("method", str);
            jSONObject9.put(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, jSONObject2);
            jSONArray3.put(jSONObject9);
            jSONObject8.put("errorClass", name);
            jSONObject8.put("stacktrace", jSONArray3);
            jSONArray2.put(jSONObject8);
            jSONObject7.put("payloadVersion", "2");
            jSONObject7.put("exceptions", jSONArray2);
            jSONObject7.put("device", jSONObject6);
            jSONObject7.put("app", jSONObject5);
            jSONObject7.put("user", jSONObject4);
            jSONObject7.put("groupingHash", name);
            jSONArray.put(jSONObject7);
            jSONObject.put("apiKey", "ce86b33875bdf14452a94a745b9b042c");
            jSONObject.put("notifier", jSONObject3);
            jSONObject.put(b.ao, jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private File d() {
        File file = new File(this.e.getExternalCacheDir(), "meiqia_log");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public String a(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    public void a() {
        this.d = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void b() {
        if (this.d != null) {
            Thread.setDefaultUncaughtExceptionHandler(this.d);
        }
    }

    public void c() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File d2 = d();
                if (d2 != null && d2.listFiles() != null) {
                    File[] listFiles = d2.listFiles();
                    for (int i = 0; i < listFiles.length; i++) {
                        final File file = listFiles[i];
                        if (i < 10) {
                            a(a(file), new Callback() {
                                public void onFailure(Call call, IOException iOException) {
                                    try {
                                        file.delete();
                                    } catch (Exception unused) {
                                    }
                                }

                                public void onResponse(Call call, Response response) {
                                    try {
                                        file.delete();
                                    } catch (Exception unused) {
                                    }
                                }
                            });
                        } else {
                            try {
                                file.delete();
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            a(th);
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (this.d != null) {
            this.d.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
