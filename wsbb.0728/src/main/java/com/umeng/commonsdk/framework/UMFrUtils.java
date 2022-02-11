package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UMFrUtils {
    private static final String KEY_LAST_INSTANT_SUCC_BUILD_TIME = "last_instant_build_time";
    private static final String KEY_LAST_SUCC_BUILD_TIME = "last_successful_build_time";
    private static String mDefaultEnvelopeDir = "envelope";
    private static String mDefaultEnvelopeDirPath = null;
    private static Object mEnvelopeBuildTimeLock = new Object();
    private static Object mEnvelopeFileLock = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        if (r3.getPackageManager().checkPermission(r9, r3.getPackageName()) != 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0036, code lost:
        if (((java.lang.Integer) java.lang.Class.forName("android.content.Context").getMethod("checkSelfPermission", new java.lang.Class[]{java.lang.String.class}).invoke(r8, new java.lang.Object[]{r9})).intValue() == 0) goto L_0x0038;
     */
    private static boolean checkPermission(Context context, String str) {
        if (context != null) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(origApplicationContext, th);
                    return false;
                }
            } else {
                try {
                } catch (Throwable th2) {
                    UMCrashManager.reportCrash(origApplicationContext, th2);
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return 0;
     */
    public static int envelopeFileNumber(Context context) {
        String[] list;
        if (context == null) {
            return 0;
        }
        try {
            File file = new File(getEnvelopeDirPath(context));
            synchronized (mEnvelopeFileLock) {
                if (file.isDirectory() && (list = file.list()) != null) {
                    int length = list.length;
                    return length;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static String getCreateTimeFromFileName(String str) {
        Context appContext = UMModuleRegister.getAppContext();
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(95) + 1;
        try {
            return str.substring(indexOf, str.indexOf(95, indexOf));
        } catch (IndexOutOfBoundsException e) {
            UMCrashManager.reportCrash(appContext, e);
            return "";
        }
    }

    public static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() <= 0)) {
                for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                    if (next.pid == myPid) {
                        return next.processName;
                    }
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(StubApp.getOrigApplicationContext(context.getApplicationContext()), th);
        }
        return "";
    }

    private static long getDistanceDays(long j, long j2) {
        return (j < j2 ? j2 - j : j - j2) / 86400000;
    }

    public static String getEnvelopeDirPath(Context context) {
        String str;
        synchronized (mEnvelopeFileLock) {
            try {
                if (mDefaultEnvelopeDirPath == null) {
                    mDefaultEnvelopeDirPath = context.getFilesDir().getAbsolutePath() + "/." + mDefaultEnvelopeDir;
                }
                File file = new File(mDefaultEnvelopeDirPath);
                if (!file.exists() && !file.mkdir()) {
                    ULog.d("--->>> Create Envelope Directory failed!!!");
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
            str = mDefaultEnvelopeDirPath;
        }
        return str;
    }

    public static File getEnvelopeFile(final Context context) {
        File file = null;
        if (context != null) {
            File file2 = new File(getEnvelopeDirPath(context));
            synchronized (mEnvelopeFileLock) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    Arrays.sort(listFiles, new Comparator<File>() {
                        /* renamed from: a */
                        public int compare(File file, File file2) {
                            String name = file.getName();
                            String name2 = file2.getName();
                            String access$000 = UMFrUtils.getCreateTimeFromFileName(name);
                            String access$0002 = UMFrUtils.getCreateTimeFromFileName(name2);
                            if (TextUtils.isEmpty(access$000) || TextUtils.isEmpty(access$0002)) {
                                return 1;
                            }
                            try {
                                long longValue = Long.valueOf(access$000).longValue() - Long.valueOf(access$0002).longValue();
                                if (longValue > 0) {
                                    return 1;
                                }
                                return longValue == 0 ? 0 : -1;
                            } catch (NumberFormatException e) {
                                UMCrashManager.reportCrash(context, e);
                                return 1;
                            }
                        }
                    });
                    file = listFiles[0];
                }
            }
        }
        return file;
    }

    public static long getLastInstantBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    public static String getLegacyEnvelopeDir(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                ULog.d("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                return mDefaultEnvelopeDir;
            } else if (runningAppProcesses.size() == 0) {
                return mDefaultEnvelopeDir;
            } else {
                try {
                    for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                        if (next.pid == Process.myPid()) {
                            String replace = next.processName.replace(':', '_');
                            ULog.d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                            return replace;
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
        return mDefaultEnvelopeDir;
    }

    public static String getSubProcessName(Context context) {
        String str = "";
        try {
            String currentProcessName = getCurrentProcessName(context);
            int indexOf = currentProcessName.indexOf(":");
            if (indexOf >= 0) {
                str = currentProcessName.substring(indexOf + 1);
            }
            return indexOf < 0 ? currentProcessName.substring(context.getPackageName().length() + 1, currentProcessName.length()) : str;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(StubApp.getOrigApplicationContext(context.getApplicationContext()), th);
            return str;
        }
    }

    public static boolean hasEnvelopeFile(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        String str = e.al;
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
            str = e.aq;
        }
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return false;
                }
                for (File name : listFiles) {
                    if (name.getName().startsWith(str)) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
        return false;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!(!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(StubApp.getOrigApplicationContext(context.getApplicationContext()), th);
        }
        return false;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static boolean removeEnvelopeFile(File file) {
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        boolean delete = file.delete();
                        return delete;
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                }
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static void removeRedundantEnvelopeFiles(final Context context, int i) {
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > i) {
                Arrays.sort(listFiles, new Comparator<File>() {
                    /* renamed from: a */
                    public int compare(File file, File file2) {
                        String name = file.getName();
                        String name2 = file2.getName();
                        String access$000 = UMFrUtils.getCreateTimeFromFileName(name);
                        String access$0002 = UMFrUtils.getCreateTimeFromFileName(name2);
                        if (TextUtils.isEmpty(access$000) || TextUtils.isEmpty(access$0002)) {
                            return 1;
                        }
                        try {
                            long longValue = Long.valueOf(access$000).longValue() - Long.valueOf(access$0002).longValue();
                            if (longValue > 0) {
                                return 1;
                            }
                            return longValue == 0 ? 0 : -1;
                        } catch (NumberFormatException e) {
                            UMCrashManager.reportCrash(context, e);
                            return 1;
                        }
                    }
                });
                if (listFiles.length > i) {
                    int i2 = 0;
                    while (i2 < listFiles.length - i) {
                        try {
                            if (!listFiles[i2].delete()) {
                                ULog.d("--->>> remove [" + i2 + "] file fail.");
                            }
                            i2++;
                        } catch (Throwable th) {
                            UMCrashManager.reportCrash(context, th);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        return 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A[SYNTHETIC, Splitter:B:20:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0061 A[SYNTHETIC, Splitter:B:32:0x0061] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x0064=Splitter:B:34:0x0064, B:22:0x0056=Splitter:B:22:0x0056} */
    public static int saveEnvelopeFile(Context context, String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        if (bArr == null) {
            return 101;
        }
        File file = new File(getEnvelopeDirPath(context) + "/" + str);
        synchronized (mEnvelopeFileLock) {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.close();
                    boolean a = a.a(context).a(str);
                    boolean b = a.a(context).b(str);
                    if (a) {
                        updateLastSuccessfulBuildTime(context);
                    }
                    if (b) {
                        updateLastInstantBuildTime(context);
                    }
                } catch (IOException e) {
                    e = e;
                    try {
                        UMCrashManager.reportCrash(context, e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        }
                        return 101;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                UMCrashManager.reportCrash(context, th3);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
                UMCrashManager.reportCrash(context, e);
                if (fileOutputStream != null) {
                }
                return 101;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                }
                throw th;
            }
        }
    }

    public static void syncLegacyEnvelopeIfNeeded(Context context) {
        if (context != null) {
            try {
                String legacyEnvelopeDir = getLegacyEnvelopeDir(context);
                if (!TextUtils.isEmpty(legacyEnvelopeDir) && !legacyEnvelopeDir.equals(mDefaultEnvelopeDir)) {
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/." + legacyEnvelopeDir);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null && listFiles.length != 0) {
                            try {
                                String envelopeDirPath = getEnvelopeDirPath(context);
                                for (int i = 0; i < listFiles.length; i++) {
                                    File file2 = listFiles[i];
                                    file2.renameTo(new File(envelopeDirPath + "/" + listFiles[i].getName()));
                                }
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        } else if (file.isDirectory()) {
                            file.delete();
                        }
                    }
                }
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(context, th2);
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:8:0x0041=Splitter:B:8:0x0041, B:10:0x0044=Splitter:B:10:0x0044, B:26:0x0057=Splitter:B:26:0x0057, B:28:0x005a=Splitter:B:28:0x005a} */
    public static byte[] toByteArray(String str) throws IOException {
        IOException e;
        Throwable th;
        byte[] bArr;
        FileChannel fileChannel = null;
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            try {
                FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                try {
                    MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
                    System.out.println(load.isLoaded());
                    bArr = new byte[((int) channel.size())];
                    if (load.remaining() > 0) {
                        load.get(bArr, 0, load.remaining());
                    }
                    try {
                        channel.close();
                    } catch (Throwable th2) {
                        UMCrashManager.reportCrash(appContext, th2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileChannel = channel;
                    try {
                        UMCrashManager.reportCrash(appContext, e);
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        th = th;
                        try {
                            fileChannel.close();
                        } catch (Throwable th4) {
                            UMCrashManager.reportCrash(appContext, th4);
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    fileChannel = channel;
                    th = th;
                    fileChannel.close();
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th6) {
                th = th6;
                fileChannel.close();
                throw th;
            }
        }
        return bArr;
    }

    private static void updateLastInstantBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    private static void updateLastSuccessfulBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }
}
