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

public class GroupSendVoiceToFriendsActivity_ViewBinding implements Unbinder {
    private GroupSendVoiceToFriendsActivity target;
    private View view2131296507;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297427;
    private View view2131297636;

    @UiThread
    public GroupSendVoiceToFriendsActivity_ViewBinding(GroupSendVoiceToFriendsActivity groupSendVoiceToFriendsActivity) {
        this(groupSendVoiceToFriendsActivity, groupSendVoiceToFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v17, types: [com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v22, types: [com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v26, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v46, types: [com.wx.assistants.activity.GroupSendVoiceToFriendsActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendVoiceToFriendsActivity_ViewBinding(final GroupSendVoiceToFriendsActivity groupSendVoiceToFriendsActivity, View view) {
        this.target = groupSendVoiceToFriendsActivity;
        groupSendVoiceToFriendsActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToFriendsActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendVoiceToFriendsActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendVoiceToFriendsActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
        groupSendVoiceToFriendsActivity.intimateModelLayout = (IntimateModelLayout) Utils.findRequiredViewAsType(view, 2131296813, "field 'intimateModelLayout'", IntimateModelLayout.class);
        groupSendVoiceToFriendsActivity.editNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296619, "field 'editNickName'", EditText.class);
        groupSendVoiceToFriendsActivity.editTitle = (EditText) Utils.findRequiredViewAsType(view, 2131296624, "field 'editTitle'", EditText.class);
        groupSendVoiceToFriendsActivity.editDesc = (EditText) Utils.findRequiredViewAsType(view, 2131296614, "field 'editDesc'", EditText.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297427, "field 'startWxTag' and method 'onViewClicked'");
        groupSendVoiceToFriendsActivity.startWxTag = (Button) Utils.castView(findRequiredView7, 2131297427, "field 'startWxTag'", Button.class);
        this.view2131297427 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToFriendsActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        GroupSendVoiceToFriendsActivity groupSendVoiceToFriendsActivity = this.target;
        if (groupSendVoiceToFriendsActivity != null) {
            this.target = null;
            groupSendVoiceToFriendsActivity.editLeavingMessage = null;
            groupSendVoiceToFriendsActivity.navBack = null;
            groupSendVoiceToFriendsActivity.navTitle = null;
            groupSendVoiceToFriendsActivity.startWx = null;
            groupSendVoiceToFriendsActivity.videoIntroduceLayout = null;
            groupSendVoiceToFriendsActivity.navRightLayout = null;
            groupSendVoiceToFriendsActivity.navRightImg = null;
            groupSendVoiceToFriendsActivity.linearLayoutTemplate = null;
            groupSendVoiceToFriendsActivity.cleanEditText = null;
            groupSendVoiceToFriendsActivity.circulateNum = null;
            groupSendVoiceToFriendsActivity.executeTimeSpaceLayout = null;
            groupSendVoiceToFriendsActivity.friendSendModeLayout = null;
            groupSendVoiceToFriendsActivity.intimateModelLayout = null;
            groupSendVoiceToFriendsActivity.editNickName = null;
            groupSendVoiceToFriendsActivity.editTitle = null;
            groupSendVoiceToFriendsActivity.editDesc = null;
            groupSendVoiceToFriendsActivity.startWxTag = null;
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
            this.view2131297427.setOnClickListener((View.OnClickListener) null);
            this.view2131297427 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
