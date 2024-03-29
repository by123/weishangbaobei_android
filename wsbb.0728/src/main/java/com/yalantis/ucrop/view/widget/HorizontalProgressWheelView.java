package com.yalantis.ucrop.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.view.CropImageView;

public class HorizontalProgressWheelView extends View {
    private final Rect mCanvasClipBounds;
    private float mLastTouchedPosition;
    private int mMiddleLineColor;
    private int mProgressLineHeight;
    private int mProgressLineMargin;
    private Paint mProgressLinePaint;
    private int mProgressLineWidth;
    private boolean mScrollStarted;
    private ScrollingListener mScrollingListener;
    private float mTotalScrollDistance;

    public interface ScrollingListener {
        void onScroll(float f, float f2);

        void onScrollEnd();

        void onScrollStart();
    }

    public HorizontalProgressWheelView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCanvasClipBounds = new Rect();
        init();
    }

    @TargetApi(21)
    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCanvasClipBounds = new Rect();
    }

    private void init() {
        this.mMiddleLineColor = ContextCompat.getColor(getContext(), R.color.ucrop_color_progress_wheel_line);
        this.mProgressLineWidth = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_width_horizontal_wheel_progress_line);
        this.mProgressLineHeight = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_height_horizontal_wheel_progress_line);
        this.mProgressLineMargin = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_margin_horizontal_wheel_progress_line);
        this.mProgressLinePaint = new Paint(1);
        this.mProgressLinePaint.setStyle(Paint.Style.STROKE);
        this.mProgressLinePaint.setStrokeWidth((float) this.mProgressLineWidth);
    }

    private void onScrollEvent(MotionEvent motionEvent, float f) {
        this.mTotalScrollDistance -= f;
        postInvalidate();
        this.mLastTouchedPosition = motionEvent.getX();
        if (this.mScrollingListener != null) {
            this.mScrollingListener.onScroll(-f, this.mTotalScrollDistance);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.mCanvasClipBounds);
        int width = this.mCanvasClipBounds.width() / (this.mProgressLineWidth + this.mProgressLineMargin);
        float f = this.mTotalScrollDistance;
        float f2 = (float) (this.mProgressLineMargin + this.mProgressLineWidth);
        this.mProgressLinePaint.setColor(getResources().getColor(R.color.ucrop_color_progress_wheel_line));
        for (int i = 0; i < width; i++) {
            int i2 = width / 4;
            if (i < i2) {
                this.mProgressLinePaint.setAlpha((int) ((((float) i) / ((float) i2)) * 255.0f));
            } else if (i > (width * 3) / 4) {
                this.mProgressLinePaint.setAlpha((int) ((((float) (width - i)) / ((float) i2)) * 255.0f));
            } else {
                this.mProgressLinePaint.setAlpha(255);
            }
            float f3 = -(f % f2);
            canvas.drawLine(((float) this.mCanvasClipBounds.left) + f3 + ((float) ((this.mProgressLineWidth + this.mProgressLineMargin) * i)), ((float) this.mCanvasClipBounds.centerY()) - (((float) this.mProgressLineHeight) / 4.0f), ((float) ((this.mProgressLineWidth + this.mProgressLineMargin) * i)) + f3 + ((float) this.mCanvasClipBounds.left), (((float) this.mProgressLineHeight) / 4.0f) + ((float) this.mCanvasClipBounds.centerY()), this.mProgressLinePaint);
        }
        this.mProgressLinePaint.setColor(this.mMiddleLineColor);
        canvas.drawLine((float) this.mCanvasClipBounds.centerX(), ((float) this.mCanvasClipBounds.centerY()) - (((float) this.mProgressLineHeight) / 2.0f), (float) this.mCanvasClipBounds.centerX(), ((float) this.mCanvasClipBounds.centerY()) + (((float) this.mProgressLineHeight) / 2.0f), this.mProgressLinePaint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastTouchedPosition = motionEvent.getX();
                break;
            case 1:
                if (this.mScrollingListener != null) {
                    this.mScrollStarted = false;
                    this.mScrollingListener.onScrollEnd();
                    break;
                }
                break;
            case 2:
                float x = motionEvent.getX() - this.mLastTouchedPosition;
                if (x != CropImageView.DEFAULT_ASPECT_RATIO) {
                    if (!this.mScrollStarted) {
                        this.mScrollStarted = true;
                        if (this.mScrollingListener != null) {
                            this.mScrollingListener.onScrollStart();
                        }
                    }
                    onScrollEvent(motionEvent, x);
                    break;
                }
                break;
        }
        return true;
    }

    public void setMiddleLineColor(@ColorInt int i) {
        this.mMiddleLineColor = i;
        invalidate();
    }

    public void setScrollingListener(ScrollingListener scrollingListener) {
        this.mScrollingListener = scrollingListener;
    }
}
