package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SelectWxVoiceFragment_ViewBinding implements Unbinder {
    private SelectWxVoiceFragment target;

    @UiThread
    public SelectWxVoiceFragment_ViewBinding(SelectWxVoiceFragment selectWxVoiceFragment, View view) {
        this.target = selectWxVoiceFragment;
        selectWxVoiceFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        selectWxVoiceFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        selectWxVoiceFragment.showTith = (TextView) Utils.findRequiredViewAsType(view, 2131297375, "field 'showTith'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SelectWxVoiceFragment selectWxVoiceFragment = this.target;
        if (selectWxVoiceFragment != null) {
            this.target = null;
            selectWxVoiceFragment.listView = null;
            selectWxVoiceFragment.refreshLayout = null;
            selectWxVoiceFragment.showTith = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
