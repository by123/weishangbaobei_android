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
import com.wx.assistants.view.AutoLinkStyleTextView;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class PassiveAddCardActivity_ViewBinding implements Unbinder {
    private PassiveAddCardActivity target;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public PassiveAddCardActivity_ViewBinding(PassiveAddCardActivity passiveAddCardActivity) {
        this(passiveAddCardActivity, passiveAddCardActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.PassiveAddCardActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PassiveAddCardActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.wx.assistants.activity.PassiveAddCardActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.PassiveAddCardActivity_ViewBinding$4] */
    @UiThread
    public PassiveAddCardActivity_ViewBinding(final PassiveAddCardActivity passiveAddCardActivity, View view) {
        this.target = passiveAddCardActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        passiveAddCardActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveAddCardActivity.onViewClicked(view);
            }
        });
        passiveAddCardActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        passiveAddCardActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveAddCardActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        passiveAddCardActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveAddCardActivity.onViewClicked(view);
            }
        });
        passiveAddCardActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        passiveAddCardActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                passiveAddCardActivity.onViewClicked(view);
            }
        });
        passiveAddCardActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        passiveAddCardActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        passiveAddCardActivity.numText = (TextView) Utils.findRequiredViewAsType(view, 2131297079, "field 'numText'", TextView.class);
        passiveAddCardActivity.editLeavingMessage = (EditText) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditText.class);
        passiveAddCardActivity.autoLinkStyleTextView = (AutoLinkStyleTextView) Utils.findRequiredViewAsType(view, 2131296363, "field 'autoLinkStyleTextView'", AutoLinkStyleTextView.class);
    }

    @CallSuper
    public void unbind() {
        PassiveAddCardActivity passiveAddCardActivity = this.target;
        if (passiveAddCardActivity != null) {
            this.target = null;
            passiveAddCardActivity.navBack = null;
            passiveAddCardActivity.navTitle = null;
            passiveAddCardActivity.startWx = null;
            passiveAddCardActivity.videoIntroduceLayout = null;
            passiveAddCardActivity.navRightImg = null;
            passiveAddCardActivity.navRightLayout = null;
            passiveAddCardActivity.navRightText = null;
            passiveAddCardActivity.shadowLinearLayout = null;
            passiveAddCardActivity.numText = null;
            passiveAddCardActivity.editLeavingMessage = null;
            passiveAddCardActivity.autoLinkStyleTextView = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
