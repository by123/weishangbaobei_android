package com.mxy.fpshadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.yalantis.ucrop.view.CropImageView;

public class FpShadowLayout extends FrameLayout {
    public static final int ALL = 4369;
    public static final int BOTTOM = 4096;
    public static final int CORNER_ALL = 4369;
    public static final int CORNER_LEFT_BOTTOM = 16;
    public static final int CORNER_LEFT_TOP = 1;
    public static final int CORNER_RIGHT_BOTTOM = 4096;
    public static final int CORNER_RIGHT_TOP = 256;
    public static final int LEFT = 1;
    public static final int RIGHT = 256;
    public static final int SHAPE_RECTANGLE = 1;
    public static final int SHAPE_ROUND_RECTANGLE = 256;
    public static final int TOP = 16;
    private int mCornerPosition;
    private Paint mPaint;
    private float mRoundCornerRadius;
    private int mShadowColor;
    private float mShadowRadius;
    private int mShadowShape;
    private int mShadowSide;
    private int mViewHeight;
    private int mViewWidth;

    public FpShadowLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public FpShadowLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FpShadowLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint(1);
        this.mShadowColor = 0;
        this.mShadowRadius = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mShadowSide = 4369;
        this.mCornerPosition = 4369;
        this.mShadowShape = 1;
        this.mRoundCornerRadius = 10.0f;
        initViews(attributeSet);
    }

    private int[] creatThreePositionColor() {
        return new int[]{16777215, this.mShadowColor, 16777215};
    }

    private float[] creatThreePositionFloat() {
        return new float[]{this.mRoundCornerRadius / (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius / (this.mShadowRadius + this.mRoundCornerRadius), 1.0f};
    }

    private int[] creatTwoPositionColor() {
        return new int[]{this.mShadowColor, 16777215};
    }

    private float[] creatTwoPositionFloat() {
        return new float[]{0.01f, 1.0f};
    }

    private float dip2px(float f) {
        return (getContext().getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    private void drawArcRadialGradient(Canvas canvas, float f, float f2, float f3, int[] iArr, float[] fArr, Shader.TileMode tileMode, RectF rectF, float f4, float f5) {
        this.mPaint.setShader(new RadialGradient(f, f2, f3, iArr, fArr, tileMode));
        canvas.drawArc(rectF, f4, f5, true, this.mPaint);
    }

    private void drawBottomSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawLeftBottomArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftBottomDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), this.mRoundCornerRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftBottomRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftBottomUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawLeftTopArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
    }

    private void drawLeftTopDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
    }

    private void drawLeftTopRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
    }

    private void drawLeftTopUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
    }

    private void drawRectLinearGradient(Canvas canvas, float f, float f2, float f3, float f4, int[] iArr, float[] fArr, Shader.TileMode tileMode, RectF rectF) {
        this.mPaint.setShader(new LinearGradient(f, f2, f3, f4, iArr, fArr, tileMode));
        canvas.drawRect(rectF, this.mPaint);
    }

    private void drawRightBottomArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightBottomDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightBottomRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightBottomUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawRightTopArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
    }

    private void drawRightTopDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
    }

    private void drawRightTopRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
    }

    private void drawRightTopUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
    }

    private void drawTopSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void initViews(AttributeSet attributeSet) {
        setLayerType(1, (Paint) null);
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.Fp_ShadowLayout);
        if (obtainStyledAttributes != null) {
            this.mShadowColor = obtainStyledAttributes.getColor(R.styleable.Fp_ShadowLayout_fp_shadowColor, getContext().getResources().getColor(17170444));
            this.mShadowRadius = obtainStyledAttributes.getDimension(R.styleable.Fp_ShadowLayout_fp_shadowRadius, dip2px(CropImageView.DEFAULT_ASPECT_RATIO));
            this.mRoundCornerRadius = obtainStyledAttributes.getDimension(R.styleable.Fp_ShadowLayout_fp_shadowRoundRadius, dip2px(CropImageView.DEFAULT_ASPECT_RATIO));
            this.mShadowSide = obtainStyledAttributes.getInt(R.styleable.Fp_ShadowLayout_fp_shadowSide, 4369);
            this.mShadowShape = obtainStyledAttributes.getInt(R.styleable.Fp_ShadowLayout_fp_shadowShape, 1);
            this.mCornerPosition = obtainStyledAttributes.getInt(R.styleable.Fp_ShadowLayout_fp_round_corner, 4369);
            obtainStyledAttributes.recycle();
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        int[] iArr = {this.mShadowColor, 16777215};
        if (this.mShadowShape == 1) {
            float[] fArr = {0.0f, 1.0f};
            this.mPaint.setStrokeWidth(this.mShadowRadius);
            if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) != 4096) {
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
            } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
            }
        } else if (this.mShadowShape == 256) {
            float[] fArr2 = {0.0f, 1.0f};
            if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 4096) == 4096) {
                if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 4096) == 4096) {
                    if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 4096) == 4096) {
                        if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 4096) != 4096) {
                            if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 4096) == 4096) {
                                if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 4096) == 4096) {
                                    if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 4096) != 4096) {
                                        if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 4096) == 4096) {
                                            if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 256) == 256 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 4096) != 4096) {
                                                if ((this.mShadowSide & 1) == 1 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 16) == 16 || (this.mShadowSide & 4096) != 4096) {
                                                    if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) != 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat = creatThreePositionFloat();
                                                            int[] creatThreePositionColor = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor, creatThreePositionFloat, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor, creatThreePositionFloat);
                                                            drawTopSide(canvas, creatThreePositionColor, creatThreePositionFloat, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor, creatThreePositionFloat);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor, creatThreePositionFloat);
                                                            drawRightSide(canvas, creatThreePositionColor, creatThreePositionFloat, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor, creatThreePositionFloat);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat2 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor2 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor2, creatThreePositionFloat2, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor2, creatThreePositionFloat2);
                                                            drawTopSide(canvas, creatThreePositionColor2, creatThreePositionFloat2, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor2, creatThreePositionFloat2, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            Canvas canvas2 = canvas;
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat3 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor3 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor3, creatThreePositionFloat3, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor3, creatThreePositionFloat3);
                                                            drawTopSide(canvas, creatThreePositionColor3, creatThreePositionFloat3, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor3, creatThreePositionFloat3, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            float[] creatTwoPositionFloat = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor, creatTwoPositionFloat);
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor, creatTwoPositionFloat);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat4 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor4 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor4, creatThreePositionFloat4, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, creatThreePositionColor4, creatThreePositionFloat4, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor4, creatThreePositionFloat4, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor4, creatThreePositionFloat4);
                                                            float[] creatTwoPositionFloat2 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor2 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor2, creatTwoPositionFloat2);
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor2, creatTwoPositionFloat2);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat5 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor5 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor5, creatThreePositionFloat5, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, creatThreePositionColor5, creatThreePositionFloat5, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor5, creatThreePositionFloat5, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor5, creatThreePositionFloat5);
                                                            Canvas canvas3 = canvas;
                                                            drawLeftTopRightAngle(canvas3, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat6 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor6 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor6, creatThreePositionFloat6, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor6, creatThreePositionFloat6, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor6, creatThreePositionFloat6, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor6, creatThreePositionFloat6);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor6, creatThreePositionFloat6);
                                                            Canvas canvas4 = canvas;
                                                            drawRightTopRightAngle(canvas4, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat7 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor7 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor7, creatThreePositionFloat7, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, creatThreePositionColor7, creatThreePositionFloat7, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor7, creatThreePositionFloat7, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor7, creatThreePositionFloat7);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor7, creatThreePositionFloat7);
                                                            Canvas canvas5 = canvas;
                                                            drawRightTopRightAngle(canvas5, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat8 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor8 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor8, creatThreePositionFloat8, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, creatThreePositionColor8, creatThreePositionFloat8, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor8, creatThreePositionFloat8, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor8, creatThreePositionFloat8);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor8, creatThreePositionFloat8);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat9 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor9 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor9, creatThreePositionFloat9, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor9, creatThreePositionFloat9, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor9, creatThreePositionFloat9, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor9, creatThreePositionFloat9);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor9, creatThreePositionFloat9);
                                                            float[] creatTwoPositionFloat3 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor3 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor3, creatTwoPositionFloat3);
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor3, creatTwoPositionFloat3);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat10 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor10 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor10, creatThreePositionFloat10, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor10, creatThreePositionFloat10, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor10, creatThreePositionFloat10, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor10, creatThreePositionFloat10);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor10, creatThreePositionFloat10);
                                                            Canvas canvas6 = canvas;
                                                            drawLeftTopRightAngle(canvas6, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat11 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor11 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor11, creatThreePositionFloat11, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, creatThreePositionColor11, creatThreePositionFloat11, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor11, creatThreePositionFloat11, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor11, creatThreePositionFloat11);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor11, creatThreePositionFloat11);
                                                            Canvas canvas7 = canvas;
                                                            drawLeftTopRightAngle(canvas7, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat12 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor12 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor12, creatThreePositionFloat12, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor12, creatThreePositionFloat12);
                                                            drawTopSide(canvas, creatThreePositionColor12, creatThreePositionFloat12, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor12, creatThreePositionFloat12);
                                                            drawRightSide(canvas, creatThreePositionColor12, creatThreePositionFloat12, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor12, creatThreePositionFloat12);
                                                            Canvas canvas8 = canvas;
                                                            drawRightTopRightAngle(canvas8, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat13 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor13 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor13, creatThreePositionFloat13, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor13, creatThreePositionFloat13);
                                                            drawTopSide(canvas, creatThreePositionColor13, creatThreePositionFloat13, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor13, creatThreePositionFloat13);
                                                            drawRightSide(canvas, creatThreePositionColor13, creatThreePositionFloat13, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor13, creatThreePositionFloat13);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat14 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor14 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor14, creatThreePositionFloat14, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor14, creatThreePositionFloat14);
                                                            drawTopSide(canvas, creatThreePositionColor14, creatThreePositionFloat14, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor14, creatThreePositionFloat14, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor14, creatThreePositionFloat14);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor14, creatThreePositionFloat14);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat15 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor15 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor15, creatThreePositionFloat15, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor15, creatThreePositionFloat15, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor15, creatThreePositionFloat15, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor15, creatThreePositionFloat15);
                                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor15, creatThreePositionFloat15);
                                                            drawLeftBottomUpArcGradient(canvas, creatThreePositionColor15, creatThreePositionFloat15);
                                                            Canvas canvas9 = canvas;
                                                            drawLeftTopRightAngle(canvas9, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat16 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor16 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor16, creatThreePositionFloat16, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawTopSide(canvas, creatThreePositionColor16, creatThreePositionFloat16, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawBottomSide(canvas, creatThreePositionColor16, creatThreePositionFloat16, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor16, creatThreePositionFloat16);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat17 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor17 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor17, creatThreePositionFloat17, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor17, creatThreePositionFloat17);
                                                            drawTopSide(canvas, creatThreePositionColor17, creatThreePositionFloat17, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor17, creatThreePositionFloat17, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            Canvas canvas10 = canvas;
                                                            drawLeftBottomRightAngle(canvas10, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat18 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor18 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor18, creatThreePositionFloat18, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor18, creatThreePositionFloat18, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor18, creatThreePositionFloat18, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor18, creatThreePositionFloat18);
                                                            Canvas canvas11 = canvas;
                                                            drawLeftTopRightAngle(canvas11, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat19 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor19 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor19, creatThreePositionFloat19, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, creatThreePositionColor19, creatThreePositionFloat19, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor19, creatThreePositionFloat19, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor19, creatThreePositionFloat19);
                                                            float[] creatTwoPositionFloat4 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor4 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor4, creatTwoPositionFloat4);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor4, creatTwoPositionFloat4);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat20 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor20 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor20, creatThreePositionFloat20, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, creatThreePositionColor20, creatThreePositionFloat20, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor20, creatThreePositionFloat20, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor20, creatThreePositionFloat20);
                                                            float[] creatTwoPositionFloat5 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor5 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor5, creatTwoPositionFloat5);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor5, creatTwoPositionFloat5);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat21 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor21 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor21, creatThreePositionFloat21, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor21, creatThreePositionFloat21);
                                                            drawTopSide(canvas, creatThreePositionColor21, creatThreePositionFloat21, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor21, creatThreePositionFloat21);
                                                            drawBottomSide(canvas, creatThreePositionColor21, creatThreePositionFloat21, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat22 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor22 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor22, creatThreePositionFloat22, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor22, creatThreePositionFloat22);
                                                            drawTopSide(canvas, creatThreePositionColor22, creatThreePositionFloat22, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor22, creatThreePositionFloat22);
                                                            drawBottomSide(canvas, creatThreePositionColor22, creatThreePositionFloat22, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            Canvas canvas12 = canvas;
                                                            drawLeftBottomRightAngle(canvas12, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat23 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor23 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor23, creatThreePositionFloat23, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor23, creatThreePositionFloat23);
                                                            drawTopSide(canvas, creatThreePositionColor23, creatThreePositionFloat23, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor23, creatThreePositionFloat23, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor23, creatThreePositionFloat23);
                                                            Canvas canvas13 = canvas;
                                                            drawLeftBottomRightAngle(canvas13, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat24 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor24 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor24, creatThreePositionFloat24, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor24, creatThreePositionFloat24, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor24, creatThreePositionFloat24, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor24, creatThreePositionFloat24);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor24, creatThreePositionFloat24);
                                                            Canvas canvas14 = canvas;
                                                            drawLeftTopRightAngle(canvas14, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat25 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor25 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor25, creatThreePositionFloat25, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor25, creatThreePositionFloat25, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor25, creatThreePositionFloat25, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor25, creatThreePositionFloat25);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor25, creatThreePositionFloat25);
                                                            Canvas canvas15 = canvas;
                                                            drawLeftTopRightAngle(canvas15, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat26 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor26 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor26, creatThreePositionFloat26, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, creatThreePositionColor26, creatThreePositionFloat26, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor26, creatThreePositionFloat26, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor26, creatThreePositionFloat26);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor26, creatThreePositionFloat26);
                                                            float[] creatTwoPositionFloat6 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor6 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor6, creatTwoPositionFloat6);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor6, creatTwoPositionFloat6);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat27 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor27 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor27, creatThreePositionFloat27, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor27, creatThreePositionFloat27);
                                                            drawTopSide(canvas, creatThreePositionColor27, creatThreePositionFloat27, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor27, creatThreePositionFloat27);
                                                            drawBottomSide(canvas, creatThreePositionColor27, creatThreePositionFloat27, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor27, creatThreePositionFloat27);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat28 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor28 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor28, creatThreePositionFloat28, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor28, creatThreePositionFloat28);
                                                            drawTopSide(canvas, creatThreePositionColor28, creatThreePositionFloat28, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor28, creatThreePositionFloat28);
                                                            drawBottomSide(canvas, creatThreePositionColor28, creatThreePositionFloat28, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor28, creatThreePositionFloat28);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat29 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor29 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor29, creatThreePositionFloat29, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor29, creatThreePositionFloat29);
                                                            drawTopSide(canvas, creatThreePositionColor29, creatThreePositionFloat29, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor29, creatThreePositionFloat29, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor29, creatThreePositionFloat29);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor29, creatThreePositionFloat29);
                                                            Canvas canvas16 = canvas;
                                                            drawLeftBottomRightAngle(canvas16, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat30 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor30 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor30, creatThreePositionFloat30, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, creatThreePositionColor30, creatThreePositionFloat30, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor30, creatThreePositionFloat30, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas, creatThreePositionColor30, creatThreePositionFloat30);
                                                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor30, creatThreePositionFloat30);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor30, creatThreePositionFloat30);
                                                            Canvas canvas17 = canvas;
                                                            drawLeftTopRightAngle(canvas17, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat31 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor31 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor31, creatThreePositionFloat31, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawBottomSide(canvas, creatThreePositionColor31, creatThreePositionFloat31, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawRightSide(canvas, creatThreePositionColor31, creatThreePositionFloat31, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor31, creatThreePositionFloat31);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat32 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor32 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor32, creatThreePositionFloat32, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor32, creatThreePositionFloat32);
                                                            drawBottomSide(canvas, creatThreePositionColor32, creatThreePositionFloat32, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor32, creatThreePositionFloat32, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat7 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor7 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor7, creatTwoPositionFloat7);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor7, creatTwoPositionFloat7);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat33 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor33 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor33, creatThreePositionFloat33, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor33, creatThreePositionFloat33);
                                                            drawBottomSide(canvas, creatThreePositionColor33, creatThreePositionFloat33, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor33, creatThreePositionFloat33, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            Canvas canvas18 = canvas;
                                                            drawRightBottomRightAngle(canvas18, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat34 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor34 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor34, creatThreePositionFloat34, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor34, creatThreePositionFloat34, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor34, creatThreePositionFloat34, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor34, creatThreePositionFloat34);
                                                            Canvas canvas19 = canvas;
                                                            drawLeftBottomRightAngle(canvas19, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat35 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor35 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor35, creatThreePositionFloat35, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor35, creatThreePositionFloat35, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor35, creatThreePositionFloat35, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor35, creatThreePositionFloat35);
                                                            float[] creatTwoPositionFloat8 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor8 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor8, creatTwoPositionFloat8);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor8, creatTwoPositionFloat8);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat36 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor36 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor36, creatThreePositionFloat36, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor36, creatThreePositionFloat36);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor36, creatThreePositionFloat36);
                                                            drawBottomSide(canvas, creatThreePositionColor36, creatThreePositionFloat36, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor36, creatThreePositionFloat36, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            Canvas canvas20 = canvas;
                                                            drawRightBottomRightAngle(canvas20, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat37 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor37 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor37, creatThreePositionFloat37, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor37, creatThreePositionFloat37);
                                                            drawBottomSide(canvas, creatThreePositionColor37, creatThreePositionFloat37, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor37, creatThreePositionFloat37, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor37, creatThreePositionFloat37);
                                                            Canvas canvas21 = canvas;
                                                            drawLeftBottomRightAngle(canvas21, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat38 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor38 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor38, creatThreePositionFloat38, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor38, creatThreePositionFloat38);
                                                            drawBottomSide(canvas, creatThreePositionColor38, creatThreePositionFloat38, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor38, creatThreePositionFloat38, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor38, creatThreePositionFloat38);
                                                            float[] creatTwoPositionFloat9 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor9 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor9, creatTwoPositionFloat9);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor9, creatTwoPositionFloat9);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat39 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor39 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor39, creatThreePositionFloat39, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mRoundCornerRadius + this.mShadowRadius));
                                                            drawBottomSide(canvas, creatThreePositionColor39, creatThreePositionFloat39, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor39, creatThreePositionFloat39, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor39, creatThreePositionFloat39);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor39, creatThreePositionFloat39);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat40 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor40 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor40, creatThreePositionFloat40, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mRoundCornerRadius + this.mShadowRadius));
                                                            drawBottomSide(canvas, creatThreePositionColor40, creatThreePositionFloat40, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor40, creatThreePositionFloat40, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor40, creatThreePositionFloat40);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor40, creatThreePositionFloat40);
                                                            Canvas canvas22 = canvas;
                                                            drawRightBottomRightAngle(canvas22, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat41 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor41 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor41, creatThreePositionFloat41, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor41, creatThreePositionFloat41, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor41, creatThreePositionFloat41, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor41, creatThreePositionFloat41);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor41, creatThreePositionFloat41);
                                                            Canvas canvas23 = canvas;
                                                            drawLeftBottomRightAngle(canvas23, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat42 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor42 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor42, creatThreePositionFloat42, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor42, creatThreePositionFloat42);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor42, creatThreePositionFloat42);
                                                            drawBottomSide(canvas, creatThreePositionColor42, creatThreePositionFloat42, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor42, creatThreePositionFloat42, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor42, creatThreePositionFloat42);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat43 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor43 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor43, creatThreePositionFloat43, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor43, creatThreePositionFloat43);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor43, creatThreePositionFloat43);
                                                            drawBottomSide(canvas, creatThreePositionColor43, creatThreePositionFloat43, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor43, creatThreePositionFloat43, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor43, creatThreePositionFloat43);
                                                            Canvas canvas24 = canvas;
                                                            drawRightBottomRightAngle(canvas24, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat44 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor44 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor44, creatThreePositionFloat44, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas, creatThreePositionColor44, creatThreePositionFloat44);
                                                            drawBottomSide(canvas, creatThreePositionColor44, creatThreePositionFloat44, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor44, creatThreePositionFloat44);
                                                            drawRightSide(canvas, creatThreePositionColor44, creatThreePositionFloat44, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor44, creatThreePositionFloat44);
                                                            Canvas canvas25 = canvas;
                                                            drawLeftBottomRightAngle(canvas25, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat45 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor45 = creatThreePositionColor();
                                                            drawLeftSide(canvas, creatThreePositionColor45, creatThreePositionFloat45, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor45, creatThreePositionFloat45);
                                                            drawBottomSide(canvas, creatThreePositionColor45, creatThreePositionFloat45, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas, creatThreePositionColor45, creatThreePositionFloat45);
                                                            drawRightSide(canvas, creatThreePositionColor45, creatThreePositionFloat45, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor45, creatThreePositionFloat45);
                                                        }
                                                    } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat46 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor46 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawTopSide(canvas, creatThreePositionColor46, creatThreePositionFloat46, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawRightSide(canvas, creatThreePositionColor46, creatThreePositionFloat46, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawBottomSide(canvas, creatThreePositionColor46, creatThreePositionFloat46, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat47 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor47 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor47, creatThreePositionFloat47);
                                                            drawTopSide(canvas, creatThreePositionColor47, creatThreePositionFloat47, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor47, creatThreePositionFloat47, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor47, creatThreePositionFloat47, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            float[] creatTwoPositionFloat10 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor10 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor10, creatTwoPositionFloat10);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor10, creatTwoPositionFloat10);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat48 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor48 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor48, creatThreePositionFloat48, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor48, creatThreePositionFloat48, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor48, creatThreePositionFloat48, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor48, creatThreePositionFloat48);
                                                            float[] creatTwoPositionFloat11 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor11 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor11, creatTwoPositionFloat11);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor11, creatTwoPositionFloat11);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat49 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor49 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor49, creatThreePositionFloat49, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor49, creatThreePositionFloat49, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor49, creatThreePositionFloat49, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor49, creatThreePositionFloat49);
                                                            Canvas canvas26 = canvas;
                                                            drawRightTopRightAngle(canvas26, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat50 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor50 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor50, creatThreePositionFloat50, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor50, creatThreePositionFloat50, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor50, creatThreePositionFloat50, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor50, creatThreePositionFloat50);
                                                            Canvas canvas27 = canvas;
                                                            drawRightBottomRightAngle(canvas27, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat51 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor51 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor51, creatThreePositionFloat51, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor51, creatThreePositionFloat51, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor51, creatThreePositionFloat51, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor51, creatThreePositionFloat51);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor51, creatThreePositionFloat51);
                                                            float[] creatTwoPositionFloat12 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor12 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor12, creatTwoPositionFloat12);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor12, creatTwoPositionFloat12);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat52 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor52 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor52, creatThreePositionFloat52, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor52, creatThreePositionFloat52, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor52, creatThreePositionFloat52, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor52, creatThreePositionFloat52);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor52, creatThreePositionFloat52);
                                                            Canvas canvas28 = canvas;
                                                            drawRightTopRightAngle(canvas28, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat53 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor53 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor53, creatThreePositionFloat53, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor53, creatThreePositionFloat53, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor53, creatThreePositionFloat53, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor53, creatThreePositionFloat53);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor53, creatThreePositionFloat53);
                                                            Canvas canvas29 = canvas;
                                                            drawRightBottomRightAngle(canvas29, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat54 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor54 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor54, creatThreePositionFloat54, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor54, creatThreePositionFloat54, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor54, creatThreePositionFloat54, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor54, creatThreePositionFloat54);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor54, creatThreePositionFloat54);
                                                            Canvas canvas30 = canvas;
                                                            drawRightTopRightAngle(canvas30, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat55 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor55 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor55, creatThreePositionFloat55, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor55, creatThreePositionFloat55, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor55, creatThreePositionFloat55, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor55, creatThreePositionFloat55);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor55, creatThreePositionFloat55);
                                                            Canvas canvas31 = canvas;
                                                            drawRightBottomRightAngle(canvas31, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat56 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor56 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor56, creatThreePositionFloat56, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, creatThreePositionColor56, creatThreePositionFloat56, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor56, creatThreePositionFloat56, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor56, creatThreePositionFloat56);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor56, creatThreePositionFloat56);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat57 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor57 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor57, creatThreePositionFloat57);
                                                            drawTopSide(canvas, creatThreePositionColor57, creatThreePositionFloat57, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor57, creatThreePositionFloat57);
                                                            drawRightSide(canvas, creatThreePositionColor57, creatThreePositionFloat57, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor57, creatThreePositionFloat57);
                                                            drawBottomSide(canvas, creatThreePositionColor57, creatThreePositionFloat57, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            Canvas canvas32 = canvas;
                                                            drawRightTopRightAngle(canvas32, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat58 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor58 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor58, creatThreePositionFloat58);
                                                            drawTopSide(canvas, creatThreePositionColor58, creatThreePositionFloat58, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor58, creatThreePositionFloat58);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor58, creatThreePositionFloat58);
                                                            drawRightSide(canvas, creatThreePositionColor58, creatThreePositionFloat58, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor58, creatThreePositionFloat58, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            Canvas canvas33 = canvas;
                                                            drawRightBottomRightAngle(canvas33, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat59 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor59 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor59, creatThreePositionFloat59);
                                                            drawTopSide(canvas, creatThreePositionColor59, creatThreePositionFloat59, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor59, creatThreePositionFloat59);
                                                            drawRightSide(canvas, creatThreePositionColor59, creatThreePositionFloat59, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor59, creatThreePositionFloat59);
                                                            drawBottomSide(canvas, creatThreePositionColor59, creatThreePositionFloat59, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat60 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor60 = creatThreePositionColor();
                                                            drawTopSide(canvas, creatThreePositionColor60, creatThreePositionFloat60, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawRightSide(canvas, creatThreePositionColor60, creatThreePositionFloat60, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawBottomSide(canvas, creatThreePositionColor60, creatThreePositionFloat60, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        }
                                                    } else if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 4096) != 4096) {
                                                    } else {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat61 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor61 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor61, creatThreePositionFloat61);
                                                            drawTopSide(canvas, creatThreePositionColor61, creatThreePositionFloat61, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor61, creatThreePositionFloat61, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor61, creatThreePositionFloat61);
                                                            drawBottomSide(canvas, creatThreePositionColor61, creatThreePositionFloat61, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor61, creatThreePositionFloat61);
                                                            drawRightSide(canvas, creatThreePositionColor61, creatThreePositionFloat61, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor61, creatThreePositionFloat61);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat62 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor62 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor62, creatThreePositionFloat62);
                                                            drawTopSide(canvas, creatThreePositionColor62, creatThreePositionFloat62, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor62, creatThreePositionFloat62, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor62, creatThreePositionFloat62, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor62, creatThreePositionFloat62, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat13 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor13 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat63 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor63 = creatThreePositionColor();
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor63, creatThreePositionFloat63);
                                                            drawTopSide(canvas, creatThreePositionColor63, creatThreePositionFloat63, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor63, creatThreePositionFloat63, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor63, creatThreePositionFloat63, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor63, creatThreePositionFloat63, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat14 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor14 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat64 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor64 = creatThreePositionColor();
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor64, creatThreePositionFloat64);
                                                            drawTopSide(canvas, creatThreePositionColor64, creatThreePositionFloat64, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor64, creatThreePositionFloat64, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor64, creatThreePositionFloat64, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor64, creatThreePositionFloat64, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat15 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor15 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat65 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor65 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor65, creatThreePositionFloat65);
                                                            drawTopSide(canvas, creatThreePositionColor65, creatThreePositionFloat65, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor65, creatThreePositionFloat65, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor65, creatThreePositionFloat65, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor65, creatThreePositionFloat65, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat16 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor16 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat66 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor66 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor66, creatThreePositionFloat66);
                                                            drawTopSide(canvas, creatThreePositionColor66, creatThreePositionFloat66, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor66, creatThreePositionFloat66, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor66, creatThreePositionFloat66);
                                                            drawBottomSide(canvas, creatThreePositionColor66, creatThreePositionFloat66, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor66, creatThreePositionFloat66, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat17 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor17 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor17, creatTwoPositionFloat17);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor17, creatTwoPositionFloat17);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat67 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor67 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor67, creatThreePositionFloat67);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor67, creatThreePositionFloat67);
                                                            drawTopSide(canvas, creatThreePositionColor67, creatThreePositionFloat67, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor67, creatThreePositionFloat67, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor67, creatThreePositionFloat67, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor67, creatThreePositionFloat67, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat18 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor18 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor18, creatTwoPositionFloat18);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor18, creatTwoPositionFloat18);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat68 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor68 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor68, creatThreePositionFloat68);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor68, creatThreePositionFloat68);
                                                            drawTopSide(canvas, creatThreePositionColor68, creatThreePositionFloat68, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor68, creatThreePositionFloat68, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor68, creatThreePositionFloat68, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor68, creatThreePositionFloat68, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat19 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor19 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor19, creatTwoPositionFloat19);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor19, creatTwoPositionFloat19);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat69 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor69 = creatThreePositionColor();
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor69, creatThreePositionFloat69);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor69, creatThreePositionFloat69);
                                                            drawTopSide(canvas, creatThreePositionColor69, creatThreePositionFloat69, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor69, creatThreePositionFloat69, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor69, creatThreePositionFloat69, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor69, creatThreePositionFloat69, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat20 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor20 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor20, creatTwoPositionFloat20);
                                                            drawRightTopRightAngle(canvas, creatTwoPositionColor20, creatTwoPositionFloat20);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat70 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor70 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor70, creatThreePositionFloat70);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor70, creatThreePositionFloat70);
                                                            drawTopSide(canvas, creatThreePositionColor70, creatThreePositionFloat70, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor70, creatThreePositionFloat70, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor70, creatThreePositionFloat70, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor70, creatThreePositionFloat70, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat21 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor21 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor21, creatTwoPositionFloat21);
                                                            drawRightBottomRightAngle(canvas, creatTwoPositionColor21, creatTwoPositionFloat21);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat71 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor71 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor71, creatThreePositionFloat71);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor71, creatThreePositionFloat71);
                                                            drawTopSide(canvas, creatThreePositionColor71, creatThreePositionFloat71, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor71, creatThreePositionFloat71, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor71, creatThreePositionFloat71, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor71, creatThreePositionFloat71, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat22 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor22 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas, creatTwoPositionColor22, creatTwoPositionFloat22);
                                                            drawLeftBottomRightAngle(canvas, creatTwoPositionColor22, creatTwoPositionFloat22);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat72 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor72 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor72, creatThreePositionFloat72);
                                                            drawTopSide(canvas, creatThreePositionColor72, creatThreePositionFloat72, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor72, creatThreePositionFloat72, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor72, creatThreePositionFloat72);
                                                            drawBottomSide(canvas, creatThreePositionColor72, creatThreePositionFloat72, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor72, creatThreePositionFloat72);
                                                            drawRightSide(canvas, creatThreePositionColor72, creatThreePositionFloat72, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            Canvas canvas34 = canvas;
                                                            drawRightTopRightAngle(canvas34, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat73 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor73 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor73, creatThreePositionFloat73);
                                                            drawTopSide(canvas, creatThreePositionColor73, creatThreePositionFloat73, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor73, creatThreePositionFloat73, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor73, creatThreePositionFloat73);
                                                            drawBottomSide(canvas, creatThreePositionColor73, creatThreePositionFloat73, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor73, creatThreePositionFloat73, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor73, creatThreePositionFloat73);
                                                            Canvas canvas35 = canvas;
                                                            drawRightBottomRightAngle(canvas35, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat74 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor74 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas, creatThreePositionColor74, creatThreePositionFloat74);
                                                            drawTopSide(canvas, creatThreePositionColor74, creatThreePositionFloat74, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor74, creatThreePositionFloat74, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor74, creatThreePositionFloat74, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor74, creatThreePositionFloat74, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor74, creatThreePositionFloat74);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor74, creatThreePositionFloat74);
                                                            Canvas canvas36 = canvas;
                                                            drawLeftBottomRightAngle(canvas36, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat75 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor75 = creatThreePositionColor();
                                                            drawLeftBottomArcGradient(canvas, creatThreePositionColor75, creatThreePositionFloat75);
                                                            drawTopSide(canvas, creatThreePositionColor75, creatThreePositionFloat75, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, creatThreePositionColor75, creatThreePositionFloat75, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, creatThreePositionColor75, creatThreePositionFloat75, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, creatThreePositionColor75, creatThreePositionFloat75, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas, creatThreePositionColor75, creatThreePositionFloat75);
                                                            drawRightBottomArcGradient(canvas, creatThreePositionColor75, creatThreePositionFloat75);
                                                            Canvas canvas37 = canvas;
                                                            drawLeftTopRightAngle(canvas37, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    }
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat76 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor76 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas, creatThreePositionColor76, creatThreePositionFloat76);
                                                    drawRightSide(canvas, creatThreePositionColor76, creatThreePositionFloat76, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas, creatThreePositionColor76, creatThreePositionFloat76);
                                                    drawBottomSide(canvas, creatThreePositionColor76, creatThreePositionFloat76, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas, creatThreePositionColor76, creatThreePositionFloat76);
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat77 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor77 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas, creatThreePositionColor77, creatThreePositionFloat77);
                                                    drawRightSide(canvas, creatThreePositionColor77, creatThreePositionFloat77, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, creatThreePositionColor77, creatThreePositionFloat77, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    Canvas canvas38 = canvas;
                                                    drawRightBottomRightAngle(canvas38, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat78 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor78 = creatThreePositionColor();
                                                    drawRightSide(canvas, creatThreePositionColor78, creatThreePositionFloat78, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas, creatThreePositionColor78, creatThreePositionFloat78);
                                                    drawBottomSide(canvas, creatThreePositionColor78, creatThreePositionFloat78, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat79 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor79 = creatThreePositionColor();
                                                    drawRightSide(canvas, creatThreePositionColor79, creatThreePositionFloat79, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, creatThreePositionColor79, creatThreePositionFloat79, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas, creatThreePositionColor79, creatThreePositionFloat79);
                                                    Canvas canvas39 = canvas;
                                                    drawRightBottomRightAngle(canvas39, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat80 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor80 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas, creatThreePositionColor80, creatThreePositionFloat80);
                                                    drawRightSide(canvas, creatThreePositionColor80, creatThreePositionFloat80, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas, creatThreePositionColor80, creatThreePositionFloat80);
                                                    drawBottomSide(canvas, creatThreePositionColor80, creatThreePositionFloat80, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat81 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor81 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas, creatThreePositionColor81, creatThreePositionFloat81);
                                                    drawRightSide(canvas, creatThreePositionColor81, creatThreePositionFloat81, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, creatThreePositionColor81, creatThreePositionFloat81, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas, creatThreePositionColor81, creatThreePositionFloat81);
                                                    Canvas canvas40 = canvas;
                                                    drawRightBottomRightAngle(canvas40, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat82 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor82 = creatThreePositionColor();
                                                    drawRightSide(canvas, creatThreePositionColor82, creatThreePositionFloat82, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas, creatThreePositionColor82, creatThreePositionFloat82);
                                                    drawBottomSide(canvas, creatThreePositionColor82, creatThreePositionFloat82, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas, creatThreePositionColor82, creatThreePositionFloat82);
                                                }
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat83 = creatThreePositionFloat();
                                                int[] creatThreePositionColor83 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor83, creatThreePositionFloat83);
                                                drawTopSide(canvas, creatThreePositionColor83, creatThreePositionFloat83, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor83, creatThreePositionFloat83);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor83, creatThreePositionFloat83);
                                                drawBottomSide(canvas, creatThreePositionColor83, creatThreePositionFloat83, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor83, creatThreePositionFloat83);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat84 = creatThreePositionFloat();
                                                int[] creatThreePositionColor84 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor84, creatThreePositionFloat84, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor84, creatThreePositionFloat84);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor84, creatThreePositionFloat84);
                                                drawBottomSide(canvas, creatThreePositionColor84, creatThreePositionFloat84, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor84, creatThreePositionFloat84);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat85 = creatThreePositionFloat();
                                                int[] creatThreePositionColor85 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor85, creatThreePositionFloat85);
                                                drawTopSide(canvas, creatThreePositionColor85, creatThreePositionFloat85, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor85, creatThreePositionFloat85);
                                                drawBottomSide(canvas, creatThreePositionColor85, creatThreePositionFloat85, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor85, creatThreePositionFloat85);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat86 = creatThreePositionFloat();
                                                int[] creatThreePositionColor86 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor86, creatThreePositionFloat86);
                                                drawTopSide(canvas, creatThreePositionColor86, creatThreePositionFloat86, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor86, creatThreePositionFloat86);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor86, creatThreePositionFloat86);
                                                drawBottomSide(canvas, creatThreePositionColor86, creatThreePositionFloat86, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat87 = creatThreePositionFloat();
                                                int[] creatThreePositionColor87 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor87, creatThreePositionFloat87);
                                                drawTopSide(canvas, creatThreePositionColor87, creatThreePositionFloat87, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor87, creatThreePositionFloat87);
                                                drawBottomSide(canvas, creatThreePositionColor87, creatThreePositionFloat87, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor87, creatThreePositionFloat87);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat88 = creatThreePositionFloat();
                                                int[] creatThreePositionColor88 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor88, creatThreePositionFloat88, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor88, creatThreePositionFloat88);
                                                drawBottomSide(canvas, creatThreePositionColor88, creatThreePositionFloat88, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor88, creatThreePositionFloat88);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat89 = creatThreePositionFloat();
                                                int[] creatThreePositionColor89 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor89, creatThreePositionFloat89, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor89, creatThreePositionFloat89);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor89, creatThreePositionFloat89);
                                                drawBottomSide(canvas, creatThreePositionColor89, creatThreePositionFloat89, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat90 = creatThreePositionFloat();
                                                int[] creatThreePositionColor90 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor90, creatThreePositionFloat90, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor90, creatThreePositionFloat90);
                                                drawBottomSide(canvas, creatThreePositionColor90, creatThreePositionFloat90, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor90, creatThreePositionFloat90);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat91 = creatThreePositionFloat();
                                                int[] creatThreePositionColor91 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor91, creatThreePositionFloat91);
                                                drawTopSide(canvas, creatThreePositionColor91, creatThreePositionFloat91, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor91, creatThreePositionFloat91);
                                                drawBottomSide(canvas, creatThreePositionColor91, creatThreePositionFloat91, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat92 = creatThreePositionFloat();
                                                int[] creatThreePositionColor92 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor92, creatThreePositionFloat92);
                                                drawTopSide(canvas, creatThreePositionColor92, creatThreePositionFloat92, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, creatThreePositionColor92, creatThreePositionFloat92, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor92, creatThreePositionFloat92);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat93 = creatThreePositionFloat();
                                                int[] creatThreePositionColor93 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor93, creatThreePositionFloat93);
                                                drawTopSide(canvas, creatThreePositionColor93, creatThreePositionFloat93, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor93, creatThreePositionFloat93);
                                                drawBottomSide(canvas, creatThreePositionColor93, creatThreePositionFloat93, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat94 = creatThreePositionFloat();
                                                int[] creatThreePositionColor94 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor94, creatThreePositionFloat94, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas, creatThreePositionColor94, creatThreePositionFloat94);
                                                drawBottomSide(canvas, creatThreePositionColor94, creatThreePositionFloat94, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat95 = creatThreePositionFloat();
                                                int[] creatThreePositionColor95 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor95, creatThreePositionFloat95, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, creatThreePositionColor95, creatThreePositionFloat95, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas, creatThreePositionColor95, creatThreePositionFloat95);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat96 = creatThreePositionFloat();
                                                int[] creatThreePositionColor96 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas, creatThreePositionColor96, creatThreePositionFloat96);
                                                drawTopSide(canvas, creatThreePositionColor96, creatThreePositionFloat96, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, creatThreePositionColor96, creatThreePositionFloat96, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat97 = creatThreePositionFloat();
                                                int[] creatThreePositionColor97 = creatThreePositionColor();
                                                drawTopSide(canvas, creatThreePositionColor97, creatThreePositionFloat97, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas, creatThreePositionColor97, creatThreePositionFloat97);
                                                drawBottomSide(canvas, creatThreePositionColor97, creatThreePositionFloat97, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            }
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat98 = creatThreePositionFloat();
                                            int[] creatThreePositionColor98 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor98, creatThreePositionFloat98);
                                            drawTopSide(canvas, creatThreePositionColor98, creatThreePositionFloat98, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightTopArcGradient(canvas, creatThreePositionColor98, creatThreePositionFloat98);
                                            drawRightSide(canvas, creatThreePositionColor98, creatThreePositionFloat98, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor98, creatThreePositionFloat98);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat99 = creatThreePositionFloat();
                                            int[] creatThreePositionColor99 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor99, creatThreePositionFloat99);
                                            drawTopSide(canvas, creatThreePositionColor99, creatThreePositionFloat99, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightTopArcGradient(canvas, creatThreePositionColor99, creatThreePositionFloat99);
                                            drawRightSide(canvas, creatThreePositionColor99, creatThreePositionFloat99, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat100 = creatThreePositionFloat();
                                            int[] creatThreePositionColor100 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor100, creatThreePositionFloat100);
                                            drawTopSide(canvas, creatThreePositionColor100, creatThreePositionFloat100, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor100, creatThreePositionFloat100, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor100, creatThreePositionFloat100);
                                            float[] creatTwoPositionFloat23 = creatTwoPositionFloat();
                                            int[] creatTwoPositionColor23 = creatTwoPositionColor();
                                            drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor23, creatTwoPositionFloat23, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat101 = creatThreePositionFloat();
                                            int[] creatThreePositionColor101 = creatThreePositionColor();
                                            drawTopSide(canvas, creatThreePositionColor101, creatThreePositionFloat101, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor101, creatThreePositionFloat101, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor101, creatThreePositionFloat101);
                                            drawRightTopArcGradient(canvas, creatThreePositionColor101, creatThreePositionFloat101);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat102 = creatThreePositionFloat();
                                            int[] creatThreePositionColor102 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas, creatThreePositionColor102, creatThreePositionFloat102);
                                            drawTopSide(canvas, creatThreePositionColor102, creatThreePositionFloat102, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor102, creatThreePositionFloat102, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            float[] creatTwoPositionFloat24 = creatTwoPositionFloat();
                                            int[] creatTwoPositionColor24 = creatTwoPositionColor();
                                            drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor24, creatTwoPositionFloat24, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat103 = creatThreePositionFloat();
                                            int[] creatThreePositionColor103 = creatThreePositionColor();
                                            drawTopSide(canvas, creatThreePositionColor103, creatThreePositionFloat103, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor103, creatThreePositionFloat103, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            drawRightTopArcGradient(canvas, creatThreePositionColor103, creatThreePositionFloat103);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat104 = creatThreePositionFloat();
                                            int[] creatThreePositionColor104 = creatThreePositionColor();
                                            drawTopSide(canvas, creatThreePositionColor104, creatThreePositionFloat104, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor104, creatThreePositionFloat104, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas, creatThreePositionColor104, creatThreePositionFloat104);
                                            float[] creatTwoPositionFloat25 = creatTwoPositionFloat();
                                            int[] creatTwoPositionColor25 = creatTwoPositionColor();
                                            drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor25, creatTwoPositionFloat25, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                                        }
                                    } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096) {
                                        float[] creatThreePositionFloat105 = creatThreePositionFloat();
                                        int[] creatThreePositionColor105 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor105, creatThreePositionFloat105, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor105, creatThreePositionFloat105, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatThreePositionColor105, creatThreePositionFloat105, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        Canvas canvas41 = canvas;
                                        drawArcRadialGradient(canvas41, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor105, creatThreePositionFloat105, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        Canvas canvas42 = canvas;
                                        drawArcRadialGradient(canvas42, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor105, creatThreePositionFloat105, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096) {
                                        float[] creatThreePositionFloat106 = creatThreePositionFloat();
                                        int[] creatThreePositionColor106 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor106, creatThreePositionFloat106, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor106, creatThreePositionFloat106, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                        float[] creatTwoPositionFloat26 = creatTwoPositionFloat();
                                        int[] creatTwoPositionColor26 = creatTwoPositionColor();
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatTwoPositionColor26, creatTwoPositionFloat26, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor26, creatTwoPositionFloat26, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096) {
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                        float[] creatThreePositionFloat107 = creatThreePositionFloat();
                                        Canvas canvas43 = canvas;
                                        drawArcRadialGradient(canvas43, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat107, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096) {
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        float[] creatThreePositionFloat108 = creatThreePositionFloat();
                                        Canvas canvas44 = canvas;
                                        drawArcRadialGradient(canvas44, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat108, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096) {
                                        float[] creatThreePositionFloat109 = creatThreePositionFloat();
                                        int[] creatThreePositionColor107 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor107, creatThreePositionFloat109, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor107, creatThreePositionFloat109, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                        float[] creatTwoPositionFloat27 = creatTwoPositionFloat();
                                        int[] creatTwoPositionColor27 = creatTwoPositionColor();
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatTwoPositionColor27, creatTwoPositionFloat27, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor27, creatTwoPositionFloat27, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        float[] creatThreePositionFloat110 = creatThreePositionFloat();
                                        Canvas canvas45 = canvas;
                                        drawArcRadialGradient(canvas45, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat110, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096) {
                                        float[] creatThreePositionFloat111 = creatThreePositionFloat();
                                        int[] creatThreePositionColor108 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), (float) this.mViewWidth, (float) this.mViewHeight));
                                        Canvas canvas46 = canvas;
                                        drawArcRadialGradient(canvas46, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096) {
                                        float[] creatThreePositionFloat112 = creatThreePositionFloat();
                                        int[] creatThreePositionColor109 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        Canvas canvas47 = canvas;
                                        drawArcRadialGradient(canvas47, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        Canvas canvas48 = canvas;
                                        drawArcRadialGradient(canvas48, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else {
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    }
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat113 = creatThreePositionFloat();
                                    int[] creatThreePositionColor110 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas49 = canvas;
                                    drawArcRadialGradient(canvas49, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas50 = canvas;
                                    drawArcRadialGradient(canvas50, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas51 = canvas;
                                    drawArcRadialGradient(canvas51, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat114 = creatThreePositionFloat();
                                    int[] creatThreePositionColor111 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor111, creatThreePositionFloat114, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor111, creatThreePositionFloat114, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    float[] creatTwoPositionFloat28 = creatTwoPositionFloat();
                                    int[] creatTwoPositionColor28 = creatTwoPositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatTwoPositionColor28, creatTwoPositionFloat28, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat115 = creatThreePositionFloat();
                                    int[] creatThreePositionColor112 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor112, creatThreePositionFloat115, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas52 = canvas;
                                    drawArcRadialGradient(canvas52, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor112, creatThreePositionFloat115, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    float[] creatTwoPositionFloat29 = creatTwoPositionFloat();
                                    int[] creatTwoPositionColor29 = creatTwoPositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatTwoPositionColor29, creatTwoPositionFloat29, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat116 = creatThreePositionFloat();
                                    int[] creatThreePositionColor113 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor113, creatThreePositionFloat116, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor113, creatThreePositionFloat116, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas53 = canvas;
                                    drawArcRadialGradient(canvas53, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor113, creatThreePositionFloat116, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    float[] creatTwoPositionFloat30 = creatTwoPositionFloat();
                                    int[] creatTwoPositionColor30 = creatTwoPositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatTwoPositionColor30, creatTwoPositionFloat30, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat117 = creatThreePositionFloat();
                                    int[] creatThreePositionColor114 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas54 = canvas;
                                    drawArcRadialGradient(canvas54, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas55 = canvas;
                                    drawArcRadialGradient(canvas55, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat118 = creatThreePositionFloat();
                                    int[] creatThreePositionColor115 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas56 = canvas;
                                    drawArcRadialGradient(canvas56, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas57 = canvas;
                                    drawArcRadialGradient(canvas57, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat119 = creatThreePositionFloat();
                                    int[] creatThreePositionColor116 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor116, creatThreePositionFloat119, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas58 = canvas;
                                    drawArcRadialGradient(canvas58, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor116, creatThreePositionFloat119, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat120 = creatThreePositionFloat();
                                    int[] creatThreePositionColor117 = creatThreePositionColor();
                                    Canvas canvas59 = canvas;
                                    drawArcRadialGradient(canvas59, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor117, creatThreePositionFloat120, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor117, creatThreePositionFloat120, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat121 = creatThreePositionFloat();
                                    int[] creatThreePositionColor118 = creatThreePositionColor();
                                    Canvas canvas60 = canvas;
                                    drawArcRadialGradient(canvas60, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor118, creatThreePositionFloat121, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor118, creatThreePositionFloat121, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas61 = canvas;
                                    drawArcRadialGradient(canvas61, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor118, creatThreePositionFloat121, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat122 = creatThreePositionFloat();
                                    int[] creatThreePositionColor119 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas62 = canvas;
                                    drawArcRadialGradient(canvas62, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas63 = canvas;
                                    drawArcRadialGradient(canvas63, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat123 = creatThreePositionFloat();
                                    int[] creatThreePositionColor120 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas64 = canvas;
                                    drawArcRadialGradient(canvas64, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas65 = canvas;
                                    drawArcRadialGradient(canvas65, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas66 = canvas;
                                    drawArcRadialGradient(canvas66, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat124 = creatThreePositionFloat();
                                    int[] creatThreePositionColor121 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas67 = canvas;
                                    drawArcRadialGradient(canvas67, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas68 = canvas;
                                    drawArcRadialGradient(canvas68, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat125 = creatThreePositionFloat();
                                    int[] creatThreePositionColor122 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas69 = canvas;
                                    drawArcRadialGradient(canvas69, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas70 = canvas;
                                    drawArcRadialGradient(canvas70, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat126 = creatThreePositionFloat();
                                    int[] creatThreePositionColor123 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas71 = canvas;
                                    drawArcRadialGradient(canvas71, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) != 1 || (this.mCornerPosition & 16) == 16 || (this.mCornerPosition & 256) == 256 || (this.mCornerPosition & 4096) != 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else {
                                    float[] creatThreePositionFloat127 = creatThreePositionFloat();
                                    int[] creatThreePositionColor124 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas72 = canvas;
                                    drawArcRadialGradient(canvas72, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                }
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat128 = creatThreePositionFloat();
                                int[] creatThreePositionColor125 = creatThreePositionColor();
                                drawLeftSide(canvas, creatThreePositionColor125, creatThreePositionFloat128, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawTopSide(canvas, creatThreePositionColor125, creatThreePositionFloat128, this.mRoundCornerRadius + this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftTopArcGradient(canvas, creatThreePositionColor125, creatThreePositionFloat128);
                                drawLeftBottomUpArcGradient(canvas, creatThreePositionColor125, creatThreePositionFloat128);
                                drawRightTopUpArcGradient(canvas, creatThreePositionColor125, creatThreePositionFloat128);
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat129 = creatThreePositionFloat();
                                int[] creatThreePositionColor126 = creatThreePositionColor();
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius));
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat130 = creatThreePositionFloat();
                                int[] creatThreePositionColor127 = creatThreePositionColor();
                                drawLeftSide(canvas, creatThreePositionColor127, creatThreePositionFloat130, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawTopSide(canvas, creatThreePositionColor127, creatThreePositionFloat130, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftBottomUpArcGradient(canvas, creatThreePositionColor127, creatThreePositionFloat130);
                                Canvas canvas73 = canvas;
                                drawLeftTopRightAngle(canvas73, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat131 = creatThreePositionFloat();
                                int[] creatThreePositionColor128 = creatThreePositionColor();
                                drawLeftSide(canvas, creatThreePositionColor128, creatThreePositionFloat131, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                drawTopSide(canvas, creatThreePositionColor128, creatThreePositionFloat131, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawRightTopUpArcGradient(canvas, creatThreePositionColor128, creatThreePositionFloat131);
                                Canvas canvas74 = canvas;
                                drawLeftTopRightAngle(canvas74, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat132 = creatThreePositionFloat();
                                int[] creatThreePositionColor129 = creatThreePositionColor();
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius));
                                Canvas canvas75 = canvas;
                                drawArcRadialGradient(canvas75, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat133 = creatThreePositionFloat();
                                int[] creatThreePositionColor130 = creatThreePositionColor();
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor130, creatThreePositionFloat133, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor130, creatThreePositionFloat133, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor130, creatThreePositionFloat133, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                Canvas canvas76 = canvas;
                                drawArcRadialGradient(canvas76, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor130, creatThreePositionFloat133, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat134 = creatThreePositionFloat();
                                int[] creatThreePositionColor131 = creatThreePositionColor();
                                drawTopSide(canvas, creatThreePositionColor131, creatThreePositionFloat134, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftSide(canvas, creatThreePositionColor131, creatThreePositionFloat134, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawLeftBottomUpArcGradient(canvas, creatThreePositionColor131, creatThreePositionFloat134);
                                drawRightTopUpArcGradient(canvas, creatThreePositionColor131, creatThreePositionFloat134);
                                Canvas canvas77 = canvas;
                                drawLeftTopRightAngle(canvas77, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else {
                                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                            }
                        } else if ((this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096) {
                            float[] creatThreePositionFloat135 = creatThreePositionFloat();
                            int[] creatThreePositionColor132 = creatThreePositionColor();
                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor132, creatThreePositionFloat135);
                            drawBottomSide(canvas, creatThreePositionColor132, creatThreePositionFloat135, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor132, creatThreePositionFloat135);
                        } else if ((this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096) {
                            float[] creatThreePositionFloat136 = creatThreePositionFloat();
                            int[] creatThreePositionColor133 = creatThreePositionColor();
                            drawLeftBottomDownArcGradient(canvas, creatThreePositionColor133, creatThreePositionFloat136);
                            drawBottomSide(canvas, creatThreePositionColor133, creatThreePositionFloat136, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                        } else if ((this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096) {
                            float[] creatThreePositionFloat137 = creatThreePositionFloat();
                            int[] creatThreePositionColor134 = creatThreePositionColor();
                            drawBottomSide(canvas, creatThreePositionColor134, creatThreePositionFloat137, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                            drawRightBottomDownArcGradient(canvas, creatThreePositionColor134, creatThreePositionFloat137);
                        }
                    } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                        float[] creatThreePositionFloat138 = creatThreePositionFloat();
                        int[] creatThreePositionColor135 = creatThreePositionColor();
                        drawRightTopDownArcGradient(canvas, creatThreePositionColor135, creatThreePositionFloat138);
                        drawRightSide(canvas, creatThreePositionColor135, creatThreePositionFloat138, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                        drawRightBottomUpArcGradient(canvas, creatThreePositionColor135, creatThreePositionFloat138);
                    } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                        float[] creatThreePositionFloat139 = creatThreePositionFloat();
                        int[] creatThreePositionColor136 = creatThreePositionColor();
                        drawRightTopDownArcGradient(canvas, creatThreePositionColor136, creatThreePositionFloat139);
                        drawRightSide(canvas, creatThreePositionColor136, creatThreePositionFloat139, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                    } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                        float[] creatThreePositionFloat140 = creatThreePositionFloat();
                        int[] creatThreePositionColor137 = creatThreePositionColor();
                        drawRightBottomUpArcGradient(canvas, creatThreePositionColor137, creatThreePositionFloat140);
                        drawRightSide(canvas, creatThreePositionColor137, creatThreePositionFloat140, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                    }
                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256) {
                    float[] creatThreePositionFloat141 = creatThreePositionFloat();
                    int[] creatThreePositionColor138 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor138, creatThreePositionFloat141, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                    drawLeftTopUpArcGradient(canvas, creatThreePositionColor138, creatThreePositionFloat141);
                    drawRightTopUpArcGradient(canvas, creatThreePositionColor138, creatThreePositionFloat141);
                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256) {
                    float[] creatThreePositionFloat142 = creatThreePositionFloat();
                    int[] creatThreePositionColor139 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor139, creatThreePositionFloat142, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                    drawLeftTopUpArcGradient(canvas, creatThreePositionColor139, creatThreePositionFloat142);
                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256) {
                    float[] creatThreePositionFloat143 = creatThreePositionFloat();
                    int[] creatThreePositionColor140 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor140, creatThreePositionFloat143, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                    drawRightTopUpArcGradient(canvas, creatThreePositionColor140, creatThreePositionFloat143);
                }
            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16) {
                float[] creatThreePositionFloat144 = creatThreePositionFloat();
                int[] creatThreePositionColor141 = creatThreePositionColor();
                drawLeftTopDownArcGradient(canvas, creatThreePositionColor141, creatThreePositionFloat144);
                drawLeftSide(canvas, creatThreePositionColor141, creatThreePositionFloat144, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16) {
                float[] creatThreePositionFloat145 = creatThreePositionFloat();
                int[] creatThreePositionColor142 = creatThreePositionColor();
                drawLeftSide(canvas, creatThreePositionColor142, creatThreePositionFloat145, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                drawLeftBottomUpArcGradient(canvas, creatThreePositionColor142, creatThreePositionFloat145);
            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16) {
                float[] creatThreePositionFloat146 = creatThreePositionFloat();
                int[] creatThreePositionColor143 = creatThreePositionColor();
                drawLeftSide(canvas, creatThreePositionColor143, creatThreePositionFloat146, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                drawLeftBottomUpArcGradient(canvas, creatThreePositionColor143, creatThreePositionFloat146);
                drawLeftTopDownArcGradient(canvas, creatThreePositionColor143, creatThreePositionFloat146);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = 0;
        super.onMeasure(i, i2);
        float f = this.mShadowRadius;
        int i4 = (this.mShadowSide & 1) == 1 ? (int) f : 0;
        int i5 = (this.mShadowSide & 16) == 16 ? (int) f : 0;
        int i6 = (this.mShadowSide & 256) == 256 ? (int) f : 0;
        if ((this.mShadowSide & 4096) == 4096) {
            i3 = (int) f;
        }
        setPadding(i4, i5, i6, i3);
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mViewHeight = i2;
        this.mViewWidth = i;
    }

    public void setShadowColor(int i) {
        this.mShadowColor = i;
        requestLayout();
        postInvalidate();
    }

    public void setShadowRadius(float f) {
        this.mShadowRadius = f;
        requestLayout();
        postInvalidate();
    }
}
