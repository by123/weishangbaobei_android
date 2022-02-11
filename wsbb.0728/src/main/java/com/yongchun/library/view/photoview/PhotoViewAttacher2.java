package com.yongchun.library.view.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.yalantis.ucrop.view.CropImageView;

public class PhotoViewAttacher2 implements View.OnTouchListener, View.OnLayoutChangeListener {
    private static float DEFAULT_MAX_SCALE = 3.0f;
    private static float DEFAULT_MID_SCALE = 1.75f;
    /* access modifiers changed from: private */
    public static float DEFAULT_MIN_SCALE = 1.0f;
    private static int DEFAULT_ZOOM_DURATION = 200;
    private static final int HORIZONTAL_EDGE_BOTH = 2;
    private static final int HORIZONTAL_EDGE_LEFT = 0;
    private static final int HORIZONTAL_EDGE_NONE = -1;
    private static final int HORIZONTAL_EDGE_RIGHT = 1;
    /* access modifiers changed from: private */
    public static int SINGLE_TOUCH = 1;
    private static final int VERTICAL_EDGE_BOTH = 2;
    private static final int VERTICAL_EDGE_BOTTOM = 1;
    private static final int VERTICAL_EDGE_NONE = -1;
    private static final int VERTICAL_EDGE_TOP = 0;
    /* access modifiers changed from: private */
    public boolean mAllowParentInterceptOnEdge = true;
    private final Matrix mBaseMatrix = new Matrix();
    private float mBaseRotation;
    /* access modifiers changed from: private */
    public boolean mBlockParentIntercept = false;
    /* access modifiers changed from: private */
    public FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect = new RectF();
    private final Matrix mDrawMatrix = new Matrix();
    private GestureDetector mGestureDetector;
    /* access modifiers changed from: private */
    public int mHorizontalScrollEdge = 2;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    /* access modifiers changed from: private */
    public Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues = new float[9];
    /* access modifiers changed from: private */
    public float mMaxScale = DEFAULT_MAX_SCALE;
    private float mMidScale = DEFAULT_MID_SCALE;
    private float mMinScale = DEFAULT_MIN_SCALE;
    /* access modifiers changed from: private */
    public View.OnClickListener mOnClickListener;
    /* access modifiers changed from: private */
    public OnViewDragListener mOnViewDragListener;
    /* access modifiers changed from: private */
    public OnOutsidePhotoTapListener mOutsidePhotoTapListener;
    /* access modifiers changed from: private */
    public OnPhotoTapListener mPhotoTapListener;
    /* access modifiers changed from: private */
    public OnScaleChangedListener2 mScaleChangeListener;
    /* access modifiers changed from: private */
    public CustomGestureDetector mScaleDragDetector;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    /* access modifiers changed from: private */
    public OnSingleFlingListener mSingleFlingListener;
    /* access modifiers changed from: private */
    public final Matrix mSuppMatrix = new Matrix();
    /* access modifiers changed from: private */
    public int mVerticalScrollEdge = 2;
    /* access modifiers changed from: private */
    public OnViewTapListener2 mViewTapListener;
    /* access modifiers changed from: private */
    public int mZoomDuration = DEFAULT_ZOOM_DURATION;
    private boolean mZoomEnabled = true;
    /* access modifiers changed from: private */
    public OnGestureListener onGestureListener = new OnGestureListener() {
        public void onDrag(float f, float f2) {
            if (!PhotoViewAttacher2.this.mScaleDragDetector.isScaling()) {
                if (PhotoViewAttacher2.this.mOnViewDragListener != null) {
                    PhotoViewAttacher2.this.mOnViewDragListener.onDrag(f, f2);
                }
                PhotoViewAttacher2.this.mSuppMatrix.postTranslate(f, f2);
                PhotoViewAttacher2.this.checkAndDisplayMatrix();
                ViewParent parent = PhotoViewAttacher2.this.mImageView.getParent();
                if (!PhotoViewAttacher2.this.mAllowParentInterceptOnEdge || PhotoViewAttacher2.this.mScaleDragDetector.isScaling() || PhotoViewAttacher2.this.mBlockParentIntercept) {
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                } else if ((PhotoViewAttacher2.this.mHorizontalScrollEdge == 2 || ((PhotoViewAttacher2.this.mHorizontalScrollEdge == 0 && f >= 1.0f) || ((PhotoViewAttacher2.this.mHorizontalScrollEdge == 1 && f <= -1.0f) || ((PhotoViewAttacher2.this.mVerticalScrollEdge == 0 && f2 >= 1.0f) || (PhotoViewAttacher2.this.mVerticalScrollEdge == 1 && f2 <= -1.0f))))) && parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
            }
        }

        public void onFling(float f, float f2, float f3, float f4) {
            FlingRunnable unused = PhotoViewAttacher2.this.mCurrentFlingRunnable = new FlingRunnable(PhotoViewAttacher2.this.mImageView.getContext());
            PhotoViewAttacher2.this.mCurrentFlingRunnable.fling(PhotoViewAttacher2.this.getImageViewWidth(PhotoViewAttacher2.this.mImageView), PhotoViewAttacher2.this.getImageViewHeight(PhotoViewAttacher2.this.mImageView), (int) f3, (int) f4);
            PhotoViewAttacher2.this.mImageView.post(PhotoViewAttacher2.this.mCurrentFlingRunnable);
        }

        public void onScale(float f, float f2, float f3) {
            if (PhotoViewAttacher2.this.getScale() < PhotoViewAttacher2.this.mMaxScale || f < 1.0f) {
                if (PhotoViewAttacher2.this.mScaleChangeListener != null) {
                    PhotoViewAttacher2.this.mScaleChangeListener.onScaleChange(f, f2, f3);
                }
                PhotoViewAttacher2.this.mSuppMatrix.postScale(f, f, f2, f3);
                PhotoViewAttacher2.this.checkAndDisplayMatrix();
            }
        }
    };

    /* renamed from: com.yongchun.library.view.photoview.PhotoViewAttacher2$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(float f, float f2, float f3, float f4) {
            this.mFocalX = f3;
            this.mFocalY = f4;
            this.mZoomStart = f;
            this.mZoomEnd = f2;
        }

        private float interpolate() {
            return PhotoViewAttacher2.this.mInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ((float) PhotoViewAttacher2.this.mZoomDuration)));
        }

        public void run() {
            float interpolate = interpolate();
            PhotoViewAttacher2.this.onGestureListener.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * interpolate)) / PhotoViewAttacher2.this.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation(PhotoViewAttacher2.this.mImageView, this);
            }
        }
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final OverScroller mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = new OverScroller(context);
        }

        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = PhotoViewAttacher2.this.getDisplayRect();
            if (displayRect != null) {
                int round = Math.round(-displayRect.left);
                float f = (float) i;
                if (f < displayRect.width()) {
                    i5 = Math.round(displayRect.width() - f);
                    i6 = 0;
                } else {
                    i5 = round;
                    i6 = round;
                }
                int round2 = Math.round(-displayRect.top);
                float f2 = (float) i2;
                if (f2 < displayRect.height()) {
                    i7 = Math.round(displayRect.height() - f2);
                    i8 = 0;
                } else {
                    i7 = round2;
                    i8 = round2;
                }
                this.mCurrentX = round;
                this.mCurrentY = round2;
                if (round != i5 || round2 != i7) {
                    this.mScroller.fling(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                PhotoViewAttacher2.this.mSuppMatrix.postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
                PhotoViewAttacher2.this.checkAndDisplayMatrix();
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation(PhotoViewAttacher2.this.mImageView, this);
            }
        }
    }

    public PhotoViewAttacher2(ImageView imageView) {
        this.mImageView = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (!imageView.isInEditMode()) {
            this.mBaseRotation = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mScaleDragDetector = new CustomGestureDetector(imageView.getContext(), this.onGestureListener);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (PhotoViewAttacher2.this.mSingleFlingListener == null || PhotoViewAttacher2.this.getScale() > PhotoViewAttacher2.DEFAULT_MIN_SCALE || motionEvent.getPointerCount() > PhotoViewAttacher2.SINGLE_TOUCH || motionEvent2.getPointerCount() > PhotoViewAttacher2.SINGLE_TOUCH) {
                        return false;
                    }
                    return PhotoViewAttacher2.this.mSingleFlingListener.onFling(motionEvent, motionEvent2, f, f2);
                }

                public void onLongPress(MotionEvent motionEvent) {
                    if (PhotoViewAttacher2.this.mLongClickListener != null) {
                        PhotoViewAttacher2.this.mLongClickListener.onLongClick(PhotoViewAttacher2.this.mImageView);
                    }
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    try {
                        float scale = PhotoViewAttacher2.this.getScale();
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (scale < PhotoViewAttacher2.this.getMediumScale()) {
                            PhotoViewAttacher2.this.setScale(PhotoViewAttacher2.this.getMediumScale(), x, y, true);
                        } else if (scale < PhotoViewAttacher2.this.getMediumScale() || scale >= PhotoViewAttacher2.this.getMaximumScale()) {
                            PhotoViewAttacher2.this.setScale(PhotoViewAttacher2.this.getMinimumScale(), x, y, true);
                        } else {
                            PhotoViewAttacher2.this.setScale(PhotoViewAttacher2.this.getMaximumScale(), x, y, true);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    return true;
                }

                public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                    return false;
                }

                public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                    if (PhotoViewAttacher2.this.mOnClickListener != null) {
                        PhotoViewAttacher2.this.mOnClickListener.onClick(PhotoViewAttacher2.this.mImageView);
                    }
                    RectF displayRect = PhotoViewAttacher2.this.getDisplayRect();
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (PhotoViewAttacher2.this.mViewTapListener != null) {
                        PhotoViewAttacher2.this.mViewTapListener.onViewTap(PhotoViewAttacher2.this.mImageView, x, y);
                    }
                    if (displayRect != null) {
                        if (displayRect.contains(x, y)) {
                            float width = (x - displayRect.left) / displayRect.width();
                            float height = (y - displayRect.top) / displayRect.height();
                            if (PhotoViewAttacher2.this.mPhotoTapListener != null) {
                                PhotoViewAttacher2.this.mPhotoTapListener.onPhotoTap(PhotoViewAttacher2.this.mImageView, width, height);
                            }
                            return true;
                        } else if (PhotoViewAttacher2.this.mOutsidePhotoTapListener != null) {
                            PhotoViewAttacher2.this.mOutsidePhotoTapListener.onOutsidePhotoTap(PhotoViewAttacher2.this.mImageView);
                        }
                    }
                    return false;
                }
            });
        }
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    /* access modifiers changed from: private */
    public void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private boolean checkMatrixBounds() {
        float f;
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        float imageViewHeight = (float) getImageViewHeight(this.mImageView);
        if (height <= imageViewHeight) {
            switch (AnonymousClass4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    f = -displayRect.top;
                    break;
                case 3:
                    f = (imageViewHeight - height) - displayRect.top;
                    break;
                default:
                    f = ((imageViewHeight - height) / 2.0f) - displayRect.top;
                    break;
            }
            this.mVerticalScrollEdge = 2;
        } else if (displayRect.top > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mVerticalScrollEdge = 0;
            f = -displayRect.top;
        } else if (displayRect.bottom < imageViewHeight) {
            this.mVerticalScrollEdge = 1;
            f = imageViewHeight - displayRect.bottom;
        } else {
            this.mVerticalScrollEdge = -1;
            f = 0.0f;
        }
        float imageViewWidth = (float) getImageViewWidth(this.mImageView);
        if (width <= imageViewWidth) {
            switch (AnonymousClass4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    f2 = -displayRect.left;
                    break;
                case 3:
                    f2 = (imageViewWidth - width) - displayRect.left;
                    break;
                default:
                    f2 = ((imageViewWidth - width) / 2.0f) - displayRect.left;
                    break;
            }
            this.mHorizontalScrollEdge = 2;
        } else if (displayRect.left > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mHorizontalScrollEdge = 0;
            f2 = -displayRect.left;
        } else if (displayRect.right < imageViewWidth) {
            f2 = imageViewWidth - displayRect.right;
            this.mHorizontalScrollEdge = 1;
        } else {
            this.mHorizontalScrollEdge = -1;
        }
        this.mSuppMatrix.postTranslate(f2, f);
        return true;
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable drawable = this.mImageView.getDrawable();
        if (drawable == null) {
            return null;
        }
        this.mDisplayRect.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    private Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    /* access modifiers changed from: private */
    public int getImageViewHeight(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    /* access modifiers changed from: private */
    public int getImageViewWidth(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setRotationBy(this.mBaseRotation);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    private void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        this.mImageView.setImageMatrix(matrix);
        if (this.mMatrixChangeListener != null && (displayRect = getDisplayRect(matrix)) != null) {
            this.mMatrixChangeListener.onMatrixChanged(displayRect);
        }
    }

    private void updateBaseMatrix(Drawable drawable) {
        if (drawable != null) {
            float imageViewWidth = (float) getImageViewWidth(this.mImageView);
            float imageViewHeight = (float) getImageViewHeight(this.mImageView);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.mBaseMatrix.reset();
            float f = (float) intrinsicWidth;
            float f2 = imageViewWidth / f;
            float f3 = (float) intrinsicHeight;
            float f4 = imageViewHeight / f3;
            if (this.mScaleType != ImageView.ScaleType.CENTER) {
                if (this.mScaleType != ImageView.ScaleType.CENTER_CROP) {
                    if (this.mScaleType != ImageView.ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f3);
                        RectF rectF2 = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, imageViewWidth, imageViewHeight);
                        if (((int) this.mBaseRotation) % SubsamplingScaleImageView.ORIENTATION_180 != 0) {
                            rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f3, f);
                        }
                        switch (AnonymousClass4.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                            case 1:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                                break;
                            case 2:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                                break;
                            case 3:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                                break;
                            case 4:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                                break;
                        }
                    } else {
                        float min = Math.min(1.0f, Math.min(f2, f4));
                        this.mBaseMatrix.postScale(min, min);
                        this.mBaseMatrix.postTranslate((imageViewWidth - (f * min)) / 2.0f, (imageViewHeight - (min * f3)) / 2.0f);
                    }
                } else {
                    float max = Math.max(f2, f4);
                    this.mBaseMatrix.postScale(max, max);
                    this.mBaseMatrix.postTranslate((imageViewWidth - (f * max)) / 2.0f, (imageViewHeight - (max * f3)) / 2.0f);
                }
            } else {
                this.mBaseMatrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
            }
            resetMatrix();
        }
    }

    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    public Matrix getImageMatrix() {
        return this.mDrawMatrix;
    }

