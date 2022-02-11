package com.meiqia.meiqiasdk.pw;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public abstract class MQBasePopupWindow extends PopupWindow implements View.OnClickListener {
    protected Activity mActivity;
    protected View mAnchorView;
    protected View mWindowRootView;

    /* access modifiers changed from: protected */
    public abstract void initView();

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract void processLogic();

    /* access modifiers changed from: protected */
    public abstract void setListener();

    public abstract void show();

    public MQBasePopupWindow(Activity activity, @LayoutRes int i, View view, int i2, int i3) {
        super(View.inflate(activity, i, (ViewGroup) null), i2, i3, true);
        init(activity, view);
        initView();
        setListener();
        processLogic();
    }

    private void init(Activity activity, View view) {
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                MQBasePopupWindow.this.dismiss();
                return true;
            }
        });
        setBackgroundDrawable(new ColorDrawable(0));
        this.mAnchorView = view;
        this.mActivity = activity;
        this.mWindowRootView = activity.getWindow().peekDecorView();
    }

    /* access modifiers changed from: protected */
    public <VT extends View> VT getViewById(@IdRes int i) {
        return getContentView().findViewById(i);
    }
}
