package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.util.RectUtils;

public class OverlayView extends View {
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final boolean DEFAULT_FREESTYLE_CROP_ENABLED = false;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    private OverlayViewChangeListener mCallback;
    private boolean mCircleDimmedLayer;
    private Path mCircularPath;
    private Paint mCropFrameCornersPaint;
    private Paint mCropFramePaint;
    protected float[] mCropGridCenter;
    private int mCropGridColumnCount;
    protected float[] mCropGridCorners;
    private Paint mCropGridPaint;
    private int mCropGridRowCount;
    private int mCropRectCornerTouchAreaLineLength;
    private int mCropRectMinSize;
    private final RectF mCropViewRect;
    private int mCurrentTouchCornerIndex;
    private int mDimmedColor;
    private Paint mDimmedStrokePaint;
    private float[] mGridPoints;
    private boolean mIsFreestyleCropEnabled;
    private float mPreviousTouchX;
    private float mPreviousTouchY;
    private boolean mShouldSetupCropBounds;
    private boolean mShowCropFrame;
    private boolean mShowCropGrid;
    private float mTargetAspectRatio;
    private final RectF mTempRect;
    protected int mThisHeight;
    protected int mThisWidth;
    private int mTouchPointThreshold;

    public OverlayView(Context context) {
        this(context, (AttributeSet) null);
    }

