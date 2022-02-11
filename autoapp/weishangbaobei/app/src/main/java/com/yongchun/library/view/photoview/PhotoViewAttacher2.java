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

    public PhotoViewAttacher2(ImageView imageView) {
        this.mImageView = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (!imageView.isInEditMode()) {
            this.mBaseRotation = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mScaleDragDetector = new CustomGestureDetector(imageView.getContext(), this.onGestureListener);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                public void onLongPress(MotionEvent motionEvent) {
                    if (PhotoViewAttacher2.this.mLongClickListener != null) {
                        PhotoViewAttacher2.this.mLongClickListener.onLongClick(PhotoViewAttacher2.this.mImageView);
                    }
                }

                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (PhotoViewAttacher2.this.mSingleFlingListener == null || PhotoViewAttacher2.this.getScale() > PhotoViewAttacher2.DEFAULT_MIN_SCALE || motionEvent.getPointerCount() > PhotoViewAttacher2.SINGLE_TOUCH || motionEvent2.getPointerCount() > PhotoViewAttacher2.SINGLE_TOUCH) {
                        return false;
                    }
                    return PhotoViewAttacher2.this.mSingleFlingListener.onFling(motionEvent, motionEvent2, f, f2);
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
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
                    if (displayRect == null) {
                        return false;
                    }
                    if (displayRect.contains(x, y)) {
                        float width = (x - displayRect.left) / displayRect.width();
                        float height = (y - displayRect.top) / displayRect.height();
                        if (PhotoViewAttacher2.this.mPhotoTapListener == null) {
                            return true;
                        }
                        PhotoViewAttacher2.this.mPhotoTapListener.onPhotoTap(PhotoViewAttacher2.this.mImageView, width, height);
                        return true;
                    } else if (PhotoViewAttacher2.this.mOutsidePhotoTapListener == null) {
                        return false;
                    } else {
                        PhotoViewAttacher2.this.mOutsidePhotoTapListener.onOutsidePhotoTap(PhotoViewAttacher2.this.mImageView);
                        return false;
                    }
                }

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
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                    return true;
                }
            });
        }
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener2 onScaleChangedListener2) {
        this.mScaleChangeListener = onScaleChangedListener2;
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.mSingleFlingListener = onSingleFlingListener;
    }

    @Deprecated
    public boolean isZoomEnabled() {
        return this.mZoomEnabled;
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
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

    public void setBaseRotation(float f) {
        this.mBaseRotation = f % 360.0f;
        update();
        setRotationBy(this.mBaseRotation);
        checkAndDisplayMatrix();
    }

    public void setRotationTo(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationBy(float f) {
        this.mSuppMatrix.postRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    public float getMaximumScale() {
        return this.mMaxScale;
    }

    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(this.mSuppMatrix, 3), 2.0d))));
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
            updateBaseMatrix(this.mImageView.getDrawable());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c1 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            boolean r0 = r10.mZoomEnabled
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00c2
            r0 = r11
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = com.yongchun.library.view.photoview.Util.hasDrawable(r0)
            if (r0 == 0) goto L_0x00c2
            int r0 = r12.getAction()
            r3 = 3
            if (r0 == r3) goto L_0x0027
            switch(r0) {
                case 0: goto L_0x001a;
                case 1: goto L_0x0027;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x007a
        L_0x001a:
            android.view.ViewParent r11 = r11.getParent()
            if (r11 == 0) goto L_0x0023
            r11.requestDisallowInterceptTouchEvent(r2)
        L_0x0023:
            r10.cancelFling()
            goto L_0x007a
        L_0x0027:
            float r0 = r10.getScale()
            float r3 = r10.mMinScale
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0050
            android.graphics.RectF r0 = r10.getDisplayRect()
            if (r0 == 0) goto L_0x007a
            com.yongchun.library.view.photoview.PhotoViewAttacher2$AnimatedZoomRunnable r9 = new com.yongchun.library.view.photoview.PhotoViewAttacher2$AnimatedZoomRunnable
            float r5 = r10.getScale()
            float r6 = r10.mMinScale
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
            r11.post(r9)
            goto L_0x0078
        L_0x0050:
            float r0 = r10.getScale()
            float r3 = r10.mMaxScale
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x007a
            android.graphics.RectF r0 = r10.getDisplayRect()
            if (r0 == 0) goto L_0x007a
            com.yongchun.library.view.photoview.PhotoViewAttacher2$AnimatedZoomRunnable r9 = new com.yongchun.library.view.photoview.PhotoViewAttacher2$AnimatedZoomRunnable
            float r5 = r10.getScale()
            float r6 = r10.mMaxScale
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
            r11.post(r9)
        L_0x0078:
            r11 = 1
            goto L_0x007b
        L_0x007a:
            r11 = 0
        L_0x007b:
            com.yongchun.library.view.photoview.CustomGestureDetector r0 = r10.mScaleDragDetector
            if (r0 == 0) goto L_0x00b4
            com.yongchun.library.view.photoview.CustomGestureDetector r11 = r10.mScaleDragDetector
            boolean r11 = r11.isScaling()
            com.yongchun.library.view.photoview.CustomGestureDetector r0 = r10.mScaleDragDetector
            boolean r0 = r0.isDragging()
            com.yongchun.library.view.photoview.CustomGestureDetector r3 = r10.mScaleDragDetector
            boolean r3 = r3.onTouchEvent(r12)
            if (r11 != 0) goto L_0x009d
            com.yongchun.library.view.photoview.CustomGestureDetector r11 = r10.mScaleDragDetector
            boolean r11 = r11.isScaling()
            if (r11 != 0) goto L_0x009d
            r11 = 1
            goto L_0x009e
        L_0x009d:
            r11 = 0
        L_0x009e:
            if (r0 != 0) goto L_0x00aa
            com.yongchun.library.view.photoview.CustomGestureDetector r0 = r10.mScaleDragDetector
            boolean r0 = r0.isDragging()
            if (r0 != 0) goto L_0x00aa
            r0 = 1
            goto L_0x00ab
        L_0x00aa:
            r0 = 0
        L_0x00ab:
            if (r11 == 0) goto L_0x00b0
            if (r0 == 0) goto L_0x00b0
            r1 = 1
        L_0x00b0:
            r10.mBlockParentIntercept = r1
            r1 = r3
            goto L_0x00b5
        L_0x00b4:
            r1 = r11
        L_0x00b5:
            android.view.GestureDetector r11 = r10.mGestureDetector
            if (r11 == 0) goto L_0x00c2
            android.view.GestureDetector r11 = r10.mGestureDetector
            boolean r11 = r11.onTouchEvent(r12)
            if (r11 == 0) goto L_0x00c2
            r1 = 1
        L_0x00c2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.view.photoview.PhotoViewAttacher2.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    public void setMinimumScale(float f) {
        Util.checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
    }

    public void setMediumScale(float f) {
        Util.checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    public void setMaximumScale(float f) {
        Util.checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    public void setScaleLevels(float f, float f2, float f3) {
        Util.checkZoomLevels(f, f2, f3);
        this.mMinScale = f;
        this.mMidScale = f2;
        this.mMaxScale = f3;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.mMatrixChangeListener = onMatrixChangedListener;
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener onOutsidePhotoTapListener) {
        this.mOutsidePhotoTapListener = onOutsidePhotoTapListener;
    }

    public void setOnViewTapListener(OnViewTapListener2 onViewTapListener2) {
        this.mViewTapListener = onViewTapListener2;
    }

    public void setOnViewDragListener(OnViewDragListener onViewDragListener) {
        this.mOnViewDragListener = onViewDragListener;
    }

    public void setScale(float f) {
        setScale(f, false);
    }

    public void setScale(float f, boolean z) {
        setScale(f, (float) (this.mImageView.getRight() / 2), (float) (this.mImageView.getBottom() / 2), z);
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

    public void setZoomInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (Util.isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    public boolean isZoomable() {
        return this.mZoomEnabled;
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

    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    public void getSuppMatrix(Matrix matrix) {
        matrix.set(this.mSuppMatrix);
    }

    private Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    public Matrix getImageMatrix() {
        return this.mDrawMatrix;
    }

    public void setZoomTransitionDuration(int i) {
        this.mZoomDuration = i;
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

    /* access modifiers changed from: private */
    public void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
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
                        this.mBaseMatrix.postTranslate((imageViewWidth - (f * min)) / 2.0f, (imageViewHeight - (f3 * min)) / 2.0f);
                    }
                } else {
                    float max = Math.max(f2, f4);
                    this.mBaseMatrix.postScale(max, max);
                    this.mBaseMatrix.postTranslate((imageViewWidth - (f * max)) / 2.0f, (imageViewHeight - (f3 * max)) / 2.0f);
                }
            } else {
                this.mBaseMatrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
            }
            resetMatrix();
        }
    }

    /* renamed from: com.yongchun.library.view.photoview.PhotoViewAttacher2$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$widget$ImageView$ScaleType = r0
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.view.photoview.PhotoViewAttacher2.AnonymousClass4.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007e, code lost:
        r9 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0089, code lost:
        r12.mHorizontalScrollEdge = 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkMatrixBounds() {
        /*
            r12 = this;
            android.graphics.Matrix r0 = r12.getDrawMatrix()
            android.graphics.RectF r0 = r12.getDisplayRect(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            float r2 = r0.height()
            float r3 = r0.width()
            android.widget.ImageView r4 = r12.mImageView
            int r4 = r12.getImageViewHeight(r4)
            float r4 = (float) r4
            r5 = -1
            r6 = 2
            r7 = 1073741824(0x40000000, float:2.0)
            r8 = 1
            r9 = 0
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x0045
            int[] r10 = com.yongchun.library.view.photoview.PhotoViewAttacher2.AnonymousClass4.$SwitchMap$android$widget$ImageView$ScaleType
            android.widget.ImageView$ScaleType r11 = r12.mScaleType
            int r11 = r11.ordinal()
            r10 = r10[r11]
            switch(r10) {
                case 2: goto L_0x003d;
                case 3: goto L_0x0038;
                default: goto L_0x0032;
            }
        L_0x0032:
            float r4 = r4 - r2
            float r4 = r4 / r7
            float r2 = r0.top
            float r4 = r4 - r2
            goto L_0x0041
        L_0x0038:
            float r4 = r4 - r2
            float r2 = r0.top
            float r4 = r4 - r2
            goto L_0x0041
        L_0x003d:
            float r2 = r0.top
            float r2 = -r2
            r4 = r2
        L_0x0041:
            r12.mVerticalScrollEdge = r6
            r2 = r4
            goto L_0x0061
        L_0x0045:
            float r2 = r0.top
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x0051
            r12.mVerticalScrollEdge = r1
            float r2 = r0.top
            float r2 = -r2
            goto L_0x0061
        L_0x0051:
            float r2 = r0.bottom
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x005e
            r12.mVerticalScrollEdge = r8
            float r2 = r0.bottom
            float r2 = r4 - r2
            goto L_0x0061
        L_0x005e:
            r12.mVerticalScrollEdge = r5
            r2 = 0
        L_0x0061:
            android.widget.ImageView r4 = r12.mImageView
            int r4 = r12.getImageViewWidth(r4)
            float r4 = (float) r4
            int r10 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x008c
            int[] r1 = com.yongchun.library.view.photoview.PhotoViewAttacher2.AnonymousClass4.$SwitchMap$android$widget$ImageView$ScaleType
            android.widget.ImageView$ScaleType r5 = r12.mScaleType
            int r5 = r5.ordinal()
            r1 = r1[r5]
            switch(r1) {
                case 2: goto L_0x0085;
                case 3: goto L_0x0080;
                default: goto L_0x0079;
            }
        L_0x0079:
            float r4 = r4 - r3
            float r4 = r4 / r7
            float r0 = r0.left
            float r4 = r4 - r0
        L_0x007e:
            r9 = r4
            goto L_0x0089
        L_0x0080:
            float r4 = r4 - r3
            float r0 = r0.left
            float r4 = r4 - r0
            goto L_0x007e
        L_0x0085:
            float r0 = r0.left
            float r0 = -r0
            r9 = r0
        L_0x0089:
            r12.mHorizontalScrollEdge = r6
            goto L_0x00a7
        L_0x008c:
            float r3 = r0.left
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0098
            r12.mHorizontalScrollEdge = r1
            float r0 = r0.left
            float r9 = -r0
            goto L_0x00a7
        L_0x0098:
            float r1 = r0.right
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x00a5
            float r0 = r0.right
            float r9 = r4 - r0
            r12.mHorizontalScrollEdge = r8
            goto L_0x00a7
        L_0x00a5:
            r12.mHorizontalScrollEdge = r5
        L_0x00a7:
            android.graphics.Matrix r0 = r12.mSuppMatrix
            r0.postTranslate(r9, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.view.photoview.PhotoViewAttacher2.checkMatrixBounds():boolean");
    }

    /* access modifiers changed from: private */
    public int getImageViewWidth(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    /* access modifiers changed from: private */
    public int getImageViewHeight(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
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

        public void run() {
            float interpolate = interpolate();
            PhotoViewAttacher2.this.onGestureListener.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * interpolate)) / PhotoViewAttacher2.this.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation(PhotoViewAttacher2.this.mImageView, this);
            }
        }

        private float interpolate() {
            return PhotoViewAttacher2.this.mInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ((float) PhotoViewAttacher2.this.mZoomDuration)));
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
                    i6 = round;
                    i5 = i6;
                }
                int round2 = Math.round(-displayRect.top);
                float f2 = (float) i2;
                if (f2 < displayRect.height()) {
                    i7 = Math.round(displayRect.height() - f2);
                    i8 = 0;
                } else {
                    i8 = round2;
                    i7 = i8;
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
}
