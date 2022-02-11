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

public class MyVoiceFragment_ViewBinding implements Unbinder {
    private MyVoiceFragment target;

    @UiThread
    public MyVoiceFragment_ViewBinding(MyVoiceFragment myVoiceFragment, View view) {
        this.target = myVoiceFragment;
        myVoiceFragment.listView = (ListView) Utils.findRequiredViewAsType(view, 2131296952, "field 'listView'", ListView.class);
        myVoiceFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        myVoiceFragment.pressLayout = (TextView) Utils.findRequiredViewAsType(view, 2131297156, "field 'pressLayout'", TextView.class);
        myVoiceFragment.voiceRecorder = (VoiceRecorderView) Utils.findRequiredViewAsType(view, 2131297661, "field 'voiceRecorder'", VoiceRecorderView.class);
    }

    @CallSuper
    public void unbind() {
        MyVoiceFragment myVoiceFragment = this.target;
        if (myVoiceFragment != null) {
            this.target = null;
            myVoiceFragment.listView = null;
            myVoiceFragment.refreshLayout = null;
            myVoiceFragment.pressLayout = null;
            myVoiceFragment.voiceRecorder = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
