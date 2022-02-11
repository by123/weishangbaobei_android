package com.wx.assistants.dialog.config;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import com.wx.assistants.dialog.listeners.OnDialogDismissListener;
import com.yalantis.ucrop.view.CropImageView;

public class MDialogConfig {
    public int animationID;
    public int backgroundViewColor;
    public int backgroundWindowColor;
    public boolean cancelable;
    public boolean canceledOnTouchOutside;
    public float cornerRadius;
    public int imgHeight;
    public int imgWidth;
    public OnDialogDismissListener onDialogDismissListener;
    public int paddingBottom;
    public int paddingLeft;
    public int paddingRight;
    public int paddingTop;
    public int progressColor;
    public int progressRimColor;
    public int progressRimWidth;
    public float progressWidth;
    public int strokeColor;
    public float strokeWidth;
    public int textColor;
    public float textSize;

    private MDialogConfig() {
        this.canceledOnTouchOutside = false;
        this.cancelable = false;
        this.backgroundWindowColor = 0;
        this.backgroundViewColor = Color.parseColor("#b2000000");
        this.strokeColor = 0;
        this.cornerRadius = 8.0f;
        this.strokeWidth = CropImageView.DEFAULT_ASPECT_RATIO;
        this.progressColor = -1;
        this.progressWidth = 2.0f;
        this.progressRimColor = 0;
        this.progressRimWidth = 0;
        this.textColor = -1;
        this.textSize = 12.0f;
        this.animationID = 0;
        this.paddingLeft = 12;
        this.paddingTop = 12;
        this.paddingRight = 12;
        this.paddingBottom = 12;
        this.imgWidth = 40;
        this.imgHeight = 40;
    }

    public static class Builder {
        private MDialogConfig mDialogConfig = new MDialogConfig();

        public MDialogConfig build() {
            return this.mDialogConfig;
        }

        public Builder isCanceledOnTouchOutside(@Nullable boolean z) {
            this.mDialogConfig.canceledOnTouchOutside = z;
            return this;
        }

        public Builder isCancelable(@Nullable boolean z) {
            this.mDialogConfig.cancelable = z;
            return this;
        }

        public Builder setBackgroundWindowColor(@Nullable int i) {
            this.mDialogConfig.backgroundWindowColor = i;
            return this;
        }

        public Builder setBackgroundViewColor(@Nullable int i) {
            this.mDialogConfig.backgroundViewColor = i;
            return this;
        }

        public Builder setStrokeColor(@Nullable int i) {
            this.mDialogConfig.strokeColor = i;
            return this;
        }

        public Builder setStrokeWidth(@Nullable float f) {
            this.mDialogConfig.strokeWidth = f;
            return this;
        }

        public Builder setCornerRadius(@Nullable float f) {
            this.mDialogConfig.cornerRadius = f;
            return this;
        }

        public Builder setProgressColor(@Nullable int i) {
            this.mDialogConfig.progressColor = i;
            return this;
        }

        public Builder setProgressWidth(@Nullable float f) {
            this.mDialogConfig.progressWidth = f;
            return this;
        }

        public Builder setProgressRimColor(int i) {
            this.mDialogConfig.progressRimColor = i;
            return this;
        }

        public Builder setProgressRimWidth(int i) {
            this.mDialogConfig.progressRimWidth = i;
            return this;
        }

        public Builder setTextColor(@Nullable int i) {
            this.mDialogConfig.textColor = i;
            return this;
        }

        public Builder setTextSize(float f) {
            this.mDialogConfig.textSize = f;
            return this;
        }

        public Builder setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
            this.mDialogConfig.onDialogDismissListener = onDialogDismissListener;
            return this;
        }

        public Builder setAnimationID(@StyleRes int i) {
            this.mDialogConfig.animationID = i;
            return this;
        }

        public Builder setImgWidthAndHeight(int i, int i2) {
            this.mDialogConfig.imgWidth = i;
            this.mDialogConfig.imgHeight = i2;
            return this;
        }

        public Builder setPadding(int i, int i2, int i3, int i4) {
            this.mDialogConfig.paddingLeft = i;
            this.mDialogConfig.paddingTop = i2;
            this.mDialogConfig.paddingRight = i3;
            this.mDialogConfig.paddingBottom = i4;
            return this;
        }
    }
}
