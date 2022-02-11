package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.view.SelectPicVideoLayout;
import com.wx.assistants.view.SendContentLayout;

public class GroupSendTextImageToFriendActivity_ViewBinding implements Unbinder {
    private GroupSendTextImageToFriendActivity target;
    private View view2131296507;
    private View view2131296616;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendTextImageToFriendActivity_ViewBinding(GroupSendTextImageToFriendActivity groupSendTextImageToFriendActivity) {
        this(groupSendTextImageToFriendActivity, groupSendTextImageToFriendActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r1v24, types: [com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v40, types: [com.wx.assistants.activity.GroupSendTextImageToFriendActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendTextImageToFriendActivity_ViewBinding(final GroupSendTextImageToFriendActivity groupSendTextImageToFriendActivity, View view) {
        this.target = groupSendTextImageToFriendActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296616, "field 'editLeavingMessage' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.editLeavingMessage = (EditTextWithScrollView) Utils.castView(findRequiredView, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        this.view2131296616 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView2, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToFriendActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.startWx = (Button) Utils.castView(findRequiredView4, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendTextImageToFriendActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToFriendActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendTextImageToFriendActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
        groupSendTextImageToFriendActivity.intimateModelLayout = (IntimateModelLayout) Utils.findRequiredViewAsType(view, 2131296813, "field 'intimateModelLayout'", IntimateModelLayout.class);
        groupSendTextImageToFriendActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendTextImageToFriendActivity.sendContentLayout = (SendContentLayout) Utils.findRequiredViewAsType(view, 2131297333, "field 'sendContentLayout'", SendContentLayout.class);
        groupSendTextImageToFriendActivity.textLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297464, "field 'textLayout'", LinearLayout.class);
        groupSendTextImageToFriendActivity.picLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297131, "field 'picLayout'", LinearLayout.class);
        groupSendTextImageToFriendActivity.selectPicVideoLayout = (SelectPicVideoLayout) Utils.findRequiredViewAsType(view, 2131297315, "field 'selectPicVideoLayout'", SelectPicVideoLayout.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        GroupSendTextImageToFriendActivity groupSendTextImageToFriendActivity = this.target;
        if (groupSendTextImageToFriendActivity != null) {
            this.target = null;
            groupSendTextImageToFriendActivity.editLeavingMessage = null;
            groupSendTextImageToFriendActivity.linearLayoutTemplate = null;
            groupSendTextImageToFriendActivity.navBack = null;
            groupSendTextImageToFriendActivity.navTitle = null;
            groupSendTextImageToFriendActivity.startWx = null;
            groupSendTextImageToFriendActivity.videoIntroduceLayout = null;
            groupSendTextImageToFriendActivity.cleanEditText = null;
            groupSendTextImageToFriendActivity.executeTimeSpaceLayout = null;
            groupSendTextImageToFriendActivity.friendSendModeLayout = null;
            groupSendTextImageToFriendActivity.intimateModelLayout = null;
            groupSendTextImageToFriendActivity.navRightImg = null;
            groupSendTextImageToFriendActivity.sendContentLayout = null;
            groupSendTextImageToFriendActivity.textLayout = null;
            groupSendTextImageToFriendActivity.picLayout = null;
            groupSendTextImageToFriendActivity.selectPicVideoLayout = null;
            this.view2131296616.setOnClickListener((View.OnClickListener) null);
            this.view2131296616 = null;
            this.view2131296950.setOnClickListener((View.OnClickListener) null);
            this.view2131296950 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
