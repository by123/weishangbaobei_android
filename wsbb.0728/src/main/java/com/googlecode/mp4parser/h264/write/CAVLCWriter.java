package com.googlecode.mp4parser.h264.write;

import com.googlecode.mp4parser.h264.Debug;
import java.io.IOException;
import java.io.OutputStream;

public class CAVLCWriter extends BitstreamWriter {
    public CAVLCWriter(OutputStream outputStream) {
        super(outputStream);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void writeBool(boolean z, String str) throws IOException {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public void writeNBit(long j, int i, String str) throws IOException {
        Debug.print(String.valueOf(str) + "\t");
        for (int i2 = 0; i2 < i; i2++) {
            write1Bit(((int) (j >> ((i - i2) - 1))) & 1);
        }
        Debug.println("\t" + j);
    }

    public void writeSE(int i, String str) throws IOException {
        int i2 = 1;
        Debug.print(String.valueOf(str) + "\t");
        int i3 = i < 0 ? -1 : 1;
        if (i <= 0) {
            i2 = 0;
        }
        writeUE(i2 + (i3 * (i << 1)));
        Debug.println("\t" + i);
    }

    public void writeSliceTrailingBits() {
        throw new IllegalStateException("todo");
    }

    public void writeTrailingBits() throws IOException {
        write1Bit(1);
        writeRemainingZero();
        flush();
    }

    public void writeU(int i, int i2) throws IOException {
        writeNBit((long) i, i2);
    }

    public void writeU(int i, int i2, String str) throws IOException {
        Debug.print(String.valueOf(str) + "\t");
        writeNBit((long) i, i2);
        Debug.println("\t" + i);
    }

    public void writeUE(int i) throws IOException {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= 15) {
                break;
            }
            int i5 = (1 << i4) + i3;
            if (i < i5) {
                i2 = i4;
                break;
            } else {
                i4++;
                i3 = i5;
            }
        }
        writeNBit(0, i2);
        write1Bit(1);
        writeNBit((long) (i - i3), i2);
    }

    public void writeUE(int i, String str) throws IOException {
        Debug.print(String.valueOf(str) + "\t");
        writeUE(i);
        Debug.println("\t" + i);
    }
}
