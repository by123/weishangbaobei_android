package com.umeng.commonsdk.proguard;

import com.umeng.socialize.ShareContent;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ac extends ai {
    private static final an d = new an("");
    private static final ad e = new ad("", (byte) 0, 0);
    private static final byte[] f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    byte[] a;
    byte[] b;
    byte[] c;
    private h m;
    private short n;
    private ad o;
    private Boolean p;
    private final long q;
    private byte[] r;

    public static class a implements ak {
        private final long a;

        public a() {
            this.a = -1;
        }

        public a(int i) {
            this.a = (long) i;
        }

        public ai a(aw awVar) {
            return new ac(awVar, this.a);
        }
    }

    private static class b {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b() {
        }
    }

    static {
        f[0] = 0;
        f[2] = 1;
        f[3] = 3;
        f[6] = 4;
        f[8] = 5;
        f[10] = 6;
        f[4] = 7;
        f[11] = 8;
        f[15] = 9;
        f[14] = 10;
        f[13] = 11;
        f[12] = 12;
    }

    public ac(aw awVar) {
        this(awVar, -1);
    }

    public ac(aw awVar, long j2) {
        super(awVar);
        this.m = new h(15);
        this.n = 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j2;
    }

    private int E() throws p {
        if (this.g.h() >= 5) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = f2[g + i3];
                i2 |= (b2 & Byte.MAX_VALUE) << i4;
                if ((b2 & 128) != 128) {
                    this.g.a(i3 + 1);
                    return i2;
                }
                i4 += 7;
                i3++;
            }
        } else {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                byte u = u();
                int i7 = ((u & Byte.MAX_VALUE) << i6) | i5;
                if ((u & 128) != 128) {
                    return i7;
                }
                i6 += 7;
                i5 = i7;
            }
        }
    }

    private long F() throws p {
        int i2;
        int i3 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            int i4 = 0;
            while (true) {
                i2 = i3;
                byte b2 = f2[g + i2];
                j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
                if ((b2 & 128) != 128) {
                    break;
                }
                i4 += 7;
                i3 = i2 + 1;
            }
            this.g.a(i2 + 1);
        } else {
            while (true) {
                byte u = u();
                j2 |= ((long) (u & Byte.MAX_VALUE)) << i3;
                if ((u & 128) != 128) {
                    break;
                }
                i3 += 7;
            }
        }
        return j2;
    }

    private long a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (((long) bArr[0]) & 255);
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) ((int) (j2 & 255));
        bArr[i2 + 1] = (byte) ((int) ((j2 >> 8) & 255));
        bArr[i2 + 2] = (byte) ((int) ((j2 >> 16) & 255));
        bArr[i2 + 3] = (byte) ((int) ((j2 >> 24) & 255));
        bArr[i2 + 4] = (byte) ((int) ((j2 >> 32) & 255));
        bArr[i2 + 5] = (byte) ((int) ((j2 >> 40) & 255));
        bArr[i2 + 6] = (byte) ((int) ((j2 >> 48) & 255));
        bArr[i2 + 7] = (byte) ((int) ((j2 >> 56) & 255));
    }

    private void a(ad adVar, byte b2) throws p {
        if (b2 == -1) {
            b2 = e(adVar.b);
        }
        if (adVar.c <= this.n || adVar.c - this.n > 15) {
            b(b2);
            a(adVar.c);
        } else {
            d((int) ((adVar.c - this.n) << 4) | b2);
        }
        this.n = adVar.c;
    }

    private void a(byte[] bArr, int i2, int i3) throws p {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    private void b(byte b2) throws p {
        this.r[0] = b2;
        this.g.b(this.r);
    }

    private void b(int i2) throws p {
        int i3 = 0;
        while ((i2 & -128) != 0) {
            this.a[i3] = (byte) ((i2 & 127) | ShareContent.MINAPP_STYLE);
            i2 >>>= 7;
            i3++;
        }
        this.a[i3] = (byte) i2;
        this.g.b(this.a, 0, i3 + 1);
    }

    private void b(long j2) throws p {
        int i2 = 0;
        while ((-128 & j2) != 0) {
            this.b[i2] = (byte) ((int) ((127 & j2) | 128));
            j2 >>>= 7;
            i2++;
        }
        this.b[i2] = (byte) ((int) j2);
        this.g.b(this.b, 0, i2 + 1);
    }

    private int c(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    private long c(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private boolean c(byte b2) {
        byte b3 = b2 & ap.m;
        return b3 == 1 || b3 == 2;
    }

    private byte d(byte b2) throws aj {
        byte b3 = (byte) (b2 & ap.m);
        switch (b3) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10;
            case 7:
                return 4;
            case 8:
                return 11;
            case 9:
                return ap.m;
            case 10:
                return ap.l;
            case 11:
                return ap.k;
            case 12:
                return 12;
            default:
                throw new aj("don't know what type: " + b3);
        }
    }

    private long d(long j2) {
        return (-(1 & j2)) ^ (j2 >>> 1);
    }

    private void d(int i2) throws p {
        b((byte) i2);
    }

    private byte e(byte b2) {
        return f[b2];
    }

    private byte[] e(int i2) throws p {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws aj {
        if (i2 < 0) {
            throw new aj("Negative length: " + i2);
        } else if (this.q != -1 && ((long) i2) > this.q) {
            throw new aj("Length exceeded max allowed: " + i2);
        }
    }

    private int g(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    public ByteBuffer A() throws p {
        int E = E();
        f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.g.d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    public void B() {
        this.m.c();
        this.n = 0;
    }

    public void a() throws p {
    }

    public void a(byte b2) throws p {
        b(b2);
    }

    /* access modifiers changed from: protected */
    public void a(byte b2, int i2) throws p {
        if (i2 <= 14) {
            d((int) e(b2) | (i2 << 4));
            return;
        }
        d((int) e(b2) | 240);
        b(i2);
    }

    public void a(double d2) throws p {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    public void a(int i2) throws p {
        b(c(i2));
    }

    public void a(long j2) throws p {
        b(c(j2));
    }

    public void a(ad adVar) throws p {
        if (adVar.b == 2) {
            this.o = adVar;
        } else {
            a(adVar, (byte) -1);
        }
    }

    public void a(ae aeVar) throws p {
        a(aeVar.a, aeVar.b);
    }

    public void a(af afVar) throws p {
        if (afVar.c == 0) {
            d(0);
            return;
        }
        b(afVar.c);
        d((int) (e(afVar.a) << 4) | e(afVar.b));
    }

    public void a(ag agVar) throws p {
        b((byte) h);
        d(((agVar.b << 5) & -32) | 1);
        b(agVar.c);
        a(agVar.a);
    }

    public void a(am amVar) throws p {
        a(amVar.a, amVar.b);
    }

    public void a(an anVar) throws p {
        this.m.a(this.n);
        this.n = 0;
    }

    public void a(String str) throws p {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e2) {
            throw new p("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer byteBuffer) throws p {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    public void a(short s) throws p {
        b(c((int) s));
    }

    public void a(boolean z) throws p {
        byte b2 = 1;
        if (this.o != null) {
            ad adVar = this.o;
            if (!z) {
                b2 = 2;
            }
            a(adVar, b2);
            this.o = null;
            return;
        }
        if (!z) {
            b2 = 2;
        }
        b(b2);
    }

    public void b() throws p {
        this.n = this.m.a();
    }

    public void c() throws p {
    }

    public void d() throws p {
        b((byte) 0);
    }

    public void e() throws p {
    }

    public void f() throws p {
    }

    public void g() throws p {
    }

    public ag h() throws p {
        byte u = u();
        if (u == -126) {
            byte u2 = u();
            byte b2 = (byte) (u2 & j);
            if (b2 == 1) {
                int E = E();
                return new ag(z(), (byte) ((u2 >> 5) & 3), E);
            }
            throw new aj("Expected version 1 but got " + b2);
        }
        throw new aj("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
    }

    public void i() throws p {
    }

    public an j() throws p {
        this.m.a(this.n);
        this.n = 0;
        return d;
    }

    public void k() throws p {
        this.n = this.m.a();
    }

    public ad l() throws p {
        byte u = u();
        if (u == 0) {
            return e;
        }
        short s = (short) ((u & 240) >> 4);
        short v = s == 0 ? v() : (short) (s + this.n);
        byte b2 = (byte) (u & ap.m);
        ad adVar = new ad("", d(b2), v);
        if (c(u)) {
            this.p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = adVar.c;
        return adVar;
    }

    public void m() throws p {
    }

    public af n() throws p {
        int E = E();
        byte u = E == 0 ? 0 : u();
        return new af(d((byte) (u >> 4)), d((byte) (u & ap.m)), E);
    }

    public void o() throws p {
    }

    public ae p() throws p {
        byte u = u();
        int i2 = (u >> 4) & 15;
        if (i2 == 15) {
            i2 = E();
        }
        return new ae(d(u), i2);
    }

    public void q() throws p {
    }

    public am r() throws p {
        return new am(p());
    }

    public void s() throws p {
    }

    public boolean t() throws p {
        if (this.p == null) {
            return u() == 1;
        }
        boolean booleanValue = this.p.booleanValue();
        this.p = null;
        return booleanValue;
    }

    public byte u() throws p {
        if (this.g.h() > 0) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        this.g.d(this.c, 0, 1);
        return this.c[0];
    }

    public short v() throws p {
        return (short) g(E());
    }

    public int w() throws p {
        return g(E());
    }

    public long x() throws p {
        return d(F());
    }

    public double y() throws p {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    public String z() throws p {
        int E = E();
        f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.g.h() < E) {
                return new String(e(E), "UTF-8");
            }
            String str = new String(this.g.f(), this.g.g(), E, "UTF-8");
            this.g.a(E);
            return str;
        } catch (UnsupportedEncodingException e2) {
            throw new p("UTF-8 not supported!");
        }
    }
}
