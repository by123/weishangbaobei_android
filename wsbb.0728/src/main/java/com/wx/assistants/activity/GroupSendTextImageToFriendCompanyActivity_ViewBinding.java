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
import com.wx.assistants.view.FriendSendModeLayoutCompany;
import com.wx.assistants.view.IntimateModelLayout;
import com.wx.assistants.view.SelectPicVideoCompanyLayout;
import com.wx.assistants.view.SendContentLayout;

public class GroupSendTextImageToFriendCompanyActivity_ViewBinding implements Unbinder {
    private GroupSendTextImageToFriendCompanyActivity target;
    private View view2131296507;
    private View view2131296616;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendTextImageToFriendCompanyActivity_ViewBinding(GroupSendTextImageToFriendCompanyActivity groupSendTextImageToFriendCompanyActivity) {
        this(groupSendTextImageToFriendCompanyActivity, groupSendTextImageToFriendCompanyActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v22, types: [com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v32, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToFriendCompanyActivity_ViewBinding$7] */
    @UiThread
    public GroupSendTextImageToFriendCompanyActivity_ViewBinding(final GroupSendTextImageToFriendCompanyActivity groupSendTextImageToFriendCompanyActivity, View view) {
        this.target = groupSendTextImageToFriendCompanyActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296616, "field 'editLeavingMessage' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.editLeavingMessage = (EditTextWithScrollView) Utils.castView(findRequiredView, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        this.view2131296616 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView2, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToFriendCompanyActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.startWx = (Button) Utils.castView(findRequiredView4, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendTextImageToFriendCompanyActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToFriendCompanyActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendTextImageToFriendCompanyActivity.friendSendModeLayout = (FriendSendModeLayoutCompany) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayoutCompany.class);
        groupSendTextImageToFriendCompanyActivity.intimateModelLayout = (IntimateModelLayout) Utils.findRequiredViewAsType(view, 2131296813, "field 'intimateModelLayout'", IntimateModelLayout.class);
        groupSendTextImageToFriendCompanyActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendTextImageToFriendCompanyActivity.sendContentLayout = (SendContentLayout) Utils.findRequiredViewAsType(view, 2131297333, "field 'sendContentLayout'", SendContentLayout.class);
        groupSendTextImageToFriendCompanyActivity.textLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297464, "field 'textLayout'", LinearLayout.class);
        groupSendTextImageToFriendCompanyActivity.picLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297131, "field 'picLayout'", LinearLayout.class);
        groupSendTextImageToFriendCompanyActivity.selectPicVideoLayout = (SelectPicVideoCompanyLayout) Utils.findRequiredViewAsType(view, 2131297315, "field 'selectPicVideoLayout'", SelectPicVideoCompanyLayout.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToFriendCompanyActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        GroupSendTextImageToFriendCompanyActivity groupSendTextImageToFriendCompanyActivity = this.target;
        if (groupSendTextImageToFriendCompanyActivity != null) {
            this.target = null;
            groupSendTextImageToFriendCompanyActivity.editLeavingMessage = null;
            groupSendTextImageToFriendCompanyActivity.linearLayoutTemplate = null;
            groupSendTextImageToFriendCompanyActivity.navBack = null;
            groupSendTextImageToFriendCompanyActivity.navTitle = null;
            groupSendTextImageToFriendCompanyActivity.startWx = null;
            groupSendTextImageToFriendCompanyActivity.videoIntroduceLayout = null;
            groupSendTextImageToFriendCompanyActivity.cleanEditText = null;
            groupSendTextImageToFriendCompanyActivity.executeTimeSpaceLayout = null;
            groupSendTextImageToFriendCompanyActivity.friendSendModeLayout = null;
            groupSendTextImageToFriendCompanyActivity.intimateModelLayout = null;
            groupSendTextImageToFriendCompanyActivity.navRightImg = null;
            groupSendTextImageToFriendCompanyActivity.sendContentLayout = null;
            groupSendTextImageToFriendCompanyActivity.textLayout = null;
            groupSendTextImageToFriendCompanyActivity.picLayout = null;
            groupSendTextImageToFriendCompanyActivity.selectPicVideoLayout = null;
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
