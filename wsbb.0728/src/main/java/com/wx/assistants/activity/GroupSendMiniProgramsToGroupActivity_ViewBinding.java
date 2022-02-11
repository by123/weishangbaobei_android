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
import com.wx.assistants.view.GroupSendModeLayoutH;

public class GroupSendMiniProgramsToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendMiniProgramsToGroupActivity target;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendMiniProgramsToGroupActivity_ViewBinding(GroupSendMiniProgramsToGroupActivity groupSendMiniProgramsToGroupActivity) {
        this(groupSendMiniProgramsToGroupActivity, groupSendMiniProgramsToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v25, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v30, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendMiniProgramsToGroupActivity_ViewBinding$5] */
    @UiThread
    public GroupSendMiniProgramsToGroupActivity_ViewBinding(final GroupSendMiniProgramsToGroupActivity groupSendMiniProgramsToGroupActivity, View view) {
        this.target = groupSendMiniProgramsToGroupActivity;
        groupSendMiniProgramsToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendMiniProgramsToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendMiniProgramsToGroupActivity.onViewClicked(view);
            }
        });
        groupSendMiniProgramsToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendMiniProgramsToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendMiniProgramsToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendMiniProgramsToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendMiniProgramsToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendMiniProgramsToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendMiniProgramsToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendMiniProgramsToGroupActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendMiniProgramsToGroupActivity.onViewClicked(view);
            }
        });
        groupSendMiniProgramsToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendMiniProgramsToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendMiniProgramsToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendMiniProgramsToGroupActivity groupSendMiniProgramsToGroupActivity = this.target;
        if (groupSendMiniProgramsToGroupActivity != null) {
            this.target = null;
            groupSendMiniProgramsToGroupActivity.editLeavingMessage = null;
            groupSendMiniProgramsToGroupActivity.navBack = null;
            groupSendMiniProgramsToGroupActivity.navTitle = null;
            groupSendMiniProgramsToGroupActivity.startWx = null;
            groupSendMiniProgramsToGroupActivity.videoIntroduceLayout = null;
            groupSendMiniProgramsToGroupActivity.navRightLayout = null;
            groupSendMiniProgramsToGroupActivity.linearLayoutTemplate = null;
            groupSendMiniProgramsToGroupActivity.navRightImg = null;
            groupSendMiniProgramsToGroupActivity.groupSendModeLayout = null;
            groupSendMiniProgramsToGroupActivity.executeTimeSpaceLayout = null;
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
