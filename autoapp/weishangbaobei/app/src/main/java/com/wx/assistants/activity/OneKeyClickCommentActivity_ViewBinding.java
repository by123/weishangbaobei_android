package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.NumSettingOnlyLayout;

public class OneKeyClickCommentActivity_ViewBinding implements Unbinder {
    private OneKeyClickCommentActivity target;
    private View view2131296507;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyClickCommentActivity_ViewBinding(OneKeyClickCommentActivity oneKeyClickCommentActivity) {
        this(oneKeyClickCommentActivity, oneKeyClickCommentActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyClickCommentActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyClickCommentActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v17, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyClickCommentActivity_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r1v21, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyClickCommentActivity_ViewBinding$4] */
    /* JADX WARNING: type inference failed for: r0v33, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyClickCommentActivity_ViewBinding$5] */
    @UiThread
    public OneKeyClickCommentActivity_ViewBinding(final OneKeyClickCommentActivity oneKeyClickCommentActivity, View view) {
        this.target = oneKeyClickCommentActivity;
        oneKeyClickCommentActivity.clickSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296514, "field 'clickSwitchBtn'", Switch.class);
        oneKeyClickCommentActivity.commentSwitchBtn = (Switch) Utils.findRequiredViewAsType(view, 2131296522, "field 'commentSwitchBtn'", Switch.class);
        oneKeyClickCommentActivity.commentLayout = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296520, "field 'commentLayout'", FrameLayout.class);
        oneKeyClickCommentActivity.numSettingOnlyLayout = (NumSettingOnlyLayout) Utils.findRequiredViewAsType(view, 2131297078, "field 'numSettingOnlyLayout'", NumSettingOnlyLayout.class);
        oneKeyClickCommentActivity.edCommentContent = (EditText) Utils.findRequiredViewAsType(view, 2131296612, "field 'edCommentContent'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyClickCommentActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyClickCommentActivity.onViewClicked(view);
            }
        });
        oneKeyClickCommentActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyClickCommentActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyClickCommentActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyClickCommentActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyClickCommentActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyClickCommentActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyClickCommentActivity.onViewClicked(view);
            }
        });
        oneKeyClickCommentActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        oneKeyClickCommentActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView5, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyClickCommentActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        OneKeyClickCommentActivity oneKeyClickCommentActivity = this.target;
        if (oneKeyClickCommentActivity != null) {
            this.target = null;
            oneKeyClickCommentActivity.clickSwitchBtn = null;
            oneKeyClickCommentActivity.commentSwitchBtn = null;
            oneKeyClickCommentActivity.commentLayout = null;
            oneKeyClickCommentActivity.numSettingOnlyLayout = null;
            oneKeyClickCommentActivity.edCommentContent = null;
            oneKeyClickCommentActivity.navBack = null;
            oneKeyClickCommentActivity.navTitle = null;
            oneKeyClickCommentActivity.startWx = null;
            oneKeyClickCommentActivity.videoIntroduceLayout = null;
            oneKeyClickCommentActivity.navRightLayout = null;
            oneKeyClickCommentActivity.navRightImg = null;
            oneKeyClickCommentActivity.cleanEditText = null;
            this.view2131297049.setOnClickListener((View.OnClickListener) null);
            this.view2131297049 = null;
            this.view2131297425.setOnClickListener((View.OnClickListener) null);
            this.view2131297425 = null;
            this.view2131297636.setOnClickListener((View.OnClickListener) null);
            this.view2131297636 = null;
            this.view2131297052.setOnClickListener((View.OnClickListener) null);
            this.view2131297052 = null;
            this.view2131296507.setOnClickListener((View.OnClickListener) null);
            this.view2131296507 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
