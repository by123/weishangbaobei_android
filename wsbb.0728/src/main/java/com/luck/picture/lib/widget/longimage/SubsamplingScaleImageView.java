package com.luck.picture.lib.widget.longimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.luck.picture.lib.R;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

public class SubsamplingScaleImageView extends View {
    public static final int EASE_IN_OUT_QUAD = 2;
    public static final int EASE_OUT_QUAD = 1;
    private static final int MESSAGE_LONG_CLICK = 1;
    public static final int ORIENTATION_0 = 0;
    public static final int ORIENTATION_180 = 180;
    public static final int ORIENTATION_270 = 270;
    public static final int ORIENTATION_90 = 90;
    public static final int ORIENTATION_USE_EXIF = -1;
    public static final int ORIGIN_ANIM = 1;
    public static final int ORIGIN_DOUBLE_TAP_ZOOM = 4;
    public static final int ORIGIN_FLING = 3;
    public static final int ORIGIN_TOUCH = 2;
    public static final int PAN_LIMIT_CENTER = 3;
    public static final int PAN_LIMIT_INSIDE = 1;
    public static final int PAN_LIMIT_OUTSIDE = 2;
    public static final int SCALE_TYPE_CENTER_CROP = 2;
    public static final int SCALE_TYPE_CENTER_INSIDE = 1;
    public static final int SCALE_TYPE_CUSTOM = 3;
    /* access modifiers changed from: private */
    public static final String TAG = "SubsamplingScaleImageView";
    public static int TILE_SIZE_AUTO = Integer.MAX_VALUE;
    /* access modifiers changed from: private */
    public static final List<Integer> VALID_EASING_STYLES = Arrays.asList(new Integer[]{2, 1});
    private static final List<Integer> VALID_ORIENTATIONS = Arrays.asList(new Integer[]{0, 90, Integer.valueOf(ORIENTATION_180), Integer.valueOf(ORIENTATION_270), -1});
    private static final List<Integer> VALID_PAN_LIMITS = Arrays.asList(new Integer[]{1, 2, 3});
    private static final List<Integer> VALID_SCALE_TYPES = Arrays.asList(new Integer[]{2, 1, 3});
    private static final List<Integer> VALID_ZOOM_STYLES = Arrays.asList(new Integer[]{1, 2, 3});
    public static final int ZOOM_FOCUS_CENTER = 2;
    public static final int ZOOM_FOCUS_CENTER_IMMEDIATE = 3;
    public static final int ZOOM_FOCUS_FIXED = 1;
    /* access modifiers changed from: private */
    public Anim anim;
    private Bitmap bitmap;
    private DecoderFactory<? extends ImageDecoder> bitmapDecoderFactory;
    private boolean bitmapIsCached;
    private boolean bitmapIsPreview;
    private Paint bitmapPaint;
    private boolean debug;
    private Paint debugPaint;
    private ImageRegionDecoder decoder;
    /* access modifiers changed from: private */
    public final Object decoderLock;
    private float density;
    private GestureDetector detector;
    private int doubleTapZoomDuration;
    private float doubleTapZoomScale;
    private int doubleTapZoomStyle;
    private float[] dstArray;
    private int fullImageSampleSize;
    private Handler handler;
    private boolean imageLoadedSent;
    private boolean isPanning;
    /* access modifiers changed from: private */
    public boolean isQuickScaling;
    /* access modifiers changed from: private */
    public boolean isZooming;
    private Matrix matrix;
    private float maxScale;
    private int maxTileHeight;
    private int maxTileWidth;
    /* access modifiers changed from: private */
    public int maxTouchCount;
    private float minScale;
    private int minimumScaleType;
    private int minimumTileDpi;
    /* access modifiers changed from: private */
    public OnImageEventListener onImageEventListener;
    /* access modifiers changed from: private */
    public View.OnLongClickListener onLongClickListener;
    private OnStateChangedListener onStateChangedListener;
    private int orientation;
    private Rect pRegion;
    /* access modifiers changed from: private */
    public boolean panEnabled;
    private int panLimit;
    private boolean parallelLoadingEnabled;
    private Float pendingScale;
    /* access modifiers changed from: private */
    public boolean quickScaleEnabled;
    /* access modifiers changed from: private */
    public float quickScaleLastDistance;
    /* access modifiers changed from: private */
    public boolean quickScaleMoved;
    /* access modifiers changed from: private */
    public PointF quickScaleSCenter;
    private final float quickScaleThreshold;
    /* access modifiers changed from: private */
    public PointF quickScaleVLastPoint;
    /* access modifiers changed from: private */
    public PointF quickScaleVStart;
    /* access modifiers changed from: private */
    public boolean readySent;
    private DecoderFactory<? extends ImageRegionDecoder> regionDecoderFactory;
    private int sHeight;
    private int sOrientation;
    private PointF sPendingCenter;
    private RectF sRect;
    /* access modifiers changed from: private */
    public Rect sRegion;
    private PointF sRequestedCenter;
    private int sWidth;
    private ScaleAndTranslate satTemp;
    /* access modifiers changed from: private */
    public float scale;
    /* access modifiers changed from: private */
    public float scaleStart;
    private float[] srcArray;
    private Paint tileBgPaint;
    private Map<Integer, List<Tile>> tileMap;
    private Uri uri;
    /* access modifiers changed from: private */
    public PointF vCenterStart;
    private float vDistStart;
    /* access modifiers changed from: private */
    public PointF vTranslate;
    private PointF vTranslateBefore;
    /* access modifiers changed from: private */
    public PointF vTranslateStart;
    /* access modifiers changed from: private */
    public boolean zoomEnabled;

    private static class Anim {
        /* access modifiers changed from: private */
        public long duration;
        /* access modifiers changed from: private */
        public int easing;
        /* access modifiers changed from: private */
        public boolean interruptible;
        /* access modifiers changed from: private */
        public OnAnimationEventListener listener;
        /* access modifiers changed from: private */
        public int origin;
        /* access modifiers changed from: private */
        public PointF sCenterEnd;
        /* access modifiers changed from: private */
        public PointF sCenterEndRequested;
        /* access modifiers changed from: private */
        public PointF sCenterStart;
        /* access modifiers changed from: private */
        public float scaleEnd;
        /* access modifiers changed from: private */
        public float scaleStart;
        /* access modifiers changed from: private */
        public long time;
        /* access modifiers changed from: private */
        public PointF vFocusEnd;
        /* access modifiers changed from: private */
        public PointF vFocusStart;

        private Anim() {
            this.duration = 500;
            this.interruptible = true;
            this.easing = 2;
            this.origin = 1;
            this.time = System.currentTimeMillis();
        }
    }

    public final class AnimationBuilder {
        private long duration;
        private int easing;
        private boolean interruptible;
        private OnAnimationEventListener listener;
        private int origin;
        private boolean panLimited;
        private final PointF targetSCenter;
        private final float targetScale;
        private final PointF vFocus;

        private AnimationBuilder(float f) {
            this.duration = 500;
            this.easing = 2;
            this.origin = 1;
            this.interruptible = true;
            this.panLimited = true;
            this.targetScale = f;
            this.targetSCenter = SubsamplingScaleImageView.this.getCenter();
            this.vFocus = null;
        }

        private AnimationBuilder(float f, PointF pointF) {
            this.duration = 500;
            this.easing = 2;
            this.origin = 1;
            this.interruptible = true;
            this.panLimited = true;
            this.targetScale = f;
            this.targetSCenter = pointF;
            this.vFocus = null;
        }

        private AnimationBuilder(float f, PointF pointF, PointF pointF2) {
            this.duration = 500;
            this.easing = 2;
            this.origin = 1;
            this.interruptible = true;
            this.panLimited = true;
            this.targetScale = f;
            this.targetSCenter = pointF;
            this.vFocus = pointF2;
        }

        private AnimationBuilder(PointF pointF) {
            this.duration = 500;
            this.easing = 2;
            this.origin = 1;
            this.interruptible = true;
            this.panLimited = true;
            this.targetScale = SubsamplingScaleImageView.this.scale;
            this.targetSCenter = pointF;
            this.vFocus = null;
        }

        /* access modifiers changed from: private */
        public AnimationBuilder withOrigin(int i) {
            this.origin = i;
            return this;
        }

        /* access modifiers changed from: private */
        public AnimationBuilder withPanLimited(boolean z) {
            this.panLimited = z;
            return this;
        }

        public void start() {
            if (!(SubsamplingScaleImageView.this.anim == null || SubsamplingScaleImageView.this.anim.listener == null)) {
                try {
                    SubsamplingScaleImageView.this.anim.listener.onInterruptedByNewAnim();
                } catch (Exception e) {
                    Log.w(SubsamplingScaleImageView.TAG, "Error thrown by animation listener", e);
                }
            }
            int paddingLeft = SubsamplingScaleImageView.this.getPaddingLeft();
            int width = ((SubsamplingScaleImageView.this.getWidth() - SubsamplingScaleImageView.this.getPaddingRight()) - SubsamplingScaleImageView.this.getPaddingLeft()) / 2;
            int paddingTop = SubsamplingScaleImageView.this.getPaddingTop();
            int height = ((SubsamplingScaleImageView.this.getHeight() - SubsamplingScaleImageView.this.getPaddingBottom()) - SubsamplingScaleImageView.this.getPaddingTop()) / 2;
            float access$6500 = SubsamplingScaleImageView.this.limitedScale(this.targetScale);
            PointF access$6600 = this.panLimited ? SubsamplingScaleImageView.this.limitedSCenter(this.targetSCenter.x, this.targetSCenter.y, access$6500, new PointF()) : this.targetSCenter;
            Anim unused = SubsamplingScaleImageView.this.anim = new Anim();
            float unused2 = SubsamplingScaleImageView.this.anim.scaleStart = SubsamplingScaleImageView.this.scale;
            float unused3 = SubsamplingScaleImageView.this.anim.scaleEnd = access$6500;
            long unused4 = SubsamplingScaleImageView.this.anim.time = System.currentTimeMillis();
            PointF unused5 = SubsamplingScaleImageView.this.anim.sCenterEndRequested = access$6600;
            PointF unused6 = SubsamplingScaleImageView.this.anim.sCenterStart = SubsamplingScaleImageView.this.getCenter();
            PointF unused7 = SubsamplingScaleImageView.this.anim.sCenterEnd = access$6600;
            PointF unused8 = SubsamplingScaleImageView.this.anim.vFocusStart = SubsamplingScaleImageView.this.sourceToViewCoord(access$6600);
            PointF unused9 = SubsamplingScaleImageView.this.anim.vFocusEnd = new PointF((float) (paddingLeft + width), (float) (paddingTop + height));
            long unused10 = SubsamplingScaleImageView.this.anim.duration = this.duration;
            boolean unused11 = SubsamplingScaleImageView.this.anim.interruptible = this.interruptible;
            int unused12 = SubsamplingScaleImageView.this.anim.easing = this.easing;
            int unused13 = SubsamplingScaleImageView.this.anim.origin = this.origin;
            long unused14 = SubsamplingScaleImageView.this.anim.time = System.currentTimeMillis();
            OnAnimationEventListener unused15 = SubsamplingScaleImageView.this.anim.listener = this.listener;
            if (this.vFocus != null) {
                float f = this.vFocus.x - (SubsamplingScaleImageView.this.anim.sCenterStart.x * access$6500);
                float f2 = this.vFocus.y - (SubsamplingScaleImageView.this.anim.sCenterStart.y * access$6500);
                ScaleAndTranslate scaleAndTranslate = new ScaleAndTranslate(access$6500, new PointF(f, f2));
                SubsamplingScaleImageView.this.fitToBounds(true, scaleAndTranslate);
                PointF unused16 = SubsamplingScaleImageView.this.anim.vFocusEnd = new PointF((scaleAndTranslate.vTranslate.x - f) + this.vFocus.x, (scaleAndTranslate.vTranslate.y - f2) + this.vFocus.y);
            }
            SubsamplingScaleImageView.this.invalidate();
        }

