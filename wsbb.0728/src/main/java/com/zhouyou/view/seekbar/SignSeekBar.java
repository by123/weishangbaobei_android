package com.zhouyou.view.seekbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class SignSeekBar extends View {
    static final int NONE = -1;
    private int barRoundingRadius;
    float dx;
    private boolean isAutoAdjustSectionMark;
    private boolean isFloatType;
    private boolean isSeekBySection;
    private boolean isShowProgressInFloat;
    private boolean isShowSectionMark;
    private boolean isShowSectionText;
    private boolean isShowSign;
    private boolean isShowSignBorder;
    private boolean isShowThumbShadow;
    private boolean isShowThumbText;
    private boolean isSidesLabels;
    private boolean isSignArrowAutofloat;
    /* access modifiers changed from: private */
    public boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    /* access modifiers changed from: private */
    public boolean isTouchToSeekAnimEnd;
    private long mAnimDuration;
    private SignConfigBuilder mConfigBuilder;
    /* access modifiers changed from: private */
    public float mDelta;
    private NumberFormat mFormat;
    /* access modifiers changed from: private */
    public float mLeft;
    private float mMax;
    /* access modifiers changed from: private */
    public float mMin;
    private Paint mPaint;
    private float mPreSecValue;
    /* access modifiers changed from: private */
    public float mProgress;
    /* access modifiers changed from: private */
    public OnProgressChangedListener mProgressListener;
    private Rect mRectText;
    private boolean mReverse;
    private float mRight;
    private int mSecondTrackColor;
    private int mSecondTrackSize;
    private int mSectionCount;
    private float mSectionOffset;
    private int mSectionTextColor;
    private int mSectionTextInterval;
    private int mSectionTextPosition;
    private int mSectionTextSize;
    private float mSectionValue;
    private String[] mSidesLabels;
    private int mSignArrowHeight;
    private int mSignArrowWidth;
    private int mSignBorderColor;
    private int mSignBorderSize;
    private int mSignColor;
    private int mSignHeight;
    private int mSignRound;
    private int mSignTextColor;
    private int mSignTextSize;
    private int mSignWidth;
    private int mTextSpace;
    private float mThumbBgAlpha;
    /* access modifiers changed from: private */
    public float mThumbCenterX;
    private int mThumbColor;
    private int mThumbRadius;
    private int mThumbRadiusOnDragging;
    private float mThumbRatio;
    private int mThumbTextColor;
    private int mThumbTextSize;
    private int mTrackColor;
    /* access modifiers changed from: private */
    public float mTrackLength;
    private int mTrackSize;
    private int mUnusableColor;
    private OnValueFormatListener mValueFormatListener;
    private Point point1;
    private Point point2;
    private Point point3;
    private RectF roundRectangleBounds;
    private Paint signPaint;
    private Paint signborderPaint;
    private Path trianglePath;
    private Path triangleboderPath;
    private boolean triggerSeekBySection;
    private String unit;
    private Rect valueSignBounds;
    private StaticLayout valueTextLayout;
    private TextPaint valueTextPaint;

    public interface OnProgressChangedListener {
        void getProgressOnActionUp(SignSeekBar signSeekBar, int i, float f);

        void getProgressOnFinally(SignSeekBar signSeekBar, int i, float f, boolean z);

        void onProgressChanged(SignSeekBar signSeekBar, int i, float f, boolean z);
    }

    public interface OnValueFormatListener {
        String format(float f);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TextPosition {
        public static final int BELOW_SECTION_MARK = 2;
        public static final int BOTTOM_SIDES = 1;
        public static final int SIDES = 0;
    }

    public SignSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSectionTextPosition = -1;
        this.isTouchToSeekAnimEnd = true;
        this.barRoundingRadius = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SignSeekBar, i, 0);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.SignSeekBar_ssb_min, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.SignSeekBar_ssb_max, 100.0f);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.SignSeekBar_ssb_progress, this.mMin);
        this.isFloatType = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_is_float_type, false);
        this.mTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_track_size, SignUtils.dp2px(2));
        this.mTextSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_text_space, SignUtils.dp2px(2));
        this.mSecondTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_second_track_size, this.mTrackSize + SignUtils.dp2px(2));
        this.mThumbRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_thumb_radius, this.mSecondTrackSize + SignUtils.dp2px(2));
        this.mThumbRadiusOnDragging = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_thumb_radius, this.mSecondTrackSize * 2);
        this.mSignBorderSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_border_size, SignUtils.dp2px(1));
        this.mSectionCount = obtainStyledAttributes.getInteger(R.styleable.SignSeekBar_ssb_section_count, 10);
        this.mTrackColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_track_color, ContextCompat.getColor(context, R.color.colorPrimary));
        this.mSecondTrackColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_second_track_color, ContextCompat.getColor(context, R.color.colorAccent));
        this.mThumbColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_thumb_color, this.mSecondTrackColor);
        this.isShowSectionText = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_section_text, false);
        this.mSectionTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_section_text_size, SignUtils.sp2px(14));
        this.mSectionTextColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_section_text_color, this.mTrackColor);
        this.isSeekBySection = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_seek_by_section, false);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SignSeekBar_ssb_section_text_position, -1);
        if (integer == 0) {
            this.mSectionTextPosition = 0;
        } else if (integer == 1) {
            this.mSectionTextPosition = 1;
        } else if (integer == 2) {
            this.mSectionTextPosition = 2;
        } else {
            this.mSectionTextPosition = -1;
        }
        this.mSectionTextInterval = obtainStyledAttributes.getInteger(R.styleable.SignSeekBar_ssb_section_text_interval, 1);
        this.isShowThumbText = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_thumb_text, false);
        this.mThumbTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_thumb_text_size, SignUtils.sp2px(14));
        this.mThumbTextColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_thumb_text_color, this.mSecondTrackColor);
        this.mSignColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_sign_color, this.mSecondTrackColor);
        this.mSignBorderColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_sign_border_color, this.mSecondTrackColor);
        this.mUnusableColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_unusable_color, -7829368);
        this.mSignTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_text_size, SignUtils.sp2px(14));
        this.mSignHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_height, SignUtils.dp2px(32));
        this.mSignWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_width, SignUtils.dp2px(72));
        this.mSignArrowHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_arrow_height, SignUtils.dp2px(3));
        this.mSignArrowWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_arrow_width, SignUtils.dp2px(5));
        this.mSignRound = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SignSeekBar_ssb_sign_round, SignUtils.dp2px(3));
        this.mSignTextColor = obtainStyledAttributes.getColor(R.styleable.SignSeekBar_ssb_sign_text_color, -1);
        this.isShowSectionMark = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_section_mark, false);
        this.isAutoAdjustSectionMark = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_auto_adjust_section_mark, false);
        this.isShowProgressInFloat = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_progress_in_float, false);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SignSeekBar_ssb_anim_duration, -1);
        this.mAnimDuration = integer2 < 0 ? 200 : (long) integer2;
        this.isTouchToSeek = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_touch_to_seek, false);
        this.isShowSignBorder = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_sign_show_border, false);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SignSeekBar_ssb_sides_labels, 0);
        this.mThumbBgAlpha = obtainStyledAttributes.getFloat(R.styleable.SignSeekBar_ssb_thumb_bg_alpha, 0.2f);
        this.mThumbRatio = obtainStyledAttributes.getFloat(R.styleable.SignSeekBar_ssb_thumb_ratio, 0.7f);
        this.isShowThumbShadow = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_thumb_shadow, false);
        this.isShowSign = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_show_sign, false);
        this.isSignArrowAutofloat = obtainStyledAttributes.getBoolean(R.styleable.SignSeekBar_ssb_sign_arrow_autofloat, true);
        obtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mRectText = new Rect();
        if (resourceId > 0) {
            this.mSidesLabels = getResources().getStringArray(resourceId);
        }
        this.isSidesLabels = this.mSidesLabels != null && this.mSidesLabels.length > 0;
        this.roundRectangleBounds = new RectF();
        this.valueSignBounds = new Rect();
        this.point1 = new Point();
        this.point2 = new Point();
        this.point3 = new Point();
        this.trianglePath = new Path();
        this.trianglePath.setFillType(Path.FillType.EVEN_ODD);
        this.triangleboderPath = new Path();
        init();
        initConfigByPriority();
    }

    /* access modifiers changed from: private */
    public void autoAdjustSection() {
        ValueAnimator valueAnimator;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        int i = 0;
        while (i <= this.mSectionCount) {
            f = (((float) i) * this.mSectionOffset) + this.mLeft;
            if (f <= this.mThumbCenterX && this.mThumbCenterX - f <= this.mSectionOffset) {
                break;
            }
            i++;
        }
        boolean z = BigDecimal.valueOf((double) this.mThumbCenterX).setScale(1, 4).floatValue() == f;
        AnimatorSet animatorSet = new AnimatorSet();
        if (!z) {
            valueAnimator = this.mThumbCenterX - f <= this.mSectionOffset / 2.0f ? ValueAnimator.ofFloat(new float[]{this.mThumbCenterX, f}) : ValueAnimator.ofFloat(new float[]{this.mThumbCenterX, (((float) (i + 1)) * this.mSectionOffset) + this.mLeft});
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = SignSeekBar.this.mThumbCenterX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float unused2 = SignSeekBar.this.mProgress = (((SignSeekBar.this.mThumbCenterX - SignSeekBar.this.mLeft) * SignSeekBar.this.mDelta) / SignSeekBar.this.mTrackLength) + SignSeekBar.this.mMin;
                    SignSeekBar.this.invalidate();
                    if (SignSeekBar.this.mProgressListener != null) {
                        SignSeekBar.this.mProgressListener.onProgressChanged(SignSeekBar.this, SignSeekBar.this.getProgress(), SignSeekBar.this.getProgressFloat(), true);
                    }
                }
            });
        } else {
            valueAnimator = null;
        }
        if (!z) {
            animatorSet.setDuration(this.mAnimDuration).playTogether(new Animator[]{valueAnimator});
        }
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                float unused = SignSeekBar.this.mProgress = (((SignSeekBar.this.mThumbCenterX - SignSeekBar.this.mLeft) * SignSeekBar.this.mDelta) / SignSeekBar.this.mTrackLength) + SignSeekBar.this.mMin;
                boolean unused2 = SignSeekBar.this.isThumbOnDragging = false;
                boolean unused3 = SignSeekBar.this.isTouchToSeekAnimEnd = true;
                SignSeekBar.this.invalidate();
            }

            public void onAnimationEnd(Animator animator) {
                float unused = SignSeekBar.this.mProgress = (((SignSeekBar.this.mThumbCenterX - SignSeekBar.this.mLeft) * SignSeekBar.this.mDelta) / SignSeekBar.this.mTrackLength) + SignSeekBar.this.mMin;
                boolean unused2 = SignSeekBar.this.isThumbOnDragging = false;
                boolean unused3 = SignSeekBar.this.isTouchToSeekAnimEnd = true;
                SignSeekBar.this.invalidate();
                if (SignSeekBar.this.mProgressListener != null) {
                    SignSeekBar.this.mProgressListener.getProgressOnFinally(SignSeekBar.this, SignSeekBar.this.getProgress(), SignSeekBar.this.getProgressFloat(), true);
                }
            }
        });
        animatorSet.start();
    }

    private void createValueTextLayout() {
        String valueOf;
        String format;
        if (this.isShowProgressInFloat) {
            float progressFloat = getProgressFloat();
            valueOf = String.valueOf(progressFloat);
            if (this.mFormat != null) {
                valueOf = this.mFormat.format((double) progressFloat);
            }
        } else {
            int progress = getProgress();
            valueOf = String.valueOf(progress);
            if (this.mFormat != null) {
                valueOf = this.mFormat.format((long) progress);
            }
        }
        if (this.mValueFormatListener != null) {
            format = this.mValueFormatListener.format(Float.parseFloat(valueOf));
        } else if (valueOf == null || this.unit == null || this.unit.isEmpty()) {
            format = valueOf;
        } else if (!this.mReverse) {
            format = valueOf + String.format(" <small>%s</small> ", new Object[]{this.unit});
        } else {
            format = String.format(" %s ", new Object[]{this.unit}) + valueOf;
        }
        this.valueTextLayout = new StaticLayout(Html.fromHtml(format), this.valueTextPaint, this.mSignWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, false);
    }

    private void drawCircleText(Canvas canvas, Paint paint, float f, float f2, float f3, String str) {
        paint.setTextAlign(Paint.Align.LEFT);
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        canvas.drawText(str, ((f - f3) + f3) - ((float) (rect.width() / 2)), (((((f3 * 2.0f) - ((float) fontMetricsInt.bottom)) + ((float) fontMetricsInt.top)) / 2.0f) + (f2 - f3)) - ((float) fontMetricsInt.top), paint);
    }

    private void drawMark(Canvas canvas, float f, float f2, boolean z, boolean z2) {
        String str;
        String str2;
        float dp2px = ((float) (this.mThumbRadiusOnDragging - SignUtils.dp2px(2))) / 2.0f;
        float f3 = this.mTrackLength / this.mDelta;
        float abs = Math.abs(this.mProgress - this.mMin);
        float f4 = this.mLeft;
        this.mPaint.setTextSize((float) this.mSectionTextSize);
        int i = 0;
        this.mPaint.getTextBounds("0123456789", 0, "0123456789".length(), this.mRectText);
        float height = ((float) this.mTextSpace) + ((float) this.mRectText.height()) + f2 + ((float) this.mThumbRadiusOnDragging);
        while (true) {
            int i2 = i;
            if (i2 <= this.mSectionCount) {
                float f5 = (float) i2;
                float f6 = (this.mSectionOffset * f5) + f;
                this.mPaint.setColor(f6 <= (f3 * abs) + f4 ? this.mSecondTrackColor : this.mTrackColor);
                canvas.drawCircle(f6, f2, dp2px, this.mPaint);
                if (z) {
                    float f7 = (f5 * this.mSectionValue) + this.mMin;
                    this.mPaint.setColor((!isEnabled() && Math.abs(this.mProgress - f7) > CropImageView.DEFAULT_ASPECT_RATIO) ? this.mUnusableColor : this.mSectionTextColor);
                    if (this.mSectionTextInterval > 1) {
                        if (z2 && i2 % this.mSectionTextInterval == 0) {
                            if (this.isSidesLabels) {
                                canvas.drawText(this.mSidesLabels[i2], f6, height, this.mPaint);
                            } else {
                                if (this.isFloatType) {
                                    str2 = float2String(f7);
                                } else {
                                    str2 = ((int) f7) + "";
                                }
                                canvas.drawText(str2, f6, height, this.mPaint);
                            }
                        }
                    } else if (z2 && i2 % this.mSectionTextInterval == 0) {
                        if (!this.isSidesLabels || i2 / this.mSectionTextInterval > this.mSidesLabels.length) {
                            if (this.isFloatType) {
                                str = float2String(f7);
                            } else {
                                str = ((int) f7) + "";
                            }
                            canvas.drawText(str, f6, height, this.mPaint);
                        } else {
                            canvas.drawText(this.mSidesLabels[i2 / this.mSectionTextInterval], f6, height, this.mPaint);
                        }
                    }
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private void drawProgressText(Canvas canvas) {
        String valueOf = this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress());
        if (!(valueOf == null || this.unit == null || this.unit.isEmpty())) {
            valueOf = valueOf + String.format("%s", new Object[]{this.unit});
        }
        int i = this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius;
        Paint paint = this.mPaint;
        paint.setColor(-16777216);
        paint.setTextSize(25.0f);
        drawCircleText(canvas, paint, this.mThumbCenterX, (float) (getPaddingTop() + this.mThumbRadiusOnDragging), (float) i, valueOf);
    }

    private void drawThumbText(Canvas canvas, float f) {
        this.mPaint.setColor(this.mThumbTextColor);
        this.mPaint.setTextSize((float) this.mThumbTextSize);
        this.mPaint.getTextBounds("0123456789", 0, "0123456789".length(), this.mRectText);
        float height = ((float) this.mRectText.height()) + f + ((float) this.mThumbRadiusOnDragging) + ((float) this.mTextSpace);
        if (this.isFloatType || (this.isShowProgressInFloat && this.mSectionTextPosition == 1 && this.mProgress != this.mMin && this.mProgress != this.mMax)) {
            float progressFloat = getProgressFloat();
            String valueOf = String.valueOf(progressFloat);
            if (this.mFormat != null) {
                valueOf = this.mFormat.format((double) progressFloat);
            }
            if (!(valueOf == null || this.unit == null || this.unit.isEmpty())) {
                if (!this.mReverse) {
                    valueOf = valueOf + String.format("%s", new Object[]{this.unit});
                } else {
                    valueOf = String.format("%s", new Object[]{this.unit}) + valueOf;
                }
            }
            drawSignText(canvas, this.mValueFormatListener != null ? this.mValueFormatListener.format(progressFloat) : valueOf, this.mThumbCenterX, height, this.mPaint);
            return;
        }
        int progress = getProgress();
        String valueOf2 = String.valueOf(progress);
        if (this.mFormat != null) {
            valueOf2 = this.mFormat.format((long) progress);
        }
        if (!(valueOf2 == null || this.unit == null || this.unit.isEmpty())) {
            if (!this.mReverse) {
                valueOf2 = valueOf2 + String.format("%s", new Object[]{this.unit});
            } else {
                valueOf2 = String.format("%s", new Object[]{this.unit}) + valueOf2;
            }
        }
        drawSignText(canvas, this.mValueFormatListener != null ? this.mValueFormatListener.format((float) progress) : valueOf2, this.mThumbCenterX, height, this.mPaint);
    }

    private void drawTriangle(Canvas canvas, Point point, Point point4, Point point5, Paint paint) {
        this.trianglePath.reset();
        this.trianglePath.moveTo((float) point.x, (float) point.y);
        this.trianglePath.lineTo((float) point4.x, (float) point4.y);
        this.trianglePath.lineTo((float) point5.x, (float) point5.y);
        this.trianglePath.lineTo((float) point.x, (float) point.y);
        this.trianglePath.close();
        canvas.drawPath(this.trianglePath, paint);
    }

    private void drawTriangleBoder(Canvas canvas, Point point, Point point4, Point point5, Paint paint) {
        this.triangleboderPath.reset();
        this.triangleboderPath.moveTo((float) point.x, (float) point.y);
        this.triangleboderPath.lineTo((float) point4.x, (float) point4.y);
        paint.setColor(this.signPaint.getColor());
        float f = (float) (this.mSignBorderSize / 6);
        paint.setStrokeWidth(((float) this.mSignBorderSize) + 1.0f);
        canvas.drawPath(this.triangleboderPath, paint);
        this.triangleboderPath.reset();
        paint.setStrokeWidth((float) this.mSignBorderSize);
        this.triangleboderPath.moveTo(((float) point.x) - f, ((float) point.y) - f);
        this.triangleboderPath.lineTo((float) point5.x, (float) point5.y);
        this.triangleboderPath.lineTo(((float) point4.x) + f, ((float) point4.y) - f);
        paint.setColor(this.mSignBorderColor);
        canvas.drawPath(this.triangleboderPath, paint);
    }

    private void drawValueSign(Canvas canvas, int i, int i2) {
        int i3 = 0;
        this.valueSignBounds.set(i2 - (this.mSignWidth / 2), getPaddingTop(), (this.mSignWidth / 2) + i2, (this.mSignHeight - this.mSignArrowHeight) + getPaddingTop());
        int i4 = this.isShowSignBorder ? this.mSignBorderSize : 0;
        if (this.valueSignBounds.left < getPaddingLeft()) {
            int paddingLeft = (-this.valueSignBounds.left) + getPaddingLeft() + i4;
            this.roundRectangleBounds.set((float) (this.valueSignBounds.left + paddingLeft), (float) this.valueSignBounds.top, (float) (paddingLeft + this.valueSignBounds.right), (float) this.valueSignBounds.bottom);
        } else if (this.valueSignBounds.right > getMeasuredWidth() - getPaddingRight()) {
            int measuredWidth = (this.valueSignBounds.right - getMeasuredWidth()) + getPaddingRight() + i4;
            this.roundRectangleBounds.set((float) (this.valueSignBounds.left - measuredWidth), (float) this.valueSignBounds.top, (float) (this.valueSignBounds.right - measuredWidth), (float) this.valueSignBounds.bottom);
        } else {
            this.roundRectangleBounds.set((float) this.valueSignBounds.left, (float) this.valueSignBounds.top, (float) this.valueSignBounds.right, (float) this.valueSignBounds.bottom);
        }
        canvas.drawRoundRect(this.roundRectangleBounds, (float) this.mSignRound, (float) this.mSignRound, this.signPaint);
        if (this.isShowSignBorder) {
            this.roundRectangleBounds.top += (float) (this.mSignBorderSize / 2);
            canvas.drawRoundRect(this.roundRectangleBounds, (float) this.mSignRound, (float) this.mSignRound, this.signborderPaint);
        }
        this.barRoundingRadius = this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius;
        if (i2 - (this.mSignArrowWidth / 2) < this.barRoundingRadius + getPaddingLeft() + this.mTextSpace + i4) {
            i3 = this.mTextSpace + i4 + (this.barRoundingRadius - i2) + getPaddingLeft();
        } else if ((this.mSignArrowWidth / 2) + i2 > (((getMeasuredWidth() - this.barRoundingRadius) - getPaddingRight()) - this.mTextSpace) - i4) {
            i3 = ((((getMeasuredWidth() - this.barRoundingRadius) - i2) - getPaddingRight()) - i4) - this.mTextSpace;
        }
        this.point1.set((i2 - (this.mSignArrowWidth / 2)) + i3, (i - this.mSignArrowHeight) + getPaddingTop());
        this.point2.set((this.mSignArrowWidth / 2) + i2 + i3, (i - this.mSignArrowHeight) + getPaddingTop());
        this.point3.set(i3 + i2, getPaddingTop() + i);
        drawTriangle(canvas, this.point1, this.point2, this.point3, this.signPaint);
        if (this.isShowSignBorder) {
            drawTriangleBoder(canvas, this.point1, this.point2, this.point3, this.signborderPaint);
        }
        createValueTextLayout();
        if (this.valueTextLayout != null) {
            canvas.translate(this.roundRectangleBounds.left, (this.roundRectangleBounds.top + (this.roundRectangleBounds.height() / 2.0f)) - ((float) (this.valueTextLayout.getHeight() / 2)));
            this.valueTextLayout.draw(canvas);
        }
    }

    private String float2String(float f) {
        return String.valueOf(formatFloat(f));
    }

    private float formatFloat(float f) {
        return BigDecimal.valueOf((double) f).setScale(1, 4).floatValue();
    }

    private String getMaxText() {
        return this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
    }

    private String getMinText() {
        return this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
    }

    private void init() {
        this.signPaint = new Paint(1);
        this.signPaint.setStyle(Paint.Style.FILL);
        this.signPaint.setAntiAlias(true);
        this.signPaint.setColor(this.mSignColor);
        this.signborderPaint = new Paint(1);
        this.signborderPaint.setStyle(Paint.Style.STROKE);
        this.signborderPaint.setStrokeWidth((float) this.mSignBorderSize);
        this.signborderPaint.setColor(this.mSignBorderColor);
        this.signborderPaint.setAntiAlias(true);
        this.valueTextPaint = new TextPaint(1);
        this.valueTextPaint.setStyle(Paint.Style.FILL);
        this.valueTextPaint.setTextSize((float) this.mSignTextSize);
        this.valueTextPaint.setColor(this.mSignTextColor);
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
            this.mSecondTrackSize = this.mTrackSize + SignUtils.dp2px(2);
        }
        if (this.mThumbRadius <= this.mSecondTrackSize) {
            this.mThumbRadius = this.mSecondTrackSize + SignUtils.dp2px(2);
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
            this.isTouchToSeek = false;
        }
        setProgress(this.mProgress);
        this.mThumbTextSize = (this.isFloatType || this.isSeekBySection || (this.isShowSectionText && this.mSectionTextPosition == 2)) ? this.mSectionTextSize : this.mThumbTextSize;
    }

    private boolean isThumbTouched(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f = (float) (this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius);
        float f2 = ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin)) + this.mLeft;
        float measuredHeight = ((float) getMeasuredHeight()) / 2.0f;
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (f + this.mLeft) * (this.mLeft + f);
    }

    private boolean isTrackTouched(MotionEvent motionEvent) {
        return isEnabled() && motionEvent.getX() >= ((float) getPaddingLeft()) && motionEvent.getX() <= ((float) (getMeasuredWidth() - getPaddingRight())) && motionEvent.getY() >= ((float) getPaddingTop()) && motionEvent.getY() <= ((float) (getMeasuredHeight() - getPaddingBottom()));
    }

    /* access modifiers changed from: package-private */
    public void config(SignConfigBuilder signConfigBuilder) {
        this.mMin = signConfigBuilder.min;
        this.mMax = signConfigBuilder.max;
        this.mProgress = signConfigBuilder.progress;
        this.isFloatType = signConfigBuilder.floatType;
        this.mTrackSize = signConfigBuilder.trackSize;
        this.mSecondTrackSize = signConfigBuilder.secondTrackSize;
        this.mThumbRadius = signConfigBuilder.thumbRadius;
        this.mThumbRadiusOnDragging = signConfigBuilder.thumbRadiusOnDragging;
        this.mTrackColor = signConfigBuilder.trackColor;
        this.mSecondTrackColor = signConfigBuilder.secondTrackColor;
        this.mThumbColor = signConfigBuilder.thumbColor;
        this.mSectionCount = signConfigBuilder.sectionCount;
        this.isShowSectionMark = signConfigBuilder.showSectionMark;
        this.isAutoAdjustSectionMark = signConfigBuilder.autoAdjustSectionMark;
        this.isShowSectionText = signConfigBuilder.showSectionText;
        this.mSectionTextSize = signConfigBuilder.sectionTextSize;
        this.mSectionTextColor = signConfigBuilder.sectionTextColor;
        this.mSectionTextPosition = signConfigBuilder.sectionTextPosition;
        this.mSectionTextInterval = signConfigBuilder.sectionTextInterval;
        this.isShowThumbText = signConfigBuilder.showThumbText;
        this.mThumbTextSize = signConfigBuilder.thumbTextSize;
        this.mThumbTextColor = signConfigBuilder.thumbTextColor;
        this.isShowProgressInFloat = signConfigBuilder.showProgressInFloat;
        this.mAnimDuration = signConfigBuilder.animDuration;
        this.isTouchToSeek = signConfigBuilder.touchToSeek;
        this.isSeekBySection = signConfigBuilder.seekBySection;
        this.mSidesLabels = this.mConfigBuilder.bottomSidesLabels;
        this.isSidesLabels = this.mSidesLabels != null && this.mSidesLabels.length > 0;
        this.mThumbBgAlpha = this.mConfigBuilder.thumbBgAlpha;
        this.mThumbRatio = this.mConfigBuilder.thumbRatio;
        this.isShowThumbShadow = this.mConfigBuilder.showThumbShadow;
        this.unit = this.mConfigBuilder.unit;
        this.mReverse = this.mConfigBuilder.reverse;
        this.mFormat = this.mConfigBuilder.format;
        this.mSignColor = signConfigBuilder.signColor;
        this.mSignTextSize = signConfigBuilder.signTextSize;
        this.mSignTextColor = signConfigBuilder.signTextColor;
        this.isShowSign = signConfigBuilder.showSign;
        this.mSignArrowWidth = signConfigBuilder.signArrowWidth;
        this.mSignArrowHeight = signConfigBuilder.signArrowHeight;
        this.mSignRound = signConfigBuilder.signRound;
        this.mSignHeight = signConfigBuilder.signHeight;
        this.mSignWidth = signConfigBuilder.signWidth;
        this.isShowSignBorder = signConfigBuilder.showSignBorder;
        this.mSignBorderSize = signConfigBuilder.signBorderSize;
        this.mSignBorderColor = signConfigBuilder.signBorderColor;
        this.isSignArrowAutofloat = signConfigBuilder.signArrowAutofloat;
        init();
        initConfigByPriority();
        createValueTextLayout();
        if (this.mProgressListener != null) {
            this.mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            this.mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        this.mConfigBuilder = null;
        requestLayout();
    }

    public void drawSignText(Canvas canvas, String str, float f, float f2, Paint paint) {
        canvas.drawText(str, f, f2, paint);
    }

    public int getColorWithAlpha(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public SignConfigBuilder getConfigBuilder() {
        if (this.mConfigBuilder == null) {
            this.mConfigBuilder = new SignConfigBuilder(this);
        }
        this.mConfigBuilder.min = this.mMin;
        this.mConfigBuilder.max = this.mMax;
        this.mConfigBuilder.progress = this.mProgress;
        this.mConfigBuilder.floatType = this.isFloatType;
        this.mConfigBuilder.trackSize = this.mTrackSize;
        this.mConfigBuilder.secondTrackSize = this.mSecondTrackSize;
        this.mConfigBuilder.thumbRadius = this.mThumbRadius;
        this.mConfigBuilder.thumbRadiusOnDragging = this.mThumbRadiusOnDragging;
        this.mConfigBuilder.trackColor = this.mTrackColor;
        this.mConfigBuilder.secondTrackColor = this.mSecondTrackColor;
        this.mConfigBuilder.thumbColor = this.mThumbColor;
        this.mConfigBuilder.sectionCount = this.mSectionCount;
        this.mConfigBuilder.showSectionMark = this.isShowSectionMark;
        this.mConfigBuilder.autoAdjustSectionMark = this.isAutoAdjustSectionMark;
        this.mConfigBuilder.showSectionText = this.isShowSectionText;
        this.mConfigBuilder.sectionTextSize = this.mSectionTextSize;
        this.mConfigBuilder.sectionTextColor = this.mSectionTextColor;
        this.mConfigBuilder.sectionTextPosition = this.mSectionTextPosition;
        this.mConfigBuilder.sectionTextInterval = this.mSectionTextInterval;
        this.mConfigBuilder.showThumbText = this.isShowThumbText;
        this.mConfigBuilder.thumbTextSize = this.mThumbTextSize;
        this.mConfigBuilder.thumbTextColor = this.mThumbTextColor;
        this.mConfigBuilder.showProgressInFloat = this.isShowProgressInFloat;
        this.mConfigBuilder.animDuration = this.mAnimDuration;
        this.mConfigBuilder.touchToSeek = this.isTouchToSeek;
        this.mConfigBuilder.seekBySection = this.isSeekBySection;
        this.mConfigBuilder.bottomSidesLabels = this.mSidesLabels;
        this.mConfigBuilder.thumbBgAlpha = this.mThumbBgAlpha;
        this.mConfigBuilder.thumbRatio = this.mThumbRatio;
        this.mConfigBuilder.showThumbShadow = this.isShowThumbShadow;
        this.mConfigBuilder.unit = this.unit;
        this.mConfigBuilder.reverse = this.mReverse;
        this.mConfigBuilder.format = this.mFormat;
        this.mConfigBuilder.signColor = this.mSignColor;
        this.mConfigBuilder.signTextSize = this.mSignTextSize;
        this.mConfigBuilder.signTextColor = this.mSignTextColor;
        this.mConfigBuilder.showSign = this.isShowSign;
        this.mConfigBuilder.signArrowHeight = this.mSignArrowHeight;
        this.mConfigBuilder.signArrowWidth = this.mSignArrowWidth;
        this.mConfigBuilder.signRound = this.mSignRound;
        this.mConfigBuilder.signHeight = this.mSignHeight;
        this.mConfigBuilder.signWidth = this.mSignWidth;
        this.mConfigBuilder.showSignBorder = this.isShowSignBorder;
        this.mConfigBuilder.signBorderSize = this.mSignBorderSize;
        this.mConfigBuilder.signBorderColor = this.mSignBorderColor;
        this.mConfigBuilder.signArrowAutofloat = this.isSignArrowAutofloat;
        return this.mConfigBuilder;
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public int getProgress() {
        if (!this.isSeekBySection || !this.triggerSeekBySection) {
            return Math.round(this.mProgress);
        }
        float f = this.mSectionValue / 2.0f;
        if (this.mProgress >= this.mPreSecValue) {
            if (this.mProgress < f + this.mPreSecValue) {
                return Math.round(this.mPreSecValue);
            }
            this.mPreSecValue += this.mSectionValue;
            return Math.round(this.mPreSecValue);
        } else if (this.mProgress >= this.mPreSecValue - f) {
            return Math.round(this.mPreSecValue);
        } else {
            this.mPreSecValue -= this.mSectionValue;
            return Math.round(this.mPreSecValue);
        }
    }

    public float getProgressFloat() {
        return formatFloat(this.mProgress);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        float paddingLeft = (float) getPaddingLeft();
        float measuredWidth = (float) (getMeasuredWidth() - getPaddingRight());
        float paddingTop = (float) (getPaddingTop() + this.mThumbRadiusOnDragging);
        if (this.isShowSign) {
            paddingTop += (float) this.mSignHeight;
        }
        float f5 = this.isShowSignBorder ? ((float) this.mSignBorderSize) + paddingTop : paddingTop;
        if (!this.isShowSign || this.isSignArrowAutofloat) {
            f = paddingLeft;
        } else {
            f = ((float) ((this.mSignWidth / 2) + this.mSignBorderSize)) + paddingLeft;
            measuredWidth -= (float) ((this.mSignWidth / 2) + this.mSignBorderSize);
        }
        if (this.isShowSectionText) {
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            this.mPaint.setColor(isEnabled() ? this.mSectionTextColor : this.mUnusableColor);
            if (this.mSectionTextPosition == 0) {
                float height = (((float) this.mRectText.height()) / 2.0f) + f5;
                String minText = this.isSidesLabels ? this.mSidesLabels[0] : getMinText();
                this.mPaint.getTextBounds(minText, 0, minText.length(), this.mRectText);
                canvas.drawText(minText, (((float) this.mRectText.width()) / 2.0f) + f, height, this.mPaint);
                float width = f + ((float) (this.mRectText.width() + this.mTextSpace));
                String maxText = (!this.isSidesLabels || this.mSidesLabels.length <= 1) ? getMaxText() : this.mSidesLabels[this.mSidesLabels.length - 1];
                this.mPaint.getTextBounds(maxText, 0, maxText.length(), this.mRectText);
                canvas.drawText(maxText, measuredWidth - (((float) this.mRectText.width()) / 2.0f), height, this.mPaint);
                f2 = measuredWidth - ((float) (this.mRectText.width() + this.mTextSpace));
                f = width;
            } else {
                if (this.mSectionTextPosition >= 1) {
                    float f6 = (float) this.mThumbRadiusOnDragging;
                    float f7 = (float) this.mTextSpace;
                    String minText2 = this.isSidesLabels ? this.mSidesLabels[0] : getMinText();
                    this.mPaint.getTextBounds(minText2, 0, minText2.length(), this.mRectText);
                    float height2 = f6 + f5 + f7 + ((float) this.mRectText.height());
                    float f8 = this.mLeft;
                    if (this.mSectionTextPosition == 1) {
                        canvas.drawText(minText2, f8, height2, this.mPaint);
                    }
                    String maxText2 = (!this.isSidesLabels || this.mSidesLabels.length <= 1) ? getMaxText() : this.mSidesLabels[this.mSidesLabels.length - 1];
                    this.mPaint.getTextBounds(maxText2, 0, maxText2.length(), this.mRectText);
                    float f9 = this.mRight;
                    if (this.mSectionTextPosition == 1) {
                        canvas.drawText(maxText2, f9, height2, this.mPaint);
                    }
                    f2 = f9;
                    f = f8;
                }
                f2 = measuredWidth;
            }
        } else {
            if (this.isShowThumbText && this.mSectionTextPosition == -1) {
                f = this.mLeft;
                f2 = this.mRight;
            }
            f2 = measuredWidth;
        }
        if ((this.isShowSectionText || this.isShowThumbText) && this.mSectionTextPosition != 0) {
            f4 = f;
            f3 = f2;
        } else {
            f4 = ((float) this.mThumbRadiusOnDragging) + f;
            f3 = f2 - ((float) this.mThumbRadiusOnDragging);
        }
        boolean z = this.isShowSectionText && this.mSectionTextPosition == 2;
        if (z || this.isShowSectionMark) {
            drawMark(canvas, f4, f5, z, true);
        }
        if (!this.isThumbOnDragging) {
            this.mThumbCenterX = ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin)) + f4;
        }
        if (this.isShowThumbText && !this.isThumbOnDragging && this.isTouchToSeekAnimEnd) {
            drawThumbText(canvas, f5);
        }
        this.mPaint.setColor(this.mSecondTrackColor);
        this.mPaint.setStrokeWidth((float) this.mSecondTrackSize);
        canvas.drawLine(f4, f5, this.mThumbCenterX, f5, this.mPaint);
        this.mPaint.setColor(this.mTrackColor);
        this.mPaint.setStrokeWidth((float) this.mTrackSize);
        canvas.drawLine(this.mThumbCenterX, f5, f3, f5, this.mPaint);
        this.mPaint.setColor(this.mThumbColor);
        if (this.isShowThumbShadow) {
            canvas.drawCircle(this.mThumbCenterX, f5, ((float) (this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius)) * this.mThumbRatio, this.mPaint);
            this.mPaint.setColor(getColorWithAlpha(this.mThumbColor, this.mThumbBgAlpha));
        }
        canvas.drawCircle(this.mThumbCenterX, f5, (float) (this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius), this.mPaint);
        if (this.isShowSign) {
            drawValueSign(canvas, this.mSignHeight, (int) this.mThumbCenterX);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.mThumbRadiusOnDragging * 2;
        if (this.isShowThumbText) {
            this.mPaint.setTextSize((float) this.mThumbTextSize);
            this.mPaint.getTextBounds("j", 0, 1, this.mRectText);
            i3 += this.mRectText.height() + this.mTextSpace;
        }
        if (this.isShowSectionText && this.mSectionTextPosition >= 1) {
            String str = this.isSidesLabels ? this.mSidesLabels[0] : "j";
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
            i3 = Math.max(i3, (this.mThumbRadiusOnDragging * 2) + this.mRectText.height() + this.mTextSpace);
        }
        boolean z = this.isShowSign;
        int i4 = i3 + this.mSignHeight;
        if (this.isShowSignBorder) {
            i4 += this.mSignBorderSize;
        }
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), i4);
        this.mLeft = (float) (getPaddingLeft() + this.mThumbRadiusOnDragging);
        this.mRight = (float) ((getMeasuredWidth() - getPaddingRight()) - this.mThumbRadiusOnDragging);
        if (this.isShowSectionText) {
            this.mPaint.setTextSize((float) this.mSectionTextSize);
            if (this.mSectionTextPosition == 0) {
                String minText = getMinText();
                this.mPaint.getTextBounds(minText, 0, minText.length(), this.mRectText);
                this.mLeft += (float) (this.mRectText.width() + this.mTextSpace);
                String maxText = getMaxText();
                this.mPaint.getTextBounds(maxText, 0, maxText.length(), this.mRectText);
                this.mRight -= (float) (this.mRectText.width() + this.mTextSpace);
            } else if (this.mSectionTextPosition >= 1) {
                String minText2 = this.isSidesLabels ? this.mSidesLabels[0] : getMinText();
                this.mPaint.getTextBounds(minText2, 0, minText2.length(), this.mRectText);
                this.mLeft = Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f) + ((float) getPaddingLeft()) + ((float) this.mTextSpace);
                String maxText2 = this.isSidesLabels ? this.mSidesLabels[this.mSidesLabels.length - 1] : getMaxText();
                this.mPaint.getTextBounds(maxText2, 0, maxText2.length(), this.mRectText);
                this.mRight = (((float) (getMeasuredWidth() - getPaddingRight())) - Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f)) - ((float) this.mTextSpace);
            }
        } else if (this.isShowThumbText && this.mSectionTextPosition == -1) {
            this.mPaint.setTextSize((float) this.mThumbTextSize);
            String minText3 = getMinText();
            this.mPaint.getTextBounds(minText3, 0, minText3.length(), this.mRectText);
            this.mLeft = Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f) + ((float) getPaddingLeft()) + ((float) this.mTextSpace);
            String maxText3 = getMaxText();
            this.mPaint.getTextBounds(maxText3, 0, maxText3.length(), this.mRectText);
            this.mRight = (((float) (getMeasuredWidth() - getPaddingRight())) - Math.max((float) this.mThumbRadiusOnDragging, ((float) this.mRectText.width()) / 2.0f)) - ((float) this.mTextSpace);
        }
        if (this.isShowSign && !this.isSignArrowAutofloat) {
            this.mLeft = Math.max(this.mLeft, (float) (getPaddingLeft() + (this.mSignWidth / 2) + this.mSignBorderSize));
            this.mRight = Math.min(this.mRight, (float) (((getMeasuredWidth() - getPaddingRight()) - (this.mSignWidth / 2)) - this.mSignBorderSize));
        }
        this.mTrackLength = this.mRight - this.mLeft;
        this.mSectionOffset = (this.mTrackLength * 1.0f) / ((float) this.mSectionCount);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgress = bundle.getFloat("progress");
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
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
                SignSeekBar.this.requestLayout();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        long j = 0;
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                getParent().requestDisallowInterceptTouchEvent(true);
                this.isThumbOnDragging = isThumbTouched(motionEvent);
                if (this.isThumbOnDragging) {
                    if (this.isSeekBySection && !this.triggerSeekBySection) {
                        this.triggerSeekBySection = true;
                    }
                    invalidate();
                } else if (this.isTouchToSeek && isTrackTouched(motionEvent)) {
                    this.isThumbOnDragging = true;
                    this.mThumbCenterX = motionEvent.getX();
                    if (this.mThumbCenterX < this.mLeft) {
                        this.mThumbCenterX = this.mLeft;
                    }
                    if (this.mThumbCenterX > this.mRight) {
                        this.mThumbCenterX = this.mRight;
                    }
                    this.mProgress = (((this.mThumbCenterX - this.mLeft) * this.mDelta) / this.mTrackLength) + this.mMin;
                    invalidate();
                }
                this.dx = this.mThumbCenterX - motionEvent.getX();
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                long j2 = 300;
                if (this.isAutoAdjustSectionMark) {
                    if (this.isTouchToSeek) {
                        AnonymousClass2 r6 = new Runnable() {
                            public void run() {
                                boolean unused = SignSeekBar.this.isTouchToSeekAnimEnd = false;
                                SignSeekBar.this.autoAdjustSection();
                            }
                        };
                        if (!this.isThumbOnDragging) {
                            j = 300;
                        }
                        postDelayed(r6, j);
                    } else {
                        autoAdjustSection();
                    }
                } else if (this.isThumbOnDragging || this.isTouchToSeek) {
                    ViewPropertyAnimator duration = animate().setDuration(this.mAnimDuration);
                    if (this.isThumbOnDragging || !this.isTouchToSeek) {
                        j2 = 0;
                    }
                    duration.setStartDelay(j2).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationCancel(Animator animator) {
                            boolean unused = SignSeekBar.this.isThumbOnDragging = false;
                            SignSeekBar.this.invalidate();
                        }

                        public void onAnimationEnd(Animator animator) {
                            boolean unused = SignSeekBar.this.isThumbOnDragging = false;
                            SignSeekBar.this.invalidate();
                            if (SignSeekBar.this.mProgressListener != null) {
                                SignSeekBar.this.mProgressListener.onProgressChanged(SignSeekBar.this, SignSeekBar.this.getProgress(), SignSeekBar.this.getProgressFloat(), true);
                            }
                        }
                    }).start();
                }
                if (this.mProgressListener != null) {
                    this.mProgressListener.getProgressOnActionUp(this, getProgress(), getProgressFloat());
                    break;
                }
                break;
            case 2:
                if (this.isThumbOnDragging) {
                    this.mThumbCenterX = motionEvent.getX() + this.dx;
                    if (this.mThumbCenterX < this.mLeft) {
                        this.mThumbCenterX = this.mLeft;
                    }
                    if (this.mThumbCenterX > this.mRight) {
                        this.mThumbCenterX = this.mRight;
                    }
                    this.mProgress = (((this.mThumbCenterX - this.mLeft) * this.mDelta) / this.mTrackLength) + this.mMin;
                    invalidate();
                    if (this.mProgressListener != null) {
                        this.mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                        break;
                    }
                }
                break;
        }
        return this.isThumbOnDragging || this.isTouchToSeek || super.onTouchEvent(motionEvent);
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
        postInvalidate();
    }

    public void setProgressWithUnit(float f, String str) {
        setProgress(f);
        this.unit = str;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setUnit(String str) {
        this.unit = str;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setValueFormatListener(OnValueFormatListener onValueFormatListener) {
        this.mValueFormatListener = onValueFormatListener;
    }
}
