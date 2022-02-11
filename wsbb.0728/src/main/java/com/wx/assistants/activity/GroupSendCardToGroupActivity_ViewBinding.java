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

public class GroupSendCardToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendCardToGroupActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendCardToGroupActivity_ViewBinding(GroupSendCardToGroupActivity groupSendCardToGroupActivity) {
        this(groupSendCardToGroupActivity, groupSendCardToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v23, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v28, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendCardToGroupActivity_ViewBinding$4] */
    @UiThread
    public GroupSendCardToGroupActivity_ViewBinding(final GroupSendCardToGroupActivity groupSendCardToGroupActivity, View view) {
        this.target = groupSendCardToGroupActivity;
        groupSendCardToGroupActivity.editCardNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296613, "field 'editCardNickName'", EditText.class);
        groupSendCardToGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendCardToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToGroupActivity.onViewClicked(view);
            }
        });
        groupSendCardToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendCardToGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendCardToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendCardToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendCardToGroupActivity.onViewClicked(view);
            }
        });
        groupSendCardToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        groupSendCardToGroupActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendCardToGroupActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        groupSendCardToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendCardToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendCardToGroupActivity.sendGroupMethodLayout = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131297334, "field 'sendGroupMethodLayout'", CustomRadioLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendCardToGroupActivity groupSendCardToGroupActivity = this.target;
        if (groupSendCardToGroupActivity != null) {
            this.target = null;
            groupSendCardToGroupActivity.editCardNickName = null;
            groupSendCardToGroupActivity.editLeavingMessage = null;
            groupSendCardToGroupActivity.navBack = null;
            groupSendCardToGroupActivity.navTitle = null;
            groupSendCardToGroupActivity.startWx = null;
            groupSendCardToGroupActivity.videoIntroduceLayout = null;
            groupSendCardToGroupActivity.navRightLayout = null;
            groupSendCardToGroupActivity.navRightImg = null;
            groupSendCardToGroupActivity.circulateNum = null;
            groupSendCardToGroupActivity.circulateModeLayout = null;
            groupSendCardToGroupActivity.executeTimeSpaceLayout = null;
            groupSendCardToGroupActivity.groupSendModeLayout = null;
            groupSendCardToGroupActivity.sendGroupMethodLayout = null;
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
