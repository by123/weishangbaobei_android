package com.xiasuhuei321.loadingdialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class RightDiaView extends View {
    private final String TAG;
    private int count;
    private boolean drawEveryTime;
    private int line1_x;
    private int line1_y;
    private int line2_x;
    private int line2_y;
    private FinishDrawListener listener;
    private Context mContext;
    private float mPadding;
    private Paint mPaint;
    private int mWidth;
    int progress;
    private RectF rectF;
    private int speed;
    private int times;

    public RightDiaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RightDiaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RightDiaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mWidth = 0;
        this.mPadding = CropImageView.DEFAULT_ASPECT_RATIO;
        this.times = 0;
        this.drawEveryTime = true;
        this.speed = 1;
        this.progress = 0;
        this.count = 0;
        init(context);
    }

    private void drawDynamic(Canvas canvas) {
        if (this.progress < 100) {
            this.progress += this.speed;
        }
        canvas.drawArc(this.rectF, 235.0f, (float) ((this.progress * 360) / 100), false, this.mPaint);
        int i = this.mWidth / 2;
        int i2 = i - (this.mWidth / 5);
        int i3 = (this.mWidth / 2) - 8;
        if (this.progress == 100) {
            int i4 = i3 / 3;
            if (this.line1_x < i4) {
                this.line1_x += this.speed;
                this.line1_y += this.speed;
            }
            canvas.drawLine((float) i2, (float) i, (float) (this.line1_x + i2), (float) (this.line1_y + i), this.mPaint);
            if (this.line1_x >= i4 && this.line2_x == 0 && this.line2_y == 0) {
                this.line2_x = this.line1_x;
                this.line2_y = this.line1_y;
                this.line1_x += this.speed;
                this.line1_y += this.speed;
            }
            if (this.line1_x >= i4 && this.line2_x <= i3 && this.line2_y <= i - i4) {
                this.line2_x += this.speed;
                this.line2_y -= this.speed;
            }
            canvas.drawLine((float) ((this.line1_x + i2) - 1), (float) ((this.line1_y + i) - 4), (float) (this.line2_x + i2), (float) (this.line2_y + i), this.mPaint);
        }
        if (this.line2_x > i3 && this.progress >= 100 && this.line1_x != i3 / 3) {
            if (this.count == 0 && this.times == 0 && this.listener != null) {
                this.listener.dispatchFinishEvent(this);
                this.count++;
            }
            this.times--;
            if (this.times >= 0) {
                reDraw();
                invalidate();
            } else {
                return;
            }
        }
        invalidate();
    }

    private void drawStatic(Canvas canvas) {
        canvas.drawArc(this.rectF, CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, false, this.mPaint);
        int i = this.mWidth / 2;
        int i2 = i - (this.mWidth / 5);
        int i3 = (this.mWidth / 2) - 8;
        int i4 = i3 / 3;
        int i5 = i2 + i4;
        int i6 = i + i4;
        canvas.drawLine((float) i2, (float) i, (float) i5, (float) i6, this.mPaint);
        canvas.drawLine((float) (i5 - 1), (float) (i6 - 4), (float) (i2 + i3), (float) (i - i4), this.mPaint);
    }

    private void init(Context context) {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(8.0f);
        this.mContext = context;
    }

    private void reDraw() {
        this.line1_x = 0;
        this.line2_x = 0;
        this.line1_y = 0;
        this.line2_y = 0;
        this.progress = 0;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.drawEveryTime) {
            drawDynamic(canvas);
            return;
        }
        drawStatic(canvas);
        if (this.listener != null) {
            this.listener.dispatchFinishEvent(this);
        }
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
            this.mWidth = SizeUtils.dip2px(this.mContext, 80.0f);
        }
        setMeasuredDimension(this.mWidth, this.mWidth);
        this.mPadding = 8.0f;
        this.rectF = new RectF(this.mPadding, this.mPadding, ((float) this.mWidth) - this.mPadding, ((float) this.mWidth) - this.mPadding);
    }

    /* access modifiers changed from: protected */
    public void setDrawColor(int i) {
        this.mPaint.setColor(i);
    }

    /* access modifiers changed from: protected */
    public void setDrawDynamic(boolean z) {
        this.drawEveryTime = z;
    }

    public void setOnDrawFinishListener(FinishDrawListener finishDrawListener) {
        this.listener = finishDrawListener;
    }

    /* access modifiers changed from: protected */
    public void setRepeatTime(int i) {
        if (this.drawEveryTime) {
            this.times = i;
        }
    }

    /* access modifiers changed from: protected */
    public void setSpeed(int i) {
        if (i > 0 || i < 3) {
            this.speed = i;
            return;
        }
        throw new IllegalArgumentException("support speed >0 & < 3, the speed you set is: " + i);
    }
}
