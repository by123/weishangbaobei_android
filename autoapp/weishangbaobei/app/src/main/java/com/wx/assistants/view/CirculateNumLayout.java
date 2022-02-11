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

public class CirculateNumLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public int circulateNum;
    /* access modifiers changed from: private */
    public TextView circulateNumEditText;
    private LinearLayout circulateNumLayout;
    /* access modifiers changed from: private */
    public CirculateNumListener listener;
    /* access modifiers changed from: private */
    public Context mContext;

    public interface CirculateNumListener {
        void circulateNum(int i);
    }

    public void setCirculateNumListener(CirculateNumListener circulateNumListener) {
        if (circulateNumListener != null) {
            this.listener = circulateNumListener;
        }
    }

    public CirculateNumLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CirculateNumLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CirculateNumLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427738, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.circulateNumLayout = (LinearLayout) findViewById(2131296495);
        this.circulateNumEditText = (TextView) findViewById(2131296494);
        this.circulateNumLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = CirculateNumLayout.this.circulateNum = Integer.parseInt(CirculateNumLayout.this.circulateNumEditText.getText().toString());
                DialogUIUtils.circulateNum(CirculateNumLayout.this.mContext, CirculateNumLayout.this.circulateNum, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = CirculateNumLayout.this.circulateNum = i;
                        TextView access$100 = CirculateNumLayout.this.circulateNumEditText;
                        access$100.setText(i + "");
                        if (CirculateNumLayout.this.listener != null) {
                            CirculateNumLayout.this.listener.circulateNum(CirculateNumLayout.this.circulateNum);
                        }
                    }
                });
            }
        });
    }
}
