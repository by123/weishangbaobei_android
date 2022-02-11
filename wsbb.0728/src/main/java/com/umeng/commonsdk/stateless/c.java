package com.umeng.commonsdk.stateless;

import android.content.Context;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;

public class c {
    private final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0};
    private final int b = 1;
    private final int c = 0;
    private String d = "1.0";
    private String e = null;
    private byte[] f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private byte[] l = null;
    private byte[] m = null;
    private boolean n = false;

    private c(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = f.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static c a(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            StringBuilder sb = new StringBuilder();
            sb.append("[stateless] build envelope, raw is  ");
            sb.append(bArr == null);
            sb.append("m app key is ");
            sb.append(str);
            sb.append("device id is ");
            sb.append(deviceId);
            sb.append(", mac is ");
            sb.append(mac);
            ULog.i("walle", sb.toString());
            c cVar = new c(bArr, str, (deviceId + mac).getBytes());
            cVar.a();
            return cVar;
        } catch (Exception e2) {
            ULog.i("walle", "[stateless] build envelope, e is " + e2.getMessage());
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    private byte[] a(byte[] bArr, int i2) {
        byte[] b2 = f.b(this.m);
        byte[] b3 = f.b(this.l);
        int length = b2.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 2;
            bArr2[i4] = b3[i3];
            bArr2[i4 + 1] = b2[i3];
        }
        for (int i5 = 0; i5 < 2; i5++) {
            bArr2[i5] = bArr[i5];
            bArr2[(bArr2.length - i5) - 1] = bArr[(bArr.length - i5) - 1];
        }
        byte b4 = (byte) (i2 & 255);
        byte b5 = (byte) ((i2 >> 8) & 255);
        byte b6 = (byte) ((i2 >> 16) & 255);
        byte b7 = (byte) (i2 >>> 24);
        for (int i6 = 0; i6 < bArr2.length; i6++) {
            bArr2[i6] = (byte) (bArr2[i6] ^ new byte[]{b4, b5, b6, b7}[i6 % 4]);
        }
        return bArr2;
    }

    public static c b(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            c cVar = new c(bArr, str, (deviceId + mac).getBytes());
            cVar.a(true);
            cVar.a();
            return cVar;
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    private byte[] c() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] d() {
        return f.b((f.c(this.f) + this.i + this.j + this.k + f.c(this.g)).getBytes());
    }

    public void a() {
        if (this.f == null) {
            this.f = c();
        }
        if (this.n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f, 1, bArr, 0, 16);
                this.l = f.a(this.l, bArr);
            } catch (Exception e2) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = d();
    }

    public void a(boolean z) {
        this.n = z;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public byte[] b() {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
