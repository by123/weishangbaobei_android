package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.wx.assistants.dialog.view.AlertFFModelDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;

public class FFModelLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public int endTime = 10;
    /* access modifiers changed from: private */
    public TextView ffTime;
    /* access modifiers changed from: private */
    public OnFFModelListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    private Switch remarkSwitchBtn;
    /* access modifiers changed from: private */
    public int startTime = 0;

    public interface OnFFModelListener {
        void ffMode(int i);

        void ffModeTime(int i, int i2);
    }

    public void setDefaultTime(int i, int i2) {
        this.startTime = i;
        this.endTime = i2;
    }

    public void setOnFFModelListener(OnFFModelListener onFFModelListener) {
        if (onFFModelListener != null) {
            this.listener = onFFModelListener;
        }
    }

    public FFModelLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public FFModelLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FFModelLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427749, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.remarkSwitchBtn = (Switch) findViewById(2131297234);
        this.ffTime = (TextView) findViewById(2131296661);
        TextView textView = this.ffTime;
        textView.setText("为了防止微信封号,加人间隔时间系统自动从[" + this.startTime + "-" + this.endTime + "]秒之间随机分配，点击此文本可更改这个时间间隔。");
        FontUtils.changeFontColor(this.mContext, this.ffTime, "从", "秒");
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (FFModelLayout.this.listener != null) {
                        FFModelLayout.this.listener.ffMode(1);
                    }
                } else if (FFModelLayout.this.listener != null) {
                    FFModelLayout.this.listener.ffMode(0);
                }
            }
        });
        this.ffTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogUIUtils.setFFModelTime(FFModelLayout.this.mContext, FFModelLayout.this.startTime, FFModelLayout.this.endTime, new AlertFFModelDialog.OnEditTextListener() {
                    public void result(int i, int i2) {
                        if (FFModelLayout.this.listener != null) {
                            int unused = FFModelLayout.this.startTime = i;
                            int unused2 = FFModelLayout.this.endTime = i2;
                            FFModelLayout.this.listener.ffModeTime(i, i2);
                            TextView access$400 = FFModelLayout.this.ffTime;
                            access$400.setText("为了防止微信封号,加人间隔时间系统自动从[" + FFModelLayout.this.startTime + "-" + FFModelLayout.this.endTime + "]秒之间随机分配，点击此文本可更改这个时间间隔。");
                            FontUtils.changeFontColor(FFModelLayout.this.mContext, FFModelLayout.this.ffTime, "从", "秒");
                        }
                    }
                });
            }
        });
    }
}
