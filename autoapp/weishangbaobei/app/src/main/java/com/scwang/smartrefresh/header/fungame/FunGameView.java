package com.scwang.smartrefresh.header.fungame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scwang.smartrefresh.header.R;
import com.scwang.smartrefresh.header.fungame.FunGameView;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public abstract class FunGameView<T extends FunGameView> extends FunGameBase {
    protected static final int STATUS_GAME_FAIL = 4;
    protected static final int STATUS_GAME_FINISHED = 3;
    protected static final int STATUS_GAME_OVER = 2;
    protected static final int STATUS_GAME_PLAY = 1;
    protected static final int STATUS_GAME_PREPARE = 0;
    protected static final float VIEW_HEIGHT_RATIO = 0.161f;
    protected float DIVIDING_LINE_SIZE = 1.0f;
    protected float controllerPosition;
    protected int controllerSize;
    protected int lModelColor;
    protected int mBackColor;
    protected int mBoundaryColor = -10461088;
    protected int mHalfHeaderHeight;
    public String mMaskTextBottom;
    public String mMaskTextTopPull;
    public String mMaskTextTopRelease;
    protected TextView mMaskViewBottom;
    protected TextView mMaskViewTop;
    protected int mModelColor;
    protected Paint mPaint;
    protected Paint mPaintText;
    protected View mShadowView;
    public String mTextGameOver;
    public String mTextLoading;
    public String mTextLoadingFailed;
    public String mTextLoadingFinish;
    protected int rModelColor;
    protected int status = 0;

    /* access modifiers changed from: protected */
    public abstract void drawGame(Canvas canvas, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void resetConfigParams();

    public FunGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FunGameView);
        this.mMaskTextBottom = getResources().getString(R.string.fgh_mask_bottom);
        this.mMaskTextTopPull = getResources().getString(R.string.fgh_mask_top_pull);
        this.mMaskTextTopRelease = getResources().getString(R.string.fgh_mask_top_release);
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghMaskTextTop)) {
            String string = obtainStyledAttributes.getString(R.styleable.FunGameView_fghMaskTextTop);
            this.mMaskTextTopRelease = string;
            this.mMaskTextTopPull = string;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghMaskTextTopPull)) {
            this.mMaskTextTopPull = obtainStyledAttributes.getString(R.styleable.FunGameView_fghMaskTextTopPull);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghMaskTextTopRelease)) {
            this.mMaskTextTopRelease = obtainStyledAttributes.getString(R.styleable.FunGameView_fghMaskTextTopRelease);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghMaskTextBottom)) {
            this.mMaskTextBottom = obtainStyledAttributes.getString(R.styleable.FunGameView_fghMaskTextBottom);
        }
        int applyDimension = (int) TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics());
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FunGameView_fghMaskTextSizeTop, applyDimension);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FunGameView_fghMaskTextSizeBottom, (applyDimension * 14) / 16);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.mShadowView = new RelativeLayout(context);
        this.mShadowView.setBackgroundColor(-12961222);
        this.mMaskViewTop = createMaskView(context, this.mMaskTextTopPull, dimensionPixelSize, 80);
        this.mMaskViewBottom = createMaskView(context, this.mMaskTextBottom, dimensionPixelSize2, 48);
        if (!isInEditMode()) {
            int dp2px = DensityUtil.dp2px(100.0f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dp2px);
            addView(this.mShadowView, layoutParams);
            addView(relativeLayout, layoutParams);
            this.mHalfHeaderHeight = (int) (((float) dp2px) * 0.5f);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, this.mHalfHeaderHeight);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.mHalfHeaderHeight);
            layoutParams3.topMargin = dp2px - this.mHalfHeaderHeight;
            relativeLayout.addView(this.mMaskViewTop, layoutParams2);
            relativeLayout.addView(this.mMaskViewBottom, layoutParams3);
        }
        this.DIVIDING_LINE_SIZE = (float) Math.max(1, DensityUtil.dp2px(0.5f));
        this.mPaint = new Paint(1);
        this.mPaint.setStrokeWidth(this.DIVIDING_LINE_SIZE);
        this.controllerPosition = this.DIVIDING_LINE_SIZE;
        this.mPaintText = new TextPaint(1);
        this.mPaintText.setColor(-4078910);
        this.mTextGameOver = context.getString(R.string.fgh_text_game_over);
        this.mTextLoading = context.getString(R.string.fgh_text_loading);
        this.mTextLoadingFinish = context.getString(R.string.fgh_text_loading_finish);
        this.mTextLoadingFailed = context.getString(R.string.fgh_text_loading_failed);
        this.mBackColor = obtainStyledAttributes.getColor(R.styleable.FunGameView_fghBackColor, 0);
        this.lModelColor = obtainStyledAttributes.getColor(R.styleable.FunGameView_fghLeftColor, -16777216);
        this.mModelColor = obtainStyledAttributes.getColor(R.styleable.FunGameView_fghMiddleColor, -16777216);
        this.rModelColor = obtainStyledAttributes.getColor(R.styleable.FunGameView_fghRightColor, -5921371);
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghTextGameOver)) {
            this.mTextGameOver = obtainStyledAttributes.getString(R.styleable.FunGameView_fghTextGameOver);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghTextLoading)) {
            this.mTextLoading = obtainStyledAttributes.getString(R.styleable.FunGameView_fghTextLoading);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghTextLoadingFinished)) {
            this.mTextLoadingFinish = obtainStyledAttributes.getString(R.styleable.FunGameView_fghTextLoadingFinished);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.FunGameView_fghTextLoadingFailed)) {
            this.mTextLoadingFailed = obtainStyledAttributes.getString(R.styleable.FunGameView_fghTextLoadingFailed);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public TextView createMaskView(Context context, String str, int i, int i2) {
        TextView textView = new TextView(context);
        textView.setTextColor(-16777216);
        textView.setGravity(i2 | 1);
        textView.setTextSize(0, (float) i);
        textView.setText(str);
        textView.setBackgroundColor(-1);
        return textView;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int i = this.mHeaderHeight;
        drawBoundary(canvas, width, i);
        drawText(canvas, width, i);
        drawGame(canvas, width, i);
        super.dispatchDraw(canvas);
    }

    private void drawBoundary(Canvas canvas, int i, int i2) {
        this.mPaint.setColor(this.mBackColor);
        float f = (float) i2;
        Canvas canvas2 = canvas;
        float f2 = (float) i;
        canvas2.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f2, f, this.mPaint);
        this.mPaint.setColor(this.mBoundaryColor);
        canvas2.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f2, CropImageView.DEFAULT_ASPECT_RATIO, this.mPaint);
        canvas2.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, f - this.DIVIDING_LINE_SIZE, f2, f - this.DIVIDING_LINE_SIZE, this.mPaint);
    }

    private void drawText(Canvas canvas, int i, int i2) {
        switch (this.status) {
            case 0:
            case 1:
                this.mPaintText.setTextSize((float) DensityUtil.dp2px(25.0f));
                promptText(canvas, this.mTextLoading, i, i2);
                return;
            case 2:
                this.mPaintText.setTextSize((float) DensityUtil.dp2px(25.0f));
                promptText(canvas, this.mTextGameOver, i, i2);
                return;
            case 3:
                this.mPaintText.setTextSize((float) DensityUtil.dp2px(20.0f));
                promptText(canvas, this.mTextLoadingFinish, i, i2);
                return;
            case 4:
                this.mPaintText.setTextSize((float) DensityUtil.dp2px(20.0f));
                promptText(canvas, this.mTextLoadingFailed, i, i2);
                return;
            default:
                return;
        }
    }

    private void promptText(Canvas canvas, String str, int i, int i2) {
        canvas.drawText(str, (((float) i) - this.mPaintText.measureText(str)) * 0.5f, (((float) i2) * 0.5f) - ((this.mPaintText.ascent() + this.mPaintText.descent()) * 0.5f), this.mPaintText);
    }

    public void postStatus(int i) {
        this.status = i;
        if (i == 0) {
            resetConfigParams();
        }
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onManualOperationMove(float f, int i, int i2, int i3) {
        float max = (float) Math.max(i, 0);
        float f2 = (((float) this.mHeaderHeight) - (this.DIVIDING_LINE_SIZE * 2.0f)) - ((float) this.controllerSize);
        if (max > f2) {
            max = f2;
        }
        this.controllerPosition = max;
        postInvalidate();
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        if (this.mHeaderHeight != i && !isInEditMode()) {
            TextView textView = this.mMaskViewTop;
            TextView textView2 = this.mMaskViewBottom;
            this.mHalfHeaderHeight = (int) (((float) i) * 0.5f);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
            int i3 = this.mHalfHeaderHeight;
            layoutParams2.height = i3;
            layoutParams.height = i3;
            layoutParams2.topMargin = i - this.mHalfHeaderHeight;
            textView.setLayoutParams(layoutParams);
            textView2.setLayoutParams(layoutParams2);
        }
        super.onInitialized(refreshKernel, i, i2);
        postStatus(0);
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        super.setPrimaryColors(iArr);
        if (iArr.length > 0) {
            this.mMaskViewTop.setTextColor(iArr[0]);
            this.mMaskViewBottom.setTextColor(iArr[0]);
            int i = iArr[0];
            this.mBackColor = i;
            this.mBoundaryColor = i;
            if (this.mBackColor == 0 || this.mBackColor == -1) {
                this.mBoundaryColor = -10461088;
            }
            if (iArr.length > 1) {
                TextView textView = this.mMaskViewTop;
                TextView textView2 = this.mMaskViewBottom;
                this.mShadowView.setBackgroundColor(iArr[1]);
                textView.setBackgroundColor(iArr[1]);
                textView2.setBackgroundColor(iArr[1]);
                this.mModelColor = iArr[1];
                this.lModelColor = ColorUtils.setAlphaComponent(iArr[1], 225);
                this.rModelColor = ColorUtils.setAlphaComponent(iArr[1], 200);
                this.mPaintText.setColor(ColorUtils.setAlphaComponent(iArr[1], 150));
            }
        }
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        super.onStateChanged(refreshLayout, refreshState, refreshState2);
        switch (refreshState2) {
            case PullDownToRefresh:
                this.mMaskViewTop.setText(this.mMaskTextTopPull);
                return;
            case ReleaseToRefresh:
                this.mMaskViewTop.setText(this.mMaskTextTopRelease);
                return;
            default:
                return;
        }
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        super.onStartAnimator(refreshLayout, i, i2);
        final TextView textView = this.mMaskViewTop;
        final View view = this.mShadowView;
        final TextView textView2 = this.mMaskViewBottom;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(textView, "translationY", new float[]{textView.getTranslationY(), (float) (-this.mHalfHeaderHeight)})).with(ObjectAnimator.ofFloat(textView2, "translationY", new float[]{textView2.getTranslationY(), (float) this.mHalfHeaderHeight})).with(ObjectAnimator.ofFloat(view, "alpha", new float[]{view.getAlpha(), 0.0f}));
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
                view.setVisibility(8);
                FunGameView.this.postStatus(1);
            }
        });
        animatorSet.setDuration(800);
        animatorSet.setStartDelay(200);
        animatorSet.start();
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mManualOperation) {
            postStatus(z ? 3 : 4);
        } else {
            postStatus(0);
            TextView textView = this.mMaskViewTop;
            TextView textView2 = this.mMaskViewBottom;
            View view = this.mShadowView;
            textView.setTranslationY(textView.getTranslationY() + ((float) this.mHalfHeaderHeight));
            textView2.setTranslationY(textView2.getTranslationY() - ((float) this.mHalfHeaderHeight));
            view.setAlpha(1.0f);
            textView.setVisibility(0);
            textView2.setVisibility(0);
            view.setVisibility(0);
        }
        return super.onFinish(refreshLayout, z);
    }
}
