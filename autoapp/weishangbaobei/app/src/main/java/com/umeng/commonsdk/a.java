package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.statistics.common.ULog;

/* compiled from: UMConfigureInternation */
public class a {
    private static boolean a;

    public static synchronized void a(final Context context) {
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
                                } catch (Throwable unused) {
                                }
                            }
                        }).start();
                        a = true;
                    }
                } catch (Throwable th) {
                    ULog.e("internal", "e is " + th.getMessage());
                }
            }
        }
        return;
    }
}
