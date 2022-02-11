package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class InvitationBindActivity_ViewBinding implements Unbinder {
    private InvitationBindActivity target;
    private View view2131296526;
    private View view2131297049;

    @UiThread
    public InvitationBindActivity_ViewBinding(InvitationBindActivity invitationBindActivity) {
        this(invitationBindActivity, invitationBindActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.InvitationBindActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.InvitationBindActivity_ViewBinding$2, android.view.View$OnClickListener] */
    @UiThread
    public InvitationBindActivity_ViewBinding(final InvitationBindActivity invitationBindActivity, View view) {
        this.target = invitationBindActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        invitationBindActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                invitationBindActivity.onViewClicked(view);
            }
        });
        invitationBindActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        invitationBindActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        invitationBindActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        invitationBindActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        invitationBindActivity.phoneNum = (EditText) Utils.findRequiredViewAsType(view, 2131297125, "field 'phoneNum'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296526, "field 'confirmBtn' and method 'onViewClicked'");
        invitationBindActivity.confirmBtn = (Button) Utils.castView(findRequiredView2, 2131296526, "field 'confirmBtn'", Button.class);
        this.view2131296526 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                invitationBindActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        InvitationBindActivity invitationBindActivity = this.target;
        if (invitationBindActivity != null) {
            this.target = null;
            invitationBindActivity.navBack = null;
            invitationBindActivity.navTitle = null;
            invitationBindActivity.navRightText = null;
            invitationBindActivity.navRightLayout = null;
            invitationBindActivity.shadowLinearLayout = null;
            invitationBindActivity.phoneNum = null;
            invitationBindActivity.confirmBtn = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296526.setOnClickListener((View.OnClickListener) null);
            this.view2131296526 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
