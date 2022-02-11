package com.umeng.socialize.a.b;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.UmengText;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class a {

    /* renamed from: com.umeng.socialize.a.b.a$a  reason: collision with other inner class name */
    private static class C0016a implements Comparator<File> {
        private C0016a() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file.lastModified() > file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }

    public static void a() {
        if (Environment.getExternalStorageDirectory() != null && !TextUtils.isEmpty(Environment.getExternalStorageDirectory().getPath())) {
            c.d = Environment.getExternalStorageDirectory().getPath() + File.separator + c.e + File.separator;
        } else {
            c.d = Environment.getDataDirectory().getPath() + File.separator + c.e + File.separator;
        }
        File file = new File(c.d);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            a(c.d);
        } catch (Exception e) {
            Log.um(UmengText.CLEAN_CACHE_ERROR + e.toString());
        }
    }

    private static void a(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            int i = 0;
            for (File length : listFiles) {
                i = (int) (((long) i) + length.length());
            }
            if (i > 0 || 40 > c()) {
                Arrays.sort(listFiles, new C0016a());
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    public static void b() {
        a();
    }

    private static int c() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double availableBlocks = (double) statFs.getAvailableBlocks();
        double blockSize = (double) statFs.getBlockSize();
        Double.isNaN(availableBlocks);
        Double.isNaN(blockSize);
        return (int) ((blockSize * availableBlocks) / 1048576.0d);
    }
}
