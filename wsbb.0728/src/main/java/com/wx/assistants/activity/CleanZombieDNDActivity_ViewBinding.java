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
import com.wx.assistants.view.CleanZombieDNDLayout;
import com.wx.assistants.view.FriendSendModeLayoutZombies;
import com.wx.assistants.view.SingleLabelSelectLayout;

public class CleanZombieDNDActivity_ViewBinding implements Unbinder {
    private CleanZombieDNDActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanZombieDNDActivity_ViewBinding(CleanZombieDNDActivity cleanZombieDNDActivity) {
        this(cleanZombieDNDActivity, cleanZombieDNDActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v16, types: [com.wx.assistants.activity.CleanZombieDNDActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.CleanZombieDNDActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v29, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombieDNDActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v34, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanZombieDNDActivity_ViewBinding$4] */
    @UiThread
    public CleanZombieDNDActivity_ViewBinding(final CleanZombieDNDActivity cleanZombieDNDActivity, View view) {
        this.target = cleanZombieDNDActivity;
        cleanZombieDNDActivity.deleteSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296581, "field 'deleteSwitchBtn'", Switch.class);
        cleanZombieDNDActivity.deleteShieldSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296580, "field 'deleteShieldSwitchBtn'", Switch.class);
        cleanZombieDNDActivity.shieldDeleteLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297369, "field 'shieldDeleteLayout'", LinearLayout.class);
        cleanZombieDNDActivity.singleSelectLabelOutLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297387, "field 'singleSelectLabelOutLayout'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanZombieDNDActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieDNDActivity.onViewClicked(view);
            }
        });
        cleanZombieDNDActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanZombieDNDActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieDNDActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanZombieDNDActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieDNDActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanZombieDNDActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanZombieDNDActivity.onViewClicked(view);
            }
        });
        cleanZombieDNDActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cleanZombieDNDActivity.cleanZombieDndLayout = (CleanZombieDNDLayout) Utils.findRequiredViewAsType(view, 2131296512, "field 'cleanZombieDndLayout'", CleanZombieDNDLayout.class);
        cleanZombieDNDActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        cleanZombieDNDActivity.friendSendModeLayout = (FriendSendModeLayoutZombies) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendSendModeLayoutZombies.class);
    }

    @CallSuper
    public void unbind() {
        CleanZombieDNDActivity cleanZombieDNDActivity = this.target;
        if (cleanZombieDNDActivity != null) {
            this.target = null;
            cleanZombieDNDActivity.deleteSwitchBtn = null;
            cleanZombieDNDActivity.deleteShieldSwitchBtn = null;
            cleanZombieDNDActivity.shieldDeleteLayout = null;
            cleanZombieDNDActivity.singleSelectLabelOutLayout = null;
            cleanZombieDNDActivity.navBack = null;
            cleanZombieDNDActivity.navTitle = null;
            cleanZombieDNDActivity.startWx = null;
            cleanZombieDNDActivity.videoIntroduceLayout = null;
            cleanZombieDNDActivity.navRightLayout = null;
            cleanZombieDNDActivity.navRightImg = null;
            cleanZombieDNDActivity.cleanZombieDndLayout = null;
            cleanZombieDNDActivity.singleSelectLabelLayout = null;
            cleanZombieDNDActivity.friendSendModeLayout = null;
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
