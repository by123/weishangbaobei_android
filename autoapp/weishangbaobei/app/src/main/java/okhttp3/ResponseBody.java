package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public abstract class ResponseBody implements Closeable {
    private Reader reader;

    public abstract long contentLength();

    @Nullable
    public abstract MediaType contentType();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    /* JADX INFO: finally extract failed */
    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength <= 2147483647L) {
            BufferedSource source = source();
            try {
                byte[] readByteArray = source.readByteArray();
                Util.closeQuietly((Closeable) source);
                if (contentLength == -1 || contentLength == ((long) readByteArray.length)) {
                    return readByteArray;
                }
                throw new IOException("Content-Length (" + contentLength + ") and stream length (" + readByteArray.length + ") disagree");
            } catch (Throwable th) {
                Util.closeQuietly((Closeable) source);
                throw th;
            }
        } else {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
    }

    public final Reader charStream() {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            return reader2;
        }
        BomAwareReader bomAwareReader = new BomAwareReader(source(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }

    public final String string() throws IOException {
        BufferedSource source = source();
        try {
            return source.readString(Util.bomAwareCharset(source, charset()));
        } finally {
            Util.closeQuietly((Closeable) source);
        }
    }

    private Charset charset() {
        MediaType contentType = contentType();
        return contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    public void close() {
        Util.closeQuietly((Closeable) source());
    }

    public static ResponseBody create(@Nullable MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        Buffer writeString = new Buffer().writeString(str, charset);
        return create(mediaType, writeString.size(), writeString);
    }

    public static ResponseBody create(@Nullable MediaType mediaType, byte[] bArr) {
        return create(mediaType, (long) bArr.length, new Buffer().write(bArr));
    }

    public static ResponseBody create(@Nullable MediaType mediaType, ByteString byteString) {
        return create(mediaType, (long) byteString.size(), new Buffer().write(byteString));
    }

    public static ResponseBody create(@Nullable final MediaType mediaType, final long j, final BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            return new ResponseBody() {
                @Nullable
                public MediaType contentType() {
                    return MediaType.this;
                }

                public long contentLength() {
                    return j;
                }

                public BufferedSource source() {
                    return bufferedSource;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
