package com.wx.assistants.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wx.assistants.utils.NetworkUtil;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.util.List;

public class WebViewUtil {
    private static LinearLayout errorPageLayout = null;
    private static volatile WebViewUtil instance = null;
    public static String loadUrl = "";
    /* access modifiers changed from: private */
    public static TextView navText;
    /* access modifiers changed from: private */
    public static ProgressBar progressBar;
    private static WebView webView;
    private WebChromeClient webChromeClient = new WebChromeClient() {
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return true;
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            TextView access$100 = WebViewUtil.navText;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (WebViewUtil.progressBar != null) {
                WebViewUtil.progressBar.setProgress(i);
                if (i > 85 && WebViewUtil.progressBar.getVisibility() == 0) {
                    WebViewUtil.progressBar.setVisibility(8);
                }
            }
        }
    };
    private WebViewClient webViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            WebViewUtil.progressBar.setVisibility(8);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewUtil.progressBar.setVisibility(0);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    };

    public static WebViewUtil getInstance() {
        if (instance == null) {
            synchronized (WebViewUtil.class) {
                if (instance == null) {
                    instance = new WebViewUtil();
                }
            }
        }
        return instance;
    }

    private WebViewUtil() {
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        private Activity context;

        public MyWebViewDownLoadListener(Activity activity) {
            this.context = activity;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            try {
                this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception unused) {
            }
        }
    }

    public synchronized void initWebView(Activity activity, WebView webView2, TextView textView, ProgressBar progressBar2, LinearLayout linearLayout) {
        errorPageLayout = linearLayout;
        errorPageLayout.setVisibility(8);
        progressBar = progressBar2;
        navText = textView;
        webView = webView2;
        webView.setBackgroundColor(0);
        webView.setDownloadListener(new MyWebViewDownLoadListener(activity));
        webView.setInitialScale(1);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(62914560);
        webView2.setWebChromeClient(this.webChromeClient);
        webView2.setWebViewClient(this.webViewClient);
        webView.addJavascriptInterface(new NativeAndJsInterface(), "ws_baby_android");
    }

    class NativeAndJsInterface {
        @JavascriptInterface
        public void goAppLogin() {
        }

        @JavascriptInterface
        public void setMaterialText(String str) {
        }

        NativeAndJsInterface() {
        }
    }

    public synchronized void loadUrl(Activity activity, String str) {
        loadUrl = str;
        if (NetworkUtil.isAvailable(activity)) {
            if (URLUtil.isValidUrl(loadUrl)) {
                webView.loadUrl(loadUrl);
            }
            errorPageLayout.setVisibility(8);
            webView.setVisibility(0);
        } else {
            errorPageLayout.setVisibility(0);
            webView.setVisibility(8);
        }
    }

    public static String getNewsJavascriptUrl(String str, List<String> list) {
        if (list != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append("'");
                stringBuffer.append(list.get(i));
                stringBuffer.append("'");
                if (i != list.size() - 1) {
                    stringBuffer.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                }
            }
            return "javascript:" + str + "(" + stringBuffer + ");";
        }
        return "javascript:" + str + "();";
    }

    public static String getValueByName(String str, String str2) {
        for (String str3 : str.substring(str.indexOf("?") + 1).split("&")) {
            if (str3.contains(str2)) {
                return str3.replace(str2 + "=", "");
            }
        }
        return "";
    }
}
