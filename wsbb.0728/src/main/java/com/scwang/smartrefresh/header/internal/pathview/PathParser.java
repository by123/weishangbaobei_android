package com.scwang.smartrefresh.header.internal.pathview;

import android.graphics.Matrix;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.NonNull;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

class PathParser {
    private static final String TAG = "PathParser";

    private static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        private ExtractFloatResult() {
        }
    }

    public static class PathDataNode {
        float[] params;
        char type;

        PathDataNode(char c, float[] fArr) {
            this.type = c;
            this.params = fArr;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d9, code lost:
            r15 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x01b9, code lost:
            r4 = r12;
            r2 = r13;
            r1 = r8;
            r3 = r7;
            r6 = r7;
            r5 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x0254, code lost:
            r4 = r12;
            r2 = r13;
            r1 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
            r4 = r12;
            r2 = r13;
            r6 = r14;
            r5 = r15;
         */
        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int i;
            int i2;
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            float f14;
            int i3 = fArr[0];
            float f15 = fArr[1];
            float f16 = fArr[2];
            float f17 = fArr[3];
            int i4 = fArr[4];
            float f18 = fArr[5];
            switch (c2) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(i4, f18);
                    f17 = f18;
                    f16 = i4;
                    f15 = f18;
                    i3 = i4;
                    break;
            }
            i = 2;
            int i5 = 0;
            while (true) {
                int i6 = i4;
                float f19 = f18;
                float f20 = i2;
                float f21 = f;
                int i7 = i5;
                if (i7 < fArr2.length) {
                    switch (c2) {
                        case 'A':
                            int i8 = i7 + 5;
                            int i9 = i7 + 6;
                            drawArc(path, f20, f21, fArr2[i8], fArr2[i9], fArr2[i7 + 0], fArr2[i7 + 1], fArr2[i7 + 2], fArr2[i7 + 3] != CropImageView.DEFAULT_ASPECT_RATIO, fArr2[i7 + 4] != CropImageView.DEFAULT_ASPECT_RATIO);
                            f4 = fArr2[i8];
                            f5 = fArr2[i9];
                            break;
                        case 'C':
                            int i10 = i7 + 2;
                            int i11 = i7 + 3;
                            int i12 = i7 + 4;
                            int i13 = i7 + 5;
                            path.cubicTo(fArr2[i7 + 0], fArr2[i7 + 1], fArr2[i10], fArr2[i11], fArr2[i12], fArr2[i13]);
                            int i14 = fArr2[i12];
                            float f22 = fArr2[i13];
                            f2 = fArr2[i10];
                            f3 = fArr2[i11];
                            i4 = i6;
                            f18 = f19;
                            i2 = i14;
                            f = f22;
                            break;
                        case 'H':
                            int i15 = i7 + 0;
                            path.lineTo(fArr2[i15], f21);
                            float f23 = fArr2[i15];
                            i4 = i6;
                            f18 = f19;
                            i2 = f23;
                            f = f21;
                            break;
                        case 'L':
                            int i16 = i7 + 0;
                            int i17 = i7 + 1;
                            path.lineTo(fArr2[i16], fArr2[i17]);
                            float f24 = fArr2[i16];
                            i4 = i6;
                            f18 = f19;
                            i2 = f24;
                            f = fArr2[i17];
                            break;
                        case 'M':
                            int i18 = i7 + 0;
                            float f25 = fArr2[i18];
                            int i19 = i7 + 1;
                            float f26 = fArr2[i19];
                            if (i7 <= 0) {
                                path.moveTo(fArr2[i18], fArr2[i19]);
                                i4 = f25;
                                f18 = f26;
                                i2 = f25;
                                f = f26;
                                break;
                            } else {
                                path.lineTo(fArr2[i18], fArr2[i19]);
                                i4 = i6;
                                f18 = f19;
                                i2 = f25;
                                f = f26;
                                break;
                            }
                        case 'Q':
                            int i20 = i7 + 0;
                            int i21 = i7 + 1;
                            int i22 = i7 + 2;
                            int i23 = i7 + 3;
                            path.quadTo(fArr2[i20], fArr2[i21], fArr2[i22], fArr2[i23]);
                            float f27 = fArr2[i20];
                            float f28 = fArr2[i21];
                            int i24 = fArr2[i22];
                            f = fArr2[i23];
                            f6 = f27;
                            f7 = f28;
                            i2 = i24;
                            break;
                        case 'S':
                            if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                f9 = (2.0f * f20) - f2;
                                f8 = (2.0f * f21) - f3;
                            } else {
                                f8 = f21;
                                f9 = f20;
                            }
                            int i25 = i7 + 0;
                            int i26 = i7 + 1;
                            int i27 = i7 + 2;
                            int i28 = i7 + 3;
                            path.cubicTo(f9, f8, fArr2[i25], fArr2[i26], fArr2[i27], fArr2[i28]);
                            float f29 = fArr2[i25];
                            float f30 = fArr2[i26];
                            int i29 = fArr2[i27];
                            f = fArr2[i28];
                            f6 = f29;
                            f7 = f30;
                            i2 = i29;
                            break;
                        case 'T':
                            if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                f21 = (2.0f * f21) - f3;
                                f20 = (2.0f * f20) - f2;
                            }
                            int i30 = i7 + 0;
                            int i31 = i7 + 1;
                            path.quadTo(f20, f21, fArr2[i30], fArr2[i31]);
                            float f31 = fArr2[i30];
                            float f32 = fArr2[i31];
                            i4 = i6;
                            f18 = f19;
                            f3 = f21;
                            f2 = f20;
                            i2 = f31;
                            f = f32;
                            break;
                        case 'V':
                            int i32 = i7 + 0;
                            path.lineTo(f20, fArr2[i32]);
                            float f33 = fArr2[i32];
                            i4 = i6;
                            f18 = f19;
                            i2 = f20;
                            f = f33;
                            break;
                        case 'a':
                            int i33 = i7 + 5;
                            int i34 = i7 + 6;
                            drawArc(path, f20, f21, fArr2[i33] + f20, fArr2[i34] + f21, fArr2[i7 + 0], fArr2[i7 + 1], fArr2[i7 + 2], fArr2[i7 + 3] != CropImageView.DEFAULT_ASPECT_RATIO, fArr2[i7 + 4] != CropImageView.DEFAULT_ASPECT_RATIO);
                            f4 = fArr2[i33] + f20;
                            f5 = fArr2[i34] + f21;
                            break;
                        case 'c':
                            int i35 = i7 + 2;
                            int i36 = i7 + 3;
                            int i37 = i7 + 4;
                            int i38 = i7 + 5;
                            path.rCubicTo(fArr2[i7 + 0], fArr2[i7 + 1], fArr2[i35], fArr2[i36], fArr2[i37], fArr2[i38]);
                            f2 = fArr2[i35] + f20;
                            f3 = fArr2[i36] + f21;
                            f20 += fArr2[i37];
                            f10 = f21 + fArr2[i38];
                            break;
                        case 'h':
                            int i39 = i7 + 0;
                            path.rLineTo(fArr2[i39], CropImageView.DEFAULT_ASPECT_RATIO);
                            f20 += fArr2[i39];
                            break;
                        case 'l':
                            int i40 = i7 + 0;
                            int i41 = i7 + 1;
                            path.rLineTo(fArr2[i40], fArr2[i41]);
                            f20 += fArr2[i40];
                            f21 += fArr2[i41];
                            break;
                        case 'm':
                            int i42 = i7 + 0;
                            float f34 = fArr2[i42] + f20;
                            int i43 = i7 + 1;
                            float f35 = fArr2[i43] + f21;
                            if (i7 <= 0) {
                                path.rMoveTo(fArr2[i42], fArr2[i43]);
                                i6 = f34;
                                f19 = f35;
                                f20 = f34;
                                f21 = f35;
                                break;
                            } else {
                                path.rLineTo(fArr2[i42], fArr2[i43]);
                                f20 = f34;
                                f21 = f35;
                                break;
                            }
                        case 'q':
                            int i44 = i7 + 0;
                            int i45 = i7 + 1;
                            int i46 = i7 + 2;
                            int i47 = i7 + 3;
                            path.rQuadTo(fArr2[i44], fArr2[i45], fArr2[i46], fArr2[i47]);
                            f2 = fArr2[i44] + f20;
                            f3 = fArr2[i45] + f21;
                            f20 += fArr2[i46];
                            f10 = f21 + fArr2[i47];
                            break;
                        case 's':
                            if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                f12 = f20 - f2;
                                f11 = f21 - f3;
                            } else {
                                f12 = CropImageView.DEFAULT_ASPECT_RATIO;
                                f11 = CropImageView.DEFAULT_ASPECT_RATIO;
                            }
                            int i48 = i7 + 0;
                            int i49 = i7 + 1;
                            int i50 = i7 + 2;
                            int i51 = i7 + 3;
                            path.rCubicTo(f12, f11, fArr2[i48], fArr2[i49], fArr2[i50], fArr2[i51]);
                            f2 = fArr2[i48] + f20;
                            f3 = fArr2[i49] + f21;
                            f20 += fArr2[i50];
                            f10 = f21 + fArr2[i51];
                            break;
                        case 't':
                            if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                f14 = f21 - f3;
                                f13 = f20 - f2;
                            } else {
                                f14 = CropImageView.DEFAULT_ASPECT_RATIO;
                                f13 = 0.0f;
                            }
                            int i52 = i7 + 0;
                            int i53 = i7 + 1;
                            path.rQuadTo(f13, f14, fArr2[i52], fArr2[i53]);
                            f3 = f14 + f21;
                            f2 = f13 + f20;
                            f20 = fArr2[i52] + f20;
                            f21 = fArr2[i53] + f21;
                            break;
                        case 'v':
                            int i54 = i7 + 0;
                            path.rLineTo(CropImageView.DEFAULT_ASPECT_RATIO, fArr2[i54]);
                            f21 += fArr2[i54];
                            break;
                    }
                } else {
                    fArr[0] = f20;
                    fArr[1] = f21;
                    fArr[2] = f2;
                    fArr[3] = f3;
                    fArr[4] = i6;
                    fArr[5] = f19;
                    return;
                }
                i5 = i7 + i;
                c = c2;
            }
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            int ceil = (int) Math.ceil(Math.abs((4.0d * d9) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d10 = -d3;
            double d11 = d10 * cos;
            double d12 = d4 * sin;
            double d13 = d10 * sin;
            double d14 = d4 * cos;
            double d15 = (double) ceil;
            Double.isNaN(d15);
            double d16 = d9 / d15;
            double d17 = (sin2 * d13) + (cos2 * d14);
            double d18 = (sin2 * d11) - (cos2 * d12);
            int i = 0;
            while (i < ceil) {
                double d19 = d8 + d16;
                double sin3 = Math.sin(d19);
                double cos3 = Math.cos(d19);
                double d20 = (((d3 * cos) * cos3) + d) - (d12 * sin3);
                double d21 = (d3 * sin * cos3) + d2 + (d14 * sin3);
                double d22 = (d11 * sin3) - (d12 * cos3);
                double d23 = (cos3 * d14) + (sin3 * d13);
                double d24 = d19 - d8;
                double tan = Math.tan(d24 / 2.0d);
                double sin4 = (Math.sin(d24) * (Math.sqrt((tan * (3.0d * tan)) + 4.0d) - 1.0d)) / 3.0d;
                path.rLineTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                path.cubicTo((float) ((d18 * sin4) + d5), (float) ((d17 * sin4) + d6), (float) (d20 - (sin4 * d22)), (float) (d21 - (sin4 * d23)), (float) d20, (float) d21);
                i++;
                d8 = d19;
                d17 = d23;
                d6 = d21;
                d18 = d22;
                d5 = d20;
            }
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians((double) f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = (double) f;
            Double.isNaN(d3);
            double d4 = (double) f2;
            Double.isNaN(d4);
            double d5 = (double) f5;
            Double.isNaN(d5);
            double d6 = ((d3 * cos) + (d4 * sin)) / d5;
            double d7 = (double) (-f);
            Double.isNaN(d7);
            Double.isNaN(d4);
            double d8 = (double) f6;
            Double.isNaN(d8);
            double d9 = ((d7 * sin) + (d4 * cos)) / d8;
            double d10 = (double) f3;
            Double.isNaN(d10);
            double d11 = (double) f4;
            Double.isNaN(d11);
            Double.isNaN(d5);
            double d12 = ((d10 * cos) + (d11 * sin)) / d5;
            double d13 = (double) (-f3);
            Double.isNaN(d13);
            Double.isNaN(d11);
            Double.isNaN(d8);
            double d14 = ((d13 * sin) + (d11 * cos)) / d8;
            double d15 = d6 - d12;
            double d16 = d9 - d14;
            double d17 = (d6 + d12) / 2.0d;
            double d18 = (d9 + d14) / 2.0d;
            double d19 = (d15 * d15) + (d16 * d16);
            if (d19 != 0.0d) {
                double d20 = (1.0d / d19) - 0.25d;
                if (d20 < 0.0d) {
                    float sqrt = (float) (Math.sqrt(d19) / 1.99999d);
                    drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                    return;
                }
                double sqrt2 = Math.sqrt(d20);
                double d21 = d15 * sqrt2;
                double d22 = sqrt2 * d16;
                if (z == z2) {
                    d = d17 - d22;
                    d2 = d18 + d21;
                } else {
                    d = d22 + d17;
                    d2 = d18 - d21;
                }
                double atan2 = Math.atan2(d9 - d2, d6 - d);
                double atan22 = Math.atan2(d14 - d2, d12 - d) - atan2;
                if (z2 != (atan22 >= 0.0d)) {
                    atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
                }
                Double.isNaN(d5);
                double d23 = d * d5;
                Double.isNaN(d8);
                double d24 = d2 * d8;
                arcToBezier(path, (d23 * cos) - (d24 * sin), (d24 * cos) + (d23 * sin), d5, d8, d3, d4, radians, atan2, atan22);
            }
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (PathDataNode pathDataNode : pathDataNodeArr) {
                addCommand(path, fArr, c, pathDataNode.type, pathDataNode.params);
                c = pathDataNode.type;
            }
        }
    }

    PathParser() {
    }

    private static void addNode(List<PathDataNode> list, char c, float[] fArr) {
        list.add(new PathDataNode(c, fArr));
    }

    static float[] copyOfRange(@NonNull float[] fArr, int i, int i2) {
        int i3 = i2 - i;
        int min = Math.min(i3, fArr.length - i);
        float[] fArr2 = new float[i3];
        System.arraycopy(fArr, i, fArr2, 0, min);
        return fArr2;
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i = nextStart + 1;
            i2 = nextStart;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r4 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a A[LOOP:0: B:1:0x0008->B:22:0x003a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0024 A[SYNTHETIC] */
    private static void extract(String str, int i, ExtractFloatResult extractFloatResult) {
        extractFloatResult.mEndWithNegOrDot = false;
        boolean z = false;
        boolean z2 = false;
        int i2 = i;
        boolean z3 = false;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt != ' ') {
                if (charAt == 'E' || charAt == 'e') {
                    z3 = true;
                    if (!z2) {
                        extractFloatResult.mEndPosition = i2;
                    }
                    i2++;
                } else {
                    switch (charAt) {
                        case ',':
                            break;
                        case '-':
                            if (i2 != i && !z3) {
                                extractFloatResult.mEndWithNegOrDot = true;
                            }
                        case '.':
                            if (!z) {
                                z = true;
                                z3 = false;
                                break;
                            } else {
                                extractFloatResult.mEndWithNegOrDot = true;
                            }
                    }
                }
            }
            z2 = true;
            z3 = false;
            if (!z2) {
            }
        }
        extractFloatResult.mEndPosition = i2;
    }

    private static float[] getFloats(String str) {
        int i = 1;
        if ((str.charAt(0) == 'z') || (str.charAt(0) == 'Z')) {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i2 = 0;
            while (i < length) {
                extract(str, i, extractFloatResult);
                int i3 = extractFloatResult.mEndPosition;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                break;
            }
            i++;
        }
        return i;
    }

    public static List<Path> transformScale(float f, float f2, List<Path> list, List<String> list2) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT > 16) {
            for (Path transform : list) {
                Path path = new Path();
                transform.transform(matrix, path);
                arrayList.add(path);
            }
        } else {
            for (String createNodesFromPathData : list2) {
                Path path2 = new Path();
                PathDataNode[] createNodesFromPathData2 = createNodesFromPathData(createNodesFromPathData);
                transformScaleNodes(f, f2, createNodesFromPathData2);
                PathDataNode.nodesToPath(createNodesFromPathData2, path2);
                arrayList.add(path2);
            }
        }
        return arrayList;
    }

    private static void transformScaleCommand(float f, float f2, char c, float[] fArr) {
        int i = 2;
        switch (c) {
            case 'A':
            case 'a':
                i = 7;
                break;
            case 'C':
            case 'c':
                i = 6;
                break;
            case 'H':
            case 'V':
            case 'h':
            case 'v':
                i = 1;
                break;
            case 'Q':
            case 'S':
            case 'q':
            case 's':
                i = 4;
                break;
        }
        for (int i2 = 0; i2 < fArr.length; i2 += i) {
            switch (c) {
                case 'A':
                case 'a':
                    break;
                case 'C':
                case 'c':
                    fArr[i2] = fArr[i2] * f;
                    int i3 = i2 + 1;
                    fArr[i3] = fArr[i3] * f2;
                    int i4 = i2 + 2;
                    fArr[i4] = fArr[i4] * f;
                    int i5 = i2 + 3;
                    fArr[i5] = fArr[i5] * f2;
                    int i6 = i2 + 4;
                    fArr[i6] = fArr[i6] * f;
                    int i7 = i2 + 5;
                    fArr[i7] = fArr[i7] * f2;
                    continue;
                case 'H':
                case 'h':
                    fArr[i2] = fArr[i2] * f;
                    continue;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                    fArr[i2] = fArr[i2] * f;
                    int i8 = i2 + 1;
                    fArr[i8] = fArr[i8] * f2;
                    continue;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    fArr[i2] = fArr[i2] * f;
                    int i9 = i2 + 1;
                    fArr[i9] = fArr[i9] * f2;
                    int i10 = i2 + 2;
                    fArr[i10] = fArr[i10] * f;
                    int i11 = i2 + 3;
                    fArr[i11] = fArr[i11] * f2;
                    break;
                case 'V':
                case 'v':
                    fArr[i2] = fArr[i2] * f2;
                    continue;
            }
            fArr[i2] = fArr[i2] * f;
            int i12 = i2 + 1;
            fArr[i12] = fArr[i12] * f2;
            int i13 = i2 + 5;
            fArr[i13] = fArr[i13] * f;
            int i14 = i2 + 6;
            fArr[i14] = fArr[i14] * f2;
        }
    }

    private static void transformScaleNodes(float f, float f2, PathDataNode[] pathDataNodeArr) {
        for (PathDataNode pathDataNode : pathDataNodeArr) {
            transformScaleCommand(f, f2, pathDataNode.type, pathDataNode.params);
        }
    }
}
