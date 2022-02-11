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
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddCardActivity_ViewBinding implements Unbinder {
    private AutoAddCardActivity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddCardActivity_ViewBinding(AutoAddCardActivity autoAddCardActivity) {
        this(autoAddCardActivity, autoAddCardActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.AutoAddCardActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v15, types: [com.wx.assistants.activity.AutoAddCardActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v21, types: [com.wx.assistants.activity.AutoAddCardActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v26, types: [com.wx.assistants.activity.AutoAddCardActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v60, types: [com.wx.assistants.activity.AutoAddCardActivity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddCardActivity_ViewBinding(final AutoAddCardActivity autoAddCardActivity, View view) {
        this.target = autoAddCardActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddCardActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddCardActivity.onViewClicked(view);
            }
        });
        autoAddCardActivity.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        autoAddCardActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddCardActivity.remarkViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131297237, "field 'remarkViewGroup'", TagCloudLayout.class);
        autoAddCardActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        autoAddCardActivity.sexRadioButtonAll = (RadioButton) Utils.findRequiredViewAsType(view, 2131297359, "field 'sexRadioButtonAll'", RadioButton.class);
        autoAddCardActivity.sexRadioButtonMan = (RadioButton) Utils.findRequiredViewAsType(view, 2131297360, "field 'sexRadioButtonMan'", RadioButton.class);
        autoAddCardActivity.sexRadioButtonWoman = (RadioButton) Utils.findRequiredViewAsType(view, 2131297361, "field 'sexRadioButtonWoman'", RadioButton.class);
        autoAddCardActivity.sexRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, 2131297362, "field 'sexRadioGroup'", RadioGroup.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddCardActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddCardActivity.onViewClicked(view);
            }
        });
        autoAddCardActivity.remarksLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297238, "field 'remarksLayout'", LinearLayout.class);
        autoAddCardActivity.editTextAddName = (EditText) Utils.findRequiredViewAsType(view, 2131296620, "field 'editTextAddName'", EditText.class);
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddCardActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddCardActivity.onViewClicked(view);
            }
        });
        autoAddCardActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddCardActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddCardActivity.onViewClicked(view);
            }
        });
        autoAddCardActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddCardActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        autoAddCardActivity.sayLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297280, "field 'sayLayout'", LinearLayout.class);
        autoAddCardActivity.remarkSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297234, "field 'remarkSwitchBtn'", Switch.class);
        autoAddCardActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddCardActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddCardActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoAddCardActivity autoAddCardActivity = this.target;
        if (autoAddCardActivity != null) {
            this.target = null;
            autoAddCardActivity.startWx = null;
            autoAddCardActivity.customRadioSwitchLayout = null;
            autoAddCardActivity.flowViewGroup = null;
            autoAddCardActivity.remarkViewGroup = null;
            autoAddCardActivity.sayContent = null;
            autoAddCardActivity.sexRadioButtonAll = null;
            autoAddCardActivity.sexRadioButtonMan = null;
            autoAddCardActivity.sexRadioButtonWoman = null;
            autoAddCardActivity.sexRadioGroup = null;
            autoAddCardActivity.videoIntroduce = null;
            autoAddCardActivity.remarksLayout = null;
            autoAddCardActivity.editTextAddName = null;
            autoAddCardActivity.navBack = null;
            autoAddCardActivity.navTitle = null;
            autoAddCardActivity.cleanEditText = null;
            autoAddCardActivity.executeTimeSpaceLayout = null;
            autoAddCardActivity.navRightImg = null;
            autoAddCardActivity.sayLayout = null;
            autoAddCardActivity.remarkSwitchBtn = null;
            autoAddCardActivity.ffModelLayout = null;
            autoAddCardActivity.singleSelectLabelLayout = null;
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
