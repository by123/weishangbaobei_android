package com.googlecode.mp4parser.h264.model;

public class AspectRatio {
    public static final AspectRatio Extended_SAR = new AspectRatio(255);
    private int value;

    private AspectRatio(int i) {
        this.value = i;
    }

    public static AspectRatio fromValue(int i) {
        if (i == Extended_SAR.value) {
            return Extended_SAR;
        }
        return new AspectRatio(i);
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "AspectRatio{" + "value=" + this.value + '}';
    }
}
