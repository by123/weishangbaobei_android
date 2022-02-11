package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    @Nullable
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x004a  */
    public static CacheControl parse(Headers headers) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        int size = headers.size();
        boolean z = true;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        int i7 = -1;
        int i8 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i9 = -1;
        int i10 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        int i11 = 0;
        while (i11 < size) {
            String name = headers.name(i11);
            String value = headers.value(i11);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str2 == null) {
                    str2 = value;
                    i5 = 0;
                    i4 = i7;
                    i3 = i8;
                    i2 = i9;
                    i = i10;
                    while (i5 < value.length()) {
                        int skipUntil = HttpHeaders.skipUntil(value, i5, "=,;");
                        String trim = value.substring(i5, skipUntil).trim();
                        if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                            i6 = skipUntil + 1;
                            str = null;
                        } else {
                            int skipWhitespace = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                            if (skipWhitespace >= value.length() || value.charAt(skipWhitespace) != '\"') {
                                i6 = HttpHeaders.skipUntil(value, skipWhitespace, ",;");
                                str = value.substring(skipWhitespace, i6).trim();
                            } else {
                                int i12 = skipWhitespace + 1;
                                int skipUntil2 = HttpHeaders.skipUntil(value, i12, "\"");
                                str = value.substring(i12, skipUntil2);
                                i6 = skipUntil2 + 1;
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i4 = HttpHeaders.parseSeconds(str, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i3 = HttpHeaders.parseSeconds(str, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i2 = HttpHeaders.parseSeconds(str, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i = HttpHeaders.parseSeconds(str, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                            z7 = true;
                        } else if ("no-transform".equalsIgnoreCase(trim)) {
                            z8 = true;
                        } else if ("immutable".equalsIgnoreCase(trim)) {
                            z9 = true;
                        }
                        i5 = i6;
                        i4 = i4;
                        i3 = i3;
                        i2 = i2;
                    }
                    i11++;
                    i7 = i4;
                    i8 = i3;
                    i9 = i2;
                    i10 = i;
                }
            } else if (!name.equalsIgnoreCase("Pragma")) {
                i = i10;
                i2 = i9;
                i3 = i8;
                i4 = i7;
                i11++;
                i7 = i4;
                i8 = i3;
                i9 = i2;
                i10 = i;
            }
            z = false;
            i5 = 0;
            i4 = i7;
            i3 = i8;
            i2 = i9;
            i = i10;
            while (i5 < value.length()) {
            }
            i11++;
            i7 = i4;
            i8 = i3;
            i9 = i2;
            i10 = i;
        }
        return new CacheControl(z2, z3, i7, i8, z4, z5, z6, i9, i10, z7, z8, z9, !z ? null : str2);
    }

    public boolean immutable() {
        return this.immutable;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue2 = headerValue();
        this.headerValue = headerValue2;
        return headerValue2;
    }
}
