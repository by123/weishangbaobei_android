package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.NumSettingLayout;

public class AutoPassNilValidationActivity_ViewBinding implements Unbinder {
    private AutoPassNilValidationActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoPassNilValidationActivity_ViewBinding(AutoPassNilValidationActivity autoPassNilValidationActivity) {
        this(autoPassNilValidationActivity, autoPassNilValidationActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.AutoPassNilValidationActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.AutoPassNilValidationActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.AutoPassNilValidationActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v34, types: [com.wx.assistants.activity.AutoPassNilValidationActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoPassNilValidationActivity_ViewBinding(final AutoPassNilValidationActivity autoPassNilValidationActivity, View view) {
        this.target = autoPassNilValidationActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoPassNilValidationActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassNilValidationActivity.onViewClicked(view);
            }
        });
        autoPassNilValidationActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoPassNilValidationActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassNilValidationActivity.onViewClicked(view);
            }
        });
        autoPassNilValidationActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoPassNilValidationActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassNilValidationActivity.onViewClicked(view);
            }
        });
        autoPassNilValidationActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoPassNilValidationActivity.remarkSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297234, "field 'remarkSwitchBtn'", Switch.class);
        autoPassNilValidationActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoPassNilValidationActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoPassNilValidationActivity.onViewClicked(view);
            }
        });
        autoPassNilValidationActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        autoPassNilValidationActivity.remarkSwitchBtn2 = (Switch) Utils.findRequiredViewAsType(view, 2131297235, "field 'remarkSwitchBtn2'", Switch.class);
        autoPassNilValidationActivity.sayContent2 = (EditText) Utils.findRequiredViewAsType(view, 2131297279, "field 'sayContent2'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        AutoPassNilValidationActivity autoPassNilValidationActivity = this.target;
        if (autoPassNilValidationActivity != null) {
            this.target = null;
            autoPassNilValidationActivity.navBack = null;
            autoPassNilValidationActivity.navTitle = null;
            autoPassNilValidationActivity.startWx = null;
            autoPassNilValidationActivity.navRightText = null;
            autoPassNilValidationActivity.videoIntroduceLayout = null;
            autoPassNilValidationActivity.numberSettingLayout = null;
            autoPassNilValidationActivity.remarkSwitchBtn = null;
            autoPassNilValidationActivity.navRightImg = null;
            autoPassNilValidationActivity.navRightLayout = null;
            autoPassNilValidationActivity.sayContent = null;
            autoPassNilValidationActivity.remarkSwitchBtn2 = null;
            autoPassNilValidationActivity.sayContent2 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
