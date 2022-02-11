package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CirculateModelLayout extends LinearLayout {
    private RadioButton circulateRB1;
    private RadioButton circulateRB2;
    private RadioGroup circulateRG;
    /* access modifiers changed from: private */
    public CirculateModelListener listener;
    private Context mContext;

    public interface CirculateModelListener {
        void circulateMode(int i);
    }

    public CirculateModelLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CirculateModelLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CirculateModelLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427737, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.circulateRB1 = (RadioButton) findViewById(2131296496);
        this.circulateRB2 = (RadioButton) findViewById(2131296497);
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        if (CirculateModelLayout.this.listener != null) {
                            CirculateModelLayout.this.listener.circulateMode(0);
                            return;
                        }
                        return;
                    case 2131296497:
                        if (CirculateModelLayout.this.listener != null) {
                            CirculateModelLayout.this.listener.circulateMode(1);
                            return;
                        }
                        return;
                    default:
                        return;
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
