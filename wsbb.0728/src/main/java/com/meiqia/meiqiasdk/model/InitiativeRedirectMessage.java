package com.meiqia.meiqiasdk.model;

import android.support.annotation.StringRes;

public class InitiativeRedirectMessage extends BaseMessage {
    @StringRes
    private int mTipResId;

    public InitiativeRedirectMessage(@StringRes int i) {
        setItemViewType(7);
        this.mTipResId = i;
    }

    public int getTipResId() {
        return this.mTipResId;
    }
}
