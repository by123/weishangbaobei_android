package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.NumSettingOnlyLayout;

public class WechatSportsActivity_ViewBinding implements Unbinder {
    private WechatSportsActivity target;
    private View view2131296339;
    private View view2131297049;
    private View view2131297052;
    private View view2131297064;
    private View view2131297154;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public WechatSportsActivity_ViewBinding(WechatSportsActivity wechatSportsActivity) {
        this(wechatSportsActivity, wechatSportsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v23, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v34, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v42, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v50, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v55, types: [android.view.View$OnClickListener, com.wx.assistants.activity.WechatSportsActivity_ViewBinding$7] */
    @UiThread
    public WechatSportsActivity_ViewBinding(final WechatSportsActivity wechatSportsActivity, View view) {
        this.target = wechatSportsActivity;
        wechatSportsActivity.rdoBtnAll = (RadioButton) Utils.findRequiredViewAsType(view, 2131297209, "field 'rdoBtnAll'", RadioButton.class);
        wechatSportsActivity.rdoBtnYes = (RadioButton) Utils.findRequiredViewAsType(view, 2131297216, "field 'rdoBtnYes'", RadioButton.class);
        wechatSportsActivity.rdoBtnNo = (RadioButton) Utils.findRequiredViewAsType(view, 2131297215, "field 'rdoBtnNo'", RadioButton.class);
        View findRequiredView = Utils.findRequiredView(view, 2131296339, "field 'allTagLayout' and method 'onViewClicked'");
        wechatSportsActivity.allTagLayout = (LinearLayout) Utils.castView(findRequiredView, 2131296339, "field 'allTagLayout'", LinearLayout.class);
        this.view2131296339 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297064, "field 'noPraiseTagLayout' and method 'onViewClicked'");
        wechatSportsActivity.noPraiseTagLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297064, "field 'noPraiseTagLayout'", LinearLayout.class);
        this.view2131297064 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297154, "field 'praiseTagLayout' and method 'onViewClicked'");
        wechatSportsActivity.praiseTagLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297154, "field 'praiseTagLayout'", LinearLayout.class);
        this.view2131297154 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        wechatSportsActivity.noFriendsTags = (TextView) Utils.findRequiredViewAsType(view, 2131297063, "field 'noFriendsTags'", TextView.class);
        wechatSportsActivity.friendsTags = (TextView) Utils.findRequiredViewAsType(view, 2131296722, "field 'friendsTags'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        wechatSportsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView4, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        wechatSportsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        wechatSportsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        wechatSportsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        wechatSportsActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        wechatSportsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                wechatSportsActivity.onViewClicked(view);
            }
        });
        wechatSportsActivity.numSettingOnlyLayout = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingOnlyLayout.class);
    }

    @CallSuper
    public void unbind() {
        WechatSportsActivity wechatSportsActivity = this.target;
        if (wechatSportsActivity != null) {
            this.target = null;
            wechatSportsActivity.rdoBtnAll = null;
            wechatSportsActivity.rdoBtnYes = null;
            wechatSportsActivity.rdoBtnNo = null;
            wechatSportsActivity.allTagLayout = null;
            wechatSportsActivity.noPraiseTagLayout = null;
            wechatSportsActivity.praiseTagLayout = null;
            wechatSportsActivity.noFriendsTags = null;
            wechatSportsActivity.friendsTags = null;
            wechatSportsActivity.navBack = null;
            wechatSportsActivity.navTitle = null;
            wechatSportsActivity.navRightLayout = null;
            wechatSportsActivity.navRightImg = null;
            wechatSportsActivity.startWx = null;
            wechatSportsActivity.videoIntroduceLayout = null;
            wechatSportsActivity.numSettingOnlyLayout = null;
            this.view2131296339.setOnClickListener((View.OnClickListener) null);
            this.view2131296339 = null;
            this.view2131297064.setOnClickListener((View.OnClickListener) null);
            this.view2131297064 = null;
            this.view2131297154.setOnClickListener((View.OnClickListener) null);
            this.view2131297154 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
