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
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;

public class GroupSendCollectionToFriendsActivity_ViewBinding implements Unbinder {
    private GroupSendCollectionToFriendsActivity target;
    private View view2131296507;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendCollectionToFriendsActivity_ViewBinding(GroupSendCollectionToFriendsActivity groupSendCollectionToFriendsActivity) {
        this(groupSendCollectionToFriendsActivity, groupSendCollectionToFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v25, types: [com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v33, types: [com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v38, types: [com.wx.assistants.activity.GroupSendCollectionToFriendsActivity_ViewBinding$6, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendCollectionToFriendsActivity_ViewBinding(final GroupSendCollectionToFriendsActivity groupSendCollectionToFriendsActivity, View view) {
        this.target = groupSendCollectionToFriendsActivity;
        groupSendCollectionToFriendsActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendCollectionToFriendsActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToFriendsActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendCollectionToFriendsActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendCollectionToFriendsActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
        groupSendCollectionToFriendsActivity.intimateModelLayout = (IntimateModelLayout) Utils.findRequiredViewAsType(view, 2131296813, "field 'intimateModelLayout'", IntimateModelLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendCollectionToFriendsActivity groupSendCollectionToFriendsActivity = this.target;
        if (groupSendCollectionToFriendsActivity != null) {
            this.target = null;
            groupSendCollectionToFriendsActivity.editLeavingMessage = null;
            groupSendCollectionToFriendsActivity.navBack = null;
            groupSendCollectionToFriendsActivity.navTitle = null;
            groupSendCollectionToFriendsActivity.startWx = null;
            groupSendCollectionToFriendsActivity.videoIntroduceLayout = null;
            groupSendCollectionToFriendsActivity.navRightLayout = null;
            groupSendCollectionToFriendsActivity.navRightImg = null;
            groupSendCollectionToFriendsActivity.linearLayoutTemplate = null;
            groupSendCollectionToFriendsActivity.cleanEditText = null;
            groupSendCollectionToFriendsActivity.circulateNum = null;
            groupSendCollectionToFriendsActivity.executeTimeSpaceLayout = null;
            groupSendCollectionToFriendsActivity.friendSendModeLayout = null;
            groupSendCollectionToFriendsActivity.intimateModelLayout = null;
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
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
