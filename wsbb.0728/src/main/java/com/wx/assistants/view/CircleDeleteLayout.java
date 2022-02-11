package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class CircleDeleteLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public CirculateModelListener listener;
    private Context mContext;
    private Switch switchButton;

    public interface CirculateModelListener {
        void circulateMode(int i);
    }

    public CircleDeleteLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CircleDeleteLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CircleDeleteLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427735, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.switchButton = (Switch) findViewById(2131297447);
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (CircleDeleteLayout.this.listener != null) {
                        CircleDeleteLayout.this.listener.circulateMode(0);
                    }
                } else if (CircleDeleteLayout.this.listener != null) {
                    CircleDeleteLayout.this.listener.circulateMode(1);
                }
            }
        });
    }

    public void setCirculateModelListener(CirculateModelListener circulateModelListener) {
        if (circulateModelListener != null) {
            this.listener = circulateModelListener;
        }
    }
}
