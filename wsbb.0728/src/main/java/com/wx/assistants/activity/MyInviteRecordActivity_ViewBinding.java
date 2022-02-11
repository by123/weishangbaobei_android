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

public class MyInviteRecordActivity_ViewBinding implements Unbinder {
    private MyInviteRecordActivity target;
    private View view2131296366;
    private View view2131297049;
    private View view2131297680;

    @UiThread
    public MyInviteRecordActivity_ViewBinding(MyInviteRecordActivity myInviteRecordActivity) {
        this(myInviteRecordActivity, myInviteRecordActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.MyInviteRecordActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v27, types: [com.wx.assistants.activity.MyInviteRecordActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v32, types: [com.wx.assistants.activity.MyInviteRecordActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public MyInviteRecordActivity_ViewBinding(final MyInviteRecordActivity myInviteRecordActivity, View view) {
        this.target = myInviteRecordActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        myInviteRecordActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myInviteRecordActivity.onViewClicked(view);
            }
        });
        myInviteRecordActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        myInviteRecordActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        myInviteRecordActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        myInviteRecordActivity.inviteReward = (TextView) Utils.findRequiredViewAsType(view, 2131296822, "field 'inviteReward'", TextView.class);
        myInviteRecordActivity.invitePeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131296820, "field 'invitePeopleNum'", TextView.class);
        myInviteRecordActivity.withdrawalAmount = (TextView) Utils.findRequiredViewAsType(view, 2131297681, "field 'withdrawalAmount'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297680, "field 'withdrawal' and method 'onViewClicked'");
        myInviteRecordActivity.withdrawal = (TextView) Utils.castView(findRequiredView2, 2131297680, "field 'withdrawal'", TextView.class);
        this.view2131297680 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myInviteRecordActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296366, "field 'backLayout' and method 'onViewClicked'");
        myInviteRecordActivity.backLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131296366, "field 'backLayout'", LinearLayout.class);
        this.view2131296366 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myInviteRecordActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MyInviteRecordActivity myInviteRecordActivity = this.target;
        if (myInviteRecordActivity != null) {
            this.target = null;
            myInviteRecordActivity.navBack = null;
            myInviteRecordActivity.navTitle = null;
            myInviteRecordActivity.recyclerView = null;
            myInviteRecordActivity.refreshLayout = null;
            myInviteRecordActivity.inviteReward = null;
            myInviteRecordActivity.invitePeopleNum = null;
            myInviteRecordActivity.withdrawalAmount = null;
            myInviteRecordActivity.withdrawal = null;
            myInviteRecordActivity.backLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297680.setOnClickListener((View.OnClickListener) null);
            this.view2131297680 = null;
            this.view2131296366.setOnClickListener((View.OnClickListener) null);
            this.view2131296366 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
