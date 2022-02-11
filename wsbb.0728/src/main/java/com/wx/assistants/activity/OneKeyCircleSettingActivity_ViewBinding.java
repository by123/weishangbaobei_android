package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyCircleSettingActivity_ViewBinding implements Unbinder {
    private OneKeyCircleSettingActivity target;
    private View view2131296338;
    private View view2131296723;
    private View view2131296724;
    private View view2131297049;
    private View view2131297115;
    private View view2131297326;
    private View view2131297370;
    private View view2131297425;

    @UiThread
    public OneKeyCircleSettingActivity_ViewBinding(OneKeyCircleSettingActivity oneKeyCircleSettingActivity) {
        this(oneKeyCircleSettingActivity, oneKeyCircleSettingActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v32, types: [com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v46, types: [com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v60, types: [com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v74, types: [com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v82, types: [com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v90, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyCircleSettingActivity_ViewBinding$8] */
    @UiThread
    public OneKeyCircleSettingActivity_ViewBinding(final OneKeyCircleSettingActivity oneKeyCircleSettingActivity, View view) {
        this.target = oneKeyCircleSettingActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        oneKeyCircleSettingActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        oneKeyCircleSettingActivity.imgAll = (ImageView) Utils.findRequiredViewAsType(view, 2131296775, "field 'imgAll'", ImageView.class);
        oneKeyCircleSettingActivity.sendAllTV = (TextView) Utils.findRequiredViewAsType(view, 2131297329, "field 'sendAllTV'", TextView.class);
        oneKeyCircleSettingActivity.sendAllSmallTV = (TextView) Utils.findRequiredViewAsType(view, 2131297328, "field 'sendAllSmallTV'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131296338, "field 'allLayout' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.allLayout = (RelativeLayout) Utils.castView(findRequiredView3, 2131296338, "field 'allLayout'", RelativeLayout.class);
        this.view2131296338 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.imgSelf = (ImageView) Utils.findRequiredViewAsType(view, 2131296779, "field 'imgSelf'", ImageView.class);
        oneKeyCircleSettingActivity.sendShelfTV = (TextView) Utils.findRequiredViewAsType(view, 2131297340, "field 'sendShelfTV'", TextView.class);
        oneKeyCircleSettingActivity.sendSelfSmallTV = (TextView) Utils.findRequiredViewAsType(view, 2131297339, "field 'sendSelfSmallTV'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297326, "field 'selfLayout' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.selfLayout = (RelativeLayout) Utils.castView(findRequiredView4, 2131297326, "field 'selfLayout'", RelativeLayout.class);
        this.view2131297326 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.imgPart = (ImageView) Utils.findRequiredViewAsType(view, 2131296778, "field 'imgPart'", ImageView.class);
        oneKeyCircleSettingActivity.sendPartTV = (TextView) Utils.findRequiredViewAsType(view, 2131297338, "field 'sendPartTV'", TextView.class);
        oneKeyCircleSettingActivity.sendPartSmallTV = (TextView) Utils.findRequiredViewAsType(view, 2131297337, "field 'sendPartSmallTV'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297115, "field 'partLayout' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.partLayout = (RelativeLayout) Utils.castView(findRequiredView5, 2131297115, "field 'partLayout'", RelativeLayout.class);
        this.view2131297115 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.imgShield = (ImageView) Utils.findRequiredViewAsType(view, 2131296780, "field 'imgShield'", ImageView.class);
        oneKeyCircleSettingActivity.sendShieldTV = (TextView) Utils.findRequiredViewAsType(view, 2131297342, "field 'sendShieldTV'", TextView.class);
        oneKeyCircleSettingActivity.sendShieldSmallTV = (TextView) Utils.findRequiredViewAsType(view, 2131297341, "field 'sendShieldSmallTV'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297370, "field 'shieldLayout' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.shieldLayout = (RelativeLayout) Utils.castView(findRequiredView6, 2131297370, "field 'shieldLayout'", RelativeLayout.class);
        this.view2131297370 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.selectTagTv = (TextView) Utils.findRequiredViewAsType(view, 2131297318, "field 'selectTagTv'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296724, "field 'fromTagSelect' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.fromTagSelect = (LinearLayout) Utils.castView(findRequiredView7, 2131296724, "field 'fromTagSelect'", LinearLayout.class);
        this.view2131296724 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.selectGroupTv = (TextView) Utils.findRequiredViewAsType(view, 2131297309, "field 'selectGroupTv'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131296723, "field 'fromGroupSelect' and method 'onViewClicked'");
        oneKeyCircleSettingActivity.fromGroupSelect = (LinearLayout) Utils.castView(findRequiredView8, 2131296723, "field 'fromGroupSelect'", LinearLayout.class);
        this.view2131296723 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyCircleSettingActivity.onViewClicked(view);
            }
        });
        oneKeyCircleSettingActivity.bottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296399, "field 'bottomLayout'", LinearLayout.class);
        oneKeyCircleSettingActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyCircleSettingActivity oneKeyCircleSettingActivity = this.target;
        if (oneKeyCircleSettingActivity != null) {
            this.target = null;
            oneKeyCircleSettingActivity.navBack = null;
            oneKeyCircleSettingActivity.navTitle = null;
            oneKeyCircleSettingActivity.startWx = null;
            oneKeyCircleSettingActivity.navRightText = null;
            oneKeyCircleSettingActivity.navRightLayout = null;
            oneKeyCircleSettingActivity.imgAll = null;
            oneKeyCircleSettingActivity.sendAllTV = null;
            oneKeyCircleSettingActivity.sendAllSmallTV = null;
            oneKeyCircleSettingActivity.allLayout = null;
            oneKeyCircleSettingActivity.imgSelf = null;
            oneKeyCircleSettingActivity.sendShelfTV = null;
            oneKeyCircleSettingActivity.sendSelfSmallTV = null;
            oneKeyCircleSettingActivity.selfLayout = null;
            oneKeyCircleSettingActivity.imgPart = null;
            oneKeyCircleSettingActivity.sendPartTV = null;
            oneKeyCircleSettingActivity.sendPartSmallTV = null;
            oneKeyCircleSettingActivity.partLayout = null;
            oneKeyCircleSettingActivity.imgShield = null;
            oneKeyCircleSettingActivity.sendShieldTV = null;
            oneKeyCircleSettingActivity.sendShieldSmallTV = null;
            oneKeyCircleSettingActivity.shieldLayout = null;
            oneKeyCircleSettingActivity.selectTagTv = null;
            oneKeyCircleSettingActivity.fromTagSelect = null;
            oneKeyCircleSettingActivity.selectGroupTv = null;
            oneKeyCircleSettingActivity.fromGroupSelect = null;
            oneKeyCircleSettingActivity.bottomLayout = null;
            oneKeyCircleSettingActivity.shadowLinearLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131296338.setOnClickListener((View.OnClickListener) null);
            this.view2131296338 = null;
            this.view2131297326.setOnClickListener((View.OnClickListener) null);
            this.view2131297326 = null;
            this.view2131297115.setOnClickListener((View.OnClickListener) null);
            this.view2131297115 = null;
            this.view2131297370.setOnClickListener((View.OnClickListener) null);
            this.view2131297370 = null;
            this.view2131296724.setOnClickListener((View.OnClickListener) null);
            this.view2131296724 = null;
            this.view2131296723.setOnClickListener((View.OnClickListener) null);
            this.view2131296723 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
