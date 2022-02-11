package com.xiasuhuei321.loadingdialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class WrongDiaView extends View {
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

    public WrongDiaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WrongDiaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WrongDiaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mWidth = 0;
        this.mPadding = CropImageView.DEFAULT_ASPECT_RATIO;
        this.times = 0;
        this.drawEveryTime = true;
        this.speed = 1;
        this.count = 0;
        this.progress = 0;
        initPaint(context);
    }

    private void drawDynamic(Canvas canvas) {
        if (this.progress < 100) {
            this.progress += this.speed;
        }
        canvas.drawArc(this.rectF, 235.0f, (float) ((this.progress * 360) / 100), false, this.mPaint);
        int i = (this.mWidth * 3) / 10;
        int i2 = (this.mWidth * 7) / 10;
        if (this.progress == 100) {
            if (this.line1_x + i <= i2) {
                this.line1_x += this.speed;
                this.line1_y += this.speed;
            }
            float f = (float) i;
            canvas.drawLine(f, f, (float) (this.line1_x + i), (float) (this.line1_y + i), this.mPaint);
            if (this.line1_x == (this.mWidth * 2) / 5) {
                this.line1_x++;
                this.line1_y++;
            }
            if (this.line1_x >= (this.mWidth * 2) / 5 && i2 - this.line2_y >= i) {
                this.line2_x -= this.speed;
                this.line2_y += this.speed;
            }
            canvas.drawLine((float) i2, f, (float) (this.line2_x + i2), (float) (this.line2_y + i), this.mPaint);
            if (i2 - this.line2_y < i) {
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
        }
        invalidate();
    }

    private void drawStatic(Canvas canvas) {
        canvas.drawArc(this.rectF, CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, false, this.mPaint);
        int i = (this.mWidth * 3) / 10;
        int i2 = (this.mWidth * 7) / 10;
        float f = (float) i;
        canvas.drawLine(f, f, (float) (((this.mWidth * 2) / 5) + i), (float) (((this.mWidth * 2) / 5) + i), this.mPaint);
        canvas.drawLine((float) (((this.mWidth * 2) / 5) + i), f, f, (float) (((this.mWidth * 2) / 5) + i), this.mPaint);
    }

    private void initPaint(Context context) {
        this.mContext = context;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(8.0f);
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
        throw new IllegalArgumentException("how can u set this speed??  " + i + "  do not use reflect to use this method!u can see the LoadingDialog class for how toset the speed");
    }
}
