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
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddSearchActivity_ViewBinding implements Unbinder {
    private AutoAddSearchActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddSearchActivity_ViewBinding(AutoAddSearchActivity autoAddSearchActivity) {
        this(autoAddSearchActivity, autoAddSearchActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [com.wx.assistants.activity.AutoAddSearchActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v9, types: [com.wx.assistants.activity.AutoAddSearchActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.wx.assistants.activity.AutoAddSearchActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v17, types: [com.wx.assistants.activity.AutoAddSearchActivity_ViewBinding$4, android.view.View$OnClickListener] */
    @UiThread
    public AutoAddSearchActivity_ViewBinding(final AutoAddSearchActivity autoAddSearchActivity, View view) {
        this.target = autoAddSearchActivity;
        autoAddSearchActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddSearchActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddSearchActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddSearchActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddSearchActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddSearchActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddSearchActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddSearchActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddSearchActivity.navBack = (LinearLayout) Utils.castView(findRequiredView4, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddSearchActivity.onViewClicked(view);
            }
        });
        autoAddSearchActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        autoAddSearchActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        autoAddSearchActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddSearchActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddSearchActivity.numSettingOnlyLayout = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingOnlyLayout.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddSearchActivity autoAddSearchActivity = this.target;
        if (autoAddSearchActivity != null) {
            this.target = null;
            autoAddSearchActivity.flowViewGroup = null;
            autoAddSearchActivity.sayContent = null;
            autoAddSearchActivity.startWx = null;
            autoAddSearchActivity.videoIntroduce = null;
            autoAddSearchActivity.navRightLayout = null;
            autoAddSearchActivity.navBack = null;
            autoAddSearchActivity.navTitle = null;
            autoAddSearchActivity.navRightImg = null;
            autoAddSearchActivity.executeTimeSpaceLayout = null;
            autoAddSearchActivity.ffModelLayout = null;
            autoAddSearchActivity.numSettingOnlyLayout = null;
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
