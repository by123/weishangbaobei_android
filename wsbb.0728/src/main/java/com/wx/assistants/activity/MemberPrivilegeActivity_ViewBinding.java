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

public class MemberPrivilegeActivity_ViewBinding implements Unbinder {
    private MemberPrivilegeActivity target;
    private View view2131297049;
    private View view2131297091;

    @UiThread
    public MemberPrivilegeActivity_ViewBinding(MemberPrivilegeActivity memberPrivilegeActivity) {
        this(memberPrivilegeActivity, memberPrivilegeActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberPrivilegeActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.MemberPrivilegeActivity_ViewBinding$2, android.view.View$OnClickListener] */
    @UiThread
    public MemberPrivilegeActivity_ViewBinding(final MemberPrivilegeActivity memberPrivilegeActivity, View view) {
        this.target = memberPrivilegeActivity;
        memberPrivilegeActivity.setUserHead = (ImageView) Utils.findRequiredViewAsType(view, 2131297353, "field 'setUserHead'", ImageView.class);
        memberPrivilegeActivity.setUserName = (TextView) Utils.findRequiredViewAsType(view, 2131297354, "field 'setUserName'", TextView.class);
        memberPrivilegeActivity.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        memberPrivilegeActivity.operationBtn = (Button) Utils.castView(findRequiredView, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberPrivilegeActivity.onViewClicked(view);
            }
        });
        memberPrivilegeActivity.memberLever = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'memberLever'", TextView.class);
        memberPrivilegeActivity.diamondImage = (ImageView) Utils.findRequiredViewAsType(view, 2131296597, "field 'diamondImage'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        memberPrivilegeActivity.navBack = (LinearLayout) Utils.castView(findRequiredView2, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberPrivilegeActivity.onViewClicked(view);
            }
        });
        memberPrivilegeActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        memberPrivilegeActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, 2131297162, "field 'progressBar'", ProgressBar.class);
        memberPrivilegeActivity.errorLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296640, "field 'errorLayout'", LinearLayout.class);
        memberPrivilegeActivity.safeWebView = (WebView) Utils.findRequiredViewAsType(view, 2131297272, "field 'safeWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        MemberPrivilegeActivity memberPrivilegeActivity = this.target;
        if (memberPrivilegeActivity != null) {
            this.target = null;
            memberPrivilegeActivity.setUserHead = null;
            memberPrivilegeActivity.setUserName = null;
            memberPrivilegeActivity.setExpireTime = null;
            memberPrivilegeActivity.operationBtn = null;
            memberPrivilegeActivity.memberLever = null;
            memberPrivilegeActivity.diamondImage = null;
            memberPrivilegeActivity.navBack = null;
            memberPrivilegeActivity.navTitle = null;
            memberPrivilegeActivity.progressBar = null;
            memberPrivilegeActivity.errorLayout = null;
            memberPrivilegeActivity.safeWebView = null;
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
