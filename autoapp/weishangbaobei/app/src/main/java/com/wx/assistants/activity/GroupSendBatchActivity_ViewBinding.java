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
import com.wx.assistants.view.SendOrderLayout;
import com.wx.assistants.view.TagCloudLayout;

public class GroupSendBatchActivity_ViewBinding implements Unbinder {
    private GroupSendBatchActivity target;
    private View view2131296507;
    private View view2131296508;
    private View view2131296617;
    private View view2131296681;
    private View view2131296950;
    private View view2131297049;
    private View view2131297065;
    private View view2131297311;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public GroupSendBatchActivity_ViewBinding(GroupSendBatchActivity groupSendBatchActivity) {
        this(groupSendBatchActivity, groupSendBatchActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r1v25, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r1v29, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r1v36, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r1v40, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r1v44, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GroupSendBatchActivity_ViewBinding$10] */
    @UiThread
    public GroupSendBatchActivity_ViewBinding(final GroupSendBatchActivity groupSendBatchActivity, View view) {
        this.target = groupSendBatchActivity;
        groupSendBatchActivity.editLeavingMessage = (EditTextWithScrollView) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        View findRequiredView = Utils.findRequiredView(view, 2131296617, "field 'editLeavingMessage2' and method 'onViewClicked'");
        groupSendBatchActivity.editLeavingMessage2 = (LinearLayout) Utils.castView(findRequiredView, 2131296617, "field 'editLeavingMessage2'", LinearLayout.class);
        this.view2131296617 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296950, "field 'linearLayoutTemplate' and method 'onViewClicked'");
        groupSendBatchActivity.linearLayoutTemplate = (LinearLayout) Utils.castView(findRequiredView2, 2131296950, "field 'linearLayoutTemplate'", LinearLayout.class);
        this.view2131296950 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296681, "field 'flowViewGroup' and method 'onViewClicked'");
        groupSendBatchActivity.flowViewGroup = (TagCloudLayout) Utils.castView(findRequiredView3, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        this.view2131296681 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297311, "field 'selectImg' and method 'onViewClicked'");
        groupSendBatchActivity.selectImg = (ImageView) Utils.castView(findRequiredView4, 2131297311, "field 'selectImg'", ImageView.class);
        this.view2131297311 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        groupSendBatchActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        groupSendBatchActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        groupSendBatchActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        groupSendBatchActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        groupSendBatchActivity.graphicExplanationLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296731, "field 'graphicExplanationLayout'", LinearLayout.class);
        groupSendBatchActivity.descText = (TextView) Utils.findRequiredViewAsType(view, 2131296585, "field 'descText'", TextView.class);
        groupSendBatchActivity.noSendLabText = (TextView) Utils.findRequiredViewAsType(view, 2131297066, "field 'noSendLabText'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297065, "field 'noSendLabLayout' and method 'onViewClicked'");
        groupSendBatchActivity.noSendLabLayout = (LinearLayout) Utils.castView(findRequiredView8, 2131297065, "field 'noSendLabLayout'", LinearLayout.class);
        this.view2131297065 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        groupSendBatchActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView9, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, 2131296508, "field 'cleanImage' and method 'onViewClicked'");
        groupSendBatchActivity.cleanImage = (LinearLayout) Utils.castView(findRequiredView10, 2131296508, "field 'cleanImage'", LinearLayout.class);
        this.view2131296508 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                groupSendBatchActivity.onViewClicked(view);
            }
        });
        groupSendBatchActivity.sendOrderLayout = (SendOrderLayout) Utils.findRequiredViewAsType(view, 2131297335, "field 'sendOrderLayout'", SendOrderLayout.class);
    }

    @CallSuper
    public void unbind() {
        GroupSendBatchActivity groupSendBatchActivity = this.target;
        if (groupSendBatchActivity != null) {
            this.target = null;
            groupSendBatchActivity.editLeavingMessage = null;
            groupSendBatchActivity.editLeavingMessage2 = null;
            groupSendBatchActivity.linearLayoutTemplate = null;
            groupSendBatchActivity.flowViewGroup = null;
            groupSendBatchActivity.selectImg = null;
            groupSendBatchActivity.navBack = null;
            groupSendBatchActivity.navTitle = null;
            groupSendBatchActivity.startWx = null;
            groupSendBatchActivity.videoIntroduceLayout = null;
            groupSendBatchActivity.graphicExplanationLayout = null;
            groupSendBatchActivity.descText = null;
            groupSendBatchActivity.noSendLabText = null;
            groupSendBatchActivity.noSendLabLayout = null;
            groupSendBatchActivity.cleanEditText = null;
            groupSendBatchActivity.cleanImage = null;
            groupSendBatchActivity.sendOrderLayout = null;
            this.view2131296617.setOnClickListener((View.OnClickListener) null);
            this.view2131296617 = null;
            this.view2131296950.setOnClickListener((View.OnClickListener) null);
            this.view2131296950 = null;
            this.view2131296681.setOnClickListener((View.OnClickListener) null);
            this.view2131296681 = null;
            this.view2131297311.setOnClickListener((View.OnClickListener) null);
            this.view2131297311 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297065.setOnClickListener((View.OnClickListener) null);
            this.view2131297065 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131296508.setOnClickListener((View.OnClickListener) null);
            this.view2131296508 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
