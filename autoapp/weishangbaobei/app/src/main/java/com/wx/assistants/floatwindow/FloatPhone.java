package com.wx.assistants.floatwindow;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.wx.assistants.utils.LogUtils;

class FloatPhone extends FloatView {
    private boolean isRemove = false;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final WindowManager.LayoutParams mLayoutParams;
    /* access modifiers changed from: private */
    public PermissionListener mPermissionListener;
    /* access modifiers changed from: private */
    public View mView;
    /* access modifiers changed from: private */
    public final WindowManager mWindowManager;
    private int mX;
    private int mY;

    FloatPhone(Context context, PermissionListener permissionListener) {
        this.mContext = context;
        this.mPermissionListener = permissionListener;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams.format = 1;
        this.mLayoutParams.flags = 680;
        this.mLayoutParams.windowAnimations = 0;
    }

    FloatPhone(Context context, PermissionListener permissionListener, boolean z) {
        this.mContext = context;
        this.mPermissionListener = permissionListener;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams.format = 1;
        LogUtils.log("WS_YY.FloatPhone." + z);
        if (z) {
            this.mLayoutParams.flags = 680;
        } else {
            this.mLayoutParams.flags = 552;
        }
        this.mLayoutParams.windowAnimations = 0;
    }

    public void setSize(int i, int i2) {
        this.mLayoutParams.width = i;
        this.mLayoutParams.height = i2;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setGravity(int i, int i2, int i3) {
        this.mLayoutParams.gravity = i;
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        this.mX = i2;
        layoutParams.x = i2;
        WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
        this.mY = i3;
        layoutParams2.y = i3;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:9|10|11|12|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0058, code lost:
        r3.mWindowManager.removeView(r3.mView);
        com.wx.assistants.floatwindow.LogUtil.e("TYPE_TOAST 失败");
        req();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init() {
        /*
            r3 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 25
            if (r0 < r1) goto L_0x000a
            r3.req()
            goto L_0x0067
        L_0x000a:
            boolean r0 = com.wx.assistants.floatwindow.Miui.rom()
            r1 = 2002(0x7d2, float:2.805E-42)
            if (r0 == 0) goto L_0x0030
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r0 < r2) goto L_0x001c
            r3.req()
            goto L_0x0067
        L_0x001c:
            java.lang.String r0 = "WS_BABY_FLOAT_TYPE_PHONE"
            com.wx.assistants.floatwindow.LogUtil.d(r0)
            android.view.WindowManager$LayoutParams r0 = r3.mLayoutParams
            r0.type = r1
            android.content.Context r0 = r3.mContext
            com.wx.assistants.floatwindow.FloatPhone$1 r1 = new com.wx.assistants.floatwindow.FloatPhone$1
            r1.<init>()
            com.wx.assistants.floatwindow.Miui.req(r0, r1)
            goto L_0x0067
        L_0x0030:
            android.view.WindowManager$LayoutParams r0 = r3.mLayoutParams     // Catch:{ Exception -> 0x0043 }
            r0.type = r1     // Catch:{ Exception -> 0x0043 }
            android.view.WindowManager r0 = r3.mWindowManager     // Catch:{ Exception -> 0x0043 }
            android.view.View r1 = r3.mView     // Catch:{ Exception -> 0x0043 }
            android.view.WindowManager$LayoutParams r2 = r3.mLayoutParams     // Catch:{ Exception -> 0x0043 }
            r0.addView(r1, r2)     // Catch:{ Exception -> 0x0043 }
            java.lang.String r0 = "WS_BABY_FLOAT_TYPE_PHONE"
            com.wx.assistants.floatwindow.LogUtil.d(r0)     // Catch:{ Exception -> 0x0043 }
            goto L_0x0067
        L_0x0043:
            android.view.WindowManager$LayoutParams r0 = r3.mLayoutParams     // Catch:{ Exception -> 0x0058 }
            r1 = 2005(0x7d5, float:2.81E-42)
            r0.type = r1     // Catch:{ Exception -> 0x0058 }
            android.view.WindowManager r0 = r3.mWindowManager     // Catch:{ Exception -> 0x0058 }
            android.view.View r1 = r3.mView     // Catch:{ Exception -> 0x0058 }
            android.view.WindowManager$LayoutParams r2 = r3.mLayoutParams     // Catch:{ Exception -> 0x0058 }
            r0.addView(r1, r2)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r0 = "WS_BABY_FLOAT_TYPE_TYPE_TOAST"
            com.wx.assistants.floatwindow.LogUtil.d(r0)     // Catch:{ Exception -> 0x0058 }
            goto L_0x0067
        L_0x0058:
            android.view.WindowManager r0 = r3.mWindowManager
            android.view.View r1 = r3.mView
            r0.removeView(r1)
            java.lang.String r0 = "TYPE_TOAST 失败"
            com.wx.assistants.floatwindow.LogUtil.e(r0)
            r3.req()
        L_0x0067:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.floatwindow.FloatPhone.init():void");
    }

    private void req() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mLayoutParams.type = 2038;
            LogUtil.d("WS_BABY_FLOAT_TYPE_APPLICATION_OVERLAY");
        } else {
            this.mLayoutParams.type = 2002;
            LogUtil.d("WS_BABY_FLOAT_TYPE_TYPE_PHONE");
        }
        FloatActivity.request(this.mContext, new PermissionListener() {
            public void onSuccess() {
                FloatPhone.this.mWindowManager.addView(FloatPhone.this.mView, FloatPhone.this.mLayoutParams);
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onSuccess();
                }
            }

            public void onFail() {
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onFail();
                }
            }
        });
    }

    public void dismiss() {
        this.isRemove = true;
        this.mWindowManager.removeView(this.mView);
    }

    public void updateXY(int i, int i2) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mX = i;
            layoutParams.x = i;
            WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
            this.mY = i2;
            layoutParams2.y = i2;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateX(int i) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mX = i;
            layoutParams.x = i;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateY(int i) {
        if (!this.isRemove) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            this.mY = i;
            layoutParams.y = i;
            this.mWindowManager.updateViewLayout(this.mView, this.mLayoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public int getX() {
        return this.mX;
    }

    /* access modifiers changed from: package-private */
    public int getY() {
        return this.mY;
    }
}
