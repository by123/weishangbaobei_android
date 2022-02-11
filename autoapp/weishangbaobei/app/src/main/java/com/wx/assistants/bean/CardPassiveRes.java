package com.wx.assistants.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CardPassiveRes {
    @SerializedName("cardIds")
    private List<Integer> cardIds;

    public CardPassiveRes(List<Integer> list) {
        this.cardIds = list;
    }
}
