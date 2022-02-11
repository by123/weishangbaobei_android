package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.ShareContent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class z {
    private static Map<String, String> a;
    private static boolean b;

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0003, code lost:
        r0 = com.stub.StubApp.getOrigApplicationContext(r1.getApplicationContext());
     */
    public static Context a(Context context) {
        Context origApplicationContext;
        return (context == null || origApplicationContext == null) ? context : origApplicationContext;
    }

    public static SharedPreferences a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    private static BufferedReader a(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (Throwable th) {
            x.a(th);
            return null;
        }
    }

    public static BufferedReader a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            return a(file);
        } catch (NullPointerException e) {
            x.a(e);
            return null;
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object) null, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain == null) {
                return createFromParcel;
            }
            obtain.recycle();
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    public static String a() {
        return a(System.currentTimeMillis());
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f7 A[SYNTHETIC, Splitter:B:52:0x00f7] */
    public static String a(Context context, int i, String str) {
        StringBuilder sb;
        Throwable th;
        String sb2;
        Process process = null;
        if (!AppInfo.a(context, "android.permission.READ_LOGS")) {
            x.d("no read_log permission!", new Object[0]);
            return null;
        }
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        sb = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec(strArr);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                    if (i > 0 && sb.length() > i) {
                        sb.delete(0, sb.length() - i);
                    }
                }
                String sb3 = sb.toString();
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                        return sb3;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return sb3;
            } catch (Throwable th2) {
                th = th2;
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            sb.append("\n[error:" + th.toString() + "]");
            sb2 = sb.toString();
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                try {
                    process.getInputStream().close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                try {
                    process.getErrorStream().close();
                    return sb2;
                } catch (IOException e9) {
                    e9.printStackTrace();
                    return sb2;
                }
            }
            return sb2;
        } catch (Throwable th4) {
            th = th4;
            if (process != null) {
            }
            throw th;
        }
        process.getInputStream().close();
        process.getErrorStream().close();
        return sb2;
        process.getErrorStream().close();
        return sb2;
    }

    public static String a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (a == null) {
            a = new HashMap();
            ArrayList<String> c = c(context, "getprop");
            if (c != null && c.size() > 0) {
                x.b(z.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : c) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                x.b(z.class, "System properties number: %d.", Integer.valueOf(a.size()));
            }
        }
        return a.containsKey(str) ? a.get(str) : "fail";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0072 A[SYNTHETIC, Splitter:B:39:0x0072] */
    public static String a(File file, int i, boolean z) {
        BufferedReader bufferedReader;
        ? r2 = 0;
        if (file != null && file.exists() && file.canRead()) {
            try {
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                        sb.append("\n");
                        if (i > 0 && sb.length() > i) {
                            if (z) {
                                sb.delete(i, sb.length());
                                break;
                            }
                            sb.delete(0, sb.length() - i);
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                }
                String sb2 = sb.toString();
                try {
                    bufferedReader.close();
                    r2 = sb2;
                } catch (Exception e) {
                    x.a(e);
                    r2 = sb2;
                }
            } catch (Throwable th2) {
                th = th2;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
                throw th;
            }
        }
        return r2;
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!x.a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static Thread a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            x.e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    public static Map<String, String> a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : allStackTraces.entrySet()) {
            sb.setLength(0);
            if (!(next.getValue() == null || ((StackTraceElement[]) next.getValue()).length == 0)) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
                int length = stackTraceElementArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                    i2++;
                }
                hashMap.put(((Thread) next.getKey()).getName() + "(" + ((Thread) next.getKey()).getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap hashMap2 = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap2.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
            hashMap = hashMap2;
        } else {
            x.e("map plugin parcel error!", new Object[0]);
            hashMap = null;
        }
        return hashMap;
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set((Object) null, obj);
        } catch (Exception e) {
        }
    }

    public static boolean a(Context context, String str, long j) {
        x.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                x.c("[Util] Lock file (%s) is expired, unlock it.", str);
                b(context, str);
            }
            if (file.createNewFile()) {
                x.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            x.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a5 A[Catch:{ all -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00aa A[SYNTHETIC, Splitter:B:45:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00af A[SYNTHETIC, Splitter:B:48:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e0 A[SYNTHETIC, Splitter:B:65:0x00e0] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e5 A[SYNTHETIC, Splitter:B:68:0x00e5] */
    public static boolean a(File file, File file2, int i) {
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream;
        x.c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            x.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (!file.exists() || !file.canRead()) {
            x.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        } else {
            try {
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
            if (!file2.exists()) {
                return false;
            }
            if (!file2.canRead()) {
                return false;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
                } catch (Throwable th2) {
                    th = th2;
                    zipOutputStream = null;
                    if (fileInputStream != null) {
                    }
                    if (zipOutputStream != null) {
                    }
                    x.c("rqdp{  ZF end}", new Object[0]);
                    throw th;
                }
                try {
                    zipOutputStream.setMethod(8);
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bArr = new byte[5000];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.flush();
                    zipOutputStream.closeEntry();
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    x.c("rqdp{  ZF end}", new Object[0]);
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                    }
                    if (zipOutputStream != null) {
                    }
                    x.c("rqdp{  ZF end}", new Object[0]);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
                zipOutputStream = null;
                if (fileInputStream != null) {
                }
                if (zipOutputStream != null) {
                }
                x.c("rqdp{  ZF end}", new Object[0]);
                throw th;
            }
        }
    }

    public static boolean a(Runnable runnable) {
        if (runnable != null) {
            w a2 = w.a();
            if (a2 != null) {
                return a2.a(runnable);
            }
            String[] split = runnable.getClass().getName().split("\\.");
            if (a(runnable, split[split.length - 1]) != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0028 A[SYNTHETIC, Splitter:B:15:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A[SYNTHETIC, Splitter:B:31:0x0049] */
    public static byte[] a(int i) {
        DataInputStream dataInputStream;
        synchronized (z.class) {
            try {
                byte[] bArr = new byte[16];
                dataInputStream = new DataInputStream(new FileInputStream(new File("/dev/urandom")));
                try {
                    dataInputStream.readFully(bArr);
                    try {
                        dataInputStream.close();
                        return bArr;
                    } catch (Exception e) {
                        if (!x.b(e)) {
                            e.printStackTrace();
                        }
                        Class<z> cls = z.class;
                        return null;
                    } catch (Throwable th) {
                        Class<z> cls2 = z.class;
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        x.e("Failed to read from /dev/urandom : %s", e);
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        KeyGenerator instance = KeyGenerator.getInstance("AES");
                        instance.init(ShareContent.MINAPP_STYLE, new SecureRandom());
                        byte[] encoded = instance.generateKey().getEncoded();
                        return encoded;
                    } catch (Throwable th2) {
                        th = th2;
                        if (dataInputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                dataInputStream = null;
                x.e("Failed to read from /dev/urandom : %s", e);
                if (dataInputStream != null) {
                }
                KeyGenerator instance2 = KeyGenerator.getInstance("AES");
                instance2.init(ShareContent.MINAPP_STYLE, new SecureRandom());
                byte[] encoded2 = instance2.generateKey().getEncoded();
                return encoded2;
            } catch (Throwable th3) {
                th = th3;
                dataInputStream = null;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @TargetApi(19)
    public static byte[] a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            if (Build.VERSION.SDK_INT < 21 || b) {
                instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            } else {
                instance.init(i, secretKeySpec, new GCMParameterSpec(instance.getBlockSize() << 3, bArr2));
            }
            return instance.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException e) {
            b = true;
            throw e;
        } catch (Exception e2) {
            if (!x.b(e2)) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0083 A[SYNTHETIC, Splitter:B:37:0x0083] */
    public static byte[] a(File file, String str, String str2) {
        ZipOutputStream zipOutputStream;
        ? r2 = 0;
        if (!(str == null || str.length() == 0)) {
            x.c("rqdp{  ZF start}", new Object[0]);
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                try {
                    zipOutputStream.setMethod(8);
                    zipOutputStream.putNextEntry(new ZipEntry(str2));
                    byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                    while (true) {
                        int read = byteArrayInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.closeEntry();
                    zipOutputStream.flush();
                    zipOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        zipOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    x.c("rqdp{  ZF end}", new Object[0]);
                    r2 = byteArray;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                x.c("rqdp{  ZF end}", new Object[0]);
                throw th;
            }
        }
        return r2;
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        x.c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), i == 2 ? "Gzip" : "zip");
        try {
            ae a2 = ad.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return a(a(bArr, 2), 1, str);
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        x.c("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        try {
            aj a2 = a.a(i);
            if (a2 == null) {
                return null;
            }
            a2.a(str);
            return a2.b(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String b(String str, String str2) {
        return (a.b() == null || a.b().G == null) ? "" : a.b().G.getString(str, str2);
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            return a(instance.digest());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static Map<String, String> b(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            x.e("map parcel error!", new Object[0]);
            hashMap = null;
        } else {
            HashMap hashMap2 = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap2.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    public static void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static void b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    public static boolean b(Context context, String str) {
        x.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            x.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    public static byte[] b(int i, byte[] bArr, byte[] bArr2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!x.b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        x.c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), i == 2 ? "Gzip" : "zip");
        try {
            ae a2 = ad.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.b(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i, int i2, String str) {
        try {
            return b(b(bArr, 1, str), 2);
        } catch (Exception e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static byte[] b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        try {
            aj a2 = a.a(i);
            if (a2 == null) {
                return null;
            }
            a2.a(str);
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            x.d("encrytype %d %s", Integer.valueOf(i), str);
            return null;
        }
    }

    public static long c(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0084 A[Catch:{ all -> 0x00ea }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089 A[SYNTHETIC, Splitter:B:23:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008e A[SYNTHETIC, Splitter:B:26:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ca A[SYNTHETIC, Splitter:B:49:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cf A[SYNTHETIC, Splitter:B:52:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    private static ArrayList<String> c(Context context, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        if (AppInfo.f(context)) {
            return new ArrayList<>(Arrays.asList(new String[]{"unknown(low memory)"}));
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String str2 = "/system/bin/sh";
        try {
            if (!new File("/system/bin/sh").exists() || !new File("/system/bin/sh").canExecute()) {
                str2 = "sh";
            }
            ArrayList arrayList2 = new ArrayList(Arrays.asList(new String[]{str2, "-c"}));
            arrayList2.add(str);
            Process exec = Runtime.getRuntime().exec((String[]) arrayList2.toArray(new String[3]));
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = null;
                    if (bufferedReader != null) {
                    }
                    if (bufferedReader2 != null) {
                    }
                    throw th;
                }
            }
            bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader2.readLine();
                    if (readLine2 != null) {
                        arrayList.add(readLine2);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (bufferedReader2 == null) {
                        return null;
                    }
                    try {
                        bufferedReader2.close();
                        return null;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
            }
            bufferedReader.close();
            try {
                bufferedReader2.close();
                return arrayList;
            } catch (IOException e4) {
                e4.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = null;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            x.a("URL(%s)'s length is larger than 255.", str);
            return false;
        } else if (str.toLowerCase().startsWith("http")) {
            return true;
        } else {
            x.a("URL(%s) is not start with \"http\".", str);
            return false;
        }
    }

    public static byte[] c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
