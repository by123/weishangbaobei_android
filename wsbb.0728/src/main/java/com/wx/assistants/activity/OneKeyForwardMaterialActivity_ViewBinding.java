package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class OneKeyForwardMaterialActivity_ViewBinding implements Unbinder {
    private OneKeyForwardMaterialActivity target;
    private View view2131297049;

    @UiThread
    public OneKeyForwardMaterialActivity_ViewBinding(OneKeyForwardMaterialActivity oneKeyForwardMaterialActivity) {
        this(oneKeyForwardMaterialActivity, oneKeyForwardMaterialActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardMaterialActivity_ViewBinding$1] */
    @UiThread
    public OneKeyForwardMaterialActivity_ViewBinding(final OneKeyForwardMaterialActivity oneKeyForwardMaterialActivity, View view) {
        this.target = oneKeyForwardMaterialActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyForwardMaterialActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardMaterialActivity.onViewClicked(view);
            }
        });
        oneKeyForwardMaterialActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        oneKeyForwardMaterialActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        oneKeyForwardMaterialActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        oneKeyForwardMaterialActivity.scrollTextView = (TextView) Utils.findRequiredViewAsType(view, 2131297287, "field 'scrollTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyForwardMaterialActivity oneKeyForwardMaterialActivity = this.target;
        if (oneKeyForwardMaterialActivity != null) {
            this.target = null;
            oneKeyForwardMaterialActivity.navBack = null;
            oneKeyForwardMaterialActivity.navTitle = null;
            oneKeyForwardMaterialActivity.recyclerView = null;
            oneKeyForwardMaterialActivity.refreshLayout = null;
            oneKeyForwardMaterialActivity.scrollTextView = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
