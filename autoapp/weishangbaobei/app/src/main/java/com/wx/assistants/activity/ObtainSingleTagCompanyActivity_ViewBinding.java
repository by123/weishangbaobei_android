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

public class ObtainSingleTagCompanyActivity_ViewBinding implements Unbinder {
    private ObtainSingleTagCompanyActivity target;
    private View view2131296529;
    private View view2131296728;
    private View view2131297049;

    @UiThread
    public ObtainSingleTagCompanyActivity_ViewBinding(ObtainSingleTagCompanyActivity obtainSingleTagCompanyActivity) {
        this(obtainSingleTagCompanyActivity, obtainSingleTagCompanyActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.ObtainSingleTagCompanyActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.wx.assistants.activity.ObtainSingleTagCompanyActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.wx.assistants.activity.ObtainSingleTagCompanyActivity_ViewBinding$3, android.view.View$OnClickListener] */
    @UiThread
    public ObtainSingleTagCompanyActivity_ViewBinding(final ObtainSingleTagCompanyActivity obtainSingleTagCompanyActivity, View view) {
        this.target = obtainSingleTagCompanyActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        obtainSingleTagCompanyActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainSingleTagCompanyActivity.onViewClicked(view);
            }
        });
        obtainSingleTagCompanyActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131296728, "field 'getLable' and method 'onViewClicked'");
        obtainSingleTagCompanyActivity.getLable = (Button) Utils.castView(findRequiredView2, 2131296728, "field 'getLable'", Button.class);
        this.view2131296728 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainSingleTagCompanyActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296529, "field 'confirmSelect' and method 'onViewClicked'");
        obtainSingleTagCompanyActivity.confirmSelect = (Button) Utils.castView(findRequiredView3, 2131296529, "field 'confirmSelect'", Button.class);
        this.view2131296529 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                obtainSingleTagCompanyActivity.onViewClicked(view);
            }
        });
        obtainSingleTagCompanyActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        obtainSingleTagCompanyActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        obtainSingleTagCompanyActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        obtainSingleTagCompanyActivity.selectWXLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297322, "field 'selectWXLayout'", LinearLayout.class);
        obtainSingleTagCompanyActivity.labelList = (ListView) Utils.findRequiredViewAsType(view, 2131296886, "field 'labelList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ObtainSingleTagCompanyActivity obtainSingleTagCompanyActivity = this.target;
        if (obtainSingleTagCompanyActivity != null) {
            this.target = null;
            obtainSingleTagCompanyActivity.navBack = null;
            obtainSingleTagCompanyActivity.navTitle = null;
            obtainSingleTagCompanyActivity.getLable = null;
            obtainSingleTagCompanyActivity.confirmSelect = null;
            obtainSingleTagCompanyActivity.navRightText = null;
            obtainSingleTagCompanyActivity.navRightLayout = null;
            obtainSingleTagCompanyActivity.flowViewGroup = null;
            obtainSingleTagCompanyActivity.selectWXLayout = null;
            obtainSingleTagCompanyActivity.labelList = null;
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
