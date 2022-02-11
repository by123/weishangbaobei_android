package com.wx.assistants.dialog.config;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import com.yalantis.ucrop.view.CropImageView;

public class MToastConfig {
    public int imgHeight;
    public int imgWidth;
    public int paddingBottom;
    public int paddingLeft;
    public int paddingRight;
    public int paddingTop;
    public int toastBackgroundColor;
    public float toastBackgroundCornerRadius;
    public int toastBackgroundStrokeColor;
    public float toastBackgroundStrokeWidth;
    public MToastGravity toastGravity;
    public Drawable toastIcon;
    public int toastTextColor;
    public float toastTextSize;
    public int xOffsets;
    public int yOffsets;

    public static class Builder {
        private MToastConfig mToastConfig;

        public Builder() {
            this.mToastConfig = null;
            this.mToastConfig = new MToastConfig();
        }

        public MToastConfig build() {
            return this.mToastConfig;
        }

        public Builder setBackgroundColor(@ColorInt int i) {
            this.mToastConfig.toastBackgroundColor = i;
            return this;
        }

        public Builder setBackgroundCornerRadius(float f) {
            this.mToastConfig.toastBackgroundCornerRadius = f;
            return this;
        }

        public Builder setBackgroundStrokeColor(@ColorInt int i) {
            this.mToastConfig.toastBackgroundStrokeColor = i;
            return this;
        }

        public Builder setBackgroundStrokeWidth(float f) {
            this.mToastConfig.toastBackgroundStrokeWidth = f;
            return this;
        }

        public Builder setGravity(MToastGravity mToastGravity) {
            this.mToastConfig.toastGravity = mToastGravity;
            return this;
        }

        public Builder setGravity(MToastGravity mToastGravity, int i, int i2) {
            this.mToastConfig.toastGravity = mToastGravity;
            this.mToastConfig.xOffsets = i;
            this.mToastConfig.yOffsets = i2;
            return this;
        }

        public Builder setImgWidthAndHeight(int i, int i2) {
            this.mToastConfig.imgWidth = i;
            this.mToastConfig.imgHeight = i2;
            return this;
        }

        public Builder setPadding(int i, int i2, int i3, int i4) {
            this.mToastConfig.paddingLeft = i;
            this.mToastConfig.paddingTop = i2;
            this.mToastConfig.paddingRight = i3;
            this.mToastConfig.paddingBottom = i4;
            return this;
        }

        public Builder setTextColor(@ColorInt int i) {
            this.mToastConfig.toastTextColor = i;
            return this;
        }

        public Builder setTextSize(float f) {
            this.mToastConfig.toastTextSize = f;
            return this;
        }

        public Builder setToastIcon(Drawable drawable) {
            this.mToastConfig.toastIcon = drawable;
            return this;
        }
    }

    public enum MToastGravity {
        CENTRE,
        BOTTOM
    }

    private MToastConfig() {
        this.toastTextSize = 13.0f;
        this.toastTextColor = Color.parseColor("#FFFFFFFF");
        this.toastBackgroundColor = Color.parseColor("#b2000000");
        this.toastBackgroundCornerRadius = 4.0f;
        this.toastBackgroundStrokeWidth = CropImageView.DEFAULT_ASPECT_RATIO;
        this.toastBackgroundStrokeColor = Color.parseColor("#00000000");
        this.toastGravity = MToastGravity.BOTTOM;
        this.xOffsets = 0;
        this.yOffsets = 0;
        this.toastIcon = null;
        this.paddingLeft = 20;
        this.paddingTop = 12;
        this.paddingRight = 20;
        this.paddingBottom = 12;
        this.imgWidth = 20;
        this.imgHeight = 20;
    }
}
