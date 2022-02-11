package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class PaymentProtocolActivity_ViewBinding implements Unbinder {
    private PaymentProtocolActivity target;
    private View view2131297049;

    @UiThread
    public PaymentProtocolActivity_ViewBinding(PaymentProtocolActivity paymentProtocolActivity) {
        this(paymentProtocolActivity, paymentProtocolActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PaymentProtocolActivity_ViewBinding$1] */
    @UiThread
    public PaymentProtocolActivity_ViewBinding(final PaymentProtocolActivity paymentProtocolActivity, View view) {
        this.target = paymentProtocolActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        paymentProtocolActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                paymentProtocolActivity.onViewClicked(view);
            }
        });
        paymentProtocolActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PaymentProtocolActivity paymentProtocolActivity = this.target;
        if (paymentProtocolActivity != null) {
            this.target = null;
            paymentProtocolActivity.navBack = null;
            paymentProtocolActivity.navTitle = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
