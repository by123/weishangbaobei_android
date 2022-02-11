package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class MyInviteRecordMainActivity_ViewBinding implements Unbinder {
    private MyInviteRecordMainActivity target;
    private View view2131297049;

    @UiThread
    public MyInviteRecordMainActivity_ViewBinding(MyInviteRecordMainActivity myInviteRecordMainActivity) {
        this(myInviteRecordMainActivity, myInviteRecordMainActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.activity.MyInviteRecordMainActivity_ViewBinding$1] */
    @UiThread
    public MyInviteRecordMainActivity_ViewBinding(final MyInviteRecordMainActivity myInviteRecordMainActivity, View view) {
        this.target = myInviteRecordMainActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        myInviteRecordMainActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myInviteRecordMainActivity.onViewClicked(view);
            }
        });
        myInviteRecordMainActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        myInviteRecordMainActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        myInviteRecordMainActivity.videoIntroduceLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        myInviteRecordMainActivity.graphicExplanationLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296731, "field 'graphicExplanationLayout'", LinearLayout.class);
        myInviteRecordMainActivity.tabLayout = (UltimateTabLayout) Utils.findRequiredViewAsType(view, 2131297452, "field 'tabLayout'", UltimateTabLayout.class);
        myInviteRecordMainActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, 2131297639, "field 'viewPager'", ViewPager.class);
        myInviteRecordMainActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        myInviteRecordMainActivity.navRightLayout = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297052, "field 'navRightLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        MyInviteRecordMainActivity myInviteRecordMainActivity = this.target;
        if (myInviteRecordMainActivity != null) {
            this.target = null;
            myInviteRecordMainActivity.navBack = null;
            myInviteRecordMainActivity.navTitle = null;
            myInviteRecordMainActivity.shadowLinearLayout = null;
            myInviteRecordMainActivity.videoIntroduceLayout = null;
            myInviteRecordMainActivity.graphicExplanationLayout = null;
            myInviteRecordMainActivity.tabLayout = null;
            myInviteRecordMainActivity.viewPager = null;
            myInviteRecordMainActivity.navRightText = null;
            myInviteRecordMainActivity.navRightLayout = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
