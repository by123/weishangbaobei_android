package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.NumSettingOnlyLayout;

public class CleanFriendsActivity_ViewBinding implements Unbinder {
    private CleanFriendsActivity target;
    private View view2131296877;
    private View view2131297049;
    private View view2131297052;
    private View view2131297210;
    private View view2131297211;
    private View view2131297212;
    private View view2131297213;
    private View view2131297214;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public CleanFriendsActivity_ViewBinding(CleanFriendsActivity cleanFriendsActivity) {
        this(cleanFriendsActivity, cleanFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v22, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r0v41, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r0v49, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r0v54, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r0v62, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r0v70, types: [android.view.View$OnClickListener, com.wx.assistants.activity.CleanFriendsActivity_ViewBinding$10] */
    @UiThread
    public CleanFriendsActivity_ViewBinding(final CleanFriendsActivity cleanFriendsActivity, View view) {
        this.target = cleanFriendsActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131297210, "field 'rdoBtnDeleteAll' and method 'onViewClicked'");
        cleanFriendsActivity.rdoBtnDeleteAll = (RadioButton) Utils.castView(findRequiredView, 2131297210, "field 'rdoBtnDeleteAll'", RadioButton.class);
        this.view2131297210 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297212, "field 'rdoBtnDeleteNickname' and method 'onViewClicked'");
        cleanFriendsActivity.rdoBtnDeleteNickname = (RadioButton) Utils.castView(findRequiredView2, 2131297212, "field 'rdoBtnDeleteNickname'", RadioButton.class);
        this.view2131297212 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297211, "field 'rdoBtnDeleteLabel' and method 'onViewClicked'");
        cleanFriendsActivity.rdoBtnDeleteLabel = (RadioButton) Utils.castView(findRequiredView3, 2131297211, "field 'rdoBtnDeleteLabel'", RadioButton.class);
        this.view2131297211 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.radioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, 2131297183, "field 'radioGroup'", RadioGroup.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131297214, "field 'rdoBtnDeleteNickname3' and method 'onViewClicked'");
        cleanFriendsActivity.rdoBtnDeleteNickname3 = (RadioButton) Utils.castView(findRequiredView4, 2131297214, "field 'rdoBtnDeleteNickname3'", RadioButton.class);
        this.view2131297214 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297213, "field 'rdoBtnDeleteNickname2' and method 'onViewClicked'");
        cleanFriendsActivity.rdoBtnDeleteNickname2 = (RadioButton) Utils.castView(findRequiredView5, 2131297213, "field 'rdoBtnDeleteNickname2'", RadioButton.class);
        this.view2131297213 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.layoutNickname = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296896, "field 'layoutNickname'", LinearLayout.class);
        cleanFriendsActivity.layoutLabel = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296895, "field 'layoutLabel'", LinearLayout.class);
        cleanFriendsActivity.editNickName = (EditText) Utils.findRequiredViewAsType(view, 2131296619, "field 'editNickName'", EditText.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        cleanFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView6, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        cleanFriendsActivity.startWx = (Button) Utils.castView(findRequiredView7, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        cleanFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView8, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.jumpLabel = (TextView) Utils.findRequiredViewAsType(view, 2131296873, "field 'jumpLabel'", TextView.class);
        View findRequiredView9 = Utils.findRequiredView(view, 2131296877, "field 'jumpLabelLayout' and method 'onViewClicked'");
        cleanFriendsActivity.jumpLabelLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131296877, "field 'jumpLabelLayout'", LinearLayout.class);
        this.view2131296877 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        cleanFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView10, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleanFriendsActivity.onViewClicked(view);
            }
        });
        cleanFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        cleanFriendsActivity.numSettingOnlyLayout = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingOnlyLayout.class);
    }

    @CallSuper
    public void unbind() {
        CleanFriendsActivity cleanFriendsActivity = this.target;
        if (cleanFriendsActivity != null) {
            this.target = null;
            cleanFriendsActivity.rdoBtnDeleteAll = null;
            cleanFriendsActivity.rdoBtnDeleteNickname = null;
            cleanFriendsActivity.rdoBtnDeleteLabel = null;
            cleanFriendsActivity.radioGroup = null;
            cleanFriendsActivity.rdoBtnDeleteNickname3 = null;
            cleanFriendsActivity.rdoBtnDeleteNickname2 = null;
            cleanFriendsActivity.layoutNickname = null;
            cleanFriendsActivity.layoutLabel = null;
            cleanFriendsActivity.editNickName = null;
            cleanFriendsActivity.navBack = null;
            cleanFriendsActivity.navTitle = null;
            cleanFriendsActivity.startWx = null;
            cleanFriendsActivity.videoIntroduceLayout = null;
            cleanFriendsActivity.jumpLabel = null;
            cleanFriendsActivity.jumpLabelLayout = null;
            cleanFriendsActivity.navRightText = null;
            cleanFriendsActivity.navRightLayout = null;
            cleanFriendsActivity.navRightImg = null;
            cleanFriendsActivity.numSettingOnlyLayout = null;
            this.view2131297210.setOnClickListener((View.OnClickListener) null);
            this.view2131297210 = null;
            this.view2131297212.setOnClickListener((View.OnClickListener) null);
            this.view2131297212 = null;
            this.view2131297211.setOnClickListener((View.OnClickListener) null);
            this.view2131297211 = null;
            this.view2131297214.setOnClickListener((View.OnClickListener) null);
            this.view2131297214 = null;
            this.view2131297213.setOnClickListener((View.OnClickListener) null);
            this.view2131297213 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296877.setOnClickListener((View.OnClickListener) null);
            this.view2131296877 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
