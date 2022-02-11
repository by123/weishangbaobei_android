package com.tencent.bugly;

import java.util.Map;

public class BuglyStrategy {
    protected int a = 31;
    protected boolean b = false;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;
    private String h;
    private boolean i = true;
    private boolean j = true;
    private boolean k = true;
    private Class<?> l = null;
    private boolean m = true;
    private boolean n = true;
    private boolean o = true;
    private boolean p = false;
    private a q;

    public static class a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            synchronized (this) {
            }
            return null;
        }

        public byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            synchronized (this) {
            }
            return null;
        }
    }

    public String getAppChannel() {
        String str;
        synchronized (this) {
            str = this.d == null ? com.tencent.bugly.crashreport.common.info.a.b().m : this.d;
        }
        return str;
    }

    public String getAppPackageName() {
        String str;
        synchronized (this) {
            str = this.e == null ? com.tencent.bugly.crashreport.common.info.a.b().c : this.e;
        }
        return str;
    }

    public long getAppReportDelay() {
        long j2;
        synchronized (this) {
            j2 = this.f;
        }
        return j2;
    }

    public String getAppVersion() {
        String str;
        synchronized (this) {
            str = this.c == null ? com.tencent.bugly.crashreport.common.info.a.b().k : this.c;
        }
        return str;
    }

    public int getCallBackType() {
        int i2;
        synchronized (this) {
            i2 = this.a;
        }
        return i2;
    }

    public boolean getCloseErrorCallback() {
        boolean z;
        synchronized (this) {
            z = this.b;
        }
        return z;
    }

    public a getCrashHandleCallback() {
        a aVar;
        synchronized (this) {
            aVar = this.q;
        }
        return aVar;
    }

    public String getDeviceID() {
        String str;
        synchronized (this) {
            str = this.h;
        }
        return str;
    }

    public String getLibBuglySOFilePath() {
        String str;
        synchronized (this) {
            str = this.g;
        }
        return str;
    }

    public Class<?> getUserInfoActivity() {
        Class<?> cls;
        synchronized (this) {
            cls = this.l;
        }
        return cls;
    }

    public boolean isBuglyLogUpload() {
        boolean z;
        synchronized (this) {
            z = this.m;
        }
        return z;
    }

    public boolean isEnableANRCrashMonitor() {
        boolean z;
        synchronized (this) {
            z = this.j;
        }
        return z;
    }

    public boolean isEnableNativeCrashMonitor() {
        boolean z;
        synchronized (this) {
            z = this.i;
        }
        return z;
    }

    public boolean isEnableUserInfo() {
        boolean z;
        synchronized (this) {
            z = this.k;
        }
        return z;
    }

    public boolean isReplaceOldChannel() {
        return this.n;
    }

    public boolean isUploadProcess() {
        boolean z;
        synchronized (this) {
            z = this.o;
        }
        return z;
    }

    public boolean recordUserInfoOnceADay() {
        boolean z;
        synchronized (this) {
            z = this.p;
        }
        return z;
    }

    public BuglyStrategy setAppChannel(String str) {
        synchronized (this) {
            this.d = str;
        }
        return this;
    }

    public BuglyStrategy setAppPackageName(String str) {
        synchronized (this) {
            this.e = str;
        }
        return this;
    }

    public BuglyStrategy setAppReportDelay(long j2) {
        synchronized (this) {
            this.f = j2;
        }
        return this;
    }

    public BuglyStrategy setAppVersion(String str) {
        synchronized (this) {
            this.c = str;
        }
        return this;
    }

    public BuglyStrategy setBuglyLogUpload(boolean z) {
        synchronized (this) {
            this.m = z;
        }
        return this;
    }

    public void setCallBackType(int i2) {
        synchronized (this) {
            this.a = i2;
        }
    }

    public void setCloseErrorCallback(boolean z) {
        synchronized (this) {
            this.b = z;
        }
    }

    public BuglyStrategy setCrashHandleCallback(a aVar) {
        synchronized (this) {
            this.q = aVar;
        }
        return this;
    }

    public BuglyStrategy setDeviceID(String str) {
        synchronized (this) {
            this.h = str;
        }
        return this;
    }

    public BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        synchronized (this) {
            this.j = z;
        }
        return this;
    }

    public BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        synchronized (this) {
            this.i = z;
        }
        return this;
    }

    public BuglyStrategy setEnableUserInfo(boolean z) {
        synchronized (this) {
            this.k = z;
        }
        return this;
    }

    public BuglyStrategy setLibBuglySOFilePath(String str) {
        synchronized (this) {
            this.g = str;
        }
        return this;
    }

    public BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        synchronized (this) {
            this.p = z;
        }
        return this;
    }

    public void setReplaceOldChannel(boolean z) {
        this.n = z;
    }

    public BuglyStrategy setUploadProcess(boolean z) {
        synchronized (this) {
            this.o = z;
        }
        return this;
    }

    public BuglyStrategy setUserInfoActivity(Class<?> cls) {
        synchronized (this) {
            this.l = cls;
        }
        return this;
    }
}
