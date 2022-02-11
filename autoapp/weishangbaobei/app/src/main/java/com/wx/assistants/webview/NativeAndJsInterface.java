package com.wx.assistants.webview;

import android.webkit.JavascriptInterface;

interface NativeAndJsInterface {
    @JavascriptInterface
    void getMaterialText(String str);

    @JavascriptInterface
    void goAppLogin();
}
