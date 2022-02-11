package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;

public class NumSettingOnlyLayout extends LinearLayout {
    private int canMaxNum = 5000;
    /* access modifiers changed from: private */
    public OnResultListener listener;
    private Context mContext;
    /* access modifiers changed from: private */
    public int num = 5000;
    private String numberTitle = "设置数量";
    @BindView(2131297085)
    TextView numberTitleText;
    @BindView(2131297173)
    LinearLayout quantityLayout;
    @BindView(2131297174)
    TextView quantityText;

    public interface OnResultListener {
        void result(int i);
    }

    public NumSettingOnlyLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public NumSettingOnlyLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public NumSettingOnlyLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427765, this, true);
        ButterKnife.bind(this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @OnClick({2131297173})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297173) {
            DialogUIUtils.dialogSetStartPoint(this.mContext, this.numberTitle, Integer.parseInt(this.quantityText.getText().toString()), this.canMaxNum, new AlertEditDialog.OnEditTextListener() {
                public void result(int i) {
                    TextView textView = NumSettingOnlyLayout.this.quantityText;
                    textView.setText(i + "");
                    int unused = NumSettingOnlyLayout.this.num = i;
                    if (NumSettingOnlyLayout.this.listener != null) {
                        NumSettingOnlyLayout.this.listener.result(NumSettingOnlyLayout.this.num);
                    }
                }
            });
        }
    }

    public void setNum(int i, int i2, String str) {
        this.num = i;
        this.canMaxNum = i2;
        this.numberTitle = str;
        if (this.numberTitle != null && !"".equals(this.numberTitle)) {
            this.numberTitleText.setText(this.numberTitle);
        }
        if (this.quantityText != null) {
            TextView textView = this.quantityText;
            textView.setText("" + this.num);
        }
    }

    public void setOnResultListener(OnResultListener onResultListener) {
        if (onResultListener != null) {
            this.listener = onResultListener;
        }
    }
}
