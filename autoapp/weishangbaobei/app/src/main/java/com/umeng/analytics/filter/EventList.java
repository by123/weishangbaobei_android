package com.umeng.analytics.filter;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.File;
import java.io.FileOutputStream;

public class EventList extends c implements FileLockCallback {
    private static final int DELETE_LIST_DATA = 2;
    private static final int LOAD_LIST_DATA = 1;
    private static final int SAVE_LIST_DATA = 0;
    private Context mAppContext;
    protected String mEventList;
    protected String mEventListKey;
    protected String mEventListName;
    protected String mEventListVersionKey;
    private FileLockUtil mFileLock = new FileLockUtil();

    /* access modifiers changed from: protected */
    public void eventListChange() {
    }

    public boolean matchHit(String str) {
        return false;
    }

    public boolean onFileLock(String str) {
        return false;
    }

    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    public void setMD5ClearFlag(boolean z) {
    }

    public EventList(String str, String str2) {
        this.mEventListName = str;
        this.mEventListKey = str;
        this.mEventListVersionKey = str2;
    }

    public boolean enabled() {
        synchronized (this) {
            if (this.mEventList == null) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b4 A[SYNTHETIC, Splitter:B:55:0x00b4] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c0 A[SYNTHETIC, Splitter:B:61:0x00c0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean loadEventListFromFile(android.content.Context r9, java.io.File r10) {
        /*
            r8 = this;
            boolean r0 = r10.exists()
            r1 = 0
            if (r0 == 0) goto L_0x00c9
            java.lang.String r0 = r8.mEventList
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00c9
            r0 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
            r2.<init>(r10)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
            r10.<init>(r2)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a4 }
            r0.<init>()     // Catch:{ Throwable -> 0x00a4 }
        L_0x001f:
            java.lang.String r2 = r10.readLine()     // Catch:{ Throwable -> 0x00a4 }
            if (r2 == 0) goto L_0x0029
            r0.append(r2)     // Catch:{ Throwable -> 0x00a4 }
            goto L_0x001f
        L_0x0029:
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00a4 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00a4 }
            if (r2 != 0) goto L_0x00a0
            java.lang.String r2 = com.umeng.commonsdk.statistics.common.HelperUtils.getMD5(r0)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = r8.mEventListVersionKey     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r4 = ""
            java.lang.String r3 = com.umeng.commonsdk.framework.UMEnvelopeBuild.imprintProperty(r9, r3, r4)     // Catch:{ Throwable -> 0x00a4 }
            r8.mEventList = r0     // Catch:{ Throwable -> 0x00a4 }
            r8.eventListChange()     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r4 = "MobclickRT"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a4 }
            r5.<init>()     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r6 = "--->>> loadEventListFromFile: mEventList = "
            r5.append(r6)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r6 = r8.mEventList     // Catch:{ Throwable -> 0x00a4 }
            r5.append(r6)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x00a4 }
            com.umeng.commonsdk.debug.UMRTLog.i(r4, r5)     // Catch:{ Throwable -> 0x00a4 }
            boolean r4 = com.umeng.commonsdk.utils.UMUtils.isMainProgress(r9)     // Catch:{ Throwable -> 0x00a4 }
            r5 = 1
            if (r4 == 0) goto L_0x0083
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x00a4 }
            if (r2 == 0) goto L_0x0077
            r8.mEventList = r0     // Catch:{ Throwable -> 0x00a4 }
            r8.setMD5ClearFlag(r1)     // Catch:{ Throwable -> 0x00a4 }
            r10.close()     // Catch:{ Throwable -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0076:
            return r5
        L_0x0077:
            r8.setMD5ClearFlag(r5)     // Catch:{ Throwable -> 0x00a4 }
            r10.close()     // Catch:{ Throwable -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0082:
            return r1
        L_0x0083:
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x00a4 }
            if (r2 == 0) goto L_0x0097
            r8.mEventList = r0     // Catch:{ Throwable -> 0x00a4 }
            r8.eventListChange()     // Catch:{ Throwable -> 0x00a4 }
            r10.close()     // Catch:{ Throwable -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0096:
            return r5
        L_0x0097:
            r10.close()     // Catch:{ Throwable -> 0x009b }
            goto L_0x009f
        L_0x009b:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x009f:
            return r1
        L_0x00a0:
            r10.close()     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x00c9
        L_0x00a4:
            r0 = move-exception
            goto L_0x00af
        L_0x00a6:
            r10 = move-exception
            r7 = r0
            r0 = r10
            r10 = r7
            goto L_0x00be
        L_0x00ab:
            r10 = move-exception
            r7 = r0
            r0 = r10
            r10 = r7
        L_0x00af:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r0)     // Catch:{ all -> 0x00bd }
            if (r10 == 0) goto L_0x00c9
            r10.close()     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x00c9
        L_0x00b8:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
            goto L_0x00c9
        L_0x00bd:
            r0 = move-exception
        L_0x00be:
            if (r10 == 0) goto L_0x00c8
            r10.close()     // Catch:{ Throwable -> 0x00c4 }
            goto L_0x00c8
        L_0x00c4:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x00c8:
            throw r0
        L_0x00c9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.filter.EventList.loadEventListFromFile(android.content.Context, java.io.File):boolean");
    }

    private void deleteDataFile(File file) {
        if (this.mAppContext != null) {
            synchronized (this.mFileLock) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private void saveEventListToFile(Context context, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(this.mEventList.getBytes());
            fileOutputStream.close();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    public void register(Context context) {
        if (this.mAppContext == null) {
            this.mAppContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!TextUtils.isEmpty(UMEnvelopeBuild.imprintProperty(this.mAppContext, this.mEventListVersionKey, ""))) {
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, (FileLockCallback) this, 1);
            } else {
                setMD5ClearFlag(true);
            }
        }
        if (UMUtils.isMainProgress(this.mAppContext)) {
            ImprintHandler.getImprintService(this.mAppContext).registPreProcessCallback(this.mEventListKey, this);
            ImprintHandler.getImprintService(this.mAppContext).registImprintCallback(this.mEventListVersionKey, this);
        }
    }

    public void onImprintValueChanged(String str, String str2) {
        if (b.ak.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable black list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), (FileLockCallback) this, 2);
        }
        if (b.al.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable white list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), (FileLockCallback) this, 2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0064, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0065, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r4.mAppContext, r6);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0060 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreProcessImprintKey(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x0073
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L_0x0073
            int r0 = r6.length()
            java.lang.String r1 = "MobclickRT"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "--->>> onPreProcessImprintKey: key = "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = "; len of value="
            r2.append(r5)
            r2.append(r0)
            java.lang.String r5 = r2.toString()
            com.umeng.commonsdk.debug.UMRTLog.i(r1, r5)
            java.lang.String r5 = "MobclickRT"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "--->>> onPreProcessImprintKey: value = "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            com.umeng.commonsdk.debug.UMRTLog.i(r5, r0)
            r4.mEventList = r6
            r4.eventListChange()
            java.io.File r5 = new java.io.File
            android.content.Context r6 = r4.mAppContext
            java.io.File r6 = r6.getFilesDir()
            java.lang.String r0 = r4.mEventListName
            r5.<init>(r6, r0)
            boolean r6 = r5.exists()
            if (r6 != 0) goto L_0x006a
            r5.createNewFile()     // Catch:{ IOException -> 0x0060 }
            goto L_0x006a
        L_0x0060:
            r5.createNewFile()     // Catch:{ IOException -> 0x0064 }
            goto L_0x006a
        L_0x0064:
            r6 = move-exception
            android.content.Context r0 = r4.mAppContext
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r0, r6)
        L_0x006a:
            com.umeng.commonsdk.utils.FileLockUtil r6 = r4.mFileLock
            r0 = 0
            r6.doFileOperateion((java.io.File) r5, (com.umeng.commonsdk.utils.FileLockCallback) r4, (int) r0)
            r4.setMD5ClearFlag(r0)
        L_0x0073:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.filter.EventList.onPreProcessImprintKey(java.lang.String, java.lang.String):boolean");
    }

    public String toString() {
        if (TextUtils.isEmpty(this.mEventListName) || TextUtils.isEmpty(this.mEventListKey)) {
            return "Uninitialized EventList.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("EventListName:" + this.mEventListName + ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append("listKey:" + this.mEventListKey + ListUtils.DEFAULT_JOIN_SEPARATOR);
        if (!TextUtils.isEmpty(this.mEventList)) {
            sb.append("listKeyValue:" + this.mEventList + "]");
        } else {
            sb.append("listKeyValue:empty,");
        }
        if (!TextUtils.isEmpty(this.mEventListVersionKey)) {
            sb.append("listKeyVer:" + this.mEventListVersionKey + "]");
        } else {
            sb.append("listKeyVer:empty]");
        }
        return sb.toString();
    }

    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            synchronized (this) {
                saveEventListToFile(this.mAppContext, file);
            }
        } else if (i == 1) {
            synchronized (this) {
                if (loadEventListFromFile(this.mAppContext, file)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> find event list data file, load it.");
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> can't find event list file.");
                }
            }
        } else if (i == 2) {
            synchronized (this) {
                this.mEventList = null;
                deleteDataFile(file);
            }
        }
        return true;
    }
}
