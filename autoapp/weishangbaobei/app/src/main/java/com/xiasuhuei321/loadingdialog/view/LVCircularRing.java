package com.xiasuhuei321.loadingdialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.xiasuhuei321.loadingdialog.R;

public class LVCircularRing extends LinearLayout {
    public LVCircularRing(Context context) {
        this(context, (AttributeSet) null);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        initCirculateNumView(context);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.baby_dialog_view, this, true);
    }
}
