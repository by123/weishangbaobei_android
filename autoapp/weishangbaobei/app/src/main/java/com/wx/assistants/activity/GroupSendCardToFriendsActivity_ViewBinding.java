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
import com.wx.assistants.view.CirculateModelLayout;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;

public class GroupSendCardToFriendsActivity_ViewBinding implements Unbinder {
    private GroupSendCardToFriendsActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendCardToFriendsActivity_ViewBinding(GroupSendCardToFriendsActivity groupSendCardToFriendsActivity) {
        this(groupSendCardToFriendsActivity, groupSendCardToFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToFriendsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToFriendsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToFriendsActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v18, types: [com.wx.assistants.activity.GroupSendCardToFriendsActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendCardToFriendsActivity_ViewBinding(final GroupSendCardToFriendsActivity groupSendCardToFriendsActivity, View view) {
        this.target = groupSendCardToFriendsActivity;
        groupSendCardToFriendsActivity.editCardNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296613, "field 'editCardNickName'", EditText.class);
        groupSendCardToFriendsActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendCardToFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendCardToFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendCardToFriendsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendCardToFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendCardToFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendCardToFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendCardToFriendsActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendCardToFriendsActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        groupSendCardToFriendsActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendCardToFriendsActivity.intimateModelLayout = (IntimateModelLayout) Utils.findRequiredViewAsType(view, 2131296813, "field 'intimateModelLayout'", IntimateModelLayout.class);
        groupSendCardToFriendsActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendCardToFriendsActivity groupSendCardToFriendsActivity = this.target;
        if (groupSendCardToFriendsActivity != null) {
            this.target = null;
            groupSendCardToFriendsActivity.editCardNickName = null;
            groupSendCardToFriendsActivity.editLeavingMessage = null;
            groupSendCardToFriendsActivity.navBack = null;
            groupSendCardToFriendsActivity.navTitle = null;
            groupSendCardToFriendsActivity.startWx = null;
            groupSendCardToFriendsActivity.videoIntroduceLayout = null;
            groupSendCardToFriendsActivity.navRightLayout = null;
            groupSendCardToFriendsActivity.navRightImg = null;
            groupSendCardToFriendsActivity.circulateNum = null;
            groupSendCardToFriendsActivity.circulateModeLayout = null;
            groupSendCardToFriendsActivity.executeTimeSpaceLayout = null;
            groupSendCardToFriendsActivity.intimateModelLayout = null;
            groupSendCardToFriendsActivity.friendSendModeLayout = null;
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
