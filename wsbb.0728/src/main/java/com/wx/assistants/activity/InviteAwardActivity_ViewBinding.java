package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class InviteAwardActivity_ViewBinding implements Unbinder {
    private InviteAwardActivity target;
    private View view2131297049;
    private View view2131297091;

    @UiThread
    public InviteAwardActivity_ViewBinding(InviteAwardActivity inviteAwardActivity) {
        this(inviteAwardActivity, inviteAwardActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteAwardActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteAwardActivity_ViewBinding$2] */
    @UiThread
    public InviteAwardActivity_ViewBinding(final InviteAwardActivity inviteAwardActivity, View view) {
        this.target = inviteAwardActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        inviteAwardActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteAwardActivity.onViewClicked(view);
            }
        });
        inviteAwardActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        inviteAwardActivity.setUserHead = (ImageView) Utils.findRequiredViewAsType(view, 2131297353, "field 'setUserHead'", ImageView.class);
        inviteAwardActivity.setUserName = (TextView) Utils.findRequiredViewAsType(view, 2131297354, "field 'setUserName'", TextView.class);
        inviteAwardActivity.diamondImage = (ImageView) Utils.findRequiredViewAsType(view, 2131296597, "field 'diamondImage'", ImageView.class);
        inviteAwardActivity.memberLever = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'memberLever'", TextView.class);
        inviteAwardActivity.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        inviteAwardActivity.operationBtn = (Button) Utils.castView(findRequiredView2, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteAwardActivity.onViewClicked(view);
            }
        });
        inviteAwardActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, 2131297162, "field 'progressBar'", ProgressBar.class);
        inviteAwardActivity.errorLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296640, "field 'errorLayout'", LinearLayout.class);
        inviteAwardActivity.safeWebView = (WebView) Utils.findRequiredViewAsType(view, 2131297272, "field 'safeWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        InviteAwardActivity inviteAwardActivity = this.target;
        if (inviteAwardActivity != null) {
            this.target = null;
            inviteAwardActivity.navBack = null;
            inviteAwardActivity.navTitle = null;
            inviteAwardActivity.setUserHead = null;
            inviteAwardActivity.setUserName = null;
            inviteAwardActivity.diamondImage = null;
            inviteAwardActivity.memberLever = null;
            inviteAwardActivity.setExpireTime = null;
            inviteAwardActivity.operationBtn = null;
            inviteAwardActivity.progressBar = null;
            inviteAwardActivity.errorLayout = null;
            inviteAwardActivity.safeWebView = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
