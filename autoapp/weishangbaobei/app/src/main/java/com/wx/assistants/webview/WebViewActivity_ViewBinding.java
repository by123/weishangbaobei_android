package com.wx.assistants.webview;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class WebViewActivity_ViewBinding implements Unbinder {
    private WebViewActivity target;
    private View view2131297049;

    @UiThread
    public WebViewActivity_ViewBinding(WebViewActivity webViewActivity) {
        this(webViewActivity, webViewActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.webview.WebViewActivity_ViewBinding$1] */
    @UiThread
    public WebViewActivity_ViewBinding(final WebViewActivity webViewActivity, View view) {
        this.target = webViewActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        webViewActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webViewActivity.onViewClicked(view);
            }
        });
        webViewActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        webViewActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, 2131297162, "field 'progressBar'", ProgressBar.class);
        webViewActivity.errorLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296640, "field 'errorLayout'", LinearLayout.class);
        webViewActivity.safeWebView = (WebView) Utils.findRequiredViewAsType(view, 2131297272, "field 'safeWebView'", WebView.class);
        webViewActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        webViewActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        webViewActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        WebViewActivity webViewActivity = this.target;
        if (webViewActivity != null) {
            this.target = null;
            webViewActivity.navBack = null;
            webViewActivity.navTitle = null;
            webViewActivity.progressBar = null;
            webViewActivity.errorLayout = null;
            webViewActivity.safeWebView = null;
            webViewActivity.refreshLayout = null;
            webViewActivity.navRightText = null;
            webViewActivity.navRightLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
