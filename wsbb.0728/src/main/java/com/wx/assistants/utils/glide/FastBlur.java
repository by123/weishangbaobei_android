package com.wx.assistants.utils.glide;

import android.graphics.Bitmap;
import java.lang.reflect.Array;

public class FastBlur {
    public static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        Bitmap copy;
        if (z) {
            copy = bitmap;
        } else {
            copy = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i2 = width * height;
        int[] iArr = new int[i2];
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = i + i + 1;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        int[] iArr5 = new int[Math.max(width, height)];
        int i6 = (i5 + 1) >> 1;
        int i7 = i6 * i6;
        int i8 = i7 * 256;
        int[] iArr6 = new int[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            iArr6[i9] = i9 / i7;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i5, 3});
        int i10 = i + 1;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i12 < height) {
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            for (int i23 = -i; i23 <= i; i23++) {
                int i24 = iArr[Math.min(i3, Math.max(i23, 0)) + i11];
                int[] iArr8 = iArr7[i23 + i];
                iArr8[0] = (16711680 & i24) >> 16;
                iArr8[1] = (65280 & i24) >> 8;
                iArr8[2] = i24 & 255;
                int abs = i10 - Math.abs(i23);
                i14 += iArr8[0] * abs;
                i15 += iArr8[1] * abs;
                i16 += abs * iArr8[2];
                if (i23 > 0) {
                    i17 += iArr8[0];
                    i18 += iArr8[1];
                    i19 += iArr8[2];
                } else {
                    i20 += iArr8[0];
                    i21 += iArr8[1];
                    i22 += iArr8[2];
                }
            }
            int i25 = 0;
            int i26 = i20;
            int i27 = i19;
            int i28 = i;
            int i29 = i21;
            int i30 = i22;
            while (i25 < width) {
                iArr2[i11] = iArr6[i14];
                iArr3[i11] = iArr6[i15];
                iArr4[i11] = iArr6[i16];
                int[] iArr9 = iArr7[((i28 - i) + i5) % i5];
                int i31 = iArr9[0];
                int i32 = iArr9[1];
                int i33 = iArr9[2];
                if (i12 == 0) {
                    iArr5[i25] = Math.min(i25 + i + 1, i3);
                }
                int i34 = iArr[iArr5[i25] + i13];
                iArr9[0] = (16711680 & i34) >> 16;
                iArr9[1] = (65280 & i34) >> 8;
                iArr9[2] = i34 & 255;
                int i35 = i17 + iArr9[0];
                int i36 = i18 + iArr9[1];
                int i37 = i27 + iArr9[2];
                int i38 = (i14 - i26) + i35;
                int i39 = (i15 - i29) + i36;
                int i40 = (i16 - i30) + i37;
                i28 = (i28 + 1) % i5;
                int[] iArr10 = iArr7[i28 % i5];
                int i41 = (i26 - i31) + iArr10[0];
                i29 = (i29 - i32) + iArr10[1];
                i30 = (i30 - i33) + iArr10[2];
                i17 = i35 - iArr10[0];
                i18 = i36 - iArr10[1];
                i27 = i37 - iArr10[2];
                i11++;
                i25++;
                i16 = i40;
                i15 = i39;
                i14 = i38;
                i26 = i41;
            }
            i12++;
            i13 += width;
        }
        for (int i42 = 0; i42 < width; i42++) {
            int i43 = -i;
            int i44 = i43 * width;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            while (i43 <= i) {
                int max = Math.max(0, i44) + i42;
                int[] iArr11 = iArr7[i43 + i];
                iArr11[0] = iArr2[max];
                iArr11[1] = iArr3[max];
                iArr11[2] = iArr4[max];
                int abs2 = i10 - Math.abs(i43);
                i45 += iArr2[max] * abs2;
                i46 += iArr3[max] * abs2;
                i47 += iArr4[max] * abs2;
                if (i43 > 0) {
                    i48 += iArr11[0];
                    i49 += iArr11[1];
                    i50 += iArr11[2];
                } else {
                    i51 += iArr11[0];
                    i52 += iArr11[1];
                    i53 += iArr11[2];
                }
                if (i43 < i4) {
                    i44 += width;
                }
                i43++;
            }
            int i54 = 0;
            int i55 = i45;
            int i56 = i42;
            int i57 = i;
            int i58 = i53;
            int i59 = i52;
            int i60 = i51;
            while (i54 < height) {
                iArr[i56] = (iArr[i56] & -16777216) | (iArr6[i55] << 16) | (iArr6[i46] << 8) | iArr6[i47];
                int[] iArr12 = iArr7[((i57 - i) + i5) % i5];
                int i61 = iArr12[0];
                int i62 = iArr12[1];
                int i63 = iArr12[2];
                if (i42 == 0) {
                    iArr5[i54] = Math.min(i54 + i10, i4) * width;
                }
                int i64 = iArr5[i54] + i42;
                iArr12[0] = iArr2[i64];
                iArr12[1] = iArr3[i64];
                iArr12[2] = iArr4[i64];
                int i65 = i48 + iArr12[0];
                int i66 = i49 + iArr12[1];
                int i67 = i50 + iArr12[2];
                int i68 = (i55 - i60) + i65;
                int i69 = (i46 - i59) + i66;
                i47 = (i47 - i58) + i67;
                i57 = (i57 + 1) % i5;
                int[] iArr13 = iArr7[i57];
                i60 = (i60 - i61) + iArr13[0];
                i59 = (i59 - i62) + iArr13[1];
                i58 = (i58 - i63) + iArr13[2];
                i48 = i65 - iArr13[0];
                i49 = i66 - iArr13[1];
                i50 = i67 - iArr13[2];
                i55 = i68;
                i56 += width;
                i54++;
                i46 = i69;
            }
        }
        copy.setPixels(iArr, 0, width, 0, 0, width, height);
        return copy;
    }
}
