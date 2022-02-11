package com.meiqia.meiqiasdk.model;

public class RedirectQueueMessage extends BaseMessage {
    private int queueSize;

    public RedirectQueueMessage(int i) {
        setItemViewType(8);
        this.queueSize = i;
    }

    public int getQueueSize() {
        return this.queueSize;
    }
}
