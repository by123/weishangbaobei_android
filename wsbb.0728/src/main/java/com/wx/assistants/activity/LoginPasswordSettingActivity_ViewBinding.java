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
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class LoginPasswordSettingActivity_ViewBinding implements Unbinder {
    private LoginPasswordSettingActivity target;
    private View view2131296657;
    private View view2131297049;
    private View view2131297437;
    private View view2131297625;

    @UiThread
    public LoginPasswordSettingActivity_ViewBinding(LoginPasswordSettingActivity loginPasswordSettingActivity) {
        this(loginPasswordSettingActivity, loginPasswordSettingActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.LoginPasswordSettingActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.LoginPasswordSettingActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v32, types: [android.view.View$OnClickListener, com.wx.assistants.activity.LoginPasswordSettingActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v40, types: [android.view.View$OnClickListener, com.wx.assistants.activity.LoginPasswordSettingActivity_ViewBinding$4] */
    @UiThread
    public LoginPasswordSettingActivity_ViewBinding(final LoginPasswordSettingActivity loginPasswordSettingActivity, View view) {
        this.target = loginPasswordSettingActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297437, "field 'submitSetting' and method 'onViewClicked'");
        loginPasswordSettingActivity.submitSetting = (Button) Utils.castView(findRequiredView, 2131297437, "field 'submitSetting'", Button.class);
        this.view2131297437 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginPasswordSettingActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        loginPasswordSettingActivity.navBack = (LinearLayout) Utils.castView(findRequiredView2, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginPasswordSettingActivity.onViewClicked(view);
            }
        });
        loginPasswordSettingActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        loginPasswordSettingActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        loginPasswordSettingActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        loginPasswordSettingActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        loginPasswordSettingActivity.numberText = (TextView) Utils.findRequiredViewAsType(view, 2131297084, "field 'numberText'", TextView.class);
        loginPasswordSettingActivity.verificationEt = (EditText) Utils.findRequiredViewAsType(view, 2131297626, "field 'verificationEt'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297625, "field 'verificationBtn' and method 'onViewClicked'");
        loginPasswordSettingActivity.verificationBtn = (TextView) Utils.castView(findRequiredView3, 2131297625, "field 'verificationBtn'", TextView.class);
        this.view2131297625 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginPasswordSettingActivity.onViewClicked(view);
            }
        });
        loginPasswordSettingActivity.passwordEditText = (EditText) Utils.findRequiredViewAsType(view, 2131297116, "field 'passwordEditText'", EditText.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296657, "field 'eyeLayout' and method 'onViewClicked'");
        loginPasswordSettingActivity.eyeLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131296657, "field 'eyeLayout'", LinearLayout.class);
        this.view2131296657 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                loginPasswordSettingActivity.onViewClicked(view);
            }
        });
        loginPasswordSettingActivity.eyeImg = (ImageView) Utils.findRequiredViewAsType(view, 2131296656, "field 'eyeImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        LoginPasswordSettingActivity loginPasswordSettingActivity = this.target;
        if (loginPasswordSettingActivity != null) {
            this.target = null;
            loginPasswordSettingActivity.submitSetting = null;
            loginPasswordSettingActivity.navBack = null;
            loginPasswordSettingActivity.navTitle = null;
            loginPasswordSettingActivity.navRightText = null;
            loginPasswordSettingActivity.navRightLayout = null;
            loginPasswordSettingActivity.shadowLinearLayout = null;
            loginPasswordSettingActivity.numberText = null;
            loginPasswordSettingActivity.verificationEt = null;
            loginPasswordSettingActivity.verificationBtn = null;
            loginPasswordSettingActivity.passwordEditText = null;
            loginPasswordSettingActivity.eyeLayout = null;
            loginPasswordSettingActivity.eyeImg = null;
            this.view2131297437.setOnClickListener((View.OnClickListener) null);
            this.view2131297437 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297625.setOnClickListener((View.OnClickListener) null);
            this.view2131297625 = null;
            this.view2131296657.setOnClickListener((View.OnClickListener) null);
            this.view2131296657 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