        public AnimationBuilder withDuration(long j) {
            this.duration = j;
            return this;
        }

        public AnimationBuilder withEasing(int i) {
            if (SubsamplingScaleImageView.VALID_EASING_STYLES.contains(Integer.valueOf(i))) {
                this.easing = i;
                return this;
            }
            throw new IllegalArgumentException("Unknown easing type: " + i);
        }

        public AnimationBuilder withInterruptible(boolean z) {
            this.interruptible = z;
            return this;
        }

        public AnimationBuilder withOnAnimationEventListener(OnAnimationEventListener onAnimationEventListener) {
            this.listener = onAnimationEventListener;
            return this;
        }
    }

    private static class BitmapLoadTask extends AsyncTask<Void, Void, Integer> {
        private Bitmap bitmap;
        private final WeakReference<Context> contextRef;
        private final WeakReference<DecoderFactory<? extends ImageDecoder>> decoderFactoryRef;
        private Exception exception;
        private final boolean preview;
        private final Uri source;
        private final WeakReference<SubsamplingScaleImageView> viewRef;

        BitmapLoadTask(SubsamplingScaleImageView subsamplingScaleImageView, Context context, DecoderFactory<? extends ImageDecoder> decoderFactory, Uri uri, boolean z) {
            this.viewRef = new WeakReference<>(subsamplingScaleImageView);
            this.contextRef = new WeakReference<>(context);
            this.decoderFactoryRef = new WeakReference<>(decoderFactory);
            this.source = uri;
            this.preview = z;
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... voidArr) {
            try {
                String uri = this.source.toString();
                Context context = (Context) this.contextRef.get();
                DecoderFactory decoderFactory = (DecoderFactory) this.decoderFactoryRef.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
                if (!(context == null || decoderFactory == null || subsamplingScaleImageView == null)) {
                    subsamplingScaleImageView.debug("BitmapLoadTask.doInBackground", new Object[0]);
                    this.bitmap = ((ImageDecoder) decoderFactory.make()).decode(context, this.source);
                    return Integer.valueOf(subsamplingScaleImageView.getExifOrientation(context, uri));
                }
            } catch (Exception e) {
                Log.e(SubsamplingScaleImageView.TAG, "Failed to load bitmap", e);
                this.exception = e;
            } catch (OutOfMemoryError e2) {
                Log.e(SubsamplingScaleImageView.TAG, "Failed to load bitmap - OutOfMemoryError", e2);
                this.exception = new RuntimeException(e2);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
            if (subsamplingScaleImageView == null) {
                return;
            }
            if (this.bitmap == null || num == null) {
                if (this.exception != null && subsamplingScaleImageView.onImageEventListener != null) {
                    if (this.preview) {
                        subsamplingScaleImageView.onImageEventListener.onPreviewLoadError(this.exception);
                    } else {
                        subsamplingScaleImageView.onImageEventListener.onImageLoadError(this.exception);
                    }
                }
            } else if (this.preview) {
                subsamplingScaleImageView.onPreviewLoaded(this.bitmap);
            } else {
                subsamplingScaleImageView.onImageLoaded(this.bitmap, num.intValue(), false);
            }
        }
    }

    public static class DefaultOnAnimationEventListener implements OnAnimationEventListener {
        public void onComplete() {
        }

        public void onInterruptedByNewAnim() {
        }

        public void onInterruptedByUser() {
        }
    }

    public static class DefaultOnImageEventListener implements OnImageEventListener {
        public void onImageLoadError(Exception exc) {
        }

        public void onImageLoaded() {
        }

        public void onPreviewLoadError(Exception exc) {
        }

        public void onPreviewReleased() {
        }

        public void onReady() {
        }

        public void onTileLoadError(Exception exc) {
        }
    }

    public static class DefaultOnStateChangedListener implements OnStateChangedListener {
        public void onCenterChanged(PointF pointF, int i) {
        }

        public void onScaleChanged(float f, int i) {
        }
    }

    public interface OnAnimationEventListener {
        void onComplete();

        void onInterruptedByNewAnim();

        void onInterruptedByUser();
    }

    public interface OnImageEventListener {
        void onImageLoadError(Exception exc);

        void onImageLoaded();

        void onPreviewLoadError(Exception exc);

        void onPreviewReleased();

        void onReady();

        void onTileLoadError(Exception exc);
    }

    public interface OnStateChangedListener {
        void onCenterChanged(PointF pointF, int i);

        void onScaleChanged(float f, int i);
    }

    private static class ScaleAndTranslate {
        /* access modifiers changed from: private */
        public float scale;
        /* access modifiers changed from: private */
        public PointF vTranslate;

        private ScaleAndTranslate(float f, PointF pointF) {
            this.scale = f;
            this.vTranslate = pointF;
        }
    }

    private static class Tile {
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public Rect fileSRect;
        /* access modifiers changed from: private */
        public boolean loading;
        /* access modifiers changed from: private */
        public Rect sRect;
        /* access modifiers changed from: private */
        public int sampleSize;
        /* access modifiers changed from: private */
        public Rect vRect;
        /* access modifiers changed from: private */
        public boolean visible;

        private Tile() {
        }
    }

    private static class TileLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private final WeakReference<ImageRegionDecoder> decoderRef;
        private Exception exception;
        private final WeakReference<Tile> tileRef;
        private final WeakReference<SubsamplingScaleImageView> viewRef;

        TileLoadTask(SubsamplingScaleImageView subsamplingScaleImageView, ImageRegionDecoder imageRegionDecoder, Tile tile) {
            this.viewRef = new WeakReference<>(subsamplingScaleImageView);
            this.decoderRef = new WeakReference<>(imageRegionDecoder);
            this.tileRef = new WeakReference<>(tile);
            boolean unused = tile.loading = true;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Void... voidArr) {
            Bitmap decodeRegion;
            try {
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
                ImageRegionDecoder imageRegionDecoder = (ImageRegionDecoder) this.decoderRef.get();
                Tile tile = (Tile) this.tileRef.get();
                if (imageRegionDecoder == null || tile == null || subsamplingScaleImageView == null || !imageRegionDecoder.isReady() || !tile.visible) {
                    if (tile != null) {
                        boolean unused = tile.loading = false;
                    }
                    return null;
                }
                subsamplingScaleImageView.debug("TileLoadTask.doInBackground, tile.sRect=%s, tile.sampleSize=%d", tile.sRect, Integer.valueOf(tile.sampleSize));
                synchronized (subsamplingScaleImageView.decoderLock) {
                    subsamplingScaleImageView.fileSRect(tile.sRect, tile.fileSRect);
                    if (subsamplingScaleImageView.sRegion != null) {
                        tile.fileSRect.offset(subsamplingScaleImageView.sRegion.left, subsamplingScaleImageView.sRegion.top);
                    }
                    decodeRegion = imageRegionDecoder.decodeRegion(tile.fileSRect, tile.sampleSize);
                }
                return decodeRegion;
            } catch (Exception e) {
                Log.e(SubsamplingScaleImageView.TAG, "Failed to decode tile", e);
                this.exception = e;
            } catch (OutOfMemoryError e2) {
                Log.e(SubsamplingScaleImageView.TAG, "Failed to decode tile - OutOfMemoryError", e2);
                this.exception = new RuntimeException(e2);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
            Tile tile = (Tile) this.tileRef.get();
            if (subsamplingScaleImageView != null && tile != null) {
                if (bitmap != null) {
                    Bitmap unused = tile.bitmap = bitmap;
                    boolean unused2 = tile.loading = false;
                    subsamplingScaleImageView.onTileLoaded();
                } else if (this.exception != null && subsamplingScaleImageView.onImageEventListener != null) {
                    subsamplingScaleImageView.onImageEventListener.onTileLoadError(this.exception);
                }
            }
        }
    }

    private static class TilesInitTask extends AsyncTask<Void, Void, int[]> {
        private final WeakReference<Context> contextRef;
        private ImageRegionDecoder decoder;
        private final WeakReference<DecoderFactory<? extends ImageRegionDecoder>> decoderFactoryRef;
        private Exception exception;
        private final Uri source;
        private final WeakReference<SubsamplingScaleImageView> viewRef;

        TilesInitTask(SubsamplingScaleImageView subsamplingScaleImageView, Context context, DecoderFactory<? extends ImageRegionDecoder> decoderFactory, Uri uri) {
            this.viewRef = new WeakReference<>(subsamplingScaleImageView);
            this.contextRef = new WeakReference<>(context);
            this.decoderFactoryRef = new WeakReference<>(decoderFactory);
            this.source = uri;
        }

        /* access modifiers changed from: protected */
        public int[] doInBackground(Void... voidArr) {
            int i;
            int i2;
            try {
                String uri = this.source.toString();
                Context context = (Context) this.contextRef.get();
                DecoderFactory decoderFactory = (DecoderFactory) this.decoderFactoryRef.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
                if (!(context == null || decoderFactory == null || subsamplingScaleImageView == null)) {
                    subsamplingScaleImageView.debug("TilesInitTask.doInBackground", new Object[0]);
                    this.decoder = (ImageRegionDecoder) decoderFactory.make();
                    Point init = this.decoder.init(context, this.source);
                    int i3 = init.x;
                    int i4 = init.y;
                    int access$5200 = subsamplingScaleImageView.getExifOrientation(context, uri);
                    if (subsamplingScaleImageView.sRegion != null) {
                        i = subsamplingScaleImageView.sRegion.width();
                        i2 = subsamplingScaleImageView.sRegion.height();
                    } else {
                        i = i3;
                        i2 = i4;
                    }
                    return new int[]{i, i2, access$5200};
                }
            } catch (Exception e) {
                Log.e(SubsamplingScaleImageView.TAG, "Failed to initialise bitmap decoder", e);
                this.exception = e;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(int[] iArr) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.viewRef.get();
            if (subsamplingScaleImageView == null) {
                return;
            }
            if (this.decoder != null && iArr != null && iArr.length == 3) {
                subsamplingScaleImageView.onTilesInited(this.decoder, iArr[0], iArr[1], iArr[2]);
            } else if (this.exception != null && subsamplingScaleImageView.onImageEventListener != null) {
                subsamplingScaleImageView.onImageEventListener.onImageLoadError(this.exception);
            }
        }
    }

