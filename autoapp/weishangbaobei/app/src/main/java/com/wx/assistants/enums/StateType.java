package com.wx.assistants.enums;

public enum StateType {
    STATE_LOAD_ERROR(3),
    STATE_LOAD_EMPTY(4),
    STATE_LOAD_SUCCESS(5),
    STATE_UNLOAD(1);
    
    private final int currentState;

    private StateType(int i) {
        this.currentState = i;
    }

    public int getCurrentState() {
        return this.currentState;
    }
}
