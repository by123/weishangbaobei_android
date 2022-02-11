package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import www.linwg.org.lib.LCardView;

public class SendContentLayout extends LinearLayout implements View.OnClickListener {
    private CirculateModelListener listener;
    private Context mContext;
    private LCardView sendContent1;
    private LCardView sendContent2;
    private LCardView sendContent3;

    public interface CirculateModelListener {
        void circulateMode(int i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131297330:
                this.sendContent1.setShadowColor(this.mContext.getResources().getColor(2131099727));
                this.sendContent2.setShadowColor(this.mContext.getResources().getColor(2131100063));
                this.sendContent3.setShadowColor(this.mContext.getResources().getColor(2131100063));
                if (this.listener != null) {
                    this.listener.circulateMode(0);
                    return;
                }
                return;
            case 2131297331:
                this.sendContent2.setShadowColor(this.mContext.getResources().getColor(2131099727));
                this.sendContent1.setShadowColor(this.mContext.getResources().getColor(2131100063));
                this.sendContent3.setShadowColor(this.mContext.getResources().getColor(2131100063));
                if (this.listener != null) {
                    this.listener.circulateMode(2);
                    return;
                }
                return;
            case 2131297332:
                this.sendContent3.setShadowColor(this.mContext.getResources().getColor(2131099727));
                this.sendContent1.setShadowColor(this.mContext.getResources().getColor(2131100063));
                this.sendContent2.setShadowColor(this.mContext.getResources().getColor(2131100063));
                if (this.listener != null) {
                    this.listener.circulateMode(1);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setCirculateModelListener(CirculateModelListener circulateModelListener) {
        if (circulateModelListener != null) {
            this.listener = circulateModelListener;
        }
    }

    public SendContentLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SendContentLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SendContentLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void setDefaultContent(int i) {
        if (i == 0) {
            this.sendContent1.performClick();
        } else if (i == 1) {
            this.sendContent2.performClick();
        } else if (i == 2) {
            this.sendContent3.performClick();
        }
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427773, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.sendContent1 = findViewById(2131297330);
        this.sendContent2 = findViewById(2131297331);
        this.sendContent3 = findViewById(2131297332);
        this.sendContent1.setOnClickListener(this);
        this.sendContent2.setOnClickListener(this);
        this.sendContent3.setOnClickListener(this);
    }
}
