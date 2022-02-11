package okhttp3;

import com.yongchun.library.view.ImagePreviewFragment;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

public final class Cookie {
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.hostOnly = z3;
        this.persistent = z4;
    }

    Cookie(Builder builder) {
        if (builder.name == null) {
            throw new NullPointerException("builder.name == null");
        } else if (builder.value == null) {
            throw new NullPointerException("builder.value == null");
        } else if (builder.domain != null) {
            this.name = builder.name;
            this.value = builder.value;
            this.expiresAt = builder.expiresAt;
            this.domain = builder.domain;
            this.path = builder.path;
            this.secure = builder.secure;
            this.httpOnly = builder.httpOnly;
            this.persistent = builder.persistent;
            this.hostOnly = builder.hostOnly;
        } else {
            throw new NullPointerException("builder.domain == null");
        }
    }

    private static int dateCharacterOffset(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (((charAt < ' ' && charAt != 9) || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z)) {
                return i3;
            }
        }
        return i2;
    }

    private static boolean domainMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) + -1) == '.' && !Util.verifyAsIpAddress(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0102 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x013f  */
    @Nullable
    static Cookie parse(long j, HttpUrl httpUrl, String str) {
        long j2;
        String host;
        String str2;
        String str3;
        long parseMaxAge;
        int length = str.length();
        int delimiterOffset = Util.delimiterOffset(str, 0, length, ';');
        int delimiterOffset2 = Util.delimiterOffset(str, 0, delimiterOffset, '=');
        if (delimiterOffset2 == delimiterOffset) {
            return null;
        }
        String trimSubstring = Util.trimSubstring(str, 0, delimiterOffset2);
        if (trimSubstring.isEmpty() || Util.indexOfControlOrNonAscii(trimSubstring) != -1) {
            return null;
        }
        String trimSubstring2 = Util.trimSubstring(str, delimiterOffset2 + 1, delimiterOffset);
        if (Util.indexOfControlOrNonAscii(trimSubstring2) != -1) {
            return null;
        }
        int i = delimiterOffset + 1;
        long j3 = -1;
        long j4 = 253402300799999L;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        String str4 = null;
        String str5 = null;
        while (i < length) {
            int delimiterOffset3 = Util.delimiterOffset(str, i, length, ';');
            int delimiterOffset4 = Util.delimiterOffset(str, i, delimiterOffset3, '=');
            String trimSubstring3 = Util.trimSubstring(str, i, delimiterOffset4);
            String trimSubstring4 = delimiterOffset4 < delimiterOffset3 ? Util.trimSubstring(str, delimiterOffset4 + 1, delimiterOffset3) : "";
            if (trimSubstring3.equalsIgnoreCase("expires")) {
                try {
                    j4 = parseExpires(trimSubstring4, 0, trimSubstring4.length());
                    parseMaxAge = j3;
                } catch (IllegalArgumentException e) {
                }
            } else {
                if (trimSubstring3.equalsIgnoreCase("max-age")) {
                    try {
                        parseMaxAge = parseMaxAge(trimSubstring4);
                    } catch (NumberFormatException e2) {
                    }
                } else if (trimSubstring3.equalsIgnoreCase("domain")) {
                    z3 = false;
                    str4 = parseDomain(trimSubstring4);
                } else if (trimSubstring3.equalsIgnoreCase(ImagePreviewFragment.PATH)) {
                    str5 = trimSubstring4;
                } else if (trimSubstring3.equalsIgnoreCase("secure")) {
                    z = true;
                } else if (trimSubstring3.equalsIgnoreCase("httponly")) {
                    z2 = true;
                }
                i = delimiterOffset3 + 1;
            }
            z4 = true;
            j3 = parseMaxAge;
            i = delimiterOffset3 + 1;
        }
        long j5 = Long.MIN_VALUE;
        if (j3 != Long.MIN_VALUE) {
            if (j3 != -1) {
                j5 = (j3 <= 9223372036854775L ? 1000 * j3 : Long.MAX_VALUE) + j;
                if (j5 < j || j5 > 253402300799999L) {
                    j2 = 253402300799999L;
                }
            } else {
                j2 = j4;
            }
            host = httpUrl.host();
            if (str4 != null) {
                str2 = host;
            } else if (!domainMatch(host, str4)) {
                return null;
            } else {
                str2 = str4;
            }
            if (host.length() != str2.length() && PublicSuffixDatabase.get().getEffectiveTldPlusOne(str2) == null) {
                return null;
            }
            if (str5 == null || !str5.startsWith("/")) {
                String encodedPath = httpUrl.encodedPath();
                int lastIndexOf = encodedPath.lastIndexOf(47);
                str3 = lastIndexOf != 0 ? encodedPath.substring(0, lastIndexOf) : "/";
            } else {
                str3 = str5;
            }
            return new Cookie(trimSubstring, trimSubstring2, j2, str2, str3, z, z2, z3, z4);
        }
        j2 = j5;
        host = httpUrl.host();
        if (str4 != null) {
        }
        if (host.length() != str2.length() || PublicSuffixDatabase.get().getEffectiveTldPlusOne(str2) == null) {
        }
    }

    @Nullable
    public static Cookie parse(HttpUrl httpUrl, String str) {
        return parse(System.currentTimeMillis(), httpUrl, str);
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        ArrayList arrayList;
        List<String> values = headers.values("Set-Cookie");
        int size = values.size();
        ArrayList arrayList2 = null;
        int i = 0;
        while (i < size) {
            Cookie parse = parse(httpUrl, values.get(i));
            if (parse == null) {
                arrayList = arrayList2;
            } else {
                arrayList = arrayList2 == null ? new ArrayList() : arrayList2;
                arrayList.add(parse);
            }
            i++;
            arrayList2 = arrayList;
        }
        return arrayList2 != null ? Collections.unmodifiableList(arrayList2) : Collections.emptyList();
    }

    private static String parseDomain(String str) {
        if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            String canonicalizeHost = Util.canonicalizeHost(str);
            if (canonicalizeHost != null) {
                return canonicalizeHost;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static long parseExpires(String str, int i, int i2) {
        int dateCharacterOffset = dateCharacterOffset(str, i, i2, false);
        Matcher matcher = TIME_PATTERN.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (dateCharacterOffset < i2) {
            int dateCharacterOffset2 = dateCharacterOffset(str, dateCharacterOffset + 1, i2, true);
            matcher.region(dateCharacterOffset, dateCharacterOffset2);
            if (i3 == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
                i7 = Integer.parseInt(matcher.group(2));
                i8 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (i6 == -1 && matcher.usePattern(MONTH_PATTERN).matches()) {
                i6 = MONTH_PATTERN.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i4 == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
            }
            dateCharacterOffset = dateCharacterOffset(str, dateCharacterOffset2 + 1, i2, false);
        }
        if (i4 >= 70 && i4 <= 99) {
            i4 += 1900;
        }
        if (i4 >= 0 && i4 <= 69) {
            i4 += BannerConfig.TIME;
        }
        if (i4 < 1601) {
            throw new IllegalArgumentException();
        } else if (i6 == -1) {
            throw new IllegalArgumentException();
        } else if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        } else if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        } else if (i7 < 0 || i7 > 59) {
            throw new IllegalArgumentException();
        } else if (i8 < 0 || i8 > 59) {
            throw new IllegalArgumentException();
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i4);
            gregorianCalendar.set(2, i6 - 1);
            gregorianCalendar.set(5, i5);
            gregorianCalendar.set(11, i3);
            gregorianCalendar.set(12, i7);
            gregorianCalendar.set(13, i8);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    private static long parseMaxAge(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                return !str.startsWith("-") ? Long.MAX_VALUE : Long.MIN_VALUE;
            }
            throw e;
        }
    }

    private static boolean pathMatch(HttpUrl httpUrl, String str) {
        String encodedPath = httpUrl.encodedPath();
        if (encodedPath.equals(str)) {
            return true;
        }
        return encodedPath.startsWith(str) && (str.endsWith("/") || encodedPath.charAt(str.length()) == '/');
    }

    public String domain() {
        return this.domain;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        return cookie.name.equals(this.name) && cookie.value.equals(this.value) && cookie.domain.equals(this.domain) && cookie.path.equals(this.path) && cookie.expiresAt == this.expiresAt && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public int hashCode() {
        return ((((((((((((((((this.name.hashCode() + 527) * 31) + this.value.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (this.expiresAt ^ (this.expiresAt >>> 32)))) * 31) + (this.secure ^ true ? 1 : 0)) * 31) + (this.httpOnly ^ true ? 1 : 0)) * 31) + (this.persistent ^ true ? 1 : 0)) * 31) + (this.hostOnly ^ true ? 1 : 0);
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public boolean matches(HttpUrl httpUrl) {
        if ((this.hostOnly ? httpUrl.host().equals(this.domain) : domainMatch(httpUrl.host(), this.domain)) && pathMatch(httpUrl, this.path)) {
            return !this.secure || httpUrl.isHttps();
        }
        return false;
    }

    public String name() {
        return this.name;
    }

    public String path() {
        return this.path;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public boolean secure() {
        return this.secure;
    }

    public String toString() {
        return toString(false);
    }

    /* access modifiers changed from: package-private */
    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=");
        sb.append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String value() {
        return this.value;
    }
}
