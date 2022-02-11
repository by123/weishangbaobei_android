package com.tencent.bugly.proguard;

import com.umeng.commonsdk.proguard.ap;
import com.yalantis.ucrop.view.CropImageView;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class i {
    private ByteBuffer a;
    private String b = "GBK";

    public static final class a {
        public byte a;
        public int b;
    }

    public i() {
    }

    public i(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    public i(byte[] bArr, int i) {
        this.a = ByteBuffer.wrap(bArr);
        this.a.position(4);
    }

    private double a(double d, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 12) {
                return 0.0d;
            }
            switch (b2) {
                case 4:
                    return (double) this.a.getFloat();
                case 5:
                    return this.a.getDouble();
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new g("require field not exist.");
        }
    }

    private float a(float f, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 4) {
                return this.a.getFloat();
            }
            if (b2 == 12) {
                return CropImageView.DEFAULT_ASPECT_RATIO;
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return f;
        } else {
            throw new g("require field not exist.");
        }
    }

    private static int a(a aVar, ByteBuffer byteBuffer) {
        byte b2 = byteBuffer.get();
        aVar.a = (byte) (b2 & ap.m);
        aVar.b = (b2 & 240) >> 4;
        if (aVar.b != 15) {
            return 1;
        }
        aVar.b = byteBuffer.get();
        return 2;
    }

    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry next = map2.entrySet().iterator().next();
        Object key = next.getKey();
        Object value = next.getValue();
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 8) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    for (int i2 = 0; i2 < a2; i2++) {
                        map.put(a(key, 0, true), a(value, 1, true));
                    }
                    return map;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return map;
        } else {
            throw new g("require field not exist.");
        }
    }

    private void a() {
        a aVar = new a();
        do {
            a(aVar, this.a);
            a(aVar.a);
        } while (aVar.a != 11);
    }

    private void a(byte b2) {
        int i = 0;
        switch (b2) {
            case 0:
                this.a.position(this.a.position() + 1);
                return;
            case 1:
                this.a.position(this.a.position() + 2);
                return;
            case 2:
                this.a.position(this.a.position() + 4);
                return;
            case 3:
                this.a.position(this.a.position() + 8);
                return;
            case 4:
                this.a.position(this.a.position() + 4);
                return;
            case 5:
                this.a.position(this.a.position() + 8);
                return;
            case 6:
                int i2 = this.a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                this.a.position(i2 + this.a.position());
                return;
            case 7:
                this.a.position(this.a.getInt() + this.a.position());
                return;
            case 8:
                int a2 = a(0, 0, true);
                while (i < (a2 << 1)) {
                    a aVar = new a();
                    a(aVar, this.a);
                    a(aVar.a);
                    i++;
                }
                return;
            case 9:
                int a3 = a(0, 0, true);
                while (i < a3) {
                    a aVar2 = new a();
                    a(aVar2, this.a);
                    a(aVar2.a);
                    i++;
                }
                return;
            case 10:
                a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar3 = new a();
                a(aVar3, this.a);
                if (aVar3.a == 0) {
                    this.a.position(a(0, 0, true) + this.a.position());
                    return;
                }
                throw new g("skipField with invalid type, type value: " + b2 + ", " + aVar3.a);
            default:
                throw new g("invalid type.");
        }
    }

    private boolean a(int i) {
        try {
            a aVar = new a();
            while (true) {
                int a2 = a(aVar, this.a.duplicate());
                if (i > aVar.b && aVar.a != 11) {
                    this.a.position(a2 + this.a.position());
                    a(aVar.a);
                }
            }
            return i == aVar.b;
        } catch (g | BufferUnderflowException e) {
            return false;
        }
    }

    private <T> T[] a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return b(tArr[0], i, z);
        }
        throw new g("unable to get type of key and value.");
    }

    private <T> T[] b(T t, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    T[] tArr = (Object[]) Array.newInstance(t.getClass(), a2);
                    for (int i2 = 0; i2 < a2; i2++) {
                        tArr[i2] = a(t, 0, true);
                    }
                    return tArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private boolean[] d(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    boolean[] zArr = new boolean[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        zArr[i2] = a((byte) 0, 0, true) != 0;
                    }
                    return zArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private short[] e(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    short[] sArr = new short[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        sArr[i2] = a(sArr[0], 0, true);
                    }
                    return sArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private int[] f(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    int[] iArr = new int[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        iArr[i2] = a(iArr[0], 0, true);
                    }
                    return iArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private long[] g(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    long[] jArr = new long[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        jArr[i2] = a(jArr[0], 0, true);
                    }
                    return jArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private float[] h(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    float[] fArr = new float[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        fArr[i2] = a(fArr[0], 0, true);
                    }
                    return fArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private double[] i(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            if (aVar.a == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    double[] dArr = new double[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        dArr[i2] = a(dArr[0], 0, true);
                    }
                    return dArr;
                }
                throw new g("size invalid: " + a2);
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final byte a(byte b2, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b3 = aVar.a;
            if (b3 == 0) {
                return this.a.get();
            }
            if (b3 == 12) {
                return 0;
            }
            throw new g("type mismatch.");
        } else if (!z) {
            return b2;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final int a(int i, int i2, boolean z) {
        if (a(i2)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 12) {
                return 0;
            }
            switch (b2) {
                case 0:
                    return this.a.get();
                case 1:
                    return this.a.getShort();
                case 2:
                    return this.a.getInt();
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final int a(String str) {
        this.b = str;
        return 0;
    }

    public final long a(long j, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 12) {
                return 0;
            }
            switch (b2) {
                case 0:
                    return (long) this.a.get();
                case 1:
                    return (long) this.a.getShort();
                case 2:
                    return (long) this.a.getInt();
                case 3:
                    return this.a.getLong();
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final k a(k kVar, int i, boolean z) {
        if (a(i)) {
            try {
                k kVar2 = (k) kVar.getClass().newInstance();
                a aVar = new a();
                a(aVar, this.a);
                if (aVar.a == 10) {
                    kVar2.a(this);
                    a();
                    return kVar2;
                }
                throw new g("type mismatch.");
            } catch (Exception e) {
                throw new g(e.getMessage());
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v23, types: [int] */
    /* JADX WARNING: type inference failed for: r0v47 */
    /* JADX WARNING: type inference failed for: r0v50 */
    /* JADX WARNING: Multi-variable type inference failed */
    public final <T> Object a(T t, int i, boolean z) {
        ? r0 = 0;
        if (t instanceof Byte) {
            return Byte.valueOf(a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            if (a((byte) 0, i, z) != 0) {
                r0 = 1;
            }
            return Boolean.valueOf(r0);
        } else if (t instanceof Short) {
            return Short.valueOf(a(0, i, z));
        } else {
            if (t instanceof Integer) {
                return Integer.valueOf(a(0, i, z));
            }
            if (t instanceof Long) {
                return Long.valueOf(a(0, i, z));
            }
            if (t instanceof Float) {
                return Float.valueOf(a((float) CropImageView.DEFAULT_ASPECT_RATIO, i, z));
            }
            if (t instanceof Double) {
                return Double.valueOf(a(0.0d, i, z));
            }
            if (t instanceof String) {
                return String.valueOf(b(i, z));
            }
            if (t instanceof Map) {
                return (HashMap) a(new HashMap(), (Map) t, i, z);
            } else if (t instanceof List) {
                List list = (List) t;
                if (list == null || list.isEmpty()) {
                    return new ArrayList();
                }
                Object[] b2 = b(list.get(0), i, z);
                if (b2 == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                while (r0 < b2.length) {
                    arrayList.add(b2[r0]);
                    r0++;
                }
                return arrayList;
            } else if (t instanceof k) {
                return a((k) t, i, z);
            } else {
                if (t.getClass().isArray()) {
                    return ((t instanceof byte[]) || (t instanceof Byte[])) ? c(i, z) : t instanceof boolean[] ? d(i, z) : t instanceof short[] ? e(i, z) : t instanceof int[] ? f(i, z) : t instanceof long[] ? g(i, z) : t instanceof float[] ? h(i, z) : t instanceof double[] ? i(i, z) : a((T[]) (Object[]) t, i, z);
                }
                throw new g("read object error: unsupport type.");
            }
        }
    }

    public final <K, V> HashMap<K, V> a(Map<K, V> map, int i, boolean z) {
        return (HashMap) a(new HashMap(), map, i, z);
    }

    public final short a(short s, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 12) {
                return 0;
            }
            switch (b2) {
                case 0:
                    return (short) this.a.get();
                case 1:
                    return this.a.getShort();
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final void a(byte[] bArr) {
        if (this.a != null) {
            this.a.clear();
        }
        this.a = ByteBuffer.wrap(bArr);
    }

    public final boolean a(int i, boolean z) {
        return a((byte) 0, i, z) != 0;
    }

    public final String b(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case 6:
                    int i2 = this.a.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    byte[] bArr = new byte[i2];
                    this.a.get(bArr);
                    try {
                        return new String(bArr, this.b);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case 7:
                    int i3 = this.a.getInt();
                    if (i3 > 104857600 || i3 < 0) {
                        throw new g("String too long: " + i3);
                    }
                    byte[] bArr2 = new byte[i3];
                    this.a.get(bArr2);
                    try {
                        return new String(bArr2, this.b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr2);
                    }
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final byte[] c(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte b2 = aVar.a;
            if (b2 == 9) {
                int a2 = a(0, 0, true);
                if (a2 >= 0) {
                    byte[] bArr = new byte[a2];
                    for (int i2 = 0; i2 < a2; i2++) {
                        bArr[i2] = a(bArr[0], 0, true);
                    }
                    return bArr;
                }
                throw new g("size invalid: " + a2);
            } else if (b2 == 13) {
                a aVar2 = new a();
                a(aVar2, this.a);
                if (aVar2.a == 0) {
                    int a3 = a(0, 0, true);
                    if (a3 >= 0) {
                        byte[] bArr2 = new byte[a3];
                        this.a.get(bArr2);
                        return bArr2;
                    }
                    throw new g("invalid size, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a + ", size: " + a3);
                }
                throw new g("type mismatch, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a);
            } else {
                throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }
}
