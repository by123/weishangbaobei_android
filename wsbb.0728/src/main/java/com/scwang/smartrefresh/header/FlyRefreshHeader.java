package com.scwang.smartrefresh.header;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public class FlyRefreshHeader extends FalsifyHeader implements RefreshHeader {
    protected float mCurrentPercent;
    protected AnimatorSet mFlyAnimator;
    protected View mFlyView;
    protected boolean mIsRefreshing = false;
    protected int mOffset = 0;
    protected RefreshKernel mRefreshKernel;
    protected RefreshLayout mRefreshLayout;
    protected MountainSceneView mSceneView;

    public FlyRefreshHeader(Context context) {
        super(context);
    }

    public FlyRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FlyRefreshHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void finishRefresh() {
        finishRefresh((AnimatorListenerAdapter) null);
    }

    public void finishRefresh(final AnimatorListenerAdapter animatorListenerAdapter) {
        if (this.mFlyView != null && this.mIsRefreshing && this.mRefreshLayout != null) {
            if (this.mFlyAnimator != null) {
                this.mFlyAnimator.end();
                this.mFlyView.clearAnimation();
            }
            this.mIsRefreshing = false;
            this.mRefreshLayout.finishRefresh(0);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(800);
            View view = this.mFlyView;
            float f = (float) (-this.mFlyView.getRight());
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{this.mFlyView.getTranslationX(), f});
            View view2 = this.mFlyView;
            float f2 = (float) (-DensityUtil.dp2px(10.0f));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "translationY", new float[]{this.mFlyView.getTranslationY(), f2});
            ofFloat2.setInterpolator(PathInterpolatorCompat.create(0.1f, 1.0f));
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mFlyView, "rotation", new float[]{this.mFlyView.getRotation(), 0.0f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mFlyView, "rotationX", new float[]{this.mFlyView.getRotationX(), 30.0f});
            ofFloat3.setInterpolator(new AccelerateInterpolator());
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ObjectAnimator.ofFloat(this.mFlyView, "scaleX", new float[]{this.mFlyView.getScaleX(), 0.9f}), ObjectAnimator.ofFloat(this.mFlyView, "scaleY", new float[]{this.mFlyView.getScaleY(), 0.9f})});
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    if (FlyRefreshHeader.this.mFlyView != null) {
                        FlyRefreshHeader.this.mFlyView.setRotationY(180.0f);
                    }
                }
            });
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(800);
            animatorSet2.setInterpolator(new DecelerateInterpolator());
            animatorSet2.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mFlyView, "translationX", new float[]{f, 0.0f}), ObjectAnimator.ofFloat(this.mFlyView, "translationY", new float[]{f2, 0.0f}), ObjectAnimator.ofFloat(this.mFlyView, "rotationX", new float[]{30.0f, CropImageView.DEFAULT_ASPECT_RATIO}), ObjectAnimator.ofFloat(this.mFlyView, "scaleX", new float[]{0.9f, 1.0f}), ObjectAnimator.ofFloat(this.mFlyView, "scaleY", new float[]{0.9f, 1.0f})});
            animatorSet2.setStartDelay(100);
            animatorSet2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (FlyRefreshHeader.this.mRefreshLayout != null) {
                        FlyRefreshHeader.this.mRefreshLayout.setEnableRefresh(true);
                    }
                    if (animatorListenerAdapter != null) {
                        animatorListenerAdapter.onAnimationEnd(animator);
                    }
                }

                public void onAnimationStart(Animator animator) {
                    if (FlyRefreshHeader.this.mFlyView != null) {
                        FlyRefreshHeader.this.mFlyView.setRotationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    }
                }
            });
            this.mFlyAnimator = new AnimatorSet();
            this.mFlyAnimator.playSequentially(new Animator[]{animatorSet, animatorSet2});
            this.mFlyAnimator.start();
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mIsRefreshing) {
            finishRefresh();
        }
        return super.onFinish(refreshLayout, z);
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        this.mRefreshLayout = refreshKernel.getRefreshLayout();
        this.mRefreshLayout.setEnableOverScrollDrag(false);
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z || !this.mIsRefreshing) {
            if (i < 0) {
                if (this.mOffset > 0) {
                    i = 0;
                    f = CropImageView.DEFAULT_ASPECT_RATIO;
                } else {
                    return;
                }
            }
            this.mOffset = i;
            this.mCurrentPercent = f;
            if (this.mSceneView != null) {
                this.mSceneView.updatePercent(f);
                this.mSceneView.postInvalidate();
            }
            if (this.mFlyView != null) {
                int i4 = i2 + i3;
                if (i4 > 0) {
                    this.mFlyView.setRotation((((float) i) * -45.0f) / ((float) i4));
                } else {
                    this.mFlyView.setRotation(f * -45.0f);
                }
            }
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mRefreshKernel.animSpinner(0);
        if (this.mCurrentPercent > CropImageView.DEFAULT_ASPECT_RATIO) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mCurrentPercent, 0.0f});
            ofFloat.setDuration(300);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FlyRefreshHeader.this.onMoving(true, ((Float) valueAnimator.getAnimatedValue()).floatValue(), 0, 0, 0);
                }
            });
            ofFloat.start();
            this.mCurrentPercent = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        if (this.mFlyView != null && !this.mIsRefreshing) {
            if (this.mFlyAnimator != null) {
                this.mFlyAnimator.end();
                this.mFlyView.clearAnimation();
            }
            this.mIsRefreshing = true;
            refreshLayout.setEnableRefresh(false);
            int width = ((View) this.mRefreshLayout).getWidth();
            int left = this.mFlyView.getLeft();
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mFlyView, "translationX", new float[]{0.0f, (float) (width - left)});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mFlyView, "translationY", new float[]{0.0f, (float) (((-(this.mFlyView.getTop() - this.mOffset)) * 2) / 3)});
            ofFloat3.setInterpolator(PathInterpolatorCompat.create(0.7f, 1.0f));
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mFlyView, "rotation", new float[]{this.mFlyView.getRotation(), 0.0f});
            ofFloat4.setInterpolator(new DecelerateInterpolator());
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.mFlyView, "rotationX", new float[]{this.mFlyView.getRotationX(), 50.0f});
            ofFloat5.setInterpolator(new DecelerateInterpolator());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(800);
            animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat3, ofFloat4, ofFloat5, ObjectAnimator.ofFloat(this.mFlyView, "scaleX", new float[]{this.mFlyView.getScaleX(), 0.5f}), ObjectAnimator.ofFloat(this.mFlyView, "scaleY", new float[]{this.mFlyView.getScaleY(), 0.5f})});
            this.mFlyAnimator = animatorSet;
            this.mFlyAnimator.start();
        }
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0 && this.mSceneView != null) {
            this.mSceneView.setPrimaryColor(iArr[0]);
        }
    }

    public void setUp(@Nullable MountainSceneView mountainSceneView, @Nullable View view) {
        this.mFlyView = view;
        this.mSceneView = mountainSceneView;
    }
}
