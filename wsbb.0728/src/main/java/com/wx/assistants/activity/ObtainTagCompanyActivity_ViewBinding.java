package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.TagCloudLayout;

public class ObtainTagCompanyActivity_ViewBinding implements Unbinder {
    private ObtainTagCompanyActivity target;
    private View view2131296529;
    private View view2131296728;
    private View view2131297049;

    @UiThread
    public ObtainTagCompanyActivity_ViewBinding(ObtainTagCompanyActivity obtainTagCompanyActivity) {
        this(obtainTagCompanyActivity, obtainTagCompanyActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.ObtainTagCompanyActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.wx.assistants.activity.ObtainTagCompanyActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.wx.assistants.activity.ObtainTagCompanyActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public ObtainTagCompanyActivity_ViewBinding(final ObtainTagCompanyActivity obtainTagCompanyActivity, View view) {
        this.target = obtainTagCompanyActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        obtainTagCompanyActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainTagCompanyActivity.onViewClicked(view);
            }
        });
        obtainTagCompanyActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296728, "field 'getLable' and method 'onViewClicked'");
        obtainTagCompanyActivity.getLable = (Button) Utils.castView(findRequiredView2, 2131296728, "field 'getLable'", Button.class);
        this.view2131296728 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainTagCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296529, "field 'confirmSelect' and method 'onViewClicked'");
        obtainTagCompanyActivity.confirmSelect = (Button) Utils.castView(findRequiredView3, 2131296529, "field 'confirmSelect'", Button.class);
        this.view2131296529 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainTagCompanyActivity.onViewClicked(view);
            }
        });
        obtainTagCompanyActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        obtainTagCompanyActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        obtainTagCompanyActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        obtainTagCompanyActivity.selectWXLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297322, "field 'selectWXLayout'", LinearLayout.class);
        obtainTagCompanyActivity.labelList = (ListView) Utils.findRequiredViewAsType(view, 2131296886, "field 'labelList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ObtainTagCompanyActivity obtainTagCompanyActivity = this.target;
        if (obtainTagCompanyActivity != null) {
            this.target = null;
            obtainTagCompanyActivity.navBack = null;
            obtainTagCompanyActivity.navTitle = null;
            obtainTagCompanyActivity.getLable = null;
            obtainTagCompanyActivity.confirmSelect = null;
            obtainTagCompanyActivity.navRightText = null;
            obtainTagCompanyActivity.navRightLayout = null;
            obtainTagCompanyActivity.flowViewGroup = null;
            obtainTagCompanyActivity.selectWXLayout = null;
            obtainTagCompanyActivity.labelList = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296728.setOnClickListener((View.OnClickListener) null);
            this.view2131296728 = null;
            this.view2131296529.setOnClickListener((View.OnClickListener) null);
            this.view2131296529 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
