package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ac;
import java.nio.ByteBuffer;

/* compiled from: TDeserializer */
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

    public void a(j jVar, byte[] bArr) throws p {
        try {
            this.b.a(bArr);
            jVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:5|6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        throw new com.umeng.commonsdk.proguard.p("JVM DOES NOT SUPPORT ENCODING: " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r1.a.B();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        throw r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.umeng.commonsdk.proguard.j r2, java.lang.String r3, java.lang.String r4) throws com.umeng.commonsdk.proguard.p {
        /*
            r1 = this;
            byte[] r3 = r3.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x000f }
            r1.a((com.umeng.commonsdk.proguard.j) r2, (byte[]) r3)     // Catch:{ UnsupportedEncodingException -> 0x000f }
            com.umeng.commonsdk.proguard.ai r2 = r1.a
            r2.B()
            return
        L_0x000d:
            r2 = move-exception
            goto L_0x0026
        L_0x000f:
            com.umeng.commonsdk.proguard.p r2 = new com.umeng.commonsdk.proguard.p     // Catch:{ all -> 0x000d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x000d }
            r3.<init>()     // Catch:{ all -> 0x000d }
            java.lang.String r0 = "JVM DOES NOT SUPPORT ENCODING: "
            r3.append(r0)     // Catch:{ all -> 0x000d }
            r3.append(r4)     // Catch:{ all -> 0x000d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x000d }
            r2.<init>((java.lang.String) r3)     // Catch:{ all -> 0x000d }
            throw r2     // Catch:{ all -> 0x000d }
        L_0x0026:
            com.umeng.commonsdk.proguard.ai r3 = r1.a
            r3.B()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.proguard.m.a(com.umeng.commonsdk.proguard.j, java.lang.String, java.lang.String):void");
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

    public Boolean a(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Boolean) a((byte) 2, bArr, qVar, qVarArr);
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

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(byte r1, byte[] r2, com.umeng.commonsdk.proguard.q r3, com.umeng.commonsdk.proguard.q... r4) throws com.umeng.commonsdk.proguard.p {
        /*
            r0 = this;
            com.umeng.commonsdk.proguard.ad r2 = r0.j(r2, r3, r4)     // Catch:{ Exception -> 0x009e }
            if (r2 == 0) goto L_0x009a
            r3 = 6
            if (r1 == r3) goto L_0x008b
            r3 = 8
            if (r1 == r3) goto L_0x007c
            r3 = 100
            r4 = 11
            if (r1 == r3) goto L_0x0071
            switch(r1) {
                case 2: goto L_0x0061;
                case 3: goto L_0x0051;
                case 4: goto L_0x0041;
                default: goto L_0x0016;
            }     // Catch:{ Exception -> 0x009e }
        L_0x0016:
            switch(r1) {
                case 10: goto L_0x0030;
                case 11: goto L_0x001b;
                default: goto L_0x0019;
            }     // Catch:{ Exception -> 0x009e }
        L_0x0019:
            goto L_0x009a
        L_0x001b:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            if (r1 != r4) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            java.lang.String r1 = r1.z()     // Catch:{ Exception -> 0x009e }
        L_0x0025:
            com.umeng.commonsdk.proguard.av r2 = r0.b
            r2.e()
            com.umeng.commonsdk.proguard.ai r2 = r0.a
            r2.B()
            return r1
        L_0x0030:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            r2 = 10
            if (r1 != r2) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            long r1 = r1.x()     // Catch:{ Exception -> 0x009e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x0041:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            r2 = 4
            if (r1 != r2) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            double r1 = r1.y()     // Catch:{ Exception -> 0x009e }
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x0051:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            r2 = 3
            if (r1 != r2) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            byte r1 = r1.u()     // Catch:{ Exception -> 0x009e }
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x0061:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            r2 = 2
            if (r1 != r2) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            boolean r1 = r1.t()     // Catch:{ Exception -> 0x009e }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x0071:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            if (r1 != r4) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            java.nio.ByteBuffer r1 = r1.A()     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x007c:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            if (r1 != r3) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            int r1 = r1.w()     // Catch:{ Exception -> 0x009e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x008b:
            byte r1 = r2.b     // Catch:{ Exception -> 0x009e }
            if (r1 != r3) goto L_0x009a
            com.umeng.commonsdk.proguard.ai r1 = r0.a     // Catch:{ Exception -> 0x009e }
            short r1 = r1.v()     // Catch:{ Exception -> 0x009e }
            java.lang.Short r1 = java.lang.Short.valueOf(r1)     // Catch:{ Exception -> 0x009e }
            goto L_0x0025
        L_0x009a:
            r1 = 0
            goto L_0x0025
        L_0x009c:
            r1 = move-exception
            goto L_0x00a5
        L_0x009e:
            r1 = move-exception
            com.umeng.commonsdk.proguard.p r2 = new com.umeng.commonsdk.proguard.p     // Catch:{ all -> 0x009c }
            r2.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x009c }
            throw r2     // Catch:{ all -> 0x009c }
        L_0x00a5:
            com.umeng.commonsdk.proguard.av r2 = r0.b
            r2.e()
            com.umeng.commonsdk.proguard.ai r2 = r0.a
            r2.B()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.proguard.m.a(byte, byte[], com.umeng.commonsdk.proguard.q, com.umeng.commonsdk.proguard.q[]):java.lang.Object");
    }

    private ad j(byte[] bArr, q qVar, q... qVarArr) throws p {
        this.b.a(bArr);
        q[] qVarArr2 = new q[(qVarArr.length + 1)];
        int i = 0;
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

    public void a(j jVar, String str) throws p {
        a(jVar, str.getBytes());
    }
}
