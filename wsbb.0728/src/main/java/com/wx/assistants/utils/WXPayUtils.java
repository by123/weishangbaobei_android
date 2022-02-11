package com.wx.assistants.utils;

import android.content.Context;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.proguard.ap;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class WXPayUtils {
    /* access modifiers changed from: private */
    public WXPayBuilder builder;
    /* access modifiers changed from: private */
    public IWXAPI iwxapi;

    public static class WXPayBuilder {
        public String appId;
        public String nonceStr;
        public String packageValue;
        public String partnerId;
        public String prepayId;
        public String sign;
        public String timeStamp;

        public WXPayUtils build() {
            return new WXPayUtils(this);
        }

        public String getAppId() {
            return this.appId;
        }

        public String getNonceStr() {
            return this.nonceStr;
        }

        public String getPackageValue() {
            return this.packageValue;
        }

        public String getPartnerId() {
            return this.partnerId;
        }

        public String getPrepayId() {
            return this.prepayId;
        }

        public String getSign() {
            return this.sign;
        }

        public String getTimeStamp() {
            return this.timeStamp;
        }

        public WXPayBuilder setAppId(String str) {
            this.appId = str;
            return this;
        }

        public WXPayBuilder setNonceStr(String str) {
            this.nonceStr = str;
            return this;
        }

        public WXPayBuilder setPackageValue(String str) {
            this.packageValue = str;
            return this;
        }

        public WXPayBuilder setPartnerId(String str) {
            this.partnerId = str;
            return this;
        }

        public WXPayBuilder setPrepayId(String str) {
            this.prepayId = str;
            return this;
        }

        public WXPayBuilder setSign(String str) {
            this.sign = str;
            return this;
        }

        public WXPayBuilder setTimeStamp(String str) {
            this.timeStamp = str;
            return this;
        }
    }

    private WXPayUtils(WXPayBuilder wXPayBuilder) {
        this.builder = wXPayBuilder;
    }

    private String genNonceStr() {
        return getMessageDigest(String.valueOf(new Random().nextInt(10000)).getBytes());
    }

    /* access modifiers changed from: private */
    public String genPackageSign(LinkedHashMap<String, String> linkedHashMap, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : linkedHashMap.entrySet()) {
            sb.append((String) next.getKey());
            sb.append('=');
            sb.append((String) next.getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(str);
        return getMessageDigest(sb.toString().getBytes()).toUpperCase();
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String getMessageDigest(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            char[] cArr2 = new char[(r5 * 2)];
            int i = 0;
            for (byte b : instance.digest()) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & ap.m];
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }

    public void toWXPayAndSign(Context context, String str, final String str2) {
        this.iwxapi = WXAPIFactory.createWXAPI(context, (String) null);
        this.iwxapi.registerApp(str);
        new Thread(new Runnable() {
            public void run() {
                PayReq payReq = new PayReq();
                payReq.appId = WXPayUtils.this.builder.getAppId();
                payReq.partnerId = WXPayUtils.this.builder.getPartnerId();
                payReq.prepayId = WXPayUtils.this.builder.getPrepayId();
                payReq.packageValue = "Sign=WXPay";
                payReq.nonceStr = WXPayUtils.this.builder.getNonceStr();
                payReq.timeStamp = WXPayUtils.this.builder.getTimeStamp();
                payReq.sign = WXPayUtils.this.builder.getSign();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("appid", payReq.appId);
                linkedHashMap.put("noncestr", payReq.nonceStr);
                linkedHashMap.put("package", payReq.packageValue);
                linkedHashMap.put("partnerid", payReq.partnerId);
                linkedHashMap.put("prepayid", payReq.prepayId);
                linkedHashMap.put("timestamp", payReq.timeStamp);
                payReq.sign = WXPayUtils.this.genPackageSign(linkedHashMap, str2);
                WXPayUtils.this.iwxapi.sendReq(payReq);
            }
        }).start();
    }

    public void toWXPayNotSign(Context context) {
        this.iwxapi = WXAPIFactory.createWXAPI(context, (String) null);
        this.iwxapi.registerApp(this.builder.getAppId());
        new Thread(new Runnable() {
            public void run() {
                PayReq payReq = new PayReq();
                payReq.appId = WXPayUtils.this.builder.getAppId();
                payReq.partnerId = WXPayUtils.this.builder.getPartnerId();
                payReq.prepayId = WXPayUtils.this.builder.getPrepayId();
                payReq.packageValue = WXPayUtils.this.builder.getPackageValue();
                payReq.nonceStr = WXPayUtils.this.builder.getNonceStr();
                payReq.timeStamp = WXPayUtils.this.builder.getTimeStamp();
                payReq.sign = WXPayUtils.this.builder.getSign();
                WXPayUtils.this.iwxapi.sendReq(payReq);
            }
        }).start();
    }
}
