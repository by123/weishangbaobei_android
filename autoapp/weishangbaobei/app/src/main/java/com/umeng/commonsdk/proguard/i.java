package com.umeng.commonsdk.proguard;

/* compiled from: TApplicationException */
public class i extends p {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final an j = new an("TApplicationException");
    private static final ad k = new ad("message", (byte) 11, 1);
    private static final ad l = new ad("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i = 0;

    public i() {
    }

    public i(int i2) {
        this.i = i2;
    }

    public i(int i2, String str) {
        super(str);
        this.i = i2;
    }

    public i(String str) {
        super(str);
    }

    public int a() {
        return this.i;
    }

    public static i a(ai aiVar) throws p {
        aiVar.j();
        String str = null;
        int i2 = 0;
        while (true) {
            ad l2 = aiVar.l();
            if (l2.b == 0) {
                aiVar.k();
                return new i(i2, str);
            }
            switch (l2.c) {
                case 1:
                    if (l2.b != 11) {
                        al.a(aiVar, l2.b);
                        break;
                    } else {
                        str = aiVar.z();
                        break;
                    }
                case 2:
                    if (l2.b != 8) {
                        al.a(aiVar, l2.b);
                        break;
                    } else {
                        i2 = aiVar.w();
                        break;
                    }
                default:
                    al.a(aiVar, l2.b);
                    break;
            }
            aiVar.m();
        }
    }

    public void b(ai aiVar) throws p {
        aiVar.a(j);
        if (getMessage() != null) {
            aiVar.a(k);
            aiVar.a(getMessage());
            aiVar.c();
        }
        aiVar.a(l);
        aiVar.a(this.i);
        aiVar.c();
        aiVar.d();
        aiVar.b();
    }
}
