package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyMemberCardFragment_ViewBinding implements Unbinder {
    private MyMemberCardFragment target;

    @UiThread
    public MyMemberCardFragment_ViewBinding(MyMemberCardFragment myMemberCardFragment, View view) {
        this.target = myMemberCardFragment;
        myMemberCardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297225, "field 'recyclerView'", RecyclerView.class);
        myMemberCardFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MyMemberCardFragment myMemberCardFragment = this.target;
        if (myMemberCardFragment != null) {
            this.target = null;
            myMemberCardFragment.recyclerView = null;
            myMemberCardFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
