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
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddContacts1Activity_ViewBinding implements Unbinder {
    private AutoAddContacts1Activity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddContacts1Activity_ViewBinding(AutoAddContacts1Activity autoAddContacts1Activity) {
        this(autoAddContacts1Activity, autoAddContacts1Activity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [com.wx.assistants.activity.AutoAddContacts1Activity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts1Activity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v20, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts1Activity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v28, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts1Activity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContacts1Activity_ViewBinding$5] */
    @UiThread
    public AutoAddContacts1Activity_ViewBinding(final AutoAddContacts1Activity autoAddContacts1Activity, View view) {
        this.target = autoAddContacts1Activity;
        autoAddContacts1Activity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddContacts1Activity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddContacts1Activity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts1Activity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddContacts1Activity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts1Activity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContacts1Activity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts1Activity.onViewClicked(view);
            }
        });
        autoAddContacts1Activity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddContacts1Activity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts1Activity.onViewClicked(view);
            }
        });
        autoAddContacts1Activity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddContacts1Activity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddContacts1Activity.switchButton = (Switch) Utils.findRequiredViewAsType(view, 2131297447, "field 'switchButton'", Switch.class);
        autoAddContacts1Activity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoAddContacts1Activity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContacts1Activity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoAddContacts1Activity autoAddContacts1Activity = this.target;
        if (autoAddContacts1Activity != null) {
            this.target = null;
            autoAddContacts1Activity.flowViewGroup = null;
            autoAddContacts1Activity.sayContent = null;
            autoAddContacts1Activity.startWx = null;
            autoAddContacts1Activity.videoIntroduce = null;
            autoAddContacts1Activity.navBack = null;
            autoAddContacts1Activity.navTitle = null;
            autoAddContacts1Activity.cleanEditText = null;
            autoAddContacts1Activity.executeTimeSpaceLayout = null;
            autoAddContacts1Activity.ffModelLayout = null;
            autoAddContacts1Activity.switchButton = null;
            autoAddContacts1Activity.numberSettingLayout = null;
            autoAddContacts1Activity.navRightImg = null;
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
