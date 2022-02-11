package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.CustomRadioSwitchLayout;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.SingleLabelSelectLayoutCompany;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddGroupFansCompanyActivity_ViewBinding implements Unbinder {
    private AutoAddGroupFansCompanyActivity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddGroupFansCompanyActivity_ViewBinding(AutoAddGroupFansCompanyActivity autoAddGroupFansCompanyActivity) {
        this(autoAddGroupFansCompanyActivity, autoAddGroupFansCompanyActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.AutoAddGroupFansCompanyActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v36, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v47, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v55, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v23, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddGroupFansCompanyActivity_ViewBinding$5] */
    @UiThread
    public AutoAddGroupFansCompanyActivity_ViewBinding(final AutoAddGroupFansCompanyActivity autoAddGroupFansCompanyActivity, View view) {
        this.target = autoAddGroupFansCompanyActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddGroupFansCompanyActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansCompanyActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansCompanyActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoAddGroupFansCompanyActivity.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        autoAddGroupFansCompanyActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddGroupFansCompanyActivity.remarkViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131297237, "field 'remarkViewGroup'", TagCloudLayout.class);
        autoAddGroupFansCompanyActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        autoAddGroupFansCompanyActivity.sexRadioButtonAll = (RadioButton) Utils.findRequiredViewAsType(view, 2131297359, "field 'sexRadioButtonAll'", RadioButton.class);
        autoAddGroupFansCompanyActivity.sexRadioButtonMan = (RadioButton) Utils.findRequiredViewAsType(view, 2131297360, "field 'sexRadioButtonMan'", RadioButton.class);
        autoAddGroupFansCompanyActivity.sexRadioButtonWoman = (RadioButton) Utils.findRequiredViewAsType(view, 2131297361, "field 'sexRadioButtonWoman'", RadioButton.class);
        autoAddGroupFansCompanyActivity.sexRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, 2131297362, "field 'sexRadioGroup'", RadioGroup.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddGroupFansCompanyActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansCompanyActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansCompanyActivity.remarksLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297238, "field 'remarksLayout'", LinearLayout.class);
        autoAddGroupFansCompanyActivity.editTextAddName = (EditText) Utils.findRequiredViewAsType(view, 2131296620, "field 'editTextAddName'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddGroupFansCompanyActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansCompanyActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansCompanyActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddGroupFansCompanyActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansCompanyActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansCompanyActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddGroupFansCompanyActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        autoAddGroupFansCompanyActivity.sayLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297280, "field 'sayLayout'", LinearLayout.class);
        autoAddGroupFansCompanyActivity.remarkSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297234, "field 'remarkSwitchBtn'", Switch.class);
        autoAddGroupFansCompanyActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddGroupFansCompanyActivity.singleSelectLabelLayout = (SingleLabelSelectLayoutCompany) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayoutCompany.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansCompanyActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoAddGroupFansCompanyActivity autoAddGroupFansCompanyActivity = this.target;
        if (autoAddGroupFansCompanyActivity != null) {
            this.target = null;
            autoAddGroupFansCompanyActivity.startWx = null;
            autoAddGroupFansCompanyActivity.numberSettingLayout = null;
            autoAddGroupFansCompanyActivity.customRadioSwitchLayout = null;
            autoAddGroupFansCompanyActivity.flowViewGroup = null;
            autoAddGroupFansCompanyActivity.remarkViewGroup = null;
            autoAddGroupFansCompanyActivity.sayContent = null;
            autoAddGroupFansCompanyActivity.sexRadioButtonAll = null;
            autoAddGroupFansCompanyActivity.sexRadioButtonMan = null;
            autoAddGroupFansCompanyActivity.sexRadioButtonWoman = null;
            autoAddGroupFansCompanyActivity.sexRadioGroup = null;
            autoAddGroupFansCompanyActivity.videoIntroduce = null;
            autoAddGroupFansCompanyActivity.remarksLayout = null;
            autoAddGroupFansCompanyActivity.editTextAddName = null;
            autoAddGroupFansCompanyActivity.navBack = null;
            autoAddGroupFansCompanyActivity.navTitle = null;
            autoAddGroupFansCompanyActivity.cleanEditText = null;
            autoAddGroupFansCompanyActivity.executeTimeSpaceLayout = null;
            autoAddGroupFansCompanyActivity.navRightImg = null;
            autoAddGroupFansCompanyActivity.sayLayout = null;
            autoAddGroupFansCompanyActivity.remarkSwitchBtn = null;
            autoAddGroupFansCompanyActivity.ffModelLayout = null;
            autoAddGroupFansCompanyActivity.singleSelectLabelLayout = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
