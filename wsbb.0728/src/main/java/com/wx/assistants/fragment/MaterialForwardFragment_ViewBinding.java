package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MaterialForwardFragment_ViewBinding implements Unbinder {
    private MaterialForwardFragment target;

    @UiThread
    public MaterialForwardFragment_ViewBinding(MaterialForwardFragment materialForwardFragment, View view) {
        this.target = materialForwardFragment;
        materialForwardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        materialForwardFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MaterialForwardFragment materialForwardFragment = this.target;
        if (materialForwardFragment != null) {
            this.target = null;
            materialForwardFragment.recyclerView = null;
            materialForwardFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
