package com.wx.assistants.floatwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import com.wx.assistants.floatwindow.FloatWindow;

public class IFloatWindowImpl extends IFloatWindow {
    /* access modifiers changed from: private */
    public float downX;
    /* access modifiers changed from: private */
    public float downY;
    private boolean isShow;
    /* access modifiers changed from: private */
    public ValueAnimator mAnimator;
    /* access modifiers changed from: private */
    public FloatWindow.B mB;
    /* access modifiers changed from: private */
    public boolean mClick = false;
    private TimeInterpolator mDecelerateInterpolator;
    private FloatLifecycle mFloatLifecycle;
    /* access modifiers changed from: private */
    public FloatView mFloatView;
    /* access modifiers changed from: private */
    public int mSlop;
    private boolean once = true;
    /* access modifiers changed from: private */
    public float upX;
    /* access modifiers changed from: private */
    public float upY;

    private IFloatWindowImpl() {
    }

    IFloatWindowImpl(FloatWindow.B b) {
        this.mB = b;
        if (this.mB.mMoveType != 0) {
            if (this.mB.isNoTouch) {
                this.mFloatView = new FloatPhone(b.mApplicationContext, this.mB.mPermissionListener, b.isScreenAlwaysLight);
            } else {
                this.mFloatView = new FloatPhoneNoTouch(b.mApplicationContext, this.mB.mPermissionListener, b.isScreenAlwaysLight);
            }
            initTouchEvent();
        } else if (Build.VERSION.SDK_INT >= 25) {
            this.mFloatView = new FloatPhone(b.mApplicationContext, this.mB.mPermissionListener, b.isScreenAlwaysLight);
        } else {
            this.mFloatView = new FloatToast(b.mApplicationContext, b.isScreenAlwaysLight);
        }
        this.mFloatView.setSize(this.mB.mWidth, this.mB.mHeight);
        this.mFloatView.setGravity(this.mB.gravity, this.mB.xOffset, this.mB.yOffset);
        this.mFloatView.setView(this.mB.mView);
        this.mFloatLifecycle = new FloatLifecycle(this.mB.mApplicationContext, this.mB.mShow, this.mB.mActivities, new LifecycleListener() {
            public void onShow() {
                IFloatWindowImpl.this.show();
            }

            public void onHide() {
                IFloatWindowImpl.this.hide();
            }

            public void onBackToDesktop() {
                if (!IFloatWindowImpl.this.mB.mDesktopShow) {
                    IFloatWindowImpl.this.hide();
                }
                if (IFloatWindowImpl.this.mB.mViewStateListener != null) {
                    IFloatWindowImpl.this.mB.mViewStateListener.onBackToDesktop();
                }
            }
        });
    }

    public void show() {
        if (this.once) {
            this.mFloatView.init();
            this.once = false;
            this.isShow = true;
        } else if (!this.isShow) {
            getView().setVisibility(0);
            this.isShow = true;
        } else {
            return;
        }
        if (this.mB.mViewStateListener != null) {
            this.mB.mViewStateListener.onShow();
        }
    }

    public void hide() {
        if (!this.once && this.isShow) {
            getView().setVisibility(4);
            this.isShow = false;
            if (this.mB.mViewStateListener != null) {
                this.mB.mViewStateListener.onHide();
            }
        }
    }

    public boolean isShowing() {
        return this.isShow;
    }

    /* access modifiers changed from: package-private */
    public void dismiss() {
        this.mFloatView.dismiss();
        this.isShow = false;
        if (this.mB.mViewStateListener != null) {
            this.mB.mViewStateListener.onDismiss();
        }
    }

    public void updateX(int i) {
        checkMoveType();
        this.mB.xOffset = i;
        this.mFloatView.updateX(i);
    }

    public void updateY(int i) {
        checkMoveType();
        this.mB.yOffset = i;
        this.mFloatView.updateY(i);
    }

    public void updateX(int i, float f) {
        int i2;
        checkMoveType();
        FloatWindow.B b = this.mB;
        if (i == 0) {
            i2 = Util.getScreenWidth(this.mB.mApplicationContext);
        } else {
            i2 = Util.getScreenHeight(this.mB.mApplicationContext);
        }
        b.xOffset = (int) (((float) i2) * f);
        this.mFloatView.updateX(this.mB.xOffset);
    }

    public void updateY(int i, float f) {
        int i2;
        checkMoveType();
        FloatWindow.B b = this.mB;
        if (i == 0) {
            i2 = Util.getScreenWidth(this.mB.mApplicationContext);
        } else {
            i2 = Util.getScreenHeight(this.mB.mApplicationContext);
        }
        b.yOffset = (int) (((float) i2) * f);
        this.mFloatView.updateY(this.mB.yOffset);
    }

    public int getX() {
        return this.mFloatView.getX();
    }

    public int getY() {
        return this.mFloatView.getY();
    }

    public View getView() {
        this.mSlop = ViewConfiguration.get(this.mB.mApplicationContext).getScaledTouchSlop();
        return this.mB.mView;
    }

    private void checkMoveType() {
        if (this.mB.mMoveType == 0) {
            throw new IllegalArgumentException("FloatWindow of this tag is not allowed to move!");
        }
    }

