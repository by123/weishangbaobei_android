package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity target;
    private View view2131296657;
    private View view2131297425;
    private View view2131297448;
    private View view2131297627;
    private View view2131297628;

    @UiThread
    public LoginActivity_ViewBinding(LoginActivity loginActivity) {
        this(loginActivity, loginActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.LoginActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.wx.assistants.activity.LoginActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v23, types: [com.wx.assistants.activity.LoginActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [android.view.View$OnClickListener, com.wx.assistants.activity.LoginActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v42, types: [com.wx.assistants.activity.LoginActivity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public LoginActivity_ViewBinding(final LoginActivity loginActivity, View view) {
        this.target = loginActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        loginActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297448, "field 'switchLogin' and method 'onViewClicked'");
        loginActivity.switchLogin = (TextView) Utils.castView(findRequiredView2, 2131297448, "field 'switchLogin'", TextView.class);
        this.view2131297448 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginActivity.onViewClicked(view);
            }
        });
        loginActivity.numberEditText = (EditText) Utils.findRequiredViewAsType(view, 2131297081, "field 'numberEditText'", EditText.class);
        loginActivity.imgVerificationEditText = (EditText) Utils.findRequiredViewAsType(view, 2131296782, "field 'imgVerificationEditText'", EditText.class);
        loginActivity.smsVerificationEditText = (EditText) Utils.findRequiredViewAsType(view, 2131297393, "field 'smsVerificationEditText'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297628, "field 'verification_img' and method 'onViewClicked'");
        loginActivity.verification_img = (ImageView) Utils.castView(findRequiredView3, 2131297628, "field 'verification_img'", ImageView.class);
        this.view2131297628 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297627, "field 'verification_btn' and method 'onViewClicked'");
        loginActivity.verification_btn = (TextView) Utils.castView(findRequiredView4, 2131297627, "field 'verification_btn'", TextView.class);
        this.view2131297627 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginActivity.onViewClicked(view);
            }
        });
        loginActivity.smsLoginLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297392, "field 'smsLoginLayout'", LinearLayout.class);
        loginActivity.passwordLoginLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297117, "field 'passwordLoginLayout'", LinearLayout.class);
        loginActivity.passwordEditText = (EditText) Utils.findRequiredViewAsType(view, 2131297116, "field 'passwordEditText'", EditText.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296657, "field 'eyeLayout' and method 'onViewClicked'");
        loginActivity.eyeLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131296657, "field 'eyeLayout'", LinearLayout.class);
        this.view2131296657 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginActivity.onViewClicked(view);
            }
        });
        loginActivity.eyeImg = (ImageView) Utils.findRequiredViewAsType(view, 2131296656, "field 'eyeImg'", ImageView.class);
        loginActivity.passwordLoginPoint = (TextView) Utils.findRequiredViewAsType(view, 2131297118, "field 'passwordLoginPoint'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        LoginActivity loginActivity = this.target;
        if (loginActivity != null) {
            this.target = null;
            loginActivity.startWx = null;
            loginActivity.switchLogin = null;
            loginActivity.numberEditText = null;
            loginActivity.imgVerificationEditText = null;
            loginActivity.smsVerificationEditText = null;
            loginActivity.verification_img = null;
            loginActivity.verification_btn = null;
            loginActivity.smsLoginLayout = null;
            loginActivity.passwordLoginLayout = null;
            loginActivity.passwordEditText = null;
            loginActivity.eyeLayout = null;
            loginActivity.eyeImg = null;
            loginActivity.passwordLoginPoint = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297448.setOnClickListener((View.OnClickListener) null);
            this.view2131297448 = null;
            this.view2131297628.setOnClickListener((View.OnClickListener) null);
            this.view2131297628 = null;
            this.view2131297627.setOnClickListener((View.OnClickListener) null);
            this.view2131297627 = null;
            this.view2131296657.setOnClickListener((View.OnClickListener) null);
            this.view2131296657 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
