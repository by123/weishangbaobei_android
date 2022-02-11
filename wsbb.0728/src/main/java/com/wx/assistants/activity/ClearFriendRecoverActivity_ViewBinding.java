package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class ClearFriendRecoverActivity_ViewBinding implements Unbinder {
    private ClearFriendRecoverActivity target;

    @UiThread
    public ClearFriendRecoverActivity_ViewBinding(ClearFriendRecoverActivity clearFriendRecoverActivity) {
        this(clearFriendRecoverActivity, clearFriendRecoverActivity.getWindow().getDecorView());
    }

    @UiThread
    public ClearFriendRecoverActivity_ViewBinding(ClearFriendRecoverActivity clearFriendRecoverActivity, View view) {
        this.target = clearFriendRecoverActivity;
        clearFriendRecoverActivity.navBack = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297049, "field 'navBack'", LinearLayout.class);
        clearFriendRecoverActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        clearFriendRecoverActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        clearFriendRecoverActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ClearFriendRecoverActivity clearFriendRecoverActivity = this.target;
        if (clearFriendRecoverActivity != null) {
            this.target = null;
            clearFriendRecoverActivity.navBack = null;
            clearFriendRecoverActivity.navTitle = null;
            clearFriendRecoverActivity.navRightLayout = null;
            clearFriendRecoverActivity.navRightText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
