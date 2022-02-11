package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.CustomSpeedLayout;
import com.wx.assistants.view.FriendSendModeLayout2;
import com.wx.assistants.view.NumSettingOnlyLayout;

public class AutoPullFriendsToGroupActivity_ViewBinding implements Unbinder {
    private AutoPullFriendsToGroupActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoPullFriendsToGroupActivity_ViewBinding(AutoPullFriendsToGroupActivity autoPullFriendsToGroupActivity) {
        this(autoPullFriendsToGroupActivity, autoPullFriendsToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.wx.assistants.activity.AutoPullFriendsToGroupActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v19, types: [com.wx.assistants.activity.AutoPullFriendsToGroupActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoPullFriendsToGroupActivity_ViewBinding(final AutoPullFriendsToGroupActivity autoPullFriendsToGroupActivity, View view) {
        this.target = autoPullFriendsToGroupActivity;
        autoPullFriendsToGroupActivity.friendSendModeLayout = (FriendSendModeLayout2) Utils.findRequiredViewAsType(view, 2131296718, "field 'friendSendModeLayout'", FriendSendModeLayout2.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoPullFriendsToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToGroupActivity.onViewClicked(view);
            }
        });
        autoPullFriendsToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoPullFriendsToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoPullFriendsToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToGroupActivity.onViewClicked(view);
            }
        });
        autoPullFriendsToGroupActivity.numSettingOnlyLayout = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingOnlyLayout.class);
        autoPullFriendsToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoPullFriendsToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToGroupActivity.onViewClicked(view);
            }
        });
        autoPullFriendsToGroupActivity.customSpeedLayout = (CustomSpeedLayout) Utils.findRequiredViewAsType(view, 2131296572, "field 'customSpeedLayout'", CustomSpeedLayout.class);
        autoPullFriendsToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        autoPullFriendsToGroupActivity.showImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297379, "field 'showImg'", ImageView.class);
        autoPullFriendsToGroupActivity.editLeavingMessageLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296618, "field 'editLeavingMessageLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        AutoPullFriendsToGroupActivity autoPullFriendsToGroupActivity = this.target;
        if (autoPullFriendsToGroupActivity != null) {
            this.target = null;
            autoPullFriendsToGroupActivity.friendSendModeLayout = null;
            autoPullFriendsToGroupActivity.navBack = null;
            autoPullFriendsToGroupActivity.navTitle = null;
            autoPullFriendsToGroupActivity.startWx = null;
            autoPullFriendsToGroupActivity.videoIntroduceLayout = null;
            autoPullFriendsToGroupActivity.numSettingOnlyLayout = null;
            autoPullFriendsToGroupActivity.navRightImg = null;
            autoPullFriendsToGroupActivity.navRightLayout = null;
            autoPullFriendsToGroupActivity.customSpeedLayout = null;
            autoPullFriendsToGroupActivity.editLeavingMessage = null;
            autoPullFriendsToGroupActivity.showImg = null;
            autoPullFriendsToGroupActivity.editLeavingMessageLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
