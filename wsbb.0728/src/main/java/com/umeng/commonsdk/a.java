package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;

public class a {
    private static boolean a;

    public static void a(final Context context) {
        synchronized (a.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName) && UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                                        UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.m, b.a(context).a(), (Object) null);
                                    }
                                } catch (Throwable th) {
                                }
                            }
                        }).start();
                        a = true;
                    }
                } catch (Throwable th) {
                    Class<a> cls = a.class;
                    throw th;
                }
            }
        }
    }
}
