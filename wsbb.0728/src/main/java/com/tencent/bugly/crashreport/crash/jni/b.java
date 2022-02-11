package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class b {
    private static List<File> a = new ArrayList();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.tencent.bugly.crashreport.crash.CrashDetailBean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.BufferedInputStream} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x009d A[SYNTHETIC, Splitter:B:57:0x009d] */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        String str2;
        String a2;
        ? r2 = 0;
        if (context == null || str == null || nativeExceptionHandler == null) {
            x.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        String a3 = a(bufferedInputStream);
                        if (a3 == null || !a3.equals("NATIVE_RQD_REPORT")) {
                            x.e("record read fail! %s", a3);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            HashMap hashMap = new HashMap();
                            loop0:
                            while (true) {
                                str2 = null;
                                while (true) {
                                    a2 = a(bufferedInputStream);
                                    if (a2 != null) {
                                        if (str2 != null) {
                                            break;
                                        }
                                        str2 = a2;
                                    } else {
                                        break loop0;
                                    }
                                }
                                hashMap.put(str2, a2);
                            }
                            if (str2 != null) {
                                x.e("record not pair! drop! %s", str2);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            } else {
                                CrashDetailBean a4 = a(context, (Map<String, String>) hashMap, nativeExceptionHandler);
                                try {
                                    bufferedInputStream.close();
                                    r2 = a4;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    r2 = a4;
                                }
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (IOException e5) {
                    e = e5;
                    bufferedInputStream = null;
                    try {
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        return r2;
                    } catch (Throwable th) {
                        th = th;
                        r2 = bufferedInputStream;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (r2 != 0) {
                    }
                    throw th;
                }
            }
        }
        return r2;
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        HashMap hashMap;
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> d = d(str);
        if (d == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            d.get("sino").intValue();
            d.get("sud").intValue();
            String str2 = map.get("soVersion");
            if (TextUtils.isEmpty(str2)) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str3 = map.get("errorAddr");
            String str4 = str3 == null ? "unknown" : str3;
            String str5 = map.get("codeMsg");
            String str6 = str5 == null ? "unknown" : str5;
            String str7 = map.get("tombPath");
            String str8 = str7 == null ? "unknown" : str7;
            String str9 = map.get("signalName");
            String str10 = str9 == null ? "unknown" : str9;
            map.get("errnoMsg");
            String str11 = map.get("stack");
            String str12 = str11 == null ? "unknown" : str11;
            String str13 = map.get("jstack");
            if (str13 != null) {
                str12 = str12 + "java:\n" + str13;
            }
            Integer num = d.get("sico");
            if (num != null && num.intValue() > 0) {
                str10 = str10 + "(" + str6 + ")";
                str6 = "KERNEL";
            }
            String str14 = map.get("nativeLog");
            byte[] a2 = (str14 == null || str14.isEmpty()) ? null : z.a((File) null, str14, "BuglyNativeLog.txt");
            String str15 = map.get("sendingProcess");
            String str16 = str15 == null ? "unknown" : str15;
            Integer num2 = d.get("spd");
            if (num2 != null) {
                str16 = str16 + "(" + num2 + ")";
            }
            String str17 = map.get("threadName");
            String str18 = str17 == null ? "unknown" : str17;
            Integer num3 = d.get("et");
            if (num3 != null) {
                str18 = str18 + "(" + num3 + ")";
            }
            String str19 = map.get("processName");
            String str20 = str19 == null ? "unknown" : str19;
            Integer num4 = d.get("ep");
            if (num4 != null) {
                str20 = str20 + "(" + num4 + ")";
            }
            String str21 = map.get("key-value");
            if (str21 != null) {
                hashMap = new HashMap();
                for (String split : str21.split("\n")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            } else {
                hashMap = null;
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str20, str18, (((long) d.get("etms").intValue()) / 1000) + (((long) d.get("ets").intValue()) * 1000), str10, str4, a(str12), str6, str16, str8, map.get("sysLogPath"), map.get("jniLogPath"), str2, a2, hashMap, false, false);
            if (packageCrashDatas != null) {
                String str22 = map.get("userId");
                if (str22 != null) {
                    x.c("[Native record info] userId: %s", str22);
                    packageCrashDatas.m = str22;
                }
                String str23 = map.get("sysLog");
                if (str23 != null) {
                    packageCrashDatas.w = str23;
                }
                String str24 = map.get("appVersion");
                if (str24 != null) {
                    x.c("[Native record info] appVersion: %s", str24);
                    packageCrashDatas.f = str24;
                }
                String str25 = map.get("isAppForeground");
                if (str25 != null) {
                    x.c("[Native record info] isAppForeground: %s", str25);
                    packageCrashDatas.N = str25.equalsIgnoreCase("true");
                }
                String str26 = map.get("launchTime");
                if (str26 != null) {
                    x.c("[Native record info] launchTime: %s", str26);
                    packageCrashDatas.M = Long.parseLong(str26);
                }
                packageCrashDatas.z = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (NumberFormatException e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            x.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003d  */
    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bufferedInputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            while (true) {
                try {
                    int read = bufferedInputStream.read();
                    if (read == -1) {
                        byteArrayOutputStream.close();
                        return null;
                    } else if (read == 0) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                        byteArrayOutputStream.close();
                        return str;
                    } else {
                        byteArrayOutputStream.write(read);
                    }
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ec A[SYNTHETIC, Splitter:B:45:0x00ec] */
    public static String a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader;
        if (str == null || i <= 0) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        x.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
        a.add(file);
        x.c("Add this record file to list for cleaning lastly.", new Object[0]);
        if (str2 == null) {
            return z.a(new File(str), i, z);
        }
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                        sb.append(readLine);
                        sb.append("\n");
                    }
                    if (i > 0 && sb.length() > i) {
                        if (z) {
                            sb.delete(i, sb.length());
                            break;
                        }
                        sb.delete(0, sb.length() - i);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    throw th;
                }
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
                return sb2;
            } catch (Exception e2) {
                x.a(e2);
                return sb2;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            if (bufferedReader != null) {
            }
            throw th;
        }
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String b = b(str, str2);
        if (b != null && !b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(b);
        }
        String c = c(str, str2);
        if (c != null && !c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(c);
        }
        return sb.toString();
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            a.add(new File(str, "rqd_record.eup"));
            a.add(new File(str, "reg_record.txt"));
            a.add(new File(str, "map_record.txt"));
            a.add(new File(str, "backup_record.txt"));
            if (z) {
                c(str);
            }
        }
        if (a != null && a.size() > 0) {
            for (File next : a) {
                if (next.exists() && next.canWrite()) {
                    next.delete();
                    x.c("Delete record file %s", next.getAbsoluteFile());
                }
            }
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    private static String b(String str, String str2) {
        String str3 = null;
        BufferedReader a2 = z.a(str, "reg_record.txt");
        if (a2 != null) {
            try {
                StringBuilder sb = new StringBuilder();
                String readLine = a2.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    int i = 18;
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                sb.append("\n");
                            }
                            sb.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i = 28;
                            }
                            sb.append("                ".substring(0, i - i2));
                        }
                        i2 = readLine2.length();
                        sb.append(readLine2);
                        i3++;
                    }
                    sb.append("\n");
                    str3 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                } else if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
                throw th;
            }
        }
        return str3;
    }

    private static String c(String str, String str2) {
        String str3 = null;
        BufferedReader a2 = z.a(str, "map_record.txt");
        if (a2 != null) {
            try {
                StringBuilder sb = new StringBuilder();
                String readLine = a2.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(readLine2);
                        sb.append("\n");
                    }
                    str3 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                } else if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
                throw th;
            }
        }
        return str3;
    }

    public static void c(String str) {
        File[] listFiles;
        if (str != null) {
            try {
                File file = new File(str);
                if (file.canRead() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (File file2 : listFiles) {
                        if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                            file2.delete();
                            x.c("Delete empty record file %s", file2.getAbsoluteFile());
                        }
                    }
                }
            } catch (Throwable th) {
                x.a(th);
            }
        }
    }

    private static Map<String, Integer> d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
                String[] split = str2.split(":");
                if (split.length != 2) {
                    x.e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
            }
            return hashMap;
        } catch (Exception e) {
            x.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }
}
