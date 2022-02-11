package com.umeng.analytics.filter;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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

    public EventList(String str, String str2) {
        this.mEventListName = str;
        this.mEventListKey = str;
        this.mEventListVersionKey = str2;
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

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A[SYNTHETIC, Splitter:B:15:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c6 A[SYNTHETIC, Splitter:B:61:0x00c6] */
    private boolean loadEventListFromFile(Context context, File file) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (file.exists() && TextUtils.isEmpty(this.mEventList)) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    String sb2 = sb.toString();
                    if (!TextUtils.isEmpty(sb2)) {
                        String md5 = HelperUtils.getMD5(sb2);
                        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, this.mEventListVersionKey, "");
                        this.mEventList = sb2;
                        eventListChange();
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> loadEventListFromFile: mEventList = " + this.mEventList);
                        if (UMUtils.isMainProgress(context)) {
                            if (md5.equalsIgnoreCase(imprintProperty)) {
                                this.mEventList = sb2;
                                setMD5ClearFlag(false);
                                try {
                                    bufferedReader.close();
                                    return true;
                                } catch (Throwable th) {
                                    UMCrashManager.reportCrash(context, th);
                                    return true;
                                }
                            } else {
                                setMD5ClearFlag(true);
                                try {
                                    bufferedReader.close();
                                    return false;
                                } catch (Throwable th2) {
                                    UMCrashManager.reportCrash(context, th2);
                                    return false;
                                }
                            }
                        } else if (md5.equalsIgnoreCase(imprintProperty)) {
                            this.mEventList = sb2;
                            eventListChange();
                            try {
                                bufferedReader.close();
                                return true;
                            } catch (Throwable th3) {
                                UMCrashManager.reportCrash(context, th3);
                                return true;
                            }
                        } else {
                            try {
                                bufferedReader.close();
                                return false;
                            } catch (Throwable th4) {
                                UMCrashManager.reportCrash(context, th4);
                                return false;
                            }
                        }
                    } else {
                        bufferedReader.close();
                        return false;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    try {
                        UMCrashManager.reportCrash(context, th);
                        if (bufferedReader != null) {
                        }
                        return false;
                    } catch (Throwable th6) {
                        th = th6;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable th7) {
                                UMCrashManager.reportCrash(context, th7);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th8) {
                th = th8;
                if (bufferedReader2 != null) {
                }
                throw th;
            }
        }
        return false;
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

    public boolean enabled() {
        synchronized (this) {
            return this.mEventList != null;
        }
    }

    /* access modifiers changed from: protected */
    public void eventListChange() {
    }

    public boolean matchHit(String str) {
        return false;
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

    public boolean onFileLock(String str) {
        return false;
    }

    public boolean onFileLock(String str, Object obj) {
        return false;
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

    public boolean onPreProcessImprintKey(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        int length = str2.length();
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onPreProcessImprintKey: key = " + str + "; len of value=" + length);
        StringBuilder sb = new StringBuilder();
        sb.append("--->>> onPreProcessImprintKey: value = ");
        sb.append(str2);
        UMRTLog.i(UMRTLog.RTLOG_TAG, sb.toString());
        this.mEventList = str2;
        eventListChange();
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    UMCrashManager.reportCrash(this.mAppContext, e2);
                }
            }
        }
        this.mFileLock.doFileOperateion(file, (FileLockCallback) this, 0);
        setMD5ClearFlag(false);
        return true;
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

    public void setMD5ClearFlag(boolean z) {
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
}
