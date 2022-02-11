package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;

public class ExecuteTimeSpaceLayout extends LinearLayout {
    private TextView executeTimeTitle;
    /* access modifiers changed from: private */
    public OnTimeSpaceListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int minTime = 0;
    private int num1 = 0;
    private LinearLayout rightLayout;
    /* access modifiers changed from: private */
    public TextView timeSpaceEditText;

    public interface OnTimeSpaceListener {
        void executeSpace(int i);
    }

    public ExecuteTimeSpaceLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public ExecuteTimeSpaceLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public ExecuteTimeSpaceLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public TextView getEditText() {
        return this.timeSpaceEditText;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427748, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.executeTimeTitle = (TextView) findViewById(2131296648);
        this.rightLayout = (LinearLayout) findViewById(2131297245);
        this.timeSpaceEditText = (TextView) findViewById(2131297507);
        this.rightLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogUIUtils.dialogSetStartPoint(ExecuteTimeSpaceLayout.this.mContext, "时间间隔", Integer.parseInt(ExecuteTimeSpaceLayout.this.timeSpaceEditText.getText().toString()), 7200, 0, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        try {
                            if (i < ExecuteTimeSpaceLayout.this.minTime || i > 7200) {
                                Context access$100 = ExecuteTimeSpaceLayout.this.mContext;
                                ToastUtils.showToast(access$100, "请设置" + ExecuteTimeSpaceLayout.this.minTime + "-7200秒之间");
                                return;
                            }
                            TextView access$000 = ExecuteTimeSpaceLayout.this.timeSpaceEditText;
                            access$000.setText(i + "");
                            if (ExecuteTimeSpaceLayout.this.listener != null) {
                                ExecuteTimeSpaceLayout.this.listener.executeSpace(i);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void setExecuteTimeTitle(String str) {
        if (str != null) {
            this.executeTimeTitle.setText(str);
        }
    }

    public void setMinTime(int i) {
        this.minTime = i;
        TextView textView = this.timeSpaceEditText;
        textView.setText(this.minTime + "");
    }

    public void setOnTimeSpaceListener(OnTimeSpaceListener onTimeSpaceListener) {
        if (onTimeSpaceListener != null) {
            this.listener = onTimeSpaceListener;
        }
    }

    public void setRightLayoutMargin(int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(4, 0, i, 0);
        this.rightLayout.setLayoutParams(layoutParams);
    }
}
