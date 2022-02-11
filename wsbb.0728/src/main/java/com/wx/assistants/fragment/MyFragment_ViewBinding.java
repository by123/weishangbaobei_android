package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyFragment_ViewBinding implements Unbinder {
    private MyFragment target;
    private View view2131296399;
    private View view2131296407;
    private View view2131296408;
    private View view2131296454;
    private View view2131296658;
    private View view2131296741;
    private View view2131296818;
    private View view2131296840;
    private View view2131296963;
    private View view2131296968;
    private View view2131296980;
    private View view2131297002;
    private View view2131297047;
    private View view2131297052;
    private View view2131297451;

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v36, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v41, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v46, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v54, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v59, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r0v67, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$10, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v72, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$11, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v77, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$12, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v82, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$13, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v87, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.MyFragment_ViewBinding$14] */
    /* JADX WARNING: type inference failed for: r0v95, types: [com.wx.assistants.fragment.MyFragment_ViewBinding$15, android.view.View$OnClickListener] */
    @UiThread
    public MyFragment_ViewBinding(final MyFragment myFragment, View view) {
        this.target = myFragment;
        View findRequiredView = Utils.findRequiredView(view, 2131297047, "field 'myActivation' and method 'onViewClicked'");
        myFragment.myActivation = (LinearLayout) Utils.castView(findRequiredView, 2131297047, "field 'myActivation'", LinearLayout.class);
        this.view2131297047 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296840, "field 'ivMyIndent' and method 'onViewClicked'");
        myFragment.ivMyIndent = (LinearLayout) Utils.castView(findRequiredView2, 2131296840, "field 'ivMyIndent'", LinearLayout.class);
        this.view2131296840 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296818, "field 'inviteFriend' and method 'onViewClicked'");
        myFragment.inviteFriend = (LinearLayout) Utils.castView(findRequiredView3, 2131296818, "field 'inviteFriend'", LinearLayout.class);
        this.view2131296818 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131296454, "field 'buttonService' and method 'onViewClicked'");
        myFragment.buttonService = (LinearLayout) Utils.castView(findRequiredView4, 2131296454, "field 'buttonService'", LinearLayout.class);
        this.view2131296454 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        myFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        myFragment.setUserName = (TextView) Utils.findRequiredViewAsType(view, 2131297354, "field 'setUserName'", TextView.class);
        myFragment.setExpireTime = (TextView) Utils.findRequiredViewAsType(view, 2131297349, "field 'setExpireTime'", TextView.class);
        myFragment.memberLever = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'memberLever'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296968, "field 'll_member_center' and method 'onViewClicked'");
        myFragment.ll_member_center = (LinearLayout) Utils.castView(findRequiredView5, 2131296968, "field 'll_member_center'", LinearLayout.class);
        this.view2131296968 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131296963, "field 'll_check_update' and method 'onViewClicked'");
        myFragment.ll_check_update = (LinearLayout) Utils.castView(findRequiredView6, 2131296963, "field 'll_check_update'", LinearLayout.class);
        this.view2131296963 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131296658, "field 'faqLayout' and method 'onViewClicked'");
        myFragment.faqLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131296658, "field 'faqLayout'", LinearLayout.class);
        this.view2131296658 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        myFragment.currentVersionText = (TextView) Utils.findRequiredViewAsType(view, 2131296562, "field 'currentVersionText'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297002, "field 'meFriendCircle', method 'onViewClicked', and method 'onViewClicked'");
        myFragment.meFriendCircle = (LinearLayout) Utils.castView(findRequiredView8, 2131297002, "field 'meFriendCircle'", LinearLayout.class);
        this.view2131297002 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
                myFragment.onViewClicked();
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131297451, "field 'systemMsgLayout' and method 'onViewClicked'");
        myFragment.systemMsgLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131297451, "field 'systemMsgLayout'", LinearLayout.class);
        this.view2131297451 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        myFragment.setUserHead = (ImageView) Utils.findRequiredViewAsType(view, 2131297353, "field 'setUserHead'", ImageView.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131296408, "field 'bottomWhiteTopLayout' and method 'onViewClicked'");
        myFragment.bottomWhiteTopLayout = (LinearLayout) Utils.castView(findRequiredView10, 2131296408, "field 'bottomWhiteTopLayout'", LinearLayout.class);
        this.view2131296408 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, 2131296980, "field 'loginBtn' and method 'onViewClicked'");
        myFragment.loginBtn = (Button) Utils.castView(findRequiredView11, 2131296980, "field 'loginBtn'", Button.class);
        this.view2131296980 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, 2131296407, "field 'bottomWhiteLayout' and method 'onViewClicked'");
        myFragment.bottomWhiteLayout = (LinearLayout) Utils.castView(findRequiredView12, 2131296407, "field 'bottomWhiteLayout'", LinearLayout.class);
        this.view2131296407 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, 2131296399, "field 'bottomLayout' and method 'onViewClicked'");
        myFragment.bottomLayout = (LinearLayout) Utils.castView(findRequiredView13, 2131296399, "field 'bottomLayout'", LinearLayout.class);
        this.view2131296399 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, 2131296741, "field 'headOperationLayout' and method 'onViewClicked'");
        myFragment.headOperationLayout = (FrameLayout) Utils.castView(findRequiredView14, 2131296741, "field 'headOperationLayout'", FrameLayout.class);
        this.view2131296741 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        myFragment.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView15 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        myFragment.navRightLayout = (LinearLayout) Utils.castView(findRequiredView15, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myFragment.onViewClicked(view);
            }
        });
        myFragment.memberLeverLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297005, "field 'memberLeverLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        MyFragment myFragment = this.target;
        if (myFragment != null) {
            this.target = null;
            myFragment.myActivation = null;
            myFragment.ivMyIndent = null;
            myFragment.inviteFriend = null;
            myFragment.buttonService = null;
            myFragment.refreshLayout = null;
            myFragment.setUserName = null;
            myFragment.setExpireTime = null;
            myFragment.memberLever = null;
            myFragment.ll_member_center = null;
            myFragment.ll_check_update = null;
            myFragment.faqLayout = null;
            myFragment.currentVersionText = null;
            myFragment.meFriendCircle = null;
            myFragment.systemMsgLayout = null;
            myFragment.setUserHead = null;
            myFragment.bottomWhiteTopLayout = null;
            myFragment.loginBtn = null;
            myFragment.bottomWhiteLayout = null;
            myFragment.bottomLayout = null;
            myFragment.headOperationLayout = null;
            myFragment.navTitle = null;
            myFragment.navRightLayout = null;
            myFragment.memberLeverLayout = null;
            this.view2131297047.setOnClickListener((View.OnClickListener) null);
            this.view2131297047 = null;
            this.view2131296840.setOnClickListener((View.OnClickListener) null);
            this.view2131296840 = null;
            this.view2131296818.setOnClickListener((View.OnClickListener) null);
            this.view2131296818 = null;
            this.view2131296454.setOnClickListener((View.OnClickListener) null);
            this.view2131296454 = null;
            this.view2131296968.setOnClickListener((View.OnClickListener) null);
            this.view2131296968 = null;
            this.view2131296963.setOnClickListener((View.OnClickListener) null);
            this.view2131296963 = null;
            this.view2131296658.setOnClickListener((View.OnClickListener) null);
            this.view2131296658 = null;
            this.view2131297002.setOnClickListener((View.OnClickListener) null);
            this.view2131297002 = null;
            this.view2131297451.setOnClickListener((View.OnClickListener) null);
            this.view2131297451 = null;
            this.view2131296408.setOnClickListener((View.OnClickListener) null);
            this.view2131296408 = null;
            this.view2131296980.setOnClickListener((View.OnClickListener) null);
            this.view2131296980 = null;
            this.view2131296407.setOnClickListener((View.OnClickListener) null);
            this.view2131296407 = null;
            this.view2131296399.setOnClickListener((View.OnClickListener) null);
            this.view2131296399 = null;
            this.view2131296741.setOnClickListener((View.OnClickListener) null);
            this.view2131296741 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
