package com.wx.assistants.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatMiddleView extends LinearLayout implements View.OnClickListener {
    public int height;
    private boolean isOnlyOk;
    private Button middleBtn1;
    private Button middleBtn2;
    private TextView middleText;
    private MyWindowManager myWindowManager;
    private OnLeftBtnListener onLeftBtnListener;
    private OnRightBtnListener onRightBtnListener;
    private TextView titleView;
    public int width;

    public interface OnLeftBtnListener {
        void leftListener();
    }

    public interface OnRightBtnListener {
        void rightListener();
    }

    public FloatMiddleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatMiddleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatMiddleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isOnlyOk = false;
        LayoutInflater.from(context).inflate(2131427699, this);
        this.titleView = (TextView) findViewById(2131297514);
        this.middleText = (TextView) findViewById(2131297024);
        this.middleBtn1 = (Button) findViewById(2131297021);
        this.middleBtn1.setOnClickListener(this);
        this.middleBtn2 = (Button) findViewById(2131297022);
        this.middleBtn2.setOnClickListener(this);
        View findViewById = findViewById(2131297023);
        this.width = findViewById.getLayoutParams().width;
        this.height = findViewById.getLayoutParams().height;
    }

    public void onClick(View view) {
        if (this.myWindowManager == null) {
            this.myWindowManager = MyWindowManager.newInstance();
        }
        switch (view.getId()) {
            case 2131297021:
                if (this.onLeftBtnListener == null) {
                    this.myWindowManager.removeMiddleView();
                    return;
                } else {
                    this.onLeftBtnListener.leftListener();
                    return;
                }
            case 2131297022:
                if (this.onRightBtnListener == null) {
                    this.myWindowManager.removeMiddleView();
                    this.myWindowManager.jumpWSBaby();
                    return;
                }
                this.onRightBtnListener.rightListener();
                return;
            default:
                return;
        }
    }

    public FloatMiddleView setContent(String str) {
        if (str != null) {
            this.middleText.setText(str);
        }
        return this;
    }

    public FloatMiddleView setLeftText(String str, OnLeftBtnListener onLeftBtnListener2) {
        if (this.middleBtn1 != null) {
            this.middleBtn1.setText(str);
        }
        if (onLeftBtnListener2 != null) {
            this.onLeftBtnListener = onLeftBtnListener2;
        }
        return this;
    }

    public FloatMiddleView setOnlyOk(boolean z) {
        this.isOnlyOk = z;
        if (this.isOnlyOk) {
            this.middleBtn1.setText("确定");
            this.middleBtn2.setVisibility(8);
        }
        return this;
    }

    public FloatMiddleView setRightText(String str, OnRightBtnListener onRightBtnListener2) {
        if (this.middleBtn2 != null) {
            this.middleBtn2.setText(str);
        }
        if (onRightBtnListener2 != null) {
            this.onRightBtnListener = onRightBtnListener2;
        }
        return this;
    }

    public FloatMiddleView setTitle(String str) {
        if (str != null) {
            this.titleView.setText(str);
        }
        return this;
    }
}
