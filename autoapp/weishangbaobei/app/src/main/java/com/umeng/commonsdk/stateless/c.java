package com.umeng.commonsdk.stateless;

import android.content.Context;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;

/* compiled from: UMSLEnvelopeManager */
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
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("[stateless] build envelope, raw is  ");
            sb.append(bArr == null);
            sb.append("m app key is ");
            sb.append(str);
            sb.append("device id is ");
            sb.append(deviceId);
            sb.append(", mac is ");
            sb.append(mac);
            objArr[0] = sb.toString();
            ULog.i("walle", objArr);
            c cVar = new c(bArr, str, (deviceId + mac).getBytes());
            cVar.a();
            return cVar;
        } catch (Exception e2) {
            ULog.i("walle", "[stateless] build envelope, e is " + e2.getMessage());
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
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

    public void a(boolean z) {
        this.n = z;
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
            } catch (Exception unused) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = d();
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
        byte[] bArr3 = {(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) (i2 >>> 24)};
        for (int i6 = 0; i6 < bArr2.length; i6++) {
            bArr2[i6] = (byte) (bArr2[i6] ^ bArr3[i6 % 4]);
        }
        return bArr2;
    }

    private byte[] c() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] d() {
        return f.b((f.c(this.f) + this.i + this.j + this.k + f.c(this.g)).getBytes());
    }

    public byte[] b() {
        b bVar = new b();
        bVar.a(this.d);
        bVar.b(this.e);
        bVar.c(f.c(this.f));
        bVar.a(this.i);
        bVar.b(this.j);
        bVar.c(this.k);
        bVar.a(this.l);
        bVar.d(this.n ? 1 : 0);
        bVar.d(f.c(this.g));
        bVar.e(f.c(this.h));
        try {
            return new s().a(bVar);
        } catch (Exception unused) {
            return null;
        }
    }
}
