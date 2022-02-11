package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.zhouyou.view.seekbar.SignSeekBar;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyForwardBigVideoActivity_ViewBinding implements Unbinder {
    private OneKeyForwardBigVideoActivity target;
    private View view2131296507;
    private View view2131296508;
    private View view2131296616;
    private View view2131296617;
    private View view2131297049;
    private View view2131297052;
    private View view2131297311;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyForwardBigVideoActivity_ViewBinding(OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity) {
        this(oneKeyForwardBigVideoActivity, oneKeyForwardBigVideoActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v19, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v32, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v37, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v42, types: [com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$8, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v65, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyForwardBigVideoActivity_ViewBinding$9] */
    @UiThread
    public OneKeyForwardBigVideoActivity_ViewBinding(final OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity, View view) {
        this.target = oneKeyForwardBigVideoActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296616, "field 'editLeavingMessage' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.editLeavingMessage = (EditTextWithScrollView) Utils.castView(findRequiredView, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        this.view2131296616 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296617, "field 'editLeavingMessage2' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.editLeavingMessage2 = (LinearLayout) Utils.castView(findRequiredView2, 2131296617, "field 'editLeavingMessage2'", LinearLayout.class);
        this.view2131296617 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297311, "field 'selectImg' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.selectImg = (ImageView) Utils.castView(findRequiredView4, 2131297311, "field 'selectImg'", ImageView.class);
        this.view2131297311 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        oneKeyForwardBigVideoActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView7, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131296508, "field 'cleanImage' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.cleanImage = (LinearLayout) Utils.castView(findRequiredView8, 2131296508, "field 'cleanImage'", LinearLayout.class);
        this.view2131296508 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
        oneKeyForwardBigVideoActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyForwardBigVideoActivity.text = (TextView) Utils.findRequiredViewAsType(view, 2131297461, "field 'text'", TextView.class);
        oneKeyForwardBigVideoActivity.text2 = (TextView) Utils.findRequiredViewAsType(view, 2131297462, "field 'text2'", TextView.class);
        oneKeyForwardBigVideoActivity.signSeekBar = (SignSeekBar) Utils.findRequiredViewAsType(view, 2131297382, "field 'signSeekBar'", SignSeekBar.class);
        oneKeyForwardBigVideoActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        oneKeyForwardBigVideoActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyForwardBigVideoActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyForwardBigVideoActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity = this.target;
        if (oneKeyForwardBigVideoActivity != null) {
            this.target = null;
            oneKeyForwardBigVideoActivity.editLeavingMessage = null;
            oneKeyForwardBigVideoActivity.editLeavingMessage2 = null;
            oneKeyForwardBigVideoActivity.videoIntroduceLayout = null;
            oneKeyForwardBigVideoActivity.selectImg = null;
            oneKeyForwardBigVideoActivity.navBack = null;
            oneKeyForwardBigVideoActivity.navTitle = null;
            oneKeyForwardBigVideoActivity.startWx = null;
            oneKeyForwardBigVideoActivity.cleanEditText = null;
            oneKeyForwardBigVideoActivity.cleanImage = null;
            oneKeyForwardBigVideoActivity.shadowLinearLayout = null;
            oneKeyForwardBigVideoActivity.text = null;
            oneKeyForwardBigVideoActivity.text2 = null;
            oneKeyForwardBigVideoActivity.signSeekBar = null;
            oneKeyForwardBigVideoActivity.navRightImg = null;
            oneKeyForwardBigVideoActivity.navRightText = null;
            oneKeyForwardBigVideoActivity.navRightLayout = null;
            this.view2131296616.setOnClickListener((View.OnClickListener) null);
            this.view2131296616 = null;
            this.view2131296617.setOnClickListener((View.OnClickListener) null);
            this.view2131296617 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297311.setOnClickListener((View.OnClickListener) null);
            this.view2131297311 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131296508.setOnClickListener((View.OnClickListener) null);
            this.view2131296508 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
