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

/* compiled from: StatsLogCache */
public class c {
    private static final String a = "c";
    private static final String b = ".log";
    private final int c = 32;
    private final int d = WXMediaMessage.TITLE_LENGTH_LIMIT;
    private final int e = 50;
    private final int f = 8;
    private String g = null;

    public c(String str) {
        this.g = str;
        String str2 = a;
        Log.d(str2, "dir path" + this.g);
    }

    public boolean a(String str) {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        String str2;
        BufferedWriter bufferedWriter;
        File e2 = e();
        File a2 = a(e2);
        boolean z = false;
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
            BufferedWriter bufferedWriter2 = null;
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
                try {
                    e4.printStackTrace();
                    Log.e(a, "save log encrypt error");
                    str2 = null;
                } catch (IOException e5) {
                    e = e5;
                    outputStreamWriter = null;
                    try {
                        aVar.b(fileOutputStream);
                        e.printStackTrace();
                        a((Closeable) bufferedWriter2);
                        a((Closeable) outputStreamWriter);
                        a((Closeable) fileOutputStream);
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        a((Closeable) bufferedWriter2);
                        a((Closeable) outputStreamWriter);
                        a((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStreamWriter = null;
                    a((Closeable) bufferedWriter2);
                    a((Closeable) outputStreamWriter);
                    a((Closeable) fileOutputStream);
                    throw th;
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                try {
                    bufferedWriter = new BufferedWriter(outputStreamWriter);
                } catch (IOException e6) {
                    e = e6;
                    aVar.b(fileOutputStream);
                    e.printStackTrace();
                    a((Closeable) bufferedWriter2);
                    a((Closeable) outputStreamWriter);
                    a((Closeable) fileOutputStream);
                    return z;
                }
                try {
                    bufferedWriter.write(str2);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    aVar.a(fileOutputStream);
                    bufferedWriter2 = bufferedWriter;
                    z = true;
                } catch (IOException e7) {
                    e = e7;
                    bufferedWriter2 = bufferedWriter;
                    aVar.b(fileOutputStream);
                    e.printStackTrace();
                    a((Closeable) bufferedWriter2);
                    a((Closeable) outputStreamWriter);
                    a((Closeable) fileOutputStream);
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter2 = bufferedWriter;
                    a((Closeable) bufferedWriter2);
                    a((Closeable) outputStreamWriter);
                    a((Closeable) fileOutputStream);
                    throw th;
                }
            } else {
                outputStreamWriter = null;
            }
            a((Closeable) bufferedWriter2);
            a((Closeable) outputStreamWriter);
            a((Closeable) fileOutputStream);
            return z;
        }
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

    private File a(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        String[] list = file.list();
        if (list == null || list.length <= 0) {
            return c(file);
        }
        File b2 = b(file);
        if (b2 == null) {
            return c(file);
        }
        return b2;
    }

    private File b(File file) {
        File[] d2 = d(file);
        if (d2 == null || d2.length <= 0) {
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

    private double b(long j) {
        if (j <= 0) {
            return 0.0d;
        }
        double d2 = (double) j;
        Double.isNaN(d2);
        return d2 / 1024.0d;
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

    private File[] d(File file) {
        if (file == null || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles, d());
        return listFiles;
    }

    private Comparator<File> d() {
        return new Comparator<File>() {
            /* renamed from: a */
            public int compare(File file, File file2) {
                return Long.valueOf(file.length()).compareTo(Long.valueOf(file2.length()));
            }
        };
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

    public a a() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
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
            inputStreamReader = new InputStreamReader(fileInputStream);
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (IOException e4) {
                e = e4;
                bufferedReader = null;
                try {
                    e.printStackTrace();
                    a((Closeable) fileInputStream);
                    a((Closeable) inputStreamReader);
                    a((Closeable) bufferedReader);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    a((Closeable) fileInputStream);
                    a((Closeable) inputStreamReader);
                    a((Closeable) bufferedReader);
                    throw th;
                }
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
                a((Closeable) fileInputStream);
                a((Closeable) inputStreamReader);
                a((Closeable) bufferedReader);
                throw th;
            }
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
                        } catch (Exception e5) {
                            e5.printStackTrace();
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
                    a((Closeable) inputStreamReader);
                    a((Closeable) bufferedReader);
                    return null;
                }
                aVar.a((List<String>) arrayList);
                a((Closeable) fileInputStream);
                a((Closeable) inputStreamReader);
                a((Closeable) bufferedReader);
                return aVar;
            } catch (IOException e6) {
                e = e6;
                e.printStackTrace();
                a((Closeable) fileInputStream);
                a((Closeable) inputStreamReader);
                a((Closeable) bufferedReader);
                return null;
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
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            inputStreamReader = null;
            a((Closeable) fileInputStream);
            a((Closeable) inputStreamReader);
            a((Closeable) bufferedReader);
            throw th;
        }
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
        if (d2 == null || d2.length <= 0) {
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

    /* compiled from: StatsLogCache */
    public static class a {
        private String a;
        private List<String> b = new ArrayList();

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public List<String> b() {
            return this.b;
        }

        public void a(List<String> list) {
            this.b = list;
        }
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
        if (d3 > 512.0d) {
            return true;
        }
        return false;
    }

    public static double b() {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
            j2 = statFs.getAvailableBlocksLong();
        } else {
            j = (long) statFs.getBlockSize();
            j2 = (long) statFs.getAvailableBlocks();
        }
        return a(j2 * j);
    }

    public static double a(long j) {
        double d2 = (double) j;
        Double.isNaN(d2);
        return (d2 / 1024.0d) / 1024.0d;
    }
}
