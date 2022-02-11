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
import com.wx.assistants.view.GroupSendModeLayoutH;

public class GroupSendVoiceToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendVoiceToGroupActivity target;
    private View view2131296507;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297427;
    private View view2131297636;

    @UiThread
    public GroupSendVoiceToGroupActivity_ViewBinding(GroupSendVoiceToGroupActivity groupSendVoiceToGroupActivity) {
        this(groupSendVoiceToGroupActivity, groupSendVoiceToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v25, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v30, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v38, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v64, types: [com.wx.assistants.activity.GroupSendVoiceToGroupActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendVoiceToGroupActivity_ViewBinding(final GroupSendVoiceToGroupActivity groupSendVoiceToGroupActivity, View view) {
        this.target = groupSendVoiceToGroupActivity;
        groupSendVoiceToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
        groupSendVoiceToGroupActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        groupSendVoiceToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendVoiceToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendVoiceToGroupActivity.editNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296619, "field 'editNickName'", EditText.class);
        groupSendVoiceToGroupActivity.editTitle = (EditText) Utils.findRequiredViewAsType(view, 2131296624, "field 'editTitle'", EditText.class);
        groupSendVoiceToGroupActivity.editDesc = (EditText) Utils.findRequiredViewAsType(view, 2131296614, "field 'editDesc'", EditText.class);
        groupSendVoiceToGroupActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297427, "field 'startWxTag' and method 'onViewClicked'");
        groupSendVoiceToGroupActivity.startWxTag = (Button) Utils.castView(findRequiredView7, 2131297427, "field 'startWxTag'", Button.class);
        this.view2131297427 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendVoiceToGroupActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        GroupSendVoiceToGroupActivity groupSendVoiceToGroupActivity = this.target;
        if (groupSendVoiceToGroupActivity != null) {
            this.target = null;
            groupSendVoiceToGroupActivity.editLeavingMessage = null;
            groupSendVoiceToGroupActivity.navBack = null;
            groupSendVoiceToGroupActivity.navTitle = null;
            groupSendVoiceToGroupActivity.startWx = null;
            groupSendVoiceToGroupActivity.videoIntroduceLayout = null;
            groupSendVoiceToGroupActivity.navRightLayout = null;
            groupSendVoiceToGroupActivity.linearLayoutTemplate = null;
            groupSendVoiceToGroupActivity.navRightImg = null;
            groupSendVoiceToGroupActivity.cleanEditText = null;
            groupSendVoiceToGroupActivity.circulateModeLayout = null;
            groupSendVoiceToGroupActivity.executeTimeSpaceLayout = null;
            groupSendVoiceToGroupActivity.groupSendModeLayout = null;
            groupSendVoiceToGroupActivity.editNickName = null;
            groupSendVoiceToGroupActivity.editTitle = null;
            groupSendVoiceToGroupActivity.editDesc = null;
            groupSendVoiceToGroupActivity.circulateNum = null;
            groupSendVoiceToGroupActivity.startWxTag = null;
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
