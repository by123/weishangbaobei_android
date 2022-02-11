package com.umeng.socialize.net.stats.cache;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class c {
    private static final String a = "c";
    private static final String b = ".log";
    private final int c = 32;
    private final int d = WXMediaMessage.TITLE_LENGTH_LIMIT;
    private final int e = 50;
    private final int f = 8;
    private String g = null;

    public static class a {
        private String a;
        private List<String> b = new ArrayList();

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public void a(List<String> list) {
            this.b = list;
        }

        public List<String> b() {
            return this.b;
        }
    }

    public c(String str) {
        this.g = str;
        String str2 = a;
        Log.d(str2, "dir path" + this.g);
    }

    public static double a(long j) {
        double d2 = (double) j;
        Double.isNaN(d2);
        return (d2 / 1024.0d) / 1024.0d;
    }

    private File a(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        String[] list = file.list();
        if (list == null || list.length <= 0) {
            return c(file);
        }
        File b2 = b(file);
        return b2 == null ? c(file) : b2;
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static double b() {
        long blockSize;
        long availableBlocks;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = (long) statFs.getBlockSize();
            availableBlocks = (long) statFs.getAvailableBlocks();
        }
        return a(blockSize * availableBlocks);
    }

    private double b(long j) {
        if (j <= 0) {
            return 0.0d;
        }
        double d2 = (double) j;
        Double.isNaN(d2);
        return d2 / 1024.0d;
    }

    private File b(File file) {
        File[] d2 = d(file);
        if (d2 == null) {
            return null;
        }
        if (d2.length <= 0) {
            return null;
        }
        int i = 0;
        while (i < d2.length) {
            File file2 = d2[i];
            if (!file2.getName().endsWith(b)) {
                b(file2.getName());
                i++;
            } else if (b(file2.length()) > 32.0d) {
                return null;
            } else {
                return file2;
            }
        }
        return null;
    }

    private File c(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        return new File(file, c());
    }

    private String c() {
        return String.valueOf(System.currentTimeMillis()) + b;
    }

    private Comparator<File> d() {
        return new Comparator<File>() {
            /* renamed from: a */
            public int compare(File file, File file2) {
                return Long.valueOf(file.length()).compareTo(Long.valueOf(file2.length()));
            }
        };
    }

    private File[] d(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles, d());
        return listFiles;
    }

    private File e() {
        if (TextUtils.isEmpty(this.g)) {
            Log.d(a, "Couldn't create directory mDirPath is null");
            return null;
        }
        File file = new File(this.g);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        String str = a;
        Log.d(str, "Couldn't create directory" + this.g);
        return null;
    }

    private File e(File file) {
        String[] list;
        if (file == null || !file.isDirectory() || (list = file.list()) == null || list.length <= 0) {
            return null;
        }
        return f(file);
    }

    private File f(File file) {
        File[] d2 = d(file);
        if (d2 == null) {
            return null;
        }
        if (d2.length <= 0) {
            return null;
        }
        for (File file2 : d2) {
            if (b(file2.length()) <= ((double) 40) && file2.getName().endsWith(b)) {
                return file2;
            }
            Log.e(a, "getReadableFileFromFiles:file length don't legal" + file2.length());
            b(file2.getName());
        }
        return null;
    }

    private boolean g(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        long j = 0;
        for (File file2 : listFiles) {
            if (file2.getName().endsWith(b)) {
                j += file2.length();
            } else if (!b(file2.getName())) {
                j += file2.length();
            }
        }
        double d2 = (double) j;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        Log.e(a, "dir size is:" + d3 + ";length:" + file.length());
        return d3 > 512.0d;
    }

    public a a() {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader3;
        String str;
        File e2 = e(e());
        if (e2 == null) {
            return null;
        }
        try {
            fileInputStream = new a(e2).c();
        } catch (IOException e3) {
            e3.printStackTrace();
            b(e2.getName());
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a(e2.getName());
            inputStreamReader2 = new InputStreamReader(fileInputStream);
            try {
                bufferedReader = new BufferedReader(inputStreamReader2);
                try {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        i++;
                        Log.d(a, "read file:" + i + readLine);
                        if (!TextUtils.isEmpty(readLine)) {
                            try {
                                str = AesHelper.decryptNoPadding(readLine, "UTF-8").replaceAll("\u0000", "");
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                Log.e(a, "read log decrypt error");
                                str = null;
                            }
                            if (TextUtils.isEmpty(str) || !str.contains(SocializeProtocolConstants.PROTOCOL_KEY_VERSION)) {
                                Log.e(a, "read log content error");
                            } else {
                                arrayList.add(str);
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        b(aVar.a());
                        a((Closeable) fileInputStream);
                        a((Closeable) inputStreamReader2);
                        a((Closeable) bufferedReader);
                        return null;
                    }
                    aVar.a((List<String>) arrayList);
                    a((Closeable) fileInputStream);
                    a((Closeable) inputStreamReader2);
                    a((Closeable) bufferedReader);
                    return aVar;
                } catch (IOException e5) {
                    e = e5;
                    inputStreamReader = inputStreamReader2;
                    try {
                        e.printStackTrace();
                        a((Closeable) fileInputStream);
                        a((Closeable) inputStreamReader);
                        a((Closeable) bufferedReader);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader3 = bufferedReader;
                        inputStreamReader2 = inputStreamReader;
                        bufferedReader2 = bufferedReader3;
                        th = th;
                        a((Closeable) fileInputStream);
                        a((Closeable) inputStreamReader2);
                        a((Closeable) bufferedReader2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader3 = bufferedReader;
                    bufferedReader2 = bufferedReader3;
                    th = th;
                    a((Closeable) fileInputStream);
                    a((Closeable) inputStreamReader2);
                    a((Closeable) bufferedReader2);
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                inputStreamReader = inputStreamReader2;
                bufferedReader = null;
                e.printStackTrace();
                a((Closeable) fileInputStream);
                a((Closeable) inputStreamReader);
                a((Closeable) bufferedReader);
                return null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                a((Closeable) fileInputStream);
                a((Closeable) inputStreamReader2);
                a((Closeable) bufferedReader2);
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            inputStreamReader = null;
            bufferedReader = null;
            e.printStackTrace();
            a((Closeable) fileInputStream);
            a((Closeable) inputStreamReader);
            a((Closeable) bufferedReader);
            return null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            inputStreamReader2 = null;
            a((Closeable) fileInputStream);
            a((Closeable) inputStreamReader2);
            a((Closeable) bufferedReader2);
            throw th;
        }
    }

    public boolean a(String str) {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        String str2;
        boolean z = true;
        BufferedWriter bufferedWriter2 = null;
        File e2 = e();
        File a2 = a(e2);
        if (a2 == null) {
            return false;
        }
        double b2 = b();
        if (b2 < 50.0d) {
            Log.e(a, "InternalMemory beyond the min limit, current size is:" + b2);
            return false;
        } else if (g(e2)) {
            Log.e(a, "dir size beyond the max limit");
            return false;
        } else {
            a aVar = new a(a2);
            try {
                fileOutputStream = aVar.a(true);
            } catch (IOException e3) {
                e3.printStackTrace();
                b(a2.getName());
                fileOutputStream = null;
            }
            if (fileOutputStream == null) {
                return false;
            }
            try {
                Log.net("stats url = " + str);
                str2 = AesHelper.encryptNoPadding(str, "UTF-8");
            } catch (Exception e4) {
                e4.printStackTrace();
                Log.e(a, "save log encrypt error");
                str2 = null;
            }
            try {
                if (!TextUtils.isEmpty(str2)) {
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    try {
                        bufferedWriter = new BufferedWriter(outputStreamWriter);
                        try {
                            bufferedWriter.write(str2);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            aVar.a(fileOutputStream);
                        } catch (IOException e5) {
                            e = e5;
                            try {
                                aVar.b(fileOutputStream);
                                e.printStackTrace();
                                z = false;
                                a((Closeable) bufferedWriter);
                                a((Closeable) outputStreamWriter);
                                a((Closeable) fileOutputStream);
                                return z;
                            } catch (Throwable th) {
                                th = th;
                                bufferedWriter2 = bufferedWriter;
                                bufferedWriter = bufferedWriter2;
                                a((Closeable) bufferedWriter);
                                a((Closeable) outputStreamWriter);
                                a((Closeable) fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            a((Closeable) bufferedWriter);
                            a((Closeable) outputStreamWriter);
                            a((Closeable) fileOutputStream);
                            throw th;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        bufferedWriter = null;
                        aVar.b(fileOutputStream);
                        e.printStackTrace();
                        z = false;
                        a((Closeable) bufferedWriter);
                        a((Closeable) outputStreamWriter);
                        a((Closeable) fileOutputStream);
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedWriter = bufferedWriter2;
                        a((Closeable) bufferedWriter);
                        a((Closeable) outputStreamWriter);
                        a((Closeable) fileOutputStream);
                        throw th;
                    }
                } else {
                    bufferedWriter = null;
                    z = false;
                    outputStreamWriter = null;
                }
            } catch (IOException e7) {
                e = e7;
                bufferedWriter = null;
                outputStreamWriter = null;
                aVar.b(fileOutputStream);
                e.printStackTrace();
                z = false;
                a((Closeable) bufferedWriter);
                a((Closeable) outputStreamWriter);
                a((Closeable) fileOutputStream);
                return z;
            } catch (Throwable th4) {
                th = th4;
                bufferedWriter = null;
                outputStreamWriter = null;
                a((Closeable) bufferedWriter);
                a((Closeable) outputStreamWriter);
                a((Closeable) fileOutputStream);
                throw th;
            }
            a((Closeable) bufferedWriter);
            a((Closeable) outputStreamWriter);
            a((Closeable) fileOutputStream);
            return z;
        }
    }

    public boolean b(String str) {
        File e2 = e();
        if (e2 == null) {
            return false;
        }
        boolean delete = new File(e2, str).delete();
        String str2 = a;
        Log.d(str2, "deleteFile:" + str + ";result:" + delete);
        return delete;
    }
}
