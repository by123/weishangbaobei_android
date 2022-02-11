package com.scwang.smartrefresh.header.internal;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final int ANIMATION_DURATION = 1332;
    private static final byte ARROW_HEIGHT = 5;
    private static final byte ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 5.0f;
    private static final byte ARROW_WIDTH = 10;
    private static final byte ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final byte CIRCLE_DIAMETER = 40;
    private static final byte CIRCLE_DIAMETER_LARGE = 56;
    private static final int[] COLORS = {-16777216};
    private static final float COLOR_START_DELAY_OFFSET = 0.75f;
    public static final byte DEFAULT = 1;
    private static final float END_TRIM_START_DELAY_OFFSET = 0.5f;
    private static final float FULL_ROTATION = 1080.0f;
    public static final byte LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final byte NUM_POINTS = 5;
    private static final float START_TRIM_DURATION_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private Animation mAnimation;
    private final List<Animation> mAnimators = new ArrayList();
    boolean mFinishing;
    private float mHeight;
    private View mParent;
    private final Ring mRing = new Ring();
    private float mRotation;
    float mRotationCount;
    private float mWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    private class Ring {
        int mAlpha;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint = new Paint();
        float mArrowScale;
        int mArrowWidth;
        int mBackgroundColor;
        final Paint mCirclePaint = new Paint(1);
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim = CropImageView.DEFAULT_ASPECT_RATIO;
        final Paint mPaint = new Paint();
        double mRingCenterRadius;
        float mRotation = CropImageView.DEFAULT_ASPECT_RATIO;
        boolean mShowArrow;
        float mStartTrim = CropImageView.DEFAULT_ASPECT_RATIO;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeInset = MaterialProgressDrawable.STROKE_WIDTH;
        float mStrokeWidth = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
        final RectF mTempBounds = new RectF();

        Ring() {
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mArrowPaint.setStyle(Paint.Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
        }

        private void drawTriangle(Canvas canvas, float f, float f2, Rect rect) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }
                float f3 = this.mArrowScale;
                double d = this.mRingCenterRadius;
                double cos = Math.cos(0.0d);
                double exactCenterX = (double) rect.exactCenterX();
                Double.isNaN(exactCenterX);
                double d2 = this.mRingCenterRadius;
                double sin = Math.sin(0.0d);
                double exactCenterY = (double) rect.exactCenterY();
                Double.isNaN(exactCenterY);
                this.mArrow.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, CropImageView.DEFAULT_ASPECT_RATIO);
                this.mArrow.lineTo((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, ((float) this.mArrowHeight) * this.mArrowScale);
                this.mArrow.offset(((float) ((d * cos) + exactCenterX)) - (((float) (((int) this.mStrokeInset) / 2)) * f3), (float) ((d2 * sin) + exactCenterY));
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                canvas.rotate((f + f2) - MaterialProgressDrawable.ARROW_OFFSET_ANGLE, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.mArrow, this.mArrowPaint);
            }
        }

        private int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        public void draw(Canvas canvas, Rect rect) {
            RectF rectF = this.mTempBounds;
            rectF.set(rect);
            rectF.inset(this.mStrokeInset, this.mStrokeInset);
            float f = (this.mStartTrim + this.mRotation) * 360.0f;
            float f2 = ((this.mEndTrim + this.mRotation) * 360.0f) - f;
            if (f2 != CropImageView.DEFAULT_ASPECT_RATIO) {
                this.mPaint.setColor(this.mCurrentColor);
                canvas.drawArc(rectF, f, f2, false, this.mPaint);
            }
            drawTriangle(canvas, f, f2, rect);
            if (this.mAlpha < 255) {
                this.mCirclePaint.setColor(this.mBackgroundColor);
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.mCirclePaint);
            }
        }

        public int getNextColor() {
            return this.mColors[getNextColorIndex()];
        }

        public int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        public void goToNextColor() {
            setColorIndex(getNextColorIndex());
        }

        public void resetOriginals() {
            this.mStartingStartTrim = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mStartingEndTrim = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mStartingRotation = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mStartTrim = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mEndTrim = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mRotation = CropImageView.DEFAULT_ASPECT_RATIO;
        }

        public void setColorIndex(int i) {
            this.mColorIndex = i;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        public void setInsets(int i, int i2) {
            float f;
            float min = (float) Math.min(i, i2);
            if (this.mRingCenterRadius <= 0.0d || min < CropImageView.DEFAULT_ASPECT_RATIO) {
                f = (float) Math.ceil((double) (this.mStrokeWidth / 2.0f));
            } else {
                double d = (double) (min / 2.0f);
                double d2 = this.mRingCenterRadius;
                Double.isNaN(d);
                f = (float) (d - d2);
            }
            this.mStrokeInset = f;
        }

        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }

    public MaterialProgressDrawable(View view) {
        this.mParent = view;
        setColorSchemeColors(COLORS);
        updateSizes(1);
        setupAnimators();
    }

    private int evaluateColorChange(float f, int i, int i2) {
        int intValue = Integer.valueOf(i).intValue();
        int i3 = (intValue >> 24) & 255;
        int i4 = (intValue >> 16) & 255;
        int i5 = (intValue >> 8) & 255;
        int i6 = intValue & 255;
        int intValue2 = Integer.valueOf(i2).intValue();
        return (i6 + ((int) (((float) ((intValue2 & 255) - i6)) * f))) | ((i3 + ((int) (((float) (((intValue2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((intValue2 >> 16) & 255) - i4)) * f))) << 16) | ((((int) (((float) (((intValue2 >> 8) & 255) - i5)) * f)) + i5) << 8);
    }

    private void setSizeParameters(int i, int i2, float f, float f2, float f3, float f4) {
        float f5 = Resources.getSystem().getDisplayMetrics().density;
        this.mWidth = ((float) i) * f5;
        this.mHeight = ((float) i2) * f5;
        this.mRing.setColorIndex(0);
        float f6 = f2 * f5;
        this.mRing.mPaint.setStrokeWidth(f6);
        this.mRing.mStrokeWidth = f6;
        this.mRing.mRingCenterRadius = (double) (f * f5);
        this.mRing.mArrowWidth = (int) (f3 * f5);
        this.mRing.mArrowHeight = (int) (f5 * f4);
        this.mRing.setInsets((int) this.mWidth, (int) this.mHeight);
        invalidateSelf();
    }

    private void setupAnimators() {
        final Ring ring = this.mRing;
        AnonymousClass1 r1 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.applyFinishTranslation(f, ring);
                    return;
                }
                float minProgressArc = MaterialProgressDrawable.this.getMinProgressArc(ring);
                float f2 = ring.mStartingEndTrim;
                float f3 = ring.mStartingStartTrim;
                float f4 = ring.mStartingRotation;
                MaterialProgressDrawable.this.updateRingColor(f, ring);
                if (f <= 0.5f) {
                    ring.mStartTrim = f3 + (MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f / 0.5f) * (MaterialProgressDrawable.MAX_PROGRESS_ARC - minProgressArc));
                }
                if (f > 0.5f) {
                    ring.mEndTrim = ((MaterialProgressDrawable.MAX_PROGRESS_ARC - minProgressArc) * MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation((f - 0.5f) / 0.5f)) + f2;
                }
                MaterialProgressDrawable.this.setProgressRotation((0.25f * f) + f4);
                float f5 = MaterialProgressDrawable.this.mRotationCount / MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
                MaterialProgressDrawable.this.setRotation((f5 * MaterialProgressDrawable.FULL_ROTATION) + (216.0f * f));
            }
        };
        r1.setRepeatCount(-1);
        r1.setRepeatMode(1);
        r1.setInterpolator(LINEAR_INTERPOLATOR);
        r1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.mStartTrim = ring.mEndTrim;
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.mFinishing = false;
                    animation.setDuration(1332);
                    MaterialProgressDrawable.this.showArrow(false);
                    return;
                }
                MaterialProgressDrawable.this.mRotationCount = (MaterialProgressDrawable.this.mRotationCount + 1.0f) % MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
            }

            public void onAnimationStart(Animation animation) {
                MaterialProgressDrawable.this.mRotationCount = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        });
        this.mAnimation = r1;
    }

    /* access modifiers changed from: package-private */
    public void applyFinishTranslation(float f, Ring ring) {
        updateRingColor(f, ring);
        float minProgressArc = getMinProgressArc(ring);
        setStartEndTrim((((ring.mStartingEndTrim - minProgressArc) - ring.mStartingStartTrim) * f) + ring.mStartingStartTrim, ring.mStartingEndTrim);
        setProgressRotation(((((float) (Math.floor((double) (ring.mStartingRotation / MAX_PROGRESS_ARC)) + 1.0d)) - ring.mStartingRotation) * f) + ring.mStartingRotation);
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public int getAlpha() {
        return this.mRing.mAlpha;
    }

    public int getIntrinsicHeight() {
        return (int) this.mHeight;
    }

    public int getIntrinsicWidth() {
        return (int) this.mWidth;
    }

    /* access modifiers changed from: package-private */
    public float getMinProgressArc(Ring ring) {
        double d = (double) ring.mStrokeWidth;
        double d2 = ring.mRingCenterRadius;
        Double.isNaN(d);
        return (float) Math.toRadians(d / (d2 * 6.283185307179586d));
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        List<Animation> list = this.mAnimators;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Animation animation = list.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void setAlpha(int i) {
        this.mRing.mAlpha = i;
    }

    public void setArrowScale(float f) {
        if (this.mRing.mArrowScale != f) {
            this.mRing.mArrowScale = f;
            invalidateSelf();
        }
    }

    public void setBackgroundColor(@ColorInt int i) {
        this.mRing.mBackgroundColor = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setColorSchemeColors(@ColorInt int... iArr) {
        this.mRing.mColors = iArr;
        this.mRing.setColorIndex(0);
    }

    public void setProgressRotation(float f) {
        this.mRing.mRotation = f;
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public void setRotation(float f) {
        this.mRotation = f;
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f2) {
        this.mRing.mStartTrim = f;
        this.mRing.mEndTrim = f2;
        invalidateSelf();
    }

    public void showArrow(boolean z) {
        if (this.mRing.mShowArrow != z) {
            this.mRing.mShowArrow = z;
            invalidateSelf();
        }
    }

    public void start() {
        this.mAnimation.reset();
        this.mRing.storeOriginals();
        if (this.mRing.mEndTrim != this.mRing.mStartTrim) {
            this.mFinishing = true;
            this.mAnimation.setDuration(666);
            this.mParent.startAnimation(this.mAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimation.setDuration(1332);
        this.mParent.startAnimation(this.mAnimation);
    }

    public void stop() {
        this.mParent.clearAnimation();
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        showArrow(false);
        setRotation(CropImageView.DEFAULT_ASPECT_RATIO);
    }

    /* access modifiers changed from: package-private */
    public void updateRingColor(float f, Ring ring) {
        if (f > COLOR_START_DELAY_OFFSET) {
            ring.mCurrentColor = evaluateColorChange((f - COLOR_START_DELAY_OFFSET) / 0.25f, ring.getStartingColor(), ring.getNextColor());
        }
    }

    public void updateSizes(int i) {
        if (i == 0) {
            setSizeParameters(56, 56, CENTER_RADIUS_LARGE, 3.0f, 12.0f, 6.0f);
        } else {
            setSizeParameters(40, 40, CENTER_RADIUS, STROKE_WIDTH, 10.0f, ARROW_OFFSET_ANGLE);
        }
    }
}
