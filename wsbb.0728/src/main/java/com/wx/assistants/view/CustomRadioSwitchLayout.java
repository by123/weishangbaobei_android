package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class CustomRadioSwitchLayout extends LinearLayout {
    private TextView customLeftText;
    /* access modifiers changed from: private */
    public OnModelSelectListener listener;
    private Context mContext;
    private Switch switchButton;

    public interface OnModelSelectListener {
        void mode(int i);
    }

    public CustomRadioSwitchLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRadioSwitchLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CustomRadioSwitchLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427744, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.customLeftText = (TextView) findViewById(2131296565);
        this.switchButton = (Switch) findViewById(2131297447);
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (CustomRadioSwitchLayout.this.listener != null) {
                        CustomRadioSwitchLayout.this.listener.mode(0);
                    }
                } else if (CustomRadioSwitchLayout.this.listener != null) {
                    CustomRadioSwitchLayout.this.listener.mode(1);
                }
            }
        });
    }

    public void setDefaultSelect(int i) {
        if (i == 0) {
            if (this.switchButton != null) {
                this.switchButton.setChecked(true);
                if (this.listener != null) {
                    this.listener.mode(0);
                }
            }
        } else if (this.switchButton != null) {
            this.switchButton.setChecked(false);
            if (this.listener != null) {
                this.listener.mode(1);
            }
        }
    }

    public void setOnModelSelectListener(OnModelSelectListener onModelSelectListener) {
        if (onModelSelectListener != null) {
            this.listener = onModelSelectListener;
        }
    }

    public void setText(String str) {
        if (str != null) {
            this.customLeftText.setText(str);
        }
    }
}
