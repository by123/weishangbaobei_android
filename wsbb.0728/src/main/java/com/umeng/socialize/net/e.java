package com.umeng.socialize.net;

import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.sina.helper.MD5;

public abstract class e extends URequest {
    public e(String str) {
        super(str);
    }

    public String a() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public String a(String str, String str2, String str3, String str4) {
        String lowerCase = MD5.hexdigest("" + str + str3 + str4 + "edafw2436ef8a3t4" + str2).toLowerCase();
        String substring = lowerCase.substring(lowerCase.length() + -6);
        String substring2 = substring.substring(0, 4);
        StringBuilder sb = new StringBuilder();
        sb.append(substring);
        sb.append(substring2);
        String lowerCase2 = MD5.hexdigest(sb.toString().toString()).toLowerCase();
        String substring3 = lowerCase2.substring(lowerCase2.length() - 1);
        return substring + substring3;
    }
}
