package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ac;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class m {
    private final ai a;
    private final av b;

    public m() {
        this(new ac.a());
    }

    public m(ak akVar) {
        this.b = new av();
        this.a = akVar.a(this.b);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private Object a(byte b2, byte[] bArr, q qVar, q... qVarArr) throws p {
        Object obj;
        try {
            ad j = j(bArr, qVar, qVarArr);
            if (j != null) {
                if (b2 != 6) {
                    if (b2 != 8) {
                        if (b2 != 100) {
                            switch (b2) {
                                case 2:
                                    if (j.b == 2) {
                                        obj = Boolean.valueOf(this.a.t());
                                        break;
                                    }
                                case 3:
                                    if (j.b == 3) {
                                        obj = Byte.valueOf(this.a.u());
                                        break;
                                    }
                                case 4:
                                    if (j.b == 4) {
                                        obj = Double.valueOf(this.a.y());
                                        break;
                                    }
                                default:
                                    switch (b2) {
                                        case 10:
                                            if (j.b == 10) {
                                                obj = Long.valueOf(this.a.x());
                                                break;
                                            }
                                        case 11:
                                            if (j.b == 11) {
                                                obj = this.a.z();
                                                break;
                                            }
                                            obj = null;
                                            break;
                                    }
                            }
                        } else if (j.b == 11) {
                            obj = this.a.A();
                            this.b.e();
                            this.a.B();
                            return obj;
                        }
                    } else if (j.b == 8) {
                        obj = Integer.valueOf(this.a.w());
                        this.b.e();
                        this.a.B();
                        return obj;
                    }
                } else if (j.b == 6) {
                    obj = Short.valueOf(this.a.v());
                    this.b.e();
                    this.a.B();
                    return obj;
                }
            }
            obj = null;
            this.b.e();
            this.a.B();
            return obj;
        } catch (Exception e) {
            throw new p((Throwable) e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }

    private ad j(byte[] bArr, q qVar, q... qVarArr) throws p {
        int i = 0;
        this.b.a(bArr);
        q[] qVarArr2 = new q[(qVarArr.length + 1)];
        qVarArr2[0] = qVar;
        int i2 = 0;
        while (i2 < qVarArr.length) {
            int i3 = i2 + 1;
            qVarArr2[i3] = qVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        ad adVar = null;
        while (i < qVarArr2.length) {
            adVar = this.a.l();
            if (adVar.b == 0 || adVar.c > qVarArr2[i].a()) {
                return null;
            }
            if (adVar.c != qVarArr2[i].a()) {
                al.a(this.a, adVar.b);
                this.a.m();
            } else {
                i++;
                if (i < qVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return adVar;
    }

    public Boolean a(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Boolean) a((byte) 2, bArr, qVar, qVarArr);
    }

    public void a(j jVar, String str) throws p {
        a(jVar, str.getBytes());
    }

    public void a(j jVar, String str, String str2) throws p {
        try {
            a(jVar, str.getBytes(str2));
            this.a.B();
        } catch (UnsupportedEncodingException e) {
            throw new p("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.a.B();
            throw th;
        }
    }

    public void a(j jVar, byte[] bArr) throws p {
        try {
            this.b.a(bArr);
            jVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(j jVar, byte[] bArr, q qVar, q... qVarArr) throws p {
        try {
            if (j(bArr, qVar, qVarArr) != null) {
                jVar.read(this.a);
            }
            this.b.e();
            this.a.B();
        } catch (Exception e) {
            throw new p((Throwable) e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }

    public Byte b(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Byte) a((byte) 3, bArr, qVar, qVarArr);
    }

    public Double c(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Double) a((byte) 4, bArr, qVar, qVarArr);
    }

    public Short d(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Short) a((byte) 6, bArr, qVar, qVarArr);
    }

    public Integer e(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Integer) a((byte) 8, bArr, qVar, qVarArr);
    }

    public Long f(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Long) a((byte) 10, bArr, qVar, qVarArr);
    }

    public String g(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (String) a((byte) 11, bArr, qVar, qVarArr);
    }

    public ByteBuffer h(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (ByteBuffer) a((byte) 100, bArr, qVar, qVarArr);
    }

    public Short i(byte[] bArr, q qVar, q... qVarArr) throws p {
        Short sh;
        try {
            if (j(bArr, qVar, qVarArr) != null) {
                this.a.j();
                sh = Short.valueOf(this.a.l().c);
            } else {
                sh = null;
            }
            this.b.e();
            this.a.B();
            return sh;
        } catch (Exception e) {
            throw new p((Throwable) e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }
}
