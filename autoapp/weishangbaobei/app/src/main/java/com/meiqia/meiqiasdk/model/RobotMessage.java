package com.meiqia.meiqiasdk.model;

public class RobotMessage extends BaseMessage {
    public static final int EVALUATE_USEFUL = 1;
    public static final int EVALUATE_USELESS = 0;
    public static final String SUB_TYPE_EVALUATE = "evaluate";
    public static final String SUB_TYPE_MANUAL_REDIRECT = "manual_redirect";
    public static final String SUB_TYPE_MENU = "menu";
    public static final String SUB_TYPE_MESSAGE = "message";
    public static final String SUB_TYPE_QUEUEING = "queueing";
    public static final String SUB_TYPE_REDIRECT = "redirect";
    public static final String SUB_TYPE_REPLY = "reply";
    public static final String SUB_TYPE_UNKNOWN = "unknown";
    private String contentRobot;
    private String extra;
    private boolean isAlreadyFeedback;
    private long questionId;
    private String subType;

    public RobotMessage() {
        setItemViewType(5);
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String str) {
        this.subType = str;
    }

    public String getContentRobot() {
        return this.contentRobot;
    }

    public void setContentRobot(String str) {
        this.contentRobot = str;
    }

    public long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(long j) {
        this.questionId = j;
    }

    public boolean isAlreadyFeedback() {
        return this.isAlreadyFeedback;
    }

    public void setAlreadyFeedback(boolean z) {
        this.isAlreadyFeedback = z;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }
}
