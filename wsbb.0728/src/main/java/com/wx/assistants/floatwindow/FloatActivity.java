package com.wx.assistants.floatwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

public class FloatActivity extends Activity {
    private static PermissionListener mPermissionListener;
    /* access modifiers changed from: private */
    public static List<PermissionListener> mPermissionListenerList;

    static {
        StubApp.interface11(9810);
    }

    /* JADX INFO: finally extract failed */
    static void request(Context context, PermissionListener permissionListener) {
        synchronized (FloatActivity.class) {
            try {
                if (PermissionUtil.hasPermission(context)) {
                    permissionListener.onSuccess();
                    return;
                }
                if (mPermissionListenerList == null) {
                    mPermissionListenerList = new ArrayList();
                    mPermissionListener = new PermissionListener() {
                        public void onFail() {
                            for (PermissionListener onFail : FloatActivity.mPermissionListenerList) {
                                onFail.onFail();
                            }
                            FloatActivity.mPermissionListenerList.clear();
                        }

                        public void onSuccess() {
                            for (PermissionListener onSuccess : FloatActivity.mPermissionListenerList) {
                                onSuccess.onSuccess();
                            }
                            FloatActivity.mPermissionListenerList.clear();
                        }
                    };
                    Intent intent = new Intent(context, FloatActivity.class);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
                mPermissionListenerList.add(permissionListener);
            } catch (Throwable th) {
                Class<FloatActivity> cls = FloatActivity.class;
                throw th;
            }
        }
    }

    @RequiresApi(api = 23)
    private void requestAlertWindowPermission() {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 756232212);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 756232212 && mPermissionListener != null) {
            if (PermissionUtil.hasPermissionOnActivityResult(this)) {
                mPermissionListener.onSuccess();
            } else {
                mPermissionListener.onFail();
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);
}
