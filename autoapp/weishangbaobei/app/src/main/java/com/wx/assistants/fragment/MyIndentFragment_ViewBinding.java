package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyIndentFragment_ViewBinding implements Unbinder {
    private MyIndentFragment target;

    @UiThread
    public MyIndentFragment_ViewBinding(MyIndentFragment myIndentFragment, View view) {
        this.target = myIndentFragment;
        myIndentFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        myIndentFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MyIndentFragment myIndentFragment = this.target;
        if (myIndentFragment != null) {
            this.target = null;
            myIndentFragment.recyclerView = null;
            myIndentFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
