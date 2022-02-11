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

public class InviteRecordActivity_ViewBinding implements Unbinder {
    private InviteRecordActivity target;
    private View view2131297049;
    private View view2131297091;

    @UiThread
    public InviteRecordActivity_ViewBinding(InviteRecordActivity inviteRecordActivity) {
        this(inviteRecordActivity, inviteRecordActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteRecordActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteRecordActivity_ViewBinding$2] */
    @UiThread
    public InviteRecordActivity_ViewBinding(final InviteRecordActivity inviteRecordActivity, View view) {
        this.target = inviteRecordActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        inviteRecordActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteRecordActivity.onViewClicked(view);
            }
        });
        inviteRecordActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        inviteRecordActivity.setUserName = (TextView) Utils.findRequiredViewAsType(view, 2131297354, "field 'setUserName'", TextView.class);
        inviteRecordActivity.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        inviteRecordActivity.operationBtn = (Button) Utils.castView(findRequiredView2, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteRecordActivity.onViewClicked(view);
            }
        });
        inviteRecordActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        inviteRecordActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        InviteRecordActivity inviteRecordActivity = this.target;
        if (inviteRecordActivity != null) {
            this.target = null;
            inviteRecordActivity.navBack = null;
            inviteRecordActivity.navTitle = null;
            inviteRecordActivity.setUserName = null;
            inviteRecordActivity.setExpireTime = null;
            inviteRecordActivity.operationBtn = null;
            inviteRecordActivity.recyclerView = null;
            inviteRecordActivity.refreshLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
