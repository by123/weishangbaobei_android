package com.umeng.analytics.process;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBFileTraversalUtil {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public static FileLockUtil b = new FileLockUtil();

    public interface a {
        void a();
    }

    public static void traverseDBFiles(String str, final FileLockCallback fileLockCallback, final a aVar) {
        final File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            a.execute(new Runnable() {
                public void run() {
                    for (File file : file.listFiles()) {
                        if (file.getName().endsWith(a.d)) {
                            DBFileTraversalUtil.b.doFileOperateion(file, fileLockCallback);
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> file: " + file.getName());
                        }
                    }
                    if (aVar != null) {
                        aVar.a();
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> end *** ");
                }
            });
        }
    }
}
