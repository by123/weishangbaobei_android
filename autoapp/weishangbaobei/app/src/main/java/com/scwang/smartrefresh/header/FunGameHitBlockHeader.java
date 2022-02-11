package com.scwang.smartrefresh.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import com.scwang.smartrefresh.header.fungame.FunGameView;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunGameHitBlockHeader extends FunGameView {
    protected static final int BLOCK_HORIZONTAL_NUM = 3;
    protected static final float BLOCK_POSITION_RATIO = 0.08f;
    protected static final int BLOCK_VERTICAL_NUM = 5;
    protected static final float BLOCK_WIDTH_RATIO = 0.01806f;
    protected static final int DEFAULT_ANGLE = 30;
    static final float DIVIDING_LINE_SIZE = 1.0f;
    protected static final float RACKET_POSITION_RATIO = 0.8f;
    protected static final int SPEED = 3;
    protected float BALL_RADIUS;
    protected int angle;
    protected float blockHeight;
    protected int blockHorizontalNum;
    protected float blockLeft;
    protected Paint blockPaint;
    protected float blockWidth;
    protected float cx;
    protected float cy;
    protected boolean isLeft;
    protected List<Point> pointList;
    protected float racketLeft;
    protected int speed;

    public FunGameHitBlockHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public FunGameHitBlockHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunGameHitBlockHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FunGameHitBlockHeader);
        this.speed = obtainStyledAttributes.getInt(R.styleable.FunGameHitBlockHeader_fghBallSpeed, DensityUtil.dp2px(3.0f));
        this.blockHorizontalNum = obtainStyledAttributes.getInt(R.styleable.FunGameHitBlockHeader_fghBlockHorizontalNum, 3);
        obtainStyledAttributes.recycle();
        this.blockPaint = new Paint(1);
        this.blockPaint.setStyle(Paint.Style.FILL);
        this.BALL_RADIUS = (float) DensityUtil.dp2px(4.0f);
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        int measuredWidth = getMeasuredWidth();
        this.blockHeight = ((float) (i / 5)) - 1.0f;
        float f = (float) measuredWidth;
        this.blockWidth = BLOCK_WIDTH_RATIO * f;
        this.blockLeft = BLOCK_POSITION_RATIO * f;
        this.racketLeft = f * RACKET_POSITION_RATIO;
        this.controllerSize = (int) (this.blockHeight * 1.6f);
        super.onInitialized(refreshKernel, i, i2);
    }

    /* access modifiers changed from: protected */
    public void resetConfigParams() {
        this.cx = this.racketLeft - (this.BALL_RADIUS * 3.0f);
        this.cy = (float) ((int) (((float) this.mHeaderHeight) * 0.5f));
        this.controllerPosition = 1.0f;
        this.angle = 30;
        this.isLeft = true;
        if (this.pointList == null) {
            this.pointList = new ArrayList();
        } else {
            this.pointList.clear();
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkTouchRacket(float f) {
        float f2 = f - this.controllerPosition;
        return f2 >= CropImageView.DEFAULT_ASPECT_RATIO && f2 <= ((float) this.controllerSize);
    }

    /* access modifiers changed from: protected */
    public boolean checkTouchBlock(float f, float f2) {
        int i = (int) ((((f - this.blockLeft) - this.BALL_RADIUS) - ((float) this.speed)) / this.blockWidth);
        if (i == this.blockHorizontalNum) {
            i--;
        }
        int i2 = (int) (f2 / this.blockHeight);
        if (i2 == 5) {
            i2--;
        }
        Point point = new Point();
        point.set(i, i2);
        boolean z = false;
        Iterator<Point> it = this.pointList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().equals(point.x, point.y)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            this.pointList.add(point);
        }
        return !z;
    }

    /* access modifiers changed from: protected */
    public void drawGame(Canvas canvas, int i, int i2) {
        drawColorBlock(canvas);
        drawRacket(canvas);
        if (this.status == 1 || this.status == 3 || this.status == 4 || isInEditMode()) {
            drawBallPath(canvas, i);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRacket(Canvas canvas) {
        this.mPaint.setColor(this.rModelColor);
        canvas.drawRect(this.racketLeft, this.controllerPosition, this.racketLeft + this.blockWidth, this.controllerPosition + ((float) this.controllerSize), this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void drawBallPath(Canvas canvas, int i) {
        this.mPaint.setColor(this.mModelColor);
        if (this.cx <= this.blockLeft + (((float) this.blockHorizontalNum) * this.blockWidth) + (((float) (this.blockHorizontalNum - 1)) * 1.0f) + this.BALL_RADIUS && checkTouchBlock(this.cx, this.cy)) {
            this.isLeft = false;
        }
        if (this.cx <= this.blockLeft + this.BALL_RADIUS) {
            this.isLeft = false;
        }
        if (this.cx + this.BALL_RADIUS < this.racketLeft || this.cx - this.BALL_RADIUS >= this.racketLeft + this.blockWidth) {
            if (this.cx > ((float) i)) {
                this.status = 2;
            }
        } else if (checkTouchRacket(this.cy)) {
            if (this.pointList.size() == this.blockHorizontalNum * 5) {
                this.status = 2;
                return;
            }
            this.isLeft = true;
        }
        if (this.cy <= this.BALL_RADIUS + 1.0f) {
            this.angle = 150;
        } else if (this.cy >= (((float) this.mHeaderHeight) - this.BALL_RADIUS) - 1.0f) {
            this.angle = 210;
        }
        if (this.isLeft) {
            this.cx -= (float) this.speed;
        } else {
            this.cx += (float) this.speed;
        }
        this.cy -= ((float) Math.tan(Math.toRadians((double) this.angle))) * ((float) this.speed);
        canvas.drawCircle(this.cx, this.cy, this.BALL_RADIUS, this.mPaint);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void drawColorBlock(Canvas canvas) {
        boolean z;
        for (int i = 0; i < this.blockHorizontalNum * 5; i++) {
            int i2 = i / this.blockHorizontalNum;
            int i3 = i % this.blockHorizontalNum;
            Iterator<Point> it = this.pointList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(i3, i2)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                this.blockPaint.setColor(ColorUtils.setAlphaComponent(this.lModelColor, 255 / (i3 + 1)));
                float f = this.blockLeft + (((float) i3) * (this.blockWidth + 1.0f));
                float f2 = (((float) i2) * (this.blockHeight + 1.0f)) + 1.0f;
                canvas.drawRect(f, f2, f + this.blockWidth, f2 + this.blockHeight, this.blockPaint);
            }
        }
    }
}
