package okhttp3.internal.cache;

import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r3.cacheControl().noStore() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        if (r4.cacheControl().noStore() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r3.cacheControl().isPrivate() == false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCacheable(okhttp3.Response r3, okhttp3.Request r4) {
        /*
            int r0 = r3.code()
            r1 = 0
            switch(r0) {
                case 200: goto L_0x0030;
                case 203: goto L_0x0030;
                case 204: goto L_0x0030;
                case 300: goto L_0x0030;
                case 301: goto L_0x0030;
                case 302: goto L_0x0009;
                case 307: goto L_0x0009;
                case 308: goto L_0x0030;
                case 404: goto L_0x0030;
                case 405: goto L_0x0030;
                case 410: goto L_0x0030;
                case 414: goto L_0x0030;
                case 501: goto L_0x0030;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0046
        L_0x0009:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.header(r0)
            if (r0 != 0) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            int r0 = r0.maxAgeSeconds()
            r2 = -1
            if (r0 != r2) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            boolean r0 = r0.isPublic()
            if (r0 != 0) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            boolean r0 = r0.isPrivate()
            if (r0 == 0) goto L_0x0046
        L_0x0030:
            okhttp3.CacheControl r3 = r3.cacheControl()
            boolean r3 = r3.noStore()
            if (r3 != 0) goto L_0x0045
            okhttp3.CacheControl r3 = r4.cacheControl()
            boolean r3 = r3.noStore()
            if (r3 != 0) goto L_0x0045
            r1 = 1
        L_0x0045:
            return r1
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.isCacheable(okhttp3.Response, okhttp3.Request):boolean");
    }
}
