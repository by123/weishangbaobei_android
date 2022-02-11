package com.isseiaoki.simplecropview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;

public class CropImageView extends ImageView {
    private static final float DEFAULT_INITIAL_FRAME_SCALE = 0.75f;
    private static final int FRAME_STROKE_WEIGHT_IN_DP = 1;
    private static final int GUIDE_STROKE_WEIGHT_IN_DP = 1;
    private static final int HANDLE_SIZE_IN_DP = 16;
    private static final int MIN_FRAME_SIZE_IN_DP = 50;
    private static final String TAG = "CropImageView";
    private final int TRANSLUCENT_BLACK;
    private final int TRANSLUCENT_WHITE;
    private final int TRANSPARENT;
    private final int WHITE;
    private float mAngle;
    private int mBackgroundColor;
    private PointF mCenter;
    private CropMode mCropMode;
    private PointF mCustomRatio;
    private int mFrameColor;
    private RectF mFrameRect;
    private float mFrameStrokeWeight;
    private int mGuideColor;
    private ShowMode mGuideShowMode;
    private float mGuideStrokeWeight;
    private int mHandleColor;
    private ShowMode mHandleShowMode;
    private int mHandleSize;
    private RectF mImageRect;
    private float mImgHeight;
    private float mImgWidth;
    private float mInitialFrameScale;
    private boolean mIsCropEnabled;
    private boolean mIsEnabled;
    private boolean mIsInitialized;
    private float mLastX;
    private float mLastY;
    private Matrix mMatrix;
    private float mMinFrameSize;
    private int mOverlayColor;
    private Paint mPaintBitmap;
    private Paint mPaintFrame;
    private Paint mPaintTransparent;
    private float mScale;
    private boolean mShowGuide;
    private boolean mShowHandle;
    private TouchArea mTouchArea;
    private int mTouchPadding;
    private int mViewHeight;
    private int mViewWidth;

    private enum TouchArea {
        OUT_OF_BOUNDS,
        CENTER,
        LEFT_TOP,
        RIGHT_TOP,
        LEFT_BOTTOM,
        RIGHT_BOTTOM
    }

    private float constrain(float f, float f2, float f3, float f4) {
        return (f < f2 || f > f3) ? f4 : f;
    }

    private float sq(float f) {
        return f * f;
    }

