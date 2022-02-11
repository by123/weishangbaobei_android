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

public class OneKeyChangeVideoCoverActivity_ViewBinding implements Unbinder {
    private OneKeyChangeVideoCoverActivity target;
    private View view2131296319;
    private View view2131296322;
    private View view2131296505;
    private View view2131296511;
    private View view2131297049;
    private View view2131297052;
    private View view2131297308;
    private View view2131297320;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyChangeVideoCoverActivity_ViewBinding(OneKeyChangeVideoCoverActivity oneKeyChangeVideoCoverActivity) {
        this(oneKeyChangeVideoCoverActivity, oneKeyChangeVideoCoverActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v24, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v32, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v46, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v51, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v56, types: [com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$9, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v67, types: [com.wx.assistants.activity.OneKeyChangeVideoCoverActivity_ViewBinding$10, android.view.View$OnClickListener] */
    @UiThread
    public OneKeyChangeVideoCoverActivity_ViewBinding(final OneKeyChangeVideoCoverActivity oneKeyChangeVideoCoverActivity, View view) {
        this.target = oneKeyChangeVideoCoverActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296322, "field 'addVideoLayout' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.addVideoLayout = (LinearLayout) Utils.castView(findRequiredView, 2131296322, "field 'addVideoLayout'", LinearLayout.class);
        this.view2131296322 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297320, "field 'selectVideo' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.selectVideo = (ImageView) Utils.castView(findRequiredView3, 2131297320, "field 'selectVideo'", ImageView.class);
        this.view2131297320 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131296511, "field 'cleanVideo' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.cleanVideo = (LinearLayout) Utils.castView(findRequiredView4, 2131296511, "field 'cleanVideo'", LinearLayout.class);
        this.view2131296511 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.navBack = (LinearLayout) Utils.castView(findRequiredView5, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        oneKeyChangeVideoCoverActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.startWx = (Button) Utils.castView(findRequiredView6, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        oneKeyChangeVideoCoverActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        oneKeyChangeVideoCoverActivity.text = (TextView) Utils.findRequiredViewAsType(view, 2131297461, "field 'text'", TextView.class);
        oneKeyChangeVideoCoverActivity.text2 = (TextView) Utils.findRequiredViewAsType(view, 2131297462, "field 'text2'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296319, "field 'addCoverLayout' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.addCoverLayout = (LinearLayout) Utils.castView(findRequiredView7, 2131296319, "field 'addCoverLayout'", LinearLayout.class);
        this.view2131296319 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131297308, "field 'selectCover' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.selectCover = (ImageView) Utils.castView(findRequiredView8, 2131297308, "field 'selectCover'", ImageView.class);
        this.view2131297308 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131296505, "field 'cleanCover' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.cleanCover = (LinearLayout) Utils.castView(findRequiredView9, 2131296505, "field 'cleanCover'", LinearLayout.class);
        this.view2131296505 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
        oneKeyChangeVideoCoverActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        oneKeyChangeVideoCoverActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyChangeVideoCoverActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView10, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyChangeVideoCoverActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        OneKeyChangeVideoCoverActivity oneKeyChangeVideoCoverActivity = this.target;
        if (oneKeyChangeVideoCoverActivity != null) {
            this.target = null;
            oneKeyChangeVideoCoverActivity.addVideoLayout = null;
            oneKeyChangeVideoCoverActivity.videoIntroduceLayout = null;
            oneKeyChangeVideoCoverActivity.selectVideo = null;
            oneKeyChangeVideoCoverActivity.cleanVideo = null;
            oneKeyChangeVideoCoverActivity.navBack = null;
            oneKeyChangeVideoCoverActivity.navTitle = null;
            oneKeyChangeVideoCoverActivity.startWx = null;
            oneKeyChangeVideoCoverActivity.shadowLinearLayout = null;
            oneKeyChangeVideoCoverActivity.text = null;
            oneKeyChangeVideoCoverActivity.text2 = null;
            oneKeyChangeVideoCoverActivity.addCoverLayout = null;
            oneKeyChangeVideoCoverActivity.selectCover = null;
            oneKeyChangeVideoCoverActivity.cleanCover = null;
            oneKeyChangeVideoCoverActivity.navRightImg = null;
            oneKeyChangeVideoCoverActivity.navRightText = null;
            oneKeyChangeVideoCoverActivity.navRightLayout = null;
            this.view2131296322.setOnClickListener((View.OnClickListener) null);
            this.view2131296322 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297320.setOnClickListener((View.OnClickListener) null);
            this.view2131297320 = null;
            this.view2131296511.setOnClickListener((View.OnClickListener) null);
            this.view2131296511 = null;
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
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
