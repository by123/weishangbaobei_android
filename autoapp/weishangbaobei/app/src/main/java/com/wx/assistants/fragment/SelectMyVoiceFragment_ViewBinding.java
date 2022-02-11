package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SelectMyVoiceFragment_ViewBinding implements Unbinder {
    private SelectMyVoiceFragment target;

    @UiThread
    public SelectMyVoiceFragment_ViewBinding(SelectMyVoiceFragment selectMyVoiceFragment, View view) {
        this.target = selectMyVoiceFragment;
        selectMyVoiceFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        selectMyVoiceFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        selectMyVoiceFragment.pressLayout = (TextView) Utils.findRequiredViewAsType(view, 2131297156, "field 'pressLayout'", TextView.class);
        selectMyVoiceFragment.voiceRecorder = (VoiceRecorderView) Utils.findRequiredViewAsType(view, 2131297661, "field 'voiceRecorder'", VoiceRecorderView.class);
    }

    @CallSuper
    public void unbind() {
        SelectMyVoiceFragment selectMyVoiceFragment = this.target;
        if (selectMyVoiceFragment != null) {
            this.target = null;
            selectMyVoiceFragment.listView = null;
            selectMyVoiceFragment.refreshLayout = null;
            selectMyVoiceFragment.pressLayout = null;
            selectMyVoiceFragment.voiceRecorder = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
