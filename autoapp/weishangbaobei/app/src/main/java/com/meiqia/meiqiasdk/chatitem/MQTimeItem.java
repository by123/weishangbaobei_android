package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.util.MQTimeUtils;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;

public class MQTimeItem extends MQBaseCustomCompositeView {
    private TextView mContentTv;

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public MQTimeItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_chat_time;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mContentTv = (TextView) getViewById(R.id.content_tv);
    }

    public void setMessage(BaseMessage baseMessage) {
        this.mContentTv.setText(MQTimeUtils.parseTime(baseMessage.getCreatedOn()));
    }
}
