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
import com.wx.assistants.view.CirculateModelLayout;
import com.wx.assistants.view.CirculateNumLayout;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.GroupSendModeLayoutH;
import com.wx.assistants.view.SelectPicVideoLayout;
import com.wx.assistants.view.SendContentLayout;

public class GroupSendTextImageToGroupActivity_ViewBinding implements Unbinder {
    private GroupSendTextImageToGroupActivity target;
    private View view2131296507;
    private View view2131296616;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendTextImageToGroupActivity_ViewBinding(GroupSendTextImageToGroupActivity groupSendTextImageToGroupActivity) {
        this(groupSendTextImageToGroupActivity, groupSendTextImageToGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v24, types: [com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v29, types: [com.wx.assistants.activity.GroupSendTextImageToGroupActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public GroupSendTextImageToGroupActivity_ViewBinding(final GroupSendTextImageToGroupActivity groupSendTextImageToGroupActivity, View view) {
        this.target = groupSendTextImageToGroupActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296616, "field 'editLeavingMessage' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.editLeavingMessage = (EditTextWithScrollView) Utils.castView(findRequiredView, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        this.view2131296616 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView2, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.startWx = (Button) Utils.castView(findRequiredView4, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendTextImageToGroupActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView7, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendTextImageToGroupActivity.onViewClicked(view);
            }
        });
        groupSendTextImageToGroupActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        groupSendTextImageToGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        groupSendTextImageToGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH.class);
        groupSendTextImageToGroupActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        groupSendTextImageToGroupActivity.sendGroupMethodLayout = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131297334, "field 'sendGroupMethodLayout'", CustomRadioLayout.class);
        groupSendTextImageToGroupActivity.sendContentLayout = (SendContentLayout) Utils.findRequiredViewAsType(view, 2131297333, "field 'sendContentLayout'", SendContentLayout.class);
        groupSendTextImageToGroupActivity.textLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297464, "field 'textLayout'", LinearLayout.class);
        groupSendTextImageToGroupActivity.picLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297131, "field 'picLayout'", LinearLayout.class);
        groupSendTextImageToGroupActivity.selectPicVideoLayout = (SelectPicVideoLayout) Utils.findRequiredViewAsType(view, 2131297315, "field 'selectPicVideoLayout'", SelectPicVideoLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendTextImageToGroupActivity groupSendTextImageToGroupActivity = this.target;
        if (groupSendTextImageToGroupActivity != null) {
            this.target = null;
            groupSendTextImageToGroupActivity.editLeavingMessage = null;
            groupSendTextImageToGroupActivity.linearLayoutTemplate = null;
            groupSendTextImageToGroupActivity.navBack = null;
            groupSendTextImageToGroupActivity.navTitle = null;
            groupSendTextImageToGroupActivity.startWx = null;
            groupSendTextImageToGroupActivity.videoIntroduceLayout = null;
            groupSendTextImageToGroupActivity.navRightLayout = null;
            groupSendTextImageToGroupActivity.navRightImg = null;
            groupSendTextImageToGroupActivity.cleanEditText = null;
            groupSendTextImageToGroupActivity.circulateModeLayout = null;
            groupSendTextImageToGroupActivity.executeTimeSpaceLayout = null;
            groupSendTextImageToGroupActivity.groupSendModeLayout = null;
            groupSendTextImageToGroupActivity.circulateNum = null;
            groupSendTextImageToGroupActivity.sendGroupMethodLayout = null;
            groupSendTextImageToGroupActivity.sendContentLayout = null;
            groupSendTextImageToGroupActivity.textLayout = null;
            groupSendTextImageToGroupActivity.picLayout = null;
            groupSendTextImageToGroupActivity.selectPicVideoLayout = null;
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
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
