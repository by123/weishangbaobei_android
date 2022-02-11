package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class InviteFriendFragment_ViewBinding implements Unbinder {
    private InviteFriendFragment target;
    private View view2131297365;
    private View view2131297366;
    private View view2131297367;
    private View view2131297368;

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InviteFriendFragment_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.fragment.InviteFriendFragment_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.wx.assistants.fragment.InviteFriendFragment_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v13, types: [com.wx.assistants.fragment.InviteFriendFragment_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public InviteFriendFragment_ViewBinding(final InviteFriendFragment inviteFriendFragment, View view) {
        this.target = inviteFriendFragment;
        inviteFriendFragment.banner = (ViewPager) Utils.findRequiredViewAsType(view, 2131296373, "field 'banner'", ViewPager.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297368, "field 'sharedWechatLayout' and method 'onViewClicked'");
        inviteFriendFragment.sharedWechatLayout = (LinearLayout) Utils.castView(findRequiredView, 2131297368, "field 'sharedWechatLayout'", LinearLayout.class);
        this.view2131297368 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297367, "field 'sharedWechatCircleLayout' and method 'onViewClicked'");
        inviteFriendFragment.sharedWechatCircleLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297367, "field 'sharedWechatCircleLayout'", LinearLayout.class);
        this.view2131297367 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendFragment.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297365, "field 'sharedQQLayout' and method 'onViewClicked'");
        inviteFriendFragment.sharedQQLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297365, "field 'sharedQQLayout'", LinearLayout.class);
        this.view2131297365 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendFragment.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297366, "field 'sharedQzoneLayout' and method 'onViewClicked'");
        inviteFriendFragment.sharedQzoneLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297366, "field 'sharedQzoneLayout'", LinearLayout.class);
        this.view2131297366 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteFriendFragment.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        InviteFriendFragment inviteFriendFragment = this.target;
        if (inviteFriendFragment != null) {
            this.target = null;
            inviteFriendFragment.banner = null;
            inviteFriendFragment.sharedWechatLayout = null;
            inviteFriendFragment.sharedWechatCircleLayout = null;
            inviteFriendFragment.sharedQQLayout = null;
            inviteFriendFragment.sharedQzoneLayout = null;
            this.view2131297368.setOnClickListener((View.OnClickListener) null);
            this.view2131297368 = null;
            this.view2131297367.setOnClickListener((View.OnClickListener) null);
            this.view2131297367 = null;
            this.view2131297365.setOnClickListener((View.OnClickListener) null);
            this.view2131297365 = null;
            this.view2131297366.setOnClickListener((View.OnClickListener) null);
            this.view2131297366 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
