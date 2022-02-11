package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class ContactActivity_ViewBinding implements Unbinder {
    private ContactActivity target;
    private View view2131297049;

    @UiThread
    public ContactActivity_ViewBinding(ContactActivity contactActivity) {
        this(contactActivity, contactActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.ContactActivity_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public ContactActivity_ViewBinding(final ContactActivity contactActivity, View view) {
        this.target = contactActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        contactActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                contactActivity.onViewClicked();
            }
        });
        contactActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ContactActivity contactActivity = this.target;
        if (contactActivity != null) {
            this.target = null;
            contactActivity.navBack = null;
            contactActivity.navTitle = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
