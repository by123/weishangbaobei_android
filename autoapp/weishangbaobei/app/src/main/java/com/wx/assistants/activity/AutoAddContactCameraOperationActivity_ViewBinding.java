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
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FFModelLayout;
import com.wx.assistants.view.RemarkFriendLayout;
import com.wx.assistants.view.SingleLabelSelectLayout;
import com.wx.assistants.view.TagCloudLayout;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class AutoAddContactCameraOperationActivity_ViewBinding implements Unbinder {
    private AutoAddContactCameraOperationActivity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public AutoAddContactCameraOperationActivity_ViewBinding(AutoAddContactCameraOperationActivity autoAddContactCameraOperationActivity) {
        this(autoAddContactCameraOperationActivity, autoAddContactCameraOperationActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraOperationActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraOperationActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraOperationActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v18, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraOperationActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r1v24, types: [android.view.View$OnClickListener, com.wx.assistants.activity.AutoAddContactCameraOperationActivity_ViewBinding$5] */
    @UiThread
    public AutoAddContactCameraOperationActivity_ViewBinding(final AutoAddContactCameraOperationActivity autoAddContactCameraOperationActivity, View view) {
        this.target = autoAddContactCameraOperationActivity;
        autoAddContactCameraOperationActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        autoAddContactCameraOperationActivity.sayContent = (EditText) Utils.findRequiredViewAsType(view, 2131297278, "field 'sayContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        autoAddContactCameraOperationActivity.startWx = (Button) Utils.castView(findRequiredView, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraOperationActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduce' and method 'onViewClicked'");
        autoAddContactCameraOperationActivity.videoIntroduce = (LinearLayout) Utils.castView(findRequiredView2, 2131297636, "field 'videoIntroduce'", LinearLayout.class);
        this.view2131297636 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraOperationActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        autoAddContactCameraOperationActivity.navBack = (LinearLayout) Utils.castView(findRequiredView3, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraOperationActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraOperationActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        autoAddContactCameraOperationActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView4, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraOperationActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraOperationActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        autoAddContactCameraOperationActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        autoAddContactCameraOperationActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView5, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                autoAddContactCameraOperationActivity.onViewClicked(view);
            }
        });
        autoAddContactCameraOperationActivity.shadowLinearLayout = (ShadowLinearLayout) Utils.findRequiredViewAsType(view, 2131297363, "field 'shadowLinearLayout'", ShadowLinearLayout.class);
        autoAddContactCameraOperationActivity.executeRemarkLayout = (RemarkFriendLayout) Utils.findRequiredViewAsType(view, 2131296646, "field 'executeRemarkLayout'", RemarkFriendLayout.class);
        autoAddContactCameraOperationActivity.singleSelectLabelLayout = (SingleLabelSelectLayout) Utils.findRequiredViewAsType(view, 2131297386, "field 'singleSelectLabelLayout'", SingleLabelSelectLayout.class);
        autoAddContactCameraOperationActivity.ffModelLayout = (FFModelLayout) Utils.findRequiredViewAsType(view, 2131296660, "field 'ffModelLayout'", FFModelLayout.class);
        autoAddContactCameraOperationActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        AutoAddContactCameraOperationActivity autoAddContactCameraOperationActivity = this.target;
        if (autoAddContactCameraOperationActivity != null) {
            this.target = null;
            autoAddContactCameraOperationActivity.flowViewGroup = null;
            autoAddContactCameraOperationActivity.sayContent = null;
            autoAddContactCameraOperationActivity.startWx = null;
            autoAddContactCameraOperationActivity.videoIntroduce = null;
            autoAddContactCameraOperationActivity.navBack = null;
            autoAddContactCameraOperationActivity.navTitle = null;
            autoAddContactCameraOperationActivity.cleanEditText = null;
            autoAddContactCameraOperationActivity.executeTimeSpaceLayout = null;
            autoAddContactCameraOperationActivity.navRightText = null;
            autoAddContactCameraOperationActivity.navRightLayout = null;
            autoAddContactCameraOperationActivity.shadowLinearLayout = null;
            autoAddContactCameraOperationActivity.executeRemarkLayout = null;
            autoAddContactCameraOperationActivity.singleSelectLabelLayout = null;
            autoAddContactCameraOperationActivity.ffModelLayout = null;
            autoAddContactCameraOperationActivity.navRightImg = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
