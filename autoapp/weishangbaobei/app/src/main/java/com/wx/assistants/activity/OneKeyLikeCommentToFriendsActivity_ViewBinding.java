package com.wx.assistants.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wx.assistants.view.CustomRadioLayoutBig;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.ExecuteTimeSpaceLayout;
import com.wx.assistants.view.FriendLikeCommentLayout;
import com.wx.assistants.view.TagCloudLayout;

public class OneKeyLikeCommentToFriendsActivity_ViewBinding implements Unbinder {
    private OneKeyLikeCommentToFriendsActivity target;
    private View view2131296507;
    private View view2131296838;
    private View view2131296841;
    private View view2131297049;
    private View view2131297052;
    private View view2131297425;
    private View view2131297636;

    @UiThread
    public OneKeyLikeCommentToFriendsActivity_ViewBinding(OneKeyLikeCommentToFriendsActivity oneKeyLikeCommentToFriendsActivity) {
        this(oneKeyLikeCommentToFriendsActivity, oneKeyLikeCommentToFriendsActivity.getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.view.View$OnClickListener, com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r1v14, types: [com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$3, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v18, types: [com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$4, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v23, types: [com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$5, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v30, types: [com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$6, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v35, types: [com.wx.assistants.activity.OneKeyLikeCommentToFriendsActivity_ViewBinding$7, android.view.View$OnClickListener] */
    @UiThread
    public OneKeyLikeCommentToFriendsActivity_ViewBinding(final OneKeyLikeCommentToFriendsActivity oneKeyLikeCommentToFriendsActivity, View view) {
        this.target = oneKeyLikeCommentToFriendsActivity;
        oneKeyLikeCommentToFriendsActivity.editLeavingMessage = (EditTextWithScrollView) Utils.findRequiredViewAsType(view, 2131296616, "field 'editLeavingMessage'", EditTextWithScrollView.class);
        oneKeyLikeCommentToFriendsActivity.editLayout = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296615, "field 'editLayout'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, 2131297049, "field 'navBack' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.navBack = (LinearLayout) Utils.castView(findRequiredView, 2131297049, "field 'navBack'", LinearLayout.class);
        this.view2131297049 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        oneKeyLikeCommentToFriendsActivity.navTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297054, "field 'navTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, 2131297425, "field 'startWx' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.startWx = (Button) Utils.castView(findRequiredView2, 2131297425, "field 'startWx'", Button.class);
        this.view2131297425 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131297636, "field 'videoIntroduceLayout' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.videoIntroduceLayout = (LinearLayout) Utils.castView(findRequiredView3, 2131297636, "field 'videoIntroduceLayout'", LinearLayout.class);
        this.view2131297636 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297052, "field 'navRightLayout' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.navRightLayout = (LinearLayout) Utils.castView(findRequiredView4, 2131297052, "field 'navRightLayout'", LinearLayout.class);
        this.view2131297052 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        oneKeyLikeCommentToFriendsActivity.navRightImg = (ImageView) Utils.findRequiredViewAsType(view, 2131297051, "field 'navRightImg'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, 2131296507, "field 'cleanEditText' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.cleanEditText = (LinearLayout) Utils.castView(findRequiredView5, 2131296507, "field 'cleanEditText'", LinearLayout.class);
        this.view2131296507 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        oneKeyLikeCommentToFriendsActivity.executeTimeSpaceLayout = (ExecuteTimeSpaceLayout) Utils.findRequiredViewAsType(view, 2131296647, "field 'executeTimeSpaceLayout'", ExecuteTimeSpaceLayout.class);
        oneKeyLikeCommentToFriendsActivity.friendSendModeLayout = (FriendLikeCommentLayout) Utils.findRequiredViewAsType(view, 2131296717, "field 'friendSendModeLayout'", FriendLikeCommentLayout.class);
        oneKeyLikeCommentToFriendsActivity.startDate = (TextView) Utils.findRequiredViewAsType(view, 2131297410, "field 'startDate'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, 2131296841, "field 'ivStartDate' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.ivStartDate = (LinearLayout) Utils.castView(findRequiredView6, 2131296841, "field 'ivStartDate'", LinearLayout.class);
        this.view2131296841 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        oneKeyLikeCommentToFriendsActivity.endDate = (TextView) Utils.findRequiredViewAsType(view, 2131296634, "field 'endDate'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, 2131296838, "field 'ivFinishDate' and method 'onViewClicked'");
        oneKeyLikeCommentToFriendsActivity.ivFinishDate = (LinearLayout) Utils.castView(findRequiredView7, 2131296838, "field 'ivFinishDate'", LinearLayout.class);
        this.view2131296838 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oneKeyLikeCommentToFriendsActivity.onViewClicked(view);
            }
        });
        oneKeyLikeCommentToFriendsActivity.flowViewCategory = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296680, "field 'flowViewCategory'", TagCloudLayout.class);
        oneKeyLikeCommentToFriendsActivity.modelLayout2 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131297028, "field 'modelLayout2'", LinearLayout.class);
        oneKeyLikeCommentToFriendsActivity.flowViewGroup = (TagCloudLayout) Utils.findRequiredViewAsType(view, 2131296681, "field 'flowViewGroup'", TagCloudLayout.class);
        oneKeyLikeCommentToFriendsActivity.customRadioLayoutBig = (CustomRadioLayoutBig) Utils.findRequiredViewAsType(view, 2131296568, "field 'customRadioLayoutBig'", CustomRadioLayoutBig.class);
        oneKeyLikeCommentToFriendsActivity.navRightText = (TextView) Utils.findRequiredViewAsType(view, 2131297053, "field 'navRightText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        OneKeyLikeCommentToFriendsActivity oneKeyLikeCommentToFriendsActivity = this.target;
        if (oneKeyLikeCommentToFriendsActivity != null) {
            this.target = null;
            oneKeyLikeCommentToFriendsActivity.editLeavingMessage = null;
            oneKeyLikeCommentToFriendsActivity.editLayout = null;
            oneKeyLikeCommentToFriendsActivity.navBack = null;
            oneKeyLikeCommentToFriendsActivity.navTitle = null;
            oneKeyLikeCommentToFriendsActivity.startWx = null;
            oneKeyLikeCommentToFriendsActivity.videoIntroduceLayout = null;
            oneKeyLikeCommentToFriendsActivity.navRightLayout = null;
            oneKeyLikeCommentToFriendsActivity.navRightImg = null;
            oneKeyLikeCommentToFriendsActivity.cleanEditText = null;
            oneKeyLikeCommentToFriendsActivity.executeTimeSpaceLayout = null;
            oneKeyLikeCommentToFriendsActivity.friendSendModeLayout = null;
            oneKeyLikeCommentToFriendsActivity.startDate = null;
            oneKeyLikeCommentToFriendsActivity.ivStartDate = null;
            oneKeyLikeCommentToFriendsActivity.endDate = null;
            oneKeyLikeCommentToFriendsActivity.ivFinishDate = null;
            oneKeyLikeCommentToFriendsActivity.flowViewCategory = null;
            oneKeyLikeCommentToFriendsActivity.modelLayout2 = null;
            oneKeyLikeCommentToFriendsActivity.flowViewGroup = null;
            oneKeyLikeCommentToFriendsActivity.customRadioLayoutBig = null;
            oneKeyLikeCommentToFriendsActivity.navRightText = null;
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
            this.view2131296841.setOnClickListener((View.OnClickListener) null);
            this.view2131296841 = null;
            this.view2131296838.setOnClickListener((View.OnClickListener) null);
            this.view2131296838 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
