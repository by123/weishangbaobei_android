package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.view.View;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.LeaveMessageCallback;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;

public class MQNoAgentItem extends MQBaseCustomCompositeView {
    private LeaveMessageCallback mCallback;

    public MQNoAgentItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_no_agent;
    }

    /* access modifiers changed from: protected */
    public void initView() {
    }

    public void onClick(View view) {
        if (this.mCallback != null) {
            this.mCallback.onClickLeaveMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    public void setCallback(LeaveMessageCallback leaveMessageCallback) {
        this.mCallback = leaveMessageCallback;
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        getViewById(R.id.tv_no_agent_leave_msg).setOnClickListener(this);
    }
}
