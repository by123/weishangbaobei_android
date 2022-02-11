package com.wx.assistants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.example.wx.assistant.R;

public class TagCloudConfiguration {
    private static final int DEFAULT_FIXED_COLUMN_SIZE = 3;
    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;
    private int columnSize;
    private boolean isFixed;
    private int lineSpacing;
    private int tagSpacing;

    public TagCloudConfiguration(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagCloudLayout);
        try {
            this.lineSpacing = obtainStyledAttributes.getDimensionPixelSize(2, 5);
            this.tagSpacing = obtainStyledAttributes.getDimensionPixelSize(3, 10);
            this.columnSize = obtainStyledAttributes.getInteger(0, 3);
            this.isFixed = obtainStyledAttributes.getBoolean(1, false);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int getLineSpacing() {
        return this.lineSpacing;
    }

    public void setLineSpacing(int i) {
        this.lineSpacing = i;
    }

    public int getTagSpacing() {
        return this.tagSpacing;
    }

    public void setTagSpacing(int i) {
        this.tagSpacing = i;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public void setColumnSize(int i) {
        this.columnSize = i;
    }

    public boolean isFixed() {
        return this.isFixed;
    }

    public void setIsFixed(boolean z) {
        this.isFixed = z;
    }
}
