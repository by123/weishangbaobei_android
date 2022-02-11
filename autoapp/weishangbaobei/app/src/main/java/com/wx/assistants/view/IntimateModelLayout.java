package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class IntimateModelLayout extends LinearLayout {
    private RadioButton circulateRB1;
    private RadioButton circulateRB2;
    private RadioGroup circulateRG;
    /* access modifiers changed from: private */
    public IntimateModelListener listener;

    public interface IntimateModelListener {
        void intimateMode(int i);
    }

    public void setIntimateModelListener(IntimateModelListener intimateModelListener) {
        if (intimateModelListener != null) {
            this.listener = intimateModelListener;
        }
    }

    public void setSelectMode(int i) {
        if (i == 0) {
            this.circulateRG.check(2131296496);
        } else if (i == 1) {
            this.circulateRG.check(2131296497);
        }
    }

    public IntimateModelLayout(Context context) {
        super(context);
    }

    public IntimateModelLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initCirculateNumView(context);
    }

    public IntimateModelLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427760, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRB1 = (RadioButton) findViewById(2131296496);
        this.circulateRB2 = (RadioButton) findViewById(2131296497);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        if (IntimateModelLayout.this.listener != null) {
                            IntimateModelLayout.this.listener.intimateMode(0);
                            return;
                        }
                        return;
                    case 2131296497:
                        if (IntimateModelLayout.this.listener != null) {
                            IntimateModelLayout.this.listener.intimateMode(1);
                            return;
                        }
                        return;
                    case 2131296498:
                        if (IntimateModelLayout.this.listener != null) {
                            IntimateModelLayout.this.listener.intimateMode(2);
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
