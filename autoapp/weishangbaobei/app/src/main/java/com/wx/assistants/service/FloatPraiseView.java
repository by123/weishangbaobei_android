package com.wx.assistants.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

class FloatPraiseView extends LinearLayout {
    public int height;
    private LinearLayout mLinearLayout;
    public int width;

    public FloatPraiseView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatPraiseView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatPraiseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(2131427700, this);
        this.mLinearLayout = (LinearLayout) findViewById(2131297153);
        this.width = this.mLinearLayout.getLayoutParams().width;
        this.height = this.mLinearLayout.getLayoutParams().height;
    }
}
