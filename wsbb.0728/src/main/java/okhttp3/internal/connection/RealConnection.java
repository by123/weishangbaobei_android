package okhttp3.internal.connection;

import io.reactivex.annotations.SchedulerSupport;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection$Listener;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class RealConnection extends Http2Connection$Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool2, Route route2) {
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    private void connectSocket(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Proxy proxy = this.route.proxy();
        this.rawSocket = (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) ? this.route.address().socketFactory().createSocket() : new Socket(proxy);
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e) {
                if (NPE_THROW_WITH_NULL.equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x010a A[Catch:{ all -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0125 A[SYNTHETIC, Splitter:B:39:0x0125] */
    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        AssertionError e;
        SSLSocket sSLSocket;
        Throwable th;
        String str = null;
        Address address = this.route.address();
        try {
            SSLSocket sSLSocket2 = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            try {
                ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                if (configureSecureSocket.supportsTlsExtensions()) {
                    Platform.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                }
                sSLSocket2.startHandshake();
                SSLSession session = sSLSocket2.getSession();
                Handshake handshake2 = Handshake.get(session);
                if (address.hostnameVerifier().verify(address.url().host(), session)) {
                    address.certificatePinner().check(address.url().host(), handshake2.peerCertificates());
                    if (configureSecureSocket.supportsTlsExtensions()) {
                        str = Platform.get().getSelectedProtocol(sSLSocket2);
                    }
                    this.socket = sSLSocket2;
                    this.source = Okio.buffer(Okio.source(this.socket));
                    this.sink = Okio.buffer(Okio.sink(this.socket));
                    this.handshake = handshake2;
                    this.protocol = str != null ? Protocol.get(str) : Protocol.HTTP_1_1;
                    if (sSLSocket2 != null) {
                        Platform.get().afterHandshake(sSLSocket2);
                        return;
                    }
                    return;
                }
                X509Certificate x509Certificate = (X509Certificate) handshake2.peerCertificates().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
            } catch (AssertionError e2) {
                e = e2;
                str = sSLSocket2;
                try {
                    if (!Util.isAndroidGetsocknameError(e)) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    sSLSocket = str;
                }
            } catch (Throwable th3) {
                th = th3;
                sSLSocket = sSLSocket2;
                if (sSLSocket != 0) {
                    Platform.get().afterHandshake(sSLSocket);
                }
                Util.closeQuietly((Socket) sSLSocket);
                throw th;
            }
        } catch (AssertionError e3) {
            e = e3;
            if (!Util.isAndroidGetsocknameError(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    private void connectTunnel(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (i4 < 21) {
            connectSocket(i, i2, call, eventListener);
            createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnelRequest != null) {
                Util.closeQuietly(this.rawSocket);
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null);
                i4++;
            } else {
                return;
            }
        }
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        Response build;
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        do {
            Http1Codec http1Codec = new Http1Codec((OkHttpClient) null, (StreamAllocation) null, this.source, this.sink);
            this.source.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    request = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    if (request == null) {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
            } else if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        } while (!"close".equalsIgnoreCase(build.header("Connection")));
        return request;
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() != null) {
            eventListener.secureConnectStart(call);
            connectTls(connectionSpecSelector);
            eventListener.secureConnectEnd(call, this.handshake);
            if (this.protocol == Protocol.HTTP_2) {
                startHttp2(i);
            }
        } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            this.socket = this.rawSocket;
            this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            startHttp2(i);
        } else {
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
        }
    }

    private void startHttp2(int i) throws IOException {
        this.socket.setSoTimeout(0);
        this.http2Connection = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(i).build();
        this.http2Connection.start();
    }

    public static RealConnection testConnection(ConnectionPool connectionPool2, Route route2, Socket socket2, long j) {
        RealConnection realConnection = new RealConnection(connectionPool2, route2);
        realConnection.socket = socket2;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r8.route.requiresTunnel() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        if (r8.rawSocket == null) goto L_0x011d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0064, code lost:
        if (r8.http2Connection == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0066, code lost:
        r1 = r8.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r8.allocationLimit = r8.http2Connection.maxConcurrentStreams();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0129, code lost:
        throw new okhttp3.internal.connection.RouteException(new java.net.ProtocolException("Too many tunnel connections attempted: 21"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011a A[LOOP:0: B:9:0x0041->B:44:0x011a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0132 A[EDGE_INSN: B:58:0x0132->B:52:0x0132 ?: BREAK  
EDGE_INSN: B:60:0x0132->B:52:0x0132 ?: BREAK  ] */
    public void connect(int i, int i2, int i3, int i4, boolean z, Call call, EventListener eventListener) {
        RouteException routeException;
        if (this.protocol == null) {
            List<ConnectionSpec> connectionSpecs = this.route.address().connectionSpecs();
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
            if (this.route.address().sslSocketFactory() == null) {
                if (connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
                    String host = this.route.address().url().host();
                    if (!Platform.get().isCleartextTrafficPermitted(host)) {
                        throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy"));
                    }
                } else {
                    throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
                }
            } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
            }
            RouteException routeException2 = null;
            while (true) {
                try {
                    routeException = routeException2;
                    if (this.route.requiresTunnel()) {
                        connectTunnel(i, i2, i3, call, eventListener);
                        if (this.rawSocket == null) {
                            break;
                        }
                        try {
                            establishProtocol(connectionSpecSelector, i4, call, eventListener);
                            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
                            break;
                        } catch (IOException e) {
                            e = e;
                        }
                    } else {
                        try {
                            connectSocket(i, i2, call, eventListener);
                            establishProtocol(connectionSpecSelector, i4, call, eventListener);
                            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
                            break;
                            break;
                        } catch (IOException e2) {
                            e = e2;
                            Util.closeQuietly(this.socket);
                            Util.closeQuietly(this.rawSocket);
                            this.socket = null;
                            this.rawSocket = null;
                            this.source = null;
                            this.sink = null;
                            this.handshake = null;
                            this.protocol = null;
                            this.http2Connection = null;
                            eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null, e);
                            if (routeException != null) {
                                routeException2 = new RouteException(e);
                            } else {
                                routeException.addConnectException(e);
                                routeException2 = routeException;
                            }
                            if (!z || !connectionSpecSelector.connectionFailed(e)) {
                                throw routeException2;
                            }
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    Util.closeQuietly(this.socket);
                    Util.closeQuietly(this.rawSocket);
                    this.socket = null;
                    this.rawSocket = null;
                    this.source = null;
                    this.sink = null;
                    this.handshake = null;
                    this.protocol = null;
                    this.http2Connection = null;
                    eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null, e);
                    if (routeException != null) {
                    }
                    if (!z || !connectionSpecSelector.connectionFailed(e)) {
                    }
                }
            }
            throw routeException2;
        }
        throw new IllegalStateException("already connected");
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isEligible(Address address, @Nullable Route route2) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route2 == null || route2.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route2.socketAddress()) || route2.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    public boolean isHealthy(boolean z) {
        int soTimeout;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.http2Connection != null) {
            return !this.http2Connection.isShutdown();
        }
        if (!z) {
            return true;
        }
        try {
            soTimeout = this.socket.getSoTimeout();
            this.socket.setSoTimeout(1);
            if (this.source.exhausted()) {
                this.socket.setSoTimeout(soTimeout);
                return false;
            }
            this.socket.setSoTimeout(soTimeout);
            return true;
        } catch (SocketTimeoutException e) {
            return true;
        } catch (IOException e2) {
            return false;
        } catch (Throwable th) {
            this.socket.setSoTimeout(soTimeout);
            throw th;
        }
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor$Chain interceptor$Chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, interceptor$Chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(interceptor$Chain.readTimeoutMillis());
        this.source.timeout().timeout((long) interceptor$Chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) interceptor$Chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public RealWebSocket.Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        return new 1(this, true, this.source, this.sink, streamAllocation);
    }

    public void onSettings(Http2Connection http2Connection2) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection2.maxConcurrentStreams();
        }
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public Route route() {
        return this.route;
    }

    public Socket socket() {
        return this.socket;
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (!httpUrl.host().equals(this.route.address().url().host())) {
            return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(":");
        sb.append(this.route.address().url().port());
        sb.append(", proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        sb.append(this.handshake != null ? this.handshake.cipherSuite() : SchedulerSupport.NONE);
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
