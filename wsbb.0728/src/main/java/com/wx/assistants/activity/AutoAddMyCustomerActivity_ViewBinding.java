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
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class AutoAddMyCustomerActivity_ViewBinding implements Unbinder {
    private AutoAddMyCustomerActivity target;
    private View view2131296506;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddMyCustomerActivity_ViewBinding(AutoAddMyCustomerActivity autoAddMyCustomerActivity) {
        this(autoAddMyCustomerActivity, autoAddMyCustomerActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v28, types: [com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v39, types: [com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v50, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddMyCustomerActivity_ViewBinding$6] */
    @UiThread
    public AutoAddMyCustomerActivity_ViewBinding(final AutoAddMyCustomerActivity autoAddMyCustomerActivity, View view) {
        this.target = autoAddMyCustomerActivity;
        autoAddMyCustomerActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddMyCustomerActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddMyCustomerActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddMyCustomerActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddMyCustomerActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        autoAddMyCustomerActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddMyCustomerActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        autoAddMyCustomerActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddMyCustomerActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddMyCustomerActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        autoAddMyCustomerActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        autoAddMyCustomerActivity.customerEdit = (EditText) Utils.findRequiredViewAsType(view, 2131296573, "field 'customerEdit'", EditText.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296506, "field 'cleanCustomer' and method 'onViewClicked'");
        autoAddMyCustomerActivity.cleanCustomer = (LinearLayout) Utils.castView(findRequiredView6, 2131296506, "field 'cleanCustomer'", LinearLayout.class);
        this.view2131296506 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddMyCustomerActivity.onViewClicked(view);
            }
        });
        autoAddMyCustomerActivity.executeRemarkLayout = (RemarkFriendLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", RemarkFriendLayout.class);
        autoAddMyCustomerActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        autoAddMyCustomerActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddMyCustomerActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoAddMyCustomerActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddMyCustomerActivity autoAddMyCustomerActivity = this.target;
        if (autoAddMyCustomerActivity != null) {
            this.target = null;
            autoAddMyCustomerActivity.flowViewGroup = null;
            autoAddMyCustomerActivity.sayContent = null;
            autoAddMyCustomerActivity.startWx = null;
            autoAddMyCustomerActivity.videoIntroduce = null;
            autoAddMyCustomerActivity.navBack = null;
            autoAddMyCustomerActivity.navTitle = null;
            autoAddMyCustomerActivity.cleanEditText = null;
            autoAddMyCustomerActivity.executeTimeSpaceLayout = null;
            autoAddMyCustomerActivity.navRightText = null;
            autoAddMyCustomerActivity.navRightLayout = null;
            autoAddMyCustomerActivity.shadowLinearLayout = null;
            autoAddMyCustomerActivity.customerEdit = null;
            autoAddMyCustomerActivity.cleanCustomer = null;
            autoAddMyCustomerActivity.executeRemarkLayout = null;
            autoAddMyCustomerActivity.singleSelectLabelLayout = null;
            autoAddMyCustomerActivity.ffModelLayout = null;
            autoAddMyCustomerActivity.numberSettingLayout = null;
            autoAddMyCustomerActivity.navRightImg = null;
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
            this.view2131296506.setOnClickListener((View.OnClickListener) null);
            this.view2131296506 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
