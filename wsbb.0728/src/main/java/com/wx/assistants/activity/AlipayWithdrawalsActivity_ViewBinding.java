package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class AlipayWithdrawalsActivity_ViewBinding implements Unbinder {
    private AlipayWithdrawalsActivity target;
    private View view2131296621;
    private View view2131296622;
    private View view2131296623;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297489;
    private View view2131297491;
    private View view2131297494;

    @UiThread
    public AlipayWithdrawalsActivity_ViewBinding(AlipayWithdrawalsActivity alipayWithdrawalsActivity) {
        this(alipayWithdrawalsActivity, alipayWithdrawalsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v24, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v29, types: [com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v37, types: [com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v45, types: [com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$8, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v65, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AlipayWithdrawalsActivity_ViewBinding$9] */
    @UiThread
    public AlipayWithdrawalsActivity_ViewBinding(final AlipayWithdrawalsActivity alipayWithdrawalsActivity, View view) {
        this.target = alipayWithdrawalsActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297491, "field 'textViewMoney' and method 'onViewClicked'");
        alipayWithdrawalsActivity.textViewMoney = (TextView) Utils.castView(findRequiredView, 2131297491, "field 'textViewMoney'", TextView.class);
        this.view2131297491 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296622, "field 'editTextZFBAccount' and method 'onViewClicked'");
        alipayWithdrawalsActivity.editTextZFBAccount = (EditText) Utils.castView(findRequiredView2, 2131296622, "field 'editTextZFBAccount'", EditText.class);
        this.view2131296622 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296623, "field 'editTextZFBName' and method 'onViewClicked'");
        alipayWithdrawalsActivity.editTextZFBName = (EditText) Utils.castView(findRequiredView3, 2131296623, "field 'editTextZFBName'", EditText.class);
        this.view2131296623 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297494, "field 'textViewWithdrawRecord' and method 'onViewClicked'");
        alipayWithdrawalsActivity.textViewWithdrawRecord = (TextView) Utils.castView(findRequiredView4, 2131297494, "field 'textViewWithdrawRecord'", TextView.class);
        this.view2131297494 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131296621, "field 'editTextWithdrawMoney' and method 'onViewClicked'");
        alipayWithdrawalsActivity.editTextWithdrawMoney = (EditText) Utils.castView(findRequiredView5, 2131296621, "field 'editTextWithdrawMoney'", EditText.class);
        this.view2131296621 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131297489, "field 'textViewAllWithdraw' and method 'onViewClicked'");
        alipayWithdrawalsActivity.textViewAllWithdraw = (TextView) Utils.castView(findRequiredView6, 2131297489, "field 'textViewAllWithdraw'", TextView.class);
        this.view2131297489 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        alipayWithdrawalsActivity.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        alipayWithdrawalsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView7, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        alipayWithdrawalsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        alipayWithdrawalsActivity.startWx = (Button) Utils.castView(findRequiredView8, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        alipayWithdrawalsActivity.dateText = (TextView) Utils.findRequiredViewAsType(view, 2131296574, "field 'dateText'", TextView.class);
        alipayWithdrawalsActivity.timeText = (TextView) Utils.findRequiredViewAsType(view, 2131297508, "field 'timeText'", TextView.class);
        alipayWithdrawalsActivity.extractMoneyText = (TextView) Utils.findRequiredViewAsType(view, 2131296654, "field 'extractMoneyText'", TextView.class);
        alipayWithdrawalsActivity.extractResult = (TextView) Utils.findRequiredViewAsType(view, 2131296655, "field 'extractResult'", TextView.class);
        alipayWithdrawalsActivity.lastRecordLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296893, "field 'lastRecordLayout'", LinearLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        alipayWithdrawalsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                alipayWithdrawalsActivity.onViewClicked(view);
            }
        });
        alipayWithdrawalsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        alipayWithdrawalsActivity.videoIntroduceLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        AlipayWithdrawalsActivity alipayWithdrawalsActivity = this.target;
        if (alipayWithdrawalsActivity != null) {
            this.target = null;
            alipayWithdrawalsActivity.textViewMoney = null;
            alipayWithdrawalsActivity.editTextZFBAccount = null;
            alipayWithdrawalsActivity.editTextZFBName = null;
            alipayWithdrawalsActivity.textViewWithdrawRecord = null;
            alipayWithdrawalsActivity.editTextWithdrawMoney = null;
            alipayWithdrawalsActivity.textViewAllWithdraw = null;
            alipayWithdrawalsActivity.refreshLayout = null;
            alipayWithdrawalsActivity.navBack = null;
            alipayWithdrawalsActivity.navTitle = null;
            alipayWithdrawalsActivity.startWx = null;
            alipayWithdrawalsActivity.dateText = null;
            alipayWithdrawalsActivity.timeText = null;
            alipayWithdrawalsActivity.extractMoneyText = null;
            alipayWithdrawalsActivity.extractResult = null;
            alipayWithdrawalsActivity.lastRecordLayout = null;
            alipayWithdrawalsActivity.navRightLayout = null;
            alipayWithdrawalsActivity.navRightImg = null;
            alipayWithdrawalsActivity.videoIntroduceLayout = null;
            this.view2131297491.setOnClickListener((View.OnClickListener) null);
            this.view2131297491 = null;
            this.view2131296622.setOnClickListener((View.OnClickListener) null);
            this.view2131296622 = null;
            this.view2131296623.setOnClickListener((View.OnClickListener) null);
            this.view2131296623 = null;
            this.view2131297494.setOnClickListener((View.OnClickListener) null);
            this.view2131297494 = null;
            this.view2131296621.setOnClickListener((View.OnClickListener) null);
            this.view2131296621 = null;
            this.view2131297489.setOnClickListener((View.OnClickListener) null);
            this.view2131297489 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
