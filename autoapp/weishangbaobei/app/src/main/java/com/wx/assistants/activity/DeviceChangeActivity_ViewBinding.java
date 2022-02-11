package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DeviceChangeActivity_ViewBinding implements Unbinder {
    private DeviceChangeActivity target;
    private View view2131297049;
    private View view2131297087;
    private View view2131297347;
    private View view2131297352;
    private View view2131297355;
    private View view2131297356;
    private View view2131297357;
    private View view2131297358;

    @UiThread
    public DeviceChangeActivity_ViewBinding(DeviceChangeActivity deviceChangeActivity) {
        this(deviceChangeActivity, deviceChangeActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.view.View$OnClickListener, com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v15, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v19, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v24, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v28, types: [com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v32, types: [android.view.View$OnClickListener, com.wx.assistants.activity.DeviceChangeActivity_ViewBinding$8] */
    @UiThread
    public DeviceChangeActivity_ViewBinding(final DeviceChangeActivity deviceChangeActivity, View view) {
        this.target = deviceChangeActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297356, "field 'setWxRadioButton' and method 'onViewClicked'");
        deviceChangeActivity.setWxRadioButton = (CheckBox) Utils.castView(findRequiredView, 2131297356, "field 'setWxRadioButton'", CheckBox.class);
        this.view2131297356 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297355, "field 'setWxPayment' and method 'onViewClicked'");
        deviceChangeActivity.setWxPayment = (RelativeLayout) Utils.castView(findRequiredView2, 2131297355, "field 'setWxPayment'", RelativeLayout.class);
        this.view2131297355 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297358, "field 'setZfbRadioButton' and method 'onViewClicked'");
        deviceChangeActivity.setZfbRadioButton = (CheckBox) Utils.castView(findRequiredView3, 2131297358, "field 'setZfbRadioButton'", CheckBox.class);
        this.view2131297358 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297357, "field 'setZfbPayment' and method 'onViewClicked'");
        deviceChangeActivity.setZfbPayment = (RelativeLayout) Utils.castView(findRequiredView4, 2131297357, "field 'setZfbPayment'", RelativeLayout.class);
        this.view2131297357 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297347, "field 'setAgreement' and method 'onViewClicked'");
        deviceChangeActivity.setAgreement = (LinearLayout) Utils.castView(findRequiredView5, 2131297347, "field 'setAgreement'", LinearLayout.class);
        this.view2131297347 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        deviceChangeActivity.setAmountPrice = (TextView) Utils.findRequiredViewAsType(view, 2131297348, "field 'setAmountPrice'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297352, "field 'setPayment' and method 'onViewClicked'");
        deviceChangeActivity.setPayment = (TextView) Utils.castView(findRequiredView6, 2131297352, "field 'setPayment'", TextView.class);
        this.view2131297352 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297087, "field 'onLineSuggestServiceBtn' and method 'onViewClicked'");
        deviceChangeActivity.onLineSuggestServiceBtn = (Button) Utils.castView(findRequiredView7, 2131297087, "field 'onLineSuggestServiceBtn'", Button.class);
        this.view2131297087 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        deviceChangeActivity.navBack = (LinearLayout) Utils.castView(findRequiredView8, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceChangeActivity.onViewClicked(view);
            }
        });
        deviceChangeActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DeviceChangeActivity deviceChangeActivity = this.target;
        if (deviceChangeActivity != null) {
            this.target = null;
            deviceChangeActivity.setWxRadioButton = null;
            deviceChangeActivity.setWxPayment = null;
            deviceChangeActivity.setZfbRadioButton = null;
            deviceChangeActivity.setZfbPayment = null;
            deviceChangeActivity.setAgreement = null;
            deviceChangeActivity.setAmountPrice = null;
            deviceChangeActivity.setPayment = null;
            deviceChangeActivity.onLineSuggestServiceBtn = null;
            deviceChangeActivity.navBack = null;
            deviceChangeActivity.navTitle = null;
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
            this.view2131297087.setOnClickListener((View.OnClickListener) null);
            this.view2131297087 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
