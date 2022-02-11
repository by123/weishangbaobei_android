package com.umeng.analytics.filter;

import android.util.Base64;
import com.umeng.commonsdk.proguard.ap;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class d {
    private static final String b = "Ă";
    private final String a = "MD5";
    private MessageDigest c;
    private Set<Object> d = new HashSet();
    private boolean e = false;

    public d(boolean z, String str) {
        this.e = z;
        try {
            this.c = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        if (str == null) {
            return;
        }
        if (z) {
            try {
                byte[] decode = Base64.decode(str.getBytes(), 0);
                for (int i = 0; i < decode.length / 4; i++) {
                    int i2 = i * 4;
                    this.d.add(Integer.valueOf((decode[i2 + 3] & 255) + ((decode[i2 + 0] & 255) << 24) + ((decode[i2 + 1] & 255) << ap.n) + ((decode[i2 + 2] & 255) << 8)));
                }
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        } else {
            for (String add : str.split(b)) {
                this.d.add(add);
            }
        }
    }

    private Integer c(String str) {
        try {
            this.c.update(str.getBytes());
            byte[] digest = this.c.digest();
            byte b2 = digest[0];
            byte b3 = digest[1];
            byte b4 = digest[2];
            return Integer.valueOf((digest[3] & 255) + ((b2 & 255) << 24) + ((b3 & 255) << ap.n) + ((b4 & 255) << 8));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a() {
        StringBuilder sb = new StringBuilder();
        for (Object append : this.d) {
            sb.append(append);
            if (sb.length() > 0) {
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
        }
        System.out.println(sb.toString());
    }

    public boolean a(String str) {
        return this.e ? this.d.contains(c(str)) : this.d.contains(str);
    }

    public void b(String str) {
        if (this.e) {
            this.d.add(c(str));
        } else {
            this.d.add(str);
        }
    }

    public String toString() {
        if (this.e) {
            byte[] bArr = new byte[(this.d.size() * 4)];
            Iterator<Object> it = this.d.iterator();
            int i = 0;
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                int i2 = i + 1;
                bArr[i] = (byte) ((-16777216 & intValue) >> 24);
                int i3 = i2 + 1;
                bArr[i2] = (byte) ((16711680 & intValue) >> 16);
                int i4 = i3 + 1;
                bArr[i3] = (byte) ((65280 & intValue) >> 8);
                bArr[i4] = (byte) (intValue & 255);
                i = i4 + 1;
            }
            return new String(Base64.encode(bArr, 0));
        }
        StringBuilder sb = new StringBuilder();
        for (Object next : this.d) {
            if (sb.length() > 0) {
                sb.append(b);
            }
            sb.append(next.toString());
        }
        return sb.toString();
    }
}
