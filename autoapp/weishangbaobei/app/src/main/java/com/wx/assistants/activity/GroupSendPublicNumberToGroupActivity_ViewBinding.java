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

public class GroupSendPublicNumberToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendPublicNumberToGroupActivity target;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendPublicNumberToGroupActivity_ViewBinding(GroupSendPublicNumberToGroupActivity groupSendPublicNumberToGroupActivity) {
        this(groupSendPublicNumberToGroupActivity, groupSendPublicNumberToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v9, types: [com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v21, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendPublicNumberToGroupActivity_ViewBinding$5] */
    @UiThread
    public GroupSendPublicNumberToGroupActivity_ViewBinding(final GroupSendPublicNumberToGroupActivity groupSendPublicNumberToGroupActivity, View view) {
        this.target = groupSendPublicNumberToGroupActivity;
        groupSendPublicNumberToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendPublicNumberToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToGroupActivity.onViewClicked(view);
            }
        });
        groupSendPublicNumberToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendPublicNumberToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendPublicNumberToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendPublicNumberToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendPublicNumberToGroupActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendPublicNumberToGroupActivity.onViewClicked(view);
            }
        });
        groupSendPublicNumberToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendPublicNumberToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendPublicNumberToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendPublicNumberToGroupActivity groupSendPublicNumberToGroupActivity = this.target;
        if (groupSendPublicNumberToGroupActivity != null) {
            this.target = null;
            groupSendPublicNumberToGroupActivity.editLeavingMessage = null;
            groupSendPublicNumberToGroupActivity.navBack = null;
            groupSendPublicNumberToGroupActivity.navTitle = null;
            groupSendPublicNumberToGroupActivity.startWx = null;
            groupSendPublicNumberToGroupActivity.videoIntroduceLayout = null;
            groupSendPublicNumberToGroupActivity.navRightLayout = null;
            groupSendPublicNumberToGroupActivity.linearLayoutTemplate = null;
            groupSendPublicNumberToGroupActivity.navRightImg = null;
            groupSendPublicNumberToGroupActivity.groupSendModeLayout = null;
            groupSendPublicNumberToGroupActivity.executeTimeSpaceLayout = null;
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
