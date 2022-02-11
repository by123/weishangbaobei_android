package com.wx.assistants.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.wx.assistants.activity.LoginActivity;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.HotMaterialTextBean;
import org.greenrobot.eventbus.EventBus;

public class WebViewActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public static boolean isCanBackUp = false;
    private static boolean isNeedTitle = true;
    private static boolean isUseCache = true;
    @BindView(2131296640)
    LinearLayout errorLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    private LinearLayout navigationLayout;
    @BindView(2131297162)
    ProgressBar progressBar;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297272)
    WebView safeWebView;
    private String title = "";
    /* access modifiers changed from: private */
    public String url = "";
    /* access modifiers changed from: private */
    public WebViewUtil2 webViewUtil2;

    static {
        StubApp.interface11(12434);
    }

    @JavascriptInterface
    public void goToLogin(String str) {
    }

    /* access modifiers changed from: protected */
    public native void onCreate(@Nullable Bundle bundle);

    public static void startActivity(Context context, String str, String str2) {
        if (context != null) {
            isUseCache = true;
            isCanBackUp = false;
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            context.startActivity(intent);
        }
    }

    public static void startActivity(Context context, String str, String str2, boolean z, boolean z2) {
        if (context != null) {
            isUseCache = z;
            isCanBackUp = z2;
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            context.startActivity(intent);
        }
    }

    public static void startActivity(Context context, String str, String str2, boolean z) {
        if (context != null) {
            isUseCache = z;
            isCanBackUp = false;
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            context.startActivity(intent);
        }
    }

    public static void startNoTitleActivity(Context context, String str, String str2) {
        if (context != null) {
            isUseCache = true;
            isNeedTitle = false;
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.webViewUtil2 != null) {
            this.webViewUtil2.pause();
        }
    }

    class NativeAndJsInterface {
        NativeAndJsInterface() {
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.webview.WebViewActivity] */
        @JavascriptInterface
        public void goAppLogin() {
            Intent intent = new Intent(WebViewActivity.this, LoginActivity.class);
            intent.putExtra("isNeedBack", false);
            WebViewActivity.this.startActivity(intent);
        }

        @JavascriptInterface
        public void closeWebView() {
            WebViewActivity.this.back();
        }

        @JavascriptInterface
        public void backUp() {
            if (!WebViewActivity.isCanBackUp || !WebViewActivity.this.safeWebView.canGoBack()) {
                WebViewActivity.this.back();
            } else {
                WebViewActivity.this.safeWebView.goBack();
            }
        }

        @JavascriptInterface
        public void setMaterialText(String str) {
            EventBus.getDefault().post(new HotMaterialTextBean(str));
            WebViewActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void initViewListener() {
        if (this.refreshLayout != null) {
            this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [com.wx.assistants.webview.WebViewActivity, android.app.Activity] */
                public void onRefresh(RefreshLayout refreshLayout) {
                    WebViewActivity.this.webViewUtil2.loadUrl(WebViewActivity.this, WebViewActivity.this.url);
                    if (WebViewActivity.this.refreshLayout != null) {
                        WebViewActivity.this.refreshLayout.finishRefresh();
                    }
                }
            });
        }
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            if (!isCanBackUp || !this.safeWebView.canGoBack()) {
                back();
            } else {
                this.safeWebView.goBack();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!isCanBackUp || !this.safeWebView.canGoBack()) {
            back();
            return true;
        }
        this.safeWebView.goBack();
        return true;
    }
}