    private void initTouchEvent() {
        if (this.mB.mMoveType != 1) {
            getView().setOnTouchListener(new View.OnTouchListener() {
                float changeX;
                float changeY;
                float lastX;
                float lastY;
                int newX;
                int newY;

                @SuppressLint({"ClickableViewAccessibility"})
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int i;
                    switch (motionEvent.getAction()) {
                        case 0:
                            float unused = IFloatWindowImpl.this.downX = motionEvent.getRawX();
                            float unused2 = IFloatWindowImpl.this.downY = motionEvent.getRawY();
                            this.lastX = motionEvent.getRawX();
                            this.lastY = motionEvent.getRawY();
                            IFloatWindowImpl.this.cancelAnimator();
                            break;
                        case 1:
                            float unused3 = IFloatWindowImpl.this.upX = motionEvent.getRawX();
                            float unused4 = IFloatWindowImpl.this.upY = motionEvent.getRawY();
                            boolean unused5 = IFloatWindowImpl.this.mClick = Math.abs(IFloatWindowImpl.this.upX - IFloatWindowImpl.this.downX) > ((float) IFloatWindowImpl.this.mSlop) || Math.abs(IFloatWindowImpl.this.upY - IFloatWindowImpl.this.downY) > ((float) IFloatWindowImpl.this.mSlop);
                            switch (IFloatWindowImpl.this.mB.mMoveType) {
                                case 3:
                                    int x = IFloatWindowImpl.this.mFloatView.getX();
                                    if ((x * 2) + view.getWidth() > Util.getScreenWidth(IFloatWindowImpl.this.mB.mApplicationContext)) {
                                        i = (Util.getScreenWidth(IFloatWindowImpl.this.mB.mApplicationContext) - view.getWidth()) - IFloatWindowImpl.this.mB.mSlideRightMargin;
                                    } else {
                                        i = IFloatWindowImpl.this.mB.mSlideLeftMargin;
                                    }
                                    ValueAnimator unused6 = IFloatWindowImpl.this.mAnimator = ObjectAnimator.ofInt(new int[]{x, i});
                                    IFloatWindowImpl.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                            IFloatWindowImpl.this.mFloatView.updateX(intValue);
                                            if (IFloatWindowImpl.this.mB.mViewStateListener != null) {
                                                IFloatWindowImpl.this.mB.mViewStateListener.onPositionUpdate(intValue, (int) IFloatWindowImpl.this.upY);
                                            }
                                        }
                                    });
                                    IFloatWindowImpl.this.startAnimator();
                                    break;
                                case 4:
                                    ValueAnimator unused7 = IFloatWindowImpl.this.mAnimator = ObjectAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{PropertyValuesHolder.ofInt("x", new int[]{IFloatWindowImpl.this.mFloatView.getX(), IFloatWindowImpl.this.mB.xOffset}), PropertyValuesHolder.ofInt("y", new int[]{IFloatWindowImpl.this.mFloatView.getY(), IFloatWindowImpl.this.mB.yOffset})});
                                    IFloatWindowImpl.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            int intValue = ((Integer) valueAnimator.getAnimatedValue("x")).intValue();
                                            int intValue2 = ((Integer) valueAnimator.getAnimatedValue("y")).intValue();
                                            IFloatWindowImpl.this.mFloatView.updateXY(intValue, intValue2);
                                            if (IFloatWindowImpl.this.mB.mViewStateListener != null) {
                                                IFloatWindowImpl.this.mB.mViewStateListener.onPositionUpdate(intValue, intValue2);
                                            }
                                        }
                                    });
                                    IFloatWindowImpl.this.startAnimator();
                                    break;
                            }
                        case 2:
                            this.changeX = motionEvent.getRawX() - this.lastX;
                            this.changeY = motionEvent.getRawY() - this.lastY;
                            this.newX = (int) (((float) IFloatWindowImpl.this.mFloatView.getX()) + this.changeX);
                            this.newY = (int) (((float) IFloatWindowImpl.this.mFloatView.getY()) + this.changeY);
                            IFloatWindowImpl.this.mFloatView.updateXY(this.newX, this.newY);
                            if (IFloatWindowImpl.this.mB.mViewStateListener != null) {
                                IFloatWindowImpl.this.mB.mViewStateListener.onPositionUpdate(this.newX, this.newY);
                            }
                            this.lastX = motionEvent.getRawX();
                            this.lastY = motionEvent.getRawY();
                            break;
                    }
                    return IFloatWindowImpl.this.mClick;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void startAnimator() {
        if (this.mB.mInterpolator == null) {
            if (this.mDecelerateInterpolator == null) {
                this.mDecelerateInterpolator = new DecelerateInterpolator();
            }
            this.mB.mInterpolator = this.mDecelerateInterpolator;
        }
        this.mAnimator.setInterpolator(this.mB.mInterpolator);
        this.mAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                IFloatWindowImpl.this.mAnimator.removeAllUpdateListeners();
                IFloatWindowImpl.this.mAnimator.removeAllListeners();
                ValueAnimator unused = IFloatWindowImpl.this.mAnimator = null;
                if (IFloatWindowImpl.this.mB.mViewStateListener != null) {
                    IFloatWindowImpl.this.mB.mViewStateListener.onMoveAnimEnd();
                }
            }
        });
        this.mAnimator.setDuration(this.mB.mDuration).start();
        if (this.mB.mViewStateListener != null) {
            this.mB.mViewStateListener.onMoveAnimStart();
        }
    }

    /* access modifiers changed from: private */
    public void cancelAnimator() {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
    }
}
