package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CustomRadioLayoutBig extends LinearLayout {
    private RadioButton circulateRB1;
    private RadioButton circulateRB2;
    private RadioGroup circulateRG;
    private TextView customLeftText;
    /* access modifiers changed from: private */
    public OnModelSelectListener listener;
    private Context mContext;

    public interface OnModelSelectListener {
        void mode(int i);
    }

    public CustomRadioLayoutBig(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRadioLayoutBig(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CustomRadioLayoutBig(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427743, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.customLeftText = (TextView) findViewById(2131296565);
        this.circulateRB1 = (RadioButton) findViewById(2131296496);
        this.circulateRB2 = (RadioButton) findViewById(2131296497);
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        if (CustomRadioLayoutBig.this.listener != null) {
                            CustomRadioLayoutBig.this.listener.mode(0);
                            return;
                        }
                        return;
                    case 2131296497:
                        if (CustomRadioLayoutBig.this.listener != null) {
                            CustomRadioLayoutBig.this.listener.mode(1);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void setDefaultSelect(int i) {
        if (i == 0) {
            if (this.circulateRB1 != null) {
                this.circulateRG.check(2131296496);
                if (this.listener != null) {
                    this.listener.mode(0);
                }
            }
        } else if (this.circulateRB2 != null) {
            this.circulateRG.check(2131296497);
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

    public void setText(String str, String str2, String str3) {
        if (str != null) {
            this.customLeftText.setText(str);
        }
        if (str2 != null) {
            this.circulateRB1.setText(str2);
        }
        if (str2 != null) {
            this.circulateRB2.setText(str3);
        }
    }
}
