package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ac;

public class al {
    private static int a = Integer.MAX_VALUE;

    public static ak a(byte[] bArr, ak akVar) {
        return bArr[0] > 16 ? new ac.a() : (bArr.length <= 1 || (bArr[1] & 128) == 0) ? akVar : new ac.a();
    }

    public static void a(int i) {
        a = i;
    }

    public static void a(ai aiVar, byte b) throws p {
        a(aiVar, b, a);
    }

    public static void a(ai aiVar, byte b, int i) throws p {
        int i2 = 0;
        if (i > 0) {
            switch (b) {
                case 2:
                    aiVar.t();
                    return;
                case 3:
                    aiVar.u();
                    return;
                case 4:
                    aiVar.y();
                    return;
                case 6:
                    aiVar.v();
                    return;
                case 8:
                    aiVar.w();
                    return;
                case 10:
                    aiVar.x();
                    return;
                case 11:
                    aiVar.A();
                    return;
                case 12:
                    aiVar.j();
                    while (true) {
                        ad l = aiVar.l();
                        if (l.b == 0) {
                            aiVar.k();
                            return;
                        } else {
                            a(aiVar, l.b, i - 1);
                            aiVar.m();
                        }
                    }
                case 13:
                    af n = aiVar.n();
                    while (i2 < n.c) {
                        int i3 = i - 1;
                        a(aiVar, n.a, i3);
                        a(aiVar, n.b, i3);
                        i2++;
                    }
                    aiVar.o();
                    return;
                case 14:
                    am r = aiVar.r();
                    while (i2 < r.b) {
                        a(aiVar, r.a, i - 1);
                        i2++;
                    }
                    aiVar.s();
                    return;
                case 15:
                    ae p = aiVar.p();
                    while (i2 < p.b) {
                        a(aiVar, p.a, i - 1);
                        i2++;
                    }
                    aiVar.q();
                    return;
                default:
                    return;
            }
        } else {
            throw new p("Maximum skip depth exceeded");
        }
    }
}