    public OverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCropViewRect = new RectF();
        this.mTempRect = new RectF();
        this.mGridPoints = null;
        this.mCircularPath = new Path();
        this.mDimmedStrokePaint = new Paint(1);
        this.mCropGridPaint = new Paint(1);
        this.mCropFramePaint = new Paint(1);
        this.mCropFrameCornersPaint = new Paint(1);
        this.mIsFreestyleCropEnabled = false;
        this.mPreviousTouchX = -1.0f;
        this.mPreviousTouchY = -1.0f;
        this.mCurrentTouchCornerIndex = -1;
        this.mTouchPointThreshold = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.mCropRectMinSize = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_min_size);
        this.mCropRectCornerTouchAreaLineLength = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        init();
    }

    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.mCallback;
    }

    public void setOverlayViewChangeListener(OverlayViewChangeListener overlayViewChangeListener) {
        this.mCallback = overlayViewChangeListener;
    }

    @NonNull
    public RectF getCropViewRect() {
        return this.mCropViewRect;
    }

    public boolean isFreestyleCropEnabled() {
        return this.mIsFreestyleCropEnabled;
    }

    public void setFreestyleCropEnabled(boolean z) {
        this.mIsFreestyleCropEnabled = z;
    }

    public void setCircleDimmedLayer(boolean z) {
        this.mCircleDimmedLayer = z;
    }

    public void setCropGridRowCount(@IntRange(from = 0) int i) {
        this.mCropGridRowCount = i;
        this.mGridPoints = null;
    }

    public void setCropGridColumnCount(@IntRange(from = 0) int i) {
        this.mCropGridColumnCount = i;
        this.mGridPoints = null;
    }

    public void setShowCropFrame(boolean z) {
        this.mShowCropFrame = z;
    }

    public void setShowCropGrid(boolean z) {
        this.mShowCropGrid = z;
    }

    public void setDimmedColor(@ColorInt int i) {
        this.mDimmedColor = i;
    }

    public void setCropFrameStrokeWidth(@IntRange(from = 0) int i) {
        this.mCropFramePaint.setStrokeWidth((float) i);
    }

    public void setCropGridStrokeWidth(@IntRange(from = 0) int i) {
        this.mCropGridPaint.setStrokeWidth((float) i);
    }

    public void setCropFrameColor(@ColorInt int i) {
        this.mCropFramePaint.setColor(i);
    }

    public void setCropGridColor(@ColorInt int i) {
        this.mCropGridPaint.setColor(i);
    }

    public void setTargetAspectRatio(float f) {
        this.mTargetAspectRatio = f;
        if (this.mThisWidth > 0) {
            setupCropBounds();
            postInvalidate();
            return;
        }
        this.mShouldSetupCropBounds = true;
    }

    public void setupCropBounds() {
        int i = (int) (((float) this.mThisWidth) / this.mTargetAspectRatio);
        if (i > this.mThisHeight) {
            int i2 = (int) (((float) this.mThisHeight) * this.mTargetAspectRatio);
            int i3 = (this.mThisWidth - i2) / 2;
            this.mCropViewRect.set((float) (getPaddingLeft() + i3), (float) getPaddingTop(), (float) (getPaddingLeft() + i2 + i3), (float) (getPaddingTop() + this.mThisHeight));
        } else {
            int i4 = (this.mThisHeight - i) / 2;
            this.mCropViewRect.set((float) getPaddingLeft(), (float) (getPaddingTop() + i4), (float) (getPaddingLeft() + this.mThisWidth), (float) (getPaddingTop() + i + i4));
        }
        if (this.mCallback != null) {
            this.mCallback.onCropRectUpdated(this.mCropViewRect);
        }
        updateGridPoints();
    }

    private void updateGridPoints() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.mCropViewRect);
        this.mCropGridCenter = RectUtils.getCenterFromRect(this.mCropViewRect);
        this.mGridPoints = null;
        this.mCircularPath.reset();
        this.mCircularPath.addCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (Build.VERSION.SDK_INT < 18 && Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.mThisWidth = (getWidth() - getPaddingRight()) - paddingLeft;
            this.mThisHeight = (getHeight() - getPaddingBottom()) - paddingTop;
            if (this.mShouldSetupCropBounds) {
                this.mShouldSetupCropBounds = false;
                setTargetAspectRatio(this.mTargetAspectRatio);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDimmedLayer(canvas);
        drawCropGrid(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mCropViewRect.isEmpty() || !this.mIsFreestyleCropEnabled) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if ((motionEvent.getAction() & 255) == 0) {
            if (this.mPreviousTouchX < CropImageView.DEFAULT_ASPECT_RATIO) {
                this.mPreviousTouchX = x;
                this.mPreviousTouchY = y;
            }
            this.mCurrentTouchCornerIndex = getCurrentTouchIndex(x, y);
            if (this.mCurrentTouchCornerIndex != -1) {
                return true;
            }
            return false;
        } else if ((motionEvent.getAction() & 255) == 2 && motionEvent.getPointerCount() == 1 && this.mCurrentTouchCornerIndex != -1) {
            float min = Math.min(Math.max(x, (float) getPaddingLeft()), (float) (getWidth() - getPaddingRight()));
            float min2 = Math.min(Math.max(y, (float) getPaddingTop()), (float) (getHeight() - getPaddingBottom()));
            updateCropViewRect(min, min2);
            this.mPreviousTouchX = min;
            this.mPreviousTouchY = min2;
            return true;
        } else {
            if ((motionEvent.getAction() & 255) == 1) {
                this.mPreviousTouchX = -1.0f;
                this.mPreviousTouchY = -1.0f;
                this.mCurrentTouchCornerIndex = -1;
                if (this.mCallback != null) {
                    this.mCallback.onCropRectUpdated(this.mCropViewRect);
                }
            }
            return false;
        }
    }

    private void updateCropViewRect(float f, float f2) {
        this.mTempRect.set(this.mCropViewRect);
        switch (this.mCurrentTouchCornerIndex) {
            case 0:
                this.mTempRect.set(f, f2, this.mCropViewRect.right, this.mCropViewRect.bottom);
                break;
            case 1:
                this.mTempRect.set(this.mCropViewRect.left, f2, f, this.mCropViewRect.bottom);
                break;
            case 2:
                this.mTempRect.set(this.mCropViewRect.left, this.mCropViewRect.top, f, f2);
                break;
            case 3:
                this.mTempRect.set(f, this.mCropViewRect.top, this.mCropViewRect.right, f2);
                break;
            case 4:
                this.mTempRect.offset(f - this.mPreviousTouchX, f2 - this.mPreviousTouchY);
                if (this.mTempRect.left > ((float) getLeft()) && this.mTempRect.top > ((float) getTop()) && this.mTempRect.right < ((float) getRight()) && this.mTempRect.bottom < ((float) getBottom())) {
                    this.mCropViewRect.set(this.mTempRect);
                    updateGridPoints();
                    postInvalidate();
                    return;
                }
                return;
        }
        boolean z = false;
        boolean z2 = this.mTempRect.height() >= ((float) this.mCropRectMinSize);
        if (this.mTempRect.width() >= ((float) this.mCropRectMinSize)) {
            z = true;
        }
        this.mCropViewRect.set((z ? this.mTempRect : this.mCropViewRect).left, (z2 ? this.mTempRect : this.mCropViewRect).top, (z ? this.mTempRect : this.mCropViewRect).right, (z2 ? this.mTempRect : this.mCropViewRect).bottom);
        if (z2 || z) {
            updateGridPoints();
            postInvalidate();
        }
    }

    private int getCurrentTouchIndex(float f, float f2) {
        double d = (double) this.mTouchPointThreshold;
        int i = -1;
        for (int i2 = 0; i2 < 8; i2 += 2) {
            double sqrt = Math.sqrt(Math.pow((double) (f - this.mCropGridCorners[i2]), 2.0d) + Math.pow((double) (f2 - this.mCropGridCorners[i2 + 1]), 2.0d));
            if (sqrt < d) {
                i = i2 / 2;
                d = sqrt;
            }
        }
        if (i >= 0 || !this.mCropViewRect.contains(f, f2)) {
            return i;
        }
        return 4;
    }

    /* access modifiers changed from: protected */
    public void drawDimmedLayer(@NonNull Canvas canvas) {
        canvas.save();
        if (this.mCircleDimmedLayer) {
            canvas.clipPath(this.mCircularPath, Region.Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.mCropViewRect, Region.Op.DIFFERENCE);
        }
        canvas.drawColor(this.mDimmedColor);
        canvas.restore();
        if (this.mCircleDimmedLayer) {
            canvas.drawCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, this.mDimmedStrokePaint);
        }
    }

    /* access modifiers changed from: protected */
    public void drawCropGrid(@NonNull Canvas canvas) {
        if (this.mShowCropGrid) {
            if (this.mGridPoints == null && !this.mCropViewRect.isEmpty()) {
                this.mGridPoints = new float[((this.mCropGridRowCount * 4) + (this.mCropGridColumnCount * 4))];
                int i = 0;
                int i2 = 0;
                while (i < this.mCropGridRowCount) {
                    int i3 = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.left;
                    int i4 = i3 + 1;
                    float f = ((float) i) + 1.0f;
                    this.mGridPoints[i3] = (this.mCropViewRect.height() * (f / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                    int i5 = i4 + 1;
                    this.mGridPoints[i4] = this.mCropViewRect.right;
                    this.mGridPoints[i5] = (this.mCropViewRect.height() * (f / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                    i++;
                    i2 = i5 + 1;
                }
                for (int i6 = 0; i6 < this.mCropGridColumnCount; i6++) {
                    int i7 = i2 + 1;
                    float f2 = ((float) i6) + 1.0f;
                    this.mGridPoints[i2] = (this.mCropViewRect.width() * (f2 / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    int i8 = i7 + 1;
                    this.mGridPoints[i7] = this.mCropViewRect.top;
                    int i9 = i8 + 1;
                    this.mGridPoints[i8] = (this.mCropViewRect.width() * (f2 / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    i2 = i9 + 1;
                    this.mGridPoints[i9] = this.mCropViewRect.bottom;
                }
            }
            if (this.mGridPoints != null) {
                canvas.drawLines(this.mGridPoints, this.mCropGridPaint);
            }
        }
        if (this.mShowCropFrame) {
            canvas.drawRect(this.mCropViewRect, this.mCropFramePaint);
        }
        if (this.mIsFreestyleCropEnabled) {
            canvas.save();
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) this.mCropRectCornerTouchAreaLineLength, (float) (-this.mCropRectCornerTouchAreaLineLength));
            canvas.clipRect(this.mTempRect, Region.Op.DIFFERENCE);
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) (-this.mCropRectCornerTouchAreaLineLength), (float) this.mCropRectCornerTouchAreaLineLength);
            canvas.clipRect(this.mTempRect, Region.Op.DIFFERENCE);
            canvas.drawRect(this.mCropViewRect, this.mCropFrameCornersPaint);
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public void processStyledAttributes(@NonNull TypedArray typedArray) {
        this.mCircleDimmedLayer = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        this.mDimmedColor = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(R.color.ucrop_color_default_dimmed));
        this.mDimmedStrokePaint.setColor(this.mDimmedColor);
        this.mDimmedStrokePaint.setStyle(Paint.Style.STROKE);
        this.mDimmedStrokePaint.setStrokeWidth(1.0f);
        initCropFrameStyle(typedArray);
        this.mShowCropFrame = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_frame, true);
        initCropGridStyle(typedArray);
        this.mShowCropGrid = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_grid, true);
    }

    private void initCropFrameStyle(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_frame_color, getResources().getColor(R.color.ucrop_color_default_crop_frame));
        this.mCropFramePaint.setStrokeWidth((float) dimensionPixelSize);
        this.mCropFramePaint.setColor(color);
        this.mCropFramePaint.setStyle(Paint.Style.STROKE);
        this.mCropFrameCornersPaint.setStrokeWidth((float) (dimensionPixelSize * 3));
        this.mCropFrameCornersPaint.setColor(color);
        this.mCropFrameCornersPaint.setStyle(Paint.Style.STROKE);
    }

    private void initCropGridStyle(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_grid_color, getResources().getColor(R.color.ucrop_color_default_crop_grid));
        this.mCropGridPaint.setStrokeWidth((float) dimensionPixelSize);
        this.mCropGridPaint.setColor(color);
        this.mCropGridRowCount = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.mCropGridColumnCount = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
}
