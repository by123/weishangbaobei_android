package com.umeng.socialize.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.stub.StubApp;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import java.lang.reflect.Method;

public abstract class BaseDialog extends Dialog {
    public final ResContainer R;
    public Activity mActivity;
    public View mContent;
    public Context mContext;
    public int mFlag = 0;
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1 && BaseDialog.this.mProgressbar != null) {
                BaseDialog.this.mProgressbar.setVisibility(8);
            }
            int i = message.what;
        }
    };
    public SHARE_MEDIA mPlatform;
    public View mProgressbar;
    public Bundle mValues;
    public String mWaitUrl = "error";
    public WebView mWebView;
    public TextView titleMidTv;

    public abstract void setClient(WebView webView);

    public BaseDialog(Activity activity, SHARE_MEDIA share_media) {
        super(activity, ResContainer.get(activity).style("umeng_socialize_popup_dialog"));
        this.mContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
        this.R = ResContainer.get(this.mContext);
        this.mActivity = activity;
        this.mPlatform = share_media;
    }

    public void setWaitUrl(String str) {
        this.mWaitUrl = str;
    }

    public void initViews() {
        setOwnerActivity(this.mActivity);
        int layout = this.R.layout("umeng_socialize_oauth_dialog");
        int id = this.R.id("umeng_socialize_follow");
        String str = null;
        this.mContent = ((LayoutInflater) this.mActivity.getSystemService("layout_inflater")).inflate(layout, (ViewGroup) null);
        final View findViewById = this.mContent.findViewById(id);
        findViewById.setVisibility(8);
        int id2 = this.R.id("progress_bar_parent");
        int id3 = this.R.id("umeng_back");
        int id4 = this.R.id("umeng_share_btn");
        int id5 = this.R.id("umeng_title");
        int id6 = this.R.id("umeng_socialize_titlebar");
        this.mProgressbar = this.mContent.findViewById(id2);
        this.mProgressbar.setVisibility(0);
        ((RelativeLayout) this.mContent.findViewById(id3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDialog.this.dismiss();
            }
        });
        this.mContent.findViewById(id4).setVisibility(8);
        this.titleMidTv = (TextView) this.mContent.findViewById(id5);
        if (this.mPlatform.toString().equals("SINA")) {
            str = UmengText.SINA;
        } else if (this.mPlatform.toString().equals("RENREN")) {
            str = UmengText.RENREN;
        } else if (this.mPlatform.toString().equals("DOUBAN")) {
            str = UmengText.DOUBAN;
        } else if (this.mPlatform.toString().equals("TENCENT")) {
            str = UmengText.TENCENT;
        }
        TextView textView = this.titleMidTv;
        textView.setText("授权" + str);
        setUpWebView();
        final View findViewById2 = this.mContent.findViewById(id6);
        final int dip2Px = SocializeUtils.dip2Px(this.mContext, 200.0f);
        AnonymousClass3 r4 = new FrameLayout(this.mContext) {
            /* access modifiers changed from: protected */
            public void onSizeChanged(int i, int i2, int i3, int i4) {
                super.onSizeChanged(i, i2, i3, i4);
                if (!SocializeUtils.isFloatWindowStyle(BaseDialog.this.mContext)) {
                    a(findViewById, findViewById2, dip2Px, i2);
                }
            }

            private void a(final View view, final View view2, int i, int i2) {
                if (view2.getVisibility() == 0 && i2 < i) {
                    BaseDialog.this.mHandler.post(new Runnable() {
                        public void run() {
                            view2.setVisibility(8);
                            if (view.getVisibility() == 0) {
                                view.setVisibility(8);
                            }
                            AnonymousClass3.this.requestLayout();
                        }
                    });
                } else if (view2.getVisibility() != 0 && i2 >= i) {
                    BaseDialog.this.mHandler.post(new Runnable() {
                        public void run() {
                            view2.setVisibility(0);
                            AnonymousClass3.this.requestLayout();
                        }
                    });
                }
            }
        };
        r4.addView(this.mContent, -1, -1);
        setContentView(r4);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (SocializeUtils.isFloatWindowStyle(this.mContext)) {
            int[] floatWindowSize = SocializeUtils.getFloatWindowSize(this.mContext);
            attributes.width = floatWindowSize[0];
            attributes.height = floatWindowSize[1];
        } else {
            attributes.height = -1;
            attributes.width = -1;
        }
        attributes.gravity = 17;
    }

    public boolean setUpWebView() {
        this.mWebView = (WebView) this.mContent.findViewById(this.R.id("webView"));
        setClient(this.mWebView);
        this.mWebView.requestFocusFromTouch();
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.getSettings().setCacheMode(2);
        this.mWebView.setBackgroundColor(-1);
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 8) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 8) {
            settings.setLoadWithOverviewMode(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setAppCacheEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            Class<WebSettings> cls = WebSettings.class;
            try {
                Method declaredMethod = cls.getDeclaredMethod("setDisplayZoomControls", new Class[]{Boolean.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(settings, new Object[]{false});
            } catch (Exception e) {
                Log.um(e.getMessage());
            }
        }
        try {
            if (this.mPlatform == SHARE_MEDIA.RENREN) {
                CookieSyncManager.createInstance(this.mContext);
                CookieManager.getInstance().removeAllCookie();
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseWebView() {
        /*
            r2 = this;
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ Exception -> 0x000d }
            android.view.ViewParent r0 = r0.getParent()     // Catch:{ Exception -> 0x000d }
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch:{ Exception -> 0x000d }
            android.webkit.WebView r1 = r2.mWebView     // Catch:{ Exception -> 0x000d }
            r0.removeView(r1)     // Catch:{ Exception -> 0x000d }
        L_0x000d:
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ Exception -> 0x0012 }
            r0.removeAllViews()     // Catch:{ Exception -> 0x0012 }
        L_0x0012:
            r0 = 0
            r2.mWebView = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.view.BaseDialog.releaseWebView():void");
    }
}
