package okio;

import javax.annotation.Nullable;

final class SegmentPool {
    static final long MAX_SIZE = 65536;
    static long byteCount;
    @Nullable
    static Segment next;

    private SegmentPool() {
    }

    static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        } else if (!segment.shared) {
            synchronized (SegmentPool.class) {
                try {
                    if (byteCount + 8192 <= MAX_SIZE) {
                        byteCount += 8192;
                        segment.next = next;
                        segment.limit = 0;
                        segment.pos = 0;
                        next = segment;
                    }
                } catch (Throwable th) {
                    Class<SegmentPool> cls = SegmentPool.class;
                    throw th;
                }
            }
        }
    }

    static Segment take() {
        synchronized (SegmentPool.class) {
            try {
                if (next == null) {
                    return new Segment();
                }
                Segment segment = next;
                next = segment.next;
                segment.next = null;
                byteCount -= 8192;
                return segment;
            } catch (Throwable th) {
                while (true) {
                    Class<SegmentPool> cls = SegmentPool.class;
                    throw th;
                }
            }
        }
    }
}
