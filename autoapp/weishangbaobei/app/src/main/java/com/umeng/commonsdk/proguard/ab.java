package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol */
public class ab extends ai {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final an h = new an();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    public void a() {
    }

    public void a(an anVar) {
    }

    public void b() {
    }

    public void c() {
    }

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }

    public void i() {
    }

    public void k() {
    }

    public void m() {
    }

    public void o() {
    }

    public void q() {
    }

    public void s() {
    }

    /* compiled from: TBinaryProtocol */
    public static class a implements ak {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        public ai a(aw awVar) {
            ab abVar = new ab(awVar, this.a, this.b);
            if (this.c != 0) {
                abVar.c(this.c);
            }
            return abVar;
        }
    }

    public ab(aw awVar) {
        this(awVar, false, true);
    }

    public ab(aw awVar, boolean z, boolean z2) {
        super(awVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    public void a(ag agVar) throws p {
        if (this.d) {
            a((int) b | agVar.b);
            a(agVar.a);
            a(agVar.c);
            return;
        }
        a(agVar.a);
        a(agVar.b);
        a(agVar.c);
    }

    public void a(ad adVar) throws p {
        a(adVar.b);
        a(adVar.c);
    }

    public void d() throws p {
        a((byte) 0);
    }

    public void a(af afVar) throws p {
        a(afVar.a);
        a(afVar.b);
        a(afVar.c);
    }

    public void a(ae aeVar) throws p {
        a(aeVar.a);
        a(aeVar.b);
    }

    public void a(am amVar) throws p {
        a(amVar.a);
        a(amVar.b);
    }

    public void a(boolean z) throws p {
        a(z ? (byte) 1 : 0);
    }

    public void a(byte b2) throws p {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    public void a(short s) throws p {
        this.j[0] = (byte) ((s >> 8) & 255);
        this.j[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    public void a(int i2) throws p {
        this.k[0] = (byte) ((i2 >> 24) & 255);
        this.k[1] = (byte) ((i2 >> 16) & 255);
        this.k[2] = (byte) ((i2 >> 8) & 255);
        this.k[3] = (byte) (i2 & 255);
        this.g.b(this.k, 0, 4);
    }

    public void a(long j2) throws p {
        this.l[0] = (byte) ((int) ((j2 >> 56) & 255));
        this.l[1] = (byte) ((int) ((j2 >> 48) & 255));
        this.l[2] = (byte) ((int) ((j2 >> 40) & 255));
        this.l[3] = (byte) ((int) ((j2 >> 32) & 255));
        this.l[4] = (byte) ((int) ((j2 >> 24) & 255));
        this.l[5] = (byte) ((int) ((j2 >> 16) & 255));
        this.l[6] = (byte) ((int) ((j2 >> 8) & 255));
        this.l[7] = (byte) ((int) (j2 & 255));
        this.g.b(this.l, 0, 8);
    }

    public void a(double d2) throws p {
        a(Double.doubleToLongBits(d2));
    }

    public void a(String str) throws p {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) throws p {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public ag h() throws p {
        int w = w();
        if (w < 0) {
            if ((a & w) == b) {
                return new ag(z(), (byte) (w & 255), w());
            }
            throw new aj(4, "Bad version in readMessageBegin");
        } else if (!this.c) {
            return new ag(b(w), u(), w());
        } else {
            throw new aj(4, "Missing version in readMessageBegin, old client?");
        }
    }

    public an j() {
        return h;
    }

    public ad l() throws p {
        short s;
        byte u = u();
        if (u == 0) {
            s = 0;
        } else {
            s = v();
        }
        return new ad("", u, s);
    }

    public af n() throws p {
        return new af(u(), u(), w());
    }

    public ae p() throws p {
        return new ae(u(), w());
    }

    public am r() throws p {
        return new am(u(), w());
    }

    public boolean t() throws p {
        return u() == 1;
    }

    public byte u() throws p {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    public short v() throws p {
        byte[] bArr = this.n;
        int i2 = 0;
        if (this.g.h() >= 2) {
            bArr = this.g.f();
            i2 = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8));
    }

    public int w() throws p {
        byte[] bArr = this.o;
        int i2 = 0;
        if (this.g.h() >= 4) {
            bArr = this.g.f();
            i2 = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << ap.n) | ((bArr[i2 + 2] & 255) << 8);
    }

    public long x() throws p {
        byte[] bArr = this.p;
        int i2 = 0;
        if (this.g.h() >= 8) {
            bArr = this.g.f();
            i2 = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return ((long) (bArr[i2 + 7] & 255)) | (((long) (bArr[i2] & 255)) << 56) | (((long) (bArr[i2 + 1] & 255)) << 48) | (((long) (bArr[i2 + 2] & 255)) << 40) | (((long) (bArr[i2 + 3] & 255)) << 32) | (((long) (bArr[i2 + 4] & 255)) << 24) | (((long) (bArr[i2 + 5] & 255)) << 16) | (((long) (bArr[i2 + 6] & 255)) << 8);
    }

    public double y() throws p {
        return Double.longBitsToDouble(x());
    }

    public String z() throws p {
        int w = w();
        if (this.g.h() < w) {
            return b(w);
        }
        try {
            String str = new String(this.g.f(), this.g.g(), w, "UTF-8");
            this.g.a(w);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i2) throws p {
        try {
            d(i2);
            byte[] bArr = new byte[i2];
            this.g.d(bArr, 0, i2);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer A() throws p {
        int w = w();
        d(w);
        if (this.g.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i2, int i3) throws p {
        d(i3);
        return this.g.d(bArr, i2, i3);
    }

    public void c(int i2) {
        this.e = i2;
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public void d(int i2) throws p {
        if (i2 < 0) {
            throw new aj("Negative length: " + i2);
        } else if (this.f) {
            this.e -= i2;
            if (this.e < 0) {
                throw new aj("Message length exceeded: " + i2);
            }
        }
    }
}
