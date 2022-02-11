package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class WxVoiceFragment_ViewBinding implements Unbinder {
    private WxVoiceFragment target;

    @UiThread
    public WxVoiceFragment_ViewBinding(WxVoiceFragment wxVoiceFragment, View view) {
        this.target = wxVoiceFragment;
        wxVoiceFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        wxVoiceFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        wxVoiceFragment.showTith = (TextView) Utils.findRequiredViewAsType(view, 2131297375, "field 'showTith'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        WxVoiceFragment wxVoiceFragment = this.target;
        if (wxVoiceFragment != null) {
            this.target = null;
            wxVoiceFragment.listView = null;
            wxVoiceFragment.refreshLayout = null;
            wxVoiceFragment.showTith = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
