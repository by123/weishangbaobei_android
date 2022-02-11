package com.wx.assistants.dialog;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wx.assistants.dialog.utils.MSizeUtils;
import com.wx.assistants.dialog.view.MNHudCircularProgressBar;
import com.yalantis.ucrop.view.CropImageView;

public class MProgressBarDialog {
    public static final int MProgressBarDialogStyle_Circle = 1;
    public static final int MProgressBarDialogStyle_Horizontal = 0;
    private MNHudCircularProgressBar circularProgressBar;
    private RelativeLayout dialog_view_bg;
    private RelativeLayout dialog_window_background;
    /* access modifiers changed from: private */
    public ProgressBar horizontalProgressBar;
    private Builder mBuilder;
    private Context mContext;
    private Dialog mDialog;
    private long mDuration;
    private TextView tvShow;

    public static final class Builder {
        int animationID = 0;
        int backgroundViewColor = this.mContext.getResources().getColor(2131099890);
        int backgroundWindowColor = this.mContext.getResources().getColor(2131099891);
        int circleProgressBarBackgroundWidth = 1;
        int circleProgressBarWidth = 3;
        float cornerRadius = 6.0f;
        int horizontalProgressBarHeight = 4;
        private Context mContext;
        int progressColor = this.mContext.getResources().getColor(2131099886);
        float progressCornerRadius = 2.0f;
        int progressbarBackgroundColor = this.mContext.getResources().getColor(2131099884);
        int strokeColor = this.mContext.getResources().getColor(2131099889);
        float strokeWidth = CropImageView.DEFAULT_ASPECT_RATIO;
        int style = 0;
        int textColor = this.mContext.getResources().getColor(2131099888);

        public Builder(Context context) {
            this.mContext = context;
        }

        public MProgressBarDialog build() {
            return new MProgressBarDialog(this.mContext, this);
        }

        public Builder setAnimationID(@StyleRes int i) {
            this.animationID = i;
            return this;
        }

        public Builder setBackgroundViewColor(@Nullable int i) {
            this.backgroundViewColor = i;
            return this;
        }

        public Builder setBackgroundWindowColor(@Nullable int i) {
            this.backgroundWindowColor = i;
            return this;
        }

        public Builder setCircleProgressBarBackgroundWidth(@Nullable int i) {
            this.circleProgressBarBackgroundWidth = i;
            return this;
        }

        public Builder setCircleProgressBarWidth(@Nullable int i) {
            this.circleProgressBarWidth = i;
            return this;
        }

        public Builder setCornerRadius(@Nullable float f) {
            this.cornerRadius = f;
            return this;
        }

        public Builder setHorizontalProgressBarHeight(@Nullable int i) {
            this.horizontalProgressBarHeight = i;
            return this;
        }

        public Builder setProgressColor(@Nullable int i) {
            this.progressColor = i;
            return this;
        }

        public Builder setProgressCornerRadius(@Nullable int i) {
            this.progressCornerRadius = (float) i;
            return this;
        }

        public Builder setProgressbarBackgroundColor(@Nullable int i) {
            this.progressbarBackgroundColor = i;
            return this;
        }

        public Builder setStrokeColor(@Nullable int i) {
            this.strokeColor = i;
            return this;
        }

        public Builder setStrokeWidth(@Nullable float f) {
            this.strokeWidth = f;
            return this;
        }

        public Builder setStyle(@Nullable int i) {
            this.style = i;
            return this;
        }

        public Builder setTextColor(@Nullable int i) {
            this.textColor = i;
            return this;
        }
    }

    public MProgressBarDialog(Context context) {
        this(context, new Builder(context));
    }

    public MProgressBarDialog(Context context, Builder builder) {
        this.mDuration = 300;
        this.mContext = context;
        this.mBuilder = builder;
        if (this.mBuilder == null) {
            this.mBuilder = new Builder(this.mContext);
        }
        initDialog();
    }

