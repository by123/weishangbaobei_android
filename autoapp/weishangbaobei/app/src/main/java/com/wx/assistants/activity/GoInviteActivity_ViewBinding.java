package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sevenheaven.segmentcontrol.SegmentControl;

public class GoInviteActivity_ViewBinding implements Unbinder {
    private GoInviteActivity target;
    private View view2131296353;
    private View view2131296823;

    @UiThread
    public GoInviteActivity_ViewBinding(GoInviteActivity goInviteActivity) {
        this(goInviteActivity, goInviteActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoInviteActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.GoInviteActivity_ViewBinding$2] */
    @UiThread
    public GoInviteActivity_ViewBinding(final GoInviteActivity goInviteActivity, View view) {
        this.target = goInviteActivity;
        goInviteActivity.myFragment = (FrameLayout) Utils.findRequiredViewAsType(view, 2131297045, "field 'myFragment'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, 2131296353, "field 'arrowBack' and method 'onViewClicked'");
        goInviteActivity.arrowBack = (LinearLayout) Utils.castView(findRequiredView, 2131296353, "field 'arrowBack'", LinearLayout.class);
        this.view2131296353 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goInviteActivity.onViewClicked(view);
            }
        });
        goInviteActivity.segmentControl = (SegmentControl) Utils.findRequiredViewAsType(view, 2131297306, "field 'segmentControl'", SegmentControl.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296823, "field 'inviteRewardLayout' and method 'onViewClicked'");
        goInviteActivity.inviteRewardLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131296823, "field 'inviteRewardLayout'", LinearLayout.class);
        this.view2131296823 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                goInviteActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        GoInviteActivity goInviteActivity = this.target;
        if (goInviteActivity != null) {
            this.target = null;
            goInviteActivity.myFragment = null;
            goInviteActivity.arrowBack = null;
            goInviteActivity.segmentControl = null;
            goInviteActivity.inviteRewardLayout = null;
            this.view2131296353.setOnClickListener((View.OnClickListener) null);
            this.view2131296353 = null;
            this.view2131296823.setOnClickListener((View.OnClickListener) null);
            this.view2131296823 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
