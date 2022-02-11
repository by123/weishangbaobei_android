package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SelectMyVoiceCollectionFragment_ViewBinding implements Unbinder {
    private SelectMyVoiceCollectionFragment target;

    @UiThread
    public SelectMyVoiceCollectionFragment_ViewBinding(SelectMyVoiceCollectionFragment selectMyVoiceCollectionFragment, View view) {
        this.target = selectMyVoiceCollectionFragment;
        selectMyVoiceCollectionFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        selectMyVoiceCollectionFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        SelectMyVoiceCollectionFragment selectMyVoiceCollectionFragment = this.target;
        if (selectMyVoiceCollectionFragment != null) {
            this.target = null;
            selectMyVoiceCollectionFragment.listView = null;
            selectMyVoiceCollectionFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
