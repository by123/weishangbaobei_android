package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.CustomSpeedLayout;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.SendContentLayout;
import com.wx.assistants.view.SendOrderLayout;
import com.wx.assistants.view.TagCloudLayout;

public class GroupSendBatchToFriendActivity_ViewBinding implements Unbinder {
    private GroupSendBatchToFriendActivity target;
    private View view2131296507;
    private View view2131296508;
    private View view2131296616;
    private View view2131296617;
    private View view2131296871;
    private View view2131296950;
    private View view2131297049;
    private View view2131297052;
    private View view2131297311;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendBatchToFriendActivity_ViewBinding(GroupSendBatchToFriendActivity groupSendBatchToFriendActivity) {
        this(groupSendBatchToFriendActivity, groupSendBatchToFriendActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v19, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v32, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v37, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v42, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v50, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$9, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v58, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$10] */
    /* JADX WARNING: type inference failed for: r0v87, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchToFriendActivity_ViewBinding$11] */
    @UiThread
    public GroupSendBatchToFriendActivity_ViewBinding(final GroupSendBatchToFriendActivity groupSendBatchToFriendActivity, View view) {
        this.target = groupSendBatchToFriendActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296616, "field 'editLeavingMessage' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.editLeavingMessage = (EditTextWithScrollView) Utils.castView(findRequiredView, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        this.view2131296616 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296617, "field 'editLeavingMessage2' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.editLeavingMessage2 = (LinearLayout) Utils.castView(findRequiredView2, 2131296617, "field 'editLeavingMessage2'", LinearLayout.class);
        this.view2131296617 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView3, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297311, "field 'selectImg' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.selectImg = (ImageView) Utils.castView(findRequiredView4, 2131297311, "field 'selectImg'", ImageView.class);
        this.view2131297311 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        groupSendBatchToFriendActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView8, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        groupSendBatchToFriendActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView9, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        groupSendBatchToFriendActivity.friendSendModeLayout = (FriendSendModeLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayout.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131296508, "field 'cleanImage' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.cleanImage = (LinearLayout) Utils.castView(findRequiredView10, 2131296508, "field 'cleanImage'", LinearLayout.class);
        this.view2131296508 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        groupSendBatchToFriendActivity.sendOrderLayout = (SendOrderLayout) Utils.findRequiredViewAsType(view, 2131297335, "field 'sendOrderLayout'", SendOrderLayout.class);
        groupSendBatchToFriendActivity.sendContentLayout = (SendContentLayout) Utils.findRequiredViewAsType(view, 2131297333, "field 'sendContentLayout'", SendContentLayout.class);
        groupSendBatchToFriendActivity.textLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297464, "field 'textLayout'", LinearLayout.class);
        groupSendBatchToFriendActivity.picLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297131, "field 'picLayout'", LinearLayout.class);
        groupSendBatchToFriendActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296872, "field 'jumpLabel'", TextView.class);
        groupSendBatchToFriendActivity.batchSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296392, "field 'batchSwitchBtn'", Switch.class);
        groupSendBatchToFriendActivity.batchLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296391, "field 'batchLayout'", LinearLayout.class);
        groupSendBatchToFriendActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        View findRequiredView11 = Utils.findRequiredView(view, 2131296871, "field 'jumpFriendLayout' and method 'onViewClicked'");
        groupSendBatchToFriendActivity.jumpFriendLayout = (LinearLayout) Utils.castView(findRequiredView11, 2131296871, "field 'jumpFriendLayout'", LinearLayout.class);
        this.view2131296871 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchToFriendActivity.onViewClicked(view);
            }
        });
        groupSendBatchToFriendActivity.picVideoRadioLayout = (CustomRadioLayout) Utils.findRequiredViewAsType(view, 2131297132, "field 'picVideoRadioLayout'", CustomRadioLayout.class);
        groupSendBatchToFriendActivity.addPicVideoText = (TextView) Utils.findRequiredViewAsType(view, 2131296320, "field 'addPicVideoText'", TextView.class);
        groupSendBatchToFriendActivity.customSpeedLayout = (CustomSpeedLayout) Utils.findRequiredViewAsType(view, 2131296572, "field 'customSpeedLayout'", CustomSpeedLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendBatchToFriendActivity groupSendBatchToFriendActivity = this.target;
        if (groupSendBatchToFriendActivity != null) {
            this.target = null;
            groupSendBatchToFriendActivity.editLeavingMessage = null;
            groupSendBatchToFriendActivity.editLeavingMessage2 = null;
            groupSendBatchToFriendActivity.linearLayoutTemplate = null;
            groupSendBatchToFriendActivity.selectImg = null;
            groupSendBatchToFriendActivity.navBack = null;
            groupSendBatchToFriendActivity.navTitle = null;
            groupSendBatchToFriendActivity.startWx = null;
            groupSendBatchToFriendActivity.videoIntroduceLayout = null;
            groupSendBatchToFriendActivity.navRightLayout = null;
            groupSendBatchToFriendActivity.navRightImg = null;
            groupSendBatchToFriendActivity.cleanEditText = null;
            groupSendBatchToFriendActivity.friendSendModeLayout = null;
            groupSendBatchToFriendActivity.cleanImage = null;
            groupSendBatchToFriendActivity.sendOrderLayout = null;
            groupSendBatchToFriendActivity.sendContentLayout = null;
            groupSendBatchToFriendActivity.textLayout = null;
            groupSendBatchToFriendActivity.picLayout = null;
            groupSendBatchToFriendActivity.jumpLabel = null;
            groupSendBatchToFriendActivity.batchSwitchBtn = null;
            groupSendBatchToFriendActivity.batchLayout = null;
            groupSendBatchToFriendActivity.flowViewGroup = null;
            groupSendBatchToFriendActivity.jumpFriendLayout = null;
            groupSendBatchToFriendActivity.picVideoRadioLayout = null;
            groupSendBatchToFriendActivity.addPicVideoText = null;
            groupSendBatchToFriendActivity.customSpeedLayout = null;
            this.view2131296616.setOnClickListener((View.OnClickListener) null);
            this.view2131296616 = null;
            this.view2131296617.setOnClickListener((View.OnClickListener) null);
            this.view2131296617 = null;
            this.view2131296950.setOnClickListener((View.OnClickListener) null);
            this.view2131296950 = null;
            this.view2131297311.setOnClickListener((View.OnClickListener) null);
            this.view2131297311 = null;
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
            this.view2131296508.setOnClickListener((View.OnClickListener) null);
            this.view2131296508 = null;
            this.view2131296871.setOnClickListener((View.OnClickListener) null);
            this.view2131296871 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
