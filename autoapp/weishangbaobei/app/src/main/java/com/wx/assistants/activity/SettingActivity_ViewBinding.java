package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xw.repo.BubbleSeekBar;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class SettingActivity_ViewBinding implements Unbinder {
    private SettingActivity target;
    private View view2131296561;
    private View view2131296601;
    private View view2131296649;
    private View view2131296666;
    private View view2131297049;
    private View view2131297350;
    private View view2131297363;
    private View view2131297622;
    private View view2131297623;
    private View view2131297693;

    @UiThread
    public SettingActivity_ViewBinding(SettingActivity settingActivity) {
        this(settingActivity, settingActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.SettingActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.SettingActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v21, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v25, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v29, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v33, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$8, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v37, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$9, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v34, types: [com.wx.assistants.activity.SettingActivity_ViewBinding$10, android.view.View$OnClickListener] */
    @UiThread
    public SettingActivity_ViewBinding(final SettingActivity settingActivity, View view) {
        this.target = settingActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        settingActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        settingActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296649, "field 'exitLogin' and method 'onViewClicked'");
        settingActivity.exitLogin = (Button) Utils.castView(findRequiredView2, 2131296649, "field 'exitLogin'", Button.class);
        this.view2131296649 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        settingActivity.macAddress = (TextView) Utils.findRequiredViewAsType(view, 2131296984, "field 'macAddress'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297363, "field 'shadowLinearLayout' and method 'onViewClicked'");
        settingActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.castView(findRequiredView3, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        this.view2131297363 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297693, "field 'wxVersionLayout' and method 'onViewClicked'");
        settingActivity.wxVersionLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297693, "field 'wxVersionLayout'", LinearLayout.class);
        this.view2131297693 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297623, "field 'userUsageProtocolLayout' and method 'onViewClicked'");
        settingActivity.userUsageProtocolLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297623, "field 'userUsageProtocolLayout'", LinearLayout.class);
        this.view2131297623 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296601, "field 'disclaimerLayout' and method 'onViewClicked'");
        settingActivity.disclaimerLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131296601, "field 'disclaimerLayout'", LinearLayout.class);
        this.view2131296601 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131296561, "field 'currentDeviceLayout' and method 'onViewClicked'");
        settingActivity.currentDeviceLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131296561, "field 'currentDeviceLayout'", LinearLayout.class);
        this.view2131296561 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131296666, "field 'fillInInvitationRelationshipLayout' and method 'onViewClicked'");
        settingActivity.fillInInvitationRelationshipLayout = (LinearLayout) Utils.castView(findRequiredView8, 2131296666, "field 'fillInInvitationRelationshipLayout'", LinearLayout.class);
        this.view2131296666 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131297350, "field 'setLoginPasswordLayout' and method 'onViewClicked'");
        settingActivity.setLoginPasswordLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131297350, "field 'setLoginPasswordLayout'", LinearLayout.class);
        this.view2131297350 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
        settingActivity.bubbleSeekBar = (BubbleSeekBar) Utils.findRequiredViewAsType(view, 2131296452, "field 'bubbleSeekBar'", BubbleSeekBar.class);
        settingActivity.switchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297446, "field 'switchBtn'", Switch.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131297622, "field 'userPrivacyPolicy' and method 'onViewClicked'");
        settingActivity.userPrivacyPolicy = (LinearLayout) Utils.castView(findRequiredView10, 2131297622, "field 'userPrivacyPolicy'", LinearLayout.class);
        this.view2131297622 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SettingActivity settingActivity = this.target;
        if (settingActivity != null) {
            this.target = null;
            settingActivity.navBack = null;
            settingActivity.navTitle = null;
            settingActivity.exitLogin = null;
            settingActivity.macAddress = null;
            settingActivity.shadowLinearLayout = null;
            settingActivity.wxVersionLayout = null;
            settingActivity.userUsageProtocolLayout = null;
            settingActivity.disclaimerLayout = null;
            settingActivity.currentDeviceLayout = null;
            settingActivity.fillInInvitationRelationshipLayout = null;
            settingActivity.setLoginPasswordLayout = null;
            settingActivity.bubbleSeekBar = null;
            settingActivity.switchBtn = null;
            settingActivity.userPrivacyPolicy = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296649.setOnClickListener((View.OnClickListener) null);
            this.view2131296649 = null;
            this.view2131297363.setOnClickListener((View.OnClickListener) null);
            this.view2131297363 = null;
            this.view2131297693.setOnClickListener((View.OnClickListener) null);
            this.view2131297693 = null;
            this.view2131297623.setOnClickListener((View.OnClickListener) null);
            this.view2131297623 = null;
            this.view2131296601.setOnClickListener((View.OnClickListener) null);
            this.view2131296601 = null;
            this.view2131296561.setOnClickListener((View.OnClickListener) null);
            this.view2131296561 = null;
            this.view2131296666.setOnClickListener((View.OnClickListener) null);
            this.view2131296666 = null;
            this.view2131297350.setOnClickListener((View.OnClickListener) null);
            this.view2131297350 = null;
            this.view2131297622.setOnClickListener((View.OnClickListener) null);
            this.view2131297622 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
