package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class InviteFriendActivity_ViewBinding implements Unbinder {
    private InviteFriendActivity target;
    private View view2131297049;
    private View view2131297365;
    private View view2131297366;
    private View view2131297367;
    private View view2131297368;

    @UiThread
    public InviteFriendActivity_ViewBinding(InviteFriendActivity inviteFriendActivity) {
        this(inviteFriendActivity, inviteFriendActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteFriendActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteFriendActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteFriendActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v16, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteFriendActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.InviteFriendActivity_ViewBinding$5] */
    @UiThread
    public InviteFriendActivity_ViewBinding(final InviteFriendActivity inviteFriendActivity, View view) {
        this.target = inviteFriendActivity;
        inviteFriendActivity.banner = (ViewPager) Utils.findRequiredViewAsType(view, 2131296373, "field 'banner'", ViewPager.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297368, "field 'sharedWechatLayout' and method 'onViewClicked'");
        inviteFriendActivity.sharedWechatLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297368, "field 'sharedWechatLayout'", LinearLayout.class);
        this.view2131297368 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297367, "field 'sharedWechatCircleLayout' and method 'onViewClicked'");
        inviteFriendActivity.sharedWechatCircleLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297367, "field 'sharedWechatCircleLayout'", LinearLayout.class);
        this.view2131297367 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297365, "field 'sharedQQLayout' and method 'onViewClicked'");
        inviteFriendActivity.sharedQQLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297365, "field 'sharedQQLayout'", LinearLayout.class);
        this.view2131297365 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297366, "field 'sharedQzoneLayout' and method 'onViewClicked'");
        inviteFriendActivity.sharedQzoneLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297366, "field 'sharedQzoneLayout'", LinearLayout.class);
        this.view2131297366 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        inviteFriendActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendActivity.onViewClicked(view);
            }
        });
        inviteFriendActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InviteFriendActivity inviteFriendActivity = this.target;
        if (inviteFriendActivity != null) {
            this.target = null;
            inviteFriendActivity.banner = null;
            inviteFriendActivity.sharedWechatLayout = null;
            inviteFriendActivity.sharedWechatCircleLayout = null;
            inviteFriendActivity.sharedQQLayout = null;
            inviteFriendActivity.sharedQzoneLayout = null;
            inviteFriendActivity.navBack = null;
            inviteFriendActivity.navTitle = null;
            this.view2131297368.setOnClickListener((View.OnClickListener) null);
            this.view2131297368 = null;
            this.view2131297367.setOnClickListener((View.OnClickListener) null);
            this.view2131297367 = null;
            this.view2131297365.setOnClickListener((View.OnClickListener) null);
            this.view2131297365 = null;
            this.view2131297366.setOnClickListener((View.OnClickListener) null);
            this.view2131297366 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
