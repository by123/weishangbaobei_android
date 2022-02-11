package com.umeng.socialize.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.stub.StubApp;
import com.umeng.socialize.Config;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.URLBuilder;
import com.umeng.socialize.utils.UmengText;
import java.lang.ref.WeakReference;

public class OauthDialog extends BaseDialog {
    private static final String BASE_URL = "https://log.umsns.com/";
    private static final String TAG = "OauthDialog";
    /* access modifiers changed from: private */
    public static String mRedirectUri = "error";
    private a mListener;

    static class a {
        private UMAuthListener a = null;
        private SHARE_MEDIA b;
        private int c;

        public a(UMAuthListener uMAuthListener, SHARE_MEDIA share_media) {
            this.a = uMAuthListener;
            this.b = share_media;
        }

        public void a(Bundle bundle) {
            if (this.a != null) {
                this.a.onComplete(this.b, this.c, SocializeUtils.bundleTomap(bundle));
            }
        }

        public void a(Exception exc) {
            if (this.a != null) {
                this.a.onError(this.b, this.c, exc);
            }
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel(this.b, this.c);
            }
        }
    }

    private static class b extends WebChromeClient {
        private WeakReference<OauthDialog> a;

        private b(OauthDialog oauthDialog) {
            this.a = new WeakReference<>(oauthDialog);
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog == null) {
                return;
            }
            if (i < 90) {
                oauthDialog.mProgressbar.setVisibility(0);
            } else {
                oauthDialog.mHandler.sendEmptyMessage(1);
            }
        }
    }

    private static class c extends WebViewClient {
        private WeakReference<OauthDialog> a;

        private c(OauthDialog oauthDialog) {
            this.a = new WeakReference<>(oauthDialog);
        }

        private void a(String str) {
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                oauthDialog.mFlag = 1;
                oauthDialog.mValues = SocializeUtils.parseUrl(str);
                if (oauthDialog.isShowing()) {
                    SocializeUtils.safeCloseDialog(oauthDialog);
                }
            }
        }

        private void b(String str) {
            Log.d(OauthDialog.TAG, OauthDialog.TAG + str);
            Log.e("gggggg url=" + str);
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                oauthDialog.mFlag = 1;
                oauthDialog.mValues = SocializeNetUtils.parseUrl(str);
                if (oauthDialog.isShowing()) {
                    SocializeUtils.safeCloseDialog(oauthDialog);
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                oauthDialog.mHandler.sendEmptyMessage(1);
                super.onPageFinished(webView, str);
                if (oauthDialog.mFlag == 0 && str.contains(oauthDialog.mWaitUrl)) {
                    a(str);
                }
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                String str2 = "";
                if (str.contains("?ud_get=")) {
                    str2 = oauthDialog.decrypt(str);
                }
                if (!str2.contains("access_key") || !str2.contains("access_secret")) {
                    if (str.startsWith(OauthDialog.mRedirectUri)) {
                        b(str);
                    }
                } else if (str.contains(oauthDialog.mWaitUrl)) {
                    a(str);
                    return;
                } else {
                    return;
                }
            }
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Log.e(OauthDialog.TAG, "onReceivedError: " + str2 + "\nerrCode: " + i + " description:" + str);
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                View view = oauthDialog.mProgressbar;
                if (view.getVisibility() == 0) {
                    view.setVisibility(8);
                }
            }
            super.onReceivedError(webView, i, str, str2);
            if (oauthDialog != null) {
                SocializeUtils.safeCloseDialog(oauthDialog);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            OauthDialog oauthDialog = this.a == null ? null : (OauthDialog) this.a.get();
            if (oauthDialog != null) {
                Context origApplicationContext = StubApp.getOrigApplicationContext(oauthDialog.mContext.getApplicationContext());
                if (!DeviceConfig.isNetworkAvailable(origApplicationContext)) {
                    Toast.makeText(origApplicationContext, UmengText.NET_INAVALIBLE, 0).show();
                    return true;
                }
                if (str.contains("?ud_get=")) {
                    str = oauthDialog.decrypt(str);
                }
                if (str.contains(oauthDialog.mWaitUrl)) {
                    a(str);
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    public OauthDialog(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        super(activity, share_media);
        this.mListener = new a(uMAuthListener, share_media);
        initViews();
    }

    /* access modifiers changed from: private */
    public String decrypt(String str) {
        try {
            String[] split = str.split("ud_get=");
            split[1] = AesHelper.decryptNoPadding(split[1], "UTF-8").trim();
            return split[0] + split[1];
        } catch (Exception e) {
            Log.um(UmengText.DECRPT_ERROR);
            e.printStackTrace();
            return str;
        }
    }

    private WebViewClient getAdapterWebViewClient() {
        return new c();
    }

    private String getUrl(SHARE_MEDIA share_media) {
        URLBuilder uRLBuilder = new URLBuilder(this.mContext);
        uRLBuilder.setHost(BASE_URL).setPath("share/auth/").setAppkey(SocializeUtils.getAppkey(this.mContext)).setEntityKey(Config.EntityKey).withMedia(share_media).withOpId("10").withSessionId(Config.SessionId).withUID(Config.UID);
        return uRLBuilder.toEncript();
    }

    public void dismiss() {
        if (this.mValues != null) {
            String string = this.mValues.getString(SocializeProtocolConstants.PROTOCOL_KEY_UID);
            String string2 = this.mValues.getString("error_code");
            String string3 = this.mValues.getString("error_description");
            if (this.mPlatform == SHARE_MEDIA.SINA && !TextUtils.isEmpty(string3)) {
                a aVar = this.mListener;
                aVar.a((Exception) new SocializeException(UmengErrorCode.AuthorizeFailed.getMessage() + "errorcode:" + string2 + " message:" + string3));
            } else if (TextUtils.isEmpty(string)) {
                a aVar2 = this.mListener;
                aVar2.a((Exception) new SocializeException(UmengErrorCode.AuthorizeFailed.getMessage() + "unfetch usid..."));
            } else {
                Log.d(TAG, "### dismiss ");
                this.mValues.putString("accessToken", this.mValues.getString("access_key"));
                this.mValues.putString("expiration", this.mValues.getString("expires_in"));
                this.mListener.a(this.mValues);
            }
        } else {
            this.mListener.onCancel();
        }
        super.dismiss();
        releaseWebView();
    }

    public void setClient(WebView webView) {
        webView.setWebViewClient(getAdapterWebViewClient());
        this.mWebView.setWebChromeClient(new b());
    }

    public void setmRedirectUri(String str) {
        mRedirectUri = str;
    }

    public void show() {
        super.show();
        this.mValues = null;
        if (this.mPlatform == SHARE_MEDIA.SINA) {
            this.mWebView.loadUrl(this.mWaitUrl);
            return;
        }
        this.mWebView.loadUrl(getUrl(this.mPlatform));
    }
}
