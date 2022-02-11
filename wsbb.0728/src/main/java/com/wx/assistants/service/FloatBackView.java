package com.wx.assistants.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

class FloatBackView extends LinearLayout {
    public int height;
    private LinearLayout mLinearLayout;
    public int width;

    public FloatBackView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatBackView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatBackView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(2131427694, this);
        this.mLinearLayout = (LinearLayout) findViewById(2131296367);
        this.width = this.mLinearLayout.getLayoutParams().width;
        this.height = this.mLinearLayout.getLayoutParams().height;
    }
}