    private void configView() {
        if (this.mBuilder == null) {
            this.mBuilder = new Builder(this.mContext);
        }
        this.dialog_window_background.setBackgroundColor(this.mBuilder.backgroundWindowColor);
        this.tvShow.setTextColor(this.mBuilder.textColor);
        GradientDrawable gradientDrawable = (GradientDrawable) this.dialog_view_bg.getBackground();
        gradientDrawable.setColor(this.mBuilder.backgroundViewColor);
        gradientDrawable.setStroke(MSizeUtils.dp2px(this.mContext, this.mBuilder.strokeWidth), this.mBuilder.strokeColor);
        gradientDrawable.setCornerRadius((float) MSizeUtils.dp2px(this.mContext, this.mBuilder.cornerRadius));
        if (Build.VERSION.SDK_INT >= 16) {
            this.dialog_view_bg.setBackground(gradientDrawable);
        } else {
            this.dialog_view_bg.setBackgroundDrawable(gradientDrawable);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(this.mBuilder.progressbarBackgroundColor);
        gradientDrawable2.setCornerRadius((float) MSizeUtils.dp2px(this.mContext, this.mBuilder.progressCornerRadius));
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setColor(this.mBuilder.progressbarBackgroundColor);
        gradientDrawable3.setCornerRadius((float) MSizeUtils.dp2px(this.mContext, this.mBuilder.progressCornerRadius));
        ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable3, 3, 1);
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setColor(this.mBuilder.progressColor);
        gradientDrawable4.setCornerRadius((float) MSizeUtils.dp2px(this.mContext, this.mBuilder.progressCornerRadius));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable2, clipDrawable, new ClipDrawable(gradientDrawable4, 3, 1)});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.horizontalProgressBar.setProgressDrawable(layerDrawable);
        ViewGroup.LayoutParams layoutParams = this.horizontalProgressBar.getLayoutParams();
        layoutParams.height = MSizeUtils.dp2px(this.mContext, (float) this.mBuilder.horizontalProgressBarHeight);
        this.horizontalProgressBar.setLayoutParams(layoutParams);
        this.circularProgressBar.setBackgroundColor(this.mBuilder.progressbarBackgroundColor);
        this.circularProgressBar.setColor(this.mBuilder.progressColor);
        this.circularProgressBar.setProgressBarWidth((float) MSizeUtils.dp2px(this.mContext, (float) this.mBuilder.circleProgressBarWidth));
        this.circularProgressBar.setBackgroundProgressBarWidth((float) MSizeUtils.dp2px(this.mContext, (float) this.mBuilder.circleProgressBarBackgroundWidth));
    }

    private void initDialog() {
        View inflate = LayoutInflater.from(this.mContext).inflate(2131427587, (ViewGroup) null);
        this.mDialog = new Dialog(this.mContext, 2131755193);
        this.mDialog.setCancelable(false);
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.setContentView(inflate);
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        this.mDialog.getWindow().setAttributes(attributes);
        if (this.mBuilder.animationID != 0) {
            try {
                this.mDialog.getWindow().setWindowAnimations(this.mBuilder.animationID);
            } catch (Exception e) {
            }
        }
        this.dialog_window_background = (RelativeLayout) inflate.findViewById(2131296596);
        this.dialog_view_bg = (RelativeLayout) inflate.findViewById(2131296594);
        this.tvShow = (TextView) inflate.findViewById(2131297542);
        this.horizontalProgressBar = (ProgressBar) inflate.findViewById(2131296748);
        this.circularProgressBar = (MNHudCircularProgressBar) inflate.findViewById(2131296491);
        this.horizontalProgressBar.setVisibility(8);
        this.circularProgressBar.setVisibility(8);
        this.horizontalProgressBar.setProgress(0);
        this.horizontalProgressBar.setSecondaryProgress(0);
        this.circularProgressBar.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
        this.tvShow.setText("");
        configView();
    }

    public void dismiss() {
        try {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
                this.mDialog = null;
                this.mContext = null;
                this.mBuilder = null;
                this.dialog_window_background = null;
                this.dialog_view_bg = null;
                this.tvShow = null;
                this.horizontalProgressBar = null;
                this.circularProgressBar = null;
            }
        } catch (Exception e) {
        }
    }

    public boolean isShowing() {
        if (this.mDialog != null) {
            return this.mDialog.isShowing();
        }
        return false;
    }

    public void refreshBuilder(Builder builder) {
        this.mBuilder = builder;
        if (this.mBuilder == null) {
            this.mBuilder = new Builder(this.mContext);
        }
        configView();
    }

    public void showProgress(int i, int i2, String str) {
        showProgress(i, i2, str, true);
    }

    public void showProgress(int i, int i2, String str, boolean z) {
        if (this.mBuilder.style == 0) {
            if (this.horizontalProgressBar.getVisibility() == 8) {
                this.horizontalProgressBar.setVisibility(0);
            }
            if (!z) {
                this.horizontalProgressBar.setProgress(i);
                this.horizontalProgressBar.setSecondaryProgress(i2);
            } else {
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.horizontalProgressBar.getProgress(), i});
                ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
                ofInt.setDuration(this.mDuration);
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        MProgressBarDialog.this.horizontalProgressBar.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofInt.start();
                ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.horizontalProgressBar.getSecondaryProgress(), i2});
                ofInt2.setInterpolator(new AccelerateDecelerateInterpolator());
                ofInt2.setDuration(this.mDuration);
                ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        MProgressBarDialog.this.horizontalProgressBar.setSecondaryProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofInt2.start();
            }
        } else {
            if (this.circularProgressBar.getVisibility() == 8) {
                this.circularProgressBar.setVisibility(0);
            }
            this.circularProgressBar.setProgress((float) i, z);
        }
        this.tvShow.setText(str);
        this.mDialog.show();
    }

    public void showProgress(int i, String str) {
        showProgress(i, 0, str, true);
    }

    public void showProgress(int i, String str, boolean z) {
        showProgress(i, 0, str, z);
    }
}
