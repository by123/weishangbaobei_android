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
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddContacts3Activity_ViewBinding implements Unbinder {
    private AutoAddContacts3Activity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddContacts3Activity_ViewBinding(AutoAddContacts3Activity autoAddContacts3Activity) {
        this(autoAddContacts3Activity, autoAddContacts3Activity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v19, types: [com.wx.assistants.activity.AutoAddContacts3Activity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.AutoAddContacts3Activity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v29, types: [com.wx.assistants.activity.AutoAddContacts3Activity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v40, types: [com.wx.assistants.activity.AutoAddContacts3Activity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v15, types: [com.wx.assistants.activity.AutoAddContacts3Activity_ViewBinding$5, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddContacts3Activity_ViewBinding(final AutoAddContacts3Activity autoAddContacts3Activity, View view) {
        this.target = autoAddContacts3Activity;
        autoAddContacts3Activity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddContacts3Activity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        autoAddContacts3Activity.remarkYes = (RadioButton) Utils.findRequiredViewAsType(view, 2131297236, "field 'remarkYes'", RadioButton.class);
        autoAddContacts3Activity.remarkNo = (RadioButton) Utils.findRequiredViewAsType(view, 2131297232, "field 'remarkNo'", RadioButton.class);
        autoAddContacts3Activity.remarkRadioGroupId = (RadioGroup) Utils.findRequiredViewAsType(view, 2131297233, "field 'remarkRadioGroupId'", RadioGroup.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddContacts3Activity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts3Activity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddContacts3Activity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts3Activity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContacts3Activity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts3Activity.onViewClicked(view);
            }
        });
        autoAddContacts3Activity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        autoAddContacts3Activity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddContacts3Activity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts3Activity.onViewClicked(view);
            }
        });
        autoAddContacts3Activity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddContacts3Activity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddContacts3Activity.numSettingOnlyLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts3Activity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoAddContacts3Activity autoAddContacts3Activity = this.target;
        if (autoAddContacts3Activity != null) {
            this.target = null;
            autoAddContacts3Activity.flowViewGroup = null;
            autoAddContacts3Activity.sayContent = null;
            autoAddContacts3Activity.remarkYes = null;
            autoAddContacts3Activity.remarkNo = null;
            autoAddContacts3Activity.remarkRadioGroupId = null;
            autoAddContacts3Activity.startWx = null;
            autoAddContacts3Activity.videoIntroduce = null;
            autoAddContacts3Activity.navBack = null;
            autoAddContacts3Activity.navTitle = null;
            autoAddContacts3Activity.navRightImg = null;
            autoAddContacts3Activity.cleanEditText = null;
            autoAddContacts3Activity.executeTimeSpaceLayout = null;
            autoAddContacts3Activity.ffModelLayout = null;
            autoAddContacts3Activity.numSettingOnlyLayout = null;
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
