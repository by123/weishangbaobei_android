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
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;

public class GroupSendPublicNumberToFriendsActivity_ViewBinding implements Unbinder {
    private GroupSendPublicNumberToFriendsActivity target;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendPublicNumberToFriendsActivity_ViewBinding(GroupSendPublicNumberToFriendsActivity groupSendPublicNumberToFriendsActivity) {
        this(groupSendPublicNumberToFriendsActivity, groupSendPublicNumberToFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v22, types: [com.wx.assistants.activity.GroupSendPublicNumberToFriendsActivity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendPublicNumberToFriendsActivity_ViewBinding(final GroupSendPublicNumberToFriendsActivity groupSendPublicNumberToFriendsActivity, View view) {
        this.target = groupSendPublicNumberToFriendsActivity;
        groupSendPublicNumberToFriendsActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendPublicNumberToFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendPublicNumberToFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendPublicNumberToFriendsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendPublicNumberToFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendPublicNumberToFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendPublicNumberToFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendPublicNumberToFriendsActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendPublicNumberToFriendsActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendPublicNumberToFriendsActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendPublicNumberToFriendsActivity groupSendPublicNumberToFriendsActivity = this.target;
        if (groupSendPublicNumberToFriendsActivity != null) {
            this.target = null;
            groupSendPublicNumberToFriendsActivity.editLeavingMessage = null;
            groupSendPublicNumberToFriendsActivity.navBack = null;
            groupSendPublicNumberToFriendsActivity.navTitle = null;
            groupSendPublicNumberToFriendsActivity.startWx = null;
            groupSendPublicNumberToFriendsActivity.videoIntroduceLayout = null;
            groupSendPublicNumberToFriendsActivity.navRightLayout = null;
            groupSendPublicNumberToFriendsActivity.navRightImg = null;
            groupSendPublicNumberToFriendsActivity.linearLayoutTemplate = null;
            groupSendPublicNumberToFriendsActivity.executeTimeSpaceLayout = null;
            groupSendPublicNumberToFriendsActivity.friendSendModeLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296950.setOnClickListener((View.OnClickListener) null);
            this.view2131296950 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
