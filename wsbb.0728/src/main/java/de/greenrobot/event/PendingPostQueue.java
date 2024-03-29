package de.greenrobot.event;

final class PendingPostQueue {
    private PendingPost head;
    private PendingPost tail;

    PendingPostQueue() {
    }

    /* access modifiers changed from: package-private */
    public void enqueue(PendingPost pendingPost) {
        synchronized (this) {
            if (pendingPost != null) {
                if (this.tail != null) {
                    this.tail.next = pendingPost;
                    this.tail = pendingPost;
                } else if (this.head == null) {
                    this.tail = pendingPost;
                    this.head = pendingPost;
                } else {
                    throw new IllegalStateException("Head present, but no tail");
                }
                notifyAll();
            } else {
                throw new NullPointerException("null cannot be enqueued");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public PendingPost poll() {
        PendingPost pendingPost;
        synchronized (this) {
            pendingPost = this.head;
            if (this.head != null) {
                this.head = this.head.next;
                if (this.head == null) {
                    this.tail = null;
                }
            }
        }
        return pendingPost;
    }

    /* access modifiers changed from: package-private */
    public PendingPost poll(int i) throws InterruptedException {
        PendingPost poll;
        synchronized (this) {
            if (this.head == null) {
                wait((long) i);
            }
            poll = poll();
        }
        return poll;
    }
}
