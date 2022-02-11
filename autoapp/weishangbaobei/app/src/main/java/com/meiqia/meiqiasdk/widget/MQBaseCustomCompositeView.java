package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class MQBaseCustomCompositeView extends RelativeLayout implements View.OnClickListener {
    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public void initAttr(int i, TypedArray typedArray) {
    }

    /* access modifiers changed from: protected */
    public abstract void initView();

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract void processLogic();

    /* access modifiers changed from: protected */
    public abstract void setListener();

    public MQBaseCustomCompositeView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MQBaseCustomCompositeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MQBaseCustomCompositeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View.inflate(context, getLayoutId(), this);
        initView();
        setListener();
        initAttrs(context, attributeSet);
        processLogic();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, getAttrs());
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            initAttr(obtainStyledAttributes.getIndex(i), obtainStyledAttributes);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public int[] getAttrs() {
        return new int[0];
    }

    /* access modifiers changed from: protected */
    public <VT extends View> VT getViewById(@IdRes int i) {
        return findViewById(i);
    }
}
