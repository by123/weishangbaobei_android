package com.scwang.smartrefresh.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import com.scwang.smartrefresh.header.internal.pathview.PathsDrawable;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class TaurusHeader extends InternalAbstract implements RefreshHeader {
    protected static final Interpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    protected static final int ANIMATION_DURATION = 1000;
    protected static final float CENTER_CLOUDS_FINAL_SCALE = 1.0f;
    protected static final float CENTER_CLOUDS_INITIAL_SCALE = 0.8f;
    protected static final int LOADING_ANIMATION_COEFFICIENT = 80;
    protected static final int MAX_WIND_LINE_WIDTH = 300;
    protected static final int MAX_WIND_X_OFFSET = 2000;
    protected static final int MIN_WIND_LINE_WIDTH = 50;
    protected static final int MIN_WIND_X_OFFSET = 1000;
    protected static final int RANDOM_Y_COEFFICIENT = 5;
    protected static final float SCALE_START_PERCENT = 0.5f;
    protected static final float SIDE_CLOUDS_FINAL_SCALE = 1.0f;
    protected static final float SIDE_CLOUDS_INITIAL_SCALE = 0.6f;
    protected static final int SLOW_DOWN_ANIMATION_COEFFICIENT = 6;
    protected static final int WIND_SET_AMOUNT = 10;
    protected static final int X_SIDE_CLOUDS_SLOW_DOWN_COF = 2;
    protected static final int Y_SIDE_CLOUDS_SLOW_DOWN_COF = 4;
    protected static int[] airplaneColors = {-1};
    protected static String[] airplanePaths = {"m23.01,81.48c-0.21,-0.3 -0.38,-0.83 -0.38,-1.19 0,-0.55 0.24,-0.78 1.5,-1.48 1.78,-0.97 2.62,-1.94 2.24,-2.57 -0.57,-0.93 -1.97,-1.24 -11.64,-2.59 -5.35,-0.74 -10.21,-1.44 -10.82,-1.54l-1.09,-0.18 1.19,-0.91c0.99,-0.76 1.38,-0.91 2.35,-0.91 0.64,0 6.39,0.33 12.79,0.74 6.39,0.41 12.09,0.71 12.65,0.67l1.03,-0.07 -1.24,-2.19C30.18,66.77 15.91,42 15.13,40.68l-0.51,-0.87 4.19,-1.26c2.3,-0.69 4.27,-1.26 4.37,-1.26 0.1,0 5.95,3.85 13,8.55 14.69,9.81 17.1,11.31 19.7,12.31 4.63,1.78 6.45,1.69 12.94,-0.64 13.18,-4.73 25.22,-9.13 25.75,-9.4 0.69,-0.36 3.6,1.33 -24.38,-14.22L50.73,23.07 46.74,16.42 42.75,9.77 43.63,8.89c0.83,-0.83 0.91,-0.86 1.46,-0.52 0.32,0.2 3.72,3.09 7.55,6.44 3.83,3.34 7.21,6.16 7.5,6.27 0.29,0.11 13.6,2.82 29.58,6.03 15.98,3.21 31.86,6.4 35.3,7.1l6.26,1.26 3.22,-1.13c41.63,-14.63 67.88,-23.23 85.38,-28 14.83,-4.04 23.75,-4.75 32.07,-2.57 7.04,1.84 9.87,4.88 7.71,8.27 -1.6,2.5 -4.6,4.63 -10.61,7.54 -5.94,2.88 -10.22,4.46 -25.4,9.41 -8.15,2.66 -16.66,5.72 -39.01,14.02 -66.79,24.82 -88.49,31.25 -121.66,36.07 -14.56,2.11 -24.17,2.95 -34.08,2.95 -5.43,0 -5.52,-0.01 -5.89,-0.54z"};
    protected static int[] cloudColors = {-1429742351, -571935747, -131587};
    protected static String[] cloudPaths = {"M551.81,1.01A65.42,65.42 0,0 0,504.38 21.5A50.65,50.65 0,0 0,492.4 20A50.65,50.65 0,0 0,441.75 70.65A50.65,50.65 0,0 0,492.4 121.3A50.65,50.65 0,0 0,511.22 117.64A65.42,65.42 0,0 0,517.45 122L586.25,122A65.42,65.42 0,0 0,599.79 110.78A59.79,59.79 0,0 0,607.81 122L696.34,122A59.79,59.79 0,0 0,711.87 81.9A59.79,59.79 0,0 0,652.07 22.11A59.79,59.79 0,0 0,610.93 38.57A65.42,65.42 0,0 0,551.81 1.01zM246.2,1.71A54.87,54.87 0,0 0,195.14 36.64A46.78,46.78 0,0 0,167.77 27.74A46.78,46.78 0,0 0,120.99 74.52A46.78,46.78 0,0 0,167.77 121.3A46.78,46.78 0,0 0,208.92 96.74A54.87,54.87 0,0 0,246.2 111.45A54.87,54.87 0,0 0,268.71 106.54A39.04,39.04 0,0 0,281.09 122L327.6,122A39.04,39.04 0,0 0,343.38 90.7A39.04,39.04 0,0 0,304.34 51.66A39.04,39.04 0,0 0,300.82 51.85A54.87,54.87 0,0 0,246.2 1.71z", "m506.71,31.37a53.11,53.11 0,0 0,-53.11 53.11,53.11 53.11,0 0,0 15.55,37.5h75.12a53.11,53.11 0,0 0,1.88 -2.01,28.49 28.49,0 0,0 0.81,2.01h212.96a96.72,96.72 0,0 0,-87.09 -54.85,96.72 96.72,0 0,0 -73.14,33.52 28.49,28.49 0,0 0,-26.74 -18.74,28.49 28.49,0 0,0 -13.16,3.23 53.11,53.11 0,0 0,0.03 -0.66,53.11 53.11,0 0,0 -53.11,-53.11zM206.23,31.81a53.81,53.81 0,0 0,-49.99 34.03,74.91 74.91,0 0,0 -47.45,-17 74.91,74.91 0,0 0,-73.54 60.82,31.3 31.3,0 0,0 -10.17,-1.73 31.3,31.3 0,0 0,-26.09 14.05L300.86,121.98a37.63,37.63 0,0 0,0.2 -3.85,37.63 37.63,0 0,0 -37.63,-37.63 37.63,37.63 0,0 0,-3.65 0.21,53.81 53.81,0 0,0 -53.54,-48.9z", "m424.05,36.88a53.46,53.46 0,0 0,-40.89 19.02,53.46 53.46,0 0,0 -1.34,1.76 62.6,62.6 0,0 0,-5.39 -0.27,62.6 62.6,0 0,0 -61.36,50.17 62.6,62.6 0,0 0,-0.53 3.51,15.83 15.83,0 0,0 -10.33,-3.84 15.83,15.83 0,0 0,-8.06 2.23,21.1 21.1,0 0,0 -18.31,-10.67 21.1,21.1 0,0 0,-19.47 12.97,21.81 21.81,0 0,0 -6.56,-1.01 21.81,21.81 0,0 0,-19.09 11.32L522.84,122.07a43.61,43.61 0,0 0,-43.11 -37.35,43.61 43.61,0 0,0 -2.57,0.09 53.46,53.46 0,0 0,-53.11 -47.93zM129.08,38.4a50.29,50.29 0,0 0,-50.29 50.29,50.29 50.29,0 0,0 2.37,15.06 15.48,15.83 0,0 0,-5.87 1.68,15.48 15.83,0 0,0 -0.98,0.58 16.53,16.18 0,0 0,-0.19 -0.21,16.53 16.18,0 0,0 -11.86,-4.91 16.53,16.18 0,0 0,-16.38 14.13,20.05 16.18,0 0,0 -14.97,7.04L223.95,122.07a42.56,42.56 0,0 0,1.14 -9.56,42.56 42.56,0 0,0 -42.56,-42.56 42.56,42.56 0,0 0,-6.58 0.54,50.29 50.29,0 0,0 -0,-0.01 50.29,50.29 0,0 0,-46.88 -32.07zM631.67,82.61a64.01,64.01 0,0 0,-44.9 18.42,26.73 26.73,0 0,0 -10.67,-2.24 26.73,26.73 0,0 0,-22.72 12.71,16.88 16.88,0 0,0 -0.25,-0.12 16.88,16.88 0,0 0,-6.57 -1.33,16.88 16.88,0 0,0 -16.15,12.03h160.36a64.01,64.01 0,0 0,-59.1 -39.46z"};
    protected boolean isRefreshing;
    protected Drawable mAirplane;
    protected Animation mAnimation;
    protected Drawable mCloudCenter;
    protected float mFinishTransformation;
    protected int mHeaderHeight;
    protected boolean mInverseDirection;
    protected float mLastAnimationTime;
    protected float mLoadingAnimationTime;
    protected Matrix mMatrix;
    protected boolean mNewWindSet;
    protected float mPercent;
    protected Random mRandom;
    protected float mWindLineWidth;
    protected Paint mWindPaint;
    protected Map<Float, Float> mWinds;

    protected enum AnimationPart {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }

    public TaurusHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public TaurusHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaurusHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isRefreshing = false;
        DensityUtil densityUtil = new DensityUtil();
        setMinimumHeight(densityUtil.dip2px(100.0f));
        this.mMatrix = new Matrix();
        this.mWinds = new HashMap();
        this.mRandom = new Random();
        this.mWindPaint = new Paint();
        this.mWindPaint.setColor(-1);
        this.mWindPaint.setStrokeWidth((float) densityUtil.dip2px(3.0f));
        this.mWindPaint.setAlpha(50);
        this.mSpinnerStyle = SpinnerStyle.Scale;
        this.mAnimation = new Animation() {
            public void applyTransformation(float f, @NonNull Transformation transformation) {
                TaurusHeader.this.mLoadingAnimationTime = (f / 6.0f) * 80.0f;
                this.invalidate();
            }
        };
        this.mAnimation.setRepeatCount(-1);
        this.mAnimation.setRepeatMode(2);
        this.mAnimation.setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR);
        this.mAnimation.setDuration(1000);
        PathsDrawable pathsDrawable = new PathsDrawable();
        if (!pathsDrawable.parserPaths(airplanePaths)) {
            pathsDrawable.declareOriginal(3, 3, 257, 79);
        }
        pathsDrawable.parserColors(airplaneColors);
        PathsDrawable pathsDrawable2 = new PathsDrawable();
        if (!pathsDrawable2.parserPaths(cloudPaths)) {
            pathsDrawable2.declareOriginal(-1, 1, 761, 121);
        }
        pathsDrawable2.parserColors(cloudColors);
        this.mAirplane = pathsDrawable;
        this.mCloudCenter = pathsDrawable2;
        this.mAirplane.setBounds(0, 0, densityUtil.dip2px(65.0f), densityUtil.dip2px(20.0f));
        this.mCloudCenter.setBounds(0, 0, densityUtil.dip2px(260.0f), densityUtil.dip2px(45.0f));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TaurusHeader);
        int color = obtainStyledAttributes.getColor(R.styleable.TaurusHeader_thPrimaryColor, 0);
        if (color != 0) {
            setBackgroundColor(color);
        } else {
            setBackgroundColor(-15614977);
        }
        obtainStyledAttributes.recycle();
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        this.mPercent = f;
        this.mHeaderHeight = i2;
        if (z) {
            this.mFinishTransformation = CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.isRefreshing = true;
        this.mFinishTransformation = CropImageView.DEFAULT_ASPECT_RATIO;
        startAnimation(this.mAnimation);
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        clearAnimation();
        if (z) {
            startAnimation(new Animation() {
                {
                    super.setDuration(100);
                    super.setInterpolator(new AccelerateInterpolator());
                }

                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    if (f == 1.0f) {
                        TaurusHeader.this.isRefreshing = false;
                    }
                    TaurusHeader.this.mFinishTransformation = f;
                    this.invalidate();
                }
            });
            return 200;
        }
        this.isRefreshing = false;
        return 0;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        setBackgroundColor(iArr[0]);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        float f;
        int width = getWidth();
        int height = getHeight();
        if (this.isRefreshing) {
            while (this.mWinds.size() < 10) {
                double d = (double) this.mHeaderHeight;
                Double.isNaN(d);
                float random = (float) (d / (Math.random() * 5.0d));
                float random2 = random(SocializeConstants.CANCLE_RESULTCODE, 2000);
                if (this.mWinds.size() > 1) {
                    while (true) {
                        f = CropImageView.DEFAULT_ASPECT_RATIO;
                        while (true) {
                            if (f != CropImageView.DEFAULT_ASPECT_RATIO) {
                                break;
                            }
                            double d2 = (double) this.mHeaderHeight;
                            Double.isNaN(d2);
                            float random3 = (float) (d2 / (Math.random() * 5.0d));
                            Iterator<Map.Entry<Float, Float>> it = this.mWinds.entrySet().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (Math.abs(((Float) it.next().getKey()).floatValue() - random3) > ((float) (this.mHeaderHeight / 5))) {
                                        f = random3;
                                    }
                                }
                            }
                        }
                    }
                    random = f;
                    break;
                }
                this.mWinds.put(Float.valueOf(random), Float.valueOf(random2));
                drawWind(canvas, random, random2, width);
            }
            if (this.mWinds.size() >= 10) {
                for (Map.Entry next : this.mWinds.entrySet()) {
                    drawWind(canvas, ((Float) next.getKey()).floatValue(), ((Float) next.getValue()).floatValue(), width);
                }
            }
            if (this.mInverseDirection && this.mNewWindSet) {
                this.mWinds.clear();
                this.mNewWindSet = false;
                this.mWindLineWidth = random(50, 300);
            }
            this.mLastAnimationTime = this.mLoadingAnimationTime;
        }
        drawAirplane(canvas, width, height);
        drawSideClouds(canvas, width, height);
        drawCenterClouds(canvas, width, height);
        super.dispatchDraw(canvas);
    }

    private void drawWind(Canvas canvas, float f, float f2, int i) {
        float f3 = (float) i;
        float f4 = (f3 + f2) / 13.0f;
        float f5 = this.mLoadingAnimationTime;
        if (this.mLastAnimationTime - this.mLoadingAnimationTime > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mInverseDirection = true;
            f5 = 13.0f - this.mLoadingAnimationTime;
        } else {
            this.mNewWindSet = true;
            this.mInverseDirection = false;
        }
        float f6 = ((f3 - (f5 * f4)) + f2) - this.mWindLineWidth;
        canvas.drawLine(f6, f, f6 + this.mWindLineWidth, f, this.mWindPaint);
    }

    private void drawSideClouds(Canvas canvas, int i, int i2) {
        Matrix matrix = this.mMatrix;
        matrix.reset();
        Drawable drawable = this.mCloudCenter;
        Drawable drawable2 = this.mCloudCenter;
        float min = Math.min(1.0f, Math.abs(this.mPercent));
        if (isInEditMode()) {
            this.mHeaderHeight = i2;
            min = 1.0f;
        }
        float f = min - SCALE_START_PERCENT;
        float f2 = SIDE_CLOUDS_INITIAL_SCALE;
        if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = SIDE_CLOUDS_INITIAL_SCALE + ((f / SCALE_START_PERCENT) * 0.39999998f);
        }
        float f3 = ((float) this.mHeaderHeight) * (1.0f - min);
        float width = (float) (0 - (drawable.getBounds().width() / 2));
        float width2 = (float) (i - (drawable2.getBounds().width() / 2));
        if (this.isRefreshing) {
            if (checkCurrentAnimationPart(AnimationPart.FIRST)) {
                width -= (getAnimationPartValue(AnimationPart.FIRST) * 2.0f) / 4.0f;
                width2 += getAnimationPartValue(AnimationPart.FIRST) / 2.0f;
            } else if (checkCurrentAnimationPart(AnimationPart.SECOND)) {
                width -= (getAnimationPartValue(AnimationPart.SECOND) * 2.0f) / 4.0f;
                width2 += getAnimationPartValue(AnimationPart.SECOND) / 2.0f;
            } else if (checkCurrentAnimationPart(AnimationPart.THIRD)) {
                width -= getAnimationPartValue(AnimationPart.THIRD) / 4.0f;
                width2 += (getAnimationPartValue(AnimationPart.THIRD) * 2.0f) / 2.0f;
            } else if (checkCurrentAnimationPart(AnimationPart.FOURTH)) {
                width -= getAnimationPartValue(AnimationPart.FOURTH) / 2.0f;
                width2 += (getAnimationPartValue(AnimationPart.FOURTH) * 2.0f) / 4.0f;
            }
        }
        float f4 = (float) (i2 + 2);
        float height = (((float) drawable.getBounds().height()) * f2) + f3 < f4 ? f4 - (((float) drawable.getBounds().height()) * f2) : f3;
        if ((((float) drawable2.getBounds().height()) * f2) + f3 < f4) {
            f3 = f4 - (((float) drawable2.getBounds().height()) * f2);
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(width, height);
        matrix.postScale(f2, f2, (float) ((drawable.getBounds().width() * 3) / 4), (float) drawable.getBounds().height());
        canvas.concat(matrix);
        drawable.setAlpha(100);
        drawable.draw(canvas);
        drawable.setAlpha(255);
        canvas.restoreToCount(saveCount);
        canvas.save();
        canvas.translate(width2, f3);
        matrix.postScale(f2, f2, CropImageView.DEFAULT_ASPECT_RATIO, (float) drawable2.getBounds().height());
        canvas.concat(matrix);
        drawable2.setAlpha(100);
        drawable2.draw(canvas);
        drawable2.setAlpha(255);
        canvas.restoreToCount(saveCount);
    }

    private void drawCenterClouds(Canvas canvas, int i, int i2) {
        float f;
        boolean z;
        float f2;
        float animationPartValue;
        Matrix matrix = this.mMatrix;
        matrix.reset();
        float min = Math.min(1.0f, Math.abs(this.mPercent));
        if (isInEditMode()) {
            this.mHeaderHeight = i2;
            min = 1.0f;
        }
        boolean z2 = true;
        if (this.mPercent > 1.0f) {
            f = Math.abs(1.0f - this.mPercent);
            z = true;
        } else {
            z = false;
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float f3 = min - SCALE_START_PERCENT;
        float f4 = CENTER_CLOUDS_INITIAL_SCALE;
        if (f3 > CropImageView.DEFAULT_ASPECT_RATIO) {
            f4 = CENTER_CLOUDS_INITIAL_SCALE + ((f3 / SCALE_START_PERCENT) * 0.19999999f);
        }
        float f5 = ((float) this.mHeaderHeight) * min;
        float height = (float) (this.mHeaderHeight - (this.mCloudCenter.getBounds().height() / 2));
        if (f5 > height) {
            f2 = f5 - height;
        } else {
            f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            z2 = false;
        }
        float width = (float) ((i / 2) - (this.mCloudCenter.getBounds().width() / 2));
        float height2 = f5 - (z2 ? ((float) (this.mCloudCenter.getBounds().height() / 2)) + f2 : (float) (this.mCloudCenter.getBounds().height() / 2));
        float f6 = z ? (f / 4.0f) + f4 : f4;
        float f7 = z ? (f / 2.0f) + f4 : f4;
        if (this.isRefreshing && !z) {
            if (checkCurrentAnimationPart(AnimationPart.FIRST)) {
                animationPartValue = f4 - ((getAnimationPartValue(AnimationPart.FIRST) / 80.0f) / 8.0f);
            } else if (checkCurrentAnimationPart(AnimationPart.SECOND)) {
                animationPartValue = f4 - ((getAnimationPartValue(AnimationPart.SECOND) / 80.0f) / 8.0f);
            } else if (checkCurrentAnimationPart(AnimationPart.THIRD)) {
                animationPartValue = f4 + ((getAnimationPartValue(AnimationPart.THIRD) / 80.0f) / 6.0f);
            } else {
                if (checkCurrentAnimationPart(AnimationPart.FOURTH)) {
                    animationPartValue = f4 + ((getAnimationPartValue(AnimationPart.FOURTH) / 80.0f) / 6.0f);
                }
                f7 = f6;
            }
            f6 = animationPartValue;
            f7 = f6;
        }
        matrix.postScale(f6, f7, (float) (this.mCloudCenter.getBounds().width() / 2), CropImageView.DEFAULT_ASPECT_RATIO);
        float f8 = (float) (i2 + 2);
        if ((((float) this.mCloudCenter.getBounds().height()) * f7) + height2 < f8) {
            height2 = f8 - (f7 * ((float) this.mCloudCenter.getBounds().height()));
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(width, height2);
        canvas.concat(matrix);
        this.mCloudCenter.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    private void drawAirplane(Canvas canvas, int i, int i2) {
        float f;
        Matrix matrix = this.mMatrix;
        matrix.reset();
        float f2 = this.mPercent;
        if (isInEditMode()) {
            this.mHeaderHeight = i2;
            f2 = 1.0f;
        }
        if (f2 > 1.0f) {
            f = ((float) (1.0d - Math.pow(100.0d, (double) ((-(f2 - 1.0f)) / 2.0f)))) * 20.0f;
            f2 = 1.0f;
        } else {
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float width = ((((float) i) * f2) / 2.0f) - ((float) (this.mAirplane.getBounds().width() / 2));
        float height = (((float) this.mHeaderHeight) * (1.0f - (f2 / 2.0f))) - ((float) (this.mAirplane.getBounds().height() / 2));
        if (this.mFinishTransformation > CropImageView.DEFAULT_ASPECT_RATIO) {
            height += (CropImageView.DEFAULT_ASPECT_RATIO - height) * this.mFinishTransformation;
            width += (((float) (i + this.mAirplane.getBounds().width())) - width) * this.mFinishTransformation;
        }
        if (this.isRefreshing) {
            if (checkCurrentAnimationPart(AnimationPart.FIRST)) {
                height -= getAnimationPartValue(AnimationPart.FIRST);
            } else if (checkCurrentAnimationPart(AnimationPart.SECOND)) {
                height -= getAnimationPartValue(AnimationPart.SECOND);
            } else if (checkCurrentAnimationPart(AnimationPart.THIRD)) {
                height += getAnimationPartValue(AnimationPart.THIRD);
            } else if (checkCurrentAnimationPart(AnimationPart.FOURTH)) {
                height += getAnimationPartValue(AnimationPart.FOURTH);
            }
        }
        if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
            matrix.postRotate(f, (float) (this.mAirplane.getBounds().width() / 2), (float) (this.mAirplane.getBounds().height() / 2));
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(width, height);
        canvas.concat(matrix);
        this.mAirplane.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    private float random(int i, int i2) {
        return (float) (this.mRandom.nextInt((i2 - i) + 1) + i);
    }

    private float getAnimationPartValue(AnimationPart animationPart) {
        switch (animationPart) {
            case FIRST:
                return this.mLoadingAnimationTime;
            case SECOND:
                return ((float) getAnimationTimePart(AnimationPart.FOURTH)) - (this.mLoadingAnimationTime - ((float) getAnimationTimePart(AnimationPart.FOURTH)));
            case THIRD:
                return this.mLoadingAnimationTime - ((float) getAnimationTimePart(AnimationPart.SECOND));
            case FOURTH:
                return ((float) getAnimationTimePart(AnimationPart.THIRD)) - (this.mLoadingAnimationTime - ((float) getAnimationTimePart(AnimationPart.FOURTH)));
            default:
                return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    private boolean checkCurrentAnimationPart(AnimationPart animationPart) {
        switch (animationPart) {
            case FIRST:
                if (this.mLoadingAnimationTime < ((float) getAnimationTimePart(AnimationPart.FOURTH))) {
                    return true;
                }
                return false;
            case SECOND:
            case THIRD:
                if (this.mLoadingAnimationTime < ((float) getAnimationTimePart(animationPart))) {
                    return true;
                }
                return false;
            case FOURTH:
                if (this.mLoadingAnimationTime > ((float) getAnimationTimePart(AnimationPart.THIRD))) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private int getAnimationTimePart(AnimationPart animationPart) {
        switch (animationPart) {
            case SECOND:
                return 40;
            case THIRD:
                return getAnimationTimePart(AnimationPart.FOURTH) * 3;
            case FOURTH:
                return 20;
            default:
                return 0;
        }
    }
}
