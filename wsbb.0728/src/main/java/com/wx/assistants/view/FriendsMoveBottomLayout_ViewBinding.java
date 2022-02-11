package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class FriendsMoveBottomLayout_ViewBinding implements Unbinder {
    private FriendsMoveBottomLayout target;
    private View view2131296361;
    private View view2131296503;
    private View view2131296504;
    private View view2131297218;

    @UiThread
    public FriendsMoveBottomLayout_ViewBinding(FriendsMoveBottomLayout friendsMoveBottomLayout) {
        this(friendsMoveBottomLayout, friendsMoveBottomLayout);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View$OnClickListener, com.wx.assistants.view.FriendsMoveBottomLayout_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View$OnClickListener, com.wx.assistants.view.FriendsMoveBottomLayout_ViewBinding$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [android.view.View$OnClickListener, com.wx.assistants.view.FriendsMoveBottomLayout_ViewBinding$3] */
    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.View$OnClickListener, com.wx.assistants.view.FriendsMoveBottomLayout_ViewBinding$4] */
    @UiThread
    public FriendsMoveBottomLayout_ViewBinding(final FriendsMoveBottomLayout friendsMoveBottomLayout, View view) {
        this.target = friendsMoveBottomLayout;
        View findRequiredView = Utils.findRequiredView(view, 2131296504, "field 'cleanAllBtn' and method 'onViewClicked'");
        friendsMoveBottomLayout.cleanAllBtn = (Button) Utils.castView(findRequiredView, 2131296504, "field 'cleanAllBtn'", Button.class);
        this.view2131296504 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                friendsMoveBottomLayout.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296503, "field 'cleanAddedBtn' and method 'onViewClicked'");
        friendsMoveBottomLayout.cleanAddedBtn = (Button) Utils.castView(findRequiredView2, 2131296503, "field 'cleanAddedBtn'", Button.class);
        this.view2131296503 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                friendsMoveBottomLayout.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, 2131296361, "field 'autoCopy' and method 'onViewClicked'");
        friendsMoveBottomLayout.autoCopy = (Button) Utils.castView(findRequiredView3, 2131296361, "field 'autoCopy'", Button.class);
        this.view2131296361 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                friendsMoveBottomLayout.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, 2131297218, "field 'readFile' and method 'onViewClicked'");
        friendsMoveBottomLayout.readFile = (Button) Utils.castView(findRequiredView4, 2131297218, "field 'readFile'", Button.class);
        this.view2131297218 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                friendsMoveBottomLayout.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        FriendsMoveBottomLayout friendsMoveBottomLayout = this.target;
        if (friendsMoveBottomLayout != null) {
            this.target = null;
            friendsMoveBottomLayout.cleanAllBtn = null;
            friendsMoveBottomLayout.cleanAddedBtn = null;
            friendsMoveBottomLayout.autoCopy = null;
            friendsMoveBottomLayout.readFile = null;
            this.view2131296504.setOnClickListener((View.OnClickListener) null);
            this.view2131296504 = null;
            this.view2131296503.setOnClickListener((View.OnClickListener) null);
            this.view2131296503 = null;
            this.view2131296361.setOnClickListener((View.OnClickListener) null);
            this.view2131296361 = null;
            this.view2131297218.setOnClickListener((View.OnClickListener) null);
            this.view2131297218 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
