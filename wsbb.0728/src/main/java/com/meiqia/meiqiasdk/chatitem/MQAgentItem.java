package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.chatitem.MQBaseBubbleItem;

public class MQAgentItem extends MQBaseBubbleItem {
    public MQAgentItem(Context context, MQBaseBubbleItem.Callback callback) {
        super(context, callback);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_chat_left;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        super.initView();
        this.unreadCircle = getViewById(R.id.unread_view);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        super.processLogic();
        applyConfig(true);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }
}
