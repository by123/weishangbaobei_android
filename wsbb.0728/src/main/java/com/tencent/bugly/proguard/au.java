package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class au extends k implements Cloneable {
    private static ArrayList<at> f;
    private static Map<String, String> g;
    public byte a = 0;
    public String b = "";
    public String c = "";
    public ArrayList<at> d = null;
    public Map<String, String> e = null;

    public final void a(i iVar) {
        this.a = iVar.a(this.a, 0, true);
        this.b = iVar.b(1, false);
        this.c = iVar.b(2, false);
        if (f == null) {
            f = new ArrayList<>();
            f.add(new at());
        }
        this.d = (ArrayList) iVar.a(f, 3, false);
        if (g == null) {
            g = new HashMap();
            g.put("", "");
        }
        this.e = (Map) iVar.a(g, 4, false);
    }

    public final void a(j jVar) {
        jVar.a(this.a, 0);
        if (this.b != null) {
            jVar.a(this.b, 1);
        }
        if (this.c != null) {
            jVar.a(this.c, 2);
        }
        if (this.d != null) {
            jVar.a(this.d, 3);
        }
        if (this.e != null) {
            jVar.a(this.e, 4);
        }
    }

    public final void a(StringBuilder sb, int i) {
    }
}
