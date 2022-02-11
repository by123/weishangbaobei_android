package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class InviteRecordOneFragment_ViewBinding implements Unbinder {
    private InviteRecordOneFragment target;

    @UiThread
    public InviteRecordOneFragment_ViewBinding(InviteRecordOneFragment inviteRecordOneFragment, View view) {
        this.target = inviteRecordOneFragment;
        inviteRecordOneFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        inviteRecordOneFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        InviteRecordOneFragment inviteRecordOneFragment = this.target;
        if (inviteRecordOneFragment != null) {
            this.target = null;
            inviteRecordOneFragment.recyclerView = null;
            inviteRecordOneFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
