package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.blankj.utilcode.util.SizeUtils;

public class CustomRadioTwoLayout extends LinearLayout {
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

    public CustomRadioTwoLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRadioTwoLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CustomRadioTwoLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427746, this, true);
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
                        if (CustomRadioTwoLayout.this.listener != null) {
                            CustomRadioTwoLayout.this.listener.mode(0);
                            return;
                        }
                        return;
                    case 2131296497:
                        if (CustomRadioTwoLayout.this.listener != null) {
                            CustomRadioTwoLayout.this.listener.mode(1);
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
        } else if (i == 1 && this.circulateRB2 != null) {
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

    public void setText(String str, String str2, int i, String str3, int i2) {
        if (str != null) {
            this.customLeftText.setText(str);
        }
        if (str2 != null) {
            this.circulateRB1.setText(str2);
            if (i != 0) {
                this.circulateRB1.setWidth(SizeUtils.dp2px((float) i));
            }
        }
        if (str3 != null) {
            this.circulateRB2.setText(str3);
            if (i2 != 0) {
                this.circulateRB2.setWidth(SizeUtils.dp2px((float) i2));
            }
        }
    }

    public void setText(String str, String str2, String str3) {
        if (str != null) {
            this.customLeftText.setText(str);
        }
        if (str2 != null) {
            this.circulateRB1.setText(str2);
        }
        if (str3 != null) {
            this.circulateRB2.setText(str3);
        }
    }
}
