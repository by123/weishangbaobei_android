package com.xiasuhuei321.loadingdialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class LoadCircleView extends View {
    public final String TAG;
    private int currentLineIndex;
    private Context mContext;
    private float mPadding;
    private Paint mPaint;
    private int mWidth;
    private RectF rectF;

    public LoadCircleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoadCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mPadding = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mWidth = 0;
        this.currentLineIndex = 0;
        this.mContext = context;
        init();
    }

    public void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(8.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        int i2 = this.mWidth >> 1;
        int i3 = (this.mWidth >> 1) - 8;
        if (this.currentLineIndex >= 12) {
            this.currentLineIndex = 0;
            i = 0;
        } else {
            i = 0;
        }
        while (i < 12) {
            if (i < this.currentLineIndex + 4 && i >= this.currentLineIndex) {
                this.mPaint.setColor(-7829368);
            } else if (this.currentLineIndex <= 8 || i >= (this.currentLineIndex + 4) - 12) {
                this.mPaint.setColor(-1);
            } else {
                this.mPaint.setColor(-7829368);
            }
            float f = (float) i2;
            double d = (double) i2;
            double d2 = (double) i3;
            Double.isNaN(d2);
            Double.isNaN(d);
            canvas.drawLine(f, (float) (d + (d2 * 0.5d)), f, (float) (i3 * 2), this.mPaint);
            canvas.rotate(30.0f, f, f);
            i++;
        }
        this.currentLineIndex++;
        postInvalidateDelayed(50);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE && mode2 != Integer.MIN_VALUE) {
            if (size < size2) {
                size = size2;
            }
            this.mWidth = size;
        } else if (mode == Integer.MIN_VALUE && mode2 != Integer.MIN_VALUE) {
            this.mWidth = size2;
        } else if (mode != Integer.MIN_VALUE) {
            this.mWidth = size;
        } else {
            this.mWidth = SizeUtils.dip2px(this.mContext, 50.0f);
        }
        setMeasuredDimension(this.mWidth, this.mWidth);
        this.mPadding = 8.0f;
    }
}
