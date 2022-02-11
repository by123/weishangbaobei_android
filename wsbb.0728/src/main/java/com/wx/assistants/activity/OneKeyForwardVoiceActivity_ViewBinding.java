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

public class OneKeyForwardVoiceActivity_ViewBinding implements Unbinder {
    private OneKeyForwardVoiceActivity target;
    private View view2131297049;
    private View view2131297052;

    @UiThread
    public OneKeyForwardVoiceActivity_ViewBinding(OneKeyForwardVoiceActivity oneKeyForwardVoiceActivity) {
        this(oneKeyForwardVoiceActivity, oneKeyForwardVoiceActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardVoiceActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardVoiceActivity_ViewBinding$2] */
    @UiThread
    public OneKeyForwardVoiceActivity_ViewBinding(final OneKeyForwardVoiceActivity oneKeyForwardVoiceActivity, View view) {
        this.target = oneKeyForwardVoiceActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyForwardVoiceActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardVoiceActivity.onViewClicked(view);
            }
        });
        oneKeyForwardVoiceActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        oneKeyForwardVoiceActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyForwardVoiceActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardVoiceActivity.onViewClicked(view);
            }
        });
        oneKeyForwardVoiceActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyForwardVoiceActivity.tabLayout = (UltimateTabLayout) Utils.findRequiredViewAsType(view, 2131297452, "field 'tabLayout'", UltimateTabLayout.class);
        oneKeyForwardVoiceActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, 2131297639, "field 'viewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyForwardVoiceActivity oneKeyForwardVoiceActivity = this.target;
        if (oneKeyForwardVoiceActivity != null) {
            this.target = null;
            oneKeyForwardVoiceActivity.navBack = null;
            oneKeyForwardVoiceActivity.navTitle = null;
            oneKeyForwardVoiceActivity.navRightText = null;
            oneKeyForwardVoiceActivity.navRightLayout = null;
            oneKeyForwardVoiceActivity.shadowLinearLayout = null;
            oneKeyForwardVoiceActivity.tabLayout = null;
            oneKeyForwardVoiceActivity.viewPager = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
