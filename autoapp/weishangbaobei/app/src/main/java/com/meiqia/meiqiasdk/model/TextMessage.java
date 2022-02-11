package com.meiqia.meiqiasdk.model;

public class TextMessage extends BaseMessage {
    private boolean isContainsSensitiveWords;
    private String replaceContent;

    public TextMessage() {
        setItemViewType(0);
        setContentType("text");
    }

    public TextMessage(String str) {
        this();
        setContent(str);
    }

    public TextMessage(String str, String str2) {
        setItemViewType(1);
        setContent(str);
        setContentType("text");
        setAvatar(str2);
        setStatus("arrived");
    }

    public boolean isContainsSensitiveWords() {
        return this.isContainsSensitiveWords;
    }

    public void setContainsSensitiveWords(boolean z) {
        this.isContainsSensitiveWords = z;
    }

    public String getReplaceContent() {
        return this.replaceContent;
    }

    public void setReplaceContent(String str) {
        this.replaceContent = str;
    }
}
