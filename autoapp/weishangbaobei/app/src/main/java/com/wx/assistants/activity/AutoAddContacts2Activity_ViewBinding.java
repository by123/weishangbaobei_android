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
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddContacts2Activity_ViewBinding implements Unbinder {
    private AutoAddContacts2Activity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddContacts2Activity_ViewBinding(AutoAddContacts2Activity autoAddContacts2Activity) {
        this(autoAddContacts2Activity, autoAddContacts2Activity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts2Activity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts2Activity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts2Activity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts2Activity_ViewBinding$4] */
    @UiThread
    public AutoAddContacts2Activity_ViewBinding(final AutoAddContacts2Activity autoAddContacts2Activity, View view) {
        this.target = autoAddContacts2Activity;
        autoAddContacts2Activity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddContacts2Activity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddContacts2Activity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts2Activity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddContacts2Activity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts2Activity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddContacts2Activity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts2Activity.onViewClicked(view);
            }
        });
        autoAddContacts2Activity.isOpenBreakText = (TextView) Utils.findRequiredViewAsType(view, 2131296829, "field 'isOpenBreakText'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContacts2Activity.navBack = (LinearLayout) Utils.castView(findRequiredView4, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts2Activity.onViewClicked(view);
            }
        });
        autoAddContacts2Activity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        autoAddContacts2Activity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        autoAddContacts2Activity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddContacts2Activity.remarkSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131297234, "field 'remarkSwitchBtn'", Switch.class);
        autoAddContacts2Activity.breakSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296414, "field 'breakSwitchBtn'", Switch.class);
        autoAddContacts2Activity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddContacts2Activity autoAddContacts2Activity = this.target;
        if (autoAddContacts2Activity != null) {
            this.target = null;
            autoAddContacts2Activity.flowViewGroup = null;
            autoAddContacts2Activity.sayContent = null;
            autoAddContacts2Activity.startWx = null;
            autoAddContacts2Activity.videoIntroduce = null;
            autoAddContacts2Activity.navRightLayout = null;
            autoAddContacts2Activity.isOpenBreakText = null;
            autoAddContacts2Activity.navBack = null;
            autoAddContacts2Activity.navTitle = null;
            autoAddContacts2Activity.navRightImg = null;
            autoAddContacts2Activity.executeTimeSpaceLayout = null;
            autoAddContacts2Activity.remarkSwitchBtn = null;
            autoAddContacts2Activity.breakSwitchBtn = null;
            autoAddContacts2Activity.ffModelLayout = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
