package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.a;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UMConfigure {
    public static final int DEVICE_TYPE_BOX = 2;
    public static final int DEVICE_TYPE_PHONE = 1;
    private static final String KEY_FILE_NAME_APPKEY = "APPKEY";
    private static final String KEY_FILE_NAME_LOG = "LOG";
    private static final String KEY_METHOD_NAME_PUSH_SETCHANNEL = "setMessageChannel";
    private static final String KEY_METHOD_NAME_PUSH_SET_SECRET = "setSecret";
    private static final String KEY_METHOD_NAME_SETAPPKEY = "setAppkey";
    private static final String KEY_METHOD_NAME_SETCHANNEL = "setChannel";
    private static final String KEY_METHOD_NAME_SETDEBUGMODE = "setDebugMode";
    private static final String TAG = "UMConfigure";
    private static final String WRAPER_TYPE_COCOS2DX_X = "Cocos2d-x";
    private static final String WRAPER_TYPE_COCOS2DX_XLUA = "Cocos2d-x_lua";
    private static final String WRAPER_TYPE_FLUTTER = "flutter";
    private static final String WRAPER_TYPE_HYBRID = "hybrid";
    private static final String WRAPER_TYPE_NATIVE = "native";
    private static final String WRAPER_TYPE_PHONEGAP = "phonegap";
    private static final String WRAPER_TYPE_REACTNATIVE = "react-native";
    private static final String WRAPER_TYPE_UNITY = "Unity";
    private static final String WRAPER_TYPE_WEEX = "weex";
    private static boolean debugLog;
    private static boolean isFinish = false;
    private static Object lockObject = new Object();
    public static UMLog umDebugLog = new UMLog();

    private static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private Object getInstanceObject(Class<?> cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
        return null;
    }

    private static Object getDecInstanceObject(Class<?> cls) {
        Constructor<?> constructor;
        if (cls != null) {
            try {
                constructor = cls.getDeclaredConstructor(new Class[0]);
            } catch (NoSuchMethodException unused) {
                constructor = null;
            }
            if (constructor != null) {
                constructor.setAccessible(true);
                try {
                    return constructor.newInstance(new Object[0]);
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused2) {
                }
            }
        }
        return null;
    }

    private static Method getDecMethod(Class<?> cls, String str, Class<?>[] clsArr) {
        Method method = null;
        if (cls != null) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
            if (method != null) {
                method.setAccessible(true);
            }
        }
        return method;
    }

    private static void invoke(Method method, Object obj, Object[] objArr) {
        if (method != null && obj != null) {
            try {
                method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    private static void invoke(Method method, Object[] objArr) {
        if (method != null) {
            try {
                method.invoke((Object) null, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    private static void setFile(Class<?> cls, String str, String str2) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, str2);
            } catch (Exception unused) {
            }
        }
    }

    private static void setFile(Class<?> cls, String str, boolean z) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }

    private static void saveSDKComponent() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getClass("com.umeng.analytics.vismode.V") != null) {
            stringBuffer.append("v");
        } else if (getClass("com.umeng.analytics.MobclickAgent") != null) {
            stringBuffer.append(e.al);
        }
        if (getClass("com.umeng.visual.UMVisualAgent") != null) {
            stringBuffer.append("x");
        }
        if (getClass("com.umeng.message.PushAgent") != null) {
            stringBuffer.append(e.ao);
        }
        if (getClass("com.umeng.socialize.UMShareAPI") != null) {
            stringBuffer.append(e.ap);
        }
        if (getClass("com.umeng.error.UMError") != null) {
            stringBuffer.append("e");
        }
        stringBuffer.append(e.aq);
        if (!(SdkVersion.SDK_TYPE == 1 || getClass("com.umeng.commonsdk.internal.UMOplus") == null)) {
            stringBuffer.append("o");
        }
        if (!TextUtils.isEmpty(stringBuffer)) {
            b.a = stringBuffer.toString();
            UMSLEnvelopeBuild.module = stringBuffer.toString();
        }
    }

    public static boolean getInitStatus() {
        boolean z;
        synchronized (lockObject) {
            z = isFinish;
        }
        return z;
    }

    public static void init(Context context, int i, String str) {
        init(context, (String) null, (String) null, i, str);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        	at java.util.ArrayList.get(ArrayList.java:433)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x0207 */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0297 A[Catch:{ Exception -> 0x0359, Throwable -> 0x033d }] */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02dc A[Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02e6 A[Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0359 A[ExcHandler: Exception (r9v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x020d A[Catch:{ Exception -> 0x0359, Throwable -> 0x033d }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0219 A[Catch:{ Exception -> 0x0359, Throwable -> 0x033d }] */
    public static void init(android.content.Context r9, java.lang.String r10, java.lang.String r11, int r12, java.lang.String r13) {
        /*
            boolean r0 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = "UMConfigure"
            java.lang.String r1 = "common version is 2.0.0"
            android.util.Log.i(r0, r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r0 = "UMConfigure"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r1.<init>()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r2 = "common type is "
            r1.append(r2)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            int r2 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r1.append(r2)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            android.util.Log.i(r0, r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0023:
            if (r9 != 0) goto L_0x0031
            boolean r9 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r9 == 0) goto L_0x0030
            java.lang.String r9 = "UMConfigure"
            java.lang.String r10 = "context is null !!!"
            android.util.Log.e(r9, r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0030:
            return
        L_0x0031:
            android.content.Context r0 = r9.getApplicationContext()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            boolean r1 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x006d
            java.lang.String r1 = com.umeng.commonsdk.utils.UMUtils.getAppkeyByXML(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            boolean r5 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x006d
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x006d
            boolean r5 = r10.equals(r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x006d
            java.lang.String r5 = "@"
            java.lang.String r6 = "#"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r6[r3] = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r6[r4] = r1     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r1 = "请注意：您init接口中设置的AppKey是@，manifest中设置的AppKey是#，init接口设置的AppKey会覆盖manifest中设置的AppKey"
            r7 = 3
            java.lang.String r8 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r1, r7, r8, r5, r6)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x006d:
            boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x0077
            java.lang.String r10 = com.umeng.commonsdk.utils.UMUtils.getAppkeyByXML(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0077:
            boolean r1 = android.text.TextUtils.isEmpty(r11)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x0081
            java.lang.String r11 = com.umeng.commonsdk.utils.UMUtils.getChannelByXML(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0081:
            boolean r1 = android.text.TextUtils.isEmpty(r11)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x0089
            java.lang.String r11 = "Unknown"
        L_0x0089:
            com.umeng.commonsdk.utils.UMUtils.setChannel(r0, r11)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            boolean r1 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x00a6
            java.lang.String r1 = "UMConfigure"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r5.<init>()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r6 = "channel is "
            r5.append(r6)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r5.append(r11)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            android.util.Log.i(r1, r5)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x00a6:
            boolean r1 = com.umeng.commonsdk.utils.UMUtils.isMainProgress(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x00af
            saveSDKComponent()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x00af:
            java.lang.String r1 = "com.umeng.analytics.MobclickAgent"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x00fb
            java.lang.String r5 = "init"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x00db
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            r6[r3] = r0     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            boolean r5 = debugLog     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x00db
            java.lang.String r5 = "统计SDK初始化成功"
            java.lang.String r6 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r5, (int) r2, (java.lang.String) r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
        L_0x00db:
            java.lang.String r5 = "com.umeng.analytics.game.UMGameAgent"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x00fb
            java.lang.String r5 = "setGameScenarioType"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x00fb
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            r6[r3] = r0     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
        L_0x00fb:
            java.lang.String r1 = com.umeng.commonsdk.statistics.b.a     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.String r5 = "e"
            int r1 = r1.indexOf(r5)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r1 < 0) goto L_0x011f
            java.lang.String r1 = "com.umeng.analytics.MobclickAgent"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x011f
            java.lang.String r5 = "disableExceptionCatch"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x011f
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x011f, Exception -> 0x0359 }
        L_0x011f:
            java.lang.String r1 = "com.umeng.message.MessageSharedPrefs"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x01d1
            java.lang.String r5 = "getInstance"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r5 == 0) goto L_0x01d1
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r6[r3] = r0     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Object r5 = r5.invoke(r1, r6)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r5 == 0) goto L_0x01d1
            java.lang.String r6 = "setMessageAppKey"
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r3] = r8     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.reflect.Method r6 = r1.getDeclaredMethod(r6, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x0164
            r6.setAccessible(r4)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r7[r3] = r10     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r6.invoke(r5, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            boolean r6 = debugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x0164
            com.umeng.commonsdk.debug.UMLog r6 = umDebugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.String r6 = "PUSH AppKey设置成功"
            java.lang.String r7 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r6, (int) r2, (java.lang.String) r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
        L_0x0164:
            java.lang.String r6 = "setMessageChannel"
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r3] = r8     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.reflect.Method r6 = r1.getDeclaredMethod(r6, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x0189
            r6.setAccessible(r4)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r7[r3] = r11     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r6.invoke(r5, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            boolean r6 = debugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x0189
            com.umeng.commonsdk.debug.UMLog r6 = umDebugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.String r6 = "PUSH Channel设置成功"
            java.lang.String r7 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r6, (int) r2, (java.lang.String) r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
        L_0x0189:
            boolean r6 = android.text.TextUtils.isEmpty(r13)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x0192
            boolean r1 = debugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            goto L_0x01d1
        L_0x0192:
            boolean r6 = debugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r6 == 0) goto L_0x01ac
            java.lang.String r6 = "UMConfigure"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r7.<init>()     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.String r8 = "push secret is "
            r7.append(r8)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r7.append(r13)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            android.util.Log.i(r6, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
        L_0x01ac:
            java.lang.String r6 = "setMessageAppSecret"
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r3] = r8     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r6, r7)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x01d1
            r1.setAccessible(r4)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r6[r3] = r13     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            r1.invoke(r5, r6)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            boolean r1 = debugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x01d1
            com.umeng.commonsdk.debug.UMLog r1 = umDebugLog     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
            java.lang.String r1 = "PUSH Secret设置成功"
            java.lang.String r5 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r1, (int) r2, (java.lang.String) r5)     // Catch:{ Exception -> 0x01d1, Throwable -> 0x033d }
        L_0x01d1:
            java.lang.String r1 = "com.umeng.socialize.UMShareAPI"
            java.lang.Class r1 = getClass(r1)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            java.lang.String r5 = "APPKEY"
            setFile((java.lang.Class<?>) r1, (java.lang.String) r5, (java.lang.String) r10)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x0207
            java.lang.String r5 = "init"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r4] = r7     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x0207
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            r6[r3] = r0     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            r6[r4] = r10     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            boolean r1 = debugLog     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x0207
            java.lang.String r1 = "Share AppKey设置成功"
            java.lang.String r5 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r1, (int) r2, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0207, Exception -> 0x0359 }
        L_0x0207:
            boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x0219
            boolean r9 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r9 == 0) goto L_0x0218
            java.lang.String r9 = com.umeng.commonsdk.debug.UMLogCommon.SC_10007     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r10 = "\\|"
            com.umeng.commonsdk.debug.UMLog.aq((java.lang.String) r9, (int) r3, (java.lang.String) r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0218:
            return
        L_0x0219:
            com.umeng.commonsdk.utils.UMUtils.setAppkey(r0, r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r1 = com.umeng.commonsdk.utils.UMUtils.getLastAppkey(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            boolean r5 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x0240
            boolean r5 = r10.equals(r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x0240
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 != 0) goto L_0x023d
            boolean r5 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 == 0) goto L_0x023d
            java.lang.String r5 = "AppKey改变!!!"
            java.lang.String r6 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r5, (int) r2, (java.lang.String) r6)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x023d:
            com.umeng.commonsdk.utils.UMUtils.setLastAppkey(r0, r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0240:
            boolean r5 = debugLog     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r5 == 0) goto L_0x0262
            java.lang.String r5 = "UMConfigure"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r6.<init>()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r7 = "current appkey is "
            r6.append(r7)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r6.append(r10)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r7 = ", last appkey is "
            r6.append(r7)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r6.append(r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r1 = r6.toString()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            android.util.Log.i(r5, r1)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x0262:
            com.umeng.commonsdk.statistics.AnalyticsConstants.setDeviceType(r12)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r1 = "com.umeng.error.UMError"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x0291
            java.lang.String r5 = "init"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x0291
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            r6[r3] = r0     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            boolean r1 = debugLog     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x0291
            java.lang.String r1 = "错误分析SDK初始化成功"
            java.lang.String r5 = ""
            com.umeng.commonsdk.debug.UMLog.mutlInfo((java.lang.String) r1, (int) r2, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0291, Exception -> 0x0359 }
        L_0x0291:
            boolean r1 = com.umeng.commonsdk.utils.UMUtils.isMainProgress(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == 0) goto L_0x02bf
            int r1 = com.umeng.commonsdk.statistics.SdkVersion.SDK_TYPE     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            if (r1 == r4) goto L_0x02bc
            java.lang.String r1 = "com.umeng.commonsdk.UMConfigureImpl"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            if (r1 == 0) goto L_0x02bf
            java.lang.String r5 = "init"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r6)     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            if (r5 == 0) goto L_0x02bf
            r5.setAccessible(r4)     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            r6[r3] = r0     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            r5.invoke(r1, r6)     // Catch:{ Throwable -> 0x02bf, Exception -> 0x0359 }
            goto L_0x02bf
        L_0x02bc:
            com.umeng.commonsdk.a.a(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x02bf:
            r1 = 0
            java.lang.String r5 = "com.umeng.visual.UMVisualAgent"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            java.lang.String r6 = "init"
            java.lang.Class[] r7 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            java.lang.Class<android.content.Context> r8 = android.content.Context.class
            r7[r3] = r8     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r4] = r8     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            java.lang.reflect.Method r5 = r5.getMethod(r6, r7)     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            if (r6 != 0) goto L_0x02e6
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            r2[r3] = r9     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            r2[r4] = r10     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            r5.invoke(r1, r2)     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            goto L_0x0309
        L_0x02e6:
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            if (r2 == 0) goto L_0x0309
            java.lang.String r2 = "initDebugSDK appkey  is null"
            com.umeng.commonsdk.statistics.common.MLog.e((java.lang.String) r2)     // Catch:{ ClassNotFoundException -> 0x02f0, Throwable -> 0x0309, Exception -> 0x0359 }
            goto L_0x0309
        L_0x02f0:
            java.lang.String r2 = "com.umeng.analytics.vismode.event.VisualHelper"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            java.lang.String r5 = "init"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r3] = r7     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            java.lang.reflect.Method r2 = r2.getMethod(r5, r6)     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            r5[r3] = r9     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
            r2.invoke(r1, r5)     // Catch:{ Throwable -> 0x0309, Throwable -> 0x033d }
        L_0x0309:
            com.umeng.commonsdk.service.UMGlobalContext$a r9 = new com.umeng.commonsdk.service.UMGlobalContext$a     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.<init>()     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.a = r0     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.b = r12     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.c = r13     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.d = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.e = r11     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r10 = com.umeng.commonsdk.statistics.b.a     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.f = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.g = r3     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r10 = com.umeng.commonsdk.framework.UMFrUtils.getCurrentProcessName(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.h = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.String r10 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.i = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            boolean r10 = com.umeng.commonsdk.utils.UMUtils.isMainProgress(r0)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            r9.j = r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            com.umeng.commonsdk.service.UMGlobalContext.newUMGlobalContext(r9)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            java.lang.Object r9 = lockObject     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            monitor-enter(r9)     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
            isFinish = r4     // Catch:{ all -> 0x033a }
            monitor-exit(r9)     // Catch:{ all -> 0x033a }
            goto L_0x0374
        L_0x033a:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x033a }
            throw r10     // Catch:{ Exception -> 0x0359, Throwable -> 0x033d }
        L_0x033d:
            r9 = move-exception
            boolean r10 = debugLog
            if (r10 == 0) goto L_0x0374
            java.lang.String r10 = "UMConfigure"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "init e is "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            android.util.Log.e(r10, r9)
            goto L_0x0374
        L_0x0359:
            r9 = move-exception
            boolean r10 = debugLog
            if (r10 == 0) goto L_0x0374
            java.lang.String r10 = "UMConfigure"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "init e is "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            android.util.Log.e(r10, r9)
        L_0x0374:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String):void");
    }

    public static boolean isDebugLog() {
        return debugLog;
    }

    public static void setLogEnabled(boolean z) {
        try {
            debugLog = z;
            MLog.DEBUG = z;
            Class<?> cls = getClass("com.umeng.message.PushAgent");
            Object decInstanceObject = getDecInstanceObject(cls);
            invoke(getDecMethod(cls, KEY_METHOD_NAME_SETDEBUGMODE, new Class[]{Boolean.TYPE}), decInstanceObject, new Object[]{Boolean.valueOf(z)});
            setFile(getClass("com.umeng.socialize.Config"), "DEBUG", z);
        } catch (Exception e) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + e);
            }
        } catch (Throwable th) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + th);
            }
        }
    }

    public static void setEncryptEnabled(boolean z) {
        b.a(z);
        UMSLEnvelopeBuild.setEncryptEnabled(z);
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    public static void setProcessEvent(boolean z) {
        AnalyticsConstants.SUB_PROCESS_EVENT = z;
    }

    private static void setLatencyWindow(long j) {
        a.c = ((int) j) * SocializeConstants.CANCLE_RESULTCODE;
    }

    private static void setCheckDevice(boolean z) {
        AnalyticsConstants.CHECK_DEVICE = z;
    }

    private static void setWraperType(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(WRAPER_TYPE_NATIVE)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_NATIVE;
                a.a = WRAPER_TYPE_NATIVE;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_X)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_COCOS2DX_X;
                a.a = WRAPER_TYPE_COCOS2DX_X;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_XLUA)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_COCOS2DX_XLUA;
                a.a = WRAPER_TYPE_COCOS2DX_XLUA;
            } else if (str.equals(WRAPER_TYPE_UNITY)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_UNITY;
                a.a = WRAPER_TYPE_UNITY;
            } else if (str.equals(WRAPER_TYPE_REACTNATIVE)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_REACTNATIVE;
                a.a = WRAPER_TYPE_REACTNATIVE;
            } else if (str.equals(WRAPER_TYPE_PHONEGAP)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_PHONEGAP;
                a.a = WRAPER_TYPE_PHONEGAP;
            } else if (str.equals(WRAPER_TYPE_WEEX)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_WEEX;
                a.a = WRAPER_TYPE_WEEX;
            } else if (str.equals("hybrid")) {
                com.umeng.commonsdk.stateless.a.a = "hybrid";
                a.a = "hybrid";
            } else if (str.equals(WRAPER_TYPE_FLUTTER)) {
                com.umeng.commonsdk.stateless.a.a = WRAPER_TYPE_FLUTTER;
                a.a = WRAPER_TYPE_FLUTTER;
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            com.umeng.commonsdk.stateless.a.b = str2;
            a.b = str2;
        }
    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] strArr = new String[2];
        if (context != null) {
            try {
                strArr[0] = DeviceConfig.getDeviceIdForGeneral(context);
                strArr[1] = DeviceConfig.getMac(context);
            } catch (Exception unused) {
            }
        }
        return strArr;
    }
}
