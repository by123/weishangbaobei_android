package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;

public final class ConnectionPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final Runnable cleanupRunnable;
    boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    final RouteDatabase routeDatabase;

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.cleanupRunnable = new 1(this);
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    public synchronized int idleConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public RealConnection get(Address address, StreamAllocation streamAllocation, Route route) {
        for (RealConnection next : this.connections) {
            if (next.isEligible(address, route)) {
                streamAllocation.acquire(next, true);
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        for (RealConnection next : this.connections) {
            if (next.isEligible(address, (Route) null) && next.isMultiplexed() && next != streamAllocation.connection()) {
                return streamAllocation.releaseAndAcquire(next);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    /* access modifiers changed from: package-private */
    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public void evictAll() {
        ArrayList<RealConnection> arrayList = new ArrayList<>();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.allocations.isEmpty()) {
                    next.noNewStreams = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        for (RealConnection socket : arrayList) {
            Util.closeQuietly(socket.socket());
        }
    }

    /* access modifiers changed from: package-private */
    public long cleanup(long j) {
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            RealConnection realConnection = null;
            int i = 0;
            int i2 = 0;
            for (RealConnection next : this.connections) {
                if (pruneAndGetAllocationCount(next, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - next.idleAtNanos;
                    if (j3 > j2) {
                        realConnection = next;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.keepAliveDurationNs) {
                if (i <= this.maxIdleConnections) {
                    if (i > 0) {
                        long j4 = this.keepAliveDurationNs - j2;
                        return j4;
                    } else if (i2 > 0) {
                        long j5 = this.keepAliveDurationNs;
                        return j5;
                    } else {
                        this.cleanupRunning = false;
                        return -1;
                    }
                }
            }
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.socket());
            return 0;
        }
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<StreamAllocation>> list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            StreamAllocation.StreamAllocationReference streamAllocationReference = (Reference) list.get(i);
            if (streamAllocationReference.get() != null) {
                i++;
            } else {
                Platform.get().logCloseableLeak("A connection to " + realConnection.route().address().url() + " was leaked. Did you forget to close a response body?", streamAllocationReference.callStackTrace);
                list.remove(i);
                realConnection.noNewStreams = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
