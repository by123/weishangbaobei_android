package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class NumSettingLayout_ViewBinding implements Unbinder {
    private NumSettingLayout target;
    private View view2131297173;
    private View view2131297415;

    @UiThread
    public NumSettingLayout_ViewBinding(NumSettingLayout numSettingLayout) {
        this(numSettingLayout, numSettingLayout);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.view.NumSettingLayout_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.view.NumSettingLayout_ViewBinding$2] */
    @UiThread
    public NumSettingLayout_ViewBinding(final NumSettingLayout numSettingLayout, View view) {
        this.target = numSettingLayout;
        View findRequiredView = Utils.findRequiredView(view, 2131297173, "field 'quantityLayout' and method 'onViewClicked'");
        numSettingLayout.quantityLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297173, "field 'quantityLayout'", LinearLayout.class);
        this.view2131297173 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                numSettingLayout.onViewClicked(view);
            }
        });
        numSettingLayout.quantityText = (TextView) Utils.findRequiredViewAsType(view, 2131297174, "field 'quantityText'", TextView.class);
        numSettingLayout.numberTitleText = (TextView) Utils.findRequiredViewAsType(view, 2131297085, "field 'numberTitleText'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297415, "field 'startPointLayout' and method 'onViewClicked'");
        numSettingLayout.startPointLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297415, "field 'startPointLayout'", LinearLayout.class);
        this.view2131297415 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                numSettingLayout.onViewClicked(view);
            }
        });
        numSettingLayout.startPointText = (TextView) Utils.findRequiredViewAsType(view, 2131297416, "field 'startPointText'", TextView.class);
        numSettingLayout.startPointTitleText = (TextView) Utils.findRequiredViewAsType(view, 2131297417, "field 'startPointTitleText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        NumSettingLayout numSettingLayout = this.target;
        if (numSettingLayout != null) {
            this.target = null;
            numSettingLayout.quantityLayout = null;
            numSettingLayout.quantityText = null;
            numSettingLayout.numberTitleText = null;
            numSettingLayout.startPointLayout = null;
            numSettingLayout.startPointText = null;
            numSettingLayout.startPointTitleText = null;
            this.view2131297173.setOnClickListener((View.OnClickListener) null);
            this.view2131297173 = null;
            this.view2131297415.setOnClickListener((View.OnClickListener) null);
            this.view2131297415 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
