package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class FriendsCopyTagSetLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public OnFriendsCopyTagSetListener listener;
    private Context mContext;
    private Switch switchButton;

    public interface OnFriendsCopyTagSetListener {
        void circulateMode(int i);
    }

    public FriendsCopyTagSetLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendsCopyTagSetLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendsCopyTagSetLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427751, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.switchButton = (Switch) findViewById(2131297447);
        this.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (FriendsCopyTagSetLayout.this.listener != null) {
                        FriendsCopyTagSetLayout.this.listener.circulateMode(0);
                    }
                } else if (FriendsCopyTagSetLayout.this.listener != null) {
                    FriendsCopyTagSetLayout.this.listener.circulateMode(1);
                }
            }
        });
    }

    public void setOnFriendsCopyTagSetListener(OnFriendsCopyTagSetListener onFriendsCopyTagSetListener) {
        if (onFriendsCopyTagSetListener != null) {
            this.listener = onFriendsCopyTagSetListener;
        }
    }
}
