package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class PublicTextActivity_ViewBinding implements Unbinder {
    private PublicTextActivity target;
    private View view2131297049;

    @UiThread
    public PublicTextActivity_ViewBinding(PublicTextActivity publicTextActivity) {
        this(publicTextActivity, publicTextActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PublicTextActivity_ViewBinding$1] */
    @UiThread
    public PublicTextActivity_ViewBinding(final PublicTextActivity publicTextActivity, View view) {
        this.target = publicTextActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        publicTextActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publicTextActivity.onViewClicked(view);
            }
        });
        publicTextActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        publicTextActivity.publicText = (TextView) Utils.findRequiredViewAsType(view, 2131297171, "field 'publicText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PublicTextActivity publicTextActivity = this.target;
        if (publicTextActivity != null) {
            this.target = null;
            publicTextActivity.navBack = null;
            publicTextActivity.navTitle = null;
            publicTextActivity.publicText = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
