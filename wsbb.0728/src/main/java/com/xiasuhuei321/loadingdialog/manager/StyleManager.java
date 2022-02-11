package com.xiasuhuei321.loadingdialog.manager;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

public class StyleManager {
    private int contentSize = -1;
    private String failedText = "加载失败";
    private boolean interceptBack = true;
    private String loadText = "加载中...";
    private boolean openAnim = true;
    private int repeatTime;
    private long showTime = -1;
    private LoadingDialog.Speed speed = LoadingDialog.Speed.SPEED_TWO;
    private String successText = "加载成功";
    private int textSize = -1;

    public StyleManager() {
    }

    public StyleManager(boolean z, int i, LoadingDialog.Speed speed2, int i2, int i3, long j, boolean z2, String str, String str2, String str3) {
        this.openAnim = z;
        this.repeatTime = i;
        this.speed = speed2;
        this.contentSize = i2;
        this.textSize = i3;
        this.showTime = j;
        this.interceptBack = z2;
        this.loadText = str;
        this.successText = str2;
        this.failedText = str3;
    }

    public StyleManager(boolean z, int i, LoadingDialog.Speed speed2, int i2, int i3, long j, boolean z2, String str, String str2, String str3, int i4) {
        this.openAnim = z;
        this.repeatTime = i;
        this.speed = speed2;
        this.contentSize = i2;
        this.textSize = i3;
        this.showTime = j;
        this.interceptBack = z2;
        this.loadText = str;
        this.successText = str2;
        this.failedText = str3;
    }

    public static StyleManager getDefault() {
        return new StyleManager(true, 0, LoadingDialog.Speed.SPEED_TWO, -1, -1, 1000, true, "加载中...", "加载成功", "加载失败");
    }

    public StyleManager Anim(boolean z) {
        this.openAnim = z;
        return this;
    }

    public StyleManager contentSize(int i) {
        this.contentSize = i;
        return this;
    }

    public StyleManager failedText(String str) {
        this.failedText = str;
        return this;
    }

    public int getContentSize() {
        return this.contentSize;
    }

    public String getFailedText() {
        return this.failedText;
    }

    public String getLoadText() {
        return this.loadText;
    }

    public int getRepeatTime() {
        return this.repeatTime;
    }

    public long getShowTime() {
        return this.showTime;
    }

    public LoadingDialog.Speed getSpeed() {
        return this.speed;
    }

    public String getSuccessText() {
        return this.successText;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public StyleManager intercept(boolean z) {
        this.interceptBack = z;
        return this;
    }

    public boolean isInterceptBack() {
        return this.interceptBack;
    }

    public boolean isOpenAnim() {
        return this.openAnim;
    }

    public StyleManager loadText(String str) {
        this.loadText = str;
        return this;
    }

    public StyleManager repeatTime(int i) {
        this.repeatTime = i;
        return this;
    }

    public StyleManager setLoadStyle(int i) {
        return this;
    }

    public StyleManager showTime(long j) {
        this.showTime = j;
        return this;
    }

    public StyleManager speed(LoadingDialog.Speed speed2) {
        this.speed = speed2;
        return this;
    }

    public StyleManager successText(String str) {
        this.successText = str;
        return this;
    }

    public StyleManager textSize(int i) {
        this.textSize = i;
        return this;
    }
}
