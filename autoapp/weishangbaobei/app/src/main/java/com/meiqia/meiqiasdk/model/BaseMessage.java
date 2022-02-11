package com.meiqia.meiqiasdk.model;

public class BaseMessage {
    public static final int MAX_TYPE = 12;
    public static final String STATE_ARRIVE = "arrived";
    public static final String STATE_FAILED = "failed";
    public static final String STATE_SENDING = "sending";
    public static final int TYPE_AGENT = 1;
    public static final int TYPE_CLIENT = 0;
    public static final int TYPE_CLUE_CARD = 11;
    public static final String TYPE_CONTENT_FILE = "file";
    public static final String TYPE_CONTENT_HYBRID = "hybrid";
    public static final String TYPE_CONTENT_PHOTO = "photo";
    public static final String TYPE_CONTENT_RICH_TEXT = "rich_text";
    public static final String TYPE_CONTENT_TEXT = "text";
    public static final String TYPE_CONTENT_UNKNOWN = "unknown";
    public static final String TYPE_CONTENT_VOICE = "audio";
    public static final String TYPE_ENDING = "ending";
    public static final int TYPE_EVALUATE = 4;
    public static final String TYPE_FROM_AGENT = "agent";
    public static final String TYPE_FROM_CLIENT = "client";
    public static final int TYPE_HYBRID = 10;
    public static final int TYPE_INITIATIVE_REDIRECT_TIP = 7;
    public static final String TYPE_INTERNAL = "internal";
    public static final String TYPE_MESSAGE = "message";
    public static final int TYPE_NO_AGENT_TIP = 6;
    public static final int TYPE_QUEUE_TIP = 8;
    public static final String TYPE_REMARK = "remark";
    public static final String TYPE_REPLY = "reply";
    public static final int TYPE_RICH_TEXT = 9;
    public static final int TYPE_ROBOT = 5;
    public static final int TYPE_TIME = 2;
    public static final int TYPE_TIP = 3;
    public static final String TYPE_WELCOME = "welcome";
    private String agentNickname;
    private String avatar;
    private String content;
    private String contentType;
    private long conversationId;
    private long createdOn = System.currentTimeMillis();
    private long id;
    private boolean isRead;
    private int itemViewType;
    private String status;
    private String type;

    public int getItemViewType() {
        return this.itemViewType;
    }

    public void setItemViewType(int i) {
        this.itemViewType = i;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long j) {
        this.createdOn = j;
    }

    public String getAgentNickname() {
        return this.agentNickname;
    }

    public void setAgentNickname(String str) {
        this.agentNickname = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public void setIsRead(boolean z) {
        this.isRead = z;
    }

    public long getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(long j) {
        this.conversationId = j;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof BaseMessage) && this.id == ((BaseMessage) obj).getId()) {
            return true;
        }
        return false;
    }
}
