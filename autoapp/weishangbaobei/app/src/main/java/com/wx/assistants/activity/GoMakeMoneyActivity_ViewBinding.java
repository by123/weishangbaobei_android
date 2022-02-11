package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class GoMakeMoneyActivity_ViewBinding implements Unbinder {
    private GoMakeMoneyActivity target;
    private View view2131296366;
    private View view2131296480;
    private View view2131296549;
    private View view2131296687;
    private View view2131296819;
    private View view2131297044;

    @UiThread
    public GoMakeMoneyActivity_ViewBinding(GoMakeMoneyActivity goMakeMoneyActivity) {
        this(goMakeMoneyActivity, goMakeMoneyActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoMakeMoneyActivity_ViewBinding$6] */
    @UiThread
    public GoMakeMoneyActivity_ViewBinding(final GoMakeMoneyActivity goMakeMoneyActivity, View view) {
        this.target = goMakeMoneyActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296480, "field 'checkRule' and method 'onViewClicked'");
        goMakeMoneyActivity.checkRule = (Button) Utils.castView(findRequiredView, 2131296480, "field 'checkRule'", Button.class);
        this.view2131296480 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked(view);
            }
        });
        goMakeMoneyActivity.inviteUrl = (TextView) Utils.findRequiredViewAsType(view, 2131296827, "field 'inviteUrl'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296549, "field 'copyBtn' and method 'onViewClicked'");
        goMakeMoneyActivity.copyBtn = (Button) Utils.castView(findRequiredView2, 2131296549, "field 'copyBtn'", Button.class);
        this.view2131296549 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296687, "field 'forwardCircle' and method 'onViewClicked'");
        goMakeMoneyActivity.forwardCircle = (Button) Utils.castView(findRequiredView3, 2131296687, "field 'forwardCircle'", Button.class);
        this.view2131296687 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131296819, "field 'inviteNow' and method 'onViewClicked'");
        goMakeMoneyActivity.inviteNow = (Button) Utils.castView(findRequiredView4, 2131296819, "field 'inviteNow'", Button.class);
        this.view2131296819 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297044, "field 'myAward' and method 'onViewClicked'");
        goMakeMoneyActivity.myAward = (Button) Utils.castView(findRequiredView5, 2131297044, "field 'myAward'", Button.class);
        this.view2131297044 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296366, "field 'backLayout' and method 'onViewClicked'");
        goMakeMoneyActivity.backLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131296366, "field 'backLayout'", LinearLayout.class);
        this.view2131296366 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goMakeMoneyActivity.onViewClicked();
            }
        });
    }

    @CallSuper
    public void unbind() {
        GoMakeMoneyActivity goMakeMoneyActivity = this.target;
        if (goMakeMoneyActivity != null) {
            this.target = null;
            goMakeMoneyActivity.checkRule = null;
            goMakeMoneyActivity.inviteUrl = null;
            goMakeMoneyActivity.copyBtn = null;
            goMakeMoneyActivity.forwardCircle = null;
            goMakeMoneyActivity.inviteNow = null;
            goMakeMoneyActivity.myAward = null;
            goMakeMoneyActivity.backLayout = null;
            this.view2131296480.setOnClickListener((View.OnClickListener) null);
            this.view2131296480 = null;
            this.view2131296549.setOnClickListener((View.OnClickListener) null);
            this.view2131296549 = null;
            this.view2131296687.setOnClickListener((View.OnClickListener) null);
            this.view2131296687 = null;
            this.view2131296819.setOnClickListener((View.OnClickListener) null);
            this.view2131296819 = null;
            this.view2131297044.setOnClickListener((View.OnClickListener) null);
            this.view2131297044 = null;
            this.view2131296366.setOnClickListener((View.OnClickListener) null);
            this.view2131296366 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
