package de.greenrobot.event;

import android.util.Log;
import com.umeng.socialize.common.SocializeConstants;

final class BackgroundPoster implements Runnable {
    private final EventBus eventBus;
    private volatile boolean executorRunning;
    private final PendingPostQueue queue = new PendingPostQueue();

    BackgroundPoster(EventBus eventBus2) {
        this.eventBus = eventBus2;
    }

    public void enqueue(Subscription subscription, Object obj) {
        PendingPost obtainPendingPost = PendingPost.obtainPendingPost(subscription, obj);
        synchronized (this) {
            this.queue.enqueue(obtainPendingPost);
            if (!this.executorRunning) {
                this.executorRunning = true;
                this.eventBus.getExecutorService().execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                PendingPost poll = this.queue.poll(SocializeConstants.CANCLE_RESULTCODE);
                if (poll == null) {
                    synchronized (this) {
                        poll = this.queue.poll();
                        if (poll == null) {
                            this.executorRunning = false;
                            this.executorRunning = false;
                            return;
                        }
                    }
                }
                this.eventBus.invokeSubscriber(poll);
            } catch (InterruptedException e) {
                try {
                    Log.w("Event", Thread.currentThread().getName() + " was interruppted", e);
                    return;
                } finally {
                    this.executorRunning = false;
                }
            }
        }
    }
}
