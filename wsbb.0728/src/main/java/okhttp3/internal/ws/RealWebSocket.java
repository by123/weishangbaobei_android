package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, WebSocketReader$FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random2, long j) {
        if ("GET".equals(request.method())) {
            this.originalRequest = request;
            this.listener = webSocketListener;
            this.random = random2;
            this.pingIntervalMillis = j;
            byte[] bArr = new byte[16];
            random2.nextBytes(bArr);
            this.key = ByteString.of(bArr).base64();
            this.writerRunnable = new 1(this);
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    private void runWriter() {
        if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return false;
     */
    private boolean send(ByteString byteString, int i) {
        synchronized (this) {
            if (!this.failed && !this.enqueuedClose) {
                if (this.queueSize + ((long) byteString.size()) > MAX_QUEUE_SIZE) {
                    close(1001, (String) null);
                    return false;
                }
                this.queueSize += (long) byteString.size();
                this.messageAndCloseQueue.add(new Message(i, byteString));
                runWriter();
                return true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void awaitTermination(int i, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination((long) i, timeUnit);
    }

    public void cancel() {
        this.call.cancel();
    }

    /* access modifiers changed from: package-private */
    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() == 101) {
            String header = response.header("Connection");
            if ("Upgrade".equalsIgnoreCase(header)) {
                String header2 = response.header("Upgrade");
                if ("websocket".equalsIgnoreCase(header2)) {
                    String header3 = response.header("Sec-WebSocket-Accept");
                    String base64 = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
                    if (!base64.equals(header3)) {
                        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header3 + "'");
                    }
                    return;
                }
                throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header2 + "'");
            }
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header + "'");
        }
        throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
    }

    public boolean close(int i, String str) {
        return close(i, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return false;
     */
    public boolean close(int i, String str, long j) {
        synchronized (this) {
            WebSocketProtocol.validateCloseCode(i);
            ByteString byteString = null;
            if (str != null) {
                byteString = ByteString.encodeUtf8(str);
                if (((long) byteString.size()) > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: " + str);
                }
            }
            if (!this.failed && !this.enqueuedClose) {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(i, byteString, j));
                runWriter();
                return true;
            }
        }
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient build = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request build2 = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(build, build2);
        this.call.enqueue(new 2(this, build2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.listener.onFailure(r3, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    public void failWebSocket(Exception exc, @Nullable Response response) {
        synchronized (this) {
            if (!this.failed) {
                this.failed = true;
                Streams streams2 = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                if (this.executor != null) {
                    this.executor.shutdown();
                }
            }
        }
    }

    public void initReaderAndWriter(String str, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (this.pingIntervalMillis != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(this), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    public void onReadClose(int i, String str) {
        Closeable closeable;
        if (i != -1) {
            synchronized (this) {
                if (this.receivedCloseCode == -1) {
                    this.receivedCloseCode = i;
                    this.receivedCloseReason = str;
                    if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                        closeable = null;
                    } else {
                        Closeable closeable2 = this.streams;
                        this.streams = null;
                        if (this.cancelFuture != null) {
                            this.cancelFuture.cancel(false);
                        }
                        this.executor.shutdown();
                        closeable = closeable2;
                    }
                } else {
                    throw new IllegalStateException("already closed");
                }
            }
            try {
                this.listener.onClosing(this, i, str);
                if (closeable != null) {
                    this.listener.onClosed(this, i, str);
                }
            } finally {
                Util.closeQuietly(closeable);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void onReadMessage(String str) throws IOException {
        this.listener.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        this.listener.onMessage((WebSocket) this, byteString);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    public void onReadPing(ByteString byteString) {
        synchronized (this) {
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString);
                runWriter();
                this.receivedPingCount++;
            }
        }
    }

    public void onReadPong(ByteString byteString) {
        synchronized (this) {
            this.receivedPongCount++;
            this.awaitingPong = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        return false;
     */
    public boolean pong(ByteString byteString) {
        synchronized (this) {
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString);
                runWriter();
                return true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            return this.receivedCloseCode == -1;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    public long queueSize() {
        long j;
        synchronized (this) {
            j = this.queueSize;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public int receivedPingCount() {
        int i;
        synchronized (this) {
            i = this.receivedPingCount;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int receivedPongCount() {
        int i;
        synchronized (this) {
            i = this.receivedPongCount;
        }
        return i;
    }

    public Request request() {
        return this.originalRequest;
    }

    public boolean send(String str) {
        if (str != null) {
            return send(ByteString.encodeUtf8(str), 1);
        }
        throw new NullPointerException("text == null");
    }

    public boolean send(ByteString byteString) {
        if (byteString != null) {
            return send(byteString, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    /* access modifiers changed from: package-private */
    public int sentPingCount() {
        int i;
        synchronized (this) {
            i = this.sentPingCount;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void tearDown() throws InterruptedException {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r2 == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r10.writePong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        okhttp3.internal.Util.closeQuietly(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005d, code lost:
        if ((r3 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        r2 = ((okhttp3.internal.ws.RealWebSocket.Message) r3).data;
        r3 = okio.Okio.buffer(r10.newMessageSink(((okhttp3.internal.ws.RealWebSocket.Message) r3).formatOpcode, (long) r2.size()));
        r3.write(r2);
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007c, code lost:
        monitor-enter(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r14.queueSize -= (long) r2.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0088, code lost:
        monitor-exit(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008e, code lost:
        okhttp3.internal.Util.closeQuietly(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0091, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0094, code lost:
        if ((r3 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0096, code lost:
        r3 = (okhttp3.internal.ws.RealWebSocket.Close) r3;
        r10.writeClose(r3.code, r3.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009f, code lost:
        if (r7 == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        r14.listener.onClosed(r14, r8, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ac, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return true;
     */
    public boolean writeOneFrame() throws IOException {
        Object obj;
        String str;
        Closeable closeable;
        Object obj2;
        int i;
        synchronized (this) {
            if (this.failed) {
                return false;
            }
            WebSocketWriter webSocketWriter = this.writer;
            ByteString poll = this.pongQueue.poll();
            if (poll == null) {
                obj = this.messageAndCloseQueue.poll();
                if (obj instanceof Close) {
                    i = this.receivedCloseCode;
                    str = this.receivedCloseReason;
                    if (i != -1) {
                        Closeable closeable2 = this.streams;
                        this.streams = null;
                        this.executor.shutdown();
                        closeable = closeable2;
                        obj2 = obj;
                    } else {
                        this.cancelFuture = this.executor.schedule(new CancelRunnable(this), ((Close) obj).cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
                        closeable = null;
                        obj2 = obj;
                    }
                } else {
                    if (obj == null) {
                        return false;
                    }
                    str = null;
                    closeable = null;
                    obj2 = obj;
                    i = -1;
                }
            } else {
                obj = null;
                str = null;
                closeable = null;
                obj2 = obj;
                i = -1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r0 == -1) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("sent ping but didn't receive pong within ");
        r1.append(r5.pingIntervalMillis);
        r1.append("ms (after ");
        r1.append(r0 - 1);
        r1.append(" successful ping/pongs)");
        failWebSocket(new java.net.SocketTimeoutException(r1.toString()), (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        failWebSocket(r0, (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void writePingFrame() {
        synchronized (this) {
            if (!this.failed) {
                WebSocketWriter webSocketWriter = this.writer;
                int i = this.awaitingPong ? this.sentPingCount : -1;
                this.sentPingCount++;
                this.awaitingPong = true;
            }
        }
    }
}
