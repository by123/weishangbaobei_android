package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class CleanZombiePullGroupActivity_ViewBinding implements Unbinder {
    private CleanZombiePullGroupActivity target;
    private View view2131296871;
    private View view2131296877;
    private View view2131297049;
    private View view2131297412;
    private View view2131297422;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanZombiePullGroupActivity_ViewBinding(CleanZombiePullGroupActivity cleanZombiePullGroupActivity) {
        this(cleanZombiePullGroupActivity, cleanZombiePullGroupActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v41, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v49, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombiePullGroupActivity_ViewBinding$7] */
    @UiThread
    public CleanZombiePullGroupActivity_ViewBinding(final CleanZombiePullGroupActivity cleanZombiePullGroupActivity, View view) {
        this.target = cleanZombiePullGroupActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        cleanZombiePullGroupActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296871, "field 'jumpFriendLayout' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.jumpFriendLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131296871, "field 'jumpFriendLayout'", LinearLayout.class);
        this.view2131296871 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297412, "field 'startLayout' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.startLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297412, "field 'startLayout'", LinearLayout.class);
        this.view2131297412 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.startWx = (Button) Utils.castView(findRequiredView4, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        cleanZombiePullGroupActivity.graphicExplanationLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296731, "field 'graphicExplanationLayout'", LinearLayout.class);
        cleanZombiePullGroupActivity.descText = (TextView) Utils.findRequiredViewAsType(view, 2131296585, "field 'descText'", TextView.class);
        cleanZombiePullGroupActivity.startPull = (TextView) Utils.findRequiredViewAsType(view, 2131297419, "field 'startPull'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297422, "field 'startPullLayout' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.startPullLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131297422, "field 'startPullLayout'", LinearLayout.class);
        this.view2131297422 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
        cleanZombiePullGroupActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296873, "field 'jumpLabel'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296877, "field 'jumpLabelLayout' and method 'onViewClicked'");
        cleanZombiePullGroupActivity.jumpLabelLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131296877, "field 'jumpLabelLayout'", LinearLayout.class);
        this.view2131296877 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombiePullGroupActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CleanZombiePullGroupActivity cleanZombiePullGroupActivity = this.target;
        if (cleanZombiePullGroupActivity != null) {
            this.target = null;
            cleanZombiePullGroupActivity.navBack = null;
            cleanZombiePullGroupActivity.navTitle = null;
            cleanZombiePullGroupActivity.jumpFriendLayout = null;
            cleanZombiePullGroupActivity.startLayout = null;
            cleanZombiePullGroupActivity.startWx = null;
            cleanZombiePullGroupActivity.videoIntroduceLayout = null;
            cleanZombiePullGroupActivity.graphicExplanationLayout = null;
            cleanZombiePullGroupActivity.descText = null;
            cleanZombiePullGroupActivity.startPull = null;
            cleanZombiePullGroupActivity.startPullLayout = null;
            cleanZombiePullGroupActivity.jumpLabel = null;
            cleanZombiePullGroupActivity.jumpLabelLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296871.setOnClickListener((View.OnClickListener) null);
            this.view2131296871 = null;
            this.view2131297412.setOnClickListener((View.OnClickListener) null);
            this.view2131297412 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297422.setOnClickListener((View.OnClickListener) null);
            this.view2131297422 = null;
            this.view2131296877.setOnClickListener((View.OnClickListener) null);
            this.view2131296877 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
