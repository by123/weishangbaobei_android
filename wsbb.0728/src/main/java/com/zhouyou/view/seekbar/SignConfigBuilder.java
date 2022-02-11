package com.zhouyou.view.seekbar;

import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import java.text.NumberFormat;

public class SignConfigBuilder {
    long animDuration;
    boolean autoAdjustSectionMark;
    String[] bottomSidesLabels;
    boolean floatType;
    NumberFormat format;
    SignSeekBar mSignSeekBar;
    float max;
    float min;
    float progress;
    boolean reverse;
    int secondTrackColor;
    int secondTrackSize;
    int sectionCount;
    int sectionTextColor;
    int sectionTextInterval;
    int sectionTextPosition;
    int sectionTextSize;
    boolean seekBySection;
    boolean showProgressInFloat;
    boolean showSectionMark;
    boolean showSectionText;
    boolean showSign;
    boolean showSignBorder;
    boolean showThumbShadow;
    boolean showThumbText;
    boolean signArrowAutofloat;
    int signArrowHeight;
    int signArrowWidth;
    int signBorderColor;
    int signBorderSize;
    int signColor;
    int signHeight;
    int signRound;
    int signTextColor;
    int signTextSize;
    int signWidth;
    float thumbBgAlpha;
    int thumbColor;
    int thumbRadius;
    int thumbRadiusOnDragging;
    float thumbRatio;
    int thumbTextColor;
    int thumbTextSize;
    boolean touchToSeek;
    int trackColor;
    int trackSize;
    String unit;

    SignConfigBuilder(SignSeekBar signSeekBar) {
        this.mSignSeekBar = signSeekBar;
    }

    public SignConfigBuilder animDuration(long j) {
        this.animDuration = j;
        return this;
    }

    public SignConfigBuilder autoAdjustSectionMark() {
        this.autoAdjustSectionMark = true;
        return this;
    }

    public SignConfigBuilder bottomSidesLabels(String[] strArr) {
        this.bottomSidesLabels = strArr;
        return this;
    }

    public void build() {
        this.mSignSeekBar.config(this);
    }

    public SignConfigBuilder floatType() {
        this.floatType = true;
        return this;
    }

    public SignConfigBuilder format(NumberFormat numberFormat) {
        this.format = numberFormat;
        return this;
    }

    public long getAnimDuration() {
        return this.animDuration;
    }

