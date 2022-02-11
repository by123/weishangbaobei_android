package com.wx.assistants.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wx.assistants.listener.ReShowingListener;

public class ShowingPage extends LinearLayout {
    private ImageView loadingImg;
    private Context mContext;
    private ReShowingListener reShowingListener;

    public ShowingPage(Context context) {
        super(context);
        this.mContext = context;
    }

    public ShowingPage(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initShowPageLayout(context);
    }

    public ShowingPage(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initShowPageLayout(Context context) {
        LayoutInflater.from(context).inflate(2131427705, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.loadingImg = (ImageView) findViewById(2131296977);
        this.loadingImg.setImageResource(2131231038);
        ((AnimationDrawable) this.loadingImg.getDrawable()).start();
    }

    public void setShowingPagerListener(ReShowingListener reShowingListener2) {
        this.reShowingListener = reShowingListener2;
    }
}
