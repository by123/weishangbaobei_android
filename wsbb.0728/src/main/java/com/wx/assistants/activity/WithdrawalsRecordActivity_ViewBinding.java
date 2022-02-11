package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class WithdrawalsRecordActivity_ViewBinding implements Unbinder {
    private WithdrawalsRecordActivity target;
    private View view2131297049;
    private View view2131297091;

    @UiThread
    public WithdrawalsRecordActivity_ViewBinding(WithdrawalsRecordActivity withdrawalsRecordActivity) {
        this(withdrawalsRecordActivity, withdrawalsRecordActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.WithdrawalsRecordActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v18, types: [com.wx.assistants.activity.WithdrawalsRecordActivity_ViewBinding$2, android.view.View$OnClickListener] */
    @UiThread
    public WithdrawalsRecordActivity_ViewBinding(final WithdrawalsRecordActivity withdrawalsRecordActivity, View view) {
        this.target = withdrawalsRecordActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        withdrawalsRecordActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                withdrawalsRecordActivity.onViewClicked(view);
            }
        });
        withdrawalsRecordActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        withdrawalsRecordActivity.setUserName = (TextView) Utils.findRequiredViewAsType(view, 2131297354, "field 'setUserName'", TextView.class);
        withdrawalsRecordActivity.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        withdrawalsRecordActivity.operationBtn = (Button) Utils.castView(findRequiredView2, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                withdrawalsRecordActivity.onViewClicked(view);
            }
        });
        withdrawalsRecordActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        withdrawalsRecordActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        withdrawalsRecordActivity.topLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297531, "field 'topLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        WithdrawalsRecordActivity withdrawalsRecordActivity = this.target;
        if (withdrawalsRecordActivity != null) {
            this.target = null;
            withdrawalsRecordActivity.navBack = null;
            withdrawalsRecordActivity.navTitle = null;
            withdrawalsRecordActivity.setUserName = null;
            withdrawalsRecordActivity.setExpireTime = null;
            withdrawalsRecordActivity.operationBtn = null;
            withdrawalsRecordActivity.recyclerView = null;
            withdrawalsRecordActivity.refreshLayout = null;
            withdrawalsRecordActivity.topLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
