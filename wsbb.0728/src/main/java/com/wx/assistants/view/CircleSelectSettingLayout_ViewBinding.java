package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CircleSelectSettingLayout_ViewBinding implements Unbinder {
    private CircleSelectSettingLayout target;
    private View view2131297312;

    @UiThread
    public CircleSelectSettingLayout_ViewBinding(CircleSelectSettingLayout circleSelectSettingLayout) {
        this(circleSelectSettingLayout, circleSelectSettingLayout);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.view.CircleSelectSettingLayout_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public CircleSelectSettingLayout_ViewBinding(final CircleSelectSettingLayout circleSelectSettingLayout, View view) {
        this.target = circleSelectSettingLayout;
        View findRequiredView = Utils.findRequiredView(view, 2131297312, "field 'selectLayout' and method 'onViewClicked'");
        circleSelectSettingLayout.selectLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297312, "field 'selectLayout'", LinearLayout.class);
        this.view2131297312 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                circleSelectSettingLayout.onViewClicked();
            }
        });
        circleSelectSettingLayout.selectModelTv = (TextView) Utils.findRequiredViewAsType(view, 2131297313, "field 'selectModelTv'", TextView.class);
        circleSelectSettingLayout.selectResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297316, "field 'selectResultLayout'", LinearLayout.class);
        circleSelectSettingLayout.selectTagsResult = (TextView) Utils.findRequiredViewAsType(view, 2131297319, "field 'selectTagsResult'", TextView.class);
        circleSelectSettingLayout.selectGroupsResult = (TextView) Utils.findRequiredViewAsType(view, 2131297310, "field 'selectGroupsResult'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CircleSelectSettingLayout circleSelectSettingLayout = this.target;
        if (circleSelectSettingLayout != null) {
            this.target = null;
            circleSelectSettingLayout.selectLayout = null;
            circleSelectSettingLayout.selectModelTv = null;
            circleSelectSettingLayout.selectResultLayout = null;
            circleSelectSettingLayout.selectTagsResult = null;
            circleSelectSettingLayout.selectGroupsResult = null;
            this.view2131297312.setOnClickListener((View.OnClickListener) null);
            this.view2131297312 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
