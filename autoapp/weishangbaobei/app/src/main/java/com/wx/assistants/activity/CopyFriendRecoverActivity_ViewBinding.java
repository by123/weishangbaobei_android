package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wx.assistants.view.FriendsMoveBottomLayout;

public class CopyFriendRecoverActivity_ViewBinding implements Unbinder {
    private CopyFriendRecoverActivity target;

    @UiThread
    public CopyFriendRecoverActivity_ViewBinding(CopyFriendRecoverActivity copyFriendRecoverActivity) {
        this(copyFriendRecoverActivity, copyFriendRecoverActivity.getWindow().getDecorView());
    }

    @UiThread
    public CopyFriendRecoverActivity_ViewBinding(CopyFriendRecoverActivity copyFriendRecoverActivity, View view) {
        this.target = copyFriendRecoverActivity;
        copyFriendRecoverActivity.navBack = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297049, "field 'navBack'", LinearLayout.class);
        copyFriendRecoverActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        copyFriendRecoverActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        copyFriendRecoverActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        copyFriendRecoverActivity.friendsMoveBottomLayout = (FriendsMoveBottomLayout) Utils.findRequiredViewAsType(view, 2131296721, "field 'friendsMoveBottomLayout'", FriendsMoveBottomLayout.class);
    }

    @CallSuper
    public void unbind() {
        CopyFriendRecoverActivity copyFriendRecoverActivity = this.target;
        if (copyFriendRecoverActivity != null) {
            this.target = null;
            copyFriendRecoverActivity.navBack = null;
            copyFriendRecoverActivity.navTitle = null;
            copyFriendRecoverActivity.navRightLayout = null;
            copyFriendRecoverActivity.navRightText = null;
            copyFriendRecoverActivity.friendsMoveBottomLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
