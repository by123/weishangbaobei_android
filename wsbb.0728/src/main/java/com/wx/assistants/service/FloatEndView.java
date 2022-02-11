package com.wx.assistants.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class FloatEndView extends LinearLayout {
    public int height;
    private LinearLayout mLinearLayout;
    public int width;

    public FloatEndView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatEndView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatEndView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(2131427698, this);
        this.mLinearLayout = (LinearLayout) findViewById(2131296636);
        this.width = this.mLinearLayout.getLayoutParams().width;
        this.height = this.mLinearLayout.getLayoutParams().height;
    }
}
