package com.meiqia.meiqiasdk.third.photoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.meiqia.meiqiasdk.third.photoview.gestures.OnGestureListener;
import com.meiqia.meiqiasdk.third.photoview.gestures.VersionedGestureDetector;
import com.meiqia.meiqiasdk.third.photoview.log.LogManager;
import com.meiqia.meiqiasdk.third.photoview.log.Logger;
import com.meiqia.meiqiasdk.third.photoview.scrollerproxy.ScrollerProxy;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;

public class PhotoViewAttacher implements IPhotoView, View.OnTouchListener, OnGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    private static final String LOG_TAG = "PhotoViewAttacher";
    static int SINGLE_TOUCH = 1;
    int ZOOM_DURATION;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private float mBaseRotation;
    private boolean mBlockParentIntercept;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    /* access modifiers changed from: private */
    public Interpolator mInterpolator;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    /* access modifiers changed from: private */
    public View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangeListener mScaleChangeListener;
    private com.meiqia.meiqiasdk.third.photoview.gestures.GestureDetector mScaleDragDetector;
    private ImageView.ScaleType mScaleType;
    private int mScrollEdge;
    /* access modifiers changed from: private */
    public OnSingleFlingListener mSingleFlingListener;
    /* access modifiers changed from: private */
    public final Matrix mSuppMatrix;
    private OnViewTapListener mViewTapListener;
    private boolean mZoomEnabled;

    /* renamed from: com.meiqia.meiqiasdk.third.photoview.PhotoViewAttacher$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 1;
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
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
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
            return PhotoViewAttacher.this.mInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ((float) PhotoViewAttacher.this.ZOOM_DURATION)));
        }

        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                float interpolate = interpolate();
                PhotoViewAttacher.this.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * interpolate)) / PhotoViewAttacher.this.getScale(), this.mFocalX, this.mFocalY);
                if (interpolate < 1.0f) {
                    Compat.postOnAnimation(imageView, this);
                }
            }
        }
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        public void cancelFling() {
            if (PhotoViewAttacher.DEBUG) {
                LogManager.getLogger().d(PhotoViewAttacher.LOG_TAG, "Cancel Fling");
            }
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = PhotoViewAttacher.this.getDisplayRect();
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
                if (PhotoViewAttacher.DEBUG) {
                    LogManager.getLogger().d(PhotoViewAttacher.LOG_TAG, "fling. StartX:" + round + " StartY:" + round2 + " MaxX:" + i5 + " MaxY:" + i7);
                }
                if (round != i5 || round2 != i7) {
                    this.mScroller.fling(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            ImageView imageView;
            if (!this.mScroller.isFinished() && (imageView = PhotoViewAttacher.this.getImageView()) != null && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (PhotoViewAttacher.DEBUG) {
                    Logger logger = LogManager.getLogger();
                    logger.d(PhotoViewAttacher.LOG_TAG, "fling run(). CurrentX:" + this.mCurrentX + " CurrentY:" + this.mCurrentY + " NewX:" + currX + " NewY:" + currY);
                }
                PhotoViewAttacher.this.mSuppMatrix.postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
                PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.this.getDrawMatrix());
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    public interface OnPhotoTapListener {
        void onOutsidePhotoTap();

        void onPhotoTap(View view, float f, float f2);
    }

    public interface OnScaleChangeListener {
        void onScaleChange(float f, float f2, float f3);
    }

    public interface OnSingleFlingListener {
        boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);
    }

    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean z) {
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.ZOOM_DURATION = 200;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mScrollEdge = 2;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mImageView = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView);
        if (!imageView.isInEditMode()) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (PhotoViewAttacher.this.mSingleFlingListener == null || PhotoViewAttacher.this.getScale() > 1.0f || MotionEventCompat.getPointerCount(motionEvent) > PhotoViewAttacher.SINGLE_TOUCH || MotionEventCompat.getPointerCount(motionEvent2) > PhotoViewAttacher.SINGLE_TOUCH) {
                        return false;
                    }
                    return PhotoViewAttacher.this.mSingleFlingListener.onFling(motionEvent, motionEvent2, f, f2);
                }

                public void onLongPress(MotionEvent motionEvent) {
                    if (PhotoViewAttacher.this.mLongClickListener != null) {
                        PhotoViewAttacher.this.mLongClickListener.onLongClick(PhotoViewAttacher.this.getImageView());
                    }
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
            this.mBaseRotation = CropImageView.DEFAULT_ASPECT_RATIO;
            setZoomable(z);
        }
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher. You should call setScaleType on the PhotoViewAttacher instead of on the ImageView");
        }
    }

    private boolean checkMatrixBounds() {
        float f;
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        ImageView imageView = getImageView();
        if (imageView == null) {
            return false;
        }
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        float imageViewHeight = (float) getImageViewHeight(imageView);
        if (height <= imageViewHeight) {
            switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
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
        } else {
            f = displayRect.top > CropImageView.DEFAULT_ASPECT_RATIO ? -displayRect.top : displayRect.bottom < imageViewHeight ? imageViewHeight - displayRect.bottom : 0.0f;
        }
        float imageViewWidth = (float) getImageViewWidth(imageView);
        if (width <= imageViewWidth) {
            switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
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
            this.mScrollEdge = 2;
        } else if (displayRect.left > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mScrollEdge = 0;
            f2 = -displayRect.left;
        } else if (displayRect.right < imageViewWidth) {
            f2 = imageViewWidth - displayRect.right;
            this.mScrollEdge = 1;
        } else {
            this.mScrollEdge = -1;
        }
        this.mSuppMatrix.postTranslate(f2, f);
        return true;
    }

    private static void checkZoomLevels(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setRotationBy(this.mBaseRotation);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    /* access modifiers changed from: private */
    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            if (this.mMatrixChangeListener != null && (displayRect = getDisplayRect(matrix)) != null) {
                this.mMatrixChangeListener.onMatrixChanged(displayRect);
            }
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        if (this.mImageView != null) {
            ImageView imageView = (ImageView) this.mImageView.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener((View.OnTouchListener) null);
                cancelFling();
            }
            if (this.mGestureDetector != null) {
                this.mGestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
            }
            this.mMatrixChangeListener = null;
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mImageView = null;
        }
    }

    @Deprecated
    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    @Deprecated
    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    public IPhotoView getIPhotoViewImplementation() {
        return this;
    }

    public ImageView getImageView() {
        ImageView imageView = this.mImageView != null ? (ImageView) this.mImageView.get() : null;
        if (imageView == null) {
            cleanup();
            LogManager.getLogger().i(LOG_TAG, "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    /* access modifiers changed from: protected */
    public int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    /* access modifiers changed from: protected */
    public int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    public float getMaximumScale() {
        return this.mMaxScale;
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    @Deprecated
    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    @Deprecated
    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
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

    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return null;
        }
        return imageView.getDrawingCache();
    }

    public void onDrag(float f, float f2) {
        if (!this.mScaleDragDetector.isScaling()) {
            if (DEBUG) {
                LogManager.getLogger().d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
            }
            ImageView imageView = getImageView();
            this.mSuppMatrix.postTranslate(f, f2);
            checkAndDisplayMatrix();
            ViewParent parent = imageView.getParent();
            if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling() || this.mBlockParentIntercept) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.mScrollEdge == 2 || ((this.mScrollEdge == 0 && f >= 1.0f) || (this.mScrollEdge == 1 && f <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void onFling(float f, float f2, float f3, float f4) {
        if (DEBUG) {
            Logger logger = LogManager.getLogger();
            logger.d(LOG_TAG, "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4);
        }
        ImageView imageView = getImageView();
        this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) f3, (int) f4);
        imageView.post(this.mCurrentFlingRunnable);
    }

    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            int top2 = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top2 != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top2;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
                return;
            }
            return;
        }
        updateBaseMatrix(imageView.getDrawable());
    }

    public void onScale(float f, float f2, float f3) {
        if (DEBUG) {
            LogManager.getLogger().d(LOG_TAG, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
        }
        if (getScale() >= this.mMaxScale && f >= 1.0f) {
            return;
        }
        if (getScale() > this.mMinScale || f > 1.0f) {
            if (this.mScaleChangeListener != null) {
                this.mScaleChangeListener.onScaleChange(f, f2, f3);
            }
            this.mSuppMatrix.postScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        RectF displayRect;
        boolean z;
        boolean z2 = false;
        if (!this.mZoomEnabled || !hasDrawable((ImageView) view)) {
            return false;
        }
        ViewParent parent = view.getParent();
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    } else {
                        LogManager.getLogger().i(LOG_TAG, "onTouch getParent() returned null");
                    }
                    cancelFling();
                    break;
                case 1:
                    break;
            }
        }
        if (getScale() < this.mMinScale && (displayRect = getDisplayRect()) != null) {
            view.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, displayRect.centerX(), displayRect.centerY()));
            z = true;
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
        z = false;
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
        if (matrix != null) {
            ImageView imageView = getImageView();
            if (imageView == null || imageView.getDrawable() == null) {
                return false;
            }
            this.mSuppMatrix.set(matrix);
            setImageViewMatrix(getDrawMatrix());
            checkMatrixBounds();
            return true;
        }
        throw new IllegalArgumentException("Matrix cannot be null");
    }

    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    public void setMaximumScale(float f) {
        checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    public void setMediumScale(float f) {
        checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    public void setMinimumScale(float f) {
        checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.mMatrixChangeListener = onMatrixChangedListener;
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mScaleChangeListener = onScaleChangeListener;
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.mSingleFlingListener = onSingleFlingListener;
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mViewTapListener = onViewTapListener;
    }

    public void setPhotoViewRotation(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
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
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (f < this.mMinScale || f > this.mMaxScale) {
            LogManager.getLogger().i(LOG_TAG, "Scale must be within the range of minScale and maxScale");
        } else if (z) {
            imageView.post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
        } else {
            this.mSuppMatrix.setScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    public void setScale(float f, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f, (float) (imageView.getRight() / 2), (float) (imageView.getBottom() / 2), z);
        }
    }

    public void setScaleLevels(float f, float f2, float f3) {
        checkZoomLevels(f, f2, f3);
        this.mMinScale = f;
        this.mMidScale = f2;
        this.mMaxScale = f3;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    public void setZoomInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setZoomTransitionDuration(int i) {
        if (i < 0) {
            i = 200;
        }
        this.ZOOM_DURATION = i;
    }

    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    public void update() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            setImageViewScaleTypeMatrix(imageView);
            updateBaseMatrix(imageView.getDrawable());
            return;
        }
        resetMatrix();
    }

    /* access modifiers changed from: protected */
    public void updateBaseMatrix(Matrix matrix) {
        this.mBaseMatrix.reset();
        this.mBaseMatrix.set(matrix);
        resetMatrix();
    }

    /* access modifiers changed from: protected */
    public void updateBaseMatrix(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView != null && drawable != null) {
            float imageViewWidth = (float) getImageViewWidth(imageView);
            float imageViewHeight = (float) getImageViewHeight(imageView);
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
                        switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                            case 2:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                                break;
                            case 3:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                                break;
                            case 4:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                                break;
                            case 5:
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
}
