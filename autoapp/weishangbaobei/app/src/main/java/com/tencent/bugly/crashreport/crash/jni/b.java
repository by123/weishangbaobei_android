package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
public final class b {
    private static List<File> a = new ArrayList();

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

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap hashMap;
        Map<String, String> map2 = map;
        if (map2 == null) {
            return null;
        }
        if (a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map2.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> d = d(str3);
        if (d == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            d.get("sino").intValue();
            d.get("sud").intValue();
            String str4 = map2.get("soVersion");
            if (TextUtils.isEmpty(str4)) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map2.get("errorAddr");
            if (str5 == null) {
                str5 = "unknown";
            }
            String str6 = str5;
            String str7 = map2.get("codeMsg");
            if (str7 == null) {
                str7 = "unknown";
            }
            String str8 = map2.get("tombPath");
            if (str8 == null) {
                str8 = "unknown";
            }
            String str9 = str8;
            String str10 = map2.get("signalName");
            if (str10 == null) {
                str10 = "unknown";
            }
            map2.get("errnoMsg");
            String str11 = map2.get("stack");
            if (str11 == null) {
                str11 = "unknown";
            }
            String str12 = map2.get("jstack");
            if (str12 != null) {
                str11 = str11 + "java:\n" + str12;
            }
            Integer num = d.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str7;
                str2 = str10;
            } else {
                str2 = str10 + "(" + str7 + ")";
                str = "KERNEL";
            }
            String str13 = map2.get("nativeLog");
            byte[] a2 = (str13 == null || str13.isEmpty()) ? null : z.a((File) null, str13, "BuglyNativeLog.txt");
            String str14 = map2.get("sendingProcess");
            if (str14 == null) {
                str14 = "unknown";
            }
            Integer num2 = d.get("spd");
            if (num2 != null) {
                str14 = str14 + "(" + num2 + ")";
            }
            String str15 = str14;
            String str16 = map2.get("threadName");
            if (str16 == null) {
                str16 = "unknown";
            }
            Integer num3 = d.get("et");
            if (num3 != null) {
                str16 = str16 + "(" + num3 + ")";
            }
            String str17 = str16;
            String str18 = map2.get("processName");
            if (str18 == null) {
                str18 = "unknown";
            }
            Integer num4 = d.get("ep");
            if (num4 != null) {
                str18 = str18 + "(" + num4 + ")";
            }
            String str19 = map2.get("key-value");
            if (str19 != null) {
                HashMap hashMap2 = new HashMap();
                String[] split = str19.split("\n");
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].split("=");
                    String[] strArr = split;
                    if (split2.length == 2) {
                        hashMap2.put(split2[0], split2[1]);
                    }
                    i++;
                    split = strArr;
                }
                hashMap = hashMap2;
            } else {
                hashMap = null;
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str18, str17, (((long) d.get("ets").intValue()) * 1000) + (((long) d.get("etms").intValue()) / 1000), str2, str6, a(str11), str, str15, str9, map2.get("sysLogPath"), map2.get("jniLogPath"), str4, a2, hashMap, false, false);
            if (packageCrashDatas != null) {
                String str20 = map2.get("userId");
                if (str20 != null) {
                    x.c("[Native record info] userId: %s", str20);
                    packageCrashDatas.m = str20;
                }
                String str21 = map2.get("sysLog");
                if (str21 != null) {
                    packageCrashDatas.w = str21;
                }
                String str22 = map2.get("appVersion");
                if (str22 != null) {
                    x.c("[Native record info] appVersion: %s", str22);
                    packageCrashDatas.f = str22;
                }
                String str23 = map2.get("isAppForeground");
                if (str23 != null) {
                    x.c("[Native record info] isAppForeground: %s", str23);
                    packageCrashDatas.N = str23.equalsIgnoreCase("true");
                }
                String str24 = map2.get("launchTime");
                if (str24 != null) {
                    x.c("[Native record info] launchTime: %s", str24);
                    packageCrashDatas.M = Long.parseLong(str24);
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

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.BufferedInputStream r4) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
        L_0x000b:
            int r2 = r4.read()     // Catch:{ Throwable -> 0x002b }
            r3 = -1
            if (r2 == r3) goto L_0x0027
            if (r2 != 0) goto L_0x0023
            java.lang.String r4 = new java.lang.String     // Catch:{ Throwable -> 0x002b }
            byte[] r2 = r1.toByteArray()     // Catch:{ Throwable -> 0x002b }
            java.lang.String r3 = "UTf-8"
            r4.<init>(r2, r3)     // Catch:{ Throwable -> 0x002b }
            r1.close()
            return r4
        L_0x0023:
            r1.write(r2)     // Catch:{ Throwable -> 0x002b }
            goto L_0x000b
        L_0x0027:
            r1.close()
            goto L_0x003a
        L_0x002b:
            r4 = move-exception
            goto L_0x0032
        L_0x002d:
            r4 = move-exception
            r1 = r0
            goto L_0x003c
        L_0x0030:
            r4 = move-exception
            r1 = r0
        L_0x0032:
            com.tencent.bugly.proguard.x.a(r4)     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x003a
            r1.close()
        L_0x003a:
            return r0
        L_0x003b:
            r4 = move-exception
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()
        L_0x0041:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(java.io.BufferedInputStream):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x008c A[SYNTHETIC, Splitter:B:53:0x008c] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0098 A[SYNTHETIC, Splitter:B:60:0x0098] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.crashreport.crash.CrashDetailBean a(android.content.Context r6, java.lang.String r7, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r8) {
        /*
            r0 = 0
            r1 = 0
            if (r6 == 0) goto L_0x00a2
            if (r7 == 0) goto L_0x00a2
            if (r8 != 0) goto L_0x000a
            goto L_0x00a2
        L_0x000a:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "rqd_record.eup"
            r2.<init>(r7, r3)
            boolean r7 = r2.exists()
            if (r7 == 0) goto L_0x00a1
            boolean r7 = r2.canRead()
            if (r7 != 0) goto L_0x001f
            goto L_0x00a1
        L_0x001f:
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            java.lang.String r2 = a((java.io.BufferedInputStream) r7)     // Catch:{ IOException -> 0x0080 }
            r3 = 1
            if (r2 == 0) goto L_0x006e
            java.lang.String r4 = "NATIVE_RQD_REPORT"
            boolean r4 = r2.equals(r4)     // Catch:{ IOException -> 0x0080 }
            if (r4 != 0) goto L_0x0039
            goto L_0x006e
        L_0x0039:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ IOException -> 0x0080 }
            r2.<init>()     // Catch:{ IOException -> 0x0080 }
        L_0x003e:
            r4 = r1
        L_0x003f:
            java.lang.String r5 = a((java.io.BufferedInputStream) r7)     // Catch:{ IOException -> 0x0080 }
            if (r5 == 0) goto L_0x004d
            if (r4 != 0) goto L_0x0049
            r4 = r5
            goto L_0x003f
        L_0x0049:
            r2.put(r4, r5)     // Catch:{ IOException -> 0x0080 }
            goto L_0x003e
        L_0x004d:
            if (r4 == 0) goto L_0x0061
            java.lang.String r6 = "record not pair! drop! %s"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0080 }
            r8[r0] = r4     // Catch:{ IOException -> 0x0080 }
            com.tencent.bugly.proguard.x.e(r6, r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0060:
            return r1
        L_0x0061:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r6 = a((android.content.Context) r6, (java.util.Map<java.lang.String, java.lang.String>) r2, (com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler) r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()
        L_0x006d:
            return r6
        L_0x006e:
            java.lang.String r6 = "record read fail! %s"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0080 }
            r8[r0] = r2     // Catch:{ IOException -> 0x0080 }
            com.tencent.bugly.proguard.x.e(r6, r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r6 = move-exception
            r6.printStackTrace()
        L_0x007f:
            return r1
        L_0x0080:
            r6 = move-exception
            goto L_0x0087
        L_0x0082:
            r6 = move-exception
            r7 = r1
            goto L_0x0096
        L_0x0085:
            r6 = move-exception
            r7 = r1
        L_0x0087:
            r6.printStackTrace()     // Catch:{ all -> 0x0095 }
            if (r7 == 0) goto L_0x0094
            r7.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0094:
            return r1
        L_0x0095:
            r6 = move-exception
        L_0x0096:
            if (r7 == 0) goto L_0x00a0
            r7.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a0:
            throw r6
        L_0x00a1:
            return r1
        L_0x00a2:
            java.lang.String r6 = "get eup record file args error"
            java.lang.Object[] r7 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.e(r6, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(android.content.Context, java.lang.String, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    private static String b(String str, String str2) {
        BufferedReader a2 = z.a(str, "reg_record.txt");
        if (a2 == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = a2.readLine();
            if (readLine != null) {
                if (readLine.startsWith(str2)) {
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i % 4 == 0) {
                            if (i > 0) {
                                sb.append("\n");
                            }
                            sb.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i2 = 28;
                            }
                            sb.append("                ".substring(0, i2 - i3));
                        }
                        i3 = readLine2.length();
                        sb.append(readLine2);
                        i++;
                    }
                    sb.append("\n");
                    String sb2 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return sb2;
                }
            }
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e2) {
                    x.a(e2);
                }
            }
            return null;
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

    private static String c(String str, String str2) {
        BufferedReader a2 = z.a(str, "map_record.txt");
        if (a2 == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = a2.readLine();
            if (readLine != null) {
                if (readLine.startsWith(str2)) {
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(readLine2);
                        sb.append("\n");
                    }
                    String sb2 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return sb2;
                }
            }
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e2) {
                    x.a(e2);
                }
            }
            return null;
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

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e5 A[SYNTHETIC, Splitter:B:40:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f0 A[SYNTHETIC, Splitter:B:45:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r7, int r8, java.lang.String r9, boolean r10) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x00fa
            if (r8 > 0) goto L_0x0007
            goto L_0x00fa
        L_0x0007:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x00f9
            boolean r2 = r1.canRead()
            if (r2 != 0) goto L_0x001a
            goto L_0x00f9
        L_0x001a:
            java.lang.String r2 = "Read system log from native record file(length: %s bytes): %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            long r4 = r1.length()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r5 = 0
            r3[r5] = r4
            r4 = 1
            java.lang.String r6 = r1.getAbsolutePath()
            r3[r4] = r6
            com.tencent.bugly.proguard.x.a(r2, r3)
            java.util.List<java.io.File> r2 = a
            r2.add(r1)
            java.lang.String r2 = "Add this record file to list for cleaning lastly."
            java.lang.Object[] r3 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.x.c(r2, r3)
            if (r9 != 0) goto L_0x004d
            java.io.File r9 = new java.io.File
            r9.<init>(r7)
            java.lang.String r7 = com.tencent.bugly.proguard.z.a((java.io.File) r9, (int) r8, (boolean) r10)
            goto L_0x00ed
        L_0x004d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00c1 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00c1 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00c1 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x00c1 }
            java.lang.String r1 = "utf-8"
            r3.<init>(r4, r1)     // Catch:{ Throwable -> 0x00c1 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00c1 }
        L_0x0063:
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            if (r0 == 0) goto L_0x00ab
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            r1.<init>()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            r1.append(r9)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            java.lang.String r3 = "[ ]*:"
            r1.append(r3)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            java.util.regex.Matcher r1 = r1.matcher(r0)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            boolean r1 = r1.find()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            if (r1 == 0) goto L_0x0090
            r7.append(r0)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            java.lang.String r0 = "\n"
            r7.append(r0)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
        L_0x0090:
            if (r8 <= 0) goto L_0x0063
            int r0 = r7.length()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            if (r0 <= r8) goto L_0x0063
            if (r10 == 0) goto L_0x00a2
            int r9 = r7.length()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            r7.delete(r8, r9)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            goto L_0x00ab
        L_0x00a2:
            int r0 = r7.length()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            int r0 = r0 - r8
            r7.delete(r5, r0)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            goto L_0x0063
        L_0x00ab:
            java.lang.String r8 = r7.toString()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b9 }
            r2.close()     // Catch:{ Exception -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r7 = move-exception
            com.tencent.bugly.proguard.x.a(r7)
        L_0x00b7:
            r7 = r8
            goto L_0x00ed
        L_0x00b9:
            r7 = move-exception
            goto L_0x00ee
        L_0x00bb:
            r8 = move-exception
            r0 = r2
            goto L_0x00c2
        L_0x00be:
            r7 = move-exception
            r2 = r0
            goto L_0x00ee
        L_0x00c1:
            r8 = move-exception
        L_0x00c2:
            com.tencent.bugly.proguard.x.a(r8)     // Catch:{ all -> 0x00be }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
            java.lang.String r10 = "\n[error:"
            r9.<init>(r10)     // Catch:{ all -> 0x00be }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00be }
            r9.append(r8)     // Catch:{ all -> 0x00be }
            java.lang.String r8 = "]"
            r9.append(r8)     // Catch:{ all -> 0x00be }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x00be }
            r7.append(r8)     // Catch:{ all -> 0x00be }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00be }
            if (r0 == 0) goto L_0x00ed
            r0.close()     // Catch:{ Exception -> 0x00e9 }
            goto L_0x00ed
        L_0x00e9:
            r8 = move-exception
            com.tencent.bugly.proguard.x.a(r8)
        L_0x00ed:
            return r7
        L_0x00ee:
            if (r2 == 0) goto L_0x00f8
            r2.close()     // Catch:{ Exception -> 0x00f4 }
            goto L_0x00f8
        L_0x00f4:
            r8 = move-exception
            com.tencent.bugly.proguard.x.a(r8)
        L_0x00f8:
            throw r7
        L_0x00f9:
            return r0
        L_0x00fa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(java.lang.String, int, java.lang.String, boolean):java.lang.String");
    }
}