    public CropImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TRANSLUCENT_WHITE = -1140850689;
        this.WHITE = -1;
        this.TRANSLUCENT_BLACK = -1157627904;
        this.mViewWidth = 0;
        this.mViewHeight = 0;
        this.mScale = 1.0f;
        this.mAngle = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        this.mImgWidth = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        this.mImgHeight = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        this.mIsInitialized = false;
        this.mMatrix = null;
        this.mCenter = new PointF();
        this.mTouchArea = TouchArea.OUT_OF_BOUNDS;
        this.mCropMode = CropMode.RATIO_1_1;
        this.mGuideShowMode = ShowMode.SHOW_ALWAYS;
        this.mHandleShowMode = ShowMode.SHOW_ALWAYS;
        this.mTouchPadding = 0;
        this.mShowGuide = true;
        this.mShowHandle = true;
        this.mIsCropEnabled = true;
        this.mIsEnabled = true;
        this.mCustomRatio = new PointF(1.0f, 1.0f);
        this.mFrameStrokeWeight = 3.0f;
        this.mGuideStrokeWeight = 3.0f;
        this.TRANSPARENT = getResources().getColor(17170445);
        float density = getDensity();
        this.mHandleSize = (int) (16.0f * density);
        this.mMinFrameSize = 50.0f * density;
        float f = density * 1.0f;
        this.mFrameStrokeWeight = f;
        this.mGuideStrokeWeight = f;
        this.mPaintFrame = new Paint();
        this.mPaintTransparent = new Paint();
        this.mPaintBitmap = new Paint();
        this.mPaintBitmap.setFilterBitmap(true);
        this.mMatrix = new Matrix();
        this.mScale = 1.0f;
        this.mBackgroundColor = this.TRANSPARENT;
        this.mFrameColor = -1;
        this.mOverlayColor = -1157627904;
        this.mHandleColor = -1;
        this.mGuideColor = -1140850689;
        handleStyleable(context, attributeSet, i, density);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.image = getBitmap();
        savedState.mode = this.mCropMode;
        savedState.backgroundColor = this.mBackgroundColor;
        savedState.overlayColor = this.mOverlayColor;
        savedState.frameColor = this.mFrameColor;
        savedState.guideShowMode = this.mGuideShowMode;
        savedState.handleShowMode = this.mHandleShowMode;
        savedState.showGuide = this.mShowGuide;
        savedState.showHandle = this.mShowHandle;
        savedState.handleSize = this.mHandleSize;
        savedState.touchPadding = this.mTouchPadding;
        savedState.minFrameSize = this.mMinFrameSize;
        savedState.customRatioX = this.mCustomRatio.x;
        savedState.customRatioY = this.mCustomRatio.y;
        savedState.frameStrokeWeight = this.mFrameStrokeWeight;
        savedState.guideStrokeWeight = this.mGuideStrokeWeight;
        savedState.isCropEnabled = this.mIsCropEnabled;
        savedState.handleColor = this.mHandleColor;
        savedState.guideColor = this.mGuideColor;
        savedState.initialFrameScale = this.mInitialFrameScale;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCropMode = savedState.mode;
        this.mBackgroundColor = savedState.backgroundColor;
        this.mOverlayColor = savedState.overlayColor;
        this.mFrameColor = savedState.frameColor;
        this.mGuideShowMode = savedState.guideShowMode;
        this.mHandleShowMode = savedState.handleShowMode;
        this.mShowGuide = savedState.showGuide;
        this.mShowHandle = savedState.showHandle;
        this.mHandleSize = savedState.handleSize;
        this.mTouchPadding = savedState.touchPadding;
        this.mMinFrameSize = savedState.minFrameSize;
        this.mCustomRatio = new PointF(savedState.customRatioX, savedState.customRatioY);
        this.mFrameStrokeWeight = savedState.frameStrokeWeight;
        this.mGuideStrokeWeight = savedState.guideStrokeWeight;
        this.mIsCropEnabled = savedState.isCropEnabled;
        this.mHandleColor = savedState.handleColor;
        this.mGuideColor = savedState.guideColor;
        this.mInitialFrameScale = savedState.initialFrameScale;
        setImageBitmap(savedState.image);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mViewWidth = ((i3 - i) - getPaddingLeft()) - getPaddingRight();
        this.mViewHeight = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        if (getDrawable() != null) {
            initLayout(this.mViewWidth, this.mViewHeight);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIsInitialized) {
            setMatrix();
            Matrix matrix = new Matrix();
            matrix.postConcat(this.mMatrix);
            Bitmap bitmap = getBitmap();
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, matrix, this.mPaintBitmap);
                drawEditFrame(canvas);
            }
        }
    }

    private void handleStyleable(Context context, AttributeSet attributeSet, int i, float f) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CropImageView, i, 0);
        this.mCropMode = CropMode.RATIO_1_1;
        try {
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.CropImageView_imgSrc);
            if (drawable != null) {
                setImageDrawable(drawable);
            }
            CropMode[] values = CropMode.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                CropMode cropMode = values[i2];
                if (obtainStyledAttributes.getInt(R.styleable.CropImageView_cropMode, 3) == cropMode.getId()) {
                    this.mCropMode = cropMode;
                    break;
                }
                i2++;
            }
            this.mBackgroundColor = obtainStyledAttributes.getColor(R.styleable.CropImageView_backgroundColor, this.TRANSPARENT);
            super.setBackgroundColor(this.mBackgroundColor);
            this.mOverlayColor = obtainStyledAttributes.getColor(R.styleable.CropImageView_overlayColor, -1157627904);
            this.mFrameColor = obtainStyledAttributes.getColor(R.styleable.CropImageView_frameColor, -1);
            this.mHandleColor = obtainStyledAttributes.getColor(R.styleable.CropImageView_handleColor, -1);
            this.mGuideColor = obtainStyledAttributes.getColor(R.styleable.CropImageView_guideColor, -1140850689);
            ShowMode[] values2 = ShowMode.values();
            int length2 = values2.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length2) {
                    break;
                }
                ShowMode showMode = values2[i3];
                if (obtainStyledAttributes.getInt(R.styleable.CropImageView_guideShowMode, 1) == showMode.getId()) {
                    this.mGuideShowMode = showMode;
                    break;
                }
                i3++;
            }
            ShowMode[] values3 = ShowMode.values();
            int length3 = values3.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length3) {
                    break;
                }
                ShowMode showMode2 = values3[i4];
                if (obtainStyledAttributes.getInt(R.styleable.CropImageView_handleShowMode, 1) == showMode2.getId()) {
                    this.mHandleShowMode = showMode2;
                    break;
                }
                i4++;
            }
            setGuideShowMode(this.mGuideShowMode);
            setHandleShowMode(this.mHandleShowMode);
            this.mHandleSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_handleSize, (int) (16.0f * f));
            this.mTouchPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_touchPadding, 0);
            this.mMinFrameSize = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_minFrameSize, (int) (50.0f * f));
            int i5 = (int) (f * 1.0f);
            this.mFrameStrokeWeight = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_frameStrokeWeight, i5);
            this.mGuideStrokeWeight = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_guideStrokeWeight, i5);
            this.mIsCropEnabled = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropEnabled, true);
            this.mInitialFrameScale = constrain(obtainStyledAttributes.getFloat(R.styleable.CropImageView_initialFrameScale, DEFAULT_INITIAL_FRAME_SCALE), 0.01f, 1.0f, DEFAULT_INITIAL_FRAME_SCALE);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    private void drawEditFrame(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mIsCropEnabled) {
            if (this.mCropMode == CropMode.CIRCLE) {
                this.mPaintTransparent.setFilterBitmap(true);
                this.mPaintTransparent.setColor(this.mOverlayColor);
                this.mPaintTransparent.setStyle(Paint.Style.FILL);
                Path path = new Path();
                path.addRect(this.mImageRect.left, this.mImageRect.top, this.mImageRect.right, this.mImageRect.bottom, Path.Direction.CW);
                path.addCircle((this.mFrameRect.left + this.mFrameRect.right) / 2.0f, (this.mFrameRect.top + this.mFrameRect.bottom) / 2.0f, (this.mFrameRect.right - this.mFrameRect.left) / 2.0f, Path.Direction.CCW);
                canvas2.drawPath(path, this.mPaintTransparent);
            } else {
                this.mPaintTransparent.setFilterBitmap(true);
                this.mPaintTransparent.setColor(this.mOverlayColor);
                this.mPaintTransparent.setStyle(Paint.Style.FILL);
                canvas.drawRect(this.mImageRect.left, this.mImageRect.top, this.mImageRect.right, this.mFrameRect.top, this.mPaintTransparent);
                canvas.drawRect(this.mImageRect.left, this.mFrameRect.bottom, this.mImageRect.right, this.mImageRect.bottom, this.mPaintTransparent);
                canvas.drawRect(this.mImageRect.left, this.mFrameRect.top, this.mFrameRect.left, this.mFrameRect.bottom, this.mPaintTransparent);
                canvas.drawRect(this.mFrameRect.right, this.mFrameRect.top, this.mImageRect.right, this.mFrameRect.bottom, this.mPaintTransparent);
            }
            this.mPaintFrame.setAntiAlias(true);
            this.mPaintFrame.setFilterBitmap(true);
            this.mPaintFrame.setStyle(Paint.Style.STROKE);
            this.mPaintFrame.setColor(this.mFrameColor);
            this.mPaintFrame.setStrokeWidth(this.mFrameStrokeWeight);
            canvas.drawRect(this.mFrameRect.left, this.mFrameRect.top, this.mFrameRect.right, this.mFrameRect.bottom, this.mPaintFrame);
            if (this.mShowGuide) {
                this.mPaintFrame.setColor(this.mGuideColor);
                this.mPaintFrame.setStrokeWidth(this.mGuideStrokeWeight);
                float f = this.mFrameRect.left + ((this.mFrameRect.right - this.mFrameRect.left) / 3.0f);
                float f2 = this.mFrameRect.right - ((this.mFrameRect.right - this.mFrameRect.left) / 3.0f);
                float f3 = this.mFrameRect.top + ((this.mFrameRect.bottom - this.mFrameRect.top) / 3.0f);
                float f4 = this.mFrameRect.bottom - ((this.mFrameRect.bottom - this.mFrameRect.top) / 3.0f);
                canvas.drawLine(f, this.mFrameRect.top, f, this.mFrameRect.bottom, this.mPaintFrame);
                canvas.drawLine(f2, this.mFrameRect.top, f2, this.mFrameRect.bottom, this.mPaintFrame);
                canvas.drawLine(this.mFrameRect.left, f3, this.mFrameRect.right, f3, this.mPaintFrame);
                canvas.drawLine(this.mFrameRect.left, f4, this.mFrameRect.right, f4, this.mPaintFrame);
            }
            if (this.mShowHandle) {
                this.mPaintFrame.setStyle(Paint.Style.FILL);
                this.mPaintFrame.setColor(this.mHandleColor);
                canvas2.drawCircle(this.mFrameRect.left, this.mFrameRect.top, (float) this.mHandleSize, this.mPaintFrame);
                canvas2.drawCircle(this.mFrameRect.right, this.mFrameRect.top, (float) this.mHandleSize, this.mPaintFrame);
                canvas2.drawCircle(this.mFrameRect.left, this.mFrameRect.bottom, (float) this.mHandleSize, this.mPaintFrame);
                canvas2.drawCircle(this.mFrameRect.right, this.mFrameRect.bottom, (float) this.mHandleSize, this.mPaintFrame);
            }
        }
    }

    private void setMatrix() {
        this.mMatrix.reset();
        this.mMatrix.setTranslate(this.mCenter.x - (this.mImgWidth * 0.5f), this.mCenter.y - (this.mImgHeight * 0.5f));
        this.mMatrix.postScale(this.mScale, this.mScale, this.mCenter.x, this.mCenter.y);
        this.mMatrix.postRotate(this.mAngle, this.mCenter.x, this.mCenter.y);
    }

    private void initLayout(int i, int i2) {
        this.mImgWidth = (float) getDrawable().getIntrinsicWidth();
        this.mImgHeight = (float) getDrawable().getIntrinsicHeight();
        if (this.mImgWidth <= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mImgWidth = (float) i;
        }
        if (this.mImgHeight <= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mImgHeight = (float) i2;
        }
        float f = (float) i;
        float f2 = (float) i2;
        float f3 = f / f2;
        float f4 = this.mImgWidth / this.mImgHeight;
        float f5 = 1.0f;
        if (f4 >= f3) {
            f5 = f / this.mImgWidth;
        } else if (f4 < f3) {
            f5 = f2 / this.mImgHeight;
        }
        setCenter(new PointF(((float) getPaddingLeft()) + (f * 0.5f), ((float) getPaddingTop()) + (f2 * 0.5f)));
        setScale(f5);
        initCropFrame();
        adjustRatio();
        this.mIsInitialized = true;
    }

    private void initCropFrame() {
        setMatrix();
        float[] fArr = {0.0f, 0.0f, 0.0f, this.mImgHeight, this.mImgWidth, 0.0f, this.mImgWidth, this.mImgHeight};
        this.mMatrix.mapPoints(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[6];
        float f4 = fArr[7];
        this.mFrameRect = new RectF(f, f2, f3, f4);
        this.mImageRect = new RectF(f, f2, f3, f4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsInitialized || !this.mIsCropEnabled || !this.mIsEnabled) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                onDown(motionEvent);
                return true;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                onUp(motionEvent);
                return true;
            case 2:
                onMove(motionEvent);
                if (this.mTouchArea != TouchArea.OUT_OF_BOUNDS) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return true;
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                onCancel();
                return true;
            default:
                return false;
        }
    }

    private void onDown(MotionEvent motionEvent) {
        invalidate();
        this.mLastX = motionEvent.getX();
        this.mLastY = motionEvent.getY();
        checkTouchArea(motionEvent.getX(), motionEvent.getY());
    }

    private void onMove(MotionEvent motionEvent) {
        float x = motionEvent.getX() - this.mLastX;
        float y = motionEvent.getY() - this.mLastY;
        switch (this.mTouchArea) {
            case CENTER:
                moveFrame(x, y);
                break;
            case LEFT_TOP:
                moveHandleLT(x, y);
                break;
            case RIGHT_TOP:
                moveHandleRT(x, y);
                break;
            case LEFT_BOTTOM:
                moveHandleLB(x, y);
                break;
            case RIGHT_BOTTOM:
                moveHandleRB(x, y);
                break;
        }
        invalidate();
        this.mLastX = motionEvent.getX();
        this.mLastY = motionEvent.getY();
    }

    private void onUp(MotionEvent motionEvent) {
        if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
            this.mShowGuide = false;
        }
        if (this.mHandleShowMode == ShowMode.SHOW_ON_TOUCH) {
            this.mShowHandle = false;
        }
        this.mTouchArea = TouchArea.OUT_OF_BOUNDS;
        invalidate();
    }

    private void onCancel() {
        this.mTouchArea = TouchArea.OUT_OF_BOUNDS;
        invalidate();
    }

    private void checkTouchArea(float f, float f2) {
        if (isInsideCornerLeftTop(f, f2)) {
            this.mTouchArea = TouchArea.LEFT_TOP;
            if (this.mHandleShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowHandle = true;
            }
            if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowGuide = true;
            }
        } else if (isInsideCornerRightTop(f, f2)) {
            this.mTouchArea = TouchArea.RIGHT_TOP;
            if (this.mHandleShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowHandle = true;
            }
            if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowGuide = true;
            }
        } else if (isInsideCornerLeftBottom(f, f2)) {
            this.mTouchArea = TouchArea.LEFT_BOTTOM;
            if (this.mHandleShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowHandle = true;
            }
            if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowGuide = true;
            }
        } else if (isInsideCornerRightBottom(f, f2)) {
            this.mTouchArea = TouchArea.RIGHT_BOTTOM;
            if (this.mHandleShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowHandle = true;
            }
            if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowGuide = true;
            }
        } else if (isInsideFrame(f, f2)) {
            if (this.mGuideShowMode == ShowMode.SHOW_ON_TOUCH) {
                this.mShowGuide = true;
            }
            this.mTouchArea = TouchArea.CENTER;
        } else {
            this.mTouchArea = TouchArea.OUT_OF_BOUNDS;
        }
    }

    private boolean isInsideFrame(float f, float f2) {
        if (this.mFrameRect.left > f || this.mFrameRect.right < f || this.mFrameRect.top > f2 || this.mFrameRect.bottom < f2) {
            return false;
        }
        this.mTouchArea = TouchArea.CENTER;
        return true;
    }

    private boolean isInsideCornerLeftTop(float f, float f2) {
        float f3 = f - this.mFrameRect.left;
        float f4 = f2 - this.mFrameRect.top;
        return sq((float) (this.mHandleSize + this.mTouchPadding)) >= (f3 * f3) + (f4 * f4);
    }

    private boolean isInsideCornerRightTop(float f, float f2) {
        float f3 = f - this.mFrameRect.right;
        float f4 = f2 - this.mFrameRect.top;
        return sq((float) (this.mHandleSize + this.mTouchPadding)) >= (f3 * f3) + (f4 * f4);
    }

    private boolean isInsideCornerLeftBottom(float f, float f2) {
        float f3 = f - this.mFrameRect.left;
        float f4 = f2 - this.mFrameRect.bottom;
        return sq((float) (this.mHandleSize + this.mTouchPadding)) >= (f3 * f3) + (f4 * f4);
    }

    private boolean isInsideCornerRightBottom(float f, float f2) {
        float f3 = f - this.mFrameRect.right;
        float f4 = f2 - this.mFrameRect.bottom;
        return sq((float) (this.mHandleSize + this.mTouchPadding)) >= (f3 * f3) + (f4 * f4);
    }

    private void moveFrame(float f, float f2) {
        this.mFrameRect.left += f;
        this.mFrameRect.right += f;
        this.mFrameRect.top += f2;
        this.mFrameRect.bottom += f2;
        checkMoveBounds();
    }

    private void moveHandleLT(float f, float f2) {
        if (this.mCropMode == CropMode.RATIO_FREE) {
            this.mFrameRect.left += f;
            this.mFrameRect.top += f2;
            if (isWidthTooSmall()) {
                this.mFrameRect.left -= this.mMinFrameSize - getFrameW();
            }
            if (isHeightTooSmall()) {
                this.mFrameRect.top -= this.mMinFrameSize - getFrameH();
            }
            checkScaleBounds();
            return;
        }
        float ratioY = (getRatioY() * f) / getRatioX();
        this.mFrameRect.left += f;
        this.mFrameRect.top += ratioY;
        if (isWidthTooSmall()) {
            float frameW = this.mMinFrameSize - getFrameW();
            this.mFrameRect.left -= frameW;
            this.mFrameRect.top -= (frameW * getRatioY()) / getRatioX();
        }
        if (isHeightTooSmall()) {
            float frameH = this.mMinFrameSize - getFrameH();
            this.mFrameRect.top -= frameH;
            this.mFrameRect.left -= (frameH * getRatioX()) / getRatioY();
        }
        if (!isInsideHorizontal(this.mFrameRect.left)) {
            float f3 = this.mImageRect.left - this.mFrameRect.left;
            this.mFrameRect.left += f3;
            this.mFrameRect.top += (f3 * getRatioY()) / getRatioX();
        }
        if (!isInsideVertical(this.mFrameRect.top)) {
            float f4 = this.mImageRect.top - this.mFrameRect.top;
            this.mFrameRect.top += f4;
            this.mFrameRect.left += (f4 * getRatioX()) / getRatioY();
        }
    }

    private void moveHandleRT(float f, float f2) {
        if (this.mCropMode == CropMode.RATIO_FREE) {
            this.mFrameRect.right += f;
            this.mFrameRect.top += f2;
            if (isWidthTooSmall()) {
                this.mFrameRect.right += this.mMinFrameSize - getFrameW();
            }
            if (isHeightTooSmall()) {
                this.mFrameRect.top -= this.mMinFrameSize - getFrameH();
            }
            checkScaleBounds();
            return;
        }
        float ratioY = (getRatioY() * f) / getRatioX();
        this.mFrameRect.right += f;
        this.mFrameRect.top -= ratioY;
        if (isWidthTooSmall()) {
            float frameW = this.mMinFrameSize - getFrameW();
            this.mFrameRect.right += frameW;
            this.mFrameRect.top -= (frameW * getRatioY()) / getRatioX();
        }
        if (isHeightTooSmall()) {
            float frameH = this.mMinFrameSize - getFrameH();
            this.mFrameRect.top -= frameH;
            this.mFrameRect.right += (frameH * getRatioX()) / getRatioY();
        }
        if (!isInsideHorizontal(this.mFrameRect.right)) {
            float f3 = this.mFrameRect.right - this.mImageRect.right;
            this.mFrameRect.right -= f3;
            this.mFrameRect.top += (f3 * getRatioY()) / getRatioX();
        }
        if (!isInsideVertical(this.mFrameRect.top)) {
            float f4 = this.mImageRect.top - this.mFrameRect.top;
            this.mFrameRect.top += f4;
            this.mFrameRect.right -= (f4 * getRatioX()) / getRatioY();
        }
    }

    private void moveHandleLB(float f, float f2) {
        if (this.mCropMode == CropMode.RATIO_FREE) {
            this.mFrameRect.left += f;
            this.mFrameRect.bottom += f2;
            if (isWidthTooSmall()) {
                this.mFrameRect.left -= this.mMinFrameSize - getFrameW();
            }
            if (isHeightTooSmall()) {
                this.mFrameRect.bottom += this.mMinFrameSize - getFrameH();
            }
            checkScaleBounds();
            return;
        }
        float ratioY = (getRatioY() * f) / getRatioX();
        this.mFrameRect.left += f;
        this.mFrameRect.bottom -= ratioY;
        if (isWidthTooSmall()) {
            float frameW = this.mMinFrameSize - getFrameW();
            this.mFrameRect.left -= frameW;
            this.mFrameRect.bottom += (frameW * getRatioY()) / getRatioX();
        }
        if (isHeightTooSmall()) {
            float frameH = this.mMinFrameSize - getFrameH();
            this.mFrameRect.bottom += frameH;
            this.mFrameRect.left -= (frameH * getRatioX()) / getRatioY();
        }
        if (!isInsideHorizontal(this.mFrameRect.left)) {
            float f3 = this.mImageRect.left - this.mFrameRect.left;
            this.mFrameRect.left += f3;
            this.mFrameRect.bottom -= (f3 * getRatioY()) / getRatioX();
        }
        if (!isInsideVertical(this.mFrameRect.bottom)) {
            float f4 = this.mFrameRect.bottom - this.mImageRect.bottom;
            this.mFrameRect.bottom -= f4;
            this.mFrameRect.left += (f4 * getRatioX()) / getRatioY();
        }
    }

    private void moveHandleRB(float f, float f2) {
        if (this.mCropMode == CropMode.RATIO_FREE) {
            this.mFrameRect.right += f;
            this.mFrameRect.bottom += f2;
            if (isWidthTooSmall()) {
                this.mFrameRect.right += this.mMinFrameSize - getFrameW();
            }
            if (isHeightTooSmall()) {
                this.mFrameRect.bottom += this.mMinFrameSize - getFrameH();
            }
            checkScaleBounds();
            return;
        }
        float ratioY = (getRatioY() * f) / getRatioX();
        this.mFrameRect.right += f;
        this.mFrameRect.bottom += ratioY;
        if (isWidthTooSmall()) {
            float frameW = this.mMinFrameSize - getFrameW();
            this.mFrameRect.right += frameW;
            this.mFrameRect.bottom += (frameW * getRatioY()) / getRatioX();
        }
        if (isHeightTooSmall()) {
            float frameH = this.mMinFrameSize - getFrameH();
            this.mFrameRect.bottom += frameH;
            this.mFrameRect.right += (frameH * getRatioX()) / getRatioY();
        }
        if (!isInsideHorizontal(this.mFrameRect.right)) {
            float f3 = this.mFrameRect.right - this.mImageRect.right;
            this.mFrameRect.right -= f3;
            this.mFrameRect.bottom -= (f3 * getRatioY()) / getRatioX();
        }
        if (!isInsideVertical(this.mFrameRect.bottom)) {
            float f4 = this.mFrameRect.bottom - this.mImageRect.bottom;
            this.mFrameRect.bottom -= f4;
            this.mFrameRect.right -= (f4 * getRatioX()) / getRatioY();
        }
    }

    private void checkScaleBounds() {
        float f = this.mFrameRect.left - this.mImageRect.left;
        float f2 = this.mFrameRect.right - this.mImageRect.right;
        float f3 = this.mFrameRect.top - this.mImageRect.top;
        float f4 = this.mFrameRect.bottom - this.mImageRect.bottom;
        if (f < com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.left -= f;
        }
        if (f2 > com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.right -= f2;
        }
        if (f3 < com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.top -= f3;
        }
        if (f4 > com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.bottom -= f4;
        }
    }

    private void checkMoveBounds() {
        float f = this.mFrameRect.left - this.mImageRect.left;
        if (f < com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.left -= f;
            this.mFrameRect.right -= f;
        }
        float f2 = this.mFrameRect.right - this.mImageRect.right;
        if (f2 > com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.left -= f2;
            this.mFrameRect.right -= f2;
        }
        float f3 = this.mFrameRect.top - this.mImageRect.top;
        if (f3 < com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.top -= f3;
            this.mFrameRect.bottom -= f3;
        }
        float f4 = this.mFrameRect.bottom - this.mImageRect.bottom;
        if (f4 > com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mFrameRect.top -= f4;
            this.mFrameRect.bottom -= f4;
        }
    }

    private boolean isInsideHorizontal(float f) {
        return this.mImageRect.left <= f && this.mImageRect.right >= f;
    }

    private boolean isInsideVertical(float f) {
        return this.mImageRect.top <= f && this.mImageRect.bottom >= f;
    }

    private boolean isWidthTooSmall() {
        return getFrameW() < this.mMinFrameSize;
    }

    private boolean isHeightTooSmall() {
        return getFrameH() < this.mMinFrameSize;
    }

    private void adjustRatio() {
        if (this.mImageRect != null) {
            float f = this.mImageRect.right - this.mImageRect.left;
            float f2 = this.mImageRect.bottom - this.mImageRect.top;
            float f3 = f / f2;
            float ratioX = getRatioX(f) / getRatioY(f2);
            float f4 = this.mImageRect.left;
            float f5 = this.mImageRect.top;
            float f6 = this.mImageRect.right;
            float f7 = this.mImageRect.bottom;
            if (ratioX >= f3) {
                f4 = this.mImageRect.left;
                f6 = this.mImageRect.right;
                float f8 = (this.mImageRect.top + this.mImageRect.bottom) * 0.5f;
                float f9 = (f / ratioX) * 0.5f;
                f5 = f8 - f9;
                f7 = f8 + f9;
            } else if (ratioX < f3) {
                f5 = this.mImageRect.top;
                f7 = this.mImageRect.bottom;
                float f10 = (this.mImageRect.left + this.mImageRect.right) * 0.5f;
                float f11 = f2 * ratioX * 0.5f;
                f4 = f10 - f11;
                f6 = f10 + f11;
            }
            float f12 = f6 - f4;
            float f13 = f7 - f5;
            float f14 = f4 + (f12 / 2.0f);
            float f15 = f5 + (f13 / 2.0f);
            float f16 = (f12 * this.mInitialFrameScale) / 2.0f;
            float f17 = (f13 * this.mInitialFrameScale) / 2.0f;
            this.mFrameRect = new RectF(f14 - f16, f15 - f17, f14 + f16, f15 + f17);
            invalidate();
        }
    }

    private float getRatioX(float f) {
        switch (this.mCropMode) {
            case RATIO_FIT_IMAGE:
                return this.mImgWidth;
            case RATIO_FREE:
                return f;
            case RATIO_4_3:
                return 4.0f;
            case RATIO_3_4:
                return 3.0f;
            case RATIO_16_9:
                return 16.0f;
            case RATIO_9_16:
                return 9.0f;
            case RATIO_1_1:
            case CIRCLE:
                return 1.0f;
            case RATIO_CUSTOM:
                return this.mCustomRatio.x;
            default:
                return f;
        }
    }

    private float getRatioY(float f) {
        switch (this.mCropMode) {
            case RATIO_FIT_IMAGE:
                return this.mImgHeight;
            case RATIO_FREE:
                return f;
            case RATIO_4_3:
                return 3.0f;
            case RATIO_3_4:
                return 4.0f;
            case RATIO_16_9:
                return 9.0f;
            case RATIO_9_16:
                return 16.0f;
            case RATIO_1_1:
            case CIRCLE:
                return 1.0f;
            case RATIO_CUSTOM:
                return this.mCustomRatio.y;
            default:
                return f;
        }
    }

    private float getRatioX() {
        int i = AnonymousClass1.$SwitchMap$com$isseiaoki$simplecropview$CropImageView$CropMode[this.mCropMode.ordinal()];
        if (i == 1) {
            return this.mImgWidth;
        }
        switch (i) {
            case 3:
                return 4.0f;
            case 4:
                return 3.0f;
            case 5:
                return 16.0f;
            case 6:
                return 9.0f;
            case 7:
            case 8:
                return 1.0f;
            case 9:
                return this.mCustomRatio.x;
            default:
                return 1.0f;
        }
    }

    private float getRatioY() {
        int i = AnonymousClass1.$SwitchMap$com$isseiaoki$simplecropview$CropImageView$CropMode[this.mCropMode.ordinal()];
        if (i == 1) {
            return this.mImgHeight;
        }
        switch (i) {
            case 3:
                return 3.0f;
            case 4:
                return 4.0f;
            case 5:
                return 9.0f;
            case 6:
                return 16.0f;
            case 7:
            case 8:
                return 1.0f;
            case 9:
                return this.mCustomRatio.y;
            default:
                return 1.0f;
        }
    }

    private float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public Bitmap getImageBitmap() {
        return getBitmap();
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mIsInitialized = false;
        super.setImageBitmap(bitmap);
        updateDrawableInfo();
    }

    public void setImageResource(int i) {
        this.mIsInitialized = false;
        super.setImageResource(i);
        updateDrawableInfo();
    }

    public void setImageDrawable(Drawable drawable) {
        this.mIsInitialized = false;
        super.setImageDrawable(drawable);
        updateDrawableInfo();
    }

    public void setImageURI(Uri uri) {
        this.mIsInitialized = false;
        super.setImageURI(uri);
        updateDrawableInfo();
    }

    private void updateDrawableInfo() {
        if (getDrawable() != null) {
            initLayout(this.mViewWidth, this.mViewHeight);
        }
    }

    public void rotateImage(RotateDegrees rotateDegrees) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            int value = rotateDegrees.getValue();
            Matrix matrix = new Matrix();
            matrix.postRotate((float) value);
            setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
        }
    }

    public Bitmap getCroppedBitmap() {
        Bitmap bitmap = getBitmap();
        if (bitmap == null) {
            return null;
        }
        float f = this.mFrameRect.left / this.mScale;
        float f2 = this.mFrameRect.top / this.mScale;
        float f3 = this.mFrameRect.right / this.mScale;
        float f4 = this.mFrameRect.bottom / this.mScale;
        int round = Math.round(f - (this.mImageRect.left / this.mScale));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, round, Math.round(f2 - (this.mImageRect.top / this.mScale)), Math.round(f3 - f), Math.round(f4 - f2), (Matrix) null, false);
        if (this.mCropMode != CropMode.CIRCLE) {
            return createBitmap;
        }
        return getCircularBitmap(createBitmap);
    }

    public Bitmap getRectBitmap() {
        Bitmap bitmap = getBitmap();
        if (bitmap == null) {
            return null;
        }
        float f = this.mFrameRect.left / this.mScale;
        float f2 = this.mFrameRect.top / this.mScale;
        float f3 = this.mFrameRect.right / this.mScale;
        float f4 = this.mFrameRect.bottom / this.mScale;
        int round = Math.round(f - (this.mImageRect.left / this.mScale));
        return Bitmap.createBitmap(bitmap, round, Math.round(f2 - (this.mImageRect.top / this.mScale)), Math.round(f3 - f), Math.round(f4 - f2), (Matrix) null, false);
    }

    public Bitmap getCircularBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Canvas canvas = new Canvas(createBitmap);
        int width = bitmap.getWidth() / 2;
        int height = bitmap.getHeight() / 2;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    private Bitmap getBitmap() {
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public RectF getActualCropRect() {
        float f = this.mImageRect.left / this.mScale;
        float f2 = this.mImageRect.top / this.mScale;
        return new RectF((this.mFrameRect.left / this.mScale) - f, (this.mFrameRect.top / this.mScale) - f2, (this.mFrameRect.right / this.mScale) - f, (this.mFrameRect.bottom / this.mScale) - f2);
    }

    public void setCropMode(CropMode cropMode) {
        if (cropMode == CropMode.RATIO_CUSTOM) {
            setCustomRatio(1, 1);
            return;
        }
        this.mCropMode = cropMode;
        adjustRatio();
    }

    public void setCustomRatio(int i, int i2) {
        if (i != 0 && i2 != 0) {
            this.mCropMode = CropMode.RATIO_CUSTOM;
            this.mCustomRatio = new PointF((float) i, (float) i2);
            adjustRatio();
        }
    }

    public void setOverlayColor(int i) {
        this.mOverlayColor = i;
        invalidate();
    }

    public void setFrameColor(int i) {
        this.mFrameColor = i;
        invalidate();
    }

    public void setHandleColor(int i) {
        this.mHandleColor = i;
        invalidate();
    }

    public void setGuideColor(int i) {
        this.mGuideColor = i;
        invalidate();
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        super.setBackgroundColor(this.mBackgroundColor);
        invalidate();
    }

    public void setMinFrameSizeInDp(int i) {
        this.mMinFrameSize = ((float) i) * getDensity();
    }

    public void setMinFrameSizeInPx(int i) {
        this.mMinFrameSize = (float) i;
    }

    public void setHandleSizeInDp(int i) {
        this.mHandleSize = (int) (((float) i) * getDensity());
    }

    public void setTouchPaddingInDp(int i) {
        this.mTouchPadding = (int) (((float) i) * getDensity());
    }

    public void setGuideShowMode(ShowMode showMode) {
        this.mGuideShowMode = showMode;
        switch (showMode) {
            case SHOW_ALWAYS:
                this.mShowGuide = true;
                break;
            case NOT_SHOW:
            case SHOW_ON_TOUCH:
                this.mShowGuide = false;
                break;
        }
        invalidate();
    }

    public void setHandleShowMode(ShowMode showMode) {
        this.mHandleShowMode = showMode;
        switch (showMode) {
            case SHOW_ALWAYS:
                this.mShowHandle = true;
                break;
            case NOT_SHOW:
            case SHOW_ON_TOUCH:
                this.mShowHandle = false;
                break;
        }
        invalidate();
    }

    public void setFrameStrokeWeightInDp(int i) {
        this.mFrameStrokeWeight = ((float) i) * getDensity();
        invalidate();
    }

    public void setGuideStrokeWeightInDp(int i) {
        this.mGuideStrokeWeight = ((float) i) * getDensity();
        invalidate();
    }

    public void setCropEnabled(boolean z) {
        this.mIsCropEnabled = z;
        invalidate();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mIsEnabled = z;
    }

    public void setInitialFrameScale(float f) {
        this.mInitialFrameScale = constrain(f, 0.01f, 1.0f, DEFAULT_INITIAL_FRAME_SCALE);
    }

    private void setScale(float f) {
        this.mScale = f;
    }

    private void setCenter(PointF pointF) {
        this.mCenter = pointF;
    }

    private float getFrameW() {
        return this.mFrameRect.right - this.mFrameRect.left;
    }

    private float getFrameH() {
        return this.mFrameRect.bottom - this.mFrameRect.top;
    }

    public enum CropMode {
        RATIO_FIT_IMAGE(0),
        RATIO_4_3(1),
        RATIO_3_4(2),
        RATIO_1_1(3),
        RATIO_16_9(4),
        RATIO_9_16(5),
        RATIO_FREE(6),
        RATIO_CUSTOM(7),
        CIRCLE(8);
        
        private final int ID;

        private CropMode(int i) {
            this.ID = i;
        }

        public int getId() {
            return this.ID;
        }
    }

    public enum ShowMode {
        SHOW_ALWAYS(1),
        SHOW_ON_TOUCH(2),
        NOT_SHOW(3);
        
        private final int ID;

        private ShowMode(int i) {
            this.ID = i;
        }

        public int getId() {
            return this.ID;
        }
    }

    public enum RotateDegrees {
        ROTATE_90D(90),
        ROTATE_180D(SubsamplingScaleImageView.ORIENTATION_180),
        ROTATE_270D(SubsamplingScaleImageView.ORIENTATION_270);
        
        private final int VALUE;

        private RotateDegrees(int i) {
            this.VALUE = i;
        }

        public int getValue() {
            return this.VALUE;
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int backgroundColor;
        float customRatioX;
        float customRatioY;
        int frameColor;
        float frameStrokeWeight;
        int guideColor;
        ShowMode guideShowMode;
        float guideStrokeWeight;
        int handleColor;
        ShowMode handleShowMode;
        int handleSize;
        Bitmap image;
        float initialFrameScale;
        boolean isCropEnabled;
        float minFrameSize;
        CropMode mode;
        int overlayColor;
        boolean showGuide;
        boolean showHandle;
        int touchPadding;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.image = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
            this.mode = (CropMode) parcel.readSerializable();
            this.backgroundColor = parcel.readInt();
            this.overlayColor = parcel.readInt();
            this.frameColor = parcel.readInt();
            this.guideShowMode = (ShowMode) parcel.readSerializable();
            this.handleShowMode = (ShowMode) parcel.readSerializable();
            boolean z = false;
            this.showGuide = parcel.readInt() != 0;
            this.showHandle = parcel.readInt() != 0;
            this.handleSize = parcel.readInt();
            this.touchPadding = parcel.readInt();
            this.minFrameSize = parcel.readFloat();
            this.customRatioX = parcel.readFloat();
            this.customRatioY = parcel.readFloat();
            this.frameStrokeWeight = parcel.readFloat();
            this.guideStrokeWeight = parcel.readFloat();
            this.isCropEnabled = parcel.readInt() != 0 ? true : z;
            this.handleColor = parcel.readInt();
            this.guideColor = parcel.readInt();
            this.initialFrameScale = parcel.readFloat();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.image, i);
            parcel.writeSerializable(this.mode);
            parcel.writeInt(this.backgroundColor);
            parcel.writeInt(this.overlayColor);
            parcel.writeInt(this.frameColor);
            parcel.writeSerializable(this.guideShowMode);
            parcel.writeSerializable(this.handleShowMode);
            parcel.writeInt(this.showGuide ? 1 : 0);
            parcel.writeInt(this.showHandle ? 1 : 0);
            parcel.writeInt(this.handleSize);
            parcel.writeInt(this.touchPadding);
            parcel.writeFloat(this.minFrameSize);
            parcel.writeFloat(this.customRatioX);
            parcel.writeFloat(this.customRatioY);
            parcel.writeFloat(this.frameStrokeWeight);
            parcel.writeFloat(this.guideStrokeWeight);
            parcel.writeInt(this.isCropEnabled ? 1 : 0);
            parcel.writeInt(this.handleColor);
            parcel.writeInt(this.guideColor);
            parcel.writeFloat(this.initialFrameScale);
        }
    }
}
