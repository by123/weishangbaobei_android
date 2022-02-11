package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class InComeFragment2_ViewBinding implements Unbinder {
    private InComeFragment2 target;
    private View view2131296416;
    private View view2131296549;
    private View view2131296816;
    private View view2131296817;
    private View view2131297007;
    private View view2131297046;
    private View view2131297052;
    private View view2131297618;

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v44, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment2_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v58, types: [com.wx.assistants.fragment.InComeFragment2_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v69, types: [com.wx.assistants.fragment.InComeFragment2_ViewBinding$8, android.view.View$OnClickListener] */
    @UiThread
    public InComeFragment2_ViewBinding(final InComeFragment2 inComeFragment2, View view) {
        this.target = inComeFragment2;
        View findRequiredView = Utils.findRequiredView(view, 2131296416, "field 'btGetMoney' and method 'onViewClicked'");
        inComeFragment2.btGetMoney = (LinearLayout) Utils.castView(findRequiredView, 2131296416, "field 'btGetMoney'", LinearLayout.class);
        this.view2131296416 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297618, "field 'upgradeMember' and method 'onViewClicked'");
        inComeFragment2.upgradeMember = (LinearLayout) Utils.castView(findRequiredView2, 2131297618, "field 'upgradeMember'", LinearLayout.class);
        this.view2131297618 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297046, "field 'myQRCode' and method 'onViewClicked'");
        inComeFragment2.myQRCode = (LinearLayout) Utils.castView(findRequiredView3, 2131297046, "field 'myQRCode'", LinearLayout.class);
        this.view2131297046 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297007, "field 'memberPrivilege' and method 'onViewClicked'");
        inComeFragment2.memberPrivilege = (LinearLayout) Utils.castView(findRequiredView4, 2131297007, "field 'memberPrivilege'", LinearLayout.class);
        this.view2131297007 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        inComeFragment2.inviteUrl = (TextView) Utils.findRequiredViewAsType(view, 2131296827, "field 'inviteUrl'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296549, "field 'copyBtn' and method 'onViewClicked'");
        inComeFragment2.copyBtn = (Button) Utils.castView(findRequiredView5, 2131296549, "field 'copyBtn'", Button.class);
        this.view2131296549 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        inComeFragment2.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        inComeFragment2.balance = (TextView) Utils.findRequiredViewAsType(view, 2131296372, "field 'balance'", TextView.class);
        inComeFragment2.invitePeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131296820, "field 'invitePeopleNum'", TextView.class);
        inComeFragment2.inviteReward = (TextView) Utils.findRequiredViewAsType(view, 2131296822, "field 'inviteReward'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296816, "field 'invitationRecord' and method 'onViewClicked'");
        inComeFragment2.invitationRecord = (LinearLayout) Utils.castView(findRequiredView6, 2131296816, "field 'invitationRecord'", LinearLayout.class);
        this.view2131296816 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        inComeFragment2.levelNameText = (TextView) Utils.findRequiredViewAsType(view, 2131296925, "field 'levelNameText'", TextView.class);
        inComeFragment2.invitePeopleNum2 = (TextView) Utils.findRequiredViewAsType(view, 2131296821, "field 'invitePeopleNum2'", TextView.class);
        inComeFragment2.navTitle = (Button) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", Button.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        inComeFragment2.navRightLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        inComeFragment2.payPeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131297119, "field 'payPeopleNum'", TextView.class);
        inComeFragment2.payPeopleNum2 = (TextView) Utils.findRequiredViewAsType(view, 2131297120, "field 'payPeopleNum2'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131296817, "field 'inviteAward' and method 'onViewClicked'");
        inComeFragment2.inviteAward = (LinearLayout) Utils.castView(findRequiredView8, 2131296817, "field 'inviteAward'", LinearLayout.class);
        this.view2131296817 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment2.onViewClicked(view);
            }
        });
        inComeFragment2.yesterdayProfits = (TextView) Utils.findRequiredViewAsType(view, 2131297697, "field 'yesterdayProfits'", TextView.class);
        inComeFragment2.withdrawTotal = (TextView) Utils.findRequiredViewAsType(view, 2131297679, "field 'withdrawTotal'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InComeFragment2 inComeFragment2 = this.target;
        if (inComeFragment2 != null) {
            this.target = null;
            inComeFragment2.btGetMoney = null;
            inComeFragment2.upgradeMember = null;
            inComeFragment2.myQRCode = null;
            inComeFragment2.memberPrivilege = null;
            inComeFragment2.inviteUrl = null;
            inComeFragment2.copyBtn = null;
            inComeFragment2.refreshLayout = null;
            inComeFragment2.balance = null;
            inComeFragment2.invitePeopleNum = null;
            inComeFragment2.inviteReward = null;
            inComeFragment2.invitationRecord = null;
            inComeFragment2.levelNameText = null;
            inComeFragment2.invitePeopleNum2 = null;
            inComeFragment2.navTitle = null;
            inComeFragment2.navRightLayout = null;
            inComeFragment2.payPeopleNum = null;
            inComeFragment2.payPeopleNum2 = null;
            inComeFragment2.inviteAward = null;
            inComeFragment2.yesterdayProfits = null;
            inComeFragment2.withdrawTotal = null;
            this.view2131296416.setOnClickListener((View.OnClickListener) null);
            this.view2131296416 = null;
            this.view2131297618.setOnClickListener((View.OnClickListener) null);
            this.view2131297618 = null;
            this.view2131297046.setOnClickListener((View.OnClickListener) null);
            this.view2131297046 = null;
            this.view2131297007.setOnClickListener((View.OnClickListener) null);
            this.view2131297007 = null;
            this.view2131296549.setOnClickListener((View.OnClickListener) null);
            this.view2131296549 = null;
            this.view2131296816.setOnClickListener((View.OnClickListener) null);
            this.view2131296816 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296817.setOnClickListener((View.OnClickListener) null);
            this.view2131296817 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
