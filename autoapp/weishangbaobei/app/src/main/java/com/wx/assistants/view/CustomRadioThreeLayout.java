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

public class CustomRadioThreeLayout extends LinearLayout {
    private RadioButton circulateRB1;
    private RadioButton circulateRB2;
    private RadioButton circulateRB3;
    private RadioGroup circulateRG;
    private TextView customLeftText;
    /* access modifiers changed from: private */
    public OnModelSelectListener listener;
    private Context mContext;

    public interface OnModelSelectListener {
        void mode(int i);
    }

    public void setOnModelSelectListener(OnModelSelectListener onModelSelectListener) {
        if (onModelSelectListener != null) {
            this.listener = onModelSelectListener;
        }
    }

    public CustomRadioThreeLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRadioThreeLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CustomRadioThreeLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427745, this, true);
    }

    public void setText(String str, String str2, String str3, String str4) {
        if (str != null) {
            this.customLeftText.setText(str);
        }
        if (str2 != null) {
            this.circulateRB1.setText(str2);
        }
        if (str3 != null) {
            this.circulateRB2.setText(str3);
        }
        if (str4 != null) {
            this.circulateRB3.setText(str4);
        }
    }

    public void setText(String str, String str2, int i, String str3, int i2, String str4, int i3) {
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
        if (str4 != null) {
            this.circulateRB3.setText(str4);
            if (i3 != 0) {
                this.circulateRB3.setWidth(SizeUtils.dp2px((float) i3));
            }
        }
    }

    public void setDefaultSelect(int i) {
        if (i == 0) {
            if (this.circulateRB1 != null) {
                this.circulateRG.check(2131296496);
                if (this.listener != null) {
                    this.listener.mode(0);
                }
            }
        } else if (i == 1) {
            if (this.circulateRB2 != null) {
                this.circulateRG.check(2131296497);
                if (this.listener != null) {
                    this.listener.mode(1);
                }
            }
        } else if (this.circulateRB3 != null) {
            this.circulateRG.check(2131296498);
            if (this.listener != null) {
                this.listener.mode(2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.customLeftText = (TextView) findViewById(2131296565);
        this.circulateRB1 = (RadioButton) findViewById(2131296496);
        this.circulateRB2 = (RadioButton) findViewById(2131296497);
        this.circulateRB3 = (RadioButton) findViewById(2131296498);
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        if (CustomRadioThreeLayout.this.listener != null) {
                            CustomRadioThreeLayout.this.listener.mode(0);
                            return;
                        }
                        return;
                    case 2131296497:
                        if (CustomRadioThreeLayout.this.listener != null) {
                            CustomRadioThreeLayout.this.listener.mode(1);
                            return;
                        }
                        return;
                    case 2131296498:
                        if (CustomRadioThreeLayout.this.listener != null) {
                            CustomRadioThreeLayout.this.listener.mode(2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
