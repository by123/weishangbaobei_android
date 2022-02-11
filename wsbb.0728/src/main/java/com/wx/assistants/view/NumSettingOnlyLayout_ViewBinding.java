package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class NumSettingOnlyLayout_ViewBinding implements Unbinder {
    private NumSettingOnlyLayout target;
    private View view2131297173;

    @UiThread
    public NumSettingOnlyLayout_ViewBinding(NumSettingOnlyLayout numSettingOnlyLayout) {
        this(numSettingOnlyLayout, numSettingOnlyLayout);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.view.NumSettingOnlyLayout_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public NumSettingOnlyLayout_ViewBinding(final NumSettingOnlyLayout numSettingOnlyLayout, View view) {
        this.target = numSettingOnlyLayout;
        View findRequiredView = Utils.findRequiredView(view, 2131297173, "field 'quantityLayout' and method 'onViewClicked'");
        numSettingOnlyLayout.quantityLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297173, "field 'quantityLayout'", LinearLayout.class);
        this.view2131297173 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                numSettingOnlyLayout.onViewClicked(view);
            }
        });
        numSettingOnlyLayout.quantityText = (TextView) Utils.findRequiredViewAsType(view, 2131297174, "field 'quantityText'", TextView.class);
        numSettingOnlyLayout.numberTitleText = (TextView) Utils.findRequiredViewAsType(view, 2131297085, "field 'numberTitleText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        NumSettingOnlyLayout numSettingOnlyLayout = this.target;
        if (numSettingOnlyLayout != null) {
            this.target = null;
            numSettingOnlyLayout.quantityLayout = null;
            numSettingOnlyLayout.quantityText = null;
            numSettingOnlyLayout.numberTitleText = null;
            this.view2131297173.setOnClickListener((View.OnClickListener) null);
            this.view2131297173 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
