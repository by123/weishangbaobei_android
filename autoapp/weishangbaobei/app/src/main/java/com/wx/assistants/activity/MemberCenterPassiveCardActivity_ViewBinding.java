package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MemberCenterPassiveCardActivity_ViewBinding implements Unbinder {
    private MemberCenterPassiveCardActivity target;
    private View view2131296814;
    private View view2131297049;
    private View view2131297355;
    private View view2131297356;
    private View view2131297357;
    private View view2131297358;
    private View view2131297414;

    @UiThread
    public MemberCenterPassiveCardActivity_ViewBinding(MemberCenterPassiveCardActivity memberCenterPassiveCardActivity) {
        this(memberCenterPassiveCardActivity, memberCenterPassiveCardActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v11, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v22, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v28, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v31, types: [com.wx.assistants.activity.MemberCenterPassiveCardActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public MemberCenterPassiveCardActivity_ViewBinding(final MemberCenterPassiveCardActivity memberCenterPassiveCardActivity, View view) {
        this.target = memberCenterPassiveCardActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297356, "field 'setWxRadioButton' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.setWxRadioButton = (RadioButton) Utils.castView(findRequiredView, 2131297356, "field 'setWxRadioButton'", RadioButton.class);
        this.view2131297356 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297355, "field 'setWxPayment' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.setWxPayment = (RelativeLayout) Utils.castView(findRequiredView2, 2131297355, "field 'setWxPayment'", RelativeLayout.class);
        this.view2131297355 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297358, "field 'setZfbRadioButton' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.setZfbRadioButton = (CheckBox) Utils.castView(findRequiredView3, 2131297358, "field 'setZfbRadioButton'", CheckBox.class);
        this.view2131297358 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297357, "field 'setZfbPayment' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.setZfbPayment = (RelativeLayout) Utils.castView(findRequiredView4, 2131297357, "field 'setZfbPayment'", RelativeLayout.class);
        this.view2131297357 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        memberCenterPassiveCardActivity.setAmountPrice = (TextView) Utils.findRequiredViewAsType(view, 2131297348, "field 'setAmountPrice'", TextView.class);
        memberCenterPassiveCardActivity.rv = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297265, "field 'rv'", RecyclerView.class);
        memberCenterPassiveCardActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        memberCenterPassiveCardActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        memberCenterPassiveCardActivity.favourable = (TextView) Utils.findRequiredViewAsType(view, 2131296659, "field 'favourable'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296814, "field 'introduceLayout' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.introduceLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131296814, "field 'introduceLayout'", LinearLayout.class);
        this.view2131296814 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297414, "field 'startPayBtn' and method 'onViewClicked'");
        memberCenterPassiveCardActivity.startPayBtn = (Button) Utils.castView(findRequiredView7, 2131297414, "field 'startPayBtn'", Button.class);
        this.view2131297414 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterPassiveCardActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MemberCenterPassiveCardActivity memberCenterPassiveCardActivity = this.target;
        if (memberCenterPassiveCardActivity != null) {
            this.target = null;
            memberCenterPassiveCardActivity.setWxRadioButton = null;
            memberCenterPassiveCardActivity.setWxPayment = null;
            memberCenterPassiveCardActivity.setZfbRadioButton = null;
            memberCenterPassiveCardActivity.setZfbPayment = null;
            memberCenterPassiveCardActivity.setAmountPrice = null;
            memberCenterPassiveCardActivity.rv = null;
            memberCenterPassiveCardActivity.refreshLayout = null;
            memberCenterPassiveCardActivity.navBack = null;
            memberCenterPassiveCardActivity.navTitle = null;
            memberCenterPassiveCardActivity.favourable = null;
            memberCenterPassiveCardActivity.introduceLayout = null;
            memberCenterPassiveCardActivity.startPayBtn = null;
            this.view2131297356.setOnClickListener((View.OnClickListener) null);
            this.view2131297356 = null;
            this.view2131297355.setOnClickListener((View.OnClickListener) null);
            this.view2131297355 = null;
            this.view2131297358.setOnClickListener((View.OnClickListener) null);
            this.view2131297358 = null;
            this.view2131297357.setOnClickListener((View.OnClickListener) null);
            this.view2131297357 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296814.setOnClickListener((View.OnClickListener) null);
            this.view2131296814 = null;
            this.view2131297414.setOnClickListener((View.OnClickListener) null);
            this.view2131297414 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
