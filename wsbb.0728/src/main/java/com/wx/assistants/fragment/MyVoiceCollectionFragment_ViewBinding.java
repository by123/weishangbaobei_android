package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyVoiceCollectionFragment_ViewBinding implements Unbinder {
    private MyVoiceCollectionFragment target;

    @UiThread
    public MyVoiceCollectionFragment_ViewBinding(MyVoiceCollectionFragment myVoiceCollectionFragment, View view) {
        this.target = myVoiceCollectionFragment;
        myVoiceCollectionFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        myVoiceCollectionFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MyVoiceCollectionFragment myVoiceCollectionFragment = this.target;
        if (myVoiceCollectionFragment != null) {
            this.target = null;
            myVoiceCollectionFragment.listView = null;
            myVoiceCollectionFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
