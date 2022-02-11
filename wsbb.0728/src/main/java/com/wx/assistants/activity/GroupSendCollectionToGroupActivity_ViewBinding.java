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
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.GroupSendModeLayoutH;

public class GroupSendCollectionToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendCollectionToGroupActivity target;
    private View view2131296507;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendCollectionToGroupActivity_ViewBinding(GroupSendCollectionToGroupActivity groupSendCollectionToGroupActivity) {
        this(groupSendCollectionToGroupActivity, groupSendCollectionToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v25, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v30, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v38, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCollectionToGroupActivity_ViewBinding$6] */
    @UiThread
    public GroupSendCollectionToGroupActivity_ViewBinding(final GroupSendCollectionToGroupActivity groupSendCollectionToGroupActivity, View view) {
        this.target = groupSendCollectionToGroupActivity;
        groupSendCollectionToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView5, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendCollectionToGroupActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView6, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCollectionToGroupActivity.onViewClicked(view);
            }
        });
        groupSendCollectionToGroupActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendCollectionToGroupActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        groupSendCollectionToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendCollectionToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendCollectionToGroupActivity.sendGroupMethodLayout = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131297334, "field 'sendGroupMethodLayout'", CustomRadioLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendCollectionToGroupActivity groupSendCollectionToGroupActivity = this.target;
        if (groupSendCollectionToGroupActivity != null) {
            this.target = null;
            groupSendCollectionToGroupActivity.editLeavingMessage = null;
            groupSendCollectionToGroupActivity.navBack = null;
            groupSendCollectionToGroupActivity.navTitle = null;
            groupSendCollectionToGroupActivity.startWx = null;
            groupSendCollectionToGroupActivity.videoIntroduceLayout = null;
            groupSendCollectionToGroupActivity.navRightLayout = null;
            groupSendCollectionToGroupActivity.linearLayoutTemplate = null;
            groupSendCollectionToGroupActivity.navRightImg = null;
            groupSendCollectionToGroupActivity.cleanEditText = null;
            groupSendCollectionToGroupActivity.circulateNum = null;
            groupSendCollectionToGroupActivity.circulateModeLayout = null;
            groupSendCollectionToGroupActivity.executeTimeSpaceLayout = null;
            groupSendCollectionToGroupActivity.groupSendModeLayout = null;
            groupSendCollectionToGroupActivity.sendGroupMethodLayout = null;
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
