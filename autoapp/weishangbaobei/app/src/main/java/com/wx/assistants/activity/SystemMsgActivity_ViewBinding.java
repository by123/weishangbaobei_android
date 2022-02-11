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

public class SystemMsgActivity_ViewBinding implements Unbinder {
    private SystemMsgActivity target;
    private View view2131297049;

    @UiThread
    public SystemMsgActivity_ViewBinding(SystemMsgActivity systemMsgActivity) {
        this(systemMsgActivity, systemMsgActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.SystemMsgActivity_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public SystemMsgActivity_ViewBinding(final SystemMsgActivity systemMsgActivity, View view) {
        this.target = systemMsgActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        systemMsgActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                systemMsgActivity.onViewClicked(view);
            }
        });
        systemMsgActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        systemMsgActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        systemMsgActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        SystemMsgActivity systemMsgActivity = this.target;
        if (systemMsgActivity != null) {
            this.target = null;
            systemMsgActivity.navBack = null;
            systemMsgActivity.navTitle = null;
            systemMsgActivity.recyclerView = null;
            systemMsgActivity.refreshLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
