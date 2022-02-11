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
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class AutoAddGroupFansActivity_ViewBinding implements Unbinder {
    private AutoAddGroupFansActivity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddGroupFansActivity_ViewBinding(AutoAddGroupFansActivity autoAddGroupFansActivity) {
        this(autoAddGroupFansActivity, autoAddGroupFansActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.AutoAddGroupFansActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v36, types: [com.wx.assistants.activity.AutoAddGroupFansActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v47, types: [com.wx.assistants.activity.AutoAddGroupFansActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v55, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddGroupFansActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v75, types: [com.wx.assistants.activity.AutoAddGroupFansActivity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddGroupFansActivity_ViewBinding(final AutoAddGroupFansActivity autoAddGroupFansActivity, View view) {
        this.target = autoAddGroupFansActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddGroupFansActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoAddGroupFansActivity.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        autoAddGroupFansActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddGroupFansActivity.remarkViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131297237, "field 'remarkViewGroup'", TagCloudLayout.class);
        autoAddGroupFansActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        autoAddGroupFansActivity.sexRadioButtonAll = (RadioButton) Utils.findRequiredViewAsType(view, 2131297359, "field 'sexRadioButtonAll'", RadioButton.class);
        autoAddGroupFansActivity.sexRadioButtonMan = (RadioButton) Utils.findRequiredViewAsType(view, 2131297360, "field 'sexRadioButtonMan'", RadioButton.class);
        autoAddGroupFansActivity.sexRadioButtonWoman = (RadioButton) Utils.findRequiredViewAsType(view, 2131297361, "field 'sexRadioButtonWoman'", RadioButton.class);
        autoAddGroupFansActivity.sexRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, 2131297362, "field 'sexRadioGroup'", RadioGroup.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddGroupFansActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansActivity.remarksLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297238, "field 'remarksLayout'", LinearLayout.class);
        autoAddGroupFansActivity.editTextAddName = (EditText) Utils.findRequiredViewAsType(view, 2131296620, "field 'editTextAddName'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddGroupFansActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddGroupFansActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddGroupFansActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        autoAddGroupFansActivity.sayLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297280, "field 'sayLayout'", LinearLayout.class);
        autoAddGroupFansActivity.remarkSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297234, "field 'remarkSwitchBtn'", Switch.class);
        autoAddGroupFansActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddGroupFansActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddGroupFansActivity.onViewClicked(view);
            }
        });
        autoAddGroupFansActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        autoAddGroupFansActivity.numberSettingLayout0 = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297083, "field 'numberSettingLayout0'", NumSettingOnlyLayout.class);
        autoAddGroupFansActivity.filterNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296669, "field 'filterNickName'", EditText.class);
        autoAddGroupFansActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddGroupFansActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddGroupFansActivity autoAddGroupFansActivity = this.target;
        if (autoAddGroupFansActivity != null) {
            this.target = null;
            autoAddGroupFansActivity.startWx = null;
            autoAddGroupFansActivity.numberSettingLayout = null;
            autoAddGroupFansActivity.customRadioSwitchLayout = null;
            autoAddGroupFansActivity.flowViewGroup = null;
            autoAddGroupFansActivity.remarkViewGroup = null;
            autoAddGroupFansActivity.sayContent = null;
            autoAddGroupFansActivity.sexRadioButtonAll = null;
            autoAddGroupFansActivity.sexRadioButtonMan = null;
            autoAddGroupFansActivity.sexRadioButtonWoman = null;
            autoAddGroupFansActivity.sexRadioGroup = null;
            autoAddGroupFansActivity.videoIntroduce = null;
            autoAddGroupFansActivity.remarksLayout = null;
            autoAddGroupFansActivity.editTextAddName = null;
            autoAddGroupFansActivity.navBack = null;
            autoAddGroupFansActivity.navTitle = null;
            autoAddGroupFansActivity.cleanEditText = null;
            autoAddGroupFansActivity.executeTimeSpaceLayout = null;
            autoAddGroupFansActivity.navRightImg = null;
            autoAddGroupFansActivity.sayLayout = null;
            autoAddGroupFansActivity.remarkSwitchBtn = null;
            autoAddGroupFansActivity.navRightText = null;
            autoAddGroupFansActivity.navRightLayout = null;
            autoAddGroupFansActivity.shadowLinearLayout = null;
            autoAddGroupFansActivity.numberSettingLayout0 = null;
            autoAddGroupFansActivity.filterNickName = null;
            autoAddGroupFansActivity.ffModelLayout = null;
            autoAddGroupFansActivity.singleSelectLabelLayout = null;
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
