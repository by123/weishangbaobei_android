package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MemberCenterActivity_ViewBinding implements Unbinder {
    private MemberCenterActivity target;
    private View view2131297049;
    private View view2131297091;
    private View view2131297347;
    private View view2131297349;
    private View view2131297352;
    private View view2131297353;
    private View view2131297354;
    private View view2131297355;
    private View view2131297356;
    private View view2131297357;
    private View view2131297358;

    @UiThread
    public MemberCenterActivity_ViewBinding(MemberCenterActivity memberCenterActivity) {
        this(memberCenterActivity, memberCenterActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v35, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v40, types: [com.wx.assistants.activity.MemberCenterActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v48, types: [com.wx.assistants.activity.MemberCenterActivity_ViewBinding$8, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v56, types: [com.wx.assistants.activity.MemberCenterActivity_ViewBinding$9, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v67, types: [com.wx.assistants.activity.MemberCenterActivity_ViewBinding$10, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v78, types: [com.wx.assistants.activity.MemberCenterActivity_ViewBinding$11, android.view.View$OnClickListener] */
    @UiThread
    public MemberCenterActivity_ViewBinding(final MemberCenterActivity memberCenterActivity, View view) {
        this.target = memberCenterActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297353, "field 'setUserHead' and method 'onViewClicked'");
        memberCenterActivity.setUserHead = (ImageView) Utils.castView(findRequiredView, 2131297353, "field 'setUserHead'", ImageView.class);
        this.view2131297353 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297354, "field 'setUserName' and method 'onViewClicked'");
        memberCenterActivity.setUserName = (TextView) Utils.castView(findRequiredView2, 2131297354, "field 'setUserName'", TextView.class);
        this.view2131297354 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297349, "field 'setExpireTime' and method 'onViewClicked'");
        memberCenterActivity.setExpireTime = (TextView) Utils.castView(findRequiredView3, 2131297349, "field 'setExpireTime'", TextView.class);
        this.view2131297349 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.anchor1 = (ImageView) Utils.findRequiredViewAsType(view, 2131296341, "field 'anchor1'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297356, "field 'setWxRadioButton' and method 'onViewClicked'");
        memberCenterActivity.setWxRadioButton = (RadioButton) Utils.castView(findRequiredView4, 2131297356, "field 'setWxRadioButton'", RadioButton.class);
        this.view2131297356 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297355, "field 'setWxPayment' and method 'onViewClicked'");
        memberCenterActivity.setWxPayment = (RelativeLayout) Utils.castView(findRequiredView5, 2131297355, "field 'setWxPayment'", RelativeLayout.class);
        this.view2131297355 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.anchor2 = (ImageView) Utils.findRequiredViewAsType(view, 2131296348, "field 'anchor2'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297358, "field 'setZfbRadioButton' and method 'onViewClicked'");
        memberCenterActivity.setZfbRadioButton = (CheckBox) Utils.castView(findRequiredView6, 2131297358, "field 'setZfbRadioButton'", CheckBox.class);
        this.view2131297358 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297357, "field 'setZfbPayment' and method 'onViewClicked'");
        memberCenterActivity.setZfbPayment = (RelativeLayout) Utils.castView(findRequiredView7, 2131297357, "field 'setZfbPayment'", RelativeLayout.class);
        this.view2131297357 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.anchor3 = (ImageView) Utils.findRequiredViewAsType(view, 2131296349, "field 'anchor3'", ImageView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297347, "field 'setAgreement' and method 'onViewClicked'");
        memberCenterActivity.setAgreement = (LinearLayout) Utils.castView(findRequiredView8, 2131297347, "field 'setAgreement'", LinearLayout.class);
        this.view2131297347 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.setAmountPrice = (TextView) Utils.findRequiredViewAsType(view, 2131297348, "field 'setAmountPrice'", TextView.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131297352, "field 'setPayment' and method 'onViewClicked'");
        memberCenterActivity.setPayment = (TextView) Utils.castView(findRequiredView9, 2131297352, "field 'setPayment'", TextView.class);
        this.view2131297352 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.rv = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297265, "field 'rv'", RecyclerView.class);
        memberCenterActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        memberCenterActivity.operationBtn = (Button) Utils.castView(findRequiredView10, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.memberLever = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'memberLever'", TextView.class);
        memberCenterActivity.diamondImage = (ImageView) Utils.findRequiredViewAsType(view, 2131296597, "field 'diamondImage'", ImageView.class);
        View findRequiredView11 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        memberCenterActivity.navBack = (LinearLayout) Utils.castView(findRequiredView11, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterActivity.onViewClicked(view);
            }
        });
        memberCenterActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        memberCenterActivity.singleAdvertLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297384, "field 'singleAdvertLayout'", LinearLayout.class);
        memberCenterActivity.singleAdvertImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297383, "field 'singleAdvertImg'", ImageView.class);
        memberCenterActivity.favourable = (TextView) Utils.findRequiredViewAsType(view, 2131296659, "field 'favourable'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        MemberCenterActivity memberCenterActivity = this.target;
        if (memberCenterActivity != null) {
            this.target = null;
            memberCenterActivity.setUserHead = null;
            memberCenterActivity.setUserName = null;
            memberCenterActivity.setExpireTime = null;
            memberCenterActivity.anchor1 = null;
            memberCenterActivity.setWxRadioButton = null;
            memberCenterActivity.setWxPayment = null;
            memberCenterActivity.anchor2 = null;
            memberCenterActivity.setZfbRadioButton = null;
            memberCenterActivity.setZfbPayment = null;
            memberCenterActivity.anchor3 = null;
            memberCenterActivity.setAgreement = null;
            memberCenterActivity.setAmountPrice = null;
            memberCenterActivity.setPayment = null;
            memberCenterActivity.rv = null;
            memberCenterActivity.refreshLayout = null;
            memberCenterActivity.operationBtn = null;
            memberCenterActivity.memberLever = null;
            memberCenterActivity.diamondImage = null;
            memberCenterActivity.navBack = null;
            memberCenterActivity.navTitle = null;
            memberCenterActivity.singleAdvertLayout = null;
            memberCenterActivity.singleAdvertImg = null;
            memberCenterActivity.favourable = null;
            this.view2131297353.setOnClickListener((View.OnClickListener) null);
            this.view2131297353 = null;
            this.view2131297354.setOnClickListener((View.OnClickListener) null);
            this.view2131297354 = null;
            this.view2131297349.setOnClickListener((View.OnClickListener) null);
            this.view2131297349 = null;
            this.view2131297356.setOnClickListener((View.OnClickListener) null);
            this.view2131297356 = null;
            this.view2131297355.setOnClickListener((View.OnClickListener) null);
            this.view2131297355 = null;
            this.view2131297358.setOnClickListener((View.OnClickListener) null);
            this.view2131297358 = null;
            this.view2131297357.setOnClickListener((View.OnClickListener) null);
            this.view2131297357 = null;
            this.view2131297347.setOnClickListener((View.OnClickListener) null);
            this.view2131297347 = null;
            this.view2131297352.setOnClickListener((View.OnClickListener) null);
            this.view2131297352 = null;
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
