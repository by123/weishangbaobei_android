package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMLogCommon;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.service.UMGlobalContext;
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
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Object getDecInstanceObject(Class<?> cls) {
        Constructor<?> constructor;
        if (cls == null) {
            return null;
        }
        try {
            constructor = cls.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            constructor = null;
        }
        if (constructor == null) {
            return null;
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e2) {
            return null;
        }
    }

    private static Method getDecMethod(Class<?> cls, String str, Class<?>[] clsArr) {
        Method method = null;
        if (cls != null) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
            }
            if (method != null) {
                method.setAccessible(true);
            }
        }
        return method;
    }

    public static boolean getInitStatus() {
        boolean z;
        synchronized (lockObject) {
            z = isFinish;
        }
        return z;
    }

    private Object getInstanceObject(Class<?> cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
            }
        }
        return null;
    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] strArr = new String[2];
        if (context != null) {
            try {
                strArr[0] = DeviceConfig.getDeviceIdForGeneral(context);
                strArr[1] = DeviceConfig.getMac(context);
            } catch (Exception e) {
            }
        }
        return strArr;
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        return null;
    }

    public static void init(Context context, int i, String str) {
        init(context, (String) null, (String) null, i, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:158:0x037c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x037f, code lost:
        if (debugLog != false) goto L_0x0381;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0381, code lost:
        android.util.Log.e(TAG, "init e is " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0205, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0208, code lost:
        if (debugLog != false) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x020a, code lost:
        android.util.Log.e(TAG, "init e is " + r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x037c A[ExcHandler: Throwable (r0v0 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0205 A[ExcHandler: Exception (r0v2 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:27:0x008e] */
    public static void init(Context context, String str, String str2, int i, String str3) {
        Method declaredMethod;
        Method declaredMethod2;
        Method declaredMethod3;
        Method declaredMethod4;
        Object invoke;
        Class<?> cls;
        Method declaredMethod5;
        Method declaredMethod6;
        try {
            if (debugLog) {
                Log.i(TAG, "common version is 2.0.0");
                Log.i(TAG, "common type is " + SdkVersion.SDK_TYPE);
            }
            if (context != null) {
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                if (debugLog) {
                    String appkeyByXML = UMUtils.getAppkeyByXML(origApplicationContext);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(appkeyByXML) && !str.equals(appkeyByXML)) {
                        UMLog.mutlInfo(UMLogCommon.SC_10011, 3, "", new String[]{"@", "#"}, new String[]{str, appkeyByXML});
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = UMUtils.getAppkeyByXML(origApplicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = UMUtils.getChannelByXML(origApplicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = "Unknown";
                }
                try {
                    UMUtils.setChannel(origApplicationContext, str2);
                    if (debugLog) {
                        Log.i(TAG, "channel is " + str2);
                    }
                    if (UMUtils.isMainProgress(origApplicationContext)) {
                        saveSDKComponent();
                    }
                    Class<?> cls2 = Class.forName("com.umeng.analytics.MobclickAgent");
                    if (cls2 != null) {
                        Method declaredMethod7 = cls2.getDeclaredMethod("init", new Class[]{Context.class});
                        if (declaredMethod7 != null) {
                            declaredMethod7.setAccessible(true);
                            declaredMethod7.invoke(cls2, new Object[]{origApplicationContext});
                            if (debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10003, 2, "");
                            }
                        }
                        if (!(Class.forName("com.umeng.analytics.game.UMGameAgent") == null || (declaredMethod6 = cls2.getDeclaredMethod("setGameScenarioType", new Class[]{Context.class})) == null)) {
                            declaredMethod6.setAccessible(true);
                            declaredMethod6.invoke(cls2, new Object[]{origApplicationContext});
                        }
                    }
                    if (!(b.a.indexOf("e") < 0 || (cls = Class.forName("com.umeng.analytics.MobclickAgent")) == null || (declaredMethod5 = cls.getDeclaredMethod("disableExceptionCatch", new Class[0])) == null)) {
                        declaredMethod5.setAccessible(true);
                        declaredMethod5.invoke(cls, new Object[0]);
                    }
                    Class<?> cls3 = Class.forName("com.umeng.message.MessageSharedPrefs");
                    if (!(cls3 == null || (declaredMethod4 = cls3.getDeclaredMethod("getInstance", new Class[]{Context.class})) == null || (invoke = declaredMethod4.invoke(cls3, new Object[]{origApplicationContext})) == null)) {
                        Method declaredMethod8 = cls3.getDeclaredMethod("setMessageAppKey", new Class[]{String.class});
                        if (declaredMethod8 != null) {
                            declaredMethod8.setAccessible(true);
                            declaredMethod8.invoke(invoke, new Object[]{str});
                            if (debugLog) {
                                UMLog uMLog = umDebugLog;
                                UMLog.mutlInfo(UMLogCommon.SC_10004, 2, "");
                            }
                        }
                        Method declaredMethod9 = cls3.getDeclaredMethod(KEY_METHOD_NAME_PUSH_SETCHANNEL, new Class[]{String.class});
                        if (declaredMethod9 != null) {
                            declaredMethod9.setAccessible(true);
                            declaredMethod9.invoke(invoke, new Object[]{str2});
                            if (debugLog) {
                                UMLog uMLog2 = umDebugLog;
                                UMLog.mutlInfo(UMLogCommon.SC_10005, 2, "");
                            }
                        }
                        if (TextUtils.isEmpty(str3)) {
                            boolean z = debugLog;
                        } else {
                            if (debugLog) {
                                Log.i(TAG, "push secret is " + str3);
                            }
                            Method declaredMethod10 = cls3.getDeclaredMethod("setMessageAppSecret", new Class[]{String.class});
                            if (declaredMethod10 != null) {
                                declaredMethod10.setAccessible(true);
                                declaredMethod10.invoke(invoke, new Object[]{str3});
                                if (debugLog) {
                                    UMLog uMLog3 = umDebugLog;
                                    UMLog.mutlInfo(UMLogCommon.SC_10009, 2, "");
                                }
                            }
                        }
                    }
                    Class<?> cls4 = getClass("com.umeng.socialize.UMShareAPI");
                    setFile(cls4, KEY_FILE_NAME_APPKEY, str);
                    if (!(cls4 == null || (declaredMethod3 = cls4.getDeclaredMethod("init", new Class[]{Context.class, String.class})) == null)) {
                        declaredMethod3.setAccessible(true);
                        declaredMethod3.invoke(cls4, new Object[]{origApplicationContext, str});
                        if (debugLog) {
                            UMLog.mutlInfo(UMLogCommon.SC_10006, 2, "");
                        }
                    }
                    if (!TextUtils.isEmpty(str)) {
                        UMUtils.setAppkey(origApplicationContext, str);
                        String lastAppkey = UMUtils.getLastAppkey(origApplicationContext);
                        if (!TextUtils.isEmpty(str) && !str.equals(lastAppkey)) {
                            if (!TextUtils.isEmpty(lastAppkey) && debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10008, 2, "");
                            }
                            UMUtils.setLastAppkey(origApplicationContext, str);
                        }
                        if (debugLog) {
                            Log.i(TAG, "current appkey is " + str + ", last appkey is " + lastAppkey);
                        }
                        AnalyticsConstants.setDeviceType(i);
                        Class<?> cls5 = Class.forName("com.umeng.error.UMError");
                        if (!(cls5 == null || (declaredMethod2 = cls5.getDeclaredMethod("init", new Class[]{Context.class})) == null)) {
                            declaredMethod2.setAccessible(true);
                            declaredMethod2.invoke(cls5, new Object[]{origApplicationContext});
                            if (debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10010, 2, "");
                            }
                        }
                        if (UMUtils.isMainProgress(origApplicationContext)) {
                            if (SdkVersion.SDK_TYPE != 1) {
                                Class<?> cls6 = Class.forName("com.umeng.commonsdk.UMConfigureImpl");
                                if (!(cls6 == null || (declaredMethod = cls6.getDeclaredMethod("init", new Class[]{Context.class})) == null)) {
                                    declaredMethod.setAccessible(true);
                                    declaredMethod.invoke(cls6, new Object[]{origApplicationContext});
                                }
                            } else {
                                a.a(origApplicationContext);
                            }
                        }
                        Method method = Class.forName("com.umeng.visual.UMVisualAgent").getMethod("init", new Class[]{Context.class, String.class});
                        if (!TextUtils.isEmpty(str)) {
                            method.invoke((Object) null, new Object[]{context, str});
                        } else if (AnalyticsConstants.UM_DEBUG) {
                            MLog.e("initDebugSDK appkey  is null");
                        }
                        UMGlobalContext.a aVar = new UMGlobalContext.a();
                        aVar.a = origApplicationContext;
                        aVar.b = i;
                        aVar.c = str3;
                        aVar.d = str;
                        aVar.e = str2;
                        aVar.f = b.a;
                        aVar.g = false;
                        aVar.h = UMFrUtils.getCurrentProcessName(origApplicationContext);
                        aVar.i = UMUtils.getAppVersionName(origApplicationContext);
                        aVar.j = UMUtils.isMainProgress(origApplicationContext);
                        UMGlobalContext.newUMGlobalContext(aVar);
                        synchronized (lockObject) {
                            isFinish = true;
                        }
                    } else if (debugLog) {
                        UMLog.aq(UMLogCommon.SC_10007, 0, "\\|");
                    }
                } catch (ClassNotFoundException e) {
                    Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("init", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                } catch (Exception e2) {
                } catch (Throwable th) {
                }
            } else if (debugLog) {
                Log.e(TAG, "context is null !!!");
            }
        } catch (Exception e22) {
        } catch (Throwable th2) {
        }
    }

    private static void invoke(Method method, Object obj, Object[] objArr) {
        if (method != null && obj != null) {
            try {
                method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
    }

    private static void invoke(Method method, Object[] objArr) {
        if (method != null) {
            try {
                method.invoke((Object) null, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
    }

    public static boolean isDebugLog() {
        return debugLog;
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

    private static void setCheckDevice(boolean z) {
        AnalyticsConstants.CHECK_DEVICE = z;
    }

    public static void setEncryptEnabled(boolean z) {
        b.a(z);
        UMSLEnvelopeBuild.setEncryptEnabled(z);
    }

    private static void setFile(Class<?> cls, String str, String str2) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, str2);
            } catch (Exception e) {
            }
        }
    }

    private static void setFile(Class<?> cls, String str, boolean z) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, Boolean.valueOf(z));
            } catch (Exception e) {
            }
        }
    }

    private static void setLatencyWindow(long j) {
        a.c = ((int) j) * SocializeConstants.CANCLE_RESULTCODE;
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

    public static void setProcessEvent(boolean z) {
        AnalyticsConstants.SUB_PROCESS_EVENT = z;
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
}
