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
import com.wx.assistants.utils.FontUtils;

public class NumSettingLayout extends LinearLayout {
    private int canMaxNum = 5000;
    /* access modifiers changed from: private */
    public OnResultListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int num = 5000;
    private String numberTitle = "设置数量";
    @BindView(2131297085)
    TextView numberTitleText;
    /* access modifiers changed from: private */
    public int pointIndex = 1;
    private String pointTitle = "设置起点";
    @BindView(2131297173)
    LinearLayout quantityLayout;
    @BindView(2131297174)
    TextView quantityText;
    @BindView(2131297415)
    LinearLayout startPointLayout;
    @BindView(2131297416)
    TextView startPointText;
    @BindView(2131297417)
    TextView startPointTitleText;

    public interface OnResultListener {
        void result(int i, int i2);
    }

    public NumSettingLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public NumSettingLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public NumSettingLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427764, this, true);
        ButterKnife.bind(this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        FontUtils.changeFontColor(this.mContext, this.startPointText);
    }

    @OnClick({2131297173, 2131297415})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297173) {
            DialogUIUtils.dialogSetStartPoint(this.mContext, this.numberTitle, Integer.parseInt(this.quantityText.getText().toString()), this.canMaxNum, new AlertEditDialog.OnEditTextListener() {
                public void result(int i) {
                    TextView textView = NumSettingLayout.this.quantityText;
                    textView.setText(i + "");
                    int unused = NumSettingLayout.this.num = i;
                    if (NumSettingLayout.this.listener != null) {
                        NumSettingLayout.this.listener.result(NumSettingLayout.this.num, NumSettingLayout.this.pointIndex);
                    }
                }
            });
        } else if (id == 2131297415) {
            DialogUIUtils.dialogSetStartPoint(this.mContext, this.pointTitle, this.pointIndex, new AlertEditDialog.OnEditTextListener() {
                public void result(int i) {
                    TextView textView = NumSettingLayout.this.startPointText;
                    textView.setText("从第 " + i + " 个开始");
                    FontUtils.changeFontColor(NumSettingLayout.this.mContext, NumSettingLayout.this.startPointText);
                    int unused = NumSettingLayout.this.pointIndex = i;
                    if (NumSettingLayout.this.listener != null) {
                        NumSettingLayout.this.listener.result(NumSettingLayout.this.num, NumSettingLayout.this.pointIndex);
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

    public void setNumberGone(boolean z) {
        if (z) {
            this.quantityLayout.setVisibility(8);
        } else {
            this.quantityLayout.setVisibility(0);
        }
    }

    public void setOnResultListener(OnResultListener onResultListener) {
        if (onResultListener != null) {
            this.listener = onResultListener;
        }
    }

    public void setStartPointIndex(int i, String str) {
        this.pointIndex = i;
        this.pointTitle = str;
        if (this.pointTitle != null && !"".equals(this.pointTitle)) {
            this.startPointTitleText.setText(this.pointTitle);
        }
        TextView textView = this.startPointText;
        textView.setText("从第 " + i + " 个开始");
        FontUtils.changeFontColor(this.mContext, this.startPointText);
    }
}
