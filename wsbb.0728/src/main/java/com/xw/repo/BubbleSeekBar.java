package com.xw.repo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.xw.repo.bubbleseekbar.R;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;

public class BubbleSeekBar extends View {
    static final int NONE = -1;
    float dx;
    /* access modifiers changed from: private */
    public boolean isAlwaysShowBubble;
    private boolean isAutoAdjustSectionMark;
    private boolean isFloatType;
    /* access modifiers changed from: private */
    public boolean isHideBubble;
    private boolean isRtl;
    private boolean isSeekBySection;
    private boolean isSeekStepSection;
    /* access modifiers changed from: private */
    public boolean isShowProgressInFloat;
    private boolean isShowSectionMark;
    private boolean isShowSectionText;
    private boolean isShowThumbText;
    /* access modifiers changed from: private */
    public boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    /* access modifiers changed from: private */
    public boolean isTouchToSeekAnimEnd;
    private long mAlwaysShowBubbleDelay;
    /* access modifiers changed from: private */
    public long mAnimDuration;
    private float mBubbleCenterRawSolidX;
    private float mBubbleCenterRawSolidY;
    /* access modifiers changed from: private */
    public float mBubbleCenterRawX;
    /* access modifiers changed from: private */
    public int mBubbleColor;
    /* access modifiers changed from: private */
    public int mBubbleRadius;
    /* access modifiers changed from: private */
    public int mBubbleTextColor;
    /* access modifiers changed from: private */
    public int mBubbleTextSize;
    /* access modifiers changed from: private */
    public BubbleView mBubbleView;
    private float mDelta;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams mLayoutParams;
    private float mLeft;
    private float mMax;
    private float mMin;
    private Paint mPaint;
    private int[] mPoint;
    private float mPreSecValue;
    private float mPreThumbCenterX;
    /* access modifiers changed from: private */
    public float mProgress;
    /* access modifiers changed from: private */
    public OnProgressChangedListener mProgressListener;
    private Rect mRectText;
    private float mRight;
    private int mSecondTrackColor;
    private int mSecondTrackSize;
    private int mSectionCount;
    private float mSectionOffset;
    private SparseArray<String> mSectionTextArray;
    private int mSectionTextColor;
    private int mSectionTextInterval;
    private int mSectionTextPosition;
    private int mSectionTextSize;
    private float mSectionValue;
    private int mTextSpace;
    /* access modifiers changed from: private */
    public float mThumbCenterX;
    private int mThumbColor;
    private int mThumbRadius;
    private int mThumbRadiusOnDragging;
    private int mThumbTextColor;
    private int mThumbTextSize;
    private int mTrackColor;
    private float mTrackLength;
    private int mTrackSize;
    /* access modifiers changed from: private */
    public WindowManager mWindowManager;
    /* access modifiers changed from: private */
    public boolean triggerBubbleShowing;
    private boolean triggerSeekBySection;

