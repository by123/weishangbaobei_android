package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class SendOrderLayout extends LinearLayout {
    private TextView leftText;
    /* access modifiers changed from: private */
    public OnSendOrderListener listener;
    private Context mContext;
    private Switch switchButton;

    public interface OnSendOrderListener {
        void sendOrder(int i);
    }

    public SendOrderLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SendOrderLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SendOrderLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427774, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.switchButton = (Switch) findViewById(2131297447);
        this.leftText = (TextView) findViewById(2131296923);
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (SendOrderLayout.this.listener != null) {
                        SendOrderLayout.this.listener.sendOrder(0);
                    }
                } else if (SendOrderLayout.this.listener != null) {
                    SendOrderLayout.this.listener.sendOrder(1);
                }
            }
        });
    }

    public void setLeftText(String str) {
        if (str != null && !"".equals(str)) {
            this.leftText.setText(str);
        }
    }

    public void setOnSendOrderListener(OnSendOrderListener onSendOrderListener) {
        if (onSendOrderListener != null) {
            this.listener = onSendOrderListener;
        }
    }

    public void setSendOrder(int i) {
        if (i == 0) {
            this.switchButton.setChecked(true);
        } else {
            this.switchButton.setChecked(false);
        }
    }
}