    public float getMaximumScale() {
        return this.mMaxScale;
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(this.mSuppMatrix, 3), 2.0d))));
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void getSuppMatrix(Matrix matrix) {
        matrix.set(this.mSuppMatrix);
    }

    @Deprecated
    public boolean isZoomEnabled() {
        return this.mZoomEnabled;
    }

    public boolean isZoomable() {
        return this.mZoomEnabled;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
            updateBaseMatrix(this.mImageView.getDrawable());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        RectF displayRect;
        boolean z;
        boolean z2 = false;
        if (!this.mZoomEnabled || !Util.hasDrawable((ImageView) view)) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                    cancelFling();
                    break;
                case 1:
                    break;
            }
        }
        if (getScale() < this.mMinScale) {
            RectF displayRect2 = getDisplayRect();
            if (displayRect2 != null) {
                view.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, displayRect2.centerX(), displayRect2.centerY()));
            }
            z = false;
            if (this.mScaleDragDetector != null) {
                boolean isScaling = this.mScaleDragDetector.isScaling();
                boolean isDragging = this.mScaleDragDetector.isDragging();
                z = this.mScaleDragDetector.onTouchEvent(motionEvent);
                boolean z3 = !isScaling && !this.mScaleDragDetector.isScaling();
                boolean z4 = !isDragging && !this.mScaleDragDetector.isDragging();
                if (z3 && z4) {
                    z2 = true;
                }
                this.mBlockParentIntercept = z2;
            }
            if (this.mGestureDetector == null || !this.mGestureDetector.onTouchEvent(motionEvent)) {
                return z;
            }
            return true;
        }
        if (getScale() > this.mMaxScale && (displayRect = getDisplayRect()) != null) {
            view.post(new AnimatedZoomRunnable(getScale(), this.mMaxScale, displayRect.centerX(), displayRect.centerY()));
        }
        z = false;
        if (this.mScaleDragDetector != null) {
        }
        if (this.mGestureDetector == null || !this.mGestureDetector.onTouchEvent(motionEvent)) {
        }
        z = true;
        if (this.mScaleDragDetector != null) {
        }
        if (this.mGestureDetector == null || !this.mGestureDetector.onTouchEvent(motionEvent)) {
        }
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    public void setBaseRotation(float f) {
        this.mBaseRotation = f % 360.0f;
        update();
        setRotationBy(this.mBaseRotation);
        checkAndDisplayMatrix();
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        } else if (this.mImageView.getDrawable() == null) {
            return false;
        } else {
            this.mSuppMatrix.set(matrix);
            checkAndDisplayMatrix();
            return true;
        }
    }

    public void setMaximumScale(float f) {
        Util.checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    public void setMediumScale(float f) {
        Util.checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    public void setMinimumScale(float f) {
        Util.checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.mMatrixChangeListener = onMatrixChangedListener;
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener onOutsidePhotoTapListener) {
        this.mOutsidePhotoTapListener = onOutsidePhotoTapListener;
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    public void setOnScaleChangeListener(OnScaleChangedListener2 onScaleChangedListener2) {
        this.mScaleChangeListener = onScaleChangedListener2;
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.mSingleFlingListener = onSingleFlingListener;
    }

    public void setOnViewDragListener(OnViewDragListener onViewDragListener) {
        this.mOnViewDragListener = onViewDragListener;
    }

    public void setOnViewTapListener(OnViewTapListener2 onViewTapListener2) {
        this.mViewTapListener = onViewTapListener2;
    }

    public void setRotationBy(float f) {
        this.mSuppMatrix.postRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationTo(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setScale(float f) {
        setScale(f, false);
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        if (f < this.mMinScale || f > this.mMaxScale) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        } else if (z) {
            this.mImageView.post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
        } else {
            this.mSuppMatrix.setScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    public void setScale(float f, boolean z) {
        setScale(f, (float) (this.mImageView.getRight() / 2), (float) (this.mImageView.getBottom() / 2), z);
    }

    public void setScaleLevels(float f, float f2, float f3) {
        Util.checkZoomLevels(f, f2, f3);
        this.mMinScale = f;
        this.mMidScale = f2;
        this.mMaxScale = f3;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (Util.isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    public void setZoomInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setZoomTransitionDuration(int i) {
        this.mZoomDuration = i;
    }

    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    public void update() {
        if (this.mZoomEnabled) {
            updateBaseMatrix(this.mImageView.getDrawable());
        } else {
            resetMatrix();
        }
    }
}