    private class BubbleView extends View {
        private Paint mBubblePaint;
        private Path mBubblePath;
        private RectF mBubbleRectF;
        private String mProgressText;
        private Rect mRect;

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context) {
            this(bubbleSeekBar, context, (AttributeSet) null);
        }

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        BubbleView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mProgressText = "";
            this.mBubblePaint = new Paint();
            this.mBubblePaint.setAntiAlias(true);
            this.mBubblePaint.setTextAlign(Paint.Align.CENTER);
            this.mBubblePath = new Path();
            this.mBubbleRectF = new RectF();
            this.mRect = new Rect();
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.mBubblePath.reset();
            float measuredWidth = ((float) getMeasuredWidth()) / 2.0f;
            float measuredHeight = ((float) getMeasuredHeight()) - (((float) BubbleSeekBar.this.mBubbleRadius) / 3.0f);
            this.mBubblePath.moveTo(measuredWidth, measuredHeight);
            double measuredWidth2 = (double) (((float) getMeasuredWidth()) / 2.0f);
            double access$2000 = (double) BubbleSeekBar.this.mBubbleRadius;
            Double.isNaN(access$2000);
            Double.isNaN(measuredWidth2);
            float sqrt = (float) (measuredWidth2 - ((Math.sqrt(3.0d) / 2.0d) * access$2000));
            float access$20002 = ((float) BubbleSeekBar.this.mBubbleRadius) * 1.5f;
            this.mBubblePath.quadTo(sqrt - ((float) BubbleUtils.dp2px(2)), access$20002 - ((float) BubbleUtils.dp2px(2)), sqrt, access$20002);
            this.mBubblePath.arcTo(this.mBubbleRectF, 150.0f, 240.0f);
            double measuredWidth3 = (double) (((float) getMeasuredWidth()) / 2.0f);
            double access$20003 = (double) BubbleSeekBar.this.mBubbleRadius;
            Double.isNaN(access$20003);
            Double.isNaN(measuredWidth3);
            this.mBubblePath.quadTo(((float) (measuredWidth3 + ((Math.sqrt(3.0d) / 2.0d) * access$20003))) + ((float) BubbleUtils.dp2px(2)), access$20002 - ((float) BubbleUtils.dp2px(2)), measuredWidth, measuredHeight);
            this.mBubblePath.close();
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleColor);
            canvas.drawPath(this.mBubblePath, this.mBubblePaint);
            this.mBubblePaint.setTextSize((float) BubbleSeekBar.this.mBubbleTextSize);
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleTextColor);
            this.mBubblePaint.getTextBounds(this.mProgressText, 0, this.mProgressText.length(), this.mRect);
            Paint.FontMetrics fontMetrics = this.mBubblePaint.getFontMetrics();
            canvas.drawText(this.mProgressText, ((float) getMeasuredWidth()) / 2.0f, (((float) BubbleSeekBar.this.mBubbleRadius) + ((fontMetrics.descent - fontMetrics.ascent) / 2.0f)) - fontMetrics.descent, this.mBubblePaint);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            setMeasuredDimension(BubbleSeekBar.this.mBubbleRadius * 3, BubbleSeekBar.this.mBubbleRadius * 3);
            this.mBubbleRectF.set((((float) getMeasuredWidth()) / 2.0f) - ((float) BubbleSeekBar.this.mBubbleRadius), CropImageView.DEFAULT_ASPECT_RATIO, (((float) getMeasuredWidth()) / 2.0f) + ((float) BubbleSeekBar.this.mBubbleRadius), (float) (BubbleSeekBar.this.mBubbleRadius * 2));
        }

        /* access modifiers changed from: package-private */
        public void setProgressText(String str) {
            if (str != null && !this.mProgressText.equals(str)) {
                this.mProgressText = str;
                invalidate();
            }
        }
    }

    public interface CustomSectionTextArray {
        @NonNull
        SparseArray<String> onCustomize(int i, @NonNull SparseArray<String> sparseArray);
    }

    public interface OnProgressChangedListener {
        void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f);

        void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);

        void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);
    }

    public static abstract class OnProgressChangedListenerAdapter implements OnProgressChangedListener {
        public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
        }

        public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }

        public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TextPosition {
        public static final int BELOW_SECTION_MARK = 2;
        public static final int BOTTOM_SIDES = 1;
        public static final int SIDES = 0;
    }

    public BubbleSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSectionTextPosition = -1;
        this.mSectionTextArray = new SparseArray<>();
        this.mPoint = new int[2];
        this.isTouchToSeekAnimEnd = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BubbleSeekBar, i, 0);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_min, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_max, 100.0f);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_progress, this.mMin);
        this.isFloatType = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_is_float_type, false);
        this.mTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_track_size, BubbleUtils.dp2px(2));
        this.mSecondTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_second_track_size, this.mTrackSize + BubbleUtils.dp2px(2));
        this.mThumbRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius, this.mSecondTrackSize + BubbleUtils.dp2px(2));
        this.mThumbRadiusOnDragging = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius_on_dragging, this.mSecondTrackSize * 2);
        this.mSectionCount = obtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_count, 10);
        this.mTrackColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_track_color, ContextCompat.getColor(context, R.color.colorPrimary));
        this.mSecondTrackColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_second_track_color, ContextCompat.getColor(context, R.color.colorAccent));
        this.mThumbColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_thumb_color, this.mSecondTrackColor);
        this.isShowSectionText = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_section_text, false);
        this.mSectionTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_section_text_size, BubbleUtils.sp2px(14));
        this.mSectionTextColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_section_text_color, this.mTrackColor);
        this.isSeekStepSection = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_seek_step_section, false);
        this.isSeekBySection = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_seek_by_section, false);
        int integer = obtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_text_position, -1);
        if (integer == 0) {
            this.mSectionTextPosition = 0;
        } else if (integer == 1) {
            this.mSectionTextPosition = 1;
        } else if (integer == 2) {
            this.mSectionTextPosition = 2;
        } else {
            this.mSectionTextPosition = -1;
        }
        this.mSectionTextInterval = obtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_text_interval, 1);
        this.isShowThumbText = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_thumb_text, false);
        this.mThumbTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_text_size, BubbleUtils.sp2px(14));
        this.mThumbTextColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_thumb_text_color, this.mSecondTrackColor);
        this.mBubbleColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_bubble_color, this.mSecondTrackColor);
        this.mBubbleTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_bubble_text_size, BubbleUtils.sp2px(14));
        this.mBubbleTextColor = obtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_bubble_text_color, -1);
        this.isShowSectionMark = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_section_mark, false);
        this.isAutoAdjustSectionMark = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_auto_adjust_section_mark, false);
        this.isShowProgressInFloat = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_progress_in_float, false);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_anim_duration, -1);
        this.mAnimDuration = integer2 < 0 ? 200 : (long) integer2;
        this.isTouchToSeek = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_touch_to_seek, false);
        this.isAlwaysShowBubble = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_always_show_bubble, false);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_always_show_bubble_delay, 0);
        this.mAlwaysShowBubbleDelay = integer3 < 0 ? 0 : (long) integer3;
        this.isHideBubble = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_hide_bubble, false);
        this.isRtl = obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_rtl, false);
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_android_enabled, isEnabled()));
        obtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mRectText = new Rect();
        this.mTextSpace = BubbleUtils.dp2px(2);
        initConfigByPriority();
        if (!this.isHideBubble) {
            this.mWindowManager = (WindowManager) context.getSystemService("window");
            this.mBubbleView = new BubbleView(this, context);
            this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
            this.mLayoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams.gravity = 8388659;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = -3;
            this.mLayoutParams.flags = 524328;
            if (BubbleUtils.isMIUI() || Build.VERSION.SDK_INT >= 25) {
                this.mLayoutParams.type = 2;
            } else {
                this.mLayoutParams.type = 2005;
            }
            calculateRadiusOfBubble();
        }
    }

    /* access modifiers changed from: private */
    public void autoAdjustSection() {
        ValueAnimator valueAnimator;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        int i = 0;
        float f2 = 0.0f;
        while (i <= this.mSectionCount) {
            f2 = (((float) i) * this.mSectionOffset) + this.mLeft;
            if (f2 <= this.mThumbCenterX && this.mThumbCenterX - f2 <= this.mSectionOffset) {
                break;
            }
            i++;
        }
        boolean z = BigDecimal.valueOf((double) this.mThumbCenterX).setScale(1, 4).floatValue() == f2;
        AnimatorSet animatorSet = new AnimatorSet();
        if (!z) {
            valueAnimator = this.mThumbCenterX - f2 <= this.mSectionOffset / 2.0f ? ValueAnimator.ofFloat(new float[]{this.mThumbCenterX, f2}) : ValueAnimator.ofFloat(new float[]{this.mThumbCenterX, (((float) (i + 1)) * this.mSectionOffset) + this.mLeft});
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = BubbleSeekBar.this.mThumbCenterX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float unused2 = BubbleSeekBar.this.mProgress = BubbleSeekBar.this.calculateProgress();
                    if (!BubbleSeekBar.this.isHideBubble) {
                        float unused3 = BubbleSeekBar.this.mBubbleCenterRawX = BubbleSeekBar.this.calculateCenterRawXofBubbleView();
                        BubbleSeekBar.this.mLayoutParams.x = (int) (BubbleSeekBar.this.mBubbleCenterRawX + 0.5f);
                        if (BubbleSeekBar.this.mBubbleView.getParent() != null) {
                            BubbleSeekBar.this.mWindowManager.updateViewLayout(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
                        }
                        BubbleSeekBar.this.mBubbleView.setProgressText(BubbleSeekBar.this.isShowProgressInFloat ? String.valueOf(BubbleSeekBar.this.getProgressFloat()) : String.valueOf(BubbleSeekBar.this.getProgress()));
                    } else {
                        float unused4 = BubbleSeekBar.this.processProgress();
                    }
                    BubbleSeekBar.this.invalidate();
                    if (BubbleSeekBar.this.mProgressListener != null) {
                        BubbleSeekBar.this.mProgressListener.onProgressChanged(BubbleSeekBar.this, BubbleSeekBar.this.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                    }
                }
            });
        } else {
            valueAnimator = null;
        }
        if (!this.isHideBubble) {
            BubbleView bubbleView = this.mBubbleView;
            Property property = View.ALPHA;
            if (this.isAlwaysShowBubble) {
                f = 1.0f;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(bubbleView, property, new float[]{f});
            if (z) {
                animatorSet.setDuration(this.mAnimDuration).play(ofFloat);
            } else {
                animatorSet.setDuration(this.mAnimDuration).playTogether(new Animator[]{valueAnimator, ofFloat});
            }
        } else if (!z) {
            animatorSet.setDuration(this.mAnimDuration).playTogether(new Animator[]{valueAnimator});
        }
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                float unused = BubbleSeekBar.this.mProgress = BubbleSeekBar.this.calculateProgress();
                boolean unused2 = BubbleSeekBar.this.isThumbOnDragging = false;
                boolean unused3 = BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
            }

            public void onAnimationEnd(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                float unused = BubbleSeekBar.this.mProgress = BubbleSeekBar.this.calculateProgress();
                boolean unused2 = BubbleSeekBar.this.isThumbOnDragging = false;
                boolean unused3 = BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
                if (BubbleSeekBar.this.mProgressListener != null) {
                    BubbleSeekBar.this.mProgressListener.getProgressOnFinally(BubbleSeekBar.this, BubbleSeekBar.this.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                }
            }
        });
        animatorSet.start();
    }

    private float calThumbCxWhenSeekStepSection(float f) {
        if (f <= this.mLeft) {
            return this.mLeft;
        }
        if (f >= this.mRight) {
            return this.mRight;
        }
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        int i = 0;
        while (i <= this.mSectionCount) {
            f2 = (((float) i) * this.mSectionOffset) + this.mLeft;
            if (f2 <= f && f - f2 <= this.mSectionOffset) {
                break;
            }
            i++;
        }
        if (f - f2 <= this.mSectionOffset / 2.0f) {
            return f2;
        }
        return this.mLeft + (((float) (i + 1)) * this.mSectionOffset);
    }

    /* access modifiers changed from: private */
    public float calculateCenterRawXofBubbleView() {
        return this.isRtl ? this.mBubbleCenterRawSolidX - ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta) : this.mBubbleCenterRawSolidX + ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta);
    }

    /* access modifiers changed from: private */
    public float calculateProgress() {
        return this.isRtl ? (((this.mRight - this.mThumbCenterX) * this.mDelta) / this.mTrackLength) + this.mMin : (((this.mThumbCenterX - this.mLeft) * this.mDelta) / this.mTrackLength) + this.mMin;
    }

    private void calculateRadiusOfBubble() {
        String float2String;
        String float2String2;
        this.mPaint.setTextSize((float) this.mBubbleTextSize);
        if (this.isShowProgressInFloat) {
            float2String = float2String(this.isRtl ? this.mMax : this.mMin);
        } else {
            float2String = this.isRtl ? this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax) : this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
        }
        this.mPaint.getTextBounds(float2String, 0, float2String.length(), this.mRectText);
        int width = this.mRectText.width();
        int i = this.mTextSpace;
        if (this.isShowProgressInFloat) {
            float2String2 = float2String(this.isRtl ? this.mMin : this.mMax);
        } else {
            float2String2 = this.isRtl ? this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin) : this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
        }
        this.mPaint.getTextBounds(float2String2, 0, float2String2.length(), this.mRectText);
        int width2 = this.mRectText.width();
        int i2 = this.mTextSpace;
        this.mBubbleRadius = BubbleUtils.dp2px(14);
        this.mBubbleRadius = Math.max(this.mBubbleRadius, Math.max((width + (i * 2)) >> 1, (width2 + (i2 * 2)) >> 1)) + this.mTextSpace;
    }

    private String float2String(float f) {
        return String.valueOf(formatFloat(f));
    }

    private float formatFloat(float f) {
        return BigDecimal.valueOf((double) f).setScale(1, 4).floatValue();
    }

    /* access modifiers changed from: private */
    public void hideBubble() {
        if (this.mBubbleView != null) {
            this.mBubbleView.setVisibility(8);
            if (this.mBubbleView.getParent() != null) {
                this.mWindowManager.removeViewImmediate(this.mBubbleView);
            }
        }
    }

    private void initConfigByPriority() {
        if (this.mMin == this.mMax) {
            this.mMin = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mMax = 100.0f;
        }
        if (this.mMin > this.mMax) {
            float f = this.mMax;
            this.mMax = this.mMin;
            this.mMin = f;
        }
        if (this.mProgress < this.mMin) {
            this.mProgress = this.mMin;
        }
        if (this.mProgress > this.mMax) {
            this.mProgress = this.mMax;
        }
        if (this.mSecondTrackSize < this.mTrackSize) {
            this.mSecondTrackSize = this.mTrackSize + BubbleUtils.dp2px(2);
        }
        if (this.mThumbRadius <= this.mSecondTrackSize) {
            this.mThumbRadius = this.mSecondTrackSize + BubbleUtils.dp2px(2);
        }
        if (this.mThumbRadiusOnDragging <= this.mSecondTrackSize) {
            this.mThumbRadiusOnDragging = this.mSecondTrackSize * 2;
        }
        if (this.mSectionCount <= 0) {
            this.mSectionCount = 10;
        }
        this.mDelta = this.mMax - this.mMin;
        this.mSectionValue = this.mDelta / ((float) this.mSectionCount);
        if (this.mSectionValue < 1.0f) {
            this.isFloatType = true;
        }
        if (this.isFloatType) {
            this.isShowProgressInFloat = true;
        }
        if (this.mSectionTextPosition != -1) {
            this.isShowSectionText = true;
        }
        if (this.isShowSectionText) {
            if (this.mSectionTextPosition == -1) {
                this.mSectionTextPosition = 0;
            }
            if (this.mSectionTextPosition == 2) {
                this.isShowSectionMark = true;
            }
        }
        if (this.mSectionTextInterval < 1) {
            this.mSectionTextInterval = 1;
        }
        initSectionTextArray();
        if (this.isSeekStepSection) {
            this.isSeekBySection = false;
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isAutoAdjustSectionMark && !this.isShowSectionMark) {
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isSeekBySection) {
            this.mPreSecValue = this.mMin;
            if (this.mProgress != this.mMin) {
                this.mPreSecValue = this.mSectionValue;
            }
            this.isShowSectionMark = true;
            this.isAutoAdjustSectionMark = true;
        }
        if (this.isHideBubble) {
            this.isAlwaysShowBubble = false;
        }
        if (this.isAlwaysShowBubble) {
            setProgress(this.mProgress);
        }
        this.mThumbTextSize = (this.isFloatType || this.isSeekBySection || (this.isShowSectionText && this.mSectionTextPosition == 2)) ? this.mSectionTextSize : this.mThumbTextSize;
    }

    private void initSectionTextArray() {
        int i;
        String str;
        boolean z = true;
        boolean z2 = this.mSectionTextPosition == 2;
        if (this.mSectionTextInterval <= 1 || this.mSectionCount % 2 != 0) {
            i = 0;
            z = false;
        } else {
            i = 0;
        }
        while (i <= this.mSectionCount) {
            float f = this.isRtl ? this.mMax - (this.mSectionValue * ((float) i)) : this.mMin + (this.mSectionValue * ((float) i));
            if (z2) {
                if (z) {
                    if (i % this.mSectionTextInterval != 0) {
                        i++;
                    } else {
                        f = this.isRtl ? this.mMax - (this.mSectionValue * ((float) i)) : this.mMin + (this.mSectionValue * ((float) i));
                    }
                }
            } else if (!(i == 0 || i == this.mSectionCount)) {
                i++;
            }
            SparseArray<String> sparseArray = this.mSectionTextArray;
            if (this.isFloatType) {
                str = float2String(f);
            } else {
                str = ((int) f) + "";
            }
            sparseArray.put(i, str);
            i++;
        }
    }

    private boolean isThumbTouched(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f = (this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin);
        float f2 = this.isRtl ? this.mRight - f : f + this.mLeft;
        float measuredHeight = ((float) getMeasuredHeight()) / 2.0f;
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (this.mLeft + ((float) BubbleUtils.dp2px(8))) * (this.mLeft + ((float) BubbleUtils.dp2px(8)));
    }

    private boolean isTrackTouched(MotionEvent motionEvent) {
        return isEnabled() && motionEvent.getX() >= ((float) getPaddingLeft()) && motionEvent.getX() <= ((float) (getMeasuredWidth() - getPaddingRight())) && motionEvent.getY() >= ((float) getPaddingTop()) && motionEvent.getY() <= ((float) (getMeasuredHeight() - getPaddingBottom()));
    }

    private void locatePositionInWindow() {
        Window window;
        getLocationInWindow(this.mPoint);
        ViewParent parent = getParent();
        if (parent instanceof View) {
            View view = (View) parent;
            if (view.getMeasuredWidth() > 0) {
                int[] iArr = this.mPoint;
                iArr[0] = iArr[0] % view.getMeasuredWidth();
            }
        }
        if (this.isRtl) {
            this.mBubbleCenterRawSolidX = (((float) this.mPoint[0]) + this.mRight) - (((float) this.mBubbleView.getMeasuredWidth()) / 2.0f);
        } else {
            this.mBubbleCenterRawSolidX = (((float) this.mPoint[0]) + this.mLeft) - (((float) this.mBubbleView.getMeasuredWidth()) / 2.0f);
        }
        this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        this.mBubbleCenterRawSolidY = (float) (this.mPoint[1] - this.mBubbleView.getMeasuredHeight());
        this.mBubbleCenterRawSolidY -= (float) BubbleUtils.dp2px(24);
        if (BubbleUtils.isMIUI()) {
            this.mBubbleCenterRawSolidY -= (float) BubbleUtils.dp2px(4);
        }
        Context context = getContext();
        if ((context instanceof Activity) && (window = ((Activity) context).getWindow()) != null && (window.getAttributes().flags & WXMediaMessage.DESCRIPTION_LENGTH_LIMIT) != 0) {
            Resources system = Resources.getSystem();
            this.mBubbleCenterRawSolidY = ((float) system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"))) + this.mBubbleCenterRawSolidY;
        }
    }

    /* access modifiers changed from: private */
    public float processProgress() {
        float f = this.mProgress;
        if (!this.isSeekBySection || !this.triggerSeekBySection) {
            return f;
        }
        float f2 = this.mSectionValue / 2.0f;
        if (this.isTouchToSeek) {
            if (f == this.mMin) {
                return f;
            }
            if (f == this.mMax) {
                return f;
            }
            for (int i = 0; i <= this.mSectionCount; i++) {
                float f3 = ((float) i) * this.mSectionValue;
                if (f3 < f && this.mSectionValue + f3 >= f) {
                    return f2 + f3 > f ? f3 : this.mSectionValue + f3;
                }
            }
        }
        if (f >= this.mPreSecValue) {
            if (f < this.mPreSecValue + f2) {
                return this.mPreSecValue;
            }
            this.mPreSecValue += this.mSectionValue;
            return this.mPreSecValue;
        } else if (f >= this.mPreSecValue - f2) {
            return this.mPreSecValue;
        } else {
            this.mPreSecValue -= this.mSectionValue;
            return this.mPreSecValue;
        }
    }

    /* access modifiers changed from: private */
    public void showBubble() {
        if (this.mBubbleView != null && this.mBubbleView.getParent() == null) {
            this.mLayoutParams.x = (int) (this.mBubbleCenterRawX + 0.5f);
            this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
            this.mBubbleView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            this.mBubbleView.setVisibility(0);
            this.mBubbleView.animate().alpha(1.0f).setDuration(this.isTouchToSeek ? 0 : this.mAnimDuration).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    BubbleSeekBar.this.mWindowManager.addView(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
                }
            }).start();
            this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
        }
    }

    public void correctOffsetWhenContainerOnScrolling() {
        if (!this.isHideBubble) {
            locatePositionInWindow();
            if (this.mBubbleView.getParent() == null) {
                return;
            }
            if (this.isAlwaysShowBubble) {
                this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
                this.mWindowManager.updateViewLayout(this.mBubbleView, this.mLayoutParams);
                return;
            }
            postInvalidate();
        }
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public OnProgressChangedListener getOnProgressChangedListener() {
        return this.mProgressListener;
    }

    public int getProgress() {
        return Math.round(processProgress());
    }

    public float getProgressFloat() {
        return formatFloat(processProgress());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        hideBubble();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        super.onDraw(canvas);
        float paddingLeft = (float) getPaddingLeft();
        float measuredWidth = (float) (getMeasuredWidth() - getPaddingRight());
        float paddingTop = (float) (getPaddingTop() + this.mThumbRadiusOnDragging);
        if (this.isShowSectionText) {
            this.mPaint.setColor(this.mSectionTextColor);
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            this.mPaint.getTextBounds("0123456789", 0, "0123456789".length(), this.mRectText);
            if (this.mSectionTextPosition == 0) {
                float height = (((float) this.mRectText.height()) / 2.0f) + paddingTop;
                String str = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
                canvas.drawText(str, (((float) this.mRectText.width()) / 2.0f) + paddingLeft, height, this.mPaint);
                paddingLeft += (float) (this.mRectText.width() + this.mTextSpace);
                String str2 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str2, 0, str2.length(), this.mRectText);
                canvas.drawText(str2, measuredWidth - ((((float) this.mRectText.width()) + 0.5f) / 2.0f), height, this.mPaint);
                f = measuredWidth - ((float) (this.mRectText.width() + this.mTextSpace));
            } else {
                if (this.mSectionTextPosition >= 1) {
                    float f4 = (float) this.mThumbRadiusOnDragging;
                    float f5 = (float) this.mTextSpace;
                    String str3 = this.mSectionTextArray.get(0);
                    this.mPaint.getTextBounds(str3, 0, str3.length(), this.mRectText);
                    float height2 = f4 + paddingTop + f5 + ((float) this.mRectText.height());
                    paddingLeft = this.mLeft;
                    if (this.mSectionTextPosition == 1) {
                        canvas.drawText(str3, paddingLeft, height2, this.mPaint);
                    }
                    String str4 = this.mSectionTextArray.get(this.mSectionCount);
                    this.mPaint.getTextBounds(str4, 0, str4.length(), this.mRectText);
                    float f6 = this.mRight;
                    if (this.mSectionTextPosition == 1) {
                        canvas.drawText(str4, f6, height2, this.mPaint);
                    }
                    f = f6;
                }
                f = measuredWidth;
            }
        } else {
            if (this.isShowThumbText && this.mSectionTextPosition == -1) {
                float f7 = this.mLeft;
                f = this.mRight;
                paddingLeft = f7;
            }
            f = measuredWidth;
        }
        if ((this.isShowSectionText || this.isShowThumbText) && this.mSectionTextPosition != 0) {
            f2 = paddingLeft;
            f3 = f;
        } else {
            float f8 = paddingLeft + ((float) this.mThumbRadiusOnDragging);
            f3 = f - ((float) this.mThumbRadiusOnDragging);
            f2 = f8;
        }
        boolean z = this.isShowSectionText && this.mSectionTextPosition == 2;
        if (z || this.isShowSectionMark) {
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            this.mPaint.getTextBounds("0123456789", 0, "0123456789".length(), this.mRectText);
            float height3 = (float) this.mRectText.height();
            float f9 = (float) this.mThumbRadiusOnDragging;
            float f10 = (float) this.mTextSpace;
            float dp2px = ((float) (this.mThumbRadiusOnDragging - BubbleUtils.dp2px(2))) / 2.0f;
            float abs = this.isRtl ? this.mRight - ((this.mTrackLength / this.mDelta) * Math.abs(this.mProgress - this.mMin)) : this.mLeft + ((this.mTrackLength / this.mDelta) * Math.abs(this.mProgress - this.mMin));
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 > this.mSectionCount) {
                    break;
                }
                float f11 = (((float) i2) * this.mSectionOffset) + f2;
                if (this.isRtl) {
                    this.mPaint.setColor(f11 <= abs ? this.mTrackColor : this.mSecondTrackColor);
                } else {
                    this.mPaint.setColor(f11 <= abs ? this.mSecondTrackColor : this.mTrackColor);
                }
                canvas.drawCircle(f11, paddingTop, dp2px, this.mPaint);
                if (z) {
                    this.mPaint.setColor(this.mSectionTextColor);
                    if (this.mSectionTextArray.get(i2, (Object) null) != null) {
                        canvas.drawText(this.mSectionTextArray.get(i2), f11, height3 + paddingTop + f9 + f10, this.mPaint);
                    }
                }
                i = i2 + 1;
            }
        }
        if (!this.isThumbOnDragging || this.isAlwaysShowBubble) {
            if (this.isRtl) {
                this.mThumbCenterX = f3 - ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin));
            } else {
                this.mThumbCenterX = ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin)) + f2;
            }
        }
        if (this.isShowThumbText && !this.isThumbOnDragging && this.isTouchToSeekAnimEnd) {
            this.mPaint.setColor(this.mThumbTextColor);
            this.mPaint.setTextSize((float) this.mThumbTextSize);
            this.mPaint.getTextBounds("0123456789", 0, "0123456789".length(), this.mRectText);
            float height4 = ((float) this.mRectText.height()) + paddingTop + ((float) this.mThumbRadiusOnDragging) + ((float) this.mTextSpace);
            if (this.isFloatType || (this.isShowProgressInFloat && this.mSectionTextPosition == 1 && this.mProgress != this.mMin && this.mProgress != this.mMax)) {
                canvas.drawText(String.valueOf(getProgressFloat()), this.mThumbCenterX, height4, this.mPaint);
            } else {
                canvas.drawText(String.valueOf(getProgress()), this.mThumbCenterX, height4, this.mPaint);
            }
        }
        this.mPaint.setColor(this.mSecondTrackColor);
        this.mPaint.setStrokeWidth((float) this.mSecondTrackSize);
        if (this.isRtl) {
            canvas.drawLine(f3, paddingTop, this.mThumbCenterX, paddingTop, this.mPaint);
        } else {
            canvas.drawLine(f2, paddingTop, this.mThumbCenterX, paddingTop, this.mPaint);
        }
        this.mPaint.setColor(this.mTrackColor);
        this.mPaint.setStrokeWidth((float) this.mTrackSize);
        if (this.isRtl) {
            canvas.drawLine(this.mThumbCenterX, paddingTop, f2, paddingTop, this.mPaint);
        } else {
            canvas.drawLine(this.mThumbCenterX, paddingTop, f3, paddingTop, this.mPaint);
        }
        this.mPaint.setColor(this.mThumbColor);
        canvas.drawCircle(this.mThumbCenterX, paddingTop, (float) (this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius), this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.isHideBubble) {
            locatePositionInWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.mThumbRadiusOnDragging * 2;
        if (this.isShowThumbText) {
            this.mPaint.setTextSize((float) this.mThumbTextSize);
            this.mPaint.getTextBounds("j", 0, 1, this.mRectText);
            i3 += this.mRectText.height();
        }
        if (this.isShowSectionText && this.mSectionTextPosition >= 1) {
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            this.mPaint.getTextBounds("j", 0, 1, this.mRectText);
            i3 = Math.max(i3, (this.mThumbRadiusOnDragging * 2) + this.mRectText.height());
        }
        setMeasuredDimension(resolveSize(BubbleUtils.dp2px(SubsamplingScaleImageView.ORIENTATION_180), i), i3 + (this.mTextSpace * 2));
        this.mLeft = (float) (getPaddingLeft() + this.mThumbRadiusOnDragging);
        this.mRight = (float) ((getMeasuredWidth() - getPaddingRight()) - this.mThumbRadiusOnDragging);
        if (this.isShowSectionText) {
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            if (this.mSectionTextPosition == 0) {
                String str = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
                this.mLeft += (float) (this.mRectText.width() + this.mTextSpace);
                String str2 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str2, 0, str2.length(), this.mRectText);
                this.mRight -= (float) (this.mRectText.width() + this.mTextSpace);
            } else if (this.mSectionTextPosition >= 1) {
                String str3 = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str3, 0, str3.length(), this.mRectText);
                this.mLeft = Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f) + ((float) getPaddingLeft()) + ((float) this.mTextSpace);
                String str4 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str4, 0, str4.length(), this.mRectText);
                this.mRight = (((float) (getMeasuredWidth() - getPaddingRight())) - Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f)) - ((float) this.mTextSpace);
            }
        } else if (this.isShowThumbText && this.mSectionTextPosition == -1) {
            this.mPaint.setTextSize((float) this.mThumbTextSize);
            String str5 = this.mSectionTextArray.get(0);
            this.mPaint.getTextBounds(str5, 0, str5.length(), this.mRectText);
            this.mLeft = Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f) + ((float) getPaddingLeft()) + ((float) this.mTextSpace);
            String str6 = this.mSectionTextArray.get(this.mSectionCount);
            this.mPaint.getTextBounds(str6, 0, str6.length(), this.mRectText);
            this.mRight = (((float) (getMeasuredWidth() - getPaddingRight())) - Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f)) - ((float) this.mTextSpace);
        }
        this.mTrackLength = this.mRight - this.mLeft;
        this.mSectionOffset = (this.mTrackLength * 1.0f) / ((float) this.mSectionCount);
        if (!this.isHideBubble) {
            this.mBubbleView.measure(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgress = bundle.getFloat("progress");
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
            if (this.mBubbleView != null) {
                this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
            }
            setProgress(this.mProgress);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save_instance", super.onSaveInstanceState());
        bundle.putFloat("progress", this.mProgress);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() {
            public void run() {
                BubbleSeekBar.this.requestLayout();
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036  */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        switch (motionEvent.getActionMasked()) {
            case 0:
                performClick();
                getParent().requestDisallowInterceptTouchEvent(true);
                this.isThumbOnDragging = isThumbTouched(motionEvent);
                if (this.isThumbOnDragging) {
                    if (this.isSeekBySection && !this.triggerSeekBySection) {
                        this.triggerSeekBySection = true;
                    }
                    if (this.isAlwaysShowBubble && !this.triggerBubbleShowing) {
                        this.triggerBubbleShowing = true;
                    }
                    if (!this.isHideBubble) {
                        showBubble();
                    }
                    invalidate();
                } else if (this.isTouchToSeek && isTrackTouched(motionEvent)) {
                    this.isThumbOnDragging = true;
                    if (this.isSeekBySection && !this.triggerSeekBySection) {
                        this.triggerSeekBySection = true;
                    }
                    if (this.isAlwaysShowBubble) {
                        hideBubble();
                        this.triggerBubbleShowing = true;
                    }
                    if (this.isSeekStepSection) {
                        float calThumbCxWhenSeekStepSection = calThumbCxWhenSeekStepSection(motionEvent.getX());
                        this.mPreThumbCenterX = calThumbCxWhenSeekStepSection;
                        this.mThumbCenterX = calThumbCxWhenSeekStepSection;
                    } else {
                        this.mThumbCenterX = motionEvent.getX();
                        if (this.mThumbCenterX < this.mLeft) {
                            this.mThumbCenterX = this.mLeft;
                        }
                        if (this.mThumbCenterX > this.mRight) {
                            this.mThumbCenterX = this.mRight;
                        }
                    }
                    this.mProgress = calculateProgress();
                    if (!this.isHideBubble) {
                        this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
                        showBubble();
                    }
                    invalidate();
                }
                this.dx = this.mThumbCenterX - motionEvent.getX();
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                if (this.isAutoAdjustSectionMark) {
                    if (this.isTouchToSeek) {
                        postDelayed(new Runnable() {
                            public void run() {
                                boolean unused = BubbleSeekBar.this.isTouchToSeekAnimEnd = false;
                                BubbleSeekBar.this.autoAdjustSection();
                            }
                        }, this.mAnimDuration);
                    } else {
                        autoAdjustSection();
                    }
                } else if (this.isThumbOnDragging || this.isTouchToSeek) {
                    if (this.isHideBubble) {
                        animate().setDuration(this.mAnimDuration).setStartDelay((this.isThumbOnDragging || !this.isTouchToSeek) ? 0 : 300).setListener(new AnimatorListenerAdapter() {
                            public void onAnimationCancel(Animator animator) {
                                boolean unused = BubbleSeekBar.this.isThumbOnDragging = false;
                                BubbleSeekBar.this.invalidate();
                            }

                            public void onAnimationEnd(Animator animator) {
                                boolean unused = BubbleSeekBar.this.isThumbOnDragging = false;
                                BubbleSeekBar.this.invalidate();
                            }
                        }).start();
                    } else {
                        postDelayed(new Runnable() {
                            public void run() {
                                BubbleSeekBar.this.mBubbleView.animate().alpha(BubbleSeekBar.this.isAlwaysShowBubble ? 1.0f : CropImageView.DEFAULT_ASPECT_RATIO).setDuration(BubbleSeekBar.this.mAnimDuration).setListener(new AnimatorListenerAdapter() {
                                    public void onAnimationCancel(Animator animator) {
                                        if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                            BubbleSeekBar.this.hideBubble();
                                        }
                                        boolean unused = BubbleSeekBar.this.isThumbOnDragging = false;
                                        BubbleSeekBar.this.invalidate();
                                    }

                                    public void onAnimationEnd(Animator animator) {
                                        if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                            BubbleSeekBar.this.hideBubble();
                                        }
                                        boolean unused = BubbleSeekBar.this.isThumbOnDragging = false;
                                        BubbleSeekBar.this.invalidate();
                                    }
                                }).start();
                            }
                        }, this.mAnimDuration);
                    }
                }
                if (this.mProgressListener != null) {
                    this.mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                    this.mProgressListener.getProgressOnActionUp(this, getProgress(), getProgressFloat());
                    break;
                }
                break;
            case 2:
                if (this.isThumbOnDragging) {
                    if (this.isSeekStepSection) {
                        float calThumbCxWhenSeekStepSection2 = calThumbCxWhenSeekStepSection(motionEvent.getX());
                        if (calThumbCxWhenSeekStepSection2 == this.mPreThumbCenterX) {
                            z = false;
                            if (z) {
                                this.mProgress = calculateProgress();
                                if (this.isHideBubble || this.mBubbleView.getParent() == null) {
                                    processProgress();
                                } else {
                                    this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
                                    this.mLayoutParams.x = (int) (this.mBubbleCenterRawX + 0.5f);
                                    this.mWindowManager.updateViewLayout(this.mBubbleView, this.mLayoutParams);
                                    this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
                                }
                                invalidate();
                                if (this.mProgressListener != null) {
                                    this.mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                                    break;
                                }
                            }
                        } else {
                            this.mPreThumbCenterX = calThumbCxWhenSeekStepSection2;
                            this.mThumbCenterX = calThumbCxWhenSeekStepSection2;
                        }
                    } else {
                        this.mThumbCenterX = motionEvent.getX() + this.dx;
                        if (this.mThumbCenterX < this.mLeft) {
                            this.mThumbCenterX = this.mLeft;
                        }
                        if (this.mThumbCenterX > this.mRight) {
                            this.mThumbCenterX = this.mRight;
                        }
                    }
                    z = true;
                    if (z) {
                    }
                }
                break;
        }
        return this.isThumbOnDragging || this.isTouchToSeek || super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(@NonNull View view, int i) {
        if (!this.isHideBubble && this.isAlwaysShowBubble) {
            if (i != 0) {
                hideBubble();
            } else if (this.triggerBubbleShowing) {
                showBubble();
            }
            super.onVisibilityChanged(view, i);
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    public void setBubbleColor(@ColorInt int i) {
        if (this.mBubbleColor != i) {
            this.mBubbleColor = i;
            if (this.mBubbleView != null) {
                this.mBubbleView.invalidate();
            }
        }
    }

    public void setCustomSectionTextArray(@NonNull CustomSectionTextArray customSectionTextArray) {
        this.mSectionTextArray = customSectionTextArray.onCustomize(this.mSectionCount, this.mSectionTextArray);
        for (int i = 0; i <= this.mSectionCount; i++) {
            if (this.mSectionTextArray.get(i) == null) {
                this.mSectionTextArray.put(i, "");
            }
        }
        this.isShowThumbText = false;
        requestLayout();
        invalidate();
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mProgressListener = onProgressChangedListener;
    }

    public void setProgress(float f) {
        this.mProgress = f;
        if (this.mProgressListener != null) {
            this.mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            this.mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        if (!this.isHideBubble) {
            this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        }
        if (this.isAlwaysShowBubble) {
            hideBubble();
            postDelayed(new Runnable() {
                public void run() {
                    BubbleSeekBar.this.showBubble();
                    boolean unused = BubbleSeekBar.this.triggerBubbleShowing = true;
                }
            }, this.mAlwaysShowBubbleDelay);
        }
        if (this.isSeekBySection) {
            this.triggerSeekBySection = false;
        }
        postInvalidate();
    }

    public void setSecondTrackColor(@ColorInt int i) {
        if (this.mSecondTrackColor != i) {
            this.mSecondTrackColor = i;
            invalidate();
        }
    }

    public void setThumbColor(@ColorInt int i) {
        if (this.mThumbColor != i) {
            this.mThumbColor = i;
            invalidate();
        }
    }

    public void setTrackColor(@ColorInt int i) {
        if (this.mTrackColor != i) {
            this.mTrackColor = i;
            invalidate();
        }
    }
}
