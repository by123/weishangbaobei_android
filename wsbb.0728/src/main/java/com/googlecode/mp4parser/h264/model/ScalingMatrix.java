package com.googlecode.mp4parser.h264.model;

import java.util.Arrays;
import java.util.List;

public class ScalingMatrix {
    public ScalingList[] ScalingList4x4;
    public ScalingList[] ScalingList8x8;

    public String toString() {
        List list = null;
        StringBuilder sb = new StringBuilder("ScalingMatrix{ScalingList4x4=");
        sb.append(this.ScalingList4x4 == null ? null : Arrays.asList(this.ScalingList4x4));
        sb.append("\n");
        sb.append(", ScalingList8x8=");
        if (this.ScalingList8x8 != null) {
            list = Arrays.asList(this.ScalingList8x8);
        }
        sb.append(list);
        sb.append("\n");
        sb.append('}');
        return sb.toString();
    }
}
