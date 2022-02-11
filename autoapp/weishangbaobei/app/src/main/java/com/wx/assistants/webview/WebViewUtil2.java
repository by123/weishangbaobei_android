package com.wx.assistants.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.DownloadListener;
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

public class WebViewUtil2 {
    private LinearLayout errorPageLayout;
    private volatile WebViewUtil2 instance = null;
    public String loadUrl = "";
    /* access modifiers changed from: private */
    public TextView navText;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private WebChromeClient webChromeClient = new WebChromeClient() {
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return true;
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (WebViewUtil2.this.navText != null) {
                String charSequence = WebViewUtil2.this.navText.getText().toString();
                if ((charSequence == null || "".equals(charSequence)) && str != null && !"".equals(str)) {
                    WebViewUtil2.this.navText.setText(str);
                }
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            if (WebViewUtil2.this.progressBar != null) {
                WebViewUtil2.this.progressBar.setProgress(i);
                if (i > 85 && WebViewUtil2.this.progressBar.getVisibility() == 0) {
                    WebViewUtil2.this.progressBar.setVisibility(8);
                }
            }
        }
    };
    private WebView webView;
    private WebViewClient webViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            WebViewUtil2.this.progressBar.setVisibility(8);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewUtil2.this.progressBar.setVisibility(0);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    };

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

    public void initWebView(Activity activity, WebView webView2, TextView textView, ProgressBar progressBar2, LinearLayout linearLayout, boolean z) {
        this.errorPageLayout = linearLayout;
        this.errorPageLayout.setVisibility(8);
        this.progressBar = progressBar2;
        this.navText = textView;
        this.webView = webView2;
        this.webView.setBackgroundColor(0);
        this.webView.setDownloadListener(new MyWebViewDownLoadListener(activity));
        this.webView.setInitialScale(1);
        WebSettings settings = this.webView.getSettings();
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
        if (z) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(2);
        }
        webView2.setWebChromeClient(this.webChromeClient);
        webView2.setWebViewClient(this.webViewClient);
    }

    public void pause() {
        if (this.loadUrl != null && this.webView != null) {
            this.webView.loadUrl("");
        }
    }

    public void loadUrl(Activity activity, String str) {
        this.loadUrl = str;
        if (NetworkUtil.isAvailable(activity)) {
            if (URLUtil.isValidUrl(this.loadUrl)) {
                this.webView.loadUrl(this.loadUrl);
            }
            this.errorPageLayout.setVisibility(8);
            this.webView.setVisibility(0);
            return;
        }
        this.errorPageLayout.setVisibility(0);
        this.webView.setVisibility(8);
    }

    public String getNewsJavascriptUrl(String str, List<String> list) {
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
