package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class InComeFragment_ViewBinding implements Unbinder {
    private InComeFragment target;
    private View view2131296416;
    private View view2131296549;
    private View view2131296816;
    private View view2131297000;
    private View view2131297007;
    private View view2131297046;
    private View view2131297618;

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v15, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v21, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.InComeFragment_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r1v25, types: [com.wx.assistants.fragment.InComeFragment_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v34, types: [com.wx.assistants.fragment.InComeFragment_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public InComeFragment_ViewBinding(final InComeFragment inComeFragment, View view) {
        this.target = inComeFragment;
        View findRequiredView = Utils.findRequiredView(view, 2131296416, "field 'btGetMoney' and method 'onViewClicked'");
        inComeFragment.btGetMoney = (LinearLayout) Utils.castView(findRequiredView, 2131296416, "field 'btGetMoney'", LinearLayout.class);
        this.view2131296416 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297618, "field 'upgradeMember' and method 'onViewClicked'");
        inComeFragment.upgradeMember = (LinearLayout) Utils.castView(findRequiredView2, 2131297618, "field 'upgradeMember'", LinearLayout.class);
        this.view2131297618 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297046, "field 'myQRCode' and method 'onViewClicked'");
        inComeFragment.myQRCode = (LinearLayout) Utils.castView(findRequiredView3, 2131297046, "field 'myQRCode'", LinearLayout.class);
        this.view2131297046 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297007, "field 'memberPrivilege' and method 'onViewClicked'");
        inComeFragment.memberPrivilege = (LinearLayout) Utils.castView(findRequiredView4, 2131297007, "field 'memberPrivilege'", LinearLayout.class);
        this.view2131297007 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        inComeFragment.userPhone = (TextView) Utils.findRequiredViewAsType(view, 2131297621, "field 'userPhone'", TextView.class);
        inComeFragment.inviteUrl = (TextView) Utils.findRequiredViewAsType(view, 2131296827, "field 'inviteUrl'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296549, "field 'copyBtn' and method 'onViewClicked'");
        inComeFragment.copyBtn = (Button) Utils.castView(findRequiredView5, 2131296549, "field 'copyBtn'", Button.class);
        this.view2131296549 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131297000, "field 'materialCircleLayout' and method 'onViewClicked'");
        inComeFragment.materialCircleLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131297000, "field 'materialCircleLayout'", LinearLayout.class);
        this.view2131297000 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        inComeFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        inComeFragment.balance = (TextView) Utils.findRequiredViewAsType(view, 2131296372, "field 'balance'", TextView.class);
        inComeFragment.invitePeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131296820, "field 'invitePeopleNum'", TextView.class);
        inComeFragment.setUserHead = (ImageView) Utils.findRequiredViewAsType(view, 2131297353, "field 'setUserHead'", ImageView.class);
        inComeFragment.inviteReward = (TextView) Utils.findRequiredViewAsType(view, 2131296822, "field 'inviteReward'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296816, "field 'invitationRecord' and method 'onViewClicked'");
        inComeFragment.invitationRecord = (LinearLayout) Utils.castView(findRequiredView7, 2131296816, "field 'invitationRecord'", LinearLayout.class);
        this.view2131296816 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inComeFragment.onViewClicked(view);
            }
        });
        inComeFragment.levelNameText = (TextView) Utils.findRequiredViewAsType(view, 2131296925, "field 'levelNameText'", TextView.class);
        inComeFragment.invitePeopleNum2 = (TextView) Utils.findRequiredViewAsType(view, 2131296821, "field 'invitePeopleNum2'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InComeFragment inComeFragment = this.target;
        if (inComeFragment != null) {
            this.target = null;
            inComeFragment.btGetMoney = null;
            inComeFragment.upgradeMember = null;
            inComeFragment.myQRCode = null;
            inComeFragment.memberPrivilege = null;
            inComeFragment.userPhone = null;
            inComeFragment.inviteUrl = null;
            inComeFragment.copyBtn = null;
            inComeFragment.materialCircleLayout = null;
            inComeFragment.refreshLayout = null;
            inComeFragment.balance = null;
            inComeFragment.invitePeopleNum = null;
            inComeFragment.setUserHead = null;
            inComeFragment.inviteReward = null;
            inComeFragment.invitationRecord = null;
            inComeFragment.levelNameText = null;
            inComeFragment.invitePeopleNum2 = null;
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
            this.view2131297000.setOnClickListener((View.OnClickListener) null);
            this.view2131297000 = null;
            this.view2131296816.setOnClickListener((View.OnClickListener) null);
            this.view2131296816 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
