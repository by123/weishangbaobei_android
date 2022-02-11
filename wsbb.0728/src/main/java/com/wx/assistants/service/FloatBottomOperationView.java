package com.wx.assistants.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatBottomOperationView extends LinearLayout {
    private LinearLayout alertLayout;
    private LinearLayout bottomLayout;
    private TextView bottomTextView;
    private int color;
    public int height;
    private Button leftBtn;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private MyWindowManager myWindowManager;
    private Button rightBtn;
    public int width;

    public FloatBottomOperationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatBottomOperationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatBottomOperationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.color = 0;
        this.mContext = context;
        LayoutInflater.from(context).inflate(2131427696, this);
        this.mLinearLayout = (LinearLayout) findViewById(2131296402);
        this.alertLayout = (LinearLayout) findViewById(2131296329);
        this.bottomLayout = (LinearLayout) this.mLinearLayout.findViewById(2131296399);
        this.bottomTextView = (TextView) this.mLinearLayout.findViewById(2131296406);
        this.width = this.mLinearLayout.getLayoutParams().width;
        this.height = this.mLinearLayout.getLayoutParams().height;
        this.leftBtn = (Button) this.mLinearLayout.findViewById(2131296922);
        this.rightBtn = (Button) this.mLinearLayout.findViewById(2131297244);
    }

    public void setBackGround(int i) {
        if (this.color != -1) {
            this.alertLayout.setBackgroundResource(i);
            this.bottomTextView.setTextColor(this.mContext.getResources().getColor(2131100063));
        }
    }

    public void setBottomLayoutVisibility(int i) {
        if (this.bottomTextView != null && this.bottomLayout != null) {
            this.bottomLayout.setVisibility(i);
        }
    }

    public void setBottomText(String str) {
        if (!(this.bottomTextView == null || this.bottomLayout == null)) {
            this.bottomLayout.setVisibility(8);
        }
        if (this.bottomTextView != null && str != null) {
            this.bottomTextView.setText(str);
        }
    }

    public void setBottomText(String str, long j) {
        if (!(this.bottomTextView == null || str == null)) {
            this.bottomTextView.setText(str);
        }
        if (j > 0) {
            this.myWindowManager.removeBottomView();
        }
    }

    public void setLeftBtnText(String str, View.OnClickListener onClickListener) {
        if (!(this.bottomTextView == null || this.bottomLayout == null)) {
            this.bottomLayout.setVisibility(0);
        }
        if (this.bottomTextView != null && str != null) {
            if (str != null) {
                this.leftBtn.setText(str);
            }
            if (onClickListener != null) {
                this.leftBtn.setOnClickListener(onClickListener);
            }
        }
    }

    public void setRightBtnText(String str, View.OnClickListener onClickListener) {
        if (!(this.bottomTextView == null || this.bottomLayout == null)) {
            this.bottomLayout.setVisibility(0);
        }
        if (this.bottomTextView != null && str != null) {
            if (str != null) {
                this.rightBtn.setText(str);
            }
            if (onClickListener != null) {
                this.rightBtn.setOnClickListener(onClickListener);
            }
        }
    }
}
