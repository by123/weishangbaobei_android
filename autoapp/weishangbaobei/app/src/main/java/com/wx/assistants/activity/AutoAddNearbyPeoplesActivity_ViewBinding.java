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
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.NumSettingLayout;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;

public class AutoAddNearbyPeoplesActivity_ViewBinding implements Unbinder {
    private AutoAddNearbyPeoplesActivity target;
    private View view2131296507;
    private View view2131296681;
    private View view2131297049;
    private View view2131297052;
    private View view2131297278;
    private View view2131297359;
    private View view2131297360;
    private View view2131297361;
    private View view2131297362;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddNearbyPeoplesActivity_ViewBinding(AutoAddNearbyPeoplesActivity autoAddNearbyPeoplesActivity) {
        this(autoAddNearbyPeoplesActivity, autoAddNearbyPeoplesActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$2, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v15, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v19, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$5] */
    /* JADX WARNING: type inference failed for: r1v23, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$6] */
    /* JADX WARNING: type inference failed for: r1v27, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$7] */
    /* JADX WARNING: type inference failed for: r1v32, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$8] */
    /* JADX WARNING: type inference failed for: r1v36, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$9] */
    /* JADX WARNING: type inference failed for: r1v40, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$10] */
    /* JADX WARNING: type inference failed for: r0v42, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddNearbyPeoplesActivity_ViewBinding$11] */
    @UiThread
    public AutoAddNearbyPeoplesActivity_ViewBinding(final AutoAddNearbyPeoplesActivity autoAddNearbyPeoplesActivity, View view) {
        this.target = autoAddNearbyPeoplesActivity;
        View findRequiredView = Utils.findRequiredView(view, 2131296681, "field 'flowViewGroup' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.flowViewGroup = (TagCloudLayout) Utils.castView(findRequiredView, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        this.view2131296681 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297278, "field 'sayContent' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.sayContent = (EditText) Utils.castView(findRequiredView2, 2131297278, "field 'sayContent'", EditText.class);
        this.view2131297278 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297359, "field 'sexRadioButtonAll' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.sexRadioButtonAll = (RadioButton) Utils.castView(findRequiredView3, 2131297359, "field 'sexRadioButtonAll'", RadioButton.class);
        this.view2131297359 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297360, "field 'sexRadioButtonMan' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.sexRadioButtonMan = (RadioButton) Utils.castView(findRequiredView4, 2131297360, "field 'sexRadioButtonMan'", RadioButton.class);
        this.view2131297360 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, 2131297361, "field 'sexRadioButtonWoman' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.sexRadioButtonWoman = (RadioButton) Utils.castView(findRequiredView5, 2131297361, "field 'sexRadioButtonWoman'", RadioButton.class);
        this.view2131297361 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, 2131297362, "field 'sexRadioGroup' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.sexRadioGroup = (RadioGroup) Utils.castView(findRequiredView6, 2131297362, "field 'sexRadioGroup'", RadioGroup.class);
        this.view2131297362 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.navBack = (LinearLayout) Utils.castView(findRequiredView7, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        autoAddNearbyPeoplesActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView8 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.startWx = (Button) Utils.castView(findRequiredView8, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView9, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddNearbyPeoplesActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView10, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
        autoAddNearbyPeoplesActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddNearbyPeoplesActivity.executeRemarkLayout = (RemarkFriendLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", RemarkFriendLayout.class);
        autoAddNearbyPeoplesActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        autoAddNearbyPeoplesActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddNearbyPeoplesActivity.numberSettingLayout = (NumSettingLayout) Utils.findRequiredViewAsType(view, 2131297082, "field 'numberSettingLayout'", NumSettingLayout.class);
        autoAddNearbyPeoplesActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView11 = Utils.findRequiredView(view, 2131297052, "method 'onViewClicked'");
        this.view2131297052 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddNearbyPeoplesActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AutoAddNearbyPeoplesActivity autoAddNearbyPeoplesActivity = this.target;
        if (autoAddNearbyPeoplesActivity != null) {
            this.target = null;
            autoAddNearbyPeoplesActivity.flowViewGroup = null;
            autoAddNearbyPeoplesActivity.sayContent = null;
            autoAddNearbyPeoplesActivity.sexRadioButtonAll = null;
            autoAddNearbyPeoplesActivity.sexRadioButtonMan = null;
            autoAddNearbyPeoplesActivity.sexRadioButtonWoman = null;
            autoAddNearbyPeoplesActivity.sexRadioGroup = null;
            autoAddNearbyPeoplesActivity.navBack = null;
            autoAddNearbyPeoplesActivity.navTitle = null;
            autoAddNearbyPeoplesActivity.startWx = null;
            autoAddNearbyPeoplesActivity.videoIntroduceLayout = null;
            autoAddNearbyPeoplesActivity.cleanEditText = null;
            autoAddNearbyPeoplesActivity.executeTimeSpaceLayout = null;
            autoAddNearbyPeoplesActivity.executeRemarkLayout = null;
            autoAddNearbyPeoplesActivity.singleSelectLabelLayout = null;
            autoAddNearbyPeoplesActivity.ffModelLayout = null;
            autoAddNearbyPeoplesActivity.numberSettingLayout = null;
            autoAddNearbyPeoplesActivity.navRightImg = null;
            this.view2131296681.setOnClickListener((View.OnClickListener) null);
            this.view2131296681 = null;
            this.view2131297278.setOnClickListener((View.OnClickListener) null);
            this.view2131297278 = null;
            this.view2131297359.setOnClickListener((View.OnClickListener) null);
            this.view2131297359 = null;
            this.view2131297360.setOnClickListener((View.OnClickListener) null);
            this.view2131297360 = null;
            this.view2131297361.setOnClickListener((View.OnClickListener) null);
            this.view2131297361 = null;
            this.view2131297362.setOnClickListener((View.OnClickListener) null);
            this.view2131297362 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
