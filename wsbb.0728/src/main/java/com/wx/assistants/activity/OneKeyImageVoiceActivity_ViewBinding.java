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
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyImageVoiceActivity_ViewBinding implements Unbinder {
    private OneKeyImageVoiceActivity target;
    private View view2131296319;
    private View view2131296323;
    private View view2131296505;
    private View view2131297049;
    private View view2131297052;
    private View view2131297147;
    private View view2131297308;
    private View view2131297321;
    private View view2131297425;
    private View view2131297426;
    private View view2131297450;
    private View view2131297636;

    @UiThread
    public OneKeyImageVoiceActivity_ViewBinding(OneKeyImageVoiceActivity oneKeyImageVoiceActivity) {
        this(oneKeyImageVoiceActivity, oneKeyImageVoiceActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v41, types: [com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v46, types: [com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$7, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v51, types: [com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$8, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v56, types: [com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$9, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v61, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$10] */
    /* JADX WARNING: type inference failed for: r0v66, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$11] */
    /* JADX WARNING: type inference failed for: r0v77, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyImageVoiceActivity_ViewBinding$12] */
    @UiThread
    public OneKeyImageVoiceActivity_ViewBinding(final OneKeyImageVoiceActivity oneKeyImageVoiceActivity, View view) {
        this.target = oneKeyImageVoiceActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296323, "field 'addVoiceLayout' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.addVoiceLayout = (LinearLayout) Utils.castView(findRequiredView, 2131296323, "field 'addVoiceLayout'", LinearLayout.class);
        this.view2131296323 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297321, "field 'selectVoice' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.selectVoice = (ImageView) Utils.castView(findRequiredView3, 2131297321, "field 'selectVoice'", ImageView.class);
        this.view2131297321 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.navBack = (LinearLayout) Utils.castView(findRequiredView4, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        oneKeyImageVoiceActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.startWx = (Button) Utils.castView(findRequiredView5, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        oneKeyImageVoiceActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyImageVoiceActivity.text = (TextView) Utils.findRequiredViewAsType(view, 2131297461, "field 'text'", TextView.class);
        oneKeyImageVoiceActivity.text2 = (TextView) Utils.findRequiredViewAsType(view, 2131297462, "field 'text2'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296319, "field 'addCoverLayout' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.addCoverLayout = (LinearLayout) Utils.castView(findRequiredView6, 2131296319, "field 'addCoverLayout'", LinearLayout.class);
        this.view2131296319 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297308, "field 'selectCover' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.selectCover = (ImageView) Utils.castView(findRequiredView7, 2131297308, "field 'selectCover'", ImageView.class);
        this.view2131297308 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131296505, "field 'cleanCover' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.cleanCover = (LinearLayout) Utils.castView(findRequiredView8, 2131296505, "field 'cleanCover'", LinearLayout.class);
        this.view2131296505 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131297147, "field 'playVoice' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.playVoice = (Button) Utils.castView(findRequiredView9, 2131297147, "field 'playVoice'", Button.class);
        this.view2131297147 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, 2131297450, "field 'switchVoice' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.switchVoice = (Button) Utils.castView(findRequiredView10, 2131297450, "field 'switchVoice'", Button.class);
        this.view2131297450 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, 2131297426, "field 'startWx2' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.startWx2 = (Button) Utils.castView(findRequiredView11, 2131297426, "field 'startWx2'", Button.class);
        this.view2131297426 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
        oneKeyImageVoiceActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        oneKeyImageVoiceActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView12 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyImageVoiceActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView12, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyImageVoiceActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        OneKeyImageVoiceActivity oneKeyImageVoiceActivity = this.target;
        if (oneKeyImageVoiceActivity != null) {
            this.target = null;
            oneKeyImageVoiceActivity.addVoiceLayout = null;
            oneKeyImageVoiceActivity.videoIntroduceLayout = null;
            oneKeyImageVoiceActivity.selectVoice = null;
            oneKeyImageVoiceActivity.navBack = null;
            oneKeyImageVoiceActivity.navTitle = null;
            oneKeyImageVoiceActivity.startWx = null;
            oneKeyImageVoiceActivity.shadowLinearLayout = null;
            oneKeyImageVoiceActivity.text = null;
            oneKeyImageVoiceActivity.text2 = null;
            oneKeyImageVoiceActivity.addCoverLayout = null;
            oneKeyImageVoiceActivity.selectCover = null;
            oneKeyImageVoiceActivity.cleanCover = null;
            oneKeyImageVoiceActivity.playVoice = null;
            oneKeyImageVoiceActivity.switchVoice = null;
            oneKeyImageVoiceActivity.startWx2 = null;
            oneKeyImageVoiceActivity.navRightImg = null;
            oneKeyImageVoiceActivity.navRightText = null;
            oneKeyImageVoiceActivity.navRightLayout = null;
            this.view2131296323.setOnClickListener((View.OnClickListener) null);
            this.view2131296323 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297321.setOnClickListener((View.OnClickListener) null);
            this.view2131297321 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131296319.setOnClickListener((View.OnClickListener) null);
            this.view2131296319 = null;
            this.view2131297308.setOnClickListener((View.OnClickListener) null);
            this.view2131297308 = null;
            this.view2131296505.setOnClickListener((View.OnClickListener) null);
            this.view2131296505 = null;
            this.view2131297147.setOnClickListener((View.OnClickListener) null);
            this.view2131297147 = null;
            this.view2131297450.setOnClickListener((View.OnClickListener) null);
            this.view2131297450 = null;
            this.view2131297426.setOnClickListener((View.OnClickListener) null);
            this.view2131297426 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
