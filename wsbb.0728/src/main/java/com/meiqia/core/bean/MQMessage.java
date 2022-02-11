package com.meiqia.core.bean;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

public class MQMessage {
    public static final List<String> ALL_SUB_TYPE = Arrays.asList(new String[]{"message", "evaluate", "redirect", "reply", "menu", "queueing", "manual_redirect"});
    public static final String STATE_ARRIVE = "arrived";
    public static final String STATE_FAILED = "failed";
    public static final String STATE_SENDING = "sending";
    public static final String SUB_TYPE_EVALUATE = "evaluate";
    public static final String SUB_TYPE_MANUAL_REDIRECT = "manual_redirect";
    public static final String SUB_TYPE_MENU = "menu";
    public static final String SUB_TYPE_MESSAGE = "message";
    public static final String SUB_TYPE_QUEUEING = "queueing";
    public static final String SUB_TYPE_REDIRECT = "redirect";
    public static final String SUB_TYPE_REPLY = "reply";
    public static final String SUB_TYPE_UNKNOWN = "unknown";
    public static final String TYPE_AUTO_REPLY = "auto_reply";
    public static final String TYPE_CONTENT_FILE = "file";
    public static final String TYPE_CONTENT_HYBRID = "hybrid";
    public static final String TYPE_CONTENT_PHOTO = "photo";
    public static final String TYPE_CONTENT_RICH_TEXT = "rich_text";
    public static final String TYPE_CONTENT_TEXT = "text";
    public static final String TYPE_CONTENT_VOICE = "audio";
    public static final String TYPE_ENDING = "ending";
    public static final String TYPE_FROM_AGENT = "agent";
    public static final String TYPE_FROM_CLIENT = "client";
    public static final String TYPE_FROM_ROBOT = "bot";
    public static final String TYPE_INTERNAL = "internal";
    public static final String TYPE_MESSAGE = "message";
    public static final String TYPE_PROMOTION = "promotion";
    public static final String TYPE_REMARK = "remark";
    public static final String TYPE_REPLY = "reply";
    public static final String TYPE_SDK = "sdk";
    public static final String TYPE_WELCOME = "welcome";
    private String agent_id;
    private String agent_nickname;
    private String avatar;
    private String content;
    private String content_robot;
    private String content_type;
    private long conversation_id;
    private long created_on;
    private long enterprise_id;
    private String extra;
    private String from_type;
    private long id;
    private boolean isAlreadyFeedback;
    private boolean is_read;
    private String media_url;
    private long question_id;
    private String status;
    private String sub_type;
    private String track_id;
    private String type;

    public MQMessage() {
        this("text");
    }

    public MQMessage(String str) {
        this.status = "arrived";
        this.id = System.currentTimeMillis();
        this.content_type = str;
        this.created_on = System.currentTimeMillis();
        this.is_read = true;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MQMessage) && this.id == ((MQMessage) obj).getId();
    }

    public String getAgent_id() {
        return this.agent_id;
    }

    public String getAgent_nickname() {
        return this.agent_nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getContent() {
        return this.content;
    }

    public String getContent_robot() {
        return this.content_robot;
    }

    public String getContent_type() {
        return this.content_type;
    }

    public long getConversation_id() {
        return this.conversation_id;
    }

    public long getCreated_on() {
        return this.created_on;
    }

    public long getEnterprise_id() {
        return this.enterprise_id;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getFrom_type() {
        return this.from_type;
    }

    public long getId() {
        return this.id;
    }

    public String getMedia_url() {
        return this.media_url;
    }

    public long getQuestion_id() {
        return this.question_id;
    }

    public String getStatus() {
        return this.status;
    }

    public String getSub_type() {
        return this.sub_type;
    }

    public String getTrack_id() {
        return this.track_id;
    }

    public String getType() {
        return this.type;
    }

    public boolean isAlreadyFeedback() {
        return this.isAlreadyFeedback;
    }

    public boolean isRobot() {
        return TextUtils.equals(TYPE_FROM_ROBOT, getFrom_type());
    }

    public boolean is_read() {
        return this.is_read;
    }

    public void setAgent_id(String str) {
        this.agent_id = str;
    }

    public void setAgent_nickname(String str) {
        this.agent_nickname = str;
    }

    public void setAlreadyFeedback(boolean z) {
        this.isAlreadyFeedback = z;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setContent_robot(String str) {
        this.content_robot = str;
    }

    public void setContent_type(String str) {
        this.content_type = str;
    }

    public void setConversation_id(long j) {
        this.conversation_id = j;
    }

    public void setCreated_on(long j) {
        this.created_on = j;
    }

    public void setEnterprise_id(long j) {
        this.enterprise_id = j;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setFrom_type(String str) {
        this.from_type = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setIs_read(boolean z) {
        this.is_read = z;
    }

    public void setMedia_url(String str) {
        this.media_url = str;
    }

    public void setQuestion_id(long j) {
        this.question_id = j;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSub_type(String str) {
        this.sub_type = str;
    }

    public void setTrack_id(String str) {
        this.track_id = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
