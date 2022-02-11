package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

final class OkHttpCall<T> implements Call<T> {
    @Nullable
    private final Object[] args;
    private volatile boolean canceled;
    @GuardedBy("this")
    @Nullable
    private Throwable creationFailure;
    @GuardedBy("this")
    private boolean executed;
    @GuardedBy("this")
    @Nullable
    private Call rawCall;
    private final ServiceMethod<T, ?> serviceMethod;

    OkHttpCall(ServiceMethod<T, ?> serviceMethod2, @Nullable Object[] objArr) {
        this.serviceMethod = serviceMethod2;
        this.args = objArr;
    }

    private Call createRawCall() throws IOException {
        Call call = this.serviceMethod.toCall(this.args);
        if (call != null) {
            return call;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    public void cancel() {
        Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.serviceMethod, this.args);
    }

    public void enqueue(Callback<T> callback) {
        Throwable th;
        Call call;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Call call2 = this.rawCall;
                th = this.creationFailure;
                if (call2 == null && th == null) {
                    try {
                        call = createRawCall();
                        this.rawCall = call;
                    } catch (Throwable th2) {
                        th = th2;
                        Utils.throwIfFatal(th);
                        this.creationFailure = th;
                        call = call2;
                    }
                } else {
                    call = call2;
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new 1(this, callback));
    }

    public Response<T> execute() throws IOException {
        Call call;
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                if (this.creationFailure == null) {
                    call = this.rawCall;
                    if (call == null) {
                        try {
                            call = createRawCall();
                            this.rawCall = call;
                        } catch (IOException | Error | RuntimeException e) {
                            Utils.throwIfFatal(e);
                            this.creationFailure = e;
                            throw e;
                        }
                    }
                } else if (this.creationFailure instanceof IOException) {
                    throw ((IOException) this.creationFailure);
                } else if (this.creationFailure instanceof RuntimeException) {
                    throw ((RuntimeException) this.creationFailure);
                } else {
                    throw ((Error) this.creationFailure);
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (this.canceled) {
            call.cancel();
        }
        return parseResponse(call.execute());
    }

    public boolean isCanceled() {
        boolean z = true;
        if (!this.canceled) {
            synchronized (this) {
                if (this.rawCall == null || !this.rawCall.isCanceled()) {
                    z = false;
                }
            }
        }
        return z;
    }

    public boolean isExecuted() {
        boolean z;
        synchronized (this) {
            z = this.executed;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public Response<T> parseResponse(Response response) throws IOException {
        ResponseBody body = response.body();
        Response build = response.newBuilder().body(new NoContentResponseBody(body.contentType(), body.contentLength())).build();
        int code = build.code();
        if (code < 200 || code >= 300) {
            try {
                return Response.error(Utils.buffer(body), build);
            } finally {
                body.close();
            }
        } else if (code == 204 || code == 205) {
            body.close();
            return Response.success(null, build);
        } else {
            ExceptionCatchingRequestBody exceptionCatchingRequestBody = new ExceptionCatchingRequestBody(body);
            try {
                return Response.success(this.serviceMethod.toResponse(exceptionCatchingRequestBody), build);
            } catch (RuntimeException e) {
                exceptionCatchingRequestBody.throwIfCaught();
                throw e;
            }
        }
    }

    public Request request() {
        Request request;
        synchronized (this) {
            Call call = this.rawCall;
            if (call != null) {
                request = call.request();
            } else if (this.creationFailure == null) {
                try {
                    Call createRawCall = createRawCall();
                    this.rawCall = createRawCall;
                    request = createRawCall.request();
                } catch (Error | RuntimeException e) {
                    Utils.throwIfFatal(e);
                    this.creationFailure = e;
                    throw e;
                } catch (IOException e2) {
                    this.creationFailure = e2;
                    throw new RuntimeException("Unable to create request.", e2);
                }
            } else if (this.creationFailure instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            } else if (this.creationFailure instanceof RuntimeException) {
                throw ((RuntimeException) this.creationFailure);
            } else {
                throw ((Error) this.creationFailure);
            }
        }
        return request;
    }
}
