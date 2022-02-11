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

public class MemberCenterListActivity_ViewBinding implements Unbinder {
    private MemberCenterListActivity target;
    private View view2131296814;
    private View view2131297049;
    private View view2131297091;
    private View view2131297349;
    private View view2131297353;
    private View view2131297354;
    private View view2131297355;
    private View view2131297356;
    private View view2131297357;
    private View view2131297358;
    private View view2131297414;

    @UiThread
    public MemberCenterListActivity_ViewBinding(MemberCenterListActivity memberCenterListActivity) {
        this(memberCenterListActivity, memberCenterListActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v35, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v40, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v54, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v62, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r0v79, types: [com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$10, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v84, types: [com.wx.assistants.activity.MemberCenterListActivity_ViewBinding$11, android.view.View$OnClickListener] */
    @UiThread
    public MemberCenterListActivity_ViewBinding(final MemberCenterListActivity memberCenterListActivity, View view) {
        this.target = memberCenterListActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297353, "field 'setUserHead' and method 'onViewClicked'");
        memberCenterListActivity.setUserHead = (ImageView) Utils.castView(findRequiredView, 2131297353, "field 'setUserHead'", ImageView.class);
        this.view2131297353 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297354, "field 'setUserName' and method 'onViewClicked'");
        memberCenterListActivity.setUserName = (TextView) Utils.castView(findRequiredView2, 2131297354, "field 'setUserName'", TextView.class);
        this.view2131297354 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297349, "field 'setExpireTime' and method 'onViewClicked'");
        memberCenterListActivity.setExpireTime = (TextView) Utils.castView(findRequiredView3, 2131297349, "field 'setExpireTime'", TextView.class);
        this.view2131297349 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        memberCenterListActivity.anchor1 = (ImageView) Utils.findRequiredViewAsType(view, 2131296341, "field 'anchor1'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297356, "field 'setWxRadioButton' and method 'onViewClicked'");
        memberCenterListActivity.setWxRadioButton = (RadioButton) Utils.castView(findRequiredView4, 2131297356, "field 'setWxRadioButton'", RadioButton.class);
        this.view2131297356 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297355, "field 'setWxPayment' and method 'onViewClicked'");
        memberCenterListActivity.setWxPayment = (RelativeLayout) Utils.castView(findRequiredView5, 2131297355, "field 'setWxPayment'", RelativeLayout.class);
        this.view2131297355 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        memberCenterListActivity.anchor2 = (ImageView) Utils.findRequiredViewAsType(view, 2131296348, "field 'anchor2'", ImageView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297358, "field 'setZfbRadioButton' and method 'onViewClicked'");
        memberCenterListActivity.setZfbRadioButton = (CheckBox) Utils.castView(findRequiredView6, 2131297358, "field 'setZfbRadioButton'", CheckBox.class);
        this.view2131297358 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297357, "field 'setZfbPayment' and method 'onViewClicked'");
        memberCenterListActivity.setZfbPayment = (RelativeLayout) Utils.castView(findRequiredView7, 2131297357, "field 'setZfbPayment'", RelativeLayout.class);
        this.view2131297357 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        memberCenterListActivity.setAmountPrice = (TextView) Utils.findRequiredViewAsType(view, 2131297348, "field 'setAmountPrice'", TextView.class);
        memberCenterListActivity.rv = (RecyclerView) Utils.findRequiredViewAsType(view, 2131297265, "field 'rv'", RecyclerView.class);
        memberCenterListActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297091, "field 'operationBtn' and method 'onViewClicked'");
        memberCenterListActivity.operationBtn = (Button) Utils.castView(findRequiredView8, 2131297091, "field 'operationBtn'", Button.class);
        this.view2131297091 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        memberCenterListActivity.memberLever = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'memberLever'", TextView.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        memberCenterListActivity.navBack = (LinearLayout) Utils.castView(findRequiredView9, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        memberCenterListActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        memberCenterListActivity.singleAdvertLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297384, "field 'singleAdvertLayout'", LinearLayout.class);
        memberCenterListActivity.singleAdvertImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297383, "field 'singleAdvertImg'", ImageView.class);
        memberCenterListActivity.favourable = (TextView) Utils.findRequiredViewAsType(view, 2131296659, "field 'favourable'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131296814, "field 'introduceLayout' and method 'onViewClicked'");
        memberCenterListActivity.introduceLayout = (LinearLayout) Utils.castView(findRequiredView10, 2131296814, "field 'introduceLayout'", LinearLayout.class);
        this.view2131296814 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, 2131297414, "field 'startPayBtn' and method 'onViewClicked'");
        memberCenterListActivity.startPayBtn = (Button) Utils.castView(findRequiredView11, 2131297414, "field 'startPayBtn'", Button.class);
        this.view2131297414 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                memberCenterListActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MemberCenterListActivity memberCenterListActivity = this.target;
        if (memberCenterListActivity != null) {
            this.target = null;
            memberCenterListActivity.setUserHead = null;
            memberCenterListActivity.setUserName = null;
            memberCenterListActivity.setExpireTime = null;
            memberCenterListActivity.anchor1 = null;
            memberCenterListActivity.setWxRadioButton = null;
            memberCenterListActivity.setWxPayment = null;
            memberCenterListActivity.anchor2 = null;
            memberCenterListActivity.setZfbRadioButton = null;
            memberCenterListActivity.setZfbPayment = null;
            memberCenterListActivity.setAmountPrice = null;
            memberCenterListActivity.rv = null;
            memberCenterListActivity.refreshLayout = null;
            memberCenterListActivity.operationBtn = null;
            memberCenterListActivity.memberLever = null;
            memberCenterListActivity.navBack = null;
            memberCenterListActivity.navTitle = null;
            memberCenterListActivity.singleAdvertLayout = null;
            memberCenterListActivity.singleAdvertImg = null;
            memberCenterListActivity.favourable = null;
            memberCenterListActivity.introduceLayout = null;
            memberCenterListActivity.startPayBtn = null;
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
            this.view2131297091.setOnClickListener((View.OnClickListener) null);
            this.view2131297091 = null;
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
