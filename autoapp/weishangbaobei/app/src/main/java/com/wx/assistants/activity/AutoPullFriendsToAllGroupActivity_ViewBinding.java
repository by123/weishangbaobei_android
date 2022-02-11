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
import com.wx.assistants.view.GroupSendModeLayoutH2;

public class AutoPullFriendsToAllGroupActivity_ViewBinding implements Unbinder {
    private AutoPullFriendsToAllGroupActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoPullFriendsToAllGroupActivity_ViewBinding(AutoPullFriendsToAllGroupActivity autoPullFriendsToAllGroupActivity) {
        this(autoPullFriendsToAllGroupActivity, autoPullFriendsToAllGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v37, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoPullFriendsToAllGroupActivity_ViewBinding$4] */
    @UiThread
    public AutoPullFriendsToAllGroupActivity_ViewBinding(final AutoPullFriendsToAllGroupActivity autoPullFriendsToAllGroupActivity, View view) {
        this.target = autoPullFriendsToAllGroupActivity;
        autoPullFriendsToAllGroupActivity.editCardNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296613, "field 'editCardNickName'", EditText.class);
        autoPullFriendsToAllGroupActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoPullFriendsToAllGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToAllGroupActivity.onViewClicked(view);
            }
        });
        autoPullFriendsToAllGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoPullFriendsToAllGroupActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToAllGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoPullFriendsToAllGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToAllGroupActivity.onViewClicked(view);
            }
        });
        autoPullFriendsToAllGroupActivity.circulateNum = (CirculateNumLayout) Utils.findRequiredViewAsType(view, 2131296493, "field 'circulateNum'", CirculateNumLayout.class);
        autoPullFriendsToAllGroupActivity.circulateModeLayout = (CirculateModelLayout) Utils.findRequiredViewAsType(view, 2131296492, "field 'circulateModeLayout'", CirculateModelLayout.class);
        autoPullFriendsToAllGroupActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoPullFriendsToAllGroupActivity.groupSendModeLayout = (GroupSendModeLayoutH2) Utils.findRequiredViewAsType(view, 2131296736, "field 'groupSendModeLayout'", GroupSendModeLayoutH2.class);
        autoPullFriendsToAllGroupActivity.sendGroupMethodLayout = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131297334, "field 'sendGroupMethodLayout'", CustomRadioLayout.class);
        autoPullFriendsToAllGroupActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoPullFriendsToAllGroupActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPullFriendsToAllGroupActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoPullFriendsToAllGroupActivity autoPullFriendsToAllGroupActivity = this.target;
        if (autoPullFriendsToAllGroupActivity != null) {
            this.target = null;
            autoPullFriendsToAllGroupActivity.editCardNickName = null;
            autoPullFriendsToAllGroupActivity.editLeavingMessage = null;
            autoPullFriendsToAllGroupActivity.navBack = null;
            autoPullFriendsToAllGroupActivity.navTitle = null;
            autoPullFriendsToAllGroupActivity.startWx = null;
            autoPullFriendsToAllGroupActivity.videoIntroduceLayout = null;
            autoPullFriendsToAllGroupActivity.circulateNum = null;
            autoPullFriendsToAllGroupActivity.circulateModeLayout = null;
            autoPullFriendsToAllGroupActivity.executeTimeSpaceLayout = null;
            autoPullFriendsToAllGroupActivity.groupSendModeLayout = null;
            autoPullFriendsToAllGroupActivity.sendGroupMethodLayout = null;
            autoPullFriendsToAllGroupActivity.navRightImg = null;
            autoPullFriendsToAllGroupActivity.navRightLayout = null;
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
