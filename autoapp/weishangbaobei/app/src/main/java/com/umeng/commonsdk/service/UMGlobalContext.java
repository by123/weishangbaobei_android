package com.umeng.commonsdk.service;

import android.content.Context;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.utils.UMUtils;
import com.wx.assistants.utils.fileutil.ListUtils;

public class UMGlobalContext {
    private static final String TAG = "UMGlobalContext";
    private String mAppVersion;
    private String mAppkey;
    private Context mApplicationContext;
    private String mChannel;
    private int mDeviceType;
    private boolean mIsDebugMode;
    private boolean mIsMainProcess;
    private String mModules;
    private String mProcessName;
    private String mPushSecret;

    public static class a {
        public Context a;
        public int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public boolean g;
        public String h;
        public String i;
        public boolean j;
    }

    public boolean hasInternalModule() {
        return true;
    }

    private UMGlobalContext() {
        this.mProcessName = "unknown";
    }

    public static UMGlobalContext newUMGlobalContext(a aVar) {
        getInstance();
        b.a.mDeviceType = aVar.b;
        b.a.mPushSecret = aVar.c;
        b.a.mAppkey = aVar.d;
        b.a.mChannel = aVar.e;
        b.a.mModules = aVar.f;
        b.a.mIsDebugMode = aVar.g;
        b.a.mProcessName = aVar.h;
        b.a.mAppVersion = aVar.i;
        b.a.mIsMainProcess = aVar.j;
        if (aVar.a != null) {
            b.a.mApplicationContext = StubApp.getOrigApplicationContext(aVar.a.getApplicationContext());
        }
        return b.a;
    }

    private static class b {
        /* access modifiers changed from: private */
        public static final UMGlobalContext a = new UMGlobalContext();

        private b() {
        }
    }

    public static UMGlobalContext getInstance() {
        return b.a;
    }

    public static Context getAppContext(Context context) {
        if (context == null) {
            return b.a.mApplicationContext;
        }
        Context context2 = b.a.mApplicationContext;
        return context2 != null ? context2 : StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public Context getAppContextDirectly() {
        return this.mApplicationContext;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public String getPushSecret() {
        return this.mPushSecret;
    }

    public String getAppkey() {
        return this.mAppkey;
    }

    public String getChannel() {
        return this.mChannel;
    }

    public boolean hasVisualSdk() {
        return this.mModules.contains("v");
    }

    public boolean hasVisualDebugSdk() {
        return this.mModules.contains("x");
    }

    public boolean hasAnalyticsSdk() {
        return this.mModules.contains(e.al);
    }

    public boolean hasPushSdk() {
        return this.mModules.contains(e.ao);
    }

    public boolean hasShareSdk() {
        return this.mModules.contains(e.ap);
    }

    public boolean hasErrorSdk() {
        return this.mModules.contains("e");
    }

    public boolean hasOplusModule() {
        return this.mModules.contains("o");
    }

    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    public String getProcessName(Context context) {
        if (context == null) {
            return b.a.mProcessName;
        }
        if (b.a.mApplicationContext != null) {
            return this.mProcessName;
        }
        return UMFrUtils.getCurrentProcessName(context);
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public boolean isMainProcess(Context context) {
        if (context == null) {
            return b.a.mIsMainProcess;
        }
        if (b.a.mApplicationContext != null) {
            return b.a.mIsMainProcess;
        }
        return UMUtils.isMainProgress(StubApp.getOrigApplicationContext(context.getApplicationContext()));
    }

    public String toString() {
        if (b.a.mApplicationContext == null) {
            return "uninitialized.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("devType:" + this.mDeviceType + ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append("appkey:" + this.mAppkey + ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append("channel:" + this.mChannel + ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append("procName:" + this.mProcessName + "]");
        return sb.toString();
    }
}