    public SubsamplingScaleImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SubsamplingScaleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int resourceId;
        String string;
        this.orientation = 0;
        this.maxScale = 2.0f;
        this.minScale = minScale();
        this.minimumTileDpi = -1;
        this.panLimit = 1;
        this.minimumScaleType = 1;
        this.maxTileWidth = TILE_SIZE_AUTO;
        this.maxTileHeight = TILE_SIZE_AUTO;
        this.panEnabled = true;
        this.zoomEnabled = true;
        this.quickScaleEnabled = true;
        this.doubleTapZoomScale = 1.0f;
        this.doubleTapZoomStyle = 1;
        this.doubleTapZoomDuration = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.decoderLock = new Object();
        this.bitmapDecoderFactory = new CompatDecoderFactory(SkiaImageDecoder.class);
        this.regionDecoderFactory = new CompatDecoderFactory(SkiaImageRegionDecoder.class);
        this.srcArray = new float[8];
        this.dstArray = new float[8];
        this.density = getResources().getDisplayMetrics().density;
        setMinimumDpi(160);
        setDoubleTapZoomDpi(160);
        setGestureDetector(context);
        this.handler = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (message.what == 1 && SubsamplingScaleImageView.this.onLongClickListener != null) {
                    int unused = SubsamplingScaleImageView.this.maxTouchCount = 0;
                    SubsamplingScaleImageView.super.setOnLongClickListener(SubsamplingScaleImageView.this.onLongClickListener);
                    SubsamplingScaleImageView.this.performLongClick();
                    SubsamplingScaleImageView.super.setOnLongClickListener((View.OnLongClickListener) null);
                }
                return true;
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SubsamplingScaleImageView);
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_assetName) && (string = obtainStyledAttributes.getString(R.styleable.SubsamplingScaleImageView_assetName)) != null && string.length() > 0) {
                setImage(ImageSource.asset(string).tilingEnabled());
            }
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_src) && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.SubsamplingScaleImageView_src, 0)) > 0) {
                setImage(ImageSource.resource(resourceId).tilingEnabled());
            }
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_panEnabled)) {
                setPanEnabled(obtainStyledAttributes.getBoolean(R.styleable.SubsamplingScaleImageView_panEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_zoomEnabled)) {
                setZoomEnabled(obtainStyledAttributes.getBoolean(R.styleable.SubsamplingScaleImageView_zoomEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_quickScaleEnabled)) {
                setQuickScaleEnabled(obtainStyledAttributes.getBoolean(R.styleable.SubsamplingScaleImageView_quickScaleEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.SubsamplingScaleImageView_tileBackgroundColor)) {
                setTileBackgroundColor(obtainStyledAttributes.getColor(R.styleable.SubsamplingScaleImageView_tileBackgroundColor, Color.argb(0, 0, 0, 0)));
            }
            obtainStyledAttributes.recycle();
        }
        this.quickScaleThreshold = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    private int calculateInSampleSize(float f) {
        int i;
        int i2;
        if (this.minimumTileDpi > 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            f *= ((float) this.minimumTileDpi) / ((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f);
        }
        int sWidth2 = (int) (((float) sWidth()) * f);
        int sHeight2 = (int) (((float) sHeight()) * f);
        if (sWidth2 == 0 || sHeight2 == 0) {
            return 32;
        }
        if (sHeight() > sHeight2 || sWidth() > sWidth2) {
            i = Math.round(((float) sHeight()) / ((float) sHeight2));
            int round = Math.round(((float) sWidth()) / ((float) sWidth2));
            if (i < round) {
                i2 = 1;
            } else {
                i2 = 1;
                i = round;
            }
        } else {
            i2 = 1;
            i = 1;
        }
        while (true) {
            int i3 = i2 * 2;
            if (i3 >= i) {
                return i2;
            }
            i2 = i3;
        }
    }

    private boolean checkImageLoaded() {
        boolean isBaseLayerReady = isBaseLayerReady();
        if (!this.imageLoadedSent && isBaseLayerReady) {
            preDraw();
            this.imageLoadedSent = true;
            onImageLoaded();
            if (this.onImageEventListener != null) {
                this.onImageEventListener.onImageLoaded();
            }
        }
        return isBaseLayerReady;
    }

    private boolean checkReady() {
        boolean z = getWidth() > 0 && getHeight() > 0 && this.sWidth > 0 && this.sHeight > 0 && (this.bitmap != null || isBaseLayerReady());
        if (!this.readySent && z) {
            preDraw();
            this.readySent = true;
            onReady();
            if (this.onImageEventListener != null) {
                this.onImageEventListener.onReady();
            }
        }
        return z;
    }

    private void createPaints() {
        if (this.bitmapPaint == null) {
            this.bitmapPaint = new Paint();
            this.bitmapPaint.setAntiAlias(true);
            this.bitmapPaint.setFilterBitmap(true);
            this.bitmapPaint.setDither(true);
        }
        if (this.debugPaint == null && this.debug) {
            this.debugPaint = new Paint();
            this.debugPaint.setTextSize(18.0f);
            this.debugPaint.setColor(-65281);
            this.debugPaint.setStyle(Paint.Style.STROKE);
        }
    }

    /* access modifiers changed from: private */
    @AnyThread
    public void debug(String str, Object... objArr) {
        if (this.debug) {
            Log.d(TAG, String.format(str, objArr));
        }
    }

    private float distance(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    /* access modifiers changed from: private */
    public void doubleTapZoom(PointF pointF, PointF pointF2) {
        if (!this.panEnabled) {
            if (this.sRequestedCenter != null) {
                pointF.x = this.sRequestedCenter.x;
                pointF.y = this.sRequestedCenter.y;
            } else {
                pointF.x = (float) (sWidth() / 2);
                pointF.y = (float) (sHeight() / 2);
            }
        }
        float min = Math.min(this.maxScale, this.doubleTapZoomScale);
        double d = (double) min;
        Double.isNaN(d);
        boolean z = ((double) this.scale) <= d * 0.9d;
        if (!z) {
            min = minScale();
        }
        if (this.doubleTapZoomStyle == 3) {
            setScaleAndCenter(min, pointF);
        } else if (this.doubleTapZoomStyle == 2 || !z || !this.panEnabled) {
            new AnimationBuilder(min, pointF).withInterruptible(false).withDuration((long) this.doubleTapZoomDuration).withOrigin(4).start();
        } else if (this.doubleTapZoomStyle == 1) {
            new AnimationBuilder(min, pointF, pointF2).withInterruptible(false).withDuration((long) this.doubleTapZoomDuration).withOrigin(4).start();
        }
        invalidate();
    }

    private float ease(int i, long j, float f, float f2, long j2) {
        switch (i) {
            case 1:
                return easeOutQuad(j, f, f2, j2);
            case 2:
                return easeInOutQuad(j, f, f2, j2);
            default:
                throw new IllegalStateException("Unexpected easing type: " + i);
        }
    }

    private float easeInOutQuad(long j, float f, float f2, long j2) {
        float f3 = ((float) j) / (((float) j2) / 2.0f);
        if (f3 < 1.0f) {
            return (f3 * (f2 / 2.0f) * f3) + f;
        }
        float f4 = f3 - 1.0f;
        return (((f4 * (f4 - 2.0f)) - 1.0f) * ((-f2) / 2.0f)) + f;
    }

    private float easeOutQuad(long j, float f, float f2, long j2) {
        float f3 = ((float) j) / ((float) j2);
        return ((f3 - 2.0f) * (-f2) * f3) + f;
    }

    private void execute(AsyncTask<Void, Void, ?> asyncTask) {
        if (this.parallelLoadingEnabled && Build.VERSION.SDK_INT >= 11) {
            try {
                AsyncTask.class.getMethod("executeOnExecutor", new Class[]{Executor.class, Object[].class}).invoke(asyncTask, new Object[]{(Executor) AsyncTask.class.getField("THREAD_POOL_EXECUTOR").get((Object) null), null});
                return;
            } catch (Exception e) {
                Log.i(TAG, "Failed to execute AsyncTask on thread pool executor, falling back to single threaded executor", e);
            }
        }
        asyncTask.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    @AnyThread
    public void fileSRect(Rect rect, Rect rect2) {
        if (getRequiredRotation() == 0) {
            rect2.set(rect);
        } else if (getRequiredRotation() == 90) {
            rect2.set(rect.top, this.sHeight - rect.right, rect.bottom, this.sHeight - rect.left);
        } else if (getRequiredRotation() == 180) {
            rect2.set(this.sWidth - rect.right, this.sHeight - rect.bottom, this.sWidth - rect.left, this.sHeight - rect.top);
        } else {
            rect2.set(this.sWidth - rect.bottom, rect.left, this.sWidth - rect.top, rect.right);
        }
    }

    private void fitToBounds(boolean z) {
        boolean z2;
        if (this.vTranslate == null) {
            z2 = true;
            this.vTranslate = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            z2 = false;
        }
        if (this.satTemp == null) {
            this.satTemp = new ScaleAndTranslate(CropImageView.DEFAULT_ASPECT_RATIO, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
        }
        float unused = this.satTemp.scale = this.scale;
        this.satTemp.vTranslate.set(this.vTranslate);
        fitToBounds(z, this.satTemp);
        this.scale = this.satTemp.scale;
        this.vTranslate.set(this.satTemp.vTranslate);
        if (z2) {
            this.vTranslate.set(vTranslateForSCenter((float) (sWidth() / 2), (float) (sHeight() / 2), this.scale));
        }
    }

    /* access modifiers changed from: private */
    public void fitToBounds(boolean z, ScaleAndTranslate scaleAndTranslate) {
        float max;
        float max2;
        float f = 0.5f;
        if (this.panLimit == 2 && isReady()) {
            z = false;
        }
        PointF access$4800 = scaleAndTranslate.vTranslate;
        float limitedScale = limitedScale(scaleAndTranslate.scale);
        float sWidth2 = ((float) sWidth()) * limitedScale;
        float sHeight2 = ((float) sHeight()) * limitedScale;
        if (this.panLimit == 3 && isReady()) {
            access$4800.x = Math.max(access$4800.x, ((float) (getWidth() / 2)) - sWidth2);
            access$4800.y = Math.max(access$4800.y, ((float) (getHeight() / 2)) - sHeight2);
        } else if (z) {
            access$4800.x = Math.max(access$4800.x, ((float) getWidth()) - sWidth2);
            access$4800.y = Math.max(access$4800.y, ((float) getHeight()) - sHeight2);
        } else {
            access$4800.x = Math.max(access$4800.x, -sWidth2);
            access$4800.y = Math.max(access$4800.y, -sHeight2);
        }
        float paddingLeft = (getPaddingLeft() > 0 || getPaddingRight() > 0) ? ((float) getPaddingLeft()) / ((float) (getPaddingLeft() + getPaddingRight())) : 0.5f;
        if (getPaddingTop() > 0 || getPaddingBottom() > 0) {
            f = ((float) getPaddingTop()) / ((float) (getPaddingTop() + getPaddingBottom()));
        }
        if (this.panLimit == 3 && isReady()) {
            max = (float) Math.max(0, getHeight() / 2);
            max2 = (float) Math.max(0, getWidth() / 2);
        } else if (z) {
            max2 = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (((float) getWidth()) - sWidth2) * paddingLeft);
            max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (((float) getHeight()) - sHeight2) * f);
        } else {
            max = (float) Math.max(0, getHeight());
            max2 = (float) Math.max(0, getWidth());
        }
        access$4800.x = Math.min(access$4800.x, max2);
        access$4800.y = Math.min(access$4800.y, max);
        float unused = scaleAndTranslate.scale = limitedScale;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    @AnyThread
    public int getExifOrientation(Context context, String str) {
        Cursor cursor;
        int i;
        Cursor cursor2 = null;
        if (str.startsWith("content")) {
            try {
                cursor = context.getContentResolver().query(Uri.parse(str), new String[]{"orientation"}, (String) null, (String[]) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            i = cursor.getInt(0);
                            if (!VALID_ORIENTATIONS.contains(Integer.valueOf(i)) || i == -1) {
                                Log.w(TAG, "Unsupported orientation: " + i);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            return i;
                        }
                    } catch (Exception e) {
                        try {
                            Log.w(TAG, "Could not get orientation of image from media store");
                            if (cursor != null) {
                                cursor.close();
                                return 0;
                            }
                            return 0;
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            cursor = cursor2;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
                i = 0;
                if (cursor != null) {
                }
                return i;
            } catch (Exception e2) {
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            if (str.startsWith("file:///") && !str.startsWith("file:///android_asset/")) {
                try {
                    int attributeInt = new ExifInterface(str.substring("file:///".length() - 1)).getAttributeInt("Orientation", 1);
                    if (attributeInt != 1) {
                        if (attributeInt == 0) {
                            return 0;
                        }
                        if (attributeInt == 6) {
                            return 90;
                        }
                        if (attributeInt == 3) {
                            return ORIENTATION_180;
                        }
                        if (attributeInt == 8) {
                            return ORIENTATION_270;
                        }
                        Log.w(TAG, "Unsupported EXIF orientation: " + attributeInt);
                        return 0;
                    }
                } catch (Exception e3) {
                    Log.w(TAG, "Could not get EXIF orientation of image");
                }
            }
            return 0;
        }
    }

    private Point getMaxBitmapDimensions(Canvas canvas) {
        int i;
        int i2;
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                i2 = ((Integer) Canvas.class.getMethod("getMaximumBitmapWidth", new Class[0]).invoke(canvas, new Object[0])).intValue();
                try {
                    i = ((Integer) Canvas.class.getMethod("getMaximumBitmapHeight", new Class[0]).invoke(canvas, new Object[0])).intValue();
                } catch (Exception e) {
                    i = 2048;
                }
            } catch (Exception e2) {
            }
            return new Point(Math.min(i2, this.maxTileWidth), Math.min(i, this.maxTileHeight));
        }
        i = 2048;
        i2 = 2048;
        return new Point(Math.min(i2, this.maxTileWidth), Math.min(i, this.maxTileHeight));
    }

    @AnyThread
    private int getRequiredRotation() {
        return this.orientation == -1 ? this.sOrientation : this.orientation;
    }

    private void initialiseBaseLayer(Point point) {
        synchronized (this) {
            debug("initialiseBaseLayer maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
            this.satTemp = new ScaleAndTranslate(CropImageView.DEFAULT_ASPECT_RATIO, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
            fitToBounds(true, this.satTemp);
            this.fullImageSampleSize = calculateInSampleSize(this.satTemp.scale);
            if (this.fullImageSampleSize > 1) {
                this.fullImageSampleSize /= 2;
            }
            if (this.fullImageSampleSize != 1 || this.sRegion != null || sWidth() >= point.x || sHeight() >= point.y) {
                initialiseTileMap(point);
                for (Tile tileLoadTask : this.tileMap.get(Integer.valueOf(this.fullImageSampleSize))) {
                    execute(new TileLoadTask(this, this.decoder, tileLoadTask));
                }
                refreshRequiredTiles(true);
            } else {
                this.decoder.recycle();
                this.decoder = null;
                execute(new BitmapLoadTask(this, getContext(), this.bitmapDecoderFactory, this.uri, false));
            }
        }
    }

    private void initialiseTileMap(Point point) {
        debug("initialiseTileMap maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
        this.tileMap = new LinkedHashMap();
        int i = this.fullImageSampleSize;
        int i2 = 1;
        int i3 = 1;
        while (true) {
            int sWidth2 = sWidth() / i2;
            int sHeight2 = sHeight() / i3;
            int i4 = sWidth2 / i;
            int i5 = sHeight2 / i;
            while (true) {
                if (i4 + i2 + 1 <= point.x) {
                    double d = (double) i4;
                    double width = (double) getWidth();
                    Double.isNaN(width);
                    if (d <= width * 1.25d || i >= this.fullImageSampleSize) {
                        int i6 = i5;
                    }
                }
                i2++;
                sWidth2 = sWidth() / i2;
                i4 = sWidth2 / i;
            }
            int i62 = i5;
            while (true) {
                if (i62 + i3 + 1 <= point.y) {
                    double d2 = (double) i62;
                    double height = (double) getHeight();
                    Double.isNaN(height);
                    if (d2 <= height * 1.25d || i >= this.fullImageSampleSize) {
                        ArrayList arrayList = new ArrayList(i2 * i3);
                        int i7 = 0;
                    }
                }
                i3++;
                int sHeight3 = sHeight() / i3;
                i62 = sHeight3 / i;
                sHeight2 = sHeight3;
            }
            ArrayList arrayList2 = new ArrayList(i2 * i3);
            int i72 = 0;
            while (i72 < i2) {
                int i8 = 0;
                while (i8 < i3) {
                    Tile tile = new Tile();
                    int unused = tile.sampleSize = i;
                    boolean unused2 = tile.visible = i == this.fullImageSampleSize;
                    Rect unused3 = tile.sRect = new Rect(i72 * sWidth2, i8 * sHeight2, i72 == i2 + -1 ? sWidth() : (i72 + 1) * sWidth2, i8 == i3 + -1 ? sHeight() : (i8 + 1) * sHeight2);
                    Rect unused4 = tile.vRect = new Rect(0, 0, 0, 0);
                    Rect unused5 = tile.fileSRect = new Rect(tile.sRect);
                    arrayList2.add(tile);
                    i8++;
                }
                i72++;
            }
            this.tileMap.put(Integer.valueOf(i), arrayList2);
            if (i != 1) {
                i /= 2;
            } else {
                return;
            }
        }
    }

    private boolean isBaseLayerReady() {
        if (this.bitmap != null && !this.bitmapIsPreview) {
            return true;
        }
        if (this.tileMap == null) {
            return false;
        }
        boolean z = true;
        for (Map.Entry next : this.tileMap.entrySet()) {
            if (((Integer) next.getKey()).intValue() == this.fullImageSampleSize) {
                boolean z2 = z;
                for (Tile tile : (List) next.getValue()) {
                    if (tile.loading || tile.bitmap == null) {
                        z2 = false;
                    }
                }
                z = z2;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public PointF limitedSCenter(float f, float f2, float f3, PointF pointF) {
        PointF vTranslateForSCenter = vTranslateForSCenter(f, f2, f3);
        pointF.set((((float) (getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2))) - vTranslateForSCenter.x) / f3, (((float) (getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2))) - vTranslateForSCenter.y) / f3);
        return pointF;
    }

    /* access modifiers changed from: private */
    public float limitedScale(float f) {
        return Math.min(this.maxScale, Math.max(minScale(), f));
    }

    private float minScale() {
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        return this.minimumScaleType == 2 ? Math.max(((float) (getWidth() - paddingLeft)) / ((float) sWidth()), ((float) (getHeight() - paddingBottom)) / ((float) sHeight())) : (this.minimumScaleType != 3 || this.minScale <= CropImageView.DEFAULT_ASPECT_RATIO) ? Math.min(((float) (getWidth() - paddingLeft)) / ((float) sWidth()), ((float) (getHeight() - paddingBottom)) / ((float) sHeight())) : this.minScale;
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Bitmap bitmap2, int i, boolean z) {
        synchronized (this) {
            debug("onImageLoaded", new Object[0]);
            if (this.sWidth > 0 && this.sHeight > 0 && !(this.sWidth == bitmap2.getWidth() && this.sHeight == bitmap2.getHeight())) {
                reset(false);
            }
            if (this.bitmap != null && !this.bitmapIsCached) {
                this.bitmap.recycle();
            }
            if (!(this.bitmap == null || !this.bitmapIsCached || this.onImageEventListener == null)) {
                this.onImageEventListener.onPreviewReleased();
            }
            this.bitmapIsPreview = false;
            this.bitmapIsCached = z;
            this.bitmap = bitmap2;
            this.sWidth = bitmap2.getWidth();
            this.sHeight = bitmap2.getHeight();
            this.sOrientation = i;
            boolean checkReady = checkReady();
            boolean checkImageLoaded = checkImageLoaded();
            if (checkReady || checkImageLoaded) {
                invalidate();
                requestLayout();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    public void onPreviewLoaded(Bitmap bitmap2) {
        synchronized (this) {
            debug("onPreviewLoaded", new Object[0]);
            if (this.bitmap != null || this.imageLoadedSent) {
                bitmap2.recycle();
                return;
            }
            if (this.pRegion != null) {
                this.bitmap = Bitmap.createBitmap(bitmap2, this.pRegion.left, this.pRegion.top, this.pRegion.width(), this.pRegion.height());
            } else {
                this.bitmap = bitmap2;
            }
            this.bitmapIsPreview = true;
            if (checkReady()) {
                invalidate();
                requestLayout();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onTileLoaded() {
        synchronized (this) {
            debug("onTileLoaded", new Object[0]);
            checkReady();
            checkImageLoaded();
            if (isBaseLayerReady() && this.bitmap != null) {
                if (!this.bitmapIsCached) {
                    this.bitmap.recycle();
                }
                this.bitmap = null;
                if (this.onImageEventListener != null && this.bitmapIsCached) {
                    this.onImageEventListener.onPreviewReleased();
                }
                this.bitmapIsPreview = false;
                this.bitmapIsCached = false;
            }
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void onTilesInited(ImageRegionDecoder imageRegionDecoder, int i, int i2, int i3) {
        synchronized (this) {
            debug("onTilesInited sWidth=%d, sHeight=%d, sOrientation=%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.orientation));
            if (this.sWidth > 0 && this.sHeight > 0 && !(this.sWidth == i && this.sHeight == i2)) {
                reset(false);
                if (this.bitmap != null) {
                    if (!this.bitmapIsCached) {
                        this.bitmap.recycle();
                    }
                    this.bitmap = null;
                    if (this.onImageEventListener != null && this.bitmapIsCached) {
                        this.onImageEventListener.onPreviewReleased();
                    }
                    this.bitmapIsPreview = false;
                    this.bitmapIsCached = false;
                }
            }
            this.decoder = imageRegionDecoder;
            this.sWidth = i;
            this.sHeight = i2;
            this.sOrientation = i3;
            checkReady();
            if (!checkImageLoaded() && this.maxTileWidth > 0 && this.maxTileWidth != TILE_SIZE_AUTO && this.maxTileHeight > 0 && this.maxTileHeight != TILE_SIZE_AUTO && getWidth() > 0 && getHeight() > 0) {
                initialiseBaseLayer(new Point(this.maxTileWidth, this.maxTileHeight));
            }
            invalidate();
            requestLayout();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x012a, code lost:
        if ((r14.scale * ((float) sWidth())) >= ((float) getWidth())) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x028d, code lost:
        if ((r14.scale * ((float) sWidth())) >= ((float) getWidth())) goto L_0x028f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x04ee  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a7  */
    private boolean onTouchEventInternal(@NonNull MotionEvent motionEvent) {
        boolean z;
        float f;
        int pointerCount = motionEvent.getPointerCount();
        switch (motionEvent.getAction()) {
            case 0:
            case 5:
            case 261:
                this.anim = null;
                requestDisallowInterceptTouchEvent(true);
                this.maxTouchCount = Math.max(this.maxTouchCount, pointerCount);
                if (pointerCount >= 2) {
                    if (this.zoomEnabled) {
                        float distance = distance(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        this.scaleStart = this.scale;
                        this.vDistStart = distance;
                        this.vTranslateStart.set(this.vTranslate.x, this.vTranslate.y);
                        this.vCenterStart.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
                    } else {
                        this.maxTouchCount = 0;
                    }
                    this.handler.removeMessages(1);
                    return true;
                } else if (this.isQuickScaling) {
                    return true;
                } else {
                    this.vTranslateStart.set(this.vTranslate.x, this.vTranslate.y);
                    this.vCenterStart.set(motionEvent.getX(), motionEvent.getY());
                    this.handler.sendEmptyMessageDelayed(1, 600);
                    return true;
                }
            case 1:
            case 6:
            case 262:
                this.handler.removeMessages(1);
                if (this.isQuickScaling) {
                    this.isQuickScaling = false;
                    if (!this.quickScaleMoved) {
                        doubleTapZoom(this.quickScaleSCenter, this.vCenterStart);
                    }
                }
                if (this.maxTouchCount > 0 && (this.isZooming || this.isPanning)) {
                    if (this.isZooming && pointerCount == 2) {
                        this.isPanning = true;
                        this.vTranslateStart.set(this.vTranslate.x, this.vTranslate.y);
                        if (motionEvent.getActionIndex() == 1) {
                            this.vCenterStart.set(motionEvent.getX(0), motionEvent.getY(0));
                        } else {
                            this.vCenterStart.set(motionEvent.getX(1), motionEvent.getY(1));
                        }
                    }
                    if (pointerCount < 3) {
                        this.isZooming = false;
                    }
                    if (pointerCount < 2) {
                        this.isPanning = false;
                        this.maxTouchCount = 0;
                    }
                    refreshRequiredTiles(true);
                    return true;
                } else if (pointerCount != 1) {
                    return true;
                } else {
                    this.isZooming = false;
                    this.isPanning = false;
                    this.maxTouchCount = 0;
                    return true;
                }
            case 2:
                if (this.maxTouchCount > 0) {
                    if (pointerCount >= 2) {
                        float distance2 = distance(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        float x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        float y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        if (this.zoomEnabled && (distance(this.vCenterStart.x, x, this.vCenterStart.y, y) > 5.0f || Math.abs(distance2 - this.vDistStart) > 5.0f || this.isPanning)) {
                            this.isZooming = true;
                            this.isPanning = true;
                            double d = (double) this.scale;
                            this.scale = Math.min(this.maxScale, (distance2 / this.vDistStart) * this.scaleStart);
                            if (this.scale <= minScale()) {
                                this.vDistStart = distance2;
                                this.scaleStart = minScale();
                                this.vCenterStart.set(x, y);
                                this.vTranslateStart.set(this.vTranslate);
                            } else if (this.panEnabled) {
                                float f2 = this.vCenterStart.x;
                                float f3 = this.vTranslateStart.x;
                                float f4 = this.vCenterStart.y;
                                float f5 = this.vTranslateStart.y;
                                float f6 = this.scale / this.scaleStart;
                                float f7 = this.scale / this.scaleStart;
                                this.vTranslate.x = x - ((f2 - f3) * f6);
                                this.vTranslate.y = y - ((f4 - f5) * f7);
                                double sHeight2 = (double) sHeight();
                                Double.isNaN(d);
                                Double.isNaN(sHeight2);
                                if (sHeight2 * d >= ((double) getHeight()) || this.scale * ((float) sHeight()) < ((float) getHeight())) {
                                    double sWidth2 = (double) sWidth();
                                    Double.isNaN(d);
                                    Double.isNaN(sWidth2);
                                    if (d * sWidth2 < ((double) getWidth())) {
                                        break;
                                    }
                                }
                                fitToBounds(true);
                                this.vCenterStart.set(x, y);
                                this.vTranslateStart.set(this.vTranslate);
                                this.scaleStart = this.scale;
                                this.vDistStart = distance2;
                            } else if (this.sRequestedCenter != null) {
                                this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * this.sRequestedCenter.x);
                                this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * this.sRequestedCenter.y);
                            } else {
                                this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * ((float) (sWidth() / 2)));
                                this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * ((float) (sHeight() / 2)));
                            }
                            fitToBounds(true);
                            refreshRequiredTiles(false);
                        }
                    } else if (this.isQuickScaling) {
                        float abs = this.quickScaleThreshold + (Math.abs(this.quickScaleVStart.y - motionEvent.getY()) * 2.0f);
                        if (this.quickScaleLastDistance == -1.0f) {
                            this.quickScaleLastDistance = abs;
                        }
                        boolean z2 = motionEvent.getY() > this.quickScaleVLastPoint.y;
                        this.quickScaleVLastPoint.set(CropImageView.DEFAULT_ASPECT_RATIO, motionEvent.getY());
                        float abs2 = Math.abs(1.0f - (abs / this.quickScaleLastDistance)) * 0.5f;
                        if (abs2 > 0.03f || this.quickScaleMoved) {
                            this.quickScaleMoved = true;
                            float f8 = this.quickScaleLastDistance > CropImageView.DEFAULT_ASPECT_RATIO ? z2 ? abs2 + 1.0f : 1.0f - abs2 : 1.0f;
                            double d2 = (double) this.scale;
                            this.scale = Math.max(minScale(), Math.min(this.maxScale, f8 * this.scale));
                            if (this.panEnabled) {
                                float f9 = this.vCenterStart.x;
                                float f10 = this.vTranslateStart.x;
                                float f11 = this.vCenterStart.y;
                                float f12 = this.vTranslateStart.y;
                                float f13 = this.scale / this.scaleStart;
                                float f14 = this.scale / this.scaleStart;
                                this.vTranslate.x = this.vCenterStart.x - ((f9 - f10) * f13);
                                this.vTranslate.y = this.vCenterStart.y - ((f11 - f12) * f14);
                                double sHeight3 = (double) sHeight();
                                Double.isNaN(d2);
                                Double.isNaN(sHeight3);
                                if (sHeight3 * d2 >= ((double) getHeight()) || this.scale * ((float) sHeight()) < ((float) getHeight())) {
                                    double sWidth3 = (double) sWidth();
                                    Double.isNaN(d2);
                                    Double.isNaN(sWidth3);
                                    if (d2 * sWidth3 < ((double) getWidth())) {
                                        break;
                                    }
                                }
                                fitToBounds(true);
                                this.vCenterStart.set(sourceToViewCoord(this.quickScaleSCenter));
                                this.vTranslateStart.set(this.vTranslate);
                                this.scaleStart = this.scale;
                                f = CropImageView.DEFAULT_ASPECT_RATIO;
                            } else if (this.sRequestedCenter != null) {
                                this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * this.sRequestedCenter.x);
                                this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * this.sRequestedCenter.y);
                                f = abs;
                            } else {
                                this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * ((float) (sWidth() / 2)));
                                this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * ((float) (sHeight() / 2)));
                                f = abs;
                            }
                            this.quickScaleLastDistance = f;
                            fitToBounds(true);
                            refreshRequiredTiles(false);
                        }
                        f = abs;
                        this.quickScaleLastDistance = f;
                        fitToBounds(true);
                        refreshRequiredTiles(false);
                    } else if (!this.isZooming) {
                        float abs3 = Math.abs(motionEvent.getX() - this.vCenterStart.x);
                        float abs4 = Math.abs(motionEvent.getY() - this.vCenterStart.y);
                        float f15 = this.density * 5.0f;
                        if (abs3 > f15 || abs4 > f15 || this.isPanning) {
                            this.vTranslate.x = this.vTranslateStart.x + (motionEvent.getX() - this.vCenterStart.x);
                            this.vTranslate.y = this.vTranslateStart.y + (motionEvent.getY() - this.vCenterStart.y);
                            float f16 = this.vTranslate.x;
                            float f17 = this.vTranslate.y;
                            fitToBounds(true);
                            boolean z3 = f16 != this.vTranslate.x;
                            boolean z4 = f17 != this.vTranslate.y;
                            boolean z5 = z3 && abs3 > abs4 && !this.isPanning;
                            boolean z6 = z4 && abs4 > abs3 && !this.isPanning;
                            boolean z7 = f17 == this.vTranslate.y && abs4 > 3.0f * f15;
                            if (!z5 && !z6 && (!z3 || !z4 || z7 || this.isPanning)) {
                                this.isPanning = true;
                            } else if (abs3 > f15 || abs4 > f15) {
                                this.maxTouchCount = 0;
                                this.handler.removeMessages(1);
                                requestDisallowInterceptTouchEvent(false);
                            }
                            if (!this.panEnabled) {
                                this.vTranslate.x = this.vTranslateStart.x;
                                this.vTranslate.y = this.vTranslateStart.y;
                                requestDisallowInterceptTouchEvent(false);
                            }
                            refreshRequiredTiles(false);
                        }
                    }
                    z = true;
                    if (z) {
                        return false;
                    }
                    this.handler.removeMessages(1);
                    invalidate();
                    return true;
                }
                z = false;
                if (z) {
                }
                break;
            default:
                return false;
        }
    }

    private void preDraw() {
        if (getWidth() != 0 && getHeight() != 0 && this.sWidth > 0 && this.sHeight > 0) {
            if (!(this.sPendingCenter == null || this.pendingScale == null)) {
                this.scale = this.pendingScale.floatValue();
                if (this.vTranslate == null) {
                    this.vTranslate = new PointF();
                }
                this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * this.sPendingCenter.x);
                this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * this.sPendingCenter.y);
                this.sPendingCenter = null;
                this.pendingScale = null;
                fitToBounds(true);
                refreshRequiredTiles(true);
            }
            fitToBounds(false);
        }
    }

    private void refreshRequiredTiles(boolean z) {
        if (this.decoder != null && this.tileMap != null) {
            int min = Math.min(this.fullImageSampleSize, calculateInSampleSize(this.scale));
            for (Map.Entry<Integer, List<Tile>> value : this.tileMap.entrySet()) {
                for (Tile tile : (List) value.getValue()) {
                    if (tile.sampleSize < min || (tile.sampleSize > min && tile.sampleSize != this.fullImageSampleSize)) {
                        boolean unused = tile.visible = false;
                        if (tile.bitmap != null) {
                            tile.bitmap.recycle();
                            Bitmap unused2 = tile.bitmap = null;
                        }
                    }
                    if (tile.sampleSize == min) {
                        if (tileVisible(tile)) {
                            boolean unused3 = tile.visible = true;
                            if (!tile.loading && tile.bitmap == null && z) {
                                execute(new TileLoadTask(this, this.decoder, tile));
                            }
                        } else if (tile.sampleSize != this.fullImageSampleSize) {
                            boolean unused4 = tile.visible = false;
                            if (tile.bitmap != null) {
                                tile.bitmap.recycle();
                                Bitmap unused5 = tile.bitmap = null;
                            }
                        }
                    } else if (tile.sampleSize == this.fullImageSampleSize) {
                        boolean unused6 = tile.visible = true;
                    }
                }
            }
        }
    }

    private void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void reset(boolean z) {
        debug("reset newImage=" + z, new Object[0]);
        this.scale = CropImageView.DEFAULT_ASPECT_RATIO;
        this.scaleStart = CropImageView.DEFAULT_ASPECT_RATIO;
        this.vTranslate = null;
        this.vTranslateStart = null;
        this.vTranslateBefore = null;
        this.pendingScale = Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO);
        this.sPendingCenter = null;
        this.sRequestedCenter = null;
        this.isZooming = false;
        this.isPanning = false;
        this.isQuickScaling = false;
        this.maxTouchCount = 0;
        this.fullImageSampleSize = 0;
        this.vCenterStart = null;
        this.vDistStart = CropImageView.DEFAULT_ASPECT_RATIO;
        this.quickScaleLastDistance = CropImageView.DEFAULT_ASPECT_RATIO;
        this.quickScaleMoved = false;
        this.quickScaleSCenter = null;
        this.quickScaleVLastPoint = null;
        this.quickScaleVStart = null;
        this.anim = null;
        this.satTemp = null;
        this.matrix = null;
        this.sRect = null;
        if (z) {
            this.uri = null;
            if (this.decoder != null) {
                synchronized (this.decoderLock) {
                    this.decoder.recycle();
                    this.decoder = null;
                }
            }
            if (this.bitmap != null && !this.bitmapIsCached) {
                this.bitmap.recycle();
            }
            if (!(this.bitmap == null || !this.bitmapIsCached || this.onImageEventListener == null)) {
                this.onImageEventListener.onPreviewReleased();
            }
            this.sWidth = 0;
            this.sHeight = 0;
            this.sOrientation = 0;
            this.sRegion = null;
            this.pRegion = null;
            this.readySent = false;
            this.imageLoadedSent = false;
            this.bitmap = null;
            this.bitmapIsPreview = false;
            this.bitmapIsCached = false;
        }
        if (this.tileMap != null) {
            for (Map.Entry<Integer, List<Tile>> value : this.tileMap.entrySet()) {
                for (Tile tile : (List) value.getValue()) {
                    boolean unused = tile.visible = false;
                    if (tile.bitmap != null) {
                        tile.bitmap.recycle();
                        Bitmap unused2 = tile.bitmap = null;
                    }
                }
            }
            this.tileMap = null;
        }
        setGestureDetector(getContext());
    }

    private void restoreState(ImageViewState imageViewState) {
        if (imageViewState != null && imageViewState.getCenter() != null && VALID_ORIENTATIONS.contains(Integer.valueOf(imageViewState.getOrientation()))) {
            this.orientation = imageViewState.getOrientation();
            this.pendingScale = Float.valueOf(imageViewState.getScale());
            this.sPendingCenter = imageViewState.getCenter();
            invalidate();
        }
    }

    private int sHeight() {
        int requiredRotation = getRequiredRotation();
        return (requiredRotation == 90 || requiredRotation == 270) ? this.sWidth : this.sHeight;
    }

    private int sWidth() {
        int requiredRotation = getRequiredRotation();
        return (requiredRotation == 90 || requiredRotation == 270) ? this.sHeight : this.sWidth;
    }

    private void sendStateChanged(float f, PointF pointF, int i) {
        if (this.onStateChangedListener != null) {
            if (this.scale != f) {
                this.onStateChangedListener.onScaleChanged(this.scale, i);
            }
            if (!this.vTranslate.equals(pointF)) {
                this.onStateChangedListener.onCenterChanged(getCenter(), i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setGestureDetector(final Context context) {
        this.detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!SubsamplingScaleImageView.this.zoomEnabled || !SubsamplingScaleImageView.this.readySent || SubsamplingScaleImageView.this.vTranslate == null) {
                    return super.onDoubleTapEvent(motionEvent);
                }
                SubsamplingScaleImageView.this.setGestureDetector(context);
                if (SubsamplingScaleImageView.this.quickScaleEnabled) {
                    PointF unused = SubsamplingScaleImageView.this.vCenterStart = new PointF(motionEvent.getX(), motionEvent.getY());
                    PointF unused2 = SubsamplingScaleImageView.this.vTranslateStart = new PointF(SubsamplingScaleImageView.this.vTranslate.x, SubsamplingScaleImageView.this.vTranslate.y);
                    float unused3 = SubsamplingScaleImageView.this.scaleStart = SubsamplingScaleImageView.this.scale;
                    boolean unused4 = SubsamplingScaleImageView.this.isQuickScaling = true;
                    boolean unused5 = SubsamplingScaleImageView.this.isZooming = true;
                    float unused6 = SubsamplingScaleImageView.this.quickScaleLastDistance = -1.0f;
                    PointF unused7 = SubsamplingScaleImageView.this.quickScaleSCenter = SubsamplingScaleImageView.this.viewToSourceCoord(SubsamplingScaleImageView.this.vCenterStart);
                    PointF unused8 = SubsamplingScaleImageView.this.quickScaleVStart = new PointF(motionEvent.getX(), motionEvent.getY());
                    PointF unused9 = SubsamplingScaleImageView.this.quickScaleVLastPoint = new PointF(SubsamplingScaleImageView.this.quickScaleSCenter.x, SubsamplingScaleImageView.this.quickScaleSCenter.y);
                    boolean unused10 = SubsamplingScaleImageView.this.quickScaleMoved = false;
                    return false;
                }
                SubsamplingScaleImageView.this.doubleTapZoom(SubsamplingScaleImageView.this.viewToSourceCoord(new PointF(motionEvent.getX(), motionEvent.getY())), new PointF(motionEvent.getX(), motionEvent.getY()));
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!SubsamplingScaleImageView.this.panEnabled || !SubsamplingScaleImageView.this.readySent || SubsamplingScaleImageView.this.vTranslate == null || motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || ((Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f) || SubsamplingScaleImageView.this.isZooming))) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                PointF pointF = new PointF(SubsamplingScaleImageView.this.vTranslate.x + (f * 0.25f), SubsamplingScaleImageView.this.vTranslate.y + (0.25f * f2));
                new AnimationBuilder(new PointF((((float) (SubsamplingScaleImageView.this.getWidth() / 2)) - pointF.x) / SubsamplingScaleImageView.this.scale, (((float) (SubsamplingScaleImageView.this.getHeight() / 2)) - pointF.y) / SubsamplingScaleImageView.this.scale)).withEasing(1).withPanLimited(false).withOrigin(3).start();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                SubsamplingScaleImageView.this.performClick();
                return true;
            }
        });
    }

    private void setMatrixArray(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        fArr[6] = f7;
        fArr[7] = f8;
    }

    private Rect sourceToViewRect(Rect rect, Rect rect2) {
        rect2.set((int) sourceToViewX((float) rect.left), (int) sourceToViewY((float) rect.top), (int) sourceToViewX((float) rect.right), (int) sourceToViewY((float) rect.bottom));
        return rect2;
    }

    private float sourceToViewX(float f) {
        if (this.vTranslate == null) {
            return Float.NaN;
        }
        return (this.scale * f) + this.vTranslate.x;
    }

    private float sourceToViewY(float f) {
        if (this.vTranslate == null) {
            return Float.NaN;
        }
        return (this.scale * f) + this.vTranslate.y;
    }

    private boolean tileVisible(Tile tile) {
        return viewToSourceX(CropImageView.DEFAULT_ASPECT_RATIO) <= ((float) tile.sRect.right) && ((float) tile.sRect.left) <= viewToSourceX((float) getWidth()) && viewToSourceY(CropImageView.DEFAULT_ASPECT_RATIO) <= ((float) tile.sRect.bottom) && ((float) tile.sRect.top) <= viewToSourceY((float) getHeight());
    }

    private PointF vTranslateForSCenter(float f, float f2, float f3) {
        int paddingLeft = getPaddingLeft();
        int width = ((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2;
        int paddingTop = getPaddingTop();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2;
        if (this.satTemp == null) {
            this.satTemp = new ScaleAndTranslate(CropImageView.DEFAULT_ASPECT_RATIO, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
        }
        float unused = this.satTemp.scale = f3;
        this.satTemp.vTranslate.set(((float) (paddingLeft + width)) - (f * f3), ((float) (paddingTop + height)) - (f2 * f3));
        fitToBounds(true, this.satTemp);
        return this.satTemp.vTranslate;
    }

    private float viewToSourceX(float f) {
        if (this.vTranslate == null) {
            return Float.NaN;
        }
        return (f - this.vTranslate.x) / this.scale;
    }

    private float viewToSourceY(float f) {
        if (this.vTranslate == null) {
            return Float.NaN;
        }
        return (f - this.vTranslate.y) / this.scale;
    }

    public AnimationBuilder animateCenter(PointF pointF) {
        if (!isReady()) {
            return null;
        }
        return new AnimationBuilder(pointF);
    }

    public AnimationBuilder animateScale(float f) {
        if (!isReady()) {
            return null;
        }
        return new AnimationBuilder(f);
    }

    public AnimationBuilder animateScaleAndCenter(float f, PointF pointF) {
        if (!isReady()) {
            return null;
        }
        return new AnimationBuilder(f, pointF);
    }

    public final int getAppliedOrientation() {
        return getRequiredRotation();
    }

    public final PointF getCenter() {
        return viewToSourceCoord((float) (getWidth() / 2), (float) (getHeight() / 2));
    }

    public float getMaxScale() {
        return this.maxScale;
    }

    public final float getMinScale() {
        return minScale();
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public final int getSHeight() {
        return this.sHeight;
    }

    public final int getSWidth() {
        return this.sWidth;
    }

    public final float getScale() {
        return this.scale;
    }

    public final ImageViewState getState() {
        if (this.vTranslate == null || this.sWidth <= 0 || this.sHeight <= 0) {
            return null;
        }
        return new ImageViewState(getScale(), getCenter(), getOrientation());
    }

    public boolean hasImage() {
        return (this.uri == null && this.bitmap == null) ? false : true;
    }

    public final boolean isImageLoaded() {
        return this.imageLoadedSent;
    }

    public final boolean isPanEnabled() {
        return this.panEnabled;
    }

    public final boolean isQuickScaleEnabled() {
        return this.quickScaleEnabled;
    }

    public final boolean isReady() {
        return this.readySent;
    }

    public final boolean isZoomEnabled() {
        return this.zoomEnabled;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        createPaints();
        if (this.sWidth != 0 && this.sHeight != 0 && getWidth() != 0 && getHeight() != 0) {
            if (this.tileMap == null && this.decoder != null) {
                initialiseBaseLayer(getMaxBitmapDimensions(canvas));
            }
            if (checkReady()) {
                preDraw();
                if (this.anim != null) {
                    float f = this.scale;
                    if (this.vTranslateBefore == null) {
                        this.vTranslateBefore = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                    }
                    this.vTranslateBefore.set(this.vTranslate);
                    long currentTimeMillis = System.currentTimeMillis() - this.anim.time;
                    boolean z = currentTimeMillis > this.anim.duration;
                    long min = Math.min(currentTimeMillis, this.anim.duration);
                    this.scale = ease(this.anim.easing, min, this.anim.scaleStart, this.anim.scaleEnd - this.anim.scaleStart, this.anim.duration);
                    float ease = ease(this.anim.easing, min, this.anim.vFocusStart.x, this.anim.vFocusEnd.x - this.anim.vFocusStart.x, this.anim.duration);
                    float ease2 = ease(this.anim.easing, min, this.anim.vFocusStart.y, this.anim.vFocusEnd.y - this.anim.vFocusStart.y, this.anim.duration);
                    this.vTranslate.x -= sourceToViewX(this.anim.sCenterEnd.x) - ease;
                    this.vTranslate.y -= sourceToViewY(this.anim.sCenterEnd.y) - ease2;
                    fitToBounds(z || this.anim.scaleStart == this.anim.scaleEnd);
                    sendStateChanged(f, this.vTranslateBefore, this.anim.origin);
                    refreshRequiredTiles(z);
                    if (z) {
                        if (this.anim.listener != null) {
                            try {
                                this.anim.listener.onComplete();
                            } catch (Exception e) {
                                Log.w(TAG, "Error thrown by animation listener", e);
                            }
                        }
                        this.anim = null;
                    }
                    invalidate();
                }
                if (this.tileMap != null && isBaseLayerReady()) {
                    int min2 = Math.min(this.fullImageSampleSize, calculateInSampleSize(this.scale));
                    boolean z2 = false;
                    for (Map.Entry next : this.tileMap.entrySet()) {
                        if (((Integer) next.getKey()).intValue() == min2) {
                            boolean z3 = z2;
                            for (Tile tile : (List) next.getValue()) {
                                if (tile.visible && (tile.loading || tile.bitmap == null)) {
                                    z3 = true;
                                }
                            }
                            z2 = z3;
                        }
                    }
                    for (Map.Entry next2 : this.tileMap.entrySet()) {
                        if (((Integer) next2.getKey()).intValue() == min2 || z2) {
                            for (Tile tile2 : (List) next2.getValue()) {
                                sourceToViewRect(tile2.sRect, tile2.vRect);
                                if (!tile2.loading && tile2.bitmap != null) {
                                    if (this.tileBgPaint != null) {
                                        canvas.drawRect(tile2.vRect, this.tileBgPaint);
                                    }
                                    if (this.matrix == null) {
                                        this.matrix = new Matrix();
                                    }
                                    this.matrix.reset();
                                    setMatrixArray(this.srcArray, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) tile2.bitmap.getWidth(), CropImageView.DEFAULT_ASPECT_RATIO, (float) tile2.bitmap.getWidth(), (float) tile2.bitmap.getHeight(), CropImageView.DEFAULT_ASPECT_RATIO, (float) tile2.bitmap.getHeight());
                                    if (getRequiredRotation() == 0) {
                                        setMatrixArray(this.dstArray, (float) tile2.vRect.left, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.bottom);
                                    } else if (getRequiredRotation() == 90) {
                                        setMatrixArray(this.dstArray, (float) tile2.vRect.right, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.top);
                                    } else if (getRequiredRotation() == 180) {
                                        setMatrixArray(this.dstArray, (float) tile2.vRect.right, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.top);
                                    } else if (getRequiredRotation() == 270) {
                                        setMatrixArray(this.dstArray, (float) tile2.vRect.left, (float) tile2.vRect.bottom, (float) tile2.vRect.left, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.top, (float) tile2.vRect.right, (float) tile2.vRect.bottom);
                                    }
                                    this.matrix.setPolyToPoly(this.srcArray, 0, this.dstArray, 0, 4);
                                    canvas.drawBitmap(tile2.bitmap, this.matrix, this.bitmapPaint);
                                    if (this.debug) {
                                        canvas.drawRect(tile2.vRect, this.debugPaint);
                                    }
                                } else if (tile2.loading && this.debug) {
                                    canvas.drawText("LOADING", (float) (tile2.vRect.left + 5), (float) (tile2.vRect.top + 35), this.debugPaint);
                                }
                                if (tile2.visible && this.debug) {
                                    canvas.drawText("ISS " + tile2.sampleSize + " RECT " + tile2.sRect.top + ListUtils.DEFAULT_JOIN_SEPARATOR + tile2.sRect.left + ListUtils.DEFAULT_JOIN_SEPARATOR + tile2.sRect.bottom + ListUtils.DEFAULT_JOIN_SEPARATOR + tile2.sRect.right, (float) (tile2.vRect.left + 5), (float) (tile2.vRect.top + 15), this.debugPaint);
                                }
                            }
                        }
                    }
                } else if (this.bitmap != null) {
                    float f2 = this.scale;
                    float f3 = this.scale;
                    if (this.bitmapIsPreview) {
                        f2 = (((float) this.sWidth) / ((float) this.bitmap.getWidth())) * this.scale;
                        f3 = this.scale * (((float) this.sHeight) / ((float) this.bitmap.getHeight()));
                    }
                    if (this.matrix == null) {
                        this.matrix = new Matrix();
                    }
                    this.matrix.reset();
                    this.matrix.postScale(f2, f3);
                    this.matrix.postRotate((float) getRequiredRotation());
                    this.matrix.postTranslate(this.vTranslate.x, this.vTranslate.y);
                    if (getRequiredRotation() == 180) {
                        this.matrix.postTranslate(this.scale * ((float) this.sWidth), this.scale * ((float) this.sHeight));
                    } else if (getRequiredRotation() == 90) {
                        this.matrix.postTranslate(this.scale * ((float) this.sHeight), CropImageView.DEFAULT_ASPECT_RATIO);
                    } else if (getRequiredRotation() == 270) {
                        this.matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, this.scale * ((float) this.sWidth));
                    }
                    if (this.tileBgPaint != null) {
                        if (this.sRect == null) {
                            this.sRect = new RectF();
                        }
                        this.sRect.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) (this.bitmapIsPreview ? this.bitmap.getWidth() : this.sWidth), (float) (this.bitmapIsPreview ? this.bitmap.getHeight() : this.sHeight));
                        this.matrix.mapRect(this.sRect);
                        canvas.drawRect(this.sRect, this.tileBgPaint);
                    }
                    canvas.drawBitmap(this.bitmap, this.matrix, this.bitmapPaint);
                }
                if (this.debug) {
                    canvas.drawText("Scale: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.scale)}), 5.0f, 15.0f, this.debugPaint);
                    canvas.drawText("Translate: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.vTranslate.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.vTranslate.y)}), 5.0f, 35.0f, this.debugPaint);
                    PointF center = getCenter();
                    canvas.drawText("Source center: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.y)}), 5.0f, 55.0f, this.debugPaint);
                    this.debugPaint.setStrokeWidth(2.0f);
                    if (this.anim != null) {
                        PointF sourceToViewCoord = sourceToViewCoord(this.anim.sCenterStart);
                        PointF sourceToViewCoord2 = sourceToViewCoord(this.anim.sCenterEndRequested);
                        PointF sourceToViewCoord3 = sourceToViewCoord(this.anim.sCenterEnd);
                        canvas.drawCircle(sourceToViewCoord.x, sourceToViewCoord.y, 10.0f, this.debugPaint);
                        this.debugPaint.setColor(-65536);
                        canvas.drawCircle(sourceToViewCoord2.x, sourceToViewCoord2.y, 20.0f, this.debugPaint);
                        this.debugPaint.setColor(-16776961);
                        canvas.drawCircle(sourceToViewCoord3.x, sourceToViewCoord3.y, 25.0f, this.debugPaint);
                        this.debugPaint.setColor(-16711681);
                        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), 30.0f, this.debugPaint);
                    }
                    if (this.vCenterStart != null) {
                        this.debugPaint.setColor(-65536);
                        canvas.drawCircle(this.vCenterStart.x, this.vCenterStart.y, 20.0f, this.debugPaint);
                    }
                    if (this.quickScaleSCenter != null) {
                        this.debugPaint.setColor(-16776961);
                        canvas.drawCircle(sourceToViewX(this.quickScaleSCenter.x), sourceToViewY(this.quickScaleSCenter.y), 35.0f, this.debugPaint);
                    }
                    if (this.quickScaleVStart != null) {
                        this.debugPaint.setColor(-16711681);
                        canvas.drawCircle(this.quickScaleVStart.x, this.quickScaleVStart.y, 30.0f, this.debugPaint);
                    }
                    this.debugPaint.setColor(-65281);
                    this.debugPaint.setStrokeWidth(1.0f);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onImageLoaded() {
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        boolean z = true;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        boolean z2 = mode != 1073741824;
        if (mode2 == 1073741824) {
            z = false;
        }
        if (this.sWidth > 0 && this.sHeight > 0) {
            if (!z2 || !z) {
                if (z) {
                    double sHeight2 = (double) sHeight();
                    double sWidth2 = (double) sWidth();
                    Double.isNaN(sHeight2);
                    Double.isNaN(sWidth2);
                    double d = sHeight2 / sWidth2;
                    double d2 = (double) size;
                    Double.isNaN(d2);
                    i3 = (int) (d * d2);
                    i4 = size;
                } else if (z2) {
                    double sWidth3 = (double) sWidth();
                    double sHeight3 = (double) sHeight();
                    Double.isNaN(sWidth3);
                    Double.isNaN(sHeight3);
                    double d3 = sWidth3 / sHeight3;
                    double d4 = (double) size2;
                    Double.isNaN(d4);
                    i4 = (int) (d3 * d4);
                    i3 = size2;
                }
                setMeasuredDimension(Math.max(i4, getSuggestedMinimumWidth()), Math.max(i3, getSuggestedMinimumHeight()));
            }
            i4 = sWidth();
            i3 = sHeight();
            setMeasuredDimension(Math.max(i4, getSuggestedMinimumWidth()), Math.max(i3, getSuggestedMinimumHeight()));
        }
        i3 = size2;
        i4 = size;
        setMeasuredDimension(Math.max(i4, getSuggestedMinimumWidth()), Math.max(i3, getSuggestedMinimumHeight()));
    }

    /* access modifiers changed from: protected */
    public void onReady() {
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        debug("onSizeChanged %dx%d -> %dx%d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2));
        PointF center = getCenter();
        if (this.readySent && center != null) {
            this.anim = null;
            this.pendingScale = Float.valueOf(this.scale);
            this.sPendingCenter = center;
        }
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        if (this.anim == null || this.anim.interruptible) {
            if (!(this.anim == null || this.anim.listener == null)) {
                try {
                    this.anim.listener.onInterruptedByUser();
                } catch (Exception e) {
                    Log.w(TAG, "Error thrown by animation listener", e);
                }
            }
            this.anim = null;
            if (this.vTranslate == null) {
                return true;
            }
            if (this.isQuickScaling || (this.detector != null && !this.detector.onTouchEvent(motionEvent))) {
                if (this.vTranslateStart == null) {
                    this.vTranslateStart = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                }
                if (this.vTranslateBefore == null) {
                    this.vTranslateBefore = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                }
                if (this.vCenterStart == null) {
                    this.vCenterStart = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                }
                float f = this.scale;
                this.vTranslateBefore.set(this.vTranslate);
                boolean onTouchEventInternal = onTouchEventInternal(motionEvent);
                sendStateChanged(f, this.vTranslateBefore, 2);
                if (onTouchEventInternal) {
                    z = true;
                } else if (super.onTouchEvent(motionEvent)) {
                    return true;
                }
                return z;
            }
            this.isZooming = false;
            this.isPanning = false;
            this.maxTouchCount = 0;
            return true;
        }
        requestDisallowInterceptTouchEvent(true);
        return true;
    }

    public void recycle() {
        reset(true);
        this.bitmapPaint = null;
        this.debugPaint = null;
        this.tileBgPaint = null;
    }

    public final void resetScaleAndCenter() {
        this.anim = null;
        this.pendingScale = Float.valueOf(limitedScale(CropImageView.DEFAULT_ASPECT_RATIO));
        if (isReady()) {
            this.sPendingCenter = new PointF((float) (sWidth() / 2), (float) (sHeight() / 2));
        } else {
            this.sPendingCenter = new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        }
        invalidate();
    }

    public final void setBitmapDecoderClass(Class<? extends ImageDecoder> cls) {
        if (cls != null) {
            this.bitmapDecoderFactory = new CompatDecoderFactory(cls);
            return;
        }
        throw new IllegalArgumentException("Decoder class cannot be set to null");
    }

    public final void setBitmapDecoderFactory(DecoderFactory<? extends ImageDecoder> decoderFactory) {
        if (decoderFactory != null) {
            this.bitmapDecoderFactory = decoderFactory;
            return;
        }
        throw new IllegalArgumentException("Decoder factory cannot be set to null");
    }

    public final void setDebug(boolean z) {
        this.debug = z;
    }

    public final void setDoubleTapZoomDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setDoubleTapZoomScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public final void setDoubleTapZoomDuration(int i) {
        this.doubleTapZoomDuration = Math.max(0, i);
    }

    public final void setDoubleTapZoomScale(float f) {
        this.doubleTapZoomScale = f;
    }

    public final void setDoubleTapZoomStyle(int i) {
        if (VALID_ZOOM_STYLES.contains(Integer.valueOf(i))) {
            this.doubleTapZoomStyle = i;
            return;
        }
        throw new IllegalArgumentException("Invalid zoom style: " + i);
    }

    public final void setImage(ImageSource imageSource) {
        setImage(imageSource, (ImageSource) null, (ImageViewState) null);
    }

    public final void setImage(ImageSource imageSource, ImageSource imageSource2) {
        setImage(imageSource, imageSource2, (ImageViewState) null);
    }

    public final void setImage(ImageSource imageSource, ImageSource imageSource2, ImageViewState imageViewState) {
        if (imageSource != null) {
            reset(true);
            if (imageViewState != null) {
                restoreState(imageViewState);
            }
            if (imageSource2 != null) {
                if (imageSource.getBitmap() != null) {
                    throw new IllegalArgumentException("Preview image cannot be used when a bitmap is provided for the main image");
                } else if (imageSource.getSWidth() <= 0 || imageSource.getSHeight() <= 0) {
                    throw new IllegalArgumentException("Preview image cannot be used unless dimensions are provided for the main image");
                } else {
                    this.sWidth = imageSource.getSWidth();
                    this.sHeight = imageSource.getSHeight();
                    this.pRegion = imageSource2.getSRegion();
                    if (imageSource2.getBitmap() != null) {
                        this.bitmapIsCached = imageSource2.isCached();
                        onPreviewLoaded(imageSource2.getBitmap());
                    } else {
                        Uri uri2 = imageSource2.getUri();
                        if (uri2 == null && imageSource2.getResource() != null) {
                            uri2 = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + imageSource2.getResource());
                        }
                        execute(new BitmapLoadTask(this, getContext(), this.bitmapDecoderFactory, uri2, true));
                    }
                }
            }
            if (imageSource.getBitmap() != null && imageSource.getSRegion() != null) {
                onImageLoaded(Bitmap.createBitmap(imageSource.getBitmap(), imageSource.getSRegion().left, imageSource.getSRegion().top, imageSource.getSRegion().width(), imageSource.getSRegion().height()), 0, false);
            } else if (imageSource.getBitmap() != null) {
                onImageLoaded(imageSource.getBitmap(), 0, imageSource.isCached());
            } else {
                this.sRegion = imageSource.getSRegion();
                this.uri = imageSource.getUri();
                if (this.uri == null && imageSource.getResource() != null) {
                    this.uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + imageSource.getResource());
                }
                if (imageSource.getTile() || this.sRegion != null) {
                    execute(new TilesInitTask(this, getContext(), this.regionDecoderFactory, this.uri));
                    return;
                }
                execute(new BitmapLoadTask(this, getContext(), this.bitmapDecoderFactory, this.uri, false));
            }
        } else {
            throw new NullPointerException("imageSource must not be null");
        }
    }

    public final void setImage(ImageSource imageSource, ImageViewState imageViewState) {
        setImage(imageSource, (ImageSource) null, imageViewState);
    }

    public final void setMaxScale(float f) {
        this.maxScale = f;
    }

    public void setMaxTileSize(int i) {
        this.maxTileWidth = i;
        this.maxTileHeight = i;
    }

    public void setMaxTileSize(int i, int i2) {
        this.maxTileWidth = i;
        this.maxTileHeight = i2;
    }

    public final void setMaximumDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setMinScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public final void setMinScale(float f) {
        this.minScale = f;
    }

    public final void setMinimumDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setMaxScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public final void setMinimumScaleType(int i) {
        if (VALID_SCALE_TYPES.contains(Integer.valueOf(i))) {
            this.minimumScaleType = i;
            if (isReady()) {
                fitToBounds(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid scale type: " + i);
    }

    public void setMinimumTileDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.minimumTileDpi = (int) Math.min((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f, (float) i);
        if (isReady()) {
            reset(false);
            invalidate();
        }
    }

    public void setOnImageEventListener(OnImageEventListener onImageEventListener2) {
        this.onImageEventListener = onImageEventListener2;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener2) {
        this.onLongClickListener = onLongClickListener2;
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener2) {
        this.onStateChangedListener = onStateChangedListener2;
    }

    public final void setOrientation(int i) {
        if (VALID_ORIENTATIONS.contains(Integer.valueOf(i))) {
            this.orientation = i;
            reset(false);
            invalidate();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i);
    }

    public final void setPanEnabled(boolean z) {
        this.panEnabled = z;
        if (!z && this.vTranslate != null) {
            this.vTranslate.x = ((float) (getWidth() / 2)) - (this.scale * ((float) (sWidth() / 2)));
            this.vTranslate.y = ((float) (getHeight() / 2)) - (this.scale * ((float) (sHeight() / 2)));
            if (isReady()) {
                refreshRequiredTiles(true);
                invalidate();
            }
        }
    }

    public final void setPanLimit(int i) {
        if (VALID_PAN_LIMITS.contains(Integer.valueOf(i))) {
            this.panLimit = i;
            if (isReady()) {
                fitToBounds(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid pan limit: " + i);
    }

    public void setParallelLoadingEnabled(boolean z) {
        this.parallelLoadingEnabled = z;
    }

    public final void setQuickScaleEnabled(boolean z) {
        this.quickScaleEnabled = z;
    }

    public final void setRegionDecoderClass(Class<? extends ImageRegionDecoder> cls) {
        if (cls != null) {
            this.regionDecoderFactory = new CompatDecoderFactory(cls);
            return;
        }
        throw new IllegalArgumentException("Decoder class cannot be set to null");
    }

    public final void setRegionDecoderFactory(DecoderFactory<? extends ImageRegionDecoder> decoderFactory) {
        if (decoderFactory != null) {
            this.regionDecoderFactory = decoderFactory;
            return;
        }
        throw new IllegalArgumentException("Decoder factory cannot be set to null");
    }

    public final void setScaleAndCenter(float f, PointF pointF) {
        this.anim = null;
        this.pendingScale = Float.valueOf(f);
        this.sPendingCenter = pointF;
        this.sRequestedCenter = pointF;
        invalidate();
    }

    public final void setTileBackgroundColor(int i) {
        if (Color.alpha(i) == 0) {
            this.tileBgPaint = null;
        } else {
            this.tileBgPaint = new Paint();
            this.tileBgPaint.setStyle(Paint.Style.FILL);
            this.tileBgPaint.setColor(i);
        }
        invalidate();
    }

    public final void setZoomEnabled(boolean z) {
        this.zoomEnabled = z;
    }

    public final PointF sourceToViewCoord(float f, float f2) {
        return sourceToViewCoord(f, f2, new PointF());
    }

    public final PointF sourceToViewCoord(float f, float f2, PointF pointF) {
        if (this.vTranslate == null) {
            return null;
        }
        pointF.set(sourceToViewX(f), sourceToViewY(f2));
        return pointF;
    }

    public final PointF sourceToViewCoord(PointF pointF) {
        return sourceToViewCoord(pointF.x, pointF.y, new PointF());
    }

    public final PointF sourceToViewCoord(PointF pointF, PointF pointF2) {
        return sourceToViewCoord(pointF.x, pointF.y, pointF2);
    }

    public final PointF viewToSourceCoord(float f, float f2) {
        return viewToSourceCoord(f, f2, new PointF());
    }

    public final PointF viewToSourceCoord(float f, float f2, PointF pointF) {
        if (this.vTranslate == null) {
            return null;
        }
        pointF.set(viewToSourceX(f), viewToSourceY(f2));
        return pointF;
    }

    public final PointF viewToSourceCoord(PointF pointF) {
        return viewToSourceCoord(pointF.x, pointF.y, new PointF());
    }

    public final PointF viewToSourceCoord(PointF pointF, PointF pointF2) {
        return viewToSourceCoord(pointF.x, pointF.y, pointF2);
    }
}
