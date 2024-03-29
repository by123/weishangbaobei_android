package com.googlecode.mp4parser.h264.model;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.h264.write.CAVLCWriter;
import java.io.IOException;

public class ScalingList {
    public int[] scalingList;
    public boolean useDefaultScalingMatrixFlag;

    public void write(CAVLCWriter cAVLCWriter) throws IOException {
        if (this.useDefaultScalingMatrixFlag) {
            cAVLCWriter.writeSE(0, "SPS: ");
            return;
        }
        int i = 8;
        for (int i2 = 0; i2 < this.scalingList.length; i2++) {
            cAVLCWriter.writeSE((this.scalingList[i2] - i) - 256, "SPS: ");
            i = this.scalingList[i2];
        }
    }

    public static ScalingList read(CAVLCReader cAVLCReader, int i) throws IOException {
        ScalingList scalingList2 = new ScalingList();
        scalingList2.scalingList = new int[i];
        int i2 = 0;
        int i3 = 8;
        int i4 = 8;
        while (i2 < i) {
            if (i3 != 0) {
                i3 = ((cAVLCReader.readSE("deltaScale") + i4) + 256) % 256;
                scalingList2.useDefaultScalingMatrixFlag = i2 == 0 && i3 == 0;
            }
            int[] iArr = scalingList2.scalingList;
            if (i3 != 0) {
                i4 = i3;
            }
            iArr[i2] = i4;
            i4 = scalingList2.scalingList[i2];
            i2++;
        }
        return scalingList2;
    }

    public String toString() {
        return "ScalingList{scalingList=" + this.scalingList + ", useDefaultScalingMatrixFlag=" + this.useDefaultScalingMatrixFlag + '}';
    }
}