    public String[] getBottomSidesLabels() {
        return this.bottomSidesLabels;
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public float getMax() {
        return this.max;
    }

    public float getMin() {
        return this.min;
    }

    public float getProgress() {
        return this.progress;
    }

    public int getSecondTrackColor() {
        return this.secondTrackColor;
    }

    public int getSecondTrackSize() {
        return this.secondTrackSize;
    }

    public int getSectionCount() {
        return this.sectionCount;
    }

    public int getSectionTextColor() {
        return this.sectionTextColor;
    }

    public int getSectionTextInterval() {
        return this.sectionTextInterval;
    }

    public int getSectionTextPosition() {
        return this.sectionTextPosition;
    }

    public int getSectionTextSize() {
        return this.sectionTextSize;
    }

    public int getSignArrowHeight() {
        return this.signArrowHeight;
    }

    public int getSignArrowWidth() {
        return this.signArrowWidth;
    }

    public int getSignBorderColor() {
        return this.signBorderColor;
    }

    public int getSignBorderSize() {
        return this.signBorderSize;
    }

    public int getSignColor() {
        return this.signColor;
    }

    public int getSignHeight() {
        return this.signHeight;
    }

    public int getSignRound() {
        return this.signRound;
    }

    public int getSignTextColor() {
        return this.signTextColor;
    }

    public int getSignTextSize() {
        return this.signTextSize;
    }

    public int getSignWidth() {
        return this.signWidth;
    }

    public float getThumbBgAlpha() {
        return this.thumbBgAlpha;
    }

    public int getThumbColor() {
        return this.thumbColor;
    }

    public int getThumbRadius() {
        return this.thumbRadius;
    }

    public int getThumbRadiusOnDragging() {
        return this.thumbRadiusOnDragging;
    }

    public float getThumbRatio() {
        return this.thumbRatio;
    }

    public int getThumbTextColor() {
        return this.thumbTextColor;
    }

    public int getThumbTextSize() {
        return this.thumbTextSize;
    }

    public int getTrackColor() {
        return this.trackColor;
    }

    public int getTrackSize() {
        return this.trackSize;
    }

    public String getUnit() {
        return this.unit;
    }

    public boolean isAutoAdjustSectionMark() {
        return this.autoAdjustSectionMark;
    }

    public boolean isFloatType() {
        return this.floatType;
    }

    public boolean isReverse() {
        return this.reverse;
    }

    public boolean isSeekBySection() {
        return this.seekBySection;
    }

    public boolean isShowProgressInFloat() {
        return this.showProgressInFloat;
    }

    public boolean isShowSectionMark() {
        return this.showSectionMark;
    }

    public boolean isShowSectionText() {
        return this.showSectionText;
    }

    public boolean isShowSignBorder() {
        return this.showSignBorder;
    }

    public boolean isShowThumbShadow() {
        return this.showThumbShadow;
    }

    public boolean isShowThumbText() {
        return this.showThumbText;
    }

    public boolean isSignArrowAutofloat() {
        return this.signArrowAutofloat;
    }

    public boolean isTouchToSeek() {
        return this.touchToSeek;
    }

    public boolean isshowSign() {
        return this.showSign;
    }

    public SignConfigBuilder max(float f) {
        this.max = f;
        return this;
    }

    public SignConfigBuilder min(float f) {
        this.min = f;
        this.progress = f;
        return this;
    }

    public SignConfigBuilder progress(float f) {
        this.progress = f;
        return this;
    }

    public SignConfigBuilder reverse() {
        this.reverse = true;
        return this;
    }

    public SignConfigBuilder secondTrackColor(@ColorInt int i) {
        this.secondTrackColor = i;
        this.thumbColor = i;
        this.thumbTextColor = i;
        this.signColor = i;
        return this;
    }

    public SignConfigBuilder secondTrackSize(int i) {
        this.secondTrackSize = SignUtils.dp2px(i);
        return this;
    }

    public SignConfigBuilder sectionCount(@IntRange(from = 1) int i) {
        this.sectionCount = i;
        return this;
    }

    public SignConfigBuilder sectionTextColor(@ColorInt int i) {
        this.sectionTextColor = i;
        return this;
    }

    public SignConfigBuilder sectionTextInterval(@IntRange(from = 1) int i) {
        this.sectionTextInterval = i;
        return this;
    }

    public SignConfigBuilder sectionTextPosition(int i) {
        this.sectionTextPosition = i;
        return this;
    }

    public SignConfigBuilder sectionTextSize(int i) {
        this.sectionTextSize = SignUtils.sp2px(i);
        return this;
    }

    public SignConfigBuilder seekBySection() {
        this.seekBySection = true;
        return this;
    }

    public SignConfigBuilder setUnit(String str) {
        this.unit = str;
        return this;
    }

    public SignConfigBuilder showProgressInFloat() {
        this.showProgressInFloat = true;
        return this;
    }

    public SignConfigBuilder showSectionMark() {
        this.showSectionMark = true;
        return this;
    }

    public SignConfigBuilder showSectionText() {
        this.showSectionText = true;
        return this;
    }

    public SignConfigBuilder showSign() {
        this.showSign = true;
        return this;
    }

    public SignConfigBuilder showSignBorder(boolean z) {
        this.showSignBorder = z;
        return this;
    }

    public SignConfigBuilder showThumbShadow(boolean z) {
        this.showThumbShadow = z;
        return this;
    }

    public SignConfigBuilder showThumbText() {
        this.showThumbText = true;
        return this;
    }

    public SignConfigBuilder signArrowAutofloat(boolean z) {
        this.signArrowAutofloat = z;
        return this;
    }

    public SignConfigBuilder signArrowHeight(int i) {
        this.signArrowHeight = i;
        return this;
    }

    public SignConfigBuilder signArrowWidth(int i) {
        this.signArrowWidth = i;
        return this;
    }

    public SignConfigBuilder signBorderColor(int i) {
        this.signBorderColor = i;
        return this;
    }

    public SignConfigBuilder signBorderSize(int i) {
        this.signBorderSize = i;
        return this;
    }

    public SignConfigBuilder signColor(@ColorInt int i) {
        this.signColor = i;
        return this;
    }

    public SignConfigBuilder signHeight(int i) {
        this.signHeight = i;
        return this;
    }

    public SignConfigBuilder signRound(int i) {
        this.signRound = i;
        return this;
    }

    public SignConfigBuilder signTextColor(@ColorInt int i) {
        this.signTextColor = i;
        return this;
    }

    public SignConfigBuilder signTextSize(int i) {
        this.signTextSize = SignUtils.sp2px(i);
        return this;
    }

    public SignConfigBuilder signWidth(int i) {
        this.signWidth = i;
        return this;
    }

    public SignConfigBuilder thumbBgAlpha(float f) {
        this.thumbBgAlpha = f;
        return this;
    }

    public SignConfigBuilder thumbColor(@ColorInt int i) {
        this.thumbColor = i;
        return this;
    }

    public SignConfigBuilder thumbRadius(int i) {
        this.thumbRadius = SignUtils.dp2px(i);
        return this;
    }

    public SignConfigBuilder thumbRadiusOnDragging(int i) {
        this.thumbRadiusOnDragging = SignUtils.dp2px(i);
        return this;
    }

    public SignConfigBuilder thumbRatio(float f) {
        this.thumbRatio = f;
        return this;
    }

    public SignConfigBuilder thumbTextColor(@ColorInt int i) {
        this.thumbTextColor = i;
        return this;
    }

    public SignConfigBuilder thumbTextSize(int i) {
        this.thumbTextSize = SignUtils.sp2px(i);
        return this;
    }

    public SignConfigBuilder touchToSeek() {
        this.touchToSeek = true;
        return this;
    }

    public SignConfigBuilder trackColor(@ColorInt int i) {
        this.trackColor = i;
        this.sectionTextColor = i;
        return this;
    }

    public SignConfigBuilder trackSize(int i) {
        this.trackSize = SignUtils.dp2px(i);
        return this;
    }
}
