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
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float f = this.mShadowRadius;
        int i3 = 0;
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

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        int[] iArr = {this.mShadowColor, 16777215};
        if (this.mShadowShape == 1) {
            float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
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
                float[] fArr2 = fArr;
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr2, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) != 4096) {
                float[] fArr3 = fArr;
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr3, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr3, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
            } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) != 4096) {
                float[] fArr4 = fArr;
                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr4, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr4, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                float[] fArr5 = fArr;
                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr5, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr5, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
            } else {
                float[] fArr6 = fArr;
                if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                    float[] fArr7 = fArr6;
                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr7, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr7, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                    drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                    drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                    drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) != 4096) {
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                    float[] fArr8 = fArr6;
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr8, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr8, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                    float[] fArr9 = fArr6;
                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr9, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr9, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                    float[] fArr10 = fArr6;
                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr10, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr10, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius));
                    drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius));
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                    drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr6, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight));
                    float[] fArr11 = fArr6;
                    drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr11, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                    drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr11, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                }
            }
        } else if (this.mShadowShape == 256) {
            float[] fArr12 = {CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
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
                                                            float[] fArr13 = creatThreePositionFloat;
                                                            drawLeftSide(canvas, creatThreePositionColor, fArr13, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor, creatThreePositionFloat);
                                                            int[] iArr2 = creatThreePositionColor;
                                                            drawTopSide(canvas, iArr2, fArr13, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor, creatThreePositionFloat);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor, creatThreePositionFloat);
                                                            drawRightSide(canvas, iArr2, fArr13, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor, creatThreePositionFloat);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat2 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor2 = creatThreePositionColor();
                                                            int[] iArr3 = creatThreePositionColor2;
                                                            float[] fArr14 = creatThreePositionFloat2;
                                                            drawLeftSide(canvas, iArr3, fArr14, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor2, creatThreePositionFloat2);
                                                            drawTopSide(canvas, iArr3, fArr14, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr3, fArr14, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat3 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor3 = creatThreePositionColor();
                                                            int[] iArr4 = creatThreePositionColor3;
                                                            float[] fArr15 = creatThreePositionFloat3;
                                                            drawLeftSide(canvas, iArr4, fArr15, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor3, creatThreePositionFloat3);
                                                            drawTopSide(canvas, iArr4, fArr15, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr4, fArr15, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            float[] creatTwoPositionFloat = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor, creatTwoPositionFloat);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor, creatTwoPositionFloat);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat4 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor4 = creatThreePositionColor();
                                                            int[] iArr5 = creatThreePositionColor4;
                                                            float[] fArr16 = creatThreePositionFloat4;
                                                            drawLeftSide(canvas, iArr5, fArr16, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, iArr5, fArr16, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr5, fArr16, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor4, creatThreePositionFloat4);
                                                            float[] creatTwoPositionFloat2 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor2 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor2, creatTwoPositionFloat2);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor2, creatTwoPositionFloat2);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat5 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor5 = creatThreePositionColor();
                                                            int[] iArr6 = creatThreePositionColor5;
                                                            float[] fArr17 = creatThreePositionFloat5;
                                                            drawLeftSide(canvas, iArr6, fArr17, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, iArr6, fArr17, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr6, fArr17, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor5, creatThreePositionFloat5);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat6 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor6 = creatThreePositionColor();
                                                            int[] iArr7 = creatThreePositionColor6;
                                                            float[] fArr18 = creatThreePositionFloat6;
                                                            drawLeftSide(canvas, iArr7, fArr18, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr7, fArr18, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr7, fArr18, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor6, creatThreePositionFloat6);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor6, creatThreePositionFloat6);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat7 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor7 = creatThreePositionColor();
                                                            int[] iArr8 = creatThreePositionColor7;
                                                            float[] fArr19 = creatThreePositionFloat7;
                                                            drawLeftSide(canvas, iArr8, fArr19, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, iArr8, fArr19, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr8, fArr19, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor7, creatThreePositionFloat7);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor7, creatThreePositionFloat7);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat8 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor8 = creatThreePositionColor();
                                                            int[] iArr9 = creatThreePositionColor8;
                                                            float[] fArr20 = creatThreePositionFloat8;
                                                            drawLeftSide(canvas, iArr9, fArr20, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, iArr9, fArr20, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr9, fArr20, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor8, creatThreePositionFloat8);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor8, creatThreePositionFloat8);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat9 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor9 = creatThreePositionColor();
                                                            int[] iArr10 = creatThreePositionColor9;
                                                            float[] fArr21 = creatThreePositionFloat9;
                                                            drawLeftSide(canvas, iArr10, fArr21, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr10, fArr21, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr10, fArr21, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor9, creatThreePositionFloat9);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor9, creatThreePositionFloat9);
                                                            float[] creatTwoPositionFloat3 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor3 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor3, creatTwoPositionFloat3);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor3, creatTwoPositionFloat3);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat10 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor10 = creatThreePositionColor();
                                                            int[] iArr11 = creatThreePositionColor10;
                                                            float[] fArr22 = creatThreePositionFloat10;
                                                            drawLeftSide(canvas, iArr11, fArr22, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr11, fArr22, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr11, fArr22, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor10, creatThreePositionFloat10);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor10, creatThreePositionFloat10);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat11 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor11 = creatThreePositionColor();
                                                            int[] iArr12 = creatThreePositionColor11;
                                                            float[] fArr23 = creatThreePositionFloat11;
                                                            drawLeftSide(canvas, iArr12, fArr23, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight);
                                                            drawTopSide(canvas, iArr12, fArr23, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr12, fArr23, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor11, creatThreePositionFloat11);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor11, creatThreePositionFloat11);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat12 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor12 = creatThreePositionColor();
                                                            int[] iArr13 = creatThreePositionColor12;
                                                            float[] fArr24 = creatThreePositionFloat12;
                                                            drawLeftSide(canvas, iArr13, fArr24, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor12, creatThreePositionFloat12);
                                                            drawTopSide(canvas, iArr13, fArr24, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor12, creatThreePositionFloat12);
                                                            drawRightSide(canvas, iArr13, fArr24, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor12, creatThreePositionFloat12);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat13 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor13 = creatThreePositionColor();
                                                            int[] iArr14 = creatThreePositionColor13;
                                                            float[] fArr25 = creatThreePositionFloat13;
                                                            drawLeftSide(canvas, iArr14, fArr25, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor13, creatThreePositionFloat13);
                                                            drawTopSide(canvas, iArr14, fArr25, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor13, creatThreePositionFloat13);
                                                            drawRightSide(canvas, iArr14, fArr25, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor13, creatThreePositionFloat13);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat14 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor14 = creatThreePositionColor();
                                                            int[] iArr15 = creatThreePositionColor14;
                                                            float[] fArr26 = creatThreePositionFloat14;
                                                            drawLeftSide(canvas, iArr15, fArr26, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor14, creatThreePositionFloat14);
                                                            drawTopSide(canvas, iArr15, fArr26, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr15, fArr26, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor14, creatThreePositionFloat14);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor14, creatThreePositionFloat14);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat15 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor15 = creatThreePositionColor();
                                                            int[] iArr16 = creatThreePositionColor15;
                                                            float[] fArr27 = creatThreePositionFloat15;
                                                            drawLeftSide(canvas, iArr16, fArr27, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr16, fArr27, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr16, fArr27, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor15, creatThreePositionFloat15);
                                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor15, creatThreePositionFloat15);
                                                            drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor15, creatThreePositionFloat15);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) != 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat16 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor16 = creatThreePositionColor();
                                                            int[] iArr17 = creatThreePositionColor16;
                                                            float[] fArr28 = creatThreePositionFloat16;
                                                            drawLeftSide(canvas, iArr17, fArr28, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawTopSide(canvas, iArr17, fArr28, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawBottomSide(canvas, iArr17, fArr28, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor16, creatThreePositionFloat16);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor16, creatThreePositionFloat16);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat17 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor17 = creatThreePositionColor();
                                                            int[] iArr18 = creatThreePositionColor17;
                                                            float[] fArr29 = creatThreePositionFloat17;
                                                            drawLeftSide(canvas, iArr18, fArr29, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor17, creatThreePositionFloat17);
                                                            drawTopSide(canvas, iArr18, fArr29, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr18, fArr29, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat18 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor18 = creatThreePositionColor();
                                                            int[] iArr19 = creatThreePositionColor18;
                                                            float[] fArr30 = creatThreePositionFloat18;
                                                            drawLeftSide(canvas, iArr19, fArr30, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr19, fArr30, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr19, fArr30, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor18, creatThreePositionFloat18);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat19 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor19 = creatThreePositionColor();
                                                            int[] iArr20 = creatThreePositionColor19;
                                                            float[] fArr31 = creatThreePositionFloat19;
                                                            drawLeftSide(canvas, iArr20, fArr31, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, iArr20, fArr31, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr20, fArr31, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor19, creatThreePositionFloat19);
                                                            float[] creatTwoPositionFloat4 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor4 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor4, creatTwoPositionFloat4);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor4, creatTwoPositionFloat4);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat20 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor20 = creatThreePositionColor();
                                                            int[] iArr21 = creatThreePositionColor20;
                                                            float[] fArr32 = creatThreePositionFloat20;
                                                            drawLeftSide(canvas, iArr21, fArr32, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, iArr21, fArr32, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr21, fArr32, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor20, creatThreePositionFloat20);
                                                            float[] creatTwoPositionFloat5 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor5 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor5, creatTwoPositionFloat5);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor5, creatTwoPositionFloat5);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat21 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor21 = creatThreePositionColor();
                                                            int[] iArr22 = creatThreePositionColor21;
                                                            float[] fArr33 = creatThreePositionFloat21;
                                                            drawLeftSide(canvas, iArr22, fArr33, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor21, creatThreePositionFloat21);
                                                            drawTopSide(canvas, iArr22, fArr33, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor21, creatThreePositionFloat21);
                                                            drawBottomSide(canvas, iArr22, fArr33, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat22 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor22 = creatThreePositionColor();
                                                            int[] iArr23 = creatThreePositionColor22;
                                                            float[] fArr34 = creatThreePositionFloat22;
                                                            drawLeftSide(canvas, iArr23, fArr34, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor22, creatThreePositionFloat22);
                                                            drawTopSide(canvas, iArr23, fArr34, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor22, creatThreePositionFloat22);
                                                            drawBottomSide(canvas, iArr23, fArr34, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat23 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor23 = creatThreePositionColor();
                                                            int[] iArr24 = creatThreePositionColor23;
                                                            float[] fArr35 = creatThreePositionFloat23;
                                                            drawLeftSide(canvas, iArr24, fArr35, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor23, creatThreePositionFloat23);
                                                            drawTopSide(canvas, iArr24, fArr35, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr24, fArr35, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor23, creatThreePositionFloat23);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat24 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor24 = creatThreePositionColor();
                                                            int[] iArr25 = creatThreePositionColor24;
                                                            float[] fArr36 = creatThreePositionFloat24;
                                                            drawLeftSide(canvas, iArr25, fArr36, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr25, fArr36, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr25, fArr36, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor24, creatThreePositionFloat24);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor24, creatThreePositionFloat24);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat25 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor25 = creatThreePositionColor();
                                                            int[] iArr26 = creatThreePositionColor25;
                                                            float[] fArr37 = creatThreePositionFloat25;
                                                            drawLeftSide(canvas, iArr26, fArr37, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr26, fArr37, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr26, fArr37, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor25, creatThreePositionFloat25);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor25, creatThreePositionFloat25);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat26 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor26 = creatThreePositionColor();
                                                            int[] iArr27 = creatThreePositionColor26;
                                                            float[] fArr38 = creatThreePositionFloat26;
                                                            drawLeftSide(canvas, iArr27, fArr38, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawTopSide(canvas, iArr27, fArr38, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr27, fArr38, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor26, creatThreePositionFloat26);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor26, creatThreePositionFloat26);
                                                            float[] creatTwoPositionFloat6 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor6 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor6, creatTwoPositionFloat6);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor6, creatTwoPositionFloat6);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat27 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor27 = creatThreePositionColor();
                                                            int[] iArr28 = creatThreePositionColor27;
                                                            float[] fArr39 = creatThreePositionFloat27;
                                                            drawLeftSide(canvas, iArr28, fArr39, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor27, creatThreePositionFloat27);
                                                            drawTopSide(canvas, iArr28, fArr39, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor27, creatThreePositionFloat27);
                                                            drawBottomSide(canvas, iArr28, fArr39, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor27, creatThreePositionFloat27);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat28 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor28 = creatThreePositionColor();
                                                            int[] iArr29 = creatThreePositionColor28;
                                                            float[] fArr40 = creatThreePositionFloat28;
                                                            drawLeftSide(canvas, iArr29, fArr40, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor28, creatThreePositionFloat28);
                                                            drawTopSide(canvas, iArr29, fArr40, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor28, creatThreePositionFloat28);
                                                            drawBottomSide(canvas, iArr29, fArr40, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor28, creatThreePositionFloat28);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat29 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor29 = creatThreePositionColor();
                                                            int[] iArr30 = creatThreePositionColor29;
                                                            float[] fArr41 = creatThreePositionFloat29;
                                                            drawLeftSide(canvas, iArr30, fArr41, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor29, creatThreePositionFloat29);
                                                            drawTopSide(canvas, iArr30, fArr41, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr30, fArr41, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor29, creatThreePositionFloat29);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor29, creatThreePositionFloat29);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat30 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor30 = creatThreePositionColor();
                                                            int[] iArr31 = creatThreePositionColor30;
                                                            float[] fArr42 = creatThreePositionFloat30;
                                                            drawLeftSide(canvas, iArr31, fArr42, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawTopSide(canvas, iArr31, fArr42, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr31, fArr42, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopUpArcGradient(canvas2, creatThreePositionColor30, creatThreePositionFloat30);
                                                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor30, creatThreePositionFloat30);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor30, creatThreePositionFloat30);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    } else if ((this.mShadowSide & 1) == 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) != 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat31 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor31 = creatThreePositionColor();
                                                            int[] iArr32 = creatThreePositionColor31;
                                                            float[] fArr43 = creatThreePositionFloat31;
                                                            drawLeftSide(canvas, iArr32, fArr43, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawBottomSide(canvas, iArr32, fArr43, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor31, creatThreePositionFloat31);
                                                            drawRightSide(canvas, iArr32, fArr43, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor31, creatThreePositionFloat31);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat32 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor32 = creatThreePositionColor();
                                                            int[] iArr33 = creatThreePositionColor32;
                                                            float[] fArr44 = creatThreePositionFloat32;
                                                            drawLeftSide(canvas, iArr33, fArr44, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor32, creatThreePositionFloat32);
                                                            drawBottomSide(canvas, iArr33, fArr44, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr33, fArr44, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat7 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor7 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor7, creatTwoPositionFloat7);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor7, creatTwoPositionFloat7);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat33 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor33 = creatThreePositionColor();
                                                            int[] iArr34 = creatThreePositionColor33;
                                                            float[] fArr45 = creatThreePositionFloat33;
                                                            drawLeftSide(canvas, iArr34, fArr45, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor33, creatThreePositionFloat33);
                                                            drawBottomSide(canvas, iArr34, fArr45, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr34, fArr45, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat34 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor34 = creatThreePositionColor();
                                                            int[] iArr35 = creatThreePositionColor34;
                                                            float[] fArr46 = creatThreePositionFloat34;
                                                            drawLeftSide(canvas, iArr35, fArr46, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr35, fArr46, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr35, fArr46, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor34, creatThreePositionFloat34);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat35 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor35 = creatThreePositionColor();
                                                            int[] iArr36 = creatThreePositionColor35;
                                                            float[] fArr47 = creatThreePositionFloat35;
                                                            drawLeftSide(canvas, iArr36, fArr47, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr36, fArr47, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr36, fArr47, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor35, creatThreePositionFloat35);
                                                            float[] creatTwoPositionFloat8 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor8 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor8, creatTwoPositionFloat8);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor8, creatTwoPositionFloat8);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat36 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor36 = creatThreePositionColor();
                                                            int[] iArr37 = creatThreePositionColor36;
                                                            float[] fArr48 = creatThreePositionFloat36;
                                                            drawLeftSide(canvas, iArr37, fArr48, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor36, creatThreePositionFloat36);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor36, creatThreePositionFloat36);
                                                            drawBottomSide(canvas, iArr37, fArr48, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr37, fArr48, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat37 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor37 = creatThreePositionColor();
                                                            int[] iArr38 = creatThreePositionColor37;
                                                            float[] fArr49 = creatThreePositionFloat37;
                                                            drawLeftSide(canvas, iArr38, fArr49, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor37, creatThreePositionFloat37);
                                                            drawBottomSide(canvas, iArr38, fArr49, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr38, fArr49, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor37, creatThreePositionFloat37);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat38 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor38 = creatThreePositionColor();
                                                            int[] iArr39 = creatThreePositionColor38;
                                                            float[] fArr50 = creatThreePositionFloat38;
                                                            drawLeftSide(canvas, iArr39, fArr50, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor38, creatThreePositionFloat38);
                                                            drawBottomSide(canvas, iArr39, fArr50, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr39, fArr50, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor38, creatThreePositionFloat38);
                                                            float[] creatTwoPositionFloat9 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor9 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor9, creatTwoPositionFloat9);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor9, creatTwoPositionFloat9);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat39 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor39 = creatThreePositionColor();
                                                            int[] iArr40 = creatThreePositionColor39;
                                                            float[] fArr51 = creatThreePositionFloat39;
                                                            drawLeftSide(canvas, iArr40, fArr51, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mRoundCornerRadius + this.mShadowRadius));
                                                            drawBottomSide(canvas, iArr40, fArr51, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr40, fArr51, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor39, creatThreePositionFloat39);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor39, creatThreePositionFloat39);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat40 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor40 = creatThreePositionColor();
                                                            int[] iArr41 = creatThreePositionColor40;
                                                            float[] fArr52 = creatThreePositionFloat40;
                                                            drawLeftSide(canvas, iArr41, fArr52, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mRoundCornerRadius + this.mShadowRadius));
                                                            drawBottomSide(canvas, iArr41, fArr52, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr41, fArr52, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor40, creatThreePositionFloat40);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor40, creatThreePositionFloat40);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat41 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor41 = creatThreePositionColor();
                                                            int[] iArr42 = creatThreePositionColor41;
                                                            float[] fArr53 = creatThreePositionFloat41;
                                                            drawLeftSide(canvas, iArr42, fArr53, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr42, fArr53, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr42, fArr53, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor41, creatThreePositionFloat41);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor41, creatThreePositionFloat41);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat42 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor42 = creatThreePositionColor();
                                                            int[] iArr43 = creatThreePositionColor42;
                                                            float[] fArr54 = creatThreePositionFloat42;
                                                            drawLeftSide(canvas, iArr43, fArr54, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor42, creatThreePositionFloat42);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor42, creatThreePositionFloat42);
                                                            drawBottomSide(canvas, iArr43, fArr54, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr43, fArr54, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor42, creatThreePositionFloat42);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat43 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor43 = creatThreePositionColor();
                                                            int[] iArr44 = creatThreePositionColor43;
                                                            float[] fArr55 = creatThreePositionFloat43;
                                                            drawLeftSide(canvas, iArr44, fArr55, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor43, creatThreePositionFloat43);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor43, creatThreePositionFloat43);
                                                            drawBottomSide(canvas, iArr44, fArr55, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr44, fArr55, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor43, creatThreePositionFloat43);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat44 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor44 = creatThreePositionColor();
                                                            int[] iArr45 = creatThreePositionColor44;
                                                            float[] fArr56 = creatThreePositionFloat44;
                                                            drawLeftSide(canvas, iArr45, fArr56, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawLeftTopDownArcGradient(canvas2, creatThreePositionColor44, creatThreePositionFloat44);
                                                            drawBottomSide(canvas, iArr45, fArr56, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor44, creatThreePositionFloat44);
                                                            drawRightSide(canvas, iArr45, fArr56, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor44, creatThreePositionFloat44);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat45 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor45 = creatThreePositionColor();
                                                            int[] iArr46 = creatThreePositionColor45;
                                                            float[] fArr57 = creatThreePositionFloat45;
                                                            drawLeftSide(canvas, iArr46, fArr57, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius));
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor45, creatThreePositionFloat45);
                                                            drawBottomSide(canvas, iArr46, fArr57, this.mRoundCornerRadius + this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopDownArcGradient(canvas2, creatThreePositionColor45, creatThreePositionFloat45);
                                                            drawRightSide(canvas, iArr46, fArr57, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor45, creatThreePositionFloat45);
                                                        }
                                                    } else if ((this.mShadowSide & 1) != 1 && (this.mShadowSide & 256) == 256 && (this.mShadowSide & 16) == 16 && (this.mShadowSide & 4096) == 4096) {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat46 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor46 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor46, creatThreePositionFloat46);
                                                            int[] iArr47 = creatThreePositionColor46;
                                                            float[] fArr58 = creatThreePositionFloat46;
                                                            drawTopSide(canvas, iArr47, fArr58, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawRightSide(canvas, iArr47, fArr58, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor46, creatThreePositionFloat46);
                                                            drawBottomSide(canvas, iArr47, fArr58, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat47 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor47 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor47, creatThreePositionFloat47);
                                                            int[] iArr48 = creatThreePositionColor47;
                                                            float[] fArr59 = creatThreePositionFloat47;
                                                            drawTopSide(canvas, iArr48, fArr59, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr48, fArr59, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr48, fArr59, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            float[] creatTwoPositionFloat10 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor10 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor10, creatTwoPositionFloat10);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor10, creatTwoPositionFloat10);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat48 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor48 = creatThreePositionColor();
                                                            int[] iArr49 = creatThreePositionColor48;
                                                            float[] fArr60 = creatThreePositionFloat48;
                                                            drawTopSide(canvas, iArr49, fArr60, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr49, fArr60, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr49, fArr60, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor48, creatThreePositionFloat48);
                                                            float[] creatTwoPositionFloat11 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor11 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor11, creatTwoPositionFloat11);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor11, creatTwoPositionFloat11);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat49 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor49 = creatThreePositionColor();
                                                            int[] iArr50 = creatThreePositionColor49;
                                                            float[] fArr61 = creatThreePositionFloat49;
                                                            drawTopSide(canvas, iArr50, fArr61, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr50, fArr61, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr50, fArr61, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor49, creatThreePositionFloat49);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat50 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor50 = creatThreePositionColor();
                                                            int[] iArr51 = creatThreePositionColor50;
                                                            float[] fArr62 = creatThreePositionFloat50;
                                                            drawTopSide(canvas, iArr51, fArr62, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr51, fArr62, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr51, fArr62, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor50, creatThreePositionFloat50);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat51 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor51 = creatThreePositionColor();
                                                            int[] iArr52 = creatThreePositionColor51;
                                                            float[] fArr63 = creatThreePositionFloat51;
                                                            drawTopSide(canvas, iArr52, fArr63, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr52, fArr63, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr52, fArr63, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor51, creatThreePositionFloat51);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor51, creatThreePositionFloat51);
                                                            float[] creatTwoPositionFloat12 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor12 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor12, creatTwoPositionFloat12);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor12, creatTwoPositionFloat12);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat52 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor52 = creatThreePositionColor();
                                                            int[] iArr53 = creatThreePositionColor52;
                                                            float[] fArr64 = creatThreePositionFloat52;
                                                            drawTopSide(canvas, iArr53, fArr64, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr53, fArr64, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr53, fArr64, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor52, creatThreePositionFloat52);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor52, creatThreePositionFloat52);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat53 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor53 = creatThreePositionColor();
                                                            int[] iArr54 = creatThreePositionColor53;
                                                            float[] fArr65 = creatThreePositionFloat53;
                                                            drawTopSide(canvas, iArr54, fArr65, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr54, fArr65, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr54, fArr65, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor53, creatThreePositionFloat53);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor53, creatThreePositionFloat53);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat54 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor54 = creatThreePositionColor();
                                                            int[] iArr55 = creatThreePositionColor54;
                                                            float[] fArr66 = creatThreePositionFloat54;
                                                            drawTopSide(canvas, iArr55, fArr66, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr55, fArr66, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr55, fArr66, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor54, creatThreePositionFloat54);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor54, creatThreePositionFloat54);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat55 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor55 = creatThreePositionColor();
                                                            int[] iArr56 = creatThreePositionColor55;
                                                            float[] fArr67 = creatThreePositionFloat55;
                                                            drawTopSide(canvas, iArr56, fArr67, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr56, fArr67, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr56, fArr67, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor55, creatThreePositionFloat55);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor55, creatThreePositionFloat55);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat56 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor56 = creatThreePositionColor();
                                                            int[] iArr57 = creatThreePositionColor56;
                                                            float[] fArr68 = creatThreePositionFloat56;
                                                            drawTopSide(canvas, iArr57, fArr68, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightSide(canvas, iArr57, fArr68, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr57, fArr68, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor56, creatThreePositionFloat56);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor56, creatThreePositionFloat56);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat57 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor57 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor57, creatThreePositionFloat57);
                                                            int[] iArr58 = creatThreePositionColor57;
                                                            float[] fArr69 = creatThreePositionFloat57;
                                                            drawTopSide(canvas, iArr58, fArr69, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor57, creatThreePositionFloat57);
                                                            drawRightSide(canvas, iArr58, fArr69, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor57, creatThreePositionFloat57);
                                                            drawBottomSide(canvas, iArr58, fArr69, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat58 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor58 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor58, creatThreePositionFloat58);
                                                            int[] iArr59 = creatThreePositionColor58;
                                                            float[] fArr70 = creatThreePositionFloat58;
                                                            drawTopSide(canvas, iArr59, fArr70, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor58, creatThreePositionFloat58);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor58, creatThreePositionFloat58);
                                                            drawRightSide(canvas, iArr59, fArr70, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr59, fArr70, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat59 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor59 = creatThreePositionColor();
                                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor59, creatThreePositionFloat59);
                                                            int[] iArr60 = creatThreePositionColor59;
                                                            float[] fArr71 = creatThreePositionFloat59;
                                                            drawTopSide(canvas, iArr60, fArr71, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor59, creatThreePositionFloat59);
                                                            drawRightSide(canvas, iArr60, fArr71, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor59, creatThreePositionFloat59);
                                                            drawBottomSide(canvas, iArr60, fArr71, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat60 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor60 = creatThreePositionColor();
                                                            int[] iArr61 = creatThreePositionColor60;
                                                            float[] fArr72 = creatThreePositionFloat60;
                                                            drawTopSide(canvas, iArr61, fArr72, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawRightSide(canvas, iArr61, fArr72, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor60, creatThreePositionFloat60);
                                                            drawBottomSide(canvas, iArr61, fArr72, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                        }
                                                    } else if ((this.mShadowSide & 1) != 1 || (this.mShadowSide & 256) != 256 || (this.mShadowSide & 16) != 16 || (this.mShadowSide & 4096) != 4096) {
                                                    } else {
                                                        if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat61 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor61 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor61, creatThreePositionFloat61);
                                                            int[] iArr62 = creatThreePositionColor61;
                                                            float[] fArr73 = creatThreePositionFloat61;
                                                            drawTopSide(canvas, iArr62, fArr73, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr62, fArr73, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor61, creatThreePositionFloat61);
                                                            drawBottomSide(canvas, iArr62, fArr73, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor61, creatThreePositionFloat61);
                                                            drawRightSide(canvas, iArr62, fArr73, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor61, creatThreePositionFloat61);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat62 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor62 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor62, creatThreePositionFloat62);
                                                            int[] iArr63 = creatThreePositionColor62;
                                                            float[] fArr74 = creatThreePositionFloat62;
                                                            drawTopSide(canvas, iArr63, fArr74, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr63, fArr74, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr63, fArr74, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr63, fArr74, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat13 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor13 = creatTwoPositionColor();
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor13, creatTwoPositionFloat13);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat63 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor63 = creatThreePositionColor();
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor63, creatThreePositionFloat63);
                                                            int[] iArr64 = creatThreePositionColor63;
                                                            float[] fArr75 = creatThreePositionFloat63;
                                                            drawTopSide(canvas, iArr64, fArr75, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr64, fArr75, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr64, fArr75, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr64, fArr75, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat14 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor14 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor14, creatTwoPositionFloat14);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat64 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor64 = creatThreePositionColor();
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor64, creatThreePositionFloat64);
                                                            int[] iArr65 = creatThreePositionColor64;
                                                            float[] fArr76 = creatThreePositionFloat64;
                                                            drawTopSide(canvas, iArr65, fArr76, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr65, fArr76, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr65, fArr76, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr65, fArr76, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat15 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor15 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor15, creatTwoPositionFloat15);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat65 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor65 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor65, creatThreePositionFloat65);
                                                            int[] iArr66 = creatThreePositionColor65;
                                                            float[] fArr77 = creatThreePositionFloat65;
                                                            drawTopSide(canvas, iArr66, fArr77, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr66, fArr77, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr66, fArr77, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr66, fArr77, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat16 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor16 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor16, creatTwoPositionFloat16);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat66 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor66 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor66, creatThreePositionFloat66);
                                                            int[] iArr67 = creatThreePositionColor66;
                                                            float[] fArr78 = creatThreePositionFloat66;
                                                            drawTopSide(canvas, iArr67, fArr78, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr67, fArr78, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor66, creatThreePositionFloat66);
                                                            drawBottomSide(canvas, iArr67, fArr78, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr67, fArr78, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat17 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor17 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor17, creatTwoPositionFloat17);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor17, creatTwoPositionFloat17);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat67 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor67 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor67, creatThreePositionFloat67);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor67, creatThreePositionFloat67);
                                                            int[] iArr68 = creatThreePositionColor67;
                                                            float[] fArr79 = creatThreePositionFloat67;
                                                            drawTopSide(canvas, iArr68, fArr79, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr68, fArr79, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr68, fArr79, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr68, fArr79, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat18 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor18 = creatTwoPositionColor();
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor18, creatTwoPositionFloat18);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor18, creatTwoPositionFloat18);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat68 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor68 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor68, creatThreePositionFloat68);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor68, creatThreePositionFloat68);
                                                            int[] iArr69 = creatThreePositionColor68;
                                                            float[] fArr80 = creatThreePositionFloat68;
                                                            drawTopSide(canvas, iArr69, fArr80, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr69, fArr80, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr69, fArr80, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr69, fArr80, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat19 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor19 = creatTwoPositionColor();
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor19, creatTwoPositionFloat19);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor19, creatTwoPositionFloat19);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat69 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor69 = creatThreePositionColor();
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor69, creatThreePositionFloat69);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor69, creatThreePositionFloat69);
                                                            int[] iArr70 = creatThreePositionColor69;
                                                            float[] fArr81 = creatThreePositionFloat69;
                                                            drawTopSide(canvas, iArr70, fArr81, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr70, fArr81, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr70, fArr81, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr70, fArr81, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat20 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor20 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor20, creatTwoPositionFloat20);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor20, creatTwoPositionFloat20);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat70 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor70 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor70, creatThreePositionFloat70);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor70, creatThreePositionFloat70);
                                                            int[] iArr71 = creatThreePositionColor70;
                                                            float[] fArr82 = creatThreePositionFloat70;
                                                            drawTopSide(canvas, iArr71, fArr82, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr71, fArr82, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr71, fArr82, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr71, fArr82, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            float[] creatTwoPositionFloat21 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor21 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor21, creatTwoPositionFloat21);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor21, creatTwoPositionFloat21);
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat71 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor71 = creatThreePositionColor();
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor71, creatThreePositionFloat71);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor71, creatThreePositionFloat71);
                                                            int[] iArr72 = creatThreePositionColor71;
                                                            float[] fArr83 = creatThreePositionFloat71;
                                                            drawTopSide(canvas, iArr72, fArr83, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr72, fArr83, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr72, fArr83, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr72, fArr83, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            float[] creatTwoPositionFloat22 = creatTwoPositionFloat();
                                                            int[] creatTwoPositionColor22 = creatTwoPositionColor();
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor22, creatTwoPositionFloat22);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor22, creatTwoPositionFloat22);
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) != 256) {
                                                            float[] creatThreePositionFloat72 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor72 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor72, creatThreePositionFloat72);
                                                            int[] iArr73 = creatThreePositionColor72;
                                                            float[] fArr84 = creatThreePositionFloat72;
                                                            drawTopSide(canvas, iArr73, fArr84, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr73, fArr84, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor72, creatThreePositionFloat72);
                                                            drawBottomSide(canvas, iArr73, fArr84, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor72, creatThreePositionFloat72);
                                                            drawRightSide(canvas, iArr73, fArr84, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                            drawRightTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat73 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor73 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor73, creatThreePositionFloat73);
                                                            int[] iArr74 = creatThreePositionColor73;
                                                            float[] fArr85 = creatThreePositionFloat73;
                                                            drawTopSide(canvas, iArr74, fArr85, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr74, fArr85, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor73, creatThreePositionFloat73);
                                                            drawBottomSide(canvas, iArr74, fArr85, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr74, fArr85, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor73, creatThreePositionFloat73);
                                                            drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat74 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor74 = creatThreePositionColor();
                                                            drawLeftTopArcGradient(canvas2, creatThreePositionColor74, creatThreePositionFloat74);
                                                            int[] iArr75 = creatThreePositionColor74;
                                                            float[] fArr86 = creatThreePositionFloat74;
                                                            drawTopSide(canvas, iArr75, fArr86, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr75, fArr86, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius);
                                                            drawBottomSide(canvas, iArr75, fArr86, this.mShadowRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr75, fArr86, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor74, creatThreePositionFloat74);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor74, creatThreePositionFloat74);
                                                            drawLeftBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 256) == 256) {
                                                            float[] creatThreePositionFloat75 = creatThreePositionFloat();
                                                            int[] creatThreePositionColor75 = creatThreePositionColor();
                                                            drawLeftBottomArcGradient(canvas2, creatThreePositionColor75, creatThreePositionFloat75);
                                                            int[] iArr76 = creatThreePositionColor75;
                                                            float[] fArr87 = creatThreePositionFloat75;
                                                            drawTopSide(canvas, iArr76, fArr87, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                            drawLeftSide(canvas, iArr76, fArr87, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawBottomSide(canvas, iArr76, fArr87, this.mShadowRadius + this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                            drawRightSide(canvas, iArr76, fArr87, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius);
                                                            drawRightTopArcGradient(canvas2, creatThreePositionColor75, creatThreePositionFloat75);
                                                            drawRightBottomArcGradient(canvas2, creatThreePositionColor75, creatThreePositionFloat75);
                                                            drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                        }
                                                    }
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat76 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor76 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas2, creatThreePositionColor76, creatThreePositionFloat76);
                                                    int[] iArr77 = creatThreePositionColor76;
                                                    float[] fArr88 = creatThreePositionFloat76;
                                                    drawRightSide(canvas, iArr77, fArr88, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas2, creatThreePositionColor76, creatThreePositionFloat76);
                                                    drawBottomSide(canvas, iArr77, fArr88, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor76, creatThreePositionFloat76);
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat77 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor77 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas2, creatThreePositionColor77, creatThreePositionFloat77);
                                                    int[] iArr78 = creatThreePositionColor77;
                                                    float[] fArr89 = creatThreePositionFloat77;
                                                    drawRightSide(canvas, iArr78, fArr89, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, iArr78, fArr89, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat78 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor78 = creatThreePositionColor();
                                                    int[] iArr79 = creatThreePositionColor78;
                                                    float[] fArr90 = creatThreePositionFloat78;
                                                    drawRightSide(canvas, iArr79, fArr90, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas2, creatThreePositionColor78, creatThreePositionFloat78);
                                                    drawBottomSide(canvas, iArr79, fArr90, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat79 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor79 = creatThreePositionColor();
                                                    int[] iArr80 = creatThreePositionColor79;
                                                    float[] fArr91 = creatThreePositionFloat79;
                                                    drawRightSide(canvas, iArr80, fArr91, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, iArr80, fArr91, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor79, creatThreePositionFloat79);
                                                    drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                    float[] creatThreePositionFloat80 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor80 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas2, creatThreePositionColor80, creatThreePositionFloat80);
                                                    int[] iArr81 = creatThreePositionColor80;
                                                    float[] fArr92 = creatThreePositionFloat80;
                                                    drawRightSide(canvas, iArr81, fArr92, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas2, creatThreePositionColor80, creatThreePositionFloat80);
                                                    drawBottomSide(canvas, iArr81, fArr92, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat81 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor81 = creatThreePositionColor();
                                                    drawRightTopDownArcGradient(canvas2, creatThreePositionColor81, creatThreePositionFloat81);
                                                    int[] iArr82 = creatThreePositionColor81;
                                                    float[] fArr93 = creatThreePositionFloat81;
                                                    drawRightSide(canvas, iArr82, fArr93, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mShadowRadius);
                                                    drawBottomSide(canvas, iArr82, fArr93, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mShadowRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor81, creatThreePositionFloat81);
                                                    drawRightBottomRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                                                } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                    float[] creatThreePositionFloat82 = creatThreePositionFloat();
                                                    int[] creatThreePositionColor82 = creatThreePositionColor();
                                                    int[] iArr83 = creatThreePositionColor82;
                                                    float[] fArr94 = creatThreePositionFloat82;
                                                    drawRightSide(canvas, iArr83, fArr94, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius);
                                                    drawRightBottomArcGradient(canvas2, creatThreePositionColor82, creatThreePositionFloat82);
                                                    drawBottomSide(canvas, iArr83, fArr94, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                    drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor82, creatThreePositionFloat82);
                                                }
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat83 = creatThreePositionFloat();
                                                int[] creatThreePositionColor83 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor83, creatThreePositionFloat83);
                                                int[] iArr84 = creatThreePositionColor83;
                                                float[] fArr95 = creatThreePositionFloat83;
                                                drawTopSide(canvas, iArr84, fArr95, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor83, creatThreePositionFloat83);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor83, creatThreePositionFloat83);
                                                drawBottomSide(canvas, iArr84, fArr95, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor83, creatThreePositionFloat83);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat84 = creatThreePositionFloat();
                                                int[] creatThreePositionColor84 = creatThreePositionColor();
                                                int[] iArr85 = creatThreePositionColor84;
                                                float[] fArr96 = creatThreePositionFloat84;
                                                drawTopSide(canvas, iArr85, fArr96, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor84, creatThreePositionFloat84);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor84, creatThreePositionFloat84);
                                                drawBottomSide(canvas, iArr85, fArr96, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor84, creatThreePositionFloat84);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat85 = creatThreePositionFloat();
                                                int[] creatThreePositionColor85 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor85, creatThreePositionFloat85);
                                                int[] iArr86 = creatThreePositionColor85;
                                                float[] fArr97 = creatThreePositionFloat85;
                                                drawTopSide(canvas, iArr86, fArr97, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor85, creatThreePositionFloat85);
                                                drawBottomSide(canvas, iArr86, fArr97, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor85, creatThreePositionFloat85);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat86 = creatThreePositionFloat();
                                                int[] creatThreePositionColor86 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor86, creatThreePositionFloat86);
                                                int[] iArr87 = creatThreePositionColor86;
                                                float[] fArr98 = creatThreePositionFloat86;
                                                drawTopSide(canvas, iArr87, fArr98, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor86, creatThreePositionFloat86);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor86, creatThreePositionFloat86);
                                                drawBottomSide(canvas, iArr87, fArr98, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat87 = creatThreePositionFloat();
                                                int[] creatThreePositionColor87 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor87, creatThreePositionFloat87);
                                                int[] iArr88 = creatThreePositionColor87;
                                                float[] fArr99 = creatThreePositionFloat87;
                                                drawTopSide(canvas, iArr88, fArr99, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor87, creatThreePositionFloat87);
                                                drawBottomSide(canvas, iArr88, fArr99, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor87, creatThreePositionFloat87);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat88 = creatThreePositionFloat();
                                                int[] creatThreePositionColor88 = creatThreePositionColor();
                                                int[] iArr89 = creatThreePositionColor88;
                                                float[] fArr100 = creatThreePositionFloat88;
                                                drawTopSide(canvas, iArr89, fArr100, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor88, creatThreePositionFloat88);
                                                drawBottomSide(canvas, iArr89, fArr100, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor88, creatThreePositionFloat88);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat89 = creatThreePositionFloat();
                                                int[] creatThreePositionColor89 = creatThreePositionColor();
                                                int[] iArr90 = creatThreePositionColor89;
                                                float[] fArr101 = creatThreePositionFloat89;
                                                drawTopSide(canvas, iArr90, fArr101, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor89, creatThreePositionFloat89);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor89, creatThreePositionFloat89);
                                                drawBottomSide(canvas, iArr90, fArr101, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat90 = creatThreePositionFloat();
                                                int[] creatThreePositionColor90 = creatThreePositionColor();
                                                int[] iArr91 = creatThreePositionColor90;
                                                float[] fArr102 = creatThreePositionFloat90;
                                                drawTopSide(canvas, iArr91, fArr102, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor90, creatThreePositionFloat90);
                                                drawBottomSide(canvas, iArr91, fArr102, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor90, creatThreePositionFloat90);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat91 = creatThreePositionFloat();
                                                int[] creatThreePositionColor91 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor91, creatThreePositionFloat91);
                                                int[] iArr92 = creatThreePositionColor91;
                                                float[] fArr103 = creatThreePositionFloat91;
                                                drawTopSide(canvas, iArr92, fArr103, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor91, creatThreePositionFloat91);
                                                drawBottomSide(canvas, iArr92, fArr103, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat92 = creatThreePositionFloat();
                                                int[] creatThreePositionColor92 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor92, creatThreePositionFloat92);
                                                int[] iArr93 = creatThreePositionColor92;
                                                float[] fArr104 = creatThreePositionFloat92;
                                                drawTopSide(canvas, iArr93, fArr104, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, iArr93, fArr104, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor92, creatThreePositionFloat92);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat93 = creatThreePositionFloat();
                                                int[] creatThreePositionColor93 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor93, creatThreePositionFloat93);
                                                int[] iArr94 = creatThreePositionColor93;
                                                float[] fArr105 = creatThreePositionFloat93;
                                                drawTopSide(canvas, iArr94, fArr105, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor93, creatThreePositionFloat93);
                                                drawBottomSide(canvas, iArr94, fArr105, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) == 16) {
                                                float[] creatThreePositionFloat94 = creatThreePositionFloat();
                                                int[] creatThreePositionColor94 = creatThreePositionColor();
                                                int[] iArr95 = creatThreePositionColor94;
                                                float[] fArr106 = creatThreePositionFloat94;
                                                drawTopSide(canvas, iArr95, fArr106, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor94, creatThreePositionFloat94);
                                                drawBottomSide(canvas, iArr95, fArr106, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat95 = creatThreePositionFloat();
                                                int[] creatThreePositionColor95 = creatThreePositionColor();
                                                int[] iArr96 = creatThreePositionColor95;
                                                float[] fArr107 = creatThreePositionFloat95;
                                                drawTopSide(canvas, iArr96, fArr107, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, iArr96, fArr107, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                                                drawRightBottomDownArcGradient(canvas2, creatThreePositionColor95, creatThreePositionFloat95);
                                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat96 = creatThreePositionFloat();
                                                int[] creatThreePositionColor96 = creatThreePositionColor();
                                                drawLeftTopUpArcGradient(canvas2, creatThreePositionColor96, creatThreePositionFloat96);
                                                int[] iArr97 = creatThreePositionColor96;
                                                float[] fArr108 = creatThreePositionFloat96;
                                                drawTopSide(canvas, iArr97, fArr108, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawBottomSide(canvas, iArr97, fArr108, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096 && (this.mCornerPosition & 16) != 16) {
                                                float[] creatThreePositionFloat97 = creatThreePositionFloat();
                                                int[] creatThreePositionColor97 = creatThreePositionColor();
                                                int[] iArr98 = creatThreePositionColor97;
                                                float[] fArr109 = creatThreePositionFloat97;
                                                drawTopSide(canvas, iArr98, fArr109, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor97, creatThreePositionFloat97);
                                                drawBottomSide(canvas, iArr98, fArr109, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mRoundCornerRadius) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            }
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat98 = creatThreePositionFloat();
                                            int[] creatThreePositionColor98 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor98, creatThreePositionFloat98);
                                            float[] fArr110 = creatThreePositionFloat98;
                                            drawTopSide(canvas, creatThreePositionColor98, fArr110, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightTopArcGradient(canvas2, creatThreePositionColor98, creatThreePositionFloat98);
                                            drawRightSide(canvas, creatThreePositionColor98, fArr110, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor98, creatThreePositionFloat98);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat99 = creatThreePositionFloat();
                                            int[] creatThreePositionColor99 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor99, creatThreePositionFloat99);
                                            float[] fArr111 = creatThreePositionFloat99;
                                            drawTopSide(canvas, creatThreePositionColor99, fArr111, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightTopArcGradient(canvas2, creatThreePositionColor99, creatThreePositionFloat99);
                                            drawRightSide(canvas, creatThreePositionColor99, fArr111, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat100 = creatThreePositionFloat();
                                            int[] creatThreePositionColor100 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor100, creatThreePositionFloat100);
                                            float[] fArr112 = creatThreePositionFloat100;
                                            drawTopSide(canvas, creatThreePositionColor100, fArr112, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor100, fArr112, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor100, creatThreePositionFloat100);
                                            float[] creatTwoPositionFloat23 = creatTwoPositionFloat();
                                            int[] creatTwoPositionColor23 = creatTwoPositionColor();
                                            drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor23, creatTwoPositionFloat23, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat101 = creatThreePositionFloat();
                                            int[] creatThreePositionColor101 = creatThreePositionColor();
                                            float[] fArr113 = creatThreePositionFloat101;
                                            drawTopSide(canvas, creatThreePositionColor101, fArr113, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor101, fArr113, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor101, creatThreePositionFloat101);
                                            drawRightTopArcGradient(canvas2, creatThreePositionColor101, creatThreePositionFloat101);
                                        } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat102 = creatThreePositionFloat();
                                            int[] creatThreePositionColor102 = creatThreePositionColor();
                                            drawLeftTopUpArcGradient(canvas2, creatThreePositionColor102, creatThreePositionFloat102);
                                            float[] fArr114 = creatThreePositionFloat102;
                                            drawTopSide(canvas, creatThreePositionColor102, fArr114, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor102, fArr114, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            float[] creatTwoPositionFloat24 = creatTwoPositionFloat();
                                            int[] creatTwoPositionColor24 = creatTwoPositionColor();
                                            drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, creatTwoPositionColor24, creatTwoPositionFloat24, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                            float[] creatThreePositionFloat103 = creatThreePositionFloat();
                                            int[] creatThreePositionColor103 = creatThreePositionColor();
                                            float[] fArr115 = creatThreePositionFloat103;
                                            drawTopSide(canvas, creatThreePositionColor103, fArr115, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor103, fArr115, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                                            drawRightTopArcGradient(canvas2, creatThreePositionColor103, creatThreePositionFloat103);
                                        } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                            float[] creatThreePositionFloat104 = creatThreePositionFloat();
                                            int[] creatThreePositionColor104 = creatThreePositionColor();
                                            float[] fArr116 = creatThreePositionFloat104;
                                            drawTopSide(canvas, creatThreePositionColor104, fArr116, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                            drawRightSide(canvas, creatThreePositionColor104, fArr116, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                            drawRightBottomUpArcGradient(canvas2, creatThreePositionColor104, creatThreePositionFloat104);
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
                                        Canvas canvas3 = canvas;
                                        int[] iArr99 = creatThreePositionColor105;
                                        float[] fArr117 = creatThreePositionFloat105;
                                        drawArcRadialGradient(canvas3, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr99, fArr117, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        Canvas canvas4 = canvas;
                                        drawArcRadialGradient(canvas4, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr99, fArr117, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
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
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                        float[] creatThreePositionFloat107 = creatThreePositionFloat();
                                        Canvas canvas5 = canvas;
                                        drawArcRadialGradient(canvas5, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat107, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096) {
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        float[] creatThreePositionFloat108 = creatThreePositionFloat();
                                        Canvas canvas6 = canvas;
                                        drawArcRadialGradient(canvas6, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat108, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
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
                                        Canvas canvas7 = canvas;
                                        drawArcRadialGradient(canvas7, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor(), creatThreePositionFloat110, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096) {
                                        float[] creatThreePositionFloat111 = creatThreePositionFloat();
                                        int[] creatThreePositionColor108 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatThreePositionColor108, creatThreePositionFloat111, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), (float) this.mViewWidth, (float) this.mViewHeight));
                                        Canvas canvas8 = canvas;
                                        int[] iArr100 = creatThreePositionColor108;
                                        float[] fArr118 = creatThreePositionFloat111;
                                        drawArcRadialGradient(canvas8, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr100, fArr118, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096) {
                                        float[] creatThreePositionFloat112 = creatThreePositionFloat();
                                        int[] creatThreePositionColor109 = creatThreePositionColor();
                                        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius)));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, creatThreePositionColor109, creatThreePositionFloat112, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight));
                                        Canvas canvas9 = canvas;
                                        int[] iArr101 = creatThreePositionColor109;
                                        float[] fArr119 = creatThreePositionFloat112;
                                        drawArcRadialGradient(canvas9, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr101, fArr119, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                        Canvas canvas10 = canvas;
                                        drawArcRadialGradient(canvas10, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr101, fArr119, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                    } else {
                                        drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius));
                                        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    }
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat113 = creatThreePositionFloat();
                                    int[] creatThreePositionColor110 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    int[] iArr102 = creatThreePositionColor110;
                                    float[] fArr120 = creatThreePositionFloat113;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr102, fArr120, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas11 = canvas;
                                    drawArcRadialGradient(canvas11, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr102, fArr120, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas12 = canvas;
                                    drawArcRadialGradient(canvas12, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr102, fArr120, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor110, creatThreePositionFloat113, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas13 = canvas;
                                    int[] iArr103 = creatThreePositionColor110;
                                    float[] fArr121 = creatThreePositionFloat113;
                                    drawArcRadialGradient(canvas13, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr103, fArr121, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
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
                                    Canvas canvas14 = canvas;
                                    int[] iArr104 = creatThreePositionColor112;
                                    float[] fArr122 = creatThreePositionFloat115;
                                    drawArcRadialGradient(canvas14, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr104, fArr122, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    float[] creatTwoPositionFloat29 = creatTwoPositionFloat();
                                    int[] creatTwoPositionColor29 = creatTwoPositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatTwoPositionColor29, creatTwoPositionFloat29, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat116 = creatThreePositionFloat();
                                    int[] creatThreePositionColor113 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor113, creatThreePositionFloat116, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    int[] iArr105 = creatThreePositionColor113;
                                    float[] fArr123 = creatThreePositionFloat116;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr105, fArr123, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas15 = canvas;
                                    drawArcRadialGradient(canvas15, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr105, fArr123, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    float[] creatTwoPositionFloat30 = creatTwoPositionFloat();
                                    int[] creatTwoPositionColor30 = creatTwoPositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatTwoPositionColor30, creatTwoPositionFloat30, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat117 = creatThreePositionFloat();
                                    int[] creatThreePositionColor114 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    int[] iArr106 = creatThreePositionColor114;
                                    float[] fArr124 = creatThreePositionFloat117;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr106, fArr124, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas16 = canvas;
                                    drawArcRadialGradient(canvas16, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr106, fArr124, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas17 = canvas;
                                    drawArcRadialGradient(canvas17, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr106, fArr124, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor114, creatThreePositionFloat117, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat118 = creatThreePositionFloat();
                                    int[] creatThreePositionColor115 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    int[] iArr107 = creatThreePositionColor115;
                                    float[] fArr125 = creatThreePositionFloat118;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr107, fArr125, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas18 = canvas;
                                    drawArcRadialGradient(canvas18, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr107, fArr125, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor115, creatThreePositionFloat118, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas19 = canvas;
                                    int[] iArr108 = creatThreePositionColor115;
                                    float[] fArr126 = creatThreePositionFloat118;
                                    drawArcRadialGradient(canvas19, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr108, fArr126, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat119 = creatThreePositionFloat();
                                    int[] creatThreePositionColor116 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor116, creatThreePositionFloat119, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas20 = canvas;
                                    int[] iArr109 = creatThreePositionColor116;
                                    float[] fArr127 = creatThreePositionFloat119;
                                    drawArcRadialGradient(canvas20, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr109, fArr127, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat120 = creatThreePositionFloat();
                                    int[] creatThreePositionColor117 = creatThreePositionColor();
                                    Canvas canvas21 = canvas;
                                    int[] iArr110 = creatThreePositionColor117;
                                    float[] fArr128 = creatThreePositionFloat120;
                                    drawArcRadialGradient(canvas21, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr110, fArr128, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor117, creatThreePositionFloat120, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    float[] creatThreePositionFloat121 = creatThreePositionFloat();
                                    int[] creatThreePositionColor118 = creatThreePositionColor();
                                    Canvas canvas22 = canvas;
                                    int[] iArr111 = creatThreePositionColor118;
                                    float[] fArr129 = creatThreePositionFloat121;
                                    drawArcRadialGradient(canvas22, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr111, fArr129, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor118, creatThreePositionFloat121, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas23 = canvas;
                                    int[] iArr112 = creatThreePositionColor118;
                                    float[] fArr130 = creatThreePositionFloat121;
                                    drawArcRadialGradient(canvas23, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr112, fArr130, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat122 = creatThreePositionFloat();
                                    int[] creatThreePositionColor119 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    int[] iArr113 = creatThreePositionColor119;
                                    float[] fArr131 = creatThreePositionFloat122;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr113, fArr131, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas24 = canvas;
                                    drawArcRadialGradient(canvas24, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr113, fArr131, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor119, creatThreePositionFloat122, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas25 = canvas;
                                    int[] iArr114 = creatThreePositionColor119;
                                    float[] fArr132 = creatThreePositionFloat122;
                                    drawArcRadialGradient(canvas25, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr114, fArr132, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat123 = creatThreePositionFloat();
                                    int[] creatThreePositionColor120 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas26 = canvas;
                                    int[] iArr115 = creatThreePositionColor120;
                                    float[] fArr133 = creatThreePositionFloat123;
                                    drawArcRadialGradient(canvas26, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr115, fArr133, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas27 = canvas;
                                    drawArcRadialGradient(canvas27, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr115, fArr133, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor120, creatThreePositionFloat123, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas28 = canvas;
                                    int[] iArr116 = creatThreePositionColor120;
                                    float[] fArr134 = creatThreePositionFloat123;
                                    drawArcRadialGradient(canvas28, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr116, fArr134, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                                    float[] creatThreePositionFloat124 = creatThreePositionFloat();
                                    int[] creatThreePositionColor121 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas29 = canvas;
                                    int[] iArr117 = creatThreePositionColor121;
                                    float[] fArr135 = creatThreePositionFloat124;
                                    drawArcRadialGradient(canvas29, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr117, fArr135, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor121, creatThreePositionFloat124, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas30 = canvas;
                                    int[] iArr118 = creatThreePositionColor121;
                                    float[] fArr136 = creatThreePositionFloat124;
                                    drawArcRadialGradient(canvas30, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr118, fArr136, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat125 = creatThreePositionFloat();
                                    int[] creatThreePositionColor122 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas31 = canvas;
                                    int[] iArr119 = creatThreePositionColor122;
                                    float[] fArr137 = creatThreePositionFloat125;
                                    drawArcRadialGradient(canvas31, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr119, fArr137, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                                    Canvas canvas32 = canvas;
                                    drawArcRadialGradient(canvas32, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr119, fArr137, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor122, creatThreePositionFloat125, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                                    float[] creatThreePositionFloat126 = creatThreePositionFloat();
                                    int[] creatThreePositionColor123 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    int[] iArr120 = creatThreePositionColor123;
                                    float[] fArr138 = creatThreePositionFloat126;
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr120, fArr138, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    Canvas canvas33 = canvas;
                                    drawArcRadialGradient(canvas33, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr120, fArr138, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor123, creatThreePositionFloat126, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else if ((this.mCornerPosition & 1) != 1 || (this.mCornerPosition & 16) == 16 || (this.mCornerPosition & 256) == 256 || (this.mCornerPosition & 4096) != 4096) {
                                    float[] fArr139 = fArr12;
                                    drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr139, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, (float) this.mViewHeight));
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr139, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (float) this.mViewHeight));
                                } else {
                                    float[] creatThreePositionFloat127 = creatThreePositionFloat();
                                    int[] creatThreePositionColor124 = creatThreePositionColor();
                                    drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                    drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
                                    drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor124, creatThreePositionFloat127, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                    Canvas canvas34 = canvas;
                                    int[] iArr121 = creatThreePositionColor124;
                                    float[] fArr140 = creatThreePositionFloat127;
                                    drawArcRadialGradient(canvas34, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr121, fArr140, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
                                }
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat128 = creatThreePositionFloat();
                                int[] creatThreePositionColor125 = creatThreePositionColor();
                                int[] iArr122 = creatThreePositionColor125;
                                float[] fArr141 = creatThreePositionFloat128;
                                drawLeftSide(canvas, iArr122, fArr141, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawTopSide(canvas, iArr122, fArr141, this.mRoundCornerRadius + this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftTopArcGradient(canvas2, creatThreePositionColor125, creatThreePositionFloat128);
                                drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor125, creatThreePositionFloat128);
                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor125, creatThreePositionFloat128);
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat129 = creatThreePositionFloat();
                                int[] creatThreePositionColor126 = creatThreePositionColor();
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor126, creatThreePositionFloat129, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius));
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat130 = creatThreePositionFloat();
                                int[] creatThreePositionColor127 = creatThreePositionColor();
                                int[] iArr123 = creatThreePositionColor127;
                                float[] fArr142 = creatThreePositionFloat130;
                                drawLeftSide(canvas, iArr123, fArr142, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawTopSide(canvas, iArr123, fArr142, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor127, creatThreePositionFloat130);
                                drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat131 = creatThreePositionFloat();
                                int[] creatThreePositionColor128 = creatThreePositionColor();
                                int[] iArr124 = creatThreePositionColor128;
                                float[] fArr143 = creatThreePositionFloat131;
                                drawLeftSide(canvas, iArr124, fArr143, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
                                drawTopSide(canvas, iArr124, fArr143, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor128, creatThreePositionFloat131);
                                drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat132 = creatThreePositionFloat();
                                int[] creatThreePositionColor129 = creatThreePositionColor();
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight));
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor129, creatThreePositionFloat132, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius));
                                Canvas canvas35 = canvas;
                                int[] iArr125 = creatThreePositionColor129;
                                float[] fArr144 = creatThreePositionFloat132;
                                drawArcRadialGradient(canvas35, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr125, fArr144, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
                            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) != 256) {
                                float[] creatThreePositionFloat133 = creatThreePositionFloat();
                                int[] creatThreePositionColor130 = creatThreePositionColor();
                                float[] fArr145 = creatThreePositionFloat133;
                                drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor130, fArr145, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius));
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, creatThreePositionColor130, fArr145, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                                int[] iArr126 = creatThreePositionColor130;
                                float[] fArr146 = creatThreePositionFloat133;
                                drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr126, fArr146, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
                                Canvas canvas36 = canvas;
                                drawArcRadialGradient(canvas36, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr126, fArr146, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
                            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 256) == 256) {
                                float[] creatThreePositionFloat134 = creatThreePositionFloat();
                                int[] creatThreePositionColor131 = creatThreePositionColor();
                                int[] iArr127 = creatThreePositionColor131;
                                float[] fArr147 = creatThreePositionFloat134;
                                drawTopSide(canvas, iArr127, fArr147, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                                drawLeftSide(canvas, iArr127, fArr147, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                                drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor131, creatThreePositionFloat134);
                                drawRightTopUpArcGradient(canvas2, creatThreePositionColor131, creatThreePositionFloat134);
                                drawLeftTopRightAngle(canvas2, creatTwoPositionColor(), creatTwoPositionFloat());
                            } else {
                                float[] fArr148 = fArr12;
                                drawRectLinearGradient(canvas, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr148, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, this.mShadowRadius, (float) this.mViewHeight));
                                drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr148, Shader.TileMode.CLAMP, new RectF(this.mShadowRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius));
                                drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr12, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
                            }
                        } else if ((this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) == 4096) {
                            float[] creatThreePositionFloat135 = creatThreePositionFloat();
                            int[] creatThreePositionColor132 = creatThreePositionColor();
                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor132, creatThreePositionFloat135);
                            drawBottomSide(canvas, creatThreePositionColor132, creatThreePositionFloat135, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor132, creatThreePositionFloat135);
                        } else if ((this.mCornerPosition & 16) == 16 && (this.mCornerPosition & 4096) != 4096) {
                            float[] creatThreePositionFloat136 = creatThreePositionFloat();
                            int[] creatThreePositionColor133 = creatThreePositionColor();
                            drawLeftBottomDownArcGradient(canvas2, creatThreePositionColor133, creatThreePositionFloat136);
                            drawBottomSide(canvas, creatThreePositionColor133, creatThreePositionFloat136, this.mRoundCornerRadius, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                        } else if ((this.mCornerPosition & 16) != 16 && (this.mCornerPosition & 4096) == 4096) {
                            float[] creatThreePositionFloat137 = creatThreePositionFloat();
                            int[] creatThreePositionColor134 = creatThreePositionColor();
                            drawBottomSide(canvas, creatThreePositionColor134, creatThreePositionFloat137, CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mViewHeight) - this.mShadowRadius) - this.mRoundCornerRadius, ((float) this.mViewWidth) - this.mRoundCornerRadius, (float) this.mViewHeight);
                            drawRightBottomDownArcGradient(canvas2, creatThreePositionColor134, creatThreePositionFloat137);
                        }
                    } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) == 4096) {
                        float[] creatThreePositionFloat138 = creatThreePositionFloat();
                        int[] creatThreePositionColor135 = creatThreePositionColor();
                        drawRightTopDownArcGradient(canvas2, creatThreePositionColor135, creatThreePositionFloat138);
                        drawRightSide(canvas, creatThreePositionColor135, creatThreePositionFloat138, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                        drawRightBottomUpArcGradient(canvas2, creatThreePositionColor135, creatThreePositionFloat138);
                    } else if ((this.mCornerPosition & 256) == 256 && (this.mCornerPosition & 4096) != 4096) {
                        float[] creatThreePositionFloat139 = creatThreePositionFloat();
                        int[] creatThreePositionColor136 = creatThreePositionColor();
                        drawRightTopDownArcGradient(canvas2, creatThreePositionColor136, creatThreePositionFloat139);
                        drawRightSide(canvas, creatThreePositionColor136, creatThreePositionFloat139, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, (float) this.mViewWidth, (float) this.mViewHeight);
                    } else if ((this.mCornerPosition & 256) != 256 && (this.mCornerPosition & 4096) == 4096) {
                        float[] creatThreePositionFloat140 = creatThreePositionFloat();
                        int[] creatThreePositionColor137 = creatThreePositionColor();
                        drawRightBottomUpArcGradient(canvas2, creatThreePositionColor137, creatThreePositionFloat140);
                        drawRightSide(canvas, creatThreePositionColor137, creatThreePositionFloat140, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                    }
                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) == 256) {
                    float[] creatThreePositionFloat141 = creatThreePositionFloat();
                    int[] creatThreePositionColor138 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor138, creatThreePositionFloat141, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                    drawLeftTopUpArcGradient(canvas2, creatThreePositionColor138, creatThreePositionFloat141);
                    drawRightTopUpArcGradient(canvas2, creatThreePositionColor138, creatThreePositionFloat141);
                } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 256) != 256) {
                    float[] creatThreePositionFloat142 = creatThreePositionFloat();
                    int[] creatThreePositionColor139 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor139, creatThreePositionFloat142, this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius + this.mRoundCornerRadius);
                    drawLeftTopUpArcGradient(canvas2, creatThreePositionColor139, creatThreePositionFloat142);
                } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 256) == 256) {
                    float[] creatThreePositionFloat143 = creatThreePositionFloat();
                    int[] creatThreePositionColor140 = creatThreePositionColor();
                    drawTopSide(canvas, creatThreePositionColor140, creatThreePositionFloat143, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius);
                    drawRightTopUpArcGradient(canvas2, creatThreePositionColor140, creatThreePositionFloat143);
                }
            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) != 16) {
                float[] creatThreePositionFloat144 = creatThreePositionFloat();
                int[] creatThreePositionColor141 = creatThreePositionColor();
                drawLeftTopDownArcGradient(canvas2, creatThreePositionColor141, creatThreePositionFloat144);
                drawLeftSide(canvas, creatThreePositionColor141, creatThreePositionFloat144, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, (float) this.mViewHeight);
            } else if ((this.mCornerPosition & 1) != 1 && (this.mCornerPosition & 16) == 16) {
                float[] creatThreePositionFloat145 = creatThreePositionFloat();
                int[] creatThreePositionColor142 = creatThreePositionColor();
                drawLeftSide(canvas, creatThreePositionColor142, creatThreePositionFloat145, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor142, creatThreePositionFloat145);
            } else if ((this.mCornerPosition & 1) == 1 && (this.mCornerPosition & 16) == 16) {
                float[] creatThreePositionFloat146 = creatThreePositionFloat();
                int[] creatThreePositionColor143 = creatThreePositionColor();
                drawLeftSide(canvas, creatThreePositionColor143, creatThreePositionFloat146, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius);
                drawLeftBottomUpArcGradient(canvas2, creatThreePositionColor143, creatThreePositionFloat146);
                drawLeftTopDownArcGradient(canvas2, creatThreePositionColor143, creatThreePositionFloat146);
            }
        }
    }

    private void drawLeftTopArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
    }

    private void drawLeftTopDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, this.mRoundCornerRadius * 2.0f), 180.0f, 90.0f);
    }

    private void drawLeftTopUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mRoundCornerRadius * 2.0f, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 180.0f, 90.0f);
    }

    private void drawRightTopArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
    }

    private void drawRightTopUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), 270.0f, 90.0f);
    }

    private void drawRightTopDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, (((float) this.mViewWidth) - this.mShadowRadius) - this.mRoundCornerRadius, this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mRoundCornerRadius * 2.0f), 270.0f, 90.0f);
    }

    private void drawLeftBottomArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftBottomUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mShadowRadius + this.mRoundCornerRadius, ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (this.mShadowRadius + this.mRoundCornerRadius) * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawLeftBottomDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), this.mRoundCornerRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawRightBottomArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightBottomUpArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), ((float) this.mViewHeight) - this.mRoundCornerRadius, this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), ((float) this.mViewHeight) - (this.mRoundCornerRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawRightBottomDownArcGradient(Canvas canvas, int[] iArr, float[] fArr) {
        Canvas canvas2 = canvas;
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        drawArcRadialGradient(canvas2, ((float) this.mViewWidth) - this.mRoundCornerRadius, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), this.mShadowRadius + this.mRoundCornerRadius, iArr2, fArr2, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mRoundCornerRadius * 2.0f), ((float) this.mViewHeight) - ((this.mShadowRadius + this.mRoundCornerRadius) * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawLeftSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        float f5 = f;
        drawRectLinearGradient(canvas, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawBottomSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        float f5 = f;
        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewHeight, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawRightSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        float f5 = f;
        drawRectLinearGradient(canvas, ((float) this.mViewWidth) - (this.mShadowRadius + this.mRoundCornerRadius), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawTopSide(Canvas canvas, int[] iArr, float[] fArr, float f, float f2, float f3, float f4) {
        float f5 = f;
        drawRectLinearGradient(canvas, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius + this.mRoundCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, iArr, fArr, Shader.TileMode.CLAMP, new RectF(f, f2, f3, f4));
    }

    private void drawLeftBottomRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), this.mShadowRadius * 2.0f, (float) this.mViewHeight), 90.0f, 90.0f);
    }

    private void drawRightBottomRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, ((float) this.mViewHeight) - this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), ((float) this.mViewHeight) - (this.mShadowRadius * 2.0f), (float) this.mViewWidth, (float) this.mViewHeight), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
    }

    private void drawLeftTopRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.mShadowRadius * 2.0f, this.mShadowRadius * 2.0f), 180.0f, 90.0f);
    }

    private void drawRightTopRightAngle(Canvas canvas, int[] iArr, float[] fArr) {
        drawArcRadialGradient(canvas, ((float) this.mViewWidth) - this.mShadowRadius, this.mShadowRadius, this.mShadowRadius, iArr, fArr, Shader.TileMode.CLAMP, new RectF(((float) this.mViewWidth) - (this.mShadowRadius * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mViewWidth, this.mShadowRadius * 2.0f), 270.0f, 90.0f);
    }

    private float[] creatTwoPositionFloat() {
        return new float[]{0.01f, 1.0f};
    }

    private float[] creatThreePositionFloat() {
        return new float[]{this.mRoundCornerRadius / (this.mShadowRadius + this.mRoundCornerRadius), this.mRoundCornerRadius / (this.mShadowRadius + this.mRoundCornerRadius), 1.0f};
    }

    private int[] creatTwoPositionColor() {
        return new int[]{this.mShadowColor, 16777215};
    }

    private int[] creatThreePositionColor() {
        return new int[]{16777215, this.mShadowColor, 16777215};
    }

    private void drawRectLinearGradient(Canvas canvas, float f, float f2, float f3, float f4, int[] iArr, float[] fArr, Shader.TileMode tileMode, RectF rectF) {
        this.mPaint.setShader(new LinearGradient(f, f2, f3, f4, iArr, fArr, tileMode));
        Canvas canvas2 = canvas;
        canvas.drawRect(rectF, this.mPaint);
    }

    private void drawArcRadialGradient(Canvas canvas, float f, float f2, float f3, int[] iArr, float[] fArr, Shader.TileMode tileMode, RectF rectF, float f4, float f5) {
        this.mPaint.setShader(new RadialGradient(f, f2, f3, iArr, fArr, tileMode));
        canvas.drawArc(rectF, f4, f5, true, this.mPaint);
    }

    private float dip2px(float f) {
        return (f * getContext().getResources().getDisplayMetrics().density) + 0.5f;
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
