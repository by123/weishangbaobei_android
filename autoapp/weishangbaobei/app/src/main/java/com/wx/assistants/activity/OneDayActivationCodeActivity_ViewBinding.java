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

public class OneDayActivationCodeActivity_ViewBinding implements Unbinder {
    private OneDayActivationCodeActivity target;
    private View view2131297049;

    @UiThread
    public OneDayActivationCodeActivity_ViewBinding(OneDayActivationCodeActivity oneDayActivationCodeActivity) {
        this(oneDayActivationCodeActivity, oneDayActivationCodeActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.OneDayActivationCodeActivity_ViewBinding$1, android.view.View$OnClickListener] */
    @UiThread
    public OneDayActivationCodeActivity_ViewBinding(final OneDayActivationCodeActivity oneDayActivationCodeActivity, View view) {
        this.target = oneDayActivationCodeActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneDayActivationCodeActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneDayActivationCodeActivity.onViewClicked(view);
            }
        });
        oneDayActivationCodeActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        oneDayActivationCodeActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        oneDayActivationCodeActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        oneDayActivationCodeActivity.topLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297531, "field 'topLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        OneDayActivationCodeActivity oneDayActivationCodeActivity = this.target;
        if (oneDayActivationCodeActivity != null) {
            this.target = null;
            oneDayActivationCodeActivity.navBack = null;
            oneDayActivationCodeActivity.navTitle = null;
            oneDayActivationCodeActivity.recyclerView = null;
            oneDayActivationCodeActivity.refreshLayout = null;
            oneDayActivationCodeActivity.topLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
