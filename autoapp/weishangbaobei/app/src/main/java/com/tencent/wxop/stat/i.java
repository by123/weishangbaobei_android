package com.tencent.wxop.stat;

import android.content.Context;
import com.stub.StubApp;
import com.tencent.a.a.a.a.g;
import com.tencent.a.a.a.a.h;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.l;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

class i {
    private static StatLogger d = l.b();
    private static i e = null;
    private static Context f = null;
    DefaultHttpClient a = null;
    e b = null;
    StringBuilder c = new StringBuilder(4096);
    private long g = 0;

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(3:4|5|6)|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x006e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private i(android.content.Context r5) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            r4.a = r0
            r4.b = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 4096(0x1000, float:5.74E-42)
            r0.<init>(r1)
            r4.c = r0
            r0 = 0
            r4.g = r0
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ Throwable -> 0x0091 }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ Throwable -> 0x0091 }
            f = r5     // Catch:{ Throwable -> 0x0091 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0091 }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            r4.g = r0     // Catch:{ Throwable -> 0x0091 }
            com.tencent.wxop.stat.common.e r5 = new com.tencent.wxop.stat.common.e     // Catch:{ Throwable -> 0x0091 }
            r5.<init>()     // Catch:{ Throwable -> 0x0091 }
            r4.b = r5     // Catch:{ Throwable -> 0x0091 }
            boolean r5 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x0091 }
            if (r5 == 0) goto L_0x006e
            java.lang.String r5 = "org.apache.http.wire"
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)     // Catch:{ Throwable -> 0x006e }
            java.util.logging.Level r0 = java.util.logging.Level.FINER     // Catch:{ Throwable -> 0x006e }
            r5.setLevel(r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.http.headers"
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)     // Catch:{ Throwable -> 0x006e }
            java.util.logging.Level r0 = java.util.logging.Level.FINER     // Catch:{ Throwable -> 0x006e }
            r5.setLevel(r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.commons.logging.Log"
            java.lang.String r0 = "org.apache.commons.logging.impl.SimpleLog"
            java.lang.System.setProperty(r5, r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.commons.logging.simplelog.showdatetime"
            java.lang.String r0 = "true"
            java.lang.System.setProperty(r5, r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.commons.logging.simplelog.log.httpclient.wire"
            java.lang.String r0 = "debug"
            java.lang.System.setProperty(r5, r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.commons.logging.simplelog.log.org.apache.http"
            java.lang.String r0 = "debug"
            java.lang.System.setProperty(r5, r0)     // Catch:{ Throwable -> 0x006e }
            java.lang.String r5 = "org.apache.commons.logging.simplelog.log.org.apache.http.headers"
            java.lang.String r0 = "debug"
            java.lang.System.setProperty(r5, r0)     // Catch:{ Throwable -> 0x006e }
        L_0x006e:
            org.apache.http.params.BasicHttpParams r5 = new org.apache.http.params.BasicHttpParams     // Catch:{ Throwable -> 0x0091 }
            r5.<init>()     // Catch:{ Throwable -> 0x0091 }
            r0 = 0
            org.apache.http.params.HttpConnectionParams.setStaleCheckingEnabled(r5, r0)     // Catch:{ Throwable -> 0x0091 }
            r0 = 10000(0x2710, float:1.4013E-41)
            org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r5, r0)     // Catch:{ Throwable -> 0x0091 }
            org.apache.http.params.HttpConnectionParams.setSoTimeout(r5, r0)     // Catch:{ Throwable -> 0x0091 }
            org.apache.http.impl.client.DefaultHttpClient r0 = new org.apache.http.impl.client.DefaultHttpClient     // Catch:{ Throwable -> 0x0091 }
            r0.<init>(r5)     // Catch:{ Throwable -> 0x0091 }
            r4.a = r0     // Catch:{ Throwable -> 0x0091 }
            org.apache.http.impl.client.DefaultHttpClient r5 = r4.a     // Catch:{ Throwable -> 0x0091 }
            com.tencent.wxop.stat.j r0 = new com.tencent.wxop.stat.j     // Catch:{ Throwable -> 0x0091 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0091 }
            r5.setKeepAliveStrategy(r0)     // Catch:{ Throwable -> 0x0091 }
            return
        L_0x0091:
            r5 = move-exception
            com.tencent.wxop.stat.common.StatLogger r0 = d
            r0.e((java.lang.Throwable) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.i.<init>(android.content.Context):void");
    }

    static Context a() {
        return f;
    }

    static void a(Context context) {
        f = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    private void a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("mid");
            if (h.c(optString)) {
                if (StatConfig.isDebugEnable()) {
                    StatLogger statLogger = d;
                    statLogger.i("update mid:" + optString);
                }
                g.C(f).a(optString);
            }
            if (!jSONObject.isNull("cfg")) {
                StatConfig.a(f, jSONObject.getJSONObject("cfg"));
            }
            if (!jSONObject.isNull("ncts")) {
                int i = jSONObject.getInt("ncts");
                int currentTimeMillis = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                if (StatConfig.isDebugEnable()) {
                    StatLogger statLogger2 = d;
                    statLogger2.i("server time:" + i + ", diff time:" + currentTimeMillis);
                }
                l.x(f);
                l.a(f, currentTimeMillis);
            }
        } catch (Throwable th) {
            d.w(th);
        }
    }

    static i b(Context context) {
        if (e == null) {
            synchronized (i.class) {
                if (e == null) {
                    e = new i(context);
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: package-private */
    public void a(com.tencent.wxop.stat.event.e eVar, h hVar) {
        b(Arrays.asList(new String[]{eVar.g()}), hVar);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02d7, code lost:
        r10 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.List<?> r10, com.tencent.wxop.stat.h r11) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x0306
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x000a
            goto L_0x0306
        L_0x000a:
            int r0 = r10.size()
            r1 = 0
            r10.get(r1)
            r2 = 0
            java.lang.StringBuilder r3 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r4 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r4 = r4.length()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r3.delete(r1, r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r3 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "["
            r3.append(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = "rc4"
            r4 = 0
        L_0x0028:
            if (r4 >= r0) goto L_0x0045
            java.lang.StringBuilder r5 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.Object r6 = r10.get(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.append(r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r5 = r0 + -1
            if (r4 == r5) goto L_0x0042
            java.lang.StringBuilder r5 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = ","
            r5.append(r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x0042:
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0045:
            java.lang.StringBuilder r10 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r0 = "]"
            r10.append(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r10 = r9.c     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r0 = r10.length()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.<init>()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = com.tencent.wxop.stat.StatConfig.getStatReportUrl()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "/?index="
            r4.append(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            long r5 = r9.g     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.append(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            long r5 = r9.g     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r7 = 1
            long r5 = r5 + r7
            r9.g = r5     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            boolean r5 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r5 == 0) goto L_0x00a0
            com.tencent.wxop.stat.common.StatLogger r5 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "["
            r6.<init>(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.append(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "]Send request("
            r6.append(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.append(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "bytes), content:"
            r6.append(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.append(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.i(r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x00a0:
            org.apache.http.client.methods.HttpPost r5 = new org.apache.http.client.methods.HttpPost     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "Accept-Encoding"
            java.lang.String r6 = "gzip"
            r5.addHeader(r4, r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "Connection"
            java.lang.String r6 = "Keep-Alive"
            r5.setHeader(r4, r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "Cache-Control"
            r5.removeHeaders(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            android.content.Context r4 = f     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            com.tencent.wxop.stat.a r4 = com.tencent.wxop.stat.a.a((android.content.Context) r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.HttpHost r4 = r4.a()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = "Content-Encoding"
            r5.addHeader(r6, r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 != 0) goto L_0x00d5
            org.apache.http.impl.client.DefaultHttpClient r6 = r9.a     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.params.HttpParams r6 = r6.getParams()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "http.route.default-proxy"
            r6.removeParameter(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x0117
        L_0x00d5:
            boolean r6 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r6 == 0) goto L_0x00f2
            com.tencent.wxop.stat.common.StatLogger r6 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r8 = "proxy:"
            r7.<init>(r8)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r8 = r4.toHostString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r7.append(r8)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.d(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x00f2:
            java.lang.String r6 = "X-Content-Encoding"
            r5.addHeader(r6, r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.impl.client.DefaultHttpClient r6 = r9.a     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.params.HttpParams r6 = r6.getParams()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "http.route.default-proxy"
            r6.setParameter(r7, r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = "X-Online-Host"
            java.lang.String r7 = com.tencent.wxop.stat.StatConfig.k     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.addHeader(r6, r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = "Accept"
            java.lang.String r7 = "*/*"
            r5.addHeader(r6, r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r6 = "Content-Type"
            java.lang.String r7 = "json"
            r5.addHeader(r6, r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x0117:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.<init>(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "UTF-8"
            byte[] r10 = r10.getBytes(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r7 = r10.length     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r8 = com.tencent.wxop.stat.StatConfig.o     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r0 <= r8) goto L_0x0129
            r0 = 1
            goto L_0x012a
        L_0x0129:
            r0 = 0
        L_0x012a:
            if (r0 == 0) goto L_0x0196
            java.lang.String r0 = "Content-Encoding"
            r5.removeHeaders(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r0.<init>()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r0.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = ",gzip"
            r0.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = "Content-Encoding"
            r5.addHeader(r3, r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 == 0) goto L_0x0153
            java.lang.String r3 = "X-Content-Encoding"
            r5.removeHeaders(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = "X-Content-Encoding"
            r5.addHeader(r3, r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x0153:
            r0 = 4
            byte[] r3 = new byte[r0]     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r6.write(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r3.write(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r3.close()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            byte[] r10 = r6.toByteArray()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r10, r1, r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r0.putInt(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            boolean r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r0 == 0) goto L_0x0196
            com.tencent.wxop.stat.common.StatLogger r0 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = "before Gzip:"
            r1.<init>(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r1.append(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = " bytes, after Gzip:"
            r1.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r3 = r10.length     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r1.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r3 = " bytes"
            r1.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r0.d(r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x0196:
            byte[] r10 = com.tencent.wxop.stat.common.f.a(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.entity.ByteArrayEntity r0 = new org.apache.http.entity.ByteArrayEntity     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r0.<init>(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.setEntity(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.impl.client.DefaultHttpClient r10 = r9.a     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.HttpResponse r10 = r10.execute(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.HttpEntity r0 = r10.getEntity()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            org.apache.http.StatusLine r1 = r10.getStatusLine()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r1 = r1.getStatusCode()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            long r3 = r0.getContentLength()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            boolean r5 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r5 == 0) goto L_0x01d9
            com.tencent.wxop.stat.common.StatLogger r5 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r8 = "http recv response status code:"
            r7.<init>(r8)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r7.append(r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r8 = ", content length:"
            r7.append(r8)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r7.append(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.i(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x01d9:
            r7 = 0
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x01ef
            com.tencent.wxop.stat.common.StatLogger r10 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r1 = "Server response no data."
            r10.e((java.lang.Object) r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r11 == 0) goto L_0x01eb
            r11.b()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x01eb:
            org.apache.http.util.EntityUtils.toString(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            return
        L_0x01ef:
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x02cd
            java.io.InputStream r3 = r0.getContent()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            long r7 = r0.getContentLength()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            int r0 = (int) r7     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.readFully(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r3.close()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.close()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "Content-Encoding"
            org.apache.http.Header r10 = r10.getFirstHeader(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r10 == 0) goto L_0x025f
            java.lang.String r4 = r10.getValue()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "gzip,rc4"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 == 0) goto L_0x0229
            byte[] r10 = com.tencent.wxop.stat.common.l.a((byte[]) r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            byte[] r0 = com.tencent.wxop.stat.common.f.b(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x025f
        L_0x0229:
            java.lang.String r4 = r10.getValue()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "rc4,gzip"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 == 0) goto L_0x023e
            byte[] r10 = com.tencent.wxop.stat.common.f.b(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            byte[] r0 = com.tencent.wxop.stat.common.l.a((byte[]) r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x025f
        L_0x023e:
            java.lang.String r4 = r10.getValue()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "gzip"
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 == 0) goto L_0x024f
            byte[] r0 = com.tencent.wxop.stat.common.l.a((byte[]) r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x025f
        L_0x024f:
            java.lang.String r10 = r10.getValue()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "rc4"
            boolean r10 = r10.equalsIgnoreCase(r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r10 == 0) goto L_0x025f
            byte[] r0 = com.tencent.wxop.stat.common.f.b(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x025f:
            java.lang.String r10 = new java.lang.String     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r4 = "UTF-8"
            r10.<init>(r0, r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            boolean r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r4 == 0) goto L_0x027f
            com.tencent.wxop.stat.common.StatLogger r4 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r7 = "http get response data:"
            r5.<init>(r7)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r5.append(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.i(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x027f:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.<init>(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r10 = 200(0xc8, float:2.8E-43)
            if (r1 != r10) goto L_0x02a4
            r9.a((org.json.JSONObject) r4)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r11 == 0) goto L_0x02c9
            java.lang.String r10 = "ret"
            int r10 = r4.optInt(r10)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r10 != 0) goto L_0x0299
            r11.a()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x02c9
        L_0x0299:
            com.tencent.wxop.stat.common.StatLogger r10 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r0 = "response error data."
            r10.error((java.lang.Object) r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x02a0:
            r11.b()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x02c9
        L_0x02a4:
            com.tencent.wxop.stat.common.StatLogger r10 = d     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "Server response error code:"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.append(r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r1 = ", error:"
            r4.append(r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r1 = new java.lang.String     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r5 = "UTF-8"
            r1.<init>(r0, r5)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r4.append(r1)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            java.lang.String r0 = r4.toString()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r10.error((java.lang.Object) r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            if (r11 == 0) goto L_0x02c9
            goto L_0x02a0
        L_0x02c9:
            r3.close()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            goto L_0x02d0
        L_0x02cd:
            org.apache.http.util.EntityUtils.toString(r0)     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
        L_0x02d0:
            r6.close()     // Catch:{ Throwable -> 0x02d7, all -> 0x02d5 }
            r10 = r2
            goto L_0x02d8
        L_0x02d5:
            r10 = move-exception
            throw r10
        L_0x02d7:
            r10 = move-exception
        L_0x02d8:
            if (r10 == 0) goto L_0x0306
            com.tencent.wxop.stat.common.StatLogger r0 = d
            r0.error((java.lang.Throwable) r10)
            if (r11 == 0) goto L_0x02eb
            r11.b()     // Catch:{ Throwable -> 0x02e5 }
            goto L_0x02eb
        L_0x02e5:
            r11 = move-exception
            com.tencent.wxop.stat.common.StatLogger r0 = d
            r0.e((java.lang.Throwable) r11)
        L_0x02eb:
            boolean r10 = r10 instanceof java.lang.OutOfMemoryError
            if (r10 == 0) goto L_0x02fd
            java.lang.System.gc()
            r9.c = r2
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r11 = 2048(0x800, float:2.87E-42)
            r10.<init>(r11)
            r9.c = r10
        L_0x02fd:
            android.content.Context r10 = f
            com.tencent.wxop.stat.a r10 = com.tencent.wxop.stat.a.a((android.content.Context) r10)
            r10.d()
        L_0x0306:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.i.a(java.util.List, com.tencent.wxop.stat.h):void");
    }

    /* access modifiers changed from: package-private */
    public void b(List<?> list, h hVar) {
        if (this.b != null) {
            this.b.a(new k(this, list, hVar));
        }
    }
}
